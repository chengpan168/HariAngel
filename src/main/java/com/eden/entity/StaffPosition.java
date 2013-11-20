package com.eden.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class StaffPosition implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int staffPositionId ;
	private int staffId ; 
	private int positionId ;
	
	private String positionName ;
	
	private Timestamp createTime ;
	
	
	public int getStaffPositionId() {
		return staffPositionId;
	}
	public void setStaffPositionId(int staffPositionId) {
		this.staffPositionId = staffPositionId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	

}
