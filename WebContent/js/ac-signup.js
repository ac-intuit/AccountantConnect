function validateFieldsOnSignUpForm(){
   var accountantName = $("#accountant_name").val();
   if(accountantName == ""){
	   $("#accountantEmailNoDataErr").show();
   }
   return false;
}