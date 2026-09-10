
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CliTlStandardFormBean;
import com.akranta.tpm.bean.BAL_JhclitCalendarBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.businessvalidations.BAL_ClitCalValidations;
import com.akranta.tpm.dao.BAL_JhClitCalendarDao;
import com.akranta.tpm.dao.impl.BAL_JhClitCalendarDaoImpl;
import com.akranta.tpm.exportreport.JHClitExcelTemplate;
import com.akranta.tpm.exportreport.whywhyReportTemplate;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;
import com.akranta.tpm.model.BAL_PlmTlMultiplemethodsmst;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.service.BAL_CltiScheduleService;
//import com.akranta.tpm.service.JhClitCalendarService;
import com.akranta.tpm.utils.CommonFunctions;
//import com.akranta.tpm.dao.impl.BAL_JhClitCalendarDaoImpl;


public  class BAL_CltiScheduleServiceImpl implements BAL_CltiScheduleService {
	private DBActionTemplate dbActionTemplate;
private BAL_ClitCalValidations clitCalValidations;
private BAL_JhClitCalendarDao jhClitCalendarDao;

	public BAL_CltiScheduleServiceImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate =dbActionTemplate;
		jhClitCalendarDao =  new BAL_JhClitCalendarDaoImpl(dbActionTemplate);
		clitCalValidations =  new BAL_ClitCalValidations(dbActionTemplate);
	}

	public List<String[]> getAlljhnfnGetScheduledArray(CommonFilter commonFilter,String shiftId) throws Exception
	{
		System.out.println("inside Service Impl");
		try{
			boolean checkMachineExist = clitCalValidations.ChkCalndrYear(commonFilter);
			System.out.println("checkMachineExist  :"+checkMachineExist );
		
			if(! checkMachineExist){
				 clitCalValidations.genrateCalYear(commonFilter);
			}
		}catch(Exception e){
			System.out.println("Exception "+ e.getMessage());
		}
		return this.jhClitCalendarDao.getjhnfnGetScheduledArray(commonFilter,shiftId);
	}

	@Override
	public List<String[]> getAlljhnfngetshiftwise(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	} 
	
	@Override
	public BAL_JhclitCalendarModel update(List<BAL_JhclitCalendarModel>  jhclitCalendarModelList ,String from_month,String user_createdBy,BAL_JhclitCalendarBean jhclitCalendarBean )throws Exception {
	try{
		System.out.println("Update called");
		System.out.println("Inside the ServiceImpl update    "+from_month);
		String ActionMode = jhclitCalendarBean.getActionmode();
		
		List<BAL_JhclitCalendarModel> newJhclitCalendarModel = new ArrayList<BAL_JhclitCalendarModel>();
		
		double totalPlan  = getTotalPlanDuration(jhclitCalendarModelList);
		
		String dateTime = CommonFunctions.dateTimeNow();
		String planDate = jhclitCalendarModelList.get(0).getClcaPlandate()+"-"+from_month;
		String actual = jhclitCalendarModelList.get(0).getClcaActualduration();
		
		System.out.println("ActionmodeService   :"+ jhclitCalendarBean.getActionmode());
		
		if(Double.parseDouble( actual ) <= 0 && ActionMode!= "delete" )
			throw new ValidationExceptions("actual_msg");
		
		for( BAL_JhclitCalendarModel jhclitCalendarModel : jhclitCalendarModelList)
		{	

			jhclitCalendarModel.setClcaModifiedon(dateTime);
			
			jhclitCalendarModel.setClcaCreatedby(user_createdBy);
			jhclitCalendarModel.setClcaPlandate(planDate);
			
			if(jhclitCalendarModel.getClcaDuration()== null)  
			   jhclitCalendarModel.setClcaDuration("0");
			  
			if(jhclitCalendarModel.getClcaActualdate()== null)
			   jhclitCalendarModel.setClcaActualdate(dateTime);	
			if(ActionMode!= "delete"){
			Double actualDuaration = Double.parseDouble(jhclitCalendarModel.getClcaActualduration() )* (Double.parseDouble(jhclitCalendarModel.getClcaDuration())/totalPlan  );
			System.out.println(" actualDuaration " + actualDuaration);
			
			
			 DecimalFormat f = new DecimalFormat("##.00"); 
			
			jhclitCalendarModel.setClcaActualduration(f.format(actualDuaration));		 
			}
			else{
				jhclitCalendarModel.setClcaActualduration("0");
			}
			if(jhclitCalendarModel.getClcaClirefid()== null)
			   jhclitCalendarModel.setClcaClirefid("{}");
			   
			if(ActionMode!= "delete")
			   jhclitCalendarModel.setClcaStatus("Y");
			else
				jhclitCalendarModel.setClcaStatus("X");
			
			newJhclitCalendarModel.add(jhclitCalendarModel);	

		}
		return this.jhClitCalendarDao.update(newJhclitCalendarModel);
	}
	catch(Exception e)
	{
		
		throw new ValidationExceptions("actual_msg");
	}
	}
	private double getTotalPlanDuration( List<BAL_JhclitCalendarModel>  jhclitCalendarModelList){
		System.out.println("Inside total");
		Double total = 0d;
		for( BAL_JhclitCalendarModel jhclitCalendarModel : jhclitCalendarModelList)
		{	
			total += Double.parseDouble(jhclitCalendarModel.getClcaDuration());	
			
		}
		System.out.println("total    :"+total );
		return total;
		
	}

	@Override
	public BAL_JhclitCalendarModel delete( String flag) {
		// TODO Auto-generated method stub
		System.out.println("inside service Impl of delerte");
	List<BAL_JhclitCalendarModel> newJhclitCalendarModel = new ArrayList<BAL_JhclitCalendarModel>();
		
		
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		
		for( BAL_JhclitCalendarModel jhclitCalendarModel : newJhclitCalendarModel)
		{	

			jhclitCalendarModel.setClcaModifiedon(dateTime);
			System.out.println(jhclitCalendarModel.getClcaModifiedon());
			
			if(jhclitCalendarModel.getClcaDuration()== null)
			   jhclitCalendarModel.setClcaDuration("0");
			if(jhclitCalendarModel.getClcaActualduration()== null)
				jhclitCalendarModel.setClcaActualduration("0");
			if(jhclitCalendarModel.getClcaActualdate()== null)
			   jhclitCalendarModel.setClcaActualdate(dateTime);	
			if(jhclitCalendarModel.getClcaClirefid()== null)
			   jhclitCalendarModel.setClcaClirefid("{}");
			if(jhclitCalendarModel.getClcaStatus()== null)
			   jhclitCalendarModel.setClcaStatus("{}");
			if(jhclitCalendarModel.getClcaCreatedby()== null)
			   jhclitCalendarModel.setClcaCreatedby("{}");
			
			newJhclitCalendarModel.add(jhclitCalendarModel);	

		}
		return this.jhClitCalendarDao.update_del(newJhclitCalendarModel,flag);
	}

	@Override
	public Workbook JHCLITExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		return this.jhClitCalendarDao.JHCLITExportExcel(commonFilter,colModel,rptFormat);
	}

	@Override
	public List<String[]> getConfiglink() throws Exception {
		// TODO Auto-generated method stub
		return  this.jhClitCalendarDao.getConfiglink();
	}

	@Override
	public Workbook getJhnCalenderExportExcel(CommonFilter commonFilter,String format, String path, String imagePath) throws Exception {
		String shiftId="";
		System.out.println("inside Service Impl");
		try{
			boolean checkMachineExist = clitCalValidations.ChkCalndrYear(commonFilter);
			System.out.println("checkMachineExist  :"+checkMachineExist );
		
			if(! checkMachineExist){
				 clitCalValidations.genrateCalYear(commonFilter);
			}
		}catch(Exception e){
			
		}
		List<String[]> jhCalenderData = jhClitCalendarDao.getjhnfnGetScheduledArray(commonFilter,shiftId);
				//Workbook wb = ( new JHClitExcelTemplate(dbActionTemplate)).fillValues(jhCalenderData,format,path,commonFilter,imagePath);
				Workbook wb = ( new JHClitExcelTemplate(dbActionTemplate)).fillValues(jhCalenderData, format, path, commonFilter, imagePath);
				return 	wb;
	}

	@Override
	public List<GenTlToolsimg> getCLTIImage(String fileName,String filePath, String keyid) throws Exception {

//List<GenTlToolsimg> jhnImgList = null;
		
CommonFunctions.debugMsg("kaizen img...test");
List<GenTlToolsimg> jhnImgList = new ArrayList<GenTlToolsimg>();
CommonFunctions.debugMsg("kaizen img...");
if(keyid!=null)
{
	CommonFunctions.debugMsg("fileName:"+fileName+",path:"+filePath+",kaizId:"+keyid);	
	GenTlToolsimg jhnBeforeImage = new GenTlToolsimg();
	
	jhnBeforeImage.setToimBlobimage(filePath);
	//jhnBeforeImage.setToimBloblength(toimBloblength)
	jhnBeforeImage.setToimFilename(fileName);
	jhnBeforeImage.setToimKeyid(keyid);
	//jhnBeforeImage.setToimModifiedon(toimModifiedon)
	
	jhnImgList.add(jhnBeforeImage);	
		CommonFunctions.debugMsg("kznImgList:"+jhnImgList);
}
jhnImgList = jhClitCalendarDao.getCLTIImage(jhnImgList);

return jhnImgList;


	}

	@Override
	public String save(List<String> actlst, String cellId,
			String mchineId, String shiftId,String createdBy,String status, String Observation,String tagClass) throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.save(actlst,cellId, mchineId,shiftId,createdBy,status,Observation,tagClass);
	}

	@Override
	public String getShift(ShiftBean shiftBean) throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.getShift(shiftBean);
	}

	@Override
	public Workbook jhComplienceExportExcel(CommonFilter commonFilter,
			JSONObject tableModel, String format) throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.jhComplienceExportExcel(commonFilter,tableModel,format);
	}

	@Override
	public List<String[]> jhComplience(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.jhComplience(commonFilter);
	}

	@Override
	public List<String[]> getEmployeeMailList(String sectionId) throws Exception {
		// TODO Auto-generated method stub
		
		CommonFunctions.debugMsg(" sectionId sectionId "+sectionId);
		return jhClitCalendarDao.getEmployeeMailList(sectionId);
	}

	@Override
	public Workbook JhExportExcel(String refId,JSONObject colmodel, String format) throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.JhExportExcel(refId,colmodel,format);
	}

	@Override
	public ResultSet JhExportData(String refId) throws Exception {
		// TODO Auto-generated method stub
		return jhClitCalendarDao.JhExportData(refId);
	}

	
	/*public List<String[]> getAlljhnfnGetScheduledArray(CommonFilter commonFilter)
			throws Exception {
		System.out.println("inside Service Impl");
		try{
			boolean checkMachineExist = clitCalValidations.ChkCalndrYear(commonFilter);
			System.out.println("checkMachineExist  :"+checkMachineExist );
		
			if(! checkMachineExist){
				 clitCalValidations.genrateCalYear(commonFilter);
			}
		}catch(Exception e){
			
		}
		return this.jhClitCalendarDao.getjhnfnGetScheduledArray(commonFilter);
	} 
*/
	
	
	}




