package com.akranta.tpm.dao.sql;

public class PlmTlUnplannedmaintmstSql {

	public static final String TBL_PLM_TL_UNPLANNEDMAINTMST = "PLM_TL_UNPLANNEDMAINTMST";  

	TableFieldType [] upmmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, entrydate, shiftid, factoryid, sectionid, cellid, machineid
		, assemblyid, partlocationid, alarmdescription, bdtype, reporteddate
		, receiveddate, wostarttime, woendtime, breaktime, actualworktime
		, downtime, prodaccepdate, bookedphenomena, phenomenadescription
		, bookedcause, finalphenomena, finalcause, bookedtrade, finaltrade
		, problemdescription, isbdlocked, shiftincharge, status, bookedby
		, remarks, bookingtype, bdrelatedto, wno, spareid, priority, woallottedflag
		, wostartflag, woendflag, woprodaccepflag, subassemblyid, repeatedbdflag
		, repeatedbdno, relatedto, mould, active, createdby, createdon
		, modifiedon
	}

	public TableFieldType[] getUpmmDbFields() {
		return upmmDbFields;
	}

	public PlmTlUnplannedmaintmstSql()
	{
		upmmDbFields = new TableFieldType[ 50 ];
		for(int i = 0;i < 50; i++)
		{	
			upmmDbFields[ i ] = new TableFieldType();
		}
		upmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "UPMM_KEYID";
		upmmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.entrydate.ordinal() ].fieldName = "UPMM_ENTRYDATE";
		upmmDbFields[ tableFldConstants.entrydate.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "UPMM_SHIFTID";
		upmmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "UPMM_FACTORYID";
		upmmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "UPMM_SECTIONID";
		upmmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "UPMM_CELLID";
		upmmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "UPMM_MACHINEID";
		upmmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "UPMM_ASSEMBLYID";
		upmmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.partlocationid.ordinal() ].fieldName = "UPMM_PARTLOCATIONID";
		upmmDbFields[ tableFldConstants.partlocationid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.alarmdescription.ordinal() ].fieldName = "UPMM_ALARMDESCRIPTION";
		upmmDbFields[ tableFldConstants.alarmdescription.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.bdtype.ordinal() ].fieldName = "UPMM_BDTYPE";
		upmmDbFields[ tableFldConstants.bdtype.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldName = "UPMM_REPORTEDDATE";
		upmmDbFields[ tableFldConstants.reporteddate.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldName = "UPMM_RECEIVEDDATE";
		upmmDbFields[ tableFldConstants.receiveddate.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldName = "UPMM_WOSTARTTIME";
		upmmDbFields[ tableFldConstants.wostarttime.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.woendtime.ordinal() ].fieldName = "UPMM_WOENDTIME";
		upmmDbFields[ tableFldConstants.woendtime.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.breaktime.ordinal() ].fieldName = "UPMM_BREAKTIME";
		upmmDbFields[ tableFldConstants.breaktime.ordinal() ].fieldType = 'N';

		upmmDbFields[ tableFldConstants.actualworktime.ordinal() ].fieldName = "UPMM_ACTUALWORKTIME";
		upmmDbFields[ tableFldConstants.actualworktime.ordinal() ].fieldType = 'N';

		upmmDbFields[ tableFldConstants.downtime.ordinal() ].fieldName = "UPMM_DOWNTIME";
		upmmDbFields[ tableFldConstants.downtime.ordinal() ].fieldType = 'N';

		upmmDbFields[ tableFldConstants.prodaccepdate.ordinal() ].fieldName = "UPMM_PRODACCEPDATE";
		upmmDbFields[ tableFldConstants.prodaccepdate.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.bookedphenomena.ordinal() ].fieldName = "UPMM_BOOKEDPHENOMENA";
		upmmDbFields[ tableFldConstants.bookedphenomena.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.phenomenadescription.ordinal() ].fieldName = "UPMM_PHENOMENADESCRIPTION";
		upmmDbFields[ tableFldConstants.phenomenadescription.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.bookedcause.ordinal() ].fieldName = "UPMM_BOOKEDCAUSE";
		upmmDbFields[ tableFldConstants.bookedcause.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldName = "UPMM_FINALPHENOMENA";
		upmmDbFields[ tableFldConstants.finalphenomena.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.finalcause.ordinal() ].fieldName = "UPMM_FINALCAUSE";
		upmmDbFields[ tableFldConstants.finalcause.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.bookedtrade.ordinal() ].fieldName = "UPMM_BOOKEDTRADE";
		upmmDbFields[ tableFldConstants.bookedtrade.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.finaltrade.ordinal() ].fieldName = "UPMM_FINALTRADE";
		upmmDbFields[ tableFldConstants.finaltrade.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.problemdescription.ordinal() ].fieldName = "UPMM_PROBLEMDESCRIPTION";
		upmmDbFields[ tableFldConstants.problemdescription.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.isbdlocked.ordinal() ].fieldName = "UPMM_ISBDLOCKED";
		upmmDbFields[ tableFldConstants.isbdlocked.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldName = "UPMM_SHIFTINCHARGE";
		upmmDbFields[ tableFldConstants.shiftincharge.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "UPMM_STATUS";
		upmmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.bookedby.ordinal() ].fieldName = "UPMM_BOOKEDBY";
		upmmDbFields[ tableFldConstants.bookedby.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "UPMM_REMARKS";
		upmmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.bookingtype.ordinal() ].fieldName = "UPMM_BOOKINGTYPE";
		upmmDbFields[ tableFldConstants.bookingtype.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.bdrelatedto.ordinal() ].fieldName = "UPMM_BDRELATEDTO";
		upmmDbFields[ tableFldConstants.bdrelatedto.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.wno.ordinal() ].fieldName = "UPMM_WNO";
		upmmDbFields[ tableFldConstants.wno.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.spareid.ordinal() ].fieldName = "UPMM_SPAREID";
		upmmDbFields[ tableFldConstants.spareid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "UPMM_PRIORITY";
		upmmDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.woallottedflag.ordinal() ].fieldName = "UPMM_WOALLOTTEDFLAG";
		upmmDbFields[ tableFldConstants.woallottedflag.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.wostartflag.ordinal() ].fieldName = "UPMM_WOSTARTFLAG";
		upmmDbFields[ tableFldConstants.wostartflag.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.woendflag.ordinal() ].fieldName = "UPMM_WOENDFLAG";
		upmmDbFields[ tableFldConstants.woendflag.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.woprodaccepflag.ordinal() ].fieldName = "UPMM_WOPRODACCEPFLAG";
		upmmDbFields[ tableFldConstants.woprodaccepflag.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldName = "UPMM_SUBASSEMBLYID";
		upmmDbFields[ tableFldConstants.subassemblyid.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.repeatedbdflag.ordinal() ].fieldName = "UPMM_REPEATEDBDFLAG";
		upmmDbFields[ tableFldConstants.repeatedbdflag.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.repeatedbdno.ordinal() ].fieldName = "UPMM_REPEATEDBDNO";
		upmmDbFields[ tableFldConstants.repeatedbdno.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "UPMM_RELATEDTO";
		upmmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.mould.ordinal() ].fieldName = "UPMM_MOULD";
		upmmDbFields[ tableFldConstants.mould.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "UPMM_ACTIVE";
		upmmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		upmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UPMM_CREATEDBY";
		upmmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		upmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UPMM_CREATEDON";
		upmmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		upmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UPMM_MODIFIEDON";
		upmmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PLM_TL_UNPLANNEDMAINTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PLM_TL_UNPLANNEDMAINTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PLM_TL_UNPLANNEDMAINTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}

