package com.akranta.tpm.dao.sql;

public class MpsTlDtlSql {

	public static final String TBL_MPS_TL_DTL = "MPS_TL_DTL";  

	TableFieldType [] mpsdDbFields = null;

	public enum   tableFldConstants
	{
		resultid, mpsid, resulttype, modifiedby, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getMpsdDbFields() {
		return mpsdDbFields;
	}

	public MpsTlDtlSql()
	{
		mpsdDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			mpsdDbFields[ i ] = new TableFieldType();
		}
		mpsdDbFields[ tableFldConstants.resultid.ordinal() ].fieldName = "MPSD_RESULTID";
		mpsdDbFields[ tableFldConstants.resultid.ordinal() ].fieldType = 'V';

		mpsdDbFields[ tableFldConstants.mpsid.ordinal() ].fieldName = "MPSD_MPSID";
		mpsdDbFields[ tableFldConstants.mpsid.ordinal() ].fieldType = 'V';

		mpsdDbFields[ tableFldConstants.resulttype.ordinal() ].fieldName = "MPSD_RESULTTYPE";
		mpsdDbFields[ tableFldConstants.resulttype.ordinal() ].fieldType = 'C';

		mpsdDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "MPSD_MODIFIEDBY";
		mpsdDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		mpsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MPSD_ACTIVE";
		mpsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mpsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MPSD_CREATEDBY";
		mpsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mpsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MPSD_CREATEDON";
		mpsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mpsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MPSD_MODIFIEDON";
		mpsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MPS_TL_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MPS_TL_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.resultid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.resultid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MPS_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.resultid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.resultid.ordinal()] + "'";
		return sql;
	}

}

