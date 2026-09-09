package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlEvaluationdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;

public interface KaizenEvaluationDao {
	
	public List<String[]> getKaizenName() throws Exception;
	public List<String[]> getKaizenData() throws Exception;
	public List<String[]> getKaizenData(CommonFilter commonFilter) throws Exception;
	public String getElementID(String kzbnFlid) throws Exception;
	public abstract KznTlEvaluationmst create(KznTlEvaluationmst kznTlEvaluationmst) throws Exception;
	public abstract KznTlEvaluationmst update(KznTlEvaluationmst kznTlEvaluationmst) throws Exception;
	public abstract  List<KznTlEvaluationmst> delete(List<KznTlEvaluationmst> newKznTlEvaluationmst) throws Exception;
	public KznTlEvaluationmst selectmst(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception;
	public List<KznTlEvaluationmst> create(
			List<KznTlEvaluationmst> kznTlEvmstList) throws Exception;
	public List<String[]> FillJhLeader(String flid)throws Exception;

}
