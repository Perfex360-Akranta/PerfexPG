/*Created By : Siddharth.A*/
package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.OplFormBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;

public interface OplTlMstService {
	
	public OplTlMst create(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean ) throws BusinessApplicationExceptions,ValidationExceptions,Exception;
	public OplTlMst update(OplTlMst newOplTlMst,OplTlMst oldOplTlMst,  OplFormBean oplFormBean )  throws Exception;
	public OplTlMst delete(OplTlMst oplTlMst) throws Exception;
	public OplTlLesson insertIntoLesson(OplTlLesson oplTlLesson) throws ValidationExceptions,Exception;
	public OplTlMst select(String oplKeyid) throws Exception;
	public OplTlMst recall(String sectionId)throws Exception;
	public List<ComboBox> getDocumentNoCombo(ComboFilter doccomboFilter) throws Exception;
	public List<String[]> getAllOplReport(CommonFilter commonFilter, String emppillar) throws Exception;
	public List<String[]> getFourQuadrantmatrix(CommonFilter commonFilter, String flid, String cellid, String Empid)throws Exception;
	public List<String[]> getAllStudent(String opllOplid,String cellId, String oplKeyid) throws Exception;
	public List<String[]> getAllPillarNameCodes(String oplId) throws Exception ;
	public List<String[]> getAllImprvCategory(List<String> pillarId) throws Exception;
	public List<GenTlAllmoduleimgfile> saveOplImg(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft) throws Exception;
	public OplTlMst getoplImage( String fileName, String filePath, OplTlMst oplTlMst)throws NoDataFoundException, Exception;
	public List<String[]> selectLesson(String oplloplId, String studId)throws Exception;
	public Workbook oplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;
	public OplTlMst updateDocUpdates(OplTlMst existOplTlMst, String docId, String yyId)throws Exception;
	
	public OplTlMst updateApprovedStatusLevel(String status,String keyid, String nextLevel, String type, String value, String mpvalue) throws Exception;
	public List<String[]> getdatevalidate(String keyId, String rowId)throws Exception;
	public List<String[]> getdatevalidation(String keyId)throws Exception;
	public List<String[]> getdatevalidating(String keyId, String rowId)throws Exception;
	public List<String[]> getAllFourQudrantReport(CommonFilter commonFilter)throws Exception;
	public Workbook getoplfourquadrantmatrix(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;
	public List<String[]> getAllOplEmpPillarMainGrid(CommonFilter commonFilter)throws Exception;
	public Workbook getEmPillarOPLReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter, String emppillar)throws Exception;
	public Workbook getFourQuarExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter,String imagepath)throws Exception;
	public void deleteimage(String keyid, String imagetype)throws Exception;
	public List<String[]> getFourQuadrantmatrixDataWise(CommonFilter commonFilter, String flid)throws Exception;
	public Workbook getFourQuarExcelDatewise(JSONObject colmodel,String format, CommonFilter commonFilter, String imagepath)throws Exception;
	public List<String[]> FillEmployeeDatainGrid(String keyid)throws Exception;
	public List<String[]> getIndividualOplReport(CommonFilter commonFilter, String emppillar) throws Exception;
	public Workbook IndividualoplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;

	public void OplTlMstServiceImplJwt(String string);
	
	public List<String[]> getOplUpdatedRow(String keyId) throws Exception;

}
