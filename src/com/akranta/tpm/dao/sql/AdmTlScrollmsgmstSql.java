package com.akranta.tpm.dao.sql;

public class AdmTlScrollmsgmstSql {

	public static final String TBL_ADM_TL_SCROLLMSGMST = "ADM_TL_SCROLLMSGMST";  

	TableFieldType [] smsgDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, roleid, cellid, shiftid, message, status, priority
		, fromdate, todate, noexpiry, noofocc, dwmy, optionno, recurrencefreq
		, weekday, weekno, dayonmonth, monthonyear, regenno, advanced
		, range, istobedisplayed, dis_alllevel, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSmsgDbFields() {
		return smsgDbFields;
	}

	public AdmTlScrollmsgmstSql()
	{
		smsgDbFields = new TableFieldType[ 31 ];
		for(int i = 0;i < 31; i++)
		{	
			smsgDbFields[ i ] = new TableFieldType();
		}
		smsgDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SMSG_KEYID";
		smsgDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SMSG_FLID";
		smsgDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "SMSG_ROLEID";
		smsgDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "SMSG_CELLID";
		smsgDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "SMSG_SHIFTID";
		smsgDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.message.ordinal() ].fieldName = "SMSG_MESSAGE";
		smsgDbFields[ tableFldConstants.message.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SMSG_STATUS";
		smsgDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.priority.ordinal() ].fieldName = "SMSG_PRIORITY";
		smsgDbFields[ tableFldConstants.priority.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "SMSG_FROMDATE";
		smsgDbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		smsgDbFields[ tableFldConstants.todate.ordinal() ].fieldName = "SMSG_TODATE";
		smsgDbFields[ tableFldConstants.todate.ordinal() ].fieldType = 'D';

		smsgDbFields[ tableFldConstants.noexpiry.ordinal() ].fieldName = "SMSG_NOEXPIRY";
		smsgDbFields[ tableFldConstants.noexpiry.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.noofocc.ordinal() ].fieldName = "SMSG_NOOFOCC";
		smsgDbFields[ tableFldConstants.noofocc.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.dwmy.ordinal() ].fieldName = "SMSG_DWMY";
		smsgDbFields[ tableFldConstants.dwmy.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.optionno.ordinal() ].fieldName = "SMSG_OPTIONNO";
		smsgDbFields[ tableFldConstants.optionno.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.recurrencefreq.ordinal() ].fieldName = "SMSG_RECURRENCEFREQ";
		smsgDbFields[ tableFldConstants.recurrencefreq.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.weekday.ordinal() ].fieldName = "SMSG_WEEKDAY";
		smsgDbFields[ tableFldConstants.weekday.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.weekno.ordinal() ].fieldName = "SMSG_WEEKNO";
		smsgDbFields[ tableFldConstants.weekno.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.dayonmonth.ordinal() ].fieldName = "SMSG_DAYONMONTH";
		smsgDbFields[ tableFldConstants.dayonmonth.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.monthonyear.ordinal() ].fieldName = "SMSG_MONTHONYEAR";
		smsgDbFields[ tableFldConstants.monthonyear.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.regenno.ordinal() ].fieldName = "SMSG_REGENNO";
		smsgDbFields[ tableFldConstants.regenno.ordinal() ].fieldType = 'N';

		smsgDbFields[ tableFldConstants.advanced.ordinal() ].fieldName = "SMSG_ADVANCED";
		smsgDbFields[ tableFldConstants.advanced.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.range.ordinal() ].fieldName = "SMSG_RANGE";
		smsgDbFields[ tableFldConstants.range.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.istobedisplayed.ordinal() ].fieldName = "SMSG_ISTOBEDISPLAYED";
		smsgDbFields[ tableFldConstants.istobedisplayed.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.dis_alllevel.ordinal() ].fieldName = "SMSG_DIS_ALLLEVEL";
		smsgDbFields[ tableFldConstants.dis_alllevel.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SMSG_TEMPFIELD2";
		smsgDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SMSG_TEMPFIELD3";
		smsgDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SMSG_TEMPFIELD4";
		smsgDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SMSG_ACTIVE";
		smsgDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		smsgDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SMSG_CREATEDBY";
		smsgDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		smsgDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SMSG_CREATEDON";
		smsgDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		smsgDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SMSG_MODIFIEDON";
		smsgDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_ADM_TL_SCROLLMSGMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_ADM_TL_SCROLLMSGMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_ADM_TL_SCROLLMSGMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		String sql= " select SMSG_KEYID,SMSG_FLID,SMSG_MESSAGE,TO_CHAR(SMSG_FROMDATE),TO_CHAR(SMSG_TODATE),SMSG_ISTOBEDISPLAYED ";
		sql+= "  from ADM_TL_SCROLLMSGMST " ;
		sql+= " where SMSG_KEYID='"+keyid+"'";
	    return sql;
	}

}

