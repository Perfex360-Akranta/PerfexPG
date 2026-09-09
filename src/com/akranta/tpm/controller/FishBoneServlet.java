
package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ChartOptionBean;
import com.akranta.tpm.bean.ChartSeries;
import com.akranta.tpm.bean.ChartTypes;
import com.akranta.tpm.bean.ChartXAxis;
import com.akranta.tpm.bean.ChartYAxis;
import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.FishBoneService;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.DashboardService;
import com.akranta.tpm.service.impl.FishBoneServiceImpl;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.service.impl.DashboardServiceImpl;

public class FishBoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/* 
     * Created : Roopa
     * Date : 13 Nov 2013
     */
	DashboardService dashboardService;
	FishBoneService fishBoneService;
	CommonFilterService commonFilterService; 
    public FishBoneServlet() throws Exception {
        super(); 
    }
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    	
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action " + action);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		
		
		if( user == null)
			return ;
		
		try {
			dashboardService = (DashboardServiceImpl)UIUtils.getServiceObject(request,"DashboardServiceImpl");
			fishBoneService = (FishBoneServiceImpl)UIUtils.getServiceObject(request,"FishBoneServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			
			CommonMessage.debugMsg("  fishBoneService jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			fishBoneService.FishBoneServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
			
			if (action.equals("filterXmlFishBoneRpt_input.fishbone") )  {
				
				UIUtils.forwardRequest(request, response,
						"/tiles/xml/FishBone.xml");
				
			}
				
			else if(action.equals("FishBone_input.fishbone"))
			{	
				String FishboneKeyId = request.getParameter("keyId");
				String refDoctype = request.getParameter("refDoctype");
				String refDocid = request.getParameter("refDocid");

				request.setAttribute("disableForRpt", "true");
				
				request.setAttribute("FishboneKeyId", FishboneKeyId);	
				request.setAttribute("refDoctype", refDoctype);
				request.setAttribute("refDocid", refDocid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneGrid.jsp");
				
				rd.forward(request, response);				
			}
			else if(action.equals("FishBoneRpt_input.fishbone"))
			{ 
				request.setAttribute("formtype", "rpt");
				request.setAttribute("disableForRpt", "false"); 				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneGrid.jsp"); 
				rd.forward(request, response);		
				
			}else if (action.equals("FishBonerptGrid_input.fishbone"))
			{
				String keyid = request.getParameter("keyid");
				String title = request.getParameter("title");
				request.setAttribute("title", title);
				request.setAttribute("keyid", keyid);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneRptGrid.jsp"); 
				rd.forward(request, response);
			}else if (action.equals("FishBonerptGrid_getCol.fishbone"))
			{
				PrintWriter out = response.getWriter();
				JSONObject jsonObject = new JSONObject();
				String Keyid =request.getParameter("keyid");
				CommonMessage.debugMsg("rptkeyid" + Keyid);
				List<String[]> detailGrid = null;
				//CommonFilter commonFilter = new CommonFilter();
				try {
					detailGrid = fishBoneService.getFBDetail(Keyid);

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				jsonObject = UIUtils.convertToJqGridTableObject(detailGrid, request, 0, 0);
				jsonObject = getTableModelForFBRpt(detailGrid);
				httpSession.removeAttribute("detailGrid");
				httpSession.setAttribute("detailGrid",jsonObject);
				out.println(jsonObject);
				
			}else if (action.equals("FishBonerptGrid_getData.fishbone"))
			{
				try {
					String keyid =request.getParameter("keyid");				

					
					List<String[]> StudentReportGrid = fishBoneService.getFBDetail(keyid);

					PrintWriter out = response.getWriter();

					JSONObject deptreportgridDetail = UIUtils.convertToJqGridTableObject(StudentReportGrid, request,1, 0);

					out.println(deptreportgridDetail);
				} catch (Exception e) {
					CommonMessage.debugMsg(e.getMessage());
				}
			}else if (action.equals("FishBonerptGrid_getExcel.fishbone"))
			{
				//CommonFilter commonFilter = populateCommonFilter(request,"ServiceLevelAgreementCommonFilter",false);
				//JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("SLAMasterReport");
				//JSONObject tblJSONObj = JSONObject.fromString(request.getParameter("colModel"));
				 JSONObject colModel = UIUtils.getXlColModel(request, response);

				String format = ExcelUtils.getFormat(request);	
				
				String keyid = request.getParameter("keyid");
				String title = request.getParameter("title");
				colModel.put("title","Fish Bone - "+title); 
				Workbook wb = fishBoneService.getFBDetailForExcel(keyid, colModel, format);
				ExcelUtils.writeToResponse(response, wb, "FishBone", format);
			}
			else if(action.equals("FishBoneRpt_getCol.fishbone"))
			{
				List<String[]> fishGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();
			
				try {
				    CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",true);
				    fishGrid = fishBoneService.getAllFishGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = fishGrid.get(1);
					String [] colHeaderHead = fishGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "100%%");
					colmodel.set("tableHeight", "80%%");
					httpSession.removeAttribute("FishBoneColModel");
					httpSession.setAttribute("FishBoneColModel", colmodel);
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}			
			}else if(action.equals("FishBoneRpt_getData.fishbone"))
			{				
				try {
					CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
					List<String[]> fishGridList =  fishBoneService.getAllFishGrid(commonFilter);

					PrintWriter out = response.getWriter();
					JSONObject fishGrid=UIUtils.convertToJqGridTableObject(fishGridList, request,2, 0,commonFilter.getTotalRecordCnt());
					// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
	  			 	out.println(fishGrid);  			 	
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("FishBoneCommonFilter");
	  			 	httpSession.setAttribute("FishBoneCommonFilter", commonFilter);
					
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			else if(action.equals("FishBone_getCol.fishbone"))
			{
				List<String[]> fishGrid = null;
				PrintWriter out = response.getWriter();
				//JSONObject jsonObject = new JSONObject();
			
				try {
				    
					String refDocid = request.getParameter("refDocid");
					String refDoctype = request.getParameter("refDoctype");
					CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
					commonFilter.setRefdocid(refDocid);
					commonFilter.setDocType(refDoctype);
					
					String loginFlid = CommonFunctions.getLoginFlid(request);
					if (!UIUtils.isValidKeyId( commonFilter.getFlid()))
							commonFilter.setFlid(loginFlid);
					
				    fishGrid = fishBoneService.getAllFishGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = fishGrid.get(1);
					String [] colHeaderHead = fishGrid.get(0);
					List<String[]> headers = new ArrayList<String[]>();
					headers.add(colHeader);
					JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					
					CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
					colmodel.set("tableWidth", "100%%");
					colmodel.set("tableHeight", "80%%");
					httpSession.removeAttribute("FishBoneColModel");
					httpSession.setAttribute("FishBoneColModel", colmodel);
					out.println(colmodel);

				} catch (Exception e) {
					e.printStackTrace();
				}			
			}else if(action.equals("FishBone_getData.fishbone"))
			{				
				try {
					
					String refDocid = request.getParameter("refDocid");
					String refDoctype = request.getParameter("refDoctype");
					
					CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
					
					commonFilter.setRefdocid(refDocid);
					commonFilter.setDocType(refDoctype);
					
					String loginFlid = CommonFunctions.getLoginFlid(request);
					if (!UIUtils.isValidKeyId( commonFilter.getFlid()))
							commonFilter.setFlid(loginFlid);
					
					List<String[]> fishGridList =  fishBoneService.getAllFishGrid(commonFilter);

					PrintWriter out = response.getWriter();
					JSONObject fishGrid=UIUtils.convertToJqGridTableObject(fishGridList, request,2, 0,commonFilter.getTotalRecordCnt());
					// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
	  			 	out.println(fishGrid);  			 	
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("FishBoneCommonFilter");
	  			 	httpSession.setAttribute("FishBoneCommonFilter", commonFilter);
					
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
			else if((action.equals("FishBone_getExcel.fishbone")||(action.equals("FishBoneRpt_getExcel.fishbone"))))
			{
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
				JSONObject colmodel = (JSONObject) httpSession.getAttribute("FishBoneColModel");
				colmodel.put("title","Fish Bone");
	            String format = ExcelUtils.getFormat(request);
				
				Workbook wb = fishBoneService.getFishBoneExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "FishBone", format);
				
			}
			else if (action.equals("FishBoneCount_input.fishbone")) {
			    CommonMessage.debugMsg("Inside Input Servlet::::");
			    
			    String comp = request.getParameter("cmbCompid");
			    CommonMessage.debugMsg("comp::::"+comp);
				String locn=request.getParameter("cmbLocnid");
				String fact = request.getParameter("cmbFactid");
				String sect = request.getParameter("cmbSectid");
				String fromMonth=request.getParameter("dtFromMonth");
				String toMonth=request.getParameter("dtToMonth");
				String toDate = request.getParameter("dtToDate");
				String fromDate = request.getParameter("dtFromDate");
				String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
				request.setAttribute("hiddenCompId",comp);
				request.setAttribute("hiddenLocnId",locn);
				request.setAttribute("hiddenFactId",fact);
				request.setAttribute("hiddenSectId",sect);
				request.setAttribute("hiddenFromMonth",fromMonth);
				request.setAttribute("hiddenToMonth",toMonth);
				request.setAttribute("hdnfromdate", fromDate);
				request.setAttribute("hdntodate", toDate);
			    
			   UIUtils.forwardRequest(request, response,"/pages/FishBoneCount.jsp");
		}
			 else if (action.equals("FishBoneCount_getCol.fishbone")) {
					//HttpSession httpSession = request.getSession(false);
					PrintWriter out = response.getWriter();
					String firstClick = request.getParameter("firstClick");
					
					
					CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",true);
					if (!UIUtils.isValidKeyId(firstClick) || (firstClick != null && !firstClick.equals("Y"))) {
						 commonFilter = (CommonFilter) httpSession.getAttribute("FishBoneReportCommonFilter");
					}
					
					if (commonFilter == null)
						commonFilter = new CommonFilter();
					String jhKaizenCategory = request.getParameter("cmbKaizenCategory");
					// -- Vignesh 17Dec2025
					String drillCaption = request.getParameter("drillCaption");
					CommonMessage.debugMsg(" drill captipn before null in servlet " + drillCaption);
			
					CommonMessage.debugMsg(" drill caption  " + commonFilter.getDrillCaption());
					
					if(drillCaption == null) {
						drillCaption = commonFilter.getDrillCaption();
					} else if ("CMP".equals(drillCaption)) {
						drillCaption = "LOCN";
					}else if ("LCN".equals(drillCaption)) {
						drillCaption = "SBU";
					}else if ("SBU".equals(drillCaption)) {
						drillCaption = "PBU";
					}else if ("PBU".equals(drillCaption)) {
						drillCaption = "SECT";
					}else if ("L".equals(drillCaption)) {
						drillCaption = "CELL";
					}else if ("C".equals(drillCaption)) {
						drillCaption = "CELL";
					}
					CommonMessage.debugMsg(" drill captipn AFTER null in servlet " + drillCaption);
					
					ComboFilter jhKaizenCategoryObj=new ComboFilter();
					if( jhKaizenCategoryObj != null)
						jhKaizenCategoryObj.setId(jhKaizenCategory);
					commonFilter.setJHKaizenCategory(jhKaizenCategoryObj);
					CommonMessage.debugMsg(" Inside Getcol Action commonFilter " +commonFilter+" "+request.getParameter("cmbKaizenCategory")+"jhKaizenCategoryObj.getId(jhKaizenCategory)"+commonFilter.getJHKaizenCategory().getId());
					httpSession.removeAttribute("FishBoneReportCommonFilter");
					httpSession.setAttribute("FishBoneReportCommonFilter", commonFilter);
					FilterValues.getCommonFilters(request, commonFilter);
					FilterValues.getOPLandKaizen(request, commonFilter);
					
					if (Constants.passNullDate.contains(commonFilter.getFromMonth()) && (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) 
					{
						commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
						commonFilter.setToMonth(CommonFunctions.getDate().substring(3,11));
						commonFilter.setMonwise("Y");
					}
					CommonMessage.debugMsg("kznSgnCount::Before::");
					List<String[]> kznSgnCount = fishBoneService.getKznSgnCount(commonFilter);
					CommonMessage.debugMsg("kznSgnCount::::"+kznSgnCount);
					JSONObject kznSgnCountData = UIUtils.convertToJqGridTableObject(kznSgnCount, request, 0, 2);
					//JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
					JSONObject jsonObject = getTableModel(kznSgnCount,FilterValues.getHeader(drillCaption));
					jsonObject.set("tableHeight", "80%%");
					jsonObject.set("tableWidth", "104%%");
					httpSession.removeAttribute("FishBoneColModel");
					httpSession.setAttribute("FishBoneColModel", jsonObject);
					CommonMessage.debugMsg("Table Model:" + jsonObject);
					out.println(jsonObject);
				} 
				
				else if (action.equals("FishBoneCount_getData.fishbone")) {
					try {
						//HttpSession httpSession = request.getSession(false);
						//String flid=request.getParameter("flid");
						//String drillflag=request.getParameter("drillFlag");
						 CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
												
						httpSession.getAttribute("FishBoneReportCommonFilter");
						List<String[]> impVscomList = fishBoneService.getKznSgnCount(commonFilter);
						
						JSONObject listToJsonObject = new JSONObject();

						if (impVscomList != null && impVscomList.size() > 4)
							CommonMessage.debugMsg("commonFilter.getTotalRecordCnt()"+commonFilter.getTotalRecordCnt());
							// listToJsonObject = convertToJqGridTableObjectOPL( impVscomList,request,2,0,0,commonFilter.getTotalRecordCnt()+1);

							listToJsonObject = UIUtils.convertToJqGridTableObject(impVscomList, request, 1,0, 0,commonFilter.getTotalRecordCnt()+1);
						
						PrintWriter out = response.getWriter();
						out.println(listToJsonObject);
					}

					catch (Exception e) {
						CommonMessage.debugMsg("error " + e.getMessage());
					}
				}
			// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//
			
				 else if (action.equals("FishBoneCount_getExcel.fishbone")) {
						//HttpSession httpSession = request.getSession(false);
					
						

						CommonFilter commonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);

			JSONObject tblJSONObj = UIUtils.getXlColModel( request, response);
			tblJSONObj.put("title","Fish Bone Count");
	        String format = ExcelUtils.getFormat(request);	
									
			Workbook wb = fishBoneService.getExcelreport(commonFilter,tblJSONObj,format);
			ExcelUtils.writeToResponse(response, wb, "FishBoneCount", format);


					} 
			
			// --------- ADDING GETEXCEL BY Vignesh 17Dec2025 -----------------------------------//
			
			else if( action.equals("FishBoneTree_modify.fishbone") )
		    {   
				//CommonMessage.debugMsg("jhaTlFiveAuditarea_modify.5saudit");
				
				String FishboneKeyId = request.getParameter("keyId");
				String refDoctype = request.getParameter("refDoctype");
				String refDocid = request.getParameter("refDocid");
				CommonMessage.debugMsg(" FishboneKeyId :: "+FishboneKeyId);
				String FishboneGrid=request.getParameter("grid");
				CommonMessage.debugMsg(" FishboneGrid :: "+FishboneGrid);
				CommonMessage.debugMsg(" Fishbonedoctype:: "+refDoctype);
				if("true".equals(FishboneGrid))
				{
				CommonMessage.debugMsg(" Inside FishboneGrid "+FishboneGrid);
				GenTlFishbonemst newGenTlFishbonemst = new GenTlFishbonemst();
				newGenTlFishbonemst = fishBoneService.getFillControl(FishboneKeyId);
				
				if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdocid()))
					newGenTlFishbonemst.setFismRefdocid(refDocid);
				/*if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdoctype()))*/
					newGenTlFishbonemst.setFismRefdoctype(refDoctype);
				
				request.setAttribute("newGenTlFishbonemst", newGenTlFishbonemst);
				
			//	httpSession.setAttribute("existGenTlFishbonemst ",newGenTlFishbonemst);
				}
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneList.jsp");					
				rd.forward(request, response);		
		    }
			
			
			else if(action.equals("functionalLoc.fishbone"))
			{				
				
				CommonMessage.debugMsg("function location ");
				FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				functLocFieldNameBean.setSection("cmbFismSectionid");
				functLocFieldNameBean.setCell("cmbFismCellid");
				functLocFieldNameBean.setMachine("cmbFismMachineid");
				functLocFieldNameBean.setSbu("cmbFismFlid");
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setFactMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCellMandatory(false);
				functLocFieldNameBean.setMachMandatory(false);
				CommonMessage.debugMsg("functionlocation null ");
                FormModes formModes = FormModes.create;
                UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );		
                
			}
			else if(action.equals("FishBoneTree_input.fishbone"))
			{				
				String UserLogin =user.getUsrm_ccno();
				CommonMessage.debugMsg(" UserLogin :: " +UserLogin);
				request.setAttribute("UserLogin", UserLogin);
				
				String FishboneKeyId = request.getParameter("keyId");
				CommonMessage.debugMsg(FishboneKeyId + "fishbonekeyid");
				String refDoctype = request.getParameter("refDoctype");
				CommonMessage.debugMsg(refDoctype);
				String refDocid = request.getParameter("refDocid");
				CommonMessage.debugMsg(refDocid + "refDocid ");
				String flid=request.getParameter("flid");
				String effect=request.getParameter("effect");	
				GenTlFishbonemst newGenTlFishbonemst = new GenTlFishbonemst();
				//newGenTlFishbonemst = fishBoneService.getFillControl(FishboneKeyId);
				
				newGenTlFishbonemst.setFismFlid(flid);
				if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdocid()))
					newGenTlFishbonemst.setFismRefdocid(refDocid);
				if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdoctype()))
					newGenTlFishbonemst.setFismRefdoctype(refDoctype);
				if(!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismProblem()))
					newGenTlFishbonemst.setFismProblem(effect);
				request.setAttribute("newGenTlFishbonemst", newGenTlFishbonemst);
				
				//httpSession.setAttribute("existGenTlFishbonemst ",newGenTlFishbonemst);
				
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneList.jsp"); 
				rd.forward(request, response);		
				
			}else if(action.equals("FishBoneCauseAdd_input.fishbone"))
			{			
				CommonMessage.debugMsg("123======FishBoneCauseAdd_input");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneCausePop.jsp"); 
				rd.forward(request, response);			
			}
			/**----- For GENERATING GRAPH------- **/
			else if( action.equals("chart.fishbone"))
			{
			processChart(request,response);
			}
			else if( action.equals("loadval.fishbone") )
			{		
				PrintWriter out = response.getWriter();
				LoadFishBoneTree(request, response,out);
			}
			else if( action.equals("searchnode.fishbone") )
			{	
				PrintWriter out = response.getWriter();
				searchFishBone(request,response,httpSession,out);
			}
			else if( action.equals("FishBoneTree_save.fishbone") )
			{	
				FishBoneBean fishBoneBean = (FishBoneBean)httpSession.getAttribute("FishBoneBean");
		        saveFishBone(request,response,fishBoneBean);
			}
			else if( action.equals("FishBoneTree_delete.fishbone") )
			{	
				FishBoneBean fishBoneBean = new FishBoneBean();
				deleteFishBoneMst(request,response,fishBoneBean);
			}
			else if( action.equals("FishBoneChildEntry_save.fishbone") )
			{	
				FishBoneBean fishBoneBean = (FishBoneBean)httpSession.getAttribute("FishBoneBean");
		        saveFishBoneChildEntry(request,response,fishBoneBean);
			}
			else if( action.equals("FishBoneChildEntry_delete.fishbone") )
			{	
				FishBoneBean fishBoneBean = (FishBoneBean)httpSession.getAttribute("FishBoneBean");
		        deleteFishBoneChildEntry(request,response,fishBoneBean);
			}
			else if( action.equals("FishBoneChild_modify.fishbone") )
			{	
				String levelNo = request.getParameter("levelNo");
				String OrderNo = request.getParameter("OrderNo");
				String dispCode = request.getParameter("dispCode");
				String ParentId = request.getParameter("ParentId");
				String Masterid = request.getParameter("Masterid");
				String DtlId = request.getParameter("DtlId");
				CommonMessage.debugMsg(levelNo+" OrderNo ::  "+OrderNo+" dispCode :: "+dispCode+" ParentId :: "+ParentId+" Masterid :: "+Masterid);
				request.setAttribute("levelNo", levelNo);
				request.setAttribute("OrderNo", OrderNo);
				request.setAttribute("dispCode", dispCode);
				request.setAttribute("ParentId", ParentId);
				request.setAttribute("Masterid", Masterid);
				request.setAttribute("DtlId", DtlId);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/FishBoneChildEntry.jsp"); 
				CommonMessage.debugMsg(" Detail Id " + DtlId);
				
				rd.forward(request, response);		
			}
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}	
		
	}
private void processChart(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
		CommonFilter commonFilter= populateCommonFilter(request,"FishBoneReportCommonFilter",false);
		CommonFilter chartCommonFilter = populateCommonFilter(request,"FishBoneReportCommonFilter",false);
		String forDashboard = request.getParameter("dashboard");
				
		if( ! "true".equals(forDashboard)){
			commonFilter = (CommonFilter) httpSession.getAttribute("FishBoneReportCommonFilter");
			BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		}
		else{
			
			CommonMessage.debugMsg("commonFilter in process chart:::::"+commonFilter);
			FilterValues.getCommonFilters(request, commonFilter) ;
			FilterValues.getAbnRelatedFilters(request, commonFilter);		
			//BeanUtils.copyProperties(chartCommonFilter, commonFilter);	
			//}
		}
		   BeanUtils.copyProperties(chartCommonFilter, commonFilter);
		
		//FilterValues.getCommonFilters(request, chartCommonFilter) ;
		chartCommonFilter.setRowTotal('Y');
		List<String[]> fbCountList  = fishBoneService.getKznSgnCount(chartCommonFilter);
		CommonMessage.debugMsg("fbCountList"+fbCountList);
		JSONObject chartObj = null;
		if(fbCountList != null && fbCountList.size() > 0)
		{	
			String flids=CommonFunctions.getLoginFlid(request);
			String lcnname=dashboardService.Functionallocn(flids);
			
			chartObj = processBarChart(lcnname,fbCountList,chartCommonFilter);
		
			PrintWriter out = response.getWriter();
			UIUtils.dashBoardSetChartObject(request,chartObj);
			out.print(chartObj);
			out.close();
		}
			
		}
	private JSONObject processBarChart(String tn, List<String[]> kznImplCountList,CommonFilter commonFilter){

		if( kznImplCountList == null || kznImplCountList.size() <= 1  )
			return null;
		ChartOptionBean lineChart =  new ChartOptionBean();
		List<String> xAxisCategory = new ArrayList<String>();
		List<ChartSeries> chartSeriesList =  new ArrayList<ChartSeries>();
		List<ChartYAxis> chartYAxis =  new ArrayList<ChartYAxis>();
		String[] month =  kznImplCountList.get(0);
		String[] data =  kznImplCountList.get(kznImplCountList.size()-2);
		String prevMonth = null;
	//	String subTitle = data[1];
		String subTitle = "";
		StringBuilder date = new StringBuilder();
		if(commonFilter.getRowTotal()==null)
			commonFilter.setRowTotal('N');
		if(commonFilter.getMonwise().equals("Y"))
		{
			date.append(commonFilter.getFromMonth()).append(" To ").append(commonFilter.getToMonth());
		}
		else
			date.append(commonFilter.getFromDate()).append(" To ").append(commonFilter.getToDate());
		String drillLevel =  FilterValues.getDrillHeader(data[1]);
		StringBuilder title = new StringBuilder(tn+"-Fish Bone Count - ").append( drillLevel).append( " Wide From ").append(date);
		ChartSeries timeSeries = new ChartSeries();
		List<Double> countData = new ArrayList<Double>();
		for( int i = 3;i < month.length-1;i++ ){
			countData.add(Double.parseDouble(data[i]));
			
				if( prevMonth == null || ! month[i].equals(prevMonth) ){
				xAxisCategory.add(month[i]);
			}
			prevMonth = month[i];
		}
		if(countData.size() > 0 )
		{
			timeSeries.setData(countData);
			timeSeries.setType(ChartTypes.COLUMN);
			timeSeries.setName("Fish Bone Count");
			chartSeriesList.add(timeSeries);
			
			ChartYAxis yAxis = new ChartYAxis(); 
			yAxis.setMin(0);
			yAxis.getTitle().setText("Numbers");
			chartYAxis.add(yAxis);
		}
		ChartXAxis xaxis = new ChartXAxis();
		if(commonFilter.getMonwise().equals("Y"))
			xaxis.getTitle().setText("Month");
		else
			xaxis.getTitle().setText("Date");
		lineChart.getxAxis().setTitle(xaxis.getTitle());
		return lineChart.drawChart(xAxisCategory, chartSeriesList, title.toString(), subTitle, chartYAxis);
		
	}
private JSONObject getTableModel(List<String[]> headers,String caption)
	{
		JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	//	String [] colHeader1 = new String[  headers.get(0).length + 1 ] ;
		
		String [] colHeader = headers.get(0) ;
		int header = colHeader.length; // removing -1
		CommonMessage.debugMsg("colHeader LENGTH ="+header);
		String [] headerArr = new String[header];
		for(int k=0;k<headerArr.length;k++)
			

		//CommonMessage.debugMsg(colHeader1 +"  =caption ="+colHeader );
		colHeader[2] = caption;  
		
		CommonMessage.debugMsg("colHeader[1] ="+colHeader[1]);
		CommonMessage.debugMsg("colHeader[2] ="+colHeader[2]);
		CommonMessage.debugMsg("colHeader[3] ="+colHeader[3]);
		//colHeader[1] = caption;  
	
		jqGridTableModel.setTableButton(true);	
		JqGridColModel jqGridColModel =getColModel("keyid1",50,"left");
		jqGridColModel.setHidden(true);

		jqGridTableModel.getColModel().add(jqGridColModel);
		jqGridTableModel.setRowNumbers(true);
		jqGridColModel = getColModel("keyid2",100,"left");
		jqGridTableModel.getColModel().add(jqGridColModel);
		//colHeader1[ 0 ] = colHeader[0];
		//colHeader1[ 1 ] = colHeader[1];
		headerArr[0] = "keyid";
		headerArr[1] = "keyid";
		String headerSql = "'SELECT ";
		for(int i =2; i < header; i++)
		{
			headerArr[i] = colHeader[i].replaceAll(" ", "");
			//colHeader1[ i ] = colHeader[i];
			jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex((colHeader[i]+i).replaceAll(" ", ""));
			jqGridColModel.setName((colHeader[i]+i).replaceAll(" ", ""));
					
			if(i==2 )
			{
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("left");
				jqGridColModel.setWidth(300);	
			}
			if(i>2)
			{
				jqGridColModel.setWidth(100);	
				jqGridColModel.setHidden(false);
				jqGridColModel.setAlign("right");
			}
			if(i==colHeader.length-1)
			{
				//jqGridColModel.setHidden(true);
			}
			CommonMessage.debugMsg("colHeader["+i+"]"+colHeader[i]);
			CommonMessage.debugMsg("headerArr["+i+"]"+headerArr[i]);
			jqGridTableModel.getColModel().add(jqGridColModel);
		//	headerSql =  headerSql + UIUtils.getTablemodelSql(jqGridColModel);
		}
		jqGridTableModel.getRowHeaders().add(headerArr);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		headerSql = headerSql.substring(0, headerSql.length()-1) + " FROM DUAL ";
		CommonMessage.debugMsg("headerSql.....123..."+headerSql);
		
		 	
			tableModel.set("tableHeight", "72%");
			return tableModel;
      }
	private JqGridColModel getColModel (String colIndex, int width,String allign)
	{
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colIndex);
		jqGridColModel.setName(colIndex);
		jqGridColModel.setHidden(true);			
		jqGridColModel.setWidth( width);				
		jqGridColModel.setAlign(allign);
		jqGridColModel.setEditable(false);
		return jqGridColModel;
	}

	private void deleteFishBoneChildEntry(HttpServletRequest request,HttpServletResponse response,
			FishBoneBean fishBoneBean)throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	try
		{
    		
         String DtlId = request.getParameter("DtlId");
         CommonMessage.debugMsg(" Inside Delete ::  "+DtlId);
         
    	if(httpSession !=null && user !=null)
		{
    		GenTlFishbonedtl newGenTlFishbonedtl = new GenTlFishbonedtl();
    		newGenTlFishbonedtl=(GenTlFishbonedtl)UIUtils.setBeanProperties((Object)newGenTlFishbonedtl,request);
			//JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst=(JhaTlFiveSAuditareamst)httpSession.getAttribute("existJhaTlFiveSAuditareamst");
			CommonMessage.debugMsg("newGenTlFishbonedtl.getFvasKeyid()  "+newGenTlFishbonedtl.getFisdFismKeyid());
			newGenTlFishbonedtl.setFisdKeyid(DtlId);
			if(UIUtils.isValidKeyId(newGenTlFishbonedtl.getFisdKeyid())){
				
				newGenTlFishbonedtl=fishBoneService.deleteFishBoneChildEntry(newGenTlFishbonedtl);
				httpSession.removeAttribute("GenTlFishbonedtl"+newGenTlFishbonedtl.getFisdKeyid());
				
			
			}
		
			
			JSONObject successData=new JSONObject();
    		JSONObject Fishbonedtldelete=new JSONObject();
    		String savemsg;
    	   if( newGenTlFishbonedtl.getFisdKeyid()==null )
			{
				savemsg=" Data Not Deleted ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    		successData.put("msg", savemsg);
    		Fishbonedtldelete.put("successData", successData);
    		out.print(Fishbonedtldelete.toString());
    		
		}
		}catch(Exception e)
		{
			
		}
		
	}

	
		
	

	private void deleteFishBoneMst(HttpServletRequest request,
			HttpServletResponse response, FishBoneBean fishBoneBean) throws IOException{
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		GenTlFishbonemst newGenTlFishbonemst = new GenTlFishbonemst();
    		newGenTlFishbonemst=(GenTlFishbonemst)UIUtils.setBeanProperties((Object)newGenTlFishbonemst,request);
			//JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst=(JhaTlFiveSAuditareamst)httpSession.getAttribute("existJhaTlFiveSAuditareamst");
			CommonMessage.debugMsg("newGenTlFishbonemst.getFvasKeyid()  "+newGenTlFishbonemst.getFismKeyid());
			
			if(UIUtils.isValidKeyId(newGenTlFishbonemst.getFismKeyid())){
				newGenTlFishbonemst=fishBoneService.deleteFishBoneMst(newGenTlFishbonemst);
				httpSession.removeAttribute("JhaTlFiveSAuditareamst"+newGenTlFishbonemst.getFismKeyid());
				
			
			}
		
			
			JSONObject successData=new JSONObject();
    		JSONObject Auditdatadelete=new JSONObject();
    		String savemsg;
    	   if( newGenTlFishbonemst.getFismKeyid()==null )
			{
				savemsg=" Data Not Deleted ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    		successData.put("msg", savemsg);
    		Auditdatadelete.put("successData", successData);
    		out.print(Auditdatadelete.toString());
    		
		}
		}catch(Exception e)
		{
			
		}
		
	}

	private void saveFishBoneChildEntry(HttpServletRequest request,HttpServletResponse response, FishBoneBean fishBoneBean) throws IOException{
		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	 try
         {
 	    	if( httpSession != null && user != null)
 	    	{	
 	    		
 	    		
 	    		GenTlFishbonedtl existGenTlFishbonedtl=(GenTlFishbonedtl)httpSession.getAttribute("GenTlFishbonemst");
 	    		
 	    		GenTlFishbonedtl newGenTlFishbonedtl = new GenTlFishbonedtl();
 	    		
 	    		newGenTlFishbonedtl=(GenTlFishbonedtl)UIUtils.setBeanProperties((Object)newGenTlFishbonedtl,request);
 	    		
 	    		//newGenTlFishbonemst.setFismCreatedby(user.getUsrm_ccno());
 	    		
 	    		newGenTlFishbonedtl.setFisdCreatedby(user.getUsrm_ccno());
 	    		
 	    		String openactnpln=request.getParameter("openactnpln");
 	    		
 	    		String levelno=request.getParameter("levelno");
                String OrderNo=request.getParameter("OrderNo");
 	    		String dispCode=request.getParameter("dispCode");
 	    		String ParentId=request.getParameter("ParentId");
 	    		String Masterid=request.getParameter("Masterid");
 	    		String Detailid=request.getParameter("DtlId");
 	    		String level=request.getParameter("level");
                String detlid=request.getParameter("detlid");
 	    		String Editval=request.getParameter("Editval");
 	    		
 	    		String remarks = request.getParameter("fisdRemarks");
 	    	//	newGenTlFishbonedtl.setFisdRemarks(remarks);
 	    		
 	    		newGenTlFishbonedtl.setFisdRemarks(remarks);

 	    	// ✅ default "-"
 	    	if(remarks == null || remarks.trim().length() == 0){
 	    	    newGenTlFishbonedtl.setFisdRemarks("-");
 	    	}

                
 	    	    CommonMessage.debugMsg(" Detailid :: "+Detailid+" dtlid :: "+detlid+" Editval :: "+Editval);
 	    	    
 	    	    CommonMessage.debugMsg(" ParentId :: "+ParentId+" Masterid :: "+Masterid);
 	    	    
 	    	    CommonMessage.debugMsg(" dispCode :: "+dispCode+" OrderNo :: "+OrderNo);
 	    	    
 	    	   
 	    	    CommonMessage.debugMsg(" remarks :: "  + remarks);
 	    		
 	    		
 	    		newGenTlFishbonedtl.setFisdFismKeyid(Masterid);
 	    		//newGenTlFishbonedtl.setFisdOrderno(OrderNo);
 	    		//newGenTlFishbonedtl.setFisdLevelno(levelno);
 	    		
 	    		if("detlid".equals(detlid))//dtlid   "0".equals(id)  
 	    		{
	 	    		CommonMessage.debugMsg(" Inside IF ");			
	 	    		newGenTlFishbonedtl.setFisdParentid(Detailid);
	 	    		
 	    		}else if("Smelvl".equals(detlid)){
 	    			if("level".equals(level)){
	 	    			CommonMessage.debugMsg(" Inside ELSE :: IF ");
	 	    			newGenTlFishbonedtl.setFisdParentid("FB001");
	 	    			//
 	    			}else
 	    			{
 	    				CommonMessage.debugMsg(" Inside ELSE :: ELSE :: Detail Id "+ParentId);
 	    				newGenTlFishbonedtl.setFisdParentid(ParentId);
 	    				//fishBoneBean.setFormModes("CheckValidate");
 	    				CommonMessage.debugMsg(" Inside ELSE :: ELSE "+newGenTlFishbonedtl.getFisdParentid());
 	    			
 	    			}
 	    		}
 	    		
 	    		if("Editval".equals(Editval))
 	    		{
 	    			CommonMessage.debugMsg("  Editval  :: "+Detailid);
 	    			newGenTlFishbonedtl.setFisdParentid(Detailid);
 	    			CommonMessage.debugMsg("  Editval  :: "+newGenTlFishbonedtl.getFisdParentid());
 	    		}
 	    		//newGenTlFishbonedtl.setFisd
 	    		
 	    		CommonMessage.debugMsg(" levelno :: "+levelno+" OrderNo :: "+OrderNo+" dispCode :: "+dispCode+" ParentId :: "+ParentId+" Masterid :: "+Masterid);
 	    		
 	    		CommonMessage.debugMsg(" Inside Save Action :: "+openactnpln);
 	    		
 	    		boolean insert = true;
 	    		String msgPropertyIdnt;
 				if( newGenTlFishbonedtl.getFisdKeyid() == null )
 				 {							
 					CommonMessage.debugMsg("KeyId is Null :: Before " );
 					existGenTlFishbonedtl =	fishBoneService.createChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean,Editval);
 					msgPropertyIdnt = "success-save";
 					CommonMessage.debugMsg("KeyId is Null :: After " );
 				 }	
 				else
 				  {
 					CommonMessage.debugMsg("Update function :: Before ");	
 					existGenTlFishbonedtl = fishBoneService.updateChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean);
 					insert = false;
 					msgPropertyIdnt = "success-update";
 					CommonMessage.debugMsg("Update function :: After ");	
 				  }	
 				
 				JSONObject successData = new JSONObject();	
 				JSONObject returnData = new JSONObject();
 								
 				returnData.put("formClear",false);
 				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
 				returnData.put("successData",successData);
 				returnData.put("Parentid", existGenTlFishbonedtl.getFisdParentid());
 				CommonMessage.debugMsg("Mode :"+returnData.toString());
 				out.print(returnData.toString());
 				out.close();	
 	    	}
 	    }
         catch (ValidationExceptions e) 
         {
 				CommonMessage.debugMsg("ValidationExceptions");
 				e.printStackTrace();
 				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FishBoneValidationChildEntry");
 				out.print(errMessage.toString());							    	
         }catch(Exception e)//FishBoneValidation
 	    {   
 	        e.printStackTrace();
 	        
 	    }
		
	}

	private void saveFishBone(HttpServletRequest request,HttpServletResponse response, FishBoneBean fishBoneBean) throws IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String type=request.getParameter("type");
    	
    	String DtlId = request.getParameter("DtlId");
    	
    	CommonMessage.debugMsg("detail id before save click " + DtlId);
        try
        {
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		GenTlFishbonemst existGenTlFishbonemst=(GenTlFishbonemst)httpSession.getAttribute("GenTlFishbonemst");	    		 
	    		GenTlFishbonemst newGenTlFishbonemst = new GenTlFishbonemst();
	    		newGenTlFishbonemst=(GenTlFishbonemst)UIUtils.setBeanProperties((Object)newGenTlFishbonemst,request);
	    		GenTlFishbonedtl newGenTlFishbonedtl = new GenTlFishbonedtl();
	    		newGenTlFishbonemst.setFismCreatedby(user.getUsrm_ccno());
	    		newGenTlFishbonedtl.setFisdCreatedby(user.getUsrm_ccno());
	    		String refDoctype=request.getParameter("refDoc");
	    		String mode="null";
	    		if(UIUtils.isValidKeyId(refDoctype))
	    			newGenTlFishbonemst.setFismRefdoctype(refDoctype);
	    		CommonMessage.debugMsg("FishBone refDoctype100:"+newGenTlFishbonemst.getFismRefdoctype());
	    		String openactnpln=request.getParameter("openactnpln");
	    		CommonMessage.debugMsg(" Inside Save Action :: "+openactnpln);
	    		
	    		boolean insert = true;
	    		String msgPropertyIdnt;
				if( newGenTlFishbonemst.getFismKeyid() == null )
				 {							
					CommonMessage.debugMsg("KeyId is Null :: Before " );
					existGenTlFishbonemst =	fishBoneService.create(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
					msgPropertyIdnt = "success-save";
					CommonMessage.debugMsg("KeyId is Null :: After " );
				 }	
				else
				  {
					CommonMessage.debugMsg("Update function :: Before ");	
					existGenTlFishbonemst = fishBoneService.update(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
					insert = false;
					//mode="Modify";
					msgPropertyIdnt = "success-update";
					CommonMessage.debugMsg("Update function :: After ");	
				  }	
				
				JSONObject successData = new JSONObject();	
				JSONObject returnData = new JSONObject();
								
				returnData.put("formClear",false);
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				/*if(mode!=null)
	    		{
	    		CommonMessage.debugMsg("mode");
	    			successData.put("mode", mode);
	    		
	    			
	    		}*/
				returnData.put("successData",successData);
				returnData.put("Mstkeyid", existGenTlFishbonemst.getFismKeyid());
				CommonMessage.debugMsg("Mode :"+returnData.toString());
				
				String mstKeyId = returnData.optString("Mstkeyid", "");
				CommonMessage.debugMsg("Mstkeyid = after menu creation inside servlet " + mstKeyId);
				
		
				
				

				
				returnData.put("type",type);
				out.print(returnData.toString());
				CommonMessage.debugMsg("detail id before after click " + DtlId);
				out.close();	
	    	}
	    }
        catch (ValidationExceptions e) 
        {
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "FishBoneValidation");
				out.print(errMessage.toString());							    	
        }catch(Exception e)//FishBoneValidation
	    {   
	        e.printStackTrace();
	        
	    }
	}
        

	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew)
	{
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);		
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		commonFilter.setViewClick('Y');
		CommonMessage.debugMsg( "  populate commonFilter getMachineRank " + (commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : null));
		return commonFilter;
	}
private JSONObject getTableModelForFish(List<String[]> headers) {

	JqGridTableModel jqGridTableModel = new JqGridTableModel();
	String[] colHeader = headers.get(0);
	String[] colHeader1 = headers.get(1);
	String[] emptyrow = new String[colHeader.length];
	emptyrow[0] = "";
	emptyrow[1] = "";

	jqGridTableModel.setRowNumbers(true);
    jqGridTableModel.setEnableFilter(true);
    jqGridTableModel.setTableButton(true);

	for (int i = 0; i < colHeader.length; i++) {
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setWidth(500);
		jqGridColModel.setAlign("left");
		CommonMessage.debugMsg("colHeader1[i]    "+colHeader1[i]+"    colHeader[i]  "+colHeader[i]);
		jqGridColModel.setEditable(false);	
		if(i==0){
			jqGridColModel.setWidth(70);
		}
		jqGridTableModel.getColModel().add(jqGridColModel);
	}
	jqGridTableModel.getRowHeaders().add(colHeader);
	JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
	tableModel.set("tableHeight", "89%%");
	tableModel.set("tableWidth", "107%%");
	return tableModel;
}
	private void searchFishBone(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,PrintWriter out  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{		
		Enumeration<String> params = request.getParameterNames() ;
		String prevSearchstr = UIUtils.getCookieValue(request,"fnlnsearch_str");
		String currentSearchStr = request.getParameter("search_str");
		//if(currentSearchStr.indexOf(":")>0)
			//currentSearchStr = functionalLocnServicesImpl.getNameForId(currentSearchStr);
		String countStr  = "0"; 
		List<String[]> searchList = null;
		if(currentSearchStr != null && prevSearchstr != null &&  currentSearchStr.equals(prevSearchstr))
		{
			countStr = UIUtils.getCookieValue(request,"fnlnsearch_str_cnt");
			searchList =(List<String[]>) httpSession.getAttribute("fnlnSearchList");
		}
		else{
			searchList = fishBoneService.getSearchNode(currentSearchStr,"");
			httpSession.setAttribute("fnlnSearchList", searchList);
		}
		int searchCnt = Integer.parseInt(countStr);
		JSONArray jSONArray =null;
		if( searchCnt < searchList.size() ){
			String parentId = searchList.get(searchCnt)[0];
			CommonMessage.debugMsg(parentId);
			parentId=parentId.replaceAll("/", "_");
			parentId = "#node_1-0" + parentId.replaceAll("_", "-");
			//parentId = "-" + parentId.replaceAll("/", "_");
			CommonMessage.debugMsg(parentId);
			parentId = parentId.replaceAll("-", "-#");
			//parentId = "#node_1-#node_2" + parentId;
			CommonMessage.debugMsg(parentId);
			String[] searchNode = parentId.split("-");
			
			jSONArray = JSONArray.fromArray(searchNode);
		}
		else{
			searchCnt =-1;
		}
		
		Cookie searchStrCookie =  new Cookie("fnlnsearch_str",currentSearchStr);
		searchStrCookie.setHttpOnly(true);
		searchStrCookie.setPath("/");
		searchStrCookie.setMaxAge(60*60);
		response.addCookie(searchStrCookie);
		Cookie searchCntCookie =  new Cookie("fnlnsearch_str_cnt",(searchCnt+1)+"");
		searchCntCookie.setHttpOnly(true);
		searchCntCookie.setPath("/");
		searchCntCookie.setMaxAge(60*60);
		response.addCookie(searchCntCookie);
		
		out.println(jSONArray);
	}
	private JSONObject getTableModelForFBRpt(List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		String[] colHeader1 = headers.get(0);
		String[] emptyrow = new String[colHeader.length];
		emptyrow[0] = "";
		emptyrow[1] = "";
		
		jqGridTableModel.setRowNumbers(true);
	    jqGridTableModel.setEnableFilter(true);
	    jqGridTableModel.setTableButton(true);
	   
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", "")); 
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(100);
			jqGridColModel.setAlign("left");			
			jqGridColModel.setEditable(false);			
			
			jqGridTableModel.getColModel().add(jqGridColModel);
			CommonMessage.debugMsg("colHeader1["+i+"]"+colHeader1[i]);
		}
		jqGridTableModel.getRowHeaders().add(colHeader1);
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "87%%");
		tableModel.set("tableWidth", "107%%");
		return tableModel;
	}
	
	private void LoadFishBoneTree(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws IOException{
		String parentNumber = null;
		String parentId = null;
		String elementType = null;		
		String id=null;
		String masterId=null;
		String levelno=null;
		
		parentNumber=request.getParameter("elementId");
		
		parentId = request.getParameter("parentId");
		elementType = request.getParameter("elementType");
		masterId = request.getParameter("masterId");
		levelno = request.getParameter("levelno");
		String lineId = request.getParameter("lineId");
		String Problem = request.getParameter("problem");
        
		CommonMessage.debugMsg(" Problem :: "+Problem);
		
		id=request.getParameter("id");
		
		/*String iddtl=request.getParameter("iddtl");
		if("iddtl".equals(iddtl)){CommonMessage.debugMsg(" Inside :: ");
			id=id+1;
		}
		*/
		
		if(CommonFunctions.isValidKeyId(parentNumber))
			parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
		
		CommonMessage.debugMsg("parentNumber : "+parentNumber);
		
		CommonMessage.debugMsg("elementType : "+elementType);
		CommonMessage.debugMsg("ID : "+id);
		CommonMessage.debugMsg("masterId : "+masterId);
    	response.setContentType("text/html;charset=UTF-8");
    	CommonMessage.debugMsg(" masterId  :: ");
    	
    	try {
    		JSONArray jSONArray = new JSONArray();	    		
	    	
    		CommonMessage.debugMsg(" Inside Before :: "+request.getParameter("id"));
    		CommonMessage.debugMsg(" masterId 1 :: ");
    		if("0".equals(id))
	    	{     
			   String dispCode = Problem;
			   String dispcodes;
			   CommonMessage.debugMsg(" masterId 2 :: "+dispCode);
			   
			   dispCode = UIUtils.isValidKeyId(dispCode)?dispCode:"Effect /Problem";
			  
	    	   JSONObject jSONObject = new JSONObject();
    		   JSONObject data = new JSONObject();
    		   JSONObject jsonAttr = new JSONObject();
               JSONObject metadata = new JSONObject();
              
               jsonAttr.put("id", "FB001");                         
    		   jsonAttr.put("originalId", "1");
               jsonAttr.put("elementId", "1");
               jsonAttr.put("parentId", "{}");
               jsonAttr.put("elementType",masterId);
               jsonAttr.put("displayCode", Problem);
               jsonAttr.put("OrderNo", "0");
               jsonAttr.put("LevelNo", "0");
               jsonAttr.put("href", "#");
               data.put("title", dispCode);	  
               data.put("icon", "");
    		   jSONObject.put("data",data);
    		   jSONObject.put("attr", jsonAttr);
    		   
    		   if(request.getParameter("search_str") != null)
    			   jSONObject.put("state","open");
    		   else
    			   jSONObject.put("state","closed");
    		   metadata.put("id", "1");
    		   jSONObject.put("metadata",metadata);
    		   jSONObject.put("icon",getIconImage("0"));
               jsonAttr = null;
               jSONObject.put("children","[{}]");
               jSONArray.put(jSONObject);
               jSONObject=null;	
               CommonMessage.debugMsg(" masterId 3 :: ");
	    	}
    		else
	    	{
	    		CommonMessage.debugMsg(" masterId 4 :: ");
	    		boolean allow = true;
	    		GenTlFishbonedtl genTlFishbonedtl = new GenTlFishbonedtl();	 
	    		GenTlFishbonemst genTlFishbonemst = new GenTlFishbonemst();	 
    			List <GenTlFishbonedtl> locnList=null;    			
    		
    			CommonMessage.debugMsg("ID : "+id);
    			if(CommonFunctions.isValidKeyId(id)){	    				
    				genTlFishbonedtl.setFisdParentid(id); 	
    			}
    			if(CommonFunctions.isValidKeyId(levelno)){	    				
    				genTlFishbonedtl.setFisdLevelno(levelno); 	
    			}
    			if(UIUtils.isValidKeyId(elementType))
    				genTlFishbonemst.setFismKeyid(elementType);
    			CommonMessage.debugMsg("Level : "+parentNumber);
    			/*if(UIUtils.isValidKeyId(parentNumber))
    			{
    				if(Integer.parseInt(parentNumber)>1)
    					allow = false;
    			}*/
    			
    			if(allow)
    			{
    				CommonMessage.debugMsg(" masterId 5 :: ");
    				CommonMessage.debugMsg(" Prinitng ID Values :: "+id);
    				//mspTlIndicatorsMst.setMspiCellid(UIUtils.isValidKeyId(lineId)?lineId:parentNumber);
    				//actList = monthlyPlanService.getIndicators(mspTlIndicatorsDtl,mspTlIndicatorsMst);
    				genTlFishbonemst.setFismFlid(UIUtils.isValidKeyId(lineId)?lineId:parentNumber);
    				locnList = fishBoneService.getFishBoneValues(genTlFishbonedtl,genTlFishbonemst,id,masterId);
    				CommonMessage.debugMsg(" locnList.size()>0    "+locnList.size());
    				if(locnList.size()>0){
	    				for(int i=0; i<locnList.size(); i++){
	    					CommonMessage.debugMsg(" masterId 6 :: ");
	    					JSONObject jSONObject = new JSONObject();
	            			JSONObject data = new JSONObject();
	            			JSONObject jsonAttr = new JSONObject();
	    	                JSONObject metadata = new JSONObject();
	    	                CommonMessage.debugMsg("ParentId"+locnList.get(i).getFisdParentid());
	    	                jsonAttr.put("id",locnList.get(i).getFisdKeyid());
	    	                jsonAttr.put("displayCode", locnList.get(i).getFisdCause());
	    	                jsonAttr.put("originalId", locnList.get(i).getFisdKeyid());
	    		            jsonAttr.put("elementId", locnList.get(i).getFisdKeyid());
	    	                jsonAttr.put("elementType",masterId);
	    	                jsonAttr.put("parentId", locnList.get(i).getFisdParentid());
	    	                jsonAttr.put("OrderNo",locnList.get(i).getFisdOrderno());
	    	                jsonAttr.put("LevelNo", locnList.get(i).getFisdLevelno());
	    	                jsonAttr.put("title","Fish Bone");
	    	                jsonAttr.put("href", "#");
	    	                
	    	                //id,originalId,elementId,parentId,parentId,elementType,displayCode,OrderNo,LevelNo,href,title
	    	                
	            			data.put("title", locnList.get(i).getFisdCause());
		        			//data.put("attr", jsonAttr);
		        			data.put("icon", "");
		        			jSONObject.put("data",data);
		        			jSONObject.put("attr", jsonAttr);	
		        			if(request.getParameter("search_str") != null)
		        			{
		        				jSONObject.put("state","open");
		        			}
		        			else
		        				jSONObject.put("state","closed");
		        			
			                metadata.put("id", i);
			                jSONObject.put("metadata",metadata);
			               // jSONObject.put("icon",getIconImage(locnList.get(i).getFisdLevelno()));			           
			                jsonAttr = null;
			                jSONObject.put("children","[{}]");
			                jSONArray.put(jSONObject);
			                jSONObject=null;
			                CommonMessage.debugMsg(" masterId 7 :: ");
			        	}
	    			}
    			}
    			
        	}   
           out.print(jSONArray);
    	   jSONArray=null;
    	}catch(Exception e){
           // CommonMessage.debugMsg(e);
            e.printStackTrace();
        }
        finally {
            out.close();
        }
     
	}	
	 public String getIconImage(String elementType)
	    {
	    	String imgUrl = null;
	    	if(elementType.equals("0"))
	    		imgUrl =  "images/FnLocn/company.jpg";
	    	else if(elementType.equals("1"))
	    		imgUrl =  "images/FnLocn/location.jpg";
	    	else if(elementType.equals("2"))
	    		imgUrl =  "images/FnLocn/factory.jpg";
	    	else if(elementType.equals("3")  )
	    		imgUrl =  "images/FnLocn/manufact.jpg";
	    	
	    	return imgUrl;
	    	
	    }
	    public String getImageUrl(String elementType)
	    {
	    	//CommonMessage.debugMsg("Inside getImage " +elementType);
	    	String imgUrl = null;
	    	if(elementType.equals("0"))
	    		imgUrl =  "images/companyy.jpg";
	    	else if(elementType.equals("1"))
	    		imgUrl =  "images/FnLocn/location.jpg";
	    	else if(elementType.equals("2"))
	    		imgUrl =  "images/factory_inside.jpg";
	    	else if(elementType.equals("3"))
	    		imgUrl =  "images/FnLocn/type.png";
	    	return imgUrl;
	    	
	    }
	    

}

