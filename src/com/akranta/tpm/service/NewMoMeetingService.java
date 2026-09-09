package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.MOMeetingBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;

import net.sf.json.JSONObject;

public interface NewMoMeetingService {
	public GenTlMommst select(String keyid) throws Exception;
	public List<String[]> selectRecalling(String shift, String mstDate,String flid, String type, String pillarid)throws Exception;
	public List<String[]> getNewMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)throws Exception;
	public List<String[]> getNewMomeetingAtt(CommonFilter commonFilter,String KeyId,String location, String flid, String momdate, String shift, String recall) throws Exception;
	public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception;
	public List<String[]> getMomReleatedFileManager(String momKeyId)throws Exception;
    public String updateIsmailid(String momid,String val) throws Exception;
	public List<ComboBox> getrolebasedemployee(ComboFilter empComboFilter,
			String flid, String string)throws Exception;
	public Workbook newmomExcelView(String meetingType, String momKeyId, String flid, String path)throws Exception;
	public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type, String pillarid, String recall) throws Exception;
	public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception;
	public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors,GenTlVisitors existGenTlVisitors) throws Exception;
	public void DeleteATTVisitorRow(String keyid) throws Exception;
	public String getmailidTrigger(String mailid) throws Exception;
 	public List<ComboBox> getRoleComboComboList(ComboFilter roleComboComboFilter, String flid, String string)throws Exception;
	public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception;
	public GenTlMommst create(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)throws Exception;
	public GenTlMommst updateatt(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst)throws Exception;
	public GenTlMommst update(GenTlMommst newGenTlMommst, GenTlMommst existGenTlMommst, MOMeetingBean momeetingBean)throws Exception;
	public GenTlActionplanmst createActionPlan(GenTlActionplanmst newActionplanmst,GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst)throws Exception;
	public GenTlActionplanmst updateActionPlan(GenTlActionplanmst newActionplanmst,GenTlActionplandtl newGenTlActionplandtl,GenTlMommst newGenTlMommst,String Rowid,
			String ActionPlanId,String ActionplanDetailId)throws Exception;
	public List<String[]> getNewMomeetingList(CommonFilter commonFilter) throws Exception;
	public void DeleteNewMomRow(String keyid,String ActionPMasterId)throws Exception;
	public Workbook momExcelView(String meetingType, String momKeyId, String flid, String path)throws Exception;
	public Workbook getMomeetingExportExcel(CommonFilter commonFilter,JSONObject colmodel, String format)throws Exception;
	
	public void NewmomeetingServiceImplJwt(String JwtToken);
	
	public List<ComboBox> getPillarGroupcombo(String condSql, ComboFilter comboFilter)throws Exception;
	public String RoleBasedFlid(String originalId);

}
