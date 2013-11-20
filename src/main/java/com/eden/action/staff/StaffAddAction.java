package com.eden.action.staff;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.eden.dto.StaffDto;
import com.eden.entity.Staff;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.ListUtil;
import com.eden.service.StaffService;

@Controller
public class StaffAddAction extends BaseAction{
	@Resource
	private StaffService staffService ;
	
	@ViewMapper(value="staffAddPane", target=Target.blank)
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		ModelAndView mv = new ModelAndView() ;
		try {
			StaffDto staffDto = actionMessage.getAttribute("staffDto") ;
			
			Staff staff = new Staff(staffDto) ;
			
			StaffDto example = new StaffDto() ;
			example.setStaffCode(staffDto.getStaffCode()) ;
			
			@SuppressWarnings("unchecked")
			List<StaffDto> list = (List<StaffDto>) staffService.queryList("Staff.queryByExample", example) ;
			if (ListUtil.isNotEmpty(list)) {
				mv.setStatus("staff.staffCodeExist") ;
				return mv ;
			}
			staffService.insertDto(staffDto) ;
			mv.setStatus("add.success") ;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setStatus("add.fail") ;
		}
		return mv ;
	}
}
