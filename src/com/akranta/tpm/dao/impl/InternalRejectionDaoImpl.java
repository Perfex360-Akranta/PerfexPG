
package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.akranta.tpm.dao.InternalRejectionDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class InternalRejectionDaoImpl implements InternalRejectionDao {
	


	private DBActionTemplate dbActionTemplate; 
	
	public InternalRejectionDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	/*
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	 */
	public List<String[]> getCalender(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			 List<String > paramValues = new ArrayList<String>();
			 String condParms = "";//FilterCondSql.getQMInternalRejRelatedStr(commonFilter);
			 
			 paramValues.add(condParms);			 
			 List<String[]> calenderList;
			 CommonMessage.debugMsg("condParms1 : "+condParms);
			 calenderList = dbActionTemplate.processFunctionCallsWithColHeaders("QTM_PC_QUALITY.QTM_FN_GETSQLFORQM", paramValues);			
		
			 /*if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
			 */
			return calenderList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
}

