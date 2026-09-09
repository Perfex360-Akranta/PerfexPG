package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlCriticalityassessmentDao;
import com.akranta.tpm.dao.sql.BdmTlCriticalityassessmentSql;
//import com.akranta.tpm.dao.sql.EntTlSelfnominationmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlCriticalityassessment;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.CriticalityAssessmentExcelGenerator;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.CriticalityServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

/* dao implementation */

public class BdmTlCriticalityassessmentDaoImpl implements BdmTlCriticalityassessmentDao {


	private DBActionTemplate dbActionTemplate; 
	private CriticalityServiceApi criticalityseviceapi;
	FunctionCallApi fnCallApi;

	public BdmTlCriticalityassessmentDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void BdmTlCriticalityassessmentDaoImplJwt(String JwtToken) 
	{
		try{
			criticalityseviceapi = new CriticalityServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<String[]> getCriticality() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		String sql1 ="";
	 sql.append("select 'CASM_KEYID','Equipment Code','Equipment','Criteria','Productivity','Quality','Cost','Delivery','Safety','Operatability','Maintainability','Reliability','Total Ratings'" +
	 		" from dual union all" +
	 		" select 'CASM_KEYID','Equipment Code','Equipment','Weightage','20',' 20','15','10','20','5',' 5','5','Total Ratings'" +
	 		" from dual " );
	sql.append(" union all");
	sql.append(" SELECT CASM_KEYID,mchm_machineno , mchm_machinename, casm_criteria,to_char( casm_productivity),to_char( casm_quality), to_char(casm_cost), to_char(casm_delivery), to_char(casm_safety),");
	sql.append(" to_char(casm_operatability), to_cha" +
			"r(casm_maintainability), to_char(casm_reliability), to_char(casm_totalratings)");
	sql.append(" FROM gen_tl_machinemst, bdm_tl_criticalityassessment,gen_tl_functionallocn a,gen_tl_functionallocn b");
	sql.append(" WHERE mchm_keyid = casm_equipmentid(+) AND b.fnln_keyid = mchm_flid AND a.fnln_parentid = b.fnln_elementid ");
	sql.append(" AND a.fnln_keyid = '");
	sql1 = sql.toString();
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql1);
	CommonMessage.debugMsg("Grid value" + gridData.get(1));
	return gridData;
	}
	
	@Override
	public List<String[]> getCriticalityfillgriddata(CommonFilter commonFilter1,String flid,String crytype) throws Exception {
		CommonMessage.debugMsg("TrainingGrid::::: IP:::");
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getETRelatedStr(commonFilter1);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter1); 
		CommonMessage.debugMsg("test to getTrainingGrid.....");	
		
		String tadeid = commonFilter1.getChkTrade();
		if(UIUtils.isValidKeyId(tadeid) && UIUtils.isValidKeyId(crytype) )
		{
			paramValues.add(condParms+"FLID="+flid+";"+"TRADEID="+tadeid+";"+"crytype="+crytype+";");
		}
		else if (UIUtils.isValidKeyId(tadeid))
		{
			paramValues.add(condParms+"FLID="+flid+";"+"TRADEID="+tadeid+";");
			
		}else
		{
			paramValues.add(condParms+"FLID="+flid+";");
		}
		paramValues.add(commonParams);
		//List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CRITICALITYASSESSMENT", paramValues);
	
		List<String[]> dataList =  fnCallApi.callFunction("PLM_FN_CRITICALITYASSESSMENT_SB", paramValues,4,false);
		
		
		if( commonFilter1.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter1.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
		
	}
	
	
	
		
	public List<BdmTlCriticalityassessment> create(List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment) throws Exception {

		CommonMessage.debugMsg("DaoIMPL");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BdmTlCriticalityassessmentSql bdmTlCriticalityassessmentSql = new BdmTlCriticalityassessmentSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			CommonMessage.debugMsg("DaoIMPL1");
			if (newBdmTlCriticalityassessment!= null && newBdmTlCriticalityassessment.size() > 0) 
			{CommonMessage.debugMsg("DaoIMPL2");
				for( BdmTlCriticalityassessment criticalityassessement :newBdmTlCriticalityassessment)
				{  
					CommonMessage.debugMsg("DaoIMPL3");   
					String dateTime = CommonFunctions.getDate();
					//BDM_TL_MCHRANKSKILLHISTORY   First Delete Record based on date for machine
				
					 String Eqpid1=criticalityassessement.getCasmEquipmentid();
					 CommonMessage.debugMsg(" Inside DaoImpl Outside if "+Eqpid1);
					 //sqls.add(" DELETE from  BDM_TL_MCHRANKSKILLHISTORY where MRSH_EQUIPMENTID ='"+Eqpid1+"' and trunc(MRSH_DATE) ='"+dateTime+"'");
					//mano
					 sqls.add("DELETE FROM BDM_TL_MCHRANKSKILLHISTORY WHERE MRSH_EQUIPMENTID = '" + Eqpid1 + "' AND DATE(MRSH_DATE) = '" + dateTime + "'");
					if(criticalityassessement.getCasmKeyid()== null){
						
						criticalityassessement.setCasmKeyid(dbActionTemplate.getSequenceNumber("TBL_BDM_TL_CRITICALITYASSESSMENT",8,"CAM","","Y")); // set the sequnce number 
					    sqls.add(BdmTlCriticalityassessmentSql.getInsertSql(bdmTlCriticalityassessmentSql.getCasmDbFields(), criticalityassessement.getSaveArray())); // add insert sql for master table
					    
					    //CommonMessage.debugMsg(" Inside Dao Impl 1 Totalratings "+criticalityassessement.getCasmTotalratings());
					    CommonMessage.debugMsg(" Inside Dao Impl 2 Equipment id "+criticalityassessement.getCasmEquipmentid());
					    CommonMessage.debugMsg(" Inside Dao Impl 3 Flid         "+criticalityassessement.getCasmFlid());
					  
					    String Totalratings=criticalityassessement.getCasmTotalRating();
					    String Eqpid=criticalityassessement.getCasmEquipmentid();
					    String tradeid = criticalityassessement.getCasmTradeid();
					    
					    CommonMessage.debugMsg(" Inside DaoImpl Inside if "+Eqpid1);
					    //StringBuffer sql1 = BdmTlCriticalityassessmentSql.getmachinerank(Totalratings);
					    StringBuffer sql1 = BdmTlCriticalityassessmentSql.getCriteria(criticalityassessement.getCasmFlid(), Totalratings , Eqpid,tradeid);
					    CommonMessage.debugMsg(" Inside DaoImpl to get Machine Rank Values "+sql1);
					    String machRank =dbActionTemplate.getSingleValue(sql1.toString());
					    CommonMessage.debugMsg(" machRank    "+machRank);
					    
					    String Keyid= dbActionTemplate.getSequenceNumber("TBL_BDM_TL_MCHRANKSKILLHISTORY",8,"MRS","","Y");
					    
					    sqls.add("insert into BDM_TL_MCHRANKSKILLHISTORY (MRSH_KEYID,MRSH_EQUIPMENTID,MRSH_RATINGS,MRSH_RANK,MRSH_DATE,MRSH_TEMPFIELD1,MRSH_CREATEDBY,MRSH_ACTIVE,MRSH_CREATEDON,MRSH_MODIFIEDON)  values('"+Keyid+"','"+Eqpid+"','"+Totalratings+"','"+machRank+"',to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS'),'-','USR0001','Y',to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS'),to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS') )");
					    
					    
					    //sqls.add(BdmTlMchrankskillhistorySql.getInsertSql(bdmTlMchrankskillhistorySql.getMrshDbFields(), bdmTlMchrankskillhistory.getSaveArray())); // add insert sql for master table
					
					    /*
					     * bdmTlMchrankskillhistory.setMrshKeyid(dbActionTemplate.getSequenceNumber(BdmTlMchrankskillhistorySql.TBL_BDM_TL_MCHRANKSKILLHISTORY)); // set the sequnce number 
			sqls.add(BdmTlMchrankskillhistorySql.getInsertSql(bdmTlMchrankskillhistorySql.getMrshDbFields(), bdmTlMchrankskillhistory.getSaveArray())); // add insert sql for master table
					     */
					
					
					}
					else
					{
						sqls.add(BdmTlCriticalityassessmentSql.getUpdateSql(bdmTlCriticalityassessmentSql.getCasmDbFields(), criticalityassessement.getSaveArray()));
						
						String Totalratings=criticalityassessement.getCasmTotalRating();
					    String Eqpid=criticalityassessement.getCasmEquipmentid();
					    
					    String Tradeid=criticalityassessement.getCasmTradeid();
					    
					    CommonMessage.debugMsg(" Inside DaoImpl Inside if "+Eqpid1);
					    //StringBuffer sql1 = BdmTlCriticalityassessmentSql.getmachinerank(Totalratings);
					    StringBuffer sql1 = BdmTlCriticalityassessmentSql.getCriteria(criticalityassessement.getCasmFlid(), Totalratings,Eqpid,Tradeid);
					    CommonMessage.debugMsg(" Inside DaoImpl to get Machine Rank Values "+sql1);
					    String machRank =dbActionTemplate.getSingleValue(sql1.toString());
					    CommonMessage.debugMsg(" machRank    "+machRank);
					    
					    String Keyid= dbActionTemplate.getSequenceNumber("TBL_BDM_TL_MCHRANKSKILLHISTORY",8,"MRS","","Y");
					    
					    sqls.add("insert into BDM_TL_MCHRANKSKILLHISTORY (MRSH_KEYID,MRSH_EQUIPMENTID,MRSH_RATINGS,MRSH_RANK,MRSH_DATE,MRSH_TEMPFIELD1,MRSH_CREATEDBY,MRSH_ACTIVE,MRSH_CREATEDON,MRSH_MODIFIEDON)  values('"+Keyid+"','"+Eqpid+"','"+Totalratings+"','"+machRank+"',to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS'),'-','USR0001','Y',to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS'),to_date('"+dateTime+"','DD-MON-YYYY HH24:MI:SS') )");
					}
				}
				
			         dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
			}
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newBdmTlCriticalityassessment;
	}
	
	public List<BdmTlCriticalityassessment> update(List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment,List<BdmTlCriticalityassessment> existBdmTlCriticalityassessment)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BdmTlCriticalityassessmentSql bdmTlCriticalityassessmentSql = new BdmTlCriticalityassessmentSql();
		try {
			if (newBdmTlCriticalityassessment!= null && newBdmTlCriticalityassessment.size() > 0) 
			{
				for( BdmTlCriticalityassessment Criticalityassessement1 :newBdmTlCriticalityassessment)
				{
					sqls.add(BdmTlCriticalityassessmentSql.getUpdateSql(bdmTlCriticalityassessmentSql.getCasmDbFields(), Criticalityassessement1.getSaveArray()));
					
					dbActionTemplate.executeStatements(sqls);
			
				}
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return newBdmTlCriticalityassessment;
	}
	@Override
	public BdmTlCriticalityassessment deleteCritical(BdmTlCriticalityassessment oldBdmTlCriticalityassessment)throws Exception {
		List<String> sqls = new ArrayList<String>();
		BdmTlCriticalityassessmentSql bdmTlCriticalityassessmentSql = new BdmTlCriticalityassessmentSql();
		try {
			
			sqls.add(bdmTlCriticalityassessmentSql.getDeleteSql(bdmTlCriticalityassessmentSql.getCasmDbFields(), oldBdmTlCriticalityassessment.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return oldBdmTlCriticalityassessment;
	}

	@Override
	public Workbook getCriticalassmntExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		  ResultSet rs = null;
			 try{	
					rs =   getControlresplanResultSet(commonFilter);
					ExcelUtils excelUtils = new ExcelUtils(colmodel);					
					return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
			}
	
	private ResultSet getControlresplanResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_CRITIASSESSMENTMAINGRID", paramValues);
   
	
	}

	@Override
	public List<String[]> getCriAssMainGrid(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		//List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_CRITIASSESSMENTMAINGRID", paramValues);
		List<String[]> dataList =  fnCallApi.callFunction("PLM_FN_CRITIASSESSMENTMAINGRID_SB", paramValues,3,true);
		
		
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	
	
	/*@Override
	public List<BdmTlCriticalityassessment> create(
			List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BdmTlCriticalityassessment> update(
			List<BdmTlCriticalityassessment> newBdmTlCriticalityassessment) {
		// TODO Auto-generated method stub
		return null;
	}*/

	
	@Override
	public String getElementID(String flid) throws Exception {
		String sql = "SELECT FNLN_ELEMENTID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID= '"+flid+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}

	@Override
	public String DeleteCriteriaList(String criteriaDeleteList) {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try{
			if(UIUtils.isValidKeyId(criteriaDeleteList)){	
				StringBuffer sql1 = new StringBuffer();
				//criteriaDeleteList=criteriaDeleteList.replace("[\"", "'").replace("\"]","'");	
				criteriaDeleteList=criteriaDeleteList.replaceAll("\"", "'");
				CommonMessage.debugMsg("criteriaDeleteList "+criteriaDeleteList);
				sql1.append(" DELETE FROM BDM_TL_CRITICALITYASSESSMENT");
				sql1.append(" WHERE CASM_KEYID in (" + criteriaDeleteList + ") ");	
				sqls.add(sql1.toString());
			}	
			dbActionTemplate.executeStatements(sqls);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return criteriaDeleteList;
	}

	@Override
	public String DeleteFlidListMst(String criteriaDeleteFlidList) {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		try{
			if(UIUtils.isValidKeyId(criteriaDeleteFlidList)){	
				StringBuffer sql1 = new StringBuffer();
				//criteriaDeleteFlidList=criteriaDeleteFlidList.replace("[\"", "'").replace("\"]","'");
				//criteriaDeleteFlidList=criteriaDeleteFlidList.replaceAll("\",\"", "','");
				
				sql1.append(" DELETE FROM BDM_TL_CRITICALITYASSESSMENT");
				sql1.append(" WHERE CASM_FLID='"+criteriaDeleteFlidList+"' ");	
				sqls.add(sql1.toString());
			}	
			dbActionTemplate.executeStatements(sqls);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return criteriaDeleteFlidList;
	}

	@Override
	public List<String[]> getCriticalassmntcritria( String flid , String equm, String total,String trade ) throws Exception {
		 StringBuffer sql1 = BdmTlCriticalityassessmentSql.getCriteria( flid , total , equm ,trade);
		 CommonMessage.debugMsg(" Inside DaoImpl to get Machine Rank Values "+sql1);
		return dbActionTemplate.getDataList(sql1.toString());
	}

	@Override
	public String getCriticalassmntremarks(String flid, String equm)
			throws Exception {
		String sql = " Select distinct CASM_REMARKS from BDM_TL_CRITICALITYASSESSMENT where CASM_FLID = '"+ flid +"' and CASM_EQUIPMENTID = '"+ equm +"'" ;
		CommonMessage.debugMsg(" Inside DaoImpl to get Machine remarks "+sql);			
		return dbActionTemplate.getSingleValue(sql);
	}
	
	@Override
	public List<String[]> getcriticalReport(CommonFilter commonFilter)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonMessage.debugMsg("test to............");
		/*if(UIUtils.isValidKeyId(flid))
			condParms +=";Flid="+flid;*/
		
	    
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("PLM_PC_PLANNEDMAINT.PLM_FN_CRITICALREPORT", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public String getFlid(String parentFlid) throws Exception {
		
	
		
		StringBuilder Sql=new StringBuilder();
		Sql.append( "SELECT FLID  FROM  GEN_MV_FLIDHIERARCHY where PARENTS = '"+parentFlid+"'");		
		
		String flid =dbActionTemplate.getSingleValue(Sql.toString());
		
	
		return flid;	
	}

	@Override
	public Workbook getcriticalReporcExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		 try
		 {
				rs =   getcriticalReporcExcel(commonFilter);
				
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				//List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				/*XLConditionalFormats condFormat = new XLConditionalFormats();
				//condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)104);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(103);
				condFormat.setToCol(-10);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				CommonMessage.debugMsg("daoimpl22222");*/
				return excelUtils.writeToExcel(rs,format,2,0,0 );
				
				
			   }finally{
				   
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}

	private ResultSet getcriticalReporcExcel(CommonFilter commonFilter) throws Exception {
		commonFilter.setFromRow("");
		commonFilter.setToRow("");
		
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_CRITICALREPORT", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
	
	// Add this method to your DAO implementation class
	@Override
	public Workbook getCriticalityAssessmentMstExcel(JSONObject colmodel, String format, 
	        CommonFilter commonFilter) throws Exception {
	    ResultSet rs = null;
	    try {
	        rs = getCriticalityAssessmentResultSet(commonFilter);
	        
	        // Initialize the custom generator
	        CriticalityAssessmentExcelGenerator excelGenerator = 
	            new CriticalityAssessmentExcelGenerator(colmodel);
	        
	        // Generate and return the workbook
	        return excelGenerator.writeToExcel(rs, format, 3, 0, 0);
	        
	    } finally {
	        if (rs != null) {
	            DBActionTemplate.closeConnection(rs, null, null, null, 
	                rs.getStatement().getConnection());
	        }
	    }
	}
	private ResultSet getCriticalityAssessmentResultSet(CommonFilter commonFilter) throws Exception {
	    List<String> paramValues = new ArrayList<String>();
	    String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    paramValues.add(condParms);
	    paramValues.add(commonParams);
	    return dbActionTemplate.NewdbFunctionCall2("PLM_FN_CRITICALITYASSESSMENT", paramValues);
	}
}

