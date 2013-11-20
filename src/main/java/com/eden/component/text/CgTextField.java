package com.eden.component.text;

import com.eden.constant.StyleConstant;

import javafx.scene.control.TextField;

public class CgTextField extends TextField {
	public CgTextField(){
		super() ;
		this.setPrefSize(StyleConstant.FIELD_WIDTH, StyleConstant.FIELD_HEIGHT ) ;
		getStyleClass().add("searchInput") ;
	}
}
