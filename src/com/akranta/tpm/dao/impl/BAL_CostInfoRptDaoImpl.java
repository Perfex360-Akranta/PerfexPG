package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.dao.BAL_CostInfoRptDao;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.BdmServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;


public class BAL_CostInfoRptDaoImpl implements BAL_CostInfoRptDao {
	
private DBActionTemplate dbActionTemplate; 
private BdmServiceApi bdmServiceApi;
	FunctionCallApi fnCallApi;
	
	public BAL_CostInfoRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	//mano
	  public void BAL_CostInfoRptDaoImplJwt(String JwtToken) 
		{
			try{
				bdmServiceApi = new BdmServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	/*
	 * public List<String[]> getGridSummaryQuery(String woId) throws Exception { try
	 * { String sql = ""; StringBuffer buffer = new StringBuffer();
	 * buffer.append(""); buffer.append
	 * (" SELECT DECODE(SLNO,null,7,SLNO) AS SLNO, DECODE(PARAM,null,'Total',PARAM) AS PARAM, "
	 * ); buffer.append
	 * (" SUM(EST_VAL) AS EST_VAL, SUM(ACT_VAL) AS ACT_VAL FROM ( ");
	 * 
	 * buffer.append
	 * (" SELECT DECODE(MPCP_SKILLFLAG,'E',1,2) AS SLNO, DECODE(MPCP_SKILLFLAG,'E','Employee Cost','H','Contractor Cost') as PARAM, "
	 * ); buffer.append (" MPCP_TOTALVALUE AS EST_VAL, 0 AS ACT_VAL ");
	 * buffer.append (" FROM WOM_TL_MANPOWERCOSTPLAN   WHERE MPCP_WOID='" + woId +
	 * "' AND MPCP_SKILLFLAG IN ('E','H') "); buffer.append
	 * (" UNION ALL SELECT DECODE(MPCS_SKILLFLAG,'E',1,2) AS SLNO, DECODE(MPCS_SKILLFLAG,'E','Employee Cost','H','Contractor Cost') as PARAM, "
	 * ); buffer.append (" 0 AS EST_VAL, MPCS_TOTALVALUE AS ACT_VAL ");
	 * buffer.append (" FROM WOM_TL_MANPOWERCOSTACTUAL WHERE MPCS_MAINTWOID='" +
	 * woId + "'  AND MPCS_SKILLFLAG IN ('E','H') "); buffer.append
	 * (" UNION ALL SELECT 3 AS SLNO,'Spares Cost' as PARAM, WSCP_VALUE AS EST_VAL, 0 AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_SPARECOSTPLAN WHERE WSCP_WOID='" + woId +
	 * "'  "); buffer.append
	 * (" UNION ALL SELECT  3 AS SLNO,'Spares Cost' as PARAM, 0 AS EST_VAL, WSCA_VALUE AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_SPARECOSTACTUAL  WHERE WSCA_WOID='" + woId +
	 * "'  "); buffer.append
	 * (" UNION ALL SELECT 4 AS SLNO,'Service Cost' as PARAM, SVCP_BILLVALUE AS EST_VAL, 0 AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_SERVICECOSTPLAN WHERE SVCP_WOID='" + woId +
	 * "'  "); buffer.append
	 * (" UNION ALL SELECT 4 AS SLNO, 'Service Cost' as PARAM, 0 AS EST_VAL, SVCA_BILLVALUE AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_SERVICECOSTACTUAL  WHERE SVCA_WOID='" + woId
	 * + "'  "); buffer.append
	 * (" UNION ALL SELECT 5 AS SLNO,'Utility Cost' as PARAM, UTCP_TOTALVALUE AS EST_VAL, 0 AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_UTILITYCOSTPLAN WHERE UTCP_WOKEYID='" + woId
	 * + "'  "); buffer.append
	 * (" UNION ALL SELECT 5 AS SLNO, 'Utility Cost' as PARAM, 0 AS EST_VAL, UTCA_TOTALVALUE AS ACT_VAL "
	 * ); buffer.append (" FROM WOM_TL_UTILITYCOSTACTUAL WHERE UTCA_WOKEYID='" +
	 * woId + "'  "); buffer.append
	 * (" UNION ALL SELECT 6 AS SLNO, 'Other Cost' as PARAM, OTCP_AMOUNT AS EST_VAL, 0 AS ACT_VAL "
	 * ); buffer.append ("     FROM WOM_TL_OTHERCOSTPLAN WHERE OTCP_WOID='" + woId +
	 * "'  "); buffer.append
	 * ("    UNION ALL SELECT 6 AS SLNO, 'Other Cost' as PARAM, 0 AS EST_VAL, OTCD_AMOUNT AS ACT_VAL "
	 * ); buffer.append ("     FROM WOM_TL_OTHERCOSTACTUAL WHERE OTCD_WOID='" + woId
	 * + "' ");
	 * 
	 * buffer.append ("UNION ALL SELECT 1, 'Employee Cost', 0, 0 FROM DUAL ");
	 * buffer.append ("UNION ALL SELECT 2, 'Contractor Cost', 0, 0 FROM DUAL ");
	 * buffer.append ("UNION ALL SELECT 3, 'Spares Cost', 0, 0 FROM DUAL ");
	 * buffer.append ("UNION ALL SELECT 4, 'Service Cost', 0, 0 FROM DUAL ");
	 * buffer.append ("UNION ALL SELECT 5, 'Utility Cost', 0, 0 FROM DUAL ");
	 * buffer.append ("UNION ALL SELECT 6, 'Other Cost', 0, 0 FROM DUAL  ");
	 * 
	 * buffer.append (" ) GROUP BY GROUPING SETS ((SLNO,PARAM),()) ");
	 * 
	 * sql = buffer.toString(); CommonFunctions.debugMsg("sql: "+sql); return
	 * dbActionTemplate.getDataList(sql); } catch(Exception e) {
	 * e.printStackTrace(); } return null; }
	 */
	
	public List<String[]> getGridSummaryQuery(String woId) throws Exception {
	    try
	    {
	        String sql = "";
	        StringBuffer buffer = new StringBuffer();
	        buffer.append("");
	        buffer.append (" SELECT COALESCE(SLNO,7) AS SLNO, COALESCE(PARAM,'Total') AS PARAM, ");
	        buffer.append (" SUM(EST_VAL) AS EST_VAL, SUM(ACT_VAL) AS ACT_VAL FROM ( ");

	        buffer.append (" SELECT CASE WHEN MPCP_SKILLFLAG='E' THEN 1 ELSE 2 END AS SLNO, ");
	        buffer.append (" CASE WHEN MPCP_SKILLFLAG='E' THEN 'Employee Cost' WHEN MPCP_SKILLFLAG='H' THEN 'Contractor Cost' END AS PARAM, ");
	        buffer.append (" MPCP_TOTALVALUE AS EST_VAL, 0 AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_MANPOWERCOSTPLAN   WHERE MPCP_WOID='" + woId + "' AND MPCP_SKILLFLAG IN ('E','H') ");
	        buffer.append (" UNION ALL SELECT CASE WHEN MPCS_SKILLFLAG='E' THEN 1 ELSE 2 END AS SLNO, ");
	        buffer.append (" CASE WHEN MPCS_SKILLFLAG='E' THEN 'Employee Cost' WHEN MPCS_SKILLFLAG='H' THEN 'Contractor Cost' END AS PARAM, ");
	        buffer.append (" 0 AS EST_VAL, MPCS_TOTALVALUE AS ACT_VAL ");
	        buffer.append (" FROM BAL_WOM_TL_MANPOWERCOSTACTUAL WHERE MPCS_MAINTWOID='" + woId + "'  AND MPCS_SKILLFLAG IN ('E','H') ");
	        buffer.append (" UNION ALL SELECT 3 AS SLNO,'Spares Cost' as PARAM, WSCP_VALUE AS EST_VAL, 0 AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_SPARECOSTPLAN WHERE WSCP_WOID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT  3 AS SLNO,'Spares Cost' as PARAM, 0 AS EST_VAL, WSCA_VALUE AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_SPARECOSTACTUAL  WHERE WSCA_WOID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT 4 AS SLNO,'Service Cost' as PARAM, SVCP_BILLVALUE AS EST_VAL, 0 AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_SERVICECOSTPLAN WHERE SVCP_WOID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT 4 AS SLNO, 'Service Cost' as PARAM, 0 AS EST_VAL, SVCA_BILLVALUE AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_SERVICECOSTACTUAL  WHERE SVCA_WOID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT 5 AS SLNO,'Utility Cost' as PARAM, UTCP_TOTALVALUE AS EST_VAL, 0 AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_UTILITYCOSTPLAN WHERE UTCP_WOKEYID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT 5 AS SLNO, 'Utility Cost' as PARAM, 0 AS EST_VAL, UTCA_TOTALVALUE AS ACT_VAL ");
	        buffer.append (" FROM WOM_TL_UTILITYCOSTACTUAL WHERE UTCA_WOKEYID='" + woId + "'  ");
	        buffer.append (" UNION ALL SELECT 6 AS SLNO, 'Other Cost' as PARAM, OTCP_AMOUNT AS EST_VAL, 0 AS ACT_VAL ");
	        buffer.append ("     FROM WOM_TL_OTHERCOSTPLAN WHERE OTCP_WOID='" + woId + "'  ");
	        buffer.append ("    UNION ALL SELECT 6 AS SLNO, 'Other Cost' as PARAM, 0 AS EST_VAL, OTCD_AMOUNT AS ACT_VAL ");
	        buffer.append ("     FROM WOM_TL_OTHERCOSTACTUAL WHERE OTCD_WOID='" + woId + "' ");

	        buffer.append ("UNION ALL SELECT 1, 'Employee Cost', 0, 0 ");
	        buffer.append ("UNION ALL SELECT 2, 'Contractor Cost', 0, 0 ");
	        buffer.append ("UNION ALL SELECT 3, 'Spares Cost', 0, 0 ");
	        buffer.append ("UNION ALL SELECT 4, 'Service Cost', 0, 0 ");
	        buffer.append ("UNION ALL SELECT 5, 'Utility Cost', 0, 0 ");
	        buffer.append ("UNION ALL SELECT 6, 'Other Cost', 0, 0  ");

	        buffer.append (" ) GROUP BY GROUPING SETS ((SLNO,PARAM),()) ");
	        buffer.append (" ORDER BY SLNO "); 

	        sql = buffer.toString();
	        CommonFunctions.debugMsg("sql: "+sql);
	        return dbActionTemplate.getDataList(sql);
	    }
	    catch(Exception e) { e.printStackTrace(); }
	    return null;
	}
	public List<String[]> getCostInfoView(CommonFilter commonFilter) throws Exception {
		try
		{
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			
			//if (reportType.equals("BDM")) {
			CommonFunctions.debugMsg("report tpyee"+commonFilter.getReportType().getId());
			
			String reportType = commonFilter.getReportType().getId();			
			
			String drillLevel = commonFilter.getDrillLevel();
			String fromDate = commonFilter.getFromDate();
			String toDate = commonFilter.getToDate();
			String company = (commonFilter.getCompany() != null ? (commonFilter.getCompany().getId()!=null? commonFilter.getCompany().getId():null):null);
			String factory = (commonFilter.getFactory() != null ? (commonFilter.getFactory().getId()!=null? commonFilter.getFactory().getId():null):null);
			String section = (commonFilter.getSection() != null ? (commonFilter.getSection().getId()!=null? commonFilter.getSection().getId():null):null);
			String cell = (commonFilter.getCell() != null ? (commonFilter.getCell().getId()!=null? commonFilter.getCell().getId():null):null);
			String machine = (commonFilter.getMachine() != null ? (commonFilter.getMachine().getId()!=null? commonFilter.getMachine().getId():null):null);
			String assembly = (commonFilter.getAssembly() != null ? (commonFilter.getAssembly().getId()!=null? commonFilter.getAssembly().getId():null):null);
			String eqpGroup = (commonFilter.getEqpGroup() != null ? (commonFilter.getEqpGroup().getId()!=null? commonFilter.getEqpGroup().getId():null):null);
			String circle = (commonFilter.getCircle() != null ? (commonFilter.getCircle().getId()!=null? commonFilter.getCircle().getId():null):null);
			String costCenter = (commonFilter.getCostCenter() != null ? (commonFilter.getCostCenter().getId()!=null? commonFilter.getCostCenter().getId():null):null);
			String jhStep = (commonFilter.getJhStep() != null ? (commonFilter.getJhStep().getId()!=null? commonFilter.getJhStep().getId():null):null);
			String machineRank = (commonFilter.getMachineRank() != null ? (commonFilter.getMachineRank().getId()!=null? commonFilter.getMachineRank().getId():null):null);
			
			String CondSql ="";
			CondSql+= " AND TRUNC(BDMS_ENTRYDATE) BETWEEN  '" + fromDate + "' AND '" + toDate + "' ";
			
			if (company != null) CondSql+= " AND  CELL_COMPANYID = '" + company + "'"; 
			if (factory != null) CondSql+= " AND  CELL_FACTORYID = '" + factory + "'"; 
			if (section != null) CondSql+= " AND  SECT_KEYID = '" + section + "'"; 
			if (cell != null) CondSql+= " AND  CELL_KEYID = '" + cell + "'"; 
			if (machine != null) CondSql+= " AND  MCHM_KEYID = '" + machine + "'";			
			if (assembly != null) CondSql+= " AND  ASSM_KEYID = '" + assembly + "'"; 

 			
			if (("BDM").equals(reportType)) {
				
				buffer.append (" SELECT  DISTINCT BDMS_KEYID AS EMRNO,TO_CHAR(BDMS_REPORTEDDATE,'DD-MON-YYYY') AS ENTRYDATE,  BDMS_SHIFTID AS SHIFTID,SFTM_NAME AS SHIFT,CELL_FACTORYID, ");
				buffer.append (" SECT_KEYID,  SECT_NAME || ' - [' || SECT_CODE || ']' AS SECTION,  CELL_KEYID,CELL_NAME || ' - [' || CELL_CODE || ']' AS LINE ,  ");
				buffer.append (" MCHM_KEYID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']' AS MACHINE,  ASSM_KEYID,ASSM_NAME AS STATION ,BPHM_KEYID,BPHM_PHENOMENANAME AS PHENOMENA , ");
				buffer.append (" BCSM_KEYID, BCSM_NAME AS CAUSE,TO_CHAR(BDMS_WOSTARTTIME,'DD-MON-YYYY hh24:mi') AS STARTTIME, TO_CHAR(BDMS_WOENDTIME,'DD-MON-YYYY hh24:mi') AS ENDTIME,  ");
				buffer.append (" BDMS_DOWNTIME AS DOWNTIME,TRDM_KEYID ,TRDM_NAME AS TRADE,  REPLACE(REPLACE(REPLACE(BDMS_PROBLEMDESCRIPTION,'<*',''),'*>',''),'{}','') AS PROBLEM,  ");
				buffer.append (" REPLACE(REPLACE(REPLACE(BDAN_COUNTERMEASURE,'<*',''),'*>',''),'{}','') AS MEASURE,  ");
				buffer.append (" ROUND(MANPOWERVAL,2) ,ROUND(SPAREVAL,2) ,ROUND(serviceval,2) ,ROUND(UTLVAL,2) ,ROUND(OTHERVAL,2) ,ROUND(TOTALVAL,2) ,TO_CHAR(BDMS_REPORTEDDATE,'YYYY-MM-DD HH24:MI')  AS REPORTDATE ");
				buffer.append (" FROM  BDM_TL_MST,BDM_TL_DTL,GEN_TL_SECTIONMST,GEN_TL_CELLMST,GEN_TL_ASSEMBLYMST,BDM_TL_PHENOMENAMST,BDM_TL_CAUSEMST, ");
				buffer.append (" GEN_TL_SHIFTMST,GEN_TL_MACHINEMST,GEN_TL_TRADEMST,GEN_VW_MCHPRODGROUP,WOM_VW_COSTACTUAL ");
				buffer.append (" WHERE  BDMS_KEYID          = BDAN_BDMS_KEYID        AND  SECT_KEYID         = BDMS_SECTIONID    AND  CELL_KEYID     = BDMS_CELLID ");  
				buffer.append (" AND  MCHM_KEYID      = BDMS_MACHINEID    AND  BDMS_ASSEMBLYID     = ASSM_KEYID        AND  BDMS_FINALPHENOMENA = BPHM_KEYID(+)    ");
				buffer.append (" AND  SFTM_KEYID(+)       = BDMS_SHIFTID      AND  BDMS_FINALCAUSE     = BCSM_KEYID(+)     AND  BDMS_FINALTRADE     =TRDM_KEYID(+) ");
				buffer.append (" AND  MCHM_KEYID=MACHINEID(+)            AND  KEYID(+)  =   BDMS_KEYID AND BDMS_DOWNTIME > 0  AND BDMS_ACTIVE ='Y'  ");
				buffer.append (" AND BDMS_STATUS  IN ('I','C')  ");
				//buffer.append (" AND TRUNC(BDMS_ENTRYDATE) BETWEEN '01-Jan-2011' AND '31-Jan-2012'
				buffer.append (CondSql);
				buffer.append (" ORDER BY  TO_CHAR(BDMS_REPORTEDDATE,'YYYY-MM-DD HH24:MI')  DESC,MACHINE ");  				
			}
			
			sql = buffer.toString();
			CommonFunctions.debugMsg("sqkssss"+sql);
			CommonFunctions.debugMsg("report type : "+commonFilter.getReportType());
			return dbActionTemplate.getDataList(sql);			
		}
		
		catch(Exception e) { e.printStackTrace(); }		
		return null;	
	}

	public List<String[]> getGridEstQuery(String formName, String woId) throws Exception {			
		try
		{
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			
			if (formName.equals("empCost")) {
				buffer.append (" SELECT MPCP_SKILLID, GRDM_NAME, MPCP_MANPOWERID, EMPM_EMPLOYEENUMBER, EMPM_NAME, "); 
				buffer.append (" MPCP_NORMALMINS, MPCP_NORMALCOST, MPCP_TOTALVALUE ");
				buffer.append (" FROM WOM_TL_MANPOWERCOSTPLAN  , GEN_TL_EMPLOYEEMST,  GEN_TL_EMPGRADEMST ");
				buffer.append (" WHERE MPCP_WOID='" + woId + "'  AND MPCP_SKILLFLAG = 'E' "); 
				buffer.append (" AND MPCP_MANPOWERID = EMPM_KEYID AND MPCP_SKILLID = GRDM_KEYID ");
				buffer.append (" ORDER BY MPCP_CREATEDON ");
			}
			else if (formName.equals("contractorCost")) {
				buffer.append (" SELECT AMVM_KEYID, AMVM_NAME, MPCP_MANPOWERID, UNGM_CODE, UNGM_NAME,  "); 
				buffer.append (" MPCP_NORMALMINS, MPCP_NORMALCOST, MPCP_TOTALVALUE ");
				buffer.append (" FROM  WOM_TL_MANPOWERCOSTPLAN  , PLM_TL_UNSKILLEDGRADEMST_I,  GEN_TL_AMCVENDORMST");
				buffer.append (" WHERE MPCP_WOID = '" + woId + "'  AND MPCP_SKILLFLAG = 'H'  "); 
				buffer.append (" AND MPCP_MANPOWERID = UNGM_KEYID AND MPCP_SKILLID = AMVM_KEYID ");
				buffer.append (" ORDER BY MPCP_CREATEDON ");
			}
			else if (formName.equals("spareCost")) {
				buffer.append (" SELECT WSCP_REQUESTEDBY, EMPM_NAME, SPRM_KEYID, SPRM_PARTNO, SPRM_PARTNAME, "); 
				buffer.append (" WSCP_QUANTITY, WSCP_RATE, WSCP_VALUE ");
				buffer.append (" FROM WOM_TL_SPARECOSTPLAN  , GEN_TL_EMPLOYEEMST,  GEN_TL_SPARESMST ");
				buffer.append (" WHERE WSCP_WOID = '" + woId + "' "); 
				buffer.append (" AND WSCP_REQUESTEDBY = EMPM_KEYID AND WSCP_SPARESID = SPRM_KEYID ");
				buffer.append (" ORDER BY WSCP_CREATEDON ");
			}
			else if (formName.equals("serviceCost")) {
				buffer.append (" SELECT AMVM_KEYID, AMVM_CODE, AMVM_NAME, SVCP_JOBDESCRIPTION,  "); 
				buffer.append (" SVCP_BILLNO, SVCP_BILLVALUE, SVCP_REMARKS ");
				buffer.append (" FROM WOM_TL_SERVICECOSTPLAN , GEN_TL_AMCVENDORMST ");
				buffer.append (" WHERE SVCP_WOID = '" + woId + "'  AND SVCP_SERVICEID = AMVM_KEYID");
				buffer.append (" ORDER BY SVCP_CREATEDON ");
			}
			else if (formName.equals("utilityCost")) {
				buffer.append (" SELECT UTCP_REQUESTEDBY, EMPM_NAME, TOLM_KEYID, TOLM_CODE, TOLM_NAME,  "); 
				buffer.append (" UTCP_QUANTITY, UTCP_MINUTES, UTCP_COST, UTCP_TOTALVALUE, UTCP_REMARKS ");
				buffer.append (" FROM WOM_TL_UTILITYCOSTPLAN  , GEN_TL_EMPLOYEEMST,  GEN_TL_TOOLSMST ");
				buffer.append (" WHERE UTCP_WOKEYID = '" + woId + "' "); 
				buffer.append (" AND UTCP_REQUESTEDBY = EMPM_KEYID AND UTCP_UTILITYMSTID = TOLM_KEYID ");
				buffer.append (" ORDER BY UTCP_CREATEDON ");
			}		
			else if (formName.equals("otherCost")) {
				buffer.append (" SELECT OTCP_REQUESTEDBY, EMPM_NAME, OTCM_KEYID, OTCM_SHORTNAME, OTCM_COSTNAME, "); 
				buffer.append (" OTCP_AMOUNT, OTCP_REMARKS  ");
				buffer.append (" FROM WOM_TL_OTHERCOSTPLAN  , GEN_TL_EMPLOYEEMST,  WOM_TL_OTHERCOSTMST ");
				buffer.append (" WHERE OTCP_WOID = '" + woId + "' "); 
				buffer.append (" AND OTCP_REQUESTEDBY = EMPM_KEYID AND OTCP_OTHERCOSTMSTID = OTCM_KEYID ");
				buffer.append (" ORDER BY OTCP_CREATEDON ");
			}				
			
			CommonFunctions.debugMsg("woId : "+woId);
			
			sql = buffer.toString();
			CommonFunctions.debugMsg("sql : "+sql);
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }		
		return null;
	}
	
	public List<String[]> getGridActQuery(String formName, String woId ) throws Exception {			
		try
		{
			
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			
			if (formName.equals("empCost")) {
				buffer.append (" SELECT MPCS_DATE, MPCS_SKILLID, GRDM_NAME, MPCS_MANPOWERID, EMPM_EMPLOYEENUMBER, EMPM_NAME, MPCS_ACTIVITY, "); 
				buffer.append (" MPCS_NORMALWT, MPCS_HOLIDAYWT, MPCS_OTHERWT, MPCS_NORMALRATE, MPCS_HOLIDAYRATE, MPCS_OTHERRATE, MPCS_TOTALVALUE, MPCS_REMARKS ");
				buffer.append (" FROM BAL_WOM_TL_MANPOWERCOSTACTUAL  , GEN_TL_EMPLOYEEMST,  GEN_TL_EMPGRADEMST ");
				buffer.append (" WHERE MPCS_MAINTWOID = '" + woId + "'  AND MPCS_SKILLFLAG = 'E'  "); 
				buffer.append (" AND MPCS_MANPOWERID = EMPM_KEYID AND MPCS_SKILLID = GRDM_KEYID ");
				buffer.append (" ORDER BY MPCS_CREATEDON ");
			}
			else if (formName.equals("contractorCost")) {
				buffer.append (" SELECT MPCS_DATE, AMVM_KEYID, AMVM_NAME, MPCS_MANPOWERID, UNGM_CODE, UNGM_NAME, MPCS_ACTIVITY, "); 
				buffer.append (" MPCS_NORMALWT, MPCS_HOLIDAYWT, MPCS_OTHERWT, MPCS_NORMALRATE, MPCS_HOLIDAYRATE, MPCS_OTHERRATE, MPCS_TOTALVALUE, MPCS_REMARKS ");
				buffer.append (" FROM BAL_WOM_TL_MANPOWERCOSTACTUAL  , PLM_TL_UNSKILLEDGRADEMST_I,  GEN_TL_AMCVENDORMST ");
				buffer.append (" WHERE MPCS_MAINTWOID = '" + woId + "'  AND MPCS_SKILLFLAG = 'H'  "); 
				buffer.append (" AND MPCS_MANPOWERID = UNGM_KEYID AND MPCS_SKILLID = AMVM_KEYID ");
				buffer.append (" ORDER BY MPCS_CREATEDON ");
			}
			else if (formName.equals("spareCost")) {
				buffer.append (" SELECT WSCA_REQUESTEDBY, EMPM_NAME, SPRM_KEYID, SPRM_PARTNO, SPRM_PARTNAME,  "); 
				buffer.append (" WSCA_QUANTITY, WSCA_RATE, WSCA_VALUE ");
				buffer.append (" FROM WOM_TL_SPARECOSTACTUAL  , GEN_TL_EMPLOYEEMST,  GEN_TL_SPARESMST ");
				buffer.append (" WHERE  WSCA_WOID = '" + woId + "'  "); 
				buffer.append (" AND WSCA_REQUESTEDBY = EMPM_KEYID AND WSCA_SPARESID = SPRM_KEYID ");
				buffer.append (" ORDER BY WSCA_CREATEDON ");
			}
			else if (formName.equals("serviceCost")) {
				buffer.append (" SELECT AMVM_KEYID, AMVM_CODE, AMVM_NAME, SVCA_JOBDESCRIPTION,   "); 
				buffer.append (" SVCA_BILLNO, SVCA_BILLDATE, SVCA_BILLVALUE, SVCA_REMARKS");
				buffer.append (" FROM WOM_TL_SERVICECOSTACTUAL , GEN_TL_AMCVENDORMST  ");
				buffer.append (" WHERE SVCA_WOID='" + woId + "'  AND SVCA_SERVICEID = AMVM_KEYID ");
				buffer.append (" ORDER BY SVCA_CREATEDON ");				
			}
			else if (formName.equals("utilityCost")) {
				buffer.append (" SELECT UTCA_REQUESTEDBY, EMPM_NAME, TOLM_KEYID, TOLM_CODE, TOLM_NAME,  "); 
				buffer.append (" UTCA_DATE, UTCA_QUANTITY, UTCA_MINUTES, UTCA_COST, UTCA_TOTALVALUE, UTCA_REMARKS ");
				buffer.append (" FROM WOM_TL_UTILITYCOSTACTUAL  , GEN_TL_EMPLOYEEMST,  GEN_TL_TOOLSMST");
				buffer.append (" WHERE UTCA_WOKEYID = '" + woId + "' "); 
				buffer.append (" AND UTCA_REQUESTEDBY = EMPM_KEYID AND UTCA_UTILITYMSTID = TOLM_KEYID ");
				buffer.append (" ORDER BY UTCA_CREATEDON ");
			}	
			else if (formName.equals("otherCost")) {
				buffer.append (" SELECT OTCD_REQUESTEDBY, EMPM_NAME, OTCM_KEYID, OTCM_SHORTNAME, OTCM_COSTNAME, "); 
				buffer.append (" OTCD_DATE, OTCD_AMOUNT, OTCD_REMARKS  ");
				buffer.append (" FROM WOM_TL_OTHERCOSTACTUAL  , GEN_TL_EMPLOYEEMST,  WOM_TL_OTHERCOSTMST ");
				buffer.append (" WHERE OTCD_WOID = '" + woId + "' "); 
				buffer.append (" AND OTCD_REQUESTEDBY = EMPM_KEYID AND OTCD_OTHERCOSTMSTID = OTCM_KEYID ");
				buffer.append (" ORDER BY OTCD_CREATEDON ");
			}				
			
			CommonFunctions.debugMsg("woId : "+woId);
			sql = buffer.toString();
			return dbActionTemplate.getDataList(sql);	
		}
		catch(Exception e) { e.printStackTrace(); }		
		return null;
	}	
		
	public List<String[]> getAllCostInfo(CommonFilter commonFilter) throws Exception
	{
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			
		//	paramValues.add(commonFilter.getFactory()!= null ? commonFilter.getFactory().getId():"{}");
			paramValues.add("{}");
			paramValues.add("{}");
			paramValues.add(commonFilter.getSection()!= null ? commonFilter.getSection().getId():"{}");
			paramValues.add(commonFilter.getCell()!= null ? commonFilter.getCell().getId():"{}");
			paramValues.add(commonFilter.getCostCenter()!= null ? commonFilter.getCostCenter().getId():"{}");
			paramValues.add(commonFilter.getCircle()!= null ? commonFilter.getCircle().getId():"{}");
			paramValues.add( commonFilter.getMachine() != null ? commonFilter.getMachine().getId():"{}");
			paramValues.add(commonFilter.getTrade()!= null ? commonFilter.getTrade().getId():"{}");
			paramValues.add(commonFilter.getAssembly()!= null ? commonFilter.getAssembly().getId():"{}");
			paramValues.add(commonFilter.getEqpGroup()!= null ? commonFilter.getEqpGroup().getId():"{}");	
			paramValues.add("{}");
			paramValues.add("{}");
			paramValues.add("{}");
			
			
			List<String[]> CostInfoRptList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_COSTINFORMATION", paramValues);
				System.out.print("CostInfoRptList="+CostInfoRptList.size());
			
			return CostInfoRptList;
		}
		catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	public List<String[]> getPageTotal(String formName, String formType,  String woId) throws Exception
	{
		try
		{
		 	String sql ="";
		 	StringBuffer buffer = new StringBuffer();
		 	
		 	/*if (formType.equals("Estimate")) {
		 		if (formName.equals("empCost")) {		 			
		 			buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL, SUM(MPCP_NOOFHELPERS) AS NOOFEMP FROM ( ");
		 			buffer.append ("  SELECT ROUND(MPCP_TOTALVALUE ,2), MPCP_NOOFHELPERS ");
		 			buffer.append (" FROM  WOM_TL_MANPOWERCOSTPLAN,GEN_TL_EMPLOYEEMST,GEN_TL_EMPGRADEMST WHERE  EMPM_KEYID=MPCP_MANPOWERID ");		 		  
					buffer.append (" AND MPCP_WOID = '" + woId + "' AND  MPCP_SKILLFLAG='E' AND GRDM_KEYID =MPCP_SKILLID   ");
					buffer.append (" UNION  SELECT ROUND(MPCP_TOTALVALUE,2) AS TOTVAL, MPCP_NOOFHELPERS  ");
					buffer.append (" FROM  WOM_TL_MANPOWERCOSTPLAN,GEN_TL_EMPLOYEEMST,GEN_TL_EMPGRADEMST WHERE  EMPM_KEYID=MPCP_MANPOWERID "); 
					buffer.append (" AND MPCP_SKILLFLAG='E' AND GRDM_KEYID =MPCP_SKILLID  ");
					buffer.append (" AND  MPCP_WOID IN ( SELECT PWDD_WOMASTERID FROM BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "') ");
					buffer.append (" ) ");
				}*/
// 	if (formType.equals("Estimate")) {
//		 	    if (formName.equals("empCost")) {
//
//		 	        buffer.append("SELECT SUM(TOTVAL) AS TOTVAL, SUM(MPCP_NOOFHELPERS) AS NOOFEMP ");
//		 	        buffer.append("FROM ( ");
//
//		 	        buffer.append("SELECT ROUND(MPCP_TOTALVALUE, 2) AS TOTVAL, MPCP_NOOFHELPERS ");
//		 	        buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
//		 	        buffer.append("WHERE EMPM_KEYID = MPCP_MANPOWERID ");
//		 	        buffer.append("AND MPCP_WOID = '" + woId + "' ");
//		 	        buffer.append("AND MPCP_SKILLFLAG = 'E' ");
//		 	        buffer.append("AND GRDM_KEYID = MPCP_SKILLID ");
//
//		 	        buffer.append("UNION ");
//
//		 	        buffer.append("SELECT ROUND(MPCP_TOTALVALUE, 2) AS TOTVAL, MPCP_NOOFHELPERS ");
//		 	        buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
//		 	        buffer.append("WHERE EMPM_KEYID = MPCP_MANPOWERID ");
//		 	        buffer.append("AND MPCP_SKILLFLAG = 'E' ");
//		 	        buffer.append("AND GRDM_KEYID = MPCP_SKILLID ");
//		 	        buffer.append("AND MPCP_WOID IN ( ");
//		 	        buffer.append("SELECT PWDD_WOMASTERID ");
//		 	        buffer.append("FROM BAL_PLM_TL_WODTL ");
//		 	        buffer.append("WHERE PWDD_CALENDARID = '" + woId + "' ");
//		 	        buffer.append(") ");
//
//		 	        buffer.append(") A");
//		 	    }
		 	if (formType.equals("Estimate")) {
		 	    if (formName.equals("empCost")) {

		 	        buffer.append("SELECT SUM(TOTVAL) AS TOTVAL, ");
		 	        buffer.append("COUNT(MPCP_MANPOWERID) AS NOOFEMP ");
		 	        buffer.append("FROM ( ");

		 	        buffer.append("SELECT ROUND(MPCP_TOTALVALUE, 2) AS TOTVAL, MPCP_MANPOWERID ");
		 	        buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
		 	        buffer.append("WHERE EMPM_KEYID = MPCP_MANPOWERID ");
		 	        buffer.append("AND MPCP_WOID = '" + woId + "' ");
		 	        buffer.append("AND MPCP_SKILLFLAG = 'E' ");
		 	        buffer.append("AND GRDM_KEYID = MPCP_SKILLID ");

		 	        buffer.append("UNION ALL ");

		 	        buffer.append("SELECT ROUND(MPCP_TOTALVALUE, 2) AS TOTVAL, MPCP_MANPOWERID ");
		 	        buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
		 	        buffer.append("WHERE EMPM_KEYID = MPCP_MANPOWERID ");
		 	        buffer.append("AND MPCP_SKILLFLAG = 'E' ");
		 	        buffer.append("AND GRDM_KEYID = MPCP_SKILLID ");
		 	        buffer.append("AND MPCP_WOID IN ( ");
		 	        buffer.append("SELECT PWDD_WOMASTERID ");
		 	        buffer.append("FROM BAL_PLM_TL_WODTL ");
		 	        buffer.append("WHERE PWDD_CALENDARID = '" + woId + "' ");
		 	        buffer.append(") ");

		 	        buffer.append(") A");
		 	    }
		 	
				/*
				 * else if (formName.equals("contractorCost")) { buffer.append
				 * (" SELECT SUM(TOTVAL) AS TOTVAL, SUM(MPCP_NOOFHELPERS) AS NOOFEMP FROM ( " );
				 * buffer.append
				 * (" SELECT  ROUND(MPCP_TOTALVALUE,2) AS TOTVAL, MPCP_NOOFHELPERS ");
				 * buffer.append
				 * (" FROM  WOM_TL_MANPOWERCOSTPLAN,PLM_TL_UNSKILLEDGRADEMST_I,GEN_TL_AMCVENDORMST  "
				 * ); buffer.append (" WHERE  UNGM_KEYID=MPCP_MANPOWERID AND MPCP_WOID = '" +
				 * woId + "' AND  MPCP_SKILLFLAG='H' AND AMVM_KEYID =MPCP_SKILLID  ");
				 * buffer.append
				 * (" UNION  SELECT  ROUND(MPCP_TOTALVALUE,2) AS TOTVAL, MPCP_NOOFHELPERS ");
				 * buffer.append
				 * (" FROM  WOM_TL_MANPOWERCOSTPLAN,PLM_TL_UNSKILLEDGRADEMST_I,GEN_TL_AMCVENDORMST "
				 * ); buffer.append
				 * (" WHERE  UNGM_KEYID=MPCP_MANPOWERID AND MPCP_SKILLFLAG='H' AND AMVM_KEYID =MPCP_SKILLID   "
				 * ); buffer.append
				 * (" AND  MPCP_WOID IN ( SELECT PWDD_WOMASTERID FROM BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '"
				 * + woId + "')"); buffer.append (" ) "); }
				 */
		 	   else if (formName.equals("contractorCost")) {

		 		    buffer.append("SELECT SUM(TOTVAL) AS TOTVAL, COUNT(MPCP_MANPOWERID) AS NOOFEMP ");
		 		    buffer.append("FROM ( ");

		 		    buffer.append("SELECT ROUND(MPCP_TOTALVALUE,2) AS TOTVAL, MPCP_MANPOWERID ");
		 		    buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, PLM_TL_UNSKILLEDGRADEMST_I, GEN_TL_AMCVENDORMST ");
		 		    buffer.append("WHERE UNGM_KEYID = MPCP_MANPOWERID ");
		 		    buffer.append("AND MPCP_WOID = '" + woId + "' ");
		 		    buffer.append("AND MPCP_SKILLFLAG = 'H' ");
		 		    buffer.append("AND AMVM_KEYID = MPCP_SKILLID ");

		 		    buffer.append("UNION ALL ");

		 		    buffer.append("SELECT ROUND(MPCP_TOTALVALUE,2) AS TOTVAL, MPCP_MANPOWERID ");
		 		    buffer.append("FROM WOM_TL_MANPOWERCOSTPLAN, PLM_TL_UNSKILLEDGRADEMST_I, GEN_TL_AMCVENDORMST ");
		 		    buffer.append("WHERE UNGM_KEYID = MPCP_MANPOWERID ");
		 		    buffer.append("AND MPCP_SKILLFLAG = 'H' ");
		 		    buffer.append("AND AMVM_KEYID = MPCP_SKILLID ");
		 		    buffer.append("AND MPCP_WOID IN ( ");
		 		    buffer.append("SELECT PWDD_WOMASTERID ");
		 		    buffer.append("FROM BAL_PLM_TL_WODTL ");
		 		    buffer.append("WHERE PWDD_CALENDARID = '" + woId + "'");
		 		    buffer.append(") ");

		 		    buffer.append(") A");
		 		}
				else if (formName.equals("spareCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(WSCP_VALUE,2) AS TOTVAL FROM  WOM_TL_SPARECOSTPLAN,GEN_TL_SPARESMST,GEN_TL_EMPLOYEEMST  "); 
					buffer.append (" WHERE  EMPM_KEYID =WSCP_REQUESTEDBY AND SPRM_KEYID=WSCP_SPARESID AND WSCP_WOID = '" + woId + "'   ");
					buffer.append (" UNION  SELECT  ROUND(WSCP_VALUE,2) AS TOTVAL FROM  WOM_TL_SPARECOSTPLAN,GEN_TL_SPARESMST,GEN_TL_EMPLOYEEMST  ");
					buffer.append (" WHERE  EMPM_KEYID =WSCP_REQUESTEDBY AND SPRM_KEYID=WSCP_SPARESID  "); 
					buffer.append (" AND  WSCP_WOID IN ( SELECT PWDD_WOMASTERID FROM BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "')");
					buffer.append (" ) ");
				}
				else if (formName.equals("serviceCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(SVCP_BILLVALUE,2) AS TOTVAL FROM  WOM_TL_SERVICECOSTPLAN,GEN_TL_AMCVENDORMST  "); 
					buffer.append (" WHERE  AMVM_KEYID=SVCP_SERVICEID AND  SVCP_WOID = '" + woId + "'  ");
					buffer.append (" UNION  SELECT   ROUND(SVCP_BILLVALUE,2) AS TOTVAL  FROM  WOM_TL_SERVICECOSTPLAN,GEN_TL_AMCVENDORMST ");
					buffer.append (" WHERE  AMVM_KEYID=SVCP_SERVICEID  AND  SVCP_WOID IN ( SELECT PWDD_WOMASTERID FROM BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "') ");
					buffer.append (" ) ");
				}
				else if (formName.equals("utilityCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(UTCP_TOTALVALUE,2) AS TOTVAL FROM  WOM_TL_UTILITYCOSTPLAN,GEN_TL_TOOLSMST,GEN_TL_EMPLOYEEMST   "); 
					buffer.append (" WHERE  TOLM_KEYID=UTCP_UTILITYMSTID AND  EMPM_KEYID=UTCP_REQUESTEDBY AND  TOLM_TYPE ='U'  ");
					buffer.append (" AND UTCP_WOKEYID = '" + woId + "' " );
					buffer.append (" UNION  SELECT  ROUND(UTCP_TOTALVALUE,2) AS TOTVAL FROM  WOM_TL_UTILITYCOSTPLAN,GEN_TL_TOOLSMST,GEN_TL_EMPLOYEEMST ");
					buffer.append (" WHERE  TOLM_KEYID=UTCP_UTILITYMSTID  AND  EMPM_KEYID=UTCP_REQUESTEDBY AND  TOLM_TYPE ='U'  "); 
					buffer.append (" AND  UTCP_WOKEYID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL  WHERE PWDD_CALENDARID = '" + woId + "' ) ");
					buffer.append (" ) ");
				}		
				else if (formName.equals("otherCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(OTCP_AMOUNT,2) AS TOTVAL FROM  WOM_TL_OTHERCOSTPLAN,WOM_TL_OTHERCOSTMST,GEN_TL_EMPLOYEEMST  "); 
					buffer.append (" WHERE  OTCM_KEYID=OTCP_OTHERCOSTMSTID AND  EMPM_KEYID=OTCP_REQUESTEDBY AND  OTCP_WOID = '" + woId + "'  ");
					buffer.append (" UNION  SELECT  ROUND(OTCP_AMOUNT,2) AS TOTVAL FROM  WOM_TL_OTHERCOSTPLAN,WOM_TL_OTHERCOSTMST,GEN_TL_EMPLOYEEMST  ");
					buffer.append (" WHERE  OTCM_KEYID=OTCP_OTHERCOSTMSTID AND  EMPM_KEYID=OTCP_REQUESTEDBY "); 
					buffer.append (" AND  OTCP_WOID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "') ");
					buffer.append (" ) ");
				}					 			
		 	}
		 	/*else if (formType.equals("Actual")) {
		 		if (formName.equals("empCost")) {
		 			buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL, SUM(MPCS_NOOFHELPERS) AS NOOFEMP FROM ( " );
					buffer.append (" SELECT  ROUND(MPCS_TOTALVALUE,2) AS TOTVAL, MPCS_NOOFHELPERS ");
					buffer.append (" FROM  BAL_WOM_TL_MANPOWERCOSTACTUAL,GEN_TL_EMPLOYEEMST,GEN_TL_EMPGRADEMST  "); 
					buffer.append (" WHERE  EMPM_KEYID=MPCS_MANPOWERID AND MPCS_MAINTWOID = '" + woId + "' AND  MPCS_SKILLFLAG='E' AND GRDM_KEYID = MPCS_SKILLID  ");
					buffer.append (" UNION SELECT  ROUND(MPCS_TOTALVALUE,2) AS TOTVAL , MPCS_NOOFHELPERS ");
					buffer.append (" FROM  BAL_WOM_TL_MANPOWERCOSTACTUAL,GEN_TL_EMPLOYEEMST,GEN_TL_EMPGRADEMST  ");
					buffer.append (" WHERE  EMPM_KEYID=MPCS_MANPOWERID  AND  MPCS_SKILLFLAG = 'E' AND GRDM_KEYID = MPCS_SKILLID   "); 
					buffer.append (" AND MPCS_MAINTWOID  IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "')    ");
					buffer.append (" ) ");
				}*/
		 	else if (formType.equals("Actual")) {
		 	    if (formName.equals("empCost")) {

		 	        buffer.append("SELECT SUM(TOTVAL) AS TOTVAL, COUNT(MPCS_MANPOWERID) AS NOOFEMP ");
		 	        buffer.append("FROM ( ");

		 	        buffer.append("SELECT ROUND(MPCS_TOTALVALUE,2) AS TOTVAL, MPCS_MANPOWERID ");
		 	        buffer.append("FROM BAL_WOM_TL_MANPOWERCOSTACTUAL, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
		 	        buffer.append("WHERE EMPM_KEYID = MPCS_MANPOWERID ");
		 	        buffer.append("AND MPCS_MAINTWOID = '" + woId + "' ");
		 	        buffer.append("AND MPCS_SKILLFLAG = 'E' ");
		 	        buffer.append("AND GRDM_KEYID = MPCS_SKILLID ");

		 	        buffer.append("UNION ALL ");

		 	        buffer.append("SELECT ROUND(MPCS_TOTALVALUE,2) AS TOTVAL, MPCS_MANPOWERID ");
		 	        buffer.append("FROM BAL_WOM_TL_MANPOWERCOSTACTUAL, GEN_TL_EMPLOYEEMST, GEN_TL_EMPGRADEMST ");
		 	        buffer.append("WHERE EMPM_KEYID = MPCS_MANPOWERID ");
		 	        buffer.append("AND MPCS_SKILLFLAG = 'E' ");
		 	        buffer.append("AND GRDM_KEYID = MPCS_SKILLID ");
		 	        buffer.append("AND MPCS_MAINTWOID IN ( ");
		 	        buffer.append("SELECT PWDD_WOMASTERID ");
		 	        buffer.append("FROM BAL_PLM_TL_WODTL ");
		 	        buffer.append("WHERE PWDD_CALENDARID = '" + woId + "'");
		 	        buffer.append(") ");

		 	        buffer.append(") A");
		 	    }
		 	
				/*
				 * else if (formName.equals("contractorCost")) { buffer.append
				 * (" SELECT SUM(TOTVAL) AS TOTVAL , SUM(MPCS_NOOFHELPERS) AS NOOFEMP FROM ( "
				 * ); buffer.append
				 * (" SELECT  ROUND(MPCS_TOTALVALUE,2) AS TOTVAL , MPCS_NOOFHELPERS ");
				 * buffer.append
				 * (" FROM  BAL_WOM_TL_MANPOWERCOSTACTUAL,PLM_TL_UNSKILLEDGRADEMST_I,GEN_TL_AMCVENDORMST  "
				 * ); buffer.append (" WHERE  UNGM_KEYID=MPCS_MANPOWERID AND MPCS_MAINTWOID = '"
				 * + woId + "'  AND MPCS_SKILLFLAG='H' AND AMVM_KEYID = MPCS_SKILLID  ");
				 * buffer.append
				 * (" UNION SELECT  ROUND(MPCS_TOTALVALUE,2) AS TOTVAL , MPCS_NOOFHELPERS ");
				 * buffer.append
				 * (" FROM  WOM_TL_MANPOWERCOSTACTUAL,PLM_TL_UNSKILLEDGRADEMST_I,GEN_TL_AMCVENDORMST "
				 * ); buffer.append
				 * (" WHERE  UNGM_KEYID=MPCS_MANPOWERID   AND MPCS_SKILLFLAG='H' AND AMVM_KEYID = MPCS_SKILLID  "
				 * ); buffer.append
				 * (" AND MPCS_MAINTWOID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '"
				 * + woId + "')  "); buffer.append (" ) "); }
				 */
		 	   else if (formName.equals("contractorCost")) {

		 		    buffer.append("SELECT SUM(TOTVAL) AS TOTVAL, COUNT(MPCS_MANPOWERID) AS NOOFEMP ");
		 		    buffer.append("FROM ( ");

		 		    buffer.append("SELECT ROUND(MPCS_TOTALVALUE,2) AS TOTVAL, MPCS_MANPOWERID ");
		 		    buffer.append("FROM BAL_WOM_TL_MANPOWERCOSTACTUAL, PLM_TL_UNSKILLEDGRADEMST_I, GEN_TL_AMCVENDORMST ");
		 		    buffer.append("WHERE UNGM_KEYID = MPCS_MANPOWERID ");
		 		    buffer.append("AND MPCS_MAINTWOID = '" + woId + "' ");
		 		    buffer.append("AND MPCS_SKILLFLAG = 'H' ");
		 		    buffer.append("AND AMVM_KEYID = MPCS_SKILLID ");

		 		    buffer.append("UNION ALL ");

		 		    buffer.append("SELECT ROUND(MPCS_TOTALVALUE,2) AS TOTVAL, MPCS_MANPOWERID ");
		 		    buffer.append("FROM BAL_WOM_TL_MANPOWERCOSTACTUAL, PLM_TL_UNSKILLEDGRADEMST_I, GEN_TL_AMCVENDORMST ");
		 		    buffer.append("WHERE UNGM_KEYID = MPCS_MANPOWERID ");
		 		    buffer.append("AND MPCS_SKILLFLAG = 'H' ");
		 		    buffer.append("AND AMVM_KEYID = MPCS_SKILLID ");
		 		    buffer.append("AND MPCS_MAINTWOID IN ( ");
		 		    buffer.append("SELECT PWDD_WOMASTERID ");
		 		    buffer.append("FROM BAL_PLM_TL_WODTL ");
		 		    buffer.append("WHERE PWDD_CALENDARID = '" + woId + "'");
		 		    buffer.append(") ");

		 		    buffer.append(") A");
		 		}
				else if (formName.equals("spareCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(WSCA_RATE,2) AS TOTVAL FROM  WOM_TL_SPARECOSTACTUAL,GEN_TL_SPARESMST,GEN_TL_EMPLOYEEMST  "); 
					buffer.append (" WHERE  SPRM_KEYID=WSCA_SPARESID AND EMPM_KEYID =WSCA_REQUESTEDBY  AND WSCA_WOID = '" + woId + "'  ");
					buffer.append (" UNION SELECT  ROUND(WSCA_VALUE,2) AS TOTVAL FROM  WOM_TL_SPARECOSTACTUAL,GEN_TL_SPARESMST,GEN_TL_EMPLOYEEMST ");
					buffer.append (" WHERE  SPRM_KEYID=WSCA_SPARESID  AND EMPM_KEYID =WSCA_REQUESTEDBY   "); 
					buffer.append (" AND WSCA_WOID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "')  ");
					buffer.append (" ) ");
				}
				else if (formName.equals("serviceCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(SVCA_BILLVALUE,2) AS TOTVAL FROM  WOM_TL_SERVICECOSTACTUAL,GEN_TL_AMCVENDORMST  "); 
					buffer.append (" WHERE  AMVM_KEYID=SVCA_SERVICEID AND  SVCA_WOID = '" + woId + "'  ");
					buffer.append (" UNION SELECT  ROUND(SVCA_BILLVALUE,2) AS TOTVAL FROM  WOM_TL_SERVICECOSTACTUAL,GEN_TL_AMCVENDORMST  ");
					buffer.append (" WHERE  AMVM_KEYID=SVCA_SERVICEID  AND  SVCA_WOID  IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL ");
					buffer.append (" 						WHERE PWDD_CALENDARID = '" + woId + "')  ");
					buffer.append (" ) ");
				}
				else if (formName.equals("utilityCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(UTCA_TOTALVALUE,2) AS TOTVAL FROM  WOM_TL_UTILITYCOSTACTUAL,GEN_TL_TOOLSMST,GEN_TL_EMPLOYEEMST   "); 
					buffer.append (" WHERE  TOLM_KEYID=UTCA_UTILITYMSTID  AND  EMPM_KEYID=UTCA_REQUESTEDBY AND  TOLM_TYPE ='U' AND UTCA_WOKEYID = '" + woId + "'   ");
					buffer.append (" UNION  SELECT  ROUND(UTCA_TOTALVALUE,2) AS TOTVAL FROM  WOM_TL_UTILITYCOSTACTUAL,GEN_TL_TOOLSMST,GEN_TL_EMPLOYEEMST  ");
					buffer.append (" WHERE  TOLM_KEYID=UTCA_UTILITYMSTID  AND  EMPM_KEYID=UTCA_REQUESTEDBY AND  TOLM_TYPE ='U'   "); 
					buffer.append (" AND  UTCA_WOKEYID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "') ");
					buffer.append (" ) ");
				}		
				else if (formName.equals("otherCost")) {
					buffer.append (" SELECT SUM(TOTVAL) AS TOTVAL FROM ( " );
					buffer.append (" SELECT  ROUND(OTCD_AMOUNT,2) AS TOTVAL FROM  WOM_TL_OTHERCOSTACTUAL,WOM_TL_OTHERCOSTMST,GEN_TL_EMPLOYEEMST  "); 
					buffer.append (" WHERE  OTCM_KEYID=OTCD_OTHERCOSTMSTID  AND  EMPM_KEYID=OTCD_REQUESTEDBY AND  OTCD_WOID = '" + woId + "'    ");
					buffer.append (" UNION  SELECT  ROUND(OTCD_AMOUNT,2) AS TOTVAL FROM  WOM_TL_OTHERCOSTACTUAL,WOM_TL_OTHERCOSTMST,GEN_TL_EMPLOYEEMST  ");
					buffer.append (" WHERE  OTCM_KEYID=OTCD_OTHERCOSTMSTID AND  EMPM_KEYID=OTCD_REQUESTEDBY  "); 
					buffer.append (" AND  OTCD_WOID IN ( SELECT PWDD_WOMASTERID FROM  BAL_PLM_TL_WODTL WHERE PWDD_CALENDARID = '" + woId + "') ");
					buffer.append (" ) ");
				}				
		}
		 	
		 	sql = buffer.toString();
		 	CommonFunctions.debugMsg("sqllll"+sql);
			return dbActionTemplate.getDataList(sql);			 
		}
		catch (Exception e) {
			throw new Exception(e.getMessage()); 
		}
		
	}
	
	
}

