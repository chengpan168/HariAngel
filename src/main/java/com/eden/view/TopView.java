package com.eden.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class TopView extends BorderPane{
	public TopView(){
		init() ;
	}
	
	public void init() {
		this.getStyleClass().add("top") ;
		Text logo = new Text("H a i r A n g e l") ;
		logo.setFont(Font.font("monospace" , FontWeight.BOLD , 36)) ;
		logo.setFill(Color.valueOf("#994d00")) ;
		
		DropShadow logoShadow = new DropShadow() ;
		logoShadow.setRadius(9) ;
		logoShadow.setOffsetX(10) ;
		logoShadow.setOffsetY(0) ;
		logoShadow.setSpread(0.3) ;
		logoShadow.setColor(Color.valueOf("#62773f")) ;
		logo.setEffect(logoShadow) ;
		this.setCenter(logo) ;
		
		HBox hBox = new HBox() ;
		try {
			Image image = new Image(new FileInputStream("image/hair.png")) ;
			hBox.getChildren().add(new ImageView(image)) ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
