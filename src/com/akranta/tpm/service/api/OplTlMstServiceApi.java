package com.akranta.tpm.service.api;

import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.model.HttpResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.GenTlDocupdates;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class OplTlMstServiceApi {

    private final Api api;



    public OplTlMstServiceApi(String jwtToken) {
    	
    	 CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
         CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
         CommonMessage.debugMsg("JWT(clean length)=" + cleanJwt(jwtToken).length());
         CommonMessage.debugMsg("JWT contains \\n ? " + cleanJwt(jwtToken).contains("\n"));
         CommonMessage.debugMsg("JWT contains \\r ? " + cleanJwt(jwtToken).contains("\r"));
        this.api = new Api(cleanJwt(jwtToken));
        

        }

    
    private String cleanJwt(String token) {
        if (token == null) return null;

        String t = token.trim();

        // remove accidental "Bearer " if already present
        if (t.toLowerCase().startsWith("bearer ")) {
            t = t.substring(7).trim();
        }

        // remove hidden line breaks (BIGGEST cause of 403 empty response)
        t = t.replace("\r", "").replace("\n", "");

        return t;
       
    }


    // -------------------------
    // CREATE (POST) - 3 PARAM VERSION
    // -------------------------
    public OplTlMst insertRecord(OplTlMst oplTlMst,
            BdmTlYycountermeasurelink bdmTlYycountermeasurelink,
            GenTlDocupdates genTlDocupdates) throws Exception {
    	
    	


if (oplTlMst == null) {
throw new IllegalArgumentException("oplTlMst is null");
}
// ✅ PRINT BEFORE CALL (prints each time)
CommonMessage.debugMsg("http.proxyHost=" + System.getProperty("http.proxyHost"));
CommonMessage.debugMsg("http.proxyPort=" + System.getProperty("http.proxyPort"));
CommonMessage.debugMsg("https.proxyHost=" + System.getProperty("https.proxyHost"));
CommonMessage.debugMsg("https.proxyPort=" + System.getProperty("https.proxyPort"));
CommonMessage.debugMsg("http.nonProxyHosts=" + System.getProperty("http.nonProxyHosts"));
String apiUrl = "/opl/save"; // BASE_URL already has /api
//0) DEBUG: verify Authorization header reaches Spring

//HttpResponse dbg = api.makeAuthRequest("/auth/debug-headers", "GET", null);
//CommonMessage.debugMsg("DEBUG /auth/debug-headers => " + dbg.getStatusCode() + " body=" + dbg.getBody());
//
//HttpResponse ping = api.makeAuthRequest("/opl", "GET", null);
//CommonMessage.debugMsg("DEBUG GET /opl => " + ping.getStatusCode() + " body=" + ping.getBody());


oplTlMst.setOplmActive("Y");

String jsonPayload = oplSaveJsonManual(oplTlMst, bdmTlYycountermeasurelink, genTlDocupdates);
CommonMessage.debugMsg("OPL SAVE (3 param) JSON :: " + jsonPayload);



HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

CommonMessage.debugMsg("OPL SAVE statusCode :: " + res.getStatusCode());
CommonMessage.debugMsg("OPL SAVE responseBody :: " + res.getBody());

// ✅ If forbidden, give a clear message + optional fallback
if (res.getStatusCode() == 403) {
// If you want: try normal /opl create as fallback (only works when link/doc are null)
CommonMessage.debugMsg("⚠️ /opl/save returned 403. Trying fallback /opl create...");

// fallback: call existing 1-param endpoint
OplTlMst fallbackSaved = insertRecord(oplTlMst);

CommonMessage.debugMsg("✅ Fallback /opl create success. Keyid = " + fallbackSaved.getOplmKeyid());
return fallbackSaved;
}

if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
throw new IllegalStateException("OPL SAVE failed. HTTP=" + res.getStatusCode() + " body=[" + res.getBody() + "]");
}

return oplFromJson(res.getBody());
}



    // -------------------------
    // EXISTING CREATE (POST) - 1 PARAM (KEEP IT)
    // -------------------------
    public OplTlMst insertRecord(OplTlMst oplTlMst) throws Exception {
        String apiUrl = "/opl";

        if (oplTlMst == null) {
            throw new IllegalArgumentException("oplTlMst is null");
        }
//        HttpResponse ping = api.makeAuthRequest("/opl", "GET", null);
//        CommonMessage.debugMsg("DEBUG GET /opl => " + ping.getStatusCode() + " body=" + ping.getBody());
        

        oplTlMst.setOplmActive("Y");

        String jsonPayload = oplJsonManual(oplTlMst);
        CommonMessage.debugMsg("OPL INSERT JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("OPL INSERT statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("OPL INSERT responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("OPL INSERT failed. HTTP=" + code + " body=[" + body + "]");
        }

        return oplFromJson(res.getBody());
    }

    // -------------------------
    // WRAPPER JSON (opl + yylink + docupdates)
    // IMPORTANT: DO NOT JSONObject.parse oplJsonManual()
    // -------------------------
    
    public static String pg_toIsoLocalDateTime(String inputDate) {
        if (inputDate == null) return null;

        String s = inputDate.trim();
        if (s.isEmpty()) return null;

        try {
            java.util.Date date;

            // If already ISO like 2026-02-03T00:00:00
            if (s.contains("T")) {
                SimpleDateFormat isoIn = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                date = isoIn.parse(s);
            }
            // If UI date like 03-Feb-2026
//            else if (s.matches("\\d{2}-[A-Za-z]{3}-\\d{4}")) {
//                SimpleDateFormat uiIn = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
//                date = uiIn.parse(s);
//            }
            else if (s.matches("\\d{1,2}-[A-Za-z]{3}-\\d{4}")) {
                // ✅ "d-MMM-yyyy" handles both 5-Feb-2026 and 03-Feb-2026
                SimpleDateFormat uiIn = new SimpleDateFormat("d-MMM-yyyy", Locale.ENGLISH);
                uiIn.setLenient(false);
                date = uiIn.parse(s);
            }
            // If PG timestamp string like 2026-02-03 00:00:00
            else if (s.matches("\\d{4}-\\d{2}-\\d{2}.*")) {
                SimpleDateFormat pgIn = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                date = pgIn.parse(s);
            } else {
                throw new IllegalArgumentException("Unsupported date format: " + s);
            }

            // ✅ Spring LocalDateTime ISO format
            SimpleDateFormat isoOut = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
            return isoOut.format(date);

        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to parse date: " + inputDate, e);
        }
    }
    public static String pg_getDateFlexible(String inputDate) {
        if (inputDate == null) return null;

        String s = inputDate.trim();
        if (s.isEmpty() || "{}".equals(s) || "-".equals(s)) return null;

        // ✅ If already ISO date, just return it (fast + safe)
        if (s.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return s;
        }

        // Try multiple input formats
        String[] patterns = new String[] {
            "dd-MMM-yyyy",            // 03-Feb-2026
            "dd-MMM-yyyy HH:mm:ss",   // 03-Feb-2026 09:43:17
            "yyyy-MM-dd HH:mm:ss",    // 2026-02-03 09:43:17
            "yyyy-MM-dd'T'HH:mm:ss"   // 2026-02-03T09:43:17
        };

        Date parsed = null;

        for (String p : patterns) {
            try {
                SimpleDateFormat in = new SimpleDateFormat(p, Locale.ENGLISH);
                in.setLenient(false);
                parsed = in.parse(s);
                if (parsed != null) break;
            } catch (Exception ignore) {
                // try next pattern
            }
        }

        if (parsed == null) {
            throw new IllegalArgumentException("Unable to parse date: " + inputDate);
        }

        // ✅ Output always ISO Date for DATE columns
        SimpleDateFormat out = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        return out.format(parsed);
    }

    
    private String oplSaveJsonManual(OplTlMst oplTlMst,
            BdmTlYycountermeasurelink link,
            GenTlDocupdates doc) {

String oplJson = oplJsonManual(oplTlMst);
if (oplJson == null || oplJson.trim().isEmpty()) {
throw new IllegalStateException("oplJsonManual() returned empty/null JSON");
}

// IMPORTANT: oplJsonManual must return "{...}" (not "[{...}]")
oplJson = oplJson.trim();

StringBuilder sb = new StringBuilder();
sb.append("{");
sb.append("\"oplTlMst\":").append(oplJson);
sb.append(",\"bdmTlYycountermeasurelink\":").append(link == null ? "null" : net.sf.json.JSONObject.fromObject(link).toString());
sb.append(",\"genTlDocupdates\":").append(doc == null ? "null" : net.sf.json.JSONObject.fromObject(doc).toString());
sb.append("}");
return sb.toString();
}
   
    private String oplJsonManual(OplTlMst o) {

        // ISO for Spring LocalDateTime: "yyyy-MM-dd'T'HH:mm:ss"
        String nowIso = CommonFunctions.pg_dateTimeNow();

        boolean isCreate = !CommonFunctions.isValidKeyId(o.getOplmKeyid());

        // 1) oplm_date (UI: dd-MMM-yyyy -> ISO LocalDateTime)
        if (CommonFunctions.isValidKeyId(o.getOplmDate())) {
           o.setOplmDate(pg_toIsoLocalDateTime(o.getOplmDate()));
        	// vignesh 03feb2026
          //  o.setOplmDate(o.getOplmDate());
        } else {
            o.setOplmDate(nowIso);
        }

        // 2) oplm_prepareddate (UI: dd-MMM-yyyy -> ISO LocalDateTime)
        if (CommonFunctions.isValidKeyId(o.getOplmPrepareddate())) {
        	// vignesh 03feb2026
            o.setOplmPrepareddate(pg_toIsoLocalDateTime(o.getOplmPrepareddate()));
        } else {
            o.setOplmPrepareddate(nowIso);
        }

        // 3) createdon / modifiedon (always ISO LocalDateTime)
        if (isCreate) {
            o.setOplmCreatedon(nowIso);
            o.setOplmApprovedid(null);
            o.setOplmApproveddate(null);
        }
        o.setOplmModifiedon(nowIso);

        CommonMessage.debugMsg("DEBUG approveddate raw = [" + o.getOplmApproveddate() + "]");
        CommonMessage.debugMsg("DEBUG approveddate iso = [" + pg_getDateFlexible(o.getOplmApproveddate()) + "]");
     

        // 4) approveddate is DATE column (UI: dd-MMM-yyyy -> yyyy-MM-dd)
        if (CommonFunctions.isValidKeyId(o.getOplmApproveddate())) {
        	// vignesh 03feb2026
            o.setOplmApproveddate(pg_getDateFlexible(o.getOplmApproveddate()));
        }

        return o.toJsonManual();
    }

    
    
//    private String oplJsonManual(OplTlMst o) {
//
//        // already ISO "yyyy-MM-dd'T'HH:mm:ss"
//        String nowIso = CommonFunctions.pg_dateTimeNow();
//        boolean isCreate = !CommonFunctions.isValidKeyId(o.getOplmKeyid());
//        // ----------------------------
//        // 1) oplm_date (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        String oplDate = o.getOplmDate();
//        if (!CommonFunctions.isValidKeyId(oplDate)) {
//            o.setOplmDate(nowIso);
//        } else {
//            String s = oplDate.trim();
//            if (s.contains("T")) {
//                // already ISO
//                o.setOplmDate(s.length() >= 19 ? s.substring(0, 19) : s);
//            } else if (s.contains(":") && s.length() >= 19 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                // already PG timestamp "yyyy-MM-dd HH:mm:ss"
//                o.setOplmDate(s.substring(0, 19).replace(" ", "T"));
//
//            } else if (s.contains(":")) {
//                // dd-MMM-yyyy HH:mm:ss
//                o.setOplmDate(CommonFunctions.pg_getDateTimeFromTimeStamp(s));
//
//            } else {
//                // dd-MMM-yyyy
//                o.setOplmDate(CommonFunctions.pg_getDateTimeFromDate(s));
//            }
//        }
//
//        // ----------------------------
//        // 2) oplm_prepareddate (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        String prepDate = o.getOplmPrepareddate();
//        if (!CommonFunctions.isValidKeyId(prepDate)) {
//            o.setOplmPrepareddate(nowIso);
//        } else {
//            String s = prepDate.trim();
//
//            if (s.contains("T")) {
//                o.setOplmPrepareddate(s.length() >= 19 ? s.substring(0, 19) : s);
//
//            } else if (s.contains(":") && s.length() >= 19 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                o.setOplmPrepareddate(s.substring(0, 19).replace(" ", "T"));
//
//            } else if (s.contains(":")) {
//                o.setOplmPrepareddate(CommonFunctions.pg_getDateTimeFromTimeStamp(s));
//
//            } else {
//                o.setOplmPrepareddate(CommonFunctions.pg_getDateTimeFromDate(s));
//            }
//        }
//
//        // ----------------------------
//        // 3) createdon / modifiedon (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        if (isCreate) {
//            o.setOplmCreatedon(nowIso);
//            o.setOplmModifiedon(nowIso);
//
//            o.setOplmApprovedid(null);
//            o.setOplmApproveddate(null);
//
//        } else {
//            String createdOn = o.getOplmCreatedon();
//            if (!CommonFunctions.isValidKeyId(createdOn)) {
//                o.setOplmCreatedon(nowIso);
//            } else {
//                String s = createdOn.trim();
//                if (s.contains("T")) {
//                    o.setOplmCreatedon(s.length() >= 19 ? s.substring(0, 19) : s);
//                } else if (s.contains(" ") && s.length() >= 19) {
//                    o.setOplmCreatedon(s.substring(0, 19).replace(" ", "T"));
//                }
//            }
//
//            // always touch modifiedon
//            o.setOplmModifiedon(nowIso);
//
//            // approveddate is DATE (yyyy-MM-dd)
//            String apprDate = o.getOplmApproveddate();
//            if (CommonFunctions.isValidKeyId(apprDate)) {
//                String s = apprDate.trim();
//                if (s.length() >= 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                    o.setOplmApproveddate(s.substring(0, 10));           // already yyyy-MM-dd
//                } else if (s.contains(":")) {
//                    o.setOplmApproveddate(CommonFunctions.pg_getDateFromTimeStamp(s)); // dd-MMM-yyyy HH:mm:ss -> yyyy-MM-dd
//                } else {
//                    o.setOplmApproveddate(CommonFunctions.pg_getDate(s)); // dd-MMM-yyyy -> yyyy-MM-dd
//                }
//            }
//        }
//
//        return o.toJsonManual();
//    }


//    private String oplJsonManual(OplTlMst o) {
//
//        // CommonFunctions gives: "yyyy-MM-dd HH:mm:ss"
//        // Spring LocalDateTime expects: "yyyy-MM-ddTHH:mm:ss"
//        String nowIso = CommonFunctions.pg_dateTimeNow().replace(" ", "T");
//
//        boolean isCreate = !CommonFunctions.isValidKeyId(o.getOplmKeyid());
//
//        // ----------------------------
//        // 1) oplm_date (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        String oplDate = o.getOplmDate();
//
//        if (!CommonFunctions.isValidKeyId(oplDate)) {
//            o.setOplmDate(nowIso);
//        } else {
//            String s = oplDate.trim();
//
//            // already ISO?
//            if (s.contains("T") && s.length() >= 19) {
//                o.setOplmDate(s.substring(0, 19));
//            }
//            // already PG timestamp "yyyy-MM-dd HH:mm:ss"
//            else if (s.length() >= 19 && s.charAt(4) == '-' && s.charAt(7) == '-' && s.charAt(10) == ' ') {
//                o.setOplmDate(s.substring(0, 19).replace(" ", "T"));
//            }
//            // only PG date "yyyy-MM-dd"
//            else if (s.length() == 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                o.setOplmDate(s + "T00:00:00");
//            }
//            // dd-MMM-yyyy HH:mm:ss (rare, but safe)
//            else if (s.contains(":")) {
//                String pgDate = CommonFunctions.pg_getDateFromTimeStamp(s); // yyyy-MM-dd
//                o.setOplmDate(pgDate + "T00:00:00");
//            }
//            // dd-MMM-yyyy
//            else {
//                String pgDate = CommonFunctions.pg_getDate(s); // yyyy-MM-dd
//                o.setOplmDate(pgDate + "T00:00:00");
//            }
//        }
//
//        // ----------------------------
//        // 2) oplm_prepareddate (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        String prepDate = o.getOplmPrepareddate();
//
//        if (!CommonFunctions.isValidKeyId(prepDate)) {
//            o.setOplmPrepareddate(nowIso);
//        } else {
//            String s = prepDate.trim();
//
//            if (s.contains("T") && s.length() >= 19) {
//                o.setOplmPrepareddate(s.substring(0, 19));
//            }
//            else if (s.length() >= 19 && s.charAt(4) == '-' && s.charAt(7) == '-' && s.charAt(10) == ' ') {
//                o.setOplmPrepareddate(s.substring(0, 19).replace(" ", "T"));
//            }
//            else if (s.length() == 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                o.setOplmPrepareddate(s + "T00:00:00");
//            }
//            else if (s.contains(":")) {
//                String pgDate = CommonFunctions.pg_getDateFromTimeStamp(s); // yyyy-MM-dd
//                o.setOplmPrepareddate(pgDate + "T00:00:00");
//            }
//            else {
//                String pgDate = CommonFunctions.pg_getDate(s); // yyyy-MM-dd
//                o.setOplmPrepareddate(pgDate + "T00:00:00");
//            }
//        }
//
//        // ----------------------------
//        // 3) createdon / modifiedon (TIMESTAMP -> LocalDateTime)
//        // ----------------------------
//        if (isCreate) {
//            o.setOplmCreatedon(nowIso);
//            o.setOplmModifiedon(nowIso);
//
//            o.setOplmApprovedid(null);
//            o.setOplmApproveddate(null);
//        } else {
//            // preserve createdon if present; normalize if it is "yyyy-MM-dd HH:mm:ss"
//            String createdOn = o.getOplmCreatedon();
//            if (!CommonFunctions.isValidKeyId(createdOn)) {
//                o.setOplmCreatedon(nowIso);
//            } else {
//                String s = createdOn.trim();
//                if (s.length() >= 19 && s.charAt(10) == ' ') {
//                    o.setOplmCreatedon(s.substring(0, 19).replace(" ", "T"));
//                } else if (s.length() == 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                    o.setOplmCreatedon(s + "T00:00:00");
//                }
//            }
//
//            o.setOplmModifiedon(nowIso);
//
//            // approveddate is DATE in DB -> send yyyy-MM-dd (no time)
//            String apprDate = o.getOplmApproveddate();
//            if (CommonFunctions.isValidKeyId(apprDate)) {
//                String s = apprDate.trim();
//                if (s.length() >= 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
//                    o.setOplmApproveddate(s.substring(0, 10)); // yyyy-MM-dd
//                } else if (s.contains(":")) {
//                    o.setOplmApproveddate(CommonFunctions.pg_getDateFromTimeStamp(s)); // yyyy-MM-dd
//                } else {
//                    o.setOplmApproveddate(CommonFunctions.pg_getDate(s)); // yyyy-MM-dd
//                }
//            }
//        }
//
//        return o.toJsonManual();
//    }


private String jsonVal(Object v) {
        // Always return a JSON string value:  "..."
        if (v == null) {
            return "\"\"";
        }
        String s = String.valueOf(v);

        // If your system uses "{}" as "empty", keep it as-is
        // but still quote it, because your API expects strings.
        s = escapeJson(s);
        return "\"" + s + "\"";
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        // escape backslash and quotes and new lines
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }

private OplTlMst oplFromJson(String json) {
        // ✅ delegate parsing to model (Abnormality style)
        return OplTlMst.fromJson(json);
    }

public OplTlMst getByKeyid(String keyid) throws Exception {

        if (keyid == null || keyid.trim().isEmpty()) {
            throw new IllegalArgumentException("keyid is null/empty");
        }

        String apiUrl = "/opl/" + URLEncoder.encode(keyid.trim(), StandardCharsets.UTF_8);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("OPL GET statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("OPL GET responseBody :: " + res.getBody());

        if (res.getStatusCode() < 200 || res.getStatusCode() >= 300) {
            throw new IllegalStateException("OPL GET failed. HTTP=" + res.getStatusCode()
                    + " body=[" + res.getBody() + "]");
        }

        return oplFromJson(res.getBody());
    }

//-------------------------
//UPDATE / UPSERT (3 PARAMS)
//-------------------------


// -------------------------
// UPDATE (POST) - 1 PARAM (FALLBACK)
// -------------------------
public OplTlMst updateRecord(OplTlMst oplTlMst) throws Exception {
    if (oplTlMst == null) {
        throw new IllegalArgumentException("oplTlMst is null");
    }
   
    if (null == (oplTlMst.getOplmKeyid())) {
        throw new IllegalArgumentException("OPL update requires keyid");
    }

    // Change this endpoint if your Spring Boot uses PUT /opl/{keyid}
    String apiUrl = "/opl/update";

    if (oplTlMst.getOplmActive() == null) {
        oplTlMst.setOplmActive("Y");
    }

    String jsonPayload = oplJsonManual(oplTlMst);
    CommonMessage.debugMsg("OPL UPDATE JSON :: " + jsonPayload);

    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

    CommonMessage.debugMsg("OPL UPDATE statusCode :: " + res.getStatusCode());
    CommonMessage.debugMsg("OPL UPDATE responseBody :: " + res.getBody());

    int code = res.getStatusCode();
    if (code < 200 || code >= 300) {
        String body = res.getBody();
        if (body == null) body = "";
        throw new IllegalStateException("OPL UPDATE failed. HTTP=" + code + " body=[" + body + "]");
    }

    return oplFromJson(res.getBody());
}
// -------------------------
// WRAPPER JSON (opl + yylink + docupdates)
// IMPORTANT: DO NOT JSONObject.parse oplJsonManual()
// -------------------------



// -------------------------
// WRAPPER JSON FOR UPDATE (opl + yylink + docupdates)
// -------------------------
private String oplSaveJsonManualForUpdate(OplTlMst oplTlMst,
        BdmTlYycountermeasurelink link,
        GenTlDocupdates doc) {

    String oplJson = oplJsonManual(oplTlMst);
    if (oplJson == null || oplJson.trim().isEmpty()) {
        throw new IllegalStateException("oplJsonManualForUpdate() returned empty/null JSON");
    }

    oplJson = oplJson.trim();

    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("\"oplTlMst\":").append(oplJson);
    sb.append(",\"bdmTlYycountermeasurelink\":")
      .append(link == null ? "null" : net.sf.json.JSONObject.fromObject(link).toString());
    sb.append(",\"genTlDocupdates\":")
      .append(doc == null ? "null" : net.sf.json.JSONObject.fromObject(doc).toString());
    sb.append("}");
    return sb.toString();
}
public List<String[]> getStudents(String oplId, String cellId, String oplKeyid) throws Exception {

    String apiUrl = "/opl/recallStudents";

    String jsonPayload = compJsonStudents(oplId, cellId, oplKeyid);

    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

    CommonMessage.debugMsg("OPL STUDENTS statusCode :: " + res.getStatusCode());
    CommonMessage.debugMsg("OPL STUDENTS responseBody :: " + res.getBody());

    int code = res.getStatusCode();
    if (code < 200 || code >= 300) {
        String body = res.getBody();
        if (body == null) body = "";
        throw new IllegalStateException("OPL getStudents failed. HTTP=" + code + " body=[" + body + "]");
    }

    JSONArray jsonArray = new JSONArray(res.getBody());

    // EXACT SAME conversion as MOM
    return convertJsonArrayToListwithcolumnorder(jsonArray);
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

private String compJsonStudents(String oplId, String cellId, String oplKeyid) {
    StringBuilder sb = new StringBuilder();
    sb.append("{");

    sb.append("\"oplId\":\"").append(oplId == null ? "" : oplId).append("\"");
    sb.append(",\"cellId\":\"").append(cellId == null ? "" : cellId).append("\"");

    // send null properly
    sb.append(",\"oplKeyid\":");
    if (oplKeyid == null || oplKeyid.trim().isEmpty()) {
        sb.append("null");
    } else {
        sb.append("\"").append(oplKeyid).append("\"");
    }

    sb.append("}");
    CommonMessage.debugMsg("OPL STUDENTS JSON :: " + sb.toString());
    return sb.toString();
}



}
