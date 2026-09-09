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

import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.HttpResponse;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import com.akranta.tpm.utils.CommonMessage;

public class WwblaServiceApi {
    private final Api api;

    public WwblaServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    public BdmTlWwblamst saveWwbla(BdmTlWwblamst wwblaMst) throws Exception {
        String apiUrl = "/wwbla/save";

        String jsonPayload = buildSaveJson(wwblaMst);

        CommonMessage.debugMsg("Wwbla Master JSON :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Parse response back to object
        BdmTlWwblamst result = parseWwblaResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);

        return result;
    }

    // Overloaded method to accept both new and existing BdmTlWwblamst objects
    public BdmTlWwblamst saveWwbla(BdmTlWwblamst newBdmTlWwblamst, BdmTlWwblamst existBdmTlWwblamst) throws Exception {
        // Use the new object for saving (existBdmTlWwblamst is typically used for validation/comparison)
        return saveWwbla(newBdmTlWwblamst);
    }

    
    public List<BdmTlWwbladtl> saveWwblaDetail(List<BdmTlWwbladtl> detailList) throws Exception {
        String apiUrl = "/wwbla/save/detail";

        String jsonPayload = buildDetailSaveJson(detailList);

        CommonMessage.debugMsg("Wwbla Detail JSON :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Parse response back to list of objects
        List<BdmTlWwbladtl> result = parseWwblaDetailResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Detail Result Count: " + result.size());

        return result;
    }

   
    public BdmTlWwbladtl saveWwblaDetail(BdmTlWwbladtl detail) throws Exception {
        List<BdmTlWwbladtl> detailList = new ArrayList<>();
        detailList.add(detail);
        
        List<BdmTlWwbladtl> result = saveWwblaDetail(detailList);
        
        return result.isEmpty() ? null : result.get(0);
    }

    // Overloaded method to accept new, existing detail objects and editval parameter
    public BdmTlWwbladtl saveWwblaDetail(BdmTlWwbladtl newBdmTlWwbladtl, BdmTlWwbladtl existBdmTlWwbladtl, String editval) throws Exception {
        // Use the new object for saving (existBdmTlWwbladtl and editval are typically used for validation/comparison)
        // If you need to use editval for specific logic, you can add it here
        return saveWwblaDetail(newBdmTlWwbladtl);
    }

    
    public List<String[]> recallWwblaDetail(String keyid) throws IOException {
        String apiUrl = "/wwbla/recall-detail/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
        
        
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        
        CommonMessage.debugMsg("Recall Wwbla Detail Response: " + res.getBody());
        String json = res.getBody();
        
        
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        CommonMessage.debugMsg("Printing recall Wwbla Details **************");
        for(String[] list : result) {
            CommonMessage.debugMsg(Arrays.toString(list));
        }
        
        return result;
    }

    
    private String buildSaveJson(BdmTlWwblamst wwblaMst) {
        StringBuilder str = new StringBuilder();

        str.append("{");
        str.append("\"master\":");
        str.append(wwblaMst.toJsonManual());
        str.append("}");

        return str.toString();
    }

   
    private String buildDetailSaveJson(List<BdmTlWwbladtl> detailList) {
        StringBuilder str = new StringBuilder();

        str.append("{");
        str.append("\"details\":[");
        
        for (int i = 0; i < detailList.size(); i++) {
            if (i > 0) str.append(",");
            str.append(detailList.get(i).toJsonManual());
        }
        
        str.append("]");
        str.append("}");

        return str.toString();
    }

    
    private BdmTlWwblamst parseWwblaResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // Parse master
        JSONObject mstObj = jsonObj.getJSONObject("master");
        BdmTlWwblamst mst = BdmTlWwblamst.fromJson(mstObj.toString());

        return mst;
    }

    
    private List<BdmTlWwbladtl> parseWwblaDetailResponse(String json) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);
        
        JSONObject jsonObj = JSONObject.fromObject(json);
        List<BdmTlWwbladtl> detailList = new ArrayList<>();

        // Parse details array
        if (jsonObj.has("details")) {
            JSONArray dtlArray = jsonObj.getJSONArray("details");
            
            for (int i = 0; i < dtlArray.length(); i++) {
                JSONObject dtlObj = dtlArray.getJSONObject(i);
                BdmTlWwbladtl dtl = BdmTlWwbladtl.fromJson(dtlObj.toString());
                detailList.add(dtl);
            }
        }

        return detailList;
    }

    
    public List<String[]> getWwblaValues(String masterKeyid, String parentId, String detailKeyid) throws IOException {
        StringBuilder apiUrl = new StringBuilder("/wwbla/values?");
        
        boolean hasParam = false;
        
        // Add masterKeyid parameter if provided
        if (masterKeyid != null && !masterKeyid.trim().isEmpty()) {
            apiUrl.append("masterKeyid=").append(URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8));
            hasParam = true;
        }
        
        // Add parentId parameter if provided
        if (parentId != null && !parentId.trim().isEmpty()) {
            if (hasParam) apiUrl.append("&");
            apiUrl.append("parentId=").append(URLEncoder.encode(parentId, StandardCharsets.UTF_8));
            hasParam = true;
        }
        
        // Add detailKeyid parameter if provided
        if (detailKeyid != null && !detailKeyid.trim().isEmpty()) {
            if (hasParam) apiUrl.append("&");
            apiUrl.append("detailKeyid=").append(URLEncoder.encode(detailKeyid, StandardCharsets.UTF_8));
        }
        
        CommonMessage.debugMsg("Get Wwbla Values API URL: " + apiUrl.toString());
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        
        CommonMessage.debugMsg("Get Wwbla Values Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse JSON array
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
        
        CommonMessage.debugMsg("Printing Wwbla Values **************");
        for(String[] list : result) {
            CommonMessage.debugMsg(Arrays.toString(list));
        }
        
        return result;
    }

   
    public List<BdmTlWwbladtl> getWwblaValues(BdmTlWwbladtl bdmTlWwbladtl, BdmTlWwblamst bdmTlWwblamst, String id, String masterId) throws Exception {
        // Build the API URL with parameters
        StringBuilder apiUrl = new StringBuilder("/wwbla/values?");
        
        boolean hasParam = false;
        
        // Add masterId parameter
        if (masterId != null && !masterId.trim().isEmpty()) {
            apiUrl.append("masterKeyid=").append(URLEncoder.encode(masterId, StandardCharsets.UTF_8));
            hasParam = true;
        }
        
        // Add id parameter (as parentId)
        if (id != null && !id.trim().isEmpty()) {
            if (hasParam) apiUrl.append("&");
            apiUrl.append("parentId=").append(URLEncoder.encode(id, StandardCharsets.UTF_8));
            hasParam = true;
        }
        
        CommonMessage.debugMsg("Get Wwbla Values API URL: " + apiUrl.toString());
        
        // Send GET request
        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        
        CommonMessage.debugMsg("Get Wwbla Values Response: " + res.getBody());
        String json = res.getBody();
        
        // Parse JSON response into List<BdmTlWwbladtl>
        List<BdmTlWwbladtl> detailList = new ArrayList<>();
        
        try {
            JSONArray jsonArray = JSONArray.fromObject(json);
            
            CommonMessage.debugMsg("Parsing " + jsonArray.length() + " items from response");
            
           
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                
                CommonMessage.debugMsg("Processing item " + i + ": " + jsonObj.toString());
                
               
                BdmTlWwbladtl dtl = new BdmTlWwbladtl();
                
                
                if (jsonObj.has("wwbd_phenomena_factor")) {
                    dtl.setWwbdCountermeasure(jsonObj.getString("wwbd_phenomena_factor"));
                }
                if (jsonObj.has("wwbd_keyid")) {
                    dtl.setWwbdKeyid(jsonObj.getString("wwbd_keyid"));
                }
                if (jsonObj.has("wwbd_orderno")) {
                    dtl.setWwbdOrderno(jsonObj.getString("wwbd_orderno"));
                }
                if (jsonObj.has("wwbd_parentid")) {
                    dtl.setWwbdParentid(jsonObj.getString("wwbd_parentid"));
                }
                if (jsonObj.has("wwbd_wwbl_keyid")) {
                    dtl.setWwbdWwblKeyid(jsonObj.getString("wwbd_wwbl_keyid"));
                }
                if (jsonObj.has("wwbd_levelno")) {
                    dtl.setWwbdLevelno(jsonObj.getString("wwbd_levelno"));
                }
                
                detailList.add(dtl);
                CommonMessage.debugMsg("Added detail with keyid: " + dtl.getWwbdKeyid());
            }
            
            CommonMessage.debugMsg("Successfully parsed " + detailList.size() + " detail records");
            
        } catch (Exception e) {
            CommonMessage.debugMsg("Error parsing Wwbla values response: " + e.getMessage());
            e.printStackTrace();
            throw new Exception("Failed to parse Wwbla values response: " + e.getMessage());
        }
        
        return detailList;
    }
    public BdmTlWwbladtl deleteWWBLAChildEntry(BdmTlWwbladtl bdmTlWwbladtl) throws Exception {
        String keyid = bdmTlWwbladtl.getWwbdKeyid();
        
        if (keyid == null || keyid.trim().isEmpty()) {
            throw new IllegalArgumentException("Key ID cannot be null or empty");
        }
        
        String apiUrl = "/wwbla/delete-detail/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
        
        CommonMessage.debugMsg("Delete WWBLA Detail API URL: " + apiUrl);
        
        // Send DELETE request
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        
        CommonMessage.debugMsg("Delete Response Status Code: " + res.getStatusCode());
        CommonMessage.debugMsg("Delete Response Body: " + res.getBody());
        
        // Parse response
        String jsonResponse = res.getBody();
        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
        
        // Check if deletion was successful
        boolean success = jsonObj.optBoolean("success", false);
        String message = jsonObj.optString("message", "");
        
        if (!success) {
            throw new Exception("Failed to delete WWBLA detail: " + message);
        }
        
        CommonMessage.debugMsg("Successfully deleted WWBLA detail with keyid: " + keyid);
        
        // Return the original object
        return bdmTlWwbladtl;
    }
    
    private List<String[]> convertJsonArrayToListWithColumnOrder(JSONArray jsonArray) {
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
                
                // Handle null, "null", "{}", and "-" values
                row[colIndex++] = (value != null && 
                                  !value.toString().equals("null") && 
                                  !value.toString().equals("{}") && 
                                  !value.toString().equals("-")) ? value.toString() : "";
            }

            list.add(row);
        }

        // Debug output
        CommonMessage.debugMsg("Converted Wwbla Detail Data:");
        for (String[] row : list) {
            CommonMessage.debugMsg(Arrays.toString(row));
        }

        return list;
    }
}
