package com.eden.component;

import com.eden.constant.StyleConstant;

import javafx.scene.control.ChoiceBox;

public class CgChoiceBox<T> extends ChoiceBox<T> {
	public CgChoiceBox(){
		super() ;
		this.setPrefHeight(StyleConstant.BUTTON_HEIGTH ) ;
		this.getStyleClass().add("choiceBox") ;
	}

	
}
