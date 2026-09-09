package com.akranta.tpm.dao.sql;

public class QtmTlQpointdtlSql {

	public static final String TBL_QTM_TL_QPOINTDTL = "QTM_TL_QPOINTDTL";  

	TableFieldType [] qpdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, qpmkeyid, qparameter, specification, effectofparam, measuringequip
		, monitoringmethod, fourm, frequency, tempfield1, tempfield2
		, tempfield3, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getQpdDbFields() {
		return qpdDbFields;
	}

	public QtmTlQpointdtlSql()
	{
		qpdDbFields = new TableFieldType[ 17 ];
		for(int i = 0;i < 17; i++)
		{	
			qpdDbFields[ i ] = new TableFieldType();
		}
		qpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QPD_KEYID";
		qpdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.qpmkeyid.ordinal() ].fieldName = "QPD_QPMKEYID";
		qpdDbFields[ tableFldConstants.qpmkeyid.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.qparameter.ordinal() ].fieldName = "QPD_QPARAMETER";
		qpdDbFields[ tableFldConstants.qparameter.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.specification.ordinal() ].fieldName = "QPD_SPECIFICATION";
		qpdDbFields[ tableFldConstants.specification.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.effectofparam.ordinal() ].fieldName = "QPD_EFFECTOFPARAM";
		qpdDbFields[ tableFldConstants.effectofparam.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.measuringequip.ordinal() ].fieldName = "QPD_MEASURINGEQUIP";
		qpdDbFields[ tableFldConstants.measuringequip.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.monitoringmethod.ordinal() ].fieldName = "QPD_MONITORINGMETHOD";
		qpdDbFields[ tableFldConstants.monitoringmethod.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.fourm.ordinal() ].fieldName = "QPD_FOURM";
		qpdDbFields[ tableFldConstants.fourm.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "QPD_FREQUENCY";
		qpdDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QPD_TEMPFIELD1";
		qpdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		qpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QPD_TEMPFIELD2";
		qpdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		qpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QPD_TEMPFIELD3";
		qpdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		qpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QPD_TEMPFIELD4";
		qpdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		qpdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QPD_ACTIVE";
		qpdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QPD_CREATEDBY";
		qpdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QPD_CREATEDON";
		qpdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QPD_MODIFIEDON";
		qpdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_QPOINTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_QPOINTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_QPOINTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getalldetail() {
		// TODO Auto-generated method stub
		 String sql="select * from " + TBL_QTM_TL_QPOINTDTL  + " where QPD_KEYID= ?";
			
			// TODO Auto-generated method stub
			return sql;
	}

}

