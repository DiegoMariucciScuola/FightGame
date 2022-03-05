package it.edu.iisgubbio.GameFight;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
	Pane areaDiGioco = new Pane();
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
        Image iBase = new Image(getClass().getResourceAsStream("bg1.gif"));
        ImageView sfondo = new ImageView(iBase);
        sfondo.setPreserveRatio(true);
        sfondo.setFitWidth(1000);
        areaDiGioco.getChildren().add(sfondo);
        
        
        
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
}
