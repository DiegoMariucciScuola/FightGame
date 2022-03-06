package it.edu.iisgubbio.GameFight;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
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
	Boolean VersoAxel=true;
	Boolean staCamminandoAxel=false;
	int contRiposoAxel=0;
	
	Boolean VersoDr=false;
	Boolean staCamminandoDr=false;
	int contRiposoDr=0;
	
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
	Image DrCamminata = new Image(getClass().getResourceAsStream("dr-camminata.gif"));
	Image DrCamminataInv = new Image(getClass().getResourceAsStream("dr-camminata-inv.gif"));
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
		//imposto lo sfondo
        Image iBase = new Image(getClass().getResourceAsStream("bg3.gif"));
        ImageView sfondo = new ImageView(iBase);
        sfondo.setPreserveRatio(true);
        sfondo.setFitWidth(1000);
        areaDiGioco.getChildren().add(sfondo);
        
        
        Axel.setFill(new ImagePattern(AxelRiposo));
        Axel.setX(20);
        Axel.setY(340);
        areaDiGioco.getChildren().add(Axel);
        
        Dr.setFill(new ImagePattern(DrRiposo));
        Dr.setX(800);
        Dr.setY(340);
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
		
		//prendo i comandi dei movimenti di Axel
		if(e.getText().equals("a")) {
			//Axel.setX(Axel.getX()-24);
			VersoAxel=false;
			staCamminandoAxel=true;
			contRiposoAxel=20;
		}
		if(e.getText().equals("d")) {
			//Axel.setX(Axel.getX()+24);
			VersoAxel=true;
			staCamminandoAxel=true;
			contRiposoAxel=20;
		}
		
		//prendo i comandi dei movimenti di Dr
		if(e.getCode()==KeyCode.LEFT) {
			//Dr.setX(Dr.getX()-24);
			VersoDr=false;
			staCamminandoDr=true;
			contRiposoDr=20;
		}
		if(e.getCode()==KeyCode.RIGHT) {
			//Dr.setX(Dr.getX()+24);
			VersoDr=true;
			staCamminandoDr=true;
			contRiposoDr=20;
		}
	}
	private void ogniTempo() {
		
		
		//regolo il movimento, il verso e quando smettere di camminare di axel
		if(staCamminandoAxel==true) {
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelCamminata));
				if((Axel.getX()+180)<Dr.getX()||Axel.getX()>(Dr.getX())+180) {
					Axel.setX(Axel.getX()+3);
				}
			}else {
				Axel.setFill(new ImagePattern(AxelCamminataInv));
				if((Axel.getX()+180)<Dr.getX()||Axel.getX()>(Dr.getX())+180) {
					Axel.setX(Axel.getX()-3);
				}
			}
			
			contRiposoAxel--;
		}
		if(contRiposoAxel==0) {
			staCamminandoAxel=false;
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelRiposo));
			}else {
				Axel.setFill(new ImagePattern(AxelRiposoInv));
			}
		}
		
		//regolo il movimento, il verso e quando smettere di camminare di Dr
		if(staCamminandoDr==true) {
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrCamminata));
				if((Dr.getX()+180)<Axel.getX()||Dr.getX()>(Axel.getX())+180) {
					Dr.setX(Dr.getX()+3);
				}
				//Dr.setX(Dr.getX()+3);
			}else {
				Dr.setFill(new ImagePattern(DrCamminataInv));
				if((Dr.getX()+180)<Axel.getX()||Dr.getX()>(Axel.getX())+180) {
					Dr.setX(Dr.getX()-3);
				}
				//Dr.setX(Dr.getX()-3);
			}
			contRiposoDr--;
		}
		if(contRiposoDr==0) {
			staCamminandoDr=false;
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrRiposo));
			}else {
				Dr.setFill(new ImagePattern(DrRiposoInv));
			}
		}
		
		
	}
	
	
	public static void main(String[] args) {
        launch(args);
    }
}
