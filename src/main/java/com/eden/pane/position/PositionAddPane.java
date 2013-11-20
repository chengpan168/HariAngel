package com.eden.pane.position;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.eden.component.CgAddChoiceBox;
import com.eden.component.CgAddItemHbox;
import com.eden.component.button.CgAddButton;
import com.eden.component.text.CgAddLabel;
import com.eden.component.text.CgAddTextArea;
import com.eden.component.text.CgAddTextField;
import com.eden.component.text.CgStarLabel;
import com.eden.component.text.CgStatus;
import com.eden.dto.PositionDto;
import com.eden.fxmvc.context.AppContext;
import com.eden.fxmvc.mvc.action.ActionContext;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.pane.BaseAddPane;
import com.eden.fxmvc.ui.Location;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.validation.Errors;
import com.eden.resource.Message;
import com.eden.util.ComponentUtil;

@Component
@Lazy
public class PositionAddPane extends BaseAddPane {
	private CgAddTextField positionName  ;
	private CgAddTextField tipField ;
	private CgAddChoiceBox validChoiceBox  ;
	private CgAddTextArea descTextArea  ;
	private CgStatus status  ;
	
	public PositionAddPane(){
		super("职位-添加") ;
		init() ;
	}
	
	public void init(){
		VBox vBox = new VBox(10) ;
		vBox.setMinWidth(300) ;
		
		CgAddItemHbox positionNameBox = new CgAddItemHbox() ;
		positionName = new CgAddTextField() ;
		positionNameBox.add(new CgAddLabel("职位名称") , 0, 0) ;
		positionNameBox.add(positionName , 1 , 0) ;
		positionNameBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox tipBox = new CgAddItemHbox() ;
		tipField = new CgAddTextField("0") ;
		tipBox.add(new CgAddLabel("小费"), 0, 0) ;
		tipBox.add(tipField , 1 , 0) ;
		tipBox.add(new CgStarLabel()  , 2 , 0) ;
		
		CgAddItemHbox validBox = new CgAddItemHbox() ;
		
		validBox.add(new CgAddLabel("状态") , 0 , 0 ) ;
		validChoiceBox = new CgAddChoiceBox() ;
		validChoiceBox.setItems(FXCollections.observableArrayList("有效" , "无效")) ;
		validChoiceBox.getSelectionModel().select(0) ;
		validBox.add(validChoiceBox , 1 , 0 ) ;
		 
		 
		CgAddItemHbox descBox = new CgAddItemHbox() ;
		descTextArea = new CgAddTextArea() ;
		CgAddLabel descLabel = new CgAddLabel("职位描述") ;
		descBox.add(descLabel , 0, 0) ;
		descBox.add(descTextArea , 1 , 0) ;
		CgAddItemHbox.setValignment(descLabel, VPos.TOP) ;
		
		status = new CgStatus() ;
		
		
		HBox buttonBox = new HBox(20) ;
		CgAddButton addButton = new CgAddButton("提交") ;
		CgAddButton cancleButton = new CgAddButton("返回" ) ;
		buttonBox.getChildren().addAll(cancleButton , addButton  ) ;
		buttonBox.setAlignment(Pos.BOTTOM_CENTER) ;
		
		vBox.getChildren().addAll(positionNameBox , tipBox , validBox , descBox , status , buttonBox) ;
		
		setContent(vBox) ;
		
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ActionMessage actionMessage = getActionMessage() ;
				
				PositionDto positionDto = actionMessage.getAttribute("positionDto") ;
				
				Errors err = positionDto.validate() ;
				if(StringUtils.isNotBlank(err.getStatus())) {
					status.setStatus(Message.getMsg(err.getStatus())) ;
					return ;
				}
				Location.go(new ActionContext("positionAddAction" , actionMessage ) ) ;
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
		PositionDto positionDto = new PositionDto() ;
		positionDto.setPositionName(ComponentUtil.trimText(positionName) );
		positionDto.setValidName(validChoiceBox.getValue()) ;
		positionDto.setTip(ComponentUtil.trimText(tipField)) ;
		positionDto.setDescription(ComponentUtil.trimText(descTextArea )) ;
		
		actionMessage.addAttribute("positionDto", positionDto ) ;
		
		return actionMessage ;
	}
	
	public void update(ModelAndView mv) {
		if(StringUtils.equals("add.success", mv.getStatus())){
			clear();
			PositionPane positionPane = AppContext.getBean("positionPane") ;
			positionPane.process() ;
		}
		status.setStatus(Message.getMsg(mv.getStatus())) ;
	}
	
	public void clear(){
		positionName.setText(null);
		validChoiceBox.setValue("有效") ;
		tipField.setText("0") ;
		descTextArea.setText(null) ;
		status.setStatus(null) ;
	}
}
