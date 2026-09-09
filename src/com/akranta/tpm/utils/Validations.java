package com.akranta.tpm.utils;
/* Author Prasanth  
 * Description: handles the server side validations
 * 
 */
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.ValidationExceptions;

public class Validations {
	/* @Object-object :- the class Object to validate 
	 * @xmlName       :- name of the xml file in com.akranta.tpm.validations,"This should not contain xml extension"
	 * @validateFor   :- the group of specific properties to validate.  
	 */
	private static final String const_dateFormat = "dd-MMM-yyyy";
	private static final String const_dateTimeFormat = "dd-MMM-yyyy HH:mm";
	private static final String CONST_LECD="LECD";
	private static final String CONST_GECD="GECD";
	private static final String CONST_GCD="GCD";
	private static final String CONST_LCD="LCD";
	private static final String CONST_TRUE="TRUE";
	private String LECD = null, GECD=null, LCD = null, GCD=null;
	
	
	public  void validate(Object object,String xmlName,String validateFor) throws ParserConfigurationException, SAXException, IOException,
	SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ValidationExceptions
	{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		
		//Document doc = docBuilder.parse (new File("com.akranta.tpm.validations."+xmlName+".xml"));
		InputStream is = this.getClass().getResourceAsStream("/com/akranta/tpm/validations/"+xmlName+".xml");
		Document doc = docBuilder.parse(new InputSource(is));
		doc.getDocumentElement().normalize ();
		
		StringBuffer returnStr = new StringBuffer();  
		Class objClass =  object.getClass();
		String className = objClass.getName(); 
		NodeList listOfClass = doc.getElementsByTagName("class");
		for(int i = 0; i< listOfClass.getLength(); i++)
		{
			Node nNode = listOfClass.item(i);
			Element classElement = (Element)listOfClass.item(i);
			String vClassName = classElement.getAttribute("name");// class name declared in xml file
			if( vClassName != null && className.equals(vClassName) ){ // matching current class name with xml class name
				if( nNode.hasChildNodes()){
					CommonMessage.debugMsg("vClassName:"+vClassName);
					NodeList cNodeList = nNode.getChildNodes();
					
					for(int j = 0;j<cNodeList.getLength();j++)
					{
					   Node tmpNode = cNodeList.item(j);
					   if( tmpNode.hasAttributes() && tmpNode.hasChildNodes() ) 
					   {
						   Element validateElement = (Element)cNodeList.item(j);
						   String validatefor = validateElement.getAttribute("For");
						   if( validatefor != null && validatefor.indexOf(validateFor) >= 0 ){
							   NodeList valideateNodeList = tmpNode.getChildNodes();
							   
							   for( int k = 0;k<valideateNodeList.getLength();k++){
								   Node propertyNode = valideateNodeList.item(k) ;
								   
					    		   if( propertyNode instanceof Element && propertyNode.hasAttributes() ){
					        		   Element element = (Element)valideateNodeList.item(k);
					        		   String property = element.getAttribute("name");
					        		   String requiredAtLeastOne  = element.getAttribute("required-AtLeastOne");
					        		   String requiredDependend = element.getAttribute("required-dependent");
					        		   
					        		   if( requiredAtLeastOne != null && requiredAtLeastOne.equals("true") ){
					        			   
					        			   if( ! checkForRequiredAtLeastOne (object,propertyNode)){
					        				   String pName =  element.getAttribute("name");
					        				   returnStr.append( pName +"-required-AtLeastOne,");
					        			   }	   
					        		   }else if( requiredDependend != null && requiredDependend.equals("true") ){
					        			   String pName =  element.getAttribute("name");
					        			   String depValue = getPropertyValue(object,pName) ;
					        			   String value = element.getAttribute("value");
					        			   if( value != null && value.equals(depValue) )
					        				   returnStr.append(checkForDependent(object,propertyNode));
					        		   }else if( property != null){
					        			   
					        			   returnStr.append(validateProperty(object,property, element) );
					        		   }
					        		   
					    		   }
							   }
						   }	   
					   }	   
					}
				}
			}   
		}
		if( returnStr != null && returnStr.length() > 0 )
		throw new ValidationExceptions(returnStr.toString());
	
	}

	private  String getPropertyValue(Object object,String propertyName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		   Class cls  = object.getClass();
		   String methodeName = "get"+propertyName;
		   Method  method = cls.getDeclaredMethod(methodeName,  null) ;
		   
		   String value = (String) method.invoke(object, null) ;
		   return value;
	}
	
	private  String validateProperty(Object object,String property, Element element) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException
	{
		
		String required = element.getAttribute("required");
		String minLengthStr = element.getAttribute("min-length");
		String maxLengthStr = element.getAttribute("max-length");
		String type = element.getAttribute("type");
		this.LECD = element.getAttribute(CONST_LECD); // check for Less than or Equal to Current Date(LECD)
		this.GECD = element.getAttribute(CONST_GECD); // check for Greater than or Equal to Current Date(GECD)
		this.LCD = element.getAttribute(CONST_LCD); // check for Less than Current Date(LCD)
		this.GCD = element.getAttribute(CONST_GCD); // check for Greater than Current Date(GCD)
		String LE = element.getAttribute("LE"); // check for Less than or Equal to (LE) given Property (Date)
		String GE = element.getAttribute("GE"); // check for greater than or Equal to (GE) given Property (Date)
		String EQUAL = element.getAttribute("EQUAL"); // check for greater than or Equal to (GE) given Property (Date)
		String REGEXP = element.getAttribute("REGEXP");// match string with the regular expression
		
		String value = getPropertyValue(object,property);
		String checkDate="";
		String tmpdatefomat ="";
		
		if( type.equals("Date") ){
    		tmpdatefomat = const_dateFormat;
    	}	
    	else if(type.equals("DateTime")) {
    		tmpdatefomat = const_dateTimeFormat;
    	}
    	else if( type.equals("Number") )
    		tmpdatefomat= "NUMBER";
		
	    //if( (value == null || value.trim().isEmpty()) && required != null && required.equals("true"))
		if( ! isValidKeyId(value) && required != null && required.equals("true"))
	    {
	    	return property+"-required,";
	    }
	    else if( (value == null && minLengthStr != null && ! minLengthStr.isEmpty() && Integer.parseInt(minLengthStr) > 0)  ||
	    		 (value != null && minLengthStr != null && ! minLengthStr.isEmpty() && value.length() < Integer.parseInt(minLengthStr)))
	    {
	    	//CommonMessage.debugMsg(" min ");
	    	return property+"-min-length,";
	    }
	    else if( (value == null && maxLengthStr != null && ! maxLengthStr.isEmpty() && Integer.parseInt(maxLengthStr) > 0)  ||
	    		 (value != null && maxLengthStr != null && ! maxLengthStr.isEmpty() && value.length() > Integer.parseInt(maxLengthStr)))
	    {
	    	return property+"-max-length,";
	    }
	    else if( value != null && ( type.equals("Date") ) )// Less than or Equal to Current Date
		{
	    	 checkDate  = checkDateWithCurrentDate(property,value,tmpdatefomat);
		}
	    if( (checkDate == null || checkDate.length() <=0) && ( type.equals("Date") || type.equals("DateTime")|| type.equals("Number")) ){
		    if( value != null  &&  LE != null && ! LE.isEmpty() )// Less than or Equal
			{
		    	String value2 = getPropertyValue(object,LE);
		    	return checkForLE(property,value,value2,tmpdatefomat);
			}
		    else if( value != null &&  GE != null && ! GE.isEmpty()  )
			{
		    	String value2 = getPropertyValue(object,GE); // Greater than or equal
		    	return checkForGE(property,value,value2,tmpdatefomat);
			}
		    else if( value != null &&  GE != null && ! EQUAL.isEmpty()  )
			{
		    	String value2 = getPropertyValue(object,EQUAL); // Equal
		    	return checkForEQUL(property,value,value2,tmpdatefomat);
			}
		   /* else if( value != null  &&  LE != null && ! LE.isEmpty() )
			{
		    	String value2 = getPropertyValue(object,LE);
		    	return checkForLE(property,value,value2,tmpdatefomat);
			}
		    else if( value != null &&  GE != null && ! GE.isEmpty()  )
			{
		    	String value2 = getPropertyValue(object,GE);
		    	return checkForGE(property,value,value2,tmpdatefomat);
			}
		    else if( value != null  &&  GE != null && ! EQUAL.isEmpty()  )
			{
		    	String value2 = getPropertyValue(object,EQUAL);
		    	
		    	return checkForEQUL(property,value,value2,tmpdatefomat);
			}
			*/
	    }
	    else if( value != null &&  REGEXP != null && ! REGEXP.isEmpty() ){
	    	if( ! value.matches(REGEXP) ){
	    		return property+"-REGEXP,";
	    	}
	    }
	    else
	    	return checkDate;
	    return "";
	}
		
	private  String checkDateWithCurrentDate(String propertyName, String value, String dateFormat){
		
		try {
			//String dateFormat = "dd-MMM-yyyy"; 
			SimpleDateFormat df = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
			Date dateReq = df.parse(value);
			
			Date dateCurr = df.parse(CommonFunctions.dateTimeNow());
			CommonMessage.debugMsg(" dateCurr " + dateCurr);
			if( isValidKeyId(this.LECD) &&  CONST_TRUE.equalsIgnoreCase(this.LECD) )
				return dateReq.after(dateCurr) ? propertyName+"-"+CONST_LECD +",":"";
			else if( isValidKeyId(this.GECD) && CONST_TRUE.equalsIgnoreCase(this.GECD))
				return dateReq.before(dateCurr) ? propertyName+"-"+CONST_GECD +",":"";
			else if( isValidKeyId(this.LCD) && CONST_TRUE.equalsIgnoreCase(this.LCD))
				return dateReq.after(dateCurr) ? "" :propertyName+"-"+CONST_LCD +",";
			else if( isValidKeyId(this.GCD) && CONST_TRUE.equalsIgnoreCase(this.GCD))
				return dateReq.before(dateCurr) ? "": propertyName+"-"+CONST_GCD + ",";
			
		} catch (ParseException e) {
			CommonMessage.debugMsg(" e " + e.getMessage());
			//e.printStackTrace();
		}
		return "";
	}
	
	private  String checkForEQUL(String propertyName, String value1,String value2,String dateFormat){
		
		try {
			//String dateFormat = "dd-MMM-yyyy"; // for example
			if( ! "NUMBER".equals(dateFormat) ){
				SimpleDateFormat df = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
				Date dateReq = df.parse(value1);
				//CommonMessage.debugMsg(" LE dateReq" + dateReq);
				Date dateCurr = df.parse(value2);
				if(! dateReq.equals(dateCurr)){
					//CommonMessage.debugMsg( propertyName+ "-LE " );
					return propertyName+"-EQUAL,";
				}
			}	
			
		} catch (ParseException e) {
			//CommonMessage.debugMsg(" e " + e.getMessage());
			//e.printStackTrace();
		}
		return "";
	}
	
	private  String checkForLE(String propertyName, String value1,String value2,String dateFormat){
		
		try {
			if(value2 == null)
				return "";
			//String dateFormat = "dd-MMM-yyyy"; // for example
			if( ! "NUMBER".equals(dateFormat) )
			{	SimpleDateFormat df = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
				Date dateReq = df.parse(value1);
				//CommonMessage.debugMsg(" LE dateReq" + dateReq);
				Date dateCurr = df.parse(value2);
				if( dateReq.before(dateCurr)){
					//CommonMessage.debugMsg( propertyName+ "-LE " );
					return propertyName+"-LE,";
				}
			}
			else{
				if( !( isDouble(value1 ) && isDouble(value2) && ( Double.parseDouble(value1) <=  Double.parseDouble(value2) ) ) )
					return propertyName+"-LE,";
					
			}
			
		} catch (ParseException e) {
			//CommonMessage.debugMsg(" e " + e.getMessage());
			//e.printStackTrace();
		}
		return "";
	}
	boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

	private  String checkForGE(String propertyName, String value1,String value2,String dateFormat){
		
		try {
			if( value2 == null )
				return "";
			//String dateFormat = "dd-MMM-yyyy"; // for example
			if( ! "NUMBER".equals(dateFormat) )
			{
				SimpleDateFormat df = new SimpleDateFormat(dateFormat,Locale.ENGLISH);
				Date dateReq = df.parse(value1);
			
				Date dateCurr = df.parse(value2);
				if( dateReq.after(dateCurr))
					return propertyName+"-GE,";
			}
			else{
				if( !( isDouble(value1 ) && isDouble(value2) && ( Double.parseDouble(value1) >=  Double.parseDouble(value2) ) ) )
					return propertyName+"-GE,";
					
			}

			
		} catch (ParseException e) {
			//CommonMessage.debugMsg(" e " + e.getMessage());
			//e.printStackTrace();
		}
		return "";
	}
	
	private boolean checkForRequiredAtLeastOne(Object object ,Node propertyNode){
		

		if( propertyNode.hasChildNodes() ){
			
			
			NodeList nodeList = propertyNode.getChildNodes();
			for(int i = 0; i< nodeList.getLength(); i++)
			{
			   Node fieldNode = nodeList.item(i) ;
				   
	 		   if( fieldNode instanceof Element && fieldNode.hasAttributes() ){
	 			   
	     		   Element element = (Element)nodeList.item(i);
	     		   String property = element.getAttribute("name");
	     		   String type = element.getAttribute("type");
	     		   String rqValue = element.getAttribute("required-value");
	     		   String operation = element.getAttribute("operation");
	     		   String objectValue =null;
	     		   try {
	     			   
	     			   objectValue = getPropertyValue(object,property);
	     			   
					} catch (SecurityException e) {
						
					} catch (IllegalArgumentException e) {
						
					} catch (NoSuchMethodException e) {
						
					} catch (IllegalAccessException e) {
						
					} catch (InvocationTargetException e) {
						
					}
					if( objectValue != null  && ! objectValue.isEmpty() )
						return true;
	 		   }   
	 		}
			
		}
		return false;
	}
	
	private String checkForDependent(Object object ,Node propertyNode) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
	
		StringBuffer dependentMsg = new StringBuffer();

		if( propertyNode.hasChildNodes() ){
			
			
			NodeList nodeList = propertyNode.getChildNodes();
			for(int i = 0; i< nodeList.getLength(); i++)
			{
			   Node fieldNode = nodeList.item(i) ;
				   
	 		   if( fieldNode instanceof Element && fieldNode.hasAttributes() ){
	 			   
	     		   Element element = (Element)nodeList.item(i);
	     		   String property = element.getAttribute("name");
	     		  dependentMsg.append(validateProperty(object,property, element));
	 		   }   
	 		}
			
		}
		return dependentMsg.toString();
	}


	public static boolean isValidKeyId(String keyId)
	{
 		if( keyId != null && ! keyId.isEmpty() && ! keyId.equals("{}") && ! keyId.equals("-") && ! keyId.toLowerCase().equals("null"))
 			return true;
 		return false;
 	}
}
