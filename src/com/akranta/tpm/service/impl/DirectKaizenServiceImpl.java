package com.akranta.tpm.service.impl;

import java.sql.SQLException;

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
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.DirectKaizenService;
import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
public class DirectKaizenServiceImpl implements DirectKaizenService{
	 private KaizenBankDao kaizenBankDao;
	 private CommonFilterDao commonFilterDao;
	 private Validations validation;
	 private KaizenBankServiceAPI kaizenBankServiceApi;
	 
	 public DirectKaizenServiceImpl(DBActionTemplate dbActionTemplate) {
		 kaizenBankDao=new KaizenBankDaoImpl(dbActionTemplate);
		 commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		 validation = new Validations();
	 }
	 
	 public void DirectKaizenServiceImplJwt(String JwtToken){
	    	try{
	    		//kaizenBankDao.KaizenBankDaoImplJwt(JwtToken);
	    		kaizenBankServiceApi = new KaizenBankServiceAPI(JwtToken);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    }
		@Override
		public KznTlKaizenbankmst selectMasterKeyid(String keyid) throws NoDataFoundException, SQLException, Exception {
			return kaizenBankDao.selectMasterKeyid(keyid);
		}
		    //*****DirectKaizen***********//
		@Override
		public KznTlKaizenbankmst createDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions,BusinessApplicationExceptions {
			try{
				String validateFor="create";
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
			//return kaizenBankDao.createDKaizen(newKznTlKaizenbankmst,type,dataKeyidArr,dataFlidArr,dataSuggestArr);	
			return kaizenBankServiceApi.saveKznTlKaizenBankMst(newKznTlKaizenbankmst);
		}
		
		@Override
		public KznTlKaizenbankmst updateDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions,BusinessApplicationExceptions  {
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
			return kaizenBankDao.updateDKaizen(newKznTlKaizenbankmst ,type,dataKeyidArr,dataFlidArr,dataSuggestArr);	
		}		
		
		private KznTlKaizenbankmst fillValues(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String AccSingle) throws Exception
		{
			CommonMessage.debugMsg("FillValues");
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
				newKznTlKaizenbankmst.setKzbnStatus("V");
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
}
