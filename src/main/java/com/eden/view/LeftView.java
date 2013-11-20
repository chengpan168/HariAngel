package com.eden.view;

import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.eden.component.ListViewTitledPane;
import com.eden.fxmvc.module.Module;
import com.eden.fxmvc.module.ModuleManager;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.util.FormatUtils;

public class LeftView extends VBox {
	//读出配置文件中的插件，显示左侧功能节点
	@Autowired
	private ModuleManager moduleManager ;
	public LeftView(ModuleManager moduleManager){
		this.moduleManager = moduleManager ;
		init() ;
	}
	public LeftView(){
		init() ;
	}
	public void init() {
		this.getStyleClass().add("left") ;
		
		Text currUserLabel = new Text("当前用户：") ;
		Text currUser = new Text("eden") ;
		currUser.setDisable(true) ;
		
		Text currDateLabel = new Text("当前时间：") ;
		Text currDate = new Text(FormatUtils.formatDate(System.currentTimeMillis())) ;
		currDate.getStyleClass().add("lefttoplabel") ;
		
		GridPane leftTop = new GridPane() ;
		leftTop.add(currUserLabel , 0 , 0) ;
		leftTop.add(currUser , 1 , 0) ;
		
		leftTop.add(currDateLabel , 0 , 1) ;
		leftTop.add(currDate , 1 , 1) ;
		leftTop.setStyle("-fx-border-width:1px") ;
		this.getChildren().add(leftTop) ;
		
		
		Group root = new Group();
		
		Accordion accordion = new Accordion();
		
		List<Module> modules = moduleManager.getModules() ;
		
		for(Module module : modules) {
			showModule(accordion , module) ;
		}
		
		root.getChildren().add(accordion);
		
		this.setPadding(new Insets(10, 10, 10, 10)) ;
		this.getChildren().add(root) ;
	}
	
	public TitledPane showModule(Accordion accordion , final Module module) {
		//如果模块为功能结点,
		if(module.isLeaf()) {
			//如果没有父结点,直接显示出来
			if(module.getParent()==null) {
				TitledPane titledPane = new TitledPane() ;
				Label title = new Label(module.getTitle()) ;
				
				title.setOnMouseClicked(new EventHandler<Event>() {
					Module moduleEvent = module ;
					@Override
					public void handle(Event e) {
						Location.go(moduleEvent.getAction()) ;
					}
				}) ;
				
				title.setStyle("-fx-cursor:hand") ;
				title.getStyleClass().add("h1") ;
				title.setPrefWidth(160) ;
				titledPane.setGraphic(title) ;
				titledPane.setCollapsible(false) ;
				accordion.getPanes().add(titledPane) ;
			} else {
				Module parent = module.getParent() ;
				for(TitledPane titledPane : accordion.getPanes()) {
					if ( StringUtils.equals( ((Label)titledPane.getGraphic()).getText() , parent.getTitle() ) ){
						ListView<Label> listView = (ListView<Label>) titledPane.getContent() ;
						if(listView == null) {
							listView = new ListViewTitledPane<Label>() ;
						}
						
						Label title = new Label(module.getTitle()) ;
						title.setOnMouseClicked(new EventHandler<Event>() {
							Module moduleEvent = module ;
							@Override
							public void handle(Event e) {
								Location.go(moduleEvent.getAction()) ;
							}
						}) ;
						title.prefWidthProperty().bind(listView.widthProperty().subtract(25)) ;
						listView.getItems().add( title ) ;
						//设置左侧树的宽度和高度
						double height = listView.getItems().size() * 26.5 ;
						listView.setPrefSize(180, height) ;
						titledPane.setContent(listView) ;
					}
				}
				
				
			}
		} else {
			TitledPane titledPane = new TitledPane() ;
			Label title = new Label(module.getTitle()) ;
			title.getStyleClass().add("h1") ;
			titledPane.setGraphic(title) ;
			accordion.getPanes().add(titledPane) ;
		}
		return null ;
	}
	public ModuleManager getModuleManager() {
		return moduleManager;
	}
	public void setModuleManager(ModuleManager moduleManager) {
		this.moduleManager = moduleManager;
	}
}
