package com.akranta.tpm.service.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.PcsTlLossphenfactorylink;
import com.akranta.tpm.model.PcsTlLossphenomenamst;
import com.akranta.tpm.model.PcsTlOtherlossentry;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class PcsEnableDisableServiceApi {

    private final Api api;

    public PcsEnableDisableServiceApi(String jwtToken) {
        CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
        CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
        CommonMessage.debugMsg("JWT(clean length)=" + (cleanJwt(jwtToken) == null ? 0 : cleanJwt(jwtToken).length()));
        CommonMessage.debugMsg("JWT contains \\n ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\n")));
        CommonMessage.debugMsg("JWT contains \\r ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\r")));
        this.api = new Api(cleanJwt(jwtToken));
    }

    private String cleanJwt(String token) {
        if (token == null) return null;
        String t = token.trim();
        if (t.toLowerCase().startsWith("bearer ")) {
            t = t.substring(7).trim();
        }
        return t.replace("\r", "").replace("\n", "");
    }

    // ==========================================================
    // ✅ GRID: Phenomena Loss (Spring: POST /api/pcs/phenomenaLoss/grid)
    // Response: { rows:[{slno, phenid, phenname},...], totalRecords, fromRow, toRow }
    // ==========================================================
    public List<String[]> getPhenomena(GridParams gridParams, String phenId, String lossId) throws Exception {

        String apiUrl = "/pcs/phenomenaLoss/grid";

        JSONObject req = new JSONObject();
        req.put("phenId", safeStrOrNull(phenId));
        req.put("lossId", safeStrOrNull(lossId));

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PHENOMENA GRID JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PHENOMENA GRID statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PHENOMENA GRID responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("PHENOMENA GRID failed. HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject resp = JSONObject.fromObject(res.getBody());

        JSONArray rowsArr = new JSONArray();

        // ✅ net.sf.json uses has("key") (NOT containsKey)
        if (resp != null && resp.has("rows")) {

            Object rowsObj = resp.get("rows");

            // rows could be JSONArray already, or could be List/other object
            if (rowsObj instanceof JSONArray) {
                rowsArr = (JSONArray) rowsObj;
            } else if (rowsObj != null) {
                // safest conversion
                rowsArr = JSONArray.fromObject(rowsObj);
            }
        }

        // Your Spring SQL returns: slno, phenid, phenname
        String[] keys = new String[] { "slno", "phenid", "phenname" };

        return convertJsonArrayToListByKeys(rowsArr, keys);
    }

    private String safeStr(String s) {
        if (!CommonFunctions.isValidKeyId(s)) return "";
        String t = s.trim();
        if ("null".equalsIgnoreCase(t) || "undefined".equalsIgnoreCase(t)) return "";
        return t;
    }
    
    public PcsTlLossphenomenamst savePhenomenaLossFactoryLink(
            PcsTlLossphenomenamst mst,
            boolean replaceFactoryLinks   // ✅ true = replace links (delete+insert); false = don't touch links
    ) throws Exception {

        if (mst == null) throw new IllegalArgumentException("PcsTlLossphenomenamst is null");

        String apiUrl = "/pcs/phenomenaLoss/save"; // BASE_URL already has /api
        String nowIso = CommonFunctions.pg_dateTimeNow(); // "yyyy-MM-dd'T'HH:mm:ss"

        // defaults like your Spring service
        if (!CommonFunctions.isValidKeyId(mst.getPlpmActive())) mst.setPlpmActive("Y");
        if (!CommonFunctions.isValidKeyId(mst.getPlpmTempfield1())) mst.setPlpmTempfield1("-");
        if (!CommonFunctions.isValidKeyId(mst.getPlpmTempfield2())) mst.setPlpmTempfield2("-");
        if (!CommonFunctions.isValidKeyId(mst.getPlpmTempfield3())) mst.setPlpmTempfield3("-");

        // created/modified timestamps (Spring expects LocalDateTime)
        if (!CommonFunctions.isValidKeyId(mst.getPlpmCreatedon())) {
            mst.setPlpmCreatedon(nowIso);
        } else {
            mst.setPlpmCreatedon(mst.getPlpmCreatedon());
        }
        mst.setPlpmModifiedon(mst.getPlpmModifiedon());

        JSONObject req = new JSONObject();
        req.put("plpmKeyid", safeStrOrNull(mst.getPlpmKeyid()));
        req.put("plpmName", safeStr(mst.getPlpmName()));
        req.put("plpmMainloss", safeStr(mst.getPlpmMainloss()));
        req.put("plpmTempfield1", safeStr(mst.getPlpmTempfield1()));
        req.put("plpmTempfield2", safeStr(mst.getPlpmTempfield2()));
        req.put("plpmTempfield3", safeStr(mst.getPlpmTempfield3()));
        req.put("plpmActive", safeStr(mst.getPlpmActive()));
        req.put("plpmCreatedby", safeStr(mst.getPlpmCreatedby()));

        // Spring DTO has LocalDateTime -> send ISO string
        req.put("plpmCreatedon", safeStrOrNull(mst.getPlpmCreatedon()));
        req.put("plpmModifiedon", safeStrOrNull(mst.getPlpmModifiedon()));

        // ✅ NEW: factoryIds (only when you want to replace)
        if (replaceFactoryLinks) {
            JSONArray factoryArr = new JSONArray();
            if (mst.getpcsTlLossphenfactorylink() != null) {
                for (PcsTlLossphenfactorylink l : mst.getpcsTlLossphenfactorylink()) {
                    if (l == null) continue;

                    // if you are using this flag anywhere
                    if ("Y".equalsIgnoreCase(l.getIsDelete())) {
                        continue; // skip deleted ones
                    }

                    String fid = l.getPpflFactoryid();
                    if (CommonFunctions.isValidKeyId(fid)) {
                 //       factoryArr.element(fid.trim());
                    }
                }
            }
            // IMPORTANT:
            // - empty array => clears all existing links in Spring (because Spring deletes then inserts none)
            // - non-empty => replaces set
         
        
            List<String> factoryIds = new ArrayList<>();

            if (mst.getpcsTlLossphenfactorylink() != null) {
                for (PcsTlLossphenfactorylink l : mst.getpcsTlLossphenfactorylink()) {
                    if (l == null) continue;

                    String fid = l.getPpflFactoryid();
                    if (CommonFunctions.isValidKeyId(fid)) {
                        factoryIds.add(fid.trim());   // ✅ normal Java List
                    }
                }
            }
            req.put("factoryIds", factoryIds);   // List<String> -> becomes JSON array

         //   req.put("factoryIds", factoryArr);
        }
        
        // else: do NOT send factoryIds -> Spring will NOT touch existing links

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PHENOMENA SAVE JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PHENOMENA SAVE statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PHENOMENA SAVE responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("PHENOMENA SAVE failed. HTTP=" + code + " body=[" + body + "]");
        }

        // Spring returns: { "msg": "...", "mstKeyid": "PLPM...." }
        JSONObject resp = JSONObject.fromObject(res.getBody());
        if (resp != null && resp.has("mstKeyid") && resp.get("mstKeyid") != null) {
            mst.setPlpmKeyid(String.valueOf(resp.get("mstKeyid")));
        }

        return mst;
    }

    
    // ==========================================================
    // ✅ SAVE: Phenomena Loss (Spring: POST /api/pcs/phenomenaLoss/save)
    // Request DTO: PhenomenaLossSaveRequestDto
    // Response: { msg:"Saved successfully", mstKeyid:"PLPM..." }
    // ==========================================================
    public PcsTlLossphenomenamst create(PcsTlLossphenomenamst m) throws Exception {

        String apiUrl = "/pcs/phenomenaLoss/save";

        if (m == null) throw new IllegalArgumentException("PcsTlLossphenomenamst is null");

        // ✅ Build JSON exactly matching Spring DTO field names
        JSONObject req = new JSONObject();
        req.put("plpmKeyid", safeStrOrNull(m.getPlpmKeyid()));      // null -> Spring generates key
        req.put("plpmName", safeStrOrEmpty(m.getPlpmName()));
        req.put("plpmMainloss", safeStrOrEmpty(m.getPlpmMainloss()));

        // defaults (Spring also sets, but we keep consistent)
        req.put("plpmTempfield1", safeStrOrDefault(m.getPlpmTempfield1(), "-"));
        req.put("plpmTempfield2", safeStrOrDefault(m.getPlpmTempfield2(), "-"));
        req.put("plpmTempfield3", safeStrOrDefault(m.getPlpmTempfield3(), "-"));
        req.put("plpmActive", safeStrOrDefault(m.getPlpmActive(), "Y"));
        req.put("plpmCreatedby", safeStrOrEmpty(m.getPlpmCreatedby()));

        // ✅ OPTIONAL: You can omit createdon/modifiedon and let Spring fill LocalDateTime.
        // If you want to send, send ISO string (CommonFunctions.pg_dateTimeNow()).
        // req.put("plpmCreatedon", CommonFunctions.pg_dateTimeNow());
        // req.put("plpmModifiedon", CommonFunctions.pg_dateTimeNow());

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PHENOMENA SAVE JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PHENOMENA SAVE statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PHENOMENA SAVE responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("PHENOMENA SAVE failed. HTTP=" + code + " body=[" + body + "]");
        }

        // Parse response: { msg, mstKeyid }
        JSONObject o = JSONObject.fromObject(res.getBody());
        String mstKeyid = o.optString("mstKeyid", "");
        if ("null".equalsIgnoreCase(mstKeyid)) mstKeyid = "";

        // return same model with key set (like your other APIs)
        m.setPlpmKeyid(mstKeyid);
        return m;
    }
 // ==========================================================
 // ✅ GRID: Factory selection for Phenomena (Spring: POST /api/pcs/phenomenaLoss/factoryGrid)
 // Request: { lossId, phenId, fromRow, toRow }
 // Response: { rows:[{slno, selected, cell_keyid, cell_code, cell_name},...], totalRecords, fromRow, toRow }
 // ==========================================================
 // --- Reflection int getter (safe even if method not present) ---
    private Integer getIntByReflection(Object obj, String... methodNames) {
        if (obj == null || methodNames == null) return null;
        for (String m : methodNames) {
            try {
                java.lang.reflect.Method mm = obj.getClass().getMethod(m);
                Object val = mm.invoke(obj);
                if (val == null) continue;
                if (val instanceof Number) return ((Number) val).intValue();
                return Integer.parseInt(String.valueOf(val));
            } catch (Exception ignore) {}
        }
        return null;
    }

    // --- Reflection long setter (safe even if setter not present) ---
    private void setLongByReflection(Object obj, long value, String... setterNames) {
        if (obj == null || setterNames == null) return;
        for (String s : setterNames) {
            try {
                java.lang.reflect.Method mm = obj.getClass().getMethod(s, long.class);
                mm.invoke(obj, value);
                return;
            } catch (Exception ignore) {}
            try {
                java.lang.reflect.Method mm = obj.getClass().getMethod(s, Long.class);
                mm.invoke(obj, value);
                return;
            } catch (Exception ignore) {}
            try {
                java.lang.reflect.Method mm = obj.getClass().getMethod(s, int.class);
                mm.invoke(obj, (int) value);
                return;
            } catch (Exception ignore) {}
            try {
                java.lang.reflect.Method mm = obj.getClass().getMethod(s, Integer.class);
                mm.invoke(obj, (int) value);
                return;
            } catch (Exception ignore) {}
        }
    }

    // --- net.sf.json conversion (NO opt(), NO length()) ---
    private List<String[]> convertJsonArrayToListByKeys_netSf(JSONArray arr, String[] keys) {
        List<String[]> list = new ArrayList<>();
        if (arr == null || keys == null) return list;

        for (int i = 0; i < arr.length(); i++) {
            Object rowObjAny = arr.get(i);
            JSONObject rowObj = (rowObjAny instanceof JSONObject)
                    ? (JSONObject) rowObjAny
                    : JSONObject.fromObject(rowObjAny);

            String[] row = new String[keys.length];
            for (int k = 0; k < keys.length; k++) {
                Object v = null;
                try { v = rowObj.get(keys[k]); } catch (Exception ignore) {}
                String s = (v == null) ? "" : String.valueOf(v).trim();
                if (s.length() == 0 || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) s = "";
                row[k] = s;
            }
            list.add(row);
        }

        // optional debug
        for (String[] r : list) {
            CommonMessage.debugMsg(Arrays.toString(r));
        }
        return list;
    }

 public List<String[]> getFactory(GridParams gridParams, String lossId, String phenID) throws Exception {

     String apiUrl = "/pcs/phenomenaLoss/factoryGrid";

     JSONObject req = new JSONObject();
     req.put("lossId", safeStrOrNull(lossId));
     req.put("phenId", safeStrOrNull(phenID));

     // ✅ Pagination: try to read from GridParams (reflection-safe)
     Integer fromRow = getIntByReflection(gridParams,
             "getFromRow", "getFromrow", "getStartRow", "getStartrow", "getStartRowNo", "getStartRowNum");
     Integer toRow = getIntByReflection(gridParams,
             "getToRow", "getTorow", "getEndRow", "getEndrow", "getEndRowNo", "getEndRowNum");

     // Fallback defaults (matches your old behavior 1..100)
     if (fromRow == null) fromRow = 1;
     if (toRow == null) toRow = 100;

     req.put("fromRow", fromRow);
     req.put("toRow", toRow);

     String jsonPayload = req.toString();
     CommonMessage.debugMsg("FACTORY GRID JSON :: " + jsonPayload);

     HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

     CommonMessage.debugMsg("FACTORY GRID statusCode :: " + res.getStatusCode());
     CommonMessage.debugMsg("FACTORY GRID responseBody :: " + res.getBody());

     int code = res.getStatusCode();
     if (code < 200 || code >= 300) {
         String body = res.getBody();
         if (body == null) body = "";
         throw new IllegalStateException("FACTORY GRID failed. HTTP=" + code + " body=[" + body + "]");
     }

     JSONObject resp = JSONObject.fromObject(res.getBody());

     // ✅ rows
     JSONArray rowsArr;
     try {
         rowsArr = resp.getJSONArray("rows");
     } catch (Exception e) {
         rowsArr = new JSONArray();
     }

     // ✅ totalRecords -> set into gridParams if setter exists
     long total = 0;
     try {
         Object tr = resp.get("totalRecords");
         if (tr != null) total = Long.parseLong(String.valueOf(tr));
     } catch (Exception ignore) {}

     setLongByReflection(gridParams, total,
             "setTotalRecordCnt", "setTotalRecords", "setTotalRecordCount", "setTotalRecord");

     // Your Spring repository returns keys exactly like this:
     String[] keys = new String[] { "slno", "selected", "cell_keyid", "cell_code", "cell_name" };

     return convertJsonArrayToListByKeys_netSf(rowsArr, keys);
 }


    // ==========================================================
    // ✅ OPTIONAL: comboText endpoint
    // Spring: POST /api/pcs/phenomenaLoss/comboText
    // Returns: List<Map<String,Object>> (JSON array)
    // ==========================================================
    public List<String[]> getPhenomenaComboText(String keyId, String type) throws Exception {
        String apiUrl = "/pcs/phenomenaLoss/comboText";

        JSONObject req = new JSONObject();
        req.put("keyId", safeStrOrEmpty(keyId));
        req.put("type", safeStrOrEmpty(type)); // PHENOMENA / LOSS

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PHENOMENA COMBOTEXT JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PHENOMENA COMBOTEXT statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PHENOMENA COMBOTEXT responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        if (code < 200 || code >= 300) {
            String body = res.getBody();
            if (body == null) body = "";
            throw new IllegalStateException("PHENOMENA COMBOTEXT failed. HTTP=" + code + " body=[" + body + "]");
        }

        JSONArray arr = JSONArray.fromObject(res.getBody());
        // query returns: PLPM_KEYID, KEYID, PLPM_NAME (but Spring aliases will be lowercase unless you alias)
        // safest: read by these lowercase keys if your Spring JDBC returns them:
        return convertJsonArrayToListByKeys(arr, new String[] { "plpm_keyid", "keyid", "plpm_name" });
    }

    // ==========================================================
    // ✅ Common conversion (NO "null" in UI)
    // ==========================================================
    private List<String[]> convertJsonArrayToListByKeys(JSONArray arr, String[] keys) {
        List<String[]> list = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject rowObj = arr.getJSONObject(i);
            String[] row = new String[keys.length];

            for (int k = 0; k < keys.length; k++) {
                Object v = rowObj.opt(keys[k]);

                if (v == null) {
                    row[k] = "";
                    continue;
                }

                String s = String.valueOf(v);
                if (s == null) {
                    row[k] = "";
                    continue;
                }

                s = s.trim();
                if (s.length() == 0 || "null".equalsIgnoreCase(s) || "undefined".equalsIgnoreCase(s)) {
                    row[k] = "";
                    continue;
                }

                row[k] = s;
            }

            list.add(row);
        }

        // Debug (optional)
        for (String[] r : list) {
            CommonMessage.debugMsg(Arrays.toString(r));
        }

        return list;
    }

    // ==========================================================
    // ✅ small helpers
    // ==========================================================
    private Object safeStrOrNull(String s) {
        if (!CommonFunctions.isValidKeyId(s)) return null;
        String t = s.trim();
        if (t.length() == 0 || "null".equalsIgnoreCase(t) || "{}".equals(t)) return null;
        return t;
    }

    private String safeStrOrEmpty(String s) {
        if (!CommonFunctions.isValidKeyId(s)) return "";
        String t = s.trim();
        if (t.length() == 0 || "null".equalsIgnoreCase(t) || "{}".equals(t)) return "";
        return t;
    }

    private String safeStrOrDefault(String s, String def) {
        String out = safeStrOrEmpty(s);
        return out.length() == 0 ? def : out;
    }
    
    public PcsTlLossphenomenamst delete(PcsTlLossphenomenamst mst, String mstKeyid) throws Exception {

        if (mst == null) {
            throw new IllegalArgumentException("PcsTlLossphenomenamst is null");
        }
        if (!CommonFunctions.isValidKeyId(mstKeyid)) {
            throw new IllegalArgumentException("mstKeyid is null/empty");
        }

        String apiUrl = "/pcs/phenomenaLoss/delete";

        JSONObject req = new JSONObject();
        req.put("plpmKeyid", mstKeyid.trim());

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("PHENOMENA DELETE JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

        CommonMessage.debugMsg("PHENOMENA DELETE statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("PHENOMENA DELETE responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            // ✅ Spring might return 500 with message:
            // "Cannot delete. This Phenomena is mapped..."
            // or any other validation error. Show it clearly.
            throw new IllegalStateException("PHENOMENA DELETE failed. This Phenomena is mapped to DMT(s) and must be unmapped first.HTTP=" + code + " body=[" + body + "]");
            
        }

        // Parse success response
        JSONObject resp = JSONObject.fromObject(body);

        // Spring returns mstKeyid in response
        String deletedKey = "";
        try {
            Object v = resp.get("mstKeyid");
            if (v != null) deletedKey = String.valueOf(v);
        } catch (Exception ignore) {}

        // set key back (optional)
        if (CommonFunctions.isValidKeyId(deletedKey)) {
            mst.setPlpmKeyid(deletedKey);
        } else {
            mst.setPlpmKeyid(mstKeyid.trim());
        }

        // If you want, you can print message:
        try {
            Object msg = resp.get("msg");
            CommonMessage.debugMsg("PHENOMENA DELETE msg :: " + (msg == null ? "" : msg.toString()));
        } catch (Exception ignore) {}

        return mst;
    }
    
    
 
 
 public PcsTlLossphenfactorylink savemultiple1(
	        PcsTlLossphenfactorylink pcsTlLossphenfactorylink,
	        String pillCode,
	        String deptId,
	        String drillLevel
	) throws Exception {

	    if (pcsTlLossphenfactorylink == null) {
	        throw new IllegalArgumentException("PcsTlLossphenfactorylink is null");
	    }

	    String apiUrl = "/pcs/phenomenaLoss/mapping/save";

	    // Rows from JSP / service fillValues
	    List<PcsTlLossphenfactorylink> rows = pcsTlLossphenfactorylink.getmethodPillarFactlink();
	    if (rows == null || rows.isEmpty()) {
	        rows = new ArrayList<>();
	        rows.add(pcsTlLossphenfactorylink); // fallback single row
	    }

	    // ✅ createdBy: try parent, else take from first valid row
	    String createdBy = safeStrOrEmpty(pcsTlLossphenfactorylink.getPpflCreatedby());
	    if (!CommonFunctions.isValidKeyId(createdBy)) {
	        for (PcsTlLossphenfactorylink r : rows) {
	            if (r == null) continue;
	            String cb = safeStrOrEmpty(r.getPpflCreatedby());
	            if (CommonFunctions.isValidKeyId(cb)) {
	                createdBy = cb;
	                break;
	            }
	        }
	    }

	    if (!CommonFunctions.isValidKeyId(createdBy)) {
	        throw new IllegalArgumentException("createdBy is required (ppflCreatedby is empty)");
	    }

	    // ✅ Build links as List<Map> (NO JSONArray usage)
	    List<java.util.Map<String, Object>> links = new ArrayList<>();

	    for (PcsTlLossphenfactorylink row : rows) {
	        if (row == null) continue;

	        String phenId = safeStrOrEmpty(row.getPpflPlpmKeyid());
	        String factoryId = safeStrOrEmpty(row.getPpflFactoryid());

	        if (!CommonFunctions.isValidKeyId(phenId) || !CommonFunctions.isValidKeyId(factoryId)) {
	            continue;
	        }

	        String isDelete = safeStrOrEmpty(row.getIsDelete());
	        if (!CommonFunctions.isValidKeyId(isDelete)) isDelete = "N";

	        java.util.Map<String, Object> linkObj = new java.util.LinkedHashMap<>();
	        linkObj.put("ppflPlpmKeyid", phenId);
	        linkObj.put("ppflFactoryid", factoryId);
	        linkObj.put("isDelete", isDelete);

	        links.add(linkObj);
	    }

	    if (links.isEmpty()) {
	        throw new IllegalArgumentException("links are required (no valid mapping rows found)");
	    }

	    JSONObject req = new JSONObject();
	    req.put("createdBy", createdBy);
	    req.put("links", links);

	    String jsonPayload = req.toString();
	    CommonMessage.debugMsg("PHENOMENA MAPPING SAVE JSON :: " + jsonPayload);

	    HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);

	    CommonMessage.debugMsg("PHENOMENA MAPPING SAVE statusCode :: " + res.getStatusCode());
	    CommonMessage.debugMsg("PHENOMENA MAPPING SAVE responseBody :: " + res.getBody());

	    int code = res.getStatusCode();
	    String body = res.getBody();
	    if (body == null) body = "";

	    if (code < 200 || code >= 300) {
	        throw new IllegalStateException("PHENOMENA MAPPING SAVE failed. HTTP=" + code + " body=[" + body + "]");
	    }

	    return pcsTlLossphenfactorylink;
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

         // numeric parse (Spring expects BigDecimal)
         BigDecimal lossVal;
         try {
             lossVal = new BigDecimal(lossValStr.trim());
         } catch (Exception e) {
             continue;
         }

         Map<String, Object> obj = new LinkedHashMap<>();
         obj.put("olseKeyid", keyId.trim());
         obj.put("olseLossvalue", lossVal); // ✅ numeric

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
