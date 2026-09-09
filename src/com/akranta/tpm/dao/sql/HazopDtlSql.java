package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;

//import sun.swing.UIAction;

public class HazopDtlSql {

	public static final String TBL_MOC_TL_HAZOPDTL = "MOC_TL_HAZOPDTL";  
	public static final String TBL_MOC_TL_RECCOMENDATIONS = "MOC_TL_RECCOMENDATIONS";  
	public static final String TBL_GEN_TL_ACTIONPLANDTL = "GEN_TL_ACTIONPLANDTL";  
	public static final String TBL_GEN_TL_ACTIONPLANMST = "GEN_TL_ACTIONPLANMST";

	TableFieldType [] mohdDbFields = null;

	public enum   tableFldConstants
	{
		keyid,mohmkeyid,rownum,guideword,parameter,deviation,causes,consequences,
		withoutsafeguards,likehood1,severity1,risk1,recommendations,
		withsafeguards,likehood2,severity2,risk2,remarks,
		responsibility,target,status,tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getmohdDbFields() {
		return mohdDbFields;
	}

	public HazopDtlSql()
	{
		mohdDbFields = new TableFieldType[ 27];
		for(int i = 0;i <27; i++)
		{	
			mohdDbFields[ i ] = new TableFieldType();
		}
		mohdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOHD_KEYID";
		mohdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.mohmkeyid.ordinal() ].fieldName = "MOHD_MOHM_KEYID";
		mohdDbFields[ tableFldConstants.mohmkeyid.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.rownum.ordinal() ].fieldName = "MOHD_ROWNUM";
		mohdDbFields[ tableFldConstants.rownum.ordinal() ].fieldType = 'N';

		mohdDbFields[ tableFldConstants.guideword.ordinal() ].fieldName = "MOHD_GUIDEWORD";
		mohdDbFields[ tableFldConstants.guideword.ordinal() ].fieldType = 'V';
		mohdDbFields[ tableFldConstants.parameter.ordinal() ].fieldName = "MOHD_PARAMETER";
		mohdDbFields[ tableFldConstants.parameter.ordinal() ].fieldType = 'V';
		mohdDbFields[ tableFldConstants.deviation.ordinal() ].fieldName = "MOHD_DEVIATION";
		mohdDbFields[ tableFldConstants.deviation.ordinal() ].fieldType = 'V';
	
		mohdDbFields[ tableFldConstants.causes.ordinal() ].fieldName = "MOHD_CAUSES";
		mohdDbFields[ tableFldConstants.causes.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.consequences.ordinal() ].fieldName = "MOHD_CONSEQUENCES";
		mohdDbFields[ tableFldConstants.consequences.ordinal() ].fieldType = 'V';

		mohdDbFields[ tableFldConstants.withoutsafeguards.ordinal() ].fieldName = "MOHD_WITHOUTSAFEGUARDS";
		mohdDbFields[ tableFldConstants.withoutsafeguards.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.likehood1.ordinal() ].fieldName = "MOHD_LIKEHOOD1";
		mohdDbFields[ tableFldConstants.likehood1.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.severity1.ordinal() ].fieldName = "MOHD_SEVERITY1";
		mohdDbFields[ tableFldConstants.severity1.ordinal() ].fieldType = 'V';
		
		
		mohdDbFields[ tableFldConstants.risk1.ordinal() ].fieldName = "MOHD_RISK1";
		mohdDbFields[ tableFldConstants.risk1.ordinal() ].fieldType = 'N';
		
		mohdDbFields[ tableFldConstants.recommendations.ordinal() ].fieldName = "MOHD_RECOMMENDATIONS";
		mohdDbFields[ tableFldConstants.recommendations.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.withsafeguards.ordinal() ].fieldName = "MOHD_WITHSAFEGUARDS";
		mohdDbFields[ tableFldConstants.withsafeguards.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.likehood2.ordinal() ].fieldName = "MOHD_LIKEHOOD2";
		mohdDbFields[ tableFldConstants.likehood2.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.severity2.ordinal() ].fieldName = "MOHD_SEVERITY2";
		mohdDbFields[ tableFldConstants.severity2.ordinal() ].fieldType = 'V';
				
		mohdDbFields[ tableFldConstants.risk2.ordinal() ].fieldName = "MOHD_RISK2";
		mohdDbFields[ tableFldConstants.risk2.ordinal() ].fieldType = 'N';
		
		mohdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MOHD_REMARKS";
		mohdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
		mohdDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "MOHD_RESPONSIBILITY";
		mohdDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		mohdDbFields[ tableFldConstants.target.ordinal() ].fieldName = "MOHD_TARGET";
		mohdDbFields[ tableFldConstants.target.ordinal() ].fieldType = 'D';

		mohdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MOHD_STATUS";
		mohdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		mohdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOHD_TEMPFIELD4";
		mohdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		mohdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOHD_TEMPFIELD5";
		mohdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';


		mohdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOHD_ACTIVE";
		mohdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mohdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOHD_CREATEDBY";
		mohdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mohdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOHD_CREATEDON";
		mohdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mohdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName ="MOHD_MODIFIEDON";
		mohdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MOC_TL_HAZOPDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_HAZOPDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_MOC_TL_HAZOPDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
		}
	
	public static String DeleteHazopRow(String keyid) {
	    String sql = "DELETE FROM " + TBL_MOC_TL_HAZOPDTL;
	    sql += " WHERE MOHD_KEYID = '" + keyid + "'";
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
	public static String DeleteHazopRowByMst(String keyid) {
	    String sql = "DELETE FROM " + TBL_MOC_TL_HAZOPDTL;
	    sql += " WHERE MOHD_MOHM_KEYID = '" + keyid + "'";
	    return sql;
	}
}

