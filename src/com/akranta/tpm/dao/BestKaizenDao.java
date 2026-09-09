package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlBestmst;

public interface BestKaizenDao {

	public List<String[]> getBestKaizen(CommonFilter commonFilter) throws Exception;

	public KznTlBestmst create(KznTlBestmst kznTlBestmst,KznTlBestmst existKznTlBestmst) throws Exception;

	public KznTlBestmst update(KznTlBestmst kznTlBestmst,KznTlBestmst existKznTlBestmst) throws Exception;

	public List<String []> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;

	public List<String[]> getBestKzngrdData(CommonFilter commonFilter) throws Exception;

	public KznTlBestmst selectmstData(String keyid) throws NoDataFoundException, SQLException, Exception;

	public KznTlBestmst delete(KznTlBestmst kznTlBestmst) throws Exception;

	public Workbook getbestkaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;

	public Workbook getBestKaizenJhExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter)throws Exception;
	
	public void BestKaizenDaoImplJwt(String JwtToken);

}
