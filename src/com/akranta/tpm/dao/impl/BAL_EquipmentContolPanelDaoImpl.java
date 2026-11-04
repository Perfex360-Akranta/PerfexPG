package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_EquipmentControlPanelDao;

import com.akranta.tpm.model.CommonFilter;
public class BAL_EquipmentContolPanelDaoImpl implements BAL_EquipmentControlPanelDao {
	
		
	private DBActionTemplate dbActionTemplate; 
	
	public BAL_EquipmentContolPanelDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getEquipmentControlPanelDao(CommonFilter commonFilter) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add( commonFilter.getMachine() != null ? "%"+commonFilter.getMachine().getCode() + "%":"%%");
			
			
			System.out.println("Machinesss:"+commonFilter.getMachine());
		System.out.println("ParmValues:"+paramValues);
		
			return dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_EQPCONTROLPANEL", paramValues);
			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
}
