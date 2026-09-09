package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonMessage;
public class GenTlAllmoduleimgfileSql {

	public static final String TBL_GEN_TL_ALLMODULEIMGFILE = "GEN_TL_ALLMODULEIMGFILE";  

	TableFieldType [] imflDbFields = null;

	public enum   tableFldConstants
	{
		refkeyid, refdoctype, imagetype, blobimage, bloblength, filename
		, tempfield1, tempfield2, modifiedon
	}

	public TableFieldType[] getImflDbFields() {
		return imflDbFields;
	}

	public GenTlAllmoduleimgfileSql()
	{
		imflDbFields = new TableFieldType[ 9 ];
		for(int i = 0;i < 9; i++)
		{	
			imflDbFields[ i ] = new TableFieldType();
		}
		imflDbFields[ tableFldConstants.refkeyid.ordinal() ].fieldName = "IMFL_REFKEYID";
		imflDbFields[ tableFldConstants.refkeyid.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "IMFL_REFDOCTYPE";
		imflDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.imagetype.ordinal() ].fieldName = "IMFL_IMAGETYPE";
		imflDbFields[ tableFldConstants.imagetype.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.blobimage.ordinal() ].fieldName = "IMFL_BLOBIMAGE";
		imflDbFields[ tableFldConstants.blobimage.ordinal() ].fieldType = 'B';

		imflDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "IMFL_BLOBLENGTH";
		imflDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		imflDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "IMFL_FILENAME";
		imflDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "IMFL_TEMPFIELD1";
		imflDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "IMFL_TEMPFIELD2";
		imflDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		imflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "IMFL_MODIFIEDON";
		imflDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql()
	{
		//return SqlUtils.getInsertSql(TBL_GEN_TL_ALLMODULEIMGFILE, fieldTypeArr, dataArray);

			return " Insert into " + TBL_GEN_TL_ALLMODULEIMGFILE + " values(?,?,?,?,?,?,?,?,?)";
		
	}
	
	

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_ALLMODULEIMGFILE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.refkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.refkeyid.ordinal() ] + "'";
		return sql;
	}


	public static String getDeleteSql()
	{
		String sql= " DELETE FROM " + TBL_GEN_TL_ALLMODULEIMGFILE + " WHERE IMFL_REFKEYID = ? " +"AND  IMFL_REFDOCTYPE = ? "
					+"AND IMFL_IMAGETYPE  = ? ";
	    CommonMessage.debugMsg("Delete Image:::"+sql);
	    return sql;
		
	}
	
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_ALLMODULEIMGFILE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.refkeyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.refkeyid.ordinal()] + "'";
		return sql;
	}

	public static String selectSql(String kznKeyid) {
		String sql = "select * from "+TBL_GEN_TL_ALLMODULEIMGFILE+" where IMFL_REFKEYID='"+kznKeyid+"'";
		return sql;
	}
	public static String getSqlDelete(String vsopKeyId) {
		String sql = "Delete from "+TBL_GEN_TL_ALLMODULEIMGFILE+" where IMFL_REFKEYID='"+vsopKeyId+"'";
		return sql;
	}

	public static String selectSqlImg(String nodeId) {
		CommonMessage.debugMsg("Image in sql"+nodeId);
	
		String sql = "select * from GEN_TL_ALLMODULEIMGFILE where IMFL_REFKEYID=?";
		return sql;
	}


}

