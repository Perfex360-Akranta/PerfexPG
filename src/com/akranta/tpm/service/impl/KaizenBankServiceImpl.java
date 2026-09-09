package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KaizenBankDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KaizenBankDaoImpl;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.KaizenBankService;
import com.akranta.tpm.service.api.ControlResponseplanserviceApi;
import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

 public class KaizenBankServiceImpl implements KaizenBankService{

	 private KaizenBankDao kaizenBankDao;
	 private CommonFilterDao commonFilterDao;
	 private Validations validation;
	 private KaizenBankServiceAPI kaizenBankServiceApi;
	 
	 public KaizenBankServiceImpl(DBActionTemplate dbActionTemplate) {
		 kaizenBankDao=new KaizenBankDaoImpl(dbActionTemplate);
		 commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		 validation = new Validations();
	 }
	 
	 public void KaizenBankServiceImplJwt(String JwtToken){
	    	try{
	    		kaizenBankDao.KaizenBankDaoImplJwt(JwtToken);
	    		kaizenBankServiceApi = new KaizenBankServiceAPI(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
	 
	 
	 
	@Override
	public List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception {
		return this.kaizenBankDao.getfillgriddata(commonFilter);
	}
	
	@Override
	public List<String[]> getfillgriddataIndividual(CommonFilter commonFilter)throws Exception {
		return this.kaizenBankDao.getfillgriddataIndividual(commonFilter);
	}


	@Override
	public KznTlKaizenbankmst create(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions,BusinessApplicationExceptions {
		try{
			String validateFor="create";
			CommonMessage.debugMsg("Validation"+type+"validateFor"+validateFor);
			if(UIUtils.isValidKeyId(type))
			{
				CommonMessage.debugMsg(type+"  status   "+newKznTlKaizenbankmst.getKzbnStatus());
				if("A".equals(type)){
					String status=newKznTlKaizenbankmst.getKzbnStatus();
					if("A".equals(status))
						validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","accept");
					else if("R".equals(status))
						validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","reject");
				}
				else if("I".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","implement");
				else if("C".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","complete");
				else if("V".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","verify");
				else if("S".equals(type)){
					CommonMessage.debugMsg(" inside type S ");
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation",validateFor);
				}
			}
			fillValues(newKznTlKaizenbankmst,existKznTlKaizenbankmst,AccSingle);
		}catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage() buss  "+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage() vali "+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch (Exception e){
			CommonMessage.debugMsg("e.getMessage() excep "+e.getMessage());
			throw new Exception(e.getMessage());
		}
		//return kaizenBankDao.create(newKznTlKaizenbankmst,type,dataKeyidArr,dataFlidArr,dataSuggestArr);
		return kaizenBankServiceApi.saveKznTlKaizenBankMst(newKznTlKaizenbankmst);
		
	}

	@Override
	public KznTlKaizenbankmst update(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions,BusinessApplicationExceptions  {
		try{
			if(UIUtils.isValidKeyId(type))
			{
				CommonMessage.debugMsg(type+"  status   "+newKznTlKaizenbankmst.getKzbnStatus());
				if("A".equals(type)){
					String status=newKznTlKaizenbankmst.getKzbnStatus();
					if("A".equals(status))
					{
						validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","accept");
					}
					else if("R".equals(status))
						validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","reject");
				}
				else if("I".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","implement");
				else if("C".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","complete");
				else if("V".equals(type))
					validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","verify");
				else if("S".equals(type))
				{
					//CommonMessage.debugMsg("AccSingle "+AccSingle);
					if(UIUtils.isValidKeyId(AccSingle) && "Y".equals(AccSingle.trim())){
						String status=newKznTlKaizenbankmst.getKzbnStatus();
						if("A".equals(status))
						{
							validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","accept");
						}
						else if("R".equals(status))
							validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","reject");
					}
					else
						validation.validate(newKznTlKaizenbankmst, "KaizenBankValidation","create");
				}
					
			}
			fillValues(newKznTlKaizenbankmst,existKznTlKaizenbankmst,AccSingle);
		}catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage() buss  "+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage() vali "+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}catch (Exception e){
			CommonMessage.debugMsg("e.getMessage() excep "+e.getMessage());
			throw new Exception(e.getMessage());
		}
//	return kaizenBankDao.update(newKznTlKaizenbankmst ,type,dataKeyidArr,dataFlidArr,dataSuggestArr);	
		return kaizenBankServiceApi.saveKznTlKaizenBankMst(newKznTlKaizenbankmst);
	} 
	private KznTlKaizenbankmst fillValues(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String AccSingle) throws Exception
	{
		newKznTlKaizenbankmst.setKzbnActive("Y");
		if(UIUtils.isValidKeyId(AccSingle) && "Y".equals(AccSingle)){
			newKznTlKaizenbankmst.setKzbnAcrejby(newKznTlKaizenbankmst.getKzbnResponsibility());
		}
		
		CommonMessage.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImplementedon());
		CommonMessage.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImplementedby());
		CommonMessage.debugMsg(" getKzbnImplementedon "+newKznTlKaizenbankmst.getKzbnImpremarks());
		String dateTime = CommonFunctions.pg_dateTimeNow();
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnBenefit()) )
			newKznTlKaizenbankmst.setKzbnBenefit("{}");
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnCompletedon()) )
			newKznTlKaizenbankmst.setKzbnCompletedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnKaizen()) )
			newKznTlKaizenbankmst.setKzbnKaizen("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnPqcdsme()) )
			newKznTlKaizenbankmst.setKzbnPqcdsme("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAccrejremarks()) )
			newKznTlKaizenbankmst.setKzbnAccrejremarks("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnStatus()) )
			newKznTlKaizenbankmst.setKzbnStatus("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnResponsibility()) )
			newKznTlKaizenbankmst.setKzbnResponsibility(newKznTlKaizenbankmst.getKzbnSuggestedby());
		
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnResponsibility()) ){
			newKznTlKaizenbankmst.setKzbnResponsibility("{}");
		}
		/*if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnResponsibility()) ){
			if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnSuggestedby()) ){
				if(UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnAcrejby()) ){
					CommonMessage.debugMsg("inside res acreby");
					newKznTlKaizenbankmst.setKzbnResponsibility(newKznTlKaizenbankmst.getKzbnAcrejby());
				}
			}
		} */
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnSuggestedby()) )
			newKznTlKaizenbankmst.setKzbnSuggestedby("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnTargetdate()) )
			newKznTlKaizenbankmst.setKzbnTargetdate(Constants.pgFutureNullDateTime);
		else 
			newKznTlKaizenbankmst.setKzbnAcceptrejon(dateTime);
		String date = newKznTlKaizenbankmst.getKzbnDate();
		newKznTlKaizenbankmst.setKzbnDate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnDate()) )
			newKznTlKaizenbankmst.setKzbnDate(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAccrejremarks()) )
			newKznTlKaizenbankmst.setKzbnAccrejremarks("{}");
		if(!UIUtils.isValidKeyId( newKznTlKaizenbankmst.getKzbnCompremarks()) )
			newKznTlKaizenbankmst.setKzbnCompremarks("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementedby()))
			newKznTlKaizenbankmst.setKzbnImplementedby("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnFlid()))
			newKznTlKaizenbankmst.setKzbnFlid("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAcrejby()))
			newKznTlKaizenbankmst.setKzbnAcrejby("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImpremarks()))
			newKznTlKaizenbankmst.setKzbnImpremarks("{}");
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnAcceptrejon()))
			newKznTlKaizenbankmst.setKzbnAcceptrejon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementedon()))
			newKznTlKaizenbankmst.setKzbnImplementedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifiedon()))
			newKznTlKaizenbankmst.setKzbnVerifiedon(Constants.pgFutureNullDateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifiedby()))
			newKznTlKaizenbankmst.setKzbnVerifiedby("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnCompletedby()) )
			newKznTlKaizenbankmst.setKzbnCompletedby("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnVerifyremarks()) )
			newKznTlKaizenbankmst.setKzbnVerifyremarks("{}");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnElementid()) )
			newKznTlKaizenbankmst.setKzbnElementid("{}");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnEhsrelated()))
			newKznTlKaizenbankmst.setKzbnEhsrelated("N");
		else if(newKznTlKaizenbankmst.getKzbnEhsrelated().equalsIgnoreCase("ON"))
			newKznTlKaizenbankmst.setKzbnEhsrelated("Y");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnEhsstatus()))
			newKznTlKaizenbankmst.setKzbnEhsstatus("X");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnRefdoctype()))
			newKznTlKaizenbankmst.setKzbnRefdoctype("-");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnRefdocno()))
			newKznTlKaizenbankmst.setKzbnRefdocno("-");
		
		 CommonMessage.debugMsg(" Inside Service Impl Fill Values :: "+newKznTlKaizenbankmst.getkzbnOthers());
		   
	    if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getkzbnOthers()))
			newKznTlKaizenbankmst.setkzbnOthers("N");
	    else if(newKznTlKaizenbankmst.getkzbnOthers().equalsIgnoreCase("ON"))
			newKznTlKaizenbankmst.setkzbnOthers("Y");
	
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnImplementcost()))
			newKznTlKaizenbankmst.setKzbnImplementcost("0");
		
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnApprovalflag()))
			newKznTlKaizenbankmst.getKzbnApprovalflag("-");
		if(!UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnmocrequired()))
			newKznTlKaizenbankmst.setKzbnmocrequired("-");
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnCreatedby()) )
			newKznTlKaizenbankmst.setKzbnCreatedby("{}");
		if( newKznTlKaizenbankmst.getKzbnKeyid() != null )
			newKznTlKaizenbankmst.setKzbnCreatedon(existKznTlKaizenbankmst.getKzbnCreatedon());
		else
			newKznTlKaizenbankmst.setKzbnCreatedon(dateTime);
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnModifiedon()) )
			newKznTlKaizenbankmst.setKzbnModifiedon(dateTime);
		
		if( !UIUtils.isValidKeyId(newKznTlKaizenbankmst.getKzbnNonJhEsp()) )
			newKznTlKaizenbankmst.setKzbnNonJhEsp("N");
		
		
		String elementId = kaizenBankDao.getElementID (newKznTlKaizenbankmst.getKzbnFlid());
		newKznTlKaizenbankmst.setKzbnElementid(elementId);
		return newKznTlKaizenbankmst; 
		
	}
	public KznTlKaizenbankmst delete(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception {
		return kaizenBankDao.delete(newKznTlKaizenbankmst);	
	}

	@Override
	public KznTlKaizenbankmst getRecall(String keyId) throws Exception {
		// TODO Auto-generated method stub
		return kaizenBankDao.getRecall(keyId);
	}

	@Override
	public Workbook getKaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kaizenBankDao.getKaizenExcel(colmodel,format, commonFilter);
	}

	@Override
	public KznTlKaizenbankmst selectmst(String keyid) throws NoDataFoundException, SQLException, Exception {
//		return kaizenBankDao.selectmst(keyid)

		return kaizenBankServiceApi.getById(keyid);
	}

	@Override
	public List<String[]> getmaingrid(CommonFilter commonFilter)
			throws Exception {
		return kaizenBankDao.getmaingrid(commonFilter);
	}

	@Override
	public Workbook getKaizenMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return kaizenBankDao.getKaizenMainExcel(colmodel,format, commonFilter);
	}

	@Override
	public List<String[]> getSafetySuggGrid(CommonFilter commonFilter)
			throws Exception {
		return kaizenBankDao.getSafetySuggGrid(commonFilter);
	}

	@Override
	public Workbook getSafetyExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return kaizenBankDao.getSafetyExcel(colmodel,format, commonFilter);
	}

	@Override
	public List<KznTlKaizenbankmst> updateSugg(List<KznTlKaizenbankmst> kznTlEvmstList) throws BusinessApplicationExceptions, Exception {
		return kaizenBankDao.updateSugg(kznTlEvmstList);
	}
	
	public KznTlKaizenbankmst updateVerifyDetails(List<KznTlKaizenbankmst> kaizenVerifyList )throws ValidationExceptions, Exception
	{
		//validations.validate(kznTlKaizenbankmst,"empAttendance","create");
		//fillValues(kznTlKaizenbankmst);
		return kaizenBankDao.updateVerifyDetails(kaizenVerifyList );
	}
	public List<String[]> getSafetySuggestionSummaryGridData(CommonFilter commonFilter) throws Exception{
		return kaizenBankDao.getSafetySuggestionSummaryGridData(commonFilter);
	}
	
	@Override
	public Workbook getSafetySuggestionSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception {
		
		return kaizenBankDao.getSafetySuggestionSummaryGridDataExportExcel(commonFilter,tableModel,format);
		
	}


	@Override
	public String getEmailId(String empmKeyid) throws Exception {
		
		return kaizenBankDao.getEmailId(empmKeyid);
	}

	@Override
	public String getJhLeaderEmailId(String flid) throws Exception {
		
		return kaizenBankDao.getJhLeaderEmailId(flid) ;
	}
	@Override
	public List<String[]> FillkznData(String keyid) throws Exception {
		// TODO Auto-generated method stub
//		return kaizenBankDao.FillkznData(keyid);
		return kaizenBankServiceApi.selectKznData(keyid);
	}

	@Override
	public String getEmailIdOfSuggestedBy(String kaizenNo) throws Exception {
		
		return kaizenBankDao.getEmailIdOfSuggestedBy(kaizenNo);
	}
	@Override
	public KznTlKaizenbankmst updatekznsugg(KznTlKaizenbankmst newkznTlKaizenbankmst) throws Exception {
	   // TODO Auto-generated method stub
	   //return kaizenBankDao.updatekznsugg(newkznTlKaizenbankmst);
		return kaizenBankServiceApi.updateKaizenWorkflowStatus(newkznTlKaizenbankmst);
	}
	@Override
	public KznTlKaizenbankmst updatekznsuggstatus(KznTlKaizenbankmst newkznTlKaizenbankmst) throws Exception {
	   // TODO Auto-generated method stub
	   return kaizenBankDao.updatekznsuggstatus(newkznTlKaizenbankmst);
	}
	public String getSuggestedName(String kaizenNo) throws Exception{
		return kaizenBankDao.getSuggestedName(kaizenNo);
	}
	/*public List<String[]> getSuggImpdata(CommonFilter commonFilter)throws Exception {
		return this.kaizenBankDao.getSuggImpdata(commonFilter);
	}*/
	public List<String[]> FillThemeCategoryData(String keyid)throws Exception{
		  return kaizenBankDao.FillThemeCategoryData(keyid);
	}
	public List<KznTlKaizenbankmst> MultipleSuggestion(List<KznTlKaizenbankmst> newKznTlKaizenbankmst,String flid,String sectionId,AdmTlUsermst createdBy) throws Exception,ValidationExceptions,BusinessApplicationExceptions{	
		  String validationFor="create";     
		try{
      			List<KznTlKaizenbankmst> kaizenList=newKznTlKaizenbankmst;
      			for(KznTlKaizenbankmst bankId: kaizenList){
      				bankId.setKzbnFlid(flid);
      				String elementId=commonFilterDao.getElementID(flid);
      				bankId.setKzbnElementid(elementId);
      				bankId.setKzbnCreatedby(createdBy.getUsrm_ccno());
      			}
      			validation.validate(newKznTlKaizenbankmst,"KaizenBankValidation",validationFor);
      		}
		     catch(BusinessApplicationExceptions e){
					CommonMessage.debugMsg("e.getMessage() buss  "+e.getMessage());
					throw new BusinessApplicationExceptions(e.getMessage());
				}catch(ValidationExceptions e){
					CommonMessage.debugMsg("e.getMessage() vali "+e.getMessage());
					throw new ValidationExceptions(e.getMessage());
				}
		     catch(Exception e){
					CommonMessage.debugMsg("e.getMessage() excep "+e.getMessage());
					throw new Exception(e.getMessage());
				}
		    // return kaizenBankDao.MultipleSuggestion(SuggestionFillValues(newKznTlKaizenbankmst));
		     return  kaizenBankServiceApi.multipleSave(SuggestionFillValues(newKznTlKaizenbankmst));
	}
private List<KznTlKaizenbankmst> SuggestionFillValues(List<KznTlKaizenbankmst> newKznTlKaizenbankmst){
	
	List<KznTlKaizenbankmst> Kaizenbank=newKznTlKaizenbankmst;
	List<KznTlKaizenbankmst> SuggList=new ArrayList<KznTlKaizenbankmst>();
	int index=0;
	if(Kaizenbank!=null)
	for(KznTlKaizenbankmst SuggData:Kaizenbank){		
	  index++;
	  String dateTime=CommonFunctions.pg_dateTimeNow();
	  SuggData.setKzbnActive("Y");
	  SuggData.setKzbnCreatedon(dateTime);
	  SuggData.setKzbnModifiedon(dateTime);	
	  SuggData.setKzbnDate(CommonFunctions.pg_getDateTimeFromDate(SuggData.getKzbnDate()));
	  
	  if(SuggData.getKzbnFlid()==null)
		  SuggData.setKzbnFlid("{}");
	  if(SuggData.getKzbnElementid()==null)
		  SuggData.setKzbnElementid("{}");
	  if(SuggData.getKzbnBenefit()==null)
	     SuggData.setKzbnBenefit("{}");
	  if(SuggData.getKzbnAccrejremarks()==null)
		  SuggData.setKzbnAccrejremarks("{}");
	   if(SuggData.getKzbnCompletedby()==null)
		  SuggData.setKzbnCompletedby("{}");
	   if(SuggData.getKzbnCompremarks()==null)
		  SuggData.setKzbnCompremarks("{}");
	   if(SuggData.getKzbnEhsrelated()==null)
		   SuggData.setKzbnEhsrelated("N");
	   if(SuggData.getKzbnEhsstatus()==null)
		   SuggData.setKzbnEhsstatus("X");
	   if(SuggData.getKzbnRefdocno()==null)
		   SuggData.setKzbnRefdocno("-");
	   if(SuggData.getKzbnRefdoctype()==null)
		   SuggData.setKzbnRefdoctype("-");
	   if(SuggData.getKzbnPqcdsme()==null)
		   SuggData.setKzbnPqcdsme("{}");
	   if(SuggData.getKzbnSuggestedby()==null)
		   SuggData.setKzbnSuggestedby("{}");
	   if(SuggData.getKzbnCompremarks()==null)
		   SuggData.setKzbnCompremarks("{}");
	   if(SuggData.getKzbnImpremarks()==null)
		   SuggData.setKzbnImpremarks("{}");
	   if(SuggData.getKzbnImplementcost()==null)
		   SuggData.setKzbnImplementcost("0");
	   if(SuggData.getKzbnImplementedby()==null)
		   SuggData.setKzbnImplementedby("{}");
	   if(SuggData.getKzbnImplementedon()==null)
		   SuggData.setKzbnImplementedon(Constants.pgFutureNullDateTime);
	   if(SuggData.getKzbnVerifiedby()==null)
		   SuggData.setKzbnVerifiedby("{}");
	   if(SuggData.getKzbnVerifiedon()==null)
		   SuggData.setKzbnVerifiedon(Constants.pgFutureNullDateTime);
	   if(SuggData.getKzbnTargetdate()==null)
		   SuggData.setKzbnTargetdate(Constants.pgFutureNullDateTime);
       if(SuggData.getKzbnAcrejby()==null)
    	   SuggData.setKzbnAcrejby("{}");
        if(SuggData.getKzbnAcceptrejon()==null)
        	SuggData.setKzbnAcceptrejon(Constants.pgFutureNullDateTime);
        if(SuggData.getkzbnOthers()==null)
        	SuggData.setkzbnOthers("N");
        if(SuggData.getKzbnmocrequired()==null)
        	SuggData.setKzbnmocrequired("-");
        if(SuggData.getKzbnApprovalflag()==null)
         SuggData.getKzbnApprovalflag("-");
        if(SuggData.getKzbnKaizen()==null)
        	SuggData.setKzbnKaizen("{}");
        if(SuggData.getKzbnStatus()==null)
        	SuggData.setKzbnStatus("-");
        if(SuggData.getKzbnDate()==null)
        	SuggData.setKzbnDate(dateTime);
        if(SuggData.getKzbnResponsibility()==null)
        	SuggData.setKzbnResponsibility(SuggData.getKzbnSuggestedby());
        if(SuggData.getKzbnVerifyremarks()==null)
        	SuggData.setKzbnVerifyremarks("{}");
        if(SuggData.getKzbnCompletedon()==null)
        	SuggData.setKzbnCompletedon(Constants.pgFutureNullDateTime);
        
        if( SuggData.getKzbnNonJhEsp() == null )
        	SuggData.setKzbnNonJhEsp("N");
         
        SuggList.add(SuggData);
	}	
	return SuggList;
}
@Override
public List<String[]> getIndividualmaingrid(CommonFilter commonFilter)
		throws Exception {
	return kaizenBankDao.getIndividualmaingrid(commonFilter);
}
@Override
public Workbook getIndividualKaizenMainExcel(JSONObject colmodel, String format,
CommonFilter commonFilter) throws Exception {
return kaizenBankDao.getIndividualKaizenMainExcel(colmodel,format, commonFilter);
}

@Override
public List<String[]> MOCcheck(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	return kaizenBankDao.MOCcheck(commonFilter);
}
 }
