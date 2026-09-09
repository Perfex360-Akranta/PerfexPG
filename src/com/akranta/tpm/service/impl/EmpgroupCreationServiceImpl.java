package com.akranta.tpm.service.impl;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlMomGroupmstDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlMomGroupmstDaoImpl;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMomGroupdtl;
import com.akranta.tpm.model.GenTlMomGroupmst;
import com.akranta.tpm.service.EmpgroupCreationService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;

public class EmpgroupCreationServiceImpl implements EmpgroupCreationService {

	private GenTlMomGroupmstDao empgroupmstCreationDao;
	private Validations validations;
	private EmployeeGroupServiceApi employeegroupserviceapi;

	public EmpgroupCreationServiceImpl(DBActionTemplate dbActionTemplate)
			throws Exception {

		empgroupmstCreationDao = new GenTlMomGroupmstDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	public void EmpgroupCreationServiceImplJwt(String JwtToken){
    	try{
    		empgroupmstCreationDao.GenTlMomGroupmstDaoImplJwt(JwtToken);
    		employeegroupserviceapi = new EmployeeGroupServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	@Override
	public List<String[]> getempgroupCreationMstGridData(
			CommonFilter commonFilter, CommonParams commonparams)
			throws NoDataFoundException, Exception {
		
		String functional=commonparams.getFlid();
		String mstkeyid=commonparams.getKeyid();
		return empgroupmstCreationDao.getEmpgroupCreationmstGridData(commonFilter, commonparams);
		//return employeegroupserviceapi.getCreationGrid(functional,mstkeyid);
	}

	public GenTlMomGroupmst createRecord(GenTlMomGroupmst newgenTlMomGroupmst,
			GenTlMomGroupmst existgenTlMomGroupmst)
			throws BusinessApplicationExceptions, ValidationExceptions,
			Exception {

		String validationsFor = "create";
		String xml = "EmpgroupCreation";
		validations.validate(newgenTlMomGroupmst, xml, validationsFor);
		fillValuesRecord(newgenTlMomGroupmst, existgenTlMomGroupmst);
		//return empgroupmstCreationDao.create(newgenTlMomGroupmst);
		return employeegroupserviceapi.saveEmployeeGroup(newgenTlMomGroupmst);
		
		
	}

	public GenTlMomGroupmst updateRecord(GenTlMomGroupmst newgenTlMomGroupmst,
			GenTlMomGroupmst existgenTlMomGroupmst) throws Exception {

		fillValuesRecord(newgenTlMomGroupmst, existgenTlMomGroupmst);
		//return empgroupmstCreationDao.update(newgenTlMomGroupmst);
		
		return employeegroupserviceapi.saveEmployeeGroup(newgenTlMomGroupmst);
	}

	private void fillValuesRecord(GenTlMomGroupmst newgenTlMomGroupmst,
			GenTlMomGroupmst existgenTlMomGroupmst) {
		newgenTlMomGroupmst.setMgrmActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();

		newgenTlMomGroupmst.setMgrmCreatedon(dateTime);
		newgenTlMomGroupmst.setMgrmModifiedon(dateTime);

		String emailid=newgenTlMomGroupmst.getMgrmEmailid();
		CommonMessage.debugMsg("Emailid in ServiceImpl"+emailid);		
 		
 		if (newgenTlMomGroupmst.getMgrmFlid() == null)
			newgenTlMomGroupmst.setMgrmFlid("-");

		
		if (newgenTlMomGroupmst.getMgrmTempfield2() == null)
			newgenTlMomGroupmst.setMgrmTempfield2("-");
		
		if(!UIUtils.isValidKeyId(newgenTlMomGroupmst.getMgrmEmailid()))
			newgenTlMomGroupmst.setMgrmEmailid("-");
		else if(UIUtils.isValidEmail(emailid) && UIUtils.isValidKeyId(newgenTlMomGroupmst.getMgrmEmailid()))
		{  newgenTlMomGroupmst.setMgrmEmailid(emailid);
		}
		
		
		newgenTlMomGroupmst.setGroupMemberDetail(fillGroupMemberData(
				newgenTlMomGroupmst, existgenTlMomGroupmst));

	}

	private List<GenTlMomGroupdtl> fillGroupMemberData(
			GenTlMomGroupmst newgenTlMomGroupmst,
			GenTlMomGroupmst existgenTlMomGroupmst) {

		CommonMessage.debugMsg("Detail 1");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<GenTlMomGroupdtl> newgenTlMemberData = newgenTlMomGroupmst
				.getGroupMemberDetail();

		List<GenTlMomGroupdtl> existgenTlMemberData = null;
		GenTlMomGroupdtl existGenTlGroupData = null;
		if (existgenTlMomGroupmst != null) {
			existgenTlMemberData = existgenTlMomGroupmst.getGroupMemberDetail();

			if (existgenTlMemberData != null && existgenTlMemberData.size() > 0)
				existGenTlGroupData = existgenTlMemberData.get(0);
		}

		List<GenTlMomGroupdtl> newGenTlMomGroupList = new ArrayList<GenTlMomGroupdtl>();
		if (newgenTlMemberData != null && newgenTlMemberData.size() > 0) {
			for (GenTlMomGroupdtl gentlGroupmember : newgenTlMemberData) {
				gentlGroupmember.setMgrdCreatedon(dateTime);
				gentlGroupmember.setMgrdModifiedon(dateTime);
				gentlGroupmember.setMgrdActive("Y");
				gentlGroupmember.setMgrdCreatedby(newgenTlMomGroupmst
						.getMgrmCreatedby());
				if (gentlGroupmember.getMgrdMgrmKeyid() == null)
					gentlGroupmember.setMgrdMgrmKeyid("-");

				if (gentlGroupmember.getMgrdTempfield1() == null)
					gentlGroupmember.setMgrdTempfield1("-");

				if (gentlGroupmember.getMgrdTempfield2() == null)
					gentlGroupmember.setMgrdTempfield2("-");

				if (gentlGroupmember.getMgrdEmpmKeyid() == null)
					gentlGroupmember.setMgrdEmpmKeyid("-");

				newGenTlMomGroupList.add(gentlGroupmember);

			}
		}
		return newGenTlMomGroupList;
	}

	@Override
	public List<String[]> getGroupGridData(
			CommonFilter commonFilter, CommonParams commonparams)
			throws NoDataFoundException, Exception {
		    String mstkeyid=commonparams.getKeyid();
		    //return employeegroupserviceapi.getDetailGrid(mstkeyid);
		return empgroupmstCreationDao.getGroupGridData(commonFilter, commonparams);
	}
	@Override
	public List<String[]> getEmpgroupViewGridData(GridParams gridparams)
			throws Exception {

		return empgroupmstCreationDao.getEmpgroupViewGridData(gridparams);
		//return employeegroupserviceapi.getGrid();
	}

	@Override
	public GenTlMomGroupmst getGridValues(String keyid) throws Exception {

		//return empgroupmstCreationDao.getGridValues(keyid);
		return employeegroupserviceapi.getViewGrid(keyid);
	}

	@Override
	public GenTlMomGroupmst DeleteGroupRecord(
			GenTlMomGroupmst newGenTlMomGroupmst)
			throws BusinessApplicationExceptions, Exception {
		return empgroupmstCreationDao.delete(newGenTlMomGroupmst);
	}

	@Override
	public GenTlMomGroupmst DeleteGroupMemberRecord(String keyid)
			throws Exception {

		GenTlMomGroupmst newGelTlMomGroupmst = new GenTlMomGroupmst();
		//this.empgroupmstCreationDao.RemoveGroupMember(keyid);
		employeegroupserviceapi.deleteDetailGrid(keyid);
		return newGelTlMomGroupmst;

	}

	@Override
	public Workbook getempgroupDetailExcel(GridParams gridparams,
			JSONObject colModel, String format, String keyid) throws Exception {

		return empgroupmstCreationDao.getempGroupDetailExcel(gridparams,
				colModel, format, keyid);
	}

	public Workbook getempgroupViewExcel(GridParams gridparams,
			JSONObject colModel, String format) throws Exception {

		return empgroupmstCreationDao.getempGroupViewExcel(gridparams,
				colModel, format);
	}

}
