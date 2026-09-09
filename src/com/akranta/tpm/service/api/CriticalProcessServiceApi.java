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

import com.akranta.tpm.model.QtmTlCriticalprocessmst;
import com.akranta.tpm.model.QtmTlCriticalprocessdtl;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class CriticalProcessServiceApi {
    private final Api api;
    
    public CriticalProcessServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Critical Process (handles both insert and update)
     * @param criticalProcessMst Master data with details
     * @return Saved/Updated QtmTlCriticalprocessmst with generated IDs
     */
    public QtmTlCriticalprocessmst saveCriticalProcess(QtmTlCriticalprocessmst criticalProcessMst) throws Exception {
        String apiUrl = "/critical-process/save";
        
        String jsonPayload = buildSaveJson(criticalProcessMst);
        
        CommonMessage.debugMsg("Critical Process JSON :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response back to object
        QtmTlCriticalprocessmst result = parseCriticalProcessResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);
        
        return result;
    }

    /**
     * Get complete Critical Process data by master keyid
     * @param masterKeyid The master key ID
     * @return Complete QtmTlCriticalprocessmst with all details
     */
    public QtmTlCriticalprocessmst getCompleteCriticalProcessData(String masterKeyid) throws IOException {
        String apiUrl = "/critical-process/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Get Complete Data Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response
        QtmTlCriticalprocessmst result = parseCriticalProcessResponse(json);
        
        return result;
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(QtmTlCriticalprocessmst criticalProcessMst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"master\":");
        String jsonMst = criticalProcessMst.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values - Note: We only have one detail object in the model
        str.append("\"details\":");
        QtmTlCriticalprocessdtl detailObj = criticalProcessMst.getQtmTlCriticalprocessdtl();
        if (detailObj != null) {
            // Wrap single detail object in array
            str.append("[");
            str.append(detailObj.toJsonManual());
            str.append("]");
        } else {
            str.append("[]");
        }
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Parse JSON response into QtmTlCriticalprocessmst object
     */
    private QtmTlCriticalprocessmst parseCriticalProcessResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        QtmTlCriticalprocessmst mst = QtmTlCriticalprocessmst.fromJson(mstObj.toString());
        
        // Parse details array - take first detail if exists
        if (jsonObj.has("details") && !jsonObj.isNullObject() && 
            !jsonObj.get("details").equals(null)) {
            
            JSONArray dtlArray = jsonObj.getJSONArray("details");
            if (dtlArray.length() > 0) {
                JSONObject dtlObj = dtlArray.getJSONObject(0);
                QtmTlCriticalprocessdtl dtl = QtmTlCriticalprocessdtl.fromJson(dtlObj.toString());
                mst.setQtmTlCriticalprocessdtl(dtl);
            }
        }
        
        return mst;
    }

    /**
     * Generic method to convert JSONArray to List<String[]> with column ordering
     * This can be used for grid data or reporting
     */
    public List<String[]> convertJsonArrayToListWithColumnOrder(JSONArray jsonArray) {
        List<String[]> list = new ArrayList<>();

        if (jsonArray.length() < 1) {
            return list;
        }

        // Row 0 = column order mapping
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

        // Process each data row (skip the first row which is column order)
        for (int i = 1; i < jsonArray.length(); i++) {
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

        // Debug output
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }

        return list;
    }

    /**
     * Example: Get Critical Process grid data with filters
     * Adjust parameters based on your actual requirements
     */
    public List<String[]> getCriticalProcessGrid(String flid, String dateFrom, String dateTo, 
                                                   String elementId) throws IOException {
        String apiUrl = "/critical-process/grid";
        
        String jsonPayload = buildGridFilterJson(flid, dateFrom, dateTo, elementId);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Grid Response: " + res.getBody());
        String json = res.getBody();
        
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        return result;
    }

    /**
     * Build JSON for grid filter query
     */
    private String buildGridFilterJson(String flid, String dateFrom, String dateTo, 
                                       String elementId) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"flid\":\"").append(flid != null ? flid : "").append("\"");
        sb.append(",\"dateFrom\":\"").append(dateFrom != null ? dateFrom : "").append("\"");
        sb.append(",\"dateTo\":\"").append(dateTo != null ? dateTo : "").append("\"");
        sb.append(",\"elementId\":\"").append(elementId != null ? elementId : "").append("\"");
        sb.append("}");
        
        CommonMessage.debugMsg("Grid Filter JSON: " + sb.toString());
        return sb.toString();
    }
    
    /**
     * Get Critical Process data by functional location and date range
     */
    public List<QtmTlCriticalprocessmst> getCriticalProcessByFlid(String flid, String dateFrom, 
                                                                   String dateTo) throws IOException {
        String apiUrl = "/critical-process/by-flid";
        
        String jsonPayload = buildFilterJson(flid, dateFrom, dateTo);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response array
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<QtmTlCriticalprocessmst> result = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            QtmTlCriticalprocessmst mst = parseCriticalProcessResponse(obj.toString());
            result.add(mst);
        }
        
        return result;
    }
    
    /**
     * Build JSON for filter query
     */
    private String buildFilterJson(String flid, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"flid\":\"").append(flid != null ? flid : "").append("\"");
        sb.append(",\"dateFrom\":\"").append(dateFrom != null ? dateFrom : "").append("\"");
        sb.append(",\"dateTo\":\"").append(dateTo != null ? dateTo : "").append("\"");
        sb.append("}");
        
        CommonMessage.debugMsg("Filter JSON: " + sb.toString());
        return sb.toString();
    }
    
    
    
    public String deleteCriticalProcessMaster(String masterId) throws Exception {
        String apiUrl = "/critical-process/delete/" + URLEncoder.encode(masterId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Deleting Critical Process Master with keyid: " + masterId);
        
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
            CommonMessage.debugMsg("Critical Process Master deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
            return deletedId;
        } else {
            System.err.println("Failed to delete Critical Process Master. Message: " + message);
            throw new RuntimeException("Failed to delete Critical Process Master: " + message);
        }
    }
    public QtmTlCriticalprocessdtl deleteCriticalProcessDetail(String detailId) throws Exception {
        String apiUrl = "/critical-process/delete-detail/" + URLEncoder.encode(detailId, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Deleting Critical Process detail with keyid: " + detailId);
        
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
            CommonMessage.debugMsg("Critical Process detail deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
            
            // Create and return a QtmTlCriticalprocessdtl object with the deleted keyid
            QtmTlCriticalprocessdtl deletedDetail = new QtmTlCriticalprocessdtl();
            deletedDetail.setCrpdKeyid(deletedId);
            
            return deletedDetail;
        } else {
            System.err.println("Failed to delete Critical Process detail. Message: " + message);
            throw new RuntimeException("Failed to delete Critical Process detail: " + message);
        }
    }

}