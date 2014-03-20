package in.accountantconnect.service.accountant;

import org.springframework.transaction.annotation.Transactional;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.common.CommonException;
import in.accountantconnect.common.MessageCollection;
import in.accountantconnect.dao.AccountantDao;
import in.accountantconnect.dao.AccountantProfileDao;
import in.accountantconnect.dao.AccountantReviewDao;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.domain.AccountantProfile;
import in.accountantconnect.domain.AccountantReview;
import in.accountantconnect.domain.AccountantViewProfile;
import in.accountantconnect.util.CollectionOfUtilityMethods;

public class EditAccountantService {
	private AccountantDao accountantDao;
	private AccountantProfileDao accountantProfileDao;
	private AccountantReviewDao accountantReviewDao;

	@Transactional
	public EditAccountantResponse createAccountantWithMinimumParams(String fullName, String email, String phone, Integer isHidden){
		EditAccountantResponse response = new EditAccountantResponse();
		response.setSuccess(false);

		Accountant accountant = new Accountant();
		String[] firstNameAndLastNameArray = CollectionOfUtilityMethods.getFirstNameAndLastNameArrayFromFullName(fullName);

		accountant.setFirstName(firstNameAndLastNameArray[0]);
		accountant.setLastName(firstNameAndLastNameArray[1]);
		accountant.setEmail(email);
		accountant.setMobile(phone);
		accountant.setJoindate(new java.sql.Date(new java.util.Date().getTime()));
		accountant.setIsProfileCreated(0);
		accountant.setIsHidden(isHidden);

		Integer accountantId = -1;
		try {
			accountantId = (Integer)accountantDao.save(accountant);
		} catch (CommonException e) {
			e.printStackTrace();
			response.setMessage(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return response;
		}
		//If accountant is added.
		response.setGeneratedAccountantId(accountantId);
		response.setSuccess(true);

		return response;
	}

	public AccountantDao getAccountantDao() {
		return accountantDao;
	}

	public void setAccountantDao(AccountantDao accountantDao) {
		this.accountantDao = accountantDao;
	}

	@Transactional
	public EditAccountantResponse createAccountantProfile(int accountantId,String imgLink,String fullName, String email, String phone, String companyName, String companyAddress, String city, String speciality,String shortDesc, String facebookId, String linkedinId, String twitterId,String clients, String education, String experience){
		EditAccountantResponse response = new EditAccountantResponse();
		 
		response.setSuccess(false);

		AccountantProfile accountantProfile =new AccountantProfile();

		String[] firstNameAndLastNameArray = CollectionOfUtilityMethods.getFirstNameAndLastNameArrayFromFullName(fullName);
		System.out.println(fullName);
		System.out.println(firstNameAndLastNameArray);
		accountantProfile.setFirstName(firstNameAndLastNameArray[0]);
		accountantProfile.setLastName(firstNameAndLastNameArray[1]);
		accountantProfile.setImgLink(imgLink);
		accountantProfile.setEmail(email);
		accountantProfile.setMobile(phone);
		accountantProfile.setCompanyName(companyName);
		accountantProfile.setCompanyAddress(companyAddress);
		accountantProfile.setAccountantid(accountantId);
		accountantProfile.setCity(city);
		accountantProfile.setShortDesc(shortDesc);
		accountantProfile.setSpeciality(speciality);
		accountantProfile.setFacebookid(facebookId);
		accountantProfile.setLinkedinid(linkedinId);
		accountantProfile.setTwitterid(twitterId);
		accountantProfile.setClients(clients);
		accountantProfile.setEducation(education);
		accountantProfile.setExperience(experience);
	
		Integer profileId = -1;
		try {
			profileId = (Integer)accountantProfileDao.save(accountantProfile);
		} catch (CommonException e) {
			e.printStackTrace();
			response.setMessage(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return response;
		}
		//If accountantprofile is created.
		response.setGeneratedProfileId(profileId);
		response.setSuccess(true);

		return response;
	}

	public AccountantProfileDao getAccountantProfileDao() {
		return accountantProfileDao;
	}

	public void setAccountantProfileDao(AccountantProfileDao accountantProfileDao) {
		this.accountantProfileDao = accountantProfileDao;
	}
	
	
    @Transactional
	public EditAccountantResponse viewAccountantProfile(String email, int profileId) {
		// TODO Auto-generated method stub
    	AccountantProfile accountantProfile=null;
		EditAccountantResponse response = new EditAccountantResponse();

		AccountantViewProfile acViewProfile =new AccountantViewProfile();
		acViewProfile.setEmail(email);
		acViewProfile.setAccountantId(profileId);
		
		try{	
			 accountantProfile=(AccountantProfile) accountantProfileDao.readById(profileId);
		}
		catch (CommonException e) {
			e.printStackTrace();
			response.setMessage(MessageCollection.ERROR_ENGINEERS_WILL_FIX_IT);
			return response;
		}
		response.setAccountantDetails(accountantProfile);
		response.setSuccess(true);
		return response;

	}
    
    
@Transactional
	public EditAccountantResponse writeReview(int profileId, String reviewDesc) {
		// TODO Auto-generated method stub
		
		
		EditAccountantResponse response = new EditAccountantResponse();
		response.setSuccess(false);

		AccountantReview accountantReview = new AccountantReview();
		 

		accountantReview.setProfileid(profileId);
		accountantReview.setReviewdesc(reviewDesc);
		 
		Integer reviewId = -1;
		try {
			profileId = (Integer)accountantReviewDao.save(accountantReview);
		} catch (CommonException e) {
			e.printStackTrace();
			response.setMessage(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return response;
		}
		//If accountantprofile is created.
		//response.setGeneratedReviewId(reviewId);
		response.setSuccess(true);

		return response;
	}

public AccountantReviewDao getAccountantReviewDao() {
	return accountantReviewDao;
}

public void setAccountantReviewDao(AccountantReviewDao accountantReviewDao) {
	this.accountantReviewDao = accountantReviewDao;
}





}




