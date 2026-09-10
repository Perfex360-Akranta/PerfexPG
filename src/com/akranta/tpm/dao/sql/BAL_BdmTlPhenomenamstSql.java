package com.akranta.tpm.dao.sql;

import java.util.List;

public class BAL_BdmTlPhenomenamstSql {

	public static final String TBL_BAL_BDM_TL_PHENOMENAMST = "BAL_BDM_TL_PHENOMENAMST";  

	TableFieldType [] bphmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, phenomenatype, phenomenaname, shortname, remarks, assemblyid
		, levelno, childflag, isphnnotdefined, causenotneeded,relatedto,tempfield1,tempfield2,tempfield3,active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getBphmDbFields() {
		return bphmDbFields;
	}

	public BAL_BdmTlPhenomenamstSql()
	{
		bphmDbFields = new TableFieldType[ 18 ];
		for(int i = 0;i < 18; i++)
		{	
			bphmDbFields[ i ] = new TableFieldType();
		}
		bphmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "BPHM_KEYID";
		bphmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.phenomenatype.ordinal() ].fieldName = "BPHM_PHENOMENATYPE";
		bphmDbFields[ tableFldConstants.phenomenatype.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.phenomenaname.ordinal() ].fieldName = "BPHM_PHENOMENANAME";
		bphmDbFields[ tableFldConstants.phenomenaname.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.shortname.ordinal() ].fieldName = "BPHM_SHORTNAME";
		bphmDbFields[ tableFldConstants.shortname.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "BPHM_REMARKS";
		bphmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldName = "BPHM_ASSEMBLYID";
		bphmDbFields[ tableFldConstants.assemblyid.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "BPHM_LEVELNO";
		bphmDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.childflag.ordinal() ].fieldName = "BPHM_CHILDFLAG";
		bphmDbFields[ tableFldConstants.childflag.ordinal() ].fieldType = 'C';

		bphmDbFields[ tableFldConstants.isphnnotdefined.ordinal() ].fieldName = "BPHM_ISPHNNOTDEFINED";
		bphmDbFields[ tableFldConstants.isphnnotdefined.ordinal() ].fieldType = 'C';

		bphmDbFields[ tableFldConstants.causenotneeded.ordinal() ].fieldName = "BPHM_CAUSENOTNEEDED";
		bphmDbFields[ tableFldConstants.causenotneeded.ordinal() ].fieldType = 'C';
		
		bphmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldName = "BPHM_RELATEDTO";
		bphmDbFields[ tableFldConstants.relatedto.ordinal() ].fieldType = 'V';
		
		bphmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "BPHM_TEMPFIELD1";
		bphmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';
		
		bphmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "BPHM_TEMPFIELD2";
		bphmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		bphmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "BPHM_TEMPFIELD3";
		bphmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		

		bphmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BPHM_ACTIVE";
		bphmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		bphmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "BPHM_CREATEDBY";
		bphmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		bphmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "BPHM_CREATEDON";
		bphmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		bphmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "BPHM_MODIFIEDON";
		bphmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BAL_BDM_TL_PHENOMENAMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BAL_BDM_TL_PHENOMENAMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BAL_BDM_TL_PHENOMENAMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
public static String getBphmKey() {	
		
		String sql = "SELECT BPHM_KEYID FROM BDM_TL_PHENOMENAMST, BAL_BDM_TL_PHNCAUSELINK ";
			   sql += " WHERE BPHM_PHENOMENANAME = ?  And BPCL_ORIGINALID = BPHM_KEYID AND INSTR(BPCL_PARENTID,?) > 0";
			   sql += " AND INSTR(BPCL_PARENTID,?) > 0";

		return sql;
	}
public static String getCauseKey(List<String > paramValues) {	
	
	String sql = "SELECT BCSM_KEYID FROM BAL_BDM_TL_CAUSEMST, BAL_BDM_TL_PHNCAUSELINK ";
		   sql += " WHERE BCSM_NAME = '"+paramValues.get(0)+"' AND BPCL_ORIGINALID = BCSM_KEYID";
		   sql += " AND INSTR(BPCL_PARENTID,'"+paramValues.get(1)+"') > 0 AND INSTR(BPCL_PARENTID,'"+paramValues.get(2)+"') > 0";
		   if(paramValues.get(3) != null)
		   sql += " AND INSTR(BPCL_PARENTID,'"+paramValues.get(3)+"') > 0";
	return sql;
}

public static String getUndefinedPhenSql() {	

	return "BDM_PC_BREAKDOWN.BDM_FN_FILLBREAKDOWN";
}

}

