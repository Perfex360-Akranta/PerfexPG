package com.akranta.tpm.dao.sql;

//import com.akranta.tpm.dao.sql.SheTlDailyauditdtlSql.tableFldConstants;
import com.akranta.tpm.model.CommonFilter;

public class SheTlPlantSafetydtlSql {
	public static final String TBL_SHE_TL_PLANTSAFETYDTL = "SHE_TL_PLANTSAFETYDTL";  

	TableFieldType [] spsdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, spsd_keyid, observe, type,relatedto, recomaction, correcaction, preventiveaction
		, responsibilty, targetdate,reponseremarks, status, actiontaken, completedby
		,completedate,completeremarks,verified,verifiedby,verifieddate,verifeidremarks,tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon,reviseddate
	}
	public TableFieldType[] getSpsdDbFields() {
		return spsdDbFields;
	}

	public SheTlPlantSafetydtlSql()
	{
		spsdDbFields = new TableFieldType[ 30];
		for(int i = 0;i < 30; i++)
		{	
			spsdDbFields[ i ] = new TableFieldType();
		}
		spsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SPSD_KEYID";
		spsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.spsd_keyid.ordinal() ].fieldName = "SPSD_SPSM_KEYID";
		spsdDbFields[ tableFldConstants.spsd_keyid.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.observe.ordinal() ].fieldName = "SPSD_OBSERVATIONS";
		spsdDbFields[ tableFldConstants.observe.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.type.ordinal() ].fieldName = "SPSD_TYPE";
		spsdDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';
		
		
		spsdDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "SPSD_RELATEDTO";
		spsdDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';
		
		spsdDbFields[ tableFldConstants.recomaction.ordinal() ].fieldName = "SPSD_RECCOMACTION";
		spsdDbFields[ tableFldConstants.recomaction.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.correcaction.ordinal() ].fieldName = "SPSD_CORRECACTION";
		spsdDbFields[ tableFldConstants.correcaction.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldName = "SPSD_PREVACTION";
		spsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.responsibilty.ordinal() ].fieldName = "SPSD_RESPONSIBILITY";
		spsdDbFields[ tableFldConstants.responsibilty.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "SPSD_TARGETDATE";
		spsdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';
		
		spsdDbFields[ tableFldConstants.reponseremarks.ordinal() ].fieldName = "SPSD_RESPREMARKS";
		spsdDbFields[ tableFldConstants.reponseremarks.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SPSD_STATUs";
		spsdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "SPSD_ACTIONTAKEN";
		spsdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "SPSD_COMPLETEDBY";
		spsdDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.completedate.ordinal() ].fieldName = "SPSD_COMPLETEDDATE";
		spsdDbFields[ tableFldConstants.completedate.ordinal() ].fieldType = 'D';
		
		spsdDbFields[ tableFldConstants.completeremarks.ordinal() ].fieldName = "SPSD_COMPLETEREMARKS";
		spsdDbFields[ tableFldConstants.completeremarks.ordinal() ].fieldType = 'V';
		
		spsdDbFields[ tableFldConstants.verified.ordinal() ].fieldName = "SPSD_VERIFIED";
		spsdDbFields[ tableFldConstants.verified.ordinal() ].fieldType = 'V';
		
		spsdDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "SPSD_VERIFIEDBY";
		spsdDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';
		
		spsdDbFields[ tableFldConstants.verifieddate.ordinal() ].fieldName = "SPSD_VERIFIEDDATE";
		spsdDbFields[ tableFldConstants.verifieddate.ordinal() ].fieldType = 'V';
		
		spsdDbFields[ tableFldConstants.verifeidremarks.ordinal() ].fieldName = "SPSD_VERIFYREMARKS";
		spsdDbFields[ tableFldConstants.verifeidremarks.ordinal() ].fieldType = 'V';
		

		spsdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SPSD_TEMPFIELD1";
		spsdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SPSD_TEMPFIELD2";
		spsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SPSD_TEMPFIELD3";
		spsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SPSD_TEMPFIELD4";
		spsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SPSD_TEMPFIELD5";
		spsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		spsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SPSD_ACTIVE";
		spsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		spsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SPSD_CREATEDBY";
		spsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

	   spsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SPSD_CREATEDON";
		spsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		spsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SPSD_MODIFIEDON";
		spsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		spsdDbFields[ tableFldConstants.reviseddate.ordinal() ].fieldName = "SPSD_REVISEDDATE";
		spsdDbFields[ tableFldConstants.reviseddate.ordinal() ].fieldType = 'V';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_PLANTSAFETYDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_PLANTSAFETYDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_PLANTSAFETYDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public String getDtlsDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql = "DELETE from SHE_TL_PLANTSAFETYDTL ";
	
		sql += " where SPSD_KEYID" +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	} 
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_SHE_TL_PLANTSAFETYDTL +" where SPSD_SPSM_KEYID=?";
		return sql;
	}
	public static String getDeletedtlSql(String dtlid)
	{
		String sql = "Delete from " + TBL_SHE_TL_PLANTSAFETYDTL +" where SPSD_KEYID= '"+dtlid+"'";
		return sql;
	}
	public static String resupdate(String dtlid,String Resid,String targetdate) {


		StringBuffer sql  = new StringBuffer();
		 sql.append(" UPDATE " + TBL_SHE_TL_PLANTSAFETYDTL + " SET SPSD_RESPONSIBILITY= '"+Resid+"'");
			sql.append(" ,SPSD_TARGETDATE= '"+targetdate+"'");
			sql.append(" WHERE 1=1 AND SPSD_KEYID='"+dtlid+"'"); 		
		
		return sql.toString();
	}
	public static String verifyupdate(String dtlid,String Verid,String verifydate) {


		StringBuffer sql  = new StringBuffer();
		 sql.append(" UPDATE " + TBL_SHE_TL_PLANTSAFETYDTL + " SET SPSD_RESPONSIBILITY= '"+Verid+"'");
			sql.append(" ,SPSD_TARGETDATE= '"+verifydate+"'");
			sql.append(" WHERE 1=1 AND SPSD_KEYID='"+dtlid+"'"); 		
		
		return sql.toString();
	}
}


