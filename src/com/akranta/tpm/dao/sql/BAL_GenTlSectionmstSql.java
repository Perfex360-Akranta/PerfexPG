package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_GenTlMachinemstSql.tableFldConstants;

public class BAL_GenTlSectionmstSql {

	public static final String TBL_GEN_TL_SECTIONMST = "GEN_TL_SECTIONMST";  

	TableFieldType [] sectDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, companyid, sectiongroup, code, name, flid, active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSectDbFields() {
		return sectDbFields;
	}

	public BAL_GenTlSectionmstSql()
	{
		sectDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			sectDbFields[ i ] = new TableFieldType();
		}
		sectDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SECT_KEYID";
		sectDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "SECT_FACTORYID";
		sectDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "SECT_COMPANYID";
		sectDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.sectiongroup.ordinal() ].fieldName = "SECT_SECTIONGROUP";
		sectDbFields[ tableFldConstants.sectiongroup.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.code.ordinal() ].fieldName = "SECT_CODE";
		sectDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.name.ordinal() ].fieldName = "SECT_NAME";
		sectDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';
		
		sectDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SECT_FLID";
		sectDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SECT_ACTIVE";
		sectDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sectDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SECT_CREATEDBY";
		sectDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sectDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SECT_CREATEDON";
		sectDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sectDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SECT_MODIFIEDON";
		sectDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SECTIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SECTIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(String delemode,TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql ="";
		
		if (delemode.equals("I")) {  
			sql = "UPDATE " + TBL_GEN_TL_SECTIONMST ;		
			sql += " SET " + fieldTypeArr[tableFldConstants.active.ordinal()].fieldName  +" = 'N'";
			sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		else  {		
		
		
			 sql = "DELETE from " + TBL_GEN_TL_SECTIONMST ;
		
			 sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		}
		return sql;
	}

	public static String genTlSectionmstSql() {
		// TODO Auto-generated method stub
		return  " SELECT * from " + TBL_GEN_TL_SECTIONMST + " where SECT_KEYID = ?  ";
	}

}

