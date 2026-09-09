package com.akranta.tpm.dao.sql;

public class PlmTlConditionalappraisalSql {

	public static final String TBL_PLM_TL_CONDITIONALAPPRAISAL = "PLM_TL_CONDITIONALAPPRAISAL";  

	TableFieldType [] cdapDbFields = null;

	public enum   tableFldConstants
	{
		keyid, cdam_keyid, component_type, componentid, newcomponent
		, dimension, checkingtool, typeofcheck, idealtype, idealminimum
		, idealmaximum, uom, idealcondition, actualcondition, actualvalue
		, oknotok, status, actionrequired, refurbishment_status, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCdapDbFields() {
		return cdapDbFields;
	}

	public PlmTlConditionalappraisalSql()
	{
		cdapDbFields = new TableFieldType[ 30 ];
		for(int i = 0;i < 30; i++)
		{	
			cdapDbFields[ i ] = new TableFieldType();
		}
		cdapDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CDAP_KEYID";
		cdapDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.cdam_keyid.ordinal() ].fieldName = "CDAP_CDAM_KEYID";
		cdapDbFields[ tableFldConstants.cdam_keyid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.component_type.ordinal() ].fieldName = "CDAP_COMPONENT_TYPE";
		cdapDbFields[ tableFldConstants.component_type.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.componentid.ordinal() ].fieldName = "CDAP_COMPONENTID";
		cdapDbFields[ tableFldConstants.componentid.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.newcomponent.ordinal() ].fieldName = "CDAP_NEWCOMPONENT";
		cdapDbFields[ tableFldConstants.newcomponent.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.dimension.ordinal() ].fieldName = "CDAP_DIMENSION";
		cdapDbFields[ tableFldConstants.dimension.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldName = "CDAP_CHECKINGTOOL";
		cdapDbFields[ tableFldConstants.checkingtool.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldName = "CDAP_TYPEOFCHECK";
		cdapDbFields[ tableFldConstants.typeofcheck.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.idealtype.ordinal() ].fieldName = "CDAP_IDEALTYPE";
		cdapDbFields[ tableFldConstants.idealtype.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.idealminimum.ordinal() ].fieldName = "CDAP_IDEALMINIMUM";
		cdapDbFields[ tableFldConstants.idealminimum.ordinal() ].fieldType = 'N';

		cdapDbFields[ tableFldConstants.idealmaximum.ordinal() ].fieldName = "CDAP_IDEALMAXIMUM";
		cdapDbFields[ tableFldConstants.idealmaximum.ordinal() ].fieldType = 'N';

		cdapDbFields[ tableFldConstants.uom.ordinal() ].fieldName = "CDAP_UOM";
		cdapDbFields[ tableFldConstants.uom.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldName = "CDAP_IDEALCONDITION";
		cdapDbFields[ tableFldConstants.idealcondition.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.actualcondition.ordinal() ].fieldName = "CDAP_ACTUALCONDITION";
		cdapDbFields[ tableFldConstants.actualcondition.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.actualvalue.ordinal() ].fieldName = "CDAP_ACTUALVALUE";
		cdapDbFields[ tableFldConstants.actualvalue.ordinal() ].fieldType = 'N';

		cdapDbFields[ tableFldConstants.oknotok.ordinal() ].fieldName = "CDAP_OKNOTOK";
		cdapDbFields[ tableFldConstants.oknotok.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.status.ordinal() ].fieldName = "CDAP_STATUS";
		cdapDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.actionrequired.ordinal() ].fieldName = "CDAP_ACTIONREQUIRED";
		cdapDbFields[ tableFldConstants.actionrequired.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.refurbishment_status.ordinal() ].fieldName = "CDAP_REFURBISHMENT_STATUS";
		cdapDbFields[ tableFldConstants.refurbishment_status.ordinal() ].fieldType = 'C';

		cdapDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CDAP_TEMPFIELD1";
		cdapDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CDAP_TEMPFIELD2";
		cdapDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CDAP_TEMPFIELD3";
		cdapDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CDAP_TEMPFIELD4";
		cdapDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CDAP_TEMPFIELD5";
		cdapDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "CDAP_TEMPFIELD6";
		cdapDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		cdapDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "CDAP_TEMPFIELD7";
		cdapDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

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
		return SqlUtils.getInsertSql(TBL_PLM_TL_CONDITIONALAPPRAISAL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_PLM_TL_CONDITIONALAPPRAISAL, fieldTypeArr, dataArray));
		
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
		sql.append(TBL_PLM_TL_CONDITIONALAPPRAISAL );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	public static String selectData(String keyid) {
		String sql= " SELECT CDAP_KEYID, CDAP_COMPONENT_TYPE, CDAP_COMPONENTID, CDAP_NEWCOMPONENT, CDAP_DIMENSION, CDAP_CHECKINGTOOL, CDAP_TYPEOFCHECK, ";
		sql+=" CDAP_IDEALTYPE,CDAP_IDEALMINIMUM,CDAP_IDEALMAXIMUM,CDAP_UOM,CDAP_IDEALCONDITION, CDAP_ACTUALCONDITION,CDAP_ACTUALVALUE,CDAP_OKNOTOK, ";
		sql+=" CDAP_STATUS,CDAP_ACTIONREQUIRED,CDAP_REFURBISHMENT_STATUS,TO_CHAR (CDAP_CREATEDON, 'DD-MON-YYYY') FROM PLM_TL_CONDITIONALAPPRAISAL ";
		sql+=" WHERE CDAP_KEYID = '"+keyid+"'";
		return sql;
	}
	public static String getSingledata() {
		String sql = "SELECT * FROM  PLM_TL_CONDITIONALAPPRAISALMST WHERE CDAM_KEYID = ? " ;
		return sql;
	}
}

