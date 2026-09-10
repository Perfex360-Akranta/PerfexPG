package com.akranta.tpm.service.api;

import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BAL_WomTlManpowercostactual;
import com.akranta.tpm.model.BAL_WomTlManpowercostplan;
import com.akranta.tpm.model.BAL_WomTlOthercostactual;
import com.akranta.tpm.model.BAL_WomTlOthercostplan;
import com.akranta.tpm.model.BAL_WomTlServicecostactual;
import com.akranta.tpm.model.BAL_WomTlServicecostplan;
import com.akranta.tpm.model.BAL_WomTlSparecostactual;
import com.akranta.tpm.model.BAL_WomTlSparecostplan;
import com.akranta.tpm.model.BAL_WomTlUtilitycostactual;
import com.akranta.tpm.model.BAL_WomTlUtilitycostplan;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.model.HttpResponse;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BdmServiceApi {

    private final Api api;

    public BdmServiceApi(String jwtToken) {
        this.api = new Api(jwtToken);
    }

    // =========================================================================
    //  SINGLE SAVE METHOD  –  INSERT when keyid is blank, UPDATE otherwise.
    //  Both paths call POST /api/bdm/save.
    //  Spring's BdmServiceImpl decides insert vs update based on keyid.
    // =========================================================================
    public BAL_BdmTlMst saveRecord(BAL_BdmTlMst bdmTlMst) throws Exception {

        String apiUrl = "/bdm/save";

        // Always mark active = Y on save
        bdmTlMst.setBdmsActive("Y");

        boolean isInsert = (bdmTlMst.getBdmsKeyid() == null
                || bdmTlMst.getBdmsKeyid().trim().isEmpty());

        CommonMessage.debugMsg("BDM saveRecord – mode: " + (isInsert ? "INSERT" : "UPDATE")
                + ", keyid: " + bdmTlMst.getBdmsKeyid());

        String jsonPayload = toJson(bdmTlMst);
        CommonMessage.debugMsg("BDM saveRecord Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("BDM saveRecord Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("BDM saveRecord Response: " + jsonResponse);

        BAL_BdmTlMst result = fromJson(jsonResponse);
        CommonMessage.debugMsg("BDM saveRecord Result: " + result);
        return result;
    }

    // =========================================================================
    //  BUILD REQUEST JSON
    //  Wraps master + detail into { "master": {...}, "detail": {...} }
    //  keyid null  →  emits "keyid":null   →  Spring treats as INSERT
    //  keyid set   →  emits "keyid":"BDM..." →  Spring treats as UPDATE
    // =========================================================================
    public String toJson(BAL_BdmTlMst bdmTlMst) {
        StringBuilder str = new StringBuilder();
        str.append("{");

        // ── master ──────────────────────────────────────────────────────────
        str.append("\"master\":");
        str.append(bdmTlMst.toJsonManual());

        // ── detail (single detail record per BDM) ───────────────────────────
        str.append(",\"detail\":");
        if (bdmTlMst.getBdmDetail() != null && !bdmTlMst.getBdmDetail().isEmpty()) {
            str.append(bdmTlMst.getBdmDetail().get(0).toJsonManual());
        } else {
            str.append("null");
        }
        str.append(",\"bdmMultiResp\":");
        if (bdmTlMst.getbdmTlMultipleResp() != null && !bdmTlMst.getbdmTlMultipleResp().isEmpty()) {
            str.append(BAL_BdmTlMultipleResp.toJsonManualList(bdmTlMst.getbdmTlMultipleResp()));
        } else {
            str.append("null");
        }

        str.append("}");
        return str.toString();
    }

    // =========================================================================
    //  PARSE RESPONSE JSON  →  BAL_BdmTlMst
    //  Response shape: { "master": {...}, "detail": {...}, "womWorkOrder": {...} }
    // =========================================================================
    public BAL_BdmTlMst fromJson(String json) {
        JSONObject jsonObj = JSONObject.fromObject(json);

        // ── master ──────────────────────────────────────────────────────────
        JSONObject mstObj = jsonObj.getJSONObject("master");
        BAL_BdmTlMst mst  = BAL_BdmTlMst.fromJson(mstObj.toString());

        // ── detail ──────────────────────────────────────────────────────────
        if (jsonObj.has("detail") && !jsonObj.get("detail").equals(null)
                && !"null".equals(jsonObj.get("detail").toString())) {
            JSONObject dtlObj   = jsonObj.getJSONObject("detail");
            BAL_BdmTlDtl detail = BAL_BdmTlDtl.fromJson(dtlObj.toString());
            mst.getBdmDetail().clear();
            mst.getBdmDetail().add(detail);
        }

        // ── wom work order ───────────────────────────────────────────────────
        if (jsonObj.has("womWorkOrder") && !jsonObj.get("womWorkOrder").equals(null)
                && !"null".equals(jsonObj.get("womWorkOrder").toString())) {
            JSONObject womObj = jsonObj.getJSONObject("womWorkOrder");
            WomTlWomst wom    = WomTlWomst.fromJson(womObj.toString());
            mst.setWomTlWomst(wom);
        }

        return mst;
    }
    
    public List<String[]> getGridSummary(String woId) throws Exception {

        String apiUrl = "/bdm/cost-summary/" + woId;

        CommonMessage.debugMsg("BDM getGridSummary – woId: " + woId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("BDM getGridSummary Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("BDM getGridSummary Response: " + jsonResponse);

        return fromJsonGridSummary(jsonResponse);
    }

    // =========================================================================
    //  PARSE RESPONSE JSON  →  List<String[]>
    //  Response shape: JSON array of row objects (keys are lowercase from Postgres)
    // =========================================================================
    private List<String[]> fromJsonGridSummary(String json) {
        List<String[]> rows = new ArrayList<>();

        if (json == null || json.trim().isEmpty()) {
            return rows;
        }

        JSONArray jsonArr = JSONArray.fromObject(json);

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject row = jsonArr.getJSONObject(i);

            String slno   = row.has("slno")   && !row.get("slno").equals(null)   ? row.get("slno").toString()   : "";
            String param  = row.has("param")  && !row.get("param").equals(null)  ? row.get("param").toString()  : "";
            String estVal = row.has("est_val") && !row.get("est_val").equals(null) ? row.get("est_val").toString() : "0";
            String actVal = row.has("act_val") && !row.get("act_val").equals(null) ? row.get("act_val").toString() : "0";

            rows.add(new String[] { slno, param, estVal, actVal });
        }

        return rows;
    }
    
    
    public List<String[]> getGridEstimate(String formName, String woId) throws Exception {

        String apiUrl = "/bdm/grid-estimate/" + formName + "/" + woId;

        CommonMessage.debugMsg("BDM getGridEstimate – formName: " + formName + ", woId: " + woId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("BDM getGridEstimate Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("BDM getGridEstimate Response: " + jsonResponse);

        return fromJsonGridRows(jsonResponse, columnOrderFor(formName));
    }

    // =========================================================================
    //  COLUMN ORDER PER FORM TYPE
    //  Must match the SELECT column order in the corresponding repository
    //  query on the Spring side (getEmpCostEstimate, getContractorCostEstimate, etc.)
    // =========================================================================
    private String[] columnOrderFor(String formName) {
        switch (formName) {
            case "empCost":
                return new String[] {
                    "mpcp_skillid", "grdm_name", "mpcp_manpowerid", "empm_employeenumber", "empm_name",
                    "mpcp_normalmins", "mpcp_normalcost", "mpcp_totalvalue"
                };
            case "contractorCost":
                return new String[] {
                    "amvm_keyid", "amvm_name", "mpcp_manpowerid", "ungm_code", "ungm_name",
                    "mpcp_normalmins", "mpcp_normalcost", "mpcp_totalvalue"
                };
            case "spareCost":
                return new String[] {
                    "wscp_requestedby", "empm_name", "sprm_keyid", "sprm_partno", "sprm_partname",
                    "wscp_quantity", "wscp_rate", "wscp_value"
                };
            case "serviceCost":
                return new String[] {
                    "amvm_keyid", "amvm_code", "amvm_name", "svcp_jobdescription",
                    "svcp_billno", "svcp_billvalue", "svcp_remarks"
                };
            case "utilityCost":
                return new String[] {
                    "utcp_requestedby", "empm_name", "tolm_keyid", "tolm_code", "tolm_name",
                    "utcp_quantity", "utcp_minutes", "utcp_cost", "utcp_totalvalue", "utcp_remarks"
                };
            case "otherCost":
                return new String[] {
                    "otcp_requestedby", "empm_name", "otcm_keyid", "otcm_shortname", "otcm_costname",
                    "otcp_amount", "otcp_remarks"
                };
            default:
                throw new IllegalArgumentException("Unknown formName: " + formName);
        }
    }

    // =========================================================================
    //  PARSE RESPONSE JSON  →  List<String[]>
    //  Reads each row object using the given column key order, so the output
    //  array positions line up exactly with the legacy grid's expected columns.
    // =========================================================================
    private List<String[]> fromJsonGridRows(String json, String[] columns) {
        List<String[]> rows = new ArrayList<>();

        if (json == null || json.trim().isEmpty()) {
            return rows;
        }

        JSONArray jsonArr = JSONArray.fromObject(json);

        for (int i = 0; i < jsonArr.length(); i++) {
            JSONObject row = jsonArr.getJSONObject(i);
            String[] values = new String[columns.length];

            for (int c = 0; c < columns.length; c++) {
                String key = columns[c];
                values[c] = (row.has(key) && !row.get(key).equals(null))
                        ? row.get(key).toString()
                        : "";
            }
            rows.add(values);
        }

        return rows;
    }
    
    public List<String[]> getPageTotal(String formName, String formType, String woId) throws Exception {

        String apiUrl = "/bdm/page-total/" + formType + "/" + formName + "/" + woId;

        CommonMessage.debugMsg("BDM getPageTotal – formType: " + formType
                + ", formName: " + formName + ", woId: " + woId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("BDM getPageTotal Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("BDM getPageTotal Response: " + jsonResponse);

        return fromJsonGridRows(jsonResponse, pageTotalColumnsFor(formName));
    }

    // =========================================================================
    //  COLUMN ORDER PER FORM TYPE
    //  empCost / contractorCost return (TOTVAL, NOOFEMP); all others return
    //  only (TOTVAL). Must match the SELECT column order on the Spring side.
    // =========================================================================
    private String[] pageTotalColumnsFor(String formName) {
        switch (formName) {
            case "empCost":
            case "contractorCost":
                return new String[] { "totval", "noofemp" };
            case "spareCost":
            case "serviceCost":
            case "utilityCost":
            case "otherCost":
                return new String[] { "totval" };
            default:
                throw new IllegalArgumentException("Unknown formName: " + formName);
        }
    }

    public List<String[]> getGridActual(String formName, String woId) throws Exception {

        String apiUrl = "/bdm/grid-actual/" + formName + "/" + woId;

        CommonMessage.debugMsg("BDM getGridActual – formName: " + formName + ", woId: " + woId);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
        CommonMessage.debugMsg("BDM getGridActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("BDM getGridActual Response: " + jsonResponse);

        return fromJsonGridRows(jsonResponse, gridActualColumnOrderFor(formName));
    }

    // =========================================================================
    //  COLUMN ORDER PER FORM TYPE  (Actual)
    //  Must match the SELECT column order in the corresponding repository
    //  query on the Spring side (getEmpCostActual, getContractorCostActual, etc.)
    // =========================================================================
    private String[] gridActualColumnOrderFor(String formName) {
        switch (formName) {
            case "empCost":
                return new String[] {
                    "mpcs_date", "mpcs_skillid", "grdm_name", "mpcs_manpowerid", "empm_employeenumber", "empm_name",
                    "mpcs_activity", "mpcs_normalwt", "mpcs_holidaywt", "mpcs_otherwt",
                    "mpcs_normalrate", "mpcs_holidayrate", "mpcs_otherrate", "mpcs_totalvalue", "mpcs_remarks"
                };
            case "contractorCost":
                return new String[] {
                    "mpcs_date", "amvm_keyid", "amvm_name", "mpcs_manpowerid", "ungm_code", "ungm_name",
                    "mpcs_activity", "mpcs_normalwt", "mpcs_holidaywt", "mpcs_otherwt",
                    "mpcs_normalrate", "mpcs_holidayrate", "mpcs_otherrate", "mpcs_totalvalue", "mpcs_remarks"
                };
            case "spareCost":
                return new String[] {
                    "wsca_requestedby", "empm_name", "sprm_keyid", "sprm_partno", "sprm_partname",
                    "wsca_quantity", "wsca_rate", "wsca_value"
                };
            case "serviceCost":
                return new String[] {
                    "amvm_keyid", "amvm_code", "amvm_name", "svca_jobdescription",
                    "svca_billno", "svca_billdate", "svca_billvalue", "svca_remarks"
                };
            case "utilityCost":
                return new String[] {
                    "utca_requestedby", "empm_name", "tolm_keyid", "tolm_code", "tolm_name",
                    "utca_date", "utca_quantity", "utca_minutes", "utca_cost", "utca_totalvalue", "utca_remarks"
                };
            case "otherCost":
                return new String[] {
                    "otcd_requestedby", "empm_name", "otcm_keyid", "otcm_shortname", "otcm_costname",
                    "otcd_date", "otcd_amount", "otcd_remarks"
                };
            default:
                throw new IllegalArgumentException("Unknown formName: " + formName);
        }
    }
    // =========================================================================
    //  SINGLE SAVE METHOD (Manpower Cost)  –  INSERT when no row matches the
    //  composite key (woid+manpowerid+skillid), UPDATE otherwise. Spring's
    //  BdmServiceImpl.saveManpowerCost() decides insert vs update by looking
    //  that key up — no keyid flag needed, unlike saveRecord(BAL_BdmTlMst).
    // =========================================================================
    public BAL_WomTlManpowercostplan saveManpowerCost(BAL_WomTlManpowercostplan mpcp) throws Exception {

        String apiUrl = "/bdm/manpower-cost/save";

        // Always mark active = Y on save
        mpcp.setMpcpActive("Y");

        CommonMessage.debugMsg("ManpowerCost saveManpowerCost – woid: " + mpcp.getMpcpWoid()
                + ", manpowerid: " + mpcp.getMpcpManpowerid()
                + ", skillid: " + mpcp.getMpcpSkillid());

        String jsonPayload = mpcp.toJsonManual();
        CommonMessage.debugMsg("ManpowerCost saveManpowerCost Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("ManpowerCost saveManpowerCost Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("ManpowerCost saveManpowerCost Response: " + jsonResponse);

        BAL_WomTlManpowercostplan result = BAL_WomTlManpowercostplan.fromJson(jsonResponse);
        CommonMessage.debugMsg("ManpowerCost saveManpowerCost Result: " + result);
        return result;
    }
    
    // =========================================================================
    //  SINGLE SAVE METHOD (Manpower Cost Actual)  –  INSERT when no row
    //  matches the composite key (maintwoid+manpowerid+skillid), UPDATE
    //  otherwise. Spring's BdmServiceImpl.saveManpowerCostActual() decides
    //  insert vs update by looking that key up.
    // =========================================================================
    public BAL_WomTlManpowercostactual saveManpowerCostActual(BAL_WomTlManpowercostactual mpcs) throws Exception {

        String apiUrl = "/bdm/manpower-cost-actual/save";

        CommonMessage.debugMsg("ManpowerCostActual saveManpowerCostActual – maintwoid: " + mpcs.getMpcsMaintwoid()
                + ", manpowerid: " + mpcs.getMpcsManpowerid()
                + ", skillid: " + mpcs.getMpcsSkillid());

        String jsonPayload = mpcs.toJsonManual();
        CommonMessage.debugMsg("ManpowerCostActual saveManpowerCostActual Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("ManpowerCostActual saveManpowerCostActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("ManpowerCostActual saveManpowerCostActual Response: " + jsonResponse);

        BAL_WomTlManpowercostactual result = BAL_WomTlManpowercostactual.fromJson(jsonResponse);
        CommonMessage.debugMsg("ManpowerCostActual saveManpowerCostActual Result: " + result);
        return result;
    } 
    
    // =========================================================================
    //  SINGLE SAVE METHOD (Spare Cost Estimate)  –  INSERT when no row
    //  matches the composite key (woid+requestedby+sparesid), UPDATE
    //  otherwise. Spring's BdmServiceImpl.saveSpareCost() decides insert vs
    //  update by looking that key up.
    // =========================================================================
    public BAL_WomTlSparecostplan saveSpareCost(BAL_WomTlSparecostplan wscp) throws Exception {

        String apiUrl = "/bdm/spare-cost/save";

        // Always mark active = Y on save
        wscp.setWscpActive("Y");

        CommonMessage.debugMsg("SpareCost saveSpareCost – woid: " + wscp.getWscpWoid()
                + ", requestedby: " + wscp.getWscpRequestedby()
                + ", sparesid: " + wscp.getWscpSparesid());

        String jsonPayload = wscp.toJsonManual();
        CommonMessage.debugMsg("SpareCost saveSpareCost Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("SpareCost saveSpareCost Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("SpareCost saveSpareCost Response: " + jsonResponse);

        BAL_WomTlSparecostplan result = BAL_WomTlSparecostplan.fromJson(jsonResponse);
        CommonMessage.debugMsg("SpareCost saveSpareCost Result: " + result);
        return result;
    }
    // =========================================================================
    //  SINGLE SAVE METHOD (Service Cost Estimate)  –  INSERT when no row
    //  matches the composite key (woid+serviceid), UPDATE otherwise.
    //  Spring's BdmServiceImpl.saveServiceCost() decides insert vs update by
    //  looking that key up.
    // =========================================================================
    public BAL_WomTlServicecostplan saveServiceCost(BAL_WomTlServicecostplan svcp) throws Exception {

        String apiUrl = "/bdm/service-cost/save";

        CommonMessage.debugMsg("ServiceCost saveServiceCost – woid: " + svcp.getSvcpWoid()
                + ", serviceid: " + svcp.getSvcpServiceid());

        String jsonPayload = svcp.toJsonManual();
        CommonMessage.debugMsg("ServiceCost saveServiceCost Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("ServiceCost saveServiceCost Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("ServiceCost saveServiceCost Response: " + jsonResponse);

        BAL_WomTlServicecostplan result = BAL_WomTlServicecostplan.fromJson(jsonResponse);
        CommonMessage.debugMsg("ServiceCost saveServiceCost Result: " + result);
        return result;
    }
    // =========================================================================
    //  SINGLE SAVE METHOD (Utility Cost Estimate)  –  INSERT when no row
    //  matches the composite key (wokeyid+requestedby+utilitymstid), UPDATE
    //  otherwise. Spring's BdmServiceImpl.saveUtilityCost() decides insert
    //  vs update by looking that key up.
    // =========================================================================
    public BAL_WomTlUtilitycostplan saveUtilityCost(BAL_WomTlUtilitycostplan utcp) throws Exception {

        String apiUrl = "/bdm/utility-cost/save";

        CommonMessage.debugMsg("UtilityCost saveUtilityCost – wokeyid: " + utcp.getUtcpWokeyid()
                + ", requestedby: " + utcp.getUtcpRequestedby()
                + ", utilitymstid: " + utcp.getUtcpUtilitymstid());

        String jsonPayload = utcp.toJsonManual();
        CommonMessage.debugMsg("UtilityCost saveUtilityCost Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("UtilityCost saveUtilityCost Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("UtilityCost saveUtilityCost Response: " + jsonResponse);

        BAL_WomTlUtilitycostplan result = BAL_WomTlUtilitycostplan.fromJson(jsonResponse);
        CommonMessage.debugMsg("UtilityCost saveUtilityCost Result: " + result);
        return result;
    }
    // =========================================================================
    //  SINGLE SAVE METHOD (Other Cost Estimate)  –  INSERT when no row
    //  matches the composite key (woid+requestedby+othercostmstid), UPDATE
    //  otherwise. Spring's BdmServiceImpl.saveOtherCost() decides insert vs
    //  update by looking that key up.
    // =========================================================================
    public BAL_WomTlOthercostplan saveOtherCost(BAL_WomTlOthercostplan otcp) throws Exception {

        String apiUrl = "/bdm/other-cost/save";

        CommonMessage.debugMsg("OtherCost saveOtherCost – woid: " + otcp.getOtcpWoid()
                + ", requestedby: " + otcp.getOtcpRequestedby()
                + ", othercostmstid: " + otcp.getOtcpOthercostmstid());

        String jsonPayload = otcp.toJsonManual();
        CommonMessage.debugMsg("OtherCost saveOtherCost Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("OtherCost saveOtherCost Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("OtherCost saveOtherCost Response: " + jsonResponse);

        BAL_WomTlOthercostplan result = BAL_WomTlOthercostplan.fromJson(jsonResponse);
        CommonMessage.debugMsg("OtherCost saveOtherCost Result: " + result);
        return result;
    }
    
    // =========================================================================
    //  SINGLE SAVE METHOD (Spare Cost Actual)  –  INSERT when no row matches
    //  the composite key (woid+requestedby+sparesid), UPDATE otherwise.
    //  Spring's BdmServiceImpl.saveSpareCostActual() decides insert vs
    //  update by looking that key up.
    // =========================================================================
    public BAL_WomTlSparecostactual saveSpareCostActual(BAL_WomTlSparecostactual wsca) throws Exception {

        String apiUrl = "/bdm/spare-cost-actual/save";

        CommonMessage.debugMsg("SpareCostActual saveSpareCostActual – woid: " + wsca.getWscaWoid()
                + ", requestedby: " + wsca.getWscaRequestedby()
                + ", sparesid: " + wsca.getWscaSparesid());

        String jsonPayload = wsca.toJsonManual();
        CommonMessage.debugMsg("SpareCostActual saveSpareCostActual Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("SpareCostActual saveSpareCostActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("SpareCostActual saveSpareCostActual Response: " + jsonResponse);

        BAL_WomTlSparecostactual result = BAL_WomTlSparecostactual.fromJson(jsonResponse);
        CommonMessage.debugMsg("SpareCostActual saveSpareCostActual Result: " + result);
        return result;
    }
    public BAL_WomTlUtilitycostactual saveUtilityCostActual(BAL_WomTlUtilitycostactual utca) throws Exception {

        String apiUrl = "/bdm/utility-cost-actual/save";

        CommonMessage.debugMsg("UtilityCostActual saveUtilityCostActual – wokeyid: " + utca.getUtcaWokeyid()
                + ", requestedby: " + utca.getUtcaRequestedby()
                + ", utilitymstid: " + utca.getUtcaUtilitymstid());

        String jsonPayload = utca.toJsonManual();
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("UtilityCostActual saveUtilityCostActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("UtilityCostActual saveUtilityCostActual Response: " + jsonResponse);

        return BAL_WomTlUtilitycostactual.fromJson(jsonResponse);
    }
    
    public BAL_WomTlOthercostactual saveOtherCostActual(BAL_WomTlOthercostactual otcd) throws Exception {

        String apiUrl = "/bdm/other-cost-actual/save";

        CommonMessage.debugMsg("OtherCostActual saveOtherCostActual – woid: " + otcd.getOtcdWoid()
                + ", requestedby: " + otcd.getOtcdRequestedby()
                + ", othercostmstid: " + otcd.getOtcdOthercostmstid());

        String jsonPayload = otcd.toJsonManual();
        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("OtherCostActual saveOtherCostActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("OtherCostActual saveOtherCostActual Response: " + jsonResponse);

        return BAL_WomTlOthercostactual.fromJson(jsonResponse);
    }
    // =========================================================================
    //  SINGLE SAVE METHOD (Service Cost Actual)  –  INSERT when no row
    //  matches the composite key (woid+serviceid), UPDATE otherwise.
    //  Spring's BdmServiceImpl.saveServiceCostActual() decides insert vs
    //  update by looking that key up.
    // =========================================================================
    public BAL_WomTlServicecostactual saveServiceCostActual(BAL_WomTlServicecostactual svca) throws Exception {

        String apiUrl = "/bdm/service-cost-actual/save";

        CommonMessage.debugMsg("ServiceCostActual saveServiceCostActual – woid: " + svca.getSvcaWoid()
                + ", serviceid: " + svca.getSvcaServiceid());

        String jsonPayload = svca.toJsonManual();
        CommonMessage.debugMsg("ServiceCostActual saveServiceCostActual Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("ServiceCostActual saveServiceCostActual Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("ServiceCostActual saveServiceCostActual Response: " + jsonResponse);

        BAL_WomTlServicecostactual result = BAL_WomTlServicecostactual.fromJson(jsonResponse);
        CommonMessage.debugMsg("ServiceCostActual saveServiceCostActual Result: " + result);
        return result;
    }
    // =========================================================================
    //  DELETE  (Manpower Cost Estimate)  –  DELETE by composite key
    //  (woid+manpowerid+skillid). No request body; params on the query string.
    // =========================================================================
    public void deleteManpowerCost(String woid, String manpowerid, String skillid) throws Exception {

        String apiUrl = "/bdm/manpower-cost/delete?woid=" + woid
                + "&manpowerid=" + manpowerid
                + "&skillid=" + skillid;

        CommonMessage.debugMsg("ManpowerCost deleteManpowerCost – woid: " + woid
                + ", manpowerid: " + manpowerid + ", skillid: " + skillid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("ManpowerCost deleteManpowerCost Status: " + res.getStatusCode());
    }
    public void deleteManpowerCost(BAL_WomTlManpowercostplan mpcp) throws Exception {
        deleteManpowerCost(mpcp.getMpcpWoid(), mpcp.getMpcpManpowerid(), mpcp.getMpcpSkillid());
    }
    // =========================================================================
    //  DELETE  (Manpower Cost Actual)  –  DELETE by composite key
    //  (maintwoid+manpowerid+skillid). No request body; params on the query string.
    // =========================================================================
    public void deleteManpowerCostActual(String maintwoid, String manpowerid, String skillid) throws Exception {

        String apiUrl = "/bdm/manpower-cost-actual/delete?maintwoid=" + maintwoid
                + "&manpowerid=" + manpowerid
                + "&skillid=" + skillid;

        CommonMessage.debugMsg("ManpowerCostActual deleteManpowerCostActual – maintwoid: " + maintwoid
                + ", manpowerid: " + manpowerid + ", skillid: " + skillid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("ManpowerCostActual deleteManpowerCostActual Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Manpower Cost Actual)  –  overload taking the model object,
    //  matching the call style of saveManpowerCostActual(). Extracts
    //  maintwoid/manpowerid/skillid internally.
    // =========================================================================
    public void deleteManpowerCostActual(BAL_WomTlManpowercostactual mpcs) throws Exception {
        deleteManpowerCostActual(mpcs.getMpcsMaintwoid(), mpcs.getMpcsManpowerid(), mpcs.getMpcsSkillid());
    }
    
    // =========================================================================
    //  DELETE  (Spare Cost Estimate)  –  DELETE by composite key
    //  (woid+requestedby+sparesid). No request body; params on the query string.
    // =========================================================================
    public void deleteSpareCost(String woid, String requestedby, String sparesid) throws Exception {

        String apiUrl = "/bdm/spare-cost/delete?woid=" + woid
                + "&requestedby=" + requestedby
                + "&sparesid=" + sparesid;

        CommonMessage.debugMsg("SpareCost deleteSpareCost – woid: " + woid
                + ", requestedby: " + requestedby + ", sparesid: " + sparesid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("SpareCost deleteSpareCost Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Spare Cost Estimate)  –  overload taking the model object,
    //  matching the call style of saveSpareCost(). Extracts
    //  woid/requestedby/sparesid internally.
    // =========================================================================
    public void deleteSpareCost(BAL_WomTlSparecostplan wscp) throws Exception {
        deleteSpareCost(wscp.getWscpWoid(), wscp.getWscpRequestedby(), wscp.getWscpSparesid());
    }
    
    // =========================================================================
    //  DELETE  (Spare Cost Actual)  –  DELETE by composite key
    //  (woid+requestedby+sparesid). No request body; params on the query string.
    // =========================================================================
    public void deleteSpareCostActual(String woid, String requestedby, String sparesid) throws Exception {

        String apiUrl = "/bdm/spare-cost-actual/delete?woid=" + woid
                + "&requestedby=" + requestedby
                + "&sparesid=" + sparesid;

        CommonMessage.debugMsg("SpareCostActual deleteSpareCostActual – woid: " + woid
                + ", requestedby: " + requestedby + ", sparesid: " + sparesid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("SpareCostActual deleteSpareCostActual Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Spare Cost Actual)  –  overload taking the model object,
    //  matching the call style of saveSpareCostActual(). Extracts
    //  woid/requestedby/sparesid internally.
    // =========================================================================
    public void deleteSpareCostActual(BAL_WomTlSparecostactual wsca) throws Exception {
        deleteSpareCostActual(wsca.getWscaWoid(), wsca.getWscaRequestedby(), wsca.getWscaSparesid());
    }
    
    // =========================================================================
    //  DELETE  (Service Cost Estimate)  –  DELETE by composite key
    //  (woid+serviceid). No request body; params on the query string.
    // =========================================================================
    public void deleteServiceCost(String woid, String serviceid) throws Exception {

        String apiUrl = "/bdm/service-cost/delete?woid=" + woid + "&serviceid=" + serviceid;

        CommonMessage.debugMsg("ServiceCost deleteServiceCost – woid: " + woid + ", serviceid: " + serviceid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("ServiceCost deleteServiceCost Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Service Cost Estimate)  –  overload taking the model object,
    //  matching the call style of saveServiceCost(). Extracts woid/serviceid
    //  internally.
    // =========================================================================
    public void deleteServiceCost(BAL_WomTlServicecostplan svcp) throws Exception {
        deleteServiceCost(svcp.getSvcpWoid(), svcp.getSvcpServiceid());
    }

    // =========================================================================
    //  DELETE  (Service Cost Actual)  –  DELETE by composite key
    //  (woid+serviceid). No request body; params on the query string.
    // =========================================================================
    public void deleteServiceCostActual(String woid, String serviceid) throws Exception {

        String apiUrl = "/bdm/service-cost-actual/delete?woid=" + woid + "&serviceid=" + serviceid;

        CommonMessage.debugMsg("ServiceCostActual deleteServiceCostActual – woid: " + woid + ", serviceid: " + serviceid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("ServiceCostActual deleteServiceCostActual Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Service Cost Actual)  –  overload taking the model object,
    //  matching the call style of saveServiceCostActual(). Extracts
    //  woid/serviceid internally.
    // =========================================================================
    public void deleteServiceCostActual(BAL_WomTlServicecostactual svca) throws Exception {
        deleteServiceCostActual(svca.getSvcaWoid(), svca.getSvcaServiceid());
    }
    
    // =========================================================================
    //  DELETE  (Utility Cost Estimate)  –  DELETE by composite key
    //  (wokeyid+requestedby+utilitymstid). No request body; params on the query string.
    // =========================================================================
    public void deleteUtilityCost(String wokeyid, String requestedby, String utilitymstid) throws Exception {

        String apiUrl = "/bdm/utility-cost/delete?wokeyid=" + wokeyid
                + "&requestedby=" + requestedby
                + "&utilitymstid=" + utilitymstid;

        CommonMessage.debugMsg("UtilityCost deleteUtilityCost – wokeyid: " + wokeyid
                + ", requestedby: " + requestedby + ", utilitymstid: " + utilitymstid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("UtilityCost deleteUtilityCost Status: " + res.getStatusCode());
    }

    // =========================================================================
    //  DELETE  (Utility Cost Estimate)  –  overload taking the model object,
    //  matching the call style of saveUtilityCost(). Extracts
    //  wokeyid/requestedby/utilitymstid internally.
    // =========================================================================
    public void deleteUtilityCost(BAL_WomTlUtilitycostplan utcp) throws Exception {
        deleteUtilityCost(utcp.getUtcpWokeyid(), utcp.getUtcpRequestedby(), utcp.getUtcpUtilitymstid());
    }
    // =========================================================================
    //  DELETE  (Utility Cost Actual)  –  DELETE by composite key
    //  (wokeyid+requestedby+utilitymstid).
    // =========================================================================
    public void deleteUtilityCostActual(String wokeyid, String requestedby, String utilitymstid) throws Exception {

        String apiUrl = "/bdm/utility-cost-actual/delete?wokeyid=" + wokeyid
                + "&requestedby=" + requestedby
                + "&utilitymstid=" + utilitymstid;

        CommonMessage.debugMsg("UtilityCostActual deleteUtilityCostActual – wokeyid: " + wokeyid
                + ", requestedby: " + requestedby + ", utilitymstid: " + utilitymstid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("UtilityCostActual deleteUtilityCostActual Status: " + res.getStatusCode());
    }

    public void deleteUtilityCostActual(BAL_WomTlUtilitycostactual utca) throws Exception {
        deleteUtilityCostActual(utca.getUtcaWokeyid(), utca.getUtcaRequestedby(), utca.getUtcaUtilitymstid());
    }

    // =========================================================================
    //  DELETE  (Other Cost Estimate)  –  DELETE by composite key
    //  (woid+requestedby+othercostmstid).
    // =========================================================================
    public void deleteOtherCost(String woid, String requestedby, String othercostmstid) throws Exception {

        String apiUrl = "/bdm/other-cost/delete?woid=" + woid
                + "&requestedby=" + requestedby
                + "&othercostmstid=" + othercostmstid;

        CommonMessage.debugMsg("OtherCost deleteOtherCost – woid: " + woid
                + ", requestedby: " + requestedby + ", othercostmstid: " + othercostmstid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("OtherCost deleteOtherCost Status: " + res.getStatusCode());
    }

    public void deleteOtherCost(BAL_WomTlOthercostplan otcp) throws Exception {
        deleteOtherCost(otcp.getOtcpWoid(), otcp.getOtcpRequestedby(), otcp.getOtcpOthercostmstid());
    }

    // =========================================================================
    //  DELETE  (Other Cost Actual)  –  DELETE by composite key
    //  (woid+requestedby+othercostmstid).
    // =========================================================================
    public void deleteOtherCostActual(String woid, String requestedby, String othercostmstid) throws Exception {

        String apiUrl = "/bdm/other-cost-actual/delete?woid=" + woid
                + "&requestedby=" + requestedby
                + "&othercostmstid=" + othercostmstid;

        CommonMessage.debugMsg("OtherCostActual deleteOtherCostActual – woid: " + woid
                + ", requestedby: " + requestedby + ", othercostmstid: " + othercostmstid);

        HttpResponse res = api.makeAuthRequest(apiUrl, "DELETE", null);
        CommonMessage.debugMsg("OtherCostActual deleteOtherCostActual Status: " + res.getStatusCode());
    }

    public void deleteOtherCostActual(BAL_WomTlOthercostactual otcd) throws Exception {
        deleteOtherCostActual(otcd.getOtcdWoid(), otcd.getOtcdRequestedby(), otcd.getOtcdOthercostmstid());
    }
    
    // =========================================================================
    //  GET  (BDM Master)  –  fetch by bdms_keyid.
    //  Spring's BdmServiceImpl.getBdmMaster() backs GET /api/bdm/master/{keyid}.
    // =========================================================================
    public BAL_BdmTlMst getBdmMaster(String keyid) throws Exception {

        String apiUrl = "/bdm/master/" + keyid;

        CommonMessage.debugMsg("BDM getBdmMaster url :: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("BDM getBdmMaster status :: " + res.getStatusCode());
        CommonMessage.debugMsg("BDM getBdmMaster body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("BDM Master fetch failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject masterJson = JSONObject.fromObject(body);
        masterJson = sanitizeDates(masterJson);

        BAL_BdmTlMst result = BAL_BdmTlMst.fromJson(masterJson.toString());
        CommonMessage.debugMsg("BDM getBdmMaster Result: " + result);
        return result;
    }

    // =========================================================================
    //  GET  (BDM Detail)  –  fetch by the master's bdms_keyid
    //  (bdan_bdms_keyid is a foreign key, not the detail's own keyid).
    //  Spring's BdmServiceImpl.getBdmDetailByMasterKeyid() backs
    //  GET /api/bdm/detail/{bdmsKeyid}.
    // =========================================================================
    public BAL_BdmTlDtl getBdmDetail(String bdmsKeyid) throws Exception {

        String apiUrl = "/bdm/detail/" + bdmsKeyid;

        CommonMessage.debugMsg("BDM getBdmDetail url :: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("BDM getBdmDetail status :: " + res.getStatusCode());
        CommonMessage.debugMsg("BDM getBdmDetail body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("BDM Detail fetch failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject detailJson = JSONObject.fromObject(body);
        detailJson = sanitizeDates(detailJson);

        BAL_BdmTlDtl result = BAL_BdmTlDtl.fromJson(detailJson.toString());
        CommonMessage.debugMsg("BDM getBdmDetail Result: " + result);
        return result;
    }

    // =========================================================================
    //  GET  (WOM Work Order)  –  fetch by woms_keyid.
    //  Spring's BdmServiceImpl.getWorkOrder() backs GET /api/bdm/workorder/{keyid}.
    // =========================================================================
    public WomTlWomst getWorkOrder(String keyid) throws Exception {

        String apiUrl = "/bdm/workorder/" + keyid;

        CommonMessage.debugMsg("BDM getWorkOrder url :: " + apiUrl);

        HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);

        CommonMessage.debugMsg("BDM getWorkOrder status :: " + res.getStatusCode());
        CommonMessage.debugMsg("BDM getWorkOrder body   :: " + res.getBody());

        int code = res.getStatusCode();
        String body = res.getBody();
        if (body == null) body = "";

        if (code < 200 || code >= 300) {
            throw new IllegalStateException("WOM Work Order fetch failed HTTP=" + code + " body=[" + body + "]");
        }

        JSONObject womJson = JSONObject.fromObject(body);
        womJson = sanitizeDates(womJson);

        WomTlWomst result = WomTlWomst.fromJson(womJson.toString());
        CommonMessage.debugMsg("BDM getWorkOrder Result: " + result);
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
    
    public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog log) throws Exception {

        String apiUrl = "/bdm/cmmsave";

        CommonMessage.debugMsg("saveCommTxt – mode: " +
            (log.getWcmlKeyid() == null || log.getWcmlKeyid().trim().isEmpty() ? "INSERT" : "UPDATE")
            + ", keyid: " + log.getWcmlKeyid());

        // ✅ Set default values if null before building JSON
        if (log.getWcmlLevel() == null || log.getWcmlLevel().trim().isEmpty()) {
            log.setWcmlLevel("1");
        }
        if (log.getWcmlActive() == null || log.getWcmlActive().trim().isEmpty()) {
            log.setWcmlActive("Y");
        }
        if (log.getWcmlDisplayorderno() == null || log.getWcmlDisplayorderno().trim().isEmpty()) {
            log.setWcmlDisplayorderno("0");
        }
        if (log.getWcmlDate() == null || log.getWcmlDate().trim().isEmpty()) {
            log.setWcmlDate(LocalDateTime.now().toString());
        }
        if (log.getWcmlCreatedon() == null || log.getWcmlCreatedon().trim().isEmpty()) {
            log.setWcmlCreatedon(LocalDateTime.now().toString());
        }
        if (log.getWcmlModifiedon() == null || log.getWcmlModifiedon().trim().isEmpty()) {
            log.setWcmlModifiedon(LocalDateTime.now().toString());
        }

        String jsonPayload = commToJson(log);
        CommonMessage.debugMsg("saveCommTxt Json :: " + jsonPayload);

        HttpResponse res = api.makeAuthRequest(apiUrl, "POST", jsonPayload);
        CommonMessage.debugMsg("saveCommTxt Status: " + res.getStatusCode());

        String jsonResponse = res.getBody();
        CommonMessage.debugMsg("saveCommTxt Response: " + jsonResponse);

        WomTlCommunicationlog result = commFromJson(jsonResponse);
        CommonMessage.debugMsg("saveCommTxt Result keyid: " + result.getWcmlKeyid());

        return result;
    }


    // =========================================================================
    //  BUILD REQUEST JSON for Communication Log
    // =========================================================================
    private String commToJson(WomTlCommunicationlog log) {
        StringBuilder str = new StringBuilder();
        str.append("{");

        str.append("\"keyid\":").append(log.getWcmlKeyid() == null ? "null" : "\"" + log.getWcmlKeyid() + "\"");
        str.append(",\"wonumber\":").append(log.getWcmlWonumber() == null ? "null" : "\"" + log.getWcmlWonumber() + "\"");
        str.append(",\"date\":").append(log.getWcmlDate() == null ? "null" : "\"" + log.getWcmlDate() + "\"");
        str.append(",\"communicationtext\":").append(log.getWcmlCommunicationtext() == null ? "null" : "\"" + log.getWcmlCommunicationtext() + "\"");
        str.append(",\"level\":").append(log.getWcmlLevel() == null ? "\"1\"" : "\"" + log.getWcmlLevel() + "\""); // ✅ default "1"
        str.append(",\"enteredby\":").append(log.getWcmlEnteredby() == null ? "null" : "\"" + log.getWcmlEnteredby() + "\"");
        str.append(",\"displayorderno\":").append(log.getWcmlDisplayorderno() == null ? "0" : log.getWcmlDisplayorderno());
        str.append(",\"active\":").append(log.getWcmlActive() == null ? "\"Y\"" : "\"" + log.getWcmlActive() + "\"");
        str.append(",\"createdby\":").append(log.getWcmlCreatedby() == null ? "null" : "\"" + log.getWcmlCreatedby() + "\"");
        str.append(",\"createdon\":").append(log.getWcmlCreatedon() == null ? "null" : "\"" + log.getWcmlCreatedon() + "\"");
        str.append(",\"modifiedon\":").append(log.getWcmlModifiedon() == null ? "null" : "\"" + log.getWcmlModifiedon() + "\"");

        str.append("}");
        return str.toString();
    }

    // =========================================================================
    //  PARSE RESPONSE JSON  →  wom_tl_communicationlog
    // =========================================================================
    private WomTlCommunicationlog commFromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);

        WomTlCommunicationlog log = new WomTlCommunicationlog();

        log.setWcmlKeyid(obj.optString("keyid", null));
        log.setWcmlWonumber(obj.optString("wonumber", null));
        log.setWcmlDate(obj.optString("date", null));
        log.setWcmlCommunicationtext(obj.optString("communicationtext", null));
        log.setWcmlLevel(obj.optString("level", "1"));        // ✅ default "1"
        log.setWcmlEnteredby(obj.optString("enteredby", null));
        log.setWcmlDisplayorderno(obj.optString("displayorderno", "0"));
        log.setWcmlActive(obj.optString("active", "Y"));
        log.setWcmlCreatedby(obj.optString("createdby", null));
        log.setWcmlCreatedon(obj.optString("createdon", null));
        log.setWcmlModifiedon(obj.optString("modifiedon", null));

        return log;
    }
    
    
    
    public List<String[]> getCommText(String bdId) {
        try {
            String apiUrl = "/bdm/cmmgettext/" + bdId;
            CommonMessage.debugMsg("BDM getCommText – bdId: " + bdId);

            HttpResponse res = api.makeAuthRequest(apiUrl, "GET", null);
            CommonMessage.debugMsg("BDM getCommText Status: " + res.getStatusCode());

            String jsonResponse = res.getBody();
            CommonMessage.debugMsg("BDM getCommText Response: " + jsonResponse);

            return fromJsonGridRows(jsonResponse, commTextColumnOrder());
        } catch (Exception e) {
            CommonMessage.debugMsg("BDM getCommText Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    private String[] commTextColumnOrder() {
        return new String[] {
            "wcml_keyid",
            "wcml_wonumber",
            "wcml_date_str",
            "wcml_time_str",
            "wcml_communicationtext",
            "dept_name",
            "empm_name"
        };
    
    }
}