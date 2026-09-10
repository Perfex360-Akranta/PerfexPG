package com.akranta.tpm.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.bean.BAL_FunctLocHierarchyIdentBean;
import com.akranta.tpm.bean.HtmlElementBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FuntLocnElementDispModel;
import com.akranta.tpm.service.BAL_FunctionalLocnServices;
import com.akranta.tpm.service.impl.BAL_FunctionalLocnServicesImpl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.FormModes;

public class BAL_FunctionalLocHierarchyServlet extends HttpServlet { 
	
	
	BAL_FunctionalLocnServices functionalLocnServices;
	public BAL_FunctionalLocHierarchyServlet(){
		
		super();	       
       /* try {
        	  functionalLocnServices = new FunctionalLocnServicesImpl();
        	} catch (Exception e) {
			
		}
		*/
		
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	try {
			process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String action = UIUtils.getActionPart(request);
		String dispUrl = null;
		
		try {
			functionalLocnServices = (BAL_FunctionalLocnServicesImpl)UIUtils.getServiceObject(request,"FunctionalLocnServicesImpl");
		} catch (ServiceObjectCreationException e) {
			CommonFunctions.debugMsg(e);
		}
	
		
		if( action.equals("loadFuntLoc.selectFuntLoc") )
		{
			
			try{
				BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean = (BAL_FunctLocHierarchyIdentBean)request.getAttribute("functLocHierarchyIdentBean"); 
				
				FormModes mode = functLocHierarchyIdentBean.getFormMode();
				
				String url = request.getParameter("url");
				String divId = request.getParameter("divId");
				String formId = request.getParameter("formId");
				
				String compId = request.getParameter("compId");
				String locnId = request.getParameter("locnId");
				String factId = request.getParameter("factId");
				
				//String sbuId = request.getParameter("sbuId");
				//String pbuId = request.getParameter("pbuId");
				String sectId = request.getParameter("sectId");
				String cellId = request.getParameter("cellId");
			//	CommonFunctions.debugMsg("test data....");
				String teamId = request.getParameter("teamId");
				String machId = request.getParameter("machId");
				String flid = request.getParameter("flid");
			//	CommonFunctions.debugMsg("loadFuntLoc.selectFuntLocteamID  "+teamId);
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				
				HttpSession httpSession = request.getSession(false);
				String sessionFlid =(String )httpSession.getAttribute("loginFlid");

				if (!UIUtils.isValidKeyId(flid) && !UIUtils.isValidKeyId(machId) &&
						!UIUtils.isValidKeyId(cellId) && !UIUtils.isValidKeyId(sectId) &&
						//!UIUtils.isValidKeyId(pbuId) && !UIUtils.isValidKeyId(sbuId) &&
						!UIUtils.isValidKeyId(factId) && !UIUtils.isValidKeyId(locnId) && !UIUtils.isValidKeyId(compId)
						)
					flid =sessionFlid;

				String roleId = request.getParameter("roleId");
				
				FactoryLayout factoryLayout= getAllRelatedFunctLocHierarchy(compId,locnId,factId,sectId,cellId,teamId,machId,flid);
				//FactoryLayout factoryLayout= getAllRelatedFunctLocHierarchy(compId,locnId,sbuId,pbuId,sectId,cellId,teamId,machId,flid);
				
				if( factoryLayout == null){
					
					factoryLayout = functionalLocnServices.getEmployeeFunctionalLocation(user.getUsrm_ccno(),roleId, flid);
					
				}
				
				lockElements(functLocHierarchyIdentBean ,factoryLayout,request);
				request.setAttribute("functLcnHierViewUrl", url);
				request.setAttribute("functLcnHierViewDivId", divId);
				request.setAttribute("currentFormId", formId);
				
				String isLock  = request.getParameter("disable");
				
				String formStr = buildFunctionlLocViewPage(divId,formId,url,factoryLayout, mode,functLocHierarchyIdentBean,isLock);
				
				ServletOutputStream out = response.getOutputStream();
				response.setContentType("text/html");
				
				out.print(formStr);
				out.flush();
				out.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
			//dispUrl = "/pages/Gen/functlLocHierarchyView.jsp";
		}
		else if( action.equals("getFuntLoc.selectFuntLoc") ){
			CommonFunctions.debugMsg("inside  getFuntLoc.selectFuntLoc ");
			HttpSession httpSession = request.getSession(false);
			
			if(httpSession != null )
			{
				BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean = (BAL_FunctLocHierarchyIdentBean)request.getAttribute("functLocHierarchyIdentBean"); 
				
				//UIUtils.displayRequestParamsValue(request);
				
				String divId = (String)request.getAttribute("divId");
				String formId = (String)request.getAttribute("formId");
				String roleId = (String)request.getAttribute("roleId");
				String flid = (String)request.getAttribute("roleId");
				
				CommonFunctions.debugMsg("flid in heraachi     "+roleId);
				CommonFunctions.debugMsg("formId in heraachi     "+formId);

				CommonFunctions.debugMsg("flid in heraachi     "+flid);
				CommonFunctions.debugMsg("divid in heraachi     "+divId);
				
				String compId = null;
				if( functLocHierarchyIdentBean.getCompany() == null){
					functLocHierarchyIdentBean.setCompany(new HtmlElementBean());
				}
				else
					compId = functLocHierarchyIdentBean.getCompany().getValue();
			
				String locnId = null;

				if( functLocHierarchyIdentBean.getLocation() != null)
					locnId = functLocHierarchyIdentBean.getLocation().getValue();
				else
					functLocHierarchyIdentBean.setLocation(new HtmlElementBean());

				
				
				//fact// 
				
				String factId = null;	
				if( functLocHierarchyIdentBean.getFactory() != null )
					factId = functLocHierarchyIdentBean.getFactory().getValue();
				else
					functLocHierarchyIdentBean.setFactory(new HtmlElementBean());
				
				/*String sbuId = null;	
				if( functLocHierarchyIdentBean.getSbu() != null )
					sbuId = functLocHierarchyIdentBean.getSbu().getValue();
				else
					functLocHierarchyIdentBean.setSbu(new HtmlElementBean());
				
				String pbuId = null;	
				if( functLocHierarchyIdentBean.getPbu() != null )
					pbuId = functLocHierarchyIdentBean.getPbu().getValue();
				else
					functLocHierarchyIdentBean.setPbu(new HtmlElementBean());
				*/
				String sectId = null;
				if( functLocHierarchyIdentBean.getSection() != null )
					sectId = functLocHierarchyIdentBean.getSection().getValue();
				else
					functLocHierarchyIdentBean.setSection(new HtmlElementBean());
				
				String cellId = null;
				if( functLocHierarchyIdentBean.getCell() != null)
					cellId = functLocHierarchyIdentBean.getCell().getValue();
				else
					functLocHierarchyIdentBean.setCell(new HtmlElementBean());
				
				String teamId = null;
				if( functLocHierarchyIdentBean.getTeam() != null)
					teamId = functLocHierarchyIdentBean.getTeam().getValue();
				else
					functLocHierarchyIdentBean.setTeam(new HtmlElementBean());
				
				String mchId = null;
				if( functLocHierarchyIdentBean.getMachine() != null  && UIUtils.isValidKeyId(functLocHierarchyIdentBean.getMachine().getValue()) )
				{
					mchId = functLocHierarchyIdentBean.getMachine().getValue();
				}
				
				//String flid = null;
				if( functLocHierarchyIdentBean.getFlid() != null  && UIUtils.isValidKeyId(functLocHierarchyIdentBean.getFlid().getValue()) )
				{
					flid = functLocHierarchyIdentBean.getFlid().getValue();
				}
				
				AdmTlUsermst user = UIUtils.getLoginUser(request);
				
				FactoryLayout factoryLayout= getAllRelatedFunctLocHierarchy(compId,locnId,factId,sectId, cellId,teamId,mchId,flid);
				//FactoryLayout factoryLayout= getAllRelatedFunctLocHierarchy(compId,locnId,sbuId,pbuId,sectId, cellId,teamId,mchId,flid);

				FactoryLayout userFactoryLayout = functionalLocnServices.getEmployeeFunctionalLocation(user.getUsrm_ccno(),roleId, flid);
				

				if(  user.getUsrm_isadministartor().equals("Y") && userFactoryLayout != null && factoryLayout == null )
				{
					factoryLayout = userFactoryLayout;
				}
				else if( ! user.getUsrm_isadministartor().equals("Y")  ){
					//System.out.println("  ----- ++ " + userFactoryLayout.getCompany().getKeyid());
					   if( factoryLayout == null)
						   factoryLayout = userFactoryLayout;
					 
					   if( userFactoryLayout != null ){

						    if( userFactoryLayout.getFlid() != null && UIUtils.isValidKeyId(userFactoryLayout.getFlid().getKeyid()) ){
								functLocHierarchyIdentBean.getFlid().setValue(userFactoryLayout.getFlid().getKeyid());
								//functLocHierarchyIdentBean.getCompany().setDisable(true);
							}
							if( userFactoryLayout.getCompany() != null && UIUtils.isValidKeyId(userFactoryLayout.getCompany().getKeyid()) ){
								functLocHierarchyIdentBean.getCompany().setValue(userFactoryLayout.getCompany().getKeyid());
								//functLocHierarchyIdentBean.getCompany().setDisable(true);
							}
							if( userFactoryLayout.getLocation() != null && UIUtils.isValidKeyId(userFactoryLayout.getLocation().getKeyid()) ){
								functLocHierarchyIdentBean.getLocation().setValue(userFactoryLayout.getLocation().getKeyid());
								//functLocHierarchyIdentBean.getLocation().setDisable(true);
								
							}
							//fact//
							
							if( userFactoryLayout.getFactory() != null && UIUtils.isValidKeyId(userFactoryLayout.getFactory().getKeyid()) ){
								functLocHierarchyIdentBean.getFactory().setValue(userFactoryLayout.getFactory().getKeyid());
								functLocHierarchyIdentBean.getFactory().setDisable(true);
								
							}
							/*
							
							if( userFactoryLayout.getSbu() != null && UIUtils.isValidKeyId(userFactoryLayout.getSbu().getKeyid()) ){
								functLocHierarchyIdentBean.getSbu().setValue(userFactoryLayout.getSbu().getKeyid());
								//functLocHierarchyIdentBean.getSbu().setDisable(true);								
							}
							if( userFactoryLayout.getPbu() != null && UIUtils.isValidKeyId(userFactoryLayout.getPbu().getKeyid()) ){
								functLocHierarchyIdentBean.getPbu().setValue(userFactoryLayout.getPbu().getKeyid());
								//functLocHierarchyIdentBean.getPbu().setDisable(true);								
							}
							*/
							if( userFactoryLayout.getSection() != null && UIUtils.isValidKeyId(userFactoryLayout.getSection().getKeyid()) ){
								functLocHierarchyIdentBean.getSection().setValue(userFactoryLayout.getSection().getKeyid());
								//functLocHierarchyIdentBean.getSection().setDisable(true);
								
							}
							if( userFactoryLayout.getCell() != null && UIUtils.isValidKeyId(userFactoryLayout.getCell().getKeyid())  ){
								functLocHierarchyIdentBean.getCell().setValue(userFactoryLayout.getCell().getKeyid());
								//functLocHierarchyIdentBean.getCell().setDisable(true);
								
							}
							if( userFactoryLayout.getTeam() != null && UIUtils.isValidKeyId(userFactoryLayout.getTeam().getKeyid())  ){
								
								functLocHierarchyIdentBean.getTeam().setValue(userFactoryLayout.getTeam().getKeyid());
								//functLocHierarchyIdentBean.getTeam().setDisable(true);
							}
					 }
				}
				
				if( factoryLayout != null ){

					//CommonFunctions.debugMsg(functLocHierarchyIdentBean.getCompany());
					
					functLocHierarchyIdentBean.getCompany().setValue(factoryLayout.getCompany().getKeyid());
					functLocHierarchyIdentBean.getLocation().setValue(factoryLayout.getLocation().getKeyid());
					functLocHierarchyIdentBean.getFactory().setValue(factoryLayout.getFactory().getKeyid());
					//functLocHierarchyIdentBean.getSbu().setValue(factoryLayout.getSbu().getKeyid());
					//functLocHierarchyIdentBean.getPbu().setValue(factoryLayout.getPbu().getKeyid());
					functLocHierarchyIdentBean.getSection().setValue(factoryLayout.getSection().getKeyid());
					functLocHierarchyIdentBean.getCell().setValue(factoryLayout.getCell().getKeyid());
					functLocHierarchyIdentBean.getMachine().setValue(factoryLayout.getMachine().getKeyid());
					
					if(! UIUtils.isValidKeyId(functLocHierarchyIdentBean.getCompany().getName()))
						functLocHierarchyIdentBean.getCompany().setName("cmbFunlocCompany");
					if(! UIUtils.isValidKeyId(functLocHierarchyIdentBean.getLocation().getName()))
						functLocHierarchyIdentBean.getLocation().setName("cmbFunlocLocation");
					
					if(! UIUtils.isValidKeyId(functLocHierarchyIdentBean.getFactory().getName()))
						functLocHierarchyIdentBean.getFactory().setName("cmbFunlocFactory");
				}
				
					
				
				httpSession.removeAttribute("functLocHierarchyIdentBean");
				
				lockElements(functLocHierarchyIdentBean, factoryLayout,request);
				request.setAttribute("functLocHierarchyIdentBean", functLocHierarchyIdentBean);
				
				httpSession.removeAttribute("functLocHierarchydivId");
				httpSession.setAttribute("functLocHierarchydivId", divId);
				httpSession.setAttribute("functLocHierarchyformId", formId);
				httpSession.setAttribute("roleId", roleId);
				
				httpSession.setAttribute("functLocHierarchyIdentBean", functLocHierarchyIdentBean);
				
				
				dispUrl = "/pages/Gen/functionalLocFilter.jsp";
				//CommonFunctions.debugMsg("dispUrl======"+dispUrl);
			}
		}
		else if( action.equals("validateFuntLoc.selectFuntLoc") ){
			
			HttpSession httpSession = request.getSession(false);
			
			StringBuffer validStr = new StringBuffer();
			if(httpSession != null )
			{
				BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean = (BAL_FunctLocHierarchyIdentBean)httpSession.getAttribute("functLocHierarchyIdentBean"); 
				
				populateValues(functLocHierarchyIdentBean, request);
				
				validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getCompany()));
				validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getLocation()));
				 validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getFactory()));
				//validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getSbu()));
				//validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getPbu()));
				validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getSection()));
				validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getCell()));
				//validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getTeam()));
				validStr.append(checkMandatoryField(functLocHierarchyIdentBean.getMachine()));
				
				ServletOutputStream out = response.getOutputStream();
				response.setContentType("text/html");
				if( validStr.length()>0 ){
					//validStr.append("validationException :");
					JSONObject mandObj = UIUtils.validationExceptions(validStr.toString(), "functLocSelection");
					out.print(mandObj.toString());
					
				}
				else{
					String divId = (String)httpSession.getAttribute("functLocHierarchydivId");
					String formId = (String)httpSession.getAttribute("functLocHierarchyformId");
					JSONObject data = new JSONObject();
					data.put("divId" , divId);
					data.put("formId" , formId);
					JSONObject controlIds = new JSONObject();
					
					//AdmTlUsermst user = UIUtils.getLoginUser(request);
					
					FactoryLayout factoryLayout= getAllRelatedFunctLocHierarchy(functLocHierarchyIdentBean.getCompany().getValue(),
							functLocHierarchyIdentBean.getLocation().getValue(),
							functLocHierarchyIdentBean.getFactory().getValue(),
							//functLocHierarchyIdentBean.getSbu().getValue(),
							//functLocHierarchyIdentBean.getPbu().getValue(),
							functLocHierarchyIdentBean.getSection().getValue(),
							functLocHierarchyIdentBean.getCell().getValue(),
							functLocHierarchyIdentBean.getTeam().getValue(),
							functLocHierarchyIdentBean.getMachine().getValue(),
							"");
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getCompany().getName())  )
						controlIds.put("compId", functLocHierarchyIdentBean.getCompany().getName());
			
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getLocation().getName())  )
						controlIds.put("locnId",functLocHierarchyIdentBean.getLocation().getName());
			
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getFactory().getName())  )
						 controlIds.put("fact", functLocHierarchyIdentBean.getFactory().getName());
/*					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getSbu().getName())  )
						controlIds.put("sbuId",functLocHierarchyIdentBean.getSbu().getName());
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getPbu().getName())  )
						controlIds.put("pbuId",functLocHierarchyIdentBean.getPbu().getName());
		*/			
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getSection().getName())  )
						controlIds.put("sectId",functLocHierarchyIdentBean.getSection().getName());
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getCell().getName())  )
						controlIds.put("cellId",functLocHierarchyIdentBean.getCell().getName());
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getTeam().getName())  )
						controlIds.put("teamId",functLocHierarchyIdentBean.getTeam().getName());
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getMachine().getName())  )
						controlIds.put("machId",functLocHierarchyIdentBean.getMachine().getName());
					
					if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getFlid().getName())  ){
						controlIds.put("flid",functLocHierarchyIdentBean.getFlid().getName());
						controlIds.put("flId",functLocHierarchyIdentBean.getFlid().getName());
					}	
					
					//CommonFunctions.debugMsg("controlIds  "+controlIds.toString());
					data.put("controlIds", controlIds);
					data.put("functLocDisplay",getFunctLocDisplayString( factoryLayout, formId));
					data.put("functLocHierarchIds",getFuntHierarchyKeyids(factoryLayout));
					
					JSONObject success = new JSONObject();
					success.put("success", "true");
					success.put("data", data);
					out.print(success.toString());
					
				}
				out.flush();
				//dispUrl = "/pages/Gen/functionalLocFilter.jsp";
			}
		}
		
		
		if( dispUrl != null )
		{
			UIUtils.forwardRequest(request, response, dispUrl);
		}
	}

	private void lockElements(BAL_FunctLocHierarchyIdentBean fLHierBean,FactoryLayout fL,HttpServletRequest request){
		
		String isLock  = request.getParameter("disable");
		
		request.setAttribute("disable", isLock);
		//CommonFunctions.debugMsg(" isLock " + isLock);

		String loginElID = (String) request.getSession().getAttribute("loginElementid");
		int n = loginElID.split("-").length;

		if(isLock != null &&  "N".equalsIgnoreCase(isLock) ) {
		
			if(  fL.getCompany() != null && UIUtils.isValidKeyId(  fL.getCompany().getKeyid()) && n >0){
				fLHierBean.getCompany().setDisable(true);
			}
			if(  fL.getLocation() != null && UIUtils.isValidKeyId( fL.getLocation().getKeyid() ) && n >1){
				fLHierBean.getLocation().setDisable(true);
			}
			
			return;
		}
		
		if(  fL.getCompany() != null && UIUtils.isValidKeyId(  fL.getCompany().getKeyid()) && n >0){
			fLHierBean.getCompany().setDisable(true);
		}
		if(  fL.getLocation() != null && UIUtils.isValidKeyId( fL.getLocation().getKeyid() ) && n >1){
			fLHierBean.getLocation().setDisable(true);
		}
		if(  fL.getFactory() != null && UIUtils.isValidKeyId( fL.getFactory().getKeyid() ) && n >2){
			fLHierBean.getFactory().setDisable(true);
		}
	/*	if(  fL.getSbu() != null &&  UIUtils.isValidKeyId( fL.getSbu().getKeyid() )&& n >2){
			fLHierBean.getSbu().setDisable(true);
		}
		if(  fL.getPbu() != null &&  UIUtils.isValidKeyId( fL.getPbu().getKeyid())&& n >3){
			fLHierBean.getPbu().setDisable(true);
		}
	*/	
		if(  fL.getSection() != null &&  UIUtils.isValidKeyId(  fL.getSection().getKeyid())&& n >3){
			fLHierBean.getSection().setDisable(true);
		}
		if(  fL.getCell() != null &&  UIUtils.isValidKeyId(  fL.getCell().getKeyid() )&& n >4){
			fLHierBean.getCell().setDisable(true);
		}
		if(  fL.getMachine() != null &&  UIUtils.isValidKeyId(  fL.getMachine().getKeyid() )&& n >5){
			fLHierBean.getMachine().setDisable(true);
		}
	}
	
	private JSONObject getFuntHierarchyKeyids(FactoryLayout factoryLayout){
		JSONObject  functHierarchy = new JSONObject();
		
		if( factoryLayout != null ){
			functHierarchy.put("compId", factoryLayout.getCompany().getKeyid());
			functHierarchy.put("locnId", factoryLayout.getLocation().getKeyid());
			functHierarchy.put("factId", factoryLayout.getFactory().getKeyid());
			//functHierarchy.put("sbuId", factoryLayout.getSbu().getKeyid());
			//functHierarchy.put("pbuId", factoryLayout.getPbu().getKeyid());
			functHierarchy.put("sectId", factoryLayout.getSection().getKeyid());
			functHierarchy.put("cellId", factoryLayout.getCell().getKeyid());
			//functHierarchy.put("teamId", factoryLayout.getTeam().getKeyid());
			functHierarchy.put("machId", factoryLayout.getMachine().getKeyid());
			functHierarchy.put("flId", factoryLayout.getFlid().getKeyid());
			functHierarchy.put("flid", factoryLayout.getFlid().getKeyid());
			String [] typeAndElementId =  factoryLayout.getFlid().getCode().split("##");
			functHierarchy.put("elementId",typeAndElementId[1]);
			functHierarchy.put("type",typeAndElementId[0]);
		}
		return functHierarchy;
	}
	private FactoryLayout getAllRelatedFunctLocHierarchy(String compId,String locnId, String factId,String sectId,
//private FactoryLayout getAllRelatedFunctLocHierarchy(String compId,String locnId, String sbuId,String pbuId,String sectId,
			String cellId, String teamId, String machId,String flid) throws Exception{
		
		FactoryLayout  factoryLayout= null;
		
		
		if( UIUtils.isValidKeyId(compId) || UIUtils.isValidKeyId(locnId) || UIUtils.isValidKeyId(factId) ||  		
		//if( UIUtils.isValidKeyId(compId) || UIUtils.isValidKeyId(locnId) ||  UIUtils.isValidKeyId(sbuId) ||
				//UIUtils.isValidKeyId(pbuId) ||
				UIUtils.isValidKeyId(sectId) || UIUtils.isValidKeyId(cellId) || UIUtils.isValidKeyId(machId)  || UIUtils.isValidKeyId(flid))
		{
			factoryLayout= new FactoryLayout();	
			factoryLayout.setCompany(getExistFuntLocnElement(compId));
			factoryLayout.setLocation(getExistFuntLocnElement(locnId));
			factoryLayout.setFactory(getExistFuntLocnElement(factId));
			//factoryLayout.setSbu(getExistFuntLocnElement(sbuId));
			//factoryLayout.setPbu(getExistFuntLocnElement(pbuId));
			factoryLayout.setSection(getExistFuntLocnElement(sectId));
			factoryLayout.setCell(getExistFuntLocnElement(cellId));
			factoryLayout.setTeam(getExistFuntLocnElement(teamId));
			factoryLayout.setMachine(getExistFuntLocnElement(machId));
			factoryLayout.setFlid(getExistFuntLocnElement(flid));
			
			factoryLayout = functionalLocnServices.getFactoryLayoutElements(factoryLayout);
			CommonFunctions.debugMsg("flid...-------."+factoryLayout.getFlid());
		}
		
		//if( ! user.getUsrm_isadministartor().equals("Y") )
		//{
			
		//}
		
		return factoryLayout; 

	}
	private FuntLocnElementDispModel getExistFuntLocnElement(String keyId){
		
			FuntLocnElementDispModel funtLocnElementDispModel = new FuntLocnElementDispModel();
			if( UIUtils.isValidKeyId(keyId))
				funtLocnElementDispModel.setKeyid(keyId);
			
			return funtLocnElementDispModel;
		
	}
	
	private String checkMandatoryField(HtmlElementBean htmlElementBean  ){
		if( ! UIUtils.isValidKeyId( htmlElementBean.getValue()) &&  htmlElementBean.isMandatory() )
			return "required-"+htmlElementBean.getId()+",";
		
		return "";
	}
	
	
	private void populateValues(BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean, HttpServletRequest request){
		
		String tmpFld = null;
		
		if( functLocHierarchyIdentBean.getCompany() != null && functLocHierarchyIdentBean.getCompany().getName() != null){
			String paramName = functLocHierarchyIdentBean.getCompany().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocComp";

			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getCompany().setValue(tmpFld);
		}
		
		if( functLocHierarchyIdentBean.getLocation() != null && functLocHierarchyIdentBean.getLocation().getName() != null){
			String paramName = functLocHierarchyIdentBean.getLocation().getName();
			
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocLocn";
			
			tmpFld = request.getParameter(paramName);
			System.out.println("  locationId " + tmpFld);
			functLocHierarchyIdentBean.getLocation().setValue(tmpFld);
		}
		else
			functLocHierarchyIdentBean.getLocation().setValue(null);
		//fact// 		
		if( functLocHierarchyIdentBean.getFactory() != null && functLocHierarchyIdentBean.getFactory().getName() != null){

			String paramName = functLocHierarchyIdentBean.getFactory().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocFact";
			
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getFactory().setValue(tmpFld);
		}	

		/*if( functLocHierarchyIdentBean.getSbu() != null && functLocHierarchyIdentBean.getSbu().getName() != null){

			String paramName = functLocHierarchyIdentBean.getSbu().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocSBU";
			
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getSbu().setValue(tmpFld);
		}	
		
		if( functLocHierarchyIdentBean.getPbu() != null && functLocHierarchyIdentBean.getPbu().getName() != null){

			String paramName = functLocHierarchyIdentBean.getPbu().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocPBU";
			
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getPbu().setValue(tmpFld);
		}*/
		if(functLocHierarchyIdentBean.getSection() != null && functLocHierarchyIdentBean.getSection().getName() != null){
			String paramName = functLocHierarchyIdentBean.getSection().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocSect";
			
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getSection().setValue(tmpFld);
		}	
		if( functLocHierarchyIdentBean.getCell() != null && functLocHierarchyIdentBean.getCell().getName() != null){
			String paramName = functLocHierarchyIdentBean.getCell().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocCell";
			
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getCell().setValue(tmpFld);
		}
		/*if( functLocHierarchyIdentBean.getFlid() != null && functLocHierarchyIdentBean.getFlid().getName() != null){
			String paramName = functLocHierarchyIdentBean.getFlid().getName();
			CommonFunctions.debugMsg("FunctionLocation Param NAME  :" +paramName);
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "hdnFunctLocFL";
			CommonFunctions.debugMsg("FunctionLocation Param NAMEs  :" +paramName);
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getFlid().setValue(tmpFld);
		}
		*/
		if( functLocHierarchyIdentBean.getMachine() != null && functLocHierarchyIdentBean.getMachine().getName() != null){
			String paramName = functLocHierarchyIdentBean.getMachine().getName();
			if( ! UIUtils.isValidKeyId(paramName) )
				paramName = "cmbFunctLocMachine";
			CommonFunctions.debugMsg("FunctionLocation tmpFld NAME  :" +tmpFld);
			tmpFld = request.getParameter(paramName);
			functLocHierarchyIdentBean.getMachine().setValue(tmpFld);
		}
	}
	
	private String buildFunctionlLocViewPage(String divId, String formId, String url,FactoryLayout factoryLayout, FormModes mode,BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean,String isLock){
		StringBuffer form = new StringBuffer();
		
		form.append("<script type='text/javascript' >");
		form.append("jQuery(document).ready(function (){ ");
		if( mode != FormModes.view){
			
			form.append("jQuery('#btn"+ formId + "mainFunLoc').live('click',function(){");
			form.append(formId+"_fillFunloc(63);");
			form.append("}); ");
			if( ! functLocHierarchyIdentBean.getCompany().isDisable()){
				form.append("jQuery('#lin"+formId +"Company').live('click',function(){");
				form.append(formId+"_fillFunloc(1);");
				form.append("}); ");
			}
			if( ! functLocHierarchyIdentBean.getLocation().isDisable()){
				form.append("jQuery('#lin"+formId +"Location').live('click',function(){");
				form.append(formId+"_fillFunloc(3);");
				form.append("}); ");
			}
			//fact// 
			if( ! functLocHierarchyIdentBean.getFactory().isDisable()){
				form.append("jQuery('#lin"+formId +"Factory').live('click',function(){");
				form.append(formId+"_fillFunloc(7);");
				form.append("}); ");
			}
			
			/*if( ! functLocHierarchyIdentBean.getSbu().isDisable() ){
				form.append("jQuery('#lin"+formId +"Sbu').live('click',function(){");
				form.append(formId+"_fillFunloc(7);");
				form.append("}); ");
			}
			
			if( ! functLocHierarchyIdentBean.getPbu().isDisable() ){
				form.append("jQuery('#lin"+formId +"Pbu').live('click',function(){");
				form.append(formId+"_fillFunloc(15);");
				form.append("}); ");
			}*/
			
			if( ! functLocHierarchyIdentBean.getSection().isDisable() ){
				form.append("jQuery('#lin"+formId +"Section').live('click',function(){");
				form.append(formId+"_fillFunloc(31);");
				form.append("}); ");
			}
			if( ! functLocHierarchyIdentBean.getCell().isDisable()){
				form.append("jQuery('#lin"+formId +"Cell').live('click',function(){");
				form.append(formId+"_fillFunloc(63);");
				form.append("}); ");
			}	
			/*if( ! functLocHierarchyIdentBean.getTeam().isDisable()){
				form.append("jQuery('#lin"+formId +"Team').live('click',function(){");
				form.append(formId+"_fillFunloc(256);");
				form.append("}); ");
			}*/	
			if( ! functLocHierarchyIdentBean.getMachine().isDisable()){	
				form.append("jQuery('#lin"+formId +"Machine').live('click',function(){");
				form.append(formId+"_fillFunloc(127);");
				form.append("}); ");
			}	
		}
		form.append(getFunctLocHiddenFieldIds(divId,formId,factoryLayout,functLocHierarchyIdentBean));
					
		form.append(" jQuery(\"#"+divId +"\").parent().css(\"display\",\"block\");");
		form.append("});");
		form.append("function " + formId+"_fillFunloc(selCntrl){ ");

		form.append("   var compId =null;var locnId=null; var factId=null;var sectId=null; var cellId=null; var machId=null;");
		form.append(" var flId=  jQuery('#"+ formId + "  input[id=\"flid\"]').val();");
		form.append("if( (selCntrl & 1 ) != 0 ) compId = jQuery('#"+ formId + "  input[id=\"company\"]').val();");
		form.append("if( (selCntrl & 2 ) != 0 )  locnId = jQuery('#"+ formId + "  input[id=\"location\"]').val();");
		form.append("if( (selCntrl & 4 ) != 0 ) factId = jQuery('#"+ formId + "  input[id=\"factory\"]').val();");
		//form.append("if( (selCntrl & 4 ) != 0 ) sbuId = jQuery('#"+ formId + "  input[id=\"sbu\"]').val();");
		//form.append("if( (selCntrl & 8 ) != 0 ) pbuId = jQuery('#"+ formId + "  input[id=\"pbu\"]').val();");
		form.append("if( (selCntrl & 16 ) != 0 ) sectId = jQuery('#"+ formId + "  input[id=\"section\"]').val();");
		form.append("if( (selCntrl & 32 ) != 0 ) cellId = jQuery('#"+ formId + "  input[id=\"cell\"]').val();");
		//form.append("if( (selCntrl & 128 ) != 0 ) teamId = jQuery('#"+ formId + "  input[id=\"team\"]').val();");
		form.append("if( (selCntrl & 128 )  != 0 ) machId = jQuery('#"+ formId + "  input[id=\"machine\"]').val();");


		form.append(" var dataStr = '';");//'&compId='+compId ;");//+'&locnId='+locnId ;"); //+'&factId='+factId ;"); //+'&sectId='+sectId ;");//+ '&cellId='+cellId ;"); // +'&machId='+ machId  ;  ");
		form.append(" if( flId != null && flId.trim() != '' && flId.length >0 ) ");
		form.append("      dataStr +='&flid='+flId ;");
		form.append(" if( compId != null && compId.trim() != '' && compId.length >0 ) ");
		form.append("      dataStr +='&compId='+compId ;");
		form.append(" if( locnId != null && locnId.trim() != '' &&  locnId.length >0 ) ");
		form.append("      dataStr +='&locnId='+locnId ;");
		form.append(" if( factId != null && factId.trim() != '' &&  factId.length >0 ) ");
		form.append("      dataStr +='&factId='+factId ;");
	/*	form.append(" if( sbuId != null && sbuId.trim() != '' && sbuId.length >0 ) ");
		form.append("      dataStr += '&sbuId='+sbuId ;");
		form.append(" if( pbuId != null && pbuId.trim() != '' && pbuId.length >0 ) ");
		form.append("      dataStr += '&pbuId='+pbuId ;");
	*/	form.append(" if( sectId != null && sectId.trim() != '' && sectId.length >0 ) ");
		form.append("      dataStr += '&sectId='+sectId ;");
		form.append(" if( cellId != null && cellId.trim() != '' && cellId.length >0 ) ");
		form.append("      dataStr += '&cellId='+cellId ;");
		/*form.append(" if( teamId != null && teamId.trim() != '' && teamId.length >0 ) ");
		form.append("      dataStr += '&teamId='+teamId ;");*/
		form.append(" if( machId != null && machId.trim() != '' &&  machId.length >0 ) ");
		form.append("      dataStr +='&machId='+ machId ;");
		if( isLock != null)
			form.append("      dataStr +='&disable=" + isLock  + "' ;");
		form.append(" fillFunctionalLocationHierarchy('" + formId + divId +"','" + formId + "', '"+ url + "',dataStr);" );
		form.append("}");
		
		
		form.append("</script>");
		
		form.append("<div id=\""+ formId + divId + "\" >");		
		

		form.append(getFunctLocDisplayString( factoryLayout, formId));
		form.append("</div>");
		/*else{
			form.append("<a id='lin"+formId +"Company'>Company : </a>"   );
			form.append("<a id='lin"+formId +"Location'>Location :</a>,");
			form.append("<a id='lin"+formId +"Factory'>Factory :</a> ,");
			form.append("<a id='lin"+ formId +"Section'>Section :</a>,");
			form.append("<a id='lin"+ formId +"Cell'>Cell : </a>,");
			form.append("<a id='lin"+formId+"Machine'>Machine :</a> "  );
		}
		*/
	//	form.append("<input id='"+ formId + "linShowFuntLocHier' type='button' /> ");
		
		//CommonFunctions.debugMsg("ptrint stmt....."+form.toString());
			
		return form.toString();
		
	}

	private String getDefaultElementHtml(String formId,String defaultId, String value){
		StringBuffer str = new StringBuffer();
		
		formId = formId.replace("'", "");
		formId = formId.trim();
		
		str.append("if( jQuery('#"+ formId + " input[id=\""+ defaultId +"\"]').length <= 0 )");
		str.append("	jQuery('#"+ formId +"FuntKeyIds').append('<input type=\"hidden\" id=\""+ defaultId +"\" name= \"hdn" + defaultId +"\">' ); ");

		str.append(" jQuery('#" + formId + " input[id=\""+defaultId + "\"]').val(\""+ value +"\");");
		

		return str.toString();

	}
	private String getFunctLocHiddenFieldIds(String divId,String formId ,FactoryLayout factoryLayout,BAL_FunctLocHierarchyIdentBean functLocHierarchyIdentBean ){
		StringBuffer str = new StringBuffer();
		
			
		if( factoryLayout != null){
			str.append("if( jQuery(\"#"+ formId + "FuntKeyIds\").length <= 0)");
			str.append("	jQuery(\"#"+divId +"\").append('<div id=\""+formId +"FuntKeyIds\"></div>');");
			
			if( factoryLayout.getCompany() != null)
				str.append(getDefaultElementHtml(formId,"company",factoryLayout.getCompany().getKeyid()));
			if( factoryLayout.getLocation() != null)
				str.append(getDefaultElementHtml(formId,"location",factoryLayout.getLocation().getKeyid()));
			if( factoryLayout.getFactory() != null)
				str.append(getDefaultElementHtml(formId,"factory",factoryLayout.getFactory().getKeyid()));
		/*	if( factoryLayout.getSbu() != null)
				str.append(getDefaultElementHtml(formId,"sbu",factoryLayout.getSbu().getKeyid()));
			if( factoryLayout.getPbu() != null)
				str.append(getDefaultElementHtml(formId,"pbu",factoryLayout.getPbu().getKeyid()));
		*/	if( factoryLayout.getSection() != null)
				str.append(getDefaultElementHtml(formId,"section",factoryLayout.getSection().getKeyid()));
			if( factoryLayout.getCell() != null)			
				str.append(getDefaultElementHtml(formId,"cell",factoryLayout.getCell().getKeyid()));
			
			//if( factoryLayout.getTeam() != null)
				//str.append(getDefaultElementHtml(formId,"team",factoryLayout.getTeam().getKeyid()));
			if( factoryLayout.getMachine() != null )
				str.append(getDefaultElementHtml(formId,"machine",factoryLayout.getMachine().getKeyid()));
			
			if(factoryLayout.getFlid() != null){
				System.out.println("keyid...."+factoryLayout.getFlid().getKeyid());
				str.append(getDefaultElementHtml(formId,"flid",factoryLayout.getFlid().getKeyid()));
				String [] typeAndElementID = factoryLayout.getFlid().getCode().split("##");
				str.append(getDefaultElementHtml(formId,"elementId",typeAndElementID[1]));
				str.append(getDefaultElementHtml(formId,"elementType",typeAndElementID[0]));
			}
			str.append("try{");
			//fact// str.append(" var args = new Object; var compId='compId';var locnId='locnId';var factId='factId';var sbuId='sbuId';var pbuId='pbuId';var sectId='sectId';var cellId='cellId'; var machId='machId';var flId='flId';");
			str.append(" var args = new Object; var compId='compId';var locnId='locnId';var sbuId='sbuId';var pbuId='pbuId';var sectId='sectId';var cellId='cellId'; var machId='machId';var flId='flId';");
			str.append(	" var filterStr = '';");
			if( factoryLayout.getCompany() != null && UIUtils.isValidKeyId(factoryLayout.getCompany().getKeyid() )){
				str.append(" args.compId =\""+ factoryLayout.getCompany().getKeyid() +"\";");
				str.append(" filterStr += 'compId='+\""+ factoryLayout.getCompany().getKeyid() +"\";");
			}	
			if( factoryLayout.getLocation() != null && UIUtils.isValidKeyId(factoryLayout.getLocation().getKeyid() )){
				str.append(" args.locnId =\""+ factoryLayout.getLocation().getKeyid() +"\";");
				str.append(" filterStr += '&locnId='+\""+ factoryLayout.getLocation().getKeyid() +"\";");
			}	
			if( factoryLayout.getFactory() != null && UIUtils.isValidKeyId(factoryLayout.getFactory().getKeyid() ))
				str.append(" args.factId =\""+ factoryLayout.getFactory().getKeyid() +"\";");
			
		/*	if( factoryLayout.getSbu() != null && UIUtils.isValidKeyId(factoryLayout.getSbu().getKeyid() )){
				str.append(" args.sbuId =\""+ factoryLayout.getSbu().getKeyid() +"\";");
				str.append(" filterStr += '&sbuId='+\""+ factoryLayout.getSbu().getKeyid() +"\";");
			}	
			if( factoryLayout.getPbu() != null && UIUtils.isValidKeyId(factoryLayout.getPbu().getKeyid() )){
				str.append(" args.pbuId =\""+ factoryLayout.getPbu().getKeyid() +"\";");
				str.append(" filterStr += '&pbuId='+\""+ factoryLayout.getPbu().getKeyid() +"\";");
			}
		*/		
			if( factoryLayout.getSection() != null && UIUtils.isValidKeyId(factoryLayout.getSection().getKeyid() )){
				str.append(" args.sectId =\""+ factoryLayout.getSection().getKeyid() +"\";");
				str.append(" filterStr += '&sectId='+\""+ factoryLayout.getSection().getKeyid() +"\";");
			}	
			if( factoryLayout.getCell() != null && UIUtils.isValidKeyId(factoryLayout.getCell().getKeyid() )){
				str.append(" args.cellId =\""+ factoryLayout.getCell().getKeyid() +"\";");
				str.append(" filterStr += '&cellId='+\""+ factoryLayout.getCell().getKeyid() +"\";");
			}	
			//if( factoryLayout.getTeam() != null && UIUtils.isValidKeyId(factoryLayout.getTeam().getKeyid() ))
				//str.append(" args.teamId =\""+ factoryLayout.getTeam().getKeyid() +"\";");
			if( factoryLayout.getMachine() != null && UIUtils.isValidKeyId(factoryLayout.getMachine().getKeyid() ))
			{
				str.append(" args.machId =\""+ factoryLayout.getMachine().getKeyid() +"\";");
				str.append(" filterStr += '&combokey='+args.machId;");
			}
			if( factoryLayout.getFlid() != null && UIUtils.isValidKeyId(factoryLayout.getFlid().getKeyid() )){
				str.append(" args.flId =\""+ factoryLayout.getFlid().getKeyid() +"\";");
				String [] typeAndElementID = factoryLayout.getFlid().getCode().split("##");
				str.append(" args.elementId =\""+ typeAndElementID[ 1 ] +"\";");
				str.append(" args.type =\""+ typeAndElementID[ 0 ] +"\";");
			}	
			//
			str.append(	 formId + "_FuntLocHierarchy_SuccessCallBack(args);");
			if( UIUtils.isValidKeyId(functLocHierarchyIdentBean.getMachine().getName())  ){
				str.append("if(filterStr!='')");
				str.append(" reloadCombo('"+formId+"','"+functLocHierarchyIdentBean.getMachine().getName()+"','machineCombo.commonFilter?'+filterStr);");
			}
			
			str.append("}catch(Exception ){ }");
			
			//System.out.println(" str try == " + str);
		}
		return str.toString();
	}
	private String getFunctLocDisplayString(FactoryLayout factoryLayout,String formId){
		
		StringBuffer form = new StringBuffer();
		form.append("<ul  ><li class='dispFunctionalLoccap'> <b > Functional Location :</b><span style='vertical-align:-4px'> <img id='btn"+ formId +"mainFunLoc' src='images/functionalLocimage.jpeg' style='width:18px;height:18px; ' /></span></li>  </ul>");
		form.append("<div id='dispFunctionalLoc' class='easyui-paddingbfpx' >");
		if( factoryLayout != null ){
			
			form.append(getDisplayString(formId,factoryLayout.getCompany(),"Company" ));
			form.append(getDisplayString(formId,factoryLayout.getLocation(),"Location" ));
			form.append(getDisplayString(formId,factoryLayout.getFactory(),"Factory" ));
		//	form.append(getDisplayString(formId,factoryLayout.getSbu(),"Sbu" ));
		//	form.append(getDisplayString(formId,factoryLayout.getPbu(),"Pbu" ));
			form.append(getDisplayString(formId,factoryLayout.getSection(),"Section" ));
			form.append(getDisplayString(formId,factoryLayout.getCell(),"Cell" ));
		//	form.append(getDisplayString(formId,factoryLayout.getTeam(),"Team" ));
			form.append(getDisplayString(formId,factoryLayout.getMachine(),"Machine" ));
		}
		form.append("</div>");
		return form.toString();
	}
	
	private String getDisplayString(String formId,FuntLocnElementDispModel funtLocElement, String elementName){
		String title ="", code = "";
		if( funtLocElement != null && UIUtils.isValidKeyId(funtLocElement.getKeyid() ))
		{
			title = funtLocElement.getName();
			code = funtLocElement.getCode();
			return "<a id='lin"+formId +elementName+"' style='cursor: pointer;' title='"+ title +"' ><u><b> " + code + " </b></u></a> / &nbsp ";
		}
				
		return "";
	}
}
