package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlSkillReviewpointdet;
import com.akranta.tpm.model.EntTlSkillReviewpointmst;

public interface EntTlSkillReviewpointmstDao {

	public abstract EntTlSkillReviewpointmst create(EntTlSkillReviewpointmst newentTlSkillReviewpointmst, EntTlSkillReviewpointdet entTlReviewPpointdet) throws Exception;
	public abstract EntTlSkillReviewpointmst update(EntTlSkillReviewpointmst entTlSkillReviewpointmst, EntTlSkillReviewpointdet newEntTlSkillReviewpointdet) throws Exception;
	public abstract EntTlSkillReviewpointmst delete(EntTlSkillReviewpointmst entTlSkillReviewpointmst) throws Exception;
	public abstract List<String[]> getRpDetail(CommonFilter commonFilter,String mstkeyid);
	public abstract EntTlSkillReviewpointmst select(String mstkeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract void DeleteRplist(String keyid) throws BusinessApplicationExceptions, Exception;
	public abstract List<String[]> getSIMainGrid(CommonFilter commonFilter, String reportName) throws Exception;
	public abstract EntTlSkillReviewpointmst createmst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst) throws Exception;
	public abstract EntTlSkillReviewpointmst updatemst(EntTlSkillReviewpointmst newentTlSkillReviewpointmst) throws Exception;
	public abstract List<String[]> getSkillAssessmentReport(CommonFilter commonFilter)throws Exception;
	public abstract Workbook SkillIndexExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws SQLException, Exception;
	public String getRecallReivewPoints(String flid, String roleId) throws Exception ;
	public abstract Workbook getskillIndexExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format) throws Exception;
	public void EntTlSkillReviewpointmstDaoImplJwt(String JwtToken); 
}

