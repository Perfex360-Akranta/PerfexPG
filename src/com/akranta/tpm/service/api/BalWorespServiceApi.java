

package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.BAL_PlmTlWorespmst;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class BalWorespServiceApi {
    private final Api api;

    public BalWorespServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    
	
    
    public BAL_PlmTlPlanconfiguration createPlanConfiguration(BAL_PlmTlPlanconfiguration planconfiguration) throws Exception {
        String apiUrl = "/plan-configuration/create";
 
        String jsonPayload = planconfiguration.toJsonManual();
 
        CommonMessage.debugMsg("Plan Configuration JSON :: " + jsonPayload);
 
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
 
        CommonMessage.debugMsg("Plan Configuration CREATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("Plan Configuration CREATE body   :: " + res.getBody());
 
        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";
 
        
        if (code < 200 || code >= 300) {
            throw new IllegalStateException("PLAN CONFIGURATION SAVE failed HTTP=" + code + " body=[" + body + "]");
        }
 
        
        BAL_PlmTlPlanconfiguration result = BAL_PlmTlPlanconfiguration.fromJson(body);
        CommonMessage.debugMsg("Parsed Result: " + result);
 
        return result;
    }
    
    //workorder
    public BAL_PlmTlWorespmst saveWoresp(BAL_PlmTlWorespmst worespmst) throws Exception {
        String apiUrl = "/woresp/save";

        String jsonPayload = worespmst.toJsonManual();

        CommonMessage.debugMsg("WoResp Save JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("WoResp SAVE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("WoResp SAVE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("WORESP SAVE failed HTTP=" + code + " body=[" + body + "]");
        }

        BAL_PlmTlWorespmst result = BAL_PlmTlWorespmst.fromJson(body);
        CommonMessage.debugMsg("Parsed WoResp Result: " + result);

        return result;
    }

   
    public BAL_PlmTlWorespmst getCompleteWorespData(String masterKeyid) throws IOException {
        String apiUrl = "/woresp/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("Get Complete WoResp Data Response: " + res.getBody());
        String json = res.getBody();

        return BAL_PlmTlWorespmst.fromJson(json);
    }

   
    public boolean deleteWorespDetail(String detailId) throws IOException {
        String apiUrl = "/woresp/delete-detail/" + URLEncoder.encode(detailId, StandardCharsets.UTF_8);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

        CommonMessage.debugMsg("Delete WoResp Detail Response: " + res.getBody());
        String json = res.getBody();

        JSONObject responseObj = JSONObject.fromObject(json);
        boolean success = responseObj.optBoolean("success", false);

        CommonMessage.debugMsg("Delete successful: " + success);

        return success;
    }


}