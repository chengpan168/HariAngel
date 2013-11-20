package com.eden.action.staff;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.eden.entity.Position;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.service.PositionService;

@Controller
public class StaffToAddAction extends BaseAction{
	@Resource
	private PositionService positionService ;
	
	@ViewMapper(value="staffAddPane", target=Target.blank)
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		ModelAndView mv = new ModelAndView() ;
		try {
			List<Position> positions = positionService.queryList(Position.class , "queryAll") ;
			mv.addAttribute("positions", positions) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv ;
	}
}
