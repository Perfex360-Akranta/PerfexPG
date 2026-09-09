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

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.ControlResponsePlanBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlControlandresponseplan;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.service.ControlResponseService;
import com.akranta.tpm.service.impl.ControlResponseServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;



public class ControlResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ControlResponseService controlResponseService ;
   
    public ControlResponseServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
		
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg(" action " + action);
		controlResponseService=(ControlResponseServiceImpl) UIUtils.getServiceObject(request, "ControlResponseServiceImpl");
	    
		HttpSession httpSession = request.getSession(false);
		controlResponseService.ControlResponseServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	

		

		if (action.equals("controlandresponse_input.conres")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("/pages/ControlResponseReport.jsp");
			rd.forward(request, response);
		} 
		
		
		else if (action.equals("controlandresponse_getCol.conres")) {
			/*PrintWriter out = response.getWriter();

			JSONObject jsonObject = new JSONObject();
		
			List<String[]> ctrlResGrid = null;
			//CommonFilter commonFilter = new CommonFilter();
			CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",true);

			try {
				ctrlResGrid = controlResponseService.controlResponseReport(commonFilter);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 jsonObject = getTableModelForCtrlResReport(ctrlResGrid);
			 httpSession.removeAttribute("ControlresplnColmodel");
			 httpSession.setAttribute("ControlresplnColmodel", jsonObject);
			 
			 //jsonObject = UIUtils.convertToJqGridTableObject(ctrlResGrid, request, 1, 0);
			out.println(jsonObject);*/
			
			List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
			    CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",true);
			    commonFilter.setIsGetCol("Y");
			    ctrlResGrid = controlResponseService.controlResponseReport(commonFilter);
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
				httpSession.removeAttribute("FishBoneColModel");
				httpSession.setAttribute("FishBoneColModel", colmodel);
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}			

		} 
		
		
		else if (action.equals("controlandresponse_getData.conres")) {

		
			/*try {
				
				CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",false);
				
				//CommonFilter commonFilter = new CommonFilter();

				//FilterValues.getCommonFilters(request, commonFilter);

				List<String[]> ctrlResponseReportGrid = controlResponseService.controlResponseReport(commonFilter);
						

				PrintWriter out = response.getWriter();

				JSONObject ctrlResponseReportGridmod = UIUtils.convertToJqGridTableObject(ctrlResponseReportGrid, request, 1,0);

				out.println(ctrlResponseReportGridmod);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}*/
			try {
				CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",false);
				commonFilter.setIsGetCol("N"); 
				List<String[]> ctrlResGrid = controlResponseService.controlResponseReport(commonFilter);

				PrintWriter out = response.getWriter();
				JSONObject fishGrid=UIUtils.convertToJqGridTableObject(ctrlResGrid, request,2, 0,commonFilter.getTotalRecordCnt());
				// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
  			 	out.println(fishGrid);  			 	
  			 	commonFilter.setViewClick('N');  			 	
  			 	httpSession.removeAttribute("FishBoneCommonFilter");
  			 	httpSession.setAttribute("FishBoneCommonFilter", commonFilter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}		


		}
       else if (action.equals("controlandresponse_modify.conres")) {
			
			String controlId = request.getParameter("keyId");
			CommonMessage.debugMsg(" Inside modify action :: Keyid "+controlId);
			String UnsafeGrid=request.getParameter("grid");
		
			if ("true".equals(UnsafeGrid)) {
				//String flid="FNLN00000001";
				CommonMessage.debugMsg(" Inside modify action :: Keyid :: Inside If "+controlId);
				GenTlControlandresponseplan genTlControlandresponseplan = new GenTlControlandresponseplan();
				genTlControlandresponseplan= controlResponseService.getAllFillControl(controlId);
				
				//request.setAttribute("flid", flid);
				
				request.setAttribute("genTlControlandresponseplan", genTlControlandresponseplan);
				//request.setAttribute("controlId", controlId);
		
			}
			 
			RequestDispatcher rd = request.getRequestDispatcher("/pages/ControlResponseDetail.jsp");
			rd.forward(request, response);

		}
		
       else if((action.equals("controlandresponse_getExcel.conres")))
		{
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"ControlresplnCommonFilter",false);
			JSONObject colmodel = UIUtils.getXlColModel( request, response);
			colmodel.put("title","Control and Response Plan");
            String format = ExcelUtils.getFormat(request);
			
			Workbook wb = controlResponseService.getConresplnExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "ControlandResponsePlan", format);
			
		}
		 
		 else if (action.equals("controlandresponse_save.conres")) {
			 CommonMessage.debugMsg(" Inside save action :: before " );
			 ControlResponsePlanBean controlresponseplanBean = (ControlResponsePlanBean) httpSession.getAttribute("phenBean");
			 saveConrespln(request,response,controlresponseplanBean);	
			 CommonMessage.debugMsg(" Inside save action :: after " );
			}
		 else if (action.equals("controlandresponse_delete.conres")) {
			 CommonMessage.debugMsg(" Inside save action :: before " );
			 ControlResponsePlanBean controlresponseplanBean = (ControlResponsePlanBean) httpSession.getAttribute("phenBean");
			 deleteConrespln(request,response,controlresponseplanBean);	
			 CommonMessage.debugMsg(" Inside save action :: after " );
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


	private void deleteConrespln(HttpServletRequest request,HttpServletResponse response,ControlResponsePlanBean controlresponseplanBean) throws IOException {
		// TODO Auto-generated method stub
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	try
		{
    	if(httpSession !=null && user !=null)
		{
    		GenTlControlandresponseplan newGenTlControlandresponseplan = new GenTlControlandresponseplan();
    		newGenTlControlandresponseplan=(GenTlControlandresponseplan)UIUtils.setBeanProperties((Object)newGenTlControlandresponseplan,request);
			
			
			if(UIUtils.isValidKeyId(newGenTlControlandresponseplan.getCarpKeyid())){
				newGenTlControlandresponseplan=controlResponseService.delete(newGenTlControlandresponseplan);
				httpSession.removeAttribute("GenTlUnsafeworkpractice"+newGenTlControlandresponseplan.getCarpKeyid());
				CommonMessage.debugMsg("GenTlUnsafeworkpractice"+newGenTlControlandresponseplan.getCarpKeyid());
			
			}
		
			
			JSONObject successData=new JSONObject();
    		JSONObject Unsafedatadelete=new JSONObject();
    		JSONObject returnData = new JSONObject();
    		String savemsg;
    	   if(newGenTlControlandresponseplan.getCarpKeyid()==null )
			{
    		  
				savemsg=" Data Not Deleted ";
				
			}
			else
			{
				
				savemsg= "Data Deleted succesfully";
			}
    		successData.put("msg", savemsg);
    		
    		Unsafedatadelete.put("successData", successData);
    		returnData.put("formClear",true);
    		out.print(Unsafedatadelete.toString());
    		//returnData.put("formClear",true);
    		
		}
		}catch(Exception e)
		{
			
		}
		
	}
	private void saveConrespln(HttpServletRequest request,HttpServletResponse response,ControlResponsePlanBean controlresponseplanBean) throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	String mode=null;
    	if(httpSession != null && user != null)
    	{
         
    	 GenTlControlandresponseplan newGenTlControlandresponseplan = new GenTlControlandresponseplan();
    	 newGenTlControlandresponseplan=(GenTlControlandresponseplan)UIUtils.setBeanProperties((Object)newGenTlControlandresponseplan,request);
    	
    	 CommonMessage.debugMsg(" Inside save action "+newGenTlControlandresponseplan.getCarpProcessstep());
    	 
    	 CommonMessage.debugMsg(" Inside save action "+newGenTlControlandresponseplan.getCarpKeyid());
    	 
    	 GenTlControlandresponseplan existGenTlControlandresponseplan = new GenTlControlandresponseplan();
		 String filemanger=request.getParameter("filemanger");
		 String savemsg;
		
    	 try
    	 {
    		 
    		if( newGenTlControlandresponseplan.getCarpKeyid()==null )
			{		
    			CommonMessage.debugMsg(" Inside save action :: Inside if "+newGenTlControlandresponseplan.getCarpKeyid());
    			
    			existGenTlControlandresponseplan= controlResponseService.create(newGenTlControlandresponseplan,existGenTlControlandresponseplan,controlresponseplanBean);
    			savemsg= "Data saved succesfully";
			}
			else
			{
				
				existGenTlControlandresponseplan= controlResponseService.update(newGenTlControlandresponseplan,existGenTlControlandresponseplan,controlresponseplanBean);
				savemsg= "Data Updated succesfully";
				mode="Modify";
			}
    		JSONObject successData=new JSONObject();
    		JSONObject Unsafedatasave=new JSONObject();
    		//String savemsg;
    	  /* if( newGenTlControlandresponseplan.getCarpKeyid()==null  )
			{
    		   savemsg= "Data Not saved";
				
			}
			else
			{
				
				savemsg= "Data saved succesfully";
				
			}*/
    	  
    	   if(UIUtils.isValidKeyId(filemanger)){
			    CommonMessage.debugMsg(" Inside filemanger "+filemanger);
			    Unsafedatasave.put("filemanger",true);
			    Unsafedatasave.put("formClear",false);
			}
			

    	    Unsafedatasave.put("Carpreskeyid",existGenTlControlandresponseplan.getCarpKeyid());
    		successData.put("msg", savemsg);
    		if(mode!=null)
    		{
    		CommonMessage.debugMsg("mode");
    			successData.put("mode", mode);
    		
    			
    		}
    		Unsafedatasave.put("successData", successData);
    		out.print(Unsafedatasave.toString());
    		
    	
    	 } catch (ValidationExceptions e) 
         {
			CommonMessage.debugMsg("ValidationExceptions");
			e.printStackTrace();
			net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "ControlandResponseProperty");
			out.print(errMessage.toString());							    	
         }  
    	 catch(Exception e)
		{				
    		 e.printStackTrace();
				
		}
    	}
    	
		
	}

	private JSONObject getTableModelForCtrlResReport(List<String[]> headers) {

		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] colHeader = headers.get(0);
		jqGridTableModel.setTableButton(true);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		jqGridTableModel.setTableHeight(100);
		jqGridTableModel.setTableWidth(500);
		for (int i = 0; i < colHeader.length; i++) {
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
			jqGridColModel.setWidth(90);
			jqGridColModel.setAlign("right");
			
			if (i == 0 || i == 5 || i == 8 || i == 9 ||i ==10) {	
				jqGridColModel.setWidth(120);
				CommonMessage.debugMsg("Table width i>2:::"
						+ jqGridColModel.getWidth());
				
			}
			if (i == 6||i==4) {	
				jqGridColModel.setWidth(200);
				
				}if (i == 0){
				jqGridColModel.setHidden(true);
			}if (i == 1||i==3||i==2||i==6||i == 11||i==12||i==13){
				jqGridColModel.setWidth(200);
				jqGridColModel.setAlign("left");
			}
			if(i==5||i==8||i==9||i==10){
				jqGridColModel.setAlign("left");
			}
			CommonMessage.debugMsg("Table width ["+i+"]"+colHeader[i]);
			jqGridColModel.setEditable(false);
			jqGridTableModel.getColModel().add(jqGridColModel);
		}

		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "84%%");
		tableModel.set("tableWidth", "108%%");
		return tableModel;
	}

}
