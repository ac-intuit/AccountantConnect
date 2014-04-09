package in.accountantconnect.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.common.MessageCollection;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.domain.ProfileCompletionStatus;
import in.accountantconnect.service.accountant.EditAccountantService;
import in.accountantconnect.util.CollectionOfUtilityMethods;
import in.accountantconnect.util.EnumCollection.EventStatus;
import in.accountantconnect.web.AppResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public AppResponse<Integer> addAccountantWithMinParams(HttpServletRequest req){
    		String name = req.getParameter("accountant_name");
    		String email = req.getParameter("accountant_email");
    		String phone = req.getParameter("accountant_phone");
    		String password = req.getParameter("accountant_password");
    		String isHiddenStr = CollectionOfUtilityMethods.getParamValIfNotNullElseGetDefaultVal("isHidden", "1", req);
    		//By default profile is created in hidden mode.
    		//It is only shown only after all the mandatory sections in the 
    		//profile is filled.
    		Integer isHidden = 1; 
    	
    		try{
    			isHidden = Integer.parseInt(isHiddenStr);
    		}catch(Exception e){}  	

    		String[] firstNameAndLastNameArray = CollectionOfUtilityMethods.getFirstNameAndLastNameArrayFromFullName(name);
    		
    		String firstName = firstNameAndLastNameArray[0];
    		String lastName = firstNameAndLastNameArray[1];
    		return editAccountantService.createAccountantWithMinimumParams(firstName, lastName, email, phone, isHidden, password);
    }   
    
    @RequestMapping(value="/saveProfile", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<ProfileCompletionStatus> saveProfileWithBasicInfo(HttpServletRequest req){
       	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<ProfileCompletionStatus>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	int id = CollectionOfUtilityMethods.parseToNumber(req.getParameter("accountantid"));
    	String firstName = req.getParameter("firstName");
    	String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String businessPhone = req.getParameter("businessPhone");
        String city = req.getParameter("city");
        String area = req.getParameter("area");
        
        Integer age = CollectionOfUtilityMethods.parseToNumber(req.getParameter("age"));
        
        String shortDescription = req.getParameter("shortDescription");
        String longDescription = req.getParameter("longDescription");
        
        String linkedinProfile = req.getParameter("linkedinProfile");
        String fbProfile = req.getParameter("fbProfile");
        String gplusProfile = req.getParameter("gplusProfile");
        
        Integer yearOfExp = CollectionOfUtilityMethods.parseToNumber(req.getParameter("yearOfExp"));
        String areasOfExpertise = req.getParameter("areasOfExpertise");

        String education = req.getParameter("education");
        String certifications = req.getParameter("certifications");
        
        String addressLine1 = req.getParameter("addressLine1");
        String addressLine2 = req.getParameter("addressLine2");
        String state = req.getParameter("state");
        Integer pincode = CollectionOfUtilityMethods.parseToNumber(req.getParameter("pincode"));
        String country = req.getParameter("country");
        if(country == null || "".equals(country)){
        	country = "india";
        }

        return editAccountantService.saveAccountantProfile(id, email, firstName, lastName, 
        		mobile, businessPhone, city, age, area, 
        		shortDescription, longDescription, 
        		linkedinProfile, fbProfile, gplusProfile, 
        		yearOfExp, areasOfExpertise, 
        		education, certifications, 
        		addressLine1, addressLine2, state, pincode, country);
    }
    
    @RequestMapping(value="/profile", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Accountant> getAccountantForSession(HttpServletRequest req){
    	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<Accountant>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	HttpSession session = req.getSession(false);
    	Integer accountantId = (Integer)session.getAttribute("accountantid");
    	if(accountantId == null){
    		return new AppResponse<Accountant>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}    	
    	return editAccountantService.getAccountantById(accountantId);
    }
    
    @RequestMapping(value="/profileCompletionStatus", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Accountant> getProfileCompletionStatus(HttpServletRequest req){
    	if(!CollectionOfUtilityMethods.isReqInSession(req)){
    		return new AppResponse<Accountant>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}
    	HttpSession session = req.getSession(false);
    	Integer accountantId = (Integer)session.getAttribute("accountantid");
    	if(accountantId == null){
    		return new AppResponse<Accountant>(EventStatus.nosession.getValue(), null, MessageCollection.NEED_TO_LOGIN_TO_ACCESS_THIS_FEATURE);
    	}    	
    	return editAccountantService.getAccountantById(accountantId);
    }

    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Accountant> getAccountantById(Model model, @PathVariable("id") int id){
    	return editAccountantService.getAccountantById(id);
    }
    
    @RequestMapping(value="/testlogin", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> isAccountantValid(HttpServletRequest req){
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	return editAccountantService.isAccountantValid(email, password);
    }
    
    @RequestMapping(value="/setpass", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<Integer> setPassword(HttpServletRequest req){
    	String email = req.getParameter("email");
    	String password = req.getParameter("password");
    	return editAccountantService.saveAccountantPassword(email, password);
    }
}
