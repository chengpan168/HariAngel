package com.eden.component;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class CgAddItemHbox extends GridPane {
	public CgAddItemHbox(){
		super();
		ColumnConstraints label = new ColumnConstraints(100) ;
		this.getColumnConstraints().add(label); 
		this.getColumnConstraints().add(new ColumnConstraints(250));
	}
}
