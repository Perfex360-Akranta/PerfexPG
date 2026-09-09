package com.akranta.tpm.dao.sql;

public class GenTlSusaParticipantmstSql {

	public static final String TBL_GEN_TL_SUSAPARTICIPANTSMST = "GEN_TL_SUSAPARTICIPANTSMST";  
  
	TableFieldType [] sustDbFields = null;

	public enum   tableFldConstants
	{
		sustkeyid, sustsusnkeyid, sustempmkeyid,
		 tempfield1, tempfield2,tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSustDbFields() {
		return sustDbFields;
	}

	public GenTlSusaParticipantmstSql()
	{
		sustDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			sustDbFields[ i ] = new TableFieldType();
		}
		sustDbFields[ tableFldConstants.sustkeyid.ordinal() ].fieldName = "SUST_KEYID";
		sustDbFields[ tableFldConstants.sustkeyid.ordinal() ].fieldType = 'V';

		sustDbFields[ tableFldConstants.sustsusnkeyid.ordinal() ].fieldName = "SUST_SUSN_KEYID";
		sustDbFields[ tableFldConstants.sustsusnkeyid.ordinal() ].fieldType = 'V';

		
		sustDbFields[ tableFldConstants.sustempmkeyid.ordinal() ].fieldName = "SUST_EMPM_KEYID";
		sustDbFields[ tableFldConstants.sustempmkeyid.ordinal() ].fieldType = 'V';

		
		sustDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SUST_TEMPFIELD1";
		sustDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		sustDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SUST_TEMPFIELD2";
		sustDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		sustDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SUST_TEMPFIELD3";
		sustDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		sustDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SUST_ACTIVE";
		sustDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sustDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SUST_CREATEDBY";
		sustDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sustDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SUST_CREATEDON";
		sustDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sustDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SUST_MODIFIEDON";
		sustDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SUSAPARTICIPANTSMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SUSAPARTICIPANTSMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.sustkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.sustkeyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SUSAPARTICIPANTSMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.sustkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.sustkeyid.ordinal()] + "'";
		return sql;
	}

	public String getselectsql() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM GEN_TL_SUSAPARTICIPANTMST WHERE SUSP_KEYID = ?";
		return sql;
	}

}

