package com.akranta.tpm.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.AbnormalityFormService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.KaizenReportService;
import com.akranta.tpm.service.WorkOrderService;
import com.akranta.tpm.service.impl.AbnormalityFormServiceImpl;
import com.akranta.tpm.service.impl.KaizenReportServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
//import com.akranta.tpm.service.impl.WorkOrderServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.FormModes;

/**
 * Servlet implementation class CommonFilters
 */

public class CommonFiltersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static double imgSizeKB;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	CommonFilterService commonFilterService;
	AbnormalityFormService abnService;
	WorkOrderService workOrderService;
	KaizenReportService kaizenReportService;

	public CommonFiltersServlet() {
		super();
		/*
		 * // TODO Auto-generated constructor stub try { commonFilterService = new
		 * CommonFilterServiceImpl(); } catch (Exception e) { // TODO Auto-generated
		 * catch block //e.printStackTrace(); }
		 */
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {

			String action = UIUtils.getActionPart(request);

			try {
				commonFilterService = (CommonFilterServiceImpl) UIUtils.getServiceObject(request,
						"CommonFilterServiceImpl");
				// workOrderService =
				// (WorkOrderServiceImpl)UIUtils.getServiceObject(request,"WorkOrderServiceImpl");
				kaizenReportService = (KaizenReportServiceImpl) UIUtils.getServiceObject(request,
						"KaizenReportServiceImpl");

				if (action.equals("ImageUpload.commonFilter")) {
					PrintWriter out = response.getWriter();
					String imgPath = UIUtils.getImagePath(request);

					File folder = new File(imgPath);

					if (!folder.exists())
						folder.mkdirs();
					String finalImage = UIUtils.imageUpload(request, imgPath);
					String imagepath = imgPath + finalImage;
					File image = new File(imagepath);
					CommonMessage.debugMsg("imagePath:" + imagepath);
					double bytes = image.length() / 1024;
					CommonMessage.debugMsg("image size in KB:" + bytes);
					imgSizeKB = bytes;
					out.print("tmp/images/" + finalImage);

				}

				CommonFilter commonFilter = new CommonFilter();

				String filter = request.getParameter("q");
				ComboFilter currentFilter = new ComboFilter();
				currentFilter.setCode(filter != null ? filter.toUpperCase() : filter);
				currentFilter.setName(filter != null ? filter.toUpperCase() : filter);

				String flId = request.getParameter("flid");
				String loginFlid = CommonFunctions.getLoginFlid(request);
				if (!UIUtils.isValidKeyId(flId))
					flId = loginFlid;

				if (UIUtils.isValidKeyId(flId)) {
					commonFilter.setFlid(flId);
				}

				String compId = request.getParameter("compId");
				ComboFilter company = null;
				if (UIUtils.isValidKeyId(compId)) {
					company = new ComboFilter();
					company.setId(compId);
					commonFilter.setCompany(company);
				}

				String locnId = request.getParameter("locnId");
				ComboFilter location = null;
				if (UIUtils.isValidKeyId(locnId)) {
					location = new ComboFilter();
					location.setId(locnId);
					commonFilter.setLocation(location);
				}

				String factId = request.getParameter("factId");
				ComboFilter factory = null;
				if (UIUtils.isValidKeyId(factId)) {
					factory = new ComboFilter();
					factory.setId(factId);
					commonFilter.setFactory(factory);

				}
				String sbuId = request.getParameter("sbuId");
				ComboFilter sbu = null;
				if (UIUtils.isValidKeyId(sbuId)) {
					sbu = new ComboFilter();
					sbu.setId(sbuId);
					commonFilter.setSbu(sbu);
				}

				String pbuId = request.getParameter("pbuId");
				ComboFilter pbu = null;
				if (UIUtils.isValidKeyId(pbuId)) {
					pbu = new ComboFilter();
					pbu.setId(pbuId);
					commonFilter.setPbu(pbu);
				}

				String sectId = request.getParameter("sectId");
				ComboFilter section = null;

				if (UIUtils.isValidKeyId(sectId)) {
					section = new ComboFilter();
					section.setId(sectId);
					commonFilter.setSection(section);
				}
				String cellId = request.getParameter("cellId");
				ComboFilter cell = null;
				if (UIUtils.isValidKeyId(cellId)) {
					cell = new ComboFilter();
					cell.setId(cellId);
					commonFilter.setCell(cell);
				}
				String machineId = request.getParameter("machineId");
				ComboFilter machine = null;
				if (!UIUtils.isValidKeyId(machineId))
					machineId = request.getParameter("machId");

				if (UIUtils.isValidKeyId(machineId)) {
					machine = new ComboFilter();
					machine.setId(machineId);
					commonFilter.setMachine(machine);

				}
				String mouldId = request.getParameter("mouldId");
				ComboFilter mould = null;
				if (UIUtils.isValidKeyId(mouldId)) {
					mould = new ComboFilter();
					mould.setId(mouldId);
					CommonMessage.debugMsg("mouldId in CMMM .." + mouldId);
					commonFilter.setMould(mould);
				}

				String costCentreId = request.getParameter("costCentreId");
				ComboFilter costCentre = null;
				if (UIUtils.isValidKeyId(costCentreId)) {
					costCentre = new ComboFilter();
					costCentre.setId(costCentreId);
					commonFilter.setCostCenter(costCentre);
				}

				List<ComboBox> comboList = new ArrayList<ComboBox>();

				String dispatchURL = null;
				/*
				 * if (action.equals("get_inputForm.commonFilter")) { String frmDt =
				 * CommonFunctions.getFirstDateofMonth(0); String dat =
				 * CommonFunctions.getFirstDateofMonth(0); String toDt =
				 * CommonFunctions.getDate(); String frmmonth =
				 * CommonFunctions.getFirstDateofMonth(-5).substring(3,11); String tomonth
				 * =CommonFunctions.getDate().substring(3,11);
				 * 
				 * CommonFilter filterValues = new CommonFilter();
				 * filterValues.setFromDate(frmDt); filterValues.setToDate(toDt);
				 * filterValues.setFromMonth(frmmonth); filterValues.setToMonth(tomonth);
				 * 
				 * request.removeAttribute("filterValues"); request.setAttribute("filterValues",
				 * filterValues); dispatchURL = "/tiles/extras.jsp"; }
				 */
				if (action.equals("get_inputForm.commonFilter")) {
					String frmDt = CommonFunctions.getFirstDateofMonth(0);
					String dat = CommonFunctions.getFirstDateofMonth(0);
					String toDt = CommonFunctions.getDate();
					String frmmonth = CommonFunctions.getFirstDateofMonth(-1).substring(3, 11);
					String tomonth = CommonFunctions.getDate().substring(3, 11);

					CommonFilter filterValues = new CommonFilter();
					filterValues.setFromDate(frmDt);
					filterValues.setToDate(toDt);
					filterValues.setFromMonth(frmmonth);
					filterValues.setToMonth(tomonth);

					request.removeAttribute("filterValues");
					request.setAttribute("filterValues", filterValues);
					dispatchURL = "/tiles/extras.jsp";
				}

				else if (action.equals("EquipmentRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/equipmentRelated.jsp";
				} else if (action.equals("SparesRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/sparesRelated.jsp";
				} else if (action.equals("BDRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/BDRelated.jsp";
				} else if (action.equals("PMRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/PMRelated.jsp";
				} else if (action.equals("breakUp_input.commonFilter")) {
					String fromDate = request.getParameter("fromDate");
					request.setAttribute("FromDate", fromDate);
					String toDate = request.getParameter("toDate");
					request.setAttribute("ToDate", toDate);
					dispatchURL = "/tiles/filter/breakUp.jsp";
				} else if (action.equals("breakUp_getCol.commonFilter")) {
					PrintWriter out = response.getWriter();
					String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BreakUpFilter", "Type");
					out.println(colModel);

				}

				else if (action.equals("frequency.commonFilter")) {
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.FrequencyCombo", "Frequency"));

				} else if (action.equals("activityType_getCol.commonFilter")) {
					PrintWriter out = response.getWriter();
					String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel",
							"actTypeColModel");
					out.println(colModel);

				} else if (action.equals("activityType_getData.commonFilter")) {
					PrintWriter out = response.getWriter();
					JSONObject jsonObject = new JSONObject();
					currentFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> activity = workOrderService.getActivityCombo("", currentFilter);
					List<String[]> activityList = new ArrayList<String[]>();
					String[] lossArr = { "UNPLANNED MAINTENANCE", "JH TAG REMOVAL", "M AND A" };
					for (int i = 0; i < activity.size(); i++) {

						if (UIUtils.isValidKeyId(activity.get(i).getText()) && !activity.get(i).getText().equals("0")) {
							if (activity.get(i).getText().equals("ABNORMALITY")) {
								CommonMessage.debugMsg("if.." + activity.get(i).getText());
								for (int j = 0; j < 3; j++) {
									String[] colName = new String[4];
									colName[0] = " ";
									colName[2] = activity.get(i).getText() + "-" + lossArr[j];
									colName[1] = lossArr[j].substring(0, 1);
									colName[3] = "Loss";
									CommonMessage.debugMsg(".t.." + colName[2]);
									activityList.add(colName);
								}

							} else {
								String[] colName = new String[4];
								CommonMessage.debugMsg("else.." + activity.get(i).getText());
								colName[0] = colName[3] = " ";
								colName[2] = activity.get(i).getText();
								colName[1] = activity.get(i).getId();
								activityList.add(colName);
							}

						}

					}

					jsonObject = convertActivityType(activityList, request, 0, 0, 0, activityList.size());
					out.println(jsonObject);
				}

				else if (action.equals("mocitem.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setMocItem(currentFilter);
					comboList = commonFilterService.getMocItemComboList(commonFilter);

				}

				/* Added BY ManikaNDAN FOR combo pop up */
				else if (action.equals("openComboGrid_input.commonFilter")) {
					String cmbUrl = request.getParameter("cmbUrl");
					String frmName = request.getParameter("frmName");
					String cmbId = request.getParameter("cmbId");
					CommonMessage.debugMsg("cmbUrl :" + cmbUrl);
					request.setAttribute("cmbUrl", cmbUrl);
					request.setAttribute("frmName", frmName);
					request.setAttribute("cmbId", cmbId);
					dispatchURL = "/pages/Gen/loadComboPop.jsp";
				} else if (action.equals("comboGridVw_getCol.commonFilter")) {
					PrintWriter out = response.getWriter();
					String showMode = request.getParameter("mode");
					// currentFilter.setMode(showMode);
					CommonMessage.debugMsg("ShowModeCommonFunctions  " + showMode);
					String url = request.getParameter("cmbUrl");
					UIUtils.forwardRequest(request, response, url);
					String colModel = getComboColmodel();
					// out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel",
					// "comboData"));
				}

				/* End */
				else if (action.equals("AbnormalityRelated.commonFilter")) {
					HttpSession httpSession = request.getSession(false);
					Boolean checkFlag = (Boolean) httpSession.getAttribute("showGen");
					CommonMessage.debugMsg("checkFlag..." + checkFlag);
					abnService = (AbnormalityFormServiceImpl) UIUtils.getServiceObject(request,
							"AbnormalityFormServiceImpl");
					if (checkFlag == null || !checkFlag) {
						String tag = abnService.checkGen();
						if (UIUtils.isValidKeyId(tag)) {
							if (tag.equals("8")) {
								httpSession.setAttribute("showGen", true);
							} else
								httpSession.setAttribute("showGen", false);
						}
					}
					dispatchURL = "/tiles/filter/Abnormality.jsp";
				}

				else if (action.equals("ActionPlanRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/ActionPlan.jsp";
				} else if (action.equals("CalibrationRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/Calibration.jsp";
				} else if (action.equals("JHClitRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/JHCLIT.jsp";
				} else if (action.equals("ManPowerCostRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/manpowerCost.jsp";
				}

				else if (action.equals("CostInformationRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/costInfo.jsp";
				}

				else if (action.equals("ManPowerUtilizationRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/manpowerUtilization.jsp";
				}

				else if (action.equals("OPLAndKaizenRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/OPLAndKaizen.jsp";
				}

				else if (action.equals("PCSRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/PCS.jsp";
				} else if (action.equals("QualityRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/Quality.jsp";
				} else if (action.equals("SafetyRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/Safety.jsp";
				} else if (action.equals("TrainingRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/Training.jsp";
				} else if (action.equals("WoRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/workorder.jsp";
				} else if (action.equals("AuditRelated.commonFilter")) {
					dispatchURL = "/tiles/filter/JhAuditSheet.jsp";
				} else if (action.equals("MasterPlanRelated.commonFilter")) {
					dispatchURL = "tiles/filter/MasterPlanRelated.jsp";
				}

				else if (action.equals("companyCombo.commonFilter")) {
					String keyid = request.getParameter("keyid");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCompany(currentFilter);
					comboList = commonFilterService.getCompanyComboList(commonFilter, keyid);
				} else if (action.equals("comboStatus.commonFilter")) {

					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CompliantGallery", "Type"));

				} else if (action.equals("Order.commonFilter")) {

					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.BreakDown", "Break"));
				}

				else if (action.equals("emplo.commonFilter")) {

					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Kpov", "kpov"));

				} else if (action.equals("comboProduct.commonFilter")) {

					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CompliantGallery", "Product"));

				} else if (action.equals("factroyCombo.commonFilter")) {
					String lcnid = request.getParameter("lcnid");
					String keyid = request.getParameter("keyid");
					String type = request.getParameter("type");
					// ComboFilter unitComboFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("Type:" + type);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setFactory(currentFilter);
					comboList = commonFilterService.getFactoryComboList(commonFilter, keyid, lcnid, type);
					UIUtils.writeComboBox(response, comboList, currentFilter);
				} else if (action.equals("sectionCombo.commonFilter")) {
					// CommonMessage.debugMsg("inisde the section combo");
					currentFilter = UIUtils.fillComboFilter(request);
					HttpSession httpSession = request.getSession(false);
					//// CommonMessage.debugMsg("request.getParameter(formId)>>>"+request.getParameter("formId"));
					//// CommonMessage.debugMsg("request.getParameter(diVID)>>>"+request.getParameter("divId"));
					String frmname = (String) httpSession.getAttribute("functLocHierarchyformId");

					// CommonMessage.debugMsg(commonFilter.getLocation() );
					if (commonFilter.getLocation() == null
							|| !UIUtils.isValidKeyId(commonFilter.getLocation().getId())) {
						String loginloc = CommonFunctions.getLoginLocaton(request);
						if (UIUtils.isValidKeyId(loginloc)) {
							ComboFilter comboFilter = commonFilter.getLocation();
							if (comboFilter == null) {
								comboFilter = new ComboFilter();
							}
							comboFilter.setId(loginloc);
							commonFilter.setLocation(comboFilter);
						}
					}
					commonFilter.setSection(currentFilter);
					if (frmname != null) {
						if (frmname.equals("frmFilter")) {
							commonFilter.setAp(frmname);
						}
					}
					String keyid = request.getParameter("keyid");
					String factoryId = request.getParameter("factoryId");
					comboList = commonFilterService.getSectionComboList(commonFilter, keyid, factoryId);
				} else if (action.equals("FillFlid.commonFilter")) {
					/*
					 * currentFilter=UIUtils.fillComboFilter(request);
					 * commonFilter.setSection(currentFilter);
					 * CommonMessage.debugMsg(" currentFilter  flid " + currentFilter.getCode());
					 * String flid=request.getParameter("flid"); String
					 * level=request.getParameter("level"); comboList =
					 * commonFilterService.getFlidComboList(commonFilter,flid,level);
					 */

					String rolelocn = request.getParameter("rolelocn");
					String flid = request.getParameter("flid");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setUom(currentFilter);
					commonFilter.setType(rolelocn);
					commonFilter.setFlid(flid);
					comboList = commonFilterService.getFlidComboList(commonFilter);

				}

				else if (action.equals("cellCombo.commonFilter")) {
					HttpSession httpSession = request.getSession(false);
					//// CommonMessage.debugMsg("request.getParameter(formId)>>>"+request.getParameter("formId"));
					//// CommonMessage.debugMsg("request.getParameter(diVID)>>>"+request.getParameter("divId"));
					String frmname = (String) httpSession.getAttribute("functLocHierarchyformId");
					// CommonMessage.debugMsg("frmname>>>"+frmname);
					httpSession.getAttribute("functLocHierarchyformId");
					// CommonMessage.debugMsg("request.getParameter(diVID)>>>"+httpSession.getAttribute("functLocHierarchydivId"));
					// CommonMessage.debugMsg("request.getParameter(diVID)>>>"+httpSession.getAttribute("functLocHierarchyformId"));
					String keyid = request.getParameter("keyid");
					String flid = request.getParameter("flId");
					String lineNotToShown = request.getParameter("lineNotToShown");
					String pcsEnabled = request.getParameter("pcsEnabled");
					String sectionid = request.getParameter("sectionid");
					// CommonMessage.debugMsg("pcsEnabled"+pcsEnabled);
					CommonMessage.debugMsg("inside cellcombo.commonfilter flid is:" + flid);
					if (UIUtils.isValidKeyId(lineNotToShown))
						commonFilter.setLineNotToShown(lineNotToShown);
					if (UIUtils.isValidKeyId(pcsEnabled))
						commonFilter.setPcsEnabled(pcsEnabled);
					else
						commonFilter.setPcsEnabled("N");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCell(currentFilter);
					if (frmname != null) {
						if (frmname.equals("frmFilter") || frmname.equals("frmuniqueposition")) {
							commonFilter.setAp(frmname);
						}
					}
					comboList = commonFilterService.getCellComboList(commonFilter, keyid, sectionid, flid);
				}
//			else if( action.equals("machineCombo.commonFilter"))
//			{
//				String machineNotToShown = request.getParameter("machineNotToShown");
//				String keyid=request.getParameter("keyid");
//				String pcsEnabled = request.getParameter("pcsEnabled");
//				/* ----------Added By Manikandan for Landing page-------------*/
//				String empId = request.getParameter("empId");
//				/* ----------Added ByDhanalakshmi.R-------------*/
//				String eqpGrpId=request.getParameter("eqpGrpId");
//				String eqpSubGrpId=request.getParameter("eqpSubGrpId");
//				ComboFilter eqpGrp =null;
//				if( UIUtils.isValidKeyId(eqpGrpId))
//				{	
//					eqpGrp = new ComboFilter();
//					eqpGrp.setId(eqpGrpId);
//					commonFilter.setEqpGroup(eqpGrp);
//				}
//				ComboFilter eqpSubGrp =null;
//				if( UIUtils.isValidKeyId(eqpSubGrpId))
//				{	
//					eqpSubGrp = new ComboFilter();
//					eqpSubGrp.setId(eqpSubGrpId);
//					commonFilter.setEqpSubGroup(eqpSubGrp);
//				}
//				/*------------------------------------------------*/
//				String circleId = request.getParameter("circleId");
//				String cellid=request.getParameter("cellId");
//				String flid=request.getParameter("flid");
//				//CommonMessage.debugMsg("pcsEnabled"+pcsEnabled);
//				//CommonMessage.debugMsg("circleId  "+circleId);
//
//				if( UIUtils.isValidKeyId(circleId) )
//					commonFilter.setStatus(circleId);//FOr sending circle id to serice impl
//				if( UIUtils.isValidKeyId(pcsEnabled) )
//					commonFilter.setPcsEnabled(pcsEnabled);
//				else
//					commonFilter.setPcsEnabled("N");
//				
//				if( UIUtils.isValidKeyId(machineNotToShown) )
//					commonFilter.setMachineNotToShown(machineNotToShown);
//				//CommonMessage.debugMsg(machineNotToShown+"  machineNotToShown   "+commonFilter.getMachineNotToShown());
//				if( UIUtils.isValidKeyId(machineId) )
//					currentFilter.setId(machineId);
//	
//				if(UIUtils.isValidKeyId(empId))
//					commonFilter.setEmpch(empId);
//				if(UIUtils.isValidKeyId(flid))
//					commonFilter.setFlid(flid);
//				currentFilter=UIUtils.fillComboFilter(request);
//				commonFilter.setMachine(currentFilter);
//				CommonMessage.debugMsg(" Inside MachineCombo "+currentFilter);
//							
//				comboList = commonFilterService.getMachineComboList(commonFilter,keyid,cellid);
//			}
				else if (action.equals("machineCombo.commonFilter")) {
					String machineNotToShown = request.getParameter("machineNotToShown");
					String keyid = request.getParameter("keyid");
					String pcsEnabled = request.getParameter("pcsEnabled");
					/* ----------Added By Manikandan for Landing page------------- */
					String empId = request.getParameter("empId");
					/* ----------Added ByDhanalakshmi.R------------- */
					String eqpGrpId = request.getParameter("eqpGrpId");
					String eqpSubGrpId = request.getParameter("eqpSubGrpId");
					ComboFilter eqpGrp = null;
					if (UIUtils.isValidKeyId(eqpGrpId)) {
						eqpGrp = new ComboFilter();
						eqpGrp.setId(eqpGrpId);
						commonFilter.setEqpGroup(eqpGrp);
					}
					ComboFilter eqpSubGrp = null;
					if (UIUtils.isValidKeyId(eqpSubGrpId)) {
						eqpSubGrp = new ComboFilter();
						eqpSubGrp.setId(eqpSubGrpId);
						commonFilter.setEqpSubGroup(eqpSubGrp);
					}

					String circleId = request.getParameter("circleId");
					String cellid = request.getParameter("cellid");
					String flid = request.getParameter("flid");
					// CommonMessage.debugMsg("pcsEnabled"+pcsEnabled);
					// CommonMessage.debugMsg("circleId "+circleId);

					if (UIUtils.isValidKeyId(circleId))
						commonFilter.setStatus(circleId);// FOr sending circle id to serice impl
					if (UIUtils.isValidKeyId(pcsEnabled))
						commonFilter.setPcsEnabled(pcsEnabled);
					else
						commonFilter.setPcsEnabled("N");

					if (UIUtils.isValidKeyId(machineNotToShown))
						commonFilter.setMachineNotToShown(machineNotToShown);
					// CommonMessage.debugMsg(machineNotToShown+" machineNotToShown
					// "+commonFilter.getMachineNotToShown());
					if (UIUtils.isValidKeyId(machineId))
						currentFilter.setId(machineId);

					if (UIUtils.isValidKeyId(empId))
						commonFilter.setEmpch(empId);
					if (UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setMachine(currentFilter);
					CommonMessage.debugMsg(" Inside MachineCombo " + currentFilter);

					comboList = commonFilterService.getMachineComboList(commonFilter, keyid, cellid);
				} else if (action.equals("location.commonFilter")) {
					String keyid = request.getParameter("keyid");
					CommonMessage.debugMsg("KEYID:" + keyid);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setLocation(currentFilter);
					comboList = commonFilterService.getLocationComboList(commonFilter, keyid);
				}

				else if (action.equals("SBU_input.commonFilter")) {

					UIUtils.forwardRequest(request, response, "/pages/SBU.jsp");
				}

				else if (action.equals("costCenter.commonFilter")) {
					// CommonMessage.debugMsg("costCenter.commonFilter =---------");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCostCenter(currentFilter);
					comboList = commonFilterService.getCostCentreComboList(commonFilter);
				} else if (action.equals("employeeFilter.commonFilter")) {
					String empFilter = request.getParameter("empFilter");
					// ComboFilter empFilterComboFilter = new ComboFilter();
					// CommonMessage.debugMsg("---------------------> "+empFilter);
					String rtal = "";
					// CommonMessage.debugMsg("---------inside empFilter------------> "+empFilter);
					/*
					 * StringBuffer cond = new StringBuffer(); String depart =
					 * request.getParameter("deptId"); String trade = request.getParameter("trade");
					 * String loginLocnId = CommonFunctions.getLoginLocaton(request); String role =
					 * request.getParameter("role"); String mgrId = request.getParameter("mgr");
					 * String mgrSql
					 * =" AND  EMPM_KEYID in (select  EEMD_EMPM_KEYID from ENT_TL_EMPMANAGERDTL,ENT_TL_EMPMANAGERMST "
					 * ; mgrSql += " where EEMD_EEMM_KEYID = EEMM_KEYID AND EEMM_MANAGER_ID= '" +
					 * mgrId + "') ";
					 * //cond.append(" AND EMPM_DEPARTMENTID IN   (SELECT DEPT_KEYID   FROM "
					 * +TableNames.TBL_GEN_TL_DEPARTMENTMST);
					 * //cond.append(" WHERE  DEPT_NAME  ='MAINTENANCE') ");
					 * if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(mgrId) &&
					 * UIUtils.isValidKeyId(depart)) cond.append( mgrSql + " AND  EMPM_ROLEID = '" +
					 * role + "'  AND EMPM_DEPARTMENTID = '" + depart + "'") ; else
					 * if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(mgrId)) cond.append(
					 * mgrSql + " AND  EMPM_ROLEID = '" + role + "'") ; else
					 * if(UIUtils.isValidKeyId(depart) && UIUtils.isValidKeyId(mgrId)) cond.append(
					 * mgrSql + " AND EMPM_DEPARTMENTID = '" + depart + "'") ; else
					 * if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(depart))
					 * cond.append(" AND EMPM_DEPARTMENTID = '" + depart + "' AND  EMPM_ROLEID = '"
					 * + role + "'") ; else if(UIUtils.isValidKeyId(role))
					 * cond.append(" AND  EMPM_ROLEID = '" + role + "'"); else
					 * if(UIUtils.isValidKeyId(depart)) cond.append(" AND EMPM_DEPARTMENTID = '" +
					 * depart + "'") ; else if(UIUtils.isValidKeyId(mgrId)) cond.append(mgrSql) ; if
					 * (UIUtils.isValidKeyId(loginLocnId)) cond.append(
					 * "  and EMPM_LOCATION = '"+loginLocnId+"' ");
					 * 
					 * empFilterComboFilter.setCondSql(cond.toString());
					 */
					try {

						/*
						 * empComboFilter=UIUtils.fillComboFilter(request); List<ComboBox> empcomboList
						 * = commonFilterService.getEmployeeComboList(empComboFilter,rtal);
						 * UIUtils.writeComboBox(response, empcomboList ,empComboFilter);
						 */
						String empType = request.getParameter("empType");
						String loginLocnId = CommonFunctions.getLoginLocaton(request);
						String locnid = request.getParameter("locnId");
						if (!UIUtils.isValidKeyId(locnid))
							locnid = loginLocnId;
						ComboFilter empFilterComboFilter = UIUtils.fillComboFilter(request);
						if (!UIUtils.isValidKeyId(empFilterComboFilter.getId()))
							empFilterComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno());
						else
							empFilterComboFilter.setId(
									empFilterComboFilter.getId() + "," + UIUtils.getLoginUser(request).getUsrm_ccno());

						if ("C".equals(empType))
							empFilterComboFilter.setCondSql(" and EMPM_EMPLOYEETYPE = 'C' ");

						empFilterComboFilter.setCondSql(
								empFilterComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");
						List<ComboBox> empfilcomboList = commonFilterService.getEmployeeComboList(empFilterComboFilter,
								rtal);
						UIUtils.writeComboBox(response, empfilcomboList, empFilterComboFilter);

					} catch (Exception e) {

						// e.printStackTrace();
					}
				}

				else if (action.equals("flidEmployeeCombo.commonFilter")) {
					// HttpSession httpSession = request.getSession(false);
					String flid = request.getParameter("flid");
					String roleId = request.getParameter("roleId");
					// String loginLocnId = CommonFunctions.getLoginLocaton(request);
					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

					if (!UIUtils.isValidKeyId(empComboFilter.getCondSql()))
						empComboFilter.setCondSql(" AND 1 = 1 ");

					// CommonMessage.debugMsg("flidddd==="+flid);
					// CommonMessage.debugMsg("roleId==="+roleId);
					// if(UIUtils.isValidKeyId(flid))
					{
						// CommonMessage.debugMsg("roleId2222==="+roleId);
						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();

						strBuf.append("  SELECT DISTINCT EMPM_KEYID ");
						strBuf.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM ");
						strBuf.append("  where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid ");
						if (UIUtils.isValidKeyId(roleId))
							strBuf.append(" and  FRT_ROLE_KEYID = '").append(roleId).append('\'');
						strBuf.append("  AND EMPM_active = 'Y'    ");
						strBuf.append(" AND FRT_FNLN_KEYID IN( ");
						strBuf.append(" SELECT flid FROM gen_mv_flidhierarchy ");
						if (UIUtils.isValidKeyId(flid))
							strBuf.append(" WHERE INSTR (parentflids || '-' || flid, '").append(flid).append("') >0 )");

						cond.append(" AND  EMPM_KEYID IN ( " + strBuf.toString() + " ) ");
						CommonMessage.debugMsg("roleId3333===" + roleId);
						empComboFilter.setCondSql(cond.toString());
					}
					if (!UIUtils.isValidKeyId(empComboFilter.getId()))
						empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno());
					else
						empComboFilter
								.setId(empComboFilter.getId() + "," + UIUtils.getLoginUser(request).getUsrm_ccno());

					String locnid = request.getParameter("locnId");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
					if (!UIUtils.isValidKeyId(locnid))
						locnid = loginLocnId;
					if (UIUtils.isValidKeyId(locnid))
						empComboFilter
								.setCondSql(empComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");

					List<ComboBox> empcomboList = commonFilterService.getEmployeeComboList(empComboFilter, "");
					UIUtils.writeComboBox(response, empcomboList, empComboFilter);

				}

				else if (action.equals("roleBasedEmployeeCombo.commonFilter")) {
					String flid = request.getParameter("flid");
					// String loginLocnId = CommonFunctions.getLoginLocaton(request);
					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

					if (!UIUtils.isValidKeyId(empComboFilter.getCondSql()))
						empComboFilter.setCondSql(" AND 1 = 1 ");

					if (UIUtils.isValidKeyId(flid)) {
						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();

						String childFlids = request.getParameter("childFlids");
						if (UIUtils.isValidKeyId(childFlids) && childFlids.equals("Y")) {
							strBuf.append("  SELECT DISTINCT FRT_EMPM_KEYID ");
							strBuf.append("  from GEN_TL_FNLNROLETEAM, GEN_MV_FLIDHIERARCHY ");
							strBuf.append("  where FRT_FNLN_KEYID = FLID ");
							strBuf.append(" AND POSITION('" + flid + "' IN (PARENTFLIDS||FLID) )>0 ");
						} else {
							strBuf.append("  SELECT DISTINCT FRT_EMPM_KEYID ");
							strBuf.append("  from GEN_TL_MEETINGTYPE_ROLE_MAP, GEN_TL_FNLNROLETEAM ");
							strBuf.append("  where FRT_ROLE_KEYID = MRMP_ROLE_KEYID ");
							strBuf.append(" AND FRT_FNLN_KEYID = " + flid + "' ");
						}

						cond.append(" AND  EMPM_KEYID IN ( " + strBuf.toString() + " ) ");
						cond.append("  AND EMPM_active = 'Y'    ");
						CommonMessage.debugMsg("cond====" + cond.toString());

						empComboFilter.setCondSql(cond.toString());

					}

					if (!UIUtils.isValidKeyId(empComboFilter.getId()))
						empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno());
					else
						empComboFilter
								.setId(empComboFilter.getId() + "," + UIUtils.getLoginUser(request).getUsrm_ccno());

					String locnid = request.getParameter("locnId");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
					if (!UIUtils.isValidKeyId(locnid))
						locnid = loginLocnId;
					CommonMessage.debugMsg("locnid====" + locnid);
					if (UIUtils.isValidKeyId(locnid))
						empComboFilter
								.setCondSql(empComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");

					List<ComboBox> empcomboList = commonFilterService.getEmployeeComboList(empComboFilter, "");
					UIUtils.writeComboBox(response, empcomboList, empComboFilter);

				}

				/*
				 * else if( action.equals("employeeCombo.commonFilter")) { //HttpSession
				 * httpSession = request.getSession(false); //String flid =
				 * request.getParameter("flid"); String locnid = request.getParameter("locnId");
				 * String cellid = request.getParameter("cellId"); String rtal =
				 * request.getParameter("rtal"); //String trade = request.getParameter("trade");
				 * String tradeid = request.getParameter("trade"); String yymode =
				 * request.getParameter("yymode"); String others =
				 * request.getParameter("others"); String loginLocnId =
				 * CommonFunctions.getLoginLocaton(request); if(!UIUtils.isValidKeyId(locnid))
				 * locnid = loginLocnId; ComboFilter empComboFilter =
				 * UIUtils.fillComboFilter(request); if( !UIUtils.isValidKeyId(
				 * empComboFilter.getId()) )
				 * empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno()); else
				 * empComboFilter.setId(empComboFilter.getId()+","+UIUtils.getLoginUser(request)
				 * .getUsrm_ccno());
				 * 
				 * if (!UIUtils.isValidKeyId( empComboFilter.getCondSql()))
				 * empComboFilter.setCondSql(" AND 1 = 1 " );
				 * 
				 * if(UIUtils.isValidKeyId(cellid)) {
				 * 
				 * StringBuffer cond = new StringBuffer(); StringBuffer strBuf = new
				 * StringBuffer();
				 * 
				 * strBuf.append("  SELECT DISTINCT EMPM_KEYID "); strBuf.
				 * append("  from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM "
				 * ); strBuf.
				 * append("  where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and "
				 * ); strBuf.append("  EMPM_active = 'Y'    ");
				 * strBuf.append(" AND FRT_FNLN_KEYID IN( ");
				 * strBuf.append(" SELECT flid FROM gen_mv_flidhierarchy "); strBuf.
				 * append(" WHERE INSTR (parentflids || '-' || flid, (SELECT CELL_FLID FROM GEN_TL_CELLMST WHERE CELL_KEYID = '"
				 * +cellid+"')) >0)"); cond.append(" AND  EMPM_KEYID IN ( " ).append(
				 * strBuf.toString()).append(')');
				 * 
				 * empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
				 * 
				 * }
				 * 
				 * if(!UIUtils.isValidKeyId(locnid)) locnid = loginLocnId;
				 * 
				 * CommonMessage.debugMsg("locnid===="+locnid); if
				 * (UIUtils.isValidKeyId(locnid)) empComboFilter.setCondSql(
				 * empComboFilter.getCondSql() + "  and EMPM_LOCATION = '"+locnid+"' ");
				 * 
				 * List<ComboBox> empcomboList =
				 * commonFilterService.getEmployeeComboList(empComboFilter,rtal);
				 * UIUtils.writeComboBox(response, empcomboList ,empComboFilter);
				 * 
				 * }
				 */

				else if (action.equals("employeeCombo.commonFilter")) {
					// HttpSession httpSession = request.getSession(false);
					// String flid = request.getParameter("flid");
					String locnid = request.getParameter("locnId");
					String cellid = request.getParameter("cellId");
					String rtal = request.getParameter("rtal");
					// String trade = request.getParameter("trade");
					String tradeid = request.getParameter("trade");
					String yymode = request.getParameter("yymode");
					String others = request.getParameter("others");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
					if (!UIUtils.isValidKeyId(locnid))
						locnid = loginLocnId;
					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);
					if (!UIUtils.isValidKeyId(empComboFilter.getId()))
						empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno());
					else
						empComboFilter
								.setId(empComboFilter.getId() + "," + UIUtils.getLoginUser(request).getUsrm_ccno());

					if (!UIUtils.isValidKeyId(empComboFilter.getCondSql()))
						empComboFilter.setCondSql(" AND 1 = 1 ");

					if (UIUtils.isValidKeyId(cellid)) {

						if (UIUtils.isValidKeyId(tradeid) && "Y".equals(yymode) && "N".equals(others)) {
							StringBuilder cond = new StringBuilder();
							StringBuilder strBuf = new StringBuilder();

							strBuf.append(" SELECT DISTINCT empm_keyid ");
							strBuf.append(" FROM gen_tl_employeemst e ");
							// strBuf.append(" JOIN gen_tl_teamtradelink t ON t.frt_empm_keyid =
							// e.empm_keyid ");
							// strBuf.append(" JOIN gen_tl_fnlnroleteam r ON r.frt_keyid = t.frp_frt_keyid
							// ");
							strBuf.append(" JOIN GEN_TL_FNLNROLETEAM R ON R.FRT_EMPM_KEYID = E.EMPM_KEYID ");
							strBuf.append(" JOIN GEN_TL_TEAMTRADELINK T ON T.FRP_FRT_KEYID = R.FRT_KEYID ");
							strBuf.append(" WHERE e.empm_active = 'Y' ");
							strBuf.append(" AND r.frt_fnln_keyid IN ( ");
							strBuf.append(" SELECT FLID FROM  gen_mv_flidhierarchy WHERE FNLN_ORIGINALID = '" + cellid
									+ "') ");
							strBuf.append(" AND FRP_TRADEID= '" + tradeid + "'");
							cond.append(" AND empm_keyid IN (").append(strBuf.toString()).append(")");

							empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						}

						else if (UIUtils.isValidKeyId(tradeid) && "Y".equals(yymode) && "Y".equals(others)) {
							StringBuilder cond = new StringBuilder();
							StringBuilder strBuf = new StringBuilder();

							strBuf.append(" SELECT DISTINCT empm_keyid ");
							strBuf.append(" FROM gen_tl_employeemst e ");
							// strBuf.append(" JOIN gen_tl_teamtradelink t ON t.frt_empm_keyid =
							// e.empm_keyid ");
							// strBuf.append(" JOIN gen_tl_fnlnroleteam r ON r.frt_keyid = t.frp_frt_keyid
							// ");
							strBuf.append(" JOIN GEN_TL_FNLNROLETEAM R ON R.FRT_EMPM_KEYID = E.EMPM_KEYID ");
							strBuf.append(" JOIN GEN_TL_TEAMTRADELINK T ON T.FRP_FRT_KEYID = R.FRT_KEYID ");
							strBuf.append(" WHERE e.empm_active = 'Y' ");
							// strBuf.append(" AND r.frt_fnln_keyid IN ( ");
							// strBuf.append(" SELECT FLID FROM gen_mv_flidhierarchy WHERE FNLN_ORIGINALID =
							// '"+cellid+"') ");
							// strBuf.append(" AND FRP_TRADEID NOT IN ( '"+tradeid+"')");
							cond.append(" AND empm_keyid  IN (").append(strBuf.toString()).append(")");

							empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						} else {
							StringBuilder cond = new StringBuilder();
							StringBuilder strBuf = new StringBuilder();

							strBuf.append(" SELECT DISTINCT empm_keyid ");
							strBuf.append(" FROM gen_tl_employeemst e ");
							// strBuf.append(" JOIN gen_tl_teamtradelink t ON t.frt_empm_keyid =
							// e.empm_keyid ");
							// strBuf.append(" JOIN gen_tl_fnlnroleteam r ON r.frt_keyid = t.frp_frt_keyid
							// ");
							strBuf.append(" JOIN GEN_TL_FNLNROLETEAM R ON R.FRT_EMPM_KEYID = E.EMPM_KEYID ");
							strBuf.append(" WHERE e.empm_active = 'Y' ");
							strBuf.append(" AND r.frt_fnln_keyid IN ( ");
							strBuf.append(" SELECT FLID FROM  gen_mv_flidhierarchy WHERE FNLN_ORIGINALID = '" + cellid
									+ "') ");
							cond.append(" AND empm_keyid IN (").append(strBuf.toString()).append(")");

							empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						}

						/*
						 * StringBuffer cond = new StringBuffer(); StringBuffer strBuf = new
						 * StringBuffer();
						 * 
						 * strBuf.append("  SELECT DISTINCT EMPM_KEYID "); strBuf.
						 * append("  from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM "
						 * ); strBuf.
						 * append("  where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and "
						 * ); strBuf.append("  EMPM_active = 'Y'    ");
						 * strBuf.append(" AND FRT_FNLN_KEYID IN( ");
						 * strBuf.append(" SELECT flid FROM gen_mv_flidhierarchy "); strBuf.
						 * append(" WHERE INSTR (parentflids || '-' || flid, (SELECT CELL_FLID FROM GEN_TL_CELLMST WHERE CELL_KEYID = '"
						 * +cellid+"')) >0)"); cond.append(" AND  EMPM_KEYID IN ( " ).append(
						 * strBuf.toString()).append(')');
						 * 
						 * empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						 */

					}

					if (!UIUtils.isValidKeyId(locnid))
						locnid = loginLocnId;

					CommonMessage.debugMsg("locnid====" + locnid);
					if (UIUtils.isValidKeyId(locnid))
						empComboFilter
								.setCondSql(empComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");

					List<ComboBox> empcomboList = commonFilterService.getEmployeeComboList(empComboFilter, rtal);
					UIUtils.writeComboBox(response, empcomboList, empComboFilter);

				} else if (action.equals("actionplnemployee.commonFilter")) {
					// HttpSession httpSession = request.getSession(false);
					// CommonMessage.debugMsg(" Inside employee::: 2222222");
					String isPbuHead = request.getParameter("isPbuHead");

					// String dept = request.getParameter("dept");
					String trade = request.getParameter("trade");
					String rtal = request.getParameter("rtal");
					String roleId = request.getParameter("roleId");
					String cellid = request.getParameter("cellId");
					String forManagerCombo = request.getParameter("mgr");
					String flid = request.getParameter("flid");
					String locnid = request.getParameter("locnId");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
					String others = request.getParameter("others");
					String loginEmpshow = request.getParameter("loginEmpshow");
					String tradeid = request.getParameter("tradeid");
					String type = request.getParameter("type");
					String pillarid = request.getParameter("pillarid");
					String locationid = request.getParameter("location");

					// if(!UIUtils.isValidKeyId(locnid))
					// locnid = loginLocnId;

					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

					/*
					 * if(!"false".equals(loginEmpshow)){ if( !UIUtils.isValidKeyId(
					 * empComboFilter.getId()) )
					 * empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno()); else
					 * empComboFilter.setId(empComboFilter.getId()+","+UIUtils.getLoginUser(request)
					 * .getUsrm_ccno()); }
					 */

					if (!UIUtils.isValidKeyId(empComboFilter.getCondSql()))
						empComboFilter.setCondSql(" AND 1 = 1 ");

					// if(UIUtils.isValidKeyId(roleId)){
					if (UIUtils.isValidKeyId(tradeid) || UIUtils.isValidKeyId(roleId)) {
						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();
						// strBuf.append(" SELECT DISTINCT EMPM_KEYID from GEN_TL_EMPLOYEEMST where 1 =
						// 1 ");
//					strBuf.append(" SELECT DISTINCT EMPM_KEYID from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM,gen_mv_flidhierarchy ");
//					strBuf.append(" where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and   EMPM_active = 'Y' ");
//
//					if( "Y".equals(others))
//						strBuf.append(" AND (POSITION((SELECT FNLN_KEYID FROM  gen_tl_functionallocn WHERE FNLN_ORIGINALID='"+locationid+"') IN (parentflids|| flid)) >0)  and flid = frt_fnln_keyid");
//					else
//						strBuf.append(" AND  (POSITION('"+flid+"' IN (parentflids|| flid)) >0) and flid = frt_fnln_keyid ");
//					
						strBuf.append(" SELECT DISTINCT EMPM_KEYID ");
						strBuf.append(" FROM GEN_TL_EMPLOYEEMST  ");
						strBuf.append(" JOIN GEN_TL_FNLNROLETEAM  ON FRT_EMPM_KEYID = EMPM_KEYID ");
						strBuf.append(" JOIN GEN_TL_TEAMTRADELINK  ON FRP_FRT_KEYID = FRT_KEYID ");
						strBuf.append(" JOIN gen_mv_flidhierarchy  ON flid = FRT_FNLN_KEYID ");
						strBuf.append(" WHERE EMPM_active = 'Y' ");

						if ("Y".equals(others)) {
							strBuf.append(
									" AND POSITION((SELECT FNLN_KEYID FROM gen_tl_functionallocn WHERE FNLN_ORIGINALID = '"
											+ locationid + "') IN (parentflids || flid)) > 0 ");
						} else {
							strBuf.append(" AND POSITION('" + flid + "' IN (parentflids || flid)) > 0 ");
						}
//					strBuf.append(" SELECT DISTINCT e.EMPM_KEYID ");
//					strBuf.append(" FROM GEN_TL_EMPLOYEEMST e ");
//					strBuf.append(" JOIN GEN_TL_TEAMTRADELINK t ON t.FRT_EMPM_KEYID = e.EMPM_KEYID ");
//					strBuf.append(" JOIN GEN_TL_FNLNROLETEAM r ON r.FRP_FRT_KEYID = t.FRT_KEYID ");
//					strBuf.append(" JOIN gen_mv_flidhierarchy h ON h.flid = t.FRT_FNLN_KEYID ");
//					strBuf.append(" WHERE e.EMPM_active = 'Y' ");
//
//					if ("Y".equals(others)) {
//					    strBuf.append(" AND POSITION((SELECT f.FNLN_KEYID ");
//					    strBuf.append("                 FROM gen_tl_functionallocn f ");
//					    strBuf.append("                WHERE f.FNLN_ORIGINALID = '" + locationid + "') ");
//					    strBuf.append("        IN (h.parentflids || h.flid)) > 0 ");
//					} else {
//					    strBuf.append(" AND POSITION('" + flid + "' IN (h.parentflids || h.flid)) > 0 ");
//					}
//		                   
						if (UIUtils.isValidKeyId(roleId)) {
							strBuf.append(" AND FRT_ROLE_KEYID='" + roleId + "'");
						}

						if (UIUtils.isValidKeyId(tradeid)) {
							strBuf.append(" AND FRP_TRADEID='" + tradeid + "'");
						}

						cond.append(" AND  EMPM_KEYID IN ( ").append(strBuf.toString()).append(')');

						CommonMessage.debugMsg(" Checking 1111 EEEE ");
						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());

					} else if (UIUtils.isValidKeyId(type)) {

						CommonMessage.debugMsg("type===" + type);

						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();
//					strBuf.append(" SELECT DISTINCT EMPM_KEYID text from GEN_TL_EMPLOYEEMST "); 
//					strBuf.append(" ,ADM_tl_rolemst,gen_mv_flidhierarchy,GEN_TL_MOMATTENDANCE,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST where 1 = 1  AND empm_keyid(+) = FRT_EMPM_KEYID");
//					strBuf.append(" AND FRT_ROLE_KEYID = ROLE_KEYID(+)  AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID AND MOMA_MOMS_KEYID = MOMS_KEYID (+)  ");
//					// AND MOMA_MOMS_KEYID(+)='' 
//					if( "Y".equals(others)){
//						CommonMessage.debugMsg("1111");
//						strBuf.append(" AND (POSITION((SELECT FNLN_KEYID FROM  gen_tl_functionallocn WHERE FNLN_ORIGINALID='"+locationid+"') IN (parentflids|| flid)) >0) and flid = frt_fnln_keyid");
//					}
//					else if ("Others".equals(type)){ 
//						CommonMessage.debugMsg("2222");
//						strBuf.append(" AND FLID = '"+flid+"' ");
//					}	
//					else if (!type.equals("Production")){//Production
//						CommonMessage.debugMsg("3333");
//						strBuf.append(" AND (POSITION('"+flid+"' IN (parentflids|| flid)) >0) and flid = frt_fnln_keyid ");
//					}
						strBuf.append(" SELECT DISTINCT EMPM_KEYID ");
						strBuf.append(" FROM GEN_TL_EMPLOYEEMST  ");
						strBuf.append(" JOIN GEN_TL_FNLNROLETEAM  ON FRT_EMPM_KEYID = EMPM_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_TEAMTRADELINK  ON FRT_KEYID = FRP_FRT_KEYID ");
						strBuf.append(" LEFT JOIN ADM_TL_ROLEMST  ON FRT_ROLE_KEYID = ROLE_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_MOMATTENDANCE  ON MOMA_EMPLOYEEID = EMPM_KEYID ");
						strBuf.append(" JOIN gen_mv_flidhierarchy  ON FLID = FRT_FNLN_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_MOMMST  ON MOMA_MOMS_KEYID = MOMS_KEYID ");
						strBuf.append(" WHERE 1=1 ");

						if ("Y".equals(others)) {
							CommonMessage.debugMsg("1111");
							strBuf.append(
									" AND POSITION((SELECT FNLN_KEYID  FROM gen_tl_functionallocn  WHERE FNLN_ORIGINALID='"
											+ locationid + "')  IN (parentflids || flid)) > 0 ");
						} else if ("Others".equals(type)) {
							CommonMessage.debugMsg("2222");
							strBuf.append(" AND flid = '" + flid + "' ");
						} else if (!type.equals("Production")) {
							CommonMessage.debugMsg("3333");
							strBuf.append(" AND POSITION('" + flid + "' IN (parentflids || flid)) > 0 ");
						}

						if (!type.equals("Others") && !type.equals("Dmt") && !type.equals("JH")
								&& !type.equals("Pillar")) {
							// if(type!="JH" && type!="Pillar"){
							CommonMessage.debugMsg("4444");
							strBuf.append(
									" AND ROLE_KEYID IN ( select mrmp_role_keyid from  GEN_TL_MEETINGTYPE_ROLE_MAP where ");
							strBuf.append(" mrmp_meeting_type = upper('" + type
									+ "') AND EMPM_ACTIVE='Y' and mrmp_pillar_id='-') ");
						} else if (type.equals("Pillar")) {
							CommonMessage.debugMsg("5555");
							strBuf.append(
									" AND ROLE_KEYID IN ( select mrmp_role_keyid from  GEN_TL_MEETINGTYPE_ROLE_MAP where ");
							strBuf.append(" mrmp_meeting_type = upper('" + type
									+ "') AND EMPM_ACTIVE='Y' and mrmp_pillar_id='" + pillarid + "')");
						}

						if (UIUtils.isValidKeyId(roleId)) {
							strBuf.append(" AND FRT_ROLE_KEYID='" + roleId + "' AND EMPM_ACTIVE=''Y'' ");
						}

						if (UIUtils.isValidKeyId(tradeid)) {
							strBuf.append(" AND FRP_TRADEID='" + tradeid + "' AND EMPM_ACTIVE=''Y'' ");
						}

						CommonMessage.debugMsg("strBuf.toString()===" + strBuf.toString());

						cond.append(" AND  EMPM_KEYID IN ( ").append(strBuf.toString()).append(')');

						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					} else {
						CommonMessage.debugMsg("2222222233333331111");
						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();
//					strBuf.append(" SELECT DISTINCT EMPM_KEYID text from GEN_TL_EMPLOYEEMST "); 
//					strBuf.append(" ,ADM_tl_rolemst,gen_mv_flidhierarchy,GEN_TL_MOMATTENDANCE,GEN_TL_FNLNROLETEAM,GEN_TL_MOMMST where 1 = 1  AND empm_keyid(+) = FRT_EMPM_KEYID");
//					strBuf.append(" AND FRT_ROLE_KEYID = ROLE_KEYID(+)  AND moma_employeeid (+) = empm_keyid AND FLID = FRT_FNLN_KEYID AND MOMA_MOMS_KEYID = MOMS_KEYID (+)  ");
//					// AND MOMA_MOMS_KEYID(+)='' 
//					if( "Y".equals(others)){
//						CommonMessage.debugMsg("1111");
//						strBuf.append(" AND (POSITION((SELECT FNLN_KEYID FROM  gen_tl_functionallocn WHERE FNLN_ORIGINALID='"+locationid+"') IN (parentflids|| flid)) >0) and flid = frt_fnln_keyid");
//					}
//					else {//Production
//						CommonMessage.debugMsg("3333");
//						strBuf.append(" AND (POSITION('"+flid+"' IN (parentflids|| flid)) >0) and flid = frt_fnln_keyid ");
//					}

						strBuf.append(" SELECT DISTINCT EMPM_KEYID ");
						strBuf.append(" FROM GEN_TL_EMPLOYEEMST  ");
						strBuf.append(" JOIN GEN_TL_FNLNROLETEAM  ON FRT_EMPM_KEYID = EMPM_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_TEAMTRADELINK  ON FRT_KEYID = FRP_FRT_KEYID ");
						strBuf.append(" LEFT JOIN ADM_TL_ROLEMST  ON FRT_ROLE_KEYID = ROLE_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_MOMATTENDANCE  ON MOMA_EMPLOYEEID = EMPM_KEYID ");
						strBuf.append(" JOIN gen_mv_flidhierarchy  ON FLID = FRT_FNLN_KEYID ");
						strBuf.append(" LEFT JOIN GEN_TL_MOMMST  ON MOMA_MOMS_KEYID = MOMS_KEYID ");
						strBuf.append(" WHERE 1=1 ");

						if ("Y".equals(others)) {
							CommonMessage.debugMsg("1111");
							strBuf.append(
									" AND POSITION((SELECT FNLN_KEYID   FROM gen_tl_functionallocn   WHERE FNLN_ORIGINALID = '"
											+ locationid + "')   IN (parentflids || flid)) > 0 ");
						} else { // Production
							CommonMessage.debugMsg("3333");
							strBuf.append(" AND POSITION('" + flid + "' IN (parentflids || flid)) > 0 ");
						}

						cond.append(" AND  EMPM_KEYID IN ( ").append(strBuf.toString()).append(')');

						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());

					}
					/*
					 * else if(UIUtils.isValidKeyId(roleId)){ StringBuffer cond = new
					 * StringBuffer(); StringBuffer strBuf = new StringBuffer(); //strBuf.
					 * append(" SELECT DISTINCT EMPM_KEYID from GEN_TL_EMPLOYEEMST where 1 = 1 ");
					 * strBuf.
					 * append(" SELECT DISTINCT EMPM_KEYID from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM,gen_mv_flidhierarchy "
					 * ); strBuf.
					 * append(" where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and   EMPM_active = 'Y' "
					 * );
					 * 
					 * if( "Y".equals(others)) strBuf.
					 * append(" AND INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn WHERE FNLN_ORIGINALID='||CHR(39) ||vLOCATION ||CHR(39) ||'))>0 and flid = frt_fnln_keyid"
					 * ); else strBuf.
					 * append(" AND ( INSTR (parentflids || '-' || flid, '||CHR(39) ||vFLID||CHR(39) ||' ) >0) and flid = frt_fnln_keyid "
					 * );
					 * 
					 * 
					 * if(UIUtils.isValidKeyId(roleId)){
					 * strBuf.append(" AND FRT_ROLE_KEYID='"+roleId+"' AND EMPM_ACTIVE=''Y'' "); }
					 * 
					 * if(UIUtils.isValidKeyId(tradeid)){
					 * strBuf.append(" AND FRP_TRADEID='"+tradeid+"' AND EMPM_ACTIVE=''Y'' "); }
					 * 
					 * cond.append(" AND  EMPM_KEYID IN ( ").append( strBuf.toString()).append(')');
					 * 
					 * CommonMessage.debugMsg(" Checking 1111 EEEE ");
					 * empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					 * 
					 * }
					 */

					if (UIUtils.isValidKeyId(locnid) && !UIUtils.isValidKeyId(isPbuHead))
						empComboFilter
								.setCondSql(empComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");
					try {

						List<ComboBox> empcomboList = commonFilterService.getEmployeeComboList(empComboFilter, rtal);
						UIUtils.writeComboBox(response, empcomboList, empComboFilter);

					} catch (Exception e) {

						e.printStackTrace();
					}
				} else if (action.equals("employee.commonFilter")) {
					// HttpSession httpSession = request.getSession(false);
					// CommonMessage.debugMsg(" Inside employee::: 2222222");
					String isPbuHead = request.getParameter("isPbuHead");
					String grade = request.getParameter("gradeId");
					// String dept = request.getParameter("dept");
					String trade = request.getParameter("trade");
					String rtal = request.getParameter("rtal");
					String roleId = request.getParameter("roleId");
					String cellid = request.getParameter("cellId");
					String forManagerCombo = request.getParameter("mgr");
					String flid = request.getParameter("flid");
					String locnid = request.getParameter("locnId");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);
					String others = request.getParameter("others");
					String loginEmpshow = request.getParameter("loginEmpshow");
					String locn = request.getParameter("locn");
					String isDMTLeader = request.getParameter("isDmtLeader");
					String DmtId = request.getParameter("DMTID");
					if (!UIUtils.isValidKeyId(locnid))
						locnid = loginLocnId;
					CommonMessage.debugMsg("---------------------> " + roleId);

					ComboFilter empComboFilter = UIUtils.fillComboFilter(request);
					// empComboFilter=UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("ids123...." + empComboFilter.getId() + "--" + loginEmpshow);
					if (!"false".equals(loginEmpshow)) {
						if (!UIUtils.isValidKeyId(empComboFilter.getId()))
							empComboFilter.setId(UIUtils.getLoginUser(request).getUsrm_ccno());
						else
							empComboFilter
									.setId(empComboFilter.getId() + "," + UIUtils.getLoginUser(request).getUsrm_ccno());
					}
					if (!UIUtils.isValidKeyId(empComboFilter.getCondSql()))
						empComboFilter.setCondSql(" AND 1 = 1 ");

					if (UIUtils.isValidKeyId(cellid) && UIUtils.isValidKeyId(trade)) {
						StringBuffer cond = new StringBuffer();
						StringBuffer strBuf = new StringBuffer();

						strBuf.append("  SELECT DISTINCT EMPM_KEYID ");
						strBuf.append("  from GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM ");
						strBuf.append("  where FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid and ");
						strBuf.append("  EMPM_active = 'Y'    ");
						if (!"others".equals(trade))
							strBuf.append(" AND  FRP_TRADEID ='").append(trade).append('\'');

						if ("Y".equals(others))
							strBuf.append(" AND FRT_FNLN_KEYID NOT IN(");
						else
							strBuf.append(" AND FRT_FNLN_KEYID IN(");

						strBuf.append(" SELECT flid FROM gen_mv_flidhierarchy ");
						strBuf.append("WHERE POSITION((SELECT CELL_FLID FROM GEN_TL_CELLMST WHERE CELL_KEYID = '"
								+ cellid + "') IN (parentflids || flid)) > 0 )");
//					strBuf.append(" WHERE INSTR (parentflids || '-' || flid, (SELECT CELL_FLID FROM GEN_TL_CELLMST WHERE CELL_KEYID = '"+cellid+"')) >0)");
						// strBuf.append(" WHERE INSTR (parentflids || '-' || flid, '"+flid+"') >0)");
						cond.append(" AND  EMPM_KEYID IN ( ").append(strBuf.toString()).append(')');

						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					} else if (UIUtils.isValidKeyId(cellid)) {
						StringBuffer cond = new StringBuffer();

						if ("Y".equals(others)) {
							// cond.append(" OR EMPM_KEYID IN(SELECT EMPM_KEYID FROM GEN_TL_EMPLOYEEMST
							// WHERE EMPM_KEYID NOT IN(SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM) ) ");
							cond.append(
									" AND  EMPM_keyid NOT IN (SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where FRT_EMPM_KEYID = empm_keyid and  FRT_FNLN_KEYID IN (SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID = '")
									.append(cellid).append("') )");
						} else if ("Y".equals(locn))
							cond.append(
									" AND  EMPM_keyid NOT IN (SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where FRT_EMPM_KEYID = empm_keyid and empm_location='")
									.append(locnid)
									.append("' and  FRT_FNLN_KEYID IN (SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID = '")
									.append(cellid).append("') )");
						else
							cond.append(
									" AND  EMPM_keyid  IN (SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where FRT_EMPM_KEYID = empm_keyid and  FRT_FNLN_KEYID IN (SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID = '"
											+ cellid + "') )");

						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					} else if (UIUtils.isValidKeyId(trade)) {

						CommonMessage.debugMsg("dept....." + trade);
						StringBuffer cond = new StringBuffer();
						// cond.append(" AND EMPM_DEPARTMENTID IN (SELECT DEPT_KEYID FROM
						// "+TableNames.TBL_GEN_TL_DEPARTMENTMST);
						// cond.append(" WHERE DEPT_NAME ='MAINTENANCE') ");
						// cond.append(" AND EMPM_TRADEID ='"+dept+"' OR EMPM_TRADEID = '{}' ");

						// cond.append(" AND EMPM_TRADEID IN (SELECT NVL(MIN(EMPM_TRADEID),'{}') FROM
						// GEN_TL_EMPLOYEEMST WHERE EMPM_TRADEID ='"+trade+"' )");

						StringBuffer strBuf = new StringBuffer();

						strBuf.append("  SELECT DISTINCT EMPM_KEYID ");
						strBuf.append("  FROM GEN_TL_EMPLOYEEMST,GEN_TL_TEAMTRADELINK,GEN_TL_FNLNROLETEAM ");
						strBuf.append("  WHERE FRP_FRT_KEYID = FRT_KEYID and FRT_EMPM_KEYID = empm_keyid ");
						strBuf.append("  AND EMPM_active = 'Y'    ");
						if (!"others".equals(trade))
							strBuf.append(" AND  FRP_TRADEID ='" + trade + "'");

						if (UIUtils.isValidKeyId(flid)) {
							if ("Y".equals(others))
								strBuf.append(" AND FRT_FNLN_KEYID NOT IN ");
							else
								strBuf.append(" AND FRT_FNLN_KEYID IN ");

							strBuf.append(" ( SELECT flid FROM gen_mv_flidhierarchy ");
							strBuf.append(" WHERE POSITION('" + flid + "' IN (parentflids || flid)) > 0 )");
						}

						cond.append(" AND  EMPM_KEYID IN ( " + strBuf.toString() + " ) ");

						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					}

					if (UIUtils.isValidKeyId(grade)) {
						StringBuffer cond = new StringBuffer();
						cond.append(" AND EMPM_GRADEID IN   ('{}', (SELECT GRDM_KEYID   FROM gen_tl_empgrademst ");
						cond.append(" WHERE GRDM_ACTIVE = 'Y' and grdm_keyid ='" + grade + "') ) ");
						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					} else if (UIUtils.isValidKeyId(forManagerCombo)) {
						CommonMessage.debugMsg("---------inside forManagerCombo------------> " + forManagerCombo);
						StringBuffer cond = new StringBuffer();
						cond.append(" AND EMPM_KEYID in (select EEMM_MANAGER_ID from  ENT_TL_EMPMANAGERMST) ");
						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
					}

					if (!UIUtils.isValidKeyId(isPbuHead) && UIUtils.isValidKeyId(flid)) {
						if (!UIUtils.isValidKeyId(trade)) {
							String otherss = request.getParameter("others");
							StringBuffer cond = new StringBuffer();
							cond.append(
									" AND  EMPM_keyid IN (SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM ");
							// cond.append(" where FRT_EMPM_KEYID = empm_keyid and FRT_FNLN_KEYID =
							// '"+flid+"' )");
							cond.append(" where FRT_EMPM_KEYID = empm_keyid and  FRT_FNLN_KEYID = '" + flid + "'  ");
							if ("Y".equals(otherss)) {
								// cond.append(" OR EMPM_KEYID IN(SELECT EMPM_KEYID FROM GEN_TL_EMPLOYEEMST ");
								cond.append(" UNION SELECT EMPM_KEYID FROM GEN_TL_EMPLOYEEMST ");
								cond.append(" WHERE EMPM_KEYID NOT IN(SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM ");
								cond.append(" 		WHERE FRT_FNLN_KEYID = '" + flid + "' ) ");

							}
							cond.append(" ) ");

							/*
							 * StringBuffer cond = new StringBuffer();
							 * cond.append(" AND  EMPM_keyid IN (SELECT  DISTINCT FRT_EMPM_KEYID from  ");
							 * cond.
							 * append(" GEN_TL_FNLNROLETEAM where FRT_FNLN_KEYID IN (SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID "
							 * ); cond.append(" IN (SELECT PBUT_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID = '"
							 * +flid+"'))  "); cond.
							 * append(" AND FRT_ROLE_KEYID IN(SELECT ROLE_KEYID FROM  ADM_TL_ROLEMST WHERE ROLE_NAME='PBU HEAD')) "
							 * ); empComboFilter.setCondSql(cond.toString());
							 */
							empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						}
					}

					/*
					 * else if(UIUtils.isValidKeyId(flid)) { String otherss =
					 * request.getParameter("others");
					 * CommonMessage.debugMsg("others......"+otherss); StringBuffer cond = new
					 * StringBuffer(); cond.
					 * append(" AND  EMPM_keyid IN (SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM where FRT_EMPM_KEYID = empm_keyid and  FRT_FNLN_KEYID = '"
					 * +flid+"' )");
					 * 
					 * if("Y".equals(otherss)) { cond.
					 * append(" OR EMPM_KEYID IN(SELECT EMPM_KEYID FROM GEN_TL_EMPLOYEEMST WHERE EMPM_KEYID NOT IN(SELECT FRT_EMPM_KEYID FROM GEN_TL_FNLNROLETEAM) ) "
					 * ); } empComboFilter.setCondSql(empComboFilter.getCondSql() +
					 * cond.toString()); }
					 */
					else if (UIUtils.isValidKeyId(roleId)) {
						CommonMessage.debugMsg("---------inside if------------> " + roleId);
						StringBuffer cond = new StringBuffer();
						// cond.append(" AND EMPM_DEPARTMENTID IN (SELECT DEPT_KEYID FROM
						// "+TableNames.TBL_GEN_TL_DEPARTMENTMST);
						// cond.append(" WHERE DEPT_NAME ='MAINTENANCE') ");
						cond.append(" AND  EMPM_ROLEID = '" + roleId + "'");
						empComboFilter.setCondSql(empComboFilter.getCondSql() + cond.toString());
						rtal = rtal + "," + roleId;
					} else if (UIUtils.isValidKeyId(isPbuHead)) {
						StringBuffer cond = new StringBuffer();
						cond.append(" AND  EMPM_keyid IN (SELECT  DISTINCT FRT_EMPM_KEYID from  ");
						cond.append(
								" GEN_TL_FNLNROLETEAM where FRT_FNLN_KEYID IN (SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID ");
						cond.append(" IN (SELECT PBUT_KEYID FROM GEN_VW_FNLN WHERE FNLN_KEYID = '" + flid + "'))  ");
						cond.append(
								" AND FRT_ROLE_KEYID IN(SELECT ROLE_KEYID FROM  ADM_TL_ROLEMST WHERE ROLE_NAME='PBU HEAD')) ");
						empComboFilter.setCondSql(cond.toString());
					} else if (UIUtils.isValidKeyId(isDMTLeader)) {
						StringBuffer cond = new StringBuffer();
						cond.append(" AND  EMPM_keyid IN (SELECT  DISTINCT FRT_EMPM_KEYID from  ");
						cond.append(
								" GEN_TL_FNLNROLETEAM where FRT_FNLN_KEYID IN (SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID ");
						cond.append(" = '" + DmtId + "')  ");
						cond.append(
								" AND FRT_ROLE_KEYID IN(SELECT ROLE_KEYID FROM  ADM_TL_ROLEMST WHERE ROLE_NAME='DMT LEADER')) ");
						empComboFilter.setCondSql(cond.toString());
					}

					if (UIUtils.isValidKeyId(locnid) && !UIUtils.isValidKeyId(isPbuHead))
						empComboFilter
								.setCondSql(empComboFilter.getCondSql() + "  and EMPM_LOCATION = '" + locnid + "' ");
					try {

						// CommonMessage.debugMsg(" Inside employee::: 33333333");

						// ComboFilter currentFilter = new ComboFilter();

//				CommonMessage.debugMsg(" Inside empComboFilter "+empComboFilter.getId());
						List<ComboBox> empcomboList = commonFilterService.getEmployeeComboList(empComboFilter, rtal);
						UIUtils.writeComboBox(response, empcomboList, empComboFilter);

					} catch (Exception e) {

						e.printStackTrace();
					}
				} else if (action.equals("AuditorName.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbAuditorName(currentFilter);
					comboList = commonFilterService.getJhAuditNameComboList(commonFilter);
				} else if (action.equals("AuditorLevel.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbAuditorLevel(currentFilter);
					comboList = commonFilterService.getJhAuditLevelComboList(commonFilter);
				}

				else if (action.equals("uomCombo.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setUom(currentFilter);
					comboList = commonFilterService.getUomComboList(commonFilter);

				} else if (action.equals("assembly.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setAssembly(currentFilter);
					String relatedTo = request.getParameter("relatedto"); // defaullt for machine, MLD for mould
					comboList = commonFilterService.getAssemblyComboList(commonFilter, relatedTo);

				} else if (action.equals("equipmentgroup.commonFilter")) {
					CommonMessage.debugMsg(" Inside Equipment group ");

					String machId = request.getParameter("machId");
					/* Added ByDhanalakshmi.R */
					String eqpSubGrpId = request.getParameter("eqpSubGrpId");
					if (UIUtils.isValidKeyId(eqpSubGrpId)) {
						ComboFilter eqpSubGroup = new ComboFilter();
						eqpSubGroup.setId(eqpSubGrpId);
						commonFilter.setEqpSubGroup(eqpSubGroup);
					}
					/*
					 * --------------- ComboFilter empComboFilter = new ComboFilter();
					 * empComboFilter=UIUtils.fillComboFilter(request);
					 * 
					 * List<ComboBox> empcomboList =
					 * commonFilterService.getEmployeeComboList(empComboFilter,rtal);
					 * UIUtils.writeComboBox(response, empcomboList ,empComboFilter);-------
					 */

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setEqpGroup(currentFilter);
					commonFilter.setMachineId(machId);
					List<ComboBox> eqpcomboList = commonFilterService.getEqpGroupComboList(commonFilter);
					UIUtils.writeComboBox(response, eqpcomboList, commonFilter.getEqpGroup());
				}
				/* Added ByDhanalakshmi.R */
				else if (action.equals("EquipmentSubGroup.commonFilter")) {

					String eqpGrpId = request.getParameter("eqpGrpId");
					String machId = request.getParameter("machId");
					commonFilter.setMachineId(machId);
					if (UIUtils.isValidKeyId(eqpGrpId)) {
						ComboFilter eqpGroup = new ComboFilter();
						eqpGroup.setId(eqpGrpId);
						commonFilter.setEqpGroup(eqpGroup);
					}

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setEqpSubGroup(currentFilter);
					comboList = commonFilterService.getEqpSubGroupComboList(commonFilter);

				}
				/* ---------------------------------------- */
				else if (action.equals("circle.commonFilter")) {

					// commonFilter.setEmployee(currentFilter);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCircle(currentFilter);
					comboList = commonFilterService.getCircleComboList(commonFilter);

					CommonMessage.debugMsg("circleSize..." + comboList.size());
					CommonMessage.debugMsg("circle..." + comboList.toString());
					UIUtils.writeComboBox(response, comboList, currentFilter);
				} else if (action.equals("trade.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setTrade(currentFilter);
					comboList = commonFilterService.getTradeComboList(commonFilter);

				} else if (action.equals("ettrade.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setTrade(currentFilter);
					comboList = commonFilterService.getETTradeComboList(commonFilter);

				} else if (action.equals("machinerank.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setMachineRank(currentFilter);
					comboList = commonFilterService.getMachineRankComboList(commonFilter);

				} else if (action.equals("machinearea.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setMachineArea(currentFilter);
					comboList = commonFilterService.getMachineAreaComboList(commonFilter);

				}

				else if (action.equals("jhAuditLevel.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setJhStep(currentFilter);
					comboList = commonFilterService.getJhAuditLevelIdComboList(commonFilter);

				} else if (action.equals("jhAudit.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setJhStep(currentFilter);
					comboList = commonFilterService.getJhAuditComboList(commonFilter);

				} else if (action.equals("jhstep.commonFilter")) {
					// CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setJhStep(currentFilter);
					comboList = commonFilterService.getJhStepComboList(commonFilter);
				} else if (action.equals("productiongroup.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbprodcngroup(currentFilter);
					comboList = commonFilterService.getProdcngroupComboList(commonFilter);

				} else if (action.equals("oplno.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setOplNoid(currentFilter);
					comboList = commonFilterService.getOplNoComboList(commonFilter);

				} else if (action.equals("kaizenNo.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setJHKaizenNo(currentFilter);
					comboList = commonFilterService.getKaizenNoComboList(commonFilter);

				}
				/*
				 * else if( action.equals("grpcell.commonFilter")) {
				 * //CommonMessage.debugMsg(" :::"+comboList);
				 * 
				 * commonFilter.setGrpByCellid(currentFilter); comboList =
				 * commonFilterService.getGrpByCellComboList(commonFilter); }
				 */
				else if (action.equals("impno.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setImprovmntNoid(currentFilter);
					comboList = commonFilterService.getImprovmntNoComboList(commonFilter);
				} else if (action.equals("kaizenThemecty.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setKznmThemecategoryid(currentFilter);
					comboList = commonFilterService.getKaizenThemeCategory(commonFilter);
				} else if (action.equals("pillar.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("currentFilter" + currentFilter);
					String type = request.getParameter("frmtype");
					if ("MOM".equals(type))
						commonFilter.setType("MOM");// for mom grid pillar code only show
					commonFilter.setPillarid(currentFilter);
					comboList = commonFilterService.getPillarComboList(commonFilter);

				}

				/* CommonFilterServlet */
				/*
				 * else if( action.equals("assemblyCombo.commonFilter")) {
				 * commonFilter.setAssembly(currentFilter); comboList =
				 * commonFilterService.getAssemblyComboList(commonFilter); }
				 */
				else if (action.equals("subassemblyCombo.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSubassembly(currentFilter);
					comboList = commonFilterService.getSubassemblyComboList(commonFilter);
				} else if (action.equals("subcell.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSubcell(currentFilter);
					comboList = commonFilterService.getSubCellComboList(commonFilter);
				} else if (action.equals("spareCombo.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSpare(currentFilter);
					String assmId = request.getParameter("cmbAssmbid");
					if (UIUtils.isValidKeyId(assmId)) {
						ComboFilter assembly = new ComboFilter();
						assembly.setId(assmId);
						commonFilter.setAssembly(assembly);
					}
					comboList = commonFilterService.getSpareComboList(commonFilter);
				} else if (action.equals("shift.commonFilter")) {
					CommonMessage.debugMsg(" Insde sift ");
					ComboFilter shiftcommonFilter = new ComboFilter();
					shiftcommonFilter = UIUtils.fillComboFilter(request);

					String frmRfilter = request.getParameter("frmRfilter");
					commonFilter.setWostatus(frmRfilter);// for indentifying frm related filter
					commonFilter.setShift(shiftcommonFilter);
					// commonFilter=UIUtils.fillComboFilter(request);
					List<ComboBox> shiftcomboList = commonFilterService.getShiftComboList(commonFilter);
					UIUtils.writeComboBox(response, shiftcomboList, shiftcommonFilter);

				} else if (action.equals("oplno.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setOplNoid(currentFilter);
					comboList = commonFilterService.getOplNoComboList(commonFilter);

				} else if (action.equals("failuretype.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbFailureType(currentFilter);
					comboList = commonFilterService.getFailTypeComboList(commonFilter);
				} else if (action.equals("rootcausebd.commonFilter")) {
					// CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbbdRootCause(currentFilter);
					comboList = commonFilterService.getBdRootCauseComboList(commonFilter);

				} else if (action.equals("phenomena.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setPhenomena(currentFilter);
					comboList = commonFilterService.getPhenomenaComboList(commonFilter);
				} else if (action.equals("productiongrpp.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbprodcngroup(currentFilter);
					comboList = commonFilterService.getProdcngroupComboList(commonFilter);
				} else if (action.equals("cause.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					String phenomena = request.getParameter("Phenomena");
					commonFilter.setCmbcause(currentFilter);
					comboList = commonFilterService.getCauseComboList(commonFilter, phenomena);
				} else if (action.equals("shiftincharge.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCmbshiftIncharge(currentFilter);
					comboList = commonFilterService.getShiftInchargeComboList(commonFilter);
				} else if (action.equals("skill.commonFilter")) { // CommonMessage.debugMsg(" Skill :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSkilName(currentFilter);
					comboList = commonFilterService.getSkillComboList(commonFilter);
				} else if (action.equals("skillRating.commonFilter")) { // CommonMessage.debugMsg(" Skill
																		// :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSkilRating(currentFilter);
					comboList = commonFilterService.getSkillRatingComboList(commonFilter);
				} else if (action.equals("topic.commonFilter")) { // CommonMessage.debugMsg(" topic :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setTopic(currentFilter);
					comboList = commonFilterService.getTopicComboList(commonFilter);
				} else if (action.equals("yyy.commonFilter")) { // CommonMessage.debugMsg(" :::"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setYyy(currentFilter);
					comboList = commonFilterService.getProductComboList(commonFilter);

				} else if (action.equals("loss.commonFilter")) {
					CommonMessage.debugMsg("loss :::" + comboList);
					String LossFrom = request.getParameter("LossFrom");
					if ("PCSLogConfig".equals(LossFrom))
						commonFilter.setFromPcs("PCS");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setLossType(currentFilter);
					comboList = commonFilterService.getLossComboList(commonFilter);

				}

				// Training Related Filters

				else if (action.equals("department.commonFilter")) {
					CommonMessage.debugMsg("department:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setDesignation(currentFilter);
					comboList = commonFilterService.getDepartmentCombo(commonFilter);

				} else if (action.equals("PjobReason.commonFilter")) {
					try {
						currentFilter = UIUtils.fillComboFilter(request);
						commonFilter.setReason(currentFilter);
						comboList = commonFilterService.getReason(commonFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("designation.commonFilter")) {
					CommonMessage.debugMsg("designation:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setDesignation(currentFilter);
					comboList = commonFilterService.getDesignationComboList(commonFilter);

				} else if (action.equals("tgtGroup.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					comboList = commonFilterService.getTargetGrpComboList(currentFilter, "");
				} else if (action.equals("roleMst.commonFilter")) {
					CommonMessage.debugMsg("role:::" + comboList);
					/*
					 * currentFilter = UIUtils.fillComboFilter(request);
					 * commonFilter.setRoleId(currentFilter); comboList =
					 * commonFilterService.getRoleComboList(commonFilter);
					 */
					String topicId = request.getParameter("topicId");
					String keyId = request.getParameter("keyId");
					String flid = request.getParameter("flid");
					String type = request.getParameter("type");
					String loginLocnId = CommonFunctions.getLoginLocaton(request);

					CommonMessage.debugMsg(" flid :: Checking 1234 :: CommonFilterServlet :: " + flid);
					if (UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);
					String childFlids = request.getParameter("childFlids");
					commonFilter.setType("Y");
					if (UIUtils.isValidKeyId(childFlids))
						commonFilter.setType(childFlids);

					commonFilter.setKey(keyId);
					commonFilter.setTopicid(topicId);
					commonFilter.setAbnormalityType(type);
					commonFilter.setKK(loginLocnId);

					CommonMessage.debugMsg("flidRoles:::" + commonFilter.getFlid());
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setRoleId(currentFilter);
					comboList = commonFilterService.getRoleComboList(commonFilter);

				} else if (action.equals("program.commonFilter")) {
					CommonMessage.debugMsg("program:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setProgm(currentFilter);
					comboList = commonFilterService.getProgramComboList(commonFilter);

				} else if (action.equals("programBenefit.commonFilter")) {
					CommonMessage.debugMsg("program Benefits:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setPgmbenefit(currentFilter);
					comboList = commonFilterService.getPgmBenefitComboList(commonFilter);

				} else if (action.equals("Batch.commonFilter")) {
					CommonMessage.debugMsg("batch:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setBatch(currentFilter);
					comboList = commonFilterService.getBatchComboList(commonFilter);

				} else if (action.equals("Spoke.commonFilter")) {
					CommonMessage.debugMsg("spoke:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSpoke(currentFilter);
					comboList = commonFilterService.getSpokeComboList(commonFilter);

				} else if (action.equals("Topics.commonFilter")) {
					CommonMessage.debugMsg("topic:::" + comboList);
					String spokeid = request.getParameter("spokeId");
					CommonMessage.debugMsg("spokeid:;" + spokeid);
					String trarkeyid = request.getParameter("trarkeyid");
					CommonMessage.debugMsg("trarkeyid" + trarkeyid);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setTopics(currentFilter);
					comboList = commonFilterService.getTopicsComboList(commonFilter, spokeid, trarkeyid);

				} else if (action.equals("ProgNum.commonFilter")) {
					CommonMessage.debugMsg("ProgNum:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setPgmno(currentFilter);
					comboList = commonFilterService.getPgmnoComboList(commonFilter);

				} else if (action.equals("skillType.commonFilter")) {
					CommonMessage.debugMsg("program:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSkillType(currentFilter);
					comboList = commonFilterService.getskillTypeComboList(commonFilter);

				} else if (action.equals("comboTheme.commonFilter")) {
					try {
						List<ComboBox> course = commonFilterService.getThemeCombo();
						// UIUtils.writeComboBox(response, course);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("combo_Topic.commonFilter")) {
					try {
						currentFilter = UIUtils.fillComboFilter(request);
						currentFilter = UIUtils.fillComboFilter(request);
						commonFilter.setTopics(currentFilter);

						String flid = request.getParameter("flid");
						currentFilter = UIUtils.fillComboFilter(request);
						if (UIUtils.isValidKeyId(flid))
							commonFilter.setFlid(flid);

						comboList = commonFilterService.getTopicCombo(commonFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				else if (action.equals("accidentNo.commonFilter")) {
					CommonMessage.debugMsg("accident:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setIncidentno(currentFilter);
					comboList = commonFilterService.getAccidentNoComboList(commonFilter);

				}

				else if (action.equals("injurytype.commonFilter")) {
					CommonMessage.debugMsg("injury:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setInjuryType(currentFilter);
					comboList = commonFilterService.getInjurymodeComboList(commonFilter);

				} else if (action.equals("bodypart.commonFilter")) {
					CommonMessage.debugMsg("bodypart:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setBodypart(currentFilter);
					comboList = commonFilterService.getBodypartComboList(commonFilter);

				}
				/*
				 * else if(action.equals("employee.commonFilter")) {
				 * CommonMessage.debugMsg(" Inside employee::: 1111111111");
				 * currentFilter=UIUtils.fillComboFilter(request);
				 * commonFilter.setEmployee(currentFilter);
				 * 
				 * 
				 * comboList = commonFilterService.getRecordedbyComboList(commonFilter);
				 * 
				 * }
				 */
				else if (action.equals("manager.commonFilter")) {
					CommonMessage.debugMsg("manager:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setManager(currentFilter);
					comboList = commonFilterService.getManagerComboList(commonFilter);

				}

				// Quality related filters
				else if (action.equals("complaintno.commonFilter")) {
					CommonMessage.debugMsg("complaintno:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setComplaintno(currentFilter);
					comboList = commonFilterService.getComplaintnoComboList(commonFilter);

				} else if (action.equals("custID.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);/** Added by sugunadevi **/
					commonFilter.setCustID(currentFilter);
					comboList = commonFilterService.getCustIDComboList(commonFilter);
					CommonMessage.debugMsg("custID:::" + comboList);
				} else if (action.equals("partnorMst.commonFilter")) {
					String type = request.getParameter("type");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setType(type);
					commonFilter.setCustID(currentFilter);
					comboList = commonFilterService.getPartnoComboList(commonFilter);
					CommonMessage.debugMsg("custID:::" + comboList);
				} else if (action.equals("product.commonFilter")) {

					String param = request.getParameter("machId");
					CommonMessage.debugMsg("product....." + param);
					currentFilter = UIUtils.fillComboFilter(request);
					// commonFilter.setEmployee(currentFilter);
					commonFilter.setproduct(currentFilter);
					// if(param != null && param.length() > 0)
					comboList = commonFilterService.getproductComboList(commonFilter);
				} else if (action.equals("productAll.commonFilter")) {
					String type = request.getParameter("type");
					String charId = request.getParameter("charId");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setproduct(currentFilter);
					commonFilter.setType(type);
					commonFilter.setChartType(charId);
					comboList = commonFilterService.getproductSLAComboList(commonFilter);
				} else if (action.equals("customer.commonFilter")) {
					// ---added by karthikeyan b on 17-oct-2013-------
					// CommonMessage.debugMsg("Customer.commonFilter =---------");
					currentFilter = UIUtils.fillComboFilter(request);/** Added by sugunadevi **/
					commonFilter.setproduct(currentFilter);
					comboList = commonFilterService.getCustomerComboList(commonFilter);
				} else if (action.equals("checkType.commonFilter")) {
					/** Added by sugunadevi **/
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setCheckType(currentFilter);
					comboList = commonFilterService.getCheckType(commonFilter);
				} else if (action.equals("product.commonFilter")) {
					// CommonMessage.debugMsg("costCenter.commonFilter =---------");
					currentFilter = UIUtils.fillComboFilter(request);
					// commonFilter.setEmployee(currentFilter);
					commonFilter.setproduct(currentFilter);
					comboList = commonFilterService.getCostCentreComboList(commonFilter);
				}

				else if (action.equals("supplier.commonFilter")) {
					CommonMessage.debugMsg("supplier:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);/*** Added by Sugunadevi **/
					commonFilter.setSupplier(currentFilter);
					comboList = commonFilterService.getsupplierComboList(commonFilter);
				} else if (action.equals("productModel.commonFilter")) {
					CommonMessage.debugMsg("productModel:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setProductModel(currentFilter);
					comboList = commonFilterService.getproductModelComboList(commonFilter);
				} else if (action.equals("defactparam.commonFilter")) {
					CommonMessage.debugMsg("defactparam:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setDefactparam(currentFilter);
					comboList = commonFilterService.getDefactparamComboList(commonFilter);
				} else if (action.equals("recordedby.commonFilter")) {
					CommonMessage.debugMsg("recordedby:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setRecordedby(currentFilter);
					comboList = commonFilterService.getRecordedbyComboList(commonFilter);
				} else if (action.equals("facultyCombo.commonFilter")) {
					CommonMessage.debugMsg("faculty combo :::");
					if (UIUtils.isValidKeyId(loginFlid)) {
						commonFilter.setFlid(loginFlid);
					}
					currentFilter = UIUtils.fillComboFilter(request);
					String sectid = request.getParameter("sectId");
					if (UIUtils.isValidKeyId(sectid)) {
						commonFilter.setSect(sectid);
					} else {

						String locnid = CommonFunctions.getLoginLocaton(request);
						commonFilter.setAbnAllch(locnid);

					}
					comboList = commonFilterService.getFacultyComboList(commonFilter, currentFilter);
				} else if (action.equals("inspection.commonFilter")) {
					CommonMessage.debugMsg("inspection:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setInspection(currentFilter);
					comboList = commonFilterService.getinspectionComboList(commonFilter);
				}
				
				  else if(action.equals("defphen.commonFilter")) {
				  CommonMessage.debugMsg("defphen:::"+comboList);
				  currentFilter=UIUtils.fillComboFilter(request);
				  commonFilter.setDefactPhenamena(currentFilter); 
				  comboList =commonFilterService.getDefactPhenamenComboList(commonFilter); 
				  }
				 

				
				 

				else if (action.equals("process.commonFilter")) {
					CommonMessage.debugMsg("process:::" + comboList);

					String flid = request.getParameter("flid");
					String originalid = request.getParameter("originalid");
					currentFilter = UIUtils.fillComboFilter(request);

					if (UIUtils.isValidKeyId(flid))
						commonFilter.setFlid(flid);

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setProcess(currentFilter);
					comboList = commonFilterService.getProcessComboList(commonFilter, originalid);
				} else if (action.equals("rawMaterial.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setRawMatrial(currentFilter);
					comboList = commonFilterService.getRawMatrialComboList(commonFilter);
				}

				else if (action.equals("qtmCause.commonFilter")) {
					CommonMessage.debugMsg("cause:::" + comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setQtmCause(currentFilter);
					comboList = commonFilterService.getQtmCauseComboList(commonFilter);
				}

				else if (action.equals("mould.commonFilter")) {
					String mchId = "";
					if (UIUtils.isValidKeyId(request.getParameter("mchId")))
						mchId = request.getParameter("mchId");

					ComboFilter mouldComboFilter = new ComboFilter();
					mouldComboFilter = UIUtils.fillComboFilter(request);
					List<ComboBox> mouldList = commonFilterService.getMould(mchId, mouldComboFilter);
					UIUtils.writeComboBox(response, mouldList, mouldComboFilter);

				} else if (action.equals("breakdown.commonFilter")) {
					List<ComboBox> bdList = commonFilterService.getBreakdown();
					UIUtils.writeComboBox(response, bdList, currentFilter);
				} else if (action.equals("msr.commonFilter")) {
					String activityType = request.getParameter("actType");
					if (UIUtils.isValidKeyId(currentFilter.getName())) {
						List<ComboBox> msrList = commonFilterService.getMSR(currentFilter, activityType);
						UIUtils.writeComboBox(response, msrList, currentFilter);
					}
				}
				/*
				 * else if( action.equals("ImageUpload.commonFilter") ){
				 * 
				 * PrintWriter out = response.getWriter(); String imgPath =
				 * UIUtils.getImagePath(request); HttpSession httpSession =
				 * request.getSession(false); File folder=new File(imgPath); if( !
				 * folder.exists()) folder.mkdirs();
				 * 
				 * String finalImage = UIUtils.imageUpload(request,imgPath); int imageSize =
				 * (Integer) httpSession.getAttribute("imageSize");
				 * CommonMessage.debugMsg(" CommonFunctions :: "+imageSize);
				 * 
				 * out.print("tmp/images/"+finalImage);
				 * 
				 * //JSONObject returnData = new JSONObject(); //returnData.put("tmp/images/",
				 * finalImage); //returnData.put("imageSize", imageSize);
				 * //out.print(returnData.toString());
				 * 
				 * //JSONObject imageInfo = new JSONObject(); //imageInfo.put("imagePath",
				 * "tmp/images/"+finalImage);
				 * 
				 * 
				 * }
				 */
//			else if( action.equals("ImageUpload.commonFilter") ){
//				PrintWriter out = response.getWriter();
//				String imgPath = UIUtils.getImagePath(request);
//				
//				File folder=new File(imgPath);
//				
//				if( ! folder.exists())
//					folder.mkdirs();
//			   String finalImage = UIUtils.imageUpload(request,imgPath);
//			   String imagepath=imgPath+finalImage;
//				File image=new File(imagepath);
//				CommonMessage.debugMsg("imagePath:"+imagepath);
//				double bytes =image.length()/1024;
//               CommonMessage.debugMsg("image size in KB:"+bytes);
//               imgSizeKB=bytes;
//			    out.print("tmp/images/"+finalImage);
//			    
//			}
				/********
				 * to get uploaded image size, if it is uploaded by ImageUpload.commonFilter use
				 * this in process Ajax calls in JSP to get the size in KB
				 *************************************/
				else if (action.equals("getUploadedImgSize.commonFilter")) {
					PrintWriter outt = response.getWriter();
					JSONObject imageInfo = new JSONObject();
					imageInfo.put("imageSize", imgSizeKB);
					outt.print(imageInfo);

				}

				else if (action.equals("machineHierarchy.commonFilter")) {

					String equipmentId = request.getParameter("machineId");
					functionalLocnHierarchy(request, response, equipmentId);
					// List<String[]> machienHierarchy =
					// commonFilterService.getMachineHierarchy(equipmentId);
					// UIUtils.writeMachineHirearchy(response,machienHierarchy);

				} else if (action.equals("cellHierarchy.commonFilter")) {
					String celId = request.getParameter("cellId");
					// CommonMessage.debugMsg(celId);
					functionalLocnHierarchy(request, response, celId);
					// List<String[]> cellHierarchy = commonFilterService.getCellHierarchy(celId);
					// UIUtils.writeCellHirearchy(response, cellHierarchy);
				} else if (action.equals("kaizenCategory.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setJHKaizenCategory(currentFilter);
					comboList = commonFilterService.getJHKaizenCategoryComboList(commonFilter);
				} else if (action.equals("fillDesignation.commonFilter")) {
					String empId = request.getParameter("empId");
					String desgId = commonFilterService.getDesigId(empId);
					CommonMessage.debugMsg("desgId55   :" + desgId);
					PrintWriter out = response.getWriter();
					;
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("desgId", desgId);
					JSONObject desgnatinId = new JSONObject();
					desgnatinId.put("desgnationID", jsonObject);
					out.println(desgnatinId);
				} else if (action.equals("fillSpoke.commonFilter")) {
					String progkeyId = request.getParameter("progkeyid");
					String spokekeyId = commonFilterService.getspokekeyId(progkeyId);
					CommonMessage.debugMsg("desgId55   :" + spokekeyId);
					PrintWriter out = response.getWriter();
					;
					JSONObject jsonObject = new JSONObject();
					if (UIUtils.isValidKeyId(spokekeyId))
						jsonObject.put("spokekey", spokekeyId);
					JSONObject spokId = new JSONObject();
					spokId.put("spokekeyID", jsonObject);
					// CommonMessage.debugMsg(spokId);
					out.println(spokId);
				}

				else if (action.equals("sectionHierarchy.commonFilter")) {
					String SectionId = request.getParameter("sectionId");
					// CommonMessage.debugMsg(SectionId);
					functionalLocnHierarchy(request, response, SectionId);
					// List<String[]> sectionHierarchy =
					// commonFilterService.getSectionHierarchy(SectionId);
					// UIUtils.writeSectionHirearchy(response, sectionHierarchy);
				} else if (action.equals("subprocess.commonFilter")) {

					String processId = request.getParameter("processId");
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setProcess(currentFilter);
					comboList = commonFilterService.getSubProcessComboList(currentFilter, processId);

				} else if (action.equals("cityHierarchy_select.commonFilter")) {
					String cityId = request.getParameter("cityId");
					// CommonMessage.debugMsg(cityId);
					List<String[]> cityHierarchy = commonFilterService.getCityHierarchy(cityId);
					UIUtils.writeCityHirearchy(response, cityHierarchy);

				}

				else if (action.equals("subUnitHierarchy.commonFilter")) {

					String subUnitId = request.getParameter("subUnitId");
					functionalLocnHierarchy(request, response, subUnitId);
					// List<String[]> subUnitHierarchy =
					// commonFilterService.getSubUnitHierarchy(subUnitId);
					// UIUtils.writeSubUnitHirearchy(response, subUnitHierarchy);
				}

				else if (action.equals("factoryHierarchy.commonFilter")) {

					String fctId = request.getParameter("factId");
					// CommonMessage.debugMsg(fctId);
					functionalLocnHierarchy(request, response, fctId);
					// List<String[]> factoryHierarchy =
					// commonFilterService.getfactoryHierarchy(fctId);
					// UIUtils.writefactoryHierarchy(response, factoryHierarchy);
				}
				/* added on 30nd june */
				else if (action.equals("workOrder.commonFilter")) {
					// CommonMessage.debugMsg(" workOrder.commonFilter:"+comboList);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setWorkOrder(currentFilter);
					comboList = commonFilterService.getWorkOrderComboList(commonFilter);
				}

				else if (action.equals("workCenter.commonFilter")) {
					// CommonMessage.debugMsg("workCenter.commonFilter =---------");
					currentFilter = UIUtils.fillComboFilter(request);

					comboList = commonFilterService.getWorkCentreComboList(commonFilter, currentFilter);
				} else if (action.equals("plannerGroup.commonFilter")) {
					// CommonMessage.debugMsg("plannerGroup.commonFilterr =---------");
					currentFilter = UIUtils.fillComboFilter(request);

					comboList = commonFilterService.getPlannerGroupComboList(commonFilter, currentFilter);
				} else if (action.equals("type.commonFilter")) {
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Disscussion", "Disscussion"));

				}

				/* Added By Praveen */
				else if (action.equals("comboSeverity.commonFilter")) {
					try {
						List<ComboBox> course = commonFilterService.getSeverityCombo();
						UIUtils.writeComboBox(response, course, currentFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("comboProbable.commonFilter")) {
					try {
						List<ComboBox> course = commonFilterService.getProbableCombo();
						UIUtils.writeComboBox(response, course, currentFilter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if (action.equals("comboGrade.commonFilter")) {
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Grade", "Grade"));
				} else if (action.equals("comboGradeno.commonFilter")) {
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.Grade", "GradeNo"));
				}

				
				  else if( action.equals("comboGradeSpec.commonFilter")) {
				  //CommonMessage.debugMsg("comboGradeSpec.commonFilter =---------"); 
					  String flid = request.getParameter("flid"); 
					  if (UIUtils.isValidKeyId(flid))
				         commonFilter.setFlid(flid); 
					  currentFilter=UIUtils.fillComboFilter(request);
				  comboList =commonFilterService.getGradeSpecComboList(commonFilter,currentFilter); 
				  }
				 

				/* Added By Praveen */
				/* added on 22nd june */
				else if (action.equals("relatedto.commonFilter")) {
					// CommonMessage.debugMsg("inside combo_relatedto CommonFilte--");
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp",
							"selectRelatedTo"));
					// CommonMessage.debugMsg("relTo SERLET
					// :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.GeneralMaintainenceProp",
					// "selectRelatedTo"));
				} else if (action.equals("locationHierarchy.commonFilter")) {

					String lcnId = request.getParameter("locnId");
					functionalLocnHierarchy(request, response, lcnId);
					//// CommonMessage.debugMsg(lcnId);
					// List<String[]> locationHierarchy =
					//// commonFilterService.getlocationHierarchy(lcnId);
					// UIUtils.writelocationHierarchy(response, locationHierarchy);

				} else if (action.equals("functionalLoc.commonFilter")) {
					FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
					functLocFieldNameBean.setCompany("cmbComp");
					functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSect");
					functLocFieldNameBean.setCell("cmbCell");
					functLocFieldNameBean.setMachine("cmbMachine");
					functLocFieldNameBean.setFactMandatory(false);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setCellMandatory(false);
					functLocFieldNameBean.setMachMandatory(false);
					String disableFuncLoc = request.getParameter("frmType");
					if ("true".equals(disableFuncLoc)) {
						functLocFieldNameBean.setCompany("cmbComp");
						functLocFieldNameBean.setSbu("cmbSbu");
						functLocFieldNameBean.setPbu("cmbPbu");
						functLocFieldNameBean.setSection("cmbSect");
						functLocFieldNameBean.setCell("cmbCell");
						functLocFieldNameBean.setMachine("cmbMachine");
						functLocFieldNameBean.setSbuDisable(true);
						functLocFieldNameBean.setPbuDisable(true);
						functLocFieldNameBean.setSectDisable(true);
						functLocFieldNameBean.setCellDisable(true);
						functLocFieldNameBean.setMachDisable(true);
					} else if ("PBU".equals(disableFuncLoc)) {
						functLocFieldNameBean.setCompany("cmbComp");
						functLocFieldNameBean.setSbu("cmbSbu");
						functLocFieldNameBean.setPbu("cmbPbu");
						functLocFieldNameBean.setSection("cmbSect");
						functLocFieldNameBean.setCell("cmbCell");
						functLocFieldNameBean.setMachine("cmbMachine");
						functLocFieldNameBean.setSbuMandatory(true);
						functLocFieldNameBean.setSbuDisable(false);
						functLocFieldNameBean.setPbuDisable(false);
						functLocFieldNameBean.setSectDisable(true);
						functLocFieldNameBean.setCellDisable(true);
						functLocFieldNameBean.setMachDisable(true);
					} else if ("SBU".equals(disableFuncLoc)) {
						functLocFieldNameBean.setCompany("cmbComp");
						functLocFieldNameBean.setSbu("cmbSbu");
						functLocFieldNameBean.setPbu("cmbPbu");
						functLocFieldNameBean.setSection("cmbSect");
						functLocFieldNameBean.setCell("cmbCell");
						functLocFieldNameBean.setMachine("cmbMachine");
						functLocFieldNameBean.setLocnMandatory(true);
						functLocFieldNameBean.setSbuDisable(false);
						functLocFieldNameBean.setPbuDisable(true);
						functLocFieldNameBean.setSectDisable(true);
						functLocFieldNameBean.setCellDisable(true);
						functLocFieldNameBean.setMachDisable(true);
					}
					FormModes formModes = FormModes.create;
					UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
				}

				else if (action.equals("sourceOfKPI.commonFilter")) {
					// String lcnid=request.getParameter("lcnid");
					// String keyid=request.getParameter("keyid");
					// String type=request.getParameter("type");
					// CommonMessage.debugMsg("Type:"+type);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSourceOfKPI(currentFilter);
					comboList = commonFilterService.getSourceOFKPI(commonFilter, currentFilter);
				}

				else if (action.equals("sbuCombo.commonFilter")) {
					// String lcnid=request.getParameter("lcnid");
					// String keyid=request.getParameter("keyid");
					// String type=request.getParameter("type");
					// CommonMessage.debugMsg("Type:"+type);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setSbu(currentFilter);
					comboList = commonFilterService.getSbuComboList(commonFilter, currentFilter);
				} else if (action.equals("pbuCombo.commonFilter")) {
					// String lcnid=request.getParameter("lcnid");
					// String keyid=request.getParameter("keyid");
					// String type=request.getParameter("type");
					// CommonMessage.debugMsg("Type:"+type);
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setPbu(currentFilter);
					comboList = commonFilterService.getPbuComboList(commonFilter, currentFilter);
				} else if (action.equals("pbu_input.commonFilter")) {
					RequestDispatcher rd = request.getRequestDispatcher("/pages/PBUMaster.jsp");
					rd.forward(request, response);
				}

				else if (action.equals("sbuHierarchy.commonFilter")) {

					String keyid = request.getParameter("sbuId");
					functionalLocnHierarchy(request, response, keyid);
					// CommonMessage.debugMsg(sbuId);
					// List<String[]> sbuHierarchy = commonFilterService.getSbuHierarchy(sbuId);
					// UIUtils.writeCellHirearchy(response, sbuHierarchy);

				} else if ("fetchFLHierarchy.commonFilter".equals(action)) {
					String originalId = request.getParameter("originalId");
					functionalLocnHierarchy(request, response, originalId);
				}

				else if (action.equals("pbuHierarchy.commonFilter")) {

					String keyid = request.getParameter("pbuId");
					functionalLocnHierarchy(request, response, keyid);
					// CommonMessage.debugMsg(sbuId);
					// List<String[]> sbuHierarchy = commonFilterService.getSbuHierarchy(sbuId);
					// UIUtils.writeCellHirearchy(response, sbuHierarchy);

				} else if (action.equals("effective.commonFilter")) {
					currentFilter = UIUtils.fillComboFilter(request);
					commonFilter.setEffectiveness(currentFilter);
					String assmId = request.getParameter("cmbAssmbid");
					if (UIUtils.isValidKeyId(assmId)) {
						ComboFilter assembly = new ComboFilter();
						assembly.setId(assmId);
						commonFilter.setAssembly(assembly);
					}
					comboList = commonFilterService.getEffectiveeComboList(commonFilter);

				} else if (action.equals("team.commonFilter")) {

					compId = request.getParameter("compId");
					factId = request.getParameter("factId");
					sectId = request.getParameter("sectId");
					cellId = request.getParameter("cellId");
					String mchId = request.getParameter("mchId");
					String empId = request.getParameter("empId");

					StringBuffer cond = new StringBuffer();
					String cnd;
					String funLocCndSql = "";
					String empCndSql = "";

					cond.append(" AND ( 1 = 2 ");
					if (UIUtils.isValidKeyId(compId)) {
						cnd = " OR TMFM_FUN_KEYID ='" + compId + "' ";
						cond.append(cnd);
					}
					if (UIUtils.isValidKeyId(factId)) {
						cnd = " OR TMFM_FUN_KEYID ='" + factId + "' ";
						cond.append(cnd);
					}
					if (UIUtils.isValidKeyId(sectId)) {
						cnd = " OR TMFM_FUN_KEYID ='" + sectId + "' ";
						cond.append(cnd);
					}
					if (UIUtils.isValidKeyId(cellId)) {
						cnd = " OR TMFM_FUN_KEYID ='" + cellId + "' ";
						cond.append(cnd);
					}
					if (UIUtils.isValidKeyId(mchId)) {
						cnd = " OR TMFM_FUN_KEYID ='" + mchId + "' ";
						cond.append(cnd);
					}
					cond.append(" ) ");

					funLocCndSql = cond.toString();

					if (UIUtils.isValidKeyId(empId))
						empCndSql = " AND TMEL_EMPID = '" + empId + "' ";

					/*
					 * empComboFilter=UIUtils.fillComboFilter(request); List<ComboBox> empcomboList
					 * = commonFilterService.getEmployeeComboList(empComboFilter,rtal);
					 * UIUtils.writeComboBox(response, empcomboList ,empComboFilter);
					 * ,teamComboFilter
					 */

					ComboFilter teamComboFilter = new ComboFilter();
					teamComboFilter = UIUtils.fillComboFilter(request);
					CommonMessage.debugMsg("teamComboFilter:::::::Year End  " + teamComboFilter.getMode());
					List<ComboBox> teamcomboList = commonFilterService.getTeamComboList(funLocCndSql, empCndSql);
					UIUtils.writeComboBox(response, teamcomboList, teamComboFilter);
				} else if (action.equals("employeeRole.commonFilter")) {

					currentFilter = UIUtils.fillComboFilter(request);
					// commonFilter.setSbu(currentFilter);

					comboList = commonFilterService.getRoleComboList(commonFilter, currentFilter);

				}

				if (comboList != null && comboList.size() > 0) {
//		    	CommonMessage.debugMsg("writeComboBox ="+comboList.toString());
					UIUtils.writeComboBox(response, comboList, currentFilter);
				}
				if (dispatchURL != null) {
					RequestDispatcher rd = request.getRequestDispatcher(dispatchURL);
					rd.forward(request, response);
				}
				commonFilter = null;
				comboList = null;

			} catch (ServiceObjectCreationException e) {
				// TODO Auto-generated catch block
				CommonMessage.debugMsg(e);
				// e.printStackTrace();
			}
		} catch (Exception e) {
			// CommonMessage.debugMsg(e.getMessage());
		}

	}

	private String getComboColmodel() {
		// TODO Auto-generated method stub
		return null;
	}

	private void functionalLocnHierarchy(HttpServletRequest request, HttpServletResponse response, String keyid)
			throws IOException {

		String funcLocnHierarchy;
		try {
			funcLocnHierarchy = commonFilterService.getFuncLocnHierarchy(keyid);
			UIUtils.writeFunctionlocnHirearchy(response, funcLocnHierarchy);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getDateWithFormat(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public static JSONObject convertActivityType(List<String[]> dataArrayList, HttpServletRequest request, int rowStart,
			int colStart, int colSub, long totalRecords) {
		String rowsStr = request.getParameter("rows");
		String pageStr = request.getParameter("page");
		int rows = 100;
		if (rowsStr != null)
			rows = Integer.parseInt(rowsStr);

		int page = 1;
		if (pageStr != null)
			page = Integer.parseInt(pageStr);

		JSONObject tableDataObject = new JSONObject();

		// CommonMessage.debugMsg(" totalRecords " + totalRecords);

		tableDataObject.put("page", page); // current page
		tableDataObject.put("total", Math.ceil(totalRecords / rows) == 0 ? 1 : (Math.ceil(totalRecords / rows) + 1)); // total
																														// page
		// if( page == 1)
		tableDataObject.put("records", totalRecords - rowStart); // total records

		JSONArray rowArr = new JSONArray();

		int rowId = rows * (page - 1);
		int slno = 0;
		for (String[] row : dataArrayList) {
			if (slno++ >= rowStart) {
				JSONObject rowObj = new JSONObject();

				rowObj.put("id", rowId - rowStart + 1);

				JSONArray cell = new JSONArray();

				for (int i = colStart; i < (row.length - colSub); i++) {
					cell.put((row[i] != null ? row[i].isEmpty() ? " "
							: row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "")
									.replace("<*", "").replace("*>", "")
							: " "));
				}
				rowObj.put("cell", cell);

				rowArr.put(rowObj);
			}
			rowId++;
		}

		tableDataObject.put("rows", rowArr);

		return tableDataObject;

	}

	public List<CommonFilter> getbreakup(String breakup) {
		CommonFilter breakUpList = new CommonFilter();
		List<CommonFilter> breakUpArray = new ArrayList<CommonFilter>();
		JSONArray convertbreak = null;
		if (UIUtils.isValidKeyId(breakup)) {
			if (breakup != null && !breakup.isEmpty()) {
				convertbreak = JSONArray.fromString(breakup);
				breakUpArray = (List<CommonFilter>) UIUtils.convertJSONArrToList(breakUpList, convertbreak);
			}
		}
		return breakUpArray;
	}

}
