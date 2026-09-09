package com.akranta.tpm.controller;

import jakarta.servlet.http.HttpServletRequest;  
//import javax.servlet.http.HttpServletResponse;  
 
import jakarta.servlet.http.HttpServletRequestWrapper;  

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
 
public class RequestWrapper extends HttpServletRequestWrapper  
{  
 
	public RequestWrapper(HttpServletRequest request)  
	{  
		super(request);  
	}  
	public String getParameter(String arg0)  
	{  
		String value = super.getParameter(arg0);
  
		String result = value;  
		if( result == null )
			return null;
		
		result = result.trim();  
		result =  result.replaceAll("'", "`");
		return result;  
	}  
	public String[] getParameterValues(String parameter)  
	{  
	  String[] results = super.getParameterValues(parameter);  
	    
	  if (results == null)  
	  {  
		  return null;  
	  }  
	    
	  int count = results.length;  
	 
	  String[] trimResults = new String[count];  
	    
	  for (int i=0; i<count; i++)   
	  { 
	    trimResults[i] = results[i].trim();  
	    trimResults[i] = trimResults[i].replace("'", "`");
	  }  
	    
	  return trimResults;  
	}  
  

}
