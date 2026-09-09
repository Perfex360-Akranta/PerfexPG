package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.QtmTlInternalrejectionhourly;

public class QtmTlInternalrejectionhourlySql {

	public static final String TBL_QTM_TL_INTERNALREJECTIONHOURLY = "QTM_TL_INTERNALREJECTIONHOURLY";  

	TableFieldType [] qihbDbFields = null;

	public enum   tableFldConstants
	{
		keyid, qtm_keyid, shifthour, shifttiming, inspectedqty, acceptedqty
		, balanceqty, rejectedqty, testingqty, mrbqty, qahold, displayorder
		, reserve1, reserve2, reserve3, reserve4, reserve5, active, userindex
		, timestamp, modtimestamp
	}

	public TableFieldType[] getQihbDbFields() {
		return qihbDbFields;
	}

	public QtmTlInternalrejectionhourlySql()
	{
		qihbDbFields = new TableFieldType[ 21 ];
		for(int i = 0;i < 21; i++)
		{	
			qihbDbFields[ i ] = new TableFieldType();
		}
		qihbDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QIHB_KEYID";
		qihbDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.qtm_keyid.ordinal() ].fieldName = "QIHB_QTM_KEYID";
		qihbDbFields[ tableFldConstants.qtm_keyid.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.shifthour.ordinal() ].fieldName = "QIHB_SHIFTHOUR";
		qihbDbFields[ tableFldConstants.shifthour.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.shifttiming.ordinal() ].fieldName = "QIHB_SHIFTTIMING";
		qihbDbFields[ tableFldConstants.shifttiming.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.inspectedqty.ordinal() ].fieldName = "QIHB_INSPECTEDQTY";
		qihbDbFields[ tableFldConstants.inspectedqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.acceptedqty.ordinal() ].fieldName = "QIHB_ACCEPTEDQTY";
		qihbDbFields[ tableFldConstants.acceptedqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.balanceqty.ordinal() ].fieldName = "QIHB_BALANCEQTY";
		qihbDbFields[ tableFldConstants.balanceqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldName = "QIHB_REJECTEDQTY";
		qihbDbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.testingqty.ordinal() ].fieldName = "QIHB_TESTINGQTY";
		qihbDbFields[ tableFldConstants.testingqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.mrbqty.ordinal() ].fieldName = "QIHB_MRBQTY";
		qihbDbFields[ tableFldConstants.mrbqty.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.qahold.ordinal() ].fieldName = "QIHB_QAHOLD";
		qihbDbFields[ tableFldConstants.qahold.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.displayorder.ordinal() ].fieldName = "QIHB_DISPLAYORDER";
		qihbDbFields[ tableFldConstants.displayorder.ordinal() ].fieldType = 'N';

		qihbDbFields[ tableFldConstants.reserve1.ordinal() ].fieldName = "QIHB_RESERVE1";
		qihbDbFields[ tableFldConstants.reserve1.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.reserve2.ordinal() ].fieldName = "QIHB_RESERVE2";
		qihbDbFields[ tableFldConstants.reserve2.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.reserve3.ordinal() ].fieldName = "QIHB_RESERVE3";
		qihbDbFields[ tableFldConstants.reserve3.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.reserve4.ordinal() ].fieldName = "QIHB_RESERVE4";
		qihbDbFields[ tableFldConstants.reserve4.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.reserve5.ordinal() ].fieldName = "QIHB_RESERVE5";
		qihbDbFields[ tableFldConstants.reserve5.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QIHB_ACTIVE";
		qihbDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qihbDbFields[ tableFldConstants.userindex.ordinal() ].fieldName = "QIHB_USERINDEX";
		qihbDbFields[ tableFldConstants.userindex.ordinal() ].fieldType = 'V';

		qihbDbFields[ tableFldConstants.timestamp.ordinal() ].fieldName = "QIHB_TIMESTAMP";
		qihbDbFields[ tableFldConstants.timestamp.ordinal() ].fieldType = 'D';

		qihbDbFields[ tableFldConstants.modtimestamp.ordinal() ].fieldName = "QIHB_MODTIMESTAMP";
		qihbDbFields[ tableFldConstants.modtimestamp.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_INTERNALREJECTIONHOURLY, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_INTERNALREJECTIONHOURLY, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_INTERNALREJECTIONHOURLY ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getQtySql(String Id,String keyId)
	{
		String sql = "SELECT SUM(QIHB_INSPECTEDQTY) QIHB_INSPECTEDQTY, SUM(QIHB_ACCEPTEDQTY) QIHB_ACCEPTEDQTY,";
		sql += "  SUM(QIHB_MRBQTY+QIHB_QAHOLD) QAREJECTION,SUM(QIHB_REJECTEDQTY) QIHB_REJECTEDQTY";
		sql += " FROM "+TBL_QTM_TL_INTERNALREJECTIONHOURLY+" WHERE QIHB_QTM_KEYID = '"+Id+"'";
		sql += " AND QIHB_KEYID <> '"+keyId+"'";
			
			return sql;
	}
	public static String updateMstSql(int insQty,int accQty,String id)
	{
		String sql = "UPDATE QTM_TL_INTREJECTIONMST SET QIRM_INSPECTIONQTY = "+insQty+",";
			   sql += "QIRM_ACCEPTEDQTY = "+accQty+" WHERE QIRM_KEYID = '"+id+"'";
			   return sql;
			
	}
	public static String updateDtlSql(String tableName,int insQty,int accQty,int rejQty,int qaRejection,String id,String colName)
	{
		/*String sql = "UPDATE "+tableName+" SET INSPECTEDQTY = "+insQty+",";
			sql += "QAACCEPTEDQTY = "+ accQty +","+colName+" = "+ qaRejection +",REJECTEDQTY='"+rejQty+"' WHERE PLDETAILSID IN ";
			sql += "(SELECT QIRM_PLDETAILID FROM QTM_TL_INTREJECTIONMST WHERE QIRM_KEYID = '"+id+"')";
			return sql;
		*/	
		String sql = "UPDATE "+tableName+" SET INSPECTEDQTY = "+insQty+",";
		sql += "QAACCEPTEDQTY = "+ accQty +","+colName+" = "+ qaRejection +" WHERE PLDETAILSID IN ";
		sql += "(SELECT QIRM_PLDETAILID FROM QTM_TL_INTREJECTIONMST WHERE QIRM_KEYID = '"+id+"')";
		return sql;
	}
	public static String pcsDaySql(String keyId,String mchId,String lossId,String entryDate)
	{
		String sql = "UPDATE PCS_TL_DAY SET DEFECTSANDREWORKLOSS_ML = (SELECT SUM(NVL(REJECTEDQTY,0) + NVL(REWORKQTY,0))";
				sql +=" FROM PCS_TL_MST, PCS_TL_EXPANSION WHERE PRLM_KEYID = PLMASTERID AND PRLM_KEYID = '"+keyId+"'";
				sql +=" AND PRLM_ENTRYDATE = '"+entryDate+"' AND MACHINEID = '"+mchId+"'),";
				sql +="QAREJECTION = (SELECT SUM(NVL("+lossId+",0)) FROM PCS_TL_MST, PCS_TL_EXPANSION";
				sql +=" WHERE PRLM_KEYID = PLMASTERID AND PRLM_KEYID = '"+keyId+"'";
				sql +=" AND PRLM_ENTRYDATE = '"+entryDate+"' AND MACHINEID = '"+mchId+"')";
				
				sql +=" WHERE ENTRYDATE = '"+entryDate+"' AND MACHINEID = '"+mchId+"'";
				return sql;
	}
	public static String getPcsFieldsSql(String keyId)
	{
		String sql = "SELECT QIRM_PLMASTERID,QIRM_PLDETAILID,QIRM_MACHINEID FROM Qtm_Tl_Intrejectionmst";
			   sql += " WHERE QIRM_KEYID='"+keyId+"'";
	    return sql;
	}
}

