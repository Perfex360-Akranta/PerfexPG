package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.SheTlIncidentEmployeetBean;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.SheTlIncidentEmployee;

public interface SheTlIncidentEmployeeDao {

	public abstract SheTlIncidentEmployee create(SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws BusinessApplicationExceptions,Exception;
	public abstract SheTlIncidentEmployee update(SheTlIncidentEmployee sheTlIncidentEmployee,SheTlIncidentEmployeetBean sheTlIncidentEmployeetBean) throws Exception;
	public abstract SheTlIncidentEmployee delete(SheTlIncidentEmployee sheTlIncidentEmployee) throws Exception;
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployee sheTlIncidentEmployee)throws Exception;

}

