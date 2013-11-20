package com.eden.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.junit.Test;

import com.eden.constant.Constant;
import com.eden.fxmvc.context.AppContext;
import com.eden.pane.position.PositionPane;

public class PositionPaneTest extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		AppContext.init();
		AppContext.setAttribute(Constant.WINDOW, stage) ;
		StackPane root = new StackPane() ;
		root.setAlignment(Pos.BASELINE_CENTER);
		root.setStyle("-fx-background-color:rgb(200 ,100 ,100)") ;
		PositionPane positionPane = new PositionPane() ;
		root.getChildren().add(positionPane);
		Scene scene = new Scene(root , 800 ,600) ;
		scene.getStylesheets().add("css/hairangel.css");
		stage.setScene(scene) ;
		stage.show() ;
		
	}
	
	public static void main(String[] args) {
		launch(args) ;
	}
	
	@Test
	public void testBoolean(){
		System.out.println(Boolean.valueOf("true")) ;
	}
	
}
