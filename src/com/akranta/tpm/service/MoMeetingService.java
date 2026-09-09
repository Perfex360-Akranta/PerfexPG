package com.akranta.tpm.service;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;

public interface MoMeetingService {

	public GenTlMommst select(String keyid) throws Exception;

	public GenTlMommst selectRecall(String shift, String mstDate, String flid, String type)throws Exception;
	
	public List<String[]> selectRecalling(String shift, String mstDate,String flid, String type, String pillarid)throws Exception;
	
	public List<String[]> getMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)throws Exception;

	public GenTlMommst create(GenTlMommst genTlMommst,GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)throws BusinessApplicationExceptions,Exception;
	
	public GenTlMommst update(GenTlMommst genTlMommst,GenTlMommst existGenTlMommst,MOMeetingBean momeetingBean)throws Exception;

	public List<String[]> getMomeeting(CommonFilter commonFilter)throws Exception;
	
	public List<String[]> getMomeetingRvw(CommonFilter commonFilter)throws Exception;

	public GenTlMomdtl selectdtl(String keyid) throws Exception;

	public GenTlMommst delete(GenTlMommst newGenTlMommst) throws Exception;

	public List<String[]> getMomeetingAtt(CommonFilter commonFilter,String KeyId,String location, String flid, String momdate, String shift, String recall) throws Exception;

	public GenTlMomattendance selectatt(String keyid)throws Exception ;

	public Workbook MomeetingExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format)throws Exception;
	
	public Workbook MomeetingRvwExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format)throws Exception;

	public GenTlMommst createatt(GenTlMommst newGenTlMommst,GenTlMommst existGenTlMommst)throws Exception;

	public GenTlMommst updateatt(GenTlMommst newGenTlMommst,GenTlMommst existGenTlMommst)throws Exception;

	public void DeleteMomRow(String keyid)throws Exception;

	public void DeleteATTVisitorRow(String keyid) throws Exception;

	public Workbook MomeetingAttExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws IOException, SQLException, Exception;

	public List<String[]> getAttendance(CommonFilter commonFilter, String flid) throws Exception;

	public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type, String pillarid, String recall) throws Exception;

	public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception;

	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception;

	public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception;

	public Workbook getmomAttReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;

	public GenTlMommst updateApl(String dtlKeyid, String aplKeyid)throws Exception;

	public List<ComboBox> getRoleComboComboList(ComboFilter roleComboComboFilter, String flid, String string)throws Exception;

	public List<String[]> FillMomAttGridData(String keyid)throws Exception;

	public Workbook momExcelView(String meetingType, String momKeyId, String flid, String path)throws Exception;

	public List<String[]> FillRoleDatainGrid(String keyid)throws Exception;

	public List<ComboBox> getrolebasedemployee(ComboFilter empComboFilter,
			String flid, String string)throws Exception;
	

	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception;

	public List<ComboBox> getPillarGroupcombo(String condSql, ComboFilter comboFilter)throws Exception;

	public List<String[]> getAttendancemonthwise(CommonFilter commonFilter,String flid)throws Exception;

	public List<String[]> fillagendadata(String flid, String momdate)throws Exception;

	public List<String[]> getMomReleatedFileManager(String momKeyId)throws Exception;

	public Workbook MomeetingMonthwiseExportExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format)throws Exception;
	public List<String[]> getAttendancemonthwiseCount(CommonFilter commonFilter, String flid) throws Exception;

	public Workbook getMomCountExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format)throws Exception;
	public List<String[]> getMomAttendanceCountData(CommonFilter chartCommonFilter) throws Exception;
	public List<String[]> getNewAttendance(CommonFilter commonFilter, String flid) throws Exception;
	public Workbook getnewmomAttReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
    public String updateIsmailid(String momid,String val) throws Exception;

	public String getmailidTrigger(String mailid) throws Exception;
    public String PillarSelectedData(CommonFilter commonfilter) throws Exception;
	public List<String[]> getAttendancemonthwiseDHQ(CommonFilter commonFilter, String flid) throws Exception;
	public Workbook MomeetingMonthwiseExportExcelDHQ(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception;

	public List<String[]> getAttendanceDHQ(CommonFilter commonFilter, String flid) throws Exception;

	public Workbook getmomAttReportExcelDHQ(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception;
	
	public void MoMeetingServiceImplJwt(String JwtToken);
	public String RoleBasedFlid(String originalId);
} 
