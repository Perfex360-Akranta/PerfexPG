package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;
import com.akranta.tpm.model.SheTlIncidentmstnew;

import net.sf.json.JSONObject;

public interface SheTlIncidentmstnewDao {
	
	public abstract SheTlIncidentmstnew create(SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public abstract SheTlIncidentmstnew update(SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public abstract SheTlIncidentmstnew delete(SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> saveOplImg(SheTlIncidentmstnew sheTlIncidentmst)throws Exception;
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception;
	public abstract SheTlIncidentEmployeenew recallEmployee(String keyId)throws Exception;
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployeenew sheTlIncidentEmployee)throws Exception;
	public SheTlIncidentmstnew select(String keyid) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> getImage(List<GenTlAllmoduleimgfile> insImgList) throws Exception;
	public abstract String AssignResponse(String keyid, String responsevalue, String targetdate,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;;
	public abstract String AssignActionClosure(String keyid, String responsevalue, String targetdate, String reviseddate, String status, String correctiveaction, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public abstract String ActionVerification(String keyid, String responsevalue, String targetdate,String verifystatus, SheTlIncidentmstnew sheTlIncidentmstnew)throws Exception;
	public abstract String saveClosure(String keyid, String responsevalue, String targetdate, String verifystatus,SheTlIncidentmstnew sheTlIncidentmstnew);
	public abstract Workbook AccidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String mode) throws IOException, SQLException, Exception;
	public abstract List<String[]> getNearMissEmpMailIds(String glbType, String flid, String combotype) throws NoDataFoundException, Exception;
	public abstract List<String[]> getIncidentReleatedFileManager(String momKeyId) throws Exception;
	//public List<String[]> getfillaccidentEmpData(String keyid,String flid)throws Exception;
    public List<String[]> getfillaccidentdata(String keyid, String flid)throws Exception;
	public List<String[]> getfillaccidentEmpData(String keyid)throws Exception;
	public abstract List<String[]> getAllImageDetail(String keyId,
			String fileName, CommonFilter commonFilter) throws Exception;
	public abstract List<GenTlAllmoduleimgfile> getIncidentImages(List<GenTlAllmoduleimgfile> incidentImgList) throws NoDataFoundException, Exception;
	public List<String[]> getfillaccidentResData(String keyid)throws Exception;
	public abstract void SheTlIncidentmstnewDaoImplJwt(String jwtToken);
}

