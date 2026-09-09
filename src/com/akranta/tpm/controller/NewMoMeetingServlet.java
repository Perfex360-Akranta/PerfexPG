package com.akranta.tpm.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.NewMoMeetingService;
import com.akranta.tpm.service.impl.NewMoMeetingServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class NewMoMeetingServlet
 */
@WebServlet("/NewMoMeetingServlet")
public class NewMoMeetingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	NewMoMeetingService NewmomeetingService;
	String  filePath = null;
	private static String DOC_ROOT_PATH;
	private static String docRealPath;
	private static final String DESTINATION_DIR_PATH = "tmpFiles";
	private static  String APP_DOCMANAGER_PATH ;
	
	public void init(ServletConfig config) throws ServletException {
    	try{
        super.init(config);
        
        filePath = config.getServletContext().getRealPath("tmp") + "\\";
		new File(filePath).mkdirs();
		
        docRealPath = getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
        
        boolean s = new File(docRealPath).mkdirs();
        
        DOC_ROOT_PATH = getServletContext().getRealPath("DocumentManagerServlet") ;
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        
        String parentFolderName = DOC_ROOT_PATH.substring(DOC_ROOT_PATH.lastIndexOf("\\")+1);
       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        CommonMessage.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
        CommonMessage.debugMsg(" FileManagerBasePath " + basePath );
        
        boolean pathExist = new File( basePath).exists();
        CommonMessage.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
        if( basePath != null && pathExist ){
        	DOC_ROOT_PATH = basePath;
        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
        	
        }
        CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
        CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
    	}catch(Exception e ){
    		e.printStackTrace();
    		CommonMessage.debugMsg(" File Manager exception " + e.getMessage());
    	}
    }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMoMeetingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   try{
		   process(request,response);
	   }
	   catch(Exception e){
		   e.printStackTrace();
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		   try{
			   process(request,response);
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }   
	}
   private void process(HttpServletRequest request,HttpServletResponse response) throws Exception{
	   
	    String action = UIUtils.getActionPart(request);
		HttpSession httpSession = request.getSession(false);
		
		
		try {
			NewmomeetingService = (NewMoMeetingServiceImpl) UIUtils.getServiceObject(request, "NewMoMeetingServiceImpl");
			NewmomeetingService.NewmomeetingServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		String dispatchUrl = null;
		
		if (action.equals("NewMeetingMin_input.nmom"))
		{

			String mainForm = request.getParameter("mainForm");
			String momRefDocId = request.getParameter("momRefDocId");
			String momRefDocType = request.getParameter("momRefDocType");
			String type = request.getParameter("type");
			String mode = request.getParameter("mode");
			String stage = request.getParameter("stage");
			
			CommonMessage.debugMsg(" Inside ::  New :: type :: "+type);
			
			if ("true".equals(mainForm)) {
                 request.setAttribute("mainForm", "mainForm");
            }
			
			String DMT = request.getParameter("DMT");
			request.setAttribute("DMT", DMT);
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			request.setAttribute("type", type);
			request.setAttribute("mode", mode);
			request.setAttribute("stage", stage);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/NewMoMeetingMainGrid.jsp");
			rd.forward(request, response);			
	    }
		if (action.equals("NewMoMeetingForm_input.nmom"))
		{
			String momRefDocId = request.getParameter("momRefDocId");
			String momRefDocType = request.getParameter("momRefDocType");
			String loginFlid = CommonFunctions.getLoginFlid(request);
			String keyid = request.getParameter("keyId");
            String flid = request.getParameter("flid");
            String mstDate = request.getParameter("Date");
            String MEETHAPPEN = request.getParameter("MEETHAPPEN");
            String DMT = request.getParameter("DMT");
            String DMTDBLE = request.getParameter("DMTDBLE");
            String type = request.getParameter("type");
            String mode = request.getParameter("mode");
            String recall=request.getParameter("recall");
            String shift=request.getParameter("shift");
            String menumode=request.getParameter("menumode");
            httpSession.setAttribute("mstDate ",mstDate);
            String date=CommonFunctions.dateTimeNow();
            String CurrentDate=UIUtils.getActualDateForm(date);
            CommonMessage.debugMsg("The CurrentDate:::"+CurrentDate);
			if ((UIUtils.isValidKeyId(keyid))) {
				GenTlMommst genTlMommst = NewmomeetingService.select(keyid);
				genTlMommst.setMomsDate(genTlMommst.getMomsDate().substring(0, 15));
				GenTlMomattendance genTlMomattendance = new GenTlMomattendance();
              	request.setAttribute("mom", genTlMommst);
				request.setAttribute("mstkeyid", keyid);
				request.setAttribute("Flid", flid);
				request.setAttribute("moma", genTlMomattendance);
				request.setAttribute("mstDate", mstDate);
				request.setAttribute("MEETHAPPEN", MEETHAPPEN);
				request.setAttribute("menumode", menumode);
				String Date = UIUtils.removeDefaultDate(genTlMommst.getMomsDate(), " ");
				genTlMommst.setMomsDate(Date);
			}else if(UIUtils.isValidKeyId(recall)){				
				List<String[]> minOfMeetingRecallList  = NewmomeetingService.selectRecalling(shift,mstDate,flid,type,"");
				if(minOfMeetingRecallList.size()>0){
					request.setAttribute("Mno",minOfMeetingRecallList.get(0)[0]);
					request.setAttribute("Ismthpn",minOfMeetingRecallList.get(0)[1]);
					request.setAttribute("sfty",minOfMeetingRecallList.get(0)[2]);
					request.setAttribute("rmrk",minOfMeetingRecallList.get(0)[3]);
					request.setAttribute("title",minOfMeetingRecallList.get(0)[4]);
					request.setAttribute("mtype",minOfMeetingRecallList.get(0)[5]);
					request.setAttribute("agnda",minOfMeetingRecallList.get(0)[6]);
					request.setAttribute("pillarid",minOfMeetingRecallList.get(0)[7]);
					request.setAttribute("pillargrpid",minOfMeetingRecallList.get(0)[8]);
				}
			}
			request.setAttribute("DMT", DMT);
			request.setAttribute("DMTDBLE", DMTDBLE);
			request.setAttribute("momRefDocId", momRefDocId);
			request.setAttribute("momRefDocType", momRefDocType);
			request.setAttribute("type", type);
			request.setAttribute("mode", mode);
			request.setAttribute("shift", shift);
			request.setAttribute("recall",recall);
			request.setAttribute("CurrentDate",CurrentDate);
			request.setAttribute("loginFlid",loginFlid);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/NewMoMeeting.jsp");
			rd.forward(request, response);
		}
		else if (action.equals("NewMoMeetingRecalling_input.nmom"))
		{
		  try {
			    PrintWriter out = response.getWriter();
			    String shift = request.getParameter("shift");
			    String mstDate = request.getParameter("Date");
			    String flid = request.getParameter("flid");
			    String type = request.getParameter("type");
			    String pillarid = request.getParameter("pillarid");
			    List<String[]> minOfMeetingRecallList  = NewmomeetingService.selectRecalling(shift,mstDate,flid,type,pillarid);
				out.print( JSONArray.fromCollection(minOfMeetingRecallList));
   			} catch (Exception e) {

			}
		}
		 
		else if(action.equals("NewMoMeetingMom_getCol.nmom") ){
			NewMoMGridgetCol(request, response);	
		}
		else if(action.equals("NewMoMeetingMom_getData.nmom") ){   	
			try{
                 UIUtils.displayRequestParamsValue(request);				
		         String KeyId=request.getParameter("keyid");
		         String flid=request.getParameter("flid");
		         String momdate=request.getParameter("momdate");
		         String shift=request.getParameter("shift");
		         String type=request.getParameter("type");
		         String pillarid=request.getParameter("pillarid");
		         
                 CommonFilter commonFilter = populateCommonFilter(request,"NewNewMinOfMeetingCommonFilter",false);
                 
                 if(UIUtils.isValidKeyId(flid))
                     commonFilter.setFlid(flid);
                 
                 if(UIUtils.isValidKeyId(type))
                    commonFilter.setType(type);
                 
            
                 
			     if(UIUtils.isValidKeyId(KeyId)||!UIUtils.isValidKeyId(KeyId)) 
			     {
			    	
				     List<String[]> minOfMeetingList  = NewmomeetingService.getNewMomGrid(commonFilter,KeyId,momdate,shift,pillarid);
				     for(String[] arr:minOfMeetingList) 
				     {
				    	 CommonMessage.debugMsg(Arrays.toString(arr));
				     }
				     PrintWriter out = response.getWriter();
				 	 JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,0,0,commonFilter.getTotalRecordCnt()); 
				 	 out.println(equipmentQueryData); 
			     }
				 	 commonFilter.setViewClick('N');  			 	
				 	 httpSession.removeAttribute("NewNewMinOfMeetingCommonFilter");
				 	 httpSession.setAttribute("NewNewMinOfMeetingCommonFilter", commonFilter);
			}catch(Exception e){
				//CommonMessage.debugMsg(e.getMessage());
			}
		}

			else if( action.equals("functionalLoc.nmom")){
							FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
							String type= "";
							type  = request.getParameter("Type");
							functLocFieldNameBean.setFactory("cmbMomdFactoryid");
							functLocFieldNameBean.setSection("cmbMomdSectionid");
							functLocFieldNameBean.setCell("cmbMomdCellid");
							functLocFieldNameBean.setMachine("cmbMomdMachineid");
							functLocFieldNameBean.setFunctionalLocId("cmbMomdFlid");
							functLocFieldNameBean.setSbu("cmbMomdSbu");
							functLocFieldNameBean.setPbu("cmbMomdPbu");	
							
							functLocFieldNameBean.setLocnMandatory(true);
							functLocFieldNameBean.setFactMandatory(false);
							functLocFieldNameBean.setSectMandatory(false);
							functLocFieldNameBean.setCellMandatory(false);
							functLocFieldNameBean.setMachMandatory(false);

							if (UIUtils.isValidKeyId(type)) {
								
								if(  "JH".equals(type.replaceAll(" ","").toUpperCase()))
								{
									functLocFieldNameBean.setCellMandatory(true);
									functLocFieldNameBean.setMachDisable(true);//Disable Equipment
								}
								else if("DMT".equals(type.replaceAll(" ","").toUpperCase()))
								{
									functLocFieldNameBean.setSectMandatory(true); //DMT - Mandatory
									functLocFieldNameBean.setMachDisable(true);//Disable Equipment
									functLocFieldNameBean.setCellDisable(true);//Disable JH
								}
								else if("PILLAR".equals(type.replaceAll(" ","").toUpperCase()) ||
										"Pillar".equals(type)) {
									
									functLocFieldNameBean.setCompDisable(true);
									functLocFieldNameBean.setLconDisable(true);
									
									functLocFieldNameBean.setPbuDisable(true);//Disable PBU
									functLocFieldNameBean.setSectDisable(true);//Disable DMT
									functLocFieldNameBean.setMachDisable(true);//Disable Equipment
									functLocFieldNameBean.setCellDisable(true);//Disable JH
								}
								if ("PRODUCTION".equals(type.replaceAll(" ", "").toUpperCase()) ||
									    "OGM".equals(type.replaceAll(" ", "").toUpperCase()) ||
									    "UMC".equals(type.replaceAll(" ", "").toUpperCase())) {
									
									functLocFieldNameBean.setSbuDisable(true);//Disable PBU
									functLocFieldNameBean.setPbuDisable(true);//Disable PBU
									functLocFieldNameBean.setSectDisable(true);//Disable DMT
									functLocFieldNameBean.setMachDisable(true);//Disable Equipment
									functLocFieldNameBean.setCellDisable(true);//Disable JH
									    
									}
							}						
			                FormModes formModes = FormModes.create;
			                UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
					 }
			else if( action.equals("NewMoMeetingMom_sendMail.nmom"))
			{	
		 		sendMomMail( request, response);
		 		
			}
			else if( action.equals("NewMoMeetingMom_isMail.nmom"))
			{	
		 		
		 		PrintWriter out=response.getWriter();
		 		String mailid=request.getParameter("momKeyId");
		 		JSONObject jsonObject=new JSONObject();
		 	String ismailid=NewmomeetingService.getmailidTrigger(mailid);
		 	String ismail="";
		 	String val="N";
		 	if(ismailid.length()!=0||ismailid!=null)
		 	  {
		 		jsonObject.put("ismail",ismailid);
		 	  }
		 	
		 	out.println(jsonObject);	
			}
			else if(action.equals("Newvisitor_input.nmom"))
			{
				String from = request.getParameter("from");
		    	String MasterKeyid =request.getParameter("keyid");
		    	request.setAttribute("from", from);
		    	request.setAttribute("MasterKeyid", MasterKeyid);
		    	UIUtils.forwardRequest(request, response, "/pages/NewAddVistors.jsp");
			}
				else if(action.equals("Newvisitor_getCol.nmom"))
			{
				try{
					PrintWriter out = response.getWriter();
			    	String from = request.getParameter("from");
			    	String frmProgrm = request.getParameter("frmProgrm");
					 
					String colModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting","VistorsAdd");
					CommonMessage.debugMsg("colmodel"+colModel);
					
					JSONObject jsonObject =  JSONObject.fromString(colModel);
					CommonMessage.debugMsg("jsonObject"+jsonObject);
			    	if("ENT".equals(from)){
			    		jsonObject.getJSONArray("rowHeaders").getJSONArray(0).put(1, "External Resource");
			    		jsonObject.getJSONArray("colModel").getJSONObject(2).set("width","100");
			    	}
			    	if("true".equals(frmProgrm)){
			    		jsonObject.set("tableHeight", "30%%");
			    		jsonObject.set("tableWidth", "30%%");
			    	}
			    	out.println(jsonObject);
					}catch(Exception e){
						//CommonMessage.debugMsg(e.getMessage());
					}
			}
			else if(action.equals("Newvisitor_getData.nmom"))
			{
				try{
	                UIUtils.displayRequestParamsValue(request);				
			         String MasterKeyid =request.getParameter("keyid");
			         String shift =request.getParameter("shift");
			         String date =request.getParameter("date");
			         String flid =request.getParameter("flid");
			         String type =request.getParameter("type");
			         String pillarid =request.getParameter("pillarid");
			         String recall =request.getParameter("recall");
			         
	                CommonFilter commonFilter = populateCommonFilter(request,"NewMinOfMeetingCommonFilter",false);
					     List<String[]> minOfMeetingList  = NewmomeetingService.getAttVistor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
					     CommonMessage.debugMsg("minOfMeetingList "+minOfMeetingList.size()); 
					     PrintWriter out = response.getWriter();
					 	 JSONObject equipmentQueryData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,0,0,commonFilter.getTotalRecordCnt()); 
					 	 out.println(equipmentQueryData); 
					 	 commonFilter.setViewClick('N');  			 	
					 	 httpSession.removeAttribute("NewMinOfMeetingCommonFilter");
					 	 httpSession.setAttribute("NewMinOfMeetingCommonFilter", commonFilter);
				}catch(Exception e){
				}
				
			}
		
		
		else if(action.equals("NewMoMeetingAtt_input.nmom")){     
		    
    	}
		else if(action.equals("NewMoMeetingAtt_getCol.nmom")){     
			 NewAttendanceGridgetCol(request,response);   
			 
     	}
		 else if(action.equals("NewMeetingType.nmom"))
			{
				   PrintWriter out = response.getWriter();
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMeetingType", "MeetingType"));	   
			}
		 else if(action.equals("NewMeetingAttType.nmom"))
			{
			       String type=request.getParameter("type");
			       String pillarcmb="";
				   PrintWriter out = response.getWriter();
				   if(UIUtils.isValidKeyId(type))
					   pillarcmb="MeetingMonthAttwise";
				   else
					   pillarcmb="MeetingAttType";
				   
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMeetingType", pillarcmb));
			}
		 else if(action.equals("NewMeetingAttendance.nmom"))
			{
				   PrintWriter out = response.getWriter();
				   out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMeetingType", "MeetingAttendance"));	   
			}
		 else if(action.equals("SelectVisitor_select.nmom"))
			{
				String Visitorkey =request.getParameter("VisitorKey");
				if ((Visitorkey != null)) {
					GenTlVisitors newGenTlVisitors = NewmomeetingService.selectVisitor(Visitorkey);
					request.setAttribute("visitor", newGenTlVisitors);
				}
				
			}
		 else if( action.equals("MoMeetingFlid.nmom"))
			{	
		 	
		 		String originalId = request.getParameter("originalId");
			
		    	String flid  = NewmomeetingService.RoleBasedFlid(originalId);
		    	 JSONObject json=new JSONObject();
				PrintWriter out=response.getWriter();
					json.put("flid", flid);
					out.println(json);
			}
		 	else if(action.equals("pillargroup.nmom")){
				try 
				{
					String pillarid = request.getParameter("pillarid");
					String flid = request.getParameter("flid");
					String pillargrp = request.getParameter("pillargrp");
					String condSql="";
					ComboFilter comboFilter = new ComboFilter();
					String locationId = request.getParameter("locationId");
					comboFilter=UIUtils.fillComboFilter(request);
						
//					if(pillarid != null)
//						//condSql=" AND  MGRM_PILLARID = '"+pillarid+"' AND MGRM_FLID='"+flid+"' ";
//						condSql=" AND  MGRM_PILLARID = '"+pillarid+"' ";
					if (pillarid != null) {
					    condSql = " AND MGRM_PILLARID = '" + pillarid + "' " +
					              " AND POSITION((" +
					              "SELECT FNLN_KEYID FROM gen_tl_functionallocn " +
					              "WHERE FNLN_ORIGINALID = '" + locationId + "'" +
					              ") IN (PARENTFLIDS || FLID)) > 0 ";
					}
					List<ComboBox>  TagClass = NewmomeetingService.getPillarGroupcombo(condSql,comboFilter);
				    UIUtils.writeComboBox(response, TagClass ,comboFilter);
				    
				} 
				catch (Exception e) 
				{
					
					e.printStackTrace();
				}
			}
		 else if(action.equals("NewMoMeetingModification_getCol.nmom")){
	            PrintWriter out = response.getWriter();
				String momRefDocId = request.getParameter("momRefDocId");
				String momRefDocType = request.getParameter("momRefDocType");
		        String flid=request.getParameter("flid");
		        String type=request.getParameter("type");				
				CommonFilter commonFilter = populateCommonFilter(request,"NewMinOfMeetingCommonFilter", true);
				commonFilter.setWostatus("1");
				commonFilter.setRefdocid(momRefDocId);
				commonFilter.setType(momRefDocType);

				String search = request.getParameter("_search");
				try{
					
				    if(UIUtils.isValidKeyId(type))
						commonFilter.setTaskid(type);
				        commonFilter.setIsGetCol("Y");
				    
				List<String[]> moReqList = NewmomeetingService.getNewMomeetingList(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				gridColModel.setFormattorFromCol("0");
				gridColModel.setFormattorToCol("0");
				
				String [] colHeader = moReqList.get(1);
				String [] colHeaderHead = moReqList.get(0);
				 
				List<String[]> headers = new ArrayList<String[]>();	
				headers.add(colHeader);
				JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				jsonObject.set("tableWidth", "106%%");
		    	jsonObject.set("tableHeight", "82%%");
		    	httpSession.removeAttribute("NewMomeetingColModel");
				httpSession.setAttribute("NewMomeetingColModel", jsonObject);
				out.println(jsonObject);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		 else if(action.equals("NewMoMeetingModification_getData.nmom"))
			{
				try
				{   
					UIUtils.displayRequestParamsValue(request);
					String momRefDocId = request.getParameter("momRefDocId");
					String momRefDocType = request.getParameter("momRefDocType");
					String flid=request.getParameter("flid");
					String type=request.getParameter("Type");
				    CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				    
				    if(UIUtils.isValidKeyId(type))
						commonFilter.setTaskid(type);
				    commonFilter.setRefdocid(momRefDocId);
				    commonFilter.setType(momRefDocType);
				    commonFilter.setFlid(flid);
				    commonFilter.setIsGetCol("N");
					List<String[]> minOfMeetingList  = NewmomeetingService.getNewMomeetingList(commonFilter);
	  			 	JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
					PrintWriter out = response.getWriter();
	  			 	out.println(MoReqData);  			 	
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("NewMinOfMeetingCommonFilter");
	  			 	httpSession.setAttribute("NewMinOfMeetingCommonFilter", commonFilter);

			    }catch(Exception e)
				{
					//CommonMessage.debugMsg(e.getMessage());
				}
			}
			else if( action.equals("NewMoMeetingModification_getExcel.nmom"))
			{

					String momRefDocId = request.getParameter("momRefDocId");
					String momRefDocType = request.getParameter("momRefDocType");			    
					CommonFilter	commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
					commonFilter.setRefdocid(momRefDocId);
				    commonFilter.setType(momRefDocType);
				    String type=request.getParameter("Type");
				    if(UIUtils.isValidKeyId(type))
						commonFilter.setTaskid(type);				    
					String tmpFromRow = commonFilter.getFromRow();
					commonFilter.setFromRow(null);
					httpSession = request.getSession(false);
					JSONObject colmodel = (JSONObject) httpSession.getAttribute("NewMomeetingColModel");
					colmodel.put("title",type+" Minutes Of Meeting Report");
		            String format = ExcelUtils.getFormat(request);
					Workbook wb = NewmomeetingService.getMomeetingExportExcel(commonFilter,colmodel,format);
					commonFilter.setFromRow(tmpFromRow);
					ExcelUtils.writeToResponse(response,wb,"MinutesOfMeetingReport", format);
			}
		
		 else if (action.equals("NewMoMeetingModification_input.nmom")){
				String type = request.getParameter("type");
				request.setAttribute("type", type);
				CommonMessage.debugMsg("Type on MOM "+type);
				CommonMessage.debugMsg("Entered into modification URL");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/NewMoMeetingMainGrid.jsp");
				rd.forward(request, response);
			}
		else if(action.equals("Rolecombo.nmom"))
			{				
			 try{
					String flid=request.getParameter("flid");
					ComboFilter roleComboComboFilter=UIUtils.fillComboFilter(request);				
					List<ComboBox> probfilcomboList = NewmomeetingService.getRoleComboComboList(roleComboComboFilter,flid,"");
					UIUtils.writeComboBox(response, probfilcomboList ,roleComboComboFilter);			
				}
				catch (Exception e) {				
					e.printStackTrace();
				}	
			}
		else if(action.equals("rolebasedemployee.nmom"))
		{				
		 try{
				String flid=request.getParameter("flid");
				CommonMessage.debugMsg(" flid :: "+flid);
				String roleId=request.getParameter("roleId");
				String tradeid=request.getParameter("tradeid");
				String Others=request.getParameter("Others");
				String locnid  = request.getParameter("locnId");
				
				StringBuffer cond = new StringBuffer();
				
				ComboFilter empComboFilter = UIUtils.fillComboFilter(request);

				cond.append(" AND EMPM_KEYID IN ( SELECT  DISTINCT EMPM_KEYID from  GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, gen_mv_flidhierarchy WHERE 1=1 ");
		
				cond.append("  and FRT_EMPM_KEYID = empm_keyid and flid = frt_fnln_keyid ");
				if(UIUtils.isValidKeyId(Others))
				    cond.append("  and INSTR(PARENTFLIDS||'/'||FLID, (SELECT FNLN_KEYID FROM  gen_tl_functionallocn   WHERE FNLN_ORIGINALID='"+locnid+"'))>0 ");
				
				if(UIUtils.isValidKeyId(flid) )
					cond.append("AND FRT_FNLN_KEYID IN( SELECT flid FROM gen_mv_flidhierarchy WHERE INSTR (parentflids || '-' || flid, '"+flid+"') >0)");
		
				if(UIUtils.isValidKeyId(roleId)){
					 cond.append(" AND FRT_ROLE_KEYID='"+roleId+"' ");	
				}
				
				cond.append(" ) " ); 
				
				empComboFilter.setCondSql( cond.toString());
				
				String loginLocnId = CommonFunctions.getLoginLocaton(request);
				if(!UIUtils.isValidKeyId(locnid))
					locnid = loginLocnId;
				CommonMessage.debugMsg("locnid===="+locnid);
				 if (UIUtils.isValidKeyId(locnid))
					 empComboFilter.setCondSql( empComboFilter.getCondSql() + "  and EMPM_LOCATION = '"+locnid+"' AND EMPM_ACTIVE='Y' ");   
				
				List<ComboBox> probfilcomboList = NewmomeetingService.getrolebasedemployee(empComboFilter,flid,"");
				UIUtils.writeComboBox(response, probfilcomboList ,empComboFilter);	
				
			
		   }
			catch (Exception e) {				
				e.printStackTrace();
			}	
		}
		 
		
		else if(action.equals("NewMoMeetingAtt_getData.nmom")){

			try
			{  
				UIUtils.displayRequestParamsValue(request);	
				String KeyId=request.getParameter("mkeyid");
				CommonFilter commonFilter = populateCommonFilter(request,"NewMinOfMeetingCommonFilter",false);			
				String flid=request.getParameter("flid");
				String Momdate=request.getParameter("momdate");
				
				CommonMessage.debugMsg("DEBUG: Momdate value = '" + Momdate + "'");
				 CommonMessage.debugMsg("DEBUG: Momdate is null? " + (Momdate == null));
				 CommonMessage.debugMsg("DEBUG: Momdate is empty? " + (Momdate != null && Momdate.trim().isEmpty()));
				 if(Momdate == null || Momdate.trim().isEmpty()){
					 Momdate = CommonFunctions.getDate();
					}
				
				String location=request.getParameter("locationId");
				String cellId=request.getParameter("cellId");
				String pillarid=request.getParameter("pillarid");
				String pillargroup=request.getParameter("pillargroup");
				String pillargrp=request.getParameter("pillargrp");
				String type=request.getParameter("meetingType");
				String shift=request.getParameter("shift");
				String menutype=request.getParameter("pillarmode");
				String recall=request.getParameter("recall");
								
				httpSession.setAttribute("cellId", cellId);
				httpSession.setAttribute("location", location);
				
				ComboFilter locationId=new  ComboFilter();
				String emply = request.getParameter("emp");
				String dept = request.getParameter("dept");
				String roleid=request.getParameter("roleid");
				String PILLAR=request.getParameter("PILLAR");
				String range=request.getParameter("pillarmode");				
				 if(UIUtils.isValidKeyId(PILLAR)){
					 commonFilter.setKey(roleid);
					 commonFilter.setActionKeyId(PILLAR);
					 commonFilter.setCellId(cellId);
					 commonFilter.setLocation(locationId);
					 commonFilter.setKK(pillargroup);
					 commonFilter.setRange(range);
				 }				 
				if(UIUtils.isValidKeyId(emply)){
				ComboFilter newComboFilter=new ComboFilter();
				newComboFilter.setId(emply);
			    commonFilter.setEmployee(newComboFilter);
				}
			    if(UIUtils.isValidKeyId(dept)) {
        		ComboFilter newComboFilter1=new ComboFilter();
				newComboFilter1.setId(dept);
			    commonFilter.setDept(newComboFilter1);
			    }
			    
				if(UIUtils.isValidKeyId(KeyId)||!UIUtils.isValidKeyId(KeyId))
			    {
					List<String[]> minOfMeetingList  = NewmomeetingService.getNewMomeetingAtt( commonFilter,KeyId,location,flid,Momdate,shift,recall);
					PrintWriter out = response.getWriter();
					JSONObject MoReqData = UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt()); 
	  			 	out.println(MoReqData);
	  			 	commonFilter.setViewClick('N');  			 	
	  			 	httpSession.removeAttribute("NewMinOfMeetingCommonFilter");
	  			 	httpSession.setAttribute("NewMinOfMeetingCommonFilter", commonFilter);
			     }
		    }catch(Exception e)
			{
			}
			
		}
		else if (action.equals("NewMoMeetingForm_save.nmom")){
			try {
				MOMeetingBean momeetingBean = new MOMeetingBean();
				saveNewMOMeeting(request, response, momeetingBean);
   			} catch (Exception e) {

			}
			
		}
		else if (action.equals("NewMoMeeting_remove.nmom")) 
        {
			DeleteNewMomRow(request, response);
		}
		else if (action.equals("NewMoMVisitorATT_save.nmom")) 
		{
			try {
				saveVisitors(request, response);
			    } catch (Exception e) { 
			    }
		}
		 else if (action.equals("NewAttendancesVisitor_remove.nmom"))
	       {
	    	   DeleteATTVisitorRow(request, response);
		   }
		 else if( action.equals("NewMoMeetingMom_view.nmom")){	
				try{
				String momKeyId = request.getParameter("momKeyId");
				String flid=request.getParameter("flid");
				String glbType=request.getParameter("glbType");
				String format = ExcelUtils.getFormat(request);
				String path = UIUtils.getExcelTemplatePath(request);
				Workbook wb = NewmomeetingService.momExcelView(glbType,momKeyId,flid,path);
				format = ".xlsx";
				ExcelUtils.writeToResponse(response, wb, "MinutesofMeeting_"+momKeyId, format);
				}
				catch(Exception e)
				{
					PrintWriter out = response.getWriter();
					JSONObject err = new JSONObject();				
					err.put("message" ,"Data Not Found" );
					out.print(err.toString());
				}
			}
}
	private void NewMoMGridgetCol(HttpServletRequest request,HttpServletResponse response) 
	{
		try{
			PrintWriter out = response.getWriter();
			String type = request.getParameter("type");
			String mode = request.getParameter("mode");
					
			if (type.equals("Production") || type.equals("Others"))
				  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting","MinOfMeetingDtlProducttion"));
			else if ((type.equals("JH") || type.equals("Dmt")|| type.equals("Pillar")||type.equals("FIP")||type.equals("UMC")||type.equals("OGM")||type.equals("CEC")||type.equals("DEC"))&& mode== null){
           	  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting","MinOfMeetingDtl"));
			}else if(mode.equals("view") && mode !=null)
				  out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting","MinOfMeetingDtlView"));
			
			}catch(Exception e){
				//CommonMessage.debugMsg(e.getMessage());
			}
	}
	private void NewAttendanceGridgetCol(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		 
            HttpSession httpSession = request.getSession(false);
			PrintWriter out = response.getWriter();
			try{
				CommonMessage.debugMsg("AttendanceGridgetCol");
				CommonFilter commonFilter = populateCommonFilter(request,"NewMinOfMeetingCommonFilter",true);
				String emply = request.getParameter("emp");
				String dept = request.getParameter("dept");
				
			
			 if(UIUtils.isValidKeyId(emply))
			 {
				ComboFilter newComboFilter= new ComboFilter();
				newComboFilter.setId(emply);
				commonFilter.setEmployee(newComboFilter);
			 }
			 if(UIUtils.isValidKeyId(dept))
			 {			 
			    ComboFilter newComboFilter1= new ComboFilter();
				newComboFilter1.setId(dept);
				commonFilter.setDept(newComboFilter1);		
			 }
			 String flid=request.getParameter("flid");
			 String KeyId=request.getParameter("mkeyid");
			 String roleid=request.getParameter("roleid");
			 String PILLAR=request.getParameter("PILLAR");
			 String location=request.getParameter("locationId");
			 String cellId=request.getParameter("cellId");
			 String pillarid=request.getParameter("pillarid");
			 String pillargroup=request.getParameter("pillargroup");
			 String pillargrp=request.getParameter("pillargrp"); 
			 String type=request.getParameter("meetingType");
			 String shift=request.getParameter("shift");
			 String recall=request.getParameter("recall");
			ComboFilter locationId=new  ComboFilter();
			 if(UIUtils.isValidKeyId(PILLAR)){
				 commonFilter.setKey(roleid);
				 commonFilter.setActionKeyId(PILLAR);
				 commonFilter.setLocation(locationId);
				 commonFilter.setCellId(cellId);
				 commonFilter.setKK(pillargroup);
			 }
			 httpSession.setAttribute("cellId", cellId);
			httpSession.setAttribute("location", location);

			 String Momdate=request.getParameter("momdate");
			 
			 CommonMessage.debugMsg("DEBUG: Momdate value = '" + Momdate + "'");
			 CommonMessage.debugMsg("DEBUG: Momdate is null? " + (Momdate == null));
			 CommonMessage.debugMsg("DEBUG: Momdate is empty? " + (Momdate != null && Momdate.trim().isEmpty()));
			 
			 CommonMessage.debugMsg("DEBUG: shift value = '" + shift + "'");
			 if(Momdate == null || Momdate.trim().isEmpty()){
				 Momdate = CommonFunctions.getDate();
				}
			
			 
        	 List<String[]> MoReqList  = NewmomeetingService.getNewMomeetingAtt(commonFilter,KeyId,location,flid,Momdate,shift,recall);  
             JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
			 GridColModel gridColModel = new GridColModel();			
			
			 jqGridTableModel.setSortable(false);
			 jqGridTableModel.setTableButton(false);
			 jqGridTableModel.setEnableFilter(true);
			 jqGridTableModel.setRowNumbers(true);

			 gridColModel.setHeaderNum(1);//9	
			 CommonMessage.debugMsg("gridColModel>>>>"+gridColModel);
			 gridColModel.setFormatter("chkMomAttformatter");
			 gridColModel.setFormattorFromCol("0");
			 gridColModel.setFormattorToCol("0");
			 String[] formatterval  = {"cmbMomaAttandance#8"};
			 jqGridTableModel.setFormatterIndex(formatterval);
			 
			 String [] colHeader = MoReqList.get(1);
			 
			 String [] colHeaderHead = MoReqList.get(0);
			 
			 List<String[]> headers = new ArrayList<String[]>();	
			 headers.add(colHeader);
	
			 JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
			 jsonObject.set("tableWidth", "108%%");
			 jsonObject.set("tableHeight", "58%%");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("index","dataOrder");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("name","dataOrder");
	     	 jsonObject.getJSONArray("colModel").getJSONObject(colHeader.length-1).set("hidden",true);
	   	     httpSession.removeAttribute("AttendanceColModel");
			 httpSession.setAttribute("AttendanceColModel",jsonObject);	
			 CommonMessage.debugMsg("jsonObject " + jsonObject);
			 out.println(jsonObject);
			 
			}catch(Exception e){
				e.printStackTrace();
			}		
	}
	
	private void saveVisitors(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	  try
          {
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlVisitors existGenTlVisitors = (GenTlVisitors)httpSession.getAttribute("newSession");
	    		GenTlVisitors newGenTlVisitors = new GenTlVisitors();
	    		newGenTlVisitors.setVisiCreatedby(user.getUsrm_ccno());
	    		newGenTlVisitors =(GenTlVisitors)UIUtils.setBeanProperties((Object)newGenTlVisitors,request);
	    		
	    		boolean insert = true;
				
				if( newGenTlVisitors.getVisiKeyid() == null )
				 {							
					existGenTlVisitors =	NewmomeetingService.createVisitor(newGenTlVisitors,existGenTlVisitors);
				 }	
				else
				  {
					existGenTlVisitors = NewmomeetingService.updateVisitor(newGenTlVisitors,existGenTlVisitors);
					insert = false;
				  }					
				httpSession.setAttribute(existGenTlVisitors.getVisiKeyid(),existGenTlVisitors);
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;					 
					 if( insert)
					   {
						msgPropertyIdnt = "success-save";
					   }
					 else
						msgPropertyIdnt = "success-update";
				 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
					successData.put("keyId", existGenTlVisitors.getVisiKeyid());
					successData.put("MstKeyId", existGenTlVisitors.getVisiMomsKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);				
					returnData.put("formClear",true);
					out.print(returnData.toString());
	    	}
          }
    	  catch (ValidationExceptions e) 
          {
				CommonMessage.debugMsg("ValidationExceptions");
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "AddVisitors");
				out.print(errMessage.toString());
				    	
          }
										
	  catch(BusinessApplicationExceptions e)
	          {
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "AddVisitors");
				out.print(errMessage.toString());
		      }
	  catch(Exception e)
	          {   
		         JSONObject err = new JSONObject();
		         err.put("tpmException", "Data Not Saved");
		         out.print(err.toString());
	          }	
	}

	private void DeleteATTVisitorRow(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		PrintWriter out = response.getWriter();
		String keyid = request.getParameter("keyid");
		try{
			if(UIUtils.isValidKeyId(keyid)){
				NewmomeetingService.DeleteATTVisitorRow(keyid);
				String msgPropertyIdnt = "success-delete";
				JSONObject err = new JSONObject();
				String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
				err.put("successData",mesg);
				CommonMessage.debugMsg(err.toString());
				out.print(err.toString());
			}
			    CommonMessage.debugMsg("Delete End");
		}
       catch (Exception e) 
       {
			CommonMessage.debugMsg("Exception: " + e);
			JSONObject err = new JSONObject();
			String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "err-delete");
			err.put("successData", mesg);
			CommonMessage.debugMsg(err.toString());
			out.print(err.toString());
		}
		
	}
	@SuppressWarnings("null")
	private void sendMomMail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fileName  = "";
		String val="";
		File f = null;
		String momKeyId = request.getParameter("momKeyId");
		try{			
			String flid=request.getParameter("flid");
			String glbType=request.getParameter("glbType");
			String mType=request.getParameter("mtype");
			String momDate=request.getParameter("date");
			String format = ExcelUtils.getFormat(request);
						
			List<String[]> empMailIds = NewmomeetingService.getMomAttendanceEmpMailIds(momKeyId, flid) ;
			String mailIds = buildToMailIds(empMailIds);			
			if( mailIds.isEmpty())
			{
				JSONObject succssMsg= new JSONObject();
				succssMsg.put("msg", "No Valid MailId Found ");
		        response.getWriter().print(succssMsg.toString());
				
				return ;
			}
			
			format = ".xlsx";
			String path = UIUtils.getExcelTemplatePath(request);
			fileName =this.filePath + "MinutesofMeeting_"+momKeyId + "_" + UIUtils.now() +format;			
			Workbook wb = NewmomeetingService.newmomExcelView(glbType,momKeyId,flid,path);			
			FileOutputStream out = new FileOutputStream( fileName );
		    wb.write(out); 
		    out.close();
		    out = null;
		    wb = null;

		    CommonMessage.debugMsg(" File Write Completed " );
		    f = new File( fileName);
			String content = UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting", "MOMMeetingMailContent");
			String disclaimerNote = UIUtils.getPropertyValue("com.akranta.tpm.resources.NewMinOfMeeting", "MOMMeetingMailDisclaimer");
			StringBuilder subject = new StringBuilder(mType);
			subject.append(" Meeting - ");
			subject.append(momDate);
		
			StringBuilder totalContent = new StringBuilder();
			totalContent.append(content);
			totalContent.append("\r\n");
			totalContent.append("\r\n");
			totalContent.append(disclaimerNote);
			CommonMessage.debugMsg(" Before sendLotusNotesMail" );
			List<String> attachmentFiles = new ArrayList<String>();
			attachmentFiles.add(fileName);
			attachmentFiles = attachFileManagerFiles(attachmentFiles,momKeyId);
			UIUtils.sendLotusNotesMailAttachments(request,response,mailIds,null,subject.toString(),totalContent.toString(),attachmentFiles);
			
			CommonMessage.debugMsg(" After sendLotusNotesMail" );
			UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Sent SuccessFully ");
			val="false";
			String mailid=NewmomeetingService.updateIsmailid(momKeyId,val);
	        response.getWriter().print(succssMsg.toString());
	         
		}catch(Exception e){
			CommonMessage.debugMsg(" sendMomMail Exception" );
			if( fileName != null && f != null)
				UIUtils.delete( f );
			
			JSONObject succssMsg= new JSONObject();
			succssMsg.put("msg", "Mail Not Sent ! "+ e.getMessage());
			val="true";
			try {
				String mailid=NewmomeetingService.updateIsmailid(momKeyId,val);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        response.getWriter().print(succssMsg.toString());
		}
		
	}
private List<String> attachFileManagerFiles(List<String> attachmentFiles, String momKeyId) throws Exception {
		
		List<String[]> docMgrids = NewmomeetingService.getMomReleatedFileManager(momKeyId);
		CommonMessage.debugMsg(" size 1234 :: "+docMgrids.size());
		for (int i=0;i<docMgrids.size();i++) {
			String fileName = docMgrids.get(i)[0];
			String ext = fileName.substring(fileName.lastIndexOf('.'),fileName.length());
		   if(UIUtils.isValidKeyId(fileName))
		   {
			   String path = DOC_ROOT_PATH + docMgrids.get(i)[1];
			   path = path.replace("/", "\\");
			   attachmentFiles.add(path);
		   }
		}
		
		return attachmentFiles;
	}
	
	private String buildToMailIds(List<String[]> mailIds){
		StringBuilder mailIdsStr = new StringBuilder();
		for(String [] mailid : mailIds){
			if(UIUtils.isValidEmail(mailid[1]) ){
				mailIdsStr.append(mailid[1]);
				mailIdsStr.append(',');
			}	
		}
		if(  mailIdsStr.length() > 0 )
			mailIdsStr.deleteCharAt(mailIdsStr.lastIndexOf(","));
		CommonMessage.debugMsg("mailIdsStr.toString()  "+mailIdsStr.toString());
		return mailIdsStr.toString();
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
		return commonFilter;
	}
	
	@SuppressWarnings("unchecked")
	private void saveNewMOMeeting(HttpServletRequest request,HttpServletResponse response, MOMeetingBean momeetingBean) throws BusinessApplicationExceptions,Exception 
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);    	
    	try
          {
	    	if( httpSession != null && user != null)
	    	{		
	    		GenTlMommst existGenTlMommst=(GenTlMommst)httpSession.getAttribute("GenTlMOmmst");
	    		GenTlMomdtl existGenTlMomdtl=(GenTlMomdtl)httpSession.getAttribute("GenTlMOmdtl");
	    		GenTlActionplandtl existGenTlActionplandtl = (GenTlActionplandtl) httpSession.getAttribute("newGenTlActionplandtl");
	    		GenTlActionplanmst existGenTlActionplanmst = (GenTlActionplanmst) httpSession.getAttribute("newGenTlActionplanmst");
	    		GenTlMommst newGenTlMommst = new GenTlMommst();
	    		GenTlMomdtl newGenTlMomdtl = new GenTlMomdtl();
	    		GenTlMomattendance newMomattendance=new GenTlMomattendance();
	    		GenTlMomKpiLink newGenTlMomKpiLink =new GenTlMomKpiLink();
	    		GenTlActionplanmst newActionplanmst=new GenTlActionplanmst();
	    		GenTlActionplandtl newActionplandtl=new GenTlActionplandtl();
	    		newGenTlMommst.setMomsCreatedby(user.getUsrm_ccno());
	    		newGenTlMomdtl.setMomdCreatedby(user.getUsrm_ccno());
	    		newMomattendance.setMomaCreatedby(user.getUsrm_ccno());
	    		newGenTlMomKpiLink.setMokpCreatedby(user.getUsrm_ccno());
	    		newActionplanmst.setAplmCreatedby(user.getUsrm_ccno());
	    		String momDetail = request.getParameter("momDetails");
	    		String ActionPlanDesc=request.getParameter("ActionPlanDesc");
	    		String Discussiondetail=request.getParameter("Discussiondetail");
	    		String Responsiblity=request.getParameter("Responsiblity");	    	
	    		String ActionPlanId=request.getParameter("ActionPlanId");
	    		String ActionplanDetailId=request.getParameter("ActionplanDetailId");
	    		String targetDate=request.getParameter("targetDate");
	    		String ActionPlanStatus=request.getParameter("ActionPlanStatus");
	    		String MomKpiIndicator = request.getParameter("KpiIndicator");
	    		String Rowid = request.getParameter("rowid");
	    		String row=request.getParameter("row");
	    		String momactnpln = request.getParameter("momactnpln");
	    		String filemanger = request.getParameter("filemanger");
	    		String MomAtt = request.getParameter("MomAtt");
	    		String flid=request.getParameter("flid");
	    		String elementid=CommonFunctions.getLoginElementId(request);
	    		String RefDocType=request.getParameter("mom");
	    		String cellId=(String)httpSession.getAttribute("cellId");
	    		momeetingBean.setMomCellId(cellId);
	    		newActionplanmst.setAplmFlid(flid);
	    		newActionplanmst.setAplmRefdoctype(RefDocType);
	    		newActionplanmst.setAplmMaintask(Discussiondetail);
	    		newActionplanmst.setAplmElementid(elementid);
	    		newGenTlMommst =(GenTlMommst)UIUtils.setBeanProperties((Object)newGenTlMommst,request);	   	         
	    		newMomattendance.setMomaFlid(newGenTlMommst.getMomsFlid());
	    		newActionplandtl.setApldActionplan(ActionPlanDesc);
	    		newActionplandtl.setApldResponsibility(Responsiblity);
	    		newActionplandtl.setApldTargetdate(targetDate);
	    		newActionplandtl.setApldStatus(ActionPlanStatus);
	    		newActionplanmst=(GenTlActionplanmst)UIUtils.setBeanProperties((Object)newActionplanmst, request);
	    		newActionplandtl=(GenTlActionplandtl)UIUtils.setBeanProperties((Object)newActionplandtl,request);
		/** save JsonARR conversion For Detail **/
	    		List<GenTlMomdtl> MomGridList = new ArrayList<GenTlMomdtl>();
	    		List<GenTlMomKpiLink> MomKpiList = new ArrayList<GenTlMomKpiLink>();
	    		List<GenTlActionplanmst> MomActionplanmst=new ArrayList<GenTlActionplanmst>();
	    		List<GenTlActionplandtl> MomActionplandtl=new ArrayList<GenTlActionplandtl>();
	    		
	    		JSONArray MOmGridjson = null;	    		
	    		JSONArray MomKpijson = null;
	    		JSONArray MomActionplanJspon=null;
	    		JSONArray momActionpladtlJSon=null; 		
	    		if(UIUtils.isValidKeyId(momDetail) )
	    		{
	    			if( newGenTlMomdtl != null)
		    			newGenTlMommst.getMomeetingDetail().add(newGenTlMomdtl);
	    			    MOmGridjson = JSONArray.fromString(momDetail);
	    			    
	    			if( UIUtils.isValidKeyId(MomKpiIndicator)){
						MomKpijson = JSONArray.fromString(MomKpiIndicator);
						MomKpiList=(List<GenTlMomKpiLink>)UIUtils.convertJSONArrToList(newGenTlMomKpiLink, MomKpijson);
	    			}
	    			    momActionpladtlJSon = JSONArray.fromString(momDetail);
						MomGridList=(List<GenTlMomdtl>)UIUtils.convertJSONArrToList(newGenTlMomdtl, MOmGridjson);
						//newActionplandtl.setApldTargetdate(CommonFunctions.pg_getDateTimeFromDate(newActionplandtl.getApldTargetdate()));
						MomActionplandtl=(List<GenTlActionplandtl>)UIUtils.convertJSONArrToList(newActionplandtl, momActionpladtlJSon);
						
						newActionplandtl.setActionplanlist(MomActionplandtl);	
					    if(MomGridList!= null )
						{
						    	newGenTlMommst.setMomeetingDetail(MomGridList);
						    	if(MomKpiList!=null){
						    		newGenTlMommst.setMomeetingKPI(MomKpiList);	
						    	}
						 }	
	            }  
	                	    		      
		/**conversion END**/	
	/** save JsonARR conversion For Attendance **/
	           	
			  	 List<GenTlMomattendance> MomGridList1 = null;
	      		 JSONArray MOmGridjson1 = null;
	  		      if(UIUtils.isValidKeyId(MomAtt))
	  		      {
						MOmGridjson1 = JSONArray.fromString(MomAtt);
						MomGridList1=(List<GenTlMomattendance>)UIUtils.convertJSONArrToList(newMomattendance, MOmGridjson1);
						 if(MomGridList1!= null)
					     {
					    	newGenTlMommst.setMomeettingAttence(MomGridList1);
					           }	
	              }
			
	/**conversion END**/	
	  		      boolean insert = true;
	  		   
	  		      
	  		      
					if( newGenTlMommst.getMomsKeyid() == null)
					 {							
						existGenTlMommst =	NewmomeetingService.create(newGenTlMommst,existGenTlMommst,momeetingBean);
						//existGenTlMommst =	NewmomeetingService.updateatt(newGenTlMommst,existGenTlMommst);
						if(ActionPlanDesc!=null && ActionPlanDesc.length()>0 || filemanger!=null && filemanger.length()>0){
                  			existGenTlActionplanmst=NewmomeetingService.createActionPlan(newActionplanmst, newActionplandtl,existGenTlMommst);
						}
					 }	
					else
					  {
						existGenTlMommst = NewmomeetingService.update(newGenTlMommst,existGenTlMommst, momeetingBean);
						//existGenTlMommst =	NewmomeetingService.updateatt(newGenTlMommst,existGenTlMommst);
						if(ActionPlanDesc!=null && ActionPlanDesc.length()>0){
						existGenTlActionplanmst=NewmomeetingService.updateActionPlan(newActionplanmst, newActionplandtl,existGenTlMommst,Rowid,ActionPlanId,ActionplanDetailId);
						}
						insert = false;
					  }					
					httpSession.setAttribute(existGenTlMommst.getMomsKeyid(),existGenTlMommst);
					httpSession.setAttribute("GenTlMOmmst", existGenTlMommst);
					JSONObject successData = new JSONObject();
					String msgPropertyIdnt;					 
						 if( insert)
						   {
							msgPropertyIdnt = "success-save";
						   }
						 else
						msgPropertyIdnt = "success-update";					 
					    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
						successData.put("keyId", existGenTlMommst.getMomsKeyid());
						JSONObject returnData = new JSONObject();
						returnData.put("successData", successData);
						returnData.put("MomMstkeyid",existGenTlMommst.getMomsKeyid());
						returnData.put("MomMstNo",existGenTlMommst.getMomsMeetingno());
						//returnData.put("ActionKeyid",existGenTlActionplanmst.getAplmKeyid());
						if(UIUtils.isValidKeyId(filemanger)){
							returnData.put("filemanger",true);
							returnData.put("formClear",false);
						}
						
						if(UIUtils.isValidKeyId(momactnpln)){
							returnData.put("RowId",Rowid);
							returnData.put("momactnpln",true);
							returnData.put("formClear",false);
							returnData.put("MomMstkeyid",existGenTlMommst.getMomsKeyid());	
						}else
						{ 
							returnData.put("MomDtlkeyid","");
							returnData.put("RowId","");
							returnData.put("formClear",false);
							returnData.put("momactnpln", false);
						}
						out.print(returnData.toString());
	    	     }
									
		}   
                     catch (ValidationExceptions e) 
                     {
							e.printStackTrace();
							net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "NewMomeeting");
							out.print(errMessage.toString());							    	
                     }
												
			       catch(BusinessApplicationExceptions e)
			          {
			    	    e.printStackTrace();
			    	    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "NewMomeeting");
						errMessage.put("tpmException","Duplicate Entry");
						errMessage.put("displyMsg", false);			
						out.print(errMessage.toString());
					
						}
			      catch(Exception e)
			         {   
				         e.printStackTrace();
				         JSONObject err = new JSONObject();
				         err.put("tpmException", "Data Not Saved");
				         out.print(err.toString());
			        }
    	}

  private void DeleteNewMomRow(HttpServletRequest request,HttpServletResponse response) throws IOException{
     
      PrintWriter out = response.getWriter();
      String keyid = request.getParameter("keyid");
      String ActionPMasterId=request.getParameter("ActionPMasterId");
      try{
	  if(UIUtils.isValidKeyId(keyid)){
		NewmomeetingService.DeleteNewMomRow(keyid,ActionPMasterId);
		String msgPropertyIdnt = "success-delete";
		JSONObject err = new JSONObject();
		String mesg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt);
		err.put("successData",mesg);
		out.print(err.toString());
	}
}
  catch(Exception e){
	JSONObject err = new JSONObject();
	String mesg=UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete");
	err.put("successData",mesg);
	out.print(err.toString());
}
}
}