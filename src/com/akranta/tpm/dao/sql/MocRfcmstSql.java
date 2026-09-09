package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.FieldObservationmstSql.tableFldConstants;

public class MocRfcmstSql {

	public static final String TBL_MOC_TL_RFCMST="MOC_TL_RFCMST";  

	TableFieldType [] rfcm_DbFields = null;

	public enum   tableFldConstants
	{
		keyid,kaizenid,suggestionid,flid,dmtid,jhid,date,empid,title,type,
		nature,detail,description,emergencyno,detailimg,descimg,status,
		tempfield1,tempfield2,tempfield3,tempfield4,tempfield5,tempfield6,tempfield7,tempfield8,tempfield9,tempfield10,
	active,createdby,createdon,modifiedon,modifiedby
	}

	public TableFieldType[] getrfcm_DbFields() {
		return rfcm_DbFields;
	}

	public MocRfcmstSql()
	{
		rfcm_DbFields = new TableFieldType[ 32 ];
		for(int i = 0;i < 32; i++)
		{	
			rfcm_DbFields[ i ] = new TableFieldType();
		}
		
		
		rfcm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "MOC_RFC_KEYID";
		rfcm_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.kaizenid.ordinal() ].fieldName = "MOC_RFC_KAIZENID";
		rfcm_DbFields[ tableFldConstants.kaizenid.ordinal() ].fieldType = 'V';
		
	    rfcm_DbFields[ tableFldConstants.suggestionid.ordinal() ].fieldName = "MOC_RFC_SUGGESTIONID";
		rfcm_DbFields[ tableFldConstants.suggestionid.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.flid.ordinal() ].fieldName = "MOC_RFC_FLID";
		rfcm_DbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.dmtid.ordinal() ].fieldName = "MOC_RFC_DMTID";
		rfcm_DbFields[ tableFldConstants.dmtid.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.jhid.ordinal() ].fieldName = "MOC_RFC_JHID";
		rfcm_DbFields[ tableFldConstants.jhid.ordinal() ].fieldType = 'V';     
		
		rfcm_DbFields[ tableFldConstants.date.ordinal() ].fieldName = "MOC_RFC_DATE";
		rfcm_DbFields[ tableFldConstants.date.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.empid.ordinal() ].fieldName = "MOC_RFC_EMPMKEYID";
		rfcm_DbFields[ tableFldConstants.empid.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.title.ordinal() ].fieldName = "MOC_RFC_TITLE";
		rfcm_DbFields[ tableFldConstants.title.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.type.ordinal() ].fieldName = "MOC_RFC_TYPE";
		rfcm_DbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';
		
	
		
		rfcm_DbFields[ tableFldConstants.nature.ordinal() ].fieldName = "MOC_RFC_NATURE";
		rfcm_DbFields[ tableFldConstants.nature.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.detail.ordinal() ].fieldName = "MOC_RFC_DETAIL";
		rfcm_DbFields[ tableFldConstants.detail.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.description.ordinal() ].fieldName = "MOC_RFC_DESCRIPTION";
		rfcm_DbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.emergencyno.ordinal() ].fieldName = "MOC_RFC_EMERGENCYNO";
		rfcm_DbFields[ tableFldConstants.emergencyno.ordinal() ].fieldType = 'V';     
		
		rfcm_DbFields[ tableFldConstants.detailimg.ordinal() ].fieldName = "MOC_RFC_DETAILIMG";
		rfcm_DbFields[ tableFldConstants.detailimg.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.descimg.ordinal() ].fieldName = "MOC_RFC_DESCIMG";
		rfcm_DbFields[ tableFldConstants.descimg.ordinal() ].fieldType = 'V';	
		
		rfcm_DbFields[ tableFldConstants.status.ordinal() ].fieldName = "MOC_RFC_STATUS";
		rfcm_DbFields[ tableFldConstants.status.ordinal() ].fieldType = 'V';
		
		
	   rfcm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD1";
		rfcm_DbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		rfcm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD2";
		rfcm_DbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD3";
		rfcm_DbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';
		
	
		
		rfcm_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD4";
		rfcm_DbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		rfcm_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD5";
		rfcm_DbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD6";
		rfcm_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';
		
	    rfcm_DbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD7";
		rfcm_DbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		rfcm_DbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD8";
		rfcm_DbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD9";
		rfcm_DbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';
		
		rfcm_DbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "MOC_RFC_TEMPFIELD10";
		rfcm_DbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';
		
		

		rfcm_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "MOC_RFC_ACTIVE";
		rfcm_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		rfcm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "MOC_RFC_CREATEDBY";
		rfcm_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		rfcm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "MOC_RFC_CREATEDON";
		rfcm_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		rfcm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MOC_RFC_MODIFIEDON ";
		rfcm_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		rfcm_DbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "MOC_TL_LASTMODIFIEDBY ";
		rfcm_DbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//System.out.println(SqlUtils.getInsertSql(TBL_MOC_TL_RFCMST, fieldTypeArr, dataArray));
		System.out.println("to be cont...IN SQL");
		return SqlUtils.getInsertSql(TBL_MOC_TL_RFCMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_RFCMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_MOC_TL_RFCMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public static String selectSqlUpdate(String nodeId) {
		// TODO Auto-generated method stub
		String sql = "select * from MOC_TL_RFCMST where MOC_RFC_KEYID='"+nodeId+"'";
		return sql;
	}

	public static String DeleteRfcByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_RFCMST;
	    sql += " WHERE MOC_RFC_KEYID = '" + keyid + "'";
	    return sql;
	}
	

}

