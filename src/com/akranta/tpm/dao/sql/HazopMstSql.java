package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.CommonFilter;

//import sun.swing.UIAction;

public class HazopMstSql {

	public static final String TBL_MOC_TL_HAZOPMST= "MOC_TL_HAZOPMST";  

	TableFieldType [] hzomDbFields = null;

	public enum   tableFldConstants
	{
		keyid,mocmkeyid,kzbnkeyid,date,facility,team,pidno,node,
		designintent,flid ,directhazop,tempfield3, tempfield4, tempfield5,
		active, createdby, createdon, modifiedon
	}

	public TableFieldType[] gethzomDbFields() {
		return hzomDbFields;
	}

	public HazopMstSql()
	{
		hzomDbFields = new TableFieldType[ 18];
		for(int i = 0;i <18; i++)
		{	
			hzomDbFields[ i ] = new TableFieldType();
		}
		hzomDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "HZOM_KEYID";
		hzomDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		hzomDbFields[ tableFldConstants.mocmkeyid.ordinal() ].fieldName = "HZOM_MOCM_KEYID";
		hzomDbFields[ tableFldConstants.mocmkeyid.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldName = "HZOM_KZBN_KEYID";
		hzomDbFields[ tableFldConstants.kzbnkeyid.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.date.ordinal() ].fieldName = "HZOM_DATE";
		hzomDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';
		
		hzomDbFields[ tableFldConstants.facility.ordinal() ].fieldName = "HZOM_FACILITY";
		hzomDbFields[ tableFldConstants.facility.ordinal() ].fieldType = 'V';
		
		hzomDbFields[ tableFldConstants.team.ordinal() ].fieldName = "HZOM_TEAM";
		hzomDbFields[ tableFldConstants.team.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.pidno.ordinal() ].fieldName = "HZOM_PIDNO";
		hzomDbFields[ tableFldConstants.pidno.ordinal() ].fieldType = 'V';
		
		hzomDbFields[ tableFldConstants.node.ordinal() ].fieldName = "HZOM_NODE";
		hzomDbFields[ tableFldConstants.node.ordinal() ].fieldType = 'V';
		
		hzomDbFields[ tableFldConstants.designintent.ordinal() ].fieldName = "HZOM_DESIGNINTENT";
		hzomDbFields[ tableFldConstants.designintent.ordinal() ].fieldType = 'V';
		
		
		hzomDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "HZOM_FLID";
		hzomDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.directhazop.ordinal() ].fieldName = "HZOM_DIRECTHAZOP";
		hzomDbFields[ tableFldConstants.directhazop.ordinal() ].fieldType = 'C';

		hzomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "HZOM_TEMPFIELD3";
		hzomDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "HZOM_TEMPFIELD4";
		hzomDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "HZOM_TEMPFIELD5";
		hzomDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';
		
	

		hzomDbFields[ tableFldConstants.active.ordinal() ].fieldName = "HZOM_ACTIVE";
		hzomDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		hzomDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "HZOM_CREATEDBY";
		hzomDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		hzomDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "HZOM_CREATEDON";
		hzomDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		hzomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName ="HZOM_MODIFIEDON";
		hzomDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_MOC_TL_HAZOPMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_MOC_TL_HAZOPMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, String abndAbnormalityid)
	{
		String sql = "DELETE from " + TBL_MOC_TL_HAZOPMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '"+abndAbnormalityid+"'";
		return sql;
		}
	
	public static String getSelectSql()
	{
		String sql = "Select * from " + TBL_MOC_TL_HAZOPMST +" where HZOM_KEYID=?";
		return sql;
	}
	
	public static String DeleteHazopMstByMoc(String keyid) 
	{
		
		String sql = "DELETE FROM " + TBL_MOC_TL_HAZOPMST;
	    sql += " WHERE HZOM_MOCM_KEYID = '" + keyid + "'";
	    return sql;
	}
	
	
}

