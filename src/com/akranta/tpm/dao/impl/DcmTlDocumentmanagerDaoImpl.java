package com.akranta.tpm.dao.impl;




import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.TblPtg;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DcmTlDocumentmanagerDao;
import com.akranta.tpm.dao.sql.DcmTlDocumentlayoutSql;
import com.akranta.tpm.dao.sql.DcmTlDocumentmanagerSql;
import com.akranta.tpm.dao.sql.DcmTlRevisionhistorySql;
import com.akranta.tpm.dao.sql.DocTlRoleRightsSql;
import com.akranta.tpm.dao.sql.DocTlTemplateDefvalDtlSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.DcmTlRevisionhistory;
import com.akranta.tpm.model.DocTlTemplateDefvalDtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class DcmTlDocumentmanagerDaoImpl implements DcmTlDocumentmanagerDao {


	private DBActionTemplate dbActionTemplate; 

	public DcmTlDocumentmanagerDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public DBActionTemplate getDbActionTemplate() {
		return this.dbActionTemplate ;
	}
	
	public String getSubjectArea(String subjectAreaId)throws Exception 	{
		String subjectArea;
		subjectArea = dbActionTemplate.getSingleValue(TableNames.TBL_DCM_TL_SUBJECTAREAMST,"DSAM_NAME","DSAM_KEYID",subjectAreaId);				
		return subjectArea;		
	}

	public String getCategory(String categoryId)throws Exception	{
		String category;
		category = dbActionTemplate.getSingleValue(TableNames.TBL_DCM_TL_CATEGORYMST,"DMCM_NAME","DMCM_KEYID",categoryId);				
		return category;		
	}

	
	public DcmTlDocumentmanager create(DcmTlDocumentmanager dcmTlDocumentmanager) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		DcmTlDocumentmanagerSql dcmTlDocumentmanagerSql = new DcmTlDocumentmanagerSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			boolean islayoutAvailable = true;
			DcmTlDocumentlayoutSql dcmTlDocumentlayoutSql = new DcmTlDocumentlayoutSql();
			if(CommonFunctions.isValidKeyId(dcmTlDocumentmanager.getDmdmIsodoctype()))
			{
				String slNoSql = DcmTlDocumentmanagerSql.getSlNo(dcmTlDocumentmanager.getDmdmIsodoctype());
				String slNo = dbActionTemplate.getSingleValue(slNoSql);
				
				if(CommonFunctions.isValidKeyId(slNo))
				{
					int sNo = Integer.parseInt(slNo)+1;
					dcmTlDocumentmanager.setDmdmSlno(Integer.toString(sNo));
				}
				else{
					islayoutAvailable = false;
				}
				sqls.add(DcmTlDocumentlayoutSql.updateFileAvl(dcmTlDocumentmanager.getDmdmIsodoctype()));
			}
			else{
				//if(!islayoutAvailable) 
					
				 
				DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();
				dcmTlDocumentlayout.setDmlyKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentlayoutSql.TBL_DCM_TL_DOCUMENTLAYOUT, 8, "DML", "YYMM", "Y")); // set the sequnce number
				 
					if(!UIUtils.isValidKeyId(dcmTlDocumentlayout.getDmlyParentid()) )
						dcmTlDocumentlayout.setDmlyParentid(dcmTlDocumentlayout.getDmlyKeyid());
					dcmTlDocumentlayout.setDmlyActive("Y");
					dcmTlDocumentlayout.setDmlyCreatedby(dcmTlDocumentmanager.getDmdmCreatedby());
					dcmTlDocumentlayout.setDmlyDisplayorder("2");
					dcmTlDocumentlayout.setDmlyLevelno("2");
					dcmTlDocumentlayout.setDmlyModifiedon(dcmTlDocumentmanager.getDmdmCreatedon());
					dcmTlDocumentlayout.setDmlyCreatedon(dcmTlDocumentmanager.getDmdmCreatedon());
					dcmTlDocumentlayout.setDmlyIsfileavl("N");
					dcmTlDocumentlayout.setDmlyIsparent("Y");
					dcmTlDocumentlayout.setDmlyName(dcmTlDocumentmanager.getDmdmRefdoctype());
					 CommonMessage.debugMsg("DLYNAME   "+dcmTlDocumentmanager.getDmdmRefdoctype());
				sqls.add(DcmTlDocumentlayoutSql.getInsertSql(dcmTlDocumentlayoutSql.getDmlyDbFields(), dcmTlDocumentlayout.getSaveArray())); // add insert sql for master table
			}
			
			dcmTlDocumentmanager.setDmdmKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentmanagerSql.TBL_DCM_TL_DOCUMENTMANAGER, 10, "DMM", "YYMM", "Y")); // set the sequnce number 
			sqls.add(DcmTlDocumentmanagerSql.getInsertSql(dcmTlDocumentmanagerSql.getDmdmDbFields(), dcmTlDocumentmanager.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return dcmTlDocumentmanager;
	}
	
	public DcmTlDocumentmanager update(DcmTlDocumentmanager dcmTlDocumentmanager,DcmTlRevisionhistory dcmTlRevisionhistory)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		DcmTlDocumentmanagerSql dcmTlDocumentmanagerSql = new DcmTlDocumentmanagerSql();
		DcmTlRevisionhistorySql dcmTlRevisionhistorySql = new DcmTlRevisionhistorySql();
		try {
			if(UIUtils.isValidKeyId(dcmTlRevisionhistory.getDmrhDocid()))
			{
				List<String[]> revList = dbActionTemplate.getDataList(DcmTlRevisionhistorySql.getRevNoSql(dcmTlRevisionhistory.getDmrhDocid()));
				if(revList.size()>0)
				{
					int revNo = revList.size() + 1;
					dcmTlRevisionhistory.setDmrhRevno(Integer.toString(revNo));
					dcmTlRevisionhistory.setDmrhCreatedon(revList.get(0)[0]);
				}
				else
				{
					dcmTlRevisionhistory.setDmrhRevno("1");
					dcmTlRevisionhistory.setDmrhCreatedon(dcmTlRevisionhistory.getDmrhModifiedon());
				}
				dcmTlRevisionhistory.setDmrhKeyid(dbActionTemplate.getSequenceNumber(DcmTlRevisionhistorySql.TBL_DCM_TL_REVISIONHISTORY, 10, "DMH", "YYMM", "Y"));
				sqls.add(DcmTlRevisionhistorySql.getInsertSql(dcmTlRevisionhistorySql.getDmrhDbFields(), dcmTlRevisionhistory.getSaveArray())); // add insert sql for master table
			}
			sqls.add(DcmTlDocumentmanagerSql.getUpdateSql(dcmTlDocumentmanagerSql.getDmdmDbFields(), dcmTlDocumentmanager.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return dcmTlDocumentmanager;
	}
	
	public DcmTlDocumentmanager delete(DcmTlDocumentmanager dcmTlDocumentmanager)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		DcmTlDocumentmanagerSql dcmTlDocumentmanagerSql = new DcmTlDocumentmanagerSql();
		try {
			CommonMessage.debugMsg("dcmTlDocumentmanager   "+dcmTlDocumentmanager.getDmdmKeyid());
			sqls.add(DcmTlDocumentmanagerSql.getDeleteSql(dcmTlDocumentmanagerSql.getDmdmDbFields(), dcmTlDocumentmanager.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return dcmTlDocumentmanager;
	}
	public DcmTlDocumentmanager getFolderPath(String id)throws Exception
	{
		//DcmTlDocumentmanager dcmTlDocumentmanager = dbActionTemplate.e.getSingleValue(DcmTlDocumentmanagerSql.TBL_DCM_TL_DOCUMENTMANAGER, "DMDM_KEYWORDS", "DMDM_ISODOCTYPE", id);
		
			String fileCountSql = DcmTlDocumentmanagerSql.getSlNo(id);
			if(id.indexOf(".")>0)
				fileCountSql = DcmTlDocumentmanagerSql.getSlNoFromFileName(id);
			String fileCount = dbActionTemplate.getSingleValue(fileCountSql);
		
		
		DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
		if(Integer.parseInt(fileCount)>0)
		{
			String sql = DcmTlDocumentmanagerSql.getDocMgr();
			if(id.indexOf(".")>0)
				sql = DcmTlDocumentmanagerSql.getDocMgrFromFileName();
			CommonMessage.debugMsg(sql + "-----------"+id);
			Object args [] = new Object [] { id };
			dcmTlDocumentmanager.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			if(UIUtils.isValidKeyId(dcmTlDocumentmanager.getDmdmCreatedby()))
				dcmTlDocumentmanager.setDmdmCreatedby(dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_EMPLOYEEMST, "EMPM_NAME", "EMPM_KEYID", dcmTlDocumentmanager.getDmdmCreatedby()));
			CommonMessage.debugMsg(sql + "-----------"+id+"........."+dcmTlDocumentmanager.getDmdmIsodoctype());
		}
		return dcmTlDocumentmanager;
	}
	public List<DcmTlDocumentmanager> searchFile(String keywords, String fromDate, String toDate, String title, String subjectArea, 
			String category, String owner, String changes,String description, String approvedBy,String type,String condSql)	throws Exception
	{
		//String fileCountSql = DcmTlDocumentmanagerSql.getFileCount(keywords);
		//String fileCount = dbActionTemplate.getSingleValue(fileCountSql);
		//CommonMessage.debugMsg(fileCountSql + " : "+fileCount);
		
		//if(Integer.parseInt(fileCount)>0)
		//{
			 String sql = DcmTlDocumentmanagerSql.searchFileSql( keywords,  fromDate,  toDate,  title,  subjectArea, 
					 category,  owner,  changes, description,  approvedBy,type,condSql);
			CommonMessage.debugMsg(sql);
			
			List resultList = dbActionTemplate.getDataList(sql);	
			CommonMessage.debugMsg(resultList.size());
			return fillDocMgr(resultList);	
			
			
		//}
	//	return null;
		
		
	}
	public List<String []> getRevisionHistory(String fileId) throws Exception
	{
		String sql = DcmTlRevisionhistorySql.getRevHistorySql(fileId);
		List<String[]> revList = dbActionTemplate.getDataList(sql);
		
		return revList;
		
	}
	public List<String []> getUserRights(String folderId) throws Exception
	{
		String sql = DocTlRoleRightsSql.getUserRightsSql(folderId);
		CommonMessage.debugMsg(sql);
		List<String[]> revList = dbActionTemplate.getDataList(sql);
		
		return revList;
	}
	public List<String []> getTypeKeywords(String typeId,String fileId,String flag) throws Exception
	{
		String sql = DocTlTemplateDefvalDtlSql.getTypeKeywordsSql(typeId,fileId,flag);
		CommonMessage.debugMsg(sql);
		List<String[]> revList = dbActionTemplate.getDataList(sql);
		
		return revList;
	}
	private List<DcmTlDocumentmanager> fillDocMgr(List<String []> resultList) throws SQLException
	{
	
		   List<DcmTlDocumentmanager> dm = new ArrayList<DcmTlDocumentmanager>();
		   for( String [] row : resultList )
		   {
			   DcmTlDocumentmanager dcm = new DcmTlDocumentmanager();
			   dcm.setDmdmKeyid(row[0]);
			   dcm.setDmdmRefdocno(row[1]);
			   dcm.setDmdmRefdoctype(row[2]);
			   dcm.setDmdmIsodoctype(row[3]);
			   dcm.setDmdmSlno(row[4]);	
			   dcm.setDmdmFilename(row[5]);
			   dcm.setDmdmDescription(row[6]);
			   dcm.setDmdmKeywords(row[7]);
			   dcm.setDmdmCategory(row[8].replace("{}", ""));
			   dcm.setDmdmOwner(row[9].replace("{}", ""));		
			   dcm.setDmdmApprovedby(row[10].replace("{}", ""));			   
			   dcm.setDmdmSubjectarea(row[11].replace("{}", ""));	
			   dcm.setDmdmTitle(row[12].replace("{}", ""));
			   
			   dcm.setDmdmPath(row[13].replace("{}", ""));
			   dcm.setDmdmType(row[14].replace("{}", ""));
			  /* dcm.setDmdmTemp2(row[15].replace("{}", ""));
			   dcm.setDmdmTemp3(row[16].replace("{}", ""));*/
			  
			   if(UIUtils.isValidKeyId(row[15]))
				   dcm.setDmdmCreatedby(dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_EMPLOYEEMST, "EMPM_NAME", "EMPM_KEYID", row[15]));
			   else
				   dcm.setDmdmCreatedby("{}");
			   
			   dcm.setDmdmCreatedon(row[16]);
			   dcm.setDmdmModifiedon(row[17]);
			  
			   dm.add(dcm);			   
		   }
  		  return dm;
	 }
	public DcmTlDocumentmanager getDocMgr(String id)throws Exception
	{
		DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
		String sql = DcmTlDocumentmanagerSql.getFileDetails();		
		Object args [] = new Object [] { id };
		dcmTlDocumentmanager.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return dcmTlDocumentmanager;
		
	}
	public List<ComboBox> fillSearchBoxValues(ComboFilter comboFilter) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT ");
		String selectSql = null;
		String likeSql = null ;
		if( comboFilter.getCodeField() != null )
		{
			selectSql =  comboFilter.getCodeField();
		}
		sql.append( selectSql + " text,");
		if( comboFilter.getIdField()!= null )
			sql.append("MAX("+comboFilter.getIdField() + ") id ");
		
		sql.append(" from ");
		sql.append(comboFilter.getTableName());
		sql.append(" where 1 = 1  " ) ;// + likeSql);
		
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		sql.append(" GROUP BY DMDM_KEYWORDS ");
		sql.append(" order by text ");
		ResultSet rs = null; 
		Connection connection = null;
		
		CommonMessage.debugMsg(" sql111 " + sql);
		try{
			rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox searchComb = new ComboBox();				
				searchComb.setText(rs.getString("text"));
				searchComb.setId(rs.getString("text"));
				
				comboList.add(searchComb);
			}
			return comboList;
			}finally{
			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
	
}

