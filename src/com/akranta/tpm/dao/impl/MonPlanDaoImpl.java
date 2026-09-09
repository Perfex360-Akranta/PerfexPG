package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.ss.usermodel.Workbook;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.MonPlanDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.MspTlIndicatorsDtlSql;
import com.akranta.tpm.dao.sql.PlmTlCbmwocompdtlSql;
import com.akranta.tpm.dao.sql.PlmTlGenmaintenanceSql;
//import com.akranta.tpm.dao.sql.PlmTlRescheduledtlSql;
import com.akranta.tpm.dao.sql.PlmTlSpareconsumedSql;
import com.akranta.tpm.dao.sql.PlmTlSparecostactualSql;
import com.akranta.tpm.dao.sql.PlmTlWofeedbackSql;
import com.akranta.tpm.dao.sql.PlmTlWorksummarySql;
//import com.akranta.tpm.dao.sql.QtmTlSopmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlCbmwocompdtl;
import com.akranta.tpm.model.PlmTlRescheduledtl;
import com.akranta.tpm.model.PlmTlSpareconsumed;
import com.akranta.tpm.model.PlmTlSparecostactual;
import com.akranta.tpm.model.PlmTlWofeedback;
import com.akranta.tpm.model.PlmTlWorksummary;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class MonPlanDaoImpl implements MonPlanDao {
	
	private DBActionTemplate dbActionTemplate;
	private PlmTlWorksummary plmTlWorksummary;
	private PlmTlWorksummarySql plmTlWorksummarysql ; // contains dbtable,field names, Field types and related sqls  of master table
	private PlmTlWofeedbackSql plmTlWofeedbacksql ; // contains dbtable,field names, Field types and related sqls  of master table
	private PlmTlSpareconsumedSql plmTlSpareconsumedSql ;
	private PlmTlSparecostactualSql plmTlSparecostactualSql ;
    private PlmTlCbmwocompdtlSql plmTlCbmwocompdtlsql;	
	public MonPlanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		 plmTlWorksummary= new PlmTlWorksummary();
		 plmTlWorksummarysql = new PlmTlWorksummarySql(); // contains dbtable,field names, Field types and related sqls  of master table
		 plmTlWofeedbacksql = new PlmTlWofeedbackSql(); // contains dbtable,field names, Field types and related sqls  of master table
		 plmTlSpareconsumedSql = new PlmTlSpareconsumedSql();
		 plmTlSparecostactualSql = new PlmTlSparecostactualSql();
		 plmTlCbmwocompdtlsql = new PlmTlCbmwocompdtlSql();
		
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		
	}


	
	public List<String[]> getMonPlan(CommonFilter commonFilter)	throws Exception {
		try
		{
			 CommonMessage.debugMsg("DAO IMPL");
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			CommonMessage.debugMsg("actwise........"+commonFilter.getActwise());
			if(("Y").equals(commonFilter.getActwise()))
				condParms+=";ISACTWISE=Y";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("After ff" );
			List<String[]> dataList;
			/*if(("Y").equals(commonFilter.getBdActivity()))
				dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST.PLM_FN_GETSCHEDULE", paramValues);
			else*/
				dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETSCHEDULE", paramValues);
			CommonMessage.debugMsg("After ff" +commonFilter.getViewClick());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
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
			throw new Exception(e.getMessage()); 
		}
		
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
		CommonMessage.debugMsg("woId  --"+woId);
		if(UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(UIUtils.isValidKeyId(woId))
			condParms += ";WODETAILID="+woId;
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}

	
	@Override
	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		 
		 
		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> wogenList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.GETPMWORKORDRERSFORCOMP", paramValues);
		return wogenList;
	}

	@Override
	public List<String[]> getAllwoCompData(CommonFilter commonFilter,String viewChecked) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		String weekno = commonFilter.getWeekno();
		String woId = commonFilter.getWodetailid();
		CommonMessage.debugMsg("woId  --"+woId);
		if(UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(UIUtils.isValidKeyId(woId))
			condParms += ";WORKORDERNO="+woId;
		if(UIUtils.isValidKeyId(viewChecked))
			condParms += ";CHKVWWORKORDER="+viewChecked;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> woCompList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETSQLFROMWO", paramValues);
		CommonMessage.debugMsg("woSize    ma"+woCompList.size());
		return woCompList;
	}

	@Override
	public List<String[]> getspareData(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		
		String spareId = dbActionTemplate.getSingleValue(TableNames.TBL_PLM_TL_SPAREDTL, "PSPD_SPAREID", "PSPD_STANDARDID", pmstdId);
		List<String> paramValues =new ArrayList<String>();
		paramValues.add(pmstdId);
		paramValues.add(spareId);
		List<String[]> spareList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETSPARESQLFROMWO",paramValues);
		CommonMessage.debugMsg("woSize    ma"+spareList.size()+"---------------"+paramValues);
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
		CommonMessage.debugMsg("woId  --"+woId);
		condParms += ";GROUPBYMACHINE=N";
		condParms += ";GROUPBYLINE=N";
		if(UIUtils.isValidKeyId(weekno))
			condParms += ";WEEKNO="+weekno;
		if(UIUtils.isValidKeyId(woId))
			condParms += ";WODETAILID="+woId;
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg(commonFilter.getFactory()+"----");
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
		CommonMessage.debugMsg("woId ~~~ --"+workorderno+"--- wOgenCancl ---"+wOgenCancl +" Weekno  "+commonFilter.getWeekno());
		condParms += ";WEEKNO="+commonFilter.getWeekno();
		if(UIUtils.isValidKeyId(workorderno))
			condParms += ";WORKORDERNO="+workorderno;
		if(UIUtils.isValidKeyId(wOgenCancl)){
			if(wOgenCancl.equals("cancel")||wOgenCancl.equals("update"))
			 condParms += ";QUERYTYPE=WOCANCEL";
			else
			 condParms += ";QUERYTYPE=WOGEN";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> woupdtCanclSelList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETACTIVITYSQLFORWO", paramValues);
		return woupdtCanclSelList;
	}
	@Override
	public PlmTlWofeedback saveWOD(PlmTlWofeedback plmTlWofeedback,WorkOrderDetailsBean workOrderDetailsBean)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String dateTime = CommonFunctions.dateTimeNow();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		
		
		try{
			CommonMessage.debugMsg("inside DAOIMPL of save woDetails");
			CommonMessage.debugMsg("Test Data   :"+plmTlWofeedback.getWofbEnddate());
			plmTlWofeedback.setWofbFeedbackid(dbActionTemplate.getSequenceNumber(PlmTlWofeedbackSql.TBL_PLM_TL_WOFEEDBACK, 11, "WFB", "YYMM", "Y")); // set the sequnce number
			plmTlWorksummary.setWksmKeyid((dbActionTemplate.getSequenceNumber(PlmTlWorksummarySql.TBL_PLM_TL_WORKSUMMARY, 11, "WOS", "YYMM", "Y"))); // set the sequnce number
			
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
			
			sqls.add(delFeedBack);
			valueList.add(delFedBck);
			dataTypes.add(dataTypesdel);
			charList.add("Q");
			sqls.add(PlmTlWofeedbackSql.getInsertSql(plmTlWofeedbacksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
			String delSmmry = plmTlWofeedbacksql.delwosumry();
			/*"DELETE FROM "+TableNames.TBL_PLM_TL_WORKSUMMARY +" WHERE WKSM_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'" */
			Object [] delSum	= {plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypessumdel =  { Types.VARCHAR};
			
			sqls.add(delSmmry);
			valueList.add(delSum);
			dataTypes.add(dataTypessumdel);
			charList.add("Q");
			sqls.add(PlmTlWorksummarySql.getInsertSql(plmTlWorksummarysql.getWksmDbFields(), plmTlWorksummary.getSaveArray())); // add insert sql for detail table
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");	
			CommonMessage.debugMsg("worksummary after");
			/*Spares table insert*/
			PlmTlSpareconsumed plmTlSpareconsumed = plmTlWofeedback.getSpareConsumed();
			CommonMessage.debugMsg("before spare consumed " + plmTlSpareconsumed);	
			if(plmTlSpareconsumed != null){
				plmTlSpareconsumed.setPspcKeyid((dbActionTemplate.getSequenceNumber(PlmTlSpareconsumedSql.TBL_PLM_TL_SPARECONSUMED,  12, "PSU", "YY", "Y"))); // set the sequnce number
				CommonMessage.debugMsg("after spare consumed");
				plmTlSpareconsumed.setPspcPmcalendarid(workOrderDetailsBean.getPmCalendarId());
				CommonMessage.debugMsg(plmTlSpareconsumed.getPspcPmcalendarid());
				plmTlSpareconsumed.setPspcRemarks(plmTlWofeedback.getWofbRemarks());
				plmTlSpareconsumed.setPspcWodetailid(plmTlWofeedback.getWofbWodetailid());
				sqls.add(PlmTlSpareconsumedSql.getInsertSql(plmTlSpareconsumedSql.getPspcDbFields(),plmTlSpareconsumed.getSaveArray()));
			CommonMessage.debugMsg("after insert spare consumption");
			}
			PlmTlSparecostactual plmTlSparecostactual= plmTlWofeedback.getSpareCostActual();
			if(plmTlSparecostactual != null){
				plmTlSparecostactual.setPscaPmcalendarid(workOrderDetailsBean.getPmCalendarId());
				plmTlSparecostactual.setPscaRequestedby(plmTlWofeedback.getWofbCompletedby());
				CommonMessage.debugMsg("before spare actual insertion");
				sqls.add(PlmTlSparecostactualSql.getInsertSql(plmTlSparecostactualSql.getPscaDbFields(), plmTlSparecostactual.getSaveArray()));
				CommonMessage.debugMsg("after spare actual insertion");
			}
			/*End*/
			String upDateCal =  plmTlWofeedbacksql.updatePmCalendar();
			CommonMessage.debugMsg("1");
				/*"UPDATE PLM_TL_CALENDAR SET PMCL_FEEDBACKID = '"+plmTlWofeedback.getWofbFeedbackid()+"',"+
			"PMCL_STATUS = 'Y' ,PMCL_FEEDBACKDATE =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
			"PMCL_COMPLETEDBY = '"+plmTlWofeedback.getWofbCompletedby()+"',  PMCL_WORKSUMMARYID = '"+plmTlWorksummary.getWksmKeyid()+"',"+
			"PMCL_EFFPLANCOMPDATE = TO_DATE('07-"+workOrderDetailsBean.getFromMonth()+"','DD-MON-YYYY') , PMCL_DOWNTIME ='"+plmTlWofeedback.getWofbDuration()+"'  WHERE  PMCL_KEYID = '"+workOrderDetailsBean.getPmCalendarId()+"' ";
			sqls.add(upDateCal);*/
			String appendedMonth ="07-"+workOrderDetailsBean.getFromMonth()+" 00:00:00";
			CommonMessage.debugMsg("appendedMonth   :"+appendedMonth );
			CommonMessage.debugMsg("dateTime  :"+dateTime);
			CommonMessage.debugMsg("TIME STAMP   "+new java.sql.Timestamp( sdf.parse(dateTime).getTime()));
			CommonMessage.debugMsg("fbId = " +plmTlWofeedback.getWofbFeedbackid());
			CommonMessage.debugMsg("complBy = " +	plmTlWofeedback.getWofbCompletedby());
			CommonMessage.debugMsg("wksmId = " +plmTlWorksummary.getWksmKeyid());
			CommonMessage.debugMsg("frmMonth = " + new java.sql.Timestamp( sdf.parse( appendedMonth ).getTime()));
			CommonMessage.debugMsg("dwnTIme = " +plmTlWofeedback.getWofbDuration());
			CommonMessage.debugMsg("complBy = " +workOrderDetailsBean.getPmCalendarId());
			
			Object [] updateCalendar	= {plmTlWofeedback.getWofbFeedbackid(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbCompletedby(),plmTlWorksummary.getWksmKeyid(),  new java.sql.Timestamp( sdf.parse(appendedMonth).getTime()),plmTlWofeedback.getWofbDuration(),workOrderDetailsBean.getPmCalendarId()};
			CommonMessage.debugMsg("2.1");
			int [] dataTypeCal =  { Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.INTEGER,Types.VARCHAR};
			CommonMessage.debugMsg("2.2");
			sqls.add(upDateCal);
			valueList.add(updateCalendar);
			dataTypes.add(dataTypeCal);
			charList.add("Q");
			
			CommonMessage.debugMsg("3");
						
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
			
			String updatePlanConfig =plmTlWofeedbacksql.getUpdatPlanConfig();
			String firstAppndFrmMnth = "01-"+workOrderDetailsBean.getFromMonth()+" 00:00:00";
				/*"UPDATE PLM_TL_PLANCONFIGURATION SET PPLC_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') ,"+
				"PPLC_MONTHLY ='01-"+workOrderDetailsBean.getFromMonth()+"' WHERE  ( PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+plmTlWofeedback.getWofbMachineid()+"' || '"+
				workOrderDetailsBean.getAssmbleyId()+"' or PPLC_MACHINEID || PPLC_ASSEMBLYID ='"+plmTlWofeedback.getWofbMachineid()+"' || '{}'   ) ";*/
			CommonMessage.debugMsg("frmMonthFirst = " +firstAppndFrmMnth);
			CommonMessage.debugMsg("frmMonthFirst = " +new java.sql.Timestamp( sdf.parse(firstAppndFrmMnth).getTime()));
			Object [] updateplanConfig	= { new java.sql.Timestamp( sdf.parse(dateTime).getTime()), new java.sql.Timestamp( sdf.parse(firstAppndFrmMnth).getTime()) ,plmTlWofeedback.getWofbMachineid(),workOrderDetailsBean.getAssmbleyId(),plmTlWofeedback.getWofbMachineid()};
			int [] dataTypesplanConfig =  {Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			
			sqls.add(updatePlanConfig);
			valueList.add(updateplanConfig);
			dataTypes.add(dataTypesplanConfig);
			charList.add("Q");
			
			String updateWODetail =plmTlWofeedbacksql.getwoDetail();
				
				/*"UPDATE PLM_TL_WODTL SET PWDD_FEEDBACKID = '"+plmTlWofeedback.getWofbFeedbackid()+"',"+
			"PWDD_COMPLETEDBY = '"+plmTlWofeedback.getWofbCompletedby()+"',"+
			"PWDD_COMPLETEDDATE = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss'),"+
			"PWDD_STATUS = 'C'  WHERE PWDD_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			Object [] updateWODtl	= {plmTlWofeedback.getWofbFeedbackid(),plmTlWofeedback.getWofbCompletedby(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesWODetail =  {Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
			
			sqls.add(updateWODetail);
			valueList.add(updateWODtl);
			dataTypes.add(dataTypesWODetail);
			charList.add("Q");
			

			String updateWomst = plmTlWofeedbacksql.getupdtWomst();
				
				/*"UPDATE PLM_TL_WOMST SET PWDM_MODIFIEDON=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') , PWDM_WOSTATUS=  (  SELECT MAX(PWDD_STATUS)  FROM PLM_TL_WODTL"+
				" WHERE PWDD_WOMASTERID = PWDM_WORKORDERNO  )  WHERE PWDM_WORKORDERNO ='"+workOrderDetailsBean.getWOmstId()+"'";*/
			CommonMessage.debugMsg("womstdate "+new java.sql.Timestamp( sdf.parse(dateTime).getTime()));
			CommonMessage.debugMsg("womstid "+workOrderDetailsBean.getWOmstId());
			Object [] updteWOMst	= {new java.sql.Timestamp( sdf.parse(dateTime).getTime()),workOrderDetailsBean.getWOmstId()};
			int [] dataTypesWOMst =  {Types.TIMESTAMP,Types.VARCHAR};
			
			sqls.add(updateWomst); 
			valueList.add(updteWOMst);
			dataTypes.add(dataTypesWOMst);
			charList.add("Q");
			
			String updateWOfeed = plmTlWofeedbacksql.updateWofeed(); 
				/*"UPDATE PLM_TL_WOFEEDBACK SET  WOFB_MODIFIEDON  =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WOFB_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			
			Object [] updateFeed	= {new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesFeed =  {Types.TIMESTAMP,Types.VARCHAR};
			sqls.add(updateWOfeed); 
			valueList.add(updateFeed);
			dataTypes.add(dataTypesFeed);
			charList.add("Q");
			
			String updateWOSum = plmTlWofeedbacksql.getwosum();
			/*" UPDATE PLM_TL_WORKSUMMARY SET WKSM_MODIFIEDON = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss') WHERE   WKSM_WODETAILID = '"+plmTlWofeedback.getWofbWodetailid()+"'";*/
			Object [] updateSumry	= { new java.sql.Timestamp( sdf.parse(dateTime).getTime()),plmTlWofeedback.getWofbWodetailid()};
			int [] dataTypesSumry=  {Types.TIMESTAMP,Types.VARCHAR};
			sqls.add(updateWOSum); 
			valueList.add(updateSumry);
			dataTypes.add(dataTypesSumry);
			charList.add("Q");
			String updateWOrelease = plmTlWofeedbacksql.getUpdtRelease();
			/*"UPDATE PLM_TL_WORKRELEASE SET  PMWR_COMPLETEDBY ='"+plmTlWofeedback.getWofbCompletedby()+"' , PMWR_COMPLETEDFLAG ='Y' ,  PMWR_COMPLETEDDATE=to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+
			",PMWR_STATUS ='W'  WHERE PMWR_PMWORKORDERNO ='"+workOrderDetailsBean.getWOmstId()+"'";*/
			
			
			Object [] updaterelease	= {plmTlWofeedback.getWofbCompletedby(), new java.sql.Timestamp( sdf.parse(dateTime).getTime()),workOrderDetailsBean.getWOmstId()};
			int [] dataTypesrelease=  {Types.VARCHAR,Types.TIMESTAMP,Types.VARCHAR};
			sqls.add(updateWOrelease ); 
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
			CommonMessage.debugMsg("paramValuesinsertPCS   "+paramValues);
			CommonMessage.debugMsg("getDBLocation()  :"+workOrderDetailsBean.getDBLocation());
			if(workOrderDetailsBean.getDBLocation().equals("VASAI")){
				CommonMessage.debugMsg("dblocation :Vasai");
				String parameterCode = "PM";
			    String insertPCSSql = 	"PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEMSR";
			    Object [] insertPCSDatas = {plmTlWofeedback.getWofbMachineid(),plmTlWofeedback.getWofbMachinetakeovertime(),plmTlWofeedback.getWofbProdstartdate(),workOrderDetailsBean.getWOmstId(),parameterCode,plmTlWofeedback.getWofbCreatedby()};
			    int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				sqls.add(insertPCSSql);
				valueList.add(insertPCSDatas);
				dataTypes.add(insertPCSTypes);
				charList.add("P");
			}
		    //CommonMessage.debugMsg("retval    :"+retval.get(0)[0]);
			char [] sqlType = new char[charList.size()];
			for(int c=0;c<sqlType.length;c++)
			{
				sqlType[c] = charList.get(c).charAt(0);
				CommonMessage.debugMsg(c +" : "+sqlType[c]);
			}
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
			CommonMessage.debugMsg("end of save");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return plmTlWofeedback;
		
	}

	@Override
	public String savegrdWOD(List<PlmTlWofeedback> newPlmTlWofeedbackList,
			WorkOrderDetailsBean workOrderDetailsBean) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String dateTime = CommonFunctions.dateTimeNow();
		
		try{
			CommonMessage.debugMsg("inside DAOIMPL of save grd save");
			//List<PlmTlWofeedback> plmTlWofeedbackList = newworkOrderFormBean.getPlmtlFeedback();
			//CommonMessage.debugMsg("Test Data   :"+newPlmTlWofeedback.getWofbEnddate());
			//CommonMessage.debugMsg("FeedbackList  Size   :"+plmTlWofeedbackList.size());
			List<WorkOrderDetailsLstBean> gridfara = workOrderDetailsBean.getGrdWoBean();
			PlmTlCbmwocompdtl newPlmTlCbmwocompdtl = new PlmTlCbmwocompdtl(); 
		
			for(PlmTlWofeedback plmTlWofeedback:newPlmTlWofeedbackList ){
					plmTlWofeedback.setWofbFeedbackid(dbActionTemplate.getSequenceNumber(PlmTlWofeedbackSql.TBL_PLM_TL_WOFEEDBACK, 11, "WFB", "YYMM", "Y")); // set the sequnce number
                 	plmTlWorksummary.setWksmWodetailid(plmTlWofeedback.getWofbWodetailid());
					plmTlWorksummary.setWksmKeyid((dbActionTemplate.getSequenceNumber(PlmTlWorksummarySql.TBL_PLM_TL_WORKSUMMARY, 11, "WOS", "YYMM", "Y"))); // set the sequnce number
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
					CommonMessage.debugMsg("Det Id    :"+plmTlWofeedback.getWofbWodetailid());
					String delFeedBack =plmTlWorksummarysql.getDelFeedBack(plmTlWofeedback.getWofbWodetailid());
					String delWoSumry =plmTlWorksummarysql.getDelWoSumry(plmTlWofeedback.getWofbWodetailid());
					sqls.add( delWoSumry );
					sqls.add( delFeedBack );
					
					sqls.add(PlmTlWofeedbackSql.getInsertSql(plmTlWofeedbacksql.getWofbDbFields(), plmTlWofeedback.getSaveArray())); // add insert sql for master table
					sqls.add(PlmTlWorksummarySql.getInsertSql(plmTlWorksummarysql.getWksmDbFields(), plmTlWorksummary.getSaveArray())); // add insert sql for detail table
					//detail table
					if(UIUtils.isValidKeyId(workOrderDetailsBean.getAdjustReading())){
						fillCbmWoDtls(newPlmTlCbmwocompdtl,workOrderDetailsBean,plmTlWofeedback.getWofbCreatedby(),plmTlWofeedback.getWofbFeedbackid());
						sqls.add(PlmTlCbmwocompdtlSql.getInsertSql(plmTlCbmwocompdtlsql.getCmcdDbFields(), newPlmTlCbmwocompdtl.getSaveArray()));
					}
					
					/*Spares table insert*/
					PlmTlSpareconsumed plmTlSpareconsumed = plmTlWofeedback.getSpareConsumed();
					CommonMessage.debugMsg("before spare consumed " + plmTlSpareconsumed);	
				
					PlmTlSparecostactual plmTlSparecostactual= plmTlWofeedback.getSpareCostActual();
					
					/*End*/
					for(WorkOrderDetailsLstBean workOrderDetailsBean1:gridfara)
					{
						for(int i=0;i<newPlmTlWofeedbackList.size();i++){
							workOrderDetailsBean1.getMachineId();
							workOrderDetailsBean1.getFromDate();
							CommonMessage.debugMsg("jobType  grid     :-" +workOrderDetailsBean1.getJobtype());
							CommonMessage.debugMsg("PmCalId  grid     :-" +workOrderDetailsBean1.getPmCalendarId());
							CommonMessage.debugMsg("PmStdId  grid     :-" +workOrderDetailsBean1.getPmstandId());
							CommonMessage.debugMsg("Wodetailid  grid     :-" +workOrderDetailsBean1.getWksmWodetailid());
							CommonMessage.debugMsg("WofbAction  grid     :-" +workOrderDetailsBean1.getMachineId());
						CommonMessage.debugMsg("getFromMonth() "+workOrderDetailsBean1.getFromMonth());
						CommonMessage.debugMsg("PmCalendarId  ::"+workOrderDetailsBean1.getPmCalendarId());
						CommonMessage.debugMsg("AssmbleyId  ::"+workOrderDetailsBean1.getAssmbleyId());
						CommonMessage.debugMsg("WOmstId  ::"+workOrderDetailsBean1.getWOmstId());
						CommonMessage.debugMsg("WofbDuration  ::"+plmTlWofeedback.getWofbDuration());
						
						if(plmTlSparecostactual != null){
							CommonMessage.debugMsg("PmCalId  plmTlSparecostactual     :-" +workOrderDetailsBean.getPmCalendarId());
							plmTlSparecostactual.setPscaPmcalendarid(workOrderDetailsBean1.getPmCalendarId());
							plmTlSparecostactual.setPscaRequestedby(plmTlWofeedback.getWofbCompletedby());
							CommonMessage.debugMsg("before spare actual insertion");
							sqls.add(PlmTlSparecostactualSql.getInsertSql(plmTlSparecostactualSql.getPscaDbFields(), plmTlSparecostactual.getSaveArray()));
							CommonMessage.debugMsg("after spare actual insertion");
						}
						if(plmTlSpareconsumed != null){
							plmTlSpareconsumed.setPspcKeyid((dbActionTemplate.getSequenceNumber(PlmTlSpareconsumedSql.TBL_PLM_TL_SPARECONSUMED,  12, "PSU", "YY", "Y"))); // set the sequnce number
							CommonMessage.debugMsg("after spare consumed");
							plmTlSpareconsumed.setPspcPmcalendarid(workOrderDetailsBean1.getPmCalendarId());
							CommonMessage.debugMsg(plmTlSpareconsumed.getPspcPmcalendarid());
							plmTlSpareconsumed.setPspcRemarks(plmTlWofeedback.getWofbRemarks());
							plmTlSpareconsumed.setPspcWodetailid(plmTlWofeedback.getWofbWodetailid());
							sqls.add(PlmTlSpareconsumedSql.getInsertSql(plmTlSpareconsumedSql.getPspcDbFields(),plmTlSpareconsumed.getSaveArray()));
						CommonMessage.debugMsg("after insert spare consumption");
						}
						String upDateCal = plmTlWorksummarysql.updtCal(plmTlWofeedback.getWofbFeedbackid(),dateTime,plmTlWofeedback.getWofbCompletedby(),plmTlWorksummary.getWksmKeyid(),workOrderDetailsBean1.getFromMonth(),plmTlWofeedback.getWofbDuration(),workOrderDetailsBean1.getPmCalendarId());
						sqls.add(upDateCal);
						/*String updatePmstd =" UPDATE PLM_TL_STANDARDS SET PMSD_LASTDONEDATE = '"+plmTlWofeedback.getWofbStartdate()+"',  PMSD_LASTDONEWEEKNO = 4 ,"+
											"PMSD_NEXTDUEDATE = '01-"+newworkOrderDetailsBean1.getFromDate()+"' ,PMSD_NEXTDUEWEEKNO = 1 , WHERE PMSD_KEYID = '"+workOrderDetailsBean1.getPmStdId()+"'";
						//sqls.add(updatePmstd);*/
						String updatePlanConfig =  plmTlWorksummarysql.updtPlanConfig(dateTime,workOrderDetailsBean.getFromMonth(),plmTlWofeedback.getWofbMachineid(),workOrderDetailsBean1.getAssmbleyId());
						sqls.add(updatePlanConfig); 
						String updateWODetail = plmTlWorksummarysql.updtWOdtl(plmTlWofeedback.getWofbFeedbackid(),plmTlWofeedback.getWofbCompletedby(),dateTime,plmTlWofeedback.getWofbWodetailid());
						sqls.add(updateWODetail);
						String updateWomst = plmTlWorksummarysql.updtWOmst(dateTime,workOrderDetailsBean1.getWOmstId());
						sqls.add(updateWomst); 
						String updateWOfeed = plmTlWorksummarysql.updtWOfeed(dateTime,plmTlWofeedback.getWofbWodetailid());
						sqls.add(updateWOfeed);
						String updateWOSum =plmTlWorksummarysql.updtWOsum(dateTime,plmTlWofeedback.getWofbWodetailid());
						sqls.add(updateWOSum);
						String updateWOrelease =plmTlWorksummarysql.updtWOrelease(plmTlWofeedback.getWofbCompletedby(),dateTime,workOrderDetailsBean1.getWOmstId());
						sqls.add(updateWOrelease );
						}
				}
			}
			dbActionTemplate.executeStatements(sqls); 
			CommonMessage.debugMsg("After saving stmts");
			
			return  "Success";
	}
		catch(Exception e)
		{
			 
			 return e.getMessage();
		}
		
		
	}
	private void fillCbmWoDtls(PlmTlCbmwocompdtl newPlmTlCbmwocompdtl,
			WorkOrderDetailsBean workOrderDetailsBean, String wofbCreatedby,
			String wofbFeedbackid) {
			String dateTime = CommonFunctions.dateTimeNow();
		
		try {
			newPlmTlCbmwocompdtl.setCmcdKeyid((dbActionTemplate.getSequenceNumber(PlmTlCbmwocompdtlSql.TBL_PLM_TL_CBMWOCOMPDTL, 15, "CBM", "YYMM", "Y")));
			newPlmTlCbmwocompdtl.setCmcdActive("Y");
			newPlmTlCbmwocompdtl.setCmcdActivitydate(Constants.passNullDate);
			newPlmTlCbmwocompdtl.setCmcdAdjustedreading(workOrderDetailsBean.getAdjustReading());
			newPlmTlCbmwocompdtl.setCmcdCreatedby(wofbCreatedby);
			newPlmTlCbmwocompdtl.setCmcdCreatedon(dateTime);
			newPlmTlCbmwocompdtl.setCmcdCurrentreading(workOrderDetailsBean.getCurrentReading());
			newPlmTlCbmwocompdtl.setCmcdMaximumreading(workOrderDetailsBean.getMaximumReading());
			newPlmTlCbmwocompdtl.setCmcdMiddlemax("{}");
			newPlmTlCbmwocompdtl.setCmcdMinimumreading(workOrderDetailsBean.getMinmumReading());
			newPlmTlCbmwocompdtl.setCmcdModifiedon(dateTime);
			newPlmTlCbmwocompdtl.setCmcdNextduedate(workOrderDetailsBean.getNextdueDate());
			newPlmTlCbmwocompdtl.setCmcdPmcalendarid(workOrderDetailsBean.getPmCalendarId());
			newPlmTlCbmwocompdtl.setCmcdPmentrytype("P");
			newPlmTlCbmwocompdtl.setCmcdPmjobtype("CBM");
			newPlmTlCbmwocompdtl.setCmcdPmstandardid(workOrderDetailsBean.getPmStdId());
			newPlmTlCbmwocompdtl.setCmcdTempfield1("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield2("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield3("-");
			newPlmTlCbmwocompdtl.setCmcdTempfield4("-");
			newPlmTlCbmwocompdtl.setCmcdWofeedbackid(wofbFeedbackid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // set the sequnce number
		
		
	}

	@Override
	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(updateWoId.replace("`", "'")+"~~"+workorderno+"~~~~"+allotedtocombo);
		updateWoId=updateWoId.replace("`", "'");
     try{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
        //String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_PREPAREDBY '"+allotedtocombo+"'  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
		 String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_ALLOTEDTO = '"+allotedtocombo+"'  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
        String updtpmcal="UPDATE PLM_TL_CALENDAR SET PMCL_STATUS = 'X',PMCL_SCHEDULEDFROM = PMCL_FROMDATE,"+
		 "PMCL_SCHEDULEDTILL=PMCL_TILLDATE, PMCL_SCHEDULEDWEEK = PMCL_MONTHWEEK , PMCL_WORKORDERID = '{}',"+
		 "PMCL_ALLOTTEDTO = '"+allotedtocombo+"'  WHERE PMCL_WORKORDERID IN ( "+updateWoId+")";

      // sqls.add(updtpmcal);
        sqls.add(updtwomst);
        CommonMessage.debugMsg(updtwomst);
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
		CommonMessage.debugMsg(cancelWoId.replace("`", "'")+"~~"+workorderno);
		cancelWoId=cancelWoId.replace("`", "'");
		
		
     try{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
        String updtwomst="UPDATE PLM_TL_WOMST SET PWDM_NUMOFACTIVITIES = PWDM_NUMOFACTIVITIES - "+noofActivites+"  WHERE   PWDM_WORKORDERNO = '"+workorderno+"'";
        String delsprcostplan="DELETE FROM WOM_TL_SPARECOSTPLAN WHERE WSCP_WOID = '"+workorderno+"'";
        String delUtilitcstpln="DELETE FROM WOM_TL_UTILITYCOSTPLAN WHERE UTCP_WOKEYID = '"+workorderno+"'";
        String delothrcstplan="DELETE FROM WOM_TL_OTHERCOSTPLAN WHERE OTCP_WOID = '"+workorderno+"'";
		String delservcCostpln="DELETE FROM WOM_TL_SERVICECOSTPLAN WHERE SVCP_WOID = '"+workorderno+"'";
		String delmanpwrcstpln="DELETE FROM WOM_TL_MANPOWERCOSTPLAN WHERE MPCP_WOID = '"+workorderno+"'";
		String delwomst ="DELETE FROM PLM_TL_WOMST WHERE PWDM_WORKORDERNO  = '"+workorderno+"'  AND PWDM_WOSTATUS = 'P'"; 
		String delwodtl ="DELETE FROM PLM_TL_WODTL WHERE PWDD_WODETAILID IN ("+cancelWoId+")";
		//String delwofeedbck="DELETE FROM PLM_TL_WOFEEDBACK WHERE WOFB_WODETAILID IN("+cancelWoId+")";
		String updtpmcal="UPDATE PLM_TL_CALENDAR SET PMCL_STATUS = 'X',PMCL_SCHEDULEDFROM = PMCL_FROMDATE,"+
						 "PMCL_SCHEDULEDTILL=PMCL_TILLDATE, PMCL_SCHEDULEDWEEK = PMCL_MONTHWEEK , PMCL_WORKORDERID = '{}',"+
						 "PMCL_ALLOTTEDTO = '{}'  WHERE PMCL_WORKORDERID IN ( "+cancelWoId+")";
		
		sqls.add(updtpmcal);
		//sqls.add(delwofeedbck);
		sqls.add(delwodtl);
		sqls.add(delwomst);
		sqls.add(delmanpwrcstpln);
		sqls.add(delservcCostpln);
		sqls.add(delothrcstplan);
		sqls.add(delUtilitcstpln);
		sqls.add(delsprcostplan);
		sqls.add(updtwomst);
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
			String date = params[0];
			CommonMessage.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]);
			CommonMessage.debugMsg("date  :"+selActforWoGen);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			
			inParams.add("01-"+date);
			inParams.add(selActforWoGen);
			inParams.add(description);
			inParams.add(allotedto);
			CommonMessage.debugMsg("procedure before :"+inParams.size());
			CommonMessage.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDID", inParams, outParam);
			CommonMessage.debugMsg("procedure returned" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			CommonMessage.debugMsg("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception  :"+e.getMessage());
			return "wonotgenerated";
		}
		
	}

	@Override
	public String saveReschedule(String datas) throws Exception {
		// TODO Auto-generated method stub
		
		String[] getData = datas.split("/");
		CommonMessage.debugMsg(getData[0]+"--"+getData[1]+"--"+getData[2]+"--"+getData[3]+"--"+getData[4]+"--"+getData[5]+"--"+getData[6]+"--"+getData[7]+"--"+getData[8]+"--"+getData[getData.length-1]);
		
		List<String> inParams = new ArrayList<String>();
		Object [] outParam = new Object [1];
		for(int i= 0;i<=getData.length-1;i++){
			CommonMessage.debugMsg("getData["+i+"]---  "+getData[i]);
			inParams.add(getData[i]);
		}
		//inParams.add();
		CommonMessage.debugMsg("procedure before :"+inParams.size());
		CommonMessage.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
		try{
			dbActionTemplate.processPLSQLProcedures("RESCHEDULESAVE", inParams, outParam);
			CommonMessage.debugMsg("procedure returned"+ outParam[0]+"  --  "+(outParam[0].equals("-2")));
			if(outParam[0].equals("-2"))
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
				//CommonMessage.debugMsg("cal Row == "+getCalData.get(i)[j]);
			//	CommonMessage.debugMsg("te   "+getCalData.get(i)[24]);
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
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCreatedby()))
					newPlmTlRescheduledtl.setPrsrCreatedby("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCalendarstatus()))
					newPlmTlRescheduledtl.setPrsrCalendarstatus("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCurrentstartdate()))
					newPlmTlRescheduledtl.setPrsrCurrentstartdate(dateTime);
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrCurrentenddate()))
					newPlmTlRescheduledtl.setPrsrCurrentenddate(Constants.futureNullDate);
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmactivitytype()))
					newPlmTlRescheduledtl.setPrsrPmactivitytype("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmcalendarid()))
					newPlmTlRescheduledtl.setPrsrPmcalendarid("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPmdetailsid()))
					newPlmTlRescheduledtl.setPrsrPmdetailsid("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPrestartdate()))
					newPlmTlRescheduledtl.setPrsrPrestartdate(dateTime);
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrPreenddate()))
					newPlmTlRescheduledtl.setPrsrPreenddate(Constants.futureNullDate);
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrReason()))
					newPlmTlRescheduledtl.setPrsrReason("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRemarks()))
					newPlmTlRescheduledtl.setPrsrRemarks("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrReschedulereason()))
					newPlmTlRescheduledtl.setPrsrReschedulereason("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRescheduleremarks()))
					newPlmTlRescheduledtl.setPrsrRescheduleremarks("{}");
					newPlmTlRescheduledtl.setPrsrRescheduletype("postponed");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrResponsibilityid()))
					newPlmTlRescheduledtl.setPrsrResponsibilityid("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrRevisedbyid()))
					newPlmTlRescheduledtl.setPrsrRevisedbyid("{}");
				if(!UIUtils.isValidKeyId(newPlmTlRescheduledtl.getPrsrStatus()))
					newPlmTlRescheduledtl.setPrsrStatus("{}");
				//CommonMessage.debugMsg("before list calendar ID   :_"+newPlmTlRescheduledtl.getPrsrPmcalendarid());
				
					
			//}
			   
			 CommonMessage.debugMsg("calender ID   :_"+newPlmTlRescheduledtl.getPrsrPmcalendarid());
			 plmTlRescheduledtlList.add(newPlmTlRescheduledtl);
		}
			
			
			 //CommonMessage.debugMsg("plmTlRescheduledtlList"+plmTlRescheduledtlList.get(i).getPrsrPmcalendarid());
			 for(PlmTlRescheduledtl plmTlRescheduledtl :plmTlRescheduledtlList ){
					
					CommonMessage.debugMsg("LIST DATA  "+plmTlRescheduledtl.getPrsrPmcalendarid());
					
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
		if(UIUtils.isValidKeyId(weekNO))
			condParms += ";WEEKNO="+weekNO;
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("After  getAllModifyForm" );
		List<String[]> dataList =  dbActionTemplate.processFunctionCalls("ABN_PC_ABNORMALITY.WO_FN_GetSqlForFillSpread", paramValues);
		return dataList;
		}

	@Override
	public String modifyCompWo(WorkOrderDetailsLstBean newworkOrderDetailsBean,
			String completedBy) throws Exception {
		// TODO Auto-generated method stub
		try{ 
			String dateTime = CommonFunctions.dateTimeNow();
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			CommonMessage.debugMsg(newworkOrderDetailsBean.getWksmWodetailid()+"-----"+completedBy);
			String sqlWoDtl = "Update "+TableNames.TBL_PLM_TL_WODTL+" set PWDD_COMPLETEDBY = '"+completedBy+"',  PWDD_COMPLETEDDATE =  to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+" where  PWDD_WODETAILID in ('"+newworkOrderDetailsBean.getWksmWodetailid()+"')";
			String sqlWoFeedBack ="Update "+TableNames.TBL_PLM_TL_WOFEEDBACK+" set WOFB_COMPLETEDBY = '"+completedBy+"' ,  WOFB_COMPLETEDDATE  = to_date('"+dateTime+"','dd-Mon-yyyy hh24:mi:ss')"+" where  WOFB_WODETAILID in ('"+newworkOrderDetailsBean.getWksmWodetailid()+"')"; 
			CommonMessage.debugMsg(sqlWoFeedBack);
			CommonMessage.debugMsg(sqlWoDtl);
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
			CommonMessage.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]);
			CommonMessage.debugMsg("date  :"+selActforWoGen);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			inParams.add(weekno);
			inParams.add(date);
			inParams.add(selActforWoGen);
			inParams.add(description);
			inParams.add(allotedto);
			CommonMessage.debugMsg("procedure before :"+inParams.size());
			CommonMessage.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDABN", inParams, outParam);
			CommonMessage.debugMsg("procedure returned" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			CommonMessage.debugMsg("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception  :"+e.getMessage());
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
		CommonMessage.debugMsg("kAIZEN ~~~ --"+kAIZEN);
		
		if(UIUtils.isValidKeyId(kAIZEN))
			condParms += ";KAIZEN=Y";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> woupdtCanclSelList =dbActionTemplate.processFunctionCalls("PLM_PC_PMWORKORDRER.PLM_FN_GETACTIVITYSQLFORWO", paramValues);
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
			CommonMessage.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]+"--"+params[3]);
			CommonMessage.debugMsg("date  :"+selActforWoKzn);
			List<String> inParams = new ArrayList<String>();
			Object [] outParam = {1,"PMWOMID"};
			inParams.add(weekno);
			inParams.add(date);
			inParams.add(selActforWoKzn);
			inParams.add(description);
			inParams.add(allotedto);
			CommonMessage.debugMsg("procedure before :"+inParams.size());
			CommonMessage.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3)+"--"+inParams.get(4));
			dbActionTemplate.processPLSQLProcedures("WOGENforSELECTEDKZN ", inParams, outParam);
			CommonMessage.debugMsg("procedure returned kzn  :" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			CommonMessage.debugMsg("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception  :"+e.getMessage());
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
			
			 CommonMessage.debugMsg("Parameters" + condParms);
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
			CommonMessage.debugMsg(" Excel Report  in dao impl ="+colmodel);
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
				   CommonMessage.debugMsg("sql  "+sql);*/
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
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return operator;
 }

	@Override
	public String generateWOSDM(String selActforWoGen, String paramsVal)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Procedure called  PLM_PR_GENSHUTDOWNCAL");
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
			CommonMessage.debugMsg("standrd keyID  :"+params[0]+"--"+params[1]+"--"+params[2]);
			CommonMessage.debugMsg("date  :"+selActforWoGen);
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
			 
			CommonMessage.debugMsg("procedure before :"+inParams.size());
			CommonMessage.debugMsg("list vals  :"+inParams.get(0)+"--"+inParams.get(1)+"--"+inParams.get(2)+"--"+inParams.get(3));
			dbActionTemplate.processPLSQLProcedures("PLM_PR_GENSHUTDOWNCAL", inParams, outParam);
			CommonMessage.debugMsg("procedure returned" +outParam.length+"-=-=-="+outParam[0]+"-=-=-"+outParam[1]);
			String woGenerated = outParam[1].toString();
			CommonMessage.debugMsg("woGenerated :"+woGenerated);
			return woGenerated;
		}
		catch(Exception e){
			CommonMessage.debugMsg("Exception  :"+e.getMessage());
			return "wonotgenerated";
		}
	}

	@Override
	public List<String[]> getdatapopup(String indicatorId,String flag) throws Exception {
		
		String sqls = "";
		List<String[]> getSop = new ArrayList<String[]>(); 
	
		MspTlIndicatorsDtlSql newMspTlIndicatorsDtlSql=new MspTlIndicatorsDtlSql();
		
			
		
		//	String inactFun = dbActionTemplate.getSingleValue(" SELECT count (*)  from " + TBL_GEN_TL_FUNCTIONALLOCN + " where FNLN_ORIGINALID =  '"+originalId+"' ");
			String sql =MspTlIndicatorsDtlSql.getdatapopup(indicatorId,flag);
			CommonMessage.debugMsg(" sql "+sql);
			getSop=dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg(" getSop "+getSop.size());
	
			return getSop;
	
}
}
	

