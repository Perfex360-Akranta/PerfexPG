package com.akranta.tpm.dao.sql;

public class GenTlTeamDoucmentLinkSql {

	public static final String TBL_GEN_TL_TEAM_DOUCMENT_LINK = "GEN_TL_TEAM_DOUCMENT_LINK";  

	TableFieldType [] tmdlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, docno, doctype, teamid, emptype, temp2, temp3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getTmdlDbFields() {
		return tmdlDbFields;
	}

	public GenTlTeamDoucmentLinkSql()
	{
		tmdlDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			tmdlDbFields[ i ] = new TableFieldType();
		}
		tmdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TMDL_KEYID";
		tmdlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.docno.ordinal() ].fieldName = "TMDL_DOCNO";
		tmdlDbFields[ tableFldConstants.docno.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "TMDL_DOCTYPE";
		tmdlDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.teamid.ordinal() ].fieldName = "TMDL_TEAMID";
		tmdlDbFields[ tableFldConstants.teamid.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.emptype.ordinal() ].fieldName = "TMDL_EMPTYPE";
		tmdlDbFields[ tableFldConstants.emptype.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "TMDL_TEMP2";
		tmdlDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "TMDL_TEMP3";
		tmdlDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "TMDL_ACTIVE";
		tmdlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		tmdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TMDL_CREATEDBY";
		tmdlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		tmdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TMDL_CREATEDON";
		tmdlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		tmdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TMDL_MODIFIEDON";
		tmdlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_TEAM_DOUCMENT_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_TEAM_DOUCMENT_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TEAM_DOUCMENT_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDelete(String docKeyid, String empType)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TEAM_DOUCMENT_LINK ;		
		sql += " where TMDL_DOCNO = '" + docKeyid + "' ";
		//sql += " and TMDL_EMPTYPE = '" + empType + "' ";
		
		return sql;
	}
}

