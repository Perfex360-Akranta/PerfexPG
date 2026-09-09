package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.GenTlEmployeedtlSql.tableFldConstants;

public class GenTlEmployeeimgSql {

	public static final String TBL_GEN_TL_EMPLOYEEIMG = "GEN_TL_EMPLOYEEIMG";  

	TableFieldType [] empiDbFields = null;

	public enum   tableFldConstants
	{
		employeeid, blobimage, bloblength, filename, modifiedon
	}

	public TableFieldType[] getEmpiDbFields() {
		return empiDbFields;
	}

	public GenTlEmployeeimgSql()
	{
		empiDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			empiDbFields[ i ] = new TableFieldType();
		}
		empiDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "EMPI_EMPLOYEEID";
		empiDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		empiDbFields[ tableFldConstants.blobimage.ordinal() ].fieldName = "EMPI_BLOBIMAGE";
		empiDbFields[ tableFldConstants.blobimage.ordinal() ].fieldType = 'B';

		empiDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "EMPI_BLOBLENGTH";
		empiDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		empiDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "EMPI_FILENAME";
		empiDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		empiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMPI_MODIFIEDON";
		empiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPLOYEEIMG, fieldTypeArr, dataArray);
	}

	

	
	
	public static String selectSql(String keyId)
	{
		String sql = "select * from gen_tl_employeeimg where EMPI_EMPLOYEEID=?";
		
		
		return sql;
	}

	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{		
		String sql = "DELETE from " + TBL_GEN_TL_EMPLOYEEIMG ;		
		sql += " where " + fieldTypeArr[tableFldConstants.employeeid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.employeeid.ordinal()] + "'";
		return sql;		
		
	}
}

