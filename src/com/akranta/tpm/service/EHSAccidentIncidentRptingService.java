package com.akranta.tpm.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.SheAccBean;
import com.akranta.tpm.bean.SheTlIncidentEmployeenewBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.SheTlIncidentEmployeenew;

import com.akranta.tpm.model.SheTlIncidentmstnew;

import net.sf.json.JSONObject;

public interface EHSAccidentIncidentRptingService {
	public SheTlIncidentmstnew create(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst,  SheAccBean sheAccIncBean ) throws ValidationExceptions, Exception;
	public SheTlIncidentmstnew update(SheTlIncidentmstnew newSheTlIncidentmst,SheTlIncidentmstnew oldSheTlIncidentmst,  SheAccBean sheAccIncBean)  throws Exception;
	public abstract SheTlIncidentEmployeenew delete(SheTlIncidentEmployeenew sheTlIncidentEmployee) throws Exception;
	public SheTlIncidentEmployeenew create(SheTlIncidentEmployeenew newsheTlIncidentEmployee,SheTlIncidentEmployeenew existSheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean)throws BusinessApplicationExceptions,Exception;
	public SheTlIncidentEmployeenew update(SheTlIncidentEmployeenew newsheTlIncidentEmployee,SheTlIncidentEmployeenew existSheTlIncidentEmployee,SheTlIncidentEmployeenewBean sheTlIncidentEmployeetBean)throws Exception;
	public List<String[]> getAllIncidents(CommonFilter commonFilter) throws Exception;
	public SheTlIncidentEmployeenew recallEmployee(String keyId)throws Exception;
	public List<String[]> getIncidentEmployee(SheTlIncidentEmployeenew sheTlIncidentEmployee)throws Exception;
	public SheTlIncidentmstnew select(String keyid) throws Exception;
	public List<ComboBox> getIncidentTypeComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	//public String getResponse(String modiefiedid) throws SQLException;
	public List<ComboBox> getBodyPartComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getTypeComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getInjuryComboList(CommonFilter commonFilter, ComboFilter comboFilter)throws Exception;
	public List<GenTlAllmoduleimgfile> saveImg(SheTlIncidentmstnew existSheTlIncidentmst)throws Exception;
	public SheAccBean getImage(String fileName, String filePath,SheAccBean sheAccIncBean,SheTlIncidentmstnew sheTlIncidentmst)throws Exception;
	public String singleResponsiblty(String keyid, String responsevalue, String targetdate, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public String ActionClosure(String keyid, String responsevalue, String targetdate, String reviseddate, String status, String correctiveaction, SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public String ActionVerification(String keyid, String responsevalue, String targetdate, String verifystatus,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public String saveClosure(String keyid, String responsevalue, String targetdate, String verifystatus,SheTlIncidentmstnew sheTlIncidentmstnew) throws Exception;
	public List<String[]> getActionPlankeyid(String keyid) throws NoDataFoundException, Exception;
	public List<String> getUpdateList(SheTlIncidentmstnew sheTlIncidentmst) throws BusinessApplicationExceptions, Exception;
	public SheTlIncidentmstnew delete(SheTlIncidentmstnew newSheTlIncidentmst) throws Exception;
	public Workbook AccidentReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String mode) throws IOException, SQLException, Exception;
	public List<String[]> getNearMissEmpMailIds(String glbType, String flid, String combotype) throws NoDataFoundException, Exception;
	public List<String[]> getIncidentReleatedFileManager(String momKeyId) throws Exception;
    public Workbook AccidentExcelView(String keyid, String flid, String format,String path,String imagepath,CommonFilter commonFilter)throws Exception;
    public List<String[]> getAllImageDetail(String keyId,String fileName,CommonFilter commonFilter) throws Exception;
    public List<GenTlAllmoduleimgfile> getIncidentAllImages(String fileName,String path, String keyId) throws Exception;
	public String getImageCount(String keyid) throws Exception;
	public String deleteimage(String keyid, String imagType)throws Exception;
	public String getempcount(String keyid)throws Exception;
	public void EHSAccidentRptingServiceImplJwt(String JwtToken);
}
