package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.JhaTlAuditdtl;
import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAuditparameter;
import com.akranta.tpm.model.JhaTlAudittemplate;
import com.akranta.tpm.model.JhaTlTemplatelevellink;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JHAuditSheetCreationItcServiApi {
    private final Api api;
    
    public JHAuditSheetCreationItcServiApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

    /**
     * Insert or Update JH Audit (Master + Details)
     * This method remains unchanged - it still uses the combined endpoint
     */
    public JhaTlAuditmst insertRecord(JhaTlAuditmst jhaTlAuditmst) throws Exception {
        String apiUrl = "/jhaudit"; // The Spring Boot API endpoint for insert
        jhaTlAuditmst.setJhamActive("Y");
        
        String jsonPayload = insertJson(jhaTlAuditmst);
        CommonMessage.debugMsg("Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        // Get the response code to confirm insert success, usually 201 Created
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        JhaTlAuditmst audit = getJson(jsonResponse);
        CommonMessage.debugMsg(audit);
        
        return audit;
    }

    /**
     * Insert ONLY Master Record
     * New endpoint: POST /jhaudit/master
     * Matches: service.insertRecord(jhaTlAuditmst)
     */
//    public JhaTlAuditmst insertMasterRecord(JhaTlAuditmst jhaTlAuditmst) throws Exception {
//        String apiUrl = "/jhaudit/master";
//        
//        String jsonPayload = jhaTlAuditmst.toJsonManual();
//        CommonMessage.debugMsg("Master Json :: " + jsonPayload);
//        
//        // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//        
//        CommonMessage.debugMsg(res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("Master JSON Response: " + jsonResponse);
//        
//        JhaTlAuditmst master = JhaTlAuditmst.fromJson(jsonResponse);
//        CommonMessage.debugMsg(master);
//        
//        return master;
//    }

    /**
     * Insert ONLY Detail Record
     * New endpoint: POST /jhaudit/detail
     * Matches: service.__insertRecord__(jhaTlAuditdtl)
     */
//    public JhaTlAuditdtl __insertRecord__(JhaTlAuditdtl jhaTlAuditdtl) throws Exception {
//        String apiUrl = "/jhaudit/detail";
//        
//        String jsonPayload = jhaTlAuditdtl.toJsonManual();
//        CommonMessage.debugMsg("Detail Json :: " + jsonPayload);
//        
//        // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//        
//        CommonMessage.debugMsg(res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("Detail JSON Response: " + jsonResponse);
//        
//        JhaTlAuditdtl detail = JhaTlAuditdtl.fromJson(jsonResponse);
//        CommonMessage.debugMsg(detail);
//        
//        return detail;
//    }

    /**
     * Get all audits with details
     */
    public List<JhaTlAuditmst> getAllAudits() throws Exception {
        String apiUrl = "/jhaudit/getAll";
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse JSON array response
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        List<JhaTlAuditmst> auditList = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAuditmst audit = getJson(obj.toString());
            auditList.add(audit);
        }
        
        return auditList;
    }
    
    
    //07-jan

// public JhaTlAuditmst getAuditByKeyid(String keyid) throws Exception {
//     String apiUrl = "/jhaudit/" + keyid;  // This matches your Postman URL
//
//     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//     CommonMessage.debugMsg(res.getStatusCode());
//
//     String jsonResponse = res.getBody();
//     CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//
//     // Parse the response using the existing getJson method
//     // which handles both jhaTlAuditmst and jhaTlAuditdtl
//     JhaTlAuditmst audit = getJson(jsonResponse);
//
//     return audit;
// }
   
    /**
     * Get existing audit using POST + model
     * Method name forced as: getAuditByKeyid
     */
    public JhaTlAuditmst getAuditByKeyid(JhaTlAuditmst filter) throws Exception {

      
        String apiUrl = "/jhaudit/search";
        
        String jsonPayload = compJsonManual(filter);
        CommonMessage.debugMsg("Json :: "+jsonPayload);


        //String jsonBody = filter.toJsonManual();

       // String payload = "{ \"jhaTlAuditmst\": " + jsonBody + " }";

        CommonMessage.debugMsg("POST API URL: " + apiUrl);
        CommonMessage.debugMsg("POST Payload: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

       
        if (jsonResponse == null || jsonResponse.trim().isEmpty()
                || jsonResponse.equalsIgnoreCase("null")) {
            CommonMessage.debugMsg("Empty JSON received, returning null audit");
            return null;
        }

        
        return getJsonSingle(jsonResponse);
    }

    
    

    /**
     * Get audit template grid with optional filters
     * Replicates getjhAuditGridSql logic
     */
    public List<String[]> getAuditTemplateGrid(String templateId, String jhamKeyid) throws Exception {
        StringBuilder apiUrl = new StringBuilder("/jhaudit/template/grid");
        
        boolean hasParams = false;
        if (CommonFunctions.isValidKeyId(templateId)) {
            apiUrl.append("?templateId=").append(templateId);
            hasParams = true;
        }
        
        if (CommonFunctions.isValidKeyId(jhamKeyid)) {
            apiUrl.append(hasParams ? "&" : "?").append("jhamKeyid=").append(jhamKeyid);
        }
        
        CommonMessage.debugMsg("API URL: " + apiUrl.toString());
        
        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse template grid response
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        
        // Define headers for template grid (17 columns)
        String[] headers = {
            "keyid", "reviewptslno", "parametername", "criteriaslno",
            "parameterdescription", "evidence", "maximumpoints",
            "parameterId", "parameterName",
            "pointsscored", "remarks", "detailKeyid",
            "ncremarks", "ncactionplan", "ncactionplanKeyId",
            "ncstatus", "ncclosed"
        };
        
        List<String[]> gridData = convertJsonArrayToListwithheadersorder(jsonArray, headers);
        CommonMessage.debugMsg("List of String Array: " + gridData);
        
        return gridData;
    }

    /**
     * Get templates by masterid
//     */
//    public List<JhaTlAudittemplate> getTemplatesByMasterid(String masterid) throws Exception {
//        String apiUrl = "/jhaudit/template/byMasterid/" + masterid;
//        
//        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//        CommonMessage.debugMsg(res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        // Parse JSON array response
//        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
//        List<JhaTlAudittemplate> templateList = new ArrayList<>();
//        
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            JhaTlAudittemplate template = JhaTlAudittemplate.fromJson(obj.toString());
//            templateList.add(template);
//        }
//        
//        return templateList;
//    }
    
    
    /**
     * Get audit parameters by template ID using query parameter
     * Matches: GET /api/auth/audit/parameters?templateId={templateId}
     */
    public List<String[]> getAuditParametersByTemplateId(String templateId) throws Exception {
        if (!CommonFunctions.isValidKeyId(templateId)) {
            throw new IllegalArgumentException("Template ID is required and must be valid");
        }
        
        //String apiUrl = "/api/audit/parameters?templateId=" + templateId;
        String apiUrl = "/auditparametr/parameters?templateId=" + templateId;
        
        CommonMessage.debugMsg("API URL: " + apiUrl);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse JSON array response
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        
        // Define headers matching the DTO structure
        String[] headers = {
            "jautKeyid", 
            "jautReviewptslno", 
            "paramname", 
            "jautCriteriaslno",
            "paramdesc", 
            "evidence", 
            "maxpoint", 
            "delet"
        };
        
        List<String[]> gridData = convertJsonArrayToListwithheadersorder(jsonArray, headers);
        CommonMessage.debugMsg("List of String Array: " + gridData);
        
        return gridData;
    }
    
    
    //09-jan
    
    public JhaTlAuditparameter getParameterByKeyid(String keyid) throws Exception {
        String apiUrl = "/auditparametr/" + keyid;
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse the response using the fromJson method
        JhaTlAuditparameter parameter = JhaTlAuditparameter.fromJson(jsonResponse);
        
        return parameter;
    }
    
    
//    //crud
//    public JhaTlAudittemplate insertTemplateRecord(JhaTlAudittemplate jhaTlAudittemplate) throws Exception {
//        String apiUrl = "/auditparametr"; // The Spring Boot API endpoint for insert
//        jhaTlAudittemplate.setJautActive("Y");
//        
//        String jsonPayload = insertJson(jhaTlAudittemplate);
//        CommonMessage.debugMsg("Json :: " + jsonPayload);
//        
//        // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//        
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        JhaTlAudittemplate audit = getJson1(jsonResponse);
//        CommonMessage.debugMsg(audit);
//        
//        return audit;
//    }
//    
//    
//    public JhaTlAudittemplate getJson1(String json) {
//        JSONObject jsonObj = JSONObject.fromObject(json);
//        JSONObject templateObj = jsonObj.getJSONObject("jhaTlAudittemplate");
//
//        JhaTlAudittemplate template = JhaTlAudittemplate.fromJson(templateObj.toString());
//
//        return template;
//    }
//    
//    public String insertJson(JhaTlAudittemplate jhaTlAudittemplate) {
//        StringBuilder str = new StringBuilder();
//
//        str.append("{");
//        str.append("\"jhaTlAudittemplate\":");
//        String jsonMst = jhaTlAudittemplate.toJsonManual();
//        str.append(jsonMst);
//        str.append("}");
//
//        return str.toString();
//    }

    
    
    
//    public JhaTlAuditparameter insertParameterRecord(JhaTlAuditparameter jhaTlAuditparameter) throws Exception {
//        String apiUrl = "/auditparametr"; // The Spring Boot API endpoint for insert
//        // Set any required fields
//        // jhaTlAuditparameter.setActive("Y"); // uncomment if needed
//
//        String jsonPayload = insertParameterJson(jhaTlAuditparameter);
//        CommonMessage.debugMsg("Json :: " + jsonPayload);
//
//        // Send request
//        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//
//        // Get the response code to confirm insert success, usually 201 Created
//        CommonMessage.debugMsg(res.getStatusCode());
//
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//
//        JhaTlAuditparameter audit = getParameterJson(jsonResponse);
//        CommonMessage.debugMsg(audit);
//
//        return audit;
//    }
    
    public JhaTlAuditparameter insertParameterRecord(JhaTlAuditparameter jhaTlAuditparameter) throws Exception {
        String apiUrl = "/jhaudit/parameter";
        
        String jsonPayload = insertParameterJson(jhaTlAuditparameter);
        
        CommonMessage.debugMsg("Json :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: [" + jsonResponse + "]");
        
        // Check for error status codes
        if (statusCode >= 400) {
            String errorMsg = "API request failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        // Check if response is empty
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new RuntimeException("API returned empty response with status " + statusCode);
        }

        JhaTlAuditparameter audit = getParameterJson(jsonResponse);
        CommonMessage.debugMsg(audit);

        return audit;
    }

//    public String insertParameterJson(JhaTlAuditparameter jhaTlAuditparameter) {
//        StringBuilder str = new StringBuilder();
//        str.append("{");
//        str.append("\"jhaTlAuditparameter\":");
//        String jsonMst = jhaTlAuditparameter.toJsonManual();
//        str.append(jsonMst);
//        str.append("}");
//        return str.toString();
//    }
    
    public String insertParameterJson(JhaTlAuditparameter jhaTlAuditparameter) 
	{
		StringBuilder str = new StringBuilder();
		
		JhaTlTemplatelevellink link = new JhaTlTemplatelevellink();
		JhaTlAudittemplate template = new JhaTlAudittemplate();
		
		//Master Details
		str.append("{");
		
		str.append("\"jhatlauditparameter\":");  
		String JsonMst = jhaTlAuditparameter.toJsonManual();
		str.append(JsonMst);
		str.append(",");
		
		//Detail Table Values
		List<JhaTlTemplatelevellink> details = jhaTlAuditparameter.getJhLevelGrid();
		str.append("\"jhatltemplatelevellink\":");
		String JsonDtl = JhaTlTemplatelevellink.toJsonManualList(details);
		str.append(JsonDtl);
		str.append(",");
		
		List<JhaTlAudittemplate> attendances = jhaTlAuditparameter.getJhAuditTemplate();
		str.append("\"jhatlaudittemplate\":");
		String Jsonatt = JhaTlAudittemplate.toJsonManualList(attendances);
		str.append(Jsonatt);
		
		str.append("}");
		
		return str.toString();
        
		
	}
    
    
    public JhaTlAuditparameter getParameterJson(String json) {
        CommonMessage.debugMsg("Response to parse: [" + json + "]");
        
        // Check if response is empty or invalid
        if (json == null || json.trim().isEmpty()) {
            throw new RuntimeException("Empty response from API - check server logs for errors");
        }
        
        // Check if response starts with valid JSON
        if (!json.trim().startsWith("{")) {
            throw new RuntimeException("Invalid JSON response: " + json);
        }
        
        try {
            JSONObject jsonObj = JSONObject.fromObject(json);
            
            // Try lowercase key first (API returns lowercase), then mixed case
            String parameterKey = null;
            if (jsonObj.has("jhatlauditparameter")) {
                parameterKey = "jhatlauditparameter";
            } else if (jsonObj.has("jhaTlAuditparameter")) {
                parameterKey = "jhaTlAuditparameter";
            } else {
                throw new RuntimeException("Response does not contain 'jhatlauditparameter' or 'jhaTlAuditparameter' key: " + json);
            }
            
            // Parse main parameter object
            JSONObject parameterObj = jsonObj.getJSONObject(parameterKey);
            JhaTlAuditparameter parameter = JhaTlAuditparameter.fromJson(parameterObj.toString());
            
            // Parse level links if present
            String levelLinkKey = null;
            if (jsonObj.has("jhatltemplatelevellink")) {
                levelLinkKey = "jhatltemplatelevellink";
            } else if (jsonObj.has("jhaTlTemplatelevellink")) {
                levelLinkKey = "jhaTlTemplatelevellink";
            }
            
            if (levelLinkKey != null && jsonObj.has(levelLinkKey)) {
                JSONArray levelLinkArray = jsonObj.getJSONArray(levelLinkKey);
                List<JhaTlTemplatelevellink> levelLinks = JhaTlTemplatelevellink.fromJsonList(levelLinkArray.toString());
                parameter.setJhLevelGrid(levelLinks);
            }
            
            // Parse audit templates if present
            String templateKey = null;
            if (jsonObj.has("jhatlaudittemplate")) {
                templateKey = "jhatlaudittemplate";
            } else if (jsonObj.has("jhaTlAudittemplate")) {
                templateKey = "jhaTlAudittemplate";
            }
            
            if (templateKey != null && jsonObj.has(templateKey)) {
                JSONArray templateArray = jsonObj.getJSONArray(templateKey);
                List<JhaTlAudittemplate> templates = JhaTlAudittemplate.fromJsonList(templateArray.toString());
                parameter.setJhAuditTemplate(templates);
            }
            
            return parameter;
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            throw new RuntimeException("Failed to parse parameter response: " + e.getMessage(), e);
        }
    }
    
 
    
    
//    public List<String[]> getAuditLevels(String templateId, String jhStepId) throws Exception {
//
//        StringBuilder apiUrl = new StringBuilder("/auditparametr/auditlevels");
//
//        boolean hasParam = false;
//
//        if (CommonFunctions.isValidKeyId(templateId)) {
//            apiUrl.append("?templateId=").append(templateId);
//            hasParam = true;
//        }
//
//        if (CommonFunctions.isValidKeyId(jhStepId)) {
//            apiUrl.append(hasParam ? "&" : "?")
//                  .append("jhStepId=").append(jhStepId);
//        }
//
//        CommonMessage.debugMsg("API URL :: " + apiUrl);
//
//        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//        CommonMessage.debugMsg("Status Code :: " + res.getStatusCode());
//
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response :: " + jsonResponse);
//
//        // Parse response
//        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
//
//        // Adjust headers to match service.getAuditLevels() response keys
//        String[] headers = {
//            "auditlevelid",
//            "auditlevelcode",
//            "auditlevelname",
//            "minpoints",
//            "maxpoints",
//            "stepid"
//        };
//
//        return convertJsonArrayToListwithheadersorder(jsonArray, headers);
//    }

//    public List<String[]> getAuditLevels(String templateId, String jhStepId) throws Exception {
//
//        StringBuilder apiUrl = new StringBuilder("/auditparametr/auditlevels");
//
//        boolean hasParam = false;
//
//        if (CommonFunctions.isValidKeyId(templateId)) {
//            apiUrl.append("?templateId=").append(templateId);
//            hasParam = true;
//        }
//
//        if (CommonFunctions.isValidKeyId(jhStepId)) {
//            apiUrl.append(hasParam ? "&" : "?")
//                  .append("jhStepId=").append(jhStepId);
//        }
//
//        CommonMessage.debugMsg("API URL :: " + apiUrl);
//
//        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//        CommonMessage.debugMsg("Status Code :: " + res.getStatusCode());
//
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response :: " + jsonResponse);
//
//        // Parse response
//        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
//
//        // ✅ FIX: Match the actual JSON response keys (lowercase)
//        String[] headers = {
//            "auditlevelid",      // Column 0 - The ID (JHT02, JHT03...)
//            "templateid",        // Column 1 - Template ID
//            "minimumpoints",     // Column 2 - Minimum points
//            "active",            // Column 3 - Active status
//            "createdon",         // Column 4 - Created date
//            "modifiedon"         // Column 5 - Modified date
//        };
//
//        return convertJsonArrayToListwithheadersorder(jsonArray, headers);
//    }
    
 // In JHAuditSheetCreationItcServiApi.java
 // Replace the existing getAuditLevels method with this:

 public JhaTlTemplatelevellink getAuditLevels(String templateId, String flId, String jhStepId) throws Exception {

     StringBuilder apiUrl = new StringBuilder("/auditparametr/auditlevels");

     boolean hasParam = false;

     if (CommonFunctions.isValidKeyId(templateId)) {
         apiUrl.append("?templateId=").append(templateId);
         hasParam = true;
     }

     if (CommonFunctions.isValidKeyId(jhStepId)) {
         apiUrl.append(hasParam ? "&" : "?")
               .append("jhStepId=").append(jhStepId);
     }

     CommonMessage.debugMsg("API URL :: " + apiUrl);

     HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
     CommonMessage.debugMsg("Status Code :: " + res.getStatusCode());

     String jsonResponse = res.getBody();
     CommonMessage.debugMsg("JSON Response :: " + jsonResponse);

     // Parse response
     //JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
     
     List<JhaTlTemplatelevellink> resultList = JhaTlTemplatelevellink.fromJsonList(jsonResponse);
     return resultList.get(0);

   
 }
 
 
 /**
  * Get minimum marks/points for a specific audit level and template
  * @param auditLevel The audit level ID (e.g., JHT02)
  * @param auditTemplate The template ID (e.g., JHP0000024)
  * @return The minimum points as Integer
  */
 public Integer getMinimumMarks(String auditLevel, String auditTemplate) throws Exception {
     
     if (auditLevel == null || auditTemplate == null) {
         return 0;
     }
     
     StringBuilder apiUrl = new StringBuilder("/auditparametr/minimummarks");
     apiUrl.append("?auditLevel=").append(auditLevel);
     apiUrl.append("&auditTemplate=").append(auditTemplate);
     
     CommonMessage.debugMsg("Min Marks API URL: " + apiUrl);
     
     HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
     CommonMessage.debugMsg("Min Marks Status Code: " + res.getStatusCode());
     
     String jsonResponse = res.getBody();
     CommonMessage.debugMsg("Min Marks Response: " + jsonResponse);
     
     if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
         return 0;
     }
     
     try {
         return Integer.parseInt(jsonResponse.trim());
     } catch (NumberFormatException e) {
         System.err.println("Error parsing min marks: " + e.getMessage());
         e.printStackTrace();
         return 0;
     }
 }
 
 
 public Integer getMinimumPoints(String auditLevel, String auditTemplate) throws Exception {

	    if (auditLevel == null || auditTemplate == null) {
	        return 0;
	    }

	    StringBuilder apiUrl = new StringBuilder("/jhaudit/minimumpoints");
	    apiUrl.append("?auditLevel=").append(auditLevel);
	    apiUrl.append("&auditTemplate=").append(auditTemplate);

	    CommonMessage.debugMsg("Min Points API URL: " + apiUrl);

	    HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
	    CommonMessage.debugMsg("Min Points Status Code: " + res.getStatusCode());

	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("Min Points Response: " + jsonResponse);

	    if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
	        return 0;
	    }

	    try {
	        return Integer.parseInt(jsonResponse.trim());
	    } catch (NumberFormatException e) {
	        System.err.println("Error parsing min points: " + e.getMessage());
	        e.printStackTrace();
	        return 0;
	    }
	}
 
 
// /**
//  * Get minimum marks/points for a specific audit level and template
//  * @param auditLevel The audit level ID (e.g., JHT02)
//  * @param auditTemplate The template ID (e.g., JHP0000024)
//  * @return The minimum points as Integer
//  */
// public Integer getMinimumMarks(String auditLevel, String auditTemplate) throws Exception {
//     StringBuilder apiUrl = new StringBuilder("/auditparametr/minimummarks");
//     
//     apiUrl.append("?auditLevel=").append(auditLevel);
//     apiUrl.append("&auditTemplate=").append(auditTemplate);
//     
//     CommonMessage.debugMsg("Min Marks API URL: " + apiUrl);
//     
//     HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//     CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
//     
//     String jsonResponse = res.getBody();
//     CommonMessage.debugMsg("Min Marks Response: " + jsonResponse);
//     
//     if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
//         return 0;
//     }
//     
//     try {
//         return Integer.parseInt(jsonResponse.trim());
//     } catch (NumberFormatException e) {
//         System.err.println("Error parsing min marks: " + e.getMessage());
//         return 0;
//     }
// }
    
    
    /**
     * Get existing audit record by parameters
     * Matches: GET /existing?templateId=...&flId=...&date=...&auditType=...&stepId=...
     */
//    public JhaTlAuditmst getExistingAudit(String templateId, String flId, String date, 
//                                          String auditType, String stepId) throws Exception {
//        StringBuilder apiUrl = new StringBuilder("/jhaudit/existing");
//        
//        // Build query parameters
//        apiUrl.append("?templateId=").append(templateId);
//        apiUrl.append("&flId=").append(flId);
//        
//        // Add date parameter only if valid
//        if (date != null && !date.equalsIgnoreCase("null") && !date.isEmpty()) {
//            apiUrl.append("&date=").append(date);
//        }
//        
//        apiUrl.append("&auditType=").append(auditType);
//        apiUrl.append("&stepId=").append(stepId);
//        
//        CommonMessage.debugMsg("API URL: " + apiUrl.toString());
//        
//        // Make GET request
//        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        // Handle empty response or no data found
//        if (jsonResponse == null || jsonResponse.trim().isEmpty() || 
//            jsonResponse.equals("null")) {
//            return null;
//        }
//        
//        // Parse the response using existing getJson method
//        JhaTlAuditmst audit = getJson(jsonResponse);
//        CommonMessage.debugMsg("Audit Result: " + audit);
//        
//        return audit;
//    }

    /**
     * Get existing audit record by parameters
     * Matches: GET /api/jhaudit/existing?templateId=...&flId=...&date=...&auditType=...&stepId=...
     */
//    public JhaTlAuditmst getExistingAudit(String templateId, String flId, String date,
//                                          String auditType, String stepId) throws Exception {
//        StringBuilder apiUrl = new StringBuilder("/jhaudit/existing");  // ✅ Add /api prefix
//
//        // Build query parameters
//        apiUrl.append("?templateId=").append(templateId);
//        apiUrl.append("&flId=").append(flId);
//
//        // Add date parameter only if valid
//        if (date != null && !date.equalsIgnoreCase("null") && !date.isEmpty()) {
//            apiUrl.append("&date=").append(date);
//        }
//
//        apiUrl.append("&auditType=").append(auditType);
//        
//        // Handle stepId - don't add if empty or "-"
//        if (stepId != null && !stepId.equals("-") && !stepId.trim().isEmpty()) {
//            apiUrl.append("&stepId=").append(stepId);
//        } else {
//            apiUrl.append("&stepId=");  // Send empty stepId
//        }
//
//        CommonMessage.debugMsg("API URL: " + apiUrl.toString());
//
//        // Make GET request
//        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
//        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
//
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//
//        // Handle empty response or no data found
//        if (jsonResponse == null || jsonResponse.trim().isEmpty() ||
//            jsonResponse.equals("null")) {
//            return null;
//        }
//
//        // Parse the response using existing getJson method
//        JhaTlAuditmst audit = getJson(jsonResponse);
//        CommonMessage.debugMsg("Audit Result: " + audit);
//
//        return audit;
//    }

    
    public JhaTlAuditmst getExistingAudit(String templateId, String flId, String date,
            String auditType, String stepId) throws Exception {
StringBuilder apiUrl = new StringBuilder("/jhaudit/existing");

apiUrl.append("?templateId=").append(templateId);
apiUrl.append("&flId=").append(flId);

if (date != null && !date.equalsIgnoreCase("null") && !date.isEmpty()) {
apiUrl.append("&date=").append(date);
}

apiUrl.append("&auditType=").append(auditType);
apiUrl.append("&stepId=").append(stepId != null ? stepId : "");

CommonMessage.debugMsg("API URL: " + apiUrl.toString());

HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
CommonMessage.debugMsg("Status Code: " + res.getStatusCode());

String jsonResponse = res.getBody();
CommonMessage.debugMsg("JSON Response: " + jsonResponse);

if (jsonResponse == null || jsonResponse.trim().isEmpty() ||
jsonResponse.equals("null")) {
return null;
}

// Use the new parsing method that handles both formats
JhaTlAuditmst audit = getJsonSingle(jsonResponse);  // ✅ Changed here
CommonMessage.debugMsg("Audit Result: " + audit);

return audit;
}
    
    
    
   
    /**
     * Delete audit template by parameter ID
     * Matches: DELETE /api/auditparametr/template/{parameterId}
     */
    public void deleteAuditTemplate(String parameterId) throws Exception {
        if (parameterId == null || parameterId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parameter ID is required");
        }
        
        String apiUrl = "/jhaudit/templatedelete/" + parameterId;
        
        CommonMessage.debugMsg("Delete API URL: " + apiUrl);
        
        // Make DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Delete Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("Delete Response: " + jsonResponse);
        
        // Check for error status codes
        if (statusCode >= 400) {
            String errorMsg = "Delete failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        CommonMessage.debugMsg("✅ Successfully deleted audit template: " + parameterId);
    }
    
    
//    /**
//     * Get count of audits by flId
//     * Matches: GET /api/jhaudit/count?flId={flId}
//     */
//    public Long getAuditCount(String flId) throws Exception {
//        if (flId == null || flId.trim().isEmpty()) {
//            throw new IllegalArgumentException("flId is required");
//        }
//        
//        String apiUrl = "/jhaudit/count?flId=" + flId;
//        
//        CommonMessage.debugMsg("API URL: " + apiUrl);
//        
//        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//        
//        int statusCode = res.getStatusCode();
//        CommonMessage.debugMsg("Status Code: " + statusCode);
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        if (statusCode >= 400) {
//            throw new RuntimeException("API request failed with status " + statusCode);
//        }
//        
//        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
//            return 0L;
//        }
//        
//        // Parse the count from response
//        Long count = Long.parseLong(jsonResponse.trim());
//        
//        CommonMessage.debugMsg("Audit Count: " + count);
//        
//        return count;
//    }
    
    
    /**
     * Get count of audits by flId
     * Matches: GET /api/jhaudit/count?flId={flId}
     */
    public Long getAuditCount(String flId) throws Exception {
        try {
            // Validate input
            if (flId == null || flId.trim().isEmpty()) {
                throw new IllegalArgumentException("flId is required and cannot be empty");
            }
            
            // Build API URL
            String apiUrl = "/jhaudit/count?flId=" + flId;
            
            CommonMessage.debugMsg("API URL: " + apiUrl);
            
            // Make GET request
            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
            
            int statusCode = res.getStatusCode();
            CommonMessage.debugMsg("Status Code: " + statusCode);
            
            String jsonResponse = res.getBody();
            CommonMessage.debugMsg("JSON Response: " + jsonResponse);
            
            // Check for error status codes
            if (statusCode >= 400) {
                String errorMsg = "API request failed with status " + statusCode;
                if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                    errorMsg += ": " + jsonResponse;
                }
                throw new RuntimeException(errorMsg);
            }
            
            // Handle empty response
            if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
                CommonMessage.debugMsg("Empty response, returning count as 0");
                return 0L;
            }
            
            // Parse the count from response
            Long count = Long.parseLong(jsonResponse.trim());
            
            CommonMessage.debugMsg("Audit Count for flId '" + flId + "': " + count);
            
            return count;
            
        } catch (NumberFormatException e) {
            System.err.println("Error parsing count response: " + e.getMessage());
            throw new RuntimeException("Invalid count response format: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Error getting audit count: " + e.getMessage());
            throw new RuntimeException("Failed to get audit count: " + e.getMessage(), e);
        }
    }
    
    
    
    //13-jan
    
    public String getUnassignedAuditTeamsCount(String flid) throws Exception {
        if (flid == null || flid.trim().isEmpty()) {
            throw new IllegalArgumentException("flid is required and cannot be empty");
        }
        
        // Updated endpoint URL
        String apiUrl = "/jhaudit/unassignedcount?flid=" + flid;
        
        CommonMessage.debugMsg("API URL: " + apiUrl);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        if (statusCode >= 400) {
            String errorMsg = "API request failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
            CommonMessage.debugMsg("Empty response, returning count as 0");
            return "0";
        }
        
        String count = jsonResponse.trim();
        CommonMessage.debugMsg("Unassigned Audit Teams Count for flId '" + flid + "': " + count);
        return count;
    }
    
    
    /**
     * Get current audit level for a template
     * Matches: SELECT JHSM_KEYID FROM GEN_TL_JHSTEPMST WHERE...
     * 
     * @param jhTemplateId Template/Facility ID
     * @return Step Master Key ID
     * @throws Exception if API call fails
     */
    public String getAuditLevelCurrent(String jhTemplateId) throws Exception {
//        if (jhTemplateId == null || jhTemplateId.trim().isEmpty()) {
//            throw new IllegalArgumentException("jhTemplateId is required and cannot be empty");
//        }
        
        String apiUrl = "/jhaudit/auditlevel/current?jhTemplateId=" + jhTemplateId;
        
        CommonMessage.debugMsg("API URL: " + apiUrl);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        int statusCode = res.getStatusCode();
        CommonMessage.debugMsg("Status Code: " + statusCode);
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        if (statusCode >= 400) {
            String errorMsg = "API request failed with status " + statusCode;
            if (jsonResponse != null && !jsonResponse.trim().isEmpty()) {
                errorMsg += ": " + jsonResponse;
            }
            throw new RuntimeException(errorMsg);
        }
        
        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.equals("null")) {
            CommonMessage.debugMsg("Empty response, returning null");
            return null;
        }
        
        String keyId = jsonResponse.trim();
        // Remove quotes if JSON string
        if (keyId.startsWith("\"") && keyId.endsWith("\"")) {
            keyId = keyId.substring(1, keyId.length() - 1);
        }
        
        CommonMessage.debugMsg("Audit Level Current KeyId for jhTemplateId '" + jhTemplateId + "': " + keyId);
        return keyId;
    }
    
    
//    
//    public JhaTlAuditparameter getParameterJson(String json) {
//        CommonMessage.debugMsg("Response to parse: [" + json + "]");
//        
//        // Check if response is empty or invalid
//        if (json == null || json.trim().isEmpty()) {
//            throw new RuntimeException("Empty response from API - check server logs for errors");
//        }
//        
//        // Check if response starts with valid JSON
//        if (!json.trim().startsWith("{")) {
//            throw new RuntimeException("Invalid JSON response: " + json);
//        }
//        
//        try {
//            JSONObject jsonObj = JSONObject.fromObject(json);
//            
//            // Check if the expected wrapper exists
//            if (!jsonObj.has("jhaTlAuditparameter")) {
//                throw new RuntimeException("Response does not contain 'jhaTlAuditparameter' key: " + json);
//            }
//            
//            JSONObject parameterObj = jsonObj.getJSONObject("jhaTlAuditparameter");
//            JhaTlAuditparameter parameter = JhaTlAuditparameter.fromJson(parameterObj.toString());
//            return parameter;
//        } catch (Exception e) {
//            System.err.println("Error parsing JSON: " + e.getMessage());
//            throw new RuntimeException("Failed to parse parameter response: " + e.getMessage(), e);
//        }
//    }
    /**
     * Get audit parameters by template ID using path variable
     * Matches: GET /api/auth/audit/parameters/{templateId}
     */
//    public List<String[]> getAuditParametersByPath(String templateId) throws Exception {
//        if (!CommonFunctions.isValidKeyId(templateId)) {
//            throw new IllegalArgumentException("Template ID is required and must be valid");
//        }
//        
//        String apiUrl = "/api/auditparametr/parameters/" + templateId;
//        
//        CommonMessage.debugMsg("API URL: " + apiUrl);
//        
//        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        // Parse JSON array response
//        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
//        
//        // Define headers matching the DTO structure
//        String[] headers = {
//            "jautKeyid", 
//            "jautReviewptslno", 
//            "paramname", 
//            "jautCriteriaslno",
//            "paramdesc", 
//            "evidence", 
//            "maxpoint", 
//            "delet"
//        };
//        
//        List<String[]> gridData = convertJsonArrayToListwithheadersorder(jsonArray, headers);
//        CommonMessage.debugMsg("List of String Array: " + gridData);
//        
//        return gridData;
//    }

    /**
     * Get audit parameters as raw JSON string (optional alternative)
     */
//    public String getAuditParametersAsJson(String templateId) throws Exception {
//        if (!CommonFunctions.isValidKeyId(templateId)) {
//            throw new IllegalArgumentException("Template ID is required and must be valid");
//        }
//        
//        String apiUrl = "/api/auditparametr/parameters?templateId=" + templateId;
//        
//        CommonMessage.debugMsg("API URL: " + apiUrl);
//        
//        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
//        CommonMessage.debugMsg("Status Code: " + res.getStatusCode());
//        
//        String jsonResponse = res.getBody();
//        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
//        
//        return jsonResponse;
//    }  
    
    /**
     * Parse single JhaTlAuditmst object from flat JSON (without wrapper)
     */
    public JhaTlAuditmst getJsonSingle(String json) {
        try {
            if (json == null || json.trim().isEmpty() || json.equals("null")) {
                return null;
            }
            
            JSONObject jsonObj = JSONObject.fromObject(json);
            
            // Check if it's wrapped or flat
            if (jsonObj.has("jhaTlAuditmst")) {
                // Wrapped format - use existing logic
                return getJson(json);
            } else {
                // Flat format - parse directly
                JhaTlAuditmst mst = JhaTlAuditmst.fromJson(json);
                return mst;
            }
            
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            throw new RuntimeException("Failed to parse audit response: " + e.getMessage(), e);
        }
    }
    

    /**
     * Convert JhaTlAuditmst object to JSON string (Master + Details)
     */
    public String insertJson(JhaTlAuditmst jhaTlAuditmst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"jhaTlAuditmst\":");
        String jsonMst = jhaTlAuditmst.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values
        List<JhaTlAuditdtl> details = jhaTlAuditmst.getAuditDtl();
        str.append("\"jhaTlAuditdtl\":");
        String jsonDtl = JhaTlAuditdtl.toJsonManualList(details);
        str.append(jsonDtl);
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Convert JSON response to JhaTlAuditmst object
     */
    public JhaTlAuditmst getJson(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        JSONObject mstObj = jsonObj.getJSONObject("jhaTlAuditmst");
        JSONArray dtlArray = jsonObj.getJSONArray("jhaTlAuditdtl");
        
        JhaTlAuditmst mst = JhaTlAuditmst.fromJson(mstObj.toString());
        List<JhaTlAuditdtl> dtl = JhaTlAuditdtl.fromJsonList(dtlArray.toString());
        
        mst.setAuditDtl(dtl);
        
        return mst;
    }
    
    
//    public JhaTlAuditmst getJson(String json) {
//
//        // ✅ VERY IMPORTANT SAFETY CHECK
//        if (json == null || json.trim().isEmpty()) {
//            CommonMessage.debugMsg("Empty JSON received, returning null audit");
//            return null;
//        }
//
//        // ✅ JSON must start with '{'
//        if (!json.trim().startsWith("{")) {
//            CommonMessage.debugMsg("Invalid JSON received: " + json);
//            return null;
//        }
//
//        JSONObject jsonObj = JSONObject.fromObject(json);
//
//        JSONObject mstObj = jsonObj.optJSONObject("jhaTlAuditmst");
//        if (mstObj == null) {
//            return null;
//        }
//
//        JhaTlAuditmst mst = JhaTlAuditmst.fromJson(mstObj.toString());
//
//        JSONArray dtlArray = jsonObj.optJSONArray("jhaTlAuditdtl");
//        if (dtlArray != null) {
//            List<JhaTlAuditdtl> dtl = JhaTlAuditdtl.fromJsonList(dtlArray.toString());
//            mst.setAuditDtl(dtl);
//        }
//
//        return mst;
//    }


    /**
     * Generic method to convert JSON array to List<String[]>
     */
    public List<String[]> convertJsonArrayToList(JSONArray jsonArray) {
        List<String[]> list = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            
            // Get all keys (dynamic, no hardcoding)
            Iterator<String> keys = obj.keys();
            List<String> row = new ArrayList<>();
            
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = obj.opt(key);
                row.add(value != null ? value.toString() : "");
            }
            
            list.add(row.toArray(new String[0]));
        }
        
        return list;
    }

    /**
     * Convert JSON array to List with specific header order
     */
    public List<String[]> convertJsonArrayToListwithheadersorder(JSONArray jsonArray, String[] headers) {
        List<String[]> list = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            
            String[] row = new String[headers.length];
            for (int j = 0; j < headers.length; j++) {
                Object val = obj.opt(headers[j]);
                row[j] = (val == null || "null".equals(val.toString())) ? "" : val.toString();
            }
            list.add(row);
        }
        
        for (String[] row : list) {
            CommonMessage.debugMsg(java.util.Arrays.toString(row));
        }
        
        return list;
    }

    /**
     * Convert JSON array to List with column order mapping
     */
    public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray, String[] headers) {
        List<String[]> list = new ArrayList<>();
        
        if (jsonArray.length() < 3) {
            throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
        }
        
        // Row 3 = column order mapping
        JSONObject orderObj = jsonArray.getJSONObject(2);
        
        // Build ordered list of keys based on orderObj values
        List<Map.Entry<String, Integer>> orderList = new ArrayList<>();
        Iterator<String> it = orderObj.keys();
        while (it.hasNext()) {
            String key = it.next();
            try {
                int order = Integer.parseInt(orderObj.getString(key));
                orderList.add(new AbstractMap.SimpleEntry<>(key, order));
            } catch (NumberFormatException e) {
                orderList.add(new AbstractMap.SimpleEntry<>(key, Integer.MAX_VALUE));
            }
        }
        
        // Sort keys by numeric order
        orderList.sort(Comparator.comparingInt(Map.Entry::getValue));
        
        // Process each row
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            String[] row = new String[orderList.size()];
            
            int colIndex = 0;
            for (Map.Entry<String, Integer> entry : orderList) {
                String key = entry.getKey();
                Object value = obj.opt(key);
                row[colIndex++] = value != null ? value.toString() : "";
            }
            
            list.add(row);
        }
        
        // Debug
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }
        
        return list;
    }

    
    public String compJsonManual(JhaTlAuditmst jhaTlAuditmst) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");
	    sb.append("\"keyId\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamKeyid())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamKeyid()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    sb.append(",\"flId\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamFlid())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamFlid()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"auditTeamId\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamAuditteamid())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamAuditteamid()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"auditType\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamAudittype())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamAudittype()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"auditPillar\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamAuditpillar())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamAuditpillar()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"jhStepId\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamJhstepid())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamJhstepid()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"auditDate\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamAuditdate())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamAuditdate()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append(",\"auditorType\":");
	    if(CommonFunctions.isValidKeyId(jhaTlAuditmst.getJhamAuditortype())) {
	    	sb.append("\"").append(jhaTlAuditmst.getJhamAuditortype()).append("\"");
	    }else {
	    	sb.append("null");
	    }
	    
	    sb.append("}");
	    return sb.toString();
	}
    
    //delete the records in detail and master
    
    
//    public String delete (JhaTlAuditmst jhaTlAuditmst) {
//        StringBuilder str = new StringBuilder();
//        
//        str.append("{");
//        
//        // Master Details
//        str.append("\"jhaTlAuditmst\":");
//        String jsonMst = jhaTlAuditmst.toJsonManual();
//        str.append(jsonMst);
//        str.append(",");
//        
//        // Detail Table Values
//        List<JhaTlAuditdtl> details = jhaTlAuditmst.getAuditDtl();
//        str.append("\"jhaTlAuditdtl\":");
//        String jsonDtl = JhaTlAuditdtl.toJsonManualList(details);
//        str.append(jsonDtl);
//        
//        str.append("}");
//        
//        return str.toString();
//    }
    
//    public JhaTlAuditmst delete (JhaTlAuditmst newJhaTlAuditmst) throws IOException
//    {
//    	
//    	String keyId = newJhaTlAuditmst.getJhamKeyid();
//    	
//    	 String apiUrl = "/jhaudit/delete/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
//    	          
//    	
//        HttpResponse res = api.makeAuthRequest(apiUrl,"DELETE", null);
//
//        // Get the response code to confirm insert success, usually 201 Created
//       CommonMessage.debugMsg(res.getBody()+"Body");
//       String json = res.getBody();
//       
//    //   JhaTlVisualsopdtl dtl = JhaTlVisualsopdtl.fromJson(json);
//       
//      
//       return newJhaTlAuditmst;
//    }
	
    
    public JhaTlAuditmst delete(JhaTlAuditmst newJhaTlAuditmst) throws Exception {
        
    	String keyId = newJhaTlAuditmst.getJhamKeyid();
    	String apiUrl = "/jhaudit/delete/"+URLEncoder.encode(keyId, StandardCharsets.UTF_8);
        String jsonPayload = insertJson(newJhaTlAuditmst);
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", jsonPayload);
        
        if (res.getStatusCode() == 200) {
            return newJhaTlAuditmst;
        } else {
            throw new Exception("Delete failed: " + res.getBody());
        }
    }


}