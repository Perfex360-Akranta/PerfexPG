package com.akranta.tpm.dao.sql;



public class MocClosureSql {

	public static final String TBL_MOC_TL_CLOSUREMASTER	="MOC_TL_CLOSUREMASTER";  

	TableFieldType [] rfcc_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterrfcid,closureid,sortorder,responseY,responseN,tempfield1,tempfield2,tempfield3,
		tempfield4,tempfield5,createdby,active,createdon,modifiedon
	}

	public TableFieldType[] getrfcc_DbFields() {
		return rfcc_DbFields;
	}

	public MocClosureSql()
	{
		rfcc_DbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			rfcc_DbFields[ i ] = new TableFieldType();
		}
		
		
		rfcc_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOCL_KEYID";
		rfcc_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		rfcc_DbFields[ tableFldConstants.masterrfcid.ordinal() ].fieldName = "  MOCL_RFC_KEYID";
		rfcc_DbFields[ tableFldConstants.masterrfcid.ordinal() ].fieldType = 'V';
		
	    rfcc_DbFields[ tableFldConstants.closureid.ordinal() ].fieldName = "MOCL_CLQ_KEYID";
		rfcc_DbFields[ tableFldConstants.closureid.ordinal() ].fieldType = 'V';
		
	    rfcc_DbFields[ tableFldConstants.sortorder.ordinal() ].fieldName = "MOCL_SORTORDER";
		rfcc_DbFields[ tableFldConstants.sortorder.ordinal() ].fieldType = 'V';
		
		
		
		rfcc_DbFields[ tableFldConstants.responseY.ordinal() ].fieldName = "MOCL_RESPONSEY";
		rfcc_DbFields[ tableFldConstants.responseY.ordinal() ].fieldType = 'V';
		
		rfcc_DbFields[ tableFldConstants.responseN.ordinal() ].fieldName = "MOCL_RESPONSEN";
		rfcc_DbFields[ tableFldConstants.responseN.ordinal() ].fieldType = 'V';
	
	    rfcc_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOCL_TEMPFIELD1";
		rfcc_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rfcc_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOCL_TEMPFIELD2";
		rfcc_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		rfcc_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOCL_TEMPFIELD3";
		rfcc_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		rfcc_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOCL_TEMPFIELD4";
		rfcc_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rfcc_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOCL_TEMPFIELD5";
		rfcc_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		rfcc_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOCL_CREATEDBY";
		rfcc_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		
    	rfcc_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOCL_ACTIVE";
		rfcc_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';


		rfcc_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOCL_CREATEDON";
		rfcc_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rfcc_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOCL_MODIFIEDON ";
		rfcc_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
	
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_RFCBASIS, fieldTypeArr, dataArray));
		System.out.println("to be cont...");
		return SqlUtils.getInsertSql(TBL_MOC_TL_CLOSUREMASTER, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_CLOSUREMASTER, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_CLOSUREMASTER ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String DeleteClosureRowByMoc(String keyid) 
	{
		//DELETE FROM MOC_TL_RECCOMENDATIONS
		//WHERE
		//MOCR_WH_KEYID = ''
		String sql = "DELETE FROM " + TBL_MOC_TL_CLOSUREMASTER;
	    sql += " WHERE MOCL_RFC_KEYID = '" + keyid + "'";
	    return sql;
	}


}

