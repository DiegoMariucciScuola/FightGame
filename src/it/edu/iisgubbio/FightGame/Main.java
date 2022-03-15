package it.edu.iisgubbio.FightGame;

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
	boolean compenetratoAxel=false;
	boolean compenetratoDr=false;
	
	/*
	 * Verso:
	 * true=destra
	 * false=sinistra
	 */
	boolean VersoAxel=true;
	boolean staCamminandoAxel=false;
	boolean staSaltandoAxel=false;
	boolean staSaltandoAvantiAxel=false;
	boolean staTirandoPugnoAxel=false;
	boolean problemaPugnoAxel=false;	
	int contCamminaAxel=0;
	int contSaltoAxel=0;
	int contSaltoAvantiAxel=0;
	int contPugnoAxel=0;
	
	boolean VersoDr=false;
	boolean staCamminandoDr=false;
	boolean staSaltandoDr=false;
	boolean staSaltandoAvantiDr=false;
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
	
	//pugno
	//Image AxelPugno = new Image(getClass().getResourceAsStream("axel-pugno2.gif"));
	
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
			if(staSaltandoAvantiAxel==false && staSaltandoAxel==false && staTirandoPugnoAxel==false) {
				VersoAxel=false;
				staCamminandoAxel=true;
				contCamminaAxel=20;
				Axel.setWidth(180);
			}
			
		}
		//verso destra
		if(e.getText().equals("d")) {
			//Axel.setX(Axel.getX()+24);
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
		//pugno
		if(e.getText().equals("s")) {
			
			if(staSaltandoAvantiAxel==false && staSaltandoAxel==false && staCamminandoAxel==false) {
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
			//System.out.println(Axel.getWidth());
			
		}
		
		
		
		
		
		//prendo i comandi dei movimenti di Dr
		//verso sinistra
		if(e.getCode()==KeyCode.LEFT) {
			if(staSaltandoAvantiDr==false&&staSaltandoDr==false) {
				VersoDr=false;
				staCamminandoDr=true;
				contCamminaDr=20;
			}
			
			/*
			//Dr.setX(Dr.getX()-24);
			VersoDr=false;
			staCamminandoDr=true;
			contCamminaDr=20;
			*/
		}
		//verso destra
		if(e.getCode()==KeyCode.RIGHT) {
			if(staSaltandoAvantiDr==false&&staSaltandoDr==false) {
				VersoDr=true;
				staCamminandoDr=true;
				contCamminaDr=20;
			}
			/*
			//Dr.setX(Dr.getX()+24);
			VersoDr=true;
			staCamminandoDr=true;
			contCamminaDr=20;
			*/
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
						//staSaltandoAxel=true;
						contSaltoAvantiDr=100;
					}else if(VersoDr==false) {
						staSaltandoAvantiDr=true;
						Dr.setY(Dr.getY()-111);
						Dr.setHeight(360);
						Image DrSalto = new Image(getClass().getResourceAsStream("dr-salto-inv.gif"));
						Dr.setFill(new ImagePattern(DrSalto));
						//staSaltandoAxel=true;
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
		
		if(Axel.getX()+90>(Dr.getX())+230||Axel.getX()+90<(Dr.getX())-50) {
			compenetratoAxel=false;
		}
		if(Dr.getX()+90>(Axel.getX())+230||Dr.getX()+90<(Axel.getX())-50) {
			compenetratoDr=false;
		}
		if(compenetratoAxel==true) {
			if(Axel.getX()+90<(Dr.getX())+90) {
				Axel.setX(Axel.getX()-4);
			}else {
				Axel.setX(Axel.getX()+4);
			}
		}
		if(compenetratoDr==true) {
			if(Dr.getX()+90<(Axel.getX())+90) {
				Dr.setX(Dr.getX()-4);
			}else {
				Dr.setX(Dr.getX()+4);
			}
		}
		
		
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
					//System.out.println("-");
					if(Axel.getX()+90<=(Dr.getX())+130&&Axel.getX()+90>=(Dr.getX())+50) {
						if(contSaltoAvantiAxel<30) {
							compenetratoAxel=true;
							//System.out.println("comp");
						}
						
					}
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-4);
					}
				}
				//System.out.println(contSaltoAvantiAxel);
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
		}else if(staTirandoPugnoAxel==true) {
			/*if(VersoAxel==true) {
				if(contPugnoAxel>0) {
					Axel.setFill(new ImagePattern(AxelPugno));
					Axel.setWidth(350);
				}
			}else if(VersoAxel==false) {
				if(contPugnoAxel>0) {
					
				}
			}*/
			contPugnoAxel--;
		}else if(staCamminandoAxel==true) {
			if(VersoAxel==true) {
				Axel.setFill(new ImagePattern(AxelCamminata));
				
				if((Axel.getX()+140)<=Dr.getX()) {
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+3);
						//System.out.println(Axel.getX()+4);
					}
					//Axel.setX(Axel.getX()+3);
				}else if(Axel.getX()>=(Dr.getX())+130) {
					if(Axel.getX()+180<1000) {
						Axel.setX(Axel.getX()+3);
						//System.out.println(Axel.getX()+4);
					}
					//Axel.setX(Axel.getX()+3);
				}
				
				
			}else {
				Axel.setFill(new ImagePattern(AxelCamminataInv));
				if(Axel.getX()>=(Dr.getX())+140) {
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-3);
					}
					//Axel.setX(Axel.getX()-3);
				}else if((Axel.getX()+130)<=Dr.getX()) {
					if(Axel.getX()-4>0) {
						Axel.setX(Axel.getX()-3);
						//System.out.println(Axel.getX()-3);
					}
					//Axel.setX(Axel.getX()-3);
				}
			}
			contCamminaAxel--;
		}
		if(Axel.getY()>600) {
			Axel.setHeight(249);
			Axel.setY(340);
		}
		

		if(contCamminaAxel<=0&&contSaltoAxel<=0&&contSaltoAvantiAxel<=0&&contPugnoAxel<=0) {
			staCamminandoAxel=false;
			staSaltandoAxel=false;
			//System.out.println("---------");
			staSaltandoAvantiAxel=false;
			staTirandoPugnoAxel=false;
			Axel.setHeight(249);
			Axel.setWidth(180);
			Axel.setY(340);
			//System.out.println("xdr");
			
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
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		if(staSaltandoAvantiDr==true) {
			if(VersoDr==true) {
				if(contSaltoAvantiDr>=50) {
					Dr.setY(Dr.getY()-4);
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+4);
					}
					//Dr.setX(Dr.getX()+4);
					//System.out.println("+");
				}else {
					
					Dr.setY(Dr.getY()+4);
					//System.out.println("-");
					if(Dr.getX()+90<=(Axel.getX())+130&&Dr.getX()+90>=(Axel.getX())+50) {
						if(contSaltoAvantiDr<30) {
							compenetratoDr=true;
							//System.out.println("+");
						}
					}
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+4);
						//System.out.println(Dr.getX()+4);
					}
					//Dr.setX(Dr.getX()+4);
				}
				//System.out.println(contSaltoAvantiAxel);
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
					//System.out.println("-");
					if(Dr.getX()+90<=(Axel.getX())+130&&Dr.getX()+90>=(Axel.getX())+50) {
						if(contSaltoAvantiDr<30) {
							compenetratoDr=true;
							//System.out.println("comp");
						}
						
					}
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-4);
					}
				}
				//System.out.println(contSaltoAvantiAxel);
				contSaltoAvantiDr--;
				contCamminaDr--;
				contSaltoDr--;
			}
			
			
		}else if(staSaltandoDr==true) {
			if(contSaltoDr>=50) {
				Dr.setY(Dr.getY()-4);
			}else {
				Dr.setY(Dr.getY()+4);
			}
			contSaltoDr--;
			//System.out.println("-");
		}else if(staCamminandoDr==true) {
			if(VersoDr==true) {
				Dr.setFill(new ImagePattern(DrCamminata));
				
				if((Dr.getX()+140)<=Axel.getX()) {
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+3);
						//System.out.println(Axel.getX()+4);
					}
					//Axel.setX(Axel.getX()+3);
				}else if(Dr.getX()>=(Axel.getX())+130) {
					if(Dr.getX()+180<1000) {
						Dr.setX(Dr.getX()+3);
						//System.out.println(Axel.getX()+4);
					}
					//Axel.setX(Axel.getX()+3);
				}
				
				
			}else {
				Dr.setFill(new ImagePattern(DrCamminataInv));
				if(Dr.getX()>=(Axel.getX())+140) {
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-3);
					}
					//Dr.setX(Dr.getX()-3);
				}else if((Dr.getX()+130)<=Axel.getX()) {
					if(Dr.getX()-4>0) {
						Dr.setX(Dr.getX()-3);
						//System.out.println(Axel.getX()-3);
					}
					//Dr.setX(Dr.getX()-3);
				}
			}
			contCamminaDr--;
		}
		if(Dr.getY()>600) {
			Dr.setHeight(249);
			Dr.setY(340);
		}
		

		if(contCamminaDr<=0&&contSaltoDr<=0&&contSaltoAvantiDr<=0) {
			staCamminandoDr=false;
			staSaltandoDr=false;
			//System.out.println("---------");
			staSaltandoAvantiDr=false;
			Dr.setHeight(249);
			Dr.setY(340);
			
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
