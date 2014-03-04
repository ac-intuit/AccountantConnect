package in.accountantconnect.service.tracking;

import org.springframework.transaction.annotation.Transactional;

import in.accountantconnect.api.response.GenericSuccessFailureResponse;
import in.accountantconnect.dao.TrackingDao;
import in.accountantconnect.domain.Tracking;

public class TrackingService {
	private TrackingDao trackingDao;
	
	@Transactional
	public GenericSuccessFailureResponse addTracking(Integer accountantid, Integer businessid, 
			String email, String mobile, String ipAddress, String feature){
		GenericSuccessFailureResponse response = new GenericSuccessFailureResponse();
		response.setSuccess(false);
		
		Tracking tracking = new Tracking();
		tracking.setAccountantid(accountantid);
		tracking.setBusinessid(businessid);
		tracking.setEmail(email);
		tracking.setMobile(mobile);
		tracking.setTrackdate(new java.sql.Date(new java.util.Date().getTime()));
		tracking.setIpaddress(ipAddress);
		tracking.setFeature(feature);
		
		try{
			trackingDao.save(tracking);
		}catch(Exception e){
		   e.printStackTrace();
		   response.setSuccess(false);
		}
		
		response.setSuccess(true);
		return response;
	}

	public TrackingDao getTrackingDao() {
		return trackingDao;
	}

	public void setTrackingDao(TrackingDao trackingDao) {
		this.trackingDao = trackingDao;
	}
}
