package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlBatchEmpAbsentDao;
import com.akranta.tpm.dao.sql.BdmTlShiftwisesplitSql;
import com.akranta.tpm.dao.sql.EntTlBatchEmpAbsentSql;
import com.akranta.tpm.dao.sql.EntTlEmpskillhistorySql;
import com.akranta.tpm.dao.sql.EntTlTopicmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchEmpAbsent;
import com.akranta.tpm.model.EntTlEmpskillhistory;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlBatchEmpAbsentDaoImpl implements EntTlBatchEmpAbsentDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlBatchEmpAbsentDaoImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		//dbActionTemplate = new DBActionTemplate();
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlBatchEmpAbsent create(EntTlBatchEmpAbsent entTlBatchEmpAbsent) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBatchEmpAbsentSql entTlBatchEmpAbsentSql = new EntTlBatchEmpAbsentSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlEmpskillhistorySql entTlEmpskillhistorySql =new EntTlEmpskillhistorySql();
		try{
			if(entTlBatchEmpAbsent.getBatchEmpAbsent()!= null && entTlBatchEmpAbsent.getBatchEmpAbsent().size()>0) // check for detail table data
			{
				
		    	for(int i =0;i<entTlBatchEmpAbsent.getBatchEmpAbsent().size();i++)
				{	
		    		EntTlBatchEmpAbsent batchEmpAbsent = (EntTlBatchEmpAbsent)entTlBatchEmpAbsent.getBatchEmpAbsent().get(i); // get detail info from list in empployee object
		    		boolean insert = true;
		    		EntTlEmpskillhistory newEntTlEmpskillhistory = new EntTlEmpskillhistory();		    		
		    		CommonMessage.debugMsg("batchEmpAbsent.getEbeaBsdlKeyid()=="+batchEmpAbsent.getEbeaBsdlKeyid());
		    		CommonMessage.debugMsg("batchEmpAbsent.getEbeaBachKeyid()=="+batchEmpAbsent.getEbeaBachKeyid());
		    		if(batchEmpAbsent.getEbeaBsdlKeyid().indexOf("_UN")>0)
		    		{
		    			insert = false;
		    			batchEmpAbsent.setEbeaBsdlKeyid(batchEmpAbsent.getEbeaBsdlKeyid().substring(0, batchEmpAbsent.getEbeaBsdlKeyid().indexOf("_UN")));
		    		}
		    		
		    		sqls.add(EntTlBatchEmpAbsentSql.deleteSql(batchEmpAbsent.getEbeaBsdlKeyid(), batchEmpAbsent.getEbeaEmpmKeyid()));
		    		if(insert)
		    		{
			    		batchEmpAbsent.setEbeaModifiedon(entTlBatchEmpAbsent.getEbeaModifiedon());
			    		batchEmpAbsent.setEbeaCreatedon(entTlBatchEmpAbsent.getEbeaCreatedon());
			    		batchEmpAbsent.setEbeaCreatedby(entTlBatchEmpAbsent.getEbeaCreatedby());
			    		batchEmpAbsent.setEbeaActive(entTlBatchEmpAbsent.getEbeaActive());
			    		//batchEmpAbsent.setEbeaAttendance(entTlBatchEmpAbsent.getEbeaAttendance());
			    		batchEmpAbsent.setEbeaTempfield3(entTlBatchEmpAbsent.getEbeaTempfield3());
			    		batchEmpAbsent.setEbeaTakenby(entTlBatchEmpAbsent.getEbeaTakenby());
			    		//batchEmpAbsent.setEbeaReason(entTlBatchEmpAbsent.getEbeaReason());
			    		//batchEmpAbsent.setEbeaBachKeyid(entTlBatchEmpAbsent.getEbeaBachKeyid());
			    		batchEmpAbsent.setEbeaKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchEmpAbsentSql.TBL_ENT_TL_BATCH_EMP_ATTENDANCE,10,"EA","YYMM", "Y"));
			    		newEntTlEmpskillhistory.setEeshKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpskillhistorySql.TBL_ENT_TL_EMPSKILLHISTORY,10,"EESH","","Y"));		
			    		sqls.add(EntTlBatchEmpAbsentSql.getInsertSql(entTlBatchEmpAbsentSql.getEbeaDbFields(), batchEmpAbsent.getSaveArray()));// add insert sql for detail table
			    		newEntTlEmpskillhistory.setEeshActive("Y");
			    		newEntTlEmpskillhistory.setEeshAssesmentid("{}");
			    		newEntTlEmpskillhistory.setEeshAssessdby("{}");
			    		newEntTlEmpskillhistory.setEeshCreatedby(entTlBatchEmpAbsent.getEbeaCreatedby() );
			    		newEntTlEmpskillhistory.setEeshCurrentratting("1");
			    		newEntTlEmpskillhistory.setEeshEmployeeid(batchEmpAbsent.getEbeaEmpmKeyid() );
			    		newEntTlEmpskillhistory.setEeshCreatedon(entTlBatchEmpAbsent.getEbeaCreatedon() );
			    		newEntTlEmpskillhistory.setEeshModifiedon(entTlBatchEmpAbsent.getEbeaModifiedon() );
			    		newEntTlEmpskillhistory.setEeshSkilupdateddate(entTlBatchEmpAbsent.getEbeaCreatedon() );
			    		newEntTlEmpskillhistory.setEeshTopicid(entTlBatchEmpAbsent.getTopicId());
			    		newEntTlEmpskillhistory.setEeshTempfield1("-");
			    		newEntTlEmpskillhistory.setEeshTempfield2("-");
			    		newEntTlEmpskillhistory.setEeshTempfield3("-");
			    		newEntTlEmpskillhistory.setEeshTempfield4("-");
			    		newEntTlEmpskillhistory.setEeshTempfield5("-");
			    		newEntTlEmpskillhistory.setEeshTempfield6("-");
			    		sqls.add(EntTlEmpskillhistorySql.getInsertSql(entTlEmpskillhistorySql.getEeshDbFields(), newEntTlEmpskillhistory.getSaveArray()));
		    		}
				}
			}
			

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBatchEmpAbsent;
	}
	
	public EntTlBatchEmpAbsent update(EntTlBatchEmpAbsent entTlBatchEmpAbsent)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlBatchEmpAbsentSql entTlBatchEmpAbsentSql = new EntTlBatchEmpAbsentSql();
		try {

			sqls.add(EntTlBatchEmpAbsentSql.getUpdateSql(entTlBatchEmpAbsentSql.getEbeaDbFields(), entTlBatchEmpAbsent.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlBatchEmpAbsent;
	}
	
	public EntTlBatchEmpAbsent delete(EntTlBatchEmpAbsent entTlBatchEmpAbsent)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlBatchEmpAbsentSql entTlBatchEmpAbsentSql = new EntTlBatchEmpAbsentSql();
		try {
			
			sqls.add(EntTlBatchEmpAbsentSql.getDeleteSql(entTlBatchEmpAbsentSql.getEbeaDbFields(), entTlBatchEmpAbsent.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBatchEmpAbsent;
	}
	
	public List<String[]> getEmpList(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_EMPATTENDANCE", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)-1);				
			}
		}
		return dataList; 
	}

	@Override
	public String getProgram(String batch) throws Exception {
		// TODO Auto-generated method stub
		String Sql = "SELECT PROG_KEYID FROM ENT_TL_PROGRAMMST WHERE PROG_KEYID = (SELECT BACH_PROG_KEYID FROM  ENT_TL_BATCHMST WHERE  BACH_KEYID =  '" + batch + "')";
		CommonMessage.debugMsg("Prog Id "+Sql);
		String progId = dbActionTemplate.getSingleValue(Sql);
		CommonMessage.debugMsg("Prog progId "+progId);
		return progId;
	}

	@Override
	public List<String[]> getTrainingAttedSmry(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> trnAttendSmry = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGATTENDANCESMRY", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt)-1);				
			}
		}
		return trnAttendSmry; 
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGATTENDANCESMRY", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
			 
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	@Override
	public Workbook attendanceSmryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	@Override
	public List<String[]> getEmployeeattandancefillgriddata(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		/*StringBuffer sql = new StringBuffer();
		String sql1 ="";//select '1','Area 1' from dual union all  select '1','Area 1' from dual;
		sql.append(" select ' ','Employee 1','',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 2',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 3',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
		sql.append(" select ' ','Employee 4',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 5',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 6',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 7',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 8',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all");
	    sql.append(" select ' ','Employee 9',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual union all ");
	    sql.append(" select ' ','Employee 10',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ' from dual");
	    
	    sql1 = sql.toString();
		CommonMessage.debugMsg("sql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql1);
		CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return dataList; 
	
	*/
		
		List<String> paramValues = new ArrayList<String>();		
		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		

		String progId ="";
		String batchId ="";
		String month ="JAN-1801";
		
		if (UIUtils.isValidKeyId(commonFilter.getProgram()))
			progId=commonFilter.getProgram();
		if (UIUtils.isValidKeyId(commonFilter.getBK()))
			batchId=commonFilter.getBK();
		if (UIUtils.isValidKeyId(commonFilter.getFromMonth()))
			month=commonFilter.getFromMonth();
		
		condParms+= "PROGID="+progId+";BATCHID="+batchId+";FORMONTH="+month+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_EMPLOYEE_ATTENDANCE", paramValues);
		
		CommonMessage.debugMsg("test to............");
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
	
		return dataList;
	}
	
	@Override
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getTopicReport(commonFilter);
			CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	
	private ResultSet getTopicReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String sql =EntTlTopicmstSql.TopicDetailGrid(commonFilter,commonFilter.getKey());
		return dbActionTemplate.getData(sql);
	}

	@Override
	public List<String[]> getFacultyTrainingHoursReport(CommonFilter commonFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 'Faculty' AS faculty, 'Program' AS program,'Functional Location' AS FunctionalLocation, 'No Of Hours' AS noofhours,'No Of Participants' as NoOfParticipants,'No Of Hours*No Of Participants'  FROM DUAL union all");
		sql.append(" SELECT 'B VENKATESH', 'Program 1','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '6','50','300' from dual union all");
		sql.append(" SELECT 'B VENKATESH', 'Program 2','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '6','50','300' from dual union all");
		sql.append(" SELECT 'B VENKATESH', 'Program 3','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '6','50','300'from dual union all");
		sql.append(" SELECT 'B VENKATESH', 'Program 4','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '6','50','300'from dual union all");
		sql.append(" SELECT 'B VENKATESH', 'Program 5','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '6','50','300' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 6','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 7','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 8','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 9','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 10','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'B SANJEEVARAO', 'Program 11','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'CH CHANDRASHEKAR', 'Program 12','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '8','50','400' from dual union all");
		sql.append(" SELECT 'CH CHANDRASHEKAR', 'Program 13','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '8','50','400' from dual union all");
		sql.append(" SELECT 'CH CHANDRASHEKAR', 'Program 14','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '8','50','400' from dual union all");
		sql.append(" SELECT 'CH CHANDRASHEKAR', 'Program 15','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '8','50','400' from dual union all");
		sql.append(" SELECT 'SK JANIMIYA', 'Program 16','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'SK JANIMIYA', 'Program 17','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'SK JANIMIYA', 'Program 18','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'SK JANIMIYA', 'Program 19','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'O RAMESH', 'Program 21','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'O RAMESH', 'Program 22','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'O RAMESH', 'Program 23','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '2','50','100' from dual union all");
		sql.append(" SELECT 'G RAVI KUMAR', 'Program 3','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'G RAVI KUMAR', 'Program 3','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual union all");
		sql.append(" SELECT 'G RAVI KUMAR', 'Program 3','2000 / BCM / SBU1 / PBU1 / PULP MILL / FIBRELINE 1 / STATION BATTERY CHARGER(NFL-1)', '3','50','150' from dual");
	
		CommonMessage.debugMsg("sql:::"+sql);
		List<String> params=null;
		List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		return gridData;
	}
}

