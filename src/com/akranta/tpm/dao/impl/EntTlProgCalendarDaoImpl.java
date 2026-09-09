package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlProgCalendarDao;
import com.akranta.tpm.dao.sql.EntTlProgCalendarSql;
import com.akranta.tpm.dao.sql.EntTlSkillmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlProgCalendarDaoImpl implements EntTlProgCalendarDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlProgCalendarDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<EntTlProgCalendar> create(List<EntTlProgCalendar> entTlProgCalendar) throws ValidationExceptions,BusinessApplicationExceptions, Exception {

		CommonMessage.debugMsg(" IN sid ethe Dao Impl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql(); // contains dbtable,field names, Field types and related sqls  of master table		
		try{
			if (entTlProgCalendar!= null && entTlProgCalendar.size() > 0) 
			{
				for( EntTlProgCalendar newEntTlProgCalendar :entTlProgCalendar)
				{	
					if(newEntTlProgCalendar.getEcalActive().equals("Y")){					
						if(!UIUtils.isValidKeyId(newEntTlProgCalendar.getEcalKeyid())){
							newEntTlProgCalendar.setEcalKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_PROG_CALENDAR)); // set the sequnce number 
							sqls.add(EntTlProgCalendarSql.getInsertSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray())); // add insert sql for master table}
						}
						else{
							sqls.add(EntTlProgCalendarSql.getUpdateSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
						}
					}
					else if(newEntTlProgCalendar.getEcalActive().equals("N")){	
						sqls.add(EntTlProgCalendarSql.getDeleteSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
					}
				}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			}	
		}
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlProgCalendar;
	}
	
	public List<EntTlProgCalendar> update(List<EntTlProgCalendar> entTlProgCalendar)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();
		try {
			if (entTlProgCalendar!= null && entTlProgCalendar.size() > 0) 
			{
				for( EntTlProgCalendar newEntTlProgCalendar :entTlProgCalendar)
				{	
					sqls.add(EntTlProgCalendarSql.getUpdateSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
				}
				dbActionTemplate.executeStatements(sqls);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlProgCalendar;
	}
	
	public List<EntTlProgCalendar> delete(List<EntTlProgCalendar> entTlProgCalendar)
			throws Exception {
		List<String> sqls = new ArrayList<String>();
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();
		try {
			if (entTlProgCalendar!= null && entTlProgCalendar.size() > 0) 
			{
				for( EntTlProgCalendar newEntTlProgCalendar :entTlProgCalendar)
				{	
					sqls.add(entTlProgCalendarSql.getDeleteSql(entTlProgCalendarSql.getEcalDbFields(), newEntTlProgCalendar.getSaveArray()));
				}
				dbActionTemplate.executeStatements(sqls);
			}
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlProgCalendar;
	}
	
	public EntTlProgCalendar select(EntTlProgCalendar entTlProgCalendar)throws Exception {		
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();
		String sql = entTlProgCalendarSql.getSelectSql(entTlProgCalendarSql.getEcalDbFields(), entTlProgCalendar.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);		
		Object [] args =  new Object [] {};
		entTlProgCalendar.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+entTlProgCalendar.getEcalKeyid());
		return entTlProgCalendar;
	}
	
	public List<String[]> selectSchedule(EntTlProgCalendar entTlProgCalendar)throws Exception{
		String sql=null;
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();			
		sql=entTlProgCalendarSql.getRefersSql(entTlProgCalendarSql.getEcalDbFields(), entTlProgCalendar.getSaveArray());		
		CommonMessage.debugMsg(" sql " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}
	
	public List<EntTlProgCalendar> selectList(EntTlProgCalendar entTlProgCalendar)throws Exception {	
		String sql=null;
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();				
		sql=entTlProgCalendarSql.getDeleteSql(entTlProgCalendarSql.getEcalDbFields(), entTlProgCalendar.getSaveArray());
		CommonMessage.debugMsg(" sql: " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillProgCalenderList(resultList);
	}
	
	public List<String[]> getDatesList(String month, int noOfMonths,String ProgId)throws Exception{
		String sql=null;
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();			
		sql=entTlProgCalendarSql.getSelectMonthSql(month, noOfMonths,ProgId);		
		CommonMessage.debugMsg (" sql in DaoImpl " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return resultList;
	}
	
	public List<String[]> getProgCalendergridData(String month, int noOfMonths,String ProgId,String frmType)throws Exception{
		CommonFilter commonFilter=new CommonFilter();
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		List<String > paramValues = new ArrayList<String>();			
		paramValues.add("FROMMONTH="+month+";MONTHDIFF="+noOfMonths+";PROGID="+ProgId +";Type="+frmType+";");
		paramValues.add(commonParams);
		List<String[]> resultList;	
		resultList = dbActionTemplate.processFunctionCallsWithColHeaders("ENT_FN_PROGCALENDAR", paramValues);
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		
		return resultList;
	}
	
	public String getStartMonth()throws Exception{
		String sql=null;
		EntTlProgCalendarSql entTlProgCalendarSql = new EntTlProgCalendarSql();				
		sql=entTlProgCalendarSql.getSelectStartMonthSql();
		CommonMessage.debugMsg(" sql: " + sql);
		String resultList = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" getStartMonth: " + resultList);
		return resultList;
	}
	
	private List<EntTlProgCalendar> fillProgCalenderList(List<String []> resultList) throws SQLException
	{
		   List<EntTlProgCalendar> menus = new ArrayList<EntTlProgCalendar>();
		   for( String [] row : resultList )
		   {			  
			   EntTlProgCalendar entTlProgCalendar = new EntTlProgCalendar();
			   entTlProgCalendar.setEcalKeyid(row[0]);
			   entTlProgCalendar.setEcalProgId(row[1]);
			   entTlProgCalendar.setEcalPlanFromdate(row[2]);	
			   entTlProgCalendar.setEcalPlanTilldate(row[3]);
			   entTlProgCalendar.setEcalPlanMonth(row[4]);
			   entTlProgCalendar.setEcalPlanWeek(row[5]);
			   entTlProgCalendar.setEcalPlanAudiencecount(row[6]);
			   entTlProgCalendar.setEcalRequestby(row[7]);
			   entTlProgCalendar.setEcalRequestdate(row[8]);
			   entTlProgCalendar.setEcalRequestremarks(row[9]);	
			   entTlProgCalendar.setEcalApprovedflag(row[10]);	
			   entTlProgCalendar.setEcalApprovedby(row[11]);	
			   entTlProgCalendar.setEcalApproveddate(row[12]);	
			   entTlProgCalendar.setEcalApprovedremarks(row[13]);	
			   entTlProgCalendar.setEcalActualFromdate(row[14]);	
			   entTlProgCalendar.setEcalActualTilldate(row[15]);	
			   entTlProgCalendar.setEcalActualAudiencecount(row[16]);	
			   entTlProgCalendar.setEcalStatus(row[17]);	
			   entTlProgCalendar.setEcalMonthwise(row[18]);	
			   entTlProgCalendar.setEcalActualAudiencecount(row[19]);				   
			   menus.add(entTlProgCalendar);			   
		   }
  		  return menus;
	 }

	@Override
	public List<String[]> getSelfNominationReport(CommonFilter commonFilter,String empId, String progId, String batchId,String userid,String Status)throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............"+condParms);
		condParms +="UNIQUEPOS=";	
		CommonMessage.debugMsg("test Dao impl type  2222"+commonFilter.getAreatype());
		if(UIUtils.isValidKeyId(commonFilter.getAreatype()))
		{
		  CommonMessage.debugMsg("test Dao impl type "+commonFilter.getAreatype());
		  condParms +=commonFilter.getAreatype();
		}
		if(UIUtils.isValidKeyId(commonFilter.getFromMonth())){
			if(commonFilter.getFromMonth().equals(Constants.passNullMonth)){
				commonFilter.setFromMonth("");
			}
		}else
			commonFilter.setFromMonth("");
		/*condParms +=";MONTH=";
		if(UIUtils.isValidKeyId(commonFilter.getFromMonth()))
		{
		   condParms +=commonFilter.getFromMonth();
		}*/
		if(UIUtils.isValidKeyId(empId)){
			condParms +=";EMPID="+empId;
		}
		if(UIUtils.isValidKeyId(userid)){
			condParms +=";USERID="+userid;
		}
		/*if(UIUtils.isValidKeyId(progId)){
			condParms +=";PROGID="+progId;
		}
		if(UIUtils.isValidKeyId(batchId)){
			condParms +=";BATCHID="+batchId;
		}*/		
		/*if(UIUtils.isValidKeyId(Status)){
			condParms +=";STATUS="+Status;
		}*/
	
		paramValues.add(condParms+";");
		paramValues.add(commonParams); 
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("test_pc_testSKILLINDX.ENT_FN_SELFNOMINATION", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}			
		}
		CommonMessage.debugMsg("Dao impl msg::: ");
		return dataList; 
	}
@Override	
public List<String[]> getSelfNominationPopUp(CommonFilter commonFilter,String empid)
throws Exception {
	
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............"+condParms);		
		if(UIUtils.isValidKeyId(empid)){
			condParms +="USERID="+empid;
		}
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("test_pc_testSKILLINDX. ENT_FN_SELFNOMINATIONAPPROVED", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}			
		}
		CommonMessage.debugMsg("Dao impl msg::: ");
		return dataList;
		
	}

@Override
public List<String[]> getSelfNominationMainGrid(CommonFilter commonFilter,String empid) throws Exception {
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();		
	String condParms =FilterCondSql.getETRelatedStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
	
	paramValues.add(condParms+"EMPID="+empid);
	paramValues.add(commonParams); 
	
	List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("test_pc_testSKILLINDX.ENT_FN_SELFNOMINATIONMAINGRID", paramValues);
	if( commonFilter.getViewClick() == 'Y'){
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger ){
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}			
	}
	CommonMessage.debugMsg("Dao impl msg::: ");
	return dataList; 
}

}

