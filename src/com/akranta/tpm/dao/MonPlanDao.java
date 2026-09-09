package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderFormBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlGenmaintenance;
import com.akranta.tpm.model.PlmTlWofeedback;

public interface MonPlanDao {

	
	public List<String []> getMonPlan(CommonFilter commonFilter)  throws Exception;

	public Workbook getMonPlanExcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat)throws Exception;

	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllwoCompData(CommonFilter commonFilter, String viewChecked) throws Exception;

	public List<String[]> getspareData(String woId) throws Exception;
	
	public abstract PlmTlWofeedback saveWOD(PlmTlWofeedback plmTlWofeedback, WorkOrderDetailsBean workOrderDetailsBean) throws Exception;

	//public PlmTlWofeedback savegrdWOD(PlmTlWofeedback newPlmTlWofeedback,WorkOrderDetailsBean workOrderDetailsBean,WorkOrderFormBean newworkOrderFormBean) throws Exception;

	public String savegrdWOD(
			List<PlmTlWofeedback> newPlmTlWofeedbackList,
			WorkOrderDetailsBean workOrderDetailsBean) throws Exception;

	public List<String[]> getupdatecancelData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getupdatecancelselctData(CommonFilter commonFilter, String workorderno, String wOgenCancl)throws Exception;

	public String cancelallocated(String workorderno, String cancelWoId, String noofActivites) throws BusinessApplicationExceptions, Exception;

	public String generateWO(String selActforWoGen, String strtDate) throws Exception;
	
	public String generateWOForABN(String selActforWoGen, String strtDate)throws Exception;
	
	public String saveReschedule(String datas)throws Exception;

	public List<String[]> getAllModifyForm(CommonFilter commonFilter, String weekNO)throws Exception;

	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo)throws Exception;

	public String modifyCompWo(WorkOrderDetailsLstBean newworkOrderDetailsBean,
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

	
}
