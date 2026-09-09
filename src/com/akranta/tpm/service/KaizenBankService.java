package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;

public interface KaizenBankService {

	public List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception;
	public List<String[]> getfillgriddataIndividual(CommonFilter commonFilter)throws Exception;
	public KznTlKaizenbankmst create(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions;
	public KznTlKaizenbankmst update(KznTlKaizenbankmst newKznTlKaizenbankmst,KznTlKaizenbankmst existKznTlKaizenbankmst,String type,String AccSingle,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) throws Exception,ValidationExceptions,BusinessApplicationExceptions;
	public KznTlKaizenbankmst delete(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception;
	public KznTlKaizenbankmst getRecall(String keyId) throws Exception;
	public Workbook getKaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
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
	public Workbook getSafetySuggestionSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;

	public String getEmailIdOfSuggestedBy(String kaizenNo) throws Exception;
	public String getEmailId(String empmKeyid) throws Exception;
	public String getJhLeaderEmailId(String flid) throws Exception;
	public KznTlKaizenbankmst updatekznsugg(KznTlKaizenbankmst newkznTlKaizenbankmst)throws Exception;
	public KznTlKaizenbankmst updatekznsuggstatus(KznTlKaizenbankmst newkznTlKaizenbankmst)throws Exception;
	public List<String[]> FillkznData(String keyid)throws Exception;
	public String getSuggestedName(String kaizenNo) throws Exception;
	//public List<String[]> getSuggImpdata(CommonFilter commonFilter)throws Exception;
	public List<String[]> FillThemeCategoryData(String keyid)throws Exception;
	public List<KznTlKaizenbankmst> MultipleSuggestion(List<KznTlKaizenbankmst> newKznTlKaizenbankmst,String flid,String sectionId,AdmTlUsermst createdBy) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<String[]> getIndividualmaingrid(CommonFilter commonFilter) throws Exception;
	public Workbook getIndividualKaizenMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	public List<String[]> MOCcheck(CommonFilter commonFilter) throws Exception;
	
	public void KaizenBankServiceImplJwt(String JwtToken);
}
