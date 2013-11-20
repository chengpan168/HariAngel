package com.eden.dto;

import java.sql.Timestamp;
import java.util.List;

import com.eden.fxmvc.mvc.annotation.ViewColumn;
import com.eden.fxmvc.util.ListUtil;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.fxmvc.util.ValidateUtil;
import com.eden.fxmvc.validation.Errors;
import com.eden.fxmvc.validation.Validator;
import com.eden.fxview.StaffView;



public class StaffDto implements Validator {
	
	enum Status {
		in { 
			String getName(){return "在职" ;} 
			int getCode(){return 1 ;}
			
		} ,
		out  { 
			String getName(){return "离职" ;} 
			int getCode(){ return 2 ; }
		} ;
		abstract String getName();
		abstract int getCode();
	}
	
	enum Sex {
		F { 
			String getName(){return "男" ;} 
			int getCode(){return 1 ;}
			
		} ,
		M  { 
			String getName(){return "女" ;} 
			int getCode(){ return 2 ; }
		} ;
		abstract String getName();
		abstract int getCode();
	}
	
	private String staffId ;
	@ViewColumn(nameCn="姓名")
	private String staffName ;
	@ViewColumn(nameCn="职员编码")
	private String staffCode ;
	private String idCardNum ;
	@ViewColumn(nameCn="手机号码")
	private String phoneNum ;
	private String accountNum ;
	private String address ;
	@ViewColumn(nameCn="性别")
	private int sex ;
	private String sexName ;
	private String statusName ;
	@ViewColumn(nameCn="状态")
	private int status ;
	private Timestamp entryTime ;
	private Timestamp birthTime ;
	private Timestamp createTime ;
	private String remarks ;
	private List<String> positions ;
	
	public StaffDto(){}

	public StaffDto(StaffView staffView) {
		staffId = staffView.getStaffId().getValue();
		staffName = staffView.getStaffName().getValue();
		staffCode = staffView.getStaffCode().getValue();
		idCardNum = staffView.getIdCardNum().getValue();
		phoneNum = staffView.getPhoneNum().getValue();
		accountNum = staffView.getAccountNum().getValue();
		address = staffView.getAddress().getValue();
		setSexName(staffView.getSex().getValue());
		setStatusName(staffView.getStatus().getValue());
		entryTime = TypeConvertUtil.toTimestamp(staffView.getEntryTime().getValue() , "yyyy-MM-dd hh:mm:ss");
		birthTime = TypeConvertUtil.toTimestamp(staffView.getBirthTime().getValue() , "yyyy-MM-dd hh:mm:ss");
		remarks = staffView.getRemarks().getValue();
		positions = staffView.getPositionList();
	}

	public Errors validate() {
		Errors err = new Errors() ;
		if(!ValidateUtil.isLengthLimit(staffCode, 1, 10) || !ValidateUtil.isNumber(staffCode)){
				err.setStatus("staff.staffCodeErr") ;
		} 
		else if(!ValidateUtil.isLengthLimit(staffName, 1, 20)) {
			err.setStatus("staff.staffNameErr") ;
		} 
		else if(!ValidateUtil.isLengthLimit(phoneNum, 11, 11) || !ValidateUtil.isNumber(phoneNum)){
			err.setStatus("staff.phoneNumErr") ;
		} 
		else if(!ValidateUtil.isLengthLimit(address, 0 , 100)){
			err.setStatus("staff.addressErr") ;
		} 
		
		else if(ListUtil.isEmpty(positions)){
			err.setStatus("staff.positionErr") ;
		}
		
		else if(!ValidateUtil.isLengthLimit(idCardNum, 1, 20) ){
			err.setStatus("staff.idCardNumErr") ;
		}
		else if(!ValidateUtil.isLengthLimit(accountNum, 0, 20)){
			err.setStatus("staff.accountNumErr") ;
		}
		else if(entryTime == null ){
			err.setStatus("staff.entryTimeErr") ;
		} 
//		else if(birthTime  == null ){
//			err.setStatus("staff.birthTimeErr") ;
//		}  
		else if(!ValidateUtil.isLengthLimit(remarks, 0 , 100)){
			err.setStatus("staff.remarksErr") ;
		}
		return err ;
	}

	public void setStatus(Integer status) {
		this.status = status;
		this.statusName = getStatusName(status) ;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
		this.status = getStatus(statusName) ;
	}

	
	public static int getStatus(String statusName) {
		for(Status s : Status.values()) {
			if(s.getName().equals(statusName)){
				return s.getCode() ;
			}
		}
		return -1 ;
	}
	public static String getStatusName(int status) {
		for(Status s : Status.values()) {
			if(s.getCode() == status){
				return s.getName() ;
			}
		}
		return null ;
	}
	
	public static int getSex(String sexName) {
		for(Sex s : Sex.values()){
			if(s.getName().equals(sexName)){
				return s.getCode() ;
			}
		}
		return -1 ;
	}
	public static String getSexName(int sex) {
		for(Sex s : Sex.values()) {
			if(s.getCode() == sex){
				return s.getName() ;
			}
		}
		return null ;
	}
	

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
		this.sexName = getSexName(sex) ;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
		this.sex = getSex(sexName) ;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		this.statusName = getStatusName(status) ;
	}
	

	public Timestamp getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Timestamp entryTime) {
		this.entryTime = entryTime;
	}

	public Timestamp getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(Timestamp birthTime) {
		this.birthTime = birthTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getStatusName() {
		return statusName;
	}

	@Override
	public String toString() {
		return "StaffDto [staffId=" + staffId + ", staffName=" + staffName
				+ ", staffCode=" + staffCode + ", idCardNum=" + idCardNum
				+ ", phoneNum=" + phoneNum + ", accountNum=" + accountNum
				+ ", address=" + address + ", sex=" + sex + ", sexName="
				+ sexName + ", statusName=" + statusName + ", status=" + status
				+ ", entryTime=" + entryTime + ", birthTime=" + birthTime
				+ ", createTime=" + createTime + "]";
	}

	public void setRemarks(String newRemarks) {
		this.remarks = newRemarks ;
		
	}

	public String getRemarks() {
		return remarks;
	}

	public List<String> getPositions() {
		return positions;
	}

	public void setPositions(List<String> positions) {
		this.positions = positions;
	}
}
