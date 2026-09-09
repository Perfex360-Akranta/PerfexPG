package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlBestmst;

public interface BestKaizenService {

	public List<String[]> getBestKaizen(CommonFilter commonFilter) throws Exception;

	public KznTlBestmst create(KznTlBestmst kznTlBestmst,
			KznTlBestmst existKznTlBestmst) throws  Exception;

	public KznTlBestmst update(KznTlBestmst kznTlBestmst,
			KznTlBestmst existKznTlBestmst) throws  Exception;

	public List<String []> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;

	public List<String[]> getBestKzngrdData(CommonFilter commonFilter) throws Exception;

	public KznTlBestmst selectmstData(String keyid) throws NoDataFoundException, SQLException, Exception;

	public KznTlBestmst delete(KznTlBestmst kznTlBestmst) throws Exception;

	public Workbook getbestkaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;

	public Workbook getBestKaizenJhExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
	
	public void BestKaizenServiceImplJwt(String JwtToken);

}
