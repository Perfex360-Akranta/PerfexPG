package com.akranta.tpm.dao.sql;

public class AbnTlDtlSql {

	public static final String TBL_ABN_TL_DTL = "ABN_TL_DTL";  

	TableFieldType [] abndDbFields = null;

	public enum   tableFldConstants
	{
		keyid,abnormalityid, ispokayokeprovided, priority, hirarefno, immediateaction
		, effectleadsto, avoidrecurrence, pokayokeid, improvementteam
		, responsiblity, circleid, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getAbndDbFields() {
		return abndDbFields;
	}

	public AbnTlDtlSql()
	{
		abndDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i <  20; i++)
		{	
			abndDbFields[ i ] = new TableFieldType();
		}
		abndDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ABND_KEYID";
		abndDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		abndDbFields[ tableFldConstants.abnormalityid.ordinal() ].fieldName = "ABND_ABNORMALITYID";
		abndDbFields[ tableFldConstants.abnormalityid.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.ispokayokeprovided.ordinal() ].fieldName = "ABND_ISPOKAYOKEPROVIDED";
		abndDbFields[ tableFldConstants.ispokayokeprovided.ordinal() ].fieldType = 'C';

		abndDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "ABND_PRIORITY";
		abndDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		abndDbFields[ tableFldConstants.hirarefno.ordinal() ].fieldName = "ABND_HIRAREFNO";
		abndDbFields[ tableFldConstants.hirarefno.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldName = "ABND_IMMEDIATEACTION";
		abndDbFields[ tableFldConstants.immediateaction.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.effectleadsto.ordinal() ].fieldName = "ABND_EFFECTLEADSTO";
		abndDbFields[ tableFldConstants.effectleadsto.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.avoidrecurrence.ordinal() ].fieldName = "ABND_AVOIDRECURRENCE";
		abndDbFields[ tableFldConstants.avoidrecurrence.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.pokayokeid.ordinal() ].fieldName = "ABND_POKAYOKEID";
		abndDbFields[ tableFldConstants.pokayokeid.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.improvementteam.ordinal() ].fieldName = "ABND_IMPROVEMENTTEAM";
		abndDbFields[ tableFldConstants.improvementteam.ordinal() ].fieldType = 'C';

		abndDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldName = "ABND_RESPONSIBLITY";
		abndDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.circleid.ordinal() ].fieldName = "ABND_CIRCLEID";
		abndDbFields[ tableFldConstants.circleid.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ABND_TEMPFIELD2";
		abndDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ABND_TEMPFIELD3";
		abndDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ABND_TEMPFIELD4";
		abndDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ABND_TEMPFIELD5";
		abndDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ABND_ACTIVE";
		abndDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		abndDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ABND_CREATEDBY";
		abndDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		abndDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ABND_CREATEDON";
		abndDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		abndDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ABND_MODIFIEDON";
		abndDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ABN_TL_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ABN_TL_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_ABN_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
	}

	public static String getInactiveSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = " UPDATE "+ TBL_ABN_TL_DTL+" SET ABND_ACTIVE = 'N' WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '"+abndAbnormalityid+"'";
		return sql;
	}
}

