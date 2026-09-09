package com.akranta.tpm.dao;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.model.CommonFilter;
//import com.akranta.tpm.model.GenTlDesignfmea;
import com.akranta.tpm.model.GenTlEquipmentfmea;
//import com.akranta.tpm.model.GenTlProcessfmea;
import com.akranta.tpm.model.PlmTlDesignfmeamst;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeamst;

public interface FMEAFormatDao {	
	public List<String[]> getFMEADtlsList(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getFMEAReviewList(CommonFilter commonFilter)throws Exception ;
	public List<String[]> getFMEAMstList(CommonFilter commonFilter)throws Exception ;
	public PlmTlDesignfmeamst create(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlDesignfmeamst update(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst create(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst update(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public PlmTlProcessfmeamst create(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public PlmTlProcessfmeamst update(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public List<String[]> select(String keyId, String type)throws Exception ;
	public PlmTlProcessfmeamst delete(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public PlmTlDesignfmeamst delete(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst delete(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst newPlmTlProcessfmeamst)throws Exception ;
	public PlmTlDesignfmeamst deleteDtls(PlmTlDesignfmeamst newPlmTlDesignfmeamst)throws Exception ;
	public PlmTlEquipmentfmeamst deleteDtls(PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst)throws Exception ;
	public Workbook getFmeaListExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception ;
	public Map<Integer, List<String[]>> fmeaExcel(String keyid, CommonFilter commonFilter)throws Exception ;
	public List<String[]> getTotalVal(String severity, String occurance, String detection) throws Exception ;
	public List<String[]> getActionplanExcel(String keyid)throws Exception ;
	public String getFlid(String cellid) throws Exception;
	public abstract void FMEAFormatDaoImplJwt(String jwtToken);
	public List<String[]> getExistingFmeaByEquip(String equipId, String flid) throws Exception;
}
