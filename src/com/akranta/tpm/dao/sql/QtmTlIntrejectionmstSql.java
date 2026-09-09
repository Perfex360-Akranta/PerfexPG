package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class QtmTlIntrejectionmstSql {

	public static final String TBL_QTM_TL_INTREJECTIONMST = "QTM_TL_INTREJECTIONMST";  

	TableFieldType [] qirmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, plmasterid, pldetailid
		, prodgroupid, productid, cavity, inspectiondate, inspectionid
		, totalproduction, testingscrap, batchno, inspectionqty, acceptedqty
		, backlogqty, qaholdqty, qaholdpercentage, shiftdate, shiftid
		, entryby, productiondate, qualityentryby, qualityentrydate, approvalby
		, approvaldate, status, backlogflag, referencekeyid, remarks
		, mrbqty, inspectedshiftid, balanceqty, linkmasterid, actualproduced
		, virtualproduced, parentmasterid, tempfield1, tempfield2, tempfield3
		, tempfield4, tempfield5, elementid,flid,active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getQirmDbFields() {
		return qirmDbFields;
	}

	public QtmTlIntrejectionmstSql()
	{
		qirmDbFields = new TableFieldType[ 50 ];
		for(int i = 0;i < 50; i++)
		{	
			qirmDbFields[ i ] = new TableFieldType();
		}
		qirmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "QIRM_KEYID";
		qirmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "QIRM_FACTORYID";
		qirmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "QIRM_SECTIONID";
		qirmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "QIRM_CELLID";
		qirmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "QIRM_MACHINEID";
		qirmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldName = "QIRM_PLMASTERID";
		qirmDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldName = "QIRM_PLDETAILID";
		qirmDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldName = "QIRM_PRODGROUPID";
		qirmDbFields[ tableFldConstants.prodgroupid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "QIRM_PRODUCTID";
		qirmDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.cavity.ordinal() ].fieldName = "QIRM_CAVITY";
		qirmDbFields[ tableFldConstants.cavity.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.inspectiondate.ordinal() ].fieldName = "QIRM_INSPECTIONDATE";
		qirmDbFields[ tableFldConstants.inspectiondate.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldName = "QIRM_INSPECTIONID";
		qirmDbFields[ tableFldConstants.inspectionid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.totalproduction.ordinal() ].fieldName = "QIRM_TOTALPRODUCTION";
		qirmDbFields[ tableFldConstants.totalproduction.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.testingscrap.ordinal() ].fieldName = "QIRM_TESTINGSCRAP";
		qirmDbFields[ tableFldConstants.testingscrap.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.batchno.ordinal() ].fieldName = "QIRM_BATCHNO";
		qirmDbFields[ tableFldConstants.batchno.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.inspectionqty.ordinal() ].fieldName = "QIRM_INSPECTIONQTY";
		qirmDbFields[ tableFldConstants.inspectionqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.acceptedqty.ordinal() ].fieldName = "QIRM_ACCEPTEDQTY";
		qirmDbFields[ tableFldConstants.acceptedqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.backlogqty.ordinal() ].fieldName = "QIRM_BACKLOGQTY";
		qirmDbFields[ tableFldConstants.backlogqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.qaholdqty.ordinal() ].fieldName = "QIRM_QAHOLDQTY";
		qirmDbFields[ tableFldConstants.qaholdqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.qaholdpercentage.ordinal() ].fieldName = "QIRM_QAHOLDPERCENTAGE";
		qirmDbFields[ tableFldConstants.qaholdpercentage.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldName = "QIRM_SHIFTDATE";
		qirmDbFields[ tableFldConstants.shiftdate.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "QIRM_SHIFTID";
		qirmDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.entryby.ordinal() ].fieldName = "QIRM_ENTRYBY";
		qirmDbFields[ tableFldConstants.entryby.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.productiondate.ordinal() ].fieldName = "QIRM_PRODUCTIONDATE";
		qirmDbFields[ tableFldConstants.productiondate.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.qualityentryby.ordinal() ].fieldName = "QIRM_QUALITYENTRYBY";
		qirmDbFields[ tableFldConstants.qualityentryby.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.qualityentrydate.ordinal() ].fieldName = "QIRM_QUALITYENTRYDATE";
		qirmDbFields[ tableFldConstants.qualityentrydate.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.approvalby.ordinal() ].fieldName = "QIRM_APPROVALBY";
		qirmDbFields[ tableFldConstants.approvalby.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.approvaldate.ordinal() ].fieldName = "QIRM_APPROVALDATE";
		qirmDbFields[ tableFldConstants.approvaldate.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "QIRM_STATUS";
		qirmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		qirmDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldName = "QIRM_BACKLOGFLAG";
		qirmDbFields[ tableFldConstants.backlogflag.ordinal() ].fieldType = 'C';

		qirmDbFields[ tableFldConstants.referencekeyid.ordinal() ].fieldName = "QIRM_REFERENCEKEYID";
		qirmDbFields[ tableFldConstants.referencekeyid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "QIRM_REMARKS";
		qirmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.mrbqty.ordinal() ].fieldName = "QIRM_MRBQTY";
		qirmDbFields[ tableFldConstants.mrbqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.inspectedshiftid.ordinal() ].fieldName = "QIRM_INSPECTEDSHIFTID";
		qirmDbFields[ tableFldConstants.inspectedshiftid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.balanceqty.ordinal() ].fieldName = "QIRM_BALANCEQTY";
		qirmDbFields[ tableFldConstants.balanceqty.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.linkmasterid.ordinal() ].fieldName = "QIRM_LINKMASTERID";
		qirmDbFields[ tableFldConstants.linkmasterid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.actualproduced.ordinal() ].fieldName = "QIRM_ACTUALPRODUCED";
		qirmDbFields[ tableFldConstants.actualproduced.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.virtualproduced.ordinal() ].fieldName = "QIRM_VIRTUALPRODUCED";
		qirmDbFields[ tableFldConstants.virtualproduced.ordinal() ].fieldType = 'N';

		qirmDbFields[ tableFldConstants.parentmasterid.ordinal() ].fieldName = "QIRM_PARENTMASTERID";
		qirmDbFields[ tableFldConstants.parentmasterid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "QIRM_TEMPFIELD1";
		qirmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "QIRM_TEMPFIELD2";
		qirmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "QIRM_TEMPFIELD3";
		qirmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "QIRM_TEMPFIELD4";
		qirmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "QIRM_TEMPFIELD5";
		qirmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "QIRM_ELEMENTID";
		qirmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "QIRM_FLID";
		qirmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		qirmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "QIRM_ACTIVE";
		qirmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		qirmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "QIRM_CREATEDBY";
		qirmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		qirmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "QIRM_CREATEDON";
		qirmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		qirmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "QIRM_MODIFIEDON";
		qirmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_QTM_TL_INTREJECTIONMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_QTM_TL_INTREJECTIONMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_QTM_TL_INTREJECTIONMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String MRBUpdateSql(String qihbKeyid)
	{
		StringBuffer sql1 =new StringBuffer();
		sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_MRBQTY=( ");
		sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
		sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='MRB')");
		sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");	
		return sql1.toString();
	}
	public static String QAHUpdateSql(String qihbKeyid)
	{
		StringBuffer sql1 =new StringBuffer();
		sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_QAHOLD =( ");
		sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
		sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='QAH')");
		sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");	
		return sql1.toString();
	}
	public static String UpdateHourlySql()
	{
		StringBuffer sql2 =new StringBuffer();
		sql2.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY = (QIHB_INSPECTEDQTY - (QIHB_MRBQTY+QIHB_QAHOLD)), ") ;
		sql2.append(" QIHB_REJECTEDQTY= (QIHB_MRBQTY+QIHB_QAHOLD) ");
		sql2.append(" WHERE QIHB_KEYID= ?");
		return sql2.toString();
	}
	public static String UpdateRejectionMstSql(String qirmKeyid)
	{
		StringBuffer sql3 =new StringBuffer();
		sql3.append(" UPDATE QTM_TL_INTREJECTIONMST SET   ") ;
		sql3.append(" QIRM_INSPECTIONQTY = (SELECT nvl(SUM(QIHB_INSPECTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'), ");
		sql3.append(" QIRM_ACCEPTEDQTY = (SELECT nvl(SUM(QIHB_ACCEPTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
		sql3.append(" QIRM_BACKLOGQTY = (SELECT nvl(SUM(QIHB_REJECTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
		sql3.append(" QIRM_QAHOLDQTY = (SELECT nvl(SUM(QIHB_QAHOLD),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
		sql3.append(" QIRM_QAHOLDPERCENTAGE = (SELECT nvl(ROUND(DECODE(SUM(QIHB_INSPECTEDQTY),0,0,SUM(QIHB_QAHOLD)/SUM(QIHB_INSPECTEDQTY)*100),2),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "')");
		sql3.append("  WHERE QIRM_KEYID='" + qirmKeyid + "' ");
		return sql3.toString();
	}
	public static String PCSUpdateFuncn(){
			return "PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL";
	}
	public static String getBalanceQtySql(String machineId,String entryDate,String shiftId,String detailTable)
	{
		StringBuffer sql3 =new StringBuffer();
		sql3.append("SELECT (DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0)) - ");
		sql3.append(" SUM(DECODE(QIHB_INSPECTEDQTY, NULL, DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0)), QIHB_INSPECTEDQTY))) AS INSPECTEDQTY");
		sql3.append(" FROM PCS_TL_MST ,QTM_TL_INTREJECTIONMST ,(SELECT QIHB_QTM_KEYID, SUM(QIHB_INSPECTEDQTY) AS QIHB_INSPECTEDQTY,");
		sql3.append("SUM(QIHB_ACCEPTEDQTY) AS QIHB_ACCEPTEDQTY FROM QTM_TL_INTERNALREJECTIONHOURLY");
		sql3.append(" WHERE QIHB_ACTIVE='Y' GROUP BY QIHB_QTM_KEYID),"+detailTable+" WHERE ");
		sql3.append("PRLM_KEYID=PLMASTERID AND PRODUCEDQTY > 0  AND QIRM_PLDETAILID(+) = PLDETAILSID"); 
		sql3.append(" AND QIRM_KEYID = QIHB_QTM_KEYID(+) AND MACHINEID ='"+machineId+"' AND PRLM_SHIFTID ='"+shiftId+"'");
		sql3.append(" AND TRUNC(PRLM_ENTRYDATE) >= '"+entryDate+"' AND TRUNC(PRLM_ENTRYDATE)<='"+entryDate+"'");
		sql3.append(" GROUP BY DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0))");
		CommonMessage.debugMsg(sql3.toString());
		return sql3.toString();
	}
	
	
	public static String getPendingColorSql(String entryDate,String curDate,String cellId,String mchId,String detailTable)
	{
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT TO_CHAR(PRLM_ENTRYDATE, 'DD-Mon-YYYY'), PRLM_SHIFTID, BALQTY FROM ( ");
			sb.append("SELECT PRLM_ENTRYDATE, PRLM_SHIFTID, (DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0)) - ");
			sb.append(" SUM(DECODE(QIHB_INSPECTEDQTY, NULL, DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0)), QIHB_INSPECTEDQTY))) AS BALQTY,");
			sb.append(" TO_CHAR(PRLM_ENTRYDATE, 'YYYYMMDD') ORDDT FROM PCS_TL_MST ,QTM_TL_INTREJECTIONMST ,");
			sb.append("(SELECT QIHB_QTM_KEYID, SUM(QIHB_INSPECTEDQTY) AS QIHB_INSPECTEDQTY, SUM(QIHB_ACCEPTEDQTY) AS QIHB_ACCEPTEDQTY FROM QTM_TL_INTERNALREJECTIONHOURLY ");
			sb.append(" WHERE QIHB_ACTIVE='Y' GROUP BY QIHB_QTM_KEYID),"+detailTable+" WHERE ");
			sb.append(" PRLM_KEYID=PLMASTERID AND PRODUCEDQTY > 0 AND QIRM_PLDETAILID(+) = PLDETAILSID "); 
			sb.append("AND QIRM_KEYID = QIHB_QTM_KEYID(+) AND TO_CHAR(PRLM_ENTRYDATE, 'MON-YYYY') = UPPER('"+entryDate+"')");
			if(CommonFunctions.isValidKeyId(cellId))
				sb.append(" AND CELLID = '"+cellId+"'");
			if(CommonFunctions.isValidKeyId(mchId))
				sb.append(" AND MACHINEID = '"+mchId+"'");
			sb.append(" GROUP BY PRLM_ENTRYDATE, PRLM_SHIFTID, DECODE(NVL(EXPANSIONQTY,0),0,NVL(PRODUCEDQTY,0)- NVL(REJECTEDQTY,0),NVL(EXPANSIONQTY,0)))");
			sb.append(" WHERE BALQTY >0	ORDER BY TO_CHAR(PRLM_ENTRYDATE, 'YYYYMMDD')");
			CommonMessage.debugMsg("getPendingColorSql "+sb.toString());
			return sb.toString();
	}

	public String selectmst(String keyid) {
		return "SELECT * from "+TBL_QTM_TL_INTREJECTIONMST +" where QIrm_Keyid = ?";
	}
}

