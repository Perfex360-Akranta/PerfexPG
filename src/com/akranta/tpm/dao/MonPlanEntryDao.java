package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BalWorkOrderDetailsBean;
import com.akranta.tpm.bean.BalWorkOrderDetailsLstBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.model.BalPlmTlCalendar;
import com.akranta.tpm.model.BalPlmTlWofeedback;
import com.akranta.tpm.model.CommonFilter;

public interface MonPlanEntryDao {

	
	public List<String []> getMonPlan(CommonFilter commonFilter,String type)  throws Exception;

	public Workbook getMonPlanExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat)throws Exception;

	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllwoCompData(CommonFilter commonFilter, String viewChecked,String type) throws Exception;
	public List<String[]> getAllwoCompDataExcel(CommonFilter commonFilter, String viewChecked,String type) throws Exception;

	public List<String[]> getspareData(String woId) throws Exception;
	
	public abstract BalPlmTlWofeedback saveWOD(BalPlmTlWofeedback plmTlWofeedback, BalWorkOrderDetailsBean workOrderDetailsBean) throws Exception;

	//public PlmTlWofeedback savegrdWOD(PlmTlWofeedback newPlmTlWofeedback,WorkOrderDetailsBean workOrderDetailsBean,WorkOrderFormBean newworkOrderFormBean) throws Exception;

	public String savegrdWOD(
			List<BalPlmTlWofeedback> newPlmTlWofeedbackList,
			BalWorkOrderDetailsBean workOrderDetailsBean,BalPlmTlWofeedback nwPlmTlWofeedback) throws Exception;

	public List<String[]> getupdatecancelData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getupdatecancelselctData(CommonFilter commonFilter, String workorderno, String wOgenCancl)throws Exception;

	public String cancelallocated(String workorderno, String cancelWoId, String noofActivites) throws BusinessApplicationExceptions, Exception;

	public String generateWO(String selActforWoGen, String strtDate) throws Exception;
	
	public String generateWOForABN(String selActforWoGen, String strtDate)throws Exception;
	
	public String saveReschedule(String datas)throws Exception;

	public List<String[]> getAllModifyForm(CommonFilter commonFilter, String weekNO)throws Exception;

	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo,BalPlmTlWofeedback newPlmTlWofeedback)throws Exception;

	public String modifyCompWo(BalWorkOrderDetailsLstBean newworkOrderDetailsBean,
			String completedBy)throws Exception;

	public List<String[]> getkaizenData(CommonFilter commonFilter, String kAIZEN)throws Exception;

	public String generateWOForKZN(String selActforWoKzn, String strtDate)throws Exception;

	public List<String[]> getMonthlyPlanRpt(CommonFilter commonFilter) throws Exception;

	

	Workbook getMonthlyPlanRptExportExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception;

	public List<String[]> getfillActivity(CommonFilter commonFilter)throws Exception;

	public String generateWOSDM(String selActforWoGen, String params)throws Exception;

	public List<String[]> getfillgridheader()throws Exception;

	public List<String[]> getfillgriddata()throws Exception;

	public List<String[]> getdatapopup(String indicatorId, String flag) throws Exception;

	public List<String[]> getObservations(CommonParams  commonParams ) throws NoDataFoundException, Exception;
	public void saveObservations(BalPlmTlCalendar plmTlCalendar) throws Exception;

	public List<String[]> getMultipleResponsibility(String pmwoKeyid,
			CommonParams commonParams) throws Exception;

	public String generateWO(String selActforWoGen, String strtDate,
			BalPlmTlWofeedback newPlmTlWofeedback) throws Exception;

	public Workbook pmCheckList(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception;

	public List<String[]> refreshProcess(CommonFilter commonFilter);

	public List<String[]> getMachine(String machineid)throws Exception;

	public List<String[]> getAllwoGenDataMobile(CommonFilter commonFilter)throws Exception;
	public List<String[]> getSchedule(CommonFilter commonFilter)throws Exception;
	
	public void MonPlanEntryDaoImplJwt(String jwtToken);

}
