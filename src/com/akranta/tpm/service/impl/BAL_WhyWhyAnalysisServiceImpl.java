package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.YYFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlWhywhymstDao;
import com.akranta.tpm.dao.impl.BAL_BdmTlWhywhymstDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.BAL_BdmTlWhywhydtl;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.BAL_BdmTlYydonebymst;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BAL_BdmTlYyeffectivemst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.service.BAL_WhyWhyAnalysisService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_WhyWhyAnalysisServiceImpl implements BAL_WhyWhyAnalysisService{

	
	private Validations validations ;
	private BAL_BdmTlWhywhymstDao bdmTlWhywhymstDao;
	
	public BAL_WhyWhyAnalysisServiceImpl(DBActionTemplate dbActionTemplate)
	{
		bdmTlWhywhymstDao = new BAL_BdmTlWhywhymstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public BAL_BdmTlWhywhydtl create(BAL_BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_BdmTlWhywhydtl update(BAL_BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BAL_BdmTlWhywhydtl delete(BAL_BdmTlWhywhydtl bdmTlWhywhydtl)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public BAL_BdmTlWhywhymst delete(BAL_BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions,ValidationExceptions, Exception
	{
		return bdmTlWhywhymstDao.delete(bdmTlWhywhymst);
	}
	public BAL_BdmTlWhywhydtl deleteYYDtl(String keyId)
	throws ValidationExceptions, Exception {
		return bdmTlWhywhymstDao.deleteYYDtl(keyId);
	}
	public BAL_BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception {

		return bdmTlWhywhymstDao.getWWMS(wwwsKeyid);
	}
	public String checkCounterMsr() throws Exception
	{
		return bdmTlWhywhymstDao.checkCounterMsr();
	}
	public BAL_BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception {
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
		return this.bdmTlWhywhymstDao.getRootCause(openMode);
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
	public BAL_BdmTlWhywhymst create(BAL_BdmTlWhywhymst newBdmTlWhywhymst,BAL_BdmTlWhywhymst existBdmTlWhywhymst,YYFormBean yyFormBean)throws BusinessApplicationExceptions,ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		validations.validate( newBdmTlWhywhymst,"WhyVali","create");
		fillYYValues(newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);
		return bdmTlWhywhymstDao.create(newBdmTlWhywhymst);
	//	return null;
	}
	public BAL_BdmTlWhywhymst update(BAL_BdmTlWhywhymst newBdmTlWhywhymst,BAL_BdmTlWhywhymst oldBdmTlWhywhymst,  YYFormBean yyFormBean ) throws BusinessApplicationExceptions,ValidationExceptions, Exception
	{
		validations.validate( newBdmTlWhywhymst,"WhyVali","update");
		fillYYValues(newBdmTlWhywhymst,oldBdmTlWhywhymst,yyFormBean);
		return bdmTlWhywhymstDao.update(newBdmTlWhywhymst);
		
	}
	
	private BAL_BdmTlWhywhymst fillYYValues(BAL_BdmTlWhywhymst newBdmTlWhywhymst,BAL_BdmTlWhywhymst oldBdmTlWhywhymst,YYFormBean yyFormBean) {
		
		CommonFunctions.debugMsg("FillValues Master start");
		/*CommonFunctions.debugMsg(" OLDKeyId  "+oldBdmTlWhywhymst.getWwmsKeyid());
		CommonFunctions.debugMsg(" NewKeyId  "+newBdmTlWhywhymst.getWwmsKeyid());*/
		String dateTime = CommonFunctions.dateTimeNow();
		String pillar = yyFormBean.getWwmsPillarmode();
		newBdmTlWhywhymst.setWwmsActive("Y");
		
		if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid()))
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);
		else
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);
		
		newBdmTlWhywhymst.setWwmsModifiedon(dateTime);
		
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
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsReportdatetime()))
			newBdmTlWhywhymst.setWwmsReportdatetime(dateTime);
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsProblemattendby()))
			newBdmTlWhywhymst.setWwmsProblemattendby("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsWhywhydoneby()))
			newBdmTlWhywhymst.setWwmsWhywhydoneby("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsOthercheckpoints()))
			newBdmTlWhywhymst.setWwmsOthercheckpoints("{}");
		
		
		CommonFunctions.debugMsg("getWwmsRefdoctype   "+newBdmTlWhywhymst.getWwmsRefdoctype());
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsRefdoctype()))
			newBdmTlWhywhymst.setWwmsRefdoctype("BDM");
		
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsImmediateaction()))
			newBdmTlWhywhymst.setWwmsImmediateaction("{}");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield1()))
			newBdmTlWhywhymst.setWwmsTempfield1("-");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield2()))
			newBdmTlWhywhymst.setWwmsTempfield2("-");
		
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsTempfield3()))
			newBdmTlWhywhymst.setWwmsTempfield3("-");
		
		CommonFunctions.debugMsg("yyFormBean.getFormType()   "+yyFormBean.getFormType());
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
			newBdmTlWhywhymst.setWwmsPrevdate(Constants.passNullDate);
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
		
		CommonFunctions.debugMsg("newBdmTlWhywhymst.getWwmsStatus()..."+newBdmTlWhywhymst.getWwmsStatus());
		if(!UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsStatus()))
			newBdmTlWhywhymst.setWwmsStatus("P");
		
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
		
		CommonFunctions.debugMsg("FillValues Master");
		newBdmTlWhywhymst.setBdmTlWhywhydtl(detailFillValues(newBdmTlWhywhymst,oldBdmTlWhywhymst,yyFormBean));
		return newBdmTlWhywhymst;
	
	}
	private List<BAL_BdmTlWhywhydtl> detailFillValues(BAL_BdmTlWhywhymst newBdmTlWhywhymst, BAL_BdmTlWhywhymst oldBdmTlWhywhymst,YYFormBean yyFormBean) {
		System.out.println("DETAIL Fill Values");
		String dateTime = CommonFunctions.dateTimeNow();
		List<BAL_BdmTlWhywhydtl> newBdmTlWhywhydtl = newBdmTlWhywhymst.getBdmTlWhywhydtl();
		List<BAL_BdmTlWhywhydtl> oldBdmTlWhywhydtl = null;
		BAL_BdmTlWhywhydtl oldBdmTlWhywhydtlValues  = null;
		if( oldBdmTlWhywhymst != null){		
			oldBdmTlWhywhydtl = oldBdmTlWhywhymst.getBdmTlWhywhydtl();
			if( oldBdmTlWhywhydtl != null && oldBdmTlWhywhydtl.size() > 0 )
			{
				oldBdmTlWhywhydtlValues = oldBdmTlWhywhydtl.get(0);
				
			}
		}
		
		System.out.println("DETAIL Fill Values");
		List<BAL_BdmTlWhywhydtl> newBdmTlWhywhydtlList = new ArrayList<BAL_BdmTlWhywhydtl>();
		for( BAL_BdmTlWhywhydtl bdmTlWhywhydtl :newBdmTlWhywhydtl)
		{	
			System.out.println("DETAIL Fill for Values");
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
			System.out.println("DETAIL Complete Fill Values");
		}
		return newBdmTlWhywhydtlList;
	}
	@Override
	public BAL_BdmTlWhywhymst create1(BAL_BdmTlWhywhymst newBdmTlWhywhymst,	BAL_BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean)
			throws Exception {
		
		//validations.validate( newQtmTlWwmsst,"Qtmst","create");
		fillValues( newBdmTlWhywhymst,existBdmTlWhywhymst,yyFormBean);//
		// TODO Auto-generated method stub
		return bdmTlWhywhymstDao.create1(newBdmTlWhywhymst) ;
		
		// TODO Auto-generated method stub
		
	}
		
	private BAL_BdmTlWhywhymst fillValues(BAL_BdmTlWhywhymst newBdmTlWhywhymst,
			BAL_BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean) {
		
		

		String dateTime = CommonFunctions.dateTimeNow();
		// newQtmTlSopdtl.setStudemail("{}");
		newBdmTlWhywhymst.setWwmsActive("Y");
		if( newBdmTlWhywhymst.getWwmsKeyid() == null )			
		{	
			newBdmTlWhywhymst.setWwmsCreatedon(dateTime);			
		}					
		else
		{	
			//CommonFunctions.debugMsg("Else");
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
	public BAL_BdmTlWhywhymst update1(BAL_BdmTlWhywhymst newBdmTlWhywhymst,
			BAL_BdmTlWhywhymst existBdmTlWhywhymst, YYFormBean yyFormBean)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BAL_BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception {
		return bdmTlWhywhymstDao.selectmaskeyid(maskeyid);
	
	}
	@Override
	public Workbook whywhyExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		
		return bdmTlWhywhymstDao.whywhyExportExcel(commonFilter,tblJSONObj,format);
	
	}
	@Override
	public List<String[]> getAllWhywhy(CommonFilter commonFilter)
			throws Exception {
		return bdmTlWhywhymstDao.getAllWhywhy(commonFilter);
		
	}
	@Override
	public BAL_BdmTlYyeffectivemst yyEffectivenessCreate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception {

		CommonFunctions.debugMsg("service");
		//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
		CommonFunctions.debugMsg("validations====="+validations);
		fillYYEffectiveValues(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
		CommonFunctions.debugMsg("serviceimpl   "+newBdmTlYyeffectivemst.getYyefCreatedon());
		return bdmTlWhywhymstDao.yyEffectivenessCreate(newBdmTlYyeffectivemst);
	}
		@Override
	public BAL_BdmTlYyeffectivemst yyEffectivenessUpdate(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst,
			BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) throws Exception {

		CommonFunctions.debugMsg("service");
		//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
		CommonFunctions.debugMsg("validations====="+validations);
		fillYYEffectiveValues(newBdmTlYyeffectivemst,existBdmTlYyeffectivemst);
		CommonFunctions.debugMsg("serviceimpl");
		return bdmTlWhywhymstDao.yyEffectivenessUpdate(newBdmTlYyeffectivemst);
	}
		
		public BAL_BdmTlYydonebymst yyDonebyCreate(BAL_BdmTlYydonebymst newBdmTlYydonebymst,
				BAL_BdmTlYydonebymst existBdmTlYydonebymst) throws Exception {

			CommonFunctions.debugMsg("service");
			//validations.validate( newBdmTlYyeffectivemst,"WhyVali","create");
			CommonFunctions.debugMsg("validations====="+validations);
			fillYYDoneByValues(newBdmTlYydonebymst,existBdmTlYydonebymst);
			CommonFunctions.debugMsg("serviceimpl   "+newBdmTlYydonebymst.getWwdbCreatedon());
			return bdmTlWhywhymstDao.yyDonebyCreate(newBdmTlYydonebymst);

		}
		
		public String deleteYYDoneBy(String keyId)throws Exception {
			return bdmTlWhywhymstDao.deleteYYDoneBy(keyId);
		}

		private BAL_BdmTlYyeffectivemst fillYYEffectiveValues(BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemstt,BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) {
			String dateTime = CommonFunctions.dateTimeNow();
			
			newBdmTlYyeffectivemstt.getBdmTlYyeffectivemst();
			
			List<BAL_BdmTlYyeffectivemst> bdmTlYyeffectivemstt = newBdmTlYyeffectivemstt.getBdmTlYyeffectivemst();
			
			List<BAL_BdmTlYyeffectivemst> newBdmTlYyeffectivemstList = new ArrayList<BAL_BdmTlYyeffectivemst>();
			for( BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst :bdmTlYyeffectivemstt)
			{
				
			
			// newQtmTlSopdtl.setStudemail("{}");
			newBdmTlYyeffectivemst.setYyefActive("Y");
			if( newBdmTlYyeffectivemst.getYyefKeyid() == null )			
			{	
				newBdmTlYyeffectivemst.setYyefCreatedon(dateTime);			
			}					
			else
			{	
				//CommonFunctions.debugMsg("Else");
				 //newQtmTlWwmsst.setWwmsCreatedon(existQtmTlWwmsst.getWwmsCreatedon());	
				newBdmTlYyeffectivemst.setYyefCreatedon(dateTime);
			}
			newBdmTlYyeffectivemst.setYyefModifiedon(dateTime);
			
			if(!UIUtils.isValidKeyId(newBdmTlYyeffectivemst.getYyefEffectivedate()))
				newBdmTlYyeffectivemst.setYyefEffectivedate(Constants.passNullDate);
			
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
		private List<BAL_BdmTlYyeffectivedtl> detailFillValues(	BAL_BdmTlYyeffectivemst newBdmTlYyeffectivemst,	BAL_BdmTlYyeffectivemst existBdmTlYyeffectivemst) {

			System.out.println("Detail 1");
			String dateTime = CommonFunctions.dateTimeNow();
			List<BAL_BdmTlYyeffectivedtl> newBdmTlYyeffectivedtl = newBdmTlYyeffectivemst.getBdmTlYyeffectivedtl();
			List<BAL_BdmTlYyeffectivedtl> oldBdmTlYyeffectivedtls = null;
			BAL_BdmTlYyeffectivedtl oldBdmTlYyeffectivedtl  = null;
			if( existBdmTlYyeffectivemst != null){
				System.out.println("Detail 2");
				oldBdmTlYyeffectivedtls = existBdmTlYyeffectivemst.getBdmTlYyeffectivedtl();
				if( oldBdmTlYyeffectivedtls != null && oldBdmTlYyeffectivedtls.size() > 0 )
					oldBdmTlYyeffectivedtl = oldBdmTlYyeffectivedtls.get(0);
			}	
			System.out.println("Detail 3");
			List<BAL_BdmTlYyeffectivedtl> newBdmTlYyeffectivedtlList = new ArrayList<BAL_BdmTlYyeffectivedtl>();
			for( BAL_BdmTlYyeffectivedtl bdmTlYyeffectivedtl :newBdmTlYyeffectivedtl)
			{	
				if(bdmTlYyeffectivedtl.getYyedKeyid() == null )			
				{	
					System.out.println("Detail");
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
				
				if( bdmTlYyeffectivedtl.getYyedCountermesdate() == null )
					bdmTlYyeffectivedtl.setYyedCountermesdate(Constants.passNullDate);
				
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

		
		private BAL_BdmTlYydonebymst fillYYDoneByValues(BAL_BdmTlYydonebymst newBdmTlYydonebymst,BAL_BdmTlYydonebymst existBdmTlYydonebymst) {
			String dateTime = CommonFunctions.dateTimeNow();
			
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
		@Override
		public String getYYKeyId(String refDocId) throws Exception {
			// TODO Auto-generated method stub
			return bdmTlWhywhymstDao.getYYKeyId(refDocId);
		}
		

}
