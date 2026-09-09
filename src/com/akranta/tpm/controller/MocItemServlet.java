package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.akranta.tpm.dao.impl.Constants;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.MocItem;

import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.DocManagerService;
import com.akranta.tpm.service.MoMeetingService;
import com.akranta.tpm.service.MocItemService;
import com.akranta.tpm.service.api.MomServiceApi;

import com.akranta.tpm.service.impl.DocManagerServiceImpl;
import com.akranta.tpm.service.impl.MoMeetingServiceImpl;
import com.akranta.tpm.service.impl.MocItemServiceImpl;
import com.akranta.tpm.service.impl.AbnormalityFormServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.MOMeetingBean;

import com.akranta.tpm.controller.UIUtils;

public class MocItemServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    //MocItemService mocItemService;

    DocManagerService docManagerService;
    DashboardService dashboardService;

    String filePath = null;

    private static String DOC_ROOT_PATH;
    private static String docRealPath;

    private static final String DESTINATION_DIR_PATH = "tmpFiles";

    private static String APP_DOCMANAGER_PATH;

    public void init(ServletConfig config) throws ServletException {

        try {

            super.init(config);

            filePath =config.getServletContext().getRealPath("tmp") + "\\";

            new File(filePath).mkdirs();

            docRealPath =getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";

            boolean s = new File(docRealPath).mkdirs();

            DOC_ROOT_PATH =getServletContext().getRealPath("DocumentManagerServlet");

            DOC_ROOT_PATH =new File(DOC_ROOT_PATH).getParent();

            String parentFolderName =DOC_ROOT_PATH.substring(DOC_ROOT_PATH.lastIndexOf("\\") + 1);

            DOC_ROOT_PATH =new File(DOC_ROOT_PATH).getParent();

            DOC_ROOT_PATH =new File(DOC_ROOT_PATH).getParent();

            CommonMessage.debugMsg("DOC_ROOT_PATH Path : " + DOC_ROOT_PATH);

            APP_DOCMANAGER_PATH =DOC_ROOT_PATH + "/" + parentFolderName;

            String basePath =
                    UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig","FILEMANGER_BASE_PATH");

            CommonMessage.debugMsg(" FileManagerBasePath " + basePath);

            boolean pathExist =new File(basePath).exists();

            CommonMessage.debugMsg(" basePath pathExist " +basePath +" " +pathExist);

            if (basePath != null && pathExist) {

                DOC_ROOT_PATH = basePath;

                APP_DOCMANAGER_PATH =basePath + "/" + parentFolderName;
            }

            CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH);

            CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " +APP_DOCMANAGER_PATH);

        } catch (Exception e) {

            e.printStackTrace();

            CommonMessage.debugMsg(" File Manager exception " + e.getMessage());
        }
    }


    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException, ServletException {

        try {

            process(request, response);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    public void doPost(HttpServletRequest request,HttpServletResponse response) 
    		throws IOException, ServletException {

        try {

            process(request, response);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    private void process(HttpServletRequest request,HttpServletResponse response)
    		throws Exception {

        String action =UIUtils.getActionPart(request);

        HttpSession httpSession =request.getSession(false);

        CommonMessage.debugMsg("action " + action);

        try {

            dashboardService =(DashboardServiceImpl)
                            UIUtils.getServiceObject(request,"DashboardServiceImpl");

            docManagerService =(DocManagerServiceImpl)
                            UIUtils.getServiceObject(request,"DocManagerServiceImpl");

            MocItemService mocItemService =(MocItemService)
                            UIUtils.getServiceObject(request,"MocItemServiceImpl");

            String jwtToken =(String)(httpSession.getAttribute("tpmjwttoken") == null
                                    ? ""
                                    : httpSession.getAttribute("tpmjwttoken"));
            mocItemService.MocItemServiceImplJwt(jwtToken);

        } catch (ServiceObjectCreationException e) {

            CommonMessage.debugMsg(e);
        }

        String dispatchUrl = null;

        if (action.equals("suggestionmocitem_input.smoc")) {

            String mainForm = request.getParameter("mainForm");
            String smocRefDocId =request.getParameter("smocRefDocId");
            String smocRefDocType =request.getParameter("smocRefDocType");
            String type = request.getParameter("type");
            String mode =request.getParameter("mode");
            String stage = request.getParameter("stage");
            CommonMessage.debugMsg(
                    " Inside :: New :: type :: " + type
            );

            if ("true".equals(mainForm)) {

                request.setAttribute(
                        "mainForm",
                        "mainForm"
                );
            }

            String DMT =
                    request.getParameter("DMT");

            request.setAttribute("DMT", DMT);

            request.setAttribute("smocRefDocId",smocRefDocId);

            request.setAttribute("smocRefDocType",smocRefDocType);

            request.setAttribute("type", type);

            request.setAttribute("mode", mode);

            request.setAttribute("stage", stage);

            CommonMessage.debugMsg("Mode value" + mode);

            RequestDispatcher rd =request.getRequestDispatcher("/pages/MOCItem.jsp");
            rd.forward(request, response);

        }

        else if (action.equals("functionalLoc.smoc")) {

            FunctLocFieldNameBean functLocFieldNameBean =
                    new FunctLocFieldNameBean();

            String type = "";

            type = request.getParameter("Type");

            CommonMessage.debugMsg("TYPE " + type);

            functLocFieldNameBean.setFactory(
                    "cmbMocItmFactoryid"
            );

            functLocFieldNameBean.setSection(
                    "cmbMocItmSectionid"
            );

            functLocFieldNameBean.setCell(
                    "cmbMocItmCellid"
            );

            functLocFieldNameBean.setMachine(
                    "cmbMocItmMachineid"
            );

            functLocFieldNameBean.setFunctionalLocId(
                    "cmbMocItmFlid"
            );

            functLocFieldNameBean.setSbu(
                    "cmbMocItmSbu"
            );

            functLocFieldNameBean.setPbu(
                    "cmbMocItmPbu"
            );

            functLocFieldNameBean.setLocnMandatory(false);

            functLocFieldNameBean.setFactMandatory(false);

            functLocFieldNameBean.setSectMandatory(true);

            functLocFieldNameBean.setCellMandatory(false);

            functLocFieldNameBean.setMachMandatory(false);

            if (UIUtils.isValidKeyId(type)) {

                if (
                        "JH".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                ) {

                    functLocFieldNameBean.setCellMandatory(true);

                    functLocFieldNameBean.setMachDisable(true);

                }

                else if (
                        "DMT".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                ) {

                    functLocFieldNameBean.setSectMandatory(true);

                    functLocFieldNameBean.setMachDisable(true);

                    functLocFieldNameBean.setCellDisable(true);
                }

                else if (
                        "PILLAR".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                        ||
                        "Pillar".equals(type)
                ) {

                    functLocFieldNameBean.setCompDisable(true);

                    functLocFieldNameBean.setLconDisable(true);

                    functLocFieldNameBean.setPbuDisable(true);

                    functLocFieldNameBean.setSectDisable(true);

                    functLocFieldNameBean.setMachDisable(true);

                    functLocFieldNameBean.setCellDisable(true);
                }

                if (
                        "PRODUCTION".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                        ||
                        "OGM".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                        ||
                        "UMC".equals(
                                type.replaceAll(" ", "")
                                        .toUpperCase()
                        )
                ) {

                    functLocFieldNameBean.setSbuDisable(true);

                    functLocFieldNameBean.setPbuDisable(true);

                    functLocFieldNameBean.setSectDisable(true);

                    functLocFieldNameBean.setMachDisable(true);

                    functLocFieldNameBean.setCellDisable(true);
                }
            }

            FormModes formModes = FormModes.create;

            UIUtils.setFunctionalLocationPopupVal(
                    request,
                    response,
                    functLocFieldNameBean,
                    formModes
            );
        }

        else if (action.equals("suggestionmocitem_save.smoc")) {

            saveMocItem(request, response);
        }
        
        

        else if (action.equals("MocItem_getCol.smoc")) {

            PrintWriter out = response.getWriter();
            CommonFilter commonFilter = new CommonFilter();
            JSONObject jsonObject = new JSONObject();

            try {
                FilterValues.getCommonFilters(request, commonFilter);

                String flid      = request.getParameter("flid");
                String sectionid = request.getParameter("sectionid");

                String flidCarrier = (flid != null ? flid.trim() : "")
                        + (sectionid != null && !sectionid.trim().isEmpty()
                                ? "|" + sectionid.trim() : "");
                commonFilter.setFlid(flidCarrier);

                CommonMessage.debugMsg("MocItem_getCol flid=[" + flid
                        + "] sectionid=[" + sectionid + "]");

                MocItemService mocItemService =
                        (MocItemService) UIUtils.getServiceObject(request, "MocItemServiceImpl");
                mocItemService.MocItemServiceImplJwt(
                        (String)(httpSession.getAttribute("tpmjwttoken") == null
                                ? "" : httpSession.getAttribute("tpmjwttoken")));

                List<String[]> gridList = mocItemService.getMocItemList(commonFilter);

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

                jsonObject = UIUtils.getTableModel(
                        headers, colHeaderHead, jqGridTableModel, gridColModel);

                //  REMOVE these two lines — let processGridnew handle the URL
                // jsonObject.set("url", dataUrl);   ← DELETE
                
                //  KEEP only these
                httpSession.removeAttribute("MocItemListCommonFilter");
                httpSession.setAttribute("MocItemListCommonFilter", commonFilter);

                httpSession.removeAttribute("MocItemListColModel");
                httpSession.setAttribute("MocItemListColModel", jsonObject);

                jsonObject.set("tableWidth",  "100%");
                jsonObject.set("tableHeight", "45%");

            } catch (Exception e) {
                CommonMessage.debugMsg("MocItem_getCol error: " + e.getMessage());
            }

            out.println(jsonObject);
        }

        // ── processGridnew derives this URL from getCol automatically ─────────────
        else if (action.equals("MocItem_getData.smoc")) {
        	
        	

            try {
                // Read session filter — same as attendance report pattern
                CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute("MocItemListCommonFilter");
                if (commonFilter == null) {
                    commonFilter = new CommonFilter();
                }

                // Always refresh pagination params from request
                FilterValues.setPaginationParams(request, commonFilter);

                String flid      = request.getParameter("flid");
                String sectionid = request.getParameter("sectionid");

                String flidCarrier = (flid != null ? flid.trim() : "")
                        + (sectionid != null && !sectionid.trim().isEmpty()
                                ? "|" + sectionid.trim() : "");
                commonFilter.setFlid(flidCarrier);
                commonFilter.setViewClick('Y');

                CommonMessage.debugMsg("MocItem_getData flid=[" + flid
                        + "] sectionid=[" + sectionid + "]");

                // ── JWT init — this was missing before ─────────────────────────
                MocItemService mocItemService =
                        (MocItemService) UIUtils.getServiceObject(request, "MocItemServiceImpl");
                mocItemService.MocItemServiceImplJwt(
                        (String)(httpSession.getAttribute("tpmjwttoken") == null
                                ? "" : httpSession.getAttribute("tpmjwttoken")));
                // ───────────────────────────────────────────────────────────────

                List<String[]> gridList = mocItemService.getMocItemList(commonFilter);

                PrintWriter out = response.getWriter();
                JSONObject result = UIUtils.convertToJqGridTableObject(
                        gridList, request, 2, 0, commonFilter.getTotalRecordCnt());
                out.println(result);

            } catch (Exception e) {
                CommonMessage.debugMsg("MocItem_getData error: " + e.getMessage());
            }
        }
        
//        else if (action.equals("MocItem_getById.smoc")) {
//
//            PrintWriter out = response.getWriter();
//            JSONObject result = new JSONObject();
//            try {
//                String keyid = request.getParameter("keyid");
//                CommonMessage.debugMsg("MocItem_getById keyid=[" + keyid + "]");
//
//                MocItemService mocItemService =
//                        (MocItemService) UIUtils.getServiceObject(request, "MocItemServiceImpl");
//                mocItemService.MocItemServiceImplJwt(
//                        (String)(httpSession.getAttribute("tpmjwttoken") == null
//                                ? "" : httpSession.getAttribute("tpmjwttoken")));
//
//                MocItem mocItem = mocItemService.getById(keyid);
//                if (mocItem != null) {
//                    JSONObject data = new JSONObject();
//                    data.put("mocItmKeyid",  mocItem.getMocItmKeyid());
//                    data.put("mocItmItem",   mocItem.getMocItmItem());
//                    data.put("mocItmSectId", mocItem.getMocItmSection());
//                    data.put("mocItmFlid",   mocItem.getMocItmFlid());
//                    result.put("success", true);
//                    result.put("data", data);
//                    CommonMessage.debugMsg("MocItem_getById found: " + data.toString());
//                } else {
//                    result.put("success", false);
//                    result.put("msg", "Record not found");
//                }
//            } catch (Exception e) {
//                CommonMessage.debugMsg("MocItem_getById error: " + e.getMessage());
//                result.put("success", false);
//                result.put("msg", e.getMessage());
//            }
//            out.println(result);
//        }

        else if (action.equals("MocItem_getById.smoc")) {

            PrintWriter out = response.getWriter();
            JSONObject result = new JSONObject();

            try {
                String keyid = request.getParameter("keyid");
                CommonMessage.debugMsg("MocItem_getById keyid=[" + keyid + "]");

                MocItemService mocItemService =
                        (MocItemService) UIUtils.getServiceObject(request, "MocItemServiceImpl");
                mocItemService.MocItemServiceImplJwt(
                        (String)(httpSession.getAttribute("tpmjwttoken") == null
                                ? "" : httpSession.getAttribute("tpmjwttoken")));

                MocItem mocItem = mocItemService.getById(keyid);

                if (mocItem != null) {
                    JSONObject data = new JSONObject();

                    //  These keys must match what JS reads: data.mocItmKeyid, data.mocItmItem
                    data.put("mocItmKeyid",  mocItem.getMocItmKeyid());
                    data.put("mocItmItem",   mocItem.getMocItmItem());
                    data.put("mocItmSectId", mocItem.getMocItmSection());
                    data.put("mocItmFlid",   mocItem.getMocItmFlid());
                    
                    CommonMessage.debugMsg("mocItmSection=[" + mocItem.getMocItmSection() + "]");
                    CommonMessage.debugMsg("mocItmFlid=[" + mocItem.getMocItmFlid() + "]");

                    result.put("success", true);
                    result.put("data",    data);

                    CommonMessage.debugMsg("MocItem_getById response: " + result.toString());
                } else {
                    result.put("success", false);
                    result.put("msg", "Record not found for keyid=[" + keyid + "]");
                }

            } catch (Exception e) {
                CommonMessage.debugMsg("MocItem_getById error: " + e.getMessage());
                result.put("success", false);
                result.put("msg", e.getMessage());
            }

            out.println(result);
        }
        
        
        else if (action.equals("MocItem_delete.smoc")) {

            PrintWriter out = response.getWriter();
            JSONObject result = new JSONObject();
            try {
                String keyid = request.getParameter("keyid");
                CommonMessage.debugMsg("MocItem_delete keyid=[" + keyid + "]");

                MocItemService mocItemService =
                        (MocItemService) UIUtils.getServiceObject(request, "MocItemServiceImpl");
                mocItemService.MocItemServiceImplJwt(
                        (String)(httpSession.getAttribute("tpmjwttoken") == null
                                ? "" : httpSession.getAttribute("tpmjwttoken")));

                mocItemService.delete(keyid);

                JSONObject successData = new JSONObject();
                successData.put("msg", "Data Deleted Successfully");
                result.put("successData", successData);
                result.put("success", true);
            } catch (Exception e) {
                CommonMessage.debugMsg("MocItem_delete error: " + e.getMessage());
                result.put("success", false);
            }
            out.println(result);
        }
        
}

 private void saveMocItem( HttpServletRequest request, HttpServletResponse response ) throws Exception {

        HttpSession httpSession =request.getSession(false);

        ServletOutputStream out =response.getOutputStream();

        AdmTlUsermst user =UIUtils.getLoginUser(request);

        try {

            MocItem newMocItem =new MocItem();

            newMocItem.setMocItmItem(request.getParameter("txtMocItemName"));

            newMocItem.setMocItmSection(request.getParameter("smocSectionid"));

            newMocItem.setMocItmFlid(request.getParameter("smocFlid"));

            newMocItem.setMocItmCreatedby(user.getUsrm_ccno());

            newMocItem.setMocItmTempfield1(request.getParameter("txtSmocTempfield1"));

            newMocItem.setMocItmTempfield2(request.getParameter("txtSmocTempfield2"));

            newMocItem.setMocItmTempfield3(request.getParameter("txtSmocTempfield3"));

            MocItemService mocItemService =(MocItemService)UIUtils.getServiceObject(request,"MocItemServiceImpl");

            String keyid =request.getParameter("txtMocItemKeyid");

            String saveMsg;

            if (!UIUtils.isValidKeyId(keyid)) {

                newMocItem =mocItemService.create(newMocItem);

                saveMsg = "Data Saved Successfully";
            }

            else {

                newMocItem.setMocItmKeyid(keyid);

                newMocItem =mocItemService.update(newMocItem);

                saveMsg = "Data Updated Successfully";
            }

            JSONObject returnData =new JSONObject();

            JSONObject successData =new JSONObject();

            successData.put("msg", saveMsg);

            successData.put("keyId",newMocItem.getMocItmKeyid());

            returnData.put("successData",successData);

            returnData.put("formClear",false);

            out.print(returnData.toString());

        }

        catch (BusinessApplicationExceptions e) {

            out.print(UIUtils.businessValidationExceptions(e.toString(),"MocItemValidation").toString());
        }

        catch (ValidationExceptions e) {

            out.print(UIUtils.validationExceptions(e.toString(),"MocItemValidation").toString());
        }

        catch (Exception e) {

            JSONObject err =new JSONObject();

            err.put("tpmException","Data Not Saved");

            out.print(err.toString());

            CommonMessage.debugMsg("MocItem save error: " +e.getMessage());
        }
    }
}