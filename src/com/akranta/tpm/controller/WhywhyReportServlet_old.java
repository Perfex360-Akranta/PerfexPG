package com.akranta.tpm.controller;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BreakdownService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.WhyWhyAnalysisService;
import com.akranta.tpm.service.WhywhyReportService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.BreakdownServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.WhyWhyAnalysisServiceImpl;
import com.akranta.tpm.service.impl.WhywhyReportServiceImpl;
import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class WhywhyReportServlet_old extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private static final String gendrillcommonfilter = "whywhygendrillfilter";
   WhywhyReportService whywhyService;
   WhyWhyAnalysisService yyService;
   WorkOrderService workOrderService;
   BreakdownService breakDownService;
   DashboardService dashboardService;
   private Collection mode;

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      try {
         this.process(request, response);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      try {
         this.process(request, response);
      } catch (Exception var4) {
         var4.printStackTrace();
      }

   }

   private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String action = UIUtils.getActionPart(request);
      new ComboFilter();

      try {
         this.whywhyService = (WhywhyReportServiceImpl)UIUtils.getServiceObject(request, "WhywhyReportServiceImpl");
         this.yyService = (WhyWhyAnalysisServiceImpl)UIUtils.getServiceObject(request, "WhyWhyAnalysisServiceImpl");
         CommonMessage.debugMsg("yyserivce completed ");
         this.breakDownService = (BreakdownServiceImpl)UIUtils.getServiceObject(request, "BreakdownServiceImpl");
         CommonMessage.debugMsg("breakDownService completed ");
         this.dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request, "DashboardServiceImpl");
         this.workOrderService = (WorkOrderServiceImpl)UIUtils.getServiceObject(request, "WorkOrderServiceImpl");
         CommonMessage.debugMsg("workOrderService completed ");
      } catch (ServiceObjectCreationException var45) {
      }

      HttpSession httpSession;
      PrintWriter out;
      CommonFilter commonFilter;
      List Proposed;
      String end;
      JqGridTableModel jqGridTableModel;
      GridColModel gridColModel;
      String[] colHeader;
      String[] colHeaderCond;
      ArrayList headers;
      JSONObject jsonObject;
      RequestDispatcher rd;
   
      String format;
      String path;
      JSONObject analysisJson;
      String imagePath;
      List AbnDataList;
      Workbook wb;
      if (action.equals("filterXmlwhywhyReport_input.why")) {
         response.setContentType("xml");
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyStandard.xml");
      } else if (action.equals("filterXmlwhywhyEffectiveness_input.why")) {
         response.setContentType("xml");
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyEffecRpt.xml");
      } else if (action.equals("whywhyEffectiveness_input.why")) {
         UIUtils.forwardRequest(request, response, "/pages/EffectivenessForm.jsp");
      } else if (action.equals("whywhyEffectiveness_getCol.why")) {
         httpSession = request.getSession(false);
         out = response.getWriter();
         commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", true);
         Proposed = this.whywhyService.getYYEffectiveness(commonFilter);
         end = null;
         jqGridTableModel = new JqGridTableModel();
         gridColModel = new GridColModel();
         jqGridTableModel.setRowNumbers(true);
         jqGridTableModel.setEnableFilter(true);
         jqGridTableModel.setTableButton(true);
         gridColModel.setHeaderNum(1);
         colHeader = (String[])Proposed.get(1);
         colHeaderCond = (String[])Proposed.get(0);
         headers = new ArrayList();
         headers.add(colHeader);
         jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
         jsonObject.set("multiSelect", true);
         httpSession.removeAttribute("YYEffectColModel");
         httpSession.setAttribute("YYEffectColModel", jsonObject);
         CommonMessage.debugMsg("jsonObject " + jsonObject);
         out.println(jsonObject);
      } else if (action.equals("whywhyEffectiveness_getData.why")) {
         try {
            out = response.getWriter();
            new JSONObject();
            commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", false);
            Proposed = this.whywhyService.getYYEffectiveness(commonFilter);
            CommonMessage.debugMsg("size " + Proposed.size());
             jsonObject = UIUtils.convertToJqGridTableObject(Proposed, request, 2, 0, (long)Proposed.size());
            out.println(jsonObject);
         } catch (Exception var44) {
            var44.printStackTrace();
         }
      } else if (action.equals("whywhyEffectiveness_getExcel.why")) {
          commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", false);
         httpSession = request.getSession(false);
         path = commonFilter.getFromRow();
         commonFilter.setFromRow((String)null);
         httpSession = request.getSession(false);
         analysisJson = (JSONObject)httpSession.getAttribute("YYEffectColModel");
         analysisJson.put("title", "Why Why Effectiveness Report");
         end = ExcelUtils.getFormat(request);
         wb = this.whywhyService.WhyWhyEffectivenessExportExcel(commonFilter, analysisJson, end);
         commonFilter.setFromRow(path);
         ExcelUtils.writeToResponse(response, wb, "WhyWhyEffectivenessReport", end);
      } else if (action.equals("whywhyanalysisgrid_recall.why")) {
         out = response.getWriter();
         format = request.getParameter("cellId");
         path = request.getParameter("frm");
         imagePath = request.getParameter("keyid");
         CommonMessage.debugMsg("keyid   keyid  :  " + format);
         CommonMessage.debugMsg(" Inside recall method for :: " + imagePath + " formName :: " + path);
         AbnDataList = this.whywhyService.FillControlData(format, imagePath, path);
         out.print(JSONArray.fromCollection(AbnDataList));
      } else if (action.equals("whywhyReport_input.why")) {
         CommonMessage.debugMsg("1111111111111111input   ::::  ");
         request.setAttribute("yyrep", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "yyrep"));
         request.setAttribute("yydetails", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "yydetails"));
         rd = request.getRequestDispatcher("/pages/WhyWhyStandardReport.jsp");
         rd.forward(request, response);
      }

      String refdocdate;
      String problem;
      String filterStr;
      String wwmsKey;
      String counterMeasureFlag;
      String existFlag;
      String rowId;
      JSONObject err;
		/*
		 * Workbook wb; PrintWriter out; CommonFilter commonFilter; CommonFilter
		 * commonFilter;
		 */
      String totalCount;
      List yy;
      JSONObject ProposedJson;
  
      List analysis;
      List whywhyList;
		/*
		 * String refdocdate; String problem;
		 */
      String yymode;
      String flid;
      if (action.equals("filterXmlwhywhyanalysis_input.why")) {
         response.setContentType("xml");
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysisRpt.xml");
      } else if (action.equals("whywhyExl_view.why")) {
         try {
            rowId = request.getParameter("keyId");
            format = UIUtils.getImagePath(request);
            CommonMessage.debugMsg("keyid::::::" + rowId);
            path = ExcelUtils.getFormat(request);
            path = "xlsx";
            imagePath = UIUtils.getExcelTemplatePath(request);
            wb = this.whywhyService.getwhywhyExlView(rowId, path, imagePath, format);
            ExcelUtils.writeToResponse(response, wb, "WhyWhy_" + rowId, path);
         } catch (Exception var43) {
            var43.printStackTrace();
            CommonMessage.debugMsg("err:" + var43.getMessage());
            out = response.getWriter();
            err = new JSONObject();
            err.put("message", "Data Not Found");
            out.print(err.toString());
         }
      } else if (action.equals("whywhyanalysis_input.why")) {
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainFormGrid.jsp");
      } else if (action.equals("Whywhycount_input.why")) {
         UIUtils.forwardRequest(request, response, "/pages/Reports/whywhycount.jsp");
      } else if (action.equals("Whywhycount_getCol.why")) {
         this.buildTableCountColModel(request, response);
      } else if (action.equals("Whywhycount_getData.why")) {
         this.whywhycountGetData(request, response);
      } else if (action.equals("Whywhycount_barchart.why")) {
         this.processbarChart(request, response);
      } else if (action.equals("Whywhycount_getExcel.why")) {
         this.exportWhyWhyCountExcel(request, response);
      } else if (action.equals("whywhyanalysismodify_input.why")) {
         AdmTlUsermst user = UIUtils.getLoginUser(request);
         format = request.getParameter("keyid");
         path = request.getParameter("type");
         imagePath = request.getParameter("cmbWwmsKeyid");
         CommonMessage.debugMsg("maskeyid..." + format + "...maskeyid1..." + imagePath);
         if (!UIUtils.isValidKeyId(format)) {
            format = imagePath;
         }

         CommonMessage.debugMsg("maskeyid " + format);
         end = CommonFunctions.getLoginElementId(request);
         totalCount = null;
         if (end.length() > 10) {
            totalCount = end.substring(11, 21);
         }

         BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();
         if (UIUtils.isValidKeyId(format)) {
            newBdmTlWhywhymst = this.yyService.selectmaskeyid(format);
            colHeader = newBdmTlWhywhymst.getWwmsReportdatetime().split(" ");
            newBdmTlWhywhymst.setWwmsReportdatetime(colHeader[0]);
            newBdmTlWhywhymst.setWwmsDate(newBdmTlWhywhymst.getWwmsDate().substring(0, 11));
            CommonMessage.debugMsg(" Inside input action :: " + colHeader[1]);
            CommonMessage.debugMsg(" Checking For Reported Time Date :: " + newBdmTlWhywhymst.getWwmsReportdatetime());
            request.setAttribute("time", colHeader[1]);
            if (UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOthercheckpoints())) {
               request.setAttribute("otherCheck", "Y");
            } else {
               request.setAttribute("otherCheck", "N");
            }
         }

          httpSession = request.getSession(false);
         problem = request.getParameter("refdocid");
         yymode = request.getParameter("refdoctype");
         CommonMessage.debugMsg("refdoctype" + yymode);
         flid = request.getParameter("flid");
         refdocdate = request.getParameter("refdocdate");
         problem = request.getParameter("problem");
         filterStr = request.getParameter("attendedBy");
         wwmsKey = request.getParameter("area");
         counterMeasureFlag = request.getParameter("pillar");
         existFlag = request.getParameter("yymode");
         CommonMessage.debugMsg("refDocId.." + problem + "...refdoctype.." + yymode + "..flid.." + flid + "..refdocdate.." + refdocdate + "..problem.." + problem);
         if (UIUtils.isValidKeyId(problem)) {
            newBdmTlWhywhymst.setWwmsRefdocno(problem);
         }

         if (UIUtils.isValidKeyId(yymode)) {
            newBdmTlWhywhymst.setWwmsRefdoctype(yymode);
         }

         if (UIUtils.isValidKeyId(flid)) {
            newBdmTlWhywhymst.setWwmsFlid(flid);
         }

         if (UIUtils.isValidKeyId(refdocdate)) {
            newBdmTlWhywhymst.setWwmsDate(refdocdate);
         }

         if (UIUtils.isValidKeyId(problem)) {
            newBdmTlWhywhymst.setWwmsProblem(problem);
         }

         if (UIUtils.isValidKeyId(wwmsKey)) {
            newBdmTlWhywhymst.setWwmsArea(wwmsKey);
         }

         if (UIUtils.isValidKeyId(filterStr)) {
            newBdmTlWhywhymst.setWwmsProblemattendby(filterStr);
         }

         if (!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsWhywhydoneby())) {
            newBdmTlWhywhymst.setWwmsWhywhydoneby(user.getUsrm_ccno());
         }

         if (!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblemattendby())) {
            newBdmTlWhywhymst.setWwmsProblemattendby(filterStr);
         }

         CommonMessage.debugMsg("123,,," + newBdmTlWhywhymst.getWwmsDate());
         request.setAttribute("newBdmTlWhywhymst", newBdmTlWhywhymst);
         request.setAttribute("rcId", newBdmTlWhywhymst.getWwmsRootcauseid());
         request.setAttribute("refDoctype", yymode);
         request.setAttribute("refDocdate", refdocdate);
         request.setAttribute("mode", existFlag);
         request.setAttribute("type", path);
         request.setAttribute("locationid", totalCount);
         httpSession.setAttribute("newBdmTlWhywhymst", newBdmTlWhywhymst);
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainForm.jsp");
      } else if (action.equals("whywhyReport_getCol.why")) {
         CommonMessage.debugMsg("1111111111111111list      ::::  col");
         httpSession = request.getSession(false);
         out = response.getWriter();
         commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", true);
         if ("01-Jan-1801".contains(commonFilter.getFromDate()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
            commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
            commonFilter.setToDate(CommonFunctions.getDate());
         }

         Proposed = this.whywhyService.getAllwhywhyStd(commonFilter);
         end = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
         jqGridTableModel = new JqGridTableModel();
         gridColModel = new GridColModel();
         jqGridTableModel.setRowNumbers(true);
         jqGridTableModel.setEnableFilter(true);
         jqGridTableModel.setTableButton(true);
         gridColModel.setHeaderNum(1);
         colHeader = (String[])Proposed.get(0);
         colHeaderCond = (String[])Proposed.get(1);
         headers = new ArrayList();
         headers.add(colHeader);
         jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
         jsonObject.put("tableHeight", "82%%");
         jsonObject.put("tableWidth", "106%%");
         httpSession.setAttribute("yyReportColModel", jsonObject);
         out.println(jsonObject);
      } else if (action.equals("whywhyReport_getData.why")) {
         try {
            httpSession = request.getSession(false);
            format = request.getParameter("page");
            CommonMessage.debugMsg("1111111111111111list      :::: daya " + format);
            out = response.getWriter();
            commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", false);
            CommonMessage.debugMsg("  11111 popoi ");
            new JSONObject();
            whywhyList = this.whywhyService.getAllwhywhyStd(commonFilter);
            CommonMessage.debugMsg("size " + whywhyList.size());
            ProposedJson = UIUtils.convertToJqGridTableObject(whywhyList, request, 2, 0, commonFilter.getTotalRecordCnt());
            CommonMessage.debugMsg("  11111" + ProposedJson);
            out.println(ProposedJson);
         } catch (Exception var42) {
            CommonMessage.debugMsg(var42.getMessage());
         }
      } else if (action.equals("whywhyReport_getExcel.why")) {
         httpSession = request.getSession(false);
         commonFilter = this.populateCommonFilter(request, "whywhyCommonFilter", false);
         path = commonFilter.getFromRow();
         commonFilter.setFromRow((String)null);
         imagePath = commonFilter.getFromDate();
         end = commonFilter.getToDate();
         totalCount = imagePath + "  -  " + end;
         JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("yyReportColModel");
         tblJSONObj.put("title", "Why-Why Standard Report  -  " + totalCount);
         refdocdate = ExcelUtils.getFormat(request);
          wb = this.whywhyService.yyExportExcel(commonFilter, tblJSONObj, refdocdate);
         commonFilter.setFromRow(path);
         ExcelUtils.writeToResponse(response, wb, "Why-WhyStandardReport", refdocdate);
      } else if (action.equals("whywhyanalysismaingrid_getExcel.why")) {
         httpSession = request.getSession(false);
         commonFilter = this.populateCommonFilter(request, "whywhyanalysisCommonFilter", false);
         commonFilter.setViewClick('Y');
         path = commonFilter.getFromRow();
         commonFilter.setFromRow((String)null);
         analysisJson = (JSONObject)httpSession.getAttribute("ColModel");
         analysisJson.put("title", "WhyWhyAnalysis");
         end = ExcelUtils.getFormat(request);
         wb = this.yyService.whywhyExportExcel(commonFilter, analysisJson, end);
         commonFilter.setFromRow(path);
         ExcelUtils.writeToResponse(response, wb, "WhyWhyAnalysis", end);
      } else if (action.equals("yyDonebyLink_getCol.why")) {
         try {
            rowId = request.getParameter("masterkeyid");
            yy = this.whywhyService.getyyDoneby(rowId);
            err = this.getyyDonebyTableModel(yy);
            CommonMessage.debugMsg("Table model");
            CommonMessage.debugMsg("jsonObject " + err);
            out = response.getWriter();
            out.println(err);
         } catch (Exception var41) {
            var41.printStackTrace();
         }
      } else if (action.equals("yyDonebyLink_getData.why")) {
         out = response.getWriter();
         format = request.getParameter("masterkeyid");
         analysis = this.whywhyService.getyyDoneby(format);
         analysisJson = UIUtils.convertToJqGridTableObject(analysis, request, 1, 0);
         out.println(analysisJson);
      }

      if (action.equals("filterXmlwhywhyQtyReport_input.why")) {
         response.setContentType("xml");
         CommonMessage.debugMsg("action " + action);
         UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyQtyStandard.xml");
      } else if (action.equals("whywhyQtyReport_input.why")) {
         rd = request.getRequestDispatcher("/pages/Reports/WhyWhyStandardQtyReport.jsp");
         rd.forward(request, response);
      } else if (action.equals("whywhyQtyReport_getCol.why")) {
         httpSession = request.getSession(false);
         out = response.getWriter();
         commonFilter = this.populateCommonFilter(request, "whywhyQtyCommonFilter", true);
         Proposed = this.whywhyService.getAllwhywhyQtyStd(commonFilter);
         ProposedJson = UIUtils.convertToJqGridTableObject(Proposed, request, 0, 0, commonFilter.getTotalRecordCnt());
         CommonMessage.debugMsg("Total Count : " + commonFilter.getTotalRecordCnt());
         httpSession.setAttribute("yyQtyDataServlet", ProposedJson);
         jqGridTableModel = new JqGridTableModel();
         gridColModel = new GridColModel();
         jqGridTableModel.setRowNumbers(true);
         jqGridTableModel.setEnableFilter(true);
         jqGridTableModel.setTableButton(false);
         gridColModel.setHeaderNum(1);
         colHeader = (String[])Proposed.get(2);
         colHeaderCond = (String[])Proposed.get(1);
         headers = new ArrayList();
         headers.add(colHeader);
         jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
         CommonMessage.debugMsg("colModel   " + jsonObject);
         jsonObject.put("tableHeight", "92%%");
         jsonObject.put("tableWidth", "108%%");
         httpSession.removeAttribute("yyQtyColModel");
         httpSession.setAttribute("yyQtyColModel", jsonObject);
         out.println(jsonObject);
      } else if (action.equals("yyprobAttbyLink_getCol.why")) {
         try {
            rowId = request.getParameter("masterkeyid");
            yy = this.whywhyService.getProbAttby(rowId);
            err = this.getProbAttbyTableModel(yy);
            CommonMessage.debugMsg("jsonObject " + err);
            out = response.getWriter();
            out.println(err);
         } catch (Exception var40) {
            var40.printStackTrace();
         }
      } else if (action.equals("yyprobAttbyLink_getData.why")) {
         out = response.getWriter();
         format = request.getParameter("masterkeyid");
         analysis = this.whywhyService.getProbAttby(format);
         analysisJson = UIUtils.convertToJqGridTableObject(analysis, request, 1, 0);
         out.println(analysisJson);
      } else {
         YYFormBean yyFormBean = null;
         if (action.equals("probAttbyLink_save.why")) {
            CommonMessage.debugMsg("Inside probAttbyLink_save.why");
            yyFormBean = new YYFormBean();
            this.saveprobAttbyLink(request, response, yyFormBean);
         } else if (action.equals("YYProbAttBy_delete.why")) {
            CommonMessage.debugMsg("YYProbAttBy_delete.why");
            this.deleteYYProbAttBy(request, response);
         } else if (action.equals("whywhyQtyReport_getData.why")) {
            try {
               httpSession = request.getSession(false);
               out = response.getWriter();
               UIUtils.displayRequestParamsValue(request);
               path = request.getParameter("page");
               commonFilter = this.populateCommonFilter(request, "whywhyQtyCommonFilter", true);
               new JSONObject();
               CommonMessage.debugMsg("PAGE : " + path);
               ProposedJson = (JSONObject)httpSession.getAttribute("yyQtyDataServlet");
               CommonMessage.debugMsg("Total Count : " + commonFilter.getTotalRecordCnt());
               whywhyList = this.whywhyService.getAllwhywhyQtyStd(commonFilter);
               ProposedJson = UIUtils.convertToJqGridTableObject(whywhyList, request, 3, 0, commonFilter.getTotalRecordCnt() + 1L);
               out.println(ProposedJson);
               commonFilter.setViewClick('N');
            } catch (Exception var39) {
               CommonMessage.debugMsg("error " + var39.getMessage());
            }
         } else if (action.equals("whywhyQtyReport_getExcel.why")) {
            httpSession = request.getSession(false);
            commonFilter = this.populateCommonFilter(request, "whywhyQtyCommonFilter", false);
            path = commonFilter.getFromRow();
            analysisJson = (JSONObject)httpSession.getAttribute("yyQtyColModel");
            analysisJson.put("title", "Why Why Standard Report");
            end = ExcelUtils.getFormat(request);
            wb = this.whywhyService.getAllwhywhyQtyExcel(commonFilter, analysisJson, end);
            commonFilter.setFromRow(path);
            ExcelUtils.writeToResponse(response, wb, "WhyWhyStandardRpt", end);
         } else if (action.equals("whywhy_input.why")) {
            UIUtils.displayRequestParamsValue(request);
            httpSession = request.getSession(false);
            BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();
            //YYFormBean yyFormBean = new YYFormBean();
            imagePath = request.getParameter("whywhyRefDocID");
            if (UIUtils.isValidKeyId(imagePath)) {
               if (!imagePath.substring(0, 2).equals("BD") && !imagePath.substring(0, 2).equals("CU")) {
                  if (imagePath.substring(0, 2).equals("AB") || imagePath.substring(0, 2).equals("GM")) {
                     end = request.getParameter("womsKey");
                     new WomTlWomst();
                     if (UIUtils.isValidKeyId(end)) {
                        WomTlWomst womTlWomst = this.workOrderService.select(end);
                        this.fillYY(bdmTlWhywhymst, womTlWomst);
                     }
                  }
               } else {
                  bdmTlWhywhymst = this.breakDownService.selectWhyWhy(imagePath);
               }
            } else {
               bdmTlWhywhymst = (BdmTlWhywhymst)UIUtils.setBeanProperties(bdmTlWhywhymst, request);
            }

            end = request.getParameter("whywhyRefDocID");
            totalCount = request.getParameter("whywhyRefDocType");
           // String flid = request.getParameter("flid");
            refdocdate = request.getParameter("refDocDate");
            CommonMessage.debugMsg("refdocdate" + refdocdate);
            problem = request.getParameter("prbolem");
            yymode = request.getParameter("yyMode");
            flid = request.getParameter("attendedBy");
            refdocdate = request.getParameter("area");
            problem = request.getParameter("pillar");
            filterStr = "refdocid=" + end + "&refdoctype=" + totalCount + "&flid=" + flid + "&refdocdate=" + refdocdate + "&problem=" + problem + "&yymode=" + yymode + "&attendedBy=" + flid + "&area=" + refdocdate + "&pillar=" + problem;
            CommonMessage.debugMsg("filterStr...." + filterStr);
            if (UIUtils.isValidKeyId(end)) {
               bdmTlWhywhymst.setWwmsRefdocno(end);
               httpSession.setAttribute("refdocid", end);
            }

            if (UIUtils.isValidKeyId(totalCount)) {
               bdmTlWhywhymst.setWwmsRefdoctype(totalCount);
               httpSession.setAttribute("refdoctype", totalCount);
            }

            if (UIUtils.isValidKeyId(flid)) {
               bdmTlWhywhymst.setWwmsFlid(flid);
               httpSession.setAttribute("flid", flid);
            }

            if (UIUtils.isValidKeyId(refdocdate)) {
               bdmTlWhywhymst.setWwmsDate(refdocdate);
               httpSession.setAttribute("refdocdate", refdocdate);
            }

            if (UIUtils.isValidKeyId(refdocdate)) {
               bdmTlWhywhymst.setWwmsProblem(problem);
               httpSession.setAttribute("problem", problem);
            }

            yyFormBean = (YYFormBean)UIUtils.setBeanProperties(yyFormBean, request);
            wwmsKey = (String)httpSession.getAttribute("wwmsKeyid");
            CommonMessage.debugMsg("Form Type .....................>" + yyFormBean.getFormType() + "................................");
            yyFormBean = new YYFormBean();
            yyFormBean.setFormType("BD");
            if (!yyFormBean.getFormType().equals("BD") && !yyFormBean.getFormType().equals("BDM") && !yyFormBean.getFormType().equals("CC") && !yyFormBean.getFormType().equals("SHE") && !yyFormBean.getFormType().equals("DOCK") && !yyFormBean.getFormType().equals("IMT") && !yyFormBean.getFormType().equals("ABN")) {
               CommonMessage.debugMsg("Why Why Key : " + bdmTlWhywhymst.getWwmsKeyid());
               counterMeasureFlag = bdmTlWhywhymst.getWwmsKeyid();
               if (UIUtils.isValidKeyId(counterMeasureFlag)) {
                  bdmTlWhywhymst = this.yyService.getWWMS(counterMeasureFlag);
               }
            } else {
               if (yyFormBean.getFormType().equals("ABN")) {
                  counterMeasureFlag = bdmTlWhywhymst.getWwmsRefdocno();
                  CommonMessage.debugMsg("refDocId " + counterMeasureFlag);
                  if (UIUtils.isValidKeyId(counterMeasureFlag)) {
                     existFlag = this.yyService.checkMstExist(counterMeasureFlag);
                     if (UIUtils.isValidKeyId(existFlag) && Integer.parseInt(existFlag) > 0) {
                        bdmTlWhywhymst = this.breakDownService.selectWhyWhy(counterMeasureFlag);
                     }
                  }
               }

               httpSession.removeAttribute("WHYWHYFORMTYPE");
               httpSession.setAttribute("WHYWHYFORMTYPE", yyFormBean.getFormType());
               if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid())) {
                  counterMeasureFlag = bdmTlWhywhymst.getWwmsKeyid();
                  if (UIUtils.isValidKeyId(counterMeasureFlag)) {
                     bdmTlWhywhymst = this.yyService.getWWMS(counterMeasureFlag);
                  }

                  List<String[]> yyMstValues = this.yyService.getSelectedRootCause(bdmTlWhywhymst.getWwmsKeyid());
                  bdmTlWhywhymst.setWwmsChecksmade(((String[])yyMstValues.get(0))[7]);
                  bdmTlWhywhymst.setWwmsYoudidnot(((String[])yyMstValues.get(0))[8]);
                  bdmTlWhywhymst.setWwmsCountermeasure(((String[])yyMstValues.get(0))[9]);
                  request.setAttribute("rcId", ((String[])yyMstValues.get(0))[0]);
                  request.setAttribute("isJH", ((String[])yyMstValues.get(0))[2]);
                  request.setAttribute("isPM", ((String[])yyMstValues.get(0))[3]);
                  request.setAttribute("isCI", ((String[])yyMstValues.get(0))[4]);
                  request.setAttribute("isET", ((String[])yyMstValues.get(0))[5]);
               }

               request.setAttribute("causeId", request.getParameter("cmbwwmsCauseid"));
            }

            if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsDate()) && bdmTlWhywhymst.getWwmsDate().indexOf(":") > 0) {
               bdmTlWhywhymst.setWwmsDate(bdmTlWhywhymst.getWwmsDate().substring(0, bdmTlWhywhymst.getWwmsDate().indexOf(" ")));
            }

            if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevdate())) {
               CommonMessage.debugMsg(bdmTlWhywhymst.getWwmsPrevdate());
               if (bdmTlWhywhymst.getWwmsPrevdate().indexOf(":") > 0) {
                  bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate().substring(0, bdmTlWhywhymst.getWwmsPrevdate().indexOf(" ")));
                  if (bdmTlWhywhymst.getWwmsPrevdate().equals("01-Jan-1801")) {
                     bdmTlWhywhymst.setWwmsPrevdate("");
                  }
               } else {
                  bdmTlWhywhymst.setWwmsPrevdate(bdmTlWhywhymst.getWwmsPrevdate());
               }
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPreveffectiveness())) {
               bdmTlWhywhymst.setWwmsPreveffectiveness("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause())) {
               bdmTlWhywhymst.setWwmsRootcause("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPokayoke())) {
               bdmTlWhywhymst.setWwmsPokayoke("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsMachineid())) {
               bdmTlWhywhymst.setWwmsMachineid("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAssemblyid())) {
               bdmTlWhywhymst.setWwmsAssemblyid("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsAccidentphen())) {
               bdmTlWhywhymst.setWwmsAccidentphen("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevno())) {
               bdmTlWhywhymst.setWwmsPrevno("");
            }

            if (!UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsPrevperson())) {
               bdmTlWhywhymst.setWwmsPrevperson("");
            }

            request.setAttribute("kznWhyWhyMst", bdmTlWhywhymst);
            request.setAttribute("yyFormBean", yyFormBean);
            CommonMessage.debugMsg("before call jsp................................");
            counterMeasureFlag = this.yyService.checkCounterMsr();
            if (UIUtils.isValidKeyId(counterMeasureFlag)) {
               request.setAttribute("counterMeasureFlag", counterMeasureFlag);
            }

            request.setAttribute("filterStr", filterStr);
            request.setAttribute("mode", yymode);
            request.setAttribute("refdoctype", totalCount);
            CommonMessage.debugMsg("refdoctype" + totalCount);
            UIUtils.forwardRequest(request, response, "/pages/WhyWhyAnalysisMainFormGrid.jsp");
         } else if (action.equals("yy_getCol.why")) {
            out = response.getWriter();
            httpSession = request.getSession(false);
            httpSession.removeAttribute("yyServletWmsKey");
            httpSession.removeAttribute("yyServletfinalAction");
            httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
            httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
            CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
            out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcolModel"));
         } else {
            Object yyList;
            String[] yyDatas;
            JSONObject tblJSONObj;
            if (action.equals("yy_getData.why")) {
               try {
                  out = response.getWriter();
                  httpSession = request.getSession(false);
                  path = (String)httpSession.getAttribute("yyServletWmsKey");
                  imagePath = (String)httpSession.getAttribute("yyServletfinalAction");
                  CommonMessage.debugMsg("wmsKey " + path);
                  CommonMessage.debugMsg("finalAction " + imagePath);
                  yyList = new ArrayList();
                  if (UIUtils.isValidKeyId(path)) {
                     CommonMessage.debugMsg("Execute Function ");
                     yyList = this.breakDownService.getYY(path);
                  } else {
                     yyDatas = new String[]{imagePath, "{}", "{}", "{}", "{}"};
                     ((List)yyList).add(yyDatas);
                  }

                  CommonMessage.debugMsg("yylist roopa STTART:" + yyList);
                  tblJSONObj = UIUtils.convertToJqGridTableObject((List)yyList, request, 0, 0);
                  CommonMessage.debugMsg("yylist roopa:" + tblJSONObj);
                  out.println(tblJSONObj);
               } catch (Exception var38) {
                  CommonMessage.debugMsg(var38.getMessage());
               }
            } else if (action.equals("yykaizen_getCol.why")) {
               out = response.getWriter();
               httpSession = request.getSession(false);
               httpSession.removeAttribute("yyKaizenServletWmsKey");
               httpSession.removeAttribute("yyKaizenServletfinalAction");
               httpSession.setAttribute("yyKaizenServletWmsKey", request.getParameter("wwmsKey"));
               httpSession.setAttribute("yyKaizenServletfinalAction", request.getParameter("finalAction"));
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYKaizencolModel"));
            } else if (action.equals("yykaizen_getData.why")) {
               try {
                  out = response.getWriter();
                  httpSession = request.getSession(false);
                  path = (String)httpSession.getAttribute("yyKaizenServletWmsKey");
                  imagePath = (String)httpSession.getAttribute("yyKaizenServletfinalAction");
                  yyList = new ArrayList();
                  if (UIUtils.isValidKeyId(path)) {
                     yyList = this.breakDownService.getYY(path);
                  } else {
                     yyDatas = new String[]{imagePath, "{}", "{}", "{}", "{}"};
                     ((List)yyList).add(yyDatas);
                  }

                  tblJSONObj = UIUtils.convertToJqGridTableObject((List)yyList, request, 0, 0);
                  CommonMessage.debugMsg("" + tblJSONObj);
                  out.println(tblJSONObj);
               } catch (Exception var37) {
                  CommonMessage.debugMsg(var37.getMessage());
               }
            } else if (action.equals("yyShe_getCol.why")) {
               out = response.getWriter();
               httpSession = request.getSession(false);
               httpSession.removeAttribute("yySheServletWmsKey");
               httpSession.removeAttribute("yySheServletPhen");
               httpSession.setAttribute("yySheServletWmsKey", request.getParameter("wwmsKey"));
               httpSession.setAttribute("yySheServletPhen", request.getParameter("phenomena"));
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYSHEcolModel"));
            } else if (action.equals("yyShe_getData.why")) {
               try {
                  out = response.getWriter();
                  httpSession = request.getSession(false);
                  path = (String)httpSession.getAttribute("yySheServletWmsKey");
                  imagePath = (String)httpSession.getAttribute("yySheServletPhen");
                  CommonMessage.debugMsg("wmsKey " + path);
                  CommonMessage.debugMsg("phen " + imagePath);
                  yyList = new ArrayList();
                  if (UIUtils.isValidKeyId(path)) {
                     CommonMessage.debugMsg("Execute Function ");
                     yyList = this.breakDownService.getYY(path);
                  } else {
                     yyDatas = new String[]{imagePath, "{}", "{}", "{}", "{}"};
                     ((List)yyList).add(yyDatas);
                  }

                  tblJSONObj = UIUtils.convertToJqGridTableObject((List)yyList, request, 0, 0);
                  CommonMessage.debugMsg("" + tblJSONObj);
                  out.println(tblJSONObj);
               } catch (Exception var36) {
                  CommonMessage.debugMsg(var36.getMessage());
               }
            } else if (action.equals("yyCC_getCol.why")) {
               out = response.getWriter();
               httpSession = request.getSession(false);
               httpSession.removeAttribute("yyServletWmsKey");
               httpSession.removeAttribute("yyServletfinalAction");
               httpSession.setAttribute("yyServletWmsKey", request.getParameter("wwmsKey"));
               httpSession.setAttribute("yyServletfinalAction", request.getParameter("finalAction"));
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.YYColModel", "YYcccolModel"));
            } else if (action.equals("rootcause_getCol.why")) {
               out = response.getWriter();
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel"));
            } else if (action.equals("rootcause_getData.why")) {
               try {
                  CommonMessage.debugMsg("ROOTCAUSE_getdata");
                  rowId = request.getParameter("openMode");
                  out = response.getWriter();
                  analysis = this.yyService.getRootCause(rowId);
                  analysisJson = UIUtils.convertToJqGridTableObject(analysis, request, 0, 0);
                  CommonMessage.debugMsg("" + analysisJson);
                  out.println(analysisJson);
               } catch (Exception var35) {
                  CommonMessage.debugMsg(var35.getMessage());
               }
            } else if (action.equals("rootcauseBD_getCol.why")) {
               out = response.getWriter();
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "newColModel2"));
            } else if (action.equals("rootcauseBD_getData.why")) {
               try {
                  CommonMessage.debugMsg("ROOTCAUSE_getdata");
                  rowId = request.getParameter("openMode");
                  out = response.getWriter();
                  analysis = this.yyService.getRootCause(rowId);
                  analysisJson = UIUtils.convertToJqGridTableObject(analysis, request, 0, 0);
                  CommonMessage.debugMsg("" + analysisJson);
                  out.println(analysisJson);
               } catch (Exception var34) {
                  CommonMessage.debugMsg(var34.getMessage());
               }
            } else if (action.equals("pillar_getCol.why")) {
               out = response.getWriter();
               CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
               out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarColModel"));
            } else {
               ArrayList rootCauseList;
               if (action.equals("pillar_getData.why")) {
                  try {
                     CommonMessage.debugMsg("PILLAR");
                     rowId = request.getParameter("wwmsKey");
                     format = request.getParameter("yyOpenFrom");
                     CommonMessage.debugMsg("PILLAR : " + rowId);
                     out = response.getWriter();
                     rootCauseList = new ArrayList();
                     if (UIUtils.isValidKeyId(rowId)) {
                        Proposed = this.yyService.getPillar(rowId, format);
                        Proposed = fillPillarDatas(Proposed);
                     } else {
                        Proposed = fillPillar(rootCauseList, format);
                     }

                     ProposedJson = UIUtils.convertToJqGridTableObject(Proposed, request, 0, 0);
                     CommonMessage.debugMsg("" + ProposedJson);
                     out.println(ProposedJson);
                  } catch (Exception var33) {
                     CommonMessage.debugMsg(var33.getMessage());
                  }
               } else if (action.equals("pillarBD_getCol.why")) {
                  out = response.getWriter();
                  CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
                  out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "pillarBDColModel"));
               } else if (action.equals("pillarBD_getData.why")) {
                  try {
                     CommonMessage.debugMsg("PILLAR");
                     rowId = request.getParameter("wwmsKey");
                     format = request.getParameter("yyOpenFrom");
                     CommonMessage.debugMsg("PILLAR : " + rowId);
                     out = response.getWriter();
                     rootCauseList = new ArrayList();
                     if (UIUtils.isValidKeyId(rowId)) {
                        Proposed = this.yyService.getPillar(rowId, format);
                        Proposed = fillPillarDatas(Proposed);
                     } else {
                        Proposed = fillPillar(rootCauseList, format);
                     }

                     ProposedJson = UIUtils.convertToJqGridTableObject(Proposed, request, 0, 0);
                     CommonMessage.debugMsg("" + ProposedJson);
                     out.println(ProposedJson);
                  } catch (Exception var32) {
                     CommonMessage.debugMsg(var32.getMessage());
                  }
               } else if (action.equals("whywhyanalysismodify_delete.why")) {
                  CommonMessage.debugMsg("delete");
                  out = response.getWriter();
                  format = request.getParameter("keyid");
                  path = request.getParameter("refdocid");
                  CommonMessage.debugMsg("delete1==" + format);

                  try {
                     if (UIUtils.isValidKeyId(format)) {
                        CommonMessage.debugMsg("delete2");
                        BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();
                        bdmTlWhywhymst.setWwmsKeyid(format);
                        bdmTlWhywhymst.setWwmsRefdocno(path);
                        this.yyService.delete(bdmTlWhywhymst);
                        ProposedJson = new JSONObject();
                        ProposedJson.put("msg", "Deleted Successfully");
                        out.print(ProposedJson.toString());
                        Proposed = null;
                     }
                  } catch (BusinessApplicationExceptions var31) {
                     ProposedJson = UIUtils.businessValidationExceptions(var31.toString(), "WhyValidation");
                     out.print(ProposedJson.toString());
                  }
               } else if (action.equals("whywhyanalysismodify_save.why")) {
                  CommonMessage.debugMsg("Inside YY Save");
                  yyFormBean = new YYFormBean();
                  this.saveYY(request, response, yyFormBean);
                  CommonMessage.debugMsg("Inside xx Save");
               } else if (action.equals("doneByLink_save.why")) {
                  CommonMessage.debugMsg("Inside doneByLink_save.why");
                  yyFormBean = new YYFormBean();
                  this.saveDonebyLink(request, response, yyFormBean);
               } else if (action.equals("YYDoneBy_delete.why")) {
                  this.deleteYYDoneBy(request, response);
               } else if (action.equals("whywhyEffectiveness_save.why")) {
                  this.saveWhyWhyEffectiveness(request, response);
               } else if (action.equals("whywhyAna_save.why")) {
                  CommonMessage.debugMsg("Inside YY Save");
                  new YYFormBean();
               } else if (action.equals("WhyWhy_delete.why")) {
                  out = response.getWriter();
                  format = request.getParameter("keyid");
                  CommonMessage.debugMsg("delete1==" + format);
                  path = request.getParameter("rowId");
                  if (UIUtils.isValidKeyId(format)) {
                     new BdmTlWhywhydtl();
                     this.yyService.deleteYYDtl(format);
                     ProposedJson = new JSONObject();
                     ProposedJson.put("msg", "Deleted Successfully");
                     ProposedJson.put("formClear", true);
                     ProposedJson.put("rowId", path);
                     out.print(ProposedJson.toString());
                  }
               } else if (action.equals("functionalLoc.why")) {
                  httpSession = request.getSession(false);
                  FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
                  functLocFieldNameBean.setFactory("cmbwwmsFactoryid");
                  functLocFieldNameBean.setSection("cmbwwmsSectionid");
                  functLocFieldNameBean.setCell("cmbwwmsCellid");
                  functLocFieldNameBean.setMachine("cmbwwmsMachineid");
                  functLocFieldNameBean.setFunctionalLocId("txtWwmsFlid");
                  functLocFieldNameBean.setFactMandatory(false);
                  functLocFieldNameBean.setSectMandatory(false);
                  functLocFieldNameBean.setCellMandatory(true);
                  functLocFieldNameBean.setMachMandatory(false);
                  FormModes formModes = (FormModes)httpSession.getAttribute("formMode");
                  UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
               } else if (action.equals("combo_yy.why")) {
                  try {
                     new ComboFilter();
                     CommonMessage.debugMsg("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
                     ComboFilter comboFilter = UIUtils.fillComboFilter(request);
                     yy = this.whywhyService.getYY("", comboFilter);
                     UIUtils.writeComboBox(response, yy, comboFilter);
                  } catch (Exception var30) {
                     var30.printStackTrace();
                  }
               } else if (action.equals("whywhyanalysisgrid_getCol.why")) {
                  try {
                     out = response.getWriter();
                     format = request.getParameter("masterkeyid");
                     analysis = this.whywhyService.getanalysis(format);
                     analysisJson = this.getTableModelAnalyis(analysis);
                     out.println(analysisJson);
                  } catch (Exception var29) {
                     var29.printStackTrace();
                  }
               } else if (action.equals("whywhyanalysisgrid_getData.why")) {
                  try {
                     out = response.getWriter();
                     format = request.getParameter("masterkeyid");
                     analysis = this.whywhyService.getanalysis(format);
                     analysisJson = UIUtils.convertToJqGridTableObject(analysis, request, 1, 0);
                     out.println(analysisJson);
                  } catch (Exception var28) {
                     CommonMessage.debugMsg(var28.getMessage());
                  }
               } else if (action.equals("whywhyanalysismaingrid_getCol.why")) {
                  out = response.getWriter();
                  httpSession = request.getSession(false);
                  httpSession.removeAttribute("whywhyanalysisCommonFilter");
                  path = request.getParameter("refdocid");
                  commonFilter = this.populateCommonFilter(request, "whywhyanalysisCommonFilter", true);
                  response.setContentType("text/html");
                  if (UIUtils.isValidKeyId(path)) {
                     commonFilter.setRefdocid(path);
                  }

                  AbnDataList = this.yyService.getAllWhywhy(commonFilter);
                  jqGridTableModel = new JqGridTableModel();
                  gridColModel = new GridColModel();
                  jqGridTableModel.setRowNumbers(true);
                  jqGridTableModel.setEnableFilter(true);
                  jqGridTableModel.setTableButton(true);
                  gridColModel.setHeaderNum(1);
                  colHeader = (String[])AbnDataList.get(2);
                  colHeaderCond = (String[])AbnDataList.get(1);
                  headers = new ArrayList();
                  headers.add(colHeader);
                  jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
                  jsonObject.put("tableHeight", "83%%");
                  jsonObject.put("tableWidth", "109%%");
                  httpSession.setAttribute("ColModel", jsonObject);
                  httpSession.setAttribute("whywhyanalysisCommonFilter", commonFilter);
                  out.println(jsonObject);
               } else if (action.equals("whywhyanalysismaingrid_getData.why")) {
                  try {
                     httpSession = request.getSession(false);
                     format = request.getParameter("refdocid");
                     commonFilter = this.populateCommonFilter(request, "whywhyanalysisCommonFilter", false);
                     if (UIUtils.isValidKeyId(format)) {
                        commonFilter.setRefdocid(format);
                     }

                     Proposed = this.yyService.getAllWhywhy(commonFilter);
                      out = response.getWriter();
                     tblJSONObj = UIUtils.convertToJqGridTableObject(Proposed, request, 3, 0, commonFilter.getTotalRecordCnt());
                     httpSession.removeAttribute("whywhyanalysisCommonFilter");
                     out.println(tblJSONObj);
                     CommonMessage.debugMsg(tblJSONObj);
                  } catch (Exception var27) {
                     CommonMessage.debugMsg(var27.getMessage());
                  }
               } else if (action.equals("whywhyproposedgrid_getCol.why")) {
                  try {
                     out = response.getWriter();
                     format = request.getParameter("masterkeyid");
                     commonFilter = this.populateCommonFilter(request, "whywhyanalysisCommonFilter", true);
                     commonFilter.setYyNo(format);
                     Proposed = this.whywhyService.getProposed(commonFilter);
                     ProposedJson = this.getTableModelProposed(Proposed);
                     out.println(ProposedJson);
                  } catch (Exception var26) {
                     var26.printStackTrace();
                  }
               } else if (action.equals("whywhyproposedgrid_getData.why")) {
                  try {
                     out = response.getWriter();
                     format = request.getParameter("masterkeyid");
                     commonFilter = this.populateCommonFilter(request, "whywhyanalysisCommonFilter", false);
                     commonFilter.setYyNo(format);
                     Proposed = this.whywhyService.getProposed(commonFilter);
                     ProposedJson = UIUtils.convertToJqGridTableObject(Proposed, request, 3, 0);
                     out.println(ProposedJson);
                  } catch (Exception var25) {
                     CommonMessage.debugMsg(var25.getMessage());
                  }
               } else if (action.equals("whywhyReport_getExcel.why")) {
                  CommonMessage.debugMsg("1111111111111111list      :::: 111");
                  httpSession = request.getSession(false);
                  commonFilter = (CommonFilter)httpSession.getAttribute("whywhyCommonFilter");
                  this.whywhyService.getAllwhywhyStd(commonFilter);
                  imagePath = "whywhyRpt.xls";
                  end = UIUtils.getPropertyValue("com.akranta.tpm.resources.whywhyRpt", "whywhyRpt");
                  tblJSONObj = JSONObject.fromString(end);
                  new ExcelUtils(tblJSONObj);
               } else if (action.equals("whywhyReport_view.why")) {
                  try {
                     rowId = request.getParameter("rowId");
                     format = ExcelUtils.getFormat(request);
                     path = UIUtils.getExcelTemplatePath(request);
                     imagePath = UIUtils.getImagePath(request);
                     format = "xlsx";
                     wb = this.whywhyService.getAllwhywhyRptExl(rowId, format, path, imagePath);
                     ExcelUtils.writeToResponse(response, wb, "WhyWhyReport", format);
                  } catch (Exception var24) {
                     CommonMessage.debugMsg("err:" + var24.getMessage());
                     out = response.getWriter();
                     err = new JSONObject();
                     err.put("message", "Data Not Found");
                     out.print(err.toString());
                  }
               } else if (action.equals("WhyWhyTrainingList_input.why")) {
                  rowId = request.getParameter("flid");
                  request.setAttribute("flid", rowId);
                   rd = request.getRequestDispatcher("/pages/WhyWhyTrainingList.jsp");
                  rd.forward(request, response);
               } else if (!action.equals("WhyWhyTrainingList_view.why")) {
                  if (action.equals("WhyWhyTrainingList_getCol.why")) {
                     CommonMessage.debugMsg("WhyWhyTrainingList_getCol.why");
                     out = response.getWriter();
                     new JSONObject();
                     out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.RootCauseColModel", "ProgramColModel"));
                  } else if (action.equals("WhyWhyTrainingList_getData.why")) {
                     try {
                        CommonMessage.debugMsg("WhyWhyTrainingList_getData.why");
                        out = response.getWriter();
                        format = request.getParameter("rows");
                        path = request.getParameter("page");
                        imagePath = "1";
                        end = "100";
                        int totalRows;
                        if (!path.equals("1")) {
                           int rowStart = Integer.parseInt(format) * Integer.parseInt(path) - 99;
                           totalRows = Integer.parseInt(format) * Integer.parseInt(path);
                           imagePath = Integer.toString(rowStart);
                           end = Integer.toString(totalRows);
                        }

                        totalCount = "5";
                        totalRows = 200;
                        List<String[]> childForParent = this.yyService.getProgramList(imagePath, end);
                        if (UIUtils.isValidKeyId(totalCount)) {
                           totalRows = Integer.parseInt(totalCount);
                        }

                        JSONObject childForParentData = UIUtils.convertToJqGridTableObject(childForParent, request, 0, 0, (long)totalRows);
                        CommonMessage.debugMsg("childForParentData:" + childForParentData);
                        out.println(childForParentData);
                     } catch (Exception var23) {
                        var23.printStackTrace();
                     }
                  } else if (action.equals("WhyWhyTraining_input.why")) {
                     rd = request.getRequestDispatcher("/pages/WhyWhyTrainingPopup.jsp");
                     rd.forward(request, response);
                  } else if (action.equals("whywhyQtyReport_view.why")) {
                     try {
                        rowId = request.getParameter("rowId");
                        format = ExcelUtils.getFormat(request);
                        path = UIUtils.getExcelTemplatePath(request);
                        imagePath = UIUtils.getImagePath(request);
                        format = "xlsx";
                        wb = this.whywhyService.getAllwhywhyQtyRptExl(rowId, format, path, imagePath);
                        ExcelUtils.writeToResponse(response, wb, "WhyWhyReport", format);
                     } catch (Exception var22) {
                        var22.printStackTrace();
                        CommonMessage.debugMsg("err:" + var22.getMessage());
                        out = response.getWriter();
                        err = new JSONObject();
                        err.put("message", "Data Not Found");
                        out.print(err.toString());
                     }
                  } else if (action.equals("filterXmlwhywhyRptGenDrill_view.why")) {
                     response.setContentType("xml");
                     CommonMessage.debugMsg("action " + action);
                     UIUtils.forwardRequest(request, response, "/tiles/xml/WhyWhyAnalysis.xml");
                  } else if (action.startsWith("whywhyRptGenDrill")) {
                     this.whywhyIdentifiedDrillDownReport(request, response);
                  }
               }
            }
         }
      }

   }

   private void saveWhyWhyEffectiveness(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      CommonMessage.debugMsg("yyService" + this.yyService);
      if (httpSession != null && user != null) {
         BdmTlYyeffectivemst existBdmTlYyeffectivemst = (BdmTlYyeffectivemst)httpSession.getAttribute("BdmTlWhywhyMst");
         BdmTlYyeffectivemst newBdmTlYyeffectivemst = new BdmTlYyeffectivemst();
         newBdmTlYyeffectivemst.setYyefCreatedby(user.getUsrm_ccno());
         newBdmTlYyeffectivemst = (BdmTlYyeffectivemst)UIUtils.setBeanProperties(newBdmTlYyeffectivemst, request);
         CommonMessage.debugMsg("YY Grid = " + request.getParameter("gridData"));
         String yyGrid = request.getParameter("gridData");
         JSONArray jsonArray = JSONArray.fromString(yyGrid);
         BdmTlYyeffectivedtl newBdmTlYyeffectivedtl = new BdmTlYyeffectivedtl();
         newBdmTlYyeffectivedtl.setYyedCreatedby(user.getUsrm_ccno());
         List<BdmTlYyeffectivedtl> yyDetail = (List<BdmTlYyeffectivedtl>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivedtl, jsonArray);
         List<BdmTlYyeffectivemst> yyMst = (List<BdmTlYyeffectivemst>) UIUtils.convertJSONArrToList(newBdmTlYyeffectivemst, jsonArray);
         if (newBdmTlYyeffectivedtl != null) {
            newBdmTlYyeffectivemst.setBdmTlYyeffectivedtl(yyDetail);
            newBdmTlYyeffectivemst.setBdmTlYyeffectivemst(yyMst);
         } 

         CommonMessage.debugMsg(" Checking value save :: " + newBdmTlYyeffectivemst.getYyefKeyid());
         
         CommonMessage.debugMsg(" newBdmTlYyeffectivedtl.getYyedCountermesdate() :: " + newBdmTlYyeffectivedtl.getYyedCountermesdate());

         try {
            if (!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefKeyid())) {
               CommonMessage.debugMsg("create");
               existBdmTlYyeffectivemst = this.yyService.yyEffectivenessCreate(newBdmTlYyeffectivemst, existBdmTlYyeffectivemst);
            } else {
               CommonMessage.debugMsg("update");
               existBdmTlYyeffectivemst = this.yyService.yyEffectivenessUpdate(newBdmTlYyeffectivemst, existBdmTlYyeffectivemst);
            }

            JSONObject persistentData = new JSONObject();
            persistentData.put("cmbWwmsKeyid", existBdmTlYyeffectivemst.getYyefKeyid());
            String formType = (String)httpSession.getAttribute("WHYWHYFORMTYPE");
            if (UIUtils.isValidKeyId(formType)) {
               persistentData.put("txtFormType", formType);
            }

            JSONObject forwardData = new JSONObject();
            CommonMessage.debugMsg(" Form Mode");
            JSONObject successData = new JSONObject();
            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
            successData.put("keyId", existBdmTlYyeffectivemst.getYyefKeyid());
            JSONObject returnData = new JSONObject();
            returnData.put("formClear", false);
            returnData.put("forwardData", forwardData);
            returnData.put("persistentData", persistentData);
            returnData.put("successData", successData);
            CommonMessage.debugMsg(" returnData.toString() " + returnData.toString());
            out.print(returnData.toString());
         } catch (BusinessApplicationExceptions var18) {
         } catch (ValidationExceptions var19) {
            JSONObject errMessage = UIUtils.validationExceptions(var19.toString(), "WhyValidation");
            errMessage.put("tpmException", "Data Not Saved");
            out.print(errMessage.toString());
         }
      }

   }

   private void deleteYYProbAttBy(HttpServletRequest request, HttpServletResponse response) throws Exception, BusinessApplicationExceptions {
      PrintWriter out = response.getWriter();
      String keyId = request.getParameter("keyid");

      JSONObject successData;
      try {
         this.yyService.deleteYYProbAttBy(keyId);
         successData = new JSONObject();
         successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
         out.print(successData.toString());
      } catch (BusinessApplicationExceptions var8) {
         successData = new JSONObject();
         successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
         JSONObject returnData = new JSONObject();
         returnData.put("formClear", false);
         returnData.put("msg", " Record Can't be Deleted Reference Found");
         out.print(returnData.toString());
      }

   }

   private void saveprobAttbyLink(HttpServletRequest request, HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      CommonMessage.debugMsg("yyService" + this.yyService);
      if (httpSession != null && user != null) {
         BdmTlYyproblemattbymst existBdmTlYyproblemattbymst = (BdmTlYyproblemattbymst)httpSession.getAttribute("BdmTlWhywhyMst");
         BdmTlYyproblemattbymst newBdmTlYyproblemattbymst = new BdmTlYyproblemattbymst();
         newBdmTlYyproblemattbymst = (BdmTlYyproblemattbymst)UIUtils.setBeanProperties(newBdmTlYyproblemattbymst, request);
         newBdmTlYyproblemattbymst.setWwpaCreatedby(user.getUsrm_ccno());
         String yyKeyid = request.getParameter("yyKeyid");
         String yypab = request.getParameter("yypab");
         if (UIUtils.isValidKeyId(yypab)) {
            newBdmTlYyproblemattbymst.setWwpaEmpmKeyid(yypab);
         }

         if (UIUtils.isValidKeyId(yyKeyid)) {
            newBdmTlYyproblemattbymst.setWwpaWwmsKeyid(yyKeyid);
         }

         JSONObject forwardData;
         try {
            if (!UIUtils.isValidKeyId(newBdmTlYyproblemattbymst.getWwpaKeyid())) {
               CommonMessage.debugMsg("create");
               existBdmTlYyproblemattbymst = this.yyService.yyProbAttCreate(newBdmTlYyproblemattbymst, existBdmTlYyproblemattbymst);
            }

            JSONObject persistentData = new JSONObject();
            persistentData.put("cmbWwmsKeyid", existBdmTlYyproblemattbymst.getWwpaKeyid());
            forwardData = new JSONObject();
            CommonMessage.debugMsg(" Form Mode");
            JSONObject successData = new JSONObject();
            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
            successData.put("keyId", existBdmTlYyproblemattbymst.getWwpaKeyid());
            JSONObject returnData = new JSONObject();
            returnData.put("formClear", false);
            returnData.put("forwardData", forwardData);
            returnData.put("persistentData", persistentData);
            returnData.put("successData", successData);
            CommonMessage.debugMsg(" returnData.toString() " + returnData.toString());
            out.print(returnData.toString());
         } catch (ValidationExceptions var15) {
            var15.printStackTrace();
            forwardData = UIUtils.validationExceptions(var15.toString(), "WhyValidation");
            out.print(forwardData.toString());
         } catch (BusinessApplicationExceptions var16) {
            CommonMessage.debugMsg("Error Servler e -" + var16.toString());
            forwardData = UIUtils.businessValidationExceptions(var16.toString(), "WhyValidation");
            out.print(forwardData.toString());
            CommonMessage.debugMsg(" e " + forwardData);
         } catch (Exception var17) {
            var17.printStackTrace();
            forwardData = new JSONObject();
            forwardData.put("tpmException", "Data Not Saved");
            out.print(forwardData.toString());
         }
      }

   }

   private JSONObject getProbAttbyTableModel(List<String[]> yyDonebyList) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader = (String[])yyDonebyList.get(0);
      jqGridTableModel.getRowHeaders().add(colHeader);
      jqGridTableModel.setRowNumbers(false);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setTableWidth(600);
      jqGridTableModel.setTableButton(false);
      jqGridTableModel.setRowNumbers(true);

      for(int i = 0; i < colHeader.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setWidth(200);
         jqGridColModel.setAlign("left");
         jqGridColModel.setEditable(false);
         if (i <= 2) {
            jqGridColModel.setHidden(true);
            jqGridColModel.setWidth(100);
            jqGridColModel.setAlign("left");
         }

         if (i == colHeader.length - 1) {
            jqGridColModel.setFormatter("BtnFormatterDelete");
            jqGridColModel.setWidth(40);
            jqGridColModel.setAlign("center");
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "30%%");
      tableModel.set("tableWidth", "26%%");
      return tableModel;
   }

   private void deleteYYDoneBy(HttpServletRequest request, HttpServletResponse response) throws Exception, BusinessApplicationExceptions {
      PrintWriter out = response.getWriter();
      String keyId = request.getParameter("keyid");

      JSONObject successData;
      try {
         this.yyService.deleteYYDoneBy(keyId);
         successData = new JSONObject();
         successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
         out.print(successData.toString());
      } catch (BusinessApplicationExceptions var8) {
         successData = new JSONObject();
         successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
         JSONObject returnData = new JSONObject();
         returnData.put("formClear", false);
         returnData.put("msg", " Record Can't be Deleted Reference Found");
         out.print(returnData.toString());
      }

   }

   private void saveDonebyLink(HttpServletRequest request, HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      CommonMessage.debugMsg("yyService" + this.yyService);
      if (httpSession != null && user != null) {
         BdmTlYydonebymst existBdmTlYydonebymst = (BdmTlYydonebymst)httpSession.getAttribute("BdmTlWhywhyMst");
         BdmTlYydonebymst newBdmTlYydonebymst = new BdmTlYydonebymst();
         newBdmTlYydonebymst = (BdmTlYydonebymst)UIUtils.setBeanProperties(newBdmTlYydonebymst, request);
         newBdmTlYydonebymst.setWwdbCreatedby(user.getUsrm_ccno());
         String yyKeyid = request.getParameter("yyKeyid");
         String yyDonebyid = request.getParameter("yyDonebyid");
         if (UIUtils.isValidKeyId(yyDonebyid)) {
            newBdmTlYydonebymst.setWwdbEmpmKeyid(yyDonebyid);
         }

         if (UIUtils.isValidKeyId(yyKeyid)) {
            newBdmTlYydonebymst.setWwdbWwmsKeyid(yyKeyid);
         }

         JSONObject forwardData;
         try {
            if (!UIUtils.isValidKeyId(newBdmTlYydonebymst.getWwdbKeyid())) {
               CommonMessage.debugMsg("create");
               existBdmTlYydonebymst = this.yyService.yyDonebyCreate(newBdmTlYydonebymst, existBdmTlYydonebymst);
            }

            JSONObject persistentData = new JSONObject();
            persistentData.put("cmbWwmsKeyid", existBdmTlYydonebymst.getWwdbKeyid());
            forwardData = new JSONObject();
            CommonMessage.debugMsg(" Form Mode");
            JSONObject successData = new JSONObject();
            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
            successData.put("keyId", existBdmTlYydonebymst.getWwdbKeyid());
            JSONObject returnData = new JSONObject();
            returnData.put("formClear", false);
            returnData.put("forwardData", forwardData);
            returnData.put("persistentData", persistentData);
            returnData.put("successData", successData);
            CommonMessage.debugMsg(" returnData.toString() " + returnData.toString());
            out.print(returnData.toString());
         } catch (ValidationExceptions var15) {
            var15.printStackTrace();
            forwardData = UIUtils.validationExceptions(var15.toString(), "WhyValidation");
            out.print(forwardData.toString());
         } catch (BusinessApplicationExceptions var16) {
            CommonMessage.debugMsg("Error Servler e -" + var16.toString());
            forwardData = UIUtils.businessValidationExceptions(var16.toString(), "WhyValidation");
            out.print(forwardData.toString());
            CommonMessage.debugMsg(" e " + forwardData);
         } catch (Exception var17) {
            var17.printStackTrace();
            forwardData = new JSONObject();
            forwardData.put("tpmException", "Data Not Saved");
            out.print(forwardData.toString());
         }
      }

   }

   private JSONObject getTableModel(List<String[]> whyReportList, CommonFilter commonFilter) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader = (String[])whyReportList.get(0);
      jqGridTableModel.getRowHeaders().add(colHeader);
      jqGridTableModel.setTableButton(true);
      jqGridTableModel.setRowNumbers(true);
      jqGridTableModel.setEnableFilter(true);

      for(int i = 0; i < colHeader.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setWidth(100);
         jqGridColModel.setAlign("left");
         jqGridColModel.setEditable(false);
         CommonMessage.debugMsg("colheader  " + i + " : " + colHeader[i]);
         if (i == 5 || i == 2 || i == 3 || i == 4) {
            jqGridColModel.setWidth(160);
         }

         if (i == 1) {
            jqGridColModel.setHidden(true);
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      return tableModel;
   }

   private void savewhy(HttpServletRequest request, HttpServletResponse response, YYFormBean yyFormBean) throws Exception {
      CommonMessage.debugMsg("nbj");
      CommonMessage.debugMsg("inside save");
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);

      try {
         if (httpSession != null && user != null) {
            BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();
            newBdmTlWhywhymst = (BdmTlWhywhymst)UIUtils.setBeanProperties(newBdmTlWhywhymst, request);
            BdmTlWhywhymst existBdmTlWhywhymst = (BdmTlWhywhymst)httpSession.getAttribute("newBdmTlWhywhymst");
            boolean insert = true;
            String savemsg;
            if (!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid())) {
               this.yyService.create1(newBdmTlWhywhymst, existBdmTlWhywhymst, yyFormBean);
               savemsg = "Data Saved Successfully";
            } else {
               insert = false;
               existBdmTlWhywhymst = this.yyService.update1(newBdmTlWhywhymst, existBdmTlWhywhymst, yyFormBean);
               savemsg = "Data Updated Successfully";
            }

            JSONObject successData = new JSONObject();
            successData.put("msg", savemsg);
            JSONObject returnData = new JSONObject();
            returnData.put("successData", successData);
            returnData.put("WwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
            returnData.put("formClear", false);
            out.print(returnData.toString());
            out.close();
         }
      } catch (ValidationExceptions var13) {
         JSONObject errMessage = UIUtils.validationExceptions(var13.toString(), "QtmstValidation");
         out.print(errMessage.toString());
      }

   }

   private JSONObject getTableModel_YYEffectiveness(List<String[]> yYEffectiveList) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader = (String[])yYEffectiveList.get(0);
      String[] emptyrow = new String[colHeader.length];

      int i;
      for(i = 0; i < colHeader.length; ++i) {
         emptyrow[i] = "";
      }

      jqGridTableModel.getRowHeaders().add(colHeader);
      jqGridTableModel.setSortable(false);
      jqGridTableModel.setTableButton(true);
      jqGridTableModel.setRowNumbers(true);

      for(i = 0; i < colHeader.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setWidth(100);
         jqGridColModel.setEditable(false);
         if (i == 0) {
            jqGridColModel.setWidth(80);
            jqGridColModel.setFormatter("checkFormatter");
         }

         if (i == 1) {
            jqGridColModel.setFormatter("dateFormatter");
         }

         if (i == 2) {
            jqGridColModel.setFormatter("comboFormatter");
            jqGridColModel.setWidth(250);
         }

         if (i == 7) {
            jqGridColModel.setWidth(200);
         }

         if (i == 9) {
            jqGridColModel.setWidth(300);
         }

         if (i == 10) {
            jqGridColModel.setFormatter("buttonFormatter");
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      return tableModel;
   }

   public static List<String[]> setRootCauseDatas() {
      List<String[]> rootCauseList = new ArrayList();
      List<String> fieldNames = new ArrayList();
      fieldNames.add("POOR BASIC CONDITION");
      fieldNames.add("POOR OPERATING CONDITION");
      fieldNames.add("DETERIORATION");
      fieldNames.add("WEAK DESIGN");
      fieldNames.add("POOR SKILL");

      for(int j = 0; j < 5; ++j) {
         String[] rootCause = new String[6];

         for(int i = 0; i < rootCause.length; ++i) {
            if (i == 1) {
               rootCause[i] = (String)fieldNames.get(j);
            } else {
               rootCause[i] = "{}";
            }
         }

         rootCauseList.add(rootCause);
      }

      return rootCauseList;
   }

   public static List<String[]> fillPillarDatas(List<String[]> pillarDatas) {
      List<String[]> rootCauseList = new ArrayList();

      byte k;
      int var7;
      for(Iterator var3 = pillarDatas.iterator(); var3.hasNext(); var7 = k + 1) {
         String[] pillar = (String[])var3.next();
         k = 0;
         String[] fieldName = new String[((String[])pillarDatas.get(k)).length];

         for(int i = 0; i < pillar.length; ++i) {
            if (i == 0) {
               fieldName[i] = pillar[i].replace("JHN", "JISHU HOZEN").replace("OPL", "OPL NO.").replace("KZN", "KAIZEN IDEA NO.").replace("PMC", "PM CALENDAR").replace("POK", "POKA YOKE").replace("4MT", "4M TYPE").replace("TRN", "TRAINING");
            } else {
               fieldName[i] = pillar[i];
            }
         }

         rootCauseList.add(fieldName);
      }

      return rootCauseList;
   }

   public static List<String[]> fillPillar(List<String[]> pillarDatas, String pillarFlag) {
      List<String[]> rootCauseList = new ArrayList();
      List<String> fieldNames = new ArrayList();
      if (UIUtils.isValidKeyId(pillarFlag)) {
         if (!pillarFlag.equals("SHE") && !pillarFlag.equals("DOCK")) {
            if (pillarFlag.equals("CC") || pillarFlag.equals("IMT")) {
               fieldNames.add("4M TYPE");
            }

            fieldNames.add("JISHU HOZEN");
            fieldNames.add("KAIZEN IDEA NO.");
            fieldNames.add("OPL NO.");
            fieldNames.add("PM CALENDAR");
            fieldNames.add("TRAINING");
         } else {
            fieldNames.add("KAIZEN");
            fieldNames.add("OJT");
            fieldNames.add("OPL");
            if (pillarFlag.equals("SHE")) {
               fieldNames.add("POKA YOKE");
            } else {
               fieldNames.add("SOP");
            }

            fieldNames.add("TRAINING");
         }
      } else {
         fieldNames.add("JISHU HOZEN");
         fieldNames.add("KAIZEN IDEA NO.");
         fieldNames.add("OPL NO.");
         fieldNames.add("PM CALENDAR");
         fieldNames.add("TRAINING");
      }

      for(int j = 0; j < fieldNames.size(); ++j) {
         String[] rootCause = new String[3];

         for(int i = 0; i < rootCause.length; ++i) {
            if (i == 0) {
               rootCause[i] = (String)fieldNames.get(j);
            } else if (i == 1) {
               rootCause[i] = "{}";
            } else {
               rootCause[i] = "";
            }

            CommonMessage.debugMsg(i + " : " + rootCause[i]);
         }

         rootCauseList.add(rootCause);
      }

      return rootCauseList;
   }

   private void saveYY(HttpServletRequest request, HttpServletResponse response, YYFormBean yyFormBean) throws BusinessApplicationExceptions, Exception {
      HttpSession httpSession = request.getSession(false);
      ServletOutputStream out = response.getOutputStream();
      AdmTlUsermst user = UIUtils.getLoginUser(request);
      JSONObject successData = new JSONObject();
      String Typenavigate = request.getParameter("Typenavigate");
      CommonMessage.debugMsg("yyService" + this.yyService);
      String counterMeasure = request.getParameter("hdnCounterMeasure");
      CommonMessage.debugMsg("counterMeasure..." + counterMeasure);
      if (httpSession != null && user != null) {
         BdmTlWhywhymst existBdmTlWhywhymst = (BdmTlWhywhymst)httpSession.getAttribute("BdmTlWhywhyMst");
         BdmTlWhywhymst newBdmTlWhywhymst = new BdmTlWhywhymst();
         newBdmTlWhywhymst.setWwmsCreatedby(user.getUsrm_ccno());
         newBdmTlWhywhymst = (BdmTlWhywhymst)UIUtils.setBeanProperties(newBdmTlWhywhymst, request);
         if (request.getParameter("YYAnalysis") != null && !request.getParameter("YYAnalysis").equals("")) {
            CommonMessage.debugMsg("YY Grid Value= " + request.getParameter("YYAnalysis"));
            String yyGrid = request.getParameter("YYAnalysis");
            JSONArray jsonArray = JSONArray.fromString(yyGrid);
            CommonMessage.debugMsg("jsonArray==" + jsonArray);
            BdmTlWhywhydtl newBdmTlWhywhydtl = new BdmTlWhywhydtl();
            newBdmTlWhywhydtl.setWwdtCreatedby(user.getUsrm_ccno());
            List<BdmTlWhywhydtl> yyDetail = (List<BdmTlWhywhydtl>) UIUtils.convertJSONArrToList(newBdmTlWhywhydtl, jsonArray);
            CommonMessage.debugMsg("jsonArray==" + jsonArray);
            if (newBdmTlWhywhydtl != null) {
               newBdmTlWhywhymst.setBdmTlWhywhydtl(yyDetail);
            }
         } else {
            CommonMessage.debugMsg("YY Grid : " + request.getParameter("YYAnalysis"));
         }

         yyFormBean = (YYFormBean)UIUtils.setBeanProperties(yyFormBean, request);
         CommonMessage.debugMsg("Pillar : " + yyFormBean.getWwmsPillarmode());

         JSONObject errMessage;
         try {
            if (!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid())) {
               CommonMessage.debugMsg("create");
               existBdmTlWhywhymst = this.yyService.create(newBdmTlWhywhymst, existBdmTlWhywhymst, yyFormBean);
            } else {
               CommonMessage.debugMsg("update");
               existBdmTlWhywhymst = this.yyService.update(newBdmTlWhywhymst, existBdmTlWhywhymst, yyFormBean);
            }

            JSONObject persistentData = new JSONObject();
            persistentData.put("cmbWwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
            String formType = (String)httpSession.getAttribute("WHYWHYFORMTYPE");
            String filemanager = request.getParameter("filemanager");
            if (UIUtils.isValidKeyId(formType)) {
               persistentData.put("txtFormType", formType);
            }

            JSONObject forwardData = new JSONObject();
            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsFactoryid())) {
               forwardData.put("factoryId", existBdmTlWhywhymst.getWwmsFactoryid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsSectionid())) {
               forwardData.put("sectionId", existBdmTlWhywhymst.getWwmsSectionid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsCellid())) {
               forwardData.put("cellId", existBdmTlWhywhymst.getWwmsCellid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsMachineid())) {
               forwardData.put("machineID", existBdmTlWhywhymst.getWwmsMachineid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsAssemblyid())) {
               forwardData.put("assemblyId", existBdmTlWhywhymst.getWwmsAssemblyid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsKeyid())) {
               forwardData.put("keyid", existBdmTlWhywhymst.getWwmsKeyid());
            }

            if (UIUtils.isValidKeyId(existBdmTlWhywhymst.getWwmsRefdocno())) {
               forwardData.put("bdId", existBdmTlWhywhymst.getWwmsRefdocno());
            }

            String getDocId = this.yyService.getDocId(existBdmTlWhywhymst.getWwmsRefdocno());
            CommonMessage.debugMsg("doccccccccccccccccccccccccccccccc " + getDocId);
            if (UIUtils.isValidKeyId(getDocId)) {
               forwardData.put("docId", getDocId);
            }

            CommonMessage.debugMsg("RootCause JH: " + existBdmTlWhywhymst.getWwmsIsjh());
            CommonMessage.debugMsg("RootCause PM: " + existBdmTlWhywhymst.getWwmsIspm());
            if (UIUtils.isValidKeyId(yyFormBean.getFormActionMode())) {
               String formBeanIdentifier = "YYFormBean" + yyFormBean.getFormActionMode();
               CommonMessage.debugMsg("formBeanIdentifier  " + formBeanIdentifier + "  formBeanIdentifier  " + formBeanIdentifier);
               httpSession.setAttribute(formBeanIdentifier, yyFormBean);
            }

            if (UIUtils.isValidKeyId(filemanager)) {
               successData.put("wwmsKeyId", existBdmTlWhywhymst.getWwmsKeyid());
               successData.put("wwmsRefdocno", existBdmTlWhywhymst.getWwmsRefdocno());
               successData.put("flid", newBdmTlWhywhymst.getWwmsFlid());
               CommonMessage.debugMsg(" Inside Flid servlet " + newBdmTlWhywhymst.getWwmsFlid());
               successData.put("Typenavigate", Typenavigate);
               successData.put("filemanager", true);
               successData.put("formClear", false);
            } else {
               successData.put("filemanager", false);
               successData.put("formClear", true);
            }

            JSONObject returnData = new JSONObject();
            CommonMessage.debugMsg(" Form Mode");
            httpSession.setAttribute("wwmsKeyid", existBdmTlWhywhymst.getWwmsKeyid());
            successData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));
            successData.put("keyId", existBdmTlWhywhymst.getWwmsKeyid());
            if (existBdmTlWhywhymst.getWwmsIskk().equals("Y")) {
               returnData.put("OpenForm", "Design");
            } else if (existBdmTlWhywhymst.getWwmsIsopl().equals("Y")) {
               returnData.put("OpenForm", "ET");
            } else if (existBdmTlWhywhymst.getWwmsIsjh().equals("Y")) {
               returnData.put("OpenForm", "JH");
            } else if (existBdmTlWhywhymst.getWwmsIspm().equals("Y")) {
               returnData.put("OpenForm", "PM");
            } else if (existBdmTlWhywhymst.getWwmsIspy().equals("Y")) {
               returnData.put("OpenForm", "PY");
            } else if (existBdmTlWhywhymst.getWwmsIsojt().equals("Y")) {
               returnData.put("OpenForm", "OJ");
            } else if (existBdmTlWhywhymst.getWwmsIssop().equals("Y")) {
               returnData.put("OpenForm", "SO");
            } else {
               returnData.put("OpenForm", "TRN");
               CommonMessage.debugMsg("ELSE ");
            }

            returnData.put("formClear", false);
            returnData.put("formMode", yyFormBean.getFormActionMode());
            returnData.put("Typenavigate", Typenavigate);
            returnData.put("forwardData", forwardData);
            returnData.put("persistentData", persistentData);
            returnData.put("successData", successData);
            returnData.put("counterMeasure", counterMeasure);
            CommonMessage.debugMsg(" returnData.toString() " + returnData.toString());
            out.print(returnData.toString());
         } catch (BusinessApplicationExceptions var18) {
            CommonMessage.debugMsg("BusinessApplicationExceptions......");
            errMessage = UIUtils.businessValidationExceptions(var18.toString(), "WhyValidation");
            out.print(errMessage.toString());
         } catch (ValidationExceptions var19) {
            errMessage = UIUtils.validationExceptions(var19.toString(), "WhyValidation");
            errMessage.put("tpmException", "Data Not Saved");
            out.print(errMessage.toString());
         }
      }

   }

   private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
      HttpSession httpSession = request.getSession(false);
      CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
      if (commonFilter != null && !createNew) {
         FilterValues.setPaginationParams(request, commonFilter);
         FilterValues.getCommonFilters(request, commonFilter);
      } else {
         commonFilter = new CommonFilter();
         commonFilter = FilterValues.getCommonFilters(request, commonFilter);
         if (beanIdentifier.equals("whywhyQtyCommonFilter")) {
            commonFilter = FilterValues.getQuality(request, commonFilter);
         } else {
            commonFilter = FilterValues.getBDRelated(request, commonFilter);
         }

         commonFilter.setViewClick('Y');
         httpSession.removeAttribute(beanIdentifier);
         httpSession.setAttribute(beanIdentifier, commonFilter);
      }

      if ("01-Jan-1801".contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
         commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
         commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
         commonFilter.setMonwise("Y");
      }

      return commonFilter;
   }

   private void fillYY(BdmTlWhywhymst bdmTlWhywhymst, WomTlWomst womTlWomst) {
      if (UIUtils.isValidKeyId(womTlWomst.getWomsActivityid())) {
         bdmTlWhywhymst.setWwmsRefdocno(womTlWomst.getWomsActivityid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsOccurreddate())) {
         bdmTlWhywhymst.setWwmsDate(womTlWomst.getWomsOccurreddate());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsMachineid())) {
         bdmTlWhywhymst.setWwmsMachineid(womTlWomst.getWomsMachineid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsAssemblyid())) {
         bdmTlWhywhymst.setWwmsAssemblyid(womTlWomst.getWomsAssemblyid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsFactoryid())) {
         bdmTlWhywhymst.setWwmsFactoryid(womTlWomst.getWomsFactoryid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsCellid())) {
         bdmTlWhywhymst.setWwmsCellid(womTlWomst.getWomsCellid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsSectionid())) {
         bdmTlWhywhymst.setWwmsSectionid(womTlWomst.getWomsSectionid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid())) {
         bdmTlWhywhymst.setWwmsPhenomenaid(womTlWomst.getWomsPhenomenaid());
      }

      if (UIUtils.isValidKeyId(womTlWomst.getWomsReportedby())) {
         bdmTlWhywhymst.setWwmsMaintinchargeid(womTlWomst.getWomsReportedby());
      }

   }

   private JSONObject getTableModel(List<String[]> headers) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] row = (String[])headers.get(0);
      String[] colHeader1 = (String[])headers.get(1);
      jqGridTableModel.getRowHeaders().add(colHeader1);
      jqGridTableModel.setTableButton(true);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setRowNumbers(true);
      jqGridTableModel.setEnableFilter(true);
      String headerSql = "'SELECT ";

      for(int i = 0; i < row.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
         jqGridColModel.setName(row[i].replaceAll(" ", ""));
         jqGridColModel.setEditable(false);
         CommonMessage.debugMsg(i + " : " + row[i]);
         if (i != 0 && i != 1) {
            if (i != 2 && i != 3) {
               if (i == 4) {
                  jqGridColModel.setWidth(80);
                  jqGridColModel.setAlign("center");
               } else if (i == 5) {
                  jqGridColModel.setWidth(300);
               } else if (i == 6) {
                  jqGridColModel.setWidth(100);
               } else if (i == 11 || i == 12 || i == 13) {
                  jqGridColModel.setWidth(100);
               }
            } else {
               jqGridColModel.setWidth(100);
            }
         } else {
            jqGridColModel.setHidden(true);
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
         headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel);
      }

      headerSql = headerSql.substring(0, headerSql.length() - 1) + " FROM DUAL '";
      CommonMessage.debugMsg("headerSql.....123..." + headerSql);
      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      CommonMessage.debugMsg(tableModel + "..................");
      return tableModel;
   }

   private JSONObject getTableModelAnalyis(List<String[]> headers) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader1 = (String[])headers.get(0);
      jqGridTableModel.getRowHeaders().add(colHeader1);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setRowNumbers(true);
      jqGridTableModel.setSortable(false);
      jqGridTableModel.setLoadOnce(true);

      for(int i = 0; i < colHeader1.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
         jqGridColModel.setEditable(false);
         jqGridColModel.setAlign("left");
         if (i == 0 || i == 1) {
            jqGridColModel.setHidden(true);
         }

         if (i == 2) {
            jqGridColModel.setWidth(420);
            jqGridColModel.setFormatter("txtFormatter");
         }

         if (i == 3) {
            jqGridColModel.setWidth(35);
            jqGridColModel.setFormatter("actionFormatterDel");
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "30%%");
      tableModel.set("tableWidth", "47%%");
      CommonMessage.debugMsg(tableModel + "..................");
      return tableModel;
   }

   private JSONObject getTableModelMainGrid(List<String[]> headers) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader1 = (String[])headers.get(0);
      jqGridTableModel.getRowHeaders().add(colHeader1);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setRowNumbers(true);

      for(int i = 0; i < colHeader1.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(colHeader1[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader1[i].replaceAll(" ", ""));
         jqGridColModel.setEditable(false);
         jqGridColModel.setAlign("left");
         jqGridColModel.setWidth(100);
         if (i == 1 || i == 2) {
            jqGridColModel.setWidth(165);
         }

         if (i == 3 || i == 4 || i == 5 || i == 6) {
            jqGridColModel.setWidth(185);
         }

         if (i == 0) {
            jqGridColModel.setHidden(true);
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "70%%");
      tableModel.set("tableWidth", "108%%");
      CommonMessage.debugMsg(tableModel + "..................");
      return tableModel;
   }

   private JSONObject getTableModelProposed(List<String[]> headers) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] row = (String[])headers.get(2);
      String[] colHeader1 = headers.get(1);
      String[] colHeader0 = headers.get(0);
      CommonMessage.debugMsg(colHeader1 + "..................");
      String[] tempCol = new String[row.length];
      jqGridTableModel.getRowHeaders().add(tempCol);
      jqGridTableModel.getRowHeaders().add(colHeader1);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setRowNumbers(true);

      for(int i = 0; i < colHeader1.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(tempCol[i] = "");
         jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
         jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));
         jqGridColModel.setIndex(colHeader0[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader0[i].replaceAll(" ", ""));
         jqGridColModel.setEditable(false);
         jqGridColModel.setAlign("left");
         jqGridColModel.setWidth(100);
         if (i == 0) {
            jqGridColModel.setWidth(252);
         }

         if (i == 5) {
            jqGridColModel.setWidth(100);
            jqGridColModel.setAlign("center");
            jqGridColModel.setFormatter("txtFormatter1");
         }

         if (i == 2) {
            jqGridColModel.setWidth(100);
            jqGridColModel.setFormatter("txtFormatter1");
         }

         if (i == 4) {
            jqGridColModel.setWidth(75);
         }

         if (i == 1) {
            jqGridColModel.setWidth(157);
         }

         if (i == colHeader1.length - 1) {
            jqGridColModel.setHidden(true);
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "34%%");
      tableModel.set("tableWidth", "54%%");
      return tableModel;
   }

   private void whywhyIdentifiedDrillDownReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
      String action = UIUtils.getActionPart(request);
      if (action.equals("whywhyRptGenDrill_view.why")) {
         UIUtils.forwardRequest(request, response, "/pages/Reports/whywhygendrilldown.jsp");
      } else if (action.equals("whywhyRptGenDrill_getCol.why")) {
         this.buildTableColModel(request, response);
      } else if (action.equals("whywhyRptGenDrill_getData.why")) {
         this.whywhyGetData(request, response);
      } else if (action.equals("whywhyRptGenDrill_chart.why")) {
         this.processChart(request, response);
      } else if (action.equals("whywhyRptGenDrill_getExcel.why")) {
         this.exportWhyWhyGenRptExcel(request, response);
      }

   }

   private void buildTableColModel(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      PrintWriter out = response.getWriter();
      String firstClick = request.getParameter("firstClick");
      CommonFilter commonFilter = null;
      if (!UIUtils.isValidKeyId(firstClick) || firstClick != null && !firstClick.equals("Y")) {
         commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      }

      if (commonFilter == null) {
         new CommonFilter();
      }

      commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", true);
      httpSession.removeAttribute("whywhygendrillfilter");
      httpSession.setAttribute("whywhygendrillfilter", commonFilter);
      CommonMessage.debugMsg(" Constants.passNullDate 01-Jan-1801 commonFilter.getFromMonth() " + commonFilter.getFromMonth());
      if ("01-Jan-1801".contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
         commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
         commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
         commonFilter.setMonwise("Y");
      }

      List<String[]> yyData = this.yyService.getWhyWhyGenDrillData(commonFilter);
      JSONObject jsonObject = this.getTableModel(yyData, FilterValues.getHeader(commonFilter.getDrillCaption()));
      jsonObject.set("tableHeight", "80%%");
      jsonObject.set("tableWidth", "104%%");
      httpSession.removeAttribute("ImprovementColData");
      httpSession.setAttribute("ImprovementColData", jsonObject);
      out.println(jsonObject);
   }

   private void whywhyGetData(HttpServletRequest request, HttpServletResponse response) throws Exception {
      CommonFilter commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", false);
      List<String[]> impVscomList = this.yyService.getWhyWhyGenDrillData(commonFilter);
      JSONObject listToJsonObject = new JSONObject();
      if (impVscomList != null && impVscomList.size() > 1) {
         listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0, commonFilter.getTotalRecordCnt());
      }

      PrintWriter out = response.getWriter();
      out.println(listToJsonObject);
   }

   private JSONObject getTableModel(List<String[]> headers, String caption) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader = (String[])headers.get(0);
      int header = colHeader.length - 1;
      String[] headerArr = new String[header];
      colHeader[2] = caption;
      jqGridTableModel.setTableButton(true);
      JqGridColModel jqGridColModel = this.getColModel("keyid1", 50, "left");
      jqGridColModel.setHidden(true);
      jqGridTableModel.getColModel().add(jqGridColModel);
      jqGridTableModel.setRowNumbers(true);
      jqGridColModel = this.getColModel("keyid2", 100, "left");
      jqGridTableModel.getColModel().add(jqGridColModel);
      headerArr[0] = "keyid1";
      headerArr[1] = "keyid2";
      headerArr[2] = "";
      jqGridColModel = this.getColModel("FLLOC", 300, "left");
      jqGridColModel.setHidden(false);
      jqGridTableModel.getColModel().add(jqGridColModel);

      for(int i = 3; i < header; ++i) {
         headerArr[i] = colHeader[i].replaceAll(" ", "");
         jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(headerArr[i] + i);
         jqGridColModel.setName(headerArr[i] + i);
         jqGridColModel.setWidth(100);
         jqGridColModel.setAlign("right");
         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      jqGridTableModel.getRowHeaders().add(headerArr);
      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "72%");
      return tableModel;
   }

   private void exportWhyWhyGenRptExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
      CommonFilter commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", false);
      JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
      tblJSONObj.put("title", "Why Why Report");
      String format = ExcelUtils.getFormat(request);
      Workbook wb = this.yyService.getWhyWhyGenDrillDataExcel(commonFilter, tblJSONObj, format);
      ExcelUtils.writeToResponse(response, wb, "WhyWhyGenReport", format);
   }

   private JqGridColModel getColModel(String colIndex, int width, String allign) {
      JqGridColModel jqGridColModel = new JqGridColModel();
      jqGridColModel.setIndex(colIndex);
      jqGridColModel.setName(colIndex);
      jqGridColModel.setHidden(true);
      jqGridColModel.setWidth(width);
      jqGridColModel.setAlign(allign);
      jqGridColModel.setEditable(false);
      return jqGridColModel;
   }

   private void processChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      String forDashboard = request.getParameter("dashboard");
      if (!"true".equals(forDashboard)) {
         commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      } else {
         commonFilter = new CommonFilter();
         FilterValues.getCommonFilters(request, commonFilter);
         FilterValues.getBDRelated(request, commonFilter);
      }

      CommonFilter chartCommonFilter = new CommonFilter();
      BeanUtils.copyProperties(chartCommonFilter, commonFilter);
      chartCommonFilter.setRowTotal('Y');
      List<String[]> whywhyList = this.yyService.getWhyWhyGenDrillData(chartCommonFilter);
      JSONObject chartObj = null;
      if (whywhyList != null && whywhyList.size() > 0) {
         String flids = CommonFunctions.getLoginFlid(request);
         String lcnname = this.dashboardService.Functionallocn(flids);
         chartObj = this.processLineChart(lcnname, whywhyList, chartCommonFilter);
         PrintWriter out = response.getWriter();
         UIUtils.dashBoardSetChartObject(request, chartObj);
         out.print(chartObj);
         out.close();
      }

   }

   private JSONObject processLineChart(String titlename, List<String[]> whywhyList, CommonFilter commonFilter) {
      if (whywhyList != null && whywhyList.size() > 1) {
         ChartOptionBean lineChart = new ChartOptionBean();
         List<String> xAxisCategory = new ArrayList();
         List<ChartSeries> chartSeriesList = new ArrayList();
         List<ChartYAxis> chartYAxis = new ArrayList();
         String[] month = (String[])whywhyList.get(0);
         String[] data = (String[])whywhyList.get(whywhyList.size() - 1);
         String prevMonth = null;
         if (commonFilter.getRowTotal() == null) {
            commonFilter.setRowTotal('N');
         }

         String subTitle = data[2];
         StringBuilder date = new StringBuilder();
         if (commonFilter.getMonwise().equals("Y")) {
            date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
         } else {
            date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
         }

         String drillLevel = FilterValues.getDrillHeader(data[1]);
         StringBuilder title = (new StringBuilder(titlename + "-WHY WHY Cumulative -")).append(drillLevel).append(" Level From ").append(date);
         ChartSeries timeSeries = new ChartSeries();
         List<Double> cumulativeData = new ArrayList();

         for(int i = 3; i < month.length - 1; ++i) {
            cumulativeData.add(Double.parseDouble(data[i]));
            if (prevMonth == null || !month[i].equals(prevMonth)) {
               xAxisCategory.add(month[i]);
            }

            prevMonth = month[i];
         }

         if (cumulativeData.size() > 0) {
            timeSeries.setData(cumulativeData);
            timeSeries.setType("spline");
            timeSeries.setName("Why Why Cumulative");
            chartSeriesList.add(timeSeries);
            ChartYAxis yAxis = new ChartYAxis();
            yAxis.setMin(0);
            yAxis.getTitle().setText("Numbers");
            chartYAxis.add(yAxis);
         }

         ChartXAxis xaxis = new ChartXAxis();
         if (commonFilter.getMonwise().equals("Y")) {
            xaxis.getTitle().setText("Month");
         } else {
            xaxis.getTitle().setText("Date");
         }

         lineChart.getxAxis().setTitle(xaxis.getTitle());
         return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
      } else {
         return null;
      }
   }

   private JSONObject getyyDonebyTableModel(List<String[]> yyDonebyList) {
      JqGridTableModel jqGridTableModel = new JqGridTableModel();
      String[] colHeader = (String[])yyDonebyList.get(0);
      jqGridTableModel.getRowHeaders().add(colHeader);
      jqGridTableModel.setRowNumbers(false);
      jqGridTableModel.setTableHeight(290);
      jqGridTableModel.setTableWidth(600);
      jqGridTableModel.setTableButton(false);
      jqGridTableModel.setRowNumbers(true);

      for(int i = 0; i < colHeader.length; ++i) {
         JqGridColModel jqGridColModel = new JqGridColModel();
         jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
         jqGridColModel.setWidth(200);
         jqGridColModel.setAlign("left");
         jqGridColModel.setEditable(false);
         if (i <= 2) {
            jqGridColModel.setHidden(true);
            jqGridColModel.setWidth(100);
            jqGridColModel.setAlign("left");
         }

         if (i == colHeader.length - 1) {
            jqGridColModel.setFormatter("BtnFormatterDelete2");
            jqGridColModel.setWidth(40);
            jqGridColModel.setAlign("center");
         }

         jqGridTableModel.getColModel().add(jqGridColModel);
      }

      JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
      tableModel.set("tableHeight", "30%%");
      tableModel.set("tableWidth", "26%%");
      return tableModel;
   }

   private void buildTableCountColModel(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      PrintWriter out = response.getWriter();
      String firstClick = request.getParameter("firstClick");
      CommonFilter commonFilter = null;
      if (!UIUtils.isValidKeyId(firstClick) || firstClick != null && !firstClick.equals("Y")) {
         commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      }

      if (commonFilter == null) {
         new CommonFilter();
      }

      commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", true);
      httpSession.removeAttribute("whywhygendrillfilter");
      httpSession.setAttribute("whywhygendrillfilter", commonFilter);
      CommonMessage.debugMsg(" Constants.passNullDate 01-Jan-1801 commonFilter.getFromMonth() " + commonFilter.getFromMonth());
      if ("01-Jan-1801".contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
         commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
         commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
         commonFilter.setMonwise("Y");
      }

      List<String[]> yyData = this.yyService.getWhyWhyCountData(commonFilter);
      JSONObject jsonObject = this.getTableModel(yyData, FilterValues.getHeader(commonFilter.getDrillCaption()));
      jsonObject.set("tableHeight", "80%%");
      jsonObject.set("tableWidth", "104%%");
      httpSession.removeAttribute("ImprovementColData");
      httpSession.setAttribute("ImprovementColData", jsonObject);
      out.println(jsonObject);
   }

   private void whywhycountGetData(HttpServletRequest request, HttpServletResponse response) throws Exception {
      CommonFilter commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", false);
      List<String[]> impVscomList = this.yyService.getWhyWhyCountData(commonFilter);
      JSONObject listToJsonObject = new JSONObject();
      if (impVscomList != null && impVscomList.size() > 1) {
         listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1, 0, commonFilter.getTotalRecordCnt());
      }

      PrintWriter out = response.getWriter();
      out.println(listToJsonObject);
   }

   private void exportWhyWhyCountExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
      CommonFilter commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", false);
      JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
      tblJSONObj.put("title", "Why Why Count");
      String format = ExcelUtils.getFormat(request);
      Workbook wb = this.yyService.getWhyWhyCountExcel(commonFilter, tblJSONObj, format);
      ExcelUtils.writeToResponse(response, wb, "WhyWhyCount", format);
   }

   private void processbarChart(HttpServletRequest request, HttpServletResponse response) throws Exception {
      HttpSession httpSession = request.getSession(false);
      CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      String forDashboard = request.getParameter("dashboard");
      if (!"true".equals(forDashboard)) {
         commonFilter = (CommonFilter)httpSession.getAttribute("whywhygendrillfilter");
      } else {
         commonFilter = this.populateCommonFilter(request, "whywhygendrillfilter", true);
      }

      CommonFilter chartCommonFilter = new CommonFilter();
      BeanUtils.copyProperties(chartCommonFilter, commonFilter);
      if (chartCommonFilter.getRowTotal() == null) {
         chartCommonFilter.setRowTotal('Y');
      }

      List<String[]> whywhyList = this.yyService.getWhyWhyCountData(chartCommonFilter);
      JSONObject chartObj = null;
      if (whywhyList != null && whywhyList.size() > 0) {
         String flids = CommonFunctions.getLoginFlid(request);
         String lcnname = this.dashboardService.Functionallocn(flids);
         chartObj = this.processBarChart2(lcnname, whywhyList, chartCommonFilter);
         PrintWriter out = response.getWriter();
         UIUtils.dashBoardSetChartObject(request, chartObj);
         out.print(chartObj);
         out.close();
      }

   }

   private JSONObject processBarChart2(String titlename, List<String[]> whywhyList, CommonFilter commonFilter) {
      if (whywhyList != null && whywhyList.size() > 1) {
         ChartOptionBean lineChart = new ChartOptionBean();
         List<String> xAxisCategory = new ArrayList();
         List<ChartSeries> chartSeriesList = new ArrayList();
         List<ChartYAxis> chartYAxis = new ArrayList();
         String[] month = (String[])whywhyList.get(0);
         String[] data = (String[])whywhyList.get(whywhyList.size() - 2);
         String prevMonth = null;
         String subTitle = "";
         StringBuilder date = new StringBuilder();
         if (commonFilter.getMonwise().equals("Y")) {
            date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
         } else {
            date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
         }

         String drillLevel = FilterValues.getDrillHeader(data[1]);
         StringBuilder title = (new StringBuilder(titlename + "-Why Why Count - ")).append(drillLevel).append(" Wide From ").append(date);
         ChartSeries timeSeries = new ChartSeries();
         List<Double> countData = new ArrayList();

         for(int i = 3; i < month.length - 1; ++i) {
            countData.add(Double.parseDouble(data[i]));
            if (prevMonth == null || !month[i].equals(prevMonth)) {
               xAxisCategory.add(month[i]);
            }

            prevMonth = month[i];
         }

         if (countData.size() > 0) {
            timeSeries.setData(countData);
            timeSeries.setType("column");
            timeSeries.setName("Why Why Count");
            chartSeriesList.add(timeSeries);
            ChartYAxis yAxis = new ChartYAxis();
            yAxis.setMin(0);
            yAxis.getTitle().setText("Numbers");
            chartYAxis.add(yAxis);
         }

         ChartXAxis xaxis = new ChartXAxis();
         if (commonFilter.getMonwise().equals("Y")) {
            xaxis.getTitle().setText("Month");
         } else {
            xaxis.getTitle().setText("Date");
         }

         lineChart.getxAxis().setTitle(xaxis.getTitle());
         return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
      } else {
         return null;
      }
   }
}
