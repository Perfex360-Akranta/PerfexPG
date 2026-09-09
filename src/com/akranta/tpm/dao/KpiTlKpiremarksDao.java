package com.akranta.tpm.dao;


import java.util.List;

import javax.xml.bind.ValidationException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.KpiTlKpiremarks;

public interface KpiTlKpiremarksDao {

	public abstract KpiTlKpiremarks create(KpiTlKpiremarks kpiTlKpiremarks) throws Exception;
	public List<KpiTlKpiremarks> create(List<KpiTlKpiremarks> kpiTlKpiremarks)
	throws Exception,ValidationException,BusinessApplicationExceptions ;
	public abstract KpiTlKpiremarks update(KpiTlKpiremarks kpiTlKpiremarks) throws Exception;
	public abstract KpiTlKpiremarks delete(KpiTlKpiremarks kpiTlKpiremarks) throws Exception;

	public KpiTlKpiremarks select(KpiTlKpiremarks newKpiTlKpiremarks)throws Exception ;
	public abstract void KpiTlKpiremarksDaoImplJwt(String jwtToken);
}

