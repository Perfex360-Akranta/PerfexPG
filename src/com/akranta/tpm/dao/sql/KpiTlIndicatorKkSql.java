package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql.tableFldConstants;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KpiTlIndicatorKkSql {

	TableFieldType [] kinkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorname, indicatorcode, description, parentid, levelno
		, sortno, ischild, inputtype, inputentry, identifier, manualcalctype
		, uomid, frequency, excelname, dept_keyid, costarea, targetneed
		, pillarid, tempfield3, tempfield4, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10,location, active, createdby
		, createdon, modifiedon
	}

	public TableFieldType[] getKinkDbFields() {
		return kinkDbFields;
	}

	public KpiTlIndicatorKkSql()
	{
		kinkDbFields = new TableFieldType[ 32 ];
		for(int i = 0;i < 32; i++)
		{	
			kinkDbFields[ i ] = new TableFieldType();
		}
		kinkDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KINK_KEYID";
		kinkDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.indicatorname.ordinal() ].fieldName = "KINK_INDICATORNAME";
		kinkDbFields[ tableFldConstants.indicatorname.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.indicatorcode.ordinal() ].fieldName = "KINK_INDICATORCODE";
		kinkDbFields[ tableFldConstants.indicatorcode.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.description.ordinal() ].fieldName = "KINK_DESCRIPTION";
		kinkDbFields[ tableFldConstants.description.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.parentid.ordinal() ].fieldName = "KINK_PARENTID";
		kinkDbFields[ tableFldConstants.parentid.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.levelno.ordinal() ].fieldName = "KINK_LEVELNO";
		kinkDbFields[ tableFldConstants.levelno.ordinal() ].fieldType = 'N';

		kinkDbFields[ tableFldConstants.sortno.ordinal() ].fieldName = "KINK_SORTNO";
		kinkDbFields[ tableFldConstants.sortno.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.ischild.ordinal() ].fieldName = "KINK_ISCHILD";
		kinkDbFields[ tableFldConstants.ischild.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.inputtype.ordinal() ].fieldName = "KINK_INPUTTYPE";
		kinkDbFields[ tableFldConstants.inputtype.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.inputentry.ordinal() ].fieldName = "KINK_INPUTENTRY";
		kinkDbFields[ tableFldConstants.inputentry.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.identifier.ordinal() ].fieldName = "KINK_IDENTIFIER";
		kinkDbFields[ tableFldConstants.identifier.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.manualcalctype.ordinal() ].fieldName = "KINK_MANUALCALCTYPE";
		kinkDbFields[ tableFldConstants.manualcalctype.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.uomid.ordinal() ].fieldName = "KINK_UOMID";
		kinkDbFields[ tableFldConstants.uomid.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.frequency.ordinal() ].fieldName = "KINK_FREQUENCY";
		kinkDbFields[ tableFldConstants.frequency.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.excelname.ordinal() ].fieldName = "KINK_EXCELNAME";
		kinkDbFields[ tableFldConstants.excelname.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldName = "KINK_DEPT_KEYID";
		kinkDbFields[ tableFldConstants.dept_keyid.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.costarea.ordinal() ].fieldName = "KINK_COSTAREA";
		kinkDbFields[ tableFldConstants.costarea.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.targetneed.ordinal() ].fieldName = "KINK_TARGETNEED";
		kinkDbFields[ tableFldConstants.targetneed.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "KINK_PILLARID";
		kinkDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KINK_TYPE";
		kinkDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KINK_TEMPFIELD4";
		kinkDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KINK_TEMPFIELD5";
		kinkDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "KINK_TEMPFIELD6";
		kinkDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "KINK_TEMPFIELD7";
		kinkDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "KINK_TEMPFIELD8";
		kinkDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "KINK_TEMPFIELD9";
		kinkDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "KINK_TEMPFIELD10";
		kinkDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';
		
		kinkDbFields[ tableFldConstants.location.ordinal() ].fieldName = "KINK_LOCATION";
		kinkDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KINK_ACTIVE";
		kinkDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kinkDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KINK_CREATEDBY";
		kinkDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KINK_CREATEDON";
		kinkDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kinkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KINK_MODIFIEDON";
		kinkDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TableNames.TBL_KPI_TL_INDICATOR, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TableNames.TBL_KPI_TL_INDICATOR, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TableNames.TBL_KPI_TL_INDICATOR ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "Select * from " + TableNames.TBL_KPI_TL_INDICATOR ;
		
		sql += " where 1=1 " ;
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.keyid.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.indicatorname.ordinal()]))
				sql += " and " +  fieldTypeArr[tableFldConstants.indicatorname.ordinal()].fieldName  +
				" = '" +  (String)dataArray[ tableFldConstants.indicatorname.ordinal()] + "'";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.parentid.ordinal()]))
			sql += " and " +  fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "'";
		
		return sql;
	}
	
	public static String getSearchNodeSql(String searchNode,String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select CHILDPATH from kpi_vw_keyperInchildpath where 1=1 ");
		if(UIUtils.isValidKeyId(searchNode))
			sb.append(" and NAME = '"+searchNode+"'");
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and KEYID = '"+originalId+"'");
		return sb.toString();
	}	
	
	public static String getkeyIndLevelSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuffer sql= new StringBuffer();		
//		sql.append(" SELECT LEVEL ");		
//		sql.append(" FROM " + TableNames.TBL_KPI_TL_INDICATOR  );
//		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
//		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'");
//		sql.append(" START WITH KINK_KEYID = KINK_PARENTID   " );
//		sql.append(" CONNECT BY nocycle prior KINK_KEYID = KINK_PARENTID " );	
		
		//sriram 11-nov-2025
//		sql.append(" WITH RECURSIVE indicator_tree AS ( ");
//		sql.append("   SELECT KINK_KEYID, KINK_PARENTID, 1 AS LEVEL ");
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR );
//		sql.append("   WHERE KINK_KEYID = KINK_PARENTID "); // same as START WITH
//		sql.append("   UNION ALL ");
//		sql.append("   SELECT c.KINK_KEYID, c.KINK_PARENTID, p.LEVEL + 1 ");
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " c ");
//		sql.append("   JOIN indicator_tree p ON c.KINK_PARENTID = p.KINK_KEYID ");
//		sql.append("   WHERE c.KINK_KEYID <> c.KINK_PARENTID "); // NOCYCLE prevention
//		sql.append(" ) ");
//		sql.append(" SELECT LEVEL ");
//		sql.append(" FROM indicator_tree ");
//		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
//		            " = '" + (String)dataArray[tableFldConstants.keyid.ordinal()] + "';");
//		sql.append(" WITH RECURSIVE indicator_tree AS ( ");
//		sql.append("   SELECT KINK_KEYID, KINK_PARENTID,  AS LEVEL ");
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " ");
//		sql.append("   WHERE KINK_KEYID = KINK_PARENTID "); // ✅ start from root nodes
//
//		sql.append("   UNION ALL ");
//
//		sql.append("   SELECT c.KINK_KEYID, c.KINK_PARENTID, p.LEVEL + 1 ");
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " c ");
//		sql.append("   JOIN indicator_tree p ON c.KINK_PARENTID = p.KINK_KEYID ");
//		sql.append("   WHERE p.LEVEL < 10 "); // ✅ limit recursion to 10 levels
//
//		sql.append(" ) ");
//		sql.append(" SELECT LEVEL ");
//		sql.append(" FROM indicator_tree ");
//		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
//		            " = '" + (String)dataArray[tableFldConstants.keyid.ordinal()] + "' ");
//		sql.append(" LIMIT 1; ");
		
		
//		sql.append(" WITH RECURSIVE indicator_tree AS ( ");
//		sql.append("   SELECT KINK_KEYID, KINK_PARENTID, 1 AS LEVEL ");  // ✅ Added 1
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " ");
//		sql.append("   WHERE KINK_KEYID = KINK_PARENTID ");
//
//		sql.append("   UNION ALL ");
//
//		sql.append("   SELECT c.KINK_KEYID, c.KINK_PARENTID, p.LEVEL + 1 ");
//		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " c ");
//		sql.append("   JOIN indicator_tree p ON c.KINK_PARENTID = p.KINK_KEYID ");
//		sql.append("   WHERE c.KINK_KEYID <> c.KINK_PARENTID ");  // ✅ Added this line
//		sql.append("     AND p.LEVEL < 10 ");
//
//		sql.append(" ) ");
//		sql.append(" SELECT LEVEL ");
//		sql.append(" FROM indicator_tree ");
//		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName +
//		            " = '" + (String)dataArray[tableFldConstants.keyid.ordinal()] + "' ");
//		sql.append(" LIMIT 1; ");

		
		
		sql.append(" WITH RECURSIVE hierarchy AS ( ");
		sql.append("   SELECT 1 as LEVEL, KINK_KEYID, KINK_PARENTID ");
		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR);
		sql.append("   WHERE KINK_KEYID = KINK_PARENTID ");
		sql.append("   UNION ALL ");
		sql.append("   SELECT h.LEVEL + 1, t.KINK_KEYID, t.KINK_PARENTID ");
		sql.append("   FROM " + TableNames.TBL_KPI_TL_INDICATOR + " t ");
		sql.append("   INNER JOIN hierarchy h ON t.KINK_PARENTID = h.KINK_KEYID ");
		sql.append("   WHERE t.KINK_KEYID != t.KINK_PARENTID ");
		sql.append(" ) ");
		sql.append(" SELECT LEVEL FROM hierarchy ");
		sql.append(" WHERE " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName + 
		  " = '" + (String)dataArray[tableFldConstants.keyid.ordinal()] + "'");
		
		CommonMessage.debugMsg("getkeyIndLevelSql =====>=" + sql);
		

		return sql.toString();
	}
	
	public static String getSearchTopicLevelSql(String originalId) {		
		StringBuffer sb = new StringBuffer(); 
		sb.append("select  CHILDPATH,PARENTNAMES from kpi_vw_keyperInchildpath where 1=1 ");		
		if(UIUtils.isValidKeyId(originalId))
			sb.append(" and KEYID = '"+originalId+"'");
		return sb.toString();
	}
	
	public static String getConfigkeyIndLevel()
	{
		StringBuffer sql= new StringBuffer();		
		sql.append(" SELECT CNFM_SETTINGVALUE ");	
		sql.append(" FROM " + TableNames.TBL_ADM_TL_CONFIGURATIONMST  );
		
		//sriram 11-nov-2025 insted of sysdate i will change CURRENT_DATE
		sql.append( " WHERE CNFM_CODE='QTMQUALITYOFCOST' AND CURRENT_DATE  BETWEEN CNFM_FROMDATE AND CNFM_TILLDATE " );		
		return sql.toString();
	}
	
	public static String getPillarKeyIdSql(String code)
	{
		String sql = " SELECT TPMP_KEYID FROM " + TableNames.TBL_GEN_TL_TPMPILLARMST ;		
		sql += " WHERE TPMP_CODE='"+code+"' " ;
		return sql;
	}
	
	public static String getSortNo(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql="";
		String levelNo=(String)dataArray[ tableFldConstants.levelno.ordinal()];
		//CommonMessage.debugMsg("lEVELNO:" +(String)dataArray[ tableFldConstants.levelno.ordinal()]);
		if(levelNo.equals("1")){
			sql = " SELECT count(*)+1 as lastCount FROM " + TableNames.TBL_KPI_TL_INDICATOR ;		
			sql += " WHERE 1=1 " ;
			
			sql += " AND " +  fieldTypeArr[tableFldConstants.levelno.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.levelno.ordinal()] + "'";
			
			sql += " AND " +  fieldTypeArr[tableFldConstants.pillarid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.pillarid.ordinal()] + "'";
		}
		else{
			sql ="SELECT sortno||'.'||lastCount FROM ( ";
			sql +="(SELECT KINK_SORTNO sortno FROM " + TableNames.TBL_KPI_TL_INDICATOR + " WHERE 1=1 " ;
			sql += " AND " +  fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "' ))a,";					
			sql +="(SELECT count(*)+1 as lastCount FROM " + TableNames.TBL_KPI_TL_INDICATOR + " WHERE 1=1 AND KINK_PARENTID<>KINK_KEYID " ;
			
			sql += " AND " +  fieldTypeArr[tableFldConstants.parentid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "' )b";
		}
		return sql;
	}
}

