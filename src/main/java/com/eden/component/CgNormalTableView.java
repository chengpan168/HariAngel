package com.eden.component;

import javafx.scene.control.TableView;

public class CgNormalTableView<S> extends TableView<S> {
	public CgNormalTableView(){
		super() ;
		this.getStyleClass().add("table_view") ;
	}
}
