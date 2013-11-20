package com.eden.component.button;

import com.eden.constant.StyleConstant;

import javafx.scene.control.Button;

public class CgButton extends Button {
	public CgButton(String name){
		super(name) ;
		this.setPrefSize(StyleConstant.BUTTON_WIDTH, StyleConstant.BUTTON_HEIGTH ) ;
		this.getStyleClass().add("button") ;
	}
}
