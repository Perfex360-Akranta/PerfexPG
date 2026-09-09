package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.GenTlNearmissreportmst;

public class GenTlNearmissreportdtlSql { 

	public static final String TBL_GEN_TL_NEARMISSREPORTDTL = "GEN_TL_NEARMISSREPORTDTL";  

	TableFieldType [] nmuaDbFields = null;

	public enum   tableFldConstants
	{
		keyid, nmrtkeyid, nearkeyid, code, tempfield1, tempfield2, tempfield3
		, tempfield4, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getNmuaDbFields() {
		return nmuaDbFields;
	}

	public GenTlNearmissreportdtlSql()
	{
		nmuaDbFields = new TableFieldType[ 12 ];
		for(int i = 0;i < 12; i++)
		{	
			nmuaDbFields[ i ] = new TableFieldType();
		}
		nmuaDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "NMUA_KEYID";
		nmuaDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		nmuaDbFields[ tableFldConstants.nmrtkeyid.ordinal() ].fieldName = "NMUA_NMRTKEYID";
		nmuaDbFields[ tableFldConstants.nmrtkeyid.ordinal() ].fieldType = 'V';

		nmuaDbFields[ tableFldConstants.nearkeyid.ordinal() ].fieldName = "NMUA_NEARKEYID";
		nmuaDbFields[ tableFldConstants.nearkeyid.ordinal() ].fieldType = 'V';

		nmuaDbFields[ tableFldConstants.code.ordinal() ].fieldName = "NMUA_CODE";
		nmuaDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		nmuaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "NMUA_TEMPFIELD1";
		nmuaDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		nmuaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "NMUA_TEMPFIELD2";
		nmuaDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		nmuaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "NMUA_TEMPFIELD3";
		nmuaDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		nmuaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "NMUA_TEMPFIELD4";
		nmuaDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		nmuaDbFields[ tableFldConstants.active.ordinal() ].fieldName = "NMUA_ACTIVE";
		nmuaDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		nmuaDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "NMUA_CREATEDBY";
		nmuaDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		nmuaDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "NMUA_CREATEDON";
		nmuaDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		nmuaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "NMUA_MODIFIEDON";
		nmuaDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_NEARMISSREPORTDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_NEARMISSREPORTDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_NEARMISSREPORTDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSingleData(){
		String sql="SELECT * from " + TBL_GEN_TL_NEARMISSREPORTDTL +" where NMUA_NMRTKEYID = ?";
		return sql;
		
	}

	public static String getDeleteDtl(String keyId) {
		String sql="DELETE from " + TBL_GEN_TL_NEARMISSREPORTDTL +" where NMUA_NMRTKEYID = '";
		sql+=keyId;
		sql+="'";
		return sql;
	}

	public static String checkDuplicate(GenTlNearmissreportmst genTlNearmissreportmst) {

		//String[] val = genTlNearmissreportmst.getNmrtOccurrencedatetime().split(":");
		//String sql = " SELECT COUNT(*) FROM GEN_TL_NEARMISSREPORTMST WHERE NMRT_EMPLOYEEID='"+genTlNearmissreportmst.getNmrtEmployeeid()+"' and to_char(NMRT_OCCURRENCEDATETIME,'DD-MON-YYYY HH24') = upper('"+val[0]+"')";
		String occurDatetime = genTlNearmissreportmst.getNmrtOccurrencedatetime();
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT COUNT(*) FROM GEN_TL_NEARMISSREPORTMST  " );
		sb.append(" WHERE NMRT_EMPLOYEEID='"+genTlNearmissreportmst.getNmrtEmployeeid()+"' " );
		sb.append(" AND To_Char(Nmrt_Occurrencedatetime,'DD-MON-YYYY HH24')  = TO_CHAR( to_date('" + occurDatetime + "','DD-MON-YYYY HH24:MI:SS' ),'DD-MON-YYYY HH24') " );
		return sb.toString();
	}
}

