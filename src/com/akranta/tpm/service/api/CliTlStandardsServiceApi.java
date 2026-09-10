package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class CliTlStandardsServiceApi {
	private final Api api;

	public CliTlStandardsServiceApi(String jwtToken) {
		this.api = new Api(jwtToken);
	}

	/**
	 * Single-row save/update — matches the old DAO-style call:
	 *   BAL_CliTlStandards cliTlStandards = cltiapi.saveCliTlStandards(newCliTlStandards, dkeyId);
	 *
	 * Wraps the single row as a one-element "details" list and posts to the
	 * same /cli-tl-standards/save endpoint used for the multi-row screen.
	 * keyid empty -> INSERT, keyid present -> UPDATE (decided server-side).
	 */
	public BAL_CliTlStandards saveCliTlStandards(BAL_CliTlStandards newCliTlStandards, String dkeyId) throws Exception {
		List<BAL_CliTlStandards> details = new ArrayList<>();
		details.add(newCliTlStandards);

		String apiUrl = "/cli-tl-standards/save";
		String jsonPayload = buildSaveJsonSingle(details, dkeyId);

		CommonMessage.debugMsg("CLI Standards JSON (single) :: " + jsonPayload);

		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		List<BAL_CliTlStandards> result = parseCliTlStandardsResponse(jsonResponse);
		if (result.isEmpty()) {
			throw new Exception("CLI Standard save returned no data");
		}

		BAL_CliTlStandards saved = result.get(0);
		CommonMessage.debugMsg("Saved CLI Standard Keyid: " + saved.getClisKeyid());
		return saved;
	}

	/**
	 * Multi-row save/update — for the multiple-rows CLTI screen.
	 * Header/location fields (factory, section, cell, machine, flid, elementid)
	 * are common to every row and get stamped by the backend on each detail row.
	 */
	public List<BAL_CliTlStandards> saveCliTlStandards(List<BAL_CliTlStandards> details,
			String factoryId, String sectionId, String cellId, String machineId,
			String flId, String elementId) throws Exception {

		String apiUrl = "/cli-tl-standards/save";

		String jsonPayload = buildSaveJson(details, factoryId, sectionId, cellId, machineId, flId, elementId);

		CommonMessage.debugMsg("CLI Standards JSON :: " + jsonPayload);

		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		List<BAL_CliTlStandards> result = parseCliTlStandardsResponse(jsonResponse);
		CommonMessage.debugMsg("Parsed Result Size: " + result.size());

		return result;
	}

	/**
	 * Build JSON payload for the single-row save/update overload.
	 * { "docKey":"...", "details":[ {row} ] }
	 */
	private String buildSaveJsonSingle(List<BAL_CliTlStandards> details, String dkeyId) {
		StringBuilder str = new StringBuilder();

		str.append("{");
		str.append("\"docKey\":\"").append(nullSafe(dkeyId)).append("\",");
		str.append("\"details\":");
		str.append(BAL_CliTlStandards.toJsonManualList(details));
		str.append("}");

		return str.toString();
	}

	/**
	 * Build JSON payload for the multi-row save/update operation.
	 * {
	 *   "factoryId":"...", "sectionId":"...", "cellId":"...",
	 *   "machineId":"...", "flId":"...", "elementId":"...",
	 *   "details":[ {row1}, {row2}, ... ]
	 * }
	 */
	private String buildSaveJson(List<BAL_CliTlStandards> details,
			String factoryId, String sectionId, String cellId, String machineId,
			String flId, String elementId) {

		StringBuilder str = new StringBuilder();

		str.append("{");
		str.append("\"factoryId\":\"").append(nullSafe(factoryId)).append("\",");
		str.append("\"sectionId\":\"").append(nullSafe(sectionId)).append("\",");
		str.append("\"cellId\":\"").append(nullSafe(cellId)).append("\",");
		str.append("\"machineId\":\"").append(nullSafe(machineId)).append("\",");
		str.append("\"flId\":\"").append(nullSafe(flId)).append("\",");
		str.append("\"elementId\":\"").append(nullSafe(elementId)).append("\",");

		// Detail rows
		str.append("\"details\":");
		str.append(BAL_CliTlStandards.toJsonManualList(details));

		str.append("}");

		return str.toString();
	}

	/**
	 * Parse JSON response into a List<BAL_CliTlStandards> (the saved/updated
	 * rows, now carrying the generated keyids for the new ones).
	 */
	private List<BAL_CliTlStandards> parseCliTlStandardsResponse(String json) {
		List<BAL_CliTlStandards> dtlList = new ArrayList<>();

		JSONObject jsonObj = JSONObject.fromObject(json);

		if (jsonObj.has("details") && !jsonObj.isNullObject()
				&& !jsonObj.get("details").equals(null)) {

			JSONArray dtlArray = jsonObj.getJSONArray("details");
			dtlList = BAL_CliTlStandards.fromJsonList(dtlArray.toString());
		}

		return dtlList;
	}

	private String nullSafe(String value) {
		return value == null ? "" : value;
	}
	
	public boolean deleteCliTlStandards(String keyId) throws Exception {
	    String apiUrl = "/cli-tl-standards/delete/" + keyId;

	    HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

	    CommonMessage.debugMsg("Delete Response Status Code: " + res.getStatusCode());

	    if (res.getStatusCode() != 204 && res.getStatusCode() != 200) {
	        throw new Exception("CLI Standard delete failed, status: " + res.getStatusCode());
	    }

	    return true;
	}
}