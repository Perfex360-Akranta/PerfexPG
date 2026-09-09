package com.akranta.tpm.scheduler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.naming.NamingException;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
//import com.akranta.tpm.utils.NotesMailClient;
import com.akranta.tpm.utils.NotesMailClient;

public class ScheduleReportConfig {
	
	private static final Logger SchedulerLog = Logger.getLogger("SchedulerLog");
	
	private DBActionTemplate dbActionTemplate;
	private ExcelUtils excelUtils = null;  
	//private MocServiceImpl mocService = null; 
	String filePath = null ;
	NotesMailClient notesMailClient =null;
	public ScheduleReportConfig(String filePath ){
		this.filePath = filePath;
		try{
			
			excelUtils=new ExcelUtils();
			SchedulerLog.info("Initializing ScheduleReportConfig.....setting file path "+ filePath);
			
			String location = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_LOCATION");
			String env = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_ENVIRONMENT");
			String dataSourceIdentifier = location+env;
			dataSourceIdentifier = dataSourceIdentifier.toLowerCase();
			SchedulerLog.info("Initializing Datasource....."+ dataSourceIdentifier);
			System.out.println(" Inside the the SCheduler  "+dataSourceIdentifier+"   "+location+"    "+env);    		
			CommonMessage.debugMsg(" dataSourceIdentifier " + dataSourceIdentifier);
			this.dbActionTemplate = new DBActionTemplate(UIUtils.getDataSource(dataSourceIdentifier));
			CommonFunctions.debugMsg("Creatting Mail client.....");
			
			  notesMailClient= new NotesMailClient( );
			  SchedulerLog.info("Creating Excel utils....."); 
			  //excelUtils = new ExcelUtils(notesMailClient);
			 
		  //  mocService=new MocServiceImpl(dbActionTemplate);// )UIUtils.getServiceObject(request, "MocServiceImpl");

		}catch(NamingException e){
			e.printStackTrace();
			SchedulerLog.info(" initializeing scheduler  Error ",  e  );
		}
		
	}
	public List<SchedulerConfigMst> getScheduleInfo(String frequency) throws NoDataFoundException
	{
		StringBuilder sql = new StringBuilder();
		sql.append("Select srcm_reportname,srcm_title,srcm_subject,srcm_type,srcm_dbfunctionname,srcm_frequency, " );
		sql.append(" SRCM_XLRPTSTARTCOLINDEX,SRCM_GROUPBY1COLINDEX,SRCM_GROUPBY2COLINDEX,SRCM_TOMAILCOLINDEX,SRCM_CCMAILCOLINDEX, " );
		sql.append(" SRCM_EMPNAMECOLINDEX,SRCM_NOOFHEADERS,SRCM_XLRPTSTARTROWINDEX from adm_TL_scheduledrpt_configmst where  1 = 1 ");

		if( frequency != null )
			sql.append(  " and srcm_frequency like " + frequency + "%'" );
		
		sql.append(" and srcm_active = 'Y' order by srcm_frequency ");
		try{
			CommonFunctions.debugMsg("Getting scheduler configuration data .....");
			CommonFunctions.debugMsg("Sql : " + sql );
			
			System.out.println(" Getting Sql : " + sql);
			List<String[]> schedulerConfigDataList = dbActionTemplate.getDataList(sql.toString());
			List<SchedulerConfigMst> schedulerConfigMstList = new ArrayList<SchedulerConfigMst>();
			CommonFunctions.debugMsg("Total number of scheduled reports  " + schedulerConfigMstList.size() );
			for( String[] row :schedulerConfigDataList){
				CommonMessage.debugMsg(" row[0] " + row[0]);
				SchedulerConfigMst schedulerConfigMst = new SchedulerConfigMst();
				CommonFunctions.debugMsg(" Report Name :" +  row[0] );
				CommonFunctions.debugMsg(" Generation Frequency :" +  row[5] );
				CommonFunctions.debugMsg(" DbFucntionName :" +  row[4] );
				schedulerConfigMst.setReportName(row[0]);
				schedulerConfigMst.setTitle( row[1]);
				schedulerConfigMst.setSubject(row[2]);
				schedulerConfigMst.setType(row[3]);
				schedulerConfigMst.setDbFucntionName(row[4]);
				schedulerConfigMst.setFrequency(row[5]);
				
				schedulerConfigMst.setXlRptStartColIndx(Integer.parseInt(row[6]));
				schedulerConfigMst.setGroupBy1ColIndx(Integer.parseInt(row[7]));
				schedulerConfigMst.setGroupBy2ColIndx(Integer.parseInt(row[8]));
				schedulerConfigMst.setToMailColIndx(Integer.parseInt(row[9]));
				schedulerConfigMst.setCcMailColIndx(Integer.parseInt(row[10]));
				schedulerConfigMst.setEmpNameColIndx(Integer.parseInt(row[11]));
				
				schedulerConfigMst.setNumberOfHeader(Integer.parseInt(row[12]));
				schedulerConfigMst.setXlRptStartRowIndx(Integer.parseInt(row[13]));
				
				schedulerConfigMstList.add(schedulerConfigMst);
				
			}
			return schedulerConfigMstList;	
		}catch(Exception e){
			e.printStackTrace();
			SchedulerLog.info(" Error " ,e);
			throw new NoDataFoundException(e.getMessage());
		}
		
	}
	
	public void genScheduledReport(SchedulerConfigMst schedulerConfigMst){
		 
		if( "G".equals(schedulerConfigMst.getType()))
			generateStaticMailReport(schedulerConfigMst);
		else if( "S".equals(schedulerConfigMst.getType()))
			generateGroupDynamicMailReport(schedulerConfigMst);
	}
	private void generateGroupDynamicMailReport(SchedulerConfigMst schedulerConfigMst){
		int curCount = 1;
		ResultSet rs =null;
		//FileOutputStream out = null;

		try {
			
			CommonMessage.debugMsg(" Fetching Data From " + schedulerConfigMst.getDbFucntionName());
			SchedulerLog.info(" Fetching Data From " + schedulerConfigMst.getDbFucntionName());
			/* Execute the PLSQL function and return system refcursor as the out parameter, and return list of result set as many(curcount), out refcursors inthe list*/
			List<ResultSet> resultSetList = dbActionTemplate.retResultSetFunCallMultiCur(schedulerConfigMst.getDbFucntionName(), null, curCount);
			
			CommonMessage.debugMsg(" Result set list Size"+resultSetList.toString());
			rs = resultSetList.get(0); 
			CommonFunctions.debugMsg(" Writing to excel ");
			CommonMessage.debugMsg(" Writing to excel ");
			/* This function generates number of excel sheet and send as email as per the configurations*/
			if(schedulerConfigMst.getDbFucntionName().equals("ADM_FN_SENDDMOCMAIL")){
			//for (int i=0;resultSetList.size()>i;i++){
			//	CommonMessage.debugMsg("IIIIIIIIII"+ rs.last() );
				while(rs.next()){
				String mocKeyid=rs.getString(1);
				String DMT=rs.getString(2);
				String JH=rs.getString(3);
				String suggestionDate=rs.getString(4);
				String suggestion=rs.getString(5);
				String suggestionId=rs.getString(6);
				String flid=rs.getString(7);
				String moctype=rs.getString(8);
				String mocdetails=rs.getString(9);
				String initiator=rs.getString(10);
				String mocnature=rs.getString(11);
				String mocDescription=rs.getString(12);
				String mocTitle=rs.getString(13);
				String MOCDate=rs.getString(14);
				String mocWifFacility=rs.getString(15);
				String mocWifDate=rs.getString(16);
				String mocWifTeam=rs.getString(17);
				String mocHazopDate=rs.getString(18);
				String mocHazopFacility=rs.getString(19);
				String mocHazopTeam=rs.getString(20);
				String pidNo=rs.getString(21);
				String mocHazopNode=rs.getString(22);
				String mocHazopDesign=rs.getString(23);
				String PssrFacility = null;
				String format =".xlsx";
				
				String path ="D:/Perfex360/App/webapps/perfex/WEB-INF/exceltemplates";

			// String path ="D:/apache-tomcat-7.0.32/wtpwebapps/perfexitc_dev/WEB-INF/exceltemplates";
				String currentDate= null;
				//mocService.MocExcelSheet(format,  path,  mocKeyid, flid,  DMT,  JH, currentDate,  mocnature, moctype, mocdetails,  mocDescription, mocTitle, initiator,MOCDate, mocWifFacility, mocWifTeam, mocWifDate,  suggestion, suggestionId,mocHazopFacility,mocHazopTeam,  mocHazopNode,  mocHazopDesign, mocHazopDate, pidNo,PssrFacility, mocdetails);
			//	CommonMessage.debugMsg("IIIIIIIIII2222222"+i);
				//NewDashboardExcelView(String format, String path,  mocKeyid,  flid,  DMT,  JH, currentDate,  mocnature, moctype, mocdetails,  pCHange, String title, String initiator,String MOCDate,String WhatifFacility,String WhatifTeam,String WhatifDate,String suggestion,String SuggestionId,String hazopFacility, String hazopTeam, String hazopNode, String hazopDesign, String hazopDate,String pidNo,String PssrFacility,String MOCDetails)
			}
			//}
			}
			else{
				CommonFunctions.debugMsg("Insdie the Else Part");
				excelUtils.genExlMailSend(rs, schedulerConfigMst, filePath);
			}
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			SchedulerLog.info(" Exception ", e);
			if(rs != null)
				try {
					DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
//					DBActionTemplate.closeConnection(rs, null, null, null, null);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			rs =null;
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs != null)
				try {
					DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
//					DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			
			
			
			// TODO Auto-generated catch block
			
		}
		
	}
	
	private void generateStaticMailReport(SchedulerConfigMst schedulerConfigMst){
		int curCount = 2;
		ResultSet rs =null;
		FileOutputStream out = null;
		Workbook wb = null;
		try {
			List<ResultSet> resultSetList = dbActionTemplate.retResultSetFunCallMultiCur(schedulerConfigMst.getDbFucntionName(), null, curCount);
			
			wb = excelUtils.writeToExcelNoColModel(resultSetList.get(0),"xlsx",1);
			String fileName =this.filePath + schedulerConfigMst.getReportName()+ "_" + now() +".xlsx";
			out = new FileOutputStream( fileName );
		    wb.write(out); 
		    wb = null;
		    CommonMessage.debugMsg(" file generated " + fileName);
		    rs = resultSetList.get(1);
		    StringBuilder ccTo = new StringBuilder();
		    String sendTo = null;
		    CommonMessage.debugMsg(" rs  "  + rs); 
		    while(rs.next()){
		    	sendTo = rs.getString(1);
		    	ccTo.append(rs.getString(2)+";");
		    }
		    List<String> attachments = new ArrayList<String>();
			attachments.add(fileName);
		    //notesMailClient.send(sendTo, ccTo.toString(), schedulerConfigMst.getTitle(), schedulerConfigMst.getSubject(), fileName);
		 //   notesMailClient.send(sendTo, ccTo.toString(), schedulerConfigMst.getTitle(), schedulerConfigMst.getSubject(), attachments);
		    
		    rs.getStatement().getConnection().close();
		    rs.getStatement().close();
		    rs = null;
		    out.close();
		    out = null;
		} catch (Exception e) {
			rs =null;
			wb = null;
			if( out != null)
				try {
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String now() {
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm",Locale.ENGLISH);
	    return sdf.format(cal.getTime());

	  }
	
	
}
