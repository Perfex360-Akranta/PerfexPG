package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class SopTlVisualchecklistdtlSql {

	public static final String TBL_SOP_TL_VISUALCHECKLISTDTL = "SOP_TL_VISUALCHECKLISTDTL";  

	TableFieldType [] vccdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vccm_keyid, checkpoints, sortorder, criteria, orderno
		, tempfield3, tempfield4, tempfield5, createdby, active, createdon
		, modifiedon
	}

	public TableFieldType[] getVccdDbFields() {
		return vccdDbFields;
	}

	public SopTlVisualchecklistdtlSql()
	{
		vccdDbFields = new TableFieldType[ 13 ];
		for(int i = 0;i < 13; i++)
		{	
			vccdDbFields[ i ] = new TableFieldType();
		}
		vccdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCCD_KEYID";
		vccdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vccdDbFields[ tableFldConstants.vccm_keyid.ordinal() ].fieldName = "VCCD_VCCM_KEYID";
		vccdDbFields[ tableFldConstants.vccm_keyid.ordinal() ].fieldType = 'V';

		vccdDbFields[ tableFldConstants.checkpoints.ordinal() ].fieldName = "VCCD_CHECKPOINTS";
		vccdDbFields[ tableFldConstants.checkpoints.ordinal() ].fieldType = 'V';

		vccdDbFields[ tableFldConstants.sortorder.ordinal() ].fieldName = "VCCD_SORTORDER";
		vccdDbFields[ tableFldConstants.sortorder.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.criteria.ordinal() ].fieldName = "VCCD_CRITERIA";
		vccdDbFields[ tableFldConstants.criteria.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "VCCD_ORDERNO";
		vccdDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCCD_TEMPFIELD3";
		vccdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VCCD_TEMPFIELD4";
		vccdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "VCCD_TEMPFIELD5";
		vccdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCCD_CREATEDBY";
		vccdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vccdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCCD_ACTIVE";
		vccdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vccdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCCD_CREATEDON";
		vccdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vccdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCCD_MODIFIEDON";
		vccdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SOP_TL_VISUALCHECKLISTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SOP_TL_VISUALCHECKLISTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SOP_TL_VISUALCHECKLISTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String Deletevis(String keyid)
	{ 
		String Keyid = keyid.replace(",", "','");
		CommonMessage.debugMsg(" before"+Keyid);
		String Sql=" DELETE FROM " + TBL_SOP_TL_VISUALCHECKLISTDTL + " WHERE VCCD_KEYID IN ('"+Keyid+"')";
		CommonMessage.debugMsg(Sql);
		CommonMessage.debugMsg(" after"+Keyid);
		return Sql;
		
	
	}
	
	public static String makeInactive(String keyid)
	{ 
		String Keyid = keyid.replace(",", "','");
		CommonMessage.debugMsg(" before"+Keyid);
		String Sql=" UPDATE  " + TBL_SOP_TL_VISUALCHECKLISTDTL + " SET VCCD_ACTIVE = 'N' WHERE VCCD_KEYID IN ('"+Keyid+"')";
		CommonMessage.debugMsg(Sql);
		CommonMessage.debugMsg(" after"+Keyid);
		return Sql;
		
	
	}

	public static String title(String titleid) {
		// TODO Auto-generated method stub
		String sql="select VCCL_FLID from gen_tl_visualcontrolchecklist where VCCL_TITLE='"+titleid+"'";
		CommonMessage.debugMsg(sql+ " sql");
		return sql;
	}

}

