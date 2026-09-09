package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.EmployeeService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.EmployeeServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/**
 * Servlet implementation class BasePageServlet
 */

public class BasePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CommonFilterService commonFilterService ;
	EmployeeService employeeService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasePageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (ValidationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (ValidationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,HttpServletResponse response)  throws Exception
			{
				
				 
				HttpSession httpSession = request.getSession(false);
				String action = UIUtils.getActionPart(request);
				ComboFilter comboFilter = new ComboFilter();
				try {
					commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
				    employeeService=(EmployeeServiceImpl)UIUtils.getServiceObject(request,"EmployeeServiceImpl");

					/*genVwToolcategoryService = (GenVwToolcategoryServiceImpl)UIUtils.getServiceObject(request,"GenVwToolcategoryServiceImpl");
					commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
					plmTlShutdowncalservice =(PlmTlShutdowncalservice) UIUtils.getServiceObject(request,"PlmTlShutdowncalserviceImpl");*/
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				if(action.equals("empEqp_input.base")){
					String showAll = request.getParameter("showAll");
					CommonMessage.debugMsg("The ShowAll   "+showAll);
					boolean dashBoardDisplay = true;
					String displayEmp ="block";
					if("false".equals(showAll)){
						dashBoardDisplay = false;
						displayEmp = "none";
					}	
					request.setAttribute("dashBoardDisplay", dashBoardDisplay);
					request.setAttribute("disaplyEmp", displayEmp);
				
					UIUtils.forwardRequest(request, response, "/pages/LandingPage/LandingPage.jsp");
				}
				else if(action.equals("Dashboard_input.base")){
					String showAll = request.getParameter("showAll");
					boolean dashBoardDisplay = true;
					String displayEmp ="block";
					if("false".equals(showAll)){
						dashBoardDisplay = false;
						displayEmp = "none";
					}	
					request.setAttribute("dashBoardDisplay", dashBoardDisplay);
					UIUtils.forwardRequest(request, response, "/pages/LandingPage/basePage.jsp");
				}
				else if(action.equals("controlPanel_input.base")){
					UIUtils.forwardRequest(request, response, "/pages/LandingPage/ControlPanel.jsp");
				}
				else if(action.equals("empldataUpdate_input.base")){
					CommonMessage.debugMsg("EmpUpdate");
					UIUtils.forwardRequest(request, response,"pages/EmployeeDetailUpdate.jsp");
				}
				else if(action.equals("getEmployeeData.base")){
					String userKeyid = request.getParameter("userkeyid");
					List<String[]>  equipmentList = employeeService.getEmpData(userKeyid);
					
					JSONObject empData = new JSONObject();
					
					empData.put("empmName",equipmentList.get(0)[0]);
					empData.put("empmNo",equipmentList.get(0)[1]);
					empData.put("empmEmail",equipmentList.get(0)[2]);
					empData.put("empmMobile",equipmentList.get(0)[3]);
					empData.put("empmPillar",equipmentList.get(0)[4]);
					
					PrintWriter out = response.getWriter();
					out.print(empData.toString());
				}
				else if(action.equals("getPillarMenu.base")){
					String pillarid = request.getParameter("pillar");
					AdmTlUsermst loginUser = UIUtils.getLoginUser(request);
					//loginUser.getUsrm_keyid();
					List<String[]>  menuList = commonFilterService.getMenu(pillarid,loginUser.getUsrm_keyid());
					JSONArray menuData = new JSONArray();
					JSONObject menuD = new JSONObject();
					
					//for(int i=0;i<menuList.size();i++){
						menuData= menuData.fromCollection(menuList);
					//}
					menuD.put("menuData", menuData);
					PrintWriter out = response.getWriter();
					out.print(menuData.toString());
				}
				else if(action.equals("combo_getLosses.base"))
			    {
			    	CommonMessage.debugMsg("inside combo_pmsdwhtfreq standarad");
			 	    PrintWriter out = response.getWriter();
			    	out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "pcslossCapture"));
			    	CommonMessage.debugMsg("relTo SERLET   :"+UIUtils.getPropertyValue("com.akranta.tpm.resources.PmstandardProp", "pcslossCapture"));
			    }
				else if(action.equals("empEqpgrid_getCol.base")){
					CommonMessage.debugMsg(action+" getCOOOOOOOOOOOL");
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModelEqpm"));
				}
				else if(action.equals("empEqpgrid_getData.base")){
					 CommonMessage.debugMsg("inside getdata");
					 String userID = request.getParameter("userId");
					  try{
						  PrintWriter out = response.getWriter();
					   List<String[]>  equipmentList = commonFilterService.getempEqpData(userID);
					   JSONObject equipmentListgrd = UIUtils.convertToJqGridTableObject(equipmentList, request, 0, 0);
					   out.println(equipmentListgrd);
					  }
					  catch (Exception e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				else if(action.equals("EmployeInfoEdit_input.base")){
					String userkeyid = request.getParameter("userkeyid");
					request.setAttribute("userkeyid", userkeyid);
					UIUtils.forwardRequest(request, response, "/pages/LandingPage/EmployeeInfo.jsp");
				}
				else if(action.equals("pcsLoss_input.base")){
					UIUtils.forwardRequest(request, response, "/pages/pcs/pcsLossCapture.jsp");
				}
				else if(action.equals("empkpiIndicatorList_view.base")){
					request.setAttribute("EmpGrid", request.getParameter("EmpGrid"));
					UIUtils.forwardRequest(request, response, "/pages/KPI/empKpiLink.jsp");
				}
				else if(action.equals("pilarWiseEmp_getCol.base")){
					PrintWriter out = response.getWriter();
					JSONObject colModelObj =JSONObject.fromString( UIUtils.getPropertyValue("com.akranta.tpm.resources.CommColModel", "colModelEmployee"));
					colModelObj.set("multiSelect", "true");
					colModelObj.getJSONArray("colModel").getJSONObject(1).set("hidden",true);
					out.println(colModelObj);
				}
				else if(action.equals("pilarWiseEmp_getData.base")){
					try {
						List<String[]> pilEMpgrid = commonFilterService.getPilarWiseEmployee("");
						PrintWriter out = response.getWriter();
						JSONObject pilEMpgridmod = UIUtils.convertToJqGridTableObject(pilEMpgrid, request, 0,0);
                        out.println(pilEMpgridmod);
                        CommonMessage.debugMsg(pilEMpgridmod);
					} catch (Exception e) {
						CommonMessage.debugMsg(e.getMessage());
					}

				}
				 else if(action.equals("getEmployeeLocation.base")){
					 PrintWriter out = response.getWriter();
					 String userkeyid=request.getParameter("userkeyid");
					 String EmpLocation = commonFilterService.getEmployeeLocation(userkeyid);
					 CommonMessage.debugMsg("EmpLocation"+EmpLocation);
					 JSONObject json=new JSONObject();
					json.put("EmpLocation",EmpLocation);
					out.println(json);
					
				 }
				
				else if(action.equals("pcsLoss_getCol.base")){
					PrintWriter out = response.getWriter();
					JSONObject jsonObject = null;
					List<String[]> lossgrid = null;
					
			try {
						lossgrid = commonFilterService.getPcsLoss("");

					} catch (Exception e) {
						e.printStackTrace();
					}
					
					 jsonObject = getTableModel(lossgrid);
				
				
					out.println(jsonObject);
					
				}
				else if(action.equals("pcsLoss_getData.base")){
					try {

						List<String[]> lossgrid = commonFilterService.getPcsLoss("");

						PrintWriter out = response.getWriter();

						JSONObject lossgridmod = UIUtils.convertToJqGridTableObject(lossgrid, request, 1,0);
							out.println(lossgridmod);
					} catch (Exception e) {
						CommonMessage.debugMsg(e.getMessage());
					}
				}
				
	 
			}

	private JSONObject getTableModel(List<String[]> lossgrid) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = lossgrid.get(0);

		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setTableHeight(200);
		jqGridTableModel.setTableWidth(500);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			
			jqGridColModel.setWidth(145);
			CommonMessage.debugMsg("inside ["+i+"]"+colHeader[i]);
			
			
			if(i==0){
            	jqGridColModel.setFormatter("FromspinerFormatter");
            	jqGridColModel.setWidth(40);				
			}
            if (i==1)
            {
            	jqGridColModel.setFormatter("TospinerFormatter");
            	jqGridColModel.setWidth(40);	
			}
            if (i==6  )
            {
            	jqGridColModel.setWidth(80);
            	jqGridColModel.setFormatter("TradeFormatter");
            }
            if (i==2  )
            {
            	jqGridColModel.setWidth(45);	
            }
            if (i==3)
            {
            	jqGridColModel.setFormatter("proImpactText");
            	jqGridColModel.setWidth(72);	
            }
            if(i==4){
            	
            	jqGridColModel.setFormatter("lossCmb");
            	jqGridColModel.setWidth(116);
            }
            if (i==5)
            {
            	jqGridColModel.setFormatter("lossReasonText");
            	jqGridColModel.setWidth(100);				
			}
            if (i==8)
            {
            	jqGridColModel.setFormatter("ProductText");
            	jqGridColModel.setWidth(109);				
			}
            
            if (i==7) {
            	jqGridColModel.setFormatter("downTimeText");
            	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(111);
			}

            if (i==11) {
            	jqGridColModel.setFormatter("kaizenBtn");
            	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(80);
			}
            if (i==12) {
            	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(81);
			}
            if (i==10) {
            	jqGridColModel.setAlign("center");	
				jqGridColModel.setWidth(83);
			}
            if (i==9) {
            	jqGridColModel.setAlign("center");	
            	jqGridColModel.setFormatter("whywhyBtn");
				jqGridColModel.setWidth(80);
			}
            if (i==13)
            {
            	jqGridColModel.setHidden(true);
            }

			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "65%%");
		tableModel.set("tableWidth", "110%%");
		return tableModel;
	}
	 
}
