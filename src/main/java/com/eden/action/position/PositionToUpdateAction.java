package com.eden.action.position;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.eden.constant.Constant;
import com.eden.dto.PositionDto;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxview.PositionView;
import com.eden.service.PositionService;

@Controller
public class PositionToUpdateAction extends BaseAction{
	@Resource
	private PositionService positionService ;
	
	@ViewMapper(value="positionUpdatePane" , target=Target.blank)
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		ModelAndView mv = new ModelAndView() ;
		try {
			PositionView positionView = actionMessage.getAttribute(Constant.UPDATE_DATA) ; 
			
			PositionDto positionDto =  new PositionDto()  ;
			positionDto.setPositionId(positionView.getPositionId().getValue()) ;
			positionDto.setPositionName(positionView.getPositionName().getValue()) ;
			positionDto.setTip(positionView.getTip().getValue() ) ;
			positionDto.setDescription(positionView.getDescription().getValue()) ;
			positionDto.setValidName(positionView.getValid().getValue()) ;
			mv.addAttribute("positionDto", positionDto) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv ;
	}
}
