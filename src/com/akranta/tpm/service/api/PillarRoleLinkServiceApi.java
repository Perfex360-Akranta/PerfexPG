package com.akranta.tpm.service.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.model.AbnTlAbnormality;
//import com.akranta.tpm.model.BAL_GenTlSubAssemblymst;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class PillarRoleLinkServiceApi {

	private final Api api;
	
	public PillarRoleLinkServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public String savePillarRoleLinkApi(String pillarId, String roleId, String createdBy) throws IOException {

	    String apiUrl = "/pillar-role-link/save";

	    String jsonPayload = insertPillarRoleJson(pillarId, roleId, createdBy);
	    CommonMessage.debugMsg("Json PillarRoleLink Save:: " + jsonPayload);
	    
	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
	    
	    String jsonResponse = res.getBody();
        
        	if (res.getStatusCode() ==  409) {
        		CommonMessage.debugMsg("Pillar-Role conflict occurred");
        		jsonResponse = "Pillar-Role conflict occurred";
            
        }

	    

	    CommonMessage.debugMsg(res.getStatusCode());

	    //String jsonResponse = res.getBody();

	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	    return jsonResponse;
	}
	
	public String deletePillarRoleLinkApi(String pillarId, String roleId) throws IOException {

	    String apiUrl = "/pillar-role-link/delete";

	    String jsonPayload = deletePillarRoleJson(pillarId, roleId);
	    CommonMessage.debugMsg("Json PillarRoleLink Delete:: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg(res.getStatusCode());

	    String jsonResponse = res.getBody();
	  
        // added by priyanka
        if (res.getStatusCode() ==  409) {
            CommonMessage.debugMsg("Pillar-Role conflict occurred");
            jsonResponse = "Pillar-Role conflict occurred";
            
        }

	    CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	    return jsonResponse;
	}
	
	
	private String insertPillarRoleJson(String pillarId, String roleId, String createdBy) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"pillarId\":").append("\"").append(pillarId).append("\"");
	    sb.append(",\"roleId\":").append("\"").append(roleId).append("\"");
	    sb.append(",\"createdBy\":").append("\"").append(createdBy).append("\"");
	    sb.append("}");
	    return sb.toString();
	}

	private String deletePillarRoleJson(String pillarId, String roleId) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"pillarId\":").append("\"").append(pillarId).append("\"");
	    sb.append(",\"roleId\":").append("\"").append(roleId).append("\"");
	    sb.append("}");
	    return sb.toString();
	}
	
}