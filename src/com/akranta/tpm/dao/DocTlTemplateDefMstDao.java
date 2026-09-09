package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DocTlTemplateDefDtl;
import com.akranta.tpm.model.DocTlTemplateDefMst;

public interface DocTlTemplateDefMstDao {

	public abstract DocTlTemplateDefMst create(DocTlTemplateDefMst newDocTlTemplateDefMst,DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception;
	public abstract DocTlTemplateDefMst update(DocTlTemplateDefMst docTlTemplateDefMst, DocTlTemplateDefMst existDocTlTemplateDefMst) throws Exception;
	public abstract DocTlTemplateDefMst delete(DocTlTemplateDefMst docTlTemplateDefMst) throws Exception;
	public abstract List<String[]> getdocTempList(CommonFilter commonFilter,String type) throws BusinessApplicationExceptions, Exception;
	public abstract DocTlTemplateDefMst deleteDocTempData(DocTlTemplateDefMst newDocTlTemplateDefMst, DocTlTemplateDefDtl newDocTlTemplateDefdtl) throws Exception;
	public abstract DocTlTemplateDefDtl deleteDocTemp(DocTlTemplateDefDtl doctempdtl) throws BusinessApplicationExceptions, Exception;
	public abstract List<String[]> getdocumentTempList(CommonFilter commonFilter,String keyId);
	public abstract int selectCount(CommonFilter commonFilter) throws Exception;
	public abstract DocTlTemplateDefMst getdocTempGridList(String keyId) throws NoDataFoundException, SQLException, Exception;
	public abstract List<String[]> getDocData(String documentNo, String documentType) throws Exception;

}

