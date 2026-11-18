package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_PcsTlProductiondataSql {

	public static final String TBL_BAL_PCS_TL_PRODUCTIONDATA = "BAL_PCS_TL_PRODUCTIONDATA";  
	public static final String TBL_PCS_TL_REJECTIONBREAKUPDATA = "PCS_TL_REJECTIONBREAKUPDATA"; 
	TableFieldType [] pprdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, cellid, date, shiftid, hour, productid, productmodel
		, manpower, cycletime, producedqty, rejectedqty, reworkqty, shiftnoplan
		, hourlynoplan, noplanduration, remarks, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPprdDbFields() {
		return pprdDbFields;
	}

	public BAL_PcsTlProductiondataSql()
	{
		pprdDbFields = new TableFieldType[ 24 ];
		for(int i = 0;i < 24; i++)
		{	
			pprdDbFields[ i ] = new TableFieldType();
		}
		pprdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PPRD_KEYID";
		pprdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PPRD_FLID";
		pprdDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PPRD_CELLID";
		pprdDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PPRD_DATE";
		pprdDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		pprdDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "PPRD_SHIFTID";
		pprdDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.hour.ordinal() ].fieldName = "PPRD_HOUR";
		pprdDbFields[ tableFldConstants.hour.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "PPRD_PRODUCTID";
		pprdDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.productmodel.ordinal() ].fieldName = "PPRD_PRODUCTMODEL";
		pprdDbFields[ tableFldConstants.productmodel.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.manpower.ordinal() ].fieldName = "PPRD_MANPOWER";
		pprdDbFields[ tableFldConstants.manpower.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.cycletime.ordinal() ].fieldName = "PPRD_CYCLETIME";
		pprdDbFields[ tableFldConstants.cycletime.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.producedqty.ordinal() ].fieldName = "PPRD_PRODUCEDQTY";
		pprdDbFields[ tableFldConstants.producedqty.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldName = "PPRD_REJECTEDQTY";
		pprdDbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.reworkqty.ordinal() ].fieldName = "PPRD_REWORKQTY";
		pprdDbFields[ tableFldConstants.reworkqty.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.shiftnoplan.ordinal() ].fieldName = "PPRD_SHIFTNOPLAN";
		pprdDbFields[ tableFldConstants.shiftnoplan.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.hourlynoplan.ordinal() ].fieldName = "PPRD_HOURLYNOPLAN";
		pprdDbFields[ tableFldConstants.hourlynoplan.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.noplanduration.ordinal() ].fieldName = "PPRD_NOPLANDURATION";
		pprdDbFields[ tableFldConstants.noplanduration.ordinal() ].fieldType = 'N';

		pprdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "PPRD_REMARKS";
		pprdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "PPRD_TEMPFIELD1";
		pprdDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "PPRD_TEMPFIELD2";
		pprdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "PPRD_TEMPFIELD3";
		pprdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PPRD_ACTIVE";
		pprdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pprdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PPRD_CREATEDBY";
		pprdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pprdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PPRD_CREATEDON";
		pprdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pprdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PPRD_MODIFIEDON";
		pprdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_PCS_TL_PRODUCTIONDATA, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_BAL_PCS_TL_PRODUCTIONDATA, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_BAL_PCS_TL_PRODUCTIONDATA );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	public static String deleteProductiondata(String keyid) {
		
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_BAL_PCS_TL_PRODUCTIONDATA );
		sql.append( " where " );
		if(keyid.startsWith("PRL"))
			{
				sql.append("PPRD_TEMPFIELD2"); 
				sql.append( " = '" );
				sql.append(keyid);
			}
		else
		{
			sql.append("PPRD_KEYID"); 
			sql.append( " = '" );
			sql.append(keyid);
		}
		
		sql.append(	"'");
		
		CommonFunctions.debugMsg("Production delete sql"+sql);
		return sql.toString();
	
	}
	public static String deleteRejectiondata(String keyid){
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_PCS_TL_REJECTIONBREAKUPDATA );
		sql.append( " where " );
		sql.append("PRBD_PPRD_KEYID"); 
		sql.append( " = '" );
		sql.append(keyid);
		sql.append(	"'");
		CommonFunctions.debugMsg("Production delete sql"+sql);
		return sql.toString();
	}

}

