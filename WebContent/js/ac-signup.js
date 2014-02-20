function validateFieldsOnSignUpForm(){
<<<<<<< HEAD
   var accountantEmail = $("#accountant_email").val();
   
  
   
   var atpos=accountantEmail.indexOf("@");
   var dotpos=accountantEmail.lastIndexOf(".");
   
   var pattern = new RegExp("/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/");
   if(atpos<1 || dotpos<atpos+2 || dotpos+2>=accountantEmail.length){
	   $("#infoTextMsg").hide();
	   $("#accountantEmailInvalidFormat").show();
	   $("#accountant_email").focus();
	   return false;
   }
   
   else {
	   $("#accountantEmailInvalidFormat").hide();
	   return true;
   }
  
  
   
    
	    
   }
   
  
 
	   
  
=======
   var accountantName = $("#accountant_name").val();
   if(accountantName == ""){
	   $("#accountantEmailNoDataErr").show();
	   return false;
   }
   return true;
}
>>>>>>> f855a2092b4e025ca298ff662374eedacfcc4bca
