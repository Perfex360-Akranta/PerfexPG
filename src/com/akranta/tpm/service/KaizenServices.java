package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlMst;


public interface KaizenServices {
	
	public KznTlMst create(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel ) throws BusinessApplicationExceptions,ValidationExceptions, Exception;
	public KznTlMst update(KznTlMst newKznTlMst,KznTlMst oldKznTlMst,  KaizenFormBean kaizenFormBean, String apprvallevel )  throws Exception;
	public KznTlMst delete(KznTlMst kznTlMst) throws Exception;
	public KznTlMst deleteKzn(KznTlMst newKznTlMst) throws Exception;
	public List<ComboBox> getImprovementNoCombo(String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getWhyWhyCombo(String string,ComboFilter comboFilter)throws Exception;
	public KznTlMst select(String kznKeyid)throws Exception ;
	public List<String[]> getAllKaizenReport(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getAllKaizenDeleteRpt(CommonFilter commonFilter)throws Exception;
	public List<GenTlAllmoduleimgfile> saveKznImg(KznTlMst existKznTlMst, KaizenFormBean kaizenFormBean) throws Exception;
	public KznTlMst getkznImage(String fileName, String filePath,KznTlMst kznTlMst)throws NoDataFoundException, Exception;
	public List<String[]> getAllPillarLink(String kznId)throws Exception;
	public List<String[]> getAllImprvCategory(List<String> pillarId)throws Exception;
	public List<String[]> getMultiSelectLoss()throws Exception;
	public List<String[]> getkznHDScanTbl(String kaizenId, CommonFilter commonFilter)throws Exception;
	public List<String[]> getkznWhyWhyData(String wwmsKeyid)throws Exception;
	public List<String[]> getKaizenCompletedDtls(CommonFilter commonFilter)throws Exception;
	public KznTlMst deleteKznHd(String khdmkeyIds)throws Exception;
	public List<String[]> getKaizenHDView(CommonFilter commonFilter)throws Exception;
	//public KznTlMst updateKznComplete(KznTlMst newKznTlMst)throws Exception;
	public List<String[]> updateKznComplete(String kznmKeyid, String khdmKeyid, String remarks,	String completedBy, String completedDate)throws Exception;
	public List<String[]> getAllGraphData(String kznKeyid, String fromMonth, String toMonth)throws Exception;
	public List<String[]> getKznHdCellMch(String kznKeyid)throws Exception;
	public KznTlMst updateKznCompletion(KznTlMst kznTlMst)throws Exception;
	public List<String[]> getResultData(String kznKeyid)throws Exception;
	public KznTlHdmst updateKznHDCompletion(KznTlHdmst kznTlHdmst)throws Exception;
	public List<String[]> chkForDuplicates(KznTlMst newKznTlMst)throws Exception;
	public List<String[]> fillkznComplete(String kznKeyid)throws Exception;
	public KznTlMst updateDocUpdates(KznTlMst kznTlMst, String kznmKeyid, String docId)throws Exception;
	public Workbook kaizenRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType)throws Exception;
	
	public Workbook getEmPillarReportExcel(JSONObject colmodel, 
			String format,CommonFilter commonFilter)throws Exception;

	public Workbook kaizenCompleteRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType, boolean isKznHd)throws Exception;
	public List<GenTlAllmoduleimgfile> getKaizenImage(String fileName,String path, String kaizId) throws Exception;
	public List<String[]> getAllGraphMnths(String kznKeyid)throws Exception;
	
	//added for kaizen approval
	List<String[]> getAllKaizenApproval(CommonFilter commonFilter)throws Exception;
	List<String[]> getAllBenifitReport(String type)throws Exception;
	List<String[]> getAllPiller()throws Exception;
	public List<String[]> getAllAuthorization(String type) throws Exception;
	public List<String[]> getBTSGridData(CommonFilter commonFilter) throws Exception;
	public String selectKznb(String kznbKeyid) throws NoDataFoundException, SQLException, Exception;
	public String getkaizenTheme(String kznbKeyid) throws Exception;
	public List<ComboBox> getKpicombo(ComboFilter comboFilter, String flid)throws Exception;
	public List<ComboBox> getKznNoName(ComboFilter comboFilter)throws Exception;
	public List<String[]> FillControlData(String keyid)throws Exception;
	public List<String[]> FillTeamControlData(String keyid)throws Exception;
	public Workbook kaizenApprovalExportExcel(CommonFilter commonFilter,
			                                                 JSONObject colmodel, String format)throws Exception;
	public KznTlMst updateKaizenStatus(String status,String keyid, String nextLevel, String type, String value, String approvallevel, String mpvalue, String verifyamount)throws Exception;
	
	public List<String[]> getKaizenEmployeeWiseMonthWise(CommonFilter commonFilter) throws Exception;
	public Workbook getMonthEmployeeWiseKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception ;
    public List<String[]> getKaizenEmployeeWiseMonthWiseTotal(CommonFilter commonFilter)throws Exception;
	public List<String[]> getKznDateUpdateData(CommonFilter commonFilter)	throws Exception;
	public void updateKaizenDate(String kaizenId, String kznRespid)	throws Exception;
	public Workbook getKaizenDateUpdateExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception ;
	public List<String[]> getAllHorizontalDeploy(CommonFilter commonFilter) throws Exception;
	public KznTlMst updateKznRejRewStatus(KznTlMst kznTlMst)throws Exception;
	public List<String[]> getEmployeeWiseMonthWiseKzn(CommonFilter commonFilter) throws Exception;
	public Workbook getEmployeeWiseMonthKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;
	public Workbook getMonthEmployeeWiseTotalKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format) throws Exception;
	public List<ComboBox> getKznThemeCategory(ComboFilter comboFilter,CommonFilter commonFilter)throws Exception;
	public List<String[]> FillCategoryData(String keyid)throws Exception;
	public List<String[]> getKaizenThemeGridData(CommonFilter commonFilter) throws Exception;
	public List<KznTlMst> updateTheme(List<KznTlMst> KaizenThemeList)throws Exception;
	public Workbook getKaizenThemeUpdateExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception ;
	public String getkaizenBenefit(String kznbKeyid) throws Exception;
	public String getkaizenPcdqsme(String kznbKeyid)throws Exception;
	public String getThemename(String benefit)throws Exception;
	public String updateCategory(String keyid, String kzbnkeyid, String kznmBenefit) throws Exception;
	public List<String[]> getKaizenDataGrid(CommonFilter commonFilter) throws Exception;
	public List<KznTlMst> updateKaizenData(List<KznTlMst> KaizenDataList)throws Exception;
	List<String[]> getAllSimplifiedKaizenApproval(CommonFilter commonFilter)throws Exception;
    public List<GenTlWorkflowInfo> createMultipleApproval(List<GenTlWorkflowInfo> genTlWorkflowInfo) throws Exception;
    public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws NoDataFoundException, Exception;
	public List<String[]> getIndividualKaizenReport(CommonFilter commonFilter)throws Exception;
	public Workbook kaizenIndividualRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType)throws Exception;
	public String getFileName(String kznKeyid)throws Exception;
	  public void KaizenFormServiceImplJwt(String JwtToken);


}
