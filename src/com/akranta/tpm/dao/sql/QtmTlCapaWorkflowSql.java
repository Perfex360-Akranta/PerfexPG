package com.akranta.tpm.dao.sql;
public class QtmTlCapaWorkflowSql{
	public static final String TBL_QTM_TL_CAPAWORKFLOW= "QTM_TL_CAPAWORKFLOW";  
	TableFieldType [] qcwfDbFields = null;

	public enum   tableFldConstants
	{
		keyid,caparefid,roleid,approvedby,status,date,
        remarks,tempfield1,tempfield2,tempfield3,tempfield4,
        active,createdby,createdon,modifiedon
	}
	
	public TableFieldType[] getqcwfDbFields() {
		return qcwfDbFields;
	}

	public QtmTlCapaWorkflowSql()
	{
		qcwfDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			qcwfDbFields[ i ] = new TableFieldType();
		}
		qcwfDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QCWF_KEYID";
		qcwfDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.caparefid.ordinal() ].fieldName = "QCWF_CAPAREFID";
		qcwfDbFields[ tableFldConstants.caparefid.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "QCWF_ROLEID";
		qcwfDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "QCWF_APPROVEDBY";
		qcwfDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.status.ordinal() ].fieldName = "QCWF_STATUS";
		qcwfDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.date.ordinal() ].fieldName = "QCWF_DATE";
		qcwfDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		qcwfDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "QCWF_REMARKS";
		qcwfDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QCWF_TEMPFIELD1";
		qcwfDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QCWF_TEMPFIELD2";
		qcwfDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QCWF_TEMPFIELD3";
		qcwfDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QCWF_TEMPFIELD4";
		qcwfDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

	    qcwfDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QCWF_ACTIVE";
		qcwfDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qcwfDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QCWF_CREATEDBY";
		qcwfDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qcwfDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QCWF_CREATEDON";
		qcwfDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qcwfDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QCWF_MODIFIEDON";
		qcwfDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_CAPAWORKFLOW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_CAPAWORKFLOW, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_CAPAWORKFLOW ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

