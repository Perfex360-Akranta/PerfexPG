package com.akranta.tpm.dao.sql;

public class GenTlNearmissreportmstSql { 

	public static final String TBL_GEN_TL_NEARMISSREPORTMST = "GEN_TL_NEARMISSREPORTMST";  

	TableFieldType [] nmrtDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flnid, employeeid, deptid, severitypotentialid, descnearmiss
		, actionrecommended, probablerecrate, responsibility, identifiedby
		, targetdate, occurrencedatetime, remarks, status,completeddate,completedby,others ,othersuc, prepareddatetime
		, investigation, approvedby, tempfield1, tempfield2,tempfield3, active, createdby, createdon, modifiedon
	} 

	public TableFieldType[] getNmrtDbFields() {
		return nmrtDbFields;
	}

	public GenTlNearmissreportmstSql()
	{
		nmrtDbFields = new TableFieldType[ 28 ];
		for(int i = 0;i < 28; i++)
		{	
			nmrtDbFields[ i ] = new TableFieldType();
		}
		nmrtDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NMRT_KEYID";
		nmrtDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.flnid.ordinal() ].fieldName = "NMRT_FLNID";
		nmrtDbFields[ tableFldConstants.flnid.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.employeeid.ordinal() ].fieldName = "NMRT_EMPLOYEEID";
		nmrtDbFields[ tableFldConstants.employeeid.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.deptid.ordinal() ].fieldName = "NMRT_DEPTID";
		nmrtDbFields[ tableFldConstants.deptid.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.severitypotentialid.ordinal() ].fieldName = "NMRT_SEVERITYPOTENTIALID";
		nmrtDbFields[ tableFldConstants.severitypotentialid.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.descnearmiss.ordinal() ].fieldName = "NMRT_DESCNEARMISS";
		nmrtDbFields[ tableFldConstants.descnearmiss.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.actionrecommended.ordinal() ].fieldName = "NMRT_ACTIONRECOMMENDED";
		nmrtDbFields[ tableFldConstants.actionrecommended.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.probablerecrate.ordinal() ].fieldName = "NMRT_PROBABLERECRATE";
		nmrtDbFields[ tableFldConstants.probablerecrate.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "NMRT_RESPONSIBILITY";
		nmrtDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldName = "NMRT_IDENTIFIEDBY";
		nmrtDbFields[ tableFldConstants.identifiedby.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "NMRT_TARGETDATE";
		nmrtDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		nmrtDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldName = "NMRT_OCCURRENCEDATETIME";
		nmrtDbFields[ tableFldConstants.occurrencedatetime.ordinal() ].fieldType = 'D';

		nmrtDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "NMRT_REMARKS";
		nmrtDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'C';		
		
		nmrtDbFields[ tableFldConstants.status.ordinal() ].fieldName = "NMRT_STATUS";
		nmrtDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';
		
		nmrtDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "NMRT_COMPLETEDDATE";
		nmrtDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';
		
		nmrtDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "NMRT_COMPLETEDBY";
		nmrtDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';
		
		nmrtDbFields[ tableFldConstants.others.ordinal() ].fieldName = "NMRT_OTHERS";
		nmrtDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.othersuc.ordinal() ].fieldName = "NMRT_OTHERSUC";
		nmrtDbFields[ tableFldConstants.othersuc.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldName = "NMRT_PREPAREDDATETIME";
		nmrtDbFields[ tableFldConstants.prepareddatetime.ordinal() ].fieldType = 'D';

		nmrtDbFields[ tableFldConstants.investigation.ordinal() ].fieldName = "NMRT_INVESTIGATION";
		nmrtDbFields[ tableFldConstants.investigation.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "NMRT_APPROVEDBY";
		nmrtDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';
		
		nmrtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "NMRT_TEMPFIELD1";
		nmrtDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		nmrtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "NMRT_TEMPFIELD2";
		nmrtDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		nmrtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "NMRT_TEMPFIELD3";
		nmrtDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
		
		nmrtDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NMRT_ACTIVE";
		nmrtDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		nmrtDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NMRT_CREATEDBY";
		nmrtDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		nmrtDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NMRT_CREATEDON";
		nmrtDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		nmrtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NMRT_MODIFIEDON";
		nmrtDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_NEARMISSREPORTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_NEARMISSREPORTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_NEARMISSREPORTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSingleData(){
		String sql="SELECT * from " + TBL_GEN_TL_NEARMISSREPORTMST +" where NMRT_KEYID = ?";
		return sql;
		
	}

	public static String getDeleteMst(String keyId) {
		String sql="DELETE from " + TBL_GEN_TL_NEARMISSREPORTMST +" where NMRT_KEYID ='";
		sql+=keyId;
		sql+="'";
		
		return sql;
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		String sql=" select aplm_status from GEN_TL_ACTIONPLANMST where APLM_MASTERREFID = '"+keyid+"' ";
		return sql;
	}

}

