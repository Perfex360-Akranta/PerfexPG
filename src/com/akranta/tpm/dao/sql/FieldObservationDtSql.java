package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class FieldObservationDtSql {

	public static final String TBL_UNSF_TL_FIELDOBSRVDTL = "UNSF_TL_FIELDOBSRVDTL";  

	TableFieldType [] FOBDDbFields = null;

	public enum   tableFldConstants
	{
		keyid,masterid, starttime, finishtime, detectedby, unsafeact
		, photo, descriptionids,badgetype,name, remarks,tempfield1,tempfield2, tempfield3, tempfield4
		, tempfield5 ,tempfield6,tempfield7, tempfield8, tempfield9
		, tempfield10, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFOBDDbFields() {
		return FOBDDbFields;
	}

	public FieldObservationDtSql()
	{
		FOBDDbFields = new TableFieldType[ 25 ];
		for(int i = 0;i <  25; i++)
		{	
			FOBDDbFields[ i ] = new TableFieldType();
		}
		FOBDDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "FOBD_KEYID";
		FOBDDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		FOBDDbFields[ tableFldConstants.masterid.ordinal() ].fieldName = "FOBD_FOBMKEYID";
		FOBDDbFields[ tableFldConstants.masterid.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.starttime.ordinal() ].fieldName = "FOBD_STARTTIME";
		FOBDDbFields[ tableFldConstants.starttime.ordinal() ].fieldType = 'D';

		FOBDDbFields[ tableFldConstants.finishtime.ordinal() ].fieldName = "FOBD_FINISHTIME";
		FOBDDbFields[ tableFldConstants.finishtime.ordinal() ].fieldType = 'D';

		FOBDDbFields[ tableFldConstants.detectedby.ordinal() ].fieldName = "FOBD_DETECTEDBY";
		FOBDDbFields[ tableFldConstants.detectedby.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.unsafeact.ordinal() ].fieldName = "FOBD_UNSAFEACT";
		FOBDDbFields[ tableFldConstants.unsafeact.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.photo.ordinal() ].fieldName = "FOBD_PHOTO";
		FOBDDbFields[ tableFldConstants.photo.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.descriptionids.ordinal() ].fieldName = "FOBD_DESCRIPTIONIDS";
		FOBDDbFields[ tableFldConstants.descriptionids.ordinal() ].fieldType = 'V';

	
		
		FOBDDbFields[ tableFldConstants.badgetype.ordinal() ].fieldName = "FOBD_BADGETYPE";
		FOBDDbFields[ tableFldConstants.badgetype.ordinal() ].fieldType = 'V';
		
		FOBDDbFields[ tableFldConstants.name.ordinal() ].fieldName = "FOBD_EMPKEYID";
		FOBDDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';
		
		FOBDDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "FOBD_REMARKS";
		FOBDDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

	

		FOBDDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "FOBD_TEMPFIELD1";
		FOBDDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "FOBD_TEMPFIELD2";
		FOBDDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "FOBD_TEMPFIELD3";
		FOBDDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "FOBD_TEMPFIELD4";
		FOBDDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "FOBD_TEMPFIELD5";
		FOBDDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
		FOBDDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "FOBD_TEMPFIELD6";
		FOBDDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "FOBD_TEMPFIELD7";
		FOBDDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "FOBD_TEMPFIELD8";
		FOBDDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "FOBD_TEMPFIELD9";
		FOBDDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "FOBD_TEMPFIELD10";
		FOBDDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FOBD_ACTIVE";
		FOBDDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		FOBDDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FOBD_CREATEDBY";
		FOBDDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		FOBDDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FOBD_CREATEDON";
		FOBDDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		FOBDDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FOBD_MODIFIEDON";
		FOBDDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_UNSF_TL_FIELDOBSRVDTL, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_UNSF_TL_FIELDOBSRVDTL, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_UNSF_TL_FIELDOBSRVDTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
	}

	public static String getInactiveSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = " UPDATE "+ TBL_UNSF_TL_FIELDOBSRVDTL+" SET ABND_ACTIVE = 'N' WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '"+abndAbnormalityid+"'";
		return sql;
	}

	public static String getMasterData(String dtlkeyId) {
		// TODO Auto-generated method stub
		return "SELECT * FROM UNSF_TL_FIELDOBSRVDTL WHERE FOBD_FOBMKEYID=? ";
	}
	
	public static String getselectsql() {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("SQL Fetch:");
		String sql;
		sql="SELECT * from " + TBL_UNSF_TL_FIELDOBSRVDTL + " where FOBD_FOBMKEYID= ?";
		CommonMessage.debugMsg("Sql:"+sql);
		return sql;
	}
	
	public static String FiledstrecalngGrid(String FOJHId,String FOdetcteddate,String FOshift,String FOdetectedby) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT FOBM_KEYID,FOBM_JHID,FOBM_LOGDATE,FOBM_SHIFTID,FOBD_DETECTEDBY,FOBD_BADGETYPE,FOBD_EMPKEYID,FOBD_UNSAFEACT,FOBD_REMARKS ");
		sql.append(" FROM UNSF_TL_FIELDOBSRVMST,UNSF_TL_FIELDOBSRVDTL WHERE FOBM_KEYID=FOBD_FOBMKEYID AND FOBM_JHID='"+FOJHId+"' AND FOBM_LOGDATE='"+FOdetcteddate+"'  AND FOBD_DETECTEDBY='"+FOdetectedby+"' ");
		sql.append(" AND FOBM_SHIFTID='"+FOshift+"' ");
		CommonMessage.debugMsg("SQL::::::"+sql.toString());

	    return sql.toString();
	}
}

