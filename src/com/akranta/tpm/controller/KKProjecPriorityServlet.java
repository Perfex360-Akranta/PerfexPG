package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.JqGridColModel;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlDmcprojectprioritydtl;
import com.akranta.tpm.model.KznTlDmcprojectprioritymst;
import com.akranta.tpm.model.KznTlKkprojectprioritydtl;
import com.akranta.tpm.model.KznTlKkprojectprioritymst;
import com.akranta.tpm.service.KKProjecPriorityService;
import com.akranta.tpm.service.impl.KKProjectPriorityServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;


public class KKProjecPriorityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	KKProjecPriorityService kkProjecPriorityService;
	public KKProjecPriorityServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request,HttpServletResponse response) throws Exception {

		String action = UIUtils.getActionPart(request);
		kkProjecPriorityService = (KKProjectPriorityServiceImpl) UIUtils.getServiceObject(request, "KKProjectPriorityServiceImpl");
		HttpSession httpSession = request.getSession(false);
		kkProjecPriorityService.KKProjectPriorityServiceImplJwt((String)(httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
		AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		if (action.equals("kkprojectpriority_input.kkpp")) {
			String checkList = request.getParameter("checkList");
			List<String[]> getKKPGrid = null;
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			String list="";
			getKKPGrid = kkProjecPriorityService.getAllProjectPrioritisat(commonFilter);
			for(int i=0;i<getKKPGrid.get(2).length;i++){
				list = list+","+getKKPGrid.get(3)[i];
				request.setAttribute("kkk", list);
			}
			request.setAttribute("kkklength", getKKPGrid.get(2).length);
			request.setAttribute("approved", user.getUsrm_ccno());
			request.setAttribute("fild", flid);
			//CommonMessage.debugMsg("flid: "+flid);
			request.setAttribute("checkList", checkList);
			commonFilter =null;
			UIUtils.forwardRequest(request, response, "/pages/KK/kkProjecPriorityReport.jsp");
		}
		if (action.equals("dmckkprojectpriority_input.kkpp")) {
			String checkList = request.getParameter("checkList");
			List<String[]> getKKPGrid = null;
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = new CommonFilter();
			commonFilter.setFlid(flid);
			String list="";
			getKKPGrid = kkProjecPriorityService.getAllProjectPrioritisat(commonFilter);
			for(int i=0;i<getKKPGrid.get(2).length;i++){
				list = list+","+getKKPGrid.get(3)[i];
				request.setAttribute("kkk", list);
			}
			request.setAttribute("kkklength", getKKPGrid.get(2).length);
			request.setAttribute("approved", user.getUsrm_ccno());
			request.setAttribute("fild", flid);
			//CommonMessage.debugMsg("flid: "+flid);
			request.setAttribute("checkList", checkList);
			commonFilter =null;
			UIUtils.forwardRequest(request, response, "/pages/KK/dmckkProjectPriorityReport.jsp");
		}
		else if(action.equals("kkprojectpriority_save.kkpp")){
			savepriority(request,response);
		}
		else if(action.equals("dmckkprojectpriority_save.kkpp")){
			savedmcpriority(request,response);
		}
		else if(action.equals("functionalLoc.kkpp"))
		{
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
			//fact// functLocFieldNameBean.setFactory("cmbFact");
			functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(true);
			functLocFieldNameBean.setLocnMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			String disableFuncLoc = request.getParameter("disableFuncLoc");
			FormModes formModes = FormModes.create;
			if("true".equals(disableFuncLoc)){
				functLocFieldNameBean.setCompany("cmbComp");
				//fact// functLocFieldNameBean.setFactory("cmbFact");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				//fact// functLocFieldNameBean.setFactDisable(true);
				functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				//fact// functLocFieldNameBean.setFactDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				formModes = FormModes.create;
			}
			
				
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);
			
			
			
			
			
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes  );
			
		}
		else if(action.equals("kkprojectpriority_delete.kkpp")){
			deletepriority(request,response);
		}
		else if (action.equals("kkprojectpriority_getCol.kkpp")) {
			PrintWriter out = response.getWriter();

			CommonFilter commonFilter =  null;
			commonFilter = populateCommonFilter(request,
					"KKCommonFilter", true);
			 String waveNo=request.getParameter("wave");
			 commonFilter.setColVal(waveNo);
				String projetname=request.getParameter("pronme");
				commonFilter.setParamtype(projetname);
			JSONObject jsonObject = new JSONObject();
			List<String[]> getKKPGrid = null;			
			try {
				//getKKPGrid = kkProjecPriorityService.getAllProjectPrioritisat(null);
				getKKPGrid = kkProjecPriorityService.getAllProjectPrioritisat(commonFilter);
				jsonObject = getTableModelForKKProject(getKKPGrid);
				out.println(jsonObject);
			//	commonFilter = null;
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if (action.equals("dmckkprojectpriority_getCol.kkpp")) {
			PrintWriter out = response.getWriter();
			String flid = request.getParameter("flid");
			CommonFilter commonFilter = new CommonFilter();
			FilterValues.getCommonFilters(request, commonFilter);
			JSONObject jsonObject = new JSONObject();
			List<String[]> getKKPGrid = null;
			
			commonFilter.setFlid(flid);			
			try {
				getKKPGrid = kkProjecPriorityService.getAlldmcProjectPrioritisat(commonFilter);
				jsonObject = getdmcTableModelForKKProject(getKKPGrid);
				out.println(jsonObject);
			//	commonFilter = null;
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		else if (action.equals("kkprojectpriority_getData.kkpp")) {

			try {
				CommonFilter commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				    String waveNo=request.getParameter("wave");
				    commonFilter.setColVal(waveNo);
					String projetname=request.getParameter("pronme");
					commonFilter.setParamtype(projetname);
						
				List<String[]> kkproject = kkProjecPriorityService.getAllProjectPrioritisat(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject kkprojectgrid = UIUtils.convertToJqGridTableObject(kkproject, request, 4, 0,commonFilter.getTotalRecordCnt());
				commonFilter = null;
				out.println(kkprojectgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
		else if (action.equals("dmckkprojectpriority_getData.kkpp")) {

			try {
				CommonFilter commonFilter = new CommonFilter();
				FilterValues.getCommonFilters(request, commonFilter);
				String flid= request.getParameter("flid");
				if(!UIUtils.isValidKeyId(flid)){
					flid=(String) httpSession.getAttribute("loginFlid");
					CommonMessage.debugMsg("flid "+flid);
				}
				commonFilter.setFlid(flid);
				List<String[]> kkproject = kkProjecPriorityService.getAlldmcProjectPrioritisat(commonFilter);
				PrintWriter out = response.getWriter();
				JSONObject kkprojectgrid = UIUtils.convertToJqGridTableObject(kkproject, request, 4, 0,commonFilter.getTotalRecordCnt());
				commonFilter = null;
				out.println(kkprojectgrid);
			} catch (Exception e) {
				CommonMessage.debugMsg(e.getMessage());
			}

		}
	}

	

	private void deletepriority(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	try
		{ 
    	if( httpSession != null && user != null)
    	{	
    		
    		KznTlKkprojectprioritymst existKznTlKkprojectprioritymst= (KznTlKkprojectprioritymst)httpSession.getAttribute("newEntTlAssessmentmst"); 
    		KznTlKkprojectprioritymst newKznTlKkprojectprioritymst = new KznTlKkprojectprioritymst();//mas
    		 String MasterProject =  request.getParameter("DeleteProject");
    		 CommonMessage.debugMsg("TrainingDetails: "+MasterProject);
  		  // master
             List<KznTlKkprojectprioritydtl> lstKznTlKkprojectprioritydtl = null;
             
             List<KznTlKkprojectprioritymst> lstKznTlKkprojectprioritymst = null;
  		  JSONArray jsonmasterArray = null;
  		  JSONArray jsondetailArray = null;
  		  if(UIUtils.isValidKeyId(MasterProject)){ //master json
  				if( MasterProject != null && ! MasterProject.isEmpty())
  	    		{
  					jsonmasterArray = JSONArray.fromString(MasterProject);
  					lstKznTlKkprojectprioritymst=(List<KznTlKkprojectprioritymst>)UIUtils.convertJSONArrToList(newKznTlKkprojectprioritymst, jsonmasterArray);
  	    		}
  			   if(lstKznTlKkprojectprioritymst!=null)
  			   {
  				  
  				   newKznTlKkprojectprioritymst.setMaster(lstKznTlKkprojectprioritymst);
  					CommonMessage.debugMsg("lstEntTlAssessmentmst.size(): "+lstKznTlKkprojectprioritymst.size());
  				}
  		  }
  		  			JSONObject successData=new JSONObject();
  					JSONObject trainingAttEffSuccessMsg=new JSONObject();
  					String savemsg;		
  					existKznTlKkprojectprioritymst=kkProjecPriorityService.delete(newKznTlKkprojectprioritymst, existKznTlKkprojectprioritymst);
  					 savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-delete");
  					 successData.put("msg", savemsg);
  					 trainingAttEffSuccessMsg.put("successData", successData);
  		    		 out.print(trainingAttEffSuccessMsg.toString());
  		  				
  	    	}				//newEntTlAssessmentmst =entTlAssessmentmstService.create(newEntTlAssessmentmst,existEntTlAssessmentmst,entTlAssessmentmstBean);
  				
  				}catch (ValidationExceptions e)
  				{
  						JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"TrainingAtten");
  		                 out.print(errMessage.toString());
  	    	  }
  				catch(Exception e){
  						CommonMessage.debugMsg("Error Msg:" + e.getMessage());
  						JSONObject err = new JSONObject();
  						err.put("tpmException", "Data Not deleted");
  						out.print(err.toString());
  				}
    		
    	}
		


	private void savepriority(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String rowId = request.getParameter("rowid");
    	if( httpSession != null && user != null)
    	{	
    	try
		{ 
    		
    		KznTlKkprojectprioritymst existKznTlKkprojectprioritymst= (KznTlKkprojectprioritymst)httpSession.getAttribute("newEntTlAssessmentmst"); 
    		KznTlKkprojectprioritymst newKznTlKkprojectprioritymst = new KznTlKkprojectprioritymst();//mas
    		
    		String flid = request.getParameter("txtKppmFlid");
    		KznTlKkprojectprioritydtl newKznTlKkprojectprioritydtl = new KznTlKkprojectprioritydtl();
    		
		    String MasterProject =  request.getParameter("MasterProject");
		    String DetailProject =  request.getParameter("DetailProject");
		    String define = request.getParameter("define");
		    
		   //CommonMessage.debugMsg("TrainingDetails: "+MasterProject);
		  // CommonMessage.debugMsg("MASTERDATA: "+DetailProject);   // master
           List<KznTlKkprojectprioritydtl> lstKznTlKkprojectprioritydtl = null;
           
           List<KznTlKkprojectprioritymst> lstKznTlKkprojectprioritymst = null;
		  JSONArray jsonmasterArray = null;
		  JSONArray jsondetailArray = null;
		  if(UIUtils.isValidKeyId(MasterProject)){ //master json
				if( MasterProject != null && ! MasterProject.isEmpty())
	    		{
					jsonmasterArray = JSONArray.fromString(MasterProject);
					lstKznTlKkprojectprioritymst=(List<KznTlKkprojectprioritymst>)UIUtils.convertJSONArrToList(newKznTlKkprojectprioritymst, jsonmasterArray);
	    		}
			   if(lstKznTlKkprojectprioritymst!=null)
			   {
				   newKznTlKkprojectprioritymst.setKppmCreatedby(user.getUsrm_keyid());
				   newKznTlKkprojectprioritymst.setMaster(lstKznTlKkprojectprioritymst);
				   for(int i=0;i<newKznTlKkprojectprioritymst.getMaster().size();i++){
					   if(newKznTlKkprojectprioritymst.getMaster().get(i).getKppmRank()==null || newKznTlKkprojectprioritymst.getMaster().get(i).getKppmRank()==""){
						   throw new Exception("Enter the Rank to save");
					   }
				   }
				}
		  }
		  /*List for detail**/
		  if(UIUtils.isValidKeyId(DetailProject)){ //detail json
			if( DetailProject != null && ! DetailProject.isEmpty())
    		{
				jsondetailArray = JSONArray.fromString(DetailProject);
				lstKznTlKkprojectprioritydtl=(List<KznTlKkprojectprioritydtl>)UIUtils.convertJSONArrToList(newKznTlKkprojectprioritydtl, jsondetailArray);
    		}

		   if(lstKznTlKkprojectprioritydtl!=null)
		   {
			   newKznTlKkprojectprioritymst.setDetail(lstKznTlKkprojectprioritydtl);			   
			   for(int i=0;i<newKznTlKkprojectprioritymst.getDetail().size();i++){
			   if(newKznTlKkprojectprioritymst.getDetail().get(i).getKppdScore()==null || newKznTlKkprojectprioritymst.getDetail().get(i).getKppdScore()==""){
				   throw new Exception("Enter the Score to save");
			   }
			   }
			}
		  }
		  if(flid==null||flid==""){
			  throw new Exception("Select the functional Location");
		  }
		  if(MasterProject==null||MasterProject==""){
				throw new Exception("Select the project to save");
			}
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
			String savemsg;		
		    existKznTlKkprojectprioritymst=kkProjecPriorityService.create(newKznTlKkprojectprioritymst, existKznTlKkprojectprioritymst);
			savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");			
			successData.put("msg", savemsg);
			if (UIUtils.isValidKeyId(define)){
				successData.put("define", define);
				if(define.equals("define")){
					returnData.put("formClear",false);
				}
			}
			returnData.put("successData", successData);
	    	out.print(returnData.toString());
    				
    	}catch (ValidationExceptions e)
			{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"kkprojectvalidation");
					if(rowId != null)
						errMessage.put("rowId",rowId);
	                 out.print(errMessage.toString());
    	  }
			catch(Exception e){
				JSONObject err = new JSONObject();
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					if(e.getMessage()== null){
						err.put("tpmException", "Data not saved");
					}else{
						err.put("tpmException", e.getMessage());
						
						
					}
				
					if(rowId != null)
						err.put("rowId",rowId);
					out.print(err.toString());
			}
    	}
			}
	
	private void savedmcpriority(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	String rowId = request.getParameter("rowid");
    	if( httpSession != null && user != null)
    	{	
    	try
		{ 
    		
    		KznTlDmcprojectprioritymst existKznTlDmcprojectprioritymst= (KznTlDmcprojectprioritymst)httpSession.getAttribute("newEntTlAssessmentmst"); 
    		KznTlDmcprojectprioritymst newKznTlDmcprojectprioritymst = new KznTlDmcprojectprioritymst();//mas
    		
    		String flid = request.getParameter("txtDmpmFlid");
    		KznTlDmcprojectprioritydtl newKznTlDmcprojectprioritydtl = new KznTlDmcprojectprioritydtl();
    		
		    String MasterProject =  request.getParameter("MasterProject");
		    String DetailProject =  request.getParameter("DetailProject");
		    String define = request.getParameter("define");
		    
		   //CommonMessage.debugMsg("TrainingDetails: "+MasterProject);
		  // CommonMessage.debugMsg("MASTERDATA: "+DetailProject);   // master
           List<KznTlDmcprojectprioritydtl> lstKznTlDmcprojectprioritydtl = null;
           
           List<KznTlDmcprojectprioritymst> lstKznTlDmcprojectprioritymst = null;
		  JSONArray jsonmasterArray = null;
		  JSONArray jsondetailArray = null;
		  if(UIUtils.isValidKeyId(MasterProject)){ //master json
				if( MasterProject != null && ! MasterProject.isEmpty())
	    		{
					jsonmasterArray = JSONArray.fromString(MasterProject);
					lstKznTlDmcprojectprioritymst=(List<KznTlDmcprojectprioritymst>)UIUtils.convertJSONArrToList(newKznTlDmcprojectprioritymst, jsonmasterArray);
	    		}
			   if(lstKznTlDmcprojectprioritymst!=null)
			   {
				   newKznTlDmcprojectprioritymst.setDmpmCreatedby(user.getUsrm_keyid());
				   newKznTlDmcprojectprioritymst.setMaster(lstKznTlDmcprojectprioritymst);
				   for(int i=0;i<newKznTlDmcprojectprioritymst.getMaster().size();i++){
					   CommonMessage.debugMsg(newKznTlDmcprojectprioritymst.getMaster().get(i).getDmpmRank()+" gdsfdhfhsdjkfdsfbdbhdf");
					   if(newKznTlDmcprojectprioritymst.getMaster().get(i).getDmpmRank()==null || newKznTlDmcprojectprioritymst.getMaster().get(i).getDmpmRank()==""){
						   CommonMessage.debugMsg(newKznTlDmcprojectprioritymst.getMaster().get(i).getDmpmRank()+" gdsfdhfhsdjkfdsfbdbhdf2");

						   throw new Exception("Enter the Rank to save");
					   }
				   }
				}
		  }
		  /*List for detail**/
		  if(UIUtils.isValidKeyId(DetailProject)){ //detail json
			if( DetailProject != null && ! DetailProject.isEmpty())
    		{
				jsondetailArray = JSONArray.fromString(DetailProject);
				lstKznTlDmcprojectprioritydtl=(List<KznTlDmcprojectprioritydtl>)UIUtils.convertJSONArrToList(newKznTlDmcprojectprioritydtl, jsondetailArray);
    		}

		   if(lstKznTlDmcprojectprioritydtl!=null)
		   {
			   newKznTlDmcprojectprioritymst.setDetail(lstKznTlDmcprojectprioritydtl);			   
			   for(int i=0;i<newKznTlDmcprojectprioritymst.getDetail().size();i++){
			   if(newKznTlDmcprojectprioritymst.getDetail().get(i).getDmdlScore()==null || newKznTlDmcprojectprioritymst.getDetail().get(i).getDmdlScore()==""){
				   throw new Exception("Enter the Score to save");
			   }
			   }
			}
		  }
		  if(flid==null||flid==""){
			  throw new Exception("Select the functional Location");
		  }
		  if(MasterProject==null||MasterProject==""){
				throw new Exception("Select the project to save");
			}
			JSONObject successData=new JSONObject();
			JSONObject returnData=new JSONObject();
			String savemsg;		
		    existKznTlDmcprojectprioritymst=kkProjecPriorityService.createdmcpp(newKznTlDmcprojectprioritymst, existKznTlDmcprojectprioritymst);
			savemsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages", "success-save");			
			successData.put("msg", savemsg);
			if (UIUtils.isValidKeyId(define)){
				successData.put("define", define);
				if(define.equals("define")){
					returnData.put("formClear",false);
				}
			}
			returnData.put("successData", successData);
	    	out.print(returnData.toString());
    				
    	}catch (ValidationExceptions e)
			{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"kkprojectvalidation");
					if(rowId != null)
						errMessage.put("rowId",rowId);
	                 out.print(errMessage.toString());
    	  }
			catch(Exception e){
				JSONObject err = new JSONObject();
					CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					if(e.getMessage()== null){
						err.put("tpmException", "Data not saved");
					}else{
						err.put("tpmException", e.getMessage());
						
						
					}
				
					if(rowId != null)
						err.put("rowId",rowId);
					out.print(err.toString());
			}
    	}
			}
	
	
	private JSONObject getdmcTableModelForKKProject(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] row = headers.get(0);
		String[] colHeader = headers.get(1);
		String[] colHeader2 = headers.get(2);
		String[] colHeader3 = headers.get(3);
		String[] tempCol = new String[row.length];
	    jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader3);
		jqGridTableModel.setRowNumbers(true);
		//jqGridTableModel.setTableHeight(80);
		//jqGridTableModel.setTableWidth(500);
		int colLimit = colHeader.length-2;
		for (int i = 0; i < colHeader.length; i++) {

			tempCol[i]="";
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(jqGridColModel.getIndex());
			jqGridColModel.setWidth(100);
			
			jqGridColModel.setAlign("right");
			if(i>1&&i<colLimit){
				jqGridColModel.setFormatter("txtFormatter");
			}
				
			if (i == 7 || i == 6 ) {
				jqGridColModel.setWidth(70);
				//jqGridColModel.setAlign("right");
			}

			else if (i == 4 ) 
				jqGridColModel.setWidth(70);
			else if( i == 8) {
				jqGridColModel.setWidth(130);
			//	jqGridColModel.setAlign("right");
			}
			else if ( i == 5 ) {
				jqGridColModel.setWidth(150);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == 9) {
				jqGridColModel.setWidth( 50);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == 10) {
				jqGridColModel.setWidth(50);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == colLimit-1) {
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}

			else if(i == 0 ||i==colLimit||i==1||i==colLimit+1){
				jqGridColModel.setHidden(true);
			}
			else if (i == 2 ) {
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("center");
			}
			else if ( i == 3 ) {
				jqGridColModel.setWidth(320);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "60%%");
		tableModel.set("tableWidth", "109%%");
		return tableModel;

	}
	private CommonFilter populateCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew){
	HttpSession httpSession = request.getSession(false);
  		
  		CommonFilter commonFilter = (CommonFilter)httpSession.getAttribute(beanIdentifier);
  		if( commonFilter != null && ! createNew ){
  			FilterValues.setPaginationParams(request,commonFilter);
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter);
  		}	
  		else{
  			commonFilter =  new CommonFilter();
  			
  			commonFilter = 	FilterValues.getCommonFilters(request, commonFilter); // getFilterValues(request);
  			//commonFilter = 	FilterValues.getAbnRelatedFilters(request, commonFilter);
  			commonFilter.setViewClick('Y');
  			httpSession.removeAttribute(beanIdentifier);
  			httpSession.setAttribute(beanIdentifier, commonFilter);
  		}
  		
  		return commonFilter; 
	}
	private JSONObject getTableModelForKKProject(List<String[]> headers) {
		JqGridTableModel jqGridTableModel = new JqGridTableModel();
		String[] row = headers.get(0);
		String[] colHeader = headers.get(1);
		String[] colHeader2 = headers.get(2);
		String[] colHeader3 = headers.get(3);
		String[] tempCol = new String[row.length];
	    jqGridTableModel.getRowHeaders().add(tempCol);
		jqGridTableModel.getRowHeaders().add(colHeader);
		jqGridTableModel.getRowHeaders().add(colHeader2);
		jqGridTableModel.getRowHeaders().add(colHeader3);
		jqGridTableModel.setRowNumbers(true);
		jqGridTableModel.setEnableFilter(true);
		//jqGridTableModel.setTableHeight(80);
		//jqGridTableModel.setTableWidth(500);
		int colLimit = colHeader.length-2;
		for (int i = 0; i < colHeader.length; i++) {

			tempCol[i]="";
			JqGridColModel jqGridColModel = new JqGridColModel();
			jqGridColModel.setIndex(row[i].replaceAll(" ", ""));
			jqGridColModel.setName(jqGridColModel.getIndex());
			jqGridColModel.setWidth(100);
			
			jqGridColModel.setAlign("right");
			if(i>1&&i<colLimit){
				jqGridColModel.setFormatter("txtFormatter");
			}
				
			if (i == 7 || i == 6 ) {
				jqGridColModel.setWidth(70);
				//jqGridColModel.setAlign("right");
			}
			else if (i == 0 ) 
				jqGridColModel.setWidth(100);

			else if (i == 4 ) 
				jqGridColModel.setWidth(70);
			else if( i == 8) {
				jqGridColModel.setWidth(130);
			//	jqGridColModel.setAlign("right");
			}
			else if ( i == 5 ) {
				jqGridColModel.setWidth(150);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == 9) {
				jqGridColModel.setWidth( 50);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == 10) {
				jqGridColModel.setWidth(50);
			//	jqGridColModel.setAlign("right");
			}
			else if (i == colLimit-1) {
				jqGridColModel.setWidth(80);
				jqGridColModel.setAlign("center");
			}

			else if(i==colLimit||i==1||i==colLimit+1){
				jqGridColModel.setHidden(true);
			}
			else if (i == 2 ) {
				jqGridColModel.setWidth(40);
				jqGridColModel.setAlign("center");
			}
			else if ( i == 3 ) {
				jqGridColModel.setWidth(320);
				jqGridColModel.setAlign("left");
			}
			jqGridTableModel.getColModel().add(jqGridColModel);
		}
		JSONObject tableModel = UIUtils.getJqGridTableModel(jqGridTableModel);
		tableModel.set("tableHeight", "60%%");
		tableModel.set("tableWidth", "109%%");
		return tableModel;

	}

}
