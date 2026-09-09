package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.KpiTlIndicatorKkSql.tableFldConstants;

public class KpiTlIndicatorSql {

	public static final String TBL_KPI_TL_INDICATOR = "KPI_TL_INDICATOR";  

	TableFieldType [] kinkDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorname, indicatorcode, description, parentid, levelno
		, sortno, ischild, inputtype, inputentry, identifier, manualcalctype
		, uomid, frequency, excelname, dept_keyid, costarea, targetneed
		, pillarid, type, impactarea, goals, sourceofkpi, kpireason, annualtarget
		, startdate, enddate,location, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKinkDbFields() {
		return kinkDbFields;
	}

	public KpiTlIndicatorSql()
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

		kinkDbFields[ tableFldConstants.type.ordinal() ].fieldName = "KINK_TYPE";
		kinkDbFields[ tableFldConstants.type.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.impactarea.ordinal() ].fieldName = "KINK_IMPACTAREA";
		kinkDbFields[ tableFldConstants.impactarea.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.goals.ordinal() ].fieldName = "KINK_GOALS";
		kinkDbFields[ tableFldConstants.goals.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.sourceofkpi.ordinal() ].fieldName = "KINK_SOURCEOFKPI";
		kinkDbFields[ tableFldConstants.sourceofkpi.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.kpireason.ordinal() ].fieldName = "KINK_KPIREASON";
		kinkDbFields[ tableFldConstants.kpireason.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.annualtarget.ordinal() ].fieldName = "KINK_ANNUALTARGET";
		kinkDbFields[ tableFldConstants.annualtarget.ordinal() ].fieldType = 'V';

		kinkDbFields[ tableFldConstants.startdate.ordinal() ].fieldName = "KINK_STARTDATE";
		kinkDbFields[ tableFldConstants.startdate.ordinal() ].fieldType = 'D';

		kinkDbFields[ tableFldConstants.enddate.ordinal() ].fieldName = "KINK_ENDDATE";
		kinkDbFields[ tableFldConstants.enddate.ordinal() ].fieldType = 'D';
		
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
		return SqlUtils.getInsertSql(TBL_KPI_TL_INDICATOR, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KPI_TL_INDICATOR, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KPI_TL_INDICATOR ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getSelectSql(TableFieldType [] fieldTypeArr, Object [] dataArray) {
		
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
			
			/*sql += " AND " +  fieldTypeArr[tableFldConstants.pillarid.ordinal()].fieldName  +
			" = '" +  (String)dataArray[ tableFldConstants.pillarid.ordinal()] + "'";*/
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

	public String getLocation(String flId) {
		// TODO Auto-generated method stub
		String sql="";
		sql += " select LOCN_KEYID from gen_vw_fnln where FNLN_KEYID='"+flId+"'";
		return sql;
	}

	public String getParentSortNo(TableFieldType [] fieldTypeArr, Object [] dataArray){
		// TODO Auto-generated method stub
		String sql="";
		sql = " SELECT KINK_SORTNO FROM " + TableNames.TBL_KPI_TL_INDICATOR ;		
		sql += " WHERE 1=1 " ;		
		sql += " AND " +  fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		" = '" +  (String)dataArray[ tableFldConstants.parentid.ordinal()] + "' ";
		return sql;
	}

}

