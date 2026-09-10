package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;

public class BAL_BdmTlMachineruntimeSql {

	public static final String TBL_BDM_TL_MACHINERUNTIME = "BDM_TL_MACHINERUNTIME";  

	TableFieldType [] mcrhDbFields = null;

	public enum   tableFldConstants
	{
		keyid, factoryid, sectionid, cellid, machineid, runhrsvalue, effectivefrom
		, effectivetill, tempfield1, tempfield2, tempfield3, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getMcrhDbFields() {
		return mcrhDbFields;
	}

	public BAL_BdmTlMachineruntimeSql()
	{
		mcrhDbFields = new TableFieldType[ 15 ];
		for(int i = 0;i < 15; i++)
		{	
			mcrhDbFields[ i ] = new TableFieldType();
		}
		mcrhDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MCRH_KEYID";
		mcrhDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "MCRH_FACTORYID";
		mcrhDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "MCRH_SECTIONID";
		mcrhDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "MCRH_CELLID";
		mcrhDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MCRH_MACHINEID";
		mcrhDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.runhrsvalue.ordinal() ].fieldName = "MCRH_RUNHRSVALUE";
		mcrhDbFields[ tableFldConstants.runhrsvalue.ordinal() ].fieldType = 'N';

		mcrhDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldName = "MCRH_EFFECTIVEFROM";
		mcrhDbFields[ tableFldConstants.effectivefrom.ordinal() ].fieldType = 'D';

		mcrhDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldName = "MCRH_EFFECTIVETILL";
		mcrhDbFields[ tableFldConstants.effectivetill.ordinal() ].fieldType = 'D';

		mcrhDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MCRH_TEMPFIELD1";
		mcrhDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		mcrhDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MCRH_TEMPFIELD2";
		mcrhDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		mcrhDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MCRH_TEMPFIELD3";
		mcrhDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		mcrhDbFields[ tableFldConstants.active.ordinal() ].fieldName = "MCRH_ACTIVE";
		mcrhDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		mcrhDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MCRH_CREATEDBY";
		mcrhDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		mcrhDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MCRH_CREATEDON";
		mcrhDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		mcrhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MCRH_MODIFIEDON";
		mcrhDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_MACHINERUNTIME, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray, String mode)
	{
		String dateTime = CommonFunctions.dateTimeNow();
		String sql = "UPDATE "+ TBL_BDM_TL_MACHINERUNTIME+" SET " ;
		if(mode.equals("updateInsert"))
			sql+=" MCRH_EFFECTIVETILL='"+CommonFunctions.addDay((String)dataArray[ tableFldConstants.effectivefrom.ordinal() ] ,-1)+"', MCRH_ACTIVE='N'";
		else if(mode.equals("updateOnly"))
			sql+="MCRH_EFFECTIVETILL='"+(String)dataArray[ tableFldConstants.effectivefrom.ordinal() ] +"', MCRH_ACTIVE='Y'";
		sql+=" ,MCRH_RUNHRSVALUE= '"+(String)dataArray[ tableFldConstants.runhrsvalue.ordinal() ]+"'";
		sql+=" ,MCRH_MODIFIEDON= to_date( '"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')";
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName+
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}
                           
	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_MACHINERUNTIME ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getMchnRunGridSql(String keyId, String from, String to) {
		// TODO Auto-generated method stub
		String sql= "SELECT * FROM (SELECT ROWNUM AS slno, a.*FROM (SELECT * FROM (sELECT DECODE (mchm_keyid, mcrh_machineid, mcrh_keyid, '') as keyid,"+
				" DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as select1,DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as check1, fact_keyid AS factid,"+
				" sect_keyid AS sectid, cell_keyid AS cellid, mchm_keyid AS machid,mchm_machinename AS machine,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as effDate, mcrh_runhrsvalue as value1,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as refEffDate, mcrh_runhrsvalue as refValue1,MCRH_ACTIVE as active"+ 
				" FROM gen_tl_machinemst,gen_tl_cellmst,gen_tl_sectionmst,gen_tl_factorymst,bdm_tl_machineruntimE WHERE mchm_cellid = cell_keyid AND cell_sectionid = sect_keyid AND sect_factoryid = fact_keyid"+
				" AND mchm_keyid = mcrh_machineid(+) AND" ;
				if(keyId.contains("LIN"))
					sql+= " sect_keyid = '"+keyId+"' ";
				else if(keyId.contains("CEL"))
					sql+= " cell_keyid = '"+keyId+"' ";
				else if(keyId.contains("MCH"))
					sql+= " mchm_keyid = '"+keyId+"' ";
				sql+=" MINUS select  DECODE (mchm_keyid, mcrh_machineid, mcrh_keyid, '') as keyid,DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as select1,"+
				" DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as check1, fact_keyid AS factid,sect_keyid AS sectid, cell_keyid AS cellid, mchm_keyid AS machid,"+
				" mchm_machinename AS machine,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as effDate, mcrh_runhrsvalue as value1,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as refEffDate, mcrh_runhrsvalue as refValue1,MCRH_ACTIVE as active"+
				" FROM gen_tl_machinemst,gen_tl_cellmst,gen_tl_sectionmst,gen_tl_factorymst,bdm_tl_machineruntime WHERE mchm_cellid = cell_keyid"+
				" AND cell_sectionid = sect_keyid AND sect_factoryid = fact_keyid AND mchm_keyid = mcrh_machineid(+) AND ";
				if(keyId.contains("LIN"))
					sql+= " sect_keyid = '"+keyId+"' ";
				else if(keyId.contains("CEL"))
					sql+= " cell_keyid = '"+keyId+"' ";
				else if(keyId.contains("MCH"))
					sql+= " mchm_keyid = '"+keyId+"' ";
				sql+=" AND MCRH_ACTIVE ='N')WHERE 1 = 1) a)"+
				" WHERE slno >= "+from+" AND slno <="+to;
				CommonFunctions.debugMsg("sql:"+sql);
				return sql;
				
	}
	public String getInnerSqlMchnRun(String keyId)
	{
		String sql= "sELECT DECODE (mchm_keyid, mcrh_machineid, mcrh_keyid, '') as keyid,"+
				" DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as select1,DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as check1, fact_keyid AS factid,"+
				" sect_keyid AS sectid, cell_keyid AS cellid, mchm_keyid AS machid,mchm_machinename AS machine,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as effDate, mcrh_runhrsvalue as value1,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as refEffDate, mcrh_runhrsvalue as refValue1,MCRH_ACTIVE as active"+ 
				" FROM gen_tl_machinemst,gen_tl_cellmst,gen_tl_sectionmst,gen_tl_factorymst,bdm_tl_machineruntimE WHERE mchm_cellid = cell_keyid AND cell_sectionid = sect_keyid AND sect_factoryid = fact_keyid"+
				" AND mchm_keyid = mcrh_machineid(+) AND";
				if(keyId.contains("LIN"))
					sql+= " sect_keyid = '"+keyId+"' ";
				else if(keyId.contains("CEL"))
					sql+= " cell_keyid = '"+keyId+"' ";
				else if(keyId.contains("MCH"))
					sql+= " mchm_keyid = '"+keyId+"' ";
				sql+=" MINUS select  DECODE (mchm_keyid, mcrh_machineid, mcrh_keyid, '') as keyid,DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as select1,"+
				" DECODE (mchm_keyid, mcrh_machineid, 'N', 'Y') as check1, fact_keyid AS factid,sect_keyid AS sectid, cell_keyid AS cellid, mchm_keyid AS machid,"+
				" mchm_machinename AS machine,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as effDate, mcrh_runhrsvalue as value1,TO_CHAR (mcrh_effectivefrom, 'DD-MON-YYYY') as refEffDate, mcrh_runhrsvalue as refValue1,MCRH_ACTIVE as active"+
				" FROM gen_tl_machinemst,gen_tl_cellmst,gen_tl_sectionmst,gen_tl_factorymst,bdm_tl_machineruntime WHERE mchm_cellid = cell_keyid"+
				" AND cell_sectionid = sect_keyid AND sect_factoryid = fact_keyid AND mchm_keyid = mcrh_machineid(+) AND";
				if(keyId.contains("LIN"))
					sql+= " sect_keyid = '"+keyId+"' ";
				else if(keyId.contains("CEL"))
					sql+= " cell_keyid = '"+keyId+"' ";
				else if(keyId.contains("MCH"))
					sql+= " mchm_keyid = '"+keyId+"' ";
				sql+=" AND MCRH_ACTIVE ='N'";
				return sql;
	}
}

