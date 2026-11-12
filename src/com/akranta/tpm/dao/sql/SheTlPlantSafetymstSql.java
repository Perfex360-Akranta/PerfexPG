package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;



public class SheTlPlantSafetymstSql {

	public static final String TBL_SHE_TL_PLANTSAFETYMST= "SHE_TL_PLANTSAFETYMST";  

	TableFieldType [] spsmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, safetydate, doneby, safetytype, members, remarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getspsmDbFields() {
		return spsmDbFields;
	}

	public SheTlPlantSafetymstSql()
	{
		spsmDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			spsmDbFields[ i ] = new TableFieldType();
		}
		spsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SPSM_KEYID";
		spsmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SPSM_FLID";
		spsmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.safetydate.ordinal() ].fieldName = "SPSM_DATE";
		spsmDbFields[ tableFldConstants.safetydate.ordinal() ].fieldType = 'D';

		spsmDbFields[ tableFldConstants.doneby.ordinal() ].fieldName = "SPSM_DONEBY";
		spsmDbFields[ tableFldConstants.doneby.ordinal() ].fieldType = 'V';
		
		spsmDbFields[ tableFldConstants.safetytype.ordinal() ].fieldName = "SPSM_TYPE";
		spsmDbFields[ tableFldConstants.safetytype.ordinal() ].fieldType = 'V';
		
		spsmDbFields[ tableFldConstants.members.ordinal() ].fieldName = "SPSM_MEMBERS";
		spsmDbFields[ tableFldConstants.members.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "SPSM_REMARKS";
		spsmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		
	
		spsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SPSM_TEMPFIELD1";
		spsmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SPSM_TEMPFIELD2";
		spsmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SPSM_TEMPFIELD3";
		spsmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SPSM_TEMPFIELD4";
		spsmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SPSM_TEMPFIELD5";
		spsmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SPSM_ACTIVE";
		spsmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		spsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SPSM_CREATEDBY";
		spsmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		spsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SPSM_CREATEDON";
		spsmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		spsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SPSM_MODIFIEDON";
		spsmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_PLANTSAFETYMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_PLANTSAFETYMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_PLANTSAFETYMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public String getDeleteDtlsSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "DELETE from SHE_TL_PLANTSAFETYDTL ";
	
		sql += " where SPSD_SPSM_KEYID" +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	} 
	
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_SHE_TL_PLANTSAFETYMST +" where SPSM_KEYID=?";
		return sql;
	}
	public static String safetyPopup(CommonFilter commonFilter) {


		StringBuffer sql  = new StringBuffer();
		
			//select EMPM_KEYID txtFrtEmpmKeyid,EMPM_NAME  EMPM_NAME,EMPM_NAME  EMPM_CODE,3 AS DATAORDER from gen_tl_employeemst where EMPM_ACTIVE='Y';
			sql.append(" SELECT EMPM_KEYID KEYID,EMPM_NAME EMPM_NAME,EMPM_CODE EMPM_CODE "); 
			sql.append(" FROM gen_tl_employeemst "); 
			sql.append(" WHERE 1=1 AND EMPM_ACTIVE ='Y' "); 		
		
		return sql.toString();
	}
	public static String getCheckSql(String keyid,String auditflid,String auditdate,String type)
	{
		String sql = "Select count(*) from " + TBL_SHE_TL_PLANTSAFETYMST +" where SPSM_FLID= '"+auditflid+"' AND SPSM_DATE= '"+auditdate+"' AND SPSM_TYPE= '"+type+"'";
		
		//select * from she_tl_plantsafetymst where spsm_keyid='' or spsm_type='' or spsm_date='' or spsm_flid='';
		//SPSD_KEYID= '"+dtlid+"'";
		return sql;
	}
}

