package com.eden.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.eden.dto.StaffDto;
import com.eden.entity.Staff;
import com.eden.entity.StaffPosition;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.service.EntityServiceImpl;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.service.StaffService;

@Service("staffService")
public class StaffServiceImpl extends EntityServiceImpl implements StaffService{

	@Override
	public int insertDto(StaffDto staffDto) {
		Staff staff = new Staff(staffDto) ;
		int count = insertBatch(staff);
		
		List<StaffPosition> list = new ArrayList<StaffPosition>() ;
		
		Timestamp createTime = new Timestamp(System.currentTimeMillis()) ;
		for(String positionId : staffDto.getPositions()){
			StaffPosition sp = new StaffPosition()  ;
			
			sp.setStaffId(staff.getStaffId()) ;
			sp.setPositionId(TypeConvertUtil.toInt(positionId)) ;
			sp.setCreateTime(createTime) ;
			list.add(sp) ;
		}
		
		insertAll("Staff.staffPosition" , list ) ;
		
		return count ;
	}

	
	public int updateStaff(StaffDto staffDto){
		Staff staff = new Staff(staffDto) ;
		int count = updateBatch(staff);
		
		List<StaffDto> staffDtoExample = new ArrayList<StaffDto>(2) ;
		staffDtoExample.add(staffDto) ;
		List<StaffPosition> staffPositions = (List<StaffPosition>) queryListBatch("Staff.queryPosition" , staffDtoExample) ;
		
		deleteBatch("Staff.deleteStaffPosition", staffPositions) ;
		
		List<StaffPosition> list = new ArrayList<StaffPosition>() ;
		Timestamp createTime = new Timestamp(System.currentTimeMillis()) ;
		for(String positionId : staffDto.getPositions()){
			StaffPosition sp = new StaffPosition()  ;
			sp.setStaffId(staff.getStaffId()) ;
			sp.setPositionId(TypeConvertUtil.toInt(positionId)) ;
			sp.setCreateTime(createTime) ;
			list.add(sp) ;
		}
		
		insertAll("Staff.staffPosition" , list ) ;
		
		return count ;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StaffDto> queryPageStaff(Page page) {
		List<StaffDto> list = (List<StaffDto>) queryPage("Staff.queryPage" , page) ;
		List<StaffPosition> sp = (List<StaffPosition>) queryList("Staff.queryPosition" , list) ;
		for(StaffDto staffDto : list) {
			List<String> positions = staffDto.getPositions() ;
			if(positions == null) positions = new ArrayList<String>() ;
			
			for(StaffPosition staffPosition : sp ) {
				if(StringUtils.equals(TypeConvertUtil.toStr(staffPosition.getStaffId()), staffDto.getStaffId())){
					positions.add(TypeConvertUtil.toStr(staffPosition.getPositionName())) ;
				}
			}
			staffDto.setPositions(positions) ;
		}
		return list ;
	}

}
