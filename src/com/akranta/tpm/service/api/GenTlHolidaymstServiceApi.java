package com.akranta.tpm.service.api;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.GenTlHolidaymst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlHolidaymstServiceApi {
    private final Api api;

    // legacy screen sends dates like "28-Sep-2026"
    //private static final DateTimeFormatter LEGACY_DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    // legacy audit timestamps like "09-Sep-2026 09:28:11" (from CommonFunctions.dateTimeNow())
   // private static final DateTimeFormatter LEGACY_DATETIME_FORMAT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
    
    private static final DateTimeFormatter LEGACY_DATE_FORMAT = 
    	    DateTimeFormatter.ofPattern("dd-MMM-yyyy", java.util.Locale.ENGLISH);
    private static final DateTimeFormatter LEGACY_DATETIME_FORMAT = 
    	    DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", java.util.Locale.ENGLISH);

    public GenTlHolidaymstServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    /**
     * Save Holiday (handles both insert and update)
     */
    public GenTlHolidaymst saveHoliday(GenTlHolidaymst genTlHolidaymst) throws Exception {
        String apiUrl = "/holiday/save";

        String jsonPayload = buildSaveJson(genTlHolidaymst);
        CommonMessage.debugMsg("Holiday JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("Response Status Code: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("JSON Response: " + jsonResponse);

        // fail loudly instead of returning a null-filled object on error
        if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
            throw new RuntimeException("Save Holiday failed [" + res.getStatusCode() + "]: " + jsonResponse);
        }

        GenTlHolidaymst result = parseHolidayResponse(jsonResponse);
        CommonMessage.debugMsg("Parsed Result: " + result);

        return result;
    }

    public GenTlHolidaymst getHoliday(String keyid) throws IOException {
        String apiUrl = "/holiday/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("Get Holiday Response: " + res.getBody());
        return parseHolidayResponse(res.getBody());
    }

    public List<GenTlHolidaymst> getAllHolidays(String factory, String flag, String year) throws IOException {
        StringBuilder apiUrl = new StringBuilder("/holiday?");

        if (factory != null && !factory.trim().isEmpty())
            apiUrl.append("factory=").append(URLEncoder.encode(factory, StandardCharsets.UTF_8)).append("&");
        if (flag != null && !flag.trim().isEmpty())
            apiUrl.append("flag=").append(URLEncoder.encode(flag, StandardCharsets.UTF_8)).append("&");
        if (year != null && !year.trim().isEmpty())
            apiUrl.append("year=").append(URLEncoder.encode(year, StandardCharsets.UTF_8));

        HttpResponse res = api.makeAuthRequest(apiUrl.toString(), "GET", null);
        CommonMessage.debugMsg("Get All Holidays Response: " + res.getBody());

        JSONArray jsonArray = JSONArray.fromObject(res.getBody());
        List<GenTlHolidaymst> holidayList = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            holidayList.add(parseHolidayResponse(jsonArray.getJSONObject(i).toString()));
        }
        return holidayList;
    }

    public boolean deleteHoliday(String keyid) throws IOException {
        if (keyid == null || keyid.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyid is required to delete a Holiday");
        }

        String apiUrl = "/holiday/" + URLEncoder.encode(keyid, StandardCharsets.UTF_8);
        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("Delete Holiday Response: " + res.getBody());

        JSONObject responseObj = JSONObject.fromObject(res.getBody());
        boolean success = responseObj.optBoolean("success", false);
        CommonMessage.debugMsg("Delete successful: " + success);
        return success;
    }

    /**
     * Build JSON payload for save/update.
     * date, createdon, modifiedon must all be sent as full ISO-8601
     * LocalDateTime strings (yyyy-MM-ddTHH:mm:ss) because the Spring Boot
     * entity fields are java.time.LocalDateTime, not LocalDate.
     */
    private String buildSaveJson(GenTlHolidaymst h) {
        JSONObject obj = new JSONObject();
        obj.put("keyid", h.getHolmKeyid());
        obj.put("name", h.getHolmName());
        obj.put("date", normalizeToIsoDateTime(h.getHolmDate()));
        obj.put("description", h.getHolmDescription());
        obj.put("factory", h.getHolmFactory());
        obj.put("holidayflag", h.getHolmHolidayflag());
        obj.put("createdby", h.getHolmCreatedby());
        obj.put("createdon", normalizeToIsoDateTime(h.getHolmCreatedon()));
        obj.put("modifiedon", normalizeToIsoDateTime(h.getHolmModifiedon()));
        return obj.toString();
    }

    /**
     * Accepts any of the formats the legacy screen might send:
     *   - "28-Sep-2026"                (date only, legacy)
     *   - "09-Sep-2026 09:28:11"       (date+time, legacy audit fields)
     *   - "2026-09-28"                 (already ISO date)
     *   - "2026-09-28T00:00:00"        (already full ISO)
     * and always emits full ISO "yyyy-MM-ddTHH:mm:ss" for Spring Boot's
     * LocalDateTime deserializer.
     */
    private String normalizeToIsoDateTime(String rawValue) {
        if (rawValue == null || rawValue.trim().isEmpty()) {
            return null;
        }
        String trimmed = rawValue.trim();

        // already full ISO with 'T' -> leave as-is
        if (trimmed.contains("T")) {
            return trimmed;
        }

        try {
            // plain ISO date only, e.g. "2026-09-28"
            if (trimmed.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return LocalDate.parse(trimmed).atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            // legacy date+time, e.g. "09-Sep-2026 09:28:11"
            if (trimmed.matches("\\d{2}-[A-Za-z]{3}-\\d{4} \\d{2}:\\d{2}:\\d{2}")) {
                LocalDateTime ldt = LocalDateTime.parse(trimmed, LEGACY_DATETIME_FORMAT);
                return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            }
            // legacy date only, e.g. "28-Sep-2026"
            LocalDate ld = LocalDate.parse(trimmed, LEGACY_DATE_FORMAT);
            return ld.atStartOfDay().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        } catch (DateTimeParseException e) {
            CommonMessage.debugMsg("Could not normalize date/time '" + trimmed + "': " + e.getMessage());
            return trimmed; // let the backend return a clear validation error instead of silently guessing
        }
    }

    private GenTlHolidaymst parseHolidayResponse(String json) {
        JSONObject obj = JSONObject.fromObject(json);

        GenTlHolidaymst h = new GenTlHolidaymst();
        h.setHolmKeyid(obj.optString("keyid", null));
        h.setHolmName(obj.optString("name", null));
        h.setHolmDate(obj.optString("date", null));
        h.setHolmDescription(obj.optString("description", null));
        h.setHolmFactory(obj.optString("factory", null));
        h.setHolmHolidayflag(obj.optString("holidayflag", null));
        h.setHolmCreatedby(obj.optString("createdby", null));
        h.setHolmCreatedon(obj.optString("createdon", null));
        h.setHolmModifiedon(obj.optString("modifiedon", null));

        return h;
    }
}