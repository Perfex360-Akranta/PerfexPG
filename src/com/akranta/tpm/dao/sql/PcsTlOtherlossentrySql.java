package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.PcsTlOtherlossentry;

public class PcsTlOtherlossentrySql {

	public static final String TBL_PCS_TL_OTHERLOSSENTRY = "PCS_TL_OTHERLOSSENTRY";  

	TableFieldType [] olseDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, lossid, lossdate, lossvalue, tempfield1
		, tempfield2, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getOlseDbFields() {
		return olseDbFields;
	}

	public PcsTlOtherlossentrySql()
	{
		olseDbFields = new TableFieldType[ 19 ];
		for(int i = 0;i < 19; i++)
		{	
			olseDbFields[ i ] = new TableFieldType();
		}
		olseDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "OLSE_KEYID";
		olseDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "OLSE_FLID";
		olseDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "OLSE_ELEMENTID";
		olseDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.date.ordinal() ].fieldName = "OLSE_DATE";
		olseDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		olseDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "OLSE_LOSSID";
		olseDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.lossdate.ordinal() ].fieldName = "OLSE_LOSSDATE";
		olseDbFields[ tableFldConstants.lossdate.ordinal() ].fieldType = 'D';

		olseDbFields[ tableFldConstants.lossvalue.ordinal() ].fieldName = "OLSE_LOSSVALUE";
		olseDbFields[ tableFldConstants.lossvalue.ordinal() ].fieldType = 'N';

		olseDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "OLSE_TEMPFIELD1";
		olseDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "OLSE_TEMPFIELD2";
		olseDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "OLSE_TEMPFIELD3";
		olseDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "OLSE_TEMPFIELD4";
		olseDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "OLSE_TEMPFIELD5";
		olseDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "OLSE_TEMPFIELD6";
		olseDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "OLSE_TEMPFIELD7";
		olseDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "OLSE_TEMPFIELD8";
		olseDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.active.ordinal() ].fieldName = "OLSE_ACTIVE";
		olseDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		olseDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "OLSE_CREATEDBY";
		olseDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		olseDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "OLSE_CREATEDON";
		olseDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		olseDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "OLSE_MODIFIEDON";
		olseDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_OTHERLOSSENTRY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_OTHERLOSSENTRY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_OTHERLOSSENTRY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String updateLossVal(PcsTlOtherlossentry pcsTlOtherlossentry) {
		String sql=" UPDATE " + TBL_PCS_TL_OTHERLOSSENTRY + " SET OLSE_LOSSVALUE='"+pcsTlOtherlossentry.getOlseLossvalue()+"' WHERE OLSE_KEYID='"+pcsTlOtherlossentry.getOlseKeyid()+"'";
		return sql;
	}

}

