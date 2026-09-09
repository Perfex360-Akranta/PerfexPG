package com.akranta.tpm.dao.sql;

public class EnttlUnpostopicSql{

	public static final String TBL_ENT_TL_UNQPOSTOPIC = "ENT_TL_UNQPOSTOPIC";  

	TableFieldType [] ftymDbFields = null;

	public enum   tableFldConstants
	{
		keyid,location,dmt,jh,uniqposkeyid,topicid,mapdate,createdate,createby,modifieddate,
		modifiedby,tempfield1, tempfield2, tempfield3, tempfield4,tempfield5, active,createdon,modifiedon
	}

	public TableFieldType[] getFtymDbFields() {
		return ftymDbFields;
	}

	public EnttlUnpostopicSql()
	{
		ftymDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			ftymDbFields[ i ] = new TableFieldType();
		}
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ETUQ_KEYID";
		ftymDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldName = "ETUQ_LOCATION";
		ftymDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldName = "ETUQ_DMT";
		ftymDbFields[ tableFldConstants.dmt.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldName = "ETUQ_JH";
		ftymDbFields[ tableFldConstants.jh.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.uniqposkeyid.ordinal() ].fieldName = "ETUQ_UNIQPOSKEYID";
		ftymDbFields[ tableFldConstants.uniqposkeyid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.topicid.ordinal() ].fieldName = "ETUQ_TOPICID";
		ftymDbFields[ tableFldConstants.topicid.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.mapdate.ordinal() ].fieldName = "ETUQ_MAPDATE";
		ftymDbFields[ tableFldConstants.mapdate.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdate.ordinal() ].fieldName = "ETUQ_CREATEDATE";
		ftymDbFields[ tableFldConstants.createdate.ordinal() ].fieldType = 'V';
	
		ftymDbFields[ tableFldConstants.createby.ordinal() ].fieldName = "ETUQ_CREATEDBY";
		ftymDbFields[ tableFldConstants.createby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.modifieddate.ordinal() ].fieldName = "ETUQ_MODIFIEDDATE";
		ftymDbFields[ tableFldConstants.modifieddate.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "ETUQ_MODIFIEDBY";
		ftymDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "ETUQ_TEMPFIELD1";
		ftymDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "ETUQ_TEMPFIELD2";
		ftymDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "ETUQ_TEMPFIELD3";
		ftymDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "ETUQ_TEMPFIELD4";
		ftymDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "ETUQ_TEMPFIELD5";
		ftymDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ETUQ_ACTIVE";
		ftymDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ETUQ_CREATEDON";
		ftymDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ETCQ_MODIFIEDON";
		ftymDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ENT_TL_UNQPOSTOPIC, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ENT_TL_UNQPOSTOPIC, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ENT_TL_UNQPOSTOPIC ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

