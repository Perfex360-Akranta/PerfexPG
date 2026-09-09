package com.akranta.tpm.dao.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import com.akranta.tpm.utils.CommonMessage;

public class CommonFunctions {
	
	
	private static final SimpleDateFormat timestampFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss",Locale.ENGLISH);
	
	
	public static String convertSqlTimeStampToString(java.sql.Timestamp timestamp)
	{
		if( timestamp != null )
			return timestampFormat.format((java.util.Date) timestamp);
		return null;
	}
	
	//kiran related masters
	 
		 public static java.sql.Timestamp PGconvertoSqlTimeStamp(String dateStr ){
				//String oldFormat = "dd-MMM-yyyy HH:mm:ss";
			    String oldFormat = "yyyy-mm-dd HH:mm:ss";

			    SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat,Locale.ENGLISH);
			    //SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


			    try {
			    	
			        return new java.sql.Timestamp( sdf1.parse(dateStr).getTime());
			        
			    } catch (ParseException e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			    }
			    return null;
			}
		 
	public static java.util.Date convertToUtilDate(String date, String dateFormat)
	{
		//The format of the input date string
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
		CommonMessage.debugMsg("Date format " + dateFormat);
		
		java.util.Date dd = null;
		try {
			dd = sdf.parse(date);
			CommonMessage.debugMsg("utildate " + dd);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return dd;
	}
	
	public static String convertToDisplayFormat(String sqlDate,String dateFormat)
	{
		String monthArray[ ] = { "Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
		//String monthArray[ ] = { "01","02","03","04","05","06","07","08","09","10","11","12"};
		StringBuffer oracleDate = new StringBuffer(""); 
		Calendar cal=Calendar.getInstance();
	    
		Date  date = convertToUtilDate(sqlDate,dateFormat);
		cal.setTime( date);
		
		oracleDate.append(cal.get(Calendar.DAY_OF_MONTH ));
		oracleDate.append("-");
		oracleDate.append(monthArray[cal.get(Calendar.MONTH  )] );
		oracleDate.append("-");
		oracleDate.append( cal.get(Calendar.YEAR) );
	
	    return oracleDate.toString();
	 }


	public static java.sql.Timestamp convertoSqlTimeStamp(String dateStr ){
		String oldFormat = "dd-MMM-yyyy HH:mm:ss";
	    //String newFormat = "yyyy/MM/dd HH:mm:ss";

	    SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat,Locale.ENGLISH);
	    //SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


	    try {
	    	
	        return new java.sql.Timestamp( sdf1.parse(dateStr).getTime());
	        
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return null;
	}
	public static java.sql.Timestamp convertoSqlTimeStampfromPgtimestamp(String dateStr ){
		String oldFormat = "yyyy-MM-dd'T'HH:mm:ss";
	    //String newFormat = "yyyy/MM/dd HH:mm:ss";

	    SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat,Locale.ENGLISH);
	    //SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);


	    try {
	    	
	        return new java.sql.Timestamp( sdf1.parse(dateStr).getTime());
	        
	    } catch (ParseException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public static java.sql.Date convertoSqlDate(String dateStr ) throws ParseException{
		String oldFormat = "dd-MMM-yyyy";
	    //String newFormat = "yyyy/MM/dd HH:mm:ss";

		//new java.sql.Date(Integer.parseInt(startDate.substring(6, 10))- 1900, Integer.parseInt(startDate.substring(3, 5))-1,Integer.parseInt(startDate.substring(0, 2))); 
		
	    SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat,Locale.ENGLISH);
	   	    	
	    return new java.sql.Date( sdf1.parse(dateStr).getTime());
	        
	}
	
	public static java.sql.Date convertoSqlPwdDate(String dateStr ) throws ParseException{
		String oldFormat = "dd-mm-yyyy";
	    //String newFormat = "yyyy/MM/dd HH:mm:ss";

		//new java.sql.Date(Integer.parseInt(startDate.substring(6, 10))- 1900, Integer.parseInt(startDate.substring(3, 5))-1,Integer.parseInt(startDate.substring(0, 2))); 
		
	    SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat,Locale.ENGLISH);
	    	CommonMessage.debugMsg(dateStr +"Datesssssssssssss");

	    return new java.sql.Date( sdf1.parse(dateStr).getTime());
	        
	}

	public static boolean isFileExists(String fileNamePath){
		 File file = new File( fileNamePath);
		 
		 return file.exists();
		 
	 }
	

	 public static boolean isValidKeyId(String keyId)
 	 {
 		
 		if( keyId != null && ! keyId.isEmpty() && ! keyId.equals("{}") && ! keyId.equals("-") && ! keyId.toLowerCase().equals("null"))
 			return true;
 		return false;
 	 }

	 public static String getSeqnoLocationIdentifier(String elementId,String tableName){
		 	
		 	StringBuilder idetifier =new StringBuilder(tableName);
		 	if( elementId != null && elementId.length() > 10  ){
		 		idetifier.append( elementId.substring(11, 21)); /* location id starts from 11  */

		 	}
		 	return idetifier.toString();
	 }
}
