package extendedapplication;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ConstellationsEvolution extends Application {
	int pulse = 0,limit=0;
	@Override
	public void start(Stage stg) throws Exception {
		
		Pane cpane = new Pane();
		int w=1450, h=760;
		List<Constellation> constellations = new ArrayList<Constellation>();
		cpane.setStyle("-fx-background-color: linear-gradient(black, #1f1f1f);");
		cpane.setPrefSize(w, h);
		String [] constellationsname = "Cassiopeia".split(",");
		//Cassiopeia
		
		String [] cassiopeiastars = "Alpha,Beta,Gamma,Delta,Epsilon".split(",");
		int [] cassiopeiadists = {228,54,613,99,442};
		double [] cassiopeiamags = {2.24,2.28,2.47,2.68,3.35};
		int [] cassiopeiaxs = {12,15,10,7,2};
		int [] cassiopeiays = {10,6,4,4,2};

		
		Constellation cassiopeia = new Constellation();
		cassiopeia.relx=((w/24)*20);cassiopeia.rely=(h/9)*4;cassiopeia.name= "cassiopeia";
		for (int i =0; i<cassiopeiastars.length; i++) {
			int idx = i;
			Star star = new Star(cassiopeiastars[idx],cassiopeiadists[idx], cassiopeiamags[idx], new V2D(cassiopeiaxs[idx],cassiopeiays[idx]));
			cassiopeia.stars.add(star);
		}
		constellations.add(cassiopeia);
		
		List<Circle> circlesl = new ArrayList<Circle>();
		//Taurus
		String [] taurusstars = "Adelbaran,Beta,Alcyone A,Sigma,Theta,Lambda,Epsilon,Omicron,Atlas A,Gamma,Electra".split(",");

		int [] taurusdists = {65,131,368,417,149,370,155,211,380,154,372};
		double [] taurusmags = {0.87,1.65,2.85,2.97,3.40,3.41,3.53,3.61,3.62,3.65,3.71};
		int [] taurusxs = {8,3,10,9,8,15,2,10,10,10,10};
		int [] taurusys = {10,3,10,10,9,15,5,10,10,10,10};

		
		Constellation taurus = new Constellation();
		taurus.relx=((w/24)*19);taurus.rely=(h/9)*4;taurus.name= "Taurus";
		for (int i =0; i<taurusstars.length; i++) {
			int idx = i;
			Star star = new Star(taurusstars[idx],taurusdists[idx], taurusmags[idx], new V2D(taurusxs[idx],taurusys[idx]));
			taurus.stars.add(star);
		}
		constellations.add(taurus);
		
		//Orion
		String [] orionstars = "Rigel,Betelgeuse,Bellatrix,Alnilam,Alnitak A,Saiph,Mintaka AB,Iota".split(",");

		int [] oriondists = {773,643,243,1342,817,721,916,1325};
		double [] orionmags = {0.18,0.42,1.64,1.69,1.88,2.07,2.20,2.75};
		int [] orionxs = {8,1,5,4,3,2,5,3};
		int [] orionys = {10,1,2,6,7,11,5,-1};
		
		Constellation orion = new Constellation();
		orion.relx=((w/24)*14);orion.rely=(h/9)*6;orion.name= "Orion";
		for (int i =0; i<orionstars.length; i++) {
			int idx = i;
			Star star = new Star(orionstars[idx],oriondists[idx], orionmags[idx], new V2D(orionxs[idx],orionys[idx]));
			orion.stars.add(star);
		}
		constellations.add(orion);
		
		//Auriga
		String [] aurigastars = "Capella,Beta,Theta,Iota,Epsilon,Eta,Sigma,Delta".split(",");

		int [] aurigadists = {42,82,173,512,2037,219,787,140};
		double [] aurigamags = {0.08,1.9,2.6,2.69,3.03,3.18,3.69,3.72};
		int [] aurigaxs = {14,7,7,16,15,15,15,7};
		int [] aurigays = {6,7,11,17,8,9,9,1};
				
				Constellation auriga = new Constellation();
				auriga.relx=((w/24)*13);auriga.rely=(int) ((h/9)*6.5);auriga.name= "Auriga";
				for (int i =0; i<aurigastars.length; i++) {
					int idx = i;
					Star star = new Star(aurigastars[idx],
							aurigadists[idx], 
							aurigamags[idx]
									, new V2D(aurigaxs[idx],
											aurigays[idx]));
					auriga.stars.add(star);
				}
				constellations.add(auriga);
		
				
		//Cancer
				String [] cancerstars = "Beta,Delta,Iota,Alpha,Gamma".split(",");

				int [] cancerdists = {290,136,298,173,181};
				double [] cancermags = {3.53,3.94,4.03,4.26,4.66};
				int [] cancerxs = {13,6,5,2,6};
				int [] cancerys = {15,9,2,13,7};
				Constellation cancer = new Constellation();
				cancer.relx=((w/24)*11);cancer.rely=(int) ((h/9)*7);cancer.name= "cancer";
				for (int i =0; i<cancerstars.length; i++) {
					int idx = i;
					Star star = new Star(cancerstars[idx],cancerdists[idx], cancermags[idx], new V2D(cancerxs[idx],cancerys[idx]));
					cancer.stars.add(star);
				}
				constellations.add(cancer);
				
		//Leo
				String [] leostars = "Regulus,Denebola,Gamma,Delta,Epsilon,Theta,Zeta,Eta,Mi,Iota,Sigma,Lambda,Kappa".split(",");

				int [] leodists = {77,36,126,58,251,178,260,2131,130,133,79,214,336,213};
				double [] leomags = {1.36,2.14,2.37,2.56,2.97,3.33,3.43,3.48,3.8,3.88,4.0,4.32,4.47};
				int [] leoxs = {15,1,12,4,17,4,12,19,12,3,4,22,23};//12
				int [] leoys = {13,11,7,7,5,10,9,7,7,2,14,17,4,1};//11
				Constellation leo = new Constellation();
				leo.relx=((w/24)*10);leo.rely=(int) ((h/9)*7.5);leo.name= "leo";
				for (int i =0; i<leostars.length; i++) {
					int idx = i;
					Star star = new Star(leostars[idx],
							leodists[idx],
							leomags[idx], 
							new V2D(leoxs[idx],
									leoys[idx]));
					leo.stars.add(star);
				}
				constellations.add(leo);

		for(int i =0; i<constellations.size(); i++) {
			List<Star> list = constellations.get(i).stars;
			Constellation cn = constellations.get(i);
			double clx = cn.relx,cly=cn.rely;
			for(int j =0;j<list.size();j++) {
				int id = j;
				Circle c = new Circle();
				Pane p = new Pane();
				
				Star s = list.get(id);
				double radius = Math.abs(3-list.get(id).magnitude);
				p.setShape(c);p.setStyle("-fx-background-color: white;");
				c.setRadius(radius);
				c.setFill(Color.WHITE);
				p.setPrefSize(radius, radius);
				double slx = clx+(s.pos().x*3),sly=cly+(s.pos().y*3);
				p.setLayoutX(slx);p.setLayoutY(sly);
				circlesl.add(c);

				cpane.getChildren().add(c);
			}
		}
		Scene sc = new Scene(cpane);
		stg.setScene(sc);
		stg.setTitle("Constellations evolution");
		stg.show();
		
		
		Button ma300= new Button("+300 Ma"),ma200= new Button("+200 Ma"), ma100= new Button("+100 Ma"), present = new Button("Present"),
				paleogene = new Button("Paleogene"),cretaceous = new Button("Cretaceous"),jurassic=  new Button("Jurassic"),triassic= new Button("Triassic");
		
		Button [] eonbtns = {triassic,jurassic,cretaceous,paleogene,present,ma100,ma200,ma300};
		
		HBox buttonsBox = new HBox(2);
		int [] limits = {-225,-173,-100,-43,0,100,200,300};
		for(int i =0; i<eonbtns.length; i++) {
			int id=i;
			Button b = eonbtns[id];
			b.setStyle("-fx-background-color: linear-gradient(#1e1e1e,#3f3f3f); -fx-text-fill:white;");
			buttonsBox.getChildren().add(b);
			b.setOnAction(ev->limit=limits[id]);
		}
		
		Label [] currentState = {new Label ("0"), new Label(" Ma "), new Label("years ago")};
		currentState[0].setStyle("-fx-font-family: 'System';-fx-font-size: 20px; -fx-text-fill: green");
		currentState[1].setStyle("-fx-font-family: 'Consolas';-fx-font-size: 16px; -fx-text-fill: lightblue");
		currentState[2].setStyle("-fx-font-family: 'Arial';-fx-font-size: 14px; -fx-text-fill: white");
		
		HBox labels = new HBox(1,currentState);
		
		labels.setLayoutX(w-200);
		labels.setLayoutY(50);

		buttonsBox.setLayoutX(30);buttonsBox.setLayoutY((h/9)*8);
		cpane.getChildren().addAll(buttonsBox,labels);
		new AnimationTimer() {
			int star=0;
			@Override public void handle(long n) {
				if(limit>0)pulse++;
				if(limit<0)pulse--;
				
				if(Math.abs(pulse)>Math.abs(limit))pulse=limit;
				for(int i =0; i<constellations.size(); i++) {
					List<Star> list = constellations.get(i).stars;
					Constellation cn = constellations.get(i);
					double clx = cn.relx,cly=cn.rely;
					for(int j =0;j<list.size();j++) {
						int id = j;
						
						Star s = list.get(id);
						int fac = 3;
						double df = (400-s.distancely)/100;
						double slx = (((pulse/25)*df)/fac)+clx+(s.pos().x*3),sly=(((pulse/25)*df)/(fac/1.5))+cly+(s.pos().y*3);
						Circle c = circlesl.get(star);
						star++;
						Platform.runLater(()->{c.setLayoutX(slx);c.setLayoutY(sly);				currentState[0].setText(pulse+"");
						currentState[2].setText((limit>0)? "years in the future" : "years ago");
});
					}
				
				}
				star=0;
			}
		}.start();

	}
	public static void main (String[] args) {
		launch(args);
	}
	class Constellation {
		String name;
		int relx, rely;
		List<Star> stars = new ArrayList<Star>();
	}
	record Star(String name, int distancely, double magnitude,V2D pos) {
		
	}
	class V2D{
		int x, y;
		public V2D(int x, int y) {
			this.x=x;this.y=y;
		}
	}

}
