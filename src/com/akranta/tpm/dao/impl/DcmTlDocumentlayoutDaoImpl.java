package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DcmTlDocumentlayoutDao;
import com.akranta.tpm.dao.sql.DcmTlDocumentlayoutSql;
import com.akranta.tpm.dao.sql.DcmTlDocumentmanagerSql;
import com.akranta.tpm.dao.sql.DocTlRoleRightsSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class DcmTlDocumentlayoutDaoImpl implements DcmTlDocumentlayoutDao {


	private DBActionTemplate dbActionTemplate;
	 private static final String DocManagerLayout_Id = "DM001";

	public DcmTlDocumentlayoutDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public DcmTlDocumentlayout create(DcmTlDocumentlayout dcmTlDocumentlayout) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		DcmTlDocumentlayoutSql dcmTlDocumentlayoutSql = new DcmTlDocumentlayoutSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			
			dcmTlDocumentlayout.setDmlyKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentlayoutSql.TBL_DCM_TL_DOCUMENTLAYOUT, 8, "DML", "YYMM", "Y")); // set the sequnce number
			if(CommonFunctions.isValidKeyId(dcmTlDocumentlayout.getDmlyParentid()))
			{
				if(dcmTlDocumentlayout.getDmlyParentid().equals(DocManagerLayout_Id))
					dcmTlDocumentlayout.setDmlyParentid(dcmTlDocumentlayout.getDmlyKeyid());
				else
					sqls.add(DcmTlDocumentlayoutSql.updateIsParent(dcmTlDocumentlayout.getDmlyParentid()));
			}
			sqls.add(DcmTlDocumentlayoutSql.getInsertSql(dcmTlDocumentlayoutSql.getDmlyDbFields(), dcmTlDocumentlayout.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return dcmTlDocumentlayout;
	}
	
	public DcmTlDocumentlayout update(DcmTlDocumentlayout dcmTlDocumentlayout)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		DcmTlDocumentlayoutSql dcmTlDocumentlayoutSql = new DcmTlDocumentlayoutSql();
		DcmTlDocumentmanagerSql dcmTlDocumentmanagerSql = new DcmTlDocumentmanagerSql();
		try {
			String fileCountSql = dbActionTemplate.getSingleValue(DcmTlDocumentmanagerSql.getSlNo(dcmTlDocumentlayout.getDmlyKeyid()));
			CommonMessage.debugMsg(fileCountSql);
			if(Integer.parseInt(fileCountSql) > 0)
			{
				String getPathSql = DcmTlDocumentmanagerSql.getPathSql(dcmTlDocumentlayout.getDmlyKeyid());
				List <String[]> pathList = dbActionTemplate.getDataList(getPathSql);
				CommonMessage.debugMsg("Size : "+pathList.size());
				String path = pathList.get(0)[0];
				CommonMessage.debugMsg("path : "+path);
				if(CommonFunctions.isValidKeyId(path))
				{
					path = path.substring(0, path.lastIndexOf("/"));
					CommonMessage.debugMsg("path 2: "+path);
					path = path.substring(0, path.lastIndexOf("/"));
					CommonMessage.debugMsg("path 3: "+path);
					path = path + "/"+dcmTlDocumentlayout.getDmlyName()+"/";
					CommonMessage.debugMsg("path 4: "+path);
					String updatePathSql = DcmTlDocumentmanagerSql.updatePathSql(dcmTlDocumentlayout.getDmlyKeyid(),path);
					sqls.add(updatePathSql);
				}
			}
			if(CommonFunctions.isValidKeyId(dcmTlDocumentlayout.getDmlyCreatedby()))
				dcmTlDocumentlayout.setDmlyCreatedby(dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_EMPLOYEEMST, "EMPM_KEYID", "EMPM_NAME", dcmTlDocumentlayout.getDmlyCreatedby()));
			sqls.add(DcmTlDocumentlayoutSql.getUpdateSql(dcmTlDocumentlayoutSql.getDmlyDbFields(), dcmTlDocumentlayout.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return dcmTlDocumentlayout;
	}
	
	public DcmTlDocumentlayout delete(DcmTlDocumentlayout dcmTlDocumentlayout)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		DcmTlDocumentlayoutSql dcmTlDocumentlayoutSql = new DcmTlDocumentlayoutSql();
		try {
			if(UIUtils.isValidKeyId(dcmTlDocumentlayout.getDmlyKeyid()))
			{
				String keyId = dcmTlDocumentlayout.getDmlyKeyid();
				String isParent = dbActionTemplate.getSingleValue(DcmTlDocumentlayoutSql.isParent(keyId));
				String isFileavl = dbActionTemplate.getSingleValue(DcmTlDocumentlayoutSql.TBL_DCM_TL_DOCUMENTLAYOUT, "DMLY_ISFILEAVL", "DMLY_KEYID", keyId);
				String rights = dbActionTemplate.getSingleValue(DocTlRoleRightsSql.checkRightsExist(keyId));
				if(UIUtils.isValidKeyId(isParent))
				{
					if(isParent.equals("Y"))
					{
						List<String[]> getChild = dbActionTemplate.getDataList(DcmTlDocumentlayoutSql.getChild(keyId));
						for(int i=0;i<getChild.size();i++)
						{
							sqls.add(DcmTlDocumentlayoutSql.delChild(getChild.get(i)[0]));
							String rightsChild = dbActionTemplate.getSingleValue(DocTlRoleRightsSql.checkRightsExist(getChild.get(i)[0]));
							if(UIUtils.isValidKeyId(rightsChild))
							{
								if(Integer.parseInt(rightsChild)>0)
									sqls.add(DocTlRoleRightsSql.delRights(getChild.get(i)[0]));
								
							}
						}
					}
				}
				if(UIUtils.isValidKeyId(isFileavl))
				{
					if(isFileavl.equals("Y"))
					{
						
							sqls.add(DcmTlDocumentmanagerSql.delChild(keyId));
							
						
					}
				}
				if(UIUtils.isValidKeyId(rights))
				{
					if(Integer.parseInt(rights)>0)
						sqls.add(DocTlRoleRightsSql.delRights(keyId));
					
				}
			}
			sqls.add(DcmTlDocumentlayoutSql.getDeleteSql(dcmTlDocumentlayoutSql.getDmlyDbFields(), dcmTlDocumentlayout.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return dcmTlDocumentlayout;
	}
	
	public List<DcmTlDocumentlayout> getAllDocument(DcmTlDocumentlayout dcmTlDocumentlayout)throws Exception
	{
		String sql = DcmTlDocumentlayoutSql.getDocTreeSql(dcmTlDocumentlayout);
		CommonMessage.debugMsg("SQL : "+sql);
		List resultList = dbActionTemplate.getDataList(sql);		
		return fillDoc(resultList);	
	}
	public String getAllParent(String parentId,int level)throws Exception
	{
		
		String parent = "";
		String folder = "";
		CommonMessage.debugMsg(parent);
		
		while(!parent.equals(parentId))
		{
			CommonMessage.debugMsg(parent);
			CommonMessage.debugMsg(parentId);
			String sql = DcmTlDocumentlayoutSql.getParentSql(parentId);
			CommonMessage.debugMsg(sql);
			List <String[]>resultList = dbActionTemplate.getDataList(sql);
			parent = resultList.get(0)[0];
			parentId =  resultList.get(0)[1];
			folder = resultList.get(0)[2] + "/"+folder;
		}
		CommonMessage.debugMsg(folder);
		return folder;
	}
	private List<DcmTlDocumentlayout> fillDoc(List<String []> resultList) throws SQLException
	{
	
		   List<DcmTlDocumentlayout> dm = new ArrayList<DcmTlDocumentlayout>();
		   for( String [] row : resultList )
		   {
			   DcmTlDocumentlayout dcm = new DcmTlDocumentlayout();
			   dcm.setDmlyKeyid(row[0]);
			   dcm.setDmlyDisplayorder(row[1]);
			   dcm.setDmlyParentid(row[2]);
			   dcm.setDmlyLevelno(row[3]);
			   dcm.setDmlyName(row[4]);			
			   dm.add(dcm);			   
		   }
  		  return dm;
	 }
	public DcmTlDocumentlayout getFolderList(String id)throws Exception
	{
		String folderCountSql = DcmTlDocumentlayoutSql.getFolderCount(id);
		String folderCount = dbActionTemplate.getSingleValue(folderCountSql);
		
		DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();
		if(Integer.parseInt(folderCount)>0)
		{
			String sql = DcmTlDocumentlayoutSql.getDocLayout();
			CommonMessage.debugMsg(sql + "-----------"+id);
			Object args [] = new Object [] { id };
			dcmTlDocumentlayout.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			if(UIUtils.isValidKeyId(dcmTlDocumentlayout.getDmlyCreatedby()))
				dcmTlDocumentlayout.setDmlyCreatedby(dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_EMPLOYEEMST, "EMPM_NAME", "EMPM_KEYID", dcmTlDocumentlayout.getDmlyCreatedby()));
			CommonMessage.debugMsg(sql + "-----------"+id+"........."+dcmTlDocumentlayout.getDmlyKeyid());
		}
		return dcmTlDocumentlayout;
	}
	
	
	
}

