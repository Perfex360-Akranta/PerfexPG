package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.bean.GridFilter;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.GenTlTeamDoucmentLink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class CommonFilterSqls {
	private static final String TBL_VIEW_GEN_VW_FACTORYLAYOUT = "GEN_VW_FACTORYLAYOUT";
	private static final String TBL_GEN_TL_EMPLOYEEMST ="GEN_TL_EMPLOYEEMST";
	private static final String TBL_GEN_TL_CELLMST = "GEN_TL_CELLMST"; 
	private static final String TBL_GEN_TL_SAP_FUNCTIONAL_LOCN = "GEN_TL_SAP_FUNCTIONAL_LOCN"; 
	
//	public static String addPaginationParams(String sql, GridParams gridParams) {
//
//		String condSql = FilterCondSql.makeGridFilterCond(gridParams
//				.getGridFilters());
//
//		StringBuilder fsql = new StringBuilder(
//				"SELECT  * from ( select ROWNUM as slno,a.* from ( select  * from ( ")
//				.append(sql).append(" )  where 1 = 1 ").append(condSql)
//				.append(" ) a ) ");
//		if (Integer.parseInt(gridParams.getToRow()) > 0) {
//			fsql.append(" where slno >=").append(gridParams.getFromRow())
//					.append(" and slno <= ").append(gridParams.getToRow());
//		}
//		return fsql.toString();
//	}
	
	// ---------------- Vignesh Changed - 17th October Menu Rights -CommonFilterSqls.java-----//
		public static String addPaginationParams(String sql, GridParams gridParams) {

			String condSql = FilterCondSql.makeGridFilterCond(gridParams
					.getGridFilters());

			StringBuilder fsql = new StringBuilder(
					"SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) as slno, a.* FROM (SELECT * FROM (")
					.append(sql).append(") WHERE 1 = 1 ").append(condSql)
					.append(") a) b");
			
			if (Integer.parseInt(gridParams.getToRow()) > 0) {
				fsql.append(" WHERE slno >= ").append(gridParams.getFromRow())
				.append(" AND slno <= ").append(gridParams.getToRow());
			}
			return fsql.toString();
		}
		// ---------------- Vignesh Changed - 17th October Menu Rights -CommonFilterSqls.java-----//


	public static String countSql(String innerSql, List<GridFilter> gridFilters) {
		String condSql = FilterCondSql.makeGridFilterCond(gridFilters);

		return new StringBuilder(" SELECT COUNT(*) FROM ( SELECT * from ( ")
				.append(innerSql).append(" ) WHERE 1 =  1 ").append(condSql)
				.append(')').toString();
	}

	
	public static String getCompanyComboSql()
	{
		return " SELECT DISTINCT COMP_KEYID id, TRIM(COMP_NAME) ||' - ' || TRIM(COMP_CODE) text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where comp_active = 'Y'  "; // and COMP_NAME || COMP_CODE like ? " ;
	}

	public static String getFactoryComboSql()
	{
		return " SELECT DISTINCT FACT_KEYID id, TRIM(FACT_NAME) ||' - ' || TRIM(FACT_CODE) text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where FACT_active = 'Y' "; //and FACT_NAME || FACT_CODE like ? " ;
	}
	
	public static String getSectionComboSql()
	{
		return " SELECT DISTINCT SECT_KEYID id, TRIM(SECT_NAME) ||' - ' || TRIM(SECT_CODE) text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where SECT_active = 'Y' "; //and SECT_NAME || SECT_CODE like ? " ;
	}
	
	public static String getFlidComboSql()
	{
		return " SELECT  FLID id , TRIM(FNLN_DESCRIPTION) || '-' || TRIM(FNLN_ELEMENTTYPE) text FROM GEN_MV_FLIDHIERARCHY ";
	}
	
	public static String getCellComboSql()
	{
		return " SELECT DISTINCT CELL_KEYID id, TRIM(CELL_CODE) ||' - ' || TRIM(CELL_NAME) text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where CELL_active = 'Y' "; // and CELL_NAME || CELL_CODE like ? " ;
	}
	
	public static String getMachineComboSql()
	{
	return " SELECT DISTINCT MCHM_KEYID id, TRIM(MCHM_MACHINENAME)||'-' || TRIM(MCHM_MACHINENO) text,1 as r from " + TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where MCHM_active = 'Y' AND MCHM_TYPE = 'MCH' "; // and MCHM_MACHINENO || MCHM_MACHINENAME like ? " ;
	}

	public static String getEmployeeComboSql()
	{
		return " SELECT DISTINCT EMPM_KEYID id, TRIM(EMPM_NAME)  ||'-' || TRIM(EMPM_CODE) text,1 as r from " +  TBL_GEN_TL_EMPLOYEEMST + " where EMPM_active = 'Y'  " ;
	}
	public static String getSectionComboSqlFilter()
	{
		return " SELECT DISTINCT SECT_KEYID id, TRIM(SECT_NAME) ||' - ' || TRIM(SECT_CODE) text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where 1=1 "; //and SECT_NAME || SECT_CODE like ? " ;
	     
	}
	
   public static String getCellComboSqlFilter()
	{ CommonMessage.debugMsg(" IN sid ethe cellCombo");
	
		return " SELECT DISTINCT CELL_KEYID id, CELL_CODE ||' - ' || CELL_NAME text from " +  TBL_VIEW_GEN_VW_FACTORYLAYOUT + " where 1=1 "; // and CELL_NAME || CELL_CODE like ? " ;
	}	
	public static String getMachineHirerachysql() {
		 CommonMessage.debugMsg(" IN sid ethe Machine Hirarachysql");
		 
		return "GEN_PC_COMMONFUNCTIONS.GEN_FN_GETMCHHIERARCHY";
		
	}
	
	public static String getCellHirerachysql() {
  CommonMessage.debugMsg(" IN sid ethe cellHirarachysql");
  
return "GEN_PC_MASTERS.GEN_FN_GETFACTSECTFROMCELL";
		
	}
	
	public static String getSectionHirerachysql() {

		String sql;
		sql=" SELECT SECT_COMPANYID, SECT_FACTORYID, FACT_LOCATIONID FROM " +TableNames.TBL_GEN_TL_SECTIONMST + "," + TableNames.TBL_GEN_TL_FACTORYMST + " where SECT_FACTORYID = FACT_KEYID AND SECT_KEYID =? ";
		return sql;
		
	}
	public static String getSubUnitHirerachysql() {

		String sql;
		sql=" SELECT CELL_KEYID, CELL_CODE,CELL_NAME FROM " +TableNames.TBL_GEN_TL_CELLMST + " where CELL_SECTIONID =? ";
		return sql;
		
	}

	public static String getfactoryHierarchysql() {
		String sql;
		sql=" SELECT FACT_COMPANYID,FACT_LOCATIONID  FROM " +TableNames.TBL_GEN_TL_FACTORYMST + " where FACT_KEYID =? ";
		return sql;
	}

	public static String getlocationHierarchysql() {
		String sql;
		sql=" SELECT LOCN_COMPANYID  FROM " +TableNames.TBL_GEN_TL_LOCATIONMST + " where LOCN_KEYID =? ";
		return sql;
	}

	public static String getCostCenterHierarchysql() {
		String sql;
		sql= "SELECT DISTINCT cell_KEYID id ,cell_CODE cell_NAME text  from"+ TBL_GEN_TL_CELLMST +" where 1 = 1 and  cell_costcentreid='CST/0001' order by text"; 
		return sql;
	}

	public static String getCityHierarchySql(){
		String sql;
		sql = "select STAM_NAME,CONM_NAME from gen_tl_statemst, gen_tl_countrymst, gen_tl_citymst where ctym_keyid = ?";
		return sql;
	}
	public static String getDesgIDsql(String empId) {
		// TODO Auto-generated method stub
		String sql = "select empm_designationid from "+ TableNames.TBL_GEN_TL_EMPLOYEEMST +" where empm_keyid ='"+empId+"'";
		CommonMessage.debugMsg("DesgID   :"+sql);
		return sql;
	}

	public static String getspokeIDsql(String progkeyId) {
		// TODO Auto-generated method stub
			
		String sql = "select PROG_SPOKE_KEYID from "+TableNames.TBL_ENT_TL_PROGRAMMST+" where PROG_KEYID = '"+progkeyId+"'";
		CommonMessage.debugMsg("spokeID   :"+sql);
		return sql;
	}

	//for team
	//if (plmTlGenmaintenance.getTeamList()==null)
	//	return ;
	
	public static void getTeamLinkSqls(List<GenTlTeamDoucmentLink> genTlTeamDoucmentLinkList,String docType,String docKeyid, List<String> sqls, DBActionTemplate dbActionTemplate
			) throws Exception {

		if (genTlTeamDoucmentLinkList==null)
			return;

		getTeamLinkQuery(genTlTeamDoucmentLinkList, docType, docKeyid, sqls, dbActionTemplate, null, null, null);
	}
	
	public static void getTeamLinkSqls(List<GenTlTeamDoucmentLink> genTlTeamDoucmentLinkList,String docType,String docKeyid, List<String> sqls, DBActionTemplate dbActionTemplate,
	List<Object[]> valueList,List<int[]> dataTypes ,List<String> charList	) throws Exception {
		
		if (genTlTeamDoucmentLinkList==null)
			return;
		
		getTeamLinkQuery(genTlTeamDoucmentLinkList, docType, docKeyid, sqls, dbActionTemplate, valueList, dataTypes, charList);
	}
	
	public static void getTeamLinkQuery(List<GenTlTeamDoucmentLink> genTlTeamDoucmentLinkList,String docType,String docKeyid, List<String> sqls, DBActionTemplate dbActionTemplate,
			List<Object[]> valueList,List<int[]> dataTypes ,List<String> charList	) throws Exception {
		
		CommonMessage.debugMsg("genTlTeamDoucmentLinkList.size()"+genTlTeamDoucmentLinkList.size());
		
		String deleteSql;
		String empType;
		

		if (genTlTeamDoucmentLinkList.size()>0) 
			empType= genTlTeamDoucmentLinkList.get(0).getTmdlEmptype();
		else
			empType="R";

		deleteSql = GenTlTeamDoucmentLinkSql.getDelete(docKeyid,empType);		
		sqls.add(deleteSql);
		
		CommonMessage.debugMsg("Team deleteSql = ="+deleteSql);
		
		if (valueList!=null && valueList.size()>0) {
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
		}

		if (genTlTeamDoucmentLinkList.size()==0) {
			return;
		}

		for(int i =0;i<genTlTeamDoucmentLinkList.size();i++)
		{
			String sql;
			GenTlTeamDoucmentLinkSql genTlTeamDoucmentLinkSql = new GenTlTeamDoucmentLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
			CommonMessage.debugMsg("Inside create sql = =");
			GenTlTeamDoucmentLink genTlTeamDoucmentLink = (GenTlTeamDoucmentLink)genTlTeamDoucmentLinkList.get(i);
			//CommonMessage.debugMsg(i+" : "+bdmTlSetupadjsplit.getSupsLossid() + "->"+bdmTlSetupadjsplit.getSupsDuration());
			String keyId = dbActionTemplate.getSequenceNumber("GEN_TL_TEAM_DOUCMENT_LINK", 15, "TMDL", "YYMM", "Y");
			genTlTeamDoucmentLink.setTmdlKeyid(keyId);
			
			genTlTeamDoucmentLink.setTmdlDocno(docKeyid);
			genTlTeamDoucmentLink.setTmdlDoctype(docType);
			
			genTlTeamDoucmentLink.setTmdlActive("Y");
			
			//GenTlTeamDoucmentLinkSql.getDeleteSql(fieldTypeArr, dataArray);
			sql= GenTlTeamDoucmentLinkSql.getInsertSql(genTlTeamDoucmentLinkSql.getTmdlDbFields(), genTlTeamDoucmentLink.getSaveArray());
			CommonMessage.debugMsg("team Sql="+sql);
			sqls.add(sql); // add insert sql for master table
			
			if (valueList!=null && valueList.size()>0) {
				valueList.add(null);
				dataTypes.add(null);
				charList.add("Q");
			}			
		}
		
	}

	
	public static String getHierarchysql(String keyid) {
		String sql;
		//sql=" SELECT FACT_COMPANYID,FACT_LOCATIONID  FROM " +TableNames.TBL_GEN_TL_FACTORYMST + " where FACT_KEYID =? ";
		sql=" SELECT FNLN_ELEMENTID FROM "+TableNames.TBL_GEN_TL_FUNCTIONALLOCN+" WHERE FNLN_ORIGINALID = '"+keyid+"'";
		CommonMessage.debugMsg(" SQL " + sql  );
		return sql;
	}

	public static String getSapLocationComboSql() {
		// TODO Auto-generated method stub
		return " SELECT DISTINCT GSFL_KEYID id, GSFL_FUNCTIONAL_LOCN text from " +  TBL_GEN_TL_SAP_FUNCTIONAL_LOCN + " where GSFL_active = 'Y'  "; // and COMP_NAME || COMP_CODE like ? " ;

	}


}
