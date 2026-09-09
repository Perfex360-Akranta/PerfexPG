package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.CommonFilter;

public class GenTlMomattendanceSql {

	public static final String TBL_GEN_TL_MOMATTENDANCE = "GEN_TL_MOMATTENDANCE";  

	TableFieldType [] momaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, moms_keyid, flid, date, employeeid, attandance, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMomaDbFields() {
		return momaDbFields;
	}

	public GenTlMomattendanceSql()
	{
		momaDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			momaDbFields[ i ] = new TableFieldType();
		}
		momaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOMA_KEYID";
		momaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldName = "MOMA_MOMS_KEYID";
		momaDbFields[ tableFldConstants.moms_keyid.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MOMA_FLID";
		momaDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.date.ordinal() ].fieldName = "MOMA_DATE";
		momaDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		momaDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "MOMA_EMPLOYEEID";
		momaDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.attandance.ordinal() ].fieldName = "MOMA_ATTANDANCE";
		momaDbFields[ tableFldConstants.attandance.ordinal() ].fieldType = 'C';

		momaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOMA_TEMPFIELD1";
		momaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOMA_TEMPFIELD2";
		momaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOMA_TEMPFIELD3";
		momaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOMA_TEMPFIELD4";
		momaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOMA_TEMPFIELD5";
		momaDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOMA_ACTIVE";
		momaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		momaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOMA_CREATEDBY";
		momaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		momaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOMA_CREATEDON";
		momaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		momaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOMA_MODIFIEDON";
		momaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_MOMATTENDANCE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_MOMATTENDANCE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_MOMATTENDANCE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String ATTGrid(CommonFilter commonFilter)
	{
		String sql=" SELECT MOMA_KEYID,'',FNLN_DISPLAYCODE,EMPM_NAME,DECODE(MOMA_ATTANDANCE,'P','PRESENT','A','ABSENT','L','LEAVE')";
	   sql+=" FROM GEN_TL_MOMATTENDANCE,GEN_TL_EMPLOYEEMST,GEN_TL_FUNCTIONALLOCN";
	    sql+=" WHERE MOMA_FLID = FNLN_ELEMENTID AND MOMA_EMPLOYEEID = EMPM_KEYID ";
	        
		 return sql.toString();
	}

}

