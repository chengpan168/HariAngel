package com.eden.fxview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import com.eden.annotation.TableViewAnnotation;
import com.eden.annotation.TableViewAnnotation.TitleType;
import com.eden.component.table.CgCheckBoxTableCell;
import com.eden.component.table.CgOperatorTableCell;
import com.eden.dto.StaffDto;
import com.eden.fxmvc.util.ListUtil;
import com.eden.fxmvc.util.TypeConvertUtil;

public class StaffView {
	
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
	
	private StringProperty staffId ;
	
	@TableViewAnnotation(
			prefWidthPercent=5
			,title="职员编号" 
			)
	private StringProperty staffCode ;
	@TableViewAnnotation(prefWidthPercent=5,title="姓名" )
	private StringProperty staffName ;
	@TableViewAnnotation(prefWidthPercent=10,title="职位" ,orderEnable = false )
	private StringProperty positions ;
	
	private ObservableList<String> positionList = FXCollections.observableArrayList();
	
	@TableViewAnnotation(prefWidthPercent=5,title="状态" )
	private StringProperty status ;
	@TableViewAnnotation(prefWidthPercent=10,title="手机号码" )
	private StringProperty phoneNum ;
	
	@TableViewAnnotation(prefWidthPercent=5,title="性别" )
	private StringProperty sex ;
	@TableViewAnnotation(prefWidthPercent=10,title="住址" )
	private StringProperty address ;
	
	@TableViewAnnotation(prefWidthPercent=10,title="身份证号" )
	private StringProperty idCardNum ;
	
	@TableViewAnnotation(prefWidthPercent=10,title="银行卡号" )
	private StringProperty accountNum ;
	
	@TableViewAnnotation(prefWidthPercent=10,title="入职时间" )
	private StringProperty entryTime ;
	
	@TableViewAnnotation(prefWidthPercent=10,title="出生年月" )
	private StringProperty birthTime ;

	@TableViewAnnotation(prefWidthPercent=10,title="创建时间" )
	private StringProperty createTime ;
	
	@TableViewAnnotation( prefWidthPercent= 10 ,title="备注"  )
	private StringProperty remarks ;
	
	public StaffView(){
	}
	
	public StaffView(String staffCode) {
		this.staffCode = new SimpleStringProperty(staffCode) ;
	}
	public StaffView(StaffDto staffDto) {
		this.staffId = new SimpleStringProperty(staffDto.getStaffId()) ;
		this.staffCode = new SimpleStringProperty(staffDto.getStaffCode() ) ;
		this.staffName = new SimpleStringProperty(staffDto.getStaffName()) ;
		this.idCardNum = new SimpleStringProperty(staffDto.getIdCardNum()) ;
		this.phoneNum = new SimpleStringProperty(staffDto.getPhoneNum() );
		this.accountNum = new SimpleStringProperty(staffDto.getAccountNum()) ;
		this.address = new SimpleStringProperty(staffDto.getAddress()) ;
		this.sex = new SimpleStringProperty(staffDto.getSexName()) ;
		this.status = new SimpleStringProperty(staffDto.getStatusName() );
		this.entryTime = new SimpleStringProperty(TypeConvertUtil.toStr(staffDto.getEntryTime()) );
		this.birthTime = new SimpleStringProperty( TypeConvertUtil.toStr(staffDto.getBirthTime()) ) ;
		this.createTime = new SimpleStringProperty(TypeConvertUtil.toStr(staffDto.getCreateTime())) ;
		this.remarks = new SimpleStringProperty(staffDto.getRemarks()) ;
		
		this.positions = new SimpleStringProperty() ;
		
		this.positionList.addListener(new ListChangeListener<String>(){
			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends String> paramChange) {
				positions.setValue(ListUtil.join(positionList, ";") );
			}
		});
		if(staffDto.getPositions() != null )
			this.positionList.setAll(staffDto.getPositions()) ;
	}

	public StringProperty getCreateTime() {
		return createTime;
	}
	public void setCreateTime(StringProperty createTime) {
		this.createTime = createTime;
	}

//------------------------------------------------------------------
	public BooleanProperty getIsSelect() {
		return isSelect;
	}

	public void setIsSelect(BooleanProperty isSelect) {
		this.isSelect = isSelect;
	}

	public StringProperty getOperator() {
		return staffId ;
	}

	public void setOperator(StringProperty operator) {
		this.operator = operator;
	}
//-------------------------------------------------------------------

	public StringProperty getStaffId() {
		return staffId;
	}

	public void setStaffId(StringProperty staffId) {
		this.staffId = staffId;
	}

	public StringProperty getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(StringProperty staffCode) {
		this.staffCode = staffCode;
	}

	public StringProperty getStaffName() {
		return staffName;
	}

	public void setStaffName(StringProperty staffName) {
		this.staffName = staffName;
	}

	public StringProperty getPositions() {
		return positions;
	}

	public void setPositions(StringProperty positions) {
		this.positions = positions;
	}

	public StringProperty getStatus() {
		return status;
	}

	public void setStatus(StringProperty status) {
		this.status = status;
	}

	public StringProperty getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(StringProperty phoneNum) {
		this.phoneNum = phoneNum;
	}

	public StringProperty getSex() {
		return sex;
	}

	public void setSex(StringProperty sex) {
		this.sex = sex;
	}

	public StringProperty getAddress() {
		return address;
	}

	public void setAddress(StringProperty address) {
		this.address = address;
	}

	public StringProperty getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(StringProperty idCardNum) {
		this.idCardNum = idCardNum;
	}

	public StringProperty getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(StringProperty accountNum) {
		this.accountNum = accountNum;
	}

	public StringProperty getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(StringProperty entryTime) {
		this.entryTime = entryTime;
	}

	public StringProperty getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(StringProperty birthTime) {
		this.birthTime = birthTime;
	}

	public StringProperty getRemarks() {
		return remarks;
	}

	public void setRemarks(StringProperty remarks) {
		this.remarks = remarks;
	}

	public ObservableList<String> getPositionList() {
		return positionList;
	}

	public void setPositionList(ObservableList<String> positionList) {
		this.positionList = positionList;
	}

}
