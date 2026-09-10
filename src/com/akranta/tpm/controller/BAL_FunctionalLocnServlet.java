package com.akranta.tpm.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;

 
import net.sf.json.JSONObject;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_FunctLocFieldNameBean;
import com.akranta.tpm.bean.BAL_GenTlCellmstBean;
import com.akranta.tpm.bean.BAL_GenTlPbumstBean;
import com.akranta.tpm.bean.BAL_GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.BAL_GenTlCellmst;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSbumst;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.service.BAL_FunctionalLocnServices;
import com.akranta.tpm.service.impl.BAL_FunctionalLocnServicesImpl;
import com.akranta.tpm.service.impl.MasterTableConfigServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
/*Created By Suresh.K on Oct 10*/
import com.akranta.tpm.utils.ReqtParamNameConst;

public class BAL_FunctionalLocnServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8070953412956046800L;
	BAL_FunctionalLocnServices functionalLocnServices;

	public BAL_FunctionalLocnServlet()
	{
		super();	       
        /*try {
        	  functionalLocnServices = new FunctionalLocnServicesImpl();
        	} catch (Exception e) {
			g
		}
		*/
		//Constructor
	}	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 

protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws Exception {
        
    	HttpSession httpSession = request.getSession(false);
    	String action = UIUtils.getActionPart(request);
    	CommonFunctions.debugMsg("Action :"+action);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		  
		CommonFilter  commonFilter = new CommonFilter();
		String filter = request.getParameter("q");
		ComboFilter currentFilter = new ComboFilter();
		currentFilter.setCode(filter);
		currentFilter.setName(filter);
		List<ComboBox> comboList = new ArrayList<ComboBox>();
		//FunctionalLocnServicesImpl functionalLocnServicesImpl = new FunctionalLocnServicesImpl();
		try {
			functionalLocnServices = (BAL_FunctionalLocnServicesImpl)UIUtils.getServiceObject(request,"BAL_FunctionalLocnServicesImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
		
		
		if( action.equals("loadval.funlocn") )
		{	
			String elementId = null;
			String parentId = null;
			String elementType = null;		
			
			elementId=request.getParameter("elementId");
			parentId = request.getParameter("parentId");
			
			elementType = request.getParameter("elementType");
			
			elementId  = elementId.equals("0") ? "0" :elementId;
			String mode =  request.getParameter("mode");
			
	    	try {
	    		JSONArray jSONArray = new JSONArray();	    		
		    	if(request.getParameter("id").equals("0"))
		    	{
			    	   JSONObject jSONObject = new JSONObject();
		    		   JSONObject data = new JSONObject();
		    		   JSONObject jsonAttr = new JSONObject();
		               JSONObject metadata = new JSONObject();
		    		   jsonAttr.put("id", "FL001");
		    		   jsonAttr.put("originalId", "1");
		               jsonAttr.put("elementId", "1");
		               jsonAttr.put("parentId", "1");
		               jsonAttr.put("elementType", "FL");
		               jsonAttr.put("displayCode", "Functional Location");
		               jsonAttr.put("imgUrl",getImageUrl("FL"));
		               jsonAttr.put("href", "#");
		               data.put("title", "Functional Location");	        			
	        		   data.put("icon", "");
	        		   jSONObject.put("data",data);
	        		   jSONObject.put("attr", jsonAttr);
	        		   
	        		   if(request.getParameter("search_str") != null)
	        			   jSONObject.put("state","open");
	        		   else
	        			   jSONObject.put("state","closed");
	        		   metadata.put("id", "1");
	        		   jSONObject.put("metadata",metadata);
		               jSONObject.put("icon",getIconImage("FL"));
		               jsonAttr = null;
		               jSONObject.put("children","[{}]");
		               jSONArray.put(jSONObject);
		               jSONObject=null;		    		
		    	}
		    	else
		    	{
		    		if("1".equals(elementId) && ! "view".equals(mode) ){
		    			String userElId =  BAL_UIUtils.getUserLoginElementId(request);
		    			//if( userElId != null && userElId.indexOf("-") > 0 )
		    			//	elementId  =  userElId.substring(0,userElId.lastIndexOf("-"));
		    			elementId = userElId ;
		    			parentId = userElId;
		    			
		    			
		    		}
		    		
		    		
		    		FunctionalLocn functionalLocn = new FunctionalLocn();	        	
		        	functionalLocn.setElementId(elementId);
		        	functionalLocn.setParentId(parentId);
		        	functionalLocn.setElementType(elementType);
		        	List <FunctionalLocn> locnList = functionalLocnServices.getAllLocation(functionalLocn);
		        	for(int i=0; i<locnList.size(); i++){
		        		JSONObject jSONObject = new JSONObject();
	        			JSONObject data = new JSONObject();
	        			JSONObject jsonAttr = new JSONObject();
		                JSONObject metadata = new JSONObject();
		        		//CommonFunctions.debugMsg(locnList.get(i).getOriginalId());
		        		//CommonFunctions.debugMsg(locnList.get(i).getElementId());
		        		//CommonFunctions.debugMsg(locnList.get(i).getParentId());
		        		//CommonFunctions.debugMsg(locnList.get(i).getElementType());
		        		jsonAttr.put("id", locnList.get(i).getOriginalId().replace("/", "_"));
		                jsonAttr.put("originalId", locnList.get(i).getOriginalId());
		                jsonAttr.put("elementId", locnList.get(i).getElementId());
		                jsonAttr.put("parentId", locnList.get(i).getParentId());
		                jsonAttr.put("elementType", locnList.get(i).getElementType());
		                jsonAttr.put("displayCode", locnList.get(i).getDisplayCode());
		                jsonAttr.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
		                jsonAttr.put("title",UIUtils.getTitle(locnList.get(i).getOriginalId().substring(0,3)));
		                jsonAttr.put("href", "#");
	        			data.put("title", locnList.get(i).getDisplayCode());
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
		                jSONObject.put("icon",getIconImage(locnList.get(i).getElementType()));
			            //jSONObject.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
			                
			            //jSONObject.put("icon","../images/fav.png");
		                jsonAttr = null;
		                jSONObject.put("children","[{}]");
		                jSONArray.put(jSONObject);
		                jSONObject=null;
		        	}   
		    	}
		    	//CommonFunctions.debugMsg(jSONArray);
	           out.print(jSONArray);
        	   jSONArray=null;
	    	}catch(Exception e){
	           // CommonFunctions.debugMsg(e);
	            e.printStackTrace();
	        }
	        finally {
	            out.close();
	        }
	        
		}
		else if(action.equals("load_view.funlocn"))
		{
			UIUtils.removeCookie(response,"fnlnsearch_str");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionallocnview.jsp"); 
			rd.forward(request, response);
		}
		else if(action.equals("load_childImage.funlocn")){
			String elemType = request.getParameter("elemType");
			request.setAttribute("elemType", elemType)	;
			String dlgId = request.getParameter("dlgId");
			request.setAttribute("dlgId", dlgId);
			String dispCode = request.getParameter("dispCode");
			request.setAttribute("dispCode", dispCode);
			String elemId = request.getParameter("elemId");
			request.setAttribute("elemId", elemId);
			String width = request.getParameter("w");
			request.setAttribute("width", width);
			String height = request.getParameter("h");
			request.setAttribute("height", height);
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionalAllcoImage.jsp"); 
			rd.forward(request, response);
		}
		else if (action.equals("load_childPop.funlocn")){
			//&=C&dlgId=dlgAddChild&w=300&h=180&dispCode=undefined&elemId=undefined
            String elemType = request.getParameter("elemType");
			request.setAttribute("elemType", elemType)	;
			String dlgId = request.getParameter("dlgId");
			CommonFunctions.debugMsg("DialodId :"+dlgId);
			request.setAttribute("dlgId", dlgId);
			String dispCode = request.getParameter("dispCode");
			request.setAttribute("dispCode", dispCode);
			String elemId = request.getParameter("elemId");
			request.setAttribute("elemId", elemId);
			String width = request.getParameter("w");
			request.setAttribute("width", width);
			String height = request.getParameter("h");
			request.setAttribute("height", height);
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionalAllcoChild.jsp"); 
			rd.forward(request, response);
		}
	
		else if( action.equals("searchnode.funlocn") )
		{	
			Enumeration<String> params = request.getParameterNames() ;
			/*while(params.hasMoreElements() )
			{
				//CommonFunctions.debugMsg("params in SEARCH  " + params.nextElement());
				//CommonFunctions.debugMsg("params ========  " + request.getParameter(params.nextElement()));
			}*/
			
			
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
				searchList = functionalLocnServices.getSearchNode(currentSearchStr);
				httpSession.setAttribute("fnlnSearchList", searchList);
			}
			int searchCnt = Integer.parseInt(countStr);
			JSONArray jSONArray =null;
			if( searchCnt < searchList.size() ){
				String parentId = searchList.get(searchCnt)[0];
				//CommonFunctions.debugMsg(parentId);
				parentId = "#node_1-FL001-" + parentId.replaceAll("/", "_");
				//parentId = "-" + parentId.replaceAll("/", "_");
				//CommonFunctions.debugMsg(parentId);
				parentId = parentId.replaceAll("-", "-#");
				//parentId = "#node_1-#node_2" + parentId;
				//CommonFunctions.debugMsg(parentId);
				String[] searchNode = parentId.split("-");
				
				jSONArray = JSONArray.fromArray(searchNode);
			}
			else{
				searchCnt =-1;
			}
			
			Cookie searchStrCookie =  new Cookie("fnlnsearch_str",currentSearchStr);
			searchStrCookie.setMaxAge(60*60);
			response.addCookie(searchStrCookie);
			Cookie searchCntCookie =  new Cookie("fnlnsearch_str_cnt",(searchCnt+1)+"");
			searchCntCookie.setMaxAge(60*60);
			response.addCookie(searchCntCookie);
			
			out.println(jSONArray);
		}
		else if( action.equals("funcnLocn_getCol.funlocn") )
		{	
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.FuncnLocnAddColModel", "colModel"));
		}
		
		
		else if(action.equals("functionalLoc.funlocn"))
		{
			
	
			BAL_FunctLocFieldNameBean functLocFieldNameBean = new BAL_FunctLocFieldNameBean();
				CommonFunctions.debugMsg("Actionload999");
				functLocFieldNameBean.setCompany("cmbComp");
				//fact// functLocFieldNameBean.setFactory("cmbFact");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				CommonFunctions.debugMsg("5555555");
			    functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(false);
				functLocFieldNameBean.setCellDisable(false);
				functLocFieldNameBean.setMachDisable(false);
		
			
	
			
			FormModes formModes = FormModes.create;
			BAL_UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		
		else if( action.equals("funcnLocn_getData.funlocn") )
		{
			try {
				
				String elementType = request.getParameter("formField");
				String parentId = request.getParameter("elemType");
				CommonFunctions.debugMsg("ParentId :"+parentId);
				
				String elemFld = null;
				//String[] elmFldArr = elmType.split("-");
				String key = request.getParameter("key");
				CommonFunctions.debugMsg(parentId + " " + elementType);
				//CommonFunctions.debugMsg(elmFldArr.length);
				//CommonFunctions.debugMsg(elmFldArr[elmFldArr.length-1]);
				
				String rows = request.getParameter("rows");
	        	String page = request.getParameter("page");	
	        	
	           
       		    String start = "1";
       		    String end = "100";
       		    if(page.equals("1"))
       		    {
       		    	
       		    }
       		    else
       		    {
       		     int rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
        		 int rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
        		 start = Integer.toString(rowStart);
        		 end = Integer.toString(rowEnd);
       		    }
				//List<String []> parentList  = functionalLocnServices.getParentElem(elmType);
				List<String> childList = null;//getChildNodeElem(parentList,frmFld);

				//if(childList.size() > 0)				
				//	elemFld = frmFld;					
				//else
				//{
					if(elementType.equals("E"))
						elementType = "M";
					else if(elementType.equals("SC"))
						elementType = DrillLevelConstants.SC;
					else if(elementType.equals("ASM"))
						elementType = "A";
					else if(elementType.equals("S"))
						elementType = DrillLevelConstants.S;
					else if(elementType.equals("SUB"))
						elementType = DrillLevelConstants.SBU;
					//else
						//elemFld = elmFldArr[elmFldArr.length-1];
				//}
				
				//CommonFunctions.debugMsg(elmType + " FORMFLD : "+elemFld);
				//String totalCount = functionalLocnServices.getTotalCount(childList,elemFld);
				String totalCount = functionalLocnServices.getTotalCount(childList,elementType,parentId);
			//	CommonFunctions.debugMsg(" totalCount "  + totalCount );
				if( Integer.parseInt(totalCount) > 0 ){
					int totalRows = 200;
					 GridParams gridParams =(GridParams) httpSession.getAttribute("FuncnLocngridParams");
					 if( gridParams == null )
	       			  gridParams = new GridParams(); 
	       		  
					 FilterValues.populateGridParams(request,gridParams );
					  
	       		  	httpSession.removeAttribute("FuncnLocngridParams");
	       		  	httpSession.setAttribute("FuncnLocngridParams" ,gridParams);
					List<String []> childForParent = functionalLocnServices.getChildElem(childList,elementType,start,end,key,gridParams,parentId);
					if(UIUtils.isValidKeyId(totalCount))
						totalRows = Integer.parseInt(totalCount);
					
			//		CommonFunctions.debugMsg(" totalCount "  + totalCount );
					net.sf.json.JSONObject childForParentData = UIUtils.convertToJqGridTableObject(childForParent,request,0,1,totalRows);
						//CommonFunctions.debugMsg(childForParentData);
					httpSession.removeAttribute("FuncnLocngridParams");
					out.println(childForParentData);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("funcnLocn_save.funlocn") )
		{
			String funLocnGrid = request.getParameter("FunctionalLocnGrid");	    		
			CommonFunctions.debugMsg("Inside funLocn Save"+funLocnGrid);
		
			FunctionalLocn functionalLocn = new FunctionalLocn();	    	
	    	functionalLocn.setParentId(request.getParameter("hdnFuncCondition"));
	    	CommonFunctions.debugMsg("Inside funLocn Save hdn FUNCCondition :"+request.getParameter("hdnFuncCondition"));
	    	
	    	List<String> locnValues = setFunLocnModel(funLocnGrid);
	    	if(funLocnGrid.startsWith("MCH"))
	    	{
	    		functionalLocn=	functionalLocnServices.update(functionalLocn,locnValues);
	    	}
	    	else
	    	{
	    		functionalLocn = functionalLocnServices.create(functionalLocn,locnValues);}
//	    	existGenTlMachinemst = equipmentService.update(newgenTlMachinemst, existGenTlMachinemst,equipmentBean);
	    	JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());
		}
		else if( action.equals("save_blob.funlocn"))
		{
			//	CommonFunctions.debugMsg(request.getParameter("fileName"));
			//	CommonFunctions.debugMsg(request.getParameter("locnId"));
			 	FileInputStream fis = null;
			 	AdmTlUsermst user = UIUtils.getLoginUser(request);
			    try {		
			    	String imagePath = UIUtils.getImagePath(request);
			    	GenTlLayoutfieldimg genTlLayoutfieldimg = new GenTlLayoutfieldimg();
					genTlLayoutfieldimg.setLyfiFilename(request.getParameter("fileName"));
					genTlLayoutfieldimg.setLyfiCreatedby(user.getUsrm_ccno());
					genTlLayoutfieldimg.setLyfiKeyid(request.getParameter("locnId").replace("_", "/"));
					genTlLayoutfieldimg.setLyfiBlobimage(imagePath);
				//	CommonFunctions.debugMsg(" Before Calling Service");
					genTlLayoutfieldimg = functionalLocnServices.saveBlobImage(genTlLayoutfieldimg);
					JSONObject mode = new JSONObject();
					mode.put("fileName",genTlLayoutfieldimg.getLyfiFilename());
					CommonFunctions.debugMsg(mode.toString());
					out.print(mode.toString());
			    }
			    catch(Exception e){
			    	
			    }
		}
		else if( action.equals("paste_eqp.funlocn"))
		{
			AdmTlUsermst user = UIUtils.getLoginUser(request);
			String elementId = request.getParameter("elemId");	
			String displayCode = request.getParameter("dispCode");
			String originalId=request.getParameter("originalId");
			String[] elemId = request.getParameter("elemId").split("-");
			List<String> parentValues = new ArrayList<String>();			
			parentValues.add(request.getParameter("unitId"));
			parentValues.add(request.getParameter("subUnitId"));
			parentValues.add(request.getParameter("sectId"));
		
			parentValues.add(elemId[3]);
			parentValues.add(user.getUsrm_ccno());
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setOriginalId(originalId);
			functionalLocn.setDisplayCode(displayCode);
			functionalLocn = functionalLocnServices.cutEqp(functionalLocn,parentValues);
		
			JSONObject pasteTo = new JSONObject();
			pasteTo.put("pasteTo",request.getParameter("sectId"));
			pasteTo.put("pasted",displayCode);
			pasteTo.put("elementId", elementId);	
			pasteTo.put("successMsg","Machine Moved Successfully");
			System.out.print(pasteTo.toString());
			out.print(pasteTo.toString());
		}
		else if( action.equals("copy_node.funlocn"))
		{
			String eqpId = request.getParameter("eqpId");
			String pasteToEqpId = request.getParameter("pasteToEqpId");
			String dispcode = request.getParameter("dispCode");
			String elementId = request.getParameter("elemId");
			String[] elemId = elementId.split("-");
			pasteToEqpId = pasteToEqpId +"-"+ eqpId;
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setDisplayCode(dispcode);
			functionalLocn.setTempParentId("COPY");
			functionalLocn = functionalLocnServices.copyNode(functionalLocn,pasteToEqpId);
			JSONObject copiedTo = new JSONObject();
			copiedTo.put("copiedTo",eqpId);	
			copiedTo.put("copiedData",dispcode);
			copiedTo.put("assmId",elemId[elemId.length-1]);
			copiedTo.put("successMsg","Assembly Pasted Successfully");
			out.print(copiedTo.toString());
		}
		
		else if(action.equals("node_del.funlocn"))
		{
			String elementId = request.getParameter("elemID");		
			String originalId = request.getParameter("originalId");	
			String type = request.getParameter("type");
			String inactiveDate = request.getParameter("inactiveDate");
			
			//CommonFunctions.debugMsg("Element Id : "+elementId);	
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setElementType(type);
			functionalLocn.setOriginalId(originalId);
			functionalLocn.setRelatedFilter(inactiveDate);
			CommonFunctions.debugMsg(inactiveDate + "->"+type + " : "+originalId);
			try
			{
				functionalLocn = functionalLocnServices.deleteNode(functionalLocn);
				JSONObject successData = new JSONObject();
				successData.put("msg","Machine Inactivated Successfully");
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
			catch(Exception e)
			{
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
		else if(action.equals("mch_del.funlocn"))
		{
			String elementId = request.getParameter("elemID");		
			String originalId = request.getParameter("originalId");	
			String type = request.getParameter("type");	
			
			//CommonFunctions.debugMsg("Element Id : "+elementId);	
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setElementType(type);
			functionalLocn.setOriginalId(originalId);
			CommonFunctions.debugMsg(type + " : "+originalId);
			try
			{
				functionalLocn = functionalLocnServices.deletMachine(functionalLocn);
				CommonFunctions.debugMsg(functionalLocn);
				JSONObject successData = new JSONObject();
				successData.put("msg","Machine Inactivated Successfully");
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
			}
			catch(BusinessApplicationExceptions e)
			{
				String warningMsg = e.toString();
				if(warningMsg.indexOf(':')>0)
					warningMsg = warningMsg.substring(warningMsg.indexOf(":")+1).trim();
				JSONObject returnData = new JSONObject();
				returnData.put("warningMsg", warningMsg);
				returnData.put("elementID",functionalLocn.getElementId());
				returnData.put("orgID", functionalLocn.getOriginalId());
				returnData.put("type", functionalLocn.getElementType());
				out.print(returnData.toString());
				net.sf.json.JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(), "funcnLocn");
				//out.print(errMessage.toString());					
			}catch(Exception e)
			{
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
		}
		else if( action.equals("get_image.funlocn"))
		{
			//ServletOutputStream out = response.getOutputStream();
			String nodeId = request.getParameter("nodeId").replace("_", "/");
			try{
			GenTlLayoutfieldimg genTlLayoutfieldimg  = getImage(request, nodeId);
			CommonFunctions.debugMsg(genTlLayoutfieldimg.getLyfiFilename());
			response.setContentType("text/html");
			if( genTlLayoutfieldimg != null ){
				net.sf.json.JSONObject  layoutJSONObj =  UIUtils.fromTpmModel(genTlLayoutfieldimg);				
				layoutJSONObj.put("imgToimBlobimage",genTlLayoutfieldimg.getLyfiFilename());
				JSONObject returndata = new JSONObject();
				returndata.put("nodeImg", layoutJSONObj);	
		CommonFunctions.debugMsg(returndata.toString());
				out.print(returndata.toString());
			}	
			}
			catch(Exception e)
			{
				
				JSONObject layoutJSONObj = new JSONObject();
				layoutJSONObj.put("imgToimBlobimage","");
				JSONObject returndata = new JSONObject();
				returndata.put("nodeImg", layoutJSONObj);	
				out.print(returndata.toString());
			}
		}
		else if( action.equals("saveImage.funlocn") )
		{
			String imgPath = request.getServletContext().getRealPath("FunctionalLocnServlet");
			imgPath = imgPath.replace("FunctionalLocnServlet","images").replace("\\","/");
		    String finalImage = UIUtils.imageUpload(request,imgPath);
		    out.print(finalImage);
		}
		else if( action.equals("SBU_input.funlocn") )
		{
			System.out.println(" Inside :: ");
			String Sbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			FormModes mode = FormModes.create;
			BAL_GenTlSbumst genTlSbumst=null;
			if(  UIUtils.isValidKeyId(Sbukeyid ))
			
			//if( ( UIUtils.isValidKeyId(Sbukeyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new")))
			{
				
				String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				
				if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
				{
					mode =  FormModes.modify;
				}else if( formMode.equals( FormModeConsts.view)){
					mode=FormModes.view;
				}
				genTlSbumst = functionalLocnServices.fillcontrol(Sbukeyid);
				
				httpSession.setAttribute("genTlSbumst" , genTlSbumst);
				request.setAttribute("genTlSbumst", genTlSbumst);
				
			}
			request.setAttribute("genTlSbumst", genTlSbumst);
			RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlSbumst.jsp");					
			rd.forward(request, response);
		}
		
		else if( action.equals("PBU_input.funlocn") )
		{
			System.out.println(" Inside :: ");
			String frmType=null;
			String Pbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			FormModes mode = FormModes.create;
			BAL_GenTlPbumst genTlPbumst=null;
			if(action.equals("PBU_input.funlocn") )
				frmType="PBU";
			
             if( ( UIUtils.isValidKeyId(Pbukeyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
				
				String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				
				if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
				{
					mode =  FormModes.modify;
				}else if( formMode.equals( FormModeConsts.view)){
					mode=FormModes.view;
				}
				genTlPbumst = functionalLocnServices.fillpbucontrol(Pbukeyid);
				
				httpSession.setAttribute("genTlPbumst" , genTlPbumst);
				request.setAttribute("genTlPbumst", genTlPbumst);
				
			}
         	request.setAttribute("frmType", frmType);

			RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
			rd.forward(request, response);
		}
		else if( action.equals("PBU_recall.funlocn") )
		{
			
			
			String Pbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			System.out.println(" Sbu :: "+Pbukeyid);
			
			BAL_GenTlPbumst genTlPbumst=functionalLocnServices.fillpbucontrol(Pbukeyid);
			
			System.out.println(" Code :: "+genTlPbumst.getPbutCode()+" Name :: "+genTlPbumst.getPbutName()+" Description :: "+genTlPbumst.getPbutDescription());
			
		     JSONObject  pbumst =  UIUtils.fromTpmModel(genTlPbumst);
			 System.out.println("inside action" + pbumst );
			 JSONObject returndata = new JSONObject();
		
			 returndata.put("pbumst", pbumst);
			 out.print(returndata.toString());
		}
		
		
		else if( action.equals("Sbu_recall.funlocn") )
		{
			System.out.println(" Inside :: ");
			
			String Sbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			System.out.println(" Sbu :: "+Sbukeyid);
			
			BAL_GenTlSbumst genTlSbumst=functionalLocnServices.fillcontrol(Sbukeyid);
			
			System.out.println(" Code :: "+genTlSbumst.getSbutCode()+" Name :: "+genTlSbumst.getSbutName()+" Description :: "+genTlSbumst.getSbutDescription());
			
		     JSONObject  sbumst =  UIUtils.fromTpmModel(genTlSbumst);
			 System.out.println("inside action" + sbumst );
			 JSONObject returndata = new JSONObject();
		
			 returndata.put("sbumst", sbumst);
			 out.print(returndata.toString());
		}
		
		else if(action.equals("SBU_save.funlocn"))
		{	
			BAL_GenTlSbumstBean genTlSbumstBean = (BAL_GenTlSbumstBean)httpSession.getAttribute("GenTlSbumstBean");
	        saveSbu(request,response,genTlSbumstBean);
		}  
		
		else if(action.equals("SBU_delete.funlocn"))
		{	
			BAL_GenTlPbumstBean genTlPbumstBean = (BAL_GenTlPbumstBean)httpSession.getAttribute("GenTlSbumst");
		        deleteSbu(request,response,genTlPbumstBean,out);
		}
		else if(action.equals("PBU_save.funlocn"))
		{	
			BAL_GenTlPbumstBean genTlPbumstBean = (BAL_GenTlPbumstBean)httpSession.getAttribute("GenTlPbumst");
	        savePbu(request,response,genTlPbumstBean);
		} 
		else if(action.equals("PBU_delete.funlocn"))
		{	
			BAL_GenTlPbumstBean genTlPbumstBean = (BAL_GenTlPbumstBean)httpSession.getAttribute("GenTlPbumst");
	        deletePbu(request,response,genTlPbumstBean,out);
		}
		
		else if( action.equals("del_img.funlocn") )
		{
			String nodeId = request.getParameter("nodeId").replace("_", "/");
			try{
			
				FunctionalLocn functionalLocn = new FunctionalLocn();				
				functionalLocn = functionalLocnServices.deleteImage(nodeId);
				JSONObject successData = new JSONObject();
				successData.put("msg","Image Deleted Successfully");
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);				
				out.print(returnData.toString());
				
			}
			catch(Exception e)
			{
				
			}
		}
		else if( action.equals("cuteqp_check.funlocn"))
		{
			String elemID = request.getParameter("elemID");
			String nodeId = request.getParameter("nodeId").replace("_", "/");
			
			try{
				FunctionalLocn functionalLocn = new FunctionalLocn();				
				List<String[]>  validateEqpToMove = functionalLocnServices.cutValidEqp(nodeId);
			    JSONObject successData = new JSONObject();
				if(validateEqpToMove.size() > 0)				
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","checkBdForMachine"));
				else
					successData.put("msg","No-bd");
				
				JSONObject returnData = new JSONObject();
				returnData.put("originalId", nodeId);
				returnData.put("elementId", elemID);
				returnData.put("successData", successData);	
				out.print(returnData.toString());
				
			}
			catch(Exception e)
			{
				
			}
		}
		else if( action.equals("get_bd.funlocn"))
		{
			CommonFunctions.debugMsg("Inside funLocn Cut");
			String elementId = request.getParameter("elemId");
			String originalId = request.getParameter("originalId");
		//	String[] elemId = request.getParameter("elemId").split("-");
			List<String[]>  bdList = functionalLocnServices.getBdforEqp(originalId);
			String bdMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","bdList")+"\n";
			for(int j=0;j<bdList.size();j++)
			{
				if(!(bdList.get(j)[1].equals("0")))
					bdMsg += bdList.get(j)[0] + " : "+bdList.get(j)[1]+"\n";
			}
			bdMsg += UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","proceed");
			CommonFunctions.debugMsg("bdMsg    "+bdMsg);
			JSONObject successData = new JSONObject();
			successData.put("msg",bdMsg);
			successData.put("confirmmsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","Move-Confirm"));
			JSONObject returnData = new JSONObject();
			returnData.put("elementId", elementId);
			returnData.put("originalId",originalId);
			returnData.put("successData", successData);				
			out.print(returnData.toString());
			
		}
		else if( action.equals("location.funlocn"))
		{
			currentFilter = UIUtils.fillComboFilter(request);
			commonFilter.setLocation(currentFilter);
			comboList = functionalLocnServices.getLocationComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter );//writeCombo(response, comboList);
		}
		else if( action.equals("findCombo.funlocn"))
		{
			String frmFld = request.getParameter("formField");
			String elmType = request.getParameter("elemType");
			String Type = request.getParameter("type");
			String code = request.getParameter("Code");
			CommonFunctions.debugMsg(frmFld + " : "+Type);
			if(UIUtils.isValidKeyId(frmFld))
				Type = frmFld;
			CommonFunctions.debugMsg(frmFld + " : "+Type);
			
			String elemFld = null;
			String[] elmFldArr = elmType.split("-");
			List<String []> parentList  = functionalLocnServices.getParentElem(elmType);
			List<String> childList = getChildNodeElem(parentList,frmFld);
			if(childList.size() > 0)				
				elemFld = frmFld;					
			else
			{
				if(frmFld.equals("E"))
					elemFld = DrillLevelConstants.MCHM;
				else if(frmFld.equals("SC"))
					elemFld = DrillLevelConstants.SC;
				else if(frmFld.equals("A"))
					elemFld = DrillLevelConstants.ASSM;
				else if(frmFld.equals("S"))
					elemFld = DrillLevelConstants.S;
				else if(frmFld.equals("SUB"))
					elemFld = DrillLevelConstants.SBA;
				else
					elemFld = elmFldArr[elmFldArr.length-1];
			}
			currentFilter = UIUtils.fillComboFilter(request);
			comboList = functionalLocnServices.getFindComboList(currentFilter,childList,elemFld,Type,code);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);
		}
		else if( action.equals("assembly.funlocn"))
		{	
			currentFilter = UIUtils.fillComboFilter(request);
			commonFilter.setAssembly(currentFilter);
			comboList = functionalLocnServices.getAssemblyComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if( action.equals("subassemblyCombo.funlocn"))
		{
			currentFilter = UIUtils.fillComboFilter(request);
			commonFilter.setSubassembly(currentFilter);
			comboList = functionalLocnServices.getSubassemblyComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				try
				{	
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
				}
				catch(Exception e)
				{
					CommonFunctions.debugMsg("Caught");
				}
		}
		else if( action.equals("spareCombo.funlocn"))
		{
			currentFilter = UIUtils.fillComboFilter(request);
			commonFilter.setSpare(currentFilter);
			comboList = functionalLocnServices.getSpareComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if( action.equals("subcell.funlocn"))
		{
			//CommonFunctions.debugMsg("SUB CELL FUNLOCN SERVLET");
			currentFilter = UIUtils.fillComboFilter(request);
			commonFilter.setSubcell(currentFilter);
			comboList = functionalLocnServices.getSubCellComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if( action.equals("machine.funlocn"))
		{
			currentFilter = UIUtils.fillComboFilter(request);
			String machineId = request.getParameter("cmbCopyEquip");
			//CommonFunctions.debugMsg("MccccccccccccccH "+request.getParameter("parent"));
			if( UIUtils.isValidKeyId(machineId) )
				currentFilter.setId(machineId);
			
			commonFilter.setMachine(currentFilter);
			
			comboList = functionalLocnServices.getMachineComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
		else if(action.equals("sbu_input.funlocn")){
			UIUtils.forwardRequest(request, response,"/pages/SBU.jsp");
		}
		else
		{	
			UIUtils.removeCookie(response,"fnlnsearch_str");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionallocn.jsp"); 
			rd.forward(request, response);
		}
		
	
    }

private void deleteSbu(HttpServletRequest request,HttpServletResponse response, BAL_GenTlPbumstBean genTlPbumstBean,PrintWriter out) throws IOException {
	
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	//ServletOutputStream out = response.getOutputStream();
	try
	{
	if(httpSession !=null && user !=null)
	{
		CommonFunctions.debugMsg(" Inside Delete Methode :: ");
		BAL_GenTlSbumst newGenTlSbumst=new BAL_GenTlSbumst();
		newGenTlSbumst=(BAL_GenTlSbumst)UIUtils.setBeanProperties((Object)newGenTlSbumst,request);
		
		CommonFunctions.debugMsg(" newGenTlSbumst.getSbutKeyid()  "+newGenTlSbumst.getSbutKeyid());
		
		if(UIUtils.isValidKeyId(newGenTlSbumst.getSbutKeyid())){
			newGenTlSbumst=functionalLocnServices.sbudelete(newGenTlSbumst);
			httpSession.removeAttribute("GenTlSbumst"+newGenTlSbumst.getSbutKeyid());
			CommonFunctions.debugMsg("GenTlSbumst"+newGenTlSbumst.getSbutKeyid());
		
		}
	
		
		JSONObject successData=new JSONObject();
		JSONObject Sbumstdatadelete=new JSONObject();
		String savemsg;
	   if( newGenTlSbumst.getSbutKeyid()==null )
		{
			savemsg=" Data Not Deleted ";
			
		}
		else
		{
			savemsg= "Data Deleted succesfully";
			
		}
		successData.put("msg", savemsg);
		
		Sbumstdatadelete.put("successData", successData);
		Sbumstdatadelete.put("formClear",true);
		out.print(Sbumstdatadelete.toString());
		
	}
	}
	
	
//	catch(BusinessApplicationExceptions e)
//	{
//		CommonFunctions.debugMsg("BusinessApplicationExceptions"+e.toString());
//		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"auditareafives1");
//		errMessage.put("tpmException","Data not deleted ! Record is  Referred");
//		//System.out.println("errMessage"+errMessage);
//		//errMessage.put("AuditException", "Audit Area Name Already Referred");
//		errMessage.put("displyMsg", false);			
//		CommonFunctions.debugMsg(errMessage.toString());
//		out.print(errMessage.toString());
//	}
	catch(Exception e)
	{
		//PrintWriter  out = response.getWriter();
		JSONObject err = new JSONObject();
		//err.put("tpmException", "Data Not Saved");
		//err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
		out.print(err.toString());

    }

	
}
private void savePbu(HttpServletRequest request, HttpServletResponse response,BAL_GenTlPbumstBean genTlPbumstBean)throws Exception {
	// TODO Auto-generated method stub
	
	System.out.println(" Inside Save Action ");
	HttpSession httpSession = request.getSession(false);
	PrintWriter  out = response.getWriter();
	//ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	if( httpSession != null && user != null)
	{	
		//GenTlCellmst newGenTlCellmst = new GenTlCellmst();
		BAL_GenTlPbumst newGenTlPbumst=new BAL_GenTlPbumst();
		newGenTlPbumst.setPbutCreatedby(user.getUsrm_ccno());
		//if( genTlCellmstBean == null)
			//genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
		newGenTlPbumst =(BAL_GenTlPbumst)UIUtils.setBeanProperties((Object)newGenTlPbumst,request);
		BAL_GenTlPbumst exitGenTlPbumst = (BAL_GenTlPbumst)httpSession.getAttribute("genTlGenTlSbumstServlet");
		String location= request.getParameter("Location");
		System.out.println(" locationon ::1"+location);
		try{
			boolean insert = true;
			if( ! UIUtils.isValidKeyId( newGenTlPbumst.getPbutKeyid() ))
			{	System.out.println(" Inside Save Action ::1");
			exitGenTlPbumst = functionalLocnServices.pbucreate(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
			}
			else{
				insert = false;
				exitGenTlPbumst = functionalLocnServices.pbuupdate(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
			}
			JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
			 if(insert)
				msgPropertyIdnt = "success-save";
			 else
				msgPropertyIdnt = "success-update";
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);
			returnData.put("formClear",true);
			httpSession.removeAttribute("genTlCellmstServlet");
			httpSession.removeAttribute("genTlCellmstBean");
			out.print(returnData.toString());
			out.close();
		}
		catch(ValidationExceptions e)
		{  
		
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GentlPbumstValidation");
			//errMessage.put("fromMode",genTlCellmstBean.getFormActionMode());
			out.print(errMessage.toString());
		}
		catch(Exception e)
		{
			 CommonFunctions.debugMsg("Inside Exceptions "+e.getMessage());
				JSONObject err = new JSONObject();
				String msg="Data Not Supported";
				if(e.toString().contains("UK_PBUT_CODE"))
					msg="Code Already Exists";
				err.put("tpmException",msg);
				out.print(err.toString());
				e.printStackTrace();
		}
}
}
private void deletePbu(HttpServletRequest request,HttpServletResponse response, BAL_GenTlPbumstBean genTlPbumstBean, PrintWriter out)throws Exception {
	// TODO Auto-generated method stub
	
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	//ServletOutputStream out = response.getOutputStream();
	try
	{
	if(httpSession !=null && user !=null)
	{
		CommonFunctions.debugMsg(" Inside Delete Methode :: ");
		BAL_GenTlPbumst newGenTlPbumst=new BAL_GenTlPbumst();
		newGenTlPbumst=(BAL_GenTlPbumst)UIUtils.setBeanProperties((Object)newGenTlPbumst,request);
		
		CommonFunctions.debugMsg(" newGenTlPbumst.getPbutKeyid()  "+newGenTlPbumst.getPbutKeyid());
		
		if(UIUtils.isValidKeyId(newGenTlPbumst.getPbutKeyid())){
			newGenTlPbumst=functionalLocnServices.pbudelete(newGenTlPbumst);
			httpSession.removeAttribute("GenTlSbumst"+newGenTlPbumst.getPbutKeyid());
			CommonFunctions.debugMsg("GenTlSbumst"+newGenTlPbumst.getPbutKeyid());
		
		}
	
		
		JSONObject successData=new JSONObject();
		JSONObject Pbumstdatadelete=new JSONObject();
		String savemsg;
	   if( newGenTlPbumst.getPbutKeyid()==null )
		{
			savemsg=" Data Not Deleted ";
			
		}
		else
		{
			savemsg= "Data Deleted succesfully";
			
		}
		successData.put("msg", savemsg);
		Pbumstdatadelete.put("successData", successData);
		out.print(Pbumstdatadelete.toString());
		
	}
	}
	
	
//	catch(BusinessApplicationExceptions e)
//	{
//		CommonFunctions.debugMsg("BusinessApplicationExceptions"+e.toString());
//		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"auditareafives1");
//		errMessage.put("tpmException","Data not deleted ! Record is  Referred");
//		//System.out.println("errMessage"+errMessage);
//		//errMessage.put("AuditException", "Audit Area Name Already Referred");
//		errMessage.put("displyMsg", false);			
//		CommonFunctions.debugMsg(errMessage.toString());
//		out.print(errMessage.toString());
//	}
	catch(Exception e)
	{
		//PrintWriter  out = response.getWriter();
		JSONObject err = new JSONObject();
		//err.put("tpmException", "Data Not Saved");
		//err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
		out.print(err.toString());

    }

	
}
private void saveSbu(HttpServletRequest request, HttpServletResponse response, BAL_GenTlSbumstBean genTlSbumstBean)throws Exception {
	// TODO Auto-generated method stub
	
	System.out.println(" Inside Save Action ");
	HttpSession httpSession = request.getSession(false);
	PrintWriter out = response.getWriter(); 
	MastTblConfigTableMeta  mastTblConfigTableMeta = null;
	
	//ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	if( httpSession != null && user != null)
	{	
		//GenTlCellmst newGenTlCellmst = new GenTlCellmst();
		BAL_GenTlSbumst newGenTlSbumst=new BAL_GenTlSbumst();
		newGenTlSbumst.setSbutCreatedby(user.getUsrm_ccno());
		//if( genTlCellmstBean == null)
			//genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
		
		newGenTlSbumst =(BAL_GenTlSbumst)UIUtils.setBeanProperties((Object)newGenTlSbumst,request);
		BAL_GenTlSbumst exitGenTlSbumst = (BAL_GenTlSbumst)httpSession.getAttribute("genTlGenTlSbumstServlet");
		String location= request.getParameter("Location");
		System.out.println(" locationon ::1"+location);
		try{
			boolean insert = true;
			if( ! UIUtils.isValidKeyId( newGenTlSbumst.getSbutKeyid() ))
			{	System.out.println(" Inside Save Action ::1");
				exitGenTlSbumst = functionalLocnServices.sbucreate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
			}
			else{
				CommonFunctions.debugMsg("SBUSave");
				insert = false;
				CommonFunctions.debugMsg("llllll  "+newGenTlSbumst.getSbutLocationid());
				exitGenTlSbumst = functionalLocnServices.sbuupdate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
			
			}
			
						
			JSONObject successData = new JSONObject();
		    String msgPropertyIdnt;
			 
			 if(insert)
				msgPropertyIdnt = "success-save";
			 else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			JSONObject returnData = new JSONObject();
			
			returnData.put("successData", successData);
			
			httpSession.removeAttribute("genTlCellmstServlet");
			httpSession.removeAttribute("genTlCellmstBean");
			
	    	//PrintWriter  out = response.getWriter();
			out.print(returnData.toString());
			out.close();
		}
		catch(ValidationExceptions e)
		{  
			//PrintWriter  out = response.getWriter();
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "GenTlSbumstValidation");
			//errMessage.put("fromMode",genTlCellmstBean.getFormActionMode());
			out.print(errMessage.toString());
		}		
		catch(BusinessApplicationExceptions e)
		{
			
			CommonFunctions.debugMsg("validationbusiness   "+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "GenTlSbumstValidation");
			CommonFunctions.debugMsg(" e " + errMessage );
			out.print(errMessage.toString());
			
			
	}	
	  catch(Exception e){
		         CommonFunctions.debugMsg("Inside Exceptions "+e.getMessage());
				JSONObject err = new JSONObject();
				String msg="Data Not Supported";
				if(e.toString().contains("UK_SBUT_CODE"))
					msg="Code Already Exists";
				err.put("tpmException",msg);
				out.print(err.toString());
				e.printStackTrace();
			/*JSONObject err = new JSONObject();
			err.put("tpmException", "Data Not Saved");
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
			out.print(err.toString());*/
		}
	}
}

private GenTlLayoutfieldimg getImage(HttpServletRequest request,String nodeId) throws Exception{
	
	  HttpSession httpSession = request.getSession(false);
	  if( UIUtils.isValidKeyId(nodeId) ){
		  GenTlLayoutfieldimg genTlLayoutfieldimg = null;
		  genTlLayoutfieldimg = functionalLocnServices.select(nodeId);		   
		  String filePath = UIUtils.getImagePath(request);
		   
		   genTlLayoutfieldimg.setLyfiBlobimage(filePath);
		   genTlLayoutfieldimg.setLyfiFilename(UIUtils.TPM_TEMPIMG_DIR);
		   CommonFunctions.debugMsg("file name:"+ genTlLayoutfieldimg.getLyfiFilename().toString());
		   try{
			   genTlLayoutfieldimg =functionalLocnServices.getLayoutImg(genTlLayoutfieldimg);
			   if( genTlLayoutfieldimg != null)
				   request.setAttribute("genTlLayoutfieldimg",genTlLayoutfieldimg);
		   }catch(Exception e){
			   
		   }
		   request.setAttribute("genTlLayoutfieldimg", genTlLayoutfieldimg);
		   
		  
		   return genTlLayoutfieldimg;
	  }	
	  return null;
}

    private List<String> setFunLocnModel(String funLocnGrid) {
	// TODO Auto-generated method stub
    String[] originalId = funLocnGrid.split("::");	
	List<String> paramValues = new ArrayList<String>();	
	for(int i=0;i<originalId.length;i++)
	{
		paramValues.add(originalId[i]);
	}
	
	return paramValues;
    }


	private List<String> getChildNodeElem(List<String[]> parentList,String formField) {
	// TODO Auto-generated method stub
	//	CommonFunctions.debugMsg("IDENTIFY = "+formField);
    	List<String> childElem =  new ArrayList<String>();	 
    	
    	for(String[] pl :parentList)
    	{
    		String[] key = null;
    		for(int i=0;i<pl.length;i++)
    		{

    			if(formField.equals("E"))
    			{
    				if(pl[i].substring(0, 3).equals("MCH"))
    					childElem.add(pl[i]);
    			}
    			else if(formField.equals("SC"))
    			{
    				if(pl[i].substring(0, 3).equals("SSN"))
    					childElem.add(pl[i]);
    			}
    			else if(formField.equals("A"))
    			{
    				if(pl[i].substring(0, 3).equals("ASM"))
    					childElem.add(pl[i]);
    			}
    			else if(formField.equals("S"))
    			{
    				if(pl[i].substring(0, 3).equals("SPR"))
    					childElem.add(pl[i]);
    			}
    			else if(formField.equals("SUB"))
    			{
    				if(pl[i].substring(0, 3).equals("SBA"))
    					childElem.add(pl[i]);
    			}
    			else
    				childElem.add(pl[i]);
    		}
    		
    	}
       return childElem;
}

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public String getIconImage(String elementType)
    {
    	//CommonFunctions.debugMsg("Inside getIcon " +elementType);
    	String imgUrl = null;
    	if(elementType.equals("CMP"))
    		imgUrl =  "images/FnLocn/company.jpg";
    	else if(elementType.equals("LCN"))
    		imgUrl =  "images/FnLocn/location.jpg";
    	else if(elementType.equals("SBU"))
    		imgUrl =  "images/FnLocn/sbu.jpg";
    	else if(elementType.equals("PBU"))
    		imgUrl =  "images/FnLocn/pbu.png";
    	else if(elementType.equals("F") || elementType.equals("FCT") )
    		imgUrl =  "images/FnLocn/factory.jpg";
    	else if(elementType.equals("L"))
    		imgUrl =  "images/FnLocn/unit.jpg";
    	else if(elementType.equals("C"))
    		imgUrl =  "images/FnLocn/section.jpg";
    	else if(elementType.equals("M"))
    		imgUrl =  "images/FnLocn/machine.jpg";
    	else if(elementType.equals("A"))
    		imgUrl =  "images/FnLocn/assembly.jpg";
    	else if(elementType.equals("SPR"))
    		imgUrl =  "images/FnLocn/spare.png";
    	else if(elementType.equals("SSN"))
    		imgUrl =  "images/FnLocn/sub-section.jpg";
    	else if(elementType.equals("IMT"))
    		imgUrl =  "images/FnLocn/instrument.jpg";
    	else if(elementType.equals("FL"))
    		imgUrl =  "images/FnLocn/fc.png";
    	else if(elementType.equals("SAM"))
    		imgUrl =  "images/FnLocn/sub-assembly.jpg";
    	return imgUrl;
    	
    }
    
    public String getImageUrl(String elementType)
    {

    	String imgUrl = null;
    	if(elementType.equals("CMP"))
    		imgUrl =  "images/companyy.jpg";
    	else if(elementType.equals("LCN"))
    		imgUrl =  "images/FnLocn/location.jpg";
    	else if(elementType.equals("SBU"))
    		imgUrl =  "images/FnLocn/sbu.jpg";
    	else if(elementType.equals("PBU"))
    		imgUrl =  "images/FnLocn/pbu.png";
    	else if(elementType.equals("F") || elementType.equals("FCT"))
    		imgUrl =  "images/factory_inside.jpg";
    	else if(elementType.equals("L"))
    		imgUrl =  "images/f.jpg";
    	else if(elementType.equals("C"))
    		imgUrl =  "images/sect.jpg";
    	else if(elementType.equals("M"))
    		imgUrl =  "images/mach.jpg";
    	else if(elementType.equals("A"))
    		imgUrl =  "images/asb.jpg";
    	else if(elementType.equals("SPR"))
    		imgUrl =  "images/spares6.jpg";
    	else if(elementType.equals("SSN"))
    		imgUrl =  "images/5s.jpg";
    	else if(elementType.equals("IMT"))
    		imgUrl =  "images/wire.jpg";
    	else if(elementType.equals("FL"))
    		imgUrl =  "images/companyy.jpg";
    	
    	return imgUrl;
    	
    }
    
    
    private net.sf.json.JSONObject getColumnModel(String tableCaption)
	{
    	String [][] colNames = { 
								 {"Name","name","340","false"},
 				                 {"Code","code","130","false"},		
		};
		net.sf.json.JSONObject tableModel= UIUtils.getGroupByColumnModel(colNames) ;
		tableModel.put("tableCaption", tableCaption);
		tableModel.put("isGroupBy", "false");
		tableModel.put("groupSummary", "false");
		CommonFunctions.debugMsg(tableModel);
		return tableModel;
		
	}   

	

}
