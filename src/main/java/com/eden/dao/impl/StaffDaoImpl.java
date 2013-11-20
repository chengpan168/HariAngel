package com.eden.dao.impl;

import org.springframework.stereotype.Repository;

import com.eden.dao.StaffDao;
import com.eden.entity.Staff;
import com.eden.fxmvc.dao.support.IBatisEntityDao;

@Repository("staffDao")
public class StaffDaoImpl extends IBatisEntityDao implements StaffDao {

}
