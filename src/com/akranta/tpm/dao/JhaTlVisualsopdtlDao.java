package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;

public interface JhaTlVisualsopdtlDao {

	public abstract JhaTlVisualsopdtl create(JhaTlVisualsopdtl jhaTlVisualsopdtl) throws Exception,BusinessApplicationExceptions;
	public abstract JhaTlVisualsopdtl update(JhaTlVisualsopdtl jhaTlVisualsopdtl) throws Exception;
	public abstract JhaTlVisualsopdtl delete(JhaTlVisualsopdtl jhaTlVisualsopdtl) throws Exception;
	public JhaTlVisualsopdtl getAllFillControlDtl(String keyId)throws Exception ;
	public GenTlAllmoduleimgfile deleteForImg(String keyId)throws Exception ;
	public Map<Integer, List<String[]>> vsopExcelReport(String vsopid,String format, CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getVisualid(String vsopid)throws Exception;
	public abstract JhaTlVisualsopmst updateApprovedStatusLevel(String status,
			String keyid, String nextLevel)throws Exception;
	public abstract List<String[]> getVsopApprovalList(String keyid)throws Exception;
}

