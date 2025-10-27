package application;                        
                        
import application.*;                        
import javafx.util.Duration;                        
import javafx.animation.Animation;                        
import javafx.animation.KeyFrame;                        
import javafx.animation.Timeline;                        
import javafx.application.*;                        
import javafx.geometry.Insets;                        
//import javafx.geometry.Pos;                                       
import javafx.scene.Node;                        
import javafx.scene.Scene;                        
import javafx.scene.control.*;                        
import javafx.scene.shape.Circle;                        
import javafx.scene.image.Image;                        
import javafx.scene.image.ImageView;                        
import javafx.scene.layout.*;                        
import javafx.scene.paint.Color;                        
import javafx.scene.text.Font;                        
import javafx.scene.text.FontWeight;                        
import javafx.stage.DirectoryChooser;                        
import javafx.stage.FileChooser;                        
import javafx.stage.FileChooser.ExtensionFilter;                        
import javafx.util.Duration;                        
import javafx.stage.Stage;                        
import javafx.stage.StageStyle;                        
                        
import java.io.BufferedReader;                        
import java.io.File;                        
import java.io.FileReader;                        
import java.io.IOException;                        
                        
import java.time.LocalDateTime;                        
import java.util.ArrayList;                        
import java.util.Arrays;                        
import java.util.List;                        
import java.util.Random;                        
import java.util.concurrent.Executors;                        
import java.util.concurrent.ScheduledExecutorService;                        
import java.util.concurrent.TimeUnit;                        
import java.util.concurrent.atomic.AtomicBoolean;                        
import java.util.concurrent.atomic.AtomicReference;                        
                        
public class Movements {                        
                        
    public Movements() {                        
                        
    }                        
                        
    public void infiniteMv(Label el, int rep) {                        
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(0), "", e -> {                        
            el.setLayoutX(641.6);                        
            el.setLayoutY(596.0);                        
        }),                        
                new KeyFrame(Duration.millis(10), "", e -> {                        
                    el.setLayoutX(632.0);                        
                    el.setLayoutY(581.6);                        
                }),                        
                new KeyFrame(Duration.millis(20), "", e -> {                        
                    el.setLayoutX(628.0);                        
                    el.setLayoutY(576.8);                        
                }),                        
                new KeyFrame(Duration.millis(30), "", e -> {                        
                    el.setLayoutX(625.6);                        
                    el.setLayoutY(573.6);                        
                }),                        
                new KeyFrame(Duration.millis(40), "", e -> {                        
                    el.setLayoutX(622.4);                        
                    el.setLayoutY(568.8);                        
                }),                        
                new KeyFrame(Duration.millis(50), "", e -> {                        
                    el.setLayoutX(620.0);                        
                    el.setLayoutY(565.6);                        
                }),                        
                new KeyFrame(Duration.millis(60), "", e -> {                        
                    el.setLayoutX(617.6);                        
                    el.setLayoutY(562.4);                        
                }),                        
                new KeyFrame(Duration.millis(70), "", e -> {                        
                    el.setLayoutX(612.8);                        
                    el.setLayoutY(558.4);                        
                }),                        
                new KeyFrame(Duration.millis(80), "", e -> {                        
                    el.setLayoutX(608.8);                        
                    el.setLayoutY(553.6);                        
                }),                        
                new KeyFrame(Duration.millis(90), "", e -> {                        
                    el.setLayoutX(604.8);                        
                    el.setLayoutY(549.6);                        
                }),                        
                new KeyFrame(Duration.millis(100), "", e -> {                        
                    el.setLayoutX(599.2);                        
                    el.setLayoutY(545.6);                        
                }),                        
                new KeyFrame(Duration.millis(110), "", e -> {                        
                    el.setLayoutX(594.4);                        
                    el.setLayoutY(543.2);                        
                }),                        
                new KeyFrame(Duration.millis(120), "", e -> {                        
                    el.setLayoutX(588.8);                        
                    el.setLayoutY(540.0);                        
                }),                        
                new KeyFrame(Duration.millis(130), "", e -> {                        
                    el.setLayoutX(582.4);                        
                    el.setLayoutY(536.0);                        
                }),                        
                new KeyFrame(Duration.millis(140), "", e -> {                        
                    el.setLayoutX(576.8);                        
                    el.setLayoutY(532.8);                        
                }),                        
                new KeyFrame(Duration.millis(150), "", e -> {                        
                    el.setLayoutX(570.4);                        
                    el.setLayoutY(530.4);                        
                }),                        
                new KeyFrame(Duration.millis(160), "", e -> {                        
                    el.setLayoutX(568.8);                        
                    el.setLayoutY(529.6);                        
                }),                        
                new KeyFrame(Duration.millis(170), "", e -> {                        
                    el.setLayoutX(564.0);                        
                    el.setLayoutY(528.0);                        
                }),                        
                new KeyFrame(Duration.millis(180), "", e -> {                        
                    el.setLayoutX(557.6);                        
                    el.setLayoutY(525.6);                        
                }),                        
                new KeyFrame(Duration.millis(190), "", e -> {                        
                    el.setLayoutX(550.4);                        
                    el.setLayoutY(522.4);                        
                }),                        
                new KeyFrame(Duration.millis(200), "", e -> {                        
                    el.setLayoutX(543.2);                        
                    el.setLayoutY(520.0);                        
                }),                        
                new KeyFrame(Duration.millis(210), "", e -> {                        
                    el.setLayoutX(536.0);                        
                    el.setLayoutY(516.8);                        
                }),                        
                new KeyFrame(Duration.millis(220), "", e -> {                        
                    el.setLayoutX(528.8);                        
                    el.setLayoutY(512.8);                        
                }),                        
                new KeyFrame(Duration.millis(230), "", e -> {                        
                    el.setLayoutX(521.6);                        
                    el.setLayoutY(507.2);                        
                }),                        
                new KeyFrame(Duration.millis(240), "", e -> {                        
                    el.setLayoutX(512.8);                        
                    el.setLayoutY(501.6);                        
                }),                        
                new KeyFrame(Duration.millis(250), "", e -> {                        
                    el.setLayoutX(504.0);                        
                    el.setLayoutY(496.8);                        
                }),                        
                new KeyFrame(Duration.millis(260), "", e -> {                        
                    el.setLayoutX(496.8);                        
                    el.setLayoutY(490.4);                        
                }),                        
                new KeyFrame(Duration.millis(270), "", e -> {                        
                    el.setLayoutX(488.0);                        
                    el.setLayoutY(483.2);                        
                }),                        
                new KeyFrame(Duration.millis(280), "", e -> {                        
                    el.setLayoutX(479.2);                        
                    el.setLayoutY(476.8);                        
                }),                        
                new KeyFrame(Duration.millis(290), "", e -> {                        
                    el.setLayoutX(470.4);                        
                    el.setLayoutY(469.6);                        
                }),                        
                new KeyFrame(Duration.millis(300), "", e -> {                        
                    el.setLayoutX(461.6);                        
                    el.setLayoutY(461.6);                        
                }),                        
                new KeyFrame(Duration.millis(310), "", e -> {                        
                    el.setLayoutX(452.8);                        
                    el.setLayoutY(452.8);                        
                }),                        
                new KeyFrame(Duration.millis(320), "", e -> {                        
                    el.setLayoutX(444.0);                        
                    el.setLayoutY(444.0);                        
                }),                        
                new KeyFrame(Duration.millis(330), "", e -> {                        
                    el.setLayoutX(436.8);                        
                    el.setLayoutY(435.2);                        
                }),                        
                new KeyFrame(Duration.millis(340), "", e -> {                        
                    el.setLayoutX(428.0);                        
                    el.setLayoutY(428.0);                        
                }),                        
                new KeyFrame(Duration.millis(350), "", e -> {                        
                    el.setLayoutX(420.0);                        
                    el.setLayoutY(419.2);                        
                }),                        
                new KeyFrame(Duration.millis(360), "", e -> {                        
                    el.setLayoutX(411.2);                        
                    el.setLayoutY(410.4);                        
                }),                        
                new KeyFrame(Duration.millis(370), "", e -> {                        
                    el.setLayoutX(404.0);                        
                    el.setLayoutY(402.4);                        
                }),                        
                new KeyFrame(Duration.millis(380), "", e -> {                        
                    el.setLayoutX(396.0);                        
                    el.setLayoutY(395.2);                        
                }),                        
                new KeyFrame(Duration.millis(390), "", e -> {                        
                    el.setLayoutX(388.8);                        
                    el.setLayoutY(386.4);                        
                }),                        
                new KeyFrame(Duration.millis(400), "", e -> {                        
                    el.setLayoutX(382.4);                        
                    el.setLayoutY(379.2);                        
                }),                        
                new KeyFrame(Duration.millis(410), "", e -> {                        
                    el.setLayoutX(376.0);                        
                    el.setLayoutY(371.2);                        
                }),                        
                new KeyFrame(Duration.millis(420), "", e -> {                        
                    el.setLayoutX(369.6);                        
                    el.setLayoutY(364.0);                        
                }),                        
                new KeyFrame(Duration.millis(430), "", e -> {                        
                    el.setLayoutX(364.8);                        
                    el.setLayoutY(357.6);                        
                }),                        
                new KeyFrame(Duration.millis(440), "", e -> {                        
                    el.setLayoutX(359.2);                        
                    el.setLayoutY(352.0);                        
                }),                        
                new KeyFrame(Duration.millis(450), "", e -> {                        
                    el.setLayoutX(354.4);                        
                    el.setLayoutY(345.6);                        
                }),                        
                new KeyFrame(Duration.millis(460), "", e -> {                        
                    el.setLayoutX(348.8);                        
                    el.setLayoutY(339.2);                        
                }),                        
                new KeyFrame(Duration.millis(470), "", e -> {                        
                    el.setLayoutX(345.6);                        
                    el.setLayoutY(334.4);                        
                }),                        
                new KeyFrame(Duration.millis(480), "", e -> {                        
                    el.setLayoutX(341.6);                        
                    el.setLayoutY(329.6);                        
                }),                        
                new KeyFrame(Duration.millis(490), "", e -> {                        
                    el.setLayoutX(337.6);                        
                    el.setLayoutY(326.4);                        
                }),                        
                new KeyFrame(Duration.millis(500), "", e -> {                        
                    el.setLayoutX(334.4);                        
                    el.setLayoutY(322.4);                        
                }),                        
                new KeyFrame(Duration.millis(510), "", e -> {                        
                    el.setLayoutX(330.4);                        
                    el.setLayoutY(319.2);                        
                }),                        
                new KeyFrame(Duration.millis(520), "", e -> {                        
                    el.setLayoutX(327.2);                        
                    el.setLayoutY(315.2);                        
                }),                        
                new KeyFrame(Duration.millis(530), "", e -> {                        
                    el.setLayoutX(323.2);                        
                    el.setLayoutY(312.0);                        
                }),                        
                new KeyFrame(Duration.millis(540), "", e -> {                        
                    el.setLayoutX(320.0);                        
                    el.setLayoutY(308.0);                        
                }),                        
                new KeyFrame(Duration.millis(550), "", e -> {                        
                    el.setLayoutX(316.8);                        
                    el.setLayoutY(304.8);                        
                }),                        
                new KeyFrame(Duration.millis(560), "", e -> {                        
                    el.setLayoutX(314.4);                        
                    el.setLayoutY(301.6);                        
                }),                        
                new KeyFrame(Duration.millis(570), "", e -> {                        
                    el.setLayoutX(312.0);                        
                    el.setLayoutY(297.6);                        
                }),                        
                new KeyFrame(Duration.millis(580), "", e -> {                        
                    el.setLayoutX(309.6);                        
                    el.setLayoutY(295.2);                        
                }),                        
                new KeyFrame(Duration.millis(590), "", e -> {                        
                    el.setLayoutX(308.0);                        
                    el.setLayoutY(292.0);                        
                }),                        
                new KeyFrame(Duration.millis(600), "", e -> {                        
                    el.setLayoutX(305.6);                        
                    el.setLayoutY(288.8);                        
                }),                        
                new KeyFrame(Duration.millis(610), "", e -> {                        
                    el.setLayoutX(302.4);                        
                    el.setLayoutY(285.6);                        
                }),                        
                new KeyFrame(Duration.millis(620), "", e -> {                        
                    el.setLayoutX(300.0);                        
                    el.setLayoutY(281.6);                        
                }),                        
                new KeyFrame(Duration.millis(630), "", e -> {                        
                    el.setLayoutX(297.6);                        
                    el.setLayoutY(278.4);                        
                }),                        
                new KeyFrame(Duration.millis(640), "", e -> {                        
                    el.setLayoutX(295.2);                        
                    el.setLayoutY(275.2);                        
                }),                        
                new KeyFrame(Duration.millis(650), "", e -> {                        
                    el.setLayoutX(294.4);                        
                    el.setLayoutY(273.6);                        
                }),                        
                new KeyFrame(Duration.millis(660), "", e -> {                        
                    el.setLayoutX(291.2);                        
                    el.setLayoutY(270.4);                        
                }),                        
                new KeyFrame(Duration.millis(670), "", e -> {                        
                    el.setLayoutX(288.0);                        
                    el.setLayoutY(265.6);                        
                }),                        
                new KeyFrame(Duration.millis(680), "", e -> {                        
                    el.setLayoutX(285.6);                        
                    el.setLayoutY(261.6);                        
                }),                        
                new KeyFrame(Duration.millis(690), "", e -> {                        
                    el.setLayoutX(283.2);                        
                    el.setLayoutY(258.4);                        
                }),                        
                new KeyFrame(Duration.millis(700), "", e -> {                        
                    el.setLayoutX(280.0);                        
                    el.setLayoutY(253.6);                        
                }),                        
                new KeyFrame(Duration.millis(710), "", e -> {                        
                    el.setLayoutX(278.4);                        
                    el.setLayoutY(251.2);                        
                }),                        
                new KeyFrame(Duration.millis(720), "", e -> {                        
                    el.setLayoutX(275.2);                        
                    el.setLayoutY(248.0);                        
                }),                        
                new KeyFrame(Duration.millis(730), "", e -> {                        
                    el.setLayoutX(273.6);                        
                    el.setLayoutY(244.8);                        
                }),                        
                new KeyFrame(Duration.millis(740), "", e -> {                        
                    el.setLayoutX(272.0);                        
                    el.setLayoutY(241.6);                        
                }),                        
                new KeyFrame(Duration.millis(750), "", e -> {                        
                    el.setLayoutX(270.4);                        
                    el.setLayoutY(236.8);                        
                }),                        
                new KeyFrame(Duration.millis(760), "", e -> {                        
                    el.setLayoutX(269.6);                        
                    el.setLayoutY(233.6);                        
                }),                        
                new KeyFrame(Duration.millis(770), "", e -> {                        
                    el.setLayoutX(268.8);                        
                    el.setLayoutY(229.6);                        
                }),                        
                new KeyFrame(Duration.millis(780), "", e -> {                        
                    el.setLayoutX(268.8);                        
                    el.setLayoutY(227.2);                        
                }),                        
                new KeyFrame(Duration.millis(790), "", e -> {                        
                    el.setLayoutX(268.0);                        
                    el.setLayoutY(223.2);                        
                }),                        
                new KeyFrame(Duration.millis(800), "", e -> {                        
                    el.setLayoutX(268.0);                        
                    el.setLayoutY(220.0);                        
                }),                        
                new KeyFrame(Duration.millis(810), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(216.0);                        
                }),                        
                new KeyFrame(Duration.millis(820), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(213.6);                        
                }),                        
                new KeyFrame(Duration.millis(830), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(208.0);                        
                }),                        
                new KeyFrame(Duration.millis(840), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(205.6);                        
                }),                        
                new KeyFrame(Duration.millis(850), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(203.2);                        
                }),                        
                new KeyFrame(Duration.millis(860), "", e -> {                        
                    el.setLayoutX(266.4);                        
                    el.setLayoutY(200.0);                        
                }),                        
                new KeyFrame(Duration.millis(870), "", e -> {                        
                    el.setLayoutX(267.2);                        
                    el.setLayoutY(197.6);                        
                }),                        
                new KeyFrame(Duration.millis(880), "", e -> {                        
                    el.setLayoutX(268.0);                        
                    el.setLayoutY(194.4);                        
                }),                        
                new KeyFrame(Duration.millis(890), "", e -> {                        
                    el.setLayoutX(270.4);                        
                    el.setLayoutY(190.4);                        
                }),                        
                new KeyFrame(Duration.millis(900), "", e -> {                        
                    el.setLayoutX(274.4);                        
                    el.setLayoutY(184.0);                        
                }),                        
                new KeyFrame(Duration.millis(910), "", e -> {                        
                    el.setLayoutX(278.4);                        
                    el.setLayoutY(176.8);                        
                }),                        
                new KeyFrame(Duration.millis(920), "", e -> {                        
                    el.setLayoutX(283.2);                        
                    el.setLayoutY(169.6);                        
                }),                        
                new KeyFrame(Duration.millis(930), "", e -> {                        
                    el.setLayoutX(288.8);                        
                    el.setLayoutY(163.2);                        
                }),                        
                new KeyFrame(Duration.millis(940), "", e -> {                        
                    el.setLayoutX(293.6);                        
                    el.setLayoutY(156.0);                        
                }),                        
                new KeyFrame(Duration.millis(950), "", e -> {                        
                    el.setLayoutX(300.0);                        
                    el.setLayoutY(148.8);                        
                }),                        
                new KeyFrame(Duration.millis(960), "", e -> {                        
                    el.setLayoutX(308.0);                        
                    el.setLayoutY(142.4);                        
                }),                        
                new KeyFrame(Duration.millis(970), "", e -> {                        
                    el.setLayoutX(315.2);                        
                    el.setLayoutY(135.2);                        
                }),                        
                new KeyFrame(Duration.millis(980), "", e -> {                        
                    el.setLayoutX(324.0);                        
                    el.setLayoutY(127.2);                        
                }),                        
                new KeyFrame(Duration.millis(990), "", e -> {                        
                    el.setLayoutX(333.6);                        
                    el.setLayoutY(120.8);                        
                }),                        
                new KeyFrame(Duration.millis(1000), "", e -> {                        
                    el.setLayoutX(344.8);                        
                    el.setLayoutY(113.6);                        
                }),                        
                new KeyFrame(Duration.millis(1010), "", e -> {                        
                    el.setLayoutX(356.0);                        
                    el.setLayoutY(108.0);                        
                }),                        
                new KeyFrame(Duration.millis(1020), "", e -> {                        
                    el.setLayoutX(368.8);                        
                    el.setLayoutY(102.4);                        
                }),                        
                new KeyFrame(Duration.millis(1030), "", e -> {                        
                    el.setLayoutX(382.4);                        
                    el.setLayoutY(96.8);                        
                }),                        
                new KeyFrame(Duration.millis(1040), "", e -> {                        
                    el.setLayoutX(399.2);                        
                    el.setLayoutY(92.0);                        
                }),                        
                new KeyFrame(Duration.millis(1050), "", e -> {                        
                    el.setLayoutX(416.0);                        
                    el.setLayoutY(86.4);                        
                }),                        
                new KeyFrame(Duration.millis(1060), "", e -> {                        
                    el.setLayoutX(432.8);                        
                    el.setLayoutY(81.6);                        
                }),                        
                new KeyFrame(Duration.millis(1070), "", e -> {                        
                    el.setLayoutX(448.8);                        
                    el.setLayoutY(77.6);                        
                }),                        
                new KeyFrame(Duration.millis(1080), "", e -> {                        
                    el.setLayoutX(467.2);                        
                    el.setLayoutY(73.6);                        
                }),                        
                new KeyFrame(Duration.millis(1090), "", e -> {                        
                    el.setLayoutX(485.6);                        
                    el.setLayoutY(69.6);                        
                }),                        
                new KeyFrame(Duration.millis(1100), "", e -> {                        
                    el.setLayoutX(500.8);                        
                    el.setLayoutY(67.2);                        
                }),                        
                new KeyFrame(Duration.millis(1110), "", e -> {                        
                    el.setLayoutX(516.8);                        
                    el.setLayoutY(64.0);                        
                }),                        
                new KeyFrame(Duration.millis(1120), "", e -> {                        
                    el.setLayoutX(532.0);                        
                    el.setLayoutY(61.6);                        
                }),                        
                new KeyFrame(Duration.millis(1130), "", e -> {                        
                    el.setLayoutX(548.0);                        
                    el.setLayoutY(59.2);                        
                }),                        
                new KeyFrame(Duration.millis(1140), "", e -> {                        
                    el.setLayoutX(563.2);                        
                    el.setLayoutY(56.8);                        
                }),                        
                new KeyFrame(Duration.millis(1150), "", e -> {                        
                    el.setLayoutX(576.0);                        
                    el.setLayoutY(55.2);                        
                }),                        
                new KeyFrame(Duration.millis(1160), "", e -> {                        
                    el.setLayoutX(589.6);                        
                    el.setLayoutY(54.4);                        
                }),                        
                new KeyFrame(Duration.millis(1170), "", e -> {                        
                    el.setLayoutX(600.0);                        
                    el.setLayoutY(53.6);                        
                }),                        
                new KeyFrame(Duration.millis(1180), "", e -> {                        
                    el.setLayoutX(611.2);                        
                    el.setLayoutY(53.6);                        
                }),                        
                new KeyFrame(Duration.millis(1190), "", e -> {                        
                    el.setLayoutX(621.6);                        
                    el.setLayoutY(53.6);                        
                }),                        
                new KeyFrame(Duration.millis(1200), "", e -> {                        
                    el.setLayoutX(632.0);                        
                    el.setLayoutY(53.6);                        
                }),                        
                new KeyFrame(Duration.millis(1210), "", e -> {                        
                    el.setLayoutX(643.2);                        
                    el.setLayoutY(53.6);                        
                }),                        
                new KeyFrame(Duration.millis(1220), "", e -> {                        
                    el.setLayoutX(653.6);                        
                    el.setLayoutY(54.4);                        
                }),                        
                new KeyFrame(Duration.millis(1230), "", e -> {                        
                    el.setLayoutX(665.6);                        
                    el.setLayoutY(56.8);                        
                }),                        
                new KeyFrame(Duration.millis(1240), "", e -> {                        
                    el.setLayoutX(677.6);                        
                    el.setLayoutY(60.0);                        
                }),                        
                new KeyFrame(Duration.millis(1250), "", e -> {                        
                    el.setLayoutX(690.4);                        
                    el.setLayoutY(63.2);                        
                }),                        
                new KeyFrame(Duration.millis(1260), "", e -> {                        
                    el.setLayoutX(702.4);                        
                    el.setLayoutY(68.0);                        
                }),                        
                new KeyFrame(Duration.millis(1270), "", e -> {                        
                    el.setLayoutX(714.4);                        
                    el.setLayoutY(72.8);                        
                }),                        
                new KeyFrame(Duration.millis(1280), "", e -> {                        
                    el.setLayoutX(728.8);                        
                    el.setLayoutY(76.8);                        
                }),                        
                new KeyFrame(Duration.millis(1290), "", e -> {                        
                    el.setLayoutX(742.4);                        
                    el.setLayoutY(81.6);                        
                }),                        
                new KeyFrame(Duration.millis(1300), "", e -> {                        
                    el.setLayoutX(756.0);                        
                    el.setLayoutY(85.6);                        
                }),                        
                new KeyFrame(Duration.millis(1310), "", e -> {                        
                    el.setLayoutX(769.6);                        
                    el.setLayoutY(88.8);                        
                }),                        
                new KeyFrame(Duration.millis(1320), "", e -> {                        
                    el.setLayoutX(782.4);                        
                    el.setLayoutY(92.8);                        
                }),                        
                new KeyFrame(Duration.millis(1330), "", e -> {                        
                    el.setLayoutX(792.0);                        
                    el.setLayoutY(96.0);                        
                }),                        
                new KeyFrame(Duration.millis(1340), "", e -> {                        
                    el.setLayoutX(800.0);                        
                    el.setLayoutY(99.2);                        
                }),                        
                new KeyFrame(Duration.millis(1350), "", e -> {                        
                    el.setLayoutX(807.2);                        
                    el.setLayoutY(102.4);                        
                }),                        
                new KeyFrame(Duration.millis(1360), "", e -> {                        
                    el.setLayoutX(814.4);                        
                    el.setLayoutY(106.4);                        
                }),                        
                new KeyFrame(Duration.millis(1370), "", e -> {                        
                    el.setLayoutX(819.2);                        
                    el.setLayoutY(109.6);                        
                }),                        
                new KeyFrame(Duration.millis(1380), "", e -> {                        
                    el.setLayoutX(824.0);                        
                    el.setLayoutY(112.0);                        
                }),                        
                new KeyFrame(Duration.millis(1390), "", e -> {                        
                    el.setLayoutX(827.2);                        
                    el.setLayoutY(116.0);                        
                }),                        
                new KeyFrame(Duration.millis(1400), "", e -> {                        
                    el.setLayoutX(830.4);                        
                    el.setLayoutY(119.2);                        
                }),                        
                new KeyFrame(Duration.millis(1410), "", e -> {                        
                    el.setLayoutX(832.8);                        
                    el.setLayoutY(123.2);                        
                }),                        
                new KeyFrame(Duration.millis(1420), "", e -> {                        
                    el.setLayoutX(835.2);                        
                    el.setLayoutY(126.4);                        
                }),                        
                new KeyFrame(Duration.millis(1430), "", e -> {                        
                    el.setLayoutX(838.4);                        
                    el.setLayoutY(131.2);                        
                }),                        
                new KeyFrame(Duration.millis(1440), "", e -> {                        
                    el.setLayoutX(840.8);                        
                    el.setLayoutY(136.8);                        
                }),                        
                new KeyFrame(Duration.millis(1450), "", e -> {                        
                    el.setLayoutX(844.0);                        
                    el.setLayoutY(142.4);                        
                }),                        
                new KeyFrame(Duration.millis(1460), "", e -> {                        
                    el.setLayoutX(847.2);                        
                    el.setLayoutY(149.6);                        
                }),                        
                new KeyFrame(Duration.millis(1470), "", e -> {                        
                    el.setLayoutX(848.8);                        
                    el.setLayoutY(156.8);                        
                }),                        
                new KeyFrame(Duration.millis(1480), "", e -> {                        
                    el.setLayoutX(851.2);                        
                    el.setLayoutY(165.6);                        
                }),                        
                new KeyFrame(Duration.millis(1490), "", e -> {                        
                    el.setLayoutX(852.0);                        
                    el.setLayoutY(173.6);                        
                }),                        
                new KeyFrame(Duration.millis(1500), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(181.6);                        
                }),                        
                new KeyFrame(Duration.millis(1510), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(192.0);                        
                }),                        
                new KeyFrame(Duration.millis(1520), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(201.6);                        
                }),                        
                new KeyFrame(Duration.millis(1530), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(211.2);                        
                }),                        
                new KeyFrame(Duration.millis(1540), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(219.2);                        
                }),                        
                new KeyFrame(Duration.millis(1550), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(227.2);                        
                }),                        
                new KeyFrame(Duration.millis(1560), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(233.6);                        
                }),                        
                new KeyFrame(Duration.millis(1570), "", e -> {                        
                    el.setLayoutX(852.8);                        
                    el.setLayoutY(239.2);                        
                }),                        
                new KeyFrame(Duration.millis(1580), "", e -> {                        
                    el.setLayoutX(850.4);                        
                    el.setLayoutY(244.8);                        
                }),                        
                new KeyFrame(Duration.millis(1590), "", e -> {                        
                    el.setLayoutX(846.4);                        
                    el.setLayoutY(249.6);                        
                }),                        
                new KeyFrame(Duration.millis(1600), "", e -> {                        
                    el.setLayoutX(841.6);                        
                    el.setLayoutY(255.2);                        
                }),                        
                new KeyFrame(Duration.millis(1610), "", e -> {                        
                    el.setLayoutX(834.4);                        
                    el.setLayoutY(259.2);                        
                }),                        
                new KeyFrame(Duration.millis(1620), "", e -> {                        
                    el.setLayoutX(827.2);                        
                    el.setLayoutY(263.2);                        
                }),                        
                new KeyFrame(Duration.millis(1630), "", e -> {                        
                    el.setLayoutX(818.4);                        
                    el.setLayoutY(266.4);                        
                }),                        
                new KeyFrame(Duration.millis(1640), "", e -> {                        
                    el.setLayoutX(810.4);                        
                    el.setLayoutY(268.0);                        
                }),                        
                new KeyFrame(Duration.millis(1650), "", e -> {                        
                    el.setLayoutX(800.8);                        
                    el.setLayoutY(270.4);                        
                }),                        
                new KeyFrame(Duration.millis(1660), "", e -> {                        
                    el.setLayoutX(791.2);                        
                    el.setLayoutY(272.8);                        
                }),                        
                new KeyFrame(Duration.millis(1670), "", e -> {                        
                    el.setLayoutX(781.6);                        
                    el.setLayoutY(274.4);                        
                }),                        
                new KeyFrame(Duration.millis(1680), "", e -> {                        
                    el.setLayoutX(770.4);                        
                    el.setLayoutY(276.8);                        
                }),                        
                new KeyFrame(Duration.millis(1690), "", e -> {                        
                    el.setLayoutX(760.0);                        
                    el.setLayoutY(279.2);                        
                }),                        
                new KeyFrame(Duration.millis(1700), "", e -> {                        
                    el.setLayoutX(748.8);                        
                    el.setLayoutY(281.6);                        
                }),                        
                new KeyFrame(Duration.millis(1710), "", e -> {                        
                    el.setLayoutX(736.8);                        
                    el.setLayoutY(283.2);                        
                }),                        
                new KeyFrame(Duration.millis(1720), "", e -> {                        
                    el.setLayoutX(723.2);                        
                    el.setLayoutY(284.8);                        
                }),                        
                new KeyFrame(Duration.millis(1730), "", e -> {                        
                    el.setLayoutX(708.8);                        
                    el.setLayoutY(285.6);                        
                }),                        
                new KeyFrame(Duration.millis(1740), "", e -> {                        
                    el.setLayoutX(690.4);                        
                    el.setLayoutY(287.2);                        
                }),                        
                new KeyFrame(Duration.millis(1750), "", e -> {                        
                    el.setLayoutX(664.8);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1760), "", e -> {                        
                    el.setLayoutX(637.6);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1770), "", e -> {                        
                    el.setLayoutX(610.4);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1780), "", e -> {                        
                    el.setLayoutX(580.8);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1790), "", e -> {                        
                    el.setLayoutX(553.6);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1800), "", e -> {                        
                    el.setLayoutX(526.4);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1810), "", e -> {                        
                    el.setLayoutX(499.2);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1820), "", e -> {                        
                    el.setLayoutX(474.4);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1830), "", e -> {                        
                    el.setLayoutX(449.6);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1840), "", e -> {                        
                    el.setLayoutX(425.6);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1850), "", e -> {                        
                    el.setLayoutX(403.2);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1860), "", e -> {                        
                    el.setLayoutX(381.6);                        
                    el.setLayoutY(288.0);                        
                }),                        
                new KeyFrame(Duration.millis(1870), "", e -> {                        
                    el.setLayoutX(364.0);                        
                    el.setLayoutY(289.6);                        
                }),                        
                new KeyFrame(Duration.millis(1880), "", e -> {                        
                    el.setLayoutX(349.6);                        
                    el.setLayoutY(290.4);                        
                }),                        
                new KeyFrame(Duration.millis(1890), "", e -> {                        
                    el.setLayoutX(336.0);                        
                    el.setLayoutY(292.8);                        
                }),                        
                new KeyFrame(Duration.millis(1900), "", e -> {                        
                    el.setLayoutX(324.0);                        
                    el.setLayoutY(295.2);                        
                }),                        
                new KeyFrame(Duration.millis(1910), "", e -> {                        
                    el.setLayoutX(313.6);                        
                    el.setLayoutY(298.4);                        
                }),                        
                new KeyFrame(Duration.millis(1920), "", e -> {                        
                    el.setLayoutX(303.2);                        
                    el.setLayoutY(302.4);                        
                }),                        
                new KeyFrame(Duration.millis(1930), "", e -> {                        
                    el.setLayoutX(295.2);                        
                    el.setLayoutY(307.2);                        
                }),                        
                new KeyFrame(Duration.millis(1940), "", e -> {                        
                    el.setLayoutX(288.0);                        
                    el.setLayoutY(312.0);                        
                }),                        
                new KeyFrame(Duration.millis(1950), "", e -> {                        
                    el.setLayoutX(280.0);                        
                    el.setLayoutY(318.4);                        
                }),                        
                new KeyFrame(Duration.millis(1960), "", e -> {                        
                    el.setLayoutX(275.2);                        
                    el.setLayoutY(326.4);                        
                }),                        
                new KeyFrame(Duration.millis(1970), "", e -> {                        
                    el.setLayoutX(269.6);                        
                    el.setLayoutY(334.4);                        
                }),                        
                new KeyFrame(Duration.millis(1980), "", e -> {                        
                    el.setLayoutX(265.6);                        
                    el.setLayoutY(344.0);                        
                }),                        
                new KeyFrame(Duration.millis(1990), "", e -> {                        
                    el.setLayoutX(260.8);                        
                    el.setLayoutY(355.2);                        
                }),                        
                new KeyFrame(Duration.millis(2000), "", e -> {                        
                    el.setLayoutX(256.8);                        
                    el.setLayoutY(366.4);                        
                }),                        
                new KeyFrame(Duration.millis(2010), "", e -> {                        
                    el.setLayoutX(252.8);                        
                    el.setLayoutY(378.4);                        
                }),                        
                new KeyFrame(Duration.millis(2020), "", e -> {                        
                    el.setLayoutX(251.2);                        
                    el.setLayoutY(392.0);                        
                }),                        
                new KeyFrame(Duration.millis(2030), "", e -> {                        
                    el.setLayoutX(248.8);                        
                    el.setLayoutY(408.8);                        
                }),                        
                new KeyFrame(Duration.millis(2040), "", e -> {                        
                    el.setLayoutX(245.6);                        
                    el.setLayoutY(429.6);                        
                }),                        
                new KeyFrame(Duration.millis(2050), "", e -> {                        
                    el.setLayoutX(244.0);                        
                    el.setLayoutY(449.6);                        
                }),                        
                new KeyFrame(Duration.millis(2060), "", e -> {                        
                    el.setLayoutX(244.0);                        
                    el.setLayoutY(468.8);                        
                }),                        
                new KeyFrame(Duration.millis(2070), "", e -> {                        
                    el.setLayoutX(244.0);                        
                    el.setLayoutY(488.0);                        
                }),                        
                new KeyFrame(Duration.millis(2080), "", e -> {                        
                    el.setLayoutX(246.4);                        
                    el.setLayoutY(511.2);                        
                }),                        
                new KeyFrame(Duration.millis(2090), "", e -> {                        
                    el.setLayoutX(251.2);                        
                    el.setLayoutY(534.4);                        
                }),                        
                new KeyFrame(Duration.millis(2100), "", e -> {                        
                    el.setLayoutX(259.2);                        
                    el.setLayoutY(554.4);                        
                }),                        
                new KeyFrame(Duration.millis(2110), "", e -> {                        
                    el.setLayoutX(271.2);                        
                    el.setLayoutY(573.6);                        
                }),                        
                new KeyFrame(Duration.millis(2120), "", e -> {                        
                    el.setLayoutX(284.0);                        
                    el.setLayoutY(586.4);                        
                }),                        
                new KeyFrame(Duration.millis(2130), "", e -> {                        
                    el.setLayoutX(300.0);                        
                    el.setLayoutY(597.6);                        
                }));                        
        tm.setCycleCount(rep);                        
        tm.play();                        
    }                        
}                        
