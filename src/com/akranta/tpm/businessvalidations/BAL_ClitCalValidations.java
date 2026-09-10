package com.akranta.tpm.businessvalidations;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.BAL_CliTlStandardsSql;
import com.akranta.tpm.dao.sql.JhClitCalendarSqls;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;

public class BAL_ClitCalValidations {
	private DBActionTemplate dbActionTemplate = null;  
	
	public BAL_ClitCalValidations(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDBActionTemplate(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}


	
	public boolean ChkCalndrYear(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Check calendarYear called");
		try
		{
			System.out.println("inside validations of clit calendar"+commonFilter.toString());
			List<String> paramValues = new ArrayList<String>();

			
			//paramValues.add(commonFilter.getFromDate());
			StringBuffer sql = new StringBuffer();
			sql.append(JhClitCalendarSqls.getchkEqpmnt());
			sql .append(" WHERE  TO_CHAR(CLCA_MONTHYEAR, 'MON-YYYY') ='"+commonFilter.getFromMonth()+"'   ");
//			sql.append("Where 1 = 1 ");
			
			System.out.println("query query  " +sql.toString());
			
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
			
			System.out.println(chkEqpmt.size()+" chkEqpmt " +chkEqpmt.toString() +"query System.  " +sql.toString() );
			
			if( chkEqpmt.size() > 0 ){
				System.out.println("chkEqmt returned true");
				System.out.println("QUERY  :"+sql);
				return true;
							
				
				}
				
			return false;
			
			}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public void genrateCalYear(CommonFilter commonFilter) throws Exception {
		
		System.out.println("   X");
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
		System.out.println("paramValues  :"+paramValues);
		System.out.println("procedure before calling procedure");
		
		dbActionTemplate.processPLSQLProcedures("CLI_PC_CALENDARINSERT.CLI_PR_RUNCLICALENDAR",paramValues,outParam);
		System.out.println("procedure returned");
		
	
	}
}
