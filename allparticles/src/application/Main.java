package application; 
 
import application.*; 
 
import javafx.application.*; 
import javafx.geometry.Insets; 
import javafx.util.Duration; 
import javafx.animation.Animation; 
import javafx.animation.KeyFrame; 
import javafx.animation.Timeline; 
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
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.StackedAreaChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart; 
import javafx.scene.chart.XYChart.Data; 
 
import java.io.BufferedReader; 
import java.io.File; 
import java.io.FileReader; 
import java.io.FileWriter; 
import java.io.IOException; 
 
import java.time.LocalDateTime; 
import java.util.ArrayList; 
import java.util.Arrays; 
import java.util.List; 
import java.util.Random; 
import java.util.concurrent.Executors; 
import java.util.concurrent.ScheduledExecutorService; 
import java.util.concurrent.TimeUnit; 
import java.util.concurrent.atomic.AtomicInteger; 
import java.util.concurrent.atomic.AtomicBoolean; 
import java.util.concurrent.atomic.AtomicReference; 
 
public class Main extends Application { 
    public static void main(String[] args) { 
        launch(args); 
    } 
 
    @Override 
    public void start(Stage stg) { 
        stg.setTitle("Allparticles desktop utility"); 
        BorderPane rt = new BorderPane(); 
        Pane field = new Pane(); 
        MenuBar bar = new MenuBar(); 
        Menu[] menus = { new Menu("New..."), new Menu("Entertainment"), new Menu("Field") }; 
        MenuItem[] newits = { new MenuItem("Element"), new MenuItem("Fermion"), new MenuItem("Lepton") }; 
        MenuItem[] enits = { new MenuItem("Add atoms") }; 
        MenuItem[] fieits = { new MenuItem("Clear field"), new MenuItem("View the Atomic-mass Increment Chart (AIC)"), 
                new MenuItem("View the Boiling/Melting point Chart (AIC)") }; 
        menus[0].getItems().addAll(newits); 
        menus[1].getItems().addAll(enits); 
        menus[2].getItems().addAll(fieits); 
        bar.getMenus().addAll(menus); 
 
        Circle cr = new Circle(20, 20, 20); 
 
        Scene sc = new Scene(rt, 1200, 600); 
        field.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, new CornerRadii(2), null))); 
        application.Methods m = new application.Methods(); 
        application.Movements mv = new application.Movements(); 
        List<List<String>> allparlists = m.getAllElementData(); 
        List<String> sym = allparlists.get(2); 
        List<Label> atoms = new ArrayList<>(); 
        List<LineChart<Double, Double>> lchts = new ArrayList<>(); 
        Random rd = new Random(); 
        enits[0].setOnAction(e -> { 
            for (int j = 0; j < Integer.parseInt(m.singleInputPane("How many atoms would you like to add?", 
                    atoms.size() - 1 + "")); j++) { 
                Label el = new Label(sym.get(rd.nextInt(0, sym.size() - 1))); 
                el.setBackground(new Background(new BackgroundFill(Color.LEMONCHIFFON, null, null))); 
                el.setLayoutX(rd.nextDouble(0, 1200)); 
                el.setLayoutY(rd.nextDouble(0, 600)); 
                el.setShape(cr); 
                atoms.add(el); 
                field.getChildren().add(el); 
                int z = rd.nextInt(0, 5); 
 
                if (z == 0) 
                    randomMv(el, 12); 
                if (z == 1) 
                    randomMv2(el, 12); 
                if (z == 2) 
                    randomMv3(el); 
                if (z == 3) 
                    randomMv4(el); 
                if (z == 4) 
                    laceMv(el, 12); 
                if (z == 5) 
                    randomMv5(el); 
 
            } 
        }); 
        fieits[0].setOnAction(e -> { 
            for (Label l : atoms) 
                field.getChildren().remove(l); 
            for (LineChart<Double, Double> lc : lchts) 
                field.getChildren().remove(lc); 
        }); 
        fieits[1].setOnAction(e -> { 
            LineChart<Double, Double> lc = showAtIncChart(); 
            lc.setPrefWidth(sc.getWidth() - 80); 
            lc.setPrefHeight(sc.getHeight() - 100); 
            field.getChildren().add(lc); 
        }); 
        fieits[2].setOnAction(e -> { 
            StackedAreaChart<Double, Double> sct = showMeltBoilChart(); 
            sct.setPrefWidth(sc.getWidth() - 80); 
            sct.setPrefHeight(sc.getHeight() - 100); 
            field.getChildren().add(sct); 
        }); 
        AtomicInteger ai = new AtomicInteger(0); 
        sc.setOnMouseMoved(e -> { 
            try { 
                FileWriter writer = new FileWriter("s.java", true); 
 
                writer.write("new KeyFrame(Duration.millis(" + ai.getAndAdd(100) + "), \"\", e->{ " + 
                        "el.setLayoutX(" + e.getSceneX() + ");el.setLayoutY(" + e.getSceneY() + " );})," 
                        + " \n"); 
                writer.close(); 
 
            } catch (IOException e3) { 
            } 
        }); 
        rt.setCenter(field); 
        rt.setTop(bar); 
 
        stg.setScene(sc); 
        stg.show(); 
 
    } 
 
    LineChart<Double, Double> showAtIncChart() { 
        application.Methods m = new application.Methods(); 
 
        List<List<String>> allparlists = m.getAllElementData(); 
        List<String> sym = allparlists.get(1); 
        NumberAxis x = new NumberAxis(); 
        NumberAxis y = new NumberAxis(); 
 
        x.setLabel("Atomic number"); 
        y.setLabel("Increment"); 
 
        LineChart<Double, Double> lc = new LineChart(x, y); 
        lc.setAnimated(false); 
        lc.setCreateSymbols(false); 
        XYChart.Series<Double, Double> ser = new XYChart.Series<Double, Double>(); 
        for (int j = 1; j < sym.size() - 1; j++) { 
            double pval = Double.parseDouble(sym.get(j - 1)); 
            double val = Double.parseDouble(sym.get(j)); 
            ser.getData().add(new XYChart.Data(j, val - pval)); 
        } 
        ser.setName("Increment in atomic mass for each element"); 
        lc.getData().add(ser); 
        return lc; 
 
    } 
 
    StackedAreaChart<Double, Double> showMeltBoilChart() { 
        application.Methods m = new application.Methods(); 
 
        List<List<String>> allparlists = m.getAllElementData(); 
        List<String> mp = allparlists.get(3); 
        List<String> bp = allparlists.get(4); 
        NumberAxis x = new NumberAxis(); 
        NumberAxis y = new NumberAxis(); 
 
        x.setLabel("Atomic number"); 
        y.setLabel("Melting, boiling point"); 
 
        StackedAreaChart<Double, Double> lc = new StackedAreaChart(x, y); 
        lc.setAnimated(false); 
        lc.setCreateSymbols(false); 
 
        XYChart.Series<Double, Double> ser = new XYChart.Series<Double, Double>(); 
        for (int j = 0; j < mp.size() - 1; j++) { 
 
            double val = Double.parseDouble(mp.get(j)); 
            ser.getData().add(new XYChart.Data(j, val)); 
        } 
        ser.setName("Melting Point for each element (°K)"); 
        XYChart.Series<Double, Double> ser2 = new XYChart.Series<Double, Double>(); 
        for (int j = 0; j < bp.size() - 1; j++) { 
 
            double val = Double.parseDouble(bp.get(j)); 
            ser2.getData().add(new XYChart.Data(j, val)); 
        } 
        ser2.setName("Boiling Point for each element (°K)"); 
        lc.getData().addAll(ser, ser2); 
        return lc; 
 
    } 
 
    public void randomMv(Label el, int rep) { 
        Timeline tm = new Timeline( 
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
 
    public void laceMv(Label el, int rep) { 
        Timeline tm = new Timeline( 
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
 
    public void randomMv2(Label el, int rep) { 
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(46800), "", e -> { 
            el.setLayoutX(0.0); 
            el.setLayoutY(504.0); 
        }), 
                new KeyFrame(Duration.millis(46900), "", e -> { 
                    el.setLayoutX(8.0); 
                    el.setLayoutY(502.0); 
                }), 
                new KeyFrame(Duration.millis(47000), "", e -> { 
                    el.setLayoutX(17.0); 
                    el.setLayoutY(499.0); 
                }), 
                new KeyFrame(Duration.millis(47100), "", e -> { 
                    el.setLayoutX(27.0); 
                    el.setLayoutY(497.0); 
                }), 
                new KeyFrame(Duration.millis(47200), "", e -> { 
                    el.setLayoutX(37.0); 
                    el.setLayoutY(495.0); 
                }), 
                new KeyFrame(Duration.millis(47300), "", e -> { 
                    el.setLayoutX(46.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47400), "", e -> { 
                    el.setLayoutX(57.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47500), "", e -> { 
                    el.setLayoutX(66.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47600), "", e -> { 
                    el.setLayoutX(75.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47700), "", e -> { 
                    el.setLayoutX(86.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47800), "", e -> { 
                    el.setLayoutX(97.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(47900), "", e -> { 
                    el.setLayoutX(108.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48000), "", e -> { 
                    el.setLayoutX(120.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48100), "", e -> { 
                    el.setLayoutX(132.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48200), "", e -> { 
                    el.setLayoutX(146.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48300), "", e -> { 
                    el.setLayoutX(159.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48400), "", e -> { 
                    el.setLayoutX(171.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48500), "", e -> { 
                    el.setLayoutX(183.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48600), "", e -> { 
                    el.setLayoutX(195.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48700), "", e -> { 
                    el.setLayoutX(208.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48800), "", e -> { 
                    el.setLayoutX(221.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(48900), "", e -> { 
                    el.setLayoutX(234.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(49000), "", e -> { 
                    el.setLayoutX(248.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(49100), "", e -> { 
                    el.setLayoutX(261.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(49200), "", e -> { 
                    el.setLayoutX(274.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(49300), "", e -> { 
                    el.setLayoutX(291.0); 
                    el.setLayoutY(491.0); 
                }), 
                new KeyFrame(Duration.millis(49400), "", e -> { 
                    el.setLayoutX(305.0); 
                    el.setLayoutY(490.0); 
                }), 
                new KeyFrame(Duration.millis(49500), "", e -> { 
                    el.setLayoutX(322.0); 
                    el.setLayoutY(489.0); 
                }), 
                new KeyFrame(Duration.millis(49600), "", e -> { 
                    el.setLayoutX(339.0); 
                    el.setLayoutY(488.0); 
                }), 
                new KeyFrame(Duration.millis(49700), "", e -> { 
                    el.setLayoutX(356.0); 
                    el.setLayoutY(487.0); 
                }), 
                new KeyFrame(Duration.millis(49800), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(485.0); 
                }), 
                new KeyFrame(Duration.millis(49900), "", e -> { 
                    el.setLayoutX(393.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50000), "", e -> { 
                    el.setLayoutX(412.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50100), "", e -> { 
                    el.setLayoutX(432.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50200), "", e -> { 
                    el.setLayoutX(451.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50300), "", e -> { 
                    el.setLayoutX(467.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50400), "", e -> { 
                    el.setLayoutX(487.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50500), "", e -> { 
                    el.setLayoutX(508.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50600), "", e -> { 
                    el.setLayoutX(533.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50700), "", e -> { 
                    el.setLayoutX(555.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50800), "", e -> { 
                    el.setLayoutX(579.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(50900), "", e -> { 
                    el.setLayoutX(609.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51000), "", e -> { 
                    el.setLayoutX(639.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51100), "", e -> { 
                    el.setLayoutX(667.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51200), "", e -> { 
                    el.setLayoutX(691.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51300), "", e -> { 
                    el.setLayoutX(716.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51400), "", e -> { 
                    el.setLayoutX(738.0); 
                    el.setLayoutY(484.0); 
                }), 
                new KeyFrame(Duration.millis(51500), "", e -> { 
                    el.setLayoutX(760.0); 
                    el.setLayoutY(483.0); 
                }), 
                new KeyFrame(Duration.millis(51600), "", e -> { 
                    el.setLayoutX(780.0); 
                    el.setLayoutY(481.0); 
                }), 
                new KeyFrame(Duration.millis(51700), "", e -> { 
                    el.setLayoutX(798.0); 
                    el.setLayoutY(479.0); 
                }), 
                new KeyFrame(Duration.millis(51800), "", e -> { 
                    el.setLayoutX(816.0); 
                    el.setLayoutY(476.0); 
                }), 
                new KeyFrame(Duration.millis(51900), "", e -> { 
                    el.setLayoutX(831.0); 
                    el.setLayoutY(474.0); 
                }), 
                new KeyFrame(Duration.millis(52000), "", e -> { 
                    el.setLayoutX(845.0); 
                    el.setLayoutY(472.0); 
                }), 
                new KeyFrame(Duration.millis(52100), "", e -> { 
                    el.setLayoutX(857.0); 
                    el.setLayoutY(468.0); 
                }), 
                new KeyFrame(Duration.millis(52200), "", e -> { 
                    el.setLayoutX(868.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(52300), "", e -> { 
                    el.setLayoutX(879.0); 
                    el.setLayoutY(462.0); 
                }), 
                new KeyFrame(Duration.millis(52400), "", e -> { 
                    el.setLayoutX(887.0); 
                    el.setLayoutY(459.0); 
                }), 
                new KeyFrame(Duration.millis(52500), "", e -> { 
                    el.setLayoutX(895.0); 
                    el.setLayoutY(456.0); 
                }), 
                new KeyFrame(Duration.millis(52600), "", e -> { 
                    el.setLayoutX(904.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(52700), "", e -> { 
                    el.setLayoutX(911.0); 
                    el.setLayoutY(449.0); 
                }), 
                new KeyFrame(Duration.millis(52800), "", e -> { 
                    el.setLayoutX(917.0); 
                    el.setLayoutY(446.0); 
                }), 
                new KeyFrame(Duration.millis(52900), "", e -> { 
                    el.setLayoutX(922.0); 
                    el.setLayoutY(444.0); 
                }), 
                new KeyFrame(Duration.millis(53000), "", e -> { 
                    el.setLayoutX(926.0); 
                    el.setLayoutY(441.0); 
                }), 
                new KeyFrame(Duration.millis(53100), "", e -> { 
                    el.setLayoutX(928.0); 
                    el.setLayoutY(439.0); 
                }), 
                new KeyFrame(Duration.millis(53200), "", e -> { 
                    el.setLayoutX(931.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(53300), "", e -> { 
                    el.setLayoutX(933.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(53400), "", e -> { 
                    el.setLayoutX(938.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(53500), "", e -> { 
                    el.setLayoutX(941.0); 
                    el.setLayoutY(430.0); 
                }), 
                new KeyFrame(Duration.millis(53600), "", e -> { 
                    el.setLayoutX(944.0); 
                    el.setLayoutY(428.0); 
                }), 
                new KeyFrame(Duration.millis(53700), "", e -> { 
                    el.setLayoutX(946.0); 
                    el.setLayoutY(427.0); 
                }), 
                new KeyFrame(Duration.millis(53800), "", e -> { 
                    el.setLayoutX(949.0); 
                    el.setLayoutY(425.0); 
                }), 
                new KeyFrame(Duration.millis(53900), "", e -> { 
                    el.setLayoutX(954.0); 
                    el.setLayoutY(421.0); 
                }), 
                new KeyFrame(Duration.millis(54000), "", e -> { 
                    el.setLayoutX(959.0); 
                    el.setLayoutY(419.0); 
                }), 
                new KeyFrame(Duration.millis(54100), "", e -> { 
                    el.setLayoutX(960.0); 
                    el.setLayoutY(417.0); 
                }), 
                new KeyFrame(Duration.millis(54200), "", e -> { 
                    el.setLayoutX(963.0); 
                    el.setLayoutY(417.0); 
                }), 
                new KeyFrame(Duration.millis(54300), "", e -> { 
                    el.setLayoutX(967.0); 
                    el.setLayoutY(416.0); 
                }), 
                new KeyFrame(Duration.millis(54400), "", e -> { 
                    el.setLayoutX(968.0); 
                    el.setLayoutY(415.0); 
                }), 
                new KeyFrame(Duration.millis(54500), "", e -> { 
                    el.setLayoutX(969.0); 
                    el.setLayoutY(414.0); 
                }), 
                new KeyFrame(Duration.millis(54600), "", e -> { 
                    el.setLayoutX(972.0); 
                    el.setLayoutY(412.0); 
                }), 
                new KeyFrame(Duration.millis(54700), "", e -> { 
                    el.setLayoutX(974.0); 
                    el.setLayoutY(411.0); 
                }), 
                new KeyFrame(Duration.millis(54800), "", e -> { 
                    el.setLayoutX(976.0); 
                    el.setLayoutY(410.0); 
                }), 
                new KeyFrame(Duration.millis(54900), "", e -> { 
                    el.setLayoutX(980.0); 
                    el.setLayoutY(410.0); 
                }), 
                new KeyFrame(Duration.millis(55000), "", e -> { 
                    el.setLayoutX(981.0); 
                    el.setLayoutY(409.0); 
                }), 
                new KeyFrame(Duration.millis(55100), "", e -> { 
                    el.setLayoutX(982.0); 
                    el.setLayoutY(409.0); 
                }), 
                new KeyFrame(Duration.millis(55200), "", e -> { 
                    el.setLayoutX(983.0); 
                    el.setLayoutY(408.0); 
                }), 
                new KeyFrame(Duration.millis(55300), "", e -> { 
                    el.setLayoutX(985.0); 
                    el.setLayoutY(406.0); 
                }), 
                new KeyFrame(Duration.millis(55400), "", e -> { 
                    el.setLayoutX(986.0); 
                    el.setLayoutY(404.0); 
                }), 
                new KeyFrame(Duration.millis(55500), "", e -> { 
                    el.setLayoutX(988.0); 
                    el.setLayoutY(402.0); 
                }), 
                new KeyFrame(Duration.millis(55600), "", e -> { 
                    el.setLayoutX(991.0); 
                    el.setLayoutY(399.0); 
                }), 
                new KeyFrame(Duration.millis(55700), "", e -> { 
                    el.setLayoutX(994.0); 
                    el.setLayoutY(396.0); 
                }), 
                new KeyFrame(Duration.millis(55800), "", e -> { 
                    el.setLayoutX(996.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(55900), "", e -> { 
                    el.setLayoutX(997.0); 
                    el.setLayoutY(393.0); 
                }), 
                new KeyFrame(Duration.millis(56000), "", e -> { 
                    el.setLayoutX(998.0); 
                    el.setLayoutY(390.0); 
                }), 
                new KeyFrame(Duration.millis(56100), "", e -> { 
                    el.setLayoutX(999.0); 
                    el.setLayoutY(390.0); 
                }), 
                new KeyFrame(Duration.millis(56200), "", e -> { 
                    el.setLayoutX(1000.0); 
                    el.setLayoutY(388.0); 
                }), 
                new KeyFrame(Duration.millis(56300), "", e -> { 
                    el.setLayoutX(1001.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(56400), "", e -> { 
                    el.setLayoutX(1001.0); 
                    el.setLayoutY(385.0); 
                }), 
                new KeyFrame(Duration.millis(56500), "", e -> { 
                    el.setLayoutX(1003.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(56600), "", e -> { 
                    el.setLayoutX(1004.0); 
                    el.setLayoutY(382.0); 
                }), 
                new KeyFrame(Duration.millis(56700), "", e -> { 
                    el.setLayoutX(1004.0); 
                    el.setLayoutY(379.0); 
                }), 
                new KeyFrame(Duration.millis(56800), "", e -> { 
                    el.setLayoutX(1004.0); 
                    el.setLayoutY(378.0); 
                }), 
                new KeyFrame(Duration.millis(56900), "", e -> { 
                    el.setLayoutX(1006.0); 
                    el.setLayoutY(376.0); 
                }), 
                new KeyFrame(Duration.millis(57000), "", e -> { 
                    el.setLayoutX(1007.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(57100), "", e -> { 
                    el.setLayoutX(1007.0); 
                    el.setLayoutY(370.0); 
                }), 
                new KeyFrame(Duration.millis(57200), "", e -> { 
                    el.setLayoutX(1009.0); 
                    el.setLayoutY(365.0); 
                }), 
                new KeyFrame(Duration.millis(57300), "", e -> { 
                    el.setLayoutX(1010.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(57400), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(361.0); 
                }), 
                new KeyFrame(Duration.millis(57500), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(57600), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(358.0); 
                }), 
                new KeyFrame(Duration.millis(57700), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(357.0); 
                }), 
                new KeyFrame(Duration.millis(57800), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(57900), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(58000), "", e -> { 
                    el.setLayoutX(1011.0); 
                    el.setLayoutY(354.0); 
                }), 
                new KeyFrame(Duration.millis(58100), "", e -> { 
                    el.setLayoutX(1010.0); 
                    el.setLayoutY(354.0); 
                }), 
                new KeyFrame(Duration.millis(58200), "", e -> { 
                    el.setLayoutX(1010.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(58300), "", e -> { 
                    el.setLayoutX(1009.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(58400), "", e -> { 
                    el.setLayoutX(1008.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(58500), "", e -> { 
                    el.setLayoutX(1007.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(58600), "", e -> { 
                    el.setLayoutX(1006.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(58700), "", e -> { 
                    el.setLayoutX(1005.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(58800), "", e -> { 
                    el.setLayoutX(1002.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(58900), "", e -> { 
                    el.setLayoutX(1000.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(59000), "", e -> { 
                    el.setLayoutX(998.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(59100), "", e -> { 
                    el.setLayoutX(995.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(59200), "", e -> { 
                    el.setLayoutX(991.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59300), "", e -> { 
                    el.setLayoutX(988.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59400), "", e -> { 
                    el.setLayoutX(986.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59500), "", e -> { 
                    el.setLayoutX(983.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59600), "", e -> { 
                    el.setLayoutX(980.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59700), "", e -> { 
                    el.setLayoutX(977.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59800), "", e -> { 
                    el.setLayoutX(974.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(59900), "", e -> { 
                    el.setLayoutX(971.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60000), "", e -> { 
                    el.setLayoutX(967.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60100), "", e -> { 
                    el.setLayoutX(963.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60200), "", e -> { 
                    el.setLayoutX(959.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60300), "", e -> { 
                    el.setLayoutX(957.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60400), "", e -> { 
                    el.setLayoutX(954.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60500), "", e -> { 
                    el.setLayoutX(950.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60600), "", e -> { 
                    el.setLayoutX(948.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60700), "", e -> { 
                    el.setLayoutX(945.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60800), "", e -> { 
                    el.setLayoutX(942.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(60900), "", e -> { 
                    el.setLayoutX(938.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61000), "", e -> { 
                    el.setLayoutX(935.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61100), "", e -> { 
                    el.setLayoutX(931.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61200), "", e -> { 
                    el.setLayoutX(926.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61300), "", e -> { 
                    el.setLayoutX(920.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61400), "", e -> { 
                    el.setLayoutX(918.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61500), "", e -> { 
                    el.setLayoutX(914.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61600), "", e -> { 
                    el.setLayoutX(912.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61700), "", e -> { 
                    el.setLayoutX(907.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61800), "", e -> { 
                    el.setLayoutX(900.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(61900), "", e -> { 
                    el.setLayoutX(893.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62000), "", e -> { 
                    el.setLayoutX(885.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62100), "", e -> { 
                    el.setLayoutX(877.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62200), "", e -> { 
                    el.setLayoutX(869.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62300), "", e -> { 
                    el.setLayoutX(860.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62400), "", e -> { 
                    el.setLayoutX(852.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62500), "", e -> { 
                    el.setLayoutX(844.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(62600), "", e -> { 
                    el.setLayoutX(836.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(62700), "", e -> { 
                    el.setLayoutX(826.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(62800), "", e -> { 
                    el.setLayoutX(817.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(62900), "", e -> { 
                    el.setLayoutX(807.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63000), "", e -> { 
                    el.setLayoutX(798.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63100), "", e -> { 
                    el.setLayoutX(788.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63200), "", e -> { 
                    el.setLayoutX(777.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63300), "", e -> { 
                    el.setLayoutX(766.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63400), "", e -> { 
                    el.setLayoutX(756.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63500), "", e -> { 
                    el.setLayoutX(745.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63600), "", e -> { 
                    el.setLayoutX(733.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63700), "", e -> { 
                    el.setLayoutX(719.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63800), "", e -> { 
                    el.setLayoutX(702.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(63900), "", e -> { 
                    el.setLayoutX(683.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(64000), "", e -> { 
                    el.setLayoutX(664.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(64100), "", e -> { 
                    el.setLayoutX(642.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(64200), "", e -> { 
                    el.setLayoutX(617.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(64300), "", e -> { 
                    el.setLayoutX(588.0); 
                    el.setLayoutY(345.0); 
                }), 
                new KeyFrame(Duration.millis(64400), "", e -> { 
                    el.setLayoutX(556.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(64500), "", e -> { 
                    el.setLayoutX(522.0); 
                    el.setLayoutY(332.0); 
                }), 
                new KeyFrame(Duration.millis(64600), "", e -> { 
                    el.setLayoutX(487.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(64700), "", e -> { 
                    el.setLayoutX(449.0); 
                    el.setLayoutY(314.0); 
                }), 
                new KeyFrame(Duration.millis(64800), "", e -> { 
                    el.setLayoutX(414.0); 
                    el.setLayoutY(305.0); 
                }), 
                new KeyFrame(Duration.millis(64900), "", e -> { 
                    el.setLayoutX(382.0); 
                    el.setLayoutY(299.0); 
                }), 
                new KeyFrame(Duration.millis(65000), "", e -> { 
                    el.setLayoutX(355.0); 
                    el.setLayoutY(292.0); 
                }), 
                new KeyFrame(Duration.millis(65100), "", e -> { 
                    el.setLayoutX(331.0); 
                    el.setLayoutY(286.0); 
                }), 
                new KeyFrame(Duration.millis(65200), "", e -> { 
                    el.setLayoutX(311.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(65300), "", e -> { 
                    el.setLayoutX(295.0); 
                    el.setLayoutY(274.0); 
                }), 
                new KeyFrame(Duration.millis(65400), "", e -> { 
                    el.setLayoutX(278.0); 
                    el.setLayoutY(267.0); 
                }), 
                new KeyFrame(Duration.millis(65500), "", e -> { 
                    el.setLayoutX(265.0); 
                    el.setLayoutY(262.0); 
                }), 
                new KeyFrame(Duration.millis(65600), "", e -> { 
                    el.setLayoutX(253.0); 
                    el.setLayoutY(256.0); 
                }), 
                new KeyFrame(Duration.millis(65700), "", e -> { 
                    el.setLayoutX(243.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(65800), "", e -> { 
                    el.setLayoutX(234.0); 
                    el.setLayoutY(245.0); 
                }), 
                new KeyFrame(Duration.millis(65900), "", e -> { 
                    el.setLayoutX(227.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(66000), "", e -> { 
                    el.setLayoutX(221.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(66100), "", e -> { 
                    el.setLayoutX(216.0); 
                    el.setLayoutY(230.0); 
                }), 
                new KeyFrame(Duration.millis(66200), "", e -> { 
                    el.setLayoutX(212.0); 
                    el.setLayoutY(225.0); 
                }), 
                new KeyFrame(Duration.millis(66300), "", e -> { 
                    el.setLayoutX(208.0); 
                    el.setLayoutY(219.0); 
                }), 
                new KeyFrame(Duration.millis(66400), "", e -> { 
                    el.setLayoutX(204.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(66500), "", e -> { 
                    el.setLayoutX(200.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(66600), "", e -> { 
                    el.setLayoutX(197.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(66700), "", e -> { 
                    el.setLayoutX(194.0); 
                    el.setLayoutY(198.0); 
                }), 
                new KeyFrame(Duration.millis(66800), "", e -> { 
                    el.setLayoutX(193.0); 
                    el.setLayoutY(195.0); 
                }), 
                new KeyFrame(Duration.millis(66900), "", e -> { 
                    el.setLayoutX(191.0); 
                    el.setLayoutY(191.0); 
                }), 
                new KeyFrame(Duration.millis(67000), "", e -> { 
                    el.setLayoutX(190.0); 
                    el.setLayoutY(190.0); 
                }), 
                new KeyFrame(Duration.millis(67100), "", e -> { 
                    el.setLayoutX(189.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(67200), "", e -> { 
                    el.setLayoutX(189.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(67300), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(186.0); 
                }), 
                new KeyFrame(Duration.millis(67400), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(185.0); 
                }), 
                new KeyFrame(Duration.millis(67500), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(184.0); 
                }), 
                new KeyFrame(Duration.millis(67600), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(181.0); 
                }), 
                new KeyFrame(Duration.millis(67700), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(180.0); 
                }), 
                new KeyFrame(Duration.millis(67800), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(179.0); 
                }), 
                new KeyFrame(Duration.millis(67900), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(178.0); 
                }), 
                new KeyFrame(Duration.millis(68000), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(175.0); 
                }), 
                new KeyFrame(Duration.millis(68100), "", e -> { 
                    el.setLayoutX(189.0); 
                    el.setLayoutY(173.0); 
                }), 
                new KeyFrame(Duration.millis(68200), "", e -> { 
                    el.setLayoutX(190.0); 
                    el.setLayoutY(171.0); 
                }), 
                new KeyFrame(Duration.millis(68300), "", e -> { 
                    el.setLayoutX(193.0); 
                    el.setLayoutY(168.0); 
                }), 
                new KeyFrame(Duration.millis(68400), "", e -> { 
                    el.setLayoutX(193.0); 
                    el.setLayoutY(167.0); 
                }), 
                new KeyFrame(Duration.millis(68500), "", e -> { 
                    el.setLayoutX(194.0); 
                    el.setLayoutY(166.0); 
                }), 
                new KeyFrame(Duration.millis(68600), "", e -> { 
                    el.setLayoutX(194.0); 
                    el.setLayoutY(165.0); 
                }), 
                new KeyFrame(Duration.millis(68700), "", e -> { 
                    el.setLayoutX(196.0); 
                    el.setLayoutY(163.0); 
                }), 
                new KeyFrame(Duration.millis(68800), "", e -> { 
                    el.setLayoutX(196.0); 
                    el.setLayoutY(162.0); 
                }), 
                new KeyFrame(Duration.millis(68900), "", e -> { 
                    el.setLayoutX(198.0); 
                    el.setLayoutY(161.0); 
                }), 
                new KeyFrame(Duration.millis(69000), "", e -> { 
                    el.setLayoutX(198.0); 
                    el.setLayoutY(160.0); 
                }), 
                new KeyFrame(Duration.millis(69100), "", e -> { 
                    el.setLayoutX(199.0); 
                    el.setLayoutY(160.0); 
                }), 
                new KeyFrame(Duration.millis(69200), "", e -> { 
                    el.setLayoutX(199.0); 
                    el.setLayoutY(158.0); 
                }), 
                new KeyFrame(Duration.millis(69300), "", e -> { 
                    el.setLayoutX(200.0); 
                    el.setLayoutY(157.0); 
                }), 
                new KeyFrame(Duration.millis(69400), "", e -> { 
                    el.setLayoutX(201.0); 
                    el.setLayoutY(155.0); 
                }), 
                new KeyFrame(Duration.millis(69500), "", e -> { 
                    el.setLayoutX(202.0); 
                    el.setLayoutY(155.0); 
                }), 
                new KeyFrame(Duration.millis(69600), "", e -> { 
                    el.setLayoutX(204.0); 
                    el.setLayoutY(154.0); 
                }), 
                new KeyFrame(Duration.millis(69700), "", e -> { 
                    el.setLayoutX(204.0); 
                    el.setLayoutY(153.0); 
                }), 
                new KeyFrame(Duration.millis(69800), "", e -> { 
                    el.setLayoutX(206.0); 
                    el.setLayoutY(153.0); 
                }), 
                new KeyFrame(Duration.millis(69900), "", e -> { 
                    el.setLayoutX(208.0); 
                    el.setLayoutY(153.0); 
                }), 
                new KeyFrame(Duration.millis(70000), "", e -> { 
                    el.setLayoutX(210.0); 
                    el.setLayoutY(152.0); 
                }), 
                new KeyFrame(Duration.millis(70100), "", e -> { 
                    el.setLayoutX(212.0); 
                    el.setLayoutY(152.0); 
                }), 
                new KeyFrame(Duration.millis(70200), "", e -> { 
                    el.setLayoutX(216.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(70300), "", e -> { 
                    el.setLayoutX(219.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(70400), "", e -> { 
                    el.setLayoutX(221.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(70500), "", e -> { 
                    el.setLayoutX(225.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(70600), "", e -> { 
                    el.setLayoutX(228.0); 
                    el.setLayoutY(150.0); 
                }), 
                new KeyFrame(Duration.millis(70700), "", e -> { 
                    el.setLayoutX(233.0); 
                    el.setLayoutY(150.0); 
                }), 
                new KeyFrame(Duration.millis(70800), "", e -> { 
                    el.setLayoutX(238.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(70900), "", e -> { 
                    el.setLayoutX(244.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(71000), "", e -> { 
                    el.setLayoutX(246.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(71100), "", e -> { 
                    el.setLayoutX(250.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(71200), "", e -> { 
                    el.setLayoutX(255.0); 
                    el.setLayoutY(148.0); 
                }), 
                new KeyFrame(Duration.millis(71300), "", e -> { 
                    el.setLayoutX(264.0); 
                    el.setLayoutY(147.0); 
                }), 
                new KeyFrame(Duration.millis(71400), "", e -> { 
                    el.setLayoutX(272.0); 
                    el.setLayoutY(146.0); 
                }), 
                new KeyFrame(Duration.millis(71500), "", e -> { 
                    el.setLayoutX(280.0); 
                    el.setLayoutY(145.0); 
                }), 
                new KeyFrame(Duration.millis(71600), "", e -> { 
                    el.setLayoutX(288.0); 
                    el.setLayoutY(143.0); 
                }), 
                new KeyFrame(Duration.millis(71700), "", e -> { 
                    el.setLayoutX(296.0); 
                    el.setLayoutY(142.0); 
                }), 
                new KeyFrame(Duration.millis(71800), "", e -> { 
                    el.setLayoutX(303.0); 
                    el.setLayoutY(141.0); 
                }), 
                new KeyFrame(Duration.millis(71900), "", e -> { 
                    el.setLayoutX(311.0); 
                    el.setLayoutY(139.0); 
                }), 
                new KeyFrame(Duration.millis(72000), "", e -> { 
                    el.setLayoutX(321.0); 
                    el.setLayoutY(137.0); 
                }), 
                new KeyFrame(Duration.millis(72100), "", e -> { 
                    el.setLayoutX(333.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(72200), "", e -> { 
                    el.setLayoutX(345.0); 
                    el.setLayoutY(133.0); 
                }), 
                new KeyFrame(Duration.millis(72300), "", e -> { 
                    el.setLayoutX(359.0); 
                    el.setLayoutY(130.0); 
                }), 
                new KeyFrame(Duration.millis(72400), "", e -> { 
                    el.setLayoutX(372.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(72500), "", e -> { 
                    el.setLayoutX(387.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(72600), "", e -> { 
                    el.setLayoutX(402.0); 
                    el.setLayoutY(123.0); 
                }), 
                new KeyFrame(Duration.millis(72700), "", e -> { 
                    el.setLayoutX(423.0); 
                    el.setLayoutY(120.0); 
                }), 
                new KeyFrame(Duration.millis(72800), "", e -> { 
                    el.setLayoutX(443.0); 
                    el.setLayoutY(119.0); 
                }), 
                new KeyFrame(Duration.millis(72900), "", e -> { 
                    el.setLayoutX(465.0); 
                    el.setLayoutY(117.0); 
                }), 
                new KeyFrame(Duration.millis(73000), "", e -> { 
                    el.setLayoutX(488.0); 
                    el.setLayoutY(116.0); 
                }), 
                new KeyFrame(Duration.millis(73100), "", e -> { 
                    el.setLayoutX(513.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(73200), "", e -> { 
                    el.setLayoutX(539.0); 
                    el.setLayoutY(111.0); 
                }), 
                new KeyFrame(Duration.millis(73300), "", e -> { 
                    el.setLayoutX(564.0); 
                    el.setLayoutY(110.0); 
                }), 
                new KeyFrame(Duration.millis(73400), "", e -> { 
                    el.setLayoutX(592.0); 
                    el.setLayoutY(107.0); 
                }), 
                new KeyFrame(Duration.millis(73500), "", e -> { 
                    el.setLayoutX(618.0); 
                    el.setLayoutY(104.0); 
                }), 
                new KeyFrame(Duration.millis(73600), "", e -> { 
                    el.setLayoutX(644.0); 
                    el.setLayoutY(101.0); 
                }), 
                new KeyFrame(Duration.millis(73700), "", e -> { 
                    el.setLayoutX(670.0); 
                    el.setLayoutY(98.0); 
                }), 
                new KeyFrame(Duration.millis(73800), "", e -> { 
                    el.setLayoutX(698.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(73900), "", e -> { 
                    el.setLayoutX(726.0); 
                    el.setLayoutY(93.0); 
                }), 
                new KeyFrame(Duration.millis(74000), "", e -> { 
                    el.setLayoutX(751.0); 
                    el.setLayoutY(90.0); 
                }), 
                new KeyFrame(Duration.millis(74100), "", e -> { 
                    el.setLayoutX(777.0); 
                    el.setLayoutY(87.0); 
                }), 
                new KeyFrame(Duration.millis(74200), "", e -> { 
                    el.setLayoutX(800.0); 
                    el.setLayoutY(85.0); 
                }), 
                new KeyFrame(Duration.millis(74300), "", e -> { 
                    el.setLayoutX(822.0); 
                    el.setLayoutY(84.0); 
                }), 
                new KeyFrame(Duration.millis(74400), "", e -> { 
                    el.setLayoutX(845.0); 
                    el.setLayoutY(83.0); 
                }), 
                new KeyFrame(Duration.millis(74500), "", e -> { 
                    el.setLayoutX(862.0); 
                    el.setLayoutY(81.0); 
                }), 
                new KeyFrame(Duration.millis(74600), "", e -> { 
                    el.setLayoutX(877.0); 
                    el.setLayoutY(80.0); 
                }), 
                new KeyFrame(Duration.millis(74700), "", e -> { 
                    el.setLayoutX(890.0); 
                    el.setLayoutY(78.0); 
                }), 
                new KeyFrame(Duration.millis(74800), "", e -> { 
                    el.setLayoutX(901.0); 
                    el.setLayoutY(77.0); 
                }), 
                new KeyFrame(Duration.millis(74900), "", e -> { 
                    el.setLayoutX(902.0); 
                    el.setLayoutY(77.0); 
                }), 
                new KeyFrame(Duration.millis(75000), "", e -> { 
                    el.setLayoutX(903.0); 
                    el.setLayoutY(77.0); 
                }), 
                new KeyFrame(Duration.millis(75100), "", e -> { 
                    el.setLayoutX(902.0); 
                    el.setLayoutY(78.0); 
                }), 
                new KeyFrame(Duration.millis(75200), "", e -> { 
                    el.setLayoutX(901.0); 
                    el.setLayoutY(79.0); 
                }), 
                new KeyFrame(Duration.millis(75300), "", e -> { 
                    el.setLayoutX(899.0); 
                    el.setLayoutY(80.0); 
                }), 
                new KeyFrame(Duration.millis(75400), "", e -> { 
                    el.setLayoutX(895.0); 
                    el.setLayoutY(82.0); 
                }), 
                new KeyFrame(Duration.millis(75500), "", e -> { 
                    el.setLayoutX(893.0); 
                    el.setLayoutY(83.0); 
                }), 
                new KeyFrame(Duration.millis(75600), "", e -> { 
                    el.setLayoutX(891.0); 
                    el.setLayoutY(84.0); 
                }), 
                new KeyFrame(Duration.millis(75700), "", e -> { 
                    el.setLayoutX(888.0); 
                    el.setLayoutY(86.0); 
                }), 
                new KeyFrame(Duration.millis(75800), "", e -> { 
                    el.setLayoutX(885.0); 
                    el.setLayoutY(91.0); 
                }), 
                new KeyFrame(Duration.millis(75900), "", e -> { 
                    el.setLayoutX(879.0); 
                    el.setLayoutY(95.0); 
                }), 
                new KeyFrame(Duration.millis(76000), "", e -> { 
                    el.setLayoutX(877.0); 
                    el.setLayoutY(97.0); 
                }), 
                new KeyFrame(Duration.millis(76100), "", e -> { 
                    el.setLayoutX(874.0); 
                    el.setLayoutY(99.0); 
                }), 
                new KeyFrame(Duration.millis(76200), "", e -> { 
                    el.setLayoutX(869.0); 
                    el.setLayoutY(105.0); 
                }), 
                new KeyFrame(Duration.millis(76300), "", e -> { 
                    el.setLayoutX(860.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(76400), "", e -> { 
                    el.setLayoutX(848.0); 
                    el.setLayoutY(126.0); 
                }), 
                new KeyFrame(Duration.millis(76500), "", e -> { 
                    el.setLayoutX(827.0); 
                    el.setLayoutY(147.0); 
                }), 
                new KeyFrame(Duration.millis(76600), "", e -> { 
                    el.setLayoutX(799.0); 
                    el.setLayoutY(176.0); 
                }), 
                new KeyFrame(Duration.millis(76700), "", e -> { 
                    el.setLayoutX(762.0); 
                    el.setLayoutY(209.0); 
                }), 
                new KeyFrame(Duration.millis(76800), "", e -> { 
                    el.setLayoutX(721.0); 
                    el.setLayoutY(242.0); 
                }), 
                new KeyFrame(Duration.millis(76900), "", e -> { 
                    el.setLayoutX(677.0); 
                    el.setLayoutY(278.0); 
                }), 
                new KeyFrame(Duration.millis(77000), "", e -> { 
                    el.setLayoutX(627.0); 
                    el.setLayoutY(315.0); 
                }), 
                new KeyFrame(Duration.millis(77100), "", e -> { 
                    el.setLayoutX(581.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(77200), "", e -> { 
                    el.setLayoutX(535.0); 
                    el.setLayoutY(381.0); 
                }), 
                new KeyFrame(Duration.millis(77300), "", e -> { 
                    el.setLayoutX(492.0); 
                    el.setLayoutY(409.0); 
                }), 
                new KeyFrame(Duration.millis(77400), "", e -> { 
                    el.setLayoutX(454.0); 
                    el.setLayoutY(436.0); 
                }), 
                new KeyFrame(Duration.millis(77500), "", e -> { 
                    el.setLayoutX(419.0); 
                    el.setLayoutY(458.0); 
                }), 
                new KeyFrame(Duration.millis(77600), "", e -> { 
                    el.setLayoutX(391.0); 
                    el.setLayoutY(476.0); 
                }), 
                new KeyFrame(Duration.millis(77700), "", e -> { 
                    el.setLayoutX(370.0); 
                    el.setLayoutY(490.0); 
                }), 
                new KeyFrame(Duration.millis(77800), "", e -> { 
                    el.setLayoutX(353.0); 
                    el.setLayoutY(501.0); 
                }), 
                new KeyFrame(Duration.millis(77900), "", e -> { 
                    el.setLayoutX(342.0); 
                    el.setLayoutY(508.0); 
                }), 
                new KeyFrame(Duration.millis(78000), "", e -> { 
                    el.setLayoutX(334.0); 
                    el.setLayoutY(513.0); 
                }), 
                new KeyFrame(Duration.millis(78100), "", e -> { 
                    el.setLayoutX(332.0); 
                    el.setLayoutY(514.0); 
                }), 
                new KeyFrame(Duration.millis(78200), "", e -> { 
                    el.setLayoutX(333.0); 
                    el.setLayoutY(513.0); 
                }), 
                new KeyFrame(Duration.millis(78300), "", e -> { 
                    el.setLayoutX(339.0); 
                    el.setLayoutY(511.0); 
                }), 
                new KeyFrame(Duration.millis(78400), "", e -> { 
                    el.setLayoutX(344.0); 
                    el.setLayoutY(509.0); 
                }), 
                new KeyFrame(Duration.millis(78500), "", e -> { 
                    el.setLayoutX(349.0); 
                    el.setLayoutY(509.0); 
                }), 
                new KeyFrame(Duration.millis(78600), "", e -> { 
                    el.setLayoutX(351.0); 
                    el.setLayoutY(509.0); 
                }), 
                new KeyFrame(Duration.millis(78700), "", e -> { 
                    el.setLayoutX(357.0); 
                    el.setLayoutY(507.0); 
                }), 
                new KeyFrame(Duration.millis(78800), "", e -> { 
                    el.setLayoutX(368.0); 
                    el.setLayoutY(504.0); 
                }), 
                new KeyFrame(Duration.millis(78900), "", e -> { 
                    el.setLayoutX(382.0); 
                    el.setLayoutY(499.0); 
                }), 
                new KeyFrame(Duration.millis(79000), "", e -> { 
                    el.setLayoutX(404.0); 
                    el.setLayoutY(491.0); 
                }), 
                new KeyFrame(Duration.millis(79100), "", e -> { 
                    el.setLayoutX(433.0); 
                    el.setLayoutY(485.0); 
                }), 
                new KeyFrame(Duration.millis(79200), "", e -> { 
                    el.setLayoutX(468.0); 
                    el.setLayoutY(478.0); 
                }), 
                new KeyFrame(Duration.millis(79300), "", e -> { 
                    el.setLayoutX(507.0); 
                    el.setLayoutY(473.0); 
                }), 
                new KeyFrame(Duration.millis(79400), "", e -> { 
                    el.setLayoutX(549.0); 
                    el.setLayoutY(469.0); 
                }), 
                new KeyFrame(Duration.millis(79500), "", e -> { 
                    el.setLayoutX(595.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(79600), "", e -> { 
                    el.setLayoutX(642.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(79700), "", e -> { 
                    el.setLayoutX(691.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(79800), "", e -> { 
                    el.setLayoutX(740.0); 
                    el.setLayoutY(466.0); 
                }), 
                new KeyFrame(Duration.millis(79900), "", e -> { 
                    el.setLayoutX(787.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(80000), "", e -> { 
                    el.setLayoutX(830.0); 
                    el.setLayoutY(472.0); 
                }), 
                new KeyFrame(Duration.millis(80100), "", e -> { 
                    el.setLayoutX(869.0); 
                    el.setLayoutY(474.0); 
                }), 
                new KeyFrame(Duration.millis(80200), "", e -> { 
                    el.setLayoutX(899.0); 
                    el.setLayoutY(474.0); 
                }), 
                new KeyFrame(Duration.millis(80300), "", e -> { 
                    el.setLayoutX(923.0); 
                    el.setLayoutY(474.0); 
                }), 
                new KeyFrame(Duration.millis(80400), "", e -> { 
                    el.setLayoutX(941.0); 
                    el.setLayoutY(473.0); 
                }), 
                new KeyFrame(Duration.millis(80500), "", e -> { 
                    el.setLayoutX(954.0); 
                    el.setLayoutY(472.0); 
                }), 
                new KeyFrame(Duration.millis(80600), "", e -> { 
                    el.setLayoutX(963.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(80700), "", e -> { 
                    el.setLayoutX(972.0); 
                    el.setLayoutY(468.0); 
                }), 
                new KeyFrame(Duration.millis(80800), "", e -> { 
                    el.setLayoutX(973.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(80900), "", e -> { 
                    el.setLayoutX(974.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(81000), "", e -> { 
                    el.setLayoutX(975.0); 
                    el.setLayoutY(466.0); 
                }), 
                new KeyFrame(Duration.millis(81100), "", e -> { 
                    el.setLayoutX(975.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(81200), "", e -> { 
                    el.setLayoutX(975.0); 
                    el.setLayoutY(464.0); 
                }), 
                new KeyFrame(Duration.millis(81300), "", e -> { 
                    el.setLayoutX(972.0); 
                    el.setLayoutY(461.0); 
                }), 
                new KeyFrame(Duration.millis(81400), "", e -> { 
                    el.setLayoutX(968.0); 
                    el.setLayoutY(457.0); 
                }), 
                new KeyFrame(Duration.millis(81500), "", e -> { 
                    el.setLayoutX(966.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(81600), "", e -> { 
                    el.setLayoutX(964.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(81700), "", e -> { 
                    el.setLayoutX(957.0); 
                    el.setLayoutY(449.0); 
                }), 
                new KeyFrame(Duration.millis(81800), "", e -> { 
                    el.setLayoutX(942.0); 
                    el.setLayoutY(440.0); 
                }), 
                new KeyFrame(Duration.millis(81900), "", e -> { 
                    el.setLayoutX(913.0); 
                    el.setLayoutY(425.0); 
                }), 
                new KeyFrame(Duration.millis(82000), "", e -> { 
                    el.setLayoutX(872.0); 
                    el.setLayoutY(406.0); 
                }), 
                new KeyFrame(Duration.millis(82100), "", e -> { 
                    el.setLayoutX(822.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(82200), "", e -> { 
                    el.setLayoutX(756.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(82300), "", e -> { 
                    el.setLayoutX(682.0); 
                    el.setLayoutY(326.0); 
                }), 
                new KeyFrame(Duration.millis(82400), "", e -> { 
                    el.setLayoutX(600.0); 
                    el.setLayoutY(293.0); 
                }), 
                new KeyFrame(Duration.millis(82500), "", e -> { 
                    el.setLayoutX(513.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(82600), "", e -> { 
                    el.setLayoutX(424.0); 
                    el.setLayoutY(230.0); 
                }), 
                new KeyFrame(Duration.millis(82700), "", e -> { 
                    el.setLayoutX(335.0); 
                    el.setLayoutY(201.0); 
                }), 
                new KeyFrame(Duration.millis(82800), "", e -> { 
                    el.setLayoutX(248.0); 
                    el.setLayoutY(174.0); 
                }), 
                new KeyFrame(Duration.millis(82900), "", e -> { 
                    el.setLayoutX(170.0); 
                    el.setLayoutY(150.0); 
                }), 
                new KeyFrame(Duration.millis(83000), "", e -> { 
                    el.setLayoutX(102.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(83100), "", e -> { 
                    el.setLayoutX(45.0); 
                    el.setLayoutY(115.0); 
                })); 
        tm.setCycleCount(Animation.INDEFINITE); 
        tm.play(); 
    } 
 
    public void randomMv3(Label el) { 
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(83200), "", e -> { 
            el.setLayoutX(1191.0); 
            el.setLayoutY(429.0); 
        }), 
                new KeyFrame(Duration.millis(83300), "", e -> { 
                    el.setLayoutX(1160.0); 
                    el.setLayoutY(434.0); 
                }), 
                new KeyFrame(Duration.millis(83400), "", e -> { 
                    el.setLayoutX(1120.0); 
                    el.setLayoutY(439.0); 
                }), 
                new KeyFrame(Duration.millis(83500), "", e -> { 
                    el.setLayoutX(1070.0); 
                    el.setLayoutY(445.0); 
                }), 
                new KeyFrame(Duration.millis(83600), "", e -> { 
                    el.setLayoutX(1015.0); 
                    el.setLayoutY(449.0); 
                }), 
                new KeyFrame(Duration.millis(83700), "", e -> { 
                    el.setLayoutX(955.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(83800), "", e -> { 
                    el.setLayoutX(893.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(83900), "", e -> { 
                    el.setLayoutX(831.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84000), "", e -> { 
                    el.setLayoutX(766.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84100), "", e -> { 
                    el.setLayoutX(706.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84200), "", e -> { 
                    el.setLayoutX(649.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84300), "", e -> { 
                    el.setLayoutX(601.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84400), "", e -> { 
                    el.setLayoutX(557.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84500), "", e -> { 
                    el.setLayoutX(522.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(84600), "", e -> { 
                    el.setLayoutX(492.0); 
                    el.setLayoutY(454.0); 
                }), 
                new KeyFrame(Duration.millis(84700), "", e -> { 
                    el.setLayoutX(468.0); 
                    el.setLayoutY(450.0); 
                }), 
                new KeyFrame(Duration.millis(84800), "", e -> { 
                    el.setLayoutX(451.0); 
                    el.setLayoutY(443.0); 
                }), 
                new KeyFrame(Duration.millis(84900), "", e -> { 
                    el.setLayoutX(435.0); 
                    el.setLayoutY(436.0); 
                }), 
                new KeyFrame(Duration.millis(85000), "", e -> { 
                    el.setLayoutX(424.0); 
                    el.setLayoutY(428.0); 
                }), 
                new KeyFrame(Duration.millis(85100), "", e -> { 
                    el.setLayoutX(415.0); 
                    el.setLayoutY(419.0); 
                }), 
                new KeyFrame(Duration.millis(85200), "", e -> { 
                    el.setLayoutX(409.0); 
                    el.setLayoutY(412.0); 
                }), 
                new KeyFrame(Duration.millis(85300), "", e -> { 
                    el.setLayoutX(403.0); 
                    el.setLayoutY(404.0); 
                }), 
                new KeyFrame(Duration.millis(85400), "", e -> { 
                    el.setLayoutX(398.0); 
                    el.setLayoutY(397.0); 
                }), 
                new KeyFrame(Duration.millis(85500), "", e -> { 
                    el.setLayoutX(394.0); 
                    el.setLayoutY(391.0); 
                }), 
                new KeyFrame(Duration.millis(85600), "", e -> { 
                    el.setLayoutX(394.0); 
                    el.setLayoutY(389.0); 
                }), 
                new KeyFrame(Duration.millis(85700), "", e -> { 
                    el.setLayoutX(393.0); 
                    el.setLayoutY(389.0); 
                }), 
                new KeyFrame(Duration.millis(85800), "", e -> { 
                    el.setLayoutX(392.0); 
                    el.setLayoutY(387.0); 
                }), 
                new KeyFrame(Duration.millis(85900), "", e -> { 
                    el.setLayoutX(392.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(86000), "", e -> { 
                    el.setLayoutX(391.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(86100), "", e -> { 
                    el.setLayoutX(389.0); 
                    el.setLayoutY(383.0); 
                }), 
                new KeyFrame(Duration.millis(86200), "", e -> { 
                    el.setLayoutX(386.0); 
                    el.setLayoutY(382.0); 
                }), 
                new KeyFrame(Duration.millis(86300), "", e -> { 
                    el.setLayoutX(382.0); 
                    el.setLayoutY(380.0); 
                }), 
                new KeyFrame(Duration.millis(86400), "", e -> { 
                    el.setLayoutX(379.0); 
                    el.setLayoutY(379.0); 
                }), 
                new KeyFrame(Duration.millis(86500), "", e -> { 
                    el.setLayoutX(373.0); 
                    el.setLayoutY(377.0); 
                }), 
                new KeyFrame(Duration.millis(86600), "", e -> { 
                    el.setLayoutX(371.0); 
                    el.setLayoutY(376.0); 
                }), 
                new KeyFrame(Duration.millis(86700), "", e -> { 
                    el.setLayoutX(367.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(86800), "", e -> { 
                    el.setLayoutX(365.0); 
                    el.setLayoutY(373.0); 
                }), 
                new KeyFrame(Duration.millis(86900), "", e -> { 
                    el.setLayoutX(360.0); 
                    el.setLayoutY(371.0); 
                }), 
                new KeyFrame(Duration.millis(87000), "", e -> { 
                    el.setLayoutX(353.0); 
                    el.setLayoutY(367.0); 
                }), 
                new KeyFrame(Duration.millis(87100), "", e -> { 
                    el.setLayoutX(347.0); 
                    el.setLayoutY(363.0); 
                }), 
                new KeyFrame(Duration.millis(87200), "", e -> { 
                    el.setLayoutX(341.0); 
                    el.setLayoutY(359.0); 
                }), 
                new KeyFrame(Duration.millis(87300), "", e -> { 
                    el.setLayoutX(336.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(87400), "", e -> { 
                    el.setLayoutX(335.0); 
                    el.setLayoutY(354.0); 
                }), 
                new KeyFrame(Duration.millis(87500), "", e -> { 
                    el.setLayoutX(332.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(87600), "", e -> { 
                    el.setLayoutX(331.0); 
                    el.setLayoutY(346.0); 
                }), 
                new KeyFrame(Duration.millis(87700), "", e -> { 
                    el.setLayoutX(329.0); 
                    el.setLayoutY(343.0); 
                }), 
                new KeyFrame(Duration.millis(87800), "", e -> { 
                    el.setLayoutX(325.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(87900), "", e -> { 
                    el.setLayoutX(324.0); 
                    el.setLayoutY(336.0); 
                }), 
                new KeyFrame(Duration.millis(88000), "", e -> { 
                    el.setLayoutX(322.0); 
                    el.setLayoutY(332.0); 
                }), 
                new KeyFrame(Duration.millis(88100), "", e -> { 
                    el.setLayoutX(318.0); 
                    el.setLayoutY(327.0); 
                }), 
                new KeyFrame(Duration.millis(88200), "", e -> { 
                    el.setLayoutX(315.0); 
                    el.setLayoutY(322.0); 
                }), 
                new KeyFrame(Duration.millis(88300), "", e -> { 
                    el.setLayoutX(314.0); 
                    el.setLayoutY(319.0); 
                }), 
                new KeyFrame(Duration.millis(88400), "", e -> { 
                    el.setLayoutX(314.0); 
                    el.setLayoutY(318.0); 
                }), 
                new KeyFrame(Duration.millis(88500), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(88600), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(88700), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(315.0); 
                }), 
                new KeyFrame(Duration.millis(88800), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(314.0); 
                }), 
                new KeyFrame(Duration.millis(88900), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(313.0); 
                }), 
                new KeyFrame(Duration.millis(89000), "", e -> { 
                    el.setLayoutX(312.0); 
                    el.setLayoutY(312.0); 
                }), 
                new KeyFrame(Duration.millis(89100), "", e -> { 
                    el.setLayoutX(312.0); 
                    el.setLayoutY(311.0); 
                }), 
                new KeyFrame(Duration.millis(89200), "", e -> { 
                    el.setLayoutX(315.0); 
                    el.setLayoutY(306.0); 
                }), 
                new KeyFrame(Duration.millis(89300), "", e -> { 
                    el.setLayoutX(317.0); 
                    el.setLayoutY(305.0); 
                }), 
                new KeyFrame(Duration.millis(89400), "", e -> { 
                    el.setLayoutX(318.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(89500), "", e -> { 
                    el.setLayoutX(319.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(89600), "", e -> { 
                    el.setLayoutX(319.0); 
                    el.setLayoutY(302.0); 
                }), 
                new KeyFrame(Duration.millis(89700), "", e -> { 
                    el.setLayoutX(321.0); 
                    el.setLayoutY(300.0); 
                }), 
                new KeyFrame(Duration.millis(89800), "", e -> { 
                    el.setLayoutX(322.0); 
                    el.setLayoutY(299.0); 
                }), 
                new KeyFrame(Duration.millis(89900), "", e -> { 
                    el.setLayoutX(324.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(90000), "", e -> { 
                    el.setLayoutX(329.0); 
                    el.setLayoutY(294.0); 
                }), 
                new KeyFrame(Duration.millis(90100), "", e -> { 
                    el.setLayoutX(332.0); 
                    el.setLayoutY(291.0); 
                }), 
                new KeyFrame(Duration.millis(90200), "", e -> { 
                    el.setLayoutX(334.0); 
                    el.setLayoutY(290.0); 
                }), 
                new KeyFrame(Duration.millis(90300), "", e -> { 
                    el.setLayoutX(340.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(90400), "", e -> { 
                    el.setLayoutX(343.0); 
                    el.setLayoutY(284.0); 
                }), 
                new KeyFrame(Duration.millis(90500), "", e -> { 
                    el.setLayoutX(349.0); 
                    el.setLayoutY(280.0); 
                }), 
                new KeyFrame(Duration.millis(90600), "", e -> { 
                    el.setLayoutX(352.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(90700), "", e -> { 
                    el.setLayoutX(358.0); 
                    el.setLayoutY(275.0); 
                }), 
                new KeyFrame(Duration.millis(90800), "", e -> { 
                    el.setLayoutX(367.0); 
                    el.setLayoutY(268.0); 
                }), 
                new KeyFrame(Duration.millis(90900), "", e -> { 
                    el.setLayoutX(377.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(91000), "", e -> { 
                    el.setLayoutX(387.0); 
                    el.setLayoutY(254.0); 
                }), 
                new KeyFrame(Duration.millis(91100), "", e -> { 
                    el.setLayoutX(400.0); 
                    el.setLayoutY(244.0); 
                }), 
                new KeyFrame(Duration.millis(91200), "", e -> { 
                    el.setLayoutX(421.0); 
                    el.setLayoutY(232.0); 
                }), 
                new KeyFrame(Duration.millis(91300), "", e -> { 
                    el.setLayoutX(452.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(91400), "", e -> { 
                    el.setLayoutX(490.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(91500), "", e -> { 
                    el.setLayoutX(537.0); 
                    el.setLayoutY(192.0); 
                }), 
                new KeyFrame(Duration.millis(91600), "", e -> { 
                    el.setLayoutX(588.0); 
                    el.setLayoutY(176.0); 
                }), 
                new KeyFrame(Duration.millis(91700), "", e -> { 
                    el.setLayoutX(643.0); 
                    el.setLayoutY(160.0); 
                }), 
                new KeyFrame(Duration.millis(91800), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(146.0); 
                }), 
                new KeyFrame(Duration.millis(91900), "", e -> { 
                    el.setLayoutX(739.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(92000), "", e -> { 
                    el.setLayoutX(779.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(92100), "", e -> { 
                    el.setLayoutX(808.0); 
                    el.setLayoutY(123.0); 
                }), 
                new KeyFrame(Duration.millis(92200), "", e -> { 
                    el.setLayoutX(829.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(92300), "", e -> { 
                    el.setLayoutX(831.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(92400), "", e -> { 
                    el.setLayoutX(832.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(92500), "", e -> { 
                    el.setLayoutX(830.0); 
                    el.setLayoutY(119.0); 
                }), 
                new KeyFrame(Duration.millis(92600), "", e -> { 
                    el.setLayoutX(827.0); 
                    el.setLayoutY(121.0); 
                }), 
                new KeyFrame(Duration.millis(92700), "", e -> { 
                    el.setLayoutX(822.0); 
                    el.setLayoutY(123.0); 
                }), 
                new KeyFrame(Duration.millis(92800), "", e -> { 
                    el.setLayoutX(818.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(92900), "", e -> { 
                    el.setLayoutX(807.0); 
                    el.setLayoutY(131.0); 
                }), 
                new KeyFrame(Duration.millis(93000), "", e -> { 
                    el.setLayoutX(783.0); 
                    el.setLayoutY(139.0); 
                }), 
                new KeyFrame(Duration.millis(93100), "", e -> { 
                    el.setLayoutX(737.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(93200), "", e -> { 
                    el.setLayoutX(675.0); 
                    el.setLayoutY(165.0); 
                }), 
                new KeyFrame(Duration.millis(93300), "", e -> { 
                    el.setLayoutX(604.0); 
                    el.setLayoutY(176.0); 
                }), 
                new KeyFrame(Duration.millis(93400), "", e -> { 
                    el.setLayoutX(516.0); 
                    el.setLayoutY(182.0); 
                }), 
                new KeyFrame(Duration.millis(93500), "", e -> { 
                    el.setLayoutX(424.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(93600), "", e -> { 
                    el.setLayoutX(324.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(93700), "", e -> { 
                    el.setLayoutX(227.0); 
                    el.setLayoutY(183.0); 
                }), 
                new KeyFrame(Duration.millis(93800), "", e -> { 
                    el.setLayoutX(140.0); 
                    el.setLayoutY(174.0); 
                }), 
                new KeyFrame(Duration.millis(93900), "", e -> { 
                    el.setLayoutX(60.0); 
                    el.setLayoutY(166.0); 
                }), 
                new KeyFrame(Duration.millis(94000), "", e -> { 
                    el.setLayoutX(9.0); 
                    el.setLayoutY(97.0); 
                }), 
                new KeyFrame(Duration.millis(94100), "", e -> { 
                    el.setLayoutX(37.0); 
                    el.setLayoutY(95.0); 
                }), 
                new KeyFrame(Duration.millis(94200), "", e -> { 
                    el.setLayoutX(68.0); 
                    el.setLayoutY(98.0); 
                }), 
                new KeyFrame(Duration.millis(94300), "", e -> { 
                    el.setLayoutX(98.0); 
                    el.setLayoutY(108.0); 
                }), 
                new KeyFrame(Duration.millis(94400), "", e -> { 
                    el.setLayoutX(132.0); 
                    el.setLayoutY(124.0); 
                }), 
                new KeyFrame(Duration.millis(94500), "", e -> { 
                    el.setLayoutX(165.0); 
                    el.setLayoutY(144.0); 
                }), 
                new KeyFrame(Duration.millis(94600), "", e -> { 
                    el.setLayoutX(195.0); 
                    el.setLayoutY(167.0); 
                }), 
                new KeyFrame(Duration.millis(94700), "", e -> { 
                    el.setLayoutX(219.0); 
                    el.setLayoutY(190.0); 
                }), 
                new KeyFrame(Duration.millis(94800), "", e -> { 
                    el.setLayoutX(237.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(94900), "", e -> { 
                    el.setLayoutX(249.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(95000), "", e -> { 
                    el.setLayoutX(257.0); 
                    el.setLayoutY(254.0); 
                }), 
                new KeyFrame(Duration.millis(95100), "", e -> { 
                    el.setLayoutX(262.0); 
                    el.setLayoutY(271.0); 
                }), 
                new KeyFrame(Duration.millis(95200), "", e -> { 
                    el.setLayoutX(266.0); 
                    el.setLayoutY(284.0); 
                }), 
                new KeyFrame(Duration.millis(95300), "", e -> { 
                    el.setLayoutX(269.0); 
                    el.setLayoutY(298.0); 
                }), 
                new KeyFrame(Duration.millis(95400), "", e -> { 
                    el.setLayoutX(274.0); 
                    el.setLayoutY(314.0); 
                }), 
                new KeyFrame(Duration.millis(95500), "", e -> { 
                    el.setLayoutX(279.0); 
                    el.setLayoutY(330.0); 
                }), 
                new KeyFrame(Duration.millis(95600), "", e -> { 
                    el.setLayoutX(285.0); 
                    el.setLayoutY(352.0); 
                }), 
                new KeyFrame(Duration.millis(95700), "", e -> { 
                    el.setLayoutX(292.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(95800), "", e -> { 
                    el.setLayoutX(299.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(95900), "", e -> { 
                    el.setLayoutX(308.0); 
                    el.setLayoutY(407.0); 
                }), 
                new KeyFrame(Duration.millis(96000), "", e -> { 
                    el.setLayoutX(317.0); 
                    el.setLayoutY(416.0); 
                }), 
                new KeyFrame(Duration.millis(96100), "", e -> { 
                    el.setLayoutX(326.0); 
                    el.setLayoutY(422.0); 
                }), 
                new KeyFrame(Duration.millis(96200), "", e -> { 
                    el.setLayoutX(338.0); 
                    el.setLayoutY(423.0); 
                }), 
                new KeyFrame(Duration.millis(96300), "", e -> { 
                    el.setLayoutX(353.0); 
                    el.setLayoutY(421.0); 
                }), 
                new KeyFrame(Duration.millis(96400), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(414.0); 
                }), 
                new KeyFrame(Duration.millis(96500), "", e -> { 
                    el.setLayoutX(399.0); 
                    el.setLayoutY(403.0); 
                }), 
                new KeyFrame(Duration.millis(96600), "", e -> { 
                    el.setLayoutX(427.0); 
                    el.setLayoutY(390.0); 
                }), 
                new KeyFrame(Duration.millis(96700), "", e -> { 
                    el.setLayoutX(459.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(96800), "", e -> { 
                    el.setLayoutX(493.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(96900), "", e -> { 
                    el.setLayoutX(528.0); 
                    el.setLayoutY(336.0); 
                }), 
                new KeyFrame(Duration.millis(97000), "", e -> { 
                    el.setLayoutX(565.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(97100), "", e -> { 
                    el.setLayoutX(599.0); 
                    el.setLayoutY(301.0); 
                }), 
                new KeyFrame(Duration.millis(97200), "", e -> { 
                    el.setLayoutX(632.0); 
                    el.setLayoutY(287.0); 
                }), 
                new KeyFrame(Duration.millis(97300), "", e -> { 
                    el.setLayoutX(661.0); 
                    el.setLayoutY(274.0); 
                }), 
                new KeyFrame(Duration.millis(97400), "", e -> { 
                    el.setLayoutX(687.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(97500), "", e -> { 
                    el.setLayoutX(710.0); 
                    el.setLayoutY(250.0); 
                }), 
                new KeyFrame(Duration.millis(97600), "", e -> { 
                    el.setLayoutX(726.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(97700), "", e -> { 
                    el.setLayoutX(737.0); 
                    el.setLayoutY(233.0); 
                }), 
                new KeyFrame(Duration.millis(97800), "", e -> { 
                    el.setLayoutX(745.0); 
                    el.setLayoutY(226.0); 
                }), 
                new KeyFrame(Duration.millis(97900), "", e -> { 
                    el.setLayoutX(751.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(98000), "", e -> { 
                    el.setLayoutX(755.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(98100), "", e -> { 
                    el.setLayoutX(757.0); 
                    el.setLayoutY(211.0); 
                }), 
                new KeyFrame(Duration.millis(98200), "", e -> { 
                    el.setLayoutX(758.0); 
                    el.setLayoutY(206.0); 
                }), 
                new KeyFrame(Duration.millis(98300), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(98400), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(202.0); 
                }), 
                new KeyFrame(Duration.millis(98500), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(201.0); 
                }), 
                new KeyFrame(Duration.millis(98600), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(200.0); 
                }), 
                new KeyFrame(Duration.millis(98700), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(199.0); 
                }), 
                new KeyFrame(Duration.millis(98800), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(198.0); 
                }), 
                new KeyFrame(Duration.millis(98900), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(196.0); 
                }), 
                new KeyFrame(Duration.millis(99000), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(195.0); 
                }), 
                new KeyFrame(Duration.millis(99100), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(99200), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(192.0); 
                }), 
                new KeyFrame(Duration.millis(99300), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(191.0); 
                }), 
                new KeyFrame(Duration.millis(99400), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(190.0); 
                }), 
                new KeyFrame(Duration.millis(99500), "", e -> { 
                    el.setLayoutX(760.0); 
                    el.setLayoutY(189.0); 
                }), 
                new KeyFrame(Duration.millis(99600), "", e -> { 
                    el.setLayoutX(760.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(99700), "", e -> { 
                    el.setLayoutX(761.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(99800), "", e -> { 
                    el.setLayoutX(763.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(99900), "", e -> { 
                    el.setLayoutX(764.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(100000), "", e -> { 
                    el.setLayoutX(767.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(100100), "", e -> { 
                    el.setLayoutX(770.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(100200), "", e -> { 
                    el.setLayoutX(772.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(100300), "", e -> { 
                    el.setLayoutX(778.0); 
                    el.setLayoutY(191.0); 
                }), 
                new KeyFrame(Duration.millis(100400), "", e -> { 
                    el.setLayoutX(781.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(100500), "", e -> { 
                    el.setLayoutX(787.0); 
                    el.setLayoutY(197.0); 
                }), 
                new KeyFrame(Duration.millis(100600), "", e -> { 
                    el.setLayoutX(797.0); 
                    el.setLayoutY(202.0); 
                }), 
                new KeyFrame(Duration.millis(100700), "", e -> { 
                    el.setLayoutX(808.0); 
                    el.setLayoutY(208.0); 
                }), 
                new KeyFrame(Duration.millis(100800), "", e -> { 
                    el.setLayoutX(820.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(100900), "", e -> { 
                    el.setLayoutX(833.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(101000), "", e -> { 
                    el.setLayoutX(846.0); 
                    el.setLayoutY(227.0); 
                }), 
                new KeyFrame(Duration.millis(101100), "", e -> { 
                    el.setLayoutX(860.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(101200), "", e -> { 
                    el.setLayoutX(874.0); 
                    el.setLayoutY(246.0); 
                }), 
                new KeyFrame(Duration.millis(101300), "", e -> { 
                    el.setLayoutX(884.0); 
                    el.setLayoutY(256.0); 
                }), 
                new KeyFrame(Duration.millis(101400), "", e -> { 
                    el.setLayoutX(893.0); 
                    el.setLayoutY(268.0); 
                }), 
                new KeyFrame(Duration.millis(101500), "", e -> { 
                    el.setLayoutX(899.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(101600), "", e -> { 
                    el.setLayoutX(902.0); 
                    el.setLayoutY(292.0); 
                }), 
                new KeyFrame(Duration.millis(101700), "", e -> { 
                    el.setLayoutX(903.0); 
                    el.setLayoutY(307.0); 
                }), 
                new KeyFrame(Duration.millis(101800), "", e -> { 
                    el.setLayoutX(902.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(101900), "", e -> { 
                    el.setLayoutX(894.0); 
                    el.setLayoutY(346.0); 
                }), 
                new KeyFrame(Duration.millis(102000), "", e -> { 
                    el.setLayoutX(884.0); 
                    el.setLayoutY(367.0); 
                }), 
                new KeyFrame(Duration.millis(102100), "", e -> { 
                    el.setLayoutX(869.0); 
                    el.setLayoutY(387.0); 
                }), 
                new KeyFrame(Duration.millis(102200), "", e -> { 
                    el.setLayoutX(853.0); 
                    el.setLayoutY(402.0); 
                }), 
                new KeyFrame(Duration.millis(102300), "", e -> { 
                    el.setLayoutX(832.0); 
                    el.setLayoutY(414.0); 
                }), 
                new KeyFrame(Duration.millis(102400), "", e -> { 
                    el.setLayoutX(805.0); 
                    el.setLayoutY(422.0); 
                }), 
                new KeyFrame(Duration.millis(102500), "", e -> { 
                    el.setLayoutX(773.0); 
                    el.setLayoutY(427.0); 
                }), 
                new KeyFrame(Duration.millis(102600), "", e -> { 
                    el.setLayoutX(742.0); 
                    el.setLayoutY(430.0); 
                }), 
                new KeyFrame(Duration.millis(102700), "", e -> { 
                    el.setLayoutX(715.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(102800), "", e -> { 
                    el.setLayoutX(695.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(102900), "", e -> { 
                    el.setLayoutX(682.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(103000), "", e -> { 
                    el.setLayoutX(682.0); 
                    el.setLayoutY(433.0); 
                }), 
                new KeyFrame(Duration.millis(103100), "", e -> { 
                    el.setLayoutX(683.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103200), "", e -> { 
                    el.setLayoutX(689.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103300), "", e -> { 
                    el.setLayoutX(693.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103400), "", e -> { 
                    el.setLayoutX(702.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103500), "", e -> { 
                    el.setLayoutX(717.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103600), "", e -> { 
                    el.setLayoutX(733.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103700), "", e -> { 
                    el.setLayoutX(752.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103800), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(103900), "", e -> { 
                    el.setLayoutX(797.0); 
                    el.setLayoutY(438.0); 
                }), 
                new KeyFrame(Duration.millis(104000), "", e -> { 
                    el.setLayoutX(827.0); 
                    el.setLayoutY(445.0); 
                }), 
                new KeyFrame(Duration.millis(104100), "", e -> { 
                    el.setLayoutX(856.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(104200), "", e -> { 
                    el.setLayoutX(890.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(104300), "", e -> { 
                    el.setLayoutX(923.0); 
                    el.setLayoutY(479.0); 
                }), 
                new KeyFrame(Duration.millis(104400), "", e -> { 
                    el.setLayoutX(957.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(104500), "", e -> { 
                    el.setLayoutX(990.0); 
                    el.setLayoutY(506.0); 
                }), 
                new KeyFrame(Duration.millis(104600), "", e -> { 
                    el.setLayoutX(1020.0); 
                    el.setLayoutY(516.0); 
                }), 
                new KeyFrame(Duration.millis(104700), "", e -> { 
                    el.setLayoutX(1048.0); 
                    el.setLayoutY(526.0); 
                }), 
                new KeyFrame(Duration.millis(104800), "", e -> { 
                    el.setLayoutX(1070.0); 
                    el.setLayoutY(531.0); 
                }), 
                new KeyFrame(Duration.millis(104900), "", e -> { 
                    el.setLayoutX(1087.0); 
                    el.setLayoutY(533.0); 
                }), 
                new KeyFrame(Duration.millis(105000), "", e -> { 
                    el.setLayoutX(1100.0); 
                    el.setLayoutY(533.0); 
                }), 
                new KeyFrame(Duration.millis(105100), "", e -> { 
                    el.setLayoutX(1111.0); 
                    el.setLayoutY(527.0); 
                }), 
                new KeyFrame(Duration.millis(105200), "", e -> { 
                    el.setLayoutX(1120.0); 
                    el.setLayoutY(517.0); 
                }), 
                new KeyFrame(Duration.millis(105300), "", e -> { 
                    el.setLayoutX(1128.0); 
                    el.setLayoutY(501.0); 
                }), 
                new KeyFrame(Duration.millis(105400), "", e -> { 
                    el.setLayoutX(1136.0); 
                    el.setLayoutY(473.0); 
                }), 
                new KeyFrame(Duration.millis(105500), "", e -> { 
                    el.setLayoutX(1141.0); 
                    el.setLayoutY(442.0); 
                }), 
                new KeyFrame(Duration.millis(105600), "", e -> { 
                    el.setLayoutX(1144.0); 
                    el.setLayoutY(406.0); 
                }), 
                new KeyFrame(Duration.millis(105700), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(367.0); 
                }), 
                new KeyFrame(Duration.millis(105800), "", e -> { 
                    el.setLayoutX(1148.0); 
                    el.setLayoutY(326.0); 
                }), 
                new KeyFrame(Duration.millis(105900), "", e -> { 
                    el.setLayoutX(1148.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(106000), "", e -> { 
                    el.setLayoutX(1148.0); 
                    el.setLayoutY(250.0); 
                }), 
                new KeyFrame(Duration.millis(106100), "", e -> { 
                    el.setLayoutX(1145.0); 
                    el.setLayoutY(214.0); 
                }), 
                new KeyFrame(Duration.millis(106200), "", e -> { 
                    el.setLayoutX(1138.0); 
                    el.setLayoutY(177.0); 
                }), 
                new KeyFrame(Duration.millis(106300), "", e -> { 
                    el.setLayoutX(1131.0); 
                    el.setLayoutY(142.0); 
                }), 
                new KeyFrame(Duration.millis(106400), "", e -> { 
                    el.setLayoutX(1123.0); 
                    el.setLayoutY(112.0); 
                }), 
                new KeyFrame(Duration.millis(106500), "", e -> { 
                    el.setLayoutX(1114.0); 
                    el.setLayoutY(89.0); 
                }), 
                new KeyFrame(Duration.millis(106600), "", e -> { 
                    el.setLayoutX(1106.0); 
                    el.setLayoutY(72.0); 
                }), 
                new KeyFrame(Duration.millis(106700), "", e -> { 
                    el.setLayoutX(1099.0); 
                    el.setLayoutY(60.0); 
                }), 
                new KeyFrame(Duration.millis(106800), "", e -> { 
                    el.setLayoutX(1093.0); 
                    el.setLayoutY(53.0); 
                }), 
                new KeyFrame(Duration.millis(106900), "", e -> { 
                    el.setLayoutX(1087.0); 
                    el.setLayoutY(48.0); 
                }), 
                new KeyFrame(Duration.millis(107000), "", e -> { 
                    el.setLayoutX(1081.0); 
                    el.setLayoutY(45.0); 
                }), 
                new KeyFrame(Duration.millis(107100), "", e -> { 
                    el.setLayoutX(1075.0); 
                    el.setLayoutY(44.0); 
                }), 
                new KeyFrame(Duration.millis(107200), "", e -> { 
                    el.setLayoutX(1071.0); 
                    el.setLayoutY(44.0); 
                }), 
                new KeyFrame(Duration.millis(107300), "", e -> { 
                    el.setLayoutX(1065.0); 
                    el.setLayoutY(44.0); 
                }), 
                new KeyFrame(Duration.millis(107400), "", e -> { 
                    el.setLayoutX(1061.0); 
                    el.setLayoutY(48.0); 
                }), 
                new KeyFrame(Duration.millis(107500), "", e -> { 
                    el.setLayoutX(1054.0); 
                    el.setLayoutY(53.0); 
                }), 
                new KeyFrame(Duration.millis(107600), "", e -> { 
                    el.setLayoutX(1048.0); 
                    el.setLayoutY(60.0); 
                }), 
                new KeyFrame(Duration.millis(107700), "", e -> { 
                    el.setLayoutX(1041.0); 
                    el.setLayoutY(70.0); 
                }), 
                new KeyFrame(Duration.millis(107800), "", e -> { 
                    el.setLayoutX(1035.0); 
                    el.setLayoutY(80.0); 
                }), 
                new KeyFrame(Duration.millis(107900), "", e -> { 
                    el.setLayoutX(1027.0); 
                    el.setLayoutY(93.0); 
                }), 
                new KeyFrame(Duration.millis(108000), "", e -> { 
                    el.setLayoutX(1014.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(108100), "", e -> { 
                    el.setLayoutX(1002.0); 
                    el.setLayoutY(136.0); 
                }), 
                new KeyFrame(Duration.millis(108200), "", e -> { 
                    el.setLayoutX(987.0); 
                    el.setLayoutY(158.0); 
                }), 
                new KeyFrame(Duration.millis(108300), "", e -> { 
                    el.setLayoutX(969.0); 
                    el.setLayoutY(180.0); 
                }), 
                new KeyFrame(Duration.millis(108400), "", e -> { 
                    el.setLayoutX(949.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(108500), "", e -> { 
                    el.setLayoutX(928.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(108600), "", e -> { 
                    el.setLayoutX(905.0); 
                    el.setLayoutY(245.0); 
                }), 
                new KeyFrame(Duration.millis(108700), "", e -> { 
                    el.setLayoutX(885.0); 
                    el.setLayoutY(264.0); 
                }), 
                new KeyFrame(Duration.millis(108800), "", e -> { 
                    el.setLayoutX(862.0); 
                    el.setLayoutY(282.0); 
                }), 
                new KeyFrame(Duration.millis(108900), "", e -> { 
                    el.setLayoutX(840.0); 
                    el.setLayoutY(298.0); 
                }), 
                new KeyFrame(Duration.millis(109000), "", e -> { 
                    el.setLayoutX(818.0); 
                    el.setLayoutY(313.0); 
                }), 
                new KeyFrame(Duration.millis(109100), "", e -> { 
                    el.setLayoutX(802.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(109200), "", e -> { 
                    el.setLayoutX(786.0); 
                    el.setLayoutY(334.0); 
                }), 
                new KeyFrame(Duration.millis(109300), "", e -> { 
                    el.setLayoutX(771.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(109400), "", e -> { 
                    el.setLayoutX(757.0); 
                    el.setLayoutY(345.0); 
                }), 
                new KeyFrame(Duration.millis(109500), "", e -> { 
                    el.setLayoutX(744.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(109600), "", e -> { 
                    el.setLayoutX(727.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(109700), "", e -> { 
                    el.setLayoutX(709.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(109800), "", e -> { 
                    el.setLayoutX(689.0); 
                    el.setLayoutY(354.0); 
                }), 
                new KeyFrame(Duration.millis(109900), "", e -> { 
                    el.setLayoutX(669.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(110000), "", e -> { 
                    el.setLayoutX(649.0); 
                    el.setLayoutY(357.0); 
                }), 
                new KeyFrame(Duration.millis(110100), "", e -> { 
                    el.setLayoutX(627.0); 
                    el.setLayoutY(358.0); 
                }), 
                new KeyFrame(Duration.millis(110200), "", e -> { 
                    el.setLayoutX(604.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(110300), "", e -> { 
                    el.setLayoutX(581.0); 
                    el.setLayoutY(361.0); 
                }), 
                new KeyFrame(Duration.millis(110400), "", e -> { 
                    el.setLayoutX(562.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(110500), "", e -> { 
                    el.setLayoutX(545.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(110600), "", e -> { 
                    el.setLayoutX(527.0); 
                    el.setLayoutY(359.0); 
                }), 
                new KeyFrame(Duration.millis(110700), "", e -> { 
                    el.setLayoutX(507.0); 
                    el.setLayoutY(352.0); 
                }), 
                new KeyFrame(Duration.millis(110800), "", e -> { 
                    el.setLayoutX(488.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(110900), "", e -> { 
                    el.setLayoutX(471.0); 
                    el.setLayoutY(326.0); 
                }), 
                new KeyFrame(Duration.millis(111000), "", e -> { 
                    el.setLayoutX(453.0); 
                    el.setLayoutY(308.0); 
                }), 
                new KeyFrame(Duration.millis(111100), "", e -> { 
                    el.setLayoutX(436.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(111200), "", e -> { 
                    el.setLayoutX(418.0); 
                    el.setLayoutY(266.0); 
                }), 
                new KeyFrame(Duration.millis(111300), "", e -> { 
                    el.setLayoutX(403.0); 
                    el.setLayoutY(246.0); 
                }), 
                new KeyFrame(Duration.millis(111400), "", e -> { 
                    el.setLayoutX(389.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(111500), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(205.0); 
                }), 
                new KeyFrame(Duration.millis(111600), "", e -> { 
                    el.setLayoutX(366.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(111700), "", e -> { 
                    el.setLayoutX(356.0); 
                    el.setLayoutY(169.0); 
                }), 
                new KeyFrame(Duration.millis(111800), "", e -> { 
                    el.setLayoutX(349.0); 
                    el.setLayoutY(156.0); 
                }), 
                new KeyFrame(Duration.millis(111900), "", e -> { 
                    el.setLayoutX(343.0); 
                    el.setLayoutY(146.0); 
                }), 
                new KeyFrame(Duration.millis(112000), "", e -> { 
                    el.setLayoutX(339.0); 
                    el.setLayoutY(138.0); 
                }), 
                new KeyFrame(Duration.millis(112100), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(133.0); 
                }), 
                new KeyFrame(Duration.millis(112200), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(112300), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(131.0); 
                }), 
                new KeyFrame(Duration.millis(112400), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(112500), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(126.0); 
                }), 
                new KeyFrame(Duration.millis(112600), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(112700), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(124.0); 
                }), 
                new KeyFrame(Duration.millis(112800), "", e -> { 
                    el.setLayoutX(337.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(112900), "", e -> { 
                    el.setLayoutX(336.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(113000), "", e -> { 
                    el.setLayoutX(335.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(113100), "", e -> { 
                    el.setLayoutX(335.0); 
                    el.setLayoutY(134.0); 
                }), 
                new KeyFrame(Duration.millis(113200), "", e -> { 
                    el.setLayoutX(334.0); 
                    el.setLayoutY(140.0); 
                }), 
                new KeyFrame(Duration.millis(113300), "", e -> { 
                    el.setLayoutX(333.0); 
                    el.setLayoutY(148.0); 
                }), 
                new KeyFrame(Duration.millis(113400), "", e -> { 
                    el.setLayoutX(332.0); 
                    el.setLayoutY(157.0); 
                }), 
                new KeyFrame(Duration.millis(113500), "", e -> { 
                    el.setLayoutX(331.0); 
                    el.setLayoutY(167.0); 
                }), 
                new KeyFrame(Duration.millis(113600), "", e -> { 
                    el.setLayoutX(329.0); 
                    el.setLayoutY(178.0); 
                }), 
                new KeyFrame(Duration.millis(113700), "", e -> { 
                    el.setLayoutX(326.0); 
                    el.setLayoutY(190.0); 
                }), 
                new KeyFrame(Duration.millis(113800), "", e -> { 
                    el.setLayoutX(321.0); 
                    el.setLayoutY(202.0); 
                }), 
                new KeyFrame(Duration.millis(113900), "", e -> { 
                    el.setLayoutX(315.0); 
                    el.setLayoutY(214.0); 
                }), 
                new KeyFrame(Duration.millis(114000), "", e -> { 
                    el.setLayoutX(309.0); 
                    el.setLayoutY(227.0); 
                }), 
                new KeyFrame(Duration.millis(114100), "", e -> { 
                    el.setLayoutX(298.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(114200), "", e -> { 
                    el.setLayoutX(285.0); 
                    el.setLayoutY(256.0); 
                }), 
                new KeyFrame(Duration.millis(114300), "", e -> { 
                    el.setLayoutX(267.0); 
                    el.setLayoutY(272.0); 
                }), 
                new KeyFrame(Duration.millis(114400), "", e -> { 
                    el.setLayoutX(245.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(114500), "", e -> { 
                    el.setLayoutX(218.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(114600), "", e -> { 
                    el.setLayoutX(192.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(114700), "", e -> { 
                    el.setLayoutX(169.0); 
                    el.setLayoutY(327.0); 
                }), 
                new KeyFrame(Duration.millis(114800), "", e -> { 
                    el.setLayoutX(149.0); 
                    el.setLayoutY(334.0); 
                }), 
                new KeyFrame(Duration.millis(114900), "", e -> { 
                    el.setLayoutX(133.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(115000), "", e -> { 
                    el.setLayoutX(121.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(115100), "", e -> { 
                    el.setLayoutX(112.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(115200), "", e -> { 
                    el.setLayoutX(106.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(115300), "", e -> { 
                    el.setLayoutX(102.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(115400), "", e -> { 
                    el.setLayoutX(97.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(115500), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(308.0); 
                }), 
                new KeyFrame(Duration.millis(115600), "", e -> { 
                    el.setLayoutX(91.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(115700), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(115800), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(115900), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(165.0); 
                }), 
                new KeyFrame(Duration.millis(116000), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(124.0); 
                }), 
                new KeyFrame(Duration.millis(116100), "", e -> { 
                    el.setLayoutX(94.0); 
                    el.setLayoutY(82.0); 
                }), 
                new KeyFrame(Duration.millis(116200), "", e -> { 
                    el.setLayoutX(99.0); 
                    el.setLayoutY(40.0); 
                }), 
                new KeyFrame(Duration.millis(116300), "", e -> { 
                    el.setLayoutX(306.0); 
                    el.setLayoutY(55.0); 
                }), 
                new KeyFrame(Duration.millis(116400), "", e -> { 
                    el.setLayoutX(418.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(116500), "", e -> { 
                    el.setLayoutX(440.0); 
                    el.setLayoutY(250.0); 
                }), 
                new KeyFrame(Duration.millis(116600), "", e -> { 
                    el.setLayoutX(462.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(116700), "", e -> { 
                    el.setLayoutX(489.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(116800), "", e -> { 
                    el.setLayoutX(520.0); 
                    el.setLayoutY(359.0); 
                }), 
                new KeyFrame(Duration.millis(116900), "", e -> { 
                    el.setLayoutX(553.0); 
                    el.setLayoutY(398.0); 
                }), 
                new KeyFrame(Duration.millis(117000), "", e -> { 
                    el.setLayoutX(588.0); 
                    el.setLayoutY(440.0); 
                }), 
                new KeyFrame(Duration.millis(117100), "", e -> { 
                    el.setLayoutX(623.0); 
                    el.setLayoutY(474.0); 
                }), 
                new KeyFrame(Duration.millis(117200), "", e -> { 
                    el.setLayoutX(654.0); 
                    el.setLayoutY(501.0); 
                }), 
                new KeyFrame(Duration.millis(117300), "", e -> { 
                    el.setLayoutX(678.0); 
                    el.setLayoutY(518.0); 
                }), 
                new KeyFrame(Duration.millis(117400), "", e -> { 
                    el.setLayoutX(698.0); 
                    el.setLayoutY(525.0); 
                }), 
                new KeyFrame(Duration.millis(117500), "", e -> { 
                    el.setLayoutX(711.0); 
                    el.setLayoutY(525.0); 
                }), 
                new KeyFrame(Duration.millis(117600), "", e -> { 
                    el.setLayoutX(723.0); 
                    el.setLayoutY(522.0); 
                }), 
                new KeyFrame(Duration.millis(117700), "", e -> { 
                    el.setLayoutX(735.0); 
                    el.setLayoutY(512.0); 
                }), 
                new KeyFrame(Duration.millis(117800), "", e -> { 
                    el.setLayoutX(747.0); 
                    el.setLayoutY(493.0); 
                }), 
                new KeyFrame(Duration.millis(117900), "", e -> { 
                    el.setLayoutX(758.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(118000), "", e -> { 
                    el.setLayoutX(764.0); 
                    el.setLayoutY(438.0); 
                }), 
                new KeyFrame(Duration.millis(118100), "", e -> { 
                    el.setLayoutX(769.0); 
                    el.setLayoutY(409.0); 
                }), 
                new KeyFrame(Duration.millis(118200), "", e -> { 
                    el.setLayoutX(772.0); 
                    el.setLayoutY(378.0); 
                }), 
                new KeyFrame(Duration.millis(118300), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(118400), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(318.0); 
                }), 
                new KeyFrame(Duration.millis(118500), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(118600), "", e -> { 
                    el.setLayoutX(772.0); 
                    el.setLayoutY(254.0); 
                }), 
                new KeyFrame(Duration.millis(118700), "", e -> { 
                    el.setLayoutX(767.0); 
                    el.setLayoutY(223.0); 
                }), 
                new KeyFrame(Duration.millis(118800), "", e -> { 
                    el.setLayoutX(760.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(118900), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(167.0); 
                }), 
                new KeyFrame(Duration.millis(119000), "", e -> { 
                    el.setLayoutX(750.0); 
                    el.setLayoutY(148.0); 
                }), 
                new KeyFrame(Duration.millis(119100), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(136.0); 
                }), 
                new KeyFrame(Duration.millis(119200), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(119300), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(136.0); 
                }), 
                new KeyFrame(Duration.millis(119400), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(138.0); 
                }), 
                new KeyFrame(Duration.millis(119500), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(144.0); 
                }), 
                new KeyFrame(Duration.millis(119600), "", e -> { 
                    el.setLayoutX(749.0); 
                    el.setLayoutY(153.0); 
                }), 
                new KeyFrame(Duration.millis(119700), "", e -> { 
                    el.setLayoutX(752.0); 
                    el.setLayoutY(164.0); 
                }), 
                new KeyFrame(Duration.millis(119800), "", e -> { 
                    el.setLayoutX(753.0); 
                    el.setLayoutY(176.0); 
                }), 
                new KeyFrame(Duration.millis(119900), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(189.0); 
                }), 
                new KeyFrame(Duration.millis(120000), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(202.0); 
                }), 
                new KeyFrame(Duration.millis(120100), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(214.0); 
                }), 
                new KeyFrame(Duration.millis(120200), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(225.0); 
                }), 
                new KeyFrame(Duration.millis(120300), "", e -> { 
                    el.setLayoutX(751.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(120400), "", e -> { 
                    el.setLayoutX(747.0); 
                    el.setLayoutY(247.0); 
                }), 
                new KeyFrame(Duration.millis(120500), "", e -> { 
                    el.setLayoutX(742.0); 
                    el.setLayoutY(257.0); 
                }), 
                new KeyFrame(Duration.millis(120600), "", e -> { 
                    el.setLayoutX(733.0); 
                    el.setLayoutY(266.0); 
                }), 
                new KeyFrame(Duration.millis(120700), "", e -> { 
                    el.setLayoutX(722.0); 
                    el.setLayoutY(272.0); 
                }), 
                new KeyFrame(Duration.millis(120800), "", e -> { 
                    el.setLayoutX(704.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(120900), "", e -> { 
                    el.setLayoutX(680.0); 
                    el.setLayoutY(283.0); 
                }), 
                new KeyFrame(Duration.millis(121000), "", e -> { 
                    el.setLayoutX(649.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(121100), "", e -> { 
                    el.setLayoutX(614.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(121200), "", e -> { 
                    el.setLayoutX(572.0); 
                    el.setLayoutY(278.0); 
                }), 
                new KeyFrame(Duration.millis(121300), "", e -> { 
                    el.setLayoutX(523.0); 
                    el.setLayoutY(267.0); 
                }), 
                new KeyFrame(Duration.millis(121400), "", e -> { 
                    el.setLayoutX(472.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(121500), "", e -> { 
                    el.setLayoutX(418.0); 
                    el.setLayoutY(233.0); 
                }), 
                new KeyFrame(Duration.millis(121600), "", e -> { 
                    el.setLayoutX(363.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(121700), "", e -> { 
                    el.setLayoutX(311.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(121800), "", e -> { 
                    el.setLayoutX(261.0); 
                    el.setLayoutY(173.0); 
                }), 
                new KeyFrame(Duration.millis(121900), "", e -> { 
                    el.setLayoutX(222.0); 
                    el.setLayoutY(156.0); 
                }), 
                new KeyFrame(Duration.millis(122000), "", e -> { 
                    el.setLayoutX(193.0); 
                    el.setLayoutY(141.0); 
                }), 
                new KeyFrame(Duration.millis(122100), "", e -> { 
                    el.setLayoutX(177.0); 
                    el.setLayoutY(131.0); 
                }), 
                new KeyFrame(Duration.millis(122200), "", e -> { 
                    el.setLayoutX(168.0); 
                    el.setLayoutY(123.0); 
                }), 
                new KeyFrame(Duration.millis(122300), "", e -> { 
                    el.setLayoutX(162.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(122400), "", e -> { 
                    el.setLayoutX(159.0); 
                    el.setLayoutY(104.0); 
                }), 
                new KeyFrame(Duration.millis(122500), "", e -> { 
                    el.setLayoutX(158.0); 
                    el.setLayoutY(93.0); 
                }), 
                new KeyFrame(Duration.millis(122600), "", e -> { 
                    el.setLayoutX(160.0); 
                    el.setLayoutY(78.0); 
                }), 
                new KeyFrame(Duration.millis(122700), "", e -> { 
                    el.setLayoutX(169.0); 
                    el.setLayoutY(58.0); 
                }), 
                new KeyFrame(Duration.millis(122800), "", e -> { 
                    el.setLayoutX(180.0); 
                    el.setLayoutY(39.0); 
                }), 
                new KeyFrame(Duration.millis(122900), "", e -> { 
                    el.setLayoutX(353.0); 
                    el.setLayoutY(33.0); 
                }), 
                new KeyFrame(Duration.millis(123000), "", e -> { 
                    el.setLayoutX(406.0); 
                    el.setLayoutY(59.0); 
                }), 
                new KeyFrame(Duration.millis(123100), "", e -> { 
                    el.setLayoutX(459.0); 
                    el.setLayoutY(92.0); 
                }), 
                new KeyFrame(Duration.millis(123200), "", e -> { 
                    el.setLayoutX(513.0); 
                    el.setLayoutY(130.0); 
                }), 
                new KeyFrame(Duration.millis(123300), "", e -> { 
                    el.setLayoutX(566.0); 
                    el.setLayoutY(171.0); 
                }), 
                new KeyFrame(Duration.millis(123400), "", e -> { 
                    el.setLayoutX(618.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(123500), "", e -> { 
                    el.setLayoutX(668.0); 
                    el.setLayoutY(259.0); 
                }), 
                new KeyFrame(Duration.millis(123600), "", e -> { 
                    el.setLayoutX(717.0); 
                    el.setLayoutY(298.0); 
                }), 
                new KeyFrame(Duration.millis(123700), "", e -> { 
                    el.setLayoutX(766.0); 
                    el.setLayoutY(330.0); 
                }), 
                new KeyFrame(Duration.millis(123800), "", e -> { 
                    el.setLayoutX(808.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(123900), "", e -> { 
                    el.setLayoutX(846.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(124000), "", e -> { 
                    el.setLayoutX(873.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(124100), "", e -> { 
                    el.setLayoutX(897.0); 
                    el.setLayoutY(359.0); 
                }), 
                new KeyFrame(Duration.millis(124200), "", e -> { 
                    el.setLayoutX(917.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(124300), "", e -> { 
                    el.setLayoutX(935.0); 
                    el.setLayoutY(334.0); 
                }), 
                new KeyFrame(Duration.millis(124400), "", e -> { 
                    el.setLayoutX(949.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(124500), "", e -> { 
                    el.setLayoutX(961.0); 
                    el.setLayoutY(298.0); 
                }), 
                new KeyFrame(Duration.millis(124600), "", e -> { 
                    el.setLayoutX(969.0); 
                    el.setLayoutY(278.0); 
                }), 
                new KeyFrame(Duration.millis(124700), "", e -> { 
                    el.setLayoutX(976.0); 
                    el.setLayoutY(253.0); 
                }), 
                new KeyFrame(Duration.millis(124800), "", e -> { 
                    el.setLayoutX(980.0); 
                    el.setLayoutY(230.0); 
                }), 
                new KeyFrame(Duration.millis(124900), "", e -> { 
                    el.setLayoutX(981.0); 
                    el.setLayoutY(205.0); 
                }), 
                new KeyFrame(Duration.millis(125000), "", e -> { 
                    el.setLayoutX(983.0); 
                    el.setLayoutY(177.0); 
                }), 
                new KeyFrame(Duration.millis(125100), "", e -> { 
                    el.setLayoutX(983.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(125200), "", e -> { 
                    el.setLayoutX(981.0); 
                    el.setLayoutY(126.0); 
                }), 
                new KeyFrame(Duration.millis(125300), "", e -> { 
                    el.setLayoutX(977.0); 
                    el.setLayoutY(108.0); 
                }), 
                new KeyFrame(Duration.millis(125400), "", e -> { 
                    el.setLayoutX(974.0); 
                    el.setLayoutY(96.0); 
                }), 
                new KeyFrame(Duration.millis(125500), "", e -> { 
                    el.setLayoutX(970.0); 
                    el.setLayoutY(87.0); 
                }), 
                new KeyFrame(Duration.millis(125600), "", e -> { 
                    el.setLayoutX(965.0); 
                    el.setLayoutY(83.0); 
                }), 
                new KeyFrame(Duration.millis(125700), "", e -> { 
                    el.setLayoutX(958.0); 
                    el.setLayoutY(82.0); 
                }), 
                new KeyFrame(Duration.millis(125800), "", e -> { 
                    el.setLayoutX(949.0); 
                    el.setLayoutY(84.0); 
                }), 
                new KeyFrame(Duration.millis(125900), "", e -> { 
                    el.setLayoutX(934.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(126000), "", e -> { 
                    el.setLayoutX(909.0); 
                    el.setLayoutY(117.0); 
                }), 
                new KeyFrame(Duration.millis(126100), "", e -> { 
                    el.setLayoutX(880.0); 
                    el.setLayoutY(141.0); 
                }), 
                new KeyFrame(Duration.millis(126200), "", e -> { 
                    el.setLayoutX(846.0); 
                    el.setLayoutY(174.0); 
                }), 
                new KeyFrame(Duration.millis(126300), "", e -> { 
                    el.setLayoutX(810.0); 
                    el.setLayoutY(206.0); 
                }), 
                new KeyFrame(Duration.millis(126400), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(239.0); 
                }), 
                new KeyFrame(Duration.millis(126500), "", e -> { 
                    el.setLayoutX(735.0); 
                    el.setLayoutY(270.0); 
                }), 
                new KeyFrame(Duration.millis(126600), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(301.0); 
                }), 
                new KeyFrame(Duration.millis(126700), "", e -> { 
                    el.setLayoutX(648.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(126800), "", e -> { 
                    el.setLayoutX(600.0); 
                    el.setLayoutY(357.0); 
                }), 
                new KeyFrame(Duration.millis(126900), "", e -> { 
                    el.setLayoutX(550.0); 
                    el.setLayoutY(381.0); 
                }), 
                new KeyFrame(Duration.millis(127000), "", e -> { 
                    el.setLayoutX(500.0); 
                    el.setLayoutY(401.0); 
                }), 
                new KeyFrame(Duration.millis(127100), "", e -> { 
                    el.setLayoutX(453.0); 
                    el.setLayoutY(420.0); 
                }), 
                new KeyFrame(Duration.millis(127200), "", e -> { 
                    el.setLayoutX(409.0); 
                    el.setLayoutY(438.0); 
                }), 
                new KeyFrame(Duration.millis(127300), "", e -> { 
                    el.setLayoutX(373.0); 
                    el.setLayoutY(450.0); 
                }), 
                new KeyFrame(Duration.millis(127400), "", e -> { 
                    el.setLayoutX(348.0); 
                    el.setLayoutY(460.0); 
                }), 
                new KeyFrame(Duration.millis(127500), "", e -> { 
                    el.setLayoutX(332.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(127600), "", e -> { 
                    el.setLayoutX(321.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(127700), "", e -> { 
                    el.setLayoutX(312.0); 
                    el.setLayoutY(469.0); 
                }), 
                new KeyFrame(Duration.millis(127800), "", e -> { 
                    el.setLayoutX(307.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(127900), "", e -> { 
                    el.setLayoutX(306.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(128000), "", e -> { 
                    el.setLayoutX(303.0); 
                    el.setLayoutY(469.0); 
                }), 
                new KeyFrame(Duration.millis(128100), "", e -> { 
                    el.setLayoutX(302.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(128200), "", e -> { 
                    el.setLayoutX(299.0); 
                    el.setLayoutY(463.0); 
                }), 
                new KeyFrame(Duration.millis(128300), "", e -> { 
                    el.setLayoutX(298.0); 
                    el.setLayoutY(460.0); 
                }), 
                new KeyFrame(Duration.millis(128400), "", e -> { 
                    el.setLayoutX(295.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(128500), "", e -> { 
                    el.setLayoutX(293.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(128600), "", e -> { 
                    el.setLayoutX(291.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(128700), "", e -> { 
                    el.setLayoutX(287.0); 
                    el.setLayoutY(448.0); 
                }), 
                new KeyFrame(Duration.millis(128800), "", e -> { 
                    el.setLayoutX(282.0); 
                    el.setLayoutY(445.0); 
                }), 
                new KeyFrame(Duration.millis(128900), "", e -> { 
                    el.setLayoutX(279.0); 
                    el.setLayoutY(445.0); 
                }), 
                new KeyFrame(Duration.millis(129000), "", e -> { 
                    el.setLayoutX(276.0); 
                    el.setLayoutY(443.0); 
                }), 
                new KeyFrame(Duration.millis(129100), "", e -> { 
                    el.setLayoutX(274.0); 
                    el.setLayoutY(442.0); 
                }), 
                new KeyFrame(Duration.millis(129200), "", e -> { 
                    el.setLayoutX(268.0); 
                    el.setLayoutY(441.0); 
                }), 
                new KeyFrame(Duration.millis(129300), "", e -> { 
                    el.setLayoutX(258.0); 
                    el.setLayoutY(439.0); 
                }), 
                new KeyFrame(Duration.millis(129400), "", e -> { 
                    el.setLayoutX(239.0); 
                    el.setLayoutY(438.0); 
                }), 
                new KeyFrame(Duration.millis(129500), "", e -> { 
                    el.setLayoutX(206.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(129600), "", e -> { 
                    el.setLayoutX(162.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(129700), "", e -> { 
                    el.setLayoutX(114.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(129800), "", e -> { 
                    el.setLayoutX(60.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(129900), "", e -> { 
                    el.setLayoutX(5.0); 
                    el.setLayoutY(437.0); 
                })); 
        tm.setCycleCount(Animation.INDEFINITE); 
        tm.play(); 
    } 
 
    public void randomMv5(Label el) { 
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(130000), "", e -> { 
            el.setLayoutX(28.0); 
            el.setLayoutY(597.0); 
        }), 
                new KeyFrame(Duration.millis(130100), "", e -> { 
                    el.setLayoutX(37.0); 
                    el.setLayoutY(589.0); 
                }), 
                new KeyFrame(Duration.millis(130200), "", e -> { 
                    el.setLayoutX(47.0); 
                    el.setLayoutY(580.0); 
                }), 
                new KeyFrame(Duration.millis(130300), "", e -> { 
                    el.setLayoutX(57.0); 
                    el.setLayoutY(572.0); 
                }), 
                new KeyFrame(Duration.millis(130400), "", e -> { 
                    el.setLayoutX(67.0); 
                    el.setLayoutY(562.0); 
                }), 
                new KeyFrame(Duration.millis(130500), "", e -> { 
                    el.setLayoutX(76.0); 
                    el.setLayoutY(552.0); 
                }), 
                new KeyFrame(Duration.millis(130600), "", e -> { 
                    el.setLayoutX(84.0); 
                    el.setLayoutY(542.0); 
                }), 
                new KeyFrame(Duration.millis(130700), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(529.0); 
                }), 
                new KeyFrame(Duration.millis(130800), "", e -> { 
                    el.setLayoutX(99.0); 
                    el.setLayoutY(512.0); 
                }), 
                new KeyFrame(Duration.millis(130900), "", e -> { 
                    el.setLayoutX(105.0); 
                    el.setLayoutY(494.0); 
                }), 
                new KeyFrame(Duration.millis(131000), "", e -> { 
                    el.setLayoutX(111.0); 
                    el.setLayoutY(480.0); 
                }), 
                new KeyFrame(Duration.millis(131100), "", e -> { 
                    el.setLayoutX(118.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(131200), "", e -> { 
                    el.setLayoutX(123.0); 
                    el.setLayoutY(448.0); 
                }), 
                new KeyFrame(Duration.millis(131300), "", e -> { 
                    el.setLayoutX(126.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(131400), "", e -> { 
                    el.setLayoutX(130.0); 
                    el.setLayoutY(419.0); 
                }), 
                new KeyFrame(Duration.millis(131500), "", e -> { 
                    el.setLayoutX(132.0); 
                    el.setLayoutY(407.0); 
                }), 
                new KeyFrame(Duration.millis(131600), "", e -> { 
                    el.setLayoutX(134.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(131700), "", e -> { 
                    el.setLayoutX(136.0); 
                    el.setLayoutY(382.0); 
                }), 
                new KeyFrame(Duration.millis(131800), "", e -> { 
                    el.setLayoutX(139.0); 
                    el.setLayoutY(371.0); 
                }), 
                new KeyFrame(Duration.millis(131900), "", e -> { 
                    el.setLayoutX(141.0); 
                    el.setLayoutY(361.0); 
                }), 
                new KeyFrame(Duration.millis(132000), "", e -> { 
                    el.setLayoutX(143.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(132100), "", e -> { 
                    el.setLayoutX(144.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(132200), "", e -> { 
                    el.setLayoutX(146.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(132300), "", e -> { 
                    el.setLayoutX(147.0); 
                    el.setLayoutY(307.0); 
                }), 
                new KeyFrame(Duration.millis(132400), "", e -> { 
                    el.setLayoutX(149.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(132500), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(272.0); 
                }), 
                new KeyFrame(Duration.millis(132600), "", e -> { 
                    el.setLayoutX(151.0); 
                    el.setLayoutY(255.0); 
                }), 
                new KeyFrame(Duration.millis(132700), "", e -> { 
                    el.setLayoutX(154.0); 
                    el.setLayoutY(240.0); 
                }), 
                new KeyFrame(Duration.millis(132800), "", e -> { 
                    el.setLayoutX(157.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(132900), "", e -> { 
                    el.setLayoutX(159.0); 
                    el.setLayoutY(211.0); 
                }), 
                new KeyFrame(Duration.millis(133000), "", e -> { 
                    el.setLayoutX(162.0); 
                    el.setLayoutY(199.0); 
                }), 
                new KeyFrame(Duration.millis(133100), "", e -> { 
                    el.setLayoutX(165.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(133200), "", e -> { 
                    el.setLayoutX(167.0); 
                    el.setLayoutY(179.0); 
                }), 
                new KeyFrame(Duration.millis(133300), "", e -> { 
                    el.setLayoutX(169.0); 
                    el.setLayoutY(170.0); 
                }), 
                new KeyFrame(Duration.millis(133400), "", e -> { 
                    el.setLayoutX(171.0); 
                    el.setLayoutY(162.0); 
                }), 
                new KeyFrame(Duration.millis(133500), "", e -> { 
                    el.setLayoutX(174.0); 
                    el.setLayoutY(155.0); 
                }), 
                new KeyFrame(Duration.millis(133600), "", e -> { 
                    el.setLayoutX(176.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(133700), "", e -> { 
                    el.setLayoutX(177.0); 
                    el.setLayoutY(144.0); 
                }), 
                new KeyFrame(Duration.millis(133800), "", e -> { 
                    el.setLayoutX(179.0); 
                    el.setLayoutY(138.0); 
                }), 
                new KeyFrame(Duration.millis(133900), "", e -> { 
                    el.setLayoutX(180.0); 
                    el.setLayoutY(134.0); 
                }), 
                new KeyFrame(Duration.millis(134000), "", e -> { 
                    el.setLayoutX(181.0); 
                    el.setLayoutY(129.0); 
                }), 
                new KeyFrame(Duration.millis(134100), "", e -> { 
                    el.setLayoutX(182.0); 
                    el.setLayoutY(126.0); 
                }), 
                new KeyFrame(Duration.millis(134200), "", e -> { 
                    el.setLayoutX(182.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(134300), "", e -> { 
                    el.setLayoutX(182.0); 
                    el.setLayoutY(120.0); 
                }), 
                new KeyFrame(Duration.millis(134400), "", e -> { 
                    el.setLayoutX(183.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(134500), "", e -> { 
                    el.setLayoutX(184.0); 
                    el.setLayoutY(117.0); 
                }), 
                new KeyFrame(Duration.millis(134600), "", e -> { 
                    el.setLayoutX(185.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(134700), "", e -> { 
                    el.setLayoutX(185.0); 
                    el.setLayoutY(115.0); 
                }), 
                new KeyFrame(Duration.millis(134800), "", e -> { 
                    el.setLayoutX(186.0); 
                    el.setLayoutY(117.0); 
                }), 
                new KeyFrame(Duration.millis(134900), "", e -> { 
                    el.setLayoutX(187.0); 
                    el.setLayoutY(117.0); 
                }), 
                new KeyFrame(Duration.millis(135000), "", e -> { 
                    el.setLayoutX(189.0); 
                    el.setLayoutY(125.0); 
                }), 
                new KeyFrame(Duration.millis(135100), "", e -> { 
                    el.setLayoutX(190.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(135200), "", e -> { 
                    el.setLayoutX(192.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(135300), "", e -> { 
                    el.setLayoutX(196.0); 
                    el.setLayoutY(141.0); 
                }), 
                new KeyFrame(Duration.millis(135400), "", e -> { 
                    el.setLayoutX(202.0); 
                    el.setLayoutY(152.0); 
                }), 
                new KeyFrame(Duration.millis(135500), "", e -> { 
                    el.setLayoutX(209.0); 
                    el.setLayoutY(166.0); 
                }), 
                new KeyFrame(Duration.millis(135600), "", e -> { 
                    el.setLayoutX(219.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(135700), "", e -> { 
                    el.setLayoutX(234.0); 
                    el.setLayoutY(211.0); 
                }), 
                new KeyFrame(Duration.millis(135800), "", e -> { 
                    el.setLayoutX(249.0); 
                    el.setLayoutY(238.0); 
                }), 
                new KeyFrame(Duration.millis(135900), "", e -> { 
                    el.setLayoutX(266.0); 
                    el.setLayoutY(270.0); 
                }), 
                new KeyFrame(Duration.millis(136000), "", e -> { 
                    el.setLayoutX(284.0); 
                    el.setLayoutY(301.0); 
                }), 
                new KeyFrame(Duration.millis(136100), "", e -> { 
                    el.setLayoutX(302.0); 
                    el.setLayoutY(333.0); 
                }), 
                new KeyFrame(Duration.millis(136200), "", e -> { 
                    el.setLayoutX(318.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(136300), "", e -> { 
                    el.setLayoutX(338.0); 
                    el.setLayoutY(390.0); 
                }), 
                new KeyFrame(Duration.millis(136400), "", e -> { 
                    el.setLayoutX(356.0); 
                    el.setLayoutY(417.0); 
                }), 
                new KeyFrame(Duration.millis(136500), "", e -> { 
                    el.setLayoutX(377.0); 
                    el.setLayoutY(445.0); 
                }), 
                new KeyFrame(Duration.millis(136600), "", e -> { 
                    el.setLayoutX(395.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(136700), "", e -> { 
                    el.setLayoutX(412.0); 
                    el.setLayoutY(490.0); 
                }), 
                new KeyFrame(Duration.millis(136800), "", e -> { 
                    el.setLayoutX(426.0); 
                    el.setLayoutY(505.0); 
                }), 
                new KeyFrame(Duration.millis(136900), "", e -> { 
                    el.setLayoutX(436.0); 
                    el.setLayoutY(514.0); 
                }), 
                new KeyFrame(Duration.millis(137000), "", e -> { 
                    el.setLayoutX(445.0); 
                    el.setLayoutY(520.0); 
                }), 
                new KeyFrame(Duration.millis(137100), "", e -> { 
                    el.setLayoutX(453.0); 
                    el.setLayoutY(524.0); 
                }), 
                new KeyFrame(Duration.millis(137200), "", e -> { 
                    el.setLayoutX(462.0); 
                    el.setLayoutY(526.0); 
                }), 
                new KeyFrame(Duration.millis(137300), "", e -> { 
                    el.setLayoutX(470.0); 
                    el.setLayoutY(529.0); 
                }), 
                new KeyFrame(Duration.millis(137400), "", e -> { 
                    el.setLayoutX(478.0); 
                    el.setLayoutY(530.0); 
                }), 
                new KeyFrame(Duration.millis(137500), "", e -> { 
                    el.setLayoutX(486.0); 
                    el.setLayoutY(530.0); 
                }), 
                new KeyFrame(Duration.millis(137600), "", e -> { 
                    el.setLayoutX(495.0); 
                    el.setLayoutY(530.0); 
                }), 
                new KeyFrame(Duration.millis(137700), "", e -> { 
                    el.setLayoutX(505.0); 
                    el.setLayoutY(530.0); 
                }), 
                new KeyFrame(Duration.millis(137800), "", e -> { 
                    el.setLayoutX(514.0); 
                    el.setLayoutY(527.0); 
                }), 
                new KeyFrame(Duration.millis(137900), "", e -> { 
                    el.setLayoutX(525.0); 
                    el.setLayoutY(523.0); 
                }), 
                new KeyFrame(Duration.millis(138000), "", e -> { 
                    el.setLayoutX(538.0); 
                    el.setLayoutY(517.0); 
                }), 
                new KeyFrame(Duration.millis(138100), "", e -> { 
                    el.setLayoutX(552.0); 
                    el.setLayoutY(510.0); 
                }), 
                new KeyFrame(Duration.millis(138200), "", e -> { 
                    el.setLayoutX(566.0); 
                    el.setLayoutY(500.0); 
                }), 
                new KeyFrame(Duration.millis(138300), "", e -> { 
                    el.setLayoutX(580.0); 
                    el.setLayoutY(490.0); 
                }), 
                new KeyFrame(Duration.millis(138400), "", e -> { 
                    el.setLayoutX(595.0); 
                    el.setLayoutY(478.0); 
                }), 
                new KeyFrame(Duration.millis(138500), "", e -> { 
                    el.setLayoutX(607.0); 
                    el.setLayoutY(467.0); 
                }), 
                new KeyFrame(Duration.millis(138600), "", e -> { 
                    el.setLayoutX(621.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(138700), "", e -> { 
                    el.setLayoutX(634.0); 
                    el.setLayoutY(432.0); 
                }), 
                new KeyFrame(Duration.millis(138800), "", e -> { 
                    el.setLayoutX(645.0); 
                    el.setLayoutY(412.0); 
                }), 
                new KeyFrame(Duration.millis(138900), "", e -> { 
                    el.setLayoutX(654.0); 
                    el.setLayoutY(389.0); 
                }), 
                new KeyFrame(Duration.millis(139000), "", e -> { 
                    el.setLayoutX(661.0); 
                    el.setLayoutY(364.0); 
                }), 
                new KeyFrame(Duration.millis(139100), "", e -> { 
                    el.setLayoutX(669.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(139200), "", e -> { 
                    el.setLayoutX(675.0); 
                    el.setLayoutY(315.0); 
                }), 
                new KeyFrame(Duration.millis(139300), "", e -> { 
                    el.setLayoutX(680.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(139400), "", e -> { 
                    el.setLayoutX(685.0); 
                    el.setLayoutY(263.0); 
                }), 
                new KeyFrame(Duration.millis(139500), "", e -> { 
                    el.setLayoutX(687.0); 
                    el.setLayoutY(240.0); 
                }), 
                new KeyFrame(Duration.millis(139600), "", e -> { 
                    el.setLayoutX(689.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(139700), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(200.0); 
                }), 
                new KeyFrame(Duration.millis(139800), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(183.0); 
                }), 
                new KeyFrame(Duration.millis(139900), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(169.0); 
                }), 
                new KeyFrame(Duration.millis(140000), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(157.0); 
                }), 
                new KeyFrame(Duration.millis(140100), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(148.0); 
                }), 
                new KeyFrame(Duration.millis(140200), "", e -> { 
                    el.setLayoutX(690.0); 
                    el.setLayoutY(141.0); 
                }), 
                new KeyFrame(Duration.millis(140300), "", e -> { 
                    el.setLayoutX(691.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(140400), "", e -> { 
                    el.setLayoutX(692.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(140500), "", e -> { 
                    el.setLayoutX(692.0); 
                    el.setLayoutY(133.0); 
                }), 
                new KeyFrame(Duration.millis(140600), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(133.0); 
                }), 
                new KeyFrame(Duration.millis(140700), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(140800), "", e -> { 
                    el.setLayoutX(695.0); 
                    el.setLayoutY(132.0); 
                }), 
                new KeyFrame(Duration.millis(140900), "", e -> { 
                    el.setLayoutX(698.0); 
                    el.setLayoutY(134.0); 
                }), 
                new KeyFrame(Duration.millis(141000), "", e -> { 
                    el.setLayoutX(699.0); 
                    el.setLayoutY(138.0); 
                }), 
                new KeyFrame(Duration.millis(141100), "", e -> { 
                    el.setLayoutX(699.0); 
                    el.setLayoutY(140.0); 
                }), 
                new KeyFrame(Duration.millis(141200), "", e -> { 
                    el.setLayoutX(702.0); 
                    el.setLayoutY(145.0); 
                }), 
                new KeyFrame(Duration.millis(141300), "", e -> { 
                    el.setLayoutX(705.0); 
                    el.setLayoutY(153.0); 
                }), 
                new KeyFrame(Duration.millis(141400), "", e -> { 
                    el.setLayoutX(710.0); 
                    el.setLayoutY(166.0); 
                }), 
                new KeyFrame(Duration.millis(141500), "", e -> { 
                    el.setLayoutX(716.0); 
                    el.setLayoutY(180.0); 
                }), 
                new KeyFrame(Duration.millis(141600), "", e -> { 
                    el.setLayoutX(723.0); 
                    el.setLayoutY(200.0); 
                }), 
                new KeyFrame(Duration.millis(141700), "", e -> { 
                    el.setLayoutX(732.0); 
                    el.setLayoutY(222.0); 
                }), 
                new KeyFrame(Duration.millis(141800), "", e -> { 
                    el.setLayoutX(741.0); 
                    el.setLayoutY(245.0); 
                }), 
                new KeyFrame(Duration.millis(141900), "", e -> { 
                    el.setLayoutX(750.0); 
                    el.setLayoutY(268.0); 
                }), 
                new KeyFrame(Duration.millis(142000), "", e -> { 
                    el.setLayoutX(761.0); 
                    el.setLayoutY(291.0); 
                }), 
                new KeyFrame(Duration.millis(142100), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(142200), "", e -> { 
                    el.setLayoutX(787.0); 
                    el.setLayoutY(343.0); 
                }), 
                new KeyFrame(Duration.millis(142300), "", e -> { 
                    el.setLayoutX(799.0); 
                    el.setLayoutY(367.0); 
                }), 
                new KeyFrame(Duration.millis(142400), "", e -> { 
                    el.setLayoutX(814.0); 
                    el.setLayoutY(391.0); 
                }), 
                new KeyFrame(Duration.millis(142500), "", e -> { 
                    el.setLayoutX(825.0); 
                    el.setLayoutY(410.0); 
                }), 
                new KeyFrame(Duration.millis(142600), "", e -> { 
                    el.setLayoutX(837.0); 
                    el.setLayoutY(424.0); 
                }), 
                new KeyFrame(Duration.millis(142700), "", e -> { 
                    el.setLayoutX(847.0); 
                    el.setLayoutY(434.0); 
                }), 
                new KeyFrame(Duration.millis(142800), "", e -> { 
                    el.setLayoutX(856.0); 
                    el.setLayoutY(442.0); 
                }), 
                new KeyFrame(Duration.millis(142900), "", e -> { 
                    el.setLayoutX(865.0); 
                    el.setLayoutY(446.0); 
                }), 
                new KeyFrame(Duration.millis(143000), "", e -> { 
                    el.setLayoutX(874.0); 
                    el.setLayoutY(449.0); 
                }), 
                new KeyFrame(Duration.millis(143100), "", e -> { 
                    el.setLayoutX(883.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(143200), "", e -> { 
                    el.setLayoutX(891.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(143300), "", e -> { 
                    el.setLayoutX(899.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(143400), "", e -> { 
                    el.setLayoutX(908.0); 
                    el.setLayoutY(451.0); 
                }), 
                new KeyFrame(Duration.millis(143500), "", e -> { 
                    el.setLayoutX(918.0); 
                    el.setLayoutY(449.0); 
                }), 
                new KeyFrame(Duration.millis(143600), "", e -> { 
                    el.setLayoutX(927.0); 
                    el.setLayoutY(447.0); 
                }), 
                new KeyFrame(Duration.millis(143700), "", e -> { 
                    el.setLayoutX(938.0); 
                    el.setLayoutY(444.0); 
                }), 
                new KeyFrame(Duration.millis(143800), "", e -> { 
                    el.setLayoutX(948.0); 
                    el.setLayoutY(441.0); 
                }), 
                new KeyFrame(Duration.millis(143900), "", e -> { 
                    el.setLayoutX(958.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(144000), "", e -> { 
                    el.setLayoutX(968.0); 
                    el.setLayoutY(427.0); 
                }), 
                new KeyFrame(Duration.millis(144100), "", e -> { 
                    el.setLayoutX(978.0); 
                    el.setLayoutY(419.0); 
                }), 
                new KeyFrame(Duration.millis(144200), "", e -> { 
                    el.setLayoutX(988.0); 
                    el.setLayoutY(408.0); 
                }), 
                new KeyFrame(Duration.millis(144300), "", e -> { 
                    el.setLayoutX(997.0); 
                    el.setLayoutY(397.0); 
                }), 
                new KeyFrame(Duration.millis(144400), "", e -> { 
                    el.setLayoutX(1008.0); 
                    el.setLayoutY(380.0); 
                }), 
                new KeyFrame(Duration.millis(144500), "", e -> { 
                    el.setLayoutX(1018.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(144600), "", e -> { 
                    el.setLayoutX(1025.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(144700), "", e -> { 
                    el.setLayoutX(1030.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(144800), "", e -> { 
                    el.setLayoutX(1034.0); 
                    el.setLayoutY(293.0); 
                }), 
                new KeyFrame(Duration.millis(144900), "", e -> { 
                    el.setLayoutX(1037.0); 
                    el.setLayoutY(272.0); 
                }), 
                new KeyFrame(Duration.millis(145000), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(252.0); 
                }), 
                new KeyFrame(Duration.millis(145100), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(238.0); 
                }), 
                new KeyFrame(Duration.millis(145200), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(145300), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(145400), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(145500), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(206.0); 
                }), 
                new KeyFrame(Duration.millis(145600), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(204.0); 
                }), 
                new KeyFrame(Duration.millis(145700), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(202.0); 
                }), 
                new KeyFrame(Duration.millis(145800), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(200.0); 
                }), 
                new KeyFrame(Duration.millis(145900), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(198.0); 
                }), 
                new KeyFrame(Duration.millis(146000), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(197.0); 
                }), 
                new KeyFrame(Duration.millis(146100), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(195.0); 
                }), 
                new KeyFrame(Duration.millis(146200), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(146300), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(190.0); 
                }), 
                new KeyFrame(Duration.millis(146400), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(188.0); 
                }), 
                new KeyFrame(Duration.millis(146500), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(184.0); 
                }), 
                new KeyFrame(Duration.millis(146600), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(182.0); 
                }), 
                new KeyFrame(Duration.millis(146700), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(180.0); 
                }), 
                new KeyFrame(Duration.millis(146800), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(178.0); 
                }), 
                new KeyFrame(Duration.millis(146900), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(177.0); 
                }), 
                new KeyFrame(Duration.millis(147000), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(175.0); 
                }), 
                new KeyFrame(Duration.millis(147100), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(173.0); 
                }), 
                new KeyFrame(Duration.millis(147200), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(172.0); 
                }), 
                new KeyFrame(Duration.millis(147300), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(171.0); 
                }), 
                new KeyFrame(Duration.millis(147400), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(170.0); 
                }), 
                new KeyFrame(Duration.millis(147500), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(169.0); 
                }), 
                new KeyFrame(Duration.millis(147600), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(170.0); 
                }), 
                new KeyFrame(Duration.millis(147700), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(172.0); 
                }), 
                new KeyFrame(Duration.millis(147800), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(174.0); 
                }), 
                new KeyFrame(Duration.millis(147900), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(175.0); 
                }), 
                new KeyFrame(Duration.millis(148000), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(176.0); 
                }), 
                new KeyFrame(Duration.millis(148100), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(180.0); 
                }), 
                new KeyFrame(Duration.millis(148200), "", e -> { 
                    el.setLayoutX(1038.0); 
                    el.setLayoutY(183.0); 
                }), 
                new KeyFrame(Duration.millis(148300), "", e -> { 
                    el.setLayoutX(1039.0); 
                    el.setLayoutY(186.0); 
                }), 
                new KeyFrame(Duration.millis(148400), "", e -> { 
                    el.setLayoutX(1042.0); 
                    el.setLayoutY(191.0); 
                }), 
                new KeyFrame(Duration.millis(148500), "", e -> { 
                    el.setLayoutX(1045.0); 
                    el.setLayoutY(197.0); 
                }), 
                new KeyFrame(Duration.millis(148600), "", e -> { 
                    el.setLayoutX(1048.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(148700), "", e -> { 
                    el.setLayoutX(1049.0); 
                    el.setLayoutY(206.0); 
                }), 
                new KeyFrame(Duration.millis(148800), "", e -> { 
                    el.setLayoutX(1051.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(148900), "", e -> { 
                    el.setLayoutX(1054.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(149000), "", e -> { 
                    el.setLayoutX(1058.0); 
                    el.setLayoutY(230.0); 
                }), 
                new KeyFrame(Duration.millis(149100), "", e -> { 
                    el.setLayoutX(1062.0); 
                    el.setLayoutY(240.0); 
                }), 
                new KeyFrame(Duration.millis(149200), "", e -> { 
                    el.setLayoutX(1066.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(149300), "", e -> { 
                    el.setLayoutX(1070.0); 
                    el.setLayoutY(262.0); 
                }), 
                new KeyFrame(Duration.millis(149400), "", e -> { 
                    el.setLayoutX(1074.0); 
                    el.setLayoutY(273.0); 
                }), 
                new KeyFrame(Duration.millis(149500), "", e -> { 
                    el.setLayoutX(1078.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(149600), "", e -> { 
                    el.setLayoutX(1081.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(149700), "", e -> { 
                    el.setLayoutX(1084.0); 
                    el.setLayoutY(309.0); 
                }), 
                new KeyFrame(Duration.millis(149800), "", e -> { 
                    el.setLayoutX(1087.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(149900), "", e -> { 
                    el.setLayoutX(1089.0); 
                    el.setLayoutY(336.0); 
                }), 
                new KeyFrame(Duration.millis(150000), "", e -> { 
                    el.setLayoutX(1091.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(150100), "", e -> { 
                    el.setLayoutX(1094.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(150200), "", e -> { 
                    el.setLayoutX(1097.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(150300), "", e -> { 
                    el.setLayoutX(1100.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(150400), "", e -> { 
                    el.setLayoutX(1102.0); 
                    el.setLayoutY(398.0); 
                }), 
                new KeyFrame(Duration.millis(150500), "", e -> { 
                    el.setLayoutX(1104.0); 
                    el.setLayoutY(408.0); 
                }), 
                new KeyFrame(Duration.millis(150600), "", e -> { 
                    el.setLayoutX(1108.0); 
                    el.setLayoutY(417.0); 
                }), 
                new KeyFrame(Duration.millis(150700), "", e -> { 
                    el.setLayoutX(1110.0); 
                    el.setLayoutY(426.0); 
                }), 
                new KeyFrame(Duration.millis(150800), "", e -> { 
                    el.setLayoutX(1113.0); 
                    el.setLayoutY(434.0); 
                }), 
                new KeyFrame(Duration.millis(150900), "", e -> { 
                    el.setLayoutX(1115.0); 
                    el.setLayoutY(441.0); 
                }), 
                new KeyFrame(Duration.millis(151000), "", e -> { 
                    el.setLayoutX(1117.0); 
                    el.setLayoutY(448.0); 
                }), 
                new KeyFrame(Duration.millis(151100), "", e -> { 
                    el.setLayoutX(1119.0); 
                    el.setLayoutY(455.0); 
                }), 
                new KeyFrame(Duration.millis(151200), "", e -> { 
                    el.setLayoutX(1121.0); 
                    el.setLayoutY(461.0); 
                }), 
                new KeyFrame(Duration.millis(151300), "", e -> { 
                    el.setLayoutX(1121.0); 
                    el.setLayoutY(462.0); 
                }), 
                new KeyFrame(Duration.millis(151400), "", e -> { 
                    el.setLayoutX(1122.0); 
                    el.setLayoutY(462.0); 
                }), 
                new KeyFrame(Duration.millis(151500), "", e -> { 
                    el.setLayoutX(1124.0); 
                    el.setLayoutY(461.0); 
                }), 
                new KeyFrame(Duration.millis(151600), "", e -> { 
                    el.setLayoutX(1127.0); 
                    el.setLayoutY(456.0); 
                }), 
                new KeyFrame(Duration.millis(151700), "", e -> { 
                    el.setLayoutX(1128.0); 
                    el.setLayoutY(453.0); 
                }), 
                new KeyFrame(Duration.millis(151800), "", e -> { 
                    el.setLayoutX(1129.0); 
                    el.setLayoutY(451.0); 
                }), 
                new KeyFrame(Duration.millis(151900), "", e -> { 
                    el.setLayoutX(1130.0); 
                    el.setLayoutY(446.0); 
                }), 
                new KeyFrame(Duration.millis(152000), "", e -> { 
                    el.setLayoutX(1133.0); 
                    el.setLayoutY(437.0); 
                }), 
                new KeyFrame(Duration.millis(152100), "", e -> { 
                    el.setLayoutX(1135.0); 
                    el.setLayoutY(428.0); 
                }), 
                new KeyFrame(Duration.millis(152200), "", e -> { 
                    el.setLayoutX(1137.0); 
                    el.setLayoutY(415.0); 
                }), 
                new KeyFrame(Duration.millis(152300), "", e -> { 
                    el.setLayoutX(1139.0); 
                    el.setLayoutY(398.0); 
                }), 
                new KeyFrame(Duration.millis(152400), "", e -> { 
                    el.setLayoutX(1142.0); 
                    el.setLayoutY(380.0); 
                }), 
                new KeyFrame(Duration.millis(152500), "", e -> { 
                    el.setLayoutX(1143.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(152600), "", e -> { 
                    el.setLayoutX(1145.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(152700), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(312.0); 
                }), 
                new KeyFrame(Duration.millis(152800), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(152900), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(260.0); 
                }), 
                new KeyFrame(Duration.millis(153000), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(153100), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(211.0); 
                }), 
                new KeyFrame(Duration.millis(153200), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(189.0); 
                }), 
                new KeyFrame(Duration.millis(153300), "", e -> { 
                    el.setLayoutX(1144.0); 
                    el.setLayoutY(169.0); 
                }), 
                new KeyFrame(Duration.millis(153400), "", e -> { 
                    el.setLayoutX(1142.0); 
                    el.setLayoutY(151.0); 
                }), 
                new KeyFrame(Duration.millis(153500), "", e -> { 
                    el.setLayoutX(1138.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(153600), "", e -> { 
                    el.setLayoutX(1132.0); 
                    el.setLayoutY(120.0); 
                }), 
                new KeyFrame(Duration.millis(153700), "", e -> { 
                    el.setLayoutX(1124.0); 
                    el.setLayoutY(105.0); 
                }), 
                new KeyFrame(Duration.millis(153800), "", e -> { 
                    el.setLayoutX(1116.0); 
                    el.setLayoutY(91.0); 
                }), 
                new KeyFrame(Duration.millis(153900), "", e -> { 
                    el.setLayoutX(1105.0); 
                    el.setLayoutY(79.0); 
                }), 
                new KeyFrame(Duration.millis(154000), "", e -> { 
                    el.setLayoutX(1092.0); 
                    el.setLayoutY(66.0); 
                }), 
                new KeyFrame(Duration.millis(154100), "", e -> { 
                    el.setLayoutX(1080.0); 
                    el.setLayoutY(57.0); 
                }), 
                new KeyFrame(Duration.millis(154200), "", e -> { 
                    el.setLayoutX(1070.0); 
                    el.setLayoutY(49.0); 
                }), 
                new KeyFrame(Duration.millis(154300), "", e -> { 
                    el.setLayoutX(1061.0); 
                    el.setLayoutY(44.0); 
                }), 
                new KeyFrame(Duration.millis(154400), "", e -> { 
                    el.setLayoutX(1054.0); 
                    el.setLayoutY(39.0); 
                }), 
                new KeyFrame(Duration.millis(154500), "", e -> { 
                    el.setLayoutX(1048.0); 
                    el.setLayoutY(35.0); 
                }), 
                new KeyFrame(Duration.millis(154600), "", e -> { 
                    el.setLayoutX(1042.0); 
                    el.setLayoutY(30.0); 
                }), 
                new KeyFrame(Duration.millis(154700), "", e -> { 
                    el.setLayoutX(884.0); 
                    el.setLayoutY(30.0); 
                }), 
                new KeyFrame(Duration.millis(154800), "", e -> { 
                    el.setLayoutX(875.0); 
                    el.setLayoutY(39.0); 
                }), 
                new KeyFrame(Duration.millis(154900), "", e -> { 
                    el.setLayoutX(868.0); 
                    el.setLayoutY(49.0); 
                }), 
                new KeyFrame(Duration.millis(155000), "", e -> { 
                    el.setLayoutX(860.0); 
                    el.setLayoutY(60.0); 
                }), 
                new KeyFrame(Duration.millis(155100), "", e -> { 
                    el.setLayoutX(851.0); 
                    el.setLayoutY(73.0); 
                }), 
                new KeyFrame(Duration.millis(155200), "", e -> { 
                    el.setLayoutX(844.0); 
                    el.setLayoutY(86.0); 
                }), 
                new KeyFrame(Duration.millis(155300), "", e -> { 
                    el.setLayoutX(835.0); 
                    el.setLayoutY(102.0); 
                }), 
                new KeyFrame(Duration.millis(155400), "", e -> { 
                    el.setLayoutX(826.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(155500), "", e -> { 
                    el.setLayoutX(817.0); 
                    el.setLayoutY(133.0); 
                }), 
                new KeyFrame(Duration.millis(155600), "", e -> { 
                    el.setLayoutX(807.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(155700), "", e -> { 
                    el.setLayoutX(796.0); 
                    el.setLayoutY(166.0); 
                }), 
                new KeyFrame(Duration.millis(155800), "", e -> { 
                    el.setLayoutX(785.0); 
                    el.setLayoutY(182.0); 
                }), 
                new KeyFrame(Duration.millis(155900), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(199.0); 
                }), 
                new KeyFrame(Duration.millis(156000), "", e -> { 
                    el.setLayoutX(763.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(156100), "", e -> { 
                    el.setLayoutX(751.0); 
                    el.setLayoutY(225.0); 
                }), 
                new KeyFrame(Duration.millis(156200), "", e -> { 
                    el.setLayoutX(739.0); 
                    el.setLayoutY(237.0); 
                }), 
                new KeyFrame(Duration.millis(156300), "", e -> { 
                    el.setLayoutX(725.0); 
                    el.setLayoutY(248.0); 
                }), 
                new KeyFrame(Duration.millis(156400), "", e -> { 
                    el.setLayoutX(708.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(156500), "", e -> { 
                    el.setLayoutX(689.0); 
                    el.setLayoutY(273.0); 
                }), 
                new KeyFrame(Duration.millis(156600), "", e -> { 
                    el.setLayoutX(668.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(156700), "", e -> { 
                    el.setLayoutX(646.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(156800), "", e -> { 
                    el.setLayoutX(626.0); 
                    el.setLayoutY(307.0); 
                }), 
                new KeyFrame(Duration.millis(156900), "", e -> { 
                    el.setLayoutX(603.0); 
                    el.setLayoutY(318.0); 
                }), 
                new KeyFrame(Duration.millis(157000), "", e -> { 
                    el.setLayoutX(576.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(157100), "", e -> { 
                    el.setLayoutX(548.0); 
                    el.setLayoutY(343.0); 
                }), 
                new KeyFrame(Duration.millis(157200), "", e -> { 
                    el.setLayoutX(520.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(157300), "", e -> { 
                    el.setLayoutX(489.0); 
                    el.setLayoutY(370.0); 
                }), 
                new KeyFrame(Duration.millis(157400), "", e -> { 
                    el.setLayoutX(458.0); 
                    el.setLayoutY(382.0); 
                }), 
                new KeyFrame(Duration.millis(157500), "", e -> { 
                    el.setLayoutX(430.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(157600), "", e -> { 
                    el.setLayoutX(401.0); 
                    el.setLayoutY(407.0); 
                }), 
                new KeyFrame(Duration.millis(157700), "", e -> { 
                    el.setLayoutX(370.0); 
                    el.setLayoutY(423.0); 
                }), 
                new KeyFrame(Duration.millis(157800), "", e -> { 
                    el.setLayoutX(341.0); 
                    el.setLayoutY(438.0); 
                }), 
                new KeyFrame(Duration.millis(157900), "", e -> { 
                    el.setLayoutX(312.0); 
                    el.setLayoutY(452.0); 
                }), 
                new KeyFrame(Duration.millis(158000), "", e -> { 
                    el.setLayoutX(286.0); 
                    el.setLayoutY(465.0); 
                }), 
                new KeyFrame(Duration.millis(158100), "", e -> { 
                    el.setLayoutX(260.0); 
                    el.setLayoutY(478.0); 
                }), 
                new KeyFrame(Duration.millis(158200), "", e -> { 
                    el.setLayoutX(237.0); 
                    el.setLayoutY(488.0); 
                }), 
                new KeyFrame(Duration.millis(158300), "", e -> { 
                    el.setLayoutX(213.0); 
                    el.setLayoutY(501.0); 
                }), 
                new KeyFrame(Duration.millis(158400), "", e -> { 
                    el.setLayoutX(192.0); 
                    el.setLayoutY(513.0); 
                }), 
                new KeyFrame(Duration.millis(158500), "", e -> { 
                    el.setLayoutX(173.0); 
                    el.setLayoutY(525.0); 
                }), 
                new KeyFrame(Duration.millis(158600), "", e -> { 
                    el.setLayoutX(159.0); 
                    el.setLayoutY(535.0); 
                }), 
                new KeyFrame(Duration.millis(158700), "", e -> { 
                    el.setLayoutX(147.0); 
                    el.setLayoutY(544.0); 
                }), 
                new KeyFrame(Duration.millis(158800), "", e -> { 
                    el.setLayoutX(136.0); 
                    el.setLayoutY(554.0); 
                }), 
                new KeyFrame(Duration.millis(158900), "", e -> { 
                    el.setLayoutX(123.0); 
                    el.setLayoutY(565.0); 
                }), 
                new KeyFrame(Duration.millis(159000), "", e -> { 
                    el.setLayoutX(113.0); 
                    el.setLayoutY(575.0); 
                }), 
                new KeyFrame(Duration.millis(159100), "", e -> { 
                    el.setLayoutX(102.0); 
                    el.setLayoutY(587.0); 
                })); 
        tm.setCycleCount(Animation.INDEFINITE); 
        tm.play(); 
    } 
 
    public void randomMv4(Label el) { 
        Timeline tm = new Timeline(new KeyFrame(Duration.millis(159200), "", e -> { 
            el.setLayoutX(865.0); 
            el.setLayoutY(569.0); 
        }), 
                new KeyFrame(Duration.millis(159300), "", e -> { 
                    el.setLayoutX(815.0); 
                    el.setLayoutY(521.0); 
                }), 
                new KeyFrame(Duration.millis(159400), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(470.0); 
                }), 
                new KeyFrame(Duration.millis(159500), "", e -> { 
                    el.setLayoutX(706.0); 
                    el.setLayoutY(419.0); 
                }), 
                new KeyFrame(Duration.millis(159600), "", e -> { 
                    el.setLayoutX(659.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(159700), "", e -> { 
                    el.setLayoutX(622.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(159800), "", e -> { 
                    el.setLayoutX(594.0); 
                    el.setLayoutY(314.0); 
                }), 
                new KeyFrame(Duration.millis(159900), "", e -> { 
                    el.setLayoutX(574.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(160000), "", e -> { 
                    el.setLayoutX(561.0); 
                    el.setLayoutY(286.0); 
                }), 
                new KeyFrame(Duration.millis(160100), "", e -> { 
                    el.setLayoutX(554.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(160200), "", e -> { 
                    el.setLayoutX(550.0); 
                    el.setLayoutY(273.0); 
                }), 
                new KeyFrame(Duration.millis(160300), "", e -> { 
                    el.setLayoutX(547.0); 
                    el.setLayoutY(267.0); 
                }), 
                new KeyFrame(Duration.millis(160400), "", e -> { 
                    el.setLayoutX(547.0); 
                    el.setLayoutY(260.0); 
                }), 
                new KeyFrame(Duration.millis(160500), "", e -> { 
                    el.setLayoutX(547.0); 
                    el.setLayoutY(252.0); 
                }), 
                new KeyFrame(Duration.millis(160600), "", e -> { 
                    el.setLayoutX(551.0); 
                    el.setLayoutY(243.0); 
                }), 
                new KeyFrame(Duration.millis(160700), "", e -> { 
                    el.setLayoutX(557.0); 
                    el.setLayoutY(235.0); 
                }), 
                new KeyFrame(Duration.millis(160800), "", e -> { 
                    el.setLayoutX(564.0); 
                    el.setLayoutY(228.0); 
                }), 
                new KeyFrame(Duration.millis(160900), "", e -> { 
                    el.setLayoutX(573.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(161000), "", e -> { 
                    el.setLayoutX(582.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(161100), "", e -> { 
                    el.setLayoutX(594.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(161200), "", e -> { 
                    el.setLayoutX(607.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(161300), "", e -> { 
                    el.setLayoutX(622.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(161400), "", e -> { 
                    el.setLayoutX(637.0); 
                    el.setLayoutY(226.0); 
                }), 
                new KeyFrame(Duration.millis(161500), "", e -> { 
                    el.setLayoutX(651.0); 
                    el.setLayoutY(231.0); 
                }), 
                new KeyFrame(Duration.millis(161600), "", e -> { 
                    el.setLayoutX(663.0); 
                    el.setLayoutY(235.0); 
                }), 
                new KeyFrame(Duration.millis(161700), "", e -> { 
                    el.setLayoutX(673.0); 
                    el.setLayoutY(239.0); 
                }), 
                new KeyFrame(Duration.millis(161800), "", e -> { 
                    el.setLayoutX(681.0); 
                    el.setLayoutY(244.0); 
                }), 
                new KeyFrame(Duration.millis(161900), "", e -> { 
                    el.setLayoutX(688.0); 
                    el.setLayoutY(247.0); 
                }), 
                new KeyFrame(Duration.millis(162000), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(162100), "", e -> { 
                    el.setLayoutX(699.0); 
                    el.setLayoutY(253.0); 
                }), 
                new KeyFrame(Duration.millis(162200), "", e -> { 
                    el.setLayoutX(704.0); 
                    el.setLayoutY(257.0); 
                }), 
                new KeyFrame(Duration.millis(162300), "", e -> { 
                    el.setLayoutX(709.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(162400), "", e -> { 
                    el.setLayoutX(713.0); 
                    el.setLayoutY(266.0); 
                }), 
                new KeyFrame(Duration.millis(162500), "", e -> { 
                    el.setLayoutX(718.0); 
                    el.setLayoutY(271.0); 
                }), 
                new KeyFrame(Duration.millis(162600), "", e -> { 
                    el.setLayoutX(722.0); 
                    el.setLayoutY(276.0); 
                }), 
                new KeyFrame(Duration.millis(162700), "", e -> { 
                    el.setLayoutX(727.0); 
                    el.setLayoutY(281.0); 
                }), 
                new KeyFrame(Duration.millis(162800), "", e -> { 
                    el.setLayoutX(732.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(162900), "", e -> { 
                    el.setLayoutX(737.0); 
                    el.setLayoutY(288.0); 
                }), 
                new KeyFrame(Duration.millis(163000), "", e -> { 
                    el.setLayoutX(738.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(163100), "", e -> { 
                    el.setLayoutX(739.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(163200), "", e -> { 
                    el.setLayoutX(740.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(163300), "", e -> { 
                    el.setLayoutX(741.0); 
                    el.setLayoutY(290.0); 
                }), 
                new KeyFrame(Duration.millis(163400), "", e -> { 
                    el.setLayoutX(742.0); 
                    el.setLayoutY(290.0); 
                }), 
                new KeyFrame(Duration.millis(163500), "", e -> { 
                    el.setLayoutX(743.0); 
                    el.setLayoutY(291.0); 
                }), 
                new KeyFrame(Duration.millis(163600), "", e -> { 
                    el.setLayoutX(744.0); 
                    el.setLayoutY(292.0); 
                }), 
                new KeyFrame(Duration.millis(163700), "", e -> { 
                    el.setLayoutX(748.0); 
                    el.setLayoutY(294.0); 
                }), 
                new KeyFrame(Duration.millis(163800), "", e -> { 
                    el.setLayoutX(750.0); 
                    el.setLayoutY(294.0); 
                }), 
                new KeyFrame(Duration.millis(163900), "", e -> { 
                    el.setLayoutX(752.0); 
                    el.setLayoutY(296.0); 
                }), 
                new KeyFrame(Duration.millis(164000), "", e -> { 
                    el.setLayoutX(755.0); 
                    el.setLayoutY(298.0); 
                }), 
                new KeyFrame(Duration.millis(164100), "", e -> { 
                    el.setLayoutX(758.0); 
                    el.setLayoutY(300.0); 
                }), 
                new KeyFrame(Duration.millis(164200), "", e -> { 
                    el.setLayoutX(759.0); 
                    el.setLayoutY(301.0); 
                }), 
                new KeyFrame(Duration.millis(164300), "", e -> { 
                    el.setLayoutX(762.0); 
                    el.setLayoutY(302.0); 
                }), 
                new KeyFrame(Duration.millis(164400), "", e -> { 
                    el.setLayoutX(763.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(164500), "", e -> { 
                    el.setLayoutX(764.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(164600), "", e -> { 
                    el.setLayoutX(765.0); 
                    el.setLayoutY(305.0); 
                }), 
                new KeyFrame(Duration.millis(164700), "", e -> { 
                    el.setLayoutX(766.0); 
                    el.setLayoutY(307.0); 
                }), 
                new KeyFrame(Duration.millis(164800), "", e -> { 
                    el.setLayoutX(767.0); 
                    el.setLayoutY(307.0); 
                }), 
                new KeyFrame(Duration.millis(164900), "", e -> { 
                    el.setLayoutX(769.0); 
                    el.setLayoutY(309.0); 
                }), 
                new KeyFrame(Duration.millis(165000), "", e -> { 
                    el.setLayoutX(770.0); 
                    el.setLayoutY(313.0); 
                }), 
                new KeyFrame(Duration.millis(165100), "", e -> { 
                    el.setLayoutX(771.0); 
                    el.setLayoutY(315.0); 
                }), 
                new KeyFrame(Duration.millis(165200), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(319.0); 
                }), 
                new KeyFrame(Duration.millis(165300), "", e -> { 
                    el.setLayoutX(775.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(165400), "", e -> { 
                    el.setLayoutX(777.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(165500), "", e -> { 
                    el.setLayoutX(778.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(165600), "", e -> { 
                    el.setLayoutX(780.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(165700), "", e -> { 
                    el.setLayoutX(781.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(165800), "", e -> { 
                    el.setLayoutX(781.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(165900), "", e -> { 
                    el.setLayoutX(782.0); 
                    el.setLayoutY(345.0); 
                }), 
                new KeyFrame(Duration.millis(166000), "", e -> { 
                    el.setLayoutX(783.0); 
                    el.setLayoutY(346.0); 
                }), 
                new KeyFrame(Duration.millis(166100), "", e -> { 
                    el.setLayoutX(783.0); 
                    el.setLayoutY(349.0); 
                }), 
                new KeyFrame(Duration.millis(166200), "", e -> { 
                    el.setLayoutX(784.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(166300), "", e -> { 
                    el.setLayoutX(784.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(166400), "", e -> { 
                    el.setLayoutX(784.0); 
                    el.setLayoutY(356.0); 
                }), 
                new KeyFrame(Duration.millis(166500), "", e -> { 
                    el.setLayoutX(785.0); 
                    el.setLayoutY(359.0); 
                }), 
                new KeyFrame(Duration.millis(166600), "", e -> { 
                    el.setLayoutX(786.0); 
                    el.setLayoutY(360.0); 
                }), 
                new KeyFrame(Duration.millis(166700), "", e -> { 
                    el.setLayoutX(787.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(166800), "", e -> { 
                    el.setLayoutX(788.0); 
                    el.setLayoutY(364.0); 
                }), 
                new KeyFrame(Duration.millis(166900), "", e -> { 
                    el.setLayoutX(789.0); 
                    el.setLayoutY(367.0); 
                }), 
                new KeyFrame(Duration.millis(167000), "", e -> { 
                    el.setLayoutX(790.0); 
                    el.setLayoutY(369.0); 
                }), 
                new KeyFrame(Duration.millis(167100), "", e -> { 
                    el.setLayoutX(791.0); 
                    el.setLayoutY(370.0); 
                }), 
                new KeyFrame(Duration.millis(167200), "", e -> { 
                    el.setLayoutX(792.0); 
                    el.setLayoutY(372.0); 
                }), 
                new KeyFrame(Duration.millis(167300), "", e -> { 
                    el.setLayoutX(793.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(167400), "", e -> { 
                    el.setLayoutX(794.0); 
                    el.setLayoutY(376.0); 
                }), 
                new KeyFrame(Duration.millis(167500), "", e -> { 
                    el.setLayoutX(796.0); 
                    el.setLayoutY(379.0); 
                }), 
                new KeyFrame(Duration.millis(167600), "", e -> { 
                    el.setLayoutX(797.0); 
                    el.setLayoutY(382.0); 
                }), 
                new KeyFrame(Duration.millis(167700), "", e -> { 
                    el.setLayoutX(797.0); 
                    el.setLayoutY(383.0); 
                }), 
                new KeyFrame(Duration.millis(167800), "", e -> { 
                    el.setLayoutX(799.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(167900), "", e -> { 
                    el.setLayoutX(801.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168000), "", e -> { 
                    el.setLayoutX(803.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168100), "", e -> { 
                    el.setLayoutX(805.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168200), "", e -> { 
                    el.setLayoutX(807.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168300), "", e -> { 
                    el.setLayoutX(810.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168400), "", e -> { 
                    el.setLayoutX(814.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168500), "", e -> { 
                    el.setLayoutX(818.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168600), "", e -> { 
                    el.setLayoutX(824.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168700), "", e -> { 
                    el.setLayoutX(829.0); 
                    el.setLayoutY(386.0); 
                }), 
                new KeyFrame(Duration.millis(168800), "", e -> { 
                    el.setLayoutX(835.0); 
                    el.setLayoutY(385.0); 
                }), 
                new KeyFrame(Duration.millis(168900), "", e -> { 
                    el.setLayoutX(838.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(169000), "", e -> { 
                    el.setLayoutX(843.0); 
                    el.setLayoutY(381.0); 
                }), 
                new KeyFrame(Duration.millis(169100), "", e -> { 
                    el.setLayoutX(849.0); 
                    el.setLayoutY(377.0); 
                }), 
                new KeyFrame(Duration.millis(169200), "", e -> { 
                    el.setLayoutX(851.0); 
                    el.setLayoutY(375.0); 
                }), 
                new KeyFrame(Duration.millis(169300), "", e -> { 
                    el.setLayoutX(856.0); 
                    el.setLayoutY(372.0); 
                }), 
                new KeyFrame(Duration.millis(169400), "", e -> { 
                    el.setLayoutX(870.0); 
                    el.setLayoutY(357.0); 
                }), 
                new KeyFrame(Duration.millis(169500), "", e -> { 
                    el.setLayoutX(876.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(169600), "", e -> { 
                    el.setLayoutX(884.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(169700), "", e -> { 
                    el.setLayoutX(890.0); 
                    el.setLayoutY(330.0); 
                }), 
                new KeyFrame(Duration.millis(169800), "", e -> { 
                    el.setLayoutX(897.0); 
                    el.setLayoutY(319.0); 
                }), 
                new KeyFrame(Duration.millis(169900), "", e -> { 
                    el.setLayoutX(903.0); 
                    el.setLayoutY(306.0); 
                }), 
                new KeyFrame(Duration.millis(170000), "", e -> { 
                    el.setLayoutX(906.0); 
                    el.setLayoutY(293.0); 
                }), 
                new KeyFrame(Duration.millis(170100), "", e -> { 
                    el.setLayoutX(910.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(170200), "", e -> { 
                    el.setLayoutX(912.0); 
                    el.setLayoutY(260.0); 
                }), 
                new KeyFrame(Duration.millis(170300), "", e -> { 
                    el.setLayoutX(914.0); 
                    el.setLayoutY(240.0); 
                }), 
                new KeyFrame(Duration.millis(170400), "", e -> { 
                    el.setLayoutX(914.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(170500), "", e -> { 
                    el.setLayoutX(913.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(170600), "", e -> { 
                    el.setLayoutX(910.0); 
                    el.setLayoutY(187.0); 
                }), 
                new KeyFrame(Duration.millis(170700), "", e -> { 
                    el.setLayoutX(906.0); 
                    el.setLayoutY(174.0); 
                }), 
                new KeyFrame(Duration.millis(170800), "", e -> { 
                    el.setLayoutX(902.0); 
                    el.setLayoutY(161.0); 
                }), 
                new KeyFrame(Duration.millis(170900), "", e -> { 
                    el.setLayoutX(896.0); 
                    el.setLayoutY(150.0); 
                }), 
                new KeyFrame(Duration.millis(171000), "", e -> { 
                    el.setLayoutX(891.0); 
                    el.setLayoutY(139.0); 
                }), 
                new KeyFrame(Duration.millis(171100), "", e -> { 
                    el.setLayoutX(885.0); 
                    el.setLayoutY(128.0); 
                }), 
                new KeyFrame(Duration.millis(171200), "", e -> { 
                    el.setLayoutX(880.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(171300), "", e -> { 
                    el.setLayoutX(875.0); 
                    el.setLayoutY(111.0); 
                }), 
                new KeyFrame(Duration.millis(171400), "", e -> { 
                    el.setLayoutX(871.0); 
                    el.setLayoutY(105.0); 
                }), 
                new KeyFrame(Duration.millis(171500), "", e -> { 
                    el.setLayoutX(868.0); 
                    el.setLayoutY(100.0); 
                }), 
                new KeyFrame(Duration.millis(171600), "", e -> { 
                    el.setLayoutX(865.0); 
                    el.setLayoutY(98.0); 
                }), 
                new KeyFrame(Duration.millis(171700), "", e -> { 
                    el.setLayoutX(861.0); 
                    el.setLayoutY(96.0); 
                }), 
                new KeyFrame(Duration.millis(171800), "", e -> { 
                    el.setLayoutX(855.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(171900), "", e -> { 
                    el.setLayoutX(853.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172000), "", e -> { 
                    el.setLayoutX(848.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172100), "", e -> { 
                    el.setLayoutX(841.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172200), "", e -> { 
                    el.setLayoutX(833.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172300), "", e -> { 
                    el.setLayoutX(825.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172400), "", e -> { 
                    el.setLayoutX(817.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172500), "", e -> { 
                    el.setLayoutX(809.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172600), "", e -> { 
                    el.setLayoutX(801.0); 
                    el.setLayoutY(94.0); 
                }), 
                new KeyFrame(Duration.millis(172700), "", e -> { 
                    el.setLayoutX(792.0); 
                    el.setLayoutY(97.0); 
                }), 
                new KeyFrame(Duration.millis(172800), "", e -> { 
                    el.setLayoutX(784.0); 
                    el.setLayoutY(103.0); 
                }), 
                new KeyFrame(Duration.millis(172900), "", e -> { 
                    el.setLayoutX(774.0); 
                    el.setLayoutY(111.0); 
                }), 
                new KeyFrame(Duration.millis(173000), "", e -> { 
                    el.setLayoutX(764.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(173100), "", e -> { 
                    el.setLayoutX(754.0); 
                    el.setLayoutY(127.0); 
                }), 
                new KeyFrame(Duration.millis(173200), "", e -> { 
                    el.setLayoutX(744.0); 
                    el.setLayoutY(137.0); 
                }), 
                new KeyFrame(Duration.millis(173300), "", e -> { 
                    el.setLayoutX(732.0); 
                    el.setLayoutY(149.0); 
                }), 
                new KeyFrame(Duration.millis(173400), "", e -> { 
                    el.setLayoutX(718.0); 
                    el.setLayoutY(164.0); 
                }), 
                new KeyFrame(Duration.millis(173500), "", e -> { 
                    el.setLayoutX(700.0); 
                    el.setLayoutY(181.0); 
                }), 
                new KeyFrame(Duration.millis(173600), "", e -> { 
                    el.setLayoutX(682.0); 
                    el.setLayoutY(199.0); 
                }), 
                new KeyFrame(Duration.millis(173700), "", e -> { 
                    el.setLayoutX(662.0); 
                    el.setLayoutY(218.0); 
                }), 
                new KeyFrame(Duration.millis(173800), "", e -> { 
                    el.setLayoutX(639.0); 
                    el.setLayoutY(236.0); 
                }), 
                new KeyFrame(Duration.millis(173900), "", e -> { 
                    el.setLayoutX(617.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(174000), "", e -> { 
                    el.setLayoutX(590.0); 
                    el.setLayoutY(268.0); 
                }), 
                new KeyFrame(Duration.millis(174100), "", e -> { 
                    el.setLayoutX(563.0); 
                    el.setLayoutY(285.0); 
                }), 
                new KeyFrame(Duration.millis(174200), "", e -> { 
                    el.setLayoutX(537.0); 
                    el.setLayoutY(300.0); 
                }), 
                new KeyFrame(Duration.millis(174300), "", e -> { 
                    el.setLayoutX(509.0); 
                    el.setLayoutY(311.0); 
                }), 
                new KeyFrame(Duration.millis(174400), "", e -> { 
                    el.setLayoutX(478.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(174500), "", e -> { 
                    el.setLayoutX(445.0); 
                    el.setLayoutY(332.0); 
                }), 
                new KeyFrame(Duration.millis(174600), "", e -> { 
                    el.setLayoutX(408.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(174700), "", e -> { 
                    el.setLayoutX(368.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(174800), "", e -> { 
                    el.setLayoutX(329.0); 
                    el.setLayoutY(352.0); 
                }), 
                new KeyFrame(Duration.millis(174900), "", e -> { 
                    el.setLayoutX(291.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(175000), "", e -> { 
                    el.setLayoutX(255.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(175100), "", e -> { 
                    el.setLayoutX(222.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(175200), "", e -> { 
                    el.setLayoutX(195.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(175300), "", e -> { 
                    el.setLayoutX(169.0); 
                    el.setLayoutY(353.0); 
                }), 
                new KeyFrame(Duration.millis(175400), "", e -> { 
                    el.setLayoutX(146.0); 
                    el.setLayoutY(350.0); 
                }), 
                new KeyFrame(Duration.millis(175500), "", e -> { 
                    el.setLayoutX(128.0); 
                    el.setLayoutY(348.0); 
                }), 
                new KeyFrame(Duration.millis(175600), "", e -> { 
                    el.setLayoutX(115.0); 
                    el.setLayoutY(345.0); 
                }), 
                new KeyFrame(Duration.millis(175700), "", e -> { 
                    el.setLayoutX(104.0); 
                    el.setLayoutY(344.0); 
                }), 
                new KeyFrame(Duration.millis(175800), "", e -> { 
                    el.setLayoutX(96.0); 
                    el.setLayoutY(343.0); 
                }), 
                new KeyFrame(Duration.millis(175900), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(342.0); 
                }), 
                new KeyFrame(Duration.millis(176000), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(341.0); 
                }), 
                new KeyFrame(Duration.millis(176100), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(176200), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(176300), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(176400), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(334.0); 
                }), 
                new KeyFrame(Duration.millis(176500), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(332.0); 
                }), 
                new KeyFrame(Duration.millis(176600), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(176700), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(176800), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(327.0); 
                }), 
                new KeyFrame(Duration.millis(176900), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(325.0); 
                }), 
                new KeyFrame(Duration.millis(177000), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(177100), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(177200), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(320.0); 
                }), 
                new KeyFrame(Duration.millis(177300), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(319.0); 
                }), 
                new KeyFrame(Duration.millis(177400), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(318.0); 
                }), 
                new KeyFrame(Duration.millis(177500), "", e -> { 
                    el.setLayoutX(95.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(177600), "", e -> { 
                    el.setLayoutX(94.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(177700), "", e -> { 
                    el.setLayoutX(94.0); 
                    el.setLayoutY(315.0); 
                }), 
                new KeyFrame(Duration.millis(177800), "", e -> { 
                    el.setLayoutX(94.0); 
                    el.setLayoutY(314.0); 
                }), 
                new KeyFrame(Duration.millis(177900), "", e -> { 
                    el.setLayoutX(94.0); 
                    el.setLayoutY(309.0); 
                }), 
                new KeyFrame(Duration.millis(178000), "", e -> { 
                    el.setLayoutX(92.0); 
                    el.setLayoutY(306.0); 
                }), 
                new KeyFrame(Duration.millis(178100), "", e -> { 
                    el.setLayoutX(91.0); 
                    el.setLayoutY(303.0); 
                }), 
                new KeyFrame(Duration.millis(178200), "", e -> { 
                    el.setLayoutX(90.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(178300), "", e -> { 
                    el.setLayoutX(89.0); 
                    el.setLayoutY(294.0); 
                }), 
                new KeyFrame(Duration.millis(178400), "", e -> { 
                    el.setLayoutX(87.0); 
                    el.setLayoutY(287.0); 
                }), 
                new KeyFrame(Duration.millis(178500), "", e -> { 
                    el.setLayoutX(85.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(178600), "", e -> { 
                    el.setLayoutX(83.0); 
                    el.setLayoutY(267.0); 
                }), 
                new KeyFrame(Duration.millis(178700), "", e -> { 
                    el.setLayoutX(81.0); 
                    el.setLayoutY(257.0); 
                }), 
                new KeyFrame(Duration.millis(178800), "", e -> { 
                    el.setLayoutX(80.0); 
                    el.setLayoutY(248.0); 
                }), 
                new KeyFrame(Duration.millis(178900), "", e -> { 
                    el.setLayoutX(80.0); 
                    el.setLayoutY(238.0); 
                }), 
                new KeyFrame(Duration.millis(179000), "", e -> { 
                    el.setLayoutX(83.0); 
                    el.setLayoutY(231.0); 
                }), 
                new KeyFrame(Duration.millis(179100), "", e -> { 
                    el.setLayoutX(92.0); 
                    el.setLayoutY(226.0); 
                }), 
                new KeyFrame(Duration.millis(179200), "", e -> { 
                    el.setLayoutX(109.0); 
                    el.setLayoutY(222.0); 
                }), 
                new KeyFrame(Duration.millis(179300), "", e -> { 
                    el.setLayoutX(142.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(179400), "", e -> { 
                    el.setLayoutX(188.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(179500), "", e -> { 
                    el.setLayoutX(243.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(179600), "", e -> { 
                    el.setLayoutX(308.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(179700), "", e -> { 
                    el.setLayoutX(375.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(179800), "", e -> { 
                    el.setLayoutX(448.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(179900), "", e -> { 
                    el.setLayoutX(516.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(180000), "", e -> { 
                    el.setLayoutX(576.0); 
                    el.setLayoutY(216.0); 
                }), 
                new KeyFrame(Duration.millis(180100), "", e -> { 
                    el.setLayoutX(623.0); 
                    el.setLayoutY(210.0); 
                }), 
                new KeyFrame(Duration.millis(180200), "", e -> { 
                    el.setLayoutX(654.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(180300), "", e -> { 
                    el.setLayoutX(656.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(180400), "", e -> { 
                    el.setLayoutX(655.0); 
                    el.setLayoutY(207.0); 
                }), 
                new KeyFrame(Duration.millis(180500), "", e -> { 
                    el.setLayoutX(656.0); 
                    el.setLayoutY(205.0); 
                }), 
                new KeyFrame(Duration.millis(180600), "", e -> { 
                    el.setLayoutX(658.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(180700), "", e -> { 
                    el.setLayoutX(662.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(180800), "", e -> { 
                    el.setLayoutX(664.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(180900), "", e -> { 
                    el.setLayoutX(666.0); 
                    el.setLayoutY(203.0); 
                }), 
                new KeyFrame(Duration.millis(181000), "", e -> { 
                    el.setLayoutX(669.0); 
                    el.setLayoutY(206.0); 
                }), 
                new KeyFrame(Duration.millis(181100), "", e -> { 
                    el.setLayoutX(673.0); 
                    el.setLayoutY(210.0); 
                }), 
                new KeyFrame(Duration.millis(181200), "", e -> { 
                    el.setLayoutX(679.0); 
                    el.setLayoutY(215.0); 
                }), 
                new KeyFrame(Duration.millis(181300), "", e -> { 
                    el.setLayoutX(685.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(181400), "", e -> { 
                    el.setLayoutX(699.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(181500), "", e -> { 
                    el.setLayoutX(700.0); 
                    el.setLayoutY(247.0); 
                }), 
                new KeyFrame(Duration.millis(181600), "", e -> { 
                    el.setLayoutX(700.0); 
                    el.setLayoutY(253.0); 
                }), 
                new KeyFrame(Duration.millis(181700), "", e -> { 
                    el.setLayoutX(700.0); 
                    el.setLayoutY(263.0); 
                }), 
                new KeyFrame(Duration.millis(181800), "", e -> { 
                    el.setLayoutX(700.0); 
                    el.setLayoutY(272.0); 
                }), 
                new KeyFrame(Duration.millis(181900), "", e -> { 
                    el.setLayoutX(698.0); 
                    el.setLayoutY(282.0); 
                }), 
                new KeyFrame(Duration.millis(182000), "", e -> { 
                    el.setLayoutX(694.0); 
                    el.setLayoutY(291.0); 
                }), 
                new KeyFrame(Duration.millis(182100), "", e -> { 
                    el.setLayoutX(688.0); 
                    el.setLayoutY(300.0); 
                }), 
                new KeyFrame(Duration.millis(182200), "", e -> { 
                    el.setLayoutX(683.0); 
                    el.setLayoutY(309.0); 
                }), 
                new KeyFrame(Duration.millis(182300), "", e -> { 
                    el.setLayoutX(677.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(182400), "", e -> { 
                    el.setLayoutX(669.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(182500), "", e -> { 
                    el.setLayoutX(660.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(182600), "", e -> { 
                    el.setLayoutX(652.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(182700), "", e -> { 
                    el.setLayoutX(643.0); 
                    el.setLayoutY(344.0); 
                }), 
                new KeyFrame(Duration.millis(182800), "", e -> { 
                    el.setLayoutX(634.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(182900), "", e -> { 
                    el.setLayoutX(627.0); 
                    el.setLayoutY(358.0); 
                }), 
                new KeyFrame(Duration.millis(183000), "", e -> { 
                    el.setLayoutX(619.0); 
                    el.setLayoutY(365.0); 
                }), 
                new KeyFrame(Duration.millis(183100), "", e -> { 
                    el.setLayoutX(613.0); 
                    el.setLayoutY(371.0); 
                }), 
                new KeyFrame(Duration.millis(183200), "", e -> { 
                    el.setLayoutX(608.0); 
                    el.setLayoutY(377.0); 
                }), 
                new KeyFrame(Duration.millis(183300), "", e -> { 
                    el.setLayoutX(604.0); 
                    el.setLayoutY(383.0); 
                }), 
                new KeyFrame(Duration.millis(183400), "", e -> { 
                    el.setLayoutX(601.0); 
                    el.setLayoutY(388.0); 
                }), 
                new KeyFrame(Duration.millis(183500), "", e -> { 
                    el.setLayoutX(600.0); 
                    el.setLayoutY(391.0); 
                }), 
                new KeyFrame(Duration.millis(183600), "", e -> { 
                    el.setLayoutX(600.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(183700), "", e -> { 
                    el.setLayoutX(600.0); 
                    el.setLayoutY(397.0); 
                }), 
                new KeyFrame(Duration.millis(183800), "", e -> { 
                    el.setLayoutX(604.0); 
                    el.setLayoutY(400.0); 
                }), 
                new KeyFrame(Duration.millis(183900), "", e -> { 
                    el.setLayoutX(611.0); 
                    el.setLayoutY(402.0); 
                }), 
                new KeyFrame(Duration.millis(184000), "", e -> { 
                    el.setLayoutX(622.0); 
                    el.setLayoutY(404.0); 
                }), 
                new KeyFrame(Duration.millis(184100), "", e -> { 
                    el.setLayoutX(634.0); 
                    el.setLayoutY(405.0); 
                }), 
                new KeyFrame(Duration.millis(184200), "", e -> { 
                    el.setLayoutX(652.0); 
                    el.setLayoutY(405.0); 
                }), 
                new KeyFrame(Duration.millis(184300), "", e -> { 
                    el.setLayoutX(674.0); 
                    el.setLayoutY(405.0); 
                }), 
                new KeyFrame(Duration.millis(184400), "", e -> { 
                    el.setLayoutX(702.0); 
                    el.setLayoutY(405.0); 
                }), 
                new KeyFrame(Duration.millis(184500), "", e -> { 
                    el.setLayoutX(736.0); 
                    el.setLayoutY(399.0); 
                }), 
                new KeyFrame(Duration.millis(184600), "", e -> { 
                    el.setLayoutX(776.0); 
                    el.setLayoutY(390.0); 
                }), 
                new KeyFrame(Duration.millis(184700), "", e -> { 
                    el.setLayoutX(817.0); 
                    el.setLayoutY(379.0); 
                }), 
                new KeyFrame(Duration.millis(184800), "", e -> { 
                    el.setLayoutX(860.0); 
                    el.setLayoutY(368.0); 
                }), 
                new KeyFrame(Duration.millis(184900), "", e -> { 
                    el.setLayoutX(903.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(185000), "", e -> { 
                    el.setLayoutX(947.0); 
                    el.setLayoutY(342.0); 
                }), 
                new KeyFrame(Duration.millis(185100), "", e -> { 
                    el.setLayoutX(1024.0); 
                    el.setLayoutY(325.0); 
                }), 
                new KeyFrame(Duration.millis(185200), "", e -> { 
                    el.setLayoutX(1052.0); 
                    el.setLayoutY(322.0); 
                }), 
                new KeyFrame(Duration.millis(185300), "", e -> { 
                    el.setLayoutX(1072.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(185400), "", e -> { 
                    el.setLayoutX(1087.0); 
                    el.setLayoutY(320.0); 
                }), 
                new KeyFrame(Duration.millis(185500), "", e -> { 
                    el.setLayoutX(1097.0); 
                    el.setLayoutY(320.0); 
                }), 
                new KeyFrame(Duration.millis(185600), "", e -> { 
                    el.setLayoutX(1099.0); 
                    el.setLayoutY(320.0); 
                }), 
                new KeyFrame(Duration.millis(185700), "", e -> { 
                    el.setLayoutX(1099.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(185800), "", e -> { 
                    el.setLayoutX(1099.0); 
                    el.setLayoutY(322.0); 
                }), 
                new KeyFrame(Duration.millis(185900), "", e -> { 
                    el.setLayoutX(1098.0); 
                    el.setLayoutY(322.0); 
                }), 
                new KeyFrame(Duration.millis(186000), "", e -> { 
                    el.setLayoutX(1097.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(186100), "", e -> { 
                    el.setLayoutX(1094.0); 
                    el.setLayoutY(316.0); 
                }), 
                new KeyFrame(Duration.millis(186200), "", e -> { 
                    el.setLayoutX(1093.0); 
                    el.setLayoutY(313.0); 
                }), 
                new KeyFrame(Duration.millis(186300), "", e -> { 
                    el.setLayoutX(1087.0); 
                    el.setLayoutY(304.0); 
                }), 
                new KeyFrame(Duration.millis(186400), "", e -> { 
                    el.setLayoutX(1077.0); 
                    el.setLayoutY(287.0); 
                }), 
                new KeyFrame(Duration.millis(186500), "", e -> { 
                    el.setLayoutX(1057.0); 
                    el.setLayoutY(255.0); 
                }), 
                new KeyFrame(Duration.millis(186600), "", e -> { 
                    el.setLayoutX(1030.0); 
                    el.setLayoutY(214.0); 
                }), 
                new KeyFrame(Duration.millis(186700), "", e -> { 
                    el.setLayoutX(996.0); 
                    el.setLayoutY(165.0); 
                }), 
                new KeyFrame(Duration.millis(186800), "", e -> { 
                    el.setLayoutX(956.0); 
                    el.setLayoutY(114.0); 
                }), 
                new KeyFrame(Duration.millis(186900), "", e -> { 
                    el.setLayoutX(910.0); 
                    el.setLayoutY(61.0); 
                }), 
                new KeyFrame(Duration.millis(187000), "", e -> { 
                    el.setLayoutX(205.0); 
                    el.setLayoutY(27.0); 
                }), 
                new KeyFrame(Duration.millis(187100), "", e -> { 
                    el.setLayoutX(199.0); 
                    el.setLayoutY(38.0); 
                }), 
                new KeyFrame(Duration.millis(187200), "", e -> { 
                    el.setLayoutX(193.0); 
                    el.setLayoutY(52.0); 
                }), 
                new KeyFrame(Duration.millis(187300), "", e -> { 
                    el.setLayoutX(186.0); 
                    el.setLayoutY(67.0); 
                }), 
                new KeyFrame(Duration.millis(187400), "", e -> { 
                    el.setLayoutX(179.0); 
                    el.setLayoutY(84.0); 
                }), 
                new KeyFrame(Duration.millis(187500), "", e -> { 
                    el.setLayoutX(172.0); 
                    el.setLayoutY(104.0); 
                }), 
                new KeyFrame(Duration.millis(187600), "", e -> { 
                    el.setLayoutX(165.0); 
                    el.setLayoutY(124.0); 
                }), 
                new KeyFrame(Duration.millis(187700), "", e -> { 
                    el.setLayoutX(159.0); 
                    el.setLayoutY(146.0); 
                }), 
                new KeyFrame(Duration.millis(187800), "", e -> { 
                    el.setLayoutX(155.0); 
                    el.setLayoutY(164.0); 
                }), 
                new KeyFrame(Duration.millis(187900), "", e -> { 
                    el.setLayoutX(153.0); 
                    el.setLayoutY(179.0); 
                }), 
                new KeyFrame(Duration.millis(188000), "", e -> { 
                    el.setLayoutX(151.0); 
                    el.setLayoutY(193.0); 
                }), 
                new KeyFrame(Duration.millis(188100), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(204.0); 
                }), 
                new KeyFrame(Duration.millis(188200), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(213.0); 
                }), 
                new KeyFrame(Duration.millis(188300), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(220.0); 
                }), 
                new KeyFrame(Duration.millis(188400), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(225.0); 
                }), 
                new KeyFrame(Duration.millis(188500), "", e -> { 
                    el.setLayoutX(150.0); 
                    el.setLayoutY(231.0); 
                }), 
                new KeyFrame(Duration.millis(188600), "", e -> { 
                    el.setLayoutX(155.0); 
                    el.setLayoutY(238.0); 
                }), 
                new KeyFrame(Duration.millis(188700), "", e -> { 
                    el.setLayoutX(161.0); 
                    el.setLayoutY(247.0); 
                }), 
                new KeyFrame(Duration.millis(188800), "", e -> { 
                    el.setLayoutX(170.0); 
                    el.setLayoutY(257.0); 
                }), 
                new KeyFrame(Duration.millis(188900), "", e -> { 
                    el.setLayoutX(182.0); 
                    el.setLayoutY(269.0); 
                }), 
                new KeyFrame(Duration.millis(189000), "", e -> { 
                    el.setLayoutX(199.0); 
                    el.setLayoutY(282.0); 
                }), 
                new KeyFrame(Duration.millis(189100), "", e -> { 
                    el.setLayoutX(218.0); 
                    el.setLayoutY(295.0); 
                }), 
                new KeyFrame(Duration.millis(189200), "", e -> { 
                    el.setLayoutX(237.0); 
                    el.setLayoutY(305.0); 
                }), 
                new KeyFrame(Duration.millis(189300), "", e -> { 
                    el.setLayoutX(256.0); 
                    el.setLayoutY(312.0); 
                }), 
                new KeyFrame(Duration.millis(189400), "", e -> { 
                    el.setLayoutX(275.0); 
                    el.setLayoutY(317.0); 
                }), 
                new KeyFrame(Duration.millis(189500), "", e -> { 
                    el.setLayoutX(289.0); 
                    el.setLayoutY(321.0); 
                }), 
                new KeyFrame(Duration.millis(189600), "", e -> { 
                    el.setLayoutX(301.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(189700), "", e -> { 
                    el.setLayoutX(312.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(189800), "", e -> { 
                    el.setLayoutX(319.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(189900), "", e -> { 
                    el.setLayoutX(319.0); 
                    el.setLayoutY(325.0); 
                }), 
                new KeyFrame(Duration.millis(190000), "", e -> { 
                    el.setLayoutX(316.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190100), "", e -> { 
                    el.setLayoutX(313.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190200), "", e -> { 
                    el.setLayoutX(311.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190300), "", e -> { 
                    el.setLayoutX(305.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190400), "", e -> { 
                    el.setLayoutX(294.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190500), "", e -> { 
                    el.setLayoutX(283.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190600), "", e -> { 
                    el.setLayoutX(282.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190700), "", e -> { 
                    el.setLayoutX(283.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190800), "", e -> { 
                    el.setLayoutX(291.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(190900), "", e -> { 
                    el.setLayoutX(296.0); 
                    el.setLayoutY(329.0); 
                }), 
                new KeyFrame(Duration.millis(191000), "", e -> { 
                    el.setLayoutX(305.0); 
                    el.setLayoutY(330.0); 
                }), 
                new KeyFrame(Duration.millis(191100), "", e -> { 
                    el.setLayoutX(315.0); 
                    el.setLayoutY(331.0); 
                }), 
                new KeyFrame(Duration.millis(191200), "", e -> { 
                    el.setLayoutX(327.0); 
                    el.setLayoutY(332.0); 
                }), 
                new KeyFrame(Duration.millis(191300), "", e -> { 
                    el.setLayoutX(339.0); 
                    el.setLayoutY(333.0); 
                }), 
                new KeyFrame(Duration.millis(191400), "", e -> { 
                    el.setLayoutX(351.0); 
                    el.setLayoutY(334.0); 
                }), 
                new KeyFrame(Duration.millis(191500), "", e -> { 
                    el.setLayoutX(362.0); 
                    el.setLayoutY(335.0); 
                }), 
                new KeyFrame(Duration.millis(191600), "", e -> { 
                    el.setLayoutX(370.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(191700), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(191800), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(191900), "", e -> { 
                    el.setLayoutX(376.0); 
                    el.setLayoutY(342.0); 
                }), 
                new KeyFrame(Duration.millis(192000), "", e -> { 
                    el.setLayoutX(374.0); 
                    el.setLayoutY(347.0); 
                }), 
                new KeyFrame(Duration.millis(192100), "", e -> { 
                    el.setLayoutX(371.0); 
                    el.setLayoutY(351.0); 
                }), 
                new KeyFrame(Duration.millis(192200), "", e -> { 
                    el.setLayoutX(366.0); 
                    el.setLayoutY(355.0); 
                }), 
                new KeyFrame(Duration.millis(192300), "", e -> { 
                    el.setLayoutX(357.0); 
                    el.setLayoutY(362.0); 
                }), 
                new KeyFrame(Duration.millis(192400), "", e -> { 
                    el.setLayoutX(345.0); 
                    el.setLayoutY(368.0); 
                }), 
                new KeyFrame(Duration.millis(192500), "", e -> { 
                    el.setLayoutX(327.0); 
                    el.setLayoutY(374.0); 
                }), 
                new KeyFrame(Duration.millis(192600), "", e -> { 
                    el.setLayoutX(305.0); 
                    el.setLayoutY(380.0); 
                }), 
                new KeyFrame(Duration.millis(192700), "", e -> { 
                    el.setLayoutX(284.0); 
                    el.setLayoutY(384.0); 
                }), 
                new KeyFrame(Duration.millis(192800), "", e -> { 
                    el.setLayoutX(268.0); 
                    el.setLayoutY(388.0); 
                }), 
                new KeyFrame(Duration.millis(192900), "", e -> { 
                    el.setLayoutX(255.0); 
                    el.setLayoutY(391.0); 
                }), 
                new KeyFrame(Duration.millis(193000), "", e -> { 
                    el.setLayoutX(243.0); 
                    el.setLayoutY(394.0); 
                }), 
                new KeyFrame(Duration.millis(193100), "", e -> { 
                    el.setLayoutX(232.0); 
                    el.setLayoutY(399.0); 
                }), 
                new KeyFrame(Duration.millis(193200), "", e -> { 
                    el.setLayoutX(221.0); 
                    el.setLayoutY(403.0); 
                }), 
                new KeyFrame(Duration.millis(193300), "", e -> { 
                    el.setLayoutX(212.0); 
                    el.setLayoutY(406.0); 
                }), 
                new KeyFrame(Duration.millis(193400), "", e -> { 
                    el.setLayoutX(212.0); 
                    el.setLayoutY(407.0); 
                }), 
                new KeyFrame(Duration.millis(193500), "", e -> { 
                    el.setLayoutX(210.0); 
                    el.setLayoutY(409.0); 
                }), 
                new KeyFrame(Duration.millis(193600), "", e -> { 
                    el.setLayoutX(210.0); 
                    el.setLayoutY(412.0); 
                }), 
                new KeyFrame(Duration.millis(193700), "", e -> { 
                    el.setLayoutX(211.0); 
                    el.setLayoutY(416.0); 
                }), 
                new KeyFrame(Duration.millis(193800), "", e -> { 
                    el.setLayoutX(217.0); 
                    el.setLayoutY(422.0); 
                }), 
                new KeyFrame(Duration.millis(193900), "", e -> { 
                    el.setLayoutX(222.0); 
                    el.setLayoutY(425.0); 
                }), 
                new KeyFrame(Duration.millis(194000), "", e -> { 
                    el.setLayoutX(233.0); 
                    el.setLayoutY(430.0); 
                }), 
                new KeyFrame(Duration.millis(194100), "", e -> { 
                    el.setLayoutX(254.0); 
                    el.setLayoutY(435.0); 
                }), 
                new KeyFrame(Duration.millis(194200), "", e -> { 
                    el.setLayoutX(288.0); 
                    el.setLayoutY(440.0); 
                }), 
                new KeyFrame(Duration.millis(194300), "", e -> { 
                    el.setLayoutX(335.0); 
                    el.setLayoutY(444.0); 
                }), 
                new KeyFrame(Duration.millis(194400), "", e -> { 
                    el.setLayoutX(389.0); 
                    el.setLayoutY(444.0); 
                }), 
                new KeyFrame(Duration.millis(194500), "", e -> { 
                    el.setLayoutX(455.0); 
                    el.setLayoutY(441.0); 
                }), 
                new KeyFrame(Duration.millis(194600), "", e -> { 
                    el.setLayoutX(529.0); 
                    el.setLayoutY(430.0); 
                }), 
                new KeyFrame(Duration.millis(194700), "", e -> { 
                    el.setLayoutX(615.0); 
                    el.setLayoutY(415.0); 
                }), 
                new KeyFrame(Duration.millis(194800), "", e -> { 
                    el.setLayoutX(706.0); 
                    el.setLayoutY(395.0); 
                }), 
                new KeyFrame(Duration.millis(194900), "", e -> { 
                    el.setLayoutX(800.0); 
                    el.setLayoutY(372.0); 
                }), 
                new KeyFrame(Duration.millis(195000), "", e -> { 
                    el.setLayoutX(891.0); 
                    el.setLayoutY(352.0); 
                }), 
                new KeyFrame(Duration.millis(195100), "", e -> { 
                    el.setLayoutX(976.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(195200), "", e -> { 
                    el.setLayoutX(1053.0); 
                    el.setLayoutY(328.0); 
                }), 
                new KeyFrame(Duration.millis(195300), "", e -> { 
                    el.setLayoutX(1116.0); 
                    el.setLayoutY(324.0); 
                }), 
                new KeyFrame(Duration.millis(195400), "", e -> { 
                    el.setLayoutX(1162.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(195500), "", e -> { 
                    el.setLayoutX(1190.0); 
                    el.setLayoutY(323.0); 
                }), 
                new KeyFrame(Duration.millis(195600), "", e -> { 
                    el.setLayoutX(1198.0); 
                    el.setLayoutY(338.0); 
                }), 
                new KeyFrame(Duration.millis(195700), "", e -> { 
                    el.setLayoutX(1185.0); 
                    el.setLayoutY(340.0); 
                }), 
                new KeyFrame(Duration.millis(195800), "", e -> { 
                    el.setLayoutX(1168.0); 
                    el.setLayoutY(342.0); 
                }), 
                new KeyFrame(Duration.millis(195900), "", e -> { 
                    el.setLayoutX(1151.0); 
                    el.setLayoutY(344.0); 
                }), 
                new KeyFrame(Duration.millis(196000), "", e -> { 
                    el.setLayoutX(1139.0); 
                    el.setLayoutY(344.0); 
                }), 
                new KeyFrame(Duration.millis(196100), "", e -> { 
                    el.setLayoutX(1138.0); 
                    el.setLayoutY(343.0); 
                }), 
                new KeyFrame(Duration.millis(196200), "", e -> { 
                    el.setLayoutX(1138.0); 
                    el.setLayoutY(339.0); 
                }), 
                new KeyFrame(Duration.millis(196300), "", e -> { 
                    el.setLayoutX(1138.0); 
                    el.setLayoutY(337.0); 
                }), 
                new KeyFrame(Duration.millis(196400), "", e -> { 
                    el.setLayoutX(1141.0); 
                    el.setLayoutY(333.0); 
                }), 
                new KeyFrame(Duration.millis(196500), "", e -> { 
                    el.setLayoutX(1142.0); 
                    el.setLayoutY(330.0); 
                }), 
                new KeyFrame(Duration.millis(196600), "", e -> { 
                    el.setLayoutX(1146.0); 
                    el.setLayoutY(325.0); 
                }), 
                new KeyFrame(Duration.millis(196700), "", e -> { 
                    el.setLayoutX(1151.0); 
                    el.setLayoutY(319.0); 
                }), 
                new KeyFrame(Duration.millis(196800), "", e -> { 
                    el.setLayoutX(1157.0); 
                    el.setLayoutY(311.0); 
                }), 
                new KeyFrame(Duration.millis(196900), "", e -> { 
                    el.setLayoutX(1162.0); 
                    el.setLayoutY(304.0); 
                }), 
                new KeyFrame(Duration.millis(197000), "", e -> { 
                    el.setLayoutX(1168.0); 
                    el.setLayoutY(297.0); 
                }), 
                new KeyFrame(Duration.millis(197100), "", e -> { 
                    el.setLayoutX(1172.0); 
                    el.setLayoutY(289.0); 
                }), 
                new KeyFrame(Duration.millis(197200), "", e -> { 
                    el.setLayoutX(1175.0); 
                    el.setLayoutY(284.0); 
                }), 
                new KeyFrame(Duration.millis(197300), "", e -> { 
                    el.setLayoutX(1178.0); 
                    el.setLayoutY(279.0); 
                }), 
                new KeyFrame(Duration.millis(197400), "", e -> { 
                    el.setLayoutX(1178.0); 
                    el.setLayoutY(278.0); 
                }), 
                new KeyFrame(Duration.millis(197500), "", e -> { 
                    el.setLayoutX(1178.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(197600), "", e -> { 
                    el.setLayoutX(1175.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(197700), "", e -> { 
                    el.setLayoutX(1172.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(197800), "", e -> { 
                    el.setLayoutX(1170.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(197900), "", e -> { 
                    el.setLayoutX(1164.0); 
                    el.setLayoutY(277.0); 
                }), 
                new KeyFrame(Duration.millis(198000), "", e -> { 
                    el.setLayoutX(1152.0); 
                    el.setLayoutY(276.0); 
                }), 
                new KeyFrame(Duration.millis(198100), "", e -> { 
                    el.setLayoutX(1134.0); 
                    el.setLayoutY(274.0); 
                }), 
                new KeyFrame(Duration.millis(198200), "", e -> { 
                    el.setLayoutX(1097.0); 
                    el.setLayoutY(269.0); 
                }), 
                new KeyFrame(Duration.millis(198300), "", e -> { 
                    el.setLayoutX(1049.0); 
                    el.setLayoutY(261.0); 
                }), 
                new KeyFrame(Duration.millis(198400), "", e -> { 
                    el.setLayoutX(991.0); 
                    el.setLayoutY(251.0); 
                }), 
                new KeyFrame(Duration.millis(198500), "", e -> { 
                    el.setLayoutX(927.0); 
                    el.setLayoutY(241.0); 
                }), 
                new KeyFrame(Duration.millis(198600), "", e -> { 
                    el.setLayoutX(863.0); 
                    el.setLayoutY(231.0); 
                }), 
                new KeyFrame(Duration.millis(198700), "", e -> { 
                    el.setLayoutX(803.0); 
                    el.setLayoutY(225.0); 
                }), 
                new KeyFrame(Duration.millis(198800), "", e -> { 
                    el.setLayoutX(751.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(198900), "", e -> { 
                    el.setLayoutX(704.0); 
                    el.setLayoutY(219.0); 
                }), 
                new KeyFrame(Duration.millis(199000), "", e -> { 
                    el.setLayoutX(663.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199100), "", e -> { 
                    el.setLayoutX(633.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199200), "", e -> { 
                    el.setLayoutX(632.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199300), "", e -> { 
                    el.setLayoutX(631.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199400), "", e -> { 
                    el.setLayoutX(634.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199500), "", e -> { 
                    el.setLayoutX(638.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199600), "", e -> { 
                    el.setLayoutX(641.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199700), "", e -> { 
                    el.setLayoutX(645.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199800), "", e -> { 
                    el.setLayoutX(648.0); 
                    el.setLayoutY(217.0); 
                }), 
                new KeyFrame(Duration.millis(199900), "", e -> { 
                    el.setLayoutX(649.0); 
                    el.setLayoutY(219.0); 
                }), 
                new KeyFrame(Duration.millis(200000), "", e -> { 
                    el.setLayoutX(649.0); 
                    el.setLayoutY(221.0); 
                }), 
                new KeyFrame(Duration.millis(200100), "", e -> { 
                    el.setLayoutX(647.0); 
                    el.setLayoutY(224.0); 
                }), 
                new KeyFrame(Duration.millis(200200), "", e -> { 
                    el.setLayoutX(640.0); 
                    el.setLayoutY(230.0); 
                }), 
                new KeyFrame(Duration.millis(200300), "", e -> { 
                    el.setLayoutX(638.0); 
                    el.setLayoutY(233.0); 
                }), 
                new KeyFrame(Duration.millis(200400), "", e -> { 
                    el.setLayoutX(630.0); 
                    el.setLayoutY(237.0); 
                }), 
                new KeyFrame(Duration.millis(200500), "", e -> { 
                    el.setLayoutX(616.0); 
                    el.setLayoutY(242.0); 
                }), 
                new KeyFrame(Duration.millis(200600), "", e -> { 
                    el.setLayoutX(593.0); 
                    el.setLayoutY(245.0); 
                }), 
                new KeyFrame(Duration.millis(200700), "", e -> { 
                    el.setLayoutX(557.0); 
                    el.setLayoutY(246.0); 
                }), 
                new KeyFrame(Duration.millis(200800), "", e -> { 
                    el.setLayoutX(515.0); 
                    el.setLayoutY(244.0); 
                }), 
                new KeyFrame(Duration.millis(200900), "", e -> { 
                    el.setLayoutX(467.0); 
                    el.setLayoutY(232.0); 
                }), 
                new KeyFrame(Duration.millis(201000), "", e -> { 
                    el.setLayoutX(418.0); 
                    el.setLayoutY(216.0); 
                }), 
                new KeyFrame(Duration.millis(201100), "", e -> { 
                    el.setLayoutX(368.0); 
                    el.setLayoutY(197.0); 
                }), 
                new KeyFrame(Duration.millis(201200), "", e -> { 
                    el.setLayoutX(326.0); 
                    el.setLayoutY(177.0); 
                }), 
                new KeyFrame(Duration.millis(201300), "", e -> { 
                    el.setLayoutX(292.0); 
                    el.setLayoutY(159.0); 
                }), 
                new KeyFrame(Duration.millis(201400), "", e -> { 
                    el.setLayoutX(268.0); 
                    el.setLayoutY(145.0); 
                }), 
                new KeyFrame(Duration.millis(201500), "", e -> { 
                    el.setLayoutX(254.0); 
                    el.setLayoutY(135.0); 
                }), 
                new KeyFrame(Duration.millis(201600), "", e -> { 
                    el.setLayoutX(244.0); 
                    el.setLayoutY(126.0); 
                }), 
                new KeyFrame(Duration.millis(201700), "", e -> { 
                    el.setLayoutX(238.0); 
                    el.setLayoutY(118.0); 
                }), 
                new KeyFrame(Duration.millis(201800), "", e -> { 
                    el.setLayoutX(233.0); 
                    el.setLayoutY(110.0); 
                }), 
                new KeyFrame(Duration.millis(201900), "", e -> { 
                    el.setLayoutX(231.0); 
                    el.setLayoutY(102.0); 
                }), 
                new KeyFrame(Duration.millis(202000), "", e -> { 
                    el.setLayoutX(231.0); 
                    el.setLayoutY(92.0); 
                }), 
                new KeyFrame(Duration.millis(202100), "", e -> { 
                    el.setLayoutX(235.0); 
                    el.setLayoutY(81.0); 
                }), 
                new KeyFrame(Duration.millis(202200), "", e -> { 
                    el.setLayoutX(240.0); 
                    el.setLayoutY(69.0); 
                }), 
                new KeyFrame(Duration.millis(202300), "", e -> { 
                    el.setLayoutX(246.0); 
                    el.setLayoutY(58.0); 
                }), 
                new KeyFrame(Duration.millis(202400), "", e -> { 
                    el.setLayoutX(246.0); 
                    el.setLayoutY(56.0); 
                }), 
                new KeyFrame(Duration.millis(202500), "", e -> { 
                    el.setLayoutX(246.0); 
                    el.setLayoutY(55.0); 
                }), 
                new KeyFrame(Duration.millis(202600), "", e -> { 
                    el.setLayoutX(245.0); 
                    el.setLayoutY(55.0); 
                }), 
                new KeyFrame(Duration.millis(202700), "", e -> { 
                    el.setLayoutX(240.0); 
                    el.setLayoutY(56.0); 
                }), 
                new KeyFrame(Duration.millis(202800), "", e -> { 
                    el.setLayoutX(235.0); 
                    el.setLayoutY(59.0); 
                }), 
                new KeyFrame(Duration.millis(202900), "", e -> { 
                    el.setLayoutX(227.0); 
                    el.setLayoutY(64.0); 
                }), 
                new KeyFrame(Duration.millis(203000), "", e -> { 
                    el.setLayoutX(214.0); 
                    el.setLayoutY(73.0); 
                }), 
                new KeyFrame(Duration.millis(203100), "", e -> { 
                    el.setLayoutX(195.0); 
                    el.setLayoutY(84.0); 
                }), 
                new KeyFrame(Duration.millis(203200), "", e -> { 
                    el.setLayoutX(169.0); 
                    el.setLayoutY(97.0); 
                }), 
                new KeyFrame(Duration.millis(203300), "", e -> { 
                    el.setLayoutX(137.0); 
                    el.setLayoutY(113.0); 
                }), 
                new KeyFrame(Duration.millis(203400), "", e -> { 
                    el.setLayoutX(103.0); 
                    el.setLayoutY(129.0); 
                }), 
                new KeyFrame(Duration.millis(203500), "", e -> { 
                    el.setLayoutX(69.0); 
                    el.setLayoutY(145.0); 
                }), 
                new KeyFrame(Duration.millis(203600), "", e -> { 
                    el.setLayoutX(31.0); 
                    el.setLayoutY(160.0); 
                })); 
        tm.setCycleCount(Animation.INDEFINITE); 
        tm.play(); 
 
    } 
} 
