package com.eden.component;

import javafx.scene.control.ChoiceBox;

public class CgAddChoiceBox extends ChoiceBox<String> {
	public CgAddChoiceBox(){
		super() ;
		getStyleClass().add("addChoiceBox") ;
		this.setPrefHeight(25) ;
	}
}
