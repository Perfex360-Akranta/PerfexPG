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

import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
//mport com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Service API class to call the Spring Boot Why-Why analysis REST endpoint.
 * Endpoint (BAL_BdmTlWhywhyController, Spring Boot side):
 *   POST /bdm/whywhy/save   handles BOTH insert and update
 *
 * JSON conversion lives in the models:
 *   BAL_BdmTlWhywhymst.toJsonManual() / fromJson(json)
 *   BAL_BdmTlWhywhydtl.toJsonManualList(list) / fromJsonList(json)
 */
public class BAL_BdmTlWhywhyServiceApi {

   
    private static final String SAVE_URL = "/bdm/whywhy/save";
    private static final String REF_KEYID_URL = "/bdm/whywhy/refkeyid/";
    private static final String MASTER_URL = "/bdm/whywhy/master/";


    private final Api api;

    public BAL_BdmTlWhywhyServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }
    

    // =========================================================================
    //  SAVE  -  INSERT when master keyid is blank, UPDATE when keyid is present
    // =========================================================================
    /**
     * @param bdmTlWhywhymst master data with its detail list (getBdmTlWhywhydtl())
     * @return saved master (generated YY key, defaults applied) with the saved details
     */
    public BAL_BdmTlWhywhymst saveWhyWhy(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws Exception {
        try {
            String jsonPayload = buildSaveJson(bdmTlWhywhymst);
            CommonMessage.debugMsg("Why Why Save JSON :: " + jsonPayload);

            HttpResponse res = api.makeAuthRequest(SAVE_URL, "POST", jsonPayload);
            CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            CommonMessage.debugMsg("JSON Response: " + jsonResponse);

            if (res.getStatusCode() == 404) {
                throw new Exception("Why Why analysis not found for keyid: " + bdmTlWhywhymst.getWwmsKeyid());
            }
            // Spring Boot controller returns 201 (CREATED) for insert, 200 (OK) for update
            if (res.getStatusCode() != 201 && res.getStatusCode() != 200) {
                throw new Exception("Failed to save Why Why analysis. Status: " + res.getStatusCode()
                        + " | Body: " + jsonResponse);
            }

            BAL_BdmTlWhywhymst result = parseResponse(jsonResponse);
            result.setElementid(bdmTlWhywhymst.getElementid());
            CommonMessage.debugMsg("Parsed Result keyid: " + result.getWwmsKeyid());

            return result;

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to save Why Why analysis: " + e.getMessage(), e);
        }
    }

    // =========================================================================
    //  PRIVATE HELPERS
    // =========================================================================

    /**
     * Build JSON payload:  {"master":{...},"details":[...],"elementId":"..."}
     * master  -> BAL_BdmTlWhywhymst.toJsonManual()
     * details -> BAL_BdmTlWhywhydtl.toJsonManualList(...)
     */
    private String buildSaveJson(BAL_BdmTlWhywhymst mst) {
        StringBuilder str = new StringBuilder();

        str.append("{");

        // Master
        str.append("\"master\":");
        str.append(mst.toJsonManual());
        str.append(",");

        // Details
        str.append("\"details\":");
        str.append(BAL_BdmTlWhywhydtl.toJsonManualList(mst.getBdmTlWhywhydtl()));

        // Element id -> the API uses it to build the master sequence identifier
        String elementId = mst.getElementid();
        if (elementId != null && !elementId.trim().isEmpty() && !"{}".equals(elementId.trim())) {
            str.append(",\"elementId\":\"")
               .append(elementId.trim().replace("\\", "\\\\").replace("\"", "\\\""))
               .append("\"");
        }

        // Optional: form metadata
        // str.append(",\"formActionMode\":\"SAVE\"");
        // str.append(",\"formMode\":\"INSERT\"");

        str.append("}");

        CommonMessage.debugMsg("Why Why Save JSON: " + str);
        return str.toString();
    }

    /**
     * Parse the API response into BAL_BdmTlWhywhymst (+ details).
     * ISO date/timestamp values are converted back to the old UI format first.
     */
    private BAL_BdmTlWhywhymst parseResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // Master
        JSONObject mstObj = sanitizeDates(jsonObj.getJSONObject("master"));
        BAL_BdmTlWhywhymst mst = BAL_BdmTlWhywhymst.fromJson(mstObj.toString());

        // Details
        List<BAL_BdmTlWhywhydtl> dtlList = new ArrayList<BAL_BdmTlWhywhydtl>();
        Object details = jsonObj.opt("details");
        if (details instanceof JSONArray) {
            JSONArray dtlArray = (JSONArray) details;
            for (int i = 0; i < dtlArray.length(); i++) {
                JSONObject dtlObj = sanitizeDates(dtlArray.getJSONObject(i));
                dtlList.add(BAL_BdmTlWhywhydtl.fromJson(dtlObj.toString()));
            }
        }
        mst.setBdmTlWhywhydtl(dtlList);

        return mst;
    }

   
    private JSONObject sanitizeDates(JSONObject obj) {

        JSONArray keys = obj.names();
        if (keys == null) {
            return obj;
        }

        for (int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);

            Object rawVal = obj.get(key);
            if (rawVal == null || "null".equals(String.valueOf(rawVal))) {
                continue;
            }

            String v = rawVal.toString();

            if (v.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*$")) {

                if (v.contains(".")) {
                    v = v.substring(0, v.indexOf("."));
                }
                if (v.endsWith("Z")) {
                    v = v.substring(0, v.length() - 1);
                }
                int plusIdx = v.indexOf('+', 10);
                if (plusIdx > -1) {
                    v = v.substring(0, plusIdx);
                }

                obj.put(key, CommonFunctions.pg_getDateTimeFromPGTimeStamp(v));

            } else if (v.matches("^\\d{4}-\\d{2}-\\d{2}$")) {

                obj.put(key, CommonFunctions.pg_getFormatDateFromDate(v));
            }
        }

        return obj;
    }
    /**
     * @param refDocId  e.g. the BDM/UPM document number (WWMS_REFDOCNO)
     * @return the WhyWhy master keyid linked to that ref doc (first match), or null if none
     */
    public String getYYRefKeyid(String refDocId) throws Exception {
        try {
            HttpResponse res = api.makeAuthRequest(REF_KEYID_URL + refDocId, "GET", null);
            CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            CommonMessage.debugMsg("JSON Response: " + jsonResponse);

            if (res.getStatusCode() != 200) {
                throw new Exception("Failed to fetch YY ref keyid. Status: " + res.getStatusCode()
                        + " | Body: " + jsonResponse);
            }

            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                return null;
            }

            JSONArray arr = JSONArray.fromObject(jsonResponse);
            if (arr.isEmpty()) {
                return null;
            }
            return arr.getString(0);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to fetch YY ref keyid: " + e.getMessage(), e);
        }
    }
    
    public BAL_BdmTlWhywhymst selectMasKeyid(String keyid) throws Exception {
        try {
            HttpResponse res = api.makeAuthRequest(MASTER_URL + keyid, "GET", null);
            CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            CommonMessage.debugMsg("JSON Response: " + jsonResponse);

            if (res.getStatusCode() == 404) {
                return null;
            }
            if (res.getStatusCode() != 200) {
                throw new Exception("Failed to fetch WhyWhy master. Status: " + res.getStatusCode()
                        + " | Body: " + jsonResponse);
            }

            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                return null;
            }

            JSONObject mstObj = sanitizeDates(JSONObject.fromObject(jsonResponse));
            return BAL_BdmTlWhywhymst.fromJson(mstObj.toString());

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to fetch WhyWhy master: " + e.getMessage(), e);
        }
    }
    public List<String[]> getRootCause(String openMode) throws Exception {
	     // Build URL with query parameter
	     StringBuilder apiUrlBuilder = new StringBuilder("/bdm/whywhy/root-cause");
	     
	     // Add openMode as query parameter if provided
	     if (openMode != null && !openMode.trim().isEmpty()) {
	         apiUrlBuilder.append("?openMode=")
	                      .append(URLEncoder.encode(openMode, StandardCharsets.UTF_8));
	     }
	     
	     String apiUrl = apiUrlBuilder.toString();
	     
	     CommonMessage.debugMsg("Fetching root cause data for openMode: " + openMode);
	     CommonMessage.debugMsg("API URL: " + apiUrl);
	     
	     // Send GET request
	     HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	     
	     CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
	     
	     String jsonResponse = res.getBody();
	     CommonMessage.debugMsg("JSON Response: " + jsonResponse);
	     
	     // Handle non-200 responses gracefully
	     if (res.getStatusCode() != 200) {
	         System.err.println("Error Response (" + res.getStatusCode() + "): " + jsonResponse);
	         return new ArrayList<>();
	     }
	     
	     // Validate JSON response
	     if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
	         CommonMessage.debugMsg("Empty response received for openMode: " + openMode);
	         return new ArrayList<>();
	     }
	     
	     // Check if it's a valid JSON array
	     String trimmedResponse = jsonResponse.trim();
	     if (!trimmedResponse.startsWith("[")) {
	         System.err.println("Invalid JSON response (not an array): " + jsonResponse);
	         return new ArrayList<>();
	     }
	     
	     // Parse JSON response
	     JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
	     
	     if (jsonArray == null || jsonArray.isEmpty()) {
	         CommonMessage.debugMsg("No root cause data found for openMode: " + openMode);
	         return new ArrayList<>();
	     }
	     
	     // Convert JSON array to List of String arrays
	     List<String[]> dataList = convertRootCauseJsonToList(jsonArray);
	     
	     CommonMessage.debugMsg("Root cause data retrieved successfully. Rows: " + dataList.size());
	     
	     return dataList;
	 }
    private List<String[]> convertRootCauseJsonToList(JSONArray jsonArray) {
	     List<String[]> list = new ArrayList<>();
	     
	     // Expected columns from the query: wrcm_keyid, '', wrcm_name
	     String[] expectedColumns = {"wrcm_keyid", "column2", "wrcm_name"};
	     
	     for (int i = 0; i < jsonArray.length(); i++) {
	         JSONObject obj = jsonArray.getJSONObject(i);
	         String[] row = new String[3]; // Fixed 3 columns as per SQL query
	         
	         // Column 1: wrcm_keyid
	         Object keyid = obj.opt("wrcm_keyid");
	         row[0] = (keyid == null || "null".equals(keyid.toString())) ? "" : keyid.toString();
	         
	         // Column 2: Empty string (from SQL SELECT '')
	         row[1] = "";
	         
	         // Column 3: wrcm_name
	         Object name = obj.opt("wrcm_name");
	         row[2] = (name == null || "null".equals(name.toString())) ? "" : name.toString();
	         
	         list.add(row);
	         CommonMessage.debugMsg("Root Cause Row " + i + ": " + Arrays.toString(row));
	     }
	     
	     return list;
	 }
    public BAL_BdmTlWhywhydtl deleteWhyWhyDetail(String detailId) throws Exception {
        String apiUrl = "/bdm/whywhy/delete-detail/" + URLEncoder.encode(detailId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Deleting WhyWhy detail with keyid: " + detailId);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse JSON response
        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
        
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        String deletedId = jsonObj.optString("deletedId", "");
        
        if (success) {
            CommonMessage.debugMsg("WhyWhy detail deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
            
            // Create and return a BdmTlWhywhydtl object with the deleted keyid
            BAL_BdmTlWhywhydtl deletedDetail = new BAL_BdmTlWhywhydtl();
            deletedDetail.setWwdtKeyid(deletedId);
            
            return deletedDetail;
        } else {
            System.err.println("Failed to delete WhyWhy detail. Message: " + message);
            throw new RuntimeException("Failed to delete WhyWhy detail: " + message);
        }
    }
    
    public List<String[]> getAnalysis(String masdetkeyid) throws Exception {
        String apiUrl = "/bdm/whywhy/analysis?masdetkeyid=" + URLEncoder.encode(masdetkeyid, StandardCharsets.UTF_8);
        
        	        
        CommonMessage.debugMsg("Fetching analysis data for masdetkeyid: " + masdetkeyid);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Response Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse JSON response
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        
        if (jsonArray == null || jsonArray.isEmpty()) {
            CommonMessage.debugMsg("No analysis data found for masdetkeyid: " + masdetkeyid);
            return new ArrayList<>();
        }
        
        // Convert JSON array to List of String arrays with column order
        List<String[]> dataList = convertJsonArrayToListwithcolumnorder(jsonArray);
        
        CommonMessage.debugMsg("Analysis data retrieved successfully. Rows: " + dataList.size());
        
        return dataList;
    }
    public List<String[]> convertJsonArrayToListwithcolumnorder(JSONArray jsonArray) {
	    List<String[]> list = new ArrayList<>();

	    if (jsonArray.length() < 0) {
	        throw new IllegalArgumentException("JSON array must have at least 3 rows (config, header, column order).");
	    }

	    // Row 3 = column order mapping
	    JSONObject orderObj = jsonArray.getJSONObject(0);

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
	    String[] headerRow = new String[orderList.size()];
	    int hColIndex = 0;
	    for (Map.Entry<String, Integer> entry : orderList) {
            String key = entry.getKey();
            headerRow[hColIndex++] = key != null ? key.toString() : "";
        }
	    
	    list.add(headerRow);

	    // Process each row (from row 1 onwards if you want headers, or from row 3 for data only)
	    for (int i = 0; i < jsonArray.length(); i++) {
	    	if( i == 0) {
	    		continue;
	    	}
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

}
