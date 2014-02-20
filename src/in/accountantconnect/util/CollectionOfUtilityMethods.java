package in.accountantconnect.util;

import javax.servlet.http.HttpServletRequest;

public class CollectionOfUtilityMethods {
    public static String getParamValIfNotNullElseGetDefaultVal(String paramName, String defaultValue, HttpServletRequest req){
    	String paramVal = req.getParameter(paramName);
    	if(paramVal == null || "".equals(paramVal.trim())){
    		paramVal = defaultValue;
    	}
    	return paramVal;
    }
    
    public static String[] getFirstNameAndLastNameArrayFromFullName(String fullName){
		String[] firstnameAndLastnameArr = fullName.split(" ");
		if(firstnameAndLastnameArr.length == 2){
			return firstnameAndLastnameArr;
		}else if(firstnameAndLastnameArr.length == 1){
			String firstName = firstnameAndLastnameArr[0];
			firstnameAndLastnameArr = new String[2];
			firstnameAndLastnameArr[0] = firstName; 
			firstnameAndLastnameArr[1] = "";
			return firstnameAndLastnameArr;
		}else{
		
			firstnameAndLastnameArr = new String[2];
		    firstnameAndLastnameArr[0] = "";
		    firstnameAndLastnameArr[1] = "";
		    return firstnameAndLastnameArr;
		}
    }
}
