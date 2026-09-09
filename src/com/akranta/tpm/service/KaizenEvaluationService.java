package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KznTlEvaluationmstBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;

public interface KaizenEvaluationService {
	
	public List<String[]> getKaizenName() throws Exception ;
	public List<String[]> getKaizenData() throws Exception ;
	public List<String[]> getKaizenData(CommonFilter commonFilter) throws Exception ;
	public KznTlEvaluationmst update(KznTlEvaluationmst newKznTlEvaluationmst,KznTlEvaluationmst existKznTlEvaluationmst,KznTlEvaluationmstBean newKznTlEvaluationmstBean) throws Exception;
	public KznTlEvaluationmst create(KznTlEvaluationmst newKznTlEvaluationmst,KznTlEvaluationmst existKznTlEvaluationmst,KznTlEvaluationmstBean newKznTlEvaluationmstBean) throws ValidationExceptions, Exception;
	public  List<KznTlEvaluationmst> delete(List<KznTlEvaluationmst> newKznTlEvaluationmst) throws Exception;
	public KznTlEvaluationmst selectmst(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;
	//public List<KznTlEvaluationmst> create(List<KznTlEvaluationmst> kznTlEvmstList,List<KznTlEvaluationmst> existKznTlEvaluationmst,List<KznTlEvaluationdtl> kznTlEvdtlList);
	//public List<KznTlEvaluationmst> update(List<KznTlEvaluationmst> kznTlEvmstList,List<KznTlEvaluationmst> existKznTlEvaluationmst,List<KznTlEvaluationdtl> kznTlEvdtlList);
	public List<KznTlEvaluationmst> create(
			List<KznTlEvaluationmst> kznTlEvmstList) throws Exception;
	public List<String[]> FillJhLeader(String flid)throws Exception;

}
