package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import org.apache.poi.ss.usermodel.Workbook;




import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BatchBean;
import com.akranta.tpm.bean.EntBatchEmployeeLinkBean;
import com.akranta.tpm.bean.EntTlBatchMstBean;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.EntTlFacultymst;
import com.akranta.tpm.service.EntBatchCreationService;
import com.akranta.tpm.service.impl.EntBatchCreationServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;

/**
 * Modified By Dhanalakshmi.R
 * Date 13-2-2013
 */

public class EntBatchCreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static EntBatchMst existEntBatchMst = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	EntBatchCreationService  entBatchCreationService ;
    public EntBatchCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
        /*try {
        	entBatchCreationService = new EntBatchCreationServiceImpl();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			process(request, response);
		} catch (Exception e) {
			
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
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		
		String action = UIUtils.getActionPart(request);
		ComboFilter comboFilter = new ComboFilter();
		response.setContentType("text/html");
		response.setContentType("text/json");
		HttpSession httpSession = request.getSession(false);
		String dispatchUrl = null;
		try {
			entBatchCreationService =  (EntBatchCreationServiceImpl)UIUtils.getServiceObject(request,"EntBatchCreationServiceImpl");
		
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}		

		if (action.equals("entBatchmst_input.entbatch")) 
		{
			String keyid = request.getParameter("keyId");
			String progKeygrd= request.getParameter("progKeygrd");
			String mode=request.getParameter("mode");
			String displayEntCode = UIUtils.displayENTCode(request);
			 request.setAttribute("displayEntCode",displayEntCode);
			if(UIUtils.isValidKeyId(progKeygrd)){
				request.setAttribute("progKeygrd", progKeygrd);
				request.setAttribute("modenew", mode);
			}
			CommonMessage.debugMsg("keyid"+keyid);
			//String userEvent = request.getParameter("userEvent");
			response.setContentType("text/html");
			if( UIUtils.isValidKeyId(keyid)) //&& userEvent == null) ||( userEvent != null &&  ! "new".equals(userEvent))
			{
				
				CommonMessage.debugMsg("mode="+mode);
				CommonMessage.debugMsg("inside validkey");
				EntBatchMst entBatchMst = entBatchCreationService.select(keyid);
				
				//String gridmode=request.getParameter("hdnFrmMode");
				
				
				FormModes modes = FormModes.view;
				if("EDIT".equals(mode))
				{
					modes = FormModes.modify;
				}
				BatchBean batchBean= new BatchBean(modes);
				
				httpSession.setAttribute("entBatchMst", entBatchMst);
				request.setAttribute("entBatchMst", entBatchMst);
				request.setAttribute("batchBean",batchBean);
				//String created=entBatchMst.getBachCreatedon();
				//CommonMessage.debugMsg("entBatchMst.getBachCreatedon()="+created);
			}
			
			dispatchUrl = "/pages/ENT/EntBatchCreation.jsp";
		}
		else if(action.equals("entBatchmst_view.entbatch"))
		{
			CommonMessage.debugMsg("inside grid");
			String progId=request.getParameter("progId");
			String openDirect=request.getParameter("openDirect");
			String frmMonth = request.getParameter("frmMonth");
			CommonMessage.debugMsg("openDirect="+openDirect);
			if(UIUtils.isValidKeyId(frmMonth))
			{
				httpSession.setAttribute("frmMonth",frmMonth);
				request.setAttribute("frmMonth",frmMonth);
				CommonMessage.debugMsg("frmMonth is valid="+frmMonth);
			}
			response.setContentType("text/html");
			if(UIUtils.isValidKeyId(progId))
			{
				CommonMessage.debugMsg("Programid is valid="+progId);
				request.setAttribute("ProgId",progId);
			}
			if(UIUtils.isValidKeyId(openDirect))
			{
				CommonMessage.debugMsg("Opendirect is valid="+openDirect);
				request.setAttribute("OpenDirect",openDirect );
				CommonMessage.debugMsg("Opendirect is set in view");
			}
			
			dispatchUrl = "/pages/ENT/BatchGrid.jsp";
		}
		else if(action.equals("entBatchmst_getCol.entbatch"))
		{
			PrintWriter out = response.getWriter();
			String openDirect=request.getParameter("opendirect");
			String displayEntCode = UIUtils.displayENTCode(request); 
			
			
			CommonMessage.debugMsg("openDirect="+openDirect);
			response.setContentType("text/html");
			response.setContentType("text/json");
			String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.BatchGridCreation","BatchGrid");
			CommonMessage.debugMsg(tableModel);
		    JSONObject colmodel =JSONObject.fromString(tableModel);
		    if(displayEntCode.equals("True"))
		    	colmodel.getJSONArray("colModel").getJSONObject(3).set("hidden",true);
		    if(openDirect!=null && openDirect.equals("true"))
			{   
		    	colmodel.set("tableWidth", "43%%");
		    	colmodel.set("tableheight","65%%");
			}
		   
			CommonMessage.debugMsg(colmodel);
			httpSession.removeAttribute("entBatchColmodel");
			httpSession.setAttribute("entBatchColmodel",colmodel);
			out.println(colmodel);
			out.close();
			
			
		}
		else if(action.equals("entBatchmst_getData.entbatch"))
		{
			CommonMessage.debugMsg("inside getdata");
			PrintWriter out = response.getWriter();
			
				
			String progId   = request.getParameter("progId");
			String frmMonth = (String)httpSession.getAttribute("frmMonth");
			CommonMessage.debugMsg("Programid is valid batch creation="+progId+" from Month "+frmMonth);
			
			
				List<String[]> BatchList  = entBatchCreationService.getBatch(progId,frmMonth);
				
			 	JSONObject BatchData = UIUtils.convertToJqGridTableObject(BatchList, request, 0, 0 );
			 	
			 	out.println(BatchData);
			 	
		
		
	}
		else if(action.equals("functionalLoc_BatchMst.entbatch")){
			 
			String mode = request.getParameter("mode");
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setFactory("cmbBachFactKeyid");
			
			functLocFieldNameBean.setFactMandatory(true);
			
			
			FormModes formModes = (FormModes)httpSession.getAttribute("EntBatchMstFormMode");			
			//if( formModes == FormModes.completion)
			if (UIUtils.isValidKeyId(mode) && mode.equals("Y"))
				formModes = FormModes.create;
			else
				formModes = FormModes.view;
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		else if(action.equals("entBatchmst_save.entbatch"))
		{	
			//CommonMessage.debugMsg("in save");		
			BatchBean batchBean = new BatchBean();
			//CommonMessage.debugMsg("genTlCountrymstBean in save "+batchBean);
			//CommonMessage.debugMsg("request b4 save "+request);
			//CommonMessage.debugMsg("response b4 save save "+response);
		    saveent(request,response,batchBean);
		}
				
		else if( action.equals("entBatchmst_delete.entbatch"))
		{	
			CommonMessage.debugMsg("Inside the DELETE");
			BatchBean batchBean = new BatchBean();
			
			Deleteent(request,response,batchBean);
			
	    }
		else if(action.equals("progKey_fillcombo.entbatch")){
			String keyId = request.getParameter("keyId");
			String spokeKey= request.getParameter("spokeKey");
			try {
				comboFilter = UIUtils.fillComboFilter(request);
 					List<ComboBox>  getProgkeyid = entBatchCreationService.getProgkeyidCombo(spokeKey,comboFilter);
 					UIUtils.writeComboBox(response, getProgkeyid,comboFilter);
 				  } 
 			  catch (Exception e) 
 				  {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				  }
		}
		else if(action.equals("spokeKeyid_fillcombo.entbatch")){
			String keyId = request.getParameter("progkeyId");
			try {
 					List<ComboBox>  getSpokeKeyid = entBatchCreationService.getspokekeyidCombo(keyId);
 					//UIUtils.writeComboBox(response, getSpokeKeyid);
 				  } 
 			  catch (Exception e) 
 				  {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				  }
		}
	   else if(action.equals("entBatchmst_recall.entbatch"))
		{	
		  // CommonMessage.debugMsg("Inside Recall");
			ServletOutputStream out = response.getOutputStream();
			String Bachkeyid =request.getParameter(ReqtParamNameConst.KEYID);
			//CommonMessage.debugMsg("BachKeyid="+Bachkeyid);
			BatchBean batchBean = new BatchBean();
			
			httpSession.removeAttribute("batchBean");
			httpSession.setAttribute("batchBean", batchBean);
			
			
			EntBatchMst entBatchMst = entBatchCreationService.select(Bachkeyid);
			httpSession.removeAttribute("entBatchMst");
			httpSession.setAttribute("entBatchMst" ,entBatchMst);
			
			JSONObject  Bachdata =  UIUtils.fromTpmModel(entBatchMst);
			JSONObject returndata = new JSONObject();
			returndata.put("Bachdata", Bachdata);
			out.print(returndata.toString());
			
			
		}
	  
	   
	   else if(action.equals("BachCombo.entbatch"))
	   {
		   try 
			{
			   String batchProgKey = request.getParameter("batchProgKey");
			   String frmMonth = (String)httpSession.getAttribute("frmMonth");
			   	CommonMessage.debugMsg("batchProgKey    :::::"+batchProgKey);
			   	comboFilter = UIUtils.fillComboFilter(request);
				List<ComboBox>  DetectedBy = entBatchCreationService.getBatchComboList(batchProgKey,frmMonth,comboFilter);
				UIUtils.writeComboBox(response, DetectedBy,comboFilter);
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
	   }

	   else if(action.equals("entFacultyCombo.entbatch"))
	   {
		   try 
			{
			   	CommonFilter commonFilter=new CommonFilter();
				comboFilter = UIUtils.fillComboFilter(request);
			   	String batchId=request.getParameter("batchId");
			   	List<ComboBox>  DetectedBy = entBatchCreationService.getFacultyComboList(commonFilter,batchId,comboFilter);
				UIUtils.writeComboBox(response, DetectedBy,comboFilter);
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
	   }
	   else if(action.equals("entVenueCombo.entbatch"))
	   {
		   try 
			{
			   String flid=request.getParameter("flId");
				comboFilter = UIUtils.fillComboFilter(request);   	
				List<ComboBox>  DetectedBy = entBatchCreationService.getVenueComboList(comboFilter,flid );
				UIUtils.writeComboBox(response, DetectedBy,comboFilter );
			} 
			catch (Exception e) 
			{
				
				e.printStackTrace();
			}
	   }
	   else if( action.equals("entBatchmst_getExcel.entbatch")){
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("entBatchColmodel");
			CommonMessage.debugMsg("Inside get excel action:colmodel= "+colmodel);
			String format = ExcelUtils.getFormat(request);
			CommonMessage.debugMsg("format="+format);
			Workbook wb = entBatchCreationService.BatchExportExcel(colmodel,format);
			ExcelUtils.writeToResponse(response, wb, "BatchCreationReport", format);
		}
	    else if(action.equals("entAddbatch_input.entbatch")) 
		{		
		   	String batchId = request.getParameter("batchId");
		   	String progKey = request.getParameter("progKeyid");
		   	CommonMessage.debugMsg("progKey="+progKey);
		   	request.setAttribute("batchId", batchId);
		   	request.setAttribute("progKey", progKey);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/addBatch.jsp"); 
			rd.forward(request, response);
		}	
	    else if(action.equals("facgrid_input.entbatch")){
	    	String bflkBachKeyid = request.getParameter("bflkBachKeyid");
		   	String progKeyBatch = request.getParameter("progKeyBatch");
	    	request.setAttribute("batchId", bflkBachKeyid);
		   	request.setAttribute("progKey", progKeyBatch);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/addTrainerFaculty.jsp"); 
			rd.forward(request, response);
	    }
	    else if(action.equals("facgrid_getCol.entbatch")){
	    	PrintWriter out = response.getWriter();
	    	 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelfacGrid"));
	    }
	    else if(action.equals("facgrid_getData.entbatch")){
	    	CommonMessage.debugMsg("facukty grid getdata as");
	    	String programKeyId = request.getParameter("progKeyid");
	    	if( ! UIUtils.isValidKeyId(programKeyId)){
	    		programKeyId = request.getParameter("progKeyBatch");
	    	}
	    	PrintWriter out = response.getWriter();
	    	List<String []> facultyList  = entBatchCreationService.getFacultylist(programKeyId);
	    	JSONObject FacultyData = UIUtils.convertToJqGridTableObject(facultyList,request,0,0);
			 	CommonMessage.debugMsg(FacultyData);
			 	out.println(FacultyData);
	    }
	    else if( action.equals("entAddbatch_getCol.entbatch") )
		{
			CommonMessage.debugMsg("entAddbatch_getCol.entbatch");
			PrintWriter out = response.getWriter();
			JSONObject jsonobj = null; 
	    	jsonobj = jsonobj.fromString(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelAddBatch")) ;
	    	  String frmbatch = request.getParameter("frmbatch");
			     if(UIUtils.isValidKeyId(frmbatch)){
			    	 jsonobj.set("tableWidth", "300");
				     jsonobj.set("tableHeight", "29%%");
			    	 jsonobj.getJSONArray("colModel").getJSONObject(3).set("hidden",true);
			    	 jsonobj.getJSONArray("colModel").getJSONObject(3).set("width",80);
			    	 out.println(jsonobj);
			     }
			     else
			    	 out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelAddBatch"));
		}
		else if( action.equals("entAddbatch_getData.entbatch") )
		{
			try
			{	
				PrintWriter out = response.getWriter();
				String bflkBachKeyid=request.getParameter("bflkBachKeyid");
				EntTlBatchFacultyLink entTlBatchFacultyLink = new EntTlBatchFacultyLink();
				if(CommonFunctions.isValidKeyId(bflkBachKeyid)){
					entTlBatchFacultyLink.setBflkBachKeyid(bflkBachKeyid);
					List<String []> batchFacultyList  = entBatchCreationService.getBatchFacultyView(entTlBatchFacultyLink);
	  			 	JSONObject batchFacultyData = UIUtils.convertToJqGridTableObject(batchFacultyList,request,0,0);
	  			 	CommonMessage.debugMsg(batchFacultyData);
	  			 	out.println(batchFacultyData);
				}	
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		else if (action.equals("entAddbatch_save.entbatch")) 
		{ 
			EntTlBatchMstBean entTlBatchMstBean = new  EntTlBatchMstBean();
			saveBatch(request,response,entTlBatchMstBean);
		}
		else if( action.equals("entAddbatch_delete.entbatch"))
		{	
			EntTlBatchMstBean entTlBatchMstBean = new  EntTlBatchMstBean();
			delBatchFaculty(request,response,entTlBatchMstBean);
			
	    }
		else if (action.equals("entBatch_checkFaculty.entbatch"))
		{
			PrintWriter out = response.getWriter();
			String bflkFtymKeyid=request.getParameter("bflkFtymKeyid");
			EntTlBatchFacultyLink entTlBatchFacultyLink = new EntTlBatchFacultyLink();
			entTlBatchFacultyLink.setBflkFtymKeyid(bflkFtymKeyid);
			List<String []> facultyCountList  = entBatchCreationService.getBatchFacultyCount(entTlBatchFacultyLink);
		 	JSONObject facultycountData = new JSONObject();
		 	CommonMessage.debugMsg("facultycountData"+facultyCountList.get(0)[0]);
		 	facultycountData.put("facultyCount", facultyCountList.get(0)[0]);
		 	out.print(facultycountData.toString());	
		}
		else if(action.equals("entAddEmp_input.entbatch")){
			String batchId = request.getParameter("batchId");
		   	request.setAttribute("batchId", batchId);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/EntbatchEmployeelink.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("entAddEmp_getCol.entbatch")){
			CommonMessage.debugMsg("entAddEmp_getCol.entbatch");
			PrintWriter out = response.getWriter();
			JSONObject jsonobj = null; 
			jsonobj = jsonobj.fromString(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelAddEmployee")) ;
	    	  String frmbatch = request.getParameter("frmbatch");
			     if(UIUtils.isValidKeyId(frmbatch)){
			    	 jsonobj.set("tableWidth", "300");
				     jsonobj.set("tableHeight", "29%%");
			    	 //jsonobj.getJSONArray("colModel").getJSONObject(3).set("hidden",true);
			    	// jsonobj.getJSONArray("colModel").getJSONObject(3).set("width",80);
			    	 jsonobj.set("multiSelect",false);
			    	 out.println(jsonobj);
			     }
			     else
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.EntBatchCreation", "colModelAddEmployee"));
		}
		else if(action.equals("entAddEmp_getData.entbatch")){
			CommonMessage.debugMsg("entAddEmp_getData.entbatch");
			try
			{	
				PrintWriter out = response.getWriter();
				String batchId=request.getParameter("bstdbachkeyid");
				String deptId=request.getParameter("deptKeyid");
				String roleId=request.getParameter("role");
				String mgrId=request.getParameter("mgrKeyid");
				String frmbatch = request.getParameter("frmbatch");
				String selecTdBatch =  "false";
				if(!UIUtils.isValidKeyId(deptId))
					deptId = null;
				if(!UIUtils.isValidKeyId(roleId))
					roleId = null;
				if(!UIUtils.isValidKeyId(mgrId))
					mgrId = null;
				String empFilter = roleId+"~"+deptId+"~"+mgrId;
			     if(UIUtils.isValidKeyId(frmbatch)){
			    	 selecTdBatch = "true";
			     }
			     //deptId=deptId+"/"+selecTdBatch;
			     deptId=selecTdBatch;
				CommonMessage.debugMsg("deptId  "+deptId +"  batchId "+batchId   );
				if(CommonFunctions.isValidKeyId(batchId)){
					CommonMessage.debugMsg("inside");
					EntTlBatchEmployeeLink entTlBatchEmployeeLink = new EntTlBatchEmployeeLink();
					List<String []> batchFacultyList  = entBatchCreationService.getBatchEmployeeView(entTlBatchEmployeeLink,batchId,deptId,empFilter);
					CommonMessage.debugMsg("query returned");
	  			 	JSONObject batchEmployeeData = UIUtils.convertToJqGridTableObject(batchFacultyList,request,0,0);
	  			 	CommonMessage.debugMsg(batchEmployeeData);
	  			 	out.println(batchEmployeeData);
				}	
					
			}catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
		/*else if(action.equals("entAddEmployee_delete.entbatch")){
			EntBatchEmployeeLinkBean entBatchEmployeeLinkBean  = new  EntBatchEmployeeLinkBean();
			deleteBatchEmployeeLink(request,response,entBatchEmployeeLinkBean);
		}*/
		else if(action.equals("entAddEmployee_save.entbatch")){
			EntBatchEmployeeLinkBean entBatchEmployeeLinkBean = new  EntBatchEmployeeLinkBean();
			saveBatchEmployeeLink(request,response,entBatchEmployeeLinkBean);
		}else if(action.equals("deleteBatchEmployee.entbatch")){
			CommonMessage.debugMsg("action:" +"deleteBatchEmployee.entbatch");
			PrintWriter out=response.getWriter();
			String bstdkeyid=request.getParameter("bstdkeyid");
			
			try {
				if(UIUtils.isValidKeyId(bstdkeyid)){
					entBatchCreationService.deleteByBstdKeyId(bstdkeyid);
					JSONObject successData = new JSONObject(); 
					//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));					
					successData.put("msg","Delete Success");
					JSONObject returnData = new JSONObject();	
					returnData.put("formClear",false);
					returnData.put("successData", successData);
					out.print(returnData.toString());
					out.close();	
				}
					
		}		
		catch(Exception e)
		{
			//CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			out.print(err.toString());
		}
			
		}
		if (dispatchUrl != null)
		{
			UIUtils.forwardRequest(request, response, dispatchUrl);
		}
	}

	private void saveent(HttpServletRequest request, HttpServletResponse response,BatchBean batchBean  ) throws IOException{
		
    	
    	HttpSession httpSession = request.getSession(false);
    	//CommonMessage.debugMsg("Session value in save "+httpSession);
    	ServletOutputStream out = response.getOutputStream();
    	//CommonMessage.debugMsg("ServletOutputStream in save "+out);
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	//CommonMessage.debugMsg("AdmTlUsermst in save "+user);
    	CommonMessage.debugMsg("in save function");
    	if( httpSession != null && user != null)
    	{	
    		EntBatchMst newEntBatchMst = new EntBatchMst();
			EntBatchMst existEntBatchMst = (EntBatchMst)httpSession.getAttribute("entBatchMst");
			newEntBatchMst.setBachCreatedby(user.getUsrm_ccno());
			//CommonMessage.debugMsg("user.getUsrm_ccno() in save "+user.getUsrm_ccno());
			//CommonMessage.debugMsg("request keyid = " + request.getParameter("txtentName") );
			
			newEntBatchMst =(EntBatchMst)UIUtils.setBeanProperties((Object)newEntBatchMst,request);
			
			//CommonMessage.debugMsg("newGenTlCountrymst in save"+newEntBatchMst);
			batchBean =(BatchBean) UIUtils.setBeanProperties((Object)batchBean,request);
			//CommonMessage.debugMsg("genTlCountrymstBean in save"+batchBean);
			
			//String createdon=existEntBatchMst.getBachCreatedon();
		//CommonMessage.debugMsg("existEntBatchMst.getBachCreatedon()="+createdon);
			String comp=newEntBatchMst.getBachKeyid();
			//CommonMessage.debugMsg("MAK code  :"+newEntBatchMst.getentCode());
			try{
				//CommonMessage.debugMsg("  newgenTlCountrymst.getConmKeyid() " +  newEntBatchMst.getentBatchKeyid());
				if( newEntBatchMst.getBachKeyid() == null )
				{	//CommonMessage.debugMsg("  servletsave " );
					existEntBatchMst = entBatchCreationService.create(newEntBatchMst,existEntBatchMst,batchBean);
					//CommonMessage.debugMsg(" existGenTlCountrymst in save"+existEntBatchMst);
					
				}
				else{
					CommonMessage.debugMsg("  servletupdatee " );	
					existEntBatchMst = entBatchCreationService.update(newEntBatchMst,existEntBatchMst,batchBean);
					//CommonMessage.debugMsg(" existGenTlCountrymst in update"+newEntBatchMst);
				}
				CommonMessage.debugMsg("Afetr asbe");
				httpSession.setAttribute( "entBatchMst" +  newEntBatchMst.getBachKeyid(), existEntBatchMst);
				httpSession.setAttribute("entBatchMst", existEntBatchMst);
				String formBeanIdentifier = "batchBean"+batchBean.getFormActionMode();
				//CommonMessage.debugMsg(" formBeanIdentifier in save"+formBeanIdentifier);
				httpSession.setAttribute(formBeanIdentifier,batchBean);
				String msgPropertyIdnt;	
				if(comp==null)
				{
					msgPropertyIdnt="success-save";
				}
				else
				{
					msgPropertyIdnt="success-update";
				}
				
				//JSONObject mode = new JSONObject(); 
				//mode.put("formMode",batchBean.getFormActionMode());
				JSONObject successData = new JSONObject(); 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				successData.put("BachKeyid",existEntBatchMst.getBachKeyid() );
				successData.put("progKeyid",existEntBatchMst.getBachProgKeyid());
				
				JSONObject result = new JSONObject();
				result.put("successData",successData );
				httpSession.removeAttribute("entBatchMst");
				//PrintWriter  out1 = response.getWriter();
				out.print(result.toString());
				out.close();
				//mode.put("forwardData",forwardData);
				//mode.put("persistentData", persistentData);
				
			}
				
		
					
			catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"BatchCreation");
				errMessage.put("fromMode",batchBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch(Exception e)
			{
				
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				String excep = null;
				if(e.getMessage().contains("actual")){
					excep = UIUtils.valueTooLongExceptions(e.getMessage(),"");
					err.put("tpmException", excep);
				}
				else
					err.put("tpmException", "Data Not Saved  ");
				
				out.print(err.toString());
			}
	    	
	    }	
	}

    private void Deleteent(HttpServletRequest request,HttpServletResponse response, BatchBean batchBean) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		CommonMessage.debugMsg("delete");
    		EntBatchMst newEntBatchMst = new EntBatchMst();
    		newEntBatchMst.setBachCreatedby(user.getUsrm_ccno());
    		CommonMessage.debugMsg("old id::::"+existEntBatchMst);
    		newEntBatchMst =(EntBatchMst)UIUtils.setBeanProperties((Object)newEntBatchMst,request);
    		batchBean =(BatchBean)UIUtils.setBeanProperties((Object)batchBean,request);
    		batchBean =(BatchBean) UIUtils.setBeanProperties((Object)batchBean,request);
    
			try{
				CommonMessage.debugMsg("Delete function");						
				existEntBatchMst = entBatchCreationService.delete(newEntBatchMst);						
				httpSession.setAttribute(existEntBatchMst.getBachKeyid(), existEntBatchMst);
				httpSession.setAttribute("entBatchMst", existEntBatchMst);
				String formBeanIdentifier = "bacthBean"+batchBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,batchBean);
							
				JSONObject mode = new JSONObject();
				mode.put("formMode",batchBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("BachKeyid",existEntBatchMst.getBachKeyid());
				persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				//successData.put("msg","Data Deleted Successfully");
				successData.put("mode",batchBean.getFormActionMode() );
				successData.put("keyId", existEntBatchMst.getBachKeyid());
				
				JSONObject result = new JSONObject();
				result.put("displayMsg", true);
				result.put("successData", successData);	
				httpSession.removeAttribute("entBatchMst");
				out.print(result.toString());
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"BatchCreation");//properties file name
				errMessage.put("fromMode",batchBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}
			catch(BusinessApplicationExceptions e)
			{
				CommonMessage.debugMsg("Error Servler e -"+e.getMessage());
				String msg =e!= null ? e.getMessage() : null;
				if(msg!= null && msg.contains("ORA-02292") && msg.contains("FK_") )
				{
					msg = UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","delete-ref-record");
					JSONObject err = new JSONObject();
				//	JSONObject confirm = new JSONObject();
					//confirm.put("confirm",msg);
					err.put("tpmException",msg);
				//	err.put("displyMsg",true);
					out.print(err.toString());
					out.close();
				//JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"BatchCreation");
				//out.print(errMessage.toString());
				//CommonMessage.debugMsg(" error in buisness validation ");
				}
					
			}
			catch(Exception e)
			{
				JSONObject err = new JSONObject();
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    }
		
	}
    
private void saveBatch(HttpServletRequest request, HttpServletResponse response,EntTlBatchMstBean entTlBatchMstBean ) throws IOException{
		
    	
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	
    	if( httpSession != null && user != null)
    	{	
    		EntTlBatchFacultyLink newEntTlBatchFacultyLink = new EntTlBatchFacultyLink();
			newEntTlBatchFacultyLink.setBflkCreatedby(user.getUsrm_ccno());
			
		 	newEntTlBatchFacultyLink =(EntTlBatchFacultyLink)UIUtils.setBeanProperties((Object)newEntTlBatchFacultyLink,request);
			entTlBatchMstBean =(EntTlBatchMstBean) UIUtils.setBeanProperties((Object)entTlBatchMstBean,request);
			EntTlBatchFacultyLink existEntTlBatchFacultyLink = (EntTlBatchFacultyLink)httpSession.getAttribute("EntBatchFacultyCreationServlet"); 
			EntTlFacultymst newentTlFacultymst = new EntTlFacultymst();
			String facGriddata = request.getParameter("facGriddata");
			String frmPopGrid =  request.getParameter("facmst");
			CommonMessage.debugMsg("from grid"+frmPopGrid);
			CommonMessage.debugMsg( newEntTlBatchFacultyLink.getBflkBachKeyid());
			try{
				if(UIUtils.isValidKeyId(frmPopGrid))
					newEntTlBatchFacultyLink.setFrmGrid(frmPopGrid);
				List<EntTlFacultymst> facultymstGridList = null;
				JSONArray facultymstjson = null;
				if(UIUtils.isValidKeyId(facGriddata)){
					facultymstjson = JSONArray.fromString(facGriddata);
					facultymstGridList=(List<EntTlFacultymst>)UIUtils.convertJSONArrToList(newentTlFacultymst, facultymstjson);
				
					if(facultymstGridList!= null)
						newEntTlBatchFacultyLink.setEntTlFacultymst(facultymstGridList);
				}
					boolean insert = true;
					if( newEntTlBatchFacultyLink.getBflkKeyid() == null )
					{
						existEntTlBatchFacultyLink = entBatchCreationService.createBatchFaculty(newEntTlBatchFacultyLink,existEntTlBatchFacultyLink,entTlBatchMstBean);
						
					}
					else{
						existEntTlBatchFacultyLink = entBatchCreationService.updateBatchFaculty(newEntTlBatchFacultyLink,existEntTlBatchFacultyLink,entTlBatchMstBean);
						insert = false;
					}
					new JSONObject();
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("batchFacultyid",existEntTlBatchFacultyLink.getBflkKeyid());
					
					
				    String msgPropertyIdnt;				
				 
					 if( insert){
						msgPropertyIdnt = "success-save";
					 }else
						 msgPropertyIdnt = "success-update";
					 JSONObject successData = new JSONObject(); 
				    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("mode",entTlBatchMstBean.getFormActionMode() );
					successData.put("keyId", existEntTlBatchFacultyLink.getBflkKeyid());
					JSONObject returnData = new JSONObject();	
					returnData.put("formClear",false);
					returnData.put("successData", successData);
						
					//httpSession.removeAttribute("EntEmpMangerLinkServlet");
					httpSession.setAttribute( "EntBatchFacultyCreationServlet", existEntTlBatchFacultyLink);
					//httpSession.removeAttribute("genTlCompanymstBean");
					CommonMessage.debugMsg(" after save  3");
					out.print(returnData.toString());
					out.close();
				
			}catch(BusinessApplicationExceptions e)
			{ 
				CommonMessage.debugMsg("BusinessApplicationExcepions");
				JSONObject errMessage = new JSONObject();
				errMessage.put("tpmException", "Faculty already exist for this batch");
				out.print(errMessage.toString());
			}			
			catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("e.yguiyg " + e.getMessage());
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
				errMessage.put("fromMode",entTlBatchMstBean.getFormActionMode());
				out.print(errMessage.toString());
					
			}catch(Exception e)
			{
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				if(e.getMessage() == null)
					err.put("tpmException", "Faculty already exist for this batch");
				else{
					err.put("tpmException", "Data Not Saved");
				}
				out.print(err.toString());
			}
			
	    }	
	}
	private void delBatchFaculty(HttpServletRequest request,
			HttpServletResponse response, EntTlBatchMstBean entTlBatchMstBean) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter outdel = response.getWriter();
		//ServletOutputStream outdel = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		String rowId=request.getParameter("rowId");
		if( httpSession != null && user != null)
		{	
			EntTlBatchFacultyLink entTlBatchFacultyLink = new EntTlBatchFacultyLink();
			entTlBatchFacultyLink.setBflkCreatedby(user.getUsrm_ccno());
			entTlBatchFacultyLink =(EntTlBatchFacultyLink)UIUtils.setBeanProperties((Object)entTlBatchFacultyLink,request);
			httpSession.getAttribute("EntBatchFacultyCreationServlet");
			
			try{
						String BatchFacultyId = request.getParameter("BatchFacultyId");
						entTlBatchFacultyLink.setBflkKeyid(BatchFacultyId);
						EntTlBatchFacultyLink delStaus = entBatchCreationService.deleteBatchFaculty(entTlBatchFacultyLink);
						JSONObject successData = new JSONObject();
						//successData.put("msg",delStaus);					
						//successData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
						JSONObject returnData = new JSONObject();	
						
						returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
						returnData.put("formClear",true);
						CommonMessage.debugMsg("rowId"+rowId);
						returnData.put("rowId",request.getParameter("rowId"));
						outdel.print(returnData.toString());
						outdel.close();
				}
			catch(BusinessApplicationExceptions e)
			{ 
				PrintWriter  out = response.getWriter();
				CommonMessage.debugMsg("BusinessApplicationExcepions");
				JSONObject errMessage = new JSONObject();
				
				out.print(errMessage.toString());
				
			}	
			
			catch(ValidationExceptions e)
			{
				PrintWriter  outexdel = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
				
				errMessage.put("formActionMode",entTlBatchMstBean.getFormActionMode());				
				outexdel.print(errMessage.toString());
				
			}
			
		}
	}
	@SuppressWarnings("unchecked")
	private void saveBatchEmployeeLink(HttpServletRequest request, HttpServletResponse response,EntBatchEmployeeLinkBean entBatchEmployeeLinkBean ) throws IOException, ValidationExceptions{
			
			
			HttpSession httpSession = request.getSession(false);
			ServletOutputStream out = response.getOutputStream();
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			if( httpSession != null && user != null)
			{	
				EntTlBatchEmployeeLink newEntTlBatchEmployeeLink = new EntTlBatchEmployeeLink();
				//newEntTlBatchEmployeeLink.setBstdcreatedby(user.getUsrm_ccno());
				//newEntTlBatchEmployeeLink =(EntTlBatchEmployeeLink)UIUtils.setBeanProperties((Object)newEntTlBatchEmployeeLink,request);
				entBatchEmployeeLinkBean =(EntBatchEmployeeLinkBean) UIUtils.setBeanProperties((Object)entBatchEmployeeLinkBean,request);
				//EntTlBatchEmployeeLink existEntTlBatchEmployeeLink = (EntTlBatchEmployeeLink)httpSession.getAttribute("EntBatchEmployeeCreationServlet"); 
				
				try{
						
						String employeeVar = request.getParameter("employee");
						String batchId=request.getParameter("hdnBatchkeyId");
						CommonMessage.debugMsg("batchId:"+batchId);
						List<EntTlBatchEmployeeLink> EmployeelinkList = null;
						if(UIUtils.isValidKeyId(employeeVar))
						{
			    			JSONArray EmployeelinkJson = null;
					    	if(UIUtils.isValidKeyId(employeeVar))
					    	{
					    		
					    		EmployeelinkJson = JSONArray.fromString(employeeVar);
					    		EmployeelinkList = (List<EntTlBatchEmployeeLink>)UIUtils.convertJSONArrToList(newEntTlBatchEmployeeLink,EmployeelinkJson);
					    		
			    		    }
			    		}
						entBatchCreationService.createBatchEmployee(user.getUsrm_ccno(),batchId,EmployeelinkList,entBatchEmployeeLinkBean);
						String msgPropertyIdnt;				
						msgPropertyIdnt = "success-save";
						JSONObject successData = new JSONObject(); 
						successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
						successData.put("mode",entBatchEmployeeLinkBean.getFormActionMode() );
						JSONObject returnData = new JSONObject();	
						returnData.put("formClear",false);
						returnData.put("successData", successData);
						out.print(returnData.toString());
						out.close();	
							
				}		
				catch(Exception e)
				{
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Data Not Saved");
					out.print(err.toString());
				}
				
		    }	
		}

	/*private void deleteBatchEmployeeLink(HttpServletRequest request,
			HttpServletResponse response, EntBatchEmployeeLinkBean entBatchEmployeeLinkBean) throws Exception
	{
		HttpSession httpSession = request.getSession(false);
		PrintWriter outdel = response.getWriter();
		//ServletOutputStream outdel = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null)
		{	
			EntTlBatchEmployeeLink entTlBatchEmployeeLink = new EntTlBatchEmployeeLink();
			entTlBatchEmployeeLink.setBstdcreatedby(user.getUsrm_ccno());
			//if( mouldFormBean == null)
				//mouldFormBean = new MouldFormBean(FormModes.create);
		
			
			entTlBatchEmployeeLink =(EntTlBatchEmployeeLink)UIUtils.setBeanProperties((Object)entTlBatchEmployeeLink,request);
			httpSession.getAttribute("EntBatchEmployeeCreationServlet");
			
			try{
				String employeeVar = request.getParameter("employeedlt");
				if(!UIUtils.isValidKeyId(employeeVar))
	    		{
		    	
	    		}
	    		else
	    		{
	    			List<EntTlBatchEmployeeLink> EmployeelinkList = null;
			    	JSONArray EmployeelinkJson = null;
			    	if(UIUtils.isValidKeyId(employeeVar))
			    	{
			    		EmployeelinkJson = JSONArray.fromString(employeeVar);
			    		EmployeelinkList = (List<EntTlBatchEmployeeLink>)UIUtils.convertJSONArrToList(entTlBatchEmployeeLink,EmployeelinkJson);
			 
			    		if(EmployeelinkList!= null){
			    			entTlBatchEmployeeLink.setmethodEntTlBatchEmployeeLink(EmployeelinkList);
			    		}
	    		    }
	    		}
				if(entTlBatchEmployeeLink.getmethodEntTlBatchEmployeeLink()!= null && entTlBatchEmployeeLink.getmethodEntTlBatchEmployeeLink().size()>=0) // check for detail table data
				{
					
					for(int i =0;i<entTlBatchEmployeeLink.getmethodEntTlBatchEmployeeLink().size();i++)
					{	
						EntTlBatchEmployeeLink newentTlBatchEmployeeLink = (EntTlBatchEmployeeLink)entTlBatchEmployeeLink.getmethodEntTlBatchEmployeeLink().get(i);
						entTlBatchEmployeeLink.setBstdkeyid(newentTlBatchEmployeeLink.getBstdkeyid());
						if( ! UIUtils.isValidKeyId( entTlBatchEmployeeLink.getBstdkeyid() ))
							{	
								
												
							}
							else
							{					
									entBatchCreationService.deleteBatchEmployee(entTlBatchEmployeeLink);
									JSONObject successData = new JSONObject();
									successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));					
								
									JSONObject returnData = new JSONObject();					
									returnData.put("successData", successData);		
									
									outdel.print(returnData.toString());
									CommonMessage.debugMsg(successData);
							}
						}
				}
			}	
			catch(ValidationExceptions e)
			{
				PrintWriter  outexdel = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
				errMessage.put("formActionMode",entBatchEmployeeLinkBean.getFormActionMode());				
				outexdel.print(errMessage.toString());
				
			}
			
		 }

	}*/
}

