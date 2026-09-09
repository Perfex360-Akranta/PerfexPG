package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMessageboard;

public interface DashboardDao {
	
	public List<String[]> getDashboardPillars() throws Exception;
	public List<DashboardDispbean> getDashboardRptDetails(String pillarCode, String userid) throws Exception;
	public List<String[]> getMessage(String flid, String roleId)throws Exception;
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception;
	public GenTlMessageboard createMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception;
	public GenTlMessageboard updateMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception;
	public GenTlMessageboard selectMessage(String mesgkeyid) throws NoDataFoundException, SQLException, Exception;
	public GenTlMessageboard deleteMessageBoard(GenTlMessageboard newGenTlMessageboard) throws Exception;
	public List<String[]> getFillMsggrid(CommonFilter commonFilter)throws Exception;
	public AdmTlScrollmsgmst createMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst)throws Exception;
	public AdmTlScrollmsgmst updateMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst)throws Exception;
	public List<String[]> FillControlData(String keyid)throws Exception;
	public AdmTlScrollmsgmst deleteMessageBoardNew(AdmTlScrollmsgmst newadmTlScrollmsgmst)throws Exception;
	public String Functionallocn(String flid)throws Exception;
	public String FunctionallocnDMTID(String DMTId)throws Exception;
	public String FunctionallocnJHID(String Flid)throws Exception;
	public String LocationName(String Location)throws Exception;
    public String getDmtFlid(String Flid)throws Exception;
	public List<String[]> MOMReviewDetail(String flid,String QuarterFirst1,String QuarterEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> TransactionDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> EHSMAtrixDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> 	KaizenStatusDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> 	AbnormalityStatusDetail(String flid,String QuarterFirst1,String QuarterSecMonth,String QuarterEnd,String FYearStart,String FYearEnd,String date,String FromDate,String ToDate,String Finance)throws Exception;
	public Map<Integer, List<String[]>> MOMAetAdherence(String flid,String fromMonth,String toMonth,String FromDate,String ToDate,String Finance) throws Exception;
	public List<String[]> 	AetAdherence(String DmtOriginalId,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> 	PactAdherence(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> AbnormalityPendingDetail(String flid,String FYearStart,String FYearEnd,String JHkeyid,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> PActAdherencePercentage(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> AetAdherencePercentage(String DmtOriginalId,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception;
	public List<String[]> getReportFunctllocn(String flid)throws Exception;
	public List<String[]> getJHId(String flid)throws Exception;
	public List<String[]> getDMTId(String flid)throws Exception;
	
}