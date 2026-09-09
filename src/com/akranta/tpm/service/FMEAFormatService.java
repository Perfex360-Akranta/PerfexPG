package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
//import com.akranta.tpm.bean.GenTlDesignfmeaBean;
//import com.akranta.tpm.bean.GenTlEquipmentfmeaBean;
//import com.akranta.tpm.bean.GenTlProcessfmeaBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.GenTlAchievements;
//import com.akranta.tpm.model.GenTlDesignfmea;
//import com.akranta.tpm.model.GenTlEquipmentfmea;
//import com.akranta.tpm.model.GenTlProcessfmea;
import com.akranta.tpm.model.PlmTlDesignfmeamst;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeamst;

public interface FMEAFormatService {
	
	public List<ComboBox> getSystemComboList(ComboFilter system,String keyid) throws Exception ;
	public List<ComboBox> getSubSystemComboList(ComboFilter system,String keyid) throws Exception ;
	public List<ComboBox> getOccuranceComboList(ComboFilter occurance, String keyid) throws Exception ;
	public List<ComboBox> getDetectionComboList(ComboFilter occurance, String keyid) throws Exception ;
	public List<ComboBox> getSeverityComboList(ComboFilter occurance, String keyid) throws Exception ;
	public List<ComboBox> getComponentComboList(ComboFilter occurance, String keyid) throws Exception ;
	public abstract List<String[]> getFMEADtlsList(CommonFilter commonFilter)throws Exception ;
	public abstract List<String[]> getFMEAReviewList(CommonFilter commonFilter)throws Exception ;
	public abstract List<String[]> getFMEAMstList(CommonFilter commonFilter)throws Exception ;
	public abstract PlmTlDesignfmeamst create(PlmTlDesignfmeamst newPlmTlDesignfmeamst,	PlmTlDesignfmeamst existPlmTlDesignfmeamst)throws ValidationExceptions,Exception ;
	public abstract PlmTlDesignfmeamst update(PlmTlDesignfmeamst newPlmTlDesignfmeamst, PlmTlDesignfmeamst existPlmTlDesignfmeamst)throws ValidationExceptions,Exception ;
	public abstract PlmTlEquipmentfmeamst create(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst,PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst)throws Exception ;
	public abstract PlmTlEquipmentfmeamst update(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst,PlmTlEquipmentfmeamst existPlmTlEquipmentfmeamst)throws Exception ;
	public abstract PlmTlProcessfmeamst create(PlmTlProcessfmeamst newPlmTlProcessfmeamst,PlmTlProcessfmeamst existPlmTlProcessfmeamst)throws Exception ;
	public abstract PlmTlProcessfmeamst update(PlmTlProcessfmeamst newPlmTlProcessfmeamst,PlmTlProcessfmeamst existPlmTlProcessfmeamst)throws Exception ;
	public List<String[]> select(String keyId, String type)throws Exception ;
	public PlmTlProcessfmeamst delete(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public PlmTlDesignfmeamst delete(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst delete(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public PlmTlDesignfmeamst deleteDtls(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst deleteDtls(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public Workbook getFmeaListExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception ;
	public Workbook getFmeaExcel(String keyid, String format,String path,CommonFilter commonFilter)throws Exception ;
	public List<String[]> getTotalVal(String severity, String occurance, String detection) throws Exception ;
	public List<ComboBox> getSubEqupmentComboList(ComboFilter subequipment, String flid, String cellid)throws Exception ;
	public String getflid(String cellid) throws Exception;
	public void FMEAFormatServiceImplJwt(String JwtToken);
	public List<String[]> getExistingFmeaByEquip(String equipId, String flid) throws Exception;
}
