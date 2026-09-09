package com.akranta.tpm.service.api;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.model.JhaTlAuditmst;
import com.akranta.tpm.model.JhaTlAudittemplate;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JhDmtAuditUploadServiceApi {
    private final Api api;
    
    public JhDmtAuditUploadServiceApi(String JwtToken) {
        this.api = new Api(JwtToken);
    }

    /**
     * Insert or Update JH Audit Master
     */
    public JhaTlAuditmst insertRecord(JhaTlAuditmst jhaTlAuditmst) throws Exception {
        String apiUrl = "/jhauditupload"; // The Spring Boot API endpoint for insert
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
    
    

    public JhaTlAuditmst getAuditByKeyid(String keyid) throws Exception {
        String apiUrl = "/jhauditupload/" + keyid;  // This matches your Postman URL

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg(res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Parse the response using the existing getJson method
        // which handles both jhaTlAuditmst and jhaTlAuditdtl
        JhaTlAuditmst audit = getJson(jsonResponse);

        return audit;
    }


    /**
     * Insert ONLY Master Record
     * New endpoint: POST /jhaudit/master
     * Matches: service.insertRecord(jhaTlAuditmst)
     */
    public JhaTlAuditmst insertMasterRecord(JhaTlAuditmst jhaTlAuditmst) throws Exception {
        String apiUrl = "/jhaudit/master";
        
        String jsonPayload = jhaTlAuditmst.toJsonManual();
        CommonMessage.debugMsg("Master Json :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("Master JSON Response: " + jsonResponse);
        
        JhaTlAuditmst master = JhaTlAuditmst.fromJson(jsonResponse);
        CommonMessage.debugMsg(master);
        
        return master;
    }

    /**
     * Get all audits
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
     */
    public List<JhaTlAudittemplate> getTemplatesByMasterid(String masterid) throws Exception {
        String apiUrl = "/jhaudit/template/byMasterid/" + masterid;
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg(res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse JSON array response
        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        List<JhaTlAudittemplate> templateList = new ArrayList<>();
        
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            JhaTlAudittemplate template = JhaTlAudittemplate.fromJson(obj.toString());
            templateList.add(template);
        }
        
        return templateList;
    }

    /**
     * Convert JhaTlAuditmst object to JSON string (Master only)
     */
    public String insertJson(JhaTlAuditmst jhaTlAuditmst) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Master Details
        str.append("\"jhaTlAuditmst\":");
        String jsonMst = jhaTlAuditmst.toJsonManual();
        str.append(jsonMst);
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Convert JSON response to JhaTlAuditmst object
     */
    public JhaTlAuditmst getJson(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        JSONObject mstObj = jsonObj.getJSONObject("jhaTlAuditmst");
        
        JhaTlAuditmst mst = JhaTlAuditmst.fromJson(mstObj.toString());
        
        return mst;
    }

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
}