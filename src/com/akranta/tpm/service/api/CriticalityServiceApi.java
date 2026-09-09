package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class CriticalityServiceApi {
	private final Api api;
	
	public CriticalityServiceApi(String jwtToken) {
		this.api = new Api(jwtToken);
				
	}
	 
	// Original method with one parameter
	public List<BdmTlCriticalityassessment> saveBdmTlRequest(
			List<BdmTlCriticalityassessment> master) throws Exception {
		String apiUrl = "/bdmTlRequest/save";
		
		String jsonPayload = BuildJsonList(master);
		CommonMessage.debugMsg("BDM TL SAVE JSON :: " + jsonPayload);
		
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("Response Code :: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<BdmTlCriticalityassessment> bdm = BdmTlCriticalityassessment.fromJsonList(jsonResponse);
        
        return bdm;
	}
	
	// Overloaded method with two parameters to match the service impl call
	public List<BdmTlCriticalityassessment> saveBdmTlRequest(
			List<BdmTlCriticalityassessment> CrtieriaGridList1,
			List<BdmTlCriticalityassessment> existBdmTlCriticalityassessment) throws Exception {
		
		
		return saveBdmTlRequest(CrtieriaGridList1);
		
		
	}
	
	public String getFlid(String parentFlid) throws IOException {
	    // Allow null parentFlid
	    if (parentFlid == null) {
	        CommonMessage.debugMsg("Parent FLID is null, returning null");
	        return null;
	    }
	    
	    CommonMessage.debugMsg("Getting FLID for parent: " + parentFlid);
	    String apiUrl = "/bdmTlRequest/flid/" + URLEncoder.encode(parentFlid, StandardCharsets.UTF_8);

	    // Send GET request
	    HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

	    CommonMessage.debugMsg("Get FLID Response Status Code: " + res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("Get FLID JSON Response: " + jsonResponse);

	    // Parse response
	    JSONObject jsonObj = JSONObject.fromObject(jsonResponse);

	    // Check if there's an error message
	    if (jsonObj.has("message") && !jsonObj.has("flid")) {
	        String errorMessage = jsonObj.getString("message");
	        throw new IOException("Error getting FLID: " + errorMessage);
	    }

	    // Extract FLID from response - allow null/empty
	    String flid = jsonObj.optString("flid", null);

	    CommonMessage.debugMsg("Retrieved FLID: " + flid);

	    return flid;  // Can be null or empty now
	}
	public String getCriticalityAssessmentRemarks(String flid, String equipmentId) throws IOException {
        // Build URL with query parameters
        String apiUrl = "/bdmTlRequest/remarks?flid=" + 
                        URLEncoder.encode(flid, StandardCharsets.UTF_8) + 
                        "&equipmentId=" + 
                        URLEncoder.encode(equipmentId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Getting criticality assessment remarks for FLID: " + flid + 
                           " and Equipment ID: " + equipmentId);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Get Remarks Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("Get Remarks JSON Response: " + jsonResponse);
        
        // Parse response
        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
        
        // Check if there's an error message
        if (jsonObj.has("message") && 
            (!jsonObj.has("remarks") || jsonObj.getString("message").contains("Error"))) {
            String errorMessage = jsonObj.getString("message");
            throw new IOException("Error getting remarks: " + errorMessage);
        }
        
        // Extract remarks from response (can be empty string)
        String remarks = jsonObj.optString("remarks", "");
        
        CommonMessage.debugMsg("Retrieved Remarks: " + remarks);
        
        return remarks;
    }
	public List<String[]> getCriteriaKeyId(String flId, String equipmentId, String totalPoints, String tradeId) 
            throws IOException {
        
        StringBuilder apiUrl = new StringBuilder("/bdmTlRequest/criteria-key?");
        apiUrl.append("flId=").append(URLEncoder.encode(flId, StandardCharsets.UTF_8));
        apiUrl.append("&totalPoints=").append(totalPoints);
        apiUrl.append("&equipmentId=").append(URLEncoder.encode(equipmentId, StandardCharsets.UTF_8));
        
        if (tradeId != null && !tradeId.isEmpty() && !"-".equals(tradeId)) {
            apiUrl.append("&tradeId=").append(URLEncoder.encode(tradeId, StandardCharsets.UTF_8));
        }
        
        CommonMessage.debugMsg("Get Criteria Key ID URL :: " + apiUrl.toString());
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        CommonMessage.debugMsg("JSON Response: " + res.getBody());
        
        // Parse response - expecting a JSON array
        JSONArray jsonArray = JSONArray.fromObject(res.getBody());
        List<String[]> resultList = new java.util.ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            
            // Extract values and create String array
            String criteriaKeyId = jsonObj.optString("criteriaKeyId", "");
            String description = jsonObj.optString("description", "");
            
            resultList.add(new String[]{criteriaKeyId, description});
        }
        
        CommonMessage.debugMsg("Retrieved Criteria Key IDs count: " + resultList.size());
        
        return resultList;
        
    }
	
	
//	public String deleteCriteriaList(String criteriaDeleteList) {
//	    try {
//	        String apiUrl = "/bdmTlRequest/delete-criteria";
//	        
//	        // Validate input
//	        if (criteriaDeleteList == null || criteriaDeleteList.trim().isEmpty()) {
//	            System.err.println("No criteria IDs provided to delete");
//	            return "{\"success\":false,\"message\":\"No valid criteria IDs to delete\"}";
//	        }
//	        
//	        CommonMessage.debugMsg("Raw input criteriaDeleteList: " + criteriaDeleteList);
//	        
//	        // Parse the incoming JSON array string and filter out "undefined" values
//	        List<String> validKeyIds = new java.util.ArrayList<>();
//	        
//	        try {
//	            // Remove brackets if present: ["CAM123","undefined"] -> "CAM123","undefined"
//	            String cleanInput = criteriaDeleteList;
//	            if (cleanInput.startsWith("[")) {
//	                cleanInput = cleanInput.substring(1);
//	            }
//	            if (cleanInput.endsWith("]")) {
//	                cleanInput = cleanInput.substring(0, cleanInput.length() - 1);
//	            }
//	            
//	            // Split by comma and clean up each value
//	            String[] parts = cleanInput.split(",");
//	            for (String part : parts) {
//	                // Remove quotes and whitespace
//	                String cleaned = part.trim().replaceAll("^\"|\"$", "");
//	                
//	                // Filter out undefined, null, empty values
//	                if (!cleaned.isEmpty() && 
//	                    !"undefined".equals(cleaned) && 
//	                    !"null".equals(cleaned)) {
//	                    validKeyIds.add(cleaned);
//	                }
//	            }
//	        } catch (Exception e) {
//	            System.err.println("Error parsing criteria delete list: " + e.getMessage());
//	            return "{\"success\":false,\"message\":\"Error parsing input: " + e.getMessage() + "\"}";
//	        }
//	        
//	        // Validate we have valid IDs after filtering
//	        if (validKeyIds.isEmpty()) {
//	            System.err.println("No valid criteria IDs to delete after filtering");
//	            return "{\"success\":false,\"message\":\"No valid criteria IDs to delete\"}";
//	        }
//	        
//	        CommonMessage.debugMsg("Valid criteria IDs to delete: " + validKeyIds);
//	        
//	        // Build clean JSON array manually (avoid double-escaping)
//	        StringBuilder jsonPayload = new StringBuilder();
//	        jsonPayload.append("[");
//	        for (int i = 0; i < validKeyIds.size(); i++) {
//	            if (i > 0) {
//	                jsonPayload.append(",");
//	            }
//	            jsonPayload.append("\"").append(validKeyIds.get(i)).append("\"");
//	        }
//	        jsonPayload.append("]");
//	        
//	        String payload = jsonPayload.toString();
//	        CommonMessage.debugMsg("Delete Criteria JSON Payload :: " + payload);
//	        
//	        // Send DELETE request with JSON body
//	        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", payload);
//	        
//	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
//	        
//	        String jsonResponse = res.getBody();
//	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//	        
//	        // Parse JSON response
//	        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
//	        
//	        boolean success = jsonObj.optBoolean("success", false);
//	        String message = jsonObj.optString("message", "");
//	        int deletedCount = jsonObj.optInt("deletedCount", 0);
//	        JSONArray deletedIds = jsonObj.optJSONArray("deletedIds");
//	        
//	        if (success) {
//	            CommonMessage.debugMsg("Criteria records deleted successfully. Count: " + deletedCount + ", Message: " + message);
//	            CommonMessage.debugMsg("Deleted IDs: " + deletedIds);
//	        } else {
//	            System.err.println("Failed to delete criteria records. Message: " + message);
//	        }
//	        
//	        return jsonResponse;
//	        
//	    } catch (Exception e) {
//	        System.err.println("Error deleting criteria list: " + e.getMessage());
//	        e.printStackTrace();
//	        // Return error JSON
//	        return "{\"success\":false,\"message\":\"Error: " + e.getMessage() + "\"}";
//	    }
//	}
	
	public String deleteCriteriaList(String criteriaDeleteList) {
	    
	        String apiUrl = "/bdmTlRequest/delete-criteria";
	        
	        String cleanedId = criteriaDeleteList.replace("\"", "").trim();
	        
	        String payLoad = deleteJson("[\"" + cleanedId + "\"]");
	        
	        CommonMessage.debugMsg("Pay Load "+payLoad);

	        // Send DELETE request with JSON body
	        HttpResponse res;
			try {
				res = api.makeAuthRequest(apiUrl, "POST", payLoad);
		        String jsonResponse = res.getBody();
			
	        
	        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	        

	        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	        
	        // Parse JSON response
	        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
	        
	        return jsonResponse;
			}
	        catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	  
	}
	public static String deleteJson(String criteriaDeleteList) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"criteriaDeleteList\":");
	    sb.append(criteriaDeleteList);
	    sb.append("}");
	    return sb.toString();
	}
	
	public static String saveJson(BdmTlCriticalityassessment bdmTlCriticalityassessment) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"master\":");
	    sb.append(bdmTlCriticalityassessment.toJsonManual());
	    sb.append(",\"TotalRating\":");
	    sb.append("\"").append(bdmTlCriticalityassessment.getCasmTotalRating()).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
	
	public static String BuildJsonList(List<BdmTlCriticalityassessment> list) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < list.size(); i++) {
	        if (i > 0) sb.append(",");
	        sb.append(saveJson(list.get(i)));
	    }
	    sb.append("]");
	    return sb.toString();
	}
}