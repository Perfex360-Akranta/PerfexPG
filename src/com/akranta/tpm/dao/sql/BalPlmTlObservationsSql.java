package com.akranta.tpm.dao.sql;

public class BalPlmTlObservationsSql {

	public static final String TBL_PLM_TL_OBSERVATIONS = "BAL_PLM_TL_OBSERVATIONS";  

	TableFieldType [] obsvDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, refid, observation, targetdate, foundby, status
		, responsibility, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getObsvDbFields() {
		return obsvDbFields;
	}

	public BalPlmTlObservationsSql()
	{
		obsvDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			obsvDbFields[ i ] = new TableFieldType();
		}
		obsvDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OBSV_KEYID";
		obsvDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "OBSV_FLID";
		obsvDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OBSV_DATE";
		obsvDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		obsvDbFields[ tableFldConstants.refid.ordinal() ].fieldName = "OBSV_REFID";
		obsvDbFields[ tableFldConstants.refid.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.observation.ordinal() ].fieldName = "OBSV_OBSERVATION";
		obsvDbFields[ tableFldConstants.observation.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "OBSV_TARGETDATE";
		obsvDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		obsvDbFields[ tableFldConstants.foundby.ordinal() ].fieldName = "OBSV_FOUNDBY";
		obsvDbFields[ tableFldConstants.foundby.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.status.ordinal() ].fieldName = "OBSV_STATUS";
		obsvDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "OBSV_RESPONSIBILITY";
		obsvDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OBSV_TEMPFIELD2";
		obsvDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OBSV_TEMPFIELD3";
		obsvDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OBSV_TEMPFIELD4";
		obsvDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OBSV_TEMPFIELD5";
		obsvDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OBSV_ACTIVE";
		obsvDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		obsvDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OBSV_CREATEDBY";
		obsvDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		obsvDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OBSV_CREATEDON";
		obsvDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		obsvDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OBSV_MODIFIEDON";
		obsvDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_OBSERVATIONS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_PLM_TL_OBSERVATIONS, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_PLM_TL_OBSERVATIONS );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}

}

