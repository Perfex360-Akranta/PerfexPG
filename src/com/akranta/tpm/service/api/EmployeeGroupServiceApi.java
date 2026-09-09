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

import com.akranta.tpm.model.GenTlMomGroupdtl;
import com.akranta.tpm.model.GenTlMomGroupmst;
import com.akranta.tpm.model.HttpResponse;
import com.google.gson.JsonArray;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class EmployeeGroupServiceApi {
    private final Api api;

    public EmployeeGroupServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save or update Employee Group
     */
    public GenTlMomGroupmst saveEmployeeGroup(GenTlMomGroupmst employeeGroupMst) throws Exception {
        String apiUrl = "/employeeGroup/save";

        String jsonPayload = buildSaveJson(employeeGroupMst);

        CommonMessage.debugMsg("Employee Group JSON :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Parse response back to object
        GenTlMomGroupmst result = parseEmployeeGroupResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);

        return result;
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(GenTlMomGroupmst employeeGroupMst) {
        StringBuilder str = new StringBuilder();

        str.append("{");

        // Master Details
        str.append("\"master\":");
        String jsonMst = employeeGroupMst.toJsonManual();
        str.append(jsonMst);
        str.append(",");

        // Detail Table Values
        str.append("\"details\":");
        List<GenTlMomGroupdtl> details = employeeGroupMst.getGroupMemberDetail();
        if (details != null && !details.isEmpty()) {
            str.append(GenTlMomGroupmst.toJsonManualList(details));
        } else {
            str.append("[]");
        }

        str.append("}");

        return str.toString();
    }

    /**
     * Parse JSON response into GenTlMomGroupmst object
     */
    private GenTlMomGroupmst parseEmployeeGroupResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        GenTlMomGroupmst mst = GenTlMomGroupmst.fromJson(mstObj.toString());

        // Parse details array
        if (jsonObj.has("details") && !jsonObj.isNullObject() &&
                !jsonObj.get("details").equals(null)) {

            JSONArray dtlArray = jsonObj.getJSONArray("details");
            List<GenTlMomGroupdtl> detailList = new ArrayList<>();

            for (int i = 0; i < dtlArray.length(); i++) {
                JSONObject dtlObj = dtlArray.getJSONObject(i);
                GenTlMomGroupdtl dtl = GenTlMomGroupdtl.fromJson(dtlObj.toString());
                detailList.add(dtl);
            }

            mst.setGroupMemberDetail(detailList);
        }

        return mst;
    }
    
    public List<String[]> getGrid() throws Exception
    {
    	 String apiUrl = "/employeeGroup/grid";

  

         // Send request
         HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

         CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

         String jsonResponse = res.getBody();
         CommonMessage.debugMsg("JSON Response: " + jsonResponse);
         
         JSONArray jsonArray = new JSONArray(jsonResponse);
         List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
         return result;

       
    	
    }
    
    public  void deleteDetailGrid(String keyId) throws Exception
    {
    	StringBuilder apiUrl = new StringBuilder("/employeeGroup/deleteGrid");

    	 apiUrl.append("?keyId=")
         .append(URLEncoder.encode(
        		 keyId != null ? keyId : "",
                 StandardCharsets.UTF_8));

         // Send request
         HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "DELETE", null);

         CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

         String jsonResponse = res.getBody();
         CommonMessage.debugMsg("JSON Response: " + jsonResponse);
         

       
    	
    }
    
    public List<String[]> getCreationGrid(String functional,String mstKeyId) throws Exception
    {
    	StringBuilder apiUrl = new StringBuilder("/employeeGroup/createGrid");
    	CommonMessage.debugMsg("Key Id "+mstKeyId);
    	CommonMessage.debugMsg("functional "+functional);
        apiUrl.append("?flid=")
              .append(URLEncoder.encode(
                      functional != null ? functional : "",
                      StandardCharsets.UTF_8));

        if (mstKeyId != null && !mstKeyId.trim().isEmpty()) {
            apiUrl.append("&keyId=")
                  .append(URLEncoder.encode(mstKeyId, StandardCharsets.UTF_8));
        }

        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);

         CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

         String jsonResponse = res.getBody();
         CommonMessage.debugMsg("JSON Response: " + jsonResponse);
         
         JSONArray jsonArray = new JSONArray(jsonResponse);
         List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
         return result;

       
    	
    }
    
    public List<String[]> getDetailGrid(String mstKeyId) throws Exception
    {
    	StringBuilder apiUrl = new StringBuilder("/employeeGroup/detailGrid");
    	CommonMessage.debugMsg("Key Id "+mstKeyId);
    	
       

        if (mstKeyId != null && !mstKeyId.trim().isEmpty()) {
            apiUrl.append("?keyId=")
                  .append(URLEncoder.encode(mstKeyId, StandardCharsets.UTF_8));
        }

        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);

         CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

         String jsonResponse = res.getBody();
         CommonMessage.debugMsg("JSON Response: " + jsonResponse);
         
         JSONArray jsonArray = new JSONArray(jsonResponse);
         List<String[]> result = convertJsonArrayToListwithcolumnorder(jsonArray);
         return result;
    }
    
    public GenTlMomGroupmst getViewGrid(String mstKeyId) throws Exception
    {
    	StringBuilder apiUrl = new StringBuilder("/employeeGroup/viewGrid");
    	CommonMessage.debugMsg("Key Id "+mstKeyId);
    	
       

        if (mstKeyId != null && !mstKeyId.trim().isEmpty()) {
            apiUrl.append("?keyId=")
                  .append(URLEncoder.encode(mstKeyId, StandardCharsets.UTF_8));
        }

        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);

         CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

         String jsonResponse = res.getBody();
         CommonMessage.debugMsg("JSON Response: " + jsonResponse);
         
       GenTlMomGroupmst mst = GenTlMomGroupmst.fromJson(jsonResponse);
       return mst;
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