package com.akranta.tpm.dao.sql;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlUpstreamdefectSql {

	public static final String TBL_GEN_TL_UPSTREAMDEFECT = "GEN_TL_UPSTREAMDEFECT";  

	TableFieldType [] upsdDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, area, date, inspectionlotno, informto
		, rawmaterial, defect, correctionaction, preventiveaction, actiontaken
		, tempfield2, tempfield3, tempfield4, tempfield5, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getUpsdDbFields() {
		return upsdDbFields;
	}

	public GenTlUpstreamdefectSql()
	{
		upsdDbFields = new TableFieldType[ 20 ];
		for(int i = 0;i < 20; i++)
		{	
			upsdDbFields[ i ] = new TableFieldType();
		}
		upsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "UPSD_KEYID";
		upsdDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "UPSD_FLID";
		upsdDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "UPSD_ELEMENTID";
		upsdDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.area.ordinal() ].fieldName = "UPSD_AREA";
		upsdDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.date.ordinal() ].fieldName = "UPSD_DATE";
		upsdDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		upsdDbFields[ tableFldConstants.inspectionlotno.ordinal() ].fieldName = "UPSD_INSPECTIONLOTNO";
		upsdDbFields[ tableFldConstants.inspectionlotno.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.informto.ordinal() ].fieldName = "UPSD_INFORMTO";
		upsdDbFields[ tableFldConstants.informto.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.rawmaterial.ordinal() ].fieldName = "UPSD_RAWMATERIAL";
		upsdDbFields[ tableFldConstants.rawmaterial.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.defect.ordinal() ].fieldName = "UPSD_DEFECT";
		upsdDbFields[ tableFldConstants.defect.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.correctionaction.ordinal() ].fieldName = "UPSD_CORRECTIONACTION";
		upsdDbFields[ tableFldConstants.correctionaction.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldName = "UPSD_PREVENTIVEACTION";
		upsdDbFields[ tableFldConstants.preventiveaction.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "USWP_ACTIONTAKEN";
		upsdDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "UPSD_TEMPFIELD2";
		upsdDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "UPSD_TEMPFIELD3";
		upsdDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "UPSD_TEMPFIELD4";
		upsdDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "UPSD_TEMPFIELD5";
		upsdDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.active.ordinal() ].fieldName = "UPSD_ACTIVE";
		upsdDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		upsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "UPSD_CREATEDBY";
		upsdDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		upsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "UPSD_CREATEDON";
		upsdDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		upsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "UPSD_MODIFIEDON";
		upsdDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_UPSTREAMDEFECT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_UPSTREAMDEFECT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
	
		String sql = "DELETE from " + TBL_GEN_TL_UPSTREAMDEFECT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	
	}

	public static String getupstreamdata() {
		// TODO Auto-generated method stub
	
		CommonMessage.debugMsg(" Inside Sql Files :: ");
		String  sql= "select * from GEN_TL_UPSTREAMDEFECT WHERE UPSD_KEYID = ?";
		return sql;
	
	}

	public static String selectData(String keyid) {
		// TODO Auto-generated method stub
	
		String sql= " select UPSD_RAWMATERIAL,UPSD_DEFECT,UPSD_INFORMTO,UPSD_CORRECTIONACTION, ";
		sql+= " UPSD_PREVENTIVEACTION,UPSM_DATE,UPSD_KEYID  ";
		sql+= " from GEN_TL_UPSTREAMDEFECT_DET,GEN_TL_UPSTREAMDEFECT_MST " ;
		sql+= " where UPSD_UPSM_KEYID=UPSM_KEYID and  UPSD_KEYID='"+keyid+"'";

		return sql;
	
	}

	public static String selectDataMst(String Keyid) {
		// TODO Auto-generated method stub
		 String  sql= "select * from GEN_TL_UPSTREAMDEFECT_MST WHERE UPSM_KEYID = ?";
			return sql;
	}

}

