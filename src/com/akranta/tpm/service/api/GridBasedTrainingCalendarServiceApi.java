
package com.akranta.tpm.service.api;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.akranta.tpm.service.api.Api;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTragcalmst;
import com.akranta.tpm.model.EntTlTrgCalSession;
import com.akranta.tpm.model.EntTlTrgCalUnqp;
import com.akranta.tpm.model.EntTlTrgFaculty;
import com.akranta.tpm.model.EntTlTtgCalEmpatScore;
import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.TrainingBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.model.HttpResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GridBasedTrainingCalendarServiceApi {

    // Springboot controller mapping: @RequestMapping("/api/grid-training-calendar")


    private final Api api;
    private static final String API_BASE = "/grid-training-calendar";
    
    public GridBasedTrainingCalendarServiceApi(String jwtToken) {
        // ✅ Same debug style you use
        CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
        CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
        CommonMessage.debugMsg("JWT(clean length)=" + (cleanJwt(jwtToken) == null ? 0 : cleanJwt(jwtToken).length()));
        CommonMessage.debugMsg("JWT contains \\n ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\n")));
        CommonMessage.debugMsg("JWT contains \\r ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\r")));

        this.api = new Api(cleanJwt(jwtToken));
    }

    /**
     * CREATE -> calls SB: POST /api/grid-training-calendar/save
     * (SB decides create/update per row based on presence of etcmKeyid)
     */
    public List<EntTlTragcalmst> create(List<EntTlTragcalmst> masters, TrainingBean bean) throws Exception {
        return saveInternal(masters, bean);
    }

    /**
     * UPDATE -> calls SB: POST /api/grid-training-calendar/save
     * (SB decides create/update per row based on presence of etcmKeyid)
     */
    public List<EntTlTragcalmst> update(List<EntTlTragcalmst> masters, TrainingBean bean) throws Exception {
        return saveInternal(masters, bean);
    }
    
    public List<EntTlTragcalmst> GrdBsdTrgCalUpdate(List<EntTlTragcalmst> trngCalList) throws Exception {

        if (trngCalList == null || trngCalList.isEmpty()) {
            return new ArrayList<>();
        }

        // ✅ validation for update: keyid MUST be present
        int rowNo = 0;
        for (EntTlTragcalmst m : trngCalList) {
            rowNo++;
            if (m == null) continue;

            // MAIN DIFFERENCE vs create:
            if (!hasText(m.getEtcmKeyid())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Training calendar keyid (etcmKeyid) is required for update");
            }

            // keep the same mandatory validations
            if (!hasText(m.getEtcmFlid())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Factory (flid) is required");
            }
            if (!hasText(m.getEtcmLocation())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Location is required");
            }
            if (!hasText(m.getEtcmTopicid())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Topic id is required");
            }
        }

        // ✅ Same endpoint as create (Spring decides update because etcmKeyid is present)
        return saveInternal(trngCalList, null);
    }
    public List<String[]> getListTrgCalendarView(CommonFilter commonFilter, GridParams gridParams) throws Exception {

        // ✅ Legacy header for VIEW grid (must match SB response order)
        final String[] header = new String[] {
            "etcm_keyid","etcm_dmt","etcm_jh","etcm_flid","etcm_location","etcm_createdatetime",
            "etcm_anchoredby","anchoredbyid","topi_name","etcm_topicid","tcat_name","etcm_topiccategory",
            "idenfiedthg","etcm_function","trdm_name","etcm_trainingfunction","venu_name","etcm_venue",
            "uniqpose","uniqposeid","role_name","etcm_caldate","sessions","etcm_permittedstrength",
            "etcm_max_duration","ftym_name","assessmentrequiredtxt","etcm_assessmentrequired",
            "materialreadytxt","etcm_materialready","markbasedtxt","etcm_markbased","uniqpos",
            "employeeadd","empattednce","assemntcompl","etcm_tempfield6","trncompl","trncomplid",
            "compltdate","completedby","etcm_completedby","plannedempm_name","presentempm_name",
            "planed","attend","adherence","manhourse","absent","etcm_rating","ratingid",
            "etcm_comments","filemgr"
        };

        List<String[]> out = new ArrayList<>();
        out.add(header);

        if (commonFilter == null) return out;

        // ✅ Spring endpoint: POST /api/grid-training-calendar/grid/view
        String url = API_BASE + "/grid/view";
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID VIEW FETCH url :: " + url);

        // ✅ Build payload
        JSONObject req = new JSONObject();

        String key       = safeStr(commonFilter.getKey());        // trgCalId
        String sectionId = safeStr(commonFilter.getSectionId());  // dmt
        String cellId    = safeStr(commonFilter.getCellId());     // jh
        String tradeId   = safeStr(commonFilter.getTrarId());     // tradeId
        String uniquePos = safeStr(commonFilter.getUniquePos());  // uniqPostn
        String flid      = safeStr(commonFilter.getFlid());
        String fromDate  = safeStr(commonFilter.getFromDate());
        String toDate    = safeStr(commonFilter.getToDate());

        // If your legacy code uses "type/mode" for view, you can pass it (SB currently ignores it, safe to send)
        String mode = safeStr(commonFilter.getType());
        if (hasText(mode)) req.put("mode", mode);

        if (hasText(key))       req.put("trgCalId", key);
        if (hasText(sectionId)) req.put("sectionId", sectionId);
        if (hasText(cellId))    req.put("cellId", cellId);
        if (hasText(tradeId))   req.put("tradeId", tradeId);
        if (hasText(uniquePos)) req.put("uniqPostn", uniquePos);
        if (hasText(flid))      req.put("flid", flid);
        if (hasText(fromDate))  req.put("fromDate", fromDate);
        if (hasText(toDate))    req.put("toDate", toDate);

        // ✅ grid params (paging + optional filters)
        JSONObject gp = new JSONObject();
        if (gridParams != null) {
            gp.put("fromRow", safeStr(gridParams.getFromRow()));
            gp.put("toRow",   safeStr(gridParams.getToRow()));

            // gridFilters -> [{field,data}]
            List<JSONObject> gfList = new ArrayList<>();
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
            } catch (Exception ignore) {}

            gp.put("gridFilters", JSONArray.fromObject(gfList));
        }
        req.put("gridParams", gp);

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID VIEW FETCH payload :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

        CommonMessage.debugMsg("grid -TRAINING-CAL GRID VIEW FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID VIEW FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("GRID VIEW FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return out; // header only
        }

        JSONObject resp = JSONObject.fromObject(body);

        // ✅ totalRecordCnt -> gridParams + commonFilter (fixes UI totalRecords=0)
        try {
            if (resp.has("totalRecordCnt") && resp.get("totalRecordCnt") != null) {
                String cntStr = safeStr(String.valueOf(resp.get("totalRecordCnt")));
                if (hasText(cntStr)) {
                    long cnt = Long.parseLong(cntStr);
                    if (gridParams != null) gridParams.setTotalRecordCnt(cnt);
                    commonFilter.setTotalRecordCnt(cnt);
                }
            }
        } catch (Exception ignore) {}

        if (!resp.has("trainingcalendar") || resp.get("trainingcalendar") == null) {
            return out;
        }

        JSONArray dataArr = JSONArray.fromObject(resp.get("trainingcalendar"));
        if (dataArr == null || dataArr.length() == 0) {
            return out;
        }

        // ✅ if backend already sent header row, use it
        int headerLen = header.length;
        try {
            JSONArray firstRow = JSONArray.fromObject(dataArr.get(0));
            if (firstRow != null && firstRow.length() > 0) {
                String firstCell = safeStr(String.valueOf(firstRow.get(0)));
                if ("etcm_keyid".equalsIgnoreCase(firstCell)) {
                    out.clear();
                    String[] hdr = new String[firstRow.length()];
                    for (int j = 0; j < hdr.length; j++) hdr[j] = safeStr(String.valueOf(firstRow.get(j)));
                    out.add(hdr);
                    headerLen = hdr.length;
                }
            }
        } catch (Exception ignore) {}

        // ✅ rows
        for (int i = 0; i < dataArr.length(); i++) {
            Object rowObj = dataArr.get(i);
            if (rowObj == null) continue;

            JSONArray row = JSONArray.fromObject(rowObj);
            if (row == null) continue;

            // skip backend header row if present
            if (i == 0) {
                try {
                    String first = row.length() > 0 ? safeStr(String.valueOf(row.get(0))) : "";
                    if ("etcm_keyid".equalsIgnoreCase(first)) continue;
                } catch (Exception ignore) {}
            }

            String[] arr = new String[headerLen];
            for (int j = 0; j < headerLen; j++) {
                String val = "";
                if (row.length() > j && row.get(j) != null) {
                    val = safeStr(String.valueOf(row.get(j)));
                }
                arr[j] = val;
            }

            // ✅ Date conversions for UI:
            // index 5  = etcm_createdatetime
            // index 21 = etcm_caldate
            // index 39 = compltdate
            if (headerLen > 5)  arr[5]  = pg_getDateTimeFromPGTimeStamp1(arr[5]);
            if (headerLen > 21) arr[21] = pg_getDateTimeFromPGTimeStamp1(arr[21]);
            if (headerLen > 39) arr[39] = pg_getDateTimeFromPGTimeStamp1(arr[39]);

            out.add(arr);
        }

        return out;
    }

    
    public List<String[]> getListTrgCalendarModify(CommonFilter commonFilter, GridParams gridParams) throws Exception {

        // ✅ Legacy header (keep UI stable even when API returns 0 rows)
        final String[] header = new String[] {
            "etcm_keyid","etcm_dmt","etcm_jh","etcm_flid","etcm_location","etcm_createdatetime",
            "etcm_anchoredby","anchoredbyid","topi_name","etcm_topicid","tcat_name","etcm_topiccategory",
            "idenfiedthg","etcm_function","trdm_name","etcm_trainingfunction","venu_name","etcm_venue",
            "uniqpose","uniqposeid","etcm_caldate","etcm_permittedstrength","etcm_max_duration",
            "assessmentrequiredtxt","etcm_assessmentrequired","materialreadytxt","etcm_materialready",
            "markbasedtxt","etcm_markbased","role_name","uniqpos","employeeadd","empattednce",
            "plannedempm_name","assemntcompl","etcm_tempfield6","trncompl","trncomplid","compltdate",
            "completedby","etcm_completedby","etcm_rating","ratingid","etcm_comments","filemgr",
            "planed","attend","adherence","manhourse"
        };

        List<String[]> out = new ArrayList<>();
        out.add(header);

        if (commonFilter == null) return out;

        // ✅ Spring endpoint: POST /api/grid-training-calendar/grid/modify
        String url = API_BASE + "/grid/modify";
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID MODIFY FETCH url :: " + url);

        // ✅ Build payload
        JSONObject req = new JSONObject();

        // filters (send only if present; SB can default dates if missing)
        String key       = safeStr(commonFilter.getKey());        // trgCalId
        String sectionId = safeStr(commonFilter.getSectionId());  // dmt
        String cellId    = safeStr(commonFilter.getCellId());     // jh
        String tradeId   = safeStr(commonFilter.getTrarId());     // tradeId
        String uniquePos = safeStr(commonFilter.getUniquePos());  // uniqPostn
        String flid      = safeStr(commonFilter.getFlid());
        String fromDate  = safeStr(commonFilter.getFromDate());
        String toDate    = safeStr(commonFilter.getToDate());

        if (hasText(key))       req.put("trgCalId", key);
        if (hasText(sectionId)) req.put("sectionId", sectionId);
        if (hasText(cellId))    req.put("cellId", cellId);
        if (hasText(tradeId))   req.put("tradeId", tradeId);
        if (hasText(uniquePos)) req.put("uniqPostn", uniquePos);
        if (hasText(flid))      req.put("flid", flid);
        if (hasText(fromDate))  req.put("fromDate", fromDate);
        if (hasText(toDate))    req.put("toDate", toDate);
        
      //  if (hasText(fromDate))  req.put("fromDate", CommonFunctions.pg_getDateTimeFromDate(fromDate));
    //    if (hasText(toDate))    req.put("toDate", CommonFunctions.pg_getDateTimeFromDate(toDate));


        // grid params (paging + optional filters)
        JSONObject gp = new JSONObject();
        if (gridParams != null) {
            gp.put("fromRow", safeStr(gridParams.getFromRow()));
            gp.put("toRow",   safeStr(gridParams.getToRow()));

            // gridFilters -> [{field,data}]
            List<JSONObject> gfList = new ArrayList<>();
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
            } catch (Exception ignore) {}

            gp.put("gridFilters", JSONArray.fromObject(gfList));
        }
        req.put("gridParams", gp);

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID MODIFY FETCH payload :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

        CommonMessage.debugMsg("grid -TRAINING-CAL GRID MODIFY FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID MODIFY FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("GRID MODIFY FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return out; // header only
        }

        JSONObject resp = JSONObject.fromObject(body);

        // ✅ Update totalRecordCnt back to gridParams (legacy behavior)
        try {
            if (resp.has("totalRecordCnt") && resp.get("totalRecordCnt") != null && gridParams != null) {
                String cntStr = safeStr(String.valueOf(resp.get("totalRecordCnt")));
//                if (hasText(cntStr)) {
//                    gridParams.setTotalRecordCnt(Long.parseLong(cntStr));
//                }
                if (hasText(cntStr)) {
                    long cnt = Long.parseLong(cntStr);
                    if (gridParams != null) gridParams.setTotalRecordCnt(cnt);
                    commonFilter.setTotalRecordCnt(cnt); // ✅ THIS fixes totalRecords=0 in UI
                }
            }
           

        } catch (Exception ignore) {}

        if (!resp.has("trainingcalendar") || resp.get("trainingcalendar") == null) {
            return out; // header only
        }

        JSONArray dataArr = JSONArray.fromObject(resp.get("trainingcalendar"));
        if (dataArr == null || dataArr.length() == 0) {
            return out; // header only
        }

        // ✅ If backend already sent a header row, replace ours to avoid duplicates
        int headerLen = header.length;
        try {
            JSONArray firstRow = JSONArray.fromObject(dataArr.get(0));
            if (firstRow != null && firstRow.length() > 0) {
                String firstCell = safeStr(String.valueOf(firstRow.get(0)));
                if ("etcm_keyid".equalsIgnoreCase(firstCell)) {
                    out.clear();
                    String[] hdr = new String[firstRow.length()];
                    for (int j = 0; j < hdr.length; j++) hdr[j] = safeStr(String.valueOf(firstRow.get(j)));
                    out.add(hdr);
                    headerLen = hdr.length;
                }
            }
        } catch (Exception ignore) {}

        // ✅ Add rows (pad/truncate to header length)
        for (int i = 0; i < dataArr.length(); i++) {
            Object rowObj = dataArr.get(i);
            if (rowObj == null) continue;

            JSONArray row = JSONArray.fromObject(rowObj);
            if (row == null) continue;

            // skip backend header if we already used it
            if (i == 0) {
                try {
                    String first = row.length() > 0 ? safeStr(String.valueOf(row.get(0))) : "";
                    if ("etcm_keyid".equalsIgnoreCase(first)) continue;
                } catch (Exception ignore) {}
            }

            String[] arr = new String[headerLen];
            for (int j = 0; j < headerLen; j++) {
                String val = "";
                if (row.length() > j && row.get(j) != null) {
                    val = safeStr(String.valueOf(row.get(j)));
                }
                arr[j] = val;
            }
         // ✅ convert etcm_createdatetime only (index 5)
            if (headerLen > 5) {
                arr[5] = pg_getDateTimeFromPGTimeStamp1(arr[5]); // "2026-01-01T00:00" -> "01-Jan-2026 00:00:00"
            }
            
            if (headerLen > 20) arr[20] = pg_getDateTimeFromPGTimeStamp1(arr[20]); // training date
            if (headerLen > 38) arr[38] = pg_getDateTimeFromPGTimeStamp1(arr[38]);  // completed date
            out.add(arr);
        }

        return out;
    }
    
    private String pg_getDateTimeFromPGTimeStamp1(String v) {
        if (!hasText(v)) return "";

        String s = v.trim();

        // remove fractional seconds if any: 2026-01-01T00:00:00.000 -> 2026-01-01T00:00:00
        int dot = s.indexOf('.');
        if (dot > 0) s = s.substring(0, dot);

        // if ISO without seconds: 2026-01-01T00:00 -> add :00
        if (s.contains("T") && s.length() == 16) {
            s = s + ":00";
        }

        // if date only: 2026-01-01 -> make it datetime
        if (!s.contains("T") && s.length() == 10) {
            s = s + "T00:00:00";
        }

        // if "yyyy-MM-dd HH:mm:ss" -> convert to ISO
        if (!s.contains("T") && s.length() >= 19 && s.charAt(10) == ' ') {
            s = s.substring(0, 10) + "T" + s.substring(11, 19);
        }

        try {
            return CommonFunctions.pg_getDateTimeFromPGTimeStamp(s); // -> dd-MMM-yyyy HH:mm:ss
        } catch (Exception e) {
            return v; // fallback (don’t break grid)
        }
    }

    
    public List<String[]> getListTrgCalendar(CommonFilter commonFilter, GridParams gridParams) throws Exception {

        // ✅ Legacy header (keep UI stable even when API returns 0 rows)
        final String[] header = new String[] {
            "etcm_keyid","etcm_dmt","etcm_jh","etcm_flid","etcm_location","etcm_createdatetime",
            "etcm_anchoredby","anchoredbyid","topi_name","etcm_topicid","tcat_name","etcm_topiccategory",
            "idenfiedthg","etcm_function","trdm_name","etcm_trainingfunction","venu_name","etcm_venue",
            "uniqpose","uniqposeid","etcm_caldate","etcm_permittedstrength","etcm_max_duration",
            "assessment_text","etcm_assessmentrequired","material_text","etcm_materialready",
            "markbased_text","etcm_markbased","role_name","uniqpos","employeeadd","empattednce",
            "plannedempm_name","assemntcompl","etcm_tempfield6","trncompl","trncomplid","compltdate",
            "completedby","etcm_completedby","etcm_rating","ratingid","etcm_comments","filemgr",
            "planedemp","attndempl","adherence","manhourse"
        };

        List<String[]> out = new ArrayList<>();
        out.add(header);

        if (commonFilter == null) return out;

        String etcmKeyid = safeStr(commonFilter.getKey());
        if (!hasText(etcmKeyid)) return out;

        // ✅ Spring endpoint: POST /grid-training-calendar/{etcmKeyid}/grid
        String url = API_BASE + "/" + enc(etcmKeyid.trim()) + "/grid";
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID FETCH url :: " + url);

        // ✅ Build payload like the attendance fetch
        JSONObject req = new JSONObject();
        JSONObject gp  = new JSONObject();

        if (gridParams != null) {
            gp.put("fromRow", safeStr(gridParams.getFromRow()));
            gp.put("toRow",   safeStr(gridParams.getToRow()));

            // gridFilters -> [{field,data}]
            List<JSONObject> gfList = new ArrayList<>();
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
            } catch (Exception ignore) { }

            gp.put("gridFilters", JSONArray.fromObject(gfList));
        }

        req.put("gridParams", gp);

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID FETCH payload :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

        CommonMessage.debugMsg("grid -TRAINING-CAL GRID FETCH status :: " + res.getStatusCode());
        CommonMessage.debugMsg("grid -TRAINING-CAL GRID FETCH body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("GRID FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return out; // header only
        }

        JSONObject resp = JSONObject.fromObject(body);

        // ✅ Update totalRecordCnt back to gridParams (like old flow)
        try {
            if (resp.has("totalRecordCnt") && resp.get("totalRecordCnt") != null && gridParams != null) {
                String cntStr = safeStr(String.valueOf(resp.get("totalRecordCnt")));
                if (hasText(cntStr)) {
                    gridParams.setTotalRecordCnt(Long.parseLong(cntStr));
                }
            }
        } catch (Exception ignore) { }

        if (!resp.has("trainingcalendar") || resp.get("trainingcalendar") == null) {
            return out; // header only
        }

        JSONArray dataArr = JSONArray.fromObject(resp.get("trainingcalendar"));
        if (dataArr == null || dataArr.length() == 0) {
            return out; // header only
        }

        // ✅ If backend already sent a header row, replace ours to avoid duplicates
        int headerLen = header.length;
        try {
            JSONArray firstRow = JSONArray.fromObject(dataArr.get(0));
            if (firstRow != null && firstRow.length() > 0) {
                String firstCell = safeStr(String.valueOf(firstRow.get(0)));
                if ("etcm_keyid".equalsIgnoreCase(firstCell)) {
                    out.clear();
                    String[] hdr = new String[firstRow.length()];
                    for (int j = 0; j < hdr.length; j++) hdr[j] = safeStr(String.valueOf(firstRow.get(j)));
                    out.add(hdr);
                    headerLen = hdr.length;
                }
            }
        } catch (Exception ignore) { }

        // ✅ Add rows (pad/truncate to header length)
        for (int i = 0; i < dataArr.length(); i++) {
            Object rowObj = dataArr.get(i);
            if (rowObj == null) continue;

            JSONArray row = JSONArray.fromObject(rowObj);
            if (row == null) continue;

            // skip backend header if we already used it
            if (i == 0) {
                try {
                    String first = row.length() > 0 ? safeStr(String.valueOf(row.get(0))) : "";
                    if ("etcm_keyid".equalsIgnoreCase(first)) continue;
                } catch (Exception ignore) { }
            }

            String[] arr = new String[headerLen];
            for (int j = 0; j < headerLen; j++) {
                String val = "";
                if (row.length() > j && row.get(j) != null) {
                    val = safeStr(String.valueOf(row.get(j)));
                }
                arr[j] = val;
            }
            
            int createdIdx = 5;
            int calIdx     = 20;
            int compIdx    = 38;

            // If first value is a row number like "1", "2", "3"... then shift by +1
            if (headerLen > 0 && hasText(arr[0]) && arr[0].matches("\\d+")) {
                createdIdx++;
                calIdx++;
                compIdx++;
            }

            if (headerLen > createdIdx) arr[createdIdx] = pg_getDateTimeFromPGTimeStamp2(arr[createdIdx]);
            if (headerLen > calIdx)     arr[calIdx]     = pg_getDateTimeFromPGTimeStamp2(arr[calIdx]);
            if (headerLen > compIdx)    arr[compIdx]    = pg_getDateTimeFromPGTimeStamp2(arr[compIdx]);

            // ✅ convert etcm_createdatetime only (index 5)
//            if (headerLen > 5) {
//                arr[5] = pg_getDateTimeFromPGTimeStamp2(arr[5]); // "2026-01-01T00:00" -> "01-Jan-2026 00:00:00"
//            }
//            
//            if (headerLen > 20) arr[20] = pg_getDateTimeFromPGTimeStamp2(arr[20]); // training date
//            if (headerLen > 38) arr[38] = pg_getDateTimeFromPGTimeStamp2(arr[38]);
//            
            out.add(arr);
        }

        return out;
    }
    private String pg_getDateTimeFromPGTimeStamp2(String v) {
        if (!hasText(v)) return "";

        String s = v.trim();

        // 1) Remove timezone if present: Z or +05:30 or -04:00
        // Examples: 2026-01-01T00:00:00Z, 2026-01-01T00:00:00+05:30
        if (s.endsWith("Z")) {
            s = s.substring(0, s.length() - 1);
        } else {
            int plus = s.indexOf('+', 10);
            int minusTz = s.indexOf('-', 10); // careful: date also has '-'; start from 10
            int tzIdx = (plus > 0) ? plus : (minusTz > 0 ? minusTz : -1);
            if (tzIdx > 0) s = s.substring(0, tzIdx);
        }

        // 2) Remove fractional seconds (microseconds also)
        // 2026-01-22 12:00:49.449509 -> 2026-01-22 12:00:49
        int dot = s.indexOf('.');
        if (dot > 0) s = s.substring(0, dot);

        // 3) Normalize separator to ISO 'T' if there is a space at position 10
        // yyyy-MM-dd HH:mm:ss  OR yyyy-MM-dd HH:mm
        if (!s.contains("T") && s.length() >= 16 && s.charAt(10) == ' ') {
            s = s.substring(0, 10) + "T" + s.substring(11);
        }

        // 4) If only date is present: yyyy-MM-dd -> add time
        if (!s.contains("T") && s.length() == 10) {
            s = s + "T00:00:00";
        }

        // 5) If time exists but seconds missing: yyyy-MM-ddTHH:mm -> add seconds
        // length 16: "2026-02-04T00:00"
        if (s.contains("T") && s.length() == 16) {
            s = s + ":00";
        }

        // 6) If time exists and only HH:mm is present after T (rare cases like extra trimming)
        // Ensure final ISO is yyyy-MM-ddTHH:mm:ss
        // If someone sends yyyy-MM-ddTHH:mm (handled above) OR yyyy-MM-ddTHH:mm:ss (already ok)
        // no extra action needed here.

        try {
            return CommonFunctions.pg_getDateTimeFromPGTimeStamp(s); // dd-MMM-yyyy HH:mm:ss
        } catch (Exception e) {
            // Helpful debugging (optional):
            // CommonMessage.debugMsg("WARN: Date parse failed raw=[" + v + "] normalized=[" + s + "] err=" + e.getMessage());
            return v; // fallback (don’t break grid)
        }
    }

    

 // =====================================================
 // ✅ GRID BASED CREATE (like old DAO loop insert)
 // =====================================================
     public List<EntTlTragcalmst> GrdBsdTrgCalcreate(List<EntTlTragcalmst> trngCalList) throws Exception {

        if (trngCalList == null || trngCalList.isEmpty()) {
            return new ArrayList<>();
        }

        // Optional: quick validation before calling SpringBoot
        int rowNo = 0;
        for (EntTlTragcalmst m : trngCalList) {
            rowNo++;
            if (m == null) continue;

            if (!hasText(m.getEtcmFlid())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Factory (flid) is required");
            }
            if (!hasText(m.getEtcmLocation())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Location is required");
            }
            if (!hasText(m.getEtcmTopicid())) {
                throw new IllegalArgumentException("Row " + rowNo + " : Topic id is required");
            }
        }

        // ✅ Call SpringBoot once (multi-row) and update etcmKeyid back into the same list
        return saveInternal(trngCalList, null);
    }
     
     public EntTlTrgCalUnqp createUniquePostion(EntTlTrgCalUnqp u) throws Exception {

    	    if (u == null) throw new IllegalArgumentException("Unique position payload is required");

    	    String etcmKeyid = safeStr(getAsString(u, "getEtcuEtcmKeyid")).trim();
    	    if (!hasText(etcmKeyid)) {
    	        throw new IllegalArgumentException("Calendar id (etcuEtcmKeyid) is required for unique position save");
    	    }

    	    String url = API_BASE + "/" + enc(etcmKeyid) + "/unique";

    	    JSONObject req = new JSONObject();

    	    // create => empty keyid
    	    put(req, "etcuKeyid", "");

    	    put(req, "etcuEtcmKeyid", etcmKeyid);
    	    put(req, "etcuRoleKeyid", safeStr(getAsString(u, "getEtcuRoleKeyid")).trim());

    	    // IMPORTANT: must be STRING (not {})
    	    String roleDmt = safeStr(getAsString(u, "getEtcuRoledmt")).trim();
    	    String roleJh  = safeStr(getAsString(u, "getEtcuRolejh")).trim();

    	    // if blank, send "{}" as string (your SB service can also default)
    	    if (!hasText(roleDmt)) roleDmt = "-";
    	    if (!hasText(roleJh))  roleJh  = "-";

    	    put(req, "etcuRoledmt", roleDmt);   // <-- will become "etcuRoledmt":"{}"
    	    put(req, "etcuRolejh",  roleJh);    // <-- will become "etcuRolejh":"{}"

    	    put(req, "etcuTempfield1", defaultIfBlank(safeStr(getAsString(u,"getEtcuTempfield1")), "-"));
    	    put(req, "etcuTempfield2", defaultIfBlank(safeStr(getAsString(u,"getEtcuTempfield2")), "-"));
    	    put(req, "etcuTempfield3", defaultIfBlank(safeStr(getAsString(u,"getEtcuTempfield3")), "-"));
    	    put(req, "etcuTempfield4", defaultIfBlank(safeStr(getAsString(u,"getEtcuTempfield4")), "-"));
    	    put(req, "etcuTempfield5", defaultIfBlank(safeStr(getAsString(u,"getEtcuTempfield5")), "-"));

    	    put(req, "etcuActive", defaultIfBlank(safeStr(getAsString(u,"getEtcuActive")), "Y"));
    	    put(req, "etcuCreatedby", safeStr(getAsString(u,"getEtcuCreatedby")));

    	    String jsonPayload = req.toString();
            
    	    CommonMessage.debugMsg("grid -TYPE roleJh  :: " + req.get("etcuRolejh").getClass());
    	    CommonMessage.debugMsg("grid -TYPE roleDmt :: " + req.get("etcuRoledmt").getClass());

    	    CommonMessage.debugMsg("grid - TRG-CAL UNIQUE CREATE url    :: " + url);
    	    CommonMessage.debugMsg("grid -TRG-CAL UNIQUE CREATE payload :: " + jsonPayload);

    	    HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

    	    int code = res.getStatusCode();
    	    String body = res.getBody();
    	    if (body == null) body = "";

    	    CommonMessage.debugMsg("TRG-CAL UNIQUE CREATE status  :: " + code);
    	    CommonMessage.debugMsg("TRG-CAL UNIQUE CREATE body    :: " + body);

    	    if (code < 200 || code >= 300) {
    	        throw new IllegalStateException("UNIQUE CREATE failed HTTP=" + code + " body=[" + body + "]");
    	    }

    	    // read uniqueId back
    	    if (hasText(body)) {
    	        try {
    	            JSONObject resp = JSONObject.fromObject(body);
    	            if (resp.has("uniqueId")) {
    	                String uid = safeStr(String.valueOf(resp.get("uniqueId")));
    	                if (hasText(uid)) invokeSetter(u, "setEtcuKeyid", uid);
    	            }
    	        } catch (Exception ignore) {}
    	    }

    	    return u;
    	}
     public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception {

    	    if (commonFilter == null) {
    	        throw new IllegalArgumentException("commonFilter is null");
    	    }

    	    String etcmKeyid = safeStr(commonFilter.getKey());   // TrainingId
    	    String flid      = safeStr(commonFilter.getFlid());  // parent flid from JSP

    	    // ✅ derive fnln from popup filters (cell/section)
    	    String cellId    = safeStr(commonFilter.getCellId());
    	    String sectionId = safeStr(commonFilter.getSectionId());

    	    String fnln = ""; // the unit filter (CEL... / SEC...)
    	    if (hasText(cellId) && UIUtils.isValidKeyId(cellId)) {
    	        fnln = cellId.trim();
    	    } else if (hasText(sectionId) && UIUtils.isValidKeyId(sectionId)) {
    	        fnln = sectionId.trim();
    	    }
    	    String roleKeyId = safeStr(commonFilter.getRoleLevel());
    	    // ✅ if fnln is present, flid must NOT be sent (matches working payload)
    	    if (hasText(fnln)) {
    	        flid = "";
    	        roleKeyId="";
    	    }

    	    // role level (your console shows "10000" is coming)
    	//    String roleKeyId = safeStr(commonFilter.getRoleLevel());

    	    String pillarId       = safeStr(commonFilter.getRefdocid());
    	    String uniq           = safeStr(commonFilter.getUniquePos());
    	    String employeeType   = safeStr(commonFilter.getEmpwiseType());
    	    String employeeGender = safeStr(commonFilter.getEmpch());

    	    // ===== grid params =====
    	    JSONObject gp = new JSONObject();
    	    gp.put("fromRow", safeStr(commonFilter.getFromRow()));
    	    gp.put("toRow",   safeStr(commonFilter.getToRow()));

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

    	    req.put("key",        etcmKeyid);
    	    req.put("TrainingId", etcmKeyid);
    	    req.put("etcmKeyid",  etcmKeyid);

    	    // ✅ IMPORTANT: apply the working rule
    	    req.put("flid", flid);          // parent flid OR blank
    	    req.put("fnln", fnln);          // CEL... / SEC... OR blank
    	    req.put("factoryId", fnln);     // Spring can read factoryId too

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

    	    String apiUrl = API_BASE + "/unique/employees/popup";
    	    String jsonPayload = req.toString();

    	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP URL  :: " + apiUrl);
    	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP JSON :: " + jsonPayload);

    	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

    	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP status :: " + res.getStatusCode());
    	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP body   :: " + res.getBody());
 
     	    int code = res.getStatusCode();
     	    String body = res.getBody();
     	    if (body == null) body = "";
    
     	    if (code < 200 || code >= 300) {
     	        throw new IllegalStateException("grid - UNIQUE POPUP failed HTTP=" + code + " body=[" + body + "]");
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
     
     public String getempdata(String calendarkeyid) throws Exception {

         String etcmKeyid = safeStr(calendarkeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Calendar id (calid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/empdata
         String url = API_BASE + "/" + enc(etcmKeyid) + "/empdata";

         CommonMessage.debugMsg("grid -TRAINING-CAL EMPDATA FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid -TRAINING-CAL EMPDATA FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid -TRAINING-CAL EMPDATA FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid -EMPDATA FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     
     public String getempattn(String masterid) throws Exception {

         String etcmKeyid = safeStr(masterid);
         if (!hasText(etcmKeyid) || "{}".equals(etcmKeyid) || "-".equals(etcmKeyid)) {
             return "0";
         }
          // changing to remove error --
//         if (!hasText(etcmKeyid)) {
//             throw new IllegalArgumentException("Calendar id (calid) is required");
//         }

         // ✅ URL: /training-calendar/{etcmKeyid}/empattn
         String url = API_BASE + "/" + enc(etcmKeyid) + "/empattn";

         CommonMessage.debugMsg("grid - TRAINING-CAL EMPATTN FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL EMPATTN FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL EMPATTN FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - EMPATTN FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     
     public String getMaxmarks(String calendarkeyid) throws Exception {

         String etcmKeyid = safeStr(calendarkeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Calendar id (calid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/maxmarks
         String url = API_BASE + "/" + enc(etcmKeyid) + "/maxmarks";

         CommonMessage.debugMsg("grid - TRAINING-CAL MAXMARKS FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL MAXMARKS FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL MAXMARKS FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - MAXMARKS FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     
     public String getCutoff(String calendarkeyid) throws Exception {

         String etcmKeyid = safeStr(calendarkeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Calendar id (calid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/maxmarks
         String url = API_BASE + "/" + enc(etcmKeyid) + "/cutoff";

         CommonMessage.debugMsg("grid - TRAINING-CAL CUTOFF FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL CUTOFF FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL CUTOFF FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - CUTOFF FETCH failed HTTP=" + code + " body=[" + body + "]");
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
             CommonMessage.debugMsg("grid - ASSESSMENT CHECK – no key, returning 0");
             return "0";
         }

         // ✅ correct SpringBoot path
         String url = API_BASE + "/" + enc(etcmKeyid) + "/assessment/check";

         CommonMessage.debugMsg("grid -ASSESSMENT CHECK API GET url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         CommonMessage.debugMsg("grid -ASSESSMENT CHECK API status :: " + code);
         CommonMessage.debugMsg("grid -ASSESSMENT CHECK API body   :: " + body);

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid -ASSESSMENT CHECK failed HTTP=" + code + " body=[" + body + "]");
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
     public String getAssesType(String calendarkeyid) throws Exception {
         String etcmKeyid = safeStr(calendarkeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Calendar id (calid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/assestype
         String url = API_BASE + "/" + enc(etcmKeyid) + "/assestype";
         CommonMessage.debugMsg("grid - TRAINING-CAL ASSESTYPE FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL ASSESTYPE FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL ASSESTYPE FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - ASSESTYPE FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     public EntTlTragcalmst getselectdata(String calendarkeyid) throws Exception {

         String etcmKeyid = safeStr(calendarkeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Calendar id (calid) is required");
         }

         // ✅ URL: /{etcmKeyid}
         String url = API_BASE + "/" + enc(etcmKeyid);

         CommonMessage.debugMsg("grid -TRAINING-CAL MASTER FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid -TRAINING-CAL MASTER FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid -TRAINING-CAL MASTER FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid -MASTER FETCH failed HTTP=" + code + " body=[" + body + "]");
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
 	    CommonMessage.debugMsg("grid -TRAINING-CAL ATTENDANCE FETCH url :: " + url);

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
 	    CommonMessage.debugMsg("grid -TRAINING-CAL ATTENDANCE FETCH payload :: " + jsonPayload);

 	    HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

 	    CommonMessage.debugMsg("grid -TRAINING-CAL ATTENDANCE FETCH status :: " + res.getStatusCode());
 	    CommonMessage.debugMsg("grid -TRAINING-CAL ATTENDANCE FETCH body   :: " + res.getBody());

 	    int code = res.getStatusCode();
 	    String body = res.getBody();
 	    if (body == null) body = "";

 	    if (code < 200 || code >= 300) {
 	        throw new IllegalStateException("grid - ATTENDANCE FETCH failed HTTP=" + code + " body=[" + body + "]");
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

//     public List<String[]> getAllUniqueEmployeePopup(CommonFilter commonFilter) throws Exception {
//
// 	    if (commonFilter == null) {
// 	        throw new IllegalArgumentException("commonFilter is null");
// 	    }
//
// 	    // ===== values from old servlet mapping =====
// 	    String etcmKeyid       = safeStr(commonFilter.getKey());        // TrainingId
// 	    String flid            = safeStr(commonFilter.getFlid());       // flid
// 	    String fnln            = safeStr(commonFilter.getFactoryId());  // fnln / factoryId
// 	    String roleKeyId       = safeStr(commonFilter.getRoleLevel());  // roleKeyId
// 	    String pillarId        = safeStr(commonFilter.getRefdocid());   // pillarId (refdocid)
// 	    String uniq            = safeStr(commonFilter.getUniquePos());  // uniq (true/false)
// 	    String employeeType    = safeStr(commonFilter.getEmpwiseType());// EmployeeType
// 	    String employeeGender  = safeStr(commonFilter.getEmpch());      // EmployeeGender
//
// 	    // ===== grid params (fromRow/toRow + gridFilters) =====
// 	    JSONObject gp = new JSONObject();
// 	    gp.put("fromRow", safeStr(commonFilter.getFromRow()));
// 	    gp.put("toRow",   safeStr(commonFilter.getToRow()));
//
// 	    // build gridFilters WITHOUT JSONArray.add() / element()
// 	    java.util.List<JSONObject> gfList = new java.util.ArrayList<>();
// 	    try {
// 	        List<GridFilter> gfs = commonFilter.getGridFilter();
// 	        if (gfs != null) {
// 	            for (GridFilter f : gfs) {
// 	                if (f == null) continue;
// 	                String field = safeStr(f.getField());
// 	                String data  = safeStr(f.getData());
// 	                if (!hasText(field) || !hasText(data)) continue;
//
// 	                JSONObject o = new JSONObject();
// 	                o.put("field", field);
// 	                o.put("data", data);
// 	                gfList.add(o);
// 	            }
// 	        }
// 	    } catch (Exception ignore) {}
//
// 	    gp.put("gridFilters", JSONArray.fromObject(gfList));
//
// 	    // ===== final payload =====
// 	    JSONObject req = new JSONObject();
// 	    // send multiple key names (Spring service uses firstText() anyway)
// 	    req.put("key",        etcmKeyid);
// 	    req.put("TrainingId", etcmKeyid);
// 	    req.put("etcmKeyid",  etcmKeyid);
//
// 	    req.put("flid", flid);
// 	    req.put("fnln", fnln);
// 	    req.put("factoryId", fnln);
//
// 	    req.put("roleKeyId", roleKeyId);
// 	    req.put("roleLevel", roleKeyId);
//
// 	    req.put("pillarId", pillarId);
// 	    req.put("refdocid", pillarId);
//
// 	    req.put("uniq", uniq);
// 	    req.put("uniquePos", uniq);
//
// 	    req.put("EmployeeType", employeeType);
// 	    req.put("empwiseType",  employeeType);
//
// 	    req.put("EmployeeGender", employeeGender);
// 	    req.put("empch",          employeeGender);
//
// 	    req.put("gridParams", gp);
//
// 	    // ===== call Spring endpoint =====
// 	    String apiUrl = API_BASE + "/unique/employees/popup";
// 	    String jsonPayload = req.toString();
//
// 	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP URL  :: " + apiUrl);
// 	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP JSON :: " + jsonPayload);
//
// 	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
//
// 	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP status :: " + res.getStatusCode());
// 	    CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POPUP body   :: " + res.getBody());
//
// 	    int code = res.getStatusCode();
// 	    String body = res.getBody();
// 	    if (body == null) body = "";
//
// 	    if (code < 200 || code >= 300) {
// 	        throw new IllegalStateException("grid - UNIQUE POPUP failed HTTP=" + code + " body=[" + body + "]");
// 	    }
//
// 	    JSONObject obj = JSONObject.fromObject(body);
//
// 	    // keep legacy behavior: update total count in commonFilter
// 	    if (obj.has("totalRecordCnt")) {
// 	        int total = safeIntOrDefault(obj.opt("totalRecordCnt"), 0);
// 	        commonFilter.setTotalRecordCnt(total);
// 	    }
//
// 	    // Spring returns: { uniqueemployeepopup : [ [..row..], [..row..] ] }
// 	    JSONArray arr = new JSONArray();
// 	    if (obj.has("uniqueemployeepopup")) {
// 	        Object v = obj.get("uniqueemployeepopup");
// 	        if (v != null) {
// 	            arr = JSONArray.fromObject(v);
// 	        }
// 	    }
//
// 	    List<String[]> result = new java.util.ArrayList<>();
//
// 	    // ✅ add legacy header row for UIUtils.convertToJqGridTableObject
// 	    result.add(new String[]{
// 	            "slno",
// 	            "selctval",
// 	            "empm_keyid",
// 	            "empm_code",
// 	            "empm_name",
// 	            "empm_employeetype_desc",
// 	            "empm_gender_desc",
// 	            "ses",
// 	            "role_name",
// 	            "etcq_currentlevel",
// 	            "etcq_currentleveldate",
// 	            "sectionid",
// 	            "sect_name",
// 	            "cellid",
// 	            "cell_name",
// 	            "roleid",
// 	            "etcm_keyid_display",
// 	            "etcekeyid"
// 	    });
//
// 	    for (int i = 0; i < arr.length(); i++) {
// 	        Object rowObj = arr.get(i);
// 	        if (rowObj == null) continue;
//
// 	        JSONArray rowArr = (rowObj instanceof JSONArray)
// 	                ? (JSONArray) rowObj
// 	                : JSONArray.fromObject(rowObj);
//
// 	        String[] row = new String[rowArr.length()];
// 	        for (int j = 0; j < row.length; j++) {
// 	            Object vv = rowArr.get(j);
// 	            if (vv == null) {
// 	                row[j] = "";
// 	                continue;
// 	            }
// 	            String s = String.valueOf(vv).trim();
// 	            if (s.length() == 0 || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) {
// 	                row[j] = "";
// 	            } else {
// 	                row[j] = s;
// 	            }
// 	        }
// 	        result.add(row);
// 	    }
//
// 	    return result;
// 	}



  public EntTlTrgFaculty createFaculty(EntTlTrgFaculty f) throws Exception {

      if (f == null) {
          throw new IllegalArgumentException("Faculty payload is required");
      }

      // Calendar id is mandatory (path variable)
      String etcmKeyid = firstNonBlank(
              getAsString(f, "getEtcfEtcmKeyid"),
              getAsString(f, "getEtcmKeyid")
      );

      if (!hasText(etcmKeyid)) {
          throw new IllegalArgumentException("Calendar id (etcfEtcmKeyid) is required for faculty save");
      }

      String url = API_BASE + "/" + enc(etcmKeyid.trim()) + "/faculty";

      // ---- Build JSON payload (NO .element / NO JSONArray.add)
      JSONObject req = new JSONObject();

      // IMPORTANT:
      // If etcfKeyid is blank -> Spring creates
      // If etcfKeyid is present -> Spring updates (matches your SB createOrUpdateFaculty logic)
      String facultyKeyId = safeStr(getAsString(f, "getEtcfKeyid"));
      put(req, "etcfKeyid", facultyKeyId);

      put(req, "etcfEtcmKeyid", etcmKeyid.trim());

      // etcfEtcmFlid is NOT NULL in your DB (legacy code was sending "{}")
      String flid = firstNonBlank(
              getAsString(f, "getEtcfEtcmFlid"),
              getAsString(f, "getEtcmFlid"),
              getAsString(f, "getFlid")
      );
      flid = defaultIfBlank(flid, "-");
      put(req, "etcfEtcmFlid", flid);

      // Faculty id
      String facultyId = firstNonBlank(
              getAsString(f, "getEtcfFacultyId"),
              getAsString(f, "getEtcfFacultyid"),
              getAsString(f, "getFacultyId"),
              getAsString(f, "getFacultyid")
      );
      put(req, "etcfFacultyId", facultyId);   // safe for camel-case binders
      put(req, "etcfFacultyid", facultyId);   // safe for legacy field-name binders

      // Faculty type (default "-")
      String facultyType = firstNonBlank(
              getAsString(f, "getEtcfFacultytype"),
              getAsString(f, "getEtcfFacultyType"),
              getAsString(f, "getFacultytype"),
              getAsString(f, "getFacultyType")
      );
      facultyType = defaultIfBlank(facultyType, "-");
      put(req, "etcfFacultytype", facultyType);
      put(req, "etcfFacultyType", facultyType);

      // Active + createdBy (Spring can also fill these, but sending helps)
      put(req, "etcfActive", defaultIfBlank(normalizeYN(getAsString(f, "getEtcfActive")), "Y"));

      String createdBy = firstNonBlank(
              getAsString(f, "getEtcfCreatedby"),
              getAsString(f, "getEtcfCreatedBy")
      );
      put(req, "etcfCreatedby", createdBy);
      put(req, "etcfCreatedBy", createdBy);

      // Do NOT send dateadd/createdon/modifiedon from Eclipse (SpringBoot sets them)

      String jsonPayload = req.toString();

      CommonMessage.debugMsg("TRG-CAL FACULTY SAVE url     :: " + url);
      CommonMessage.debugMsg("TRG-CAL FACULTY SAVE payload :: " + jsonPayload);

      HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

      int code = res.getStatusCode();
      String body = res.getBody();
      if (body == null) body = "";

      CommonMessage.debugMsg("TRG-CAL FACULTY SAVE status  :: " + code);
      CommonMessage.debugMsg("TRG-CAL FACULTY SAVE body    :: " + body);

      if (code < 200 || code >= 300) {
          throw new IllegalStateException("FACULTY SAVE failed HTTP=" + code + " body=[" + body + "]");
      }

      // Spring controller returns: { msg, facultyId, traCalId }
      if (hasText(body)) {
          try {
              JSONObject resp = JSONObject.fromObject(body);

              String savedId = "";
              if (resp.has("facultyId")) {
                  savedId = safeStr(String.valueOf(resp.get("facultyId")));
              }
              if (!hasText(savedId) && resp.has("etcfKeyid")) {
                  savedId = safeStr(String.valueOf(resp.get("etcfKeyid")));
              }

              if (hasText(savedId)) {
                  // try both common setter spellings
                  invokeSetter(f, "setEtcfKeyid", savedId);
                  invokeSetter(f, "setEtcfKeyId", savedId);
              }
          } catch (Exception ignore) { }
      }

      return f;
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
     
     public EntTlTrgCalSession createSession(EntTlTrgCalSession s) throws Exception {

         if (s == null) {
             throw new IllegalArgumentException("Session payload is required");
         }
         if (!hasText(s.getEtcsEtcmKeyid())) {
             throw new IllegalArgumentException("Calendar id (etcsEtcmKeyid) is required");
         }

         // ---- Build SpringBoot URL: POST /api/grid-training-calendar/{etcmKeyid}/session
         String etcmKeyid = s.getEtcsEtcmKeyid().trim();
         String url = API_BASE + "/" + java.net.URLEncoder.encode(etcmKeyid, "UTF-8") + "/session";

         // ---- Build JSON payload
         JSONObject req = new JSONObject();

         // create => keyid MUST be empty
         put(req, "etcsKeyid", ""); // important: create flow

         put(req, "etcsEtcmKeyid", etcmKeyid);
         put(req, "etcsEtcmFlid", defaultIfBlank(safeStr(getAsString(s, "getEtcsEtcmFlid")), "-"));
         put(req, "etcsName", defaultIfBlank(safeStr(getAsString(s, "getEtcsName")), "Session"));

         // ✅ Convert legacy strings to SB-friendly format (dd-MMM-yyyy HH:mm)
         // Your servlet currently sets:
         //   etcsSessionDate = "20-JAN-2026" or "20-JAN-2026 00:00:00"
         //   etcsFromDate    = "20-JAN-2026 20:58"
         //   etcsTillDate    = "20-JAN-2026 23:58"
         String sessionDate = normalizeLegacyDateOnly(safeStr(getAsString(s, "getEtcsSessionDate")));
         String fromDate    = normalizeLegacyDateTime(safeStr(getAsString(s, "getEtcsFromDate")));
         String tillDate    = normalizeLegacyDateTime(safeStr(getAsString(s, "getEtcsTillDate")));

         // send as text; SB parses to LocalDateTime
         put(req, "etcsSessiondateText", sessionDate); // if you have dto text field
         put(req, "etcsFromdateText", fromDate);
         put(req, "etcsTilldateText", tillDate);

         // also send fallback names (controller may bind directly)
         put(req, "etcsSessiondate", sessionDate);
         put(req, "etcsFromdate", fromDate);
         put(req, "etcsTilldate", tillDate);

         // ✅ Do NOT send dateadd/createdon/modifiedon from Eclipse.
         // Spring will set them.

         put(req, "etcsTempfield1", defaultIfBlank(safeStr(getAsString(s, "getEtcsTempfield1")), "-"));
         put(req, "etcsTempfield2", defaultIfBlank(safeStr(getAsString(s, "getEtcsTempfield2")), "-"));
         put(req, "etcsTempfield3", defaultIfBlank(safeStr(getAsString(s, "getEtcsTempfield3")), "-"));
         put(req, "etcsTempfield4", defaultIfBlank(safeStr(getAsString(s, "getEtcsTempfield4")), "-"));
         put(req, "etcsTempfield5", defaultIfBlank(safeStr(getAsString(s, "getEtcsTempfield5")), "-"));
         put(req, "etcsActive", defaultIfBlank(safeStr(getAsString(s, "getEtcsActive")), "Y"));

         String jsonPayload = req.toString();

         CommonMessage.debugMsg("TRG-CAL SESSION CREATE url     :: " + url);
         CommonMessage.debugMsg("TRG-CAL SESSION CREATE payload :: " + jsonPayload);

         HttpResponse res = api.makeAuthRequest(url, "POST", jsonPayload);

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         CommonMessage.debugMsg("TRG-CAL SESSION CREATE status  :: " + code);
         CommonMessage.debugMsg("TRG-CAL SESSION CREATE body    :: " + body);

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("SESSION CREATE failed HTTP=" + code + " body=[" + body + "]");
         }

         // ---- set returned sessionId back into object
         if (hasText(body)) {
             try {
                 JSONObject resp = JSONObject.fromObject(body);
                 if (resp.has("sessionId")) {
                     String sid = safeStr(String.valueOf(resp.get("sessionId")));
                     if (hasText(sid)) {
                         invokeSetter(s, "setEtcsKeyid", sid);
                     }
                 }
             } catch (Exception ignore) {}
         }

         return s;
     }

     /* =========================
        Helpers used by the method
        ========================= */

     // "20-JAN-2026 00:00:00" -> "20-Jan-2026"
     private String normalizeLegacyDateOnly(String v) {
         if (!hasText(v)) return "";
         String x = v.trim();
         // remove time part if present
         if (x.length() > 11 && x.contains(" ")) {
             x = x.substring(0, x.indexOf(" ")).trim();
         }
         // normalize month case: JAN -> Jan (SB patterns use MMM)
         return normalizeMonthCase(x);
     }

     // "20-JAN-2026 23:58" or "20-JAN-2026 23:58:00" -> "20-Jan-2026 23:58"
     private String normalizeLegacyDateTime(String v) {
         if (!hasText(v)) return "";
         String x = v.trim();

         // if has seconds, drop seconds (SB accepts HH:mm well)
         // 20-JAN-2026 23:58:00 -> 20-JAN-2026 23:58
         if (x.matches(".*\\d{2}:\\d{2}:\\d{2}$")) {
             x = x.substring(0, x.length() - 3);
         }
         x = normalizeMonthCase(x);
         return x;
     }

     private String normalizeMonthCase(String x) {
         // expects DD-MMM-YYYY or DD-MMM-YYYY HH:mm
         // Make month "JAN" -> "Jan"
         try {
             // split by '-' to find month token
             String[] parts = x.split("-");
             if (parts.length >= 3) {
                 String mon = parts[1];
                 if (mon != null && mon.length() >= 3) {
                     String m = mon.substring(0, 1).toUpperCase() + mon.substring(1, 3).toLowerCase();
                     parts[1] = m;
                     // rebuild keeping rest (year + optional time)
                     String rebuilt = parts[0] + "-" + parts[1] + "-" + parts[2];
                     // if there were extra '-' beyond 3, keep them (rare)
                     for (int i = 3; i < parts.length; i++) rebuilt += "-" + parts[i];
                     return rebuilt;
                 }
             }
         } catch (Exception ignore) {}
         return x;
     }
     // =====================================================
     // ✅ CHECK UNIQUE POSITION DUPLICATE
     // Eclipse old DAO:
     //   select count(*) from ENT_TL_TRGCALUNQP
     //   where ETCU_ETCM_KEYID = keyid
//          and ETCU_ROLE_KEYID = Upid
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

         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE CHECK URL :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE CHECK status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE CHECK body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - UNIQUE CHECK failed HTTP=" + code + " body=[" + body + "]");
         }

         JSONObject obj = JSONObject.fromObject(body);

         // ✅ response key = uniquecnt
         int cnt = safeIntOrDefault(obj.opt("uniquecnt"), 0);

         return String.valueOf(cnt);
     }
     // =====================================================
     // ✅ FETCH TRAINING CALENDAR UNIQUE POSITION GRID (By ID) - API Layer
     // =====================================================
     public List<String[]> getNewUniqPosData(String trainingKeyid) throws Exception {

         String etcmKeyid = safeStr(trainingKeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Training id (keyid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/unique
         String url = API_BASE + "/" + enc(etcmKeyid) + "/unique";
         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POSITION FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POSITION FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL UNIQUE POSITION FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - UNIQUE POSITION FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     // =====================================================
     // ✅ FETCH TRAINING CALENDAR faculty (By ID) - API Layer
     // =====================================================
     public List<String[]> getFaculty(String progKeyid) throws Exception {

         String etcmKeyid = safeStr(progKeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Training id (progKeyid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/faculty
         String url = API_BASE + "/" + enc(etcmKeyid) + "/faculty";
         CommonMessage.debugMsg("grid - TRAINING-CAL FACULTY FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL FACULTY FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid -TRAINING-CAL FACULTY FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - FACULTY FETCH failed HTTP=" + code + " body=[" + body + "]");
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
     // ✅ GRID EMPLOYEE ATTENDANCE SAVE
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
    //         if (hasText(safeStr(s.getEtcaDept())))      o.put("etcaDept", s.getEtcaDept().trim());
     
             String dept = safeStr(s.getEtcaDept());
             if (hasText(dept) && !"{}".equals(dept.trim())) {
                 o.put("etcaDept", dept.trim());
             }
             // else don't send etcaDept at all

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
             String attDate = firstNonEmpty(getAsString(s, "getEtcaAttDate"), getAsString(s, "getEtcaAddDate"));
             if (hasText(attDate)) {
                 o.put("etcaAttDate", tryNormalizeToIso(attDate));
             }

             scoreDtoList.add(o);
         }

         payload.put("scores", JSONArray.fromObject(scoreDtoList));

         String url = API_BASE + "/" + enc(etcmKeyid) + "/attendance";
         CommonMessage.debugMsg(" grid ATTENDANCE API POST url :: " + url);
         CommonMessage.debugMsg("grid ATTENDANCE API POST payload :: " + payload);

         HttpResponse res = api.makeAuthRequest(url, "POST", payload.toString());

         CommonMessage.debugMsg(" grid ATTENDANCE API POST status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid ATTENDANCE API POST body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";
         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid ATTENDANCE SAVE failed HTTP=" + code + " body=[" + body + "]");
         }

         return scores;
     }
     private String firstNonEmpty(String... vals) {
         if (vals == null) return "";
         for (String s : vals) {
             if (hasText(s)) return s.trim();
         }
         return "";
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
     // ✅ FETCH TRAINING CALENDAR session GRID (By ID) - API Layer
     // =====================================================
     public List<String[]> getsession(String trgcalKeyid) throws Exception {

         String etcmKeyid = safeStr(trgcalKeyid);
         if (!hasText(etcmKeyid)) {
             throw new IllegalArgumentException("Training id (trgcalKeyid) is required");
         }

         // ✅ URL: /training-calendar/{etcmKeyid}/session
         String url = API_BASE + "/" + enc(etcmKeyid) + "/session";
         CommonMessage.debugMsg("grid - TRAINING-CAL SESSION FETCH url :: " + url);

         HttpResponse res = api.makeAuthRequest(url, "GET", null);

         CommonMessage.debugMsg("grid - TRAINING-CAL SESSION FETCH status :: " + res.getStatusCode());
         CommonMessage.debugMsg("grid - TRAINING-CAL SESSION FETCH body   :: " + res.getBody());

         int code = res.getStatusCode();
         String body = res.getBody();
         if (body == null) body = "";

         if (code < 200 || code >= 300) {
             throw new IllegalStateException("grid - SESSION FETCH failed HTTP=" + code + " body=[" + body + "]");
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

     CommonMessage.debugMsg("grid- TRAINING-CAL UNIQUE ROLE SEL FETCH url :: " + url);

     HttpResponse res = api.makeAuthRequest(url, "GET", null);

     CommonMessage.debugMsg("grid -TRAINING-CAL UNIQUE ROLE SEL FETCH status :: " + res.getStatusCode());
     CommonMessage.debugMsg("grid -TRAINING-CAL UNIQUE ROLE SEL FETCH body   :: " + res.getBody());

     int code = res.getStatusCode();
     String body = res.getBody();
     if (body == null) body = "";

     if (code < 200 || code >= 300) {
         throw new IllegalStateException("grid -UNIQUE ROLE SEL FETCH failed HTTP=" + code + " body=[" + body + "]");
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



    /**
     * SAVE/UPDATE (multi-row) -> SB endpoint:
     * POST /api/grid-training-calendar/save
     *
     * Response example:
     * { "msg": "...", "traCalIds": ["ETC..","ETC.."], "traCalId":"ETC..", "traCreateTime":"..." }
     */
   public static String pg_getDateTimeAllFormat(String input) {
	    if (input == null) return null;

	    String s = input.trim();
	    if (s.isEmpty() || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) {
	        return null;
	    }

	    // ✅ If it's already ISO like 2026-01-29T01:03:23 or with milliseconds, return as-is
	    // (Spring/Jackson can parse this easily)
	    if (s.matches("^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?$")) {
	        return s;
	    }

	    // ✅ If it's DB style with space: 2026-01-29 01:03:23, convert to ISO
	    if (s.matches("^\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?$")) {
	        return s.replace(' ', 'T');
	    }

	    // ✅ If it's UI style: 20-Jan-2026 or 8-Jan-2026
	    try {
	        java.time.LocalDate d = java.time.LocalDate.parse(
	                s,
	                java.time.format.DateTimeFormatter.ofPattern("d-MMM-yyyy", java.util.Locale.ENGLISH)
	        );
	        // return ISO datetime at midnight
	        return d.atStartOfDay().format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	    } catch (Exception ignore) {}

	    // ✅ If it's UI datetime: 28-Jan-2026 11:37:09
	    try {
	        java.time.LocalDateTime dt = java.time.LocalDateTime.parse(
	                s,
	                java.time.format.DateTimeFormatter.ofPattern("d-MMM-yyyy HH:mm:ss", java.util.Locale.ENGLISH)
	        );
	        return dt.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
	    } catch (Exception ignore) {}

	    throw new IllegalArgumentException("Unable to parse date: " + input);
	}

    private List<EntTlTragcalmst> saveInternal(List<EntTlTragcalmst> masters, TrainingBean bean) throws Exception {

        if (masters == null || masters.isEmpty()) {
            return new ArrayList<>();
        }

        // ---- derive common header values (optional, but helps SB fill missing values) ----
        EntTlTragcalmst first = masters.get(0);

        String flid       = firstNonBlank(getAsString(first, "getEtcmFlid"), getAsString(first, "getFlid"));
        String locationId = firstNonBlank(getAsString(first, "getEtcmLocation"), getAsString(first, "getLocationId"));
        String sectionId  = firstNonBlank(getAsString(first, "getEtcmDmt"), getAsString(first, "getSectionId"));
        String cellId     = firstNonBlank(getAsString(first, "getEtcmJh"), getAsString(first, "getCellId"));
        String createdBy  = firstNonBlank(getAsString(first, "getEtcmCreatedBy"), getAsString(first, "getEtcmCreatedby"));

        // Note: you can send blank for sectionId/cellId; SpringBoot service will default to "{}" before saving (NOT NULL columns)
        sectionId = defaultIfBlank(sectionId, "");
        cellId    = defaultIfBlank(cellId, "");

        // If caller passes a calendarId separately in your flow, try to derive it
        String calendarId = firstNonBlank(getAsString(first, "getEtcmKeyid"), getAsString(first, "getTrnCalId"));

        // ---- build calendarDetails as JSON (NO JSONArray.add / element usage) ----
        List<JSONObject> detailList = new ArrayList<>();

        for (EntTlTragcalmst m : masters) {
            if (m == null) continue;

            JSONObject row = new JSONObject();

            // Key (blank means create)
            put(row, "etcmKeyid", safeStr(getAsString(m, "getEtcmKeyid")));

            // Common master fields
            put(row, "etcmFlid",      defaultIfBlank(firstNonBlank(getAsString(m, "getEtcmFlid"), flid), flid));
            put(row, "etcmLocation",  defaultIfBlank(firstNonBlank(getAsString(m, "getEtcmLocation"), locationId), locationId));

            // NOT NULL columns -> force {}
            put(row, "etcmDmt", defaultIfBlank(firstNonBlank(getAsString(m, "getEtcmDmt"), sectionId), ""));
            put(row, "etcmJh",  defaultIfBlank(firstNonBlank(getAsString(m, "getEtcmJh"), cellId), ""));

            // Topic
            put(row, "etcmTopicid", firstNonBlank(
                    getAsString(m, "getEtcmTopicid"),
                    getAsString(m, "getCmbEtcmTopicid") // if your eclipse bean stores UI name
            ));

            // Dates: send as *Text* also (SB controller parses *_Text fields)
            String calDateText = firstNonBlank(
                    getAsString(m, "getEtcmCalendarDateText"),
                    getAsString(m, "getEtcmCalendarDate"),
                    getAsString(m, "getDteEtcmCalendarDate"),
                    getAsString(m, "getEtcmCaldate") // if you already store DB field name
            );
            String createDateText = firstNonBlank(
                    getAsString(m, "getEtcmCreatedatetimeText"),
                    getAsString(m, "getEtcmCreatedDateTime"),
                    getAsString(m, "getDteEtcmCreatedDateTime"),
                    getAsString(m, "getEtcmCreatedatetime")
            );

            put(row, "etcmCalendarDateText", calDateText);
            put(row, "etcmCreatedatetimeText", createDateText);

            // Legacy keys (some UIs / DTOs may expect these)
            put(row, "etcmCalendarDate", calDateText);
            put(row, "etcmCreatedDateTime", createDateText);

            // Other columns (use whichever getters exist in your eclipse model)
            put(row, "etcmRemarks", firstNonBlank(getAsString(m, "getEtcmRemarks"), getAsString(m, "getTxtEtcmRemarks")));
            
            // REMOVING VIGNESH FOR UNIQUE POSITION
       //     put(row, "etcmGeneral", normalizeYN(firstNonBlank(getAsString(m, "getEtcmGeneral"), getAsString(m, "getChkEtcmGeneral"))));
      //      put(row, "etcmUniqueposition", normalizeYN(firstNonBlank(getAsString(m, "getEtcmUniquepos"), getAsString(m, "getUniquePosition"))));
      //      put(row, "etcmMSD", normalizeYN(firstNonBlank(getAsString(m, "getEtcmMsd"), getAsString(m, "getCmbEtcmMsd"))));
           
            String mode = firstNonBlank(
                    getAsString(m, "getChkEtcmGeneral"),  // UI: UQ/GN/MS
                    getAsString(m, "getEtcmGeneral")      // sometimes stored here
            );
            mode = safeStr(mode).trim();

            String general = "Y";
            String unique  = "N";
            String msd     = "N";

            if ("UQ".equalsIgnoreCase(mode)) {
                unique  = "Y";
                general = "N";
                msd     = "N";
            } else if ("GN".equalsIgnoreCase(mode)) {
                unique  = "N";
                general = "Y";
                msd     = "N";
            } else if ("MS".equalsIgnoreCase(mode)) {
                unique  = "N";
                general = "N";
                msd     = "Y";
            } else {
                // if already Y/N passed in model, keep it
                general = normalizeYN(mode);
                unique  = normalizeYN(firstNonBlank(getAsString(m, "getEtcmUniquepos"), getAsString(m, "getEtcmUniqueposition")));
                msd     = normalizeYN(firstNonBlank(getAsString(m, "getEtcmMsd"), getAsString(m, "getEtcmMSD")));
            }

            put(row, "etcmGeneral", general);

            // send both names to avoid mismatch
            put(row, "etcmUniquepos", unique);
            put(row, "etcmUniqueposition", unique);

            put(row, "etcmMSD", msd);
            put(row, "etcmMsd", msd);

            
       //     put(row, "etcmChkCompleted", normalizeYN(firstNonBlank(getAsString(m, "getEtcmChkcompleted"), getAsString(m, "getCmbEtcmChkCompleted"))));
   
            put(row, "etcmChkCompleted", m.getEtcmChkCompleted());
            
       //     put(row, "etcmCompletedDate", firstNonBlank(getAsString(m, "getEtcmCompletedDate"), getAsString(m, "getDteEtcmCompletedDate")));
           
          //  put(row, "etcmCompletedDate",CommonFunctions.pg_getDateTimeFromDate(m.getEtcmCompletedDate()) );
         
            String completedRaw = firstNonBlank(
                    getAsString(m, "getDteEtcmCompletedDate"),
                    getAsString(m, "getEtcmCompletedDate")
            );

            put(row, "etcmCompletedDate", pg_getDateTimeAllFormat(completedRaw));

           
         //   o.setOplmDate(CommonFunctions.pg_getDateTimeFromDate(o.getOplmDate()));
            
            put(row, "etcmCompletedBy", firstNonBlank(getAsString(m, "getEtcmCompletedBy"), getAsString(m, "getCmbEtcmCompletedBy")));

            put(row, "etcmMaxDuration", firstNonBlank(getAsString(m, "getEtcmMaxDuration"), getAsString(m, "getTxtEtcmMaxDuration")));
            put(row, "etcmFunction", firstNonBlank(getAsString(m, "getEtcmFunction"), getAsString(m, "getCmbEtcmFunction")));
            put(row, "etcmVenue", firstNonBlank(getAsString(m, "getEtcmVenue"), getAsString(m, "getCmbEtcmVenue")));
            put(row, "etcmPermittedStrength", firstNonBlank(getAsString(m, "getEtcmPermittedStrength"), getAsString(m, "getTxtEtcmPermittedStrength")));

            put(row, "etcmMaterialsReady", normalizeYN(firstNonBlank(getAsString(m, "getEtcmMaterialsReady"), getAsString(m, "getEtcmMaterialready"), getAsString(m, "getCmbEtcmMaterialsReady"))));
            put(row, "etcmAssessmentReq", normalizeYN(firstNonBlank(getAsString(m, "getEtcmAssessmentReq"), getAsString(m, "getEtcmAssessmentrequired"), getAsString(m, "getCmbEtcmAssessmentReq"))));
            put(row, "etcmMarksBased", normalizeYN(firstNonBlank(getAsString(m, "getEtcmMarksBased"), getAsString(m, "getEtcmMarkbased"), getAsString(m, "getCmbEtcmMarksBased"))));

            put(row, "etcmFileManagedId", firstNonBlank(getAsString(m, "getEtcmFileManagedId"), getAsString(m, "getEtcmFilemgnid")));
            put(row, "etcmAnchoredby", firstNonBlank(getAsString(m, "getEtcmAnchoredby"), getAsString(m, "getCmbEtcmAnchoredby")));
            put(row, "etcmTrainingfunction", firstNonBlank(getAsString(m, "getEtcmTrainingfunction"), getAsString(m, "getCmbEtcmTrainingfunction")));
            put(row, "etcmRating", firstNonBlank(getAsString(m, "getEtcmRating"), getAsString(m, "getCmbEtcmRating")));
            put(row, "etcmComments", firstNonBlank(getAsString(m, "getEtcmComments"), getAsString(m, "getTxtEtcmComments")));
            put(row, "etcmTopiccategory", firstNonBlank(getAsString(m, "getEtcmTopiccategory"), getAsString(m, "getCmbEtcmTopiccategory")));

            put(row, "etcmTempfield6",  getAsString(m, "getEtcmTempfield6"));
            put(row, "etcmTempfield7",  getAsString(m, "getEtcmTempfield7"));
            put(row, "etcmTempfield8",  getAsString(m, "getEtcmTempfield8"));
            put(row, "etcmTempfield9",  getAsString(m, "getEtcmTempfield9"));
            put(row, "etcmTempfield10", getAsString(m, "getEtcmTempfield10"));

            put(row, "etcmActive", normalizeYN(getAsString(m, "getEtcmActive")));
            put(row, "etcmCreatedBy", defaultIfBlank(firstNonBlank(getAsString(m, "getEtcmCreatedBy"), getAsString(m, "getEtcmCreatedby"), createdBy), createdBy));

            detailList.add(row);
        }

        JSONObject req = new JSONObject();
        req.put("calendarDetails", JSONArray.fromObject(detailList));

        // Optional header fields (controller uses these only if a row is missing them)
        req.put("flid", flid);
        req.put("locationId", locationId);
        req.put("sectionId", sectionId);
        req.put("cellId", cellId);
        req.put("createdBy", createdBy);
        if (hasText(calendarId)) {
            req.put("calendarId", calendarId);
        }

        String jsonPayload = req.toString();

        CommonMessage.debugMsg("GRID-TRG-CAL SAVE url     :: " + (API_BASE + "/save"));
        CommonMessage.debugMsg("GRID-TRG-CAL SAVE payload :: " + jsonPayload);



        HttpResponse res = api.makeAuthRequest(API_BASE + "/save", "POST", jsonPayload);

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        CommonMessage.debugMsg("GRID-TRG-CAL SAVE status  :: " + code);
        CommonMessage.debugMsg("GRID-TRG-CAL SAVE body    :: " + body);

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("GRID TRAINING CAL SAVE failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            return masters;
        }

        JSONObject resp = JSONObject.fromObject(body);

        // Update keyids back into eclipse objects (important for legacy flow)
        try {
            if (resp.has("traCalIds") && resp.get("traCalIds") != null) {
                JSONArray ids = resp.getJSONArray("traCalIds");
                for (int i = 0; i < ids.length() && i < masters.size(); i++) {
                    String id = safeStr(String.valueOf(ids.get(i)));
                    if (hasText(id)) {
                        invokeSetter(masters.get(i), "setEtcmKeyid", id);
                    }
                }
            } else if (resp.has("traCalId") && resp.get("traCalId") != null) {
                String id = safeStr(String.valueOf(resp.get("traCalId")));
                if (hasText(id)) {
                    invokeSetter(masters.get(0), "setEtcmKeyid", id);
                }
            }
        } catch (Exception e) {
            // ignore (do not break save)
        }

        return masters;
    }
    
    public void DeleteCal(String etcmKeyid) throws Exception {

        if (!hasText(etcmKeyid)) {
            return;
        }

        String url = API_BASE + "/" + etcmKeyid.trim();

        CommonMessage.debugMsg("GRID BASED - TRAINING-CAL API DELETE url :: " + url);

        HttpResponse res = api.makeAuthRequest(url, "DELETE", null);

        CommonMessage.debugMsg("GRID BASED - TRAINING-CAL API DELETE status :: " + res.getStatusCode());
        CommonMessage.debugMsg("GRID BASED - TRAINING-CAL API DELETE body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        // 204 is valid no-content
        if (code != 204 && (code < 200 || code >= 300)) {
            throw new IllegalStateException("DELETE failed HTTP=" + code + " body=[" + body + "]");
        }
    }

    // -------------------- helpers (same style as NewTrainingcalendarServiceApi) --------------------

    private static void put(JSONObject obj, String key, String value) {
        if (obj == null || key == null) return;
        if (value == null) value = "";
        obj.put(key, value);
    }

    private static String normalizeYN(String v) {
        if (!hasText(v)) return "";
        String x = v.trim();
        if ("Y".equalsIgnoreCase(x) || "N".equalsIgnoreCase(x)) return x.toUpperCase();
        if ("true".equalsIgnoreCase(x)) return "Y";
        if ("false".equalsIgnoreCase(x)) return "N";
        // special case seen in legacy UI (example: chkEtcmGeneral = "GN")
        if ("GN".equalsIgnoreCase(x)) return "Y";
        return x; // keep as-is if caller already sends correct value
    }

    private static String cleanJwt(String jwt) {
        if (jwt == null) return "";
        String s = jwt.trim();
        if (s.startsWith("Bearer ")) s = s.substring(7).trim();
        return s;
    }

    private static boolean hasText(String s) {
        return s != null && s.trim().length() > 0 && !"null".equalsIgnoreCase(s.trim());
    }

    private static String safeStr(String s) {
        return s == null ? "" : s;
    }

    private static String defaultIfBlank(String v, String fallback) {
        if (!hasText(v) || "{}".equals(v.trim())) return fallback;
        return v;
    }

    private static String firstNonBlank(String... vals) {
        if (vals == null) return "";
        for (String v : vals) {
            if (hasText(v) && !"{}".equals(v.trim())) return v;
        }
        return "";
    }

    private static String enc(String s) {
        try {
            return URLEncoder.encode(s == null ? "" : s, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            return s == null ? "" : s;
        }
    }

    private static String getAsString(Object o, String getterName) {
        try {
            if (o == null || !hasText(getterName)) return "";
            Method m = o.getClass().getMethod(getterName);
            Object v = m.invoke(o);
            return v == null ? "" : String.valueOf(v);
        } catch (Exception e) {
            return "";
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

    private static void invokeSetter(Object o, String setterName, String value) {
        try {
            if (o == null || !hasText(setterName)) return;
            Method m = o.getClass().getMethod(setterName, String.class);
            m.invoke(o, value);
        } catch (Exception e) {
            // ignore
        }
    }
}
