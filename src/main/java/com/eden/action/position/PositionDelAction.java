package com.eden.action.position;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.service.PositionService;

@Controller
public class PositionDelAction extends BaseAction {

	@Resource
	private PositionService positionService ;
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		List<Integer> ids = actionMessage.getAttribute("ids") ;
		
		int count = positionService.delete("Position.deleteByIds", ids) ;
		ModelAndView mv = new ModelAndView() ;
		mv.addAttribute("status", count) ;
		return mv ;
	}
	
	
	public PositionService getPositionService() {
		return positionService;
	}
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

}
