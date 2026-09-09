/*  Author ManiKandan*/
package com.akranta.tpm.dao.impl;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;
import com.akranta.tpm.dao.ImprovementProjSheetKaiDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class ImprovementProjSheetKaiDaoImpl implements ImprovementProjSheetKaiDao {
private DBActionTemplate dbActionTemplate;
	
	public ImprovementProjSheetKaiDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String []> getImprovementProjSheetkaiReport(CommonFilter commonFilter) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Inside Improvement Project Sheet daoimpl");
			List<String> paramValues = new ArrayList<String>();
			
			
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);		
			paramValues.add(commonParams);

			CommonMessage.debugMsg("before call functuion"+paramValues.toString());
			List<String[]> ImproprojshtReport = dbActionTemplate.processFunctionCalls("KZN_PC_KAIZEN.KZN_FN_IMPROVPROJECTSHEET", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					CommonMessage.debugMsg("commonFilter.setTotalRecordCnt:"+commonFilter.getTotalRecordCnt());
				}
			}	
			
			return ImproprojshtReport;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String[]> getImprovementProjSheetkaiexlReport(String kaizId) throws Exception
		{
			try
			{
				CommonMessage.debugMsg("Inside export daoimpl");
				List<String> paramValues = new ArrayList<String>();				
				paramValues.add(kaizId);
				
				CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
				Map<Integer, List<String[]>> improprojshtReport = dbActionTemplate.processDbFunCallMultCursorPG("KZN_FN_IMPSHEET1PGRPT", paramValues,6);
				
				for(int i = 0; i <  improprojshtReport.size();i++ )
				{
					List<String[]> lists =  improprojshtReport.get(i);
				}
				
				CommonMessage.debugMsg("Inside v: " +improprojshtReport);
				
				//return ImproprojshtReport;
				return null;
				
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
	}


	/*@Override
	public List<String[]> getAllFiveSAuditareamaster(String kaizId,String flid,String benTypeVal,String user,String format) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel user :: "+user+" benTypeVal :: "+benTypeVal);
			CommonMessage.debugMsg(" Dao Impl :: flid :: New :: "+flid);
			//String flid=commonFilter.getFlid();
			List<String> paramValues = new ArrayList<String>();				
			CommonFilter  commonFilter = new CommonFilter();

			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT role_name, empm_name FROM (SELECT FRT_FNLN_KEYID,wrin_keyid, wrkd_keyid, wrml_keyid,role_keyid,role_name,empm_keyid,");
			sql.append(" empm_name,NVL(wrin_status, 'Pending')wrin_status,wrin_date,wrin_remarks,role_level FROM gen_tl_workflow_info,");
			sql.append(" gen_tl_workflow_menu_link,gen_tl_workflowdtl,adm_tl_rolemst,gen_tl_fnlnroleteam,gen_tl_employeemst ");
			sql.append(" WHERE wrin_wrml_keyid(+) = wrml_keyid  AND wrin_wrkd_keyid(+) = wrkd_keyid AND wrml_wrkm_keyid = wrkd_wrkm_keyid ");
			sql.append(" AND wrkd_stage = role_keyid AND role_keyid = frt_role_keyid AND frt_empm_keyid = empm_keyid AND wrml_trans_code = '"+benTypeVal+"') a, ");
			sql.append(" (SELECT frt_empm_keyid, role_level selemprole, parentflids|| '-' || flid parentflids  FROM adm_tl_rolemst, gen_tl_fnlnroleteam,  ");
			sql.append(" gen_mv_flidhierarchy  WHERE role_keyid = frt_role_keyid AND frt_empm_keyid = '"+user+"'");
			sql.append(" AND INSTR (parentflids || '-' || flid, frt_fnln_keyid) > 0 ");
			sql.append(" AND flid ='"+flid+"') b  WHERE 1=1 and instr(parentflids, FRT_FNLN_KEYID) > 0 ORDER BY a.role_level DESC ");
			
			//sql.append(" FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
		    CommonMessage.debugMsg("sql..."+sql);
	        List<String []> gridData = dbActionTemplate.getDataList(sql);
			return gridData ;
			
			
		    StringBuffer sql1 = new StringBuffer();
			sql1.append(" SELECT KHDM_KAIZENID, SECT_NAME || ' - [' || SECT_CODE || ']' AS SHOP , ");
			sql1.append(" CELL_NAME || ' - [' || CELL_CODE || ']' as   CELL  , ");
			sql1.append(" DECODE(MCHM_KEYID,KHDM_MACHINEID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']','') ");
			sql1.append("  AS  EQUIPMENT ,  TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY') AS TARGETDATE, ");
			sql1.append("  EMPM_CODE AS RESPONSIBILITY, ");
			sql1.append(" DECODE (KHDM_STATUS, 'P','PENDING', 'C', 'COMPLETE', 'A', 'PENDING') AS STATUS ");
			sql1.append(" FROM ");
			sql1.append(" GEN_TL_SECTIONMST , GEN_TL_CELLMST , GEN_TL_MACHINEMST , KZN_TL_HDMST ,GEN_TL_EMPLOYEEMST ");
			sql1.append(" WHERE CELL_SECTIONID = SECT_KEYID AND KHDM_CELLID = CELL_KEYID ");
			sql1.append(" AND KHDM_MACHINEID = MCHM_KEYID(+) AND KHDM_RESPONSIBILITYID = EMPM_KEYID AND KHDM_REFDOCTYPE='KZN' ");
			sql1.append(" AND KHDM_KAIZENID = " + "'" + kaizId +"'" + " ");
			
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	*/
	
	@Override
	public Map<Integer, List<String[]>> getImprovementProjSheetkaiexlReport(String kaizId,String flid,String benTypeVal,String user,String format) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel user :: "+user+" benTypeVal :: "+benTypeVal);
			CommonMessage.debugMsg(" Dao Impl :: flid :: New :: "+flid);
			//String flid=commonFilter.getFlid();
			List<String> paramValues = new ArrayList<String>();				
  
		    paramValues.add(kaizId);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			Map<Integer, List<String[]>> improprojshtReport = dbActionTemplate.processDbFunCallMultCursorPG("KZN_FN_IMPSHEET1PGRPT", paramValues,6);
			
			for(int i = 0; i <  improprojshtReport.size();i++ )
			{
				List<String[]> lists =  improprojshtReport.get(i);
			}
			
			CommonMessage.debugMsg("Inside v: " +improprojshtReport.toString());
			CommonMessage.debugMsg("Inside ParamVal: " +paramValues);
			
			//return ImproprojshtReport;
			return improprojshtReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
  
	//-----------------------------------------------------Vignesh 01Oct2025 ------------------------------------//
	
	@Override
	public List<String[]> getImprovementProjSheetkaiexlReportApproval(String User,
	                                                                  String flid,
	                                                                  String benTypeVal,
	                                                                  String kaizId) throws Exception {
	  CommonMessage.debugMsg(" User :: " + User + " flid :: " + flid + " benTypeVal :: " + benTypeVal + " :: kaizId :: " + kaizId);

	  StringBuilder sql = new StringBuilder();

	  sql.append(" select distinct role_name,empm_name,  ")
	   .append("  CASE WHEN currentstat = 'E' THEN 'REWORK' ")
	   .append("       WHEN currentstat = 'R' THEN 'REJECTED' ")
	   .append("       ELSE TO_CHAR(wrin_date,'dd-Mon-yyyy') ")
	   .append("  END wrin_date,  role_level,wrin_remarks ")
	   .append("from ( ")
	   .append("  SELECT wrin_keyid, wrkd_keyid, wrml_keyid, empm_keyid, ")
	   .append("         CASE WHEN empm_keyid = frt_empm_keyid ")
	   .append("              THEN (CASE WHEN wrin_status = 'Pending' THEN 'Y' ELSE 'N' END) ")
	   .append("              ELSE 'N' ")
	   .append("         END enabl, ")
	   .append("         role_keyid, ")
	   .append("         lead(coalesce(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat, ")
	   .append("         '' editrow, '' saverow, role_name, empm_name, ")
	   .append("         wrin_status currentstat, wrin_date, wrin_remarks, role_level, ")
	   .append("         LEAD (role_level, 1) OVER (ORDER BY role_level) prevLevl ")
	   .append("  FROM ( ")
	   .append("    SELECT frt.FRT_FNLN_KEYID, ")
	   .append("           w.wrin_keyid, wd.wrkd_keyid, ml.wrml_keyid, ")
	   .append("           r.role_keyid, r.role_name, ")
	   .append("           e.empm_keyid, e.empm_name, ")
	   .append("           COALESCE(w.wrin_status, 'Pending') wrin_status, ")
	   .append("           w.wrin_date, w.wrin_remarks, ")
	   .append("           r.role_level ")
	   .append("    FROM   gen_tl_workflow_menu_link  ml ")
	   .append("    JOIN   gen_tl_workflowdtl        wd ON ml.wrml_wrkm_keyid = wd.wrkd_wrkm_keyid ")
	   .append("    JOIN   adm_tl_rolemst            r  ON wd.wrkd_stage     = r.role_keyid ")
	   .append("    JOIN   gen_tl_fnlnroleteam       frt ON r.role_keyid     = frt.frt_role_keyid ")
	   .append("    JOIN   gen_tl_employeemst        e   ON frt.frt_empm_keyid= e.empm_keyid ")
	   .append("    LEFT JOIN gen_tl_workflow_info   w   ON w.wrin_wrml_keyid = ml.wrml_keyid ")
	   .append("                                         AND w.wrin_wrkd_keyid = wd.wrkd_keyid ")
	   .append("                                         AND w.wrin_ref_id    = '").append(kaizId).append("' ")
	   .append("    WHERE  ml.wrml_trans_code = '").append(benTypeVal).append("' ")
	   .append("  ) a, ")
	   .append("  ( ")
	   .append("    SELECT frt.frt_empm_keyid, ")
	   .append("           r.role_level selemprole, ")
	   .append("           h.parentflids|| '-' || h.flid parentflids, ")
	   .append("           frt.frt_fnln_keyid ")
	   .append("    FROM   adm_tl_rolemst r ")
	   .append("    JOIN   gen_tl_fnlnroleteam frt ON r.role_keyid = frt.frt_role_keyid ")
	   .append("    JOIN   gen_mv_flidhierarchy h ")
	   .append("           ON POSITION(frt.frt_fnln_keyid IN (h.parentflids || '-' || h.flid)) > 0 ")
	   .append("    WHERE  h.flid = '").append(flid).append("' ")
	   .append("  ) b ")
	   .append("  WHERE  1=1 ")
	   .append("    AND  POSITION(a.FRT_FNLN_KEYID IN b.parentflids) > 0 ")
	   .append("  ORDER BY a.role_level DESC ")
	   .append(") x ")
	   .append("order by role_level DESC");


	  CommonMessage.debugMsg(" sql... 12345678 " + sql);

	  List<String[]>  improprojshtApproval  = dbActionTemplate.getDataList(sql.toString());
	  return improprojshtApproval;
	}
	
	
	
//	@Override
//	public List<String[]>  getImprovementProjSheetkaiexlReportApproval(String User, String flid, String benTypeVal, String kaizId) throws Exception {
//		// TODO Auto-generated method stub
//
//		CommonMessage.debugMsg(" User :: "+User+" flid :: "+flid+" benTypeVal :: "+benTypeVal+" :: kaizId :: "+kaizId);
//		StringBuilder sql = new StringBuilder();
//
//		/*sql.append(" SELECT distinct role_name, empm_name,wrin_date, role_level FROM (SELECT FRT_FNLN_KEYID,wrin_keyid, wrkd_keyid, wrml_keyid,role_keyid,role_name,empm_keyid,");
//		sql.append(" empm_name,NVL(wrin_status, 'Pending')wrin_status,wrin_date,wrin_remarks,role_level FROM gen_tl_workflow_info,");
//		sql.append(" gen_tl_workflow_menu_link,gen_tl_workflowdtl,adm_tl_rolemst,gen_tl_fnlnroleteam,gen_tl_employeemst ");
//		sql.append(" WHERE wrin_wrml_keyid(+) = wrml_keyid  AND wrin_wrkd_keyid(+) = wrkd_keyid AND wrml_wrkm_keyid = wrkd_wrkm_keyid ");
//		sql.append(" AND wrkd_stage = role_keyid AND role_keyid = frt_role_keyid AND frt_empm_keyid = empm_keyid AND wrml_trans_code = '"+benTypeVal+"') a, ");
//		sql.append(" (SELECT frt_empm_keyid, role_level selemprole, parentflids|| '-' || flid parentflids  FROM adm_tl_rolemst, gen_tl_fnlnroleteam,  ");
//		sql.append(" gen_mv_flidhierarchy  WHERE role_keyid = frt_role_keyid AND frt_empm_keyid = '"+User+"'");
//		sql.append(" AND INSTR (parentflids || '-' || flid, frt_fnln_keyid) > 0  ");
//		sql.append(" AND flid ='"+flid+"') b  WHERE 1=1 and instr(parentflids, FRT_FNLN_KEYID) > 0 order by role_level desc ");*/
//		
//			sql.append(" select distinct role_name,empm_name,DECODE(currentstat, 'E', 'REWORK','R', 'REJECTED', TO_CHAR(wrin_date,'dd-Mon-yyyy')) wrin_date,role_level,wrin_remarks from ( "); 
//			sql.append(" SELECT   wrin_keyid, wrkd_keyid, wrml_keyid, empm_keyid, "); 
//			sql.append(" DECODE (empm_keyid, frt_empm_keyid, decode(wrin_status,'Pending', 'Y', 'N'),'N') enabl,");   
//			sql.append(" role_keyid,lead(nvl(wrin_status,'Pending'),1) OVER (ORDER BY role_level ) prevstat,  '' editrow, '' saverow, role_name,"); 
//			sql.append(" empm_name, wrin_status currentstat, wrin_date,  wrin_remarks,role_level,LEAD (role_level, 1) ");
//			sql.append(" OVER (ORDER BY role_level) prevLevl  FROM ( ");
//			sql.append(" SELECT FRT_FNLN_KEYID,wrin_keyid, wrkd_keyid, wrml_keyid, role_keyid, role_name,  empm_keyid, empm_name, ");   
//			sql.append(" NVL (wrin_status, 'Pending') wrin_status,  wrin_date,  wrin_remarks, role_level  ");
//			sql.append(" FROM gen_tl_workflow_info,  gen_tl_workflow_menu_link,   gen_tl_workflowdtl,  ");
//			sql.append(" adm_tl_rolemst,   gen_tl_fnlnroleteam,  gen_tl_employeemst  ");
//			sql.append(" WHERE wrin_wrml_keyid(+) = wrml_keyid  AND wrin_wrkd_keyid(+) = wrkd_keyid  AND wrml_wrkm_keyid = wrkd_wrkm_keyid ");  
//			sql.append(" AND wrkd_stage = role_keyid  AND role_keyid = frt_role_keyid  AND frt_empm_keyid = empm_keyid  ");
//			sql.append(" AND wrml_trans_code = '"+benTypeVal+"' ");
//			sql.append(" AND wrin_ref_id(+) ='"+kaizId+"' ) a,  (  "); //AND wrin_ref_type(+) = 'KZNBTS'
//			sql.append(" SELECT frt_empm_keyid, role_level selemprole, parentflids|| '-' || flid parentflids ");  
//			sql.append(" FROM adm_tl_rolemst, gen_tl_fnlnroleteam, gen_mv_flidhierarchy  ");
//			sql.append(" WHERE role_keyid = frt_role_keyid ");  
//			sql.append(" AND INSTR (parentflids || '-' || flid, frt_fnln_keyid) > 0  AND flid ='"+flid+"') b  ");
//			sql.append(" WHERE 1=1  and instr(parentflids, FRT_FNLN_KEYID) > 0  ");
//			sql.append(" ORDER BY a.role_level DESC) order by role_level DESC");
//			 
//
//		//sql.append(" FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) ;
//	    CommonMessage.debugMsg(" sql... 12345678 "+sql);
//	    
//	    List<String[]>  improprojshtApproval  = dbActionTemplate.getDataList(sql.toString());
//	    return improprojshtApproval;
//	    
//	}
//	
	
	//-----------------------------------------------------Vignesh 01Oct2025 ------------------------------------//
	
	@Override
	public List<String[]> getImprovementProjSheetkaiexlReportHDData(String kaizId) throws Exception {
		// TODO Auto-generated method stub
		
		    CommonMessage.debugMsg(" kaizId :: "+kaizId);
		    StringBuffer sql = new StringBuffer();
			sql.append(" SELECT KHDM_KAIZENID, SECT_NAME || ' - [' || SECT_CODE || ']' AS SHOP , ");
			sql.append(" CELL_NAME || ' - [' || CELL_CODE || ']' as   CELL  , ");
			sql.append(" DECODE(MCHM_KEYID,KHDM_MACHINEID,MCHM_MACHINENAME || ' - [' || MCHM_MACHINENO || ']','') ");
			sql.append("  AS  EQUIPMENT ,  TO_CHAR(KHDM_TARGETDATE,'DD-MON-YYYY') AS TARGETDATE, ");
			sql.append("  EMPM_CODE AS RESPONSIBILITY, ");
			sql.append(" DECODE (KHDM_STATUS, 'P','PENDING', 'C', 'COMPLETE', 'A', 'PENDING') AS STATUS ");
			sql.append(" FROM ");
			sql.append(" GEN_TL_SECTIONMST , GEN_TL_CELLMST , GEN_TL_MACHINEMST , KZN_TL_HDMST ,GEN_TL_EMPLOYEEMST ");
			sql.append(" WHERE CELL_SECTIONID = SECT_KEYID AND KHDM_CELLID = CELL_KEYID ");
			sql.append(" AND KHDM_MACHINEID = MCHM_KEYID(+) AND KHDM_RESPONSIBILITYID = EMPM_KEYID AND KHDM_REFDOCTYPE='KZN' ");
			sql.append(" AND KHDM_KAIZENID = " + "'" + kaizId +"'" + " ");
			
			List<String[]> improprojshtHDData=dbActionTemplate.getDataList(sql.toString());
			return improprojshtHDData;
	}

	@Override
	public List<String[]> getteamdata(String flid,String kaizId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select sect_name || '\\' || cell_name,COALESCE(mchm_machinename, '-') machine ");
		sql.append(" from gen_vw_fnln,KZN_TL_MST");
		sql.append(" where KZNM_FLID= FNLN_KEYID");
		sql.append(" AND KZNM_KEYID='" + kaizId +"' ");
        //AND FNLN_KEYID='" + flid +"' and KZNM_KEYID='" + kaizId +"' ");
		//select CELL_NAME,KZNM_MACHINEID from gen_vw_fnln,kzn_tl_mst WHERE KZNM_FLID= FNLN_KEYID AND FNLN_KEYID='FNL000000075' and KZNM_KEYID='KZ1400000093';
		CommonMessage.debugMsg(" Inside CommonFunctions :: 1234 "+sql);
	    return dbActionTemplate.getDataList(sql.toString());
	    
	}
	
	@Override
	public List<String[]> getactivitypillar(String kaizId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select TPMP_CODE from GEN_TL_TPMPILLARMST,kzn_tl_mst ");
		sql.append(" where KZNM_ACTIVITYPILLARID=TPMP_KEYID");
		sql.append(" AND KZNM_KEYID='" + kaizId +"' ");
        
		CommonMessage.debugMsg(" Inside CommonFunctions :: 5678 "+sql);
	    return dbActionTemplate.getDataList(sql.toString());
	}

	
	@Override
	public Workbook improvementSmryReportExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("Excel Format in dao mpl");
			rs =   getImprovementReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
	/*		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setDbChkColIndx(1);
			condFormat.setFromCol(1);
			condFormat.setToCol(2);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("C"); //Tick
			condFormat.setSymbolStr("");	
			condFormat.setIdentfier("C");
			condFormat.setBgColor(new RGB(213,255,195));
			condFormats.add(condFormat);
		
			

			XLConditionalFormats condFormatPlan = new XLConditionalFormats();
			condFormatPlan.setFontColor(new RGB(254,0,0)); //red font
			condFormatPlan.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatPlan.setFontHeightPoint((short)14);
			condFormatPlan.setFontBoldWeight((short)20);
			condFormatPlan.setDbChkColIndx(1);
			condFormatPlan.setFromCol(1);
			condFormatPlan.setToCol(2);
			condFormatPlan.setOperator(ComparisonOperator.EQUAL);
			condFormatPlan.setCondValue("A"); 
			condFormatPlan.setSymbolStr("");	
			condFormatPlan.setBgColor(new RGB(253,184,184));
			condFormatPlan.setIdentfier("A");
			condFormats.add(condFormatPlan);
			
			XLConditionalFormats condFormatNA = new XLConditionalFormats();
			//condFormatNA.setFontColor(new RGB(254,0,0)); //red font
			condFormatNA.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatNA.setFontHeightPoint((short)10);
			condFormatNA.setFontBoldWeight((short)10);
			condFormatPlan.setDbChkColIndx(1);
			condFormatNA.setFromCol(1);
			condFormatNA.setToCol(2);
			condFormatNA.setOperator(ComparisonOperator.EQUAL);
			condFormatNA.setCondValue(""); 
			condFormatNA.setSymbolStr("N/A");	
			//condFormatNA.setBgColor(new RGB(192,192,192));
			condFormatNA.setIdentfier("Plan");
			condFormats.add(condFormatNA);
			
			XLConditionalFormats condFormatHD = new XLConditionalFormats();
			condFormatHD.setFontColor(new RGB(254,0,0)); //red font
			condFormatHD.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatHD.setFontHeightPoint((short)14);
			condFormatHD.setFontBoldWeight((short)20);
			condFormatHD.setDbChkColIndx(2);
			condFormatHD.setFromCol(2);
			condFormatHD.setToCol(3);
			condFormatHD.setOperator(ComparisonOperator.EQUAL);
			condFormatHD.setCondValue("C"); //Tick
			condFormatHD.setSymbolStr("");	
			condFormatHD.setIdentfier("C");
			condFormatHD.setBgColor(new RGB(253,253,92));
			condFormats.add(condFormatHD);
		
			

			XLConditionalFormats condFormatPlanHD = new XLConditionalFormats();
			condFormatPlanHD.setFontColor(new RGB(254,0,0)); //red font
			condFormatPlanHD.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatPlanHD.setFontHeightPoint((short)14);
			condFormatPlanHD.setFontBoldWeight((short)20);
			condFormatPlanHD.setDbChkColIndx(2);
			condFormatPlanHD.setFromCol(2);
			condFormatPlanHD.setToCol(3);
			condFormatPlanHD.setOperator(ComparisonOperator.EQUAL);
			condFormatPlanHD.setCondValue("A"); 
			condFormatPlanHD.setSymbolStr("");	
			condFormatPlanHD.setBgColor(new RGB(216,104,248));
			condFormatPlanHD.setIdentfier("A");
			condFormats.add(condFormatPlanHD);
			
			XLConditionalFormats condFormatNAHD = new XLConditionalFormats();
			//condFormatNA.setFontColor(new RGB(254,0,0)); //red font
			condFormatNAHD.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatNAHD.setFontHeightPoint((short)10);
			condFormatNAHD.setFontBoldWeight((short)10);
			condFormatNAHD.setDbChkColIndx(2);
			condFormatNAHD.setFromCol(2);
			condFormatNAHD.setToCol(3);
			condFormatNAHD.setOperator(ComparisonOperator.EQUAL);
			condFormatNAHD.setCondValue(""); 
			condFormatNAHD.setSymbolStr("N/A");	
			//condFormatNA.setBgColor(new RGB(192,192,192));
			condFormatNAHD.setIdentfier("Plan");
			condFormats.add(condFormatNAHD);
			

			
			
			excelUtils.setCondFormats(condFormats);*/			
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}	
	
	private ResultSet getImprovementReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);		
		return dbActionTemplate.dbFunctionCall("KZN_PC_KAIZEN.KZN_FN_IMPROVPROJECTSHEET", paramValues);
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	


	
	
}

