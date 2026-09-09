
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.ChartPie;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;

import com.akranta.tpm.service.SmryAbnService;

import com.akranta.tpm.service.impl.SmryAbnServiceImpl;
//import com.akranta.tpm.service.impl.SparesQueryServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class SmryAbnRptServlet extends HttpServlet {
	/**
	 * Created By:Siddharth.A Modified By :N.Arun
	 */
	private static final long serialVersionUID = 1L;

	SmryAbnService smryAbnService;

	public SmryAbnRptServlet() {
		/*
		 * try {
		 * 
		 * 
		 * smryAbnService = new SmryAbnServiceImpl(); } catch (Exception e) {
		 * 
		 * e.printStackTrace(); }
		 */
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if (!UIUtils.checkUserSession(request, response))
			return;
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		try {
			smryAbnService = (SmryAbnServiceImpl) UIUtils.getServiceObject(request, "SmryAbnServiceImpl");
			smryAbnService.SmryAbnServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
					: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
		}

		if (action.equals("filterXmlAbnSummary_input.abnSmrRpt")
				|| action.equals("filterXmlHSE_AbnSummary_input.abnSmrRpt")
				|| (action.equals("filterXmlAbnSocSummary_input.abnSmrRpt"))
				|| (action.equals("filterXmlAbnHTASummary_input.abnSmrRpt"))
				|| (action.equals("filterXmlAbnUNSSummary_input.abnSmrRpt"))
				|| action.equals("filterAbnSmryEmpwise_input.abnSmrRpt")) {
			response.setContentType("xml");
			CommonMessage.debugMsg("action filterXmlAbnSummary_input.abnSmrRpt" + action);
			UIUtils.forwardRequest(request, response, "/tiles/xml/SmryAbnormaityReport.xml");
		} else if (action.equals("AbnSummary_input.abnSmrRpt") || action.equals("HSE_AbnSummary_input.abnSmrRpt")
				|| (action.equals("AbnSocSummary_input.abnSmrRpt")) || (action.equals("AbnHTASummary_input.abnSmrRpt"))
				|| (action.equals("AbnUNSSummary_input.abnSmrRpt")) || (action.equals("TeamSummary_input.abnSmrRpt"))) {
			String filter = request.getParameter("filter");

			String mchId = request.getParameter("mchId");
			String parentId = request.getParameter("backParentId");
			httpSession.removeAttribute("parentId");
			httpSession.setAttribute("parentId", parentId);

			String filterString = request.getParameter("filterString");
			request.setAttribute("filterStr", filterString);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/summaryofabn.jsp");
			request.setAttribute("filter", filter);

			if (action.equals("HSE_AbnSummary_input.abnSmrRpt"))
				request.setAttribute("AbnType", "SHE");
			else if (action.equals("AbnHTASummary_input.abnSmrRpt"))
				request.setAttribute("AbnType", "HTA");
			else if (action.equals("AbnSocSummary_input.abnSmrRpt"))
				request.setAttribute("AbnType", "SOC");
			else if (action.equals("AbnUNSSummary_input.abnSmrRpt"))
				request.setAttribute("AbnType", "UNS");

			if (mchId != null && mchId != "")
				request.setAttribute("mchId", mchId);
			rd.forward(request, response);
		} else if (action.equals("filterXmlAbnSmryEmpwise_input.abnSmrRpt")) {
			CommonMessage.debugMsg("filter_s1");
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/AbnSmryEmpwise.xml");
		} else if (action.equals("AbnSmryEmpwise_input.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseKaizen_input.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseOpl_input.abnSmrRpt")
				|| action.equals("AbnSmryEmpAbnKznOpl_input.abnSmrRpt")) {
			String EmpwiseType = "";
			if (action.equals("AbnSmryEmpwise_input.abnSmrRpt")) {
				EmpwiseType = "ABN";
			} else if (action.equals("AbnSmryEmpwiseKaizen_input.abnSmrRpt")) {
				EmpwiseType = "KZN";
			} else if (action.equals("AbnSmryEmpwiseOpl_input.abnSmrRpt")) {
				EmpwiseType = "OPL";
			} else if (action.equals("AbnSmryEmpAbnKznOpl_input.abnSmrRpt")) {
				EmpwiseType = "ALL";
			}
			CommonMessage.debugMsg("EmpwiseType------------>" + EmpwiseType);
			httpSession = request.getSession(false);
			httpSession.removeAttribute("EmpwiseType");
			httpSession.setAttribute("EmpwiseType", EmpwiseType);
			CommonMessage.debugMsg("EmpwiseType------------>" + httpSession.getAttribute("EmpwiseType"));

			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnSmryEmpwise.jsp");
			rd.forward(request, response);
		}

		else if (action.equals("KznSmryMngrwise_input.abnSmrRpt")) {
			String EmpwiseType = "";

			if (action.equals("KznSmryMngrwise_input.abnSmrRpt")) {
				EmpwiseType = "KZN";
			}

			CommonMessage.debugMsg("EmpwiseType------------>" + EmpwiseType);
			CommonMessage.debugMsg("EmpwiseType------------>" + EmpwiseType);
			httpSession = request.getSession(false);
			httpSession.removeAttribute("EmpwiseType");
			httpSession.setAttribute("EmpwiseType", EmpwiseType);
			CommonMessage.debugMsg("EmpwiseType------------>" + httpSession.getAttribute("EmpwiseType"));

			RequestDispatcher rd = request.getRequestDispatcher("/pages/AbnSmryMngrwise.jsp");
			rd.forward(request, response);
		} else if (action.equals("KznSmryMngrwise_getCol.abnSmrRpt")) {
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = null;
			String EmpwiseType = "";
			if (commonFilter == null) {
				commonFilter = new CommonFilter();
			}

			// FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", true);
			// FilterValues.getCommonFilters(request,commonFilter);
			if ((commonFilter.getRemoveBlank()) == null)
				commonFilter.setRemoveBlank("Y");
			httpSession.removeAttribute("AbnormalitySmryReportCommonFilter");
			httpSession.setAttribute("AbnormalitySmryReportCommonFilter", commonFilter);

			if (action.equals("KznSmryMngrwise_getCol.abnSmrRpt")) {
				commonFilter.setEmpwiseType("KZN");
			}

			EmpwiseType = commonFilter.getEmpwiseType();
			CommonMessage.debugMsg("  emptype 1" + commonFilter.getEmpwiseType());
			commonFilter.setIsGetCol("Y");
			List<String[]> abndrillList = smryAbnService.getAllManager(commonFilter);
			// JSONObject abndrillData = null;
			JSONObject jsonObject = null;

			// abndrillData =
			// UIUtils.convertToJqGridTableObject(abndrillList,request,4,0,commonFilter.getTotalRecordCnt());

			// httpSession.setAttribute("listToJsonObjec", abndrillData);

			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(2);

			String[] colHeaderCond = abndrillList.get(0);
			String[] colHeader = abndrillList.get(1);
			String[] colHeader1 = abndrillList.get(2);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeader1);

			/*
			 * if (action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt") ||
			 * action.equals("AbnSmryEmpwiseOpl_getCol.abnSmrRpt")) {
			 * jqGridTableModel.setGroupBy(true);
			 * jqGridTableModel.setGroupByField("FUNCTONALLOCATION" ); }
			 */

			jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "105%%");
			jsonObject.set("tableHeight", "84%%");
			// jsonObject.put("data", abndrillData);
			CommonMessage.debugMsg(jsonObject);
			// if(action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt"))
			// jsonObject.set("multiSelect",true);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel", jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("KznSmryMngrwise_getData.abnSmrRpt")) {
			PrintWriter out = response.getWriter();
			// String page = request.getParameter("page");
			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", false);

			if (action.equals("KznSmryMngrwise_getData.abnSmrRpt")) {
				commonFilter.setEmpwiseType("KZN");
			}

			JSONObject jsonObject = new JSONObject();
			/*
			 * if(page.equals("1")) jsonObject = (JSONObject)
			 * httpSession.getAttribute("listToJsonObjec"); else
			 */
			commonFilter.setAllotedDtTo(user.getUsrm_ccno());
			{
				commonFilter.setIsGetCol("N");
				List<String[]> abndrillList = smryAbnService.getAllManager(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(abndrillList, request, 3, 0,
						commonFilter.getTotalRecordCnt());
			}

			httpSession.removeAttribute("AbnormalitySmryReportCommonFilter");
			httpSession.setAttribute("AbnormalitySmryReportCommonFilter", commonFilter);
			out.println(jsonObject);
		}

		else if (action.equals("KznSmryMngrwise_getExcel.abnSmrRpt")) {
			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalityStratificationCommonFilter", false);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnColModel");

			// JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
			if (action.equals("KznSmryMngrwise_getExcel.abnSmrRpt")) {
				tblJSONObj.put("title", "Employee wise Kaizen Report");
				commonFilter.setEmpwiseType("KZN");
			}

			String tmpFromRow = commonFilter.getFromRow();//
			String format = ExcelUtils.getFormat(request);

			JSONArray header2 = (JSONArray) tblJSONObj.getJSONArray("rowHeaders").get(1);
			header2.put(1, "");
			header2.put(2, "");
			header2.put(3, "");
			tblJSONObj.getJSONArray("rowHeaders").put(1, header2);
			// CommonMessage.debugMsg(" tblJSONObj " + tblJSONObj);
			commonFilter.setFromRow(null);//
			Workbook wb = smryAbnService.ManagerExportExcel(commonFilter, tblJSONObj, format);

			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() + "SmryEmpWiseReport", format);
		}

		else if (action.equals("AbnSmryEmpwise_getCol.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseOpl_getCol.abnSmrRpt")
				|| action.equals("AbnSmryEmpAbnKznOpl_getCol.abnSmrRpt")) {
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg("``  in side the ABNSmrywiseKaizen");
			CommonFilter commonFilter = null;
			String EmpwiseType = "";
			if (commonFilter == null) {
				commonFilter = new CommonFilter();
			}

			// FilterValues.getAbnRelatedFilters(request, commonFilter);
			commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", true);
			// FilterValues.getCommonFilters(request,commonFilter);
			if ((commonFilter.getRemoveBlank()) == null)
				commonFilter.setRemoveBlank("Y");
			httpSession.removeAttribute("AbnormalitySmryReportCommonFilter");
			httpSession.setAttribute("AbnormalitySmryReportCommonFilter", commonFilter);

			if (action.equals("AbnSmryEmpwise_getCol.abnSmrRpt")) {
				CommonMessage.debugMsg("In side the  AbnSmryEmpwise_getCol.abnSmrRpt");
				commonFilter.setEmpwiseType("ABN");
			} else if (action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt")) {
				commonFilter.setEmpwiseType("KZN");
			} else if (action.equals("AbnSmryEmpwiseOpl_getCol.abnSmrRpt")) {
				commonFilter.setEmpwiseType("OPL");
			} else if (action.equals("AbnSmryEmpAbnKznOpl_getCol.abnSmrRpt")) {
				commonFilter.setEmpwiseType("ALL");
			}
			EmpwiseType = commonFilter.getEmpwiseType();
			CommonMessage.debugMsg("  emptype 1" + commonFilter.getEmpwiseType());
			commonFilter.setIsGetCol("Y");
			List<String[]> abndrillList = smryAbnService.getAllAbnEmp(commonFilter);
			// JSONObject abndrillData = null;
			JSONObject jsonObject = null;

			// abndrillData =
			// UIUtils.convertToJqGridTableObject(abndrillList,request,4,0,commonFilter.getTotalRecordCnt());

			// httpSession.setAttribute("listToJsonObjec", abndrillData);

			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();

			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);

			gridColModel.setHeaderNum(2);

			String[] colHeaderCond = abndrillList.get(0);
			String[] colHeader = abndrillList.get(1);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);

			if (action.equals("AbnSmryEmpwise_getCol.abnSmrRpt")) {
				CommonMessage.debugMsg("In side the  AbnSmryEmpwise_getCol.abnSmrRpt");
				String[] colHeader1 = abndrillList.get(2);
				headers.add(colHeader1);
				gridColModel.setHeaderNum(2);
			} else {
				gridColModel.setHeaderNum(1);
			}

			/*
			 * if (action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt") ||
			 * action.equals("AbnSmryEmpwiseOpl_getCol.abnSmrRpt")) {
			 * jqGridTableModel.setGroupBy(true);
			 * jqGridTableModel.setGroupByField("FUNCTONALLOCATION" ); }
			 */

			jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "105%%");
			jsonObject.set("tableHeight", "84%%");
			// jsonObject.put("data", abndrillData);
			CommonMessage.debugMsg(jsonObject);
			// if(action.equals("AbnSmryEmpwiseKaizen_getCol.abnSmrRpt"))
			// jsonObject.set("multiSelect",true);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel", jsonObject);
			out.println(jsonObject);
		}

		else if (action.equals("AbnSmryEmpwise_getData.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseKaizen_getData.abnSmrRpt")
				|| action.equals("AbnSmryEmpwiseOpl_getData.abnSmrRpt")
				|| action.equals("AbnSmryEmpAbnKznOpl_getData.abnSmrRpt")) {
			PrintWriter out = response.getWriter();
			int rowStart = 2;
			// String page = request.getParameter("page");
			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", false);
			if (action.equals("AbnSmryEmpwise_getData.abnSmrRpt")) {
				commonFilter.setEmpwiseType("ABN");
				rowStart = 3;
			}
			if (action.equals("AbnSmryEmpwiseKaizen_getData.abnSmrRpt")) {
				commonFilter.setEmpwiseType("KZN");
			}
			if (action.equals("AbnSmryEmpwiseOpl_getData.abnSmrRpt")) {
				commonFilter.setEmpwiseType("OPL");
			}
			if (action.equals("AbnSmryEmpAbnKznOpl_getData.abnSmrRpt")) {
				commonFilter.setEmpwiseType("OPL");
			}
			JSONObject jsonObject = new JSONObject();
			/*
			 * if(page.equals("1")) jsonObject = (JSONObject)
			 * httpSession.getAttribute("listToJsonObjec"); else
			 */
			commonFilter.setAllotedDtTo(user.getUsrm_ccno());
			  commonFilter.setIsGetCol("N");
			{
				
				List<String[]> abndrillList = smryAbnService.getAllAbnEmp(commonFilter);
				jsonObject = UIUtils.convertToJqGridTableObject(abndrillList, request, rowStart, 0,
						commonFilter.getTotalRecordCnt());
			}

			httpSession.removeAttribute("AbnormalitySmryReportCommonFilter");
			httpSession.setAttribute("AbnormalitySmryReportCommonFilter", commonFilter);
			out.println(jsonObject);
		}

		/*
		 * else if(action.equals("AbnSmryEmpwise_getExcel.abnSmrRpt") ||
		 * action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt") ||
		 * action.equals("AbnSmryEmpAbnKznOpl_getExcel.abnSmrRpt") ){ CommonFilter
		 * commonFilter =
		 * populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		 * JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
		 * 
		 * //JSONObject tblJSONObj = UIUtils.getXlColModel(request, response); if
		 * (action.equals("AbnSmryEmpwise_getExcel.abnSmrRpt")) {
		 * tblJSONObj.put("title", "Employee wise Abnormality Report");
		 * commonFilter.setEmpwiseType("ABN"); } if
		 * (action.equals("AbnSmryEmpwiseKaizen_getExcel.abnSmrRpt")) {
		 * tblJSONObj.put("title", "Employee wise Kaizen Report");
		 * commonFilter.setEmpwiseType("KZN"); } if
		 * (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
		 * tblJSONObj.put("title", "Employee wise Opl Report");
		 * commonFilter.setEmpwiseType("OPL"); } if
		 * (action.equals("AbnSmryEmpAbnKznOpl_getExcel.abnSmrRpt")) {
		 * tblJSONObj.put("title", "Employee wise Abnormality, Kaizen, Opl Report");
		 * commonFilter.setEmpwiseType("ALL"); }
		 * 
		 * String tmpFromRow = commonFilter.getFromRow();// String format =
		 * ExcelUtils.getFormat(request);
		 * 
		 * JSONArray header2 = (JSONArray)tblJSONObj.getJSONArray("rowHeaders").get(1);
		 * header2.put(1,""); header2.put(2,""); header2.put(3,"");
		 * tblJSONObj.getJSONArray("rowHeaders").put(1,header2); //
		 * CommonMessage.debugMsg(" tblJSONObj " + tblJSONObj);
		 * commonFilter.setFromRow(null);// Workbook wb =
		 * smryAbnService.AbnsmryEmpReportExportExcel(commonFilter,tblJSONObj,format);
		 * 
		 * commonFilter.setFromRow(tmpFromRow);
		 * 
		 * ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() +
		 * "SmryEmpWiseReport", format); }
		 */

		// ----------- Vignesh -------------------------------// 11Nov2025 excel filter
		// ------------------//

		/*
		 * else if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
		 * 
		 * // Build once for ABN/ALL (old flow) CommonFilter commonFilter =
		 * populateCommonFilter(request, "AbnormalityStratificationCommonFilter",
		 * false); JSONObject tblJSONObj = (JSONObject)
		 * httpSession.getAttribute("AbnColModel");
		 * 
		 * if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) { // 🔧 IMPORTANT:
		 * use a FRESH filter for OPL so we don't merge old session values CommonFilter
		 * oplFilter = populateCommonFilter(request,
		 * "AbnormalityStratificationCommonFilter", true);
		 * 
		 * // OPL specifics oplFilter.setEmpwiseType("OPL"); // Excel must not paginate
		 * oplFilter.setFromRow(null); oplFilter.setToRow(null); //
		 * oplFilter.setIsTotalCnt("N"); oplFilter.setGridFilter(null);
		 * 
		 * // Title + header cleanup (as before) tblJSONObj.put("title",
		 * "Employee wise Opl Report"); JSONArray header2 = (JSONArray)
		 * tblJSONObj.getJSONArray("rowHeaders").get(1); header2.put(1, "");
		 * header2.put(2, ""); header2.put(3, "");
		 * tblJSONObj.getJSONArray("rowHeaders").put(1, header2);
		 * 
		 * // (Optional) debug so you can verify the exact cond string sent to PG //
		 * System.out.prntln("Excel(OPL) cond: " + getCondParams(oplFilter));
		 * 
		 * Workbook wb = smryAbnService.AbnsmryEmpReportExportExcel(oplFilter,
		 * tblJSONObj, ExcelUtils.getFormat(request));
		 * ExcelUtils.writeToResponse(response, wb, "OPLSmryEmpWiseReport",
		 * ExcelUtils.getFormat(request)); return; // ✅ stop here so ABN/ALL continue
		 * old flow }
		 * 
		 * // Existing header tweak JSONArray header2 = (JSONArray)
		 * tblJSONObj.getJSONArray("rowHeaders").get(1); header2.put(1, "");
		 * header2.put(2, ""); header2.put(3, "");
		 * tblJSONObj.getJSONArray("rowHeaders").put(1, header2);
		 * 
		 * String tmpFromRow = commonFilter.getFromRow(); String format =
		 * ExcelUtils.getFormat(request);
		 * 
		 * // No pagination for Excel commonFilter.setFromRow(null); Workbook wb =
		 * smryAbnService.AbnsmryEmpReportExportExcel(commonFilter, tblJSONObj, format);
		 * commonFilter.setFromRow(tmpFromRow);
		 * 
		 * ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() +
		 * "SmryEmpWiseReport", format); }
		 */

		// ----------- Vignesh --------removed URL AbnSmryEmpwiseOpl_getExcel--
		// 11Nov2025 for excel filter ------------------//

		
		  else if(action.equals("AbnSmryEmpwise_getExcel.abnSmrRpt") ||
		  action.equals("AbnSmryEmpAbnKznOpl_getExcel.abnSmrRpt") ){ CommonFilter
		  commonFilter = populateCommonFilter(request,"AbnormalityStratificationCommonFilter",false);
		  JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("AbnColModel");
		  
		  //JSONObject tblJSONObj = UIUtils.getXlColModel(request, response); 
		  if(action.equals("AbnSmryEmpwise_getExcel.abnSmrRpt")) { 
			  commonFilter = populateCommonFilter(request, "AbnormalityStratificationCommonFilter", true);
		     tblJSONObj.put("title", "Employee wise Abnormality Report");
		  commonFilter.setEmpwiseType("ABN"); 
		  } 
		  if(action.equals("AbnSmryEmpwiseKaizen_getExcel.abnSmrRpt")) {
		  tblJSONObj.put("title", "Employee wise Kaizen Report");
		  commonFilter.setEmpwiseType("KZN"); 
		  } 
		  if(action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
		  tblJSONObj.put("title", "Employee wise Opl Report");
		  commonFilter.setEmpwiseType("OPL");
		  } 
		  if(action.equals("AbnSmryEmpAbnKznOpl_getExcel.abnSmrRpt")) {
		  tblJSONObj.put("title", "Employee wise Abnormality, Kaizen, Opl Report");
		  commonFilter.setEmpwiseType("ALL"); }
		  
		  String tmpFromRow = commonFilter.getFromRow();
		  String format =ExcelUtils.getFormat(request);
		  
		  JSONArray header2 = (JSONArray)tblJSONObj.getJSONArray("rowHeaders").get(1);
//		  header2.put(1,""); header2.put(2,""); header2.put(3,"");
		  tblJSONObj.getJSONArray("rowHeaders").put(1,header2); 
		  CommonMessage.debugMsg(" tblJSONObj " + tblJSONObj);
		  commonFilter.setFromRow(null); 
		  Workbook wb = smryAbnService.AbnsmryEmpReportExportExcel(commonFilter,tblJSONObj,format);
		  
		  commonFilter.setFromRow(tmpFromRow);
		  
		  ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() +"SmryEmpWiseReport", format); 
		  }
		  
		  
		/*  else if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) { // Build
		  once for ABN/ALL (old flow) CommonFilter commonFilter =
		  populateCommonFilter(request, "AbnormalityStratificationCommonFilter",
		  false); JSONObject tblJSONObj = (JSONObject)
		  httpSession.getAttribute("AbnColModel");
		  
		  //vignesh if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
		  commonFilter = populateCommonFilter(request,
		  "AbnormalityStratificationCommonFilter", true); tblJSONObj.put("title",
		  "Employee wise Opl Report"); commonFilter.setEmpwiseType("OPL"); } String
		  tmpFromRow = commonFilter.getFromRow();// String format =
		  ExcelUtils.getFormat(request);
		  
		  JSONArray header2 = (JSONArray)tblJSONObj.getJSONArray("rowHeaders").get(0);
		  header2.put(1,""); header2.put(2,""); header2.put(3,"");
		  tblJSONObj.getJSONArray("rowHeaders").put(1,header2); //
		  CommonMessage.debugMsg(" tblJSONObj " + tblJSONObj);
		  commonFilter.setFromRow(null);// Workbook wb =
		  smryAbnService.AbnsmryEmpReportExportExcel(commonFilter,tblJSONObj,format);
		  
		  commonFilter.setFromRow(tmpFromRow);
		  
		  ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() +
		  "SmryEmpWiseReport", format);
		  
		  
		  }*/
		 

		else if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
			// Build once for ABN/ALL (old flow)
			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalityStratificationCommonFilter", false);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnColModel");

			// vignesh
			if (action.equals("AbnSmryEmpwiseOpl_getExcel.abnSmrRpt")) {
				commonFilter = populateCommonFilter(request, "AbnormalityStratificationCommonFilter", true);
				tblJSONObj.put("title", "Employee wise Opl Report");
				commonFilter.setEmpwiseType("OPL");
			}
			String tmpFromRow = commonFilter.getFromRow();//
			String format = ExcelUtils.getFormat(request);

//			JSONArray header2 = (JSONArray) tblJSONObj.getJSONArray("rowHeaders").get(0);
//		header2.put(1, "");
//			header2.put(2, "");
//			header2.put(3, "");
//			tblJSONObj.getJSONArray("rowHeaders").put(1, header2);
			// CommonMessage.debugMsg(" tblJSONObj " + tblJSONObj);
			commonFilter.setFromRow(null);//
			Workbook wb = smryAbnService.AbnsmryEmpReportExportExcel(commonFilter, tblJSONObj, format);

			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() + "SmryEmpWiseReport", format);

		}

		// --------------------- removed opl url
		// alone----------------------------------------------------- //
		else if (action.equals("AbnSmryEmpwiseKaizen_getExcel.abnSmrRpt")) {
			// PrintWriter out = response.getWriter();
			// String page = request.getParameter("page");
			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnColModel");
			// JSONObject jsonObject = new JSONObject();

			tblJSONObj.put("title", "Employee wise Kaizen Report");
			commonFilter.setEmpwiseType("KZN");

			String format = ExcelUtils.getFormat(request);
			Workbook wb = smryAbnService.AbnsmryEmpReportExportExcel(commonFilter, tblJSONObj, format);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, commonFilter.getEmpwiseType() + "SmryEmpWiseReport", format);

		}

		else if (action.equals("AbnSummary_getExcel.abnSmrRpt") || (action.equals("HSE_AbnSummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnSocSummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnHTASummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnUNSSummary_getExcel.abnSmrRpt"))) {

			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String checkField = "ABN";
			if (action.equals("HSE_AbnSummary_getExcel.abnSmrRpt"))
				checkField = "HSE";

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnColModel");
			tblJSONObj.put("title", "Summary of Abnormality Report");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = smryAbnService.smryAbnormalityReportExportExcel(commonFilter, tblJSONObj, format, checkField);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "SummaryOfAbnormalityReport", format);
		} else if (action.equals("AbnSummary_getCol.abnSmrRpt") || action.equals("HSE_AbnSummary_getCol.abnSmrRpt")
				|| (action.equals("AbnSocSummary_getCol.abnSmrRpt"))
				|| (action.equals("AbnHTASummary_getCol.abnSmrRpt"))
				|| (action.equals("AbnUNSSummary_getCol.abnSmrRpt"))
				|| (action.equals("TeamSummary_getCol.abnSmrRpt"))) {
			CommonMessage.debugMsg("getcol:");
			String teamId = "";

			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = null;
			String firstClick = request.getParameter("firstClick");

			String parentId = (String) httpSession.getAttribute("parentId");

			if (commonFilter == null)
				commonFilter = new CommonFilter();

			FilterValues.getAbnRelatedFilters(request, commonFilter);

			if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
				CommonMessage.debugMsg("test first click..........");
				commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalitySmryReportCommonFilter");

			}
			CommonMessage.debugMsg("test first click..........");
			String checkField = "ABN";
			if (action.equals("HSE_AbnSummary_getCol.abnSmrRpt"))
				checkField = "HSE";
			commonFilter.setAssemblyDrillExist(true);

			FilterValues.getCommonFilters(request, commonFilter);

			// if(UIUtils.isValidKeyId(commonFilter.getRemoveBlank()))

			/*
			 * if(commonFilter.getDrillFlag() == 'b'){ if(UIUtils.isValidKeyId(parentId) &&
			 * "LOC".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.LOCATION); else
			 * if(UIUtils.isValidKeyId(parentId) && "FCT".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.FACTORY); else
			 * if(UIUtils.isValidKeyId(parentId) && "SEC".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.SECTION); else
			 * if(UIUtils.isValidKeyId(parentId) && "CELL".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.CELL); else
			 * if(UIUtils.isValidKeyId(parentId) && "MCH".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.MACHINE); else
			 * if(UIUtils.isValidKeyId(parentId) && "ASM".equals(parentId.substring(0,3)))
			 * commonFilter.setDrillLevel(DrillLevelConstants.ASSEMBLY); }
			 */
			String prevDrillLevel = commonFilter.getDrillLevel();
			String skipLine = request.getParameter("SkipLine");
			String abnType = commonFilter.getAbnormalityType();

			CommonMessage.debugMsg("test first click..........");

			if (action.equals("TeamSummary_getCol.abnSmrRpt")) {
				CommonMessage.debugMsg("team id in before......." + teamId);
				teamId = request.getParameter("teamId");
				String levelNo = request.getParameter("levelNo");
				CommonMessage.debugMsg("team id in after......." + teamId);
				if (UIUtils.isValidKeyId(teamId))
					commonFilter.setTeamId(teamId);
				else
					commonFilter.setTeamId("");
				commonFilter.setTeamLevelNo(levelNo);
				commonFilter.setType("Y");
				abnType = "Team";
			}

			httpSession.removeAttribute("AbnormalitySmryReportCommonFilter");
			httpSession.setAttribute("AbnormalitySmryReportCommonFilter", commonFilter);
			response.setContentType("text/html");

			// ---------------------headers-------------
			List<String[]> abndrillList = smryAbnService.getAllAbnormalities(commonFilter, checkField);
			JSONObject abndrillData = null;
			JSONObject jsonObject = null;
			if (abndrillList != null && abndrillList.size() > 0) {
				abndrillData = UIUtils.convertToJqGridTableObject(abndrillList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				httpSession.setAttribute("listToJsonObjec", abndrillData);
			} else
				httpSession.setAttribute("listToJsonObjec", abndrillData);

			CommonMessage.debugMsg("abndrillData:" + abndrillData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			gridColModel.setHeaderNum(2);

			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableHeight(290);
			String[] colHeaderHead = abndrillList.get(1);
			/*
			 * String caption=FilterValues.getHeader(commonFilter.getDrillCaption());
			 * 
			 * if("Team".equals(abnType)) { String [] header = abndrillList.get(0);
			 * CommonMessage.debugMsg(header[2]); caption = header[2]; }
			 */
			/*
			 * CommonMessage.debugMsg("caption: abnType"+abnType); String[] ABNHeader1 =
			 * {"rowNum","keyid","Name","Identified","Removed","Identified","Removed",
			 * "Identified","Removed"} ; String[] ABNHeader2 =
			 * {"rowNum","keyid","Name","Abnormalities","Abnormalities","Red","Red","White",
			 * "White"}; String[] ABNHeader3
			 * ={"","","Double Click to Drill Down","","","","","",""}; String[] ABNHeader4
			 * = {"",""," ","","","","","",""};
			 * 
			 * String[] HSEHeader1 =
			 * {"rowNum","keyid","Name","Identified","Removed","Identified","Removed",
			 * "Identified","Removed","Identified","Removed"} ; String[] HSEHeader2 =
			 * {"rowNum","keyid","Name","Abnormalities","Abnormalities","Red","Red","White",
			 * "White","Green","Green"}; String[] HSEHeader3
			 * ={"","","Double Click to Drill Down","","","","","","","",""}; String[]
			 * HSEHeader4 = {"",""," ","","","","","","","",""};
			 * 
			 * String[] header1 = ABNHeader1; String[] header2 = ABNHeader2; String[]
			 * header3 = ABNHeader3; String[] header4 = ABNHeader4;
			 * 
			 * if("SHE".equals(abnType)) { header1 = HSEHeader1; header2 = HSEHeader2;
			 * header3 = HSEHeader3; header4 = HSEHeader4; }
			 */
			/*
			 * header2[2] = caption; header1[2] = caption;
			 */
			/*
			 * if(abndrillList.size() > 0 ) { String[] row = abndrillList.get(0); for(int
			 * i=3;i< row.length ;i++ ){
			 * jqGridColModelList.add(getColModel(row[i],120,"center", "SHE".equals(abnType)
			 * ? row[i].startsWith("BOTH") : false)); } } else{ String[] row =
			 * {"adnI","abnR","redI","redR","whiteI","whiteR"}; for(int i=0;i< row.length
			 * ;i++ ){ jqGridColModelList.add(getColModel(row[i],120,"center",false)); } }
			 */

			List<String[]> headers = new ArrayList<String[]>();
			/*
			 * if(caption != "Assembly"){ headers.add(header3); } else headers.add(header4);
			 */

			headers.add(abndrillList.get(2));
			headers.add(abndrillList.get(3));

			jsonObject = UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
			CommonMessage.debugMsg("jsonObject:" + jsonObject);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "72%%");
			jsonObject.put("data", abndrillData);
			httpSession.removeAttribute("AbnColModel");
			httpSession.setAttribute("AbnColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("AbnSummary_getData.abnSmrRpt") || action.equals("HSE_AbnSummary_getData.abnSmrRpt")
				|| (action.equals("AbnSocSummary_getData.abnSmrRpt"))
				|| (action.equals("AbnHTASummary_getData.abnSmrRpt"))
				|| (action.equals("AbnUNSSummary_getData.abnSmrRpt"))
				|| (action.equals("TeamSummary_getData.abnSmrRpt"))) {
			try {
				UIUtils.displayRequestParamsValue(request);
				PrintWriter out = response.getWriter();
				String page = request.getParameter("page");
				CommonFilter commonFilter = (CommonFilter) httpSession
						.getAttribute("AbnormalitySmryReportCommonFilter");
				String flid = request.getParameter("flid");
				String drillflag = request.getParameter("drillFlag");
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (UIUtils.isValidKeyId(drillflag)) {
					CommonMessage.debugMsg("  (drillflag.trim()).charAt(0)   " + (drillflag.trim()).charAt(0));
					commonFilter.setDrillFlag((drillflag.trim()).charAt(0));
				}
				JSONObject jsonObject = new JSONObject();

				String checkField = "ABN";
				if (action.equals("HSE_AbnSummary_getCol.abnSmrRpt"))
					checkField = "HSE";

				if (action.equals("TeamSummary_getData.abnSmrRpt"))
					commonFilter.setType("Y");

				/*
				 * if(page != null && page.equals("1")) jsonObject = (JSONObject)
				 * httpSession.getAttribute("listToJsonObjec"); else {
				 */
				List<String[]> abndrillList = smryAbnService.getAllAbnormalities(commonFilter, checkField);
				if (abndrillList != null && abndrillList.size() > 0)
					jsonObject = UIUtils.convertToJqGridTableObject(abndrillList, request, 4, 0,
							commonFilter.getTotalRecordCnt());
				// }

				out.println(jsonObject);
				commonFilter.setViewClick('N');
			} catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
			}
		}

		else if (action.equals("piechart.abnSmrRpt")) {
			try {
				String total = null;
				String forDashboard = request.getParameter("dashboard");
				String flag = request.getParameter("flag");
				JSONArray chartData = new JSONArray();
				CommonMessage.debugMsg(flag + " flag");
				CommonFilter commonFilter = new CommonFilter();
				CommonFilter piechrtCommonFilter = new CommonFilter();
				if (!"true".equals(forDashboard)) {
					commonFilter = (CommonFilter) httpSession.getAttribute("AbnormalitySmryReportCommonFilter");
					piechrtCommonFilter = (CommonFilter) httpSession.getAttribute("AbnormalitySmryReportCommonFilter");
				} else {
					FilterValues.getSafty(request, piechrtCommonFilter);
					FilterValues.getCommonFilters(request, piechrtCommonFilter);
				}
				BeanUtils.copyProperties(piechrtCommonFilter, commonFilter);
				// piechrtCommonFilter.setFlid(commonFilter.getFlid());
				// piechrtCommonFilter.setDrillFlag(commonFilter.getDrillFlag());
				CommonMessage.debugMsg(
						piechrtCommonFilter.getDrillFlag() + " piechrtCommonFilter " + piechrtCommonFilter.getFlid());
				String rownum = request.getParameter("rownum");

				CommonMessage.debugMsg(piechrtCommonFilter.getFlid() + " aftr settng");
				String fromMonth = piechrtCommonFilter.getFromMonth();
				String toMonth = piechrtCommonFilter.getToMonth();

				String month1 = fromMonth + "  -  " + toMonth;
				String fromdate = piechrtCommonFilter.getFromDate();
				String todate = piechrtCommonFilter.getToDate();
				String date = fromdate + "  -  " + todate;
				String title;
				CommonMessage.debugMsg(flag + "   flag   bfr if" + flag.equals("Y"));
				if (flag.equals("Y")) {
					title = "Rectified Report" + " - " + date;
				} else {
					title = "Identified Report" + " - " + date;
				}

				if (piechrtCommonFilter.getMonwise().equals("Y")) {
					if (flag.equals("Y")) {
						title = "Rectified Report" + " - " + month1;
					} else {
						title = "Identified Report" + " - " + month1;

					}

				}
				if (piechrtCommonFilter.getRowTotal() == null)
					piechrtCommonFilter.setRowTotal('N');
				List<String[]> graphData = smryAbnService.getpiechart(piechrtCommonFilter, flag);
				CommonMessage.debugMsg(graphData.size() + " list size");
				ChartPie pieChart = new ChartPie();

				List<ChartSeries> chartSeriesList = new ArrayList<ChartSeries>();

				PrintWriter out = response.getWriter();
				String[] header = graphData.get(1);
				String[] header2 = graphData.get(2);
				int rowid = 2;
				if (UIUtils.isValidKeyId(rownum)) {
					rowid = rowid + Integer.parseInt(rownum);
				} else
					rowid = graphData.size() - 1;

				String[] data = graphData.get(rowid);

				String subTitle = "";// header[3]

				List<Object[]> dataList = new ArrayList<Object[]>();

				for (int i = 4; i < data.length; i++) {
					CommonMessage.debugMsg(header[i] + "  header2) ");
					Object[] pieData = new Object[2];
					pieData[0] = header[i];
					pieData[1] = Double.parseDouble(data[i]);
					dataList.add(pieData);
				}
				ChartSeries chartSeries = new ChartSeries();
				chartSeries.setType(ChartTypes.PIE);
				chartSeries.setData(dataList);
				chartSeriesList.add(chartSeries);
				chartSeries.setSize(200);

				JSONObject chartObj = pieChart.drawChart(chartSeriesList, title, subTitle);
				chartData.put(chartObj);
				UIUtils.setDashBoardIdentifier(request, chartObj);
				dataList = new ArrayList<Object[]>();
				out.println(chartObj);
				out.close();
			} catch (Exception e) {
				CommonMessage.debugMsg("error " + e.getMessage());
				e.printStackTrace();
			}
		}

		else if (action.equals("AbnSummary_getExcel.abnSmrRpt") || (action.equals("HSE_AbnSummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnSocSummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnHTASummary_getExcel.abnSmrRpt"))
				|| (action.equals("AbnUNSSummary_getExcel.abnSmrRpt"))) {

			CommonFilter commonFilter = populateCommonFilter(request, "AbnormalitySmryReportCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			String checkField = "ABN";
			if (action.equals("HSE_AbnSummary_getExcel.abnSmrRpt"))
				checkField = "HSE";

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("AbnColModel");
			tblJSONObj.put("title", "Summary of Abnormality Report");
			String format = ExcelUtils.getFormat(request);

			Workbook wb = smryAbnService.smryAbnormalityReportExportExcel(commonFilter, tblJSONObj, format, checkField);
			commonFilter.setFromRow(tmpFromRow);

			ExcelUtils.writeToResponse(response, wb, "SummaryOfAbnormalityReport", format);
		}
	}

	private JSONObject getTabModel(List<String[]> headers, String EmpwiseType) {
		// String[] ASEHeader1 = {"","","Employee wise Abnormality, Kaizen,
		// Opl","","","","","","","",""} ;
		// String[] ASEHeader2 =
		// {"rowNum","keyid","Employee","Abnormalities","Abnormalities","Red","Red","White","White","JH
		// Kaisen","OPL"};
		// String[] ASEHeader3 =
		// {"rowNum","keyid","Employee","Identified","Removed","Identified","Removed","Identified","Removed","Identified","Identified"}
		// ;
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setRowNumbers(true);
		String[] colHeader = null;
		String[] colHeader2 = null;
		String[] colHeader3 = null;
		if (EmpwiseType.equals("ABN")) {
			String[] ABNHeader1 = { "", "", "Employee wise Abnormality", "", "", "", "", "", "" };
			String[] ABNHeader2 = { "rowNum", "keyid", "Employee", "Abnormalities", "Abnormalities", "Red", "Red",
					"White", "White" };
			String[] ABNHeader3 = { "rowNum", "keyid", "Employee", "Identified", "Removed", "Identified", "Removed",
					"Identified", "Removed" };
			colHeader = ABNHeader1;
			colHeader2 = ABNHeader2;
			colHeader3 = ABNHeader3;
		} else if (EmpwiseType.equals("KZN")) {
			String[] KZNHeader1 = { "", "", "Employee wise Kaizen", "" };
			String[] KZNHeader2 = { "rowNum", "keyid", "Employee", "Kaizen" };
			String[] KZNHeader3 = { "rowNum", "keyid", "Employee", "Identified" };
			colHeader = KZNHeader1;
			colHeader2 = KZNHeader2;
			colHeader3 = KZNHeader3;
		} else if (EmpwiseType.equals("OPL")) {
			String[] OPLHeader1 = { "", "", "Employee wise Opl", "" };
			String[] OPLHeader2 = { "rowNum", "keyid", "Employee", "OPL" };
			String[] OPLHeader3 = { "rowNum", "keyid", "Employee", "Identified" };
			colHeader = OPLHeader1;
			colHeader2 = OPLHeader2;
			colHeader3 = OPLHeader3;
		} else if (EmpwiseType.equals("ALL")) {
			String[] ALLHeader1 = { "", "", "Employee wise Abnormality, Kaizen, OPl", "", "", "", "", "", "", "", "" };
			String[] ALLHeader2 = { "rowNum", "keyid", "Employee", "Abnormalities", "Abnormalities", "Red", "Red",
					"White", "White", "Kaizen", "OPL" };
			String[] ALLHeader3 = { "rowNum", "keyid", "Employee", "Identified", "Removed", "Identified", "Removed",
					"Identified", "Removed", "Identified", "Identified" };
			colHeader = ALLHeader1;
			colHeader2 = ALLHeader2;
			colHeader3 = ALLHeader3;
		}

		String[] row = colHeader;
		String[] tempCol = new String[row.length];
		/*
		 * String [] colHeader = ASEHeader1; String [] colHeader2 = ASEHeader2; String
		 * [] colHeader3 = ASEHeader3;
		 */
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "") + "_" + i);
			jqGridColModel.setName(colHeader[i].replaceAll(" ", "") + "_" + i);
			tempCol[i] = "";

			if (i == 1 || i == 0)
				jqGridColModel.setHidden(true);
			else if (i == 0)
				jqGridColModel.setWidth(50);
			else if (i == 2)
				jqGridColModel.setWidth(300);
			else if (i > 2)
				jqGridColModel.setAlign("right");

			jqGridColModel.setEditable(false);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader3);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		return tableModel;
	}

	/*
	 * private JSONObject getTabModel(List<String[]> headers) { String[] ASEHeader1
	 * = {"","","Employee wise Abnormality, Kaizen, Opl","","","","","","","",""} ;
	 * String[] ASEHeader2 =
	 * {"rowNum","keyid","Employee","Abnormalities","Abnormalities","Red","Red",
	 * "White","White","JH Kaisen","OPL"}; String[] ASEHeader3 =
	 * {"rowNum","keyid","Employee","Identified","Removed","Identified","Removed",
	 * "Identified","Removed","Identified","Identified"} ;
	 * 
	 * JqGridTableModel jqGridTableModel = new JqGridTableModel();
	 * jqGridTableModel.setTableButton(true); jqGridTableModel.setRowNumbers(true);
	 * String[] row =ASEHeader1; String[] tempCol = new String[row.length]; String
	 * [] colHeader = ASEHeader1; String [] colHeader2 = ASEHeader2; String []
	 * colHeader3 = ASEHeader3; for(int i =0; i < colHeader.length; i++) {
	 * JqGridColModel jqGridColModel = new JqGridColModel();
	 * jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")+"_"+i);
	 * jqGridColModel.setName(colHeader[i].replaceAll(" ", "")+"_"+i); tempCol[i]
	 * ="";
	 * 
	 * if( i==1 || i==0) jqGridColModel.setHidden(true); else if(i==0)
	 * jqGridColModel.setWidth(50); else if(i==2) jqGridColModel.setWidth(300); else
	 * if(i>2) jqGridColModel.setAlign("right");
	 * 
	 * jqGridColModel.setEditable(false);
	 * jqGridTableModel.getColModel().add(jqGridColModel); }
	 * jqGridTableModel.getRowHeaders().add(ASEHeader1);
	 * jqGridTableModel.getRowHeaders().add(colHeader2);
	 * jqGridTableModel.getRowHeaders().add(colHeader3); JSONObject tableModel =
	 * UIUtils.getJqGridTableModel(jqGridTableModel); return tableModel; }
	 */
	private JqGridColModel getColModel(String colIndex, int width, String allign, boolean hidden) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setWidth(width);
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		jqGridColModel.setHidden(hidden);
		return jqGridColModel;
	}

	private JSONObject getTableModel(List<String[]> headers, String caption, String abnType) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		if ("Team".equals(abnType)) {
			String[] header = headers.get(0);
			CommonMessage.debugMsg(header[2]);
			caption = header[2];

		}
		String[] ABNHeader1 = { "rowNum", "keyid", "Company", "Identified", "Removed", "Identified", "Removed",
				"Identified", "Removed" };
		String[] ABNHeader2 = { "rowNum", "keyid", "Company", "Abnormalities", "Abnormalities", "Red", "Red", "White",
				"White" };
		String[] ABNHeader3 = { "", "", "Double Click on the " + caption + " to Drill Down", "", "", "", "", "", "" };
		String[] ABNHeader4 = { "", "", " ", "", "", "", "", "", "" };

		/*
		 * String[] HSEHeader1 =
		 * {"rowNum","keyid","Company","Identified","Removed","Identified","Removed"} ;
		 * String[] HSEHeader2 =
		 * {"rowNum","keyid","Company","Both","Both","Green","Green"}; String[]
		 * HSEHeader3
		 * ={"","","Double Click on the "+caption+" to Drill Down","","","",""};
		 * String[] HSEHeader4 = {"",""," ","","","",""};
		 */

		String[] HSEHeader1 = { "rowNum", "keyid", "Company", "Identified", "Removed", "Identified", "Removed",
				"Identified", "Removed", "Identified", "Removed" };
		String[] HSEHeader2 = { "rowNum", "keyid", "Company", "Abnormalities", "Abnormalities", "Red", "Red", "White",
				"White", "Green", "Green" };
		String[] HSEHeader3 = { "", "", "Double Click on the " + caption + " to Drill Down", "", "", "", "", "", "", "",
				"" };
		String[] HSEHeader4 = { "", "", " ", "", "", "", "", "", "", "", "" };

		String[] header1 = ABNHeader1;
		String[] header2 = ABNHeader2;
		String[] header3 = ABNHeader3;
		String[] header4 = ABNHeader4;

		if ("SHE".equals(abnType)) {
			header1 = HSEHeader1;
			header2 = HSEHeader2;
			header3 = HSEHeader3;
			header4 = HSEHeader4;
		}
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.setTableHeight(290);
		// String prevKeyid = null;
		List<JqGridColModel> jqGridColModelList = new ArrayList<JqGridColModel>();
		JqGridColModel jqGridColModel = getColModel("rowNum", 50, "left", true);

		jqGridColModelList.add(jqGridColModel);
		jqGridColModel = getColModel("keyid", 50, "left", true);
		jqGridColModelList.add(jqGridColModel);
		jqGridColModel = getColModel("Description", 350, "left", false);
		jqGridColModelList.add(jqGridColModel);
		header2[2] = caption;
		header1[2] = caption;

		if (headers.size() > 0) {
			String[] row = headers.get(0);
			for (int i = 3; i < row.length; i++) {
				jqGridColModelList.add(
						getColModel(row[i], 120, "center", "SHE".equals(abnType) ? row[i].startsWith("BOTH") : false));
			}
		} else {
			String[] row = { "adnI", "abnR", "redI", "redR", "whiteI", "whiteR" };
			for (int i = 0; i < row.length; i++) {
				jqGridColModelList.add(getColModel(row[i], 120, "center", false));
			}
		}
		jqGridTableModel.setColModel(jqGridColModelList);

		if (caption != "Assembly") {
			jqGridTableModel.getRowHeaders().add(header3);
		} else
			jqGridTableModel.getRowHeaders().add(header4);

		jqGridTableModel.getRowHeaders().add(header2);
		jqGridTableModel.getRowHeaders().add(header1);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);

		return tableModel;
	}

	private JSONObject fillABNJqGrid(List<String[]> dataArrayList, HttpServletRequest request, int rowStart) {
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 1000;
		if (rowsStr != null)
			rows = Integer.parseInt(rowsStr);

		int page = 1;
		if (pageStr != null)
			page = Integer.parseInt(pageStr);

		JSONObject tableDataObject = new JSONObject();

		tableDataObject.put("page", page); // current page
		tableDataObject.put("total",
				Math.ceil(dataArrayList.size() / rows) == 0 ? 1 : Math.ceil(dataArrayList.size() / rows)); // total page
		tableDataObject.put("records", (dataArrayList.size() - rowStart)); // total records

		JSONArray rowArr = new JSONArray();

		int rowId = 0;
		int i = 0;
		String prevKeyid = null;
		JSONObject rowObj = null;
		JSONArray cell = null;
		int cnt = 0;
		int total = 0;
		while (i < dataArrayList.size()) {
			String[] curRow = (String[]) dataArrayList.get(i);

			if (rowId >= rowStart) {
				if (!curRow[0].equals(prevKeyid)) {
					if (prevKeyid != null) {
						cell.put(total);
						rowObj.put("cell", cell);
						rowArr.put(rowObj);
						rowId++;
						total = 0;
					}
					rowObj = new JSONObject();
					rowObj.put("id", rowId + 1);
					cell = new JSONArray();
					cell.put((curRow[0] != null
							? curRow[0].replace("{", "").replace("}", "").replace("[", "").replace("]", "")
							: " "));
					cell.put((curRow[1] != null
							? curRow[1].replace("{", "").replace("}", "").replace("[", "").replace("]", "")
							: " "));

				}
				cnt = Integer.parseInt(curRow[2]);
				total += cnt;
				cell.put(cnt);
			}
			prevKeyid = curRow[0]; // @keyid - to compare next row
			i++;
		}
		cell.put(total);
		rowObj.put("cell", cell);
		rowArr.put(rowObj);

		tableDataObject.put("rows", rowArr);
		return tableDataObject;
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getAbnRelatedFilters(request, commonFilter);

			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-1).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			}

			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		return commonFilter;
	}
}
