package com.akranta.tpm.service.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_PlmTlStandardsFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.BAL_PlmTlShutdowncalDao;
import com.akranta.tpm.dao.BAL_PlmTlStandardsDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.BAL_PlmTlShutdowncalDaoImpl;
import com.akranta.tpm.dao.impl.BAL_PlmTlStandardsDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl;
import com.akranta.tpm.model.BAL_PlmTlMethodsmst;
import com.akranta.tpm.model.BAL_PlmTlMethodtasklist;
import com.akranta.tpm.model.BAL_PlmTlMultipleResp;
import com.akranta.tpm.model.BAL_PlmTlPmsftpermitlink;
import com.akranta.tpm.model.BAL_PlmTlShutdowncal;
import com.akranta.tpm.model.BAL_PlmTlStandards;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.service.BAL_PlmTlStandardsService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.BAL_PlmStandardsServiceApi;

import com.akranta.tpm.service.api.FunctionCallApi;


public class BAL_PlmTlStandardsServiceImpl implements BAL_PlmTlStandardsService {
	
	private CommonFilterDao commonFilterDao;
	private BAL_PlmTlStandardsDao plmTlStandardsDao;
	private BAL_PlmTlShutdowncalDao plmTlShutdowncalDao;
	private Validations validations; 
	private BAL_PlmStandardsServiceApi balpmstandards;
	FunctionCallApi fnCallApi;
	public BAL_PlmTlStandardsServiceImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		plmTlStandardsDao = new BAL_PlmTlStandardsDaoImpl(dbActionTemplate); 
		plmTlShutdowncalDao= new BAL_PlmTlShutdowncalDaoImpl(dbActionTemplate); 
		validations  = new Validations();
	}
	public void BAL_PlmTlStandardsServiceImplJwt(String JwtToken){
		try{
			plmTlStandardsDao.BAL_PlmTlStandardsDaoImplJwt(JwtToken);
			balpmstandards = new BAL_PlmStandardsServiceApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	    // TODO Auto-generated constructor stub
	}
	@Override
	public List<ComboBox> getPmsdTradeCombo(ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getpmsdpreparedbyCombo(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("EMPM_CODE");
		comboFilter.setNameField("EMPM_NAME");
		comboFilter.setIdField("EMPM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_EMPLOYEEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getcbmInspectionIdCombo(ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setCodeField("INSM_CODE");
		comboFilter.setNameField("INSM_NAME");
		comboFilter.setIdField("INSM_KEYID");
		comboFilter.setTableName(TableNames.TBL_PLM_TL_INSPECTIONMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getpmsdassemblyIdCombo(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("ASSEMBLYNAME");
		comboFilter.setIdField("ASSEMBLYID");
		comboFilter.setTableName(TableNames.TBL_GEN_VW_MCHASMLINK);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	/*
	 * @Override public List<ComboBox> getpmsdsubassemblyIdCombo(ComboFilter
	 * comboFilter,String assmId) throws Exception { // TODO Auto-generated method
	 * stub //ComboFilter comboFilter = new ComboFilter();
	 * if(UIUtils.isValidKeyId(assmId)) { comboFilter.setIdField("SBAM_KEYID");
	 * //subAssembly.setCodeField("SBAM_CODE");
	 * comboFilter.setNameField("SBAM_NAME"); StringBuilder CondSql=new
	 * StringBuilder(); CondSql.
	 * append(" and SBAM_KEYID in ( select fnln_originalid from gen_tl_functionallocn"
	 * ) ;
	 * CondSql.append(" where fnln_elementtype = 'SAM' AND instr(fnln_parentid, '");
	 * CondSql.append(assmId); CondSql.append("')>0 )");
	 * comboFilter.setCondSql(CondSql.toString());
	 * 
	 * } else{ comboFilter.setNameField("SBAM_NAME");
	 * comboFilter.setIdField("SBAM_KEYID"); }
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST); return
	 * commonFilterDao.fillComboValues(comboFilter); }
	 */
	/*
	 * @Override public List<ComboBox> getpmsdsubassemblyIdCombo(ComboFilter
	 * comboFilter, String assmId) throws Exception {
	 * 
	 * if (UIUtils.isValidKeyId(assmId)) {
	 * 
	 * comboFilter.setIdField("SBAM_KEYID"); comboFilter.setNameField("SBAM_NAME");
	 * 
	 * StringBuilder condSql = new StringBuilder();
	 * 
	 * condSql.append( " AND SBAM_KEYID IN (" + " SELECT fnln_originalid " +
	 * " FROM gen_tl_functionallocn " + " WHERE fnln_elementtype = 'SAM' " +
	 * " AND POSITION('" );
	 * 
	 * condSql.append(assmId);
	 * 
	 * condSql.append( "' IN fnln_parentid) > 0 " + ")" );
	 * 
	 * comboFilter.setCondSql(condSql.toString());
	 * 
	 * } else {
	 * 
	 * comboFilter.setNameField("SBAM_NAME"); comboFilter.setIdField("SBAM_KEYID");
	 * }
	 * 
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST);
	 * 
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */
	@Override
	public List<ComboBox> getpmsdsubassemblyIdCombo(ComboFilter comboFilter, String assmId, String machineId) throws Exception {

	    System.out.println("getpmsdsubassemblyIdCombo assmId=[" + assmId + "] machineId=[" + machineId + "]");

	    comboFilter.setIdField("SBAM_KEYID");
	    comboFilter.setNameField("SBAM_NAME");
	    comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST);

	    String condSql = " AND SBAM_ACTIVE = 'Y'";

	    if (UIUtils.isValidKeyId(assmId)) {
	        // Assembly selected → filter by assembly
	        condSql += " AND SBAM_ASSEMBLYID = '" + assmId + "'";
	    } else if (UIUtils.isValidKeyId(machineId)) {
	        // Only machine selected → show all subassemblies under that machine's assemblies
	        condSql += " AND SBAM_ASSEMBLYID IN ("
	                + " SELECT ASSEMBLYID FROM GEN_VW_MCHASMLINK"
	                + " WHERE MACHINEID = '" + machineId + "'"
	                + ")";
	    }
	    // Neither selected → show all

	    System.out.println("getpmsdsubassemblyIdCombo condSql=[" + condSql + "]");
	    comboFilter.setCondSql(condSql);

	    return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getpmsdjobtypeCombo(String condSql,ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("PMAM_NAME ");
		comboFilter.setCodeField("PMAM_CODE");
		comboFilter.setIdField("PMAM_CODE");
		comboFilter.setCondSql(" AND PMAM_ACTIVE = 'Y'"); 
		comboFilter.setTableName(TableNames.TBL_BAL_PLM_TL_ACTIVITYTYPEMST);
		
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<ComboBox> getpmsdphenomenaIdCombo(ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BPHM_PHENOMENANAME");
		comboFilter.setIdField("BPHM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_PHENOMENAMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<ComboBox> getpmsdcauseIdCombo(ComboFilter comboFilter) throws Exception {
		// TODO Auto-generated method stub
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setNameField("BCSM_NAME ");
		comboFilter.setIdField("BCSM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_CAUSEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public List<String[]> getAllsubtype() {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("inside service Impl "	);
		return plmTlStandardsDao.getAllsubtype();
	}
	
	
	@Override
	
	public List<BAL_PlmTlStandards> createMultiple( List<BAL_PlmTlStandards> newList,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions, Exception {

	    CommonFunctions.debugMsg("createMultiple CALLED, rows=" + newList.size());
	    List<BAL_PlmTlStandards> savedList = new ArrayList<>();

	    try {
	        for (BAL_PlmTlStandards newPlmTlStandards : newList) {

	            BAL_PlmTlStandards existPlmTlStandards = null; // always null for create

	            String validationsFor;
	            if ("SDM".equals(newPlmTlStandards.getPmsdActivitytype()))
	                validationsFor = "SDMcreate";
	            else
	                validationsFor = "create";

	            validations.validate(newPlmTlStandards, "PmstandardCreation", validationsFor);

	            if ("CBM".equals(newPlmTlStandards.getPmsdActivitytype())) {
	                CommonFunctions.debugMsg("CBM Detail");
	                List<BAL_PlmTlCbmstdcadtl> cbmlist = newPlmTlStandards.getCbmData();
	                if (newPlmTlStandards.getCbmData() != null && newPlmTlStandards.getCbmData().size() > 0) {
	                    for (BAL_PlmTlCbmstdcadtl newplmTlCbmstdcadtl : cbmlist) {
	                        validations.validate(newplmTlCbmstdcadtl, "PmstandardCreation", validationsFor);
	                    }
	                } else {
	                    BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl();
	                    validations.validate(plmTlCbmstdcadtl, "PmstandardCreation", validationsFor);
	                }
	            }

	            if (newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size() > 0) {
	                CommonFunctions.debugMsg("method Detail");
	            }

	            CommonFunctions.debugMsg("After validation");

	            if (newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size() > 0) {
	                CommonFunctions.debugMsg("tool Detail");
	                List<BAL_PlmTlToolsdtl> toolsPickUplist = newPlmTlStandards.getToolsDetail();
	                CommonFunctions.debugMsg("dasf---" + toolsPickUplist.size());
	            }

	            BdmTlYycountermeasurelink counterMeasurelist = newPlmTlStandards.getCountermeasureLink();

	            fillValues(newPlmTlStandards, existPlmTlStandards, plmTlStandardsFormBean);
	            CommonFunctions.debugMsg("After Filling tool Values" + newPlmTlStandards.getPmsdAssemblyid());

	            String dKeyId = plmTlStandardsFormBean.getBdmDockKey();
	           // BAL_PlmTlStandards plmTlStandards = plmTlStandardsDao.create(newPlmTlStandards, dKeyId);
	            BAL_PlmTlStandards plmTlStandards = balpmstandards.savePlmTlStandards(newPlmTlStandards, dKeyId);
				/*
				 * List<BAL_PlmTlMethodsmst> methodsList = newPlmTlStandards.getMethodDetail();
				 * List<BAL_PlmTlCbmstdcadtl> cbmList = newPlmTlStandards.getCbmData();
				 * List<BAL_PlmTlToolsdtl> toolsList = newPlmTlStandards.getToolsDetail();
				 * List<BAL_PlmTlPmsftpermitlink> permitLinksList =
				 * newPlmTlStandards.getPermitlinkDetail();
				 * 
				 * BAL_PlmTlStandards plmTlStandards = balpmstandards.saveApi(
				 * newPlmTlStandards, methodsList, cbmList, toolsList, permitLinksList,
				 * counterMeasurelist, plmTlStandardsFormBean.getFormActionMode(),
				 * plmTlStandardsFormBean.getFormMode(),
				 * plmTlStandardsFormBean.getFormHeader());
				 */
	            if (!"SDM".equals(newPlmTlStandards.getPmsdActivitytype())) {
	               // plmTlStandardsDao.generateCalendar((newPlmTlStandards.getPmsdEffectivedate()).substring(7, 11),
					/*
					 * plmTlStandardsDao.generateCalendar((newPlmTlStandards.getPmsdEffectivedate())
					 * , newPlmTlStandards.getPmsdFactoryid(), newPlmTlStandards.getPmsdSectionid(),
					 * newPlmTlStandards.getPmsdCellid(), newPlmTlStandards.getPmsdMachineid(),
					 * newPlmTlStandards.getPmsdAssemblyid(),
					 * newPlmTlStandards.getPmsdFrequencyunit(), newPlmTlStandards.getPmsdMouldid(),
					 * (newPlmTlStandards.getPmsdEffectivedate()).substring(3, 6));
					 */
	            	String[] genYearMonth = extractYearAndMonthForCalendar(newPlmTlStandards.getPmsdEffectivedate());
	                if (genYearMonth != null) {
	                    plmTlStandardsDao.generateCalendar(genYearMonth[0],
	                            newPlmTlStandards.getPmsdFactoryid(),
	                            newPlmTlStandards.getPmsdSectionid(),
	                            newPlmTlStandards.getPmsdCellid(),
	                            newPlmTlStandards.getPmsdMachineid(),
	                            newPlmTlStandards.getPmsdAssemblyid(),
	                            newPlmTlStandards.getPmsdFrequencyunit(),
	                            newPlmTlStandards.getPmsdMouldid(),
	                            genYearMonth[1]);
	                } else {
	                    CommonFunctions.debugMsg("Skipping calendar generation - could not derive year/month from effective date: "
	                            + newPlmTlStandards.getPmsdEffectivedate());
	                }
	            } else {
	                CommonFunctions.debugMsg(" PM Calendar Not Genrated.....  ");
	            }

	            savedList.add(plmTlStandards);
	        }
	        return savedList;

	    } catch (ValidationExceptions e) {
	        CommonFunctions.debugMsg("createMultiple validation" + e.getMessage());
	        e.printStackTrace();
	        throw new ValidationExceptions(e.getMessage());
	    }
	}
	
	
	private String[] extractYearAndMonthForCalendar(String pmsdEffectivedate) {
	    if (pmsdEffectivedate == null || pmsdEffectivedate.trim().isEmpty()) {
	        return null;
	    }

	    String value = pmsdEffectivedate.trim();

	    try {
	        // ISO-style: yyyy-MM-dd...  (dash at index 4 and 7)
	        if (value.length() >= 10 && value.charAt(4) == '-' && value.charAt(7) == '-') {
	            String year = value.substring(0, 4);
	            int monthNum = Integer.parseInt(value.substring(5, 7));
	            if (monthNum < 1 || monthNum > 12) return null;
	            String[] shortMonths = new java.text.DateFormatSymbols().getShortMonths();
	            String monthAbbrev = shortMonths[monthNum - 1];
	            monthAbbrev = monthAbbrev.substring(0, 1).toUpperCase() + monthAbbrev.substring(1, 3).toLowerCase();
	            return new String[] { year, monthAbbrev };
	        }

	        // Legacy style: dd-MON-yyyy  (dash at index 2 and 6)
	        if (value.length() >= 11 && value.charAt(2) == '-' && value.charAt(6) == '-') {
	            String rawMonth = value.substring(3, 6);
	            String monthAbbrev = rawMonth.substring(0, 1).toUpperCase() + rawMonth.substring(1, 3).toLowerCase();
	            String year = value.substring(7, 11);
	            return new String[] { year, monthAbbrev };
	        }
	    } catch (Exception e) {
	        CommonFunctions.debugMsg("extractYearAndMonthForCalendar failed to parse: " + pmsdEffectivedate + " - " + e.getMessage());
	        return null;
	    }

	    CommonFunctions.debugMsg("extractYearAndMonthForCalendar: unrecognized date format: " + pmsdEffectivedate);
	    return null;
	}

	@Override
	
	public List<BAL_PlmTlStandards> updateMultiple( List<BAL_PlmTlStandards> newList,List<BAL_PlmTlStandards> existList,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions, Exception {

	    CommonFunctions.debugMsg("updateMultiple CALLED, rows=" + newList.size());
	    List<BAL_PlmTlStandards> savedList = new ArrayList<>();

	    try {
	        for (int i = 0; i < newList.size(); i++) {
	            BAL_PlmTlStandards newPlmTlStandards = newList.get(i);
	            BAL_PlmTlStandards existPlmTlStandards =
	                    (existList != null && existList.size() > i) ? existList.get(i) : null;

	            String validationsFor = "create";
	            CommonFunctions.debugMsg("Inside the ServiceImpl updateMultiple");

	            validations.validate(newPlmTlStandards, "PmstandardCreation", validationsFor);
	            validations.validate(newPlmTlStandards, "PmstandardCreation", "update");
	            CommonFunctions.debugMsg(" after validatiop ");

	            if (newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size() > 0) {
	                List<BAL_PlmTlMethodsmst> newPlmTlmethodsmsts = newPlmTlStandards.getMethodDetail();
	                for (BAL_PlmTlMethodsmst plmTlmethodsmst : newPlmTlmethodsmsts) {
	                    CommonFunctions.debugMsg(" methodname " + plmTlmethodsmst.getPmmsActivity());
	                    validations.validate(plmTlmethodsmst, "PmstandardCreation", validationsFor);
	                }
	            }

	            if (newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size() > 0) {
	                List<BAL_PlmTlToolsdtl> plmTlToolsdtlList = newPlmTlStandards.getToolsDetail();
	                for (BAL_PlmTlToolsdtl plmTlToolsdtl : plmTlToolsdtlList) {
	                    CommonFunctions.debugMsg("TOOLS  " + plmTlToolsdtl.getPtldToolid());
	                    validations.validate(plmTlToolsdtl, "PmstandardCreation", validationsFor);
	                }
	            }

	            BdmTlYycountermeasurelink counterMeasurelist = newPlmTlStandards.getCountermeasureLink();
	            CommonFunctions.debugMsg("After");

	            fillValues(newPlmTlStandards, existPlmTlStandards, plmTlStandardsFormBean);
	            String dKeyId = plmTlStandardsFormBean.getBdmDockKey();

	            //BAL_PlmTlStandards plmTlStandards = plmTlStandardsDao.update(newPlmTlStandards, dKeyId);
	            BAL_PlmTlStandards plmTlStandards = balpmstandards.savePlmTlStandards(newPlmTlStandards, dKeyId);
	            savedList.add(plmTlStandards);
	        }
	        return savedList;

	    } catch (ValidationExceptions e) {
	        e.printStackTrace();
	        CommonFunctions.debugMsg(e.getMessage());
	        throw new ValidationExceptions(e.getMessage());
	    }
	}

	@Override
	public BAL_PlmTlStandards create(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		/*****/
		CommonFunctions.debugMsg("CREATE CALLED");
		try {
			CommonFunctions.debugMsg("Service IMPL");
			/*
			 * String validationsFor;
			 * if(newPlmTlStandards.getPmsdActivitytype().equals("SDM")) validationsFor =
			 * "SDMcreate"; else validationsFor = "create";
			 */		
			String validationsFor;
			if("SDM".equals(newPlmTlStandards.getPmsdActivitytype()))
			    validationsFor = "SDMcreate";
			else
			    validationsFor = "create";
			validations.validate(newPlmTlStandards,"PmstandardCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			//CommonFunctions.debugMsg("newPlmTlStandards.getPmsdSource()   :"+newPlmTlStandards.getMethodDetail());
			
			//if(newPlmTlStandards.getCbmData() != null && newPlmTlStandards.getCbmData().size()>0){
			//if(newPlmTlStandards.getPmsdActivitytype().equals("CBM")){
			//mano
			if("CBM".equals(newPlmTlStandards.getPmsdActivitytype())) {
				CommonFunctions.debugMsg("CBM Detail");
			List<BAL_PlmTlCbmstdcadtl> cbmlist = newPlmTlStandards.getCbmData();
				if(newPlmTlStandards.getCbmData() != null && newPlmTlStandards.getCbmData().size()>0){
					for(BAL_PlmTlCbmstdcadtl newplmTlCbmstdcadtl:cbmlist)
					{
						validations.validate(newplmTlCbmstdcadtl,"PmstandardCreation",validationsFor);
					}
				 }
				
				else{
					BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl(); 
					validations.validate(plmTlCbmstdcadtl,"PmstandardCreation",validationsFor);
				}
			}
			if(newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size()>0){
				CommonFunctions.debugMsg("method Detail");
			/*List<PlmTlMethodsmst> methodslist = newPlmTlStandards.getMethodDetail();
				for(PlmTlMethodsmst multipleMethod:methodslist)
				{
					//validations.validate(multipleMethod,"PmstandardCreation",validationsFor);
				}*/
			}
			CommonFunctions.debugMsg("After validation");
		//**for tools*//*
			if(newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size()>0){
				CommonFunctions.debugMsg("tool Detail");
			List <BAL_PlmTlToolsdtl> toolsPickUplist = newPlmTlStandards.getToolsDetail();
			CommonFunctions.debugMsg("dasf---"+toolsPickUplist.size());
				/*for(PlmTlToolsdtl toolsPickUp:toolsPickUplist)
				{
					CommonFunctions.debugMsg("tool validate");
					
					//validations.validate(toolsPickUp,"PmstandardCreation",validationsFor);
				}*/
			}
			//**for counterMeasure**//
			BdmTlYycountermeasurelink counterMeasurelist = newPlmTlStandards.getCountermeasureLink();
			
			/*if(newPlmTlStandards.getPmsdSource().equals("ON"))
			{
				CommonFunctions.debugMsg("Inside If   :"+newPlmTlStandards.getPmsdSource());
				newPlmTlStandards.setPmsdSource("I");
			}
			else{
				//CommonFunctions.debugMsg("newPlmTlStandards.getPmsdSource()   :"+newPlmTlStandards.getPmsdSource());
				newPlmTlStandards.setPmsdSource("E");
			}*/
			fillValues(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean);
			CommonFunctions.debugMsg("After Filling tool Values" +newPlmTlStandards.getPmsdAssemblyid());
			String dKeyId = plmTlStandardsFormBean.getBdmDockKey();
			//for inserting in clisCalendar procedure
			BAL_PlmTlStandards plmTlStandards = plmTlStandardsDao.create(newPlmTlStandards,dKeyId);
			//if(!newPlmTlStandards.getPmsdActivitytype().equals("SDM")){
			//mano
			if(!"SDM".equals(newPlmTlStandards.getPmsdActivitytype())){
				
				plmTlStandardsDao.generateCalendar((newPlmTlStandards.getPmsdEffectivedate()).substring(7,11),newPlmTlStandards.getPmsdFactoryid(),newPlmTlStandards.getPmsdSectionid(),newPlmTlStandards.getPmsdCellid(),newPlmTlStandards.getPmsdMachineid(),newPlmTlStandards.getPmsdAssemblyid()
						,newPlmTlStandards.getPmsdFrequencyunit(),newPlmTlStandards.getPmsdMouldid(),(newPlmTlStandards.getPmsdEffectivedate()).substring(3,6));
			}
			else
				CommonFunctions.debugMsg(" PM Calendar Not Genrated.....  ");
			return  plmTlStandards;
			
		}catch (ValidationExceptions e){
			CommonFunctions.debugMsg("validation"+e.getMessage());
             e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public BAL_PlmTlStandards update(BAL_PlmTlStandards newPlmTlStandards,
			BAL_PlmTlStandards existPlmTlStandards,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws ValidationExceptions,Exception {
		// TODO Auto-generated method stub
		 try {
			 	CommonFunctions.debugMsg("UDATE CALLED");
			    CommonFunctions.debugMsg("Update called");
				String validationsFor = "create";
				CommonFunctions.debugMsg("Inside the ServiceImpl update");
				validations.validate(newPlmTlStandards,"PmstandardCreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
				validations.validate(newPlmTlStandards,"PmstandardCreation","update");//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
				 CommonFunctions.debugMsg(" after validatiop " );
				/*for method*/
				 if(newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size()>0){
					List<BAL_PlmTlMethodsmst> newPlmTlmethodsmsts = newPlmTlStandards.getMethodDetail();
						for( BAL_PlmTlMethodsmst plmTlmethodsmst : newPlmTlmethodsmsts)
						{	
						    CommonFunctions.debugMsg(" methodname " + plmTlmethodsmst.getPmmsActivity());
							validations.validate(plmTlmethodsmst,"PmstandardCreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
						}
					 }
				/*for TOOLS*/
				 if(newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size()>0){
					List<BAL_PlmTlToolsdtl> plmTlToolsdtlList = newPlmTlStandards.getToolsDetail();
					for( BAL_PlmTlToolsdtl plmTlToolsdtl : plmTlToolsdtlList)
					{	
						CommonFunctions.debugMsg("TOOLS  " + plmTlToolsdtl.getPtldToolid());
						validations.validate(plmTlToolsdtl,"PmstandardCreation",validationsFor);//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
					}
				 }
				//**for counterMeasure**//
					BdmTlYycountermeasurelink counterMeasurelist = newPlmTlStandards.getCountermeasureLink();
				CommonFunctions.debugMsg("After");
				fillValues(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean);
				String dKeyId = plmTlStandardsFormBean.getBdmDockKey();
				BAL_PlmTlStandards plmTlStandards = plmTlStandardsDao.update(newPlmTlStandards,dKeyId);
	 		   return plmTlStandards;
				//return plmTlStandardsDao.update(newPlmTlStandards);	
			}catch (ValidationExceptions e){
				
				e.printStackTrace();
				CommonFunctions.debugMsg(e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}
	}
//fill values for saving in db if the value is null
//	private BAL_PlmTlStandards fillValues(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) {
//			// TODO Auto-generated method stub
//	         
//			newPlmTlStandards.setPmsdActive("Y");
//			CommonFunctions.debugMsg("lllllll  :");
//			
//			String dateTime = CommonFunctions.dateTimeNow();
//			CommonFunctions.debugMsg(dateTime);
//			if(newPlmTlStandards.getPmsdKeyid() == null )
//				newPlmTlStandards.setPmsdCreatedon(dateTime);
//			else
//				newPlmTlStandards.setPmsdCreatedon(dateTime);
//			
//			CommonFunctions.debugMsg(" --- "+dateTime);
//			newPlmTlStandards.setPmsdModifiedon(dateTime);
//			/*if( newPlmTlStandards.getPmsdAssemblyid() == null )
//			    //newPlmTlStandards.setClisAssemblyid("MMA/0155");
//				newPlmTlStandards.setPmsdAssemblyid("{}");*/
//			if(newPlmTlStandards.getPmsdActivitytype()== null)
//				newPlmTlStandards.setPmsdActivitytype("{}");
//			if(newPlmTlStandards.getPmsdActivitysubtype()== null)
//				newPlmTlStandards.setPmsdActivitysubtype("{}");
//			if(newPlmTlStandards.getPmsdCauseid()== null)
//				newPlmTlStandards.setPmsdCauseid("{}");
//			if(newPlmTlStandards.getPmsdCellid()== null)
//				newPlmTlStandards.setPmsdCellid("{}");
//			if(newPlmTlStandards.getPmsdCorrectiveaction()== null)
//				newPlmTlStandards.setPmsdCorrectiveaction("{}");
//			if(newPlmTlStandards.getPmsdCreatedby()== null)
//				newPlmTlStandards.setPmsdCreatedby("{}");
//			CommonFunctions.debugMsg(" created-- "+newPlmTlStandards.getPmsdCreatedby());
//			if(newPlmTlStandards.getPmsdDate()== null)
//				newPlmTlStandards.setPmsdDate(dateTime);
//			if(newPlmTlStandards.getPmsdBomid()== null)
//				newPlmTlStandards.setPmsdBomid("{}");
//			if(newPlmTlStandards.getPmsdEffectivedate()== null)
//				newPlmTlStandards.setPmsdEffectivedate(dateTime);
//			if(newPlmTlStandards.getPmsdFactoryid()== null)
//				newPlmTlStandards.setPmsdFactoryid("{}");
//			if(newPlmTlStandards.getPmsdFormatno()== null)
//				newPlmTlStandards.setPmsdFormatno("{}");
//			CommonFunctions.debugMsg(" formtn-- "+newPlmTlStandards.getPmsdFormatno());
//			if(newPlmTlStandards.getPmsdFrequency()== null)
//				newPlmTlStandards.setPmsdFrequency("0");
//			CommonFunctions.debugMsg(" freq-- "+newPlmTlStandards.getPmsdFrequency());
//			if(newPlmTlStandards.getPmsdFrequencyunit()== null)
//				newPlmTlStandards.setPmsdFrequencyunit("W");
//			if(!UIUtils.isValidKeyId(newPlmTlStandards.getPmsdHowmethod()))
//				newPlmTlStandards.setPmsdHowmethod("{}");
//			if(newPlmTlStandards.getPmsdDuration()== null)
//				newPlmTlStandards.setPmsdDuration("0");
//			if(newPlmTlStandards.getPmsdInactivateddate()== null)
//				newPlmTlStandards.setPmsdInactivateddate(Constants.passNullDate);
//			if(newPlmTlStandards.getPmsdIssftpermitreq()== null)
//				newPlmTlStandards.setPmsdIssftpermitreq("N");
//			if(newPlmTlStandards.getPmsdIssparesreq()== null)
//				newPlmTlStandards.setPmsdIssparesreq("N");
//			if(!UIUtils.isValidKeyId(newPlmTlStandards.getPmsdIstoolsreq()))
//				newPlmTlStandards.setPmsdIstoolsreq("N");
//			if(newPlmTlStandards.getPmsdRoutenumber()== null)
//				newPlmTlStandards.setPmsdRoutenumber("{}");
//			if(newPlmTlStandards.getPmsdIncludeinshutdownmaint()== null)
//				newPlmTlStandards.setPmsdIncludeinshutdownmaint("N");
//			if(newPlmTlStandards.getPmsdGroupno()== null)
//				newPlmTlStandards.setPmsdGroupno("{}");
//			/*if(newPlmTlStandards.getPmsdLastdonedate()== null)
//				newPlmTlStandards.setPmsdLastdonedate(Constants.passNullDate);
//			if(newPlmTlStandards.getPmsdLastdoneweekno()== null)
//				newPlmTlStandards.setPmsdLastdoneweekno("0");
//			if(newPlmTlStandards.getPmsdLastfeedbackdate()== null)
//				newPlmTlStandards.setPmsdLastfeedbackdate(Constants.passNullDate);
//			if(newPlmTlStandards.getPmsdLastfeedbackid()== null)
//				newPlmTlStandards.setPmsdLastfeedbackid("{}");
//			if(newPlmTlStandards.getPmsdLastwogendate()== null)
//				newPlmTlStandards.setPmsdLastwogendate(Constants.passNullDate);
//			if(newPlmTlStandards.getPmsdLastwoid()== null)
//				newPlmTlStandards.setPmsdLastwoid("{}");*/
//			if(newPlmTlStandards.getPmsdMachinecondition()== null)
//				newPlmTlStandards.setPmsdMachinecondition("S");
//			if(newPlmTlStandards.getPmsdMachineid()== null)
//				newPlmTlStandards.setPmsdMachineid("{}");
//			if(newPlmTlStandards.getPmsdMonthweekno()== null)
//				newPlmTlStandards.setPmsdMonthweekno("1");
//			if(newPlmTlStandards.getPmsdRelatedTo()== null)
//				newPlmTlStandards.setPmsdRelatedTo("MCH");
//			if(newPlmTlStandards.getPmsdMouldid()== null)
//				newPlmTlStandards.setPmsdMouldid("{}");
//			if(newPlmTlStandards.getPmsdLocationid() == null)
//				newPlmTlStandards.setPmsdLocationid("{}"); //tempfield3 change to locationID
//			if(newPlmTlStandards.getPmsdFlid() == null)
//				newPlmTlStandards.setPmsdFlid("{}"); 
//	
//			if(newPlmTlStandards.getPmsdElementid()== null)
//				newPlmTlStandards.setPmsdElementid("{}");
//	
//			if(newPlmTlStandards.getPmsdMaxValue()== null)
//				newPlmTlStandards.setPmsdMaxValue("0");
//			if(newPlmTlStandards.getPmsdMinValue()== null)
//				newPlmTlStandards.setPmsdMinValue("0");
//			if(newPlmTlStandards.getPmsdTarget()== null)
//				newPlmTlStandards.setPmsdTarget("0");
//			if(newPlmTlStandards.getPmsdTempfield8()== null)
//				newPlmTlStandards.setPmsdTempfield8("X");
//			if(newPlmTlStandards.getPmsdTempfield9()== null)
//				newPlmTlStandards.setPmsdTempfield9("X");
//			if(newPlmTlStandards.getPmsdTempfield10()== null)
//				newPlmTlStandards.setPmsdTempfield10("X");
//			CommonFunctions.debugMsg("mk  :"+newPlmTlStandards.getPmsdTempfield10());
//			/*if(newPlmTlStandards.getPmsdMinimumreading()== null)
//				newPlmTlStandards.setPmsdMinimumreading("0");
//			if(newPlmTlStandards.getPmsdMonthweekno()== null)
//				newPlmTlStandards.setPmsdMonthweekno("0");
//			if(newPlmTlStandards.getPmsdNextduedate()== null)
//				newPlmTlStandards.setPmsdNextduedate(Constants.passNullDate);
//			if(newPlmTlStandards.getPmsdNextdueweekno()== null)
//				newPlmTlStandards.setPmsdNextdueweekno("0");
//			if(newPlmTlStandards.getPmsdNoofpoints()== null)
//				newPlmTlStandards.setPmsdNoofpoints("0");*/
//			if(newPlmTlStandards.getPmsdPhenomenaid()== null)
//				newPlmTlStandards.setPmsdPhenomenaid("{}");
//			if(newPlmTlStandards.getPmsdPlanconfigstatus()== null)
//				newPlmTlStandards.setPmsdPlanconfigstatus("Y");
//			if(newPlmTlStandards.getPmsdEqpgroupid()== null)
//				newPlmTlStandards.setPmsdEqpgroupid("{}");
//			if(newPlmTlStandards.getPmsdPreparedbyid()== null)
//				newPlmTlStandards.setPmsdPreparedbyid("{}");
//			if(newPlmTlStandards.getPmsdRefdocno()== null)
//				newPlmTlStandards.setPmsdRefdocno("{}");
//			if(newPlmTlStandards.getPmsdRefdoctype()== null)
//				newPlmTlStandards.setPmsdRefdoctype("{}");
//			if(newPlmTlStandards.getPmsdPreparedbyid()== null)
//				newPlmTlStandards.setPmsdPreparedbyid("{}");
//			/*if(newPlmTlStandards.getPmsdResponsibleempid()== null)
//				newPlmTlStandards.setPmsdResponsibleempid("{}");*/
//			if(newPlmTlStandards.getPmsdResultifnotdone()== null)
//				newPlmTlStandards.setPmsdResultifnotdone("{}");
//			if(newPlmTlStandards.getPmsdSafetyinstruction()== null)
//				newPlmTlStandards.setPmsdSafetyinstruction("{}");
//			if(newPlmTlStandards.getPmsdSectionid()== null)
//				newPlmTlStandards.setPmsdSectionid("{}");
//			if(newPlmTlStandards.getPmsdSource()== null)
//				newPlmTlStandards.setPmsdSource("I");
//			if(newPlmTlStandards.getPmsdEffectivedate()== null)
//				newPlmTlStandards.setPmsdEffectivedate(Constants.passNullDate);
//			CommonFunctions.debugMsg("effdate   :"+newPlmTlStandards.getPmsdEffectivedate());
//			/*if(newPlmTlStandards.getPmsdStartweekno()== null)
//				newPlmTlStandards.setPmsdStartweekno("0");*/
//			if(newPlmTlStandards.getPmsdSubassemblyid()== null)
//				newPlmTlStandards.setPmsdSubassemblyid("{}");
//			if(newPlmTlStandards.getPmsdSupplierid()== null)
//				newPlmTlStandards.setPmsdSupplierid("{}");
//			if(newPlmTlStandards.getPmsdTradeid()== null)
//				newPlmTlStandards.setPmsdTradeid("{}");
//			if(newPlmTlStandards.getPmsdUomid()== null)
//				newPlmTlStandards.setPmsdUomid("{}");
//			if(newPlmTlStandards.getPmsdActivity()== null)
//				newPlmTlStandards.setPmsdActivity("{}");
//			if(newPlmTlStandards.getPmsdStandard()== null)
//				newPlmTlStandards.setPmsdStandard("{}");
//			if(newPlmTlStandards.getPmsdLocation()== null)
//				newPlmTlStandards.setPmsdLocation("{}");
//			if(newPlmTlStandards.getPmsdStandard()== null)
//				newPlmTlStandards.setPmsdStandard("{}");
//			if(newPlmTlStandards.getPmsdWogenflag()== null)
//				newPlmTlStandards.setPmsdWogenflag("N");
//			CommonFunctions.debugMsg("end fill");
//			if(newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size()>0)
//			newPlmTlStandards.setMethodDetail(refTablesFillValues(newPlmTlStandards,existPlmTlStandards));
//			CommonFunctions.debugMsg("end method");
//			if(newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size()>0)
//			newPlmTlStandards.setToolsDetail(refToolTablesFillValues(newPlmTlStandards,existPlmTlStandards));
//			CommonFunctions.debugMsg("end tool");
//			if(newPlmTlStandards.getPermitlinkDetail() != null && newPlmTlStandards.getPermitlinkDetail().size()>0)
//			newPlmTlStandards.setPermitlinkDetail(refPermitLinkFillValues(newPlmTlStandards,existPlmTlStandards));
//			//**for counterMeasure**//
//			
//			if(newPlmTlStandards.getCbmData() != null )
//				newPlmTlStandards.setCbmData(fillValuesCBM(newPlmTlStandards,existPlmTlStandards));
//			if(newPlmTlStandards.getCountermeasureLink() != null )
//				newPlmTlStandards.setCountermeasureLink(refCountermeasureFillValues(newPlmTlStandards,existPlmTlStandards));
//			//newPlmTlStandards.setplmTlMultipleResp(MultiRespfillvalues(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean));
//			CommonFunctions.debugMsg("end permLink");
//			return newPlmTlStandards;
//		}
	//mano
	private BAL_PlmTlStandards fillValues(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards,BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) {
		// TODO Auto-generated method stub
         
		newPlmTlStandards.setPmsdActive("Y");
		CommonFunctions.debugMsg("lllllll  :");
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		CommonFunctions.debugMsg(dateTime);
		if(newPlmTlStandards.getPmsdKeyid() == null )
			newPlmTlStandards.setPmsdCreatedon(dateTime);
		else
			newPlmTlStandards.setPmsdCreatedon(dateTime);
		
		CommonFunctions.debugMsg(" --- "+dateTime);
		newPlmTlStandards.setPmsdModifiedon(dateTime);
		/*if( newPlmTlStandards.getPmsdAssemblyid() == null )
		    //newPlmTlStandards.setClisAssemblyid("MMA/0155");
			newPlmTlStandards.setPmsdAssemblyid("{}");*/
		if(newPlmTlStandards.getPmsdActivitytype()== null)
			newPlmTlStandards.setPmsdActivitytype("{}");
		if(newPlmTlStandards.getPmsdActivitysubtype()== null)
			newPlmTlStandards.setPmsdActivitysubtype("{}");
		if(newPlmTlStandards.getPmsdCauseid()== null)
			newPlmTlStandards.setPmsdCauseid("{}");
		if(newPlmTlStandards.getPmsdCellid()== null)
			newPlmTlStandards.setPmsdCellid("{}");
		if(newPlmTlStandards.getPmsdCorrectiveaction()== null)
			newPlmTlStandards.setPmsdCorrectiveaction("{}");
		if(newPlmTlStandards.getPmsdCreatedby()== null)
			newPlmTlStandards.setPmsdCreatedby("{}");
		CommonFunctions.debugMsg(" created-- "+newPlmTlStandards.getPmsdCreatedby());
		if(newPlmTlStandards.getPmsdDate()== null)
			newPlmTlStandards.setPmsdDate(dateTime);
		if(newPlmTlStandards.getPmsdBomid()== null)
			newPlmTlStandards.setPmsdBomid("{}");
		String date = newPlmTlStandards.getPmsdEffectivedate();
		newPlmTlStandards.setPmsdEffectivedate(CommonFunctions.pg_getDateTimeFromPGTimeStamp(date));
		
		if(newPlmTlStandards.getPmsdEffectivedate()== null)
			newPlmTlStandards.setPmsdEffectivedate(dateTime);
		if(newPlmTlStandards.getPmsdFactoryid()== null)
			newPlmTlStandards.setPmsdFactoryid("{}");
		if(newPlmTlStandards.getPmsdFormatno()== null)
			newPlmTlStandards.setPmsdFormatno("{}");
		CommonFunctions.debugMsg(" formtn-- "+newPlmTlStandards.getPmsdFormatno());
		if(newPlmTlStandards.getPmsdFrequency()== null)
			newPlmTlStandards.setPmsdFrequency("0");
		CommonFunctions.debugMsg(" freq-- "+newPlmTlStandards.getPmsdFrequency());
		if(newPlmTlStandards.getPmsdFrequencyunit()== null)
			newPlmTlStandards.setPmsdFrequencyunit("W");
		if(!UIUtils.isValidKeyId(newPlmTlStandards.getPmsdHowmethod()))
			newPlmTlStandards.setPmsdHowmethod("{}");
		if(newPlmTlStandards.getPmsdDuration()== null)
			newPlmTlStandards.setPmsdDuration("0");
		if(newPlmTlStandards.getPmsdInactivateddate()== null)
			newPlmTlStandards.setPmsdInactivateddate(Constants.pgPassNullDateTime);
		if(newPlmTlStandards.getPmsdIssftpermitreq()== null)
			newPlmTlStandards.setPmsdIssftpermitreq("N");
		if(newPlmTlStandards.getPmsdIssparesreq()== null)
			newPlmTlStandards.setPmsdIssparesreq("N");
		if(!UIUtils.isValidKeyId(newPlmTlStandards.getPmsdIstoolsreq()))
			newPlmTlStandards.setPmsdIstoolsreq("N");
		if(newPlmTlStandards.getPmsdRoutenumber()== null)
			newPlmTlStandards.setPmsdRoutenumber("{}");
		if(newPlmTlStandards.getPmsdIncludeinshutdownmaint()== null)
			newPlmTlStandards.setPmsdIncludeinshutdownmaint("N");
		if(newPlmTlStandards.getPmsdGroupno()== null)
			newPlmTlStandards.setPmsdGroupno("{}");
		/*if(newPlmTlStandards.getPmsdLastdonedate()== null)
			newPlmTlStandards.setPmsdLastdonedate(Constants.passNullDate);
		if(newPlmTlStandards.getPmsdLastdoneweekno()== null)
			newPlmTlStandards.setPmsdLastdoneweekno("0");
		if(newPlmTlStandards.getPmsdLastfeedbackdate()== null)
			newPlmTlStandards.setPmsdLastfeedbackdate(Constants.passNullDate);
		if(newPlmTlStandards.getPmsdLastfeedbackid()== null)
			newPlmTlStandards.setPmsdLastfeedbackid("{}");
		if(newPlmTlStandards.getPmsdLastwogendate()== null)
			newPlmTlStandards.setPmsdLastwogendate(Constants.passNullDate);
		if(newPlmTlStandards.getPmsdLastwoid()== null)
			newPlmTlStandards.setPmsdLastwoid("{}");*/
		if(newPlmTlStandards.getPmsdMachinecondition()== null)
			newPlmTlStandards.setPmsdMachinecondition("S");
		if(newPlmTlStandards.getPmsdMachineid()== null)
			newPlmTlStandards.setPmsdMachineid("{}");
		if(newPlmTlStandards.getPmsdMonthweekno()== null)
			newPlmTlStandards.setPmsdMonthweekno("1");
		if(newPlmTlStandards.getPmsdRelatedTo()== null)
			newPlmTlStandards.setPmsdRelatedTo("MCH");
		if(newPlmTlStandards.getPmsdMouldid()== null)
			newPlmTlStandards.setPmsdMouldid("{}");
		if(newPlmTlStandards.getPmsdLocationid() == null)
			newPlmTlStandards.setPmsdLocationid("{}"); //tempfield3 change to locationID
		if(newPlmTlStandards.getPmsdFlid() == null)
			newPlmTlStandards.setPmsdFlid("{}"); 

		if(newPlmTlStandards.getPmsdElementid()== null)
			newPlmTlStandards.setPmsdElementid("{}");

		if(newPlmTlStandards.getPmsdMaxValue()== null)
			newPlmTlStandards.setPmsdMaxValue("0");
		if(newPlmTlStandards.getPmsdMinValue()== null)
			newPlmTlStandards.setPmsdMinValue("0");
		if(newPlmTlStandards.getPmsdTarget()== null)
			newPlmTlStandards.setPmsdTarget("0");
		if(newPlmTlStandards.getPmsdTempfield8()== null)
			newPlmTlStandards.setPmsdTempfield8("X");
		if(newPlmTlStandards.getPmsdTempfield9()== null)
			newPlmTlStandards.setPmsdTempfield9("X");
		if(newPlmTlStandards.getPmsdTempfield10()== null)
			newPlmTlStandards.setPmsdTempfield10("X");
		CommonFunctions.debugMsg("mk  :"+newPlmTlStandards.getPmsdTempfield10());
		/*if(newPlmTlStandards.getPmsdMinimumreading()== null)
			newPlmTlStandards.setPmsdMinimumreading("0");
		if(newPlmTlStandards.getPmsdMonthweekno()== null)
			newPlmTlStandards.setPmsdMonthweekno("0");
		if(newPlmTlStandards.getPmsdNextduedate()== null)
			newPlmTlStandards.setPmsdNextduedate(Constants.passNullDate);
		if(newPlmTlStandards.getPmsdNextdueweekno()== null)
			newPlmTlStandards.setPmsdNextdueweekno("0");
		if(newPlmTlStandards.getPmsdNoofpoints()== null)
			newPlmTlStandards.setPmsdNoofpoints("0");*/
		if(newPlmTlStandards.getPmsdPhenomenaid()== null)
			newPlmTlStandards.setPmsdPhenomenaid("{}");
		if(newPlmTlStandards.getPmsdPlanconfigstatus()== null)
			newPlmTlStandards.setPmsdPlanconfigstatus("Y");
		if(newPlmTlStandards.getPmsdEqpgroupid()== null)
			newPlmTlStandards.setPmsdEqpgroupid("{}");
		if(newPlmTlStandards.getPmsdPreparedbyid()== null)
			newPlmTlStandards.setPmsdPreparedbyid("{}");
		if(newPlmTlStandards.getPmsdRefdocno()== null)
			newPlmTlStandards.setPmsdRefdocno("{}");
		if(newPlmTlStandards.getPmsdRefdoctype()== null)
			newPlmTlStandards.setPmsdRefdoctype("{}");
		if(newPlmTlStandards.getPmsdPreparedbyid()== null)
			newPlmTlStandards.setPmsdPreparedbyid("{}");
		/*if(newPlmTlStandards.getPmsdResponsibleempid()== null)
			newPlmTlStandards.setPmsdResponsibleempid("{}");*/
		if(newPlmTlStandards.getPmsdResultifnotdone()== null)
			newPlmTlStandards.setPmsdResultifnotdone("{}");
		if(newPlmTlStandards.getPmsdSafetyinstruction()== null)
			newPlmTlStandards.setPmsdSafetyinstruction("{}");
		if(newPlmTlStandards.getPmsdSectionid()== null)
			newPlmTlStandards.setPmsdSectionid("{}");
		if(newPlmTlStandards.getPmsdSource()== null)
			newPlmTlStandards.setPmsdSource("I");
		if(newPlmTlStandards.getPmsdEffectivedate()== null)
			newPlmTlStandards.setPmsdEffectivedate(Constants.pgPassNullDateTime);
		CommonFunctions.debugMsg("effdate   :"+newPlmTlStandards.getPmsdEffectivedate());
		/*if(newPlmTlStandards.getPmsdStartweekno()== null)
			newPlmTlStandards.setPmsdStartweekno("0");*/
		if(newPlmTlStandards.getPmsdSubassemblyid()== null)
			newPlmTlStandards.setPmsdSubassemblyid("{}");
		if(newPlmTlStandards.getPmsdSupplierid()== null)
			newPlmTlStandards.setPmsdSupplierid("{}");
		if(newPlmTlStandards.getPmsdTradeid()== null)
			newPlmTlStandards.setPmsdTradeid("{}");
		if(newPlmTlStandards.getPmsdUomid()== null)
			newPlmTlStandards.setPmsdUomid("{}");
		if(newPlmTlStandards.getPmsdActivity()== null)
			newPlmTlStandards.setPmsdActivity("{}");
		if(newPlmTlStandards.getPmsdStandard()== null)
			newPlmTlStandards.setPmsdStandard("{}");
		if(newPlmTlStandards.getPmsdLocation()== null)
			newPlmTlStandards.setPmsdLocation("{}");
		
		if(newPlmTlStandards.getPmsdStandard()== null)
			newPlmTlStandards.setPmsdStandard("{}");
		if(newPlmTlStandards.getPmsdWogenflag()== null)
			newPlmTlStandards.setPmsdWogenflag("N");
		CommonFunctions.debugMsg("end fill");
		if(newPlmTlStandards.getMethodDetail() != null && newPlmTlStandards.getMethodDetail().size()>0)
		newPlmTlStandards.setMethodDetail(refTablesFillValues(newPlmTlStandards,existPlmTlStandards));
		CommonFunctions.debugMsg("end method");
		if(newPlmTlStandards.getToolsDetail() != null && newPlmTlStandards.getToolsDetail().size()>0)
		newPlmTlStandards.setToolsDetail(refToolTablesFillValues(newPlmTlStandards,existPlmTlStandards));
		CommonFunctions.debugMsg("end tool");
		if(newPlmTlStandards.getPermitlinkDetail() != null && newPlmTlStandards.getPermitlinkDetail().size()>0)
		newPlmTlStandards.setPermitlinkDetail(refPermitLinkFillValues(newPlmTlStandards,existPlmTlStandards));
		//**for counterMeasure**//
		
		if(newPlmTlStandards.getCbmData() != null )
			newPlmTlStandards.setCbmData(fillValuesCBM(newPlmTlStandards,existPlmTlStandards));
		if(newPlmTlStandards.getCountermeasureLink() != null )
			newPlmTlStandards.setCountermeasureLink(refCountermeasureFillValues(newPlmTlStandards,existPlmTlStandards));
		//newPlmTlStandards.setplmTlMultipleResp(MultiRespfillvalues(newPlmTlStandards,existPlmTlStandards,plmTlStandardsFormBean));
		CommonFunctions.debugMsg("end permLink");
		return newPlmTlStandards;
	}
	private List<BAL_PlmTlMultipleResp> MultiRespfillvalues(
		BAL_PlmTlStandards newPlmTlStandards, BAL_PlmTlStandards existPlmTlStandards,
		BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) {
		List<BAL_PlmTlMultipleResp> plmTlMultipleResp=newPlmTlStandards.getplmTlMultipleResp();
		CommonFunctions.debugMsg("Multiple resp size "+plmTlMultipleResp.size());
		List<BAL_PlmTlMultipleResp> plmTlMultipleRespArray = new ArrayList<BAL_PlmTlMultipleResp>();
		for( BAL_PlmTlMultipleResp plmTlMultipleRespList :plmTlMultipleResp)
		{
			if(plmTlMultipleRespList.getPmrsActive() == null)
				plmTlMultipleRespList.setPmrsActive("Y");
			if(plmTlMultipleRespList.getPmrsCompletedEmpid() == null)
				plmTlMultipleRespList.setPmrsCompletedEmpid(newPlmTlStandards.getPmsdCreatedby());
			plmTlMultipleRespArray.add(plmTlMultipleRespList);
		}
		return plmTlMultipleRespArray;
	
}

	private List<BAL_PlmTlCbmstdcadtl> fillValuesCBM(BAL_PlmTlStandards newPlmTlStandards,
			BAL_PlmTlStandards existPlmTlStandards) {
		// TODO Auto-generated method stub
		List<BAL_PlmTlCbmstdcadtl> plmTlCbmstdcadtlList = newPlmTlStandards.getCbmData();
		List<BAL_PlmTlCbmstdcadtl> oldplmTlCbmstdcadtlList = null;
		BAL_PlmTlCbmstdcadtl oldplmTlPmsftpermitlink = null;
		String dateTime = CommonFunctions.dateTimeNow();
		
		if( existPlmTlStandards != null)
		{
			oldplmTlCbmstdcadtlList = existPlmTlStandards.getCbmData();
		
		if( oldplmTlCbmstdcadtlList != null && oldplmTlCbmstdcadtlList.size() > 0 )
		   CommonFunctions.debugMsg("inside Cbmstd");
			oldplmTlPmsftpermitlink = oldplmTlCbmstdcadtlList.get(0);
		}
		List<BAL_PlmTlCbmstdcadtl> newPlmTlCbmstdcadtlList = new ArrayList<BAL_PlmTlCbmstdcadtl >();
		for( BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl : plmTlCbmstdcadtlList)
		{		
			if(newPlmTlCbmstdcadtl.getCmdtKeyid() == null )
				newPlmTlCbmstdcadtl.setCmdtCreatedon(dateTime);
			else
				newPlmTlCbmstdcadtl.setCmdtCreatedon(dateTime);
			newPlmTlCbmstdcadtl.setCmdtActive("Y");
			newPlmTlCbmstdcadtl.setCmdtCreatedby(newPlmTlStandards.getPmsdCreatedby());
			CommonFunctions.debugMsg(" --- "+dateTime);
			newPlmTlCbmstdcadtl.setCmdtModifiedon(dateTime);
			if( newPlmTlCbmstdcadtl.getCmdtCbmcondition() == null )
				newPlmTlCbmstdcadtl.setCmdtCbmcondition("-");
			if( newPlmTlCbmstdcadtl.getCmdtCorrectiveaction() == null )
				newPlmTlCbmstdcadtl.setCmdtCorrectiveaction("{}");
			if( newPlmTlCbmstdcadtl.getCmdtDesirablereading() == null )
				newPlmTlCbmstdcadtl.setCmdtDesirablereading("0");
			if( newPlmTlCbmstdcadtl.getCmdtInspectionid() == null )
				newPlmTlCbmstdcadtl.setCmdtInspectionid("{}");
			if( newPlmTlCbmstdcadtl.getCmdtKeyid() == null )
				newPlmTlCbmstdcadtl.setCmdtKeyid("{}");
			if( newPlmTlCbmstdcadtl.getCmdtLowerlimit() == null )
				newPlmTlCbmstdcadtl.setCmdtLowerlimit("0");
			if( newPlmTlCbmstdcadtl.getCmdtMeasuringmethod() == null )
				newPlmTlCbmstdcadtl.setCmdtMeasuringmethod("{}");
			if( newPlmTlCbmstdcadtl.getCmdtPmstandardid() == null )
				newPlmTlCbmstdcadtl.setCmdtPmstandardid("{}");
			if( newPlmTlCbmstdcadtl.getCmdtUomid() == null )
				newPlmTlCbmstdcadtl.setCmdtUomid("{}");
			if( newPlmTlCbmstdcadtl.getCmdtUpperlimit() == null )
				newPlmTlCbmstdcadtl.setCmdtUpperlimit("0");
			if( newPlmTlCbmstdcadtl.getCmdtZonecolor() == null )
				newPlmTlCbmstdcadtl.setCmdtZonecolor("{}");
			if( newPlmTlCbmstdcadtl.getCmdtZoneid() == null )
				newPlmTlCbmstdcadtl.setCmdtZoneid("{}");
			
			newPlmTlCbmstdcadtlList.add(newPlmTlCbmstdcadtl);
		 }
		CommonFunctions.debugMsg("end CBM FILLVALUES");
		return newPlmTlCbmstdcadtlList;
	}
	
	private BdmTlYycountermeasurelink refCountermeasureFillValues(
			BAL_PlmTlStandards newPlmTlStandards, BAL_PlmTlStandards existPlmTlStandards) {
		// TODO Auto-generated method stub
		BdmTlYycountermeasurelink bdmTlYycountermeasure = newPlmTlStandards.getCountermeasureLink();
	
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonFunctions.debugMsg("Counter m  sertghjk");
		
		
			if(bdmTlYycountermeasure.getYycmKeyid() == null )			
			{	
				
				bdmTlYycountermeasure.setYycmCreatedon(dateTime);
			}	
			
			
			
			bdmTlYycountermeasure.setYycmActive("Y");
			bdmTlYycountermeasure.setYycmCreatedby(newPlmTlStandards.getPmsdCreatedby());
			
			
			bdmTlYycountermeasure.setYycmModifieyon(dateTime);
			if(bdmTlYycountermeasure.getYycmWoid()== null)
				bdmTlYycountermeasure.setYycmWoid("{}");
			
			if(bdmTlYycountermeasure.getYycmCountermsrid()== null)
				bdmTlYycountermeasure.setYycmCountermsrid("{}");		
			CommonFunctions.debugMsg("stdpermlink   "+bdmTlYycountermeasure.getYycmCountermsrid());
			
			CommonFunctions.debugMsg("permid   "+bdmTlYycountermeasure.getYycmKeyid());
			if(bdmTlYycountermeasure.getYycmRefdoctype()== null)
				bdmTlYycountermeasure.setYycmRefdoctype("{}");
			
			if(bdmTlYycountermeasure.getYycmTempfield1()== null)
				bdmTlYycountermeasure.setYycmTempfield1("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield2()== null)
				bdmTlYycountermeasure.setYycmTempfield2("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield3()== null)
				bdmTlYycountermeasure.setYycmTempfield3("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield4()== null)
				bdmTlYycountermeasure.setYycmTempfield4("-");
			
			if(bdmTlYycountermeasure.getYycmTempfield5()== null)
				bdmTlYycountermeasure.setYycmTempfield5("-");
			
		return bdmTlYycountermeasure;
	}
	
	private List<BAL_PlmTlPmsftpermitlink> refPermitLinkFillValues(BAL_PlmTlStandards newPlmTlStandards, BAL_PlmTlStandards existPlmTlStandards) {
		// TODO Auto-generated method stub
		List<BAL_PlmTlPmsftpermitlink> plmTlPmsftpermitlinkList = newPlmTlStandards.getPermitlinkDetail();
		List<BAL_PlmTlPmsftpermitlink> oldplmTlPmsftpermitlinkList = null;
		BAL_PlmTlPmsftpermitlink oldplmTlPmsftpermitlink = null;
		String dateTime = CommonFunctions.dateTimeNow();
		
		if( existPlmTlStandards != null)
		{
			oldplmTlPmsftpermitlinkList = existPlmTlStandards.getPermitlinkDetail();
		
		if( oldplmTlPmsftpermitlinkList != null && oldplmTlPmsftpermitlinkList.size() > 0 )
		   CommonFunctions.debugMsg("inside permlink");
			oldplmTlPmsftpermitlink = oldplmTlPmsftpermitlinkList.get(0);
		}
		List<BAL_PlmTlPmsftpermitlink> newPlmTlPmsftpermitlinkList = new ArrayList<BAL_PlmTlPmsftpermitlink >();
		for( BAL_PlmTlPmsftpermitlink plmTlPmsftpermitlink : plmTlPmsftpermitlinkList)
		{		
			
			   
			if(plmTlPmsftpermitlink.getPsplPmstandardid()== null)
				plmTlPmsftpermitlink.setPsplPmstandardid("{}");		
			CommonFunctions.debugMsg("stdpermlink   "+plmTlPmsftpermitlink.getPsplPmstandardid());
			if(plmTlPmsftpermitlink.getPsplSftpermitid()== null)
				plmTlPmsftpermitlink.setPsplSftpermitid("{}");
			CommonFunctions.debugMsg("permid   "+plmTlPmsftpermitlink.getPsplSftpermitid());
			if(plmTlPmsftpermitlink.getPsplSftpermittype()== null)
				plmTlPmsftpermitlink.setPsplSftpermittype("{}");
			
			if(plmTlPmsftpermitlink.getPsplTempfield1()== null)
				plmTlPmsftpermitlink.setPsplTempfield1("{}");
			
			newPlmTlPmsftpermitlinkList.add(plmTlPmsftpermitlink);
		 }
		return newPlmTlPmsftpermitlinkList;
	}
	
	/**for tools**/
	private List<BAL_PlmTlToolsdtl> refToolTablesFillValues(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards) 
	{
		List<BAL_PlmTlToolsdtl> plmTlToolsdtlList = newPlmTlStandards.getToolsDetail();
		List<BAL_PlmTlToolsdtl> oldplmTlToolsdtlList = null;
		BAL_PlmTlToolsdtl oldPlmTlToolsdtl = null;
		String dateTime = CommonFunctions.dateTimeNow();
	
		if( existPlmTlStandards != null)
		{
			oldplmTlToolsdtlList = existPlmTlStandards.getToolsDetail();
		
		if( oldplmTlToolsdtlList != null && oldplmTlToolsdtlList.size() > 0 )
		   
			oldPlmTlToolsdtl = oldplmTlToolsdtlList.get(0);
		}
		List<BAL_PlmTlToolsdtl> newPlmTlToolsdtlList = new ArrayList<BAL_PlmTlToolsdtl>();
		for( BAL_PlmTlToolsdtl plmTlToolsdtl : plmTlToolsdtlList)
		{	
			if(plmTlToolsdtl.getPtldKeyid() == null )			
			{	
				
				plmTlToolsdtl.setPtldCreatedon(dateTime);
			}	
			else{
				plmTlToolsdtl.setPtldCreatedon(oldPlmTlToolsdtl.getPtldCreatedon());
			}
			plmTlToolsdtl.setPtldModifiedon(dateTime);
			
			System.out.println("toolDetail getPtldModifiedon  : " + plmTlToolsdtl.getPtldModifiedon());
			System.out.println("toolDetail getPtldStandardid  : " + plmTlToolsdtl.getPtldStandardid());
			System.out.println("toolDetail getPtldToolid      : " + plmTlToolsdtl.getPtldToolid());
			
			plmTlToolsdtl.setPtldActive("Y");
			plmTlToolsdtl.setPtldCreatedby(newPlmTlStandards.getPmsdCreatedby());
			
			
			plmTlToolsdtl.setPtldModifiedon(dateTime);
			System.out.println("toolDetailafter active" + plmTlToolsdtl.getPtldModifiedon());
			
			   
			if(plmTlToolsdtl.getPtldToolid()== null)
				plmTlToolsdtl.setPtldToolid("{}");		
			
			if(plmTlToolsdtl.getPtldStandardid()== null)
				plmTlToolsdtl.setPtldStandardid("{}");
			
			newPlmTlToolsdtlList.add(plmTlToolsdtl);
		 }
		return newPlmTlToolsdtlList;
	}
	/**end of tools **/
	 //for filling method table
	private List<BAL_PlmTlMethodsmst> refTablesFillValues(BAL_PlmTlStandards newPlmTlStandards,BAL_PlmTlStandards existPlmTlStandards) 
	{
		System.out.println("Detail 1");
		String dateTime = CommonFunctions.dateTimeNow();
	  
		
		/***method master***/
		List<BAL_PlmTlMethodsmst> newPlmTlmethodsmsts = newPlmTlStandards.getMethodDetail();
		List<BAL_PlmTlMethodsmst> oldPlmTlMultiplemethodsmsts = null;
		BAL_PlmTlMethodsmst oldPlmTlMultiplemethodsmst  = null;
		System.out.println("methodDetail 2");
		
		if( existPlmTlStandards != null)
		{
			oldPlmTlMultiplemethodsmsts = existPlmTlStandards.getMethodDetail();
		
		if( oldPlmTlMultiplemethodsmsts != null && oldPlmTlMultiplemethodsmsts.size() > 0 )
		   
			oldPlmTlMultiplemethodsmst = oldPlmTlMultiplemethodsmsts.get(0);
		}	
		System.out.println("Detail 3");
		List<BAL_PlmTlMethodsmst> newPlmTlMultiplemethodsmstList = new ArrayList<BAL_PlmTlMethodsmst>();
		for( BAL_PlmTlMethodsmst plmTlmethodsmst : newPlmTlmethodsmsts)
		{	
			if(plmTlmethodsmst.getPmmsKeyid() == null )			
			{	
				
				plmTlmethodsmst.setPmmsCreatedon(dateTime);
				//newPlmTlStandards.setPmsdStartdate(newPlmTlStandards.getPmsdEffectivedate());
			}	
			else{
				plmTlmethodsmst.setPmmsCreatedon(oldPlmTlMultiplemethodsmst.getPmmsCreatedon());
				//newPlmTlStandards.setPmsdStartdate(existPlmTlStandards.getPmsdEffectivedate());
			}
			plmTlmethodsmst.setPmmsModifiedon(dateTime);
			System.out.println("method Detailbefor active  :-" + plmTlmethodsmst.getPmmsModifiedon());
			plmTlmethodsmst.setPmmsActive("Y");
			plmTlmethodsmst.setPmmsCreatedby(newPlmTlStandards.getPmsdCreatedby());
			
			
			plmTlmethodsmst.setPmmsModifiedon(dateTime);
			System.out.println("method Detailafter active       :-" + plmTlmethodsmst.getPmmsModifiedon());
			System.out.println("Methoddescription after active  :-" + plmTlmethodsmst.getPmmsActivity());
			System.out.println("MethodDuration after active     :-" + plmTlmethodsmst.getPmmsDuration());
			if(plmTlmethodsmst.getPmmsDuration()== null)
			   plmTlmethodsmst.setPmmsDuration("0");
			  
			if(plmTlmethodsmst.getPmmsActivity()== null)
			   plmTlmethodsmst.setPmmsActivity("{}");		
			  
			
			   
			if(plmTlmethodsmst.getPmmsInstructions()== null)
			   plmTlmethodsmst.setPmmsInstructions("{}");
			   
			if(plmTlmethodsmst.getPmmsPermittype()== null)
			   plmTlmethodsmst.setPmmsPermittype("{}");
			
			if(plmTlmethodsmst.getPmmsProtectiveequipements()== null)
			   plmTlmethodsmst.setPmmsProtectiveequipements("{}");
			
			if(plmTlmethodsmst.getPmmsPmkeyid()== null)
				   plmTlmethodsmst.setPmmsPmkeyid("{}");
			
			if(plmTlmethodsmst.getPmmsSafetyinstruction()== null)
				   plmTlmethodsmst.setPmmsSafetyinstruction("{}");
			
			if(plmTlmethodsmst.getPmmsStandards()== null)
				   plmTlmethodsmst.setPmmsStandards("{}");
			
		
			if(plmTlmethodsmst.getPmmsTempfield1()== null)
				   plmTlmethodsmst.setPmmsTempfield1("T");
			
			if(plmTlmethodsmst.getPmmsTempfield2()== null)
				   plmTlmethodsmst.setPmmsTempfield2("T");
			
			if(plmTlmethodsmst.getPmmsTempfield3()== null)
				   plmTlmethodsmst.setPmmsTempfield3("T");
			
			if(plmTlmethodsmst.getPmmsTempfield4()== null)
				   plmTlmethodsmst.setPmmsTempfield4("T");
			
			if(plmTlmethodsmst.getPmmsTempfield5()== null)
				   plmTlmethodsmst.setPmmsTempfield5("T");
			
			if(plmTlmethodsmst.getPmmsWorkpermitrequired()== null)
				   plmTlmethodsmst.setPmmsWorkpermitrequired("N");
			
			   newPlmTlMultiplemethodsmstList.add(plmTlmethodsmst);
			  
		   }
		return newPlmTlMultiplemethodsmstList;
		}
	//end of method table
	@Override
	public List<String[]> getAllgridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		//List<String[]> getAllgridData();
		return plmTlStandardsDao.getAllgridData(commonFilter);
	}
	
	@Override
	public BAL_PlmTlStandards getFillValue(String pmstdKeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getFillValue(pmstdKeyid);
	}
	
	@Override
	public List<String[]> getAllsprGriddata(String pmsdkeyid) {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getAllsprGriddata(pmsdkeyid);
	}
	
	@Override
	public List<String[]> getSprPickup(String standardId) {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getSprPickup(standardId);
	}
	
	@Override
	public List<String[]> getactSubValue(String replActSub) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getactSubValue(replActSub);
	}
	
	@Override
	public List<String[]> getAlljhclittools(String toolpmsdid) {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getAlljhclittools(toolpmsdid) ;
	}
	
	@Override
	public List<String[]> getchkplnexists(String machorasswise, String machineId) {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getchkplnexists(machorasswise,machineId);
	}
	
	@Override
	public List<String[]> getAllgridassmData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getAllgridassmData(commonFilter);
	}
	
	@Override
	public List<String[]> getassmgridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getassmgridData(commonFilter);
	}
	
	@Override
	public String delSpares(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.delSpares(pmstdId);
	}
	
	@Override
	public String delTool(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.delTool(pmstdId);
	}
	
	@Override
	public List<String[]> getCBM(String pmStandardId) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getCBM(pmStandardId);
	}
	
	@Override
	public BAL_PlmTlCbmstdcadtl createCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			BAL_PlmTlCbmstdcadtl existPlmTlCbmstdcadtl,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws Exception {
		// TODO Auto-generated method stub
		/*****/
		CommonFunctions.debugMsg("CREATE CALLED CBM");
		try {
			CommonFunctions.debugMsg("Service IMPLb CBM");
			String validationsFor;
			validationsFor = "create";
			validations.validate(newPlmTlCbmstdcadtl,"PmstandardCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			
			CommonFunctions.debugMsg("After validation");
		
			
			//fillValuesCBM(newPlmTlCbmstdcadtl,existPlmTlCbmstdcadtl,plmTlStandardsFormBean);
			CommonFunctions.debugMsg("After Filling tool Values" );
			String dKeyId = plmTlStandardsFormBean.getBdmDockKey();
			//for inserting in clisCalendar procedure
			BAL_PlmTlCbmstdcadtl plmTlStandards = plmTlStandardsDao.createCBM(newPlmTlCbmstdcadtl,dKeyId);
	
			return  plmTlStandards;
			
		}catch (ValidationExceptions e){
			CommonFunctions.debugMsg("validation"+e.getMessage());
	         e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}
	}
	
	/*private PlmTlCbmstdcadtl fillValuesCBM(PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,PlmTlCbmstdcadtl existPlmTlCbmstdcadtl,
			PlmTlStandardsFormBean plmTlStandardsFormBean) {
		// TODO Auto-generated method stub
		newPlmTlCbmstdcadtl.setCmdtActive("Y");
		CommonFunctions.debugMsg("lllllll  :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonFunctions.debugMsg(dateTime);
		if(newPlmTlCbmstdcadtl.getCmdtKeyid() == null )
			newPlmTlCbmstdcadtl.setCmdtCreatedon(dateTime);
		else
			newPlmTlCbmstdcadtl.setCmdtCreatedon(dateTime);
		
		CommonFunctions.debugMsg(" --- "+dateTime);
		newPlmTlCbmstdcadtl.setCmdtModifiedon(dateTime);
		if( newPlmTlCbmstdcadtl.getCmdtCbmcondition() == null )
			newPlmTlCbmstdcadtl.setCmdtCbmcondition("-");
		if( newPlmTlCbmstdcadtl.getCmdtCorrectiveaction() == null )
			newPlmTlCbmstdcadtl.setCmdtCorrectiveaction("{}");
		if( newPlmTlCbmstdcadtl.getCmdtDesirablereading() == null )
			newPlmTlCbmstdcadtl.setCmdtDesirablereading("0");
		if( newPlmTlCbmstdcadtl.getCmdtInspectionid() == null )
			newPlmTlCbmstdcadtl.setCmdtInspectionid("{}");
		if( newPlmTlCbmstdcadtl.getCmdtKeyid() == null )
			newPlmTlCbmstdcadtl.setCmdtKeyid("{}");
		if( newPlmTlCbmstdcadtl.getCmdtLowerlimit() == null )
			newPlmTlCbmstdcadtl.setCmdtLowerlimit("0");
		if( newPlmTlCbmstdcadtl.getCmdtMeasuringmethod() == null )
			newPlmTlCbmstdcadtl.setCmdtMeasuringmethod("{}");
		if( newPlmTlCbmstdcadtl.getCmdtPmstandardid() == null )
			newPlmTlCbmstdcadtl.setCmdtPmstandardid("{}");
		if( newPlmTlCbmstdcadtl.getCmdtUomid() == null )
			newPlmTlCbmstdcadtl.setCmdtUomid("{}");
		if( newPlmTlCbmstdcadtl.getCmdtUpperlimit() == null )
			newPlmTlCbmstdcadtl.setCmdtUpperlimit("0");
		if( newPlmTlCbmstdcadtl.getCmdtZonecolor() == null )
			newPlmTlCbmstdcadtl.setCmdtZonecolor("{}");
		if( newPlmTlCbmstdcadtl.getCmdtZoneid() == null )
			newPlmTlCbmstdcadtl.setCmdtZoneid("{}");
		CommonFunctions.debugMsg("end CBM FILLVALUES");
		return newPlmTlCbmstdcadtl;
	}*/
	
	@Override
	public BAL_PlmTlCbmstdcadtl updateCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			BAL_PlmTlCbmstdcadtl existPlmTlCbmstdcadtl,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws Exception {
		// TODO Auto-generated method stub
		 try {
			 	CommonFunctions.debugMsg("UDATE CALLED");
			    CommonFunctions.debugMsg("Update called");
				String validationsFor = "create";
				CommonFunctions.debugMsg("Inside the ServiceImpl update");
				validations.validate(newPlmTlCbmstdcadtl,"PmstandardCreation","update");//com.akranta.validations.tpm.validations.clitcreation.xml - defined rules for server side validations
				 CommonFunctions.debugMsg(" after validatiop " );
				
				
				CommonFunctions.debugMsg("After");
				//fillValuesCBM(newPlmTlCbmstdcadtl,existPlmTlCbmstdcadtl,plmTlStandardsFormBean);
				String dKeyId = plmTlStandardsFormBean.getBdmDockKey();
				BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = plmTlStandardsDao.updateCBM(newPlmTlCbmstdcadtl,dKeyId);
			   return plmTlCbmstdcadtl;
				//return plmTlStandardsDao.update(newPlmTlStandards);	
			}catch (ValidationExceptions e){
				
				e.printStackTrace();
				CommonFunctions.debugMsg(e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}
	}
	
	@Override
	public BAL_PlmTlCbmstdcadtl getFillValueCMB(String pmsdId) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getFillValueCMB( pmsdId);
	}
	
	@Override
	public List<String[]> getPermitLinkData(String pmStandardId) throws Exception {
		// TODO Auto-generated method stub
		return  plmTlStandardsDao.getPermitLinkData( pmStandardId);
	}
	
	@Override
	public List<String[]> getfillJobType() throws Exception {
		return  plmTlStandardsDao.getfillJobType();
	}
	@Override
	public String sdmGenrateCal(String effectivedate, String factoryid,
			String sectionid, String cellid, String machineid, String assembly,
			String frequency, String locationid) throws Exception {
		// TODO Auto-generated method stub
		//generatePmcalendar(effectivedate, factoryid, sectionid,cellid,machineid,assembly, frequency, locationid);
		return "gen";
	}
	
	@Override
	public BAL_PlmTlShutdowncal createSDM(BAL_PlmTlShutdowncal newPlmTlShutdowncal,
			BAL_PlmTlShutdowncal existPlmTlShutdowncal,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) throws Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("CREATE CALLED SDM");
		try {
			CommonFunctions.debugMsg("Service IMPLb CBM");
			String validationsFor;
			
			CommonFunctions.debugMsg("After validation");
		
			
			 fillValuesSDM(newPlmTlShutdowncal,existPlmTlShutdowncal,plmTlStandardsFormBean);
			CommonFunctions.debugMsg("After Filling tool Values" );
			 
			
			BAL_PlmTlShutdowncal plmTlShutdowncal = plmTlShutdowncalDao.create(newPlmTlShutdowncal );
	
			return  plmTlShutdowncal;
			
		}catch (ValidationExceptions e){
			CommonFunctions.debugMsg("validation"+e.getMessage());
	         e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}
	}
	
	private BAL_PlmTlShutdowncal fillValuesSDM(BAL_PlmTlShutdowncal newPlmTlShutdowncal,
			BAL_PlmTlShutdowncal existPlmTlShutdowncal,
			BAL_PlmTlStandardsFormBean plmTlStandardsFormBean) {
		// TODO Auto-generated method stub
		newPlmTlShutdowncal.setSdclActive("Y");
		CommonFunctions.debugMsg("lllllll  :");
		
		String dateTime = CommonFunctions.dateTimeNow();
		CommonFunctions.debugMsg(dateTime);
		if(newPlmTlShutdowncal.getSdclKeyid() == null )
			newPlmTlShutdowncal.setSdclCreatedon(dateTime);
		else
			newPlmTlShutdowncal.setSdclCreatedon(dateTime);
		
		CommonFunctions.debugMsg(" --- "+dateTime);
		newPlmTlShutdowncal.setSdclModifiedon(dateTime);
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclAssemblyid()))
			newPlmTlShutdowncal.setSdclAssemblyid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclCellid()))
			newPlmTlShutdowncal.setSdclCellid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclCompanyid()))
			newPlmTlShutdowncal.setSdclCompanyid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclFactoryid()))
			newPlmTlShutdowncal.setSdclFactoryid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclFrequnit()))
			newPlmTlShutdowncal.setSdclFrequnit("-");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclFromdate()))
			newPlmTlShutdowncal.setSdclFromdate(dateTime);
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclLocationid()))
			newPlmTlShutdowncal.setSdclLocationid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclMachineid()))
			newPlmTlShutdowncal.setSdclMachineid("{}");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclSectionid()))
			newPlmTlShutdowncal.setSdclSectionid("{}");
	 
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclElementid()))
			newPlmTlShutdowncal.setSdclElementid("-");
	 
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclTempfield3()))
			newPlmTlShutdowncal.setSdclTempfield3("-");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclTilldate()))
			newPlmTlShutdowncal.setSdclTilldate(dateTime);
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclRelatedto()))
			newPlmTlShutdowncal.setSdclRelatedto("MCH");
		if(!UIUtils.isValidKeyId(newPlmTlShutdowncal.getSdclMouldid()))
			newPlmTlShutdowncal.setSdclMouldid("{}");
		return newPlmTlShutdowncal;
	}
	
	@Override
	public List<String[]> getfillActivity() throws Exception {
		// TODO Auto-generated method stub
		return  plmTlStandardsDao.getfillActivity();
	}
	
	 
	
	 
	
	@Override
	public List<String[]> getCbmGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return  plmTlStandardsDao.getCbmGrid(commonFilter);
	}
	
	@Override
	public List<String[]> getPMReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getPMReport(commonFilter);
	}
	
	public List<String[]> getMethodTaskList(CommonFilter commonFilter)throws Exception{
		return plmTlStandardsDao.getMethodTaskList(commonFilter);
	}

	@Override
	public BAL_PlmTlMethodtasklist createMethodTask(
			BAL_PlmTlMethodtasklist newPlmTlMethodtasklist) throws Exception {
		// TODO Auto-generated method stub
		fillvaluesTaskList(newPlmTlMethodtasklist);
		return plmTlStandardsDao.createMethodTask(newPlmTlMethodtasklist);
	}

	private List<BAL_PlmTlMethodtasklist> fillvaluesTaskList(BAL_PlmTlMethodtasklist newPlmTlMethodtasklist) {
		// TODO Auto-generated method stub
		List<BAL_PlmTlMethodtasklist> plmMethodTaskList = new ArrayList<BAL_PlmTlMethodtasklist>();
		CommonFunctions.debugMsg("machine Id  "+newPlmTlMethodtasklist.getMtskMachineid());
		for(BAL_PlmTlMethodtasklist plmTlMethodtaskList :newPlmTlMethodtasklist.getPlmTlMethodtasklist()){
			String dateTime = CommonFunctions.dateTimeNow();
			  
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskActualcondition()))
				plmTlMethodtaskList.setMtskActualcondition("{}");
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskCheckingtool() ))
				plmTlMethodtaskList.setMtskCheckingtool("{}");
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskIdealcondition() ))
				plmTlMethodtaskList.setMtskIdealcondition("{}");
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskMachineid())){
			/*	plmTlMethodtaskList.setMtskMachineid("{}");
			else{*/
				plmTlMethodtaskList.setMtskMachineid(newPlmTlMethodtasklist.getMtskMachineid());
			}
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskOperation()))
				plmTlMethodtaskList.setMtskOperation("{}");
			if(!UIUtils.isValidKeyId(plmTlMethodtaskList.getMtskTypeofcheck()))
				plmTlMethodtaskList.setMtskTypeofcheck("{}");
			
			if( UIUtils.isValidKeyId(plmTlMethodtaskList.getIdCriteria() ))
				plmTlMethodtaskList.setMtskIdealcondition(plmTlMethodtaskList.getIdCriteria());
			if( UIUtils.isValidKeyId(plmTlMethodtaskList.getIddirectVal() ))
				plmTlMethodtaskList.setMtskIdealcondition(plmTlMethodtaskList.getIddirectVal());
			if( UIUtils.isValidKeyId(plmTlMethodtaskList.getIdMaxVal()))
				plmTlMethodtaskList.setMtskIdealcondition(plmTlMethodtaskList.getIdMinVal()+"-"+plmTlMethodtaskList.getIdMaxVal());
			
			plmMethodTaskList.add(plmTlMethodtaskList);
		}
		 return plmMethodTaskList;
	}
	
	// To copy Standards
	
	@Override
	public List<String[]> getEquipmentList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return plmTlStandardsDao.getEquipmentList(commonFilter);
	}

	@Override
	public String getEquipflid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return  plmTlStandardsDao.getEquipflid(commonFilter);
	}

	@Override
	public List<String[]> copyStandards(List<String> eqlist, List<String> actlst,String elementid,String tradeid)
			throws Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg(" In side The daoimpl tradeid  "+tradeid);
		CommonFunctions.debugMsg(" In side The daoimpl elementid  "+elementid);
		
		return plmTlStandardsDao.copyStandards(eqlist,actlst,elementid,tradeid);
	}
	public Workbook stdExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception{


return plmTlStandardsDao.stdExportExcel(commonFilter,tblJSONObj,format);
}
	public String delteStandards(String detailKeyid,String cellId, String machineId)throws Exception {
		return plmTlStandardsDao.delteStandards(detailKeyid,cellId,machineId);
	}
	@Override
	public List<String[]> getMultiplePmsdList(CommonFilter commonFilter) throws Exception {
	    return plmTlStandardsDao.getMultiplePmsdList(commonFilter);
	}
	@Override
	public BAL_PlmTlStandards delete(BAL_PlmTlStandards plmTlStandards) throws Exception
	{
	    System.out.println("delete");
	    balpmstandards.deletePlmTlStandards(plmTlStandards.getPmsdKeyid());
	    return plmTlStandards;
	}
}
