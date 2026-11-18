package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_PcsTlDtlSql.tableFldConstants;

public class BAL_PcsTlHtHistrorySql {
	
	
	
	
	public static final String TBL_PCS_TL_HTPRODSPECMSTHISTORY = "PCS_TL_HTPRODSPECMSTHISTORY";  

	TableFieldType [] pcs_DbFields = null;

	public enum   tableFldConstants
	{
		keyid, cellid, date, shiftid,  productid, batchquantity,weightprice,area,fromdate,todate,remarks,
		tempfield1, tempfield2, tempfield3, active, createdby, createdon, modifiedon
	}


	public TableFieldType[] getPcs_DbFields() {
		return pcs_DbFields;
	}

	public BAL_PcsTlHtHistrorySql()
	{
		pcs_DbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			pcs_DbFields[ i ] = new TableFieldType();
		}
		pcs_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "HPSH_KEYID";
		pcs_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		pcs_DbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "HPSH_CELLID";
		pcs_DbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "HPSH_SUBGROUPID";
		pcs_DbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.productid.ordinal() ].fieldName = "HPSH_PRODUCTID";
		pcs_DbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.batchquantity.ordinal() ].fieldName = "HPSH_BATCHQUANTITY";
		pcs_DbFields[ tableFldConstants.batchquantity.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.weightprice.ordinal() ].fieldName = "HPSH_WEIGHTPERPIECE";
		pcs_DbFields[ tableFldConstants.weightprice.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.area.ordinal() ].fieldName = "HPSH_AREA";
		pcs_DbFields[ tableFldConstants.area.ordinal() ].fieldType = 'C';

		pcs_DbFields[ tableFldConstants.fromdate.ordinal() ].fieldName = "HPSH_FROMDATE";
		pcs_DbFields[ tableFldConstants.fromdate.ordinal() ].fieldType = 'D';

		pcs_DbFields[ tableFldConstants.todate.ordinal() ].fieldName = "HPSH_TODATE";
		pcs_DbFields[ tableFldConstants.todate.ordinal() ].fieldType = 'D';

		pcs_DbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "HPSH_REMARKS";
		pcs_DbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "HPSH_TEMPFIELD1";
		pcs_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "HPSH_TEMPFIELD2";
		pcs_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		pcs_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "HPSH_TEMPFIELD3";
		pcs_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "HPSH_ACTIVE";
		pcs_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "HPSH_CREATEDBY";
		pcs_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "HPSH_CREATEDON";
		pcs_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pcs_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "HPSH_MODIFIEDON";
		pcs_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}
	public static String getUpdateSql(String detailTableName, TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_DTL, fieldTypeArr, dataArray);
		String sql = SqlUtils.getUpdatePcsSql(detailTableName, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getUpdatePcsProductionSql(String detailTableName, TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_DTL, fieldTypeArr, dataArray);
		String sql = SqlUtils.getUpdatePcsSql(detailTableName, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_HTPRODSPECMSTHISTORY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

}
