package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlPhenomenaMapping;
import com.akranta.tpm.service.QtmTlPhenomenaMappingService;
import com.akranta.tpm.service.impl.QtmTlPhenomenaMappingServiceImpl;
import com.akranta.tpm.utils.CommonMessage;

import net.sf.json.JSONObject;


public class QtmTlPhenomenaMappingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private QtmTlPhenomenaMappingService phenomenaMappingService;
    
    public QtmTlPhenomenaMappingServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            process(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            process(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------------------------------------- router
    private void process(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession httpSession = request.getSession(false);
        String action = UIUtils.getActionPart(request);

        phenomenaMappingService = (QtmTlPhenomenaMappingServiceImpl)
                UIUtils.getServiceObject(request, "QtmTlPhenomenaMappingServiceImpl");

        phenomenaMappingService.QtmTlPhenomenaMappingServiceImplJwt(
                (String) (httpSession.getAttribute("tpmjwttoken") == null
                        ? "" : httpSession.getAttribute("tpmjwttoken")));

        // ------------------------------------------------ open form
        if (action.equals("phenomenadmtmapping_input.phmfass")) {
            String mode  = request.getParameter("mode");
            String keyid = request.getParameter("keyid");
            CommonMessage.debugMsg("PhenomenaMapping_input mode:" + mode + " keyid:" + keyid);

            QtmTlPhenomenaMapping phenomenaMapping = new QtmTlPhenomenaMapping();
            AdmTlUsermst user = UIUtils.getLoginUser(request);
            phenomenaMapping.setPhnmCreatedby(user.getUsrm_ccno());

            if ("modify".equals(mode) || "view".equals(mode)) {
                request.setAttribute("keyid", keyid);
                request.setAttribute("phenomenaMapping", phenomenaMapping);
                httpSession.setAttribute("phenomenaMapping", phenomenaMapping);
            }

            request.setAttribute("mode", mode);
            UIUtils.forwardRequest(request, response, "/pages/phenomenaDmtMapping.jsp");
        }

        // ------------------------------------------------ get column definitions
        else if (action.equals("PhenomenaDmtMapping_getCol.phmfass")) {

            PrintWriter out = response.getWriter();
            CommonFilter commonFilter = new CommonFilter();
            JSONObject jsonObject = new JSONObject();

            try {
                FilterValues.getCommonFilters(request, commonFilter);

                // FIX: read flid and qphmKeyid params.
                // CommonFilter has no setQphmKeyid(), so we piggyback qphmKeyid
                // onto the flid field using a pipe separator: "FNL000000001|QPH0000052"
                // The DAO splits this before building condParms.
                String flid      = request.getParameter("flid");
                String qphmKeyid = request.getParameter("qphmKeyid");
                String flidCarrier = (flid != null ? flid.trim() : "")
                        + (qphmKeyid != null && !qphmKeyid.trim().isEmpty()
                                ? "|" + qphmKeyid.trim() : "");
                commonFilter.setFlid(flidCarrier);

                CommonMessage.debugMsg("PhenomenaDmtMapping_getCol flid=[" + flid
                        + "] qphmKeyid=[" + qphmKeyid + "]");

                List<String[]> gridList = phenomenaMappingService.getPhenomenaDmtList(commonFilter);

                JqGridTableModel jqGridTableModel = new JqGridTableModel();
                GridColModel gridColModel = new GridColModel();
                gridColModel.setHeaderNum(1);
                jqGridTableModel.setSortable(false);
                jqGridTableModel.setTableButton(false);
                jqGridTableModel.setEnableFilter(false);
                jqGridTableModel.setRowNumbers(true);

                String[] colHeaderHead = gridList.get(0);
                String[] colHeader     = gridList.get(1);
                List<String[]> headers = new ArrayList<>();
                headers.add(colHeader);

                jsonObject = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);

                // Attach custom checkbox formatter to ISSELECTED column
                if (jsonObject.has("colModel")) {
                    net.sf.json.JSONArray colModel = jsonObject.getJSONArray("colModel");
                    for (int i = 0; i < colModel.length(); i++) {
                        JSONObject col = colModel.getJSONObject(i);
                        if ("ISSELECTED".equalsIgnoreCase(col.optString("name"))) {
                            col.put("formatter",  "checkboxFormatter");
                            col.put("formattype", "custom");
                            col.put("sortable",   false);
                            col.put("align",      "center");
                            col.put("width",      80);
                            break;
                        }
                    }
                    jsonObject.put("colModel", colModel);
                }

                jsonObject.set("tableWidth",  "100%%");
                jsonObject.set("tableHeight", "45%%");

                // Cache column model in session for getData reuse
                httpSession.removeAttribute("PhenomenaMappingListColModel");
                httpSession.setAttribute("PhenomenaMappingListColModel", jsonObject);

            } catch (Exception e) {
                CommonMessage.debugMsg("PhenomenaDmtMapping_getCol error: " + e.getMessage());
            }

            out.println(jsonObject);
        }

        // ------------------------------------------------ get grid data
        else if (action.equals("PhenomenaDmtMapping_getData.phmfass")) {
            try {
                CommonFilter commonFilter = populateCommonFilter(
                        request, "PhenomenaMappingListCommonFilter", true);
                FilterValues.getCommonFilters(request, commonFilter);

                // FIX: read flid and qphmKeyid, carry both in the flid field
                // using pipe separator so no new field is needed on CommonFilter.
                String flid      = request.getParameter("flid");
                String qphmKeyid = request.getParameter("qphmKeyid");
                String flidCarrier = (flid != null ? flid.trim() : "")
                        + (qphmKeyid != null && !qphmKeyid.trim().isEmpty()
                                ? "|" + qphmKeyid.trim() : "");
                commonFilter.setFlid(flidCarrier);

                CommonMessage.debugMsg("PhenomenaDmtMapping_getData flid=[" + flid
                        + "] qphmKeyid=[" + qphmKeyid + "]");

                List<String[]> gridList = phenomenaMappingService.getPhenomenaDmtList(commonFilter);
                PrintWriter out = response.getWriter();

                JSONObject result = UIUtils.convertToJqGridTableObject(
                        gridList, request, 2, 0, commonFilter.getTotalRecordCnt());
                out.println(result);

            } catch (Exception e) {
                CommonMessage.debugMsg("PhenomenaDmtMapping_getData error: " + e.getMessage());
            }
        }

        // ------------------------------------------------ save (insert / update)
        else if (action.equals("phenomenadmtmapping_save.phmfass")) {
            savePhenomenaMapping(request, response);
        }
    }

    // --------------------------------------------------------------- save
	/*
	 * private void savePhenomenaMapping(HttpServletRequest request,
	 * HttpServletResponse response) throws ValidationExceptions,
	 * BusinessApplicationExceptions, Exception {
	 * 
	 * HttpSession httpSession = request.getSession(false); ServletOutputStream out
	 * = response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request);
	 * 
	 * try { if (httpSession != null && user != null) {
	 * 
	 * // Read the array of selected DMT flids String[] selectedDmtFlids =
	 * request.getParameterValues("selectedDmtFlids");
	 * 
	 * if (selectedDmtFlids == null || selectedDmtFlids.length == 0) { JSONObject
	 * err = new JSONObject(); err.put("tpmException", "No DMT selected");
	 * out.print(err.toString()); return; }
	 * 
	 * QtmTlPhenomenaMapping baseMapping = new QtmTlPhenomenaMapping(); baseMapping
	 * = (QtmTlPhenomenaMapping) UIUtils.setBeanProperties((Object) baseMapping,
	 * request);
	 * 
	 * // Fallback for FLID from widget String baseFlidParam =
	 * request.getParameter("cmbPhnmFlid"); if (baseFlidParam != null &&
	 * !baseFlidParam.trim().isEmpty()) {
	 * baseMapping.setPhnmSectFlid(baseFlidParam.trim()); }
	 * 
	 * baseMapping.setPhnmCreatedby(user.getUsrm_ccno());
	 * 
	 * QtmTlPhenomenaMapping existPhenomenaMapping = (QtmTlPhenomenaMapping)
	 * httpSession.getAttribute("phenomenaMapping");
	 * 
	 * String lastKeyId = "";
	 * 
	 * // ---- Loop: one INSERT per selected DMT flid ---- for (String dmtFlid :
	 * selectedDmtFlids) { QtmTlPhenomenaMapping rowMapping = new
	 * QtmTlPhenomenaMapping();
	 * 
	 * // Copy base fields
	 * rowMapping.setPhnmQphmKeyid(baseMapping.getPhnmQphmKeyid());
	 * rowMapping.setPhnmCreatedby(baseMapping.getPhnmCreatedby());
	 * rowMapping.setPhnmTempfield1(baseMapping.getPhnmTempfield1());
	 * rowMapping.setPhnmTempfield2(baseMapping.getPhnmTempfield2());
	 * rowMapping.setPhnmTempfield3(baseMapping.getPhnmTempfield3());
	 * rowMapping.setPhnmActive("Y");
	 * 
	 * // Each row gets its own DMT flid rowMapping.setPhnmSectFlid(dmtFlid.trim());
	 * 
	 * if (!UIUtils.isValidKeyId(baseMapping.getPhnmKeyid())) { // INSERT — service
	 * generates a new sequence per call existPhenomenaMapping =
	 * phenomenaMappingService.create( rowMapping, existPhenomenaMapping); } else {
	 * // UPDATE path (if needed per row) existPhenomenaMapping =
	 * phenomenaMappingService.update( rowMapping, existPhenomenaMapping); }
	 * 
	 * lastKeyId = existPhenomenaMapping.getPhnmKeyid();
	 * CommonMessage.debugMsg("Saved DMT flid=[" + dmtFlid + "] keyId=[" + lastKeyId
	 * + "]"); }
	 * 
	 * JSONObject successData = new JSONObject(); successData.put("msg",
	 * "Data Saved Successfully (" + selectedDmtFlids.length + " records)");
	 * successData.put("keyId", lastKeyId);
	 * 
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData); returnData.put("GenKeyid", lastKeyId);
	 * returnData.put("formClear", false);
	 * 
	 * out.print(returnData.toString()); }
	 * 
	 * } catch (ValidationExceptions e) { e.printStackTrace();
	 * out.print(UIUtils.validationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (BusinessApplicationExceptions e) {
	 * e.printStackTrace();
	 * out.print(UIUtils.businessValidationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (Exception e) { e.printStackTrace();
	 * JSONObject err = new JSONObject(); err.put("tpmException", "Data Not Saved: "
	 * + e.getMessage()); out.print(err.toString()); } }
	 */
    
    
	/*
	 * private void savePhenomenaMapping(HttpServletRequest request,
	 * HttpServletResponse response) throws ValidationExceptions,
	 * BusinessApplicationExceptions, Exception {
	 * 
	 * HttpSession httpSession = request.getSession(false); ServletOutputStream out
	 * = response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request);
	 * 
	 * try { if (httpSession != null && user != null) {
	 * 
	 * // Read selected SECTION keyids (one per checked checkbox) String[]
	 * selectedSectKeyids = request.getParameterValues("selectedDmtSectKeyids");
	 * 
	 * if (selectedSectKeyids == null || selectedSectKeyids.length == 0) {
	 * JSONObject err = new JSONObject(); err.put("tpmException",
	 * "No DMT selected"); out.print(err.toString()); return; }
	 * 
	 * // Read base fields from form QtmTlPhenomenaMapping baseMapping = new
	 * QtmTlPhenomenaMapping(); baseMapping = (QtmTlPhenomenaMapping)
	 * UIUtils.setBeanProperties((Object) baseMapping, request);
	 * baseMapping.setPhnmCreatedby(user.getUsrm_ccno());
	 * 
	 * QtmTlPhenomenaMapping existPhenomenaMapping = (QtmTlPhenomenaMapping)
	 * httpSession.getAttribute("phenomenaMapping");
	 * 
	 * String lastKeyId = ""; int savedCount = 0;
	 * 
	 * // One INSERT per selected section keyid for (String sectKeyid :
	 * selectedSectKeyids) {
	 * 
	 * // Guard against null / empty / "undefined" from JS if (sectKeyid == null ||
	 * sectKeyid.trim().isEmpty() || "undefined".equalsIgnoreCase(sectKeyid.trim()))
	 * { CommonMessage.debugMsg("Skipping invalid sectKeyid=[" + sectKeyid + "]");
	 * continue; }
	 * 
	 * QtmTlPhenomenaMapping rowMapping = new QtmTlPhenomenaMapping();
	 * 
	 * // Copy shared fields from base
	 * rowMapping.setPhnmQphmKeyid(baseMapping.getPhnmQphmKeyid());
	 * rowMapping.setPhnmCreatedby(baseMapping.getPhnmCreatedby());
	 * rowMapping.setPhnmTempfield1(baseMapping.getPhnmTempfield1());
	 * rowMapping.setPhnmTempfield2(baseMapping.getPhnmTempfield2());
	 * rowMapping.setPhnmTempfield3(baseMapping.getPhnmTempfield3());
	 * rowMapping.setPhnmActive("Y");
	 * 
	 * // FIX: save sect_keyid into phnm_sect_flid column (only ONE call) // SP
	 * checks: pm.phnm_sect_flid = sec.sect_keyid
	 * rowMapping.setPhnmSectFlid(sectKeyid.trim());
	 * 
	 * CommonMessage.debugMsg("Saving sectKeyid=[" + sectKeyid.trim() +
	 * "] qphmKeyid=[" + baseMapping.getPhnmQphmKeyid() + "]");
	 * 
	 * if (!UIUtils.isValidKeyId(baseMapping.getPhnmKeyid())) { // INSERT
	 * existPhenomenaMapping = phenomenaMappingService.create( rowMapping,
	 * existPhenomenaMapping); } else { // UPDATE existPhenomenaMapping =
	 * phenomenaMappingService.update( rowMapping, existPhenomenaMapping); }
	 * 
	 * lastKeyId = existPhenomenaMapping.getPhnmKeyid(); savedCount++;
	 * CommonMessage.debugMsg("Saved sectKeyid=[" + sectKeyid.trim() + "] keyId=[" +
	 * lastKeyId + "]"); }
	 * 
	 * if (savedCount == 0) { JSONObject err = new JSONObject();
	 * err.put("tpmException", "No valid DMT selected"); out.print(err.toString());
	 * return; }
	 * 
	 * JSONObject successData = new JSONObject(); successData.put("msg",
	 * "Data Saved Successfully (" + savedCount + " records)");
	 * successData.put("keyId", lastKeyId);
	 * 
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData); returnData.put("GenKeyid", lastKeyId);
	 * returnData.put("formClear", false);
	 * 
	 * out.print(returnData.toString()); }
	 * 
	 * } catch (ValidationExceptions e) { e.printStackTrace();
	 * out.print(UIUtils.validationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (BusinessApplicationExceptions e) {
	 * e.printStackTrace();
	 * out.print(UIUtils.businessValidationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (Exception e) { e.printStackTrace();
	 * JSONObject err = new JSONObject(); err.put("tpmException", "Data Not Saved: "
	 * + e.getMessage()); out.print(err.toString()); } }
	 */
    
	/*
	 * private void savePhenomenaMapping(HttpServletRequest request,
	 * HttpServletResponse response) throws ValidationExceptions,
	 * BusinessApplicationExceptions, Exception {
	 * 
	 * HttpSession httpSession = request.getSession(false); ServletOutputStream out
	 * = response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request);
	 * 
	 * try { if (httpSession != null && user != null) {
	 * 
	 * String[] selectedSectKeyids =
	 * request.getParameterValues("selectedDmtSectKeyids"); String[]
	 * deselectedSectKeyids = request.getParameterValues("deselectedDmtSectKeyids");
	 * boolean hasSelections = selectedSectKeyids != null &&
	 * selectedSectKeyids.length > 0; boolean hasDeselections = deselectedSectKeyids
	 * != null && deselectedSectKeyids.length > 0;
	 * 
	 * if (!hasSelections && !hasDeselections) { JSONObject err = new JSONObject();
	 * err.put("tpmException", "No DMT selected"); out.print(err.toString());
	 * return; } QtmTlPhenomenaMapping baseMapping = new QtmTlPhenomenaMapping();
	 * baseMapping = (QtmTlPhenomenaMapping) UIUtils.setBeanProperties((Object)
	 * baseMapping, request); baseMapping.setPhnmCreatedby(user.getUsrm_ccno());
	 * 
	 * String qphmKeyid = baseMapping.getPhnmQphmKeyid();
	 * 
	 * QtmTlPhenomenaMapping existPhenomenaMapping = (QtmTlPhenomenaMapping)
	 * httpSession.getAttribute("phenomenaMapping");
	 * 
	 * String lastKeyId = ""; int savedCount = 0;
	 * 
	 * // ── STEP 1: Handle CHECKED rows (INSERT or reactivate) ────────────── for
	 * (String sectKeyid : selectedSectKeyids) {
	 * 
	 * if (sectKeyid == null || sectKeyid.trim().isEmpty() ||
	 * "undefined".equalsIgnoreCase(sectKeyid.trim())) { continue; }
	 * 
	 * sectKeyid = sectKeyid.trim();
	 * 
	 * // Check if a record already exists for this phenomena + sectKeyid
	 * QtmTlPhenomenaMapping existing =
	 * phenomenaMappingService.findByQphmAndSect(qphmKeyid, sectKeyid);
	 * 
	 * if (existing != null) { // Record exists — if inactive, reactivate it if
	 * (!"Y".equals(existing.getPhnmActive())) { existing.setPhnmActive("Y");
	 * existing.setPhnmModifiedon(
	 * com.akranta.tpm.utils.CommonFunctions.dateTimeNow());
	 * phenomenaMappingService.updateActiveStatus(existing);
	 * CommonMessage.debugMsg("Reactivated sectKeyid=[" + sectKeyid + "] keyId=[" +
	 * existing.getPhnmKeyid() + "]"); } else {
	 * CommonMessage.debugMsg("Already active — skip sectKeyid=[" + sectKeyid +
	 * "]"); } lastKeyId = existing.getPhnmKeyid(); } else { // No record — INSERT
	 * new QtmTlPhenomenaMapping rowMapping = new QtmTlPhenomenaMapping();
	 * rowMapping.setPhnmQphmKeyid(qphmKeyid);
	 * rowMapping.setPhnmCreatedby(baseMapping.getPhnmCreatedby());
	 * rowMapping.setPhnmTempfield1(baseMapping.getPhnmTempfield1());
	 * rowMapping.setPhnmTempfield2(baseMapping.getPhnmTempfield2());
	 * rowMapping.setPhnmTempfield3(baseMapping.getPhnmTempfield3());
	 * rowMapping.setPhnmActive("Y"); rowMapping.setPhnmSectFlid(sectKeyid);
	 * 
	 * existPhenomenaMapping = phenomenaMappingService.create( rowMapping,
	 * existPhenomenaMapping); lastKeyId = existPhenomenaMapping.getPhnmKeyid();
	 * CommonMessage.debugMsg("Inserted sectKeyid=[" + sectKeyid + "] keyId=[" +
	 * lastKeyId + "]"); } savedCount++; }
	 * 
	 * // ── STEP 2: Handle UNCHECKED rows (set phnm_active = 'N') ────────── if
	 * (deselectedSectKeyids != null) { for (String sectKeyid :
	 * deselectedSectKeyids) {
	 * 
	 * if (sectKeyid == null || sectKeyid.trim().isEmpty() ||
	 * "undefined".equalsIgnoreCase(sectKeyid.trim())) { continue; }
	 * 
	 * sectKeyid = sectKeyid.trim();
	 * 
	 * QtmTlPhenomenaMapping existing =
	 * phenomenaMappingService.findByQphmAndSect(qphmKeyid, sectKeyid);
	 * 
	 * if (existing != null && "Y".equals(existing.getPhnmActive())) {
	 * existing.setPhnmActive("N"); existing.setPhnmModifiedon(
	 * com.akranta.tpm.utils.CommonFunctions.dateTimeNow());
	 * phenomenaMappingService.updateActiveStatus(existing);
	 * CommonMessage.debugMsg("Deactivated sectKeyid=[" + sectKeyid + "] keyId=[" +
	 * existing.getPhnmKeyid() + "]"); } } }
	 * 
	 * if (savedCount == 0) { JSONObject err = new JSONObject();
	 * err.put("tpmException", "No valid DMT selected"); out.print(err.toString());
	 * return; }
	 * 
	 * JSONObject successData = new JSONObject(); successData.put("msg",
	 * "Data Saved Successfully (" + savedCount + " records)");
	 * successData.put("keyId", lastKeyId);
	 * 
	 * JSONObject returnData = new JSONObject(); returnData.put("successData",
	 * successData); returnData.put("GenKeyid", lastKeyId);
	 * returnData.put("formClear", false);
	 * 
	 * out.print(returnData.toString()); }
	 * 
	 * } catch (ValidationExceptions e) { e.printStackTrace();
	 * out.print(UIUtils.validationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (BusinessApplicationExceptions e) {
	 * e.printStackTrace();
	 * out.print(UIUtils.businessValidationExceptions(e.toString(),
	 * "PhenomenaMapping").toString()); } catch (Exception e) { e.printStackTrace();
	 * JSONObject err = new JSONObject(); err.put("tpmException", "Data Not Saved: "
	 * + e.getMessage()); out.print(err.toString()); } }
	 */
    
    
    private void savePhenomenaMapping(HttpServletRequest request,
            HttpServletResponse response)
            throws ValidationExceptions, BusinessApplicationExceptions, Exception {

        HttpSession httpSession = request.getSession(false);
        ServletOutputStream out = response.getOutputStream();
        AdmTlUsermst user = UIUtils.getLoginUser(request);

        try {
            if (httpSession != null && user != null) {

                String[] selectedSectKeyids   = request.getParameterValues("selectedDmtSectKeyids");
                String[] deselectedSectKeyids = request.getParameterValues("deselectedDmtSectKeyids");

                boolean hasSelections   = selectedSectKeyids   != null && selectedSectKeyids.length   > 0;
                boolean hasDeselections = deselectedSectKeyids != null && deselectedSectKeyids.length > 0;

                if (!hasSelections && !hasDeselections) {
                    JSONObject err = new JSONObject();
                    err.put("tpmException", "No DMT selected");
                    out.print(err.toString());
                    return;
                }

                QtmTlPhenomenaMapping baseMapping = new QtmTlPhenomenaMapping();
                baseMapping = (QtmTlPhenomenaMapping)
                        UIUtils.setBeanProperties((Object) baseMapping, request);
                baseMapping.setPhnmCreatedby(user.getUsrm_ccno());

                String qphmKeyid = baseMapping.getPhnmQphmKeyid();

                QtmTlPhenomenaMapping existPhenomenaMapping =
                        (QtmTlPhenomenaMapping) httpSession.getAttribute("phenomenaMapping");

                String lastKeyId = "";
                int savedCount   = 0;

                // ── STEP 1: Handle CHECKED rows (INSERT or reactivate) ──────────────
                if (selectedSectKeyids != null) {
                    for (String sectKeyid : selectedSectKeyids) {

                        if (sectKeyid == null || sectKeyid.trim().isEmpty()
                                || "undefined".equalsIgnoreCase(sectKeyid.trim())) {
                            continue;
                        }

                        sectKeyid = sectKeyid.trim();

                        // Check if a record already exists for this phenomena + sectKeyid
                        QtmTlPhenomenaMapping existing =
                                phenomenaMappingService.findByQphmAndSect(qphmKeyid, sectKeyid);

                        if (existing != null) {
                            // Record exists — if inactive, reactivate it
                            if (!"Y".equals(existing.getPhnmActive())) {
                                existing.setPhnmActive("Y");
                                existing.setPhnmModifiedon(
                                        com.akranta.tpm.utils.CommonFunctions.dateTimeNow());
                                phenomenaMappingService.updateActiveStatus(existing);
                                CommonMessage.debugMsg("Reactivated sectKeyid=[" + sectKeyid
                                        + "] keyId=[" + existing.getPhnmKeyid() + "]");
                            } else {
                                CommonMessage.debugMsg("Already active — skip sectKeyid=[" + sectKeyid + "]");
                            }
                            lastKeyId = existing.getPhnmKeyid();
                        } else {
                            // No record — INSERT new
                            QtmTlPhenomenaMapping rowMapping = new QtmTlPhenomenaMapping();
                            rowMapping.setPhnmQphmKeyid(qphmKeyid);
                            rowMapping.setPhnmCreatedby(baseMapping.getPhnmCreatedby());
                            rowMapping.setPhnmTempfield1(baseMapping.getPhnmTempfield1());
                            rowMapping.setPhnmTempfield2(baseMapping.getPhnmTempfield2());
                            rowMapping.setPhnmTempfield3(baseMapping.getPhnmTempfield3());
                            rowMapping.setPhnmActive("Y");
                            rowMapping.setPhnmSectFlid(sectKeyid);

                            existPhenomenaMapping = phenomenaMappingService.create(
                                    rowMapping, existPhenomenaMapping);
                            lastKeyId = existPhenomenaMapping.getPhnmKeyid();
                            CommonMessage.debugMsg("Inserted sectKeyid=[" + sectKeyid
                                    + "] keyId=[" + lastKeyId + "]");
                        }
                        savedCount++;
                    }
                }

                // ── STEP 2: Handle UNCHECKED rows (set phnm_active = 'N') ──────────
                if (deselectedSectKeyids != null) {
                    for (String sectKeyid : deselectedSectKeyids) {

                        if (sectKeyid == null || sectKeyid.trim().isEmpty()
                                || "undefined".equalsIgnoreCase(sectKeyid.trim())) {
                            continue;
                        }

                        sectKeyid = sectKeyid.trim();

                        QtmTlPhenomenaMapping existing =
                                phenomenaMappingService.findByQphmAndSect(qphmKeyid, sectKeyid);

                        if (existing != null && "Y".equals(existing.getPhnmActive())) {
                            existing.setPhnmActive("N");
                            existing.setPhnmModifiedon(
                                    com.akranta.tpm.utils.CommonFunctions.dateTimeNow());
                            phenomenaMappingService.updateActiveStatus(existing);
                            CommonMessage.debugMsg("Deactivated sectKeyid=[" + sectKeyid
                                    + "] keyId=[" + existing.getPhnmKeyid() + "]");

                            // Capture keyId for response in case savedCount remains 0
                            if (lastKeyId.isEmpty()) {
                                lastKeyId = existing.getPhnmKeyid();
                            }
                        }
                    }
                }

                // ── STEP 3: Build response ───────────────────────────────────────────
                // savedCount == 0 means only deactivations happened — still a valid save
                if (savedCount == 0 && lastKeyId.isEmpty()) {
                    JSONObject err = new JSONObject();
                    err.put("tpmException", "No valid DMT found to process");
                    out.print(err.toString());
                    return;
                }

                JSONObject successData = new JSONObject();
                successData.put("msg",   "Data Saved Successfully" +
                        (savedCount > 0 ? " (" + savedCount + " records)" : ""));
                successData.put("keyId", lastKeyId);

                JSONObject returnData = new JSONObject();
                returnData.put("successData", successData);
                returnData.put("GenKeyid",    lastKeyId);
                returnData.put("formClear",   false);

                out.print(returnData.toString());
            }

        } catch (ValidationExceptions e) {
            e.printStackTrace();
            out.print(UIUtils.validationExceptions(e.toString(), "PhenomenaMapping").toString());
        } catch (BusinessApplicationExceptions e) {
            e.printStackTrace();
            out.print(UIUtils.businessValidationExceptions(e.toString(), "PhenomenaMapping").toString());
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject err = new JSONObject();
            err.put("tpmException", "Data Not Saved: " + e.getMessage());
            out.print(err.toString());
        }
    }
    // --------------------------------------------------------------- helper
    private CommonFilter populateCommonFilter(HttpServletRequest request,
            String beanIdentifier,
            boolean createNew) {

        HttpSession httpSession = request.getSession(false);

        CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
        if (commonFilter != null && !createNew) {
            FilterValues.setPaginationParams(request, commonFilter);
        } else {
            commonFilter = new CommonFilter();
            commonFilter = FilterValues.getCommonFilters(request, commonFilter);
            commonFilter = FilterValues.getAbnRelatedFilters(request, commonFilter);
            commonFilter.setViewClick('Y');
            httpSession.removeAttribute(beanIdentifier);
            httpSession.setAttribute(beanIdentifier, commonFilter);
        }
        return commonFilter;
    }
}