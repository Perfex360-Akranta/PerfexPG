package com.akranta.tpm.dao.sql;

public class SheTlIncidentmstSql {

	public static final String TBL_SHE_TL_INCIDENTMST = "SHE_TL_INCIDENTMST";  

	TableFieldType [] sincDbFields = null;

	public enum   tableFldConstants
	{
		keyid, locn_keyid, fact_keyid, sect_keyid, cell_keyid, mach_keyid
		, occurred_date, occurred_shift, reported_date, reported_shift
		, reported_by, department, division, area, accident_type, accident_desc
		, immediate_action, recommendations, noofemployees, status, exact_desc
		, exact_type, analysed_by, analysed_date, phenomena, whywhyflag
		, rootcause, countermeasure, rootcausedueto, idea, analysed_shift
		, yyno, lossproperty, directcost, tempfield5, tempfield6
		, tempfield7, tempfield8, tempfield9, tempfield10, tempfield11
		, tempfield12, tempfield13, tempfield14, tempfield15, tempfield16
		, tempfield17, tempfield18, tempfield19, tempfield20, elementid,flid,active
		, createdby, createdon, modifiedon
	}

	public TableFieldType[] getSincDbFields() {
		return sincDbFields;
	}

	public SheTlIncidentmstSql()
	{
		sincDbFields = new TableFieldType[ 56 ];
		for(int i = 0;i < 56; i++)
		{	
			sincDbFields[ i ] = new TableFieldType();
		}
		sincDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SINC_KEYID";
		sincDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.locn_keyid.ordinal() ].fieldName = "SINC_LOCN_KEYID";
		sincDbFields[ tableFldConstants.locn_keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "SINC_FACT_KEYID";
		sincDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.sect_keyid.ordinal() ].fieldName = "SINC_SECT_KEYID";
		sincDbFields[ tableFldConstants.sect_keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.cell_keyid.ordinal() ].fieldName = "SINC_CELL_KEYID";
		sincDbFields[ tableFldConstants.cell_keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.mach_keyid.ordinal() ].fieldName = "SINC_MACH_KEYID";
		sincDbFields[ tableFldConstants.mach_keyid.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldName = "SINC_OCCURRED_DATE";
		sincDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldType = 'D';

		sincDbFields[ tableFldConstants.occurred_shift.ordinal() ].fieldName = "SINC_OCCURRED_SHIFT";
		sincDbFields[ tableFldConstants.occurred_shift.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.reported_date.ordinal() ].fieldName = "SINC_REPORTED_DATE";
		sincDbFields[ tableFldConstants.reported_date.ordinal() ].fieldType = 'D';

		sincDbFields[ tableFldConstants.reported_shift.ordinal() ].fieldName = "SINC_REPORTED_SHIFT";
		sincDbFields[ tableFldConstants.reported_shift.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.reported_by.ordinal() ].fieldName = "SINC_REPORTED_BY";
		sincDbFields[ tableFldConstants.reported_by.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.department.ordinal() ].fieldName = "SINC_DEPARTMENT";
		sincDbFields[ tableFldConstants.department.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.division.ordinal() ].fieldName = "SINC_DIVISION";
		sincDbFields[ tableFldConstants.division.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.area.ordinal() ].fieldName = "SINC_AREA";
		sincDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.accident_type.ordinal() ].fieldName = "SINC_ACCIDENT_TYPE";
		sincDbFields[ tableFldConstants.accident_type.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.accident_desc.ordinal() ].fieldName = "SINC_ACCIDENT_DESC";
		sincDbFields[ tableFldConstants.accident_desc.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldName = "SINC_IMMEDIATE_ACTION";
		sincDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.recommendations.ordinal() ].fieldName = "SINC_RECOMMENDATIONS";
		sincDbFields[ tableFldConstants.recommendations.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.noofemployees.ordinal() ].fieldName = "SINC_NOOFEMPLOYEES";
		sincDbFields[ tableFldConstants.noofemployees.ordinal() ].fieldType = 'N';

		sincDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SINC_STATUS";
		sincDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		sincDbFields[ tableFldConstants.exact_desc.ordinal() ].fieldName = "SINC_EXACT_DESC";
		sincDbFields[ tableFldConstants.exact_desc.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.exact_type.ordinal() ].fieldName = "SINC_EXACT_TYPE";
		sincDbFields[ tableFldConstants.exact_type.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.analysed_by.ordinal() ].fieldName = "SINC_ANALYSED_BY";
		sincDbFields[ tableFldConstants.analysed_by.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.analysed_date.ordinal() ].fieldName = "SINC_ANALYSED_DATE";
		sincDbFields[ tableFldConstants.analysed_date.ordinal() ].fieldType = 'D';

		sincDbFields[ tableFldConstants.phenomena.ordinal() ].fieldName = "SINC_PHENOMENA";
		sincDbFields[ tableFldConstants.phenomena.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldName = "SINC_WHYWHYFLAG";
		sincDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldType = 'C';

		sincDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "SINC_ROOTCAUSE";
		sincDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "SINC_COUNTERMEASURE";
		sincDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.rootcausedueto.ordinal() ].fieldName = "SINC_ROOTCAUSEDUETO";
		sincDbFields[ tableFldConstants.rootcausedueto.ordinal() ].fieldType = 'C';

		sincDbFields[ tableFldConstants.idea.ordinal() ].fieldName = "SINC_IDEA";
		sincDbFields[ tableFldConstants.idea.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.analysed_shift.ordinal() ].fieldName = "SINC_ANALYSED_SHIFT";
		sincDbFields[ tableFldConstants.analysed_shift.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.yyno.ordinal() ].fieldName = "SINC_YYNO";
		sincDbFields[ tableFldConstants.yyno.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.lossproperty.ordinal() ].fieldName = "SINC_LOSSPROPERTY";
		sincDbFields[ tableFldConstants.lossproperty.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.directcost.ordinal() ].fieldName = "SINC_DIRECTCOST";
		sincDbFields[ tableFldConstants.directcost.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SINC_TEMPFIELD5";
		sincDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SINC_TEMPFIELD6";
		sincDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "SINC_TEMPFIELD7";
		sincDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "SINC_TEMPFIELD8";
		sincDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "SINC_TEMPFIELD9";
		sincDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "SINC_TEMPFIELD10";
		sincDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield11.ordinal() ].fieldName = "SINC_TEMPFIELD11";
		sincDbFields[ tableFldConstants.tempfield11.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield12.ordinal() ].fieldName = "SINC_TEMPFIELD12";
		sincDbFields[ tableFldConstants.tempfield12.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield13.ordinal() ].fieldName = "SINC_TEMPFIELD13";
		sincDbFields[ tableFldConstants.tempfield13.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield14.ordinal() ].fieldName = "SINC_TEMPFIELD14";
		sincDbFields[ tableFldConstants.tempfield14.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield15.ordinal() ].fieldName = "SINC_TEMPFIELD15";
		sincDbFields[ tableFldConstants.tempfield15.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield16.ordinal() ].fieldName = "SINC_TEMPFIELD16";
		sincDbFields[ tableFldConstants.tempfield16.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield17.ordinal() ].fieldName = "SINC_TEMPFIELD17";
		sincDbFields[ tableFldConstants.tempfield17.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield18.ordinal() ].fieldName = "SINC_TEMPFIELD18";
		sincDbFields[ tableFldConstants.tempfield18.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield19.ordinal() ].fieldName = "SINC_TEMPFIELD19";
		sincDbFields[ tableFldConstants.tempfield19.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.tempfield20.ordinal() ].fieldName = "SINC_TEMPFIELD20";
		sincDbFields[ tableFldConstants.tempfield20.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "SINC_ELEMENTID";
		sincDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';
		
		sincDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SINC_FLID";
		sincDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		sincDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SINC_ACTIVE";
		sincDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sincDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SINC_CREATEDBY";
		sincDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sincDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SINC_CREATEDON";
		sincDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sincDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SINC_MODIFIEDON";
		sincDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_INCIDENTMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_SHE_TL_INCIDENTMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_SHE_TL_INCIDENTMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static String selectIncident()
	{
		return "SELECT * from "+TBL_SHE_TL_INCIDENTMST+" where SINC_KEYID = ?";
	}
	public static String selectActionPlanStatus(String incidentNo)
	{
		return "SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST where APLM_MASTERREFID ='"+incidentNo+"'";
	}
	public static String selectwhywhyStatus(String incidentNo)
	{
		return "SELECT WWMS_STATUS FROM BDM_TL_WHYWHYMST where WWMS_REFDOCNO ='"+incidentNo+"'";
	}


	public static String checkStatus(String sincKeyid) {

		return "SELECT SINC_ANALYSED_BY from "+TBL_SHE_TL_INCIDENTMST+" where SINC_KEYID = '"+sincKeyid+"'";
	}


}

