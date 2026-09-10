package com.akranta.tpm.dao.impl;

import com.akranta.tpm.dao.BAL_CLTI_ScheduleDao;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Types;

	import net.sf.json.JSONObject;

	import org.apache.poi.ss.usermodel.Workbook;

	import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.JhClitCalendarDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_CliTlStandardsSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.JhClitCalendarSqls;
//import com.akranta.tpm.dao.sql.BAL_SapTlSparesreplacedSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
//import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;
import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;

	public class BAL_CLTI_ScheduleDaoImpl implements BAL_CLTI_ScheduleDao  {
		

		private static final String TBL_CLI_TL_CALENDAR = "CLI_TL_CALENDAR ";
		private DBActionTemplate dbActionTemplate;

		public BAL_CLTI_ScheduleDaoImpl(DBActionTemplate dbActionTemplate) 
		{
			this.dbActionTemplate = dbActionTemplate;
		}
		
		public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
			this.dbActionTemplate = dbActionTemplate;
		}
		
		public List<String []>getjhnfnGetScheduledArray(CommonFilter commonFilter,String shiftId) throws Exception
		{
			try
			{
				System.out.println("inside DAOImpl " +shiftId);
				List<String> paramValues = new ArrayList<String>();

				//paramValues.add(commonFilter.getFromDate());
				paramValues.add("01-"+commonFilter.getFromMonth());
			/*	paramValues.add(commonFilter.getFactory()!=null ?( commonFilter.getFactory().getId() != null ? commonFilter.getFactory().getId():"{}") :"{}");
				paramValues.add(commonFilter.getSection()!= null ?( commonFilter.getSection().getId() !=null ? commonFilter.getSection().getId():"{}"):"{}");
				paramValues.add(commonFilter.getCell() != null ? (commonFilter.getCell().getId() != null?commonFilter.getCell().getId():"{}"):"{}");
				paramValues.add(commonFilter.getMachine()!= null ? (commonFilter.getMachine().getId()!=null?commonFilter.getMachine().getId():"{}"):"{}");
				paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
				paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
			*/	
				paramValues.add(commonFilter.getFactoryId()!=null ?( commonFilter.getFactoryId() != null ? commonFilter.getFactoryId():"{}") :"{}");
				paramValues.add(commonFilter.getSectionId()!= null ?( commonFilter.getSectionId() !=null ? commonFilter.getSectionId():"{}"):"{}");
				paramValues.add(commonFilter.getCellId() != null ? (commonFilter.getCellId() != null?commonFilter.getCellId():"{}"):"{}");
				paramValues.add(commonFilter.getMachineId()!= null ? (commonFilter.getMachineId()!=null?commonFilter.getMachineId():"{}"):"{}");
				paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
				paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
				paramValues.add(shiftId);
				
				CommonFunctions.debugMsg(commonFilter.getCellId()+"....... FLID........"+commonFilter.getMachineId());
				List<String[]> jhShftCalReport =null;
				if(!FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
					jhShftCalReport = dbActionTemplate.processFunctionCalls ("JHN_PC_CLISTANDARD.JHN_FN_GETSCHEDULECOMPLETION", paramValues);
					
				}
				else if(FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
					jhShftCalReport = dbActionTemplate.processFunctionCalls("JHN_PC_CLISTANDARD.JHN_FN_GETSCHARRSHIFTWISE", paramValues);
					
				}
				
				return jhShftCalReport;
			}
			catch (Exception e)
			{e.printStackTrace();System.out.println(" e.getMessage()e.getMessage() "+e.getMessage());
				throw new Exception(e.getMessage()); 
			}
			
		} 

		//for shift wise report
		
		public List<String []> getJHNFNGETSCHARRSHIFTWISE(CommonFilter commonFilter) throws Exception
		{
			try
			{
				List<String> paramValues = new ArrayList<String>();
	       
				paramValues.add(commonFilter.getFromDate());
				paramValues.add(commonFilter.getToDate());
				paramValues.add(commonFilter.getAssembly()!= null ? commonFilter.getAssembly().getId():"{}");
				paramValues.add(commonFilter.getMachine()!= null ? commonFilter.getMachine().getId():"{}");
				paramValues.add(commonFilter.getSection()!= null ? commonFilter.getSection().getId():"{}");
				paramValues.add(commonFilter.getCell() != null ? commonFilter.getCell().getId():"{}");
				paramValues.add(commonFilter.getFactory()!= null ? commonFilter.getFactory().getId():"{}");
				paramValues.add(commonFilter.getEqpGroup()!= null ? commonFilter.getEqpGroup().getId():"{}");
				paramValues.add(commonFilter.getCostCenter()!= null ? commonFilter.getCostCenter().getId():"{}");
				paramValues.add(commonFilter.getJhStep()!= null ? commonFilter.getJhStep().getId():"{}");
				paramValues.add(commonFilter.getMachineRank()!= null ? commonFilter.getMachineRank().getId():"{}");
				paramValues.add(commonFilter.getCircle()!= null ? commonFilter.getCircle().getId():"{}");			
				paramValues.add(commonFilter.getCategory()!= null ? commonFilter.getCategory().getId():"{}");
				paramValues.add(commonFilter.getImpact()!= null ? commonFilter.getImpact():"{}");
				paramValues.add(commonFilter.getType()!= null ? commonFilter.getType():"{}");
				paramValues.add(commonFilter.getProduction()!= null ? commonFilter.getProduction().getId():"{}");
				paramValues.add(commonFilter.getStatus()!= null ? commonFilter.getStatus():"{}");

				List<String[]> abnormalityReport = dbActionTemplate.processFunctionCalls(JhClitCalendarSqls.getjhclitshiftarrSql(), paramValues);

				return abnormalityReport;

			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}

		@Override
	/*	public JhclitCalendarModel update(List<JhclitCalendarModel>  jhclitCalendarModelList ) {
			// TODO Auto-generated method stub
			System.out.println("inside update DaoImpl");
			List<String> sqls = new ArrayList<String>();
				sqls=JhClitCalendarSqls.updateCal(jhclitCalendarModelList);
			
			try {
				dbActionTemplate.executeStatements(sqls);
				
			    } catch (BusinessApplicationExcepions e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}
	*/
		public BAL_JhclitCalendarModel update(List<BAL_JhclitCalendarModel>  jhclitCalendarModelList ) {
			// TODO Auto-generated method stub
			System.out.println("inside update DaoImpl");
			
			String	sql =JhClitCalendarSqls.getUpdateActualCLITCalendarSql();
			int [] dataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			List<Object[]> valueList = new ArrayList<Object[]>();
			try {
				for( BAL_JhclitCalendarModel jhclitCalendarModel :jhclitCalendarModelList ){
					System.out.println("duration  :"+jhclitCalendarModel.getClcaActualduration());
					Object[] values = {jhclitCalendarModel.getClcaActualduration(),jhclitCalendarModel.getClcaStatus(),jhclitCalendarModel.getClcaCreatedby(),jhclitCalendarModel.getClcaModifiedon(),jhclitCalendarModel.getClcaPlandate(),jhclitCalendarModel.getClcaClirefid() };
					valueList.add(values);
				}
				dbActionTemplate.executeBatch(sql, valueList, dataTypes);
				
			    } catch (BusinessApplicationExceptions e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}

		@Override
		public BAL_JhclitCalendarModel update_del(List<BAL_JhclitCalendarModel> newJhclitCalendarModel, String flag) {
			// TODO Auto-generated method stub
	System.out.println("inside delete DaoImpl"+ flag);

			String	sql =JhClitCalendarSqls.getUpdateActualCLITCalendarSql();
			int [] dataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
			List<Object[]> valueList = new ArrayList<Object[]>();
			try {
				for( BAL_JhclitCalendarModel jhclitCalendarModel :newJhclitCalendarModel ){
					System.out.println("duration  :"+jhclitCalendarModel.getClcaModifiedon());
					Object[] values = {jhclitCalendarModel.getClcaActualduration(),jhclitCalendarModel.getClcaStatus(),jhclitCalendarModel.getClcaCreatedby(),jhclitCalendarModel.getClcaModifiedon(),jhclitCalendarModel.getClcaPlandate(),jhclitCalendarModel.getClcaClirefid() };
					
					System.out.println("values "+values);
					valueList.add(values);
				}
				dbActionTemplate.executeBatch(sql, valueList, dataTypes);
				
			    } catch (BusinessApplicationExceptions e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return null;
		}

		@Override
		public Workbook JHCLITExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
			ResultSet rs = null;
			   try{
				
				rs =   getJHCLITReportResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(colModel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
				
				XLConditionalFormats condFormatNotExist = new XLConditionalFormats();
				condFormatNotExist.setFontColor(new RGB(254,0,0)); //red font
				condFormatNotExist.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormatNotExist.setFontHeightPoint((short)14);
				condFormatNotExist.setFontBoldWeight((short)20);
				condFormatNotExist.setFromCol(7);
				condFormatNotExist.setToCol(-1);
				condFormatNotExist.setOperator(ComparisonOperator.EQUAL);
				condFormatNotExist.setCondValue("0"); 
				condFormatNotExist.setSymbolStr("");
				condFormatNotExist.setBgColor(new RGB(192,192,192));
				condFormatNotExist.setIdentfier("NoPlan");
				
				condFormats.add(condFormatNotExist);
				
				XLConditionalFormats condFormatPlan = new XLConditionalFormats();
				condFormatPlan.setFontColor(new RGB(254,0,0)); //red font
				condFormatPlan.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormatPlan.setFontHeightPoint((short)14);
				condFormatPlan.setFontBoldWeight((short)20);
				condFormatPlan.setFromCol(7);
				condFormatPlan.setToCol(-1);
				condFormatPlan.setOperator(ComparisonOperator.EQUAL);
				condFormatPlan.setCondValue("-1"); 
				condFormatPlan.setSymbolStr("");	
				
				condFormatPlan.setIdentfier("Plan");
				condFormats.add(condFormatPlan);
				
				XLConditionalFormats condFormatActual = new XLConditionalFormats();
				/*condFormatActual.setFontColor(new RGB(254,0,0)); //red font
				condFormatActual.setFontName(XLConditionalFormats.FONT_WINGDINGS);
				condFormatActual.setFontHeightPoint((short)14);
				condFormatActual.setFontBoldWeight((short)20);
				condFormatActual.setFromCol(10);
				condFormatActual.setToCol(-1);
				condFormatActual.setOperator(ComparisonOperator.GT);
				condFormatActual.setCondValue("0"); //Tick
				condFormatActual.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				
				condFormatActual.setBgColor(new RGB(192,255,255));
				condFormatActual.setIdentfier("Actual");
				condFormats.add(condFormatActual);*/
				
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
		}

		private ResultSet getJHCLITReportResultSet(CommonFilter commonFilter) throws Exception
		{
			//List<String> paramValues = getFilterParamValues(commonFilter);
			List<String> paramValues = new ArrayList<String>();
			paramValues.add("01-"+commonFilter.getFromMonth());
			paramValues.add(commonFilter.getFactory()!=null ?( commonFilter.getFactory().getId() != null ? commonFilter.getFactory().getId():"{}") :"{}");
			paramValues.add(commonFilter.getSection()!= null ?( commonFilter.getSection().getId() !=null ? commonFilter.getSection().getId():"{}"):"{}");
			paramValues.add(commonFilter.getCell() != null ? (commonFilter.getCell().getId() != null?commonFilter.getCell().getId():"{}"):"{}");
			paramValues.add(commonFilter.getMachine()!= null ? (commonFilter.getMachine().getId()!=null?commonFilter.getMachine().getId():"{}"):"{}");
			paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
			paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
			CommonFunctions.debugMsg("Frequency........"+commonFilter.getJhfreq());
			if(! commonFilter.getJhfreq().equals("S")){
				return dbActionTemplate.dbFunctionCall("JHN_PC_CLISTANDARD.JHN_FN_GETSCHEDULEARRAY", paramValues);			
			}
			else{
				return dbActionTemplate.dbFunctionCall("JHN_PC_CLISTANDARD.JHN_FN_GETSCHARRSHIFTWISE", paramValues);			
			}
			
		}
		
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();
			//	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);

			String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}

		@Override
		public List<String[]> getConfiglink() throws Exception {
			// TODO Auto-generated method stub
			String sql = JhClitCalendarSqls.getConfiglinkBtns();
			
			return dbActionTemplate.getDataList(sql);
		}

		@Override
		public List<GenTlToolsimg> getCLTIImage(List<GenTlToolsimg> jhnImgList)	throws Exception {
			
			List<GenTlToolsimg> GenTlToolsimgList = new ArrayList<GenTlToolsimg>();
			System.out.println(" fileName123... "+jhnImgList.size() );
			for(GenTlToolsimg genTlToolsimg:jhnImgList)
			{
				System.out.println(" fileName " );
				//String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
				String condSql = " AND TOIM_KEYID = '" +genTlToolsimg.getToimKeyid() + "'";
				
				String fileName = dbActionTemplate.getSingleValue(" SELECT TOIM_FILENAME FROM "+TableNames.TBL_GEN_TL_TOOLSIMG +" WHERE TOIM_KEYID='"+genTlToolsimg.getToimKeyid()+"'");
				//String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_TOOLSIMG, "IMFL_FILENAME", "IMFL_REFKEYID", TOIM_KEYID,condSql);
				System.out.println(" fileName.... "+fileName );
				
				if( UIUtils.isValidKeyId(fileName))
				{
					
					if( fileName.lastIndexOf("/") > -1 )
					fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
					
					System.out.println(" fileName " + fileName);
					
					String fileNamePath = genTlToolsimg.getToimBlobimage()+  fileName; 
					System.out.println(" fileNamePath " + fileNamePath);
					String imgFileName = genTlToolsimg.getToimFilename()+fileName;
					genTlToolsimg.setToimFilename(imgFileName);
					
					System.out.println(" fileName " + fileNamePath);
					
					dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_TOOLSIMG, "TOIM_BLOBIMAGE", condSql, fileNamePath);
					GenTlToolsimgList.add(genTlToolsimg);
				}
				
			}
			return GenTlToolsimgList;
		}

		@Override
		public String save(List<String> actlst, String cellId,
				String mchineId, String shiftId,String createdBy,String status,List<String> observations,List<String> tagClass ) throws Exception {
			String date=CommonFunctions.getDate();
			CommonFunctions.debugMsg(date +"dateTimedateTimedateTime");
			//HARI CHANGED THIS getDateTime() TO getDate() 
			String dateTime = CommonFunctions.getDate();//dd-MMM-yyyy
			CommonFunctions.debugMsg(dateTime +"dateTimedateTimedateTime");
			String hour="";
			String dateTimess="";
			String second=dateTime.substring(13);
			String dateTimes=dateTime.substring(11,13 ) ;
			
			
			//dateTimes="00";
			if(dateTimes.equals("13")){
				hour="01";
			}
			else if(dateTimes.equals("14")){
				hour="02";
			}
			else if(dateTimes.equals("15")){
				hour="03";
			}
			else if(dateTimes.equals("16")){
				hour="04";
			}
			else if(dateTimes.equals("17")){
				hour="05";
			}
			else if(dateTimes.equals("18")){
				hour="06";
			}
			else if(dateTimes.equals("19")){
				hour="07";
			}
			else if(dateTimes.equals("20")){
				hour="08";
			}
			else if(dateTimes.equals("21")){
				hour="09";
			}
			else if(dateTimes.equals("22")){
				hour="10";
			}
			else if(dateTimes.equals("23")){
				hour="11";
			}
			else if(dateTimes.equals("00")){
				hour="12";
			}
			if(hour!=null && hour!=""){
				dateTimess=date.concat(" ").concat(hour).concat(second);
			}
			else{
				dateTimess=dateTime;
			}
			
			CommonFunctions.debugMsg(dateTimes+" "+dateTime +"  dateTimedateTimedateTime  "+second +" hour "+hour );

			List<String> sqls = new ArrayList<String>();
			String observation=null;
			String keyId=null;
			String abnTagClass=null;
			for( int i=0;i<actlst.size();i++)
			{ 
				keyId=actlst.get(i);
				//for(int j=0;j==i;j++ ){
					 observation=observations.get(i);
				//	 for(int k=0;k==i;j++ ){
					 abnTagClass=tagClass.get(i);
			
				CommonFunctions.debugMsg(keyId + " inside the ");
			   StringBuffer sql = new StringBuffer();
				sql.append(" Update CLI_TL_CALENDAR ");
				sql.append(" SET CLCA_STATUS = 'Y' ,");
				sql.append("  CLCA_ACTUALDATE =TO_DATE('"+dateTimess+"','DD-MM-YYYY HH12:MI'),");
				sql.append("  CLCA_COMPLETEDBY ='"+createdBy+"',");
				sql.append("  CLCA_ACTUALSHIFTID = '"+shiftId+"', ");
				sql.append("  CLCA_REMARKS = '"+status+"', ");
				sql.append("  CLCA_OBSERVATION = '"+observation+"', ");
				sql.append("  CLCA_ABNORMALITYTAG = '"+abnTagClass+"' ");
				sql.append(" where CLCA_CELLID = '"+cellId +"' "); 
				sql.append(" AND CLCA_MACHINEID = '"+ mchineId +"' "); 
				sql.append(" AND CLCA_PLANDATE =  '"+date+"' ");
				sql.append(" AND CLCA_PLANSHIFTID = '"+shiftId+"' "); 
				sql.append(" AND CLCA_CLIREFID = '"+keyId+"' "); 
				System.out.println(sql +" sqlopdsda");
			
				sqls.add(sql.toString());   
		//	}
		//	}
			}
			dbActionTemplate.executeStatements(sqls);
			return "sucess";
	 
		
	 }

		@Override
		public String getShift(ShiftBean shiftBean) throws Exception {
			
				try
				{			
					String sql = BAL_BdmTlMstSql.getShiftFunction();
					System.out.println(sql);
					List<String > paramValues = new ArrayList<String>();
					paramValues.add(shiftBean.getFactId());
					paramValues.add(shiftBean.getSectId());
					paramValues.add(shiftBean.getCellId());
					paramValues.add(shiftBean.getFromTime());
					System.out.println(shiftBean.getFactId());
					List<String []> fillShift = dbActionTemplate.processFunctionCalls( "BDM_PC_BREAKDOWN.BDM_FN_GetshiftForTime",paramValues);
					return fillShift.get(0)[0];
				}
				catch(Exception e)
				{
					//CommonFunctions.debugMsg("err : "+e.toString());
				}
				return null;
			}

		@Override
		public List<String[]> jhComplience(CommonFilter commonFilter)
				throws Exception {
			try
			{
				
				List<String > paramValues = getFilterParamValues(commonFilter);
				
				CommonFunctions.debugMsg(commonFilter.getSectionId() +"commonFiltercommonFiltercommonFilter");
				
				
				List<String[]> dataList =  dbActionTemplate.processFunctionCalls("JHN_PC_CLITREPORT.JHN_FN_GETCOMPLIANCEREPORT", paramValues);
				
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
		public Workbook jhComplienceExportExcel(CommonFilter commonFilter,
				JSONObject tableModel, String format) throws Exception {
			// TODO Auto-generated method stub
			ResultSet rs = null;
			try {
				rs = getActivityResultSet(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tableModel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254, 0, 0)); // red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short) 14);
				condFormat.setFontBoldWeight((short) 20);
				condFormat.setFromCol(13);
				condFormat.setToCol(-1);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue((char) 252 + ""); // Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs, format, 2, 0, 0);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				DBActionTemplate.closeConnection(rs, null, null, null, rs
						.getStatement().getConnection());
			}
			return null;
		}

		private ResultSet getActivityResultSet(CommonFilter commonFilter)
				throws Exception {
			List<String> paramValues = getFilterParamValues(commonFilter);
			ResultSet rs = null;
			rs = dbActionTemplate.dbFunctionCall("JHN_PC_CLITREPORT.JHN_FN_GETCOMPLIANCEREPORT", paramValues);
			return rs;
		}

		/*@Override
		public List<String[]> getjhnfnGetScheduledArray(CommonFilter commonFilter)
				throws Exception {
			try
			{
				System.out.println("inside DAOImpl " );
				List<String> paramValues = new ArrayList<String>();

				paramValues.add(commonFilter.getFromDate());
				paramValues.add("01-"+commonFilter.getFromMonth());
			/*	paramValues.add(commonFilter.getFactory()!=null ?( commonFilter.getFactory().getId() != null ? commonFilter.getFactory().getId():"{}") :"{}");
				paramValues.add(commonFilter.getSection()!= null ?( commonFilter.getSection().getId() !=null ? commonFilter.getSection().getId():"{}"):"{}");
				paramValues.add(commonFilter.getCell() != null ? (commonFilter.getCell().getId() != null?commonFilter.getCell().getId():"{}"):"{}");
				paramValues.add(commonFilter.getMachine()!= null ? (commonFilter.getMachine().getId()!=null?commonFilter.getMachine().getId():"{}"):"{}");
				paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
				paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
			*/	
			/*	paramValues.add(commonFilter.getFactoryId()!=null ?( commonFilter.getFactoryId() != null ? commonFilter.getFactoryId():"{}") :"{}");
				paramValues.add(commonFilter.getSectionId()!= null ?( commonFilter.getSectionId() !=null ? commonFilter.getSectionId():"{}"):"{}");
				paramValues.add(commonFilter.getCellId() != null ? (commonFilter.getCellId() != null?commonFilter.getCellId():"{}"):"{}");
				paramValues.add(commonFilter.getMachineId()!= null ? (commonFilter.getMachineId()!=null?commonFilter.getMachineId():"{}"):"{}");
				paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
				paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
				//paramValues.add(shiftId);
				
				CommonFunctions.debugMsg(commonFilter.getCellId()+"....... FLID........"+commonFilter.getMachineId());
				List<String[]> jhShftCalReport =null;
				//if(!FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
					jhShftCalReport = dbActionTemplate.processFunctionCalls("JHN_PC_CLISTANDARD.JHN_FN_GETSCHEDULEARRAY", paramValues);
					
				//}
				//else if(FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
			//		jhShftCalReport = dbActionTemplate.processFunctionCalls("JHN_PC_CLISTANDARD.JHN_FN_GETSCHARRSHIFTWISE", paramValues);
					
			//	}
				
				return jhShftCalReport;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
			
		}
		*/

		@Override
		public List<String[]> getEmployeeMailList(String sectionId)
				throws Exception {
			try
			{
				
				//String sect =commonFilter.getSectionId();//  getSectionId();
				CommonFunctions.debugMsg("Section mst in side the DAOIML" +sectionId);
				String flid=null;
				flid=getFlid(sectionId);
				String sqls=null;
				
					 sqls=" SELECT DISTINCT '','',EMPM_KEYID ,EMPM_NAME, EMPM_EMAIL "+
						  " FROM gen_tl_employeemst, ADM_tl_rolemst,  gen_mv_flidhierarchy, GEN_TL_FNLNROLETEAM,ADM_TL_USERMST,ADM_TL_USER_ROLE_LINK "+
						  " WHERE empm_keyid(+) = FRT_EMPM_KEYID AND FRT_ROLE_KEYID  = ROLE_KEYID(+) AND EMPM_EMAIL<>'{}' "+
						  " AND FLID = FRT_FNLN_KEYID AND USRM_CCNO=FRT_EMPM_KEYID(+) AND USRM_KEYID=ARUL_USERID AND ( INSTR (parentflids || '-' || flid, '"+flid+"' ) >0  )";				
				
				List<String[]> mailList=dbActionTemplate.getDataList(sqls);			
				System.out.println(flid +"Inside daoimpl_09_10"+ mailList.size()+ sqls); 			
				
				return mailList;
			}
			catch (Exception e)
			{
				throw new Exception(e.getMessage()); 
			}
		}
		
		public String getFlid(String sectionId) throws Exception{
			
			String sql=null;
			String flid=null;
			sql="SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID='"+sectionId+"'";
			flid=dbActionTemplate.getSingleValue(sql);   //getData(sql);
			
			CommonFunctions.debugMsg(" flid  flidflid"+flid);
			return flid;
			
		}

		@Override
		public Workbook JhExportExcel(String refId,JSONObject colmodel,	String format) throws Exception {
			
				CommonFunctions.debugMsg(refId+"    ,,,,,,,,,,,,,,.............."+colmodel);
				ResultSet rs = null;
				   try{
					
					rs =   getJhExportExcelResultSet(refId);
					ExcelUtils excelUtils = new ExcelUtils(colmodel);
					
					List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
					
					XLConditionalFormats condFormat = new XLConditionalFormats();
					condFormat.setFontColor(new RGB(254,0,0)); //red font TLM_FN_TOOLGRAPHS
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
					return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
					//return excelUtils.writeToExcel(rs,format, 2,0,0 );
					
				   }catch(Exception e){
					   
					   e.printStackTrace();
				   }
				   /*finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	*/
				return null;
			
			}

			private ResultSet getJhExportExcelResultSet(String  refId) throws Exception {
				// TODO Auto-generated method stub
				List<String> paramValues = new ArrayList<String>();		
				String condParms = null;
				condParms+="REFID="+refId;	 
				 
			    paramValues.add(condParms);		
				
				return dbActionTemplate.dbFunctionCall("JHN_PC_CLITREPORT.JHN_FN_ABNORMALITYREPORTEXCEL", paramValues);
			}

			@Override
			public ResultSet JhExportData(String refId) throws Exception {
				try
				{
					
					CommonFunctions.debugMsg("Section mst in side the DAOIML" +refId);
					
					
					String sqls=null;
					
						 sqls=" SELECT  CELL_NAME ||' - '|| CELL_CODE CELL ,MCHM_MACHINENAME MachineName,MCHM_MACHINENO As Machineno,CLIS_WHATACTIVITY AS ASSEMBLY,CLIS_STANDARD AS ACTIVITY, "+
					 " TRDM_NAME,CLCA_REMARKS AS REMARKS,CLCA_OBSERVATION AS ABNORMALITY,EMPM_NAME AS DETECTEDBY ,CLCA_ABNORMALITYTAG AS ABNORMALITYTAG "+
					 " FROM GEN_VW_FNLN, CLI_TL_CALENDAR,GEN_TL_TRADEMST, GEN_TL_EMPLOYEEMST, CLI_TL_STANDARDS  "+
	                 " WHERE CLCA_TRADEID=TRDM_KEYID(+)  "+
	                 " AND CLIS_FLID = FNLN_KEYID "+
	                 " AND EMPM_KEYID= CLCA_COMPLETEDBY(+) "+
	                 " AND CLCA_KEYID= '"+refId+"' "+
	                 " AND CLIS_KEYID=CLCA_CLIREFID ";
						 CommonFunctions.debugMsg(sqls +"  sqls sqls sqls   ");
						 ResultSet dataList=dbActionTemplate.getData(sqls) ;			
					
					return dataList;
				}
				catch (Exception e)
				{
					throw new Exception(e.getMessage()); 
				}
			}

			@Override
			public List<String[]> getjhnfnGetScheduledArray(
					CommonFilter commonFilter) throws Exception {
				try
				{
					//System.out.println("inside DAOImpl " +shiftId);
					List<String> paramValues = new ArrayList<String>();

					//paramValues.add(commonFilter.getFromDate());
					paramValues.add("01-"+commonFilter.getFromMonth());
				/*	paramValues.add(commonFilter.getFactory()!=null ?( commonFilter.getFactory().getId() != null ? commonFilter.getFactory().getId():"{}") :"{}");
					paramValues.add(commonFilter.getSection()!= null ?( commonFilter.getSection().getId() !=null ? commonFilter.getSection().getId():"{}"):"{}");
					paramValues.add(commonFilter.getCell() != null ? (commonFilter.getCell().getId() != null?commonFilter.getCell().getId():"{}"):"{}");
					paramValues.add(commonFilter.getMachine()!= null ? (commonFilter.getMachine().getId()!=null?commonFilter.getMachine().getId():"{}"):"{}");
					paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
					paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
				*/	
					paramValues.add(commonFilter.getFactoryId()!=null ?( commonFilter.getFactoryId() != null ? commonFilter.getFactoryId():"{}") :"{}");
					paramValues.add(commonFilter.getSectionId()!= null ?( commonFilter.getSectionId() !=null ? commonFilter.getSectionId():"{}"):"{}");
					paramValues.add(commonFilter.getCellId() != null ? (commonFilter.getCellId() != null?commonFilter.getCellId():"{}"):"{}");
					paramValues.add(commonFilter.getMachineId()!= null ? (commonFilter.getMachineId()!=null?commonFilter.getMachineId():"{}"):"{}");
					paramValues.add(commonFilter.getAssembly()!= null ? (commonFilter.getAssembly().getId()!=null?commonFilter.getAssembly().getId():"{}"):"{}");
					paramValues.add(commonFilter.getFlid()!= null ? (commonFilter.getFlid()!=null?commonFilter.getFlid():"{}"):"{}");
					//paramValues.add(shiftId);
					
					CommonFunctions.debugMsg(commonFilter.getCellId()+"....... FLID........"+commonFilter.getMachineId());
					List<String[]> jhShftCalReport =null;
					if(!FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
						jhShftCalReport = dbActionTemplate.processFunctionCalls("JHN_PC_CLISTANDARD.JHN_FN_GETSCHEDULE", paramValues);
						
					}
					else if(FilterCondSql.getComboSelectionId( commonFilter.getFrequency()).contains("S")){
						jhShftCalReport = dbActionTemplate.processFunctionCalls("JHN_PC_CLISTANDARD.JHN_FN_GETSCHARRSHIFTWISE", paramValues);
						
					}
					
					return jhShftCalReport;
				}
				catch (Exception e)
				{
					throw new Exception(e.getMessage()); 
				}
			}
		}





