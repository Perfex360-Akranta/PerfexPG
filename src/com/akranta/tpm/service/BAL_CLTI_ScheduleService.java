	package com.akranta.tpm.service;

	import java.sql.ResultSet;
import java.util.List;

	import net.sf.json.JSONObject;

	import org.apache.poi.ss.usermodel.Workbook;

	import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_JhclitCalendarBean;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.BAL_JhclitCalendarModel;

		public interface BAL_CLTI_ScheduleService {


			public List<String[]> getAlljhnfngetshiftwise(CommonFilter commonFilter) throws Exception;
		    public List<String[]> getAlljhnfnGetScheduledArray(CommonFilter commonFilter, String shiftId) throws Exception;

			public BAL_JhclitCalendarModel update(List<BAL_JhclitCalendarModel> existjhncalendar, String from_month, String user_createdBy,BAL_JhclitCalendarBean jhclitCalendarBean ) throws Exception;
			public BAL_JhclitCalendarModel delete(String flag);
			public Workbook JHCLITExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception;
			public List<String[]> getConfiglink()throws Exception;
			public Workbook getJhnCalenderExportExcel(CommonFilter commonFilter, String format, String path, String imagePath)throws Exception;
			public List<GenTlToolsimg> getCLTIImage(String tpmTempimgDir,String imagePath, String keyid)throws Exception;
			public String save(List<String> actlst, String cellId,String mchineId, String shiftId, String user_createdBy,String status,List<String> obsernlst,List<String> tagclasslist )throws Exception;
			public String getShift(ShiftBean shiftBean)throws Exception;
			public Workbook jhComplienceExportExcel(CommonFilter commonFilter,JSONObject tableModel, String format)throws Exception;
			public List<String[]> jhComplience(CommonFilter commonFilter)throws Exception;
			//public List<String[]> getEmployeeMailList(String sectionId) throws Exception;
			public List<String[]> getEmployeeMailList(String sectionId) throws Exception;
			public Workbook JhExportExcel(String refId, JSONObject colmodel, String format)throws Exception;
			public ResultSet  JhExportData(String refId)throws Exception;
			//public List<String[]> getAlljhnfnGetScheduledArray(
			//		CommonFilter commonFilter)throws Exception;


		}









