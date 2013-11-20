package com.eden.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.eden.dto.StaffDto;
import com.eden.fxmvc.util.TypeConvertUtil;

public class Staff implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer staffId ;
	private String staffName ;
	private Integer staffCode ;
	private String idCardNum ;
	private String phoneNum ;
	private String accountNum ;
	private String address ;
	private Integer sex ;
	private Integer status ;
	private Timestamp entryTime ;
	private Timestamp birthTime ;
	private Timestamp createTime ;
	private String remarks ;
	
	
	public Staff(StaffDto staffDto) {
		this.staffId = TypeConvertUtil.toInt(staffDto.getStaffId()) ;
		this.staffCode = TypeConvertUtil.toInt(staffDto.getStaffCode()) ;
		this.staffName = staffDto.getStaffName() ;
		this.idCardNum = staffDto.getIdCardNum() ;
		this.phoneNum = staffDto.getPhoneNum() ;
		this.accountNum = staffDto.getAccountNum() ;
		this.address = staffDto.getAddress() ;
		this.sex = staffDto.getSex() ;
		this.status = staffDto.getStatus() ;
		this.entryTime = staffDto.getEntryTime() ;
		this.birthTime = staffDto.getBirthTime() ;
		this.createTime = new Timestamp(System.currentTimeMillis()) ;
		this.remarks = staffDto.getRemarks() ;
	}
	public Staff() {
	}
	public Integer getStaffId() {
		return staffId;
	}
	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Integer getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(Integer staffCode) {
		this.staffCode = staffCode;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
