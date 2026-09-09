package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


public class KznTlHdmstSql {

	public static final String TBL_KZN_TL_HDMST = "KZN_TL_HDMST";  

	TableFieldType [] khdmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, slno, kaizenid, refdoctype, refdocno, factoryid, sectionid
		, cellid, machineid, kaizenlink, kaizenlinktype, assemblyid, phenomenaid
		, causeid, lossid, targetdate, responsibilityid, status, woid
		, wofeedbackid, completeddate, completedby, remarks, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getKhdmDbFields() {
		return khdmDbFields;
	}

	public KznTlHdmstSql()
	{
		khdmDbFields = new TableFieldType[ 27 ];
		for(int i = 0;i < 27; i++)
		{	
			khdmDbFields[ i ] = new TableFieldType();
		}
		khdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KHDM_KEYID";
		khdmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.slno.ordinal() ].fieldName = "KHDM_SLNO";
		khdmDbFields[ tableFldConstants.slno.ordinal() ].fieldType = 'N';

		khdmDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "KHDM_KAIZENID";
		khdmDbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "KHDM_REFDOCTYPE";
		khdmDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "KHDM_REFDOCNO";
		khdmDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "KHDM_FACTORYID";
		khdmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "KHDM_SECTIONID";
		khdmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "KHDM_CELLID";
		khdmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "KHDM_MACHINEID";
		khdmDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.kaizenlink.ordinal() ].fieldName = "KHDM_KAIZENLINK";
		khdmDbFields[ tableFldConstants.kaizenlink.ordinal() ].fieldType = 'C';

		khdmDbFields[ tableFldConstants.kaizenlinktype.ordinal() ].fieldName = "KHDM_KAIZENLINKTYPE";
		khdmDbFields[ tableFldConstants.kaizenlinktype.ordinal() ].fieldType = 'C';

		khdmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "KHDM_ASSEMBLYID";
		khdmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldName = "KHDM_PHENOMENAID";
		khdmDbFields[ tableFldConstants.phenomenaid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "KHDM_CAUSEID";
		khdmDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "KHDM_LOSSID";
		khdmDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "KHDM_TARGETDATE";
		khdmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		khdmDbFields[ tableFldConstants.responsibilityid.ordinal() ].fieldName = "KHDM_RESPONSIBILITYID";
		khdmDbFields[ tableFldConstants.responsibilityid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KHDM_STATUS";
		khdmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		khdmDbFields[ tableFldConstants.woid.ordinal() ].fieldName = "KHDM_WOID";
		khdmDbFields[ tableFldConstants.woid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldName = "KHDM_WOFEEDBACKID";
		khdmDbFields[ tableFldConstants.wofeedbackid.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldName = "KHDM_COMPLETEDDATE";
		khdmDbFields[ tableFldConstants.completeddate.ordinal() ].fieldType = 'D';

		khdmDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "KHDM_COMPLETEDBY";
		khdmDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "KHDM_REMARKS";
		khdmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KHDM_ACTIVE";
		khdmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		khdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KHDM_CREATEDBY";
		khdmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		khdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KHDM_CREATEDON";
		khdmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		khdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KHDM_MODIFIEDON";
		khdmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_HDMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_HDMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_HDMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'"+
			  " AND  KHDM_STATUS = 'A'";
		return sql;
	}
	
	
	public static String getDeleteKznSql(TableFieldType [] fieldTypeArr, String khdmKeyId)
	{
		String sql = "DELETE from " + TBL_KZN_TL_HDMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.kaizenid.ordinal()].fieldName  +
			  " = '" +khdmKeyId+ "'";
			  
		return sql;
	}
	
	
	public static String getDeleteSqls(String kaizenId)
	{
		String sql = "DELETE from " + TBL_KZN_TL_HDMST + " where KHDM_KAIZENID = " + "'"+kaizenId+"'" + " AND KHDM_STATUS = 'A' ";
		return sql;
	}
	public static String selectSql()
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  KZNM_KEYID,KZNM_FACTORYID,KZNM_SECTIONID,KZNM_CELLID,KZNM_MACHINEID,KZNM_ASSEMBLYID,");
		sql.append(" KZNM_PHENOMENAID,KZNM_CAUSEID,KZNM_STARTDATE,KZNM_ENDDATE,KZNM_THEME ");
		sql.append(" FROM KZN_TL_MST where KZNM_KEYID = ? ");
	
		return sql.toString();
	}
	
	public static String getHDMachineDtlsSql(String sectId,String mchId)
	{
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT  DISTINCT DECODE(MACID,NULL,0,1) AS TICK, ");
		sql.append(" SECT_KEYID,  SECT_NAME || ' - ['|| SECT_CODE || ']' ,");
		sql.append(" CELL_KEYID, CELL_NAME || ' - ['|| CELL_CODE || ']',");
		sql.append(" MCHM_KEYID, MCHM_MACHINENO AS EquipmentNo , MCHM_MACHINENAME AS Equipment ,");
		sql.append(" TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY'),KHDM_RESPONSIBILITYID,EMPM_NAME,'', DECODE(MACID,NULL,'','INSERT') AS MODES ");
		sql.append(" FROM GEN_TL_SECTIONMST, GEN_TL_CELLMST,GEN_TL_MACHINEMST ,GEN_TL_EMPLOYEEMST, ");
		sql.append(" (SELECT KHDM_MACHINEID AS MACID,KHDM_TARGETDATE,KHDM_RESPONSIBILITYID FROM KZN_TL_HDMST,GEN_TL_MACHINEMST WHERE KHDM_MACHINEID = MCHM_KEYID AND MCHM_ACTIVE = 'Y' ");
		sql.append(" AND KHDM_REFDOCNO = ? )");
		sql.append(" WHERE MCHM_CELLID = CELL_KEYID AND KHDM_RESPONSIBILITYID=EMPM_KEYID(+) AND SECT_KEYID = ? AND CELL_SECTIONID  = SECT_KEYID ");
		sql.append(" AND MCHM_ACTIVE = 'Y' AND MCHM_KEYID =MACID(+) ");
		sql.append(" AND MCHM_KEYID NOT IN ( ? ) ORDER BY SECT_NAME ");
		sql.append(" || ' - [' || SECT_CODE || ']',CELL_NAME || ' - ['|| CELL_CODE || ']',Equipment ");
		CommonMessage.debugMsg(" sql.toString() 1234 :: "+sql.toString());
		return sql.toString();
	}
	
	public static String getHDMachineDtlsdmtSql(String dmtid, String dmtlevel, String kaizenId, String cellId,String hdcellid) {
		// TODO Auto-generated method stub
		
		StringBuffer sql= new StringBuffer();
		
		CommonMessage.debugMsg(" dmtid "+dmtid+" kaizenId :: "+kaizenId+" cellId "+cellId);
		
		//sql.append(" SELECT DISTINCT DECODE(CELLID,NULL,0,1) AS TICK, ");
		sql.append(" SELECT DISTINCT DECODE(CELLID,NULL,0,1) AS TICK,KEYID AS HDKEYID, ");
		sql.append(" SECT_KEYID,SECT_NAME || ' - ['|| SECT_CODE || ']' AS SUBUNITNAME , ");
		sql.append(" CELL_KEYID, CELL_NAME || ' - [' || CELL_CODE || ']' AS CELL,");
		sql.append(" TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY'),KHDM_RESPONSIBILITYID,EMPM_NAME,'', DECODE(CELLID,NULL,'','INSERT') AS MODES,DECODE(HDSTATUS,'C','Completed','A','Pending'),'',CDATE AS CDATE ");          //  AND INSTR(PARENTFLIDS,'"+dmtid+"')>0)                                                                 
		sql.append(" FROM GEN_TL_SECTIONMST, GEN_TL_CELLMST ,GEN_TL_EMPLOYEEMST, ");
		sql.append(" (SELECT KHDM_KEYID AS KEYID,KHDM_CELLID AS CELLID,KHDM_TARGETDATE,KHDM_RESPONSIBILITYID,KHDM_CREATEDON AS CDATE,KHDM_STATUS as HDSTATUS FROM KZN_TL_HDMST,GEN_TL_CELLMST  WHERE KHDM_CELLID = CELL_KEYID AND CELL_ACTIVE ='Y' ");
		if(!hdcellid.equals(dmtid))
		{ 
         CommonMessage.debugMsg("The DmtId::::"+dmtid);
		 sql.append(" AND KHDM_REFDOCNO = '"+kaizenId+"' ) WHERE CELL_KEYID IN (select FNLN_ORIGINALID from gen_mv_flidhierarchy where fnln_elementtype IN ('L','C') AND INSTR(FLID || '\\' || PARENTFLIDS,'"+dmtid+"')>0 and FLID <> "+"'"+hdcellid+"'"+")");
		}
		else
		{
	       CommonMessage.debugMsg("The DmtId else::::"+dmtid);
			sql.append(" AND KHDM_REFDOCNO = '"+kaizenId+"' ) WHERE CELL_KEYID IN (select FNLN_ORIGINALID from gen_mv_flidhierarchy where fnln_elementtype IN ('L','C') AND INSTR(FLID || '\\' || PARENTFLIDS,'"+dmtid+"')>0 "+")");
		}
		sql.append(" AND CELL_SECTIONID = SECT_KEYID AND KHDM_RESPONSIBILITYID=EMPM_KEYID(+)  AND  CELL_ACTIVE  = 'Y' AND CELL_KEYID = CELLID(+) ");
		//sql.append(" AND CELL_KEYID    <> '"+cellId+"' ");
		sql.append(" ORDER BY SECT_NAME  || ' - ['  || SECT_CODE  || ']',  CELL_NAME  || ' - ['  || CELL_CODE|| ']' ");
		CommonMessage.debugMsg(" sql.toString() 5678 :: dmt "+sql.toString());
		return sql.toString();
		
	}
	
	public static String getHDCellDtlsSql(String sectId,String mchId)
	{
		StringBuffer sql= new StringBuffer();
		//sql.append(" SELECT DISTINCT DECODE(CELLID,NULL,0,1) AS TICK, ");
		sql.append(" SELECT DISTINCT DECODE(CELLID,NULL,0,1) AS TICK,KEYID AS HDKEYID, ");
		sql.append(" SECT_KEYID,SECT_NAME || ' - ['|| SECT_CODE || ']' AS SUBUNITNAME , ");
		sql.append(" CELL_KEYID, CELL_NAME || ' - [' || CELL_CODE || ']' AS CELL,");
		sql.append(" TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY'),KHDM_RESPONSIBILITYID,EMPM_NAME,'', DECODE(CELLID,NULL,'','INSERT') AS MODES, '' ");
		sql.append(" FROM GEN_TL_SECTIONMST, GEN_TL_CELLMST ,GEN_TL_EMPLOYEEMST, ");
		sql.append(" (SELECT KHDM_KEYID AS KEYID,KHDM_CELLID AS CELLID,KHDM_TARGETDATE,KHDM_RESPONSIBILITYID  FROM KZN_TL_HDMST,GEN_TL_CELLMST  WHERE KHDM_CELLID = CELL_KEYID AND CELL_ACTIVE ='Y' ");
		sql.append(" AND KHDM_REFDOCNO = ? ) WHERE SECT_KEYID = ? ");
		sql.append(" AND CELL_SECTIONID = SECT_KEYID AND KHDM_RESPONSIBILITYID=EMPM_KEYID(+)  AND  CELL_ACTIVE  = 'Y' AND CELL_KEYID = CELLID(+) ");
		//sql.append(" AND CELL_KEYID    <> ? ");
		sql.append(" ORDER BY SECT_NAME  || ' - ['  || SECT_CODE  || ']',  CELL_NAME  || ' - ['  || CELL_CODE|| ']' ");
		CommonMessage.debugMsg(" sql.toString() 5678 :: "+sql.toString());
		return sql.toString();
	}
	
	
	public static String getDeleteKznHDSql(TableFieldType [] fieldTypeArr, String khdmkeyIds)
	{
		String sql = "DELETE from " + TBL_KZN_TL_HDMST + " where  KHDM_KEYID IN ("+khdmkeyIds+") ";
		return sql;
	}
	public static String getUpdateHDMSTSql(TableFieldType [] fieldTypeArr, Object [] dataArray,String modifiedOn)
	{
		
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_HDMST, fieldTypeArr, dataArray);

		sql="UPDATE KZN_TL_HDMST SET KHDM_STATUS =  'C', "+
					fieldTypeArr[tableFldConstants.completedby.ordinal()].fieldName  +
			 " = '" +  (String)dataArray[ tableFldConstants.completedby.ordinal() ] + "' , " + 
			 fieldTypeArr[tableFldConstants.completeddate.ordinal()].fieldName  +
		 " = to_date( '" +  (String)dataArray[ tableFldConstants.completeddate.ordinal() ] + "' , 'DD-MON-YYYY hh24:mi:ss') , "+ 
		 fieldTypeArr[tableFldConstants.remarks.ordinal()].fieldName  +  
		 " = '" +  (String)dataArray[ tableFldConstants.remarks.ordinal() ] + "' , "+ 
		 fieldTypeArr[tableFldConstants.modifiedon.ordinal()].fieldName  +
		 " = to_date( '" +  modifiedOn + "', 'DD-MON-YYYY hh24:mi:ss')"; 
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
		
		String sql="SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID = '"+keyid+"'";
		return sql;
		
	}

	

}

