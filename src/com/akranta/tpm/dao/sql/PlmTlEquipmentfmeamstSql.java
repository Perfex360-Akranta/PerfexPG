package com.akranta.tpm.dao.sql;

public class PlmTlEquipmentfmeamstSql {

	public static final String TBL_PLM_TL_EQUIPMENTFMEAMST = "PLM_TL_EQUIPMENTFMEAMST";  

	TableFieldType [] fmeqDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, date, no, equipid, supequipid, preparedby, coreteam
		, doctype, docmstid, docdtlsid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFmeqDbFields() {
		return fmeqDbFields;
	}

	public PlmTlEquipmentfmeamstSql()
	{
		fmeqDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			fmeqDbFields[ i ] = new TableFieldType();
		}
		fmeqDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FMEQ_KEYID";
		fmeqDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FMEQ_FLID";
		fmeqDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.date.ordinal() ].fieldName = "FMEQ_DATE";
		fmeqDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		fmeqDbFields[ tableFldConstants.no.ordinal() ].fieldName = "FMEQ_NO";
		fmeqDbFields[ tableFldConstants.no.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.equipid.ordinal() ].fieldName = "FMEQ_EQUIPID";
		fmeqDbFields[ tableFldConstants.equipid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.supequipid.ordinal() ].fieldName = "FMEQ_SUPEQUIPID";
		fmeqDbFields[ tableFldConstants.supequipid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "FMEQ_PREPAREDBY";
		fmeqDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.coreteam.ordinal() ].fieldName = "FMEQ_CORETEAM";
		fmeqDbFields[ tableFldConstants.coreteam.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.doctype.ordinal() ].fieldName = "FMEQ_DOCTYPE";
		fmeqDbFields[ tableFldConstants.doctype.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.docmstid.ordinal() ].fieldName = "FMEQ_DOCMSTID";
		fmeqDbFields[ tableFldConstants.docmstid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldName = "FMDM_DOCDTLSID";
		fmeqDbFields[ tableFldConstants.docdtlsid.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FMEQ_TEMPFIELD1";
		fmeqDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FMEQ_TEMPFIELD2";
		fmeqDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FMEQ_TEMPFIELD3";
		fmeqDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FMEQ_TEMPFIELD4";
		fmeqDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FMEQ_TEMPFIELD5";
		fmeqDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FMEQ_ACTIVE";
		fmeqDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fmeqDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FMEQ_CREATEDBY";
		fmeqDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fmeqDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FMEQ_CREATEDON";
		fmeqDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fmeqDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FMEQ_MODIFIEDON";
		fmeqDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_EQUIPMENTFMEAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_EQUIPMENTFMEAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_EQUIPMENTFMEAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSelectSql(String keyId) {
		// TODO Auto-generated method stub
		String sql = "SELECT FMEQ_KEYID,FMEQ_FLID,FMEQ_DATE,FMEQ_NO,FMEQ_PREPAREDBY,FMEQ_CORETEAM,FMEQ_EQUIPID,FMEQ_SUPEQUIPID  from " + TBL_PLM_TL_EQUIPMENTFMEAMST ;		
		sql += " where FMEQ_KEYID= '" +  keyId + "'";
		return sql;
	}
}

