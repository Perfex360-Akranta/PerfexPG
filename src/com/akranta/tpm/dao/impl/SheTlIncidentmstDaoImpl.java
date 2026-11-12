package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlIncidentmstDao;
import com.akranta.tpm.dao.sql.AbnTlAbnhistorydtlSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.SheTlActionplanSql;
import com.akranta.tpm.dao.sql.SheTlIncidentEmployeeSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.model.SheTlIncidentEmployee;
import com.akranta.tpm.model.SheTlIncidentmst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class SheTlIncidentmstDaoImpl implements SheTlIncidentmstDao {


	private DBActionTemplate dbActionTemplate; 
	private SheTlIncidentEmployeeSql sheTlIncidentEmployeeSql;
	public SheTlIncidentmstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		sheTlIncidentEmployeeSql = new SheTlIncidentEmployeeSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public SheTlIncidentmst create(SheTlIncidentmst sheTlIncidentmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlIncidentmstSql sheTlIncidentmstSql = new SheTlIncidentmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		SheTlIncidentEmployeeSql sheTlIncidentEmployeeSql = new SheTlIncidentEmployeeSql();
		try{
			String elementId = sheTlIncidentmst.getSincElementid();
		 	String location = null;
		 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,SheTlIncidentmstSql.TBL_SHE_TL_INCIDENTMST);
			
			sheTlIncidentmst.setSincKeyid(dbActionTemplate.getSequenceNumber(seqIdentf,11,"IN", "YY", "Y")); // set the sequnce number 
			//sheTlIncidentmst.setSincKeyid(dbActionTemplate.getSequenceNumber(SheTlIncidentmstSql.TBL_SHE_TL_INCIDENTMST,11,"INC", "MMYY", "Y")); // set the sequnce number 
			sqls.add(SheTlIncidentmstSql.getInsertSql(sheTlIncidentmstSql.getSincDbFields(), sheTlIncidentmst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); 
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlIncidentmst;
	}
	
	public SheTlIncidentmst update(SheTlIncidentmst sheTlIncidentmst) throws BusinessApplicationExceptions ,Exception { 
		
		List<String> sqls = new ArrayList<String>(); // by karthikeyanb
		SheTlIncidentmstSql sheTlIncidentmstSql = new SheTlIncidentmstSql();
		try {
			/*List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(sheTlIncidentmst.getSincKeyid()));
			if(planStatus.size()>0)
			{
				for(int i=0;i<planStatus.size();i++)
				{
					if(planStatus.get(i)[0].equals("P"))
					{  
						throw new BusinessApplicationExceptions("Actionplan");
					}
				}
				
			}*/
			//if(UIUtils.isValidKeyId(sheTlIncidentmst.gets))

			sqls.add(SheTlIncidentmstSql.getUpdateSql(sheTlIncidentmstSql.getSincDbFields(), sheTlIncidentmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlIncidentmst;
	}
	
	public SheTlIncidentmst delete(SheTlIncidentmst sheTlIncidentmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		SheTlIncidentmstSql sheTlIncidentmstSql = new SheTlIncidentmstSql();
		//try {
			SheTlIncidentEmployee	sheTlIncidentEmployee = new SheTlIncidentEmployee();
			sheTlIncidentEmployee.setSiemSincKeyid(sheTlIncidentmst.getSincKeyid());
			String checkValSql = SheTlIncidentmstSql.checkStatus(sheTlIncidentmst.getSincKeyid());
			String chackVal = dbActionTemplate.getSingleValue(checkValSql);
			if(!UIUtils.isValidKeyId(chackVal))
			{
				//int val = Integer.parseInt(chackVal);
				//if(val>1)
				{
					sqls.add(SheTlIncidentEmployeeSql.deleteBasedSincId(sheTlIncidentEmployeeSql.getSiemDbFields(), sheTlIncidentEmployee.getSaveArray()));
					sqls.add(SheTlIncidentmstSql.getDeleteSql(sheTlIncidentmstSql.getSincDbFields(), sheTlIncidentmst.getSaveArray()));
					dbActionTemplate.executeStatements(sqls);
				}
				
			}
			else
				throw new Exception("STATUS_COMPLETED");
						
		/*}catch( Exception e){
			throw new Exception(e.getMessage());
		}*/
		return sheTlIncidentmst;
	}
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();		
		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(CommonFunctions.isValidKeyId(commonFilter.getSafetyMode()))
		{
			if(commonFilter.getSafetyMode().equals("Create"))
				condParms += "SFTSTATUS=P";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("HSE_PC_SAFETY.HSE_FN_INCIDENTRPTMODIFY", paramValues);
		System.out.println("After " +commonFilter.getViewClick());
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	public SheTlIncidentmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		SheTlIncidentmst sheTlIncidentmst = new SheTlIncidentmst();
		String sql = SheTlIncidentmstSql.selectIncident();		
		Object args [] = new Object [] { keyid };
		sheTlIncidentmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		String getyy = "SELECT LISTAGG(WWMS_KEYID,',') within group(order by WWMS_KEYID) WWMS_KEYID FROM  BDM_TL_WHYWHYMST WHERE WWMS_REFDOCNO = '"+keyid+"'";
		String yyno = dbActionTemplate.getSingleValue(getyy);
		sheTlIncidentmst.setSincYyno(yyno);
		return sheTlIncidentmst;
	}

	@Override
	public SheTlIncidentEmployee recallEmployee(String keyId) throws Exception {
		
		SheTlIncidentEmployee sheTlIncidentEmployee = new SheTlIncidentEmployee();
		
		System.out.println("Before fetch the data");
		String sql = SheTlIncidentEmployeeSql.getIncidentEmployee();
		
		System.out.println(sql);
		Object args [] = new Object [] {keyId};
		sheTlIncidentEmployee.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		System.out.println("Dao Impl"+sheTlIncidentEmployee);
			
		return sheTlIncidentEmployee;
		
	}
	public SheTlActionplan recallAction(String keyId) throws Exception {
		
		SheTlActionplan sheTlActionplan = new SheTlActionplan();
		
		
		String sql = SheTlActionplanSql.getActionplan();
		
		System.out.println(sql);
		Object args [] = new Object [] {keyId};
		sheTlActionplan.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
			
		return sheTlActionplan;
		
	}
	
	public Workbook accidentIncidentReportExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getAccidentIncedentReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
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
			return excelUtils.writeToExcel(rs,rptFormat,1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getAccidentIncedentReportResultSet(CommonFilter commonFilter) throws Exception
	{
		//List<String> paramValues = getFilterParamValues(commonFilter);
		

		//return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_INCIDENTRPTMODIFY", paramValues);
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_INCIDENTRPTMODIFY", paramValues);
		
	}
	
	private List<String> getFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonFunctions.debugMsg("commonFilter.getSafetyMode()"+commonFilter.getSafetyMode());
		if(CommonFunctions.isValidKeyId(commonFilter.getSafetyMode()))
		{
			if(commonFilter.getSafetyMode().equals("Create"))
				condParms += "SFTSTATUS=P";
		}
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}
	
	public List<String[]> getAccidentInvest(CommonFilter commonFilter) throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();
			CommonFunctions.debugMsg(commonFilter.getToRow());
				
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =null;
	
			dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_ACCIDENTINVEST", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt..."+totalCnt+"--"+paramValues.get(0));
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
		
	}

	@Override
	public Map<Integer, List<String[]>> getAccInvestExportExcel(String format,CommonFilter commonFilter,String insId) throws Exception {
		try
		{
			CommonFunctions.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
			paramValues.add(insId);
			
			CommonFunctions.debugMsg("param Values :-" +paramValues.get(0));
			Map<Integer, List<String[]>> dataList = dbActionTemplate.processDbFunCallMultCursor("HSE_PC_SAFETY.HSE_FN_ACCIDENTEXPORTEXCEL", paramValues,2);
			
			CommonFunctions.debugMsg("Inside v: " );
			CommonFunctions.debugMsg("Inside ParamVal: " );
						
			return dataList;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public Workbook getAccidentInvestExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		 ResultSet rs = null;
		   try{
			
			rs =   getAccidentInvestigationExportExcelResultSet(commonFilter);
			CommonFunctions.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();			
			
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getAccidentInvestigationExportExcelResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		paramValues.add(condParms);
		paramValues.add(commonParams);			
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_ACCIDENTINVEST", paramValues);
		
		return rs;
	}

	@Override
	public List<GenTlAllmoduleimgfile> saveOplImg(SheTlIncidentmst sheTlIncidentmst) throws Exception,BusinessApplicationExceptions {
		
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = sheTlIncidentmst.getAllmoduleimgfile();
			System.out.println("oplTlMst.getAllmoduleimgfile()"+sheTlIncidentmst.getAllmoduleimgfile());
			
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				System.out.println("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
				System.out.println("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
			
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
			
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
						genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
						genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
			
				valueList.add(delValue);
				valueList.add(insValues);
		
				dataTypes.add(delTypes);
				dataTypes.add(insDataType);
				}
			
			System.out.println("sqls ="+sqls);
			System.out.println("valueList ="+valueList);
			System.out.println("dataTypes ="+dataTypes);
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		
		
		
		return null;
		
	}

	@Override
	public List<GenTlAllmoduleimgfile> getImage(List<GenTlAllmoduleimgfile> insImgList) throws Exception {
		
		System.out.println("Inside Dao impl Imgh");
		//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:insImgList)
		{
			try{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
			CommonFunctions.debugMsg("condSql...."+condSql);
			CommonFunctions.debugMsg("condSql1...."+condSql1);
			String Sql = "SELECT IMFL_FILENAME FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND  IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			CommonFunctions.debugMsg("SQL : "+Sql);
			String fileName = dbActionTemplate.getSingleValue(Sql);
			CommonFunctions.debugMsg("fileName...");
			//String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
			CommonFunctions.debugMsg("fileName..."+fileName);
			
			if( fileName != null )
			{
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
			
				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
				genTlAllmoduleimgfile.setImflFilename(imgFileName);
			
				System.out.println(" fileName " + fileNamePath);
				
				
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
			}catch(Exception e)
			{
				System.out.println("Inside exc");
				e.printStackTrace();
			}
		}
		
		return genTlAllmoduleimgList;
	}

	@Override
	public List<GenTlAllmoduleimgfile> getImageExcel(List<GenTlAllmoduleimgfile> insImgList) throws Exception {
		
		System.out.println("Inside Dao impl Imgh");
		//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:insImgList)
		{
			try{
			if(genTlAllmoduleimgfile != null)
			{
			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
			CommonFunctions.debugMsg("KEY Id : "+genTlAllmoduleimgfile.getImflRefkeyid());
		//	String Sql = "SELECT IMFL_FILENAME FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND  IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
			String fileName = dbActionTemplate.getSingleValue("GEN_TL_ALLMODULEIMGFILE", "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid());
			
			//String fileName = dbActionTemplate.getSingleValue(Sql);
			
			if( fileName != null )
			{
				if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
				String imgFileName = fileName;
				genTlAllmoduleimgfile.setImflFilename(imgFileName);
			
				System.out.println(" fileName " + fileNamePath);
				
				
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
				
				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
			}
			}
			}catch(Exception e)
			{
				System.out.println("Inside exc");
				e.printStackTrace();
			}
		}
		
		return genTlAllmoduleimgList;
	}

	@Override
	public List<String[]> getAccidentInvRpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();		
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		CommonFunctions.debugMsg("test to............");
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("test_pc_testSKILLINDX.SHE_FN_ACCIDENTINVRPT", paramValues);
		if( commonFilter.getViewClick() == 'Y')
		{
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt..."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger )
			{
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}

	@Override
	public Workbook AccInvRptExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{			
			rs =   getAccidentInvReport(commonFilter);
			System.out.println("   rs value::::::::");
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,2,0,0 );	
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}

	private ResultSet getAccidentInvReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();	
		String condParms =FilterCondSql.getETRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
		
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
		return dbActionTemplate.dbFunctionCall("test_pc_testSKILLINDX.SHE_FN_ACCIDENTINVRPT", paramValues);
	}
	
	
	@Override
	public List<String[]> getIncidentCount(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SOP_PC_SOP.GEN_FN_INCIDENTCOUNT", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	} 
	
	@Override
	public List<String[]> incidentCumList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.GEN_FN_INCIDENTREPCOM", paramValues);
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

	@Override
	public List<String[]> hseIncidentStr(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_ACCIDENTPIEGRH", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	} 
	@Override
	public List<String[]> incidentStrgraph(CommonFilter commonFilter,String keyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{ 
			List<String> paramValues = new ArrayList<String>();
		
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_ACCIDENTPIEGRH", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	} 

	@Override
	public List<String[]> getIncidentCat(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			System.out.println("Incident Category Data:::::::");
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_INCIDENTCATERYRPT", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	} 
	
	@Override
	public List<String[]> incidentCategorygraph(CommonFilter commonFilter,String keyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{ 
			List<String> paramValues = new ArrayList<String>();
		
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_INCIDENTCATERYRPT", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	public Workbook IncidentCategyExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
		  
		   ResultSet rs = null;
		   try
		   {
			
			rs =   getIncidentCatyExportExcel(commonFilter);
			CommonFunctions.debugMsg(rs);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
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
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
			}finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	

		private ResultSet getIncidentCatyExportExcel(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String> paramValues = getFilterParamValues(commonFilter);	
		CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
		ResultSet rs = dbActionTemplate.dbFunctionCall("HSE_PC_SAFETY.HSE_FN_INCIDENTCATERYRPT", paramValues);
		CommonFunctions.debugMsg("rs value.....");
		return rs;
	}
		@Override
		public List<String[]> safetyTrendRptList(CommonFilter commonFilter) throws Exception {
			// TODO Auto-generated method stub
			try
			{
				List<String> paramValues = new ArrayList<String>();
							
				String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
				paramValues.add(condParms);
				paramValues.add(commonParams);
				CommonFunctions.debugMsg("Before Function");
				List<String[]> dataList =  dbActionTemplate.processFunctionCalls("SAP_PC_REPORTS.GEN_FN_SAFETYTRTRPT", paramValues); 
				CommonFunctions.debugMsg("Length...."+dataList.size());
				if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				}
				return dataList; 
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
				
			}
		}
		public Workbook SafetyTrendRptExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
			  
			   ResultSet rs = null;
			   try
			   {
				
				rs =   getSafetyTrendExportExcel(commonFilter);
				CommonFunctions.debugMsg(rs);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
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
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
				
				}finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
			private ResultSet getSafetyTrendExportExcel(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String> paramValues = getFilterParamValues(commonFilter);	
			CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
			ResultSet rs = dbActionTemplate.dbFunctionCall("SAP_PC_REPORTS.GEN_FN_SAFETYTRTRPT", paramValues);
			CommonFunctions.debugMsg("rs value.....");
			return rs;
		}
			
			public Workbook IncidentCountExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
				  
				   ResultSet rs = null;
				   try
				   {
					
					rs =   getIncidentCountExportExcel(commonFilter);
					CommonFunctions.debugMsg(rs);
					ExcelUtils excelUtils = new ExcelUtils(colModel);
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
					return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
					
					}finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
			}
		private ResultSet getIncidentCountExportExcel(CommonFilter commonFilter) throws Exception
			{
				List<String> paramValues = new ArrayList<String>();
				String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
				String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
				
				paramValues.add(condParms);
				paramValues.add(commonParams);
				//List<String> paramValues = getFilterParamValues(commonFilter);	
				CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
				ResultSet rs = dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.GEN_FN_NEWNINCIDENTCOUNTRPT", paramValues);
				CommonFunctions.debugMsg("rs value.....");
				return rs;
			}

		public Workbook IncidentCumCountExportExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws Exception{
			  
			   ResultSet rs = null;
			   try
			   {
				
				rs =   getIncidentCumCountExportExcel(commonFilter);
				CommonFunctions.debugMsg(rs);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
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
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
				
				}finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}
	private ResultSet getIncidentCumCountExportExcel(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			//List<String> paramValues = getFilterParamValues(commonFilter);	
			CommonFunctions.debugMsg("Inside the rs.........."+paramValues);
			ResultSet rs = dbActionTemplate.dbFunctionCall("TEST_PC_TEST3.GEN_FN_INCIDENTREPCOM", paramValues);
			CommonFunctions.debugMsg("rs value.....");
			return rs;
		}	
	public List<String[]> getNewIncidentCount(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
						
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonFunctions.debugMsg("Before Function");
			List<String[]> dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.GEN_FN_NEWNINCIDENTCOUNTRPT", paramValues); 
			CommonFunctions.debugMsg("Length...."+dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
}

