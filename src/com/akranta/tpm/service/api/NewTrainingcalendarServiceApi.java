package com.akranta.tpm.service.api;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalEmp;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Calls Springboot Training Calendar REST APIs
 * Mirrors FishBoneServiceApi structure
 */
public class NewTrainingcalendarServiceApi {

    private final Api api;

    // ✅ Your Spring controller mapping:
    // @RequestMapping("/api/training-calendar")
    // But your Api wrapper probably already includes "/api"
    private static final String API_BASE = "/training-calendar";

    public NewTrainingcalendarServiceApi(String jwtToken) {

        // ✅ Same debug style you use
        CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
        CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
        CommonMessage.debugMsg("JWT(clean length)=" + (cleanJwt(jwtToken) == null ? 0 : cleanJwt(jwtToken).length()));
        CommonMessage.debugMsg("JWT contains \\n ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\n")));
        CommonMessage.debugMsg("JWT contains \\r ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\r")));

        this.api = new Api(cleanJwt(jwtToken));
    }
    // =====================================================
    // ✅ EMPLOYEE ATTENDANCE SAVE
    // =====================================================
    /**
     * Calls SpringBoot API:
     * POST /api/ntrc/training-calendar/{etcmKeyid}/attendance
     * Body: EmployeeAttendanceRequest
     */
    public List<EntTlTtgCalEmpatScore> createEmployeeAttendance(List<EntTlTtgCalEmpatScore> scores,
                                                               CommonFilter commonfilter) throws Exception {
        if (scores == null || scores.isEmpty()) {
            return new ArrayList<>();
        }

        EntTlTtgCalEmpatScore first = scores.get(0);

        // Prefer filter key (legacy) -> fallback to first score
        String etcmKeyid = (commonfilter != null) ? safeStr(commonfilter.getKey()) : "";
        if (!hasText(etcmKeyid)) {
            etcmKeyid = safeStr(first.getEtcaEtcmKeyid());
        }
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("etcmKeyid is required for employee attendance save");
        }

        // Build request payload (matches SpringBoot EmployeeAttendanceRequest)
        JSONObject payload = new JSONObject();

        // ✅ Grid-level params
        String cutoff = safeStr(first.getEtcaCutOff());
        String max = safeStr(first.getEtcaMaxMarks());
        String typ = safeStr(first.getEtcaType());
        String assess = safeStr(first.getEtcaAssessmentCom());
        if (!hasText(assess)) assess = "N";

        payload.put("cutoff", cutoff);
        payload.put("max", max);
        payload.put("typ", typ);
        payload.put("assess", assess);

        if (commonfilter != null) {
            payload.put("topicid", safeStr(commonfilter.getTopicid()));
            payload.put("locnid", safeStr(commonfilter.getLossId()));
            payload.put("flid", safeStr(commonfilter.getFlid()));
        }

        // ✅ Scores list
        List<Object> scoreDtoList = new ArrayList<>();
        for (EntTlTtgCalEmpatScore s : scores) {
            if (s == null) continue;
            JSONObject o = new JSONObject();

            // keyid optional (for update)
            if (hasText(safeStr(s.getEtcaKeyid()))) {
                o.put("etcaKeyid", s.getEtcaKeyid().trim());
            }

            // required identifiers
            o.put("etcaEtcmKeyid", hasText(safeStr(s.getEtcaEtcmKeyid())) ? s.getEtcaEtcmKeyid().trim() : etcmKeyid);
            o.put("etcaEtceEmpmKeyid", safeStr(s.getEtcaEtceEmpmKeyid()));
            o.put("etcaEtceKeyid", safeStr(s.getEtcaEtceKeyid()));

            // attendance values (keep 0 / P / A etc)
            o.put("etcaPrsentAbsent", hasText(safeStr(s.getEtcaPresentAbsent())) ? s.getEtcaPresentAbsent().trim() : "P");
            o.put("etcaScore", hasText(safeStr(s.getEtcaScore())) ? s.getEtcaScore().trim() : "0");
            o.put("etcaResult", hasText(safeStr(s.getEtcaResult())) ? s.getEtcaResult().trim() : "P");

            // optional strings
            if (hasText(safeStr(s.getEtcaDept())))      o.put("etcaDept", s.getEtcaDept().trim());
            if (hasText(safeStr(s.getEtcaRemarks())))   o.put("etcaRemarks", s.getEtcaRemarks().trim());

            // created by
            String createdBy = safeStr(s.getEtcaCreatedby());
            if (!hasText(createdBy) && commonfilter != null) {
                createdBy = safeStr(commonfilter.getChkExternal());
            }
            if (hasText(createdBy)) {
                o.put("etcaCreatedby", createdBy);
            }

            // date (if UI sends dd-Mon-yyyy / dd-Mon-yyyy hh:mm:ss, normalize for Jackson)
            // legacy bean might expose either getEtcaAttDate() OR getEtcaAddDate(), so we try both.
            String attDate = firstNonEmpty(getAsString(s, "getEtcaAddDate"));
            if (hasText(attDate)) {
                o.put("etcaAttDate", CommonFunctions.pg_getDateTimeFromDate(attDate));
            }

            scoreDtoList.add(o);
        }

        payload.put("scores", JSONArray.fromObject(scoreDtoList));

        String url = API_BASE + "/" + enc(etcmKeyid) + "/attendance";
        CommonMessage.debugMsg("ATTENDANCE API POST url :: " + url);
        CommonMessage.debugMsg("ATTENDANCE API POST payload :: " + payload);

        HttpResponse res = api.makeAuthRequest(url, "POST", payload.toString());

        CommonMessage.debugMsg("ATTENDANCE API POST status :: " + res.getStatusCode());
        CommonMessage.debugMsg("ATTENDANCE API POST body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";
        if (code < 200 || code >= 300) {
            throw new IllegalStateException("ATTENDANCE SAVE failed HTTP=" + code + " body=[" + body + "]");
        }

        return scores;
    }
 // =====================================================
 // ✅ FETCH TRAINING CALENDAR UNIQUE POSITION GRID (By ID) - API Layer
 // =====================================================
 public List<String[]> getNewUniqPosData(String keyid) throws Exception {

     String etcmKeyid = safeStr(keyid);
     if (!hasText(etcmKeyid)) {
         throw new IllegalArgumentException("Training id (keyid) is required");
     }

     // ✅ URL: /training-calendar/{etcmKeyid}/unique
     String url = API_BASE + "/" + enc(etcmKeyid) + "/unique";
     CommonMessage.debugMsg("TRAINING-CAL UNIQUE POSITION FETCH url :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "GET", null);

     CommonMessage.debugMsg("TRAINING-CAL UNIQUE POSITION FETCH status :: " + res.getStatusCode());
     CommonMessage.debugMsg("TRAINING-CAL UNIQUE POSITION FETCH body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("UNIQUE POSITION FETCH failed HTTP=" + code + " body=[" + body + "]");
     }

     if (!hasText(body)) {
         return new ArrayList<>();
     }

     JSONObject resp = JSONObject.fromObject(body);

     List<String[]> out = new ArrayList<>();

     // ✅ Response: { "unique": [ [..6 cols..], ... ], "keyid": "ETC..." }
     if (!resp.has("unique") || resp.get("unique") == null) {
         return out;
     }

     JSONArray uniqArr = resp.getJSONArray("unique");

     for (int i = 0; i < uniqArr.length(); i++) {

         // each row = ["ETCU_KEYID","ETCU_ROLE_KEYID","Unique Position","DMT","JH","Delete"]
         JSONArray row = uniqArr.getJSONArray(i);

         String[] arr = new String[6];

         for (int j = 0; j < 6; j++) {
             String val = "";
             if (row != null && row.length() > j && row.get(j) != null) {
                 val = safeStr(String.valueOf(row.get(j)));
             }
             arr[j] = val;
         }

         out.add(arr);
     }

     return out;
 }
 public List<String[]> getAllEmployee(CommonFilter commonFilter, GridParams gridParams) throws Exception {

	    if (commonFilter == null) {
	        throw new IllegalArgumentException("commonFilter is null");
	    }

	    String etcmKeyid = safeStr(commonFilter.getKey()); // TraKeyid / MasterId
	    if (!hasText(etcmKeyid)) {
	        throw new IllegalArgumentException("Training id (commonFilter.key / etcmKeyid) is required");
	    }

	    // ✅ Spring endpoint:
	    // POST /training-calendar/{etcmKeyid}/attendance
	    String url = API_BASE + "/" + enc(etcmKeyid) + "/attendance/fetch";
	    CommonMessage.debugMsg("TRAINING-CAL ATTENDANCE FETCH url :: " + url);

	    // ✅ Build payload (gridParams)
	    JSONObject req = new JSONObject();
	    JSONObject gp  = new JSONObject();

	    if (gridParams != null) {
	        gp.put("fromRow", safeStr(gridParams.getFromRow()));
	        gp.put("toRow",   safeStr(gridParams.getToRow()));

	        // ✅ gridFilters -> without JSONArray.add() / element()
	        java.util.List<JSONObject> gfList = new java.util.ArrayList<>();
	        try {
	            List<GridFilter> gfs = gridParams.getGridFilters();
	            if (gfs != null) {
	                for (GridFilter f : gfs) {
	                    if (f == null) continue;
	                    String field = safeStr(f.getField());
	                    String data  = safeStr(f.getData());
	                    if (!hasText(field) || !hasText(data)) continue;

	                    JSONObject obj = new JSONObject();
	                    obj.put("field", field);
	                    obj.put("data",  data);
	                    gfList.add(obj);
	                }
	            }
	        } catch (Exception e) {
	            // ignore
	        }

	        gp.put("gridFilters", JSONArray.fromObject(gfList));
	    }

	    req.put("gridParams", gp);

	    String jsonPayload = req.toString();
	    CommonMessage.debugMsg("TRAINING-CAL ATTENDANCE FETCH payload :: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

	    CommonMessage.debugMsg("TRAINING-CAL ATTENDANCE FETCH status :: " + res.getStatusCode());
	    CommonMessage.debugMsg("TRAINING-CAL ATTENDANCE FETCH body   :: " + res.getBody());

	    int code = res.getStatusCode();
	    String body = res.getBody();
	    if (body == null) body = "";

	    if (code < 200 || code >= 300) {
	        throw new IllegalStateException("ATTENDANCE FETCH failed HTTP=" + code + " body=[" + body + "]");
	    }

	    List<String[]> out = new ArrayList<>();

	    // ✅ header row (legacy UI expects first row as column names)
	    out.add(new String[]{
	            "slno",
	            "hdnEtcaEtcmKeyid",
	            "hdnEtcaKeyid",
	            "hdnEtcaEtceKeyid",
	            "hdnEtcaEtceEmpmKeyid",
	            "hdnTopicId",
	            "empname",
	            "EMPM_EMPLOYEETYPE",
	            "EMPM_GENDER",
	            "cmbEtcaPresentAbsent",
	            "dteEtcaAddDate",
	            "txtEtcaScore",
	            "txtEtcaResult",
	            "txtEtcaRemarks",
	            "btnFilManage"
	    });

	    if (!hasText(body)) {
	        return out;
	    }

	    JSONObject resp = JSONObject.fromObject(body);

	    // ✅ update totalRecordCnt back to gridParams (like old flow)
	    try {
	        if (resp.has("totalRecordCnt") && resp.get("totalRecordCnt") != null && gridParams != null) {
	            String cntStr = safeStr(String.valueOf(resp.get("totalRecordCnt")));
	            if (hasText(cntStr)) {
	                gridParams.setTotalRecordCnt(Long.parseLong(cntStr));
	            }
	        }
	    } catch (Exception e) {
	        // ignore
	    }

	    if (!resp.has("attendance") || resp.get("attendance") == null) {
	        return out;
	    }

	    JSONArray dataArr = JSONArray.fromObject(resp.get("attendance"));

	    for (int i = 0; i < dataArr.length(); i++) {
	        Object rowObj = dataArr.get(i);
	        if (rowObj == null) continue;

	        JSONArray row = JSONArray.fromObject(rowObj);

	        // ✅ avoid duplicate header if backend sends header row accidentally
	        if (i == 0 && row.length() > 0) {
	            String first = safeStr(String.valueOf(row.get(0)));
	            if ("slno".equalsIgnoreCase(first)) {
	                continue;
	            }
	        }

	        String[] arr = new String[15];
	        for (int j = 0; j < 15; j++) {
	            String val = "";
	            if (row.length() > j && row.get(j) != null) {
	                val = safeStr(String.valueOf(row.get(j)));
	            }
	            arr[j] = val;
	        }
	        out.add(arr);
	    }

	    return out;
	}

 
 public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception {

	    if (commonFilter == null) {
	        throw new IllegalArgumentException("commonFilter is null");
	    }

	    // ===== values from old servlet mapping =====
	    String etcmKeyid       = safeStr(commonFilter.getKey());        // TrainingId
	    String flid            = safeStr(commonFilter.getFlid());       // flid
	    String fnln            = safeStr(commonFilter.getFactoryId());  // fnln / factoryId
	    String roleKeyId       = safeStr(commonFilter.getRoleLevel());  // roleKeyId
	    String pillarId        = safeStr(commonFilter.getRefdocid());   // pillarId (refdocid)
	    String uniq            = safeStr(commonFilter.getUniquePos());  // uniq (true/false)
	    String employeeType    = safeStr(commonFilter.getEmpwiseType());// EmployeeType
	    String employeeGender  = safeStr(commonFilter.getEmpch());      // EmployeeGender

	    // ===== grid params (fromRow/toRow + gridFilters) =====
	    JSONObject gp = new JSONObject();
	    gp.put("fromRow", safeStr(commonFilter.getFromRow()));
	    gp.put("toRow",   safeStr(commonFilter.getToRow()));

	    // build gridFilters WITHOUT JSONArray.add() / element()
	    java.util.List<JSONObject> gfList = new java.util.ArrayList<>();
	    try {
	        List<GridFilter> gfs = commonFilter.getGridFilter();
	        if (gfs != null) {
	            for (GridFilter f : gfs) {
	                if (f == null) continue;
	                String field = safeStr(f.getField());
	                String data  = safeStr(f.getData());
	                if (!hasText(field) || !hasText(data)) continue;

	                JSONObject o = new JSONObject();
	                o.put("field", field);
	                o.put("data", data);
	                gfList.add(o);
	            }
	        }
	    } catch (Exception ignore) {}

	    gp.put("gridFilters", JSONArray.fromObject(gfList));

	    // ===== final payload =====
	    JSONObject req = new JSONObject();
	    // send multiple key names (Spring service uses firstText() anyway)
	    req.put("key",        etcmKeyid);
	    req.put("TrainingId", etcmKeyid);
	    req.put("etcmKeyid",  etcmKeyid);

	    req.put("flid", flid);
	    req.put("fnln", fnln);
	    req.put("factoryId", fnln);

	    req.put("roleKeyId", roleKeyId);
	    req.put("roleLevel", roleKeyId);

	    req.put("pillarId", pillarId);
	    req.put("refdocid", pillarId);

	    req.put("uniq", uniq);
	    req.put("uniquePos", uniq);

	    req.put("EmployeeType", employeeType);
	    req.put("empwiseType",  employeeType);

	    req.put("EmployeeGender", employeeGender);
	    req.put("empch",          employeeGender);

	    req.put("gridParams", gp);

	    // ===== call Spring endpoint =====
	    String apiUrl = API_BASE + "/unique/employees/popup";
	    String jsonPayload = req.toString();

	    CommonMessage.debugMsg("TRAINING-CAL UNIQUE POPUP URL  :: " + apiUrl);
	    CommonMessage.debugMsg("TRAINING-CAL UNIQUE POPUP JSON :: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg("TRAINING-CAL UNIQUE POPUP status :: " + res.getStatusCode());
	    CommonMessage.debugMsg("TRAINING-CAL UNIQUE POPUP body   :: " + res.getBody());

	    int code = res.getStatusCode();
	    String body = res.getBody();
	    if (body == null) body = "";

	    if (code < 200 || code >= 300) {
	        throw new IllegalStateException("UNIQUE POPUP failed HTTP=" + code + " body=[" + body + "]");
	    }

	    JSONObject obj = JSONObject.fromObject(body);

	    // keep legacy behavior: update total count in commonFilter
	    if (obj.has("totalRecordCnt")) {
	        int total = safeIntOrDefault(obj.opt("totalRecordCnt"), 0);
	        commonFilter.setTotalRecordCnt(total);
	    }

	    // Spring returns: { uniqueemployeepopup : [ [..row..], [..row..] ] }
	    JSONArray arr = new JSONArray();
	    if (obj.has("uniqueemployeepopup")) {
	        Object v = obj.get("uniqueemployeepopup");
	        if (v != null) {
	            arr = JSONArray.fromObject(v);
	        }
	    }

	    List<String[]> result = new java.util.ArrayList<>();

	    // ✅ add legacy header row for UIUtils.convertToJqGridTableObject
	    result.add(new String[]{
	            "slno",
	            "selctval",
	            "empm_keyid",
	            "empm_code",
	            "empm_name",
	            "empm_employeetype_desc",
	            "empm_gender_desc",
	            "ses",
	            "role_name",
	            "etcq_currentlevel",
	            "etcq_currentleveldate",
	            "sectionid",
	            "sect_name",
	            "cellid",
	            "cell_name",
	            "roleid",
	            "etcm_keyid_display",
	            "etcekeyid"
	    });

	    for (int i = 0; i < arr.length(); i++) {
	        Object rowObj = arr.get(i);
	        if (rowObj == null) continue;

	        JSONArray rowArr = (rowObj instanceof JSONArray)
	                ? (JSONArray) rowObj
	                : JSONArray.fromObject(rowObj);

	        String[] row = new String[rowArr.length()];
	        for (int j = 0; j < row.length; j++) {
	            Object vv = rowArr.get(j);
	            if (vv == null) {
	                row[j] = "";
	                continue;
	            }
	            String s = String.valueOf(vv).trim();
	            if (s.length() == 0 || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) {
	                row[j] = "";
	            } else {
	                row[j] = s;
	            }
	        }
	        result.add(row);
	    }

	    return result;
	}


//=====================================================
//✅ FETCH TRAINING CALENDAR - UNIQUE EMPLOYEES GRID (By TrainingId)
//Endpoint: POST /training-calendar/{etcmKeyid}/unique/employees
//Request : { "gridParams": { "fromRow":1, "toRow":100, "gridFilters":[{field,data},..] } }
//Response: { "uniqueemployees":[[..15 cols..],..], "keyid":"ETC..", "totalRecordCnt":.. }
//=====================================================
public List<String[]> getAllUniqueEmployee(CommonFilter commonFilter, GridParams gridParams) throws Exception {

  if (commonFilter == null) {
      throw new IllegalArgumentException("commonFilter is null");
  }

  String etcmKeyid = safeStr(commonFilter.getKey()); // TrgId / MasterId
  if (!hasText(etcmKeyid)) {
      throw new IllegalArgumentException("Training id (commonFilter.key / etcmKeyid) is required");
  }

  // ✅ URL: /training-calendar/{etcmKeyid}/unique/employees
  String url = API_BASE + "/" + enc(etcmKeyid) + "/unique/employees";
  CommonMessage.debugMsg("TRAINING-CAL UNIQUE EMP FETCH url :: " + url);

  // ✅ Build payload (we'll send gridParams as JSON)
  JSONObject req = new JSONObject();
  JSONObject gp  = new JSONObject();

  if (gridParams != null) {

      // paging
      gp.put("fromRow", safeStr(gridParams.getFromRow()));
      gp.put("toRow",   safeStr(gridParams.getToRow()));

      // gridFilters -> [{field:"name", data:"ABC*"}, ...]
      JSONArray gfArr = new JSONArray();
       // ---- Vignesh Replacement for .add and .element 
      java.util.List<JSONObject> gfList = new java.util.ArrayList<>();
      try {
    	    List<GridFilter> gfs = gridParams.getGridFilters();
    	    if (gfs != null) {
    	        for (GridFilter f : gfs) {
    	            if (f == null) continue;

    	            String field = safeStr(f.getField());
    	            String data  = safeStr(f.getData());

    	            if (!hasText(field) || !hasText(data)) continue;

    	            JSONObject obj = new JSONObject();
    	            obj.put("field", field);
    	            obj.put("data", data);

    	            // ✅ No JSONArray.add / element → add to normal Java list
    	            gfList.add(obj);
    	        }
    	    }
    	} catch (Exception e) {
          // ignore if gridFilters are not available / not needed
      }
      gp.put("gridFilters", JSONArray.fromObject(gfList)); 
  //    gp.put("gridFilters", gfArr);
  }

  req.put("gridParams", gp);

  String jsonPayload = req.toString();
  CommonMessage.debugMsg("TRAINING-CAL UNIQUE EMP FETCH payload :: " + jsonPayload);

  HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

  CommonMessage.debugMsg("TRAINING-CAL UNIQUE EMP FETCH status :: " + res.getStatusCode());
  CommonMessage.debugMsg("TRAINING-CAL UNIQUE EMP FETCH body   :: " + res.getBody());

  int code = res.getStatusCode();
  String body = res.getBody();
  if (body == null) body = "";

  if (code < 200 || code >= 300) {
      throw new IllegalStateException("UNIQUE EMP FETCH failed HTTP=" + code + " body=[" + body + "]");
  }

  List<String[]> out = new ArrayList<>();

  // ✅ Always keep header row as first row (legacy expected)
  String[] header = new String[]{
          "slno", "etcekeyid", "attnkeyid", "keyid", "empcode", "name",
          "emptype", "gender", "ses", "rolename", "currlevel", "lastupdate",
          "dmt", "jh", "roleid"
  };
  out.add(header);

  if (!hasText(body)) {
      return out;
  }

  JSONObject resp = JSONObject.fromObject(body);

  // optional: totalRecordCnt update back to gridParams
  try {
      if (resp.has("totalRecordCnt") && resp.get("totalRecordCnt") != null && gridParams != null) {
          String cntStr = safeStr(String.valueOf(resp.get("totalRecordCnt")));
          if (hasText(cntStr)) {
              gridParams.setTotalRecordCnt(Long.parseLong(cntStr));
          }
      }
  } catch (Exception e) {
      // ignore
  }

  if (!resp.has("uniqueemployees") || resp.get("uniqueemployees") == null) {
      return out;
  }

  JSONArray dataArr = resp.getJSONArray("uniqueemployees");

  for (int i = 0; i < dataArr.length(); i++) {

      JSONArray row = dataArr.getJSONArray(i);

      // ✅ if backend already sent header row, skip it (avoid duplicate header)
      if (i == 0 && row != null && row.length() > 0) {
          String first = safeStr(String.valueOf(row.get(0)));
          if ("slno".equalsIgnoreCase(first)) {
              continue;
          }
      }

      String[] arr = new String[15];
      for (int j = 0; j < 15; j++) {
          String val = "";
          if (row != null && row.length() > j && row.get(j) != null) {
              val = safeStr(String.valueOf(row.get(j)));
          }
          arr[j] = val;
      }

      out.add(arr);
  }

  return out;
}

 // =====================================================
 // ✅ FETCH TRAINING CALENDAR session GRID (By ID) - API Layer
 // =====================================================
 public List<String[]> getsession(String progKeyid) throws Exception {

     String etcmKeyid = safeStr(progKeyid);
     if (!hasText(etcmKeyid)) {
         throw new IllegalArgumentException("Training id (progKeyid) is required");
     }

     // ✅ URL: /training-calendar/{etcmKeyid}/session
     String url = API_BASE + "/" + enc(etcmKeyid) + "/session";
     CommonMessage.debugMsg("TRAINING-CAL SESSION FETCH url :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "GET", null);

     CommonMessage.debugMsg("TRAINING-CAL SESSION FETCH status :: " + res.getStatusCode());
     CommonMessage.debugMsg("TRAINING-CAL SESSION FETCH body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("SESSION FETCH failed HTTP=" + code + " body=[" + body + "]");
     }

     if (!hasText(body)) {
         return new ArrayList<>();
     }

     JSONObject resp = JSONObject.fromObject(body);

     List<String[]> out = new ArrayList<>();

     // ✅ Response: { "session": [ [..6 cols..], ... ], "keyid": "ETC..." }
     if (!resp.has("session") || resp.get("session") == null) {
         return out;
     }

     JSONArray sessionArr = resp.getJSONArray("session");

     for (int i = 0; i < sessionArr.length(); i++) {

         // each row = ["ETCS_KEYID","ETCS_NAME","DD-Mon-YYYY","HH24:MI","HH24:MI",""]
         JSONArray row = sessionArr.getJSONArray(i);

         String[] arr = new String[6];

         for (int j = 0; j < 6; j++) {
             String val = "";
             if (row != null && row.length() > j && row.get(j) != null) {
                 val = safeStr(String.valueOf(row.get(j)));
             }
             arr[j] = val;
         }

         out.add(arr);
     }

     return out;
 }

    // =====================================================
    // ✅ FETCH TRAINING CALENDAR faculty (By ID) - API Layer
    // =====================================================
    public List<String[]> getFaculty(String progKeyid) throws Exception {

        String etcmKeyid = safeStr(progKeyid);
        
        String url;
        if (!hasText(etcmKeyid)) {
         //   url = API_BASE + "/faculty";    
            url =  API_BASE + "/'" +  "'/faculty";    // ✅ /training-calendar/faculty
        } else {
            url = API_BASE + "/" + enc(etcmKeyid) + "/faculty";  // ✅ /training-calendar/{id}/faculty
        }
        
        CommonMessage.debugMsg("TRAINING-CAL FACULTY FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL FACULTY FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL FACULTY FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FACULTY FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return new ArrayList<>();
        }

        JSONObject resp = JSONObject.fromObject(body);

        List<String[]> out = new ArrayList<>();

        if (!resp.has("faculty") || resp.get("faculty") == null) {
            return out;
        }

        JSONArray facultyArr = resp.getJSONArray("faculty");

        for (int i = 0; i < facultyArr.length(); i++) {

            // each row = ["", "FTYM_EMPM_KEYID", "ETCF_KEYID", "FacultyName", ""]
            JSONArray row = facultyArr.getJSONArray(i);

            String[] arr = new String[5];

            for (int j = 0; j < 5; j++) {
                String val = "";
                if (row != null && row.length() > j && row.get(j) != null) {
                    val = safeStr(String.valueOf(row.get(j)));
                }
                arr[j] = val;
            }

            out.add(arr);
        }

        return out;
    }

 // =====================================================
 // ✅ FETCH TRAINING CALENDAR MASTER (By ID) - API Layer
 // =====================================================
 /**
  * Calls SpringBoot API:
  * GET /api/training-calendar/{etcmKeyid}
  * Response:
  * {
  *   "master": { ...EntTlTragcalmst fields... },
  *   "traCalId": "ETC000000000123"
  * }
  */
    
    public EntTlTragcalmst getselectdata(String calid) throws Exception {

        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /{etcmKeyid}
        String url = API_BASE + "/" + enc(etcmKeyid);

        CommonMessage.debugMsg("TRAINING-CAL MASTER FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL MASTER FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL MASTER FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("MASTER FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Parse JSON -> master object
        JSONObject resp = JSONObject.fromObject(body);

        JSONObject masterJson = null;
        if (resp.has("master") && resp.get("master") != null) {
            masterJson = resp.getJSONObject("master");
        }

        if (masterJson == null || masterJson.isEmpty()) {
            throw new IllegalStateException("Master data not received from API for id: " + etcmKeyid);
        }

        // ==========================================================
        // ✅ FIX: Convert ISO datetime (with 'T') into old UI format
        // Example: 2023-10-14T00:00:00  -> 14-Oct-2023 00:00:00
        // Handles microseconds: 2026-01-22T14:44:06.721388
        // ==========================================================
        String[] dateKeys = {
            "etcmCalendarDate",
            "etcmCreatedOn",
            "etcmCreatedDateTime",
            "etcmModifiedOn",
            "etcmCompletedDate"
        };
        
     // ==========================================================
        // ✅ SANITIZE: convert "-" / "{}" / "null" -> "" for UI fields
        // ==========================================================
        if (masterJson.has("etcmComments") && masterJson.get("etcmComments") != null) {
            String c = masterJson.getString("etcmComments");
            if (!hasText(c) || "-".equals(c) || "{}".equals(c) || "null".equalsIgnoreCase(c.trim())) {
                masterJson.put("etcmComments", "");
            }
        }

        for (String key : dateKeys) {
            if (masterJson.has(key) && masterJson.get(key) != null) {

                String v = masterJson.getString(key);

                if (hasText(v) && v.contains("T")) {

                    // ✅ remove microseconds .721388
                    if (v.contains(".")) {
                        v = v.substring(0, v.indexOf("."));
                    }

                    // ✅ remove timezone if any (safe)
                    if (v.endsWith("Z")) {
                        v = v.substring(0, v.length() - 1);
                    }
                    if (v.contains("+")) {
                        v = v.substring(0, v.indexOf("+"));
                    }

                    // ✅ now convert using your existing function
                    String fixed = CommonFunctions.pg_getDateTimeFromPGTimeStamp(v);

                    masterJson.put(key, fixed);
                }
            }
        }

        // ✅ Convert JSON -> EntTlTragcalmst POJO
        EntTlTragcalmst master =
                (EntTlTragcalmst) JSONObject.toBean(masterJson, EntTlTragcalmst.class);

        return master;
    }

    public String getempdata(String calid) throws Exception {

        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /training-calendar/{etcmKeyid}/empdata
        String url = API_BASE + "/" + enc(etcmKeyid) + "/empdata";

        CommonMessage.debugMsg("TRAINING-CAL EMPDATA FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL EMPDATA FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL EMPDATA FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("EMPDATA FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return "0";
        }

        JSONObject resp = JSONObject.fromObject(body);

        String empcount = "0";
        if (resp.has("empcount") && resp.get("empcount") != null) {
            empcount = safeStr(resp.getString("empcount"));
        }

        return hasText(empcount) ? empcount : "0";
    }
    
    public String getempattn(String calid) throws Exception {

        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /training-calendar/{etcmKeyid}/empattn
        String url = API_BASE + "/" + enc(etcmKeyid) + "/empattn";

        CommonMessage.debugMsg("TRAINING-CAL EMPATTN FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL EMPATTN FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL EMPATTN FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("EMPATTN FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return "0";
        }

        JSONObject resp = JSONObject.fromObject(body);

        String empattn = "0";
        if (resp.has("empattn") && resp.get("empattn") != null) {
            empattn = safeStr(resp.getString("empattn"));
        }

        return hasText(empattn) ? empattn : "0";
    }
    
    public String getMaxmarks(String calid) throws Exception {

        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /training-calendar/{etcmKeyid}/maxmarks
        String url = API_BASE + "/" + enc(etcmKeyid) + "/maxmarks";

        CommonMessage.debugMsg("TRAINING-CAL MAXMARKS FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL MAXMARKS FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL MAXMARKS FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("MAXMARKS FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return "0";
        }

        JSONObject resp = JSONObject.fromObject(body);

        String maxmarks = "0";
        if (resp.has("maxmarks") && resp.get("maxmarks") != null) {
            maxmarks = safeStr(resp.getString("maxmarks"));
        }

        return hasText(maxmarks) ? maxmarks : "0";
    }
    
    public String getCutoff(String calid) throws Exception {

        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /training-calendar/{etcmKeyid}/maxmarks
        String url = API_BASE + "/" + enc(etcmKeyid) + "/cutoff";

        CommonMessage.debugMsg("TRAINING-CAL CUTOFF FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL CUTOFF FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL CUTOFF FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("CUTOFF FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return "0";
        }

        JSONObject resp = JSONObject.fromObject(body);

        String cutoff = "0";
        if (resp.has("cutoff") && resp.get("cutoff") != null) {
        	cutoff = safeStr(resp.getString("cutoff"));
        }

        return hasText(cutoff) ? cutoff : "0";
    }
    public String getAssesType(String calid) throws Exception {
        String etcmKeyid = safeStr(calid);
        if (!hasText(etcmKeyid)) {
            throw new IllegalArgumentException("Calendar id (calid) is required");
        }

        // ✅ URL: /training-calendar/{etcmKeyid}/assestype
        String url = API_BASE + "/" + enc(etcmKeyid) + "/assestype";
        CommonMessage.debugMsg("TRAINING-CAL ASSESTYPE FETCH url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL ASSESTYPE FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL ASSESTYPE FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("ASSESTYPE FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return "";
        }

        JSONObject resp = JSONObject.fromObject(body);

        String assesType = "";
        if (resp.has("assesType") && resp.get("assesType") != null) {
            assesType = safeStr(resp.getString("assesType"));
        }

        return hasText(assesType) ? assesType : "";
    }

 // =====================================================
 // ✅ CHECK ASSESSMENT COMPLETED
 // =====================================================
 /**
  * Calls SpringBoot API:
  * GET /api/ntrc/training-calendar/{etcmKeyid}/assessment/check
  * Response: { keyid: "...", cnt: "0" or "1" }
  */
 public String chkAssesmentComplted(CommonFilter commonFilter) throws Exception {

     // legacy key comes from commonFilter.setKey(keyid)
     String etcmKeyid = (commonFilter != null) ? safeStr(commonFilter.getKey()) : "";

     if (!hasText(etcmKeyid)) {
         CommonMessage.debugMsg("ASSESSMENT CHECK – no key, returning 0");
         return "0";
     }

     // ✅ correct SpringBoot path
     String url = API_BASE + "/" + enc(etcmKeyid) + "/assessment/check";

     CommonMessage.debugMsg("ASSESSMENT CHECK API GET url :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "GET", null);

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     CommonMessage.debugMsg("ASSESSMENT CHECK API status :: " + code);
     CommonMessage.debugMsg("ASSESSMENT CHECK API body   :: " + body);

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("ASSESSMENT CHECK failed HTTP=" + code + " body=[" + body + "]");
     }

     if (!hasText(body)) {
         return "0";
     }

     JSONObject obj = JSONObject.fromObject(body);

     // response returns cnt as "0" / "1"
     String cnt = safeStr(obj.optString("cnt"));
     if (!hasText(cnt)) cnt = "0";

     return cnt;
 }

 
//---------------------------------------Gopi-----------------------------
public String getAssessmentComStatus(String calid) throws Exception {
	    String etcmKeyid = safeStr(calid);
	    if (!hasText(etcmKeyid)) return "0";

	    String url = API_BASE + "/" + enc(etcmKeyid) + "/assessment/status";
	    HttpResponse res = api.makeAuthRequest(url, "GET", null);

	    int code = res.getStatusCode();
	    String body = res.getBody();
	    if (body == null) body = "";
	    if (code < 200 || code >= 300) return "0";
	    if (!hasText(body)) return "0";

	    JSONObject obj = JSONObject.fromObject(body);
	    String cnt = safeStr(obj.optString("cnt"));
	    return hasText(cnt) ? cnt : "0";
	}
public String resetAssessmentForMaintenance(String etcmKeyid) throws Exception {

    String key = safeStr(etcmKeyid);
    if (!hasText(key)) {
        throw new IllegalArgumentException("etcmKeyid is required for resetAssessmentForMaintenance()");
    }

    String url = API_BASE + "/" + enc(key) + "/assessment/reset";

    CommonMessage.debugMsg("APP MAINTENANCE RESET URL :: " + url);

    HttpResponse res = api.makeAuthRequest(url, "PUT", "");

    CommonMessage.debugMsg("APP MAINTENANCE RESET status :: " + res.getStatusCode());
    CommonMessage.debugMsg("APP MAINTENANCE RESET body   :: " + res.getBody());

    int code = res.getStatusCode();
    String body = res.getBody();
    if (body == null) body = "";

    if (code < 200 || code >= 300) {
        throw new IllegalStateException("APP MAINTENANCE RESET failed HTTP=" + code + " body=[" + body + "]");
    }

    if (!hasText(body)) {
        return "0";
    }

    JSONObject obj = JSONObject.fromObject(body);
    String result = safeStr(obj.optString("result"));
    return hasText(result) ? result : "0";
}
   


 // =====================================================
 // ✅ DELETE DETAIL RECORD (Session / Faculty / Unique Position)
 // Old Eclipse DAO logic:
 //   - Gengrid    -> delete faculty by keyId
 //   - UniqueGrid -> delete unique position by keyId
 //   - else       -> delete session by keyId (requires trainingId)
 //
 // SpringBoot API:
 //   DELETE /training-calendar/{etcmKeyid}/details/{gridId}/{keyId}
 // =====================================================
 public String deleteDetailRecord(String keyId, String gridId, String trainingId) throws Exception {

     if (!hasText(keyId)) {
         throw new IllegalArgumentException("Detail keyId is required");
     }
     if (!hasText(gridId)) {
         throw new IllegalArgumentException("gridId is required");
     }
     if (!hasText(trainingId)) {
         throw new IllegalArgumentException("TrainingId / etcmKeyid is required");
     }

     String url = API_BASE + "/" + trainingId.trim()
                + "/details/" + gridId.trim()
                + "/" + keyId.trim();

     CommonMessage.debugMsg("TRAINING-CAL API DELETE DETAIL url :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "DELETE", null);

     CommonMessage.debugMsg("TRAINING-CAL API DELETE DETAIL status :: " + res.getStatusCode());
     CommonMessage.debugMsg("TRAINING-CAL API DELETE DETAIL body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     // ✅ Spring returns 200 with JSON body, but keep 204 safe
     if (code == 204) {
         return "Data Deleted Successfully";
     }

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("DELETE DETAIL failed HTTP=" + code + " body=[" + body + "]");
     }

     // ✅ Expected Spring response:
     // { "msg": "...", "gridid": "..." }
     if (!hasText(body)) {
         return "Data Deleted Successfully";
     }

     JSONObject resp = JSONObject.fromObject(body);
     String msg = safeStr(resp.optString("msg"));

     if (!hasText(msg)) {
         msg = "Data Deleted Successfully";
     }

     return msg;
 }
//=====================================================
//✅ MULTIPLE UNIQUE POSITION SAVE (BULK)
//Eclipse old DAO inserted into ENT_TL_TRGCALUNQP in loop.
//SpringBoot endpoint:
//POST /training-calendar/{etcmKeyid}/unique/bulk
//Body: JSON Array of unique positions
//=====================================================
public List<EntTlTrgCalUnqp> CreateMultipleUnique(List<EntTlTrgCalUnqp> uniqueAddList) throws Exception {

  if (uniqueAddList == null || uniqueAddList.isEmpty()) {
      return new java.util.ArrayList<>();
  }

  // ✅ etcmKeyid required for URL
  String etcmKeyid = safeStr(uniqueAddList.get(0).getEtcuEtcmKeyid());
  if (!hasText(etcmKeyid)) {
      throw new IllegalArgumentException("etcmKeyid is required for CreateMultipleUnique()");
  }

  // ✅ Ensure etcmKeyid is set for every record (same as Eclipse servlet logic)
  for (EntTlTrgCalUnqp u : uniqueAddList) {
      if (u != null && !hasText(safeStr(u.getEtcuEtcmKeyid()))) {
          u.setEtcuEtcmKeyid(etcmKeyid);
      }
  }

  // ✅ Build JSON array body (net.sf.json has no add(), so use fromObject(list))
  java.util.List<Object> jsonList = new java.util.ArrayList<>();

  for (EntTlTrgCalUnqp u : uniqueAddList) {
      if (u != null) {
          // beanToJson already does:
          // - skips invalid values
          // - converts dd-MMM-yyyy / dd-MMM-yyyy HH:mm:ss to pg ISO using CommonFunctions
          JSONObject one = beanToJson(u);
          jsonList.add(one);
      }
  }

  JSONArray payloadArr = JSONArray.fromObject(jsonList);

  // ✅ Spring endpoint:
  // POST /training-calendar/{etcmKeyid}/unique/bulk
  String url = API_BASE + "/" + enc(etcmKeyid) + "/unique/bulk";

  CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE URL     :: " + url);
  CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE payload :: " + payloadArr);

  HttpResponse res = api.makeAuthRequest(url, "POST", payloadArr.toString());

  CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE status :: " + res.getStatusCode());
  CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE body   :: " + res.getBody());

  int code = res.getStatusCode();
  String body = res.getBody();
  if (body == null) body = "";

  if (code < 200 || code >= 300) {
      throw new IllegalStateException("BULK UNIQUE SAVE failed HTTP=" + code + " body=[" + body + "]");
  }

  // ✅ optional parse (not mandatory)
  try {
      JSONObject obj = JSONObject.fromObject(body);
      String msg = safeStr(obj.optString("msg"));
      String cnt = safeStr(obj.optString("count"));
      CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE msg   :: " + msg);
      CommonMessage.debugMsg("TRAINING-CAL BULK UNIQUE count :: " + cnt);
  } catch (Exception e) {
      // ignore parse issues
  }

  // ✅ same behavior as old DAO (returns list back)
  return uniqueAddList;
}
//=====================================================
//✅ BULK EMPLOYEE SAVE (Session Employee Link)
//Eclipse old DAO inserted into ENT_TL_TRGCALEMP in loop.
//SpringBoot endpoint:
//POST /training-calendar/{etcmKeyid}/sessions/{sessionKeyid}/employees/bulk
//Body: JSON Array of employees
//=====================================================
public List<EntTlTrgCalEmp> createSessionEmployee(List<EntTlTrgCalEmp> employees) throws Exception {

 if (employees == null || employees.isEmpty()) {
     return new java.util.ArrayList<>();
 }

 // ✅ Get etcmKeyid and etcsKeyid from first row (same as servlet values MasterId, SessionId)
 String etcmKeyid = safeStr(employees.get(0).getEtceEtcmKeyid());
 String sessionKeyid = safeStr(employees.get(0).getEtceEtcsKeyid());

 if (!hasText(etcmKeyid)) {
     throw new IllegalArgumentException("etcmKeyid is required for createSessionEmployee()");
 }
 if (!hasText(sessionKeyid)) {
     throw new IllegalArgumentException("sessionKeyid (etceEtcsKeyid) is required for createSessionEmployee()");
 }

 // ✅ Ensure every record contains master + session ids
 for (EntTlTrgCalEmp e : employees) {
     if (e == null) continue;

     if (!hasText(safeStr(e.getEtceEtcmKeyid()))) {
         e.setEtceEtcmKeyid(etcmKeyid);
     }
     if (!hasText(safeStr(e.getEtceEtcsKeyid()))) {
         e.setEtceEtcsKeyid(sessionKeyid);
     }
 }

 // ✅ Build JSON array payload
 java.util.List<Object> jsonList = new java.util.ArrayList<>();

 for (EntTlTrgCalEmp e : employees) {
     if (e != null) {
         JSONObject one = beanToJson(e);
         jsonList.add(one);
     }
 }

 JSONArray payloadArr = JSONArray.fromObject(jsonList);

 // ✅ Spring URL
 String url = API_BASE + "/" + enc(etcmKeyid)
         + "/sessions/" + enc(sessionKeyid)
         + "/employees/bulk";

 CommonMessage.debugMsg("TRAINING-CAL BULK EMP URL     :: " + url);
 CommonMessage.debugMsg("TRAINING-CAL BULK EMP payload :: " + payloadArr);

 HttpResponse res = api.makeAuthRequest(url, "POST", payloadArr.toString());

 CommonMessage.debugMsg("TRAINING-CAL BULK EMP status :: " + res.getStatusCode());
 CommonMessage.debugMsg("TRAINING-CAL BULK EMP body   :: " + res.getBody());

 int code = res.getStatusCode();
 String body = res.getBody();
 if (body == null) body = "";

 if (code < 200 || code >= 300) {
     throw new IllegalStateException("BULK EMP SAVE failed HTTP=" + code + " body=[" + body + "]");
 }

 // ✅ Optional parsing (for debug)
 try {
     JSONObject obj = JSONObject.fromObject(body);
     CommonMessage.debugMsg("TRAINING-CAL BULK EMP msg     :: " + safeStr(obj.optString("msg")));
     CommonMessage.debugMsg("TRAINING-CAL BULK EMP count   :: " + safeStr(obj.optString("count")));
     CommonMessage.debugMsg("TRAINING-CAL BULK EMP traCalId:: " + safeStr(obj.optString("traCalId")));
     CommonMessage.debugMsg("TRAINING-CAL BULK EMP sessionId:: " + safeStr(obj.optString("sessionId")));
 } catch (Exception ignore) {
 }

 // ✅ return list back (same pattern as bulk unique save)
 return employees;
}

//=====================================================
//✅ GET JH + DMT (sect + cell) FOR ROLE
//Eclipse old DAO:
// SELECT sect_keyid, cell_keyid
// FROM GEN_VW_FNLN
// WHERE fnln_keyid IN (select ROLE_FLID from GEN_TL_ROLEMST WHERE ROLE_KEYID = :roleKeyid)
//
//SpringBoot:
//GET /training-calendar/roles/{roleKeyid}/jh
//Response: { "successData": { "sect": "...", "cell": "..." } }
//=====================================================
public List<String[]> chkJHforRole(CommonFilter commonFilter) throws Exception {

  if (commonFilter == null) {
      throw new IllegalArgumentException("commonFilter is null");
  }

  String roleKeyid = safeStr(commonFilter.getKey()); // roleid

  if (!hasText(roleKeyid)) {
      throw new IllegalArgumentException("roleKeyid is required for chkJHforRole()");
  }

  // ✅ Spring endpoint:
  // GET /training-calendar/roles/{roleKeyid}/jh
  String url = API_BASE + "/roles/" + enc(roleKeyid) + "/jh";

  CommonMessage.debugMsg("TRAINING-CAL ROLE JH URL :: " + url);

  HttpResponse res = api.makeAuthRequest(url, "GET", null);

  CommonMessage.debugMsg("TRAINING-CAL ROLE JH status :: " + res.getStatusCode());
  CommonMessage.debugMsg("TRAINING-CAL ROLE JH body   :: " + res.getBody());

  int code = res.getStatusCode();
  String body = res.getBody();
  if (body == null) body = "";

  if (code < 200 || code >= 300) {
      throw new IllegalStateException("ROLE JH failed HTTP=" + code + " body=[" + body + "]");
  }

  JSONObject obj = JSONObject.fromObject(body);

  JSONObject success = null;
  try {
      success = obj.optJSONObject("successData");
  } catch (Exception e) {
      success = null;
  }

  String sect = "";
  String cell = "";

  if (success != null) {
      sect = safeStr(success.optString("sect"));
      cell = safeStr(success.optString("cell"));
  }

  // ✅ Return List<String[]> exactly like old DAO
  List<String[]> result = new java.util.ArrayList<>();
  result.add(new String[]{ sect, cell });

  return result;
}


    // =====================================================
    // ✅ CREATE
    // =====================================================
    public EntTlTragcalmst create(EntTlTragcalmst master) throws Exception {

        if (master == null) {
            throw new IllegalArgumentException("master is required");
        }

        JSONObject payload = buildRequest(master);

        CommonMessage.debugMsg("TRAINING-CAL API CREATE payload :: " + payload);

        HttpResponse res = api.makeAuthRequest(API_BASE, "POST", payload.toString());

        CommonMessage.debugMsg("TRAINING-CAL API CREATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL API CREATE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("CREATE failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject resp = JSONObject.fromObject(body);

        String traCalId = safeStr(resp.optString("traCalId"));
        if (hasText(traCalId)) {
            master.setEtcmKeyid(traCalId);
        }

        return master;
    }
 // =====================================================
 // ✅ ADD EMPLOYEE (Single) - API Layer Call
 // =====================================================
 /**
  * Calls SpringBoot API:
  * POST /api/training-calendar/{etcmKeyid}/sessions/{sessionKeyid}/employees
  * Body: EntTlTrgCalEmpDto (we send only required fields)
  */
 public EntTlTrgCalEmp EmployeebyCreate(EntTlTrgCalEmp emp) throws Exception {

     if (emp == null) {
         throw new IllegalArgumentException("Employee (EntTlTrgCalEmp) is required");
     }

     // ✅ Path params MUST be proper values (do NOT pass "{}")
     String etcmKeyid = safeStr(emp.getEtceEtcmKeyid());
     String sessionKeyid = safeStr(emp.getEtceEtcsKeyid());

     if (!hasText(etcmKeyid)) {
         throw new IllegalArgumentException("etcmKeyid is required to save employee");
     }
     if (!hasText(sessionKeyid)) {
         throw new IllegalArgumentException("sessionKeyid is required to save employee");
     }

     // ✅ Build minimal request payload (Spring will populate remaining defaults)
     JSONObject payload = new JSONObject();

     // required employee id
     String empmKeyid = safeStr(emp.getEtceEmpmKeyid());
     if (!hasText(empmKeyid)) {
         throw new IllegalArgumentException("etceEmpmKeyid (Employee ID) is required");
     }
     payload.put("etceEmpmKeyid", empmKeyid);

     // optional audit/flags
     String createdBy = safeStr(emp.getEtceCreatedby());
     if (hasText(createdBy)) {
         payload.put("etceCreatedby", createdBy);
     }

     payload.put("etceActive", hasText(safeStr(emp.getEtceActive())) ? safeStr(emp.getEtceActive()) : "Y");

     // optional roles (keep "{}" if UI sends it)
     payload.put("etceRoleKeyid", hasText(safeStr(emp.getEtceRoleKeyid())) ? safeStr(emp.getEtceRoleKeyid()) : "{}");
     payload.put("etceRoleDmt",   hasText(safeStr(emp.getEtceRoleDmt()))   ? safeStr(emp.getEtceRoleDmt())   : "{}");
     payload.put("etceRoleJh",    hasText(safeStr(emp.getEtceRoleJh()))    ? safeStr(emp.getEtceRoleJh())    : "{}");

     // optional temp fields
     payload.put("etceTempfield1", hasText(safeStr(emp.getEtceTempfield1())) ? safeStr(emp.getEtceTempfield1()) : "-");
     payload.put("etceTempfield2", hasText(safeStr(emp.getEtceTempfield2())) ? safeStr(emp.getEtceTempfield2()) : "-");
     payload.put("etceTempfield3", hasText(safeStr(emp.getEtceTempfield3())) ? safeStr(emp.getEtceTempfield3()) : "-");
     payload.put("etceTempfield4", hasText(safeStr(emp.getEtceTempfield4())) ? safeStr(emp.getEtceTempfield4()) : "-");
     payload.put("etceTempfield5", hasText(safeStr(emp.getEtceTempfield5())) ? safeStr(emp.getEtceTempfield5()) : "-");

     // ✅ URL
     String url = API_BASE + "/" + enc(etcmKeyid) + "/sessions/" + enc(sessionKeyid) + "/employees";

     CommonMessage.debugMsg("TRAINING-CAL EMP CREATE url     :: " + url);
     CommonMessage.debugMsg("TRAINING-CAL EMP CREATE payload :: " + payload);

     HttpResponse res = api.makeAuthRequest(url, "POST", payload.toString());

     CommonMessage.debugMsg("TRAINING-CAL EMP CREATE status :: " + res.getStatusCode());
     CommonMessage.debugMsg("TRAINING-CAL EMP CREATE body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("EMP SAVE failed HTTP=" + code + " body=[" + body + "]");
     }

     // ✅ Response example:
     // { msg, traCalId, sessionId, empKeyId }
     if (hasText(body)) {
         JSONObject resp = JSONObject.fromObject(body);

         String empKeyId = safeStr(resp.optString("empKeyId"));
         if (hasText(empKeyId)) {
             emp.setEtceKeyid(empKeyId);
         }

         // optional: sync ids from response
         String traCalId = safeStr(resp.optString("traCalId"));
         if (hasText(traCalId)) {
             emp.setEtceEtcmKeyid(traCalId);
         }
         String sessId = safeStr(resp.optString("sessionId"));
         if (hasText(sessId)) {
             emp.setEtceEtcsKeyid(sessId);
         }
     }

     return emp;
 }
//=====================================================
//✅ FETCH TRAINING CALENDAR - MULTIPLE UNIQUE ROLE SELECTION GRID (By CalendarId + Flid)
//Endpoint: GET /training-calendar/{etcmKeyid}/unique/roles?Calendarflid=...
//Response : { "uniquerole": [ [..6 cols..], ... ], "keyid": "...", "flid": "..." }
//=====================================================
public List<String[]> gwtJHRoleUniquePos(CommonFilter commonFilter) throws Exception {

  if (commonFilter == null) {
      throw new IllegalArgumentException("commonFilter is null");
  }

  String etcmKeyid = safeStr(commonFilter.getKey());   // CalendarId
  String flid      = safeStr(commonFilter.getFlid());  // Calendarflid

  if (!hasText(etcmKeyid)) {
      throw new IllegalArgumentException("CalendarId (commonFilter.key) is required");
  }

  // ✅ URL: /training-calendar/{etcmKeyid}/unique/roles?Calendarflid=...
  String url = API_BASE + "/" + enc(etcmKeyid) + "/unique/roles";
  if (hasText(flid)) {
      url += "?Calendarflid=" + enc(flid);
  }

  CommonMessage.debugMsg("TRAINING-CAL UNIQUE ROLE SEL FETCH url :: " + url);

  HttpResponse res = api.makeAuthRequest(url, "GET", null);

  CommonMessage.debugMsg("TRAINING-CAL UNIQUE ROLE SEL FETCH status :: " + res.getStatusCode());
  CommonMessage.debugMsg("TRAINING-CAL UNIQUE ROLE SEL FETCH body   :: " + res.getBody());

  int code = res.getStatusCode();
  String body = res.getBody();
  if (body == null) body = "";

  if (code < 200 || code >= 300) {
      throw new IllegalStateException("UNIQUE ROLE SEL FETCH failed HTTP=" + code + " body=[" + body + "]");
  }

  if (!hasText(body)) {
      return new ArrayList<>();
  }

  JSONObject resp = JSONObject.fromObject(body);

  List<String[]> out = new ArrayList<>();

  if (!resp.has("uniquerole") || resp.get("uniquerole") == null) {
      return out;
  }

  JSONArray arrData = resp.getJSONArray("uniquerole");

  for (int i = 0; i < arrData.length(); i++) {

      // each row = ["", "etcu_keyid", "role_keyid", "uniqposition", "sect_keyid", "cell_keyid"]
      JSONArray row = arrData.getJSONArray(i);

      String[] arr = new String[6];

      for (int j = 0; j < 6; j++) {
          String val = "";
          if (row != null && row.length() > j && row.get(j) != null) {
              val = safeStr(String.valueOf(row.get(j)));
          }
          arr[j] = val;
      }

      out.add(arr);
  }

  return out;
}


 // =====================================================
 // ✅ CHECK UNIQUE POSITION DUPLICATE
 // Eclipse old DAO:
 //   select count(*) from ENT_TL_TRGCALUNQP
 //   where ETCU_ETCM_KEYID = keyid
//      and ETCU_ROLE_KEYID = Upid
 //
 // SpringBoot:
 // GET /training-calendar/{etcmKeyid}/unique/check?roleKeyid=...&uniqueKeyid=...&chkuniq=...
 // Response JSON: { "uniquecnt": <int>, ... }
 // =====================================================
 public String chkUniqueposition(CommonFilter commonFilter) throws Exception {

     if (commonFilter == null) {
         throw new IllegalArgumentException("commonFilter is null");
     }

     String etcmKeyid   = safeStr(commonFilter.getKey());     // keyid
     String roleKeyid   = safeStr(commonFilter.getUtil());    // Upid
     String uniqueKeyid = safeStr(commonFilter.getKK());      // uniqukeyid (optional)
     String chkuniq     = safeStr(commonFilter.getCellch());  // chkuni (optional)

     if (!hasText(etcmKeyid) || !hasText(roleKeyid)) {
         throw new IllegalArgumentException("keyid(etcmKeyid) and Upid(roleKeyid) are required for chkUniqueposition()");
     }

     // ✅ Spring endpoint:
     // GET /training-calendar/{etcmKeyid}/unique/check?roleKeyid=...&uniqueKeyid=...&chkuniq=...
     String url = API_BASE + "/" + enc(etcmKeyid) + "/unique/check"
             + "?roleKeyid=" + enc(roleKeyid);

     // optional params (only append when available)
     if (hasText(uniqueKeyid)) {
         url += "&uniqueKeyid=" + enc(uniqueKeyid);
     }
     if (hasText(chkuniq)) {
         url += "&chkuniq=" + enc(chkuniq);
     }

     CommonMessage.debugMsg("TRAINING-CAL UNIQUE CHECK URL :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "GET", null);

     CommonMessage.debugMsg("TRAINING-CAL UNIQUE CHECK status :: " + res.getStatusCode());
     CommonMessage.debugMsg("TRAINING-CAL UNIQUE CHECK body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("UNIQUE CHECK failed HTTP=" + code + " body=[" + body + "]");
     }

     JSONObject obj = JSONObject.fromObject(body);

     // ✅ response key = uniquecnt
     int cnt = safeIntOrDefault(obj.opt("uniquecnt"), 0);

     return String.valueOf(cnt);
 }

    public String chkSessionDate(CommonFilter commonFilter) throws Exception {

        if (commonFilter == null) {
            throw new IllegalArgumentException("commonFilter is null");
        }

        String keyid = safeStr(commonFilter.getKey());              // etcmKeyid
        String sessionDate = safeStr(commonFilter.getDteOccuredto()); // "21-Jan-2026"
        String fromTime = safeStr(commonFilter.getDteAllotedfrm());   // "09:59"
        String toTime = safeStr(commonFilter.getDteAllotedto());      // "10:59"

        if (!hasText(keyid) || !hasText(sessionDate) || !hasText(fromTime) || !hasText(toTime)) {
            throw new IllegalArgumentException("keyid/sessionDate/fromTime/toTime required for chkSessionDate()");
        }

        // ✅ Spring endpoint:
        // GET /training-calendar/{etcmKeyid}/sessions/check-duplicate?sessionDate=...&fromTime=...&toTime=...
        String url = API_BASE + "/" + enc(keyid) + "/sessions/check-duplicate"
                + "?sessionDate=" + enc(sessionDate)
                + "&fromTime=" + enc(fromTime)
                + "&toTime=" + enc(toTime);

        CommonMessage.debugMsg("TRAINING-CAL CHECK DUPLICATE URL :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "GET", null);

        CommonMessage.debugMsg("TRAINING-CAL CHECK DUPLICATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL CHECK DUPLICATE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("CHECK DUPLICATE failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject obj = JSONObject.fromObject(body);

        // ✅ response key = sessioncnt
        int cnt = safeIntOrDefault(obj.opt("sessioncnt"), 0);

        return String.valueOf(cnt);
    }

    private String enc(String s) {
        try {
            return URLEncoder.encode(safeStr(s), "UTF-8");
        } catch (Exception e) {
            return safeStr(s);
        }
    }
    private int safeIntOrDefault(Object v, int def) {
        if (v == null) return def;
        try {
            String s = String.valueOf(v).trim();
            if (s.length() == 0 || "null".equalsIgnoreCase(s) || "{}".equals(s)) return def;
            return Integer.parseInt(s);
        } catch (Exception e) {
            return def;
        } 
    }
    // =====================================================
    // ✅ UPDATE
    // =====================================================
    public EntTlTragcalmst update(EntTlTragcalmst master) throws Exception {

        if (master == null || !hasText(master.getEtcmKeyid())) {
            throw new IllegalArgumentException("etcmKeyid is required for update");
        }

        JSONObject payload = buildRequest(master);

        String url = API_BASE + "/" + master.getEtcmKeyid().trim();

        CommonMessage.debugMsg("TRAINING-CAL API UPDATE url     :: " + url);
        CommonMessage.debugMsg("TRAINING-CAL API UPDATE payload :: " + payload);

        HttpResponse res = api.makeAuthRequest(url, "PUT", payload.toString());

        CommonMessage.debugMsg("TRAINING-CAL API UPDATE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL API UPDATE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("UPDATE failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject resp = JSONObject.fromObject(body);

        String traCalId = safeStr(resp.optString("traCalId"));
        if (hasText(traCalId)) {
            master.setEtcmKeyid(traCalId);
        }

        return master;
    }

    // =====================================================
    // ✅ DELETE
    // =====================================================
    public EntTlTragcalmst deleteTrainingCal(EntTlTragcalmst master) throws Exception {

        if (master == null || !hasText(master.getEtcmKeyid())) {
            throw new IllegalArgumentException("etcmKeyid is required for delete");
           
        }
       
        JSONObject payload = buildRequest(master);

        String url = API_BASE + "/" + master.getEtcmKeyid().trim();	
        
        	
        CommonMessage.debugMsg("TRAINING-CAL API DELETE url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "DELETE", null);

        CommonMessage.debugMsg("TRAINING-CAL API DELETE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL API DELETE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        // 204 is valid no-content
        if (code != 204 && (code < 200 || code >= 300)) {
            throw new IllegalStateException("DELETE failed HTTP=" + code + " body=[" + body + "]");
        }
		return master;
        
    }
    
    
    public void delete(String etcmKeyid) throws Exception {

        if (!hasText(etcmKeyid)) {
            return;
        }

        String url = API_BASE + "/" + etcmKeyid.trim();

        CommonMessage.debugMsg("TRAINING-CAL API DELETE url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "DELETE", null);

        CommonMessage.debugMsg("TRAINING-CAL API DELETE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("TRAINING-CAL API DELETE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        // 204 is valid no-content
        if (code != 204 && (code < 200 || code >= 300)) {
            throw new IllegalStateException("DELETE failed HTTP=" + code + " body=[" + body + "]");
        }
    }

    // =====================================================
    // ✅ BUILD TrainingCalendarRequest JSON
    // =====================================================
    private JSONObject buildRequest(EntTlTragcalmst master) throws Exception {

        JSONObject root = new JSONObject();

        // ✅ master : exclude nested objects to prevent recursive JSON
        root.put("master", beanToJson(master, "sessionMaster", "faculty", "roleLink"));

        // ✅ session
        EntTlTrgCalSession session = master.getsessionMaster();
        if (session != null && isSessionMeaningful(session)) {
            root.put("session", beanToJson(session));
        } else {
            root.put("session", (Object) null);   // ✅ IMPORTANT: avoid ambiguous put()
        }

        // ✅ faculty
        EntTlTrgFaculty faculty = master.getFaculty();
        if (faculty != null && isFacultyMeaningful(faculty)) {
            root.put("faculty", beanToJson(faculty));
        } else {
            root.put("faculty", (Object) null);   // ✅ IMPORTANT
        }

        // ✅ unique positions
        Object roleLinkObj = master.getRoleLink();

        if (roleLinkObj instanceof EntTlTrgCalUnqp) {

            EntTlTrgCalUnqp unqp = (EntTlTrgCalUnqp) roleLinkObj;

            if (unqp != null && isUnqpMeaningful(unqp)) {

                // ✅ NO JSONArray.add() here
                java.util.List<Object> list = new java.util.ArrayList<>();
                list.add(beanToJson(unqp));

                root.put("uniquePositions", (Object) JSONArray.fromObject(list));
            } else {
                root.put("uniquePositions", (Object) null);  // ✅ IMPORTANT
            }

        } else if (roleLinkObj instanceof List) {

            List<?> list = (List<?>) roleLinkObj;
            java.util.List<Object> jsonList = new java.util.ArrayList<>();

            for (Object it : list) {
                if (it != null) {
                    jsonList.add(beanToJson(it));
                }
            }

            if (!jsonList.isEmpty()) {
                root.put("uniquePositions", (Object) JSONArray.fromObject(jsonList));
            } else {
                root.put("uniquePositions", (Object) null);  // ✅ IMPORTANT
            }

        } else {
            root.put("uniquePositions", (Object) null);      // ✅ IMPORTANT
        }

        return root;
    }


    // ✅ avoids sending dummy session object always
    private boolean isSessionMeaningful(EntTlTrgCalSession s) {
        String till = firstNonEmpty(
                getAsString(s, "getEtcsTillDate"),
                getAsString(s, "getEtcsTilldate")
        );
        String from = firstNonEmpty(
                getAsString(s, "getEtcsFromDate"),
                getAsString(s, "getEtcsFromdate")
        );
        String name = safeStr(getAsString(s, "getEtcsName"));
        return !(isBlank(till) && isBlank(from) && isBlank(name));
    }

    private boolean isFacultyMeaningful(EntTlTrgFaculty f) {
        String fid = firstNonEmpty(
                getAsString(f, "getEtcfFacultyid"),
                getAsString(f, "getEtcfFacultyId")
        );
        return hasText(fid);
    }

    private boolean isUnqpMeaningful(EntTlTrgCalUnqp u) {
        String role = firstNonEmpty(
                getAsString(u, "getEtcuRoleKeyid"),
                getAsString(u, "getEtcuRolekeyid")
        );
        String dmt = firstNonEmpty(
                getAsString(u, "getEtcuRoleDmt"),
                getAsString(u, "getEtcuRoledmt")
        );
        String jh = firstNonEmpty(
                getAsString(u, "getEtcuRoleJh"),
                getAsString(u, "getEtcuRolejh")
        );
        return !(isBlank(role) && isBlank(dmt) && isBlank(jh));
    }

    // =====================================================
    // ✅ BEAN → JSON (generic)
    // =====================================================
    private JSONObject beanToJson(Object bean, String... excludeProps) throws Exception {

        JSONObject json = new JSONObject();
        if (bean == null) return json;

        Set<String> excludes = new HashSet<>();
        if (excludeProps != null) {
            for (String e : excludeProps) {
                if (e != null) excludes.add(e);
            }
        }

        for (PropertyDescriptor pd : Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors()) {

            String name = pd.getName();
            if ("class".equals(name)) continue;
            if (excludes.contains(name)) continue;

            if (pd.getReadMethod() == null) continue;

            Object value = pd.getReadMethod().invoke(bean);
            Object clean = cleanValue(value);

            if (clean == null) continue;

         // list support
            if (clean instanceof Collection) {

                java.util.List<Object> jsonList = new java.util.ArrayList<>();

                for (Object it : (Collection<?>) clean) {
                    Object itClean = cleanValue(it);

                    if (itClean != null) {
                        if (isSimpleValue(itClean)) {
                            jsonList.add(itClean);
                        } else {
                            jsonList.add(beanToJson(itClean));
                        }
                    }
                }

                // ✅ Works with ALL json-lib versions (no add/element)
                JSONArray arr = JSONArray.fromObject(jsonList);

                // ✅ Cast avoids ambiguous put()
                json.put(name, (Object) arr);
            }

            // nested bean support
            else if (!isSimpleValue(clean)) {
                json.put(name, beanToJson(clean));
            }
            // simple
            else {
                json.put(name, clean);
            }
        }

        return json;
    }

    private JSONObject beanToJson(Object bean) throws Exception {
        return beanToJson(bean, (String[]) null);
    }

    private boolean isSimpleValue(Object v) {
        return v instanceof String ||
               v instanceof Number ||
               v instanceof Boolean ||
               v instanceof JSONObject ||
               v instanceof JSONArray;
    }

    private Object cleanValue(Object v) {

        if (v == null) return null;

        // ✅ String cleanup + your date conversion
        if (v instanceof String) {

            String s = ((String) v).trim();

            if (!CommonFunctions.isValidKeyId(s)) return null;

            // ✅ "20-Jan-2026 11:58:49" → "2026-01-20T11:58:49"
            if (s.matches("\\d{2}-[A-Za-z]{3}-\\d{4} \\d{2}:\\d{2}:\\d{2}")) {
                return CommonFunctions.pg_getDateTimeFromTimeStamp(s);
            }
            
            if (s.matches("\\d{1}-[A-Za-z]{3}-\\d{4} \\d{2}:\\d{2}:\\d{2}")) {
                return CommonFunctions.pg_getDateTimeFromTimeStamp(s);
            }

            // ✅ "20-Jan-2026" → "2026-01-20T00:00:00"
            if (s.matches("\\d{2}-[A-Za-z]{3}-\\d{4}")) {
                return CommonFunctions.pg_getDateTimeFromDate(s);
            }
            
            if (s.matches("\\d{1}-[A-Za-z]{3}-\\d{4}")) {
                return CommonFunctions.pg_getDateTimeFromDate(s);
            }
            
            if (s.matches("\\d{1,2}-[A-Za-z]{3}-\\d{4} \\d{1,2}:\\d{2}")) {
                try {
                    java.text.SimpleDateFormat in  = new java.text.SimpleDateFormat("d-MMM-yyyy H:mm", java.util.Locale.ENGLISH);
                    java.util.Date d = in.parse(s);
                    java.text.SimpleDateFormat out = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.ENGLISH);
                    return out.format(d);
                } catch (Exception ignore) { }
            }

            // ✅ already ISO timestamp stays same
            return s;
        }

        // ✅ java.util.Date → ISO now
        if (v instanceof Date) {
            return CommonFunctions.pg_dateTimeNow();
        }

        // ✅ Keep numeric / boolean as-is
        return v;
    }


    // =====================================================
    // ✅ DATE NORMALIZER: "20-Jan-2026 11:58:49" -> ISO
    // =====================================================
    private String tryNormalizeToIso(String input) {

        if (!hasText(input)) return null;

        String s = input.trim();

        // already ISO
        if (s.contains("T") && s.length() >= 19) {
            return s;
        }

        try {
            DateTimeFormatter f1 = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("dd-MMM-yyyy HH:mm:ss")
                    .toFormatter(Locale.ENGLISH);

            LocalDateTime dt = LocalDateTime.parse(s, f1);
            return dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        } catch (Exception e) {
            // ignore
        }

        try {
            DateTimeFormatter f2 = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("dd-MMM-yyyy")
                    .toFormatter(Locale.ENGLISH);

            LocalDateTime dt = LocalDateTime.parse(s + " 00:00:00",
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .appendPattern("dd-MMM-yyyy HH:mm:ss")
                            .toFormatter(Locale.ENGLISH));

            return dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        } catch (Exception e) {
            // ignore
        }

        return null;
    }

    // =====================================================
    // ✅ JWT CLEAN (FishBone-style)
    // =====================================================
    private String cleanJwt(String token) {
        if (token == null) return null;

        String t = token.trim();

        if (t.toLowerCase().startsWith("bearer ")) {
            t = t.substring(7).trim();
        }

        t = t.replace("\r", "").replace("\n", "");

        return t;
    }

    // =====================================================
    // ✅ SAFE HELPERS (FishBone-style)
    // =====================================================
    private boolean hasText(String v) {
        if (v == null) return false;
        String s = v.trim();
        if (s.length() == 0) return false;
        if ("{}".equals(s)) return false;
        if ("null".equalsIgnoreCase(s)) return false;
        if ("undefined".equalsIgnoreCase(s)) return false;
        return true;
    }

    private boolean isBlank(String value) {
        return !hasText(value);
    }

    private String safeStr(Object v) {
        if (v == null) return "";
        String s = String.valueOf(v);
        if (!hasText(s)) return "";
        return s.trim();
    }

    private String firstNonEmpty(String... vals) {
        if (vals == null) return "";
        for (String s : vals) {
            if (hasText(s)) return s.trim();
        }
        return "";
    }

    // Reflection getter reader (safe)
    private String getAsString(Object obj, String getterName) {
        try {
            Object v = obj.getClass().getMethod(getterName).invoke(obj);
            return v == null ? "" : String.valueOf(v);
        } catch (Exception e) {
            return "";
        }
    }
    
}
