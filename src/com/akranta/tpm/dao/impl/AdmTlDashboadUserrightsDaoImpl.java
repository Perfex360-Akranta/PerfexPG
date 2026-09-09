package com.akranta.tpm.dao.impl;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.AdmTlDashboadUserrightsDao;
import com.akranta.tpm.dao.sql.AdmTlDashboadUserrightsSql;
import com.akranta.tpm.dao.sql.FilterCondSql;

import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class AdmTlDashboadUserrightsDaoImpl implements AdmTlDashboadUserrightsDao {

	
	private DBActionTemplate dbActionTemplate; 

	public AdmTlDashboadUserrightsDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	
	
	public AdmTlDashboadUserrights update(AdmTlDashboadUserrights admTlDashboadUserrights)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlDashboadUserrightsSql admTlDashboadUserrightsSql = new AdmTlDashboadUserrightsSql();
		try {

			sqls.add(AdmTlDashboadUserrightsSql.getUpdateSql(admTlDashboadUserrightsSql.getDburDbFields(), admTlDashboadUserrights.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlDashboadUserrights;
	}
	
	public AdmTlDashboadUserrights delete(AdmTlDashboadUserrights admTlDashboadUserrights)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlDashboadUserrightsSql admTlDashboadUserrightsSql = new AdmTlDashboadUserrightsSql();
		try {
			
			//sqls.add(AdmTlDashboadUserrightsSql.getDeleteSql(admTlDashboadUserrightsSql.getDburDbFields(), admTlDashboadUserrights.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlDashboadUserrights;
	}

	@Override
	public List<AdmTlDashboadUserrights> create(List<AdmTlDashboadUserrights> newDBUserRightsList) throws Exception {
		CommonMessage.debugMsg("Inside dao impl");
		List<String> sqls = new ArrayList<String>();
		AdmTlDashboadUserrightsSql admTlDashboadUserrightsSql = new AdmTlDashboadUserrightsSql();
		try
		{
			if(newDBUserRightsList!=null && newDBUserRightsList.size()>0)
			{
				for( AdmTlDashboadUserrights newdbuserrights :newDBUserRightsList)
			  {
					String menuid=newdbuserrights.getDburMenuid();
					String roleid=newdbuserrights.getDburRoleid();
					String sql=null;
					CommonMessage.debugMsg("Status delete"+newdbuserrights.getDburKeyid());
					//if(UIUtils.isValidKeyId(newdbuserrights.getDburKeyid())){
						
						if((newdbuserrights.getDburstatus().equals("D"))){
							sql="DELETE FROM ADM_TL_DASHBOAD_USERRIGHTS WHERE DBUR_MENUID ='"+menuid+"' AND DBUR_ROLEID = '"+roleid+"'";
							sqls.add(sql);
							//sqls.add(AdmTlDashboadUserrightsSql.getDeleteSql(admTlDashboadUserrightsSql.getDburDbFields(), newdbuserrights.getSaveArray()));
						
							newdbuserrights.setDburTempfield("-");
							//else if(newdbuserrights.getDburstatus().equals("U"))
							//sqls.add(AdmTlDashboadUserrightsSql.getUpdateSql(admTlDashboadUserrightsSql.getDburDbFields(), newdbuserrights.getSaveArray()));
						}
					//}
					else if(newdbuserrights.getDburstatus().equals("I")){
						newdbuserrights.setDburTempfield("-");
					//if(!UIUtils.isValidKeyId(newdbuserrights.getDburKeyid()))
						newdbuserrights.setDburKeyid(dbActionTemplate.getSequenceNumber(admTlDashboadUserrightsSql.TBL_ADM_TL_DASHBOAD_USERRIGHTS, 8, "DBR", "YYMM", "Y")); // set the sequnce number
					sqls.add(AdmTlDashboadUserrightsSql.getInsertSql(admTlDashboadUserrightsSql.getDburDbFields(), newdbuserrights.getSaveArray())); // add insert sql for master table
			  }
			  }
			
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return newDBUserRightsList;
	}

	@Override
	public Workbook getDBUserRightsExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{	
			    rs =   getFsasResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condForma = new XLConditionalFormats();
				XLConditionalFormats condForma1 = new XLConditionalFormats();
				condForma.setFontColor(new RGB(0,0,254)); //red font
				condForma.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condForma.setFontHeightPoint((short)14);
				condForma.setFontBoldWeight((short)20);
				condForma.setFromCol(3);
				condForma.setToCol(-1);
				condForma.setOperator(ComparisonOperator.EQUAL);
				condForma.setCondValue("1"); //Tick
				condForma.setIdentfier("1");
				condForma.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				condFormats.add(condForma);
				
				condForma1.setFontColor(new RGB(0,0,254)); //red font
				condForma1.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condForma1.setFontHeightPoint((short)14);
				condForma1.setFontBoldWeight((short)20);
				condForma1.setFromCol(3);
				condForma1.setToCol(-1);
				condForma1.setOperator(ComparisonOperator.EQUAL);
				condForma1.setCondValue("0"); //Tick
				condForma1.setIdentfier("0");
				condForma1.setSymbolStr(" ");
				condFormats.add(condForma1);
				

				
				excelUtils.setCondFormats(condFormats);
				
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
}

	private ResultSet getFsasResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.ADM_FN_DASHBOADMST", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		 condParms +="ISNEEDROLEID=N;";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}


		public List<String[]> getDBUserRights() throws Exception {
			try
			{
				  List<String > paramValues = new ArrayList<String>();
				 List<String[]> getDBUserRightsList;
				 
				 getDBUserRightsList = dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.ADM_FN_DASHBOADMST", paramValues);
		 		 
		 		
			return getDBUserRightsList;
			
		}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}
	
}

