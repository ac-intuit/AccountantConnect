function validateFieldsOnSignUpForm(){

	var accountantEmail = $("#accountant_email").val();
	
	var atpos=accountantEmail.indexOf("@");
	var dotpos=accountantEmail.lastIndexOf(".");

	var pattern = new RegExp("/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/");
	if(atpos<1 || dotpos<atpos+2 || dotpos+2>=accountantEmail.length){
		$("#infoEmailTextMsg").hide();
		$("#accountantEmailInvalidFormat").show();
		$("#accountant_email").focus();
		$("#accountant_email_div").addClass("has-error");
		return false;
	}

	else {
		$("#accountantEmailInvalidFormat").hide();
		$("#accountant_email_div").removeClass("has-error");
		return true;
	}

}






