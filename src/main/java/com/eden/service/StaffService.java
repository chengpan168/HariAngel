package com.eden.service;

import java.util.List;

import com.eden.dto.StaffDto;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.service.EntityService;

public interface StaffService extends EntityService{

	/**
	 * 保存一个职员，同时保存职员职位关系
	 * @param staffDto
	 * @return
	 */
	public int insertDto(StaffDto staffDto) ;

	/**
	 * 查询职员信息
	 * @param page
	 * @return
	 */
	public List<StaffDto> queryPageStaff(Page page);

	/**
	 * 更改职员信息
	 * @param staffDto
	 * @return
	 */
	public int updateStaff(StaffDto staffDto);

}
