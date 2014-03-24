package in.accountantconnect.api.response;

public class EditAccountantResponse extends GenericSuccessFailureResponse{
	private int generatedAccountantId = -1;

	public int getGeneratedAccountantId() {
		return generatedAccountantId;
	}

	public void setGeneratedAccountantId(int generatedAccountantId) {
		this.generatedAccountantId = generatedAccountantId;
	}
	
}