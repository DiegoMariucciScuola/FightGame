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
    	areaDiGioco.setPrefSize(400, 400);
        
        Image iBase = new Image(getClass().getResourceAsStream("/res/bg/bg1.gif"));
        ImageView asteroide = new ImageView(iBase);
        asteroide.setPreserveRatio(true);
        asteroide.setFitWidth(500);
        areaDiGioco.getChildren().add(asteroide);
        
        
        
        Scene scena = new Scene(areaDiGioco);
        primaryStage.setScene(scena);
        primaryStage.setTitle("CD");
        primaryStage.show();
	}
	public static void main(String[] args) {
        launch(args);
    }
}
