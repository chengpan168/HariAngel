package com.eden.dao.impl;

import org.springframework.stereotype.Repository;

import com.eden.dao.PositionDao;
import com.eden.entity.Position;
import com.eden.fxmvc.dao.support.IBatisEntityDao;

@Repository("positionDao")
public class PositionDaoImpl extends IBatisEntityDao implements PositionDao {

}
