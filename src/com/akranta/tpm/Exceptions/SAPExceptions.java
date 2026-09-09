package com.akranta.tpm.Exceptions;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class SAPExceptions extends Exception {
	public static final int ERROR_CODE_NOAUTHENTICATION_FOUND = -1;
	public static final int ERROR_CODE_NOMAPPING_FOUND = -2;
	
	public SAPExceptions(int errorCode,Throwable cause){
		super(getErrorMsg(errorCode),cause);
		cause.printStackTrace();
	}
	public SAPExceptions(int errorCode,String msg, Throwable cause){
		
		super(getErrorMsg(errorCode)+":"+msg,cause);
		CommonMessage.debugMsg(" Excep " + errorCode + " -- " + msg );
		cause.printStackTrace();
	}
	
	private static String getErrorMsg(int errorCode){
		String errorMsg = "Un Known Error";
		switch(errorCode){
			case ERROR_CODE_NOAUTHENTICATION_FOUND :
				errorMsg = "No SAP Connection information Found";
				
		}
		return errorMsg;
	}
}
