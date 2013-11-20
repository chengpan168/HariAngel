package com.eden.component.text;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CgText extends Text {
	public CgText(String text){
		super(text) ;
		this.setFont(Font.font("", 14)) ;
	}
	
	public CgText(){
		super();
		this.setFont(Font.font("", 14)) ;
	}
}
