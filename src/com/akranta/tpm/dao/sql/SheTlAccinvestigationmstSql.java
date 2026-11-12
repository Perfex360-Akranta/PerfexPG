package com.akranta.tpm.dao.sql;

public class SheTlAccinvestigationmstSql {

	public static final String TBL_SHE_TL_ACCINVESTIGATIONMST = "SHE_TL_ACCINVESTIGATIONMST";  

	TableFieldType [] ainmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, employee, pnocontractor, accdate, acctime, acclocation
		, department, bodypartaffected, natureofinjury, accdescription
		, treatment, shiftincharge, operators, findings, immediatecause
		, rootcause, responsibility, flid, elementid, tempfield3
		, tempfield4, tempfield5, tempfield6, tempfield7, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getAinmDbFields() {
		return ainmDbFields;
	}

	public SheTlAccinvestigationmstSql()
	{
		ainmDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i < 28; i++)
		{	
			ainmDbFields[ i ] = new TableFieldType();
		}
		ainmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "AINM_KEYID";
		ainmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.employee.ordinal() ].fieldName = "AINM_EMPLOYEE";
		ainmDbFields[ tableFldConstants.employee.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.pnocontractor.ordinal() ].fieldName = "AINM_PNOCONTRACTOR";
		ainmDbFields[ tableFldConstants.pnocontractor.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.accdate.ordinal() ].fieldName = "AINM_ACCDATE";
		ainmDbFields[ tableFldConstants.accdate.ordinal() ].fieldType = 'D';

		ainmDbFields[ tableFldConstants.acctime.ordinal() ].fieldName = "AINM_ACCTIME";
		ainmDbFields[ tableFldConstants.acctime.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.acclocation.ordinal() ].fieldName = "AINM_ACCLOCATION";
		ainmDbFields[ tableFldConstants.acclocation.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.department.ordinal() ].fieldName = "AINM_DEPARTMENT";
		ainmDbFields[ tableFldConstants.department.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.bodypartaffected.ordinal() ].fieldName = "AINM_BODYPARTAFFECTED";
		ainmDbFields[ tableFldConstants.bodypartaffected.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.natureofinjury.ordinal() ].fieldName = "AINM_NATUREOFINJURY";
		ainmDbFields[ tableFldConstants.natureofinjury.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.accdescription.ordinal() ].fieldName = "AINM_ACCDESCRIPTION";
		ainmDbFields[ tableFldConstants.accdescription.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.treatment.ordinal() ].fieldName = "AINM_TREATMENT";
		ainmDbFields[ tableFldConstants.treatment.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldName = "AINM_SHIFTINCHARGE";
		ainmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.operators.ordinal() ].fieldName = "AINM_OPERATORS";
		ainmDbFields[ tableFldConstants.operators.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.findings.ordinal() ].fieldName = "AINM_FINDINGS";
		ainmDbFields[ tableFldConstants.findings.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.immediatecause.ordinal() ].fieldName = "AINM_IMMEDIATECAUSE";
		ainmDbFields[ tableFldConstants.immediatecause.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "AINM_ROOTCAUSE";
		ainmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "AINM_RESPONSIBILITY";
		ainmDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "AINM_FLID";
		ainmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "AINM_ELEMENTID";
		ainmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "AINM_TEMPFIELD3";
		ainmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "AINM_TEMPFIELD4";
		ainmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "AINM_TEMPFIELD5";
		ainmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "AINM_TEMPFIELD6";
		ainmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "AINM_TEMPFIELD7";
		ainmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "AINM_ACTIVE";
		ainmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		ainmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "AINM_CREATEDBY";
		ainmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		ainmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "AINM_CREATEDON";
		ainmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		ainmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "AINM_MODIFIEDON";
		ainmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_ACCINVESTIGATIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_ACCINVESTIGATIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_ACCINVESTIGATIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getFillData() {
		String sql=" SELECT * FROM " + TBL_SHE_TL_ACCINVESTIGATIONMST +" WHERE AINM_KEYID=?";
		return sql;
	}

	public static String getElementId(String ainmFlid) {
		String sql="SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID = '"+ainmFlid+"'";
		return sql;
	}

}

