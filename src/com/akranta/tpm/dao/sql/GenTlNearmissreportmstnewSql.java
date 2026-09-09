package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class GenTlNearmissreportmstnewSql {

	public static final String TBL_GEN_TL_NEARMISSREPORTMSTNEW = "GEN_TL_NEARMISSREPORTMSTNEW";  

	TableFieldType [] nmrnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, severitypotentialid, occurrencedatetime, flnid, employeeid
		, descnearmiss, identifiedby, deptid, prepareddatetime, investigation
		, probablerecrate, chkothersusa, chkothersusc, othersusa, othersusc
		, remarks, actionplanno, whywhyno, investigationby, investigationdate
		, actionrecommended,invesremarks, responsibility, status, targetdate,responseremarks
		,revisedtarget, actiontaken, completedby, datecompleted
		,actionclsremarks, verifiedstatus, verifiedby
		, dateverified,verifiedremarks, closed, closedby, closeddate,closedbyremarks, tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, approvedby, active, createdby
		, createdon, modifiedby, modifiedon
	}

	public TableFieldType[] getNmrnDbFields() {
		return nmrnDbFields;
	}

	public GenTlNearmissreportmstnewSql()
	{
		nmrnDbFields = new TableFieldType[ 50 ];
		for(int i = 0;i < 50; i++)
		{	
			nmrnDbFields[ i ] = new TableFieldType();
		}
		nmrnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NMRN_KEYID";
		nmrnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.severitypotentialid.ordinal() ].fieldName = "NMRN_SEVERITYPOTENTIALID";
		nmrnDbFields[ tableFldConstants.severitypotentialid.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldName = "NMRN_OCCURRENCEDATETIME";
		nmrnDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldType = 'D';

		nmrnDbFields[ tableFldConstants.flnid.ordinal() ].fieldName = "NMRN_FLNID";
		nmrnDbFields[ tableFldConstants.flnid.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "NMRN_EMPLOYEEID";
		nmrnDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.descnearmiss.ordinal() ].fieldName = "NMRN_DESCNEARMISS";
		nmrnDbFields[ tableFldConstants.descnearmiss.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldName = "NMRN_IDENTIFIEDBY";
		nmrnDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.deptid.ordinal() ].fieldName = "NMRN_DEPTID";
		nmrnDbFields[ tableFldConstants.deptid.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldName = "NMRN_PREPAREDDATETIME";
		nmrnDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldType = 'D';

		nmrnDbFields[ tableFldConstants.investigation.ordinal() ].fieldName = "NMRN_INVESTIGATION";
		nmrnDbFields[ tableFldConstants.investigation.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.probablerecrate.ordinal() ].fieldName = "NMRN_PROBABLERECRATE";
		nmrnDbFields[ tableFldConstants.probablerecrate.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.chkothersusa.ordinal() ].fieldName = "NMRN_CHKOTHERSUSA";
		nmrnDbFields[ tableFldConstants.chkothersusa.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.chkothersusc.ordinal() ].fieldName = "NMRN_CHKOTHERSUSC";
		nmrnDbFields[ tableFldConstants.chkothersusc.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.othersusa.ordinal() ].fieldName = "NMRN_OTHERSUSA";
		nmrnDbFields[ tableFldConstants.othersusa.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.othersusc.ordinal() ].fieldName = "NMRN_OTHERSUSC";
		nmrnDbFields[ tableFldConstants.othersusc.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "NMRN_REMARKS";
		nmrnDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.actionplanno.ordinal() ].fieldName = "NMRN_ACTIONPLANNO";
		nmrnDbFields[ tableFldConstants.actionplanno.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.whywhyno.ordinal() ].fieldName = "NMRN_WHYWHYNO";
		nmrnDbFields[ tableFldConstants.whywhyno.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.investigationby.ordinal() ].fieldName = "NMRN_INVESTIGATIONBY";
		nmrnDbFields[ tableFldConstants.investigationby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.investigationdate.ordinal() ].fieldName = "NMRN_INVESTIGATIONDATE";
		nmrnDbFields[ tableFldConstants.investigationdate.ordinal() ].fieldType = 'D';

		nmrnDbFields[ tableFldConstants.actionrecommended.ordinal() ].fieldName = "NMRN_ACTIONRECOMMENDED";
		nmrnDbFields[ tableFldConstants.actionrecommended.ordinal() ].fieldType = 'V';
		
		nmrnDbFields[ tableFldConstants.invesremarks.ordinal() ].fieldName = "NMRN_INVRECREMARKS";
		nmrnDbFields[ tableFldConstants.invesremarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "NMRN_RESPONSIBILITY";
		nmrnDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.status.ordinal() ].fieldName = "NMRN_STATUS";
		nmrnDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "NMRN_TARGETDATE";
		nmrnDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';
		
		nmrnDbFields[ tableFldConstants.responseremarks.ordinal() ].fieldName = "NMRN_RESPONREMARKS";
		nmrnDbFields[ tableFldConstants.responseremarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.revisedtarget.ordinal() ].fieldName = "NMRN_REVISEDTARGET";
		nmrnDbFields[ tableFldConstants.revisedtarget.ordinal() ].fieldType = 'D';

		nmrnDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "NMRN_ACTIONTAKEN";
		nmrnDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "NMRN_COMPLETEDBY";
		nmrnDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.datecompleted.ordinal() ].fieldName = "NMRN_DATECOMPLETED";
		nmrnDbFields[ tableFldConstants.datecompleted.ordinal() ].fieldType = 'D';
		
		nmrnDbFields[ tableFldConstants.actionclsremarks.ordinal() ].fieldName = "NMRN_ACTNCLSREMARKS";
		nmrnDbFields[ tableFldConstants.actionclsremarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldName = "NMRN_VERIFIEDSTATUS";
		nmrnDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "NMRN_VERIFIEDBY";
		nmrnDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.dateverified.ordinal() ].fieldName = "NMRN_DATEVERIFIED";
		nmrnDbFields[ tableFldConstants.dateverified.ordinal() ].fieldType = 'D';
		
		nmrnDbFields[ tableFldConstants.verifiedremarks.ordinal() ].fieldName = "NMRN_VERIFYREMARKS";
		nmrnDbFields[ tableFldConstants.verifiedremarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.closed.ordinal() ].fieldName = "NMRN_CLOSED";
		nmrnDbFields[ tableFldConstants.closed.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.closedby.ordinal() ].fieldName = "NMRN_CLOSEDBY";
		nmrnDbFields[ tableFldConstants.closedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.closeddate.ordinal() ].fieldName = "NMRN_CLOSEDDATE";
		nmrnDbFields[ tableFldConstants.closeddate.ordinal() ].fieldType = 'D';
		
		nmrnDbFields[ tableFldConstants.closedbyremarks.ordinal() ].fieldName = "NMRN_CLOSEDBYREMARKS";
		nmrnDbFields[ tableFldConstants.closedbyremarks.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "NMRN_TEMPFIELD1";
		nmrnDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "NMRN_TEMPFIELD2";
		nmrnDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "NMRN_TEMPFIELD3";
		nmrnDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "NMRN_TEMPFIELD4";
		nmrnDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "NMRN_TEMPFIELD5";
		nmrnDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "NMRN_APPROVEDBY";
		nmrnDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NMRN_ACTIVE";
		nmrnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		nmrnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NMRN_CREATEDBY";
		nmrnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NMRN_CREATEDON";
		nmrnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		nmrnDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "NMRN_MODIFIEDBY";
		nmrnDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		nmrnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NMRN_MODIFIEDON";
		nmrnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_NEARMISSREPORTMSTNEW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_NEARMISSREPORTMSTNEW, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_NEARMISSREPORTMSTNEW ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getSingleData(){
		String sql="SELECT * from " + TBL_GEN_TL_NEARMISSREPORTMSTNEW +" where NMRN_KEYID = ?";
		return sql;
		
	}

	public String selectgetReleatedFileManager(String momKeyId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select dmdm_filename,dmdm_path from DCM_TL_DOCUMENTMANAGER where dmdm_refdocno='"+momKeyId+"' ");
		
		CommonMessage.debugMsg(" Checking FileManager Data "+sql.toString());
		return sql.toString();
	}
}

