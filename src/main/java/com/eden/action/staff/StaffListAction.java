package com.eden.action.staff;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.eden.dto.StaffDto;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxview.StaffView;
import com.eden.service.StaffService;

@Controller
public class StaffListAction extends BaseAction{
	private static final Log log = LogFactory.getLog(StaffListAction.class) ;
	
	@Resource
	private StaffService staffService ;
	
	@ViewMapper(value="staffListPane" , target=Target.current)
	@Override
	public ModelAndView execute(ActionMessage actionMessage ) {
		ModelAndView modelAndView = new ModelAndView() ;
		log.debug(staffService) ;
		Page page = null ;
		if(actionMessage != null)
			page = actionMessage.getAttribute(FXConstant.PAGE) ;
		
		if(page == null) {
			page = new Page() ;
		} 
		
		int total = staffService.queryCount("Staff.queryCount" , page) ;
		page.setTotal(total) ;
		modelAndView.addAttribute(FXConstant.PAGE	 , page) ;
		
		if(total == 0) return modelAndView ;
		
		List<StaffDto> list = (List<StaffDto>) staffService.queryPageStaff(page) ;
		
		List<StaffView> viewList = new ArrayList<StaffView>() ;
		for(StaffDto staffDto : list) {
			StaffView staffView = new StaffView(staffDto) ;
			viewList.add(staffView) ;
		}
		
		modelAndView.addAttribute("list" , FXCollections.observableArrayList(viewList)) ;
		return modelAndView ;
	}

	public ModelAndView beforeQuery(){
		return null ;
	}
	
	public StaffService getStaffService() {
		return staffService;
	}

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}

}
