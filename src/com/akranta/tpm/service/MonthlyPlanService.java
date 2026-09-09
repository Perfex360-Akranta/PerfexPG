package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MilestoneBean;
//import com.akranta.tpm.bean.SopBean;
import com.akranta.tpm.bean.WorkOrderDetailsBean;
import com.akranta.tpm.bean.WorkOrderDetailsLstBean;
import com.akranta.tpm.bean.WorkOrderFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.model.MspTlDtl;
import com.akranta.tpm.model.MspTlIndicators;
import com.akranta.tpm.model.MspTlIndicatorsDtl;
import com.akranta.tpm.model.MspTlIndicatorsMst;
import com.akranta.tpm.model.MspTlMst;
import com.akranta.tpm.model.PlmTlWofeedback;

public interface MonthlyPlanService {
	
	public List<String []> getMonPlan(CommonFilter commonFilter)  throws Exception;

	public Workbook getMonPlanExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;

	public List<String[]> getAllwoGenData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getAllwoCompData(CommonFilter commonFilter, String viewChecked) throws Exception;

	public List<String[]> getspareData(String pmstdId) throws Exception;

	public PlmTlWofeedback saveWOD(PlmTlWofeedback newPlmTlWofeedback,
			PlmTlWofeedback existPlmTlWofeedback,
			WorkOrderDetailsBean workOrderDetailsBean) throws ValidationExceptions, SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception;

	public String savegrdWOD(WorkOrderDetailsBean workOrderDetailsBean,
			WorkOrderFormBean newworkOrderFormBean) throws Exception;

	public List<String[]> getupdatecancelData(CommonFilter commonFilter) throws Exception;

	public List<String[]> getupdatecancelselctData(CommonFilter commonFilter, String workorderno, String wOgenCancl) throws Exception;

	public String cancelallocated(String workorderno, String cancelWoId, String noofActivites) throws BusinessApplicationExceptions, Exception;

	public String generateWO(String selActforWoGen, String strtDate) throws Exception;
	
	public String generateWOForABN(String selActforWoGen, String strtDate)throws Exception;
	
	public String saveReschedule(String datas) throws Exception;

	public List<String[]> getAllModifyForm(CommonFilter commonFilter, String weekNO)throws Exception;

	public String updateallocated(String workorderno, String updateWoId,
			String allotedtocombo)throws Exception;

	public String modifyCompWo(WorkOrderDetailsLstBean newworkOrderDetailsBean,
			String completedBy)throws Exception;

	public List<String[]> getkaizenData(CommonFilter commonFilter, String kAIZEN)throws Exception;

	public String generateWOForKZN(String selActforWoKzn, String strtDate)throws Exception;
	public MspTlIndicators select(String nodeId,String cellId) 	throws Exception; 
	public MspTlIndicatorsDtl selectIndicatorsDtl(String nodeId,String Flid) 	throws Exception; 
	public MspTlMst selectMst(String indicatorId,String cellId,String fromDate,String toDate,String flag)throws Exception;
	public List<MspTlIndicators> getMasterPlanActivities(MspTlIndicators mspTlIndicators) throws Exception;
	public List<MspTlIndicatorsDtl> getIndicators(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception;
	public MspTlIndicators create(MspTlIndicators newMspTlIndicators,MspTlIndicators oldMspTlIndicators,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicators update(MspTlIndicators newMspTlIndicators,MspTlIndicators oldMspTlIndicators,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicators delete(MspTlIndicators newMspTlIndicators)throws Exception;
	public MspTlIndicatorsDtl createInd(MspTlIndicatorsDtl newMspTlIndicatorsDtl,MspTlIndicatorsDtl oldMspTlIndicatorsDtl,MspTlIndicatorsMst newMspTlIndicatorsMst,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicatorsDtl updateInd(MspTlIndicatorsDtl newMspTlIndicatorsDtl,MspTlIndicatorsDtl oldMspTlIndicatorsDtl,MspTlIndicatorsMst newMspTlIndicatorsMst,MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicatorsDtl deleteInd(MspTlIndicatorsMst newMspTlIndicatorsMst,MspTlIndicatorsDtl newMspTlIndicatorsDtl)throws Exception;
	public MspTlMst createMilestone(MspTlMst newMspTlMst,MspTlMst oldMspTlMst, MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlMst updateMilestone(MspTlMst newMspTlMst,MspTlMst oldMspTlMst, MilestoneBean msBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlDtl deleteMilestone(MspTlDtl mspTlDtl)throws Exception;
	public MspTlMst deleteMilestone(MspTlMst mspTlMst,MilestoneBean msBean)throws Exception;
	public MspTlIndicators assignParent(MspTlIndicators newMspTlIndicators)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public MspTlIndicatorsDtl assignToParent(MspTlIndicatorsDtl newMspTlIndicatorsDtl)throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public List<ComboBox> getCategoryComboList(String cellId)throws Exception;
	public List<ComboBox> getSubCategoryComboList(String cellId,String parentId)throws Exception;
	public List<String[]> getActivitiesForSubcategory(MspTlIndicators mspTlIndicators) throws Exception;
	public List<String[]> getIndicatorsForSubcategory(MspTlIndicatorsDtl mspTlIndicatorsDtl,MspTlIndicatorsMst mspTlIndicatorsMst) throws Exception;
	public List<String[]> getPlanActual(CommonFilter commonFilter,String pillar,String masterkeyid)throws Exception;
	public List<String[]> getAllMilestones(CommonFilter commonFilter,String indicatorId,String cellId,String mode,String fromDate) throws Exception;
	public List<String[]> getAllHistory(String dtlId) throws Exception;
	public List<String[]> getIndicatorsGrid(CommonFilter commonFilter,String pillar) throws Exception;
	public List<String[]> getColorsFromConfiguration() throws Exception;
	public  List<String[]> getSearchIndicator(String indicatorId,String indName) throws Exception;
	public Workbook getAllIndicatorsExcel(JSONObject jsonObject,CommonFilter commonFilter,String format,String pillar)throws Exception;
	public Workbook getPlanActualExcel(String pillar,JSONObject jsonObject,CommonFilter commonFilter,String format)throws Exception;
	public Workbook getAllHistoryExcel(String dtlId,JSONObject jsonObject,String format)throws Exception;
	public List<String[]> getMonthlyPlanRpt(CommonFilter commonFilter) throws Exception;
	public String getTitle(String cellId,String pillar,String title) throws Exception;
	public Workbook getMonthlyPlanRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;

	public List<ComboBox> getActivityComboList(String cellId) throws Exception;

	public List<String[]> getfillActivity(CommonFilter commonFilter)throws Exception;

	public String generateWOSDM(String selActforWoGen, String strtDate)throws Exception;

	public List<String[]> getfillgridheader()throws Exception;

	public List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception;

	public List<String[]> getdatapopup(String indicatorId,String flag) throws Exception;

	public MspTlIndicatorsMst create(MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean) throws Exception;

	public MspTlIndicatorsMst update(MspTlIndicatorsMst newMspTlIndicatorsMst,
			MspTlIndicatorsMst existMspTlIndicatorsMst, MilestoneBean msBean) throws Exception;

	public MspTlIndicatorsMst selectMasKeyid(String masterkeyid) throws Exception;

	public List<String[]> getplandate(String nodeId) throws Exception;

	//public List<String[]> getdatapopup(String indicatorId, String flag);

}
