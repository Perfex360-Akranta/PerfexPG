package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;

public interface KaizenBankDao {

	public List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception;
	public List<String[]> getfillgriddataIndividual(CommonFilter commonFilter)throws Exception;
	public KznTlKaizenbankmst create(KznTlKaizenbankmst newKznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception;
	public KznTlKaizenbankmst update(KznTlKaizenbankmst newKznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception;
	public KznTlKaizenbankmst delete(KznTlKaizenbankmst kznTlKaizenbankmst)throws Exception;
	public KznTlKaizenbankmst getRecall(String keyId) throws Exception;
	public Workbook getKaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	public String getElementID(String kzbnFlid) throws Exception;
	public KznTlKaizenbankmst selectmst(String keyid) throws NoDataFoundException, SQLException, Exception;
	public List<String[]> getmaingrid(CommonFilter commonFilter) throws Exception;
	public Workbook getKaizenMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public List<String[]> getSafetySuggGrid(CommonFilter commonFilter) throws Exception;
	public Workbook getSafetyExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public List<KznTlKaizenbankmst> updateSugg(List<KznTlKaizenbankmst> kznTlEvmstList) throws BusinessApplicationExceptions, Exception;
	public KznTlKaizenbankmst updateVerifyDetails(List<KznTlKaizenbankmst> kaizenVerifyList )throws ValidationExceptions, Exception;
	public List<String[]> getSafetySuggestionSummaryGridData(CommonFilter commonFilter) throws Exception;
	public Workbook getSafetySuggestionSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String rptFormat) throws Exception;

	public String getEmailId(String empmKeyid) throws Exception ;
	public String getJhLeaderEmailId(String empmKeyid) throws Exception ;
	public String getEmailIdOfSuggestedBy(String kaizenNo) throws Exception;
	public KznTlKaizenbankmst updatekznsugg(KznTlKaizenbankmst newkznTlKaizenbankmst)throws Exception;
	public KznTlKaizenbankmst updatekznsuggstatus(KznTlKaizenbankmst newkznTlKaizenbankmst)throws Exception;
	public List<String[]> FillkznData(String keyid)throws Exception;
	public String getSuggestedName(String kaizenNo) throws Exception;
	//public List<String[]> getSuggImpdata(CommonFilter commonFilter)throws Exception;
	
	//******************DirectKaizen*******************************//
	public KznTlKaizenbankmst selectMasterKeyid(String keyid) throws NoDataFoundException, SQLException, Exception;
	public KznTlKaizenbankmst createDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception;
	public KznTlKaizenbankmst updateDKaizen(KznTlKaizenbankmst newKznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception;
	public List<String[]> FillThemeCategoryData(String keyid)throws Exception;
	public List<KznTlKaizenbankmst> MultipleSuggestion(List<KznTlKaizenbankmst> newKznTlKaizenbankmst) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<String[]> getIndividualmaingrid(CommonFilter commonFilter) throws Exception;
	public Workbook getIndividualKaizenMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public List<String[]> MOCcheck(CommonFilter commonFilter) throws Exception;
	public abstract void KaizenBankDaoImplJwt(String jwtToken);
}
