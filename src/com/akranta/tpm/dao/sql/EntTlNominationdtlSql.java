package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.sql.EntTlNominationmstSql.tableFldConstants;

public class EntTlNominationdtlSql {

	public static final String TBL_ENT_TL_NOMINATIONDTL = "ENT_TL_NOMINATIONDTL";  

	TableFieldType [] noddDbFields = null;

	public enum   tableFldConstants
	{
		keyid, nomm_keyid, empm_keyid, cur_keyid, tar_keyid, lasttrn_date
		, mailsent, mailsent_date, isapproved, approved_by, approved_date
		, approved_remarks, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getNoddDbFields() {
		return noddDbFields;
	}

	public EntTlNominationdtlSql()
	{
		noddDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			noddDbFields[ i ] = new TableFieldType();
		}
		noddDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NODD_KEYID";
		noddDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.nomm_keyid.ordinal() ].fieldName = "NODD_NOMM_KEYID";
		noddDbFields[ tableFldConstants.nomm_keyid.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldName = "NODD_EMPM_KEYID";
		noddDbFields[ tableFldConstants.empm_keyid.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.cur_keyid.ordinal() ].fieldName = "NODD_CUR_KEYID";
		noddDbFields[ tableFldConstants.cur_keyid.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.tar_keyid.ordinal() ].fieldName = "NODD_TAR_KEYID";
		noddDbFields[ tableFldConstants.tar_keyid.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.lasttrn_date.ordinal() ].fieldName = "NODD_LASTTRN_DATE";
		noddDbFields[ tableFldConstants.lasttrn_date.ordinal() ].fieldType = 'D';

		noddDbFields[ tableFldConstants.mailsent.ordinal() ].fieldName = "NODD_MAILSENT";
		noddDbFields[ tableFldConstants.mailsent.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.mailsent_date.ordinal() ].fieldName = "NODD_MAILSENT_DATE";
		noddDbFields[ tableFldConstants.mailsent_date.ordinal() ].fieldType = 'D';

		noddDbFields[ tableFldConstants.isapproved.ordinal() ].fieldName = "NODD_ISAPPROVED";
		noddDbFields[ tableFldConstants.isapproved.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.approved_by.ordinal() ].fieldName = "NODD_APPROVED_BY";
		noddDbFields[ tableFldConstants.approved_by.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.approved_date.ordinal() ].fieldName = "NODD_APPROVED_DATE";
		noddDbFields[ tableFldConstants.approved_date.ordinal() ].fieldType = 'D';

		noddDbFields[ tableFldConstants.approved_remarks.ordinal() ].fieldName = "NODD_APPROVED_REMARKS";
		noddDbFields[ tableFldConstants.approved_remarks.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "NODD_TEMPFIELD1";
		noddDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "NODD_TEMPFIELD2";
		noddDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "NODD_TEMPFIELD3";
		noddDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "NODD_TEMPFIELD4";
		noddDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "NODD_TEMPFIELD5";
		noddDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NODD_ACTIVE";
		noddDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		noddDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NODD_CREATEDBY";
		noddDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		noddDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NODD_CREATEDON";
		noddDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		noddDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NODD_MODIFIEDON";
		noddDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_NOMINATIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_NOMINATIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_NOMINATIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " IN (" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + ")";
		return sql;
	}
	
	public static String getUpdateAppSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "UPDATE " + TBL_ENT_TL_NOMINATIONDTL ;
		
		sql += " SET NODD_ISAPPROVED='N',NODD_APPROVED_BY='{}',NODD_APPROVED_DATE=to_date( '" +(String)Constants.futureNullDate+ "','dd-Mon-yyyy hh24:mi:ss'),NODD_APPROVED_REMARKS='{}' where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " IN (" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + ")";
		return sql;
	}
	public static String getUpdateAppMstSql(String nommKeyId)
	{
		String sql = "UPDATE " + TBL_ENT_TL_NOMINATIONDTL ;
		
		sql += " SET NODD_ISAPPROVED='N',NODD_APPROVED_BY='{}',NODD_APPROVED_DATE=to_date( '" +(String)Constants.futureNullDate+ "','dd-Mon-yyyy hh24:mi:ss'),NODD_APPROVED_REMARKS='{}' " +
				" where NODD_NOMM_KEYID='"+ nommKeyId + "'";
		return sql;
	}
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_ENT_TL_NOMINATIONDTL +" where ROWNUM=1 AND NODD_ISAPPROVED='Y' and NODD_NOMM_KEYID=?  ORDER BY NODD_KEYID DESC ";
		return sql;
	}
}

