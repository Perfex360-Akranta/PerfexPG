package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class VocTlChecklistcustomerSql {

	public static final String TBL_VOC_TL_CHECKLISTCUSTOMER = "VOC_TL_CHECKLISTCUSTOMER";  

	TableFieldType [] vclcDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vocc_keyid, vchm_keyid, tempfield2, tempfield3, tempfield4
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getVclcDbFields() {
		return vclcDbFields;
	}

	public VocTlChecklistcustomerSql()
	{
		vclcDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			vclcDbFields[ i ] = new TableFieldType();
		}
		vclcDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCLC_KEYID";
		vclcDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vclcDbFields[ tableFldConstants.vocc_keyid.ordinal() ].fieldName = "VCLC_VOCC_KEYID";
		vclcDbFields[ tableFldConstants.vocc_keyid.ordinal() ].fieldType = 'V';

		vclcDbFields[ tableFldConstants.vchm_keyid.ordinal() ].fieldName = "VCLC_VCHM_KEYID";
		vclcDbFields[ tableFldConstants.vchm_keyid.ordinal() ].fieldType = 'C';

		vclcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VCLC_TEMPFIELD2";
		vclcDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		vclcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCLC_TEMPFIELD3";
		vclcDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		vclcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "VCLC_TEMPFIELD4";
		vclcDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		vclcDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCLC_ACTIVE";
		vclcDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vclcDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCLC_CREATEDBY";
		vclcDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vclcDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCLC_CREATEDON";
		vclcDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vclcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCLC_MODIFIEDON";
		vclcDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_VOC_TL_CHECKLISTCUSTOMER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_VOC_TL_CHECKLISTCUSTOMER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_VOC_TL_CHECKLISTCUSTOMER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDelete(String vclcVchmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_VOC_TL_CHECKLISTCUSTOMER +" WHERE VCLC_VCHM_KEYID = '"+vclcVchmKeyid+"'";
		CommonMessage.debugMsg(sql);
		return sql;
	}

	

}

