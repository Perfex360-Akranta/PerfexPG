
package com.akranta.tpm.controller;
/*	Created By:Siddharth.A
 * 
 * */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.OplTlCategorymst;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.model.OplTlPillarlink;
import com.akranta.tpm.model.OplTlStudent;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.OplTlMstService;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.OplTlMstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;

import com.akranta.tpm.service.api.OplTlMstServiceApi;


public class OplServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	OplTlMstService oplService;
	CommonFilterService commonFilterService;
	 OplTlMstServiceApi oplServiceApi;	
	 
    public OplServlet() {
        super();
       /* CommonMessage.debugMsg(" initialising servlet ....");
        try
        {
        	oplService = new OplTlMstServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
		} 
        catch (Exception e)
        {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        // TODO Auto-generated constructor stub
         * 
         */
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	String uri = request.getRequestURI();
    	ComboFilter doccomboFilter = new ComboFilter();

		HttpSession httpSession = request.getSession(false);  
		
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		
		response.setContentType("text/html");
		response.setContentType("text/json");
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action = :::::::::::::::       "+action);
		try {
			oplService = (OplTlMstServiceImpl)UIUtils.getServiceObject(request,"OplTlMstServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
		//	oplServiceApi = new OplTlMstServiceApi();	
		//	CommonMessage.debugMsg("  abnormalityServices jwt token : "+s.getAttribute("tpmjwttoken") );
			
			CommonMessage.debugMsg("  oplServices jwt token : "+ httpSession.getAttribute("tpmjwttoken"));
		//	abnService.AbnormalityFormServiceImplJwt((String) (s.getAttribute("tpmjwttoken") == null ? "" : s.getAttribute("tpmjwttoken")) );
			oplService.OplTlMstServiceImplJwt(
					   (String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken"))
					);
			
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}

		/* if (action.equals("opl_view.mom")) {  This URL Need To Give In Db,opl_view.mom
		
		
		request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));
		RequestDispatcher rd = request.getRequestDispatcher("/pages/OplModification.jsp"); 
		//RequestDispatcher rd = request.getRequestDispatcher("/pages/Reports/EquipmentCatgForm.jsp"); 
		rd.forward(request, response); 
	}
 		else if( action.equals("getModeopl_view.mom")){
		response.setContentType("text/html");
		//response.setContentType("text/json");
		
		PrintWriter out = response.getWriter();
		JSONObject result = new JSONObject();
		
		result.put("mode","create");
		result.put("url","modify_input.opl");
		result.put("formHeader","Creation");
		
		out.println(result);
	}
	
	
		else */ if (action.equals("create_input.opl") && user != null) 
		{ 
			String mode = "Create";
			UIUtils.displayRequestParamsValue(request);
			String oplKeyid = request.getParameter("oplKeyId");
			String bdmmode = request.getParameter("bdmmode");
			CommonMessage.debugMsg(bdmmode);
			//String oplmodes= request.getParameter("mod");
			String factId = request.getParameter("factoryId");
			String sectId = request.getParameter("sectionId");
			String mchId = request.getParameter("machineID");
			String cellId = request.getParameter("cellId");
			String yyId= request.getParameter("yyId");
			String whywhymod=request.getParameter("whywhymod");
			String Emppillar= request.getParameter("Emppillar");
			String refDocNo = request.getParameter("refDocNo");
			String refDocType = request.getParameter("refDocType");
			String flid = request.getParameter("flid");
			CommonMessage.debugMsg("yyId ="+yyId);
			String docId = request.getParameter("docId");
			String approvalMode = request.getParameter("Mode");
			String frmmode = request.getParameter("mod");
			
			CommonMessage.debugMsg("factId ="+factId+"sectId="+sectId+"mchId ="+mchId+"cellId="+cellId+"docId="+docId);
			
			httpSession.removeAttribute("docOPLId");
			httpSession.removeAttribute("yyIdOPL");
			if(UIUtils.isValidKeyId(refDocNo))
			{
				CommonMessage.debugMsg("refDocNo ="+refDocNo);
				httpSession.setAttribute("refDocNo",refDocNo);
			}
			if(UIUtils.isValidKeyId(refDocType))
			{
				CommonMessage.debugMsg("refDocType ="+refDocType);
				httpSession.setAttribute("refDocType",refDocType);
			}
			
			if(UIUtils.isValidKeyId(docId))
			{
				CommonMessage.debugMsg("docId ="+docId);
				httpSession.setAttribute("docOPLId",docId);
			}
			if(UIUtils.isValidKeyId(yyId))
			{
				CommonMessage.debugMsg("yyId Inside="+yyId);
				httpSession.setAttribute("yyIdOPL",yyId);
			}
			httpSession.removeAttribute("bdmmode");
			if(UIUtils.isValidKeyId(bdmmode)){
				CommonMessage.debugMsg(bdmmode+"mchId  :"+bdmmode);
				request.setAttribute("bdmmode", bdmmode);
				httpSession.setAttribute("bdmmode",bdmmode);
			}
			
			CommonMessage.debugMsg("oplKeyid"+oplKeyid);
			
			OplTlMst oplTlMst =null;
			OplTlLesson oplTlLesson = new OplTlLesson();
			OplFormBean oplFormBean = null;
			
       
			if( UIUtils.isValidKeyId(oplKeyid))
			{	
				CommonMessage.debugMsg(" oplKeyId  ----------- " +  oplKeyid);
				oplTlMst = (OplTlMst)httpSession.getAttribute("oplTlMst"+oplKeyid);
				
				//if( oplTlMst == null  ){
					try
					{
						CommonMessage.debugMsg("oplTlMst is not null"+oplTlMst);
						oplTlMst = oplService.select(oplKeyid);
				
						oplTlLesson.setOpllEmployeeid(user.getUsrm_ccno());
						CommonMessage.debugMsg("OPl keyid = "+oplKeyid +"Stud id=  "+user.getUsrm_ccno());
						
						List<String[]> oplStudentList = oplService.selectLesson(oplKeyid,user.getUsrm_ccno());
						
						CommonMessage.debugMsg("List Size ="+oplStudentList.size());
						CommonMessage.debugMsg("oplTlMst.getOplmStatus() val ="+oplTlMst.getOplmStatus());
						/*if("A".equals(oplTlMst.getOplmStatus())){
							 approvalMode = "APPROVAL";
							 frmmode = "approval";
						}
						*/
						if(oplStudentList.size()>0)
							httpSession.setAttribute("ChkUnderstoodOpl", "true");//oplFormBean.setChkUnderstoodOpl(true);
						else
							httpSession.setAttribute("ChkUnderstoodOpl", "false");
						
						// ----  finding the issue 
						CommonMessage.debugMsg("API date=" + oplTlMst.getOplmDate() + " len=" +
						        (oplTlMst.getOplmDate() == null ? 0 : oplTlMst.getOplmDate().length()));

						CommonMessage.debugMsg("API createdon=" + oplTlMst.getOplmCreatedon() + " len=" +
						        (oplTlMst.getOplmCreatedon() == null ? 0 : oplTlMst.getOplmCreatedon().length()));

						CommonMessage.debugMsg("API approveddate=" + oplTlMst.getOplmApproveddate() + " len=" +
						        (oplTlMst.getOplmApproveddate() == null ? 0 : oplTlMst.getOplmApproveddate().length()));

						
						
						// --- 
						
						filloplData(oplTlMst);
						
						String filePath = UIUtils.getImagePath(request);
						
						try
						{	 
							 CommonMessage.debugMsg("Inside image service");
							 String fileName=UIUtils.TPM_TEMPIMG_DIR;
							 oplTlMst = oplService.getoplImage(fileName,filePath,oplTlMst);
							 
							 
					    }
						catch(Exception e)
						{
							CommonMessage.debugMsg("Exception in while Selecting Image");   
						}
						
						httpSession.setAttribute("oplTlMst"+oplKeyid, oplTlMst);
					}
					catch(Exception e)
					{
						
						   e.printStackTrace(); 
					//	CommonMessage.debugMsg("Error While selecting oplkeyid"+e.getMessage());
					}
				}	
			//}
			
			if( oplTlMst == null)
			{
				oplTlMst = new  OplTlMst();
				oplFormBean = new OplFormBean(mode);
			}
			else
			{
				
				CommonMessage.debugMsg(" frmmode :: "+frmmode);
				if(frmmode.equals("Create"))
				{	
					oplTlMst.setOplmPreparedid(user.getUsrm_ccno());		//oplFormBean.setFormMode(mode);
				}
				
				else if(frmmode.equals("modify"))
				{	
					mode = "Update";
					//oplFormBean.setFormMode(mode);
				}
				else if(frmmode.equals("view"))
				{
					mode="View";
					//oplFormBean.setFormMode(mode);
				}
				else if(frmmode.equals("approval"))
				{
					
					mode="Approval";
					CommonMessage.debugMsg(" Inside :: mode "+mode);
					//oplFormBean.setFormMode(mode);
				}
			}
			
		    //oplTlMst.setOplmApprovedid(user.getUsrm_ccno());
			
			
			if( oplFormBean != null)
				oplFormBean.setResponsibility(user.getUsrm_ccno());
			else
				oplFormBean = new OplFormBean(mode);
			
			String classification =oplTlMst.getOplmClassification();
			
//				if(classification.substring(0, 1).equals("B"))
//					oplFormBean.setClassificationB("B");
//				if(classification.indexOf("I")>=0)
//					oplFormBean.setClassificationI("I");
//				if(classification.indexOf("T")>=0)
//					oplFormBean.setClassificationT("T");
				if(classification != null && classification.trim().length() > 0)
				{
				    String classificationTrim = classification.trim();
				    if(classificationTrim.contains("B"))
				        oplFormBean.setClassificationB("B");
				    if(classificationTrim.contains("I"))
				        oplFormBean.setClassificationI("I");
				    if(classificationTrim.contains("T"))
				        oplFormBean.setClassificationT("T");
				    if(classificationTrim.contains("S"))
				        oplFormBean.setClassificationS("S");
				    if(classificationTrim.contains("C"))
				        oplFormBean.setClassificationC("C");
				    if(classificationTrim.contains("P"))
				        oplFormBean.setClassificationP("P");
				}
			
			
			String chkUnderstoodOpl =(String)httpSession.getAttribute("ChkUnderstoodOpl");
			if(chkUnderstoodOpl!=null)
				oplFormBean.setChkUnderstoodOpl(chkUnderstoodOpl);
			
			oplFormBean.setFormMode(mode);
			
			//String oplmodes= request.getParameter("mod");
			//CommonMessage.debugMsg(" bdmmode:::::: Place "+oplmodes);
			//oplFormBean.setFormActionMode(oplmodes);
			
			CommonMessage.debugMsg("mode:d:"+oplFormBean.getFormActionMode());
			httpSession.removeAttribute("OplServletFormActionMode");
			httpSession.setAttribute("OplServletFormActionMode",oplFormBean.getFormActionMode());
			CommonMessage.debugMsg(" oplFormBean.getFormActionMode "+oplFormBean.getFormActionMode());
			
			if(UIUtils.isValidKeyId(factId))
				oplTlMst.setOplmFactoryid(factId);
		
			if(UIUtils.isValidKeyId(sectId))
				oplTlMst.setOplmSectionid(sectId);
			
			if(UIUtils.isValidKeyId(cellId))
				oplTlMst.setOplmCellid(cellId);
			
			if(UIUtils.isValidKeyId(mchId))
			{
				oplTlMst.setOplmMachineid(mchId);
			}
			
			if(UIUtils.isValidKeyId(flid))
			{
				oplTlMst.setOplmFlid(flid);
			}
			if(UIUtils.isValidKeyId(refDocNo))
			{
				CommonMessage.debugMsg("refDocNo ="+refDocNo);
				httpSession.setAttribute("refDocNo",refDocNo);
				oplTlMst.setOplmRefdocno(refDocNo);
			}
			if(UIUtils.isValidKeyId(refDocType))
			{
				CommonMessage.debugMsg("refDocType ="+refDocType);
				httpSession.setAttribute("refDocType",refDocType);
				oplTlMst.setOplmRefdoctype(refDocType);
			}
			CommonMessage.debugMsg("JDFDHoplTlMst...."+oplTlMst.getOplmMpworthy());
			request.setAttribute("oplTlLesson", oplTlLesson);
			request.setAttribute("oplTlMst", oplTlMst);
			request.setAttribute("oplFormBean", oplFormBean);
			request.setAttribute("oplKeyid", oplKeyid);
			request.setAttribute("Emppillar", Emppillar);
			request.setAttribute("approvalMode", approvalMode);
			request.setAttribute("whywhymod", whywhymod);
			
			
		}
		
		else if( action.equals("modify_input.opl")||action.equals("oplVw_input.opl"))
		{
			
				try
				{
					String Modifymode = request.getParameter("form");
					CommonMessage.debugMsg(" oplKeyid :: "+Modifymode);
					OplFormBean oplFormBean=null;
					if(action.equals("modify_input.opl"))
					{
						//CommonFilter commonFilter =populateCommonFilter(request,"OnePointLessonCommonFilter",true);
						oplFormBean = new OplFormBean("Update");
						oplFormBean.setFormActionMode("modify");
						oplFormBean.setFormHeader("Modify - OPL");
					}
					
					else if(action.equals("oplVw_input.opl"))
					{
						oplFormBean = new OplFormBean("View");
						oplFormBean.setFormActionMode("view");
						oplFormBean.setFormHeader("View - OPL");
					}//form
					String filterString = request.getParameter("filterString");
					String mode = request.getParameter("mod");
					String empKeyId=request.getParameter("empKeyId");
					CommonMessage.debugMsg(" Inside view action "+mode);
					request.setAttribute("empKeyId",empKeyId);
					request.setAttribute("mode", mode);
					request.setAttribute("filterStr", filterString);
					request.setAttribute("oplFormBean",oplFormBean);
					request.setAttribute("Modifymode",Modifymode);
					request.setAttribute("respnlity",user.getUsrm_ccno());
					httpSession.removeAttribute("oplFormBean");
					httpSession.setAttribute("oplFormBean", oplFormBean);
					httpSession.setAttribute("oplFormBean", oplFormBean);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/OplModification.jsp"); 
					rd.forward(request, response); 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

		}
		
		
//************************************************Individual Employee**********************************************//		
		else if( action.equals("individualmodify_input.opl") ||action.equals("individualoplVw_input.opl"))
		{
			try
			{
					String Modifymode = request.getParameter("form");
					CommonMessage.debugMsg(" oplKeyid :: "+Modifymode);
					AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
					String empId=userkeyid.getUsrm_ccno();
				  	CommonMessage.debugMsg("The empId::"+empId);
				  	
					OplFormBean oplFormBean=null;
					if(action.equals("modify_input.opl"))
					{
						oplFormBean = new OplFormBean("Update");
						oplFormBean.setFormActionMode("modify");
						oplFormBean.setFormHeader("Modify - OPL");
					}
					
					else if(action.equals("individualoplVw_input.opl"))
					{
						oplFormBean = new OplFormBean("View");
						oplFormBean.setFormActionMode("view");
						oplFormBean.setFormHeader("View - OPL");
					}
					String filterString = request.getParameter("filterString");
					String mode = request.getParameter("mod");
					String empKeyId=request.getParameter("empKeyId");
					CommonMessage.debugMsg("empKeyId"+empKeyId);
					CommonMessage.debugMsg(" Inside view action "+mode);
					request.setAttribute("empKeyId",empKeyId);
					request.setAttribute("empId", empId);
					request.setAttribute("mode", mode);
					request.setAttribute("filterStr", filterString);
					request.setAttribute("oplFormBean",oplFormBean);
					request.setAttribute("Modifymode",Modifymode);
					request.setAttribute("respnlity",user.getUsrm_ccno());
					httpSession.removeAttribute("oplFormBean");
					httpSession.setAttribute("oplFormBean", oplFormBean);
					httpSession.setAttribute("oplFormBean", oplFormBean);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/OPLIndividual.jsp"); 
					rd.forward(request, response); 
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
		}		

		else if( action.equals("individualmodify_getCol.opl")|| action.equals("individualoplVw_getCol.opl"))
		{
			
			try
			{	
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = new CommonFilter();
				ComboFilter cmbFactory = new ComboFilter();
				ComboFilter cmbSection = new ComboFilter();
				ComboFilter cmbMachine = new ComboFilter();
				ComboFilter cmbCell = new ComboFilter();
				commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",true);
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
			  	commonFilter.setAbnDetectBy(empId);
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("The empKeyId"+empKeyId);
				commonFilter.setEmpch(empKeyId);
				String factId = request.getParameter("factoryId");
				String sectId = request.getParameter("sectionId");
				String mchId = request.getParameter("machineID");
				String cellId = request.getParameter("cellId");
				String docId = request.getParameter("docId");
				String yyId= request.getParameter("yyId");
				String mode=request.getParameter("mode");
				String prepardby=request.getParameter("prepardby");
				
				String formmode=request.getParameter("Formmode");
				
				String emppillar=request.getParameter("Emppillar");

				CommonMessage.debugMsg(" Inside Servlet mode :: "+prepardby+" formmode ::"+formmode+" mode "+mode);
				
				
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);
				
				if (action.equals("approval_getData.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("APPROVAL");
				else if (action.equals("modify_getCol.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("MODIFY");
				
				if (UIUtils.isValidKeyId(formmode))
					commonFilter.setType(formmode);
				
				
				CommonMessage.debugMsg("factId ="+factId+"sectId="+sectId+"mchId ="+mchId+"cellId="+cellId+"docId="+docId);
				
				httpSession.removeAttribute("docOPLId");
				httpSession.removeAttribute("yyIdOPL");
				if(UIUtils.isValidKeyId(docId))
				{
					CommonMessage.debugMsg("docId ="+docId);
					httpSession.setAttribute("docOPLId",docId);
				}
				if(UIUtils.isValidKeyId(yyId))
				{
					CommonMessage.debugMsg("yyId Inside="+yyId);
					httpSession.setAttribute("yyIdOPL",yyId);
				}
				
				if(UIUtils.isValidKeyId(factId))
				{	
					cmbFactory.setId(factId);
					commonFilter.setFactory(cmbFactory);
				}
				if(UIUtils.isValidKeyId(sectId))
				{
					cmbSection.setId(sectId);
					commonFilter.setSection(cmbSection);
				}
				if(UIUtils.isValidKeyId(mchId))
				{
					cmbMachine.setId(mchId);
					commonFilter.setMachine(cmbMachine);
				}
				if(UIUtils.isValidKeyId(cellId))
				{
					cmbCell.setId(cellId);
					commonFilter.setCell(cmbCell);
				}
				
				httpSession.removeAttribute("oplCommonFilterVal");
				httpSession.setAttribute("oplCommonFilterVal", commonFilter);
				
				List<String []> oplModifyList  = oplService.getIndividualOplReport(commonFilter,emppillar);
		      		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = oplModifyList.get(1);			
					String [] colHeaderCond = oplModifyList.get(0);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "80%%");
					jsonObject.put("tableWidth", "106%%");
			      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
			      	httpSession.setAttribute("oplIndividualColModel", jsonObject);
					out.println(jsonObject);     	 
					//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplModificationtable"));
				//}
			}																								
	   
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		else if( action.equals("individualmodify_getData.opl")||action.equals("individualoplVw_getData.opl"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
			  	commonFilter.setAbnDetectBy(empId);
				String mode = request.getParameter("mode");
				String prepardby=request.getParameter("prepardby");
				String emppillar=request.getParameter("Emppillar");
				String formmode=request.getParameter("Formmode");
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("empdkyied"+empKeyId);
				commonFilter.setEmpch(empKeyId);
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);		
				if (action.equals("approval_getData.opl") && !UIUtils.isValidKeyId(formmode))
				    commonFilter.setMainGroup("APPROVAL");
				else if (action.equals("individualmodify_getData.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("MODIFY");
			
				if (UIUtils.isValidKeyId(formmode))
					commonFilter.setType(formmode);
				
				CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
				
				List<String []> oplModifyList  = oplService.getIndividualOplReport(commonFilter,emppillar); 
				
				CommonMessage.debugMsg(" Inside :: EmPillar For OPL :: "+commonFilter.getMPWorthy());
				
				JSONObject oplRptData = UIUtils.convertToJqGridTableObject(oplModifyList,request,2,0,commonFilter.getTotalRecordCnt());
				CommonMessage.debugMsg("Total Count:"+commonFilter.getTotalRecordCnt());
				out.println(oplRptData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Get Data Exception"+e.getMessage());
			}
		}		
		
		else if(action.equals("individualmodify_getExcel.opl")||action.equals("individualoplVw_getExcel.opl"))
		{

				CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				AdmTlUsermst userkeyid=UIUtils.getLoginUser(request);
				String empId=userkeyid.getUsrm_ccno();
			  	CommonMessage.debugMsg("The empId::"+empId);
			  	commonFilter.setAbnDetectBy(empId);
				CommonMessage.debugMsg("Inside the Excel"); 
				String prepardby=request.getParameter("prepardby");
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);
				commonFilter.setFromRow(null);
				JSONObject tblJSONObj = (JSONObject)httpSession.getAttribute("oplIndividualColModel");
				tblJSONObj.put("title", "Individual OPL Details");
				String format = ExcelUtils.getFormat(request);
				CommonMessage.debugMsg("The Format is:::"+format);
				Workbook wb = oplService.IndividualoplRptExportExcel(commonFilter,tblJSONObj,format);
				CommonMessage.debugMsg("Excel Utiles::::"+wb);
				ExcelUtils.writeToResponse(response, wb, "OPL", format);	
		}
		
		 if(action.equals("approval_input.opl"))
		{
				String Formmode=request.getParameter("frm");
				request.setAttribute("Formmode", Formmode);
				request.setAttribute("oplmode", "approval");
				RequestDispatcher rd = request.getRequestDispatcher("/pages/OplModification.jsp"); 
				rd.forward(request, response); 
		}
		else if(action.equals("OplFillEmploy_modify.opl")){
			 try {
				    PrintWriter out = response.getWriter();
					String keyid = request.getParameter("emplyid");
					CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
					List<String []> condReclData  = oplService.FillEmployeeDatainGrid(keyid);
					out.print( JSONArray.fromCollection(condReclData));
	   			} catch (Exception e) {

				}
		}
		else if( action.equals("create_save.opl"))
		{
			OplFormBean oplFormBean = new  OplFormBean();
			saveOpl(request,response,oplFormBean);
	    }
		 

		else if( action.equals("updateApprovedStatusLevel.opl"))
		{
			PrintWriter out = response.getWriter();
			OplFormBean oplFormBean = new  OplFormBean();
			OplTlMst newOplTlMst = new OplTlMst();
			String status=request.getParameter("status");
			String nextLevel=request.getParameter("nextLevel");
			String keyid=request.getParameter("keyid");
			String type=request.getParameter("type");
			String value=request.getParameter("value");
			String mpvalue=request.getParameter("mpvalue");
			
			CommonMessage.debugMsg("nextLevel===="+nextLevel);
			newOplTlMst = oplService.updateApprovedStatusLevel(status,keyid, nextLevel,type,value,mpvalue);
			
			JSONObject returnData = new JSONObject();
			//returnData.put("formClear",clrVal);
			String saveMsg = "success-save";
			returnData.put("msg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",saveMsg));
			//returnData.put("successData", successData);
			out.print(returnData.toString());
			
			
			//saveOpl(request,response,oplFormBean);
	    }
		 
		
		else if( action.equals("modify_delete.opl"))
		{	
			OplFormBean oplFormBean = new  OplFormBean();
			deleteOpl(request,response, oplFormBean);
	    }
		else if( action.equals("imageclear_delete.opl"))
		{	
			ServletOutputStream out = response.getOutputStream();
			String keyid = request.getParameter("keyid");
			String imagetype = request.getParameter("IMAGETYPE");
			//OplTlMst newOplTlMst = new OplTlMst();
			//newOplTlMst =
				oplService.deleteimage(keyid,imagetype);
				
				String delMsg="success-delete";
		
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",delMsg));
				JSONObject returnData = new JSONObject();
				returnData.put("displyMsg", true);
				returnData.put("successData", successData);
				out.print(returnData.toString());
				
	    }
		 
		else if( action.equals("combo_documentNo.opl"))
		{
			try 
			{
				
				doccomboFilter=UIUtils.fillComboFilter(request);
				
				List<ComboBox>  oplmKeyids = oplService.getDocumentNoCombo(doccomboFilter);
				UIUtils.writeComboBox(response, oplmKeyids,doccomboFilter);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		else if(action.equals("functionalLoc.opl")){
			
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmboplmFactoryid");
			functLocFieldNameBean.setSection("cmboplmSectionid");
			functLocFieldNameBean.setCell("cmboplmCellid");
			functLocFieldNameBean.setMachine("cmboplmMachineid");
			functLocFieldNameBean.setFunctionalLocId("cmboplmFlid");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(true);
			functLocFieldNameBean.setMachMandatory(false);
			
			OplFormBean oplFormBean=(OplFormBean)httpSession.getAttribute("oplFormBean");
			FormModes formModes = FormModes.create;//(FormModes)httpSession.getAttribute("AbnormalityFormMode");
			boolean funcLocnView=false;
			String bdmmode=(String)httpSession.getAttribute("bdmmode");
			
			if(UIUtils.isValidKeyId(bdmmode))
				funcLocnView=true;
			
			if(( formModes == FormModes.completion)||funcLocnView==true)
				{formModes = FormModes.view;}
			else if(oplFormBean!=null && oplFormBean.getFormActionMode().equalsIgnoreCase("view"))
				{formModes = FormModes.view;}
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		
				
		else if( action.equals("modify_getCol.opl")||action.equals("oplVw_getCol.opl") || action.equals("approval_getCol.opl")||action.equals("EmPillar_getCol.opl") )
		{
			
			try
			{	
				PrintWriter out = response.getWriter();
				CommonFilter commonFilter = new CommonFilter();
				ComboFilter cmbFactory = new ComboFilter();
				ComboFilter cmbSection = new ComboFilter();
				ComboFilter cmbMachine = new ComboFilter();
				ComboFilter cmbCell = new ComboFilter();
				commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",true);
				//commonFilter = FilterValues.getCommonFilters(request, commonFilter); 			
				//commonFilter = FilterValues.getOPLandKaizen(request, commonFilter);
				String empKeyId=request.getParameter("empKeyId");
				commonFilter.setEmpch(empKeyId);
				String factId = request.getParameter("factoryId");
				String sectId = request.getParameter("sectionId");
				String mchId = request.getParameter("machineID");
				String cellId = request.getParameter("cellId");
				String docId = request.getParameter("docId");
				String yyId= request.getParameter("yyId");
				String mode=request.getParameter("mode");
				String prepardby=request.getParameter("prepardby");
				
				String formmode=request.getParameter("Formmode");
				
				String emppillar=request.getParameter("Emppillar");

				CommonMessage.debugMsg(" Inside Servlet mode :: "+prepardby+" formmode ::"+formmode+" mode "+mode);
				
				
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);
				
				if (action.equals("approval_getData.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("APPROVAL");
				else if (action.equals("modify_getCol.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("MODIFY");
				
				if (UIUtils.isValidKeyId(formmode))
					commonFilter.setType(formmode);
				
				
				CommonMessage.debugMsg("factId ="+factId+"sectId="+sectId+"mchId ="+mchId+"cellId="+cellId+"docId="+docId);
				
				httpSession.removeAttribute("docOPLId");
				httpSession.removeAttribute("yyIdOPL");
				if(UIUtils.isValidKeyId(docId))
				{
					CommonMessage.debugMsg("docId ="+docId);
					httpSession.setAttribute("docOPLId",docId);
				}
				if(UIUtils.isValidKeyId(yyId))
				{
					CommonMessage.debugMsg("yyId Inside="+yyId);
					httpSession.setAttribute("yyIdOPL",yyId);
				}
				
				if(UIUtils.isValidKeyId(factId))
				{	
					cmbFactory.setId(factId);
					commonFilter.setFactory(cmbFactory);
				}
				if(UIUtils.isValidKeyId(sectId))
				{
					cmbSection.setId(sectId);
					commonFilter.setSection(cmbSection);
				}
				if(UIUtils.isValidKeyId(mchId))
				{
					cmbMachine.setId(mchId);
					commonFilter.setMachine(cmbMachine);
				}
				if(UIUtils.isValidKeyId(cellId))
				{
					cmbCell.setId(cellId);
					commonFilter.setCell(cmbCell);
				}
				
				httpSession.removeAttribute("oplCommonFilterVal");
				httpSession.setAttribute("oplCommonFilterVal", commonFilter);
				  commonFilter.setIsGetCol("Y");
				List<String []> oplModifyList  = oplService.getAllOplReport(commonFilter,emppillar);
				//if(action.equals("oplVw_getCol.opl")){
					//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplView"));
				//}else{
					// jsonObject = getTableModel(oplCummulativeList,FilterValues.getHeader( commonFilter.getDrillCaption()));
		      		JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();
					
					jqGridTableModel.setRowNumbers(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setTableButton(true);
					gridColModel.setHeaderNum(1);
					
					String [] colHeader = oplModifyList.get(1);			
					String [] colHeaderCond = oplModifyList.get(0);
					
					//CommonMessage.debugMsg("   TABLEMODEL     "+choiceCol.get(0)[1]);
					List<String[]> headers = new ArrayList<String[]>();
					//headers.add(colHeaderCond);
					headers.add(colHeader);
					
					JSONObject jsonObject = UIUtils.getTableModel(headers,colHeaderCond,jqGridTableModel,gridColModel);
					jsonObject.put("tableHeight", "80%%");
					jsonObject.put("tableWidth", "106%%");
			      	CommonMessage.debugMsg("jsonObject ="+jsonObject);
			      	httpSession.setAttribute("empillarColModel", jsonObject);
					out.println(jsonObject);
			      	 
					//out.print(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "oplModificationtable"));
				//}
			}																								
	   
			catch (Exception e) 
			{
				e.printStackTrace();
			}

		}
		
		else if( action.equals("modify_getData.opl")||action.equals("oplVw_getData.opl") || action.equals("approval_getData.opl")||action.equals("EmPillar_getData.opl"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				//CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("oplCommonFilterVal");
				CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
				//String chkMPWorthy=request.getParameter("chkMPWorthy");
				
				String mode = request.getParameter("mode");
				String prepardby=request.getParameter("prepardby");
				String emppillar=request.getParameter("Emppillar");
				String formmode=request.getParameter("Formmode");
				String empKeyId=request.getParameter("empKeyId");
				CommonMessage.debugMsg("empdkyied"+empKeyId);
				commonFilter.setEmpch(empKeyId);
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);
				
				if (action.equals("approval_getData.opl") && !UIUtils.isValidKeyId(formmode))
				    commonFilter.setMainGroup("APPROVAL");
				else if (action.equals("modify_getData.opl") && !UIUtils.isValidKeyId(formmode))  
					commonFilter.setMainGroup("MODIFY");
				

				if (UIUtils.isValidKeyId(formmode))
					commonFilter.setType(formmode);
				
				CommonMessage.debugMsg(" Inside Servlet mode :: "+mode);
				 commonFilter.setIsGetCol("N");
				List<String []> oplModifyList  = oplService.getAllOplReport(commonFilter,emppillar); 
				
				CommonMessage.debugMsg(" Inside :: EmPillar For OPL :: "+commonFilter.getMPWorthy());
				
				JSONObject oplRptData = UIUtils.convertToJqGridTableObject(oplModifyList,request,2,0,commonFilter.getTotalRecordCnt());
				CommonMessage.debugMsg("Total Count:"+commonFilter.getTotalRecordCnt());
				
				out.println(oplRptData);
				
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Get Data Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("EmPillar_getExcel.opl")){
			
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("empillarColModel");
			//JSONObject colmodel = UIUtils.getXlColModel(request, response);
			colmodel.put("title","EM Pillar OPL Report");
            String format = ExcelUtils.getFormat(request);
            String emppillar=request.getParameter("Emppillar");
            CommonMessage.debugMsg(" Inside Servlet emppillar "+emppillar);
            
			Workbook wb = oplService.getEmPillarOPLReportExcel(colmodel,format,commonFilter,emppillar);
			ExcelUtils.writeToResponse(response, wb, "EMPillarOPLReport", format);
			
			
		}
		 
		else if( action.equals("filterXmlmodify_input.opl")||action.equals("filterXmloplVw_input.opl"))
		{
			response.setContentType("xml"); 
			UIUtils.forwardRequest(request, response, "/tiles/xml/OPLReport.xml") ;
		}
		
		else if(action.equals("modify_getExcel.opl")||action.equals("oplw_getExcel.opl")||action.equals("oplVw_getExcel.opl")|action.equals("approval_getExcel.opl"))
		{
				//CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute("oplCommonFilterVal");
				CommonFilter commonFilter = populateCommonFilter(request,"OnePointLessonCommonFilter",false);
				String tmpFromRow = commonFilter.getFromRow();
				String viewGrid="";
				
				if(action.equals("oplVw_getExcel.opl"))
					viewGrid="oplView";
				else if(action.equals("modify_getExcel.opl"))
					viewGrid="oplModificationtable";
				
				
				if (action.equals("approval_getExcel.opl")) 
				{
					
					commonFilter.setMainGroup("APPROVAL");
				}
				else if (action.equals("modify_getExcel.opl"))  
					commonFilter.setMainGroup("MODIFY");
				
				
				String prepardby=request.getParameter("prepardby");
				
				if (UIUtils.isValidKeyId(prepardby))
					commonFilter.setRescheduleTo(prepardby);

				
				commonFilter.setFromRow(null);
				    //String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", viewGrid);
				//JSONObject tblJSONObj = UIUtils.getXlColModel(request, response);
				    //JSONObject tblJSONObj = JSONObject.fromString(tableModel);
				
				JSONObject tblJSONObj = (JSONObject) httpSession.getAttribute("empillarColModel");
				
				tblJSONObj.put("title", "OPL Details");
				String format = ExcelUtils.getFormat(request);
		
				Workbook wb = oplService.oplRptExportExcel(commonFilter,tblJSONObj,format);
				//commonFilter.setFromRow(tmpFromRow);
					
				ExcelUtils.writeToResponse(response, wb, "OPL", format);
				
		}
		
		
		else if( action.equals("opl_category.opl"))
		{
			OplFormBean oplFormBean = new OplFormBean("Category");
			request.setAttribute("oplFormBean", oplFormBean);
			saveOpl(request,response,oplFormBean);
		}
	 
		
		else if(action.equals("excel_input.opl"))
		{
			OplTlLesson oplTlLesson =new OplTlLesson();
			try 
			{
				UIUtils.setBeanProperties(oplTlLesson, request);
				oplTlLesson.setOpllEmployeeid(user.getUsrm_ccno());
				oplTlLesson = oplService.insertIntoLesson(oplTlLesson);
				
			}
			catch (ValidationExceptions e) 
			{
				e.printStackTrace();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			
		}
		
		else if(action.equals("oplStudent_input.opl"))
		{
			
			
		}
		
		else if( action.equals("oplStudent_getCol.opl"))
		{
			
			try
			{	
				
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();
				PrintWriter out = response.getWriter();
				JSONObject json = JSONObject.fromString(UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "StudentTable"));
		
				
				String[] formatterval  = {"dteOpllDate#2","cmboplcombo#3"};
				json.put("formatterIndex",formatterval);
				out.print(json );
				
			}
			
			catch(Exception e)
			{
				CommonMessage.debugMsg("Student getCol Exception"+e.getMessage());
			}
		}
		
		else if(action.equals("oplStudent_getData.opl"))
		{
			try
			{	
				PrintWriter out = response.getWriter();
				String cellId=request.getParameter("cellId");
				CommonMessage.debugMsg("cellId:::::::::::: "+cellId);
				String oplKeyid=request.getParameter("hdnoplkeyid");
				CommonMessage.debugMsg(" HiddenKeyid:::::::::::: "+oplKeyid);
                String id = user.getUsrm_keyid();
				CommonMessage.debugMsg(" Inside Opl Servlet Id :"+id);
                String oplId = request.getParameter("oplId");
                CommonMessage.debugMsg(" HiddenoplId:::::::::::: "+oplId);
				List<String []> oplStudentList  = oplService.getAllStudent(oplId,cellId,oplKeyid);
				// -- Vignesh 
				CommonMessage.debugMsg("First row length = " + oplStudentList.get(0).length);
				CommonMessage.debugMsg("First row = " + Arrays.toString(oplStudentList.get(0)));

				JSONObject oplStudentData = UIUtils.convertToJqGridTableObject(oplStudentList,request,0,2);
				out.println(oplStudentData);
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Opl Category Exception"+e.getMessage());
			}
			
		}
		
		else if(action.equals("Pillar_input.opl"))
		{
			
		}else if(action.equals("EmPillar_input.opl"))
		{
			
		     String oplKeyid=request.getParameter("oplKeyId");
		     
		     OplTlMst oplTlMst =null;
			
		     if( UIUtils.isValidKeyId(oplKeyid))
			 {	
				CommonMessage.debugMsg(" oplKeyId  ----------- " +  oplKeyid);
				oplTlMst = (OplTlMst)httpSession.getAttribute("oplTlMst"+oplKeyid);
				
				try
				{
					CommonMessage.debugMsg("oplTlMst is not null"+oplTlMst);
					oplTlMst = oplService.select(oplKeyid);
				}catch(Exception e)
				{
					CommonMessage.debugMsg("Error While selecting oplkeyid"+e.getMessage());
				}
			}
		     CommonMessage.debugMsg("Error While selecting oplkeyid");
		     request.setAttribute("EmPillar", "EmPillar");
		  RequestDispatcher rd = request.getRequestDispatcher("/pages/EmPillarOpl.jsp"); 
		  rd.forward(request, response);
		  
		}
		/*else if(action.equals("EmPillar_getCol.opl"))
		{

			List<String[]> oplEmPillarGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
			    CommonFilter commonFilter = populateCommonFilter(request,"TroubleShootingCommonFilter",true);
			    oplEmPillarGrid = oplService.getAllOplEmpPillarMainGrid(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = oplEmPillarGrid.get(1);
				String [] colHeaderHead = oplEmPillarGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "80%%");
				httpSession.removeAttribute("FishBoneColModel");
				httpSession.setAttribute("FishBoneColModel", colmodel);
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(action.equals("EmPillar_getData.opl"))
		{
			
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"TroubleShootingCommonFilter",false);
				List<String[]> fishGridList =  oplService.getAllOplEmpPillarMainGrid(commonFilter);

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
		*/
		else if(action.equals("Pillar_getCol.opl"))
		{
			PrintWriter out = response.getWriter();
			String oplPillarColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "PillarTable");
			out.print(oplPillarColM);
			
		}
		else if(action.equals("Pillar_getData.opl"))
		{
			try
			{	
				String oplId = request.getParameter("oplId");
				PrintWriter out = response.getWriter();
				OplTlMst oplTlMst =(OplTlMst) httpSession.getAttribute("oplTlMst"+oplId);	
				List<String[]> oplPillarList = oplService.getAllPillarNameCodes(oplId);
				
				JSONObject oplPillarLnkData = UIUtils.convertToJqGridTableObject(oplPillarList,request,0,3);
				List<OplTlPillarlink> oplTlPillarlinkList =	populatePillarLinkList(oplPillarList);
				
				if( oplTlMst != null )
					oplTlMst.setPillarLink(oplTlPillarlinkList);
				
				httpSession.setAttribute("oplTlMst"+oplId,oplTlMst);	
				oplPillarLnkData.put("oplcCategoryid", oplId);
	    		
	    		out.println(oplPillarLnkData);
				
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Get Data Exception"+e.getMessage());
			}
		}
	
	
		else if(action.equals("imprvCategory_input.opl"))
		{
			String dispatchUrl = "/pages/MultiSelectPopup.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
		}
		else if(action.equals("Datevalidate_input.opl"))
		{	PrintWriter out = response.getWriter();
			String KeyId = request.getParameter("keyid");//
			String rowId = request.getParameter("rowId");
			CommonMessage.debugMsg(KeyId);
			try {
				List<String []> opldatevalidateList  = oplService.getdatevalidate(KeyId,rowId);
				//CommonMessage.debugMsg("resultn  "+opldatevalidateList.get(0) +"   second  "+opldatevalidateList.get(1));
				out.print( JSONArray.fromCollection(opldatevalidateList));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(action.equals("Datevalidation_input.opl"))
		{
			PrintWriter out = response.getWriter();
			String KeyId = request.getParameter("keyid");//
			String rowId = request.getParameter("rowId");
			CommonMessage.debugMsg(KeyId);
			try {
				List<String []> opldatevalidateList  = oplService.getdatevalidation(KeyId);
				out.print( JSONArray.fromCollection(opldatevalidateList));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if(action.equals("Datevalidation_input.opl"))
		{
			PrintWriter out = response.getWriter();
			String KeyId = request.getParameter("keyid");
			String rowId = request.getParameter("rowId");
			CommonMessage.debugMsg(KeyId);
			try {
				List<String []> opldatevalidateList  = oplService.getdatevalidation(KeyId);
				out.print( JSONArray.fromCollection(opldatevalidateList));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}else if(action.equals("Datevalidating_input.opl"))
		{
			PrintWriter out = response.getWriter();
			String KeyId = request.getParameter("keyid");//
			String rowId = request.getParameter("rowId");
			CommonMessage.debugMsg(KeyId);
			try {
				List<String []> opldatevalidateList  = oplService.getdatevalidating(KeyId,rowId);
				out.print( JSONArray.fromCollection(opldatevalidateList));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		else if(action.equals("imprvCategory_getCol.opl"))
		{
			PrintWriter out = response.getWriter();
			String oplImprCategoryColM = UIUtils.getPropertyValue("com.akranta.tpm.resources.OplRelatedColHeaders", "ImprovCategoryTable");
			out.print(oplImprCategoryColM);
		}
		
		else if(action.equals("imprvCategory_getData.opl"))
		{
			
			PrintWriter out = response.getWriter();
			List<String> pillarId=new ArrayList<String>();
			pillarId.add(request.getParameter("pillarId"));	
			try 
			{
				List<String []> ImprCategoryList  = oplService.getAllImprvCategory(pillarId);
				CommonMessage.debugMsg("ImprCategoryList ="+ImprCategoryList.size());
				if(ImprCategoryList.size()<=0)
				{
					String msg="No Records Found";
					JSONObject returnData= new JSONObject();
					CommonMessage.debugMsg("mmsg  ="+msg);
				    returnData.put("dataNotExist", msg);	
					out.print(returnData.toString());
				}
				
				JSONObject ImprCategoryData = UIUtils.convertToJqGridTableObject(ImprCategoryList,request,0,0);
				out.println(ImprCategoryData);
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
			
		}
		
		else if(action.equals("create_delete.opl"))
		{
			OplFormBean oplFormBean = new  OplFormBean();
			deleteOpl(request,response, oplFormBean);
		}
		else if(action.equals("OplRowUpdate_getData.opl")) {
            ServletOutputStream out = response.getOutputStream();
            JSONObject returnData = new JSONObject();
            try {
                    String keyId = request.getParameter("keyId");
                    CommonMessage.debugMsg("OplRowUpdate_getData keyId: " + keyId);
                    if(UIUtils.isValidKeyId(keyId)) {
                            List<String[]> updatedRow = oplService.getOplUpdatedRow(keyId);
                            if(updatedRow != null && !updatedRow.isEmpty()) {
                                    String rowString = String.join(",&!@$", updatedRow.get(0));
                                    returnData.put("updatedRow", rowString);
                                    returnData.put("rowId", keyId);
                            }
                    }
                    out.print(returnData.toString());
            } catch(Exception e) {
                    e.printStackTrace();
                    returnData.put("tpmException", "Row update failed");
                    out.print(returnData.toString());
            }
    }
		
		if (action.equals("create_input.opl")) 
		{
			String Formmode=request.getParameter("frm");
			CommonMessage.debugMsg("Formmode::1"+Formmode);
			String Mode=request.getParameter("Mode");
			CommonMessage.debugMsg("Mode::1"+Mode);
			String refDocNo = request.getParameter("refDocNo");
			CommonMessage.debugMsg("refDocNo::1"+refDocNo);
			String refDocType = request.getParameter("refDocType");
			CommonMessage.debugMsg("refDocType::1"+refDocType);
			String whywhymod=request.getParameter("whywhymod");
			CommonMessage.debugMsg("whywhymod::1"+whywhymod);
			String Emppillar= request.getParameter("Emppillar");
			CommonMessage.debugMsg("Emppillar::1"+Emppillar);
			
			if(UIUtils.isValidKeyId(refDocNo))
			{
				CommonMessage.debugMsg("refDocNo ="+refDocNo);
				request.setAttribute("refDocNo",refDocNo);
			}
			if(UIUtils.isValidKeyId(refDocType))
			{
				CommonMessage.debugMsg("refDocType ="+refDocType);
				request.setAttribute("refDocType",refDocType);
			}
			
			//request.setAttribute("Emppillar", Emppillar);
			CommonMessage.debugMsg(" Formmode ::::: "+Mode);
			request.setAttribute("Emppillar", Emppillar);
			request.setAttribute("Formmode", Formmode);
			request.setAttribute("Mode", Mode);
			request.setAttribute("User",user.getUsrm_ccno());
			request.setAttribute("whywhymod", whywhymod);
			String dispatchUrl = "/pages/OnePointLesson.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl); 
			rd.forward(request, response); 
		}
	}

//    private void filloplData(OplTlMst oplTlMst) {
//		// TODO Auto-generated method stub
//		oplTlMst.setOplmAftercondition(oplTlMst.getOplmAftercondition().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmPresentcondition(oplTlMst.getOplmPresentcondition().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmLesson(oplTlMst.getOplmLesson().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmTheme(oplTlMst.getOplmTheme().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmClassdescription(oplTlMst.getOplmClassdescription().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmDepartmentmanager(oplTlMst.getOplmDepartmentmanager().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmSectionmanager(oplTlMst.getOplmSectionmanager().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmGroupleader(oplTlMst.getOplmGroupleader().replace("<*","").replace("*>",""));
//    	oplTlMst.setOplmDate(oplTlMst.getOplmDate().substring(0,11));
//    	oplTlMst.setOplmPrepareddate(oplTlMst.getOplmPrepareddate().substring(0,11));
//    	oplTlMst.setOplmApproveddate(oplTlMst.getOplmApproveddate().substring(0,11));
//	}

    // --- api call -- vignesh -- //


private void filloplData(OplTlMst oplTlMst) {

    // ✅ text fields: avoid NPE + remove <* *>
    oplTlMst.setOplmAftercondition(cleanText(oplTlMst.getOplmAftercondition()));
    oplTlMst.setOplmPresentcondition(cleanText(oplTlMst.getOplmPresentcondition()));
    oplTlMst.setOplmLesson(cleanText(oplTlMst.getOplmLesson()));
    oplTlMst.setOplmTheme(cleanText(oplTlMst.getOplmTheme()));
    oplTlMst.setOplmClassdescription(cleanText(oplTlMst.getOplmClassdescription()));
    oplTlMst.setOplmDepartmentmanager(cleanText(oplTlMst.getOplmDepartmentmanager()));
    oplTlMst.setOplmSectionmanager(cleanText(oplTlMst.getOplmSectionmanager()));
    oplTlMst.setOplmGroupleader(cleanText(oplTlMst.getOplmGroupleader()));

    // ✅ date fields: API gives yyyy-MM-dd, UI expects dd-MMM-yyyy
    oplTlMst.setOplmDate(normalizeDate(oplTlMst.getOplmDate()));
    oplTlMst.setOplmPrepareddate(normalizeDate(oplTlMst.getOplmPrepareddate()));
    oplTlMst.setOplmApproveddate(normalizeDate(oplTlMst.getOplmApproveddate()));
}

private String cleanText(String s) {
    if (s == null) return "";
    s = s.trim();

    // handle common placeholders coming from API/DB
    if (s.isEmpty() || "{}".equals(s) || "-".equals(s) || "null".equalsIgnoreCase(s)) return "";

    return s.replace("<*", "").replace("*>", "");
}

private String normalizeDate(String s) {
    s = cleanText(s);
    if (s.isEmpty()) return "";

    // If already in dd-MMM-yyyy (like 30-Dec-2025), keep first 11 chars
    if (s.length() >= 11 && s.charAt(2) == '-' && s.charAt(6) == '-') {
        return s.substring(0, 11);
    }

    // If ISO yyyy-MM-dd (like 2025-12-30), convert to dd-MMM-yyyy
    if (s.length() >= 10 && s.charAt(4) == '-' && s.charAt(7) == '-') {
        String iso = s.substring(0, 10);
        try {
            SimpleDateFormat in = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat out = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date d = in.parse(iso);
            return out.format(d);
        } catch (ParseException e) {
            return iso; // fallback: show ISO if parsing fails
        }
    }

    // fallback: return as-is
    return s;
}
    // --- api call -- vignesh -- //
     
    
	private List<OplTlPillarlink> populatePillarLinkList(List<String[]> pillarLinks)
    {
    	 List<OplTlPillarlink> pillarLinkList = new ArrayList<OplTlPillarlink>();
    	 CommonMessage.debugMsg("Inside populatePillarLinkList"+pillarLinks.size());
    	 for( String [] row : pillarLinks)
 		 {
    		 OplTlPillarlink oplTlPillarlink = new OplTlPillarlink();
    		 oplTlPillarlink.setDbMode(row[6]);//1
    		 oplTlPillarlink.setOpplOplid(row[5]);
    		 oplTlPillarlink.setOpplCreatedon(row[2]);
    		 oplTlPillarlink.setOpplTpmpillarid(row[0]);//0
    		 oplTlPillarlink.setOpplOplcategoryid(row[7]);//2
    		 pillarLinkList.add(oplTlPillarlink);
 		 }
    	 CommonMessage.debugMsg(pillarLinkList);
    	 return pillarLinkList;
    }
    
    private JSONObject convertToPillarTblObject(List<Object> PillarNameList)
    {
 	   JSONObject opltableDataObject = new JSONObject();
 		
  		opltableDataObject.put("page", 1); //current page
  		opltableDataObject.put("total",10); // total page
  		opltableDataObject.put("records", (PillarNameList.size())); //total records
 		
 		JSONArray rowArr = new JSONArray(); 
     
  		for(Object tpmModel: PillarNameList)
  		{
  			
  			OplTlCategorymst oplTlCategorymst = (OplTlCategorymst)tpmModel;
 	  		JSONObject rowObj =new JSONObject();
 		    rowObj.put("id",+1);
 	        
 	        JSONArray cell = new  JSONArray();
 	        cell.put(oplTlCategorymst.getOplcKeyid());
 	        cell.put(oplTlCategorymst.getOplcName());
 	        cell.put(oplTlCategorymst.getOplcCode());
 	        rowObj.put("cell",cell);
 	        
 	        rowArr.put(rowObj);
 		}
  		
  			opltableDataObject.put("rows", rowArr);
 	      
  			CommonMessage.debugMsg("opltableDataObject="+opltableDataObject);
 	        return opltableDataObject;
   	}
    
    private void saveOpl(HttpServletRequest request, HttpServletResponse response,OplFormBean oplFormBean  ) throws BusinessApplicationExceptions,IOException
    {
    	System.out.print("Inside Save");
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String oplstatus=request.getParameter("hdnOplmStatus");
    	String oplapprov=request.getParameter("hdnOplmAprovLevel");
    	String empillar=request.getParameter("hdnEmppillar");
    	String formmode=request.getParameter("Oplformmode");
    	CommonMessage.debugMsg(" Inside Save Action Mode :: "+oplstatus);
    	CommonMessage.debugMsg(" Inside Save Action Mode :: "+oplapprov);
		/* remove ths line*/
    //	String ElementId=request.getParameter("hdnelementId");
	
    	String status=request.getParameter("status");
    	
    	CommonMessage.debugMsg("ElementId :::::::::: "+status);
    	//OplTlPillarlink newOplPillarlink = new OplTlPillarlink(); 
    	
    	if( httpSession != null && user != null)
    	{	
    		OplTlMst newOplTlMst = new OplTlMst();
    		newOplTlMst =(OplTlMst)UIUtils.setBeanProperties((Object)newOplTlMst,request);
    		oplFormBean =(OplFormBean) UIUtils.setBeanProperties((Object)oplFormBean,request);
    		
    		newOplTlMst.setOplmCreatedby(user.getUsrm_ccno());
    		/*remove ths line*/
			
			//newOplTlMst.setOplmElementid(ElementId);
    		UIUtils.displayRequestParamsValue(request);
			CommonMessage.debugMsg("newOplTlMst:flid "+newOplTlMst.getOplmFlid());
    		List<OplTlPillarlink> pillarLnkList =null;
    		OplTlPillarlink oplTlPillarlink = new OplTlPillarlink();
    		String pillarlinkStr =  request.getParameter("OplPillarLink");
    		String openactnpln = request.getParameter("openactnpln");  
    		String filemanager = request.getParameter("filemanager");
    		String gridData =request.getParameter("gridData");
    		String Mode =request.getParameter("Mode");
    		String frmMode = request.getParameter("mod");
    		String nextlevel=request.getParameter("Approvallevel");
    		
    		CommonMessage.debugMsg(" Inside Save Action Mode :: "+frmMode);
        	CommonMessage.debugMsg(" Inside Save Action Mode :: "+Mode);
        	oplFormBean.setFormActionMode(frmMode);
        	oplFormBean.setFormMode(Mode);
                      
        	
        	
        	if(UIUtils.isValidKeyId(status)){
				if("C".equals(status))
					newOplTlMst.setOplmStatus("C");
				else if("A".equals(status))
					newOplTlMst.setOplmStatus("A");
				else if("E".equals(status))
					newOplTlMst.setOplmStatus("E");
				else if("R".equals(status))
					newOplTlMst.setOplmStatus("R");
				else
					newOplTlMst.setOplmStatus("P");
			 }else if("C".equals(status)&&("OPLMATRIX".equals(Mode)||"APPROVAL".equals(Mode)))
				newOplTlMst.setOplmStatus("C");
        	  else
				 newOplTlMst.setOplmStatus("-");
        
        	
        	
        	if(UIUtils.isValidKeyId(nextlevel)){ 
        		newOplTlMst.setOplmAprovLevel(nextlevel);
			}else {
				newOplTlMst.setOplmAprovLevel("-");
		    }
        	
        	JSONArray jsonOplStudent = null;
			
			List<OplTlStudent> newOplTlStudent = null;
			if(UIUtils.isValidKeyId(gridData)){
				
					jsonOplStudent = JSONArray.fromString(gridData);
					CommonMessage.debugMsg("list:::"+jsonOplStudent);
					newOplTlStudent=(List<OplTlStudent>)UIUtils.convertJSONArrToList(new OplTlStudent(), jsonOplStudent);
					CommonMessage.debugMsg("newOplTlStudent"+newOplTlStudent.size());
                    
                    //newOplTlStudent
			}if( newOplTlStudent != null)
				newOplTlMst.setStudentLink(newOplTlStudent);
			
    		CommonMessage.debugMsg(" Inside opl save action::openactnpln "+gridData);
    		JSONArray pillarLinkJSON =null;
    		if( pillarlinkStr != null && ! pillarlinkStr.isEmpty())
    		{
    			pillarLinkJSON = JSONArray.fromString(pillarlinkStr);
    			pillarLnkList=(List<OplTlPillarlink>)UIUtils.convertJSONArrToList(oplTlPillarlink, pillarLinkJSON);
    		}
    		
			if( pillarLnkList != null)
				newOplTlMst.setPillarLink(pillarLnkList);
			
			
			
			/** For Saving Image **/
			
			String imgOplPresentImgFilename= request.getParameter("imgOplPresentImgFilename");
			String imgOplAfterImgFilename =request.getParameter("imgOplAfterImgFilename");
			CommonMessage.debugMsg("imgOplAfterImgFilename="+imgOplAfterImgFilename);
			CommonMessage.debugMsg("imgOplPresentImgFilename="+imgOplPresentImgFilename);
			String imagePath = UIUtils.getImagePath(request);
			
			List <GenTlAllmoduleimgfile> imgfileList =new ArrayList<GenTlAllmoduleimgfile>();
			
			if( imgOplPresentImgFilename != null){
				GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgOplPresentImgFilename);
				genTlAllmoduleimgfile.setImflImagetype("PRE");
				genTlAllmoduleimgfile.setImflRefdoctype("OPL");
				imgfileList.add(genTlAllmoduleimgfile);
				CommonMessage.debugMsg(" genTlAllmoduleimgfile.setToimBlobimage( " + genTlAllmoduleimgfile.getImflBlobimage());
			}
			
			if( imgOplAfterImgFilename != null){
				GenTlAllmoduleimgfile genTlAllmoduleimgfile = new GenTlAllmoduleimgfile();
				genTlAllmoduleimgfile.setImflBlobimage(imagePath );
				genTlAllmoduleimgfile.setImflFilename(imgOplAfterImgFilename);
				genTlAllmoduleimgfile.setImflImagetype("AFT");
				genTlAllmoduleimgfile.setImflRefdoctype("OPL");
				imgfileList.add(genTlAllmoduleimgfile);
			}
			
			newOplTlMst.setAllmoduleimgfile(imgfileList) ;			
		
			/**---------- **/
    		
    		OplTlMst existOplTlMst = (OplTlMst)httpSession.getAttribute("oplTlMst"+newOplTlMst.getOplmKeyid());
    		//String frmMode=(String)httpSession.getAttribute("OplServletFormActionMode");
    		CommonMessage.debugMsg(" Inside Save OPl "+frmMode);
			try
			{
				//CommonMessage.debugMsg("frmMode in save" +frmMode);
				String docId=(String)httpSession.getAttribute("docOPLId");
				String yyId=(String)httpSession.getAttribute("yyIdOPL");
				//if(UIUtils.isValidKeyId(frmMode))
				   // oplFormBean.setFormMode(frmMode);
				if(UIUtils.isValidKeyId(yyId))
					oplFormBean.setYyId(yyId);	
				
				if(UIUtils.isValidKeyId(docId))
					oplFormBean.setDocId(docId);	
				
				 String saveMsg = null ;
				//if((frmMode==null)&&!(frmMode.equals("View")))
				//{
				    
					if( newOplTlMst.getOplmKeyid() == null )
					{	
						CommonMessage.debugMsg(" Inside :: If create 1");
						existOplTlMst =	oplService.create(newOplTlMst,existOplTlMst,oplFormBean);
						saveMsg = "success-save";
						CommonMessage.debugMsg(" Inside :: ");
					}	
					else
					{   CommonMessage.debugMsg(" Inside :: Else update 1");
					  if(formmode.equals("Approval"))
					  {
					   newOplTlMst.setOplmStatus(oplstatus);
					   newOplTlMst.setOplmAprovLevel(oplapprov);
					  }
					  else{
						  if(oplstatus.equals("R"))
							{
							  newOplTlMst.setOplmStatus("P");
							  newOplTlMst.setOplmAprovLevel("-");
						     }
					  }
						existOplTlMst = oplService.update(newOplTlMst,existOplTlMst,oplFormBean);
						saveMsg = "success-update";
					}
					
				//}
				httpSession.setAttribute("oplTlMst"+existOplTlMst.getOplmKeyid(), existOplTlMst);
				
				CommonMessage.debugMsg("frmMode ="+frmMode);	
				CommonMessage.debugMsg("existOplTlMst.getOplmKeyid()-"+existOplTlMst.getOplmKeyid());
				
				
				//Boolean clrVal=true;
				String successIden;/*= "success-save";
				
				
				if( newOplTlMst.getOplmKeyid() == null )
				   successIden= "success-save";
				else
					successIden= "success-update";*/
				
				
				
				/*if(frmMode.equals("modify")||frmMode.equals(" ")||frmMode==null)
					successIden= "success-update";
				else if(frmMode.equals("View"))
					{successIden= "vw-save";clrVal=false;}*/
				
				
				/*if(UIUtils.isValidKeyId(frmMode)){
					if(frmMode.equals("modify"))
						successIden= "success-update";
					else if(frmMode.equals("View")){
						successIden= "vw-save";
						clrVal=false;
					}
				}else
					successIden= "success-update";*/
					
				
				JSONObject mode = new JSONObject();
				JSONObject forwardData = new JSONObject();
				forwardData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);
				mode.put("frmMode",frmMode);
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",saveMsg) + 
						" - OPL. No. " + existOplTlMst.getOplmKeyid()) ;
				
				
				
				//String id=existOplTlMst.getOplmKeyid();
				if(UIUtils.isValidKeyId(filemanager)){
					successData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
					successData.put("flid", newOplTlMst.getOplmFlid());
					CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
					successData.put("filemanager", true);
					successData.put("formClear",false);
				}else
				{
					successData.put("filemanager", false);
					successData.put("formClear",true);
				}
				
				if(UIUtils.isValidKeyId(openactnpln)){
					successData.put("oplKeyId",existOplTlMst.getOplmKeyid() );
					successData.put("flid", newOplTlMst.getOplmFlid());
					CommonMessage.debugMsg(" Inside Flid servlet "+newOplTlMst.getOplmFlid());
					successData.put("openactnpln", true);
					successData.put("formClear",false);
				}else
				{
					successData.put("openactnpln", false);
					successData.put("formClear",false);
				}
				
				String modeReturn =(String)httpSession.getAttribute("bdmmode");
				if(UIUtils.isValidKeyId(modeReturn))
					successData.put("successData", modeReturn);
				JSONObject returnData = new JSONObject();
				returnData.put("formMode",oplFormBean.getFormActionMode());
				returnData.put("mode", mode);
				//returnData.put("formClear",true);
				returnData.put("successData",successData);
				CommonMessage.debugMsg("Mode :"+returnData.toString());
				out.print(returnData.toString());
				out.close();
				//if((frmMode!=null)&&!(frmMode.equals("View")))
				
				String keyid = request.getParameter("keyid");
				String imagetypepre = request.getParameter("IMAGETYPEPRE"); 
				String imagetypeaft = request.getParameter("IMAGETYPEAFT");
				
				CommonMessage.debugMsg(" keyid :: "+keyid+" imagetypepre :: "+imagetypepre);
			    CommonMessage.debugMsg("imagetypeaft :: "+imagetypeaft);
			    
			    
			    existOplTlMst.setAllmoduleimgfile(imgfileList) ;
				if( existOplTlMst.getOplmKeyid() == null )
					oplService.saveOplImg(existOplTlMst,keyid,imagetypepre,imagetypeaft);	
				else 
				    oplService.saveOplImg(existOplTlMst,keyid,imagetypepre,imagetypeaft);
				
				
				
			}
			/*catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("BusinessApplicationExceptions"+e.toString());
				JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"OplCreationExceptions");
				//errMessage.put("tpmException","OPL is approved, Can not be deleted.");
				errMessage.put("displyMsg", false);	
				//CommonMessage.debugMsg(errMessage.toString());
				out.print(errMessage.toString());
			}*/
			
			catch(ValidationExceptions e)
			{
				e.printStackTrace();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "OplCreationExceptions");
				errMessage.put("formMode",oplFormBean.getFormActionMode());
				out.print(errMessage.toString());
			}
			catch(BusinessApplicationExceptions e)
	          {
	    	    e.printStackTrace();
	    	    JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "OplCreationExceptions");
				errMessage.put("tpmException","This Record is already exist");
				errMessage.put("displyMsg", false);			
				out.print(errMessage.toString());
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				CommonMessage.debugMsg("ERR"+e.getStackTrace());
				CommonMessage.debugMsg("ERR"+e.getLocalizedMessage());
				CommonMessage.debugMsg("ERR"+e.getCause());
				e.printStackTrace();
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
			
				out.print(err.toString());
			}
    	}	
    }
    
    private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
		HttpSession httpSession = request.getSession(false);
		
		
		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
		if( commonFilter != null && ! createNew ){
			FilterValues.setPaginationParams(request,commonFilter);
		}	
		else{
			commonFilter =  new CommonFilter();
			
			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
			commonFilter = 	FilterValues.getOPLandKaizen(request, commonFilter);
			commonFilter.setViewClick('Y');
			httpSession.removeAttribute(beanIdentifier);
			httpSession.setAttribute(beanIdentifier, commonFilter);
		}
		CommonMessage.debugMsg("commonfileter.getTotla=="+commonFilter.getTotalRecordCnt());
		return commonFilter;
	}
    
   private void deleteOpl(HttpServletRequest request, HttpServletResponse response,OplFormBean oplFormBean ) throws IOException
   {
	    HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
   	    AdmTlUsermst user = UIUtils.getLoginUser(request);
   	
	   	if( httpSession != null && user != null)
	   	{	
	   		OplTlMst newOplTlMst = new OplTlMst();
	   		newOplTlMst =(OplTlMst)UIUtils.setBeanProperties((Object)newOplTlMst,request);
	   		oplFormBean =(OplFormBean) UIUtils.setBeanProperties((Object)oplFormBean,request);
	   		
	   		OplTlMst existOplTlMst = (OplTlMst)httpSession.getAttribute("oplTlMst"+newOplTlMst.getOplmKeyid()); 
	   		
	   		newOplTlMst.setOplmCreatedby(user.getUsrm_ccno());
	  		String frmMode=(String)httpSession.getAttribute("OplServletFormActionMode");
			try
			{		
				String saveMsg = null ;
				if( newOplTlMst.getOplmKeyid() != null )
				{
					//if(frmMode!=null&&!(frmMode.equals("View")))
					//{	
						existOplTlMst =	oplService.delete(newOplTlMst); 
						saveMsg = "Data Deleted Successfully";
						httpSession.removeAttribute("oplTlMst"+existOplTlMst.getOplmKeyid());
					//}
					
				
					Boolean clrVal=true;
					String delMsg="success-delete";
					if(frmMode.equals("View"))
						{delMsg="vw-delete";clrVal=false;}
					
					JSONObject successData = new JSONObject();
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",delMsg));
					successData.put("keyid", newOplTlMst.getOplmKeyid());
					successData.put("frmMode",frmMode);
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
				out.print(errMessage.toString());
			}
			catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
	   	}	
    }
   
   
}
   
   
   
   
   
   
