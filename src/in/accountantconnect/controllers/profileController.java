package in.accountantconnect.controllers;

import javax.servlet.http.HttpServletRequest;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.service.accountant.EditAccountantService;
import in.accountantconnect.util.CollectionOfUtilityMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/accountant")
public class profileController {
	@Autowired
	EditAccountantService editAccountantService;

	@RequestMapping(value="/createProfile", method = RequestMethod.POST)
	@ResponseBody
	public EditAccountantResponse createProfile(HttpServletRequest req){
		String name = req.getParameter("accountant_name");
		String email = req.getParameter("accountant_email");
		String phone = req.getParameter("accountant_phone");
		String companyName=req.getParameter("company_name");
		String companyDesc=req.getParameter("company_description");
		String city=req.getParameter("accountant_city");
		String speciality=req.getParameter("accountant_speciality");
		String isHiddenStr = CollectionOfUtilityMethods.getParamValIfNotNullElseGetDefaultVal("isHidden", "0", req);
		Integer isHidden = 0;
		try{
			isHidden = Integer.parseInt(isHiddenStr);
		}catch(Exception e){}

		return editAccountantService.createAccountantProfile(name, email, phone, companyName,companyDesc,phone,city,speciality);
	}
}

