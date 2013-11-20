package com.eden.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import com.eden.component.button.CgButton;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.context.AppContext;

public class MessageBox {
	public static void showMessage(String msg) {
		final Stage stage = newInstance() ;
		stage.setTitle("提示") ;
		stage.centerOnScreen() ;
        
		VBox content = new VBox(35);
        content.setAlignment(Pos.TOP_CENTER) ;
        
        Text text = new Text(20, 270, msg);
        text.setTextAlignment(TextAlignment.CENTER) ;
        text.setWrappingWidth(270) ;
        text.setFill(Color.RED);
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 14));
        
        CgButton ok = new CgButton("确定") ;
        ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				stage.close() ;
			}
		}) ;
        VBox.setMargin(text, new Insets(20 , 0 , 0 , 0) );
        content.getChildren().addAll(text , ok);
        
        stage.getScene().setRoot(content) ;
        stage.show();
	}
	
	public static <T> void confirm(String msg , final Callback<Boolean , T> callback ) {
		final Stage stage = instanceOne() ;
		stage.setTitle("提示") ;
		stage.centerOnScreen() ;
        
		VBox content = new VBox(35);
        content.setAlignment(Pos.TOP_CENTER) ;
        
        Text text = new Text(20, 270, msg);
        text.setTextAlignment(TextAlignment.CENTER) ;
        text.setWrappingWidth(270) ;
        text.setFill(Color.RED);
        text.setEffect(new Lighting());
        text.setFont(Font.font(Font.getDefault().getFamily(), 14));
        
        CgButton ok = new CgButton("确定") ;
        CgButton cancel = new CgButton("取消") ;
        ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				if(callback != null )
					callback.call(true) ;
				stage.close() ;
			}
		}) ;
        cancel.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent paramT) {
        		if(callback != null )
        			callback.call(false) ;
        		stage.close() ;
        	}
        }) ;
        
        HBox buttons = new HBox(30) ;
        buttons.setAlignment(Pos.CENTER) ;
        buttons.getChildren().addAll( cancel , ok ) ;
        
        VBox.setMargin(text, new Insets(20 , 0 , 0 , 0) );
        content.getChildren().addAll(text , buttons);
        
        stage.getScene().setRoot(content) ;
        stage.show();
	}
	
	private static Stage stageSingle = null ;
	public static Stage instanceOne(){
		if(stageSingle != null) return stageSingle ;
		
		stageSingle = new Stage(StageStyle.UTILITY) ;
		Scene stageSingleScene = new Scene(new BorderPane() , 350, 150 ) ;
		stageSingleScene.getStylesheets().add("css/hairangel.css");
		stageSingle.initModality(Modality.APPLICATION_MODAL) ;
		stageSingle.setScene(stageSingleScene) ;
		stageSingle.setResizable(false)  ;
		stageSingle.initOwner((Window) AppContext.getAttribute(FXConstant.WINDOW)) ;
		
		return stageSingle ;
	}
	
	public static Stage newInstance(){
		Stage stage = new Stage(StageStyle.UTILITY) ;
		Scene stageScene = new Scene(new BorderPane() , 350, 150 ) ;
		stageScene.getStylesheets().add("css/hairangel.css");
		stage.initModality(Modality.APPLICATION_MODAL) ;
		stage.setScene(stageScene) ;
		stage.setResizable(false)  ;
		stage.initOwner((Window) AppContext.getAttribute(FXConstant.WINDOW)) ;
		
		return stage ;
	}
}
