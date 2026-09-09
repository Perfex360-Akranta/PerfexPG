package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;

//import sun.swing.UIAction;

public class WhatifMstSql {

	public static final String TBL_MOC_TL_WHATIFMST = "MOC_TL_WHATIFMST";  

	TableFieldType [] wifmDbFields = null;

	public enum   tableFldConstants
	{
		keyid,mocmkeyid,kzbnkeyid,date,facility,team,
		tempfield1 ,tempfield2,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getwifmDbFields() {
		return wifmDbFields;
	}

	public WhatifMstSql()
	{
		wifmDbFields = new TableFieldType[ 15];
		for(int i = 0;i <15; i++)
		{	
			wifmDbFields[ i ] = new TableFieldType();
		}
		wifmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WIFM_KEYID";
		wifmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		wifmDbFields[ tableFldConstants.mocmkeyid.ordinal() ].fieldName = "WIFM_MOCM_KEYID";
		wifmDbFields[ tableFldConstants.mocmkeyid.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldName = "WIFM_KZBN_KEYID";
		wifmDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "WIFM_DATE";
		wifmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';
		
		wifmDbFields[ tableFldConstants.facility.ordinal() ].fieldName = "WIFM_FACILITY";
		wifmDbFields[ tableFldConstants.facility.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.team.ordinal() ].fieldName = "WIFM_TEAM";
		wifmDbFields[ tableFldConstants.team.ordinal() ].fieldType = 'V';
		
		wifmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WIFM_TEMPFIELD1";
		wifmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WIFM_TEMPFIELD2";
		wifmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WIFM_TEMPFIELD3";
		wifmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "WIFM_TEMPFIELD4";
		wifmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "WIFM_TEMPFIELD5";
		wifmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
	

		wifmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WIFM_ACTIVE";
		wifmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wifmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WIFM_CREATEDBY";
		wifmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wifmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WIFM_CREATEDON";
		wifmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wifmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName ="WIFM_MODIFIEDON";
		wifmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MOC_TL_WHATIFMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_WHATIFMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_MOC_TL_WHATIFMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
		}
	
	public static String DeleteWhatIfMstByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_WHATIFMST;
	    sql += " WHERE WIFM_MOCM_KEYID = '" + keyid + "'";
	    return sql;
	}
}

