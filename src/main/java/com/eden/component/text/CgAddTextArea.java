package com.eden.component.text;

import javafx.scene.control.TextArea;

public class CgAddTextArea extends TextArea {
	public CgAddTextArea(){
		super() ;
		setWrapText(true) ;
		this.getStyleClass().add("addTextArea") ;
	}
}
