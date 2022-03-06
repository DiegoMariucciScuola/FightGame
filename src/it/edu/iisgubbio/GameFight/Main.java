package it.edu.iisgubbio.GameFight;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	Pane areaDiGioco = new Pane();
	double tempo=0.010;
	/*
	 * Verso:
	 * true=destra
	 * false=sinistra
	 */
	Boolean Verso=true;
	Boolean staCamminando=false;
	
	int contRiposo=0;
	
	Rectangle Axel = new Rectangle(180,249);
	Rectangle Dr = new Rectangle(180,249);
	
	
	
	/*
	 * importo tutte le immagini
	 * 
	 * TUTTI I TIPI DI ANIMAZIONI:
	 * riposo
	 * camminata
	 * pugno
	 * colpito
	 * salto
	 * accucciato
	 * 
	 */
	
	
	//riposo
	Image AxelRiposo = new Image(getClass().getResourceAsStream("axel-riposo.gif"));
	Image DrRiposo = new Image(getClass().getResourceAsStream("dr-riposo.gif"));
	Image AxelRiposoInv = new Image(getClass().getResourceAsStream("axel-riposo-inv.gif"));
	Image DrRiposoInv = new Image(getClass().getResourceAsStream("dr-riposo-inv.gif"));
	
	//camminata
	Image AxelCamminata = new Image(getClass().getResourceAsStream("axel-camminata.gif"));
	Image AxelCamminataInv = new Image(getClass().getResourceAsStream("axel-camminata-inv.gif"));
	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
		//imposto lo sfondo
        Image iBase = new Image(getClass().getResourceAsStream("bg1.gif"));
        ImageView sfondo = new ImageView(iBase);
        sfondo.setPreserveRatio(true);
        sfondo.setFitWidth(1000);
        areaDiGioco.getChildren().add(sfondo);
        
        
        Axel.setFill(new ImagePattern(AxelRiposo));
        Axel.setX(200);
        Axel.setY(320);
        areaDiGioco.getChildren().add(Axel);
        
        Dr.setFill(new ImagePattern(DrRiposo));
        Dr.setX(600);
        Dr.setY(320);
        areaDiGioco.getChildren().add(Dr);
        
        
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
        
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(tempo), 
                x -> ogniTempo()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        scena.setOnKeyPressed(e->movimenti(e));  
	}
	private void movimenti(KeyEvent e) {
		if(e.getText().equals("a")) {
			Axel.setX(Axel.getX()-24);
			Verso=false;
			staCamminando=true;
			contRiposo=40;
		}
		if(e.getText().equals("d")) {
			Axel.setX(Axel.getX()+24);
			Verso=true;
			staCamminando=true;
			contRiposo=40;
		}
		
	}
	private void ogniTempo() {
		
		if(staCamminando==true) {
			if(Verso==true) {
				Axel.setFill(new ImagePattern(AxelCamminata));
			}else {
				Axel.setFill(new ImagePattern(AxelCamminataInv));
			}
			
			contRiposo--;
		}
		if(contRiposo==0) {
			staCamminando=false;
			if(Verso==true) {
				Axel.setFill(new ImagePattern(AxelRiposo));
			}else {
				Axel.setFill(new ImagePattern(AxelRiposoInv));
			}
		}
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }
}
