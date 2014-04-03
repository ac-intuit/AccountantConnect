package in.accountantconnect.controllers;

import javax.servlet.http.HttpServletRequest;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.service.accountant.EditAccountantService;
import in.accountantconnect.util.CollectionOfUtilityMethods;
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
    
    @RequestMapping(value="/saveProfile", method = RequestMethod.POST)
    @ResponseBody
    public AppResponse<String> saveProfileWithBasicInfo(HttpServletRequest req){
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

        return editAccountantService.saveAccountantProfile(id, email, firstName, lastName, 
        		mobile, businessPhone, city, age, area, 
        		shortDescription, longDescription, 
        		linkedinProfile, fbProfile, gplusProfile, 
        		yearOfExp, areasOfExpertise, 
        		education, certifications, 
        		addressLine1, addressLine2, state, pincode, "India");
    }
    
    @RequestMapping(value="/profile/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AppResponse<Accountant> getAccountantById(Model model, @PathVariable("id") int id){
    	return editAccountantService.getAccountantById(id);
    }
}
