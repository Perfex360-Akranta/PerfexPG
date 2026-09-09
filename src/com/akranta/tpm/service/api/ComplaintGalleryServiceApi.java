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

import com.akranta.tpm.model.QtmTlComplaintgallery;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class ComplaintGalleryServiceApi {
    private final Api api;
    
    public ComplaintGalleryServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Complaint Gallery (handles both insert and update)
     * @param complaintGallery Complaint Gallery data
     * @return Saved/Updated QtmTlComplaintgallery with generated ID
     */
    public QtmTlComplaintgallery saveComplaintGallery(QtmTlComplaintgallery complaintGallery) throws Exception {
        String apiUrl = "/complaint-gallery/save";
        
        String jsonPayload = buildSaveJson(complaintGallery);
        
        CommonMessage.debugMsg("Complaint Gallery JSON :: " + jsonPayload);
        
        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());
        
        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);
        
        // Parse response back to object
        QtmTlComplaintgallery result = parseComplaintGalleryResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);
        
        return result;
    }

    /**
     * Get Complaint Gallery data by keyid
     * @param keyid The complaint gallery key ID
     * @return QtmTlComplaintgallery object
     */
    public QtmTlComplaintgallery getComplaintGalleryData(String keyid) {
        try {
            String apiUrl = "/complaint-gallery/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
            
            // Send GET request
            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
            
            CommonMessage.debugMsg("Get Complaint Gallery Data Response: " + res.getBody());
            String json = res.getBody();
            
            // Parse response
            QtmTlComplaintgallery result = parseComplaintGalleryResponse(json);
            
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get complaint gallery data for keyid: " + keyid, e);
        }
    }

    /**
     * Build JSON payload for save/update operation
     */
    private String buildSaveJson(QtmTlComplaintgallery complaintGallery) {
        StringBuilder str = new StringBuilder();
        
        str.append("{");
        
        // Complaint Gallery Details
        str.append("\"complaintGallery\":");
        String jsonComplaint = complaintGallery.toJsonManual();
        str.append(jsonComplaint);
        
        str.append("}");
        
        return str.toString();
    }

    /**
     * Parse JSON response into QtmTlComplaintgallery object
     */
    private QtmTlComplaintgallery parseComplaintGalleryResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        
        // Parse complaint gallery
        JSONObject complaintObj = jsonObj.getJSONObject("complaintGallery");
        QtmTlComplaintgallery complaint = QtmTlComplaintgallery.fromJson(complaintObj.toString());
        
        return complaint;
    }

    /**
     * Get Complaint Gallery data by customer ID
     */
    public List<QtmTlComplaintgallery> getComplaintGalleryByCustomer(String customerId) {
        try {
            String apiUrl = "/api/complaint-gallery/by-customer";
            
            String jsonPayload = buildCustomerFilterJson(customerId);
            
            // Send request
            HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
            
            CommonMessage.debugMsg("Response: " + res.getBody());
            String json = res.getBody();
            
            // Parse response array
            JSONArray jsonArray = JSONArray.fromObject(json);
            List<QtmTlComplaintgallery> result = new ArrayList<>();
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                QtmTlComplaintgallery complaint = parseComplaintGalleryResponse(obj.toString());
                result.add(complaint);
            }
            
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get complaint gallery by customer: " + customerId, e);
        }
    }

    /**
     * Get Complaint Gallery data by functional location and date range
     */
    public List<QtmTlComplaintgallery> getComplaintGalleryByFlid(String flid, String dateFrom, 
                                                                   String dateTo) {
        try {
            String apiUrl = "/api/complaint-gallery/by-flid";
            
            String jsonPayload = buildFilterJson(flid, dateFrom, dateTo);
            
            // Send request
            HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
            
            CommonMessage.debugMsg("Response: " + res.getBody());
            String json = res.getBody();
            
            // Parse response array
            JSONArray jsonArray = JSONArray.fromObject(json);
            List<QtmTlComplaintgallery> result = new ArrayList<>();
            
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                QtmTlComplaintgallery complaint = parseComplaintGalleryResponse(obj.toString());
                result.add(complaint);
            }
            
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get complaint gallery by flid", e);
        }
    }

    /**
     * Get Complaint Gallery grid data with filters
     */
    public List<String[]> getComplaintGalleryGrid(String flid, String dateFrom, String dateTo, 
                                                   String customerId, String defectId) {
        try {
            String apiUrl = "/api/complaint-gallery/grid";
            
            String jsonPayload = buildGridFilterJson(flid, dateFrom, dateTo, customerId, defectId);
            
            // Send request
            HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
            
            CommonMessage.debugMsg("Grid Response: " + res.getBody());
            String json = res.getBody();
            
            JSONArray jsonArray = JSONArray.fromObject(json);
            List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
            
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to get complaint gallery grid data", e);
        }
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
     * Build JSON for grid filter query
     */
    private String buildGridFilterJson(String flid, String dateFrom, String dateTo, 
                                       String customerId, String defectId) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"flid\":\"").append(flid != null ? flid : "").append("\"");
        sb.append(",\"dateFrom\":\"").append(dateFrom != null ? dateFrom : "").append("\"");
        sb.append(",\"dateTo\":\"").append(dateTo != null ? dateTo : "").append("\"");
        sb.append(",\"customerId\":\"").append(customerId != null ? customerId : "").append("\"");
        sb.append(",\"defectId\":\"").append(defectId != null ? defectId : "").append("\"");
        sb.append("}");
        
        CommonMessage.debugMsg("Grid Filter JSON: " + sb.toString());
        return sb.toString();
    }

    /**
     * Build JSON for filter query by flid and date range
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

    /**
     * Build JSON for customer filter query
     */
    private String buildCustomerFilterJson(String customerId) {
        StringBuilder sb = new StringBuilder();
        
        sb.append("{");
        sb.append("\"customerId\":\"").append(customerId != null ? customerId : "").append("\"");
        sb.append("}");
        
        CommonMessage.debugMsg("Customer Filter JSON: " + sb.toString());
        return sb.toString();
    }

    /**
     * Delete Complaint Gallery by keyid
     */
	/*
	 * public boolean deleteComplaintGallery(String keyid) { try { String apiUrl =
	 * "/complaint-gallery/delete/" + URLEncoder.encode(keyid,
	 * StandardCharsets.UTF_8);
	 * 
	 * // Send DELETE request HttpResponse res = api.makeAuthRequest(apiUrl,
	 * "DELETE", null);
	 * 
	 * CommonMessage.debugMsg("Delete Response: " + res.getBody());
	 * 
	 * return res.getStatusCode() == 200 || res.getStatusCode() == 204; } catch
	 * (IOException e) { e.printStackTrace(); throw new
	 * RuntimeException("Failed to delete complaint gallery with keyid: " + keyid,
	 * e); } }
	 */
    
    
    /**
     * Delete Complaint Gallery by keyid
     */
	/*
	 * public boolean deleteComplaintGallery(String keyid) { try { String apiUrl =
	 * "/complaint-gallery/delete/" + URLEncoder.encode(keyid,
	 * StandardCharsets.UTF_8);
	 * 
	 * // Send DELETE request HttpResponse res = api.makeAuthRequest(apiUrl,
	 * "DELETE", null);
	 * 
	 * CommonMessage.debugMsg("Delete Response: " + res.getBody());
	 * 
	 * return res.getStatusCode() == 200 || res.getStatusCode() == 204; } catch
	 * (IOException e) { e.printStackTrace(); throw new
	 * RuntimeException("Failed to delete complaint gallery with keyid: " + keyid,
	 * e); } }
	 */
    
    /**
     * Delete Complaint Gallery
     * Accepts either a QtmTlComplaintgallery object or extracts keyid from it
     * @param complaintGallery The complaint gallery object to delete
     * @return The deleted QtmTlComplaintgallery object
     * @throws Exception if deletion fails
     */
    public QtmTlComplaintgallery deleteComplaintGallery(QtmTlComplaintgallery complaintGallery) throws Exception {
        try {
            if (complaintGallery == null) {
                throw new IllegalArgumentException("Complaint Gallery object cannot be null");
            }
            
            String keyid = complaintGallery.getCmgaKeyid();
            
            if (keyid == null || keyid.trim().isEmpty()) {
                throw new IllegalArgumentException("Complaint Gallery keyid cannot be null or empty");
            }
            
            String apiUrl = "/complaint-gallery/delete/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
            
            CommonMessage.debugMsg("Deleting Complaint Gallery with keyid: " + keyid);
            
            // Send DELETE request
            HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
            
            CommonMessage.debugMsg("Delete Response Status Code: " + res.getStatusCode());
            CommonMessage.debugMsg("Delete Response Body: " + res.getBody());
            
            // Check if deletion was successful
            if (res.getStatusCode() == 200 || res.getStatusCode() == 204) {
                CommonMessage.debugMsg("Successfully deleted Complaint Gallery with keyid: " + keyid);
                return complaintGallery;
            } else if (res.getStatusCode() == 404) {
                throw new Exception("Complaint Gallery not found with keyid: " + keyid);
            } else {
                throw new Exception("Failed to delete Complaint Gallery. Status code: " + res.getStatusCode());
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to delete complaint gallery: " + e.getMessage(), e);
        }
    }
}