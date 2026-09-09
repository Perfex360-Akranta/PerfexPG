package com.akranta.tpm.service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;
import org.xml.sax.SAXException;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.model.AdmTlDashboadUserrights;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMessageboard;

public interface DashboardService {
	public List<String[]> getDhashboardPillars() throws Exception;
	public List<DashboardDispbean> getDashboardRptDetails(String pillarCode, String userid) throws Exception;
	public List<String[]> getDBUserRights() throws Exception;

	public List<AdmTlDashboadUserrights> createDBUserRights(List<AdmTlDashboadUserrights> newDBUserRightsList,List<AdmTlDashboadUserrights> existAdmTlDashboadUserrights, String ccno) throws Exception;
	public Workbook getDBUserRightsExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)throws Exception;
	public List<String[]> getMessage(String flid, String roleId)throws Exception;
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception;
	public GenTlMessageboard createMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;
	public GenTlMessageboard updateMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws ValidationExceptions, SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, ParserConfigurationException, SAXException, IOException, Exception;
	public GenTlMessageboard selectMessage(String mesgkeyid) throws NoDataFoundException, SQLException, Exception;
	public GenTlMessageboard deleteMessageBoard(GenTlMessageboard newGenTlMessageboard) throws Exception;
	public List<String[]> getFillMsggrid(CommonFilter commonFilter)throws Exception;
	public AdmTlScrollmsgmst createMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst)throws Exception;
	public AdmTlScrollmsgmst updateMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst) throws Exception;
	public List<String[]> FillControlData(String keyid)throws Exception;
	public AdmTlScrollmsgmst deleteMessageBoardNew(AdmTlScrollmsgmst newadmTlScrollmsgmst)throws Exception;
	public String Functionallocn(String Flid)throws Exception;
	public String FunctionallocnJHID(String Flid)throws Exception;
	public String FunctionallocnDMTID(String DMTId)throws Exception;
	public String LocationName(String Location)throws Exception;
	public String getDmtFlid(String Flid)throws Exception;
	public Workbook NewDashboardExcelView(String flid,String fromMonth,String toMonth,String FirstMonth,String FYearStart,String FYearEnd,String QuarterFirst1,String QuarterSecMonth,
String QuarterEnd,String Location,String Dept,String DMTname,String DmtOriginalId,String date,String format, String path,String JHkeyid,String FromDate,String ToDate,String StartMonth,String EndMonth,String Finance) throws IOException, SQLException, Exception;	
}
