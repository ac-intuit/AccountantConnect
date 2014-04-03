package in.accountantconnect.service.accountant;

import org.springframework.transaction.annotation.Transactional;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.common.CommonException;
import in.accountantconnect.common.MessageCollection;
import in.accountantconnect.dao.AccountantDao;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.util.CollectionOfUtilityMethods;
import in.accountantconnect.util.EnumCollection;
import in.accountantconnect.util.EnumCollection.EventStatus;
import in.accountantconnect.web.AppResponse;

public class EditAccountantService {
	private AccountantDao accountantDao;
	
	@Transactional
	public EditAccountantResponse createAccountantWithMinimumParams(
			String fullName, 
			String email, 
			String phone, 
			Integer isHidden){
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
	
	@Transactional
	public AppResponse<Accountant> saveProfileWithBasicInfo(
			Integer id,
			String firstName,
			String lastName,			
			String email,
			String mobile,
			String businessPhone,
			String city,
			String area){
		Accountant accountant = null;
		AppResponse<Accountant> appResponse = new AppResponse<Accountant>();
		appResponse.setCode(EventStatus.failure.getValue());
		try {
			accountant	= (Accountant)accountantDao.readById(id);
		} catch (CommonException e) {
			e.printStackTrace();
		}
					
		accountant.setFirstName(firstName);
		accountant.setLastName(lastName);
		accountant.setEmail(email);
		accountant.setMobile(mobile);
		accountant.setBusinessPhone(businessPhone);
		accountant.setCity(city);
		accountant.setArea(area);
		
		try {
			accountantDao.update(accountant);
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(accountant);
			appResponse.setDescription(MessageCollection.PROFILE_SUCCESSFULLY_UPDATED);
		} catch (CommonException e) {
			e.printStackTrace();
			appResponse.setDescription(MessageCollection.INTERNAL_ERROR_WHILE_ADDING_ACCOUNTANT);
			return appResponse;
		}		
		
		return appResponse;
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
	public AppResponse<String> saveAccountantProfile(
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
		AppResponse<String> appResponse = new AppResponse<String>();
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
			appResponse.setCode(EventStatus.success.getValue());
			appResponse.setData(accountant);
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
