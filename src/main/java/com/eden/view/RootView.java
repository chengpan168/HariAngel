package com.eden.view;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class RootView extends BorderPane {
	
	public RootView(Node topView , Node leftView , Node bottomView , Node centerView){
		this.setTop(topView) ;
		this.setLeft(leftView) ;
		this.setBottom(bottomView) ;
		this.setCenter(centerView) ;
	}
	
	public RootView(Node topView , Node leftView , Node bottomView){
		this.setTop(topView) ;
		this.setLeft(leftView) ;
		this.setBottom(bottomView) ;
	}
}
