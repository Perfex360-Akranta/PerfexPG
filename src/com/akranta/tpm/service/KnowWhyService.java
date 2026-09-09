package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.bean.Knowwhybean;
import com.akranta.tpm.bean.Vocchecklistbean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.QtmTlKnowwhydtl;
import com.akranta.tpm.model.QtmTlKnowwhymst;

public interface KnowWhyService {
	
	
	public List<String[]> getKnow(CommonFilter commonFilter) throws Exception;
	public List<String[]> getITCKnow(CommonFilter commonFilter) throws Exception;
	public QtmTlKnowwhymst create(QtmTlKnowwhymst qtmTlKnowwhymst,QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception;
	public QtmTlKnowwhymst update(QtmTlKnowwhymst qtmTlKnowwhymst,QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception;
	public QtmTlKnowwhymst getknwwhy(String keyId, String fileDir, String imagepath) throws Exception;
	//public List<String[]> getKnow(CommonFilter commonFilter) throws Exception;
	/*public QtmTlKnowwhydtl createknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,
			QtmTlKnowwhydtl existQtmTlKnowwhydtl, Knowwhybean knowwhybean) throws Exception, ValidationExceptions;
	public QtmTlKnowwhydtl updateknowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl,
			QtmTlKnowwhydtl existQtmTlKnowwhydtl, Knowwhybean knowwhybean) throws Exception, ValidationExceptions;*/
	public QtmTlKnowwhydtl deleteknwwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl, String dtlkeyid) throws Exception;
	public QtmTlKnowwhymst delete(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existqtmTlKnowwhymst, Knowwhybean newKnowwhybean) throws Exception;
	public Workbook getknowExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception;
	public List<String[]> getKnowrpt(CommonFilter commonFilter);
	public List<GenTlAllmoduleimgfile> getKnwwhyImage(String fileName,String path, String knwwhyId) throws Exception;
	public Workbook knowWhyExportExcel(String knowId, String format,
			String path, String imagePath, CommonFilter commonFilter) throws Exception;
	public QtmTlKnowwhymst createdtl(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception;
	public QtmTlKnowwhymst updatedtl(QtmTlKnowwhymst qtmTlKnowwhymst,
			QtmTlKnowwhymst existQtmTlKnowwhymst, Knowwhybean knowwhybean) throws Exception;
	public int getStatusCount(String keyid) throws Exception;
	public String  getkeyiddetail(String keyid)throws Exception;
	public String getapprovalkeyid(String appkeyid)throws Exception;
	//public String getknowwhycountlist(String knowwhykeyid) throws Exception;
	public List<String[]> getKnwwhycnt(CommonFilter commonFilter) throws Exception;
	public Workbook KnowWhyCountExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat) throws Exception;
	public void KnowWhyServiceImplJwt(String JwtToken);
}
