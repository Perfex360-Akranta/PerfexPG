package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BalWorkOrderDetailsBean;
import com.akranta.tpm.bean.BalWorkOrderDetailsLstBean;
import com.akranta.tpm.bean.CommonParams;
//import com.akranta.tpm.bean.WorkOrderDetailsBean;
//import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.controller.BAL_UIUtils;
import com.akranta.tpm.dao.MonPlanDao;
import com.akranta.tpm.dao.MonPlanEntryDao;
import com.akranta.tpm.dao.sql.BAL_PlmTlMultipleRespSql;
import com.akranta.tpm.dao.sql.BalPlmTlObservationsSql;
import com.akranta.tpm.dao.sql.BalPlmTlWofeedbackEntrySql;
import com.akranta.tpm.dao.sql.BalPlmTlWorksummarySql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.MspTlIndicatorsDtlSql;
import com.akranta.tpm.dao.sql.PlmTlCbmwocompdtlSql;
import com.akranta.tpm.dao.sql.PlmTlSpareconsumedSql;
import com.akranta.tpm.dao.sql.PlmTlSparecostactualSql;
import com.akranta.tpm.dao.sql.PlmTlWofeedbackSql;
import com.akranta.tpm.dao.sql.PlmTlWorksummarySql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
/*import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;*/
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlCalendar;
import com.akranta.tpm.model.PlmTlCbmwocompdtl;
import com.akranta.tpm.model.PlmTlMultipleResp;
import com.akranta.tpm.model.BalPlmTlCalendar;
import com.akranta.tpm.model.BalPlmTlCbmwocompdtl;
import com.akranta.tpm.model.BalPlmTlMultipleResp;
import com.akranta.tpm.model.BalPlmTlObservations;
import com.akranta.tpm.model.BalPlmTlSpareconsumed;
import com.akranta.tpm.model.BalPlmTlSparecostactual;
import com.akranta.tpm.model.BalPlmTlWofeedback;
import com.akranta.tpm.model.BalWoObservation;
import com.akranta.tpm.model.PlmTlRescheduledtl;

import com.akranta.tpm.model.PlmTlWorksummary;

import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

public class MonPlanEntryDaoImpl implements MonPlanEntryDao {
	
	private DBActionTemplate dbActionTemplate;
	private PlmTlWorksummary plmTlWorksummary;
	private PlmTlWorksummarySql plmTlWorksummarysql ; // contains dbtable,field names, Field types and related sqls  of master table
	private BalPlmTlWofeedbackEntrySql plmTlWofeedbackEntrksql ; // contains dbtable,field names, Field types and related sqls  of master table
	private PlmTlSpareconsumedSql plmTlSpareconsumedSql ;
	private PlmTlWofeedbackSql plmTlWofeedbacksql ;
	private PlmTlSparecostactualSql plmTlSparecostactualSql ;
    private PlmTlCbmwocompdtlSql plmTlCbmwocompdtlsql;	
    private BAL_PlmTlMultipleRespSql  plmTlMultipleRespSql;
    FunctionCallApi fnCallApi;
	public MonPlanEntryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		plmTlWofeedbacksql =new PlmTlWofeedbackSql();
		 plmTlWorksummary= new PlmTlWorksummary();
		 plmTlWorksummarysql = new PlmTlWorksummarySql(); // contains dbtable,field names, Field types and related sqls  of master table
		 plmTlWofeedbackEntrksql = new BalPlmTlWofeedbackEntrySql(); // contains dbtable,field names, Field types and related sqls  of master table
		 plmTlSpareconsumedSql = new PlmTlSpareconsumedSql();
		 plmTlSparecostactualSql = new PlmTlSparecostactualSql();
		 plmTlMultipleRespSql =new BAL_PlmTlMultipleRespSql(); 
		 plmTlCbmwocompdtlsql = new PlmTlCbmwocompdtlSql();

	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}

	public void MonPlanEntryDaoImplJwt(String JwtToken) 
	{
		try{
	
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String[]> getMonPlan(CommonFilter commonFilter,String type)	throws Exception {
		try
		{
			
			String strSect=null;
			 CommonFunctions.debugMsg("DAO IMPL"+type);
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			if(("Y").equals(commonFilter.getActwise()))
				condParms+="ISACTWISE=Y;";
			if(BAL_UIUtils.isValidKeyId(commonFilter.getEqpSubGroup().getId()))
					condParms+="EQPSUBGRPID="+commonFilter.getEqpSubGroup().getId()+";";
			CommonFunctions.debugMsg("Sub Group"+commonFilter.getEqpSubGroup().getId());
			condParms+="TYPE="+type+";";
			strSect=getSect(commonFilter.getFlid());
			CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
			//condParms+="ASSEQPWISE="+commonFilter.getType();
			if(strSect.substring(0,3).equals("SEC")){
				CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
				condParms+="SECTIONID="+strSect;
			}
			if(BAL_UIUtils.isValidKeyId(commonFilter.getType())){
				CommonFunctions.debugMsg("******" +commonFilter.getType());
				condParms+="ASSEQPWISE="+commonFilter.getType();
				
			}
			else if(strSect.substring(0,3).equals("FCT")){
				CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
				condParms+="FACTORYID="+strSect;
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("After ff.........."+ condParms);
			List<String[]> dataList;
			/*if(("Y").equals(commonFilter.getBdActivity()))
				dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PLM_FN_GETSCHEDULE", paramValues);
			else*/
			System.out.println("After ff..........////////" + paramValues);
				dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_GETSCHEDULE", paramValues);////PLM_FN_GETSCHEDULE", paramValues);
			System.out.println("..........After ff    " +commonFilter.getViewClick());
			String totalCnt = paramValues.get(0);
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnty = paramValues.get(0); 
				CommonFunctions.debugMsg("total"+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnty);
				if( isInteger ){
					CommonFunctions.debugMsg("total 1  "+totalCnty);
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnty));
					CommonFunctions.debugMsg("total 2"+commonFilter.getTotalRecordCnt());
					
				}
			}
			return dataList; 
			
			//List<String> paramValues = new ArrayList<String>();
			
			//String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			//String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			//paramValues.add(condParms);
			//paramValues.add(commonParams);
			/*paramValues.add("N");
			paramValues.add("N");
			paramValues.add(getComboSelectionId(commonFilter.getFactory()));
			paramValues.add(getComboSelectionId(commonFilter.getSection()));
			paramValues.add(getComboSelectionId(commonFilter.getCostCenter()));
			paramValues.add(getComboSelectionId(commonFilter.getCell()));
			paramValues.add(getComboSelectionId(commonFilter.getMachine()));
			paramValues.add(getComboSelectionId(commonFilter.getAssembly()));
			paramValues.add(getComboSelectionId(commonFilter.getTrade()));
			paramValues.add("{}");
			paramValues.add("{}");			
			paramValues.add("{}");
			paramValues.add(getComboSelectionId(commonFilter.getcboActivitytype()));
			paramValues.add("{}");
			paramValues.add(getComboSelectionId(commonFilter.getEqpGroup()));
			paramValues.add(getComboSelectionId(commonFilter.getCmbprodcngroup()));
			paramValues.add(getComboSelectionId(commonFilter.getCmbstep()));
			paramValues.add(getComboSelectionId(commonFilter.getCircle()));
			paramValues.add(getComboSelectionId(commonFilter.getMachineRank()));
			paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());			
			
		
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETSCHEDULE", paramValues);
			
			
		
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; */
		}
		catch (Exception e)
		{
			e.printStackTrace();
		//	throw new Exception(e.getMessage()); 
		}
		return null;
		
	}
	public String  getSect(String flid) throws Exception{
		
		String sql="Select FNLN_ORIGINALID from GEN_TL_FUNCTIONALLOCN where FNLN_KEYID='"+flid+"'";
		String sectid=dbActionTemplate.getSingleValue(sql);
		
		return sectid;
	}
	@Override
	public List<String[]> getfillgridheader() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select 'Equipment','Assembly','Nov-13','Nov-13','Nov-13','Nov-13'"
				+ " From Dual "    
				+ " Union All "
				+ " select 'Equipment','Assembly','W1','W2','W3','W4'"
				+  " From Dual "
				);
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	@Override
	public List<String[]> getfillgriddata() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("select '10001651-SHEETER-1 SLITTER UNIT','BLOWER MOTOR..','C','C','IC','A' "
				+ " From Dual "
				+ " Union All "
				+"select '10001650-SHEETER-1 DECURLER UNIT','BLOWER MOTOR..','C','IC','IC','A' "
				+ " From Dual "
				+ " Union All "
				+"select '10001652-SHEETER-1 JOGGER UNIT ','BREAD04242','A','IC','C','A' "
				+ " From Dual "
				+ " Union All "
				+"select '10001651-SHEETER-1 SLITTER UNIT','BREAD04242','A','A','C','IC' "
				+ " From Dual "
				+ " Union All "
				+"select '20009547-UNWIND STAND-1','BREAD04242','IC','A','IC','C' "
				+ " From Dual "
				);
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;
	}
	
	public static String getComboSelectionId(ComboFilter comboFilter){ 		
		if( comboFilter != null && FilterCondSql.isValidKeyId( comboFilter.getId() ) )
			return comboFilter.getId();
		return "{}";
	}

	@Override
	public Workbook getMonPlanExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getdownTimeReportResultSet(commonFilter);
			CommonFunctions.debugMsg(colmodel+ "in side bb daoimpl");
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(9,5,247)); //red font
			condFormatTick.setBgColor(new RGB(245,123,61));
			condFormatTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			//condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("1"); //Tick
			condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormatTick.setIdentfier("1");
			condFormats.add(condFormatTick);
			
		 	XLConditionalFormats condFormatBGTick = new XLConditionalFormats();
		 	condFormatBGTick.setFontColor(new RGB(9,5,247)); //red font
		 	condFormatBGTick.setBgColor(new RGB(245,123,61));
		 	condFormatBGTick.setFontName(XLConditionalFormats.FONT_DEFAULT);
		 	condFormatBGTick.setFontHeightPoint((short)9);
		 	condFormatBGTick.setFontBoldWeight((short)13);
		 	condFormatBGTick.setFromCol(3);
		 	condFormatBGTick.setToCol(-1);
		 	condFormatBGTick.setOperator(ComparisonOperator.EQUAL);
		 	condFormatBGTick.setCondValue("2"); //Tick
		 	condFormatBGTick.setSymbolStr("IC");
		 	condFormatBGTick.setIdentfier("2");
			condFormats.add(condFormatBGTick);
			
			XLConditionalFormats condFormatBG = new XLConditionalFormats();
			condFormatBG.setFontColor(new RGB(9,5,247)); //red font
			condFormatBG.setBgColor(new RGB(245,123,61));
			condFormatBG.setFontName(XLConditionalFormats.FONT_DEFAULT );
			condFormatBG.setFontHeightPoint((short)9);
			condFormatBG.setFontBoldWeight((short)13);
			condFormatBG.setFromCol(3);
			condFormatBG.setToCol(-1);
			condFormatBG.setOperator(ComparisonOperator.EQUAL);
			condFormatBG.setCondValue("3"); //Tick
			condFormatBG.setSymbolStr("A");
			condFormatBG.setIdentfier("3");
			condFormats.add(condFormatBG);
			
			XLConditionalFormats condFormatBGClr = new XLConditionalFormats();
			condFormatBGClr.setFontColor(new RGB(9,5,247)); //red font
			condFormatBGClr.setBgColor(new RGB(245,123,61));
			condFormatBGClr.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatBGClr.setFontHeightPoint((short)10);
			condFormatBGClr.setFontBoldWeight((short)15);
			condFormatBGClr.setFromCol(3);
			condFormatBGClr.setToCol(-1);
			condFormatBGClr.setOperator(ComparisonOperator.EQUAL);
			condFormatBGClr.setCondValue("4"); //Tick
			condFormatBGClr.setSymbolStr(" ");
			condFormatBGClr.setIdentfier("4");
			condFormats.add(condFormatBGClr);
			
		 
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("0"); //Tick
			condFormat.setIdentfier("0");
			condFormat.setSymbolStr(" ");
			
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		
		
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_GETSCHEDULE", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String weekno = commonFilter.getWeekno();
		String woId = commonFilter.getWodetailid();
		String tradeId=commonFilter.getTrarId();
		String jobType=commonFilter.getJobtype().getId();
		CommonFunctions.debugMsg("woId  --"+woId);
		if(BAL_UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(BAL_UIUtils.isValidKeyId(woId))
			condParms += ";WODETAILID="+woId;
		if(BAL_UIUtils.isValidKeyId(tradeId))
			condParms +=";TRADEID="+tradeId;
		if(BAL_UIUtils.isValidKeyId(jobType))
			condParms += ";JOBTYPE="+jobType;
		
		String strSect=null;
		try {
			strSect = getSect(commonFilter.getFlid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CommonFunctions.debugMsg("strSect ***** outside if " +strSect+", ** "+strSect.substring(0,3));
		if(strSect.substring(0,3).equals("SEC")){
			CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
			condParms+=";SECTIONID="+strSect;
		}
		else if(strSect.substring(0,3).equals("FCT")){
			CommonFunctions.debugMsg("strSect ***** in side if" +strSect);
			condParms+=";FACTORYID="+strSect;
		}
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	
	@Override
	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		 
		 
		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> wogenList =dbActionTemplate.processFunctionCalls("GETPMWORKORDRERSFORCOMP",paramValues);//GETPMWORKORDRERSFORCOMP", paramValues);
		return wogenList;
	}

	@Override
	public List<String[]> getAllwoCompData(CommonFilter commonFilter,String viewChecked,String type) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String weekno = commonFilter.getWeekno();
		String woId = commonFilter.getWodetailid();
		CommonFunctions.debugMsg("woId  --"+woId);
		if(BAL_UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(BAL_UIUtils.isValidKeyId(woId))
			condParms += ";WORKORDERNO="+woId;
		if(BAL_UIUtils.isValidKeyId(viewChecked))
			condParms += ";CHKVWWORKORDER="+viewChecked;
		if(BAL_UIUtils.isValidKeyId(commonFilter.getJobtype().getId()))
			condParms += ";JOBTYPE="+commonFilter.getJobtype().getId();
		condParms += ";TYPE="+type;
		paramValues.add(condParms);		
		paramValues.add(commonParams);
		CommonFunctions.debugMsg("Params in dao"+condParms);
		//if(type=="PRM" || type=="CBM" || type=="TBM"){
		List<String[]> woCompList =dbActionTemplate.processFunctionCalls("PLM_FN_GETSQLFROMWO", paramValues);
		CommonFunctions.debugMsg("woSize    ma"+type);
		return woCompList;
	}
	public List<String[]> getAllwoCompDataExcel(CommonFilter commonFilter,String viewChecked,String type) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String weekno = commonFilter.getWeekno();
		String woId = commonFilter.getWodetailid();
		CommonFunctions.debugMsg("woId  --"+woId);
		if(BAL_UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(BAL_UIUtils.isValidKeyId(woId))
			condParms += ";WORKORDERNO="+woId;
		if(BAL_UIUtils.isValidKeyId(viewChecked))
			condParms += ";CHKVWWORKORDER="+viewChecked;
		if(BAL_UIUtils.isValidKeyId(commonFilter.getJobtype().getId()))
			condParms += ";JOBTYPE="+commonFilter.getJobtype().getId();
		condParms += ";TYPE="+type;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonFunctions.debugMsg("Params in dao"+condParms);
		List<String[]> woCompList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETSQLFROMWOExcel", paramValues);
		CommonFunctions.debugMsg("woSize    ma"+type);
		return woCompList;
	}


	@Override
	public List<String[]> getspareData(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		
		String spareId = dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_SPAREDTL, "PSPD_SPAREID", "PSPD_STANDARDID", pmstdId);
		List<String> paramValues =new ArrayList<String>();
		paramValues.add(pmstdId);
		paramValues.add(spareId);
		List<String[]> spareList =dbActionTemplate.processFunctionCalls("PLM_FN_GETSPARESQLFROMWO",paramValues);
		CommonFunctions.debugMsg("woSize    ma"+spareList.size()+"---------------"+paramValues);
		return spareList;
	}
	@Override
	public List<String[]> getupdatecancelData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stubs
		//List<String> paramValues = getFilterParamValues(commonFilter);
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String weekno = commonFilter.getWeekno();
		String woId = commonFilter.getWodetailid();
		String tradeId=commonFilter.getTrarId();
		String jobType=commonFilter.getJobtype().getId();
		CommonFunctions.debugMsg("woId  --"+woId);
		condParms += ";GROUPBYMACHINE=N";
		condParms += ";GROUPBYLINE=N";
		if(BAL_UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(BAL_UIUtils.isValidKeyId(woId))
			condParms += ";WODETAILID="+woId;
		if(BAL_UIUtils.isValidKeyId(tradeId))
			condParms +=";TRADEID="+tradeId;
		if(BAL_UIUtils.isValidKeyId(jobType))
			condParms += ";JOBTYPE="+jobType;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonFunctions.debugMsg(commonFilter.getFactory()+"----");
		List<String[]> woupdtCanclList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETWOSQLFORUPDATE", paramValues);
		return woupdtCanclList;
	}
	@Override
	public List<String[]> getupdatecancelselctData(CommonFilter commonFilter,String workorderno, String wOgenCancl)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		CommonFunctions.debugMsg("woId ~~~ --"+workorderno+"--- wOgenCancl ---"+wOgenCancl +" Weekno  "+commonFilter.getWeekno());
		condParms += ";WEEKNO="+commonFilter.getWeekno();
		if(BAL_UIUtils.isValidKeyId(workorderno))
			condParms += ";WORKORDERNO="+workorderno;
		if(BAL_UIUtils.isValidKeyId(wOgenCancl)){
			if(wOgenCancl.equals("cancel")||wOgenCancl.equals("update"))
			 condParms += ";QUERYTYPE=WOCANCEL";
			else
			 condParms += ";QUERYTYPE=WOGEN";
		}
		/*if(UIUtils.isValidKeyId(commonFilter.getTrarId()))
			condParms +=";TRADEID="+commonFilter.getTrarId();
		if(UIUtils.isValidKeyId(commonFilter.getJobtype().getId()))
			condParms +=";JOBTYPE="+commonFilter.getJobtype().getId();*/
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> woupdtCanclSelList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETACTIVITYSQLFORWO", paramValues);
		return woupdtCanclSelList;
	}
	@Override
	public BalPlmTlWofeedback saveWOD(BalPlmTlWofeedback plmTlWofeedback,BalWorkOrderDetailsBean workOrderDetailsBean)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String dateTime = CommonFunctions.dateTimeNow();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		
		
		try{
			CommonFunctions.debugMsg("inside DAOIMPL of save woDetails");
			CommonFunctions.debugMsg("Test Data   :"+plmTlWofeedback.getWofbEnddate());
			plmTlWofeedback.setWofbFeedbackid(dbActionTemplate.getSequenceNumber(BalPlmTlWofeedbackEntrySql.TBL_PLM_TL_WOFEEDBACK_ENTRY, 11, "WFB", "YYMM", "Y")); // set the sequnce number
			plmTlWofeedback.setWofbFeedbackid(dbActionTemplate.getSequenceNumber(PlmTlWofeedbackSql.TBL_PLM_TL_WOFEEDBACK, 11, "WFB", "YYMM", "Y")); // set the sequnce number

			/*	plmTlWorksummary.setWksmKeyid((dbActionTemplate.getSequenceNumber(PlmTlWorksummarySql.TBL_PLM_TL_WORKSUMMARY, 11, "WOS", "YYMM", "Y"))); // set the sequnce number
			
			plmTlWorksummary.setWksmAccountedtime("0");
			plmTlWorksummary.setWksmContractorcost("0");
			plmTlWorksummary.setWksmCreatedby(plmTlWofeedback.getWofbCreatedby());
			plmTlWorksummary.setWksmCreatedon(plmTlWofeedback.getWofbCreatedon());
			plmTlWorksummary.setWksmManpowercost("0");
			plmTlWorksummary.setWksmModifiedon(plmTlWofeedback.getWofbModifiedon());
			plmTlWorksummary.setWksmOthercost("0");
			plmTlWorksummary.setWksmSparecost("0");
			plmTlWorksummary.setWksmSpareflag("N");
			plmTlWorksummary.setWksmUnaccountedtime("0");
			plmTlWorksummary.setWksmWodetailid(plmTlWofeedback.getWofbWodetailid());
			plmTlWorksummary.setWksmWofeedbackid(plmTlWofeedback.getWofbFeedbackid());
			
			String delFeedBack = plmTlWofeedbacksql.delfeedBck();
			 /*"DELETE FROM "+TableNames.TBL_PLM_TL_WOFEEDBACK +" WHERE WOFB_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'" */
			Object [] delFedBck	= {plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesdel =  { Types.VARCHAR};
			
			//sqls.add(delFeedBack);
			valueList.add(delFedBck);
			dataTypes.add(dataTypesdel);
			charList.add("Q");
			sqls.add(BalPlmTlWofeedbackEntrySql.getInsertSql(plmTlWofeedbackEntrksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table
			sqls.add(PlmTlWofeedbackSql.getInsertSql(plmTlWofeedbacksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table

			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
			//String delSmmry = plmTlWofeedbacksql.delwosumry();
			/*"DELETE FROM "+TableNames.TBL_PLM_TL_WORKSUMMARY +" WHERE WKSM_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'" */
			Object [] delSum	= {plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypessumdel =  { Types.VARCHAR};
			
		//	sqls.add(delSmmry);
			valueList.add(delSum);
			dataTypes.add(dataTypessumdel);
			charList.add("Q");
			sqls.add(PlmTlWorksummarySql.getInsertSql(plmTlWorksummarysql.getWksmDbFields(), plmTlWorksummary.getSaveArray())); // add insert sql for detail table
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");	
			CommonFunctions.debugMsg("worksummary after");
			/*Spares table insert*/
		/*	PlmTlSpareconsumed plmTlSpareconsumed = plmTlWofeedback.getSpareConsumed();
			CommonFunctions.debugMsg("before spare consumed " + plmTlSpareconsumed);	
			if(plmTlSpareconsumed != null){
				plmTlSpareconsumed.setPspcKeyid((dbActionTemplate.getSequenceNumber(PlmTlSpareconsumedSql.TBL_PLM_TL_SPARECONSUMED,  12, "PSU", "YY", "Y"))); // set the sequnce number
				CommonFunctions.debugMsg("after spare consumed");
				plmTlSpareconsumed.setPspcPmcalendarid(workOrderDetailsBean.getPmCalendarId());
				CommonFunctions.debugMsg(plmTlSpareconsumed.getPspcPmcalendarid());
				plmTlSpareconsumed.setPspcRemarks(plmTlWofeedback.getWofbRemarks());
				plmTlSpareconsumed.setPspcWodetailid(plmTlWofeedback.getWofbWodetailid());
				sqls.add(PlmTlSpareconsumedSql.getInsertSql(plmTlSpareconsumedSql.getPspcDbFields(),plmTlSpareconsumed.getSaveArray()));
			CommonFunctions.debugMsg("after insert spare consumption");
			}
			PlmTlSparecostactual plmTlSparecostactual= plmTlWofeedback.getSpareCostActual();
			if(plmTlSparecostactual != null){
				plmTlSparecostactual.setPscaPmcalendarid(workOrderDetailsBean.getPmCalendarId());
				plmTlSparecostactual.setPscaRequestedby(plmTlWofeedback.getWofbCompletedby());
				CommonFunctions.debugMsg("before spare actual insertion");
				sqls.add(PlmTlSparecostactualSql.getInsertSql(plmTlSparecostactualSql.getPscaDbFields(), plmTlSparecostactual.getSaveArray()));
				CommonFunctions.debugMsg("after spare actual insertion");
			}
			/*End*/
			//String upDateCal =  plmTlWofeedbacksql.updatePmCalendar();
			CommonFunctions.debugMsg("1");
				/*"UPDATE PLM_TL_CALENDAR SET PMCL_FEEDBACKID = '"+plmTlWofeedback.getWofbFeedbackid()+"',"+
			"PMCL_STATUS = 'Y' ,PMCL_FEEDBACKDATE =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
			"PMCL_COMPLETEDBY = '"+plmTlWofeedback.getWofbCompletedby()+"',  PMCL_WORKSUMMARYID = '"+plmTlWorksummary.getWksmKeyid()+"',"+
			"PMCL_EFFPLANCOMPDATE = TO_DATE('07-"+workOrderDetailsBean.getFromMonth()+"','DD-MON-YYYY') , PMCL_DOWNTIME ='"+plmTlWofeedback.getWofbDuration()+"'  WHERE  PMCL_KEYID = '"+workOrderDetailsBean.getPmCalendarId()+"' ";
			sqls.add(upDateCal);*/
			String appendedMonth ="07-"+workOrderDetailsBean.getFromMonth()+" 00:00:00";
			CommonFunctions.debugMsg("appendedMonth   :"+appendedMonth );
			CommonFunctions.debugMsg("dateTime  :"+dateTime);
			CommonFunctions.debugMsg("TIME STAMP   "+new java.sql.Timestamp( sdf.parse(dateTime).getTime()));
			CommonFunctions.debugMsg("fbId = " +plmTlWofeedback.getWofbFeedbackid());
			CommonFunctions.debugMsg("complBy = " +	plmTlWofeedback.getWofbCompletedby());
			CommonFunctions.debugMsg("wksmId = " +plmTlWorksummary.getWksmKeyid());
			CommonFunctions.debugMsg("frmMonth = " + new java.sql.Timestamp( sdf.parse( appendedMonth ).getTime()));
			CommonFunctions.debugMsg("dwnTIme = " +plmTlWofeedback.getWofbDuration());
			CommonFunctions.debugMsg("complBy = " +workOrderDetailsBean.getPmCalendarId());
			
			Object [] updateCalendar	= {plmTlWofeedback.getWofbFeedbackid(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbCompletedby(),plmTlWorksummary.getWksmKeyid(),  new java.sql.Timestamp( sdf.parse(appendedMonth).getTime()),plmTlWofeedback.getWofbDuration(),workOrderDetailsBean.getPmCalendarId()};
			CommonFunctions.debugMsg("2.1");
			int [] dataTypeCal =  { Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR};
			CommonFunctions.debugMsg("2.2");
			//sqls.add(upDateCal);
			//valueList.add(updateCalendar);
			dataTypes.add(dataTypeCal);
			charList.add("Q");
			
			CommonFunctions.debugMsg("3");
						
		/*	String updatePmstd =plmTlWofeedbacksql.getUpdateStd();
				" UPDATE PLM_TL_STANDARDS SET PMSD_LASTDONEDATE = '"+plmTlWofeedback.getWofbStartdate()+"',  PMSD_LASTDONEWEEKNO = 4 ,"+
				"PMSD_NEXTDUEDATE = '01-"+workOrderDetailsBean.getFromMonth()+"' ,PMSD_NEXTDUEWEEKNO = 1 , WHERE PMSD_KEYID = '"+workOrderDetailsBean.getPmStdId()+"'";
			//sqls.add(updatePmstd);
			Object [] updateStd	= {plmTlWofeedback.getWofbStartdate(), "01-"+workOrderDetailsBean.getFromMonth(),workOrderDetailsBean.getPmStdId()};
			int [] dataTypesStd =  {Types.DATE,Types.DATE,Types.VARCHAR};
			
			sqls.add(updatePmstd);
			valueList.add(updateStd);
			dataTypes.add(dataTypesStd);
			charList.add("Q");*/
			
			//String updatePlanConfig =plmTlWofeedbacksql.getUpdatPlanConfig();
			String firstAppndFrmMnth = "01-"+workOrderDetailsBean.getFromMonth()+" 00:00:00";
				/*"UPDATE PLM_TL_PLANCONFIGURATION SET PPLC_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') ,"+
				"PPLC_MONTHLY ='01-"+workOrderDetailsBean.getFromMonth()+"' WHERE  ( PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+plmTlWofeedback.getWofbMachineid()+"' || '"+
				workOrderDetailsBean.getAssmbleyId()+"' or PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+plmTlWofeedback.getWofbMachineid()+"' || '{}'   ) ";*/
			CommonFunctions.debugMsg("frmMonthFirst = " +firstAppndFrmMnth);
			CommonFunctions.debugMsg("frmMonthFirst = " +new java.sql.Timestamp( sdf.parse(firstAppndFrmMnth).getTime()));
			Object [] updateplanConfig	= { new java.sql.Timestamp( sdf.parse(dateTime).getTime()), new java.sql.Timestamp( sdf.parse(firstAppndFrmMnth).getTime()) ,plmTlWofeedback.getWofbMachineid(),workOrderDetailsBean.getAssmbleyId(),plmTlWofeedback.getWofbMachineid()};
			int [] dataTypesplanConfig =  {Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			//sqls.add(updatePlanConfig);
			valueList.add(updateplanConfig);
			dataTypes.add(dataTypesplanConfig);
			charList.add("Q");
			
			//String updateWODetail =plmTlWofeedbackEntrksql.getwoDetail();
				
				/*"UPDATE PLM_TL_WODTL SET PWDD_FEEDBACKID = '"+plmTlWofeedback.getWofbFeedbackid()+"',"+
			"PWDD_COMPLETEDBY = '"+plmTlWofeedback.getWofbCompletedby()+"',"+
			"PWDD_COMPLETEDDATE = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
			"PWDD_STATUS = 'C'  WHERE PWDD_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			Object [] updateWODtl	= {plmTlWofeedback.getWofbFeedbackid(),plmTlWofeedback.getWofbCompletedby(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesWODetail =  {Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
			//
			//sqls.add(updateWODetail);
			valueList.add(updateWODtl);
			dataTypes.add(dataTypesWODetail);
			charList.add("Q");
			

			//String updateWomst = plmTlWofeedbackEntrksql.getupdtWomst();
				
				/*"UPDATE PLM_TL_WOMST SET PWDM_MODIFIEDON=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') , PWDM_WOSTATUS=  (  SELECT MAX(PWDD_STATUS)  FROM PLM_TL_WODTL"+
				" WHERE PWDD_WOMASTERID = PWDM_WORKORDERNO  )  WHERE PWDM_WORKORDERNO ='"+workOrderDetailsBean.getWOmstId()+"'";*/
			CommonFunctions.debugMsg("womstdate "+new java.sql.Timestamp( sdf.parse(dateTime).getTime()));
			CommonFunctions.debugMsg("womstid "+workOrderDetailsBean.getWOmstId());
			Object [] updteWOMst	= {new java.sql.Timestamp( sdf.parse(dateTime).getTime()),workOrderDetailsBean.getWOmstId()};
			int [] dataTypesWOMst =  {Types.TIMESTAMP,Types.VARCHAR};
			
		//	sqls.add(updateWomst); 
			valueList.add(updteWOMst);
			dataTypes.add(dataTypesWOMst);
			charList.add("Q");
			
		//	String updateWOfeed = plmTlWofeedbacksql.updateWofeed(); 
				/*"UPDATE PLM_TL_WOFEEDBACK SET  WOFB_MODIFIEDON  =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WOFB_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			
			Object [] updateFeed	= {new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesFeed =  {Types.TIMESTAMP,Types.VARCHAR};
			//sqls.add(updateWOfeed); 
			valueList.add(updateFeed);
			dataTypes.add(dataTypesFeed);
			charList.add("Q");
			
		//	String updateWOSum = plmTlWofeedbacksql.getwosum();
			/*" UPDATE PLM_TL_WORKSUMMARY SET WKSM_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WKSM_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			Object [] updateSumry	= { new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesSumry=  {Types.TIMESTAMP,Types.VARCHAR};
		//	sqls.add(updateWOSum); 
			valueList.add(updateSumry);
			dataTypes.add(dataTypesSumry);
			charList.add("Q");
		//	String updateWOrelease = plmTlWofeedbacksql.getUpdtRelease();
			/*"UPDATE PLM_TL_WORKRELEASE SET  PMWR_COMPLETEDBY ='"+plmTlWofeedback.getWofbCompletedby()+"' , PMWR_COMPLETEDFLAG ='Y' ,  PMWR_COMPLETEDDATE=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+
			",PMWR_STATUS ='W'  WHERE PMWR_PMWORKORDERNO ='"+workOrderDetailsBean.getWOmstId()+"'";*/
			
			
			Object [] updaterelease	= {plmTlWofeedback.getWofbCompletedby(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),workOrderDetailsBean.getWOmstId()};
			int [] dataTypesrelease=  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
		//	sqls.add(updateWOrelease ); 
			valueList.add(updaterelease);
			dataTypes.add(dataTypesrelease);
			charList.add("Q");
			List<String> paramValues = new ArrayList<String>();
			
			
			paramValues.add(plmTlWofeedback.getWofbMachineid());
			paramValues.add(plmTlWofeedback.getWofbMachinetakeovertime());
			paramValues.add(plmTlWofeedback.getWofbProdstartdate());
			paramValues.add(workOrderDetailsBean.getWOmstId());
			paramValues.add("PM");
			paramValues.add(plmTlWofeedback.getWofbCreatedby());
			/*vMACHINEID IN VARCHAR2,
            vFROMDATETIME IN VARCHAR2, vTODATETIME IN VARCHAR2, vWNO IN VARCHAR2,
            vPARAMCODE IN VARCHAR2, vLOGINUSERID IN VARCHAR2*/
			CommonFunctions.debugMsg("paramValuesinsertPCS   "+paramValues);
			CommonFunctions.debugMsg("getDBLocation()  :"+workOrderDetailsBean.getDBLocation());
			if(workOrderDetailsBean.getDBLocation().equals("VASAI")){
				CommonFunctions.debugMsg("dblocation :Vasai");
				String parameterCode = "PM";
			    String insertPCSSql = 	"PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEMSR";
			    Object [] insertPCSDatas = {plmTlWofeedback.getWofbMachineid(),plmTlWofeedback.getWofbMachinetakeovertime(),plmTlWofeedback.getWofbProdstartdate(),workOrderDetailsBean.getWOmstId(),parameterCode,plmTlWofeedback.getWofbCreatedby()};
			    int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				sqls.add(insertPCSSql);
				valueList.add(insertPCSDatas);
				dataTypes.add(insertPCSTypes);
				charList.add("P");
			}
		    //CommonFunctions.debugMsg("retval    :"+retval.get(0)[0]);
			char [] sqlType = new char[charList.size()];
			for(int c=0;c<sqlType.length;c++)
			{
				sqlType[c] = charList.get(c).charAt(0);
				CommonFunctions.debugMsg(c +" : "+sqlType[c]);
			}
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
			CommonFunctions.debugMsg("end of save  " +sqls  +"   ,,,,,,,,,");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return plmTlWofeedback;
		
	}

	@Override
	public String savegrdWOD(List<BalPlmTlWofeedback> newPlmTlWofeedbackList,
			BalWorkOrderDetailsBean workOrderDetailsBean,BalPlmTlWofeedback nwPlmTlWofeedback) throws Exception {
		// TODO Auto-generated method stub
		
		CommonFunctions.debugMsg("in side DAOimpl of mce");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String dateTime = CommonFunctions.dateTimeNow();
		GenSequenceNumber feedbackSeqnoGen = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),plmTlWofeedbackEntrksql.TBL_PLM_TL_WOFEEDBACK_ENTRY, 11, "WFB", "YYMM", "Y");
		GenSequenceNumber feedbackSeqnoGenw = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), plmTlWofeedbacksql.TBL_PLM_TL_WOFEEDBACK, 11, "WFB", "YYMM", "Y");
		//GenSequenceNumber workSmrySeqnoGen = new GenSequenceNumber(dbActionTemplate.getDataSource(), PlmTlWorksummarySql.TBL_PLM_TL_WORKSUMMARY, 11, "WOS", "YYMM", "Y");
		GenSequenceNumber spareConsSeqnoGen = null;
		GenSequenceNumber pmCalendarSeqnoGen = null;
		GenSequenceNumber pmCalendarSeqnoGenw = null;

		GenSequenceNumber cbmSeqnoGen = null;
		GenSequenceNumber obserSeqnoGen = null;
		try{  
			
			//List<PlmTlWofeedback> plmTlWofeedbackList = newworkOrderFormBean.getPlmtlFeedback();
			//CommonFunctions.debugMsg("Test Data   :"+newPlmTlWofeedback.getWofbEnddate());
			//CommonFunctions.debugMsg("FeedbackList  Size   :"+plmTlWofeedbackList.size());
			List<BalWorkOrderDetailsLstBean> gridfara = workOrderDetailsBean.getGrdWoBean();
			BalPlmTlCbmwocompdtl newPlmTlCbmwocompdtl = new BalPlmTlCbmwocompdtl();
			
			BalPlmTlObservationsSql plmTlObservationsSql = null;
						
			for(BalPlmTlWofeedback plmTlWofeedback:newPlmTlWofeedbackList ){

				plmTlWofeedback.setWofbFeedbackid(feedbackSeqnoGen.getSequnceNumber()); // set the sequnce number
				plmTlWofeedback.setWofbFeedbackid(feedbackSeqnoGenw.getSequnceNumber()); // set the sequnce number

             /*	plmTlWorksummary.setWksmWodetailid(plmTlWofeedback.getWofbWodetailid());
				plmTlWorksummary.setWksmKeyid(workSmrySeqnoGen.getSequnceNumber()); // set the sequnce number
				plmTlWorksummary.setWksmAccountedtime("0");
				plmTlWorksummary.setWksmContractorcost("0");
				plmTlWorksummary.setWksmCreatedby(plmTlWofeedback.getWofbCreatedby());
				plmTlWorksummary.setWksmCreatedon(plmTlWofeedback.getWofbCreatedon());
				plmTlWorksummary.setWksmManpowercost("0");
				plmTlWorksummary.setWksmModifiedon(plmTlWofeedback.getWofbModifiedon());
				plmTlWorksummary.setWksmOthercost("0");
				plmTlWorksummary.setWksmSparecost("0");
				plmTlWorksummary.setWksmSpareflag("N");
				plmTlWorksummary.setWksmUnaccountedtime("0");
				plmTlWorksummary.setWksmWofeedbackid(plmTlWofeedback.getWofbFeedbackid());
				String delFeedBack =plmTlWorksummarysql.getDelFeedBack(plmTlWofeedback.getWofbWodetailid());
				String delWoSumry =plmTlWorksummarysql.getDelWoSumry(plmTlWofeedback.getWofbWodetailid());
				sqls.add( delWoSumry );
				sqls.add( delFeedBack );
				*/
				sqls.add(BalPlmTlWofeedbackEntrySql.getInsertSql(plmTlWofeedbackEntrksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table
				sqls.add(PlmTlWofeedbackSql.getInsertSql(plmTlWofeedbacksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table
//sqls.add(PlmTlWorksummarySql.getInsertSql(plmTlWorksummarysql.getWksmDbFields(), plmTlWorksummary.getSaveArray())); // add insert sql for detail table
				
				
				if( CommonFunctions.isValidKeyId(plmTlWofeedback.getWofbObservation().replaceAll("[^a-zA-Z]", "")) )
				{
					if( obserSeqnoGen == null)
						obserSeqnoGen  = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), BalPlmTlObservationsSql.TBL_PLM_TL_OBSERVATIONS, 10, "OB", null, null);
					if( plmTlObservationsSql == null)
						plmTlObservationsSql = new BalPlmTlObservationsSql();
					
					BalPlmTlObservations plmTlObservations= getObservationOb(obserSeqnoGen.getSequnceNumber(),plmTlWofeedback) ;
					sqls.add(BalPlmTlObservationsSql.getInsertSql(plmTlObservationsSql.getObsvDbFields(), plmTlObservations.getSaveArray()));
					/*
					if(com.akranta.tpm.dao.impl.CommonFunctions.isValidKeyId(plmTlObservations.getObsvResponsibility()))
					{
						if( pmCalendarSeqnoGen == null)
							pmCalendarSeqnoGen = new GenSequenceNumber(dbActionTemplate.getDataSource(), TableNames.TBL_PLM_TL_CALENDAR, 12, "PMC", "YY", "Y");

						String calSql = getPMCalendarSql(plmTlObservations,workOrderDetailsBean,pmCalendarSeqnoGen.getSequnceNumber());
						sqls.add(calSql);
					}
					*/
				}
				//detail table
				
				if(BAL_UIUtils.isValidKeyId(plmTlWofeedback.getCbmReading())){
					if( cbmSeqnoGen == null)
						CommonFunctions.debugMsg("In side The CBM");
						cbmSeqnoGen = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), PlmTlCbmwocompdtlSql.TBL_PLM_TL_CBMWOCOMPDTL, 15, "CBM", "YY", "Y");
						CommonFunctions.debugMsg("In side The CBM2 "+cbmSeqnoGen);
					fillCbmWoDtls(newPlmTlCbmwocompdtl,workOrderDetailsBean,plmTlWofeedback,cbmSeqnoGen.getSequnceNumber());
					CommonFunctions.debugMsg("In side The CBM3   "+plmTlCbmwocompdtlsql.getCmcdDbFields() +"  "+newPlmTlCbmwocompdtl.getSaveArray());
					sqls.add(PlmTlCbmwocompdtlSql.getInsertSql(plmTlCbmwocompdtlsql.getCmcdDbFields(), newPlmTlCbmwocompdtl.getSaveArray()));
					CommonFunctions.debugMsg("In side The CBM4 "+sqls);
				}
				
				/*Spares table insert*/
				BalPlmTlSpareconsumed plmTlSpareconsumed = plmTlWofeedback.getSpareConsumed();
			
				BalPlmTlSparecostactual plmTlSparecostactual= plmTlWofeedback.getSpareCostActual();
				
				/*End*/
				for(BalWorkOrderDetailsLstBean workOrderDetailsBean1:gridfara)
				{
					for(int i=0;i<newPlmTlWofeedbackList.size();i++){
						//workOrderDetailsBean1.getMachineId();
						//workOrderDetailsBean1.getFromDate();
						CommonFunctions.debugMsg(newPlmTlWofeedbackList.size()+"  size in near to update"+ i +" "+   workOrderDetailsBean1);
						
						if(plmTlSparecostactual != null){
							plmTlSparecostactual.setPscaPmcalendarid(workOrderDetailsBean1.getPmCalendarId());
							plmTlSparecostactual.setPscaRequestedby(plmTlWofeedback.getWofbCompletedby());
							sqls.add(PlmTlSparecostactualSql.getInsertSql(plmTlSparecostactualSql.getPscaDbFields(), plmTlSparecostactual.getSaveArray()));
						}
						if(plmTlSpareconsumed != null){
							if(spareConsSeqnoGen == null)
								spareConsSeqnoGen = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), PlmTlSpareconsumedSql.TBL_PLM_TL_SPARECONSUMED,  12, "PSU", "YY", "Y");
							
							plmTlSpareconsumed.setPspcKeyid(spareConsSeqnoGen.getSequnceNumber()); // set the sequnce number
							plmTlSpareconsumed.setPspcPmcalendarid(workOrderDetailsBean1.getPmCalendarId());
							plmTlSpareconsumed.setPspcRemarks(plmTlWofeedback.getWofbRemarks());
							plmTlSpareconsumed.setPspcWodetailid(plmTlWofeedback.getWofbWodetailid());
							sqls.add(PlmTlSpareconsumedSql.getInsertSql(plmTlSpareconsumedSql.getPspcDbFields(),plmTlSpareconsumed.getSaveArray()));
						}
							
						/*String updatePmstd =" UPDATE PLM_TL_STANDARDS SET PMSD_LASTDONEDATE = '"+plmTlWofeedback.getWofbStartdate()+"',  PMSD_LASTDONEWEEKNO = 4 ,"+
											"PMSD_NEXTDUEDATE = '01-"+newworkOrderDetailsBean1.getFromDate()+"' ,PMSD_NEXTDUEWEEKNO = 1 , WHERE PMSD_KEYID = '"+workOrderDetailsBean1.getPmStdId()+"'";
						//sqls.add(updatePmstd);*/
						/*	String updatePlanConfig =  plmTlWorksummarysql.updtPlanConfig(dateTime,workOrderDetailsBean.getFromMonth(),plmTlWofeedback.getWofbMachineid(),workOrderDetailsBean1.getAssmbleyId());
						sqls.add(updatePlanConfig); */
						
						/*
						String updateWomst = plmTlWorksummarysql.updtWOmst(dateTime,workOrderDetailsBean1.getWOmstId());
						sqls.add(updateWomst); 
						String updateWOfeed = plmTlWorksummarysql.updtWOfeed(dateTime,plmTlWofeedback.getWofbWodetailid());
						sqls.add(updateWOfeed);
						String updateWOSum =plmTlWorksummarysql.updtWOsum(dateTime,plmTlWofeedback.getWofbWodetailid());
						sqls.add(updateWOSum);
						String updateWOrelease =plmTlWorksummarysql.updtWOrelease(plmTlWofeedback.getWofbCompletedby(),dateTime,workOrderDetailsBean1.getWOmstId());
						sqls.add(updateWOrelease ); */
					}
					//CommonFunctions.debugMsg(newPlmTlWofeedbackList.size()+"  size in near to update"+ gridfara);
					String upDateCal = plmTlWorksummarysql.updtCal(plmTlWofeedback.getWofbFeedbackid(),dateTime,plmTlWofeedback.getWofbCompletedby(),plmTlWorksummary.getWksmKeyid(),workOrderDetailsBean1.getFromMonth(),plmTlWofeedback.getWofbDuration(),workOrderDetailsBean1.getPmCalendarId());

					//sqls.add(upDateCal);
			    }
				 
				String updateWODetail = plmTlWorksummarysql.updtWOdtl(plmTlWofeedback.getWofbFeedbackid(),plmTlWofeedback.getWofbCompletedby(),dateTime,plmTlWofeedback.getWofbWodetailid());
				sqls.add(updateWODetail);
			}
			multipleResponsibility(nwPlmTlWofeedback,"");
			dbActionTemplate.executeStatements(sqls); 
			
			return  "Success";
	}
		catch(Exception e)
		{
			 
			 return e.getMessage();
		}
		finally{
			if( feedbackSeqnoGen != null )
				feedbackSeqnoGen.closeConnection();
		/*	if( workSmrySeqnoGen != null)
				workSmrySeqnoGen.closeConnection();
			if( spareConsSeqnoGen != null)
				spareConsSeqnoGen.closeConnection();
			if( obserSeqnoGen != null )
				obserSeqnoGen.closeConnection();
			if( pmCalendarSeqnoGen != null)
				pmCalendarSeqnoGen.closeConnection();
			*/
			if( cbmSeqnoGen != null)
				cbmSeqnoGen.closeConnection();
			spareConsSeqnoGen = null;
			spareConsSeqnoGen =null;
			feedbackSeqnoGen = null;
			obserSeqnoGen = null;
			pmCalendarSeqnoGen= null;
			cbmSeqnoGen = null;
		}
		
		
	}
	private BalPlmTlObservations getObservationOb(String keyId,BalPlmTlWofeedback plmTlWofeedback){
		
		BalPlmTlObservations plmTlObservations = new BalPlmTlObservations ();
		plmTlObservations.setObsvKeyid(keyId);
		plmTlObservations.setObsvDate(plmTlWofeedback .getWofbCreatedon());
		plmTlObservations.setObsvFlid(plmTlWofeedback.getWofbMachineid());
		plmTlObservations.setObsvObservation(plmTlWofeedback.getWofbObservation());
		plmTlObservations.setObsvRefid(plmTlWofeedback.getWofbFeedbackid());
		
		//plmTlObservations.setObsvStatus("A");
		plmTlObservations.setObsvResponsibility(plmTlWofeedback.getObsvResponsibility());
		plmTlObservations.setObsvStatus("X");
		if(!BAL_UIUtils.isValidKeyId(plmTlWofeedback.getObsvResponsibility()) ){
		//	plmTlObservations.setObsvStatus("X");
			plmTlObservations.setObsvResponsibility("{}");
		}	
		
		plmTlObservations.setObsvFoundby(plmTlWofeedback.getWofbCompletedby());
		plmTlObservations.setObsvTargetdate(plmTlWofeedback.getObsvTargetDate());
		
		
		plmTlObservations.setObsvTempfield2("-");
		plmTlObservations.setObsvTempfield3("-");
		plmTlObservations.setObsvTempfield4("-");
		plmTlObservations.setObsvTempfield5("-");
		plmTlObservations.setObsvActive("Y");
		plmTlObservations.setObsvCreatedby(plmTlWofeedback.getWofbCreatedby());
		plmTlObservations.setObsvCreatedon(plmTlWofeedback.getWofbCreatedon());
		plmTlObservations.setObsvModifiedon(plmTlWofeedback.getWofbModifiedon());
		
		return plmTlObservations;
		
	}
	private String  getPMCalendarSql( BalPlmTlObservations plmTlObservations,BalWorkOrderDetailsBean workOrderDetailsBean,String keyid){
		
		String year = plmTlObservations.getObsvTargetdate().substring(plmTlObservations.getObsvTargetdate().lastIndexOf("-")+1);
		SimpleDateFormat monthYear = new SimpleDateFormat("dd-MMM-yyyy");
		int weekNo = 1;
		try {
			java.util.Date dd	 = monthYear.parse(plmTlObservations.getObsvTargetdate());
			
			Calendar cal=Calendar.getInstance();
			cal.setTime(dd);
			weekNo = cal.get(Calendar.DAY_OF_MONTH);
			weekNo = weekNo < 8 ? 1 :( weekNo < 15 ? 2 : ( weekNo < 22 ? 3 : 4 ) )   ;
			//year = cal.get(Calendar.YEAR)  ;//plmTlObservations.getObsvTargetdate().substring(plmTlObservations.getObsvTargetdate().lastIndexOf("-")+1);
			
		}catch(Exception e){
			
			//year = plmTlObservations.getObsvTargetdate().substring(plmTlObservations.getObsvTargetdate().lastIndexOf("-")+1);
		}
		StringBuilder sql = new StringBuilder("insert into PLM_TL_CALENDAR values('");
		sql.append(keyid+"','").append(workOrderDetailsBean.getGenwFactoryid()+"','").append(workOrderDetailsBean.getGenwSectionid()+"','").append(workOrderDetailsBean.getGenwCellid()+"','")
		.append(workOrderDetailsBean.getGenwMachineid()+"','").append(workOrderDetailsBean.getWogenAssemblyid()+"','{}',").append("'U','X','").append(plmTlObservations.getObsvKeyid()).append("','OBS','I','").append(workOrderDetailsBean.getWogenTradeid()).append("', ");
		sql.append(" '{}','").append(plmTlObservations.getObsvObservation()+"','").append ( year).append(  "','").append(weekNo+"','").append(plmTlObservations.getObsvTargetdate()+"','").append(plmTlObservations.getObsvTargetdate()+"','").append(plmTlObservations.getObsvTargetdate()+"','").append(plmTlObservations.getObsvTargetdate()+"','")
		.append(plmTlObservations.getObsvTargetdate()+"','").append(weekNo+"'").append(",'{}','X','{}','{}','01-Jan-1801','01-Jan-1801','01-Jan-1801','{}',0,'-','X',");
		sql.append(" 0,'01-Jan-1801','01-Jan-1801','").append(plmTlObservations.getObsvResponsibility()).append("',0,'N','N','MCH','{}','").append(workOrderDetailsBean.getGenwLocationid()+"','").append(workOrderDetailsBean.getGenwFlid()).append("','-','").append(workOrderDetailsBean.getGenwElementid()).append("','Y','").append(plmTlObservations.getObsvCreatedby()).append("',sysdate,sysdate ) ");
		
		return sql.toString();
	}
	private void fillCbmWoDtls(BalPlmTlCbmwocompdtl newPlmTlCbmwocompdtl,
			BalWorkOrderDetailsBean workOrderDetailsBean,BalPlmTlWofeedback plmTlWofeedback,String keyid) {
			String dateTime = CommonFunctions.dateTimeNow();
		
	
			newPlmTlCbmwocompdtl.setCmcdKeyid(keyid);
			newPlmTlCbmwocompdtl.setCmcdActive("Y");
			newPlmTlCbmwocompdtl.setCmcdActivitydate(dateTime);
			newPlmTlCbmwocompdtl.setCmcdAdjustedreading(plmTlWofeedback.getCbmAdjustedReading());
			newPlmTlCbmwocompdtl.setCmcdCreatedby(plmTlWofeedback.getWofbCreatedby());
			newPlmTlCbmwocompdtl.setCmcdCreatedon(dateTime);
			newPlmTlCbmwocompdtl.setCmcdCurrentreading(plmTlWofeedback.getCbmReading());
			newPlmTlCbmwocompdtl.setCmcdMaximumreading(plmTlWofeedback.getCbmMaxReading());
			newPlmTlCbmwocompdtl.setCmcdMiddlemax("{}");
			newPlmTlCbmwocompdtl.setCmcdMinimumreading(plmTlWofeedback.getCbmMinReading());
			newPlmTlCbmwocompdtl.setCmcdModifiedon(dateTime);
			newPlmTlCbmwocompdtl.setCmcdNextduedate(plmTlWofeedback.getCbmNextDueDate());
			newPlmTlCbmwocompdtl.setCmcdPmcalendarid(plmTlWofeedback.getPmCalendarId());
			newPlmTlCbmwocompdtl.setCmcdPmentrytype("P");
			newPlmTlCbmwocompdtl.setCmcdPmjobtype("CBM");
			newPlmTlCbmwocompdtl.setCmcdPmstandardid(plmTlWofeedback.getPmstandId());
			newPlmTlCbmwocompdtl.setCmcdTempfield1("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield2("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield3("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield4("-");
			newPlmTlCbmwocompdtl.setCmcdWofeedbackid(plmTlWofeedback.getWofbFeedbackid());
		
	}

	@Override
	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo ,BalPlmTlWofeedback newPlmTlWofeedback) throws Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg(updateWoId.replace("`", "'")+"~~"+workorderno+"~~~~"+allotedtocombo);
		updateWoId=updateWoId.replace("`", "'");
     try{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
        //String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_PREPAREDBY '"+allotedtocombo+"'  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
//		 String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_ALLOTEDTO = '"+allotedtocombo+"'  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
//        String updtpmcal="UPDATE PLM_TL_CALENDAR SET PMCL_STATUS = 'X',PMCL_SCHEDULEDFROM = PMCL_FROMDATE,"+
//		 "PMCL_SCHEDULEDTILL=PMCL_TILLDATE, PMCL_SCHEDULEDWEEK = PMCL_MONTHWEEK , PMCL_WORKORDERID = '{}',"+
//		 "PMCL_ALLOTTEDTO = '"+allotedtocombo+"'  WHERE PMCL_WORKORDERID IN ( "+updateWoId+")";
		
		 String updtwomst="UPDATE BAL_PLM_TL_WOMST SET PWDM_ALLOTEDTO = '"+allotedtocombo+"'  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
	        String updtpmcal="UPDATE PLM_TL_CALENDAR SET PMCL_STATUS = 'X',PMCL_SCHEDULEDFROM = PMCL_FROMDATE,"+
			 "PMCL_SCHEDULEDTILL=PMCL_TILLDATE, PMCL_SCHEDULEDWEEK = PMCL_MONTHWEEK , PMCL_WORKORDERID = '{}',"+
			 "PMCL_ALLOTTEDTO = '"+allotedtocombo+"'  WHERE PMCL_WORKORDERID IN ( "+updateWoId+")";

      // sqls.add(updtpmcal);
        sqls.add(updtwomst);
        multipleResponsibility(newPlmTlWofeedback,workorderno);
        CommonFunctions.debugMsg(updtwomst);
		dbActionTemplate.executeStatements(sqls); 
		return "Success";
     }
     catch(Exception e){
    	 return "fail";
     }
	}
	@Override
	public String cancelallocated(String workorderno, String cancelWoId,String noofActivites) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg(cancelWoId.replace("`", "'")+"~~"+workorderno);
		cancelWoId=cancelWoId.replace("`", "'");
		
		
     try{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
        String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_NUMOFACTIVITIES = PWDM_NUMOFACTIVITIES - "+noofActivites+"  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
        String delsummary="delete from plm_tl_worksummary where WKSM_WODETAILID IN ('"+workorderno+"') ";
		String delFeedback="delete from  PLM_TL_wofeedback where WOFB_WODETAILID  IN ('"+workorderno+"')" ;
        String delsprcostplan="DELETE FROM WOM_TL_SPARECOSTPLAN WHERE WSCP_WOID = '"+workorderno+"'";
        String delUtilitcstpln="DELETE FROM WOM_TL_UTILITYCOSTPLAN WHERE UTCP_WOKEYID = '"+workorderno+"'";
        String delothrcstplan="DELETE FROM WOM_TL_OTHERCOSTPLAN WHERE OTCP_WOID = '"+workorderno+"'";
		String delservcCostpln="DELETE FROM WOM_TL_SERVICECOSTPLAN WHERE SVCP_WOID = '"+workorderno+"'";
		String delmanpwrcstpln="DELETE FROM WOM_TL_MANPOWERCOSTPLAN WHERE MPCP_WOID = '"+workorderno+"'";
		String delwomst ="DELETE FROM PLM_TL_WOMST WHERE PWDM_WORKORDERNO  = '"+workorderno+"'  AND PWDM_WOSTATUS = 'P'"; 
		String delwodtl ="DELETE FROM PLM_TL_WODTL WHERE PWDD_WODETAILID IN ("+cancelWoId+")";
		String updtpmcal="UPDATE PLM_TL_CALENDAR SET PMCL_STATUS = 'X',PMCL_SCHEDULEDFROM = PMCL_FROMDATE,"+
						 "PMCL_SCHEDULEDTILL=PMCL_TILLDATE, PMCL_SCHEDULEDWEEK = PMCL_MONTHWEEK , PMCL_WORKORDERID = '{}',"+
						 "PMCL_ALLOTTEDTO = '{}'  WHERE PMCL_WORKORDERID IN ( "+cancelWoId+")";
		String deleteMultiResp="delete from PLM_TL_MULTIPLE_RESP where PMRS_REFID='"+workorderno+"' and PMRS_COMPLETED_EMPID='-'";
		
		sqls.add(updtpmcal);
		sqls.add(delsummary);
		sqls.add(delFeedback);
		sqls.add(delwodtl);
		sqls.add(delwomst);
		sqls.add(delmanpwrcstpln);
		sqls.add(delservcCostpln);
		sqls.add(delothrcstplan);
		sqls.add(delUtilitcstpln);
		sqls.add(delsprcostplan);
		sqls.add(updtwomst);
		sqls.add(deleteMultiResp);
		dbActionTemplate.executeStatements(sqls); 
		return "Success";
     }
     catch(Exception e){
    	 return "fail";
     }
	}

	@Override
	public String generateWO(String selActforWoGen, String strtDate) throws Exception {
		// TODO Auto-generated method stub
		try{
			selActforWoGen= selActforWoGen.replace("`", "");
			String[] params = strtDate.split("/");
			String allotedto =params[1];
			String description =params[2];
			String loginUser =params[3];
			String date = params[0];
			
			
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			
			inParams.add("01-"+date);
			inParams.add(selActforWoGen);
			inParams.add(description);
			inParams.add(loginUser);
			inParams.add(allotedto);
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDID", inParams, outParam);
			
			System.out.println("WOGENforSELECTEDID procedure AFTER :"+selActforWoGen+"inParams   : "+inParams+"  outParam   "+outParam);

			String woGenerated = outParam[1].toString();
			System.out.println("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			e.printStackTrace();
			CommonFunctions.debugMsg("Exception  : 1"+e.getMessage());
			return "wonotgenerated";
		}
		
	}

	@Override
	public String saveReschedule(String datas) throws Exception {
		// TODO Auto-generated method stub
		
		String[] getData = datas.split("/");
		CommonFunctions.debugMsg(getData[0]+"--"+getData[1]+"--"+getData[2]+"--"+getData[3]+"--"+getData[4]+"--"+getData[5]+"--"+getData[6]+"--"+getData[7]+"--"+getData[8]+"--"+getData[getData.length-1]);
		
		List<String> inParams = new ArrayList<String>();
		Object [] outParam = new Object [1];
		for(int i= 0;i< getData.length;i++){
			CommonFunctions.debugMsg("getData["+i+"]---  "+getData[i]);
			inParams.add(getData[i]);
		}
		//inParams.add();
		System.out.println("procedure before :"+inParams.size());
		CommonFunctions.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
		try{
			dbActionTemplate.processPLSQLProcedures("RESCHEDULESAVE", inParams, outParam);
			CommonFunctions.debugMsg("procedure returned"+ outParam[0]+"  --  "+(outParam[0].equals("-2")));
			if(outParam[0].equals("-2"))
				return "cantdo";
			else if(outParam[0].equals("-1"))
				return "cantdo";
			else
				return "Rescheduled";
		
		}catch(Exception e){
			e.printStackTrace();
			return "fail";	
		}
		
	}

	private List<PlmTlRescheduledtl> fillReschedule(PlmTlRescheduledtl newPlmTlRescheduledtl,
			List<String[]> getCalData, String[] getData) {
		// TODO Auto-generated method stub
		List<PlmTlRescheduledtl> plmTlRescheduledtlList = new ArrayList<PlmTlRescheduledtl>();
		
		String dateTime = CommonFunctions.dateTimeNow();
			for(int i=0;i<getCalData.size();i++){
						
				//for(int j=0;j<50;j++){
				//CommonFunctions.debugMsg("cal Row == "+getCalData.get(i)[j]);
			//	CommonFunctions.debugMsg("te   "+getCalData.get(i)[24]);
					//plmTlRescheduledtlList.add(getCalData.get(i));
				newPlmTlRescheduledtl.setPrsrPmcalendarid(getCalData.get(i)[0]);
				newPlmTlRescheduledtl.setPrsrCalendarstatus(getCalData.get(i)[24]);
				newPlmTlRescheduledtl.setPrsrPmactivitytype(getCalData.get(i)[10]);
				newPlmTlRescheduledtl.setPrsrPmdetailsid(getCalData.get(i)[9]);
				newPlmTlRescheduledtl.setPrsrPrestartdate(getCalData.get(i)[17]);
				newPlmTlRescheduledtl.setPrsrPreenddate(getCalData.get(i)[18]);
				newPlmTlRescheduledtl.setPrsrCurrentstartdate(getData[2]);
				newPlmTlRescheduledtl.setPrsrCurrentenddate(getData[8]);
				newPlmTlRescheduledtl.setPrsrReschedulereason(getData[6]);
				newPlmTlRescheduledtl.setPrsrRescheduleremarks(getData[getData.length-1]);
				newPlmTlRescheduledtl.setPrsrResponsibilityid(getCalData.get(i)[37]);
				newPlmTlRescheduledtl.setPrsrRevisedbyid(getData[7]);
				newPlmTlRescheduledtl.setPrsrStatus("");
				newPlmTlRescheduledtl.setPrsrCreatedby(getData[5]);
				newPlmTlRescheduledtl.setPrsrActive("Y");
				newPlmTlRescheduledtl.setPrsrCreatedon(dateTime);
				newPlmTlRescheduledtl.setPrsrModifiedon(dateTime);
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCreatedby()))
					newPlmTlRescheduledtl.setPrsrCreatedby("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCalendarstatus()))
					newPlmTlRescheduledtl.setPrsrCalendarstatus("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCurrentstartdate()))
					newPlmTlRescheduledtl.setPrsrCurrentstartdate(dateTime);
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCurrentenddate()))
					newPlmTlRescheduledtl.setPrsrCurrentenddate(Constants.futureNullDate);
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmactivitytype()))
					newPlmTlRescheduledtl.setPrsrPmactivitytype("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmcalendarid()))
					newPlmTlRescheduledtl.setPrsrPmcalendarid("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmdetailsid()))
					newPlmTlRescheduledtl.setPrsrPmdetailsid("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPrestartdate()))
					newPlmTlRescheduledtl.setPrsrPrestartdate(dateTime);
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPreenddate()))
					newPlmTlRescheduledtl.setPrsrPreenddate(Constants.futureNullDate);
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrReason()))
					newPlmTlRescheduledtl.setPrsrReason("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRemarks()))
					newPlmTlRescheduledtl.setPrsrRemarks("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrReschedulereason()))
					newPlmTlRescheduledtl.setPrsrReschedulereason("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRescheduleremarks()))
					newPlmTlRescheduledtl.setPrsrRescheduleremarks("{}");
					newPlmTlRescheduledtl.setPrsrRescheduletype("postponed");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrResponsibilityid()))
					newPlmTlRescheduledtl.setPrsrResponsibilityid("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRevisedbyid()))
					newPlmTlRescheduledtl.setPrsrRevisedbyid("{}");
				if(!BAL_UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrStatus()))
					newPlmTlRescheduledtl.setPrsrStatus("{}");
				//CommonFunctions.debugMsg("before list calendar ID   :_"+newPlmTlRescheduledtl.getPrsrPmcalendarid());
				
					
			//}
			   
			 CommonFunctions.debugMsg("calender ID   :_"+newPlmTlRescheduledtl.getPrsrPmcalendarid());
			 plmTlRescheduledtlList.add(newPlmTlRescheduledtl);
		}
			
			
			 //CommonFunctions.debugMsg("plmTlRescheduledtlList"+plmTlRescheduledtlList.get(i).getPrsrPmcalendarid());
			 for(PlmTlRescheduledtl plmTlRescheduledtl :plmTlRescheduledtlList ){
					
					CommonFunctions.debugMsg("LIST DATA  "+plmTlRescheduledtl.getPrsrPmcalendarid());
					
				}
		return plmTlRescheduledtlList;	
	}

	@Override
	public List<String[]> getAllModifyForm(CommonFilter commonFilter,String weekNO)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if(BAL_UIUtils.isValidKeyId(weekNO))
			condParms += ";WEEKNO="+weekNO;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		System.out.println("After  getAllModifyForm" );
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ABN_PC_ABNORMALITY.WO_FN_GetSqlForFillSpread", paramValues);
		return dataList;
		}

	@Override
	public String modifyCompWo(BalWorkOrderDetailsLstBean newworkOrderDetailsBean,
			String completedBy) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			String dateTime = CommonFunctions.dateTimeNow();
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			CommonFunctions.debugMsg(newworkOrderDetailsBean.getWksmWodetailid()+"-----"+completedBy);
			String sqlWoDtl = "Update "+TableNames.TBL_PLM_TL_WODTL+" set PWDD_COMPLETEDBY = '"+completedBy+"',  PWDD_COMPLETEDDATE =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+" where  PWDD_WODETAILID in ('"+newworkOrderDetailsBean.getWksmWodetailid()+"')";
			String sqlWoFeedBack ="Update "+TableNames.TBL_PLM_TL_WOFEEDBACK+" set WOFB_COMPLETEDBY = '"+completedBy+"' ,  WOFB_COMPLETEDDATE  = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+" where  WOFB_WODETAILID in ('"+newworkOrderDetailsBean.getWksmWodetailid()+"')"; 
			CommonFunctions.debugMsg(sqlWoFeedBack);
			CommonFunctions.debugMsg(sqlWoDtl);
			sqls.add(sqlWoFeedBack);
			sqls.add(sqlWoDtl);
			dbActionTemplate.executeStatements(sqls);
		return "Success";
		}catch(Exception e){
			return "Error";
		}
	}

	@Override
	public String generateWOForABN(String selActforWoGen, String strtDate)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			selActforWoGen= selActforWoGen.replace("`", "");
			String[] params = strtDate.split("/");
			
			String allotedto =params[3];
			String description =params[2];
			String date = params[1];
			String weekno = params[0];
			CommonFunctions.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]);
			CommonFunctions.debugMsg("date  :"+selActforWoGen);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			inParams.add(weekno);
			inParams.add(date);
			inParams.add(selActforWoGen);
			inParams.add(description);
			inParams.add(allotedto);
			System.out.println("procedure before :"+inParams.size());
			CommonFunctions.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDABN", inParams, outParam);
			System.out.println("procedure returned" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			System.out.println("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonFunctions.debugMsg("Exception  : 2"+e.getMessage());
			return "wonotgenerated";
		}
		
	}

	@Override
	public List<String[]> getkaizenData(CommonFilter commonFilter, String kAIZEN)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		CommonFunctions.debugMsg("kAIZEN ~~~ --"+kAIZEN);
		
		if(BAL_UIUtils.isValidKeyId(kAIZEN))
			condParms += ";KAIZEN=Y";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> woupdtCanclSelList =dbActionTemplate.processFunctionCalls("PLM_FN_GETACTIVITYSQLFORWO", paramValues);
		return woupdtCanclSelList;
	}

	@Override
	public String generateWOForKZN(String selActforWoKzn, String strtDate)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			selActforWoKzn= selActforWoKzn.replace("`", "");
			String[] params = strtDate.split("/");
			
			String allotedto =params[3];
			String description =params[2];
			String date = params[1];
			String weekno = params[0];
			CommonFunctions.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]+"--"+params[3]);
			CommonFunctions.debugMsg("date  :"+selActforWoKzn);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			inParams.add(weekno);
			inParams.add(date);
			inParams.add(selActforWoKzn);
			inParams.add(description);
			inParams.add(allotedto);
			System.out.println("procedure before :"+inParams.size());
			CommonFunctions.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3)+"--"+inParams.get(4));
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDKZN ", inParams, outParam);
			System.out.println("procedure returned kzn  :" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			System.out.println("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonFunctions.debugMsg("Exception  : 3"+e.getMessage());
			return "wonotgenerated";
		}
	
	}

	@Override
	public List<String[]> getMonthlyPlanRpt(CommonFilter commonFilter) throws Exception {
		try
		{
			  List<String > paramValues = new ArrayList<String>();
			 List<String[]> getMonthlyPlanRptList;
			 String condParms = FilterCondSql.getMasterPlanRelated(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			
			 CommonFunctions.debugMsg("Parameters" + condParms);
	  		 paramValues.add(condParms);
			 paramValues.add(commonParams);
			 getMonthlyPlanRptList = dbActionTemplate.processFunctionCalls("MSP_PC_MASTERPLAN.MSP_FN_MASTERPLANREPORT", paramValues);
	 		 
	 		if(commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			 }
		return getMonthlyPlanRptList;
		
	}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}	
	}

	@Override
	public Workbook getMonthlyPlanRptExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		
		ResultSet rs = null;
		   try{
			rs =   getMonthlyPlanRptResultSet(commonFilter);
			CommonFunctions.debugMsg(" Excel Report  in dao impl ="+colmodel);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getMonthlyPlanRptResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("MSP_PC_MASTERPLAN.MSP_FN_MASTERPLANREPORT", paramValues);
	}

	@Override
	public List<String[]> getfillActivity(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> operator = null;
		try
		{
			List<String> params = new ArrayList<String>();
			/*String sql =" SELECT '' AS CHKSELECTED,PMSD_KEYID,PMSD_ACTIVITY ,TRDM_NAME AS TRADE,PMSD_LOCATION FROM PLM_TL_STANDARDS,GEN_TL_TRADEMST where ";
			sql+= " PMSD_ACTIVITYTYPE = 'SDM' ";
			sql+= " AND TRDM_KEYID (+) = PMSD_TRADEID  ";
			sql+= " AND PMSD_ACTIVE='Y' and PMSD_LOCATIONID ='"+commonFilter.getLocation()+"' AND PMSD_FACTORYID ='"+commonFilter.getFactoryId()+"'";
			sql+= " AND PMSD_SECTIONID ='"+commonFilter.getSectionId()+" ' AND PMSD_CELLID ='"+commonFilter.getCellId()+"' AND PMSD_MACHINEID ='"+commonFilter.getMachineId()+"' ";
			sql+= " AND PMSD_KEYID NOT IN ( ";
			sql+= " SELECT PMCL_PMREFID FROM PLM_TL_CALENDAR,( ";
			sql+= " select SDCL_FROMDATE FD, SDCL_TILLDATE TD from plm_tl_shutdowncal ";
			sql+= " where SDCL_LOCATIONID = '"+commonFilter.getLocation()+"' AND SDCL_FACTORYID='"+commonFilter.getFactoryId()+"' AND SDCL_SECTIONID ='"+commonFilter.getSectionId()+"'";
            sql+= " AND SDCL_CELLID ='"+commonFilter.getCellId()+"' AND SDCL_MACHINEID ='"+commonFilter.getMachineId()+"'  and '"+commonFilter.getFromDate()+"' BETWEEN SDCL_FROMDATE AND SDCL_TILLDATE) "; 
    		sql+= " WHERE PMCL_FROMDATE BETWEEN FD AND TD";
    		sql+= " AND PMCL_LOCATIONID='"+commonFilter.getLocation()+"' AND PMCL_FACTORYID='"+commonFilter.getFactoryId()+"' AND PMCL_SECTIONID ='"+commonFilter.getSectionId()+"' AND PMCL_CELLID ='"+commonFilter.getCellId()+"' AND PMCL_MACHINEID ='"+commonFilter.getMachineId()+"')";
			*/
			/*String sql = "SELECT '' AS CHKSELECTED,PMSD_KEYID,PMSD_ACTIVITY ,TRDM_NAME AS TRADE,PMSD_LOCATION ";
				   sql+= "FROM PLM_TL_STANDARDS,GEN_TL_TRADEMST WHERE PMSD_ACTIVITYTYPE ='SDM'" ;
				   sql+= "AND TRDM_KEYID (+) = PMSD_TRADEID AND PMSD_ACTIVE='Y'";
				   System.out.println("sql  "+sql);*/
			 //operator = dbActionTemplate.getDataList(sql, params);
				   List<String> paramValues = new ArrayList<String>();
					
					String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
					String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
					paramValues.add(condParms);
					paramValues.add(commonParams);
				   operator =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST4.TEST_FN_GETSDMACTIVITY", paramValues);
	
		
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return operator;
 }

	@Override
	public String generateWOSDM(String selActforWoGen, String paramsVal)
			throws Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("Procedure called  PLM_PR_GENSHUTDOWNCAL");
		try{
			selActforWoGen= selActforWoGen.replace("`", "");
			String[] params = paramsVal.split("/");
			 
			String allotedto =params[1];
			String description =params[2];
			String date = params[0];
			
			String weekNo =params[3];
			String month =params[4];
			String year = params[5];
			String loginuser = params[6];
			CommonFunctions.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]);
			CommonFunctions.debugMsg("date  :"+selActforWoGen);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			//PMSDKEYID IN VARCHAR2, GENWEEK IN NUMBER, GENMONTH IN VARCHAR2, GENYEAR IN NUMBER, RESPONSIBILTYBY IN VARCHAR2,
           // PROBDESC IN VARCHAR2, LOGINUSERID IN VARCHAR2, PMWOMID OUT VARCHAR2
			 
			inParams.add(selActforWoGen);
			inParams.add(weekNo);
			inParams.add(month);
			inParams.add(year);
			inParams.add(allotedto);
			inParams.add(description);
			inParams.add(loginuser);
			 
			System.out.println("procedure before :"+inParams.size());
			CommonFunctions.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
			dbActionTemplate.processPLSQLProcedures("PLM_PR_GENSHUTDOWNCAL", inParams, outParam);
			System.out.println("procedure returned" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			System.out.println("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonFunctions.debugMsg("Exception  : 4"+e.getMessage());
			return "wonotgenerated";
		}
	}

	@Override
	public List<String[]> getdatapopup(String indicatorId,String flag) throws Exception {
		
		String sqls = "";
		List<String[]> getSop = new ArrayList<String[]>(); 
	
		 //MspTlIndicatorsDtlSql newMspTlIndicatorsDtlSql=new MspTlIndicatorsDtlSql();
		
			
		
		//	String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			String sql =MspTlIndicatorsDtlSql.getdatapopup(indicatorId,flag);
			System.out.println(" sql "+sql);
			getSop=dbActionTemplate.getDataList(sql);
			System.out.println(" getSop "+getSop.size());
	
			return getSop;
	
}

	public List<String[]> getObservations(CommonParams  commonParams ) throws NoDataFoundException, Exception{
		String innerSql = "SELECT OBSV_KEYID,'', OBSV_OBSERVATION FROM PLM_TL_OBSERVATIONS where OBSV_FLID = ? AND OBSV_STATUS = 'X' ";
		//String innerSql = "SELECT OBSV_KEYID, OBSV_OBSERVATION FROM PLM_TL_OBSERVATIONS ";//where OBSV_FLID = ? AND OBSV_STATUS = 'X' ";
		String cntSql = CommonFilterSqls.countSql(innerSql, commonParams.getGridFilters());
		
		Object [] args = {commonParams.getFlid()};
		List<String[]> data = dbActionTemplate.getDataList(cntSql, args);
		
		int count = 0;
		if (data.size()>0) {
			count = Integer.parseInt(data.get(0)[0]);
		}

		commonParams.setTotalRecordCnt(count);
		if (count > 0) {
			String sql = CommonFilterSqls.addPaginationParams(innerSql,
					commonParams);
			return dbActionTemplate.getDataList(sql, args);
		}

		throw new NoDataFoundException("No Data Found");
	}
	
	public void saveObservations(BalPlmTlCalendar plmTlCalendar) throws Exception{
		
		StringBuilder sql = new StringBuilder("insert into PLM_TL_CALENDAR values(?,?,?,?,?,?,?,'U','X',?,'OBS','I',?,");
		sql.append(" '{}',?,?,?,?,?,?,?,?,?,'{}','X','{}','{}','01-Jan-1801','01-Jan-1801','01-Jan-1801','{}',0,'-','X',");
		sql.append(" 0,'01-Jan-1801','01-Jan-1801','{}',0,'N','N','MCH','{}',?,?,'-',?,'Y',?,sysdate,sysdate ) ");
		//CommonFunctions.debugMsg(" sql " + sql);
		
		int [] insdTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR, Types.INTEGER,Types.INTEGER,Types.DATE,Types.DATE,Types.DATE,Types.DATE,Types.DATE,Types.INTEGER,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		List<Object[]> valueList = new ArrayList<Object[]>();
		List<BalWoObservation> woObservations = plmTlCalendar.getWoObservations();
		GenSequenceNumber genSequenceNumber = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),TableNames.TBL_PLM_TL_CALENDAR,12,"P","YY","");
		try{
			int i= 0;
			int [] updsTypes = new int[woObservations.size()];
			StringBuilder obsKeyids = new StringBuilder();
			StringBuilder updateSql = new StringBuilder(" UPdate PLM_TL_OBSERVATIONS SET OBSV_STATUS = 'A' WHERE OBSV_KEYID in (");
			for(BalWoObservation woObservation :woObservations){
				Object[] data = {
								 genSequenceNumber.getSequnceNumber(),plmTlCalendar.getPmclFactoryid(),
								 plmTlCalendar.getPmclSectionid(),plmTlCalendar.getPmclCellid(),plmTlCalendar.getPmclMachineid(),
								 (plmTlCalendar.getPmclAssemblyid() == null ? "{}":plmTlCalendar.getPmclAssemblyid()),plmTlCalendar.getPmclSubassemblyid()==null?"{}":plmTlCalendar.getPmclSubassemblyid(),woObservation.getObsrKeyid(),
								 plmTlCalendar.getPmclTradeid()==null?"{}":plmTlCalendar.getPmclTradeid(),woObservation.getObservation(),plmTlCalendar.getPmclCalendaryear(),
								 plmTlCalendar.getPmclMonthweek(),com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlDate(plmTlCalendar.getPmclFromdate()),com.akranta.tpm.dao.impl. CommonFunctions.convertoSqlDate(plmTlCalendar.getPmclTilldate()),
								 com.akranta.tpm.dao.impl. CommonFunctions.convertoSqlDate(plmTlCalendar.getPmclMaxcompletiondate()),com.akranta.tpm.dao.impl. CommonFunctions.convertoSqlDate(plmTlCalendar.getPmclScheduledfrom()),com.akranta.tpm.dao.impl. CommonFunctions.convertoSqlDate(plmTlCalendar.getPmclScheduledtill()),
								 plmTlCalendar.getPmclMonthweek(),plmTlCalendar.getPmclLocationid(),plmTlCalendar.getPmclFlid(),plmTlCalendar.getPmclElementid(),plmTlCalendar.getPmclCreatedby()};
				
			//	CommonFunctions.debugMsg(" woObservation.getObservation() "+ woObservation.getObservation());
				valueList.add(data);
				obsKeyids.append( woObservation.getObsrKeyid()+",");
				updateSql.append("?,");
				updsTypes[ i++ ] = Types.VARCHAR;
			}
			obsKeyids.deleteCharAt(obsKeyids.length()-1);
			updateSql.deleteCharAt(updateSql.length()-1);
			updateSql.append(")");
			
			List<Object[]> obsKList = new ArrayList<Object[]>();
			Object [] uKeyid =  obsKeyids.toString().split(",");
			obsKList.add(uKeyid);
			List<String> sqls = new ArrayList<String>();
 
			Map<Integer , List<Object[]>>  datas = new HashMap<Integer,List<Object[]>>();
			
			datas.put(0,valueList);
			datas.put(1,obsKList );
			List<int[]> dataTypes = new ArrayList<int[]>();
			dataTypes.add(insdTypes);
			dataTypes.add(updsTypes);
			sqls.add(sql.toString());
			sqls.add(updateSql.toString());
			dbActionTemplate.executeBatch(sqls, datas, dataTypes);
		
		}finally{
			if( genSequenceNumber != null)
				genSequenceNumber.closeConnection();
		}
	}


	@Override
	public List<String[]> getMultipleResponsibility(String pmwoKeyid,
			CommonParams commonParams) throws Exception {
		// TODO Auto-generated method stub
		String innerSql=BalPlmTlWorksummarySql.getMultipleRespSql(pmwoKeyid,commonParams);
		String sql = CommonFilterSqls.countSql(innerSql, commonParams.getGridFilters());
		CommonFunctions.debugMsg("SQL 1 "+sql);
		String [] arg = {commonParams.getFlid()};
		
		List<String[]> data = dbActionTemplate.getDataList(sql);
		
		int count = 0;
		if (data.size()>0) {
			count = Integer.parseInt(data.get(0)[0]);
		}

		commonParams.setTotalRecordCnt(count);
		if (count > 0) {
			sql = CommonFilterSqls.addPaginationParams(innerSql,commonParams);
			return dbActionTemplate.getDataList(sql);
		}
		throw new NoDataFoundException("No Data Found");
	}

	@Override
	public String generateWO(String selActforWoGen, String strtDate,
			BalPlmTlWofeedback newPlmTlWofeedback) throws Exception {
		// TODO Auto-generated method stub
		try{
			selActforWoGen= selActforWoGen.replace("`", "");
			String[] params = strtDate.split("/");
			String allotedto =params[1];
			String description =params[2];
			String loginUser =params[3];
			String date = params[0];
			
			
			
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {0,"PMWOMID"};
			
			inParams.add("01-"+date);
			inParams.add(selActforWoGen);
			inParams.add(description);
			inParams.add(loginUser);
			inParams.add(allotedto);
			System.out.println("01-"+date+","+selActforWoGen+","+description+","+loginUser+","+allotedto);
			System.out.println("WOGENforSELECTEDID procedure before :"+selActforWoGen);
			
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDID", inParams, outParam);
			System.out.println("WOGENforSELECTEDID procedure After :"+selActforWoGen+"        ........  "+inParams+"   "+outParam[0].toString().split(":") );
			String woGenerated = outParam[1].toString();
			System.out.println("woGenerated :"+woGenerated);
			newPlmTlWofeedback.setPmstandId(woGenerated.split(" : ")[0]);
			multipleResponsibility(newPlmTlWofeedback,allotedto);
			
			return woGenerated;
		}
		catch(Exception e){
			e.printStackTrace();  
			CommonFunctions.debugMsg("Exception  : 5"+e.getMessage());
			return "wonotgenerated";
		}
	}
	private void multipleResponsibility(BalPlmTlWofeedback plmTlWofeedback,String allotedto) throws Exception {
		List<String> sqls = new ArrayList<String>();
		CommonFunctions.debugMsg("Reference Keyid "+allotedto);
		if(BAL_UIUtils.isValidKeyId(allotedto))
			sqls.add(BAL_PlmTlMultipleRespSql.getDeleteAlloted(plmTlWofeedback.getPmstandId()));
		else
			sqls.add(BAL_PlmTlMultipleRespSql.getDeleteCompleted(plmTlWofeedback.getPmstandId()));
		
		if(plmTlWofeedback.getplmTlMultipleResp()!= null && plmTlWofeedback.getplmTlMultipleResp().size()>0) // check for detail table data
		{
			
	    	for(int i =0;i<plmTlWofeedback.getplmTlMultipleResp().size();i++)
			{	
	    		BalPlmTlMultipleResp plmTlMultipleResp = (BalPlmTlMultipleResp)plmTlWofeedback.getplmTlMultipleResp().get(i); // get detail info from list in empployee object
	    		CommonFunctions.debugMsg("Reference Keyid "+plmTlWofeedback.getPmstandId());
	    		plmTlMultipleResp.setPmrsRefid(plmTlWofeedback.getPmstandId());				
	    		plmTlMultipleResp.setPmrsKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlMultipleRespSql.TBL_PLM_TL_MULTIPLE_RESP));
				sqls.add(BAL_PlmTlMultipleRespSql.getInsertSql(plmTlMultipleRespSql.getPmrsDbFields(), plmTlMultipleResp.getSaveArray()));// add insert sql for detail table
			}
	    	dbActionTemplate.executeStatements(sqls);
		}
	//	return null;
	}

	@Override
	public Workbook pmCheckList(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getSpareConsumptionResultSet(commonFilter);
			CommonFunctions.debugMsg(tblJSONObj+ "in side bb daoimpl");
			
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				
				return excelUtils.writeToExcel(rs,format, 0,0,0);
				
		   }
		   finally{
				   if( rs != null)
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   } 		
		}
			/*
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormatTick = new XLConditionalFormats();
			condFormatTick.setFontColor(new RGB(9,5,247)); //red font
			condFormatTick.setBgColor(new RGB(245,123,61));
			condFormatTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			//condFormatTick.setFontName("Wingdings");
			condFormatTick.setFontHeightPoint((short)14);
			condFormatTick.setFontBoldWeight((short)20);
			condFormatTick.setFromCol(3);
			condFormatTick.setToCol(-1);
			condFormatTick.setOperator(ComparisonOperator.EQUAL);
			condFormatTick.setCondValue("1"); //Tick
			condFormatTick.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
			condFormatTick.setIdentfier("1");
			condFormats.add(condFormatTick);
			
		 	XLConditionalFormats condFormatBGTick = new XLConditionalFormats();
		 	condFormatBGTick.setFontColor(new RGB(9,5,247)); //red font
		 	condFormatBGTick.setBgColor(new RGB(245,123,61));
		 	condFormatBGTick.setFontName(XLConditionalFormats.FONT_DEFAULT);
		 	condFormatBGTick.setFontHeightPoint((short)9);
		 	condFormatBGTick.setFontBoldWeight((short)13);
		 	condFormatBGTick.setFromCol(3);
		 	condFormatBGTick.setToCol(-1);
		 	condFormatBGTick.setOperator(ComparisonOperator.EQUAL);
		 	condFormatBGTick.setCondValue("2"); //Tick
		 	condFormatBGTick.setSymbolStr("IC");
		 	condFormatBGTick.setIdentfier("2");
			condFormats.add(condFormatBGTick);
			
			XLConditionalFormats condFormatBG = new XLConditionalFormats();
			condFormatBG.setFontColor(new RGB(9,5,247)); //red font
			condFormatBG.setBgColor(new RGB(245,123,61));
			condFormatBG.setFontName(XLConditionalFormats.FONT_DEFAULT );
			condFormatBG.setFontHeightPoint((short)9);
			condFormatBG.setFontBoldWeight((short)13);
			condFormatBG.setFromCol(3);
			condFormatBG.setToCol(-1);
			condFormatBG.setOperator(ComparisonOperator.EQUAL);
			condFormatBG.setCondValue("3"); //Tick
			condFormatBG.setSymbolStr("A");
			condFormatBG.setIdentfier("3");
			condFormats.add(condFormatBG);
			
			XLConditionalFormats condFormatBGClr = new XLConditionalFormats();
			condFormatBGClr.setFontColor(new RGB(9,5,247)); //red font
			condFormatBGClr.setBgColor(new RGB(245,123,61));
			condFormatBGClr.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatBGClr.setFontHeightPoint((short)10);
			condFormatBGClr.setFontBoldWeight((short)15);
			condFormatBGClr.setFromCol(3);
			condFormatBGClr.setToCol(-1);
			condFormatBGClr.setOperator(ComparisonOperator.EQUAL);
			condFormatBGClr.setCondValue("4"); //Tick
			condFormatBGClr.setSymbolStr(" ");
			condFormatBGClr.setIdentfier("4");
			condFormats.add(condFormatBGClr);
			
		 
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(0,0,254)); //red font
			condFormat.setFontName(XLConditionalFormats.FONT_WINGDINGS);
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(3);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("0"); //Tick
			condFormat.setIdentfier("0");
			condFormat.setSymbolStr(" ");
			
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   if( rs != null)
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
		/*ResultSet rs = null;
		   try{
			   
			   System.out.println("in side Dao impl export to excel   " +tblJSONObj);
			
			rs =   getSpareConsumptionResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,format, 1,0,0 );
			
		   }finally{
			  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   } */
		
	private ResultSet getSpareConsumptionResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PLM_PC_PMWORKORDRER.PLM_FN_GETSQLFROMWO", paramValues);
	}

	public List<String[]> refreshProcess(CommonFilter commonFilter) {
		List<String> inParams = new ArrayList<String>();
		Object [] outParam = {0,"PMWOMID"};
				
		System.out.println(commonFilter.getSectionId() + "12389"+inParams);
		try {
			dbActionTemplate.processPLSQLProcedures("activity1", inParams, outParam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("WOGENforSELECTEDID procedure After :"+selActforWoGen+"        ........  "+inParams+"   "+outParam[0].toString().split(":") );
		String woGenerated = outParam[1].toString();
		System.out.println("woGenerated :"+woGenerated);
		return null;
		
	
 }

	@Override
	public List<String[]> getMachine(String machineid) throws Exception {
		StringBuffer sql = new StringBuffer();

		 sql.append(" select mchm_machinename||'-[' || mchm_machineno || ']'  from gen_tl_machinemst  where mchm_keyid='"+machineid+"'");
		List<String[]> machineList = dbActionTemplate.getDataList(sql.toString());
		
		return machineList;
	}

	@Override
	public List<String[]> getAllwoGenDataMobile(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT   orderno,alloteddate, WEEK, SCHDMONTH,tradename, ");
		sql.append("count(orderno) as totalactivity,   SUM(COMPSTATUS),SUM(PENDSTATUS), DECODE ((SUM(PENDSTATUS)+SUM(COMPSTATUS)), SUM(COMPSTATUS), 'COMPLETED','PENDING') AS status ");
		sql.append("FROM ( ");
		sql.append("SELECT DISTINCT pwdm_workorderno orderno,pwdm_alloteddate as alloteddate, 'W'||pmcl_scheduledweek AS week,  ");
        sql.append("TO_CHAR (PMCL_FROMDATE,'MON-YYYY') AS SCHDMONTH, ");
		sql.append("trdm_name AS tradename, ");
		sql.append("DECODE(PMCL_status, 'A', 1,0) as PENDSTATUS, DECODE(PMCL_status, 'Y', 1,0) as COMPSTATUS  ");                
        sql.append(" FROM plm_tl_womst, ");
        sql.append(" plm_tl_calendar,plm_tl_wodtl,gen_tl_trademst ");
        sql.append(" WHERE pwdm_workorderno = pwdd_womasterid AND pwdd_wodetailid = pmcl_workorderid ");
        sql.append(" AND PMCL_tradeid = trdm_keyid  ");                            
        sql.append(" AND pmcl_active = 'Y' AND pwdm_active = 'Y' AND PMCL_STATUS='A' ");
        sql.append(" AND pmcl_entrytype <> 'G' AND pmcl_pmsource IN ('E', 'I') ");
        sql.append(" AND pmcl_pmfreq IN('D', 'W', 'F', 'M', 'Q', 'H', 'Y', 'X')  ");
        sql.append(" AND PWDM_MACHINEID='"+commonFilter.getMachineId()+"'");
        sql.append(" AND (   TRUNC (pmcl_fromdate) BETWEEN TO_DATE ('"+commonFilter.getFromDate()+"', ");
        sql.append("'dd-mon-yyyy') AND TO_DATE ('"+commonFilter.getToDate()+"','dd-mon-yyyy') ");
        sql.append(" OR pmcl_tilldate BETWEEN TO_DATE ('"+commonFilter.getFromDate()+"', 'dd-mon-yyyy') AND TO_DATE ('"+commonFilter.getToDate()+"','dd-mon-yyyy') ))  GROUP BY  orderno, WEEK, SCHDMONTH,tradename,alloteddate ORDER BY SCHDMONTH,WEEK");
		
        
        System.out.println(sql.toString() +"sql.toString()sql.toString()");
    List<String[]> dataList = dbActionTemplate.getDataList(sql.toString());
		
		return dataList;
	}
	public List<String[]> getSchedule(CommonFilter commonFilter)
			throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT PMCL_KEYID, PMCL_JOBTYPE,PMCL_PMFREQ,dECODE(SBAM_NAME,'','([ ' || ASSM_NAME ||' ]) - '||PMCL_WHATACTIVITY,'([ ' || ASSM_NAME||' / '||SBAM_NAME||' ]) - '||PMCL_WHATACTIVITY),PMCL_DURATION,'' AS OK,'' AS NOTOK,'' ACTIONTAKEN ");
		sql.append(" FROM PLM_TL_CALENDAR,PLM_TL_WODTL,PLM_TL_WOMST,GEN_TL_ASSEMBLYMST,GEN_TL_SUBASSEMBLYMST ");
		sql.append(" WHERE PMCL_WORKORDERID=PWDD_WODETAILID");
		//sql.append(" AND PMCL_TRADEID=TRDM_KEYID");
		sql.append(" AND PMCL_ASSEMBLYID=ASSM_KEYID");
		sql.append(" AND PMCL_SUBASSEMBLYID=SBAM_KEYID(+)");
		sql.append(" AND PWDM_WORKORDERNO=PWDD_WOMASTERID");
		sql.append(" AND PMCL_MACHINEID='"+commonFilter.getMachineId()+"' AND PWDM_WORKORDERNO='"+commonFilter.getWodetailid()+"'");
      //sql.add("AND PMCL_SCHEDULEDWEEK=2 
	    sql.append(" AND PMCL_ACTIVE='Y' AND PMCL_STATUS='A'");
        System.out.println(sql.toString() +"sql.toString()sql.toString()");
    List<String[]> dataList = dbActionTemplate.getDataList(sql.toString());
		
		return dataList; 
		
	}
	
	
}
	


