package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.QtmTlCapa;
import com.akranta.tpm.model.QtmTlCapaActionmst;
import com.akranta.tpm.model.QtmTlCapaWorkflow;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class NewcapaServiceApi {
	private final Api api;

    public NewcapaServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
    
    public  QtmTlCapa  saveQtmTlCapa(QtmTlCapa qtmTlCapa) 
			throws Exception {
        
		String apiUrl = "/qtmtlcapa/save"; 
		
        
		String jsonPayload =  qtmTlCapa.toJsonManual();
		
        System.out.println("Json Know Why:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        System.out.println(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        System.out.println("JSON Response: " + jsonResponse);
   
        qtmTlCapa   =QtmTlCapa.fromJson(jsonResponse);
        
        return qtmTlCapa;
        
}  
    
    

    
    public QtmTlCapaWorkflow saveQtmTlCapaWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId)
            throws Exception {

        String apiUrl = "/qtmtlcapa/saveWorkflow";

        String jsonPayload = "{"
                + "\"workflow\":" + qtmTlCapaWorkflow.toJsonManual() + ","
                + "\"capaId\":\"" + capaId + "\""
                + "}";
//        String jsonPayload = "{"
//                + "\"workflow\":" + qtmTlCapaWorkflow.toJsonManual() + ","
//                + "\"capaId\":" + (capaId == null ? null : "\"" + capaId + "\"")
//                + "}";

        System.out.println("Json Know Why:: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        System.out.println(res.getStatusCode());

        String jsonResponse = res.getBody();

        System.out.println("JSON Response: " + jsonResponse);

        JSONObject mainObj = JSONObject.fromObject(jsonResponse);

        if (mainObj.has("workflow")) {
            JSONObject workflowObj = mainObj.getJSONObject("workflow");
            qtmTlCapaWorkflow = QtmTlCapaWorkflow.fromJson(workflowObj.toString());
        }

        return qtmTlCapaWorkflow;
  }
    
    public  List<QtmTlCapaActionmst>  saveQtmTlCapaActionmst(List<QtmTlCapaActionmst> qtmTlCapaActionmst, String createdBy,String CapaMasterKeyidL) 
			throws Exception {
        
		String apiUrl = "/qtmtlcapa/saveActionMst"; 
		
		String jsonPayload = "{"
		        + "\"actionList\":" + QtmTlCapaActionmst.toJsonManualList(qtmTlCapaActionmst) + ","
		        + "\"createdby\":\"" + createdBy + "\","
		        + "\"capaMasterKeyid\":\"" + CapaMasterKeyidL + "\""
		        + "}";
		
        System.out.println("Json Know Why:: "+jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
        
        
        // Get the response code to confirm insert success, usually 201 Created
        System.out.println(res.getStatusCode());

        
        

        String jsonResponse = res.getBody();
        
        
        
        System.out.println("JSON Response: " + jsonResponse);
   
        List<QtmTlCapaActionmst>  result = getJson(jsonResponse);
        
        return result;
        
}
    public  List<QtmTlCapaActionmst> getJson(String json) 
	{
		JSONObject jsonObj = new JSONObject(json);
		
		JSONArray actionmstArray = null;
		List<QtmTlCapaActionmst> actionmstDtl = new ArrayList<>();
		
		
		
		if (jsonObj.has("actionList")
		        && !jsonObj.isNullObject()
		        && !jsonObj.get("actionList").equals(null)) {

			actionmstArray = jsonObj.getJSONArray("actionList");
		}
		
		
		if(actionmstArray != null &&!actionmstArray.isEmpty()) {
			
			actionmstDtl = QtmTlCapaActionmst.fromJsonList(actionmstArray.toString());
		}
		
		return actionmstDtl;
		
	}
    
    
    
    public QtmTlCapaWorkflow updateCapaWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String empId,
			 String status, 
			 String date,
			 String remarks,
	            String capaId) throws IOException {

			String apiUrl = "/qtmtlcapa/updateCapaWorkflow"
			+ "?empId=" + URLEncoder.encode(empId, StandardCharsets.UTF_8)
			+ "&remarks=" + URLEncoder.encode(remarks, StandardCharsets.UTF_8)
			+ "&status=" + URLEncoder.encode(status, StandardCharsets.UTF_8)
			+ "&date=" + URLEncoder.encode(date, StandardCharsets.UTF_8)
			+ "&capaId=" + URLEncoder.encode(capaId, StandardCharsets.UTF_8);
			
			System.out.println("Date "+date);

					// Send POST request (no body, params in URL)
					HttpResponse res = api.makeAuthRequest(apiUrl, "POST", null);
					
					System.out.println(res.getBody() + " Body");
					
					// Since controller returns int
					int result = Integer.parseInt(res.getBody());
					
					return qtmTlCapaWorkflow;
}
    
    public String getFnlnKeyIdByOriginalId(String targetJH) throws Exception {

        String apiUrl = "/qtmtlcapa/getFnlnKeyIdByOriginalId/" +
                URLEncoder.encode(targetJH, StandardCharsets.UTF_8);

        System.out.println("Calling API: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        System.out.println("Status Code: " + res.getStatusCode());

        String result = res.getBody();

        System.out.println("Response Body: " + result);

        return result;
    }
    
    
//    public void deleteCapaActionPlanDetail(String qcpaKeyid) throws Exception {
//
//        String apiUrl = "/qtmtlcapa/deleteCapaActionPlanDetail" +
//                URLEncoder.encode(qcpaKeyid, StandardCharsets.UTF_8);
//
//        System.out.println("Calling DELETE API: " + apiUrl);
//
//        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
//
//        System.out.println("Status Code: " + res.getStatusCode());
//
//        if (res.getStatusCode() != 200) {
//            throw new RuntimeException("Failed to delete record. Status: " + res.getStatusCode());
//        }
//    }
    
    public QtmTlCapaActionmst deleteCapaActionPlanDetail(QtmTlCapaActionmst qtmTlCapaActionmst) throws Exception {

        String apiUrl = "/qtmtlcapa/deleteCapaActionPlanDetail/"+ qtmTlCapaActionmst.getQcpaKeyid();

        System.out.println("Calling DELETE API: " + apiUrl);
        System.out.println("Deleting QCPA_KEYID: " + qtmTlCapaActionmst.getQcpaKeyid());

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

        int statusCode = res.getStatusCode();
        System.out.println("Status Code: " + statusCode);

        if (statusCode != 200) {
            throw new RuntimeException("Failed to delete record. Status: " + statusCode);
        }

        return qtmTlCapaActionmst;
    }
    
    
    
    
    
    


}
//public  QtmTlCapaWorkflow  saveQtmTlCapaWorkflow(QtmTlCapaWorkflow qtmTlCapaWorkflow, String capaId) 
//		throws Exception {
//  
//	String apiUrl = "/qtmtlcapa/saveWorkflow"; 
//	
//  
//	 String jsonPayload = "{"
//	            + "\"workflow\":" + qtmTlCapaWorkflow.toJsonManual() + ","
//	            + "\"capaId\":\"" + capaId + "\""
//	            + "}";
//	
//	
//	
//  System.out.println("Json Know Why:: "+jsonPayload);
//  
//  // Send request
//  HttpResponse res = api.makeAuthRequest(apiUrl,"POST", jsonPayload);
//  
//  
//  // Get the response code to confirm insert success, usually 201 Created
//  System.out.println(res.getStatusCode());
//
//  
//  
//
//  String jsonResponse = res.getBody();
//  
//  
//  
//  System.out.println("JSON Response: " + jsonResponse);
//
//  qtmTlCapaWorkflow = QtmTlCapaWorkflow.fromJson(jsonResponse);
//  
//  return qtmTlCapaWorkflow;
//  
//}