package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class DocTlRoleRightsSql {

	public static final String TBL_DOC_TL_ROLE_RIGHTS = "DOC_TL_ROLE_RIGHTS";  

	TableFieldType [] rlriDbFields = null;

	public enum   tableFldConstants
	{
		keyid, role_keyid, docid, rights, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getRlriDbFields() {
		return rlriDbFields;
	}

	public DocTlRoleRightsSql()
	{
		rlriDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			rlriDbFields[ i ] = new TableFieldType();
		}
		rlriDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "RLRI_KEYID";
		rlriDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		rlriDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "RLRI_ROLE_KEYID";
		rlriDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		rlriDbFields[ tableFldConstants.docid.ordinal() ].fieldName = "RLRI_DOCID";
		rlriDbFields[ tableFldConstants.docid.ordinal() ].fieldType = 'V';

		rlriDbFields[ tableFldConstants.rights.ordinal() ].fieldName = "RLRI_RIGHTS";
		rlriDbFields[ tableFldConstants.rights.ordinal() ].fieldType = 'N';

		rlriDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "RLRI_TEMPFIELD1";
		rlriDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		rlriDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "RLRI_TEMPFIELD2";
		rlriDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		rlriDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "RLRI_TEMPFIELD3";
		rlriDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		rlriDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "RLRI_TEMPFIELD4";
		rlriDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		rlriDbFields[ tableFldConstants.active.ordinal() ].fieldName = "RLRI_ACTIVE";
		rlriDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rlriDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "RLRI_CREATEDBY";
		rlriDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rlriDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "RLRI_CREATEDON";
		rlriDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rlriDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "RLRI_MODIFIEDON";
		rlriDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_DOC_TL_ROLE_RIGHTS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_DOC_TL_ROLE_RIGHTS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_DOC_TL_ROLE_RIGHTS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getUserRightsSql(String id)
	{
		CommonMessage.debugMsg(id.indexOf(":"));
		String userId=null;
		String empId=null;
		if(id.indexOf(":")>0)
		{
			String[] datas = id.split(":");
			id=datas[0];
			userId = datas[1];
			if(datas.length >2)
				empId = datas[2];
		}
		
		String sql = "SELECT RLRI_KEYID,RLRI_ROLE_KEYID,ROLE_NAME,bitand( rlri_rights,1) vie,bitand( rlri_rights,2) as modifys,";
			   sql += "bitand( rlri_rights,4) as deletes,bitand( rlri_rights,8) download,bitand( rlri_rights,16) as UserRights";
			   sql += " FROM "+TBL_DOC_TL_ROLE_RIGHTS+","+TableNames.TBL_ADM_TL_ROLEMST;
			   if(CommonFunctions.isValidKeyId(userId))
			   {
				   sql += ","+TableNames.TBL_ADM_TL_USER_ROLE_LINK;				   
			   }
			   sql += " WHERE ROLE_KEYID=RLRI_ROLE_KEYID ";
			   if(CommonFunctions.isValidKeyId(userId))
			   {
				   sql += "AND Rlri_Role_Keyid = Arul_Roleid ";
				   sql += "AND ARUL_USERID= '"+userId+"' ";
			   }
			   sql +="AND RLRI_DOCID='"+id+"'";
			   if(CommonFunctions.isValidKeyId(userId))
			   {
				   sql +=" UNION ";
				   sql +="SELECT '' RLRI_KEYID, ROLE_KEYID, ROLE_NAME, 1 AS VIE, 2 AS MODIFYS, 4 AS DELETES, 8 AS DOWNLOAD, 16 AS USERRIGHTS";
				   sql +=" FROM Dcm_Tl_Documentlayout,"+TableNames.TBL_ADM_TL_USER_ROLE_LINK+","+TableNames.TBL_ADM_TL_ROLEMST;
				   sql +=" WHERE DMLY_KEYID = '"+id+"' AND DMLY_CREATEDBY = '"+empId+"'";
				   sql +=" AND ARUL_USERID = '"+userId+"' AND ARUL_ROLEID = ROLE_KEYID";
			   }
		return sql;
	}
	public static String checkRightsExist(String id)
	{
		String sql = "SELECT COUNT(*) FROM "+TBL_DOC_TL_ROLE_RIGHTS;
			   sql += " WHERE RLRI_DOCID='"+id+"'";
		return sql;
	}
	public static String delRights(String id)
	{
		String sql = "DELETE "+TBL_DOC_TL_ROLE_RIGHTS;
			   sql += " WHERE RLRI_DOCID='"+id+"'";
		return sql;
	}

}

