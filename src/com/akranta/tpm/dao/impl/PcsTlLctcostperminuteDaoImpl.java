package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlLctcostperminuteDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
import com.akranta.tpm.dao.sql.PcsTlLctcostperminuteSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlLctcostperminute;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class PcsTlLctcostperminuteDaoImpl implements PcsTlLctcostperminuteDao {
	private DBActionTemplate dbActionTemplate; 
	public PcsTlLctcostperminuteDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void create(List<PcsTlLctcostperminute> pcsTlLctcostperminute) 	throws Exception,BusinessApplicationExceptions{
		try{
			CommonMessage.debugMsg("pcscreatess");
			                                    
			List<String> sqls = new ArrayList<String>(); 
			Map<Integer,List<Object[]>> saveLossCost=new HashMap<Integer,List<Object[]>>();
			List<Object []> insObj=new ArrayList<Object[]>();
			List<Object[]> upObj=new ArrayList<Object[]>();
			List<Object[]> delObj=new ArrayList<Object[]>();
			List<int[]> dataType=new ArrayList<int[]>();
			SimpleDateFormat sdf = new SimpleDateFormat(CommonFunctions.DATE_FORMAT_NOW,Locale.ENGLISH);
			List <PcsTlLctcostperminute> methodslist = pcsTlLctcostperminute;
			if(methodslist != null && methodslist.size()>0)
			{
				
				GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(), PcsTlLctcostperminuteSql.TBL_PCS_TL_LCTCOSTPERMINUTE, 12, "LCPM", null,null);
				for(PcsTlLctcostperminute lossCstList:methodslist)
				{
					CommonMessage.debugMsg("PCSSAVE    "+lossCstList.getCkPcsLossCost());
					if(lossCstList.getLcpmKeyid()!=null && lossCstList.getCkPcsLossCost().equals("Y"))
					{
						/*CommonMessage.debugMsg("Deletefunction");
						Object [] deleteLossCst	={lossCstList.getLcpmKeyid()};
						delObj.add(deleteLossCst);
						int [] dataTypeIndicator ={Types.VARCHAR};
						dataType.add(dataTypeIndicator);
						saveLossCost.put(0, delObj);
						sqls.add(PcsTlLctcostperminuteSql.getDelete());
						dbActionTemplate.executeBatch(sqls,saveLossCost,dataType);
						sqls.clear();
						saveLossCost.clear();
						dataType.clear();*/
						
						//CommonMessage.debugMsg("pcscreatess");
						                //LCPM_COSTPERMINUTE=?,LCPM_FROMDATE=?,LCPM_TODATE=?,LCPM_LATESTFLAG='N' WHERE LCPM_ELEMENTID=?
						
						
						
						List<String> paramValues=new ArrayList<String>();
						paramValues.add(lossCstList.getLcpmElementid());
						CommonMessage.debugMsg("lossCstList.getLcpmFromdate1():"+lossCstList.getLcpmFromdate());
						paramValues.add(lossCstList.getLcpmFromdate());
						List<String[]> resultlist=dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PCS_FN_LCTCOSTPERMINUTESAVE", paramValues);
						String retVal=resultlist.get(0)[0];
						CommonMessage.debugMsg("retVal:"+ retVal);
						if(retVal.equals("2"))//WHEN EXIST ENTRY EQUAL TO CURRENT ENTRY
						{
							//CommonMessage.debugMsg("pcscreatess");
							Object[] updateLossCst={lossCstList.getLcpmCostperminute(),lossCstList.getLcpmElementid(),lossCstList.getLcpmKeyid()};
							int [] dataTypeIndicator = {Types.NUMERIC,Types.VARCHAR,Types.VARCHAR};
							dataType.add(dataTypeIndicator);
							upObj.add(updateLossCst);
							saveLossCost.put(0, upObj);	
							CommonMessage.debugMsg(lossCstList.getLcpmCostperminute()+"   updatedao  "+lossCstList.getLcpmElementid()+" lossCstList.getLcpmKeyid()  "+lossCstList.getLcpmKeyid());
							sqls.add(PcsTlLctcostperminuteSql.getUpdateExistDateEqual());
							CommonMessage.debugMsg("UPDATE QUERY:  "+sqls.toString());
							CommonMessage.debugMsg("UPDATE QUERY:  "+dataType.toString());
							CommonMessage.debugMsg("UPDATE QUERY:  "+upObj.toString());
							CommonMessage.debugMsg("UPDATE QUERY:  "+saveLossCost.toString());
							dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
							sqls.clear();
							upObj.clear();
							saveLossCost.clear();	
							dataType.clear();
						}else if(retVal.equals("3"))
						{
							throw new BusinessApplicationExceptions(lossCstList.getLcpmElementid()+" has Effective Date Greater than Selected Date ");
						}else{
							lossCstList.setLcpmTodate(CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1));
				        	Object[] updateLossCst={lossCstList.getLcpmCostperminute(),lossCstList.getLcpmFromdate(),lossCstList.getLcpmTodate(),lossCstList.getLcpmElementid(),lossCstList.getLcpmKeyid()};
				        	CommonMessage.debugMsg(lossCstList.getLcpmKeyid()+"update1234  "+lossCstList.getLcpmFromdate() +lossCstList.getLcpmLatestflag());
				        	String seqNo=sequenceNumber.getSequnceNumber();
				        	CommonMessage.debugMsg("seqNo  "+seqNo);
							lossCstList.setLcpmKeyid(seqNo);
				        	Object [] insertLossCst	={lossCstList.getLcpmKeyid(),lossCstList.getLcpmElementid(),lossCstList.getLcpmElementtype(),new java.sql.Date( sdf.parse(lossCstList.getLcpmFromdate()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmCostperminute(),						
		        			lossCstList.getLcpmLatestflag(),lossCstList.getLcpmTempfield1(),lossCstList.getLcpmTempfield2(),lossCstList.getLcpmTempfield3(),lossCstList.getLcpmTempfield4(),lossCstList.getLcpmTempfield5(),
							lossCstList.getLcpmActive(),lossCstList.getLcpmCreatedby(),new java.sql.Date( sdf.parse(lossCstList.getLcpmCreatedon()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmModifiedon()).getTime())};
				        	
				        	
							int [] dataTypeIndicator = {Types.TIMESTAMP,Types.CHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
							dataType.add(dataTypeIndicator);
							int [] dataTypeIndicatorIns = {Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.DATE,Types.DATE,Types.NUMERIC,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.DATE,Types.DATE};
							dataType.add(dataTypeIndicatorIns);
							upObj.add(updateLossCst);
							insObj.add(insertLossCst);
							saveLossCost.put(0, upObj);	
							sqls.add(PcsTlLctcostperminuteSql.getUpdateExistDateGreater());
							saveLossCost.put(1, insObj);
							sqls.add(PcsTlLctcostperminuteSql.getInsert());
							dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
						}
						//CommonMessage.debugMsg("updatdao123");
						//sqls.clear();
						//upObj.clear();e
						//saveLossCost.clear();	
						//dataType.clear();
						
						/*CommonMessage.debugMsg("updatefunction");
						CommonMessage.debugMsg("CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1):"+CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1));
						lossCstList.setLcpmTodate(CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1));
						Object[] updateLossCst={new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmElementid()};
						int [] dataTypeIndicator = {Types.DATE,Types.VARCHAR};
						dataType.add(dataTypeIndicator);
						upObj.add(updateLossCst);
						String seqNo=sequenceNumber.getSequnceNumber();
						lossCstList.setLcpmKeyid(seqNo);
						Object [] insertLossCst	={lossCstList.getLcpmKeyid(),lossCstList.getLcpmElementid(),lossCstList.getLcpmElementtype(),new java.sql.Date( sdf.parse(lossCstList.getLcpmFromdate()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmCostperminute(),
						lossCstList.getLcpmLatestflag(),lossCstList.getLcpmTempfield1(),lossCstList.getLcpmTempfield2(),lossCstList.getLcpmTempfield3(),lossCstList.getLcpmTempfield4(),lossCstList.getLcpmTempfield5(),
						lossCstList.getLcpmActive(),lossCstList.getLcpmCreatedby(),new java.sql.Date( sdf.parse(lossCstList.getLcpmCreatedon()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmModifiedon()).getTime())};
						insObj.add(insertLossCst);
						int [] dataTypeIndicatorIns = {Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.DATE,Types.DATE,Types.NUMERIC,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.DATE,Types.DATE};
						dataType.add(dataTypeIndicatorIns);
						saveLossCost.put(0, upObj);
						CommonMessage.debugMsg("updatedaoimpl");
						sqls.add(PcsTlLctcostperminuteSql.getUpdateExistDateGreater());
						saveLossCost.put(1, insObj);
						sqls.add(PcsTlLctcostperminuteSql.getInsert());
						dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
						//sqls.clear();
						//insObj.clear();
						//upObj.clear();
						//saveLossCost.clear();
						//dataType.clear();
						 * */
						
					}
				  else if(lossCstList.getCkPcsLossCost().equals("N"))
					{
						//CommonMessage.debugMsg("PCSSAVE123     "+lossCstList.getCkPcsLossCost());
						//CommonMessage.debugMsg("saveeeeee");
						List<String> paramValues=new ArrayList<String>();
						paramValues.add(lossCstList.getLcpmElementid());
						CommonMessage.debugMsg("lossCstList.getLcpmFromdate1():"+lossCstList.getLcpmFromdate());
						paramValues.add(lossCstList.getLcpmFromdate());
						List<String[]> resultlist=dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PCS_FN_LCTCOSTPERMINUTESAVE", paramValues);
						String retVal=resultlist.get(0)[0];
						CommonMessage.debugMsg("retVal:"+ retVal);
						if(retVal.equals("0"))//WHEN NO TABLE ENTRY EXISTS
						{
							CommonMessage.debugMsg("saveeeeeeeeeeeeeeeee");
							String seqNo=dbActionTemplate.getSequenceNumber(PcsTlLctcostperminuteSql.TBL_PCS_TL_LCTCOSTPERMINUTE, 12, "LCPM", null,null);
							lossCstList.setLcpmKeyid(seqNo);
							CommonMessage.debugMsg("lossCstList.getLcpmFromdate():"+lossCstList.getLcpmFromdate());
							CommonMessage.debugMsg("lossCstList.getLcpmTodate():"+lossCstList.getLcpmTodate());
							Object [] insertLossCst	={lossCstList.getLcpmKeyid(),lossCstList.getLcpmElementid(),lossCstList.getLcpmElementtype(),new java.sql.Date( sdf.parse(lossCstList.getLcpmFromdate()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmCostperminute(),
							lossCstList.getLcpmLatestflag(),lossCstList.getLcpmTempfield1(),lossCstList.getLcpmTempfield2(),lossCstList.getLcpmTempfield3(),lossCstList.getLcpmTempfield4(),lossCstList.getLcpmTempfield5(),
							lossCstList.getLcpmActive(),lossCstList.getLcpmCreatedby(),new java.sql.Date( sdf.parse(lossCstList.getLcpmCreatedon()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmModifiedon()).getTime())};
							insObj.add(insertLossCst);
							int [] dataTypeIndicator = {Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.DATE,Types.DATE,Types.NUMERIC,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.DATE,Types.DATE};
							dataType.add(dataTypeIndicator);
							saveLossCost.put(0, insObj);
							sqls.add(PcsTlLctcostperminuteSql.getInsert());
							CommonMessage.debugMsg("sql :  "+sqls.toString());
							dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
							sqls.clear();
							insObj.clear();
							saveLossCost.clear();
							dataType.clear();
							
						}
						else if(retVal.equals("1"))//WHEN EXIST ENTRY LESS THAN CURRENT ENTRY
						{
							
							
							CommonMessage.debugMsg("updatefunction");
							CommonMessage.debugMsg("CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1):"+CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1));
							lossCstList.setLcpmTodate(CommonFunctions.addDay(lossCstList.getLcpmFromdate(),-1));
							Object[] updateLossCst={new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmElementid()};
							int [] dataTypeIndicator = {Types.DATE,Types.VARCHAR};
							dataType.add(dataTypeIndicator);
							upObj.add(updateLossCst);
							String seqNo=sequenceNumber.getSequnceNumber();
							lossCstList.setLcpmKeyid(seqNo);
							Object [] insertLossCst	={lossCstList.getLcpmKeyid(),lossCstList.getLcpmElementid(),lossCstList.getLcpmElementtype(),new java.sql.Date( sdf.parse(lossCstList.getLcpmFromdate()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmTodate()).getTime()),lossCstList.getLcpmCostperminute(),
							lossCstList.getLcpmLatestflag(),lossCstList.getLcpmTempfield1(),lossCstList.getLcpmTempfield2(),lossCstList.getLcpmTempfield3(),lossCstList.getLcpmTempfield4(),lossCstList.getLcpmTempfield5(),
							lossCstList.getLcpmActive(),lossCstList.getLcpmCreatedby(),new java.sql.Date( sdf.parse(lossCstList.getLcpmCreatedon()).getTime()),new java.sql.Date( sdf.parse(lossCstList.getLcpmModifiedon()).getTime())};
							insObj.add(insertLossCst);
							int [] dataTypeIndicatorIns = {Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.DATE,Types.DATE,Types.NUMERIC,Types.CHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.DATE,Types.DATE};
							dataType.add(dataTypeIndicatorIns);
							saveLossCost.put(0, upObj);
							CommonMessage.debugMsg("updatedaoimpl");
							sqls.add(PcsTlLctcostperminuteSql.getUpdateExistDateGreater());
							saveLossCost.put(1, insObj);
							sqls.add(PcsTlLctcostperminuteSql.getInsert());
							dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
							sqls.clear();
							insObj.clear();
							upObj.clear();
							saveLossCost.clear();
							dataType.clear();
						}
						else if(retVal.equals("2"))//WHEN EXIST ENTRY EQUAL TO CURRENT ENTRY
						{
							//CommonMessage.debugMsg("pcscreatess");
							Object[] updateLossCst={lossCstList.getLcpmCostperminute(),lossCstList.getLcpmElementid()};
							int [] dataTypeIndicator = {Types.NUMERIC,Types.VARCHAR};
							dataType.add(dataTypeIndicator);
							upObj.add(updateLossCst);
							saveLossCost.put(0, upObj);	
							CommonMessage.debugMsg("updatedao");
							sqls.add(PcsTlLctcostperminuteSql.getUpdateExistDateEqual());
							dbActionTemplate.executeBatch(sqls, saveLossCost, dataType);
							sqls.clear();
							upObj.clear();
							saveLossCost.clear();	
							dataType.clear();
						
						}
						else if(retVal.equals("3"))
						{
							throw new BusinessApplicationExceptions(lossCstList.getLcpmElementid()+" has Effective Date Greater than Selected Date ");
						}
						//sequenceNumber.closeConnection();
						//sequenceNumber =null;
					}		
			}	
				
				
		}
	}
		catch(BusinessApplicationExceptions e)
		{
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
	}
	public PcsTlLctcostperminute update(PcsTlLctcostperminute pcsTlLctcostperminute)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		PcsTlLctcostperminuteSql pcsTlLctcostperminuteSql = new PcsTlLctcostperminuteSql();
		try {
			sqls.add(PcsTlLctcostperminuteSql.getUpdateSql(pcsTlLctcostperminuteSql.getLcpmDbFields(), pcsTlLctcostperminute.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlLctcostperminute;
	}
	
	public PcsTlLctcostperminute delete(PcsTlLctcostperminute pcsTlLctcostperminute)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		PcsTlLctcostperminuteSql pcsTlLctcostperminuteSql = new PcsTlLctcostperminuteSql();
		try {
			
			sqls.add(PcsTlLctcostperminuteSql.getDeleteSql(pcsTlLctcostperminuteSql.getLcpmDbFields(), pcsTlLctcostperminute.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlLctcostperminute;
	}

	@Override
	public List<String[]> getPcsLossCost(CommonFilter commonFilter)
			throws Exception {
		CommonMessage.debugMsg("pcsdaoimpl");
		List<String> paramValues =  new ArrayList<String>();
		String condParam=FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParam=FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParam);
		paramValues.add(commonParam);
		List<String[]> resultList=dbActionTemplate.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_LCTCOSTPERMINUTE", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return resultList;
	}

	@Override
	public Workbook getPcsLossCostExportExcel(CommonFilter commonFilter, JSONObject tableModel, String format)
			throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getPCSLossCostReportResultSet(commonFilter);
			CommonMessage.debugMsg("After Data....");
			ExcelUtils excelUtils = new ExcelUtils(tableModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("1"); //Tick
			condFormat.setIdentfier("tick");
			condFormat.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormats.add(condFormat);
			XLConditionalFormats condFormat1 = new XLConditionalFormats();
			condFormat1.setFontColor(new RGB(254,0,0)); //red font
			condFormat1.setFontName("Wingdings");
			condFormat1.setFontHeightPoint((short)14);
			condFormat1.setFontBoldWeight((short)20);
			condFormat1.setFromCol(3);
			condFormat1.setToCol(-1);
			condFormat1.setOperator(ComparisonOperator.EQUAL);
			condFormat1.setCondValue("0"); //Tick
			condFormat1.setIdentfier("tick");
			condFormat1.setSymbolStr("");
			condFormats.add(condFormat1);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	}
	private ResultSet getPCSLossCostReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		
	return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.PCS_FN_LCTCOSTPERMINUTE", paramValues);
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues =  new ArrayList<String>();
		String condParam=FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParam=FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParam);
		paramValues.add(commonParam);
		
		return paramValues;
	}

	
	/*public PcsTlLctcostperminute create(PcsTlLctcostperminute newPcsTlLctcostperminute) throws Exception {
		
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution 
		PcsTlLctcostperminuteSql  newPcsTlLctcostperminuteSql=new PcsTlLctcostperminuteSql();
		try{
		for(int i=0;i<newPcsTlLctcostperminute.getPcsTlLctcostperminute().size();i++){
			
			if(!UIUtils.isValidKeyId(newPcsTlLctcostperminute.getLcpmKeyid()))
			{
				newPcsTlLctcostperminute.setLcpmKeyid(dbActionTemplate.getSequenceNumber(PcsTlLctcostperminuteSql.TBL_PCS_TL_LCTCOSTPERMINUTE, 8, "LPCM", "YYMM", "Y"));  // set the sequnce number 
				sqls.add(PcsTlLctcostperminuteSql.getInsertSql(newPcsTlLctcostperminuteSql.getLcpmDbFields(), newPcsTlLctcostperminute.getSaveArray())); // add insert sql for master table
			}
			else
			{
				sqls.add(PcsTlLctcostperminuteSql.getUpdateSql(newPcsTlLctcostperminuteSql.getLcpmDbFields(), newPcsTlLctcostperminute.getSaveArray()));
			}
	
		}
			dbActionTemplate.executeStatements(sqls);
		}
	   catch(Exception e)
		{
			e.printStackTrace();
		}
		//return newPcsTlLctcostperminute;
	 
		return newPcsTlLctcostperminute;
	
	}*/


	/*public ResultSet getPcsLoss(CommonFilter commonFilter)
			throws Exception 
		/*try
		{

			List<String> paramValues = new ArrayList<String>();
			
				
			 String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			 
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PCS_FN_LCTCOSTPERMINUTE", paramValues);
			 if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg(paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			 CommonMessage.debugMsg(" daoimpl " + dataList.size());
			return dataList;
		
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
			{
		List<String> paramValues = getFilterParamValues(commonFilter);
	
		
	return dbActionTemplate.dbFunctionCall("TEST_PC_TEST.PCS_FN_LCTCOSTPERMINUTE", paramValues);
}*/
	
}

