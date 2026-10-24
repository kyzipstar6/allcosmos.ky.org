// Compile (Linux example):
//   g++ -std=c++17 NovaAnimation.cpp -o NovaAnimation \
//       -lraylib -lGL -lm -lpthread -ldl -lrt -lX11
// (On Windows/macOS use the raylib MSVC / brew installs instead.)

#include "raylib.h"
#include <vector>
#include <random>
#include <string>
#include <cmath>
#include <sstream>
#include <iomanip>
#include <algorithm>

// ─────────────────────────────────────────────────────────────────
// RNG
// ─────────────────────────────────────────────────────────────────
static std::mt19937 rng(std::random_device{}());
float randFloat(float a, float b) { return std::uniform_real_distribution<float>(a, b)(rng); }
int   randInt  (int   a, int   b) { return std::uniform_int_distribution<int>(a, b)(rng); }

// ─────────────────────────────────────────────────────────────────
// Enums
// ─────────────────────────────────────────────────────────────────
enum class SpectralType  { O, B, A, F, G, K, M };
enum class EvolutionStage{ MAIN_SEQUENCE, RED_GIANT, RED_SUPERGIANT,
                           WOLF_RAYET, WHITE_DWARF, NEUTRON_STAR, STELLAR_BLACK_HOLE };
enum class ExplosionType { NOVA, SUPERNOVA, HYPERNOVA };
enum class StellarEvent  { NONE, PLANETARY_NEBULA, TYPE_II, HYPERNOVA, GAMMA_RAY_BURST };
enum class Phase         { SEARCHING, TRACKING, EXPLODING, RESULT };

// ─────────────────────────────────────────────────────────────────
// Star Data
// ─────────────────────────────────────────────────────────────────
struct StarData {
    std::string  designation;
    SpectralType spectralType;
    EvolutionStage stage;
    StellarEvent   event;
    ExplosionType  explosion;
    double massSolar, radiusSolar, luminositySolar, temperatureKelvin;
    double ageYears, lifetimeYears, distanceLY;
    Color  colour;
};

// ─────────────────────────────────────────────────────────────────
// Physics helpers
// ─────────────────────────────────────────────────────────────────
Color temperatureToColour(double T) {
    if (T > 30000) return Color{215, 235, 255, 255};
    if (T > 10000) return Color{180, 220, 255, 255};
    if (T >  7500) return Color{255, 255, 255, 255};
    if (T >  6000) return Color{255, 250, 220, 255};
    if (T >  5000) return Color{255, 215, 100, 255};
    if (T >  3500) return Color{255, 160,  80, 255};
    return                Color{255, 100,  60, 255};
}

double randomMass(SpectralType t) {
    switch (t) {
        case SpectralType::O: return randFloat(16.f, 80.f);
        case SpectralType::B: return randFloat(2.1f, 16.f);
        case SpectralType::A: return randFloat(1.4f, 2.1f);
        case SpectralType::F: return randFloat(1.04f, 1.4f);
        case SpectralType::G: return randFloat(0.8f, 1.04f);
        case SpectralType::K: return randFloat(0.45f, 0.8f);
        case SpectralType::M: return randFloat(0.08f, 0.45f);
    }
    return 1.0;
}

double randomTemperature(SpectralType t) {
    switch (t) {
        case SpectralType::O: return randFloat(30000.f, 50000.f);
        case SpectralType::B: return randFloat(10000.f, 30000.f);
        case SpectralType::A: return randFloat(7500.f, 10000.f);
        case SpectralType::F: return randFloat(6000.f, 7500.f);
        case SpectralType::G: return randFloat(5200.f, 6000.f);
        case SpectralType::K: return randFloat(3700.f, 5200.f);
        case SpectralType::M: return randFloat(2400.f, 3700.f);
    }
    return 5778.0;
}

double radiusForStage(double mass, EvolutionStage s) {
    switch (s) {
        case EvolutionStage::MAIN_SEQUENCE:      return std::pow(mass, 0.80);
        case EvolutionStage::RED_GIANT:          return randFloat(20.f, 100.f) * std::pow(mass, 0.45);
        case EvolutionStage::RED_SUPERGIANT:     return randFloat(400.f, 1200.f);
        case EvolutionStage::WOLF_RAYET:         return randFloat(2.f, 15.f);
        case EvolutionStage::WHITE_DWARF:        return randFloat(0.009f, 0.02f);
        case EvolutionStage::NEUTRON_STAR:       return 0.00002;
        case EvolutionStage::STELLAR_BLACK_HOLE: return (2953.0 * mass) / 695700000.0; // Schwarzschild
    }
    return 1.0;
}

EvolutionStage pickStage(double mass, double age, double lifetime) {
    double f = age / lifetime;
    if (f < 0.85) return EvolutionStage::MAIN_SEQUENCE;
    if (mass < 8)  return (f < 0.98) ? EvolutionStage::RED_GIANT       : EvolutionStage::WHITE_DWARF;
    if (mass < 25) return (f < 0.98) ? EvolutionStage::RED_SUPERGIANT  : EvolutionStage::NEUTRON_STAR;
    return               (f < 0.98) ? EvolutionStage::WOLF_RAYET       : EvolutionStage::STELLAR_BLACK_HOLE;
}

StellarEvent predictEvent(double mass) {
    if (mass < 0.08) return StellarEvent::NONE;
    if (mass < 8)    return StellarEvent::PLANETARY_NEBULA;
    if (mass < 25)   return StellarEvent::TYPE_II;
    if (mass < 40)   return StellarEvent::HYPERNOVA;
    return StellarEvent::GAMMA_RAY_BURST;
}

StarData generateStar() {
    StarData s;
    static const char* prefixes[] = {"HD","TYC","RX","SN","VX","RS","NGC","PB","RT","AW","GOR"};
    s.designation = std::string(prefixes[randInt(0, 10)]) + " "
                  + std::to_string(randInt(0, 999999)) + " "
                  + std::to_string(randInt(0, 9999)) + "-" + std::to_string(randInt(0, 9999));

    // realistic IMF-weighted pick
    double r = randFloat(0, 1);
    if      (r < 0.760 ) s.spectralType = SpectralType::M;
    else if (r < 0.880 ) s.spectralType = SpectralType::K;
    else if (r < 0.956 ) s.spectralType = SpectralType::G;
    else if (r < 0.986 ) s.spectralType = SpectralType::F;
    else if (r < 0.992 ) s.spectralType = SpectralType::A;
    else if (r < 0.9997) s.spectralType = SpectralType::B;
    else                 s.spectralType = SpectralType::O;

    s.massSolar      = randomMass(s.spectralType);
    s.lifetimeYears  = 1.0e10 / std::pow(s.massSolar, 2.5);      // t ∝ M^-2.5
    s.ageYears       = randFloat(0, 1) * s.lifetimeYears;
    s.stage          = pickStage(s.massSolar, s.ageYears, s.lifetimeYears);
    s.radiusSolar    = radiusForStage(s.massSolar, s.stage);
    s.temperatureKelvin = randomTemperature(s.spectralType);
    s.luminositySolar   = std::pow(s.massSolar, 3.5);             // L ∝ M^3.5
    s.distanceLY     = randInt(5, 100000);
    s.colour         = temperatureToColour(s.temperatureKelvin);
    s.event          = predictEvent(s.massSolar);

    if      (s.massSolar > 10) s.explosion = ExplosionType::HYPERNOVA;
    else if (s.massSolar >  3) s.explosion = ExplosionType::SUPERNOVA;
    else                       s.explosion = ExplosionType::NOVA;

    return s;
}

// ─────────────────────────────────────────────────────────────────
// Particle Engine
// ─────────────────────────────────────────────────────────────────
struct Particle { Vector2 pos, vel; float life, maxLife, radius; Color color; };

struct ParticleEngine {
    std::vector<Particle> particles;

    void explode(Vector2 c, int n, Color col, float vMin, float vMax) {
        for (int i = 0; i < n; i++) {
            float a = randFloat(0, 2 * PI);
            float s = randFloat(vMin, vMax);
            Particle p;
            p.pos    = c;
            p.vel    = { cosf(a) * s, sinf(a) * s };
            p.maxLife = randFloat(1.2f, 3.0f);
            p.life    = p.maxLife;
            p.radius  = randFloat(1.0f, 3.2f);
            p.color   = col;
            particles.push_back(p);
        }
    }

    void update(float dt) {
        for (auto& p : particles) {
            p.pos.x += p.vel.x * dt;
            p.pos.y += p.vel.y * dt;
            p.vel.x *= 0.985f;
            p.vel.y *= 0.985f;
            p.life  -= dt;
        }
        particles.erase(std::remove_if(particles.begin(), particles.end(),
            [](const Particle& p){ return p.life <= 0; }), particles.end());
    }

    void draw() const {
        for (auto& p : particles) {
            float a = std::max(0.0f, p.life / p.maxLife);
            Color c = p.color;
            c.a = (unsigned char)(255 * a);
            DrawCircleV(p.pos, p.radius * (0.4f + a * 0.6f), c);
        }
    }
};

// ─────────────────────────────────────────────────────────────────
// Rendering — pulsating star
// ─────────────────────────────────────────────────────────────────
void drawStar(Vector2 center, float R, Color baseColor, float t, float pulsation) {
    // Outer corona glow
    for (int i = 14; i >= 1; i--) {
        float k = (float)i / 14.0f;
        Color c = baseColor;
        c.a = (unsigned char)(10 + (14 - i) * 3);
        DrawCircleV(center, R * (1.0f + k * 1.6f), c);
    }

    // Noisy surface polygon (three harmonics → organic pulse)
    const int N = 160;
    Vector2 pts[N + 1];
    pts[0] = center;
    for (int i = 0; i < N; i++) {
        float a = 2.0f * PI * i / N;
        float r = R * (1.0f + 0.015f * sinf(t * 0.8f));
        r += sinf(a *  2 + t * 1.2f) * 10.0f * pulsation;
        r += sinf(a *  7 - t * 1.6f) *  4.0f * pulsation;
        r += sinf(a * 17 + t * 2.9f) *  2.0f * pulsation;
        pts[i + 1] = { center.x + cosf(a) * r, center.y + sinf(a) * r };
    }
    DrawTriangleFan(pts, N + 1, baseColor);

    // Limb darkening — smooth radial brightening
    for (int i = 18; i >= 1; i--) {
        float k = (float)i / 18.0f;
        float b = 1.0f - k;
        Color c = baseColor;
        c.r = (unsigned char)std::min(255.0f, c.r + (255.0f - c.r) * b * 0.8f);
        c.g = (unsigned char)std::min(255.0f, c.g + (255.0f - c.g) * b * 0.8f);
        c.b = (unsigned char)std::min(255.0f, c.b + (255.0f - c.b) * b * 0.8f);
        c.a = 45;
        DrawCircleV(center, R * 0.92f * k, c);
    }

    Color core = WHITE;
    core.a = 200;
    DrawCircleV(center, R * 0.25f, core);
}

// ─────────────────────────────────────────────────────────────────
// Rendering — remnant
// ─────────────────────────────────────────────────────────────────
void drawRemnant(const StarData& star, Vector2 c, float t) {
    if (star.explosion == ExplosionType::NOVA) {
        // White dwarf
        for (int i = 8; i >= 1; i--) {
            Color col = {180, 220, 255, (unsigned char)(20 + i * 3)};
            DrawCircleV(c, 6 + i * 3, col);
        }
        DrawCircleV(c, 6, WHITE);
        DrawCircleV(c, 3, Color{230, 245, 255, 255});
    }
    else if (star.explosion == ExplosionType::SUPERNOVA) {
        // Neutron star with rotating magnetic beams
        float ang = t * 3.0f;
        for (int i = 0; i < 2; i++) {
            float a = ang + i * PI;
            Vector2 end = { c.x + cosf(a) * 70, c.y + sinf(a) * 70 };
            DrawLineEx(c, end, 2.0f, Color{180, 240, 255, 130});
        }
        for (int i = 6; i >= 1; i--) {
            Color col = {150, 220, 255, (unsigned char)(30 + i * 4)};
            DrawCircleV(c, 3 + i * 2, col);
        }
        DrawCircleV(c, 3, WHITE);
    }
    else {
        // Stellar black hole — dark core + glowing accretion ring
        for (int i = 10; i >= 1; i--) {
            float k = (float)i / 10.0f;
            Color col = {255, (unsigned char)(150 + (int)(80 * (1 - k))), 50,
                         (unsigned char)(15 + i * 5)};
            DrawCircleV(c, 25 + k * 40, col);
        }
        for (int i = 0; i < 48; i++) {
            float a = t * 1.5f + i * 2.0f * PI / 48;
            Vector2 p = { c.x + cosf(a) * 32, c.y + sinf(a) * 32 };
            DrawCircleV(p, 2.0f, Color{255, 200, 100, 180});
        }
        DrawCircleV(c, 26, BLACK);
    }
}

// ─────────────────────────────────────────────────────────────────
// Background stars
// ─────────────────────────────────────────────────────────────────
struct BgStar { Vector2 pos; float radius, alpha, phase; Color color; };

std::vector<BgStar> makeBgStars(int count) {
    std::vector<BgStar> v;
    v.reserve(count);
    for (int i = 0; i < count; i++) {
        BgStar b;
        b.pos    = { randFloat(0, 1400), randFloat(0, 800) };
        b.radius = randFloat(0.5f, 2.0f);
        b.alpha  = randFloat(0.2f, 0.95f);
        b.phase  = randFloat(0, 2 * PI);
        float rr = randFloat(0, 1);
        if      (rr < 0.80f) b.color = WHITE;
        else if (rr < 0.90f) b.color = Color{180, 220, 255, 255};
        else if (rr < 0.97f) b.color = Color{255, 240, 200, 255};
        else                 b.color = Color{255, 200, 150, 255};
        v.push_back(b);
    }
    return v;
}

// ─────────────────────────────────────────────────────────────────
// HUD
// ─────────────────────────────────────────────────────────────────
std::string fixedStr(double v, int prec) {
    std::ostringstream oss;
    oss << std::fixed << std::setprecision(prec) << v;
    return oss.str();
}
std::string ageStr(double years) { return fixedStr(years / 1e6, 2) + " My"; }

std::string spectralName(SpectralType t) {
    const char* n[] = {"O","B","A","F","G","K","M"};
    return n[(int)t];
}
std::string stageName(EvolutionStage s) {
    switch (s) {
        case EvolutionStage::MAIN_SEQUENCE:      return "Main Sequence";
        case EvolutionStage::RED_GIANT:          return "Red Giant";
        case EvolutionStage::RED_SUPERGIANT:     return "Red Supergiant";
        case EvolutionStage::WOLF_RAYET:         return "Wolf-Rayet";
        case EvolutionStage::WHITE_DWARF:        return "White Dwarf";
        case EvolutionStage::NEUTRON_STAR:       return "Neutron Star";
        case EvolutionStage::STELLAR_BLACK_HOLE: return "Stellar Black Hole";
    }
    return "?";
}
std::string eventName(StellarEvent e) {
    switch (e) {
        case StellarEvent::NONE:            return "None (Stable Remnant)";
        case StellarEvent::PLANETARY_NEBULA:return "Planetary Nebula";
        case StellarEvent::TYPE_II:         return "Type II Supernova";
        case StellarEvent::HYPERNOVA:       return "Hypernova";
        case StellarEvent::GAMMA_RAY_BURST: return "Gamma Ray Burst";
    }
    return "?";
}
std::string explosionName(ExplosionType e) {
    switch (e) {
        case ExplosionType::NOVA:       return "NOVA";
        case ExplosionType::SUPERNOVA:  return "SUPERNOVA";
        case ExplosionType::HYPERNOVA:  return "HYPERNOVA";
    }
    return "?";
}

void drawHUD(const StarData& star, Phase phase, float clock) {
    int x = 30, y = 30, fs = 20, line = 26;
    Color bright = {120, 255, 140, 255};
    Color dim    = { 60, 160,  80, 255};
    Color accent = {180, 255, 200, 255};

    DrawText("ANTARIS DEEP SPACE OBSERVATORY", x, y, fs, bright); y += line;
    DrawText("========================================", x, y, fs, dim); y += line + 6;

    if (phase == Phase::SEARCHING) {
        int dots = ((int)(clock * 2.5f)) % 4;
        std::string msg = "Scanning nearby galaxies";
        for (int i = 0; i < dots; i++) msg += ".";
        DrawText(msg.c_str(), x, y, fs, bright);
        DrawText("Press R to regenerate target", x, GetScreenHeight() - 30, 14, dim);
        return;
    }

    DrawText("TARGET ACQUIRED", x, y, fs, accent); y += line + 6;

    auto put = [&](const std::string& k, const std::string& v) {
        std::string s = k + " : " + v;
        DrawText(s.c_str(), x, y, fs, bright);
        y += line;
    };

    put("Designation",     star.designation);
    put("Spectral Type",   spectralName(star.spectralType));
    put("Evolution Stage", stageName(star.stage));
    y += 6;
    put("Mass",            fixedStr(star.massSolar, 3)       + " M_sun");
    put("Radius",          fixedStr(star.radiusSolar, 3)     + " R_sun");
    put("Luminosity",      fixedStr(star.luminositySolar, 3) + " L_sun");
    put("Temperature",     std::to_string((int)star.temperatureKelvin) + " K");
    put("Age",             ageStr(star.ageYears));
    put("Expected Lifetime", ageStr(star.lifetimeYears));
    put("Distance",        fixedStr(star.distanceLY, 1) + " ly");
    y += 6;
    put("Predicted Final Event", eventName(star.event));

    if (phase == Phase::EXPLODING || phase == Phase::RESULT) {
        y += 10;
        std::string line2 = "*** " + explosionName(star.explosion) + " DETECTED ***";
        DrawText(line2.c_str(), x, y, fs + 4, Color{255, 220, 100, 255});
    }

    DrawText("Press R to regenerate target", x, GetScreenHeight() - 30, 14, dim);
}

// ─────────────────────────────────────────────────────────────────
// Main
// ─────────────────────────────────────────────────────────────────
int main() {
    InitWindow(1400, 800, "Antaris Deep Space Observatory — Nova Simulation");
    SetTargetFPS(60);

    Phase    phase      = Phase::SEARCHING;
    StarData star       = generateStar();

    float clock      = 0.0f;   // global time (background twinkle)
    float phaseClock = 0.0f;   // resets on every phase transition
    float flashAlpha = 0.0f;
    Color flashColor = WHITE;
    bool  explosionTriggered = false;

    ParticleEngine     particles;
    std::vector<BgStar> bgStars = makeBgStars(1600);

    Vector2 starCenter      = { 880, 400 };
    float   baseStarRadius  = 175.0f;

    while (!WindowShouldClose()) {
        float dt = GetFrameTime();
        clock      += dt;
        phaseClock += dt;

        // ── Reset ────────────────────────────────────────────────
        if (IsKeyPressed(KEY_R)) {
            star             = generateStar();
            phase            = Phase::SEARCHING;
            clock            = 0;
            phaseClock       = 0;
            flashAlpha       = 0;
            explosionTriggered = false;
            particles.particles.clear();
            bgStars          = makeBgStars(1600);
        }

        // ── Phase transitions ────────────────────────────────────
        if (phase == Phase::SEARCHING && phaseClock > 3.0f) {
            phase = Phase::TRACKING; phaseClock = 0;
        }
        if (phase == Phase::TRACKING && phaseClock > 7.0f) {
            phase = Phase::EXPLODING; phaseClock = 0;
        }
        if (phase == Phase::EXPLODING && !explosionTriggered) {
            explosionTriggered = true;
            switch (star.explosion) {
                case ExplosionType::NOVA:
                    flashColor = WHITE; flashAlpha = 1.0f;
                    particles.explode(starCenter,  350, star.colour,   80, 320);
                    break;
                case ExplosionType::SUPERNOVA:
                    flashColor = WHITE; flashAlpha = 1.0f;
                    particles.explode(starCenter,  900, ORANGE,        140, 600);
                    break;
                case ExplosionType::HYPERNOVA:
                    flashColor = Color{200, 230, 255, 255}; flashAlpha = 1.0f;
                    particles.explode(starCenter, 1800, Color{100,180,255,255}, 200, 900);
                    break;
            }
        }
        if (phase == Phase::EXPLODING && phaseClock > 3.5f) {
            phase = Phase::RESULT; phaseClock = 0;
        }

        // ── Updates ──────────────────────────────────────────────
        if (flashAlpha > 0) {
            flashAlpha -= dt * 2.2f;
            if (flashAlpha < 0) flashAlpha = 0;
        }
        particles.update(dt);

        // ── Draw ─────────────────────────────────────────────────
        BeginDrawing();
        ClearBackground({2, 2, 10, 255});

        // Twinkling background
        for (auto& b : bgStars) {
            float tw = 0.7f + 0.3f * sinf(clock * 2.0f + b.phase);
            Color c = b.color;
            c.a = (unsigned char)(255 * b.alpha * tw);
            DrawCircleV(b.pos, b.radius, c);
        }

        // Central object
        if (phase == Phase::TRACKING) {
            drawStar(starCenter, baseStarRadius, star.colour, phaseClock, 2.2f);
        } else if (phase == Phase::EXPLODING || phase == Phase::RESULT) {
            drawRemnant(star, starCenter, clock);
        }

        particles.draw();

        // Screen flash
        if (flashAlpha > 0) {
            Color c = flashColor;
            c.a = (unsigned char)(255 * flashAlpha);
            DrawRectangle(0, 0, GetScreenWidth(), GetScreenHeight(), c);
        }

        drawHUD(star, phase, clock);

        EndDrawing();
    }

    CloseWindow();
    return 0;
}