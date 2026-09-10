/*Author MANIKANDAN*/
package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_CliTlStandardFormBean;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;

public interface BAL_CliTlStandardsService {
	public BAL_CliTlStandards create(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards,  BAL_CliTlStandardFormBean cliTlStandardFormBean ) throws ValidationExceptions, Exception;
	public BAL_CliTlStandards update(BAL_CliTlStandards newCliTlStandards,BAL_CliTlStandards oldCliTlStandards,BAL_CliTlStandardFormBean cliTlStandardFormBean) throws ValidationExceptions,Exception ;
	public BAL_CliTlStandards delete(BAL_CliTlStandards cliTlStandards) throws Exception;
	public List<ComboBox> getClitTradeCombo(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getClitShiftCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getclitDesignationCombo(CommonFilter commonFilter,String desgId,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getclitResponsibilityCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter) throws Exception;
	public List<ComboBox> getclitpreparedbyCombo(CommonFilter commonFilter,String condSql) throws Exception;
	public List<String[]> getAlljhclitcountList(List<String> paramValues);//for 1st grid
	public List<String[]> getAlljhclitmachinearea(String mchId);//for 2nd grid
	public List<String[]> getAlljhclitstandards(String mchId,String jhasmId);//for 3rd grid
	public BAL_CliTlStandards jhclitformfill(String stdId);//for 4th grid
	public List<String[]> getAlljhclitcountmodifyList(List<String> paramValues);
	public List<String[]> getAllcountmodifyList(String flagm, String machineIDview);
//	public List<String[]> getAllcountmodifyList(List<String> paramValue);
	public List<Object> getmethodlist(String jhclitkeyID) throws Exception;//for method master
	public List<ComboBox> getclitmachineAreaCombo(CommonFilter commonFilter,ComboFilter comboFilter) throws Exception;//for machinea Area combo
	public List<ComboBox> getclitDeptmgrCombo(CommonFilter commonFilter,String condSql,ComboFilter comboFilter) throws Exception;// for department manager
	public List<ComboBox> getclitSectMgrCombo(CommonFilter commonFilter,String condSql) throws Exception;//for Section manager
	public List<ComboBox> getclitGroupleadCombo(CommonFilter commonFilter,String condSql) throws Exception;// for Group Leader
/*	public List<String[]> getAddMachine(String machineID, String getEquipmentId);
	public String geteqpGroup(String machineID);*/
	public List<String[]> getAlljhclittools(String toolclisid);
	public Workbook clistdrptExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws  Exception;
	
	public Workbook getClitExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	

	List<String[]> getMultipleClitList(CommonFilter commonFilter, String filePath) throws Exception;
	
	public void deleteImage(String clisKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception;
	
	public List<GenTlAllmoduleimgfile> getClitEqpImages(String mchId, String fileDir, String imagePath) throws Exception;
	
	
	 public void BAL_CliTlStandardsServiceImplJwt(String JwtToken);
	
	}
