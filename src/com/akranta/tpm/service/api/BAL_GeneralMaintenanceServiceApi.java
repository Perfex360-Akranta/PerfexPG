package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Service API class to call the Spring Boot General Maintenance REST endpoints.
 * Endpoints (PlmTlGenmaintenanceController, Spring Boot side):
 *   POST   /genmaintenance/save          � handles BOTH insert and update
 *   GET    /genmaintenance/{keyid}
 *   GET    /genmaintenance
 *   DELETE /genmaintenance/delete/{keyid}
 */
public class BAL_GeneralMaintenanceServiceApi {

    private final Api api;

    public BAL_GeneralMaintenanceServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    // =========================================================================
    //  SAVE  �  INSERT when gmntKeyid is blank, UPDATE when gmntKeyid is present
    // =========================================================================
    public BAL_PlmTlGenmaintenance saveGeneralmainteneance(BAL_PlmTlGenmaintenance genMaintenance) throws Exception {
        try {
            String apiUrl = "/genmaintenance/save";

            String jsonPayload = buildSaveJson(genMaintenance);
            System.out.println("GenMaintenance Save JSON :: " + jsonPayload);

            HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
            System.out.println("Response Status Code: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            System.out.println("JSON Response: " + jsonResponse);

            if (res.getStatusCode() == 404) {
                throw new Exception("General Maintenance not found for keyid: " + genMaintenance.getGmntKeyid());
            }
            // Spring Boot controller returns 201 (CREATED) for insert, 200 (OK) for update
            if (res.getStatusCode() != 201 && res.getStatusCode() != 200) {
                throw new Exception("Failed to save General Maintenance. Status: " + res.getStatusCode()
                        + " | Body: " + jsonResponse);
            }

            BAL_PlmTlGenmaintenance result = parseResponse(jsonResponse);
            System.out.println("Parsed Result: " + result);

            return result;

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("Failed to save General Maintenance: " + e.getMessage(), e);
        }
    }

    // =========================================================================
    //  FIND BY KEYID
    // =========================================================================
	/*
	 * public BAL_PlmTlGenmaintenance getGenMaintenanceByKeyid(String keyid) throws
	 * Exception { String apiUrl = "/genmaintenance/" + URLEncoder.encode(keyid,
	 * StandardCharsets.UTF_8);
	 * 
	 * System.out.println("Fetching GenMaintenance with keyid: " + keyid);
	 * 
	 * HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
	 * System.out.println("Response Status Code: " + res.getStatusCode());
	 * 
	 * String jsonResponse = res.getBody(); System.out.println("JSON Response: " +
	 * jsonResponse);
	 * 
	 * if (res.getStatusCode() == 404) { throw new
	 * Exception("General Maintenance not found for keyid: " + keyid); } if
	 * (res.getStatusCode() != 200) { throw new
	 * Exception("Failed to fetch General Maintenance. Status: " +
	 * res.getStatusCode() + " | Body: " + jsonResponse); } if (jsonResponse == null
	 * || jsonResponse.trim().isEmpty()) { throw new
	 * RuntimeException("No record found for keyid: " + keyid); }
	 * 
	 * BAL_PlmTlGenmaintenance result = parseResponse(jsonResponse);
	 * System.out.println("Parsed Result: " + result);
	 * 
	 * return result; }
	 */
    //mano
    public BAL_PlmTlGenmaintenance getGenMaintenanceByKeyid(String keyid) throws Exception {
        String apiUrl = "/genmaintenance/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

        System.out.println("Fetching GenMaintenance with keyid: " + keyid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        if (res.getStatusCode() == 404) {
            throw new Exception("General Maintenance not found for keyid: " + keyid);
        }
        if (res.getStatusCode() != 200) {
            throw new Exception("Failed to fetch General Maintenance. Status: " + res.getStatusCode()
                    + " | Body: " + jsonResponse);
        }
        if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
            throw new RuntimeException("No record found for keyid: " + keyid);
        }

        // ✅ FIX: convert ISO date/timestamp values in the JSON back into the
        // old UI format (dd-MMM-yyyy / dd-MMM-yyyy HH:mm:ss) before parsing,
        // same fix applied in BdmServiceApi.
        JSONObject jsonObj = JSONObject.fromObject(jsonResponse);
        jsonObj = sanitizeDates(jsonObj);

        BAL_PlmTlGenmaintenance result = parseResponse(jsonObj.toString());
        System.out.println("Parsed Result: " + result);

        return result;
    }

    // ==========================================================
    // ✅ FIX: Convert any ISO date/timestamp value in the JSON
    // response into the old UI format before binding to a POJO.
    //   Timestamp: 2026-07-19T03:54:00[.723][Z|+05:30] -> 19-Jul-2026 03:54:00
    //   Date only: 2026-07-19                          -> 19-Jul-2026
    // ==========================================================
    // ==========================================================
    // ✅ FIX: Convert any ISO date/timestamp value in the JSON
    // response into the old UI format before binding to a POJO.
    //   Timestamp: 2026-07-19T03:54:00[.723][Z|+05:30] -> 19-Jul-2026 03:54:00
    //   Date only: 2026-07-19                          -> 19-Jul-2026
    // ==========================================================
    private JSONObject sanitizeDates(JSONObject obj) {

        JSONArray keys = obj.names();

        for (int i = 0; i < keys.length(); i++) {
            String key = keys.getString(i);

            Object rawVal = obj.get(key);
            if (rawVal == null || "null".equals(String.valueOf(rawVal))) {
                continue;
            }

            String v = rawVal.toString();

            if (v.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.*$")) {

                if (v.contains(".")) {
                    v = v.substring(0, v.indexOf("."));
                }
                if (v.endsWith("Z")) {
                    v = v.substring(0, v.length() - 1);
                }
                int plusIdx = v.indexOf('+', 10);
                if (plusIdx > -1) {
                    v = v.substring(0, plusIdx);
                }

                obj.put(key, CommonFunctions.pg_getDateTimeFromPGTimeStamp(v));

            } else if (v.matches("^\\d{4}-\\d{2}-\\d{2}$")) {

                obj.put(key, CommonFunctions.pg_getFormatDateFromDate(v));
            }
        }

        return obj;
    }
    /**
     * GET FILL VALUE
     * Legacy BAL_PlmTlGenmaintenanceDaoImpl.getFillValue(docno) mirror
     */
    public BAL_PlmTlGenmaintenance getFillValue(String keyid) throws Exception {
        try {
            String apiUrl = "/genmaintenance/fillvalue/" 
                    + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

            System.out.println("getFillValue API call for keyid: " + keyid);

            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
            System.out.println("getFillValue Response Status: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            System.out.println("getFillValue JSON Response: " + jsonResponse);

            if (res.getStatusCode() == 404) {
                throw new Exception("Record not found for keyid: " + keyid);
            }
            if (res.getStatusCode() == 400) {
                throw new Exception("Bad request for keyid: " + keyid);
            }
            if (res.getStatusCode() != 200) {
                throw new Exception("getFillValue failed. Status: " 
                        + res.getStatusCode() + " | Body: " + jsonResponse);
            }
            if (jsonResponse == null || jsonResponse.trim().isEmpty()) {
                throw new RuntimeException("Empty response for keyid: " + keyid);
            }

            BAL_PlmTlGenmaintenance result = parseResponse(jsonResponse);
            System.out.println("getFillValue - isSpares: " + result.getIsSpares()
                    + " | erpStatus: " + result.getErrppostStatus());
            return result;

        } catch (IOException e) {
            throw new Exception("getFillValue IO error: " + e.getMessage(), e);
        }
    }

    // =========================================================================
    //  GET ALL (active records only)
    // =========================================================================
    public List<BAL_PlmTlGenmaintenance> getAllGenMaintenance() throws Exception {
        String apiUrl = "/genmaintenance";

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        System.out.println("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        System.out.println("JSON Response: " + jsonResponse);

        List<BAL_PlmTlGenmaintenance> resultList = new ArrayList<>();

        if (jsonResponse == null || jsonResponse.trim().isEmpty() || jsonResponse.trim().equals("[]")) {
            return resultList;
        }

        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);

        // FIX: net.sf.json.JSONArray uses .size(), NOT .length()
        for (int i = 0; i < jsonArray.length(); i++) {
            BAL_PlmTlGenmaintenance item = parseResponse(jsonArray.getJSONObject(i).toString());
            resultList.add(item);
        }

        System.out.println("Retrieved GenMaintenance count: " + resultList.size());

        return resultList;
    }

    // =========================================================================
    //  DELETE by keyid (soft delete on Spring Boot side)
    // =========================================================================
    public BAL_PlmTlGenmaintenance delete(BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception {
        try {
            String keyid = plmTlGenmaintenance.getGmntKeyid();
            if (keyid == null || keyid.trim().isEmpty()) {
                throw new IllegalArgumentException("keyid cannot be null or empty");
            }

            String apiUrl = "/genmaintenance/delete/" 
                    + URLEncoder.encode(keyid, StandardCharsets.UTF_8);

            System.out.println("Delete API call for keyid: " + keyid);

            HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
            System.out.println("Delete Response Status: " + res.getStatusCode());
            System.out.println("Delete Response Body: " + res.getBody());

            if (res.getStatusCode() == 200) {
                System.out.println("Successfully deleted keyid: " + keyid);
                return plmTlGenmaintenance;
            } else if (res.getStatusCode() == 404) {
                throw new Exception("Record not found for keyid: " + keyid);
            } else if (res.getStatusCode() == 400) {
                throw new Exception("Bad request for keyid: " + keyid);
            } else {
                throw new Exception("Delete failed. Status: " + res.getStatusCode()
                        + " | Body: " + res.getBody());
            }

        } catch (IOException e) {
            throw new Exception("Delete IO error: " + e.getMessage(), e);
        }
    }

    // =========================================================================
    //  PRIVATE HELPERS
    // =========================================================================

    /**
     * Build JSON payload by delegating to the model's toJsonManual().
     */
    private String buildSaveJson(BAL_PlmTlGenmaintenance genMaintenance) {
        String json = genMaintenance.toJsonManual();
        System.out.println("GenMaintenance Save JSON: " + json);
        return json;
    }

    /**
     * Parse a JSON string response into BAL_PlmTlGenmaintenance.
     */
    private BAL_PlmTlGenmaintenance parseResponse(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);
        return BAL_PlmTlGenmaintenance.fromJson(jsonObj.toString());
    }
}