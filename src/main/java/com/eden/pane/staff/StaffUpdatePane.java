package com.eden.pane.staff;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.eden.component.CgAddChoiceBox;
import com.eden.component.CgAddItemHbox;
import com.eden.component.CgCheckBox;
import com.eden.component.button.CgAddButton;
import com.eden.component.text.CgAddLabel;
import com.eden.component.text.CgAddTextArea;
import com.eden.component.text.CgAddTextField;
import com.eden.component.text.CgStarLabel;
import com.eden.component.text.CgStatus;
import com.eden.component.text.CgTooltip;
import com.eden.dto.StaffDto;
import com.eden.entity.Position;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.mvc.action.ActionContext;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.pane.BaseUpdatePane;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.fxmvc.validation.Errors;
import com.eden.javafx.calendar.FXCalendar;
import com.eden.resource.Message;
import com.eden.util.ComponentUtil;

@Component
@Lazy
public class StaffUpdatePane extends BaseUpdatePane {
	private CgAddTextField staffId ;
	private CgAddTextField staffCode  ;
	private CgAddTextField staffName  ;
	private ObservableList<CheckBox> positionsCheckBox ;
	private CgAddChoiceBox statusChoiceBox  ;
	
	private CgAddTextField phoneNum ;
	private CgAddChoiceBox sex ;
	private CgAddTextField address ;
	private CgAddTextField idCardNum ;
	private CgAddTextField accountNum  ;
	
	private FXCalendar entryTime  ;
	
	private FXCalendar birthTime  ;
	
	private CgAddTextArea remarks  ;
	private CgStatus status  ;
	
	public StaffUpdatePane(){
		super("职位-添加") ;
		init() ;
	}
	
	public void init(){
		staffId = new CgAddTextField() ;
		
		VBox vBox = new VBox(10) ;
		vBox.setMinWidth(300) ;
		
		CgAddItemHbox staffCodeBox = new CgAddItemHbox() ;
		staffCode = new CgAddTextField() ;
		staffCodeBox.add(new CgAddLabel("职员编号") , 0, 0) ;
		staffCodeBox.add(staffCode , 1 , 0) ;
		staffCodeBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox staffNameBox = new CgAddItemHbox() ;
		staffName = new CgAddTextField() ;
		staffNameBox.add(new CgAddLabel("姓名") , 0, 0) ;
		staffNameBox.add(staffName , 1 , 0) ;
		staffNameBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox phoneNumBox = new CgAddItemHbox() ;
		phoneNum = new CgAddTextField() ;
		phoneNumBox.add(new CgAddLabel("手机号码"), 0, 0) ;
		phoneNumBox.add(phoneNum , 1 , 0) ;
		phoneNumBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox idCardNumBox = new CgAddItemHbox() ;
		idCardNum = new CgAddTextField() ;
		idCardNumBox.add(new CgAddLabel("身份证号码"), 0, 0) ;
		idCardNumBox.add(idCardNum , 1 , 0) ;
		idCardNumBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox sexBox = new CgAddItemHbox() ;
		sexBox.add(new CgAddLabel("性别") , 0 , 0 ) ;
		sex = new CgAddChoiceBox() ;
		sex.setItems(FXCollections.observableArrayList("男" , "女")) ;
		sex.getSelectionModel().select(0) ;
		sexBox.add(sex , 1 , 0 ) ;
		
		CgAddItemHbox statusBox = new CgAddItemHbox() ;
		statusBox.add(new CgAddLabel("状态") , 0 , 0 ) ;
		statusChoiceBox = new CgAddChoiceBox() ;
		statusChoiceBox.setItems(FXCollections.observableArrayList("在职" , "离职")) ;
		statusChoiceBox.getSelectionModel().select(0) ;
		statusBox.add(statusChoiceBox , 1 , 0 ) ;
		
//		选择职位
		positionsCheckBox = FXCollections.observableArrayList() ;
		CgAddItemHbox positionBox = new CgAddItemHbox() ;
		Label positionLabel = new CgAddLabel("职位") ;
		CgAddItemHbox.setValignment(positionLabel, VPos.TOP) ;
		positionBox.add(positionLabel , 0 , 0 ) ;
		final GridPane  positionPane = new GridPane () ;
		positionPane.getColumnConstraints().add(new ColumnConstraints(100));
		positionPane.getColumnConstraints().add(new ColumnConstraints(100));
		positionPane.setAlignment(Pos.BASELINE_CENTER) ;
		positionPane.setStyle("-fx-background-color:#E3E3E3;-fx-border-radius: 3 3 3 3;-fx-border-color: hotpink;/*#7792B7; */-fx-border-width:1 1 1 1 ; ") ;
		positionPane.setVgap(8);
		positionPane.setHgap(5);
		positionPane.setPadding(new Insets(5, 5, 5, 5)) ;
		
		positionsCheckBox.addListener(new ListChangeListener<CheckBox>(){
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends CheckBox> paramChange) {
				int i = 0 ;
				positionPane.getChildren().clear() ;
				for(CheckBox checkBox : paramChange.getList() ){
					positionPane.add(checkBox , i%2 , i/2) ;
					i++ ;
				}
			}
			
		}) ;
		positionBox.add(positionPane , 1 , 0 ) ;
		Label positionStar = new CgStarLabel() ;
		positionBox.add( positionStar , 2 , 0) ;
		CgAddItemHbox.setValignment(positionStar, VPos.TOP) ;
		
		CgAddItemHbox addressBox = new CgAddItemHbox() ;
		address = new CgAddTextField() ;
		addressBox.add(new CgAddLabel("住址"), 0, 0) ;
		addressBox.add(address , 1 , 0) ;
		
		CgAddItemHbox accountNumBox = new CgAddItemHbox() ;
		accountNum = new CgAddTextField() ;
		accountNumBox.add(new CgAddLabel("银行账号"), 0, 0) ;
		accountNumBox.add(accountNum , 1 , 0) ;
		
		CgAddItemHbox entryTimeBox = new CgAddItemHbox() ;
		entryTime = new FXCalendar();
		entryTimeBox.add(new CgAddLabel("入职日期"), 0, 0) ;
		entryTimeBox.add(entryTime , 1 , 0) ;
		entryTimeBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox birthTimeBox = new CgAddItemHbox() ;
		birthTime = new FXCalendar();
		birthTimeBox.add(new CgAddLabel("出生年月"), 0, 0) ;
		birthTimeBox.add(birthTime , 1 , 0) ;
		birthTimeBox.add(new CgStarLabel()  , 2 , 0) ;
		 
		CgAddItemHbox remarksBox = new CgAddItemHbox() ;
		remarks = new CgAddTextArea() ;
		remarks.setPrefHeight(60) ;
		CgAddLabel descLabel = new CgAddLabel("备注") ;
		remarksBox.add(descLabel , 0, 0) ;
		remarksBox.add(remarks , 1 , 0) ;
		CgAddItemHbox.setValignment(descLabel, VPos.TOP) ;
		
		status = new CgStatus() ;
		
		
		HBox buttonBox = new HBox(20) ;
		CgAddButton addButton = new CgAddButton("提交") ;
		CgAddButton cancleButton = new CgAddButton("返回" ) ;
		buttonBox.getChildren().addAll(cancleButton , addButton  ) ;
		buttonBox.setAlignment(Pos.BOTTOM_CENTER) ;
		
		vBox.getChildren().addAll(staffCodeBox , staffNameBox , phoneNumBox , addressBox , statusBox 
				, sexBox , positionBox , idCardNumBox , accountNumBox
				, entryTimeBox , birthTimeBox ,remarksBox, status , buttonBox) ;
		
		setContent(vBox) ;
		
		//提交，验证，
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ActionMessage actionMessage = getActionMessage() ;
				
				StaffDto staffDto = actionMessage.getAttribute("staffDto") ;
				
				Errors err = staffDto.validate() ;
				if(StringUtils.isNotBlank(err.getStatus())) {
					status.setStatus(Message.getMsg(err.getStatus())) ;
					return ;
				}
				Location.go(new ActionContext("staffUpdateAction" , actionMessage ) ) ;
			}
		}) ;
		cancleButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				clear() ;
				close() ;
			}
		}) ;
	}
	
	public ActionMessage getActionMessage(){
		ActionMessage actionMessage = new ActionMessage() ;
		StaffDto staffDto = new StaffDto() ;
		staffDto.setStaffId(staffId.getText()) ;
		staffDto.setStaffCode(ComponentUtil.trimText(staffCode) );
		staffDto.setStaffName(ComponentUtil.trimText(staffName)) ;
		staffDto.setStatusName(statusChoiceBox.getValue()) ;
		staffDto.setSexName(sex.getValue()) ;
		staffDto.setPhoneNum(ComponentUtil.trimText(phoneNum)) ;
		staffDto.setAddress(ComponentUtil.trimText(address)) ;
		staffDto.setIdCardNum(ComponentUtil.trimText(idCardNum)) ;
		staffDto.setAccountNum(ComponentUtil.trimText(accountNum)) ;
		staffDto.setEntryTime(TypeConvertUtil.toTimestamp(entryTime.getValue()) ) ;
		staffDto.setBirthTime(TypeConvertUtil.toTimestamp(birthTime.getValue()) ) ;
		
		staffDto.setRemarks(ComponentUtil.trimText(remarks )) ;
		
		List<String> pos = new ArrayList<String>() ;
		for(CheckBox checkBox : positionsCheckBox) {
			if(checkBox.isSelected()){
				pos.add(TypeConvertUtil.toStr(checkBox.getUserData()) );
			}
		}
		staffDto.setPositions(pos) ;
		
		actionMessage.addAttribute("staffDto", staffDto ) ;
		
		return actionMessage ;
	}
	
	public void update(ModelAndView mv) {
		
		if(StringUtils.equals("update.success", mv.getStatus())){
			StaffListPane staffListPane = AppContext.getBean("staffListPane") ;
			staffListPane.process() ;
		} else if(mv.getStatus() == null ){
			StaffDto staffDto = mv.getAttribute("staffDto") ;
			List<Position> positions = mv.getAttribute("positions") ;
			List<CheckBox> boxs = new ArrayList<CheckBox>() ;
			for(Position position : positions) {
				CheckBox box = new CgCheckBox(position.getPositionName()) ;
				if(staffDto.getPositions() != null && staffDto.getPositions().contains(position.getPositionName())){
					box.setSelected(true) ;
				}
				box.setUserData(position.getPositionId()) ;
				if(position.getPositionName()!= null && position.getPositionName().length() > 4) {
					box.setTooltip(new CgTooltip(position.getPositionName())) ;
				}
//				box.setPrefWidth(80) ;
				boxs.add(box) ;
			}
			positionsCheckBox.setAll(boxs);
			
			staffId.setText(staffDto.getStaffId()) ;
			staffCode.setText(staffDto.getStaffCode()) ;
			staffName.setText(staffDto.getStaffName()) ;
			idCardNum.setText(staffDto.getIdCardNum());
			phoneNum.setText(staffDto.getPhoneNum()) ;
			accountNum.setText(staffDto.getAccountNum()) ;
			address.setText(staffDto.getAddress()) ;
			sex.setValue(staffDto.getSexName()) ;
			statusChoiceBox.setValue(staffDto.getStatusName()) ;
			entryTime.setValue(staffDto.getEntryTime()) ;
			birthTime.setValue(staffDto.getBirthTime()) ;
			remarks.setText(staffDto.getRemarks()) ;
		}
		status.setStatus(Message.getMsg(mv.getStatus())) ;
	}
	
	public void clear(){
		staffCode.setText(null);
		staffName.setText(null);
		statusChoiceBox.setValue("在职") ;
		sex.setValue("男") ;
		phoneNum.setText(null) ;
		address.setText(null) ;
		idCardNum.setText(null) ;
		accountNum.setText(null) ;
		entryTime.setValue(null) ;
		birthTime.setValue(null) ;
		remarks.setText(null) ;
		status.setStatus(null) ;
		
		if(positionsCheckBox != null) {
			for(CheckBox checkBox : positionsCheckBox){
				checkBox.setSelected(false) ;
			}
		}
	}
}
