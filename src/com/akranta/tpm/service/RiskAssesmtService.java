package com.akranta.tpm.service;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.SheTlDeriskmst;
import com.akranta.tpm.model.SheTlRiskassessmentmst;

public interface RiskAssesmtService {
	public SheTlRiskassessmentmst create(SheTlRiskassessmentmst newSheTlRiskassessmentmst,
				SheTlRiskassessmentmst existGenTlRiskassessment) throws ValidationExceptions,BusinessApplicationExceptions, Exception;
	public SheTlRiskassessmentmst update(SheTlRiskassessmentmst newSheTlRiskassessmentmst,
				SheTlRiskassessmentmst existGenTlRiskassessment) throws ValidationExceptions,BusinessApplicationExceptions,Exception;
	public SheTlRiskassessmentmst delete(SheTlRiskassessmentmst newSheTlRiskassessmentmst) throws Exception;	
	public SheTlRiskassessmentmst select(String keyid) throws Exception ;		
	public Workbook RiskExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception ;	
	public List<String[]> getRiskList(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getRiskDtlsList(CommonFilter commonFilter) throws Exception;
	public List<ComboBox> getProbablityComboList(ComboFilter probFilterComboFilter)throws Exception;
	public List<ComboBox> getSeviorityComboList(ComboFilter sivFilterComboFilter)throws Exception;
	public List<ComboBox> getRiskLevelComboList(ComboFilter riskLevelFilterComboFilter)throws Exception;
	public List<ComboBox> getControlTypeComboList(ComboFilter ControlFilterComboFilter)throws Exception;
	public String getRiskLevel(String riskVal)throws Exception;
	public List<String[]> getDeRiskList(CommonFilter commonFilter)throws Exception;
	public List<String[]> getDeRiskDtlsList(CommonFilter commonFilter)throws Exception;
	public SheTlDeriskmst create(SheTlDeriskmst newSheTlDeriskmst,SheTlDeriskmst existSheTlDeriskmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public SheTlDeriskmst update(SheTlDeriskmst newSheTlDeriskmst,SheTlDeriskmst existSheTlDeriskmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public SheTlDeriskmst delete(SheTlDeriskmst newSheTlDeriskmst)throws ValidationExceptions,BusinessApplicationExceptions,Exception ;
	public SheTlDeriskmst selectDeRisk(String dramkeyid) throws Exception ;
	public List<String[]> getDeRiskDtlsGridList(CommonFilter commonFilter)throws Exception ;
	public Workbook DeRiskExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception ;
	public String getProbablityVal(String prob)throws Exception ;
	public String getSeviorityVal(String sev)throws Exception ;
}
