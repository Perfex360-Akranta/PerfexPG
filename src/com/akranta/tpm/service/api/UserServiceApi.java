package com.akranta.tpm.service.api;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AdmTlUsersessions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserServiceApi {
private final Api api;
	
	public UserServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
public String getUserLoginAttempt(String loginId) throws Exception {
		

        String apiUrl = "/auth/loginFailCount/"+loginId; 
        
        // Send request
        HttpResponse res = api.makeRequest(apiUrl,"GET", "");

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
     
        return jsonResponse;
}

public AdmTlUsermst getUserByLogin(String loginId) throws Exception {
	

    String apiUrl = "/auth/userByLogin/"+loginId; 
    
    // Send request
    HttpResponse res = api.makeRequest(apiUrl,"GET", "");

    // Get the response code to confirm insert success, usually 201 Created
    CommonMessage.debugMsg(res.getStatusCode());
    
    String jsonResponse = res.getBody();
    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
    if(jsonResponse != "" && !jsonResponse.isEmpty()) {
    AdmTlUsermst user = AdmTlUsermst.fromJson(jsonResponse);
 
    return user;
}throw new  NoDataFoundException("NO-DATA");
}

public String getEmployeeLocation(String code) throws Exception {
	

    String apiUrl = "/auth/location/"+code; 
    
    // Send request
    HttpResponse res = api.makeRequest(apiUrl,"GET", "");

    // Get the response code to confirm insert success, usually 201 Created
    CommonMessage.debugMsg(res.getStatusCode());
    
    String jsonResponse = res.getBody();
    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
 
    return jsonResponse;
}

public UserLoginDetailsBean getLoginUserDetails(String userKeyid) throws Exception {
	

    String apiUrl = "/auth/loginUserDetails/"+userKeyid; 
    
    // Send request
    HttpResponse res = api.makeRequest(apiUrl,"GET", "");

    // Get the response code to confirm insert success, usually 201 Created
    CommonMessage.debugMsg(res.getStatusCode());
    
    String jsonResponse = res.getBody();
    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
    UserLoginDetailsBean userLoginDetailsBean  = UserLoginDetailsBean.fromJson(jsonResponse);
    return userLoginDetailsBean;
}

public String getEmployeeIsActive(String loginId) throws Exception {
	

    String apiUrl = "/auth/isExpired/"+loginId; 
    
    // Send request
    HttpResponse res = api.makeRequest(apiUrl,"GET", "");

    // Get the response code to confirm insert success, usually 201 Created
    CommonMessage.debugMsg(res.getStatusCode());
    
    String jsonResponse = res.getBody();
    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
 
    return jsonResponse;
}

	public AdmTlUsersessions insertSession(AdmTlUsersessions admTlUsersessions) throws Exception {
		
		HttpResponse res = api.makeAuthRequest("/sessions/max/"+admTlUsersessions.getUsseUserid(),"GET","");

        CommonMessage.debugMsg("Max Val response Code :" + res.getStatusCode());
        String maxVal = res.getBody();
        maxVal = Integer.parseInt(maxVal) + 1 +"";
		admTlUsersessions.setUsseSessionno(maxVal); // set the sequnce number 
		CommonMessage.debugMsg(" maxVal " + maxVal);

        String apiUrl = "/sessions"; 
       
        // Convert object to JSON
        String jsonPayload = admTlUsersessions.toJsonManual();
        CommonMessage.debugMsg("Json :: "+jsonPayload);
        
        // Send request
        HttpResponse res1 = api.makeAuthRequest(apiUrl,"POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res1.getStatusCode());
//        int responseCode = res1.getStatusCode();
        
        String jsonResponse = res1.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        AdmTlUsersessions session = AdmTlUsersessions.fromJson(jsonResponse);
        return session;
}
	
	
public String getJwtToken(String KeyId) throws Exception {
		

        String apiUrl = "/auth/login/"+KeyId; 
        
        // Send request
        HttpResponse res = api.makeRequest(apiUrl,"GET", "");

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
     
        return jsonResponse;
}
	
	public String updateUserLoginTime(AdmTlUsermst admTlUsermst) throws Exception {
        String apiUrl = "/users/updateUserLoginTime";
		
		String jsonPayload = updateUserLoginTimeJson(admTlUsermst);
        CommonMessage.debugMsg("Json :: "+jsonPayload);
		
     // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"PUT", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        int responseCode = res.getStatusCode();
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        return jsonResponse;
}
	
	public String updateUserLoginTimeJson(AdmTlUsermst admTlUsermst) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyid\":");
	    sb.append("\"").append(admTlUsermst.getUsrm_keyid()).append("\"");
	    sb.append(",\"lastlogindate\":");
	    sb.append("\"").append(admTlUsermst.getUsrm_lastlogindate()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
}
