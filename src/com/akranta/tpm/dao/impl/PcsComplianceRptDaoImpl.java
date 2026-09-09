
package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.NoPlanSaveBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsComplianceRptDao;

import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PcsTlMstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class PcsComplianceRptDaoImpl implements PcsComplianceRptDao {
	
	private DBActionTemplate dbActionTemplate; 
	
	public PcsComplianceRptDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String []> getpcsRpt(CommonFilter commonFilter,String from) throws Exception
	{
		CommonMessage.debugMsg("from:"+from);
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("relCondStr="+paramValues);
			CommonMessage.debugMsg("relCondStr="+paramValues);
			List<String[]> topFailList=null;
			if(from.equals("pcsCompliance"))
				 topFailList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_COMPLIANCE", paramValues);
			else if(from.equals("noPlanEntry"))
			{
				if(condParms.contains("SECTIONID"))
				topFailList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_PCSNOPLANSPREAD", paramValues);
			}
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
		  		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
		    return topFailList;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public Workbook getpcscompExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat,String from) throws Exception 
	{
		 ResultSet rs = null;
		 ExcelUtils excelUtils = new ExcelUtils(colmodel);
		 try
		 {
			if(from.equals("pcsCompliance"))
			{
				rs =   getdownTimeReportResultSet(commonFilter);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				XLConditionalFormats NoPlanExists = new XLConditionalFormats();
				
				XLConditionalFormats condForma = new XLConditionalFormats();
				condForma.setFontColor(new RGB(0,0,254)); //red font
				condForma.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condForma.setFontHeightPoint((short)14);
				condForma.setFontBoldWeight((short)20);
				condForma.setFromCol(3);
				condForma.setToCol(-1);
				condForma.setOperator(ComparisonOperator.EQUAL);
				condForma.setCondValue("1"); //Tick
				condForma.setIdentfier("1");
				condForma.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				condFormats.add(condForma);
				
				/*NoPlanExists.setFontColor(new RGB(0,0,254)); //red font
				NoPlanExists.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				NoPlanExists.setFontHeightPoint((short)14);
				NoPlanExists.setFontBoldWeight((short)20);
				NoPlanExists.setFromCol(3);
				NoPlanExists.setToCol(-1);
				NoPlanExists.setOperator(ComparisonOperator.EQUAL);
				NoPlanExists.setCondValue("1"); //Tick
				NoPlanExists.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				NoPlanExists.setIdentfier("1");
				condFormats.add(NoPlanExists);*/
				XLConditionalFormats condFormatsTick = new XLConditionalFormats();
				NoPlanExists.setFontColor(new RGB(0,0,254)); //red font
				NoPlanExists.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				NoPlanExists.setFontHeightPoint((short)14);
				NoPlanExists.setFontBoldWeight((short)20);
				NoPlanExists.setFromCol(3);
				NoPlanExists.setToCol(-1);
				NoPlanExists.setOperator(ComparisonOperator.EQUAL);
				NoPlanExists.setCondValue("4"); //Tick
				NoPlanExists.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				NoPlanExists.setIdentfier("4");
				condFormats.add(condFormatsTick);
				
				XLConditionalFormats HolidayTick = new XLConditionalFormats();
				HolidayTick.setFontColor(new RGB(0,254,0)); //red font
				HolidayTick.setBgColor(new RGB(153, 153, 153));//Grey Color
				HolidayTick.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				HolidayTick.setFontHeightPoint((short)14);
				HolidayTick.setFontBoldWeight((short)20);
				HolidayTick.setFromCol(3);
				HolidayTick.setToCol(-1);
				HolidayTick.setOperator(ComparisonOperator.EQUAL);
		 		HolidayTick.setCondValue("2"); //Tick
		 		HolidayTick.setSymbolStr(" ");
		 		HolidayTick.setIdentfier("2");
		 		condFormats.add(HolidayTick);
		 		
		 		XLConditionalFormats Holiday = new XLConditionalFormats();
		 		Holiday.setFontColor(new RGB(254,0,0)); //red font
		 		Holiday.setBgColor(new RGB(157, 153, 242));//Purple Color
		 		Holiday.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		 		Holiday.setFontHeightPoint((short)14);
		 		Holiday.setFontBoldWeight((short)20);
		 		Holiday.setFromCol(3);
		 		Holiday.setToCol(-1);
		 		Holiday.setOperator(ComparisonOperator.EQUAL);
		 		Holiday.setCondValue("3"); //Tick
		 		Holiday.setSymbolStr(" ");
		 		Holiday.setIdentfier("3");
		 		condFormats.add(Holiday);
		 		
		 		XLConditionalFormats condFormat1 = new XLConditionalFormats();
		 		condFormat1.setFontColor(new RGB(0,0,254)); //red font
		 		condFormat1.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		 		condFormat1.setFontHeightPoint((short)14);
		 		condFormat1.setFontBoldWeight((short)20);
		 		condFormat1.setFromCol(3);
		 		condFormat1.setToCol(-1);
		 		condFormat1.setOperator(ComparisonOperator.EQUAL);
		 		condFormat1.setCondValue("5"); //Tick
		 		condFormat1.setIdentfier("5");
		 		condFormat1.setSymbolStr(" ");
		 		condFormats.add(condFormat1);
		 		
		 		XLConditionalFormats condFormat2 = new XLConditionalFormats();
		 		condFormat2.setFontColor(new RGB(0,0,254)); //red font
		 		condFormat2.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		 		condFormat2.setFontHeightPoint((short)14);
		 		condFormat2.setFontBoldWeight((short)20);
		 		condFormat2.setFromCol(3);
				condFormat2.setToCol(-1);
				condFormat2.setOperator(ComparisonOperator.EQUAL);
				condFormat2.setCondValue("6"); //Tick
				condFormat2.setIdentfier("6");
				condFormat2.setSymbolStr(" ");
				condFormats.add(condFormat2);
				XLConditionalFormats condFormat3 = new XLConditionalFormats();
				condFormat3.setFontColor(new RGB(0,0,254)); //red font
				condFormat3.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condFormat3.setFontHeightPoint((short)14);
				condFormat3.setFontBoldWeight((short)20);
				condFormat3.setFromCol(3);
				condFormat3.setToCol(-1);
				condFormat3.setOperator(ComparisonOperator.EQUAL);
				condFormat3.setCondValue("8"); //Tick
				condFormat3.setIdentfier("8");
				condFormat3.setSymbolStr(" ");
				condFormats.add(condFormat3);
				
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
				
			}
			else if(from.equals("noPlanEntry"))
			{	
				rs=getdownTimeReportResultSetNoPlan(commonFilter);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				XLConditionalFormats NoPlanExists = new XLConditionalFormats();
				NoPlanExists.setFontColor(new RGB(0,0,254)); //red font
				NoPlanExists.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				NoPlanExists.setFontHeightPoint((short)14);
				NoPlanExists.setFontBoldWeight((short)20);
				NoPlanExists.setFromCol(3);
				NoPlanExists.setToCol(-1);
				NoPlanExists.setOperator(ComparisonOperator.EQUAL);
				NoPlanExists.setCondValue("-2"); //Tick
				NoPlanExists.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				NoPlanExists.setIdentfier("1");
				condFormats.add(NoPlanExists);
				XLConditionalFormats Holiday = new XLConditionalFormats();
				Holiday.setFontColor(new RGB(254,0,0)); //red font
				Holiday.setBgColor(new RGB(157, 153, 242 ));
				Holiday.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				Holiday.setFontHeightPoint((short)14);
				Holiday.setFontBoldWeight((short)20);
				Holiday.setFromCol(3);
				Holiday.setToCol(-1);
				Holiday.setOperator(ComparisonOperator.EQUAL);
				Holiday.setCondValue("-3"); //Tick
				Holiday.setSymbolStr(" ");
				Holiday.setIdentfier("Holiday");
				condFormats.add(Holiday);
				XLConditionalFormats NoPlan = new XLConditionalFormats();
				NoPlan.setFontColor(new RGB(0,0,254)); //red font
				NoPlan.setBgColor(new RGB(169 ,169 ,169));
				NoPlan.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				NoPlan.setFontHeightPoint((short)14);
				NoPlan.setFontBoldWeight((short)20);
				NoPlan.setFromCol(3);
				NoPlan.setToCol(-1);
				NoPlan.setOperator(ComparisonOperator.EQUAL);
				NoPlan.setCondValue("0"); //Tick
				NoPlan.setIdentfier("NoPlan");
				NoPlan.setSymbolStr(" ");
				condFormats.add(NoPlan);
				XLConditionalFormats pCSExists = new XLConditionalFormats();
				pCSExists.setFontColor(new RGB(51, 153, 51)); //red font
				pCSExists.setFontName(XLConditionalFormats.FONT_DEFAULT);
				pCSExists.setBgColor(new RGB(51, 153, 51));
				pCSExists.setFontHeightPoint((short)14);
				pCSExists.setFontBoldWeight((short)20);
				pCSExists.setFromCol(3);
				pCSExists.setToCol(-1);
				pCSExists.setOperator(ComparisonOperator.EQUAL);
				pCSExists.setCondValue("-1"); 
				pCSExists.setSymbolStr("");	
				pCSExists.setIdentfier("pCSExists");
				condFormats.add(pCSExists);
				XLConditionalFormats partialNoPlan = new XLConditionalFormats();
				partialNoPlan.setFontColor(new RGB(0,0,254)); //red font
				partialNoPlan.setFontName(XLConditionalFormats.FONT_WINGDINGS_2);
				partialNoPlan.setFontHeightPoint((short)10);
				partialNoPlan.setFontBoldWeight((short)20);
				partialNoPlan.setFromCol(3);
				partialNoPlan.setToCol(-1);
				partialNoPlan.setOperator(ComparisonOperator.GT);
				partialNoPlan.setCondValue("0"); 
				partialNoPlan.setSymbolStr(XLConditionalFormats.SYMBOL_STAR+"");
				partialNoPlan.setIdentfier("partialNoPlan");
				condFormats.add(partialNoPlan);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			}
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_COMPLIANCE", paramValues);
	}
	private ResultSet getdownTimeReportResultSetNoPlan(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getFilterParamValues(commonFilter);
		
		return dbActionTemplate.dbFunctionCall("PCS_PC_PRODLOG.PCS_FN_PCSNOPLANSPREAD", paramValues);
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	public String getShiftKeyid(String factId, String shiftCode) throws Exception {
		
		CommonMessage.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try
		{			
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			
			sql1.append(" select * from GEN_TL_SHIFTMST   ");
			sql1.append(" WHERE SFTM_SHIFTORDER ='" + shiftCode + "'     ");
		//	sql1.append(" WHERE SFTM_CODE ='" + shiftCode + "'     ");
			sql1.append(" AND SFTM_FACTORYID ='" + factId + "'    ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");
			
			sql=sql1.toString();
			
			CommonMessage.debugMsg("Str	ing sql="+sql);
			
			String keyId= dbActionTemplate.getSingleValue(sql);	
			
			return keyId;
		}		
		catch(Exception e)
		{	
			CommonMessage.debugMsg("Exception in getShiftKeyid dao impl"+e.getMessage());
			return " ";			
		}			
		
	}
	@Override
	public List<String[]> getNoPlan(String mchId, String date, String shift)throws Exception
	{
		/*List<String> paramValues = new ArrayList<String>();
		paramValues.add(mchId);
		paramValues.add(date);
		paramValues.add(shift);
		return  dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETNOPLANTIMEDURATION",paramValues);*/
		String sql=PcsTlMstSql.getNoPlanTimeDuration(mchId,date,shift).toString();
		CommonMessage.debugMsg("sql:"+sql);
		return dbActionTemplate.getDataList(sql);
	}

	@Override
	public List<String[]> NoPlanSave(List<NoPlanSaveBean> noPlanSaveList,String usrm_ccno) throws Exception
	{
	
		List<String[]> noPlanList=new ArrayList<String[]>();
		NoPlanSaveBean noPlanSaveBean=new NoPlanSaveBean();
		String machineId="";
		String shiftOrder="";
		String shiftDate="";
		if(noPlanSaveList!=null)
		{
			for(int i=0;i<noPlanSaveList.size();i++)
			{
				noPlanSaveBean=noPlanSaveList.get(i);
				machineId+=noPlanSaveBean.getMachineKeyid();
				machineId+=";";
				shiftOrder+=noPlanSaveBean.getShiftOrder();
				shiftOrder+=";";
				shiftDate+=noPlanSaveBean.getShiftDate();
				shiftDate+=";";
			}
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(usrm_ccno);
			paramValues.add(machineId);
			paramValues.add(shiftDate);
			paramValues.add(shiftOrder);
			noPlanList=dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_INSERTPCSBULKNOPLAN",paramValues);
		}
		return noPlanList;
	}
	
}
