package com.eden.action.position;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import com.eden.dto.PositionDto;
import com.eden.fxmvc.constant.FXConstant;
import com.eden.fxmvc.dao.support.Page;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxview.PositionView;
import com.eden.service.PositionService;

@Controller
public class PositionAction extends BaseAction{
	private static final Log log = LogFactory.getLog(PositionAction.class) ;
	
	@Resource
	private PositionService positionService ;
	
	@SuppressWarnings("unchecked")
	@ViewMapper(value="positionPane" , target=Target.current)
	@Override
	public ModelAndView execute(ActionMessage actionMessage ) {
		ModelAndView modelAndView = new ModelAndView() ;
		log.debug(positionService) ;
		Page page = null ;
		if(actionMessage != null)
			page = actionMessage.getAttribute(FXConstant.PAGE) ;
		
		if(page == null) {
			page = new Page() ;
		} 
		
		int total = positionService.queryCount("Position.queryCount" , page) ;
		page.setTotal(total) ;
		modelAndView.addAttribute(FXConstant.PAGE	 , page) ;
		
		if(total == 0) return modelAndView ;
		
		List<PositionDto> list = (List<PositionDto>) positionService.queryPage("Position.queryPage" , page) ;
		
		List<PositionView> viewList = new ArrayList<PositionView>() ;
		for(PositionDto positionDto : list) {
			viewList.add(new PositionView(positionDto)) ;
		}
		
		modelAndView.addAttribute("list" , FXCollections.observableArrayList(viewList)) ;
		return modelAndView ;
	}

	public ModelAndView beforeQuery(){
		return null ;
	}
	
	public PositionService getPositionService() {
		return positionService;
	}

	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

}
