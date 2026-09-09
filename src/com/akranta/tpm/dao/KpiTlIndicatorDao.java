package com.akranta.tpm.dao;

import com.akranta.tpm.model.KpiTlIndicator;

public interface KpiTlIndicatorDao {

	public abstract KpiTlIndicator create(KpiTlIndicator kpiTlIndicator) throws Exception;
	public abstract KpiTlIndicator update(KpiTlIndicator kpiTlIndicator) throws Exception;
	public abstract KpiTlIndicator delete(KpiTlIndicator kpiTlIndicator) throws Exception;
	public abstract String getSortNo(KpiTlIndicator kpiTlIndicatorKk)throws Exception;
	public abstract String getLocation(String flId)throws Exception;
	public abstract void KpiTlIndicatorDaoImplJwt(String jwtToken);

}

