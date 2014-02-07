package in.accountantconnect.service.accountant;

import org.springframework.transaction.annotation.Transactional;

import in.accountantconnect.api.response.EditAccountantResponse;
import in.accountantconnect.common.CommonException;
import in.accountantconnect.common.MessageCollection;
import in.accountantconnect.dao.AccountantDao;
import in.accountantconnect.domain.Accountant;
import in.accountantconnect.util.CollectionOfUtilityMethods;

public class EditAccountantService {
	private AccountantDao accountantDao;
	
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
}
