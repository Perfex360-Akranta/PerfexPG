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
import com.akranta.tpm.bean.GenTlCellmstBean;
//import com.akranta.tpm.bean.GenTlSectionmstBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFunctionallocn;
//import com.akranta.tpm.model.GenTlSectionmst;




import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.service.CommonFilterService;
import com.akranta.tpm.service.FunctionalLocnServices;
import com.akranta.tpm.service.GenTlCellmstService;
import com.akranta.tpm.service.impl.CommonFilterServiceImpl;
import com.akranta.tpm.service.impl.FunctionalLocnServicesImpl;
import com.akranta.tpm.service.impl.GenTlCellmstServiceImpl;
//import com.akranta.tpm.service.impl.GeneralmaintServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.FormModeConsts;
import com.akranta.tpm.utils.ReqtParamNameConst;


		public class GenTlCellmstServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
				private static int count;
				String sbuvalue;
				String funcLocnHierarchy;
				
		
	    /**
	     * @see HttpServlet#HttpServlet()
	     */
			GenTlCellmstService  genTlCellmstService ;
			CommonFilterService commonFilterService;
			FunctionalLocnServices functionalLocnServices;
			
	    public GenTlCellmstServlet() {
	        super();
	        	CommonMessage.debugMsg(" initialising servlet ....");
/*	        try {
	        	genTlCellmstService = new GenTlCellmstServiceImpl();
				commonFilterService = new CommonFilterServiceImpl();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		*/	
	      
	    }

		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
	    	
	    	
	    	
	    	CommonFilter  commonFilter = new CommonFilter();
	    	ComboFilter currentFilter = new ComboFilter();
	    	String filter = request.getParameter("q");
			currentFilter.setCode(filter);
			currentFilter.setName(filter);
			List<ComboBox> comboList = new ArrayList<ComboBox>();
		
	
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
			if( httpSession != null && user != null)
	    	{
				response.setContentType("text/html");
				response.setContentType("text/json");
				String dispatchUrl = null; 
			    String action = UIUtils.getActionPart(request);
				try {
					functionalLocnServices = (FunctionalLocnServicesImpl)UIUtils.getServiceObject(request,"FunctionalLocnServicesImpl");
					genTlCellmstService = (GenTlCellmstServiceImpl)UIUtils.getServiceObject(request,"GenTlCellmstServiceImpl");
					commonFilterService = (CommonFilterServiceImpl)UIUtils.getServiceObject(request,"CommonFilterServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}	

				
					if (action.equals("cell_input.cell")) 
					{
						String keyid = request.getParameter(ReqtParamNameConst.KEYID);
						CommonMessage.debugMsg("INSIDE SERVLET:::::"+keyid);
						String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
						//String compId = request.getParameter(ReqtParamNameConst.COMPID);
						String factId = request.getParameter(ReqtParamNameConst.FACTID);
						String sectId = request.getParameter(ReqtParamNameConst.SECTID);
						String locId = request.getParameter(ReqtParamNameConst.LOCNID);
						String celladd=request.getParameter("id");
						CommonMessage.debugMsg("sbuadd+"+celladd);
						
						CommonMessage.debugMsg("LOCID:::::"+locId);
						
						CommonMessage.debugMsg(" userEvent  " + userEvent);
						String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
						response.setContentType("text/html");
						FormModes mode = FormModes.create;	
					
						GenTlCellmst genTlCellmst = null;
						
						httpSession.removeAttribute("genTlCellmstServlet");
						
					if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						genTlCellmst = genTlCellmstService.select(keyid);
						genTlCellmst = genTlCellmstService.fillcellcontrol(keyid);
						CommonMessage.debugMsg("genTlCellmst::::"+genTlCellmst);
						if(genTlCellmst.getCellEffectivedate() != null)
							genTlCellmst.setCellEffectivedate(genTlCellmst.getCellEffectivedate().substring(0, 11));
						httpSession.setAttribute("genTlCellmstServlet" , genTlCellmst);
						request.setAttribute("genTlCellmst", genTlCellmst);
					}
					if( UIUtils.isValidKeyId(factId )&& UIUtils.isValidKeyId(sectId ))
					{
						
						genTlCellmst = new GenTlCellmst();
						//genTlCellmst.setCellCompanyid(compId);
						
						
						genTlCellmst.setCellFactoryid(factId);
						genTlCellmst.setCellSectionid(sectId);
						genTlCellmst.setLocation(locId);
						CommonMessage.debugMsg("genTlCellmst.getCellFactoryid"+genTlCellmst.getCellFactoryid());
						CommonMessage.debugMsg("genTlCellmst.getCellSectionid"+genTlCellmst.getCellSectionid());
						String compId = genTlCellmstService.getCompany(genTlCellmst);
						//String locId = genTlCellmstService.getLocation(genTlCellmst);
						if(UIUtils.isValidKeyId(compId ))
							genTlCellmst.setCellCompanyid(compId.substring(0, compId.indexOf("-")));
						genTlCellmst.setLocation(locId.substring(0, locId.indexOf("-")));
						request.setAttribute("genTlCellmst", genTlCellmst);
					}
						//mode=FormModes.view;
						
						GenTlCellmstBean genTlCellmstBean = new GenTlCellmstBean(mode,lockFields);
						
						httpSession.setAttribute("genTlCellmstBean", genTlCellmstBean);
						request.setAttribute("genTlCellmstBean", genTlCellmstBean);
						request.setAttribute("genTlCellmstServlet", genTlCellmst);

						//dispatchUrl = "/pages/GenTlCellmst.jsp";
						
						//dispatchUrl = "/pages/cellmaster1.jsp";
						request.setAttribute("celladd", celladd);
						
						/*if(celladd!=null)
						{
						if(celladd.equals("add"))
						{
						RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlCellmstrAdd.jsp");					
						rd.forward(request, response);
						}
						else if(celladd.equals("update"))
						{
							RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlCellmster.jsp");					
							rd.forward(request, response);
						}
						}
						else
						{
							RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlCellmster.jsp");					
							rd.forward(request, response);
													
						}
					*/
						RequestDispatcher rd = request.getRequestDispatcher("/pages/GenTlCellmster.jsp");					
						rd.forward(request, response);					
					}
				
					else if(action.equals("functionalLocadd.cell"))
					{
					    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				
							functLocFieldNameBean.setCompany("cmbComp");
							functLocFieldNameBean.setLocation("cmbLocn");
					        functLocFieldNameBean.setSbu("cmbSbu");
							functLocFieldNameBean.setPbu("cmbPbu");
							functLocFieldNameBean.setSection("cmbSect");
							functLocFieldNameBean.setCell("cmbCell");
							functLocFieldNameBean.setMachine("cmbMachine");
							functLocFieldNameBean.setSectMandatory(true);
							functLocFieldNameBean.setCellMandatory(false);
							functLocFieldNameBean.setLocnMandatory(false);
							functLocFieldNameBean.setCompMandatory(false);
							//functLocFieldNameBean.setSectMandatory(false);
							functLocFieldNameBean.setSbuDisable(false);
							functLocFieldNameBean.setPbuDisable(false);
					      
							functLocFieldNameBean.setCellDisable(true);
							functLocFieldNameBean.setMachDisable(true);
							
							sbuvalue=functLocFieldNameBean.getSbu();
							CommonMessage.debugMsg("functLocFieldNameBean.getSbu():::::"+sbuvalue);
							
					
						
			            FormModes formModes = FormModes.create;
						UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
						CommonMessage.debugMsg(UIUtils.getFunctionalLocation("cmbSbu"));
					}
					else if(action.equals("functionalLoc.cell"))
					{
					    FunctLocFieldNameBean functLocFieldNameBean = new FunctLocFieldNameBean();
				
							functLocFieldNameBean.setCompany("cmbComp");
							functLocFieldNameBean.setLocation("cmbLocn");
					        functLocFieldNameBean.setSbu("cmbSbu");
							functLocFieldNameBean.setPbu("cmbPbu");
							functLocFieldNameBean.setSection("cmbSect");
							functLocFieldNameBean.setCell("cmbCell");
							functLocFieldNameBean.setMachine("cmbMachine");
							functLocFieldNameBean.setSectMandatory(true);
							functLocFieldNameBean.setCellMandatory(false);
							functLocFieldNameBean.setLocnMandatory(false);
							functLocFieldNameBean.setCompMandatory(false);
							//functLocFieldNameBean.setSectMandatory(false);
							functLocFieldNameBean.setSbuDisable(false);
							functLocFieldNameBean.setPbuDisable(false);
							functLocFieldNameBean.setSectDisable(false);
							functLocFieldNameBean.setCellDisable(false);
							functLocFieldNameBean.setMachDisable(true);
							
							sbuvalue=functLocFieldNameBean.getSbu();
							CommonMessage.debugMsg("functLocFieldNameBean.getSbu():::::"+sbuvalue);
							
					
						
			            FormModes formModes = FormModes.create;
						UIUtils.setFunctionalLocationPopupVal(request, response, functLocFieldNameBean, formModes);
						CommonMessage.debugMsg(UIUtils.getFunctionalLocation("cmbSbu"));
					}
					//else if( action.equals("location.func"))
					else if( action.equals("location.funlocn"))
					{
						currentFilter = UIUtils.fillComboFilter(request);
						commonFilter.setLocation(currentFilter);
						comboList = functionalLocnServices.getLocationComboList(commonFilter);
						if( comboList != null && comboList.size() > 0 )
							UIUtils.writeComboBox(response, comboList,currentFilter );//writeCombo(response, comboList);
					}
					else if( action.equals("CellAuto_recall.cell") )
					{
						//PrintWriter out = response.getWriter();
						ServletOutputStream out = response.getOutputStream();

						String cellkeyid =request.getParameter(ReqtParamNameConst.KEYID);
						CommonMessage.debugMsg("KKKKKK   "+cellkeyid);
						GenTlCellmstBean genTlCellmstBean = new GenTlCellmstBean(FormModes.modify);
						
						httpSession.removeAttribute("genTlCellmstBean");
						httpSession.setAttribute("genTlCellmstBean", genTlCellmstBean);
						
						httpSession.removeAttribute("genTlCellmstServlet");
						
						GenTlCellmst genTlcellmst=genTlCellmstService.fillcellcontrol(cellkeyid);
						JSONObject  Cellmst =  UIUtils.fromTpmModel(genTlcellmst);
						CommonMessage.debugMsg("CELLMST:::::"+Cellmst);
						 CommonMessage.debugMsg("inside action" + genTlcellmst );
						 JSONObject returndata = new JSONObject();
					
						 returndata.put("cellmst", Cellmst);
						 out.print(returndata.toString());
					}
				    
					else if(action.equals("cell_save.cell"))
					{	
						GenTlCellmstBean genTlCellmstBean = (GenTlCellmstBean)httpSession.getAttribute("genTlCellmstBean");
				        savecell(request,response,genTlCellmstBean);
					}
					else if( action.equals("cell_delete.cell"))
					{	
						GenTlCellmstBean genTlCellmstBean = (GenTlCellmstBean)httpSession.getAttribute("genTlCellmstBean");
						deleteCell(request,response,genTlCellmstBean);
						
				    }
				
					else if(action.equals("cell_recall.cell"))
					{	
						ServletOutputStream out = response.getOutputStream();
						String cell =request.getParameter(ReqtParamNameConst.KEYID);
						CommonMessage.debugMsg("INSIDE RECALL"+cell);
						
						GenTlCellmstBean genTlCellmstBean = new GenTlCellmstBean(FormModes.modify);
						
						httpSession.removeAttribute("genTlCellmstBean");
						httpSession.setAttribute("genTlCellmstBean", genTlCellmstBean);
						
						httpSession.removeAttribute("genTlCellmstServlet");
						GenTlCellmst genTlCellmst = genTlCellmstService.select(cell);
						httpSession.setAttribute("genTlCellmstServlet",genTlCellmst);
						JSONObject  cellmst =  UIUtils.fromTpmModel(genTlCellmst);
						CommonMessage.debugMsg("inside action" + cellmst );
						JSONObject returndata = new JSONObject();
						returndata.put("cell", cellmst);
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
	    
	    
	    
	    
	    private void savecell(HttpServletRequest request, HttpServletResponse response,GenTlCellmstBean genTlCellmstBean  ) throws IOException{
		
	    	HttpSession httpSession = request.getSession(false);
	    	//ServletOutputStream out = response.getOutputStream();
	    	PrintWriter  out = response.getWriter();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		GenTlCellmst newGenTlCellmst = new GenTlCellmst();
				newGenTlCellmst.setCellCreatedby(user.getUsrm_ccno());
				if( genTlCellmstBean == null)
					genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
				
				
				
				newGenTlCellmst =(GenTlCellmst)UIUtils.setBeanProperties((Object)newGenTlCellmst,request);
				CommonMessage.debugMsg("newGenTlCellmst.getCellKeyid:::::"+newGenTlCellmst.getCellKeyid());
				 String originalId =newGenTlCellmst.getCellSectionid();
				CommonMessage.debugMsg("newGenTlCellmst.getCellFlid()*********"+newGenTlCellmst.getCellFlid());
				CommonMessage.debugMsg("newGenTlCellmst.getCellFlid()*********"+newGenTlCellmst.getCellSectionid());
				
				
				functionalLocnHierarchy(request,response,originalId);
				
				GenTlCellmst existGenTlCellmst = (GenTlCellmst)httpSession.getAttribute("genTlCellmstServlet");
				String location= request.getParameter("Location");
				String locId = request.getParameter(ReqtParamNameConst.LOCNID);
		/*		String Sbukeyid =request.getParameter(ReqtParamNameConst.KEYID);
				String 	flid = request.getParameter("flid"); 
			
				//String SbuId = request.getParameter(ReqtParamNameConst.SECTID);
				//String SbuId1= request.getParameter(ReqtParamNameConst.PHENID);
			//	String SbuId2= request.getParameter(ReqtParamNameConst.BUTTONID);
				//String SbuId3= request.getParameter(ReqtParamNameConst.ASSMID);
				String SbuId4= request.getParameter(ReqtParamNameConst.FACTID);
				
				String SbuId5= request.getParameter(ReqtParamNameConst.MACHID);
				String SbuId6= request.getParameter(ReqtParamNameConst.SPRSID);
				String SbuId7= request.getParameter(ReqtParamNameConst.ASSMID);
				*/
				CommonMessage.debugMsg("LOCATION::::"+locId);
				
			
				String sbu=funcLocnHierarchy.substring(22,32);
				String locat=funcLocnHierarchy.substring(11,21);
				CommonMessage.debugMsg("sbu::::"+sbu);
				//CommonMessage.debugMsg("SbuId1::::"+SbuId1);
				////CommonMessage.debugMsg("SbuId2::::"+SbuId2);
				//CommonMessage.debugMsg("SbuId3::::"+SbuId3);
				//////CommonMessage.debugMsg("SbuId4::::"+SbuId4);
				//CommonMessage.debugMsg("SbuId5::::"+SbuId5);
				//CommonMessage.debugMsg("SbuId::::"+SbuId6);
				//CommonMessage.debugMsg("SbuId76::::"+SbuId7);
				
				newGenTlCellmst.setLocation(locat);
				newGenTlCellmst.setSbu(sbu);
				
				CommonMessage.debugMsg(" locationon ::1"+location);
				try{
					
					
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( newGenTlCellmst.getCellKeyid() ))
					{	
						
						existGenTlCellmst = genTlCellmstService.create(newGenTlCellmst,existGenTlCellmst,genTlCellmstBean);
						//GenTlCellmst genTlCellmst = new GenTlCellmst();
						
						CommonMessage.debugMsg("AFTER INSERT::::"+existGenTlCellmst.getLocation());
						
				    	String funLocnGrid[] =new String[3];
				
						funLocnGrid[0]=existGenTlCellmst.getCellKeyid();
						funLocnGrid[1]=existGenTlCellmst.getCellName();
						funLocnGrid[2]=existGenTlCellmst.getCellCode();
						
						//funLocnGrid.add(existGenTlCellmst.getCellKeyid());
						//funLocnGrid.add(existGenTlCellmst.getCellName());
						//funLocnGrid.add(existGenTlCellmst.getCellCode());
						
						CommonMessage.debugMsg("funLocnGrid:::"+funLocnGrid);
						
						//CommonMessage.debugMsg("funLocnGrid:::::"+funLocnGrid[0]+funLocnGrid[1]+funLocnGrid[2]);
                          FunctionalLocn functionalLocn = new FunctionalLocn();
                          GenTlFunctionallocn funalocn;
                          funalocn=existGenTlCellmst.getGenTlFunctionallocn();
                          String parentid;
						CommonMessage.debugMsg("parentid:::::"+funalocn.getFnlnElementid());
						parentid=funalocn.getFnlnElementid();
						CommonMessage.debugMsg("SUBSTRING::::"+parentid.substring(7,32));
						String parentid1=parentid.substring(7,54);
						CommonMessage.debugMsg("SUBSTRING1::::"+parentid.substring(0,54));
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
		            		 
	
		     
		            
		             
		              
		           String parentNumberfun = parentid.substring(7,54); 
		   			String parentIdfun = parentid.substring(0,54); 
		   			String elementTypefun ="SEC";
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
		   			CommonMessage.debugMsg("ID : "+parentNumberfun.substring(36,46));
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
			    	PrintWriter pw=response.getWriter();
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
						existGenTlCellmst = genTlCellmstService.update(newGenTlCellmst,existGenTlCellmst,genTlCellmstBean);
						
						CommonMessage.debugMsg("existGenTlCellmst>>>>"+existGenTlCellmst);
						String funLocnGrid[] =new String[3];
						
						funLocnGrid[0]=existGenTlCellmst.getCellKeyid();
						funLocnGrid[1]=existGenTlCellmst.getCellName();
						funLocnGrid[2]=existGenTlCellmst.getCellCode();
						
						//funLocnGrid.add(existGenTlCellmst.getCellKeyid());
						//funLocnGrid.add(existGenTlCellmst.getCellName());
						//funLocnGrid.add(existGenTlCellmst.getCellCode());
						
						CommonMessage.debugMsg("funLocnGrid:::"+funLocnGrid);
						
						//CommonMessage.debugMsg("funLocnGrid:::::"+funLocnGrid[0]+funLocnGrid[1]+funLocnGrid[2]);
                          FunctionalLocn functionalLocn = new FunctionalLocn();
                          GenTlFunctionallocn funalocn;
                          funalocn=existGenTlCellmst.getGenTlFunctionallocn();
                          String parentid;
						CommonMessage.debugMsg("parentid:::::"+funalocn.getFnlnElementid());
						parentid=funalocn.getFnlnElementid();
						CommonMessage.debugMsg("SUBSTRING::::"+parentid.substring(7,32));
						String parentid1=parentid.substring(7,54);
						CommonMessage.debugMsg("SUBSTRING1::::"+parentid.substring(0,54));
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
		            		 
	
		     
		            
		             
		              
		           String parentNumberfun = parentid.substring(7,54); 
		   			String parentIdfun = parentid.substring(0,54); 
		   			String elementTypefun ="SEC";
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
		   			CommonMessage.debugMsg("ID : "+parentNumberfun.substring(36,46));
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
			    	PrintWriter pw=response.getWriter();
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
					
					CommonMessage.debugMsg("OUTSIDE OF THE METHOD")	;		
					JSONObject successData = new JSONObject();
				    String msgPropertyIdnt;
					 
					 if( insert){
						 CommonMessage.debugMsg("INSIDE the insert if blcok");
						msgPropertyIdnt = "success-save";
				
						
						
					 }
					 else
					 {
						msgPropertyIdnt = "success-update";
					 }
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
					successData.put("CellKeyid", existGenTlCellmst.getCellKeyid());
					JSONObject returnData = new JSONObject();
					
					returnData.put("successData", successData);
					returnData.put("formClear", true);
					
					httpSession.removeAttribute("genTlCellmstServlet");
					httpSession.removeAttribute("genTlCellmstBean");
					
			    	
					out.print(returnData.toString());
					
					out.close();
				}
				catch(ValidationExceptions e)
				{  
					
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "cellCreationException");
					CommonMessage.debugMsg("errMessage:::"+errMessage);
					errMessage.put("fromMode",genTlCellmstBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}
				catch(BusinessApplicationExceptions e)
				{ 
					
					CommonMessage.debugMsg("validationbusiness   "+e.toString());
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "cellCreationException");
					CommonMessage.debugMsg(" e " + errMessage );
					out.print(errMessage.toString());
				}
				catch(Exception e)
				{
					
				/*	CommonMessage.debugMsg("Inside Exceptions "+e.getMessage());
					JSONObject err = new JSONObject();
					String msg="Data Not Supported";
					//String msg=null;
					if(e.toString().contains("UK_CELL_CODE"))
						msg="This JH Code is Already Exists";
					err.put("tpmException",msg);
					out.print(err.toString());
					e.printStackTrace();
					//PrintWriter  out = response.getWriter();
					//JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					//err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-save"));
					//out.print(err.toString());*/
					CommonMessage.debugMsg("gete. " + e.getMessage());
					JSONObject err = new JSONObject();
					//err.put("tpmException", "Data Not Saved");
					err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CellError","err-save"));
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


		private void deleteCell(HttpServletRequest request,HttpServletResponse response, GenTlCellmstBean genTlCellmstBean) throws IOException
		{
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		if( genTlCellmstBean == null)
	    			genTlCellmstBean = new GenTlCellmstBean(FormModes.create);
	    		
	    		GenTlCellmst existGenTlCellmst = (GenTlCellmst)httpSession.getAttribute("genTlCellmstServlet"); 
	    		GenTlCellmst newGenTlCellmst = new GenTlCellmst();
	    		newGenTlCellmst.setCellCreatedby(user.getUsrm_ccno());

	    		
	    		newGenTlCellmst =(GenTlCellmst)UIUtils.setBeanProperties((Object)newGenTlCellmst,request);
	    		genTlCellmstBean =(GenTlCellmstBean)UIUtils.setBeanProperties((Object)genTlCellmstBean,request);
	    		genTlCellmstBean =(GenTlCellmstBean) UIUtils.setBeanProperties((Object)genTlCellmstBean,request);
	    	try{
				
				String inactMode =request.getParameter("hdnInactive");
				String toInactiveMsg =null;
				if ( inactMode.equals("Inactive") ){
					existGenTlCellmst = genTlCellmstService.delete("I",newGenTlCellmst);
					toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactivated");
				}
				else{
					existGenTlCellmst = genTlCellmstService.delete("D",newGenTlCellmst);
					toInactiveMsg= UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete");
				}
				
				
				
				existGenTlCellmst.setCellKeyid("N");
				
				
				JSONObject mode = new JSONObject();
//				mode.put("formMode",genTlCellmstBean.getFormActionMode());
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("CellKeyid",existGenTlCellmst.getCellKeyid());

				
				JSONObject forwardData = new JSONObject();
				forwardData.put("CellKeyid",existGenTlCellmst.getCellKeyid());

				mode.put("forwardData", forwardData);
				mode.put("persistentData", persistentData);

				httpSession.removeAttribute("genTlCellmstServlet");
				httpSession.removeAttribute("genTlCellmstBean");

				JSONObject successData = new JSONObject();
				successData.put("msg",toInactiveMsg);
						//UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", successData);		
				out.print(returnData.toString());	
			}catch(ValidationExceptions e)
			{
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "cellCreationException");
				errMessage.put("fromMode",genTlCellmstBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(BusinessApplicationExceptions e)
			{ 
				CommonMessage.debugMsg("BusinessApplicationExcepions"  );
				JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),  "cellCreationException");
				//out.print(errMessage.toString());
				successData.put("msg", "OriginalIdExists");	
				successData.put("Errmsg", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","inactive-confirm"));
				
				JSONObject returnData = new JSONObject();	
				returnData.put("formClear",false);
				returnData.put("ErrData", successData);					
				returnData.put("successData", successData);	
				returnData.put("CellKeyid",existGenTlCellmst.getCellKeyid());
			
				
				out.print(returnData.toString());
						
			}catch(Exception e)
			{
				e.printStackTrace();
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Deleted");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    }
	}
}