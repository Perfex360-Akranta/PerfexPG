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

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BdmTlWwbladtl;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.model.GridColModel;

import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.FishBoneService;
import com.akranta.tpm.service.WwblaService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;

import com.akranta.tpm.service.impl.WwblaServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import com.akranta.tpm.service.api.WwblaServiceApi;


public class WwblaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	WwblaService wwblaService;
	CommonFilterService commonFilterService;
	WwblaServiceApi wwblaserviceapi;


	private Object existBdmTlWwblamst;


	private BdmTlWwbladtl newBdmTlWwblamst; 
    public WwblaServlet() throws Exception {
        super(); 
    }
	
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	    	try {
				process(request, response);
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
		}
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	    	try {
				process(request, response);
			} catch (Exception e) {
			
				e.printStackTrace();
			} 
	    }
	
	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {
		try {
			wwblaService = (WwblaServiceImpl)UIUtils.getServiceObject(request,"WwblaServiceImpl");
			commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			
		String dispatchUrl = null;
		String action = UIUtils.getActionPart(request);
		CommonMessage.debugMsg("action " + action);
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		HttpSession httpSession = request.getSession(false);
		wwblaService.WwblaServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
	
		
		if (action.equals("wwbla_input.wwbla"))
		{
			String UserLogin =user.getUsrm_ccno();
			CommonMessage.debugMsg(" UserLogin :: " +UserLogin);
			request.setAttribute("UserLogin", UserLogin);
			
            
			String lopcId = request.getParameter("lopckeyid");
			String WWBLkeyid = request.getParameter("WWBLkeyid");
			CommonMessage.debugMsg("Key id "+WWBLkeyid);
			String WwblFlid = request.getParameter("wwblFlid");
			String preparedBy = request.getParameter("EmployeeId");
			String lopcTier = request.getParameter("Tier");
			String lopcDesc = request.getParameter("LopcDesc");
			String mechanism = request.getParameter("mechanism");
			if (mechanism == null) {
			    mechanism = "";
			}
			
		
			//String lopcProblem = request.getParameter("")
			CommonMessage.debugMsg("keyid "+lopcId);
			
			CommonMessage.debugMsg(lopcId);
			
			
			String problem=request.getParameter("problem");
			BdmTlWwblamst newBdmTlWwblamst = new BdmTlWwblamst();
			//newGenTlFishbonemst = fishBoneService.getFillControl(FishboneKeyId);
			
			
			if (!UIUtils.isValidKeyId(newBdmTlWwblamst.getWwblFlid()))
				newBdmTlWwblamst.setWwblFlid(WwblFlid);
			
			if(!UIUtils.isValidKeyId(newBdmTlWwblamst.getWwblProblem()))
				newBdmTlWwblamst.setWwblProblem(problem);
			
			newBdmTlWwblamst.setWwblPreparedby(preparedBy);
			newBdmTlWwblamst.setWwblProblem(lopcTier);
			newBdmTlWwblamst.setWwblPhenomena(lopcDesc);
			newBdmTlWwblamst.setWwblLopcId(lopcId);
			newBdmTlWwblamst.setWwblMechanism(mechanism);
			newBdmTlWwblamst.setWwblKeyid(WWBLkeyid);
			
			
			request.setAttribute("newBdmTlWwblamst", newBdmTlWwblamst);
			request.setAttribute("LopcId", lopcId);
			
			//httpSession.setAttribute("existGenTlFishbonemst ",newGenTlFishbonemst);
			
			CommonMessage.debugMsg("dddd");
			RequestDispatcher rd=request.getRequestDispatcher("/pages/Wwbla.jsp");
		      rd.forward(request,response);
		      CommonMessage.debugMsg("responce123"+response);
	}	
		else if( action.equals("loadval.wwbla") )
		{		CommonMessage.debugMsg("chk loadval");
			PrintWriter out = response.getWriter();
			LoadWwblaTree(request, response,out);
		}
		else if( action.equals("searchnode.wwbla") )
		{	
			PrintWriter out = response.getWriter();
			searchWwbla(request,response,httpSession,out);
		}

		else if( action.equals("WwblaTree_save.wwbla") )
		{	
			//WwblaBean wwblaBean = (WwblaBean)httpSession.getAttribute(" " +".");
	        saveWwbla(request,response);
		
		}
		else if( action.equals("WWBLAChildEntry_save.wwbla") )
		{	
			saveWWBLAChildEntry(request,response);
		}
		else if( action.equals("WWBLAChildEntry_delete.wwbla") )
		{	
			deleteWWBLAChildEntry(request,response);
		}
		else if( action.equals("wwbla_modify.wwbla") )
	    {   
			//CommonMessage.debugMsg("jhaTlFiveAuditarea_modify.5saudit");
			
			String levelNo = request.getParameter("levelNo");
			String OrderNo = request.getParameter("OrderNo");
			String dispCode = request.getParameter("dispCode");
			String ParentId = request.getParameter("ParentId");
			String Masterid = request.getParameter("Masterid");
			String DtlId = request.getParameter("DtlId");
			 String mode = request.getParameter("mode");  // ADD THIS LINE
			    String Status = request.getParameter("Status"); 
			CommonMessage.debugMsg(levelNo+" OrderNo ::  "+OrderNo+" dispCode :: "+dispCode+" ParentId :: "+ParentId+" Masterid :: "+Masterid);
			request.setAttribute("levelNo", levelNo);
			request.setAttribute("OrderNo", OrderNo);
			request.setAttribute("dispCode", dispCode);
			request.setAttribute("ParentId", ParentId);
			request.setAttribute("Masterid", Masterid);
			request.setAttribute("DtlId", DtlId);
			request.setAttribute("mode", mode);      // ADD THIS LINE
		    request.setAttribute("Status", Status); 
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/Wwblachildentry.jsp");					
			rd.forward(request, response);		
	    }
		else if( action.equals("wwblafillcombo.wwbla") )
	    {   
			String type = request.getParameter("type");
			PrintWriter out = response.getWriter();
			if("verify".equals(type))
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.wwblaType", "verify"));
			if("factor".equals(type))
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.wwblaType", "factor"));
			if("type".equals(type))
				out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.wwblaType", "type"));
					
	    }else if( action.equals("Wwblachildentry_recall.wwbla"))
	    {   
	    	 try {
				    PrintWriter out = response.getWriter();
					String dtlId = request.getParameter("dtlId");
					CommonMessage.debugMsg("keyid   keyid  :  "+dtlId);
					List<String []> wwblachildData  = wwblaService.WwblachildData(dtlId);
					
					
					out.print( JSONArray.fromCollection(wwblachildData));
	   			} catch (Exception e) {

				}		
	    }
		if (action.equals("WwblaModify_input.wwbla"))
		{
			
			RequestDispatcher rd=request.getRequestDispatcher("/pages/WwblaGrid.jsp");
		      rd.forward(request,response);
		      CommonMessage.debugMsg("responce123"+response);
	}	
		
		else if(action.equals("WwblaModify_getCol.wwbla"))
		{
			CommonMessage.debugMsg("dddd col");
			List<String[]> wwblaGrid = null;
			PrintWriter out = response.getWriter();
			//JSONObject jsonObject = new JSONObject();
		
			try {
			    
				//String refDocid = request.getParameter("refDocid");
				//String refDoctype = request.getParameter("refDoctype");
				CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
				//commonFilter.setRefdocid(refDoc
				//commonFilter.setDocType(refDoctype);
				
				String loginFlid = CommonFunctions.getLoginFlid(request);
				if (!UIUtils.isValidKeyId( commonFilter.getFlid()))
						commonFilter.setFlid(loginFlid);
				
			    wwblaGrid = wwblaService.getAllWwblaGrid(commonFilter);
				JqGridTableModel jqGridTableModel = new  JqGridTableModel();			
				GridColModel gridColModel = new GridColModel();			
				
				jqGridTableModel.setSortable(true);
				jqGridTableModel.setTableButton(true);
				jqGridTableModel.setEnableFilter(true);
				jqGridTableModel.setRowNumbers(true);
				gridColModel.setHeaderNum(1);//9
				String [] colHeader = wwblaGrid.get(1);
				String [] colHeaderHead = wwblaGrid.get(0);
				List<String[]> headers = new ArrayList<String[]>();
				headers.add(colHeader);
				JSONObject colmodel = UIUtils.getTableModel(headers,colHeaderHead,jqGridTableModel,gridColModel);
				
				CommonMessage.debugMsg(colHeader.length+"  Value   "+colHeader[colHeader.length-1]);
				colmodel.set("tableWidth", "100%%");
				colmodel.set("tableHeight", "80%%");
				httpSession.removeAttribute("WwblaColModel");
				httpSession.setAttribute("WwblaColModel", colmodel);
				out.println(colmodel);

			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else if(action.equals("WwblaModify_getData.wwbla"))
		{				
			try {
				
			//	//String refDocid = request.getParameter("refDocid");
				//String refDoctype = request.getParameter("refDoctype");
				
				CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
				
				//commonFilter.setRefdocid(refDocid);
				///commonFilter.setDocType(refDoctype);
				
				String loginFlid = CommonFunctions.getLoginFlid(request);
				if (!UIUtils.isValidKeyId( commonFilter.getFlid()))
						commonFilter.setFlid(loginFlid);
				
				List<String[]> fishGridList =  wwblaService.getAllWwblaGrid(commonFilter);

				PrintWriter out = response.getWriter();
				JSONObject fishGrid=UIUtils.convertToJqGridTableObject(fishGridList, request,2, 0,commonFilter.getTotalRecordCnt());
				// UIUtils.convertToJqGridTableObject(minOfMeetingList,request,2,0,commonFilter.getTotalRecordCnt());
  			 	out.println(fishGrid);  			 	
  			 	commonFilter.setViewClick('N');  			 	
  			 	httpSession.removeAttribute("WwblaCommonFilter");
  			 	httpSession.setAttribute("WwblaCommonFilter", commonFilter);
				
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if((action.equals("WwblaModify_getExcel.wwbla")))
		{
			httpSession = request.getSession(false);
			CommonFilter commonFilter = populateCommonFilter(request,"WwblaReportCommonFilter",false);
			JSONObject colmodel = (JSONObject) httpSession.getAttribute("WwblaColModel");
			colmodel.put("title","Wwbla");
            String format = ExcelUtils.getFormat(request);
			
			Workbook wb = wwblaService.getWwblaExcel(colmodel,format,commonFilter);
			ExcelUtils.writeToResponse(response, wb, "Wwbla", format);
			
		}
		/*
		 * else if (action.equals("WwblaView_input.wwbla")) { String keyid =
		 * request.getParameter("keyid"); String flid = request.getParameter("flid");
		 * 
		 * CommonMessage.debugMsg(keyid);
		 * 
		 * BdmTlWwblamst newBdmTlWwblamst=null;
		 * 
		 * if(UIUtils.isValidKeyId(keyid)){
		 * 
		 * newBdmTlWwblamst = wwblaService.getWwbla(keyid);
		 * if(!UIUtils.isValidKeyId(newBdmTlWwblamst.getWwblFlid()))
		 * newBdmTlWwblamst.setWwblProblem(flid);
		 * request.setAttribute("newBdmTlWwblamst", newBdmTlWwblamst);
		 * 
		 * httpSession.setAttribute("existBdmTlWwblamst ",newBdmTlWwblamst); }
		 * 
		 * RequestDispatcher rd=request.getRequestDispatcher("/pages/Wwbla.jsp");
		 * rd.forward(request,response); }
		 */
		else if (action.equals("WwblaView_input.wwbla"))
		{
		    String keyid = request.getParameter("keyid");
		    String flid = request.getParameter("flid");
		    String frmMode = request.getParameter("frmMode"); // Get the mode
		    String Status = request.getParameter("Status");   // Get status too
		    
		    CommonMessage.debugMsg(keyid);

		    BdmTlWwblamst newBdmTlWwblamst = null;

		    if(UIUtils.isValidKeyId(keyid)){
		        newBdmTlWwblamst = wwblaService.getWwbla(keyid);
		        if(!UIUtils.isValidKeyId(newBdmTlWwblamst.getWwblFlid()))
		            newBdmTlWwblamst.setWwblProblem(flid);
		        
		        request.setAttribute("newBdmTlWwblamst", newBdmTlWwblamst);
		        httpSession.setAttribute("existBdmTlWwblamst", newBdmTlWwblamst);
		    }
		    
		    // SET THE MODE AND STATUS IN REQUEST ATTRIBUTES
		    request.setAttribute("frmMode", frmMode);
		    request.setAttribute("Status", Status);
		    
		    RequestDispatcher rd = request.getRequestDispatcher("/pages/Wwbla.jsp");
		    rd.forward(request, response);
		}
		else if( action.equals("WwblaView_delete.wwbla") )
			{	
				FishBoneBean fishBoneBean = new FishBoneBean();
				deleteWwbla(request,response);
			}
		
		
		/*else if( action.equals("wwbla_save.wwbla") )
		{	
			WwblaBean wwblaBean = (WwblaBean)httpSession.getAttribute("WwblaBean");
	        saveWwbla(request,response);
		}
		*/
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}	
		
	}
	

private void deleteWwbla(HttpServletRequest request,HttpServletResponse response) throws Exception {
	 UIUtils.displayRequestParamsValue(request);
   	HttpSession httpSession = request.getSession(false);    	
   	AdmTlUsermst user = UIUtils.getLoginUser(request);
   	ServletOutputStream out = response.getOutputStream();
   	
   	try
		{
   	if(httpSession !=null && user !=null)
		{
   		BdmTlWwblamst newBdmTlWwblamst = new BdmTlWwblamst();
   		newBdmTlWwblamst=(BdmTlWwblamst)UIUtils.setBeanProperties((Object)newBdmTlWwblamst,request);
			//JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst=(JhaTlFiveSAuditareamst)httpSession.getAttribute("existJhaTlFiveSAuditareamst");
			CommonMessage.debugMsg("newBdmTlWwblamst.getwwblKeyid()  "+newBdmTlWwblamst.getWwblKeyid());
			
			if(UIUtils.isValidKeyId(newBdmTlWwblamst.getWwblKeyid())){
				newBdmTlWwblamst=wwblaService.delete(newBdmTlWwblamst);
				httpSession.removeAttribute("JhaTlFiveSAuditareamst"+newBdmTlWwblamst.getWwblKeyid());
			}
			CommonMessage.debugMsg("ddddddd");
			JSONObject successData=new JSONObject();
   		JSONObject Auditdatadelete=new JSONObject();
   		CommonMessage.debugMsg("ddddddd");
   		String savemsg;
   	   if( newBdmTlWwblamst.getWwblKeyid()==null )
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

	private void deleteWWBLAChildEntry(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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
    		BdmTlWwbladtl newBdmTlWwbladtl = new BdmTlWwbladtl();
    		newBdmTlWwbladtl=(BdmTlWwbladtl)UIUtils.setBeanProperties((Object)newBdmTlWwbladtl,request);
			//JhaTlFiveSAuditareamst existJhaTlFiveSAuditareamst=(JhaTlFiveSAuditareamst)httpSession.getAttribute("existJhaTlFiveSAuditareamst");
			CommonMessage.debugMsg("newGenTlFishbonedtl.getFvasKeyid()  "+newBdmTlWwbladtl.getWwbdKeyid());
			newBdmTlWwbladtl.setWwbdKeyid(DtlId);
			if(UIUtils.isValidKeyId(newBdmTlWwbladtl.getWwbdKeyid())){
				
				newBdmTlWwbladtl=wwblaService.deleteWWBLAChildEntry(newBdmTlWwbladtl);
				httpSession.removeAttribute("GenTlFishbonedtl"+newBdmTlWwbladtl.getWwbdKeyid());
				
			
			}
		
			
			JSONObject successData=new JSONObject();
    		JSONObject Fishbonedtldelete=new JSONObject();
    		String savemsg;
    	   if( newBdmTlWwbladtl.getWwbdKeyid()==null )
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




	private void saveWWBLAChildEntry(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		

		// TODO Auto-generated method stub
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	 try
         {
 	    	if( httpSession != null && user != null)
 	    	{	
 	    		
 	    		
 	    		BdmTlWwbladtl existBdmTlWwbladtl=(BdmTlWwbladtl)httpSession.getAttribute("BdmTlWwblamst");
 	    		BdmTlWwbladtl newBdmTlWwbladtl = new BdmTlWwbladtl();
 	    		newBdmTlWwbladtl=(BdmTlWwbladtl)UIUtils.setBeanProperties((Object)newBdmTlWwbladtl,request);
 	    		newBdmTlWwbladtl.setWwbdCreatedby(user.getUsrm_ccno());
 	    		
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
 	    		String parenttxt=request.getParameter("parenttxt");
 	    		String Skilltype=request.getParameter("Skilltype");
 	    		String actionplan=request.getParameter("actionplan");
 	    		CommonMessage.debugMsg("action plan:"  +actionplan);
 	            CommonMessage.debugMsg("Skilltype/Responsibility: " + Skilltype);
 	    		CommonMessage.debugMsg("mparentid"+ParentId);
 	    		CommonMessage.debugMsg("mMasterid"+Masterid);
 	    		
 	    	    CommonMessage.debugMsg(" Detailid :: "+Detailid+" dtlid :: "+detlid+" Editval :: "+Editval);
 	    	    CommonMessage.debugMsg(" ParentId :: "+ParentId+" Masterid :: "+Masterid);
 	    	    CommonMessage.debugMsg(" dispCode :: "+dispCode+" OrderNo :: "+OrderNo);
 	    		
 	    	   newBdmTlWwbladtl.setWwbdWwblKeyid(Masterid);
 	    	   
 	    	  if(Skilltype != null && !Skilltype.trim().isEmpty()) {
 	                newBdmTlWwbladtl.setWwbdResponsiblity(Skilltype);
 	                CommonMessage.debugMsg(" Responsibility set :: "+Skilltype);
 	            }
 	    	  if (actionplan != null && !actionplan.trim().isEmpty()) {
 	    		  newBdmTlWwbladtl.setWwbdReoccur(actionplan);
 	    		  CommonMessage.debugMsg("actionplan set::"+actionplan);
 	    	  }
 	    		
 	    	  if("detlid".equals(detlid))  
 	    		{
	 	    		CommonMessage.debugMsg(" Inside IF ");			
	 	    		newBdmTlWwbladtl.setWwbdParentid(Detailid);
	 	    		
 	    		}else if("Smelvl".equals(detlid)){
 	    			if("level".equals(level)){
	 	    			CommonMessage.debugMsg(" Inside ELSE :: IF ");
	 	    			newBdmTlWwbladtl.setWwbdParentid("WWBLA001");
	 	    			//
 	    			}else
 	    			{
 	    				CommonMessage.debugMsg(" Inside ELSE :: ELSE :: Detail Id "+ParentId);
 	    				newBdmTlWwbladtl.setWwbdParentid(ParentId);
 	    				//fishBoneBean.setFormModes("CheckValidate");
 	    				CommonMessage.debugMsg(" Inside ELSE :: ELSE "+newBdmTlWwbladtl.getWwbdParentid());
 	    			
 	    			}
 	    		}
 	    		
 	    		if("Editval".equals(Editval))
 	    		{
 	    			CommonMessage.debugMsg("  Editval  :: "+Detailid+" parenttxt "+parenttxt);
 	    			newBdmTlWwbladtl.setWwbdParentid(Detailid);
 	    			newBdmTlWwbladtl.setWwblParent(parenttxt);
 	    			CommonMessage.debugMsg("  Editval  :: "+newBdmTlWwbladtl.getWwbdParentid());
 	    		}
 	    		//newGenTlFishbonedtl.setFisd
 	    		
 	    		
 	    	    CommonMessage.debugMsg(" levelno :: "+levelno+" OrderNo :: "+OrderNo+" dispCode :: "+dispCode+" ParentId :: "+ParentId+" Masterid :: "+Masterid);
 	    		
 	    		CommonMessage.debugMsg(" Inside Save Action :: "+openactnpln);
 	    		
 	    		boolean insert = true;
 	    		String msgPropertyIdnt;
 				if( newBdmTlWwbladtl.getWwbdKeyid() == null )
 				 {							
 					CommonMessage.debugMsg("KeyId is Null :: Before " );
 					existBdmTlWwbladtl =	wwblaService.createChildEntry(newBdmTlWwbladtl,existBdmTlWwbladtl,Editval);
 					msgPropertyIdnt = "success-save";
 					CommonMessage.debugMsg("KeyId is Null :: After " );
 				 }	
 				else
 				  {
 					CommonMessage.debugMsg("Update function :: Before ");	
 					existBdmTlWwbladtl = wwblaService.updateChildEntry(newBdmTlWwbladtl,existBdmTlWwbladtl);
 					insert = false;
 					msgPropertyIdnt = "success-update";
 					CommonMessage.debugMsg("Update function :: After ");	
 				  }	
 				
 				JSONObject successData = new JSONObject();	
 				JSONObject returnData = new JSONObject();
 								
 				returnData.put("formClear",false);
 				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
 				returnData.put("successData",successData);
 				returnData.put("Parentid", existBdmTlWwbladtl.getWwbdParentid());
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




	private void searchWwbla(HttpServletRequest request, HttpServletResponse response,HttpSession httpSession,PrintWriter out  ) throws ValidationExceptions,BusinessApplicationExceptions, Exception{		
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
			searchList = wwblaService.getSearchNode(currentSearchStr,"");
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
			
		private void saveWwbla(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String keyid = request.getParameter("wwblKeyid");
    	
    			
        try
        {
	    	if( httpSession != null && user != null)
	    	{
	    		//String LopcId = request.getParameter("LopcId");
	    		
	    		BdmTlWwblamst existBdmTlWwblamst=(BdmTlWwblamst)httpSession.getAttribute("BdmTlWwblamst");	    		 
	    		BdmTlWwblamst newBdmTlWwblamst = new BdmTlWwblamst();
	    		newBdmTlWwblamst=(BdmTlWwblamst)UIUtils.setBeanProperties((Object)newBdmTlWwblamst,request);
	    		
	    		CommonMessage.debugMsg("Keyid "+newBdmTlWwblamst.getWwblKeyid()+"Keyid ***********");
	    		
	       

	    		BdmTlWwbladtl newBdmTlWwbladtl = new BdmTlWwbladtl();
	    		//newBdmTlWwblamst.setFismCreatedby(user.getUsrm_ccno());
	    		newBdmTlWwblamst.setWwblCreatedby(user.getUsrm_ccno());
	    		String flid=request.getParameter("flid");
	    		
				if(UIUtils.isValidKeyId(flid))
	    			newBdmTlWwblamst.setWwblFlid(flid);
	    		CommonMessage.debugMsg("Wwbla flid:"+newBdmTlWwblamst.getWwblFlid());
	    		  String openactnpln = request.getParameter("openactnpln");
	    		CommonMessage.debugMsg(" Inside Save Action :: "+openactnpln);
	    		
	    		boolean insert = true;
	    		String msgPropertyIdnt;
				CommonMessage.debugMsg(newBdmTlWwblamst.getWwblKeyid()+"Value of Keyid");
				if( newBdmTlWwblamst.getWwblKeyid() == null )
				 {							
					CommonMessage.debugMsg("KeyId is Null :: Before " );
					//newBdmTlWwblamst.setWwblLopcId(LopcId);
					existBdmTlWwblamst =	wwblaService.create(newBdmTlWwblamst,existBdmTlWwblamst);
					msgPropertyIdnt = "success-save";
					CommonMessage.debugMsg("KeyId is Null :: After " );
					
				 }
	    	
				else
				  {
					CommonMessage.debugMsg("Update function :: Before ");	
					
					 existBdmTlWwblamst = wwblaService.update(newBdmTlWwblamst,existBdmTlWwblamst);
					 
					insert = false;
					 msgPropertyIdnt = "success-update";
					CommonMessage.debugMsg("Update function :: After ");	
				  }	
				
				JSONObject successData = new JSONObject();	
				JSONObject returnData = new JSONObject();
								
				returnData.put("formClear",false);
				//CommonMessage.debugMsg(" WWBLA KEYID "+existBdmTlWwblamst.getWwblKeyid());
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));	 
				
				
				returnData.put("Mstkeyid", existBdmTlWwblamst.getWwblKeyid());
				CommonMessage.debugMsg("Key Id after Save "+existBdmTlWwblamst.getWwblKeyid());
				returnData.put("successData",successData);
				
				CommonMessage.debugMsg("Mode :"+returnData.toString());
				out.print(returnData.toString());
				out.close();	
	    	}
	    }
        catch (ValidationExceptions e) 
        {
				CommonMessage.debugMsg("ValidationExceptions");
				e.printStackTrace();
				net.sf.json.JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"WwblaValidation");
				//out.print(errMessage.toString());							    	
        
        }catch(Exception e)//FishBoneValidation
	    {   
	        e.printStackTrace();
	        
	    }
		}
		
		
        private void LoadWwblaTree(HttpServletRequest request, HttpServletResponse response,PrintWriter out) throws IOException{
    		
        	CommonMessage.debugMsg("chk in loadwwblatree in serv");
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
    		String mechanism = request.getParameter("mechanism");
    		String phenomena = request.getParameter("phenomena");
    		
    		CommonMessage.debugMsg(" Problem :: "+Problem);
    		
    		id=request.getParameter("id");
    		
    		/*String iddtl=request.getParameter("iddtl");
    		if("iddtl".equals(iddtl)){CommonMessage.debugMsg(" Inside :: ");
    			id=id+1;
    		}
    		*/
    		
    		if(CommonFunctions.isValidKeyId(parentNumber))
    			parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
    		
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
    			   
    			   if(!UIUtils.isValidKeyId(Problem) && !UIUtils.isValidKeyId(phenomena) && !UIUtils.isValidKeyId(mechanism))
    			       dispCode = UIUtils.isValidKeyId(dispCode)?dispCode:"Problem/Phenomena/Mechanism";
    			   else
    				   dispCode = Problem+"/"+phenomena+"/"+mechanism;
    			  
    	    	   JSONObject jSONObject = new JSONObject();
        		   JSONObject data = new JSONObject();
        		   JSONObject jsonAttr = new JSONObject();
                   JSONObject metadata = new JSONObject();
                  
                   jsonAttr.put("id", "WWBLA001");                         
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
    	    		BdmTlWwbladtl bdmTlWwbladtl = new BdmTlWwbladtl();	 
    	    		BdmTlWwblamst bdmTlWwblamst = new BdmTlWwblamst();	 
        			List <BdmTlWwbladtl> locnList=null;    			
        		
        			CommonMessage.debugMsg("ID : "+id);
        			if(CommonFunctions.isValidKeyId(id)){	    				
        				bdmTlWwbladtl.setWwbdParentid(id); 	
        			}
        			if(CommonFunctions.isValidKeyId(levelno)){	    				
        				bdmTlWwbladtl.setWwbdLevelno(levelno); 	
        			}
        			if(UIUtils.isValidKeyId(elementType))
        				bdmTlWwblamst.setWwblKeyid(elementType);
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
        				bdmTlWwblamst.setWwblFlid(UIUtils.isValidKeyId(lineId)?lineId:parentNumber);
        				locnList = wwblaService.getWwblaValues(bdmTlWwbladtl,bdmTlWwblamst,id,masterId);
        				CommonMessage.debugMsg(" locnList.size()>0    "+locnList.size());
        				if(locnList.size()>0){
    	    				for(int i=0; i<locnList.size(); i++){
    	    					CommonMessage.debugMsg(" masterId 6 :: ");
    	    					JSONObject jSONObject = new JSONObject();
    	            			JSONObject data = new JSONObject();
    	            			JSONObject jsonAttr = new JSONObject();
    	    	                JSONObject metadata = new JSONObject();
    	    	                CommonMessage.debugMsg("ParentId"+locnList.get(i).getWwbdParentid());
    	    	                jsonAttr.put("id",locnList.get(i).getWwbdKeyid());
    	    	                jsonAttr.put("displayCode", locnList.get(i).getWwbdCountermeasure());
    	    	                jsonAttr.put("originalId", locnList.get(i).getWwbdKeyid());
    	    		            jsonAttr.put("elementId", locnList.get(i).getWwbdKeyid());
    	    	                jsonAttr.put("elementType",masterId);
    	    	                jsonAttr.put("parentId", locnList.get(i).getWwbdParentid());
    	    	                jsonAttr.put("OrderNo",locnList.get(i).getWwbdOrderno());
    	    	                jsonAttr.put("LevelNo", locnList.get(i).getWwbdLevelno());
    	    	                jsonAttr.put("title","Wwbla");
    	    	                jsonAttr.put("href", "#");
    	    	                
    	    	                //id,originalId,elementId,parentId,parentId,elementType,displayCode,OrderNo,LevelNo,href,title
    	    	                
    	            			data.put("title", locnList.get(i).getWwbdCountermeasure());
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
		}
		


	




	
		
	

	
	