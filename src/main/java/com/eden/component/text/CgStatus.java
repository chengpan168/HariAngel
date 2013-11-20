package com.eden.component.text;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CgStatus extends HBox {
	private Label status ; 
	
	public CgStatus() {
		super() ;
		setAlignment(Pos.CENTER) ;
		
		status = new Label() ; 
		status.setAlignment(Pos.CENTER) ;
		status.setStyle("-fx-text-fill:red") ;
		getChildren().add(status) ;
	}
	
	public void setStatus(String text) {
		status.setText(text) ;
	}
}
