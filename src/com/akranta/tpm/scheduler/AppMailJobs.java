package com.akranta.tpm.scheduler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import oracle.jdbc.pool.OracleDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DcmTlDocumentmanagerDao;
import com.akranta.tpm.dao.GenTlActionplanmstDao;
import com.akranta.tpm.dao.QtmTlSapCustComplaintsDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.DcmTlDocumentmanagerDaoImpl;
import com.akranta.tpm.dao.impl.GenTlActionplanmstDaoImpl;
import com.akranta.tpm.dao.impl.QtmTlSapCustComplaintsDaoImpl;
import com.akranta.tpm.dao.sql.DcmTlDocumentlayoutSql;
import com.akranta.tpm.dao.sql.DcmTlDocumentmanagerSql;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentlayout;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.NotesMailClient;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.CellReference;

import javax.sql.DataSource;



public class AppMailJobs implements Job {

	private static final Logger SchedulerLog = Logger.getLogger("SchedulerLog");
	String location = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_LOCATION");
	String env= UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_ENVIRONMENT");
	String CRMpath= UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "CRM_SOURCEPATH");
	String adminMailTo = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_ADMIN_EMAIL");
	NotesMailClient notesMailClient = new NotesMailClient();

  private static final String DOCMANAGER_PATH_SAVE = "/docmanager";
	String dataSourceIdentifier =location+env;
	String dataidentifier = dataSourceIdentifier.toLowerCase();
    String apppath=null;
    String databaseuser=null;
    String excelxls="xls";
	String excelxlsx="xlsx";
	String Actionplan="Responsiblity";
	
    DBActionTemplate dbActionTemplate;
	
	
	public DBActionTemplate  getDBActionTemplate()
	{
		return this.dbActionTemplate ;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}


	
	DcmTlDocumentmanagerDao dcmTlDocumentmanagerDao=new DcmTlDocumentmanagerDaoImpl(dbActionTemplate);
	GenTlActionplanmstDao  genTlActionplanmstDao =new GenTlActionplanmstDaoImpl(dbActionTemplate);

	//DcmTlDocumentmanagerDaoImpl dcmTlDocumentmanagerDaoml=new DcmTlDocumentmanagerDaoImpl();

	/* This method will be invoked on running scheduler */
	@Override
	
	public void execute(JobExecutionContext context) throws JobExecutionException {

	    LocalDateTime jobStartTime = LocalDateTime.now();
	    AtomicInteger mailSuccessCount = new AtomicInteger(0);
	    AtomicInteger mailFailedCount = new AtomicInteger(0);
	    String jobStatus = "SUCCESS";

	    JobDataMap dataMap = context.getJobDetail().getJobDataMap();

	    SchedulerConfigMst schedulerConfigMst =
	            (SchedulerConfigMst) dataMap.get(MailScheduler.CONST_SCHEDULERCONFIGMST_IDENT);

	    String jobName = schedulerConfigMst.getReportName();

	    // ---- OPTIONAL: job-started notification (admin only) ----
	    try {
	        sendJobStartedMail(jobName, jobStartTime);
	    } catch (Exception e) {
	        CommonFunctions.debugMsg("Failed to send job-started mail: " + e.getMessage());
	    }

	    try {
	        System.out.println(CRMpath + " CRMpathCRMpathCRMpath");

	        try {
	            DataSource dbUser = getDataSource(dataidentifier);
	            dbActionTemplate = new DBActionTemplate(dbUser);

	            Connection con = null;
	            try {
	                con = dbUser.getConnection();
	                databaseuser = con.getMetaData().getUserName();
	                CommonFunctions.debugMsg(" Db User  " + dbUser);
	            } finally {
	                if (con != null) {
	                    con.close();
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        CommonFunctions.debugMsg(" Running Job 123");

	        ScheduleReportConfig scheduleReportConfig =
	                (ScheduleReportConfig) dataMap.get(MailScheduler.CONST_SCHEDULEREPORTCONFIG_IDENT);

	        CommonFunctions.debugMsg(" Generating Report : " + jobName);

	        if (jobName.equals("CRM ActionPlan")) {

	            apppath = schedulerConfigMst.getTitle();

	            String docManagerFolder = apppath + "/" + databaseuser + DOCMANAGER_PATH_SAVE + "/CRM";

	            int fromindex = docManagerFolder.indexOf("tmp0");
	            int toindex = docManagerFolder.indexOf("CRM");

	            String documentpath = docManagerFolder.substring(fromindex + 4, toindex + 3);

	            File f1 = null;
	            String documentno = null;
	            String dateTime = CommonFunctions.dateTimeNow();

	            File DestinationFolder = new File(docManagerFolder);
	            if (!DestinationFolder.exists()) {
	                DestinationFolder.mkdirs();
	            }

	            File dir = new File(CRMpath);

	            if (!dir.exists()) {
	                System.out.println("Current Path : " + CRMpath);
	                return;
	            }

	            File[] listDir = dir.listFiles();

	            if (listDir == null || listDir.length == 0) {
	                System.out.println("CRM Folder Empty : " + CRMpath);
	                return;
	            }

	            int numberOfSubfolders = 0;
	            String filesname = null;
	            String subfilename = null;
	            String Sysadmin = "EMP00001";

	            for (int i = 0; i < listDir.length; i++) {

	                if (listDir[i] != null && listDir[i].isDirectory()) {

	                    String filename = listDir[i].getName();
	                    documentno = filename;

	                    File subfile = new File(CRMpath, filename);

	                    if (filename == null || filename.trim().isEmpty()) {
	                        delete(subfile);
	                        continue;
	                    }

	                    f1 = subfile;

	                    File[] listsubfiles = subfile.listFiles();

	                    if (listsubfiles == null || listsubfiles.length == 0) {
	                        continue;
	                    }

	                    for (int j = 0; j < listsubfiles.length; j++) {

	                        if (listsubfiles[j] == null) {
	                            continue;
	                        }

	                        filesname = listsubfiles[j].getName();

	                        if (filesname == null || !filesname.contains(".")) {
	                            continue;
	                        }

	                        subfilename = f1 + "\\" + filesname;

	                        String fileNameWithoutExt =
	                                filesname.substring(0, filesname.lastIndexOf("."));

	                        if (fileNameWithoutExt.equals(Actionplan)) {
	                            try {
	                                String count = ActionplanCount(documentno);
	                                int actioncount = Integer.parseInt(count);

	                                if (actioncount <= 0) {
	                                    readExcelFile(subfilename, documentno);
	                                }
	                            } catch (Exception e) {
	                                try {
	                                    ErrorLog(documentno, "Error at Reading Excel File");
	                                } catch (Exception e1) {
	                                    e1.printStackTrace();
	                                }
	                                e.printStackTrace();
	                            }
	                        }

	                        DcmTlDocumentmanager newdcmTlDocumentmanager = new DcmTlDocumentmanager();
	                        newdcmTlDocumentmanager.setDmdmSlno("1");
	                        newdcmTlDocumentmanager.setDmdmFilename(filesname);
	                        newdcmTlDocumentmanager.setDmdmPath("{}");
	                        newdcmTlDocumentmanager.setDmdmBloblength("0");
	                        newdcmTlDocumentmanager.setDmdmBlobfile(null);
	                        newdcmTlDocumentmanager.setDmdmActive("Y");
	                        newdcmTlDocumentmanager.setDmdmOwner(Sysadmin);
	                        newdcmTlDocumentmanager.setDmdmApprovedby(Sysadmin);
	                        newdcmTlDocumentmanager.setDmdmCreatedby(Sysadmin);
	                        newdcmTlDocumentmanager.setDmdmCreatedon(dateTime);
	                        newdcmTlDocumentmanager.setDmdmModifiedon(dateTime);
	                        newdcmTlDocumentmanager.setDmdmDescription("-");
	                        newdcmTlDocumentmanager.setDmdmRefdoctype("CRM");
	                        newdcmTlDocumentmanager.setDmdmRefdocno(filename);
	                        newdcmTlDocumentmanager.setDmdmPath(
	                                documentpath + "/" + filename + "_" + UIUtils.now());

	                        try {
	                            CRMFilecreate(newdcmTlDocumentmanager);
	                            mailSuccessCount.incrementAndGet(); // treat doc registration as a "unit of work" success
	                        } catch (Exception e) {
	                            mailFailedCount.incrementAndGet();
	                            try {
	                                ErrorLog(documentno, "Error at Writing Document Manager");
	                            } catch (Exception e1) {
	                                e1.printStackTrace();
	                            }
	                            e.printStackTrace();
	                        }

	                        try {
	                            File SourceFolder = new File(f1, filesname);
	                            FileUtils.copyFileToDirectory(SourceFolder, DestinationFolder);

	                            File refile = new File(DestinationFolder + "\\" + filesname);
	                            refile.renameTo(
	                                    new File(DestinationFolder + "\\" + documentno + "_" + UIUtils.now()));

	                            delete(new File(subfilename));

	                        } catch (IOException e) {
	                            mailFailedCount.incrementAndGet();
	                            try {
	                                ErrorLog(documentno, "Error at File Copying Time");
	                            } catch (Exception e1) {
	                                e1.printStackTrace();
	                            }
	                            e.printStackTrace();
	                        }
	                    }

	                    delete(f1);
	                    numberOfSubfolders++;
	                }
	            }

	        } else {
	            scheduleReportConfig.genScheduledReport(schedulerConfigMst);
	            mailSuccessCount.incrementAndGet(); // genScheduledReport currently has no internal counter hook (see note below)
	            SchedulerLog.info("running job " + schedulerConfigMst.getDbFucntionName());
	        }

	    } catch (Exception e) {
	        jobStatus = "FAILED";
	        e.printStackTrace();
	    } finally {
	        LocalDateTime jobEndTime = LocalDateTime.now();

	        if (!"FAILED".equals(jobStatus) && mailFailedCount.get() > 0) {
	            jobStatus = "COMPLETED_WITH_ERRORS";
	        }

	        try {
	            sendJobSummaryMail(jobName, jobStartTime, jobEndTime, jobStatus,
	                    mailSuccessCount.get(), mailFailedCount.get());
	        } catch (Exception e) {
	            CommonFunctions.debugMsg("Failed to send job-summary mail: " + e.getMessage());
	        }
	    }
	}
	/*
	 * public void execute(JobExecutionContext context) throws JobExecutionException
	 * {
	 * 
	 * System.out.println(CRMpath + " CRMpathCRMpathCRMpath");
	 * 
	 * try {
	 * 
	 * DataSource dbUser = getDataSource(dataidentifier);
	 * 
	 * dbActionTemplate = new DBActionTemplate(dbUser);
	 * 
	 * Connection con = null;
	 * 
	 * try {
	 * 
	 * con = dbUser.getConnection();
	 * 
	 * databaseuser = con.getMetaData().getUserName();
	 * CommonFunctions.debugMsg(" Db User  "+dbUser);
	 * 
	 * } finally {
	 * 
	 * if (con != null) { con.close(); } }
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * CommonFunctions.debugMsg(" Running Job 123");
	 * 
	 * JobDataMap dataMap = context.getJobDetail().getJobDataMap();
	 * 
	 * ScheduleReportConfig scheduleReportConfig =(ScheduleReportConfig)
	 * dataMap.get(MailScheduler.CONST_SCHEDULEREPORTCONFIG_IDENT);
	 * 
	 * SchedulerConfigMst schedulerConfigMst =(SchedulerConfigMst)dataMap.get(
	 * MailScheduler.CONST_SCHEDULERCONFIGMST_IDENT);
	 * 
	 * CommonFunctions.debugMsg(" Generating Report : " +
	 * schedulerConfigMst.getReportName()); if
	 * (schedulerConfigMst.getReportName().equals("CRM ActionPlan")) {
	 * 
	 * apppath = schedulerConfigMst.getTitle();
	 * 
	 * String docManagerFolder =apppath + "/" + databaseuser + DOCMANAGER_PATH_SAVE
	 * + "/CRM";
	 * 
	 * int fromindex = docManagerFolder.indexOf("tmp0");
	 * 
	 * int toindex = docManagerFolder.indexOf("CRM");
	 * 
	 * String documentpath = docManagerFolder.substring( fromindex + 4, toindex +
	 * 3);
	 * 
	 * File f1 = null;
	 * 
	 * String documentno = null;
	 * 
	 * String dateTime =CommonFunctions.dateTimeNow();
	 * 
	 * File DestinationFolder = new File(docManagerFolder);
	 * 
	 * if (!DestinationFolder.exists()) {
	 * 
	 * DestinationFolder.mkdirs(); }
	 * 
	 * File dir = new File(CRMpath); System.out.println("Absolute Path : " +
	 * dir.getAbsolutePath()); System.out.println("Exists : " + dir.exists());
	 * System.out.println("Is Directory : " + dir.isDirectory());
	 * System.out.println("Can Read : " + dir.canRead());
	 * System.out.println("Can Write : " + dir.canWrite()); if (!dir.exists()) {
	 * 
	 * System.out.println( "Current Path : " + CRMpath);
	 * 
	 * return; }
	 * 
	 * File[] listDir = dir.listFiles();
	 * 
	 * if (listDir == null || listDir.length == 0) {
	 * 
	 * System.out.println( "CRM Folder Empty : " + CRMpath);
	 * 
	 * return; }
	 * 
	 * int numberOfSubfolders = 0;
	 * 
	 * String filesname = null;
	 * 
	 * String subfilename = null;
	 * 
	 * String Sysadmin = "EMP00001";
	 * 
	 * for (int i = 0; i < listDir.length; i++) {
	 * 
	 * if (listDir[i] != null && listDir[i].isDirectory()) {
	 * 
	 * String filename =listDir[i].getName();
	 * 
	 * documentno = filename;
	 * 
	 * File subfile =new File(CRMpath, filename);
	 * 
	 * if (filename == null || filename.trim().isEmpty()) {
	 * 
	 * delete(subfile);
	 * 
	 * continue; }
	 * 
	 * f1 = subfile;
	 * 
	 * File[] listsubfiles = subfile.listFiles();
	 * 
	 * if (listsubfiles == null || listsubfiles.length == 0) {
	 * 
	 * continue; }
	 * 
	 * for (int j = 0; j < listsubfiles.length; j++) {
	 * 
	 * if (listsubfiles[j] == null) { continue; }
	 * 
	 * filesname =listsubfiles[j].getName();
	 * 
	 * if (filesname == null || !filesname.contains(".")) {
	 * 
	 * continue; }
	 * 
	 * subfilename = f1 + "\\" + filesname;
	 * 
	 * String fileNameWithoutExt =
	 * filesname.substring(0,filesname.lastIndexOf("."));
	 * 
	 * CommonMessage.debugMsg("File Name : "+ fileNameWithoutExt);
	 * 
	 * if (fileNameWithoutExt .equals(Actionplan)) {
	 * 
	 * try {
	 * 
	 * String count = ActionplanCount( documentno);
	 * 
	 * int actioncount = Integer.parseInt(count);
	 * 
	 * if (actioncount <= 0) {
	 * 
	 * readExcelFile( subfilename, documentno); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * try { ErrorLog( documentno, "Error at Reading Excel File"); } catch
	 * (Exception e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * e.printStackTrace(); } }
	 * 
	 * CommonMessage.debugMsg( "filesname : " + filesname);
	 * 
	 * DcmTlDocumentmanager newdcmTlDocumentmanager = new DcmTlDocumentmanager();
	 * 
	 * newdcmTlDocumentmanager .setDmdmSlno("1");
	 * 
	 * newdcmTlDocumentmanager .setDmdmFilename(filesname);
	 * 
	 * newdcmTlDocumentmanager .setDmdmPath("{}");
	 * 
	 * newdcmTlDocumentmanager .setDmdmBloblength("0");
	 * 
	 * // POSTGRES FIX newdcmTlDocumentmanager .setDmdmBlobfile(null);
	 * 
	 * newdcmTlDocumentmanager .setDmdmActive("Y");
	 * 
	 * newdcmTlDocumentmanager .setDmdmOwner(Sysadmin);
	 * 
	 * newdcmTlDocumentmanager .setDmdmApprovedby(Sysadmin);
	 * 
	 * newdcmTlDocumentmanager .setDmdmCreatedby(Sysadmin);
	 * 
	 * newdcmTlDocumentmanager .setDmdmCreatedon(dateTime);
	 * 
	 * newdcmTlDocumentmanager .setDmdmModifiedon(dateTime);
	 * 
	 * newdcmTlDocumentmanager .setDmdmDescription("-");
	 * 
	 * newdcmTlDocumentmanager .setDmdmRefdoctype("CRM");
	 * 
	 * newdcmTlDocumentmanager .setDmdmRefdocno(filename);
	 * 
	 * newdcmTlDocumentmanager .setDmdmPath( documentpath + "/" + filename + "_" +
	 * UIUtils.now());
	 * 
	 * try {
	 * 
	 * CRMFilecreate( newdcmTlDocumentmanager);
	 * 
	 * } catch (Exception e) {
	 * 
	 * try { ErrorLog( documentno, "Error at Writing Document Manager"); } catch
	 * (Exception e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * e.printStackTrace(); }
	 * 
	 * try {
	 * 
	 * File SourceFolder = new File( f1, filesname);
	 * 
	 * FileUtils.copyFileToDirectory( SourceFolder, DestinationFolder);
	 * 
	 * File refile = new File( DestinationFolder + "\\" + filesname);
	 * 
	 * refile.renameTo( new File( DestinationFolder + "\\" + documentno + "_" +
	 * UIUtils.now()));
	 * 
	 * delete(new File(subfilename));
	 * 
	 * } catch (IOException e) {
	 * 
	 * try { ErrorLog( documentno, "Error at File Copying Time"); } catch (Exception
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }
	 * 
	 * e.printStackTrace(); } }
	 * 
	 * delete(f1);
	 * 
	 * numberOfSubfolders++; } }
	 * 
	 * } else {
	 * 
	 * scheduleReportConfig .genScheduledReport( schedulerConfigMst);
	 * 
	 * SchedulerLog.info( "running job " + schedulerConfigMst .getDbFucntionName());
	 * } }
	 */
	
		
	private void readExcelFile(String filesname, String documentno) throws Exception {
		// TODO Auto-generated method stub
		 String extension = filesname.substring(filesname.lastIndexOf(".") + 1, filesname.length());
		 FileInputStream file = new FileInputStream(new File(filesname));
		 String userkeyid=UIUtils.getPropertyValue("com.akranta.tpm.resources.LocationConfig","userid");
		 String cellContent="Target Date";
		 if(extension.equals(excelxlsx))
    	 {
			 XSSFWorkbook workbook = new XSSFWorkbook (file);
			 XSSFSheet sheet = workbook.getSheetAt(0);
	        // Iterator<Row> rowIterator = sheet.iterator();
		    // Row row = rowIterator.next();
			//Iterator<Cell> cellIterator = row.cellIterator();
					//while(cellIterator.hasNext()) {
					Row rn=sheet.getRow(3);
						//Cell cell = cellIterator.next();
				  //commonFunctions.
					   String Actionplan=rn.getCell(2).getStringCellValue();
					   String responsibilty=rn.getCell(3).getStringCellValue();
					   String Status=rn.getCell(5).getStringCellValue();
					 //  String targetdate=rn.getCell(4).getStringCellValue();
					   DataFormatter formatter = new DataFormatter();
					  // double tardate=rn.getCell(4).getNumericCellValue();
					   String targetdate = formatter.formatCellValue(rn.getCell(4));
					   CommonMessage.debugMsg("targetdate>>>"+targetdate);

					  // =rn.getCell(4).getDateCellValue() ;
					   
					   String empcount=employeecheck(responsibilty);
					   int empcnt=Integer.parseInt(empcount);
					   String assignedprsn;
					   if(empcnt < 0)
					   {
						   assignedprsn=userkeyid;
					   }
					   else
					   {
						   assignedprsn=responsibilty;
					   }
					
		QtmTlSapCustComplaintsDao qtmTlSapCustComplaintsDao =new QtmTlSapCustComplaintsDaoImpl(dbActionTemplate);
		CommonFilter commonFilter =new CommonFilter();  
		commonFilter.setKey(documentno);
		  // List<String[]> result=qtmTlSapCustComplaintsDao.CRMDecription(commonFilter);
		List<String[]> result=CRMDecription(commonFilter);
		       String transactiondate=result.get(0)[0];
		       String location=result.get(0)[2];
		       String plantlocation=null;
		      
		     plantlocation= UIUtils.getPropertyValue("com.akranta.tpm.resources.LocationConfig",location.replaceAll("\\s+",""));
		      
		     
		     CommonMessage.debugMsg("plantlocation"+plantlocation);
		     
		       CommonMessage.debugMsg("transactiondate:::::"+transactiondate);
		       CommonMessage.debugMsg("targetdate:::::"+targetdate);
		      // int datedif=getDateDiffCRM(transactiondate, targetdate);
		       long datedif= CommonFunctions.getMonthDiff(transactiondate, targetdate);
		       long val=0;
		       String actStaus=null;
		       String defalutadddte;
		       if(val > datedif)
		       {
		    	   defalutadddte=CommonFunctions.addDay(transactiondate, 15);
		    	   CommonMessage.debugMsg("defalutadddte"+defalutadddte);
		       }
		       else{
		    	   defalutadddte=targetdate;
		       }
		       
		       if(Status.toLowerCase().equals("completed") || Status.toLowerCase().equals("done")  )
		       {
		    	   actStaus="C";
		       }
		       else{
		    	   actStaus="P";
		       }
		    /*   else{
		    	   String Sysdate=CommonFunctions.dateTimeNow();
		    	   long datedif1= CommonFunctions.getMonthDiff(transactiondate, targetdate);
		       }*/
		       CommonMessage.debugMsg("datedif>>>"+datedif);
		   	String currentDate=CommonFunctions.dateTimeNow();
		  
		       
		       String Crmdesc=result.get(0)[1];
		      // String flid=result.get(0)[2];
		    
					   GenTlActionplanmst genTlActionplanmst =new GenTlActionplanmst();
					   GenTlActionplanmst genTlActionplanmstnew =new GenTlActionplanmst();
						GenTlActionplandtl genTlActionplandtl =new GenTlActionplandtl();
						GenTlActionplandtl genTlActionplandtllist =new GenTlActionplandtl();
						genTlActionplanmst.setAplmCreatedby("EMP00001");
						genTlActionplanmst.setAplmDetailrefid(documentno);
					    genTlActionplanmst.setAplmFlid(plantlocation);
						genTlActionplanmst.setAplmMasterrefid(documentno);
						genTlActionplanmst.setAplmMaintask(Crmdesc);
						genTlActionplanmst.setAplmRefdoctype("CRM");
						genTlActionplanmst.setAplmStatus(actStaus);
						genTlActionplanmst.setAplmCreatedon(currentDate);
						genTlActionplanmst.setAplmActive("Y");
						genTlActionplanmst.setAplmModifiedon(currentDate);
						genTlActionplandtl.setApldResponsibility(assignedprsn);
						genTlActionplandtl.setApldStatus(actStaus);
						genTlActionplandtl.setApldTargetdate(defalutadddte);
						genTlActionplandtl.setApldActionplan(Actionplan);
						
						
						try{
							
							
							genTlActionplanmstnew=fillValues(genTlActionplanmst,genTlActionplandtl);	
							create(genTlActionplanmstnew, genTlActionplanmstnew.getGenTlActionplandtl());
							
						
							
						}
						catch(Exception e){
							String errormsg="Error at File Copying Time";
							ErrorLog(documentno,errormsg);
							e.printStackTrace();
						}
					
						
				
			file.close();
						
						
    	 }			
    	 else{
    		 
    	 }
    	 
	
	}


	
	
	/*
	 * private String ActionplanCount(String documentno) throws Exception { // TODO
	 * Auto-generated method stub if(dbActionTemplate==null) { // dbActionTemplate =
	 * new DBActionTemplate( getDataSource(dataidentifier)); }
	 * 
	 * return dbActionTemplate.
	 * getSingleValue("select COUNT(*) GEN_TL_ACTIONPLANMST where  APLM_REFDOCTYPE='CRM' AND APLM_MASTERREFID='"
	 * +documentno+"'");
	 * 
	 * }
	 */
	
	private String ActionplanCount(String documentno)
	        throws Exception {

	    return dbActionTemplate.getSingleValue(

	            "select COUNT(*) from GEN_TL_ACTIONPLANMST "
	                    + "where APLM_REFDOCTYPE='CRM' "
	                    + "AND APLM_MASTERREFID='"
	                    + documentno
	                    + "'"
	    );
	}

	
	private String employeecheck(String responsibilty) throws Exception {
		// TODO Auto-generated method stub
		if(dbActionTemplate==null)
		{
	//	dbActionTemplate = new DBActionTemplate( getDataSource(dataidentifier));
		}
		
		 return dbActionTemplate.getSingleValue("select COUNT(*) from GEN_TL_EMPLOYEEMST where EMPM_KEYID='"+responsibilty+"'");
	}
	
	private void  ErrorLog(String documentno,String Errormsg) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			if(dbActionTemplate==null)
			{
		//	dbActionTemplate = new DBActionTemplate( getDataSource(dataidentifier));
			}
			 
			  //return dbActionTemplate.
				String errorupdate=dbActionTemplate.getSingleValue("Update QTM_TL_SAPCUSTCOMPLAINTS set QCCP_ERRORLOG='" + Errormsg + "' where QCCP_SAPTRANSID='" +documentno+ "'   ");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		  
		// return dbActionTemplate.getSingleValue("select COUNT(*) from GEN_TL_EMPLOYEEMST where EMPM_KEYID='"+responsibilty+"'");
	}

	public static int getDateDiffCRM(String str1, String str2) throws ParseException {

	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-Mmm-yyyy hh:mm",Locale.ENGLISH);
	    java.util.Date date1 =  simpleDateFormat.parse(str1);
	    java.util.Date date2 =  simpleDateFormat.parse(str2);
	    CommonMessage.debugMsg(str1);
	    CommonMessage.debugMsg(str2);
	    int difInDays = (int) ((date2.getTime() - date1.getTime())/(1000*60*60*24));
	    return difInDays;
	}
	
	public static void delete(File file) {

	    if (file == null || !file.exists()) {
	        return;
	    }

	    if (file.isDirectory()) {

	        File[] files = file.listFiles();

	        if (files != null) {

	            for (int i = 0; i < files.length; i++) {

	                delete(files[i]);
	            }
	        }

	        file.delete();

	    } else {

	        file.delete();
	    }
	}
	
	/*
	 * public static void delete(File file) {
	 * 
	 * // Check if file is directory/folder if(file.isDirectory()) { // Get all
	 * files in the folder File[] files=file.listFiles();
	 * 
	 * for(int i=0;i<files.length;i++) {
	 * 
	 * // Delete each file in the folder delete(files[i]);
	 * 
	 * }
	 * 
	 * // Delete the folder file.delete();
	 * 
	 * }
	 * 
	 * 
	 * else {
	 * 
	 * // Delete the file if it is not a folder file.delete();
	 * 
	 * } }
	 */

	private static int findRow(XSSFSheet sheet, String cellContent) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == CellType.STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    return row.getRowNum();  
	                }
	            }
	        }
	    }               
	    return 0;
	}
	
	/*public static OracleDataSource getDataSource(String dataSourceIdentifier) throws NamingException{
		
		
		  Context initContext = new InitialContext();
		  Context envContext = (Context) initContext.lookup("java:/comp/env");
		//  CommonMessage.debugMsg(" dataSourceIdentifier " + dataidentifier);
		  OracleDataSource ds = (OracleDataSource) envContext.lookup("jdbc/"+dataSourceIdentifier);
		  
		  return ds;
	}*/
	


	public static DataSource getDataSource(String dataSourceIdentifier)
	        throws NamingException {

	    Context initContext = new InitialContext();

	    Context envContext =
	            (Context) initContext.lookup("java:/comp/env");

	    DataSource ds =
	            (DataSource) envContext.lookup("jdbc/" + dataSourceIdentifier);

	    return ds;
	}
	
	
	public GenTlActionplanmst create(GenTlActionplanmst genTlActionplanmst,
			GenTlActionplandtl genTlActionplandtl) throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql(); // contains
																					// dbtable,field
																					// names,
																					// Field
																					// types
																					// and
																					// related
																					// sqls
																					// of
																					// master
																					// table
		GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
		String elementId = genTlActionplanmst.getAplmElementid();
	 	String location = null;
	 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);

		try {
			
			 if(dbActionTemplate==null)
			    {  
                    dataSourceIdentifier = dataSourceIdentifier.toLowerCase();
			    //	dbActionTemplate = new DBActionTemplate( getDataSource(dataidentifier));
			    	//HttpServletRequest request = (HttpServletRequest)org.apache.catalina.core.ApplicationFilterChain.getLastServicedRequest();
			    	
			    	//DBActionTemplate dbActionTemplate =UIUtils.getDBActionTemplate(httpServletRequest);
			    }
			genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(
					seqIdentf, 10, "AP",
					"", "")); // set the sequnce number

			//genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(
			//		GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST, 10, "APLM",
			//		"", "")); // set the sequnce number

			// //CommonMessage.debugMsg("master key in create....."+genTlActionplanmst.getAplmKeyid());
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst
					.getAplmKeyid());
			sqls.add(GenTlActionplanmstSql.getInsertSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
			
			//String selectall=genTlActionplandtl.getActionplanidenfr();
			List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
			String currentDate=CommonFunctions.dateTimeNow();
			
			if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
				
		            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
					{
		            	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
		            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
		            	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
		            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
		            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
		            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
		            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
		            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
		            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
		            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
		            	genTlActionplandtlss.setApldCreatedon(currentDate);
		            	genTlActionplandtlss.setApldActive("Y");
		            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
		            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
		            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
		            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
		            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
		            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
		            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
		            	genTlActionplandtlss.setApldModifiedon(currentDate);
		            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
						sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
					} 
	            }
			 else{
			   if (!CommonFunctions
					.isValidKeyId(genTlActionplandtl.getApldKeyid())) {
				// //CommonMessage.debugMsg("master key in create....."+genTlActionplanmst.getAplmKeyid());
				genTlActionplandtl.setApldKeyid(dbActionTemplate
						.getSequenceNumber(
								GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,
								10, "APLD", "", "")); // set the sequnce number
				sqls.add(GenTlActionplandtlSql.getInsertSql(
						genTlActionplandtlSql.getApldDbFields(),
						genTlActionplandtl.getSaveArray()));
			}}
			sqls.add(GenTlActionplanmstSql.getUpdateStausSql(
					genTlActionplanmstSql.getAplmDbFields(),
					genTlActionplanmst.getSaveArray())); // add insert sql for
															// master table
			
			String sql = GenTlActionplanmstSql
					.getRefDocUpdateSql(genTlActionplanmst);
			
			if (CommonFunctions.isValidKeyId(sql))
				sqls.add(sql);
			dbActionTemplate.executeStatements(sqls); // execute the block of
														// sqls
			CommonMessage.debugMsg(" Inside 8 ");

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return genTlActionplanmst;
	}
	
	
	private GenTlActionplanmst fillValues(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl newGenTlActionplandtl)throws Exception 
	{
		CommonMessage.debugMsg("In Side fill values::::::;");
		String dateTime = CommonFunctions.dateTimeNow();
		genTlActionplanmst.setAplmActive("Y");	
		genTlActionplanmst.setAplmCreatedon(dateTime);
		genTlActionplanmst.setAplmModifiedon(dateTime);
		
		CommonMessage.debugMsg(" Master update servlet:::: In ServiceImpl :: "+ genTlActionplanmst.getAplmKeyid());
		CommonMessage.debugMsg(" MasterRefid :: In ServiceImpl :: "+ genTlActionplanmst.getAplmMasterrefid());
		CommonMessage.debugMsg(" DetailRefid :: In ServiceImpl :: "+ genTlActionplanmst.getAplmDetailrefid());
		CommonMessage.debugMsg(" Refdoctype() :: In ServiceImpl :: "+ genTlActionplanmst.getAplmRefdoctype());
		
		if(genTlActionplanmst.getAplmPillarid()==null)
			genTlActionplanmst.setAplmPillarid("{}");		
		if(genTlActionplanmst.getAplmMasterrefid()==null)
			genTlActionplanmst.setAplmMasterrefid("{}");
		if(genTlActionplanmst.getAplmDetailrefid()==null)
			genTlActionplanmst.setAplmDetailrefid("{}");
		if(genTlActionplanmst.getAplmMaintask()==null)
			genTlActionplanmst.setAplmMaintask("{}");
		if(genTlActionplanmst.getAplmRefdoctype()==null)
			genTlActionplanmst.setAplmRefdoctype("{}");
		if(genTlActionplanmst.getAplmStatus()==null)
			genTlActionplanmst.setAplmStatus("P");
		if(genTlActionplanmst.getAplmRemarks()==null)
			genTlActionplanmst.setAplmRemarks("{}");

		if(!UIUtils.isValidDate(genTlActionplanmst.getAplmPlandate()))
			genTlActionplanmst.setAplmPlandate(dateTime);
		
		if(genTlActionplanmst.getAplmTempfiled2()==null)
			genTlActionplanmst.setAplmTempfiled2("-");
		if(genTlActionplanmst.getAplmTempfiled3()==null)
			genTlActionplanmst.setAplmTempfiled3("-");
		if(genTlActionplanmst.getAplmTempfiled4()==null)
			genTlActionplanmst.setAplmTempfiled4("-");
		if(genTlActionplanmst.getAplmTempfiled5()==null)
			genTlActionplanmst.setAplmTempfiled5("-");	
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmCreatedby()==null)
			genTlActionplanmst.setAplmCreatedby("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		if(genTlActionplanmst.getAplmFlid()==null)
			genTlActionplanmst.setAplmFlid("{}");
		if(genTlActionplanmst.getAplmElementid()==null)
			genTlActionplanmst.setAplmElementid("{}");
		
		genTlActionplanmst.setGenTlActionplandtl(fillActionplandtlValues(newGenTlActionplandtl,genTlActionplanmst));		
		genTlActionplanmst.setAplmRemarks(newGenTlActionplandtl.getApldRemarks());
		CommonMessage.debugMsg("End Of  Fill values");
		return genTlActionplanmst;
	}

	private GenTlActionplandtl fillActionplandtlValues(GenTlActionplandtl genTlActionplandtl,GenTlActionplanmst genTlActionplanmst) {
		
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldAplmKeyid()))
			genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedby()))
			genTlActionplandtl.setApldCreatedby(genTlActionplanmst.getAplmCreatedby());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActive()))
			genTlActionplandtl.setApldActive("Y");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldActionplan()))
			genTlActionplandtl.setApldActionplan("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldHowtodo()))
			genTlActionplandtl.setApldHowtodo("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCountermeasure()))
			genTlActionplandtl.setApldCountermeasure("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldRemarks()))
			genTlActionplandtl.setApldRemarks("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldResponsibility()))
			genTlActionplandtl.setApldResponsibility("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldStatus()))
			genTlActionplandtl.setApldStatus("P");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTargetdate()))
			genTlActionplandtl.setApldTargetdate(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTradeid()))
			genTlActionplandtl.setApldTradeid("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompletedby()))
			genTlActionplandtl.setApldCompletedby("{}");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCompleatedon()))
			genTlActionplandtl.setApldCompleatedon(Constants.futureNullDate);
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldModifiedon()))
			genTlActionplandtl.setApldModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldCreatedon()))
			genTlActionplandtl.setApldCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldOthers()))
			genTlActionplandtl.setApldOthers("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled2()))
			genTlActionplandtl.setApldTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled3()))
			genTlActionplandtl.setApldTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled4()))
			genTlActionplandtl.setApldTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlActionplandtl.getApldTempfiled5()))
			genTlActionplandtl.setApldTempfiled5("-");
		
		return genTlActionplandtl;
	}
	
	public DcmTlDocumentmanager CRMFilecreate(
			DcmTlDocumentmanager dcmTlDocumentmanager) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		DcmTlDocumentmanagerSql dcmTlDocumentmanagerSql = new DcmTlDocumentmanagerSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			boolean islayoutAvailable = true;
			//this.dbActionTemplate = dbActionTemplate;
			CommonMessage.debugMsg("dbActionTemplate::"+dbActionTemplate);
		    if(dbActionTemplate==null)
		    {  
		    	//String location="bcm-pspd";
		    	//String environmnet="quality";
		    	//String dataSourceIdentifier = location+environmnet;
		    	String location = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_LOCATION");
				String env = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_ENVIRONMENT");
				String dataSourceIdentifier = location+env;
				dataSourceIdentifier = dataSourceIdentifier.toLowerCase();
		  //  	dbActionTemplate = new DBActionTemplate( getDataSource(dataSourceIdentifier));
		    	//HttpServletRequest request = (HttpServletRequest)org.apache.catalina.core.ApplicationFilterChain.getLastServicedRequest();
		    	
		    	//DBActionTemplate dbActionTemplate =UIUtils.getDBActionTemplate(httpServletRequest);
		    }
		
			
		/*dcmTlDocumentmanager.setDmdmKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentmanagerSql.TBL_DCM_TL_DOCUMENTMANAGER, 10, "DMM", "YYMM", "Y")); // set the sequnce number 
			sqls.add(DcmTlDocumentmanagerSql.getInsertSql(dcmTlDocumentmanagerSql.getDmdmDbFields(), dcmTlDocumentmanager.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls)*/; // execute the block of sqls
			
			
			DcmTlDocumentlayoutSql dcmTlDocumentlayoutSql = new DcmTlDocumentlayoutSql();
			if(CommonFunctions.isValidKeyId(dcmTlDocumentmanager.getDmdmIsodoctype()))
			{
				String slNoSql = DcmTlDocumentmanagerSql.getSlNo(dcmTlDocumentmanager.getDmdmIsodoctype());
				String slNo = dbActionTemplate.getSingleValue(slNoSql);
				
				if(CommonFunctions.isValidKeyId(slNo))
				{
					int sNo = Integer.parseInt(slNo)+1;
					dcmTlDocumentmanager.setDmdmSlno(Integer.toString(sNo));
				}
				else{
					islayoutAvailable = false;
				}
				sqls.add(DcmTlDocumentlayoutSql.updateFileAvl(dcmTlDocumentmanager.getDmdmIsodoctype()));
			}
			else{
				//if(!islayoutAvailable) 
					
				 
				DcmTlDocumentlayout dcmTlDocumentlayout = new DcmTlDocumentlayout();
				dcmTlDocumentlayout.setDmlyKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentlayoutSql.TBL_DCM_TL_DOCUMENTLAYOUT, 8, "DML", "YYMM", "Y")); // set the sequnce number
				 
					if(!UIUtils.isValidKeyId(dcmTlDocumentlayout.getDmlyParentid()) )
						dcmTlDocumentlayout.setDmlyParentid(dcmTlDocumentlayout.getDmlyKeyid());
					dcmTlDocumentlayout.setDmlyActive("Y");
					dcmTlDocumentlayout.setDmlyCreatedby(dcmTlDocumentmanager.getDmdmCreatedby());
					dcmTlDocumentlayout.setDmlyDisplayorder("2");
					dcmTlDocumentlayout.setDmlyLevelno("2");
					dcmTlDocumentlayout.setDmlyModifiedon(dcmTlDocumentmanager.getDmdmCreatedon());
					dcmTlDocumentlayout.setDmlyCreatedon(dcmTlDocumentmanager.getDmdmCreatedon());
					dcmTlDocumentlayout.setDmlyIsfileavl("N");
					dcmTlDocumentlayout.setDmlyIsparent("Y");
					dcmTlDocumentlayout.setDmlyName(dcmTlDocumentmanager.getDmdmRefdoctype());
					 CommonMessage.debugMsg("DLYNAME   "+dcmTlDocumentmanager.getDmdmRefdoctype());
				sqls.add(DcmTlDocumentlayoutSql.getInsertSql(dcmTlDocumentlayoutSql.getDmlyDbFields(), dcmTlDocumentlayout.getSaveArray())); // add insert sql for master table
			}
			
			dcmTlDocumentmanager.setDmdmKeyid(dbActionTemplate.getSequenceNumber(DcmTlDocumentmanagerSql.TBL_DCM_TL_DOCUMENTMANAGER, 10, "DMM", "YYMM", "Y")); // set the sequnce number 
			sqls.add(DcmTlDocumentmanagerSql.getInsertSql(dcmTlDocumentmanagerSql.getDmdmDbFields(), dcmTlDocumentmanager.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); //
			
		}catch(Exception e)
		{
			//throw new Exception(e.getMessage());
			e.printStackTrace();
		}
		return dcmTlDocumentmanager;
	}
	
	public List<String[]> CRMDecription(CommonFilter commonfilter) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		if(dbActionTemplate==null)
		{
		//dbActionTemplate = new DBActionTemplate( getDataSource(dataidentifier));
		}
		 
		sql.append("select TO_CHAR(QCCP_SAPTRANSDATE,'DD-Mon-YYYY'),QCCP_CATDESC,QCCP_PLANT from QTM_TL_SAPCUSTCOMPLAINTS WHERE QCCP_SAPTRANSID='"+commonfilter.getKey()+"' ");
		return dbActionTemplate.getDataList(sql.toString());
	}
	private void sendJobStartedMail(String jobName, LocalDateTime startTime) throws Exception {

	    if (!UIUtils.isValidKeyId(adminMailTo)) {
	        CommonMessage.debugMsg("SCHDULER_ADMIN_EMAIL not configured; skipping job-started mail");
	        return;
	    }

	    String subject = "Scheduler Job";
	    String body = " Following Job is started to execute \n Job Started: " + jobName;
	     body = "\n Job Name   : " + jobName + "\n"
	                + "Started On : " + formatDateTime(startTime);

	    notesMailClient.send(adminMailTo, "",  subject  , body,Collections.emptyList());
	}

	private void sendJobSummaryMail(String jobName, LocalDateTime startTime, LocalDateTime endTime,
	        String status, int successCount, int failedCount) throws Exception {

	    if (!UIUtils.isValidKeyId(adminMailTo)) {
	        CommonMessage.debugMsg("SCHDULER_ADMIN_EMAIL not configured; cannot send job-summary mail");
	        return;
	    }

	    long durationSeconds = Duration.between(startTime, endTime).getSeconds();

	    String title = "Scheduler Job";
	    String subject = "Job Summary: " + jobName + " [" + status + "]";

	    String body = "Job Name        : " + jobName + "\n"
	                + "Started On      : " + formatDateTime(startTime) + "\n"
	                + "Ended On        : " + formatDateTime(endTime) + "\n"
	                + "Duration        : " + durationSeconds + " sec\n"
	                + "Status          : " + status + "\n"
	                + "Mails Sent (OK) : " + successCount + "\n"
	                + "Mails Failed    : " + failedCount;

	    notesMailClient.send(adminMailTo, "",  subject ,body,Collections.emptyList());
	}

	private String formatDateTime(LocalDateTime dt) {
	    return dt.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
	}
}
