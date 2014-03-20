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
public class AccountantController {
    @Autowired
    EditAccountantService editAccountantService;
    
    @RequestMapping(value="/createAccountantWithMinParams", method = RequestMethod.POST)
    @ResponseBody
    public EditAccountantResponse addAccountantWithMinParams(HttpServletRequest req){
    		String name = req.getParameter("accountant_name");
    		String email = req.getParameter("accountant_email");
    		String phone = req.getParameter("accountant_phone");
    		String isHiddenStr = CollectionOfUtilityMethods.getParamValIfNotNullElseGetDefaultVal("isHidden", "0", req);
    		Integer isHidden = 0;
    		try{
    			isHidden = Integer.parseInt(isHiddenStr);
    		}catch(Exception e){}
    	
    		return editAccountantService.createAccountantWithMinimumParams(name, email, phone, isHidden);
    } 
    
    @RequestMapping(value="/createProfile", method = RequestMethod.POST)
	@ResponseBody
	public EditAccountantResponse createProfile(HttpServletRequest req){
    	
    	int accountantId=Integer.parseInt(req.getParameter("accountant_Id"));
    	String imgLink=	req.getParameter("img_link");
		String name = req.getParameter("accountant_name");
		String email = req.getParameter("accountant_email");
		String phone = req.getParameter("accountant_phone");
		String companyName=req.getParameter("company_name");
		String companyAddress=req.getParameter("company_address");
		String city=req.getParameter("accountant_city");
		String speciality=req.getParameter("speciality");
		String shortDesc=req.getParameter("short_desc");
		String facebookId=req.getParameter("facebook_id");
		String linkedinId=req.getParameter("linkedin_id");
		String twitterId=req.getParameter("twitter_id");
		String clients=req.getParameter("clients");
		String education=req.getParameter("education");
		String experience=req.getParameter("experience");
	 
		String isHiddenStr = CollectionOfUtilityMethods.getParamValIfNotNullElseGetDefaultVal("isHidden", "0", req);
		Integer isHidden = 0;
		try{
			isHidden = Integer.parseInt(isHiddenStr);
		}catch(Exception e){}

		return editAccountantService.createAccountantProfile(accountantId,imgLink,name, email, phone, companyName,companyAddress,city,speciality,shortDesc,facebookId,linkedinId,twitterId,clients,education,experience);
	}
    
    
    @RequestMapping(value="/viewDetailProfile", method = RequestMethod.POST)
	@ResponseBody
	public EditAccountantResponse viewDetailProfile(HttpServletRequest req){
    	String email=req.getParameter("email_id");
    	int profileId=Integer.parseInt(req.getParameter("profile_Id"));
		return editAccountantService.viewAccountantProfile(email,profileId);
	}
    
    @RequestMapping(value="/writeReview", method = RequestMethod.POST)
 	@ResponseBody
 	public EditAccountantResponse writeReview(HttpServletRequest req){
    	 
    	int profileId=Integer.parseInt(req.getParameter("profile_Id"));
    	String reviewDesc=req.getParameter("review_desc");
    	
		return editAccountantService.writeReview(profileId, reviewDesc);
	}
 	
    
    
    
}
