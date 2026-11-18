package com.akranta.tpm.service.api;

import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlEnablelosscapture;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;

public class BAL_PcsTlEnablelosscaptureServiceApi {
	
	private final Api api;

    public BAL_PcsTlEnablelosscaptureServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	
	//create or update
	
	public List<BAL_PcsTlEnablelosscapture> createorupdate(List<BAL_PcsTlEnablelosscapture> list)  throws Exception {
    	
    	
		
    	
    	String apiUrl = "/BAL_PcsTlEnablelosscapture/createorupdate"; // The Spring Boot API endpoint for insert
    	//jhaTlVisualsopmst.setUpsmActive("Y");
        
        //String jsonPayload = insertJson(jhaTlVisualsopmst);
       
    	String jsonPayload = BAL_PcsTlEnablelosscapture.toJsonManualList(list);
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        List<BAL_PcsTlEnablelosscapture> save = BAL_PcsTlEnablelosscapture.fromJsonList(jsonResponse);
        CommonMessage.debugMsg(save);
        
        return save;
	}

}
