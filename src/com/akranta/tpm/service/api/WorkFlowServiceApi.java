package com.akranta.tpm.service.api;

import com.akranta.tpm.dao.impl.CommonFunctions;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.GenTlWorkflowdtl;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.KznTlProjectcreationmst;
import com.akranta.tpm.model.WorkFlowmst;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class WorkFlowServiceApi {

private final Api api;
	
	public WorkFlowServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	
	public void saveApprovals(GenTlWorkflowInfo genTlWorkflowInfo, String lastLevel, String nextRoleName, String nextRoleId) throws Exception {
        String apiUrl = "/workflow/approval/save";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"workFlowInfo\":");
	    sb.append(genTlWorkflowInfo.toJsonManual());
	    sb.append(",\"lastLevel\":");
	    if (!CommonFunctions.isValidKeyId(lastLevel)) {
	    	sb.append("\"").append("N").append("\""); 
        } else {
            sb.append("\"").append(lastLevel).append("\"");
        }
	    sb.append(",\"nextRoleName\":");
	    if (!CommonFunctions.isValidKeyId(nextRoleName)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(nextRoleName).append("\"");
        }
	    sb.append(",\"nextRoleId\":");
	    if (!CommonFunctions.isValidKeyId(nextRoleId)) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(nextRoleId).append("\"");
        }
	    sb.append(",\"nextEmpId\":");
	    if (!CommonFunctions.isValidKeyId(genTlWorkflowInfo.getNextEmpId())) {
            sb.append("null"); 
        } else {
            sb.append("\"").append(genTlWorkflowInfo.getNextEmpId()).append("\"");
        }
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
           
    }
	
	
	public WorkFlowmst getWorkflowMst(String KeyId) throws Exception {
        String apiUrl = "/workflow/mst/"+KeyId;
        
        CommonMessage.debugMsg("Api URL: " + apiUrl);
        HttpResponse res = api.makeAuthRequest(apiUrl,"GET",null);

   
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        WorkFlowmst wfm = WorkFlowmst.fromJson(jsonResponse);
        
        return wfm;
        

   }
	
	public WorkFlowmst saveWorkFlow(WorkFlowmst genTlWorkflowmst) throws Exception {
        String apiUrl = "/workflow/mst/save";
           
      
        StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"workFlowMst\":");
	    sb.append(genTlWorkflowmst.toJsonManual());
	    sb.append(",\"workFlowDtls\":");
	    sb.append(GenTlWorkflowdtl.toJsonManualList(genTlWorkflowmst.getWorkFlowDtls()));
	    sb.append("}");
	    String jsonPayload = sb.toString();
	    
	    CommonMessage.debugMsg("Json :: "+jsonPayload);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"POST",jsonPayload);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JSONObject obj = JSONObject.fromObject(jsonResponse);
        
        genTlWorkflowmst = WorkFlowmst.fromJson(obj.getString("workFlowMst"));
        
        return genTlWorkflowmst;
        
           
    }
	
	public WorkFlowmst delteWorkFlowDtl(String Keyid) throws Exception {
        String apiUrl = "/workflow/dtl/"+Keyid;
           
	    
	    CommonMessage.debugMsg("apiUrl :: "+apiUrl);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE",null);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
  
        return null;
        
           
    }
	
	public WorkFlowmst deleteWorkFlowMst(WorkFlowmst genTlWorkflowmst) throws Exception {
        String apiUrl = "/workflow/mst/"+genTlWorkflowmst.getWrkmKeyid();
           
	    
	    CommonMessage.debugMsg("apiUrl :: "+apiUrl);
      
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE",null);
   
        CommonMessage.debugMsg(apiUrl + ":"+res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
  
        return genTlWorkflowmst;
        
           
    }
}
