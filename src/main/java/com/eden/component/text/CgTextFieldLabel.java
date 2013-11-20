package com.eden.component.text;

import javafx.scene.control.TextField;

/**
 * 假如你想复制显示的信息，这个比较好用。。。
 * @author eden
 *
 */
public class CgTextFieldLabel extends TextField {
	public CgTextFieldLabel(){
		this(""); 
	}
	
	public CgTextFieldLabel(String text){
		super(text) ;
		this.getStyleClass().add("input_label") ;
		this.setEditable(false) ;
	}
	
	public CgTextFieldLabel(String text , double prefWidth , double prefHeight){
		super(text) ;
		this.setPrefSize(prefWidth, prefHeight) ;
		this.getStyleClass().add("input_label") ;
		this.setEditable(false) ;
	}
}
