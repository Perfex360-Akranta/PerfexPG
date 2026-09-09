package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;


import com.akranta.tpm.bean.FunctLocFieldNameBean;
//import com.akranta.tpm.bean.GenTlFactorymstBean;
import com.akranta.tpm.bean.GenTlSectionmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFactorymst;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlPbumst;



import com.akranta.tpm.model.GenTlSectionmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.FunctionalLocnServices;
import com.akranta.tpm.service.GenTlSectionmstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.FunctionalLocnServicesImpl;
import com.akranta.tpm.service.impl.GenTlSectionmstServiceImpl;
//import com.akranta.tpm.service.impl.GenTlShiftmstServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;

	public class SectionmstServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static int count;
	String funcLocnHierarchy;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
		GenTlSectionmstService  genTlSectionmstService ;
		CommonFilterService commonFilterService;
		FunctionalLocnServices functionalLocnServices;
	
    public SectionmstServlet() {
        super();
        //	CommonMessage.debugMsg(" initialising servlet ....");
        /*try {
        	genTlSectionmstService = new GenTlSectionmstServiceImpl();
			commonFilterService = new CommonFilterServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
    private void process(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
    	HttpSession httpSession = request.getSession(false);
    
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
		if( httpSession != null && user != null)
    	{
			String action = UIUtils.getActionPart(request);
			try {
				functionalLocnServices = (FunctionalLocnServicesImpl)UIUtils.getServiceObject(request,"FunctionalLocnServicesImpl");
				genTlSectionmstService = (GenTlSectionmstServiceImpl)UIUtils.getServiceObject(request,"GenTlSectionmstServiceImpl");
				commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
			} catch (ServiceObjectCreationException e) {
				CommonMessage.debugMsg(e);
			}
			
			response.setContentType("text/html");
			response.setContentType("text/json");
				
			String dispatchUrl = null; 
			boolean delete = false;
			if (action.equals("section_input.sect")) 
			{
				String keyid = request.getParameter(ReqtParamNameConst.KEYID);
				String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
				String compId = request.getParameter(ReqtParamNameConst.COMPID);
				String factId = request.getParameter(ReqtParamNameConst.FACTID);
				CommonMessage.debugMsg(" userEvent  " + userEvent);
				String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
				response.setContentType("text/html");
				FormModes mode = FormModes.create;	
				String sectadd=request.getParameter("id");
				CommonMessage.debugMsg("sectadd+"+sectadd);
			
				GenTlSectionmst genTlSectionmst = null;
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				
				if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
					
					String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
					
					if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
					{
						mode =  FormModes.modify;
					}else if( formMode.equals( FormModeConsts.view)){
						mode=FormModes.view;
					}
					
					genTlSectionmst = genTlSectionmstService.select(keyid);
					httpSession.setAttribute("genTlSectionmstServlet" , genTlSectionmst);
					request.setAttribute("genTlSectionmst", genTlSectionmst);
					
				}
				if(UIUtils.isValidKeyId(compId )&& UIUtils.isValidKeyId(factId ))
				{
					
					genTlSectionmst = new GenTlSectionmst();
					genTlSectionmst.setSectCompanyid(compId);
					genTlSectionmst.setSectFactoryid(factId);
					request.setAttribute("genTlSectionmst", genTlSectionmst);
				}
				//mode=FormModes.view;
				
				GenTlSectionmstBean genTlSectionmstBean = new GenTlSectionmstBean(mode,lockFields);
				
				httpSession.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				request.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				request.setAttribute("genTlSectionmstServlet", genTlSectionmst);
				request.setAttribute("sectadd", sectadd);
				RequestDispatcher rd = request.getRequestDispatcher("/pages/sectionmaster.jsp");					
				rd.forward(request, response);
				/*if(sectadd!=null)
				{
				if(sectadd.equals("add"))
				{
				RequestDispatcher rd = request.getRequestDispatcher("/pages/sectionmasteradd.jsp");					
				rd.forward(request, response);
				}
				else if(sectadd.equals("update"))
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/sectionmaster.jsp");					
					rd.forward(request, response);
				}
				}
				else
				{
					RequestDispatcher rd = request.getRequestDispatcher("/pages/sectionmaster.jsp");					
					rd.forward(request, response);
											
				}*/

				//dispatchUrl = "/pages/sectionmaster.jsp";
			}
			else if(action.equals("section_save.sect"))
			{	
				GenTlSectionmstBean genTlSectionmstBean = (GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
		        savesect(request,response,genTlSectionmstBean);
			}
			else if( action.equals("section_delete.sect"))
			{	
				//GenTlSectionmstBean genTlSectionmstBean = (GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
				
				//deleteSect(request,response,genTlSectionmstBean);
				ServletOutputStream out = response.getOutputStream();
			//	delete = false;
		//	if(delete ){	
				
				GenTlSectionmstBean genTlSectionmstBean = (GenTlSectionmstBean)httpSession.getAttribute("genTlSectionmstBean");
			
				deleteSect(request,response,genTlSectionmstBean);
				
			/*} else 
				
				 user = UIUtils.getLoginUser(request);
				 JSONObject successData = new JSONObject();
				 successData.put("msg","Deletion is Restricted");
				 //successData.put("mode",genTlFactorymstBean.getFormMode() );
				 //successData.put("keyId", existGenTlFactorymst.getFactKeyid());
				 JSONObject returnData = new JSONObject();					 		
				 returnData.put("successData", successData);
				 returnData.put("formClear",  false);
				 out.print(returnData.toString());*/
			}
			
			else if( action.equals("DMT_recall.sect") )
			{
				PrintWriter out = response.getWriter();
				
				String DMTkeyid =request.getParameter(ReqtParamNameConst.KEYID);
				CommonMessage.debugMsg("KKKKKK   "+DMTkeyid);
				
				GenTlSectionmst genTlDmtmst=genTlSectionmstService.filldmtcontrol(DMTkeyid);
				
				CommonMessage.debugMsg(" Code :: "+genTlDmtmst.getSectCode()+" Name :: "+genTlDmtmst.getSectName() );
				
			     JSONObject  dmtmst =  UIUtils.fromTpmModel(genTlDmtmst);
				 CommonMessage.debugMsg("inside action" + genTlDmtmst );
				 JSONObject returndata = new JSONObject();
			
				 returndata.put("dmtmst", dmtmst);
				 out.print(returndata.toString());
			}
			
			
			
			else if(action.equals("functionalLocadd.sect"))
			{
			    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
		
					functLocFieldNameBean.setCompany("cmbComp");
			        functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSect");
					functLocFieldNameBean.setCell("cmbCell");
					functLocFieldNameBean.setMachine("cmbMachine");
					functLocFieldNameBean.setPbuMandatory(true);
					functLocFieldNameBean.setSbuDisable(false);
					functLocFieldNameBean.setPbuDisable(false);
			        functLocFieldNameBean.setSectDisable(true);
					functLocFieldNameBean.setCellDisable(true);
					functLocFieldNameBean.setMachDisable(true);
					
			
				
	            FormModes formModes = FormModes.create;
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
			}
		    
			else if(action.equals("functionalLoc.sect"))
			{
			    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
		
					functLocFieldNameBean.setCompany("cmbComp");
			        functLocFieldNameBean.setSbu("cmbSbu");
					functLocFieldNameBean.setPbu("cmbPbu");
					functLocFieldNameBean.setSection("cmbSect");
					functLocFieldNameBean.setCell("cmbCell");
					functLocFieldNameBean.setMachine("cmbMachine");
					functLocFieldNameBean.setPbuMandatory(true);
					functLocFieldNameBean.setSectMandatory(false);
					functLocFieldNameBean.setSbuDisable(false);
					functLocFieldNameBean.setPbuDisable(false);
			        functLocFieldNameBean.setSectDisable(true);
					functLocFieldNameBean.setCellDisable(true);
					functLocFieldNameBean.setMachDisable(true);
					
			
				
	            FormModes formModes = FormModes.create;
				UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
			}
		    
			
			else if(action.equals("section_recall.sect"))
			{	
				
				ServletOutputStream out = response.getOutputStream();
				String sect =request.getParameter(ReqtParamNameConst.KEYID);
				CommonMessage.debugMsg("KKKKKK   "+sect);

				GenTlSectionmstBean genTlSectionmstBean = new GenTlSectionmstBean(FormModes.modify);
				
				httpSession.removeAttribute("genTlSectionmstBean");
				httpSession.setAttribute("genTlSectionmstBean", genTlSectionmstBean);
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				GenTlSectionmst genTlSectionmst = genTlSectionmstService.select(sect);
				httpSession.setAttribute("genTlSectionmstServlet",genTlSectionmst);
				JSONObject  section =  UIUtils.fromTpmModel(genTlSectionmst);
				
				JSONObject returndata = new JSONObject();
				returndata.put("section", section);
				out.print(returndata.toString());
				
		    	
			}
	
		
			if (dispatchUrl != null)
			{
				UIUtils.forwardRequest(request, response, dispatchUrl);
			}
		}
	}
    
    
	private void functionalLocnHierarchy(HttpServletRequest request,HttpServletResponse response, String keyid) throws IOException {
		
    	
		try {
			funcLocnHierarchy = commonFilterService.getFuncLocnHierarchy(keyid);
			CommonMessage.debugMsg("funcLocnHierarchy---------"+funcLocnHierarchy);
			//UIUtils.writeFunctionlocnHirearchy(response, funcLocnHierarchy);
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void savesect(HttpServletRequest request, HttpServletResponse response,GenTlSectionmstBean genTlSectionmstBean  ) throws IOException{
		
    	HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		CommonMessage.debugMsg("secccc");
			GenTlSectionmst newGenTlSectionmst = new GenTlSectionmst();
			newGenTlSectionmst.setSectCreatedby(user.getUsrm_ccno());
			if( genTlSectionmstBean == null)
				genTlSectionmstBean = new GenTlSectionmstBean(FormModes.create);
			
			newGenTlSectionmst =(GenTlSectionmst)UIUtils.setBeanProperties((Object)newGenTlSectionmst,request);
			
			String locId = request.getParameter(ReqtParamNameConst.LOCNID);
			CommonMessage.debugMsg("location:::::"+locId);
			 String originalId =newGenTlSectionmst.getSectFactoryid();
		 CommonMessage.debugMsg("newGenTlSectionmst.getSectFlid()*********"+newGenTlSectionmst.getSectFlid());
				CommonMessage.debugMsg("newGenTlSectionmst.getSectFactoryid()*********"+newGenTlSectionmst.getSectFactoryid());
				
				functionalLocnHierarchy(request,response,originalId);
			
				String sbu=funcLocnHierarchy.substring(22,32);
				String locat=funcLocnHierarchy.substring(11,21);
			newGenTlSectionmst.setLocation(locat);
			newGenTlSectionmst.setSbu(sbu) ;
			
			//String sbuid=request.getParameter(ReqtParamNameConst.MACHID);
			//String sbuid2=request.getParameter(ReqtParamNameConst.ASSMID);
			//CommonMessage.debugMsg("sbuid:::::"+sbuid+sbuid2);
			//genTlSectionmstBean =(GenTlSectionmstBean) UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
			GenTlSectionmst existGenTlSectionmst = (GenTlSectionmst)httpSession.getAttribute("genTlSectionmstServlet");
			
			try{
				boolean insert = true;
				if( ! UIUtils.isValidKeyId( newGenTlSectionmst.getSectKeyid() ))
				{	
					existGenTlSectionmst = genTlSectionmstService.create(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
					String funLocnGrid[] =new String[3];
					
					funLocnGrid[0]=existGenTlSectionmst.getSectKeyid();
					funLocnGrid[1]=existGenTlSectionmst.getSectName();
					funLocnGrid[2]=existGenTlSectionmst.getSectCode();
					
					//funLocnGrid.add(existGenTlSectionmst.getCellKeyid());
					//funLocnGrid.add(existGenTlSectionmst.getCellName());
					//funLocnGrid.add(existGenTlSectionmst.getCellCode());
					
					CommonMessage.debugMsg("funLocnGrid:::"+funLocnGrid);
					
					//CommonMessage.debugMsg("funLocnGrid:::::"+funLocnGrid[0]+funLocnGrid[1]+funLocnGrid[2]);
                      FunctionalLocn functionalLocn = new FunctionalLocn();
                      GenTlFunctionallocn funalocn;
                      funalocn=existGenTlSectionmst.getGenTlFunctionallocn();
                      String parentid;
					CommonMessage.debugMsg("parentid:::::"+funalocn.getFnlnElementid());
					parentid=funalocn.getFnlnElementid();
					CommonMessage.debugMsg("SUBSTRING::::"+parentid.substring(7,32));
					String parentid1=parentid.substring(7,43);
					CommonMessage.debugMsg("SUBSTRING1::::"+parentid.substring(0,43));
			    	functionalLocn.setParentId(parentid1);
			    	
			    	CommonMessage.debugMsg("parentid1"+parentid1);
			    /*	String[] funcn = (String[]) funLocnGrid.toArray(); 
			    	//String funcnloc=(String)funcn;
			    	String func1=Arrays.toString(funcn);*/
			    	String funcn=Arrays.toString(funLocnGrid);
			    	CommonMessage.debugMsg("funcn"+funcn);
			    	int funclength=funcn.length();
			    	String funcn1=funcn.substring(1,funclength-1);
			    	CommonMessage.debugMsg("funcn1;;;;"+funcn1);
			    
			    	//List<String> locnValues = setFunLocnModel(funcn.substring(1,31));
			    	List<String> locnValues = setFunLocnModel(funcn1);
			       functionalLocn = functionalLocnServices.create(functionalLocn,locnValues);
			 	CommonMessage.debugMsg("functionalLocn---->"+functionalLocn);
			 	/* JSONObject jSONObject = new JSONObject();
	    		   JSONObject data = new JSONObject();
	    		   JSONObject jsonAttr = new JSONObject();
	               JSONObject metadata = new JSONObject();
	               
	             CommonMessage.debugMsg("functionalLocn"+functionalLocn.getDisplayCode()+"/n"
	            		 +functionalLocn.getElementId()+functionalLocn.getElementType()+functionalLocn.getOriginalId()+functionalLocn.getParentId()+functionalLocn.getParentNumber());*/
	            		 

	     
	            
	             
	              
	           String parentNumberfun = parentid.substring(7,43); 
	   			String parentIdfun = parentid.substring(0,43); 
	   			String elementTypefun ="PBU";
	   		 int parentlength=parentNumberfun.length();
	   		 
	   		 CommonMessage.debugMsg(parentlength);
	   			
	   			//parentNumber=request.getParameter("elementId");
	   	
	   			CommonMessage.debugMsg("PARENT NUMBER"+parentNumberfun);
	   			//parentIdfun =functionalLocn.getParentId();
	   			CommonMessage.debugMsg("PARENt id"+parentIdfun);
	   			
	   			//elementTypefun = functionalLocn.getElementType();
	   			CommonMessage.debugMsg("elementType::::"+elementTypefun);
	   			
	   			parentNumberfun  = parentNumberfun.equals("0") ? "0" :parentNumberfun;
	   			CommonMessage.debugMsg("parentNumber::::"+parentNumberfun);
	   			//CommonMessage.debugMsg("ID : "+parentNumberfun.substring(33,43));
	   	    	response.setContentType("text/html;charset=UTF-8");
	   	   
	   	    	CommonMessage.debugMsg("***********************************************");
	   	    	String id="0";
	   	    	try {
		    		JSONArray jSONArray = new JSONArray();	    		
			    	//if(request.getParameter("id").equals("0"))
		    		 if(("id").equals("0"))
			    	{
		    			 CommonMessage.debugMsg("INSDIE THE TRY BLOCK IF STATEMENT");
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
			    	else{
	   	    	
		    		//JSONArray jSONArray = new JSONArray();
		    	FunctionalLocn functionalLocn1 = new FunctionalLocn();	        	
		        	functionalLocn1.setElementId(parentNumberfun);
		        	functionalLocn1.setParentId(parentIdfun);
		        	functionalLocn1.setElementType(elementTypefun);
		        	List <FunctionalLocn> locnList = functionalLocnServices.getAllLocation(functionalLocn1);
		        	CommonMessage.debugMsg("locnList::::"+locnList);
		        	for(int i=0; i<locnList.size(); i++){
		        		JSONObject jSONObject = new JSONObject();
	        			JSONObject data = new JSONObject();
	        			JSONObject jsonAttr = new JSONObject();
		                JSONObject metadata = new JSONObject();
		        	CommonMessage.debugMsg("//////////////////////");
		                CommonMessage.debugMsg(locnList.get(i).getOriginalId());
		        		CommonMessage.debugMsg(locnList.get(i).getElementId());
		        		CommonMessage.debugMsg(locnList.get(i).getParentId());
		        	 CommonMessage.debugMsg(locnList.get(i).getElementType());
		        	 CommonMessage.debugMsg(locnList.get(i).getDisplayCode());
		        	 
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
	        			CommonMessage.debugMsg("JSONATTRBUTE+"+jsonAttr);
	        			//data.put("attr", jsonAttr);
	        			data.put("icon", "");
	        			jSONObject.put("data",data);
	        			jSONObject.put("attr", jsonAttr);	
	        			if(request.getParameter("search_str") != null)
	        			{
	        				jSONObject.put("state","open");
	        				CommonMessage.debugMsg("INSIDE THE IF CONDIION");
	        			}
	        			else
	        	
	        				jSONObject.put("state","closed");
	        				
	        			CommonMessage.debugMsg("INSIDE THE ESLE CONDIION");
		                metadata.put("id", i);
		                jSONObject.put("metadata",metadata);
		                jSONObject.put("icon",getIconImage(locnList.get(i).getElementType()));
			            //jSONObject.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
			           CommonMessage.debugMsg("JSONON+BJECT"+ jSONObject);  
			            //jSONObject.put("icon","../images/fav.png");
		                jsonAttr = null;
		                jSONObject.put("children","[{}]");
		                jSONArray.put(jSONObject);
		                jSONObject=null;
		        	}
		        	  }  
		    	CommonMessage.debugMsg(jSONArray);
		    	//PrintWriter pw=response.getWriter();
		    	CommonMessage.debugMsg("*****************");
		    	//pw.print(jSONArray);
		        CommonMessage.debugMsg("-----------------");
	           //out.print(jSONArray);
        	   jSONArray=null;
		      
	    
        	   	CommonMessage.debugMsg(jSONArray);
		    	CommonMessage.debugMsg("///////////////////////");
	          // out.print(jSONArray);
	           CommonMessage.debugMsg("*************************");
        	  // jSONArray=null;
	    	}catch(Exception e){
	           // CommonMessage.debugMsg(e);
	            e.printStackTrace();
	        }
	      /*  finally {
	            out.close();
	        }*/
	               
	               

	               
				
				
				
				
				}
				else{
					insert = false;
					existGenTlSectionmst = genTlSectionmstService.update(newGenTlSectionmst,existGenTlSectionmst,genTlSectionmstBean);
					
                     String funLocnGrid[] =new String[3];
					
					funLocnGrid[0]=existGenTlSectionmst.getSectKeyid();
					funLocnGrid[1]=existGenTlSectionmst.getSectName();
					funLocnGrid[2]=existGenTlSectionmst.getSectCode();
					
					//funLocnGrid.add(existGenTlSectionmst.getCellKeyid());
					//funLocnGrid.add(existGenTlSectionmst.getCellName());
					//funLocnGrid.add(existGenTlSectionmst.getCellCode());
					
					CommonMessage.debugMsg("funLocnGrid:::"+funLocnGrid);
					
					//CommonMessage.debugMsg("funLocnGrid:::::"+funLocnGrid[0]+funLocnGrid[1]+funLocnGrid[2]);
                      FunctionalLocn functionalLocn = new FunctionalLocn();
                      GenTlFunctionallocn funalocn;
                      funalocn=existGenTlSectionmst.getGenTlFunctionallocn();
                      String parentid;
					CommonMessage.debugMsg("parentid:::::"+funalocn.getFnlnElementid());
					parentid=funalocn.getFnlnElementid();
					CommonMessage.debugMsg("SUBSTRING::::"+parentid.substring(7,32));
					String parentid1=parentid.substring(7,43);
					CommonMessage.debugMsg("SUBSTRING1::::"+parentid.substring(0,43));
			    	functionalLocn.setParentId(parentid1);
			    	
			    	CommonMessage.debugMsg("parentid1"+parentid1);
			    /*	String[] funcn = (String[]) funLocnGrid.toArray(); 
			    	//String funcnloc=(String)funcn;
			    	String func1=Arrays.toString(funcn);*/
			    	String funcn=Arrays.toString(funLocnGrid);
			    	CommonMessage.debugMsg("funcn"+funcn);
			    	int funclength=funcn.length();
			    	String funcn1=funcn.substring(1,funclength-1);
			    	CommonMessage.debugMsg("funcn1;;;;"+funcn1);
			    
			    	//List<String> locnValues = setFunLocnModel(funcn.substring(1,31));
			    	List<String> locnValues = setFunLocnModel(funcn1);
			       functionalLocn = functionalLocnServices.create(functionalLocn,locnValues);
			 	CommonMessage.debugMsg("functionalLocn---->"+functionalLocn);
			 	/* JSONObject jSONObject = new JSONObject();
	    		   JSONObject data = new JSONObject();
	    		   JSONObject jsonAttr = new JSONObject();
	               JSONObject metadata = new JSONObject();
	               
	             CommonMessage.debugMsg("functionalLocn"+functionalLocn.getDisplayCode()+"/n"
	            		 +functionalLocn.getElementId()+functionalLocn.getElementType()+functionalLocn.getOriginalId()+functionalLocn.getParentId()+functionalLocn.getParentNumber());*/
	            		 

	     
	            
	             
	              
	           String parentNumberfun = parentid.substring(7,43); 
	   			String parentIdfun = parentid.substring(0,43); 
	   			String elementTypefun ="PBU";
	   		 int parentlength=parentNumberfun.length();
	   		 
	   		 CommonMessage.debugMsg(parentlength);
	   			
	   			//parentNumber=request.getParameter("elementId");
	   	
	   			CommonMessage.debugMsg("PARENT NUMBER"+parentNumberfun);
	   			//parentIdfun =functionalLocn.getParentId();
	   			CommonMessage.debugMsg("PARENt id"+parentIdfun);
	   			
	   			//elementTypefun = functionalLocn.getElementType();
	   			CommonMessage.debugMsg("elementType::::"+elementTypefun);
	   			
	   			parentNumberfun  = parentNumberfun.equals("0") ? "0" :parentNumberfun;
	   			CommonMessage.debugMsg("parentNumber::::"+parentNumberfun);
	   			//CommonMessage.debugMsg("ID : "+parentNumberfun.substring(33,43));
	   	    	response.setContentType("text/html;charset=UTF-8");
	   	   
	   	    	CommonMessage.debugMsg("***********************************************");
	   	    	String id="0";
	   	    	try {
		    		JSONArray jSONArray = new JSONArray();	    		
			    	//if(request.getParameter("id").equals("0"))
		    		 if(("id").equals("0"))
			    	{
		    			 CommonMessage.debugMsg("INSDIE THE TRY BLOCK IF STATEMENT");
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
			    	else{
	   	    	
		    		//JSONArray jSONArray = new JSONArray();
		    	FunctionalLocn functionalLocn1 = new FunctionalLocn();	        	
		        	functionalLocn1.setElementId(parentNumberfun);
		        	functionalLocn1.setParentId(parentIdfun);
		        	functionalLocn1.setElementType(elementTypefun);
		        	List <FunctionalLocn> locnList = functionalLocnServices.getAllLocation(functionalLocn1);
		        	CommonMessage.debugMsg("locnList::::"+locnList);
		        	for(int i=0; i<locnList.size(); i++){
		        		JSONObject jSONObject = new JSONObject();
	        			JSONObject data = new JSONObject();
	        			JSONObject jsonAttr = new JSONObject();
		                JSONObject metadata = new JSONObject();
		        	CommonMessage.debugMsg("//////////////////////");
		                CommonMessage.debugMsg(locnList.get(i).getOriginalId());
		        		CommonMessage.debugMsg(locnList.get(i).getElementId());
		        		CommonMessage.debugMsg(locnList.get(i).getParentId());
		        	 CommonMessage.debugMsg(locnList.get(i).getElementType());
		        	 CommonMessage.debugMsg(locnList.get(i).getDisplayCode());
		        	 
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
	        			CommonMessage.debugMsg("JSONATTRBUTE+"+jsonAttr);
	        			//data.put("attr", jsonAttr);
	        			data.put("icon", "");
	        			jSONObject.put("data",data);
	        			jSONObject.put("attr", jsonAttr);	
	        			if(request.getParameter("search_str") != null)
	        			{
	        				jSONObject.put("state","open");
	        				CommonMessage.debugMsg("INSIDE THE IF CONDIION");
	        			}
	        			else
	        	
	        				jSONObject.put("state","closed");
	        				
	        			CommonMessage.debugMsg("INSIDE THE ESLE CONDIION");
		                metadata.put("id", i);
		                jSONObject.put("metadata",metadata);
		                jSONObject.put("icon",getIconImage(locnList.get(i).getElementType()));
			            //jSONObject.put("imgUrl",getImageUrl(locnList.get(i).getElementType()));
			           CommonMessage.debugMsg("JSONON+BJECT"+ jSONObject);  
			            //jSONObject.put("icon","../images/fav.png");
		                jsonAttr = null;
		                jSONObject.put("children","[{}]");
		                jSONArray.put(jSONObject);
		                jSONObject=null;
		        	}
		        	  }  
		    	CommonMessage.debugMsg(jSONArray);
		    	//PrintWriter pw=response.getWriter();
		    	CommonMessage.debugMsg("*****************");
		    	//pw.print(jSONArray);
		        CommonMessage.debugMsg("-----------------");
	           //out.print(jSONArray);
        	   jSONArray=null;
		      
	    
        	   	CommonMessage.debugMsg(jSONArray);
		    	CommonMessage.debugMsg("///////////////////////");
	          // out.print(jSONArray);
	           CommonMessage.debugMsg("*************************");
        	  // jSONArray=null;
	    	}catch(Exception e){
	           // CommonMessage.debugMsg(e);
	            e.printStackTrace();
	        }
	      /*  finally {
	            out.close();
	        }*/
				}
				
							
			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("SectKeyid",existGenTlSectionmst.getSectKeyid() );
				
				JSONObject successData = new JSONObject();
			    String msgPropertyIdnt;
				 
				 if( insert){
					msgPropertyIdnt = "success-save";
				 }else
					msgPropertyIdnt = "success-update";
				 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
			
				//successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode() );
				successData.put("SectKeyid", existGenTlSectionmst.getSectKeyid());
				JSONObject returnData = new JSONObject();
				
				returnData.put("successData", successData);
				
				httpSession.removeAttribute("genTlSectionmstServlet");
				httpSession.removeAttribute("genTlSectionmstBean");
				
				out.print(returnData.toString());
				out.close();
			}catch(ValidationExceptions e)
			{
				//PrintWriter  out = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"sectionCreationException");
				CommonMessage.debugMsg("errMessage:::"+errMessage);
				errMessage.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
				//errMessage.put("FormActionMode","This section code is already Exist");
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				//	PrintWriter  out = response.getWriter();
					CommonMessage.debugMsg("BusinessApplicationExcepions"  );
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),"sectionCreationException");
					out.print(errMessage.toString());
					
					
			}catch(Exception e)
			{
				e.printStackTrace();
				//PrintWriter  out = response.getWriter();
				CommonMessage.debugMsg("gete. " + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.SectionError","err-save"));
				out.print(err.toString());
			}
    	}	
    }
    
	 public String getIconImage(String elementType)
	    {
	    	CommonMessage.debugMsg("Inside getIcon " +elementType);
	    	String imgUrl = null;
	    	CommonMessage.debugMsg("ELEMENT TYPE++++++++++++++"+elementType);
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
	    	CommonMessage.debugMsg("ELEMENT TYPE IN IMAGE URL++++++++++++++"+elementType);
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
	    		//imgUrl =  "images/sect.jpg";
	    		imgUrl   =  "images/FnLocn/section.jpg";
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
	    
    
    
    private List<String> setFunLocnModel(String funLocnGrid) {
    	// TODO Auto-generated method stub
    	CommonMessage.debugMsg("setFunLocnModel"+funLocnGrid);
        String[] originalId = funLocnGrid.split("::");	
    	CommonMessage.debugMsg("originalId"+originalId);
    	List<String> paramValues = new ArrayList<String>();	
    	for(int i=0;i<originalId.length;i++)
    	{
    		paramValues.add(originalId[i]);
    	}
    	//((Object) paramValues).subString(1,34);
    	CommonMessage.debugMsg("paramValues::::"+paramValues);
    	return paramValues;
        }
    private void deleteSect(HttpServletRequest request,HttpServletResponse response, GenTlSectionmstBean genTlSectionmstBean) throws IOException
	{
		HttpSession httpSession = request.getSession(false);
    	ServletOutputStream out = response.getOutputStream();
    	AdmTlUsermst user = UIUtils.getLoginUser(request);
    	
    	if( httpSession != null && user != null)
    	{	
    		GenTlSectionmst existGenTlSectionmst = (GenTlSectionmst)httpSession.getAttribute("genTlSectionmstServlet"); 
    	 
    		
    		GenTlSectionmst newGenTlSectionmst = new GenTlSectionmst();
    
    		
    		newGenTlSectionmst.setSectCreatedby(user.getUsrm_ccno());
    
    		
    		CommonMessage.debugMsg("old id::::"+ existGenTlSectionmst);
    		
    		newGenTlSectionmst =(GenTlSectionmst)UIUtils.setBeanProperties((Object)newGenTlSectionmst,request);
    		genTlSectionmstBean =(GenTlSectionmstBean)UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
    		
    		genTlSectionmstBean =(GenTlSectionmstBean) UIUtils.setBeanProperties((Object)genTlSectionmstBean,request);
    		
	
		try{
			String inactMode =request.getParameter("hdnInactive");
			CommonMessage.debugMsg(inactMode);
			String toInactiveMsg =null;
			if ( inactMode.equals("Inactive") ){
				
				existGenTlSectionmst = genTlSectionmstService.delete("I",newGenTlSectionmst);
				toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactivated");
			}
			else{
				existGenTlSectionmst = genTlSectionmstService.delete("D",newGenTlSectionmst);
				toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");
			}
			
			
			//httpSession.setAttribute(existGenTlSectionmst.getSectKeyid(), existGenTlSectionmst);
			//httpSession.setAttribute("genTlSectionmst", existGenTlSectionmst);
			//String formBeanIdentifier = "GenTlSectionmstBean"+genTlSectionmstBean.getFormActionMode();
			//httpSession.setAttribute(formBeanIdentifier,genTlSectionmstBean);
			
			existGenTlSectionmst.setSectKeyid("N");
			
			JSONObject mode = new JSONObject();
			//mode.put("formMode",genTlSectionmstBean.getFormActionMode());
			JSONObject persistentData = new JSONObject(); 
			persistentData.put("SectKeyid",existGenTlSectionmst.getSectKeyid());
			//persistentData.put("fromBean", formBeanIdentifier);
			
			JSONObject forwardData = new JSONObject();
			forwardData.put("SectKeyid",existGenTlSectionmst.getSectKeyid());

			mode.put("forwardData", forwardData);
			mode.put("persistentData", persistentData);
			
			JSONObject successData = new JSONObject();
			
			successData.put("msg",toInactiveMsg);
					//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			//successData.put("msg","Data Deleted Successfully");
			successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode() );
			successData.put("SectKeyid", existGenTlSectionmst.getSectKeyid());
			JSONObject returnData = new JSONObject();
			
			returnData.put("successData", successData);		
			out.print(returnData.toString());
			
	
		}catch(ValidationExceptions e)
		{
			JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"sectionCreationException");
			errMessage.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
			out.print(errMessage.toString());
		}catch(BusinessApplicationExceptions e)
		{ 
			CommonMessage.debugMsg("BusinessApplicationExcepions");
			JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),"sectionCreationException");
			//successData.put("FormActionMode",genTlSectionmstBean.getFormActionMode());
			//successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
			successData.put("msg", "Original Id Exists");
			JSONObject returnData = new JSONObject();	
			returnData.put("successData", successData);	
			returnData.put("formClear",false);
			
			//out.print(successData.toString());
			
			out.print(returnData.toString());
			

						
		}catch(Exception e)
		{
			CommonMessage.debugMsg("Error Msg:" + e.getMessage());
			JSONObject err = new JSONObject();
			//err.put("tpmException", "Data Not Deleted");
			err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.SectionError","err-save"));
			out.print(err.toString());
		}
    }
	}

}
