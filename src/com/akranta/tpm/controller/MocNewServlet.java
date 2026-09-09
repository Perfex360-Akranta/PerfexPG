package com.akranta.tpm.controller;

import java.io.File;
import com.akranta.tpm.utils.CommonMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//import javax.xml.bind.ValidationException;

//import org.apache.poi.ss.usermodel.Workbook;

//import lotus.domino.NotesException;

import com.akranta.tpm.service.ActionPlanRptService;
import com.akranta.tpm.service.MocService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.impl.MocServiceImpl;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AuditmasterModel;
//import com.akranta.tpm.model.BadgeTrackMst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlTrgCalEmp;
//import com.akranta.tpm.model.FieldObservationdesc;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.HazopDtl;
import com.akranta.tpm.model.HazopMst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.MOCPssrReccommend;
import com.akranta.tpm.model.MOCReccommendation;
import com.akranta.tpm.model.MocClosure;
import com.akranta.tpm.model.MocPssrdtl;
import com.akranta.tpm.model.MocPssrmst;
import com.akranta.tpm.model.MocRfQuestions;
import com.akranta.tpm.model.MocRfcBasismst;
import com.akranta.tpm.model.MocRfcmst;
import com.akranta.tpm.model.MocRfcmstNew;
import com.akranta.tpm.model.MocTeamConfigmst;
import com.akranta.tpm.model.MocTeamConfigmstNew;
import com.akranta.tpm.model.WhatifDtl;
import com.akranta.tpm.model.WhatifMst;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.MocNewService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.DashboardServiceImpl;
import com.akranta.tpm.service.impl.MocNewServiceImpl;
import com.akranta.tpm.service.impl.ActionPlanRptServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/MocNewServlet")
public class MocNewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MocNewService mocService;
	private CommonFilterService commonFilterService;
	private DashboardService dashboardService;
	private ActionPlanRptService actionPlanRptService;
	private static String realPath;
	String glbmsg = null;
	String glbpopup = null;
	
	private static final String AdmUploadExcelServlet_filename = "DocManagerServletfilename" ; 
	private static final String DESTINATION_DIR_PATH = "tmpFiles";

	private static final String gendrillcommonfilter = "susagendrillfilter";

	
	
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        realPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        boolean s = new File(realPath).mkdirs();
        
    }
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MocNewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession httpSession = request.getSession(false);
		String action = UIUtils.getActionPart(request);
		ComboFilter currentFilter = new ComboFilter();
		// mocServiceImpl=(mocServiceImpl)UIUtils.getServiceObject(request,"mocServiceImpl");
		commonFilterService = (CommonFilterServiceImpl) UIUtils.getServiceObject(request, "CommonFilterServiceImpl");
		mocService = (MocNewServiceImpl) UIUtils.getServiceObject(request, "MocNewServiceImpl");
		dashboardService = (DashboardServiceImpl) UIUtils.getServiceObject(request, "DashboardServiceImpl");
		actionPlanRptService = (ActionPlanRptServiceImpl) UIUtils.getServiceObject(request, "ActionPlanRptServiceImpl");

		if (action.equals("Questionaire_input.mocn")) {
			CommonMessage.debugMsg("Action::::" + action);
			UIUtils.forwardRequest(request, response, "pages/Questionaire.jsp");

		}
		else if (action.equals("WhatIfExcelPopup_input.mocn")) {

		    CommonFunctions.debugMsg("Opening WhatIf Excel Popup");

		    UIUtils.forwardRequest(request, response, "/pages/MOCWhatIfExcelPopup.jsp");
		}
		 else if (action.equals("HazopExcelPopup_input.mocn")) {

			    CommonFunctions.debugMsg("Opening WhatIf Excel Popup");

			    UIUtils.forwardRequest(request, response, "/pages/MOCHazopExcelPopup.jsp");
			}
		 else if (action.equals("Questionaire_getCol.mocn")) {
			{
				/*
				 * CommonFilter commonFilter =
				 * populateCommonFilter(request,"QuestionaireCommonFilter",true);
				 * 
				 * List<String[]> visualGridList = mocService.getQuestionaire(commonFilter);
				 * PrintWriter out = response.getWriter(); // JSONObject jsonObject =
				 * getTableModel(visualGridList); JqGridTableModel jqGridTableModel = new
				 * JqGridTableModel(); GridColModel gridColModel = new GridColModel();
				 * jqGridTableModel.setRowNumbers(true);
				 * //jqGridTableModel.setEnableFilter(true);
				 * jqGridTableModel.setTableButton(true);
				 * //jqGridTableModel.setEnableFilter(true); gridColModel.setHeaderNum(1);
				 * String[] colHeader = visualGridList.get(2); String[] colHeaderCond =
				 * visualGridList.get(1); List<String[]> headers = new ArrayList<String[]>();
				 * headers.add(colHeader); JSONObject colModel =new JSONObject(); colModel =
				 * UIUtils.getTableModel(headers, colHeaderCond,jqGridTableModel, gridColModel);
				 * colModel.set("tableHeight", "50%%"); colModel.set("tableWidth", "110%%");
				 * httpSession.removeAttribute("VisualColmodel");
				 * httpSession.setAttribute("VisualColmodel", colModel); out.println(colModel);
				 */
				try {
					PrintWriter out = response.getWriter();

					out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "Questionaire"));
					CommonMessage
							.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "Questionaire"));

				} catch (Exception e) {
					CommonFunctions.debugMsg("RiskAssesment_getCol.risk Exception" + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		else if (action.equals("Questionaire_getData.mocn")) {
			/*
			 * try {
			 * 
			 * UIUtils.displayRequestParamsValue(request);
			 * 
			 * CommonFilter commonFilter =
			 * populateCommonFilter(request,"Questionaire",false);
			 * 
			 * List<String[]> visualGridList = mocService.getQuestionaire(commonFilter);
			 * PrintWriter out = response.getWriter(); JSONObject visualQueryData =
			 * UIUtils.convertToJqGridTableObject(visualGridList,request,3,0);
			 * out.println(visualQueryData); }catch(Exception e) {
			 * CommonMessage.debugMsg(e.getMessage()); }
			 */
			try {
				CommonFilter commonFilter = populateCommonFilter(request, "Questionaire CommonFilter", false);
				CommonMessage.debugMsg("get data");

				String MocKeyid = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("Data is enter or not" + MocKeyid);
				commonFilter.setKey(MocKeyid);

				List<String[]> MachineGrid = mocService.getQuestionaire(commonFilter);

				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("Questionaire_save.mocn")) {
			String MocKeyid = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("MOC Keyid::::" + MocKeyid);
			saveQuestionsdata(request, response, MocKeyid);
		}
		
		// Vignesh 25may 2026 //
		else if (action.equals("pillarChampionEmployeeCombo.mocn")) {

		    String sugflid = request.getParameter("sugflid");

		    List<String[]> empList = mocService.getPillarChampionEmployeeList(sugflid);

		    java.util.List<JSONObject> jsonList = new java.util.ArrayList<JSONObject>();

		    if (empList != null) {
		        for (String[] row : empList) {

		            JSONObject obj = new JSONObject();

		            obj.put("id", row[0]);
		            obj.put("text", row[1]);

		            jsonList.add(obj);
		        }
		    }

		    JSONArray arr = JSONArray.fromObject(jsonList);

		    PrintWriter out = response.getWriter();
		    out.print(arr.toString());
		}

		else if (action.equals("PSSRRecommentationsCount.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String pssrCount = mocService.getPssrCount(Mockeyid);
			String MocStatus = mocService.getMOCStatusCount(Mockeyid);
			CommonMessage.debugMsg("the pssrCount" + pssrCount);
			JSONObject json = new JSONObject();
			json.put("pssrCount", pssrCount);
			json.put("MocStatus", MocStatus);
			out.println(json);

		}

		else if (action.equals("PSITeamMailTrigger.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");

			String TotalCount = mocService.getPssrReccommendationCount(Mockeyid);
			String CompletedStatus = mocService.getPssrReccommendationCompleted(Mockeyid);
			CommonMessage.debugMsg("TotalCount=" + TotalCount + "CompletedStatus=" + CompletedStatus);

			if (TotalCount.equals(CompletedStatus)) {
				try {
					CommonMessage.debugMsg("Inside Final  Approval Mail Trigger");
					String SuggestionNo = request.getParameter("SuggestionId");
					String Suggestion = request.getParameter("Suggestion");
					String CR = request.getParameter("CR");
					CommonMessage.debugMsg("Suggestion=" + Suggestion + "&SuggestionId=" + SuggestionNo + "&CR=" + CR);
					String MOCtitle = request.getParameter("Title");
					List<String[]> empMailIds = mocService.getPSITeamMailIds(Mockeyid);
					CommonMessage.debugMsg("emailId" + Mockeyid);
					String mailIds = buildToMailIds(empMailIds);
					if (mailIds.isEmpty()) {
						JSONObject succssMsg = new JSONObject();
						glbmsg = "No Valid MailId Found ";
						return;
					}
					SendMailToPSI(request, response, mailIds, SuggestionNo, Suggestion, Mockeyid, MOCtitle, CR);
				} catch (Exception e) {
					CommonMessage.debugMsg("Mail" + e);
				}
			}

			/*
			 * JSONObject json=new JSONObject(); json.put("pssrCount", pssrCount);
			 * json.put("MocStatus", MocStatus); out.println(json);
			 */

		}

		else if (action.equals("WhatIfHazopRecommentationsCount.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String WHCount = mocService.getWhatifHazopCount(Mockeyid);

			CommonMessage.debugMsg("the WHCount" + WHCount);
			JSONObject json = new JSONObject();
			json.put("WHCount", WHCount);
			out.println(json);

		} else if (action.equals("InitialApprovalCount.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String TotalApproval = mocService.getApprovalCount(Mockeyid);
			String CompletedApproval = mocService.getCompletedApprovalCount(Mockeyid);
			String MaxGroupNo = mocService.getMaxGroupCount(Mockeyid);
			String NextGroupNo = mocService.getNextGroupCount(Mockeyid);
			CommonMessage.debugMsg("the TNextGroupNo Count::::" + NextGroupNo);
			CommonMessage.debugMsg("the TotalApproval Count::::" + TotalApproval);
			CommonMessage.debugMsg("the  Completed Approval Count::::" + CompletedApproval);
			JSONObject json = new JSONObject();
			json.put("MaxGroupNo", MaxGroupNo);
			json.put("NextGroupNo", NextGroupNo);
			json.put("TotalApproval", TotalApproval);
			json.put("CompletedApproval", CompletedApproval);
			out.println(json);

		} else if (action.equals("HazopApprovalCount.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String HazopTotalApproval = mocService.getHazopApprovalCount(Mockeyid);
			String HazopCompletedApproval = mocService.getHazopCompletedApprovalCount(Mockeyid);
			String HazopMaxGroupNo = mocService.getHazopMaxGroupCount(Mockeyid);
			String HazopNextGroupNo = mocService.getHazopNextGroupCount(Mockeyid);
			CommonMessage.debugMsg("theHazop HazopMaxGroupNo Count::::" + HazopMaxGroupNo);
			CommonMessage.debugMsg("theHazop TNextGroupNo Count::::" + HazopNextGroupNo);
			CommonMessage.debugMsg("theHazop TotalApproval Count::::" + HazopTotalApproval);
			CommonMessage.debugMsg("theHazop  Completed Approval Count::::" + HazopCompletedApproval);
			JSONObject json = new JSONObject();
			json.put("HazopMaxGroupNo", HazopMaxGroupNo);
			json.put("HazopNextGroupNo", HazopNextGroupNo);
			json.put("HazopTotalApproval", HazopTotalApproval);
			json.put("HazopCompletedApproval", HazopCompletedApproval);
			out.println(json);

		}

		else if (action.equals("FinalApprovalCount.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String FinalTotalApproval = mocService.getFinalApprovalCount(Mockeyid);
			String FinalCompletedApproval = mocService.getFinalCompletedApprovalCount(Mockeyid);
			String FinalMaxGroupNo = mocService.getFinalMaxGroupCount(Mockeyid);
			String FinalNextGroupNo = mocService.getFinalNextGroupCount(Mockeyid);
			String FinalMaxGroupStatus = mocService.getFinalMaxGroupCountStatus(Mockeyid);
			CommonMessage.debugMsg("the FinalTNextGroupNo Count::::" + FinalNextGroupNo);
			CommonMessage.debugMsg("the FinalTot alApproval Count::::" + FinalTotalApproval);
			CommonMessage.debugMsg("the  FinalCompleted Approval Count::::" + FinalCompletedApproval);
			CommonMessage.debugMsg("FinalMaxGroupStatus:::::::::::::" + FinalMaxGroupStatus);
			JSONObject json = new JSONObject();
			json.put("FinalMaxGroupStatus", FinalMaxGroupStatus);
			json.put("FinalMaxGroupNo", FinalMaxGroupNo);
			json.put("FinalNextGroupNo", FinalNextGroupNo);
			json.put("FinalTotalApproval", FinalTotalApproval);
			json.put("FinalCompletedApproval", FinalCompletedApproval);
			out.println(json);

		} else if (action.equals("MOCClosureCheck.mocn")) {
			PrintWriter out = response.getWriter();
			String Mockeyid = request.getParameter("MocKeyid");
			String ClosureCount = mocService.getClosureCount(Mockeyid);

			CommonMessage.debugMsg("FinalMaxGroupStatus:::::::::::::" + ClosureCount);
			JSONObject json = new JSONObject();

			json.put("ClosureCount", ClosureCount);
			out.println(json);

		} else if (action.equals("MocCreationGrid_input.mocn")) {
			CommonMessage.debugMsg("Inside the Input method");

			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String mode = "create";
			request.setAttribute("mode", mode);

			RequestDispatcher rd = request.getRequestDispatcher("/pages/MOCGridnew.jsp");
			rd.forward(request, response);
		} else if (action.equals("MocCreationGrid_getCol.mocn")) {
			CommonMessage.debugMsg("in  GetCol" + action);
			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "MocCommonFilter", true);

			List<String[]> saralViewData = mocService.getMocRelatedData(commonFilter);
			CommonMessage.debugMsg("in  GetCol" + action);
			JqGridTableModel jqGridTableModel = new JqGridTableModel();
			GridColModel gridColModel = new GridColModel();
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setEnableFilter(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setEnableFilter(true);
			gridColModel.setHeaderNum(1);
			String[] colHeader = saralViewData.get(2);
			String[] colHeaderCond = saralViewData.get(1);
			List<String[]> headers = new ArrayList<String[]>();
			headers.add(colHeader);
			JSONObject colModel = new JSONObject();
			colModel = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
			colModel.set("tableHeight", "80%%");
			colModel.set("tableWidth", "108%%");
			httpSession.removeAttribute("MOCColModel");
			httpSession.setAttribute("MOCColModel", colModel);
			httpSession.removeAttribute("MOCCommonFilter");
			httpSession.setAttribute("MOCCommonFilter", commonFilter);
			out.println(colModel);
		}

		else if (action.equals("MocCreationGrid_getData.mocn")) {

			PrintWriter out = response.getWriter();
			CommonFilter commonFilter = populateCommonFilter(request, "MocCommonFilter", false);
			CommonMessage.debugMsg("in  Data" + action);
			List<String[]> saralViewData = mocService.getMocRelatedData(commonFilter);
			JSONObject dataJson = UIUtils.convertToJqGridTableObject(saralViewData, request, 3, 0,
					commonFilter.getTotalRecordCnt() + 3);
			out.print(dataJson);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute("MocCommonFilter");
			httpSession.setAttribute("MocCommonFilter", commonFilter);
		} else if (action.equals("MocCreationGrid_getExcel.mocn")) {
			CommonFilter commonFilter = new CommonFilter();
			commonFilter = populateCommonFilter(request, "MocCommonFilter", false);
			String tmpFromRow = commonFilter.getFromRow();
			commonFilter.setFromRow(null);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("MOCColModel");
			colmodel.put("title", "MOC");
			String format = ExcelUtils.getFormat(request);
			Workbook wb = mocService.getMOCModificationExcel(colmodel, format, commonFilter);
			commonFilter.setFromRow(tmpFromRow);
			ExcelUtils.writeToResponse(response, wb, "MOC", format);

		}

		else if (action.equals("WhatIFEntry_getCol.mocn")) {

			try {
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk.............");
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "WhatIf"));
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "WhatIf"));

			} catch (Exception e) {
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk Exception" + e.getMessage());
				e.printStackTrace();
			}
		} else if (action.equals("getRiskLevel.mocn")) {
			try {
				String riskVal = request.getParameter("riskval");
				CommonMessage.debugMsg("The Risk val:::" + riskVal);
				String rowId = request.getParameter("rowid");
				String sev = request.getParameter("sev");
				String prob = request.getParameter("prob");

				// CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel = "";
				int iRiskVal = 0;
				String sevVal = "0";
				String probVal = "0";
				// if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))) {
					probVal = mocService.getProbablityVal(prob);
					CommonMessage.debugMsg("The probVal:::" + probVal);
					sevVal = mocService.getSeviorityVal(sev);
					CommonFunctions.debugMsg("sevVal:" + sevVal);
					CommonMessage.debugMsg("The sevVal val:::" + sevVal);
					iRiskVal = Integer.parseInt(probVal) * Integer.parseInt(sevVal);
					riskVal = String.valueOf(iRiskVal);
					// CommonFunctions.debugMsg("riskVal:"+riskVal);
					CommonMessage.debugMsg("Risk value:::" + riskVal);
					JSONObject successData = new JSONObject();
					successData.put("riskVal", riskVal);

					successData.put("rowId", rowId);
					// CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out = response.getWriter();
					out.print(successData.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("getRiskLevel1.mocn")) {
			try {
				String riskVal = request.getParameter("riskval");
				CommonMessage.debugMsg("The Risk val:::" + riskVal);
				String rowId = request.getParameter("rowid");
				String sev = request.getParameter("sev");
				String prob = request.getParameter("prob");

				// CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel = "";
				int iRiskVal = 0;
				String sevVal = "0";
				String probVal = "0";
				// if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))) {
					probVal = mocService.getProbablityVal(prob);
					CommonMessage.debugMsg("The probVal:::" + probVal);
					sevVal = mocService.getSeviorityVal(sev);
					CommonFunctions.debugMsg("sevVal:" + sevVal);
					CommonMessage.debugMsg("The sevVal val:::" + sevVal);
					iRiskVal = Integer.parseInt(probVal) * Integer.parseInt(sevVal);
					riskVal = String.valueOf(iRiskVal);
					// CommonFunctions.debugMsg("riskVal:"+riskVal);
					CommonMessage.debugMsg("Risk value:::" + riskVal);
					JSONObject successData = new JSONObject();
					successData.put("riskVal", riskVal);

					successData.put("rowId", rowId);
					// CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out = response.getWriter();
					out.print(successData.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("getRiskLevel2.mocn")) {
			try {
				String riskVal = request.getParameter("riskval");
				CommonMessage.debugMsg("The Risk val:::" + riskVal);
				String rowId = request.getParameter("rowid");
				String sev = request.getParameter("sev");
				String prob = request.getParameter("prob");

				// CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel = "";
				int iRiskVal = 0;
				String sevVal = "0";
				String probVal = "0";
				// if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))) {
					probVal = mocService.getProbablityVal(prob);
					CommonMessage.debugMsg("The probVal:::" + probVal);
					sevVal = mocService.getSeviorityVal(sev);
					CommonFunctions.debugMsg("sevVal:" + sevVal);
					CommonMessage.debugMsg("The sevVal val:::" + sevVal);
					iRiskVal = Integer.parseInt(probVal) * Integer.parseInt(sevVal);
					riskVal = String.valueOf(iRiskVal);
					// CommonFunctions.debugMsg("riskVal:"+riskVal);
					CommonMessage.debugMsg("Risk value:::" + riskVal);
					JSONObject successData = new JSONObject();
					successData.put("riskVal", riskVal);

					successData.put("rowId", rowId);
					// CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out = response.getWriter();
					out.print(successData.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("getRiskLevel3.mocn")) {
			try {
				String riskVal = request.getParameter("riskval");
				CommonMessage.debugMsg("The Risk val:::" + riskVal);
				String rowId = request.getParameter("rowid");
				String sev = request.getParameter("sev");
				String prob = request.getParameter("prob");

				// CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel = "";
				int iRiskVal = 0;
				String sevVal = "0";
				String probVal = "0";
				// if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))) {
					probVal = mocService.getProbablityVal(prob);
					CommonMessage.debugMsg("The probVal:::" + probVal);
					sevVal = mocService.getSeviorityVal(sev);
					CommonFunctions.debugMsg("sevVal:" + sevVal);
					CommonMessage.debugMsg("The sevVal val:::" + sevVal);
					iRiskVal = Integer.parseInt(probVal) * Integer.parseInt(sevVal);
					riskVal = String.valueOf(iRiskVal);
					// CommonFunctions.debugMsg("riskVal:"+riskVal);
					CommonMessage.debugMsg("Risk value:::" + riskVal);
					JSONObject successData = new JSONObject();
					successData.put("riskVal", riskVal);

					successData.put("rowId", rowId);
					// CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out = response.getWriter();
					out.print(successData.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("HazopEntry_getData.mocn")) {
			try {
				CommonFilter commonFilter = new CommonFilter();
				String keyid = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("keyid" + keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> HazopList = mocService.getHazopList(commonFilter);
				JSONObject HazopData = UIUtils.convertToJqGridTableObject(HazopList, request, 2, 0);
				out.println(HazopData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("WhatIFEntry_getData.mocn")) {
			try {
				CommonFilter commonFilter = new CommonFilter();
				String keyid = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("keyid" + keyid);
				commonFilter.setKey(keyid);
				PrintWriter out = response.getWriter();
				List<String[]> WhatifList = mocService.getWhatIfList(commonFilter);
				JSONObject WhatifData = UIUtils.convertToJqGridTableObject(WhatifList, request, 2, 0);
				out.println(WhatifData);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("HazopEntry_getCol.mocn")) {

			try {
				PrintWriter out = response.getWriter();
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk.............");
				out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "Hazop"));
				CommonMessage.debugMsg(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "Hazop"));

			} catch (Exception e) {
				CommonFunctions.debugMsg("RiskAssesment_getCol.risk Exception" + e.getMessage());
				e.printStackTrace();
			}
		} else if (action.equals("getRiskLevel.mocn")) {
			try {
				String riskVal = request.getParameter("riskval");
				String rowId = request.getParameter("rowid");
				String sev = request.getParameter("sev");
				String prob = request.getParameter("prob");

				// CommonFunctions.debugMsg("riskVal:"+riskVal);
				String riskLevel = "";
				int iRiskVal = 0;
				String sevVal = "0";
				String probVal = "0";
				// if(UIUtils.isValidKeyId(riskVal)){
				if ((UIUtils.isValidKeyId(prob)) && (UIUtils.isValidKeyId(sev))) {
					probVal = mocService.getProbablityVal(prob);
					// CommonFunctions.debugMsg("probVal:"+probVal);
					sevVal = mocService.getSeviorityVal(sev);
					// CommonFunctions.debugMsg("sevVal:"+sevVal);
					iRiskVal = Integer.parseInt(probVal) * Integer.parseInt(sevVal);
					riskVal = String.valueOf(iRiskVal);
					// CommonFunctions.debugMsg("riskVal:"+riskVal);
					riskLevel = mocService.getRiskLevel(riskVal);
					request.setAttribute("riskLevel", riskLevel);
					JSONObject successData = new JSONObject();
					successData.put("riskVal", riskVal);
					successData.put("riskLevel", riskLevel);
					successData.put("rowId", rowId);
					// CommonFunctions.debugMsg(returnData.toString());
					PrintWriter out = response.getWriter();
					out.print(successData.toString());
				}
				// }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (action.equals("ChangeRequest_delete.mocn")) {
			try {
				
				DeleteMoc(request,response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (action.equals("ChangeRequest_input.mocn")) {
			String mode = request.getParameter("mode");
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String mocDate = "";
			String SuggestionId = request.getParameter("SuggestionId");
			CommonMessage.debugMsg("SuggestionSuggestionId ID iss::;" + SuggestionId);
			String Suggestion = request.getParameter("Suggestion");
			String Sugflid = request.getParameter("SuggestionFlid");
			CommonMessage.debugMsg("SuggestionFLid" + Sugflid);
			String MocKeyid = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("MocKeyid:::" + MocKeyid);
			String userid = user.getUsrm_ccno();
			CommonMessage.debugMsg("User is::" + userid);
			String Responsibility = request.getParameter("Responsibility");
			CommonMessage.debugMsg("Responsibility" + Responsibility);

			if ((!UIUtils.isValidKeyId(MocKeyid)) && (UIUtils.isValidKeyId(SuggestionId))) 
			{
				MocKeyid = mocService.getMocBySuggestionId(SuggestionId);
			}
			
			boolean isApprovalUser = false;

			if (UIUtils.isValidKeyId(MocKeyid)) {
				isApprovalUser = mocService.isMocApprovalUser(MocKeyid, userid);
			}

			String Status = request.getParameter("Status");
			CommonMessage.debugMsg("Status:" + Status);
			String WhatifKey = request.getParameter("WhatifKey");
			CommonMessage.debugMsg("User is::" + WhatifKey);
			String HazopKey = request.getParameter("HazopKey");
			CommonMessage.debugMsg("Hazop is::" + HazopKey);
			String PssrKey = request.getParameter("psrmkey");
			CommonMessage.debugMsg("Pssr is::" + PssrKey);
			String nature = request.getParameter("nature");
			CommonMessage.debugMsg("nature" + nature);
			String type = request.getParameter("type");
			CommonMessage.debugMsg("type" + type);

			// Primary approval // to be checked Vignesh 14May2026

			MocRfcmstNew mocRfcmst = new MocRfcmstNew();
			KznTlKaizenbankmst Kaizenbankmst = new KznTlKaizenbankmst();

			WhatifMst whatifMst = new WhatifMst();
			HazopMst hazopMst = new HazopMst();
			MocPssrmst mocPssrmst = new MocPssrmst();
			String JHLeader = mocService.getJHLeader(Sugflid);
			String DMTLeader = mocService.getDMTLeader(Sugflid);

			String ProcessArea = mocService.getProcess(Sugflid);
			String MechArea = mocService.getMech(Sugflid);
			String InstrumentArea = mocService.getInstrument(Sugflid);
			String CivilArea = mocService.getCivil(Sugflid);
			CommonMessage.debugMsg("CivilArea from service = " + CivilArea);
			// mano
			String PMMechChampion = mocService.getPMMechChampion(Sugflid);
			String PMEIChampion = mocService.getPMEIChampion(Sugflid);
			String PillarChampion = mocService.getPillarChampion(Sugflid);
			String SafetyManager = mocService.getSafetymanager(Sugflid);
			// mano

			String ElectricalArea = mocService.getElect(Sugflid);
			/*
			 * String EHSHead=mocService.getEHSHead(Sugflid); String
			 * EHSManager=mocService.getEHSManager(Sugflid);
			 */
			String PBUHead = mocService.getPbuHead(Sugflid);
			/*
			 * String DMTLeader=mocService.getDMTLeader(Sugflid); String
			 * ProcessArea=mocService.getProcess(Sugflid); String
			 * MechArea=mocService.getMech(Sugflid); String
			 * InstrumentArea=mocService.getInstrument(Sugflid); String
			 * CivilArea=mocService.getCivil(Sugflid);
			 * 
			 * String ElectricalArea=mocService.getElect(Sugflid); String
			 * EHSHead=mocService.getEHSHead(Sugflid); String
			 * EHSManager=mocService.getEHSManager(Sugflid); String
			 * PBUHead=mocService.getPbuHead(Sugflid);
			 */
			// if(MocKeyid!=null&&MocKeyid.length()!=0)
			if (UIUtils.isValidKeyId(MocKeyid)) {
				mocRfcmst = mocService.getGridMocData(MocKeyid);
				mocDate = mocRfcmst.getRfcmdate().substring(0, 11);
				CommonMessage.debugMsg("mocDate" + mocDate);
				CommonMessage.debugMsg("mocRfcmst" + mocRfcmst + "whatif" + whatifMst);

				nature = mocRfcmst.getRfcmnature();
				type = mocRfcmst.getRfcmtype();

				CommonMessage.debugMsg("nature from DB: " + nature);
				CommonMessage.debugMsg("type from DB: " + type);

			}

			String othersNotes = "";

			if (UIUtils.isValidKeyId(MocKeyid)) {
				othersNotes = mocService.getOthersNotes(MocKeyid);
			}

			CommonMessage.debugMsg("Fetched Others Notes ::: " + othersNotes);
			request.setAttribute("othersNotes", othersNotes);

			if (UIUtils.isValidKeyId(SuggestionId)) {
				Kaizenbankmst = mocService.getGridKaizenData(SuggestionId);

			}
			
			if ((!UIUtils.isValidKeyId(WhatifKey)) && (UIUtils.isValidKeyId(MocKeyid))) {
				WhatifKey = mocService.getWhatifMstKeyid(MocKeyid);
			}
            if ((!UIUtils.isValidKeyId(PssrKey)) && (UIUtils.isValidKeyId(MocKeyid))) {
				PssrKey = mocService.getPssrKeyid(MocKeyid);
			}
            if ((!UIUtils.isValidKeyId(HazopKey)) && (UIUtils.isValidKeyId(MocKeyid))) {
				HazopKey = mocService.getHazopMstKeyid(MocKeyid);
			}

			if (UIUtils.isValidKeyId(WhatifKey)) {
				whatifMst = mocService.getWhatIfMstData(WhatifKey);
			}
			if (UIUtils.isValidKeyId(PssrKey)) {
				mocPssrmst = mocService.getPssrMstData(PssrKey);
			}
			if (UIUtils.isValidKeyId(HazopKey)) {
				hazopMst = mocService.getHazopMstData(HazopKey);
			}
			if (mode == null || mode.trim().length() == 0) {

				if (userid != null && userid.equals(Responsibility)) {
					mode = "create";
				} else if (isApprovalUser) {
					mode = "approval";
				} else {
					mode = "view";
				}
			}

			CommonMessage.debugMsg(" JHLeader:" + JHLeader);
			// String userid=user.getUsrm_ccno();
			CommonMessage.debugMsg("Mode==" + mode);
			request.setAttribute("mode", mode);
			request.setAttribute("JHleader", JHLeader);
			request.setAttribute("DMTLeader", DMTLeader);

			request.setAttribute("ProcessArea", ProcessArea);
			request.setAttribute("MechArea", MechArea);
			request.setAttribute("InstrumentArea", InstrumentArea);
			request.setAttribute("CivilArea", CivilArea);
			request.setAttribute("ElectricalArea", ElectricalArea);
			request.setAttribute("PBUHead", PBUHead);

			// mano
			request.setAttribute("SafetyManager", SafetyManager);
			request.setAttribute("PillarChampion", PillarChampion);
			request.setAttribute("PMEIChampion", PMEIChampion);
			request.setAttribute("PMMechChampion", PMMechChampion);
			// mano
			request.setAttribute("userid", userid);
			request.setAttribute("Status", Status);
			request.setAttribute("mocRfcmst", mocRfcmst);
			request.setAttribute("mocPssrmst", mocPssrmst);
			request.setAttribute("whatifMst", whatifMst);
			request.setAttribute("hazopMst", hazopMst);
			request.setAttribute("Kaizenbankmst", Kaizenbankmst);
			request.setAttribute("SuggestionId", SuggestionId);
			request.setAttribute("Responsibility", Responsibility);
			request.setAttribute("Suggestion", Suggestion);
			request.setAttribute("Sugflid", Sugflid);
			request.setAttribute("WhatifKey", WhatifKey);
			request.setAttribute("HazopKey", HazopKey);
			request.setAttribute("nature", nature);
			request.setAttribute("type", type);
			request.setAttribute("mocDate", mocDate);

			CommonMessage.debugMsg("Befor MOC JSP");
			UIUtils.forwardRequest(request, response, "/pages/MOCnew.jsp");
		} else if (action.equals("ChangeRequest_save.mocn")) {
			SaveMOC(request, response);
		}

		else if (action.equals("ChangeRequestBasis_getCol.mocn")) {
			String unsafeact = request.getParameter("unsafeact");
			CommonMessage.debugMsg("unsafeact" + unsafeact);
			request.setAttribute("unsafeact", unsafeact);
			PrintWriter out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnewnew", "QCLogging");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			out.println(colmodel);
		}

		else if (action.equals("ChangeRequestBasis_getData.mocn")) {

			/*
			 * try { PrintWriter out = response.getWriter(); CommonFilter commonFilter =
			 * populateCommonFilter(request,"ChangeRequest CommonFilter",false);
			 * List<String[]> safety = newCRService.getCRBasis(commonFilter); JSONObject
			 * Safety = UIUtils.convertToJqGridTableObject(safety, request, 3, 0);
			 * //httpSession.removeAttribute("genTlSusamst"); out.println(Safety); } catch
			 * (Exception e) { e.printStackTrace(); }
			 */

			try {
				CommonFilter commonFilter = populateCommonFilter(request, "ChangeRequest CommonFilter", false);
				CommonMessage.debugMsg("get data");
				List<String[]> MachineGrid = mocService.getCRBasis(commonFilter);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("InitialApproval_getCol.mocn")) {

			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "InitialApproval");
			response.getWriter().print(colModel);
			response.getWriter().close();
		} else if (action.equals("InitialApproval_getData.mocn")) {
			String MocKeyid = request.getParameter("MocKeyid");

			CommonMessage.debugMsg("MocKeyid in Approval" + MocKeyid);
			List<String[]> MachineGrid = mocService.getApprovalList(MocKeyid);
			// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
			PrintWriter out = response.getWriter();
			// CommonMessage.debugMsg("get data method1");
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
			out.println(machinegrid);
		}

		else if (action.equals("HazopApproval_getCol.mocn")) {

			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "HazopApproval");
			response.getWriter().print(colModel);
			response.getWriter().close();
		} else if (action.equals("HazopApproval_getData.mocn")) {
			String MocKeyid = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("MocKeyid in Approval" + MocKeyid);
			List<String[]> MachineGrid = mocService.getHazopApprovalList(MocKeyid);
			// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
			PrintWriter out = response.getWriter();
			// CommonMessage.debugMsg("get data method1");
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
			out.println(machinegrid);
		}

		else if (action.equals("FinalApproval_getCol.mocn")) {

			String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "FinalApproval");
			response.getWriter().print(colModel);
			response.getWriter().close();
		} else if (action.equals("FinalApproval_getData.mocn")) {
			String MocKeyid = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("MocKeyid in Approval" + MocKeyid);
			List<String[]> MachineGrid = mocService.getFinalApprovalList(MocKeyid);
			// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
			PrintWriter out = response.getWriter();
			// CommonMessage.debugMsg("get data method1");
			JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
			out.println(machinegrid);
		}

		else if (action.equals("GuideWord.mocn")) {
			try {
				ComboFilter combofilter = UIUtils.fillComboFilter(request);
				List<ComboBox> projectList = mocService.getMocGuideWordComboList(combofilter);
				UIUtils.writeComboBox(response, projectList, combofilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("MOCTeamSusscess_getCol.mocn")) {

			CommonMessage.debugMsg("Success Call Back");

			String MocKeyId = request.getParameter("MocKeyid");
			PrintWriter out = response.getWriter();
			request.setAttribute("MocKeyId", MocKeyId);
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "MOCTeam");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			response.setContentType("text/html");
			out.print(colmodel);
			out.close();
		} else if (action.equals("MOCTeamSusscess_getData.mocn")) {
			// CommonMessage.debugMsg("get data method");
			String MocKeyId = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("MOC KeyID" + MocKeyId);
			CommonFilter commonFilter = populateCommonFilter(request, "MOCTeam", false);
			try {

				List<String[]> MachineGrid = mocService.getMOCTeamSuccess(commonFilter, MocKeyId);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}

		}

		/*
		 * else if (action.equals("PssrReccommendation_save.mocn")){
		 * SavePssrReccommendation(request,response); }
		 */

		else if (action.equals("MocReccommendation_save.mocn")) {
			SaveMOCReccommendation(request, response);
		} else if (action.equals("MocPSSRChkReccommendation_save.mocn")) {
			SaveMOCPSSRCHReccommendation(request, response);
		}

		else if (action.equals("InitialApproval_save.mocn")) {

			SaveInitialApproval(request, response);

		}

		else if (action.equals("HazopApproval_save.mocn")) {

			SaveHazopApproval(request, response);

		}

		else if (action.equals("FinalApproval_save.mocn")) {

			SaveFinalApproval(request, response);

		} else if (action.equals("Whatif_remove.mocn")) {
			DeleteWhatifRow(request, response);
		} else if (action.equals("Hazop_remove.mocn")) {
			DeleteHazopRow(request, response);
		}
		// -- vignesh 07sep2026
	 else if (action.equals("PSSRReccommendation_remove.mocn")) {
	    DeletePssrReccommendationRow(request, response);
	}

		else if (action.equals("MOCTeam_getCol.mocn")) {
			CommonMessage.debugMsg("achuievenen");

			PrintWriter out = response.getWriter();

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "MOCTeam");
			JSONObject colmodel = JSONObject.fromString(tableModel);
			response.setContentType("text/html");
			out.print(colmodel);
			out.close();
		} else if (action.equals("MOCTeam_getData.mocn")) {
			// CommonMessage.debugMsg("get data method");
			CommonFilter commonFilter = populateCommonFilter(request, "MOCTeam", false);
			try {

				List<String[]> MachineGrid = mocService.getMOCTeam(commonFilter);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}

		} else if (action.equals("managementofchangetype.mocn")) {
			/*
			 * try{ ComboFilter combofilter=UIUtils.fillComboFilter(request);
			 * List<ComboBox>projectList=mocService.getMocTypeComboList(combofilter);
			 * UIUtils.writeComboBox(response,projectList,combofilter); } catch(Exception
			 * e){ e.printStackTrace(); }
			 */
			CommonMessage.debugMsg("In type get Col");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "MocType"));

		} else if (action.equals("questionaireYesNo.mocn")) {

			CommonMessage.debugMsg("In type get Col");
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "YesNo"));

		}

		else if (action.equals("managementofchangenature.mocn")) {
			CommonMessage.debugMsg("In type get Col" + action);
			PrintWriter out = response.getWriter();
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "MocNature"));
		}

		else if (action.equals("managementofchangetypenew.mocn")) {
			try {
				CommonMessage.debugMsg("In type combobox Col" + action);
				ComboFilter combofilter = UIUtils.fillComboFilter(request);
				List<ComboBox> projectList = mocService.getMocTypeComboList(combofilter);
				UIUtils.writeComboBox(response, projectList, combofilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals("managementofchangenaturenew.mocn")) {
			try {
				CommonMessage.debugMsg("In type combobox Col" + action);
				ComboFilter combofilter = UIUtils.fillComboFilter(request);
				List<ComboBox> projectList = mocService.getMocTypeComboList(combofilter);
				UIUtils.writeComboBox(response, projectList, combofilter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (action.equals("BasisofChange_getCol.mocn")) {
			CommonMessage.debugMsg("achuievenen");

			String masterKeyid = request.getParameter("unsafeact");
			CommonMessage.debugMsg("masterKeyid" + masterKeyid);
			String detailsid = request.getParameter("unsafeact");
			CommonMessage.debugMsg("detailsid" + detailsid + "masterKeyid" + masterKeyid);
			request.setAttribute("unsafeact", masterKeyid);
			request.setAttribute("detailsid", detailsid);
			PrintWriter out = response.getWriter();

			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "BasisofChange");
			JSONObject colmodel = JSONObject.fromString(tableModel);

			out.println(colmodel);
		} else if (action.equals("BasisofChange_getData.mocn")) {
			// CommonMessage.debugMsg("get data method");
			String MocKeyid = request.getParameter("MocKeyid");

			CommonMessage.debugMsg("MocKeyid" + MocKeyid);
			request.setAttribute("MocKeyid", MocKeyid);
			try {

				List<String[]> MachineGrid = mocService.getBasisofChange(MocKeyid);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}

		} else if (action.equals("HazopRecommendations_getCol.mocn")) {
			CommonMessage.debugMsg("IN Recc");

			PrintWriter out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "HazopReccommend");
			JSONObject colmodel = JSONObject.fromString(tableModel);

			out.println(colmodel);
		} else if (action.equals("HazopRecommendations_getData.mocn")) {

			CommonFilter commonFilter = populateCommonFilter(request, "HazopReccommendCommonFilter", true);
			String MocKeyId = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("IN Recc Getdata" + MocKeyId);
			commonFilter.setKey(MocKeyId);
			try {

				List<String[]> MachineGrid = mocService.getHazopReccommendation(commonFilter);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}
		} else if (action.equals("PSSRRecommendations_getCol.mocn")) {
			CommonMessage.debugMsg("IN Recc");

			PrintWriter out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "PSSRREccommend");
			JSONObject colmodel = JSONObject.fromString(tableModel);

			out.println(colmodel);
		}

		else if (action.equals("PSSRRecommendations_getData.mocn")) {

			CommonFilter commonFilter = populateCommonFilter(request, "MOCReccommendCommonFilter", true);
			String MocKeyId = request.getParameter("MocKeyid");
			CommonMessage.debugMsg("IN Recc Getdata" + MocKeyId);
			commonFilter.setKey(MocKeyId);
			try {

				List<String[]> MachineGrid = mocService.getPssrReccommendation(commonFilter);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}
		}

		else if (action.equals("MocClosure_getCol.mocn")) {
			CommonMessage.debugMsg("IN MOC CLosure");
			/*
			 * PrintWriter out = response.getWriter(); String tableModel =
			 * UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew","MOCClosure");
			 * CommonMessage.debugMsg("IN MOC CLosure"); JSONObject colmodel =
			 * JSONObject.fromString(tableModel);
			 */
			PrintWriter out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "MOCClosure");
			JSONObject colmodel = JSONObject.fromString(tableModel);

			out.println(colmodel);
		}

		else if (action.equals("MocClosure_getData.mocn")) {

			CommonFilter commonFilter = populateCommonFilter(request, "MOCClosureCommonFilter", true);
			try {
				String MocKeyid = request.getParameter("MocKeyid");
				commonFilter.setKey(MocKeyid);
				List<String[]> MachineGrid = mocService.getMOCClosure(commonFilter);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}

		} else if (action.equals("PssrChecklist_input.mocn")) {
			UIUtils.forwardRequest(request, response, "pages/Pssrchecklist.jsp");
		} else if (action.equals("PssrChecklist_getCol.mocn")) {
			PrintWriter out = response.getWriter();
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources..RFCnew", "PssrCheckList");
			JSONObject colmodel = JSONObject.fromString(tableModel);

			out.println(colmodel);
		} else if (action.equals("PssrChecklist_getData.mocn")) {

			String MocKeyid = request.getParameter("MocKeyid");
			request.setAttribute("unsafeact", MocKeyid);
			try {

				List<String[]> MachineGrid = mocService.getPSSR(MocKeyid);
				// CommonMessage.debugMsg("Data is enter or not" + MachineGrid.get(0));
				PrintWriter out = response.getWriter();
				// CommonMessage.debugMsg("get data method1");
				JSONObject machinegrid = UIUtils.convertToJqGridTableObject(MachineGrid, request, 0, 0);
				out.println(machinegrid);

			} catch (Exception e) {
				// CommonMessage.debugMsg(e.getMessage());
			}

		} else if (action.equals("functionalLoc.mocn")) {

			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			// functLocFieldNameBean.setFactory("cmbbdmsFactoryid");
			functLocFieldNameBean.setPbu("cmbsusmpbuid");
			functLocFieldNameBean.setSection("cmbsusmSectionid");

			functLocFieldNameBean.setCell("cmbsusmCellid");
			functLocFieldNameBean.setMachine("cmbsusmMachineid");
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);

			FormModes formModes = (FormModes) httpSession.getAttribute("formMode");

			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		} else if (action.equals("MOCExcelView_Excelview.mocn")) {
			try {
				String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);
				CommonMessage.debugMsg("path   in servelets" + path);
				String MocKeyid = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("MocKeyid" + MocKeyid);
				String date = CommonFunctions.getDate();
				CommonMessage.debugMsg("Date:" + date);
				String flid = request.getParameter("flid");
				String DMTId = request.getParameter("DMTId");
				CommonMessage.debugMsg("DMTId" + DMTId);
				String JHId = request.getParameter("JHId");
				CommonMessage.debugMsg("JHId" + JHId);
				String CurrentDate = date.replace("-", "");
				String NOC = request.getParameter("Nature");
				String Type = request.getParameter("Type");
				String Detail = request.getParameter("Detail");
				CommonMessage.debugMsg("Detail  " + Detail);
				String PCHange = request.getParameter("PCHange");
				CommonMessage.debugMsg("PCHange" + PCHange);
				String Title = request.getParameter("Title");
				String Initiator = request.getParameter("Initiator");
				String MOCDate = request.getParameter("MOCDate");
				CommonMessage.debugMsg("MOCDate" + MOCDate);
				String WhatifFacility = request.getParameter("Facility");
				String WhatifTeam = request.getParameter("Team");
				String WhatifDate = request.getParameter("WhatifDate");
				String Suggestion = request.getParameter("Suggestion");
				String SuggestionId = request.getParameter("SuggestionId");
				String HazopFacility = request.getParameter("HazopFacility");
				String HazopTeam = request.getParameter("HazopTeam");
				String HazopNode = request.getParameter("HazopNode");
				String HazopDesign = request.getParameter("HazopDesign");
				String HazopDate = request.getParameter("SuggestionId");
				String pidNo = request.getParameter("pidNo");
				String PssrFacility = request.getParameter("PssrFacility");
				String MOCDetails = request.getParameter("MOCDetails");

				CommonMessage.debugMsg(MOCDetails + "  MOCDetails " + Detail);
				String DMT = mocService.getDMTName(DMTId);
				String JH = mocService.getJHName(JHId);

				Workbook wb = mocService.NewDashboardExcelView(format, path, MocKeyid, flid, DMT, JH, CurrentDate, NOC,
						Type, Detail, PCHange, Title, Initiator, MOCDate, WhatifFacility, WhatifTeam, WhatifDate,
						Suggestion, SuggestionId, HazopFacility, HazopTeam, HazopNode, HazopDesign, HazopDate, pidNo,
						PssrFacility, MOCDetails);
				format = ".xlsx";
				ExcelUtils.writeToResponse(response, wb, JH + "_" + MocKeyid, format);

			} catch (Exception e) {

			}
		} else if (action.equals("MOCWhatDwonloadExcel_downExcel.mocn")) {

			java.io.FileInputStream fis = null;

			try {
				String filePath = getServletContext().getRealPath("/WEB-INF/exceltemplates/WhatIf_Excel_Format.xlsx");

				System.out.println("Excel Template Path :: " + filePath);

				File excelFile = new File(filePath);

				if (!excelFile.exists()) {
					System.out.println("FILE NOT FOUND :: " + filePath);
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "Excel template file not found.");
					return;
				}

				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setContentLength((int) excelFile.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"WhatIf_Excel_Format.xlsx\"");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");

				fis = new java.io.FileInputStream(excelFile);
				ServletOutputStream out = response.getOutputStream();

				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.flush();
				System.out.println("Excel file downloaded successfully.");

			} catch (Exception e) {
				System.out.println("Error downloading Excel :: " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		//Vignesh = WHatifExcel - 22May 2026
				else if(action.equals("whatif_excel_grid.mocn")){
				    loadWhatIfExcelToGrid(request, response);
				}

				
				else if(action.equals("hazop_excel_grid.mocn")){
					loadHazopExcelToGrid(request, response);
				}

		else if (action.equals("MOChazofDwonloadExcel_downExcel.mocn")) {

			java.io.FileInputStream fis = null;

			try {
				String filePath = getServletContext().getRealPath("/WEB-INF/exceltemplates/HAZOP_Excel_Format.xlsx");

				System.out.println("Hazop Excel Template Path :: " + filePath);

				File excelFile = new File(filePath);

				if (!excelFile.exists()) {
					System.out.println("FILE NOT FOUND :: " + filePath);
					response.sendError(HttpServletResponse.SC_NOT_FOUND, "Hazop Excel template file not found.");
					return;
				}

				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
				response.setContentLength((int) excelFile.length());
				response.setHeader("Content-Disposition", "attachment; filename=\"Hazof_Excel_Format.xlsx\"");
				response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "0");

				fis = new java.io.FileInputStream(excelFile);
				ServletOutputStream out = response.getOutputStream();

				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = fis.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				out.flush();
				System.out.println("Hazop Excel file downloaded successfully.");

			} catch (Exception e) {
				System.out.println("Error downloading Hazop Excel :: " + e.getMessage());
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		else if (action.equals("getInitialApprovedPrimaryRoles.mocn")) {

		    PrintWriter out = response.getWriter();

		    try {
		        String mocKeyid = request.getParameter("MocKeyid");

		        CommonMessage.debugMsg("getInitialApprovedPrimaryRoles MocKeyid ::: " + mocKeyid);

		        List<String[]> rows = mocService.getInitialApprovedPrimaryRoleList(mocKeyid);

		        java.util.List<JSONObject> jsonList = new java.util.ArrayList<JSONObject>();

		        if (rows != null) {
		            for (String[] row : rows) {

		                JSONObject obj = new JSONObject();

		                obj.put("roleId", row.length > 0 ? row[0] : "");
		                obj.put("empId",  row.length > 1 ? row[1] : "");
		                obj.put("rcmId",  row.length > 2 ? row[2] : "");

		                jsonList.add(obj);
		            }
		        }

		        JSONObject result = new JSONObject();
		        result.put("success", true);
		        result.put("rows", JSONArray.fromObject(jsonList));

		        out.print(result.toString());

		    } catch (Exception e) {
		        e.printStackTrace();

		        JSONObject err = new JSONObject();
		        err.put("success", false);
		        err.put("msg", "Unable to fetch approved primary approval roles: " + e.getMessage());

		        out.print(err.toString());
		    }
		}
		
		else if (action.equals("getMocTeamWorkflowRoleLock.mocn")) {

		    PrintWriter out = response.getWriter();

		    try {
		        String mocKeyid = request.getParameter("MocKeyid");

		        CommonMessage.debugMsg("getMocTeamWorkflowRoleLock MocKeyid ::: " + mocKeyid);

		        List<String[]> rows = mocService.getMocTeamWorkflowRoleLock(mocKeyid);

		        String isLocked = "N";
		        java.util.List<JSONObject> roleList = new java.util.ArrayList<JSONObject>();

		        if (rows != null) {
		            for (String[] row : rows) {

		                if (row != null && row.length > 0) {
		                    isLocked = row[0];
		                }

		                String roleId = "";
		                String rcmId = "";

		                if (row != null && row.length > 1) {
		                    roleId = row[1];
		                }

		                if (row != null && row.length > 2) {
		                    rcmId = row[2];
		                }

		                if (roleId != null && roleId.trim().length() > 0) {
		                    JSONObject obj = new JSONObject();
		                    obj.put("roleId", roleId);
		                    obj.put("rcmId", rcmId);
		                    roleList.add(obj);
		                }
		            }
		        }

		        JSONObject result = new JSONObject();
		        result.put("success", true);
		        result.put("isLocked", isLocked);
		        result.put("roles", JSONArray.fromObject(roleList));

		        out.print(result.toString());

		    } catch (Exception e) {
		        e.printStackTrace();

		        JSONObject err = new JSONObject();
		        err.put("success", false);
		        err.put("msg", "Unable to check MOC Team workflow lock: " + e.getMessage());

		        out.print(err.toString());
		    }
		}
	}

	private void SendMailToPSI(HttpServletRequest request, HttpServletResponse response, String mailIds,
			String suggestionNo, String suggestion, String mockeyid, String mOCtitle, String cR) {
		CommonMessage.debugMsg("---------Sending Mail To PSI for MOC Information----------");
		CommonMessage.debugMsg("suggestion:" + suggestion);
		CommonMessage.debugMsg("emailId :" + mailIds);
		CommonMessage.debugMsg("MocKeyid :" + mockeyid);
		CommonMessage.debugMsg("MOCtitle :" + mOCtitle + "cR=" + cR);
		try {
			// CommonMessage.debugMsg("E-Mail Id :" +UIUtils.isValidEmail(emailId) + " " +
			// emailId);
			if (UIUtils.isValidEmail(mailIds)) {
				CommonMessage.debugMsg("Inside Mail Body");
				String disclaimerNote = "--";
				StringBuilder subject = new StringBuilder("");
				subject.append(" MOC will be  Implemented with necessary Approvals ");
				StringBuilder totalContent = new StringBuilder();
				totalContent.append("\r\n");
				totalContent.append("Suggestion No: " + suggestionNo).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("Suggestion : " + suggestion).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("MOC No : " + mockeyid).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("MOC Title : " + mOCtitle).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("Proposed Change & Description : " + cR).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append(disclaimerNote);
				totalContent.append("\r\n");
				// totalContent.append("Regards,");
				totalContent.append("\r\n");
				totalContent.append(
						"This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");
				CommonMessage.debugMsg(" Before sendLotusNotesMail to JH leader");
				// CommonMessage.debugMsg("Suggestion:"+suggestion+"MocId"+MocKeyid);
				UIUtils.sendLotusNotesMail(request, response, mailIds, null, subject.toString(),
						totalContent.toString(), null);
				CommonMessage.debugMsg("Content :\n" + totalContent.toString());
				CommonMessage.debugMsg(" After sendLotusNotesMail to PSI Team");
			}
			// }
			else {
				CommonMessage.debugMsg(" Mail not send to PSI for suggestion Approval no mail id");
			}
		} catch (ValidationExceptions e) {
			CommonMessage.debugMsg("ValidationExceptions from  sendMailToJhLeader()" + e);

		} catch (BusinessApplicationExceptions e) {
			CommonMessage.debugMsg("BusinessApplicationExceptions from  sendMailToJhLeader()" + e);

		}
//			catch(NotesException e)
//			{
//				CommonMessage.debugMsg("NotesException from  sendMailToJhLeader()" + e);
//			}
		catch (Exception e) {

			CommonMessage.debugMsg("Exception from  sendMailToJhLeader()" + e);
		} catch (Throwable e) {

		}

		CommonMessage.debugMsg("--------- Mail Send To PSI for Suggestion Approvel----------");

	}
	
	private void DeleteMoc(HttpServletRequest request, HttpServletResponse response) throws IOException
	   {
	
		ServletOutputStream out = response.getOutputStream();
		   		String mocKeyId = request.getParameter("MocKeyid");
				try
				{		
					String saveMsg = null ;
					if( mocKeyId!= null )
					{
						//if(frmMode!=null&&!(frmMode.equals("View")))
						//{	
							String DeletedmocKeyid =	mocService.Delete(mocKeyId); 
							saveMsg = "Data Deleted Successfully";
						
						
					
						Boolean clrVal=true;
						String delMsg="success-delete";
			
						
						JSONObject successData = new JSONObject();
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",delMsg));
						successData.put("keyid", DeletedmocKeyid);
						//successData.put("frmMode",frmMode);
						JSONObject returnData = new JSONObject();
						returnData.put("formClear",clrVal);
						returnData.put("displyMsg", true);
						returnData.put("successData", successData);
						out.print(returnData.toString());
					}

				}
				catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("BusinessApplicationExceptions"+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"OplCreationExceptions");
					//errMessage.put("tpmException","OPL is approved, Can not be deleted.");
					errMessage.put("displyMsg", false);	
					//CommonMessage.debugMsg(errMessage.toString());
					
				}
				catch(Exception e)
				{
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
		   		
	    }

	@SuppressWarnings({ "unchecked", "unused" })
	private void SaveMOCPSSRCHReccommendation(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		CommonMessage.debugMsg("In PSSR reccm save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				MOCPssrReccommend existMOCPssrReccommend = (MOCPssrReccommend) httpSession
						.getAttribute("MOCReccommendation");
				// GenTlMomdtl
				// existGenTlMomdtl=(GenTlMomdtl)httpSession.getAttribute("GenTlMOmdtl");
				GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
						.getAttribute("newGenTlActionplandtl");
				GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession
						.getAttribute("newGenTlActionplanmst");
				MOCPssrReccommend newMOCPssrReccommend = new MOCPssrReccommend();

				GenTlActionplanmst newActionplanmst = new GenTlActionplanmst();
				GenTlActionplandtl newActionplandtl = new GenTlActionplandtl();
				newMOCPssrReccommend.setPsrrCreatedby(user.getUsrm_ccno());

				newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				String MocKeyidRec = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("MocKeyid Rec" + MocKeyidRec);
				String RefDocType = request.getParameter("doctype");
				String Reccommendation = request.getParameter("Reccommendation");
				String Responsiblity = request.getParameter("Responsiblity");
				String targetDate = request.getParameter("targetDate");
				String ReccomendId = request.getParameter("ReccomendId");
				CommonMessage.debugMsg("ReccomendId::::::::::::::::::::" + ReccomendId);
				String ActionPlanStatus = request.getParameter("ActionPlanStatus");
				CommonMessage.debugMsg("ActionPlanStatus::::::::::::::::::::" + ActionPlanStatus);
				String ActionPlanId = request.getParameter("ActionPlanId");
				String Pssrdetailid = request.getParameter("detailid");
				CommonMessage.debugMsg("Detail::::::::::::::::::::" + Pssrdetailid);
				String paramJsonArrNew = request.getParameter("PSSRChkReccommendconvert");
				String Rowid = request.getParameter("rowid");
				String row = request.getParameter("row");
				String momactnpln = request.getParameter("momactnpln");
				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("flid:::" + flid);
				String elementid = CommonFunctions.getLoginElementId(request);

				newActionplanmst.setAplmMasterrefid(MocKeyidRec);
				newActionplanmst.setAplmDetailrefid(Pssrdetailid);
				newActionplanmst.setAplmFlid(flid);
				newActionplanmst.setAplmRefdoctype(RefDocType);
				newActionplanmst.setAplmMaintask(Reccommendation);
				newActionplanmst.setAplmElementid(elementid);
				newMOCPssrReccommend = (MOCPssrReccommend) UIUtils.setBeanProperties((Object) newMOCPssrReccommend,
						request);
				newActionplandtl.setApldActionplan(Reccommendation);
				newActionplandtl.setApldResponsibility(Responsiblity);
				newActionplandtl.setApldTargetdate(targetDate);
				newActionplandtl.setApldStatus(ActionPlanStatus);
				newActionplanmst = (GenTlActionplanmst) UIUtils.setBeanProperties((Object) newActionplanmst, request);
				newActionplandtl = (GenTlActionplandtl) UIUtils.setBeanProperties((Object) newActionplandtl, request);
				/** save JsonARR conversion For Detail **/
				List<MOCPssrReccommend> MOCPssrReccommendList = new ArrayList<MOCPssrReccommend>();

				List<GenTlActionplanmst> MomActionplanmst = new ArrayList<GenTlActionplanmst>();
				List<GenTlActionplandtl> MomActionplandtl = new ArrayList<GenTlActionplandtl>();

				JSONArray ReccommendList = null;
				JSONArray MomKpijson = null;
				JSONArray MomActionplanJspon = null;
				JSONArray momActionpladtlJSon = null;
				if (UIUtils.isValidKeyId(paramJsonArrNew)) {

					ReccommendList = JSONArray.fromString(paramJsonArrNew);
					momActionpladtlJSon = JSONArray.fromString(paramJsonArrNew);
					CommonMessage.debugMsg("Inside the JSOn");
					MOCPssrReccommendList = (List<MOCPssrReccommend>) UIUtils.convertJSONArrToList(newMOCPssrReccommend,
							ReccommendList);
					MomActionplandtl = (List<GenTlActionplandtl>) UIUtils.convertJSONArrToList(newActionplandtl,
							momActionpladtlJSon);
					newActionplandtl.setActionplanlist(MomActionplandtl);

				}

				/** conversion END **/
				boolean insert = true;

				if (ReccomendId.length() > 0) {
					CommonMessage.debugMsg("In Update Reccommendation");

					existMOCPssrReccommend = mocService.UpdatePSSRReccommendation(MocKeyidRec, Pssrdetailid, targetDate,
							ActionPlanStatus, Responsiblity, Reccommendation);
					
					/*
					 * Now check whether ActionPlan already exists
					 * for THIS recommendation.
					 */
		String actionPlanKey =  mocService.getPssrActionPlanKey(MocKeyidRec,Pssrdetailid );


					CommonMessage.debugMsg(
					    "PSSR Existing ActionPlan Key ::: "
					    + actionPlanKey
					);
					if (!UIUtils.isValidKeyId(actionPlanKey)) {

					    CommonMessage.debugMsg(
					        "PSSR ActionPlan missing - creating ActionPlan"
					    );


					    /*
					     * Reuse the SAME existing createActionPlan call
					     * that your old CREATE recommendation branch uses.
					     *
					     * Do not write another manual ActionPlan insert.
					     */
					    mocService.createActionPlan(
					        newActionplanmst,
					        newActionplandtl,
					        MocKeyidRec,
					        Pssrdetailid
					    );
					}
					else {

					    CommonMessage.debugMsg(
					        "PSSR ActionPlan already exists - update completed"
					    );
					}
					
				}

				else {
					CommonMessage.debugMsg("In Create Reccommendation");
					MOCPssrReccommendList = mocService.createPSSRCheckReccommendation(MOCPssrReccommendList,
							MocKeyidRec, Pssrdetailid);
					String PrrrKeyid = mocService.getPssrKeyid(MocKeyidRec);
					CommonMessage.debugMsg("The" + PrrrKeyid);
					if (Reccommendation != null && Reccommendation.length() > 0) {
						existGenTlActionplanmst = mocService.createActionPlan(newActionplanmst, newActionplandtl,
								MocKeyidRec, PrrrKeyid);

					}

				}

				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;
				if (insert) {
					msgPropertyIdnt = "success-save";
				} else
					msgPropertyIdnt = "success-update";
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
				// successData.put("keyId", existMOCPssrReccommend.getPsrrKeyid());
				successData.put("keyId", MocKeyidRec);
				successData.put("RowId", Rowid);
				// successData.put("momactnpln",true);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("keyId", MocKeyidRec);
				// returnData.put("MomMstkeyid",existMOCPssrReccommend.getPsrrKeyid());
				// returnData.put("MomMstNo",existMOCPssrReccommend.getPsrrKeyid());
				// returnData.put("ActionKeyid",existGenTlActionplanmst.getAplmKeyid());

				if (UIUtils.isValidKeyId(momactnpln)) {
					returnData.put("RowId", Rowid);
					// returnData.put("momactnpln",true);
					returnData.put("formClear", false);

				} else {
					returnData.put("MomDtlkeyid", "");
					returnData.put("RowId", "");
					returnData.put("formClear", false);
					// returnData.put("momactnpln", false);
				}
				out.print(returnData.toString());
			}

		} catch (ValidationExceptions e) {
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewMomeeting");
			out.print(errMessage.toString());
		}

		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "NewMomeeting");
			errMessage.put("tpmException", "Duplicate Entry");
			errMessage.put("displyMsg", false);
			out.print(errMessage.toString());

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}
	
	private void loadWhatIfExcelToGrid(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

	    PrintWriter out = response.getWriter();

	    try {
	        HttpSession httpSession = request.getSession(false);

	        String excelFileName1 = (String) httpSession.getAttribute(AdmUploadExcelServlet_filename);
	        CommonMessage.debugMsg("WhatIf uploaded file name from session ::: " + excelFileName1);
	        CommonMessage.debugMsg("MocNewServlet realPath ::: " + realPath);
	        CommonMessage.debugMsg("Servlet root path ::: " + request.getServletContext().getRealPath("/"));
	     
	        if (excelFileName1 == null || excelFileName1.trim().length() == 0) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Please browse and upload Excel file first.");
	            out.print(err.toString());
	            return;
	        }
	        
	        String basePath = realPath;

	        if (basePath == null || basePath.trim().length() == 0 || "null".equalsIgnoreCase(basePath.trim())) {
	            basePath = request.getServletContext().getRealPath("/");
	        }

	        if (basePath == null) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Upload path is not configured.");
	            out.print(err.toString());
	            return;
	        }

	        if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
	            basePath = basePath + java.io.File.separator;
	        }
	       
	        String excelFileName = basePath + "" + excelFileName1;

	        CommonMessage.debugMsg("WhatIf uploaded file name ::: " + excelFileName1);
	        CommonMessage.debugMsg("WhatIf base path ::: " + basePath);
	        CommonMessage.debugMsg("WhatIf final Excel path ::: " + excelFileName);
	        CommonMessage.debugMsg("WhatIf file exists ::: " + new java.io.File(excelFileName).exists());

	        if (!new java.io.File(excelFileName).exists()) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Uploaded Excel file not found in path: " + excelFileName);
	            out.print(err.toString());
	            return;
	        }
	       

	        JSONArray rows = mocService.readWhatIfExcelRows(excelFileName);
	        // vignesh -- 24May2026
	        boolean hasErrors = false;
	        StringBuilder errorMsg = new StringBuilder();

	        for (int i = 0; i < rows.length(); i++) {
	            JSONObject rowObj = rows.getJSONObject(i);

	            if ("Y".equalsIgnoreCase(rowObj.optString("rowHasError"))) {
	                hasErrors = true;

	                String msg = rowObj.optString("errorMessage", "");
	                if (msg != null && msg.trim().length() > 0) {
	                    errorMsg.append(msg).append("\n");
	                }
	            }
	        }
	        // vignesh -- 24May2026
	        JSONObject result = new JSONObject();
	        result.put("success", true);
	        // vignesh -- 24May2026
	        result.put("hasErrors", hasErrors);
	        result.put("msg", hasErrors ? errorMsg.toString() : "Excel loaded successfully");
	        // vignesh -- 24May2026
	        result.put("rows", rows);
	     
	        // result.put("msg", "Excel loaded successfully");

	        out.print(result.toString());

	    } catch(Exception e) {
	        e.printStackTrace();

	        JSONObject err = new JSONObject();
	        err.put("success", false);
	        err.put("msg", "Unable to read WhatIf Excel: " + e.getMessage());

	        out.print(err.toString());
	    }
	}
	
	private void loadHazopExcelToGrid(HttpServletRequest request,
	        HttpServletResponse response) throws Exception {

	    PrintWriter out = response.getWriter();

	    try {
	        HttpSession httpSession = request.getSession(false);

	        if (httpSession == null) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Session expired. Please login again.");
	            out.print(err.toString());
	            return;
	        }

	        String excelFileName1 = (String) httpSession.getAttribute(AdmUploadExcelServlet_filename);

	        CommonMessage.debugMsg("Hazop uploaded file name from session ::: " + excelFileName1);
	        CommonMessage.debugMsg("MocNewServlet realPath ::: " + realPath);
	        CommonMessage.debugMsg("Servlet root path ::: " + request.getServletContext().getRealPath("/"));

	        if (excelFileName1 == null || excelFileName1.trim().length() == 0) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Please browse and upload Hazop Excel file first.");
	            out.print(err.toString());
	            return;
	        }

	        String basePath = realPath;

	        if (basePath == null || basePath.trim().length() == 0 || "null".equalsIgnoreCase(basePath.trim())) {
	            basePath = request.getServletContext().getRealPath("/");
	        }

	        if (basePath == null) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Upload path is not configured.");
	            out.print(err.toString());
	            return;
	        }

	        if (!basePath.endsWith("/") && !basePath.endsWith("\\")) {
	            basePath = basePath + java.io.File.separator;
	        }

	        String excelFileName = basePath + excelFileName1;

	        CommonMessage.debugMsg("Hazop uploaded file name ::: " + excelFileName1);
	        CommonMessage.debugMsg("Hazop base path ::: " + basePath);
	        CommonMessage.debugMsg("Hazop final Excel path ::: " + excelFileName);
	        CommonMessage.debugMsg("Hazop file exists ::: " + new java.io.File(excelFileName).exists());

	        if (!new java.io.File(excelFileName).exists()) {
	            JSONObject err = new JSONObject();
	            err.put("success", false);
	            err.put("msg", "Uploaded Hazop Excel file not found in path: " + excelFileName);
	            out.print(err.toString());
	            return;
	        }

	        JSONArray rows = mocService.readHazopExcelRows(excelFileName);

	        boolean hasErrors = false;
	        StringBuilder errorMsg = new StringBuilder();

	        for (int i = 0; i < rows.length(); i++) {
	            JSONObject rowObj = rows.getJSONObject(i);

	            if ("Y".equalsIgnoreCase(rowObj.optString("rowHasError"))) {
	                hasErrors = true;

	                String msg = rowObj.optString("errorMessage", "");
	                if (msg != null && msg.trim().length() > 0) {
	                    errorMsg.append(msg).append("\n");
	                }
	            }
	        }

	        JSONObject result = new JSONObject();
	        result.put("success", true);
	        result.put("hasErrors", hasErrors);
	        result.put("msg", hasErrors ? errorMsg.toString() : "Hazop Excel loaded successfully");
	        result.put("rows", rows);

	        out.print(result.toString());

	    } catch(Exception e) {
	        e.printStackTrace();

	        JSONObject err = new JSONObject();
	        err.put("success", false);
	        err.put("msg", "Unable to read Hazop Excel: " + e.getMessage());

	        out.print(err.toString());
	    }
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void SaveMOCReccommendation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonMessage.debugMsg("In reccm save");
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		try {
			if (httpSession != null && user != null) {
				MOCReccommendation existMOCReccommendation = (MOCReccommendation) httpSession
						.getAttribute("MOCReccommendation");
				// GenTlMomdtl
				// existGenTlMomdtl=(GenTlMomdtl)httpSession.getAttribute("GenTlMOmdtl");
				GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
						.getAttribute("newGenTlActionplandtl");
				GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession
						.getAttribute("newGenTlActionplanmst");
				MOCReccommendation newMOCReccommendation = new MOCReccommendation();

				GenTlActionplanmst newActionplanmst = new GenTlActionplanmst();
				GenTlActionplandtl newActionplandtl = new GenTlActionplandtl();
				newMOCReccommendation.setMocrCreatedby(user.getUsrm_ccno());

				newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				String MocKeyidRec = request.getParameter("MocKeyid");
				CommonMessage.debugMsg("MocKeyid Rec" + MocKeyidRec);
				String RefDocType = request.getParameter("doctype");
				String Reccommendation = request.getParameter("Reccommendation");
				String Responsiblity = request.getParameter("Responsiblity");
				String targetDate = request.getParameter("targetDate");
				String ReccomendId = request.getParameter("ReccomendId");
				CommonMessage.debugMsg("ReccomendId::::::::::::::::::::" + ReccomendId);

				String ActionPlanStatus = request.getParameter("ActionPlanStatus");
				CommonMessage.debugMsg("ActionPlanStatus::::::::::::::::::::" + ActionPlanStatus);
				String ActionPlanId = request.getParameter("ActionPlanId");
				String detailid = request.getParameter("detailid");
				CommonMessage.debugMsg("Detail::::::::::::::::::::" + detailid);
				String paramJsonArrNew = request.getParameter("Reccommendconvert");
				String Rowid = request.getParameter("rowid");
				String row = request.getParameter("row");
				String momactnpln = request.getParameter("momactnpln");

				String flid = request.getParameter("flid");
				CommonMessage.debugMsg("flid:::" + flid);
				String elementid = CommonFunctions.getLoginElementId(request);

				newActionplanmst.setAplmMasterrefid(MocKeyidRec);
				newActionplanmst.setAplmDetailrefid(detailid);
				newActionplanmst.setAplmFlid(flid);
				newActionplanmst.setAplmRefdoctype(RefDocType);
				newActionplanmst.setAplmMaintask(Reccommendation);
				newActionplanmst.setAplmElementid(elementid);
				newMOCReccommendation = (MOCReccommendation) UIUtils.setBeanProperties((Object) newMOCReccommendation,
						request);

				newActionplandtl.setApldActionplan(Reccommendation);
				newActionplandtl.setApldResponsibility(Responsiblity);
				newActionplandtl.setApldTargetdate(targetDate);
				newActionplandtl.setApldStatus(ActionPlanStatus);
				newActionplanmst = (GenTlActionplanmst) UIUtils.setBeanProperties((Object) newActionplanmst, request);
				newActionplandtl = (GenTlActionplandtl) UIUtils.setBeanProperties((Object) newActionplandtl, request);
				/** save JsonARR conversion For Detail **/
				List<MOCReccommendation> MOCReccommendationList = new ArrayList<MOCReccommendation>();

				List<GenTlActionplanmst> MomActionplanmst = new ArrayList<GenTlActionplanmst>();
				List<GenTlActionplandtl> MomActionplandtl = new ArrayList<GenTlActionplandtl>();

				JSONArray ReccommendList = null;
				JSONArray MomKpijson = null;
				JSONArray MomActionplanJspon = null;
				JSONArray momActionpladtlJSon = null;
				if (UIUtils.isValidKeyId(paramJsonArrNew)) {

					ReccommendList = JSONArray.fromString(paramJsonArrNew);

					momActionpladtlJSon = JSONArray.fromString(paramJsonArrNew);
					CommonMessage.debugMsg("Inside the JSOn");
					MOCReccommendationList = (List<MOCReccommendation>) UIUtils
							.convertJSONArrToList(newMOCReccommendation, ReccommendList);
					MomActionplandtl = (List<GenTlActionplandtl>) UIUtils.convertJSONArrToList(newActionplandtl,
							momActionpladtlJSon);
					newActionplandtl.setActionplanlist(MomActionplandtl);

				}

				/** conversion END **/
				boolean insert = true;

				if (ReccomendId.length() > 0) {
					CommonMessage.debugMsg("In Update Reccommendation");

					existMOCReccommendation = mocService.UpdateReccommendation(MocKeyidRec, detailid, targetDate,
							ActionPlanStatus, Responsiblity);
				}

				else {
					CommonMessage.debugMsg("In Create Reccommendation");
					MOCReccommendationList = mocService.createReccommendation(MOCReccommendationList, MocKeyidRec,
							detailid);

					if (Reccommendation != null && Reccommendation.length() > 0) {
						existGenTlActionplanmst = mocService.createActionPlan(newActionplanmst, newActionplandtl,
								MocKeyidRec, detailid);

					}

				}

				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;
				if (insert) {
					msgPropertyIdnt = "success-save";
				} else
					msgPropertyIdnt = "success-update";
				successData.put("msg",
						UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt));
				// successData.put("keyId", existMOCPssrReccommend.getPsrrKeyid());
				successData.put("keyId", MocKeyidRec);
				successData.put("RowId", Rowid);
				// successData.put("momactnpln",true);
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);
				returnData.put("keyId", MocKeyidRec);
				// returnData.put("MomMstkeyid",existMOCPssrReccommend.getPsrrKeyid());
				// returnData.put("MomMstNo",existMOCPssrReccommend.getPsrrKeyid());
				// returnData.put("ActionKeyid",existGenTlActionplanmst.getAplmKeyid());

				if (UIUtils.isValidKeyId(momactnpln)) {
					returnData.put("RowId", Rowid);
					// returnData.put("momactnpln",true);
					returnData.put("formClear", false);

				} else {
					returnData.put("MomDtlkeyid", "");
					returnData.put("RowId", "");
					returnData.put("formClear", false);
					// returnData.put("momactnpln", false);
				}
				out.print(returnData.toString());
			}

		} catch (ValidationExceptions e) {
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewMomeeting");
			out.print(errMessage.toString());
		}

		catch (BusinessApplicationExceptions e) {
			e.printStackTrace();
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "NewMomeeting");
			errMessage.put("tpmException", "Duplicate Entry");
			errMessage.put("displyMsg", false);
			out.print(errMessage.toString());

		} catch (Exception e) {
			e.printStackTrace();
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
	}

	/*
	 * @SuppressWarnings("unchecked") private void
	 * SavePssrReccommendation(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { HttpSession httpSession =
	 * request.getSession(false); ServletOutputStream out =
	 * response.getOutputStream(); AdmTlUsermst user =
	 * UIUtils.getLoginUser(request); try { if( httpSession != null && user != null)
	 * { MOCPssrReccommend
	 * existMOCPssrReccommend=(MOCPssrReccommend)httpSession.getAttribute(
	 * "MOCPssrReccommend"); //GenTlMomdtl
	 * existGenTlMomdtl=(GenTlMomdtl)httpSession.getAttribute("GenTlMOmdtl");
	 * GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl)
	 * httpSession.getAttribute("newGenTlActionplandtl"); GenTlActionplanmst
	 * existGenTlActionplanmst = (GenTlActionplanmst)
	 * httpSession.getAttribute("newGenTlActionplanmst"); MOCPssrReccommend
	 * newMOCPssrReccommend = new MOCPssrReccommend();
	 * 
	 * GenTlActionplanmst newActionplanmst=new GenTlActionplanmst();
	 * GenTlActionplandtl newActionplandtl=new GenTlActionplandtl();
	 * newMOCPssrReccommend.setPsrrCreatedby(user.getUsrm_ccno());
	 * 
	 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno()); String
	 * MocKeyidRec=request.getParameter("MocKeyid");
	 * CommonMessage.debugMsg("MocKeyid Rec"+MocKeyidRec); String
	 * RefDocType=request.getParameter("doctype"); String
	 * Reccommendation=request.getParameter("Reccommendation"); String
	 * Responsiblity=request.getParameter("Responsiblity"); String
	 * targetDate=request.getParameter("targetDate"); String
	 * ActionPlanStatus=request.getParameter("ActionPlanStatus"); String
	 * ActionPlanId=request.getParameter("ActionPlanId");
	 * 
	 * String paramJsonArrNew=request.getParameter("pssrReccommendconvert"); String
	 * Rowid = request.getParameter("rowid"); String
	 * row=request.getParameter("row"); String momactnpln =
	 * request.getParameter("momactnpln");
	 * 
	 * String flid=request.getParameter("flid");
	 * CommonMessage.debugMsg("flid:::"+flid); String
	 * elementid=CommonFunctions.getLoginElementId(request);
	 * 
	 * 
	 * 
	 * newActionplanmst.setAplmFlid(flid);
	 * newActionplanmst.setAplmRefdoctype(RefDocType);
	 * newActionplanmst.setAplmMaintask(Reccommendation);
	 * newActionplanmst.setAplmElementid(elementid); newMOCPssrReccommend
	 * =(MOCPssrReccommend)UIUtils.setBeanProperties((Object)newMOCPssrReccommend,
	 * request);
	 * 
	 * newActionplandtl.setApldActionplan(Reccommendation);
	 * newActionplandtl.setApldResponsibility(Responsiblity);
	 * newActionplandtl.setApldTargetdate(targetDate);
	 * newActionplandtl.setApldStatus(ActionPlanStatus);
	 * newActionplanmst=(GenTlActionplanmst)UIUtils.setBeanProperties((Object)
	 * newActionplanmst, request);
	 * newActionplandtl=(GenTlActionplandtl)UIUtils.setBeanProperties((Object)
	 * newActionplandtl,request);
	 *//** save JsonARR conversion For Detail **/

	/*
	 * List<MOCPssrReccommend> MOCPssrReccommendList = new
	 * ArrayList<MOCPssrReccommend>();
	 * 
	 * List<GenTlActionplanmst> MomActionplanmst=new
	 * ArrayList<GenTlActionplanmst>(); List<GenTlActionplandtl>
	 * MomActionplandtl=new ArrayList<GenTlActionplandtl>();
	 * 
	 * JSONArray ReccommendList = null; JSONArray MomKpijson = null; JSONArray
	 * MomActionplanJspon=null; JSONArray momActionpladtlJSon=null;
	 * if(UIUtils.isValidKeyId(paramJsonArrNew) ) {
	 * 
	 * ReccommendList = JSONArray.fromString(paramJsonArrNew);
	 * 
	 * 
	 * momActionpladtlJSon = JSONArray.fromString(paramJsonArrNew);
	 * MOCPssrReccommendList=(List<MOCPssrReccommend>)UIUtils.convertJSONArrToList(
	 * newMOCPssrReccommend, ReccommendList);
	 * MomActionplandtl=(List<GenTlActionplandtl>)UIUtils.convertJSONArrToList(
	 * newActionplandtl, momActionpladtlJSon);
	 * newActionplandtl.setActionplanlist(MomActionplandtl);
	 * 
	 * }
	 * 
	 * 
	 *//** conversion END **//*
								 * boolean insert = true;
								 * 
								 * 
								 * 
								 * if( Reccommendation!= null) {
								 * MOCPssrReccommendList=mocService.createMocReccommendation(
								 * MOCPssrReccommendList,MocKeyidRec); String
								 * PrrrKeyid=mocService.getPssrKeyid(MocKeyidRec);
								 * CommonMessage.debugMsg("The"+PrrrKeyid);
								 * 
								 * if(Reccommendation!=null && Reccommendation.length()>0 ){
								 * existGenTlActionplanmst=mocService.createActionPlan(newActionplanmst,
								 * newActionplandtl,MocKeyidRec,PrrrKeyid); }
								 * 
								 * } else { existMOCPssrReccommend =
								 * NewmomeetingService.update(newGenTlMommst,existGenTlMommst, momeetingBean);
								 * existGenTlMommst =
								 * NewmomeetingService.updateatt(newGenTlMommst,existGenTlMommst);
								 * if(ActionPlanDesc!=null && ActionPlanDesc.length()>0){
								 * existGenTlActionplanmst=NewmomeetingService.updateActionPlan(
								 * newActionplanmst,
								 * newActionplandtl,newGenTlMommst,Rowid,ActionPlanId,ActionplanDetailId); }
								 * insert = false; }
								 * httpSession.setAttribute(existGenTlMommst.getMomsKeyid(),existGenTlMommst);
								 * httpSession.setAttribute("GenTlMOmmst", existGenTlMommst); JSONObject
								 * successData = new JSONObject(); String msgPropertyIdnt; if( insert) {
								 * msgPropertyIdnt = "success-save"; } else msgPropertyIdnt = "success-update";
								 * successData.put("msg",UIUtils.getPropertyValue(
								 * "com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
								 * //successData.put("keyId", existMOCPssrReccommend.getPsrrKeyid());
								 * successData.put("keyId",MocKeyidRec); successData.put("RowId",Rowid);
								 * successData.put("momactnpln",true); JSONObject returnData = new JSONObject();
								 * returnData.put("successData", successData);
								 * returnData.put("keyId",MocKeyidRec);
								 * //returnData.put("MomMstkeyid",existMOCPssrReccommend.getPsrrKeyid());
								 * //returnData.put("MomMstNo",existMOCPssrReccommend.getPsrrKeyid());
								 * //returnData.put("ActionKeyid",existGenTlActionplanmst.getAplmKeyid());
								 * 
								 * 
								 * if(UIUtils.isValidKeyId(momactnpln)){ returnData.put("RowId",Rowid);
								 * returnData.put("momactnpln",true); returnData.put("formClear",false);
								 * returnData.put("MomMstkeyid",existMOCPssrReccommend.getPsrrKeyid()); }else {
								 * returnData.put("MomDtlkeyid",""); returnData.put("RowId","");
								 * returnData.put("formClear",false); returnData.put("momactnpln", false); }
								 * out.print(returnData.toString()); }
								 * 
								 * } catch (ValidationExceptions e) { e.printStackTrace();
								 * net.sf.json.JSONObject errMessage =
								 * UIUtils.validationExceptions(e.toString(), "NewMomeeting");
								 * out.print(errMessage.toString()); }
								 * 
								 * catch(BusinessApplicationExceptions e) { e.printStackTrace(); JSONObject
								 * errMessage = UIUtils.businessValidationExceptions(e.toString(),
								 * "NewMomeeting"); errMessage.put("tpmException","Duplicate Entry");
								 * errMessage.put("displyMsg", false); out.print(errMessage.toString());
								 * 
								 * } catch(Exception e) { e.printStackTrace(); JSONObject err = new
								 * JSONObject(); err.put("tpmException", "Data Not Saved");
								 * out.print(err.toString()); } }
								 */

	private void SaveFinalApproval(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String saveMsg = "";
		if (httpSession != null && user != null) {
			try {
				String keyid = request.getParameter("keyid");
				String EmpId = request.getParameter("EmpId");
				String status = request.getParameter("status");
				String date = request.getParameter("date");
				String remarks = request.getParameter("remarks");
				String MocKeyId = request.getParameter("MocKeyId");
				MocTeamConfigmst mocTeamConfigmst = new MocTeamConfigmst();
				MocTeamConfigmst existMocTeamConfigmst = (MocTeamConfigmst) httpSession
						.getAttribute("MocTeamConfigmst");
				mocTeamConfigmst = (MocTeamConfigmst) UIUtils.setBeanProperties((Object) mocTeamConfigmst, request);

				if (keyid.length() > 0) {
					CommonMessage.debugMsg("Inside the Update");
					existMocTeamConfigmst = mocService.getFinalApproval(mocTeamConfigmst, existMocTeamConfigmst, keyid,
							EmpId, status, date, remarks, MocKeyId);
					saveMsg = "Data Updated Successfully";
				}

				JSONObject persistentData = new JSONObject();
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("keyId", MocKeyId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear", false);
				returnData.put("forwardData", forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);
				out.print(returnData.toString());
				try {
					CommonMessage.debugMsg("Inside Final  Approval Mail Trigger");
					String SuggestionNo = request.getParameter("SuggestionNo");
					String Suggestion = request.getParameter("Suggestion");
					CommonMessage.debugMsg("Suggestion" + Suggestion);
					String MOCtitle = request.getParameter("MOCtitle");
					String emailId = mocService.getHazopnextApprovalKeyid(MocKeyId);
					CommonMessage.debugMsg("emailId" + emailId);
					SendMailToJHLeader(request, response, emailId, SuggestionNo, Suggestion, MocKeyId, MOCtitle);
				} catch (Exception e) {
					CommonMessage.debugMsg("Mail" + e);
				}
			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectValidations");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				JSONObject err = new JSONObject();

				out.print(err.toString());
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();

				err.put("tpmException", "Data Not Saved");

				out.print(err.toString());
			}
		}
	}

	private void DeleteWhatifRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonFunctions.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonFunctions.debugMsg("KEYID: " + keyid);
		try {
			if (UIUtils.isValidKeyId(keyid)) {
				mocService.DeleteWhatifRow(keyid);
				String msgPropertyIdnt = "success-delete";
				// CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt);
				err.put("successData", mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
			CommonFunctions.debugMsg("Delete End");
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception: " + e);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete");
			err.put("successData", mesg);
			CommonFunctions.debugMsg(err.toString());
			out.print(err.toString());
		}
	}

	private void DeleteHazopRow(HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommonFunctions.debugMsg("Servlet Remove:");
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		CommonFunctions.debugMsg("KEYID: " + keyid);
		try {
			if (UIUtils.isValidKeyId(keyid)) {
				mocService.DeleteHazopRow(keyid);
				String msgPropertyIdnt = "success-delete";
				// CommonMessage.debugMsg("msgPropertyIdnt:::::: "+msgPropertyIdnt);
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", msgPropertyIdnt);
				err.put("successData", mesg);
				CommonFunctions.debugMsg(err.toString());
				out.print(err.toString());
			}
			CommonFunctions.debugMsg("Delete End");
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception: " + e);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete");
			err.put("successData", mesg);
			CommonFunctions.debugMsg(err.toString());
			out.print(err.toString());
		}
	}
	
	// -- vignesh 07sep2026
	
	private void DeletePssrReccommendationRow(
	        HttpServletRequest request,
	        HttpServletResponse response) throws IOException
	{
	    CommonFunctions.debugMsg("Servlet Remove PSSR Recommendation:");

	    PrintWriter out = response.getWriter();

	    String keyid = request.getParameter("keyid");

	    CommonFunctions.debugMsg("PSSR Recommendation KEYID: " + keyid);

	    try
	    {
	        if (UIUtils.isValidKeyId(keyid))
	        {
	            mocService.DeletePssrReccommendationRow(keyid);

	            JSONObject result = new JSONObject();

	            String message = UIUtils.getPropertyValue(
	                    "com.akranta.tpm.resources.CommonMessages",
	                    "success-delete"
	            );

	            result.put("successData", message);

	            out.print(result.toString());
	        }
	    }
	    catch (Exception e)
	    {
	        CommonFunctions.debugMsg(
	            "PSSR Recommendation Delete Exception: " + e
	        );

	        JSONObject result = new JSONObject();

	        String message = UIUtils.getPropertyValue(
	                "com.akranta.tpm.resources.CommonMessages",
	                "err-delete"
	        );

	        result.put("successData", message);

	        out.print(result.toString());
	    }
	}
	// -- vignesh 07sep2026

	private void SaveHazopApproval(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String saveMsg = "";
		if (httpSession != null && user != null) {
			try {
				String keyid = request.getParameter("keyid");
				String EmpId = request.getParameter("EmpId");
				String status = request.getParameter("status");
				String date = request.getParameter("date");
				String remarks = request.getParameter("remarks");
				String MocKeyId = request.getParameter("MocKeyId");
				String PMMech = request.getParameter("PMMech");
				String PMElect = request.getParameter("PMElect");
				String PMInst = request.getParameter("PMInst");
				MocTeamConfigmst mocTeamConfigmst = new MocTeamConfigmst();
				MocTeamConfigmst existMocTeamConfigmst = (MocTeamConfigmst) httpSession
						.getAttribute("MocTeamConfigmst");
				mocTeamConfigmst = (MocTeamConfigmst) UIUtils.setBeanProperties((Object) mocTeamConfigmst, request);

				if (keyid.length() > 0) {
					CommonMessage.debugMsg("Inside the Update");
					existMocTeamConfigmst = mocService.getHazopApproval(mocTeamConfigmst, existMocTeamConfigmst, keyid,
							EmpId, status, date, remarks, MocKeyId);
					saveMsg = "Data Updated Successfully";
				}

				JSONObject persistentData = new JSONObject();
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("keyId", MocKeyId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear", false);
				returnData.put("forwardData", forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);
				out.print(returnData.toString());
				try {
					CommonMessage.debugMsg("Inside W/H Approval Mail Trigger");
					String SuggestionNo = request.getParameter("SuggestionNo");
					String Suggestion = request.getParameter("Suggestion");
					CommonMessage.debugMsg("Suggestion" + Suggestion);
					String MOCtitle = request.getParameter("MOCtitle");
					String emailId = mocService.getHazopnextApprovalKeyid(MocKeyId);
					CommonMessage.debugMsg("emailId" + emailId);
					SendMailToJHLeader(request, response, emailId, SuggestionNo, Suggestion, MocKeyId, MOCtitle);
				} catch (Exception e) {
					CommonMessage.debugMsg("Mail" + e);
				}
			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectValidations");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				JSONObject err = new JSONObject();

				out.print(err.toString());
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();

				err.put("tpmException", "Data Not Saved");

				out.print(err.toString());
			}
		}

	}

	private void SaveInitialApproval(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		ServletOutputStream out = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String saveMsg = "";
		if (httpSession != null && user != null) {
			try {
				String keyid = request.getParameter("keyid");
				String EmpId = request.getParameter("EmpId");
				String status = request.getParameter("status");
				String date = request.getParameter("date");
				String remarks = request.getParameter("remarks");
				String MocKeyId = request.getParameter("MocKeyId");
				CommonMessage.debugMsg("MOC Id" + MocKeyId);
				MocTeamConfigmst mocTeamConfigmst = new MocTeamConfigmst();
				MocTeamConfigmst existMocTeamConfigmst = (MocTeamConfigmst) httpSession
						.getAttribute("MocTeamConfigmst");
				mocTeamConfigmst = (MocTeamConfigmst) UIUtils.setBeanProperties((Object) mocTeamConfigmst, request);

				if (keyid.length() > 0) {
					CommonMessage.debugMsg("Inside the Update");
					existMocTeamConfigmst = mocService.getInitialApproval(mocTeamConfigmst, existMocTeamConfigmst,
							keyid, EmpId, status, date, remarks, MocKeyId);
					saveMsg = "Data Updated Successfully";
				}

				JSONObject persistentData = new JSONObject();
				JSONObject forwardData = new JSONObject();
				JSONObject successData = new JSONObject();
				successData.put("msg", saveMsg);
				successData.put("keyId", MocKeyId);
				JSONObject returnData = new JSONObject();
				returnData.put("formClear", false);
				returnData.put("forwardData", forwardData);
				returnData.put("persistentData", persistentData);
				returnData.put("successData", successData);
				out.print(returnData.toString());
				try {
					CommonMessage.debugMsg("Inside Initial Approval Mail Trigger");
					String SuggestionNo = request.getParameter("SuggestionNo");
					String Suggestion = request.getParameter("Suggestion");
					CommonMessage.debugMsg("Suggestion" + Suggestion);
					String MOCtitle = request.getParameter("MOCtitle");
					String emailId = mocService.getnextApprovalKeyid(MocKeyId);
					CommonMessage.debugMsg("emailId" + emailId);
					SendMailToJHLeader(request, response, emailId, SuggestionNo, Suggestion, MocKeyId, MOCtitle);
				} catch (Exception e) {
					CommonMessage.debugMsg("Mail" + e);
				}

			} catch (ValidationExceptions e) {
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ProjectValidations");
				out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				JSONObject err = new JSONObject();

				out.print(err.toString());
			} catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();

				err.put("tpmException", "Data Not Saved");

				out.print(err.toString());
			}
		}

	}

	private void SendMailToJHLeader(HttpServletRequest request, HttpServletResponse response, String emailId,
			String SuggestionNo, String suggestion, String MocKeyid, String MOCtitle)
			throws ServletException, IOException, Exception {
		CommonMessage.debugMsg("---------Sending Mail To Jh Leader for MOC Approvel----------");
		CommonMessage.debugMsg("suggestion:" + suggestion);
		CommonMessage.debugMsg("emailId :" + emailId);
		CommonMessage.debugMsg("MocKeyid :" + MocKeyid);
		CommonMessage.debugMsg("MOCtitle :" + MOCtitle);
		try {
			// CommonMessage.debugMsg("E-Mail Id :" +UIUtils.isValidEmail(emailId) + " " +
			// emailId);
			if (UIUtils.isValidEmail(emailId)) {

				String disclaimerNote = "--";
				StringBuilder subject = new StringBuilder("");
				subject.append(" MOC Suggestion Wating for Approval ");
				StringBuilder totalContent = new StringBuilder();
				totalContent.append("\r\n");
				totalContent.append("Suggestion No: " + SuggestionNo).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("Suggestion : " + suggestion).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("MOC No : " + MocKeyid).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append("MOC Title : " + MOCtitle).append("\r\n");
				totalContent.append("\r\n");
				totalContent.append(disclaimerNote);
				totalContent.append("\r\n");
				// totalContent.append("Regards,");
				totalContent.append("\r\n");
				totalContent.append(
						"This is system generated mail. Please do not reply to this mail,\n For any further assistance please contact perfex support team");
				CommonMessage.debugMsg(" Before sendLotusNotesMail to JH leader");
				CommonMessage.debugMsg("Suggestion:" + suggestion + "MocId" + MocKeyid);
				UIUtils.sendLotusNotesMail(request, response, emailId, null, subject.toString(),
						totalContent.toString(), null);
				CommonMessage.debugMsg("Content :\n" + totalContent.toString());
				CommonMessage.debugMsg(" After sendLotusNotesMail to jh leader");
			}
			// }
			else {
				CommonFunctions.debugMsg(" Mail not send to jh leader for suggestion Approval no mail id");
			}
		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("ValidationExceptions from  sendMailToJhLeader()" + e);

		} catch (BusinessApplicationExceptions e) {
			CommonFunctions.debugMsg("BusinessApplicationExceptions from  sendMailToJhLeader()" + e);

		}
//			catch(NotesException e)
//			{
//				CommonFunctions.debugMsg("NotesException from  sendMailToJhLeader()" + e);
//			}
		catch (Exception e) {

			CommonFunctions.debugMsg("Exception from  sendMailToJhLeader()" + e);
		} catch (Throwable e) {

		}

		CommonFunctions.debugMsg("--------- Mail Send To Jh Leader for Suggestion Approvel----------");
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request, String string, boolean b) {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter = (CommonFilter) httpSession.getAttribute(string);
		if (commonFilter != null && !b) {
			FilterValues.setPaginationParams(request, commonFilter);
		} else {
			commonFilter = new CommonFilter();
			commonFilter = FilterValues.getCommonFilters(request, commonFilter);
			commonFilter = FilterValues.getBDRelated(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(string);
			httpSession.setAttribute(string, commonFilter);
		}
		return commonFilter;
	}

	@SuppressWarnings("unchecked")
	private void SaveMOC(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpSession httpSession = request.getSession(false);
		PrintWriter out = response.getWriter();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String flid = request.getParameter("flid");
		String openfilemgr = request.getParameter("filemanager");
		String ConvertDesc = request.getParameter("BasisDetails");
		CommonMessage.debugMsg("ConvertDesc:::" + ConvertDesc);

		String basisJsonArr = request.getParameter("paramconvertArr");
		String paramJsonArr = request.getParameter("paramconvert");
		// String QuestionConvertDesc=request.getParameter("paramconvert");

		String gridval = request.getParameter("gridval");
		CommonMessage.debugMsg("gridval:::" + gridval);
		String title = request.getParameter("title");
		/*
		 * Fallback to hidden form field.
		 * Normally "title" comes from frmMocProject_beforeSubmit().
		 */
		if (title == null || title.trim().length() == 0) {
		    title = request.getParameter("hdntitle");
		}
		
		CommonMessage.debugMsg("Title iss:::" + title);

		// String MocKeyid=request.getParameter("MocKeyid");

		String keyId = request.getParameter("MocKeyid");
		CommonMessage.debugMsg("MOC" + keyId);

		String Nature = request.getParameter("Nature");
		String Desc = request.getParameter("Desc");
		String Detail = request.getParameter("Detail");
		CommonMessage.debugMsg("Detail" + Detail);

		String Dmt = request.getParameter("Dmt");
		String Jh = request.getParameter("Jh");
		String Initiator = request.getParameter("Initiator");
		String MOCTitle = request.getParameter("MOCTitle");
		String MocType = request.getParameter("MocType");
		String MocKeyidQuestionnaire = request.getParameter("MocKeyid");

// 	Vignesh adding 11may 2026
		String MocCapex = request.getParameter("chckMocRfcmCapex");

		if (MocCapex != null) {
			MocCapex = MocCapex.trim();
		}
		CommonMessage.debugMsg("MocCapex" + MocCapex);

// 	Vignesh adding 11may 2026

		if (httpSession != null && user != null) {
			MocRfcmstNew mocRfcmst = new MocRfcmstNew();
			MocRfcmstNew existMocRfcmst = (MocRfcmstNew) httpSession.getAttribute("mocRfcmst");
			mocRfcmst = (MocRfcmstNew) UIUtils.setBeanProperties((Object) mocRfcmst, request);

			String noOfDays = request.getParameter("noofdays");

			if (noOfDays != null) {
				noOfDays = noOfDays.trim();
			}

			CommonMessage.debugMsg("MOC No Of Days = " + noOfDays);

			mocRfcmst.setRfcmNoofdays(noOfDays);

			String formType = request.getParameter("formType");

			String KeyId = mocRfcmst.getRfcmKeyid();
			CommonMessage.debugMsg("KeyId" + KeyId);
			mocRfcmst.setRfcmCreatedby(user.getUsrm_ccno());
			mocRfcmst.setRfcmModifiedby(user.getUsrm_ccno());
			JSONObject MOCSuccessmsg = new JSONObject();
			String savemsg = null;
			mocRfcmst.setRfcmCreatedby(user.getUsrm_ccno());
			mocRfcmst.setRfcmModifiedby(user.getUsrm_ccno());

//  				 	Vignesh adding 11may 2026
			mocRfcmst.setRfcmCapex(MocCapex);
//  				 	Vignesh adding 11may 2026

			try {
				/*if (title.equals("MOC Team")) {
					if (gridval != null) {
						MocTeamConfigmstNew mocTeamConfigmst = new MocTeamConfigmstNew();
						MocRfcBasismst mocRfcBasismst = new MocRfcBasismst();
						String createdBy = user.getUsrm_ccno();
						mocTeamConfigmst.setMctcRfcmst(mocRfcmst);
						JSONArray teamList = null;
						List<MocTeamConfigmstNew> MocTeamConfigmstNew = null;

						if (UIUtils.isValidKeyId(gridval)) {
							teamList = JSONArray.fromString(gridval);
							CommonMessage.debugMsg("EmployeeList" + gridval);

							MocTeamConfigmstNew = (List<MocTeamConfigmstNew>) UIUtils
									.convertJSONArrToList(mocTeamConfigmst, teamList);
							// Vignesh 13-May-2026: Check secondary approval employee id from JSP grid JSON
							for (int i = 0; i < teamList.length(); i++) {
								JSONObject rowObj = teamList.getJSONObject(i);

								String roleName = rowObj.optString("txtRoleName", "");
								String primaryEmp = rowObj.optString("hdnMctcempid", "");
								String secondaryEmp = rowObj.optString("hdnMctcempid2", "");

								if (secondaryEmp == null) {
									secondaryEmp = "";
								}

								secondaryEmp = secondaryEmp.trim();

								CommonMessage.debugMsg("MOC Team Row " + (i + 1) + " Role=" + roleName + " PrimaryEmp="
										+ primaryEmp + " SecondaryEmp=" + secondaryEmp);

								if (MocTeamConfigmstNew != null && i < MocTeamConfigmstNew.size()) {
									MocTeamConfigmstNew.get(i).setMctcSecEmpid(secondaryEmp);

									CommonMessage.debugMsg("Model SecondaryEmp Saved In Tempfield10 = "
											+ MocTeamConfigmstNew.get(i).getMctcSecEmpid());
								}
							}
							// Vignesh 13-May-2026: Check secondary approval employee id from JSP grid JSON
							CommonMessage.debugMsg("MocTeamConfigmst ::::" + MocTeamConfigmstNew);
						}

						String mocKeyIdFromRequest = request.getParameter("MocKeyid");
						CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest);

//  					            if(mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
//  					                mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest);
//  					            }

						if (mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
							mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest);
						} else if (teamList != null && teamList.length() > 0) {

							String masterIdFromGrid = teamList.getJSONObject(0).optString("hdnMctcmasterid", "").trim();
							CommonMessage.debugMsg("masterIdFromGrid :: " + masterIdFromGrid);
							if (!masterIdFromGrid.isEmpty()) {
								mocRfcmst.setRfcmKeyid(masterIdFromGrid);
							}
						}

						CommonMessage.debugMsg("Final mocRfcmst.getRfcmKeyid() :: " + mocRfcmst.getRfcmKeyid());

						if (mocRfcmst.getRfcmKeyid() == null || mocRfcmst.getRfcmKeyid().trim().isEmpty()) {

							String suggestionId = request.getParameter("SuggestionId");
							CommonMessage.debugMsg("mkeyidcheck " + suggestionId);

							if (suggestionId != null && !suggestionId.trim().isEmpty()) {
								mocRfcmst.setRfcmsuggestionid(suggestionId);
							}

							mocRfcmst.setRfcmflid(flid);
							CommonMessage
									.debugMsg("mocRfcmst.getRfcmKeyid() before create: " + mocRfcmst.getRfcmKeyid());

							existMocRfcmst = mocService.createMOC(mocRfcmst, existMocRfcmst, mocRfcBasismst,
									mocTeamConfigmst);

							MocTeamConfigmstNew = mocService.createMOCTeam(MocTeamConfigmstNew, createdBy,
									existMocRfcmst);

							String newMockeyid = existMocRfcmst.getRfcmKeyid();
							mocRfcmst.setRfcmKeyid(newMockeyid);

							CommonMessage.debugMsg("New MOC Created with ID: " + newMockeyid);
							savemsg = "Data saved successfully";

						} else {

							CommonMessage.debugMsg("Updating existing MOC: " + mocRfcmst.getRfcmKeyid());
							// Vignesh 13 may
							// Update CapEx in master table for existing MOC Team save
							if (MocCapex != null) {
								MocCapex = MocCapex.trim();
							}

							if ("Y".equals(MocCapex) || "N".equals(MocCapex)) {
								mocService.updateMocCapex(mocRfcmst.getRfcmKeyid(), MocCapex, user.getUsrm_ccno());
							}
							// Vignesh 13 may
							MocTeamConfigmstNew = mocService.createMOCTeam(MocTeamConfigmstNew, createdBy, mocRfcmst);

							savemsg = "Data updated successfully";
						}

						String mockey = mocRfcmst.getRfcmKeyid();
						CommonMessage.debugMsg("mockey returned to UI :: " + mockey);

						JSONObject successData = new JSONObject();
						successData.put("msg", savemsg);
						successData.put("keyId", mockey);
						MOCSuccessmsg.put("successData", successData);
						MOCSuccessmsg.put("formClear", false);
						out.print(MOCSuccessmsg.toString());
						out.close();
					}
				}*/
				if (title.equals("MOC Team")) {
					if (gridval != null) {
						MocTeamConfigmstNew mocTeamConfigmst = new MocTeamConfigmstNew();
						MocRfcBasismst mocRfcBasismst = new MocRfcBasismst();
						String createdBy = user.getUsrm_ccno();
						mocTeamConfigmst.setMctcRfcmst(mocRfcmst);
						JSONArray teamList = null;
						List<MocTeamConfigmstNew> MocTeamConfigmstNew = null;

						if (UIUtils.isValidKeyId(gridval)) {
							teamList = JSONArray.fromString(gridval);
							CommonMessage.debugMsg("EmployeeList" + gridval);

							MocTeamConfigmstNew = (List<MocTeamConfigmstNew>) UIUtils
									.convertJSONArrToList(mocTeamConfigmst, teamList);
							// Vignesh 13-May-2026: Check secondary approval employee id from JSP grid JSON
							for (int i = 0; i < teamList.length(); i++) {
								JSONObject rowObj = teamList.getJSONObject(i);

								String roleName = rowObj.optString("txtRoleName", "");
								String primaryEmp = rowObj.optString("hdnMctcempid", "");
								String secondaryEmp = rowObj.optString("hdnMctcempid2", "");

								if (secondaryEmp == null) {
									secondaryEmp = "";
								}

								secondaryEmp = secondaryEmp.trim();

								CommonMessage.debugMsg("MOC Team Row " + (i + 1) + " Role=" + roleName + " PrimaryEmp="
										+ primaryEmp + " SecondaryEmp=" + secondaryEmp);

								if (MocTeamConfigmstNew != null && i < MocTeamConfigmstNew.size()) {
									MocTeamConfigmstNew.get(i).setMctcSecEmpid(secondaryEmp);

									CommonMessage.debugMsg("Model SecondaryEmp Saved In Tempfield10 = "
											+ MocTeamConfigmstNew.get(i).getMctcSecEmpid());
								}
							}
							// Vignesh 13-May-2026: Check secondary approval employee id from JSP grid JSON
							CommonMessage.debugMsg("MocTeamConfigmst ::::" + MocTeamConfigmstNew);
						}

//						String mocKeyIdFromRequest = request.getParameter("MocKeyid");
//						CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest);
						
						keyId = request.getParameter("MocKeyid");
						// Debug all values if duplicate MocKeyid is coming
						
						  String[] mocValues = request.getParameterValues("MocKeyid");
						if (mocValues != null) {
						    for (int i = 0; i < mocValues.length; i++) {
						        CommonMessage.debugMsg("MocKeyid value[" + i + "] = " + mocValues[i]);

						        if (UIUtils.isValidKeyId(mocValues[i])) {
						            keyId = mocValues[i].trim();
						            break;
						        }
						    }
						}

						if (!UIUtils.isValidKeyId(keyId)) {
						    keyId = request.getParameter("txtRfcmKeyid");
						}

						if (!UIUtils.isValidKeyId(keyId)) {
						    keyId = request.getParameter("hdnMocKeyId");
						}

						if (keyId == null) {
						    keyId = "";
						}

						keyId = keyId.trim();
						
						CommonMessage.debugMsg("mocKeyIdFromRequest :: " + keyId);

//  					            if(mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
//  					                mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest);
//  					            }

						if (keyId != null && !keyId.trim().isEmpty()) {
							mocRfcmst.setRfcmKeyid(keyId);
						} else if (teamList != null && teamList.length() > 0) {

							String masterIdFromGrid = teamList.getJSONObject(0).optString("hdnMctcmasterid", "").trim();
							CommonMessage.debugMsg("masterIdFromGrid :: " + masterIdFromGrid);
							if (!masterIdFromGrid.isEmpty()) {
								mocRfcmst.setRfcmKeyid(masterIdFromGrid);
							}
						}

						CommonMessage.debugMsg("Final mocRfcmst.getRfcmKeyid() :: " + mocRfcmst.getRfcmKeyid());

						if (mocRfcmst.getRfcmKeyid() == null || mocRfcmst.getRfcmKeyid().trim().isEmpty()) {

							String suggestionId = request.getParameter("SuggestionId");
							CommonMessage.debugMsg("mkeyidcheck " + suggestionId);

							if (suggestionId != null && !suggestionId.trim().isEmpty()) {
								mocRfcmst.setRfcmsuggestionid(suggestionId);
							}

							mocRfcmst.setRfcmflid(flid);
							CommonMessage
									.debugMsg("mocRfcmst.getRfcmKeyid() before create: " + mocRfcmst.getRfcmKeyid());

							existMocRfcmst = mocService.createMOC(mocRfcmst, existMocRfcmst, mocRfcBasismst,
									mocTeamConfigmst);

							MocTeamConfigmstNew = mocService.createMOCTeam(MocTeamConfigmstNew, createdBy,
									existMocRfcmst);

							String newMockeyid = existMocRfcmst.getRfcmKeyid();
							mocRfcmst.setRfcmKeyid(newMockeyid);

							CommonMessage.debugMsg("New MOC Created with ID: " + newMockeyid);
							savemsg = "Data saved successfully";

						} else {

							CommonMessage.debugMsg("Updating existing MOC: " + mocRfcmst.getRfcmKeyid());
							// Vignesh 13 may
							// Update CapEx in master table for existing MOC Team save
							if (MocCapex != null) {
								MocCapex = MocCapex.trim();
							}

							if ("Y".equals(MocCapex) || "N".equals(MocCapex)) {
								mocService.updateMocCapex(mocRfcmst.getRfcmKeyid(), MocCapex, user.getUsrm_ccno());
							}
							// Vignesh 13 may
						//	MocTeamConfigmstNew = mocService.createMOCTeam(MocTeamConfigmstNew, createdBy, mocRfcmst);

							// Vignesh 05-Jun-2026
						    // Do not call createMOCTeam for existing MOC.
						    // createMOCTeam deletes/reinserts rows and removes approval status.
						    MocTeamConfigmstNew = mocService.updateMOCTeam(MocTeamConfigmstNew, createdBy, mocRfcmst);
						    
							savemsg = "Data updated successfully";
						}

						String mockey = mocRfcmst.getRfcmKeyid();
						CommonMessage.debugMsg("mockey returned to UI :: " + mockey);

						JSONObject successData = new JSONObject();
						successData.put("msg", savemsg);
						successData.put("keyId", mockey);
						MOCSuccessmsg.put("successData", successData);
						MOCSuccessmsg.put("formClear", false);
						out.print(MOCSuccessmsg.toString());
						out.close();
					}
				}
				else if (title.equals("RequestforChange")) {

					CommonMessage.debugMsg("IN RFC");
					keyId = request.getParameter("MocKeyid");
			
					// Debug all values if duplicate MocKeyid is coming
				
					  String[] mocValues = request.getParameterValues("MocKeyid");
					if (mocValues != null) {
					    for (int i = 0; i < mocValues.length; i++) {
					        CommonMessage.debugMsg("MocKeyid value[" + i + "] = " + mocValues[i]);

					        if (UIUtils.isValidKeyId(mocValues[i])) {
					            keyId = mocValues[i].trim();
					            break;
					        }
					    }
					}

					if (!UIUtils.isValidKeyId(keyId)) {
					    keyId = request.getParameter("txtRfcmKeyid");
					}

					if (!UIUtils.isValidKeyId(keyId)) {
					    keyId = request.getParameter("hdnMocKeyId");
					}

					if (keyId == null) {
					    keyId = "";
					}

					keyId = keyId.trim();
			       
					CommonMessage.debugMsg("MocKeyId************" + keyId);

					if (!UIUtils.isValidKeyId(keyId)) {
					    throw new Exception("MOC KeyId is missing. Request for Change cannot be saved.");
					}
					
					String basisJsonArrnew = request.getParameter("BasisDetails");
					MocRfcBasismst Employeelink = new MocRfcBasismst();
					JSONArray EmployeeList = null;
					List<MocRfcBasismst> EmployeeAddList = null;

					if (UIUtils.isValidKeyId(basisJsonArrnew)) {
						EmployeeList = JSONArray.fromString(basisJsonArrnew);
						CommonMessage.debugMsg("EmployeeList" + EmployeeList);
						EmployeeAddList = (List<MocRfcBasismst>) UIUtils.convertJSONArrToList(Employeelink,
								EmployeeList);
						// mano
						String othersText = request.getParameter("txtAdditionalNotes");
						if (othersText == null)
							othersText = "";
						othersText = othersText.trim();

						for (int i = 0; i < EmployeeList.length(); i++) {
							JSONObject obj = EmployeeList.getJSONObject(i);
							if ("MOCB0013".equals(obj.optString("hdnRfcbbasisid", ""))) {
								if (EmployeeAddList != null && i < EmployeeAddList.size()) {
									EmployeeAddList.get(i).setRfcbTempfield1(othersText.isEmpty() ? "-" : othersText);
									CommonMessage.debugMsg("Others text set: " + othersText);
								}
							}
						}
						EmployeeAddList = mocService.createBasis(EmployeeAddList, keyId);

						existMocRfcmst = mocService.updateMOC(mocRfcmst, existMocRfcmst, keyId, Nature, Desc, Detail,
								Dmt, Jh, Initiator, MOCTitle, MocType, noOfDays);

						CommonMessage.debugMsg("After Save");
						savemsg = "Data Saved Successfully";
						CommonMessage.debugMsg("After Save" + savemsg);
					}
				}
				/*
				 * else if (title.equals("RequestforChange")) {
				 * 
				 * CommonMessage.debugMsg("IN RFC"); keyId = request.getParameter("MocKeyid");
				 * // keyId=existMocRfcmst.getRfcmKeyid();
				 * CommonMessage.debugMsg("MocKeyId************" + keyId); String
				 * basisJsonArrnew = request.getParameter("BasisDetails"); MocRfcBasismst
				 * Employeelink = new MocRfcBasismst(); JSONArray EmployeeList = null;
				 * List<MocRfcBasismst> EmployeeAddList = null;
				 * 
				 * if (UIUtils.isValidKeyId(basisJsonArrnew)) { EmployeeList =
				 * JSONArray.fromString(basisJsonArrnew); CommonMessage.debugMsg("EmployeeList"
				 * + EmployeeList); EmployeeAddList = (List<MocRfcBasismst>)
				 * UIUtils.convertJSONArrToList(Employeelink, EmployeeList); // mano String
				 * othersText = request.getParameter("txtAdditionalNotes"); if (othersText ==
				 * null) othersText = ""; othersText = othersText.trim();
				 * 
				 * for (int i = 0; i < EmployeeList.length(); i++) { JSONObject obj =
				 * EmployeeList.getJSONObject(i); if
				 * ("MOCB0013".equals(obj.optString("hdnRfcbbasisid", ""))) { if
				 * (EmployeeAddList != null && i < EmployeeAddList.size()) {
				 * EmployeeAddList.get(i).setRfcbTempfield1(othersText.isEmpty() ? "-" :
				 * othersText); CommonMessage.debugMsg("Others text set: " + othersText); } } }
				 * EmployeeAddList = mocService.createBasis(EmployeeAddList, keyId);
				 * 
				 * existMocRfcmst = mocService.updateMOC(mocRfcmst, existMocRfcmst, keyId,
				 * Nature, Desc, Detail, Dmt, Jh, Initiator, MOCTitle, MocType, noOfDays);
				 * 
				 * CommonMessage.debugMsg("After Save"); savemsg = "Data Saved Successfully";
				 * CommonMessage.debugMsg("After Save" + savemsg); } }
				 */
				/*
				 * else if (title.equals("RequestforChange")) {
				 * CommonMessage.debugMsg("IN RFC"); String MocKeyidnew =
				 * request.getParameter("MocKeyid"); CommonMessage.debugMsg("MocKeyidnew" +
				 * MocKeyidnew); String basisJsonArrnew = request.getParameter("BasisDetails");
				 * MocRfcBasismst Employeelink = new MocRfcBasismst(); JSONArray EmployeeList =
				 * null; List<MocRfcBasismst> EmployeeAddList = null; if
				 * (UIUtils.isValidKeyId(basisJsonArrnew)) { EmployeeList =
				 * JSONArray.fromString(basisJsonArrnew); CommonMessage.debugMsg("EmployeeList"
				 * + EmployeeList); EmployeeAddList = (List<MocRfcBasismst>)
				 * UIUtils.convertJSONArrToList(Employeelink, EmployeeList);
				 * 
				 * EmployeeAddList = mocService.createBasis(EmployeeAddList, MocKeyidnew); //
				 * existMocRfcmst=mocService.updateMOC(mocRfcmst,existMocRfcmst); //
				 * existMocRfcmst.setRfcmKeyid(MocKeyidnew); existMocRfcmst =
				 * mocService.updateMOC(mocRfcmst, existMocRfcmst, MocKeyidnew, Nature, Desc,
				 * Detail, Dmt, Jh, Initiator, MOCTitle, MocType, noOfDays);
				 * 
				 * // existMocRfcmst=mocService.updateMOC(mocRfcmst,existMocRfcmst,MocKeyidnew);
				 * CommonMessage.debugMsg("After Save"); savemsg = "Data Saved Successfully";
				 * CommonMessage.debugMsg("After Save" + savemsg);
				 * 
				 * } JSONObject successData = new JSONObject(); successData.put("msg", savemsg);
				 * JSONObject returnData = new JSONObject();// returnData.put("successData",
				 * successData); returnData.put("keyId", MocKeyidnew);
				 * returnData.put("formClear", false); //
				 * returnData.put("GenKeyid",existWhatifMst.getWifmKeyid()); //
				 * successData.put("keyId",newwhatifMst.getWifmKeyid());
				 * out.print(returnData.toString()); }
				 */

				else if (title.equals("Questionnaire")) {
					CommonMessage.debugMsg("IN Questionnaire");
					// keyId=mocRfcmst.getRfcmKeyid();

					keyId = request.getParameter("MocKeyid");
					// Debug all values if duplicate MocKeyid is coming
					
					  String[] mocValues = request.getParameterValues("MocKeyid");
					if (mocValues != null) {
					    for (int i = 0; i < mocValues.length; i++) {
					        CommonMessage.debugMsg("MocKeyid value[" + i + "] = " + mocValues[i]);

					        if (UIUtils.isValidKeyId(mocValues[i])) {
					            keyId = mocValues[i].trim();
					            break;
					        }
					    }
					}

					if (!UIUtils.isValidKeyId(keyId)) {
					    keyId = request.getParameter("txtRfcmKeyid");
					}

					if (!UIUtils.isValidKeyId(keyId)) {
					    keyId = request.getParameter("hdnMocKeyId");
					}

					if (keyId == null) {
					    keyId = "";
					}

					keyId = keyId.trim();
			       
					CommonMessage.debugMsg("MocKeyId******" + keyId);
					CommonMessage.debugMsg("MocKeyId" + keyId);
					String paramJsonArrNew = request.getParameter("paramconvert");
					CommonMessage.debugMsg("paramJsonArr" + paramJsonArrNew);
					MocRfQuestions mocRfQuestions = new MocRfQuestions();
					JSONArray EmployeeList = null;
					List<MocRfQuestions> MocRfQuestionsList = null;
					if (UIUtils.isValidKeyId(paramJsonArr)) {
						EmployeeList = JSONArray.fromString(paramJsonArr);
						MocRfQuestionsList = (List<MocRfQuestions>) UIUtils.convertJSONArrToList(mocRfQuestions,
								EmployeeList);
						MocRfQuestionsList = mocService.createMocRfQuestions(MocRfQuestionsList, keyId);
						CommonMessage.debugMsg("After Save");
						savemsg = "Data Saved Successfully";
						CommonMessage.debugMsg("After Save" + savemsg);
					}
				}else if (title.equals("FinalApprovals")) {
					CommonMessage.debugMsg("Final Approvals");
					// keyId=mocRfcmst.getRfcmKeyid();
					// CommonMessage.debugMsg("MocKeyId"+keyId);
					String mocKeyIdFromRequest = request.getParameter("MocKeyid");
					CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest);
					String MOCCompleted = request.getParameter("MOCCompleted");
					CommonMessage.debugMsg("paramJsonArr" + MOCCompleted);

					if (UIUtils.isValidKeyId(MOCCompleted)) {
						String Complete = mocService.getMocCompletionUpdate(keyId);
						CommonMessage.debugMsg("the keyId  Complete" + Complete);
						JSONObject successData = new JSONObject();
						// json.put("Complete", Complete);

						// out.println(json);
						savemsg = "Data Saved Successfully";
						successData.put("msg", savemsg);
						successData.put("keyId", keyId);
						MOCSuccessmsg.put("successData", successData);
						MOCSuccessmsg.put("formClear", false);
						// successData.put("flid", mocRfcmst.getRfcmflid());
						out.print(MOCSuccessmsg.toString());
						out.close();
						CommonMessage.debugMsg("After Save" + savemsg);
					}
				}

				else if (title.equals("MOCCLosure")) {
					CommonMessage.debugMsg("IN MOCCLosure");
					String MocKeyidClosure = request.getParameter("MocKeyid");
					CommonMessage.debugMsg("MocKeyidnew" + MocKeyidClosure);
					String paramJsonArrNew = request.getParameter("paramconvertClosure");
					CommonMessage.debugMsg("paramJsonArr" + paramJsonArrNew);
					MocClosure mocClosure = new MocClosure();
					JSONArray ClosureList = null;
					List<MocClosure> MocClosureList = null;
					if (UIUtils.isValidKeyId(paramJsonArrNew)) {
						CommonMessage.debugMsg("Inside IF Cond 1");
						ClosureList = JSONArray.fromString(paramJsonArrNew);
						CommonMessage.debugMsg("Inside IF Cond");
						MocClosureList = (List<MocClosure>) UIUtils.convertJSONArrToList(mocClosure, ClosureList);
						MocClosureList = mocService.createMocClosureQuestions(MocClosureList, MocKeyidClosure);
						CommonMessage.debugMsg("After Save");
						savemsg = "Data Saved Successfully";
						CommonMessage.debugMsg("After Save" + savemsg);
					}
					JSONObject successData = new JSONObject();
					/*
					 * String mockey=mocRfcmst.getRfcmKeyid();
					 * CommonMessage.debugMsg("mockey::"+mockey);
					 */
					successData.put("msg", savemsg);
					successData.put("keyId", MocKeyidClosure);
					MOCSuccessmsg.put("successData", successData);
					MOCSuccessmsg.put("formClear", false);
					// successData.put("flid", mocRfcmst.getRfcmflid());
					out.print(MOCSuccessmsg.toString());
					out.close();

					String Mockeyid = request.getParameter("MocKeyid");

					String TotalCount = mocService.getPssrReccommendationCount(Mockeyid);
					String CompletedStatus = mocService.getPssrReccommendationCompleted(Mockeyid);
					CommonMessage.debugMsg("TotalCount=" + TotalCount + "CompletedStatus=" + CompletedStatus);

					if (TotalCount.equals(CompletedStatus)) {
						try {
							CommonMessage.debugMsg("Inside Final  Approval Mail Trigger");
							String SuggestionNo = request.getParameter("SuggestionId");
							String Suggestion = request.getParameter("Suggestion");
							String CR = request.getParameter("CR");
							CommonMessage.debugMsg(
									"Suggestion=" + Suggestion + "&SuggestionId=" + SuggestionNo + "&CR=" + CR);
							String MOCtitle = request.getParameter("Title");
							List<String[]> empMailIds = mocService.getPSITeamMailIds(Mockeyid);
							CommonMessage.debugMsg("emailId" + Mockeyid);
							String mailIds = buildToMailIds(empMailIds);
							if (mailIds.isEmpty()) {
								JSONObject succssMsg = new JSONObject();
								glbmsg = "No Valid MailId Found ";
								return;
							}
							SendMailToPSI(request, response, mailIds, SuggestionNo, Suggestion, Mockeyid, MOCtitle, CR);
						} catch (Exception e) {
							CommonMessage.debugMsg("Mail" + e);
						}
					}
				} else if (title.equals("Psschecklist")) {
	
					CommonMessage.debugMsg("inside the pssr:::");
					MocPssrmst newwMocPssrmst = new MocPssrmst();
					// MocPssrdtl mocPssrdtl=new MocPssrdtl();
					String pssrDetails = request.getParameter("pssrconvert");
					CommonMessage.debugMsg("PSSR Convert" + pssrDetails);
					String mockeyId = request.getParameter("MocKeyid");
					CommonMessage.debugMsg("MOC Keyid in PSSE" + mockeyId);
					// String kaizeid = request.getParameter("kaizeid");
				//	KeyId = mocRfcmst.getRfcmKeyid();
					
					keyId = request.getParameter("MocKeyid");
				//	keyId = request.getParameter("mockeyid");
					if (keyId == null || keyId.trim().isEmpty()) {
						keyId = mocRfcmst.getRfcmKeyid();
					}
					newwMocPssrmst = (MocPssrmst) UIUtils.setBeanProperties((Object) newwMocPssrmst, request);
					MocPssrmst existMocPssrmst = (MocPssrmst) httpSession.getAttribute("newwMocPssrmst");
					newwMocPssrmst.setPsrmrfcid(mockeyId);
					newwMocPssrmst.setPsrmCreatedby(user.getUsrm_ccno());
					newwMocPssrmst.setPsrmrfcid(keyId);
					// newwMocPssrmst.setWifmKzbnKeyid(kaizeid);
					List<MocPssrdtl> mocPssrdtlList = null;
					JSONArray pssrdtljson = null;

					if (pssrDetails != null) {
						CommonMessage.debugMsg("insideeee");

						MocPssrdtl mocPssrdtl = new MocPssrdtl();
						MocPssrmst mocPssrmst = new MocPssrmst();
						String createdBy = user.getUsrm_ccno();
						mocPssrdtl.setPsrdPssrmst(mocPssrmst);
						JSONArray Pssrchecklist = null;
						List<MocPssrdtl> MocPssrdtlnew = null;
						if (UIUtils.isValidKeyId(pssrDetails)) {
							CommonMessage.debugMsg("EmployeeList" + pssrDetails);
							Pssrchecklist = JSONArray.fromString(pssrDetails);
							CommonMessage.debugMsg("EmployeeList" + pssrDetails);
							MocPssrdtlnew = (List<MocPssrdtl>) UIUtils.convertJSONArrToList(mocPssrdtl, Pssrchecklist);
							CommonMessage.debugMsg("MocTeamConfigmst ::::" + MocPssrdtlnew);
						}
						String saveMsg;
						boolean insert = true;
						if (!UIUtils.isValidKeyId(newwMocPssrmst.getPsrmKeyid())) {
							CommonMessage.debugMsg("In Side If");
							existMocPssrmst = mocService.createPssrCheckList(newwMocPssrmst, existMocPssrmst, mockeyId);
							MocPssrdtlnew = mocService.createPssrCheckListdtl(MocPssrdtlnew, createdBy, newwMocPssrmst);
							savemsg = "Data Saved Successfully";
						} else {
							insert = true;
							MocPssrdtlnew = mocService.createPssrCheckListdtl(MocPssrdtlnew, createdBy, newwMocPssrmst);
							CommonFunctions.debugMsg("update");
							savemsg = "Data Updated Successfully";
						}
						/*
						 * JSONObject successData = new JSONObject(); successData.put("msg",savemsg);
						 * JSONObject returnData = new JSONObject();// returnData.put("successData",
						 * successData); returnData.put("MocKeyid",keyId);
						 * returnData.put("formClear",false);
						 * //returnData.put("GenKeyid",existWhatifMst.getWifmKeyid());
						 * //successData.put("keyId",newwhatifMst.getWifmKeyid());
						 * out.print(returnData.toString());
						 */
					}
				}
				// *************************************Swetha Change
				// Here*********************//
				/*
				 * else if (title.equals("WhatIf")) {
				 * CommonMessage.debugMsg("inside the What if:::"); WhatifMst newwhatifMst = new
				 * WhatifMst(); WhatifDtl whatifDtl = new WhatifDtl(); // Addtion here - swetha
				 * 
				 * MOCReccommendation mocRecommend = new MOCReccommendation(); String
				 * whatifDetails = request.getParameter("whatifDetails"); String WhatifKeyId =
				 * request.getParameter("WhatifKeyId"); CommonMessage.debugMsg("WhatifKeyId" +
				 * WhatifKeyId); // keyId=mocRfcmst.getRfcmKeyid(); keyId =
				 * request.getParameter("MocKeyid"); keyId = request.getParameter("mockeyid");
				 * if (keyId == null || keyId.trim().isEmpty()) { keyId =
				 * mocRfcmst.getRfcmKeyid(); } CommonMessage.debugMsg("MocKeyId" + keyId);
				 * String kaizeid = request.getParameter("kaizeid"); String CreatedBy =
				 * user.getUsrm_ccno(); CommonMessage.debugMsg("2222222"); newwhatifMst =
				 * (WhatifMst) UIUtils.setBeanProperties((Object) newwhatifMst, request);
				 * WhatifMst existWhatifMst = (WhatifMst)
				 * httpSession.getAttribute("newwhatifMst"); WhatifDtl whatifdtl = (WhatifDtl)
				 * httpSession.getAttribute("newwhatifdtl");
				 * newwhatifMst.setWifmCreatedby(user.getUsrm_ccno());
				 * newwhatifMst.setWifmMocmKeyid(keyId); newwhatifMst.setWifmKzbnKeyid(kaizeid);
				 * List<WhatifDtl> whatifdtlList = null; CommonMessage.debugMsg("33333");
				 * List<MOCReccommendation> mocRecommendList = null; JSONArray whatifdtljson =
				 * null; String elementid = CommonFunctions.getLoginElementId(request);
				 * GenTlActionplanmst newActionplanmst = new GenTlActionplanmst();
				 * GenTlActionplandtl newActionplandtl = new GenTlActionplandtl();
				 * 
				 * GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
				 * .getAttribute("newGenTlActionplandtl"); GenTlActionplanmst
				 * existGenTlActionplanmst = (GenTlActionplanmst) httpSession
				 * .getAttribute("newGenTlActionplanmst"); MOCReccommendation
				 * existMOCReccommendation = (MOCReccommendation) httpSession
				 * .getAttribute("MOCReccommendation");
				 * 
				 * if (UIUtils.isValidKeyId(whatifDetails)) { CommonMessage.debugMsg("4444");
				 * 
				 * whatifdtljson = JSONArray.fromString(whatifDetails);
				 * 
				 * whatifdtlList = (List<WhatifDtl>) UIUtils.convertJSONArrToList(whatifDtl,
				 * whatifdtljson); mocRecommendList = (List<MOCReccommendation>)
				 * UIUtils.convertJSONArrToList(mocRecommend, whatifdtljson); for
				 * (MOCReccommendation r : mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * }
				 * 
				 * if (whatifdtlList != null) { for (int i = 0; i <= whatifdtlList.size() - 1;
				 * i++) { } newwhatifMst.setWhatifDetails(whatifdtlList);
				 * 
				 * } } String saveMsg; boolean insert = true; if
				 * (!UIUtils.isValidKeyId(WhatifKeyId)) { if
				 * (!UIUtils.isValidKeyId(newwhatifMst.getWifmKeyid())) {
				 * CommonMessage.debugMsg("Inside If"); existWhatifMst =
				 * mocService.createWhatif(newwhatifMst, existWhatifMst, keyId);
				 * mocRecommendList = mocService.createReccommendationNew(mocRecommendList,
				 * keyId, kaizeid, existWhatifMst, title); for (MOCReccommendation r :
				 * mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * }
				 * 
				 * if (whatifDetails != null && whatifDetails.length() > 0) {
				 * existGenTlActionplanmst = mocService.createActionPlan(newActionplanmst,
				 * newActionplandtl, keyId, existWhatifMst.getWifmKeyid());
				 * 
				 * }
				 * 
				 * savemsg = "Data Saved Successfully"; } } else {
				 * CommonMessage.debugMsg("Inside else"); String targetDateUpdate =
				 * newActionplandtl.getApldTargetdate(); String statusUpdate =
				 * newActionplandtl.getApldStatus();
				 * 
				 * String responsibilityUpdate = newActionplandtl.getApldResponsibility();
				 * existMOCReccommendation = mocService.UpdateReccommendation(keyId,
				 * WhatifKeyId, targetDateUpdate, statusUpdate, responsibilityUpdate);
				 * 
				 * existWhatifMst = mocService.UpdateWhatif(newwhatifMst, existWhatifMst,
				 * CreatedBy, WhatifKeyId);
				 * 
				 * savemsg = "Data Updated Successfully"; }
				 * 
				 * 
				 * JSONObject successData = new JSONObject(); // String
				 * mockey=mocRfcmst.getRfcmKeyid(); String WifmKeyid =
				 * newwhatifMst.getWifmKeyid(); CommonMessage.debugMsg("mockey::" + keyId);
				 * CommonMessage.debugMsg("whatif keyid" + WifmKeyid); successData.put("msg",
				 * savemsg); successData.put("keyId", keyId); successData.put("WifmKeyid",
				 * WifmKeyid); successData.put("WhatifKeyId", WhatifKeyId);
				 * MOCSuccessmsg.put("successData", successData); MOCSuccessmsg.put("formClear",
				 * false); // successData.put("flid", mocRfcmst.getRfcmflid());
				 * out.print(MOCSuccessmsg.toString()); out.close(); }
				 */

				/*
				 * else if (title.equals("WhatIf")) {
				 * CommonMessage.debugMsg("inside the What if:::"); WhatifMst newwhatifMst = new
				 * WhatifMst(); WhatifDtl whatifDtl = new WhatifDtl(); // Addtion here - swetha
				 * 
				 * MOCReccommendation mocRecommend = new MOCReccommendation(); String
				 * whatifDetails = request.getParameter("whatifDetails"); String WhatifKeyId =
				 * request.getParameter("WhatifKeyId"); CommonMessage.debugMsg("WhatifKeyId" +
				 * WhatifKeyId); // keyId=mocRfcmst.getRfcmKeyid(); keyId =
				 * request.getParameter("MocKeyid"); keyId = request.getParameter("mockeyid");
				 * if (keyId == null || keyId.trim().isEmpty()) { keyId =
				 * mocRfcmst.getRfcmKeyid(); } CommonMessage.debugMsg("MocKeyId" + keyId);
				 * String kaizeid = request.getParameter("kaizeid"); String CreatedBy =
				 * user.getUsrm_ccno(); CommonMessage.debugMsg("2222222"); newwhatifMst =
				 * (WhatifMst) UIUtils.setBeanProperties((Object) newwhatifMst, request);
				 * WhatifMst existWhatifMst = (WhatifMst)
				 * httpSession.getAttribute("newwhatifMst"); WhatifDtl whatifdtl = (WhatifDtl)
				 * httpSession.getAttribute("newwhatifdtl");
				 * newwhatifMst.setWifmCreatedby(user.getUsrm_ccno());
				 * newwhatifMst.setWifmMocmKeyid(keyId); newwhatifMst.setWifmKzbnKeyid(kaizeid);
				 * List<WhatifDtl> whatifdtlList = null; CommonMessage.debugMsg("33333");
				 * List<MOCReccommendation> mocRecommendList = null; JSONArray whatifdtljson =
				 * null; String elementid = CommonFunctions.getLoginElementId(request);
				 * GenTlActionplanmst newActionplanmst = new GenTlActionplanmst();
				 * GenTlActionplandtl newActionplandtl = new GenTlActionplandtl();
				 * 
				 * GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
				 * .getAttribute("newGenTlActionplandtl"); GenTlActionplanmst
				 * existGenTlActionplanmst = (GenTlActionplanmst) httpSession
				 * .getAttribute("newGenTlActionplanmst"); MOCReccommendation
				 * existMOCReccommendation = (MOCReccommendation) httpSession
				 * .getAttribute("MOCReccommendation");
				 * 
				 * if (UIUtils.isValidKeyId(whatifDetails)) { CommonMessage.debugMsg("4444");
				 * 
				 * whatifdtljson = JSONArray.fromString(whatifDetails);
				 * 
				 * whatifdtlList = (List<WhatifDtl>) UIUtils.convertJSONArrToList(whatifDtl,
				 * whatifdtljson); mocRecommendList = (List<MOCReccommendation>)
				 * UIUtils.convertJSONArrToList(mocRecommend, whatifdtljson); for
				 * (MOCReccommendation r : mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * }
				 * 
				 * if (whatifdtlList != null) { for (int i = 0; i <= whatifdtlList.size() - 1;
				 * i++) { } newwhatifMst.setWhatifDetails(whatifdtlList);
				 * 
				 * } } String saveMsg; boolean insert = true; if
				 * (!UIUtils.isValidKeyId(WhatifKeyId)) { if
				 * (!UIUtils.isValidKeyId(newwhatifMst.getWifmKeyid())) {
				 * CommonMessage.debugMsg("Inside If"); existWhatifMst =
				 * mocService.createWhatif(newwhatifMst, existWhatifMst, keyId);
				 * mocRecommendList = mocService.createReccommendationNew(mocRecommendList,
				 * keyId, kaizeid, existWhatifMst, title); //for (WhatifDtl r :
				 * existWhatifMst.getWhatifDetails()) { for(int i
				 * =0;i<existWhatifMst.getWhatifDetails().size();i++) {
				 * 
				 * WhatifDtl r = existWhatifMst.getWhatifDetails().get(i);
				 * 
				 * newActionplanmst = new GenTlActionplanmst(); newActionplandtl = new
				 * GenTlActionplandtl();
				 * 
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(r.getWifdKeyid());
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getWifdRecommentations());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getWifdRecommentations());
				 * 
				 * newActionplandtl.setApldResponsibility(mocRecommendList.get(i).
				 * getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(mocRecommendList.get(i).getMocrTargetDate(
				 * )); newActionplandtl.setApldStatus(mocRecommendList.get(i).getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno()); if (whatifDetails !=
				 * null && whatifDetails.length() > 0) { existGenTlActionplanmst =
				 * mocService.createActionPlan(newActionplanmst, newActionplandtl,
				 * keyId,r.getWifdKeyid());
				 * 
				 * }
				 * 
				 * }
				 * 
				 * 
				 * 
				 * savemsg = "Data Saved Successfully"; } } else {
				 * CommonMessage.debugMsg("Inside else");
				 * 
				 * String targetDateUpdate = newActionplandtl.getApldTargetdate(); String
				 * statusUpdate = newActionplandtl.getApldStatus();
				 * 
				 * String responsibilityUpdate = newActionplandtl.getApldResponsibility();
				 * existMOCReccommendation = mocService.UpdateReccommendation(keyId,
				 * WhatifKeyId, targetDateUpdate, statusUpdate, responsibilityUpdate);
				 * 
				 * existWhatifMst = mocService.UpdateWhatif(newwhatifMst, existWhatifMst,
				 * CreatedBy, WhatifKeyId);
				 * 
				 * savemsg = "Data Updated Successfully"; }
				 * 
				 * 
				 * JSONObject successData = new JSONObject(); successData.put("msg",savemsg);
				 * JSONObject returnData = new JSONObject();// returnData.put("successData",
				 * successData); returnData.put("MocKeyid",keyId);
				 * returnData.put("formClear",false);
				 * //returnData.put("GenKeyid",existWhatifMst.getWifmKeyid());
				 * //successData.put("keyId",newwhatifMst.getWifmKeyid());
				 * out.print(returnData.toString());
				 * 
				 * JSONObject successData = new JSONObject(); // String
				 * mockey=mocRfcmst.getRfcmKeyid(); String WifmKeyid =
				 * newwhatifMst.getWifmKeyid(); CommonMessage.debugMsg("mockey::" + keyId);
				 * CommonMessage.debugMsg("whatif keyid" + WifmKeyid); successData.put("msg",
				 * savemsg); successData.put("keyId", keyId); successData.put("WifmKeyid",
				 * WifmKeyid); successData.put("WhatifKeyId", WhatifKeyId);
				 * MOCSuccessmsg.put("successData", successData); MOCSuccessmsg.put("formClear",
				 * false); // successData.put("flid", mocRfcmst.getRfcmflid());
				 * out.print(MOCSuccessmsg.toString()); out.close(); }
				 */
				
				else if (title.equals("WhatIf")) {
					CommonMessage.debugMsg("inside the What if:::");
					WhatifMst newwhatifMst = new WhatifMst();
					WhatifMst newForMocRecomm = new WhatifMst();
					WhatifDtl whatifDtl = new WhatifDtl();
					// Addtion here - swetha

					MOCReccommendation mocRecommend = new MOCReccommendation();
					String whatifDetails = request.getParameter("whatifDetails");
					String WhatifKeyId = request.getParameter("WhatifKeyId");
					CommonMessage.debugMsg("WhatifKeyId" + WhatifKeyId);
					// keyId=mocRfcmst.getRfcmKeyid();
					keyId = request.getParameter("MocKeyid");
					keyId = request.getParameter("mockeyid");
					if (keyId == null || keyId.trim().isEmpty()) {
						keyId = mocRfcmst.getRfcmKeyid();
					}
					CommonMessage.debugMsg("MocKeyId" + keyId);
					String kaizeid = request.getParameter("kaizeid");
					String CreatedBy = user.getUsrm_ccno();
					CommonMessage.debugMsg("2222222");
					newwhatifMst = (WhatifMst) UIUtils.setBeanProperties((Object) newwhatifMst, request);
					newForMocRecomm = (WhatifMst) UIUtils.setBeanProperties((Object) newwhatifMst, request);
					WhatifMst existWhatifMst = (WhatifMst) httpSession.getAttribute("newwhatifMst");
					WhatifDtl whatifdtl = (WhatifDtl) httpSession.getAttribute("newwhatifdtl");
					newwhatifMst.setWifmCreatedby(user.getUsrm_ccno());
					newwhatifMst.setWifmMocmKeyid(keyId);
					newwhatifMst.setWifmKzbnKeyid(kaizeid);
					List<WhatifDtl> whatifdtlList = null;
					CommonMessage.debugMsg("33333");
					List<MOCReccommendation> mocRecommendList = null;
					JSONArray whatifdtljson = null;
					String elementid = CommonFunctions.getLoginElementId(request);
					GenTlActionplanmst newActionplanmst = new GenTlActionplanmst();
					GenTlActionplandtl newActionplandtl = new GenTlActionplandtl();
					Boolean whatIfVal = false;

					GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
							.getAttribute("newGenTlActionplandtl");
					GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession
							.getAttribute("newGenTlActionplanmst");
					MOCReccommendation existMOCReccommendation = (MOCReccommendation) httpSession
							.getAttribute("MOCReccommendation");

					if (UIUtils.isValidKeyId(whatifDetails)) {
						CommonMessage.debugMsg("4444");

						whatifdtljson = JSONArray.fromString(whatifDetails);

						whatifdtlList = (List<WhatifDtl>) UIUtils.convertJSONArrToList(whatifDtl, whatifdtljson);
						mocRecommendList = (List<MOCReccommendation>) UIUtils.convertJSONArrToList(mocRecommend,
								whatifdtljson);
						for (MOCReccommendation r : mocRecommendList) {

							newActionplanmst.setAplmMasterrefid(keyId);
							newActionplanmst.setAplmDetailrefid(kaizeid);
							newActionplanmst.setAplmFlid(flid);
							newActionplanmst.setAplmRefdoctype("MOCR");
							newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
							newActionplanmst.setAplmElementid(elementid);
							newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
							newActionplandtl.setApldActionplan(r.getMocrrecmnd());
							newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
							newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
							newActionplandtl.setApldStatus(r.getMocrStatus());
							newActionplandtl.setApldCreatedby(user.getUsrm_ccno());

						}
					
						if (whatifdtlList != null) {
							for (int i = 0; i <= whatifdtlList.size() - 1; i++) 
							{
								if(UIUtils.isValidKeyId (whatifdtlList.get(i).getWifdKeyid()))
								{
									whatIfVal = true;
								}
								
							}
							newwhatifMst.setWhatifDetails(whatifdtlList);
							newForMocRecomm.setWhatifDetails(whatifdtlList);

						}
					}
					String saveMsg;
					boolean insert = true;
					if (!UIUtils.isValidKeyId(WhatifKeyId)) {
						if (!UIUtils.isValidKeyId(newwhatifMst.getWifmKeyid())) {
							CommonMessage.debugMsg("Inside If");
							existWhatifMst = mocService.createWhatif(newwhatifMst, existWhatifMst, keyId);
							mocRecommendList = mocService.createReccommendationNew(mocRecommendList, keyId, kaizeid,
									existWhatifMst, title);
							//for (WhatifDtl r : existWhatifMst.getWhatifDetails()) {
							for(int i =0;i<existWhatifMst.getWhatifDetails().size();i++) {
								 
								WhatifDtl r = existWhatifMst.getWhatifDetails().get(i);
								
								newActionplanmst = new GenTlActionplanmst();
								 newActionplandtl = new GenTlActionplandtl();


								newActionplanmst.setAplmMasterrefid(keyId);
								newActionplanmst.setAplmDetailrefid(r.getWifdKeyid());
								newActionplanmst.setAplmFlid(flid);
								newActionplanmst.setAplmRefdoctype("MOCR");
								newActionplanmst.setAplmMaintask(r.getWifdRecommentations());
								newActionplanmst.setAplmElementid(elementid);
								newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
								newActionplandtl.setApldActionplan(r.getWifdRecommentations());
								
								newActionplandtl.setApldResponsibility(mocRecommendList.get(i).getMocrResponsibility());
								newActionplandtl.setApldTargetdate(mocRecommendList.get(i).getMocrTargetDate());
								newActionplandtl.setApldStatus(mocRecommendList.get(i).getMocrStatus());
								newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
								if (whatifDetails != null && whatifDetails.length() > 0) {
									existGenTlActionplanmst = mocService.createActionPlan(newActionplanmst,
											newActionplandtl, keyId,r.getWifdKeyid());

								}

							}

							

							savemsg = "Data Saved Successfully";
						}
					} else {
						CommonMessage.debugMsg("Inside else");
						
						//existWhatifMst = mocService.UpdateWhatifNew(newwhatifMst, existWhatifMst, CreatedBy, WhatifKeyId,mocRecommendList,flid,elementid);
						existWhatifMst = mocService.UpdateWhatif(newwhatifMst, existWhatifMst,CreatedBy, WhatifKeyId,mocRecommendList,flid,elementid);
						CommonMessage.debugMsg("Before UpdateWhatif");
						savemsg = "Data Updated Successfully";
					}

					/*
					 * JSONObject successData = new JSONObject(); successData.put("msg",savemsg);
					 * JSONObject returnData = new JSONObject();// returnData.put("successData",
					 * successData); returnData.put("MocKeyid",keyId);
					 * returnData.put("formClear",false);
					 * //returnData.put("GenKeyid",existWhatifMst.getWifmKeyid());
					 * //successData.put("keyId",newwhatifMst.getWifmKeyid());
					 * out.print(returnData.toString());
					 */
					JSONObject successData = new JSONObject();
					// String mockey=mocRfcmst.getRfcmKeyid();
					String WifmKeyid = newwhatifMst.getWifmKeyid();
					CommonMessage.debugMsg("mockey::" + keyId);
					CommonMessage.debugMsg("whatif keyid" + WifmKeyid);
					successData.put("msg", savemsg);
					successData.put("keyId", keyId);
					successData.put("WifmKeyid", WifmKeyid);
					successData.put("WhatifKeyId", WhatifKeyId);
					MOCSuccessmsg.put("successData", successData);
					MOCSuccessmsg.put("formClear", false);
					// successData.put("flid", mocRfcmst.getRfcmflid());
					out.print(MOCSuccessmsg.toString());
					out.close();
				}

				/*
				 * else if(title.equals("WhatIf")){
				 * CommonMessage.debugMsg("inside the What if:::"); WhatifMst newwhatifMst=new
				 * WhatifMst(); WhatifDtl whatifDtl=new WhatifDtl(); String whatifDetails =
				 * request.getParameter("whatifDetails"); String
				 * WhatifKeyId=request.getParameter("WhatifKeyId");
				 * CommonMessage.debugMsg("WhatifKeyId"+WhatifKeyId);
				 * //keyId=mocRfcmst.getRfcmKeyid(); keyId = request.getParameter("MocKeyid");
				 * if(keyId == null || keyId.trim().isEmpty()) { keyId =
				 * mocRfcmst.getRfcmKeyid(); } CommonMessage.debugMsg("MocKeyId"+keyId); String
				 * kaizeid = request.getParameter("kaizeid"); String
				 * CreatedBy=user.getUsrm_ccno();
				 * newwhatifMst=(WhatifMst)UIUtils.setBeanProperties((Object)newwhatifMst,
				 * request); WhatifMst
				 * existWhatifMst=(WhatifMst)httpSession.getAttribute("newwhatifMst"); WhatifDtl
				 * whatifdtl=(WhatifDtl)httpSession.getAttribute("newwhatifdtl");
				 * newwhatifMst.setWifmCreatedby(user.getUsrm_ccno());
				 * newwhatifMst.setWifmMocmKeyid(keyId); newwhatifMst.setWifmKzbnKeyid(kaizeid);
				 * List<WhatifDtl> whatifdtlList = null; JSONArray whatifdtljson = null;
				 * 
				 * if(UIUtils.isValidKeyId(whatifDetails) ) { whatifdtljson =
				 * JSONArray.fromString(whatifDetails);
				 * whatifdtlList=(List<WhatifDtl>)UIUtils.convertJSONArrToList(whatifDtl,
				 * whatifdtljson);
				 * 
				 * if(whatifdtlList!= null) { for(int i=0 ;i<=whatifdtlList.size()-1;i++){ }
				 * newwhatifMst.setWhatifDetails(whatifdtlList);
				 * 
				 * } } String saveMsg; boolean insert = true; if(!
				 * UIUtils.isValidKeyId(WhatifKeyId) ){ if( ! UIUtils.isValidKeyId
				 * (newwhatifMst.getWifmKeyid() ) ){ CommonMessage.debugMsg("Inside If");
				 * existWhatifMst=mocService.createWhatif(newwhatifMst,existWhatifMst,keyId);
				 * savemsg="Data Saved Successfully"; } } else{
				 * CommonMessage.debugMsg("Inside else");
				 * existWhatifMst=mocService.UpdateWhatif(newwhatifMst,existWhatifMst,CreatedBy,
				 * WhatifKeyId); savemsg="Data Updated Successfully"; }
				 * 
				 * JSONObject successData = new JSONObject(); successData.put("msg",savemsg);
				 * JSONObject returnData = new JSONObject();// returnData.put("successData",
				 * successData); returnData.put("MocKeyid",keyId);
				 * returnData.put("formClear",false);
				 * //returnData.put("GenKeyid",existWhatifMst.getWifmKeyid());
				 * //successData.put("keyId",newwhatifMst.getWifmKeyid());
				 * out.print(returnData.toString()); JSONObject successData = new JSONObject();
				 * //String mockey=mocRfcmst.getRfcmKeyid(); String
				 * WifmKeyid=newwhatifMst.getWifmKeyid();
				 * CommonMessage.debugMsg("mockey::"+keyId);
				 * CommonMessage.debugMsg("whatif keyid"+WifmKeyid);
				 * successData.put("msg",savemsg); successData.put("keyId",keyId);
				 * successData.put("WifmKeyid", WifmKeyid);
				 * successData.put("WhatifKeyId",WhatifKeyId); MOCSuccessmsg.put("successData",
				 * successData); MOCSuccessmsg.put("formClear",false); //successData.put("flid",
				 * mocRfcmst.getRfcmflid()); out.print(MOCSuccessmsg.toString()); out.close(); }
				 */

				/*
				 * else if (title.equals("Hazop")) { CommonMessage.debugMsg("Inside the hazop");
				 * HazopMst newhazopMst = new HazopMst(); HazopDtl hazopDtl = new HazopDtl();
				 * String hazopDetails = request.getParameter("hazopDetails");
				 * CommonMessage.debugMsg("Hazop Details::" + hazopDetails); String HazopKeyId =
				 * request.getParameter("HazopKeyId"); CommonMessage.debugMsg("HazopKey" +
				 * HazopKeyId); // keyId = request.getParameter("MocKeyid"); //
				 * keyId=mocRfcmst.getRfcmKeyid(); String mocKeyIdFromRequest =
				 * request.getParameter("MocKeyid");
				 * CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest); if
				 * (mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
				 * mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest); }
				 * CommonMessage.debugMsg("MocKeyId" + keyId); String kaizeid =
				 * request.getParameter("kaizeid"); String CreatedBy = user.getUsrm_ccno();
				 * newhazopMst = (HazopMst) UIUtils.setBeanProperties((Object) newhazopMst,
				 * request); HazopMst existhazopMst = (HazopMst)
				 * httpSession.getAttribute("newhazopmst");
				 * newhazopMst.setHzomCreatedby(user.getUsrm_ccno());
				 * newhazopMst.setHzomMocmKeyid(keyId); newhazopMst.setHzomKzbnKeyid(kaizeid);
				 * 
				 * List<HazopDtl> hazopdtlList = null; JSONArray hazopdtljson = null; if
				 * (UIUtils.isValidKeyId(hazopDetails)) { hazopdtljson =
				 * JSONArray.fromString(hazopDetails); hazopdtlList = (List<HazopDtl>)
				 * UIUtils.convertJSONArrToList(hazopDtl, hazopdtljson);
				 * 
				 * if (hazopdtlList != null) { for (int i = 0; i <= hazopdtlList.size() - 1;
				 * i++) {
				 * 
				 * newhazopMst.setHazopDetails(hazopdtlList); } } String saveMsg; boolean insert
				 * = true; if (!UIUtils.isValidKeyId(HazopKeyId)) { if
				 * (!UIUtils.isValidKeyId(newhazopMst.getHzomKeyid())) { existhazopMst =
				 * mocService.createHazop(newhazopMst, existhazopMst, keyId); savemsg =
				 * "Data Saved Successfully"; } } else { // insert = false; existhazopMst =
				 * mocService.UpdateHazop(newhazopMst, existhazopMst, HazopKeyId, CreatedBy);
				 * CommonFunctions.debugMsg("update"); savemsg = "Data Updated Successfully"; }
				 * 
				 * JSONObject successData = new JSONObject(); // String
				 * mockey=mocRfcmst.getRfcmKeyid(); CommonMessage.debugMsg("mockey::" + keyId);
				 * String HzomKeyid = newhazopMst.getHzomKeyid(); successData.put("msg",
				 * savemsg); successData.put("keyId", keyId); successData.put("HzomKeyid",
				 * HzomKeyid); successData.put("HazopKey", HazopKeyId);
				 * MOCSuccessmsg.put("successData", successData); MOCSuccessmsg.put("formClear",
				 * false); // successData.put("flid", mocRfcmst.getRfcmflid());
				 * out.print(MOCSuccessmsg.toString()); out.close(); }
				 * 
				 * }
				 */

				/*
				 * else if (title.equals("Hazop")) { CommonMessage.debugMsg("Inside the hazop");
				 * HazopMst newhazopMst = new HazopMst(); HazopDtl hazopDtl = new HazopDtl();
				 * String hazopDetails = request.getParameter("hazopDetails");
				 * CommonMessage.debugMsg("Hazop Details::" + hazopDetails); String HazopKeyId =
				 * request.getParameter("HazopKeyId"); CommonMessage.debugMsg("HazopKey" +
				 * HazopKeyId); // keyId = request.getParameter("MocKeyid"); //
				 * keyId=mocRfcmst.getRfcmKeyid();
				 * 
				 * //Addtion here - swetha
				 * 
				 * MOCReccommendation mocRecommend = new MOCReccommendation(); String
				 * mocKeyIdFromRequest = request.getParameter("MocKeyid");
				 * CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest); if
				 * (mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
				 * mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest); }
				 * CommonMessage.debugMsg("MocKeyId" + keyId); String kaizeid =
				 * request.getParameter("kaizeid"); String CreatedBy = user.getUsrm_ccno();
				 * newhazopMst = (HazopMst) UIUtils.setBeanProperties((Object) newhazopMst,
				 * request); HazopMst existhazopMst = (HazopMst)
				 * httpSession.getAttribute("newhazopmst");
				 * newhazopMst.setHzomCreatedby(user.getUsrm_ccno());
				 * newhazopMst.setHzomMocmKeyid(keyId); newhazopMst.setHzomKzbnKeyid(kaizeid);
				 * List<MOCReccommendation> mocRecommendList = null;
				 * 
				 * List<HazopDtl> hazopdtlList = null; JSONArray hazopdtljson = null; String
				 * elementid=CommonFunctions.getLoginElementId(request); GenTlActionplanmst
				 * newActionplanmst=new GenTlActionplanmst(); GenTlActionplandtl
				 * newActionplandtl=new GenTlActionplandtl();
				 * 
				 * GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl)
				 * httpSession.getAttribute("newGenTlActionplandtl"); GenTlActionplanmst
				 * existGenTlActionplanmst = (GenTlActionplanmst)
				 * httpSession.getAttribute("newGenTlActionplanmst"); MOCReccommendation
				 * existMOCReccommendation=(MOCReccommendation)httpSession.getAttribute(
				 * "MOCReccommendation"); if (UIUtils.isValidKeyId(hazopDetails)) { hazopdtljson
				 * = JSONArray.fromString(hazopDetails); hazopdtlList = (List<HazopDtl>)
				 * UIUtils.convertJSONArrToList(hazopDtl, hazopdtljson);
				 * 
				 * mocRecommendList =
				 * (List<MOCReccommendation>)UIUtils.convertJSONArrToList(mocRecommend,
				 * hazopdtljson); for(MOCReccommendation r:mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * 
				 * 
				 * 
				 * }
				 * 
				 * if (hazopdtlList != null) { for (int i = 0; i <= hazopdtlList.size() - 1;
				 * i++) {
				 * 
				 * newhazopMst.setHazopDetails(hazopdtlList); } } String saveMsg; boolean insert
				 * = true; if (!UIUtils.isValidKeyId(HazopKeyId)) { if
				 * (!UIUtils.isValidKeyId(newhazopMst.getHzomKeyid())) { existhazopMst =
				 * mocService.createHazop(newhazopMst, existhazopMst, keyId);
				 * mocRecommendList=mocService.createReccommendationNewHazop(mocRecommendList,
				 * keyId,kaizeid,existhazopMst,title); for(MOCReccommendation
				 * r:mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * 
				 * 
				 * 
				 * } if(hazopdtlList!=null && hazopdtlList.size()>0 ){
				 * existGenTlActionplanmst=mocService.createActionPlan(newActionplanmst,
				 * newActionplandtl,keyId,existhazopMst.getHzomKeyid());
				 * 
				 * } savemsg = "Data Saved Successfully"; } } else { // insert = false;
				 * CommonMessage.debugMsg("Inside else"); String targetDateUpdate =
				 * newActionplandtl.getApldTargetdate(); String statusUpdate =
				 * newActionplandtl.getApldStatus();
				 * 
				 * String responsibilityUpdate = newActionplandtl.getApldResponsibility();
				 * existMOCReccommendation=mocService.UpdateReccommendation(keyId,HazopKeyId,
				 * targetDateUpdate,statusUpdate,responsibilityUpdate); existhazopMst =
				 * mocService.UpdateHazop(newhazopMst, existhazopMst, HazopKeyId, CreatedBy);
				 * CommonFunctions.debugMsg("update"); savemsg = "Data Updated Successfully"; }
				 * 
				 * JSONObject successData = new JSONObject(); // String
				 * mockey=mocRfcmst.getRfcmKeyid(); CommonMessage.debugMsg("mockey::" + keyId);
				 * String HzomKeyid = newhazopMst.getHzomKeyid(); successData.put("msg",
				 * savemsg); successData.put("keyId", keyId); successData.put("HzomKeyid",
				 * HzomKeyid); successData.put("HazopKey", HazopKeyId);
				 * MOCSuccessmsg.put("successData", successData); MOCSuccessmsg.put("formClear",
				 * false); // successData.put("flid", mocRfcmst.getRfcmflid());
				 * out.print(MOCSuccessmsg.toString()); out.close(); }
				 * 
				 * }
				 */

				/*
				 * else if (title.equals("Hazop")) { CommonMessage.debugMsg("Inside the hazop");
				 * HazopMst newhazopMst = new HazopMst(); HazopDtl hazopDtl = new HazopDtl();
				 * String hazopDetails = request.getParameter("hazopDetails");
				 * CommonMessage.debugMsg("Hazop Details::" + hazopDetails); String HazopKeyId =
				 * request.getParameter("HazopKeyId"); CommonMessage.debugMsg("HazopKey" +
				 * HazopKeyId); // keyId = request.getParameter("MocKeyid"); //
				 * keyId=mocRfcmst.getRfcmKeyid();
				 * 
				 * // Addtion here - swetha
				 * 
				 * MOCReccommendation mocRecommend = new MOCReccommendation(); String
				 * mocKeyIdFromRequest = request.getParameter("MocKeyid");
				 * CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest); if
				 * (mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
				 * mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest); }
				 * CommonMessage.debugMsg("MocKeyId" + keyId); String kaizeid =
				 * request.getParameter("kaizeid"); String CreatedBy = user.getUsrm_ccno();
				 * newhazopMst = (HazopMst) UIUtils.setBeanProperties((Object) newhazopMst,
				 * request); HazopMst existhazopMst = (HazopMst)
				 * httpSession.getAttribute("newhazopmst");
				 * newhazopMst.setHzomCreatedby(user.getUsrm_ccno());
				 * newhazopMst.setHzomMocmKeyid(keyId); newhazopMst.setHzomKzbnKeyid(kaizeid);
				 * List<MOCReccommendation> mocRecommendList = null;
				 * 
				 * List<HazopDtl> hazopdtlList = null; JSONArray hazopdtljson = null; String
				 * elementid = CommonFunctions.getLoginElementId(request); GenTlActionplanmst
				 * newActionplanmst = new GenTlActionplanmst(); GenTlActionplandtl
				 * newActionplandtl = new GenTlActionplandtl();
				 * 
				 * GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession
				 * .getAttribute("newGenTlActionplandtl"); GenTlActionplanmst
				 * existGenTlActionplanmst = (GenTlActionplanmst) httpSession
				 * .getAttribute("newGenTlActionplanmst"); MOCReccommendation
				 * existMOCReccommendation = (MOCReccommendation) httpSession
				 * .getAttribute("MOCReccommendation"); if (UIUtils.isValidKeyId(hazopDetails))
				 * { hazopdtljson = JSONArray.fromString(hazopDetails); hazopdtlList =
				 * (List<HazopDtl>) UIUtils.convertJSONArrToList(hazopDtl, hazopdtljson);
				 * 
				 * mocRecommendList = (List<MOCReccommendation>)
				 * UIUtils.convertJSONArrToList(mocRecommend, hazopdtljson); for
				 * (MOCReccommendation r : mocRecommendList) {
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMocrrecmnd());
				 * newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
				 * newActionplandtl.setApldStatus(r.getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
				 * 
				 * }
				 * 
				 * if (hazopdtlList != null) { for (int i = 0; i <= hazopdtlList.size() - 1;
				 * i++) {
				 * 
				 * newhazopMst.setHazopDetails(hazopdtlList); } } String saveMsg; boolean insert
				 * = true; if (!UIUtils.isValidKeyId(HazopKeyId)) { if
				 * (!UIUtils.isValidKeyId(newhazopMst.getHzomKeyid())) { existhazopMst =
				 * mocService.createHazop(newhazopMst, existhazopMst, keyId); mocRecommendList =
				 * mocService.createReccommendationNewHazop(mocRecommendList, keyId, kaizeid,
				 * existhazopMst, title); for (int i = 0; i <
				 * existhazopMst.getHazopDetails().size(); i++) // for(MOCReccommendation
				 * r:mocRecommendList) { HazopDtl r = existhazopMst.getHazopDetails().get(i);
				 * newActionplanmst = new GenTlActionplanmst(); newActionplandtl = new
				 * GenTlActionplandtl();
				 * 
				 * newActionplanmst.setAplmMasterrefid(keyId);
				 * newActionplanmst.setAplmDetailrefid(kaizeid);
				 * newActionplanmst.setAplmFlid(flid);
				 * newActionplanmst.setAplmRefdoctype("MOCR");
				 * newActionplanmst.setAplmMaintask(r.getMohdRecommentations());
				 * newActionplanmst.setAplmElementid(elementid);
				 * newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
				 * newActionplandtl.setApldActionplan(r.getMohdRecommentations());
				 * newActionplandtl
				 * .setApldResponsibility(mocRecommendList.get(i).getMocrResponsibility());
				 * newActionplandtl.setApldTargetdate(mocRecommendList.get(i).getMocrTargetDate(
				 * )); newActionplandtl.setApldStatus(mocRecommendList.get(i).getMocrStatus());
				 * newActionplandtl.setApldCreatedby(user.getUsrm_ccno()); if (hazopdtlList !=
				 * null && hazopdtlList.size() > 0) { existGenTlActionplanmst =
				 * mocService.createActionPlan(newActionplanmst, newActionplandtl, keyId,
				 * r.getMohdKeyid());
				 * 
				 * }
				 * 
				 * }
				 * 
				 * savemsg = "Data Saved Successfully"; } } else { // insert = false;
				 * CommonMessage.debugMsg("Inside else"); String targetDateUpdate =
				 * newActionplandtl.getApldTargetdate(); String statusUpdate =
				 * newActionplandtl.getApldStatus();
				 * 
				 * String responsibilityUpdate = newActionplandtl.getApldResponsibility();
				 * existMOCReccommendation = mocService.UpdateReccommendation(keyId, HazopKeyId,
				 * targetDateUpdate, statusUpdate, responsibilityUpdate); existhazopMst =
				 * mocService.UpdateHazop(newhazopMst, existhazopMst, HazopKeyId, CreatedBy);
				 * CommonFunctions.debugMsg("update"); savemsg = "Data Updated Successfully"; }
				 * 
				 * JSONObject successData = new JSONObject(); // String
				 * mockey=mocRfcmst.getRfcmKeyid(); CommonMessage.debugMsg("mockey::" + keyId);
				 * String HzomKeyid = newhazopMst.getHzomKeyid(); successData.put("msg",
				 * savemsg); successData.put("keyId", keyId); successData.put("HzomKeyid",
				 * HzomKeyid); successData.put("HazopKey", HazopKeyId);
				 * MOCSuccessmsg.put("successData", successData); MOCSuccessmsg.put("formClear",
				 * false); // successData.put("flid", mocRfcmst.getRfcmflid());
				 * out.print(MOCSuccessmsg.toString()); out.close(); }
				 * 
				 * }
				 */
				
				else if (title.equals("Hazop")) {
					CommonMessage.debugMsg("Inside the hazop");
					HazopMst newhazopMst = new HazopMst();
					HazopDtl hazopDtl = new HazopDtl();
					String hazopDetails = request.getParameter("hazopDetails");
					CommonMessage.debugMsg("Hazop Details::" + hazopDetails);
					String HazopKeyId = request.getParameter("HazopKeyId");
					CommonMessage.debugMsg("HazopKey" + HazopKeyId);
					// keyId = request.getParameter("MocKeyid");
					// keyId=mocRfcmst.getRfcmKeyid();
					
					//Addtion here - swetha
					
					MOCReccommendation mocRecommend = new MOCReccommendation();
					String mocKeyIdFromRequest = request.getParameter("MocKeyid");
					CommonMessage.debugMsg("mocKeyIdFromRequest :: " + mocKeyIdFromRequest);
					if (mocKeyIdFromRequest != null && !mocKeyIdFromRequest.trim().isEmpty()) {
						mocRfcmst.setRfcmKeyid(mocKeyIdFromRequest);
					}
					CommonMessage.debugMsg("MocKeyId" + keyId);
					String kaizeid = request.getParameter("kaizeid");
					String CreatedBy = user.getUsrm_ccno();
					newhazopMst = (HazopMst) UIUtils.setBeanProperties((Object) newhazopMst, request);
					HazopMst existhazopMst = (HazopMst) httpSession.getAttribute("newhazopmst");
					newhazopMst.setHzomCreatedby(user.getUsrm_ccno());
					newhazopMst.setHzomMocmKeyid(keyId);
					newhazopMst.setHzomKzbnKeyid(kaizeid);
					List<MOCReccommendation> mocRecommendList = null;

					List<HazopDtl> hazopdtlList = null;
					JSONArray hazopdtljson = null;
					String elementid=CommonFunctions.getLoginElementId(request);
		    		GenTlActionplanmst newActionplanmst=new GenTlActionplanmst();
		        	GenTlActionplandtl newActionplandtl=new GenTlActionplandtl();
		    		
		    		GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession.getAttribute("newGenTlActionplandtl");
	        		GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession.getAttribute("newGenTlActionplanmst");
	        		MOCReccommendation existMOCReccommendation=(MOCReccommendation)httpSession.getAttribute("MOCReccommendation");
					if (UIUtils.isValidKeyId(hazopDetails)) {
						hazopdtljson = JSONArray.fromString(hazopDetails);
						hazopdtlList = (List<HazopDtl>) UIUtils.convertJSONArrToList(hazopDtl, hazopdtljson);
						
						mocRecommendList = (List<MOCReccommendation>)UIUtils.convertJSONArrToList(mocRecommend,hazopdtljson);
		    			for(MOCReccommendation r:mocRecommendList) 
					     {
					    	
				        	newActionplanmst.setAplmMasterrefid(keyId);
			        		newActionplanmst.setAplmDetailrefid(kaizeid);
			        		newActionplanmst.setAplmFlid(flid);
			        		newActionplanmst.setAplmRefdoctype("MOCR");
			        		newActionplanmst.setAplmMaintask(r.getMocrrecmnd());
			        		newActionplanmst.setAplmElementid(elementid);
			        		newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
			        		newActionplandtl.setApldActionplan(r.getMocrrecmnd());
			        		newActionplandtl.setApldResponsibility(r.getMocrResponsibility());
			        		newActionplandtl.setApldTargetdate(r.getMocrTargetDate());
			        		newActionplandtl.setApldStatus(r.getMocrStatus());
			        		newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
			        		
				        	
					    	 
					    	 
					     }
						
						if (hazopdtlList != null) {
							for (int i = 0; i <= hazopdtlList.size() - 1; i++) {

								newhazopMst.setHazopDetails(hazopdtlList);
							}
						}
						String saveMsg;
						boolean insert = true;
						if (!UIUtils.isValidKeyId(HazopKeyId)) {
							if (!UIUtils.isValidKeyId(newhazopMst.getHzomKeyid())) {
								existhazopMst = mocService.createHazop(newhazopMst, existhazopMst, keyId);
								mocRecommendList=mocService.createReccommendationNewHazop(mocRecommendList,keyId,kaizeid,existhazopMst,title);
								for(int i =0;i<existhazopMst.getHazopDetails().size();i++) 
							     //for(MOCReccommendation r:mocRecommendList) 
							     {
							    	HazopDtl r = existhazopMst.getHazopDetails().get(i);
									newActionplanmst = new GenTlActionplanmst();
									 newActionplandtl = new GenTlActionplandtl();

						        	newActionplanmst.setAplmMasterrefid(keyId);
					        		newActionplanmst.setAplmDetailrefid(kaizeid);
					        		newActionplanmst.setAplmFlid(flid);
					        		newActionplanmst.setAplmRefdoctype("MOCR");
					        		newActionplanmst.setAplmMaintask(r.getMohdRecommentations());
					        		newActionplanmst.setAplmElementid(elementid);
					        		newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
					        		newActionplandtl.setApldActionplan(r.getMohdRecommentations());
					        		newActionplandtl.setApldResponsibility(mocRecommendList.get(i).getMocrResponsibility());
					        		newActionplandtl.setApldTargetdate(mocRecommendList.get(i).getMocrTargetDate());
					        		newActionplandtl.setApldStatus(mocRecommendList.get(i).getMocrStatus());
					        		newActionplandtl.setApldCreatedby(user.getUsrm_ccno());
					        		if(hazopdtlList!=null && hazopdtlList.size()>0 ){
					      	  			existGenTlActionplanmst=mocService.createActionPlan(newActionplanmst, newActionplandtl,keyId,r.getMohdKeyid());
					      			
					      		    	}
						        	
							    	 
							    	 
							     }
							     
								savemsg = "Data Saved Successfully";
							}
						} else {
							// insert = false;
							
							existhazopMst = mocService.UpdateHazop(newhazopMst, existhazopMst, HazopKeyId, CreatedBy,mocRecommendList,flid,elementid);
							CommonFunctions.debugMsg("update");
							savemsg = "Data Updated Successfully";
						}

						JSONObject successData = new JSONObject();
						// String mockey=mocRfcmst.getRfcmKeyid();
						CommonMessage.debugMsg("mockey::" + keyId);
						String HzomKeyid = newhazopMst.getHzomKeyid();
						successData.put("msg", savemsg);
						successData.put("keyId", keyId);
						successData.put("HzomKeyid", HzomKeyid);
						successData.put("HazopKey", HazopKeyId);
						MOCSuccessmsg.put("successData", successData);
						MOCSuccessmsg.put("formClear", false);
						// successData.put("flid", mocRfcmst.getRfcmflid());
						out.print(MOCSuccessmsg.toString());
						out.close();
					}

				}

				CommonMessage.debugMsg("MocKeyidQuestionnaire" + MocKeyidQuestionnaire);
				JSONObject SuccessData = new JSONObject();
				JSONObject returnData = new JSONObject();
				SuccessData.put("msg", savemsg);
				SuccessData.put("keyId", keyId);
				// SuccessData.put("formClear",false);
				returnData.put("successData", SuccessData);

				// returnData.put("keyId",MocKeyidQuestionnaire);
				returnData.put("formClear", false);
				out.print(returnData.toString());
				out.close();

			}

			catch (ValidationExceptions e) {
				CommonFunctions.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewNearmiss");
				// out.print(errMessage.toString());
			} catch (BusinessApplicationExceptions e) {
				CommonFunctions.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewNearmiss");
				// out.print(errMessage.toString());
			} catch (Exception e) {
				// out.print(e.toString());
				JSONObject err = new JSONObject();
				String errmsg = "Data Not Saved";
				if (e.toString().contains("NEAR_EXIST"))
					errmsg = "Already Exists";
				// err.put("tpmException", errmsg);
				// out.print(err.toString());
			}

		}

	}

	private String buildToMailIds(List<String[]> mailIds) {
		StringBuilder mailIdsStr = new StringBuilder();
		for (String[] mailid : mailIds) {
			if (UIUtils.isValidEmail(mailid[1])) {
				mailIdsStr.append(mailid[1]);
				mailIdsStr.append(',');
			}
		}
		if (mailIdsStr.length() > 0)
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		CommonMessage.debugMsg("buildToMailIds" + mailIdsStr);
		return mailIdsStr.toString();
	}

	@SuppressWarnings("unchecked")
	private void saveQuestionsdata(HttpServletRequest request, HttpServletResponse response, String MocKeyid)
			throws Exception {
		{
			HttpSession httpsession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);

			CommonMessage.debugMsg("MOC Keyid::::" + MocKeyid);
			String saveAlert = "";
			try {
				if (httpsession != null && user != null) {

					String paramJsonArr = request.getParameter("paramconvert");

					MocRfQuestions Employeelink = new MocRfQuestions();
					JSONArray EmployeeList = null;
					List<MocRfQuestions> EmployeeAddList = null;
					if (UIUtils.isValidKeyId(paramJsonArr)) {
						EmployeeList = JSONArray.fromString(paramJsonArr);
						CommonMessage.debugMsg("EmployeeList" + EmployeeList);
						EmployeeAddList = (List<MocRfQuestions>) UIUtils.convertJSONArrToList(Employeelink,
								EmployeeList);

						EmployeeAddList = mocService.createMocRfQuestions(EmployeeAddList, MocKeyid);
						String mockey = Employeelink.getRfcqKeyid();
						CommonMessage.debugMsg("MOC Key in Questions" + mockey);
						CommonMessage.debugMsg("After Save");
						saveAlert = "Data Saved Successfully";
						CommonMessage.debugMsg("After Save" + saveAlert);
					}

					JSONObject SuccessData = new JSONObject();
					String mockey = Employeelink.getRfcqKeyid();
					CommonMessage.debugMsg("mockey::" + mockey);
					SuccessData.put("msg",
							UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save"));

					// SuccessData.put("msg",saveAlert);
					SuccessData.put("formClear", false);
					JSONObject returnData = new JSONObject();
					returnData.put("successData", SuccessData);
					returnData.put("formClear", false);
					out.print(returnData.toString());
					out.close();
				}

			}

			catch (Exception e) {
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
	}

}
