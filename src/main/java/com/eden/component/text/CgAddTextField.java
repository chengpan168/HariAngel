package com.eden.component.text;

import javafx.scene.control.TextField;

public class CgAddTextField extends TextField{
	public CgAddTextField(){
		this(null) ;
	}

	public CgAddTextField(String string) {
		super(string) ;
		getStyleClass().add("addTextField") ;
	}

}
