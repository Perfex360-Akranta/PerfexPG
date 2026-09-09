package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;

public interface GenTlMommstDao 
{

	public abstract GenTlMommst create(GenTlMommst genTlMommst) throws BusinessApplicationExceptions,Exception;
	
	public abstract GenTlMommst update(GenTlMommst genTlMommst) throws Exception;
	public abstract GenTlMommst delete(GenTlMommst genTlMommst) throws Exception;
	public abstract GenTlMommst select(String momKeyid) throws Exception;

	public abstract GenTlMommst selectRecall(String shift, String mstDate,String flid, String type)throws Exception;
	
	public abstract List<String[]> getMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)throws Exception;
	

	public abstract List<String[]> getMomeeting(CommonFilter commonFilter)throws Exception;
	
	public abstract List<String[]> getMomeetingRvw(CommonFilter commonFilter)throws Exception;

	public abstract GenTlMomdtl selectdtl(String keyid) throws Exception;


	public abstract List<String[]> getMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid, String momdate, String shift, String recall) throws Exception;

	public abstract GenTlMomattendance selectatt(String keyid)throws Exception;

	public Workbook MomeetingExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public Workbook MomeetingRvwExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public abstract GenTlMommst createatt(GenTlMommst newGenTlMommst) throws Exception;

	public abstract GenTlMommst updateatt(GenTlMommst newGenTlMommst)throws Exception;

	public abstract void DeleteMomRow(String keyid)throws Exception;

	public abstract void DeleteATTVisitorRow(String keyid) throws Exception ;

	public abstract Workbook MomeetingAttExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws IOException, SQLException, Exception;

	public abstract List<String[]> getAttendance(CommonFilter commonFilter, String flid) throws Exception;

	public abstract List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type, String pillarid, String recall) throws Exception;

	public abstract GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors) throws Exception;

	public abstract GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception;

	public abstract GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors) throws Exception;

	public abstract Workbook getmomAttReportExcel(CommonFilter commonFilter,JSONObject colmodel, String format)throws Exception;

	public abstract GenTlMommst updateApl(String dtlKeyid, String aplKeyid) throws Exception;

	public abstract List<String[]> FillMomAttGridData(String keyid)throws Exception;

	public abstract List<String[]> getfilldetaildata(String momKeyId,
			String flid)throws Exception;

	public abstract List<String[]> getfillactnplndata(String momKeyId,
			String flid)throws Exception;

	public abstract List<String[]> getfillmstdata(String momKeyId, String flid)throws Exception;

	public abstract List<String[]> getfillattdanceData(String momKeyId,
			String flid)throws Exception;

	public abstract List<String[]> FillRoleDatainGrid(String keyid)throws Exception;

	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception;

	public abstract List<String[]> getAttendancemonthwise(CommonFilter commonFilter, String flid)throws Exception;

	public abstract List<String[]> fillagendadata(String locationId,String momdate)throws Exception;

	public abstract List<String[]> getMomReleatedFileManager(String momKeyId)throws Exception;

	public abstract List<String[]> getfillexternalData(String momKeyId,String flid)throws Exception;

	public abstract Workbook MomeetingMonthwiseExportExcel(
			CommonFilter commonFilter, JSONObject colmodel, String format)throws Exception;

	public abstract List<String[]> selectRecalling(String shift,String mstDate, String flid, String type, String pillarid)throws Exception;
	public abstract List<String[]> getAttendancemonthwiseCount(CommonFilter commonFilter, String flid) throws Exception;

	public abstract Workbook getMomCountExcel(CommonFilter commonFilter,
			JSONObject colmodel, String format)throws Exception;
	public abstract List<String[]> getMomAttendanceCountData(
			CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getNewAttendance(CommonFilter commonFilter, String flid) throws Exception;
	public Workbook getnewmomAttReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter)throws Exception;
    public abstract String updateIsmail(String momid, String val) throws Exception;

	public abstract String getmailidTrigger(String mailid) throws Exception;

	public abstract String PillarSelectedData(CommonFilter commonfilter) throws Exception;
	public List<String[]> getAttendancemonthwiseDHQ(CommonFilter commonFilter, String flid) throws Exception;

	public Workbook MomeetingMonthwiseExportExcelDHQ(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception;

	public abstract List<String[]> getAttendanceDHQ(CommonFilter commonFilter, String flid) throws Exception;

	public abstract Workbook getmomAttReportExcelDHQ(CommonFilter commonFilter, JSONObject colmodel, String format) throws Exception;
	
	public abstract void GenTlMommstDaoImplJwt(String jwtToken);
}

