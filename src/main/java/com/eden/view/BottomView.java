package com.eden.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class BottomView extends BorderPane{
	public BottomView() {
		super();
		init() ;
	}
	
	public void init() {
		HBox bottomRight = new HBox() ;
		Text status = new Text("status") ;
		bottomRight.getChildren().add(status) ;
		this.getStyleClass().add("bottom") ;
		this.setRight(bottomRight) ;
		
		Text bottomCenter = new Text("copyright © eden software personal") ;
		this.setCenter(bottomCenter) ;
	}
}
