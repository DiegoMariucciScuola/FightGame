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
	Boolean staSaltandoAxel=false;
	Boolean staSaltandoAvantiAxel=false;
	int contCamminaAxel=0;
	int contSaltoAxel=0;
	int contSaltoAvantiAxel=0;
	
	Boolean VersoDr=false;
	Boolean staCamminandoDr=false;
	Boolean staSaltandoDr=false;
	Boolean staSaltandoAvantiDr=false;
	int contCamminaDr=0;
	int contSaltoDr=0;
	int contSaltoAvantiDr=0;
	
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
	
	//salto
	//Image AxelSalto = new Image(getClass().getResourceAsStream("axel-salto.gif"));
	Image AxelSaltoInv = new Image(getClass().getResourceAsStream("axel-salto-inv.gif"));
	Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto.gif"));
	Image DrSaltoInv = new Image(getClass().getResourceAsStream("dr-salto-inv.gif"));
	
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
		//verso sinistra
		if(e.getText().equals("a")) {
			//Axel.setX(Axel.getX()-24);
			if(staSaltandoAvantiAxel==false&&staSaltandoAxel==false) {
				VersoAxel=false;
				staCamminandoAxel=true;
				contCamminaAxel=20;
			}
			
		}
		//verso destra
		if(e.getText().equals("d")) {
			//Axel.setX(Axel.getX()+24);
			if(staSaltandoAvantiAxel==false&&staSaltandoAxel==false) {
				VersoAxel=true;
				staCamminandoAxel=true;
				contCamminaAxel=20;
			}
			
		}
		//salto
		if(e.getText().equals("w")) {
			
			if(staCamminandoAxel==true) {
				if(staSaltandoAvantiAxel==false) {
					if(VersoAxel==true) {
						staSaltandoAvantiAxel=true;
						Axel.setY(Axel.getY()-111);
						Axel.setHeight(360);
						Image AxelSalto = new Image(getClass().getResourceAsStream("axel-salto.gif"));
						Axel.setFill(new ImagePattern(AxelSalto));
						//staSaltandoAxel=true;
						contSaltoAvantiAxel=100;
					}else if(VersoAxel==false) {
						staSaltandoAvantiAxel=true;
						Axel.setY(Axel.getY()-111);
						Axel.setHeight(360);
						Image AxelSalto = new Image(getClass().getResourceAsStream("axel-salto-inv.gif"));
						Axel.setFill(new ImagePattern(AxelSalto));
						//staSaltandoAxel=true;
						contSaltoAvantiAxel=100;
					}
					
				}
				
			}else if(staSaltandoAxel==false) {
				Axel.setY(Axel.getY()-111);
				Axel.setHeight(360);
				Image AxelSalto = new Image(getClass().getResourceAsStream("axel-salto.gif"));
				if(VersoAxel==true) {
					AxelSalto = new Image(getClass().getResourceAsStream("axel-salto.gif"));
				}else if(VersoAxel==false) {
					AxelSalto = new Image(getClass().getResourceAsStream("axel-salto-inv.gif"));
				}
				
				Axel.setFill(new ImagePattern(AxelSalto));
				staSaltandoAxel=true;
				contSaltoAxel=100;
			}
			
		}
		
		//prendo i comandi dei movimenti di Dr
		//verso sinistra
		if(e.getCode()==KeyCode.LEFT) {
			//Dr.setX(Dr.getX()-24);
			VersoDr=false;
			staCamminandoDr=true;
			contCamminaDr=20;
		}
		//verso destra
		if(e.getCode()==KeyCode.RIGHT) {
			//Dr.setX(Dr.getX()+24);
			VersoDr=true;
			staCamminandoDr=true;
			contCamminaDr=20;
		}
		//salto
		if(e.getCode()==KeyCode.UP) {
			/*
			if(staCamminandoDr==true) {
				if(staSaltandoAvantiDr==false) {
					staSaltandoAvantiDr=true;
					Dr.setY(Dr.getY()-111);
					Dr.setHeight(360);
					Image DrSalto = new Image(getClass().getResourceAsStream("Dr-salto.gif"));
					Dr.setFill(new ImagePattern(DrSalto));
					//staSaltandoAxel=true;
					contSaltoAvantiDr=100;
				}
				
			}else if(staSaltandoDr==false) {
				Dr.setY(Dr.getY()-111);
				Dr.setHeight(360);
				Image AxelSalto = new Image(getClass().getResourceAsStream("Dr-salto.gif"));
				Dr.setFill(new ImagePattern(DrSalto));
				staSaltandoDr=true;
				contSaltoDr=100;
			}
			*/
			staSaltandoDr=true;
			contSaltoDr=20;
		}
	}
	private void ogniTempo() {
		
		/*
		 * stasaltandoavanti è vero{
		 * 		imposto il salto in avanti
		 * }se invece sta solo camminando{
		 * 		inmposto il cammino
		 * }se sta solo saltando{
		 * 		imposto il salto sul posto
		 * }
		 */
		
		
		if(staSaltandoAvantiAxel==true) {
			if(VersoAxel==true) {
				if(contSaltoAvantiAxel>=50) {
					Axel.setY(Axel.getY()-4);
					Axel.setX(Axel.getX()+4);
					//System.out.println("+");
				}else {
					Axel.setY(Axel.getY()+4);
					//System.out.println("-");
					Axel.setX(Axel.getX()+4);
				}
				System.out.println(contSaltoAvantiAxel);
				contSaltoAvantiAxel--;
				contCamminaAxel--;
				contSaltoAxel--;
			}else if(VersoAxel==false) {
				if(contSaltoAvantiAxel>=50) {
					Axel.setY(Axel.getY()-4);
					Axel.setX(Axel.getX()-4);
					//System.out.println("+");
				}else {
					Axel.setY(Axel.getY()+4);
					//System.out.println("-");
					Axel.setX(Axel.getX()-4);
				}
				System.out.println(contSaltoAvantiAxel);
				contSaltoAvantiAxel--;
				contCamminaAxel--;
				contSaltoAxel--;
			}
			
			
		}else if(staSaltandoAxel==true) {
			if(contSaltoAxel>=50) {
				Axel.setY(Axel.getY()-4);
			}else {
				Axel.setY(Axel.getY()+4);
			}
			contSaltoAxel--;
			//System.out.println("-");
		}else if(staCamminandoAxel==true) {
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelCamminata));
				
				if((Axel.getX()+140)<=Dr.getX()) {
					Axel.setX(Axel.getX()+3);
				}else if(Axel.getX()>=(Dr.getX())+130) {
					Axel.setX(Axel.getX()+3);
				}
				
				
			}else {
				Axel.setFill(new ImagePattern(AxelCamminataInv));
				if(Axel.getX()>=(Dr.getX())+140) {
					Axel.setX(Axel.getX()-3);
				}else if((Axel.getX()+130)<=Dr.getX()) {
					Axel.setX(Axel.getX()-3);
				}
			}
			contCamminaAxel--;
		}
		if(Axel.getY()>600) {
			Axel.setHeight(249);
			Axel.setY(340);
		}
		

		if(contCamminaAxel<=0&&contSaltoAxel<=0&&contSaltoAvantiAxel<=0) {
			staCamminandoAxel=false;
			staSaltandoAxel=false;
			//System.out.println("---------");
			staSaltandoAvantiAxel=false;
			Axel.setHeight(249);
			Axel.setY(340);
			
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelRiposo));
			}else {
				Axel.setFill(new ImagePattern(AxelRiposoInv));
			}
		}
		

		
		
		
		
		
		
		
		
		
		//regolo il movimento, il verso 
		if(staCamminandoDr==true) {
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrCamminata));
				if((Dr.getX()+180)<Axel.getX()||Dr.getX()>(Axel.getX())+180) {
					Dr.setX(Dr.getX()+3);
				}
			}else {
				Dr.setFill(new ImagePattern(DrCamminataInv));
				if((Dr.getX()+180)<Axel.getX()||Dr.getX()>(Axel.getX())+180) {
					Dr.setX(Dr.getX()-3);
				}
			}
			contCamminaDr--;
		}
		//quando smettere di camminare di Dr
		if(contCamminaDr==0) {
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
