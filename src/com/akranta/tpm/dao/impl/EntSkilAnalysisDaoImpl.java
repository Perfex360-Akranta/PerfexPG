package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.FilterValues;
import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.CustomerComplaintStmtRptDao;
import com.akranta.tpm.dao.EntSkillAnalysisDao;
import com.akranta.tpm.dao.EntTlTrainingHoursDao;
import com.akranta.tpm.dao.sql.EntTlMultiskilldialymapSql;
import com.akranta.tpm.dao.sql.EntTlMultiskillempmapSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.CommonFilterTraining;
import com.akranta.tpm.model.EntTlMultiskilldialymap;
import com.akranta.tpm.model.EntTlMultiskillempmap;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
public class EntSkilAnalysisDaoImpl implements  EntSkillAnalysisDao {	
	
	private DBActionTemplate dbActionTemplate; 
	
	public EntSkilAnalysisDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> getSkillGapAnalysis(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms = FilterCondSql.getETRelatedStr(commonFilter);
			condParms=condParms.replace("`", "");
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentid1())){
				condParms+="TRARLOCN="+commonFilterTraining.gettrarparentid1()+";";
			}
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentid2())){
				condParms+="TRARCLASS="+commonFilterTraining.gettrarparentid2()+";";
			} 
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentidunt3())){
				condParms+="TRARUNT="+commonFilterTraining.gettrarparentidunt3()+";";
			} 
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentiddpt3())){
				condParms+="TRARDPT="+commonFilterTraining.gettrarparentiddpt3()+";";
			}
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentidfun4())){
				condParms+="TRARFUN="+commonFilterTraining.gettrarparentidfun4()+";";
			}
			
			if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentiddpt3())){
				condParms+="TRARPRO="+commonFilterTraining.gettrarparentidpro4()+";";
			}
			if(CommonFunctions.isValidKeyId(commonFilterTraining.getDrillLevel())){
				condParms+="DRILLLEVELID="+commonFilterTraining.getDrillLevel()+";";
			}
			
			//condParms+="";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("commonFilter.getViewClick():"+commonFilter.getViewClick());
			List<String[]> rootRptList;
			rootRptList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_SKILLGAPANALYSIS", paramValues);	
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
			 }
		
			return rootRptList	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}
	@Override
	public Workbook getSkillGapAnalysisExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,CommonFilterTraining commonFilterTraining) throws Exception {
		   ResultSet rs = null;
		   try{
			
			rs =   getSkillGapResultSet(commonFilter,commonFilterTraining);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0);
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	}
	private ResultSet getSkillGapResultSet(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter,commonFilterTraining);
		
		return dbActionTemplate.dbFunctionCall("ENT_PC_EDUANDTRAINING.ENT_FN_SKILLGAPANALYSIS", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter,CommonFilterTraining commonFilterTraining){
		
		List<String> paramValues = new ArrayList<String>();			
		String condParms = FilterCondSql.getETRelatedStr(commonFilter);
		condParms=condParms.replace("`", "");
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentid1())){
			condParms+="TRARLOCN="+commonFilterTraining.gettrarparentid1()+";";
		}
		
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentid2())){
			condParms+="TRARCLASS="+commonFilterTraining.gettrarparentid2()+";";
		} 
		
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentidunt3())){
			condParms+="TRAUNT="+commonFilterTraining.gettrarparentidunt3()+";";
		} 
		
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentiddpt3())){
			condParms+="TRADPT="+commonFilterTraining.gettrarparentiddpt3()+";";
		}
		
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentidfun4())){
			condParms+="TRAFUN="+commonFilterTraining.gettrarparentidfun4()+";";
		}
		
		if(CommonFunctions.isValidKeyId(commonFilterTraining.gettrarparentiddpt3())){
			condParms+="TRARPRO="+commonFilterTraining.gettrarparentidpro4()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilterTraining.getDrillLevel())){
			condParms+="DRILLLEVELID="+commonFilterTraining.getDrillLevel()+";";
		}
		//condParms+="";
		
		paramValues.add(condParms);
		
		paramValues.add(commonParams);
		
		return paramValues;
	}

	@Override
	public EntTlMultiskilldialymap create(
			List<EntTlMultiskilldialymap> lstEntTlMultiskilldialymap) {
		List<String> sqls = new ArrayList<String>(); 
		
		EntTlMultiskilldialymapSql entTlMultiskilldialymapSql  = new  EntTlMultiskilldialymapSql();
		try
		{
		  // sqls.add(" Delete  from ENT_TL_MULTISKILLEMPMAP where muse_keyid = "+newEntTlMultiskillempmap+" "); // 
			sqls.add("DELETE FROM "+entTlMultiskilldialymapSql.TBL_ENT_TL_MULTISKILLDIALYMAP+" WHERE MUDM_Flid ='"+lstEntTlMultiskilldialymap.get(0).getMudmFlid()+"' and  '"+lstEntTlMultiskilldialymap.get(0).getMudmEmployeeid()+"'");
	      for (EntTlMultiskilldialymap MultiskillempDate : lstEntTlMultiskilldialymap)
			{
	    	  // sqls.add(" Delete  from ENT_TL_MULTISKILLEMPMAP where muse_flid ="+Multiskillempmap+" ");
	    	   if( ! UIUtils.isValidKeyId (MultiskillempDate.getMudmKeyid()))
	    	   {
				CommonMessage.debugMsg("daoimpl44");
				MultiskillempDate.setMudmKeyid(dbActionTemplate.getSequenceNumber(EntTlMultiskilldialymapSql.TBL_ENT_TL_MULTISKILLDIALYMAP,10, "MUDM", "", "Y")); // set the sequnce number
				 sqls.add(EntTlMultiskilldialymapSql.getInsertSql(entTlMultiskilldialymapSql.getMudmDbFields(), MultiskillempDate.getSaveArray())); // add insert sql for master table
			  }
	    	/* else
			 {
	    		 CommonMessage.debugMsg("Delete");
				 sqls.add(EntTlMultiskillempmapSql.getDeleteSql(entTlMultiskillempmapSql.getMuseDbFields(), Multiskillempmap.getSaveArray())); // add insert sql for master table
			 }*/
			}
	        dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		   
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
}
