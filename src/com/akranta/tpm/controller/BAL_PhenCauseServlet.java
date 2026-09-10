package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.BAL_PhenCauseService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.BAL_PhenCauseServiceImpl;
//import com.akranta.tpm.service.impl.PlmTlPlanconfigurationServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.PrjConstants;
import com.akranta.tpm.utils.ReqtParamNameConst;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CauseBean;
import com.akranta.tpm.bean.BAL_GenTlAssemblymstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.JqGridTableModel;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.bean.BAL_PhenCauseBean;
import com.akranta.tpm.bean.BAL_PhenomenaBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.model.GenTlToolsmst;
import com.akranta.tpm.model.GridColModel;


public class BAL_PhenCauseServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BAL_PhenCauseService  phenCauseService ;
	CommonFilterService commonFilterService;
	
	String formTypeIdentifier [];
	
	public BAL_PhenCauseServlet()
	{
		super();
       /* CommonFunctions.debugMsg(" initialising servlet ....");
        try {
        	phenCauseService = new PhenCauseServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
		//Constructor
	}
	    /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			try {
				processRequest(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		   throws Exception {
			
			    HttpSession httpSession = request.getSession(false);
		    	String action = BAL_UIUtils.getActionPart(request);
				ComboFilter comboFilter = new ComboFilter();
				comboFilter=BAL_UIUtils.fillComboFilter(request);

		    	try {
					
					phenCauseService = (BAL_PhenCauseServiceImpl)BAL_UIUtils.getServiceObject(request,"BAL_PhenCauseServiceImpl");
					phenCauseService.BAL_PhenCauseServiceImplJwt((String) (httpSession.getAttribute("tpmjwttoken") == null ? "" : httpSession.getAttribute("tpmjwttoken")) );
					commonFilterService = (CommonFilterServiceImpl)BAL_UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			
				} catch (ServiceObjectCreationException e) {
					CommonFunctions.debugMsg(e);
				}

			   
			   if( action.equals("PhenCause_input.pcl") ){	
				   CommonFunctions.debugMsg(" inside action  " );
				   BAL_UIUtils.displayRequestParamsValue(request);
				   response.setContentType("text/html");
				   
				   BAL_PhenCauseBean phenCauseBean = (BAL_PhenCauseBean)httpSession.getAttribute("PhenCauseServletPhenCauseBean");
				   if (phenCauseBean == null )	
				   		phenCauseBean = new  BAL_PhenCauseBean();					
					
				   if (request.getParameter("phenId")==null || request.getParameter("phenId")=="")
					   phenCauseBean.setFormActionMode("Save");
				   else
					   phenCauseBean.setFormActionMode("Update");
				   
				   httpSession.setAttribute("formBean",phenCauseBean );				   

/*				   String params[] =request.getParameter("controls").split("``");
				   request.setAttribute("combinedId", params[0].replace("{}",""));				   
				   request.setAttribute("AssmId", params[1].replace("{}",""));
				   request.setAttribute("phenId", params[2].replace("{}",""));
				   request.setAttribute("causeId", params[3].replace("{}",""));
*/
				   //CommonFunctions.debugMsg("combinedid=="+request.getParameter("combinedId"));
				   String linkBd = request.getParameter("linkBD");
				   if(BAL_UIUtils.isValidKeyId(linkBd))
					   request.setAttribute("linkBd",linkBd);
				   request.setAttribute("combinedId", request.getParameter("combinedId"));
				   request.setAttribute("assmId", request.getParameter("assmId"));
				   request.setAttribute("phenId", request.getParameter("phenId"));
				   request.setAttribute("causeId", request.getParameter("causeId"));
				   
				   phenCauseBean.setCombinedId(request.getParameter("combinedId"));
				   CommonFunctions.debugMsg("servert"+phenCauseBean.getCombinedId());
					
					RequestDispatcher rd = request.getRequestDispatcher("/pages/PhenCause.jsp");					
					rd.forward(request, response);		
				}	
			   	else if( action.equals("PhenCause_load.pcl"))
				{
			   		RequestDispatcher rd = request.getRequestDispatcher("/pages/PhenCauseLink.jsp"); 
					rd.forward(request, response);
				}
			   	else if( action.equals("PhenCause_tree.pcl"))
				{
			   		Enumeration<String> params = request.getParameterNames() ;
			   		PrintWriter out = response.getWriter();
			   		while(params.hasMoreElements() )
					{
						CommonFunctions.debugMsg("params  " + params.nextElement());
					}
			   		String parentNumber = null;
					String parentId = null;
					String elementType = null;		
					
					parentNumber=request.getParameter("elementId");
					parentId = request.getParameter("parentId");
					elementType = request.getParameter("elementType");
					
					CommonFunctions.debugMsg( " ElementId " + parentNumber);
					CommonFunctions.debugMsg( " elementType " + elementType);
					parentNumber  = parentNumber.equals("0") ? "0" :parentNumber;
					CommonFunctions.debugMsg("ID : "+request.getParameter("id"));
			    	response.setContentType("text/html;charset=UTF-8");
			    	try {
			    		JSONArray jSONArray = new JSONArray();	    		
				    	if(request.getParameter("id").equals("0"))
				    	{
					    	   JSONObject jSONObject = new JSONObject();
				    		   JSONObject data = new JSONObject();
				    		   JSONObject jsonAttr = new JSONObject();
				               JSONObject metadata = new JSONObject();
				    		   jsonAttr.put("id", "PCL001");
				    		   jsonAttr.put("originalId", "1");
				               jsonAttr.put("elementId", "1");
				               jsonAttr.put("parentId", "1");
				               jsonAttr.put("elementType", "PCL");
				               jsonAttr.put("displayCode", "Breakdown Phenomena");
				               jsonAttr.put("imgUrl",getImageUrl("PCL"));
				               jsonAttr.put("href", "#");
				               data.put("title", "Breakdown Phenomena");	        			
			        		   data.put("icon", "");
			        		   jSONObject.put("data",data);
			        		   jSONObject.put("attr", jsonAttr);
			        		   
			        		   if(request.getParameter("search_str") != null)
			        			   jSONObject.put("state","open");
			        		   else
			        			   jSONObject.put("state","closed");
			        		   metadata.put("id", "1");
			        		   jSONObject.put("metadata",metadata);
				               jSONObject.put("icon",getImageUrl("PCL"));
				               jsonAttr = null;
				               jSONObject.put("children","[{}]");
				               jSONArray.put(jSONObject);
				               jSONObject=null;		    		
				    	}
				    	else
				    	{
				    		BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();	        	
				    		bdmTlPhncauselink.setBpclElementid(parentNumber);
				    		bdmTlPhncauselink.setBpclParentid(parentId);
				    		bdmTlPhncauselink.setBpclElementtype(elementType);
				        	List <BAL_BdmTlPhncauselink> phenCauseList = phenCauseService.getAllLocation(bdmTlPhncauselink);
				        	for(int i=0; i<phenCauseList.size(); i++){
				        		JSONObject jSONObject = new JSONObject();
			        			JSONObject data = new JSONObject();
			        			JSONObject jsonAttr = new JSONObject();
				                JSONObject metadata = new JSONObject();
				                jsonAttr.put("id", phenCauseList.get(i).getBpclOriginalid().replace("/", "_"));
				                jsonAttr.put("originalId", phenCauseList.get(i).getBpclOriginalid());
				                jsonAttr.put("elementId", phenCauseList.get(i).getBpclElementid());
				                jsonAttr.put("parentId", phenCauseList.get(i).getBpclParentid());
				                jsonAttr.put("elementType", phenCauseList.get(i).getBpclElementtype());
				                jsonAttr.put("displayCode", phenCauseList.get(i).getBpclDisplaycode());
				                jsonAttr.put("imgUrl",getImageUrl(phenCauseList.get(i).getBpclElementtype()));
				                jsonAttr.put("title",BAL_UIUtils.getTitle(phenCauseList.get(i).getBpclOriginalid().substring(0,3)));
				                jsonAttr.put("href", "#");
			        			data.put("title", phenCauseList.get(i).getBpclDisplaycode());
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
				                jSONObject.put("icon",getImageUrl(phenCauseList.get(i).getBpclElementtype()));
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
			   	else if( action.equals("searchTree.pcl") )
				{	
			   		PrintWriter out = response.getWriter();
					Enumeration<String> params = request.getParameterNames() ;
					while(params.hasMoreElements() )
					{
						CommonFunctions.debugMsg("params in SEARCH  " + params.nextElement());
						CommonFunctions.debugMsg("params ========  " + request.getParameter(params.nextElement()));
					}
					
					String prevSearchstr = BAL_UIUtils.getCookieValue(request,"BDPHNsearch_str");
					String currentSearchStr = request.getParameter("search_str");
					
					CommonFunctions.debugMsg("css "+currentSearchStr);
					String countStr  = "0"; 
					List<String[]> searchList = null;
					if(currentSearchStr.equals(prevSearchstr))
					{
						countStr = BAL_UIUtils.getCookieValue(request,"BDPHNsearch_str_cnt");
						searchList =(List<String[]>) httpSession.getAttribute("BDPHNSearchList");
					}
					else{
						searchList = phenCauseService.getSearchNode(currentSearchStr);
						CommonFunctions.debugMsg("Size Of List : "+searchList.size());
						httpSession.setAttribute("BDPHNSearchList", searchList);
					}
					int searchCnt = Integer.parseInt(countStr);
					JSONArray jSONArray =null;
					if( searchCnt < searchList.size() ){
						String parentId = searchList.get(searchCnt)[0];
						CommonFunctions.debugMsg("Parent ID : "+parentId);
						if(parentId.substring(0, 3).equals(DrillLevelConstants.COMP))
						{
							parentId = "#node_1-PCL001-"+ parentId.replaceAll("/", "_");
						}
						else
						{
							String cmpId = phenCauseService.getCmpFromPhnCauseLink(parentId);
							parentId = "#node_1-PCL001-"+cmpId +"-"+ parentId.replaceAll("/", "_");
						}
							
						//parentId = "-" + parentId.replaceAll("/", "_");
						//CommonFunctions.debugMsg(parentId);
						parentId = parentId.replaceAll("-", "-#");
						//parentId = "#node_1-#node_2" + parentId;
						//CommonFunctions.debugMsg(parentId);
						CommonFunctions.debugMsg("PareNt ========================= : "+searchList.size());
						String[] searchNode = parentId.split("-");
						
						jSONArray = JSONArray.fromArray(searchNode);
					}
					else{
						searchCnt =-1;
					}
					Cookie searchStrCookie =  new Cookie("BDPHNsearch_str",currentSearchStr);
					response.addCookie(searchStrCookie);
					Cookie searchCntCookie =  new Cookie("BDPHNsearch_str_cnt",(searchCnt+1)+"");
					response.addCookie(searchCntCookie);
					CommonFunctions.debugMsg(jSONArray);
					out.println(jSONArray);
				}
			   	else if( action.equals("addPhenCause_getCol.pcl") )
				{	
			   		PrintWriter out = response.getWriter();
					out.println(BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.FuncnLocnAddColModel", "colModel"));
				}
				else if( action.equals("addPhenCause_getData.pcl") )
				{
					try {
						PrintWriter out = response.getWriter();
						String frmFld = request.getParameter("formField");
						String elmType = request.getParameter("elemType");
						String elemFld = null;
						String[] elmFldArr = elmType.split("-");
						
						
						String rows = request.getParameter("rows");
			        	String page = request.getParameter("page");	
			        	
			           
		       		    String start = "1";
		       		    String end = "100";
		       		    if(!page.equals("1"))		       		  
		       		    {
		       		     int rowStart = (Integer.parseInt(rows)*Integer.parseInt(page))-99;
		        		 int rowEnd = Integer.parseInt(rows)*Integer.parseInt(page);
		        		 start = Integer.toString(rowStart);
		        		 end = Integer.toString(rowEnd);
		       		    }
						List<String []> parentList  = phenCauseService.getParentElem(elmType);
						List<String> childList = getChildNodeElem(parentList,frmFld);
						CommonFunctions.debugMsg("Size Of Child List : "+childList.size() + " - "+frmFld);
						//if(childList.size() > 0)				
						elemFld = frmFld;					
						//else
						//{
							//if(frmFld.equals("ASM"))
							//	elemFld = DrillLevelConstants.ASSM;
							//else if(frmFld.equals("PHN"))
								//elemFld = DrillLevelConstants.PHENOMENA;
						//	else
							//	elemFld = elmFldArr[elmFldArr.length-1];
					//	}
						
						String totalCount = phenCauseService.getTotalCount(childList,elemFld);
						int totalRows = 200;
						 GridParams gridParams =(GridParams) httpSession.getAttribute("PhenomenaCauseParams");
						 if( gridParams == null )
		       			  gridParams = new GridParams(); 
		       		  
						 FilterValues.populateGridParams(request,gridParams );
						  
			       		  	httpSession.removeAttribute("PhenomenaCauseParams");
			       		  	httpSession.setAttribute("PhenomenaCauseParams" ,gridParams);
						List<String []> childForParent = phenCauseService.getChildElem(childList,elemFld,start,end,gridParams);
						CommonFunctions.debugMsg("sIZE OF CHILD IN SERVLET : "+childForParent.size());
						if(BAL_UIUtils.isValidKeyId(totalCount))
							totalRows = Integer.parseInt(totalCount);
						net.sf.json.JSONObject childForParentData = BAL_UIUtils.convertToJqGridTableObject(childForParent,request,0,1,totalRows);
						httpSession.removeAttribute("PhenomenaCauseParams");
						out.println(childForParentData);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else if( action.equals("addPhenCause_save.pcl") )
				{
					PrintWriter out = response.getWriter();
					String phenCauseGrid = request.getParameter("FunctionalLocnGrid");
					String parentId = request.getParameter("hdnFuncCondition");
					BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();	    	
					bdmTlPhncauselink.setBpclParentid(parentId);
			    	List<String> phenCauseValues = setPhenCauseModel(phenCauseGrid);
			    	bdmTlPhncauselink = phenCauseService.create(bdmTlPhncauselink,phenCauseValues);
			    	JSONObject successData = new JSONObject();
					successData.put("msg",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-save"));
					JSONObject returnData = new JSONObject();
					returnData.put("successData", successData);				
					out.print(returnData.toString());
				}
				else if (action.equals("phenomena_input.pcl")) 
				{
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					String assmId = request.getParameter(ReqtParamNameConst.ASSMID);

					
					CommonFunctions.debugMsg(" userEvent  " + userEvent);
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					BAL_BdmTlPhenomenamst bdmTlPhenomenamst = null;
					
					httpSession.removeAttribute("bdmTlPhenomenamstphncsServlet");
					
					if( ( BAL_UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						
						bdmTlPhenomenamst = phenCauseService.selectPhenomena(keyid);
						httpSession.setAttribute("bdmTlPhenomenamstphncsServlet" , bdmTlPhenomenamst);
						request.setAttribute("bdmTlPhenomenamstphncsServlet", bdmTlPhenomenamst);
					}
					//mode=FormModes.view;
					if(BAL_UIUtils.isValidKeyId(assmId ))
					{
						bdmTlPhenomenamst = new BAL_BdmTlPhenomenamst();
						bdmTlPhenomenamst.setBphmAssemblyid(assmId);
					}
					BAL_PhenomenaBean phenBean = new BAL_PhenomenaBean(mode,lockFields);
					
					httpSession.setAttribute("phenBean", phenBean);
					request.setAttribute("phenBean", phenBean);
					request.setAttribute("bdmTlPhenomenamst", bdmTlPhenomenamst);

					RequestDispatcher rd = request.getRequestDispatcher("/pages/Phenomena.jsp"); 
					rd.forward(request, response);					
				}
				else if (action.equals("phenomena_save.pcl")) 
				{
					BAL_PhenomenaBean phenBean = (BAL_PhenomenaBean) httpSession.getAttribute("phenBean");
					savePhenomena(request,response,phenBean);
				}
				else if (action.equals("cause_input.pcl")) 
				{
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					String phnId = request.getParameter(ReqtParamNameConst.PHENID);

					
					CommonFunctions.debugMsg(" userEvent  " + userEvent);
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					BAL_BdmTlCausemst bdmTlCausemst = null;
					
					httpSession.removeAttribute("bdmTlCausemstphncsServlet");
					
					if( ( BAL_UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						
						bdmTlCausemst = phenCauseService.selectCause(keyid);
						httpSession.setAttribute("bdmTlCausemstphncsServlet" , bdmTlCausemst);
						request.setAttribute("bdmTlCausemstphncsServlet", bdmTlCausemst);
					}
					//mode=FormModes.view;
					/*
					 * if(BAL_UIUtils.isValidKeyId(phnId )) { bdmTlCausemst = new
					 * BAL_BdmTlCausemst(); bdmTlCausemst.setBcsmPhenomenaid(phnId); }
					 */
					if (BAL_UIUtils.isValidKeyId(phnId)) {
				        if (bdmTlCausemst == null) {
				            bdmTlCausemst = new BAL_BdmTlCausemst();
				        }
				        bdmTlCausemst.setBcsmPhenomenaid(phnId);
				    }
					request.setAttribute("phenId", phnId);
					BAL_CauseBean causeBean = new BAL_CauseBean(mode,lockFields);
					
					httpSession.setAttribute("causeBean", causeBean);
					request.setAttribute("causeBean", causeBean);
					request.setAttribute("bdmTlCausemst", bdmTlCausemst);

					RequestDispatcher rd = request.getRequestDispatcher("/pages/Cause.jsp"); 
					rd.forward(request, response);					
				}
			   
				else if (action.equals("cause_save.pcl")) 
				{
					BAL_CauseBean causeBean = (BAL_CauseBean) httpSession.getAttribute("causeBean");
					saveCause(request,response,causeBean);
				}
				else if( action.equals("combo_Assembly.pcl"))
				{
					try {						
						List<ComboBox>  assembly = phenCauseService.getsAssemblyCombo("");
						BAL_UIUtils.writeComboBox(response, assembly, comboFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				else if (action.equals("cause_getCol.pcl")) {
				    getCauseColModel(request, response);
				}
				else if (action.equals("cause_getData.pcl")) {
				    getCauseTblData(request, response);
				}

			   else if( action.equals("combo_Phenomena.pcl"))
				{
				   CommonFunctions.debugMsg(" inside combo phenomena  " );
					try {						
						List<ComboBox>  phenomena = phenCauseService.getPhenomena("");
						BAL_UIUtils.writeComboBox(response, phenomena, comboFilter);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
				}
				else if( action.equals("combo_Cause.pcl"))
				{
					try {						
						List<ComboBox>  cause = phenCauseService.getCause("");
						BAL_UIUtils.writeComboBox(response, cause, comboFilter);
					} catch (Exception e) {
					
						e.printStackTrace();
					}				
				}			   
				else if( action.equals("PhenCause_save.pcl"))
				{
					CommonFunctions.debugMsg(" inside save " );
					CommonFunctions.debugMsg(request.getParameter("cmbbphmKeyid"));
					
					BAL_PhenCauseBean phenCauseBean = (BAL_PhenCauseBean)httpSession.getAttribute("formBean");
					
					if (phenCauseBean == null )	{
						phenCauseBean = new  BAL_PhenCauseBean();					
					}
					
					CommonFunctions.debugMsg(phenCauseBean.getFormActionMode());
					
					savePcl(request,response,phenCauseBean);
				}
			   // added here by priyanka 
				
				else if (action.equals("phenomena_getCol.pcl")) {
				    getPhenomenaColModel(request, response);
				}
				else if (action.equals("phenomena_getData.pcl")) {
				    getPhenomenaTblData(request, response);
				}
			   
				else if (action.equals("phenomena_recall.pcl"))
				{
				    ServletOutputStream out = response.getOutputStream();
				    JSONObject returndata = new JSONObject();

				    String keyId = request.getParameter("keyId");

				    if (BAL_UIUtils.isValidKeyId(keyId))
				    {
				        BAL_BdmTlPhenomenamst bdmTlPhenomenamst = phenCauseService.selectPhenomena(keyId);
				        httpSession.removeAttribute("bdmTlPhenomenamstphncsServlet");
				        httpSession.setAttribute("bdmTlPhenomenamstphncsServlet", bdmTlPhenomenamst);
				        JSONObject phenData = UIUtils.fromTpmModel(bdmTlPhenomenamst);
				        returndata.put("phenData", phenData);
				    }

				    System.out.println("inside phenomena_recall action " + returndata);
				    out.print(returndata.toString());
				}
				else if (action.equals("cause_recall.pcl"))
				{
				    ServletOutputStream out = response.getOutputStream();
				    JSONObject returndata = new JSONObject();

				    String keyId = request.getParameter("keyId");

				    if (BAL_UIUtils.isValidKeyId(keyId))
				    {
				        BAL_BdmTlCausemst bdmTlCausemst = phenCauseService.selectCause(keyId);
				        httpSession.removeAttribute("bdmTlCausemstphncsServlet");
				        httpSession.setAttribute("bdmTlCausemstphncsServlet", bdmTlCausemst);
				        JSONObject causeData = UIUtils.fromTpmModel(bdmTlCausemst);
				        returndata.put("causeData", causeData);
				    }

				    System.out.println("inside cause_recall action " + returndata);
				    out.print(returndata.toString());
				}
				else if (action.equals("PhenCause_delete.pcl")) {
				    BAL_PhenCauseBean phenCauseBean =
				        (BAL_PhenCauseBean) httpSession.getAttribute("phenCauseBean");

				    deletePcl(request, response, phenCauseBean);
				}
			   
				else if (action.equals("Cause_delete.pcl")) {
				    BAL_PhenCauseBean phenCauseBean =
				        (BAL_PhenCauseBean) httpSession.getAttribute("phenCauseBean");

				    deleteCausePcl(request, response, phenCauseBean);
				}
				 
			   // end
		}
	private void savePhenomena(HttpServletRequest request, HttpServletResponse response, BAL_PhenomenaBean phenBean  ) throws IOException
	{
		BAL_UIUtils.displayRequestParamsValue(request);
    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		if( phenBean == null)
    			phenBean = new BAL_PhenomenaBean(FormModes.create);
    		
    		BAL_BdmTlPhenomenamst newBdmTlPhenomenamst = new BAL_BdmTlPhenomenamst();
			newBdmTlPhenomenamst.setBphmCreatedby(user.getUsrm_ccno());
			newBdmTlPhenomenamst =(BAL_BdmTlPhenomenamst)BAL_UIUtils.setBeanProperties((Object)newBdmTlPhenomenamst,request);
			//added here priyanka 
			String assemblyId =request.getParameter("cmbbphmAssemblyid");
			//end
			newBdmTlPhenomenamst.setBphmAssemblyid(assemblyId);
			BAL_BdmTlPhenomenamst existBdmTlPhenomenamst = (BAL_BdmTlPhenomenamst)httpSession.getAttribute("bdmTlPhenomenamstphncsServlet");
			
			try{
				boolean insert = true;
				if( ! BAL_UIUtils.isValidKeyId( newBdmTlPhenomenamst.getBphmKeyid() ))
				{	
					existBdmTlPhenomenamst = phenCauseService.create(newBdmTlPhenomenamst,existBdmTlPhenomenamst,phenBean);
				}
				else{
					insert = false;
					existBdmTlPhenomenamst = phenCauseService.update(newBdmTlPhenomenamst,existBdmTlPhenomenamst,phenBean);
				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("BphmKeyid",existBdmTlPhenomenamst.getBphmKeyid() );
				System.out.println(" after save  11111");
			
				System.out.println(" after save  2");
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
			 
			 if( insert){
				msgPropertyIdnt = "success-save";
			 }else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			successData.put("BphmKeyid", existBdmTlPhenomenamst.getBphmKeyid());
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);		
			httpSession.removeAttribute("bdmTlPhenomenamstphncsServlet");
			httpSession.removeAttribute("bdmTlPhenomenamstphncsServlet");
			
	    	PrintWriter  out = response.getWriter();
			out.print(returnData.toString());
			out.close();
		}catch(ValidationExceptions e)
		{
			PrintWriter  out = response.getWriter();
			JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),"PhenCauseCreationException");
			errMessage.put("formActionMode",phenBean.getFormActionMode());
			out.print(errMessage.toString());
		}catch(BusinessApplicationExceptions e)
		{ 
			PrintWriter  out = response.getWriter();
			JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(e.toString(),"PhenCauseCreationException");
			out.print(errMessage.toString());
		}catch(Exception e)
		{
			PrintWriter  out = response.getWriter();
			JSONObject err = new JSONObject();
			err.put("tpmException",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
			out.print(err.toString());
		}
    }	
	}
	
	private void getPhenomenaColModel(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

	    PrintWriter out = response.getWriter();
	    HttpSession httpSession = request.getSession(false);

	    String assemblyId = request.getParameter("assemblyId");

	    try {
	        CommonFilter commonFilter = populatePhenomenaCommonFilter(request, "phenCommonFilter", true);
	        commonFilter.setIsGetCol("Y");

	        // add this field in CommonFilter if not already available
	        //commonFilter.setAssembly(assemblyId);
	        
	        List<String> assemblyIdList = new ArrayList<String>();
	        List<String[]> phenList = phenCauseService.getPhenomenaGridData(commonFilter, assemblyIdList);
	        //List<String[]> phenList = balBdmTlPhenomenamstService.getPhenomenaGridData(commonFilter);

	        JqGridTableModel jqGridTableModel = new JqGridTableModel();
	        GridColModel gridColModel = new GridColModel();

	        jqGridTableModel.setRowNumbers(true);
	        jqGridTableModel.setEnableFilter(true);

	        gridColModel.setHeaderNum(1);

	        String[] colHeader     = phenList.get(1);
	        String[] colHeaderCond = phenList.get(0);

	        List<String[]> headers = new ArrayList<String[]>();
	        headers.add(colHeader);

	        JSONObject jsonObject = UIUtils.getTableModel(
	                headers, colHeaderCond, jqGridTableModel, gridColModel);

	        jsonObject.put("tableHeight", "45%");
	        jsonObject.put("tableWidth", "55%");

	        httpSession.setAttribute("PhenListColModel", jsonObject);
	        httpSession.setAttribute("phenCommonFilter", commonFilter);

	        out.println(jsonObject);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("phenomena_getCol ERROR: " + e.getMessage());
	        e.printStackTrace();
	        out.println("{}");
	    }
	}
	
	private void getPhenomenaTblData(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

	    PrintWriter out = response.getWriter();
	    HttpSession httpSession = request.getSession(false);

	    try {
	        CommonFilter commonFilter = populatePhenomenaCommonFilter(request, "phenCommonFilter", false);
	        commonFilter.setIsGetCol("N");

	        List<String> assemblyIdList = new ArrayList<String>();
	        
	        //added by priyanka
	        String assmId = request.getParameter("assmId");
	        if (assmId != null && !assmId.trim().isEmpty()) {
	            assemblyIdList.add(assmId.trim());
	        }
	        //end

	        List<String[]> phenList = phenCauseService.getPhenomenaGridData(commonFilter, assemblyIdList);

	        CommonMessage.debugMsg("Phenomena rows returned = " + phenList.size());
	        CommonMessage.debugMsg("Phenomena TotalRecordCnt = " + commonFilter.getTotalRecordCnt());

	        JSONObject phenData = UIUtils.convertToJqGridTableObject(
	                phenList, request, 2, 0, commonFilter.getTotalRecordCnt());

	        httpSession.removeAttribute("phenCommonFilter");
	        httpSession.setAttribute("phenCommonFilter", commonFilter);

	        out.println(phenData);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("phenomena_getData ERROR: " + e.getMessage());
	        e.printStackTrace();
	        out.println("{}");
	    }
	}
	
	private void saveCause(HttpServletRequest request, HttpServletResponse response, BAL_CauseBean causeBean  ) throws IOException
	{

    	HttpSession httpSession = request.getSession(false);    	
    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		if( causeBean == null)
    			causeBean = new BAL_CauseBean(FormModes.create);
    		
    		BAL_BdmTlCausemst newBdmTlCausemst = new BAL_BdmTlCausemst();
    		newBdmTlCausemst.setBcsmName(request.getParameter("txtCauseName"));
    		newBdmTlCausemst.setBcsmRemarks(request.getParameter("txtCauseRemarks"));
    		newBdmTlCausemst.setBcsmPhenomenaid(request.getParameter("cmbbphmKeyid"));
			newBdmTlCausemst.setBcsmCreatedby(user.getUsrm_ccno());
			newBdmTlCausemst =(BAL_BdmTlCausemst)BAL_UIUtils.setBeanProperties((Object)newBdmTlCausemst,request);
			BAL_BdmTlCausemst existBdmTlCausemst = (BAL_BdmTlCausemst)httpSession.getAttribute("bdmTlCausemstphncsServlet");
			
			try{
				boolean insert = true;
				if( ! BAL_UIUtils.isValidKeyId( newBdmTlCausemst.getBcsmKeyid() ))
				{	
					existBdmTlCausemst = phenCauseService.create(newBdmTlCausemst,existBdmTlCausemst,causeBean);
				}
				else{
					insert = false;
					existBdmTlCausemst = phenCauseService.update(newBdmTlCausemst,existBdmTlCausemst,causeBean);
				}
				
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("BphmKeyid",existBdmTlCausemst.getBcsmKeyid() );
				System.out.println(" after save  11111");
			
				System.out.println(" after save  2");
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
			 
			 if( insert){
				msgPropertyIdnt = "success-save";
			 }else
				msgPropertyIdnt = "success-update";
			 
			successData.put("msg",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			successData.put("BcsmKeyid", existBdmTlCausemst.getBcsmKeyid());
			JSONObject returnData = new JSONObject();
			returnData.put("successData", successData);		
			httpSession.removeAttribute("bdmTlCausemstphncsServlet");
			httpSession.removeAttribute("bdmTlCausemstphncsServlet");
			
	    	PrintWriter  out = response.getWriter();
			out.print(returnData.toString());
			out.close();
		}catch(ValidationExceptions e)
		{
			PrintWriter  out = response.getWriter();
			JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),"PhenCauseCreationException");
			errMessage.put("formActionMode",causeBean.getFormActionMode());
			out.print(errMessage.toString());
		}catch(BusinessApplicationExceptions e)
		{ 
			PrintWriter  out = response.getWriter();
			JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(e.toString(),"PhenCauseCreationException");
			out.print(errMessage.toString());
		}catch(Exception e)
		{
			PrintWriter  out = response.getWriter();
			JSONObject err = new JSONObject();
			err.put("tpmException",BAL_UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
			out.print(err.toString());
		}
    }	
	}
	
	private void getCauseColModel(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

	    PrintWriter out = response.getWriter();
	    HttpSession httpSession = request.getSession(false);

	    String phenId = request.getParameter("phenId");

	    try {
	        CommonFilter commonFilter = populateCauseCommonFilter(request, "causeCommonFilter", true);
	        commonFilter.setIsGetCol("Y");

	        List<String[]> causeList = phenCauseService.getCauseGridData(commonFilter, phenId);

	        JqGridTableModel jqGridTableModel = new JqGridTableModel();
	        GridColModel gridColModel = new GridColModel();
	        jqGridTableModel.setRowNumbers(true);
	        jqGridTableModel.setEnableFilter(true);
	        gridColModel.setHeaderNum(1);

	        String[] colHeader     = causeList.get(1);
	        String[] colHeaderCond = causeList.get(0);
	        List<String[]> headers = new ArrayList<String[]>();
	        headers.add(colHeader);

	        JSONObject jsonObject = UIUtils.getTableModel(headers, colHeaderCond, jqGridTableModel, gridColModel);
	        jsonObject.put("tableHeight", "45%");
	        jsonObject.put("tableWidth",  "55%");

	        httpSession.setAttribute("CauseListColModel", jsonObject);
	        httpSession.setAttribute("causeCommonFilter", commonFilter);
	        httpSession.setAttribute("causePhenId", phenId); // remember it for getData call

	        out.println(jsonObject);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("cause_getCol ERROR: " + e.getMessage());
	        e.printStackTrace();
	        out.println("{}");
	    }
	}

	private void getCauseTblData(HttpServletRequest request, HttpServletResponse response)
	        throws Exception {

	    PrintWriter out = response.getWriter();
	    HttpSession httpSession = request.getSession(false);

	    try {
	        CommonFilter commonFilter = populateCauseCommonFilter(request, "causeCommonFilter", false);
	        commonFilter.setIsGetCol("N");

	        // phenId isn't resent by jqGrid's paging call, so pull it back from session
	        String phenId = (String) httpSession.getAttribute("causePhenId");

	        List<String[]> causeList = phenCauseService.getCauseGridData(commonFilter, phenId);

	        JSONObject causeData = UIUtils.convertToJqGridTableObject(
	                causeList, request, 2, 0, commonFilter.getTotalRecordCnt());

	        httpSession.removeAttribute("causeCommonFilter");
	        httpSession.setAttribute("causeCommonFilter", commonFilter);
	        out.println(causeData);

	    } catch (Exception e) {
	        CommonMessage.debugMsg("cause_getData ERROR: " + e.getMessage());
	        e.printStackTrace();
	    }
	}

	private void savePcl(HttpServletRequest request, HttpServletResponse response, BAL_PhenCauseBean phenCauseBean  ) throws IOException
	{
		CommonFunctions.debugMsg("Pcl MAIN");
		BAL_UIUtils.displayRequestParamsValue(request);
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		BAL_BdmTlPhncauselink existBdmTlPhncauselink = (BAL_BdmTlPhncauselink)httpSession.getAttribute("bdmTlPhncauselink"); 
    		
    		BAL_BdmTlPhenomenamst newBdmTlPhenomenamst = new BAL_BdmTlPhenomenamst();
    		//BAL_BdmTlCausemst newBdmTlCausemst = new BAL_BdmTlCausemst();
    			BAL_BdmTlPhncauselink newBdmTlPhncauselink = new BAL_BdmTlPhncauselink();    		
    		
    		BAL_BdmTlPhncauselink oldBdmTlPhncauselink = new BAL_BdmTlPhncauselink();
    		
    		CommonFunctions.debugMsg("Pcl DE");
    		
    		CommonFunctions.debugMsg(request.getParameter("hdnCombinedId"));
    		CommonFunctions.debugMsg(request.getParameter("cmbbphmAssemblyId"));
    		// added by priyanka 
    		//String assemblyId = request.getParameter("bphmAssemblyid");
    		//CommonFunctions.debugMsg("Request AssemblyId : " + assemblyId);
    		// end 
    		System.out.println("Request AssemblyId : " + request.getParameter("cmbbphmAssemblyId"));
    		System.out.println("Bean AssemblyId    : " + newBdmTlPhenomenamst.getBphmAssemblyid());
    		System.out.println("CombinedId         : " + phenCauseBean.getCombinedId());
    		CommonFunctions.debugMsg(request.getParameter("cmbbphmKeyid"));
    		CommonFunctions.debugMsg(request.getParameter("cmbbcsmKeyid"));
    		
    		newBdmTlPhenomenamst.setBphmCreatedby(user.getUsrm_ccno());    		
    		//newBdmTlCausemst.setBcsmCreatedby(user.getUsrm_ccno());
    		
    		CommonFunctions.debugMsg(newBdmTlPhenomenamst.getBphmRemarks());    		
    		
    		newBdmTlPhenomenamst =(BAL_BdmTlPhenomenamst)BAL_UIUtils.setBeanProperties((Object)newBdmTlPhenomenamst,request);
    		// added here by priyanka
    		String assemblyId = request.getParameter("bphmAssemblyid");

    		if (assemblyId != null && !assemblyId.trim().isEmpty()) {
    		    newBdmTlPhenomenamst.setBphmAssemblyid(assemblyId);
    		}
    		
    		String phenName = request.getParameter("phenName");
    		if (phenName != null && !phenName.trim().isEmpty()) {
    		    newBdmTlPhenomenamst.setBphmPhenomenaname(phenName.trim());
    		}

    		String remarksParam = request.getParameter("remarks");
    		if (remarksParam != null) {
    		    newBdmTlPhenomenamst.setBphmRemarks(remarksParam.trim());
    		}

    		System.out.println(
    		    "Final Assembly Id : "
    		    + newBdmTlPhenomenamst.getBphmAssemblyid()
    		);
    		
    		// end
    		//newBdmTlCausemst=(BAL_BdmTlCausemst)BAL_UIUtils.setBeanProperties((Object)newBdmTlCausemst,request);
    		
    		CommonFunctions.debugMsg("Phenomena Name :" +newBdmTlPhenomenamst.getBphmPhenomenaname());
    		newBdmTlPhncauselink =(BAL_BdmTlPhncauselink)BAL_UIUtils.setBeanProperties((Object)newBdmTlPhncauselink,request);
    		
			phenCauseBean =(BAL_PhenCauseBean) BAL_UIUtils.setBeanProperties((Object)phenCauseBean,request);
			
			if( newBdmTlPhenomenamst != null)
				newBdmTlPhncauselink.getBdmTlPhenomenamst().add(newBdmTlPhenomenamst);			
			//if( newBdmTlCausemst != null)
				//newBdmTlPhncauselink.getBdmTlCausemst().add(newBdmTlCausemst);
			
			CommonFunctions.debugMsg("Phenomena Name :" +newBdmTlPhenomenamst.getBphmPhenomenaname());
			CommonFunctions.debugMsg("session form type " + httpSession.getAttribute("formType"));
			
			try{
				CommonFunctions.debugMsg(" Phenomena KEYID() " +  newBdmTlPhenomenamst.getBphmKeyid());
				CommonFunctions.debugMsg(phenCauseBean.getFormActionMode());
				//if( newBdmTlPhenomenamst.getBphmKeyid() == null )
				// commented and added here by priyanka 
				/*
				 * if( phenCauseBean.getFormActionMode()=="Save") { existBdmTlPhncauselink
				 * =phenCauseService.create(newBdmTlPhncauselink, oldBdmTlPhncauselink,
				 * phenCauseBean); } else{ existBdmTlPhncauselink =
				 * phenCauseService.update(newBdmTlPhncauselink, oldBdmTlPhncauselink,
				 * phenCauseBean); }
				 */
				
				boolean existingPhen =
				        request.getParameter("chkExiPhen") != null;

				boolean existingCause =
				        request.getParameter("chkExiCause") != null;

				if(existingPhen || existingCause)
				{
				    phenCauseBean.setFormActionMode("Update");
				}
				
				if ("Save".equalsIgnoreCase(phenCauseBean.getFormActionMode()))
				{
				    existBdmTlPhncauselink =phenCauseService.create(newBdmTlPhncauselink,oldBdmTlPhncauselink,phenCauseBean);
				}
				else
				{
				    existBdmTlPhncauselink =phenCauseService.update(newBdmTlPhncauselink,oldBdmTlPhncauselink,phenCauseBean);
				}
				// end 
				httpSession.setAttribute(existBdmTlPhncauselink.getBpclOriginalid(), existBdmTlPhncauselink);
				httpSession.setAttribute("BdmTlPhenomenamst", existBdmTlPhncauselink);
				String formBeanIdentifier = "phenCauseBean"+phenCauseBean.getFormActionMode();
				httpSession.setAttribute(formBeanIdentifier,phenCauseBean);
						
				JSONObject mode = new JSONObject();
				mode.put("formMode",phenCauseBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("bphmKeyid",existBdmTlPhncauselink.getBpclOriginalid());
				//persistentData.put("bcsmKeyid",existBdmTlPhncauselink.getBpclOriginalid());
				persistentData.put("fromBean", formTypeIdentifier);
				
				JSONObject successData = new JSONObject();
				successData.put("msg","Data Saved Successfully");
				successData.put("mode",phenCauseBean.getFormMode() );
				successData.put("keyId", existBdmTlPhncauselink.getBpclOriginalid());
				JSONObject returnData = new JSONObject();					
				returnData.put("successData", successData);								
				out.print(returnData.toString());
				
/*				JSONObject forwardData = new JSONObject();
				forwardData.put("BPHMKeyid",existBdmTlPhncauselink.getBpclOriginalid());
				mode.put("forwardData",forwardData);
				mode.put("persistentData", persistentData);
				mode.put("tpmException", "Data  Saved / Updated ");
				out.print(mode.toString());			
*/				
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = BAL_UIUtils.validationExceptions(e.toString(),  "PhenCauseCreationException");
				errMessage.put("fromMode",phenCauseBean.getFormActionMode());
				out.print(errMessage.toString());
				
			}catch(BusinessApplicationExceptions e)
			{
				CommonFunctions.debugMsg("DNFKHDJFDK"+e.toString());
				JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(e.toString(),  "PhenCauseCreationException");
				out.print(errMessage.toString());					
			}catch(Exception e)
			{
				CommonFunctions.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				err.put("tpmException", "Data Not Saved");
				out.print(err.toString());
			}
    	}	
	}
	// added here by priyanka 
	
	private void deletePcl(HttpServletRequest request,
	        HttpServletResponse response,
	        BAL_PhenCauseBean phenCauseBean) throws IOException {

	    HttpSession httpSession = request.getSession(false);
	    ServletOutputStream out = response.getOutputStream();
	    AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);

	    if (httpSession != null && user != null) {

	        if (phenCauseBean == null) {
	            phenCauseBean = new BAL_PhenCauseBean();
	        }

	        BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();

	        try {
	            //String combinedId = request.getParameter("hdnCombinedId");
	            String phenId = request.getParameter("cmbbphmKeyid");
	            //String causeId = request.getParameter("cmbbcsmKeyid");
	            
	            System.out.println("Delete phenId : " + phenId);
	            
	            if (phenId == null || phenId.trim().isEmpty()) {
	                throw new Exception("Phenomena id is empty");
	            }

	            //System.out.println("Delete combinedId : " + combinedId);
	            //System.out.println("Delete phenId     : " + phenId);
	            //System.out.println("Delete causeId    : " + causeId);

	            //bdmTlPhncauselink.setBpclOriginalid(combinedId);
	            //bdmTlPhncauselink.setBpclElementid(causeId);
	            bdmTlPhncauselink.setBpclOriginalid(phenId);
	            bdmTlPhncauselink.setBpclElementid(phenId);
	            bdmTlPhncauselink.setBpclParentid(phenId);
	            bdmTlPhncauselink.setBpclElementtype("PHN");
	            bdmTlPhncauselink.setBpclActive("N");

	            bdmTlPhncauselink = phenCauseService.delete(bdmTlPhncauselink);

	            JSONObject successData = new JSONObject();
	            successData.put("msg", BAL_UIUtils.getPropertyValue(
	                    "com.akranta.tpm.resources.CommonMessages", "success-delete"));
	            successData.put("formActionMode", phenCauseBean.getFormActionMode());
	            //successData.put("keyId", "N");
	            successData.put("keyId", phenId);

	            JSONObject returnData = new JSONObject();
	            returnData.put("successData", successData);

	            httpSession.removeAttribute("bdmTlPhncauselink");
	            httpSession.removeAttribute("formBean");

	            out.print(returnData.toString());

	        } catch (ValidationExceptions e) {
	            JSONObject errMessage = BAL_UIUtils.validationExceptions(
	                    e.toString(), "PhenCauseCreationException");
	            errMessage.put("formActionMode", phenCauseBean.getFormActionMode());
	            out.print(errMessage.toString());

	        } catch (BusinessApplicationExceptions e) {
	            JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(
	                    e.toString(), "PhenCauseCreationException");
	            out.print(errMessage.toString());

	        } catch (Exception e) {
	            System.out.println("PhenCause Delete Error : " + e.getMessage());

	            JSONObject err = new JSONObject();
	            err.put("tpmException", BAL_UIUtils.getPropertyValue(
	                    "com.akranta.tpm.resources.CommonMessages", "err-delete"));
	            out.print(err.toString());
	        }
	    }
	}
	
	private void deleteCausePcl(HttpServletRequest request,
	        HttpServletResponse response,
	        BAL_PhenCauseBean phenCauseBean) throws IOException {

	    HttpSession httpSession = request.getSession(false);
	    ServletOutputStream out = response.getOutputStream();
	    AdmTlUsermst user = BAL_UIUtils.getLoginUser(request);

	    if (httpSession != null && user != null) {

	        if (phenCauseBean == null) {
	            phenCauseBean = new BAL_PhenCauseBean();
	        }

	        BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();

	        try {
	            String causeId = request.getParameter("cmbbcsmKeyid");

	            System.out.println("Delete causeId : " + causeId);

	            if (causeId == null || causeId.trim().isEmpty()) {
	                throw new Exception("Cause id is empty");
	            }

	            bdmTlPhncauselink.setBpclOriginalid(causeId);
	            bdmTlPhncauselink.setBpclElementid(causeId);
	            bdmTlPhncauselink.setBpclParentid(causeId);
	            bdmTlPhncauselink.setBpclElementtype("CSE");
	            bdmTlPhncauselink.setBpclActive("N");

	            bdmTlPhncauselink = phenCauseService.deleteCause(bdmTlPhncauselink);

	            JSONObject successData = new JSONObject();
	            successData.put("msg", BAL_UIUtils.getPropertyValue(
	                    "com.akranta.tpm.resources.CommonMessages", "success-delete"));
	            successData.put("formActionMode", phenCauseBean.getFormActionMode());
	            successData.put("keyId", causeId);

	            JSONObject returnData = new JSONObject();
	            returnData.put("successData", successData);

	            httpSession.removeAttribute("bdmTlPhncauselink");
	            httpSession.removeAttribute("formBean");

	            out.print(returnData.toString());

	        } catch (ValidationExceptions e) {
	            JSONObject errMessage = BAL_UIUtils.validationExceptions(
	                    e.toString(), "PhenCauseCreationException");
	            errMessage.put("formActionMode", phenCauseBean.getFormActionMode());
	            out.print(errMessage.toString());

	        } catch (BusinessApplicationExceptions e) {
	            JSONObject errMessage = BAL_UIUtils.businessValidationExceptions(
	                    e.toString(), "PhenCauseCreationException");
	            out.print(errMessage.toString());

	        } catch (Exception e) {
	            System.out.println("Cause Delete Error : " + e.getMessage());

	            JSONObject err = new JSONObject();
	            err.put("tpmException", BAL_UIUtils.getPropertyValue(
	                    "com.akranta.tpm.resources.CommonMessages", "err-delete"));
	            out.print(err.toString());
	        }
	    }
	}
	
	//end 
	 public String getImageUrl(String elementType)
	    {
	    	CommonFunctions.debugMsg("Inside getImage " +elementType);
	    	String imgUrl = null;
	    	if(elementType.equals("CMP"))
	    		imgUrl =  "images/FnLocn/company.jpg";
	    	else if(elementType.equals("LCN"))
	    		imgUrl =  "images/FnLocn/location.jpg";
	    	else if(elementType.equals("F"))
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
	    	else if(elementType.equals("PHN"))
	    		imgUrl =  "images/FnLocn/phen.png";
	    	else if(elementType.equals("CAS"))
	    		imgUrl =  "images/FnLocn/cause.png";
	    	else if(elementType.equals("PCL"))
	    		imgUrl =  "images/FnLocn/fc.png";
	    	
	    	return imgUrl;
	    	
	    }
	 
	
	 private List<String> getChildNodeElem(List<String[]> parentList,String formField) {
		 	
		 		List<String> childElem =  new ArrayList<String>();	 
		    	
		    	for(String[] pl :parentList)
		    	{
		    		CommonFunctions.debugMsg(pl.length + " : Length Of PL "+formField);
		    		String[] key = null;
		    		for(int i=0;i<pl.length;i++)
		    		{
		    			CommonFunctions.debugMsg(pl[i]+" : "+i);
		    			if(formField.equals("ASM"))
		    			{
		    				if(pl[i].substring(0, 3).equals("PHM"))
		    					childElem.add(pl[i]);
		    			}
		    			else if(formField.equals("PHM"))
		    			{
		    				if(pl[i].substring(0, 3).equals("CSM"))
		    					childElem.add(pl[i]);
		    			}		    			
		    			//else
		    				//childElem.add(pl[i]);
		    		}
		    		
		    	}
		       return childElem;
		}
	 
 private List<String> setPhenCauseModel(String funLocnGrid) {
			// TODO Auto-generated method stub
	 String[] originalId = funLocnGrid.split("::");	
	 List<String> paramValues = new ArrayList<String>();	
	 for(int i=0;i<originalId.length;i++)
	 {
			paramValues.add(originalId[i]);
	 }
			
	return paramValues;
 }
 private CommonFilter populatePhenomenaCommonFilter(HttpServletRequest request,String beanIdentifier,boolean createNew) {

	    HttpSession httpSession = request.getSession(false);
	    CommonFilter commonFilter = null;
	    

	    if (httpSession != null) {
	        commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
	    }

	    if (commonFilter != null && !createNew) {

	        FilterValues.setPaginationParams(request, commonFilter);
	        FilterValues.getCommonFilters(request, commonFilter);

	    } else {

	        commonFilter = new CommonFilter();
	        commonFilter = FilterValues.getCommonFilters(request, commonFilter);

	        // For phenomena, use BD related filters
	        commonFilter = FilterValues.getBDRelated(request, commonFilter);
	        commonFilter.setViewClick('Y');

	        if (httpSession != null) {
	            httpSession.removeAttribute(beanIdentifier);
	            httpSession.setAttribute(beanIdentifier, commonFilter);
	        }
	    }

		/*
		 * String assemblyId = request.getParameter("assemblyId");
		 * 
		 * if (BAL_UIUtils.isValidKeyId(assemblyId)) {
		 * commonFilter.setAssemblyid(assemblyId); }
		 */

	    commonFilter.setIsGetCol(createNew ? "Y" : "N");

	    if (Constants.passNullDate.contains(commonFilter.getFromMonth())
	            && (commonFilter.getMonwise() == null
	            || commonFilter.getMonwise().equals("Y"))) {

	        commonFilter.setFromMonth(
	                CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));

	        commonFilter.setToMonth(
	                CommonFunctions.getDate().substring(3, 11));

	        commonFilter.setMonwise("Y");
	    }

	    return commonFilter;
	}
 
	/*
	 * private CommonFilter populateCauseCommonFilter(HttpServletRequest request,
	 * String beanIdentifier, boolean createNew) { // same structure as
	 * populatePhenomenaCommonFilter... // plus: String phenId =
	 * request.getParameter("phenId"); if (BAL_UIUtils.isValidKeyId(phenId)) {
	 * commonFilter.setPhenomenaid(phenId); // add this field to CommonFilter if not
	 * present } return commonFilter; }
	 */
 
 private CommonFilter populateCauseCommonFilter(HttpServletRequest request, String beanIdentifier, boolean createNew) {

	    HttpSession httpSession = request.getSession(false);
	    CommonFilter commonFilter = null;

	    if (httpSession != null) {
	        commonFilter = (CommonFilter) httpSession.getAttribute(beanIdentifier);
	    }

	    if (commonFilter != null && !createNew) {
	        FilterValues.setPaginationParams(request, commonFilter);
	        FilterValues.getCommonFilters(request, commonFilter);
	    } else {
	        commonFilter = new CommonFilter();
	        commonFilter = FilterValues.getCommonFilters(request, commonFilter);
	        commonFilter.setViewClick('Y');

	        if (httpSession != null) {
	            httpSession.removeAttribute(beanIdentifier);
	            httpSession.setAttribute(beanIdentifier, commonFilter);
	        }
	    }

	    commonFilter.setIsGetCol(createNew ? "Y" : "N");
	    return commonFilter;
	}
 

}





