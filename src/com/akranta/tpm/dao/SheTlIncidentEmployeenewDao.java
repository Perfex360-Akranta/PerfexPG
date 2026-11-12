package com.akranta.tpm.dao;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;

public interface SheTlIncidentEmployeenewDao {

	//public abstract SheTlIncidentEmployeenew create(SheTlIncidentEmployeenew sheTlIncidentEmployeenew) throws Exception;
	//public abstract SheTlIncidentEmployeenew update(SheTlIncidentEmployeenew sheTlIncidentEmployeenew) throws Exception;
	
	public abstract SheTlIncidentEmployeenew create(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws BusinessApplicationExceptions,Exception;
	public abstract SheTlIncidentEmployeenew update(SheTlIncidentEmployeenew sheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean) throws Exception;
    public abstract SheTlIncidentEmployeenew delete(SheTlIncidentEmployeenew sheTlIncidentEmployeenew) throws Exception;
   // public abstract void SheTlIncidentEmployeenewDaoImplJwt(String jwtToken);

}

