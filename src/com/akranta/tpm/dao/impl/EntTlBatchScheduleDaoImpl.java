package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.ProgramBatchLinkBean;
import com.akranta.tpm.bean.ProgramBatchLinkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlBatchScheduleDao;
import com.akranta.tpm.dao.sql.EntTlBatchScheduleSql;
import com.akranta.tpm.dao.sql.EntTlRoleSkillLinkSql;
import com.akranta.tpm.dao.sql.EntTlBatchScheduleSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlBatchSchedule;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlBatchScheduleDaoImpl implements EntTlBatchScheduleDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlBatchScheduleDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlBatchSchedule create(EntTlBatchSchedule entTlBatchSchedule) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			//entTlBatchSchedule.setBsdlKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchScheduleSql.TBL_ENT_TL_BATCH_SCHEDULE)); // set the sequnce number
			entTlBatchSchedule.setBsdlKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchScheduleSql.TBL_ENT_TL_BATCH_SCHEDULE, 10, "BSDL",null,null));
			sqls.add(EntTlBatchScheduleSql.getInsertSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBatchSchedule;
	}
	
	public EntTlBatchSchedule update(EntTlBatchSchedule entTlBatchSchedule)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql();
		try {

			sqls.add(EntTlBatchScheduleSql.getUpdateSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlBatchSchedule;
	}
	
	public EntTlBatchSchedule delete(EntTlBatchSchedule entTlBatchSchedule)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql();
		try {
			
			sqls.add(EntTlBatchScheduleSql.getDeleteSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBatchSchedule;
	}
	
	public EntTlBatchSchedule multiQuery(EntTlBatchSchedule entTlBatchSchedule) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			multiInserts(entTlBatchSchedule,sqls);
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBatchSchedule;
	}
	
	
	
	
	public List<String[]> getProgBatchGrid(String fromDt, String tillDt, String progId, String batchId) throws Exception {
		CommonMessage.debugMsg("Inside entry grid Dao Impl..........");
		try
		{		
			CommonMessage.debugMsg("progId==="+progId);
			
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			

			sql1.append(" SELECT BSDL_KEYID, BSDL_PROG_KEYID, BSDL_BACH_KEYID, TO_CHAR(DATE1,'DD-Mon-YYYY') AS BSDL_SCHEDULE_DATE,TO_CHAR(DATE1,'Day') ,");
			sql1.append(" BSDL_DURATION, BSDL_FROMTIME, BSDL_TILLTIME,'' AS SAVE, '' AS DEL ");
			sql1.append(" FROM (    ");
				
				sql1.append(" SELECT DISTINCT DATE1 FROM ( ");
				
				sql1.append(" SELECT TO_DATE('" + fromDt + "') + ROWNUM-1 AS DATE1 ");
				sql1.append(" FROM TAB  WHERE ROWNUM < = (TO_DATE('" + tillDt + "','dd-Mon-yyyy') -TO_DATE('" + fromDt + "','dd-Mon-yyyy'))+1 ");
			
				sql1.append(" UNION ALL ");
				sql1.append(" SELECT DISTINCT BSDL_SCHEDULE_DATE FROM ENT_TL_BATCH_SCHEDULE ");
				sql1.append(" WHERE BSDL_PROG_KEYID ='" + progId + "' AND BSDL_BACH_KEYID ='" + batchId + "' ");
				
				sql1.append(" ) ");
						
			sql1.append(" ),(");
			sql1.append(" SELECT BSDL_KEYID, BSDL_PROG_KEYID, BSDL_BACH_KEYID, TRUNC(BSDL_SCHEDULE_DATE) AS BSDL_SCHEDULE_DATE,TO_CHAR(BSDL_SCHEDULE_DATE,'Day') ,");
			sql1.append(" BSDL_DURATION, TO_CHAR(BSDL_FROMTIME,'HH:MI') AS BSDL_FROMTIME, TO_CHAR(BSDL_TILLTIME,'HH:MI') AS BSDL_TILLTIME,'' AS BTN");
			sql1.append(" FROM ENT_TL_BATCH_SCHEDULE");
			sql1.append(" WHERE BSDL_PROG_KEYID ='" + progId + "' AND BSDL_BACH_KEYID ='" + batchId + "'");
			sql1.append(" ) WHERE BSDL_SCHEDULE_DATE (+) = DATE1");
			sql1.append(" ORDER BY DATE1");
		
			sql=sql1.toString();
			
			CommonMessage.debugMsg("getEmployeeGrid sql="+sql);
			List<String[]> gridList = dbActionTemplate.getDataList(sql);
			return gridList;
			
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getEmployeeGrid dao impl"+e.getMessage());
		}
		return null;
	}
	
	public List<String[]> getFutureDays(String progId, String batchId , ProgramBatchLinkBean programBatchLinkBean) throws Exception {
		CommonMessage.debugMsg("Inside getFutureDays.........");
		try
		{		

			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			String fromDt =programBatchLinkBean.getDate();
			String toDt =programBatchLinkBean.getEndDate();			
				
			sql1.append(" SELECT TO_CHAR(DATE1,'DD-MON-YYYY') AS DATE1, BSDL_KEYID  FROM ( ");
			sql1.append(" SELECT DATE1 FROM ( ");			
			sql1.append(" SELECT TO_DATE('" + fromDt + "')-1 + ROWNUM AS DATE1 FROM TAB ");
			
			sql1.append(" WHERE ROWNUM <= (TO_DATE('" + toDt + "')-TO_DATE('" + fromDt + "'))+1 ");
			sql1.append(" ) WHERE TRUNC(DATE1)BETWEEN '" + fromDt + "' AND '" + toDt + "' ");
			sql1.append(" AND TRIM(TO_CHAR(DATE1,'Day'))= ( SELECT TRIM(TO_CHAR(TO_DATE('" + fromDt + "'),'Day')) FROM DUAL) ");
			sql1.append(" ),(  ");
			sql1.append(" SELECT BSDL_KEYID, TRUNC(BSDL_SCHEDULE_DATE) AS BSDL_SCHEDULE_DATE  ");
			sql1.append(" FROM ENT_TL_BATCH_SCHEDULE ");
			sql1.append(" WHERE BSDL_PROG_KEYID ='" + progId + "' AND BSDL_BACH_KEYID ='" + batchId + "'");
			sql1.append(" AND TRUNC(BSDL_SCHEDULE_DATE) BETWEEN '" + fromDt + "' AND '" + toDt + "' ");
			sql1.append(" ) WHERE BSDL_SCHEDULE_DATE (+) = DATE1");
			sql1.append(" ORDER BY DATE1");
			
			sql=sql1.toString();
			
			CommonMessage.debugMsg("getFutureDays sql="+sql);
			List<String[]> futureList = dbActionTemplate.getDataList(sql);
			return futureList;			
		}
	
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getFutureDays dao impl"+e.getMessage());
		}
		return null;		
	}
	
	public List<String[]> getBatchDates(String batchId) throws Exception {
		CommonMessage.debugMsg("Inside getBatchDates.........");
		try
		{		
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
				
			sql1.append(" SELECT TO_CHAR(BACH_FROMDATE,'DD-MON-YYYY'), TO_CHAR(BACH_TILLDATE,'DD-MON-YYYY') FROM ENT_TL_BATCHMST  ");
			sql1.append(" WHERE BACH_KEYID ='" + batchId + "' ");			
			sql=sql1.toString();
			
			CommonMessage.debugMsg("getBatchDates sql="+sql);
			List<String[]> batchList = dbActionTemplate.getDataList(sql);
			return batchList;			
		}
	
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getFutureDays dao impl"+e.getMessage());
		}
		return null;		
	}
	

	public List<String[]> getCalendarKeyid(String progId, String month) throws Exception {
		CommonMessage.debugMsg("Inside getCalendarKeyid.........");
		try
		{		
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
				
			sql1.append(" SELECT ECAL_KEYID FROM ENT_TL_PROG_CALENDAR   ");
			sql1.append(" WHERE ECAL_PROG_ID ='" + progId + "' ");			
			sql1.append(" AND ECAL_PLAN_MONTH=UPPER('" + month + "') ");
			
			sql=sql1.toString();
			
			CommonMessage.debugMsg("getBatchDates sql="+sql);
			List<String[]> calList = dbActionTemplate.getDataList(sql);
			
			return calList;			
		}
	
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getFutureDays dao impl"+e.getMessage());
		}
		return null;		
	}	
	
	private List<String> multiInserts(EntTlBatchSchedule entTlBatchSchedule,
			List<String> sqls) throws Exception
	{
		//CommonMessage.debugMsg("SIZE OF GRID"+entTlBatchSchedule2.getPcsTlAncilliarytimeList().size());
		if(entTlBatchSchedule.getEntTlBatchScheduleList()!= null && entTlBatchSchedule.getEntTlBatchScheduleList().size()>=0) // check for detail table data
		{
			if (entTlBatchSchedule.getEntTlBatchScheduleList().size()>=0) {
				CommonMessage.debugMsg("bsdl keyid in "+entTlBatchSchedule.getBsdlKeyid());
				//sqls.add(entTlBatchSchedule2Sql.getDeleteSql(entTlBatchSchedule., entTlBatchSchedule2.getPlrkLossid()));
			}
    		
	    	for(int i =0;i<entTlBatchSchedule.getEntTlBatchScheduleList().size();i++)
			{	
	    		EntTlBatchScheduleSql entTlBatchScheduleSql = new EntTlBatchScheduleSql();
	    		EntTlBatchSchedule entTlBatchSchedule2 = (EntTlBatchSchedule)entTlBatchSchedule.getEntTlBatchScheduleList().get(i);
	    		
	    		CommonMessage.debugMsg("entTlBatchSchedule2.getBsdlKeyid()"+entTlBatchSchedule2.getBsdlKeyid());
	    		
	    		if (!UIUtils.isValidKeyId(entTlBatchSchedule2.getBsdlKeyid())) {
	    			entTlBatchSchedule2.setBsdlKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchScheduleSql.TBL_ENT_TL_BATCH_SCHEDULE, 8, "BSDL",null,null)); // set the sequnce number
	    			sqls.add(EntTlBatchScheduleSql.getInsertSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule2.getSaveArray()));// add insert sql for detail table
	    		}
	    		else
	    			sqls.add(EntTlBatchScheduleSql.getUpdateSql(entTlBatchScheduleSql.getBsdlDbFields(), entTlBatchSchedule2.getSaveArray()));
				}
		}
		return sqls;
	}

	@Override
	public String getProgDuration(String progId) throws Exception {
		// TODO Auto-generated method stub
		String progDuration = dbActionTemplate.getSingleValue("SELECT PROG_MAX_DURATION+(PROG_MAX_DURATION*20/100) FROM ENT_TL_PROGRAMMST WHERE PROG_KEYID = '"+progId+"'");
		CommonMessage.debugMsg("Duration  :"+progDuration);
		return progDuration;
	}
	
}

