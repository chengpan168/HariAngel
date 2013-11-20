package com.eden.component;

import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

public class CgCheckBox extends CheckBox {
	public CgCheckBox(){
		super() ;
	}
	
	public CgCheckBox(String text) {
		super(text) ;
		setFont(Font.getDefault()) ;
	}
}
