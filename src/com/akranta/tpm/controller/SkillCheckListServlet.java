package com.akranta.tpm.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ServiceObjectCreationException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SkillCheckListBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.EntTlChecklistdtl;
import com.akranta.tpm.model.EntTlChecklistmst;
import com.akranta.tpm.service.SkillCheckListService;
import com.akranta.tpm.service.impl.SlillCheckListServiceImpl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.FormModes;
import com.akranta.tpm.utils.ReqtParamNameConst;



public class SkillCheckListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SkillCheckListService  skillCheckListService ;		
    /**
     * Created By :KarthicK.T
     * Date       :Aug 06 2012
     */
    public SkillCheckListServlet() {
        super();
        /*try {
        	skillCheckListService = new SlillCheckListServiceImpl();
			
		} catch (Exception e) {
			
		} 
		*/      
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
	
	
	  private void process(HttpServletRequest request, HttpServletResponse response) throws Exception,ValidationExceptions
		{
	    	HttpSession httpSession = request.getSession(false);
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
			if( httpSession != null && user != null)
	    	{
				String action = UIUtils.getActionPart(request);
				try {
					skillCheckListService =(SlillCheckListServiceImpl)UIUtils.getServiceObject(request,"SlillCheckListServiceImpl");
				} catch (ServiceObjectCreationException e) {
					CommonMessage.debugMsg(e);
				}
				response.setContentType("text/html");
				response.setContentType("text/json");
			
				String dispatchUrl = null; 
					
				if (action.equals("SkillCheckListMainGrid_input.checkList")) 
				{
					request.setAttribute("DoubleClick", UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","viewdata"));
					RequestDispatcher rd = request.getRequestDispatcher("/pages/SkillMainGrid.jsp"); 
					rd.forward(request, response); 
				}
				else if(action.equals("SkillCheckListMainGrid_getCol.checkList")) 
				{
					PrintWriter out = response.getWriter();
					out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList","SkillMainGrid"));

				}
				
				else if(action.equals("SkillCheckListMainGrid_getData.checkList")) 
				{
					try {					
												
						PrintWriter out = response.getWriter();							
						List<String[]> CheckListMainGrid =   skillCheckListService.selectCheckListMainGrid();						
						JSONObject holidayData = UIUtils.convertToJqGridTableObject(CheckListMainGrid, request, 0, 0);

						out.println(holidayData);
						
					} catch (Exception e) {
						CommonMessage.debugMsg("CheckList MainGrid Form Exception" + e.getMessage());
					}
				}
				else if (action.equals("SkillCheckList_input.checkList")) 
				{
					
					/*CommonMessage.debugMsg("test value to be consider....................");
					String keyid = request.getParameter(ReqtParamNameConst.KEYID);
					String userEvent = request.getParameter(ReqtParamNameConst.USER_EVENT);
					
					CommonMessage.debugMsg(" userEvent  " + userEvent);
					String lockFields = request.getParameter(ReqtParamNameConst.LOCK_FIELDS);
					response.setContentType("text/html");
					FormModes mode = FormModes.create;	
				
					EntTlChecklistmst EntTlChecklistmst = null;
					
					httpSession.removeAttribute("skillCheckList");
					
					if( ( UIUtils.isValidKeyId(keyid ) && userEvent == null) ||( userEvent != null &&  ! userEvent.equals("new"))){
						
						String formMode = request.getParameter(ReqtParamNameConst.FORM_MODE);
						
						if( formMode == null || (formMode !=null && formMode.equals( FormModeConsts.modify)) )
						{
							mode =  FormModes.modify;
						}else if( formMode.equals( FormModeConsts.view)){
							mode=FormModes.view;
						}
						
						EntTlChecklistmst = skillCheckListService.select(keyid);
						httpSession.setAttribute("skillCheckList" , EntTlChecklistmst);
						request.setAttribute("skillCheckList", EntTlChecklistmst);
					}
					//mode=FormModes.view;
					
					SkillCheckListBean skillCheckListBean = new SkillCheckListBean(mode,lockFields);
					
					httpSession.setAttribute("skillCheckListBean", skillCheckListBean);
					request.setAttribute("skillCheckListBean", skillCheckListBean);
					request.setAttribute("EntTlChecklistmst", EntTlChecklistmst);

					*/
					String rating = request.getParameter("basedRating");
					String keyId = request.getParameter("keyId");
					String topicId =""; 
					String rattingId = "";
					
					if (UIUtils.isValidKeyId(keyId)) {
						String[] keyids=keyId.split("-");

						topicId =keyids[0]; 
						//CommonMessage.debugMsg("keyids.length"+keyids.length);
						if (keyids.length>=2)
							rattingId = keyids[1];
					}
					CommonMessage.debugMsg("rattingId........." + rattingId);	
					httpSession.setAttribute("getBasedOn" , rating);
					request.setAttribute("topicId", topicId);
					request.setAttribute("rattingId", rattingId);
					RequestDispatcher rd = request.getRequestDispatcher("/pages/ENT/SkillCkeckList.jsp"); 
					rd.forward(request, response); 
				}
				
				else if(action.equals("SkillCheckList_getCol.checkList"))
				{
						PrintWriter out = response.getWriter();
						out.println(UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList","SkillCheckList"));
				}
				else if(action.equals("SkillCheckList_getData.checkList"))
				{
					try {
						String topicId = request.getParameter("topicId");
						String rattingId = request.getParameter("rattingId");
						String rating = (String) httpSession.getAttribute("getBasedOn");
						List<String[]> CheckList = null;
						PrintWriter out = response.getWriter();					

						if(UIUtils.isValidKeyId(rating) && rating.equals("Y"))
							CheckList =   skillCheckListService.selectCheckListRating(topicId,rattingId);
						else if( UIUtils.isValidKeyId(topicId))
							CheckList =   skillCheckListService.selectCheckList(topicId);//recall
						else
							CheckList = skillCheckListService.getAllCheckList();
						
						JSONObject checkListData = UIUtils.convertToJqGridTableObject(CheckList, request, 0, 0);
						out.println(checkListData);
						
					} catch (Exception e) {
						CommonMessage.debugMsg("CheckList Form Exception" + e.getMessage());
					}
				}
					
				else if( action.equals("SkillCheckListMainGrid_getExcel.checkList"))
				{	
					String tmpFromRow = "0";
					
					String tableModel = UIUtils.getPropertyValue("com.akranta.tpm.resources.SkillCheckList","SkillMainGrid");
					JSONObject tblJSONObj = JSONObject.fromString(tableModel);					
					tblJSONObj.put("title", "Skill Grid Report");
					String format = ExcelUtils.getFormat(request);					
					Workbook wb = skillCheckListService.SkillExportExcel(tblJSONObj,format);
									
					ExcelUtils.writeToResponse(response, wb, "SkillGrid", format);
				}
				
				else if(action.equals("SkillCheckList_save.checkList"))
				{	
					SkillCheckListBean skillCheckListBean = (SkillCheckListBean)httpSession.getAttribute("skillCheckListBean");
					//CommonMessage.debugMsg("save ......"+genTlHolidaymstBean.getBeanYear());
				    saveSkillCheckList(request,response,skillCheckListBean);
				}
				
				else if( action.equals("SkillCheckList_delete.checkList"))
				{	
					SkillCheckListBean skillCheckListBean = (SkillCheckListBean)httpSession.getAttribute("skillCheckListBean");
					deleteSkillCheckList(request,response,skillCheckListBean);
					
			    }
				else if( action.equals("checkListDelete_Delete.checkList"))
				{
					
					/*ServletOutputStream out = response.getOutputStream();
					String keyId =request.getParameter(ReqtParamNameConst.KEYID);
					
					List<String[]> CheckList = skillCheckListService.deleteDetail(keyId);*/
					
					SkillCheckListBean skillCheckListBean = (SkillCheckListBean)httpSession.getAttribute("skillCheckListBean");
					deleteCheckListDetail(request,response,skillCheckListBean);
				}
				else if(action.equals("SkillCheckList_recallDtl.checkList"))
				{
					ServletOutputStream out = response.getOutputStream();
					String keyId =request.getParameter(ReqtParamNameConst.KEYID);
					
					SkillCheckListBean skillCheckListBean = new SkillCheckListBean(FormModes.modify);
					EntTlChecklistdtl entTlChecklistdtl = skillCheckListService.selectDtl(keyId);
					
					JSONObject  checkListDataDtl =  UIUtils.fromTpmModel(entTlChecklistdtl);
					JSONObject returndata = new JSONObject();
					returndata.put("checkListDataDtl", checkListDataDtl);					
					
					out.print(returndata.toString());
					
				}
				else if(action.equals("SkillCheckList_recallrank.checkList"))
				{
					
					ServletOutputStream out = response.getOutputStream();
					String topicId =request.getParameter("topicId");
					String rattingId =request.getParameter("rattingId");
					
					SkillCheckListBean skillCheckListBean = new SkillCheckListBean(FormModes.modify);
					
					httpSession.removeAttribute("skillCheckListBean");
					httpSession.setAttribute("skillCheckListBean", skillCheckListBean);
					CommonMessage.debugMsg("topicId................"+topicId);
					CommonMessage.debugMsg("rattingId............."+rattingId);
					
					
					httpSession.removeAttribute("entTlChecklistmst");
					EntTlChecklistmst entChecklistRecall = skillCheckListService.selectRank(topicId,rattingId);
					
					httpSession.setAttribute("entTlSkillChecklist" ,entChecklistRecall);
					JSONObject  checkListData =  UIUtils.fromTpmModel(entChecklistRecall);
					
					JSONObject returndata = new JSONObject();
					returndata.put("checkListData", checkListData);
					
					
					out.print(returndata.toString());
					
				}
			   else if(action.equals("SkillCheckList_recall.checkList"))
				{	
				   /* ServletOutputStream out = response.getOutputStream();
					String keyId =request.getParameter(ReqtParamNameConst.KEYID);
					
					SkillCheckListBean skillCheckListBean = new SkillCheckListBean(FormModes.modify);
					
					httpSession.removeAttribute("skillCheckListBean");
					httpSession.setAttribute("skillCheckListBean", skillCheckListBean);
					
					httpSession.removeAttribute("entTlChecklistmst");
					EntTlChecklistmst entChecklistRecall = skillCheckListService.select(keyId);
					
					httpSession.setAttribute("entTlSkillChecklist" ,entChecklistRecall);
					JSONObject  checkListData =  UIUtils.fromTpmModel(entChecklistRecall);
					CommonMessage.debugMsg("inside action" + checkListData );
					JSONObject returndata = new JSONObject();
					returndata.put("checkListData", checkListData);
					out.print(returndata.toString());*/
				   
				   
				   
					PrintWriter out = response.getWriter();
					String keyId =request.getParameter(ReqtParamNameConst.KEYID);
					String dtlId = request.getParameter("dtlId");
					EntTlChecklistmst entChecklistRecall = skillCheckListService.select(keyId,dtlId);
					
					httpSession.setAttribute("newGenTlEmployeemst", entChecklistRecall);
					JSONObject checkListData = UIUtils.fromTpmModel(entChecklistRecall);
					JSONObject checkListDtl = null;
					List<EntTlChecklistdtl> checkListdtlList = entChecklistRecall.getCheckListDetail();
					
					if(checkListdtlList != null  && checkListdtlList.size() > 0){
						EntTlChecklistdtl newEntTlChecklistdtl = checkListdtlList.get(0);
						checkListDtl = UIUtils.fromTpmModel(newEntTlChecklistdtl);						
					}
					
					JSONObject returndata = new JSONObject();
					returndata.put("checkListMst", checkListData);
					returndata.put("checkListDtl", checkListDtl);
					out.print(returndata);
					
				}
			   
				if (dispatchUrl != null)
				{
					UIUtils.forwardRequest(request, response, dispatchUrl);
				}
	    	}
		}	

	  
	  
	 

	private void deleteCheckListDetail(HttpServletRequest request,	HttpServletResponse response, SkillCheckListBean skillCheckListBean) throws Exception {
		HttpSession httpSession = request.getSession(false);
		PrintWriter outdel = response.getWriter();
		//ServletOutputStream outdel = response.getOutputStream();
		AdmTlUsermst user = UIUtils.getLoginUser(request);

		if( httpSession != null && user != null)
		{	
			
			//String BatchFacultyId = request.getParameter("BatchFacultyId");
			
			String keyId =request.getParameter(ReqtParamNameConst.KEYID);
			EntTlChecklistmst newEntTlChecklistmst = new EntTlChecklistmst();
    		EntTlChecklistdtl newEntTlChecklistdtl = new EntTlChecklistdtl();
			
			//skillCheckListBean =(SkillCheckListBean)UIUtils.setBeanProperties((Object)skillCheckListBean,request);
			httpSession.getAttribute("EntBatchFacultyCreationServlet");
			
			try{
						String incEmpId=request.getParameter("incEmpId");
						newEntTlChecklistdtl.setChkdKeyid(keyId);
						
						EntTlChecklistdtl delStaus = skillCheckListService.deleteDetail(newEntTlChecklistdtl);
											
						JSONObject returnData = new JSONObject();					
						returnData.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));		
						returnData.put("formClear",true);
						returnData.put("rowId",request.getParameter("rowId"));
						outdel.print(returnData.toString());
						outdel.close();
				}
			
			
			catch(ValidationExceptions e)
			{
				PrintWriter  outexdel = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"EntTlBatchFacultyException");
				
				errMessage.put("formActionMode",skillCheckListBean.getFormActionMode());				
				outexdel.print(errMessage.toString());				
			}catch(BusinessApplicationExceptions e)
			{
				PrintWriter  out = response.getWriter();
				CommonMessage.debugMsg("Error Servler e -"+e.toString());
				String errMessage = UIUtils.getOracleConstraintMessages(e.toString());
				JSONObject err = new JSONObject();
				err.put("tpmException", errMessage);
				out.print(err.toString());
				
				CommonMessage.debugMsg(" e " + err.toString() );
				
			}
			catch(Exception e)
			{
				PrintWriter  out = response.getWriter();
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				String errMessage = UIUtils.getOracleConstraintMessages(e.toString());
				JSONObject err = new JSONObject();
				err.put("tpmException", errMessage);
				out.print(err.toString());
			}
			
		}
		
	}

	private void saveSkillCheckList(HttpServletRequest request,HttpServletResponse response,SkillCheckListBean skillCheckListBean) throws IOException{
			
	    	UIUtils.displayRequestParamsValue(request);
	    	HttpSession httpSession = request.getSession(false);
	    	
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		if( skillCheckListBean == null)
	    			skillCheckListBean = new SkillCheckListBean(FormModes.create);
	    		
	    		EntTlChecklistmst newEntTlChecklistmst = new EntTlChecklistmst();
	    		EntTlChecklistdtl newEntTlChecklistdtl = new EntTlChecklistdtl();
	    		
	    		newEntTlChecklistmst.setChkmCreatedby(user.getUsrm_ccno());
	    		newEntTlChecklistdtl.setChkdCreatedby(user.getUsrm_ccno());
	    		
	    		newEntTlChecklistmst = (EntTlChecklistmst)UIUtils.setBeanProperties((Object)newEntTlChecklistmst,request);
	    		newEntTlChecklistdtl = (EntTlChecklistdtl)UIUtils.setBeanProperties((Object)newEntTlChecklistdtl,request);
	    		
	    		if( newEntTlChecklistdtl != null)
	    			newEntTlChecklistmst.getCheckListDetail().add(newEntTlChecklistdtl);
	    		
	    		EntTlChecklistmst existEntTlChecklistmst = (EntTlChecklistmst)httpSession.getAttribute("EntTlChecklistmst");
				skillCheckListBean = (SkillCheckListBean) UIUtils.setBeanProperties((Object) skillCheckListBean, request);
				
				
				
				try{
					boolean insert = true;
					if( ! UIUtils.isValidKeyId( newEntTlChecklistmst.getChkmKeyid()) ) // ||  ! UIUtils.isValidKeyId( newEntTlChecklistmst.getChkmSkrmKeyid())
					{	
						existEntTlChecklistmst = skillCheckListService.create(newEntTlChecklistmst,existEntTlChecklistmst,skillCheckListBean);
					}
					else{
						insert = false;
						existEntTlChecklistmst = skillCheckListService.update(newEntTlChecklistmst,existEntTlChecklistmst,skillCheckListBean);
						}				
				
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("CheckListKeyid",existEntTlChecklistmst.getChkmKeyid() );
					JSONObject successData = new JSONObject();
				    String msgPropertyIdnt;
				 
				 if( insert){
					msgPropertyIdnt = "success-save";
				 }else
					msgPropertyIdnt = "success-update";
				 
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages",msgPropertyIdnt));
				successData.put("ChekKeyid", existEntTlChecklistmst.getChkmKeyid());
				
				JSONObject returnData = new JSONObject();				
				returnData.put("successData", successData);						
				
				List<EntTlChecklistdtl> entTlChecklistdtls = existEntTlChecklistmst.getCheckListDetail();
				if( entTlChecklistdtls != null && entTlChecklistdtls.size()> 0 )
				{
					EntTlChecklistdtl entTlChecklistdtl = entTlChecklistdtls.get(0);
					returnData.put("succDetailmstid",entTlChecklistdtl.getChkdChkmKeyid());
				//	returnData.put("succDetaildtlid",entTlChecklistdtl.getChkdKeyid());					
				}
			
				returnData.put("skillId",existEntTlChecklistmst.getChkmSkrmKeyid());
				returnData.put("topicId",existEntTlChecklistmst.getChkmTopiKeyid());
		    	PrintWriter  out = response.getWriter();
				out.print(returnData.toString());
				out.close();
				
				
			}catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("ValidationExceptions");
				PrintWriter  out = response.getWriter();
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"SkillCheckListCreation");
				errMessage.put("formActionMode",skillCheckListBean.getFormActionMode());
				out.print(errMessage.toString());
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Exception.."+e.getMessage());
				PrintWriter  out = response.getWriter();
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Saved");
				err.put("tpmException","Check Points already Exists");
				
				JSONObject returnData = new JSONObject();
				returnData.put("successData", "alerady Exists");
				returnData.put("successData", returnData);
				
				out.print(err.toString());
			}
	    }	
	 }
	  
	  
	  private void deleteSkillCheckList(HttpServletRequest request,HttpServletResponse response, SkillCheckListBean skillCheckListBean) throws IOException
		{
		/*	UIUtils.displayRequestParamsValue(request);
			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		if( skillCheckListBean == null)
	    			skillCheckListBean = new SkillCheckListBean(FormModes.create);
	    		
	    		EntTlChecklistmst existEntTlChecklistmst = (EntTlChecklistmst)httpSession.getAttribute("genTlHolidaymstServlet"); 
	    		EntTlChecklistmst newEntTlChecklistmst = new EntTlChecklistmst();
	    		newEntTlChecklistmst.setChkmCreatedby(user.getUsrm_ccno());
	    	
	    		newEntTlChecklistmst =(EntTlChecklistmst)UIUtils.setBeanProperties((Object)newEntTlChecklistmst,request);
	    		skillCheckListBean =(SkillCheckListBean)UIUtils.setBeanProperties((Object)skillCheckListBean,request);
	    		//genTlHolidaymstBean =(GenTlHolidaymstBean) UIUtils.setBeanProperties((Object)genTlHolidaymstBean,request);
	    		
			try{
				
				
				existEntTlChecklistmst = skillCheckListService.delete(newEntTlChecklistmst,skillCheckListBean);
				
				existEntTlChecklistmst.setChkmKeyid("N");
				
				JSONObject mode = new JSONObject();
			
				JSONObject persistentData = new JSONObject(); 
				persistentData.put("HolmKeyid",existEntTlChecklistmst.getChkmKeyid());
				//persistentData.put("fromBean", formBeanIdentifier);
				
				JSONObject forwardData = new JSONObject();
				forwardData.put("HolmKeyid",existEntTlChecklistmst.getChkmKeyid());
				//CommonMessage.debugMsg("servlet out put:" + forwardData);
				mode.put("forwardData", forwardData);
				mode.put("persistentData", persistentData);
				
				JSONObject successData = new JSONObject();
				successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));

				successData.put("formActionMode",skillCheckListBean.getFormActionMode() );
				successData.put("HolmKeyid", existEntTlChecklistmst.getChkmKeyid());
				JSONObject returnData = new JSONObject();		
				
				httpSession.removeAttribute("genTlHolidaymstServlet");
				httpSession.removeAttribute("genTlHolidaymstBean");
				
				returnData.put("formClear",true);
				returnData.put("successData", successData);		
				out.print(returnData.toString());
					
			}catch(ValidationExceptions e)
			{
				CommonMessage.debugMsg("test...");
				JSONObject errMessage = UIUtils.validationExceptions(e.toString(),"holidayCreation");
				errMessage.put("formActionMode",skillCheckListBean.getFormActionMode());
				out.print(errMessage.toString());
				CommonMessage.debugMsg("test..."+errMessage);
			}catch(BusinessApplicationExceptions e)
			{ 
				CommonMessage.debugMsg("BusinessApplicationExcepions"  );
				JSONObject successData = UIUtils.businessValidationExceptions(e.toString(),"holidayCreation");
				//out.print(errMessage.toString());
				successData.put("msg", "Original Id Exists");
				JSONObject returnData = new JSONObject();	
				
				returnData.put("formClear",false);
				returnData.put("successData", successData);	
							
			}catch(Exception e)
			{
				CommonMessage.debugMsg("Error Msg:" + e.getMessage());
				JSONObject err = new JSONObject();
				//err.put("tpmException", "Data Not Deleted");
				err.put("tpmException",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","err-delete"));
				out.print(err.toString());
			}
	    	}*/
		  
		  
		  

			HttpSession httpSession = request.getSession(false);
	    	ServletOutputStream out = response.getOutputStream();
	    	AdmTlUsermst user = UIUtils.getLoginUser(request);
	    	
	    	if( httpSession != null && user != null)
	    	{	
	    		
	    		if( skillCheckListBean == null)
	    			skillCheckListBean = new SkillCheckListBean(FormModes.create);
	    		
	    		EntTlChecklistmst existEntTlChecklistmst = (EntTlChecklistmst)httpSession.getAttribute("newSession"); 
	    		//GenTlEmployeedtl existGenTlEmployeedtl = (GenTlEmployeedtl)httpSession.getAttribute("genTl"); 
	    		
	    		EntTlChecklistmst newEntTlChecklistmst = new EntTlChecklistmst();
	    		EntTlChecklistdtl newEntTlChecklistdtl = new EntTlChecklistdtl();
	    		
	    		
	    		newEntTlChecklistmst.setChkmCreatedby(user.getUsrm_ccno());
	    		newEntTlChecklistdtl.setChkdCreatedby(user.getUsrm_ccno());
	    	
	    		newEntTlChecklistmst =(EntTlChecklistmst)UIUtils.setBeanProperties((Object)newEntTlChecklistmst,request);
	    		newEntTlChecklistdtl =(EntTlChecklistdtl)UIUtils.setBeanProperties((Object)newEntTlChecklistdtl,request);
	    		
	    		
	    		
	    		newEntTlChecklistmst.getCheckListDetail().add(newEntTlChecklistdtl);
	    		
	    		
	    		
	    		skillCheckListBean =(SkillCheckListBean)UIUtils.setBeanProperties((Object)skillCheckListBean,request);
				
				try{
										
										
						existEntTlChecklistmst = skillCheckListService.delete(newEntTlChecklistmst,skillCheckListBean);
					
					CommonMessage.debugMsg("After the IF Loop");
					httpSession.setAttribute(existEntTlChecklistmst.getChkmKeyid(), existEntTlChecklistmst);
					httpSession.setAttribute("GenTlEmployeemst", existEntTlChecklistmst);
					String formBeanIdentifier = "SkillCheckListBean"+skillCheckListBean.getFormActionMode();
					httpSession.setAttribute(formBeanIdentifier,skillCheckListBean);
							
					JSONObject mode = new JSONObject();
					mode.put("formMode",skillCheckListBean.getFormActionMode());
					JSONObject persistentData = new JSONObject(); 
					persistentData.put("EmpmKeyid",existEntTlChecklistmst.getChkmKeyid());
					persistentData.put("fromBean", formBeanIdentifier);
					
					JSONObject forwardData = new JSONObject();		
								
					JSONObject successData = new JSONObject();
					CommonMessage.debugMsg("SuccessData");
					successData.put("msg",UIUtils.getPropertyValue("com.akranta.tpm.resources.CommonMessages","success-delete"));
					
					JSONObject returnData = new JSONObject();				
					returnData.put("successData", successData);			
					List<EntTlChecklistdtl> entTlChecklistdtls = existEntTlChecklistmst.getCheckListDetail();
					if( entTlChecklistdtls != null && entTlChecklistdtls.size()> 0 )
					{
						EntTlChecklistdtl entTlChecklistdtl = entTlChecklistdtls.get(0);
						returnData.put("succDetailmstid",entTlChecklistdtl.getChkdChkmKeyid());
					//	returnData.put("succDetaildtlid",entTlChecklistdtl.getChkdKeyid());					
					}
					CommonMessage.debugMsg("Skill Id " +existEntTlChecklistmst.getChkmSkrmKeyid());
					returnData.put("skillId",existEntTlChecklistmst.getChkmSkrmKeyid());
					returnData.put("topicId",existEntTlChecklistmst.getChkmTopiKeyid());
			    	//PrintWriter  out = response.getWriter();
					out.print(returnData.toString());
					out.close();
					
				}catch(ValidationExceptions e)
				{
					JSONObject errMessage = UIUtils.validationExceptions(e.toString(),  "SkillCheckListCreation");
					errMessage.put("fromMode",skillCheckListBean.getFormActionMode());
					out.print(errMessage.toString());
					
				}catch(BusinessApplicationExceptions e)
				{
					CommonMessage.debugMsg("Business EXC "+e);
					JSONObject errMessage = UIUtils.businessValidationExceptions(e.toString(),  "SkillCheckListCreation");
					out.print(errMessage.toString());
					
				}catch(Exception e)
				{
					/*CommonMessage.debugMsg("Error Msg:" + e.getMessage());
					JSONObject err = new JSONObject();
					err.put("tpmException", "Double Click Row Data");
					out.print(err.toString());*/
					
					CommonMessage.debugMsg("Error Servler e -"+e.toString());
					String errMessage = UIUtils.getOracleConstraintMessages(e.toString());
					JSONObject err = new JSONObject();
					err.put("tpmException", errMessage);
					out.print(err.toString());
					
					CommonMessage.debugMsg(" e " + err.toString() );
				}
	    	}
		}
}


