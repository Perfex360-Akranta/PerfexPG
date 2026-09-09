package com.akranta.tpm.scheduler;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage ;

public class MailScheduler extends HttpServlet  {
	ServletContext sc;
	 private static final String DESTINATION_DIR_PATH = "scheduleReports";
	 public static final String CONST_SCHEDULERCONFIGMST_IDENT = "schedulerConfigMst";
	 public static final String CONST_SCHEDULEREPORTCONFIG_IDENT = "scheduleReportConfig" ;
	 private static String docRealPath;
	    private static String DOC_ROOT_PATH;
	    private static final String DOCMANAGER_PATH_SAVE = "/docmanager";
	    private static  String APP_DOCMANAGER_PATH ;
	 public void init(ServletConfig config) throws ServletException{
		  //  Scheduler sched;
		 docRealPath = config.getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
	        
	        boolean s = new File(docRealPath).mkdirs();
	        
	        DOC_ROOT_PATH =config.getServletContext().getRealPath("DocumentManagerServlet") ;
	        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
	        
	        String parentFolderName = DOC_ROOT_PATH.substring(DOC_ROOT_PATH.lastIndexOf("\\")+1);
	       // CommonMessage.debugMsg( " parentFolderName "  + parentFolderName);
	        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
	        DOC_ROOT_PATH = new File(DOC_ROOT_PATH).getParent();
	        //APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
	        CommonMessage.debugMsg("DOC_ROOT_PATH Path : "+ DOC_ROOT_PATH);
	        APP_DOCMANAGER_PATH = DOC_ROOT_PATH +"/" +parentFolderName ;
	        String basePath = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "FILEMANGER_BASE_PATH");
	        CommonMessage.debugMsg(" FileManagerBasePath " + basePath );
	        
	        boolean pathExist = new File( basePath).exists();
	        CommonMessage.debugMsg(" basePath  pathExist " + basePath + "  " + pathExist );
	        if( basePath != null && pathExist ){
	        	DOC_ROOT_PATH = basePath;
	        	APP_DOCMANAGER_PATH = basePath +"/" +parentFolderName ;
	        	
	        }
	        CommonMessage.debugMsg(" DOC_ROOT_PATH " + DOC_ROOT_PATH );
	        CommonMessage.debugMsg(" ----APP_DOCMANAGER_PATH " + APP_DOCMANAGER_PATH );
	    	
	        CommonMessage.debugMsg("APP_DOCMANAGER_PATH IN MAIL SCHEDULER"+APP_DOCMANAGER_PATH);
	        CommonMessage.debugMsg("DOC_ROOT_PATH IN MAIL SCHEDULER"+DOC_ROOT_PATH);
		 
		
	        try {
	          
	        	/* check whether the scheduler is enabled from ApplicationConfig file*/
	        	String eSchedule = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "SCHDULER_ENABLE");
	        	if( ! "YES".equalsIgnoreCase(eSchedule) ){
	        		CommonMessage.debugMsg(" Scheduler not enabled ");
	        		return ;
	        	}		
	        	/* Creates a folder scheduleReports in Application Paths to store all generated files */
	        	String  filePath = config.getServletContext().getRealPath(DESTINATION_DIR_PATH) + "\\";
	        	//String documnger=config.getServletContext().getRealPath(DOCMANAGER_PATH_SAVE) + "\\";
	        	CommonMessage.debugMsg("filePath"+filePath);
	        //	CommonMessage.debugMsg("documnger in mail scheduler"+documnger);
	        	
	            new File(filePath).mkdirs();
	             
	            /*initialise and instantiate ScheduleReportConfig Object */
	        	ScheduleReportConfig scheduleReportConfig = new ScheduleReportConfig( filePath );
	        	 
	        	/* get All scheduler reports configuration from db and store into the List*/
				List<SchedulerConfigMst> schedulerConfigList   =  scheduleReportConfig.getScheduleInfo(null);
			
				/* Create Scheduler Factory Object*/
				SchedulerFactory sf = new StdSchedulerFactory();
				Scheduler sched = sf.getScheduler();
				
				/* Create and Initialise the job and Scheduler based on configurations*/
				for(SchedulerConfigMst schedulerConfigMst:schedulerConfigList){
        	
					if(schedulerConfigMst.getReportName().equals("Action Plan"))
							{
						       schedulerConfigMst.setTitle(APP_DOCMANAGER_PATH);
							}
					
					/* Instantiate  AppMailJobs and store it with an identifier*/
				    JobDetail job = JobBuilder.newJob(AppMailJobs.class).withIdentity(schedulerConfigMst.getTitle().replaceAll(" ", "")).build();
		            //job.getJobDataMap().put("schedulerConfigList", schedulerConfigList);
				    /* Store related data need to run the job */
		            job.getJobDataMap().put(CONST_SCHEDULERCONFIGMST_IDENT, schedulerConfigMst);
		            job.getJobDataMap().put(CONST_SCHEDULEREPORTCONFIG_IDENT, scheduleReportConfig);
		            
		            /*ScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
		                                                .withIntervalInSeconds(100).repeatForever();
		            */
		            
		            /*Trigger trigger = TriggerBuilder.newTrigger()
		                                .withIdentity("MailTrigger")
		                                .withSchedule(scheduleBuilder).startNow().build();
		 			*/
		            
					try {
						/* Create trigger based on frequency */
						Trigger trigger = TriggerBuilder.newTrigger()
								    		.withIdentity(schedulerConfigMst.getReportName().replaceAll(" ", ""),
								    						schedulerConfigMst.getTitle().replaceAll(" ", ""))
								    		.withSchedule(CronScheduleBuilder.cronSchedule(schedulerConfigMst.getFrequency()))
								    		.build();
						
						/*Map the job with trigger */
						
				         sched.scheduleJob(job, trigger);
						
						//sched.scheduleJob(job, trigger);
						
						CommonMessage.debugMsg(" scheduling  " + schedulerConfigMst.getReportName());
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
				}
				
	        } catch (NoDataFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	        } catch (SchedulerException e) {
	            e.printStackTrace();
	        }
		 
	 }
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//response.getWriter().append("Served at: ").append(request.getContextPath());
			try{
				process(request,response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			//doGet(request, response);
			try{
				process(request,response);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	 
	 private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException,Exception{
		    HttpSession httpSession=request.getSession(false);
		 //   request.getServletContext().setAttribute("stringName", someObject); 
	 }


}
