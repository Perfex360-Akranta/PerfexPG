package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.dao.EntBatchCreationDao;
import com.akranta.tpm.dao.sql.EntBatchMstSql;
import com.akranta.tpm.dao.sql.EntTlBatchEmployeeLinkSql;
import com.akranta.tpm.dao.sql.EntTlBatchFacultyLinkSql;
import com.akranta.tpm.dao.sql.EntTlFacultymstSql;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.EntTlFacultymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class EntBatchCreationDaoImpl implements EntBatchCreationDao
{
	private DBActionTemplate dbActionTemplate; 
	private TrainingCalendarDaoImpl trainingCalendarDaoImpl; 
	public EntBatchCreationDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		trainingCalendarDaoImpl= new TrainingCalendarDaoImpl(dbActionTemplate);
		
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public EntBatchMst create(EntBatchMst entBatchMst) 	throws Exception {
		List<String> sqls = new ArrayList<String>(); 
		EntBatchMstSql entBatchMstSql=new EntBatchMstSql();/* sqls for execution */ 
		try{ 
			String bachName =trainingCalendarDaoImpl.getBachName(entBatchMst);
			entBatchMst.setBachName(bachName);
			CommonMessage.debugMsg("bachName  "+bachName);
			entBatchMst.setBachKeyid(dbActionTemplate.getSequenceNumber("TBL_ENT_TL_BATCHMST",8,"BAT","",""));// set the sequnce number
			sqls.add(EntBatchMstSql.getInsertSql(entBatchMstSql.getBachDbFields(), entBatchMst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return entBatchMst;		
	}
public EntBatchMst update(EntBatchMst entBatchMst)	throws Exception { 
		
	List<String> sqls = new ArrayList<String>();
	EntBatchMstSql entBatchMstSql=new EntBatchMstSql();
	try {
		CommonMessage.debugMsg("inside dao impl update for function " +entBatchMst.getBachKeyid());
		
		sqls.add(EntBatchMstSql.getUpdateSql(entBatchMstSql.getBachDbFields(), entBatchMst.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage());
	}
	
	return entBatchMst;
	}
	
	public EntBatchMst delete(EntBatchMst entBatchMst) throws Exception 
	{
		EntBatchMstSql entBatchMstSql=new EntBatchMstSql();
		List<String> sqls = new ArrayList<String>();
		
		sqls.add(EntBatchMstSql.getDeleteSql(entBatchMstSql.getBachDbFields(), (Object[]) entBatchMst.getSaveArray()));

		dbActionTemplate.executeStatements(sqls);
		trainingCalendarDaoImpl.updateBatchName(entBatchMst.getBachProgKeyid());
		return entBatchMst;	
	}
	public EntBatchMst select(String bachKeyid) throws Exception {
		EntBatchMst entBatchMst = new EntBatchMst();
		String sql = EntBatchMstSql.getBatchMstSql();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {bachKeyid};
		entBatchMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return entBatchMst;
	

	}
	
	@Override
	public List<String[]> getBatchDao(String ProgId,String frmMonth) throws Exception {
		// TODO Auto-generated method stub
		String sql = EntBatchMstSql.getBatchQuery(ProgId,frmMonth);
		CommonMessage.debugMsg(sql);
		List<String[]> result=dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(result);
		return result;
	}
	
	
	public EntTlBatchFacultyLink createBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlBatchFacultyLinkSql entTlBatchFacultyLinksql = new EntTlBatchFacultyLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlFacultymstSql entTlFacultymstSql = new EntTlFacultymstSql();
		try{
			List <EntTlFacultymst> facultymstList = entTlBatchFacultyLink.getEntTlFacultymst();
			   if(facultymstList != null && facultymstList.size()>0)
				{//MLMM_REFDOCID
					for(EntTlFacultymst facultymstObj:facultymstList)
					{
						CommonMessage.debugMsg("facultymst ss");
						facultymstObj.setFtymKeyid(dbActionTemplate.getSequenceNumber(EntTlFacultymstSql.TBL_ENT_TL_FACULTYMST, 8, "FTY", null, "N"));
						//toolsPickUp.setPtldKeyid(dbActionTemplate.getSequenceNumber(PlmTlToolsdtlSql.TBL_PLM_TL_TOOLSDTL,13, "TLDll", "MMYY", null));
						CommonMessage.debugMsg("fac mst ss"+facultymstObj.getFtymKeyid());
						entTlBatchFacultyLink.setBflkFtymKeyid(facultymstObj.getFtymKeyid());
						
					    sqls.add(EntTlFacultymstSql.getInsertSql(entTlFacultymstSql.getFtymDbFields(),facultymstObj.getSaveArray()));
					    entTlBatchFacultyLink.setBflkKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchFacultyLinkSql.TBL_ENT_TL_BATCH_FACULTY_LINK, 8, "BFL", null, "N")); // set the sequnce number 
						sqls.add(EntTlBatchFacultyLinkSql.getInsertSql(entTlBatchFacultyLinksql.getBflkDbFields(), entTlBatchFacultyLink.getSaveArray())); // add insert sql for master table
					}
				}
			   else{
				   CommonMessage.debugMsg("inside else of fac save");
					entTlBatchFacultyLink.setBflkKeyid(dbActionTemplate.getSequenceNumber(EntTlBatchFacultyLinkSql.TBL_ENT_TL_BATCH_FACULTY_LINK, 8, "BFL", null, "N")); // set the sequnce number 
					sqls.add(EntTlBatchFacultyLinkSql.getInsertSql(entTlBatchFacultyLinksql.getBflkDbFields(), entTlBatchFacultyLink.getSaveArray())); // add insert sql for master table
			   }
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlBatchFacultyLink;
	}
	public EntTlBatchFacultyLink updateBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink)	throws Exception { 
			
			List<String> sqls = new ArrayList<String>();
			EntTlBatchFacultyLinkSql entTlBatchFacultyLinkSql = new EntTlBatchFacultyLinkSql();
			try {
	
				sqls.add(EntTlBatchFacultyLinkSql.getUpdateSql(entTlBatchFacultyLinkSql.getBflkDbFields(), entTlBatchFacultyLink.getSaveArray()));
				
				dbActionTemplate.executeStatements(sqls);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}
			
			return entTlBatchFacultyLink;
		}
	public List<String[]> getBatchFacultyView(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception
	{
		try
		{
			String sql = EntTlBatchFacultyLinkSql.getBatchFacultyViewsql(entTlBatchFacultyLink);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public List<String[]> getBatchFacultyCount(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception
	{
		try
		{
			String sql = EntTlBatchFacultyLinkSql.getBatchFacultyCountsql(entTlBatchFacultyLink);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public EntTlBatchFacultyLink deleteBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink)
	throws Exception {
	
		List<String> sqls = new ArrayList<String>();
		EntTlBatchFacultyLinkSql entTlBatchFacultyLinkSql = new EntTlBatchFacultyLinkSql();
		try {
		
		sqls.add(entTlBatchFacultyLinkSql.getDeleteSql(entTlBatchFacultyLinkSql.getBflkDbFields(), entTlBatchFacultyLink.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
		
		}catch( Exception e){
		throw new Exception(e.getMessage());
		}
		return entTlBatchFacultyLink;
	}
public Workbook getBatch( JSONObject colmodel,String rptFormat) throws Exception {
	CommonMessage.debugMsg("in dao");
	 ResultSet rs = null;
	   try{
		
		rs =   getdownTimeReportResultSet();
		CommonMessage.debugMsg("rptFormat="+rptFormat);
		CommonMessage.debugMsg("rs="+rs);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,rptFormat,0, 0,0 );
		
	   }finally{
		   if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
	private ResultSet getdownTimeReportResultSet() throws Exception
	{
		String sql=EntBatchMstSql.getBatchQuery1();
		
		return dbActionTemplate.getData(sql);
		
	}


	public List<String[]> getBatchEmployeeView(EntTlBatchEmployeeLink entTlBatchEmployeeLink,String batchId,String deptId,String empFilter)throws Exception
	{
		CommonMessage.debugMsg("inside DAoIml"+batchId +"   "+ deptId+"   "+ empFilter );
		EntTlBatchEmployeeLinkSql entTlBatchEmployeeLinkSql=new EntTlBatchEmployeeLinkSql();
		String sql = entTlBatchEmployeeLinkSql.getBatchEmployeeViewsql(entTlBatchEmployeeLink,batchId,deptId,empFilter);
		return dbActionTemplate.getDataList(sql);
	}
	
	
	
	public List<EntTlBatchEmployeeLink> createBatchEmployee(List<EntTlBatchEmployeeLink> entTlBatchEmployeeLink,String batchId) 	throws Exception {

		EntTlBatchEmployeeLinkSql entTlBatchEmployeeLinkSql = new EntTlBatchEmployeeLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
			List<String> sqls = new ArrayList<String>();
			List <EntTlBatchEmployeeLink> methodslist = entTlBatchEmployeeLink;
			//CommonMessage.debugMsg("methodslist.size() in dao impl"+methodslist.size());
			////CommonMessage.debugMsg("batchId in dao impl"+batchId);
			
			sqls.add(EntTlBatchEmployeeLinkSql.getDeleteSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(),batchId));
			GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(), EntTlBatchEmployeeLinkSql.TBL_ENT_TL_BATCH_EMPLOYEE_LINK, 9, "BST", null,null);
		//	CommonMessage.debugMsg("sequenceNumber"+sequenceNumber);
			//if(methodslist != null && methodslist.size()>0)
			//{
				CommonMessage.debugMsg("sequenceNumber in IF"+sequenceNumber);
				for(EntTlBatchEmployeeLink empBatchLink:methodslist)
				{
					//CommonMessage.debugMsg("empBatchLink.getBstdbachkeyid():" +empBatchLink.getBstdbachkeyid());
					//CommonMessage.debugMsg("empBatchLink.getBstdkeyid():" +empBatchLink.getBstdkeyid());
					//CommonMessage.debugMsg("empBatchLink.getBstdempmkeyid():" +empBatchLink.getBstdempmkeyid());
					
					//if(UIUtils.isValidKeyId(empBatchLink.getBstdkeyid()))
				//	{
				//	sqls.add(EntTlBatchEmployeeLinkSql.getEmpDeleteSql(empBatchLink.getBstdbachkeyid(),empBatchLink.getBstdempmkeyid()));	
						
				//	}
					
					
					//if(!UIUtils.isValidKeyId(empBatchLink.getBstdkeyid())){
						String seqNo=sequenceNumber.getSequnceNumber();
					//	CommonMessage.debugMsg("seqNo in dao "+seqNo);
						empBatchLink.setBstdkeyid(seqNo);
					//	CommonMessage.debugMsg("empBatchLink:"+empBatchLink.getBstdbachkeyid());
						sqls.add(EntTlBatchEmployeeLinkSql .getInsertSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(), empBatchLink.getSaveArray()));
					//}
					//else{
					//	sqls.add(EntTlBatchEmployeeLinkSql .getUpdateSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(), empBatchLink.getSaveArray()));
					//}
						
				}
			//}
			dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		return null;
	}
	
	public void deleteByBstdKeyId(String bstdKeyid) throws Exception
	{
		StringBuilder sql = new StringBuilder("DELETE from ENT_TL_BATCH_EMPLOYEE_LINK ");
		sql.append("WHERE BSTD_KEYID='").append(bstdKeyid).append("'");
		CommonMessage.debugMsg("Delete sql:" + sql);
		try {
			
			dbActionTemplate.executeStatement(sql.toString());
			
		}catch( Exception e){
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		
	}
	/*public EntTlBatchEmployeeLink deleteBatchEmployee(EntTlBatchEmployeeLink entTlBatchEmployeeLink)
	throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlBatchEmployeeLinkSql entTlBatchEmployeeLinkSql = new EntTlBatchEmployeeLinkSql();
		try {
			
			sqls.add(entTlBatchEmployeeLinkSql.getDeleteSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(), entTlBatchEmployeeLink.getSaveArray()));
		
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlBatchEmployeeLink;
		}*/
	public EntTlBatchEmployeeLink updateBatchEmployee(EntTlBatchEmployeeLink entTlBatchEmployeeLink)	throws Exception { 
			
			List<String> sqls = new ArrayList<String>();
			EntTlBatchEmployeeLinkSql entTlBatchEmployeeLinkSql = new EntTlBatchEmployeeLinkSql();
			try {
	
				sqls.add(EntTlBatchEmployeeLinkSql.getUpdateSql(entTlBatchEmployeeLinkSql.getEnt_DbFields(), entTlBatchEmployeeLink.getSaveArray()));
				
				dbActionTemplate.executeStatements(sqls);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception(e.getMessage());
			}
			
			return entTlBatchEmployeeLink;
		}
	@Override
	public List<String[]> getFacultylist(String programKeyId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			String sql = EntTlBatchFacultyLinkSql.getFacultyViewsql(programKeyId);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
 }
