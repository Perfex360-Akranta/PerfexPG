package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.MocRfcmstSql.tableFldConstants;

public class MocRfcBasismstSql {

	public static final String TBL_MOC_TL_RFCBASIS	="MOC_TL_RFCBASIS";  

	TableFieldType [] rfcb_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,rfcid,basisid,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getrfcb_DbFields() {
		return rfcb_DbFields;
	}

	public MocRfcBasismstSql()
	{
		rfcb_DbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			rfcb_DbFields[ i ] = new TableFieldType();
		}
		
		
		rfcb_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOC_BAM_KEYID";
		rfcb_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		rfcb_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldName = "MOC_BAM_RFCKEYID";
		rfcb_DbFields[ tableFldConstants.rfcid.ordinal() ].fieldType = 'V';
		
	    rfcb_DbFields[ tableFldConstants.basisid.ordinal() ].fieldName = "MOC_BAM_BASISID";
		rfcb_DbFields[ tableFldConstants.basisid.ordinal() ].fieldType = 'V';
	
	    rfcb_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOC_BAM_TEMPFIELD1";
		rfcb_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rfcb_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOC_BAM_TEMPFIELD2";
		rfcb_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		rfcb_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOC_BAM_TEMPFIELD3";
		rfcb_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		rfcb_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOC_BAM_TEMPFIELD4";
		rfcb_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rfcb_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOC_BAM_TEMPFIELD5";
		rfcb_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		rfcb_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOC_BAM_CREATEDBY";
		rfcb_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
    	rfcb_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOC_BAM_ACTIVE";
		rfcb_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';


		rfcb_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOC_BAM_CREATEDON";
		rfcb_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rfcb_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOC_BAM_MODIFIEDON ";
		rfcb_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_RFCBASIS, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_RFCBASIS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_RFCBASIS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_RFCBASIS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String DeleteRfcBasisByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_RFCBASIS;
	    sql += " WHERE MOC_BAM_KEYID = '" + keyid + "'";
	    return sql;
	}
	
}

