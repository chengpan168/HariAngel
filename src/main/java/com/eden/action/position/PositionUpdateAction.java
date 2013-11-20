package com.eden.action.position;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.eden.dto.PositionDto;
import com.eden.entity.Position;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.ListUtil;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.service.PositionService;

@Controller
public class PositionUpdateAction extends BaseAction{
	@Resource
	private PositionService positionService ;
	
	@ViewMapper(value="positionUpdatePane" , target=Target.blank)
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		ModelAndView mv = new ModelAndView() ;
		try {
			PositionDto positionDto = actionMessage.getAttribute("positionDto") ;
			mv.addAttribute("positionDto" , positionDto) ;
			
			Position position = new Position() ;
			position.setPositionId(TypeConvertUtil.toInt(positionDto.getPositionId())) ;
			position.setPositionName(positionDto.getPositionName()) ;
			position.setTip(TypeConvertUtil.toInt(positionDto.getTip()) ) ;
			position.setDescription(positionDto.getDescription()) ;
			position.setValid(positionDto.getValid()) ;
			
			PositionDto example = new PositionDto() ;
			example.setPositionName(positionDto.getPositionName()) ;
			
			@SuppressWarnings("unchecked")
			List<PositionDto> list = (List<PositionDto>) positionService.queryList("Position.queryByExample", example) ;
			if (ListUtil.isNotEmpty(list)) {
				for(PositionDto p : list) {
					if(!StringUtils.equals(p.getPositionId(), positionDto.getPositionId())) {
						mv.setStatus("position.positionNameExists") ;
						return mv ;
					}
				}
			}
			positionService.update(position) ;
			mv.setStatus("update.success") ;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setStatus("update.fail") ;
		}
		return mv ;
	}
}
