package com.akranta.tpm.dao.sql;

import java.util.List;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlEmployeedtlSql {

	public static final String TBL_GEN_TL_EMPLOYEEDTL = "GEN_TL_EMPLOYEEDTL";  

	TableFieldType [] empdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, birthdate, address, cityid, stateid, countryid, phone
		, image, remarks, currentexperience, otherexperience, totalexperience
		, qualification, discipline, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEmpdDbFields() {
		return empdDbFields;
	}

	public GenTlEmployeedtlSql()
	{
		empdDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			empdDbFields[ i ] = new TableFieldType();
		}
		empdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EMPD_KEYID";
		empdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.birthdate.ordinal() ].fieldName = "EMPD_BIRTHDATE";
		empdDbFields[ tableFldConstants.birthdate.ordinal() ].fieldType = 'D';

		empdDbFields[ tableFldConstants.address.ordinal() ].fieldName = "EMPD_ADDRESS";
		empdDbFields[ tableFldConstants.address.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.cityid.ordinal() ].fieldName = "EMPD_CITYID";
		empdDbFields[ tableFldConstants.cityid.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.stateid.ordinal() ].fieldName = "EMPD_STATEID";
		empdDbFields[ tableFldConstants.stateid.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.countryid.ordinal() ].fieldName = "EMPD_COUNTRYID";
		empdDbFields[ tableFldConstants.countryid.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.phone.ordinal() ].fieldName = "EMPD_PHONE";
		empdDbFields[ tableFldConstants.phone.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.image.ordinal() ].fieldName = "EMPD_IMAGE";
		empdDbFields[ tableFldConstants.image.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "EMPD_REMARKS";
		empdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.currentexperience.ordinal() ].fieldName = "EMPD_CURRENTEXPERIENCE";
		empdDbFields[ tableFldConstants.currentexperience.ordinal() ].fieldType = 'N';

		empdDbFields[ tableFldConstants.otherexperience.ordinal() ].fieldName = "EMPD_OTHEREXPERIENCE";
		empdDbFields[ tableFldConstants.otherexperience.ordinal() ].fieldType = 'N';

		empdDbFields[ tableFldConstants.totalexperience.ordinal() ].fieldName = "EMPD_TOTALEXPERIENCE";
		empdDbFields[ tableFldConstants.totalexperience.ordinal() ].fieldType = 'N';

		empdDbFields[ tableFldConstants.qualification.ordinal() ].fieldName = "EMPD_QUALIFICATION";
		empdDbFields[ tableFldConstants.qualification.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.discipline.ordinal() ].fieldName = "EMPD_DISCIPLINE";
		empdDbFields[ tableFldConstants.discipline.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EMPD_ACTIVE";
		empdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		empdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EMPD_CREATEDBY";
		empdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		empdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EMPD_CREATEDON";
		empdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		empdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMPD_MODIFIEDON";
		empdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql;
		 sql=SqlUtils.getInsertSql(TBL_GEN_TL_EMPLOYEEDTL, fieldTypeArr, dataArray);
		 CommonMessage.debugMsg("Insert detail Sql Query"+sql);
		 return sql;
		
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPLOYEEDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPLOYEEDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getEmployeedtlSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_EMPLOYEEDTL + " where EMPD_KEYID= ?";
		//CommonMessage.debugMsg("before + ");
		//return "select * from "+ TBL_GEN_TL_EMPLOYEEMST+","+TBL_GEN_TL_EMPLOYEEDTL +" where EMPM_KEYID=EMPD_KEYID and  EMPM_KEYID= ?";
		
	}
	
	public static String getInsertSqlForEmp(){
		
		
		return " Insert into GEN_TL_EMPLOYEEIMG values(?,?,?,?,?)";
		
	}

}

