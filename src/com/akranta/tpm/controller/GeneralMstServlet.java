package com.akranta.tpm.controller;

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
import javax.xml.bind.ValidationException;

import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.ComboBox;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.AuditmasterModel;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;

import com.akranta.tpm.model.MasterModuleGroup;
import com.akranta.tpm.service.MasterModuleGroupService;
import com.akranta.tpm.service.impl.MasterModuleGroupServiceImpl;
//import com.akranta.tpm.service.impl.SparesPullListServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.PrjConstants;

public class GeneralMstServlet extends HttpServlet {
	
	/**
	 * Created By Suresh.k 
	 * Created On 22-Nov-2011
	 */
	private static final long serialVersionUID = 1L;
	private static final String commonFilterIden = "Related_CommonFilter";
	private static final String commonFilterIden1 = "Related_CommonFilterData";
	private final String titleGen ="General";
	private final String titleBdm ="Breakdown";
	private final String titleJh ="Jishu Hozen";
	private final String titleKzn ="Kobetsu Kaizen";
	private final String titleEt ="Education And Training";
	private final String titleQtm ="Quality Maintenance";
	private final String titlePmd ="Planned Maintenance";
	private final String titleEhs ="EHS";
	private final String titleAdm ="Administration";
	private final String titleAll ="All";
	private final String titleSAP ="SAP";
	
	MasterModuleGroupService masterModuleGroupService;
	  public GeneralMstServlet()  {
	        super();
	  /*      try {
				masterModuleGroupService = new MasterModuleGroupServiceImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        // TODO Auto-generated constructor stub
	         * 
	         */
	    }
	    
	    
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			processRequest(request, response);
		}
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws ServletException, IOException {
			HttpSession httpSession = request.getSession(false);
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			ComboFilter currentFilter = new ComboFilter(); //@by iyyappan
		    CommonFilter  commonfilter = new CommonFilter(); //@iyyappan
			List<ComboBox> comboList = new ArrayList<ComboBox>(); //@by iyyappan
			response.setContentType("text/html");
			response.setContentType("text/json");
			  
			try {
			    
			   String action = UIUtils.getActionPart(request);
			 //  ComboFilter comboFilter = new ComboFilter();
			   try {
				   masterModuleGroupService = (MasterModuleGroupServiceImpl)UIUtils.getServiceObject(request,"MasterModuleGroupServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				
			   if( action.equals("general_input.gnms") ){	
				
				   String title = request.getParameter("title");
				    if( title == null )
					   title = titleAll;
				   
				    //CommonMessage.debugMsg("title " + title );
				    request.setAttribute("showGen", true);
				    
				    request.setAttribute("titleGen", titleGen);
				    request.setAttribute("titleBdm", titleBdm);
				    request.setAttribute("titleJh", titleJh);
				    request.setAttribute("titleAdm", titleAdm);
				    request.setAttribute("titleEhs", titleEhs);
				    request.setAttribute("titleKzn", titleKzn);
				    request.setAttribute("titlePmd", titlePmd);
				    request.setAttribute("titleQtm", titleQtm);
				    request.setAttribute("titleAll", titleAll);
					request.setAttribute("titleEt", titleEt);
					request.setAttribute("titleSAP", titleSAP);
					
					request.setAttribute("tblGen", titleGen.replaceAll(" ", ""));
				    request.setAttribute("tblBdm", titleBdm.replaceAll(" ", ""));
				    request.setAttribute("tblJh", titleJh.replaceAll(" ", ""));
				    request.setAttribute("tblAdm", titleAdm.replaceAll(" ", ""));
				    request.setAttribute("tblEhs", titleEhs.replaceAll(" ", ""));
				    request.setAttribute("tblKzn", titleKzn.replaceAll(" ", ""));
				    request.setAttribute("tblPmd", titlePmd.replaceAll(" ", ""));
				    request.setAttribute("tblQtm", titleQtm.replaceAll(" ", ""));
				    request.setAttribute("tblAll", titleAll.replaceAll(" ", ""));
					request.setAttribute("tblEt", titleEt.replaceAll(" ", ""));
					request.setAttribute("tblSAP", titleSAP.replaceAll(" ", ""));
					
					request.setAttribute("showBdm", true);
				    request.setAttribute("showJH", true);
				    request.setAttribute("showPM", true);
				    request.setAttribute("showAdm", true);
				    request.setAttribute("showET", true);
				    request.setAttribute("showHSE", true);
				    request.setAttribute("showKZN", true);
				    request.setAttribute("showQTM", true);
				    request.setAttribute("showAll", true);
				    request.setAttribute("showSAP", true);
					request.setAttribute("title", title);
					
					UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("general_getCol.gnms") ) //@by Prasanth
			   { 
				   PrintWriter out = response.getWriter();
				   String module = request.getParameter("moduleCol");
				   String propertyFileTag ="masterMainTable";
				   if( "ALL".equalsIgnoreCase(module) )
					   propertyFileTag ="masterMainTableAll";
				   String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.MasterTableIntegrate", propertyFileTag);
				  
				   response.setContentType("text/html");
				   out.print(colModel);
				   out.close();
			   }
			   
			   else if( action.equals("general_getData.gnms") ){ //@by Prasanth	

				   PrintWriter out = response.getWriter();
				   String module = request.getParameter("module");
				   CommonMessage.debugMsg(" module " + module);
				   	if(module != null )
				   	{	
				   		if( module.equals(titleGen.replaceAll(" ", "")))
				   			module = "GEN";
				   		else if(module.equals(titleBdm.replaceAll(" ", "")))
				   			module = "BDM";
				   		else if(module.equals(titleJh.replaceAll(" ", "")))
				   			module = "ABN";
				   		else if(module.equals(titleKzn.replaceAll(" ", "")))
				   			module = "KZN";
				   		else if(module.equals(titlePmd.replaceAll(" ", "")))
				   			module = "PMD";
				   		else if(module.equals(titleEhs.replaceAll(" ", "")))
				   			module = "SFT";
				   		else if(module.equals(titleEt.replaceAll(" ", "")))
				   			module = "ET";
				   		else if(module.equals(titleAdm.replaceAll(" ", "")))
				   			module = "ADM";
				   		else if(module.equals(titleQtm.replaceAll(" ", "")))
				   			module = "QTM";
				   		else if(titleAll.replaceAll(" ", "").equalsIgnoreCase(module))
				   			module = "ALL";
				   		else if(titleSAP.replaceAll(" ", "").equalsIgnoreCase(module))
				   			module = "SAP";
				   	}
					List< MasterModuleGroup> masterModuleGroups  = masterModuleGroupService.getAllMasterModuleGroup(user.getUsrm_keyid(),module);
					
					JSONObject genMastTbl = new JSONObject();
					
					genMastTbl.put("page", 1);
					if( masterModuleGroups != null  ){
						genMastTbl.put("total", masterModuleGroups.size());
						genMastTbl.put("records", masterModuleGroups.size());
					}
					org.json.simple.JSONArray rows = new org.json.simple.JSONArray(); 
			        int i=1;
			        org.json.simple.JSONObject cellobj = null;

			        for( MasterModuleGroup masterModuleGroup : masterModuleGroups)
					{
			        		cellobj =new org.json.simple.JSONObject();
			                cellobj.put("id",i++);
			                org.json.simple.JSONArray cell=new org.json.simple.JSONArray();
			                cell.add(masterModuleGroup.getMenuCaption());
			                cell.add(masterModuleGroup.getNumberRecords());
			                cell.add(masterModuleGroup.getLastModidied());
			                cell.add(masterModuleGroup.getModule());
			                cell.add(masterModuleGroup.getMenuNumber());
			                cell.add(masterModuleGroup.getMenuName());
			                cell.add(masterModuleGroup.getIsMMC());
			                cell.add(masterModuleGroup.getLoadFormArgument());
			              //  CommonMessage.debugMsg("masterModuleGroup.getMenuCaption() " + masterModuleGroup.getMenuCaption());
			                cellobj.put("cell",cell);
			                
			                // cell.clear();
			                rows.add(cellobj);
			                //cfoell.clear();
			       }
			        genMastTbl.put("rows", rows);
			        out.println(genMastTbl);
			        
			   }
			   
			   else if(action.equals("abnRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleJh);
				   request.setAttribute("showJH", true);
				   request.setAttribute("titleJh", titleJh);
				   request.setAttribute("tblJh", titleJh.replaceAll(" ", ""));
				   
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("bdmRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleBdm);
				   request.setAttribute("showBdm", true);
				   request.setAttribute("titleBdm", titleBdm);
				   request.setAttribute("tblBdm", titleBdm.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("kznRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleKzn);
				   request.setAttribute("showKZN", true);
				   request.setAttribute("titleKzn", titleKzn);
				   request.setAttribute("tblKzn", titleKzn.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("qtmRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleQtm);
				   request.setAttribute("titleQtm", titleQtm);
				   request.setAttribute("tblQtm", titleQtm.replaceAll(" ", ""));
				   request.setAttribute("showQTM", true);
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("pmRelatedMst_input.gnms") ){
				   request.setAttribute("title", titlePmd);
				   request.setAttribute("showPM", true);
				   request.setAttribute("titlePmd", titlePmd);
				   request.setAttribute("tblPmd", titlePmd.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("hseRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleEhs);
				   request.setAttribute("showHSE", true);
				   request.setAttribute("titleEhs", titleEhs);
				   request.setAttribute("tblEhs", titleEhs.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("etRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleEt);
				   request.setAttribute("showET", true);
				   request.setAttribute("titleEt", titleEt);
				   request.setAttribute("tblEt", titleEt.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("admRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleAdm);
				   request.setAttribute("showAdm", true);
				   request.setAttribute("titleAdm", titleAdm);
				   request.setAttribute("tblAdm", titleAdm.replaceAll(" ", ""));
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   
			   else if(action.equals("sapMainNotificationType_input.sapinfo")){
				    
				   UIUtils.forwardRequest(request, response,"/pages/Mastermaininfo.jsp");
			   }
			   else if(action.equals("sapMainNotificationType_getCol.sapinfo")){
                  	
				 //  String sapinfo=request.getParameter("SapInfo");
				    PrintWriter out=response.getWriter();
					HttpSession httpsession=request.getSession(false);
					String tableModel=UIUtils.getPropertyValue("com.akranta.tpm.resources.SapInformation","Sapinfo");
					net.sf.json.JSONObject jsonob=net.sf.json.JSONObject.fromString(tableModel);
					httpSession.removeAttribute("SapMainNotification");
					httpSession.setAttribute("SapMainNotification",jsonob);
					out.println(jsonob);
				   
			   }
			   else if(action.equals("sapMainNotificationType_getData.sapinfo"))
			   {
				   PrintWriter out=null;
				   HttpSession httpsession=request.getSession(false);
				     CommonFilter commonFilter=populateCommonFilter(request,"SapInformationView",true);
					 GridParams gridParams=new GridParams();
					 FilterValues.populateGridParams(request,gridParams);
				   
					 
					 
			   }			   
			    else if(action.equals("Auditmasters_input.gnms")){
				   CommonMessage.debugMsg("Inside the JspPage");
				   
				   String title = request.getParameter("title");
				   CommonMessage.debugMsg("The Title IS"+title);
				    if( title == null )
					   title = titleAll;
				   request.setAttribute("showGen", true);   
				    request.setAttribute("titleGen", titleGen);
				    request.setAttribute("titleBdm", titleBdm);
				    request.setAttribute("titleJh", titleJh);
				    request.setAttribute("titleAdm", titleAdm);
				    request.setAttribute("titleEhs", titleEhs);
				    request.setAttribute("titleKzn", titleKzn);
				    request.setAttribute("titlePmd", titlePmd);
				    request.setAttribute("titleQtm", titleQtm);
				    request.setAttribute("titleAll", titleAll);
					request.setAttribute("titleEt", titleEt);
					request.setAttribute("titleSAP", titleSAP);
					
					request.setAttribute("tblGen", titleGen.replaceAll(" ", ""));
				    request.setAttribute("tblBdm", titleBdm.replaceAll(" ", ""));
				    request.setAttribute("tblJh", titleJh.replaceAll(" ", ""));
				    request.setAttribute("tblAdm", titleAdm.replaceAll(" ", ""));
				    request.setAttribute("tblEhs", titleEhs.replaceAll(" ", ""));
				    request.setAttribute("tblKzn", titleKzn.replaceAll(" ", ""));
				    request.setAttribute("tblPmd", titlePmd.replaceAll(" ", ""));
				    request.setAttribute("tblQtm", titleQtm.replaceAll(" ", ""));
				    request.setAttribute("tblAll", titleAll.replaceAll(" ", ""));
					request.setAttribute("tblEt", titleEt.replaceAll(" ", ""));
					request.setAttribute("tblSAP", titleSAP.replaceAll(" ", ""));
					
					request.setAttribute("showBdm", true);
				    request.setAttribute("showJH", true);
				    request.setAttribute("showPM", true);
				    request.setAttribute("showAdm", true);
				    request.setAttribute("showET", true);
				    request.setAttribute("showHSE", true);
				    request.setAttribute("showKZN", true);
				    request.setAttribute("showQTM", true);
				    request.setAttribute("showAll", true);
				    request.setAttribute("showSAP", true);
					request.setAttribute("title", title);
				   
				   UIUtils.forwardRequest(request, response,"/pages/Auditmasters.jsp");
			   }
			   
			   else if(action.equals("Auditmasters_getCol.gnms")){
				   PrintWriter out=response.getWriter();
				   HttpSession httpsession=request.getSession(false);
				   String modules = request.getParameter("moduleCol");
				   CommonMessage.debugMsg("The getcol data"+modules);
				  String getPropertyfileinfo ="masterMainTable";
				   if( "ALL".equalsIgnoreCase(modules) )
					   getPropertyfileinfo ="masterMainTableAll";
				   String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.Auditmaster",getPropertyfileinfo);
				   response.setContentType("text/html");
				   out.print(colModel);
				   out.close();   
			   }
			   
			   else if(action.equals("Auditmasters_getData.gnms")){
				     CommonMessage.debugMsg("Inside the getData Function");
				     String Module=request.getParameter("module");
					 CommonMessage.debugMsg("The Module Data Are"+Module);
					 PrintWriter out=response.getWriter();
					 	if(Module != null )
					   	{	
					   		if( Module.equals(titleGen.replaceAll(" ", "")))
					   			Module = "GEN";
					   		else if(Module.equals(titleBdm.replaceAll(" ", "")))
					   			Module = "BDM";
					   		else if(Module.equals(titleJh.replaceAll(" ", "")))
					   			Module = "ABN";
					   		else if(Module.equals(titleKzn.replaceAll(" ", "")))
					   			Module = "KZN";
					   		else if(Module.equals(titlePmd.replaceAll(" ", "")))
					   			Module = "PMD";
					   		else if(Module.equals(titleEhs.replaceAll(" ", "")))
					   			Module = "SFT";
					   		else if(Module.equals(titleEt.replaceAll(" ", "")))
					   			Module = "ET";
					   		else if(Module.equals(titleAdm.replaceAll(" ", "")))
					   			Module = "ADM";
					   		else if(Module.equals(titleQtm.replaceAll(" ", "")))
					   			Module = "QTM";
					   		else if(titleAll.replaceAll(" ", "").equalsIgnoreCase(Module))
					   			Module = "ALL";
					   		else if(titleSAP.replaceAll(" ", "").equalsIgnoreCase(Module))
					   			Module = "SAP";
					   		
				  	}
					 	List<AuditmasterModel> mastermodelgroup=masterModuleGroupService.getauditmastermodule(user.getUsrm_keyid(),Module);
	                     JSONObject genMastTbl = new JSONObject();
						  genMastTbl.put("page", 1);
						if( mastermodelgroup != null  ){
							genMastTbl.put("total", mastermodelgroup.size());
							genMastTbl.put("records", mastermodelgroup.size());
						}
						org.json.simple.JSONArray rows = new org.json.simple.JSONArray(); 
				        int i=1;
				        org.json.simple.JSONObject cellobj = null;

				        for( AuditmasterModel masterModuleGroup : mastermodelgroup)
						{
				        		cellobj =new org.json.simple.JSONObject();
				                cellobj.put("id",i++);
				                org.json.simple.JSONArray cell=new org.json.simple.JSONArray();
					              cell.add(masterModuleGroup.getAtdactive());
					              cell.add(masterModuleGroup.getAtdcreatedby());
                                  cell.add(masterModuleGroup.getAtdcreatedon());
                                  cell.add(masterModuleGroup.getAtdmodifyon());
                                  cell.add(masterModuleGroup.getAtdtablename());
                                  cell.add(masterModuleGroup.getAtdtriggerenable());
                                  cellobj.put("cell",cell);
				                rows.add(cellobj);
				       }
				        genMastTbl.put("rows",rows);
				        out.println(genMastTbl);	
				   }
			   
			    else if(action.equals("Auditmasters_save.gnms")){
					 savedata(request,response);
					 } 
			   else if(action.equals("sapRelatedMst_input.gnms") ){
				   request.setAttribute("title", titleSAP);
				   request.setAttribute("showSAP", true);
				   request.setAttribute("titleSAP", titleSAP);
				   request.setAttribute("tblSAP", titleSAP.replaceAll(" ", ""));
				   
				   UIUtils.forwardRequest(request, response, "/pages/generalmst.jsp");
			   }
			   else if(action.equals("PhenomenaMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.PHENOMENA_URL,PrjConstants.PHENOMENA_CAPTION,PrjConstants.PHENOMENA_MENUNAME);
			   }
			   else if(action.equals("CauseMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.CAUSE_URL,PrjConstants.CAUSE_CAPTION,PrjConstants.CAUSE_MENUNAME);				   
			   }			   
			   else if(action.equals("FailureTypeMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.FAILURE_TYPE_URL,PrjConstants.FAILURE_TYPE_CAPTION,PrjConstants.FAILURE_TYPE_MENUNAME);				  
			   }
			   else if(action.equals("BDClassifcnMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.BDCLASSFCN_URL,PrjConstants.BDCLASSFCN_CAPTION,PrjConstants.BDCLASSFCN_MENUNAME);				  
			   }
			   else if(action.equals("AlarmMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.ALARM_URL,PrjConstants.ALARM_CAPTION,PrjConstants.ALARM_MENUNAME);	
			   }
			   else if(action.equals("AssmMst_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.ASSM_URL,PrjConstants.ASSM_CAPTION,PrjConstants.ASSM_MENUNAME);
			   }
			   
			   else if(action.equals("ClassFication_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.CLASSIFICATION_URL,PrjConstants.CLASSIFICATION_CAPTION,PrjConstants.CLASSIFICATION_MENUNAME);
			   }
			   
			   else if(action.equals("Catagory_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.CATAGORY_URL,PrjConstants.CATAGORY_CAPTION,PrjConstants.CATAGORY_MENUNAME);
			   }
			   
			   else if(action.equals("SubCatagory_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.SUBCATAGORY_URL,PrjConstants.SUBCATAGORY_CAPTION,PrjConstants.SUBCATAGORY_MENUNAME);
			   }
			   else if(action.equals("RoleMaster_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.ROLEMASTER_URL,PrjConstants.ROLEMASTER_CAPTION,PrjConstants.ROLEMASTER_MENUNAME);
			   }
			   else if(action.equals("WorkFlowMenu_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.WORKFLOWMLINK_URL,PrjConstants.WORKFLOWMLINK_CAPTION,PrjConstants.WORKFLOWMLINK_MENUNAME);
			   }
			   else if(action.equals("ProbablityMaster_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.PROBABLITYMST_URL,PrjConstants.PROBABLITYMST_CAPTION,PrjConstants.PROBABLITYMST_MENUNAME);
			   }
			   else if(action.equals("SeviorityMaster_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.SEVIORITYMST_URL,PrjConstants.SEVIORITYMST_CAPTION,PrjConstants.SEVIORITYMST_MENUNAME);
			   }
			   else if(action.equals("RiskLevelMaster_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.RISKLEVMST_URL,PrjConstants.RISKLEVMST_CAPTION,PrjConstants.RISKLEVMST_MENUNAME);
			   }
			   else if(action.equals("ControlTypeMaster_link.gnms") ){
				   loadMasterLink(request, response,"Y", PrjConstants.CONTROLTYPEMST_URL,PrjConstants.CONTROLTYPEMST_CAPTION,PrjConstants.CONTROLTYPEMST_MENUNAME);
			   }
			   else if( action.equals("loadmst_grid.gnms") ){	
				    UIUtils.displayRequestParamsValue(request);
				    String menuCaptionLink = (String) request.getAttribute("menuCaption");
				    String loadFormArgLink = (String)request.getAttribute("loadFormArg");
				    String isMMCLink = (String)request.getAttribute("isMMC");
				    String menuNameLink = (String)request.getAttribute("menuName");
				    
				    String menuCaption = request.getParameter("menuCaption");
				    String loadFormArg = request.getParameter("loadFormArg");
				    String isMMC = request.getParameter("isMMC");
				    String clickEnable = request.getParameter("clickEnable");
				    if(UIUtils.isValidKeyId(menuCaptionLink))
				    	menuCaption = menuCaptionLink;
				    if(UIUtils.isValidKeyId(loadFormArgLink))
				    	loadFormArg = loadFormArgLink;
				    if(UIUtils.isValidKeyId(isMMCLink))
				    	isMMC = isMMCLink;
					 httpSession.setAttribute("menuCaptionGenMstSvt",menuCaption);
					 httpSession.setAttribute("menuNameGenMstSvt", loadFormArg);
					 request.setAttribute("clickEnable", clickEnable);
					 if( UIUtils.isValidKeyId(isMMC )  && isMMC.equals("Y") ) // load master table configuration
					 {
						 String menuName = request.getParameter("menuName");
						 if(UIUtils.isValidKeyId(menuNameLink))
							 menuName = menuNameLink;
						
						 request.setAttribute("menuName", menuName);
						 request.setAttribute("menuCaption", menuCaption);
						 request.setAttribute("masterForm", "Y");
						 UIUtils.forwardRequest(request, response, "mmc_input.mastertblconfig");
					 }
					 else if( UIUtils.isValidKeyId(isMMC )  && isMMC.equals("D") )	{
						 RequestDispatcher rd = request.getRequestDispatcher(loadFormArg); 
						 rd.forward(request, response);						 
					 }
					 else{
						 request.setAttribute("masterGridURL", "loadmastergrid_input.gnms");
						 request.setAttribute("menuCaption", menuCaption);
						 request.setAttribute("menuName", loadFormArg);
						 request.setAttribute("masterForm", "Y");
						 RequestDispatcher rd = request.getRequestDispatcher("/pages/commonmastergrid.jsp"); 
						 rd.forward(request, response);
					 }	 
				 }	
			   else if( action.equals("loadmastergrid_getCol.gnms")){	
					 
					 String menuCaption = (String) httpSession.getAttribute("menuCaptionGenMstSvt");
					 
					 httpSession.removeAttribute("CaptionMenu");
					 httpSession.setAttribute("CaptionMenu", menuCaption);
					 String menuName = (String) httpSession.getAttribute("menuNameGenMstSvt");					 
					 String rowStart = request.getParameter("rowStart");
					 String rowEnd = request.getParameter("rowEnd");
					 String active = request.getParameter("active");
					 PrintWriter out = response.getWriter();
					 
					 String loadFormArg = menuName; 
					 menuName = menuName.replace("?", "");
					 GridParams gridParams = new GridParams();  
					 FilterValues.populateGridParams(request,gridParams );
					 gridParams.setFromRow("1");
					 gridParams.setToRow("2");
					 
					 httpSession.removeAttribute(commonFilterIden+menuName);
					 httpSession.setAttribute(commonFilterIden+menuName, active);					 
					 //String count = masterModuleGroupService.getMstDatasCount(menuName, active,gridParams);
				//	 httpSession.removeAttribute("CountData");
				//	 httpSession.setAttribute("CountData", count);					 
					 httpSession.removeAttribute(menuName+"gridParams");					 
					 List<String []> sprMasterList  =masterModuleGroupService.getRelatedMst(menuCaption,menuName,active,gridParams);
					  CommonMessage.debugMsg("The List "+sprMasterList);
					 // JSONObject listToJsonObject = UIUtils.convertListToJqGridTableObject(sprMasterList, request, 1, 0,Integer.parseInt(count));
					// httpSession.setAttribute("masterDatas", listToJsonObject);
					 int count = 0;
					 String chkBoxHide = request.getParameter("chkHide");
					 JSONObject jsonObject = getTableModel(sprMasterList,chkBoxHide);
					 if(chkBoxHide != null &&  chkBoxHide.equals("Y"))
					 {	 httpSession.removeAttribute("ColModel");
					 	 httpSession.setAttribute("ColModel", jsonObject);}
						 jsonObject.put("tableCaption", menuCaption);
						 jsonObject.put("isGroupBy", "false");
						 jsonObject.put("isRowNumbers", "true");
						 jsonObject.put("groupSummary", "false");
						 //"&datasCount="+Integer.parseInt(count)
						 jsonObject.put("dataURL", "getrelated_getData.gnms?q=2&menuCaption="+menuCaption+"&menuName="+menuName+"&datasCount="+count+"&masterForm=Y");
						 out.println(jsonObject);
				    }
			   
			    else if( action.equals("getrelated_getData.gnms")){
				   	  PrintWriter out = response.getWriter();		
				        	  
	        	   	//  String count =  (String) httpSession.getAttribute("CountData");
				   	  
	        
	        		  String menuCaption = request.getParameter("menuCaption");
	        		  CommonMessage.debugMsg("The menuCaption"+menuCaption);
	        		  String menuName = request.getParameter("menuName");
	        		  CommonMessage.debugMsg("The menuName"+menuName);
	        		  String menuNameIdn = (String) httpSession.getAttribute("menuNameGenMstSvt");
	        		  menuNameIdn = menuNameIdn.replace("?", "");
	        		  String active = (String)httpSession.getAttribute(commonFilterIden+menuNameIdn);
	        		  GridParams gridParams =(GridParams) httpSession.getAttribute(menuNameIdn+"gridParams");
	        		  if( gridParams == null )
	        			  gridParams = new GridParams(); 
	        		  
	        		  FilterValues.populateGridParams(request,gridParams);
	        		  String count = masterModuleGroupService.getMstDatasCount(menuName, active,gridParams);
	        		  CommonMessage.debugMsg("The Count"+count);
	        		  httpSession.removeAttribute(menuNameIdn+"gridParams");
	        		  httpSession.setAttribute(menuNameIdn+"gridParams" ,gridParams);
					  List<String []> masterList  = masterModuleGroupService.getRelatedMst(menuCaption,menuName,active,gridParams);
					  CommonMessage.debugMsg("The Master List"+masterList);
					  int cnt = Integer.parseInt(count);
					  Long totalRecords = (long)cnt;		
	        		 // JSONObject jsonObject = UIUtils.convertListToJqGridTableObject(masterList, request,  1, 1,Integer.parseInt(count));
	        		  JSONObject jsonObject = UIUtils.convertToJqGridTableObject(masterList, request, 1, 1, totalRecords+1);
	        		  out.println(jsonObject);
			   }
			   
			    else if(action.equals("sapMainOrderType_input.sapinfo")){	
			    CommonMessage.debugMsg("Inside the jsppage");
			    UIUtils.forwardRequest(request, response,"/pages/Mastermaininfo.jsp"); 
			    }
			   
			  /*  else if(action.equals("sapMainOrderType_getData.sapinfo")){
			    	
					   PrintWriter out=response.getWriter();
					    String menuCaption = (String) httpSession.getAttribute("menuCaptionGenMstSvt");
						 
						 httpSession.removeAttribute("CaptionMenu");
						 httpSession.setAttribute("CaptionMenu", menuCaption);
						 String menuName = (String) httpSession.getAttribute("menuNameGenMstSvt");					 
						 String rowStart = request.getParameter("rowStart");
						 String rowEnd = request.getParameter("rowEnd");
						 String active = request.getParameter("active");
						 
						 String loadFormArg = menuName; 
						 menuName = menuName.replace("?", "");
						 GridParams gridParams = new GridParams();  
						 FilterValues.populateGridParams(request,gridParams );
						 gridParams.setFromRow("1");
						 gridParams.setToRow("2");
						 
						 httpSession.removeAttribute(commonFilterIden+menuName);
						 httpSession.setAttribute(commonFilterIden+menuName, active);					 
						 //String count = masterModuleGroupService.getMstDatasCount(menuName, active,gridParams);
					//	 httpSession.removeAttribute("CountData");
					//	 httpSession.setAttribute("CountData", count);					 
						 httpSession.removeAttribute(menuName+"gridParams");					 
						 List<String []> sprMasterList  =masterModuleGroupService.getRelatedMst(menuCaption,menuName,active,gridParams);
						  CommonMessage.debugMsg("The List "+sprMasterList);
						 // JSONObject listToJsonObject = UIUtils.convertListToJqGridTableObject(sprMasterList, request, 1, 0,Integer.parseInt(count));
						// httpSession.setAttribute("masterDatas", listToJsonObject);
						 int count = 0;
						 String chkBoxHide = request.getParameter("chkHide");
						 JSONObject jsonObject = getTableModel(sprMasterList,chkBoxHide);
						 if(chkBoxHide != null &&  chkBoxHide.equals("Y"))
						 {	 httpSession.removeAttribute("ColModel");
						 	 httpSession.setAttribute("ColModel", jsonObject);
						 	 }
							 jsonObject.put("tableCaption", menuCaption);
							 jsonObject.put("isGroupBy", "false");
							 jsonObject.put("isRowNumbers", "true");
							 jsonObject.put("groupSummary", "false");
							 //"&datasCount="+Integer.parseInt(count)
							 jsonObject.put("dataURL", "getrelated_getData.gnms?q=2&menuCaption="+menuCaption+"&menuName="+menuName+"&datasCount="+count+"&masterForm=Y");
							 out.println(jsonObject);
					    }
			   
			    else if(action.equals("sapMainOrderType_getData.sapinfo")){
			     	
			       	
			    } */
				  
					   
				    
			   
			   else if( action.equals("getrelated_getExcel.gnms") ){	
				   getExcelData(request,response);
					
			   }
			   else if(action.equals("Auditreport_input.gnms")){
				   CommonMessage.debugMsg("AuditReport");
				   UIUtils.forwardRequest(request, response,"/pages/AuditReports.jsp");
			   }
			   else if(action.equals("getTableNameCombo.gnms")) // @by iyyappan
				{
					String title=request.getParameter("cmbTxt");
					if(title != null )
				   	{	
				   		if( title.equals("General"))
				   			title = "GEN";
				   		else if(title.equals("Breakdown"))
				   			title = "BDM";
				   		else if(title.equals("Jishu Hozen"))
				   			title = "ABN";
				   		else if(title.equals("Kobetsu Kaizen"))
				   			title = "KZN";
				   		else if(title.equals("PM"))
				   			title = "PMD";
				   		else if(title.equals("EHS"))
				   			title = "SFT";
				   		else if(title.equals("ET"))
				   			title = "ET";
				   		else if(title.equals("Administration"))
				   			title = "ADM";
				   		else if(title.equals("QM"))
				   			title = "QTM";
				   		else if(title.equals("SAP"))
				   			title = "SAP";
				   	}
				   	currentFilter = UIUtils.fillComboFilter(request);
					commonfilter.setPillarid(currentFilter);
					commonfilter.settitle(title);
					List<ComboBox> auditkeyid=masterModuleGroupService.getTableCombo(commonfilter);
					 UIUtils.writeComboBox(response,auditkeyid,currentFilter);
		    	}
			   else if(action.equals("Auditreport_getCol.gnms")){
				   AuditReportMainGrid(request,response);
			   }
			   
			   else if(action.equals("Auditreport_getData.gnms")){
				  
				  try
				  {
				   UIUtils.displayRequestParamsValue(request);
				   String tablename=request.getParameter("tablename");
			       CommonFilter commonFilter=populateCommonFilter(request, "AuditReportView",false);
			       
			          if(UIUtils.isValidKeyId(tablename)){
			    	   commonFilter.setAbnViewType(tablename);
			    	   List<String[]> auditdata=masterModuleGroupService.auditreportdata(commonFilter);
			    	   JSONObject jsonobjdata=UIUtils.convertToJqGridTableObject(auditdata,request,2,0,commonFilter.getTotalRecordCnt());
			    	   PrintWriter out=response.getWriter();
			    	   out.println(jsonobjdata);
			    	   commonFilter.setViewClick('N');
			    	   httpSession.removeAttribute("AuditReportCol");
			    	   httpSession.setAttribute("AuditReportCol",jsonobjdata);
			    	   
			       }
				  }
				 catch(Exception e){
					 e.printStackTrace();
				 }   
			   }
			  
			   else if(action.equals("Audit_combo.gnms"))
			   {  
				   ComboFilter comboFilter=UIUtils.fillComboFilter(request);
				   List<ComboBox> auditkeyid=masterModuleGroupService.getauditcombo(comboFilter);
				   CommonMessage.debugMsg("The Audit Combo Value"+auditkeyid);
				   UIUtils.writeComboBox(response,auditkeyid,comboFilter);
			   }
			   
			         else if(action.equals("Auditreport_getExcel.gnms")){
					 try{
						 CommonMessage.debugMsg("Audit Report Excel");
						 HttpSession httpsession=request.getSession(false);
						 CommonFilter commonFilter=populateCommonFilter(request, "AuditReportview",false);
						 JSONObject jsonobj=(JSONObject) httpsession.getAttribute("AuditReportCol");
						 jsonobj.put("title","Audit Report View");
						 String format=ExcelUtils.getFormat(request);
						 CommonMessage.debugMsg("The Format Is"+format);
						 Workbook wb=masterModuleGroupService.getauditdataexcel(commonFilter,jsonobj, format); 
						 ExcelUtils.writeToResponse(response, wb,"Audit Report View", format);
					 }
					 catch(Exception e){
					    e.printStackTrace();
					 } 
					 
				   }
			   
			   else if( action.equals("getrelated_getMakeActive.gnms")){	
				  
					try
					{
						String menuName = (String) httpSession.getAttribute("menuNameGenMstSvt");	
						menuName = menuName.replace("?", "");
						String menuCaption = (String) httpSession.getAttribute("CaptionMenu");
						List<String > paramValues = new ArrayList<String>();
						PrintWriter out = response.getWriter();		
						String keyId = request.getParameter("keyIds");	
						CommonMessage.debugMsg(keyId +"keyIdkeyIdkeyIdkeyId");
						String[] temp;
						String delimiter = ",";					
						temp = ((String) keyId).split(delimiter);
						for(int i =0; i < temp.length ; i++)
						{
							  String data = temp[i];
							  paramValues.add(data);							
						}	
						
						if(UIUtils.isValidKeyId(keyId))
						{
							 CommonMessage.debugMsg("keyId.."+keyId);								
							 masterModuleGroupService.getMakeactive(menuCaption,menuName,paramValues);							 
							 JSONObject successData = new JSONObject();
							 successData.put("msg","Record is Activated Successfully");								
							 JSONObject returnData = new JSONObject();						
							 returnData.put("successData", successData);		
							
							 out.print(returnData.toString());
						}
						else{
							JSONObject err = new JSONObject();
							JSONObject returnData = new JSONObject();
							err.put("Data", "No Data Selected");
							err.put("keyId", keyId);
							returnData.put("successData", err);
							out.print(returnData);
						}
					}
					catch(Exception e){
						JSONObject err = new JSONObject();
						err.put("EquioInact", "Null");
					}
				}
			   
			   
			  /* else if( action.equals("navigate_form.gnms") ){
				   RequestDispatcher rd = request.getRequestDispatcher("/pages/Employee.jsp"); 
				   rd.forward(request, response);
			   }*/
			   
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}

		  


 private void AuditReportMainGrid(HttpServletRequest request,HttpServletResponse response)throws Exception{
	   
	 
	 HttpSession httpSession=request.getSession(false);
	 PrintWriter out=response.getWriter();
	 String tableName=request.getParameter("tablename");
     CommonMessage.debugMsg("The tableName is"+tableName);
	 CommonFilter commonFilter=populateCommonFilter(request,"AuditReportGridData",true);
     commonFilter.setWostatus("1");
	 commonFilter.setAbnViewType(tableName);
	 
	 try{
		 
	  if(UIUtils.isValidKeyId(tableName)){
		  
	      CommonMessage.debugMsg("The TableName Is"+tableName);
		  commonFilter.setAbnViewType(tableName);
		  List<String[]> auditreportdata=masterModuleGroupService.auditreportdata(commonFilter);
          CommonMessage.debugMsg("AuditReportData"+auditreportdata);
		  JqGridTableModel jqGridTableModel=new JqGridTableModel();
		  GridColModel gridColModel=new GridColModel();
		  jqGridTableModel.setSortable(true);
		  jqGridTableModel.setTableButton(true);
		  jqGridTableModel.setEnableFilter(true);
		  jqGridTableModel.setRowNumbers(true);
		  gridColModel.setHeaderNum(1);
		  gridColModel.setFormattorFromCol("0");
		  gridColModel.setFormattorToCol("0");
          
		  String[] colHeader=auditreportdata.get(1);
		  CommonMessage.debugMsg("The ColHeader"+auditreportdata.size());
		  String[] colHeaderHead=auditreportdata.get(0);
		  CommonMessage.debugMsg("The ColHeaderhead"+colHeaderHead);
		  List<String[]> headers=new ArrayList<String[]>();
		  headers.add(colHeader);
		 
		 JSONObject jsonobject=UIUtils.getTableModel(headers, colHeaderHead, jqGridTableModel, gridColModel);
		 CommonMessage.debugMsg("The JsonObject"+jsonobject);
		 CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
		 jsonobject.set("tableWidth","106%%");
		 jsonobject.set("tableHeight", "82%%");
		 httpSession.removeAttribute("AuditReportCol");
		 httpSession.setAttribute("AuditReportCol", jsonobject);
		 out.println(jsonobject); 
 
	  }
		 
	 }
	catch(Exception e){
		e.printStackTrace();
	}
	 
 }		
		
		
		private void savedata(HttpServletRequest request,HttpServletResponse response)throws Exception{
			HttpSession httpsession=request.getSession();
		    ServletOutputStream out=response.getOutputStream();
		    AdmTlUsermst user=UIUtils.getLoginUser(request);
		    CommonMessage.debugMsg("The Login User"+user);
		    CommonMessage.debugMsg("The Login User"+user);
		    String saveMsg="";
		    try{
		    	if(httpsession!=null && user!=null){
		    		String auditgriddata=request.getParameter("paramJsonArr");
                   // CommonMessage.debugMsg("The AuditGridData"+auditgriddata);
		    		 CommonMessage.debugMsg("The auditgriddata:::"+auditgriddata);
		    		AuditmasterModel empmodel=new AuditmasterModel();
		    		JSONArray auditenable=null;
		    		    if(UIUtils.isValidKeyId(auditgriddata)){
		    			auditenable=JSONArray.fromString(auditgriddata);
		    			//CommonMessage.debugMsg("The JsonArray Data"+auditenable);
		       		     CommonMessage.debugMsg("The auditenable:::"+auditenable);
		    			List<AuditmasterModel> auditEnableList =(List<AuditmasterModel>)UIUtils.convertJSONArrToList(empmodel,auditenable) ;	
		       		  auditEnableList=masterModuleGroupService.save(auditEnableList);
		       		  saveMsg="Data Saved Successfully";
		       		  //CommonMessage.debugMsg("The Audit List Data"+auditEnableList);
		       		CommonMessage.debugMsg("The auditgriddata:::"+auditEnableList);
                    		    		    }
		    		JSONObject SuccessData=new JSONObject();
			 	    SuccessData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
			    	SuccessData.put("msg",saveMsg);
			    	SuccessData.put("formClear",true);
			    	JSONObject returnData=new JSONObject();
			    	returnData.put("successData", SuccessData);		
			    	out.print(returnData.toString());
			    	out.close();
		    	}
		    }
		    catch(ValidationException e){	   
		 	   JSONObject errMessage=UIUtils.validationExceptions(e.toString(),"Auditmaster");
		 	   out.print(errMessage.toString());
		    }
		    catch(Exception e){
		    	CommonMessage.debugMsg("Error Msggg===="+e.toString());
		 	   JSONObject errMessage=UIUtils.validationExceptions(e.toString(),"Auditmaster");
		 	   out.print(errMessage.toString());
		    }
		}		
		private void getExcelData(HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			HttpSession	httpSession = request.getSession(false);
			JSONObject tblJSONObj =  (JSONObject) httpSession.getAttribute("ColModel");
			CommonMessage.debugMsg(tblJSONObj);
			
			//String rows = (String) httpSession.getAttribute("rows");
     	  //  String page = (String) httpSession.getAttribute("page");   
     	    
     	    //String count = (String) httpSession.getAttribute("CountData");
     	    String format = ExcelUtils.getFormat(request);
     	  
     	   
  		    String menuCaption =  (String)httpSession.getAttribute("CaptionMenu");
  		    String menuName = (String) httpSession.getAttribute("menuNameGenMstSvt");
  		    
  		    menuName = menuName.replace("?", "");
  		    
  		    
  		    GridParams gridParams =(GridParams) httpSession.getAttribute(menuName+"gridParams");
			
			tblJSONObj.put("title", menuCaption);
  		    String active = (String)httpSession.getAttribute(commonFilterIden+menuName);
		    
		    CommonMessage.debugMsg(" gridParams " + gridParams);
		  
			Workbook wb = masterModuleGroupService.generalMstFormExportExcel(menuCaption,menuName,active,tblJSONObj,format,gridParams);
			
			ExcelUtils.writeToResponse(response, wb, menuCaption, format);
		}
		private JSONObject getTableModel(List<String[]> headers,String chkBoxHide)
		{
			JqGridTableModel jqGridTableModel = new  JqGridTableModel();
			String [] colHeader = headers.get(0);	
			if(colHeader.length<=0)
				return null;
			String [] colNames = new String[colHeader.length-1];
			//jqGridTableModel.getRowHeaders().add(colHeader);
			jqGridTableModel.setRowNumbers(true);
			jqGridTableModel.setTableButton(true);
			jqGridTableModel.setTableHeight(250);
			jqGridTableModel.setEnableFilter(true);
			CommonMessage.debugMsg("chkBoxHide...."+chkBoxHide);
			for(int i =1; i < colHeader.length; i++)
			{
				colNames[i-1] = colHeader[i];
				JqGridColModel jqGridColModel = new JqGridColModel();
				jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
				jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				int colWidth = getColWidth(colHeader[i]);			
				jqGridColModel.setWidth( colWidth);				
				jqGridColModel.setAlign("left");
				jqGridColModel.setEditable(false);
				CommonMessage.debugMsg("get table data...................."+i);
				if(i==0||i==2||i==3)
				{
					jqGridColModel.setHidden(true);
					if(i==0)
						jqGridColModel.setKey(true);
				}
				
				if(i==1)
				{
					if(chkBoxHide.equals("N"))
					{
						jqGridColModel.setFormatter("BtnFormatterTick");
						jqGridColModel.setAlign("center");
					}
					else if(chkBoxHide.equals("Y"))
						jqGridColModel.setHidden(true);
				}
				jqGridTableModel.getColModel().add(jqGridColModel);
			}
			jqGridTableModel.getRowHeaders().add(colNames);
			 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
			 tableModel.set("enableFilter", true);
			 return tableModel;
		}
		
		private int getColWidth(String colName)
		{
			int colWidth = 100;
			if(colName.endsWith("NAME"))
				colWidth = 300;
			else if(colName.endsWith("Y"))
				colWidth = 200;
			else if(colName.endsWith("TION"))
				colWidth = 150;
			else if(colName.endsWith("SLNO"))
				colWidth = 50;
			return colWidth;
		}
		
		private void loadMasterLink(HttpServletRequest request, HttpServletResponse response,String isMMC,String loadFormArg,String menuCaption,String menuName) throws Exception
		{
			   request.setAttribute("isMMC", isMMC);
			   request.setAttribute("loadFormArg", loadFormArg);
			   request.setAttribute("menuCaption", menuCaption);
			   request.setAttribute("menuName", menuName);			   
			   UIUtils.forwardRequest(request, response,"loadmst_grid.gnms");
		}
  
		  
		private CommonFilter populateCommonFilter(HttpServletRequest request,
					String string, boolean b) {
			       
		 		    	 HttpSession httpSession = request.getSession(false);

		 		CommonFilter commonFilter =(CommonFilter)httpSession.getAttribute(string);
		 		if( commonFilter != null && ! b ){
		 			FilterValues.setPaginationParams(request,commonFilter);
		 		}	
		 		else{
		 			commonFilter =  new CommonFilter();
		 			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); 
		 			commonFilter = 	FilterValues.getRoleViewFilters(request, commonFilter);
		 			httpSession.removeAttribute(string);
		 			httpSession.setAttribute(string, commonFilter);
		 		}
		 		return commonFilter;
			}
		
	/*public static JSONObject listToJsonObject(List<String[]> dataArrayList)
		{
			JSONObject tableDataObject = new JSONObject();
			JSONArray rowArr = new JSONArray(); 	      
			int rowId = 0;
	        for( String [] row : dataArrayList)
			{
	        	if( rowId >= 1 )
	        	{	
		    	    JSONObject rowObj =new JSONObject();
		    	    //rowObj.put("id",rowId+1);
		    	    JSONArray cell=new JSONArray();
		            for( int i = 0 ;i < row.length ; i++)
		            {	
		            	cell.put( ( row[i] != null ? row[i].replace("{", "").replace("}", "").replace("[", "").replace("]", "").replace("N", "NO") :" ") ); 
		            }	
		            rowObj.put("cell",cell);
		            
		            rowArr.put(rowObj);
	        	}
	        	rowId++;
	       }

	        tableDataObject.put("rows", rowArr);
	        
	        return tableDataObject;
		}*/
}
