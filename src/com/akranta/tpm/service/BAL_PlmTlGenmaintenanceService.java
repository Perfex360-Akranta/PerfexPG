package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.bean.BAL_GeneralMaintainanceBean;
import com.akranta.tpm.model.BAL_BdmTlSetupadjsplit;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;

public interface BAL_PlmTlGenmaintenanceService {

	List<ComboBox> getGmntTradeCombo(String condSql, ComboFilter comboFilter) throws Exception;

	List<ComboBox> getGmntcompletedbyCombo(String condSql, ComboFilter comboFilter) throws Exception;
	
	List<ComboBox> getGmntreptdbyidCombo(String condSql, ComboFilter comboFilter) throws Exception;
	
	List<ComboBox> getGmntshiftCombo(String condSql, ComboFilter comboFilter) throws Exception;
	List<ComboBox> getSubLossshiftCombo(String fromDate,String toDate,String factId, ComboFilter comboFilter) throws Exception;
	
	List<ComboBox> getSubLossCombo(String condSql, ComboFilter comboFilter) throws Exception;
	List<ComboBox> getGmntmouldCombo(String condSql, ComboFilter comboFilter) throws Exception;

	BAL_PlmTlGenmaintenance create(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance,BAL_PlmTlGenmaintenance existPlmTlGenmaintenance,BAL_GeneralMaintainanceBean generalMaintainanceBean) throws SecurityException, IllegalArgumentException, ParserConfigurationException, SAXException, IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, Exception;

	BAL_PlmTlGenmaintenance update(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance,BAL_PlmTlGenmaintenance existPlmTlGenmaintenance,BAL_GeneralMaintainanceBean generalMaintainanceBean,WomTlWomst womTlWomst) throws Exception;

	BAL_PlmTlGenmaintenance delete(BAL_PlmTlGenmaintenance newPlmTlGenmaintenance) throws Exception;
	
	BAL_BdmTlSetupadjsplit deleteSubLoss(BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit) throws Exception;

	List<String[]> getdataGenMain(CommonFilter commonFilter, String relto, String setupAdj);
	List<String[]> getSetupAdjSubLoss(String refDocId);

	BAL_PlmTlGenmaintenance getFillValue(String docno) throws Exception;

	String getShift(List<String> paramValues);
	
	public Workbook genMaintainanaceExportExcel(CommonFilter commonFilter,	JSONObject tblJSONObj, String format,String relto) throws Exception;

	public void BAL_PlmTlGenmaintenanceServiceImplJwt(String JwtToken); 
		
		

	

}
