package com.akranta.tpm.dao.sql;



public class MocPssrmstSql {

	public static final String TBL_MOC_TL_PSSRCHECKLISTMST	="MOC_TL_PSSRCHECKLISTMST";  

	TableFieldType [] psrm_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,rfcid,process,date,mocdetails,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getpsrm_DbFields() {
		return psrm_DbFields;
	}

	public MocPssrmstSql()
	{
		psrm_DbFields = new TableFieldType[ 14 ];
		for(int i = 0;i < 14; i++)
		{	
			psrm_DbFields[ i ] = new TableFieldType();
		}
		
		
		psrm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PSRM_KEYID";
		psrm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		psrm_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldName = "PSRM_RFCKEYID";
		psrm_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldType = 'V';
		
	    psrm_DbFields[ tableFldConstants.process.ordinal() ].fieldName = "PSRM_PROCESS";
		psrm_DbFields[ tableFldConstants.process.ordinal() ].fieldType = 'V';
		
	    psrm_DbFields[ tableFldConstants.date.ordinal() ].fieldName = "PSRM_DATE";
		psrm_DbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';
		
		 psrm_DbFields[ tableFldConstants.mocdetails.ordinal() ].fieldName = "PSRM_MOCDETAILS";
		psrm_DbFields[ tableFldConstants.mocdetails.ordinal() ].fieldType = 'V';
			
	
	    psrm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PSRM_TEMPFIELD1";
		psrm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		psrm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PSRM_TEMPFIELD2";
		psrm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		psrm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PSRM_TEMPFIELD3";
		psrm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		psrm_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PSRM_TEMPFIELD4";
		psrm_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		psrm_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PSRM_TEMPFIELD5";
		psrm_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		psrm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PSRM_CREATEDBY";
		psrm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
    	psrm_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "PSRM_ACTIVE";
		psrm_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';


		psrm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PSRM_CREATEDON";
		psrm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		psrm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PSRM_MODIFIEDON ";
		psrm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_PSSRCHECKLISTMST, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_PSSRCHECKLISTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_PSSRCHECKLISTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_PSSRCHECKLISTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String DeletePssrMstbyMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_PSSRCHECKLISTMST;
	    sql += " WHERE PSRM_RFCKEYID = '" + keyid + "'";
	    return sql;
	}


}

