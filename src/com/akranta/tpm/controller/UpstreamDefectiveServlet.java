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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.GenTlControlandresponseplan;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
//import com.akranta.tpm.service.ControlResponseService;
import com.akranta.tpm.service.UpstreamDefectiveServices;
import com.akranta.tpm.service.impl.UpstreamDefectiveServicesImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/**
 * Servlet implementation class UpstreamDefective
 */

public class UpstreamDefectiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UpstreamDefectiveServices upstreamDefectiveServices;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpstreamDefectiveServlet()
    {
        super();
    
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		try {
			process(request, response);
		} catch (Exception e){
			
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			process(request, response);
		} catch (Exception e){
			
			e.printStackTrace();
		}
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CommonMessage.debugMsg("gsags");
		
		HttpSession httpSession = request.getSession(false);
		upstreamDefectiveServices= (UpstreamDefectiveServicesImpl) UIUtils.getServiceObject(request, "UpstreamDefectiveServicesImpl");
		upstreamDefectiveServices.UpstreamDefectiveServicesImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );

		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action1 " + action);
		
		// String dispatchUrl = null;
		// HttpSession httpSession = request.getSession(false);
		if(action.equals("UpstreamDefect_input.upd"))
		{
			String mainForm=request.getParameter("mainForm");
			
			if("true".equals(mainForm)){
				request.setAttribute("mainForm", "mainForm");
				
				
			   }
			RequestDispatcher rd = request.getRequestDispatcher("/pages/UpstremDefectiveMainGrid.jsp"); 
			rd.forward(request, response); 
			CommonMessage.debugMsg(" response gggggggggggg" ); 
		}
		else if(action.equals("UpstreamDefect_getCol.upd"))
		{
			/*PrintWriter out = response.getWriter();
			//populateCommonFilter(request,"MinOfMeetingCommonFilter",true);
			CommonMessage.debugMsg(" after populate col ");			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.UpstreamDefective","UpstreamDefectmain"));
			CommonMessage.debugMsg(" test datefmdfm ");*/
			
			List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
			    CommonFilter commonFilter = populateCommonFilter(request,"UpstreamDefectCommonFilter",true);
			    String keyid=request.getParameter("keyid");
			    commonFilter.setIsGetCol("Y");
			    ctrlResGrid = upstreamDefectiveServices.getUpstreamGrid(commonFilter,keyid);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = ctrlResGrid.get(1);
				String [] colHeaderHead = ctrlResGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "80%%");
				
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}			

		}
		else if(action.equals("UpstreamDefect_getData.upd"))
		{
			CommonMessage.debugMsg(" get data.....");
			
			try
			{   

				UIUtils.displayRequestParamsValue(request);	
				String keyid=request.getParameter("keyid");
				CommonFilter commonFilter = populateCommonFilter(request,"UpstreamDefectCommonFilter",false);
				commonFilter.setIsGetCol("N");
				List<String[]> UpstreamGrid  =upstreamDefectiveServices.getUpstreamGrid(commonFilter,keyid);
  			 	CommonMessage.debugMsg("equipmentQueryList " + UpstreamGrid.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(UpstreamGrid,request,2,0); 
  			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
//		else if(action.equals("UpstreamDefectForm_input.upd"))
//		{
//			
//			String UpSGrid = request.getParameter("grid");
//			String Keyid = request.getParameter("keyId");
//			String Date = request.getParameter("Date");
//			String New = request.getParameter("new");
//			String FnlnId = request.getParameter("flId");
//			
//			CommonMessage.debugMsg(" New :: "+New);
//			CommonMessage.debugMsg(" FnlnId :: "+FnlnId);
//			
//			CommonMessage.debugMsg(" Keyid :: "+Keyid);
//			
//			GenTlUpstreamdefect newGenTlUpstreamdefect = new GenTlUpstreamdefect();
//			
//			GenTlUpstreamdefectMst newGenTlUpstreamdefectMst = new GenTlUpstreamdefectMst();
//			
//			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
//			
//			AdmTlUsermst user = UIUtils.getLoginUser(request);
//
//			if("true".equals(UpSGrid))
//			{
//				newGenTlUpstreamdefectMst= upstreamDefectiveServices.getFillControlDatas(FnlnId,Date,Keyid);
//				
//				CommonMessage.debugMsg(" Inside :: 1 "+newGenTlUpstreamdefect.getUpsdFlid());
//				CommonMessage.debugMsg(" Inside :: 2 "+newGenTlUpstreamdefect.getUpsdDate());
//				
//			
//			}
//				
//				newGenTlUpstreamdefectMst.setUpsmFlid(FnlnId);
//				newGenTlUpstreamdefectMst.setUpsmDate(Date);
//				newGenTlUpstreamdefectMst.setUpsmKeyid(Keyid);
//		
//			
//			request.setAttribute("newGenTlUpstreamdefectMst", newGenTlUpstreamdefectMst);
//			request.setAttribute("New", New);
//			request.setAttribute("FnlnId", FnlnId);
//			
//			RequestDispatcher rd = request.getRequestDispatcher("/pages/UpstreamDefective.jsp"); 
//			rd.forward(request, response); 
//			CommonMessage.debugMsg(" response " + response); 
//			
//		}
		
		else if(action.equals("UpstreamDefectForm_input.upd"))
		{
			try {
			String UpSGrid = request.getParameter("grid");
			String Keyid = request.getParameter("keyId");
			String Date = request.getParameter("Date");
			String New = request.getParameter("new");
			String FnlnId = request.getParameter("flId");
			
			CommonMessage.debugMsg(" New :: "+New);
			CommonMessage.debugMsg(" FnlnId :: "+FnlnId);
			
			CommonMessage.debugMsg(" Keyid :: "+Keyid);
			
			// Get login user details
	        AdmTlUsermst user = UIUtils.getLoginUser(request);
	        String tmpFromRow = user.getUsrm_username();
	        String loginflid = CommonFunctions.getLoginFlid(request);
	        String keyId = CommonFunctions.getLoginLevel(request);
	        String format = (String)httpSession.getAttribute("loginElementid");
	        String empId = user.getUsrm_ccno();
	        
	        CommonMessage.debugMsg("=== UPSTREAM DEFECT ACCESS DEBUG START ===");
	        CommonMessage.debugMsg("loginflid: " + loginflid);
	        CommonMessage.debugMsg("keyId: " + keyId);
	        CommonMessage.debugMsg("format: " + format);
	        CommonMessage.debugMsg("empId: " + empId);
	        
	        // Get user role details using the service
	        List<String[]> getUserLoginDtl = this.upstreamDefectiveServices.getElementId(
	            loginflid, keyId, format, empId);
	        
	        CommonMessage.debugMsg("Query Result Size: " + (getUserLoginDtl != null ? getUserLoginDtl.size() : "NULL"));
	        
	        if(getUserLoginDtl != null && getUserLoginDtl.size() > 0) {
	            String elementid = ((String[])getUserLoginDtl.get(0))[0];
	            String fnln = ((String[])getUserLoginDtl.get(0))[1];
	            String level = ((String[])getUserLoginDtl.get(0))[2];
	            String rolename = ((String[])getUserLoginDtl.get(0))[3];
	            String rolekeyid = ((String[])getUserLoginDtl.get(0))[4];
	            
	            CommonMessage.debugMsg("Role Name: '" + rolename + "'");
	            CommonMessage.debugMsg("Role KeyId: '" + rolekeyid + "'");
	            CommonMessage.debugMsg("Role Name Length: " + rolename.length());
	            CommonMessage.debugMsg("Expected: 'QM PILLAR MEMBER' Length: 16");
	            CommonMessage.debugMsg("Match: " + "QM PILLAR MEMBER".equals(rolename.trim()));
	            CommonMessage.debugMsg("=== UPSTREAM DEFECT ACCESS DEBUG END ===");
	            
	            // Set role attributes - Always set these so JavaScript can check
	            request.setAttribute("rolekeyid", rolekeyid);
	            request.setAttribute("rolename", rolename);
	            
	        } else {
	            CommonMessage.debugMsg("ERROR: No role data found for upstream defect!");
	            // Set empty values so page can load and JavaScript can show error
	            request.setAttribute("rolekeyid", "");
	            request.setAttribute("rolename", "");
	        }
			
			GenTlUpstreamdefect newGenTlUpstreamdefect = new GenTlUpstreamdefect();
			
			GenTlUpstreamdefectMst newGenTlUpstreamdefectMst = new GenTlUpstreamdefectMst();
			
			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
			

			if("true".equals(UpSGrid))
			{
				newGenTlUpstreamdefectMst= upstreamDefectiveServices.getFillControlDatas(FnlnId,Date,Keyid);
				
				CommonMessage.debugMsg(" Inside :: 1 "+newGenTlUpstreamdefect.getUpsdFlid());
				CommonMessage.debugMsg(" Inside :: 2 "+newGenTlUpstreamdefect.getUpsdDate());
				
			
			}
				
				newGenTlUpstreamdefectMst.setUpsmFlid(FnlnId);
				newGenTlUpstreamdefectMst.setUpsmDate(Date);
				newGenTlUpstreamdefectMst.setUpsmKeyid(Keyid);
		
			
			request.setAttribute("newGenTlUpstreamdefectMst", newGenTlUpstreamdefectMst);
			request.setAttribute("New", New);
			request.setAttribute("FnlnId", FnlnId);
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/UpstreamDefective.jsp"); 
			rd.forward(request, response); 
			CommonMessage.debugMsg(" response " + response); 
			
		} catch (Exception e) {
	        CommonMessage.debugMsg("ERROR in UpstreamDefectForm_input: " + e.getMessage());
	        e.printStackTrace();
	    }
			
		}
		
		else if( action.equals("Upstream_DefectForm.upd"))
		{
			
				CommonMessage.debugMsg(" Inside SErvlet Action 11 :: ");
				ComboFilter comboFilter = new ComboFilter();
				CommonFilter  commonFilter = new CommonFilter();
				comboFilter=UIUtils.fillComboFilter(request);
				CommonMessage.debugMsg(" Inside CommonFunctions 11 :: ");
				String flid = request.getParameter("flid");
			    if (UIUtils.isValidKeyId(flid))
			        commonFilter.setFlid(flid);

			    // read directly from request
			    String sectionId  = request.getParameter("sectionId");
			    String defectMode = request.getParameter("defectMode");

			    // piggyback into condSql - no model setter needed
			    comboFilter.setCondSql("##" + defectMode + "##" + sectionId + "##");
			    
				List<ComboBox>  UpstreamDefect = upstreamDefectiveServices.getFillcombobox(commonFilter,comboFilter);
				UIUtils.writeComboBox(response, UpstreamDefect ,comboFilter);
				
		
			
		}
		else if(action.equals("UpstreamDefectForm_getCol.upd"))
		{
			/*PrintWriter out = response.getWriter();
			//populateCommonFilter(request,"MinOfMeetingCommonFilter",true);
			CommonMessage.debugMsg(" after populate col ");			
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.UpstreamDefective","UpstreamDefect"));
			CommonMessage.debugMsg(" test datefmdfm ");*/
			
			
			List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
			    CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",true);
			    
			    String keyId = request.getParameter("keyId");
			    
			    ctrlResGrid = upstreamDefectiveServices.getUpstreamFormGrid(commonFilter,keyId);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(false);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = ctrlResGrid.get(1);
				String [] colHeaderHead = ctrlResGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "65%%");
				colmodel.set("tableHeight", "60%%");
				
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}	
			
		}
		else if(action.equals("UpstreamDefectForm_getData.upd"))
		{
			CommonMessage.debugMsg(" get data.....");
			try
			{   

				UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"MinOfMeetingCommonFilter",false);
				
				String date = request.getParameter("Date");
			    String fnlnId = request.getParameter("flId");
			    String keyId = request.getParameter("keyId");
				
			    String upStreamDate = "";
			    //6-feb
			    if(UIUtils.isValidKeyId(date)){
			    	upStreamDate  = date;// date.substring(0, 11);
			    }
				CommonMessage.debugMsg(" Date :: "+date+" FnlnId :: "+fnlnId+" UpstreamDate :: "+upStreamDate);
				CommonMessage.debugMsg(" keyId :: "+keyId);
			    commonFilter.setFlid(fnlnId);
				commonFilter.setDteend(upStreamDate);
				commonFilter.setKey(keyId);
				List<String[]> UpstreamGrid  =upstreamDefectiveServices.getUpstreamFormGrid(commonFilter,keyId);
  			 	CommonMessage.debugMsg("equipmentQueryList " + UpstreamGrid.size());
				PrintWriter out = response.getWriter();
  			 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(UpstreamGrid,request,2,0); 
  			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		else if(action.equals("UpstreamDefect_getExcel.upd"))
		{
			CommonMessage.debugMsg(" get data.....");
			try
			{   

				
				httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"UpstreamDefectCommonFilter",false);
				JSONObject colmodel = UIUtils.getXlColModel(request,response);
				colmodel.put("title","Upstream Defect");
	            String format = ExcelUtils.getFormat(request);
				
				Workbook wb = upstreamDefectiveServices.getUpstreamDefectExcel(colmodel,format,commonFilter);
				ExcelUtils.writeToResponse(response, wb, "UpstreamDefectReport", format);
				

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		
		
		else if (action.equals("UpstreamDefectForm_recall.upd"))
		{
			PrintWriter out = response.getWriter();
			String keyid = request.getParameter("KEYID");
			CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
			List<String []> condReclData  = upstreamDefectiveServices.FillControlData( keyid);
			out.print( JSONArray.fromCollection(condReclData));
		}

		
		
		else if (action.equals("UpstreamDefectForm_save.upd"))
		{
			try {
				
				UpstreamDefect upstreamDefect = new UpstreamDefect();
				saveUpStreamDefect(request, response, upstreamDefect);
				
   			} catch (Exception e) {

			}
		}
		
		else if (action.equals("UpstreamDefectForm_delete.upd"))
		{
			try {
				
				UpstreamDefect upstreamDefect = new UpstreamDefect();
				deleteUpStreamDefect(request, response, upstreamDefect);
				
   			} catch (Exception e) {

			}
		}else if(action.equals("UpstreamDefectForm_remove.upd"))
		{
			try {
				
				UpstreamDefect upstreamDefect = new UpstreamDefect();
				deleteUpStreamDefectDetail(request, response, upstreamDefect);
				
   			} catch (Exception e) {

			}
		}

}

	private void deleteUpStreamDefectDetail(HttpServletRequest request,
			HttpServletResponse response, UpstreamDefect upstreamDefect) throws IOException{
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		GenTlUpstreamdefectDet newGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet(); 
    		
    		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet(); 
    		
    		newGenTlUpstreamdefectDet=(GenTlUpstreamdefectDet)UIUtils.setBeanProperties((Object)newGenTlUpstreamdefectDet,request);
		
			CommonMessage.debugMsg("newGenTlUpstreamdefect.getUpsdKeyid()  "+newGenTlUpstreamdefectDet.getUpsdKeyid());
			
			String Keyid=request.getParameter("dtlKeyid");
			
			CommonMessage.debugMsg(" Keyid :: "+Keyid);
			
			newGenTlUpstreamdefectDet.setUpsdKeyid(Keyid);
			
			CommonMessage.debugMsg(" CommonFunctions "+Keyid);
			
			if(UIUtils.isValidKeyId(newGenTlUpstreamdefectDet.getUpsdKeyid())){
				
				newGenTlUpstreamdefectDet=upstreamDefectiveServices.deleteNewUpstreamDefectDetails(newGenTlUpstreamdefectDet);
				httpSession.removeAttribute("GenTlUpstreamdefect"+newGenTlUpstreamdefectDet.getUpsdKeyid());
				
			}
		
			JSONObject successData=new JSONObject();
    		JSONObject Upstreamdatadelete=new JSONObject();
    		String savemsg;
    	   if( newGenTlUpstreamdefectDet.getUpsdKeyid()==null )
			{
				savemsg=" Data Deleted succesfully ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    		successData.put("msg", savemsg);
    		successData.put("formClear",true);
    		Upstreamdatadelete.put("successData", successData);
    		out.print(Upstreamdatadelete.toString());
    		
    	}
		}
		
		catch(Exception e)
		{
			
		}
		
	}

	private void deleteUpStreamDefect(HttpServletRequest request,HttpServletResponse response, UpstreamDefect upstreamDefect) throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		GenTlUpstreamdefectMst  existGenTlUpstreamdefectMst  = new GenTlUpstreamdefectMst();
    		
    		GenTlUpstreamdefectMst  newGenTlUpstreamdefectMst  = new GenTlUpstreamdefectMst();
    		
    		newGenTlUpstreamdefectMst=(GenTlUpstreamdefectMst)UIUtils.setBeanProperties((Object)newGenTlUpstreamdefectMst,request);
		
			CommonMessage.debugMsg("newGenTlUpstreamdefect.getUpsdKeyid()  "+newGenTlUpstreamdefectMst.getUpsmKeyid());
			
			String Keyid=request.getParameter("keyid");
			
			//newGenTlUpstreamdefectMst.setUpsmKeyid(Keyid);
			
		//	CommonMessage.debugMsg(" CommonFunctions "+Keyid);
			
			if(UIUtils.isValidKeyId(newGenTlUpstreamdefectMst.getUpsmKeyid())){
				
				newGenTlUpstreamdefectMst=upstreamDefectiveServices.deleteNewUpstreamDefect(newGenTlUpstreamdefectMst);
				httpSession.removeAttribute("GenTlUpstreamdefect"+newGenTlUpstreamdefectMst.getUpsmKeyid());
				
			}
		
			/*JSONObject successData=new JSONObject();
    		JSONObject Upstreamdatadelete=new JSONObject();
    		String savemsg;
    	   if( newGenTlUpstreamdefectMst.getUpsmKeyid()==null )
			{
				savemsg=" Data Deleted succesfully ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    		successData.put("msg", savemsg);
    		successData.put("formClear",true);
    		Upstreamdatadelete.put("successData", successData);
    		out.print(Upstreamdatadelete.toString());*/
			
			
			JSONObject successData = new JSONObject();
			CommonMessage.debugMsg("SuccessData");
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			//successData.put("keyId", newGenTlUpstreamdefectMst.getUpsmKeyid());
			JSONObject returnData = new JSONObject();						
			returnData.put("successData", successData);
			returnData.put("formClear", true);	
			CommonMessage.debugMsg("successData"+successData);
			
			out.print(returnData.toString());
    		
    	}
	}
		
		catch(Exception e)
		{
			
		}
		
	}

	private CommonFilter populateCommonFilter(HttpServletRequest request,String beanIdentifier, boolean createNew) {
			
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
		  		CommonMessage.debugMsg("test to be conducted................"+commonFilter.getFromRow()+"----"+commonFilter.getToRow());
		  		return commonFilter;
		  	}

	private void saveUpStreamDefect(HttpServletRequest request,
			HttpServletResponse response, UpstreamDefect upstreamDefect)throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String type=request.getParameter("type");
    	
    	  try
          {
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		GenTlUpstreamdefectMst  existGenTlUpstreamdefectMst  = new GenTlUpstreamdefectMst();
	    		
	    		GenTlUpstreamdefectMst  newGenTlUpstreamdefectMst  = new GenTlUpstreamdefectMst();
	    		
	    		newGenTlUpstreamdefectMst=(GenTlUpstreamdefectMst)UIUtils.setBeanProperties((Object)newGenTlUpstreamdefectMst,request);
	    		
	    		newGenTlUpstreamdefectMst.setUpsmCreatedby(user.getUsrm_ccno());
	    		//newGenTlUpstreamdefect =(GenTlVisitors)UIUtils.setBeanProperties((Object)newGenTlVisitors,request);
	    		
	    		GenTlUpstreamdefectDet newGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet(); 
	    		
	    		newGenTlUpstreamdefectDet=(GenTlUpstreamdefectDet)UIUtils.setBeanProperties((Object)newGenTlUpstreamdefectDet,request);
	    		
	    		newGenTlUpstreamdefectDet.setUpsdCreatedby(user.getUsrm_ccno());
	    		
	    		String upsactnpln = request.getParameter("upsactnpln");
	    		String filemanger = request.getParameter("filemanger");

	    		
	    		CommonMessage.debugMsg(" Inside :: "+newGenTlUpstreamdefectDet.getUpsdKeyid());
	    		
	    		CommonMessage.debugMsg(" Inside Servlet :: try block "+newGenTlUpstreamdefectDet.getUpsdPreventiveaction());
			    CommonMessage.debugMsg(" Inside Servlet :: try block "+newGenTlUpstreamdefectDet.getUpsdRawmaterial());
			    CommonMessage.debugMsg(" Inside Servlet :: try block "+newGenTlUpstreamdefectDet.getUpsdInformto());
			    CommonMessage.debugMsg(" Inside Servlet :: try block "+newGenTlUpstreamdefectDet.getUpsdActive());
	    		
	    		CommonMessage.debugMsg(" Details ::  " +newGenTlUpstreamdefectDet.getUpsdInformto());
	    		
	    		CommonMessage.debugMsg(" Master ::  " +newGenTlUpstreamdefectMst.getUpsmArea());
	    		
	    		//newGenTlUpstreamdefectMst.setUpstreamdefect(newGenTlUpstreamdefectDet);
	    		
	    		boolean insert = true;
				
				//System.out.pintln("  newGenTlMommst.getMomsKeyId() " +  newGenTlVisitors.getVisiMomsKeyid());
				if( newGenTlUpstreamdefectMst.getUpsmKeyid() == null )
				 {							
					CommonMessage.debugMsg("KeyId is Null" );
					existGenTlUpstreamdefectMst =	upstreamDefectiveServices.createNewUpstream(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
				 }	
				else
				  {
					CommonMessage.debugMsg("Update function");						
					existGenTlUpstreamdefectMst = upstreamDefectiveServices.updateNewUpstream(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
					//newGenTlUpstreamdefectMst.setUpsmCreatedby(user.getUsrm_ccno());
					insert = false;
				  }					
				CommonMessage.debugMsg("After the IF Loop");
				CommonMessage.debugMsg("Ooooold KEyId"+existGenTlUpstreamdefectMst.getUpsmKeyid());
				
			    CommonMessage.debugMsg("http sesiion 1");
			    
				//httpssion.setAttribute("GenTlMOmmst", existGenTlVisitors);
				CommonMessage.debugMsg(" DATA Sucessfuly saved ");
				JSONObject successData = new JSONObject();
				String msgPropertyIdnt;					 
					 if( insert)
					   {
						msgPropertyIdnt = "success-save";
					   }
					 else
						msgPropertyIdnt = "success-update";
				 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
					successData.put("keyId", existGenTlUpstreamdefectMst.getUpsmKeyid());
					
					JSONObject returnData = new JSONObject();
					
					if(UIUtils.isValidKeyId(filemanger)){
					    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
						returnData.put("filemanger",true);
						returnData.put("formClear",false);
					}
					
					if(UIUtils.isValidKeyId(upsactnpln)){
						
						returnData.put("upsactnpln",true);
						returnData.put("formClear",false);
						returnData.put("Upsmkeyid",existGenTlUpstreamdefectMst.getUpsmKeyid());	
					}else
					{ 
						returnData.put("formClear",false);
						returnData.put("upsactnpln", false);
					}

					
					returnData.put("Upstreamkeyid",existGenTlUpstreamdefectMst.getUpsmKeyid());
					returnData.put("successData", successData);				
					returnData.put("formClear",false);
					returnData.put("type",type);
					out.print(returnData.toString());
	    	}
          }
    	  catch (ValidationExceptions e) 
          {
					CommonMessage.debugMsg("ValidationExceptions");
					e.printStackTrace();
					net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "UpstreamDefect");
					out.print(errMessage.toString());							    	
          }
										
	     catch(Exception e)
	          {   
		         CommonMessage.debugMsg("Error Msg:" + e.getMessage());
		         JSONObject err = new JSONObject();
		         err.put("tpmException", "Data Not Saved");
		         out.print(err.toString());
	          }

		
	}
}
