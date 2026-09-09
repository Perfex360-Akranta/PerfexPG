package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class QtmTlTestscrapbrkupSql {

	public static final String TBL_QTM_TL_TESTSCRAPBRKUP = "QTM_TL_TESTSCRAPBRKUP";  

	TableFieldType [] qsbrDbFields = null;

	public enum   tableFldConstants
	{
		rejectionid, masterid, quantity, remarks, backlogflag, referencekeyid
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getQsbrDbFields() {
		return qsbrDbFields;
	}

	public QtmTlTestscrapbrkupSql()
	{
		qsbrDbFields = new TableFieldType[ 10 ];
		for(int i = 0;i < 10; i++)
		{	
			qsbrDbFields[ i ] = new TableFieldType();
		}
		qsbrDbFields[ tableFldConstants.rejectionid.ordinal() ].fieldName = "QSBR_REJECTIONID";
		qsbrDbFields[ tableFldConstants.rejectionid.ordinal() ].fieldType = 'V';

		qsbrDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "QSBR_MASTERID";
		qsbrDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		qsbrDbFields[ tableFldConstants.quantity.ordinal() ].fieldName = "QSBR_QUANTITY";
		qsbrDbFields[ tableFldConstants.quantity.ordinal() ].fieldType = 'N';

		qsbrDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "QSBR_REMARKS";
		qsbrDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		qsbrDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldName = "QSBR_BACKLOGFLAG";
		qsbrDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldType = 'C';

		qsbrDbFields[ tableFldConstants.referencekeyid.ordinal() ].fieldName = "QSBR_REFERENCEKEYID";
		qsbrDbFields[ tableFldConstants.referencekeyid.ordinal() ].fieldType = 'V';

		qsbrDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QSBR_ACTIVE";
		qsbrDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qsbrDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QSBR_CREATEDBY";
		qsbrDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qsbrDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QSBR_CREATEDON";
		qsbrDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qsbrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QSBR_MODIFIEDON";
		qsbrDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_TESTSCRAPBRKUP, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_TESTSCRAPBRKUP, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.masterid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.masterid.ordinal() ] + "'";
		sql += " AND " + fieldTypeArr[tableFldConstants.rejectionid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.rejectionid.ordinal() ] + "'";
		sql += " AND " + fieldTypeArr[tableFldConstants.referencekeyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.referencekeyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_TESTSCRAPBRKUP ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.masterid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.masterid.ordinal()] + "'";
		return sql;
	}
	public static String checkBreakupExist(String rejId,String mstId,String refId)
	{
		String sql = "SELECT COUNT(*) FROM "+TBL_QTM_TL_TESTSCRAPBRKUP ;
			   sql += " where QSBR_REJECTIONID = '"+rejId+"' AND QSBR_MASTERID = '"+mstId+"'";
			   sql += " AND QSBR_REFERENCEKEYID = '"+refId+"'";
			   CommonMessage.debugMsg(sql);
	    return sql;
	}

}

