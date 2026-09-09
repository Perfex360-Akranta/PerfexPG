package com.akranta.tpm.dao.sql;



public class MocRfcQuestionsSql {

	public static final String TBL_MOC_TL_RFCQST	="MOC_TL_RFCQST";  

	TableFieldType [] rfcq_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterrfcid,questionaireid,sortorder,response,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getrfcq_DbFields() {
		return rfcq_DbFields;
	}

	public MocRfcQuestionsSql()
	{
		rfcq_DbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			rfcq_DbFields[ i ] = new TableFieldType();
		}
		
		
		rfcq_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOC_RFQ_KEYID";
		rfcq_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		rfcq_DbFields[ tableFldConstants.masterrfcid.ordinal() ].fieldName = "MOC_QRFC_KEYID";
		rfcq_DbFields[ tableFldConstants.masterrfcid.ordinal() ].fieldType = 'V';
		
	    rfcq_DbFields[ tableFldConstants.questionaireid.ordinal() ].fieldName = "MOC_QRQM_KEYID";
		rfcq_DbFields[ tableFldConstants.questionaireid.ordinal() ].fieldType = 'V';
		
	    rfcq_DbFields[ tableFldConstants.sortorder.ordinal() ].fieldName = "MOC_RFQ_SORTORDER";
		rfcq_DbFields[ tableFldConstants.sortorder.ordinal() ].fieldType = 'V';
		
		
	    rfcq_DbFields[ tableFldConstants.response.ordinal() ].fieldName = "MOC_RFQ_RESPONSE";
		rfcq_DbFields[ tableFldConstants.response.ordinal() ].fieldType = 'V';
		
	
	
	    rfcq_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOC_RFQ_TEMPFIELD1";
		rfcq_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rfcq_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOC_RFQ_TEMPFIELD2";
		rfcq_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		rfcq_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOC_RFQ_TEMPFIELD3";
		rfcq_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		rfcq_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOC_RFQ_TEMPFIELD4";
		rfcq_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rfcq_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOC_RFQ_TEMPFIELD5";
		rfcq_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		rfcq_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOC_RFQ_CREATEDBY";
		rfcq_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
    	rfcq_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOC_RFQ_ACTIVE";
		rfcq_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';


		rfcq_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOC_RFQ_CREATEDON";
		rfcq_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rfcq_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOC_RFQ_MODIFIEDON ";
		rfcq_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_RFCBASIS, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_RFCQST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_RFCQST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_RFCQST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String DeleteQuestionByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_RFCQST;
	    sql += " WHERE MOC_QRFC_KEYID = '" + keyid + "'";
	    return sql;
	}


}

