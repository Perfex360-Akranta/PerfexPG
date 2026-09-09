package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AdmTlUsersessions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.UserLoginDetailsBean;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UserCreationServiceApi {
private final Api api;
	
	public UserCreationServiceApi(String JwtToken) 
	{
		CommonMessage.debugMsg("JWT Token for User "+JwtToken);
        this.api = new Api(JwtToken);
    }
	
	public AdmTlUsermst saveUser(AdmTlUsermst usermst) throws Exception 
	{
		

        String apiUrl = "/saveuser"; 
        
    
        String payload = usermst.toJsonManual();
        
        
        
        CommonMessage.debugMsg("pay laod "+payload);
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", payload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	    AdmTlUsermst result = AdmTlUsermst.fromJson(jsonResponse);
	        return result;
}
	
	public String insertJson(AdmTlUsermst usermst) 
	{
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append("\"genTlMommst\":");
		String JsonMst = usermst.toJsonManual();
		str.append(JsonMst);
		str.append("}");
		return str.toString();
	}
	
	
	public List<String[]> findSingleRole(String keyId) throws IOException
	{
		CommonMessage.debugMsg("keyid "+keyId);
		
		String apiUrl = "/saveuser/singleRole"
		        + (keyId != null
		            ? "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
		            : "");

		 // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	
	public void deleteRole(String roleId,String userId) throws IOException
	{
		CommonMessage.debugMsg("keyid "+userId);
		
		String apiUrl = "/saveuser/deleteRole"
		        + (roleId != null
		            ? "?roleId=" + URLEncoder.encode(roleId, StandardCharsets.UTF_8)
		            : "")	
		        + (userId != null
	            ? "&userId=" + URLEncoder.encode(userId, StandardCharsets.UTF_8)
	            : "");

		 // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
    
//        int responseCode = res.getStatusCode();
	}
	
	
	public List<String[]> findMultipleRole(String keyId) throws IOException
	{
		CommonMessage.debugMsg("keyid "+keyId);
		
		String apiUrl = "/saveuser/multipleRole"
		        + (keyId != null
		            ? "?keyId=" + URLEncoder.encode(keyId, StandardCharsets.UTF_8)
		            : "");


		 // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       JSONArray jsonArray = new JSONArray(json);
       List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
       return result;
       
//        int responseCode = res.getStatusCode();
	}
	
	public AdmTlUserRoleLink saveRole(AdmTlUserRoleLink userRoleLink) throws Exception 
	{
		

        String apiUrl = "/saveuser/saveRole"; 
        
    
        String payload = userRoleLink.toJsonManual();
        
        
        
        CommonMessage.debugMsg("pay laod "+payload);
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", payload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        AdmTlUserRoleLink result = AdmTlUserRoleLink.fromJson(jsonResponse);
	        return result;
}
	
	public AdmTlUsermst userRecall(String userKeyId) throws Exception 
	{
		

        String apiUrl = "/saveuser/userRecall"
        		+ (userKeyId != null
	            ? "?keyId=" + URLEncoder.encode(userKeyId, StandardCharsets.UTF_8)
	            : "");; 
        
    
        
        
        
        
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET", null);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	    AdmTlUsermst result = AdmTlUsermst.fromJson(jsonResponse);
	        return result;
}

	
	 public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
		    List<String[]> list = new ArrayList<>();

		    if (jsonArray.length() < 0) {
		        throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
		    }

		    // Row 3 = column order mapping
		    JSONObject orderObj = jsonArray.getJSONObject(0);

		    // Build ordered list of keys based on orderObj values
		    List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
		    Iterator<String> it = orderObj.keys();
		    while (it.hasNext()) {
		        String key = it.next();
		        try {
		            int order = Integer.parseInt(orderObj.getString(key));
		            orderList.add(new AbstractMap.SimpleEntry<>(key, order));
		        } catch (NumberFormatException e) {
		            orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
		        }
		    }

		    // Sort keys by numeric order
		    orderList.sort(Comparator.comparingInt(Map.Entry::getValue));

		    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
		    for (int i = 0; i < jsonArray.length(); i++) {
		    	if( i == 0) {
		    		continue;
		    	}
		        JSONObject obj = jsonArray.getJSONObject(i);
		        String[] row = new String[orderList.size()];

		        int colIndex = 0;
		        for (Map.Entry<String, Integer> entry : orderList) {
		            String key = entry.getKey();
		            Object value = obj.opt(key);
		            row[colIndex++] = value != null ? value.toString() : "";
		        }

		        list.add(row);
		    }

		    // Debug
		    for (String[] row : list) {
		        CommonMessage.debugMsg(Arrays.toString(row));
		    }

		    return list;
		}
	 
	 public AdmTlUserRoleLink updateRole(AdmTlUserRoleLink userRoleLink) throws Exception 
		{
			

	        String apiUrl = "/saveuser/updateRole"; 
	        
	    
	        String payload = userRoleLink.toJsonManual();
	        
	        
	        
	        CommonMessage.debugMsg("pay laod "+payload);
	        // Send request
	        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", payload);

	        // Get the response code to confirm insert success, usually 201 Created
	        CommonMessage.debugMsg(res.getStatusCode());
	        
	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        AdmTlUserRoleLink result = AdmTlUserRoleLink.fromJson(jsonResponse);
		        return result;
	}
	 
	 

}
