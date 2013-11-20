package com.eden.fxview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.eden.annotation.TableViewAnnotation;
import com.eden.annotation.TableViewAnnotation.TitleType;
import com.eden.component.table.CgCheckBoxTableCell;
import com.eden.component.table.CgOperatorTableCell;
import com.eden.component.table.CgTextTableCell;
import com.eden.dto.PositionDto;
import com.eden.fxmvc.util.TypeConvertUtil;

public class PositionView {
	
	@TableViewAnnotation(
			prefWidthPercent=5
			,orderEnable = false 
			,cellType=CgCheckBoxTableCell.class 
			,titleType=TitleType.checkBox
			)
	private BooleanProperty isSelect = new SimpleBooleanProperty(false) ;
	
	@TableViewAnnotation(
			prefWidthPercent=5
			,orderEnable = false 
			,cellType=CgOperatorTableCell.class 
			,title="操作" 
			)
	private StringProperty operator   ;
	
	@TableViewAnnotation(
			prefWidthPercent=10
			,title="职位编号" 
			)
	private StringProperty positionId ;
	@TableViewAnnotation(prefWidthPercent=20,title="职位名称" )
	private StringProperty positionName ;
	@TableViewAnnotation(prefWidthPercent=10,title="小费" )
	private StringProperty tip ;
	
	@TableViewAnnotation(prefWidthPercent=10,title="状态" )
	private StringProperty valid ;
	@TableViewAnnotation(
			prefWidthPercent= 20
			,title="职位描述" 
			, cellType=CgTextTableCell.class
			)
	private StringProperty description ;
	@TableViewAnnotation(prefWidthPercent=20,title="创建时间" )
	private StringProperty createTime ;
	
	
	public PositionView(){
	}
	
	public PositionView(PositionDto positionDto){
		this(TypeConvertUtil.toStr(positionDto.getPositionId()) , 
				positionDto.getPositionName() , 
				TypeConvertUtil.toStr(positionDto.getTip()) ,
				positionDto.getDescription() ,
				positionDto.getValidName() ,
				TypeConvertUtil.toStr(positionDto.getCreateTime())
				);
	}
	
	public PositionView(String positionId, String positionName,
			String tip, String description, String valid ,String createTime) {
		this.positionId = new SimpleStringProperty(positionId);
		this.positionName = new SimpleStringProperty(positionName);
		this.tip = new SimpleStringProperty(tip) ;
		this.description = new SimpleStringProperty(description) ;
		this.valid = new SimpleStringProperty(valid) ;
		this.createTime = new SimpleStringProperty(createTime) ;
	}
	
	public PositionView(String positionId) {
		this.positionId = new SimpleStringProperty(positionId) ;
	}
	public StringProperty getPositionId() {
		return positionId;
	}
	public void setPositionId(StringProperty positionId) {
		this.positionId = positionId;
	}
	public StringProperty getPositionName() {
		return positionName;
	}
	public void setPositionName(StringProperty positionName) {
		this.positionName = positionName;
	}
	public StringProperty getTip() {
		return tip;
	}
	public void setTip(StringProperty tip) {
		this.tip = tip;
	}
	public StringProperty getDescription() {
		return description;
	}
	public void setDescription(StringProperty description) {
		this.description = description;
	}
	public StringProperty getCreateTime() {
		return createTime;
	}
	public void setCreateTime(StringProperty createTime) {
		this.createTime = createTime;
	}

	public void setDescription(String newValue) {
		this.description = new SimpleStringProperty(newValue);
		
	}

	public StringProperty getValid() {
		return valid;
	}

	public void setValid(StringProperty valid) {
		this.valid = valid;
	}

	public BooleanProperty getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(BooleanProperty isSelect) {
		this.isSelect = isSelect;
	}

	public StringProperty getOperator() {
		return positionId ;
	}

	public void setOperator(StringProperty operator) {
		this.operator = operator;
	}

}
