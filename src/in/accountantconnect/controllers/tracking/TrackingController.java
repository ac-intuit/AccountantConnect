package in.accountantconnect.controllers.tracking;

import javax.servlet.http.HttpServletRequest;

import in.accountantconnect.api.response.GenericSuccessFailureResponse;
import in.accountantconnect.service.tracking.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/tracking")
public class TrackingController {
   
	@Autowired TrackingService trackingService;
	
	@RequestMapping(value="/addTracking", method = RequestMethod.POST)
    @ResponseBody
    public GenericSuccessFailureResponse addTracking(HttpServletRequest req){
		//Capture IP address
		String ipAddress = req.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
		    ipAddress = req.getRemoteAddr();
		}
		
		String accountantidStr = req.getParameter("accountant_id");
		String businessidStr = req.getParameter("business_id");
		Integer accountantid = null;
		Integer businessid = null;
		try{
			accountantid = Integer.parseInt(accountantidStr);
		}catch(NumberFormatException e){}
		try{
			businessid = Integer.parseInt(businessidStr);
		}catch(NumberFormatException e){}
		
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String feature = req.getParameter("feature");
		
		return trackingService.addTracking(accountantid, businessid, email, mobile, ipAddress, feature);
	}

	public TrackingService getTrackingService() {
		return trackingService;
	}

	public void setTrackingService(TrackingService trackingService) {
		this.trackingService = trackingService;
	}
}
