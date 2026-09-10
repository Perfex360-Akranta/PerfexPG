package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.GenTlSectionmstSql.tableFldConstants;

/**
 * Author:N Arun
 * Created on:25.11.2011
 */
public class BAL_GenTlAssemblymstSql {

	public static final String TBL_BAL_GEN_TL_ASSEMBLYMST = "BAL_GEN_TL_ASSEMBLYMST";  

	TableFieldType [] assmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, code, name, description, remarks, type, relatedto, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getAssmDbFields() {
		return assmDbFields;
	}

	public BAL_GenTlAssemblymstSql()
	{
		assmDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			assmDbFields[ i ] = new TableFieldType();
		}
		assmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "ASSM_KEYID";
		assmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "ASSM_CODE";
		assmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "ASSM_NAME";
		assmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.description.ordinal() ].fieldName = "ASSM_DESCRIPTION";
		assmDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "ASSM_REMARKS";
		assmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.type.ordinal() ].fieldName = "ASSM_TYPE";
		assmDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		assmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "ASSM_RELATEDTO";
		assmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';
		
		assmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ASSM_ACTIVE";
		assmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		assmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "ASSM_CREATEDBY";
		assmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		assmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "ASSM_CREATEDON";
		assmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		assmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "ASSM_MODIFIEDON";
		assmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_GEN_TL_ASSEMBLYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_GEN_TL_ASSEMBLYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String delemode, TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql ="";
		
		if (delemode.equals("I")) {  
			sql = "UPDATE " + TBL_BAL_GEN_TL_ASSEMBLYMST ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		else  {		
			
		
		 sql = "DELETE from " + TBL_BAL_GEN_TL_ASSEMBLYMST ;
		
		 sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		return sql;
	}

/*	public static String getAssemblymstSql(String assemblyId) {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_ASSEMBLYMST + " where ASSM_KEYID= '" + assemblyId +"'";
	}
 */
	public static String getAssemblymstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_BAL_GEN_TL_ASSEMBLYMST + " where ASSM_KEYID = ?  ";
	}

	public static String getassmfrmdatasql() {
		// TODO Auto-generated method stub
		return "SELECT * from " + TBL_BAL_GEN_TL_ASSEMBLYMST + " where ASSM_KEYID= ?";
	}

}

