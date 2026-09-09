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

import com.akranta.tpm.model.PlmTlConditionalappraisalmst;
import com.akranta.tpm.model.PlmTlConditionalappraisal;
import com.akranta.tpm.model.PlmTlConditionalappraisalmstentry;
import com.akranta.tpm.model.PlmTlConditionalappraisalEntry;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class ConditionalAppraisalServiceApi {
	private final Api api;

	public ConditionalAppraisalServiceApi(String jwtToken) {
		this.api = new Api(jwtToken);
	}

	
	public PlmTlConditionalappraisalmst saveConditionalAppraisal(PlmTlConditionalappraisalmst conditionalAppraisalMst) throws Exception {
		String apiUrl = "/conditional-appraisal/save";

		String jsonPayload = buildSaveJson(conditionalAppraisalMst);

		CommonMessage.debugMsg("Conditional Appraisal JSON :: " + jsonPayload);

		// Send request
		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		// Parse response back to object
		PlmTlConditionalappraisalmst result = parseConditionalAppraisalResponse(jsonResponse);
		CommonMessage.debugMsg("Parsed Result: " + result);

		return result;
	}

	
	public PlmTlConditionalappraisalmstentry saveConditionalAppraisalEntry(PlmTlConditionalappraisalmstentry conditionalAppraisalMstEntry) throws Exception {
		String apiUrl = "/conditional-appraisal/save-entry";

		String jsonPayload = buildSaveEntryJson(conditionalAppraisalMstEntry);

		CommonMessage.debugMsg("Conditional Appraisal Entry JSON :: " + jsonPayload);

		// Send request
		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		// Parse response back to object
		PlmTlConditionalappraisalmstentry result = parseConditionalAppraisalEntryResponse(jsonResponse);
		CommonMessage.debugMsg("Parsed Result: " + result);

		return result;
	}

	/**
	 * Get complete Conditional Appraisal data by master keyid
	 * @param masterKeyid The master key ID
	 * @return Complete PlmTlConditionalappraisalmst with all details
	 */
	public PlmTlConditionalappraisalmst getCompleteConditionalAppraisalData(String masterKeyid) throws IOException {
		String apiUrl = "/conditional-appraisal/complete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

		// Send GET request
		HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

		CommonMessage.debugMsg("Get Complete Data Response: " + res.getBody());
		String json = res.getBody();

		// Parse response
		PlmTlConditionalappraisalmst result = parseConditionalAppraisalResponse(json);

		return result;
	}
	
	/**
	 * Check if update exists for Conditional Appraisal Entry
	 * @param masterEntry The master entry object containing FLID and DATE
	 * @param forGrid Flag to determine query type ("N" = with join, other = without join)
	 * @param cdapkeyid Detail key ID (required when forGrid = "N")
	 * @return Map containing count and exists flag
	 */
	/**
	 * Check if update exists for Conditional Appraisal Entry
	 * @param masterEntry The master entry object containing FLID and DATE
	 * @param forGrid Flag to determine query type ("N" = with join, other = without join)
	 * @param cdapkeyid Detail key ID (required when forGrid = "N")
	 * @return Map containing count and exists flag
	 */
	/**
	 * Check if update exists for Conditional Appraisal Entry
	 * @param masterEntry The master entry object containing FLID, DATE, and optionally CDAPKEYID
	 * @param forGrid Flag to determine query type ("N" = with join, other = without join)
	 * @return Map containing count and exists flag
	 */
	/**
	 * Check if update exists for Conditional Appraisal Entry
	 * @param masterEntry The master entry object containing FLID, DATE, and optionally CDAPKEYID
	 * @param forGrid Flag to determine query type ("N" = with join, other = without join)
	 * @return String JSON response containing count and exists flag
	 */
	public String checkUpdate(String flid, String date, String cdapKeyid, String forGrid) 
	        throws IOException {
	    
	    String apiUrl = "/conditional-appraisal/check-update";
	    
	    // Build query parameters
	    StringBuilder urlWithParams = new StringBuilder(apiUrl);
	    urlWithParams.append("?forGrid=").append(URLEncoder.encode(forGrid, StandardCharsets.UTF_8));
	    
	    // Add cdapkeyid as query param if available and forGrid is "N"
	    if ("N".equals(forGrid) && cdapKeyid != null && !cdapKeyid.trim().isEmpty()) {
	        urlWithParams.append("&cdapkeyid=").append(URLEncoder.encode(cdapKeyid, StandardCharsets.UTF_8));
	    }
	    
	    // Build minimal JSON payload with only required fields
	    String jsonPayload = String.format(
	        "{\"flid\":\"%s\",\"date\":\"%s\"}", 
	        flid != null ? flid.replace("\"", "\\\"") : "", 
	        date != null ? date.replace("\"", "\\\"") : ""
	    );
	    
	    CommonMessage.debugMsg("Check Update Request URL: " + urlWithParams.toString());
	    CommonMessage.debugMsg("Check Update JSON Payload: " + jsonPayload);
	    
	    // Send POST request
	    HttpResponse res = api.makeAuthRequest(urlWithParams.toString(), "POST", jsonPayload);
	    
	    CommonMessage.debugMsg("Check Update Response Status: " + res.getStatusCode());
	    
	    String jsonResponse = res.getBody();
	    CommonMessage.debugMsg("Check Update Response: " + jsonResponse);
	    
	    return jsonResponse;
	}
	/**
	 * Recall Conditional Appraisal detail by detail keyid
	 * Returns grid data with header row and detail record
	 * @param keyid Detail key ID
	 * @return List of String arrays (row 0 = header, row 1+ = data)
	 */
	public List<String[]> recallConditionalAppraisalDetail(String keyid) throws IOException {
		
		String apiUrl = "/conditional-appraisal/recall-detail/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
		
		// Send GET request
		HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
		
		CommonMessage.debugMsg("Recall Detail Response: " + res.getBody());
		String json = res.getBody();
		
		// Parse JSON array
		JSONArray jsonArray = JSONArray.fromObject(json);
		List<String[]> result = convertJsonArrayToListWithColumnOrder(jsonArray);
		CommonMessage.debugMsg("Printing recall Details **************");
		for(String[] list : result) 
		{
			CommonMessage.debugMsg(Arrays.toString(list));
		}
		
		return result;
	}
	
	/**
	 * Delete Conditional Appraisal detail by keyid string
	 * @param keyid - the keyid of the detail to delete
	 * @return PlmTlConditionalappraisal object with the deleted keyid
	 * @throws Exception if deletion fails
	 */
	public PlmTlConditionalappraisal deleteConditionalAppraisalDetail(String keyid) throws Exception {
	    String apiUrl = "/conditional-appraisal/delete-detail/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
	    
	    CommonMessage.debugMsg("Deleting Conditional Appraisal detail with keyid: " + keyid);
	    
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
	        CommonMessage.debugMsg("Conditional Appraisal detail deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
	        
	        // Create and return a PlmTlConditionalappraisal object with the deleted keyid
	        PlmTlConditionalappraisal deletedDetail = new PlmTlConditionalappraisal();
	        deletedDetail.setCdapKeyid(deletedId);
	        
	        return deletedDetail;
	    } else {
	        System.err.println("Failed to delete Conditional Appraisal detail. Message: " + message);
	        throw new RuntimeException("Failed to delete Conditional Appraisal detail: " + message);
	    }
	}

	/**
	 * Delete Conditional Appraisal detail by PlmTlConditionalappraisal object
	 * Overloaded method - extracts keyid from object and calls the main delete method
	 * @param plmTlConditionalappraisal - the detail object containing the keyid
	 * @return PlmTlConditionalappraisal object with the deleted keyid
	 * @throws Exception if deletion fails or if object/keyid is null/empty
	 */
	public PlmTlConditionalappraisal deleteConditionalAppraisalDetail(PlmTlConditionalappraisal plmTlConditionalappraisal) throws Exception {
	    // Validate input
	    if (plmTlConditionalappraisal == null) {
	        throw new IllegalArgumentException("PlmTlConditionalappraisal object cannot be null");
	    }
	    
	    String keyid = plmTlConditionalappraisal.getCdapKeyid();
	    
	    if (keyid == null || keyid.trim().isEmpty()) {
	        throw new IllegalArgumentException("Detail keyid cannot be null or empty");
	    }
	    
	    // Call the main delete method with the extracted keyid and return the result
	    return deleteConditionalAppraisalDetail(keyid);
	}
	/**
	 * Build JSON payload for save/update operation
	 */
	private String buildSaveJson(PlmTlConditionalappraisalmst conditionalAppraisalMst) {
		StringBuilder str = new StringBuilder();

		str.append("{");

		// Master Details
		str.append("\"master\":");
		String jsonMst = conditionalAppraisalMst.toJsonManual();
		str.append(jsonMst);
		str.append(",");

		// Detail Table Values - Note: We only have one detail object in the model
		str.append("\"details\":");
		PlmTlConditionalappraisal detailObj = conditionalAppraisalMst.getPlmTlConditionalappraisal();
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
	 * Build JSON payload for save-entry operation
	 */
	private String buildSaveEntryJson(PlmTlConditionalappraisalmstentry conditionalAppraisalMstEntry) {
		StringBuilder str = new StringBuilder();

		str.append("{");

		// Master Entry Details
		str.append("\"masterEntry\":");
		String jsonMstEntry = conditionalAppraisalMstEntry.toJsonManual();
		str.append(jsonMstEntry);
		str.append(",");

		// Detail Entry Table Values
		str.append("\"detailsEntry\":");
		PlmTlConditionalappraisalEntry detailEntryObj = conditionalAppraisalMstEntry.getPlmTlConditionalappraisal();
		if (detailEntryObj != null) {
			// Wrap single detail entry object in array
			str.append("[");
			str.append(detailEntryObj.toJsonManual());
			str.append("]");
		} else {
			str.append("[]");
		}

		str.append("}");

		return str.toString();
	}

	/**
	 * Parse JSON response into PlmTlConditionalappraisalmst object
	 */
	private PlmTlConditionalappraisalmst parseConditionalAppraisalResponse(String json) {
		JSONObject jsonObj = JSONObject.fromObject(json);

		// Parse master
		JSONObject mstObj = jsonObj.getJSONObject("master");
		PlmTlConditionalappraisalmst mst = PlmTlConditionalappraisalmst.fromJson(mstObj.toString());

		// Parse details array - take first detail if exists
		if (jsonObj.has("details") && !jsonObj.isNullObject() && 
			!jsonObj.get("details").equals(null)) {

			JSONArray dtlArray = jsonObj.getJSONArray("details");
			if (dtlArray.length() > 0) {
				JSONObject dtlObj = dtlArray.getJSONObject(0);
				PlmTlConditionalappraisal dtl = PlmTlConditionalappraisal.fromJson(dtlObj.toString());
				mst.setPlmTlConditionalappraisal(dtl);
			}
		}

		return mst;
	}

	/**
	 * Parse JSON response into PlmTlConditionalappraisalmstentry object
	 */
	private PlmTlConditionalappraisalmstentry parseConditionalAppraisalEntryResponse(String json) {
		JSONObject jsonObj = JSONObject.fromObject(json);

		// Parse master entry
		JSONObject mstEntryObj = jsonObj.getJSONObject("masterEntry");
		PlmTlConditionalappraisalmstentry mstEntry = PlmTlConditionalappraisalmstentry.fromJson(mstEntryObj.toString());

		// Parse details entry array - take first detail entry if exists
		if (jsonObj.has("detailsEntry") && !jsonObj.isNullObject() && 
			!jsonObj.get("detailsEntry").equals(null)) {

			JSONArray dtlEntryArray = jsonObj.getJSONArray("detailsEntry");
			if (dtlEntryArray.length() > 0) {
				JSONObject dtlEntryObj = dtlEntryArray.getJSONObject(0);
				PlmTlConditionalappraisalEntry dtlEntry = PlmTlConditionalappraisalEntry.fromJson(dtlEntryObj.toString());
				mstEntry.setPlmTlConditionalappraisal(dtlEntry);
			}
		}

		return mstEntry;
	}

	/**
	 * Convert JSONArray to List<String[]> with column ordering
	 * Row 0 contains numeric column order mapping (1, 2, 3...)
	 * Subsequent rows contain actual data ordered by those numbers
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
				//row[colIndex++] = value != null ? value.toString() : "";
				//row[colIndex++] = (value != null && !value.toString().equals("null")) ? value.toString() : "";
				
				row[colIndex++] = (value != null && !value.toString().equals("null") && !value.toString().equals("{}") && !value.toString().equals("-")) ? value.toString() : "";
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
	 * Delete Conditional Appraisal by master keyid
	 */
	public boolean deleteConditionalAppraisal(String masterKeyid) throws IOException {
		String apiUrl = "/conditional-appraisal/delete/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);

		// Send DELETE request
		HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

		CommonMessage.debugMsg("Delete Response Status: " + res.getStatusCode());

		return res.getStatusCode() >= 200 && res.getStatusCode() < 300;
	}
	
	/**
	 * Delete Conditional Appraisal Master and all its details
	 * @param plmTlConditionalappraisalmst - the master object containing the keyid
	 * @return PlmTlConditionalappraisalmst object with the deleted keyid
	 * @throws Exception if deletion fails or if object/keyid is null/empty
	 */
	public PlmTlConditionalappraisalmst deleteConditionalAppraisalMaster(PlmTlConditionalappraisalmst plmTlConditionalappraisalmst) throws Exception {
	    // Validate input
	    if (plmTlConditionalappraisalmst == null) {
	        throw new IllegalArgumentException("PlmTlConditionalappraisalmst object cannot be null");
	    }
	    
	    String masterKeyid = plmTlConditionalappraisalmst.getCdamKeyid();
	    
	    if (masterKeyid == null || masterKeyid.trim().isEmpty()) {
	        throw new IllegalArgumentException("Master keyid cannot be null or empty");
	    }
	    
	    String apiUrl = "/conditional-appraisal/delete-master/" + URLEncoder.encode(masterKeyid, StandardCharsets.UTF_8);
	    
	    CommonMessage.debugMsg("Deleting Conditional Appraisal master with keyid: " + masterKeyid);
	    
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
	        CommonMessage.debugMsg("Conditional Appraisal master deleted successfully. DeletedId: " + deletedId + ", Message: " + message);
	        
	        // Create and return a PlmTlConditionalappraisalmst object with the deleted keyid
	        PlmTlConditionalappraisalmst deletedMaster = new PlmTlConditionalappraisalmst();
	        deletedMaster.setCdamKeyid(deletedId);
	        
	        return deletedMaster;
	    } else {
	        System.err.println("Failed to delete Conditional Appraisal master. Message: " + message);
	        throw new RuntimeException("Failed to delete Conditional Appraisal master: " + message);
	    }
	}
}