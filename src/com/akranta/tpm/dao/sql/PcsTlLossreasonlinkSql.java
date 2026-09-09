package com.akranta.tpm.dao.sql;

import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class PcsTlLossreasonlinkSql {

	public static final String TBL_PCS_TL_LOSSREASONLINK = "PCS_TL_LOSSREASONLINK";  

	TableFieldType [] plrkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, lossid, reasonid, causeid, rootcauseid, pldetailid, wno
		, minutes, instance, date, shiftid, hourno, factoryid, sectionid
		, cellid, machineid, subgroupid, remarks, msrno, processid
		, flid, elementid, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPlrkDbFields() {
		return plrkDbFields;
	}

	public PcsTlLossreasonlinkSql()
	{
		plrkDbFields = new TableFieldType[ 26 ];
		for(int i = 0;i < 26; i++)
		{	
			plrkDbFields[ i ] = new TableFieldType();
		}
		plrkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "PLRK_KEYID";
		plrkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.lossid.ordinal() ].fieldName = "PLRK_LOSSID";
		plrkDbFields[ tableFldConstants.lossid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.reasonid.ordinal() ].fieldName = "PLRK_REASONID";
		plrkDbFields[ tableFldConstants.reasonid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.causeid.ordinal() ].fieldName = "PLRK_CAUSEID";
		plrkDbFields[ tableFldConstants.causeid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldName = "PLRK_ROOTCAUSEID";
		plrkDbFields[ tableFldConstants.rootcauseid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldName = "PLRK_PLDETAILID";
		plrkDbFields[ tableFldConstants.pldetailid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.wno.ordinal() ].fieldName = "PLRK_WNO";
		plrkDbFields[ tableFldConstants.wno.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.minutes.ordinal() ].fieldName = "PLRK_MINUTES";
		plrkDbFields[ tableFldConstants.minutes.ordinal() ].fieldType = 'N';

		plrkDbFields[ tableFldConstants.instance.ordinal() ].fieldName = "PLRK_INSTANCE";
		plrkDbFields[ tableFldConstants.instance.ordinal() ].fieldType = 'N';

		plrkDbFields[ tableFldConstants.date.ordinal() ].fieldName = "PLRK_DATE";
		plrkDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		plrkDbFields[ tableFldConstants.shiftid.ordinal() ].fieldName = "PLRK_SHIFTID";
		plrkDbFields[ tableFldConstants.shiftid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.hourno.ordinal() ].fieldName = "PLRK_HOURNO";
		plrkDbFields[ tableFldConstants.hourno.ordinal() ].fieldType = 'N';

		plrkDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "PLRK_FACTORYID";
		plrkDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "PLRK_SECTIONID";
		plrkDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "PLRK_CELLID";
		plrkDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "PLRK_MACHINEID";
		plrkDbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.subgroupid.ordinal() ].fieldName = "PLRK_SUBGROUPID";
		plrkDbFields[ tableFldConstants.subgroupid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "PLRK_REMARKS";
		plrkDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.msrno.ordinal() ].fieldName = "PLRK_MSRNO";
		plrkDbFields[ tableFldConstants.msrno.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.processid.ordinal() ].fieldName = "PLRK_PROCESSID";
		plrkDbFields[ tableFldConstants.processid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "PLRK_FLID";
		plrkDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "PLRK_ELEMENTID";
		plrkDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "PLRK_ACTIVE";
		plrkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		plrkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "PLRK_CREATEDBY";
		plrkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		plrkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "PLRK_CREATEDON";
		plrkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		plrkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "PLRK_MODIFIEDON";
		plrkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_LOSSREASONLINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_LOSSREASONLINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_LOSSREASONLINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String checkLossExistSql(PcsTlLossreasonlink pcsTlLossreasonlink)
	{
		String sql = "select count(*) from "+ TBL_PCS_TL_LOSSREASONLINK;
			   sql += " where plrk_lossid='"+pcsTlLossreasonlink.getPlrkLossid()+"'";
			   sql += " and plrk_reasonid='"+pcsTlLossreasonlink.getPlrkReasonid()+"'";
			   sql += " and plrk_causeid = '"+pcsTlLossreasonlink.getPlrkCauseid()+"'";
			   sql += " AND PLRK_ROOTCAUSEID='"+pcsTlLossreasonlink.getPlrkRootcauseid()+"'";
			   sql += " AND PLRK_PLDETAILID='"+pcsTlLossreasonlink.getPlrkPldetailid()+"'";
			   sql += " AND PLRK_KEYID<>'"+pcsTlLossreasonlink.getPlrkKeyid()+"'";
		CommonMessage.debugMsg("sql : "+sql);
		return sql;
	}
	public static String checkLossExistSql(String lossId,String plDetailId)
	{
		String sql = "select count(*) from "+ TBL_PCS_TL_LOSSREASONLINK;
		   	   sql += " where plrk_lossid='"+lossId+"'";
		   	   sql += " AND PLRK_PLDETAILID='"+plDetailId+"'";
		return sql;	
	}
	public static String getLossRelatedSql(String lossId,String plDetailId)
	{
		String sql = "select PLRK_KEYID,PLRK_REASONID,PLRK_CAUSEID,PLRK_ROOTCAUSEID,";
			   sql += "PLRK_MINUTES,PLRK_INSTANCE,PLRK_REMARKS from "+ TBL_PCS_TL_LOSSREASONLINK;
		   	   sql += " where plrk_lossid='"+lossId+"'";
		   	   sql += " AND PLRK_PLDETAILID='"+plDetailId+"'";
		return sql;	
	}

	public String checkExistId(String plrkCauseid) {		 
		 //String sql =" SELECT COUNT(*) FROM "+TableNames.TBL_QTM_TL_LAYOUT +" WHERE QLYT_PARENTID = '"+plrkCauseid+"'";
		 String sql =" SELECT COUNT(*) FROM "+TableNames.TBL_PCS_TL_LOSSCAUSEMST +" WHERE PLCS_KEYID = '"+plrkCauseid+"'";
		return sql;
	}

	public String pcsCauseCount(String plrkReasonid) {
		 String sql = " SELECT COUNT(*) FROM "+TableNames.TBL_PCS_TL_LOSSCAUSEMST+" WHERE PLCS_KEYID = '"+plrkReasonid+"'";
		 return sql;
	}

}

