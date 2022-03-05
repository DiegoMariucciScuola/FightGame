package it.edu.iisgubbio.GameFight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	Pane areaDiGioco = new Pane();
	
	
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
	
	Image AxelRiposo = new Image(getClass().getResourceAsStream("axel-riposoP.gif"));
	Image DrRiposo = new Image(getClass().getResourceAsStream("dr-riposo.gif"));
	
	
	
	
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
	}
	public static void main(String[] args) {
        launch(args);
    }
}
