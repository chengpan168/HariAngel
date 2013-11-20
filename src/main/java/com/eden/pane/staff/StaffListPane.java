package com.eden.pane.staff;

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

import com.eden.action.staff.StaffDelAction;
import com.eden.component.CgChoiceBox;
import com.eden.component.button.CgButton;
import com.eden.component.page.Pagination;
import com.eden.component.table.CgTableView;
import com.eden.component.text.CgTextField;
import com.eden.constant.Constant;
import com.eden.dto.StaffDto;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.mvc.action.ActionContext;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.pane.BaseListPane;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.BeanUtil;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.fxmvc.util.ValidateUtil;
import com.eden.fxview.StaffView;
import com.eden.resource.Message;
import com.eden.util.ComponentUtil;
import com.eden.view.MessageBox;

@Component
public class StaffListPane  extends BaseListPane {
	private static final Log log = LogFactory.getLog(StaffListPane.class) ;
	
	private CgTableView<StaffView> tableView ;
	private Pagination pagination ;
	ChoiceBox<String> findTypeChoiceBox ;
	TextField findValueTextField ;
	
	public StaffListPane(){
		init() ;
	}
	
	protected void init() {
		BorderPane borderPane = new BorderPane();
		
		HBox hbox = new HBox(20);

		this.getChildren().add(borderPane);

		findTypeChoiceBox = new CgChoiceBox<String>();
		findTypeChoiceBox.getStyleClass().add("findType") ;
		findTypeChoiceBox.getItems().addAll(Constant.FIND_ALL, "职员编码" ,"姓名" , "状态" , "手机号码" ,"性别");
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
				Location.go("staffToAddAction") ;
				/*BaseAddPane staffAddPane = AppContext.getBean("staffAddPane") ;
				staffAddPane.show() ;*/
			}
		}) ;
		delButton.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event paramT) {
				log.info("to delete staff ") ;
				List<StaffView> list = tableView.getItems() ;
				final List<Integer> ids = new ArrayList<Integer>() ;
				for(StaffView staffView : list) {
					if(staffView.getIsSelect().getValue()) {
						ids.add(TypeConvertUtil.toInteger(staffView.getStaffId().getValue()) );
					}
				}
				if(ids.isEmpty()){
					MessageBox.showMessage(Message.getMsg("del.noSelect")) ;
				} else {
					MessageBox.confirm(Message.getMsg("del.confirm") , new Callback<Boolean, Object>() {
						@Override
						public Object call(Boolean paramP) {
							if(paramP){
								StaffDelAction staffDelAction = AppContext.getBean("staffDelAction") ;
								ModelAndView mv = staffDelAction.execute(new ActionMessage("ids" , ids )) ;
								MessageBox.showMessage(Message.getMsg("del.success")) ;
								
								Location.go(
										new ActionContext("staffListAction" 
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
		
		tableView = new CgTableView<StaffView>() ;
		tableView.setEditable(true) ;
		
		tableView.getColumns().addAll(ComponentUtil.generateTableColumn(StaffView.class, tableView)) ;
		
		
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
							Location.go("staffListAction" , getQueryPage()) ;
						}
					}
		}) ;
		
		//点击修改时
		tableView.getProperties().put(Constant.TO_UPDATE_ACTION, "staffToUpdateAction") ;
		
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
		tableView.setItems((ObservableList<StaffView>) getModelAndView().getAttribute("list")) ;
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
			page.setFindType(BeanUtil.getPropertyNameEn(StaffDto.class, page.getFindType())) ;
			findValueTextField.setText(StringUtils.trim(findValueTextField.getText())) ;
			page.setFindValue(findValueTextField.getText()) ;
			
			if(StringUtils.equals(page.getFindType(), "sex")){
				page.setFindValue(TypeConvertUtil.toStr(StaffDto.getSex(page.getFindValue())) );
			} else if(StringUtils.equals(page.getFindType(), "status")){
				page.setFindValue(TypeConvertUtil.toStr(StaffDto.getStatus(page.getFindValue())) );
			}
		}
		return page ;
	}
	
	
	public void process(){
		boolean isOk = true ;
		if(StringUtils.equals(findTypeChoiceBox.getValue(), Constant.FIND_ALL)){
			findValueTextField.setText(null) ;
		} else {
			ComponentUtil.trimText(findValueTextField) ;
			if(StringUtils.isBlank(findValueTextField.getText())){
				isOk =false ;
				MessageBox.showMessage(Message.getMsg("query.no.noValue") );
			} else if(StringUtils.equals(findTypeChoiceBox.getValue() , "职员编码")) {
				if(!ValidateUtil.isLengthLimit(ComponentUtil.trimText(findValueTextField), 1, 8) 
						|| !ValidateUtil.isNumber(ComponentUtil.trimText(findValueTextField))){
					isOk =false ;
					MessageBox.showMessage(Message.getMsg("staff.staffCodeErr") );
				} 
			} else if(StringUtils.equals(findTypeChoiceBox.getValue() , "手机号码")){
				if(!ValidateUtil.isLengthLimit(ComponentUtil.trimText(findValueTextField), 11, 11) 
						|| !ValidateUtil.isNumber(ComponentUtil.trimText(findValueTextField))){
					MessageBox.showMessage(Message.getMsg("staff.phoneNumErr") );
				} 
			}
		}
		if(isOk)
			Location.go("staffListAction", getQueryPage()) ;
	}
}
