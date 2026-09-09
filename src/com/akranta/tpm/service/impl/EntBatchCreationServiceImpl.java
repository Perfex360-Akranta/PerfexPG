package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BatchBean;
import com.akranta.tpm.bean.EntBatchEmployeeLinkBean;
import com.akranta.tpm.controller.UIUtils;

import com.akranta.tpm.bean.EntTlBatchMstBean;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntBatchCreationDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntBatchCreationDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntBatchMst;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.EntTlBatchFacultyLink;
import com.akranta.tpm.model.EntTlFacultymst;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;
import com.akranta.tpm.service.EntBatchCreationService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntBatchCreationServiceImpl implements EntBatchCreationService {
	private EntBatchCreationDao entBatchCreationDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	public EntBatchCreationServiceImpl(DBActionTemplate dbActionTemplate)
	{
		entBatchCreationDao=new EntBatchCreationDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}

	public EntBatchMst create(EntBatchMst newEntBatchMst,EntBatchMst oldEntBatchMst,  BatchBean batchBean) throws ValidationExceptions,Exception {

		try {
			CommonMessage.debugMsg("create service" + newEntBatchMst);
			String validationsFor = "create";
			validations.validate(newEntBatchMst,"BatchCreation",validationsFor);
			CommonMessage.debugMsg("after validation");
			fillValues(newEntBatchMst,oldEntBatchMst,batchBean);
			 CommonMessage.debugMsg("afete save fill newBachBatchMst= "+newEntBatchMst);
			// GBachlFunctionallocn  newGBachlFunctionallocn = fillFunctionLoc(newBachBatchMst);
			return entBatchCreationDao.create(newEntBatchMst);
			
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	
    
	public void setBachBatchCreationDao(EntBatchCreationDao entBatchCreationDao)
	{
		this.entBatchCreationDao = entBatchCreationDao;
	}

	

	private EntBatchMst fillValues(EntBatchMst newEntBatchMst,EntBatchMst oldEntBatchMst,BatchBean batchBean) {
		CommonMessage.debugMsg("create service fill value");
		newEntBatchMst.setBachActive("Y");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg(dateTime);
		CommonMessage.debugMsg("lllllll"+ 	newEntBatchMst.getBachCreatedon());
		CommonMessage.debugMsg("newBatchMst.getBatchKeyid()"+newEntBatchMst.getBachKeyid());
		if( ! UIUtils.isValidKeyId(newEntBatchMst.getBachKeyid())  )
		{
			newEntBatchMst.setBachCreatedon(dateTime);
			newEntBatchMst.setBachModifiedon(dateTime);
		}
		else{
			newEntBatchMst.setBachCreatedon(oldEntBatchMst.getBachCreatedon());
			newEntBatchMst.setBachModifiedon(oldEntBatchMst.getBachModifiedon());
		}  
		
		
		/*if( newEntBatchMst.getBachCode() == null )
			newEntBatchMst.setBachCode("{}");
		CommonMessage.debugMsg("fill2gs77777777777777777777777777777777777777" );
		
		if( newEntBatchMst.getBachName() == null )
			newEntBatchMst.setBachName("{}");*/
		
		if( !UIUtils.isValidKeyId(newEntBatchMst.getBachCode() ) )
			newEntBatchMst.setBachCode("{}");
		if( newEntBatchMst.getBachFactKeyid() == null )
			newEntBatchMst.setBachFactKeyid("{}");
		if( newEntBatchMst.getBachFromdate() == null )
			newEntBatchMst.setBachFromdate(Constants.passNullDate);
		
		if( newEntBatchMst.getBachTilldate() == null )
			newEntBatchMst.setBachTilldate(Constants.futureNullDate);
		
		if( newEntBatchMst.getBachMinsize() == null )
			newEntBatchMst.setBachMinsize("0");
		
		if( newEntBatchMst.getBachMaxsize() == null )
			newEntBatchMst.setBachMaxsize("0");
		
		if( newEntBatchMst.getBachDuration() == null )
			newEntBatchMst.setBachDuration("1");
		
		if( newEntBatchMst.getBachStatus() == null )
			newEntBatchMst.setBachStatus("P");
		
		
		
		if( newEntBatchMst.getBachRemarks() == null )
			newEntBatchMst.setBachRemarks("-");
		
		if( newEntBatchMst.getBachCompletedate() == null )
			newEntBatchMst.setBachCompletedate(newEntBatchMst.getBachTilldate());
		
		if( newEntBatchMst.getBachTempfield2() == null )
			newEntBatchMst.setBachTempfield2("-");
		
		if( newEntBatchMst.getBachTempfield3() == null )
			newEntBatchMst.setBachTempfield3("-");
		
		if( newEntBatchMst.getBachTempfield4() == null )
			newEntBatchMst.setBachTempfield4("-");
		
		if( newEntBatchMst.getBachTempfield5() == null )
			newEntBatchMst.setBachTempfield5("-");
		
		
		return newEntBatchMst;
		
	}
	

	public EntBatchMst update(EntBatchMst newEntBatchMst,EntBatchMst oldEntBatchMst,BatchBean batchBean) throws Exception {	// TODO Auto-generated method stub
			//return null;
		
		CommonMessage.debugMsg("updatee");
		String validationsFor;
		validationsFor = "update";
		validations.validate(newEntBatchMst,"BatchCreation",validationsFor);
		CommonMessage.debugMsg("validations");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newEntBatchMst,oldEntBatchMst,batchBean);
			
		return entBatchCreationDao.update(newEntBatchMst);
	}
			
		

	@Override
	public EntBatchMst delete(EntBatchMst entBatchMst) throws Exception {
		
		return entBatchCreationDao.delete(entBatchMst);
	}
	

	public EntBatchMst select(String Bachkeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.entBatchCreationDao.select(Bachkeyid);
	}

	public List<ComboBox> getBatchComboList(String batchProgKey,String frmMonth, ComboFilter Bach) throws Exception 
	{
	//	ComboFilter Bach = new ComboFilter();
		Bach.setIdField("BACH_KEYID");
		Bach.setCodeField("BACH_NAME");
		if(UIUtils.isValidKeyId(batchProgKey))
			Bach.setCondSql(" AND bach_prog_keyid = '"+batchProgKey+"'");
		if(UIUtils.isValidKeyId(frmMonth))
			Bach.setCondSql(" AND TO_CHAR (BACH_FROMDATE, 'MON-YYYY')  = UPPER( '"+ frmMonth +"')");
		Bach.setTableName(TableNames.TBL_ENT_TL_BATCHMST);
		
		return commonFilterDao.fillComboValues(Bach);
	}

	@Override
	public List<ComboBox> getVenueComboList( ComboFilter Bach,String flid) throws Exception {
		
		//ComboFilter Bach = new ComboFilter();
		Bach.setIdField("VENU_KEYID");
		Bach.setCodeField("VENU_CODE");
		Bach.setNameField("VENU_NAME");
		if(UIUtils.isValidKeyId(flid))
		//Bach.setCondSql(" and VENU_FLID= '" +flid+"'");
		if (UIUtils.isValidKeyId(flid)) {
			StringBuffer sb = new StringBuffer();
			sb.append(" AND VENU_FLID In (Select Fnln_Keyid From Gen_Tl_Functionallocn Where  " );
			sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
			sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID='" + flid + "') ) " );
			Bach.setCondSql(sb.toString());
		}
		Bach.setTableName(TableNames.TBL_ENT_TL_VENUEMST);
		
		return commonFilterDao.fillComboValues(Bach);
	}

	@Override
	public Workbook BatchExportExcel(
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return this.entBatchCreationDao.getBatch(tblJSONObj,format);
	}
	
	public List<ComboBox> getFacultyComboList(CommonFilter commonFilter,String batchId,ComboFilter ent) throws Exception 
	{
		//ComboFilter ent = new ComboFilter();
		ent.setIdField("FTYM_KEYID");
		ent.setNameField("FTYM_NAME");
		ent.setCodeField("FTYM_CODE");
		ent.setTableName(TableNames.TBL_ENT_TL_FACULTYMST);
		if(UIUtils.isValidKeyId(batchId))
		ent.setCondSql(" and  FTYM_KEYID not in (select BFLK_FTYM_KEYID from ENT_TL_BATCH_FACULTY_LINK where BFLK_BACH_KEYID='"+batchId+"')");
		return commonFilterDao.fillComboValues(ent);
	}

	public EntTlBatchFacultyLink createBatchFaculty(EntTlBatchFacultyLink newEntTlBatchFacultyLink,EntTlBatchFacultyLink oldEntTlBatchFacultyLink,  EntTlBatchMstBean entTlBatchMstBean) throws ValidationExceptions,Exception {

		try {
			String validationsFor = "create";
			CommonMessage.debugMsg("from gridv  "+newEntTlBatchFacultyLink.getFrmGrid());
			if(!UIUtils.isValidKeyId(newEntTlBatchFacultyLink.getFrmGrid()))
				validations.validate(newEntTlBatchFacultyLink,"EntBatchFacultyLink",validationsFor);
			CommonMessage.debugMsg("not from fdfd grid");
			fillValuesBatchFaculty(newEntTlBatchFacultyLink,oldEntTlBatchFacultyLink,entTlBatchMstBean);
			CommonMessage.debugMsg("not from gridsdssdsdasasasasasa ");
			List<String[]> count  = null;
			CommonMessage.debugMsg("not from grid sdsds");
			if(!UIUtils.isValidKeyId(newEntTlBatchFacultyLink.getFrmGrid())){
				CommonMessage.debugMsg("not from grid ");
				count  =  entBatchCreationDao.getBatchFacultyCount(newEntTlBatchFacultyLink);
				CommonMessage.debugMsg("from grid not");
				CommonMessage.debugMsg("count"+count.get(0)[0]);
				if(("0").equals(count.get(0)[0])|| !UIUtils.isValidKeyId(newEntTlBatchFacultyLink.getFrmGrid()) ){
					CommonMessage.debugMsg("t.get(0)[0/");
					return entBatchCreationDao.createBatchFaculty(newEntTlBatchFacultyLink);
				}else{
					return null;
				}
			}
			else{
				CommonMessage.debugMsg("from grid ffgfgfgfot");
				return entBatchCreationDao.createBatchFaculty(newEntTlBatchFacultyLink);
			}
			
			
			

		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}
	public EntTlBatchFacultyLink updateBatchFaculty(EntTlBatchFacultyLink newEntTlBatchFacultyLink,EntTlBatchFacultyLink oldEntTlBatchFacultyLink,  EntTlBatchMstBean entTlBatchMstBean) throws ValidationExceptions,Exception {
		//return null;
	
		String validationsFor;
		validationsFor = "update";
		validations.validate(newEntTlBatchFacultyLink,"BatchCreation",validationsFor);
		fillValuesBatchFaculty(newEntTlBatchFacultyLink, oldEntTlBatchFacultyLink, entTlBatchMstBean);
			
		return entBatchCreationDao.updateBatchFaculty(newEntTlBatchFacultyLink);
	}
	private EntTlBatchFacultyLink fillValuesBatchFaculty(EntTlBatchFacultyLink newEntTlBatchFacultyLink,EntTlBatchFacultyLink oldEntTlBatchFacultyLink,  EntTlBatchMstBean entTlBatchMstBean) {
		newEntTlBatchFacultyLink.setBflkActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("newCreatedon" +newEntTlBatchFacultyLink.getBflkCreatedon());
		//CommonMessage.debugMsg("oldCreatedon" +oldEntTlBatchFacultyLink.getBflkCreatedon());
		if(newEntTlBatchFacultyLink.getBflkKeyid() == null )
		{
			newEntTlBatchFacultyLink.setBflkCreatedon(dateTime);
			newEntTlBatchFacultyLink.setBflkModifiedon(dateTime);
			CommonMessage.debugMsg("0");
		}
		else{
			CommonMessage.debugMsg("oldCreatedon" +oldEntTlBatchFacultyLink.getBflkCreatedon());
			CommonMessage.debugMsg("0.1");
			newEntTlBatchFacultyLink.setBflkCreatedon(oldEntTlBatchFacultyLink.getBflkCreatedon());
			newEntTlBatchFacultyLink.setBflkModifiedon(oldEntTlBatchFacultyLink.getBflkModifiedon());
		}
		CommonMessage.debugMsg("0.2");
		if(newEntTlBatchFacultyLink.getBflkBachKeyid()==null)
			newEntTlBatchFacultyLink.setBflkBachKeyid("{}");
		CommonMessage.debugMsg("1");
		if(newEntTlBatchFacultyLink.getBflkFtymKeyid()==null)
			newEntTlBatchFacultyLink.setBflkFtymKeyid("{}");
		
		if( newEntTlBatchFacultyLink.getBflkEffFromdate() == null )
			newEntTlBatchFacultyLink.setBflkEffFromdate(dateTime);
		
		if( newEntTlBatchFacultyLink.getBflkEffTilldate() == null )
			newEntTlBatchFacultyLink.setBflkEffTilldate(Constants.futureNullDate);
		CommonMessage.debugMsg("2");
		if( newEntTlBatchFacultyLink.getBflkTempfield1() == null )
			newEntTlBatchFacultyLink.setBflkTempfield1("-");
		
		if( newEntTlBatchFacultyLink.getBflkTempfield2() == null )
			newEntTlBatchFacultyLink.setBflkTempfield2("-");
		
		if( newEntTlBatchFacultyLink.getBflkTempfield3() == null )
			newEntTlBatchFacultyLink.setBflkTempfield3("-");
		
		if( newEntTlBatchFacultyLink.getBflkTempfield4() == null )
			newEntTlBatchFacultyLink.setBflkTempfield4("-");
		CommonMessage.debugMsg("3");
		if( newEntTlBatchFacultyLink.getBflkTempfield5() == null )
			newEntTlBatchFacultyLink.setBflkTempfield5("-");
		
		if( newEntTlBatchFacultyLink.getBflkFtymKeyid() == null )
			newEntTlBatchFacultyLink.setBflkFtymKeyid("{}");
		CommonMessage.debugMsg("4");
		//CommonMessage.debugMsg("Facultymst().size  "+newEntTlBatchFacultyLink.getEntTlFacultymst().size());
		if(newEntTlBatchFacultyLink.getEntTlFacultymst() != null && newEntTlBatchFacultyLink.getEntTlFacultymst().size()>0)
		newEntTlBatchFacultyLink.setEntTlFacultymst(refTablesFillValues(newEntTlBatchFacultyLink,oldEntTlBatchFacultyLink));
		CommonMessage.debugMsg("after mst().size ");
		return newEntTlBatchFacultyLink;
		
	}
	private List<EntTlFacultymst> refTablesFillValues(EntTlBatchFacultyLink newEntTlBatchFacultyLink,
			EntTlBatchFacultyLink oldEntTlBatchFacultyLink) {
		// TODO Auto-generated method stub
		List<EntTlFacultymst> facultymstList = newEntTlBatchFacultyLink.getEntTlFacultymst();
		List<EntTlFacultymst> oldfacultymstList = null;
		EntTlFacultymst oldEntTlFacultymst = null;
		String dateTime = CommonFunctions.dateTimeNow();
	
		if( oldEntTlBatchFacultyLink != null)
		{
			oldfacultymstList = oldEntTlBatchFacultyLink.getEntTlFacultymst();
		
		if( oldfacultymstList != null && oldfacultymstList.size() > 0 )
		   
			oldEntTlFacultymst = oldfacultymstList.get(0);
		}
		List<EntTlFacultymst> newEntTlFacultymstList = new ArrayList<EntTlFacultymst>();
		for( EntTlFacultymst entTlFacultymst : facultymstList)
		{	
			if(entTlFacultymst.getFtymKeyid() == null )			
			{	
				
				entTlFacultymst.setFtymCreatedon(dateTime);
			}	
			else{
				entTlFacultymst.setFtymCreatedon(oldEntTlFacultymst.getFtymCreatedon());
			}
			entTlFacultymst.setFtymModifiedon(dateTime);
			if(entTlFacultymst.getFtymActive()== null)
				entTlFacultymst.setFtymActive("Y");	
			if(entTlFacultymst.getFtymAddress1()== null)
				entTlFacultymst.setFtymAddress1("{}");	
			if(entTlFacultymst.getFtymAddress2()== null)
				entTlFacultymst.setFtymAddress2("{}");	
			if(entTlFacultymst.getFtymAddress3()== null)
				entTlFacultymst.setFtymAddress3("{}");
			if(entTlFacultymst.getFtymCode()== null)
				entTlFacultymst.setFtymCode("{}");	
			if(entTlFacultymst.getFtymCreatedby()== null)
				entTlFacultymst.setFtymCreatedby(newEntTlBatchFacultyLink.getBflkCreatedby());
			if(entTlFacultymst.getFtymEmpmKeyid()== null)
				entTlFacultymst.setFtymEmpmKeyid("{}");	
			if(entTlFacultymst.getFtymFactKeyid()== null)
				entTlFacultymst.setFtymFactKeyid("{}");
			if(entTlFacultymst.getFtymName()== null)
				entTlFacultymst.setFtymName("{}");
			if(entTlFacultymst.getFtymPotential()== null)
				entTlFacultymst.setFtymPotential("N");
			if(entTlFacultymst.getFtymRemarks()== null)
				entTlFacultymst.setFtymRemarks("{}");
			if(entTlFacultymst.getFtymType()== null)
				entTlFacultymst.setFtymType("I");
			if(entTlFacultymst.getFtymTempfield1()== null)
				entTlFacultymst.setFtymTempfield1("-");
			if(entTlFacultymst.getFtymTempfield2()== null)
				entTlFacultymst.setFtymTempfield2("-");
			if(entTlFacultymst.getFtymTempfield3()== null)
				entTlFacultymst.setFtymTempfield3("-");
			if(entTlFacultymst.getFtymTempfield4()== null)
				entTlFacultymst.setFtymTempfield4("-");
			if(entTlFacultymst.getFtymTempfield5()== null)
				entTlFacultymst.setFtymTempfield5("-");
			newEntTlFacultymstList.add(entTlFacultymst);
		}
		return newEntTlFacultymstList;
	}

	@Override
	public List<String[]> getBatchFacultyView(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception
	{
		return this.entBatchCreationDao.getBatchFacultyView(entTlBatchFacultyLink);
	}
	@Override
	public EntTlBatchFacultyLink deleteBatchFaculty(EntTlBatchFacultyLink entTlBatchFacultyLink) throws Exception {
		
		return entBatchCreationDao.deleteBatchFaculty(entTlBatchFacultyLink);
	}
	@Override
	public List<String[]> getBatchFacultyCount(EntTlBatchFacultyLink entTlBatchFacultyLink)throws Exception
	{
		return this.entBatchCreationDao.getBatchFacultyCount(entTlBatchFacultyLink);
	}
	@Override
	public List<String[]> getBatchEmployeeView(EntTlBatchEmployeeLink entTlBatchEmployeeLink,String batchId,String deptId,String empFilter)throws Exception
	{CommonMessage.debugMsg("insides service impl");
		return this.entBatchCreationDao.getBatchEmployeeView(entTlBatchEmployeeLink,batchId,deptId,empFilter);
	}
	/*public EntTlBatchEmployeeLink createBatchEmployee(EntTlBatchEmployeeLink newEntTlBatchEmployeeLink,EntTlBatchEmployeeLink oldEntTlBatchEmployeeLink,  EntBatchEmployeeLinkBean entBatchEmployeeLinkBean) throws ValidationExceptions,Exception {

		try {
			String validationsFor = "create";
			validations.validate(newEntTlBatchEmployeeLink,"EntBatchFacultyLink",validationsFor);
			fillValuesBatchEmployee(newEntTlBatchEmployeeLink,oldEntTlBatchEmployeeLink,entBatchEmployeeLinkBean);
			//List<String[]> count  =  entBatchCreationDao.getBatchFacultyCount(newEntTlBatchEmployeeLink);
			return entBatchCreationDao.createBatchEmployee(newEntTlBatchEmployeeLink);
			
		}catch (ValidationExceptions e){

			throw new ValidationExceptions(e.getMessage());
		}	
	}*/
	private List<EntTlBatchEmployeeLink> fillValuesBatchEmployee(String usrm_ccno,String batchId, List<EntTlBatchEmployeeLink> employeelinkList) {
		
		List<EntTlBatchEmployeeLink> newEntTlBatchEmployeeLinks =employeelinkList;
		List<EntTlBatchEmployeeLink> newEntTlBatchEmployeeLinkList = new ArrayList<EntTlBatchEmployeeLink>();
		int index=0;
		if(employeelinkList!=null)
		for( EntTlBatchEmployeeLink genEntTlBatchEmployeeLink : newEntTlBatchEmployeeLinks)
		{	
			String dateTime = CommonFunctions.dateTimeNow();
			EntTlBatchEmployeeLink genEntTlBatchEmployeeLink1 = newEntTlBatchEmployeeLinks.get(index);
			index++;
			genEntTlBatchEmployeeLink.setBstdactive("Y");
			genEntTlBatchEmployeeLink.setBstdcreatedon(dateTime);
			genEntTlBatchEmployeeLink.setBstdcreatedby(usrm_ccno);
			genEntTlBatchEmployeeLink.setBstdmodifiedon(dateTime);
			if(UIUtils.isValidKeyId(batchId))
			genEntTlBatchEmployeeLink.setBstdbachkeyid(batchId);
			genEntTlBatchEmployeeLink.setBstdefffromdate(dateTime);
			genEntTlBatchEmployeeLink.setBstdefftilldate(Constants.futureNullDate);
			genEntTlBatchEmployeeLink.setBstdempmkeyid(genEntTlBatchEmployeeLink1.getBstdempmkeyid());
			genEntTlBatchEmployeeLink.setBstdtempfield1("-");
			genEntTlBatchEmployeeLink.setBstdtempfield2("-");
			genEntTlBatchEmployeeLink.setBstdtempfield3("-");
			genEntTlBatchEmployeeLink.setBstdtempfield4("-");
			genEntTlBatchEmployeeLink.setBstdtempfield5("-");
			newEntTlBatchEmployeeLinkList.add(genEntTlBatchEmployeeLink);
	}
	return newEntTlBatchEmployeeLinkList;
		
	}
	
	/*public EntTlBatchEmployeeLink updateBatchEmployee(EntTlBatchEmployeeLink newEntTlBatchEmployeeLink,EntTlBatchEmployeeLink oldEntTlBatchEmployeeLink,  EntBatchEmployeeLinkBean entBatchEmployeeLinkBean) throws ValidationExceptions,Exception {
		//return null;
	
		String validationsFor;
		validationsFor = "update";
		validations.validate(newEntTlBatchEmployeeLink,"EntBatchFacultyLink",validationsFor);
		fillValuesBatchEmployee(newEntTlBatchEmployeeLink, oldEntTlBatchEmployeeLink, entBatchEmployeeLinkBean);
			
		return entBatchCreationDao.updateBatchEmployee(newEntTlBatchEmployeeLink);
	}
*/
	@Override
	public List<String[]> getBatch(String progId,String frmMonth) throws Exception {
		// TODO Auto-generated method stub
		return this.entBatchCreationDao.getBatchDao(progId,frmMonth);
	}

	@Override
	public List<ComboBox> getProgkeyidCombo(String spokeKey,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("PROG_NAME");
		comboFilter.setIdField("PROG_KEYID");
		if( UIUtils.isValidKeyId(spokeKey)  ){
			comboFilter.setCondSql(" AND PROG_SPOKE_KEYID = '" + spokeKey + "'");
		}
		comboFilter.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getspokekeyidCombo(String consql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("SPOK_NAME");
		comboFilter.setIdField("SPOK_KEYID");
		if( UIUtils.isValidKeyId(consql)  ){
			comboFilter.setCondSql(" AND SPOK_KEYID = ( select PROG_SPOKE_KEYID from ent_tl_programmst where PROG_KEYID = '" + consql + "')");
		}
		comboFilter.setTableName(TableNames.TBL_ENT_TL_SPOKEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getFacultylist(String programKeyId) throws Exception {
		// TODO Auto-generated method stub
		return entBatchCreationDao.getFacultylist(programKeyId);
	}

	@Override
	public List<EntTlBatchEmployeeLink> createBatchEmployee(String usrm_ccno,String batchId, List<EntTlBatchEmployeeLink> employeelinkList,EntBatchEmployeeLinkBean entBatchEmployeeLinkBean) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("batchId in service:"+batchId);
		return entBatchCreationDao.createBatchEmployee(fillValuesBatchEmployee(usrm_ccno,batchId,employeelinkList),batchId);
	}

	@Override
	public void deleteByBstdKeyId(String bstdKeyid) throws Exception {
		entBatchCreationDao.deleteByBstdKeyId(	bstdKeyid);
		
	}

	


	
/*	public BachBatchMst assmformfill(String keyId) {
		// TODO Auto-generated method stub
		return this.BachBatchCreationDao.assmformfill(keyId);
	}*/

}



