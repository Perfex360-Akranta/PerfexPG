package com.akranta.tpm.businessvalidations;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.sql.CliTlStandardsSql;
import com.akranta.tpm.dao.sql.JhClitCalendarSqls;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.CommonMessage;

public class ClitCalValidations {
	private DBActionTemplate dbActionTemplate = null;  
	
	public ClitCalValidations(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDBActionTemplate(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}


	
	public boolean ChkCalndrYear(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Check calendarYear called");
		try
		{
			CommonMessage.debugMsg("inside validations of clit calendar");
			List<String> paramValues = new ArrayList<String>();

			//paramValues.add(commonFilter.getFromDate());
			StringBuffer sql = new StringBuffer();
			sql.append(JhClitCalendarSqls.getchkEqpmnt());
			sql .append(" WHERE  TO_CHAR(CLCA_MONTHYEAR, 'MON-YYYY') = '"+commonFilter.getFromMonth()+"'   ");
//			sql.append("Where 1 = 1 ");
			
			String tempId = commonFilter.getFactory().getId();
			
			
			if( CommonFunctions.isValidKeyId(tempId))
				sql.append(" AND CLCA_FACTORYID = '" + tempId + "'");
			
			tempId =commonFilter.getSection().getId();
			if( CommonFunctions.isValidKeyId(tempId))
				sql.append(" AND CLCA_SECTIONID = '" + tempId + "'");
			
			tempId =commonFilter.getCell().getId();
			if( CommonFunctions.isValidKeyId(tempId))
				sql.append(" AND CLCA_CELLID = '" + tempId + "'");
			
			tempId =commonFilter.getMachine().getId();
			if( CommonFunctions.isValidKeyId(tempId))
				sql.append(" AND CLCA_MACHINEID = '" + tempId + "'");
							
			List<String[]> chkEqpmt = dbActionTemplate.getDataList(sql.toString());
			
			if( chkEqpmt.size() > 0 )
				//CommonMessage.debugMsg("chkEqmt returned true");
				return true;
				
			return false;
			//CommonMessage.debugMsg("QUERY  :"+sql);
			}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public void genrateCalYear(CommonFilter commonFilter) throws Exception {
		
		CommonMessage.debugMsg("Generate calendar called");
		List<String> paramValues = new ArrayList<String>();
		Object[] outParam    = null;
		paramValues.add("01-"+commonFilter.getFromMonth());
		paramValues.add(commonFilter.getFactory()!=null ?( commonFilter.getFactory().getId() != null ? commonFilter.getFactory().getId():"{}") :"{}");
		paramValues.add(commonFilter.getSection()!= null ?( commonFilter.getSection().getId() !=null ? commonFilter.getSection().getId():"{}"):"{}");
		paramValues.add(commonFilter.getCell() != null ? (commonFilter.getCell().getId() != null?commonFilter.getCell().getId():"{}"):"{}");
		paramValues.add(commonFilter.getMachine()!= null ? (commonFilter.getMachine().getId()!=null?commonFilter.getMachine().getId():"{}"):"{}");
		paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
		paramValues.add("X");
		//outParam.add("null");
		CommonMessage.debugMsg("paramValues  :"+paramValues);
		CommonMessage.debugMsg("procedure before calling procedure");
		
		dbActionTemplate.processPLSQLProcedures("CLI_PC_CALENDARINSERT.CLI_PR_RUNCLICALENDAR",paramValues,outParam);
		CommonMessage.debugMsg("procedure returned");
		
	
	}
}
