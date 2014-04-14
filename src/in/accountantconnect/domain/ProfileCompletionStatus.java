package in.accountantconnect.domain;

public class ProfileCompletionStatus {
    private boolean isBasicInfoSectionFilled = true;
	private boolean isPhotoUploaded = true;
	private boolean isDescriptionSectionFilled = true;
	private boolean isExperienceSectionFilled = true;
	private boolean isContactSectionFilled = true;
	//any one of the above false, meaning this one must be false;
	private boolean isAllMandatorySectionsFilled = true;

	public ProfileCompletionStatus(Accountant accountant){
		fillCompletionStatus(accountant);
	}
	
	public void fillCompletionStatus(Accountant accountant){
		if(accountant.getFirstName() == null || "".equals(accountant.getFirstName().trim())
				|| accountant.getLastName() == null || "".equals(accountant.getLastName().trim())
				|| accountant.getEmail() == null || "".equals(accountant.getEmail().trim())
				|| accountant.getMobile() == null || "".equals(accountant.getMobile().trim())
				|| accountant.getBusinessPhone() == null || "".equals(accountant.getBusinessPhone().trim())
				|| accountant.getCity() == null || "".equals(accountant.getCity().trim())
				|| accountant.getArea() == null || "".equals(accountant.getArea().trim())){
			isBasicInfoSectionFilled = false;
			isAllMandatorySectionsFilled = false;
		}
		
		if(accountant.getPhotoFileName() == null || "".equals(accountant.getPhotoFileName().trim())){
			isPhotoUploaded = false;
			isAllMandatorySectionsFilled = false;			
		}
		
		if(accountant.getShortDescription() == null || "".equals(accountant.getShortDescription().trim())
				|| accountant.getLongDescription() == null || "".equals(accountant.getLongDescription().trim())){
			isDescriptionSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
		
		if(accountant.getYearOfExp() == null || "".equals(accountant.getYearOfExp())
				|| accountant.getAreasOfExpertise() == null || "".equals(accountant.getAreasOfExpertise().trim())){
			isExperienceSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
		if(accountant.getAddressLine1() == null || "".equals(accountant.getAddressLine1().trim())
				|| accountant.getAddressLine2() == null || "".equals(accountant.getAddressLine2().trim())
				|| accountant.getCity() == null || "".equals(accountant.getCity().trim())
				|| accountant.getState() == null || "".equals(accountant.getState().trim())
				|| accountant.getPincode() == null || "".equals(accountant.getPincode())){
			isContactSectionFilled = false;
			isAllMandatorySectionsFilled = false;			
		}
	}
	
	public boolean isBasicInfoSectionFilled() {
		return isBasicInfoSectionFilled;
	}
	public void setBasicInfoSectionFilled(boolean isBasicInfoSectionFilled) {
		this.isBasicInfoSectionFilled = isBasicInfoSectionFilled;
	}
	public boolean isPhotoUploaded() {
		return isPhotoUploaded;
	}
	public void setPhotoUploaded(boolean isPhotoUploaded) {
		this.isPhotoUploaded = isPhotoUploaded;
	}
	public boolean isDescriptionSectionFilled() {
		return isDescriptionSectionFilled;
	}
	public void setDescriptionSectionFilled(boolean isDescriptionSectionFilled) {
		this.isDescriptionSectionFilled = isDescriptionSectionFilled;
	}
	public boolean isExperienceSectionFilled() {
		return isExperienceSectionFilled;
	}
	public void setExperienceSectionFilled(boolean isExperienceSectionFilled) {
		this.isExperienceSectionFilled = isExperienceSectionFilled;
	}
	public boolean isContactSectionFilled() {
		return isContactSectionFilled;
	}
	public void setContactSectionFilled(boolean isContactSectionFilled) {
		this.isContactSectionFilled = isContactSectionFilled;
	}

	public boolean isAllMandatorySectionsFilled() {
		return isAllMandatorySectionsFilled;
	}

	public void setAllMandatorySectionsFilled(boolean isAllMandatorySectionsFilled) {
		this.isAllMandatorySectionsFilled = isAllMandatorySectionsFilled;
	}
}
