package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlMomdtlSql {

	public static final String TBL_GEN_TL_MOMDTL = "GEN_TL_MOMDTL"; 
	public static final String TBL_GEN_TL_MOMATTENDANCE = "GEN_TL_MOMATTENDANCE";  

	TableFieldType [] momdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, momskeyid, discussiontype, discussiondetails, actionplanid
		, remarks,pillar, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMomdDbFields() {
		return momdDbFields;
	}

	public GenTlMomdtlSql()
	{
		momdDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			momdDbFields[ i ] = new TableFieldType();
		}
		momdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOMD_KEYID";
		momdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.momskeyid.ordinal() ].fieldName = "MOMD_MOMS_KEYID";
		momdDbFields[ tableFldConstants.momskeyid.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.discussiontype.ordinal() ].fieldName = "MOMD_DISCUSSION_TYPE";
		momdDbFields[ tableFldConstants.discussiontype.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.discussiondetails.ordinal() ].fieldName = "MOMD_DISCUSSION_DETAILS";
		momdDbFields[ tableFldConstants.discussiondetails.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldName = "MOMD_ACTIONPLAN_ID";
		momdDbFields[ tableFldConstants.actionplanid.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MOMD_REMARKS";
		momdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		momdDbFields[ tableFldConstants.pillar.ordinal() ].fieldName = "MOMD_PILLAR";
		momdDbFields[ tableFldConstants.pillar.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOMD_TEMPFIELD1";
		momdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOMD_TEMPFIELD2";
		momdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOMD_TEMPFIELD3";
		momdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOMD_TEMPFIELD4";
		momdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOMD_TEMPFIELD5";
		momdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOMD_ACTIVE";
		momdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		momdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOMD_CREATEDBY";
		momdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		momdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOMD_CREATEDON";
		momdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		momdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOMD_MODIFIEDON";
		momdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql;
		 sql=SqlUtils.getInsertSql(TBL_GEN_TL_MOMDTL, fieldTypeArr, dataArray);
		 CommonMessage.debugMsg("Insert detail Sql Query"+sql);
		 return sql;
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MOMDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String mstkeyid,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MOMDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		sql +="  AND MOMD_MOMS_KEYID='"+mstkeyid+"'";
		return sql;
	}

	public static String DeleteMomRow(String keyid)
	{
		// TODO Auto-generated method stub
		String sql=" DELETE FROM " + TBL_GEN_TL_MOMDTL + " WHERE MOMD_KEYID ='"+keyid+"'";
		CommonMessage.debugMsg("Delete Row Sql: "+sql);
		return sql;
	}

	public static String DeleteATTRow(String keyid)
	{
		String sql=" DELETE FROM " +TBL_GEN_TL_MOMATTENDANCE + " WHERE MOMA_KEYID ='"+keyid+"'";
		
		CommonMessage.debugMsg("sql:: "+sql);
		return sql;
	}

	public static String getUpdateApl(String dtlKeyid, String aplKeyid) {
		String sql=" UPDATE GEN_TL_MOMDTL SET MOMD_ACTIONPLAN_ID='"+aplKeyid+"' WHERE MOMD_KEYID='"+dtlKeyid+"' ";
		return sql;
	}

}

