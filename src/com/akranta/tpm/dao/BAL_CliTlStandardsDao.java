/*Author MANIKANDAN*/
package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface BAL_CliTlStandardsDao {

	public abstract BAL_CliTlStandards create(BAL_CliTlStandards cliTlStandards, String dkeyId) throws Exception;
	public abstract BAL_CliTlStandards update(BAL_CliTlStandards cliTlStandards, String dkeyId) throws Exception;
	public abstract BAL_CliTlStandards delete(BAL_CliTlStandards cliTlStandards) throws Exception;
	public abstract List<String[]> getAlljhclitcountList(List<String> paramValues);
	public abstract List<String[]> getAlljhclitmachinearea(String mchId);
	public abstract List<String[]> getAlljhclitstandards(String mchId,String jhasmId);
	public BAL_CliTlStandards jhclitformfill(String stdId);//for 4th grid
	public abstract List<String[]> getAlljhclitcountmodifyList(List<String> paramValues);   //added by mani
	public List<String[]> getAllcountmodifyList(String flagm, String machineIDview);//for getting count
	public List<Object> getmethodlist(String jhclitkeyID) throws Exception;//for method master
	/*** FOR ADDINNG IN CLIS_CALENDAR**/
	public abstract void generateCalendar(String clisKeyid,String clisStartdate, String string) throws Exception;
/*	public abstract List<String[]> getAddMachine(String machineID, String getEquipmentId);
	public abstract String geteqpGroup(String machineID);*/
	public abstract List<String[]> getAlljhclittools(String toolclisid);
	public abstract Workbook clistdrptExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws  Exception;
	
	public abstract Workbook getClitExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception ;
	List<String[]> getMultipleClitList(CommonFilter commonFilter,String filePath) throws Exception;
	
	public void deleteImage(String clisKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception;
	
	public List<GenTlAllmoduleimgfile> getClitEqpImages(String mchId, String fileDir, String imagePath) throws Exception;
	
	 public abstract void BAL_CliTlStandardsDaoImplJwt(String jwtToken);
	
}

