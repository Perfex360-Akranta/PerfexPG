package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class GenTlCriticalprocessSql {

	public static final String TBL_GEN_TL_CRITICALPROCESS = "GEN_TL_CRITICALPROCESS";  

	TableFieldType [] crppDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, parameter, method, unit, value
		, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getCrppDbFields() {
		return crppDbFields;
	}

	public GenTlCriticalprocessSql()
	{
		crppDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			crppDbFields[ i ] = new TableFieldType();
		}
		crppDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CRPP_KEYID";
		crppDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CRPP_FLID";
		crppDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CRPP_ELEMENTID";
		crppDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.date.ordinal() ].fieldName = "CRPP_DATE";
		crppDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		crppDbFields[ tableFldConstants.parameter.ordinal() ].fieldName = "CRPP_PARAMETER";
		crppDbFields[ tableFldConstants.parameter.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.method.ordinal() ].fieldName = "CRPP_METHOD";
		crppDbFields[ tableFldConstants.method.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.unit.ordinal() ].fieldName = "CRPP_UNIT";
		crppDbFields[ tableFldConstants.unit.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.value.ordinal() ].fieldName = "CRPP_VALUE";
		crppDbFields[ tableFldConstants.value.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "CRPP_TEMPFIELD1";
		crppDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CRPP_TEMPFIELD2";
		crppDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CRPP_TEMPFIELD3";
		crppDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CRPP_TEMPFIELD4";
		crppDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CRPP_TEMPFIELD5";
		crppDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CRPP_ACTIVE";
		crppDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		crppDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CRPP_CREATEDBY";
		crppDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		crppDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CRPP_CREATEDON";
		crppDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		crppDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CRPP_MODIFIEDON";
		crppDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_CRITICALPROCESS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_CRITICALPROCESS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_CRITICALPROCESS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectData(String keyid, String type) {
		// TODO Auto-generated method stub
		
		String sql=null;
		if(UIUtils.isValidKeyId(type)){
			sql= " select empm_roleid from gen_tl_employeemst where empm_keyid='"+keyid+"'";
		}else{	
			sql= " select crpp_flid,to_char(crpp_date,'DD-MON-YYYY'),crpp_parameter,crpp_method,crpp_unit,crpp_value ";
			sql+= "  FROM GEN_TL_CRITICALPROCESS " ;
			sql+= " where crpp_KEYID='"+keyid+"'";
		}
	    return sql;
	}

}

