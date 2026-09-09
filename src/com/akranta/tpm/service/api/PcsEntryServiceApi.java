package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class PcsEntryServiceApi {

    private final Api api;
    private final String jwt;

    // Detail fields that are STRING type in Spring DTO (everything else we treat as number/date)
    private static final Set<String> DETAIL_STRING_FIELDS = new HashSet<>(Arrays.asList(
            "pldetailsid", "plmasterid", "cellid", "machineid",
            "productid", "rawmaterialtype", "wno", "operationno", "operationdescription", "batchno",
            "completedflag", "remarks", "modelchangepart",
            "active", "createdby"
    ));

    public PcsEntryServiceApi(String jwtToken) {

        // ✅ Abnormality style JWT debug
        CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
        CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
        CommonMessage.debugMsg("JWT(clean length)=" + (cleanJwt(jwtToken) == null ? 0 : cleanJwt(jwtToken).length()));
        CommonMessage.debugMsg("JWT contains \\n ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\n")));
        CommonMessage.debugMsg("JWT contains \\r ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\r")));
       
        this.jwt = cleanJwt(jwtToken);  
        this.api = new Api(cleanJwt(jwtToken));
    }

    private String cleanJwt(String token) {
        if (token == null) return null;

        String t = token.trim();

        // remove accidental "Bearer "
        if (t.toLowerCase().startsWith("bearer ")) {
            t = t.substring(7).trim();
        }

        // remove hidden line breaks (common 403 cause)
        t = t.replace("\r", "").replace("\n", "");

        return t;
    }

    // ==========================================================
    // ✅ EXISTING GRID API (UNCHANGED)
    // ==========================================================
    public List<String[]> getLossEntryData(GridParams gridParams, CommonFilter commonFilter, String flid) throws Exception {

        // ✅ MUST match Spring controller: /api/pcs/lossEntryGrid
        String apiUrl = "/pcs/lossEntryGrid";

        String fromDate = "01-JAN-1801";
        String toDate   = "31-DEC-2100";

        if (commonFilter != null) {
            if ("Y".equals(commonFilter.getMonwise())) {
                String from = commonFilter.getFromMonth();
                String to   = commonFilter.getToMonth();
                fromDate = "01-" + from;
                toDate   = CommonFunctions.getLastDayOfMonth("01-" + to);
            } else if (commonFilter.getFromDate() != null) {
                fromDate = commonFilter.getFromDate();
                toDate   = commonFilter.getToDate();
            }
        }

        JSONObject req = new JSONObject();
        req.put("flid", flid);
        req.put("fromDate", fromDate);
        req.put("toDate", toDate);

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PCS LOSS ENTRY GRID JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PCS LOSS ENTRY GRID statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PCS LOSS ENTRY GRID responseBody :: " + res.getBody());

        JSONArray arr = JSONArray.fromObject(res.getBody());

        // ✅ Keys MUST match Spring SQL aliases (lowercase)
        return convertJsonArrayToListByKeys(arr,
                new String[]{"flid", "plossdate", "dmt", "jh", "shiftid", "totalcnt"});
    }

//    private List<String[]> convertJsonArrayToListByKeys(JSONArray arr, String[] keys) {
//        List<String[]> list = new ArrayList<>();
//        for (int i = 0; i < arr.length(); i++) {
//            JSONObject rowObj = arr.getJSONObject(i);
//            String[] row = new String[keys.length];
//            for (int k = 0; k < keys.length; k++) {
//                Object v = rowObj.opt(keys[k]);
//                row[k] = (v == null) ? "" : v.toString();
//            }
//            list.add(row);
//        }
//        return list;
//    }
    
    private List<String[]> convertJsonArrayToListByKeys(JSONArray arr, String[] keys) {

        List<String[]> list = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {

            JSONObject rowObj = arr.getJSONObject(i);
            String[] row = new String[keys.length];

            for (int k = 0; k < keys.length; k++) {

                Object v = rowObj.opt(keys[k]);

                // 1) real JSON null / missing key
                if (v == null ) {
                    row[k] = "";
                    continue;
                }

                // 2) convert to string safely
                String s = String.valueOf(v);

                // 3) handle "null" string cases + blanks
                if (s == null) {
                    row[k] = "";
                    continue;
                }

                s = s.trim();

                // if value is literally "null" (any case), treat as empty
                if (s.length() == 0 || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) {
                    row[k] = "";
                    continue;
                }

                // 4) final cleaned value
                row[k] = s;
            }

            list.add(row);
        }

        return list;
    }


    // ==========================================================
    // ✅ NEW: CREATE LOSS CAPTURE (SPRING: POST /api/pcs/lossCapture)
    // Call from ServiceImpl:
    // return pcsEntryServiceApi.createLossCapture(newPcsTlLosscapture, pcsTlMst, pcsTlDtl, pcsTlLossreasonlink);
    // ==========================================================
    public PcsTlLosscapture createLossCapture(PcsTlLosscapture lossCapture,
                                             PcsTlMst pcsTlMst,
                                             PcsTlDtl pcsTlDtl,
                                             PcsTlLossreasonlink pcsTlLossreasonlink) throws Exception {

        // ✅ MUST match Spring controller: /api/pcs/lossCapture
        String apiUrl = "/pcs/lossCapture";

        if (lossCapture == null) throw new IllegalArgumentException("lossCapture is null");
        if (pcsTlMst == null) throw new IllegalArgumentException("pcsTlMst is null");
        if (pcsTlDtl == null) throw new IllegalArgumentException("pcsTlDtl is null");
        if (pcsTlLossreasonlink == null) throw new IllegalArgumentException("pcsTlLossreasonlink is null");

        // ✅ Abnormality style proxy debug (optional but useful)
 //       CommonMessage.debugMsg("http.proxyHost=" + System.getProperty("http.proxyHost"));
  //      CommonMessage.debugMsg("http.proxyPort=" + System.getProperty("http.proxyPort"));
  //      CommonMessage.debugMsg("https.proxyHost=" + System.getProperty("https.proxyHost"));
  //      CommonMessage.debugMsg("https.proxyPort=" + System.getProperty("https.proxyPort"));
//        CommonMessage.debugMsg("http.nonProxyHosts=" + System.getProperty("http.nonProxyHosts"));

        // ✅ Defaults like Abnormality style
        String nowIso = CommonFunctions.pg_dateTimeNow();

        // Active flags
        pcsTlMst.setPrlmActive("Y");
        pcsTlDtl.setActive("Y");
        pcsTlLossreasonlink.setPlrkActive("Y");
        lossCapture.setPlosActive("Y");

        // ---- MASTER: LocalDateTime fields expected by Spring DTO
//        pcsTlMst.setPrlmDate(toIsoDateTimeOrNow(pcsTlMst.getPrlmDate(), nowIso));
//        pcsTlMst.setPrlmEntrydate(toIsoDateTimeOrNow(pcsTlMst.getPrlmEntrydate(), nowIso));
//      
        CommonMessage.debugMsg(" 1");
        if(pcsTlMst.getPrlmDate().contains("T")) {
        	pcsTlMst.setPrlmDate(pcsTlMst.getPrlmDate());
        }else {
        	pcsTlMst.setPrlmDate(CommonFunctions.pg_getDateTimeFromTimeStamp(pcsTlMst.getPrlmDate() + ":00") );
        }
        
        pcsTlMst.setPrlmEntrydate(CommonFunctions.pg_getDateTimeFromDate(pcsTlMst.getPrlmEntrydate()));
        
        CommonMessage.debugMsg(" 2");
        // createdon only on create, modifiedon always
        if (!CommonFunctions.isValidKeyId(pcsTlMst.getPrlmKeyid())) {
            pcsTlMst.setPrlmCreatedon(nowIso);
        }
        pcsTlMst.setPrlmModifiedon(nowIso);

        // optional master date-times if present
        if (CommonFunctions.isValidKeyId(pcsTlMst.getPrlmUpdateddate())) {
            pcsTlMst.setPrlmUpdateddate(toIsoDateTime(pcsTlMst.getPrlmUpdateddate()));
        }
        CommonMessage.debugMsg(" 3");
        if (CommonFunctions.isValidKeyId(pcsTlMst.getPrlmApprovedate())) {
            pcsTlMst.setPrlmApprovedate(toIsoDateTime(pcsTlMst.getPrlmApprovedate()));
        }
        CommonMessage.debugMsg("4 ");

        // ---- DETAIL: createdon/modifiedon as ISO LocalDateTime
        if (!CommonFunctions.isValidKeyId(pcsTlDtl.getPldetailsid())) {
            pcsTlDtl.setCreatedon(nowIso);
        }
        pcsTlDtl.setModifiedon(nowIso);
        CommonMessage.debugMsg(" 5");
        // ---- LOSS REASON LINK: date + created/modified
        if (CommonFunctions.isValidKeyId(pcsTlLossreasonlink.getPlrkDate())) {
            pcsTlLossreasonlink.setPlrkDate(toIsoDateTime(pcsTlLossreasonlink.getPlrkDate()));
        }
        CommonMessage.debugMsg(" 6");
        if (!CommonFunctions.isValidKeyId(pcsTlLossreasonlink.getPlrkKeyid())) {
            pcsTlLossreasonlink.setPlrkCreatedon(nowIso);
        }
        pcsTlLossreasonlink.setPlrkModifiedon(nowIso);

        // ---- LOSS CAPTURE: plosDate/from/to + created/modified
        lossCapture.setPlosDate(toIsoDateTimeOrNow(lossCapture.getPlosDate(), nowIso));
        if (CommonFunctions.isValidKeyId(lossCapture.getPlosFromtime())) {
            lossCapture.setPlosFromtime(toIsoDateTime(lossCapture.getPlosFromtime()));
        }
        CommonMessage.debugMsg(" 7");
        if (CommonFunctions.isValidKeyId(lossCapture.getPlosTotime())) {
            lossCapture.setPlosTotime(toIsoDateTime(lossCapture.getPlosTotime()));
        }
        CommonMessage.debugMsg(" 8");
        if (!CommonFunctions.isValidKeyId(lossCapture.getPlosKeyid())) {
            lossCapture.setPlosCreatedon(nowIso);
        }
        lossCapture.setPlosModifiedon(nowIso);

        // ✅ Build wrapper JSON exactly like Spring DTO (master/detail/lossReasonLink/lossCapture)
        String jsonPayload = pcsSaveJsonManual(pcsTlMst, pcsTlDtl, pcsTlLossreasonlink, lossCapture);
        CommonMessage.debugMsg("PCS LOSS CAPTURE SAVE JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PCS LOSS CAPTURE statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PCS LOSS CAPTURE responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("PCS LOSS CAPTURE failed. HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Spring returns JSON keys like plosKeyid/plosFlid/... so parse manually
        return pcsLossCaptureFromSpringJson(res.getBody());
    }

    // ==========================================================
    // ✅ WRAPPER JSON (PcsSaveRequestDto)
    // ==========================================================
    private String pcsSaveJsonManual(PcsTlMst mst, PcsTlDtl dtl, PcsTlLossreasonlink link, PcsTlLosscapture cap) {

        String masterJson = masterJsonManual(mst);
        String detailJson = detailJsonManual(dtl);
        String linkJson   = lossReasonJsonManual(link);
        String capJson    = lossCaptureJsonManual(cap);

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"master\":").append(masterJson);
        sb.append(",\"detail\":").append(detailJson);
        sb.append(",\"lossReasonLink\":").append(linkJson);
        sb.append(",\"lossCapture\":").append(capJson);
        sb.append("}");
        return sb.toString();
    }

    // ==========================================================
    // ✅ MASTER JSON
    // (Spring DTO keys: prlmKeyid, prlmDate, prlmEntrydate, prlmCreatedon, prlmModifiedon, ...)
    // ==========================================================
    private String masterJsonManual(PcsTlMst m) {

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        // NOTE: string fields -> jsonStr ("" if null)
        // dateTime fields -> jsonDateTimeOrNull (null if invalid/blank)

        append(sb, "prlmKeyid",       jsonStr(m.getPrlmKeyid()), true);
        append(sb, "prlmDate",        jsonDateTimeOrNull(m.getPrlmDate()), false);
        append(sb, "prlmFactoryid",   jsonStr(m.getPrlmFactoryid()), false);
        append(sb, "prlmSectionid",   jsonStr(m.getPrlmSectionid()), false);
        append(sb, "prlmCellid",      jsonStr(m.getPrlmCellid()), false);
        append(sb, "prlmMachineid",   jsonStr(m.getPrlmMachineid()), false);
        append(sb, "prlmShiftid",     jsonStr(m.getPrlmShiftid()), false);

        append(sb, "prlmEntrydate",   jsonDateTimeOrNull(m.getPrlmEntrydate()), false);
        append(sb, "prlmEntryby",     jsonStr(m.getPrlmEntryby()), false);
        append(sb, "prlmUpdatedby",   jsonStr(m.getPrlmUpdatedby()), false);
        append(sb, "prlmUpdateddate", jsonDateTimeOrNull(m.getPrlmUpdateddate()), false);
        append(sb, "prlmApprovedby",  jsonStr(m.getPrlmApprovedby()), false);
        append(sb, "prlmApprovedate", jsonDateTimeOrNull(m.getPrlmApprovedate()), false);

        append(sb, "prlmApporvedflag",   jsonStr(m.getPrlmApporvedflag()), false);
        append(sb, "prlmShiftincharge",  jsonStr(m.getPrlmShiftincharge()), false);
        append(sb, "prlmSubgroupid",     jsonStr(m.getPrlmSubgroupid()), false);
        append(sb, "prlmIsnoplan",       jsonStr(m.getPrlmIsnoplan()), false);
        append(sb, "prlmLogtype",        jsonStr(m.getPrlmLogtype()), false);
        append(sb, "prlmTimeorqty",      jsonStr(m.getPrlmTimeorqty()), false);
        append(sb, "prlmCompletedflag",  jsonStr(m.getPrlmCompletedflag()), false);
        append(sb, "prlmTempfield2",     jsonStr(m.getPrlmTempfield2()), false);

        append(sb, "prlmFlid",        jsonStr(m.getPrlmFlid()), false);
        append(sb, "prlmElementid",   jsonStr(m.getPrlmElementid()), false);

        append(sb, "prlmActive",      jsonStr(m.getPrlmActive()), false);
        append(sb, "prlmCreatedby",   jsonStr(m.getPrlmCreatedby()), false);
        append(sb, "prlmCreatedon",   jsonDateTimeOrNull(m.getPrlmCreatedon()), false);
        append(sb, "prlmModifiedon",  jsonDateTimeOrNull(m.getPrlmModifiedon()), false);

        sb.append("}");
        return sb.toString();
    }

    // ==========================================================
    // ✅ DETAIL JSON
    // - excludes loss01..loss97 as top-level fields
    // - adds them inside: "losses": { "loss01": 12.5, ... }
    // ==========================================================
    private String detailJsonManual(PcsTlDtl d) {

        Object[] arr = d.getSaveArray();

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;

        for (PcsTlDtl.tableFldConstants f : PcsTlDtl.tableFldConstants.values()) {

            String enumName = f.name();

            // ✅ loss01..loss97 go inside "losses" map only
            if (enumName.startsWith("loss") && enumName.length() == 6) {
                continue;
            }

            String dtoKey = dtoKeyFromDtlEnum(enumName);

            Object raw = arr[f.ordinal()];

            String jsonValue;

            // createdon/modifiedon are LocalDateTime in Spring DTO
            if ("createdon".equals(dtoKey) || "modifiedon".equals(dtoKey)) {
                jsonValue = jsonDateTimeOrNull(raw);
            }
            // string fields
            else if (DETAIL_STRING_FIELDS.contains(dtoKey)) {
                jsonValue = jsonStr(raw);
            }
            // numeric fields
            else {
                jsonValue = jsonNumOrNull(raw);
            }

            if (!first) sb.append(",");
            sb.append("\"").append(dtoKey).append("\":").append(jsonValue);
            first = false;
        }

        // ✅ add losses map
        sb.append(",\"losses\":").append(lossesMapJson(d));

        sb.append("}");
        return sb.toString();
    }

    private String lossesMapJson(PcsTlDtl d) {

        Object[] arr = d.getSaveArray();

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;

        for (int i = 1; i <= 97; i++) {

            String lossKey = String.format("loss%02d", i);

            Object raw;
            try {
                raw = arr[PcsTlDtl.tableFldConstants.valueOf(lossKey).ordinal()];
            } catch (Exception ex) {
                continue;
            }

            String num = jsonNumOrNull(raw);

            // ✅ if null/blank, skip (prevents BigDecimal parse error on empty string)
            if ("null".equals(num)) {
                continue;
            }

            if (!first) sb.append(",");
            sb.append("\"").append(lossKey).append("\":").append(num);
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }

    // enum name -> Spring DTO field name
    private String dtoKeyFromDtlEnum(String enumName) {

        // special uppercase fields in your model enum
        if ("INSPECTEDQTY".equals(enumName)) return "inspectedqty";
        if ("QAACCEPTEDQTY".equals(enumName)) return "qaacceptedqty";

        // _ml/_sl pattern -> Ml/Sl
        if (enumName.endsWith("_ml")) return enumName.substring(0, enumName.length() - 3) + "Ml";
        if (enumName.endsWith("_sl")) return enumName.substring(0, enumName.length() - 3) + "Sl";

        // all others match Spring DTO names
        return enumName;
    }

    // ==========================================================
    // ✅ LOSS REASON LINK JSON
    // ==========================================================
    private String lossReasonJsonManual(PcsTlLossreasonlink l) {

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        append(sb, "plrkKeyid",        jsonStr(l.getPlrkKeyid()), true);
        append(sb, "plrkLossid",       jsonStr(l.getPlrkLossid()), false);
        append(sb, "plrkReasonid",     jsonStr(l.getPlrkReasonid()), false);
        append(sb, "plrkCauseid",      jsonStr(l.getPlrkCauseid()), false);
        append(sb, "plrkRootcauseid",  jsonStr(l.getPlrkRootcauseid()), false);
        append(sb, "plrkPldetailid",   jsonStr(l.getPlrkPldetailid()), false);
        append(sb, "plrkWno",          jsonStr(l.getPlrkWno()), false);

        // numeric
        append(sb, "plrkMinutes",      jsonNumOrNull(l.getPlrkMinutes()), false);
        append(sb, "plrkInstance",     jsonNumOrNull(l.getPlrkInstance()), false);

        // dateTime
        append(sb, "plrkDate",         jsonDateTimeOrNull(l.getPlrkDate()), false);

        append(sb, "plrkShiftid",      jsonStr(l.getPlrkShiftid()), false);

        // numeric
        append(sb, "plrkHourno",       jsonNumOrNull(l.getPlrkHourno()), false);

        append(sb, "plrkFactoryid",    jsonStr(l.getPlrkFactoryid()), false);
        append(sb, "plrkSectionid",    jsonStr(l.getPlrkSectionid()), false);
        append(sb, "plrkCellid",       jsonStr(l.getPlrkCellid()), false);
        append(sb, "plrkMachineid",    jsonStr(l.getPlrkMachineid()), false);
        append(sb, "plrkSubgroupid",   jsonStr(l.getPlrkSubgroupid()), false);

        append(sb, "plrkRemarks",      jsonStr(l.getPlrkRemarks()), false);
        append(sb, "plrkMsrno",        jsonStr(l.getPlrkMsrno()), false);
        append(sb, "plrkProcessid",    jsonStr(l.getPlrkProcessid()), false);

        append(sb, "plrkFlid",         jsonStr(l.getPlrkFlid()), false);
        append(sb, "plrkElementid",    jsonStr(l.getPlrkElementid()), false);

        append(sb, "plrkActive",       jsonStr(l.getPlrkActive()), false);
        append(sb, "plrkCreatedby",    jsonStr(l.getPlrkCreatedby()), false);
        append(sb, "plrkCreatedon",    jsonDateTimeOrNull(l.getPlrkCreatedon()), false);
        append(sb, "plrkModifiedon",   jsonDateTimeOrNull(l.getPlrkModifiedon()), false);

        sb.append("}");
        return sb.toString();
    }

    // ==========================================================
    // ✅ LOSS CAPTURE JSON
    // ==========================================================
    private String lossCaptureJsonManual(PcsTlLosscapture c) {

        StringBuilder sb = new StringBuilder();
        sb.append("{");

        append(sb, "plosKeyid",            jsonStr(c.getPlosKeyid()), true);
        append(sb, "plosFlid",             jsonStr(c.getPlosFlid()), false);
        append(sb, "plosDate",             jsonDateTimeOrNull(c.getPlosDate()), false);
        append(sb, "plosShiftid",          jsonStr(c.getPlosShiftid()), false);

        append(sb, "plosFromtime",         jsonDateTimeOrNull(c.getPlosFromtime()), false);
        append(sb, "plosTotime",           jsonDateTimeOrNull(c.getPlosTotime()), false);

        append(sb, "plosLosstime",         jsonStr(c.getPlosLosstime()), false);
        append(sb, "plosLossreason",       jsonStr(c.getPlosLossreason()), false);
        append(sb, "plosLossid",           jsonStr(c.getPlosLossid()), false);
        append(sb, "plosTradeid",          jsonStr(c.getPlosTradeid()), false);

        append(sb, "plosProdImpact",       jsonStr(c.getPlosProdImpact()), false);
        append(sb, "plosProdImpQty",       jsonNumOrNull(c.getPlosProdImpQty()), false);

        append(sb, "plosLossdescription",  jsonStr(c.getPLosLossdescription()), false);
        append(sb, "plosPldetailsid",      jsonStr(c.getPlospldetailsid()), false);

        append(sb, "plosEquipment",        jsonStr(c.getPlosEquipment()), false);
        append(sb, "plosDetectedby",       jsonStr(c.getPlosTempfield3()), false);

        append(sb, "plosTempfield4",       jsonStr(c.getPlosTempfield4()), false);
        append(sb, "plosTempfield5",       jsonStr(c.getPlosTempfield5()), false);

        append(sb, "plosActive",           jsonStr(c.getPlosActive()), false);
        append(sb, "plosCreatedby",        jsonStr(c.getPlosCreatedby()), false);
        append(sb, "plosCreatedon",        jsonDateTimeOrNull(c.getPlosCreatedon()), false);
        append(sb, "plosModifiedon",       jsonDateTimeOrNull(c.getPlosModifiedon()), false);

        sb.append("}");
        return sb.toString();
    }

    // ==========================================================
    // ✅ SPRING RESPONSE -> Eclipse Model
    // Spring returns keys like: plosKeyid, plosFlid, ...
    // ==========================================================
    private PcsTlLosscapture pcsLossCaptureFromSpringJson(String json) {

        JSONObject o = JSONObject.fromObject(json);

        PcsTlLosscapture m = new PcsTlLosscapture();

        m.setPlosKeyid(o.optString("plosKeyid", ""));
        m.setPlosFlid(o.optString("plosFlid", ""));
        m.setPlosDate(o.optString("plosDate", ""));
        m.setPlosShiftid(o.optString("plosShiftid", ""));

        m.setPlosFromtime(o.optString("plosFromtime", ""));
        m.setPlosTotime(o.optString("plosTotime", ""));

        m.setPlosLosstime(o.optString("plosLosstime", ""));
        m.setPlosLossreason(o.optString("plosLossreason", ""));
        m.setPlosLossid(o.optString("plosLossid", ""));
        m.setPlosTradeid(o.optString("plosTradeid", ""));

        m.setPlosProdImpact(o.optString("plosProdImpact", ""));
        m.setPlosProdImpQty(o.optString("plosProdImpQty", ""));

        m.setPlosLossdescription(o.optString("plosLossdescription", ""));
        m.setPlosPldetailsid(o.optString("plosPldetailsid", ""));

        m.setPlosEquipment(o.optString("plosEquipment", ""));
        m.setPlosTempfield3(o.optString("plosDetectedby", ""));

        m.setPlosTempfield4(o.optString("plosTempfield4", ""));
        m.setPlosTempfield5(o.optString("plosTempfield5", ""));

        m.setPlosActive(o.optString("plosActive", ""));
        m.setPlosCreatedby(o.optString("plosCreatedby", ""));
        m.setPlosCreatedon(o.optString("plosCreatedon", ""));
        m.setPlosModifiedon(o.optString("plosModifiedon", ""));

        return m;
    }

    // ==========================================================
    // ✅ SMALL JSON HELPERS (Abnormality style)
    // ==========================================================
    private void append(StringBuilder sb, String key, String val, boolean first) {
        if (!first) sb.append(",");
        sb.append("\"").append(key).append("\":").append(val);
    }

    private String jsonStr(Object v) {
        if (v == null) return "\"\"";
        String s = String.valueOf(v);
        if (!CommonFunctions.isValidKeyId(s)) return "\"\"";
        return "\"" + escapeJson(s) + "\"";
    }

    private String jsonDateTimeOrNull(Object v) {
        if (v == null) return "null";
        String s = String.valueOf(v);
        if (!CommonFunctions.isValidKeyId(s)) return "null";
        return "\"" + escapeJson(s) + "\"";
    }

    private String jsonNumOrNull(Object v) {
        if (v == null) return "null";
        String s = String.valueOf(v);
        if (!CommonFunctions.isValidKeyId(s)) return "null";

        String t = s.trim();

        // allow integer/decimal
        if (t.matches("[-+]?\\d+(\\.\\d+)?")) {
            return t; // number token (no quotes)
        }

        return "null";
    }

    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n")
                .replace("\t", "\\t");
    }

    // ==========================================================
    // ✅ DATE/TIMESTAMP -> ISO LocalDateTime string for Spring
    // Uses ONLY CommonFunctions conversion methods
    // ==========================================================
    private String toIsoDateTimeOrNow(String input, String nowIso) {
        String out = toIsoDateTime(input);
        return (out == null) ? nowIso : out;
    }

    private String toIsoDateTime(String input) {

        if (!CommonFunctions.isValidKeyId(input)) return null;

        String s = input.trim();
        // ✅ FIX 1: Normalize single-digit day  (e.g., "1-Mar-2026" → "01-Mar-2026")
        s = s.replaceAll("^(\\d)(-.+)", "0$1$2");

        // ✅ FIX 2: Normalize single-digit hour (e.g., "9:58" → "09:58")
        s = s.replaceAll("(\\s)(\\d)(:\\d{2})", "$10$2$3");
        // already ISO: yyyy-MM-dd'T'HH:mm:ss (or with milliseconds)
        if (s.contains("T")) {
            int dot = s.indexOf('.');
            if (dot > 0) s = s.substring(0, dot);
            if (s.length() == 10) s = s + "T00:00:00";
            return s;
        }

        // yyyy-MM-dd
        if (s.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return s + "T00:00:00";
        }

        // yyyy-MM-dd HH:mm or yyyy-MM-dd HH:mm:ss  -> make ISO (no CommonFunctions needed)
        if (s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}(:\\d{2})?")) {
            if (s.matches(".*\\d{2}:\\d{2}$")) {
                s = s + ":00";
            }
            return s.replace(" ", "T");
        }

        // dd-MMM-yyyy HH:mm -> add seconds (so CommonFunctions.pg_getDateTimeFromTimeStamp can parse)
        if (s.matches(".*\\d{2}:\\d{2}$")) {
            s = s + ":00";
        }

        // dd-MMM-yyyy HH:mm:ss
        if (s.contains(":")) {
            return CommonFunctions.pg_getDateTimeFromTimeStamp(s);
        }

        // dd-MMM-yyyy
        return CommonFunctions.pg_getDateTimeFromDate(s);
    }
    
    
 // ==========================================================
 // ✅ NEW GRID API: PCS LOSS CAPTURE GRID
 // Spring: POST /api/pcs/pcsLossCaptureGrid
 // Spring returns: List<Map<String,Object>>  (JSON array of objects)
 // Keys returned by query are: text, text-2, ... text-26, dataorder
 // ==========================================================
 public List<String[]> getPcsLossCaptureGrid(String flid, String fromDate, String toDate, String shiftId) throws Exception {

     // ✅ MUST match Spring controller mapping
     // (change only if your controller URL is different)
     String apiUrl = "/pcs/pcsLossCaptureGrid";
     

     // ✅ build request JSON (same style as getLossEntryData)
     JSONObject req = new JSONObject();
     req.put("flid", flid);
     req.put("fromDate", fromDate);
     req.put("toDate", toDate);
     req.put("shiftId", (shiftId == null) ? "" : shiftId);

     String jsonPayload = req.toString();
     CommonMessage.debugMsg("PCS LOSS CAPTURE GRID JSON :: " + jsonPayload);



     // ✅ call SpringBoot
     HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
 
     CommonMessage.debugMsg("PCS LOSS CAPTURE GRID statusCode :: " + res.getStatusCode());
     CommonMessage.debugMsg("PCS LOSS CAPTURE GRID responseBody :: " + res.getBody());
   
  
     int code = res.getStatusCode();
     if (code < 200 || code >= 300) {
         String body = res.getBody();
         if (body == null) body = "";
         throw new IllegalStateException("PCS LOSS CAPTURE GRID failed. HTTP=" + code + " body=[" + body + "]");
     }

     JSONArray arr = JSONArray.fromObject(res.getBody());

     // ✅ IMPORTANT:
     // Your native SQL is returning generic aliases:
     // text, text-2, ..., text-26, dataorder
     // So we must read in that exact order.
//     String[] keys = new String[] {
//             "text", "text-2", "text-3", "text-4", "text-5", "text-6", "text-7", "text-8", "text-9",
//             "text-10", "text-11", "text-12", "text-13", "text-14", "text-15", "text-16", "text-17",
//             "text-18", "text-19", "text-20", "text-21", "text-22", "text-23", "text-24", "text-25",
//             "text-26", "dataorder"
//     };
    
     String[] keys = new String[] {
    		    "pldetailsid",     // 1
    		    "keyid",           // 2
    		    "fromdate",        // 3
    		    "fromtime",        // 4
    		    "todate",          // 5
    		    "totime",          // 6
    		    "hours",           // 7
    		    "lossid",          // 8
    		    "lossrefno",       // 9
    		    "lossreasonid",    // 10
    		    "lossreason",      // 11
    		    "lossdescription", // 12
    		    "tradeid",         // 13
    		    "trade",           // 14
    		    "prodnimpact",     // 15
    		    "prodimpqty",      // 16
    		    "equipmentname",   // 17
    		    "detectedby",      // 18
    		    "whywhy",          // 19
    		    "whywhyrefno",     // 20
    		    "kaizenideas",     // 21
    		    "kaizenrefno",     // 22
    		    "actionplan",      // 23
    		    "actionplanno",    // 24
    		    "equipmentid",     // 25
    		    "employeeid",      // 26
    		    "dataorder"        // 27
    		};

     return convertJsonArrayToListByKeys(arr, keys);
 }
 
//==========================================================
//✅ DELETE LOSS ENTRY (SPRING: POST /api/pcs/pcsLossEntry/delete)
//Call from ServiceImpl:
//return pcsEntryServiceApi.deletePcsLossEntryITCNew(plrkKeyid, pldetailsid, sectId);
//==========================================================
public PcsTlMst deletePcsLossEntryITCNew(String plrkKeyid, String pldetailsid, String sectId) throws Exception {

  // ✅ MUST match Spring controller mapping
  String apiUrl = "/pcs/pcsLossEntry/delete";

  // basic validations (same style as your other methods)
  if (!CommonFunctions.isValidKeyId(plrkKeyid)) {
      throw new IllegalArgumentException("plrkKeyid is required");
  }
  if (!CommonFunctions.isValidKeyId(pldetailsid)) {
      throw new IllegalArgumentException("pldetailsid is required");
  }
  if (!CommonFunctions.isValidKeyId(sectId)) {
      throw new IllegalArgumentException("sectId is required");
  }

  // ✅ build request JSON exactly like DeleteLossEntryRequestDto
  JSONObject req = new JSONObject();
  req.put("plrkKeyid", plrkKeyid);
  req.put("pldetailsid", pldetailsid);
  req.put("sectId", sectId);

  String jsonPayload = req.toString();
  CommonMessage.debugMsg("PCS LOSS DELETE JSON :: " + jsonPayload);

  // ✅ call SpringBoot
  HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

  CommonMessage.debugMsg("PCS LOSS DELETE statusCode :: " + res.getStatusCode());
  CommonMessage.debugMsg("PCS LOSS DELETE responseBody :: " + res.getBody());

  int code = res.getStatusCode();
  if (code < 200 || code >= 300) {
      String body = res.getBody();
      if (body == null) body = "";
      throw new IllegalStateException("PCS LOSS DELETE failed. HTTP=" + code + " body=[" + body + "]");
  }

  // ✅ parse response JSON: { "msg": "...", "keyId": "PRL..." }
  String masterKeyId = "";
  if (CommonFunctions.isValidKeyId(res.getBody())) {
      JSONObject o = JSONObject.fromObject(res.getBody());
      masterKeyId = o.optString("keyId", "");
      // if Spring returns null -> sanitize
      if ("null".equalsIgnoreCase(masterKeyId)) masterKeyId = "";
  }

  // ✅ return model object (servlet expects existPcsTlMst.getPrlmKeyid())
  PcsTlMst pcsTlMst = new PcsTlMst();
  pcsTlMst.setPrlmKeyid(masterKeyId);

  return pcsTlMst;
}


//==========================================================
//✅ UPLOAD EXCEL (Spring: POST /api/pcs/otherLoss/upload)
//multipart/form-data: file, flid, elementId, lossId, createdBy
//returns: {"msg":"Data Uploaded successfully","rowsInserted":6}
//==========================================================
public String populateTempTable  (String excelFileName, PcsTlOtherlossentry pcsTlOtherlossentry) throws Exception {

 String apiUrl = "/pcs/otherLoss/upload";

 if (!CommonFunctions.isValidKeyId(excelFileName)) {
     throw new IllegalArgumentException("excelFileName is empty");
 }
 if (pcsTlOtherlossentry == null) {
     throw new IllegalArgumentException("pcsTlOtherlossentry is null");
 }

 java.io.File file = new java.io.File(excelFileName);
 if (!file.exists() || !file.isFile()) {
     throw new IllegalArgumentException("Excel file not found: " + excelFileName);
 }

 String flid      = safeStrOrEmpty(pcsTlOtherlossentry.getOlseFlid());
 String elementId = safeStrOrEmpty(pcsTlOtherlossentry.getOlseElementid());
 String lossId    = safeStrOrEmpty(pcsTlOtherlossentry.getOlseLossid());
 String createdBy = safeStrOrEmpty(pcsTlOtherlossentry.getOlseCreatedby());

 if (!CommonFunctions.isValidKeyId(flid))      throw new IllegalArgumentException("flid is required");
 if (!CommonFunctions.isValidKeyId(elementId)) throw new IllegalArgumentException("elementId is required");
 if (!CommonFunctions.isValidKeyId(lossId))    throw new IllegalArgumentException("lossId is required");
 if (!CommonFunctions.isValidKeyId(createdBy)) throw new IllegalArgumentException("createdBy is required");

 HttpResponse res = makeAuthMultipartRequest(apiUrl, file, flid, elementId, lossId, createdBy);

 CommonMessage.debugMsg("OTHERLOSS UPLOAD statusCode :: " + res.getStatusCode());
 CommonMessage.debugMsg("OTHERLOSS UPLOAD responseBody :: " + res.getBody());

 int code = res.getStatusCode();
 String body = res.getBody();
 if (body == null) body = "";

 if (code < 200 || code >= 300) {
     throw new IllegalStateException("OTHERLOSS UPLOAD failed. HTTP=" + code + " body=[" + body + "]");
 }

 // Optional: treat rowsInserted==0 as "No"
 try {
     JSONObject resp = JSONObject.fromObject(body);
     Object rows = resp.get("rowsInserted");
     if (rows != null) {
         int n = Integer.parseInt(String.valueOf(rows).trim());
         if (n <= 0) return "No";
     }
 } catch (Exception ignore) {}

 return excelFileName; // same as your old DAO behaviour
}
private HttpResponse makeAuthMultipartRequest(
        String apiUrl,
        java.io.File file,
        String flid,
        String elementId,
        String lossId,
        String createdBy
) throws java.io.IOException {

    HttpURLConnection connection = null;

    String boundary = "----PerfexBoundary" + System.currentTimeMillis();
    String CRLF = "\r\n";

    try {
        // ✅ Use same BASE_URL as Api.makeAuthRequest (reflection fallback)
        String baseUrl = getApiBaseUrlSafe();
        java.net.URL url = java.net.URI.create(baseUrl + apiUrl).toURL();

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setUseCaches(false);

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

        if (jwt != null && !jwt.isEmpty()) {
            connection.setRequestProperty("Authorization", "Bearer " + jwt);

            CommonMessage.debugMsg("FULL REQUEST URL :: " + url);
            CommonMessage.debugMsg("AUTH HEADER :: Bearer " + (jwt == null ? "null" : (jwt.length() + " chars")));
        }

        try (java.io.OutputStream os = connection.getOutputStream();
             java.io.DataOutputStream dos = new java.io.DataOutputStream(os)) {

            // text parts
            writeTextPart(dos, boundary, "flid", flid);
            writeTextPart(dos, boundary, "elementId", elementId);
            writeTextPart(dos, boundary, "lossId", lossId);
            writeTextPart(dos, boundary, "createdBy", createdBy);

            // file part
            dos.writeBytes("--" + boundary + CRLF);
            dos.writeBytes("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"" + CRLF);
            dos.writeBytes("Content-Type: application/octet-stream" + CRLF);
            dos.writeBytes(CRLF);

            try (java.io.InputStream fis = new java.io.BufferedInputStream(new java.io.FileInputStream(file))) {
                byte[] buf = new byte[8192];
                int len;
                while ((len = fis.read(buf)) != -1) {
                    dos.write(buf, 0, len);
                }
            }

            dos.writeBytes(CRLF);
            dos.writeBytes("--" + boundary + "--" + CRLF);
            dos.flush();
        }

        int code = connection.getResponseCode();
        String responseBody;

        if (code >= 200 && code < 300) {
            responseBody = readResponse(connection.getInputStream());
        } else {
            responseBody = readResponse(connection.getErrorStream());
            CommonMessage.debugMsg(String.format("Error Response (%d): %s", code, responseBody));
        }

        return new HttpResponse(code, responseBody);

    } finally {
        if (connection != null) connection.disconnect();
    }
}

private void writeTextPart(java.io.DataOutputStream dos, String boundary, String name, String value) throws java.io.IOException {
    String CRLF = "\r\n";
    if (value == null) value = "";
    dos.writeBytes("--" + boundary + CRLF);
    dos.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"" + CRLF);
    dos.writeBytes("Content-Type: text/plain; charset=UTF-8" + CRLF);
    dos.writeBytes(CRLF);
    dos.write(value.getBytes(java.nio.charset.StandardCharsets.UTF_8));
    dos.writeBytes(CRLF);
}
private String readResponse(java.io.InputStream inputStream) throws java.io.IOException {
    if (inputStream == null) return "";
    try (java.io.BufferedReader br = new java.io.BufferedReader(
            new java.io.InputStreamReader(inputStream, java.nio.charset.StandardCharsets.UTF_8))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        return sb.toString();
    }
}

private String safeStrOrEmpty(String s) {
    if (s == null) return "";
    String t = s.trim();
    if (t.length() == 0) return "";
    if ("null".equalsIgnoreCase(t) || "undefined".equalsIgnoreCase(t) || "{}".equals(t)) return "";
    return t;
}

private String getAny(Object obj, String... getterNames) {
    if (obj == null || getterNames == null) return null;
    for (String g : getterNames) {
        try {
            java.lang.reflect.Method m = obj.getClass().getMethod(g);
            Object v = m.invoke(obj);
            if (v != null) return String.valueOf(v);
        } catch (Exception ignore) {}
    }
    return null;
}

private String getApiBaseUrlSafe() {
    // Try to read Api.BASE_URL via reflection so you don't hardcode.
//    try {
//        java.lang.reflect.Field f = Api.class.getDeclaredField("BASE_URL");
//        f.setAccessible(true);
//        Object v = f.get(null);
//        if (v != null) {
//            String s = String.valueOf(v).trim();
//            if (s.endsWith("/")) s = s.substring(0, s.length() - 1);
//            return s;
//        }
//    } catch (Exception ignore) {}

    // fallback (matches your logs)
   	String BASE_URL = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "BASE_URL");
    return BASE_URL;
}

// ==========================================================
// ✅ UPDATE LOSS VALUES (Spring: PUT /api/pcs/otherLoss/lossValues)
// Request: [ { "olseKeyid":"OLS0003757", "olseLossvalue":99 }, ... ]
// ==========================================================
public String updateLossVal(List<PcsTlOtherlossentry> otherLossList) throws Exception {

    String apiUrl = "/pcs/otherLoss/lossValues";

    if (otherLossList == null || otherLossList.isEmpty()) {
        throw new IllegalArgumentException("otherLossList is empty");
    }

    // ✅ Build plain Java list (same pattern you used for factoryIds)
    List<Map<String, Object>> payloadList = new ArrayList<>();

    for (PcsTlOtherlossentry row : otherLossList) {
        if (row == null) continue;

     // keyId: try possible getters one by one
        String keyId = safeStrOrEmpty(row.getOlseKeyid());
        if (!CommonFunctions.isValidKeyId(keyId)) {
            keyId = safeStrOrEmpty(row.getOlseKeyid());
        }

        // loss value: try possible getters one by one
        String lossValStr = safeStrOrEmpty(row.getOlseLossvalue());
        if (lossValStr == null || lossValStr.trim().isEmpty()) {
            lossValStr = safeStrOrEmpty(row.getOlseLossvalue());
        }


        // ✅ validate keyId properly
        if (!CommonFunctions.isValidKeyId(keyId)) {
            continue;
        }

        // ✅ DO NOT use isValidKeyId() for numeric loss value
        if (lossValStr == null || lossValStr.trim().isEmpty()) {
            continue;
        }

     // numeric parse (Spring expects number)
        Object lossValNum;
        String n = lossValStr.trim();

        try {
            // keep exact string, but send as Long if integer, else Double
            if (n.indexOf('.') >= 0) {
                lossValNum = Double.valueOf(n);
            } else {
                lossValNum = Long.valueOf(n);
            }
        } catch (Exception e) {
            continue;
        }

        Map<String, Object> obj = new LinkedHashMap<>();
        obj.put("olseKeyid", keyId.trim());
        obj.put("olseLossvalue", lossValNum);   // ✅ IMPORTANT: not BigDecimal
        payloadList.add(obj);


        payloadList.add(obj);
    }

    if (payloadList.isEmpty()) {
        throw new IllegalArgumentException("No valid update rows found");
    }

    // ✅ Convert Java List -> JSON array using json-lib (no element/add needed)
    String jsonPayload = net.sf.json.JSONArray.fromObject(payloadList).toString();
    CommonMessage.debugMsg("OTHERLOSS UPDATE JSON :: " + jsonPayload);

    HttpResponse res = api.makeAuthRequest(apiUrl, "PUT", jsonPayload);

    CommonMessage.debugMsg("OTHERLOSS UPDATE statusCode :: " + res.getStatusCode());
    CommonMessage.debugMsg("OTHERLOSS UPDATE responseBody :: " + res.getBody());

    int code = res.getStatusCode();
    String body = res.getBody();
    if (body == null) body = "";

    if (code < 200 || code >= 300) {
        throw new IllegalStateException("OTHERLOSS UPDATE failed. HTTP=" + code + " body=[" + body + "]");
    }

    return "Data Updated successfully";
}



}
