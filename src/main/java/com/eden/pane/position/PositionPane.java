package com.eden.pane.position;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.eden.action.position.PositionDelAction;
import com.eden.component.CgChoiceBox;
import com.eden.component.button.CgButton;
import com.eden.component.page.Pagination;
import com.eden.component.table.CgTableView;
import com.eden.component.text.CgTextField;
import com.eden.constant.Constant;
import com.eden.dto.PositionDto;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.mvc.action.ActionContext;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.pane.BaseAddPane;
import com.eden.fxmvc.pane.BaseListPane;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.BeanUtil;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.fxmvc.util.ValidateUtil;
import com.eden.fxview.PositionView;
import com.eden.resource.Message;
import com.eden.util.ComponentUtil;
import com.eden.view.MessageBox;

@Component
public class PositionPane  extends BaseListPane {
	private static final Log log = LogFactory.getLog(PositionPane.class) ;
	
	private CgTableView<PositionView> tableView ;
	private Pagination pagination ;
	ChoiceBox<String> findTypeChoiceBox ;
	TextField findValueTextField ;
	
	public PositionPane(){
		init() ;
	}
	
	protected void init() {
		BorderPane borderPane = new BorderPane();
		
		HBox hbox = new HBox(20);

		this.getChildren().add(borderPane);

		findTypeChoiceBox = new CgChoiceBox<String>();
		findTypeChoiceBox.getStyleClass().add("findType") ;
		findTypeChoiceBox.getItems().addAll(Constant.FIND_ALL, "职位编码" ,"职位名称" , "状态");
		findTypeChoiceBox.getSelectionModel().selectFirst();

		hbox.getChildren().add(findTypeChoiceBox);

		findValueTextField = new CgTextField();
		findValueTextField.setText("");
		findValueTextField.setPrefWidth(200) ;

		hbox.getChildren().add(findValueTextField);

		HBox buttonHBox = new HBox(10);
		Button findButton = new CgButton("查找");
		Button addButton = new CgButton("添加");
		Button delButton = new CgButton("删除");

		findValueTextField.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent paramT) {
				log.info("find")  ;
				process() ;
			}
		}) ;
		findButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event paramT) {
				log.info("find")  ;
				process() ;
			}
		}) ;
		addButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event paramT) {
				BaseAddPane positionAddPane = AppContext.getBean("positionAddPane") ;
//				Dialog.showInOne(positionAddPane) ;
				positionAddPane.show() ;
			}
		}) ;
		delButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event paramT) {
				log.info("to delete position ") ;
				List<PositionView> list = tableView.getItems() ;
				final List<Integer> ids = new ArrayList<Integer>() ;
				for(PositionView positionView : list) {
					if(positionView.getIsSelect().getValue()) {
						ids.add(TypeConvertUtil.toInteger(positionView.getPositionId().getValue()) );
					}
				}
				if(ids.isEmpty()){
					MessageBox.showMessage(Message.getMsg("del.noSelect")) ;
				} else {
					MessageBox.confirm(Message.getMsg("del.confirm") , new Callback<Boolean, Object>() {
						@Override
						public Object call(Boolean paramP) {
							if(paramP){
								PositionDelAction positionDelAction = AppContext.getBean("positionDelAction") ;
								ModelAndView mv = positionDelAction.execute(new ActionMessage("ids" , ids )) ;
								MessageBox.showMessage(Message.getMsg("del.success")) ;
								
								Location.go(
										new ActionContext("positionAction" 
												, new ActionMessage(FXConstant.PAGE, getQueryPage()))) ;
							}
							return null;
						}
					}) ;
				}
			}
		}) ;
		
		buttonHBox.getChildren().addAll(findButton, addButton, delButton);
		hbox.getChildren().add(buttonHBox);

		BorderPane.setAlignment(hbox, Pos.BOTTOM_CENTER);
		hbox.setAlignment(Pos.BASELINE_LEFT);

		borderPane.setTop(hbox);
		borderPane.setStyle("-fx-background-color:#00ffff");
		BorderPane.setMargin(hbox, new Insets(20, 0, 5, 10));

//		分页条位置
		pagination = new Pagination() ;
		borderPane.setBottom(pagination) ;
		BorderPane.setAlignment(pagination, Pos.BOTTOM_RIGHT) ;
		BorderPane.setMargin(pagination, new Insets(5, 20, 0, 0)) ;
		
		tableView = new CgTableView<PositionView>() ;
		tableView.setEditable(true) ;
		
		tableView.getColumns().addAll(ComponentUtil.generateTableColumn(PositionView.class, tableView)) ;
		
		
		//根据父面板的首选大小设置表格的首选大小
		tableView.prefWidthProperty().bind(this.prefWidthProperty().subtract(10)) ;
		tableView.prefHeightProperty().bind(this.prefHeightProperty().subtract(100)) ;
		
		borderPane.setCenter(tableView);
		BorderPane.setMargin(tableView, new Insets(15, 5, 0, 5));
		
		tableView.getProperties().addListener(new MapChangeListener<Object, Object>(){
					@Override
					public void onChanged(
							javafx.collections.MapChangeListener.Change<? extends Object , ? extends Object> paramChange) {
						//如果是排序有变，刷新页面
						if( StringUtils.equals(TypeConvertUtil.toStr(paramChange.getKey()) , FXConstant.PAGE) ){
							Location.go("positionAction" , getQueryPage()) ;
						}
					}
		}) ;
		
		//点击修改时
		tableView.getProperties().put(Constant.TO_UPDATE_ACTION, "positionToUpdateAction") ;
		
		pagination.setCallback(new Callback<Page, Object>() {
			@Override
			public Object call(Page paramP) {
				process() ;
				return null;
			}
		}) ;
	}

	
	@SuppressWarnings("unchecked")
	public void update(ModelAndView mv ){
		tableView.setItems((ObservableList<PositionView>) getModelAndView().getAttribute("list")) ;
		pagination.setPage( (Page) mv.getAttribute(FXConstant.PAGE)  ) ;
	}
	
	public Page getQueryPage(){
		Page p = TypeConvertUtil.toTarget(tableView.getProperties().get(FXConstant.PAGE) , Page.class ) ;
		Page page = pagination.getPage() ;
		page.setSortType(p.getSortType()) ;
		page.setSortValue(p.getSortValue()) ;
		if(StringUtils.equals(findTypeChoiceBox.getValue(), Constant.FIND_ALL)){
			page.setFindType(null) ;
		} else {
			page.setFindType(findTypeChoiceBox.getValue()) ;
			page.setFindType(BeanUtil.getPropertyNameEn(PositionDto.class, page.getFindType())) ;
			findValueTextField.setText(StringUtils.trim(findValueTextField.getText())) ;
			page.setFindValue(findValueTextField.getText()) ;
			
			if(StringUtils.equals(page.getFindType(), "valid")){
				page.setFindValue(TypeConvertUtil.toStr(PositionDto.getValidCode(page.getFindValue())) ) ;
			}
		}
		return page ;
	}
	
	
	public void process(){
		boolean isOk = true ;
		if(StringUtils.equals(findTypeChoiceBox.getValue(), Constant.FIND_ALL)){
			findValueTextField.setText(null) ;
		} else {
			if(StringUtils.isBlank(findValueTextField.getText())){
				isOk =false ;
				MessageBox.showMessage(Message.getMsg("query.no.noValue") );
			} else if(StringUtils.equals(findTypeChoiceBox.getValue() , "职位编码")) {
				if(!ValidateUtil.isNumber(findValueTextField.getText()) 
						|| !ValidateUtil.isLengthLimit(findValueTextField.getText() , 1, 5 )){
					isOk =false ;
					MessageBox.showMessage(Message.getMsg("position.positionIdErr") );
				} 
			}
		}
		if(isOk)
			Location.go("positionAction", getQueryPage()) ;
	}
}
