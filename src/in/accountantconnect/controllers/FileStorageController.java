package in.accountantconnect.controllers;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.api.response.FileStorageResponse;
import in.accountantconnect.service.accountant.EditAccountantService;
import in.accountantconnect.util.CollectionOfUtilityMethods;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/file")
public class FileStorageController {

    //@Autowired
    //ImageService imageService;
    
    @RequestMapping(value="/img/{id}", method = RequestMethod.POST)
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
	    
        return null;
    		//return editAccountantService.createAccountantWithMinimumParams(name, email, phone, isHidden);
    }
}
