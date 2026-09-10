package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.model.EmployeeDetail;
//import com.akranta.tpm.model.BAL_PlmTlCalendar;

public class BAL_PlmTlUnscheduledactmstSql {

	public static final String TBL_PLM_TL_UNSCHEDULEDACTMST = "PLM_TL_UNSCHEDULEDACTMST";  

	TableFieldType [] punsDbFields = null;

	
	public enum   tableFldConstants
	{
		keyid, trandate, factoryid, sectionid, cellid, machineid, assemblyid
		, subassemblyid, tradeid, source, jobtype, supplierid, machinecondition
		, howmuchduration, wherelocation, whatactivity, howmethod, whatstandard
		, desirablereading, minimumreading, maximumreading, uomid, correctiveaction
		, sparerequired, toolrequired, responsibility, fromdate, todate
		, targetweekno, wogenflag, ismadeimpact, pmstandardid, workorderid
		, status, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		,elementid,flid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPunsDbFields() {
		return punsDbFields;
	}

	public BAL_PlmTlUnscheduledactmstSql()
	{
		punsDbFields = new TableFieldType[ 45 ];
		for(int i = 0;i < 45; i++)
		{	
			punsDbFields[ i ] = new TableFieldType();
		}
		punsDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PUNS_KEYID";
		punsDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.trandate.ordinal() ].fieldName = "PUNS_TRANDATE";
		punsDbFields[ tableFldConstants.trandate.ordinal() ].fieldType = 'D';

		punsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PUNS_FACTORYID";
		punsDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PUNS_SECTIONID";
		punsDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PUNS_CELLID";
		punsDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PUNS_MACHINEID";
		punsDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "PUNS_ASSEMBLYID";
		punsDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldName = "PUNS_SUBASSEMBLYID";
		punsDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "PUNS_TRADEID";
		punsDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.source.ordinal() ].fieldName = "PUNS_SOURCE";
		punsDbFields[ tableFldConstants.source.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.jobtype.ordinal() ].fieldName = "PUNS_JOBTYPE";
		punsDbFields[ tableFldConstants.jobtype.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.supplierid.ordinal() ].fieldName = "PUNS_SUPPLIERID";
		punsDbFields[ tableFldConstants.supplierid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldName = "PUNS_MACHINECONDITION";
		punsDbFields[ tableFldConstants.machinecondition.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.howmuchduration.ordinal() ].fieldName = "PUNS_HOWMUCHDURATION";
		punsDbFields[ tableFldConstants.howmuchduration.ordinal() ].fieldType = 'N';

		punsDbFields[ tableFldConstants.wherelocation.ordinal() ].fieldName = "PUNS_WHERELOCATION";
		punsDbFields[ tableFldConstants.wherelocation.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.whatactivity.ordinal() ].fieldName = "PUNS_WHATACTIVITY";
		punsDbFields[ tableFldConstants.whatactivity.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.howmethod.ordinal() ].fieldName = "PUNS_HOWMETHOD";
		punsDbFields[ tableFldConstants.howmethod.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.whatstandard.ordinal() ].fieldName = "PUNS_WHATSTANDARD";
		punsDbFields[ tableFldConstants.whatstandard.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.desirablereading.ordinal() ].fieldName = "PUNS_DESIRABLEREADING";
		punsDbFields[ tableFldConstants.desirablereading.ordinal() ].fieldType = 'N';

		punsDbFields[ tableFldConstants.minimumreading.ordinal() ].fieldName = "PUNS_MINIMUMREADING";
		punsDbFields[ tableFldConstants.minimumreading.ordinal() ].fieldType = 'N';

		punsDbFields[ tableFldConstants.maximumreading.ordinal() ].fieldName = "PUNS_MAXIMUMREADING";
		punsDbFields[ tableFldConstants.maximumreading.ordinal() ].fieldType = 'N';

		punsDbFields[ tableFldConstants.uomid.ordinal() ].fieldName = "PUNS_UOMID";
		punsDbFields[ tableFldConstants.uomid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldName = "PUNS_CORRECTIVEACTION";
		punsDbFields[ tableFldConstants.correctiveaction.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.sparerequired.ordinal() ].fieldName = "PUNS_SPAREREQUIRED";
		punsDbFields[ tableFldConstants.sparerequired.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.toolrequired.ordinal() ].fieldName = "PUNS_TOOLREQUIRED";
		punsDbFields[ tableFldConstants.toolrequired.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "PUNS_RESPONSIBILITY";
		punsDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "PUNS_FROMDATE";
		punsDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		punsDbFields[ tableFldConstants.todate.ordinal() ].fieldName = "PUNS_TODATE";
		punsDbFields[ tableFldConstants.todate.ordinal() ].fieldType = 'D';

		punsDbFields[ tableFldConstants.targetweekno.ordinal() ].fieldName = "PUNS_TARGETWEEKNO";
		punsDbFields[ tableFldConstants.targetweekno.ordinal() ].fieldType = 'N';

		punsDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldName = "PUNS_WOGENFLAG";
		punsDbFields[ tableFldConstants.wogenflag.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.ismadeimpact.ordinal() ].fieldName = "PUNS_ISMADEIMPACT";
		punsDbFields[ tableFldConstants.ismadeimpact.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldName = "PUNS_PMSTANDARDID";
		punsDbFields[ tableFldConstants.pmstandardid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.workorderid.ordinal() ].fieldName = "PUNS_WORKORDERID";
		punsDbFields[ tableFldConstants.workorderid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.status.ordinal() ].fieldName = "PUNS_STATUS";
		punsDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PUNS_TEMPFIELD1";
		punsDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PUNS_TEMPFIELD2";
		punsDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PUNS_TEMPFIELD3";
		punsDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "PUNS_TEMPFIELD4";
		punsDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "PUNS_TEMPFIELD5";
		punsDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PUNS_ELEMENTID";
		punsDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PUNS_FLID";
		punsDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		punsDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PUNS_ACTIVE";
		punsDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		punsDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PUNS_CREATEDBY";
		punsDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		punsDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PUNS_CREATEDON";
		punsDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		punsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PUNS_MODIFIEDON";
		punsDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_UNSCHEDULEDACTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_UNSCHEDULEDACTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_UNSCHEDULEDACTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String getSparesPickupTbl() 
	{
		String sql = "Select PSPD_SPAREID, PSPD_KEYID, SPRM_PARTNO, SPRM_PARTNAME ,SPRM_MAKE,SPRM_MODEL," +
				" PSPD_QUANTITY from " + TableNames.TBL_GEN_TL_SPARESMST +"," + TableNames.TBL_PLM_TL_SPAREDTL +
				 " where SPRM_KEYID = PSPD_SPAREID and PSPD_STANDARDID = ? ";
		
		return sql;
	}
	

	public static String getSubtype() 
	{
		return "  Select  Distinct PASM_KEYID,DECODE(PASM_KEYID,'',1,0) AS TICK,PASM_CODE,PASM_NAME From "+ TableNames.TBL_PLM_TL_ACTIVITYSUBTYPEMST +" Where  PASM_ACTIVE in ( 'Y' ) ORDER BY TICK  DESC ";
	}
	
	public static String getSelectSql()
	{
		String sql ="Select * from "+TBL_PLM_TL_UNSCHEDULEDACTMST +" where PUNS_KEYID = ?";
		
		return sql;
	}
	
	public static String getSelectPmStandSql()
	{
		String sql ="Select PMSD_PHENOMENAID,PMSD_CAUSEID,PMSD_SAFETYINSTRUCTION,PMSD_FREQUENCYUNIT,PMSD_FREQUENCY,PMSD_CORRECTIVEACTION,PMSD_WHYSTANDARD  from "
					+ TableNames.TBL_PLM_TL_STANDARDS + " where PMSD_KEYID = ?";
		
		return sql;
	}
	
	public static String selectToolsSql()
	{
		return "SELECT * from " +TableNames.TBL_GEN_TL_TOOLSMST  + " where TOLM_KEYID= ?";
	}
	
	public static String getSftyPermitTbl()
	{
		String sql ="Select 'False' as \"select\", typeofwork, sftpermittype, pspl_pmstandardid stan,decode(nvl(pspl_pmstandardid,0),pspl_pmstandardid,'1','') as selected from plm_tl_pmsftpermitlink "
			+","+"("
			+ " select 'Hot Work' as typeofwork , 'H' sftpermittype from dual"
			+ " union"	
			+ " select 'Conifined Space Entry ' as typeofwork , 'C' sftpermittype from dual"
			+ " union"
			+ " select 'Working At Height' as typeofwork , 'W' sftpermittype from dual"
			+ " union"
			+ " select 'Line Breaking for Hazardous Material' as typeofwork , 'L' sftpermittype from dual"
			+ " union"
			+ " select 'Lockout/Tagout' as typeofwork , 'T' sftpermittype from dual"
			+ " union"
			+ " select 'Crane Work permit' as typeofwork ,'CR' sftpermittype from dual"
			+ " union"
			+ " select 'Excavation Work Permit' as typeofwork,'E' sftpermittype from dual"
			+ ")"
			+ " where sftpermittype = pspl_sftpermittype(+) and   pspl_pmstandardid(+) = ?";

		return sql;

	}
	
	public static String getGridDataSql()
	{
		StringBuffer sql =new StringBuffer();
		sql.append("Select PUNS_KEYID,FACT_NAME ,SECT_NAME ,CELL_NAME,MCHM_MACHINENAME,ASSM_NAME,");
		sql.append("SBAM_NAME,TRDM_NAME,DECODE(PUNS_SOURCE,'I','Internal','E','External',PUNS_SOURCE),SUPM_NAME1,"); 
		sql.append("DECODE(PUNS_MACHINECONDITION,'B','Both','S','Shut Down','R','Running',PUNS_MACHINECONDITION),");
		sql.append("PUNS_HOWMUCHDURATION,PUNS_WHERELOCATION,PUNS_WHATACTIVITY,PUNS_HOWMETHOD,PUNS_WHATSTANDARD,");
		sql.append("PUNS_CORRECTIVEACTION,PUNS_SPAREREQUIRED,PUNS_TOOLREQUIRED " );
		sql.append("FROM");
		sql.append(" PLM_TL_UNSCHEDULEDACTMST,GEN_TL_FACTORYMST,GEN_TL_SECTIONMST,GEN_TL_CELLMST,");
		sql.append("GEN_TL_MACHINEMST,GEN_TL_ASSEMBLYMST,GEN_TL_SUBASSEMBLYMST,GEN_TL_TRADEMST,GEN_TL_SUPPLIERMST ");		
		sql.append("WHERE FACT_KEYID=PUNS_FACTORYID AND SECT_KEYID=PUNS_SECTIONID AND CELL_KEYID=PUNS_CELLID ");
		sql.append("AND MCHM_KEYID=PUNS_MACHINEID AND PUNS_ASSEMBLYID= ASSM_KEYID ");
		sql.append("AND SBAM_KEYID(+) = PUNS_SUBASSEMBLYID AND TRDM_KEYID(+) = PUNS_TRADEID  AND SUPM_KEYID(+)=PUNS_SUPPLIERID  " );
			       
		return sql.toString();
	}


	

}

