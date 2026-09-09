package com.akranta.tpm.dao.sql;

public class GenTlActionplandtlSql {

	public static final String TBL_GEN_TL_ACTIONPLANDTL = "GEN_TL_ACTIONPLANDTL";  

	TableFieldType [] apldDbFields = null;

	public enum   tableFldConstants
	{
		keyid, aplm_keyid, tradeid, actionplan, howtodo, responsibility
		, targetdate, status, compleatedon, completedby, countermeasure
		, remarks, others, tempfiled2, tempfiled3, tempfiled4, tempfiled5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getApldDbFields() {
		return apldDbFields;
	}

	public GenTlActionplandtlSql()
	{
		apldDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			apldDbFields[ i ] = new TableFieldType();
		}
		apldDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "APLD_KEYID";
		apldDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.aplm_keyid.ordinal() ].fieldName = "APLD_APLM_KEYID";
		apldDbFields[ tableFldConstants.aplm_keyid.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "APLD_TRADEID";
		apldDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.actionplan.ordinal() ].fieldName = "APLD_ACTIONPLAN";
		apldDbFields[ tableFldConstants.actionplan.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.howtodo.ordinal() ].fieldName = "APLD_HOWTODO";
		apldDbFields[ tableFldConstants.howtodo.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "APLD_RESPONSIBILITY";
		apldDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "APLD_TARGETDATE";
		apldDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		apldDbFields[ tableFldConstants.status.ordinal() ].fieldName = "APLD_STATUS";
		apldDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.compleatedon.ordinal() ].fieldName = "APLD_COMPLEATEDON";
		apldDbFields[ tableFldConstants.compleatedon.ordinal() ].fieldType = 'D';

		apldDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "APLD_COMPLETEDBY";
		apldDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "APLD_COUNTERMEASURE";
		apldDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "APLD_REMARKS";
		apldDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.others.ordinal() ].fieldName = "APLD_OTHERS";
		apldDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldName = "APLD_TEMPFILED2";
		apldDbFields[ tableFldConstants.tempfiled2.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldName = "APLD_TEMPFILED3";
		apldDbFields[ tableFldConstants.tempfiled3.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldName = "APLD_TEMPFILED4";
		apldDbFields[ tableFldConstants.tempfiled4.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldName = "APLD_TEMPFILED5";
		apldDbFields[ tableFldConstants.tempfiled5.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.active.ordinal() ].fieldName = "APLD_ACTIVE";
		apldDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		apldDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "APLD_CREATEDBY";
		apldDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		apldDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "APLD_CREATEDON";
		apldDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		apldDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "APLD_MODIFIEDON";
		apldDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_ACTIONPLANDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_ACTIONPLANDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_ACTIONPLANDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDeleteSql(String aplmKeyid) {
		String sql = "DELETE from " + TBL_GEN_TL_ACTIONPLANDTL ;
		sql += " where APLD_APLM_KEYID='" +aplmKeyid+
		   "'";
		return sql;
	}

	public static String getDeleteMasterSql(String aplmKeyid){
		
		StringBuilder sql = new StringBuilder(" DELETE from GEN_TL_ACTIONPLANMST a where NOT EXISTS ( select APLD_APLM_KEYID from GEN_TL_ACTIONPLANDTL  where APLD_APLM_KEYID = a.APLM_KEYID  and APLD_APLM_KEYID = '");  
		sql.append(aplmKeyid).append("') 	and  APLM_KEYID = '").append(aplmKeyid ).append("'");
		
		return sql.toString();
	}
	public static String getActionPDetailDeleteSql(String ActionPMasterId) {
		String sql = "DELETE from " + TBL_GEN_TL_ACTIONPLANDTL ;
		sql += " where APLD_APLM_KEYID='" +ActionPMasterId+
		   "'";
		return sql;
	}
public static String getActionPMasterDeleteMasterSql(String ActionPMasterId){
		
		StringBuilder sql = new StringBuilder(" DELETE from GEN_TL_ACTIONPLANMST a where NOT EXISTS ( select APLD_APLM_KEYID from GEN_TL_ACTIONPLANDTL  where APLD_APLM_KEYID = a.APLM_KEYID  and APLD_APLM_KEYID = '");  
		sql.append(ActionPMasterId).append("') 	and  APLM_KEYID = '").append(ActionPMasterId ).append("'");
		return sql.toString();
	}
}

