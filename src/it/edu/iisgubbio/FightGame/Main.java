package it.edu.iisgubbio.FightGame;

import java.awt.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Bounds;
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
	//variabili generali
	Pane areaDiGioco = new Pane();
	double tempo=0.010;
	boolean giocoIniziato=false;
	
	/*
	 * personaggi:
	 * 
	 * Axel=Axel
	 * Dr Gilbert=dr
	 */
	
	//variabili Axel
	boolean VersoAxel=true;
	/*
	 * Verso:
	 * true=destra
	 * false=sinistra
	 */
	boolean staCamminandoAxel=false;
	boolean staSaltandoAxel=false;
	boolean staSaltandoAvantiAxel=false;
	boolean staTirandoPugnoAxel=false;
	boolean problemaPugnoAxel=false;	
	boolean vittoriaAxel=false;	
	boolean compenetratoAxel=false;

	int contCamminaAxel=0;
	int contSaltoAxel=0;
	int contSaltoAvantiAxel=0;
	int contPugnoAxel=0;
	double VitaAxel=100;
	
	//variabili dr
	boolean VersoDr=false;
	boolean staCamminandoDr=false;
	boolean staSaltandoDr=false;
	boolean staSaltandoAvantiDr=false;
	boolean staTirandoPugnoDr=false;
	boolean problemaPugnoDr=false;	
	boolean vittoriaDr=false;	
	boolean compenetratoDr=false;
	
	int contCamminaDr=0;
	int contSaltoDr=0;
	int contSaltoAvantiDr=0;
	int contPugnoDr=0;
	double VitaDr=100;
	
	//rettangoli personaggi
	Rectangle Axel = new Rectangle(180,249);
	Rectangle Dr = new Rectangle(180,249);
	
	/*componenti interfaccia interfaccia:
	 * 
	 * mini loghi axel e dr
	 * loghi vittoria axel e dr
	 * barra della vita sia quella sotto grigia(RVita-)si quella verde che diminuisce(RVita-2)
	 * pezzi dell'interfaccia:
	 * 		start
	 *		wasd
	 *		frecce
	 *		fight
	 * 		youWin
	 */
	Rectangle AxelLogo = new Rectangle(70,92);
	Rectangle DrLogo = new Rectangle(70,92);
	
	Rectangle AxelLogoVittoria = new Rectangle(210,276);
	Rectangle DrLogoVittoria = new Rectangle(210,276);
	
	Rectangle RVitaAxel = new Rectangle(300,30);
	Rectangle RVitaAxel2 = new Rectangle(300,30);
	Rectangle RVitaDr = new Rectangle(300,30);
	Rectangle RVitaDr2 = new Rectangle(300,30);
	
	Rectangle Start = new Rectangle(350,150);
	Rectangle wasd = new Rectangle(190,179);
	Rectangle frecce = new Rectangle(190,179);
	Rectangle Fight = new Rectangle(430,230);
	Rectangle youWin = new Rectangle(330,157);
	
	
	
	
	/*
	 * importo tutte le immagini
	 * 
	 * TUTTI I TIPI DI ANIMAZIONI:
	 * riposo
	 * camminata
	 * pugno
	 * salto
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
	Image AxelSaltoInv = new Image(getClass().getResourceAsStream("axel-salto-inv.gif"));
	Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto.gif"));
	Image DrSaltoInv = new Image(getClass().getResourceAsStream("dr-salto-inv.gif"));
	
	//immagini interfaccia
	Image ImgStart = new Image(getClass().getResourceAsStream("start.png"));
	Image ImgWasd = new Image(getClass().getResourceAsStream("wasd.png"));
	Image ImgFrecce = new Image(getClass().getResourceAsStream("frecce.png"));
	Image ImgFight = new Image(getClass().getResourceAsStream("fight.png"));
	Image ImgYouWin = new Image(getClass().getResourceAsStream("you-win.png"));
	
	//immagini logo  axel e dr
	Image ImgAxelLogo = new Image(getClass().getResourceAsStream("axel-logo.png"));
	Image ImgDrLogo = new Image(getClass().getResourceAsStream("dr-logo.jpg"));
	

	
	@Override
    public void start(Stage primaryStage) throws Exception {
		
		//imposto lo sfondo
        Image iBase = new Image(getClass().getResourceAsStream("bg3.gif"));
        ImageView sfondo = new ImageView(iBase);
        sfondo.setPreserveRatio(true);
        sfondo.setFitWidth(1000);
        areaDiGioco.getChildren().add(sfondo);
        
        //imposto barra della vita che sta sotto axel
        RVitaAxel2.setFill(javafx.scene.paint.Color.GREY);
        RVitaAxel2.setX(90);
        RVitaAxel2.setY(30);
        areaDiGioco.getChildren().add(RVitaAxel2);
        
        //imposto barra della vita che sta sotto dr
        RVitaDr2.setFill(javafx.scene.paint.Color.GREY);
        RVitaDr2.setX(610);
        RVitaDr2.setY(30);
        areaDiGioco.getChildren().add(RVitaDr2);
        
        //imposto barra della vita verde axel
        RVitaAxel.setFill(javafx.scene.paint.Color.GREEN);
        RVitaAxel.setX(90);
        RVitaAxel.setY(30);
        areaDiGioco.getChildren().add(RVitaAxel);
        
        //imposto barra della vita verde dr
        RVitaDr.setFill(javafx.scene.paint.Color.GREEN);
        RVitaDr.setX(610);
        RVitaDr.setY(30);
        areaDiGioco.getChildren().add(RVitaDr);
        
        //imposto il personaggio axel
        Axel.setFill(new ImagePattern(AxelRiposo));
        Axel.setX(20);
        Axel.setY(340);
        areaDiGioco.getChildren().add(Axel);
        
        //imposto il personaggio dr
        Dr.setFill(new ImagePattern(DrRiposo));
        Dr.setX(800);
        Dr.setY(340);
        areaDiGioco.getChildren().add(Dr);
        
        //imposto il logo di axel
        AxelLogo.setFill(new ImagePattern(ImgAxelLogo));
        AxelLogo.setX(20);
        AxelLogo.setY(5);
        areaDiGioco.getChildren().add(AxelLogo);
        
        //imposto il logo di dr
        DrLogo.setFill(new ImagePattern(ImgDrLogo));
        DrLogo.setX(910);
        DrLogo.setY(5);
        areaDiGioco.getChildren().add(DrLogo);

        //imposto le parti dell'interfaccia
        AxelLogoVittoria.setFill(new ImagePattern(ImgAxelLogo));
        DrLogoVittoria.setFill(new ImagePattern(ImgDrLogo));
        
        Start.setFill(new ImagePattern(ImgStart));
        Start.setX(325);
        Start.setY(400);
        areaDiGioco.getChildren().add(Start);
        
        wasd.setFill(new ImagePattern(ImgWasd));
        wasd.setX(20);
        wasd.setY(160);
        areaDiGioco.getChildren().add(wasd);
        
        frecce.setFill(new ImagePattern(ImgFrecce));
        frecce.setX(800);
        frecce.setY(160);
        areaDiGioco.getChildren().add(frecce);
        
        Fight.setFill(new ImagePattern(ImgFight));
        Fight.setX(285);
        Fight.setY(120);
        areaDiGioco.getChildren().add(Fight);
        
        youWin.setFill(new ImagePattern(ImgYouWin));
        
        //imposto il bottone inizia partita
        Start.setOnMouseClicked(
	            event -> {giocoIniziato=true; 
	            Fight.setX(1285);
	            wasd.setX(1285);
	            frecce.setX(1285);
	            Start.setX(1285);
	            areaDiGioco.getChildren().remove(AxelLogoVittoria);
	            areaDiGioco.getChildren().remove(DrLogoVittoria);
	            areaDiGioco.getChildren().remove(youWin);
	            }
	    );
        
        //imposto la finesatra
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("Fight Game");
        primaryStage.show();
        
        //imposto la timeline
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.seconds(tempo), 
                x -> ogniTempo()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        scena.setOnKeyPressed(e->movimenti(e));  
	}
	
	private void movimenti(KeyEvent e) {

		if(giocoIniziato==true) {
			
			//prendo i comandi dei movimenti di Axel
			//verso sinistra
			if(e.getText().equals("a")) {
				if(staSaltandoAvantiAxel==false && staSaltandoAxel==false && staTirandoPugnoAxel==false) {
					VersoAxel=false;
					staCamminandoAxel=true;
					contCamminaAxel=20;
					Axel.setWidth(180);
				}
				
			}
			
			//verso destra
			if(e.getText().equals("d")) {
				if(staSaltandoAvantiAxel==false && staSaltandoAxel==false && staTirandoPugnoAxel==false) {
					VersoAxel=true;
					staCamminandoAxel=true;
					contCamminaAxel=20;
					Axel.setWidth(180);
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
							contSaltoAvantiAxel=100;
						}else if(VersoAxel==false) {
							staSaltandoAvantiAxel=true;
							Axel.setY(Axel.getY()-111);
							Axel.setHeight(360);
							Image AxelSalto = new Image(getClass().getResourceAsStream("axel-salto-inv.gif"));
							Axel.setFill(new ImagePattern(AxelSalto));
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
			
			//pugno
			if(e.getText().equals("s")) {
				if(staSaltandoAvantiAxel==false && staSaltandoAxel==false && staCamminandoAxel==false&& staTirandoPugnoAxel==false) {
					Image AxelPugno = new Image(getClass().getResourceAsStream("axel-pugno3.gif"));
					Image AxelPugnoInv = new Image(getClass().getResourceAsStream("axel-pugno-inv.gif"));
					if(VersoAxel==true) {
						Axel.setFill(new ImagePattern(AxelPugno));
						Axel.setWidth(350);
					}else if(VersoAxel==false) {
						Axel.setFill(new ImagePattern(AxelPugnoInv));
						Axel.setX(Axel.getX()-170);
						Axel.setWidth(350);
						problemaPugnoAxel=true;

					
					}
					contPugnoAxel=20;
					staTirandoPugnoAxel=true;
				}
			}
						
			//prendo i comandi dei movimenti di Dr
			//verso sinistra
			if(e.getCode()==KeyCode.LEFT) {
				if(staSaltandoAvantiDr==false&&staSaltandoDr==false) {
					VersoDr=false;
					staCamminandoDr=true;
					contCamminaDr=20;
				}
			}
			
			//verso destra
			if(e.getCode()==KeyCode.RIGHT) {
				if(staSaltandoAvantiDr==false&&staSaltandoDr==false) {
					VersoDr=true;
					staCamminandoDr=true;
					contCamminaDr=20;
				}
			}
			
			//salto
			if(e.getCode()==KeyCode.UP) {
				if(staCamminandoDr==true) {
					if(staSaltandoAvantiDr==false) {
						if(VersoDr==true) {
							staSaltandoAvantiDr=true;
							Dr.setY(Dr.getY()-111);
							Dr.setHeight(360);
							Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto.gif"));
							Dr.setFill(new ImagePattern(DrSalto));
							contSaltoAvantiDr=100;
						}else if(VersoDr==false) {
							staSaltandoAvantiDr=true;
							Dr.setY(Dr.getY()-111);
							Dr.setHeight(360);
							Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto-inv.gif"));
							Dr.setFill(new ImagePattern(DrSalto));
							contSaltoAvantiDr=100;
						}
						
					}
					
				}else if(staSaltandoDr==false) {
					Dr.setY(Dr.getY()-111);
					Dr.setHeight(360);
					Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto.gif"));
					if(VersoDr==true) {
						DrSalto = new Image(getClass().getResourceAsStream("dr-salto.gif"));
					}else if(VersoDr==false) {
						DrSalto = new Image(getClass().getResourceAsStream("dr-salto-inv.gif"));
					}
					
					Dr.setFill(new ImagePattern(DrSalto));
					staSaltandoDr=true;
					contSaltoDr=100;
				}	
			}
			
			//pugno
			if(e.getCode()==KeyCode.DOWN) {
				
				if(staSaltandoAvantiDr==false && staSaltandoDr==false && staCamminandoDr==false&& staTirandoPugnoDr==false) {
					Image DrPugno = new Image(getClass().getResourceAsStream("dr-pugno.gif"));
					Image DrPugnoInv = new Image(getClass().getResourceAsStream("dr-pugno-inv.gif"));
					if(VersoDr==true) {
						Dr.setFill(new ImagePattern(DrPugno));
						Dr.setWidth(350);
					}else if(VersoDr==false) {
						Dr.setFill(new ImagePattern(DrPugnoInv));
						Dr.setX(Dr.getX()-170);
						Dr.setWidth(350);
						problemaPugnoDr=true;
					}
					contPugnoDr=20;
					staTirandoPugnoDr=true;
				}
			}
		}
	}
	
	private void ogniTempo() {
		//imposto i bounds pere le collisioni di axel e dr
		Bounds b1 = Axel.getBoundsInParent();
        Bounds b2 = Dr.getBoundsInParent();
        

		if(Axel.getX()+90>(Dr.getX())+230||Axel.getX()+90<(Dr.getX())-50) {
			compenetratoAxel=false;
		}
		
		if(Dr.getX()+90>(Axel.getX())+230||Dr.getX()+90<(Axel.getX())-50) {
			compenetratoDr=false;
		}
		
		//controllo le compenetrazioni dei movimenti di axel su dr
		if(compenetratoAxel==true) {
			if(Axel.getX()+90<(Dr.getX())+90) {
				Axel.setX(Axel.getX()-4);
			}else {
				Axel.setX(Axel.getX()+4);
			}
		}
		
		//controllo le compenetrazioni dei movimenti di dr su axel
		if(compenetratoDr==true) {
			if(Dr.getX()+90<(Axel.getX())+90) {
				Dr.setX(Dr.getX()-4);
			}else {
				Dr.setX(Dr.getX()+4);
			}
		}
		
		/////////////imposto i movimenti di axel////////////////
		
		//imposto il salto avanti o indietro (non in alto) di axel
		if(staSaltandoAvantiAxel==true) {
			if(VersoAxel==true) {
				if(contSaltoAvantiAxel>=50) {
					Axel.setY(Axel.getY()-4);
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+4);
					}
				}else {
					Axel.setY(Axel.getY()+4);
					if(Axel.getX()+90<=(Dr.getX())+130&&Axel.getX()+90>=(Dr.getX())+50) {
						if(contSaltoAvantiAxel<30) {
							compenetratoAxel=true;
						}
					}
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+4);
					}
				}

				contSaltoAvantiAxel--;
				contCamminaAxel--;
				contSaltoAxel--;
			}else if(VersoAxel==false) {
				if(contSaltoAvantiAxel>=50) {
					Axel.setY(Axel.getY()-4);
					
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-4);
					}
				}else {
					Axel.setY(Axel.getY()+4);
					if(Axel.getX()+90<=(Dr.getX())+130&&Axel.getX()+90>=(Dr.getX())+50) {
						if(contSaltoAvantiAxel<30) {
							compenetratoAxel=true;
						}
					}
					
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-4);
					}
				}
				contSaltoAvantiAxel--;
				contCamminaAxel--;
				contSaltoAxel--;
			}
		
		//imposto il salto in alto (non a destra e a sinistra) di axel
		}else if(staSaltandoAxel==true) {
			if(contSaltoAxel>=50) {
				Axel.setY(Axel.getY()-4);
			}else {
				Axel.setY(Axel.getY()+4);
			}
			contSaltoAxel--;
		
		//imposto il pugno di axel
		}else if(staTirandoPugnoAxel==true) {
			if((Axel.getX())<=Dr.getX()) {
				if(VersoAxel==true) {
					if(b1.intersects(b2)) {
						VitaDr-=0.25;
						RVitaDr.setWidth(VitaDr*3);
						RVitaDr.setX(610+300-VitaDr*3);
						if(VitaDr<0) {
							vittoriaAxel=true;
						}
					}
				}
			}
			
			if((Axel.getX()+200)>=Dr.getX()){
				if(VersoAxel==false) {
					if(b1.intersects(b2)) {
						VitaDr-=0.25;
						RVitaDr.setWidth(VitaDr*3);
						RVitaDr.setX(610+300-VitaDr*3);
						
						if(VitaDr<0) {
							vittoriaAxel=true;
						}
					}
				}
			}
			contPugnoAxel--;
			
		//imposto la camminata sia destra che a sinitra di axel
		}else if(staCamminandoAxel==true) {
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelCamminata));
				
				if((Axel.getX()+140)<=Dr.getX()) {
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+3);
					}
				}else if(Axel.getX()>=(Dr.getX())+130) {
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+3);
					}
				}
				
			}else {
				Axel.setFill(new ImagePattern(AxelCamminataInv));
				if(Axel.getX()>=(Dr.getX())+140) {
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-3);
					}
				}else if((Axel.getX()+130)<=Dr.getX()) {
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-3);
					}
				}
			}
			contCamminaAxel--;
		}
		//imposto un limite di mappa nel caso ci fossero dei bug di axel
		if(Axel.getY()>600) {
			Axel.setHeight(249);
			Axel.setY(340);
		}
		
		//se axel non sta facendo niente imposta l'animazione di riposo
		if(contCamminaAxel<=0&&contSaltoAxel<=0&&contSaltoAvantiAxel<=0&&contPugnoAxel<=0) {
			staCamminandoAxel=false;
			staSaltandoAxel=false;
			staSaltandoAvantiAxel=false;
			staTirandoPugnoAxel=false;
			Axel.setHeight(249);
			Axel.setWidth(180);
			Axel.setY(340);
			
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelRiposo));
			}else {
				if(problemaPugnoAxel==true) {
					Axel.setX(Axel.getX()+170);
					problemaPugnoAxel=false;
				}
				Axel.setFill(new ImagePattern(AxelRiposoInv));
			}
		}

		/////////////imposto i movimenti di dr////////////////		
		
		//imposto il salto avanti o indietro (non in alto) di dr
		if(staSaltandoAvantiDr==true) {
			if(VersoDr==true) {
				if(contSaltoAvantiDr>=50) {
					Dr.setY(Dr.getY()-4);
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+4);
					}
				}else {
					Dr.setY(Dr.getY()+4);
					
					if(Dr.getX()+90<=(Axel.getX())+130&&Dr.getX()+90>=(Axel.getX())+50) {
						if(contSaltoAvantiDr<30) {
							compenetratoDr=true;
						}
					}
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+4);
					}
				}
				contSaltoAvantiDr--;
				contCamminaDr--;
				contSaltoDr--;
			}else if(VersoDr==false) {
				if(contSaltoAvantiDr>=50) {
					Dr.setY(Dr.getY()-4);
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-4);
					}
				}else {
					Dr.setY(Dr.getY()+4);
					if(Dr.getX()+90<=(Axel.getX())+130&&Dr.getX()+90>=(Axel.getX())+50) {
						if(contSaltoAvantiDr<30) {
							compenetratoDr=true;
						}
						
					}
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-4);
					}
				}
				contSaltoAvantiDr--;
				contCamminaDr--;
				contSaltoDr--;
			}
			
		//imposto il salto in alto (non a destra e a sinistra) di dr	
		}else if(staSaltandoDr==true) {
			if(contSaltoDr>=50) {
				Dr.setY(Dr.getY()-4);
			}else {
				Dr.setY(Dr.getY()+4);
			}
			contSaltoDr--;
			
		//imposto il pugno di dr
		}else if(staTirandoPugnoDr==true) {
			if((Dr.getX())<=Axel.getX()) {
				if(VersoDr==true) {
					if(b1.intersects(b2)) {
						VitaAxel-=0.25;
						RVitaAxel.setWidth(VitaAxel*3);
						if(VitaAxel<0) {
							vittoriaDr=true;
						}
					}
				}
			}
			if((Dr.getX()+200)>=Axel.getX()){
				if(VersoDr==false) {
					if(b1.intersects(b2)) {
						VitaAxel-=0.25;
						RVitaAxel.setWidth(VitaAxel*3);
						if(VitaAxel<0) {
							vittoriaDr=true;
						}
					}
				}
			}
			contPugnoDr--;
			
		//imposto la camminata sia destra che a sinitra di dr
		}else if(staCamminandoDr==true) {
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrCamminata));
				
				if((Dr.getX()+140)<=Axel.getX()) {
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+3);
					}
				}else if(Dr.getX()>=(Axel.getX())+130) {
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+3);
					}
				}
				
			}else {
				Dr.setFill(new ImagePattern(DrCamminataInv));
				if(Dr.getX()>=(Axel.getX())+140) {
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-3);
					}
				}else if((Dr.getX()+130)<=Axel.getX()) {
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-3);
					}
				}
			}
			contCamminaDr--;
		}
		
		//imposto un limite di mappa nel caso ci fossero dei bug per dr
		if(Dr.getY()>600) {
			Dr.setHeight(249);
			Dr.setY(340);
		}
		
		//se dr non sta facendo niente imposta l'animazione di riposo
		if(contCamminaDr<=0&&contSaltoDr<=0&&contSaltoAvantiDr<=0&&contPugnoDr<=0) {
			staCamminandoDr=false;
			staSaltandoDr=false;
			staSaltandoAvantiDr=false;
			staTirandoPugnoDr=false;
			Dr.setHeight(249);
			Dr.setWidth(180);
			Dr.setY(340);
			
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrRiposo));
			}else {
				if(problemaPugnoDr==true) {
					Dr.setX(Dr.getX()+170);
					problemaPugnoDr=false;
				}
				Dr.setFill(new ImagePattern(DrRiposoInv));
			}
		}
		
		//se dr vince:
		if(vittoriaDr==true) {
			vittoriaDr=false;
			
			//imposto l'interfaccia
			giocoIniziato=false; 
            wasd.setX(20);
            frecce.setX(800);
            Start.setX(325);
            
            //rimetto in posizione axel e dr
            Axel.setX(20);
            if(VersoDr==false && staTirandoPugnoDr==true) {
            	Dr.setX(630);
            }else {
            	Dr.setX(800);
            }
            VersoAxel=true;
            VersoDr=false;
            VitaAxel=100;
            RVitaAxel.setWidth(100*3);
            VitaDr=100;
            RVitaDr.setWidth(100*3);
            RVitaDr.setX(610);
            
            areaDiGioco.getChildren().add(DrLogoVittoria);
            DrLogoVittoria.setX(395);
            DrLogoVittoria.setY(60);
            
            areaDiGioco.getChildren().add(youWin);
            youWin.setX(320);
            youWin.setY(265);
		}
		
		//se axel vince
		if(vittoriaAxel==true) {
			vittoriaAxel=false;
			
			//imposto l'interfaccia
			giocoIniziato=false; 
            wasd.setX(20);
            frecce.setX(800);
            Start.setX(325);
            
            //posizione axel e dr
            Axel.setX(20);
            if(VersoDr==false && staTirandoPugnoDr==true) {
            	Dr.setX(630);
            }else {
            	Dr.setX(800);
            }

            VersoAxel=true;
            VersoDr=false;
            VitaAxel=100;
            RVitaAxel.setWidth(100*3);
            VitaDr=100;
            RVitaDr.setWidth(100*3);
            RVitaDr.setX(610);
            
            areaDiGioco.getChildren().add(AxelLogoVittoria);
            AxelLogoVittoria.setX(395);
            AxelLogoVittoria.setY(60);
            
            areaDiGioco.getChildren().add(youWin);
            youWin.setX(320);
            youWin.setY(265);
		}
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}