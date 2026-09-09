package com.akranta.tpm.dao.sql;

public class GenTlToolsimgSql {

	public static final String TBL_GEN_TL_TOOLSIMG = "GEN_TL_TOOLSIMG";  

	TableFieldType [] toimDbFields = null;

	public enum   tableFldConstants
	{
		keyid, blobimage, bloblength, filename, modifiedon
	}

	public TableFieldType[] getToimDbFields() {
		return toimDbFields;
	}

	public GenTlToolsimgSql()
	{
		toimDbFields = new TableFieldType[ 5 ];
		for(int i = 0;i < 5; i++)
		{	
			toimDbFields[ i ] = new TableFieldType();
		}
		toimDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TOIM_KEYID";
		toimDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		toimDbFields[ tableFldConstants.blobimage.ordinal() ].fieldName = "TOIM_BLOBIMAGE";
		toimDbFields[ tableFldConstants.blobimage.ordinal() ].fieldType = 'B';

		toimDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "TOIM_BLOBLENGTH";
		toimDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		toimDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "TOIM_FILENAME";
		toimDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		toimDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TOIM_MODIFIEDON";
		toimDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_TOOLSIMG, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_TOOLSIMG ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getInsertSqlForImg(){
		return " Insert into " + TableNames.TBL_GEN_TL_TOOLSIMG  + " values(?,?,?,?,?)";
		//return " Insert into  testImage values(?,?,?,?)";
	}
	public static String getUpdateSqlForImg(){
		return " update " + TableNames.TBL_GEN_TL_TOOLSIMG  + " set TOIM_BLOBIMAGE = ?,TOIM_BLOBLENGTH =?, TOIM_FILENAME=?,TOIM_MODIFIEDON=? where TOIM_KEYID = ? ";
		//return " Insert into  testImage values(?,?,?,?)";
	}
	public static String getInsertSql(){
		return " select TOIM_FILENAME " + TableNames.TBL_GEN_TL_TOOLSIMG  + " values(?,?,?,?,?)";
		//return " Insert into  testImage values(?,?,?,?)";
	}
	public static String getDeleteSql(){
		return " DELETE FROM " + TableNames.TBL_GEN_TL_TOOLSIMG + " WHERE TOIM_KEYID = ? ";
	}

	public static String selectSql() {
		String sql = "select * from GEN_TL_TOOLSIMG ";
		return sql;
	}
}

