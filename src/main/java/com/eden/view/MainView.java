package com.eden.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.eden.constant.Constant;
import com.eden.fxmvc.context.AppContext;


public class MainView extends Application {
	private static final Log log = LogFactory.getLog(MainView.class) ;
	private String cssPath ;
	private String icoPath ;
	private String title ;
	
	private Scene scene  ;
	public Scene getScene() {
		return scene;
	}

	BorderPane centerPane  ;
	
	@Override
	public void start(Stage stage) throws Exception {
		log.info("application start...") ;
		initContext() ;
		init(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * 初始化应用程序数据
	 */
	public void initContext() {
		AppContext.init();
	}
	
	/**
	 * 初始化程序界面
	 * @param stage
	 */
	public void init(Stage stage) {
		AppContext.setAttribute(Constant.WINDOW, stage) ;
		stage.setTitle("HairAngel");
		// 设置任务栏图标
		stage.setIconified(false);
		File f = new File("image/ico.png");
		try {
			FileInputStream iconInputStream = new FileInputStream(f);
			Image image = new Image(iconInputStream);
			iconInputStream.close();
			stage.getIcons().add(image);
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene = AppContext.getBean("scene") ;
		stage.setScene(scene);
		stage.setMinHeight(400) ;
		stage.setMinWidth(780) ;
		stage.show();
	}

	public String getCssPath() {
		return cssPath;
	}

	public void setCssPath(String cssPath) {
		this.cssPath = cssPath;
	}

	public String getIcoPath() {
		return icoPath;
	}

	public void setIcoPath(String icoPath) {
		this.icoPath = icoPath;
	}
}
