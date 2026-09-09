package com.akranta.tpm.dao.sql;

public class QtmTlIntrejectiondtlSql {

	public static final String TBL_QTM_TL_INTREJECTIONDTL = "QTM_TL_INTREJECTIONDTL";  

	TableFieldType [] qirdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, prodfamilyid, processid, phenomenaid, causeid, quantity
		, m4type, type, backlogflag, referenceid, remarks, wwmasterid
		, entrytype, masterid, qhb_keyid, plrk_keyid, subprocessid, qty
		, tempfield4, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getQirdDbFields() {
		return qirdDbFields;
	}

	public QtmTlIntrejectiondtlSql()
	{
		qirdDbFields = new TableFieldType[ 24 ];
		for(int i = 0;i < 24; i++)
		{	
			qirdDbFields[ i ] = new TableFieldType();
		}
		qirdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QIRD_KEYID";
		qirdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.prodfamilyid.ordinal() ].fieldName = "QIRD_PRODFAMILYID";
		qirdDbFields[ tableFldConstants.prodfamilyid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "QIRD_PROCESSID";
		qirdDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "QIRD_PHENOMENAID";
		qirdDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "QIRD_CAUSEID";
		qirdDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "QIRD_QUANTITY";
		qirdDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		qirdDbFields[ tableFldConstants.m4type.ordinal() ].fieldName = "QIRD_4MTYPE";
		qirdDbFields[ tableFldConstants.m4type.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "QIRD_TYPE";
		qirdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'C';

		qirdDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldName = "QIRD_BACKLOGFLAG";
		qirdDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldType = 'C';

		qirdDbFields[ tableFldConstants.referenceid.ordinal() ].fieldName = "QIRD_REFERENCEID";
		qirdDbFields[ tableFldConstants.referenceid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "QIRD_REMARKS";
		qirdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.wwmasterid.ordinal() ].fieldName = "QIRD_WWMASTERID";
		qirdDbFields[ tableFldConstants.wwmasterid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.entrytype.ordinal() ].fieldName = "QIRD_ENTRYTYPE";
		qirdDbFields[ tableFldConstants.entrytype.ordinal() ].fieldType = 'C';

		qirdDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "QIRD_MASTERID";
		qirdDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.qhb_keyid.ordinal() ].fieldName = "QIRD_QHB_KEYID";
		qirdDbFields[ tableFldConstants.qhb_keyid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.plrk_keyid.ordinal() ].fieldName = "QIRD_PLRK_KEYID";
		qirdDbFields[ tableFldConstants.plrk_keyid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.subprocessid.ordinal() ].fieldName = "QIRD_SUBPROCESSID";
		qirdDbFields[ tableFldConstants.subprocessid.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.qty.ordinal() ].fieldName = "QIRD_QTY";
		qirdDbFields[ tableFldConstants.qty.ordinal() ].fieldType = 'N';

		qirdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QIRD_TEMPFIELD4";
		qirdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QIRD_TEMPFIELD5";
		qirdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QIRD_ACTIVE";
		qirdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qirdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QIRD_CREATEDBY";
		qirdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qirdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QIRD_CREATEDON";
		qirdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qirdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QIRD_MODIFIEDON";
		qirdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_INTREJECTIONDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_INTREJECTIONDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_INTREJECTIONDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

