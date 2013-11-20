package com.eden.view;

import com.eden.fxmvc.pane.BasePane;
import com.eden.fxmvc.ui.DisplayView;

public class CenterView extends DisplayView {
	public CenterView(){
	}
	
	public void setContent(BasePane pane) {
		this.setCenter(pane) ;
		pane.prefWidthProperty().bind(this.widthProperty()); 
		pane.prefHeightProperty().bind(this.heightProperty()); 
		
	}
}
