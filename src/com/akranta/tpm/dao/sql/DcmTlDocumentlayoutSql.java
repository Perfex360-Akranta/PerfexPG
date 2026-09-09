package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class DcmTlDocumentlayoutSql {

	public static final String TBL_DCM_TL_DOCUMENTLAYOUT = "DCM_TL_DOCUMENTLAYOUT";  

	TableFieldType [] dmlyDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, parentid, levelno, displayorder, isfileavl, isparent
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDmlyDbFields() {
		return dmlyDbFields;
	}

	public DcmTlDocumentlayoutSql()
	{
		dmlyDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			dmlyDbFields[ i ] = new TableFieldType();
		}
		dmlyDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DMLY_KEYID";
		dmlyDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dmlyDbFields[ tableFldConstants.name.ordinal() ].fieldName = "DMLY_NAME";
		dmlyDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		dmlyDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "DMLY_PARENTID";
		dmlyDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		dmlyDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "DMLY_LEVELNO";
		dmlyDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		dmlyDbFields[ tableFldConstants.displayorder.ordinal() ].fieldName = "DMLY_DISPLAYORDER";
		dmlyDbFields[ tableFldConstants.displayorder.ordinal() ].fieldType = 'N';

		dmlyDbFields[ tableFldConstants.isfileavl.ordinal() ].fieldName = "DMLY_ISFILEAVL";
		dmlyDbFields[ tableFldConstants.isfileavl.ordinal() ].fieldType = 'C';

		dmlyDbFields[ tableFldConstants.isparent.ordinal() ].fieldName = "DMLY_ISPARENT";
		dmlyDbFields[ tableFldConstants.isparent.ordinal() ].fieldType = 'C';

		dmlyDbFields[ tableFldConstants.active.ordinal() ].fieldName = "DMLY_ACTIVE";
		dmlyDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		dmlyDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DMLY_CREATEDBY";
		dmlyDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dmlyDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DMLY_CREATEDON";
		dmlyDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dmlyDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DMLY_MODIFIEDON";
		dmlyDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DCM_TL_DOCUMENTLAYOUT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DCM_TL_DOCUMENTLAYOUT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DCM_TL_DOCUMENTLAYOUT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getDocTreeSql(DcmTlDocumentlayout dcmTlDocumentlayout)
	{
		String parentId = dcmTlDocumentlayout.getDmlyParentid();
		String id = dcmTlDocumentlayout.getDmlyKeyid();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DMLY_KEYID,DMLY_DISPLAYORDER,DMLY_PARENTID,DMLY_LEVELNO,DMLY_NAME");
		sb.append(" FROM "+TBL_DCM_TL_DOCUMENTLAYOUT+" WHERE 1=1");
		if (CommonFunctions.isValidKeyId(parentId))
		{
			if(parentId.equals("1"))
				sb.append(" AND DMLY_KEYID=DMLY_PARENTID ");
				
			else
			{
				sb.append(" AND DMLY_KEYID<>DMLY_PARENTID ");
				sb.append(" AND DMLY_PARENTID='" + id + "'");
			}
		}
		sb.append(" AND DMLY_ACTIVE = 'Y'");
		return sb.toString();
	}
	public static String getParentSql(String parentId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT DMLY_KEYID,DMLY_PARENTID,DMLY_NAME");
		sb.append(" FROM "+TBL_DCM_TL_DOCUMENTLAYOUT+" WHERE 1=1");
		if (CommonFunctions.isValidKeyId(parentId))
		{
			sb.append(" AND DMLY_KEYID='" + parentId + "'");
		}
		sb.append(" AND DMLY_ACTIVE = 'Y'");
		return sb.toString();
	}
	public static String updateIsParent(String parentId)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE "+TBL_DCM_TL_DOCUMENTLAYOUT+" SET DMLY_ISPARENT = 'Y' WHERE DMLY_KEYID='"+parentId+"'");
		return sb.toString();
	}
	public static String getFolderCount(String keyId)
	{
		String sql = "Select  count(*) from "+TBL_DCM_TL_DOCUMENTLAYOUT+" where DMLY_NAME = '"+keyId+"'";
		return sql;
	}
	public static String getDocLayout()
	{
		String sql = "Select * from "+TBL_DCM_TL_DOCUMENTLAYOUT+" where DMLY_NAME = ?";
		return sql;
	}
	public static String getChild(String id)
	{
		String sql = "Select DMLY_keyid from "+TBL_DCM_TL_DOCUMENTLAYOUT+" where DMLY_parentid = '"+id+"'";
		return sql;
	}
	public static String isParent(String id)
	{
		String sql = "Select DMLY_ISPARENT from "+TBL_DCM_TL_DOCUMENTLAYOUT+" where DMLY_keyid = '"+id+"'";
		return sql;
	}
	public static String delChild(String id)
	{
		String sql = "DELETE  "+TBL_DCM_TL_DOCUMENTLAYOUT+" where DMLY_KEYID = '"+id+"'";
		return sql;
	}
	public static String updateFileAvl(String id)
	{
		String sql = "UPDATE "+TBL_DCM_TL_DOCUMENTLAYOUT+" SET DMLY_ISFILEAVL='Y' where DMLY_KEYID = '"+id+"'";
		return sql;
	}


}

