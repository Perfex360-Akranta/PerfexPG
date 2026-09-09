package com.akranta.tpm.dao.sql;

public class MldTlMouldunloadmstSql {

	public static final String TBL_MLD_TL_MOULDUNLOADMST = "MLD_TL_MOULDUNLOADMST";  

	TableFieldType [] munlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, shiftdate, bookeddate, receiveddate, allocateddate, wostartdate
		, woenddate, responsetime, workhours, downtime, refdoctype, refdocid
		, mouldid, factoryid, sectionid, lineid, machineid, stationid
		, phenid, rootcauseid, shift, trade, partlocation, activitytype
		, mchcondition, manpowercost, contractorcost, othercost, sparecost
		, reason, rootcause, countermeasure, action, reportedby, targetdate
		, status, isyy, yyno, completedby, completeddate, remarks, pctrmeasure
		, relatedto, tempfield1, tempfield2, tempfield3, tempfield4, tempfield5
		, tempfield6, tempfield7, tempfield8, tempfield9, tempfield10
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getMunlDbFields() {
		return munlDbFields;
	}

	public MldTlMouldunloadmstSql()
	{
		munlDbFields = new TableFieldType[ 57 ];
		for(int i = 0;i < 57; i++)
		{	
			munlDbFields[ i ] = new TableFieldType();
		}
		munlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MUNL_KEYID";
		munlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldName = "MUNL_SHIFTDATE";
		munlDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.bookeddate.ordinal() ].fieldName = "MUNL_BOOKEDDATE";
		munlDbFields[ tableFldConstants.bookeddate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldName = "MUNL_RECEIVEDDATE";
		munlDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.allocateddate.ordinal() ].fieldName = "MUNL_ALLOCATEDDATE";
		munlDbFields[ tableFldConstants.allocateddate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.wostartdate.ordinal() ].fieldName = "MUNL_WOSTARTDATE";
		munlDbFields[ tableFldConstants.wostartdate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.woenddate.ordinal() ].fieldName = "MUNL_WOENDDATE";
		munlDbFields[ tableFldConstants.woenddate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.responsetime.ordinal() ].fieldName = "MUNL_RESPONSETIME";
		munlDbFields[ tableFldConstants.responsetime.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.workhours.ordinal() ].fieldName = "MUNL_WORKHOURS";
		munlDbFields[ tableFldConstants.workhours.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "MUNL_DOWNTIME";
		munlDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "MUNL_REFDOCTYPE";
		munlDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "MUNL_REFDOCID";
		munlDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.mouldid.ordinal() ].fieldName = "MUNL_MOULDID";
		munlDbFields[ tableFldConstants.mouldid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MUNL_FACTORYID";
		munlDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MUNL_SECTIONID";
		munlDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.lineid.ordinal() ].fieldName = "MUNL_LINEID";
		munlDbFields[ tableFldConstants.lineid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MUNL_MACHINEID";
		munlDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.stationid.ordinal() ].fieldName = "MUNL_STATIONID";
		munlDbFields[ tableFldConstants.stationid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.phenid.ordinal() ].fieldName = "MUNL_PHENID";
		munlDbFields[ tableFldConstants.phenid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "MUNL_ROOTCAUSEID";
		munlDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.shift.ordinal() ].fieldName = "MUNL_SHIFT";
		munlDbFields[ tableFldConstants.shift.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.trade.ordinal() ].fieldName = "MUNL_TRADE";
		munlDbFields[ tableFldConstants.trade.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.partlocation.ordinal() ].fieldName = "MUNL_PARTLOCATION";
		munlDbFields[ tableFldConstants.partlocation.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.activitytype.ordinal() ].fieldName = "MUNL_ACTIVITYTYPE";
		munlDbFields[ tableFldConstants.activitytype.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldName = "MUNL_MCHCONDITION";
		munlDbFields[ tableFldConstants.mchcondition.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldName = "MUNL_MANPOWERCOST";
		munlDbFields[ tableFldConstants.manpowercost.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldName = "MUNL_CONTRACTORCOST";
		munlDbFields[ tableFldConstants.contractorcost.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.othercost.ordinal() ].fieldName = "MUNL_OTHERCOST";
		munlDbFields[ tableFldConstants.othercost.ordinal() ].fieldType = 'N';

		munlDbFields[ tableFldConstants.sparecost.ordinal() ].fieldName = "MUNL_SPARECOST";
		munlDbFields[ tableFldConstants.sparecost.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.reason.ordinal() ].fieldName = "MUNL_REASON";
		munlDbFields[ tableFldConstants.reason.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "MUNL_ROOTCAUSE";
		munlDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "MUNL_COUNTERMEASURE";
		munlDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.action.ordinal() ].fieldName = "MUNL_ACTION";
		munlDbFields[ tableFldConstants.action.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.reportedby.ordinal() ].fieldName = "MUNL_REPORTEDBY";
		munlDbFields[ tableFldConstants.reportedby.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "MUNL_TARGETDATE";
		munlDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.status.ordinal() ].fieldName = "MUNL_STATUS";
		munlDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.isyy.ordinal() ].fieldName = "MUNL_ISYY";
		munlDbFields[ tableFldConstants.isyy.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.yyno.ordinal() ].fieldName = "MUNL_YYNO";
		munlDbFields[ tableFldConstants.yyno.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "MUNL_COMPLETEDBY";
		munlDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "MUNL_COMPLETEDDATE";
		munlDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "MUNL_REMARKS";
		munlDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.pctrmeasure.ordinal() ].fieldName = "MUNL_PCTRMEASURE";
		munlDbFields[ tableFldConstants.pctrmeasure.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "MUNL_RELATEDTO";
		munlDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MUNL_TEMPFIELD1";
		munlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MUNL_TEMPFIELD2";
		munlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MUNL_TEMPFIELD3";
		munlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MUNL_TEMPFIELD4";
		munlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MUNL_TEMPFIELD5";
		munlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MUNL_TEMPFIELD6";
		munlDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MUNL_TEMPFIELD7";
		munlDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MUNL_TEMPFIELD8";
		munlDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "MUNL_TEMPFIELD9";
		munlDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "MUNL_TEMPFIELD10";
		munlDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MUNL_ACTIVE";
		munlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		munlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MUNL_CREATEDBY";
		munlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		munlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MUNL_CREATEDON";
		munlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		munlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MUNL_MODIFIEDON";
		munlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MLD_TL_MOULDUNLOADMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MLD_TL_MOULDUNLOADMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MLD_TL_MOULDUNLOADMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getMouldUnloadSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_MLD_TL_MOULDUNLOADMST + " where MUNL_KEYID = ?  ";
	}
	

}

