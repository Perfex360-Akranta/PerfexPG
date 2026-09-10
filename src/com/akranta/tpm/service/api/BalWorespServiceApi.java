package com.akranta.tpm.service.api;



import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.SequenceNumGenException;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BalWorespServiceApi {

    private final Api api;

    public BalWorespServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

    // saving work order responsibility (master + detail list) — matches plmTlWorespmstDao.create(...)
    public BAL_PlmTlWorespmst insertRecord(BAL_PlmTlWorespmst plmTlWorespmst) throws Exception {

        String apiUrl = "/BalWoresponsibility/save";   // ⚠️ confirm this matches your Api class's base URL setup

        String jsonPayload = plmTlWorespmst.toJsonManual();
        CommonMessage.debugMsg("Json :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        BAL_PlmTlWorespmst result = BAL_PlmTlWorespmst.fromJson(jsonResponse);
        CommonMessage.debugMsg(result);

        return result;
    }
    
    
    // updating work order responsibility detail rows — matches plmTlWorespmstDao.update(...)
    public BAL_PlmTlWorespmst updateRecord(BAL_PlmTlWorespmst plmTlWorespmst) throws Exception {

        String apiUrl = "/BalWoresponsibility/update";

        String jsonPayload = plmTlWorespmst.toJsonManual();
        CommonMessage.debugMsg("Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg(res.getStatusCode());
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        BAL_PlmTlWorespmst result = BAL_PlmTlWorespmst.fromJson(jsonResponse);
        CommonMessage.debugMsg(result);

        return result;
    }
    
 // fetching next due dates
    
	/*
	 * public List<String[]> getNextDueDates() throws Exception { String apiUrl =
	 * "/api/BalPlanConfiguration/getnextduedates"; HttpResponse res =
	 * api.makeAuthRequest(apiUrl, "GET", null);
	 * CommonMessage.debugMsg(res.getStatusCode()); String jsonResponse =
	 * res.getBody(); CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	 * 
	 * if (res.getStatusCode() != 200) { throw new
	 * BusinessApplicationExceptions(extractMessage(jsonResponse)); }
	 * 
	 * JSONArray jsonArray = new JSONArray(jsonResponse); List<String[]> result =
	 * new ArrayList<>(); for (int i = 0; i < jsonArray.length(); i++) { JSONObject
	 * obj = jsonArray.getJSONObject(i); result.add(new String[] {
	 * obj.getString("freq"), obj.getString("nextDueDate") }); } return result; }
	 */
    
	
	  public List<String[]> getNextDueDates() throws Exception {
	  
	  String apiUrl = "/BalPlanConfiguration/getnextduedates"; // ⚠️ confirm base
	  
	  
	  HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	  
	  CommonMessage.debugMsg(res.getStatusCode()); String jsonResponse =
	  res.getBody(); CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	  
	  JSONArray jsonArray = new JSONArray(jsonResponse); List<String[]> result =
	  new ArrayList<>(); for (int i = 0; i < jsonArray.length(); i++) { JSONObject
	  obj = jsonArray.getJSONObject(i); result.add(new String[] {
	  obj.getString("freq"), obj.getString("nextDueDate") }); } return result; }
	 

    // saving single plan configuration — matches plmTlPlanconfigurationDao.create(...)
    
	/*
	 * public BAL_PlmTlPlanconfiguration insertRecord(BAL_PlmTlPlanconfiguration
	 * newPlmTlPlanconfiguration) throws Exception { String apiUrl =
	 * "/api/BalPlanConfiguration/save"; String jsonPayload =
	 * newPlmTlPlanconfiguration.toJsonManual(); CommonMessage.debugMsg("Json :: " +
	 * jsonPayload);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	 * CommonMessage.debugMsg(res.getStatusCode()); String jsonResponse =
	 * res.getBody(); CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	 * 
	 * if (res.getStatusCode() != 201) { throwMappedException(jsonResponse); }
	 * 
	 * BAL_PlmTlPlanconfiguration result =
	 * BAL_PlmTlPlanconfiguration.fromJson(jsonResponse);
	 * CommonMessage.debugMsg(result); return result; }
	 * 
	 * // maps the backend's {errorType, message} envelope back to the old exception
	 * types private void throwMappedException(String jsonResponse) throws Exception
	 * { String errorType = "UNKNOWN"; String message = jsonResponse; try {
	 * JSONObject err = new JSONObject(jsonResponse); errorType =
	 * err.optString("errorType", "UNKNOWN"); message = err.optString("message",
	 * jsonResponse); } catch (Exception parseEx) { // body wasn't JSON — fall back
	 * to raw string as the message }
	 * 
	 * switch (errorType) { case "SEQUENCE_NUM_GEN": throw new
	 * SequenceNumGenException(message); case "WO_RESPONSIBILITY": throw new
	 * WoResponsibilityExpection(message); case "BUSINESS_APPLICATION": default:
	 * throw new BusinessApplicationExceptions(message); } }
	 * 
	 * private String extractMessage(String jsonResponse) { try { JSONObject err =
	 * new JSONObject(jsonResponse); return err.optString("message", jsonResponse);
	 * } catch (Exception e) { return jsonResponse; } }
	 */
    
	
	  public BAL_PlmTlPlanconfiguration insertRecord(BAL_PlmTlPlanconfiguration
	  newPlmTlPlanconfiguration) throws Exception {
	  
	  String apiUrl = "/BalPlanConfiguration/save"; // ⚠️ confirm base URL first
	  
	  String jsonPayload = newPlmTlPlanconfiguration.toJsonManual();
	  CommonMessage.debugMsg("Json :: " + jsonPayload);
	  
	  HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	  
	  CommonMessage.debugMsg(res.getStatusCode()); String jsonResponse =
	  res.getBody(); CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	  
	  BAL_PlmTlPlanconfiguration result =
	  BAL_PlmTlPlanconfiguration.fromJson(jsonResponse);
	  CommonMessage.debugMsg(result);
	  
	  return result; }
	 
}