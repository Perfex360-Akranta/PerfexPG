package com.akranta.tpm.dao.sql;

public class GenTlDocupdatesSql {

	public static final String TBL_GEN_TL_DOCUPDATES = "GEN_TL_DOCUPDATES";  

	TableFieldType [] dcupDbFields = null;

	public enum   tableFldConstants
	{
		keyid, feedbackid, refdoctype, refdocid, updatedoctype, detailid
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getDcupDbFields() {
		return dcupDbFields;
	}

	public GenTlDocupdatesSql()
	{
		dcupDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			dcupDbFields[ i ] = new TableFieldType();
		}
		dcupDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "DCUP_KEYID";
		dcupDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldName = "DCUP_FEEDBACKID";
		dcupDbFields[ tableFldConstants.feedbackid.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "DCUP_REFDOCTYPE";
		dcupDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "DCUP_REFDOCID";
		dcupDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.updatedoctype.ordinal() ].fieldName = "DCUP_UPDATEDOCTYPE";
		dcupDbFields[ tableFldConstants.updatedoctype.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.detailid.ordinal() ].fieldName = "DCUP_DETAILID";
		dcupDbFields[ tableFldConstants.detailid.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "DCUP_CREATEDBY";
		dcupDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		dcupDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "DCUP_CREATEDON";
		dcupDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		dcupDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "DCUP_MODIFIEDON";
		dcupDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_DOCUPDATES, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_DOCUPDATES, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_DOCUPDATES ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getDocUpdateSql(String modifiedOn,String docId)
	{
		String sql = "UPDATE " + TBL_GEN_TL_DOCUPDATES ;
		sql += " SET DCUP_MODIFIEDON = '"+modifiedOn+"' WHERE DCUP_KEYID = '"+docId+"'";
		return sql;
	}

	public static String getUpdateDocSql(TableFieldType[] fieldTypeArr,	Object[] dataArray) 
	{
		String sql = "UPDATE " + TBL_GEN_TL_DOCUPDATES ;
		
		sql += " SET "+	fieldTypeArr[tableFldConstants.detailid.ordinal()].fieldName +
	  	 		" = '" +  (String)dataArray[ tableFldConstants.detailid.ordinal() ] + "'," +
	  	 			fieldTypeArr[tableFldConstants.updatedoctype.ordinal()].fieldName +
		  	 " = '" +  (String)dataArray[ tableFldConstants.updatedoctype.ordinal() ] + "' " +
		  	 		" where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'" ;
		return sql;
	}

}

