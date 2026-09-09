package com.akranta.tpm.service.api;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.model.GenTlVisualcntchecklistdtl;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class VisualControlChecklistServiceApi {
	private final Api api;
	
	public VisualControlChecklistServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }
	  
    public GenTlVisualcontrolchecklist insertRecord(
            GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,
            VisualControlCheckListBean visualControlCheckListBean) throws Exception {
        
        String apiUrl = "/visualcontrol/create";
        
        String jsonPayload = insertJson(newGenTlVisualcontrolchecklist, visualControlCheckListBean);
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        GenTlVisualcontrolchecklist visualControl = getJson(jsonResponse);
        CommonMessage.debugMsg(visualControl);
        
        return visualControl;
    }

    /**
     * Convert GenTlVisualcontrolchecklist object to JSON string (Master + Details + Bean)
     */
    public String insertJson(GenTlVisualcontrolchecklist master, VisualControlCheckListBean bean) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"genTlVisualcontrolchecklist\":");
        String jsonMst = master.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values
        List<GenTlVisualcntchecklistdtl> details = master.getVisualControlDetail();
        str.append("\"genTlVisualcntchecklistdtl\":");
        String jsonDtl = GenTlVisualcntchecklistdtl.toJsonManualList(details);
        str.append(jsonDtl);
        
        // Bean DTO (if not null)
        if (bean != null) {
            str.append(",");
            str.append("\"visualControlChecklistdtlDto\":");
            str.append(beanToJson(bean));
        }
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Convert VisualControlCheckListBean to JSON
     */
//    private String beanToJson(VisualControlCheckListBean bean) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//        
//        sb.append("\"dtlKeyid\":");
//        if (bean.getDtlKeyid() != null) {
//            sb.append("\"").append(bean.getDtlKeyid()).append("\"");
//        } else {
//            sb.append("null");
//        }
//        
//        sb.append("}");
//        return sb.toString();
//    }
    
    private String beanToJson(VisualControlCheckListBean bean) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"dtlKeyid\":");
        String dtlKeyid = bean.getDtlKeyid();
        
        // ✅ Handle null, empty, and "null" string properly
        if (dtlKeyid == null || dtlKeyid.trim().isEmpty() || dtlKeyid.equalsIgnoreCase("null")) {
            sb.append("null");  // JSON null without quotes
        } else {
            sb.append("\"").append(dtlKeyid).append("\"");
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert JSON response to GenTlVisualcontrolchecklist object
     */
    public GenTlVisualcontrolchecklist getJson(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        JSONObject mstObj = jsonObj.getJSONObject("genTlVisualcontrolchecklist");
        JSONArray dtlArray = jsonObj.getJSONArray("genTlVisualcntchecklistdtl");
        
        GenTlVisualcontrolchecklist master = GenTlVisualcontrolchecklist.fromJson(mstObj.toString());
        List<GenTlVisualcntchecklistdtl> details = GenTlVisualcntchecklistdtl.fromJsonList(dtlArray.toString());
        
        master.setVisualControlDetail(details);
        
        return master;
    }
	
    
    
    
    
    public GenTlVisualcontrolchecklist getRecordByKeyid(String keyid) throws Exception {
        
        String apiUrl = "/visualcontrol/native/" + keyid;
        
        CommonMessage.debugMsg("Fetching record with keyid: " + keyid);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Check for error status codes
        if (statusCode != 200 && statusCode != 201) {
            System.err.println("ERROR: API call failed with status code: " + statusCode);
            System.err.println("Response Body: " + jsonResponse);
            throw new Exception("API call failed with status " + statusCode + ": " + jsonResponse);
        }
        
        // Validate response is not empty
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new Exception("API returned empty response");
        }
        
        // Parse response (it's an Object[] from native query)
        GenTlVisualcontrolchecklist visualControl = parseNativeQueryResponse(jsonResponse);
        CommonMessage.debugMsg("Successfully fetched Visual Control Check: " + visualControl.getVcclKeyid());
        
        return visualControl;
    }

    /**
     * Parse native query response (Object[]) to entity
     */
//    private GenTlVisualcontrolchecklist parseNativeQueryResponse(String json) {
//        // Remove array brackets if present
//        String cleanJson = json.trim();
//        if (cleanJson.startsWith("[") && cleanJson.endsWith("]")) {
//            cleanJson = cleanJson.substring(1, cleanJson.length() - 1);
//        }
//        
//        // Parse as JSONArray
//        JSONArray jsonArray = JSONArray.fromObject("[" + cleanJson + "]");
//        
//        // ✅ FIX: Use length() instead of size()
//        if (jsonArray.length() == 0) {
//            return null;
//        }
//        
//        GenTlVisualcontrolchecklist master = new GenTlVisualcontrolchecklist();
//        
//        // Map array positions to entity fields
//        master.setVcclKeyid(jsonArray.getString(0));
//        master.setVcclFlid(jsonArray.getString(1));
//        master.setVcclEmployeeid(jsonArray.getString(2));
//        master.setVcclDate(jsonArray.getString(3));
//        master.setVcclTitle(jsonArray.getString(4));
//        master.setVcclApprovedby(jsonArray.getString(5));
//        master.setVcclTempfield3(jsonArray.getString(6));
//        master.setVcclTempfield4(jsonArray.getString(7));
//        master.setVcclTempfield5(jsonArray.getString(8));
//        master.setVcclTempfield6(jsonArray.getString(9));
//        master.setVcclActive(jsonArray.getString(10));
//        master.setVcclCreatedby(jsonArray.getString(11));
//        master.setVcclCreatedon(jsonArray.getString(12));
//        master.setVcclModifiedon(jsonArray.getString(13));
//        
//        return master;
//    }
    
    
    
    /**
     * Parse native query response (Object[]) to entity
     */
    private GenTlVisualcontrolchecklist parseNativeQueryResponse(String json) {
        // Parse the JSON response
        JSONArray outerArray = JSONArray.fromObject(json);
        
        // Check if empty
        if (outerArray.length() == 0) {
            return null;
        }
        
        // Get the inner array (the actual data)
        JSONArray dataArray = outerArray.getJSONArray(0);
        
        // Check if inner array is empty
        if (dataArray.length() == 0) {
            return null;
        }
        
        GenTlVisualcontrolchecklist master = new GenTlVisualcontrolchecklist();
        
        // Map array positions to entity fields
        // [0] = VCCL_KEYID
        // [1] = VCCL_FLID
        // [2] = VCCL_EMPLOYEEID
        // [3] = VCCL_DATE (formatted as dd-MON-yyyy)
        // [4] = VCCL_TITLE
        // [5] = VCCL_APPROVEDBY
        // [6] = VCCL_TEMPFIELD3
        // [7] = VCCL_TEMPFIELD4
        // [8] = VCCL_TEMPFIELD5
        // [9] = VCCL_TEMPFIELD6
        // [10] = VCCL_ACTIVE
        // [11] = VCCL_CREATEDBY
        // [12] = VCCL_CREATEDON
        // [13] = VCCL_MODIFIEDON
        
        master.setVcclKeyid(dataArray.getString(0));
        master.setVcclFlid(dataArray.getString(1));
        master.setVcclEmployeeid(dataArray.getString(2));
        master.setVcclDate(dataArray.getString(3));
        master.setVcclTitle(dataArray.getString(4));
        master.setVcclApprovedby(dataArray.getString(5));
        master.setVcclTempfield3(dataArray.getString(6));
        master.setVcclTempfield4(dataArray.getString(7));
        master.setVcclTempfield5(dataArray.getString(8));
        master.setVcclTempfield6(dataArray.getString(9));
        master.setVcclActive(dataArray.getString(10));
        master.setVcclCreatedby(dataArray.getString(11));
        master.setVcclCreatedon(dataArray.getString(12));
        master.setVcclModifiedon(dataArray.getString(13));
        
        return master;
    }
    
    
    //delete record and doesnot if abnormality exsits
    public GenTlVisualcontrolchecklist delete (GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist) throws Exception
    {
    	
    	String keyId = newGenTlVisualcontrolchecklist.getVcclKeyid();
    	
    	 String apiUrl = "/visualcontrol/delete/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
    	          
    	
        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);
        
        CommonMessage.debugMsg("Status: " + res.getStatusCode());
        
        int responseCode = res.getStatusCode();
        if(responseCode == 202) {
        	throw new BusinessApplicationExceptions("ABNEXIST,");
        }

        // Get the response code to confirm insert success, usually 201 Created
       CommonMessage.debugMsg(res.getBody()+"Body");
       String json = res.getBody();
       
       
//     JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
     
    
     return newGenTlVisualcontrolchecklist;
  }
	
	
	
}