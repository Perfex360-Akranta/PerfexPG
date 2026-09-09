package com.akranta.tpm.dao.sql;

public class VocTlChecklistdtlSql {

	public static final String TBL_VOC_TL_CHECKLISTDTL = "VOC_TL_CHECKLISTDTL";  

	TableFieldType [] vchdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, vchm_keyid, criteria_order, criteria, check_order, check_point
		, target_score, effective_from, effective_till, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getVchdDbFields() {
		return vchdDbFields;
	}

	public VocTlChecklistdtlSql()
	{
		vchdDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			vchdDbFields[ i ] = new TableFieldType();
		}
		vchdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "VCHD_KEYID";
		vchdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.vchm_keyid.ordinal() ].fieldName = "VCHD_VCHM_KEYID";
		vchdDbFields[ tableFldConstants.vchm_keyid.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.criteria_order.ordinal() ].fieldName = "VCHD_CRITERIA_ORDER";
		vchdDbFields[ tableFldConstants.criteria_order.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.criteria.ordinal() ].fieldName = "VCHD_CRITERIA";
		vchdDbFields[ tableFldConstants.criteria.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.check_order.ordinal() ].fieldName = "VCHD_CHECK_ORDER";
		vchdDbFields[ tableFldConstants.check_order.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.check_point.ordinal() ].fieldName = "VCHD_CHECK_POINT";
		vchdDbFields[ tableFldConstants.check_point.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.target_score.ordinal() ].fieldName = "VCHD_TARGET_SCORE";
		vchdDbFields[ tableFldConstants.target_score.ordinal() ].fieldType = 'N';

		vchdDbFields[ tableFldConstants.effective_from.ordinal() ].fieldName = "VCHD_EFFECTIVE_FROM";
		vchdDbFields[ tableFldConstants.effective_from.ordinal() ].fieldType = 'D';

		vchdDbFields[ tableFldConstants.effective_till.ordinal() ].fieldName = "VCHD_EFFECTIVE_TILL";
		vchdDbFields[ tableFldConstants.effective_till.ordinal() ].fieldType = 'D';

		vchdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "VCHD_TEMPFIELD1";
		vchdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "VCHD_TEMPFIELD2";
		vchdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "VCHD_TEMPFIELD3";
		vchdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "VCHD_ACTIVE";
		vchdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		vchdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "VCHD_CREATEDBY";
		vchdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		vchdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "VCHD_CREATEDON";
		vchdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		vchdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "VCHD_MODIFIEDON";
		vchdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_VOC_TL_CHECKLISTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_VOC_TL_CHECKLISTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_VOC_TL_CHECKLISTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getDelete(String vchmKeyid) {
		// TODO Auto-generated method stub
		String sql = "DELETE from " + TBL_VOC_TL_CHECKLISTDTL +" WHERE VCHD_VCHM_KEYID = '"+vchmKeyid+"'";
		return sql;
	}

}

