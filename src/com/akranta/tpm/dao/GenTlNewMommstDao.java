package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;

import net.sf.json.JSONObject;

public interface GenTlNewMommstDao {
	public GenTlMommst select(String momKeyid) throws Exception;
	public List<String[]> selectRecalling(String shift, String mstDate,String flid, String type, String pillarid) throws Exception;
	public abstract List<String[]> getNewMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)throws Exception;
	public abstract List<String[]> getNewMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid, String momdate, String shift, String recall) throws Exception;
	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception;
	public abstract List<String[]> getMomReleatedFileManager(String momKeyId)throws Exception;
    public abstract String updateIsmail(String momid, String val) throws Exception;
    public abstract List<String[]> getfillmstdata(String momKeyId, String flid)throws Exception;
	public abstract List<String[]> getfilldetaildata(String momKeyId,String flid)throws Exception;
	public abstract List<String[]> getfillactnplndata(String momKeyId,String flid)throws Exception;
	public abstract List<String[]> getfillattdanceData(String momKeyId,String flid)throws Exception;
	public abstract List<String[]> getfillexternalData(String momKeyId,String flid)throws Exception;
	public abstract List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type, String pillarid, String recall) throws Exception;
	public abstract GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors) throws Exception;
	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors) throws Exception;
	public abstract void DeleteATTVisitorRow(String keyid) throws Exception ;
	public abstract String getmailidTrigger(String mailid) throws Exception;
	public abstract GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception;
	public abstract GenTlMommst create(GenTlMommst newGenTlMommst)throws BusinessApplicationExceptions,Exception;
	public abstract GenTlMommst update(GenTlMommst newGenTlMommst)throws BusinessApplicationExceptions,Exception;
	public abstract GenTlMommst updateatt(GenTlMommst newGenTlMommst)throws Exception;
	public abstract GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst,GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst)throws Exception;
	public abstract GenTlActionplanmst updateActionPlan(GenTlActionplanmst newActionplanmst,GenTlActionplandtl newGenTlActionplandtl,
			GenTlMommst newGenTlMommst,String Rowid,String ActionPlanId,String ActionplanDetailId)throws Exception;
	public List<String[]> getNewMomeetingList(CommonFilter commonFilter) throws Exception;
	public abstract void DeleteNewMomRow(String keyid,String ActionPMasterId)throws Exception;
	public Workbook getMomeetingExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public abstract void GenTlNewMommstDaoImplJwt(String jwtToken);
}
