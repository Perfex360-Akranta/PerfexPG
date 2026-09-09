package com.akranta.tpm.dao;

import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;
import net.sf.json.JSONObject;
import com.akranta.tpm.model.CommonFilter;

public interface MasterMenuReptDao {
	
	public List<String[]> getMasterMenuRept(CommonFilter commonFilter) throws Exception;
	
	public Workbook getMasterMenuReportExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception;
	public abstract void MasterMenuReptDaoImplJwt(String jwtToken);
}
