/*
 * package com.akranta.tpm.service.api;
 * 
 * import java.io.IOException; import java.util.List;
 * 
 * import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl; import
 * com.akranta.tpm.model.BAL_PlmTlMethodsmst; import
 * com.akranta.tpm.model.BAL_PlmTlPmsftpermitlink; import
 * com.akranta.tpm.model.BAL_PlmTlStandards; import
 * com.akranta.tpm.model.BAL_PlmTlToolsdtl; import
 * com.akranta.tpm.model.BdmTlYycountermeasurelink; import
 * com.akranta.tpm.model.HttpResponse; import
 * com.akranta.tpm.utils.CommonMessage;
 * 
 * import net.sf.json.JSONArray; import net.sf.json.JSONObject;
 * 
 * public class BAL_PlmStandardsServiceApi {
 * 
 * private final Api api;
 * 
 * public BAL_PlmStandardsServiceApi(String JwtToken) { this.api = new
 * Api(JwtToken); }
 * 
 * public BAL_PlmTlStandards saveApi( BAL_PlmTlStandards standards,
 * List<BAL_PlmTlMethodsmst> methods, List<BAL_PlmTlCbmstdcadtl> cbmDetails,
 * List<BAL_PlmTlToolsdtl> tools, List<BAL_PlmTlPmsftpermitlink> permitLinks,
 * BdmTlYycountermeasurelink countermeasureLink, String formActionMode, String
 * formMode, String formHeader) throws IOException {
 * 
 * String apiUrl = "/plmstandards/save";
 * 
 * // Build a single BALPlmStandardsDto shaped JSON object JSONObject dtoJson =
 * new JSONObject();
 * 
 * if (standards != null) { dtoJson.put("standards",
 * JSONObject.fromObject(standards.toJsonManual())); }
 * 
 * if (methods != null && !methods.isEmpty()) { JSONArray methodsArr = new
 * JSONArray(); for (BAL_PlmTlMethodsmst method : methods) {
 * methodsArr.put(JSONObject.fromObject(method.toJsonManual())); }
 * dtoJson.put("methods", methodsArr); }
 * 
 * if (cbmDetails != null && !cbmDetails.isEmpty()) { JSONArray cbmArr = new
 * JSONArray(); for (BAL_PlmTlCbmstdcadtl cbm : cbmDetails) {
 * cbmArr.put(JSONObject.fromObject(cbm.toJsonManual())); }
 * dtoJson.put("cbmDetails", cbmArr); }
 * 
 * if (tools != null && !tools.isEmpty()) { JSONArray toolsArr = new
 * JSONArray(); for (BAL_PlmTlToolsdtl tool : tools) {
 * toolsArr.put(JSONObject.fromObject(tool.toJsonManual())); }
 * dtoJson.put("tools", toolsArr); }
 * 
 * if (permitLinks != null && !permitLinks.isEmpty()) { JSONArray permitArr =
 * new JSONArray(); for (BAL_PlmTlPmsftpermitlink permitLink : permitLinks) {
 * permitArr.put(JSONObject.fromObject(permitLink.toJsonManual())); }
 * dtoJson.put("permitLinks", permitArr); }
 * 
 * if (countermeasureLink != null) { dtoJson.put("countermeasureLink",
 * JSONObject.fromObject(countermeasureLink.toJsonManual())); }
 * 
 * if (formActionMode != null) { dtoJson.put("formActionMode", formActionMode);
 * } if (formMode != null) { dtoJson.put("formMode", formMode); } if (formHeader
 * != null) { dtoJson.put("formHeader", formHeader); }
 * 
 * // Controller expects List<BALPlmStandardsDto> -> wrap as single-element
 * array JSONArray requestArray = new JSONArray(); requestArray.put(dtoJson);
 * String jsonPayload = requestArray.toString();
 * 
 * CommonMessage.debugMsg("Json PlmStandards Save:: " + jsonPayload);
 * 
 * HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
 * 
 * CommonMessage.debugMsg(res.getStatusCode());
 * 
 * String jsonResponse = res.getBody(); CommonMessage.debugMsg("JSON Response: "
 * + jsonResponse);
 * 
 * JSONArray responseArray = JSONArray.fromObject(jsonResponse); JSONObject
 * firstDto = responseArray.getJSONObject(0); JSONObject savedStandardsJson =
 * firstDto.getJSONObject("standards");
 * 
 * return BAL_PlmTlStandards.fromJson(savedStandardsJson.toString()); } }
 */


package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlStandards;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class BAL_PlmStandardsServiceApi {
	private final Api api;

	public BAL_PlmStandardsServiceApi(String jwtToken) {
		this.api = new Api(jwtToken);
	}

	/**
	 * Single-row save/update — matches the old DAO-style call:
	 *   BAL_PlmTlStandards plmTlStandards = pmsdapi.savePlmTlStandards(newPlmTlStandards, dkeyId);
	 *
	 * Wraps the single row as a one-element "details" list and posts to the
	 * same /plm-tl-standards/save endpoint used for the multi-row screen.
	 * keyid empty -> INSERT, keyid present -> UPDATE (decided server-side).
	 */
	public BAL_PlmTlStandards savePlmTlStandards(BAL_PlmTlStandards newPlmTlStandards, String dkeyId) throws Exception {
		List<BAL_PlmTlStandards> details = new ArrayList<>();
		details.add(newPlmTlStandards);

		String apiUrl = "/plm-tl-standards/save";
		String jsonPayload = buildSaveJsonSingle(details, dkeyId);

		CommonMessage.debugMsg("PM Standards JSON (single) :: " + jsonPayload);

		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		List<BAL_PlmTlStandards> result = parsePlmTlStandardsResponse(jsonResponse);
		if (result.isEmpty()) {
			throw new Exception("PM Standard save returned no data");
		}

		BAL_PlmTlStandards saved = result.get(0);
		CommonMessage.debugMsg("Saved PM Standard Keyid: " + saved.getPmsdKeyid());
		return saved;
	}

	/**
	 * Multi-row save/update — for the multiple-rows PM Standards screen.
	 * Header/location fields (factory, section, cell, machine, locationid, flid)
	 * are common to every row and get stamped by the backend on each detail row.
	 */
	public List<BAL_PlmTlStandards> savePlmTlStandards(List<BAL_PlmTlStandards> details,
			String factoryId, String sectionId, String cellId, String machineId,
			String locationId, String flId) throws Exception {

		String apiUrl = "/plm-tl-standards/save";

		String jsonPayload = buildSaveJson(details, factoryId, sectionId, cellId, machineId, locationId, flId);

		CommonMessage.debugMsg("PM Standards JSON :: " + jsonPayload);

		HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

		CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

		String jsonResponse = res.getBody();
		CommonMessage.debugMsg("JSON Response: " + jsonResponse);

		List<BAL_PlmTlStandards> result = parsePlmTlStandardsResponse(jsonResponse);
		CommonMessage.debugMsg("Parsed Result Size: " + result.size());

		return result;
	}

	/**
	 * Build JSON payload for the single-row save/update overload.
	 * { "docKey":"...", "details":[ {row} ] }
	 */
	private String buildSaveJsonSingle(List<BAL_PlmTlStandards> details, String dkeyId) {
		StringBuilder str = new StringBuilder();

		str.append("{");
		str.append("\"docKey\":\"").append(nullSafe(dkeyId)).append("\",");
		str.append("\"details\":");
		str.append(BAL_PlmTlStandards.toJsonManualList(details));
		str.append("}");

		return str.toString();
	}

	/**
	 * Build JSON payload for the multi-row save/update operation.
	 * {
	 *   "factoryId":"...", "sectionId":"...", "cellId":"...",
	 *   "machineId":"...", "locationId":"...", "flId":"...",
	 *   "details":[ {row1}, {row2}, ... ]
	 * }
	 */
	private String buildSaveJson(List<BAL_PlmTlStandards> details,
			String factoryId, String sectionId, String cellId, String machineId,
			String locationId, String flId) {

		StringBuilder str = new StringBuilder();

		str.append("{");
		str.append("\"factoryId\":\"").append(nullSafe(factoryId)).append("\",");
		str.append("\"sectionId\":\"").append(nullSafe(sectionId)).append("\",");
		str.append("\"cellId\":\"").append(nullSafe(cellId)).append("\",");
		str.append("\"machineId\":\"").append(nullSafe(machineId)).append("\",");
		str.append("\"locationId\":\"").append(nullSafe(locationId)).append("\",");
		str.append("\"flId\":\"").append(nullSafe(flId)).append("\",");

		// Detail rows
		str.append("\"details\":");
		str.append(BAL_PlmTlStandards.toJsonManualList(details));

		str.append("}");

		return str.toString();
	}

	/**
	 * Parse JSON response into a List<BAL_PlmTlStandards> (the saved/updated
	 * rows, now carrying the generated keyids for the new ones).
	 */
	private List<BAL_PlmTlStandards> parsePlmTlStandardsResponse(String json) {
		List<BAL_PlmTlStandards> dtlList = new ArrayList<>();

		JSONObject jsonObj = JSONObject.fromObject(json);

		if (jsonObj.has("details") && !jsonObj.isNullObject()
				&& !jsonObj.get("details").equals(null)) {

			JSONArray dtlArray = jsonObj.getJSONArray("details");
			dtlList = BAL_PlmTlStandards.fromJsonList(dtlArray.toString());
		}

		return dtlList;
	}

	private String nullSafe(String value) {
		return value == null ? "" : value;
	}

	public boolean deletePlmTlStandards(String keyId) throws Exception {
	    String apiUrl = "/plm-tl-standards/delete/" + keyId;

	    HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);

	    CommonMessage.debugMsg("Delete Response Status Code: " + res.getStatusCode());

	    if (res.getStatusCode() != 204 && res.getStatusCode() != 200) {
	        throw new Exception("PM Standard delete failed, status: " + res.getStatusCode());
	    }

	    return true;
	}
}
