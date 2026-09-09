package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.NearMissDao;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.NearMissDaoImpl;
import com.akranta.tpm.exportreport.NEARMISSREPORTExcelTemplate;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlNearmissreportmst;
import com.akranta.tpm.service.NearMissService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
//import com.sun.corba.se.impl.orbutil.closure.Constant;

public class NearMissServiceImpl implements NearMissService {
	private NearMissDao nearMissDao;
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	
	public NearMissServiceImpl(DBActionTemplate dbActionTemplate){
		 
		// TODO Auto-generated constructor stub
		nearMissDao = new NearMissDaoImpl(dbActionTemplate);
		validations = new Validations();
		this.dbActionTemplate  =dbActionTemplate ;
	}
	
	@Override
	public List<String[]> getAllNear(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getAllNear(commonFilter);
	}
	@Override
	public List<String[]> getNear(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNear(masterKeyid);
	}
	@Override
	public List<String[]> getNearMiss(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearMiss(masterKeyid);
	}

	@Override
	public Workbook getNearMissExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearMissExportToExcel(colmodel,format,commonFilter1);
	}

	@Override
	public GenTlNearmissreportmst create(GenTlNearmissreportmst genTlNearmissreportmst,GenTlNearmissreportmst existGenTlNearmissreportmst)throws Exception {
		CommonMessage.debugMsg("Inside Service Impl");
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "create";
		String xml="Nearmiss";
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xml);
		if("C".equals(genTlNearmissreportmst.getNmrtStatus()))
			validationsFor = "complete";
		validations.validate(genTlNearmissreportmst,xml,validationsFor);
		
        
		fillvalues(genTlNearmissreportmst,existGenTlNearmissreportmst);
		return nearMissDao.create(genTlNearmissreportmst);
		
	}

	

	@Override
	public GenTlNearmissreportmst update(GenTlNearmissreportmst genTlNearmissreportmst,GenTlNearmissreportmst existGenTlNearmissreportmst)throws Exception {
		CommonMessage.debugMsg("Inside Service Impl");
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "update";
		String xml="Nearmiss";
		
		if("C".equals(genTlNearmissreportmst.getNmrtStatus()))
			validationsFor = "complete";
		
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xml);
		CommonMessage.debugMsg("validationsFor:::::::::"+validationsFor);
		CommonMessage.debugMsg("status:::::::::"+genTlNearmissreportmst.getNmrtStatus());
		
		validations.validate(genTlNearmissreportmst,xml,validationsFor);
		CommonMessage.debugMsg("fill vales....");
		fillvalues(genTlNearmissreportmst,existGenTlNearmissreportmst);
		return nearMissDao.update(genTlNearmissreportmst);
	}

	@Override
	public GenTlNearmissreportmst getGridData(String keyId) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		
		return nearMissDao.getGridDdatas(keyId);
	}

	private void fillvalues(GenTlNearmissreportmst genTlNearmissreportmst,GenTlNearmissreportmst existGenTlNearmissreportmst) {
				String dateTime = CommonFunctions.dateTimeNow();
		genTlNearmissreportmst.setNmrtCreatedon(dateTime);
		genTlNearmissreportmst.setNmrtModifiedon(dateTime);
		if(genTlNearmissreportmst.getNmrtActionrecommended() == null)
			genTlNearmissreportmst.setNmrtActionrecommended("{}");
		
		if(genTlNearmissreportmst.getNmrtApprovedby() == null)
			genTlNearmissreportmst.setNmrtApprovedby("-");

		if(genTlNearmissreportmst.getNmrtTempfield1() == null)
			genTlNearmissreportmst.setNmrtTempfield1("-");
		if(genTlNearmissreportmst.getNmrtTempfield2() == null)
			genTlNearmissreportmst.setNmrtTempfield2("-");
		if(genTlNearmissreportmst.getNmrtTempfield3() == null)
			genTlNearmissreportmst.setNmrtTempfield3("-");
		
		if(genTlNearmissreportmst.getNmrtActive() == null)
			genTlNearmissreportmst.setNmrtActive("Y");
		if(genTlNearmissreportmst.getNmrtDeptid() == null)
			genTlNearmissreportmst.setNmrtDeptid("{}");
		if(genTlNearmissreportmst.getNmrtDescnearmiss() == null)
			genTlNearmissreportmst.setNmrtDescnearmiss("{}");
		if(genTlNearmissreportmst.getNmrtEmployeeid() == null)
			genTlNearmissreportmst.setNmrtEmployeeid("{}");
		if(genTlNearmissreportmst.getNmrtIdentifiedby() == null)
			genTlNearmissreportmst.setNmrtIdentifiedby("{}");
		if(genTlNearmissreportmst.getNmrtOccurrencedatetime() == null)
			genTlNearmissreportmst.setNmrtOccurrencedatetime(dateTime);
		if(genTlNearmissreportmst.getNmrtProbablerecrate() ==  null)
			genTlNearmissreportmst.setNmrtProbablerecrate("{}");
		if(genTlNearmissreportmst.getNmrtResponsibility() ==  null)
			genTlNearmissreportmst.setNmrtResponsibility("{}");
		if(genTlNearmissreportmst.getNmrtSeveritypotentialid() ==  null)
			genTlNearmissreportmst.setNmrtSeveritypotentialid("{}");
		if(genTlNearmissreportmst.getNmrtTargetdate()==null)
			genTlNearmissreportmst.setNmrtTargetdate(dateTime);
		if(genTlNearmissreportmst.getNmrtRemarks() == null)
			genTlNearmissreportmst.setNmrtRemarks("-");
		
		if(genTlNearmissreportmst.getNmrtStatus() == null)
			genTlNearmissreportmst.setNmrtStatus("P");
		
		if(genTlNearmissreportmst.getNmrtCompletedby() == null)
			genTlNearmissreportmst.setNmrtCompletedby("{}");
		
		if(genTlNearmissreportmst.getNmrtCompleteddate() == null)
			genTlNearmissreportmst.setNmrtCompleteddate(Constants.passNullDate);
		
		
		if(genTlNearmissreportmst.getNmrtCreatedby() == null)
			genTlNearmissreportmst.setNmrtCreatedby("{}");
		
		if(genTlNearmissreportmst.getNmrtOthers() == null)
			genTlNearmissreportmst.setNmrtOthers("{}");
		
		if(genTlNearmissreportmst.getNmrtOthersuc() == null)
			genTlNearmissreportmst.setNmrtOthersuc("{}");
		if(genTlNearmissreportmst.getNmrtPreparedDatetime() == null)
			genTlNearmissreportmst.setNmrtPreparedDatetime(dateTime);
		if(genTlNearmissreportmst.getNmrtInvestigation() == null)
			genTlNearmissreportmst.setNmrtInvestigation("-");
		if(genTlNearmissreportmst.getNmrtFlnid()==null)
			genTlNearmissreportmst.setNmrtFlnid("{}");
		
	}

	@Override
	public List<String[]> getSingleNearMissDetail(String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getSingleNearMissDetail(keyId);
	}

	@Override
	public GenTlNearmissreportmst delete(GenTlNearmissreportmst oldGenTlNearmissreportmst)throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.delete(oldGenTlNearmissreportmst);
	}

	@Override
	public List<String[]> getAllNeargrd() throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getAllNeargrd();
	}

	@Override
	public List<String[]> getNearmissmonthrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearmissmonthrpt(commonFilter);
	}

	@Override
	public Workbook getNearMissMonthExportToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearMissMonthExportToExcel(colmodel,format,commonFilter1);
	}

	@Override
	public List<String[]> actnplnstatus(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.actnplnstatus(keyid);
	}

	@Override
	public Workbook nearmissExcelView(String keyid, String flid, String path)
			throws Exception {
		// TODO Auto-generated method stub

		List<String[]> nearmissData = nearMissDao.getfillnearmissdata(keyid,flid);
		List<String[]> unsafeactData = nearMissDao.getfillunsafeactdata(keyid,flid);
		List<String[]> unsafeconditionData = nearMissDao.getfillunsafeconditiondata(keyid,flid);
		
		Workbook wb=(new NEARMISSREPORTExcelTemplate(dbActionTemplate)).fillValues(nearmissData,unsafeactData,unsafeconditionData,path);
		
		return wb;
	}
	//@Override
	/*public List<String[]> getNearmisscountrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearmisscountrpt(commonFilter);
	}
	@Override
	public Workbook getNearMissCountToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearMissCountToExcel(colmodel,format,commonFilter1);
	}
	@Override
	public List<String[]> getNearmissCountRptgraph(CommonFilter commonFilter,String rowId)
	throws Exception {
	// TODO Auto-generated method stub
	return this.nearMissDao.getNearmissCountRptgraph(commonFilter,rowId);
}
	@Override
	public List<String[]> getNearmissTrendcountrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearmissTrendcountrpt(commonFilter);
	}

	@Override
	public List<String[]> getNearmissTrendcountrptgraph(CommonFilter chartCommonFilter, String rowId) throws Exception {
		return this.nearMissDao.getNearmissTrendcountrptgraph(chartCommonFilter,rowId);
	}	*/

	@Override
	public List<String[]> getNearmisscountrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearmisscountrpt(commonFilter);
	}

	@Override
	public List<String[]> getNearmissCountRptgraph(CommonFilter chrtCommonFilter, String rowId) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearmissCountRptgraph(chrtCommonFilter,rowId);
	}

	@Override
	public Workbook getNearMissCountToExcel(JSONObject colmodel, String format, CommonFilter commonFilter1)
			throws IOException, SQLException, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearMissCountToExcel(colmodel, format, commonFilter1);
	}
	@Override
	public List<String[]> getNearmissTrendcountrpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.nearMissDao.getNearmissTrendcountrpt(commonFilter);
	}

	@Override
	public List<String[]> getNearmissTrendcountrptgraph(CommonFilter chartCommonFilter, String rowId) throws Exception {
		return this.nearMissDao.getNearmissTrendcountrptgraph(chartCommonFilter,rowId);
	}
	
	public Workbook getNearMissTrendToExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter1) throws IOException, SQLException, Exception{
		  return this.nearMissDao.getNearMissTrendToExcel(colmodel, format, commonFilter1);
	}
}
