package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_BreakdownDao;
import com.akranta.tpm.dao.sql.BAL_BreakDownSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;


public class BAL_BreakdownDaoImpl implements BAL_BreakdownDao {
	
	
	private DBActionTemplate dbActionTemplate;
	
	public BAL_BreakdownDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	private List<ComboBox> getComboValues(String sql, Object[]condArgs  ) throws Exception
	{
		ResultSet rs = dbActionTemplate.getData(sql, condArgs) ;
		
		System.out.println("sql " + sql);
		List<ComboBox> companyList = new ArrayList<ComboBox>();
		while(rs.next())
		{	
			ComboBox compComb = new ComboBox();
			compComb.setId(rs.getString("id"));
			compComb.setText(rs.getString("text"));
			
			companyList.add(compComb);
		}
		return companyList;

	}
	
	public List<String []> getBreakdown(CommonFilter commonFilter) throws Exception
	{
		try
		{
			System.out.println("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			
			String sql = BAL_BreakDownSql.getBreakdownModificationSql();
			
			List<String[]> breakdown = dbActionTemplate.getDataList(sql);
			
			//List<String[]> masterModuleGroup = reportProcedures.execute();
			
			//fillAbnormalityReport(AbnormalityReportModel);
			return breakdown;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

}

