package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BestKaizenDao;
import com.akranta.tpm.dao.impl.BestKaizenDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.service.BestKaizenService;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
//import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class BestKaizenServiceImpl implements BestKaizenService {
	private BestKaizenDao bestKaizenDao;
	private Validations validation;
	BestKaizenServiceApi serviceApi;
	
	public BestKaizenServiceImpl(DBActionTemplate dbActionTemplate) {
		bestKaizenDao = new BestKaizenDaoImpl(dbActionTemplate); 
		validation = new Validations();
	}
	
	public void BestKaizenServiceImplJwt(String JwtToken){
    	try{
    		bestKaizenDao.BestKaizenDaoImplJwt(JwtToken);
    		serviceApi = new BestKaizenServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	@Override
	public List<String[]> getBestKaizen(CommonFilter commonFilter) throws Exception {
		return this.bestKaizenDao.getBestKaizen(commonFilter);
	}
	@Override
	public KznTlBestmst create(KznTlBestmst kznTlBestmst,
			KznTlBestmst existKznTlBestmst) throws Exception {
		String validationsFor="create";
		validation.validate(kznTlBestmst, "KznTlBestmstValid",validationsFor);
		fillValues(kznTlBestmst);
		fillValues(existKznTlBestmst);
		//return bestKaizenDao.create(kznTlBestmst,existKznTlBestmst);
		return serviceApi.saveBestKaizen(kznTlBestmst, existKznTlBestmst);
	}
	private KznTlBestmst fillValues(KznTlBestmst kznTlBestmst) {
		kznTlBestmst.setKzbmActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		String date = kznTlBestmst.getKzbmDate();
		kznTlBestmst.setKzbmDate(CommonFunctions.pg_getDateTimeFromDate(date));
		CommonMessage.debugMsg(kznTlBestmst.getKzbmKeyid()+"   Inside fill Values"+dateTime);	
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmFlid()) )
			kznTlBestmst.setKzbmFlid("{}");
	
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmEmployeeid()))
			kznTlBestmst.setKzbmEmployeeid("{}");
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmDate()) )
			kznTlBestmst.setKzbmDate(Constants.pgPassNullDate);
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmMonth()))
			kznTlBestmst.setKzbmMonth(Constants.passNullMonth);
		
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmTempfield1()) )
			kznTlBestmst.setKzbmTempfield1("-");
		
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmTempfield2()))
			kznTlBestmst.setKzbmTempfield2("-");
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmTempfield3()))
			kznTlBestmst.setKzbmTempfield3("-");
		
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmTempfield4()) )
			kznTlBestmst.setKzbmTempfield4("-");
		
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmTempfield5()))
			kznTlBestmst.setKzbmTempfield5("-");
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmActive()))
			kznTlBestmst.setKzbmActive("N");
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmCreatedby()))
			kznTlBestmst.setKzbmCreatedby("{}");
		
		if(!UIUtils.isValidKeyId( kznTlBestmst.getKzbmCreatedon()) )
			kznTlBestmst.setKzbmCreatedon(dateTime);
		
		if( !UIUtils.isValidKeyId(kznTlBestmst.getKzbmModifiedon()))
			kznTlBestmst.setKzbmModifiedon(dateTime);

			kznTlBestmst.setKznTlBestdtl(fillKznBestDtl(kznTlBestmst));
		return kznTlBestmst ;
	}
	private List<KznTlBestdtl> fillKznBestDtl(KznTlBestmst kznTlBestmst) {
		CommonMessage.debugMsg("Detail 1");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<KznTlBestdtl> kznTlBestdtl =kznTlBestmst.getKznTlBestdtl();
		List<KznTlBestdtl> existkznTlBestdtl = null;
		KznTlBestdtl existnewkznTlBestdtl = null;
		CommonMessage.debugMsg("Detail 2");
		/*if (existKznTlEvaluationmst != null) {
			existkznTlBestdtl = existKznTlEvaluationmst.getKznTlBestdtl();
			if (existkznTlBestdtl != null && existkznTlBestdtl.size() > 0)
				existnewkznTlBestdtl = existkznTlBestdtl.get(0);
		}*/

		List<KznTlBestdtl> newkznTlBestdtlList = new ArrayList<KznTlBestdtl>();
		for (KznTlBestdtl newkznTlBestdtl : kznTlBestdtl)
		{
			newkznTlBestdtl.setKzbdCreatedon(dateTime);
			newkznTlBestdtl.setKzbdModifiedon(dateTime);

			newkznTlBestdtl.setKzbdActive("Y");
			if (!UIUtils.isValidKeyId(kznTlBestmst.getKzbmCreatedby()))
				newkznTlBestdtl.setKzbdCreatedby("{}");
			else
				newkznTlBestdtl.setKzbdCreatedby(kznTlBestmst.getKzbmCreatedby());

			if (!UIUtils.isValidKeyId(kznTlBestmst.getKzbmKeyid()))
				newkznTlBestdtl.setKzbdKzbmKeyid("{}");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdKaizenid()))
				newkznTlBestdtl.setKzbdKaizenid("{}");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdTempfield2()))
				newkznTlBestdtl.setKzbdTempfield2("-");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdTempfield3()))
				newkznTlBestdtl.setKzbdTempfield3("-");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdTempfield4()))
				newkznTlBestdtl.setKzbdTempfield4("-");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdTempfield5()))
				newkznTlBestdtl.setKzbdTempfield5("-");
			if (!UIUtils.isValidKeyId(newkznTlBestdtl.getKzbdTempfield1()))
				newkznTlBestdtl.setKzbdTempfield1("-");

			newkznTlBestdtlList.add(newkznTlBestdtl);
		}
		return newkznTlBestdtlList;
	}
	@Override
	public KznTlBestmst update(KznTlBestmst kznTlBestmst,
			KznTlBestmst existKznTlBestmst) throws Exception {
		String validationsFor="update";
		validation.validate(kznTlBestmst, "KznTlBestmstValid",validationsFor);
		fillValues(kznTlBestmst);
		fillValues(existKznTlBestmst);
	//	return bestKaizenDao.update(kznTlBestmst,existKznTlBestmst);
		return serviceApi.saveBestKaizen(kznTlBestmst, existKznTlBestmst);
	}
	@Override
	public List<String []> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception 
	{
		String fromMonth = commonFilter.getFromMonth();
		String flid = commonFilter.getFlid();
		String kznBankType = commonFilter.getKznBankType();
		//return bestKaizenDao.selectData(commonFilter);
		return serviceApi.selectData(flid, fromMonth, kznBankType);
	}
	@Override
	public List<String[]> getBestKzngrdData(CommonFilter commonFilter) throws Exception {
		return this.bestKaizenDao.getBestKzngrdData(commonFilter);
	}
	@Override
	public KznTlBestmst selectmstData(String keyid) throws NoDataFoundException, SQLException, Exception {
		//return bestKaizenDao.selectmstData(keyid);
		return serviceApi.getById(keyid);
	}
	@Override
	public KznTlBestmst delete(KznTlBestmst kznTlBestmst) throws Exception {
		//return bestKaizenDao.delete(kznTlBestmst);
		return serviceApi.DeleteBestKaizen(kznTlBestmst);
	}
	@Override
	public Workbook getbestkaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return  bestKaizenDao.getbestkaizenExcel(colmodel,format,commonFilter);
	}
	@Override
	public Workbook getBestKaizenJhExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return  bestKaizenDao.getBestKaizenJhExcel(colmodel,format,commonFilter);
	}
}
