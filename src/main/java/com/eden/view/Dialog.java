package com.eden.view;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.pane.BasePane;

@Deprecated
public class Dialog {
	/**
	 * 展示添加面板
	 * @param pane
	 */
	public static void showInOne(BasePane pane){
		
		Stage stage = instanceOne() ;
		((BorderPane)stage.getScene().getRoot()).setCenter(pane) ;
		stage.show() ;
		
	}
	public static void closeOne(){
		Stage stage = instanceOne() ;
		stage.close() ;
	}
	
	private static Stage stageSingle = null ;
	public static Stage instanceOne(){
		if(stageSingle != null) return stageSingle ;
		
		stageSingle = new Stage(StageStyle.UTILITY) ;
		Scene stageSingleScene = new Scene(new BorderPane() , 500 , 600 ) ;
		stageSingleScene.getStylesheets().add("css/hairangel.css");
		stageSingle.initModality(Modality.APPLICATION_MODAL) ;
		stageSingle.setScene(stageSingleScene) ;
		stageSingle.setMinHeight(500) ;
		stageSingle.setMinWidth(400) ;
		
		stageSingle.initOwner((Window) AppContext.getAttribute(FXConstant.WINDOW)) ;
		
		return stageSingle ;
	}
}
