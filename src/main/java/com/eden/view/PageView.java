package com.eden.view;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import com.eden.component.page.Pagination;

public class PageView extends HBox {
	public PageView(){
		super() ;
		this.setMaxSize(300, 30) ;
		this.setAlignment(Pos.BASELINE_CENTER) ;
		this.setStyle("-fx-background-color:#0fffff") ;
		Pagination pagination = new Pagination(30);
		this.getChildren().add(pagination) ;
	}
}
