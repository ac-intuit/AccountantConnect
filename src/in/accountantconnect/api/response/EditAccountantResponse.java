package in.accountantconnect.api.response;

import java.util.List;

import in.accountantconnect.dao.AccountantProfileDao;
import in.accountantconnect.domain.AccountantProfile;

public class EditAccountantResponse extends GenericSuccessFailureResponse{
	private int generatedAccountantId = -1;
	private int generatedProfileId = -1;
	private int generatedReviewId=-1;
	private Object accountantProfile=null;

	public int getGeneratedAccountantId() {
		return generatedAccountantId;
	}

	public void setGeneratedAccountantId(int generatedAccountantId) {
		this.generatedAccountantId = generatedAccountantId;
	}
	
	public int getGeneratedProfileId() {
		return generatedProfileId;
	}

	public void setGeneratedProfileId(int generatedProfileId) {
		this.generatedProfileId = generatedProfileId;
	}

	public void setAccountantDetails(AccountantProfile accountProfile)
			 {
		// TODO Auto-generated method stub
		this.accountantProfile=accountProfile;
	}
	
	public Object getAccountantDetails() {
		return accountantProfile;
	}
	
	public int getGeneratedReviewId() {
		return generatedReviewId;
	}

	 
	
	
	
}