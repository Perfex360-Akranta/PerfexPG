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
public class TradeRoleLinkServiceApi {

	private final Api api;
	
	public TradeRoleLinkServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public String saveTradeRoleLinkApi(String tradeId, String roleId, String createdBy) throws IOException {

        String apiUrl = "/trade-role-link/save";

        String jsonPayload = insertJson(tradeId, roleId, createdBy);
        CommonMessage.debugMsg("Json TradeRoleLink Save:: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg(res.getStatusCode());
        
        

        String jsonResponse = res.getBody();
        
        if (res.getStatusCode() ==  409) {
            CommonMessage.debugMsg("Trade-Role conflict occurred");
            jsonResponse = "Trade-Role conflict occurred";
            
        }
        // commented and added by priyanka 
		/*
		 * if (jsonResponse == null || !jsonResponse.trim().startsWith("{")) {
		 * JSONObject fallback = new JSONObject(); fallback.put("tpmException",
		 * "Save failed. Please try again."); return fallback; }
		 */
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        return jsonResponse;

     
    }

    //public JSONObject deleteTradeRoleLinkApi(String tradeId, String roleId) throws IOException {
    public String deleteTradeRoleLinkApi(String tradeId, String roleId) throws IOException {

        String apiUrl = "/trade-role-link/delete";

        String jsonPayload = deleteJson(tradeId, roleId);
        CommonMessage.debugMsg("Json TradeRoleLink Delete:: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg(res.getStatusCode());
        
        // added by priyanka 
		/*
		 * if (res.getStatusCode() != 200) { String errorMsg =
		 * res.getHeader("Error-Message"); JSONObject err = new JSONObject();
		 * err.put("tpmException", errorMsg != null ? errorMsg : "Data Not Deleted");
		 * return err; }
		 */
        // end 

        String jsonResponse = res.getBody();
        // added by priyanka
        if (res.getStatusCode() ==  409) {
            CommonMessage.debugMsg("Trade-Role conflict occurred");
            jsonResponse = "Trade-Role conflict occurred";
            
        }
        // end 
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        //return new JSONObject(jsonResponse);
        return jsonResponse;
    }

    private String insertJson(String tradeId, String roleId, String createdBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"tradeId\":").append("\"").append(tradeId).append("\"");
        sb.append(",\"roleId\":").append("\"").append(roleId).append("\"");
        sb.append(",\"createdBy\":").append("\"").append(createdBy).append("\"");
        sb.append("}");
        return sb.toString();
    }

    private String deleteJson(String tradeId, String roleId) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"tradeId\":").append("\"").append(tradeId).append("\"");
        sb.append(",\"roleId\":").append("\"").append(roleId).append("\"");
        sb.append("}");
        return sb.toString();
    }
	
	
	
}	