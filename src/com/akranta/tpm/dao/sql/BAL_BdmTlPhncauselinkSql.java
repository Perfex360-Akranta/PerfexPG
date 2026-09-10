package com.akranta.tpm.dao.sql;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.utils.CommonFunctions;

public class BAL_BdmTlPhncauselinkSql {

	public static final String TBL_BDM_TL_PHNCAUSELINK = "BDM_TL_PHNCAUSELINK";  

	TableFieldType [] bpclDbFields = null;

	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, elementtype, active
	}

	public TableFieldType[] getBpclDbFields() {
		return bpclDbFields;
	}

	public BAL_BdmTlPhncauselinkSql()
	{
		bpclDbFields = new TableFieldType[ 6 ];
		for(int i = 0;i < 6; i++)
		{	
			bpclDbFields[ i ] = new TableFieldType();
		}
		bpclDbFields[ tableFldConstants.originalid.ordinal() ].fieldName = "BPCL_ORIGINALID";
		bpclDbFields[ tableFldConstants.originalid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "BPCL_ELEMENTID";
		bpclDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "BPCL_PARENTID";
		bpclDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		bpclDbFields[ tableFldConstants.displaycode.ordinal() ].fieldName = "BPCL_DISPLAYCODE";
		bpclDbFields[ tableFldConstants.displaycode.ordinal() ].fieldType = 'V';

		bpclDbFields[ tableFldConstants.elementtype.ordinal() ].fieldName = "BPCL_ELEMENTTYPE";
		bpclDbFields[ tableFldConstants.elementtype.ordinal() ].fieldType = 'V';

		bpclDbFields[ tableFldConstants.active.ordinal() ].fieldName = "BPCL_ACTIVE";
		bpclDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_PHNCAUSELINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_PHNCAUSELINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_PHNCAUSELINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.originalid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.originalid.ordinal()] + "'";
		return sql;
	}
	public static String getPhencauseTreeSql(BAL_BdmTlPhncauselink bdmTlPhncauselink)
	{
		
		StringBuffer sb = new StringBuffer();
		sb.append("select PCT_ORGINALID,PCT_ELEMENTID,PCT_PARENTID,PCT_ELEMENTTYPE,PCT_DISPLAYCODE");
		sb.append(" from BDM_VW_PHNCASLAYOUT");
		if( bdmTlPhncauselink.getBpclElementid().equals("1"))
			sb.append(" where PCT_ELEMENTTYPE ='CMP' ");
		else
			sb.append(" where PCT_PARENTID ='"+bdmTlPhncauselink.getBpclElementid() +"' and PCT_ELEMENTTYPE != '" + bdmTlPhncauselink.getBpclElementtype() +"'");
			
		System.out.println("SQL : "+sb.toString());		
		return sb.toString();
		
	}
	
	public static String getParentElemSql(String elemId) {
		
		return "select distinct PCT_ORGINALID from BDM_VW_PHNCASLAYOUT where PCT_PARENTID ='" +elemId+"' and PCT_ORGINALID !='"+elemId+"'";
	}
	public static String getParentSql(String elemId) {
		
		return "select distinct PCT_PARENTID from BDM_VW_PHNCASLAYOUT where PCT_ELEMENTID ='" +elemId+"'";
	}
	
	public static String getChildSql(int size,String keyFlag,String start,String end,GridParams gridParams) {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("Size inside SQL : "+size);
		StringBuffer sb = new StringBuffer();
		if(CommonFunctions.isValidKeyId(start))
			sb.append(" select *  from  (");
		else
			sb.append(" select count(*)  from  (");
		sb.append("SELECT rownum AS slno, A.* FROM(");
		
		if(keyFlag.equals("ASM"))
			sb.append("select BPHM_KEYID,'' as temp1,'',BPHM_PHENOMENANAME AS TXTDISPLAYCODE,BPHM_PHENOMENATYPE AS TXTDESCRIPTION from BDM_TL_PHENOMENAMST"); 
		if(keyFlag.equals("PHM"))
			sb.append("select BCSM_KEYID,'' as temp1,'',BCSM_NAME AS TXTDISPLAYCODE,BCSM_CODE AS TXTDESCRIPTION from BDM_TL_CAUSEMST"); 	
		
		if(size > 0)
		{
			if(keyFlag.equals("ASM"))
				sb.append(" where BPHM_keyid");
			if(keyFlag.equals("PHM"))
				sb.append(" where BCSM_keyid");
			
			String sql = " not in (";
			for(int i=0;i<size;i++)
				sql+= "?,";
			
			sql = sql.substring(0, sql.length()-1)+")";
			sb.append(sql);
		}
		if(keyFlag.equals("PHM"))
			sb.append(" order by BCSM_NAME");
		if(keyFlag.equals("ASM"))
			sb.append(" order by BPHM_PHENOMENANAME");
		
		sb.append(")A");
		String condSql ="";
		if( gridParams != null)
		{
			condSql = FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
			sb.append(" WHERE 1=1 "+condSql);
		}
		sb.append(") where slno >= "+start+" and slno <= "+end);
		/*sb.append(")A )");
		if(CommonFunctions.isValidKeyId(start))
			sb.append(" where slno >= "+start+" and slno <= "+end);*/
		
		if(keyFlag.equals("PHM"))
			sb.append(" order by TXTDISPLAYCODE");
		if(keyFlag.equals("ASM"))
			sb.append(" order by TXTDISPLAYCODE");
		
		return sb.toString();
	}

	public static String getPhenmstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TableNames.TBL_BDM_TL_PHENOMENAMST + " where BPHM_KEYID = ?  ";
	}
	public static String getCausemstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TableNames.TBL_BDM_TL_CAUSEMST + " where BCSM_KEYID = ?  ";
	}

	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select PCT_PARENTID from BDM_VW_PHNCASLAYOUT where PCT_DISPLAYCODE like '%"+searchNode+"%'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and PCT_ORGINALID = '"+originalId+"'");
		return sb.toString();
	}
}

