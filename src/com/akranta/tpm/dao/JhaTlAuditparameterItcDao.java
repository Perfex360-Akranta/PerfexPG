package com.akranta.tpm.dao;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.JhaTlAuditparameter;

public interface JhaTlAuditparameterItcDao {

	public abstract JhaTlAuditparameter create(JhaTlAuditparameter jhaTlAuditparameter) throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public abstract JhaTlAuditparameter update(JhaTlAuditparameter jhaTlAuditparameter) throws Exception;
	public abstract JhaTlAuditparameter delete(JhaTlAuditparameter jhaTlAuditparameter) throws Exception;
	public void  deleteParameter(String parameterId) throws BusinessApplicationExceptions,Exception;

}

