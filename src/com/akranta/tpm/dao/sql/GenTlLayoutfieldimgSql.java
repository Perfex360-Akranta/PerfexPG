package com.akranta.tpm.dao.sql;

import java.io.FileInputStream;

import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.GenTlLayoutfieldimg;

public class GenTlLayoutfieldimgSql {

	public static final String TBL_GEN_TL_LAYOUTFIELDIMG = "GEN_TL_LAYOUTFIELDIMG";  

	TableFieldType [] lyfiDbFields = null;

	public enum   tableFldConstants
	{
		keyid, bloblength, blobimage, filename, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getLyfiDbFields() {
		return lyfiDbFields;
	}

	public GenTlLayoutfieldimgSql()
	{
		lyfiDbFields = new TableFieldType[ 11 ];
		for(int i = 0;i < 11; i++)
		{	
			lyfiDbFields[ i ] = new TableFieldType();
		}
		lyfiDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "LYFI_KEYID";
		lyfiDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.bloblength.ordinal() ].fieldName = "LYFI_BLOBLENGTH";
		lyfiDbFields[ tableFldConstants.bloblength.ordinal() ].fieldType = 'N';

		lyfiDbFields[ tableFldConstants.blobimage.ordinal() ].fieldName = "LYFI_BLOBIMAGE";
		lyfiDbFields[ tableFldConstants.blobimage.ordinal() ].fieldType = 'B';

		lyfiDbFields[ tableFldConstants.filename.ordinal() ].fieldName = "LYFI_FILENAME";
		lyfiDbFields[ tableFldConstants.filename.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "LYFI_TEMPFIELD1";
		lyfiDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "LYFI_TEMPFIELD2";
		lyfiDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "LYFI_TEMPFIELD3";
		lyfiDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.active.ordinal() ].fieldName = "LYFI_ACTIVE";
		lyfiDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		lyfiDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "LYFI_CREATEDBY";
		lyfiDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		lyfiDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "LYFI_CREATEDON";
		lyfiDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		lyfiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "LYFI_MODIFIEDON";
		lyfiDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_LAYOUTFIELDIMG, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_LAYOUTFIELDIMG, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getInsertImgSql(GenTlLayoutfieldimg genTlLayoutfieldimg,FileInputStream fis)
	{
		String sql = "insert into gen_tl_layoutfieldimg(lyfi_keyid,lyfi_bloblength,lyfi_blobimage,lyfi_filename,lyfi_tempfield1,lyfi_tempfield2,";
			   sql += "lyfi_tempfield3,lyfi_active,lyfi_createdby,lyfi_createdon,lyfi_modifiedon) values(";
			   sql += genTlLayoutfieldimg.getLyfiKeyid()+","+genTlLayoutfieldimg.getLyfiBloblength()+","+genTlLayoutfieldimg.getLyfiBlobimage()+","+genTlLayoutfieldimg.getLyfiFilename()+",{},{},{},Y,";
			   sql += genTlLayoutfieldimg.getLyfiCreatedby()+","+genTlLayoutfieldimg.getLyfiCreatedon()+","+genTlLayoutfieldimg.getLyfiModifiedon()+")";
		return sql;
		
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_LAYOUTFIELDIMG ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getInsertSqlForImg(){
		
		return " Insert into " + TableNames.TBL_GEN_TL_LAYOUTFIELDIMG  + " values(?,?,?,?,?,?,?,?,?,?,?)";
		
	}
	
	public static String getDeleteBlobSql(){
		return " DELETE FROM " + TableNames.TBL_GEN_TL_LAYOUTFIELDIMG + " WHERE LYFI_KEYID=?";
	}
	public static String selectSql()
	{
		return "SELECT * from " + TBL_GEN_TL_LAYOUTFIELDIMG + " where LYFI_KEYID= ?";
	}

}

