package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.UpstreamDefectiveDAO;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import com.akranta.tpm.dao.impl.UpstreamDefectiveDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;
import com.akranta.tpm.service.UpstreamDefectiveServices;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.UpstreamdefectServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class UpstreamDefectiveServicesImpl implements UpstreamDefectiveServices


{
	
	private UpstreamdefectServiceApi upstreamdefectServiceApi;
	UpstreamDefectiveDAO UpstreamDefectiveDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations;
	public  UpstreamDefectiveServicesImpl (DBActionTemplate dbActionTemplate)
	{
		commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		UpstreamDefectiveDao=  new UpstreamDefectiveDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	//21/01/2026
	public void UpstreamDefectiveServicesImplJwt(String JwtToken){
    	try{
    		UpstreamDefectiveDao.UpstreamDefectiveDaoImplJwt(JwtToken);
    		upstreamdefectServiceApi = new UpstreamdefectServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }


	@Override
	public List<String[]> getUpstreamGrid(CommonFilter commonFilter,String keyid) throws Exception {
		// TODO Auto-generated method stub
		
		return this.UpstreamDefectiveDao.getUpstreamGrid(commonFilter,keyid);
	}

	@Override
	public List<String[]> getUpstreamFormGrid(CommonFilter commonFilter,String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		return this.UpstreamDefectiveDao.getUpstreamFormGrid(commonFilter,keyId);
	}
	/*
	 * @Override public List<ComboBox> getFillcombobox(CommonFilter
	 * commonFilter,ComboFilter comboFilter) throws Exception { // TODO
	 * Auto-generated method stub
	 * 
	 * CommonMessage.debugMsg(" Inside Service Impl  11 :: ");
	 * //comboFilter.setCodeField("QDPN_CODE");
	 * comboFilter.setNameField("QPHM_NAME"); comboFilter.setIdField("QPHM_KEYID");
	 * //comboFilter.setIdField("QDPN_ORIGINALID");
	 * 
	 * comboFilter.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);
	 * 
	 * return commonFilterDao.fillComboValues(comboFilter);
	 * 
	 * }
	 */
	
	//mano
		@Override
		public List<ComboBox> getFillcombobox(CommonFilter commonFilter, ComboFilter comboFilter)
		        throws Exception {

		    CommonMessage.debugMsg(" Inside Service Impl 11 :: ");

		    comboFilter.setNameField("QPHM_NAME");
		    comboFilter.setIdField("QPHM_KEYID");
		    comboFilter.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);

		    // parse defectMode and sectionId from condSql marker
		    String defectMode   = "";
		    String sectionId    = "";
		    String existingCond = comboFilter.getCondSql();

		    if (existingCond != null && existingCond.startsWith("##")) {
		        String[] parts = existingCond.split("##");
		        defectMode = parts.length > 1 ? parts[1] : "";
		        sectionId  = parts.length > 2 ? parts[2] : "";
		        // reset condSql so it doesn't pollute the actual query
		        comboFilter.setCondSql("");
		    }

		    CommonFunctions.debugMsg("=== getFillcombobox (Upstream Defect) ===");
		    CommonFunctions.debugMsg("defectMode : " + defectMode);
		    CommonFunctions.debugMsg("flid       : " + commonFilter.getFlid());
		    CommonFunctions.debugMsg("sectionId  : " + sectionId);

		    if ("OTHERS".equals(defectMode)) {
		        comboFilter.setCondSql(
		            " AND QPHM_KEYID NOT IN ( " +
		            "   SELECT PHNM_QPHM_KEYID " +
		            "   FROM QTM_TL_PHENOMENA_MAPPING " +
		            "   WHERE PHNM_ACTIVE = 'Y' " +
		            "   AND PHNM_SECT_FLID = '" + sectionId + "' " +
		            " ) "
		        );
		    } else {
		        comboFilter.setCondSql(
		            " AND QPHM_KEYID IN ( " +
		            "   SELECT PHNM_QPHM_KEYID " +
		            "   FROM QTM_TL_PHENOMENA_MAPPING " +
		            "   WHERE PHNM_ACTIVE = 'Y' " +
		            "   AND PHNM_SECT_FLID = '" + sectionId + "' " +
		            " ) "
		        );
		    }

		    return commonFilterDao.fillComboValues(comboFilter);
		}


	@Override
	public GenTlUpstreamdefect createUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,GenTlUpstreamdefect existGenTlUpstreamdefect,
			UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		fillValuesgenTlMommst(newGenTlUpstreamdefect, existGenTlUpstreamdefect,upstreamDefect);
		return UpstreamDefectiveDao.createUpstream(newGenTlUpstreamdefect,existGenTlUpstreamdefect,upstreamDefect);
	}

	@Override
	public Workbook getUpstreamDefectExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return UpstreamDefectiveDao.getUpstreamDefectExcel(colmodel,format,commonFilter);
	}


	private void fillValuesgenTlMommst(GenTlUpstreamdefect newGenTlUpstreamdefect,
			GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		
		newGenTlUpstreamdefect.setUpsdActive("Y");

		String dateTime = CommonFunctions.dateTimeNow();

		newGenTlUpstreamdefect.setUpsdCreatedon(dateTime);

		newGenTlUpstreamdefect.setUpsdModifiedon(dateTime);
		if (newGenTlUpstreamdefect.getUpsdFlid() == null)
			newGenTlUpstreamdefect.setUpsdFlid("{}");

		if (newGenTlUpstreamdefect.getUpsdElementid() == null)
			newGenTlUpstreamdefect.setUpsdElementid("{}");
		
		if (newGenTlUpstreamdefect.getUpsdArea() == null)
			newGenTlUpstreamdefect.setUpsdArea("{}");
		
		if (newGenTlUpstreamdefect.getUpsdDate() == null)
			newGenTlUpstreamdefect.setUpsdDate("{}");
		
		if (newGenTlUpstreamdefect.getUpsdInspectionlotno() == null)
			newGenTlUpstreamdefect.setUpsdInspectionlotno("{}");
		
		if (newGenTlUpstreamdefect.getUpsdInformto() == null)
			newGenTlUpstreamdefect.setUpsdInformto("{}");
		
		if (newGenTlUpstreamdefect.getUpsdInformto() == null)
			newGenTlUpstreamdefect.setUpsdInformto("{}");
		
		if (newGenTlUpstreamdefect.getUpsdRawmaterial() == null)
			newGenTlUpstreamdefect.setUpsdRawmaterial("{}");
		
		if (newGenTlUpstreamdefect.getUpsdDefect() == null)
			newGenTlUpstreamdefect.setUpsdDefect("{}");
		
		if (newGenTlUpstreamdefect.getUpsdCorrectionaction() == null)
			newGenTlUpstreamdefect.setUpsdCorrectionaction("{}");
			
        if (newGenTlUpstreamdefect.getUpsdPreventiveaction() == null)
			newGenTlUpstreamdefect.setUpsdPreventiveaction("{}");
	
        if (newGenTlUpstreamdefect.getUpsdTempfield1() == null)
			newGenTlUpstreamdefect.setUpsdTempfield1("-");
	
        if (newGenTlUpstreamdefect.getUpsdTempfield2() == null)
			newGenTlUpstreamdefect.setUpsdTempfield2("-");
        
        if (newGenTlUpstreamdefect.getUpsdTempfield3() == null)
			newGenTlUpstreamdefect.setUpsdTempfield3("-");
        
        if (newGenTlUpstreamdefect.getUpsdTempfield4() == null)
			newGenTlUpstreamdefect.setUpsdTempfield4("-");
        
        if (newGenTlUpstreamdefect.getUpsdTempfield5() == null)
			newGenTlUpstreamdefect.setUpsdTempfield5("-");
	
	}


	
	@Override
	public GenTlUpstreamdefectMst createNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,
			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		String validationsFor;
		validationsFor = "create";
		String xml = "UpstreamDefect";
		validations.validate(newGenTlUpstreamdefectMst, xml, validationsFor);
		validations.validate(newGenTlUpstreamdefectDet, xml, validationsFor);
		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		fillValuesNewgenTlUpstreammst(newGenTlUpstreamdefectMst, existGenTlUpstreamdefectMst,upstreamDefect);
		fillValuesNewgenTlUpstreamdtl(newGenTlUpstreamdefectDet, existGenTlUpstreamdefectDet,upstreamDefect);
		//return UpstreamDefectiveDao.createNewUpstream(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
		return upstreamdefectServiceApi.insertRecord(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
	}
	
	@Override
	public GenTlUpstreamdefectMst getFillControlDatas(String FnlnId, String date,String Keyid)
			throws Exception {
		// TODO Auto-generated method stub
		//return UpstreamDefectiveDao.getFillControlDatas(FnlnId,date,Keyid);
		return upstreamdefectServiceApi.getById(FnlnId,date,Keyid);
	}


	private void fillValuesNewgenTlUpstreammst(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
			GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,UpstreamDefect upstreamDefect)throws Exception {
		// TODO Auto-generated method stub

		newGenTlUpstreamdefectMst.setUpsmActive("Y");

		String dateTime = CommonFunctions.pg_dateTimeNow();

		newGenTlUpstreamdefectMst.setUpsmCreatedon(dateTime);

		newGenTlUpstreamdefectMst.setUpsmModifiedon(dateTime);
		if (newGenTlUpstreamdefectMst.getUpsmFlid() == null)
			newGenTlUpstreamdefectMst.setUpsmFlid("{}");

		if (newGenTlUpstreamdefectMst.getUpsmElementid() == null)
			newGenTlUpstreamdefectMst.setUpsmElementid("{}");
		
		if (newGenTlUpstreamdefectMst.getUpsmArea() == null)
			newGenTlUpstreamdefectMst.setUpsmArea("{}");
		
		String date = newGenTlUpstreamdefectMst.getUpsmDate();
		
		newGenTlUpstreamdefectMst.setUpsmDate(CommonFunctions.pg_getDateTimeFromDate(date));
		if (newGenTlUpstreamdefectMst.getUpsmDate() == null)
			newGenTlUpstreamdefectMst.setUpsmDate("{}");
		
		if (newGenTlUpstreamdefectMst.getUpsmInspectionlotno() == null)
			newGenTlUpstreamdefectMst.setUpsmInspectionlotno("{}");
		
		if (newGenTlUpstreamdefectMst.getUpsmTempfield1() == null)
			newGenTlUpstreamdefectMst.setUpsmTempfield1("-");
		
		if (newGenTlUpstreamdefectMst.getUpsmTempfield2() == null)
			newGenTlUpstreamdefectMst.setUpsmTempfield2("-");
		
		if (newGenTlUpstreamdefectMst.getUpsmTempfield3() == null)
			newGenTlUpstreamdefectMst.setUpsmTempfield3("-");
		
		if (newGenTlUpstreamdefectMst.getUpsmTempfield4() == null)
			newGenTlUpstreamdefectMst.setUpsmTempfield4("-");
		
		if (newGenTlUpstreamdefectMst.getUpsmTempfield5() == null)
			newGenTlUpstreamdefectMst.setUpsmTempfield5("-");

		if (newGenTlUpstreamdefectMst.getUpsmRemakrs() == null)
			newGenTlUpstreamdefectMst.setUpsmRemakrs("{}");
		
	}
	
	private void fillValuesNewgenTlUpstreamdtl(GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,
			GenTlUpstreamdefectDet existGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect)throws Exception {
		// TODO Auto-generated method stub
		
		//AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		newGenTlUpstreamdefectDet.setUpsdActive("Y");

		String dateTime = CommonFunctions.pg_dateTimeNow();

		newGenTlUpstreamdefectDet.setUpsdCreatedon(dateTime);

		newGenTlUpstreamdefectDet.setUpsdModifiedon(dateTime);
		if (newGenTlUpstreamdefectDet.getUpsdUpsmKeyid() == null)
			newGenTlUpstreamdefectDet.setUpsdUpsmKeyid("{}");

		if (newGenTlUpstreamdefectDet.getUpsdInformto() == null)
			newGenTlUpstreamdefectDet.setUpsdInformto("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdRawmaterial() == null)
			newGenTlUpstreamdefectDet.setUpsdRawmaterial("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdDefect() == null)
			newGenTlUpstreamdefectDet.setUpsdDefect("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdCorrectionaction() == null)
			newGenTlUpstreamdefectDet.setUpsdCorrectionaction("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdPreventiveaction() == null)
			newGenTlUpstreamdefectDet.setUpsdPreventiveaction("{}");
		
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield1() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield1("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield2() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield2("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield3() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield3("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield4() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield4("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield5() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield5("-");


		if (newGenTlUpstreamdefectDet.getUpsdCreatedby() == null)
			newGenTlUpstreamdefectDet.setUpsdCreatedby("{}");
		
		
		
	}



	@Override
	public GenTlUpstreamdefectMst updateNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,GenTlUpstreamdefectMst existGenTlUpstreamdefectMst,
			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		
		String validationsFor;
		validationsFor = "update";
		String xml = "UpstreamDefect";
	    validations.validate(newGenTlUpstreamdefectMst, xml, validationsFor);
		validations.validate(newGenTlUpstreamdefectDet, xml, validationsFor);
		fillValuesNewgenTlUpstreammst(newGenTlUpstreamdefectMst, existGenTlUpstreamdefectMst,upstreamDefect);
		fillValuesNewgenTlUpstreamdtl(newGenTlUpstreamdefectDet, newGenTlUpstreamdefectDet,upstreamDefect);
		//return UpstreamDefectiveDao.updateNewUpstream(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
		return upstreamdefectServiceApi.insertRecord(newGenTlUpstreamdefectMst,existGenTlUpstreamdefectMst,newGenTlUpstreamdefectDet,upstreamDefect);
	}
	
	
	@Override
	public GenTlUpstreamdefectMst deleteNewUpstreamDefect(
			GenTlUpstreamdefectMst newGenTlUpstreamdefectMst) throws Exception {
		// TODO Auto-generated method stub
		//return UpstreamDefectiveDao.deleteNewUpstreamDefect(newGenTlUpstreamdefectMst);
		return upstreamdefectServiceApi.deleteNewUpstreamDefect(newGenTlUpstreamdefectMst);
	}
	

	@Override
	public GenTlUpstreamdefectDet deleteNewUpstreamDefectDetails(
			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet) throws Exception {
		// TODO Auto-generated method stub
		//return UpstreamDefectiveDao.deleteNewUpstreamDefectDetails(newGenTlUpstreamdefectDet);
		return upstreamdefectServiceApi.deleteNewUpstreamDefectDetails(newGenTlUpstreamdefectDet);
	}
	
	
	@Override
	public GenTlUpstreamdefect updateUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,GenTlUpstreamdefect existGenTlUpstreamdefect,
			UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		fillValuesgenTlMommst(newGenTlUpstreamdefect, existGenTlUpstreamdefect,upstreamDefect);
		return UpstreamDefectiveDao.updateUpstream(newGenTlUpstreamdefect,existGenTlUpstreamdefect,upstreamDefect);
	}


	@Override
	public GenTlUpstreamdefect deleteUpstreamDefect(
			GenTlUpstreamdefect newGenTlUpstreamdefect) throws Exception {
		// TODO Auto-generated method stub
		return UpstreamDefectiveDao.deleteUpstreamDefect(newGenTlUpstreamdefect);
	}
	
	@Override
	public GenTlUpstreamdefect getFillControlData(String keyid)
			throws Exception {
		// TODO Auto-generated method stub
		return UpstreamDefectiveDao.getFillControlData(keyid);
	}


	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		//return UpstreamDefectiveDao.FillControlData(keyid);
		return upstreamdefectServiceApi.getByDetailId(keyid);
	}


	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	  // return UpstreamDefectiveDao.getElementId(loginflid, loginlevel, loginElementid, empId);
		return upstreamdefectServiceApi.getElementId(loginflid, loginlevel, loginElementid, empId);
	}



	


	


}
