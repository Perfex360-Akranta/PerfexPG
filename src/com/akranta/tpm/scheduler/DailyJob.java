package com.akranta.tpm.scheduler;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import com.akranta.tpm.utils.CommonMessage;
public class DailyJob implements Job {

	
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		 JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		 ScheduleReportConfig scheduleReportConfig  = (ScheduleReportConfig) dataMap.get("scheduleReportConfig");
		// List<SchedulerConfigMst> schedulerConfigList  = (List<SchedulerConfigMst>) dataMap.get("schedulerConfigList");
		 SchedulerConfigMst schedulerConfigMst = (SchedulerConfigMst) dataMap.get("schedulerConfigMst");
		// for(SchedulerConfigMst schedulerConfigMst : schedulerConfigList){
			 scheduleReportConfig.genScheduledReport(schedulerConfigMst);
		 //}
		 
		CommonMessage.debugMsg(" running job " +schedulerConfigMst.getDbFucntionName() );
		
	}

	
}
