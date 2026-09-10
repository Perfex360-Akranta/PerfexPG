package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.BAL_PcsTlPcsBd;

public class BAL_BdmTlDtlSql {

	public static final String TBL_BAL_BDM_TL_DTL = "BAL_BDM_TL_DTL";  

	TableFieldType [] bdanDbFields = null;

	public enum   tableFldConstants
	{
		keyid, bdms_keyid, finalphenomena, finalcause, finalaction, tradeid
		, countermeasure, wwrequired, wwno, rootcause, preventivemeasure
		, rootcauseid, countermeasureid, preventivemeasureid, breakdowntime
		, worktime, classificationid, categoryid, issparesreplaced, alarmno
		, manpowercost, contractorcost, sparescost, othercost, status
		, remarks, actiontakenby, completedby, costcentre, erppoststatus
		, problemseverity, failuretype, isapproved, approverdby, erpnumber
		, otherfailuretype,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBdanDbFields() {
		return bdanDbFields;
	}

	public BAL_BdmTlDtlSql()
	{
		bdanDbFields = new TableFieldType[ 40 ];
		for(int i = 0;i < 40; i++)
		{	
			bdanDbFields[ i ] = new TableFieldType();
		}
		bdanDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BDAN_KEYID";
		bdanDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.bdms_keyid.ordinal() ].fieldName = "BDAN_BDMS_KEYID";
		bdanDbFields[ tableFldConstants.bdms_keyid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldName = "BDAN_FINALPHENOMENA";
		bdanDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.finalcause.ordinal() ].fieldName = "BDAN_FINALCAUSE";
		bdanDbFields[ tableFldConstants.finalcause.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.finalaction.ordinal() ].fieldName = "BDAN_FINALACTION";
		bdanDbFields[ tableFldConstants.finalaction.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "BDAN_TRADEID";
		bdanDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "BDAN_COUNTERMEASURE";
		bdanDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.wwrequired.ordinal() ].fieldName = "BDAN_WWREQUIRED";
		bdanDbFields[ tableFldConstants.wwrequired.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.wwno.ordinal() ].fieldName = "BDAN_WWNO";
		bdanDbFields[ tableFldConstants.wwno.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "BDAN_ROOTCAUSE";
		bdanDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldName = "BDAN_PREVENTIVEMEASURE";
		bdanDbFields[ tableFldConstants.preventivemeasure.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "BDAN_ROOTCAUSEID";
		bdanDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldName = "BDAN_COUNTERMEASUREID";
		bdanDbFields[ tableFldConstants.countermeasureid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldName = "BDAN_PREVENTIVEMEASUREID";
		bdanDbFields[ tableFldConstants.preventivemeasureid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldName = "BDAN_BREAKDOWNTIME";
		bdanDbFields[ tableFldConstants.breakdowntime.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.worktime.ordinal() ].fieldName = "BDAN_WORKTIME";
		bdanDbFields[ tableFldConstants.worktime.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.classificationid.ordinal() ].fieldName = "BDAN_CLASSIFICATIONID";
		bdanDbFields[ tableFldConstants.classificationid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.categoryid.ordinal() ].fieldName = "BDAN_CATEGORYID";
		bdanDbFields[ tableFldConstants.categoryid.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.issparesreplaced.ordinal() ].fieldName = "BDAN_ISSPARESREPLACED";
		bdanDbFields[ tableFldConstants.issparesreplaced.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.alarmno.ordinal() ].fieldName = "BDAN_ALARMNO";
		bdanDbFields[ tableFldConstants.alarmno.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "BDAN_MANPOWERCOST";
		bdanDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "BDAN_CONTRACTORCOST";
		bdanDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.sparescost.ordinal() ].fieldName = "BDAN_SPARESCOST";
		bdanDbFields[ tableFldConstants.sparescost.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "BDAN_OTHERCOST";
		bdanDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		bdanDbFields[ tableFldConstants.status.ordinal() ].fieldName = "BDAN_STATUS";
		bdanDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BDAN_REMARKS";
		bdanDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.actiontakenby.ordinal() ].fieldName = "BDAN_ACTIONTAKENBY";
		bdanDbFields[ tableFldConstants.actiontakenby.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "BDAN_COMPLETEDBY";
		bdanDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.costcentre.ordinal() ].fieldName = "BDAN_COSTCENTRE";
		bdanDbFields[ tableFldConstants.costcentre.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.erppoststatus.ordinal() ].fieldName = "BDAN_ERPPOSTSTATUS";
		bdanDbFields[ tableFldConstants.erppoststatus.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.problemseverity.ordinal() ].fieldName = "BDAN_PROBLEMSEVERITY";
		bdanDbFields[ tableFldConstants.problemseverity.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.failuretype.ordinal() ].fieldName = "BDAN_FAILURETYPE";
		bdanDbFields[ tableFldConstants.failuretype.ordinal() ].fieldType = 'V';
		
		bdanDbFields[ tableFldConstants.isapproved.ordinal() ].fieldName = "BDAN_ISAPPROVED";
		bdanDbFields[ tableFldConstants.isapproved.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.approverdby.ordinal() ].fieldName = "BDAN_APPROVERDBY";
		bdanDbFields[ tableFldConstants.approverdby.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.erpnumber.ordinal() ].fieldName = "BDAN_ERPNUMBER";
		bdanDbFields[ tableFldConstants.erpnumber.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.otherfailuretype.ordinal() ].fieldName = "BDAN_OTHERFAILURETYPE";
		bdanDbFields[ tableFldConstants.otherfailuretype.ordinal() ].fieldType = 'V';
		
		bdanDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BDAN_ACTIVE";
		bdanDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bdanDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BDAN_CREATEDBY";
		bdanDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bdanDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BDAN_CREATEDON";
		bdanDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bdanDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BDAN_MODIFIEDON";
		bdanDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_BDM_TL_DTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_BDM_TL_DTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		BAL_PcsTlPcsBd newpcsbd= new BAL_PcsTlPcsBd();
		String sql=null;
		
		 sql = "DELETE from " + TBL_BAL_BDM_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "' AND BDAN_BDMS_KEYID NOT IN(SELECT PBD_BDM_KEYID FROM PCS_TL_PCSBD)";
		
		
			  return sql;
	}
	
	public static String getDeleteSqlPbd(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		
		BAL_PcsTlPcsBd newpcsbd= new BAL_PcsTlPcsBd();
		String sql=null;
		
		 sql = "DELETE from " + TBL_BAL_BDM_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] +"'";
		
			  return sql;
	}
	
	public static String select()
	{
		return "SELECT * from "+TBL_BAL_BDM_TL_DTL+" where bdan_bdms_keyid = ?";
	}

	public static String selectWhyWhyMst()
	{
		return "SELECT * from "+TableNames.TBL_BDM_TL_WHYWHYMST+" where WWMS_REFDOCNO = ?";
	}

	
}

