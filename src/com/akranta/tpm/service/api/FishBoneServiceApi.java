package com.akranta.tpm.service.api;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class FishBoneServiceApi {

    private final Api api;

    // ✅ Change these if your Spring controller endpoint is different
    private static final String API_SAVE_MASTER = "/fishbone/save";
    
    private static final String API_SAVE_CHILD  = "/fishbone/child/save";
    
    private static final String API_TREE_FETCH = "/fishbone/tree";
    
    private static final String API_DELETE_CHILD = "/fishbone/child/delete";

    private static final String API_GET_MASTER = "/fishbone/master/get";

    private static final String API_REPORT_GRID = "/fishbone/report/grid";


    public FishBoneServiceApi(String jwtToken) {

        // ✅ Same debug style you use
        CommonMessage.debugMsg("JWT(raw)=" + jwtToken);
        CommonMessage.debugMsg("JWT(clean)=" + cleanJwt(jwtToken));
        CommonMessage.debugMsg("JWT(clean length)=" + (cleanJwt(jwtToken) == null ? 0 : cleanJwt(jwtToken).length()));
        CommonMessage.debugMsg("JWT contains \\n ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\n")));
        CommonMessage.debugMsg("JWT contains \\r ? " + (cleanJwt(jwtToken) != null && cleanJwt(jwtToken).contains("\r")));

        this.api = new Api(cleanJwt(jwtToken));
    }
    
    
    public List<String[]> getFBDetail(String keyId) throws Exception {

        if (!hasText(keyId)) {
            throw new IllegalArgumentException("keyId is required");
        }

        CommonMessage.debugMsg("Inside FishBoneServiceApi.getFBDetail() keyId=" + keyId);

        // ✅ build request payload
        JSONObject req = new JSONObject();
        req.put("keyId", safeStr(keyId));

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE RPT GRID JSON :: " + jsonPayload);

        // ✅ call springboot
        HttpResponse res = api.makeAuthRequest(API_REPORT_GRID, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE RPT GRID statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE RPT GRID responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE RPT GRID failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ response is array of objects
        net.sf.json.JSONArray arr = net.sf.json.JSONArray.fromObject(body);

        List<String[]> result = new ArrayList<>();

        // ✅ header row (must exist for getCol + getData)
        String[] header = new String[30];
        for (int i = 1; i <= 30; i++) {
            header[i - 1] = "level_" + i;
        }
        result.add(header);

        // ✅ data rows
        for (int r = 0; r < arr.length(); r++) {
            JSONObject rowObj = arr.getJSONObject(r);

            String[] row = new String[30];
            for (int i = 1; i <= 30; i++) {
                String col = "level_" + i;

                // value can be null
                Object val = rowObj.has(col) ? rowObj.get(col) : null;
                row[i - 1] = (val == null || "null".equalsIgnoreCase(String.valueOf(val)))
                        ? ""
                        : String.valueOf(val);
            }
            result.add(row);
        }

        CommonMessage.debugMsg("FISHBONE RPT GRID rows returned (with header) :: " + result.size());
        return result;
    }

    public GenTlFishbonemst getFillControl(String fishboneKeyId) throws Exception {

        if (!hasText(fishboneKeyId)) {
            throw new IllegalArgumentException("fishboneKeyId is required");
        }

        CommonMessage.debugMsg("Inside FishBoneServiceApi.getFillControl() keyId=" + fishboneKeyId);

        JSONObject req = new JSONObject();
        req.put("keyId", safeStr(fishboneKeyId));

        // Optional (not required from your Eclipse flow)
        req.put("refDocType", "");
        req.put("refDocId", "");

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE MASTER GET JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(API_GET_MASTER, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE MASTER GET statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE MASTER GET responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE MASTER GET failed HTTP=" + code + " body=[" + body + "]");
        }

        if (!hasText(body)) {
            throw new IllegalStateException("Empty response body from master/get");
        }

        JSONObject obj = JSONObject.fromObject(body);

        GenTlFishbonemst mst = new GenTlFishbonemst();

        // ✅ Important: return "{}" for nullable legacy fields (same as pre-api style)
        mst.setFismKeyid(optStr(obj, "fismKeyid"));
        mst.setFismFlid(optStr(obj, "fismFlid"));

        mst.setFismElementid(safeStrOrBraces(optStr(obj, "fismElementid")));
        mst.setFismRefdocid(safeStrOrBraces(optStr(obj, "fismRefdocid")));
        mst.setFismRefdoctype(safeStrOrBraces(optStr(obj, "fismRefdoctype")));
        
        mst.setFismRevisionno(safeStrOrNull(optStr(obj, "fismRevisionno")));

        mst.setFismTitle(optStr(obj, "fismTitle"));
        mst.setFismProblem(optStr(obj, "fismProblem"));

       // mst.setFismPrepareddate(toDdMmmYyyy(optStr(obj, "fismPrepareddate")));
        
      
        mst.setFismPrepareddate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(optStr(obj, "fismPrepareddate")));
        
        
        mst.setFismPreparedby(optStr(obj, "fismPreparedby"));

        mst.setFismApproveddate(toDdMmmYyyy(optStr(obj, "fismApproveddate")));
        
        
        
        
        mst.setFismApprovedby(optStr(obj, "fismApprovedby"));

        mst.setFismStatus(firstNonEmpty(optStr(obj, "fismStatus"), "Y"));
        mst.setFismDefect(safeStrOrNull(optStr(obj, "fismDefect")));

        mst.setFismTempfield2(firstNonEmpty(optStr(obj, "fismTempfield2"), "-"));
        mst.setFismTempfield3(firstNonEmpty(optStr(obj, "fismTempfield3"), "-"));
        mst.setFismTempfield4(firstNonEmpty(optStr(obj, "fismTempfield4"), "-"));
        mst.setFismTempfield5(firstNonEmpty(optStr(obj, "fismTempfield5"), "-"));

        mst.setFismActive(firstNonEmpty(optStr(obj, "fismActive"), "Y"));
        mst.setFismCreatedby(optStr(obj, "fismCreatedby"));

        mst.setFismCreatedon(safeStrOrNull(optStr(obj, "fismCreatedon")));
        mst.setFismModifiedon(safeStrOrNull(optStr(obj, "fismModifiedon")));

        CommonMessage.debugMsg("FISHBONE MASTER GET SUCCESS :: keyId=" + mst.getFismKeyid());
        return mst;
    }

    
    private String toDdMmmYyyy(String input) {

        if (!hasText(input)) return null;

        String s = input.trim();

        // ✅ already in dd-MMM-yyyy
        if (s.matches("\\d{2}-[A-Za-z]{3}-\\d{4}")) {
            return s;
        }

        try {
            // ✅ ISO "2025-12-15T00:00:00"
            if (s.contains("T")) {
                LocalDateTime dt = LocalDateTime.parse(s, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                return dt.toLocalDate().format(
                        DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH)
                );
            }

            // ✅ ISO "2025-12-15"
            if (s.matches("\\d{4}-\\d{2}-\\d{2}")) {
                LocalDate d = LocalDate.parse(s, DateTimeFormatter.ISO_LOCAL_DATE);
                return d.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH));
            }

        } catch (Exception e) {
            // ignore, fallback below
        }

        // fallback: return as-is
        return s;
    }


    public GenTlFishbonedtl deleteFishBoneChildEntry(GenTlFishbonedtl dtl) throws Exception {

        if (dtl == null) {
            throw new IllegalArgumentException("GenTlFishbonedtl is null");
        }

        String detailKeyId = safeStr(dtl.getFisdKeyid());

        if (!hasText(detailKeyId)) {
            throw new IllegalArgumentException("DtlId / fisdKeyid is required for delete");
        }

        // ✅ Build request JSON for Spring controller
        JSONObject req = new JSONObject();
        req.put("detailKeyId", detailKeyId);

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE CHILD DELETE JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(API_DELETE_CHILD, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE CHILD DELETE statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE CHILD DELETE responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE CHILD DELETE failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Response example:
        // { "msg":"Data Deleted successfully", "deletedChildren":2, "detailKeyId":"FSD..." }
        JSONObject resp = JSONObject.fromObject(body);
        String msg = optStr(resp, "msg");

        // ✅ Keep compatibility with your servlet:
        // if keyid == null => "Data Not Deleted"
        if (msg != null && msg.toLowerCase().contains("not deleted")) {
            dtl.setFisdKeyid(null);
        } else {
            dtl.setFisdKeyid(detailKeyId);
        }

        CommonMessage.debugMsg("FISHBONE CHILD DELETE SUCCESS :: " + msg
                + " detailKeyId=" + detailKeyId
                + " deletedChildren=" + optStr(resp, "deletedChildren"));

        return dtl;
    }

 // ==========================================================
 // ✅ CREATE CHILD ENTRY (DAO compatible signature)
 // Handles:
 //   1) Same Level save  (parentId = FB001)
 //   2) Child save       (parentId = detailId)
 //   3) Edit cause only  (Editval)
 // ==========================================================
 public GenTlFishbonedtl createChildEntry(GenTlFishbonedtl newDtl,
                                         GenTlFishbonedtl existDtl,
                                         FishBoneBean fishBoneBean,
                                         String editval) throws Exception {

     if (newDtl == null) throw new IllegalArgumentException("newDtl is null");

     CommonMessage.debugMsg("Inside FishBoneServiceApi.createChildEntry()");
     CommonMessage.debugMsg("editval :: " + editval);

     // ✅ If Editval -> update cause only
     if ("Editval".equalsIgnoreCase(safeStr(editval))) {

         // In old Eclipse DAO:
         // UPDATE gen_tl_fishbonedtl SET FISD_CAUSE='newCause' WHERE FISD_KEYID='targetKey'
         //
         // targetKey = newDtl.getFisdParentid()  (servlet sets it like that)
         // newCause  = newDtl.getFismParent()

         String masterKey = safeStr(newDtl.getFisdFismKeyid());
         String targetKey = firstNonEmpty(
                 safeStr(newDtl.getFisdParentid()),  // most important
                 safeStr(newDtl.getFisdKeyid())
         );

         // ✅ new cause value (matches DAO logic: getFismParent)
         String newCause = firstNonEmpty(
                 safeStr(newDtl.getFismParent()),
                 safeStr(newDtl.getFisdCause())
         );

         String createdBy = safeStr(newDtl.getFisdCreatedby());

         if (!hasText(masterKey)) throw new IllegalArgumentException("masterKey is required for Editval");
         if (!hasText(targetKey)) throw new IllegalArgumentException("detailKey is required for Editval");
         if (!hasText(newCause)) throw new IllegalArgumentException("new cause is required for Editval");

         // ✅ Call Spring Edit Mode
         editChildCause(masterKey, targetKey, newCause, createdBy);

         // keep same object, just return it
         newDtl.setFisdKeyid(targetKey);
         return newDtl;
     }

     // ✅ Normal insert mode (same-level OR child)
     // For CREATE we must ensure fisdKeyid is NULL so Spring inserts new row
     newDtl.setFisdKeyid(null);

     // ✅ Make sure parentId is set (your servlet already sets correctly)
     if (!hasText(newDtl.getFisdParentid())) {
         newDtl.setFisdParentid("FB001");
     }

     // ✅ Make sure remarks default "-"
     if (!hasText(newDtl.getFisdRemarks())) {
         newDtl.setFisdRemarks("-");
     }

     // ✅ Call Spring /child/save
     GenTlFishbonedtl saved = saveOrUpdateChild(newDtl);

     return saved;
 }


    public List<GenTlFishbonedtl> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl,
                                                   GenTlFishbonemst genTlFishbonemst,
                                                   String id,
                                                   String masterId) throws Exception {

        CommonMessage.debugMsg("Inside FishBoneServiceApi.getFishBoneValues()");

        // ✅ master key: prefer genTlFishbonemst.getFismKeyid() else masterId param
        String mstKey = firstNonEmpty(
                safeStr(genTlFishbonemst != null ? genTlFishbonemst.getFismKeyid() : null),
                safeStr(masterId)
        );

        // ✅ current node id (in your servlet id=request.getParameter("id"))
        String nodeId = safeStr(id);

        if (!hasText(mstKey)) {
            throw new IllegalArgumentException("masterId is required for fishbone tree fetch");
        }
        if (!hasText(nodeId)) {
            nodeId = "0"; // root fallback
        }

        // ✅ Build request JSON same as FishboneTreeRequest
        JSONObject req = new JSONObject();
        req.put("id", nodeId);
        req.put("masterId", mstKey);

        // optional fields (send only if you have)
        if (genTlFishbonedtl != null && hasText(genTlFishbonedtl.getFisdParentid())) {
            req.put("parentId", genTlFishbonedtl.getFisdParentid());
        }
        if (genTlFishbonedtl != null && hasText(genTlFishbonedtl.getFisdLevelno())) {
            req.put("levelNo", genTlFishbonedtl.getFisdLevelno());
        }

        // ✅ This is only for ROOT title display
        // In your servlet root node uses "problem" // changed by vignesh
        req.put("problem", ""); // or you can pass actual Problem string if available

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE TREE FETCH JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(API_TREE_FETCH, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE TREE FETCH statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE TREE FETCH responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE TREE FETCH failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Spring returns List<Map<..>> => JSON array
        JSONArray arr = JSONArray.fromObject(body);

        List<GenTlFishbonedtl> list = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject row = arr.getJSONObject(i);

            // Spring keys from your serviceimpl:
            // id, displayCode, parentId, orderNo, levelNo, elementType/masterId
            String fisdKeyid   = optStr(row, "id");
            String fisdCause   = optStr(row, "displayCode");
            String fisdParent  = optStr(row, "parentId");

            int orderNo = safeIntOrDefault(row.opt("orderNo"), 1);
            int levelNo = safeIntOrDefault(row.opt("levelNo"), 1);

            GenTlFishbonedtl dtl = new GenTlFishbonedtl();
            dtl.setFisdKeyid(fisdKeyid);
            dtl.setFisdCause(fisdCause);
            dtl.setFisdParentid(fisdParent);

            // ✅ your model expects String
            dtl.setFisdOrderno(String.valueOf(orderNo));
            dtl.setFisdLevelno(String.valueOf(levelNo));

            // ✅ needed for compatibility
            dtl.setFisdFismKeyid(mstKey);

            list.add(dtl);
        }

        CommonMessage.debugMsg("Fishbone children fetched count = " + list.size());
        return list;
    }

    // ==========================================================
    // ✅ CREATE MASTER (Your service create(...) will call this)
    // Spring: saveOrUpdateMaster()
    // POST /api/fishbone/master/save
    // ==========================================================
    public GenTlFishbonemst create(GenTlFishbonemst newMst, GenTlFishbonemst existMst) throws Exception {
        // ✅ You already fill values in service before calling API
        // So we directly call saveOrUpdateMaster(newMst)
        return saveOrUpdateMaster(newMst);
    }
    // ==========================================================
 // ✅ CREATE (DAO style signature)
 // Your old code expects: create(newMst, existMst, fishBoneBean)
 // Spring side will auto insert default children.
 // ==========================================================
 public GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,
                               GenTlFishbonemst existGenTlFishbonemst,
                               FishBoneBean fishBoneBean) throws Exception {

     if (newGenTlFishbonemst == null) {
         throw new IllegalArgumentException("newGenTlFishbonemst is null");
     }

     // ✅ IMPORTANT:
     // DAO create always generates a NEW key.
     // So to force Spring "CREATE", keep keyid EMPTY.
     if (!hasText(newGenTlFishbonemst.getFismKeyid())) {
         newGenTlFishbonemst.setFismKeyid(null);
     }

     // ✅ You already call fillValuesFishBone() in service before this,
     // so we don’t need to use existMst or fishBoneBean here.
     // But we keep them to match your method signature.

     // ✅ Call Spring Master Save API
     GenTlFishbonemst saved = saveOrUpdateMaster(newGenTlFishbonemst);

     return saved;
 }


    // ==========================================================
    // ✅ SAVE / UPDATE MASTER
    // ==========================================================
    public GenTlFishbonemst saveOrUpdateMaster(GenTlFishbonemst mst) throws Exception {

        if (mst == null) throw new IllegalArgumentException("GenTlFishbonemst is null");

        // ✅ Spring expects LocalDateTime -> safest ISO format
        String nowIso = CommonFunctions.pg_dateTimeNow(); // "yyyy-MM-dd'T'HH:mm:ss"

        // ✅ Defaults like Spring applyMasterDefaults()
        if (!hasText(mst.getFismStatus())) mst.setFismStatus("Y");
        if (!hasText(mst.getFismActive())) mst.setFismActive("Y");

        if (!hasText(mst.getFismTempfield2())) mst.setFismTempfield2("-");
        if (!hasText(mst.getFismTempfield3())) mst.setFismTempfield3("-");
        if (!hasText(mst.getFismTempfield4())) mst.setFismTempfield4("-");
        if (!hasText(mst.getFismTempfield5())) mst.setFismTempfield5("-");

        // ✅ Convert possible "19-Jan-2026 12:03:08" into ISO if needed
        mst.setFismPrepareddate(normalizeDateTime(mst.getFismPrepareddate(), nowIso));
        mst.setFismApproveddate(normalizeDateTime(mst.getFismApproveddate(), nowIso));
        mst.setFismCreatedon(normalizeDateTime(mst.getFismCreatedon(), nowIso));
        mst.setFismModifiedon(normalizeDateTime(mst.getFismModifiedon(), nowIso));

        // ✅ Build JSON payload for Spring DTO
        JSONObject req = new JSONObject();

        // If keyid null -> create, else update
        req.put("fismKeyid", safeStrOrNull(mst.getFismKeyid()));

        req.put("fismFlid", safeStr(mst.getFismFlid()));
        
        // VIGNESH CHANGING --03fEB2026
        
  //      req.put("fismElementid", safeStrOrBraces(mst.getFismElementid()));
   //     req.put("fismRefdocid", safeStrOrBraces(mst.getFismRefdocid()));
  //      req.put("fismRefdoctype", safeStrOrBraces(mst.getFismRefdoctype()));
        
        req.put("fismElementid", safeStrOrNull(mst.getFismElementid()));
        req.put("fismRefdocid", safeStrOrNull(mst.getFismRefdocid()));
        req.put("fismRefdoctype", safeStrOrNull(mst.getFismRefdoctype()));

        req.put("fismTitle", safeStr(mst.getFismTitle()));
        req.put("fismProblem", safeStr(mst.getFismProblem()));
        req.put("fismRevisionno", safeStrOrNull(mst.getFismRevisionno()));

        req.put("fismPrepareddate", safeStrOrNull(mst.getFismPrepareddate()));
        req.put("fismPreparedby", safeStr(mst.getFismPreparedby()));

        req.put("fismApproveddate", safeStrOrNull(mst.getFismApproveddate()));
        req.put("fismApprovedby", safeStr(mst.getFismApprovedby()));

        req.put("fismStatus", safeStr(mst.getFismStatus()));
        req.put("fismDefect", safeStrOrNull(mst.getFismDefect()));

        req.put("fismTempfield2", safeStr(mst.getFismTempfield2()));
        req.put("fismTempfield3", safeStr(mst.getFismTempfield3()));
        req.put("fismTempfield4", safeStr(mst.getFismTempfield4()));
        req.put("fismTempfield5", safeStr(mst.getFismTempfield5()));

        req.put("fismActive", safeStr(mst.getFismActive()));
        req.put("fismCreatedby", safeStr(mst.getFismCreatedby()));

        req.put("fismCreatedon", safeStrOrNull(mst.getFismCreatedon()));
        req.put("fismModifiedon", safeStrOrNull(mst.getFismModifiedon()));

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE MASTER JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(API_SAVE_MASTER, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE MASTER statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE MASTER responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE MASTER SAVE failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Spring returns { masterKeyId: "..."} but your servlet may expect "Mstkeyid"
        JSONObject resp = JSONObject.fromObject(body);

        String masterKey = firstNonEmpty(
                optStr(resp, "masterKeyId"),
                optStr(resp, "mstKeyid"),
                optStr(resp, "Mstkeyid"),
                optStr(resp, "masterKeyid")
        );

        if (hasText(masterKey)) {
            mst.setFismKeyid(masterKey);
        }

        return mst;
    }

    // ==========================================================
    // ✅ SAVE / UPDATE CHILD DETAIL
    // Spring: saveOrUpdateChild()
    // POST /api/fishbone/child/save
    // ==========================================================
    public GenTlFishbonedtl saveOrUpdateChild(GenTlFishbonedtl dtl) throws Exception {

        if (dtl == null) throw new IllegalArgumentException("GenTlFishbonedtl is null");

        String nowIso = CommonFunctions.pg_dateTimeNow();

        // ✅ Decide Mode
        boolean isUpdate = hasText(dtl.getFisdKeyid()); // if key exists -> UPDATE

        // ✅ Defaults (same as Spring applyChildDefaults)
        if (!hasText(dtl.getFisdParentid())) dtl.setFisdParentid("FB001");
        if (!hasText(dtl.getFisdOrderno()))  dtl.setFisdOrderno("1");
        if (!hasText(dtl.getFisdLevelno()))  dtl.setFisdLevelno("1");

        if (!hasText(dtl.getFisdTempfield1())) dtl.setFisdTempfield1("-");
        if (!hasText(dtl.getFisdTempfield2())) dtl.setFisdTempfield2("-");
        if (!hasText(dtl.getFisdTempfield3())) dtl.setFisdTempfield3("-");
        if (!hasText(dtl.getFisdTempfield4())) dtl.setFisdTempfield4("-");
        if (!hasText(dtl.getFisdTempfield5())) dtl.setFisdTempfield5("-");
        if (!hasText(dtl.getFisdActive()))     dtl.setFisdActive("Y");

        // ✅ Remarks must never be empty (your old DAO did this)
        if (!hasText(dtl.getFisdRemarks())) {
            dtl.setFisdRemarks("-");
        }

        // ✅ Date normalize
        // createdon -> keep old if present, else set now
        dtl.setFisdCreatedon(normalizeDateTime(dtl.getFisdCreatedon(), nowIso));
        // modifiedon -> always set now for updates too
        dtl.setFisdModifiedon(normalizeDateTime(dtl.getFisdModifiedon(), nowIso));

        // ✅ For CREATE force key null
        if (!isUpdate) {
            dtl.setFisdKeyid(null);
        }

        // ✅ Build JSON
        JSONObject req = new JSONObject();

        // null -> create, not-null -> update
        req.put("fisdKeyid", safeStrOrNull(dtl.getFisdKeyid()));

        // master key required
        req.put("fisdFismKeyid", safeStr(dtl.getFisdFismKeyid()));

        req.put("fisdCause", safeStr(dtl.getFisdCause()));
        req.put("fisdParentid", safeStr(dtl.getFisdParentid()));

        // Spring expects Integer
        req.put("fisdOrderno", safeIntOrDefault(dtl.getFisdOrderno(), 1));
        req.put("fisdLevelno", safeIntOrDefault(dtl.getFisdLevelno(), 1));

        req.put("fisdTempfield1", safeStr(dtl.getFisdTempfield1()));
        req.put("fisdTempfield2", safeStr(dtl.getFisdTempfield2()));
        req.put("fisdTempfield3", safeStr(dtl.getFisdTempfield3()));
        req.put("fisdTempfield4", safeStr(dtl.getFisdTempfield4()));
        req.put("fisdTempfield5", safeStr(dtl.getFisdTempfield5()));

        req.put("fisdActive", safeStr(dtl.getFisdActive()));
        req.put("fisdCreatedby", safeStr(dtl.getFisdCreatedby()));

        req.put("fisdCreatedon", safeStrOrNull(dtl.getFisdCreatedon()));
        req.put("fisdModifiedon", safeStrOrNull(dtl.getFisdModifiedon()));

        req.put("fisdRemarks", safeStr(dtl.getFisdRemarks()));

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE CHILD MODE :: " + (isUpdate ? "UPDATE" : "CREATE"));
        CommonMessage.debugMsg("FISHBONE CHILD JSON :: " + jsonPayload);

        // ✅ Call Spring
        HttpResponse res = api.makeAuthRequest(API_SAVE_CHILD, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE CHILD statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE CHILD responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE CHILD SAVE failed HTTP=" + code + " body=[" + body + "]");
        }

        // ✅ Parse response
        JSONObject resp = JSONObject.fromObject(body);

        // ✅ detailKeyId can come from Spring create/update
        String detailKey = firstNonEmpty(
                optStr(resp, "detailKeyId"),
                optStr(resp, "detailKeyid"),
                optStr(resp, "fisdKeyid"),
                optStr(resp, "fisdKeyId")
        );

        // ✅ parentId comes back (your servlet expects Parentid)
        String parentId = firstNonEmpty(
                optStr(resp, "parentId"),
                optStr(resp, "Parentid"),
                optStr(resp, "fisdParentid")
        );

        if (hasText(detailKey)) {
            dtl.setFisdKeyid(detailKey);
        }
        if (hasText(parentId)) {
            dtl.setFisdParentid(parentId);
        }

        CommonMessage.debugMsg("FISHBONE CHILD SUCCESS :: detailKey=" + dtl.getFisdKeyid()
                + " parentId=" + dtl.getFisdParentid()
                + " msg=" + optStr(resp, "msg"));

        return dtl;
    }

 // ==========================================================
 // ✅ UPDATE CHILD ENTRY (DAO compatible signature)
 // ==========================================================
 public GenTlFishbonedtl updateChildEntry(GenTlFishbonedtl newDtl,
                                         GenTlFishbonedtl existDtl,
                                         FishBoneBean fishBoneBean) throws Exception {

     if (newDtl == null) throw new IllegalArgumentException("newDtl is null");

     // ✅ must have keyid to update
     if (!hasText(newDtl.getFisdKeyid())) {
         throw new IllegalArgumentException("fisdKeyid is required for updateChildEntry()");
     }

     // ✅ simply call saveOrUpdateChild (it will go UPDATE mode)
     return saveOrUpdateChild(newDtl);
 }

    // ==========================================================
    // ✅ EDIT CHILD CAUSE ONLY (Spring editMode = EDITVAL)
    // ==========================================================
    public String editChildCause(String masterKeyId, String detailKeyId, String newCause, String createdBy) throws Exception {

        if (!hasText(masterKeyId)) throw new IllegalArgumentException("masterKeyId is required");
        if (!hasText(detailKeyId)) throw new IllegalArgumentException("detailKeyId is required");
        if (!hasText(newCause)) throw new IllegalArgumentException("newCause is required");

        JSONObject req = new JSONObject();

        req.put("fisdFismKeyid", safeStr(masterKeyId));
        req.put("fisdKeyid", safeStr(detailKeyId));
        req.put("editMode", "EDITVAL");        // ✅ Spring edit flag
        req.put("updateCause", safeStr(newCause)); // ✅ update field alias in Spring
        req.put("fisdCreatedby", safeStr(createdBy)); // validation safe

        String jsonPayload = req.toString();
        CommonMessage.debugMsg("FISHBONE EDIT CHILD JSON :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(API_SAVE_CHILD, "POST", jsonPayload);

        CommonMessage.debugMsg("FISHBONE EDIT CHILD statusCode :: " + res.getStatusCode());
        CommonMessage.debugMsg("FISHBONE EDIT CHILD responseBody :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("FISHBONE EDIT CHILD failed HTTP=" + code + " body=[" + body + "]");
        }

        return "Data Saved Successfully";
    }

    // ==========================================================
    // ✅ JWT CLEAN
    // ==========================================================
    private String cleanJwt(String token) {
        if (token == null) return null;
        String t = token.trim();

        if (t.toLowerCase().startsWith("bearer ")) {
            t = t.substring(7).trim();
        }

        t = t.replace("\r", "").replace("\n", "");
        return t;
    }

    // ==========================================================
    // ✅ DATE NORMALIZER: "19-Jan-2026 12:03:08" -> ISO for Spring
    // ==========================================================
    private String normalizeDateTime(String input, String defaultIso) {

        if (!hasText(input)) return defaultIso;

        String s = input.trim();

        // already ISO
        if (s.contains("T") && s.length() >= 19) {
            return s;
        }

        try {
            // ✅ Parse "19-Jan-2026 12:03:08"
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
            // ✅ Parse "19-Jan-2026"
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

        return defaultIso;
    }

    // ==========================================================
    // ✅ SAFE HELPERS
    // ==========================================================
    private boolean hasText(String v) {
        if (v == null) return false;
        String s = v.trim();
        if (s.length() == 0) return false;
        if ("{}".equals(s)) return false;
        if ("null".equalsIgnoreCase(s)) return false;
        if ("undefined".equalsIgnoreCase(s)) return false;
        return true;
    }

    private String safeStr(Object v) {
        if (v == null) return "";
        String s = String.valueOf(v);
        if (s == null) return "";
        s = s.trim();
        if (s.length() == 0) return "";
        if ("{}".equals(s)) return "";
        if ("null".equalsIgnoreCase(s)) return "";
        if ("undefined".equalsIgnoreCase(s)) return "";
        return s;
    }

    private String safeStrOrNull(Object v) {
        String s = safeStr(v);
        return s.length() == 0 ? null : s;
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

    private String optStr(JSONObject obj, String key) {
        if (obj == null || key == null) return "";
        Object v = obj.opt(key);
        return v == null ? "" : String.valueOf(v);
    }

    private String firstNonEmpty(String... vals) {
        if (vals == null) return "";
        for (String s : vals) {
            if (hasText(s)) return s.trim();
        }
        return "";
    }
    
 // ✅ Use this ONLY for elementid/refdocid/refdoctype/revisionno
    private String safeStrOrBraces(Object v) {

        if (v == null) return "{}";

        String s = String.valueOf(v);
        if (s == null) return "{}";

        s = s.trim();

        if (s.length() == 0) return "{}";
        if ("null".equalsIgnoreCase(s)) return "{}";
        if ("undefined".equalsIgnoreCase(s)) return "{}";

        // ✅ if already {}, keep it
        if ("{}".equals(s)) return "{}";

        return s;
    }
    
    

}
