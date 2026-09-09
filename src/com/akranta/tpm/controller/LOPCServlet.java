package com.akranta.tpm.controller;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.LopcEntryMst;
import com.akranta.tpm.service.LOPCService;
import com.akranta.tpm.service.impl.LOPCServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.service.api.LopcEntryServiceApi;
public class LOPCServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   LopcEntryServiceApi lopcentryserviceapi;
   private LOPCService lOPCService;
   

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         this.Process(request, response);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      try {
         this.Process(request, response);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void Process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      String action = UIUtils.getActionPart(request);
      new ComboFilter();
      this.lOPCService = (LOPCServiceImpl)UIUtils.getServiceObject(request, "LOPCServiceImpl");
      lOPCService.LOPCServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
      String mode;
      String tmpFromRow;
      String loginflid;
      String keyId;
      String format;
      AdmTlUsermst usersdetails;
      if (action.equals("LOPCCreation_input.lopc")) {
         mode = request.getParameter("mode");
         CommonMessage.debugMsg("mode" + mode);
         tmpFromRow = request.getParameter("keyid");
         CommonMessage.debugMsg("LOPC keyid" + tmpFromRow);
         loginflid = request.getParameter("frmMode");
         keyId = request.getParameter("WWBLkeyid");
         format = request.getParameter("Status");
         LopcEntryMst lopcEntryMst = new LopcEntryMst();
         usersdetails = UIUtils.getLoginUser(request);
         lopcEntryMst.setLoemCreatedby(usersdetails.getUsrm_ccno());
         if (UIUtils.isValidKeyId(tmpFromRow)) {
            lopcEntryMst = this.lOPCService.getLOPCData(tmpFromRow);
         }

         CommonMessage.debugMsg("keyid:::" + tmpFromRow);
         request.setAttribute("keyid", tmpFromRow);
         request.setAttribute("frmMode", loginflid);
         request.setAttribute("WWblaKeyid", keyId);
         request.setAttribute("lopcEntryMst", lopcEntryMst);
         request.setAttribute("Status", format);
         httpSession.setAttribute("lopcEntryMst", lopcEntryMst);

         try {
            UIUtils.forwardRequest(request, response, "/pages/LOPC/LOPCCreation.jsp");
         } catch (ServletException var29) {
            var29.printStackTrace();
         }
      } else if (action.equals("LOPCCategory.lopc")) {
         try {
            ComboFilter comboFilter = UIUtils.fillComboFilter(request);
            List<ComboBox> course = this.lOPCService.getLOPCCategory(comboFilter);
            UIUtils.writeComboBox(response, course, comboFilter);
         } catch (Exception var28) {
            var28.printStackTrace();
         }
      } else {
         RequestDispatcher rd;
         if (action.equals("LOPCClosurepopup_input.lopc")) {
            mode = request.getParameter("keyid");
            CommonMessage.debugMsg("The keyid" + mode);
            request.setAttribute("keyid", mode);
            rd = request.getRequestDispatcher("pages/LOPC/LOPCClosurePopup.jsp");
            rd.forward(request, response);
         } else if (action.equals("LOPCModification_input.lopc")) {
            mode = request.getParameter("mode");
            CommonMessage.debugMsg("The Mode" + mode);
            tmpFromRow = UIUtils.getLoginUser(request).getUsrm_username();
            loginflid = CommonFunctions.getLoginFlid(request);
            keyId = CommonFunctions.getLoginLevel(request);
            format = (String)httpSession.getAttribute("loginElementid");
            new AdmTlUsermst();
            usersdetails = UIUtils.getLoginUser(request);
            String empId = usersdetails.getUsrm_ccno();
            CommonMessage.debugMsg("loginflid::  " + loginflid + "hh " + keyId + "2 " + format + "3 " + empId);
            new AdmTlUserRoleLink();
            List<String[]> getUserLoginDtl = this.lOPCService.getElementId(loginflid, keyId, format, empId);
            String elementid = ((String[])getUserLoginDtl.get(0))[0];
            String fnln = ((String[])getUserLoginDtl.get(0))[1];
            String level = ((String[])getUserLoginDtl.get(0))[2];
            String rolename = ((String[])getUserLoginDtl.get(0))[3];
            String rolekeyid = ((String[])getUserLoginDtl.get(0))[4];
            request.setAttribute("mode", mode);
            request.setAttribute("rolekeyid", rolekeyid);
            request.setAttribute("rolename", rolename);
             rd = request.getRequestDispatcher("pages/LOPC/LOPCModify.jsp");
            rd.forward(request, response);
         } else if (action.equals("LOPCActionClosure_update.lopc")) {
            this.UpdateLOPCActionClosure(request, response);
         } else {
            ArrayList headers;
            PrintWriter out;
            CommonFilter commonFilter;
            JSONObject tblJSONObj;
            List riskListGrid;
            JqGridTableModel jqGridTableModel;
            GridColModel gridColModel;
            String[] colHeaderHead;
            String[] colHeader;
            if (action.equals("LOPCModification_getCol.lopc")) {
               out = response.getWriter();
               CommonMessage.debugMsg("gETCOL lOPC");
               commonFilter = new CommonFilter();
               new JSONObject();
               riskListGrid = null;

               try {
                  FilterValues.getCommonFilters(request, commonFilter);
                  commonFilter.setIsGetCol("Y");
                  riskListGrid = this.lOPCService.getLOPCModificationList(commonFilter);
               } catch (Exception var27) {
                  CommonMessage.debugMsg(var27.getMessage());
               }

               jqGridTableModel = new JqGridTableModel();
               gridColModel = new GridColModel();
               gridColModel.setHeaderNum(1);
               jqGridTableModel.setSortable(false);
               jqGridTableModel.setTableButton(true);
               jqGridTableModel.setEnableFilter(true);
               jqGridTableModel.setRowNumbers(true);
               colHeaderHead = (String[])riskListGrid.get(0);
               colHeader = (String[])riskListGrid.get(1);
               headers = new ArrayList();
               headers.add(colHeader);
               tblJSONObj = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
               tblJSONObj.set("tableWidth", "108%%");
               tblJSONObj.set("tableHeight", "62%%");
               httpSession.removeAttribute("HazopColModel");
               httpSession.setAttribute("HazopColModel", tblJSONObj);
               CommonMessage.debugMsg("jsonObject " + tblJSONObj);
               out.println(tblJSONObj);
            } else {
               // commonFilter;
               List MasterGrid;
              // PrintWriter out;
               JSONObject ResourceGridmod;
               if (action.equals("LOPCModification_getData.lopc")) {
                  try {
                     commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", true);
                     FilterValues.getCommonFilters(request, commonFilter);
                     tmpFromRow = request.getParameter("keyid");
                     CommonMessage.debugMsg("keyid" + tmpFromRow);
                     commonFilter.setIsGetCol("N");
                     MasterGrid = this.lOPCService.getLOPCModificationList(commonFilter);
                     out = response.getWriter();
                     ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request, 2, 0);
                     out.println(ResourceGridmod);
                  } catch (Exception var26) {
                     CommonMessage.debugMsg(var26.getMessage());
                  }
               } else if (action.equals("LOPCActionClosure_input.lopc")) {
                  mode = request.getParameter("mode");
                  AdmTlUsermst usersdetails2 = UIUtils.getLoginUser(request);
                  String employeeId = usersdetails2.getUsrm_ccno(); 
                  CommonMessage.debugMsg("Employee ID: " + employeeId);
                  CommonMessage.debugMsg("The Mode" + mode);
                  request.setAttribute("mode", mode);
                  request.setAttribute("employeeId", employeeId);
                  rd = request.getRequestDispatcher("pages/LOPC/LOPCActionClosure.jsp");
                  rd.forward(request, response);
               } else if (action.equals("LOPCView_input.lopc")) {
                  mode = request.getParameter("mode");
                  CommonMessage.debugMsg("The Mode" + mode);
                  request.setAttribute("mode", mode);
                  rd = request.getRequestDispatcher("pages/LOPC/LOPCView.jsp");
                  rd.forward(request, response);
               } else if (action.equals("LOPCView_getCol.lopc")) {
                  out = response.getWriter();
                  CommonMessage.debugMsg("gETCOL ActionPlan");
                  commonFilter = new CommonFilter();
                  new JSONObject();
                  riskListGrid = null;

                  try {
                     FilterValues.getCommonFilters(request, commonFilter);
                     commonFilter.setIsGetCol("Y");
                     riskListGrid = this.lOPCService.getLOPCView(commonFilter);
                  } catch (Exception var25) {
                     CommonMessage.debugMsg(var25.getMessage());
                  }

                  jqGridTableModel = new JqGridTableModel();
                  gridColModel = new GridColModel();
                  gridColModel.setHeaderNum(1);
                  jqGridTableModel.setSortable(false);
                  jqGridTableModel.setTableButton(true);
                  jqGridTableModel.setEnableFilter(true);
                  jqGridTableModel.setRowNumbers(true);
                  colHeaderHead = (String[])riskListGrid.get(0);
                  colHeader = (String[])riskListGrid.get(1);
                  headers = new ArrayList();
                  headers.add(colHeader);
                  tblJSONObj = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
                  tblJSONObj.set("tableWidth", "108%%");
                  tblJSONObj.set("tableHeight", "62%%");
                  httpSession.removeAttribute("HazopColModel");
                  httpSession.setAttribute("HazopColModel", tblJSONObj);
                  CommonMessage.debugMsg("jsonObject " + tblJSONObj);
                  out.println(tblJSONObj);
               } else if (action.equals("LOPCView_getData.lopc")) {
                  try {
                     commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", true);
                     FilterValues.getCommonFilters(request, commonFilter);
                     tmpFromRow = request.getParameter("keyid");
                     CommonMessage.debugMsg("keyid" + tmpFromRow);
                     commonFilter.setIsGetCol("N");
                     MasterGrid = this.lOPCService.getLOPCView(commonFilter);
                     out = response.getWriter();
                     ResourceGridmod = UIUtils.convertToJqGridTableObject(MasterGrid, request, 2, 0);
                     out.println(ResourceGridmod);
                  } catch (Exception var24) {
                     CommonMessage.debugMsg(var24.getMessage());
                  }
               }/* else if (action.equals("LOPCActionClosure_getCol.lopc")) {
                  out = response.getWriter();
                  CommonMessage.debugMsg("gETCOL ActionPlan");
                  commonFilter = new CommonFilter();
                  new JSONObject();
                  riskListGrid = null;

                  try {
                     FilterValues.getCommonFilters(request, commonFilter);
                     riskListGrid = this.lOPCService.getLOPCActionPlanList(commonFilter);
                  } catch (Exception var23) {
                     CommonMessage.debugMsg(var23.getMessage());
                  }

                  jqGridTableModel = new JqGridTableModel();
                  gridColModel = new GridColModel();
                  gridColModel.setHeaderNum(1);
                  jqGridTableModel.setSortable(false);
                  jqGridTableModel.setTableButton(true);
                  jqGridTableModel.setEnableFilter(true);
                  jqGridTableModel.setRowNumbers(true);
                  colHeaderHead = (String[])riskListGrid.get(0);
                  colHeader = (String[])riskListGrid.get(1);
                  headers = new ArrayList();
                  headers.add(colHeader);
                  tblJSONObj = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
                  tblJSONObj.set("tableWidth", "108%%");
                  tblJSONObj.set("tableHeight", "62%%");
                  httpSession.removeAttribute("HazopColModel");
                  httpSession.setAttribute("HazopColModel", tblJSONObj);
                  CommonMessage.debugMsg("jsonObject " + tblJSONObj);
                  out.println(tblJSONObj);*/
               else if (action.equals("LOPCActionClosure_getCol.lopc")) {
            	    out = response.getWriter();
            	    CommonMessage.debugMsg("gETCOL ActionPlan");
            	    commonFilter = new CommonFilter();
            	    new JSONObject();
            	    riskListGrid = null;

            	    try {
            	        FilterValues.getCommonFilters(request, commonFilter);
            	        
            	        // Get employee ID from request
            	        String employeeId = request.getParameter("USERID");
            	        if(employeeId == null || employeeId.isEmpty()) {
            	            AdmTlUsermst usersdetails4 = UIUtils.getLoginUser(request);
            	            employeeId = usersdetails4.getUsrm_ccno();
            	        }
            	        
            	        CommonMessage.debugMsg("Employee ID in getCol: " + employeeId);
            	        
            	        // Pass employeeId to the service method
            	        commonFilter.setIsGetCol("Y");
            	        riskListGrid = this.lOPCService.getLOPCActionPlanList(commonFilter, employeeId);
            	    } catch (Exception var23) {
            	        CommonMessage.debugMsg(var23.getMessage());
            	    }

            	    jqGridTableModel = new JqGridTableModel();
            	    gridColModel = new GridColModel();
            	    gridColModel.setHeaderNum(1);
            	    jqGridTableModel.setSortable(false);
            	    jqGridTableModel.setTableButton(true);
            	    jqGridTableModel.setEnableFilter(true);
            	    jqGridTableModel.setRowNumbers(true);
            	    colHeaderHead = (String[])riskListGrid.get(0);
            	    colHeader = (String[])riskListGrid.get(1);
            	    headers = new ArrayList();
            	    headers.add(colHeader);
            	    tblJSONObj = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
            	    tblJSONObj.set("tableWidth", "108%%");
            	    tblJSONObj.set("tableHeight", "62%%");
            	    httpSession.removeAttribute("HazopColModel");
            	    httpSession.setAttribute("HazopColModel", tblJSONObj);
            	    CommonMessage.debugMsg("jsonObject " + tblJSONObj);
            	    out.println(tblJSONObj);
            	
                } else if (action.equals("LOPCActionClosure_getData.lopc")) {
            	    try {
            	        commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", true);
            	        FilterValues.getCommonFilters(request, commonFilter);
            	        String employeeId = request.getParameter("USERID");
            	        if(employeeId == null || employeeId.isEmpty()) {
            	            AdmTlUsermst usersdetails3 = UIUtils.getLoginUser(request);
            	            employeeId = usersdetails3.getUsrm_ccno();
            	        }
            	        tmpFromRow = request.getParameter("keyid");
            	        CommonMessage.debugMsg("Employee ID in getData: " + employeeId);
            	        CommonMessage.debugMsg("keyid" + tmpFromRow);
            	        commonFilter.setIsGetCol("N");
            	        @SuppressWarnings("unchecked")
            	        
            	        List<String[]> MasterGrid6 = this.lOPCService.getLOPCActionPlanList(commonFilter, employeeId);
            	        
            	        PrintWriter out6 = response.getWriter();
            	        JSONObject ResourceGridmod3 = UIUtils.convertToJqGridTableObject(MasterGrid6, request, 2, 0);
            	        out6.println(ResourceGridmod3);
            	    } catch (Exception var22) {
            	        CommonMessage.debugMsg(var22.getMessage());
            	    }
            	
               } else if (action.equals("functionalLoc.lopc")) {
                  FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
                  functLocFieldNameBean.setSection("cmbnmrtSectionid");
                  functLocFieldNameBean.setCell("cmbnmrtCellid");
                  functLocFieldNameBean.setMachine("cmbnmrtMachineid");
                  functLocFieldNameBean.setSectMandatory(false);
                  functLocFieldNameBean.setCellMandatory(true);
                  functLocFieldNameBean.setFunctionalLocId("cmbnmrtFlid");
                  tmpFromRow = null;
  				FormModes formModes = FormModes.create;

                  UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
               } else if (action.equals("LOPCCreation_save.lopc")) {
                  this.SaveLopc(request, response);
               } else if (action.equals("LOPCView_getExcel.lopc")) {
                  CommonMessage.debugMsg("LOPCView_input.lopc?mode=Report&");
                  commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", false);
                  tmpFromRow = commonFilter.getFromRow();
                  commonFilter.setFromRow((String)null);
                  tblJSONObj = (JSONObject)httpSession.getAttribute("HazopColModel");
                  keyId = request.getParameter("KeyId");
                  tblJSONObj.put("title", "Lopc Report");
                  format = ExcelUtils.getFormat(request);
                  Workbook wb = this.lOPCService.getLopcExportExcel(commonFilter, tblJSONObj, format);
                  commonFilter.setFromRow(tmpFromRow);
                  ExcelUtils.writeToResponse(response, wb, "Lopc Report", format);
               }
               else if (action.equals("LOPCModification_getExcel.lopc")) {
           	    CommonMessage.debugMsg("LOPCModification_getExcel.lopc?mode=Report&");
           	    commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", false);
           	    tmpFromRow = commonFilter.getFromRow();
           	    commonFilter.setFromRow((String)null);
           	    tblJSONObj = (JSONObject)httpSession.getAttribute("HazopColModel");
           	    keyId = request.getParameter("KeyId");
           	    tblJSONObj.put("title", "LOPC Modification Report");
           	    format = ExcelUtils.getFormat(request);
           	    Workbook wb = this.lOPCService.getLopcModificationExportExcel(commonFilter, tblJSONObj, format);
           	    commonFilter.setFromRow(tmpFromRow);
           	    ExcelUtils.writeToResponse(response, wb, "LOPC_Modification_Report", format);
           	}
			/*
			 * else if (action.equals("LOPCActionClosure_getExcel.lopc")) {
			 * CommonMessage.debugMsg("LOPCActionClosure_getExcel.lopc?mode=Report&");
			 * commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", false);
			 * tmpFromRow = commonFilter.getFromRow();
			 * commonFilter.setFromRow((String)null); tblJSONObj =
			 * (JSONObject)httpSession.getAttribute("HazopColModel"); keyId =
			 * request.getParameter("KeyId"); tblJSONObj.put("title",
			 * "LOPC Action Closure Report"); format = ExcelUtils.getFormat(request);
			 * Workbook wb = this.lOPCService.getLopcActionClosureExportExcel(commonFilter,
			 * tblJSONObj, format); commonFilter.setFromRow(tmpFromRow);
			 * ExcelUtils.writeToResponse(response, wb, "LOPC_Action_Closure_Report",
			 * format); }
			 */
               else if (action.equals("LOPCActionClosure_getExcel.lopc")) {
            	    CommonMessage.debugMsg("LOPCActionClosure_getExcel.lopc?mode=Report&");
            	    
            	    commonFilter = this.populateCommonFilter(request, "LOPCCommonFilter", false);
            	    
            	    // **Get employeeId same as getData does**
            	    String employeeId = request.getParameter("USERID");
            	    if(employeeId == null || employeeId.isEmpty()) {
            	        AdmTlUsermst usersdetails8 = UIUtils.getLoginUser(request);
            	        employeeId = usersdetails8.getUsrm_ccno();
            	    }
            	    CommonMessage.debugMsg("Employee ID in getExcel: " + employeeId);
            	    
            	    tmpFromRow = commonFilter.getFromRow();
            	    commonFilter.setFromRow((String)null);
            	    tblJSONObj = (JSONObject)httpSession.getAttribute("HazopColModel");
            	    keyId = request.getParameter("KeyId");
            	    tblJSONObj.put("title", "LOPC Action Closure Report");
            	    format = ExcelUtils.getFormat(request);
            	    
            	    // **PASS employeeId to the service method**
            	    Workbook wb = this.lOPCService.getLopcActionClosureExportExcel(commonFilter, tblJSONObj, format, employeeId);
            	    
            	    commonFilter.setFromRow(tmpFromRow);
            	    ExcelUtils.writeToResponse(response, wb, "LOPC_Action_Closure_Report", format);
            	}
            }
         }
      }

   }

   private void SaveLopc(HttpServletRequest request, HttpServletResponse response) throws IOException {
      HttpSession httpSession = request.getSession(false);
      PrintWriter out = response.getWriter();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      String flid = request.getParameter("cmbloemFnlid");
      String LopcId = request.getParameter("LopcId");
      CommonMessage.debugMsg("The flid:::" + flid + "LopcId" + LopcId);
      String openfilemgr = request.getParameter("filemanager");
      String severity = request.getParameter("cmbNmrnSeveritypotentialid");
      if (httpSession != null && user != null) {
         LopcEntryMst lopcEntryMst = new LopcEntryMst();
         LopcEntryMst existLopcEntryMst = (LopcEntryMst)httpSession.getAttribute("LopcEntryMst");
         lopcEntryMst = (LopcEntryMst)UIUtils.setBeanProperties(lopcEntryMst, request);
         String formType = request.getParameter("formType");
         JSONObject successData = new JSONObject();
         JSONObject lopcEntrymstSuccessmsg = new JSONObject();
         lopcEntryMst.setLoemCreatedby(user.getUsrm_ccno());

         JSONObject err;
         String Lopckeyid;
         try {
            String spnOccurrencetime = request.getParameter("spnOccurrencetime");
            String spnPreparedDatetime = request.getParameter("spnPreparedDatetime");
            CommonMessage.debugMsg("lopcEntryMst.getLoemOccurencedatetime()" + lopcEntryMst.getLoemOccurencedatetime());
            if (UIUtils.isValidDate(lopcEntryMst.getLoemPrepareddatetime())) {
               if (spnPreparedDatetime != null) {
                  lopcEntryMst.setLoemOccurencedatetime(lopcEntryMst.getLoemPrepareddatetime() + " " + spnPreparedDatetime);
                  lopcEntryMst.setLoemPrepareddatetime(lopcEntryMst.getLoemPrepareddatetime() + " " + spnPreparedDatetime);
               } else {
                  lopcEntryMst.setLoemPrepareddatetime(lopcEntryMst.getLoemPrepareddatetime() + " 00:00");
                  lopcEntryMst.setLoemOccurencedatetime(lopcEntryMst.getLoemPrepareddatetime() + " 00:00");
               }

               CommonMessage.debugMsg("spnPreparedDatetime::" + spnPreparedDatetime);
            }

            String savemsg;
            if (lopcEntryMst.getLoemKeyid() == null && !UIUtils.isValidKeyId(LopcId)) {
               CommonMessage.debugMsg("lopcEntryMst.getLoemKeyid(): " + lopcEntryMst.getLoemKeyid());
               existLopcEntryMst = this.lOPCService.create(lopcEntryMst, existLopcEntryMst);
               Lopckeyid = existLopcEntryMst.getLoemKeyid();
               CommonMessage.debugMsg("outside of the insert data");
               savemsg = " Data saved succesfully";
            } else {
               CommonMessage.debugMsg("Inside Else");
               this.lOPCService.update(lopcEntryMst, existLopcEntryMst, LopcId);
               savemsg = "Data Updated succesfully";
            }

            if (UIUtils.isValidKeyId(openfilemgr)) {
               successData.put("openfilemgr", true);
               successData.put("flid", lopcEntryMst.getLoemFnlid());
               successData.put("keyId", lopcEntryMst.getLoemKeyid());
            }

            successData.put("msg", savemsg);
            lopcEntrymstSuccessmsg.put("successData", successData);
            out.print(lopcEntrymstSuccessmsg.toString());
         } catch (ValidationExceptions var19) {
            CommonMessage.debugMsg("ValidationExceptions");
            err = UIUtils.validationExceptions(var19.toString(), "LOPC");
            out.print(err.toString());
         } catch (BusinessApplicationExceptions var20) {
            CommonMessage.debugMsg("ValidationExceptions");
            err = UIUtils.validationExceptions(var20.toString(), "LOPC");
            out.print(err.toString());
         } catch (Exception var21) {
            err = new JSONObject();
            Lopckeyid = "Data Not Saved";
            if (var21.toString().contains("NEAR_EXIST")) {
               Lopckeyid = "Already Exists";
            }

            err.put("tpmException", Lopckeyid);
            out.print(err.toString());
         }
      }

   }

   private void UpdateLOPCActionClosure(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      String saveMsg = "";
      if (httpSession != null && user != null) {
         JSONObject err;
         try {
            String keyid = request.getParameter("keyid");
            String CompletedBy = request.getParameter("CompletedBy");
            String CompletedDate = request.getParameter("targetdate");
            String status = request.getParameter("status");
            String correctiveaction = request.getParameter("correctiveaction");
            String Remarks = request.getParameter("remarks");
            BdmTlWwbladtl bdmTlWwbladtl = new BdmTlWwbladtl();
            BdmTlWwbladtl existBdmTlWwbladtl = (BdmTlWwbladtl)httpSession.getAttribute("BdmTlWwbladtl");
            bdmTlWwbladtl = (BdmTlWwbladtl)UIUtils.setBeanProperties(bdmTlWwbladtl, request);
            if (keyid.length() > 0) {
               CommonMessage.debugMsg("Inside the Update");
               this.lOPCService.UpdateLOPCAClosure(bdmTlWwbladtl, existBdmTlWwbladtl, keyid, CompletedBy, status, CompletedDate, correctiveaction, Remarks);
               saveMsg = "Data Updated Successfully";
            }

            JSONObject persistentData = new JSONObject();
            JSONObject forwardData = new JSONObject();
            JSONObject successData = new JSONObject();
            successData.put("msg", saveMsg);
            successData.put("keyId", keyid);
            JSONObject returnData = new JSONObject();
            returnData.put("formClear", false);
            returnData.put("forwardData", forwardData);
            returnData.put("persistentData", persistentData);
            returnData.put("successData", successData);
            out.print(returnData.toString());
         } catch (ValidationExceptions var19) {
            err = UIUtils.validationExceptions(var19.toString(), "ProjectValidations");
            out.print(err.toString());
         } catch (BusinessApplicationExceptions var20) {
            err = new JSONObject();
            out.print(err.toString());
         } catch (Exception var21) {
            var21.printStackTrace();
            err = new JSONObject();
            err.put("tpmException", "Data Not Saved");
            out.print(err.toString());
         }
      }

   }

   private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
      HttpSession httpSession = request.getSession(false);
      CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
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
