package com.eden.component;

import com.eden.component.table.CgTableColumn;


public class NormalTableColumn<S, T> extends CgTableColumn<S, T> {
	public NormalTableColumn(){
		super() ;
		this.setMinWidth(50) ;
		this.setPrefWidth(170) ;
		this.getStyleClass().add("normal_table_column") ;
	}
	
	public NormalTableColumn(double minWidth , double prefWidth){
		super() ;
		this.setMinWidth(minWidth) ;
		this.setPrefWidth(prefWidth) ;
	}
}
