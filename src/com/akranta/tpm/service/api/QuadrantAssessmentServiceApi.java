package com.akranta.tpm.service.api;

import com.akranta.tpm.model.EntTlTragcalquad;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.JhaTlAuditmst;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QuadrantAssessmentServiceApi {	
	 private final Api api;
	    
	    public QuadrantAssessmentServiceApi(String JwtToken) {
	        this.api = new Api(JwtToken);
	    }
	    
	    
//	    public EntTlTragcalquad insertRecord(String UpdateList,String userid) throws Exception {
//	 
//	        String apiUrl = "/Quadrant/updatelevel"; // The Spring Boot API endpoint for insert
//	      //  jhaTlAuditmst.setJhamActive("Y");
//	        EntTlTragcalquad entTlTragcalquad = new EntTlTragcalquad();
//	        
//	        String jsonPayload = insertJson(entTlTragcalquad);
//	        CommonMessage.debugMsg("Json :: " + jsonPayload);
//	        
//	        // Send request
//	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//	        
//	        // Get the response code to confirm insert success, usually 201 Created
//	        CommonMessage.debugMsg(res.getStatusCode());
//	        
//	        String jsonResponse = res.getBody();
//	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//	        
//	        EntTlTragcalquad quadrant = getJson(jsonResponse);
//	        CommonMessage.debugMsg(quadrant);
//	        
//	        return quadrant;
//	    }
	    
	    
	    public EntTlTragcalquad insertRecord(String updateList, String userid) throws Exception {

	        String apiUrl = "/Quadrant/updatelevel";

	        JSONObject json = new JSONObject();
	        json.put("updateList", updateList);
	        json.put("userid", userid);

	        String jsonPayload = json.toString();
	        CommonMessage.debugMsg("Json :: " + jsonPayload);

	        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	        CommonMessage.debugMsg(res.getStatusCode());

	        String jsonResponse = res.getBody();
	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse and validate response
	        JSONObject responseObj = JSONObject.fromObject(jsonResponse);
	        if (!responseObj.getBoolean("success")) {
	            throw new RuntimeException("Update failed: " + responseObj.getString("message"));
	        }

	        EntTlTragcalquad quadrant = getJson(jsonResponse);
	        return quadrant;
	    }

	    
	    
//	    public String insertJson(EntTlTragcalquad entTlTragcalquad) {
//	        StringBuilder str = new StringBuilder();
//	        
//	        str.append("{");
//	        
//	        // Master Details
//	        str.append("\"jhaTlAuditmst\":");
//	        String jsonMst = entTlTragcalquad.toJsonManual();
//	        str.append(jsonMst);
//	        
//	        str.append("}");
//	        
//	        return str.toString();
//	    }
	    
	    
//	    public EntTlTragcalquad getJson(String json) {
//	        JSONObject jsonObj = JSONObject.fromObject(json);
//	        JSONObject mstObj = jsonObj.getJSONObject("entTlTragcalquad");
//	        
//	        EntTlTragcalquad quadrant = EntTlTragcalquad.fromJson(mstObj.toString());
//	        
//	        return quadrant;
//	    }
	    
	    
	    public EntTlTragcalquad getJson(String json) {
	        JSONObject jsonObj = JSONObject.fromObject(json);
	        
	        // Check if the response was successful
	        if (!jsonObj.getBoolean("success")) {
	            throw new RuntimeException("API call failed: " + jsonObj.optString("message"));
	        }
	        
	        // Extract the data object (not "entTlTragcalquad")
	        JSONObject dataObj = jsonObj.getJSONObject("data");
	        
	        EntTlTragcalquad quadrant = EntTlTragcalquad.fromJson(dataObj.toString());
	        
	        return quadrant;
	    }

}
