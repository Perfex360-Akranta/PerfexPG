
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlTopicmstBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointdet;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;
import com.akranta.tpm.model.EntTlTopicmst;
import com.akranta.tpm.model.EntTlTrainingfeedmst;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.JhnTlSladtl;
import com.akranta.tpm.service.SkillindexRpService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class SkillIndexReviewPoint
 */
@WebServlet("/SkillIndexReviewPoint")
public class SkillIndexReviewPointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SkillindexRpService skillindexRpservice;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SkillIndexReviewPointServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws NoDataFoundException, SQLException, Exception {
		String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);

		ComboFilter comboFilter = new ComboFilter();
		comboFilter = UIUtils.fillComboFilter(request);

		try {

			skillindexRpservice = (SkillindexRpService) UIUtils.getServiceObject(request, "SkillindexRpServiceImpl");
			skillindexRpservice
					.SkillindexRpServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? ""
							: httpSession.getAttribute("tpmjwttoken")));
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		String dispatchUrl = null;

		if (action.equals("filterXmlSkillIndexRpMain_view.sirp")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/skillIndex.xml");
		} else if (action.equals("getModeSkillAssementRpMain_view.sirp")) {
			CommonMessage.debugMsg("msg");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode", "create");
			result.put("url", "SkillIndexAssement_input.sirp");
			result.put("formHeader", "Skill Index Review point");

			out.println(result);
		}

		if (action.equals("filterXmlSkillAssementRpMain_view.sirp")) {
			response.setContentType("xml");
			UIUtils.forwardRequest(request, response, "/tiles/xml/SkillIndexAssesMent.xml");
		}
		if (action.equals("SkillIndexRpMain_view.sirp") || action.equals("SkillAssementRpMain_view.sirp")
				|| action.equals("SkillIndexReport_input.sirp")) {

			String mstkeyid = request.getParameter("keyId");
			String reportName = "";

			if (action.equals("SkillIndexRpMain_view.sirp"))
				reportName = "REVIEW";
			else if (action.equals("SkillAssementRpMain_view.sirp"))
				reportName = "ASSEMENT";
			else if (action.equals("SkillIndexReport_input.sirp"))
				reportName = "REPORT";

			/*
			 * if ((mstkeyid != null)){ EntTlSkillReviewpointmst newEntTlSkillReviewpointmst
			 * = skillindexRpservice.select(mstkeyid);
			 * CommonMessage.debugMsg("keyid ::::::"+newEntTlSkillReviewpointmst.
			 * getSirmKeyid()); request.setAttribute("SIRP", newEntTlSkillReviewpointmst); }
			 */
			request.setAttribute("mstkeyid", mstkeyid);
			request.setAttribute("reportName", reportName);
			request.setAttribute("mode", FormModes.create);

			UIUtils.forwardRequest(request, response, "/pages/ENT/SkillIndexMainGrid.jsp");

		}

		else if (action.equals("getModeSkillIndexRpMain_view.sirp")) {
			CommonMessage.debugMsg("msg");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			JSONObject result = new JSONObject();
			result.put("mode", "create");
			result.put("url", "RePointDetail_input.sirp");
			result.put("formHeader", "Skill Index Review point");

			out.println(result);
		}

		else if (action.equals("functionalLoc_skillAssement.sirp")) {

			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSbu("sbu");
			functLocFieldNameBean.setPbu("pbu");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("flid");
			functLocFieldNameBean.setSbuMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);

			// FormModes formModes = (FormModes)httpSession.getAttribute("SkillFormMode");
			// if( formModes == FormModes.completion)
			FormModes formModes = FormModes.create;

			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);

		}

		else if (action.equals("functionalLoc.sirp")) {
			CommonMessage.debugMsg("function location ");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("factory");
			functLocFieldNameBean.setSection("section");
			functLocFieldNameBean.setCell("cell");
			functLocFieldNameBean.setMachine("machine");
			functLocFieldNameBean.setFunctionalLocId("cmbtfmsFlid");
			functLocFieldNameBean.setLocnMandatory(true);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			CommonMessage.debugMsg("functionlocation null ");
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);

		}

		else if (action.equals("SkillIndexAssement_input.sirp")) {

			String flId = request.getParameter("flId");
			String uniqPosid = request.getParameter("uniqPosid");
			String creteriaId = request.getParameter("creteriaId");
			String reviewDate = request.getParameter("reviewDate");

			request.setAttribute("flId", flId);
			request.setAttribute("uniqPosid", uniqPosid);
			request.setAttribute("creteriaId", creteriaId);
			request.setAttribute("reviewDate", reviewDate);

			UIUtils.forwardRequest(request, response, "/pages/ENT/SkillIndexAssessmentReport.jsp");

		}

		else if (action.equals("SkillIndexAssessment_getCol.sirp")) {
			PrintWriter out = response.getWriter();

			String flnid = request.getParameter("flnid");
			CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
			String errlid = request.getParameter("errlid");
			CommonMessage.debugMsg("refKeyId::::::::::" + errlid);
			String empId = request.getParameter("empId");
			String reviewDate = request.getParameter("reviewDate");
			JSONObject jsonObject = new JSONObject();
			List<String[]> skillasslst = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setEmpch(empId);
				commonFilter.setStartDate(reviewDate);
				skillasslst = skillindexRpservice.getSkillAssessmentReport(commonFilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonObject = getTableModelSkillAssessment(skillasslst);
			httpSession.removeAttribute("SkillAssessmentColmodel");
			httpSession.setAttribute("SkillAssessmentColmodel", jsonObject);
			out.println(jsonObject);

		} else if (action.equals("SkillIndexAssessment_getData.sirp")) {
			List<String[]> skillDatalst = null;

			PrintWriter out = response.getWriter();

			String flnid = request.getParameter("flnid");
			CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
			String errlid = request.getParameter("errlid");
			String empId = request.getParameter("empId");
			String reviewDate = request.getParameter("reviewDate");
			CommonMessage.debugMsg("reviewDate::::::::::" + reviewDate);
			List<String[]> skillasslst = null;
			CommonFilter commonFilter = new CommonFilter();
			try {
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setStartDate(reviewDate);
				commonFilter.setEmpch(empId);
				skillDatalst = skillindexRpservice.getSkillAssessmentReport(commonFilter);
				JSONObject Achievements = UIUtils.convertToJqGridTableObject(skillDatalst, request, 3, 0);
				out.println(Achievements);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("SkillIndexAssessment_getExcel.sirp")) {

			String flnid = request.getParameter("flnid");
			CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
			String errlid = request.getParameter("errlid");

			String reviewDate = request.getParameter("reviewDate");
			CommonMessage.debugMsg("refKeyId::::::::::" + errlid);
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flnid);
			commonFilter.setEmmLinkKeyId(errlid);
			commonFilter.setStartDate(reviewDate);

			String tmpFromRow = commonFilter.getFromRow();

			JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("SkillAssessmentColmodel");

			tblJSONObj.put("title", "Training Attendance Summary");
			String format = ExcelUtils.getFormat(request);

			commonFilter.setFromRow(tmpFromRow);

			Workbook wb = skillindexRpservice.SkillIndexExcel(commonFilter, tblJSONObj, format);

			ExcelUtils.writeToResponse(response, wb, "SkillAssementDetails", format);

		}

		else if (action.equals("UniquePositionform_getCol.sirp")) {
			PrintWriter out = response.getWriter();

			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList", "empListGrid"));
			CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList", "empListGrid"));
		} else if (action.equals("UniquePositionform_getData.sirp")) {
			try {
				PrintWriter out = response.getWriter();

				CommonFilter commonFilter = populateCommonFilter(request, "uniqueCommonFilter", true);
				GridParams gridParams = (GridParams) httpSession.getAttribute("gridParams");
				if (gridParams == null)
					gridParams = new GridParams();
				FilterValues.populateGridParams(request, gridParams);
				String flnid = request.getParameter("flnid");
				CommonMessage.debugMsg("refKeyId::::::::::" + flnid);
				String errlid = request.getParameter("errlid");
				String empId = request.getParameter("empId");
				String reviewDate = request.getParameter("reviewDate");
				String halfYear = request.getParameter("halfYear");
				commonFilter.setFlid(flnid);
				commonFilter.setEmmLinkKeyId(errlid);
				commonFilter.setStartDate(reviewDate);
				commonFilter.setEmpch(empId);
				commonFilter.setYear(halfYear);
				UIUtils.displayRequestParamsValue(request);
				//List<String[]> uniquelist = skillindexRpservice.getEmpList(commonFilter);
				
				List<String[]> uniquelist = skillindexRpservice.getEmpListFunction(commonFilter,gridParams);

				net.sf.json.JSONObject unique = UIUtils.convertToJqGridTableObject(uniquelist, request, 1, 1);
				out.println(unique);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		/*
		 * else if (action.equals("UniquePositionform_getData.sirp")) { try {
		 * PrintWriter out = response.getWriter();
		 * 
		 * CommonFilter commonFilter = populateCommonFilter(request,
		 * "uniqueCommonFilter", true);
		 * 
		 * String flnid = request.getParameter("flnid");
		 * CommonMessage.debugMsg("refKeyId::::::::::" + flnid); String errlid =
		 * request.getParameter("errlid"); String empId = request.getParameter("empId");
		 * String reviewDate = request.getParameter("reviewDate");
		 * commonFilter.setFlid(flnid); commonFilter.setEmmLinkKeyId(errlid);
		 * commonFilter.setStartDate(reviewDate); commonFilter.setEmpch(empId);
		 * UIUtils.displayRequestParamsValue(request); List<String[]> uniquelist =
		 * skillindexRpservice.getEmpList(commonFilter);
		 * 
		 * net.sf.json.JSONObject unique =
		 * UIUtils.convertToJqGridTableObject(uniquelist, request, 0, 0);
		 * out.println(unique); } catch (Exception e) {
		 * CommonMessage.debugMsg(e.getMessage()); } }
		 */

		/*
		 * else if(action.equals("SkillIndexRpMain_getCol.sirp") ||
		 * action.equals("SkillAssementRpMain_getCol.sirp") ) { PrintWriter out =
		 * response.getWriter();
		 * 
		 * CommonFilter commonFilter =
		 * populateCommonFilter(request,"SkillIndexCommonFilter", true);
		 * CommonMessage.debugMsg("getColEntry:::::: "+commonFilter.getFromDate());
		 * 
		 * String reportName = request.getParameter("reportName"); String flid =
		 * request.getParameter("flid"); if (UIUtils.isValidKeyId(flid))
		 * commonFilter.setFlid(flid); if (!UIUtils.isValidKeyId(flid)) { String
		 * loginFlid = CommonFunctions.getLoginFlid(request);
		 * commonFilter.setFlid(loginFlid); } commonFilter.setHrschkbox("HEADER");
		 * commonFilter.setIsGetCol("Y");
		 * 
		 * List<String[]> skReqList = skillindexRpservice.getSIMainGrid(commonFilter,
		 * reportName);
		 * 
		 * JSONObject SkillData= UIUtils.convertToJqGridTableObject(skReqList,request,
		 * 2, 0, commonFilter.getTotalRecordCnt());
		 * CommonMessage.debugMsg("UOTSIDEskillindexRpservice ");
		 * httpSession.setAttribute("SkillData", SkillData); JqGridTableModel
		 * jqGridTableModel = new JqGridTableModel(); GridColModel gridColModel = new
		 * GridColModel(); jqGridTableModel.setRowNumbers(true);
		 * jqGridTableModel.setEnableFilter(false);
		 * jqGridTableModel.setTableButton(true); gridColModel.setHeaderNum(1);
		 * 
		 * gridColModel.setFormatter("btnShowFormmter"); if
		 * (reportName.equals("REVIEW")) { gridColModel.setFormattorFromCol("7");
		 * gridColModel.setFormattorToCol("7"); } else {
		 * gridColModel.setFormattorFromCol("8"); gridColModel.setFormattorToCol("8"); }
		 * 
		 * String [] colHeader = skReqList.get(1); String [] colHeaderCond =
		 * skReqList.get(0);
		 * 
		 * List<String[]> headers = new ArrayList<String[]>(); headers.add(colHeader);
		 * headers.add(colHeaderCond);
		 * 
		 * 
		 * JSONObject jsonObject =
		 * UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
		 * jsonObject.set("tableWidth", "106%%"); jsonObject.set("tableHeight", "78%%");
		 * 
		 * jsonObject.put("isGroupBy", "false"); jsonObject.put("groupByField", false);
		 * jsonObject.put("rowNumbers", true); jsonObject.put("groupSummary", false);
		 * jsonObject.put("tableButton", true);
		 * 
		 * //jsonObject.put("tableHeight", "75%"); //jsonObject.put("data", SkillData);
		 * 
		 * CommonMessage.debugMsg(jsonObject);
		 * httpSession.removeAttribute("SkillReportColModel");
		 * httpSession.setAttribute("SkillReportColModel", jsonObject);
		 * out.println(jsonObject); }
		 */

		else if (action.equals("SkillIndexRpMain_getCol.sirp") || action.equals("SkillAssementRpMain_getCol.sirp")) {
			PrintWriter out = response.getWriter();

			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", true);
			CommonMessage.debugMsg("getColEntry:::::: " + commonFilter.getFromDate());

			String reportName = request.getParameter("reportName");
			String flid = request.getParameter("flid");
			if (UIUtils.isValidKeyId(flid))
				commonFilter.setFlid(flid);
			if (!UIUtils.isValidKeyId(flid)) {
				String loginFlid = CommonFunctions.getLoginFlid(request);
				commonFilter.setFlid(loginFlid);
			}
			commonFilter.setHrschkbox("HEADER");
			commonFilter.setIsGetCol("Y");

			List<String[]> skReqList = skillindexRpservice.getSIMainGrid(commonFilter, reportName);

			JSONObject SkillData = UIUtils.convertToJqGridTableObject(skReqList, request, 2, 0,
					commonFilter.getTotalRecordCnt());
			CommonMessage.debugMsg("UOTSIDEskillindexRpservice ");
			httpSession.setAttribute("SkillData", SkillData);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(false);
			jqGridTableModel.setTableButton(true);
			gridColModel.setHeaderNum(1);

			gridColModel.setFormatter("btnShowFormmter");
			if (reportName.equals("REVIEW")) {
				gridColModel.setFormattorFromCol("7");
				gridColModel.setFormattorToCol("7");
			} else {
				gridColModel.setFormattorFromCol("10");// changed here
				gridColModel.setFormattorToCol("10");// changed here
			}

			String[] colHeader = skReqList.get(1);
			String[] colHeaderCond = skReqList.get(0);

			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			headers.add(colHeaderCond);

			JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			jsonObject.set("tableWidth", "106%%");
			jsonObject.set("tableHeight", "78%%");
			/*
			 * jsonObject.put("isGroupBy", "false"); jsonObject.put("groupByField", false);
			 * jsonObject.put("rowNumbers", true); jsonObject.put("groupSummary", false);
			 * jsonObject.put("tableButton", true);
			 */
			// jsonObject.put("tableHeight", "75%");
			// jsonObject.put("data", SkillData);

			CommonMessage.debugMsg(jsonObject);
			httpSession.removeAttribute("SkillReportColModel");
			httpSession.setAttribute("SkillReportColModel", jsonObject);
			out.println(jsonObject);
		} else if (action.equals("SkillIndexRpMain_getData.sirp")
				|| action.equals("SkillAssementRpMain_getData.sirp")) {
			try {
				PrintWriter out = response.getWriter();
				UIUtils.displayRequestParamsValue(request);
				CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter", false);
				String reportName = request.getParameter("reportName");
				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("flid====" + flid);
				if (UIUtils.isValidKeyId(flid))
					commonFilter.setFlid(flid);
				if (!UIUtils.isValidKeyId(commonFilter.getFlid())) {
					String loginFlid = CommonFunctions.getLoginFlid(request);
					CommonMessage.debugMsg("flid====" + loginFlid);
					commonFilter.setFlid(loginFlid);
				}

				commonFilter.setIsGetCol("N");

				List<String[]> ShillDataList = skillindexRpservice.getSIMainGrid(commonFilter, reportName);
				JSONObject SkillIndexData = UIUtils.convertToJqGridTableObject(ShillDataList, request, 2, 0,
						commonFilter.getTotalRecordCnt());
				out.println(SkillIndexData);
				httpSession.removeAttribute("SkillIndexCommonFilter");
				httpSession.setAttribute("SkillIndexCommonFilter", commonFilter);

			} catch (Exception e) {
				CommonMessage.debugMsg("getdata Exception :" + e.getMessage());
				e.printStackTrace();
			}
		} else if (action.equals("RePointDetail_input.sirp")) {
			// HttpSession httpSession = request.getSession(false);
			// String mstkeyid=request.getParameter("mstkeyid");
			String sirmKeyid = request.getParameter("keyId");
			String flid = request.getParameter("flid");
			String uniqPosid = request.getParameter("uniqPosid");

			if ((sirmKeyid != null)) {
				// EntTlSkillReviewpointmst newEntTlSkillReviewpointmst =
				// skillindexRpservice.select(sirmKeyid);
				// CommonMessage.debugMsg("keyid
				// "+newEntTlSkillReviewpointmst.getSirmKeyid());
				// request.setAttribute("SIRP", newEntTlSkillReviewpointmst);
			}

			request.setAttribute("sirmKeyid", sirmKeyid);
			request.setAttribute("flid", flid);
			request.setAttribute("uniqPosid", uniqPosid);
			request.setAttribute("mode", FormModes.create);

			UIUtils.forwardRequest(request, response, "/pages/ENT/SkillIndexReviewPoint.jsp");
		}

		else if (action.equals("RePointDetail_getCol.sirp")) {
			CommonMessage.debugMsg("dhgfdshgfa");
			ReviewPointDetailgetCol(request, response);
		} else if (action.equals("RePointDetail_getData.sirp")) {
			ReviewPointDetailgetData(request, response);
		} else if (action.equals("ReviewPoint_save.sirp")) {
			ReviewPointSave(request, response);
		} else if (action.equals("SkillIndexListDetl_remove.sirp")) {
			deleteSkilldetails(request, response);
		}

		else if (action.equals("RePointDetail_delete.sirp")) {
			DeleteMasterDeatisl(request, response);
		} else if (action.equals("RePointDetail_save.sirp")) {
			ReviewPointSaveMst(request, response);
		}

		else if (action.equals("SkillIndexAssement_save.sirp")) {
			saveSkillIndexAssement(request, response);
		}

		else if (action.equals("getRecallReviewPoints.sirp")) {
			String flid = request.getParameter("flid");
			String roleId = request.getParameter("roleId");
			String keyid = skillindexRpservice.getRecallReivewPoints(flid, roleId);
			JSONObject plmData = new JSONObject();
			plmData.put("keyid", keyid);
			PrintWriter out = response.getWriter();
			out.print(plmData);
		} else if (action.equals("SkillAssementRpMain_getExcel.sirp")) {
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request, "SkillIndexCommonFilter ", false);
			JSONObject colmodel = UIUtils.getXlColModel(request, response);

			colmodel.put("title", "SkillIndex");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = skillindexRpservice.getskillIndexExcel(colmodel, format, commonFilter);
			ExcelUtils.writeToResponse(response, wb, "SkillIndex", format);

		} else if (action.equals("combo_empType.sirp")) {
			try {
				CommonFilter commonFilter = new CommonFilter();
				String filter = request.getParameter("q");
				ComboFilter currentFilter = new ComboFilter();
				currentFilter.setCode(filter != null ? filter.toUpperCase() : filter);
				currentFilter.setName(filter != null ? filter.toUpperCase() : filter);

				currentFilter = UIUtils.fillComboFilter(request);
				commonFilter.setEmp(currentFilter);

				List<ComboBox> prdModel = skillindexRpservice.getEmpTypeCombo(commonFilter);

				UIUtils.writeComboBox(response, prdModel, comboFilter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// CommonMessage.debugMsg("condSql................"+e.toString());
				e.printStackTrace();
			}
		}

	}

//skill index assment 

	/*
	 * private void saveSkillIndexAssement(HttpServletRequest request,
	 * HttpServletResponse response) throws IOException { HttpSession httpSession =
	 * request.getSession(false); ServletOutputStream out =
	 * response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request); try { if (httpSession != null && user != null)
	 * {
	 * 
	 * EntTlSkillindexassessmst existEntTlSkillindexassessmst =
	 * (EntTlSkillindexassessmst) httpSession
	 * .getAttribute("newEntTlAssessmentmst"); EntTlSkillindexassessmst
	 * newEntTlSkillindexassessmst = new EntTlSkillindexassessmst();// mas
	 * 
	 * EntTlSkillindexassessdtl newEntTlSkillindexassessdtl = new
	 * EntTlSkillindexassessdtl();
	 * 
	 * String MasterProject = request.getParameter("masterData"); String
	 * DetailProject = request.getParameter("detailData");
	 * CommonMessage.debugMsg("TrainingDetails: " + MasterProject);
	 * CommonMessage.debugMsg(MasterProject + "MASTERDATA: " + DetailProject); //
	 * master List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl = null;
	 * 
	 * List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst = null;
	 * JSONArray jsonmasterArray = null; JSONArray jsondetailArray = null; if
	 * (UIUtils.isValidKeyId(MasterProject)) { // master json
	 * 
	 * if (MasterProject != null && !MasterProject.isEmpty()) { jsonmasterArray =
	 * JSONArray.fromString(MasterProject); lstEntTlSkillindexassessmstmst =
	 * (List<EntTlSkillindexassessmst>) UIUtils
	 * .convertJSONArrToList(newEntTlSkillindexassessmst, jsonmasterArray);
	 * 
	 * } if (lstEntTlSkillindexassessmstmst != null) {
	 * newEntTlSkillindexassessmst.setSiamCreatedby(user.getUsrm_keyid()); } } List
	 * for detail * if (UIUtils.isValidKeyId(DetailProject)) { // detail json if
	 * (DetailProject != null && !DetailProject.isEmpty()) { jsondetailArray =
	 * JSONArray.fromString(DetailProject); lstEntTlSkillindexassessmstdtl =
	 * (List<EntTlSkillindexassessdtl>) UIUtils
	 * .convertJSONArrToList(newEntTlSkillindexassessdtl, jsondetailArray); }
	 * 
	 * }
	 * 
	 * JSONObject successData = new JSONObject(); JSONObject
	 * trainingAttEffSuccessMsg = new JSONObject(); String savemsg; //
	 * newEntTlAssessmentmst.setAsmmCreatedby(user.getUsrm_ccno()); CommonFunctions
	 * .debugMsg("lstEntTlSkillindexassessmstmst   ass(): " +
	 * lstEntTlSkillindexassessmstmst.size()); CommonFunctions
	 * .debugMsg("lstEntTlSkillindexassessmstdtl   ==(): " +
	 * lstEntTlSkillindexassessmstdtl.size());
	 * 
	 * // existKznTlKkprojectprioritymst=kkProjecPriorityService.create(
	 * newEntTlSkillindexassessmst, // existKznTlKkprojectprioritymst);
	 * skillindexRpservice.createSkillAssement(lstEntTlSkillindexassessmstmst,
	 * lstEntTlSkillindexassessmstdtl, user.getUsrm_keyid());
	 * 
	 * savemsg =
	 * UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",
	 * "success-save");
	 * 
	 * successData.put("msg", savemsg); trainingAttEffSuccessMsg.put("successData",
	 * successData); trainingAttEffSuccessMsg.put("formClear", false);
	 * 
	 * out.print(trainingAttEffSuccessMsg.toString());
	 * 
	 * } // newEntTlAssessmentmst //
	 * =entTlAssessmentmstService.create(newEntTlAssessmentmst,
	 * existEntTlAssessmentmst,entTlAssessmentmstBean);
	 * 
	 * } catch (ValidationExceptions e) {
	 * CommonMessage.debugMsg("validations exception"); JSONObject errMessage =
	 * UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
	 * CommonMessage.debugMsg(errMessage.toString());
	 * out.print(errMessage.toString());
	 * 
	 * } catch (BusinessApplicationExceptions e) {
	 * CommonMessage.debugMsg("DNFKHDJFDK" + e.toString()); JSONObject errMessage =
	 * UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
	 * out.print(errMessage.toString());
	 * 
	 * } catch (Exception e) {
	 * 
	 * JSONObject err = new JSONObject(); // err.put("tpmException",
	 * "Data Not Saved"); err.put("tpmException", e.getMessage());
	 * out.print(err.toString()); }
	 * 
	 * }
	 */
	
//	private void saveSkillIndexAssement(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		HttpSession httpSession = request.getSession(false);
//		ServletOutputStream out = response.getOutputStream();
//		AdmTlUsermst user = UIUtils.getLoginUser(request);
//		String newSiamKeyId = request.getParameter("siamKeyidNew");
//		try {
//			if (httpSession != null && user != null) {
//
//				EntTlSkillindexassessmst existEntTlSkillindexassessmst = (EntTlSkillindexassessmst) httpSession
//						.getAttribute("newEntTlAssessmentmst");
//				EntTlSkillindexassessmst newEntTlSkillindexassessmst = new EntTlSkillindexassessmst();// mas
//
//				EntTlSkillindexassessdtl newEntTlSkillindexassessdtl = new EntTlSkillindexassessdtl();
//
//				String MasterProject = request.getParameter("masterData");
//				String DetailProject = request.getParameter("detailData");
//				CommonFunctions
//				.debugMsg("TrainingDetails: " + MasterProject);
//				CommonFunctions
//				.debugMsg(MasterProject + "MASTERDATA: " + DetailProject); // master
//				List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl = null;
//
//				List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst = null;
//				JSONArray jsonmasterArray = null;
//				JSONArray jsondetailArray = null;
//				if (UIUtils.isValidKeyId(MasterProject)) { // master json
//
//					if (MasterProject != null && !MasterProject.isEmpty()) {
//						jsonmasterArray = JSONArray.fromString(MasterProject);
//						lstEntTlSkillindexassessmstmst = (List<EntTlSkillindexassessmst>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessmst, jsonmasterArray);
//
//					}
//					if (lstEntTlSkillindexassessmstmst != null) {
//						newEntTlSkillindexassessmst.setSiamCreatedby(user.getUsrm_keyid());
//					}
//				}
//				/* List for detail **/
//				if (UIUtils.isValidKeyId(DetailProject)) { // detail json
//					if (DetailProject != null && !DetailProject.isEmpty()) {
//						jsondetailArray = JSONArray.fromString(DetailProject);
//						lstEntTlSkillindexassessmstdtl = (List<EntTlSkillindexassessdtl>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessdtl, jsondetailArray);
//					}
//
//				}
//
//				JSONObject successData = new JSONObject();
//				JSONObject trainingAttEffSuccessMsg = new JSONObject();
//				String savemsg;
//				// newEntTlAssessmentmst.setAsmmCreatedby(user.getUsrm_ccno());
//				CommonFunctions
//						.debugMsg("lstEntTlSkillindexassessmstmst   ass(): " + lstEntTlSkillindexassessmstmst.size());
//				CommonFunctions
//						.debugMsg("lstEntTlSkillindexassessmstdtl   ==(): " + lstEntTlSkillindexassessmstdtl.size());
//
//				// existKznTlKkprojectprioritymst=kkProjecPriorityService.create(newEntTlSkillindexassessmst,
//				// existKznTlKkprojectprioritymst);
//				skillindexRpservice.createSkillAssement(lstEntTlSkillindexassessmstmst, lstEntTlSkillindexassessmstdtl,
//						user.getUsrm_keyid(),newSiamKeyId);
//
//				savemsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");
//
//				successData.put("msg", savemsg);
//				trainingAttEffSuccessMsg.put("successData", successData);
//				trainingAttEffSuccessMsg.put("formClear", false);
//
//				out.print(trainingAttEffSuccessMsg.toString());
//
//			} // newEntTlAssessmentmst
//				// =entTlAssessmentmstService.create(newEntTlAssessmentmst,existEntTlAssessmentmst,entTlAssessmentmstBean);
//
//		} catch (ValidationExceptions e) {
//			CommonFunctions.debugMsg("validations exception");
//			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
//			CommonFunctions.debugMsg(errMessage.toString());
//			out.print(errMessage.toString());
//
//		} catch (BusinessApplicationExceptions e) {
//			CommonFunctions.debugMsg("DNFKHDJFDK" + e.toString());
//			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
//			out.print(errMessage.toString());
//
//		} catch (Exception e) {
//
//			JSONObject err = new JSONObject();
//			// err.put("tpmException", "Data Not Saved");
//			err.put("tpmException", e.getMessage());
//			out.print(err.toString());
//		}
//
//	}
	
	private void saveSkillIndexAssement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		//String newSiamKeyId = request.getParameter("siamKeyidNew");//commented HERE
		try {
			if (httpSession != null && user != null) {

				EntTlSkillindexassessmst existEntTlSkillindexassessmst = (EntTlSkillindexassessmst) httpSession
						.getAttribute("newEntTlAssessmentmst");
				EntTlSkillindexassessmst newEntTlSkillindexassessmst = new EntTlSkillindexassessmst();// mas

				EntTlSkillindexassessdtl newEntTlSkillindexassessdtl = new EntTlSkillindexassessdtl();

				String MasterProject = request.getParameter("masterData");
				String DetailProject = request.getParameter("detailData");
				CommonFunctions
				.debugMsg("TrainingDetails: " + MasterProject);
				CommonFunctions
				.debugMsg(MasterProject + "MASTERDATA: " + DetailProject); // master
				List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessmstdtl = null;

				List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmstmst = null;
				JSONArray jsonmasterArray = null;
				JSONArray jsondetailArray = null;
				if (UIUtils.isValidKeyId(MasterProject)) { // master json

					if (MasterProject != null && !MasterProject.isEmpty()) {
						jsonmasterArray = JSONArray.fromString(MasterProject);
						lstEntTlSkillindexassessmstmst = (List<EntTlSkillindexassessmst>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessmst, jsonmasterArray);

					}
					if (lstEntTlSkillindexassessmstmst != null) {
						newEntTlSkillindexassessmst.setSiamCreatedby(user.getUsrm_keyid());
					}
				}
				/* List for detail **/
				if (UIUtils.isValidKeyId(DetailProject)) { // detail json
					if (DetailProject != null && !DetailProject.isEmpty()) {
						jsondetailArray = JSONArray.fromString(DetailProject);
						lstEntTlSkillindexassessmstdtl = (List<EntTlSkillindexassessdtl>) UIUtils.convertJSONArrToList(newEntTlSkillindexassessdtl, jsondetailArray);
					}

				}

				JSONObject successData = new JSONObject();
				JSONObject trainingAttEffSuccessMsg = new JSONObject();
				String savemsg;
				// newEntTlAssessmentmst.setAsmmCreatedby(user.getUsrm_ccno());
				CommonFunctions
						.debugMsg("lstEntTlSkillindexassessmstmst   ass(): " + lstEntTlSkillindexassessmstmst.size());
				CommonFunctions
						.debugMsg("lstEntTlSkillindexassessmstdtl   ==(): " + lstEntTlSkillindexassessmstdtl.size());

				// existKznTlKkprojectprioritymst=kkProjecPriorityService.create(newEntTlSkillindexassessmst,
				// existKznTlKkprojectprioritymst);
				skillindexRpservice.createSkillAssement(lstEntTlSkillindexassessmstmst, lstEntTlSkillindexassessmstdtl,
						user.getUsrm_keyid());//-CHANGE METHOD HERE

				savemsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");

				successData.put("msg", savemsg);
				trainingAttEffSuccessMsg.put("successData", successData);
				trainingAttEffSuccessMsg.put("formClear", false);

				out.print(trainingAttEffSuccessMsg.toString());

			} // newEntTlAssessmentmst
				// =entTlAssessmentmstService.create(newEntTlAssessmentmst,existEntTlAssessmentmst,entTlAssessmentmstBean);

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("validations exception");
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
			CommonFunctions.debugMsg(errMessage.toString());
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {
			CommonFunctions.debugMsg("DNFKHDJFDK" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());

		} catch (Exception e) {

			JSONObject err = new JSONObject();
			// err.put("tpmException", "Data Not Saved");
			err.put("tpmException", e.getMessage());
			out.print(err.toString());
		}

	}

//review points save

	private void ReviewPointSaveMst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		try {
			if (httpSession != null && user != null) {
				EntTlSkillReviewpointmst existEntTlSkillReviewpointmst = (EntTlSkillReviewpointmst) httpSession
						.getAttribute("entTlSkillReviewpointmst");
				EntTlSkillReviewpointmst newentTlSkillReviewpointmst = new EntTlSkillReviewpointmst();
				newentTlSkillReviewpointmst.setSirmCreatedby(user.getUsrm_ccno());
				newentTlSkillReviewpointmst = (EntTlSkillReviewpointmst) UIUtils
						.setBeanProperties((Object) newentTlSkillReviewpointmst, request);

				String saveMsg;
				// CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()" +
				// newQtmTlDockaudit.getQaudkeyid());
				if (newentTlSkillReviewpointmst.getSirmKeyid() == null) {
					CommonMessage.debugMsg("key id is not available ");

					existEntTlSkillReviewpointmst = skillindexRpservice.createmst(newentTlSkillReviewpointmst,
							existEntTlSkillReviewpointmst);
					saveMsg = "Data Saved Successfully";
				} else {
					CommonMessage.debugMsg("key id is available:" + newentTlSkillReviewpointmst.getSirmKeyid());
					existEntTlSkillReviewpointmst = skillindexRpservice.updatemst(newentTlSkillReviewpointmst,
							existEntTlSkillReviewpointmst);
					saveMsg = "Data Update Successfully";
				}

				CommonMessage.debugMsg("save details " + existEntTlSkillReviewpointmst.getSirmKeyid());
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("Save completed");
				String openFileMgr = request.getParameter("openFileManager");
				if (UIUtils.isValidKeyId(openFileMgr))
					returnData.put("openFileMgr", "Y");
				successData.put("keyId", existEntTlSkillReviewpointmst.getSirmKeyid());
				successData.put("Rollmast", existEntTlSkillReviewpointmst.getSirmRoleKeyid());
				// successData.put("spokemst", exitEntTlSkillReviewpointdet.getSirdSpokKeyid());
				successData.put("Flid", existEntTlSkillReviewpointmst.getSirmFlid());

				successData.put("msg", saveMsg);
				returnData.put("formClear", false);
				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (ValidationExceptions e) {
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {

			CommonMessage.debugMsg("Error Servler e -" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {

			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}

	}

	private void DeleteMasterDeatisl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if (httpSession != null && user != null) {
			EntTlSkillReviewpointmst existEntTlSkillReviewpointmst = (EntTlSkillReviewpointmst) httpSession
					.getAttribute("entTlSkillReviewpointmst");
			// GenTlEmployeedtl existGenTlEmployeedtl =
			// (GenTlEmployeedtl)httpSession.getAttribute("genTl");
			EntTlSkillReviewpointmst newEntTlSkillReviewpointmst = new EntTlSkillReviewpointmst();
			EntTlSkillReviewpointdet newEntTlSkillReviewpointdet = new EntTlSkillReviewpointdet();

			newEntTlSkillReviewpointmst.setSirmCreatedby(user.getUsrm_ccno());
			newEntTlSkillReviewpointdet.setSirdCreatedby(user.getUsrm_ccno());
			newEntTlSkillReviewpointmst = (EntTlSkillReviewpointmst) UIUtils
					.setBeanProperties((Object) newEntTlSkillReviewpointmst, request);

			try {
				existEntTlSkillReviewpointmst = skillindexRpservice.delete(newEntTlSkillReviewpointmst);
				CommonMessage.debugMsg("After the IF Loop");
				httpSession.setAttribute(existEntTlSkillReviewpointmst.getSirmKeyid(), existEntTlSkillReviewpointmst);
				JSONObject mode = new JSONObject();
				JSONObject persistentData = new JSONObject();
				persistentData.put("SirmKeyid", existEntTlSkillReviewpointmst.getSirmKeyid());
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("SuccessData");
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete"));
				successData.put("keyId", existEntTlSkillReviewpointmst.getSirmKeyid());
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("formClear", true);
				CommonMessage.debugMsg("successData" + successData);
				out.print(returnData.toString());

			} catch (BusinessApplicationExceptions e) {
				CommonMessage.debugMsg("Business EXC " + e);
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "Momeeting");
				out.print(errMessage.toString());

			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", " Data Not Delete ");
				out.print(err.toString());
			}
		}

	}

	private void deleteSkilldetails(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonMessage.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonMessage.debugMsg("KEYID: " + keyid);
		try {
			if (UIUtils.isValidKeyId(keyid)) {
				skillindexRpservice.DeleteRplist(keyid);
				String msgPropertyIdnt = "success-delete";
				CommonMessage.debugMsg("msgPropertyIdnt:::::: " + msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt);
				err.put("successData", mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			CommonMessage.debugMsg("Delete End");
		} catch (ValidationExceptions e) {
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {

			CommonMessage.debugMsg("Error Servler e -" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");

			if (e.toString().contains("FK_SIAD_REVIEWID"))
				errMessage = UIUtils.businessValidationExceptions("FK_SIAD_REVIEWID", "SkillIndexRp");

			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception: " + e);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete");
			err.put("successData", mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
	}

	private void ReviewPointSave(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				String keyId = request.getParameter("keyid");
				EntTlSkillReviewpointmst existEntTlSkillReviewpointmst = (EntTlSkillReviewpointmst) httpSession
						.getAttribute("entTlSkillReviewpointmst");
				EntTlSkillReviewpointdet exitEntTlSkillReviewpointdet = (EntTlSkillReviewpointdet) httpSession
						.getAttribute("entTlSkillReviewpointdet");
				EntTlSkillReviewpointmst newentTlSkillReviewpointmst = new EntTlSkillReviewpointmst();
				EntTlSkillReviewpointdet entTlReviewPpointdet = new EntTlSkillReviewpointdet();

				newentTlSkillReviewpointmst.setSirmCreatedby(user.getUsrm_ccno());
				entTlReviewPpointdet.setSirdCreatedby(user.getUsrm_ccno());

				newentTlSkillReviewpointmst = (EntTlSkillReviewpointmst) UIUtils
						.setBeanProperties((Object) newentTlSkillReviewpointmst, request);
				entTlReviewPpointdet = (EntTlSkillReviewpointdet) UIUtils
						.setBeanProperties((Object) entTlReviewPpointdet, request);

				String saveMsg = null;
				// CommonMessage.debugMsg(" newQtmTlDockaudit.getQaudkeyid()" +
				// newQtmTlDockaudit.getQaudkeyid());

				if (!UIUtils.isValidKeyId(entTlReviewPpointdet.getSirdSpokKeyid()))
					throw new BusinessApplicationExceptions("EN_SPOKID" + ",");
				if (!UIUtils.isValidKeyId(entTlReviewPpointdet.getSirdReviewpoint()))
					throw new BusinessApplicationExceptions("EN_REVIEWPOINT" + ",");
				if (!UIUtils.isValidKeyId(entTlReviewPpointdet.getSirdReviewno()))
					throw new BusinessApplicationExceptions("EN_REVIEWNO" + ",");

				if (newentTlSkillReviewpointmst.getSirmKeyid() == null) {
					CommonMessage.debugMsg("key id is not available ");
					// entTlTopicmstBean.setFormMode(FormModes.create);
					existEntTlSkillReviewpointmst = skillindexRpservice.create(newentTlSkillReviewpointmst,
							existEntTlSkillReviewpointmst, entTlReviewPpointdet);
					saveMsg = "Data Saved Successfully";
				} else {

					if (UIUtils.isValidKeyId(newentTlSkillReviewpointmst.getSirmKeyid())
							&& UIUtils.isValidKeyId(entTlReviewPpointdet.getSirdKeyid())) {
						CommonMessage.debugMsg("Update SucessFuly  ");
						saveMsg = "Data Update Successfully";
					} else if (UIUtils.isValidKeyId(newentTlSkillReviewpointmst.getSirmKeyid())
							&& !UIUtils.isValidKeyId(entTlReviewPpointdet.getSirdKeyid())) {
						CommonMessage.debugMsg("saved SucessFuly  ");
						saveMsg = "Data Saved Successfully";
					}

					CommonMessage.debugMsg("key id is available:" + newentTlSkillReviewpointmst.getSirmKeyid());
					// entTlTopicmstBean.setFormMode(FormModes.modify);
					existEntTlSkillReviewpointmst = skillindexRpservice.update(newentTlSkillReviewpointmst,
							existEntTlSkillReviewpointmst, entTlReviewPpointdet);
					// saveMsg = "Data Update Successfully";
				}

				CommonMessage.debugMsg("save details " + existEntTlSkillReviewpointmst.getSirmKeyid());
				JSONObject returnData = new JSONObject();
				JSONObject successData = new JSONObject();
				CommonMessage.debugMsg("Save completed");
				String openFileMgr = request.getParameter("openFileManager");
				if (UIUtils.isValidKeyId(openFileMgr))
					returnData.put("openFileMgr", "Y");
				successData.put("keyId", existEntTlSkillReviewpointmst.getSirmKeyid());
				successData.put("Rollmast", existEntTlSkillReviewpointmst.getSirmRoleKeyid());
				successData.put("spokemst", entTlReviewPpointdet.getSirdSpokKeyid());
				successData.put("Flid", existEntTlSkillReviewpointmst.getSirmFlid());

				successData.put("msg", saveMsg);
				returnData.put("formClear", false);
				returnData.put("successData", successData);
				out.print(returnData.toString());
			}
		} catch (ValidationExceptions e) {
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());

		} catch (BusinessApplicationExceptions e) {

			CommonMessage.debugMsg("Error Servler e -" + e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "SkillIndexRp");
			out.print(errMessage.toString());
			CommonMessage.debugMsg(" e " + errMessage);
		} catch (Exception e) {

			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}

	}

	private void ReviewPointDetailgetCol(HttpServletRequest request, HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			CommonMessage.debugMsg("test input action skill index form.............");
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Reviewpoint", "Reviewpoint"));
			// CommonMessage.debugMsg("MoMeetingMom_getCol.mom end ");
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
	}

	private void ReviewPointDetailgetData(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession httpSession = request.getSession(false);
			UIUtils.displayRequestParamsValue(request);
			String mstkeyid = request.getParameter("mstkeyid");
			CommonFilter commonFilter = populateCommonFilter(request, "ReviewDetailsCommonFilter", false);
			List<String[]> TopicList = skillindexRpservice.getRpDetail(commonFilter, mstkeyid);
			// CommonMessage.debugMsg("equipmentQueryList " + TopicList.size());
			JSONObject TopicReqData = UIUtils.convertToJqGridTableObject(TopicList, request, 0, 0,
					commonFilter.getTotalRecordCnt());
			PrintWriter out = response.getWriter();
			out.println(TopicReqData);
			httpSession.removeAttribute("ReviewDetailsCommonFilter");
			httpSession.setAttribute("ReviewDetailsCommonFilter", commonFilter);

		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}

	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {

		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
		if (commonFilter != null && !createNew) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = FilterValues.getTraning(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');

		String trigger = request.getParameter("trigger");
		String flid = request.getParameter("flid");
		String drillFlag = request.getParameter("drillFlag");
		CommonMessage.debugMsg("flid====" + flid);
		commonFilter.setBreakup(trigger);
		commonFilter.setFlid(flid);
		if (UIUtils.isValidKeyId(drillFlag))
			commonFilter.setDrillFlag(drillFlag.charAt(0));

		CommonMessage.debugMsg("  populate commonFilter getMachineRank "
				+ (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}

	private JSONObject getTableModelSkillAssessment(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		CommonMessage.debugMsg("pRINTING THE TABLE MODEL");
		for (String[] arr : headers) {
			CommonMessage.debugMsg(Arrays.toString(arr));
		}
		String[] row = headers.get(0);
		String[] colHeader = headers.get(1);
		String[] colHeader2 = headers.get(2);
		String[] tempCol = new String[row.length];
		jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.setRowNumbers(true);
		String headerSql = "'SELECT ";
		String header = "'SELECT ";

		for (int i = 0; i < colHeader.length; i++) {

			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(tempCol[i] = "");
			jqGridColModel.setIndex(tempCol[i].replaceAll(" ", ""));
			jqGridColModel.setName(tempCol[i].replaceAll(" ", ""));

			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(row[i].replaceAll(" ", ""));
			jqGridColModel.setIndex(row[i].replaceAll(" ", "") + i);
			jqGridColModel.setName(row[i].replaceAll(" ", "") + i);
			jqGridColModel.setWidth(60);

			if (i == 0 || i == 1 || i == 5 || i == 6) {
				jqGridColModel.setHidden(true);
			}
			if (i == 2) {
				jqGridColModel.setWidth(100);
			}
			if (i == 3) {
				jqGridColModel.setWidth(45);
			}
			if (i == 4) {
				jqGridColModel.setWidth(200);
			}
			// if ( jqGridColModel.getName().contains("EMP")) {

			if (i == 7) {
				jqGridColModel.setWidth(90);
				jqGridColModel.setAlign("center");
			}
			if (i == 8) {
				jqGridColModel.setFormatter("formatterCheckbox");
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("center");
			}
			if (i == 9) {
				jqGridColModel.setWidth(50);
				jqGridColModel.setAlign("center");
			}

			if (i == colHeader.length - 1) {
				jqGridColModel.setHidden(true);
			}

			// CommonMessage.debugMsg("column["+i+"]::::"+jqGridColModel.getName().contains("CHK"));
			CommonMessage.debugMsg("column[" + i + "]::::" + jqGridColModel.getName());
			jqGridTableModel.getColModel().add(jqGridColModel);
			headerSql = headerSql + UIUtils.getTablemodelSql(jqGridColModel);
			header = header + "''" + jqGridColModel.getName() + "'' as " + jqGridColModel.getName().toUpperCase() + ",";
		}
		headerSql = headerSql.substring(0, headerSql.length() - 1) + " FROM DUAL ";
		header = header + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql....." + headerSql);
		CommonMessage.debugMsg("header......." + header);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "70%%");
		tableModel.set("tableWidth", "60%%");
		return tableModel;

	}

}
