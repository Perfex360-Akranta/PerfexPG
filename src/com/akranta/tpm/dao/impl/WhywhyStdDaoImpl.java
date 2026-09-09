package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;


import com.akranta.tpm.dao.WhywhyDao;
import com.akranta.tpm.model.CommonFilter;
public class WhywhyStdDaoImpl implements WhywhyDao {
	
	
	
	
	private DBActionTemplate dbActionTemplate; 
	
	public WhywhyStdDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getWhywhyStd(CommonFilter commonFilter) throws Exception
	{
		try
		{

			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add(commonFilter.getKey()!=null?commonFilter.getKey():"{}");
			
			paramValues.add(commonFilter.getFinalTrade()!= null ? commonFilter.getFinalTrade().getId():"{}");
			
			paramValues.add(commonFilter.getFactory()!= null ? commonFilter.getFactory().getId():"{}");		
			
			paramValues.add(commonFilter.getCell()!= null ? commonFilter.getCell().getId():"{}");
			
			paramValues.add(commonFilter.getCostCenter()!= null ? commonFilter.getCostCenter().getId():"{}");
			
			paramValues.add(commonFilter.getJhStep()!= null ? commonFilter.getJhStep().getId():"{}");
			
			paramValues.add(commonFilter.getMachineRank()!= null ? commonFilter.getMachineRank().getId():"{}");
			
			paramValues.add(commonFilter.getMachine() != null ? commonFilter.getMachine().getId():"{}");
			
			paramValues.add(commonFilter.getCircle()!= null ? commonFilter.getCircle().getId():"{}");
			
			paramValues.add(commonFilter.getAssembly()!= null ? commonFilter.getAssembly().getId():"{}");
			
			paramValues.add(commonFilter.getSection()!= null ? commonFilter.getSection().getId():"{}");	
			
			paramValues.add(commonFilter.getPhenomena()!= null ? commonFilter.getPhenomena().getId():"{}");	
			
			paramValues.add(commonFilter.getFinalCause()!= null ? commonFilter.getFinalCause().getId():"{}");	
			
			paramValues.add(commonFilter.getMaintainChargeId()!= null ? commonFilter.getMaintainChargeId().getId():"{}");
			
			paramValues.add(commonFilter.getEqpGroup()!= null ? commonFilter.getEqpGroup().getId():"{}");
			
			paramValues.add(commonFilter.getFromDate());
			
			paramValues.add(commonFilter.getToDate());
			
		
			return dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORT", paramValues);
			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
}
