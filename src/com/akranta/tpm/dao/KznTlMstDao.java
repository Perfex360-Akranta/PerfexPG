package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.QtmTlCustcomplaintdtl;

public interface KznTlMstDao {

	public abstract KznTlMst create(KznTlMst kznTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink, GenTlDocupdates genTlDocupdates, QtmTlCustcomplaintdtl qtmTlCustcomplaintdtl) throws BusinessApplicationExceptions,Exception;
	public abstract KznTlMst update(KznTlMst kznTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink, GenTlDocupdates genTlDocupdates) throws Exception;
	public abstract KznTlMst delete(KznTlMst kznTlMst) throws Exception;
	public KznTlMst deleteKzn(KznTlMst newKznTlMst) throws Exception;
	public KznTlMst select(String kznKeyid) throws Exception ;
	public List<String[]> getKaizenReport(CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAllKaizenDeleteRpt(CommonFilter commonFilter)throws Exception ;
	public List<GenTlAllmoduleimgfile> saveKznImg(KznTlMst kznTlMst, KaizenFormBean kaizenFormBean)throws Exception;
	public List<GenTlAllmoduleimgfile> getKznImage(List<GenTlAllmoduleimgfile> kznImgList)throws NoDataFoundException, Exception;
	public List<String[]> getAllPillarLink(String kznId) throws Exception;
	public List<String[]> getkznImprvCategory(List<String> pillarId)throws Exception;
	public List<String[]> getMultiSelectLoss()throws Exception;
	public List<String[]> getkznHDScanTbl(String kaizenId, CommonFilter commonFilter)throws Exception;
	public List<String[]> getkznWhyWhyData(String wwmsKeyid)throws Exception;
	public List<String[]> getKaizenCompletedDtls(CommonFilter commonFilter)throws Exception;;
	public KznTlMst deleteKznHd(String khdmkeyIds)throws Exception;
	public List<String[]> getKaizenHDView(CommonFilter commonFilter)throws Exception;
	public KznTlMst updateKznComplete(KznTlMst newKznTlMst)throws Exception;
	public List<String[]> getGraphData(String kznKeyid,String fromMonth, String toMonth)throws Exception;
	public List<String[]> getKznHdCellMch(String kznKeyid)throws Exception;
	public  List<String[]> updateKznComplete(String kznmKeyid,String khdmKeyid, String remarks, String completedBy,
			String completedDate,String modifiedDate)throws Exception;
	public KznTlMst updateKznCompletion(KznTlMst kznTlMst)throws Exception;
	public List<String[]> getResultData(String kznKeyid)throws Exception;
	public KznTlHdmst updateKznHDCompletion(KznTlHdmst kznTlHdmst)throws Exception;
	public List<String[]> chkForDuplicates(KznTlMst newKznTlMst)throws Exception;
	public List<String[]> fillkznComplete(String kznKeyid)throws Exception;
	public KznTlMst updateDocUpdates(KznTlMst kznTlMst, String kznmKeyid, String docId)throws Exception;
	public Workbook kaizenRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType)throws Exception;
	public abstract Workbook getEmPillarReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter)throws Exception;
	public Workbook kaizenCompleteRptExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,String reportType, boolean isKznHd)throws Exception;
	public List<String[]> getAllGraphMnths(String kznKeyid)throws Exception;
	///added For kaizen Approval by sriram
	public List<String[]> getAllKaizenApproval(CommonFilter commonFilter)throws Exception;
	public List<String[]> getAllBenifitReport(String type) throws Exception;
	public List<String[]> getAllPiller() throws Exception;
	public List<String[]> getAllAuthorization(String type) throws Exception;
	public abstract List<String[]> getBTSGridData(CommonFilter commonFilter) throws Exception;
	public abstract String selectKznb(String kznbKeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract String getElementID(String kznmFlid) throws Exception;
	public abstract String getkaizenTheme(String kznbKeyid) throws Exception;
	public abstract List<String[]> FillControlData(String keyid)throws Exception;
	public abstract List<String[]> FillTeamControlData(String keyid)throws Exception;
	public abstract Workbook kaizenApprovalExportExcel(JSONObject colmodel,
			      String format, CommonFilter commonFilter)throws Exception;
	public abstract KznTlMst updateKaizenStatus(String status,String keyid, String nextLevel, String type, String value, String approvallevel, String mpvalue, String verifyamount)throws Exception;
	public List<String[]> getKaizenEmployeeWiseMonthWise(CommonFilter commonFilter) throws Exception ;
	public Workbook getMonthEmployeeWiseKaizenExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String rptFormat) throws Exception ;	
	public List<String[]> getKaizenEmployeeWiseMonthWiseTotal(CommonFilter commonFilter)throws Exception;
	public List<String[]> getKznDateUpdateData(CommonFilter commonFilter)	throws Exception;
	public void updateKaizenDate(String kaizenId, String kznRespid) throws Exception;
	public Workbook getKaizenDateUpdateExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getKaizenHoriaonDeploy(CommonFilter commonFilter) throws Exception;
	public KznTlMst updateKznRejRewStatus(KznTlMst kznTlMst)throws Exception;
	public List<String[]> getEmployeeWiseMonthWiseKzn(CommonFilter commonFilter) throws Exception ;
	public Workbook getEmployeeWiseMonthKaizenExcel(CommonFilter commonFilter,
				JSONObject tblJSONObj, String rptFormat) throws Exception ;
	public Workbook getMonthEmployeeWiseTotalKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;
	public abstract List<String[]> FillCategoryData(String keyid) throws Exception;
	public List<String[]> getKaizenThemeGridData(CommonFilter commonFilter) throws Exception;
	public List<KznTlMst> updateTheme(List<KznTlMst> KaizenThemeList)throws Exception;
	public Workbook getKaizenThemeUpdateExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception ;

	public abstract String getkaizenBenefit(String kznbKeyid) throws Exception;
	public abstract String getkaizenPcdqsme(String kznbKeyid)throws Exception;
	public abstract String getThemename(String benefit)throws Exception;
	public abstract String updateCategory(String keyid, String kzbnkeyid, String kznmBenefit)throws Exception;
	//public abstract KznTlMst updateKaizenUpload(String string, String flid, String date, String thmcategory, String actPillar, String problem, String benselval, String themename) throws Exception;
	public KznTlMst updateKaizenUpload(KznTlMst newKznTlMst) throws Exception;
	public abstract KznTlMst createKaizenUpload(KznTlMst kznTlMst) throws BusinessApplicationExceptions,Exception;
	public List<String[]> getKaizenDataGrid(CommonFilter commonFilter) throws Exception;
	public List<KznTlMst> updateKaizenData(List<KznTlMst> KaizenDataList)throws Exception;
	List<String[]> getAllSimplifiedKaizenApproval(CommonFilter commonFilter)throws Exception;
    public List<GenTlWorkflowInfo> createMultipleApproval(List<GenTlWorkflowInfo> genTlWorkflowInfo) throws Exception;
	public abstract List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empid) throws NoDataFoundException, Exception;
	public List<String[]> getIndividualKaizenReport(CommonFilter commonFilter)throws Exception;
	public Workbook kaizenIndividualRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType)throws Exception;
	public abstract String getFileName(String kznKeyid) throws Exception;

	
	public void KznTlMstDaoImplJwt(String JwtToken);
}

