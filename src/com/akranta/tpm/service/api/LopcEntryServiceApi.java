package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.akranta.tpm.model.LopcEntryMst;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class LopcEntryServiceApi {
    private final Api api;
    
    public LopcEntryServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save LOPC Entry (handles both insert and update)
     * @param lopcEntryMst Master data
     * @return Saved/Updated LopcEntryMst with generated ID
     */
    public LopcEntryMst saveLopcEntry(LopcEntryMst lopcEntryMst) throws Exception {
        String apiUrl = "/lopc-entry/save";
        
        String jsonPayload = buildSaveJson(lopcEntryMst);
        
        CommonMessage.debugMsg("LOPC Entry JSON :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response back to object
        LopcEntryMst result = parseLopcEntryResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);
        
        return result;
    }

    /**
     * Get complete LOPC Entry data by master keyid
     * @param lopcEntryMst The master key ID
     * @return Complete LopcEntryMst data
     */
    /**
     * Get complete LOPC Entry data by master keyid
     * @param keyid The master key ID (String)
     * @return Complete LopcEntryMst data
     */
    public LopcEntryMst getCompleteLopcEntryData(String keyid) throws IOException {
        String apiUrl = "/lopc-entry/get/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("Get Complete Data Response: " + res.getBody());
        String json = res.getBody();

        // Parse response
        LopcEntryMst result = parseLopcEntryResponse(json);

        return result;
    }
    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(LopcEntryMst lopcEntryMst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"master\":");
        String jsonMst = lopcEntryMst.toJsonManual();
        str.append(jsonMst);
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Parse JSON response into LopcEntryMst object
     */
    private LopcEntryMst parseLopcEntryResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        LopcEntryMst mst = LopcEntryMst.fromJson(mstObj.toString());
        
        return mst;
    }
    public BdmTlWwbladtl updateLopcActionClosure(BdmTlWwbladtl bdmTlWwbladtl, 
            BdmTlWwbladtl existBdmTlWwbladtl, 
            String keyid, 
            String completedBy, 
            String status, 
            String completedDate, 
            String correctiveaction, 
            String remarks) throws Exception {
String apiUrl = "/lopc-entry/update-closure/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

// Build JSON payload
StringBuilder str = new StringBuilder();
str.append("{");
str.append("\"completedBy\":\"").append(completedBy != null ? completedBy : "").append("\",");
str.append("\"status\":\"").append(status != null ? status : "").append("\",");
str.append("\"completedDate\":\"").append(completedDate != null ? completedDate : "").append("\",");
str.append("\"correctiveAction\":\"").append(correctiveaction != null ? correctiveaction : "").append("\",");
str.append("\"remarks\":\"").append(remarks != null ? remarks : "").append("\"");
str.append("}");
String jsonPayload = str.toString();

CommonMessage.debugMsg("Update Closure JSON :: " + jsonPayload);

// Send request
HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);

CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

String jsonResponse = res.getBody();
CommonMessage.debugMsg("JSON Response: " + jsonResponse);

// Parse response
JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
String message = jsonObj.optString("message", "Update successful");
CommonMessage.debugMsg("Update Message: " + message);

// Return the updated object (you can update it with response data if needed)
// For now, just update the fields that were sent
if (bdmTlWwbladtl != null) {
bdmTlWwbladtl.setWwbdCompletedBy(completedBy);
bdmTlWwbladtl.setWwbdStatus(status);
bdmTlWwbladtl.setWwbdAction(correctiveaction);
bdmTlWwbladtl.setWwbdRemarks(remarks);
// Note: completedDate would need to be converted from String to LocalDateTime
}

return bdmTlWwbladtl;
}

/**
* Get all detail records for a master key
* @param wwblKeyid Master key ID
* @return JSON array string of detail records
*/
public String getDetailRecords(String wwblKeyid) throws IOException {
String apiUrl = "/lopc-entry/detail-records/" + URLEncoder.encode(wwblKeyid, StandardCharsets.UTF_8);

// Send GET request
HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

CommonMessage.debugMsg("Get Detail Records Response: " + res.getBody());

return res.getBody();
}
  
}