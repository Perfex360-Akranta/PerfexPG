package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlSparedtl;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class BAL_PlmTlSparedtlServiceApi {
    private final Api api;

    public BAL_PlmTlSparedtlServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Spares Pickup details for a Standard (handles both insert and
     * update per row). standardId and createdBy are read off the first row
     * in the list — fillValues() already stamps every row with both before
     * this is called, so the caller only needs to pass the list.
     *
     * @param sparesList list of spare rows to save/update, all belonging to the same standard
     * @return Saved/Updated list of BAL_PlmTlSparedtl with generated keyids
     */
    public List<BAL_PlmTlSparedtl> saveSparesPickup(List<BAL_PlmTlSparedtl> sparesList) throws Exception {

        if (sparesList == null || sparesList.isEmpty()) {
            throw new IllegalArgumentException("Spares list is empty - nothing to save");
        }

        BAL_PlmTlSparedtl firstRow = sparesList.get(0);
        String standardId = firstRow.getPspdStandardid();
        String createdBy = firstRow.getPspdCreatedby();

        String apiUrl = "/spares-pickup/save";

        String jsonPayload = buildSaveJson(standardId, sparesList, createdBy);

        CommonMessage.debugMsg("Spares Pickup JSON :: " + jsonPayload);

        // Send request
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // Parse response back to object
        List<BAL_PlmTlSparedtl> result = parseSparesPickupResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);

        return result;
    }

    /**
     * Builds the JSON payload matching BAL_PlmTlSparedtlRequest on the API side:
     * { "standardId": "...", "createdBy": "...", "details": [ {...}, {...} ] }
     */
    private String buildSaveJson(String standardId, List<BAL_PlmTlSparedtl> sparesList, String createdBy) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"standardId\":\"").append(standardId == null ? "" : standardId).append("\",");
        sb.append("\"createdBy\":\"").append(createdBy == null ? "" : createdBy).append("\",");
        sb.append("\"details\":");
        sb.append(BAL_PlmTlSparedtl.toJsonManualList(sparesList));
        sb.append("}");
        return sb.toString();
    }

    /**
     * The controller returns { "standardId":..., "details":[ {...} ], ... } —
     * pull the "details" array out of the response and hand it to fromJsonList.
     */
    private List<BAL_PlmTlSparedtl> parseSparesPickupResponse(String jsonResponse) {
        List<BAL_PlmTlSparedtl> list = new ArrayList<>();
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            return list;
        }

        JSONObject obj = JSONObject.fromObject(jsonResponse);
        JSONArray detailsArr = obj.optJSONArray("details");
        if (detailsArr != null) {
            list = BAL_PlmTlSparedtl.fromJsonList(detailsArr.toString());
        }
        return list;
    }
    
		public List<String[]> getSprPickup(String standardId) throws Exception {
		String apiUrl = "/spares-pickup/list/" + (standardId == null ? "" : standardId);

		HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

		CommonMessage.debugMsg("getSprPickup Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("getSprPickup JSON Response: " + jsonResponse);

		if (res.getStatusCode() != 200) {
			throw new Exception("Fetching Spares Pickup data failed, status: " + res.getStatusCode());
		}

		return parseSprPickupResponse(jsonResponse);
	}

	
	private List<String[]> parseSprPickupResponse(String json) {
		List<String[]> sprPkupList = new ArrayList<>();

		if (json == null || json.trim().isEmpty()) {
			return sprPkupList;
		}

		JSONArray rows = JSONArray.fromObject(json);

		for (int i = 0; i < rows.length(); i++) {
			JSONObject row = rows.getJSONObject(i);

			String[] rowArr = new String[] {
					jsonStr(row, "select_flag"),
					jsonStr(row, "pspd_spareid"),
					jsonStr(row, "pspd_keyid"),
					jsonStr(row, "standardid"),
					jsonStr(row, "sprm_partno"),
					jsonStr(row, "sprm_partname"),
					jsonStr(row, "sprm_make"),
					jsonStr(row, "sprm_model"),
					jsonStr(row, "pspd_quantity"),
					jsonStr(row, "pspd_modifiedon"),
					jsonStr(row, "pspd_createdon")
			};

			sprPkupList.add(rowArr);
		}

		return sprPkupList;
	}

	
	private String jsonStr(JSONObject row, String key) {
		if (!row.has(key) || row.isNullObject()) {
			return "";
		}
		Object val = row.get(key);
		if (val == null || net.sf.json.JSONNull.getInstance().equals(val)) {
			return "";
		}
		return String.valueOf(val);
	}
}