package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class FishboneSql {

	public static final String TBL_FISHBONE = "FISHBONE";  

	TableFieldType [] fiboDbFields = null;

	public enum   tableFldConstants
	{
		no, name, parentid, levelno, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getFiboDbFields() {
		return fiboDbFields;
	}

	public FishboneSql()
	{
		fiboDbFields = new TableFieldType[ 8 ];
		for(int i = 0;i < 8; i++)
		{	
			fiboDbFields[ i ] = new TableFieldType();
		}
		fiboDbFields[ tableFldConstants.no.ordinal() ].fieldName = "FIBO_NO";
		fiboDbFields[ tableFldConstants.no.ordinal() ].fieldType = 'N';

		fiboDbFields[ tableFldConstants.name.ordinal() ].fieldName = "FIBO_NAME";
		fiboDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		fiboDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "FIBO_PARENTID";
		fiboDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		fiboDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "FIBO_LEVELNO";
		fiboDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		fiboDbFields[ tableFldConstants.active.ordinal() ].fieldName = "FIBO_ACTIVE";
		fiboDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		fiboDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "FIBO_CREATEDBY";
		fiboDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		fiboDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "FIBO_CREATEDON";
		fiboDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		fiboDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "FIBO_MODIFIEDON";
		fiboDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_FISHBONE, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_FISHBONE, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.no.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.no.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_FISHBONE ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.no.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.no.ordinal()] + "'";
		return sql;
	}
	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		//sb.append("select parentid from GEN_VW_FUNCLOCN where 1=1 ");
		sb.append("select CHILDPATH from vw_fishbone where 1=1 ");
		if(UIUtils.isValidKeyId(searchNode))
			sb.append(" and NAME = '"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and FISHBONENO = '"+originalId+"'");
		return sb.toString();
	}
}

