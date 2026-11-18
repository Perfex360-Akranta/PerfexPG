package com.akranta.tpm.service.api;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.akranta.tpm.model.BAL_PcsTlCycletimemst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;

public class BAL_PcsTlCycletimeServiceApi {
	
	private final Api api;
	
	public BAL_PcsTlCycletimeServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }
	
	
	public BAL_PcsTlCycletimemst createorupdate (BAL_PcsTlCycletimemst PcsTlCycletimemst) throws Exception {
        String apiUrl = "/cycletime/createorupdate";
 
        String jsonPayload = PcsTlCycletimemst.toJsonManual();
 
        CommonMessage.debugMsg("Cycletime  JSON :: " + jsonPayload);
 
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
 
        CommonMessage.debugMsg("Cycletime  CREATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("Cycletime  CREATE body   :: " + res.getBody());
 
        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";
 
        
        if (code < 200 || code >= 300) {
            throw new IllegalStateException("Cycletime SAVE failed HTTP=" + code + " body=[" + body + "]");
        }
 
        
        BAL_PcsTlCycletimemst result = BAL_PcsTlCycletimemst.fromJson(body);
        CommonMessage.debugMsg("Parsed Result: " + result);
 
        return result;
    }
	
	public BAL_PcsTlCycletimemst delete (BAL_PcsTlCycletimemst PcsTlCycletimemst) throws Exception {
		String keyid = PcsTlCycletimemst.getCytmKeyid();
        String apiUrl = "/cycletime/delete/" +URLEncoder.encode(keyid, StandardCharsets.UTF_8);;
 
        String jsonPayload = PcsTlCycletimemst.toJsonManual();
 
        CommonMessage.debugMsg("Cycletime  JSON :: " + jsonPayload);
 
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
 
        CommonMessage.debugMsg("Cycletime  CREATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("Cycletime  CREATE body   :: " + res.getBody());
 
        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";
 
        
        if (code < 200 || code >= 300) {
            throw new IllegalStateException("Cycletime delete failed HTTP=" + code + " body=[" + body + "]");
        }
 
        
        CommonMessage.debugMsg("Cycletime deleted successfully: " + keyid);
        return PcsTlCycletimemst;
 
        
    }


	


}
