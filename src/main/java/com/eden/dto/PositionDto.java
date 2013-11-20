package com.eden.dto;

import java.sql.Timestamp;

import com.eden.fxmvc.mvc.annotation.ViewColumn;
import com.eden.fxmvc.util.ValidateUtil;
import com.eden.fxmvc.validation.Errors;
import com.eden.fxmvc.validation.Validator;



public class PositionDto implements Validator {
	
	enum Valid {
		valid { 
			String getName(){return "有效" ;} 
			int getCode(){return 1 ;}
			
		} ,
		invalid  { 
			String getName(){return "无效" ;} 
			int getCode(){ return 2 ; }
		} ;
		abstract String getName();
		abstract int getCode();
	}
	@ViewColumn(nameCn="职位编码")
	private String positionId ;
	@ViewColumn(nameCn="职位名称")
	private String positionName ;
	private String tip ;
	private String description ;
	@ViewColumn(nameCn="状态")
	private Integer valid ;
	private String validName ;
	private Timestamp createTime ;

	
	
	public PositionDto(){}

	public Errors validate() {
		Errors err = new Errors() ;
		if(!ValidateUtil.isLengthLimit(positionName, 1, 20)) {
			err.setStatus("position.positionNameErr") ;
		} else if(!ValidateUtil.isNumber(tip) 
				|| !ValidateUtil.isLengthLimit(tip , 1, 8 )){
			err.setStatus("position.tipErr") ;
		} else if(!ValidateUtil.isLengthLimit(description, 0 , 100)) {
			err.setStatus("position.descErr") ;
		}
		
		return err ;
	}
	
	
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "PositionDto [positionId=" + positionId + ", positionName="
				+ positionName + ", tip=" + tip + ", valid=" + valid
				+ ", description=" + description + ", createTime=" + createTime
				+ "]";
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
		for(Valid v : Valid.values()) {
			if(v.getCode() == valid ){
				this.validName = v.getName() ;
				break ;
			}
		}
	}

	public String getValidName() {
		return validName;
	}

	public void setValidName(String validName) {
		this.validName = validName;
		this.valid = getValidCode(validName) ;
	}

	
	public static int getValidCode(String validName) {
		for(Valid valid : Valid.values()) {
			if(valid.getName().equals(validName)){
				return valid.getCode() ;
			}
		}
		return -1 ;
	}
}
