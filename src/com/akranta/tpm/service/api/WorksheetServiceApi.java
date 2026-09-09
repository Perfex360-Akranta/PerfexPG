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

import com.akranta.tpm.model.StdTlStdworksheetmst;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class WorksheetServiceApi {
    private final Api api;
    
    public WorksheetServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Worksheet (handles both insert and update)
     * @param worksheetMst Master data with details
     * @return Saved/Updated StdTlStdworksheetmst with generated IDs
     */
    public StdTlStdworksheetmst saveWorksheet(StdTlStdworksheetmst worksheetMst) throws Exception {
        String apiUrl = "/worksheet/save";
        
        String jsonPayload = buildSaveJson(worksheetMst);
        
        CommonMessage.debugMsg("Worksheet JSON :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response back to object
        StdTlStdworksheetmst result = parseWorksheetResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);
        
        return result;
    }

    /**
     * Get complete Worksheet data by master keyid
     * @param masterKeyid The master key ID
     * @return Complete StdTlStdworksheetmst with all details
     */
    public StdTlStdworksheetmst getCompleteWorksheetData(String masterKeyid) throws IOException {
        String apiUrl = "/worksheet/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Get Complete Data Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response
        StdTlStdworksheetmst result = parseWorksheetResponse(json);
        
        return result;
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(StdTlStdworksheetmst worksheetMst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"master\":");
        String jsonMst = worksheetMst.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values
        str.append("\"details\":");
        List<StdTlStdworksheetdtl> detailList = worksheetMst.getstdTlStdworksheetdtl();
        if (detailList != null && !detailList.isEmpty()) {
            str.append(StdTlStdworksheetdtl.toJsonManualList(detailList));
        } else {
            str.append("[]");
        }
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Parse JSON response into StdTlStdworksheetmst object
     */
    private StdTlStdworksheetmst parseWorksheetResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        StdTlStdworksheetmst mst = StdTlStdworksheetmst.fromJson(mstObj.toString());
        
        // Parse details array
        if (jsonObj.has("details") && !jsonObj.isNullObject() && 
            !jsonObj.get("details").equals(null)) {
            
            JSONArray dtlArray = jsonObj.getJSONArray("details");
            if (dtlArray.length() > 0) {
                List<StdTlStdworksheetdtl> detailList = StdTlStdworksheetdtl.fromJsonList(dtlArray.toString());
                mst.setStdTlStdworksheetdtl(detailList);
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
     * Example: Get Worksheet grid data with filters
     * Adjust parameters based on your actual requirements
     */
    public List<String[]> getWorksheetGrid(String flid, String dateFrom, String dateTo, 
                                           String elementId) throws IOException {
        String apiUrl = "/api/worksheet/grid";
        
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
     * Get Worksheet data by functional location and date range
     */
    public List<StdTlStdworksheetmst> getWorksheetByFlid(String flid, String dateFrom, 
                                                          String dateTo) throws IOException {
        String apiUrl = "/api/worksheet/by-flid";
        
        String jsonPayload = buildFilterJson(flid, dateFrom, dateTo);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response array
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<StdTlStdworksheetmst> result = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            StdTlStdworksheetmst mst = parseWorksheetResponse(obj.toString());
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
}