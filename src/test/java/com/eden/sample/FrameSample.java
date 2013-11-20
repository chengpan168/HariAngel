package com.eden.sample;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FrameSample extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		primaryStage.setTitle("hello") ;
		File f = new File("image/ico.png") ;
		primaryStage.setIconified(false) ;
		Image image = new Image(new FileInputStream(f)) ;
		primaryStage.getIcons().add(image);
		
		
		ImageView imageView  = new ImageView(image) ;
		Button btn = new Button("click me" , imageView) ;
		btn.setContentDisplay(ContentDisplay.LEFT) ;
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println(event) ;
			}
		});
//		primaryStage.setOpacity(0.) ;
		StackPane root = new StackPane() ;
		root.getChildren().add(btn) ;
		
		btn.setPrefSize(100, 50) ;
		
		primaryStage.setScene(new Scene(root, 200, 300)) ;
		primaryStage.show() ;
	}

	public static void main(String[] args) {
		launch(args) ;
	}
}
