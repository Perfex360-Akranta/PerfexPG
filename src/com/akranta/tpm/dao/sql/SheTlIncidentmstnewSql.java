package com.akranta.tpm.dao.sql;

public class SheTlIncidentmstnewSql {

	public static final String TBL_SHE_TL_INCIDENTMSTNEW = "SHE_TL_INCIDENTMSTNEW";  

	TableFieldType [] sinmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, elementid, flid, locn_keyid, fact_keyid, sect_keyid, cell_keyid
		, mach_keyid, department, division, area, accident_type, accident_desc
		, immediate_action, rptrecommendations, lossproperty, directcost
		, noofemployees, occurred_date, occurred_shift, reported_date
		, reported_shift, reported_by,witness1,witness2, exact_desc, exact_type, investigation,invstremarks
		, phenomena, whywhyflag, whywhyno, rootcause, countermeasure
		, rootcausedueto, idea, analysed_by, analysed_date, analysed_shift
		, invrptid, invrptby,invrptremarks, invrptdate, carecommended, responsibility
		, targetdate,responseremarks, actionplanno, status, reviseddate, actiontaken
		, completedby, datecompleted,completeremarks, verifiedstatus, verifiedby, dateverified,verifyremarks
		, approvalstatus, approvalby, approvaldate,approvalremarks,tempfield1, tempfield2
		, tempfield3, tempfield4, tempfield5, tempfield6, tempfield7
		, active
		, createdby, modifiedby, createdon, modifiedon
	}

	public TableFieldType[] getSinmDbFields() {
		return sinmDbFields;
	}

	public SheTlIncidentmstnewSql()
	{
		sinmDbFields = new TableFieldType[ 74];
		for(int i = 0;i < 74; i++)
		{	
			sinmDbFields[ i ] = new TableFieldType();
		}
		sinmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "SINM_KEYID";
		sinmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "SINM_ELEMENTID";
		sinmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "SINM_FLID";
		sinmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.locn_keyid.ordinal() ].fieldName = "SINM_LOCN_KEYID";
		sinmDbFields[ tableFldConstants.locn_keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldName = "SINM_FACT_KEYID";
		sinmDbFields[ tableFldConstants.fact_keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.sect_keyid.ordinal() ].fieldName = "SINM_SECT_KEYID";
		sinmDbFields[ tableFldConstants.sect_keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.cell_keyid.ordinal() ].fieldName = "SINM_CELL_KEYID";
		sinmDbFields[ tableFldConstants.cell_keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.mach_keyid.ordinal() ].fieldName = "SINM_MACH_KEYID";
		sinmDbFields[ tableFldConstants.mach_keyid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.department.ordinal() ].fieldName = "SINM_DEPARTMENT";
		sinmDbFields[ tableFldConstants.department.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.division.ordinal() ].fieldName = "SINM_DIVISION";
		sinmDbFields[ tableFldConstants.division.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.area.ordinal() ].fieldName = "SINM_AREA";
		sinmDbFields[ tableFldConstants.area.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.accident_type.ordinal() ].fieldName = "SINM_ACCIDENT_TYPE";
		sinmDbFields[ tableFldConstants.accident_type.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.accident_desc.ordinal() ].fieldName = "SINM_ACCIDENT_DESC";
		sinmDbFields[ tableFldConstants.accident_desc.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldName = "SINM_IMMEDIATE_ACTION";
		sinmDbFields[ tableFldConstants.immediate_action.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.rptrecommendations.ordinal() ].fieldName = "SINM_RPTRECOMMENDATIONS";
		sinmDbFields[ tableFldConstants.rptrecommendations.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.lossproperty.ordinal() ].fieldName = "SINM_LOSSPROPERTY";
		sinmDbFields[ tableFldConstants.lossproperty.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.directcost.ordinal() ].fieldName = "SINM_DIRECTCOST";
		sinmDbFields[ tableFldConstants.directcost.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.noofemployees.ordinal() ].fieldName = "SINM_NOOFEMPLOYEES";
		sinmDbFields[ tableFldConstants.noofemployees.ordinal() ].fieldType = 'N';

		sinmDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldName = "SINM_OCCURRED_DATE";
		sinmDbFields[ tableFldConstants.occurred_date.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.occurred_shift.ordinal() ].fieldName = "SINM_OCCURRED_SHIFT";
		sinmDbFields[ tableFldConstants.occurred_shift.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.reported_date.ordinal() ].fieldName = "SINM_REPORTED_DATE";
		sinmDbFields[ tableFldConstants.reported_date.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.reported_shift.ordinal() ].fieldName = "SINM_REPORTED_SHIFT";
		sinmDbFields[ tableFldConstants.reported_shift.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.reported_by.ordinal() ].fieldName = "SINM_REPORTED_BY";
		sinmDbFields[ tableFldConstants.reported_by.ordinal() ].fieldType = 'V';
		
		sinmDbFields[ tableFldConstants.witness1.ordinal() ].fieldName = "SINM_WITNESS1";
		sinmDbFields[ tableFldConstants.witness1.ordinal() ].fieldType = 'V';
		
		sinmDbFields[ tableFldConstants.witness2.ordinal() ].fieldName = "SINM_WITNESS2";
		sinmDbFields[ tableFldConstants.witness2.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.exact_desc.ordinal() ].fieldName = "SINM_EXACT_DESC";
		sinmDbFields[ tableFldConstants.exact_desc.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.exact_type.ordinal() ].fieldName = "SINM_EXACT_TYPE";
		sinmDbFields[ tableFldConstants.exact_type.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.investigation.ordinal() ].fieldName = "SINM_INVESTIGATION";
		sinmDbFields[ tableFldConstants.investigation.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.invstremarks.ordinal() ].fieldName = "SINM_INVESTRMRKS";
		sinmDbFields[ tableFldConstants.invstremarks.ordinal() ].fieldType = 'V';
		
		sinmDbFields[ tableFldConstants.phenomena.ordinal() ].fieldName = "SINM_PHENOMENA";
		sinmDbFields[ tableFldConstants.phenomena.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldName = "SINM_WHYWHYFLAG";
		sinmDbFields[ tableFldConstants.whywhyflag.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.whywhyno.ordinal() ].fieldName = "SINM_WHYWHYNO";
		sinmDbFields[ tableFldConstants.whywhyno.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldName = "SINM_ROOTCAUSE";
		sinmDbFields[ tableFldConstants.rootcause.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldName = "SINM_COUNTERMEASURE";
		sinmDbFields[ tableFldConstants.countermeasure.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.rootcausedueto.ordinal() ].fieldName = "SINM_ROOTCAUSEDUETO";
		sinmDbFields[ tableFldConstants.rootcausedueto.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.idea.ordinal() ].fieldName = "SINM_IDEA";
		sinmDbFields[ tableFldConstants.idea.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.analysed_by.ordinal() ].fieldName = "SINM_ANALYSED_BY";
		sinmDbFields[ tableFldConstants.analysed_by.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.analysed_date.ordinal() ].fieldName = "SINM_ANALYSED_DATE";
		sinmDbFields[ tableFldConstants.analysed_date.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.analysed_shift.ordinal() ].fieldName = "SINM_ANALYSED_SHIFT";
		sinmDbFields[ tableFldConstants.analysed_shift.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.invrptid.ordinal() ].fieldName = "SINM_INVRPTID";
		sinmDbFields[ tableFldConstants.invrptid.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.invrptby.ordinal() ].fieldName = "SINM_INVRPTBY";
		sinmDbFields[ tableFldConstants.invrptby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.invrptremarks.ordinal() ].fieldName = "SINM_INVESTRPTRMRKS";
		sinmDbFields[ tableFldConstants.invrptremarks.ordinal() ].fieldType = 'V';
		
		sinmDbFields[ tableFldConstants.invrptdate.ordinal() ].fieldName = "SINM_INVRPTDATE";
		sinmDbFields[ tableFldConstants.invrptdate.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.carecommended.ordinal() ].fieldName = "SINM_CARECOMMENDED";
		sinmDbFields[ tableFldConstants.carecommended.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "SINM_RESPONSIBILITY";
		sinmDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "SINM_TARGETDATE";
		sinmDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';
		
		sinmDbFields[ tableFldConstants.responseremarks.ordinal() ].fieldName = "SINM_RESPONSERMRKS";
		sinmDbFields[ tableFldConstants.responseremarks.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.actionplanno.ordinal() ].fieldName = "SINM_ACTIONPLANNO";
		sinmDbFields[ tableFldConstants.actionplanno.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.status.ordinal() ].fieldName = "SINM_STATUS";
		sinmDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.reviseddate.ordinal() ].fieldName = "SINM_REVISEDDATE";
		sinmDbFields[ tableFldConstants.reviseddate.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldName = "SINM_ACTIONTAKEN";
		sinmDbFields[ tableFldConstants.actiontaken.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "SINM_COMPLETEDBY";
		sinmDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.datecompleted.ordinal() ].fieldName = "SINM_DATECOMPLETED";
		sinmDbFields[ tableFldConstants.datecompleted.ordinal() ].fieldType = 'D';
		
		sinmDbFields[ tableFldConstants.completeremarks.ordinal() ].fieldName = "SINM_COMPLETERMRKS";
		sinmDbFields[ tableFldConstants.completeremarks.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldName = "SINM_VERIFIEDSTATUS";
		sinmDbFields[ tableFldConstants.verifiedstatus.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "SINM_VERIFIEDBY";
		sinmDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.dateverified.ordinal() ].fieldName = "SINM_DATEVERIFIED";
		sinmDbFields[ tableFldConstants.dateverified.ordinal() ].fieldType = 'D';
		
		sinmDbFields[ tableFldConstants.verifyremarks.ordinal() ].fieldName = "SINM_VERIFYRMRKS";
		sinmDbFields[ tableFldConstants.verifyremarks.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.approvalstatus.ordinal() ].fieldName = "SINM_APPROVALSTATUS";
		sinmDbFields[ tableFldConstants.approvalstatus.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.approvalby.ordinal() ].fieldName = "SINM_APPROVALBY";
		sinmDbFields[ tableFldConstants.approvalby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.approvaldate.ordinal() ].fieldName = "SINM_APPROVALDATE";
		sinmDbFields[ tableFldConstants.approvaldate.ordinal() ].fieldType = 'D';
		
		sinmDbFields[ tableFldConstants.approvalremarks.ordinal() ].fieldName = "SINM_APPROVALRMRKS";
		sinmDbFields[ tableFldConstants.approvalremarks.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "SINM_TEMPFIELD1";
		sinmDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "SINM_TEMPFIELD2";
		sinmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "SINM_TEMPFIELD3";
		sinmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "SINM_TEMPFIELD4";
		sinmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "SINM_TEMPFIELD5";
		sinmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "SINM_TEMPFIELD6";
		sinmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "SINM_TEMPFIELD7";
		sinmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		

		sinmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "SINM_ACTIVE";
		sinmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		sinmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "SINM_CREATEDBY";
		sinmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldName = "SINM_MODIFIEDBY";
		sinmDbFields[ tableFldConstants.modifiedby.ordinal() ].fieldType = 'V';

		sinmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "SINM_CREATEDON";
		sinmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		sinmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "SINM_MODIFIEDON";
		sinmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_SHE_TL_INCIDENTMSTNEW, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( SqlUtils.getUpdateSql(TBL_SHE_TL_INCIDENTMSTNEW, fieldTypeArr, dataArray));
		
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ) ; 
		sql.append(" = '"  ) ; 
		sql.append( (String)dataArray[ tableFldConstants.keyid.ordinal() ] );
		sql.append("'");
		return sql.toString();
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		StringBuilder sql = new StringBuilder( "DELETE from " );
		sql.append(TBL_SHE_TL_INCIDENTMSTNEW );
		sql.append( " where " );
		sql.append(fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName ); 
		sql.append( " = '" );
		sql.append((String)dataArray[ tableFldConstants.keyid.ordinal()] );
		sql.append(	"'");
		return sql.toString();
	}
	public static String selectActionPlanStatus(String incidentNo)
	{
		return "SELECT APLM_STATUS FROM GEN_TL_ACTIONPLANMST where APLM_MASTERREFID ='"+incidentNo+"'";
	}
	public static String selectIncident()
	{
		return "SELECT * from "+TBL_SHE_TL_INCIDENTMSTNEW+" where SINM_KEYID = ?";
	}

	public static String selectgetReleatedFileManager(String momKeyId) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select dmdm_filename,dmdm_path from DCM_TL_DOCUMENTMANAGER where dmdm_refdocno='"+momKeyId+"' ");
		
		System.out.println(" Checking FileManager Data "+sql.toString());
		return sql.toString();
	}
	
	public static StringBuilder getAllImageList(String keyid){
		
		StringBuilder sql=new StringBuilder();
		sql.append(" SELECT SINM_KEYID AS DocumentNo,IMFL_IMAGETYPE AS Imagetype,ImFL_FILEName AS Image");
		sql.append(" from SHE_TL_INCIDENTMSTNEW, gen_tl_allmoduleimgfile "); 
		sql.append(" WHERE SINM_KEYID = '"+keyid+"' and SINM_KEYID=IMFL_REFKEYID ");
		sql.append(" ORDER BY IMFL_MODIFIEDON ASC ");
		System.out.println("SQL...:"+sql.toString());
	    return sql;
	}

}

