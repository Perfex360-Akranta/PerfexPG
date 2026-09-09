package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class EntTlTrgCalUnqpSql {

	public static final String TBL_ENT_TL_TRGCALUNQP = "ENT_TL_TRGCALUNQP";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid, etcm_keyid, role_keyid, roledmt, rolejh, dateadd
		,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, createdby,active
		, createdon, modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EntTlTrgCalUnqpSql()
	{
		ftymDbFields = new TableFieldType[15 ];
		for(int i = 0;i < 15; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETCU_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldName = "ETCU_ETCM_KEYID";
		ftymDbFields[ tableFldConstants.etcm_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldName = "ETCU_ROLE_KEYID";
		ftymDbFields[ tableFldConstants.role_keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.roledmt.ordinal() ].fieldName = "ETCU_ROLEDMT";
		ftymDbFields[ tableFldConstants.roledmt.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.rolejh.ordinal() ].fieldName = "ETCU_ROLEJH";
		ftymDbFields[ tableFldConstants.rolejh.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldName = "ETCU_DATEADD";
		ftymDbFields[ tableFldConstants.dateadd.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETCU_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETCU_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETCU_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETCU_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETCU_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
        
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ETCU_CREATEDBY";
		ftymDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETCU_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETCU_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCU_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		CommonMessage.debugMsg(SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALUNQP, fieldTypeArr, dataArray)+" In sid the SQL");
		return SqlUtils.getInsertSql(TBL_ENT_TL_TRGCALUNQP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_TRGCALUNQP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_TRGCALUNQP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

