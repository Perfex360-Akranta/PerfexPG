package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;

//import sun.swing.UIAction;

public class WhatifDtlSql {

	public static final String TBL_MOC_TL_WHATIFDTL = "MOC_TL_WHATIFDTL";  
	public static final String TBL_MOC_TL_RECCOMENDATIONS = "MOC_TL_RECCOMENDATIONS";  
	public static final String TBL_GEN_TL_ACTIONPLANDTL = "GEN_TL_ACTIONPLANDTL";  
	public static final String TBL_GEN_TL_ACTIONPLANMST = "GEN_TL_ACTIONPLANMST";

	TableFieldType [] wifdDbFields = null;

	public enum   tableFldConstants
	{
		keyid,wifmkeyid,rownum,whatif,causes,consequences,
		withoutsafeguards,likehood1,severity1,risk1,recommendations,
		withsafeguards,likehood2,severity2,risk2,remarks,
		tempfield1 ,tempfield2,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getwifdDbFields() {
		return wifdDbFields;
	}

	public WhatifDtlSql()
	{
		wifdDbFields = new TableFieldType[ 25];
		for(int i = 0;i <25; i++)
		{	
			wifdDbFields[ i ] = new TableFieldType();
		}
		wifdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WIFD_KEYID";
		wifdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.wifmkeyid.ordinal() ].fieldName = "WIFD_WIFM_KEYID";
		wifdDbFields[ tableFldConstants.wifmkeyid.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.rownum.ordinal() ].fieldName = "WIFD_ROWNUM";
		wifdDbFields[ tableFldConstants.rownum.ordinal() ].fieldType = 'N';

		wifdDbFields[ tableFldConstants.whatif.ordinal() ].fieldName = "WIFD_WHATIF";
		wifdDbFields[ tableFldConstants.whatif.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.causes.ordinal() ].fieldName = "WIFD_CAUSES";
		wifdDbFields[ tableFldConstants.causes.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.consequences.ordinal() ].fieldName = "WIFD_CONSEQUENCES";
		wifdDbFields[ tableFldConstants.consequences.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.withoutsafeguards.ordinal() ].fieldName = "WIFD_WITHOUTSAFEGUARDS";
		wifdDbFields[ tableFldConstants.withoutsafeguards.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.likehood1.ordinal() ].fieldName = "WIFD_LIKEHOOD1";
		wifdDbFields[ tableFldConstants.likehood1.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.severity1.ordinal() ].fieldName = "WIFD_SEVERITY1";
		wifdDbFields[ tableFldConstants.severity1.ordinal() ].fieldType = 'V';
		
		
		wifdDbFields[ tableFldConstants.risk1.ordinal() ].fieldName = "WIFD_RISK1";
		wifdDbFields[ tableFldConstants.risk1.ordinal() ].fieldType = 'N';
		
		wifdDbFields[ tableFldConstants.recommendations.ordinal() ].fieldName = "WIFD_RECOMMENDATIONS";
		wifdDbFields[ tableFldConstants.recommendations.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.withsafeguards.ordinal() ].fieldName = "WIFD_WITHSAFEGUARDS";
		wifdDbFields[ tableFldConstants.withsafeguards.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.likehood2.ordinal() ].fieldName = "WIFD_LIKEHOOD2";
		wifdDbFields[ tableFldConstants.likehood2.ordinal() ].fieldType = 'V';
		
		wifdDbFields[ tableFldConstants.severity2.ordinal() ].fieldName = "WIFD_SEVERITY2";
		wifdDbFields[ tableFldConstants.severity2.ordinal() ].fieldType = 'V';
				
		wifdDbFields[ tableFldConstants.risk2.ordinal() ].fieldName = "WIFD_RISK2";
		wifdDbFields[ tableFldConstants.risk2.ordinal() ].fieldType = 'N';
		
		wifdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WIFD_REMARKS";
		wifdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		
		wifdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "WIFD_TEMPFIELD1";
		wifdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "WIFD_TEMPFIELD2";
		wifdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "WIFD_TEMPFIELD3";
		wifdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "WIFD_TEMPFIELD4";
		wifdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "WIFD_TEMPFIELD5";
		wifdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';


		wifdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WIFD_ACTIVE";
		wifdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wifdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WIFD_CREATEDBY";
		wifdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wifdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WIFD_CREATEDON";
		wifdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wifdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName ="WIFD_MODIFIEDON";
		wifdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MOC_TL_WHATIFDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_WHATIFDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_MOC_TL_WHATIFDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
		}
	
	public static String DeleteWhatifRow (String keyid) {
	    String sql = "DELETE FROM " + TBL_MOC_TL_WHATIFDTL;
	    sql += " WHERE WIFD_KEYID = '" + keyid + "'";
	    return sql;
	}
	
	
	public static String DeleteMocRecRow(String keyid) 
	{
		//DELETE FROM MOC_TL_RECCOMENDATIONS
		//WHERE
		//MOCR_WH_KEYID = ''
		String sql = "DELETE FROM " + TBL_MOC_TL_RECCOMENDATIONS;
	    sql += " WHERE MOCR_WH_KEYID = '" + keyid + "'";
	    return sql;
	}
	
	public static String DeleteActionPlanDetail(String keyid) 
	{
		//DELETE FROM MOC_TL_RECCOMENDATIONS
		//WHERE
		//MOCR_WH_KEYID = ''
		String sql = "DELETE FROM " + TBL_GEN_TL_ACTIONPLANDTL;
	    sql += " WHERE APLD_APLM_KEYID = '" + keyid + "'";
	    return sql;
	}
	
	public static String DeleteActionPlanMaster(String keyid) 
	{
		//DELETE FROM MOC_TL_RECCOMENDATIONS
		//WHERE
		//MOCR_WH_KEYID = ''
		String sql = "DELETE FROM " + TBL_GEN_TL_ACTIONPLANMST;
	    sql += " WHERE APLM_KEYID = '" + keyid + "'";
	    return sql;
	}
	
}

