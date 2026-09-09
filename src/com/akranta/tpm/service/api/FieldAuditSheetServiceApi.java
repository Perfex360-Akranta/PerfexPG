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

import com.akranta.tpm.model.FieldAuditSheetmst;
import com.akranta.tpm.model.FieldAuditSheetdtl;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class FieldAuditSheetServiceApi {
    private final Api api;
    
    public FieldAuditSheetServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Field Audit Sheet (handles both insert and update)
     * @param fieldAuditSheetmst Master data with details
     * @return Saved/Updated FieldAuditSheetmst with generated IDs
     */
    public FieldAuditSheetmst saveFieldAuditSheet(FieldAuditSheetmst fieldAuditSheetmst) throws Exception {
        String apiUrl = "/field-audit-sheet/save";
        
        String jsonPayload = buildSaveJson(fieldAuditSheetmst);
        
        CommonMessage.debugMsg("Field Audit Sheet JSON :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response back to object
        FieldAuditSheetmst result = parseFieldAuditSheetResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);
        
        return result;
    }

    /**
     * Get complete Field Audit Sheet data by master keyid
     * @param masterKeyid The master key ID
     * @return Complete FieldAuditSheetmst with all details
     */
    public FieldAuditSheetmst getCompleteFieldAuditSheetData(String masterKeyid) throws IOException {
        String apiUrl = "/field-audit-sheet/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Get Complete Data Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response
        FieldAuditSheetmst result = parseFieldAuditSheetResponse(json);
        
        return result;
    }

    /**
     * Delete Field Audit Sheet detail by detail keyid
     * @param detailId The detail key ID to delete
     * @return true if deletion was successful
     */
    public boolean deleteFieldAuditSheetDetail(String detailId) throws IOException {
        String apiUrl = "/field-audit-sheet/delete-detail/" + URLEncoder.encode(detailId, StandardCharsets.UTF_8);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Delete Detail Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse response
        JSONObject responseObj = JSONObject.fromObject(json);
        boolean success = responseObj.optBoolean("success", false);
        
        CommonMessage.debugMsg("Delete successful: " + success);
        
        return success;
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(FieldAuditSheetmst fieldAuditSheetmst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"master\":");
        String jsonMst = fieldAuditSheetmst.toJsonManual();
        str.append(jsonMst);
        str.append(",");
        
        // Detail Table Values
        List<FieldAuditSheetdtl> details = fieldAuditSheetmst.getAuditDtl();
        str.append("\"details\":");
        String jsonDtl = FieldAuditSheetdtl.toJsonManualList(details);
        str.append(jsonDtl);
        
        // Optional: Include form metadata if needed
        // str.append(",\"formActionMode\":\"SAVE\"");
        // str.append(",\"formMode\":\"INSERT\"");
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Parse JSON response into FieldAuditSheetmst object
     */
    private FieldAuditSheetmst parseFieldAuditSheetResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        FieldAuditSheetmst mst = FieldAuditSheetmst.fromJson(mstObj.toString());
        
        // Parse details array
        List<FieldAuditSheetdtl> dtlList = new ArrayList<>();
        if (jsonObj.has("details") && !jsonObj.isNullObject() && 
            !jsonObj.get("details").equals(null)) {
            
            JSONArray dtlArray = jsonObj.getJSONArray("details");
            dtlList = FieldAuditSheetdtl.fromJsonList(dtlArray.toString());
        }
        
        mst.setAuditDtl(dtlList);
        
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
     * Example: Get Field Audit Sheet grid data with filters
     * This is a placeholder - adjust parameters based on your actual requirements
     */
    public List<String[]> getFieldAuditSheetGrid(String flid, String dateFrom, String dateTo, 
                                                   String shift, String tradeId) throws IOException {
        String apiUrl = "/api/field-audit-sheet/grid";
        
        String jsonPayload = buildGridFilterJson(flid, dateFrom, dateTo, shift, tradeId);
        
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
                                       String shift, String tradeId) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"flid\":\"").append(flid).append("\"");
        sb.append(",\"dateFrom\":\"").append(dateFrom).append("\"");
        sb.append(",\"dateTo\":\"").append(dateTo).append("\"");
        sb.append(",\"shift\":\"").append(shift).append("\"");
        sb.append(",\"tradeId\":\"").append(tradeId).append("\"");
        sb.append("}");
        
        CommonMessage.debugMsg("Grid Filter JSON: " + sb.toString());
        return sb.toString();
    }
}