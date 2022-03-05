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
	
	Rectangle rettangolo = new Rectangle(180,249);

	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
		//imposto lo sfondo
        Image iBase = new Image(getClass().getResourceAsStream("bg1.gif"));
        ImageView sfondo = new ImageView(iBase);
        sfondo.setPreserveRatio(true);
        sfondo.setFitWidth(1000);
        areaDiGioco.getChildren().add(sfondo);
        
        
        //Image Base = new Image(getClass().getResourceAsStream("player1.gif"));
        //rettangolo.setImage(Base);
        //Image img = new Image("player1.gif");
        Image img = new Image(getClass().getResourceAsStream("player1.gif"));
        //ImageView player1 = new ImageView(img);
        rettangolo.setFill(new ImagePattern(img));
        rettangolo.setX(200);
        rettangolo.setY(320);
        //rettangolo.setFill(Color.WHITE);
        areaDiGioco.getChildren().add(rettangolo);
        
        
        
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
}
