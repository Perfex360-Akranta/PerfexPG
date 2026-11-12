package com.akranta.tpm.dao;

import java.util.List;

import com.akranta.tpm.model.SheTlActionplan;

public interface SheTlActionplanDao {

	public abstract SheTlActionplan create(SheTlActionplan sheTlActionplan) throws Exception;
	public abstract SheTlActionplan update(SheTlActionplan sheTlActionplan) throws Exception;
	public abstract SheTlActionplan delete(SheTlActionplan sheTlActionplan) throws Exception;
	public abstract List<String[]> getIncidentActionPlan(SheTlActionplan sheTlActionplan)throws Exception;
	public abstract SheTlActionplan deleteActionPlan(SheTlActionplan sheTlActionplan)throws Exception;

}

