package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.OplTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.OplTlMstDaoImpl;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.service.OplTlUploadService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

	public class OplUploadServiceImpl implements OplTlUploadService {
		
		private OplTlMstDao oplTlMstDao ; 
		private CommonFilterDao commonFilterDao;
		private Validations validations ;
			
		public OplUploadServiceImpl(DBActionTemplate dbActionTemplate)
		{
			oplTlMstDao = new OplTlMstDaoImpl(dbActionTemplate);
			commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
			validations = new Validations();
		}
		
		public void setOplTlMstDao(OplTlMstDao oplTlMstDao)
		{
			this.oplTlMstDao = oplTlMstDao;
		}
		public OplTlMst createOPlUpload(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception{
			try 
			{
				CommonMessage.debugMsg(" Create=== "+oplFormBean.getFormMode());
				String validationsFor;
				
				if(oplFormBean.getFormActionMode() != null && oplFormBean.getFormActionMode().equals("category") ){
					
					validationsFor = "category";
					validations.validate(newOplTlMst,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					
				}
		
				else if(oplFormBean.getFormMode()!=null){
					CommonMessage.debugMsg("Inside the Create Validaiton");
					validationsFor = "create";
					validations.validate(newOplTlMst,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				}else {
					validationsFor = "create";
					validations.validate(newOplTlMst,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
					validations.validate(oplFormBean,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			}
	
				if(UIUtils.isValidKeyId(oplFormBean.getFormActionMode())&& "approval".equals(oplFormBean.getFormMode())){
					validationsFor = "approval";
					validations.validate(oplFormBean,"opluploadcreation",validationsFor);//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
				} 
			
				fillValues(newOplTlMst,oldOplTlMst,oplFormBean);
				return oplTlMstDao.createOPlUpload(newOplTlMst);
				
			}
			catch (ValidationExceptions e)
			{
				System.out.print(" validate n create exception"+e.getLocalizedMessage());
				throw new ValidationExceptions(e.getMessage());
			}	
		}
		
		private OplTlMst fillValues(OplTlMst newOplTlMst,OplTlMst oldOplTlMst, OplFormBean oplFormBean)
		{
			
			newOplTlMst.setOplmActive("Y");			
			String dateTime = CommonFunctions.dateTimeNow();
			newOplTlMst.setOplmOplupload("Y");
			
			if(newOplTlMst.getOplmKeyid() == null )
			{	
				newOplTlMst.setOplmCreatedon(dateTime);
			}
			else
			{				
				newOplTlMst.setOplmCreatedon(dateTime);
			}
			
			newOplTlMst.setOplmModifiedon(dateTime);
			
			if(newOplTlMst.getOplmDate()==null)
				newOplTlMst.setOplmDate(dateTime);
			
			if(newOplTlMst.getOplmElementid() == null)
				newOplTlMst.setOplmElementid("{}");
			
			if( newOplTlMst.getOplmTpmpillarid() == null )
				newOplTlMst.setOplmTpmpillarid("{}");
		
			if( newOplTlMst.getOplmFactoryid() == null )
				newOplTlMst.setOplmFactoryid("{}");
			
			if( newOplTlMst.getOplmTheme() == null )
				newOplTlMst.setOplmTheme("{}");
			
			if( newOplTlMst.getOplmMachineid() == null )
				newOplTlMst.setOplmMachineid("{}");
			
			if( newOplTlMst.getOplmThemecategoryid() == null )
				newOplTlMst.setOplmThemecategoryid("{}");
			
			if( newOplTlMst.getOplmLesson() == null )
				newOplTlMst.setOplmLesson("{}");
			
			if( newOplTlMst.getOplmApprovedid() == null )
				newOplTlMst.setOplmApprovedid("{}");
			
			if( newOplTlMst.getOplmUtiliseforfuture() == null )
				newOplTlMst.setOplmUtiliseforfuture("N");
			else
				newOplTlMst.setOplmUtiliseforfuture("Y");
			
			if( newOplTlMst.getOplmMpworthy() == null )
				newOplTlMst.setOplmMpworthy("N");
		    else
				newOplTlMst.setOplmMpworthy("Y");
			
			if( newOplTlMst.getOplmIsUpload() == null )
				newOplTlMst.setOplmIsUpload("-");
			
			
			if( newOplTlMst.getOplmIsgeneral() == null )
				newOplTlMst.setOplmIsgeneral("-");
	
			
			if( newOplTlMst.getOplmTempfield4() == null )
				newOplTlMst.setOplmTempfield4("-");
			
			if( newOplTlMst.getOplmTempfield5() == null )
				newOplTlMst.setOplmTempfield5("-");
			
			  if(newOplTlMst.getOplmRelated()==null)
				  newOplTlMst.setOplmRelated("-");     			
			  if(newOplTlMst.getOplmClassification()==null)
				  newOplTlMst.setOplmClassdescription("<**>");  
			  
			if( newOplTlMst.getOplmClassdescription() == null )
				newOplTlMst.setOplmClassdescription("<**>");
			
			if( newOplTlMst.getOplmBenefit() == null )
				newOplTlMst.setOplmBenefit("{}");
				
			newOplTlMst.setOplmType("R");
			
			if( newOplTlMst.getOplmTradeid() == null )
				newOplTlMst.setOplmTradeid("{}");
		
			if( newOplTlMst.getOplmPresentcondition() == null )
				newOplTlMst.setOplmPresentcondition("<**>");
		
			if( newOplTlMst.getOplmAftercondition() == null )
				newOplTlMst.setOplmAftercondition("<**>");
		
			if( newOplTlMst.getOplmPresentimage() == null )
				newOplTlMst.setOplmPresentimage("{}");
		
			if( newOplTlMst.getOplmAfterimage() == null )
				newOplTlMst.setOplmAfterimage("<**>");
			
			if( newOplTlMst.getOplmPrepareddate() == null )
				newOplTlMst.setOplmPrepareddate(Constants.passNullDate);
			
			if( newOplTlMst.getOplmApproveddate() == null )
				newOplTlMst.setOplmApproveddate(Constants.passNullDate);
			
			
			if( newOplTlMst.getOplmRefdoctype() == null)
				newOplTlMst.setOplmRefdoctype("{}");
			
			if( newOplTlMst.getOplmFlid() == null)
				newOplTlMst.setOplmFlid("{}");
			
			if( newOplTlMst.getOplmProcess() == null)
				newOplTlMst.setOplmProcess("{}");
			
			
			if( newOplTlMst.getOplmElementid() == null)
				newOplTlMst.setOplmElementid("{}");
			
			if( newOplTlMst.getOplmRefdocno() == null)
				newOplTlMst.setOplmRefdocno("{}");
			
			if( newOplTlMst.getOplmRemarks() == null)
				newOplTlMst.setOplmRemarks("{}");
			
				newOplTlMst.setOplmRelatedto("C");
				
			if( newOplTlMst.getOplmDepartmentmanager() == null)
				newOplTlMst.setOplmDepartmentmanager("<**>");
			
			if( newOplTlMst.getOplmSectionmanager() == null)
				newOplTlMst.setOplmSectionmanager("<**>");
			
			if( newOplTlMst.getOplmGroupleader() == null)
				newOplTlMst.setOplmGroupleader("<**>");
			
				newOplTlMst.setOplmRequestflag("N");
			
				CommonMessage.debugMsg(" Inside Fill Values :: "+newOplTlMst.getOplmStatus());
			
			if( newOplTlMst.getOplmMouldid() == null)
				newOplTlMst.setOplmMouldid("{}");

			CommonMessage.debugMsg(" Inside Service Impl ::Before "+newOplTlMst.getOplmIsok());
			
			if( newOplTlMst.getOplmIsok()!=null && ( newOplTlMst.getOplmIsok().equalsIgnoreCase("ON") ||
					newOplTlMst.getOplmIsok().equalsIgnoreCase("Y")))
				newOplTlMst.setOplmIsok("Y");
			
			//else if( newOplTlMst.getOplmIsok().equals("N"))
				//newOplTlMst.setOplmIsok("N");
			
			else
				newOplTlMst.setOplmIsok("N");
			
			CommonMessage.debugMsg(" Inside Service After :: "+newOplTlMst.getOplmIsok());
			
			
			CommonMessage.debugMsg(" newOplTlMst :: Before  "+newOplTlMst.getOplmIspresent());
			
			
			if( newOplTlMst.getOplmIspresent()!=null && ( newOplTlMst.getOplmIspresent().equalsIgnoreCase("ON") ||
					newOplTlMst.getOplmIspresent().equalsIgnoreCase("Y")))
				newOplTlMst.setOplmIspresent("Y");
			
			if(UIUtils.isValidKeyId(newOplTlMst.getOplmIspresent())){
				if(newOplTlMst.getOplmIspresent().equals("Y")){
					newOplTlMst.setOplmIspresent("Y");	
				}
			}
				
				
			else
				newOplTlMst.setOplmIspresent("N");
                  if(UIUtils.isValidKeyId(oplFormBean.getOplmwhyhow()))
				newOplTlMst.setOplmIsok("W");

			return newOplTlMst;
		}	
		public OplTlMst updateOPlUpload(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean) throws BusinessApplicationExceptions,ValidationExceptions,Exception{
			String validationsFor;
			if(newOplTlMst != null)
				//validations.validate(newOplTlMst,"oplcreation","mould");
			validations.validate(newOplTlMst,"oplcreation","update");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			validations.validate(oplFormBean,"oplcreation","update");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
			CommonMessage.debugMsg(" Inside SErvice :: "+oplFormBean.getFormMode());
			fillValues(newOplTlMst,oldOplTlMst,oplFormBean);
			return oplTlMstDao.updateOPlUpload(newOplTlMst);
		}
 }
