package com.akranta.tpm.dao.sql;



public class MocPssrdtlSql {

	public static final String TBL_MOC_TL_PSSRCHECKLISTDTL	="MOC_TL_PSSRCHECKLISTDTL";  

	TableFieldType [] psrd_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,rfcid,mocid,rownum,date,mocdetails,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getpsrd_DbFields() {
		return psrd_DbFields;
	}

	public MocPssrdtlSql()
	{
		psrd_DbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			psrd_DbFields[ i ] = new TableFieldType();
		}
		
		
		psrd_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PSRD_KEYID";
		psrd_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		psrd_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldName = "PSRD_PSRM_KEYID";
		psrd_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldType = 'V';
		
		psrd_DbFields[ tableFldConstants.mocid.ordinal() ].fieldName = "PSRD_RFCM_KEYID";
		psrd_DbFields[ tableFldConstants.mocid.ordinal() ].fieldType = 'V';
		
	    psrd_DbFields[ tableFldConstants.rownum.ordinal() ].fieldName = "PSRD_rownum";
		psrd_DbFields[ tableFldConstants.rownum.ordinal() ].fieldType = '1';
		
	    psrd_DbFields[ tableFldConstants.date.ordinal() ].fieldName = "PSRD_MOC_PCM_KEYID";
		psrd_DbFields[ tableFldConstants.date.ordinal() ].fieldType = 'V';
		
		 psrd_DbFields[ tableFldConstants.mocdetails.ordinal() ].fieldName = "PSRD_OBSERVATION";
		psrd_DbFields[ tableFldConstants.mocdetails.ordinal() ].fieldType = 'V';
			
	
	    psrd_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PSRD_TEMPFIELD1";
		psrd_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		psrd_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PSRD_TEMPFIELD2";
		psrd_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		psrd_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PSRD_TEMPFIELD3";
		psrd_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		psrd_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PSRD_TEMPFIELD4";
		psrd_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		psrd_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PSRD_TEMPFIELD5";
		psrd_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		psrd_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSRD_CREATEDBY";
		psrd_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
    	psrd_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "PSRD_ACTIVE";
		psrd_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';


		psrd_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSRD_CREATEDON";
		psrd_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		psrd_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSRD_MODIFIEDON ";
		psrd_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_PSSRCHECKLISTDTL, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_PSSRCHECKLISTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_PSSRCHECKLISTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_PSSRCHECKLISTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeletePssrDtlByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_PSSRCHECKLISTDTL;
	    sql += " WHERE PSRD_RFCM_KEYID = '" + keyid + "'";
	    return sql;
	}
	
}

