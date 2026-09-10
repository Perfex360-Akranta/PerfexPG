package com.akranta.tpm.dao.impl;


import com.akranta.tpm.service.api.BalWorespServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.SequenceNumGenException;
import com.akranta.tpm.Exceptions.WoResponsibilityExpection;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlPlanconfigurationDao;
import com.akranta.tpm.dao.sql.BAL_PlmTlPlanconfigurationSql;
import com.akranta.tpm.dao.sql.PcsTlProductionplanSql;
//import com.akranta.tpm.dao.sql.PlmTlPlanconfigurationSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PlmTlPlanconfigurationDaoImpl implements BAL_PlmTlPlanconfigurationDao {

	private BalWorespServiceApi balWorespServiceApi; 
	FunctionCallApi fnCallApi;
	
	
	private DBActionTemplate dbActionTemplate; 
	BAL_PlmTlPlanconfigurationSql plmTlPlanconfigurationsql ; 
	public BAL_PlmTlPlanconfigurationDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		
		plmTlPlanconfigurationsql = new BAL_PlmTlPlanconfigurationSql(); 
		
 
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_PlmTlPlanconfigurationDaoImplJwt(String jwtToken) { 
		 try { 
		 balWorespServiceApi = new BalWorespServiceApi(jwtToken); 
		 fnCallApi = new FunctionCallApi(jwtToken); 
		 } 
		 catch (Exception e) { 
			 CommonFunctions.debugMsg("getdataPlanConfig error: " + e.getMessage());
		 e.printStackTrace(); 
		 } 
		 }
	
	@Override
	public BAL_PlmTlPlanconfiguration create(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration)throws BusinessApplicationExceptions, SequenceNumGenException, WoResponsibilityExpection {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		// contains dbtable,field names, Field types and related sqls  of master table
		StringBuilder sql = new StringBuilder();
	
			 if(plmTlPlanconfiguration.getPplcLevel().equals("A")){
				sql.append("DELETE FROM ") .append(TableNames.TBL_BAL_PLM_TL_PLANCONFIGURATION );
				sql.append(" WHERE  PPLC_MACHINEID IN ('" ).append( plmTlPlanconfiguration.getPplcMachineid() ).append( "') AND  " );
				sql.append(" PPLC_ASSEMBLYID <> '{}' AND PPLC_LEVEL ='A' ");
			}
			 else if(plmTlPlanconfiguration.getPplcLevel().equals("M")){
				 sql.append( "DELETE FROM " ).append( TableNames.TBL_BAL_PLM_TL_PLANCONFIGURATION );
				 sql.append(" WHERE  PPLC_MACHINEID IN ('" ).append( plmTlPlanconfiguration.getPplcMachineid() ).append( "') AND  " );
				 sql.append(" PPLC_ASSEMBLYID = '{}' AND PPLC_LEVEL ='M' ");
			}
			 CommonFunctions.debugMsg("DeleteData..  :"+sql);
			// dbActionTemplate.executeStatement(sql);
			 sqls.add(sql.toString());
			
			try{ 
				plmTlPlanconfiguration.setPplcKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlPlanconfigurationSql.TBL_BAL_PLM_TL_PLANCONFIGURATION, 15, "PPC", "MMYY", null)); // set the sequnce number
			}catch(Exception e){
				throw new SequenceNumGenException(BAL_PlmTlPlanconfigurationSql.TBL_BAL_PLM_TL_PLANCONFIGURATION,e.getCause());
			}
			sqls.add(BAL_PlmTlPlanconfigurationSql.getInsertSql(plmTlPlanconfigurationsql.getPplcDbFields(), plmTlPlanconfiguration.getSaveArray())); // add insert sql for master table

			//List<PlmTlWorespmst>  workRespMstList = plmTlPlanconfiguration.getWoRespMast();
			
		//	List<PlmTlWorespdtl>  workRespdtlList = plmTlPlanconfiguration.getWoRespDetail();
			
			//String sqlPm = null;
			StringBuilder sqlPm = new StringBuilder("SELECT DISTINCT PMSD_FREQUENCYUNIT,PMSD_FREQUENCY FROM ").append(TableNames.TBL_BAL_PLM_TL_STANDARDS).append( " WHERE PMSD_MACHINEID = '").append( plmTlPlanconfiguration.getPplcMachineid()).append("'");
			List<String[]> frequncies = null;
			try{
				frequncies =dbActionTemplate.getDataList(sqlPm.toString());//DataList(sqlPm);
				//getFreqFrmPmstd =dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_STANDARDS, "PMSD_FREQUENCYUNIT", "PMSD_MACHINEID", plmTlPlanconfiguration.getPplcMachineid());//DataList(sqlPm);
				for(String [] frequncy : frequncies){
					String freqUnit = frequncy[0];
					String freq = frequncy[1];
					if(UIUtils.isValidKeyId(freqUnit)){
						//String getFrqFrmPmstd = null;
						String getEffdate = null;
						//getFrqFrmPmstd = freqUnit.get(0)[0];
						if(freqUnit.equals("W") || freqUnit.equals("F") || freqUnit.equals("M")){
	
							 getEffdate= plmTlPlanconfiguration.getPplcMonthly();
						}
						else if(freqUnit.equals("Q")){
							getEffdate= plmTlPlanconfiguration.getPplcQuarterly();
						}
						else if(freqUnit.equals("H")){
							getEffdate= plmTlPlanconfiguration.getPplcHalfyearly();
						}
						else if(freqUnit.equals("Y") && CommonFunctions.isValidKeyId(freq)){
							switch(Integer.parseInt(freq)){
								case 2 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly2();
									break;
								case 3 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly3();
									break;
								case 4 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly4();
									break;
								case 5:
									getEffdate= plmTlPlanconfiguration.getPplcYearly5();
									break;
								case 6 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly6();
									break;
								case 7 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly7();
									break;
								case 8 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly8();
									break;
								case 9 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly9();
									break;
								case 10 :
									getEffdate= plmTlPlanconfiguration.getPplcYearly10();
									break;	
								default :
									getEffdate= plmTlPlanconfiguration.getPplcYearly();
									break;		
							}
						}
						
						else
							getEffdate= plmTlPlanconfiguration.getPplcMonthly();
						
						
						
						StringBuilder sqlUp = new StringBuilder(" UPDATE BAL_PLM_TL_STANDARDS SET PMSD_EFFECTIVEDATE = '").append(getEffdate).append("'");
						sqlUp.append(" ,PMSD_MONTHWEEKNO = '").append(plmTlPlanconfiguration.getPplcWeekno()).append("', PMSD_MODIFIEDON = SYSDATE ,PMSD_PLANCONFIGSTATUS ='Y' " );
						sqlUp.append(" WHERE  PMSD_MACHINEID = '" ).append(plmTlPlanconfiguration.getPplcMachineid()).append( "' " );
						sqlUp.append(" AND PMSD_ACTIVE ='Y' AND PMSD_FREQUENCYUNIT = '" ).append( freqUnit ).append("'");
						
					
						if(CommonFunctions.isValidKeyId(plmTlPlanconfiguration.getPplcAssemblyid() ))
							sqlUp.append( " AND PMSD_ASSEMBLYID ='").append( plmTlPlanconfiguration.getPplcAssemblyid() ).append( "'");
					
						if(freqUnit.equals("Y") && CommonFunctions.isValidKeyId(freq) )
							sqlUp.append( " AND PMSD_FREQUENCY ='").append( freq ).append("'");
						
						sqls.add(sqlUp.toString());
					}
				}
			}catch(Exception e){
				
			}
		
			StringBuilder sqlUpCal = new StringBuilder(" UPDATE  BAL_PLM_TL_CALENDAR SET PMCL_ACTIVE = 'N',PMCL_MODIFIEDON = CURRENT_TIMESTAMP  " );
			sqlUpCal.append(" WHERE  PMCL_MACHINEID = '").append(plmTlPlanconfiguration.getPplcMachineid()).append( "' AND PMCL_PMFREQ IN ('W','F','M','Q','H','Y') " );
			sqlUpCal.append(" AND PMCL_STATUS IN ('X','A') AND PMCL_ACTIVE='Y'  ");
			
			if(UIUtils.isValidKeyId(plmTlPlanconfiguration.getPplcAssemblyid() ))
				sqlUpCal.append( " AND PMCL_ASSEMBLYID ='").append( plmTlPlanconfiguration.getPplcAssemblyid() ).append( "'");		
			
			
			sqls.add(sqlUpCal.toString());
			
			
			String woRespData= null;
			try{
			    dbActionTemplate.executeStatements(sqls);
			    StringBuilder sqlStd = new StringBuilder("select pmsd_keyid from ").append( TableNames.TBL_BAL_PLM_TL_STANDARDS+" where pmsd_machineid = '").append( plmTlPlanconfiguration.getPplcMachineid() ).append("'");
			    String pmsdKeyId = dbActionTemplate.getSingleValue(sqlStd.toString());
			    
			    if(UIUtils.isValidKeyId(pmsdKeyId))
			    	generateCalendar(plmTlPlanconfiguration.getPplcYearly().substring(7,11), plmTlPlanconfiguration.getPplcFactoryid(), plmTlPlanconfiguration.getPplcSectionid(), plmTlPlanconfiguration.getPplcCellid(), plmTlPlanconfiguration.getPplcMachineid(),plmTlPlanconfiguration.getPplcAssemblyid(), plmTlPlanconfiguration.getPplcFrequency(),plmTlPlanconfiguration.getPplcYearly().substring(3,6));
			    
			    StringBuilder sqlWo = new StringBuilder("SELECT COUNT ( PWRM_MACHINEID) FROM " ).append(TableNames.TBL_BAL_PLM_TL_WORESPMST ).append(" WHERE PWRM_MACHINEID = '" ).append( plmTlPlanconfiguration.getPplcMachineid() ).append( "'");
				
			    woRespData = dbActionTemplate.getSingleValue(sqlWo.toString());

				if( Integer.parseInt(woRespData)== 0 || woRespData == null )
					throw new WoResponsibilityExpection("No Work Order Responsibility entry for the Equipment, Do you want to open?");
			}catch(Exception e){
				CommonFunctions.debugMsg("dao impl  :"+e.getMessage());
				if( Integer.parseInt(woRespData)== 0 || woRespData == null ){
				throw new WoResponsibilityExpection(e.getMessage());
				}
				
				throw new BusinessApplicationExceptions(e.getMessage());
			}
			
	   
	
		return plmTlPlanconfiguration;
	}
	
	@Override
	public BAL_PlmTlPlanconfiguration update(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration)	throws Exception { 
		System.out.println("update Dao impl");
		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlPlanconfigurationSql plmTlPlanconfigurationSql = new BAL_PlmTlPlanconfigurationSql();
		try {

			sqls.add(BAL_PlmTlPlanconfigurationSql.getUpdateSql(plmTlPlanconfigurationSql.getPplcDbFields(), plmTlPlanconfiguration.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return plmTlPlanconfiguration;
	}
	@Override
	public BAL_PlmTlPlanconfiguration delete(BAL_PlmTlPlanconfiguration plmTlPlanconfiguration)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlPlanconfigurationSql plmTlPlanconfigurationsql = new BAL_PlmTlPlanconfigurationSql();
		try {
			
			sqls.add(BAL_PlmTlPlanconfigurationSql.getDeleteSql(plmTlPlanconfigurationsql.getPplcDbFields(), plmTlPlanconfiguration.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlPlanconfiguration;
	}

	@Override
	public List<String[]> getdataPlanConfig(String factId,String machId, String cellId,String chkValue) {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(chkValue);
			paramValues.add(cellId);
			paramValues.add(machId);
			System.out.println("paramValues  "+paramValues);
			//System.out.println("fnCallApi is null? " + (fnCallApi == null));
			//List<String[]> planConfigList = dbActionTemplate.processFunctionCalls("PLM_FN_GETPMPLANCONFIG",paramValues);
			List<String[]> planConfigList = fnCallApi.callMultiParamFunction("PLM_FN_GETPMPLANCONFIG_SB",paramValues,1,true);
			//return "PLM_PC_PLANNEDMAINT.PLM_FN_GETPMPLANCONFIG";

			System.out.println("planConfigList  :"+planConfigList);
			return planConfigList;
		
		}
		catch (Exception e)
		{
			CommonFunctions.debugMsg("getdataPlanConfig ERROR: " + e.getMessage()); 
			//e.getMessage();
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public List<String[]> getYearHirerachy(String selYear) throws Exception {
		// TODO Auto-generated method stub
		String sql= BAL_PlmTlPlanconfigurationSql.getyearhirerachy(selYear);
		List<String[]> planConfigList = dbActionTemplate.getDataList(sql);
		CommonFunctions.debugMsg("planConfigListplanConfigList-#     :"+planConfigList.size());
		return planConfigList;
	}

	@Override
	public List<String[]> getwoRespGriddata(String workrepmstkeyid) {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(workrepmstkeyid);
			/*paramValues.add(sectId);
			paramValues.add(cellid);
			paramValues.add(costcenterid);
			paramValues.add(workrepmachineId);
			paramValues.add(optedValue);*/
			//String sql = PlmTlPlanconfigurationSql.getWORespListSql(workrepmstkeyid);
			CommonFunctions.debugMsg("paramValues     :"+paramValues);
			//List<String[]> planConfigList =dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_FILLMCHFORPMWORESP",paramValues);
			//List<String[]> planConfigList =dbActionTemplate.processFunctionCalls("PLM_FN_FILLMCHFORPMWORESP",paramValues);
			List<String[]> planConfigList = fnCallApi.callMultiParamFunction("PLM_FN_FILLMCHFORPMWORESP_SB", paramValues, 1, true);
				//dbActionTemplate.getDataList(sql);
			
			System.out.println("planConfigList  :"+planConfigList);
			return planConfigList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getwoRespGridadd(String workrepmstkeyid) {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			String sql = BAL_PlmTlPlanconfigurationSql.getWORespListSql(workrepmstkeyid);
			List<String[]> planConfigList = dbActionTemplate.getDataList(sql);
			//processFunctionCalls(PlmTlPlanconfigurationSql.getWORespListSql(workrepmstkeyid),paramValues);
			System.out.println("planConfigList  :"+planConfigList);
			return planConfigList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}
	public List<String[]> getPlanEntry(String machineId,String entryDate,String productId,CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		try
		{
			String sql = PcsTlProductionplanSql.getPlanEntrySql(machineId,entryDate,productId,commonFilter,false);
			List<String[]> planConfigList = dbActionTemplate.getDataList(sql);	
			if( commonFilter.getViewClick() == 'Y'){
				String count = PcsTlProductionplanSql.getPlanEntrySql(machineId,entryDate,productId,commonFilter,true);
				List<String[]> countList = dbActionTemplate.getDataList(count);	
				if(countList.size()>0)
				{
					String totalCnt = countList.get(0)[0];
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
			}
			return planConfigList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}
	@Override
	public BAL_PlmTlPlanconfiguration getplanConfigdata(String planConfigKey) throws  Exception {
		// TODO Auto-generated method stub
		BAL_PlmTlPlanconfiguration plmTlPlanconfiguration = new BAL_PlmTlPlanconfiguration();
		String sql = BAL_PlmTlPlanconfigurationSql.getplanconfigListSql(planConfigKey);
		System.out.println("in dao impl" );
		Object args [] = new Object [] { planConfigKey };
		plmTlPlanconfiguration.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return plmTlPlanconfiguration;
		
	}

	@Override
	public void generateCalendar(String substring, String pplcFactoryid,
			String pplcSectionid, String pplcCellid, String pplcMachineid,
			String pplcAssemblyid, String pplcFrequency,String fromMonth) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inside generate planConfig function daoImpl" + substring);
		List<String> inParamValues = new ArrayList<String>();
		inParamValues.add(substring);
		inParamValues.add(pplcFactoryid);
		inParamValues.add(pplcSectionid);
		
		inParamValues.add(pplcCellid);
		inParamValues.add(pplcMachineid);
		inParamValues.add(pplcAssemblyid);
		
		inParamValues.add(pplcFrequency);
		inParamValues.add("MCH");
		inParamValues.add("{}");
		inParamValues.add("{}");
		inParamValues.add(fromMonth);
		System.out.println("inParamValues  :"+inParamValues);
		
		 
		Object [] outParams = new Object[ 1 ];
		System.out.println("PLM_PR_ANNUALCALGEN   :"+outParams);
		dbActionTemplate.processPLSQLProcedures("PLM_PR_ANNUALCALGEN_NEW",inParamValues,outParams);
		System.out.println("outParamsAfter   :"+outParams);
	}

	@Override
	public void insertAllMach(BAL_PlmTlPlanconfiguration newPlmTlPlanconfiguration,String string) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("Inside daoimpl");
			List<String> inParamValues = new ArrayList<String>();	
			/*
			  vCELLID VARCHAR2,vLEVEL VARCHAR2,vWKNO VARCHAR2,vM VARCHAR2, 
                vQ VARCHAR2, vH VARCHAR2,vY1 VARCHAR2, vY2 VARCHAR2, 
                vY3 VARCHAR2, vY4 VARCHAR2, vY5 VARCHAR2, vY6 VARCHAR2,
                vY7 VARCHAR2, vY8 VARCHAR2, vY9 VARCHAR2, vY10 VARCHAR2,
                vFREQ VARCHAR2, vASSMID VARCHAR2*/
			
			if(string.equals("M"))
			inParamValues.add(newPlmTlPlanconfiguration.getPplcCellid());
			else if(string.equals("A"))
				inParamValues.add(newPlmTlPlanconfiguration.getPplcMachineid());
			
			inParamValues.add(newPlmTlPlanconfiguration.getPplcLevel());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcWeekno());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcMonthly());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcQuarterly());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcHalfyearly());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly2());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly3());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly4());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly5());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly6());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly7());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly8());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly9());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcYearly10());
			inParamValues.add(newPlmTlPlanconfiguration.getPplcFrequency());
			//if(string.equals("M"))
			//inParamValues.add(newPlmTlPlanconfiguration.getPplcAssemblyid());
			CommonFunctions.debugMsg("Apply To All DAOIMPL   :"+string);			
			System.out.println("inParamValues:"+inParamValues);	
			  //List<String[]> insertAll = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PLANCONFIGBULKINS", inParamValues);
			  Object [] outParams = new Object[ 1 ];
				System.out.println("outParams   :"+outParams);
				if(string.equals("M"))
					dbActionTemplate.processPLSQLProcedures("PLM_PC_PLANNEDMAINT.PLM_PR_PLANCONFIGBULKINS",inParamValues,outParams);
				else if(string.equals("A"))
					dbActionTemplate.processPLSQLProcedures("PLM_PC_PLANNEDMAINT.PLM_PR_PLANCONFIGBULKINSASM",inParamValues,outParams);
				System.out.println("outParamsAfter   :"+outParams.length);
                if(outParams.length>0){
                	throw new Exception(" Data Saved Successfully ");
                }
                else
                	throw new Exception(" Data Not Saved ");
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}

	}

	
	
	
	
}

