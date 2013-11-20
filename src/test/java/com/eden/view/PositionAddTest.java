package com.eden.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.eden.pane.position.PositionAddPane;

public class PositionAddTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
/*		Group root = new Group(); 
		root.getChildren().add(new ListView()) ;
*/		
		Scene scene = new Scene(new PositionAddPane() , 800 ,600) ;
		scene.getStylesheets().add("css/hairangel.css");
		stage.setScene(scene) ;
		stage.show() ;
	}

	public static void main(String[] args) {
		launch(args) ;
	}
}
