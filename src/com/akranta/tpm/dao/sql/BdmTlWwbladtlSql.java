package com.akranta.tpm.dao.sql;

public class BdmTlWwbladtlSql {

	public static final String TBL_BDM_TL_WWBLADTL = "BDM_TL_WWBLADTL";  

	TableFieldType [] wwbdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, wwbl_keyid, phenomena_factor, verification, parentid, orderno, levelno, islastfactor
		, countermeasure, skilltype, responsiblity, targetdate, status
		, active, createdby, createdon, modifiedon,reoccur,actiontaken,completedby,completedon,remarks
	}

	public TableFieldType[] getWwbdDbFields() {
		return wwbdDbFields;
	}

	public BdmTlWwbladtlSql()
	{
		wwbdDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			wwbdDbFields[ i ] = new TableFieldType();
		}
		wwbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "WWBD_KEYID";
		wwbdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.wwbl_keyid.ordinal() ].fieldName = "WWBD_WWBL_KEYID";
		wwbdDbFields[ tableFldConstants.wwbl_keyid.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.phenomena_factor.ordinal() ].fieldName = "WWBD_PHENOMENA_FACTOR";
		wwbdDbFields[ tableFldConstants.phenomena_factor.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.verification.ordinal() ].fieldName = "WWBD_VERIFICATION";
		wwbdDbFields[ tableFldConstants.verification.ordinal() ].fieldType = 'C';

		wwbdDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "WWBD_PARENTID";
		wwbdDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';
		
		wwbdDbFields[ tableFldConstants.orderno.ordinal() ].fieldName = "WWBD_ORDERNO";
		wwbdDbFields[ tableFldConstants.orderno.ordinal() ].fieldType = 'N';
		
		wwbdDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "WWBD_LEVELNO";
		wwbdDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		wwbdDbFields[ tableFldConstants.islastfactor.ordinal() ].fieldName = "WWBD_ISLASTFACTOR";
		wwbdDbFields[ tableFldConstants.islastfactor.ordinal() ].fieldType = 'C';

		wwbdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "WWBD_COUNTERMEASURE";
		wwbdDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.skilltype.ordinal() ].fieldName = "WWBD_SKILLTYPE";
		wwbdDbFields[ tableFldConstants.skilltype.ordinal() ].fieldType = 'C';
	  
		wwbdDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldName = "WWBD_RESPONSIBILITY";
		wwbdDbFields[ tableFldConstants.responsiblity.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "WWBD_TARGETDATE";
		wwbdDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		wwbdDbFields[ tableFldConstants.status.ordinal() ].fieldName = "WWBD_STATUS";
		wwbdDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "WWBD_ACTIVE";
		wwbdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		wwbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "WWBD_CREATEDBY";
		wwbdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "WWBD_CREATEDON";
		wwbdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		wwbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "WWBD_MODIFIEDON";
		wwbdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		wwbdDbFields[ tableFldConstants.reoccur.ordinal() ].fieldName = "WWBD_REOCCUR";
		wwbdDbFields[ tableFldConstants.reoccur.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "WWBD_ACTIONTAKEN";
		wwbdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "WWBD_COMPLETEDBY";
		wwbdDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		wwbdDbFields[ tableFldConstants.completedon.ordinal() ].fieldName = "WWBD_COMPLETEDDATE";
		wwbdDbFields[ tableFldConstants.completedon.ordinal() ].fieldType = 'D';

		wwbdDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "WWBD_REMARKS";
		wwbdDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

	
		
	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_WWBLADTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_WWBLADTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_WWBLADTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	/*
	 * public static String selectchildData(String dtlId) { // TODO Auto-generated
	 * method stub StringBuilder sql = new StringBuilder(); sql.
	 * append("  select wwbd_verification,wwbd_phenomena_factor,wwbd_islastfactor,wwbd_countermeasure,wwbd_responsibility,wwbd_reoccur,wwbd_status,to_char(wwbd_targetdate,'DD-Mon-YYYY') "
	 * ); sql.append("  from BDM_TL_WWBLADTL ");
	 * sql.append("  where wwbd_keyid='"+dtlId+"' ");
	 * 
	 * return sql.toString();
	 * 
	 * }
	 */
	public static String selectchildData(String dtlId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append("  select wwbd_verification,wwbd_phenomena_factor,wwbd_islastfactor,wwbd_countermeasure,wwbd_responsibility,wwbd_reoccur,wwbd_status, ");
		sql.append("  CASE WHEN to_char(wwbd_targetdate,'DD-Mon-YYYY') = '31-Dec-2100' ");
		sql.append("  THEN '-' ");
		sql.append("  ELSE to_char(wwbd_targetdate,'DD-Mon-YYYY') ");
		sql.append("  END as target_date ");
		sql.append("  from BDM_TL_WWBLADTL ");
		sql.append("  where wwbd_keyid='"+dtlId+"' ");

		return sql.toString();
	}

}

