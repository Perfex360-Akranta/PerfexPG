package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KznTlEvaluationmstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KaizenEvaluationDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.KaizenEvaluationDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.service.KaizenEvaluationService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KaizenEvaluationServiceImpl implements KaizenEvaluationService {
	private KaizenEvaluationDao kaizenNameDao;
	private CommonFilterDao commonFilterdao;
	private Validations validation;
	
	public KaizenEvaluationServiceImpl(DBActionTemplate actionTemplate) {
		commonFilterdao = new CommonFilterDaoImpl(actionTemplate);
		kaizenNameDao = new KaizenEvaluationDaoImpl(actionTemplate);
		validation = new Validations();
			
		
	}
	

	@Override
	public List<String[]> getKaizenName() throws Exception {
		return this.kaizenNameDao.getKaizenName();
	}


	@Override
	public List<String[]> getKaizenData() throws Exception {
		// TODO Auto-generated method stub
		return this.kaizenNameDao.getKaizenData();
	}
	@Override
	public List<String[]> getKaizenData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.kaizenNameDao.getKaizenData(commonFilter);
	}


	@Override
	public KznTlEvaluationmst update(KznTlEvaluationmst newKznTlEvaluationmst,
			KznTlEvaluationmst existKznTlEvaluationmst,
			KznTlEvaluationmstBean newKznTlEvaluationmstBean) throws Exception {
		// TODO Auto-generated method stub
		String validationsFor = "update";
		CommonMessage.debugMsg("Validations impl 1");
		validation.validate(newKznTlEvaluationmst, "KevaEvaluationValidation",validationsFor);
	//fillValues(newKznTlEvaluationmst,existKznTlEvaluationmst);
	return kaizenNameDao.create(newKznTlEvaluationmst);
	}


	@Override
	public KznTlEvaluationmst create(KznTlEvaluationmst newKznTlEvaluationmst,
			KznTlEvaluationmst existKznTlEvaluationmst,
			KznTlEvaluationmstBean newKznTlEvaluationmstBean) throws ValidationExceptions,Exception{
		String validationsFor = "create";
		CommonMessage.debugMsg("Validations impl 1");
		//validation.validate(newKznTlEvaluationmst, "KevaEvaluationValidation",validationsFor);
	//fillValues(newKznTlEvaluationmst,existKznTlEvaluationmst);
	return kaizenNameDao.create(newKznTlEvaluationmst);
	}


	@Override
	public  List<KznTlEvaluationmst> delete(List<KznTlEvaluationmst> newKznTlEvaluationmst) throws Exception {
		return kaizenNameDao.delete(newKznTlEvaluationmst);
	}
	//private KznTlEvaluationmst fillValues(KznTlEvaluationmst newKznTlEvaluationmst,KznTlEvaluationmst existKznTlEvaluationmst) throws Exception
	private List<KznTlEvaluationmst> fillValues(List<KznTlEvaluationmst> kznTlEvmstList) 
	{
		
		List<KznTlEvaluationmst> kznTlEvaluationmstList = new ArrayList<KznTlEvaluationmst>();
		for (KznTlEvaluationmst newKznTlEvaluationmst : kznTlEvmstList){
		CommonMessage.debugMsg("Inside fill Values");
		newKznTlEvaluationmst.setKevaActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Inside fill Values"+dateTime);	
				
		
		if( newKznTlEvaluationmst.getKevaFlid() == null )
			newKznTlEvaluationmst.setKevaFlid("{}");
	
		if( newKznTlEvaluationmst.getKevaEmployeeid() == null )
			newKznTlEvaluationmst.setKevaEmployeeid("{}");
		
		if( newKznTlEvaluationmst.getKevaDate() == null )
			newKznTlEvaluationmst.setKevaDate(Constants.passNullDate);
		
		if( newKznTlEvaluationmst.getKevaTempfield1() == null )
			newKznTlEvaluationmst.setKevaTempfield1("-");
		
		if( newKznTlEvaluationmst.getKevaTempfield2() == null )
			newKznTlEvaluationmst.setKevaTempfield2("-");
		
		if( newKznTlEvaluationmst.getKevaTempfield3() == null )
			newKznTlEvaluationmst.setKevaTempfield3("-");
		
		if( newKznTlEvaluationmst.getKevaTempfield4() == null )
			newKznTlEvaluationmst.setKevaTempfield4("-");
		
		if( newKznTlEvaluationmst.getKevaTempfield5() == null )
			newKznTlEvaluationmst.setKevaTempfield5("-");
		

		if( newKznTlEvaluationmst.getKevaTempfield6() == null )
			newKznTlEvaluationmst.setKevaTempfield6("-");
		
		if( newKznTlEvaluationmst.getKevaActive() == null )
			newKznTlEvaluationmst.setKevaActive("N");
		
		if( newKznTlEvaluationmst.getKevaCreatedby() == null )
			newKznTlEvaluationmst.setKevaCreatedby("{}");
		
		if( newKznTlEvaluationmst.getKevaCreatedon() == null )
			newKznTlEvaluationmst.setKevaCreatedon(dateTime);
		
		if( newKznTlEvaluationmst.getKevaModifiedon() == null )
			newKznTlEvaluationmst.setKevaModifiedon(dateTime);

		if( !UIUtils.isValidKeyId(newKznTlEvaluationmst.getKevaKaizenid()) )
			newKznTlEvaluationmst.setKevaKaizenid("{}");
			newKznTlEvaluationmst.setKznTlEvaluationdtl(fillEznEvalDtl(newKznTlEvaluationmst));
			
			kznTlEvaluationmstList.add(newKznTlEvaluationmst);
		}
		return kznTlEvaluationmstList; 
		
	}

	private List<KznTlEvaluationdtl> fillEznEvalDtl(KznTlEvaluationmst newKznTlEvaluationmst) {
		CommonMessage.debugMsg("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
		List<KznTlEvaluationdtl> kznTlEvadtl =newKznTlEvaluationmst.getKznTlEvaluationdtl();
		List<KznTlEvaluationdtl> existkznTlEvadtl = null;
		KznTlEvaluationdtl existkznTlEvaluationdtl = null;
		CommonMessage.debugMsg("Detail 2");
		/*if (existKznTlEvaluationmst != null) {
			existkznTlEvadtl = existKznTlEvaluationmst.getKznTlEvaluationdtl();
			if (existkznTlEvadtl != null && existkznTlEvadtl.size() > 0)
				existkznTlEvaluationdtl = existkznTlEvadtl.get(0);
		}*/

		List<KznTlEvaluationdtl> kznTlEvaluationdtlList = new ArrayList<KznTlEvaluationdtl>();
		for (KznTlEvaluationdtl kznTlEvaluationdtl : kznTlEvadtl)
		{
			kznTlEvaluationdtl.setKedlCreatedon(dateTime);
			kznTlEvaluationdtl.setKedlModifiedon(dateTime);

			kznTlEvaluationdtl.setKedlActive("Y");
			if (!UIUtils.isValidKeyId(newKznTlEvaluationmst.getKevaCreatedby()))
				kznTlEvaluationdtl.setKedlCreatedby("{}");
			else
				kznTlEvaluationdtl.setKedlCreatedby(newKznTlEvaluationmst.getKevaCreatedby());

			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlKevaKeyid()))
				kznTlEvaluationdtl.setKedlKevaKeyid("{}");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlKzncretriaid()))
				kznTlEvaluationdtl.setKedlKzncretriaid("{}");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlKzncriteriaval()))
				kznTlEvaluationdtl.setKedlKzncriteriaval("0");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlKznmKeyid()))
				kznTlEvaluationdtl.setKedlKznmKeyid("{}");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlTempfield2()))
				kznTlEvaluationdtl.setKedlTempfield2("-");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlTempfield3()))
				kznTlEvaluationdtl.setKedlTempfield3("-");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlTempfield4()))
				kznTlEvaluationdtl.setKedlTempfield4("-");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlTempfield5()))
				kznTlEvaluationdtl.setKedlTempfield5("-");
			if (!UIUtils.isValidKeyId(kznTlEvaluationdtl.getKedlTempfield6()))
				kznTlEvaluationdtl.setKedlTempfield6("-");

			kznTlEvaluationdtlList.add(kznTlEvaluationdtl);
		}
		CommonMessage.debugMsg("End Of  fillValues kznTlEvaluationdtl ");

		return kznTlEvaluationdtlList;
	}
	@Override
	public KznTlEvaluationmst selectmst(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception {
		return kaizenNameDao.selectmst(commonFilter);
	}


	@Override
	public List<KznTlEvaluationmst> create(
			List<KznTlEvaluationmst> kznTlEvmstList) throws Exception {
		fillValues(kznTlEvmstList);
		return kaizenNameDao.create(kznTlEvmstList);
	}

	@Override
	public List<String[]> FillJhLeader(String flid) throws Exception {
		// TODO Auto-generated method stub
		return kaizenNameDao.FillJhLeader(flid);
	}
	
	/*public List<KznTlEvaluationmst> create(
			List<KznTlEvaluationmst> kznTlEvmstList,
			List<KznTlEvaluationmst> existKznTlEvaluationmst,
			List<KznTlEvaluationdtl> kznTlEvdtlList) {
		fillValues(kznTlEvmstList,existKznTlEvaluationmst);
		fillEznEvalDtl(kznTlEvdtlList);
		return kaizenNameDao.create(kznTlEvmstList,kznTlEvdtlList);
	}

	@Override
	public List<KznTlEvaluationmst> update(
			List<KznTlEvaluationmst> kznTlEvmstList,
			List<KznTlEvaluationmst> existKznTlEvaluationmst,
			List<KznTlEvaluationdtl> kznTlEvdtlList) {
		fillValues(kznTlEvmstList,existKznTlEvaluationmst);
		fillEznEvalDtl(kznTlEvdtlList);
		return kaizenNameDao.update(kznTlEvmstList,kznTlEvdtlList);
	}*/
}
