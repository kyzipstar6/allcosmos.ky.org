package application;              
              
import java.io.*;              
              
import java.util.List;              
import java.util.ArrayList;              
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
import java.util.concurrent.atomic.AtomicBoolean;              
import java.util.concurrent.atomic.AtomicReference;              
              
public class Methods {              
    public Methods() {              
    }              
              
    public static void main(String[] args) {              
        new Methods().getAllElementData();              
    }              
              
    public List<List<String>> getAllElementData() {              
        List<List<String>> allparlists = null;              
        try {              
            String pf1 = new File("").getAbsoluteFile().toURI() + "";              
            System.out.println(pf1);              
              
            BufferedReader rd = new BufferedReader(              
                    new FileReader(pf1.replace("file:/", "").replace("/", "\\") + "files\\elements.json"));              
            List<String> lns = rd.lines().toList();              
            rd.close();              
            String npars = "number";              
            String spars = "symbol";              
            String apars = "atomic_mass";              
            String npar = "      \"number\":";              
            String amaspar = "      \"atomic_mass\":";              
            String sympar = "\"symbol\":";              
            String mppar = "\"melt\":";              
            String bppar = "\"boil\":";              
            String mppars = "melt";              
            String bppars = "boil";              
              
            List<String> nparl = new ArrayList();              
            List<String> amasparl = new ArrayList();              
            List<String> symparl = new ArrayList();              
            List<String> mpparl = new ArrayList();              
            List<String> bpparl = new ArrayList();              
              
            allparlists = new ArrayList();              
            allparlists.add(nparl);              
            allparlists.add(amasparl);              
            allparlists.add(symparl);              
            allparlists.add(mpparl);              
            allparlists.add(bpparl);              
              
            String[] parse = { npar, amaspar, sympar, mppar, bppar };              
            String[] parsenoc = { npars, apars, spars };              
            for (int j = 0; j < lns.size(); j++) {              
              
                if (lns.get(j).contains(mppars) && !lns.get(j).contains("("))              
                    mpparl.add(              
                            lns.get(j).replaceAll("[^1-9.]", ""));              
                if (lns.get(j).contains(mppars) && lns.get(j).contains("("))              
                    mpparl.add(              
                            lns.get(j).substring(0, lns.get(j).indexOf("(")).replaceAll("[^1-9.]", ""));              
              
                // .substring(lns.get(j).indexOf(mppars)).replace(mppars, "").replace("K", "")              
                // .replace(" ", "").replace("\"", "").replace(":", "").replace(",", ""));              
              
            }              
            for (int j = 0; j < lns.size(); j++) {              
              
                if (lns.get(j).contains(bppars) && !lns.get(j).contains("null") && !lns.get(j).contains("("))              
                    bpparl.add(              
                            lns.get(j).replaceAll("[^1-9.]", ""));              
                if (lns.get(j).contains(bppars) && lns.get(j).contains("("))              
                    bpparl.add(lns.get(j).substring(0, lns.get(j).indexOf("(")).replaceAll("[^1-9.]", ""));              
            }              
            for (int j = 0; j < lns.size(); j++) {              
              
                if (lns.get(j).contains(apars))              
                    amasparl.add(              
                            lns.get(j).substring(lns.get(j).indexOf(apars)).replace(apars, "").replace("u", "")              
                                    .replace(" ", "").replace("\"", "").replace(":", "").replace(",", ""));              
              
            }              
            for (int j = 0; j < lns.size(); j++) {              
                if (lns.get(j).contains(spars))              
                    symparl.add(              
                            lns.get(j).substring(lns.get(j).indexOf(spars)).replace("\"", "").replace(":", "")              
                                    .replace(spars, "")              
                                    .replace(" ", "")              
                                    .replace(",", ""));              
              
            }              
            for (int j = 0; j < lns.size(); j++) {              
                if (lns.get(j).contains(npars))              
                    nparl.add(              
                            lns.get(j).substring(lns.get(j).indexOf(npars)).replace("\"", "").replace(":", "")              
                                    .replace(npars, "")              
                                    .replace(" ", "")              
                                    .replace(",", ""));              
              
            }              
            System.out.println(nparl.get(2)              
                    + " " + amasparl.get(2)              
                    + " " +              
                    symparl.get(2));              
        } catch (IOException e) {              
            e.printStackTrace();              
        }              
        return allparlists;              
    }              
              
    class ElementData {              
        String sym = "E";              
        double atomicmass = 0;              
        int n = 0;              
              
        public ElementData(String sym, double atmass, int n) {              
            this.sym = sym;              
            this.atomicmass = atmass;              
            this.n = n;              
        }              
              
        public String getSym() {              
            return sym;              
        }              
              
        public double getMass() {              
            return atomicmass;              
        }              
              
        public int getN() {              
            return n;              
        }              
    }              
              
    String[] doubleInputPane(String title, String[] labtags) {              
        TextArea[] ar = new TextArea[labtags.length];              
              
        Stage stg = new Stage();              
        stg.setTitle(title);              
        BorderPane root = new BorderPane();              
        Scene sc = new Scene(root);              
        try {              
            VBox box = new VBox(5);              
            Label lab = new Label(title);              
            box.getChildren().add(lab);              
            Label[] labs = new Label[labtags.length];              
            for (int i = 0; i < labtags.length; i++) {              
              
                labs[i] = new Label(labtags[i]);              
                ar[i] = new TextArea(labtags[i]);              
                int fi = i;              
                ar[i].setOnMouseEntered(e -> {              
                    if (ar[fi].getText().equals(labtags[fi]))              
                        ar[fi].setText("");              
                });              
                ar[i].setMaxHeight(12);              
                ar[i].setMaxWidth(100);              
                box.getChildren().addAll(labs[i], ar[i]);              
              
            }              
            Button bt = new Button("Set");              
            bt.setOnAction(e -> stg.close());              
            box.getChildren().add(bt);              
            root.setCenter(box);              
              
        } catch (Exception e) {              
            e.printStackTrace();              
        }              
              
        stg.setScene(sc);              
        stg.setResizable(false);              
        stg.showAndWait();              
              
        return new String[] { ar[0].getText(), ar[1].getText() };              
              
    }              
              
    String singleInputPane(String title, String labtags) {              
              
        Stage stg = new Stage();              
        stg.setTitle(title);              
        BorderPane root = new BorderPane();              
        Scene sc = new Scene(root);              
        TextArea ar = new TextArea(labtags);              
        try {              
            VBox box = new VBox(5);              
            Label lab = new Label(title);              
            box.getChildren().add(lab);              
            Label labs = new Label(labtags);              
              
            ar.setOnMouseEntered(e -> {              
                if (ar.getText().equals(labtags))              
                    ar.setText("");              
            });              
            ar.setMaxHeight(12);              
            ar.setMaxWidth(100);              
            box.getChildren().addAll(labs, ar);              
              
            Button bt = new Button("Set");              
            bt.setOnAction(e -> stg.close());              
            box.getChildren().add(bt);              
            root.setCenter(box);              
              
        } catch (Exception e) {              
            e.printStackTrace();              
        }              
              
        stg.setScene(sc);              
        stg.setResizable(false);              
        stg.showAndWait();              
              
        return ar.getText();              
              
    }              
}              
