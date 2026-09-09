package com.akranta.tpm.service.impl;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlWhywhymstDao;
import com.akranta.tpm.dao.impl.BdmTlWhywhymstDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.WhyWhyAnalysisService;
//import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class WhyWhyAnalysisServiceImpl implements WhyWhyAnalysisService{

	
	private Validations validations ;
	private WhywhyServiceApi whywhyServiceApi;
	private BdmTlWhywhymstDao bdmTlWhywhymstDao;
	
	public WhyWhyAnalysisServiceImpl(DBActionTemplate dbActionTemplate)
	{
		bdmTlWhywhymstDao = new BdmTlWhywhymstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void WhywhyAnalysisServiceImplJwt(String JwtToken){
    	try{
    		bdmTlWhywhymstDao.BdmTlWhywhymstDaoImplJwt(JwtToken);
    		whywhyServiceApi = new WhywhyServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }

	@Override
	public BdmTlWhywhydtl create(BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BdmTlWhywhydtl update(BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BdmTlWhywhydtl delete(BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public BdmTlWhywhymst delete(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,ValidationExceptions, Exception
	{
		return bdmTlWhywhymstDao.delete(bdmTlWhywhymst);
	}
	public BdmTlWhywhydtl deleteYYDtl(String keyId)
	throws ValidationExceptions, Exception {
		//return bdmTlWhywhymstDao.deleteYYDtl(keyId);
		return whywhyServiceApi.deleteWhyWhyDetail(keyId);
		
	}
	public BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception {

		return bdmTlWhywhymstDao.getWWMS(wwwsKeyid);
	}
	public String checkCounterMsr() throws Exception
	{
		return bdmTlWhywhymstDao.checkCounterMsr();
	}
	public BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception {
		return bdmTlWhywhymstDao.getWWMSValues(refDocId);
	}
	@Override
	public List<Object> getWWDT(String wwwsKeyid) throws Exception {

		return bdmTlWhywhymstDao.getWWDT(wwwsKeyid);
	}
	
	public List<String[]> getAllwhywhyStd(CommonFilter commonFilter) throws Exception
	{
		
		return this.bdmTlWhywhymstDao.getWhywhyStd(commonFilter);
		
	}
	public List<String[]> getRootCause(String openMode)throws Exception
	{
		//return this.bdmTlWhywhymstDao.getRootCause(openMode);
		
		return this.whywhyServiceApi.getRootCause(openMode);
	}
	public List<String []> getProgramList(String start,String end) throws Exception{
		return this.bdmTlWhywhymstDao.getProgramList(start,end);
	}
	public List<String[]> getPillar(String yyId,String pillarFlag)throws Exception
	{
		return this.bdmTlWhywhymstDao.getPillar(yyId,pillarFlag);
	}
	public List<String[]> getSelectedRootCause(String yyNo)throws Exception
	{
		return this.bdmTlWhywhymstDao.getSelectedRootCause(yyNo);
	}
	public String getDocId(String bdId)throws Exception
	{
		return this.bdmTlWhywhymstDao.getDocId(bdId);
	}
	public String checkMstExist(String refDocId)throws Exception
	{
		return this.bdmTlWhywhymstDao.checkMstExist(refDocId);
	}
	@Override
	public BdmTlWhywhymst create(BdmTlWhywhymst newBdmTlWhywhymst,BdmTlWhywhymst existBdmTlWhywhymst,YYFormBean yyFormBean)throws BusinessApplicationExceptions,ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("service");
		//mano change 
		validations.validate( newBdmTlWhywhymst,"WhyVali","create");
		CommonMessage.debugMsg("validations====="+validations);
		fillYYValues(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);
		CommonMessage.debugMsg("serviceimpl");
		//return bdmTlWhywhymstDao.create(newBdmTlWhywhymst);
		return whywhyServiceApi.insertRecord(newBdmTlWhywhymst);
		
		
	//	return null;
	}
	public BdmTlWhywhymst update(BdmTlWhywhymst newBdmTlWhywhymst,BdmTlWhywhymst oldBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception
	{
		CommonMessage.debugMsg("service update impl");
		CommonMessage.debugMsg("MASTER DETAILS IN SERVICE IMPL "+newBdmTlWhywhymst);
		validations.validate( newBdmTlWhywhymst,"WhyVali","update");
		fillYYValues(newBdmTlWhywhymst,oldBdmTlWhywhymst,yyFormBean);
		CommonMessage.debugMsg("MASTER DETAILS IN SERVICE IMPL "+newBdmTlWhywhymst);
		CommonMessage.debugMsg("service update fill impl");
		//return bdmTlWhywhymstDao.update(newBdmTlWhywhymst);
		return whywhyServiceApi.insertRecord(newBdmTlWhywhymst);
		
	}
	
	private BdmTlWhywhymst fillYYValues(BdmTlWhywhymst newBdmTlWhywhymst,BdmTlWhywhymst oldBdmTlWhywhymst,YYFormBean yyFormBean) {
		
		CommonMessage.debugMsg("FillValues Master start");
		/*CommonMessage.debugMsg(" OLDKeyId  "+oldBdmTlWhywhymst.getWwmsKeyid());
		CommonMessage.debugMsg(" NewKeyId  "+newBdmTlWhywhymst.getWwmsKeyid());*/
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		String pillar = yyFormBean.getWwmsPillarmode();
		newBdmTlWhywhymst.setWwmsActive("Y");
		
		if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid()))
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);
		else
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);
		
		newBdmTlWhywhymst.setWwmsModifiedon(dateTime);
		String wwmsDate = newBdmTlWhywhymst.getWwmsDate();
		newBdmTlWhywhymst.setWwmsDate(CommonFunctions.pg_getDateTimeFromDate(wwmsDate));
		CommonMessage.debugMsg("Date : "+newBdmTlWhywhymst.getWwmsDate() );
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsDate()))
			newBdmTlWhywhymst.setWwmsDate(dateTime);
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsFactoryid()))
			newBdmTlWhywhymst.setWwmsFactoryid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProductid()))
			newBdmTlWhywhymst.setWwmsProductid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPillarid()))
			newBdmTlWhywhymst.setWwmsPillarid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSectionid()))
			newBdmTlWhywhymst.setWwmsSectionid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsLossid()))
			newBdmTlWhywhymst.setWwmsLossid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsCellid()))
			newBdmTlWhywhymst.setWwmsCellid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSubcellid()))
			newBdmTlWhywhymst.setWwmsSubcellid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsMachineid()))
			newBdmTlWhywhymst.setWwmsMachineid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsAssemblyid()))
			newBdmTlWhywhymst.setWwmsAssemblyid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTargetpillarid()))
			newBdmTlWhywhymst.setWwmsTargetpillarid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsArea()))
			newBdmTlWhywhymst.setWwmsArea("{}");
		
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblem()))
			newBdmTlWhywhymst.setWwmsProblem("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTimespent()))
			newBdmTlWhywhymst.setWwmsTimespent("0");
		String WwmsReportdatetime = newBdmTlWhywhymst.getWwmsReportdatetime();
		newBdmTlWhywhymst.setWwmsReportdatetime(CommonFunctions.pg_getDateTimeFromDate(WwmsReportdatetime));
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsReportdatetime()))
			newBdmTlWhywhymst.setWwmsReportdatetime(dateTime);
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblemattendby()))
			newBdmTlWhywhymst.setWwmsProblemattendby("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsWhywhydoneby()))
			newBdmTlWhywhymst.setWwmsWhywhydoneby("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOthercheckpoints()))
			newBdmTlWhywhymst.setWwmsOthercheckpoints("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRefdoctype()))
			newBdmTlWhywhymst.setWwmsRefdoctype("BDM");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsAppStatus())){
			if(Float.parseFloat(newBdmTlWhywhymst.getWwmsTimespent())>=0){ //mano insted odf 2 i give 1 
				newBdmTlWhywhymst.setWwmsAppStatus("P");
			}
			else{
				newBdmTlWhywhymst.setWwmsAppStatus("-");

			}
		}
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsApprvedOn())){
			newBdmTlWhywhymst.setWwmsApprvedOn(Constants.pgPassNullDateTime);
			
			
		}
		if(UIUtils.isValidKeyId(yyFormBean.getFormType()))
		{
			if(yyFormBean.getFormType().equals("BD"))
			{
				if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRefdocno()))
				{
					if(newBdmTlWhywhymst.getWwmsRefdocno().substring(0, 1).equals("U"))
						newBdmTlWhywhymst.setWwmsRefdoctype("UPM");
					else
						newBdmTlWhywhymst.setWwmsRefdoctype("BDM");
				}
			}	
			else if(yyFormBean.getFormType().equals("SHE"))
				newBdmTlWhywhymst.setWwmsRefdoctype("SFT");
			else if(yyFormBean.getFormType().equals("CC"))
				newBdmTlWhywhymst.setWwmsRefdoctype("CMC");
			else if(yyFormBean.getFormType().equals("DOCK"))
				newBdmTlWhywhymst.setWwmsRefdoctype("DOC");
			else if(yyFormBean.getFormType().equals("KAIZEN"))
				newBdmTlWhywhymst.setWwmsRefdoctype("KZN");
			else if(yyFormBean.getFormType().equals("ABN"))
				newBdmTlWhywhymst.setWwmsRefdoctype("ABN");
			else if(yyFormBean.getFormType().equals("GM"))
				newBdmTlWhywhymst.setWwmsRefdoctype("GNM");
			else if(yyFormBean.getFormType().equals("IMT"))
				newBdmTlWhywhymst.setWwmsRefdoctype("IMT");
			else if(yyFormBean.getFormType().equals("ACTION"))
				newBdmTlWhywhymst.setWwmsRefdoctype("APT");
			else
				newBdmTlWhywhymst.setWwmsRefdoctype("{}");
		}
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPhenomenaid()))
			newBdmTlWhywhymst.setWwmsPhenomenaid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRefdocno()))
			newBdmTlWhywhymst.setWwmsRefdocno("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsFinalaction()))
			newBdmTlWhywhymst.setWwmsFinalaction("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSparesreplaced()))
			newBdmTlWhywhymst.setWwmsSparesreplaced("N");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsChecksmade()))
			newBdmTlWhywhymst.setWwmsChecksmade("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSymptombefore()))
			newBdmTlWhywhymst.setWwmsSymptombefore("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsYoudidnot()))
			newBdmTlWhywhymst.setWwmsYoudidnot("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsCountermeasure()))
			newBdmTlWhywhymst.setWwmsCountermeasure("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsCountermeasureid()))
			newBdmTlWhywhymst.setWwmsCountermeasureid("{}");
			
		newBdmTlWhywhymst.setWwmsIsjh("X");
		newBdmTlWhywhymst.setWwmsIspm("X");
		newBdmTlWhywhymst.setWwmsIskk("X");
		newBdmTlWhywhymst.setWwmsIsopl("X");
		newBdmTlWhywhymst.setWwmsIspy("X");
		newBdmTlWhywhymst.setWwmsIsojt("X");
		newBdmTlWhywhymst.setWwmsIssop("X");
		
		if(UIUtils.isValidKeyId(pillar))
		{
			
			if(pillar.equals("AM") || pillar.equals("JH") )
				newBdmTlWhywhymst.setWwmsIsjh("Y");
		
			if(pillar.equals("PM"))
				newBdmTlWhywhymst.setWwmsIspm("Y");
			
			if(pillar.equals("CI"))
				newBdmTlWhywhymst.setWwmsIskk("Y");
			
			if(pillar.equals("ET"))
				newBdmTlWhywhymst.setWwmsIsopl("Y");
			
			if(pillar.equals("PY"))
				newBdmTlWhywhymst.setWwmsIspy("Y");
			
			if(pillar.equals("OJ"))
				newBdmTlWhywhymst.setWwmsIsojt("Y");
			
			if(pillar.equals("SO"))
				newBdmTlWhywhymst.setWwmsIssop("Y");
			if(pillar.equals("TRN")){
				newBdmTlWhywhymst.setWwmsIsjh("X");
				newBdmTlWhywhymst.setWwmsIspm("X");
				newBdmTlWhywhymst.setWwmsIskk("X");
				newBdmTlWhywhymst.setWwmsIsopl("X");
				newBdmTlWhywhymst.setWwmsIspy("X");
				newBdmTlWhywhymst.setWwmsIsojt("X");
				newBdmTlWhywhymst.setWwmsIssop("X");
			}
		}
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOjtdesc()))
			newBdmTlWhywhymst.setWwmsOjtdesc("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSopdesc()))
			newBdmTlWhywhymst.setWwmsSopdesc("{}");
		
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPreventivemeasure()))
			newBdmTlWhywhymst.setWwmsPreventivemeasure("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPreventivemeasureid()))
			newBdmTlWhywhymst.setWwmsPreventivemeasureid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsMaintinchargeid()))
			newBdmTlWhywhymst.setWwmsMaintinchargeid("{}");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsIshdpossible()))
			newBdmTlWhywhymst.setWwmsIshdpossible("N");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPrevdate()))
			newBdmTlWhywhymst.setWwmsPrevdate(Constants.pgPassNullDateTime);
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPreveffectiveness()))
			newBdmTlWhywhymst.setWwmsPreveffectiveness("{}");
		/*if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield1()))
			newBdmTlWhywhymst.setWwmsTempfield1("-");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield2()))
			newBdmTlWhywhymst.setWwmsTempfield2("-");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield3()))
			newBdmTlWhywhymst.setWwmsTempfield3("-");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield4()))
			newBdmTlWhywhymst.setWwmsTempfield4("-");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield5()))
			newBdmTlWhywhymst.setWwmsTempfield5("-");*/
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsIskzn()))
			newBdmTlWhywhymst.setWwmsIskzn("N");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsIspokayoke()))
			newBdmTlWhywhymst.setWwmsIspokayoke("N");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsFormtype()))
			newBdmTlWhywhymst.setWwmsFormtype("N");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPokayoke()))
			newBdmTlWhywhymst.setWwmsPokayoke("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsAccidentdesc()))
			newBdmTlWhywhymst.setWwmsAccidentdesc("{}");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsAccidentphen()))
			newBdmTlWhywhymst.setWwmsAccidentphen("{}");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPrevno()))
			newBdmTlWhywhymst.setWwmsPrevno("{}");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsPrevperson()))
			newBdmTlWhywhymst.setWwmsPrevperson("{}");
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsIseffective()))
			newBdmTlWhywhymst.setWwmsIseffective("N");
		

		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRootcause()))
			newBdmTlWhywhymst.setWwmsRootcause("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRootcauseid()))
			newBdmTlWhywhymst.setWwmsRootcauseid("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsSparesId()))
			newBdmTlWhywhymst.setWwmsSparesId("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTradeId()))
			newBdmTlWhywhymst.setWwmsTradeId("-");
		
		CommonMessage.debugMsg("newBdmTlWhywhymst.getWwmsStatus()..."+newBdmTlWhywhymst.getWwmsStatus());
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsStatus()))
			newBdmTlWhywhymst.setWwmsStatus("P");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsIscobd()))
			newBdmTlWhywhymst.setWwmsIscobd("N");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsCobdvalue()))
			newBdmTlWhywhymst.setWwmsCobdvalue("0");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsCobdhours()))
			newBdmTlWhywhymst.setWwmsCobdhours("0");
		
		if(UIUtils.isValidKeyId(yyFormBean.getFormType()) && yyFormBean.getFormType().equals("SHE"))
		{
			if(UIUtils.isValidKeyId(yyFormBean.getWwmsPrevdat()))
			{
				newBdmTlWhywhymst.setWwmsPrevdate(yyFormBean.getWwmsPrevdat());
			}
			
			if(UIUtils.isValidKeyId(yyFormBean.getWwmsPreveffective()))
			{
				newBdmTlWhywhymst.setWwmsPreveffectiveness(yyFormBean.getWwmsPreveffective());
			}
		}
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsFlid()))
			newBdmTlWhywhymst.setWwmsFlid("{}");
		
		CommonMessage.debugMsg("FillValues Master");
		newBdmTlWhywhymst.setBdmTlWhywhydtl(detailFillValues(newBdmTlWhywhymst,oldBdmTlWhywhymst,yyFormBean));
		return newBdmTlWhywhymst;
	
	}
	private List<BdmTlWhywhydtl> detailFillValues(BdmTlWhywhymst newBdmTlWhywhymst, BdmTlWhywhymst oldBdmTlWhywhymst,YYFormBean yyFormBean) {
		CommonMessage.debugMsg("DETAIL Fill Values");
		String dateTime = CommonFunctions.pg_dateTimeNow(); 
		//mano
		
		//
		List<BdmTlWhywhydtl> newBdmTlWhywhydtl = newBdmTlWhywhymst.getBdmTlWhywhydtl();
		List<BdmTlWhywhydtl> oldBdmTlWhywhydtl = null;
		BdmTlWhywhydtl oldBdmTlWhywhydtlValues  = null;
		if( oldBdmTlWhywhymst != null){		
			oldBdmTlWhywhydtl = oldBdmTlWhywhymst.getBdmTlWhywhydtl();
			if( oldBdmTlWhywhydtl != null && oldBdmTlWhywhydtl.size() > 0 )
			{
				oldBdmTlWhywhydtlValues = oldBdmTlWhywhydtl.get(0);
				
			}
		}
		//changed by mano 
		CommonMessage.debugMsg("DETAIL Fill Values");
		List<BdmTlWhywhydtl> newBdmTlWhywhydtlList = new ArrayList<BdmTlWhywhydtl>();
		for( BdmTlWhywhydtl bdmTlWhywhydtl :newBdmTlWhywhydtl)
		{	
			CommonMessage.debugMsg("DETAIL Fill for Values");
			bdmTlWhywhydtl.setWwdtCreatedby(newBdmTlWhywhymst.getWwmsCreatedby());
			bdmTlWhywhydtl.setWwdtCreatedon(dateTime);
			bdmTlWhywhydtl.setWwdtModifiedon(dateTime);
			if(!UIUtils.isValidKeyId(bdmTlWhywhydtl.getWwdtWhy()))
				bdmTlWhywhydtl.setWwdtWhy("{}");
			if(!UIUtils.isValidKeyId(bdmTlWhywhydtl.getWwdtAnswer()))
				bdmTlWhywhydtl.setWwdtAnswer("{}");
		
			if(!UIUtils.isValidKeyId(bdmTlWhywhydtl.getWwdtAction()))
				bdmTlWhywhydtl.setWwdtAction("{}");
			
			newBdmTlWhywhydtlList.add(bdmTlWhywhydtl);
			CommonMessage.debugMsg("DETAIL Complete Fill Values");
		}
		return newBdmTlWhywhydtlList;
	}
	@Override
	public BdmTlWhywhymst create1(BdmTlWhywhymst newBdmTlWhywhymst,	BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean)
			throws Exception {
		
		//validations.validate( newQtmTlWwmsst,"Qtmst","create");
		fillValues( newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);//
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.create1(newBdmTlWhywhymst) ;
		
		// TODO Auto-generated method stub
		
	}
		
	private BdmTlWhywhymst fillValues(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) {
		
		

		String dateTime = CommonFunctions.pg_dateTimeNow();
		// newQtmTlSopdtl.setStudemail("{}");
		newBdmTlWhywhymst.setWwmsActive("Y");
		if( newBdmTlWhywhymst.getWwmsKeyid() == null )			
		{	
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);			
		}					
		else
		{	
			//CommonMessage.debugMsg("Else");
			 //newQtmTlWwmsst.setWwmsCreatedon(existQtmTlWwmsst.getWwmsCreatedon());	
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);
		}
		newBdmTlWhywhymst.setWwmsModifiedon(dateTime);
		
		
		 if( newBdmTlWhywhymst.getWwmsKeyid() == null )
			 newBdmTlWhywhymst.setWwmsKeyid("{}");
		
		 
		
		if( newBdmTlWhywhymst.getWwmsAccidentdesc() == null )
			newBdmTlWhywhymst.setWwmsAccidentdesc("{}");
		
		
		if( newBdmTlWhywhymst.getWwmsAccidentphen() == null )
			newBdmTlWhywhymst.setWwmsAccidentphen("{}");
		
		if( newBdmTlWhywhymst.getWwmsAssemblyid() == null )
			newBdmTlWhywhymst.setWwmsAssemblyid("{}");
		
		
		if( newBdmTlWhywhymst.getWwmsCellid() == null )
			newBdmTlWhywhymst.setWwmsCellid("{}");
		
		if( newBdmTlWhywhymst.getWwmsChecksmade() == null )
			newBdmTlWhywhymst.setWwmsChecksmade("{}");
		 
		if( newBdmTlWhywhymst.getWwmsCountermeasure()== null )
			newBdmTlWhywhymst.setWwmsCountermeasure("{}");
		
		if( newBdmTlWhywhymst.getWwmsCountermeasureid() == null )
			newBdmTlWhywhymst.setWwmsCountermeasureid("{}");
		
		if( newBdmTlWhywhymst.getWwmsDate() == null )
			newBdmTlWhywhymst.setWwmsDate(dateTime);
		
		if( newBdmTlWhywhymst.getWwmsCreatedby() == null )
			newBdmTlWhywhymst.setWwmsCreatedby("{}");
		
		if( newBdmTlWhywhymst.getWwmsFactoryid() == null )
			newBdmTlWhywhymst.setWwmsFactoryid("{}");
		
		
		if( newBdmTlWhywhymst.getWwmsFinalaction() == null )
			newBdmTlWhywhymst.setWwmsFinalaction("{}");
		
		if( newBdmTlWhywhymst.getWwmsFlid() == null )
			newBdmTlWhywhymst.setWwmsFlid("{}");
		
		
		if( newBdmTlWhywhymst.getWwmsFormtype() == null )
			newBdmTlWhywhymst.setWwmsFlid("{}");
		
		if( newBdmTlWhywhymst.getWwmsIseffective() == null )
			newBdmTlWhywhymst.setWwmsIseffective("{}");
		 
		if( newBdmTlWhywhymst.getWwmsIshdpossible() == null )
			newBdmTlWhywhymst.setWwmsIshdpossible("{}");
		
		if( newBdmTlWhywhymst.getWwmsIsjh() == null )
			newBdmTlWhywhymst.setWwmsIsjh("{}");
		
		if( newBdmTlWhywhymst.getWwmsIskk() == null )
			newBdmTlWhywhymst.setWwmsIskk("{}");
		
		if( newBdmTlWhywhymst.getWwmsIskzn() == null )
			newBdmTlWhywhymst.setWwmsIskzn("{}");
		
		if( newBdmTlWhywhymst.getWwmsIsojt() == null )
			newBdmTlWhywhymst.setWwmsIsojt("{}");
		 
		if( newBdmTlWhywhymst.getWwmsIsopl()== null )
			newBdmTlWhywhymst.setWwmsIsopl("{}");
		
		if( newBdmTlWhywhymst.getWwmsIspm() == null )
			newBdmTlWhywhymst.setWwmsIspm("{}");
		
		if( newBdmTlWhywhymst.getWwmsIspokayoke() == null )
			newBdmTlWhywhymst.setWwmsIspokayoke("-");
		
		if( newBdmTlWhywhymst.getWwmsIspy() == null )
			newBdmTlWhywhymst.setWwmsIspy("{}");
		if( newBdmTlWhywhymst.getWwmsIssop() == null )
			newBdmTlWhywhymst.setWwmsIssop(dateTime);
		
		if( newBdmTlWhywhymst.getWwmsLossid() == null )
			newBdmTlWhywhymst.setWwmsLossid("-");
		 
		if( newBdmTlWhywhymst.getWwmsMachineid() == null )
			newBdmTlWhywhymst.setWwmsMachineid("-");
		
		if( newBdmTlWhywhymst.getWwmsMaintinchargeid() == null )
			newBdmTlWhywhymst.setWwmsMaintinchargeid("-");
		
	
		
		
	
		return newBdmTlWhywhymst;
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public BdmTlWhywhymst update1(BdmTlWhywhymst newBdmTlWhywhymst,
			BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception {
		//return bdmTlWhywhymstDao.selectmaskeyid(maskeyid);
		return whywhyServiceApi.getCompleteWhyWhyData(maskeyid);
		
	
	}
	@Override
	public Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		return bdmTlWhywhymstDao.whywhyExportExcel(commonFilter,tblJSONObj,format);
	
	}
	@Override
	public Workbook whywhyAgeExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		return bdmTlWhywhymstDao.whywhyAgeExportExcel(commonFilter,tblJSONObj,format);
	
	}
	@Override
	public List<String[]> getAllWhywhy(CommonFilter commonFilter)
			throws Exception {
		return bdmTlWhywhymstDao.getAllWhywhy(commonFilter);
		
	}
	@Override
	public List<String[]> getWhywhyAge(CommonFilter commonFilter)
			throws Exception {
		return bdmTlWhywhymstDao.getWhywhyAge(commonFilter);
				
	}
	@Override
	public BdmTlYyeffectivemst yyEffectivenessCreate(BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception {

		CommonMessage.debugMsg("service");
		//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
		CommonMessage.debugMsg("validations====="+validations);
		
		fillYYEffectiveValues(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
		
		CommonMessage.debugMsg("serviceimpl   "+newBdmTlYyeffectivemst.getYyefCreatedon());
	//return bdmTlWhywhymstDao.yyEffectivenessCreate(newBdmTlYyeffectivemst);
		return whywhyServiceApi.insertEffectiveness(newBdmTlYyeffectivemst);
	}
		@Override
	public BdmTlYyeffectivemst yyEffectivenessUpdate(BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception {

		CommonMessage.debugMsg("service");
		//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
		CommonMessage.debugMsg("validations====="+validations);
		fillYYEffectiveValues(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
		CommonMessage.debugMsg("serviceimpl");
		return bdmTlWhywhymstDao.yyEffectivenessUpdate(newBdmTlYyeffectivemst);
		//return whywhyServiceApi.updateEffectivenessRecord(newBdmTlYyeffectivemst);
	}
		
		public BdmTlYydonebymst yyDonebyCreate(BdmTlYydonebymst newBdmTlYydonebymst,
				BdmTlYydonebymst existBdmTlYydonebymst) throws Exception {

			CommonMessage.debugMsg("service");
			//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
			CommonMessage.debugMsg("validations====="+validations);
			fillYYDoneByValues(newBdmTlYydonebymst,existBdmTlYydonebymst);
			CommonMessage.debugMsg("serviceimpl   "+newBdmTlYydonebymst.getWwdbCreatedon());
			//return bdmTlWhywhymstDao.yyDonebyCreate(newBdmTlYydonebymst);
			return whywhyServiceApi.yyDonebyCreate(newBdmTlYydonebymst);

		}
		public BdmTlYyproblemattbymst yyProbAttCreate(BdmTlYyproblemattbymst newBdmTlYyproblemattbymst,
				BdmTlYyproblemattbymst existBdmTlYyproblemattbymst)throws Exception {
			CommonMessage.debugMsg("service");
			CommonMessage.debugMsg("validations====="+validations);
			fillYYProbAttValues(newBdmTlYyproblemattbymst,existBdmTlYyproblemattbymst);
			CommonMessage.debugMsg("serviceimpl   "+newBdmTlYyproblemattbymst.getWwpaCreatedon());
			//return bdmTlWhywhymstDao.yyProbattCreate(newBdmTlYyproblemattbymst);
			return whywhyServiceApi.yyProbAttCreate(newBdmTlYyproblemattbymst);
			
			
		}
		
		
		private BdmTlYyproblemattbymst fillYYProbAttValues(BdmTlYyproblemattbymst newBdmTlYyproblemattbymst,
				BdmTlYyproblemattbymst existBdmTlYyproblemattbymst) {
			// TODO Auto-generated method stub
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			newBdmTlYyproblemattbymst.setWwpaActive("Y");
			newBdmTlYyproblemattbymst.setWwpaCreatedon(dateTime);			
			newBdmTlYyproblemattbymst.setWwpaModifiedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newBdmTlYyproblemattbymst.getWwpaTempfield1()))
				newBdmTlYyproblemattbymst.setWwpaTempfield1("-");
			if(!UIUtils.isValidKeyId(newBdmTlYyproblemattbymst.getWwpaTempfield2()))
				newBdmTlYyproblemattbymst.setWwpaTempfield2("-");
			if(!UIUtils.isValidKeyId(newBdmTlYyproblemattbymst.getWwpaTempfield3()))
				newBdmTlYyproblemattbymst.setWwpaTempfield3("-");

			return newBdmTlYyproblemattbymst;
		
		}
		
		public String deleteYYProbAttBy(String keyId) throws Exception {
			// TODO Auto-generated method stub
			CommonMessage.debugMsg("KEYID SERVICE IMPL "+keyId);
			//return bdmTlWhywhymstDao.deleteYYProbAttBy(keyId);
			return whywhyServiceApi.deleteYYProbAttBy(keyId);
		}


		public String deleteYYDoneBy(String keyId)throws Exception {
			//return bdmTlWhywhymstDao.deleteYYDoneBy(keyId);
			return whywhyServiceApi.deleteYYDoneBy(keyId);
		}

		private BdmTlYyeffectivemst fillYYEffectiveValues(BdmTlYyeffectivemst newBdmTlYyeffectivemstt,BdmTlYyeffectivemst existBdmTlYyeffectivemst) {
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			newBdmTlYyeffectivemstt.getBdmTlYyeffectivemst();
			
			List<BdmTlYyeffectivemst> bdmTlYyeffectivemstt = newBdmTlYyeffectivemstt.getBdmTlYyeffectivemst();
			
			List<BdmTlYyeffectivemst> newBdmTlYyeffectivemstList = new ArrayList<BdmTlYyeffectivemst>();
			for( BdmTlYyeffectivemst newBdmTlYyeffectivemst :bdmTlYyeffectivemstt)
			{
				
			
			// newQtmTlSopdtl.setStudemail("{}");
			newBdmTlYyeffectivemst.setYyefActive("Y");
			if( newBdmTlYyeffectivemst.getYyefKeyid() == null )			
			{	
				newBdmTlYyeffectivemst.setYyefCreatedon(dateTime);			
			}					
			else
			{	
				//CommonMessage.debugMsg("Else");
				 //newQtmTlWwmsst.setWwmsCreatedon(existQtmTlWwmsst.getWwmsCreatedon());	
				newBdmTlYyeffectivemst.setYyefCreatedon(dateTime);
			}
			newBdmTlYyeffectivemst.setYyefModifiedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefEffectivedate()))
				newBdmTlYyeffectivemst.setYyefEffectivedate(Constants.pgPassNullDateTime);
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefFlid()))
				newBdmTlYyeffectivemst.setYyefFlid("{}");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefRefdocid()))
				newBdmTlYyeffectivemst.setYyefRefdocid("{}");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefRefdoctype()))
				newBdmTlYyeffectivemst.setYyefRefdoctype("{}");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield1()))
				newBdmTlYyeffectivemst.setYyefTempfield1("-");
			
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield2()))
				newBdmTlYyeffectivemst.setYyefTempfield2("-");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield3()))
				newBdmTlYyeffectivemst.setYyefTempfield3("-");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield4()))
				newBdmTlYyeffectivemst.setYyefTempfield4("-");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield5()))
				newBdmTlYyeffectivemst.setYyefTempfield5("-");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield6()))
				newBdmTlYyeffectivemst.setYyefTempfield6("-");
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefTempfield7()))
				newBdmTlYyeffectivemst.setYyefTempfield7("-");
			
			newBdmTlYyeffectivemstList.add(newBdmTlYyeffectivemst);
			}
			newBdmTlYyeffectivemstt.setBdmTlYyeffectivemst(newBdmTlYyeffectivemstList);
			newBdmTlYyeffectivemstt.setBdmTlYyeffectivedtl(detailFillValues(newBdmTlYyeffectivemstt,existBdmTlYyeffectivemst));
			return newBdmTlYyeffectivemstt;
		}
		
		private List<BdmTlYyeffectivedtl> detailFillValues(	BdmTlYyeffectivemst newBdmTlYyeffectivemst,	BdmTlYyeffectivemst existBdmTlYyeffectivemst) {

			CommonMessage.debugMsg("Detail 1");
			String dateTime = CommonFunctions.pg_dateTimeNow();
			List<BdmTlYyeffectivedtl> newBdmTlYyeffectivedtl = newBdmTlYyeffectivemst.getBdmTlYyeffectivedtl();
			List<BdmTlYyeffectivedtl> oldBdmTlYyeffectivedtls = null;
			BdmTlYyeffectivedtl oldBdmTlYyeffectivedtl  = null;
			if( existBdmTlYyeffectivemst != null){
				CommonMessage.debugMsg("Detail 2");
				oldBdmTlYyeffectivedtls = existBdmTlYyeffectivemst.getBdmTlYyeffectivedtl();
				if( oldBdmTlYyeffectivedtls != null && oldBdmTlYyeffectivedtls.size() > 0 )
					oldBdmTlYyeffectivedtl = oldBdmTlYyeffectivedtls.get(0);
			}	
			CommonMessage.debugMsg("Detail 3");
			List<BdmTlYyeffectivedtl> newBdmTlYyeffectivedtlList = new ArrayList<BdmTlYyeffectivedtl>();
			for( BdmTlYyeffectivedtl bdmTlYyeffectivedtl :newBdmTlYyeffectivedtl)
			{	
				if(bdmTlYyeffectivedtl.getYyedKeyid() == null )			
				{	
					CommonMessage.debugMsg("Detail");
					bdmTlYyeffectivedtl.setYyedCreatedon(dateTime);
				}	
				else{
					bdmTlYyeffectivedtl.setYyedCreatedon(dateTime);
				}
				
				bdmTlYyeffectivedtl.setYyedModifiedon(dateTime);
				bdmTlYyeffectivedtl.setYyedActive("Y");
				bdmTlYyeffectivedtl.setYyedCreatedby(newBdmTlYyeffectivemst.getYyefCreatedby());

				
				
				/*if(employeeBean.getEmpdBirthdate()==null)
					bdmTlYyeffectivedtl.setEmpdBirthdate(Constants.passNullDate);*/
				 String date = CommonFunctions.pg_getDateTimeFromDate(bdmTlYyeffectivedtl.getYyedCountermesdate());
						 bdmTlYyeffectivedtl.setYyedCountermesdate(date);
				
				
				
				if( bdmTlYyeffectivedtl.getYyedCountermesdate() == null )
					bdmTlYyeffectivedtl.setYyedCountermesdate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(Constants.pgPassNullDateTime));
				
				if( bdmTlYyeffectivedtl.getYyedCountermesid() == null )
					bdmTlYyeffectivedtl.setYyedCountermesid("{}");
				
				if( bdmTlYyeffectivedtl.getYyedCountermestype() == null )
					bdmTlYyeffectivedtl.setYyedCountermestype("{}");
				
				if( bdmTlYyeffectivedtl.getYyedEffectiveid() == null )
					bdmTlYyeffectivedtl.setYyedEffectiveid("{}");
				
				if( bdmTlYyeffectivedtl.getYyedEmpmKeyid() == null )
					bdmTlYyeffectivedtl.setYyedEmpmKeyid("{}");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield1() == null )
					bdmTlYyeffectivedtl.setYyedTempfield1("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield2() == null )
					bdmTlYyeffectivedtl.setYyedTempfield2("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield3() == null )
					bdmTlYyeffectivedtl.setYyedTempfield3("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield4() == null )
					bdmTlYyeffectivedtl.setYyedTempfield4("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield5() == null )
					bdmTlYyeffectivedtl.setYyedTempfield5("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield6() == null )
					bdmTlYyeffectivedtl.setYyedTempfield6("-");
				
				if( bdmTlYyeffectivedtl.getYyedTempfield7() == null )
					bdmTlYyeffectivedtl.setYyedTempfield7("-");
				
				
				newBdmTlYyeffectivedtlList.add(bdmTlYyeffectivedtl);
			}
			
			
			return newBdmTlYyeffectivedtlList;

			
		}
		
		public List<String[]> getWhyWhyGenDrillData(CommonFilter commonfilter) throws Exception{
			return bdmTlWhywhymstDao.getWhyWhyGenDrillData(commonfilter);
		}
		
		public Workbook getWhyWhyGenDrillDataExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String format) throws Exception{
			return bdmTlWhywhymstDao.getWhyWhyGenDrillDataExcel(commonFilter,tblJSONObj,format);
		}

		
		private BdmTlYydonebymst fillYYDoneByValues(BdmTlYydonebymst newBdmTlYydonebymst,BdmTlYydonebymst existBdmTlYydonebymst) {
			String dateTime = CommonFunctions.pg_dateTimeNow();
			
			newBdmTlYydonebymst.setWwdbActive("Y");
			newBdmTlYydonebymst.setWwdbCreatedon(dateTime);			
			newBdmTlYydonebymst.setWwdbModifiedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newBdmTlYydonebymst.getWwdbTempfield1()))
				newBdmTlYydonebymst.setWwdbTempfield1("-");
			if(!UIUtils.isValidKeyId(newBdmTlYydonebymst.getWwdbTempfield2()))
				newBdmTlYydonebymst.setWwdbTempfield2("-");
			if(!UIUtils.isValidKeyId(newBdmTlYydonebymst.getWwdbTempfield3()))
				newBdmTlYydonebymst.setWwdbTempfield3("-");

			return newBdmTlYydonebymst;
		}
		public List<String[]> getWhyWhyCountData(CommonFilter commonfilter)	throws Exception {
			// TODO Auto-generated method stub
			return bdmTlWhywhymstDao.getWhyWhyCountData(commonfilter);
			
		}
	public Workbook getWhyWhyCountExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String format) throws Exception {
			return bdmTlWhywhymstDao.getWhyWhyCountExcel(commonFilter,tblJSONObj,format);
		}
	@Override
	public List<String[]> getRootCauseList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getRootCauseList(commonFilter);
	}
	public Workbook getWhyWhyCountRootCauseExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception {
	    return bdmTlWhywhymstDao.getWhyWhyCountRootCauseExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public List<String[]> getCounterMeasureList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getCounterMeasureList(commonFilter);
	}
	public Workbook getWhyWhyCountCounterExcel(CommonFilter commonFilter,
	        JSONObject tblJSONObj, String format) throws Exception {
	    return bdmTlWhywhymstDao.getWhyWhyCountCounterExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public String getCCEmailId(String empmKeyId) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getCCEmailId(empmKeyId);
	}
	@Override
	public List<String[]> getEmailIds(String whywhyNo) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getEmailIds(whywhyNo);
	}
	public List<String[]> getPcEmailIds(String whywhyNo) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("getPcEmailIdsgetPcEmailIds "+whywhyNo);
		return bdmTlWhywhymstDao.getPcEmailIds(whywhyNo);
	}
	@Override
	public List<String[]> getWhyReleatedFileManager(String whywhyNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}		
	@Override
	public String getWhywhyTrade(String tradeId) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getWhywhyTrade(tradeId);
	}
	
	@Override
	public String getWhywhyPillar(String pillarId) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.getWhywhyPillar(pillarId);
	}

}
