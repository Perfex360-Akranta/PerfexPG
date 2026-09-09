package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointdet;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;
import com.akranta.tpm.model.EntTlSkillindexassessdtl;
import com.akranta.tpm.model.EntTlSkillindexassessmst;

public interface SkillindexRpService {

	public List<String[]> getRpDetail(CommonFilter commonFilter,String mstkeyid);
	public	EntTlSkillReviewpointmst create(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst,EntTlSkillReviewpointdet newEntTlSkillReviewpointdet) throws Exception, IllegalArgumentException, IOException;
	public	EntTlSkillReviewpointmst update(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst, EntTlSkillReviewpointdet entTlReviewPpointdet) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;
	public EntTlSkillReviewpointmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;
	public void DeleteRplist(String keyid) throws BusinessApplicationExceptions, Exception;
	public EntTlSkillReviewpointmst delete(EntTlSkillReviewpointmst newEntTlSkillReviewpointmst) throws Exception;
	public List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) throws Exception;
	public EntTlSkillReviewpointmst createmst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, ValidationExceptions, Exception;
	public EntTlSkillReviewpointmst updatemst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst,EntTlSkillReviewpointmst existEntTlSkillReviewpointmst) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;
	public abstract List<String[]> getSkillAssessmentReport(CommonFilter commonFilter)throws Exception;
	//public EntTlSkillindexassessmst create(EntTlSkillindexassessmst newEntTlSkillindexassessmst,EntTlSkillindexassessmst existEntTlSkillindexassessmst)throws Exception;
	public Workbook SkillIndexExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception;
//	public EntTlSkillindexassessmst createSkillAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,
//	List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId , String newSiamKeyId ) throws Exception;
	public EntTlSkillindexassessmst createSkillAssement(List<EntTlSkillindexassessmst> lstEntTlSkillindexassessmst,
			List<EntTlSkillindexassessdtl> lstEntTlSkillindexassessdtl, String userId) throws Exception;
	public String getRecallReivewPoints(String flid, String roleId) throws Exception ;
	public List<ComboBox> getEmpTypeCombo(CommonFilter commonFilter) throws Exception;

	public List<String[]> getEmpList(CommonFilter commonfilter) throws Exception;

	public Workbook getskillIndexExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	
	
	public void SkillindexRpServiceImplJwt(String JwtToken);
	
	public List<String[]> getEmpListFunction(CommonFilter commonfilter,GridParams gridParams) throws Exception;
	
}
