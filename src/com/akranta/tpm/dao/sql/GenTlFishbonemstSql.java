package com.akranta.tpm.dao.sql;

public class GenTlFishbonemstSql {

	public static final String TBL_GEN_TL_FISHBONEMST = "GEN_TL_FISHBONEMST";  

	TableFieldType [] fismDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, refdocid, refdoctype, title, problem
		, revisionno, prepareddate, preparedby, approveddate, approvedby
		, status, defect, tempfield2, tempfield3, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFismDbFields() {
		return fismDbFields;
	}

	public GenTlFishbonemstSql()
	{
		fismDbFields = new TableFieldType[ 22 ];
		for(int i = 0;i < 22; i++)
		{	
			fismDbFields[ i ] = new TableFieldType();
		}
		fismDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FISM_KEYID";
		fismDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "FISM_FLID";
		fismDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "FISM_ELEMENTID";
		fismDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.refdocid.ordinal() ].fieldName = "FISM_REFDOCID";
		fismDbFields[ tableFldConstants.refdocid.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "FISM_REFDOCTYPE";
		fismDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'C';

		fismDbFields[ tableFldConstants.title.ordinal() ].fieldName = "FISM_TITLE";
		fismDbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.problem.ordinal() ].fieldName = "FISM_PROBLEM";
		fismDbFields[ tableFldConstants.problem.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.revisionno.ordinal() ].fieldName = "FISM_REVISIONNO";
		fismDbFields[ tableFldConstants.revisionno.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldName = "FISM_PREPAREDDATE";
		fismDbFields[ tableFldConstants.prepareddate.ordinal() ].fieldType = 'D';

		fismDbFields[ tableFldConstants.preparedby.ordinal() ].fieldName = "FISM_PREPAREDBY";
		fismDbFields[ tableFldConstants.preparedby.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.approveddate.ordinal() ].fieldName = "FISM_APPROVEDDATE";
		fismDbFields[ tableFldConstants.approveddate.ordinal() ].fieldType = 'D';

		fismDbFields[ tableFldConstants.approvedby.ordinal() ].fieldName = "FISM_APPROVEDBY";
		fismDbFields[ tableFldConstants.approvedby.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.status.ordinal() ].fieldName = "FISM_STATUS";
		fismDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		fismDbFields[ tableFldConstants.defect.ordinal() ].fieldName = "FISM_DEFECT";
		fismDbFields[ tableFldConstants.defect.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FISM_TEMPFIELD2";
		fismDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FISM_TEMPFIELD3";
		fismDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FISM_TEMPFIELD4";
		fismDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FISM_TEMPFIELD5";
		fismDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FISM_ACTIVE";
		fismDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fismDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FISM_CREATEDBY";
		fismDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fismDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FISM_CREATEDON";
		fismDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fismDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FISM_MODIFIEDON";
		fismDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_FISHBONEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_FISHBONEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_FISHBONEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String getFishdata() {
		// TODO Auto-generated method stub
		 
		 String  sql= "select * from GEN_TL_FISHBONEMST WHERE FISM_KEYID = ?";
		 
		 return sql;
	}

	public static String getUpdateWomstSql(String fBKeyid, String womsKey) {
		// TODO Auto-generated method stub
		StringBuffer sql= new StringBuffer();
		 sql.append("update wom_tl_workorder_mst set WOMS_FISHBONE_REFNO='");
		 sql.append(fBKeyid);
		 sql.append("' where woms_keyid='");
		 sql.append(womsKey);
		 sql.append("'");
		 return sql.toString();
	}

}

