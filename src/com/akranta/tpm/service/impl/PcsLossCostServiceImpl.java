package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlLctcostperminuteDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.PcsTlLctcostperminuteDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlLctcostperminute;
import com.akranta.tpm.service.PcsLossCostService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.PrjConstants;
import com.akranta.tpm.utils.Validations;

public class PcsLossCostServiceImpl implements PcsLossCostService {
	private PcsTlLctcostperminuteDao pcsTlLctcostperminuteDao; 
	private Validations validations ;
	
	public PcsLossCostServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlLctcostperminuteDao = new PcsTlLctcostperminuteDaoImpl(dbActionTemplate);
		new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	
	@Override
	public List<String[]> getPcsLossCost(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("poladasd");
		return pcsTlLctcostperminuteDao.getPcsLossCost(commonFilter);
	}

	@Override
	public Workbook getPcsLossCostExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return pcsTlLctcostperminuteDao.getPcsLossCostExportExcel(commonFilter,tableModel,format);
	}


	@Override
	public void create(
			List<PcsTlLctcostperminute> lossCstList, String usrm_ccno)
			throws Exception ,ValidationExceptions,BusinessApplicationExceptions {
		try{
			lossCstFillValues(lossCstList,usrm_ccno);
			String validationsFor = "create";
			for(int i=0;i<lossCstList.size();i++)
			{
				CommonMessage.debugMsg(lossCstList.get(i).getCkPcsLossCost()+"  lossCstList.get(i).getckPcsLossCost():"+lossCstList.get(i).getCkPcsLossCost());
				//if(lossCstList.get(i).getCkPcsLossCost().equals("N"))
					validations.validate( lossCstList.get(i),"PCSLossCst",validationsFor);
			}
			pcsTlLctcostperminuteDao.create(lossCstList);
		 }
		 catch (ValidationExceptions e){
				CommonMessage.debugMsg("validation exception occured");
				throw new ValidationExceptions(e.getMessage());
		}	
		 catch(BusinessApplicationExceptions b)
		 {
			 throw new BusinessApplicationExceptions(b.getMessage());
		 }
		
	}
	private List<PcsTlLctcostperminute> lossCstFillValues(
			List<PcsTlLctcostperminute> lossCstList, String usrm_ccno) {
		String dateTime = CommonFunctions.dateTimeNow();
		List<PcsTlLctcostperminute> newPcsTlLctcostperminutelinks = lossCstList;
		List<PcsTlLctcostperminute> newPcsTlLctcostperminuteList = new ArrayList<PcsTlLctcostperminute>();
		int index=0;
		for( PcsTlLctcostperminute pcsTlLctcostperminute : newPcsTlLctcostperminutelinks)
		{	
			/*if(pcsTlLctcostperminute.getLcpmActive()==null)
			pcsTlLctcostperminute.setLcpmActive("Y");
			PcsTlLctcostperminute pcsTlLctcostperminute1 = lossCstList.get(index);
			CommonMessage.debugMsg("pcsTlLctcostperminute1:"+pcsTlLctcostperminute1.getLcpmFromdate()+":"+pcsTlLctcostperminute1.getLcpmCostperminute()+":"+pcsTlLctcostperminute1.getLcpmElementid());
			pcsTlLctcostperminute.setLcpmCostperminute(pcsTlLctcostperminute1.getLcpmCostperminute());
			pcsTlLctcostperminute.setLcpmElementid(pcsTlLctcostperminute1.getLcpmElementid());
			pcsTlLctcostperminute.setLcpmFromdate(pcsTlLctcostperminute1.getLcpmFromdate());
			CommonMessage.debugMsg("pcsTlLctcostperminute1:"+pcsTlLctcostperminute1.getLcpmFromdate());
			pcsTlLctcostperminute.setLcpmCreatedby(usrm_ccno);
			pcsTlLctcostperminute.setLcpmCreatedon(dateTime);*/
			pcsTlLctcostperminute.setLcpmCreatedby(usrm_ccno);
			pcsTlLctcostperminute.setLcpmCreatedon(dateTime);
			if(!UIUtils.isValidKeyId(pcsTlLctcostperminute.getLcpmActive()))
				pcsTlLctcostperminute.setLcpmActive("Y");
			if(!UIUtils.isValidKeyId(pcsTlLctcostperminute.getLcpmCostperminute()))
				pcsTlLctcostperminute.setLcpmCostperminute("0");
			if(!UIUtils.isValidKeyId(pcsTlLctcostperminute.getLcpmElementid()))
				pcsTlLctcostperminute.setLcpmElementid("{}");
			if(!UIUtils.isValidKeyId(pcsTlLctcostperminute.getLcpmElementtype()))
				pcsTlLctcostperminute.setLcpmElementtype("{}");
			if(!UIUtils.isValidKeyId(pcsTlLctcostperminute.getLcpmFromdate()))
				pcsTlLctcostperminute.setLcpmFromdate(Constants.passNullDate);
			/*String deptType="";
			if(pcsTlLctcostperminute1.getLcpmElementid().substring(0, 3).equals(PrjConstants.IDENT_LOCATION))
				deptType=PrjConstants.IDENT_LOCATION;
			else if(pcsTlLctcostperminute1.getLcpmElementid().substring(0, 3).equals(PrjConstants.IDENT_FACTORY))
				deptType=PrjConstants.IDENT_FACTORY;
			else if(pcsTlLctcostperminute1.getLcpmElementid().substring(0,3).equals( PrjConstants.IDENT_SECTION))
				deptType=PrjConstants.IDENT_SECTION;
			else if(pcsTlLctcostperminute1.getLcpmElementid().substring(0, 3).equals(PrjConstants.IDENT_CELL))
				deptType=PrjConstants.IDENT_CELL;
			else if(pcsTlLctcostperminute1.getLcpmElementid().substring(0, 3).equals(PrjConstants.IDENT_MACHINE))
				deptType=PrjConstants.IDENT_MACHINE;*/
			if(pcsTlLctcostperminute.getLcpmModifiedon()==null)
			pcsTlLctcostperminute.setLcpmModifiedon(dateTime);
			if(pcsTlLctcostperminute.getLcpmTempfield1()==null)
			pcsTlLctcostperminute.setLcpmTempfield1("-");
			if(pcsTlLctcostperminute.getLcpmTempfield2()==null)
			pcsTlLctcostperminute.setLcpmTempfield2("-");
			if(pcsTlLctcostperminute.getLcpmTempfield3()==null)
			pcsTlLctcostperminute.setLcpmTempfield3("-");
			if(pcsTlLctcostperminute.getLcpmTempfield4()==null)
			pcsTlLctcostperminute.setLcpmTempfield4("-");
			if(pcsTlLctcostperminute.getLcpmTempfield5()==null)
			pcsTlLctcostperminute.setLcpmTempfield5("-");
			pcsTlLctcostperminute.setLcpmLatestflag("Y");
			pcsTlLctcostperminute.setLcpmTodate(Constants.futureNullDate);
			index++;
			newPcsTlLctcostperminuteList.add(pcsTlLctcostperminute);
		}
		
		return newPcsTlLctcostperminuteList;
		
	}


	@Override
	public PcsTlLctcostperminute create(PcsTlLctcostperminute newPcsTlLctcostperminute,PcsTlLctcostperminute existPcsTlLctcostperminute) throws Exception {
		
		
		
		CommonMessage.debugMsg("service");
		//validations.validate( newPcsTlLctcostperminute,"WhyVali","create");
		CommonMessage.debugMsg("validations====="+validations);
		//lossCstFillValues(newPcsTlLctcostperminute,existPcsTlLctcostperminute);
		fillvalues(newPcsTlLctcostperminute,existPcsTlLctcostperminute);
		CommonMessage.debugMsg("serviceimpl");
		return null;// pcsTlLctcostperminuteDao.create(newPcsTlLctcostperminute);
		
	}





	private PcsTlLctcostperminute fillvalues(PcsTlLctcostperminute newPcsTlLctcostperminute,PcsTlLctcostperminute existPcsTlLctcostperminute)throws Exception  {
		
		
		String dateTime = CommonFunctions.dateTimeNow();
		newPcsTlLctcostperminute.setLcpmActive("Y");
		if( newPcsTlLctcostperminute.getLcpmKeyid() == null )			
		{	
			newPcsTlLctcostperminute.setLcpmCreatedon(dateTime);			
		}					
		else
		{	
			newPcsTlLctcostperminute.setLcpmCreatedon(dateTime);
		}
		newPcsTlLctcostperminute.setLcpmModifiedon(dateTime);
		
		
		
		
		if( newPcsTlLctcostperminute.getLcpmElementid() == null )
			newPcsTlLctcostperminute.setLcpmElementid("{}");
		
		if( newPcsTlLctcostperminute.getLcpmElementtype()== null )
			newPcsTlLctcostperminute.setLcpmElementtype("-");
		
		if( newPcsTlLctcostperminute.getLcpmFromdate() == null )
			newPcsTlLctcostperminute.setLcpmFromdate(dateTime);
		 
		if( newPcsTlLctcostperminute.getLcpmTodate() == null )
			newPcsTlLctcostperminute.setLcpmTodate(Constants.futureNullDate);
		
		if( newPcsTlLctcostperminute.getLcpmCreatedby() == null )
			newPcsTlLctcostperminute.setLcpmCreatedby("{}");
		
	
	  if( newPcsTlLctcostperminute.getLcpmCostperminute() == null )
			newPcsTlLctcostperminute.setLcpmCostperminute("1");
		
		if( newPcsTlLctcostperminute.getLcpmLatestflag() == null )
			newPcsTlLctcostperminute.setLcpmLatestflag("-");
		
		if( newPcsTlLctcostperminute.getLcpmTempfield1() == null )
			newPcsTlLctcostperminute.setLcpmTempfield1("{}");
	
		
		if( newPcsTlLctcostperminute.getLcpmTempfield2() == null )
			newPcsTlLctcostperminute.setLcpmTempfield2("{}");
		
		if( newPcsTlLctcostperminute.getLcpmTempfield3() == null )
			newPcsTlLctcostperminute.setLcpmTempfield3("{}");
		
		
		if( newPcsTlLctcostperminute.getLcpmTempfield4() == null )
			newPcsTlLctcostperminute.setLcpmTempfield4("{}");
		
		if( newPcsTlLctcostperminute.getLcpmTempfield5() == null )
			newPcsTlLctcostperminute.setLcpmTempfield5("{}");
		
		return newPcsTlLctcostperminute;
		
	}


	@Override
	public PcsTlLctcostperminute update(
			PcsTlLctcostperminute newPcsTlLctcostperminute,
			PcsTlLctcostperminute existPcsTlLctcostperminute) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String[]> getPcsLoss(CommonFilter commonFilter)
			throws Exception {
		return null;//pcsTlLctcostperminuteDao.getPcsLoss(commonFilter);
	}

}
