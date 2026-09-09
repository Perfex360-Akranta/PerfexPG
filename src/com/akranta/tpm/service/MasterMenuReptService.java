package com.akranta.tpm.service;

import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONObject;
import com.akranta.tpm.model.CommonFilter;

public interface MasterMenuReptService {
	
	public List<String[]> getMasterMenuRept(CommonFilter commonFilter) throws Exception;
	
	public Workbook MasterMenuReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	
	public abstract void MasterMenuReptServiceImplJwt(String jwtToken);
}
