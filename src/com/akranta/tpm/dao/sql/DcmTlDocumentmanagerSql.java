package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class DcmTlDocumentmanagerSql {

	public static final String TBL_DCM_TL_DOCUMENTMANAGER = "DCM_TL_DOCUMENTMANAGER";  

	TableFieldType [] dmdmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, refdocno, refdoctype, isodoctype, slno, filename, description
		, keywords, bloblength, blobfile, category, owner, approvedby
		, subjectarea, title, path, type,  active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDmdmDbFields() {
		return dmdmDbFields;
	}

	public DcmTlDocumentmanagerSql()
	{
		dmdmDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			dmdmDbFields[ i ] = new TableFieldType();
		}
		dmdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMDM_KEYID";
		dmdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "DMDM_REFDOCNO";
		dmdmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "DMDM_REFDOCTYPE";
		dmdmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.isodoctype.ordinal() ].fieldName = "DMDM_ISODOCTYPE";
		dmdmDbFields[ tableFldConstants.isodoctype.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "DMDM_SLNO";
		dmdmDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		dmdmDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "DMDM_FILENAME";
		dmdmDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "DMDM_DESCRIPTION";
		dmdmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.keywords.ordinal() ].fieldName = "DMDM_KEYWORDS";
		dmdmDbFields[ tableFldConstants.keywords.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "DMDM_BLOBLENGTH";
		dmdmDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		dmdmDbFields[ tableFldConstants.blobfile.ordinal() ].fieldName = "DMDM_BLOBFILE";
		dmdmDbFields[ tableFldConstants.blobfile.ordinal() ].fieldType = 'B';

		dmdmDbFields[ tableFldConstants.category.ordinal() ].fieldName = "DMDM_CATEGORY";
		dmdmDbFields[ tableFldConstants.category.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.owner.ordinal() ].fieldName = "DMDM_OWNER";
		dmdmDbFields[ tableFldConstants.owner.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "DMDM_APPROVEDBY";
		dmdmDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.subjectarea.ordinal() ].fieldName = "DMDM_SUBJECTAREA";
		dmdmDbFields[ tableFldConstants.subjectarea.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.title.ordinal() ].fieldName = "DMDM_TITLE";
		dmdmDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.path.ordinal() ].fieldName = "DMDM_PATH";
		dmdmDbFields[ tableFldConstants.path.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "DMDM_TYPE";
		dmdmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

	/*	dmdmDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "DMDM_TEMP2";
		dmdmDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "DMDM_TEMP3";
		dmdmDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';*/

		dmdmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMDM_ACTIVE";
		dmdmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMDM_CREATEDBY";
		dmdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMDM_CREATEDON";
		dmdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMDM_MODIFIEDON";
		dmdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DCM_TL_DOCUMENTMANAGER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DCM_TL_DOCUMENTMANAGER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DCM_TL_DOCUMENTMANAGER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		CommonMessage.debugMsg("deleteFileManager    "+sql);
		return sql;
	}
	public static String getDocMgr()
	{
		String sql = "Select * from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_ISODOCTYPE = ?";
		return sql;
	}
	public static String searchFileSql(String keywords, String fromDate, String toDate, String title, String subjectArea, 
						String category, String owner, String changes,String description, String approvedBy,String type,String condSql)
	{
		String sql = "Select DISTINCT DMDM_KEYID,DMDM_REFDOCNO,DMDM_REFDOCTYPE,DMDM_ISODOCTYPE,DMDM_SLNO,DMDM_FILENAME,";
			   sql +="DMDM_DESCRIPTION,DMDM_KEYWORDS,DMCM_NAME AS CATEGORY, DMDM_OWNER,DMDM_APPROVEDBY, DSAM_NAME AS SUBJECTAREA ,";
			   sql += "DMDM_TITLE,DMDM_PATH, DMDM_TYPE, DMDM_CREATEDBY,DMDM_CREATEDON,DMDM_MODIFIEDON ";
			   sql += "from "+TBL_DCM_TL_DOCUMENTMANAGER+" , "+TableNames.TBL_DCM_TL_CATEGORYMST+", "+TableNames.TBL_DCM_TL_SUBJECTAREAMST;
			   if (UIUtils.isValidKeyId(condSql))
				   sql += ","+DocTlTemplateDefvalDtlSql.TBL_DOC_TL_TEMPLATE_DEFVAL_DTL;
			   sql += " WHERE 1=1 ";
			   
			   sql += " AND DMDM_CATEGORY = DMCM_KEYID (+)";
			   sql += " AND DMDM_SUBJECTAREA = DSAM_KEYID (+) ";
			   if (UIUtils.isValidKeyId(condSql))
				   sql += " And Dmdm_KEYID = Dtpv_DMDm_Keyid (+) ";
			   if (UIUtils.isValidKeyId(keywords))
				   sql +=" and  DMDM_KEYWORDS like  '%"+keywords+"%'";

			   if (UIUtils.isValidDate(fromDate))
				   sql +=" and DMDM_CREATEDON >= '"+fromDate+"' ";

			   if (UIUtils.isValidDate(toDate))
				   sql +=" and DMDM_CREATEDON <= '"+toDate+"' ";

			   if (UIUtils.isValidKeyId(title))
				   sql +=" and DMDM_TITLE like  '%"+title+"%'";
			   if (UIUtils.isValidKeyId(subjectArea))
				   sql +=" and DMDM_SUBJECTAREA ='"+subjectArea+"'" ;
			   if (UIUtils.isValidKeyId(category))
				   sql +=" and  DMDM_CATEGORY  ='"+category+"'" ;
			   if (UIUtils.isValidKeyId(owner))
				   sql +=" and DMDM_OWNER  ='"+owner+"'" ;
			   //if (UIUtils.isValidKeyId(changes))
			//	   sql +=" where DMDM_TITLE like  '%"+changes+"%'";
			   if (UIUtils.isValidKeyId(approvedBy))
				   sql +=" and DMDM_OWNER  ='"+approvedBy+"'" ;
			   
			   if (UIUtils.isValidKeyId(type))
				   sql +=" and DMDM_TYPE  ='"+type+"'" ;
			   
			   if (UIUtils.isValidKeyId(condSql))
				   sql += condSql;


		return sql;
	}
	public static String getFileCount(String keywords)
	{
		String sql = "Select  count(*) from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_KEYWORDS like '%"+keywords+"%'";
		return sql;
	}
	public static String getFileDetails()
	{
		String sql = "Select * from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_keyid = ?";
		return sql;
	}
	public static String getDocMgrFromFileName()
	{
		String sql = "Select * from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_FILENAME = ?";
		return sql;
	}
	public static String getSlNo(String docType)
	{
		String sql = "Select  count(*) from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_ISODOCTYPE = '"+docType+"'";
		return sql;
	}
	public static String getSlNoFromFileName(String docType)
	{
		String sql = "Select  count(*) from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_FILENAME = '"+docType+"'";
		return sql;
	}
	public static String getPathSql(String id)
	{
		String sql = "Select DMDM_DESCRIPTION from "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_ISODOCTYPE = '"+id+"'";
		return sql;
	}
	public static String updatePathSql(String id,String path)
	{
		String sql = "UPDATE "+TBL_DCM_TL_DOCUMENTMANAGER+" SET DMDM_DESCRIPTION ='"+path+"' WHERE DMDM_ISODOCTYPE='"+id+"'";
		return sql;
	}
	public static String delChild(String id)
	{
		String sql = "DELETE  "+TBL_DCM_TL_DOCUMENTMANAGER+" where DMDM_ISODOCTYPE = '"+id+"'";
		return sql;
	}
	
	

}

