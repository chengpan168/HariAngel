package com.eden.action.staff;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;

import com.eden.dto.StaffDto;
import com.eden.entity.Staff;
import com.eden.fxmvc.mvc.action.ActionMessage;
import com.eden.fxmvc.mvc.action.BaseAction;
import com.eden.fxmvc.mvc.annotation.ViewMapper;
import com.eden.fxmvc.mvc.annotation.ViewMapper.Target;
import com.eden.fxmvc.ui.ModelAndView;
import com.eden.fxmvc.util.ListUtil;
import com.eden.fxmvc.util.TypeConvertUtil;
import com.eden.service.StaffService;

@Controller
public class StaffUpdateAction extends BaseAction{
	@Resource
	private StaffService staffService ;
	
	@ViewMapper(value="staffUpdatePane" , target=Target.blank)
	@Override
	public ModelAndView execute(ActionMessage actionMessage) {
		ModelAndView mv = new ModelAndView() ;
		try {
			StaffDto staffDto = actionMessage.getAttribute("staffDto") ;
			mv.addAttribute("staffDto" , staffDto) ;
			
			
			StaffDto example = new StaffDto() ;
			example.setStaffName(staffDto.getStaffName()) ;
			
			@SuppressWarnings("unchecked")
			List<StaffDto> list = (List<StaffDto>) staffService.queryList("Staff.queryByExample", example) ;
			if (ListUtil.isNotEmpty(list)) {
				for(StaffDto p : list) {
					if(!StringUtils.equals(p.getStaffId(), staffDto.getStaffId())) {
						mv.setStatus("staff.staffCodeExist") ;
						return mv ;
					}
				}
			}
			staffService.updateStaff(staffDto) ;
			mv.setStatus("update.success") ;
		} catch (Exception e) {
			e.printStackTrace();
			mv.setStatus("update.fail") ;
		}
		return mv ;
	}
}
