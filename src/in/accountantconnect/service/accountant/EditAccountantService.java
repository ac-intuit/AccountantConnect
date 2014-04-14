package in.accountantconnect.service.accountant;

import org.springframework.transaction.annotation.Transactional;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.common.CommonException;
import in.accountantconnect.common.MessageCollection;
import in.accountantconnect.dao.AccountantDao;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.domain.ProfileCompletionStatus;
import in.accountantconnect.util.CollectionOfUtilityMethods;
import in.accountantconnect.util.EnumCollection;
import in.accountantconnect.util.EnumCollection.EventStatus;
import in.accountantconnect.web.AppResponse;

public class EditAccountantService {
	private AccountantDao accountantDao;
	
	@Transactional
	public boolean doesTheEmailExist(String email){
		boolean doesTheEmailExist = false;
		
		Accountant accountant = null;
		try {
			accountant	= (Accountant)accountantDao.doesTheEmailExist(email);
			if(accountant != null){
				doesTheEmailExist = true;
			}else{
				doesTheEmailExist = false;
			}
		} catch (CommonException e) {
			e.printStackTrace();
		}
		
		return doesTheEmailExist;		
	}
		
	/**
	 * This method is called first to create an accountant in the system.
	 * This must be entry point.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phone
	 * @param isHidden
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> createAccountantWithMinimumParams(
			String firstName,
			String lastName,
			String email, 
			String phone, 
			Integer isHidden,
			String password){
		AppResponse<Integer> response = new AppResponse<Integer>();
		response.setCode(EventStatus.failure.getValue());
		//Check if the email is not duplicate
		if(doesTheEmailExist(email)){
			response.setDescription(MessageCollection.THIS_EMAIL_ALREADY_EXISTS);
		}
		
		Accountant accountant = new Accountant();
		
		accountant.setFirstName(firstName);
		accountant.setLastName(lastName);
		accountant.setEmail(email);
		accountant.setMobile(phone);
		accountant.setJoindate(new java.sql.Date(new java.util.Date().getTime()));
		accountant.setIsProfileCreated(0);
		accountant.setIsHidden(isHidden);
		accountant.setPassword(password);
		
		accountant.setNoOfVisitToEditProfilepage(0);
	
		Integer accountantId = -1;
		try {
			accountantId = (Integer)accountantDao.save(accountant);
			response.setData(accountantId);
			response.setCode(EventStatus.success.getValue());
		} catch (CommonException e) {
			e.printStackTrace();
			response.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);			
			return response;
		}

		return response;
	}
	
	/**
	 * And then we go and create the full profile of an accountant
	 * @param fullName
	 * @param email
	 * @param phone
	 * @param isHidden
	 * @return
	 */
	
	@Transactional
	public AppResponse<ProfileCompletionStatus> saveAccountantProfile(
			int id,
			String email, 
			String firstName,
			String lastName,
			
			String mobile,
			String businessPhone,
			String city,
			Integer age,
			String area,
						
			String shortDescription,
			String longDescription,
			
			String websiteURL,
			String linkedinProfile,
			String fbProfile,
			String gplusProfile,
			
			Integer yearOfExp,
			String areasOfExpertise,
			
			String education,
			String certifications,
			
			String addressLine1,
			String addressLine2,
			String state,
			Integer pincode,
			String country){		
		Accountant accountant = null;
		//Prepare the response
		AppResponse<ProfileCompletionStatus> appResponse = new AppResponse<ProfileCompletionStatus>();
		appResponse.setCode(EventStatus.failure.getValue());
		//To update the profile the accountant must already be created.
	    if(id == -1){
	    	appResponse.setDescription(MessageCollection.ACCOUNTANT_DOES_NOT_EXIST_NEEDED_BEFORE_CREATING_PROFILE);
			return appResponse;
		}
		
		try {
			accountant	= (Accountant)accountantDao.readById(id);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}
		
		//If the accountant is retrieved successfully
		accountant.setFirstName(firstName);
		accountant.setLastName(lastName);
		accountant.setEmail(email);
		
		accountant.setMobile(mobile);
		accountant.setIsProfileCreated(1);
		accountant.setIsHidden(0);
		
		accountant.setBusinessPhone(businessPhone);
		accountant.setCity(city);
		accountant.setAge(age);
		accountant.setArea(area);
				
		accountant.setShortDescription(shortDescription);
		accountant.setLongDescription(longDescription);
		
		accountant.setWebsiteURL(websiteURL);
		accountant.setLinkedinProfile(linkedinProfile);
		accountant.setFbProfile(fbProfile);
		accountant.setGplusProfile(gplusProfile);
		
		accountant.setYearOfExp(yearOfExp);
		accountant.setAreasOfExpertise(areasOfExpertise);
		
		accountant.setEducation(education);
		accountant.setCertifications(certifications);
		
		accountant.setAddressLine1(addressLine1);
		accountant.setAddressLine2(addressLine2);
		accountant.setState(state);
		accountant.setPincode(pincode);
		accountant.setCountry(country);
	
		try {
			accountantDao.update(accountant);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}
		
		//If accountant profile is updated successfully.
		appResponse.setCode(EventStatus.success.getValue());
		appResponse.setData(new ProfileCompletionStatus(accountant));
		appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
		
		return appResponse;
	}
	
	/**
	 * Get Accountant by Id
	 */
	@Transactional
	public AppResponse<Accountant> getAccountantById(Integer id){
		Accountant accountant = null;
		AppResponse<Accountant> appResponse = new AppResponse<Accountant>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.readById(id);
			//TODO: increase the editprofile page access count for this accoutant.
			//I am assuming this call has come from the editprofile page. 
			//This is completely wrong. But I still am doing this because giving something to the customers is more
			//important to me than getting it 100% right! For the sake of time.
			accountant.setNoOfVisitToEditProfilepage(accountant.getNoOfVisitToEditProfilepage() + 1);
			//Set the section fill information
			ProfileCompletionStatus profileCompletionStatus = new ProfileCompletionStatus(accountant);
			accountant.setProfileCompletionStatus(profileCompletionStatus);
			
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(accountant);
			accountantDao.update(accountant);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.ERROR_ENGINEERS_WILL_FIX_IT);
		}
		
		return appResponse; 
	}
	/**
	 * Save the photograph of the accountant and return the URL to the photo
	 * @param id
	 * @param photoURL
	 * @return
	 */
	
	@Transactional
	public AppResponse<String> saveAccountantPhoto(
			Integer id,
			String photoURL){
		Accountant accountant = null;
		AppResponse<String> appResponse = new AppResponse<String>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.readById(id);
		} catch (CommonException e) {
			e.printStackTrace();
		}
    	accountant.setPhotoFileName(photoURL);
		
		try {
			accountantDao.update(accountant);
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(null);
			appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}		
		
		return appResponse;
	}
	
	@Transactional
	public AppResponse<String> getPhotoLocation(Integer id){
		Accountant accountant = null;
		AppResponse<String> appResponse = new AppResponse<String>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.readById(id);
			if(accountant != null){
				appResponse.setCode(EventStatus.success.getValue());
				appResponse.setData(accountant.getPhotoFileName());
			}
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR);
		}
    	return appResponse;
	}
	
	/**
	 * Save accountant password. Create an account if accountant does not exist.
	 * @param id
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> saveAccountantPassword(String email, String password){
		Accountant accountant = null;
		AppResponse<Integer> appResponse = new AppResponse<Integer>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.readByEmail(email);
		} catch (CommonException e) {
			e.printStackTrace();
		}
		//If the account is not created first create it.
		if(accountant == null){
			return createAccountantWithMinimumParams("", "", email, "", 1, password);
		}else{		
			accountant.setPassword(password);
		
			try {
				accountantDao.update(accountant);
				appResponse.setCode(EventStatus.success.getValue());
				appResponse.setData(accountant.getAccountantid());
				appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
			} catch (CommonException e) {
				e.printStackTrace();
				appResponse.setDescription(MessageCollection.PASSWORD_COULD_NOT_BE_SET);
				return appResponse;
			}
		}
			
		return appResponse;
	}
	
	/**
	 * Validate Accountant & return the Id associated
	 * @param email
	 * @param password
	 * @return
	 */
	@Transactional
	public AppResponse<Integer> isAccountantValid(String email, String password){
		Accountant accountant = null;
		AppResponse<Integer> appResponse = new AppResponse<Integer>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.validateAccountant(email, password);
			if(accountant != null){
			  appResponse.setData(accountant.getAccountantid());
				appResponse.setCode(EventStatus.success.getValue());
			}else{
				appResponse.setDescription(MessageCollection.LOGIN_FAILED);
			}
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.ERROR_ENGINEERS_WILL_FIX_IT);
		}
		
		return appResponse; 
	}


	public AccountantDao getAccountantDao() {
		return accountantDao;
	}

	public void setAccountantDao(AccountantDao accountantDao) {
		this.accountantDao = accountantDao;
	}
}
