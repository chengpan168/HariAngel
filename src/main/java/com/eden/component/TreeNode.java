package com.eden.component;

import javafx.scene.control.Button;

public class TreeNode extends Button{

	public TreeNode(String name) {
		super(name) ;
		init() ;
	}
	
	public void init() {
		this.getStyleClass().add("treeNode") ;
	}
	

	public String toString(){
		return getText() ;
	}
}
