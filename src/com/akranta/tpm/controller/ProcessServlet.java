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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CriticalProcessNew;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GridColModel;
import com.akranta.tpm.model.QtmTlCriticalprocessdtl;
import com.akranta.tpm.model.QtmTlCriticalprocessmst;
import com.akranta.tpm.service.ProcessService;
import com.akranta.tpm.service.impl.ProcessServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.CriticalProcessServiceApi;

/**
 * Servlet implementation class KpivServlet
 */

public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProcessService processservice;
	
	CriticalProcessServiceApi criticalprocessapi;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response)	throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
	
	HttpSession httpSession = request.getSession(false);
	String action = UIUtils.getActionPart(request);
	
	processservice=(ProcessServiceImpl)UIUtils.getServiceObject(request,"ProcessServiceImpl");
	
	processservice.ProcessServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	
	
	
	 if(action.equals("criticalprocessNew_input.cprc"))
	{
		RequestDispatcher rd = request.getRequestDispatcher("/pages/ProcessMainGrid.jsp");
		rd.forward(request, response);
	}
	 else if(action.equals("criticalprocessNew_getCol.cprc"))
		{
		 List<String[]> ctrlResGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();  //QTM_FN_QPOINTSNEWMAINGRID
		
			try {
				
				
				    CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",true);
				    String loginFlid = CommonFunctions.getLoginFlid(request);
					 if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
					 {
					      commonFilter.setFlid(loginFlid);
					 }
					 commonFilter.setIsGetCol("Y");
				    List<String[]> Grid = processservice.getFillMainGrid(commonFilter);
					JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
					GridColModel gridColModel = new GridColModel();			
					jqGridTableModel.setSortable(true);
					jqGridTableModel.setTableButton(true);
					jqGridTableModel.setEnableFilter(true);
					jqGridTableModel.setRowNumbers(true);
					gridColModel.setHeaderNum(1);//9
					String [] colHeader = Grid.get(1);
					String [] colHeaderHead = Grid.get(0);
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
	 else if(action.equals("criticalprocessNew_getData.cprc"))
		{
		 try
			{   
		        UIUtils.displayRequestParamsValue(request);	
				CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",false);
				 String loginFlid = CommonFunctions.getLoginFlid(request);
				 if (!UIUtils.isValidKeyId(commonFilter.getFlid()))
				 {
				      commonFilter.setFlid(loginFlid);
				 }
				 commonFilter.setIsGetCol("N");
				List<String[]> Grid = processservice.getFillMainGrid(commonFilter);
				PrintWriter out = response.getWriter();
			 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(Grid,request,2,0); 
			 	out.println(UpstreamData);  

		    }catch(Exception e)
			{
				CommonMessage.debugMsg(e.getMessage());
			}
		}
	
	
	 else if(action.equals("criticalprocessNew_getExcel.cprc"))
		{
			
			  httpSession = request.getSession(false);
				CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",false);
				CommonMessage.debugMsg("excellll");
			   JSONObject colmodel = UIUtils.getXlColModel( request, response);
				CommonMessage.debugMsg("excellll888");
				
				colmodel.put("title","Critical Process");
	          String format = ExcelUtils.getFormat(request);				
				Workbook wb = processservice.getprocessExcel(colmodel,format,commonFilter);
				CommonMessage.debugMsg("excellll0000");
				ExcelUtils.writeToResponse(response, wb, "Critical Process", format);
		}
	else if (action.equals("criticalprocess_input.cprc")) {
		
		String keyid = request.getParameter("keyid");
		
		
		
		QtmTlCriticalprocessmst exstQtmTlCriticalprocessmst = null;
		if(UIUtils.isValidKeyId(keyid))
		{
			exstQtmTlCriticalprocessmst = processservice.select(keyid) ;
			//exstQtmTlCriticalprocessmst.setCrppDate(UIUtils.getActualDateForm(exstQtmTlCriticalprocessmst.getCrppDate()));
			String date = exstQtmTlCriticalprocessmst.getCrppDate();
			exstQtmTlCriticalprocessmst.setCrppDate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
			
		}
		
		request.getSession(true).removeAttribute("qtmTlCriticalprocessmst");
		request.setAttribute("qtmTlCriticalprocessmst", exstQtmTlCriticalprocessmst);
		
		request.getSession(true).setAttribute("qtmTlCriticalprocessmst", exstQtmTlCriticalprocessmst);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/pages/Process.jsp");
		rd.forward(request, response);
		
	}
	else if (action.equals("criticalprocess_getCol.cprc"))  //master grid 
	{
		PrintWriter out = response.getWriter();
		//JSONObject jsonObject = new JSONObject();  //QTM_FN_QPOINTSNEWMAINGRID
	
		try {
			
			    CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",true);
			    
			   /* String Date = request.getParameter("Date");
			    String FnlnId = request.getParameter("flId");
			    String Keyid = request.getParameter("Keyid");
			    
			    
			    // String Date  = Date.substring(0, 11);
				
			    CommonMessage.debugMsg(" Date :: "+Date+" FnlnId :: "+FnlnId+" UpstreamDate :: "+Date);
			    
			    CommonMessage.debugMsg(" After Saving :: getCol "+Keyid);
			    
			    commonFilter.setFlid(FnlnId);
				commonFilter.setDteend(Date);
				
				if(UIUtils.isValidKeyId(Keyid))
				commonFilter.setKey(Keyid);
*/
				
			    List<String[]> Grid = processservice.getAllProcess(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				jqGridTableModel.setSortable(true);
				//jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = Grid.get(1);
				String [] colHeaderHead = Grid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "106%%");
				colmodel.set("tableHeight", "60%%");
				
				out.println(colmodel);

		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	} 
	
	else if (action.equals("criticalprocess_getData.cprc"))  //master grid 
	{
	try
	{   
       UIUtils.displayRequestParamsValue(request);	
		CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",false);
		
		String Date = request.getParameter("Date");
	    String FnlnId = request.getParameter("flId");
	    String Keyid = request.getParameter("Keyid");
	    
	    String Parameter = request.getParameter("Parameter");
	    
	    CommonMessage.debugMsg(" Date :: "+Date+" FnlnId :: "+FnlnId+" UpstreamDate :: "+Date+" Parameter "+Parameter);
	    
	    //String UpstreamDate  = Date.substring(0, 11);
	    String UpstreamDate  = Date;
	    
	    CommonMessage.debugMsg(" After Saving :: "+Keyid);
	    
	    commonFilter.setFlid(FnlnId);
		commonFilter.setDteend(UpstreamDate);
		commonFilter.setParamCode(Parameter);
		
		if(UIUtils.isValidKeyId(Keyid))
		commonFilter.setKey(Keyid);
	
		List<String[]> Grid = processservice.getAllProcess(commonFilter);
		PrintWriter out = response.getWriter();
	 	JSONObject UpstreamData = UIUtils.convertToJqGridTableObject(Grid,request,2,0); 
	 	out.println(UpstreamData);  

    }catch(Exception e)
	{
		CommonMessage.debugMsg(e.getMessage());
	}
         
 }  
	
	
	else if(action.equals("criticalprocess_save.cprc"))
	{
	    CriticalProcessNew criticalProcessNew = new CriticalProcessNew();
		saveCriticalProcess(request, response, criticalProcessNew);
	}
	
	 else if(action.equals("criticalprocess_delete.cprc"))
		{
		    
			deleteCriticalProcess(request, response);
		}
	 else if(action.equals("criticalprocessDtl_delete.cprc"))
		{
		    
			deleteCriticalProcessDtl(request, response);
		}
	
	 
   else if(action.equals("criticalprocess_recall.cprc"))
	{
	    PrintWriter out = response.getWriter();
		String keyid = request.getParameter("KEYID");
		CommonMessage.debugMsg("keyid   keyid  :  "+keyid);
		List<String []> condReclData  = processservice.FillControlData(keyid);
		out.print( JSONArray.fromCollection(condReclData));
	}
   else if(action.equals("criticalprocess_getExcel.cprc"))
	{
	 	try
		{   
           httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"ProcessCommonFilter",false);
			JSONObject colmodel = UIUtils.getXlColModel(request,response);
			colmodel.put("title","Critical Process");
           String format = ExcelUtils.getFormat(request);
			
			Workbook wb = processservice.getCriticalProcessExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "CriticalProcessReport", format);
			

	    }catch(Exception e)
		{
			CommonMessage.debugMsg(e.getMessage());
		}
	}
	
	
	
}



private void deleteCriticalProcessDtl(HttpServletRequest request,HttpServletResponse response)throws IOException {
	// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	try
		{
    	if(httpSession !=null && user !=null)
		{

    		
    		String Criticalkeyid = request.getParameter("keyid");

			JSONObject successData=new JSONObject();
			if(UIUtils.isValidKeyId(Criticalkeyid)){
				
				processservice.deleteDtl(Criticalkeyid);
				httpSession.removeAttribute("QtmTlCriticalprocessmst");

  		    }
		
			
    		JSONObject Criticaldatadelete=new JSONObject();
    		String savemsg;
    	    if(Criticalkeyid ==null )
			{
				savemsg=" Data Not Deleted ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    	    
    		successData.put("msg", savemsg);
    		successData.put("formClear",true);
    		Criticaldatadelete.put("successData", successData);
    		out.print(Criticaldatadelete.toString());
    		
    		}
		}
		catch(Exception e)
		{
			
		}
	
}



	private void deleteCriticalProcess(HttpServletRequest request,HttpServletResponse response)throws IOException {
	// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	ServletOutputStream out = response.getOutputStream();
    	
    	try
		{
    	if(httpSession !=null && user !=null)
		{

    		
    		String Criticalkeyid = request.getParameter("txtCrppKeyid");

			JSONObject successData=new JSONObject();
			if(UIUtils.isValidKeyId(Criticalkeyid)){
				
				processservice.delete(Criticalkeyid);
				httpSession.removeAttribute("QtmTlCriticalprocessmst");

  		    }
		
			
    		JSONObject Criticaldatadelete=new JSONObject();
    		String savemsg;
    	    if(Criticalkeyid ==null )
			{
				savemsg=" Data Not Deleted ";
				
			}
			else
			{
				savemsg= "Data Deleted succesfully";
				
			}
    	    
    		successData.put("msg", savemsg);
    		successData.put("formClear",true);
    		Criticaldatadelete.put("successData", successData);
    		out.print(Criticaldatadelete.toString());
    		
    		}
		}
		catch(Exception e)
		{
			
		}
	
}

	private void saveCriticalProcess(HttpServletRequest request,HttpServletResponse response, 
			     CriticalProcessNew criticalProcessNew)throws IOException {
		// TODO Auto-generated method stub
		
		UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);    	
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		ServletOutputStream out = response.getOutputStream();
		String hdnelementId = request.getParameter("hdnelementId");
		String type=request.getParameter("type");
		

  try
  {
	if( httpSession != null && user != null)
	{	
		QtmTlCriticalprocessmst existQtmTlCriticalprocessmst  = (QtmTlCriticalprocessmst)httpSession.getAttribute("qtmTlCriticalprocessmst");
		
		QtmTlCriticalprocessmst  newQtmTlCriticalprocessmst  = new QtmTlCriticalprocessmst();
		QtmTlCriticalprocessdtl  newQtmTlCriticalprocessDtl  = new QtmTlCriticalprocessdtl();
		
		newQtmTlCriticalprocessmst=(QtmTlCriticalprocessmst)UIUtils.setBeanProperties((Object)newQtmTlCriticalprocessmst,request);
		newQtmTlCriticalprocessmst.setCrppElementid(hdnelementId);
		newQtmTlCriticalprocessmst.setCrppCreatedby(user.getUsrm_ccno());
		
		
		newQtmTlCriticalprocessDtl=(QtmTlCriticalprocessdtl)UIUtils.setBeanProperties((Object)newQtmTlCriticalprocessDtl,request);
		
		newQtmTlCriticalprocessmst.setQtmTlCriticalprocessdtl(newQtmTlCriticalprocessDtl);
		//newGenTlUpstreamdefect =(GenTlVisitors)UIUtils.setBeanProperties((Object)newGenTlVisitors,request);
		
		String filemanger=request.getParameter("filemanger");
		
		boolean insert = true;
		
		//CommonMessage.debugMsg("  newGenTlMommst.getMomsKeyId() " +  newGenTlVisitors.getVisiMomsKeyid());
		if( newQtmTlCriticalprocessmst.getCrppKeyid() == null )
		 {							
			existQtmTlCriticalprocessmst =	processservice.create(newQtmTlCriticalprocessmst,existQtmTlCriticalprocessmst,criticalProcessNew);
		 }	
		else
		  {
			existQtmTlCriticalprocessmst = processservice.update(newQtmTlCriticalprocessmst,existQtmTlCriticalprocessmst,criticalProcessNew);
			insert = false;
		  }					
		
		
		JSONObject successData = new JSONObject();
		String msgPropertyIdnt;					 
			 if( insert)
			   {
				msgPropertyIdnt = "success-save";
			   }
			 else
				msgPropertyIdnt = "success-save";
		 
		    successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
			successData.put("keyId", existQtmTlCriticalprocessmst.getCrppKeyid());
			
			JSONObject returnData = new JSONObject();
			
			if(UIUtils.isValidKeyId(filemanger)){
				returnData.put("filemanger",true);
				returnData.put("formClear",false);
			}
			
			returnData.put("successData", successData);				
			returnData.put("formClear",false);
			returnData.put("keyId", existQtmTlCriticalprocessmst.getCrppKeyid());
			returnData.put("type",type);
			
			out.print(returnData.toString());
	}
  }
  catch (ValidationExceptions e) 
  {
		CommonMessage.debugMsg("ValidationExceptions "+e.getMessage());
		net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(), "criticalProcessValidations");
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

private JSONObject getTableModel(List<String[]> kpivReportList,
		CommonFilter commonFilter) {
	
	JqGridTableModel jqGridTableModel = new  JqGridTableModel();
	String [] colHeader = kpivReportList.get(0);	
	jqGridTableModel.getRowHeaders().add(colHeader);
	jqGridTableModel.setTableButton(true);
	jqGridTableModel.setRowNumbers(true);	
	//jqGridTableModel.setEnableFilter(false);
	
	
	for(int i =0; i <colHeader.length; i++)
	{			
		JqGridColModel jqGridColModel = new JqGridColModel();
		jqGridColModel.setIndex(colHeader[i].replaceAll(" ", ""));
		jqGridColModel.setName(colHeader[i].replaceAll(" ", ""));
				
		jqGridColModel.setWidth( 100);				
		jqGridColModel.setAlign("left");
		jqGridColModel.setEditable(false);
		
		CommonMessage.debugMsg("colheader  "+i+" : "+colHeader[i]);//if i==0
		if(i==0){
		 
		//jqGridColModel.setHidden(true);
			jqGridColModel.setWidth(100);				
			jqGridColModel.setAlign("left");	
		
	}
	else if(i==1){
		//jqGridColModel.setHidden(true);
		
		jqGridColModel.setWidth(100);				
		jqGridColModel.setAlign("left");
	}
		
	
	else if(i==2)
	{
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("left");
		
	}else if(i==3){
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
		
	}
	else if(i==4)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==5)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}

	else if(i==6)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==7)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==8)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==9)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==10)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==11)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==12)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==13)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==14)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==15)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==16)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	else if(i==17)
	{
		
		jqGridColModel.setWidth(50);				
		jqGridColModel.setAlign("right");
	}
	
	jqGridTableModel.getColModel().add(jqGridColModel);
}

 JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
 return tableModel;
}

private CommonFilter populateCommonFilter(HttpServletRequest request,
		String beanIdentifier, boolean createNew) {
	
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
	
	return commonFilter;
}

}
