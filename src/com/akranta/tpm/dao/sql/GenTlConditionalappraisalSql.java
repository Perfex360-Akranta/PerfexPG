package com.akranta.tpm.dao.sql;

public class GenTlConditionalappraisalSql {

	public static final String TBL_GEN_TL_CONDITIONALAPPRAISAL = "GEN_TL_CONDITIONALAPPRAISAL";  

	TableFieldType [] cdapDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flnid, componentid, dimension, checkingtool, typeofcheck
		, idealcondition, status, actionrequired, remarks, actualconditin
		, responsibility, targetdate, completedby, completeddate, tempfield1
		, tempfield2, tempfield3, tempfield4, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getCdapDbFields() {
		return cdapDbFields;
	}

	public GenTlConditionalappraisalSql()
	{
		cdapDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			cdapDbFields[ i ] = new TableFieldType();
		}
		cdapDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CDAP_KEYID";
		cdapDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.flnid.ordinal() ].fieldName = "CDAP_FLNID";
		cdapDbFields[ tableFldConstants.flnid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.componentid.ordinal() ].fieldName = "CDAP_COMPONENTID";
		cdapDbFields[ tableFldConstants.componentid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.dimension.ordinal() ].fieldName = "CDAP_DIMENSION";
		cdapDbFields[ tableFldConstants.dimension.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldName = "CDAP_CHECKINGTOOL";
		cdapDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldName = "CDAP_TYPEOFCHECK";
		cdapDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldName = "CDAP_IDEALCONDITION";
		cdapDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.status.ordinal() ].fieldName = "CDAP_STATUS";
		cdapDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.actionrequired.ordinal() ].fieldName = "CDAP_ACTIONREQUIRED";
		cdapDbFields[ tableFldConstants.actionrequired.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CDAP_REMARKS";
		cdapDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.actualconditin.ordinal() ].fieldName = "CDAP_ACTUALCONDITIN";
		cdapDbFields[ tableFldConstants.actualconditin.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "CDAP_RESPONSIBILITY";
		cdapDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "CDAP_TARGETDATE";
		cdapDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		cdapDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "CDAP_COMPLETEDBY";
		cdapDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "CDAP_COMPLETEDDATE";
		cdapDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		cdapDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CDAP_TEMPFIELD1";
		cdapDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CDAP_TEMPFIELD2";
		cdapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CDAP_TEMPFIELD3";
		cdapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CDAP_TEMPFIELD4";
		cdapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CDAP_ACTIVE";
		cdapDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CDAP_CREATEDBY";
		cdapDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CDAP_CREATEDON";
		cdapDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		cdapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CDAP_MODIFIEDON";
		cdapDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_CONDITIONALAPPRAISAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_CONDITIONALAPPRAISAL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_CONDITIONALAPPRAISAL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSingledata() {
		String sql = "select * from GEN_TL_CONDITIONALAPPRAISAL WHERE CDAP_KEYID = ? " ;
	
		return sql;
	}

}

