package com.akranta.tpm.service.impl;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntTlBatchEmpAbsentDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.EntTlBatchEmpAbsentDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlBatchEmpAbsent;
import com.akranta.tpm.service.ENTEmpAttendanceService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class ENTEmpAttendanceServiceImpl implements ENTEmpAttendanceService{
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	private EntTlBatchEmpAbsentDao entTlBatchEmpAbsentDao;
	
	public ENTEmpAttendanceServiceImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		entTlBatchEmpAbsentDao = new EntTlBatchEmpAbsentDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public EntTlBatchEmpAbsent create(EntTlBatchEmpAbsent entTlBatchEmpAbsent)throws ValidationExceptions, Exception
	{
		validations.validate(entTlBatchEmpAbsent,"empAttendance","create");
		fillValues(entTlBatchEmpAbsent);
		return entTlBatchEmpAbsentDao.create(entTlBatchEmpAbsent);
	}
	@Override
	public List<String[]> getEmpList(CommonFilter commonFilter)throws Exception {
	
		return entTlBatchEmpAbsentDao.getEmpList(commonFilter);
	}
public List<ComboBox> getMonthCombo(String bachid, String progid  ,ComboFilter comboFilter ) throws Exception {
		
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("to_char(bsdl_schedule_date,'MON-YYYY')");
		comboFilter.setIdField("to_char(bsdl_schedule_date,'MON-YYYY')");
		
		CommonMessage.debugMsg(bachid);
		String Strconsql = " AND BSDL_ACTIVE = 'Y'" ;
		if( UIUtils.isValidKeyId(bachid)  ){
			Strconsql = Strconsql + " AND bsdl_bach_keyid = '" + bachid + "' ";
		}
		
		if( UIUtils.isValidKeyId(progid)  ){
			Strconsql = Strconsql + " AND BSDL_PROG_KEYID = '" + progid + "' ";
		}
		comboFilter.setCondSql(Strconsql);
		comboFilter.setTableName(TableNames.TBL_ENT_TL_BATCH_SCHEDULE);
		return commonFilterDao.fillComboValues(comboFilter);
	}
		
	private EntTlBatchEmpAbsent fillValues(EntTlBatchEmpAbsent entTlBatchEmpAbsent)
	{
		entTlBatchEmpAbsent.setEbeaActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		
		entTlBatchEmpAbsent.setEbeaCreatedon(dateTime);
		entTlBatchEmpAbsent.setEbeaModifiedon(dateTime);
		//entTlBatchEmpAbsent.setEbeaTempfield2("-");
		entTlBatchEmpAbsent.setEbeaTempfield3("-");
		
		if(!UIUtils.isValidKeyId(entTlBatchEmpAbsent.getEbeaReason()))
		entTlBatchEmpAbsent.setEbeaReason("{}");
		
		if(!UIUtils.isValidKeyId(entTlBatchEmpAbsent.getEbeaTakenby()))
			entTlBatchEmpAbsent.setEbeaTakenby(entTlBatchEmpAbsent.getEbeaTakenby());
		
		return entTlBatchEmpAbsent;
	}
	@Override
	public String getProgram(String batch) throws Exception {
		// TODO Auto-generated method stub
		return entTlBatchEmpAbsentDao.getProgram(batch);
	}
	@Override
	public List<String[]> getTrainingAttedSmry(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return entTlBatchEmpAbsentDao.getTrainingAttedSmry(commonFilter);
	}
	@Override
	public Workbook attendanceSmryExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlBatchEmpAbsentDao.attendanceSmryExportExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public List<String[]> getEmployeeattandancefillgriddata(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlBatchEmpAbsentDao.getEmployeeattandancefillgriddata(commonFilter);
	}
	public Workbook gettargetExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws SQLException, Exception {
		return entTlBatchEmpAbsentDao.gettargetExcel(colmodel, format, commonFilter);
	}

	@Override
	public List<String[]> getFacultyTrainingHoursReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return entTlBatchEmpAbsentDao.getFacultyTrainingHoursReport(commonFilter);
	}
	@Override
	public List<ComboBox> getProgkeyidCombo(String condsql, ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		CommonMessage.debugMsg("Inside getProgkeyidCombo ");
		comboFilter.setIdField("PROG_KEYID");
		//comboFilter.setNameField("PROG_NAME ||' - ' || to_char(bsdl_schedule_date,'MON-YYYY') ||'-'|| bach_name " );	
		comboFilter.setNameField("PROG_NAME" );
		String strcondsql =  ""; //" and BCOM_PROG_KEYID = PROG_KEYID  and  PROG_KEYID = bsdl_prog_keyid and prog_keyid not like 'TOP%' and bach_prog_keyid = prog_keyid and bach_keyid =  bsdl_bach_keyid";
		if (UIUtils.isValidKeyId(condsql))
		{
			strcondsql = strcondsql + "  and PROG_TRAR_KEYID = '"+ condsql + "'" ;	
		}
		comboFilter.setCondSql(strcondsql);
		// comboFilter.setTableName(" ENT_TL_PROGRAMMST, ENT_TL_BATCHCOMPLETION , ENT_TL_BATCH_SCHEDULE ,ent_tl_batchmst " );
		comboFilter.setTableName(" Ent_Vw_Programmst " );
		return commonFilterDao.fillComboValues(comboFilter);
	}	
	
}
