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
import com.akranta.tpm.bean.FunctLocFieldNameBean;
import com.akranta.tpm.bean.GenTlCellmstBean;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSbumst;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.service.FunctionalLocnServices;
import com.akranta.tpm.service.impl.FunctionalLocnServicesImpl;
import com.akranta.tpm.service.impl.MasterTableConfigServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
/*Created By Suresh.K on Oct 10*/
import com.akranta.tpm.utils.ReqtParamNameConst;

public class FunctionalLocnServlet extends HttpServlet {
	
	private static final long serialVersionUID = -8070953412956046800L;
	FunctionalLocnServices functionalLocnServices;

	public FunctionalLocnServlet()
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
			functionalLocnServices = (FunctionalLocnServicesImpl)UIUtils.getServiceObject(request,"FunctionalLocnServicesImpl");
		} catch (ServiceObjectCreationException e) {
			CommonMessage.debugMsg(e);
		}
		
		
		if( action.equals("loadval.funlocn") )
		{	
			String parentNumber = null;
			String parentId = null;
			String elementType = null;		
			
			parentNumber=request.getParameter("elementId");
			//CommonMessage.debugMsg("PARENT NUMBER"+parentNumber);
			parentId = request.getParameter("parentId");
			//CommonMessage.debugMsg("PARENt id"+parentId);
			
			elementType = request.getParameter("elementType");
			//CommonMessage.debugMsg("elementType::::"+elementType);
			
			parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
			//CommonMessage.debugMsg("parentNumber::::"+parentNumber);
			CommonMessage.debugMsg("ID : "+request.getParameter("id"));
	    	response.setContentType("text/html;charset=UTF-8");
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
		    		FunctionalLocn functionalLocn = new FunctionalLocn();	        	
		        	functionalLocn.setElementId(parentNumber);
		        	functionalLocn.setParentId(parentId);
		        	functionalLocn.setElementType(elementType);
		        	List <FunctionalLocn> locnList = functionalLocnServices.getAllLocation(functionalLocn);
		        	//CommonMessage.debugMsg("locnList::::"+locnList);
		        	for(int i=0; i<locnList.size(); i++){
		        		JSONObject jSONObject = new JSONObject();
		        		JSONObject MastTblConfigTableMeta = new JSONObject();
	        			JSONObject data = new JSONObject();
	        			JSONObject jsonAttr = new JSONObject();
		                JSONObject metadata = new JSONObject();
		        		CommonMessage.debugMsg(locnList.get(i).getOriginalId());
		        		CommonMessage.debugMsg(locnList.get(i).getElementId());
		        		CommonMessage.debugMsg(locnList.get(i).getParentId());
		        	 CommonMessage.debugMsg(locnList.get(i).getElementType());
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
	        			
	        			//CommonMessage.debugMsg("originalId"+jsonAttr);
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
	        				
	        			//CommonMessage.debugMsg("INSIDE THE ELSE CONDITION");
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
		    	CommonMessage.debugMsg(jSONArray);
		    	//CommonMessage.debugMsg("///////////////////////");
	           out.print(jSONArray);
	           //CommonMessage.debugMsg("888888888888888************");
        	   jSONArray=null;
	    	}catch(Exception e){
	           // CommonMessage.debugMsg(e);
	            e.printStackTrace();
	        }
	        finally {
	            out.close();
	        }
	        
		}
		else if(action.equals("functionalLoc1.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
		    functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setCompMandatory(false);
			functLocFieldNameBean.setLocnMandatory(false);
		
			String disableFuncLoc = request.getParameter("frmType");
		  if("true".equals(disableFuncLoc)){
				functLocFieldNameBean.setCompany("cmbComp");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setCompDisable(true);
				functLocFieldNameBean.setLconDisable(true);
			}
		  else if("PBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
		        functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(false);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
			else if("SBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
			    functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setLocnMandatory(true);
			    functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
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
		}else if (action.equals("load_equipCut.funlocn")){
			//&=C&dlgId=dlgAddChild&w=300&h=180&dispCode=undefined&elemId=undefined
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
			
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionalCutEquip.jsp"); 
			rd.forward(request, response);
		}
	
		else if( action.equals("searchnode.funlocn") )
		{	
			Enumeration<String> params = request.getParameterNames() ;
			/*while(params.hasMoreElements() )
			{
				//CommonMessage.debugMsg("params in SEARCH  " + params.nextElement());
				//CommonMessage.debugMsg("params ========  " + request.getParameter(params.nextElement()));
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
				//CommonMessage.debugMsg(parentId);
				parentId = "#node_1-FL001-" + parentId.replaceAll("/", "_");
				//parentId = "-" + parentId.replaceAll("/", "_");
				//CommonMessage.debugMsg(parentId);
				parentId = parentId.replaceAll("-", "-#");
				//parentId = "#node_1-#node_2" + parentId;
				//CommonMessage.debugMsg(parentId);
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
		else if( action.equals("funcnLocn_getCol.funlocn") )
		{	
			out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.FuncnLocnAddColModel", "colModel"));
		}
		
		
		else if(action.equals("functionalLoc.funlocn"))
		{
			
	
			FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				CommonMessage.debugMsg("Actionload999");
				functLocFieldNameBean.setCompany("cmbComp");
				//fact// functLocFieldNameBean.setFactory("cmbFact");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				CommonMessage.debugMsg("5555555");
			    functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(false);
				functLocFieldNameBean.setCellDisable(false);
				functLocFieldNameBean.setMachDisable(false);
		
			
	
			
			FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		
		else if( action.equals("funcnLocn_getData.funlocn") )
		{
			try {
				
				String frmFld = request.getParameter("formField");
				String elmType = request.getParameter("elemType");
				String elemFld = null;
				String[] elmFldArr = elmType.split("-");
				String key = request.getParameter("key");
				//CommonMessage.debugMsg(elmType);
				//CommonMessage.debugMsg(elmFldArr.length);
				//CommonMessage.debugMsg(elmFldArr[elmFldArr.length-1]);
				
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
						elemFld = DrillLevelConstants.SBU;
					else
						elemFld = elmFldArr[elmFldArr.length-1];
				}
				
				//CommonMessage.debugMsg("FORMFLD : "+elemFld);
				String totalCount = functionalLocnServices.getTotalCount(childList,elemFld);
				int totalRows = 200;
				 GridParams gridParams =(GridParams) httpSession.getAttribute("FuncnLocngridParams");
				 if( gridParams == null )
       			  gridParams = new GridParams(); 
       		  
				 FilterValues.populateGridParams(request,gridParams );
				  
       		  	httpSession.removeAttribute("FuncnLocngridParams");
       		  	httpSession.setAttribute("FuncnLocngridParams" ,gridParams);
				List<String []> childForParent = functionalLocnServices.getChildElem(childList,elemFld,start,end,key,gridParams);
				if(UIUtils.isValidKeyId(totalCount))
					totalRows = Integer.parseInt(totalCount);
				net.sf.json.JSONObject childForParentData = UIUtils.convertToJqGridTableObject(childForParent,request,0,1,totalRows);
					//CommonMessage.debugMsg(childForParentData);
				httpSession.removeAttribute("FuncnLocngridParams");
				out.println(childForParentData);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if( action.equals("funcnLocn_save.funlocn") )
		{
			//CommonMessage.debugMsg("Inside funLocn Save");
			String funLocnGrid = request.getParameter("FunctionalLocnGrid");
			//CommonMessage.debugMsg("funLocnGrid:::::"+funLocnGrid);
			FunctionalLocn functionalLocn = new FunctionalLocn();	
			
	    	functionalLocn.setParentId(request.getParameter("hdnFuncCondition"));
	    	//CommonMessage.debugMsg("(request.getParameter::::"+(request.getParameter("hdnFuncCondition")));
	    	List<String> locnValues = setFunLocnModel(funLocnGrid);
	    	functionalLocn = functionalLocnServices.create(functionalLocn,locnValues);
	    	//CommonMessage.debugMsg("functionalLocn---->"+functionalLocn);
	    	JSONObject successData = new JSONObject();
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);				
			out.print(returnData.toString());
		}
		else if( action.equals("save_blob.funlocn"))
		{
			//	CommonMessage.debugMsg(request.getParameter("fileName"));
			//	CommonMessage.debugMsg(request.getParameter("locnId"));
			 	FileInputStream fis = null;
			 	AdmTlUsermst user = UIUtils.getLoginUser(request);
			    try {		
			    	String imagePath = UIUtils.getImagePath(request);
			    	GenTlLayoutfieldimg genTlLayoutfieldimg = new GenTlLayoutfieldimg();
					genTlLayoutfieldimg.setLyfiFilename(request.getParameter("fileName"));
					genTlLayoutfieldimg.setLyfiCreatedby(user.getUsrm_ccno());
					genTlLayoutfieldimg.setLyfiKeyid(request.getParameter("locnId").replace("_", "/"));
					genTlLayoutfieldimg.setLyfiBlobimage(imagePath);
				//	CommonMessage.debugMsg(" Before Calling Service");
					genTlLayoutfieldimg = functionalLocnServices.saveBlobImage(genTlLayoutfieldimg);
					JSONObject mode = new JSONObject();
					mode.put("fileName",genTlLayoutfieldimg.getLyfiFilename());
					CommonMessage.debugMsg(mode.toString());
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
			String[] elemId = request.getParameter("elemId").split("-");
			for(int i=0;i<elemId.length;i++){
				CommonMessage.debugMsg("elemId[" +i +"] " + elemId[i]);
			}
			List<String> parentValues = new ArrayList<String>();			
			parentValues.add(request.getParameter("subUnitId"));//DMT
			parentValues.add(request.getParameter("sectId"));//JH
			parentValues.add(elemId[6]);//Equiment
			CommonMessage.debugMsg("elemId[6]" + elemId[6]);
			parentValues.add(request.getParameter("unitId"));//Location
			parentValues.add(user.getUsrm_ccno());
			FunctionalLocn functionalLocn = new FunctionalLocn();
			
			functionalLocn.setElementId(elementId);
			functionalLocn.setDisplayCode(displayCode);
			functionalLocn = functionalLocnServices.cutEqp(functionalLocn,parentValues);
			JSONObject pasteTo = new JSONObject();
			pasteTo.put("pasteTo",request.getParameter("sectId"));
			pasteTo.put("pasted",displayCode);
			pasteTo.put("successMsg","Machine Moved Successfully");
			//System.out.print(pasteTo.toString());
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
			
			//CommonMessage.debugMsg("Element Id : "+elementId);	
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setElementType(type);
			functionalLocn.setOriginalId(originalId);
			functionalLocn.setRelatedFilter(inactiveDate);
			CommonMessage.debugMsg(inactiveDate + "->"+type + " : "+originalId);
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
			
			//CommonMessage.debugMsg("Element Id : "+elementId);	
			FunctionalLocn functionalLocn = new FunctionalLocn();
			functionalLocn.setElementId(elementId);
			functionalLocn.setElementType(type);
			functionalLocn.setOriginalId(originalId);
			CommonMessage.debugMsg(type + " : "+originalId);
			try
			{
				functionalLocn = functionalLocnServices.deletMachine(functionalLocn);
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
			//CommonMessage.debugMsg("NODE ID::"+nodeId);
		
	
			try{
			GenTlLayoutfieldimg genTlLayoutfieldimg  = getImage(request, nodeId);
			//CommonMessage.debugMsg("genTlLayoutfieldimg--------"+genTlLayoutfieldimg);
			//CommonMessage.debugMsg(genTlLayoutfieldimg.getLyfiFilename());
			response.setContentType("text/html");
			if( genTlLayoutfieldimg != null ){
				net.sf.json.JSONObject  layoutJSONObj =  UIUtils.fromTpmModel(genTlLayoutfieldimg);				
				layoutJSONObj.put("imgToimBlobimage",genTlLayoutfieldimg.getLyfiFilename());
				JSONObject returndata = new JSONObject();
				returndata.put("nodeImg", layoutJSONObj);	
				//CommonMessage.debugMsg(returndata.toString());
				out.print(returndata.toString());
			}	
			}
			catch(Exception e)
			{
				//CommonMessage.debugMsg(e.toString());
				JSONObject layoutJSONObj = new JSONObject();
				layoutJSONObj.put("imgToimBlobimage","");
				JSONObject returndata = new JSONObject();
				returndata.put("nodeImg", layoutJSONObj);	
				//CommonMessage.debugMsg(returndata.toString());
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
		else if( action.equals("SBU_input.funlocn"))
		{   
			//CommonMessage.debugMsg(" Inside :: sbu funcnlocation");
			String Sbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
			//FormModes mode = FormModes.create;
			GenTlSbumst genTlSbumst=null;
			String sbuadd=request.getParameter("id");
			//CommonMessage.debugMsg("sbuadd in fnlcn servlet+"+sbuadd);
			if(  UIUtils.isValidKeyId(Sbukeyid ))
			
			//if( ( UIUtils.isValidKeyId(Sbukeyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new")))
			{
				
				String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
				
				/*if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
				{
					mode =  FormModes.modify;
				}else if( formMode.equals( FormModeConsts.view)){
					mode=FormModes.view;
				}*/
				genTlSbumst = functionalLocnServices.fillcontrol(Sbukeyid);
				
				httpSession.setAttribute("genTlSbumst" , genTlSbumst);
				request.setAttribute("genTlSbumst", genTlSbumst);
				
			}
			request.setAttribute("genTlSbumst", genTlSbumst);
			request.setAttribute("sbuadd", sbuadd);
			/*if(sbuadd!=null)
			{
			if(sbuadd.equals("add"))
			{
			RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlSbumstadd.jsp");					
			rd.forward(request, response);
			}
			else if(sbuadd.equals("update"))
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlSbumst.jsp");					
				rd.forward(request, response);
			}
			}
			else
			{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlSbumst.jsp");					
				rd.forward(request, response);
			}*/
			
			//UIUtils.forwardRequest(request, response,"/pages/SBU.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlSbumst.jsp");					
			rd.forward(request, response);
		}
		
		else if(action.equals("functionalLocsbu.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
		    functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSbuMandatory(true);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setCompMandatory(false);
			functLocFieldNameBean.setLocnMandatory(false);
		
			String disableFuncLoc = request.getParameter("frmType");
			//CommonMessage.debugMsg(disableFuncLoc);
		  if("true".equals(disableFuncLoc)){
				functLocFieldNameBean.setCompany("cmbComp");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setCompDisable(true);
				functLocFieldNameBean.setLconDisable(true);
			}
		  else if("PBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
		        functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(false);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
			else if("SBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
			    functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setLocnMandatory(true);
			    functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		
		else if(action.equals("functionalLoc.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
			functLocFieldNameBean.setCompany("cmbComp");
		    functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setFactMandatory(false);
			functLocFieldNameBean.setSbuMandatory(true);
			functLocFieldNameBean.setSectMandatory(false);
			functLocFieldNameBean.setCellMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setMachMandatory(false);
			functLocFieldNameBean.setCompMandatory(false);
			functLocFieldNameBean.setLocnMandatory(false);
		
			String disableFuncLoc = request.getParameter("frmType");
			//CommonMessage.debugMsg(disableFuncLoc);
		  if("true".equals(disableFuncLoc)){
				functLocFieldNameBean.setCompany("cmbComp");
				functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuDisable(true);
				functLocFieldNameBean.setPbuDisable(true);
				functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				functLocFieldNameBean.setCompDisable(true);
				functLocFieldNameBean.setLconDisable(true);
			}
		  else if("PBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
		        functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(false);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
			else if("SBU".equals(disableFuncLoc))
			{
				functLocFieldNameBean.setCompany("cmbComp");
			    functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setLocnMandatory(true);
			    functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
			}
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		
		else if(action.equals("functionalLocsbuadd.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
	
			
		    functLocFieldNameBean.setCompany("cmbComp");
	        functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setSbuMandatory(false);
			functLocFieldNameBean.setPbuMandatory(false);
			functLocFieldNameBean.setLocnMandatory(true);
			functLocFieldNameBean.setSbuDisable(false);
			functLocFieldNameBean.setPbuDisable(true);
			functLocFieldNameBean.setSbuDisable(true);
	        functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);
				
		
			
           // FormModes formModes = FormModes.create;
			FormModes formModes=FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		else if(action.equals("functionalLocpbu.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
	
			
		    functLocFieldNameBean.setCompany("cmbComp");
	        functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setPbuMandatory(true);
			functLocFieldNameBean.setSbuMandatory(false);
			functLocFieldNameBean.setSbuDisable(true);
			functLocFieldNameBean.setPbuDisable(false);
	        functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);
				
		
			
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		else if(action.equals("functionalLocpbuadd.funlocn"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
	
			
		    functLocFieldNameBean.setCompany("cmbComp");
	        functLocFieldNameBean.setSbu("cmbSbu");
			functLocFieldNameBean.setPbu("cmbPbu");
			functLocFieldNameBean.setSection("cmbSect");
			functLocFieldNameBean.setCell("cmbCell");
			functLocFieldNameBean.setMachine("cmbMachine");
			functLocFieldNameBean.setPbuMandatory(false);
			functLocFieldNameBean.setSbuMandatory(true);
			functLocFieldNameBean.setSbuDisable(false);
			functLocFieldNameBean.setPbuDisable(true);
	        functLocFieldNameBean.setSectDisable(true);
			functLocFieldNameBean.setCellDisable(true);
			functLocFieldNameBean.setMachDisable(true);
				
		
			
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		
		else if(action.equals("PBU_input.funlocn"))
		{
			
				//CommonMessage.debugMsg(" Inside ::  pbu action");
				//String id=request.getParameter("id");
				String pbuadd=request.getParameter("id");
				CommonMessage.debugMsg("sbuadd+"+pbuadd);
				
				////CommonMessage.debugMsg("THE PASSED ID ID IS >>>>"+id);
				//String result=functionalLocnServices.(id);
				String frmType=null;
				String Pbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
				CommonMessage.debugMsg("The Pbukeyid"+Pbukeyid);
				String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
				FormModes mode = FormModes.create;
				GenTlPbumst genTlPbumst=null;
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
				//	genTlPbumst.setPbutFlid("FNL000000120");
					
					////CommonMessage.debugMsg("genTlPbumst.getPbutFlid"+genTlPbumst.getPbutFlid());
					httpSession.setAttribute("genTlPbumst" , genTlPbumst);
					request.setAttribute("genTlPbumst", genTlPbumst);
					
				}
	         	request.setAttribute("frmType", frmType);
	         	request.setAttribute("pbuadd",pbuadd );
	         /*	if(pbuadd!=null)
	         	{
	         	if(pbuadd.equals("add"))
				{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlpbumstadd.jsp");					
				rd.forward(request, response);
				}
	         	else if(pbuadd.equals("update"))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
					rd.forward(request, response);
				}
	         	else if(pbuadd.equals("subuntupdate"))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
					rd.forward(request, response);
				}
	         	}
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
					rd.forward(request, response);
				}*/
				RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
			    rd.forward(request, response);
	         	//RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlPbumst.jsp");					
				//rd.forward(request, response);
				
			}
		else if( action.equals("PBU_recall.funlocn") )
		{
			//CommonMessage.debugMsg(" Inside :: ");
			
			String Pbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			//CommonMessage.debugMsg(" Sbu :: "+Pbukeyid);
			
			GenTlPbumst genTlPbumst=functionalLocnServices.fillpbucontrol(Pbukeyid);
			
			//CommonMessage.debugMsg(" Code :: "+genTlPbumst.getPbutCode()+" Name :: "+genTlPbumst.getPbutName()+" Description :: "+genTlPbumst.getPbutDescription());
			
		     JSONObject  pbumst =  UIUtils.fromTpmModel(genTlPbumst);
			 //CommonMessage.debugMsg("inside action" + pbumst );
			 JSONObject returndata = new JSONObject();
		
			 returndata.put("pbumst", pbumst);
			 out.print(returndata.toString());
		}
		
		
		else if( action.equals("Sbu_recall.funlocn") )
		{
			//CommonMessage.debugMsg(" Inside :: ");
			
			String Sbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
			//CommonMessage.debugMsg(" Sbu :: "+Sbukeyid);
			
			GenTlSbumst genTlSbumst=functionalLocnServices.fillcontrol(Sbukeyid);
			
			//CommonMessage.debugMsg(" Code :: "+genTlSbumst.getSbutCode()+" Name :: "+genTlSbumst.getSbutName()+" Description :: "+genTlSbumst.getSbutDescription());
			
		     JSONObject  sbumst =  UIUtils.fromTpmModel(genTlSbumst);
			 //CommonMessage.debugMsg("inside action" + sbumst );
			 JSONObject returndata = new JSONObject();
		
			 returndata.put("sbumst", sbumst);
			 out.print(returndata.toString());
		}
		
		else if(action.equals("functionalLoc2.commonFilter"))
		{
		    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
	
				functLocFieldNameBean.setCompany("cmbComp");
		        functLocFieldNameBean.setSbu("cmbSbu");
				functLocFieldNameBean.setPbu("cmbPbu");
				functLocFieldNameBean.setSection("cmbSect");
				functLocFieldNameBean.setCell("cmbCell");
				functLocFieldNameBean.setMachine("cmbMachine");
				functLocFieldNameBean.setPbuMandatory(false);
				functLocFieldNameBean.setSectMandatory(false);
				functLocFieldNameBean.setCompMandatory(false);
				functLocFieldNameBean.setSbuMandatory(true);
				functLocFieldNameBean.setLocnMandatory(false);
				functLocFieldNameBean.setCompDisable(true);
				functLocFieldNameBean.setLconDisable(true);
				functLocFieldNameBean.setSbuDisable(false);
				functLocFieldNameBean.setPbuDisable(true);
		        functLocFieldNameBean.setSectDisable(true);
				functLocFieldNameBean.setCellDisable(true);
				functLocFieldNameBean.setMachDisable(true);
				
		
			
            FormModes formModes = FormModes.create;
			UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
		}
		else if(action.equals("SBU_save.funlocn"))
		{	
			GenTlSbumstBean genTlSbumstBean = (GenTlSbumstBean)httpSession.getAttribute("GenTlSbumstBean");
	        saveSbu(request,response,genTlSbumstBean);
		}  
		
		else if(action.equals("SBU_delete.funlocn"))
		{	
				GenTlPbumstBean genTlPbumstBean = (GenTlPbumstBean)httpSession.getAttribute("GenTlSbumst");
		        deleteSbu(request,response,genTlPbumstBean,out);
		}
		else if(action.equals("PBU_save.funlocn"))
		{	
			GenTlPbumstBean genTlPbumstBean = (GenTlPbumstBean)httpSession.getAttribute("GenTlPbumst");
	        savePbu(request,response,genTlPbumstBean);
		} 
		else if(action.equals("PBU_delete.funlocn"))
		{	
			GenTlPbumstBean genTlPbumstBean = (GenTlPbumstBean)httpSession.getAttribute("GenTlPbumst");
	        deletePbu(request,response,genTlPbumstBean,out);
		}
		
		else if( action.equals("del_img.funlocn") )
		{
			String nodeId = request.getParameter("nodeId").replace("_", "/");
			//CommonMessage.debugMsg(nodeId);
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
			String dispCode = request.getParameter("dispCode");
			CommonMessage.debugMsg(nodeId);
			CommonMessage.debugMsg(elemID);
			try{
				//FunctionalLocn functionalLocn = new FunctionalLocn();				
				List<String[]>  validateEqpToMove = functionalLocnServices.cutValidEqp(nodeId);
				CommonMessage.debugMsg(validateEqpToMove.size());
				JSONObject successData = new JSONObject();
				if(validateEqpToMove.size() > 0)				
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","checkBdForMachine"));
				else
					successData.put("msg","No-bd");
				
				JSONObject returnData = new JSONObject();
				returnData.put("elementId", elemID);	
				returnData.put("dispCode", dispCode);
				returnData.put("nodeId", nodeId);
				returnData.put("successData", successData);	
				out.print(returnData.toString());
				
			}
			catch(Exception e)
			{
				
			}
		}
		else if( action.equals("get_bd.funlocn"))
		{
			CommonMessage.debugMsg("Inside funLocn Cut");
			String elementId = request.getParameter("elemId");
			String[] elemId = request.getParameter("elemId").split("-");
			CommonMessage.debugMsg("Element Id : "+elemId[3]);	
			CommonMessage.debugMsg("Element Id : "+elementId);	
			List<String[]>  bdList = functionalLocnServices.getBdforEqp(elemId[3]);
			String bdMsg = UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","bdList")+"\n";
			for(int j=0;j<bdList.size();j++)
			{
				if(!(bdList.get(j)[1].equals("0")))
					bdMsg += bdList.get(j)[0] + " : "+bdList.get(j)[1]+"\n";
			}
			bdMsg += UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","proceed");
			CommonMessage.debugMsg("bdMsg    "+bdMsg);
			JSONObject successData = new JSONObject();
			successData.put("msg",bdMsg);
			successData.put("confirmmsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.funcnLocn","Move-Confirm"));
			JSONObject returnData = new JSONObject();
			returnData.put("elementId", elementId);
			returnData.put("successData", successData);				
			out.print(returnData.toString());
			
		}
		else if( action.equals("location.funlocn"))
		{
			currentFilter = UIUtils.fillComboFilter(request);
			//CommonMessage.debugMsg("INSIDE THE location.funlocn");
			commonFilter.setLocation(currentFilter);
			comboList = functionalLocnServices.getLocationComboList(commonFilter);
			//CommonMessage.debugMsg("comboList in side the location"+comboList);
			if( comboList != null && comboList.size() > 0 )
				
				UIUtils.writeComboBox(response, comboList,currentFilter );//writeCombo(response, comboList);
		}
		else if( action.equals("findCombo.funlocn"))
		{
			String frmFld = request.getParameter("formField");
			String elmType = request.getParameter("elemType");
			String Type = request.getParameter("type");
			String code = request.getParameter("Code");
			CommonMessage.debugMsg(frmFld + " : "+Type);
			if(UIUtils.isValidKeyId(frmFld))
				Type = frmFld;
			CommonMessage.debugMsg(frmFld + " : "+Type);
			
			String elemFld = null;
			String[] elmFldArr = elmType.split("-");
			List<String []> parentList  = functionalLocnServices.getParentElem(elmType);
			//CommonMessage.debugMsg("parentList in side the location"+parentList);
			List<String> childList = getChildNodeElem(parentList,frmFld);
			//CommonMessage.debugMsg("childList in side the location"+childList);
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
			//CommonMessage.debugMsg("comboList1 in side the location"+comboList);
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
					CommonMessage.debugMsg("Caught");
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
			//CommonMessage.debugMsg("SUB CELL FUNLOCN SERVLET");
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
			//CommonMessage.debugMsg("MccccccccccccccH "+request.getParameter("parent"));
			if( UIUtils.isValidKeyId(machineId) )
				currentFilter.setId(machineId);
			
			commonFilter.setMachine(currentFilter);
			
			comboList = functionalLocnServices.getMachineComboList(commonFilter);
			if( comboList != null && comboList.size() > 0 )
				UIUtils.writeComboBox(response, comboList,currentFilter);//writeCombo(response, comboList);
		}
/*		else if(action.equals("sbu_input.funlocn")){
			UIUtils.forwardRequest(request, response,"/pages/SBU.jsp");
		}*/
		else
		{	
			UIUtils.removeCookie(response,"fnlnsearch_str");
			RequestDispatcher rd = request.getRequestDispatcher("/pages/functionallocn.jsp"); 
			rd.forward(request, response);
		}
		
	
    }

private void deleteSbu(HttpServletRequest request,HttpServletResponse response, GenTlPbumstBean genTlPbumstBean,PrintWriter out) throws IOException {
	// TODO Auto-generated method stub
	//http://localhost:7070/TPMToolKit/SBU_delete.funlocn?&keyId=SBU0000034
	
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	//ServletOutputStream out = response.getOutputStream();
	try
	{
	if(httpSession !=null && user !=null)
	{
		CommonMessage.debugMsg(" Inside Delete Methode :: ");
		GenTlSbumst newGenTlSbumst=new GenTlSbumst();
		newGenTlSbumst=(GenTlSbumst)UIUtils.setBeanProperties((Object)newGenTlSbumst,request);
		
		CommonMessage.debugMsg(" newGenTlSbumst.getSbutKeyid()  "+newGenTlSbumst.getSbutKeyid());
		
		if(UIUtils.isValidKeyId(newGenTlSbumst.getSbutKeyid())){
			newGenTlSbumst=functionalLocnServices.sbudelete(newGenTlSbumst);
			httpSession.removeAttribute("GenTlSbumst"+newGenTlSbumst.getSbutKeyid());
			CommonMessage.debugMsg("GenTlSbumst"+newGenTlSbumst.getSbutKeyid());
		
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
//		CommonMessage.debugMsg("BusinessApplicationExceptions"+e.toString());
//		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"auditareafives1");
//		errMessage.put("tpmException","Data not deleted ! Record is  Referred");
//		////CommonMessage.debugMsg("errMessage"+errMessage);
//		//errMessage.put("AuditException", "Audit Area Name Already Referred");
//		errMessage.put("displyMsg", false);			
//		CommonMessage.debugMsg(errMessage.toString());
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
private void savePbu(HttpServletRequest request, HttpServletResponse response,GenTlPbumstBean genTlPbumstBean)throws Exception {
	// TODO Auto-generated method stub
	
	//CommonMessage.debugMsg(" Inside Save Action ");
	HttpSession httpSession = request.getSession(false);
	PrintWriter  out = response.getWriter();
	//ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	if( httpSession != null && user != null)
	{	
		//GenTlCellmst newGenTlCellmst = new GenTlCellmst();
		GenTlPbumst newGenTlPbumst=new GenTlPbumst();
		newGenTlPbumst.setPbutCreatedby(user.getUsrm_ccno());
		//if( genTlCellmstBean == null)
			//genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
		newGenTlPbumst =(GenTlPbumst)UIUtils.setBeanProperties((Object)newGenTlPbumst,request);
		GenTlPbumst exitGenTlPbumst = (GenTlPbumst)httpSession.getAttribute("genTlGenTlSbumstServlet");
		String location= request.getParameter("Location");
		//CommonMessage.debugMsg(" locationon ::1"+location);
		try{
			boolean insert = true;
			if( ! UIUtils.isValidKeyId( newGenTlPbumst.getPbutKeyid() ))
			{	//CommonMessage.debugMsg(" Inside Save Action ::1");
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
			successData.put("PbutKeyid", exitGenTlPbumst.getPbutKeyid());
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
			 /*CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
				JSONObject err = new JSONObject();
				String msg="Data Not Supported";
				if(e.toString().contains("UK_PBUT_CODE"))
					msg="Code Already Exists";
				err.put("tpmException",msg);
				out.print(err.toString());
				e.printStackTrace();*/
			 //CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.SectionError","err-pbu"));
				out.print(err.toString());
		}
}
}
private void deletePbu(HttpServletRequest request,HttpServletResponse response, GenTlPbumstBean genTlPbumstBean, PrintWriter out)throws Exception {
	// TODO Auto-generated method stub
	
	UIUtils.displayRequestParamsValue(request);
	HttpSession httpSession = request.getSession(false);    	
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	//ServletOutputStream out = response.getOutputStream();
	try
	{
	if(httpSession !=null && user !=null)
	{
		CommonMessage.debugMsg(" Inside Delete Methode :: ");
		GenTlPbumst newGenTlPbumst=new GenTlPbumst();
		newGenTlPbumst=(GenTlPbumst)UIUtils.setBeanProperties((Object)newGenTlPbumst,request);
		
		CommonMessage.debugMsg(" newGenTlPbumst.getPbutKeyid()  "+newGenTlPbumst.getPbutKeyid());
		
		if(UIUtils.isValidKeyId(newGenTlPbumst.getPbutKeyid())){
			newGenTlPbumst=functionalLocnServices.pbudelete(newGenTlPbumst);
			httpSession.removeAttribute("GenTlSbumst"+newGenTlPbumst.getPbutKeyid());
			CommonMessage.debugMsg("GenTlSbumst"+newGenTlPbumst.getPbutKeyid());
		
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
//		CommonMessage.debugMsg("BusinessApplicationExceptions"+e.toString());
//		JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"auditareafives1");
//		errMessage.put("tpmException","Data not deleted ! Record is  Referred");
//		////CommonMessage.debugMsg("errMessage"+errMessage);
//		//errMessage.put("AuditException", "Audit Area Name Already Referred");
//		errMessage.put("displyMsg", false);			
//		CommonMessage.debugMsg(errMessage.toString());
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
private void saveSbu(HttpServletRequest request, HttpServletResponse response, GenTlSbumstBean genTlSbumstBean)throws Exception {
	// TODO Auto-generated method stub
	
	//CommonMessage.debugMsg(" Inside Save Action ");
	HttpSession httpSession = request.getSession(false);
	PrintWriter out = response.getWriter(); 
	MastTblConfigTableMeta  mastTblConfigTableMeta = null;
	
	//ServletOutputStream out = response.getOutputStream();
	AdmTlUsermst user = UIUtils.getLoginUser(request);
	
	if( httpSession != null && user != null)
	{	
		//GenTlCellmst newGenTlCellmst = new GenTlCellmst();
		GenTlSbumst newGenTlSbumst=new GenTlSbumst();
		newGenTlSbumst.setSbutCreatedby(user.getUsrm_ccno());
		//if( genTlCellmstBean == null)
			//genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
		
		newGenTlSbumst =(GenTlSbumst)UIUtils.setBeanProperties((Object)newGenTlSbumst,request);
		
		GenTlSbumst exitGenTlSbumst = (GenTlSbumst)httpSession.getAttribute("genTlGenTlSbumstServlet");
		String location= request.getParameter("Location");
		//CommonMessage.debugMsg(" locationon ::1"+location);
		try{
			boolean insert = true;
			if( ! UIUtils.isValidKeyId( newGenTlSbumst.getSbutKeyid() ))
			{	//CommonMessage.debugMsg(" Inside Save Action ::1");
				exitGenTlSbumst = functionalLocnServices.sbucreate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
			}
			else{
				CommonMessage.debugMsg("SBUSave");
				insert = false;
				CommonMessage.debugMsg("llllll  "+newGenTlSbumst.getSbutLocationid());
				exitGenTlSbumst = functionalLocnServices.sbuupdate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
			
			}
			
						
			JSONObject successData = new JSONObject();
		    String msgPropertyIdnt;
			 
			 if(insert)
				msgPropertyIdnt = "success-save";
			 else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			successData.put("SbutKeyid", exitGenTlSbumst.getSbutKeyid());
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
			
			CommonMessage.debugMsg("validationbusiness   "+e.toString());
			JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "GenTlSbumstValidation");
			CommonMessage.debugMsg(" e " + errMessage );
			out.print(errMessage.toString());
			
			
	}	
	  catch(Exception e){
		      /*   CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
				JSONObject err = new JSONObject();
				String msg="Data Not Supported";
				if(e.toString().contains("UK_SBUT_CODE"))
					msg="Code Already Exists";
				err.put("tpmException",msg);
				out.print(err.toString());
				e.printStackTrace();*/
		  //CommonMessage.debugMsg("gete. " + e.getMessage());
			JSONObject err = new JSONObject();
			//err.put("tpmException", "Data Not Saved");
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.SectionError","err-sbu"));
			out.print(err.toString());
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
		 
		   try{
			   genTlLayoutfieldimg =functionalLocnServices.getLayoutImg(genTlLayoutfieldimg);
			   if( genTlLayoutfieldimg != null)
				   request.setAttribute("genTlLayoutfieldimg",genTlLayoutfieldimg);
		   }catch(Exception e){
			   
		   }
		   request.setAttribute("genTlLayoutfieldimg", genTlLayoutfieldimg);
		   
		  //CommonMessage.debugMsg("genTlLayoutfieldimg.........."+genTlLayoutfieldimg);
		   return genTlLayoutfieldimg;
	  }	
	  return null;
}

    private List<String> setFunLocnModel(String funLocnGrid) {
	// TODO Auto-generated method stub
    	//CommonMessage.debugMsg("setFunLocnModel:::"+funLocnGrid);
        String[] originalId = funLocnGrid.split("::");	
    	//CommonMessage.debugMsg("originalId::"+originalId);
    	List<String> paramValues = new ArrayList<String>();	
    	for(int i=0;i<originalId.length;i++)
    	{
    		paramValues.add(originalId[i]);
    	}
    	
    	//CommonMessage.debugMsg("paramValues::::"+paramValues);
    	return paramValues;
        }



	private List<String> getChildNodeElem(List<String[]> parentList,String formField) {
	// TODO Auto-generated method stub
	//	CommonMessage.debugMsg("IDENTIFY = "+formField);
    	List<String> childElem =  new ArrayList<String>();	 
    	
    	for(String[] pl :parentList)
    	{
    		String[] key = null;
    		for(int i=0;i<pl.length;i++)
    		{
    			CommonMessage.debugMsg(pl[i]+" : "+i);
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
    	//CommonMessage.debugMsg("Inside getIcon " +elementType);
    	String imgUrl = null;
    	if(elementType.equals("CMP"))
    		imgUrl =  "images/FnLocn/company.jpg";
    	else if(elementType.equals("LCN"))
    		imgUrl =  "images/FnLocn/location.jpg";
    	else if(elementType.equals("SBU"))
    		imgUrl =  "images/FnLocn/sbu.jpg";
    	else if(elementType.equals("PBU"))
    		imgUrl =  "images/FnLocn/pbu.png";
    	else if(elementType.equals("F"))
    		imgUrl =  "images/FnLocn/factory.jpg";
    	else if(elementType.equals("L"))
    		imgUrl =  "images/FnLocn/unit.jpg";
    	else if(elementType.equals("SEC"))
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
    	
    	return imgUrl;
    	
    }
    
    public String getImageUrl(String elementType)
    {
    	CommonMessage.debugMsg("Inside getImage " +elementType);
    	String imgUrl = null;
    	if(elementType.equals("CMP"))
    		imgUrl =  "images/companyy.jpg";
    	else if(elementType.equals("LCN"))
    		imgUrl =  "images/FnLocn/location.jpg";
    	else if(elementType.equals("SBU"))
    		imgUrl =  "images/FnLocn/sbu.jpg";
    	else if(elementType.equals("PBU"))
    		imgUrl =  "images/FnLocn/pbu.png";
    	else if(elementType.equals("F"))
    		imgUrl =  "images/factory_inside.jpg";
    	else if(elementType.equals("L"))
    		//imgUrl =  "images/f.jpg";
    	    imgUrl =  "images/FnLocn/unit.jpg";
    	else if(elementType.equals("SEC"))
    		//imgUrl =  "images/f.jpg";
    	    imgUrl =  "images/FnLocn/unit.jpg";
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
		CommonMessage.debugMsg(tableModel);
		return tableModel;
		
	}   

	

}
