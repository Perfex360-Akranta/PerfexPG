/*Created By : Siddharth.A*/
package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;


public interface OplTlMstDao 
{
	public abstract OplTlMst create(OplTlMst oplTlMst, BdmTlYycountermeasurelink bdmTlYycountermeasurelink, GenTlDocupdates genTlDocupdates) throws BusinessApplicationExceptions,Exception;
	public abstract OplTlMst update(OplTlMst oplTlMst ,BdmTlYycountermeasurelink bdmTlYycountermeasurelink, GenTlDocupdates genTlDocupdates) throws Exception;
	public abstract OplTlMst delete(OplTlMst oplTlMst) throws Exception;
	public abstract OplTlMst select(String oplKeyid) throws Exception;
	public abstract OplTlLesson insertIntoLesson(OplTlLesson oplTlLesson) throws Exception;
	public List<String[]> getOplReportDao(CommonFilter commonFilter, String emppillar)throws Exception;
	public abstract List<String[]> getFourQuadrantmatrix(CommonFilter commonFilter, String flid, String cellid, String Empid)throws Exception;
	public List<String[]> getStudents(String opllOplid,String cellId, String oplKeyid) throws Exception;
	public List<String[]> getPillarNameCodes(String oplId) throws Exception;
	public List<String[]> getImprvCategory(List<String> pillarId) throws Exception;
	public List<GenTlAllmoduleimgfile> saveOplImg(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft) throws Exception;
	public List<GenTlAllmoduleimgfile> getOplImage(List<GenTlAllmoduleimgfile> oplImgList)throws NoDataFoundException, Exception;
	public List<String[]> selectLesson(String oplloplId, String studId);
	public Workbook oplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;
	public OplTlMst updateDocUpdates(OplTlMst oplTlMst, String docId, String yyId)throws Exception;
	
	public OplTlMst updateApprovedStatusLevel(String status,String keyid, String nextLevel, String type, String value, String mpValue) throws Exception ;
	
	public abstract List<String[]> getdatevalidate(String keyId, String rowId)throws Exception;
	public abstract List<String[]> getdatevalidating(String keyId, String rowId)throws Exception;
	public abstract List<String[]> getdatevalidation(String keyId)throws Exception;
	public abstract List<String[]> getAllFourQudrantReport(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getoplfourquadrantmatrix(JSONObject colmodel,String format, CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getAllOplEmpPillarMainGrid(CommonFilter commonFilter)throws Exception;
	public abstract Workbook getEmPillarOPLReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter, String emppillar)throws Exception;
	public abstract Workbook getFourQuarExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter,String imagepath)throws Exception;
	public abstract void deleteimage(String keyid, String imagetype)throws Exception;
	public abstract List<String[]> getFourQuadrantmatrixDataWise(CommonFilter commonFilter, String flid)throws Exception;
	public abstract Workbook getFourQuarExcelDatewise(JSONObject colmodel,String format, CommonFilter commonFilter, String imagepath)throws Exception;
	public abstract List<String[]> FillEmployeeDatainGrid(String keyid)throws Exception;
	public abstract OplTlMst createOPlUpload(OplTlMst oplTlMst) throws BusinessApplicationExceptions,Exception;
	public abstract OplTlMst updateOPlUpload(OplTlMst oplTlMst) throws BusinessApplicationExceptions,Exception;
	public List<String[]> getIndividualOplReport(CommonFilter commonFilter, String emppillar) throws Exception;
	public Workbook IndividualoplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String reportType)throws Exception;
	
	public abstract void OplTlMstDaoImplJwt(String jwtToken);

	public List<String[]> getOplUpdatedRow(String keyId) throws Exception;
    
	
}

