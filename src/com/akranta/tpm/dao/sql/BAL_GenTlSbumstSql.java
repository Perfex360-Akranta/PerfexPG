package com.akranta.tpm.dao.sql;

public class BAL_GenTlSbumstSql {

	public static final String TBL_GEN_TL_SBUMST = "GEN_TL_SBUMST";  
	
	public static final String TBL_GEN_TL_FUNCTIONALLOCN = "GEN_TL_FUNCTIONALLOCN";  
	
	TableFieldType [] sbutDbFields = null;

	public enum   tableFldConstants
	{
		keyid, companyid, locationid, factoryid, flid, name, code, description
		, tempfield1, tempfield2, tempfield3, tempfield4, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getSbutDbFields() {
		return sbutDbFields;
	}

	public BAL_GenTlSbumstSql()
	{
		sbutDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			sbutDbFields[ i ] = new TableFieldType();
		}
		sbutDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SBUT_KEYID";
		sbutDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.companyid.ordinal() ].fieldName = "SBUT_COMPANYID";
		sbutDbFields[ tableFldConstants.companyid.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.locationid.ordinal() ].fieldName = "SBUT_LOCATIONID";
		sbutDbFields[ tableFldConstants.locationid.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "SBUT_FACTORYID";
		sbutDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SBUT_FLID";
		sbutDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.name.ordinal() ].fieldName = "SBUT_NAME";
		sbutDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.code.ordinal() ].fieldName = "SBUT_CODE";
		sbutDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.description.ordinal() ].fieldName = "SBUT_DESCRIPTION";
		sbutDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SBUT_TEMPFIELD1";
		sbutDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		sbutDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SBUT_TEMPFIELD2";
		sbutDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		sbutDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SBUT_TEMPFIELD3";
		sbutDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		sbutDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SBUT_TEMPFIELD4";
		sbutDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		sbutDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SBUT_ACTIVE";
		sbutDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sbutDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SBUT_CREATEDBY";
		sbutDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sbutDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SBUT_CREATEDON";
		sbutDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sbutDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SBUT_MODIFIEDON";
		sbutDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_SBUMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_SBUMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_SBUMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getGenTlSbumstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_SBUMST + " where SBUT_KEYID = ?  ";
	}

}

