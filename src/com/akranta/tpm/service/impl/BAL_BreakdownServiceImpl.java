
package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlDtlDao;
import com.akranta.tpm.dao.BAL_BdmTlMstDao;
import com.akranta.tpm.dao.BAL_BdmTlWhywhymstDao;
import com.akranta.tpm.dao.BAL_BreakdownDao;
import com.akranta.tpm.dao.BAL_CommonFilterDao;
import com.akranta.tpm.dao.impl.BAL_BdmTlDtlDaoImpl;
import com.akranta.tpm.dao.impl.BAL_BdmTlMstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_BdmTlWhywhymstDaoImpl;
import com.akranta.tpm.dao.impl.BAL_BreakdownDaoImpl;
import com.akranta.tpm.dao.impl.BAL_CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BAL_BdmTlNewphncausereq;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlMachineCalTime;
import com.akranta.tpm.model.BAL_PcsTlPcsBd;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.BAL_BreakdownService;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.service.api.BdmServiceApi;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;

public class BAL_BreakdownServiceImpl implements BAL_BreakdownService {

	private BAL_BreakdownDao breakdownDao;
	private BAL_BdmTlMstDao bdmTlMstDao;
	private BAL_BdmTlDtlDao bdmTlDtlDao;
	private BAL_BdmTlWhywhymstDao bdmTlWhywhymstDao;
//private BdmTlNewphncausereqDao bdmTlNewphncausereqDao;
//private BdmTlPhenomenamstDao bdmTlPhenomenamstDao;
	private BAL_CommonFilterDao commonFilterDao;
	private BdmServiceApi bdmServiceApi;
	private Validations validations;

	public BAL_BreakdownServiceImpl(DBActionTemplate dbActionTemplate) {
		breakdownDao = new BAL_BreakdownDaoImpl(dbActionTemplate);
		bdmTlMstDao = new BAL_BdmTlMstDaoImpl(dbActionTemplate);
		bdmTlDtlDao = new BAL_BdmTlDtlDaoImpl(dbActionTemplate);
		bdmTlWhywhymstDao = new BAL_BdmTlWhywhymstDaoImpl(dbActionTemplate);
		// bdmTlNewphncausereqDao = new BdmTlNewphncausereqDaoImpl(dbActionTemplate);
		// bdmTlPhenomenamstDao = new BdmTlPhenomenamstDaoImpl(dbActionTemplate);
		commonFilterDao = new BAL_CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}

	public void BAL_BreakdownServiceImplJwt(String JwtToken) {
		try {
			bdmTlMstDao.BAL_BdmTlMstDaoImplJwt(JwtToken);
			bdmServiceApi = new BdmServiceApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	public BAL_BdmTlMst create(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst, BAL_BDFormBean bdFormBean)
			throws ValidationExceptions, Exception {

		try {

			String validationsFor;

			// if(employeeBean.getFormActionMode() != null &&
			// employeeBean.getFormActionMode().equals("emp") )
			// validationsFor = "emp";
			// else
			validationsFor = "create";
			if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag())) {

				if (newBdmTlMst.getBdmsRepeatedbdflag().equals("Y")) {
					CommonFunctions.debugMsg("YES");
				} else {
					validations.validate(newBdmTlMst, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
					// - defined rules for server side
					// validations
					validations.validate(bdFormBean, "Bal_bdcreation", validationsFor);

					List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
					for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
						validations.validate(bdmTlDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																							// - defined rules for
																							// server
																							// side validations
					}
				}
			} else {
				validations.validate(newBdmTlMst, "Bal_bdcreation", validationsFor);// bdcreationcom.akranta.validations.tpm.validations.kaizencreation.xml
				// - defined rules for server side
				// validations
				validations.validate(bdFormBean, "Bal_bdcreation", validationsFor);

				List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
				for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
					validations.validate(bdmTlDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																						// - defined rules for server
																						// side
																						// validations
				}

			}

			fillValues(newBdmTlMst, oldBdmTlMst, bdFormBean);
			System.out.println("Master Object:");
			System.out.println(newBdmTlMst);
			for (BAL_BdmTlDtl dtl : newBdmTlMst.getBdmDetail()) {
				System.out.println(dtl);
			}
			/*
			 * String phen = getPhenType(newBdmTlMst.getBdmsFinalphenomena());
			 * if(UIUtils.isValidKeyId(phen)) { if(phen.equals("ND")) throw new
			 * BusinessApplicationExceptions("UndefinedPhen"); }
			 */
			String strAPstatus = "0";
			if (newBdmTlMst.getBdmsStatus().equals("C"))
				strAPstatus = bdmTlMstDao.getActionPlanDetails(newBdmTlMst);
			;

			if (Integer.parseInt(strAPstatus) > 0)
				throw new BusinessApplicationExceptions("Action Plan pending");
			else {
				// return bdmTlMstDao.create(newBdmTlMst);
				return bdmServiceApi.saveRecord(newBdmTlMst);
			}

			// return bdmTlMstDao.create(newBdmTlMst);

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}

	public BAL_BdmTlMst update(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst, BAL_BDFormBean bdFormBean,
			WomTlWomst womTlWomst) throws BusinessApplicationExceptions, Exception {
		String validationsFor = "update";

		if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag())) {
			System.out.println("Inside service IMPL : " + newBdmTlMst.getBdmsRepeatedbdflag());
			if (newBdmTlMst.getBdmsRepeatedbdflag().equals("Y")) {
				CommonFunctions.debugMsg("YES");
			} else {

				validations.validate(newBdmTlMst, "Bal_bdcreation", "update");// com.akranta.validations.tpm.validations.kaizencreation.xml
																				// - defined rules for server side
																				// validations

				System.out.println("After Validation in Mst S Upd");
				List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
				for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
					validations.validate(bdmTlDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																						// - defined rules for server
																						// side
																						// validations
				}
			}
		} else {

			validations.validate(newBdmTlMst, "Bal_bdcreation", "update");// com.akranta.validations.tpm.validations.kaizencreation.xml
																			// - defined rules for server side
																			// validations

			System.out.println("After Validation in Mst S Upd");
			List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
			for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
				validations.validate(bdmTlDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																					// - defined rules for server side
																					// validations
				CommonFunctions.debugMsg(
						"Final Action in Service IMPL : " + bdmTlDtl.getBdanFinalaction() + " --------------> "
								+ newBdmTlMst.getBdmsWoendflag() + " : " + newBdmTlMst.getBdmsWostartflag());
				if (UIUtils.isValidKeyId(bdmTlDtl.getBdanFinalaction()))// if(UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag())
																		// &&
																		// newBdmTlMst.getBdmsWoendflag().equals("Y")&&
				{
					CommonFunctions.debugMsg("Inside Spares RE");
					validations.validate(newBdmTlMst, "Bal_bdcreation", "FinalAction");

					validations.validate(bdmTlDtl, "Bal_bdcreation", "FinalAction");
				}
			}

		}

		System.out.println("After Update");
		fillValues(newBdmTlMst, oldBdmTlMst, bdFormBean);
		if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag())
				&& UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag())) {
			if (newBdmTlMst.getBdmsWoprodaccepflag().equals("Y") && newBdmTlMst.getBdmsWoendflag().equals("Y")) {
				validations.validate(newBdmTlMst, "Bal_bdcreation", "prodAcceptance");
			}
		}
		// if(newBdmTlMst.getBdmsWostartflag().equals("Y") &&
		// newBdmTlMst.getBdmsWoendflag().equals("Y"))
		// validations.validate(newBdmTlMst,"bdcreation","validDateBD");
		String strAPstatus = "0";
		if (newBdmTlMst.getBdmsStatus().equals("C"))
			strAPstatus = bdmTlMstDao.getActionPlanDetails(newBdmTlMst);
		;

		if (Integer.parseInt(strAPstatus) > 0)
			throw new BusinessApplicationExceptions("ActionPlan");
		else {
			// return bdmTlMstDao.update(newBdmTlMst, womTlWomst);
			if (newBdmTlMst.getbdmTlMultipleResp() != null) {
				for (BAL_BdmTlMultipleResp resp : newBdmTlMst.getbdmTlMultipleResp()) {
					resp.setBdrsRefid(newBdmTlMst.getBdmsKeyid()); // "BDM--00110"
				}
			}
			return bdmServiceApi.saveRecord(newBdmTlMst);
		}
		// return bdmTlMstDao.update(newBdmTlMst,womTlWomst);

	}

	public BAL_BdmTlMst delete(BAL_BdmTlMst bdmTlMst) throws Exception {
		return bdmTlMstDao.delete(bdmTlMst);
	}

	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog newWomTlCommunicationlog,
			WomTlCommunicationlog oldWomTlCommunicationlog, BAL_BDFormBean bdFormBean) throws Exception {
		fillComTxtValues(newWomTlCommunicationlog, oldWomTlCommunicationlog, bdFormBean);
		//return bdmTlMstDao.saveCommTxt(newWomTlCommunicationlog);
		return bdmServiceApi.saveCommTxt(newWomTlCommunicationlog);

	}

	public List<String[]> getAllBreakdown(CommonFilter commonFilter) throws Exception {
		return this.breakdownDao.getBreakdown(commonFilter);
	}

	public BAL_BdmTlMst select(String keyid) throws Exception {
		//return this.bdmTlMstDao.select(keyid);
		return this.bdmServiceApi.getBdmMaster(keyid);
		
		
	}

	public BAL_BdmTlDtl selectBd(String keyid) throws Exception {
		//return this.bdmTlDtlDao.selectBd(keyid);
		return this.bdmServiceApi.getBdmDetail(keyid);
	}

	public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception {
		return this.bdmTlMstDao.selectUPM(keyid);
	}

	public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception {
		return this.bdmTlMstDao.selectUPMDetail(keyid);
	}

	public BAL_BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception {
		return this.bdmTlWhywhymstDao.select(keyid);
	}

	public List<String[]> getAllBD(CommonFilter commonFilter) throws Exception {
		CommonFunctions.debugMsg("service impl");
		return this.bdmTlMstDao.getAllBD(commonFilter);
	}

	@Override
	public List<String[]> getAllBreakdownnew(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getAllBreakdownnew(commonFilter);
	}

	@Override
	public List<String[]> getSapInfoList(CommonFilter commFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getSapInfoList(commFilter);
	}

	@Override
	public List<String[]> getExtServiceList(String ExtMasterId) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtServiceList(ExtMasterId);
	}

	@Override
	public List<String[]> getExtSubList(String wwNo) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtSubList(wwNo);
	}

	@Override
	public List<String[]> getExtRepairList(String wwNo) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getExtRepairList(wwNo);
	}

	public List<ComboBox> getComboShift(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("SFTM_CODE");
		comboFilter.setIdField("SFTM_KEYID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_SHIFTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getMould(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("MLDM_DESCRIPTION");
		comboFilter.setIdField("MLDM_MOULDID");
		comboFilter.setTableName(TableNames.TBL_GEN_TL_MOULDMST);
		if (UIUtils.isValidKeyId(condSql)) {
			StringBuffer sb = new StringBuffer();
			sb.append("AND MLDM_MOULDID IN(SELECT MMLK_MOULDID FROM " + TableNames.TBL_GEN_TL_MOULDMACHINELINK);
			sb.append(" WHERE MMLK_MACHINEID = '" + condSql + "')");
			comboFilter.setCondSql(sb.toString());
		}
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getRepeatedbdno(String condSql, String assmId, String eqpId, String idFlag,
			ComboFilter comboFilter) throws Exception {
//	public List<ComboBox> getRepeatedbdno(String condSql) throws Exception

		// ComboFilter comboFilter = new ComboFilter();

		if (UIUtils.isValidKeyId(idFlag)) {
			comboFilter.setNameField("UPMM_REPORTEDDATE");
			comboFilter.setCodeField("UPMM_KEYID");
			comboFilter.setIdField("UPMM_KEYID");
			comboFilter.setTableName("PLM_TL_UNPLANNEDMAINTMST");
			if (UIUtils.isValidKeyId(assmId)) {
				condSql += "AND UPMM_ACTIVE ='Y' AND UPMM_STATUS <> 'D'";
				condSql += " AND UPMM_ASSEMBLYID = '" + assmId + "'";
			}
			if (UIUtils.isValidKeyId(eqpId)) {
				condSql += " AND UPMM_MACHINEID = '" + eqpId + "'";
				condSql += " AND  UPMM_KEYID <> '-99' AND LENGTH(UPMM_KEYID) >  10 AND UPMM_STATUS ='C' ";// AND
																											// UPMM_STATUS
																											// ='C'
				// condSQL += " AND TO_CHAR(UPMM_REPORTEDDATE,'MON-YYYY') =
				// '"+repDate.substring(3, 11)+"'";
				// condSQL += " AND UPMM_REPORTEDDATE > TO_DATE('"+repDate+"','DD-MON-YYYY
				// HH24:MI')";
				condSql += " AND UPMM_REPEATEDBDFLAG ='N'";
			}

		} else {
			comboFilter.setNameField("BDMS_REPORTEDDATE");
			comboFilter.setCodeField("BDMS_KEYID");
			comboFilter.setIdField("BDMS_KEYID");
			comboFilter.setTableName(TableNames.TBL_BDM_TL_MST);
			if (UIUtils.isValidKeyId(assmId)) {
				condSql += "AND BDMS_ACTIVE ='Y' AND BDMS_STATUS <> 'D'";
				condSql += " AND BDMS_ASSEMBLYID = '" + assmId + "'";
			}
			if (UIUtils.isValidKeyId(eqpId)) {
				condSql += " AND BDMS_MACHINEID = '" + eqpId + "'";
				condSql += " AND  BDMS_KEYID <> '-99' AND LENGTH(BDMS_KEYID) >  10 AND BDMS_STATUS ='C' ";// AND
																											// BDMS_STATUS
																											// ='C'
				// condSQL += " AND TO_CHAR(BDMS_REPORTEDDATE,'MON-YYYY') =
				// '"+repDate.substring(3, 11)+"'";
				// condSQL += " AND BDMS_REPORTEDDATE > TO_DATE('"+repDate+"','DD-MON-YYYY
				// HH24:MI')";
				condSql += " AND BDMS_REPEATEDBDFLAG ='N'";
			}
		}
		if (condSql.length() > 0)
			comboFilter.setCondSql(condSql);

		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getFinalTrade(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();

		comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setNameField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		// MANO CHANGE
		comboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getAlarm(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("BALM_NAME");
		comboFilter.setIdField("BALM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_ALARMMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getFailureType(String condSql, ComboFilter comboFilter) throws Exception {
		String condn = "";
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("FLTM_CODE");
		comboFilter.setNameField("FLTM_NAME");
		comboFilter.setIdField("FLTM_KEYID");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_FAILURETYPEMST);
		if (UIUtils.isValidKeyId(condSql))
			condn = "AND FLTM_RELATEDTO = '" + condSql + "'";
		comboFilter.setCondSql(condn);
		CommonFunctions.debugMsg(" get id failur ::::" + comboFilter.getIdField());
		return commonFilterDao.fillComboValues(comboFilter);
	}

	// commented and added by priyanka
	/*
	 * public List<ComboBox> getPhenomena(String condSql, ComboFilter comboFilter)
	 * throws Exception { // ComboFilter comboFilter = new ComboFilter();
	 * StringBuffer sb = new StringBuffer(); if (!UIUtils.isValidKeyId(condSql))
	 * condSql = "{}"; String[] condFlag = condSql.split("-"); if
	 * (UIUtils.isValidKeyId(condFlag[0])) { if (condFlag[0].equals("MLD")) {
	 * System.out.println("Entered IF -> MLD case");
	 * 
	 * comboFilter.setNameField("BPHM_PHENOMENANAME");
	 * comboFilter.setIdField("MPHL_BPHMID "); comboFilter
	 * .setTableName(TableNames.TBL_GEN_TL_MOULDPHENLINK + "," +
	 * TableNames.TBL_BAL_BDM_TL_PHENOMENAMST); } else {
	 * System.out.println("Entered ELSE -> Non MLD case");
	 * comboFilter.setNameField("PCT_DISPLAYCODE");
	 * comboFilter.setIdField("PCT_ORGINALID");
	 * comboFilter.setTableName(TableNames.TBL_BAL_BDM_VW_PHENCASLAYOUT); } } else {
	 * System.out.println("Entered OUTER ELSE -> condFlag[0] is invalid");
	 * 
	 * comboFilter.setNameField("PCT_DISPLAYCODE");
	 * comboFilter.setIdField("PCT_ORGINALID");
	 * comboFilter.setTableName(TableNames.TBL_BAL_BDM_VW_PHENCASLAYOUT);
	 * 
	 * }
	 * 
	 * // comboFilter.setCodeField("BPHM_PHENOMENATYPE");
	 * 
	 * CommonFunctions.debugMsg("Machine ID : " + condSql);
	 * 
	 * 
	 * if(UIUtils.isValidKeyId(condFlag[0])) { if(condFlag[0].equals("MLD")) {
	 * sb.append(" AND PCT_ORIGINALID IN (SELECT M } }
	 * 
	 * if (UIUtils.isValidKeyId(condSql)) { if (condFlag[0].equals("MLD")) {
	 * sb.append("AND MPHL_BPHMID = BPHM_KEYID AND MPHL_MOULDID = '" + condFlag[1] +
	 * "'"); } else { sb.append(" AND PCT_ELEMENTID LIKE '%" + condSql + "%'");
	 * sb.append(" AND PCT_ELEMENTTYPE ='PHN'"); } } else
	 * sb.append(" AND PCT_ELEMENTTYPE ='PHN'");
	 * 
	 * // sb.append(" UNION SELECT DISTINCT BPHM_KEYID id ,BPHM_PHENOMENANAME text
	 * from // "+TableNames.TBL_BDM_TL_PHENOMENAMST); //
	 * sb.append(" where BPHM_PHENOMENANAME = 'NOT DEFINED'");
	 * 
	 * comboFilter.setCondSql(sb.toString()); return
	 * commonFilterDao.fillComboValues(comboFilter); }
	 */
	
	public List<ComboBox> getPhenomena(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		StringBuffer sb = new StringBuffer();
		if (!UIUtils.isValidKeyId(condSql))
			condSql = "{}";
		String[] condFlag = condSql.split("-");
		if (UIUtils.isValidKeyId(condFlag[0])) {
			if (condFlag[0].equals("MLD")) {
				System.out.println("Entered IF -> MLD case");

				comboFilter.setNameField("BPHM_PHENOMENANAME");
				comboFilter.setIdField("MPHL_BPHMID ");
				comboFilter
						.setTableName(TableNames.TBL_GEN_TL_MOULDPHENLINK + "," + TableNames.TBL_BAL_BDM_TL_PHENOMENAMST);
			} else {
				System.out.println("Entered ELSE -> Non MLD case");
				comboFilter.setNameField("PCT_DISPLAYCODE");
				comboFilter.setIdField("PCT_ORGINALID");
				comboFilter.setTableName(TableNames.TBL_BAL_BDM_VW_PHENCASLAYOUT);
			}
		} else {
			System.out.println("Entered OUTER ELSE -> condFlag[0] is invalid");

			//comboFilter.setNameField("PCT_DISPLAYCODE");
			//comboFilter.setIdField("PCT_ORGINALID");
			//comboFilter.setTableName(TableNames.TBL_BAL_BDM_VW_PHENCASLAYOUT);
			comboFilter.setNameField("BPHM_PHENOMENANAME");
		    comboFilter.setIdField("BPHM_KEYID");
		    comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_PHENOMENAMST);

		    sb.append(" AND BPHM_PHENOMENATYPE = 'BD'");
		   

		}

		// comboFilter.setCodeField("BPHM_PHENOMENATYPE");

		CommonFunctions.debugMsg("Machine ID : " + condSql);

		/*
		 * if(UIUtils.isValidKeyId(condFlag[0])) { if(condFlag[0].equals("MLD")) {
		 * sb.append(" AND PCT_ORIGINALID IN (SELECT M } }
		 */
		if (UIUtils.isValidKeyId(condSql)) {
			if (condFlag[0].equals("MLD")) {
				sb.append("AND MPHL_BPHMID = BPHM_KEYID AND MPHL_MOULDID = '" + condFlag[1] + "'");
			} else {
				sb.append(" AND PCT_ELEMENTID LIKE '%" + condSql + "%'");
				sb.append(" AND PCT_ELEMENTTYPE ='PHN'");
			}
		} else
			//sb.append(" AND PCT_ELEMENTTYPE ='PHN'");

		// sb.append(" UNION SELECT DISTINCT BPHM_KEYID id ,BPHM_PHENOMENANAME text from
		// "+TableNames.TBL_BDM_TL_PHENOMENAMST);
		// sb.append(" where BPHM_PHENOMENANAME = 'NOT DEFINED'");

		comboFilter.setCondSql(sb.toString());
		return commonFilterDao.fillComboValues(comboFilter);
	}
	// end

	/*
	 * public List<ComboBox> getCause(String condSql, String phenId, String assmId,
	 * ComboFilter comboFilter) throws Exception { // ComboFilter comboFilter = new
	 * ComboFilter(); // comboFilter.setCodeField("BCSM_CODE"); //
	 * comboFilter.setNameField("BCSM_NAME"); //
	 * comboFilter.setIdField("BCSM_KEYID");
	 * comboFilter.setNameField("BPCL_DISPLAYCODE");
	 * comboFilter.setIdField("BPCL_ORIGINALID"); condSql =
	 * "AND BPCL_ELEMENTTYPE = 'CAS'"; if (UIUtils.isValidKeyId(phenId)) { condSql
	 * += "AND INSTR(BPCL_PARENTID, '" + phenId + "')>0 "; } if
	 * (UIUtils.isValidKeyId(assmId)) { condSql += "AND INSTR(BPCL_PARENTID, '" +
	 * assmId + "')>0 "; } comboFilter.setCondSql(condSql);
	 * 
	 * // comboFilter.setTableName(TableNames.TBL_BDM_TL_CAUSEMST);
	 * comboFilter.setTableName(TableNames.TBL_BDM_TL_PHNCAUSELINK); return
	 * commonFilterDao.fillComboValues(comboFilter); }
	 */
	// mano
	// commented and added by priyanka
	/*
	 * @Override public List<ComboBox> getCause(String condSql, String phenId,
	 * String assmId, ComboFilter comboFilter) throws Exception {
	 * 
	 * comboFilter.setNameField("BPCL_DISPLAYCODE");
	 * comboFilter.setIdField("BPCL_ORIGINALID");
	 * 
	 * condSql = " AND BPCL_ELEMENTTYPE = 'CAS' ";
	 * 
	 * if (UIUtils.isValidKeyId(phenId)) { condSql += " AND POSITION('" + phenId +
	 * "' IN BPCL_PARENTID) > 0 "; }
	 * 
	 * if (UIUtils.isValidKeyId(assmId)) { condSql += " AND POSITION('" + assmId +
	 * "' IN BPCL_PARENTID) > 0 "; }
	 * 
	 * comboFilter.setCondSql(condSql);
	 * 
	 * comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK);
	 * 
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */
	@Override
	public List<ComboBox> getCause(String condSql, String phenId, String assmId, ComboFilter comboFilter)
			throws Exception {

		comboFilter.setNameField("BCSM_NAME");
		comboFilter.setIdField("BCSM_KEYID");

		// condSql = " AND BPCL_ELEMENTTYPE = 'CAS' ";
		CommonMessage.debugMsg("Phenomena Id " + phenId);
		if (UIUtils.isValidKeyId(phenId)) {
			condSql += " AND BCSM_PHENOMENAID = " + "'" + phenId + "'";
		}

		// if (UIUtils.isValidKeyId(assmId)) {
		// condSql += " AND POSITION('" + assmId + "' IN BPCL_PARENTID) > 0 ";
		// }

		comboFilter.setCondSql(condSql);

		comboFilter.setTableName(TableNames.TBL_BAL_BDM_TL_CAUSEMST);

		return commonFilterDao.fillComboValues(comboFilter);
		// return comboList;
	}

	// end
	public List<ComboBox> getBDClassification(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		// comboFilter.setCodeField("TPMP_CODE");
		comboFilter.setNameField("BCLM_NAME");
		comboFilter.setIdField("BCLM_KEYID");
		// mano 0206
		comboFilter.setTableName("BAL_BDM_VW_CLASSIFICATION");

		if (condSql.length() > 0)
			comboFilter.setCondSql(condSql);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getSpare(String factId, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		// String condSql;
		comboFilter.setCodeField("SPRM_PARTNO");
		comboFilter.setNameField("SPRM_PARTNAME");
		comboFilter.setIdField("SPRM_KEYID");
		comboFilter.setTableName(TableNames.TBL_SAP_TL_spares);
		// condSql = "AND FNLN_ELEMENTTYPE = 'SPR'";
		// if(UIUtils.isValidKeyId(spareId))
		// condSql += "AND FNLN_ORIGINALID='"+spareId+"'";
		StringBuilder condSql = new StringBuilder();
		if (UIUtils.isValidKeyId(factId))
			condSql.append("AND SPRM_FACTORYID='" + factId + "'");
		comboFilter.setCondSql(condSql.toString());

		CommonFunctions.debugMsg(" get id SPARESS ::" + comboFilter.getNameField());
		return commonFilterDao.fillComboValues(comboFilter);

	}

	/*
	 * public List<String[]> getShift(List<String> paramValues) { return
	 * this.bdmTlMstDao.getShift(paramValues); }
	 */
	public List<String[]> getPillarClassfcn(String pillar) throws Exception {
		return this.bdmTlMstDao.getPillarClassfcn(pillar);
	}

	public String getShift(ShiftBean shiftBean) {
		return this.bdmTlMstDao.getShift(shiftBean);
	}

	public List<String[]> getDownTime(List<String> paramValues) {
		return this.bdmTlMstDao.getDownTime(paramValues);
	}

	public List<String[]> getCommText(String bdId) {
		//return this.bdmTlMstDao.getCommText(bdId);
		return this.bdmServiceApi.getCommText(bdId);
	}

	public List<String[]> getYY(String wwNo) {
		return this.bdmTlMstDao.getYY(wwNo);
	}

	public List<String[]> getRootCause(String wwNo) throws Exception {
		return this.bdmTlMstDao.getRootCause(wwNo);
	}

	public String getPhenType(String bookedPhen) {
		return this.bdmTlMstDao.getPhenType(bookedPhen);
	}

	public Workbook breakdownExportExcel(CommonFilter commonFilter, JSONObject colmodel, String rptFormat)
			throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.breakdownRpt(commonFilter, colmodel, rptFormat);
	}

	private BAL_BdmTlMst fillValues(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst, BAL_BDFormBean bdFormBean) {

		newBdmTlMst.setBdmsActive("Y");

		// PcsTlPcsBd newPcsbd =new PcsTlPcsBd();

		System.out.println("Reported Date " + newBdmTlMst.getBdmsReporteddate());

		System.out.println("Entry Date " + newBdmTlMst.getBdmsEntrydate());

		System.out.println(" Prodaceep Date " + newBdmTlMst.getBdmsProdaccepdate());

		WomTlWomst newWoTlmst = new WomTlWomst();
		newWoTlmst.setWomsReportedby(newBdmTlMst.getBdmsBookedby());
		String dateTime = CommonFunctions.pg_dateTimeNow();
		// System.out.println("Old CreatedON : "+oldBdmTlMst.getBdmsCreatedon());
		/*
		 * if(UIUtils.isValidKeyId(oldBdmTlMst.getBdmsKeyid()) ) {
		 * newBdmTlMst.setBdmsCreatedon(oldBdmTlMst.getBdmsCreatedon()); } else {
		 */
		// newBdmTlMst.setBdmsCreatedon(oldBdmTlMst.getBdmsCreatedon());
		newBdmTlMst.setBdmsCreatedon(dateTime);
		// }

		newBdmTlMst.setBdmsModifiedon(dateTime);

		String date = newBdmTlMst.getBdmsEntrydate();
		newBdmTlMst.setBdmsEntrydate(CommonFunctions.pg_getDateTimeFromDate(date));

		if (newBdmTlMst.getBdmsEntrydate() == null)
			newBdmTlMst.setBdmsEntrydate(dateTime);

		if (newBdmTlMst.getBdmsShiftid() == null)
			newBdmTlMst.setBdmsShiftid("{}");

		if (newBdmTlMst.getBdmsPartlocationid() == null)
			newBdmTlMst.setBdmsPartlocationid("{}");

		if (newBdmTlMst.getBdmsAlarmdescription() == null)
			newBdmTlMst.setBdmsAlarmdescription("{}");

		if (newBdmTlMst.getBdmsElementid() == null)
			newBdmTlMst.setBdmsElementid("{}");

		if (newBdmTlMst.getBdmsFlid() == null)
			newBdmTlMst.setBdmsFlid("{}");

		if (newBdmTlMst.getBdmsBdtype() == null)
			newBdmTlMst.setBdmsBdtype("E");

		// String date1 = newBdmTlMst.getBdmsReporteddate();
		// newBdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDateFromTimeStamp(date1));

		if (newBdmTlMst.getBdmsReporteddate() == null)
			newBdmTlMst.setBdmsReporteddate(dateTime);

		if (newBdmTlMst.getBdmsOtherPhenomena() == null)
			newBdmTlMst.setBdmsOtherPhenomena("{}");

		if (!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag()))
			newBdmTlMst.setBdmsWoprodaccepflag("N");
		if (!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag()))
			newBdmTlMst.setBdmsWoendflag("N");
		if (!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWostartflag()))
			newBdmTlMst.setBdmsWostartflag("N");
		if (!UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoallottedflag()))
			newBdmTlMst.setBdmsWoallottedflag("N");

		// if( newBdmTlMst.getBdmsProdaccepdate() == null )

		if (newBdmTlMst.getBdmsWoprodaccepflag().equals("Y")) {
			System.out.println("(newBdmTlMst.getBdmsProdaccepdate()+  +bdFormBean.getBdmsProdacceptime());"
					+ newBdmTlMst.getBdmsProdaccepdate() + "     " + bdFormBean.getBdmsProdacceptime());

			if (newBdmTlMst.getBdmsProdaccepdate() != null) {
				System.out.println("(,,,,,,,newBdmTlMst.getBdmsProdaccepdate()+  +bdFormBean.getBdmsProdacceptime());"
						+ newBdmTlMst.getBdmsProdaccepdate() + "  " + bdFormBean.getBdmsReportedtime() + "    "
						+ bdFormBean.getBdmsProdacceptime());

				if (bdFormBean.getBdmsProdacceptime() != null) {

					newBdmTlMst.setBdmsProdaccepdate(
							newBdmTlMst.getBdmsProdaccepdate() + " " + bdFormBean.getBdmsProdacceptime());
					String prodaceepDate = newBdmTlMst.getBdmsProdaccepdate() + ":00";
					newBdmTlMst.setBdmsProdaccepdate(CommonFunctions.pg_getDateTimeFromTimeStamp(prodaceepDate));

				} else {
					String prodaceepDate1 = newBdmTlMst.getBdmsReporteddate();
					newBdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDate(prodaceepDate1));

				}
			}
		} else
			newBdmTlMst.setBdmsProdaccepdate(Constants.pgFutureNullDateTime);

		CommonFunctions.debugMsg(" bdFormBean.getChkCompletedBy() " + bdFormBean.getChkCompletedBy());

		if ("Y".equals(bdFormBean.getChkCompletedBy())) {
			if (newBdmTlMst.getBdmsCompleteddate() != null) {
				if (bdFormBean.getBdmsCompletedtime() != null) {
					newBdmTlMst.setBdmsCompleteddate(
							newBdmTlMst.getBdmsCompleteddate() + " " + bdFormBean.getBdmsCompletedtime());
					String completedDate = newBdmTlMst.getBdmsCompleteddate() + ":00";
					newBdmTlMst.setBdmsCompleteddate(CommonFunctions.pg_getDateTimeFromTimeStamp(completedDate));

				} else {
					String completedDate1 = newBdmTlMst.getBdmsCompleteddate();
					newBdmTlMst.setBdmsCompleteddate(CommonFunctions.pg_getDateTimeFromDate(completedDate1));

				}

			} else
				newBdmTlMst.setBdmsCompleteddate(Constants.pgFutureNullDateTime);
		} else
			newBdmTlMst.setBdmsCompleteddate(Constants.pgFutureNullDateTime);

		if (newBdmTlMst.getBdmsReporteddate() != null) {
			if (bdFormBean.getBdmsReportedtime() != null) {
				newBdmTlMst.setBdmsReporteddate(
						newBdmTlMst.getBdmsReporteddate() + " " + bdFormBean.getBdmsReportedtime());

				String reportedDate = newBdmTlMst.getBdmsReporteddate() + ":00";
				newBdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDateTimeFromTimeStamp(reportedDate));
			} else {
				String reportedDate1 = newBdmTlMst.getBdmsReporteddate();
				newBdmTlMst.setBdmsReporteddate(CommonFunctions.pg_getDateTimeFromDate(reportedDate1));

			}
		}

		if (newBdmTlMst.getBdmsWoallottedflag().equals("Y")) {
			if (newBdmTlMst.getBdmsReceiveddate() != null) {
				if (bdFormBean.getBdmsReceivedtime() != null) {
					newBdmTlMst.setBdmsReceiveddate(
							newBdmTlMst.getBdmsReceiveddate() + " " + bdFormBean.getBdmsReceivedtime());
				}
			}
		} else
			newBdmTlMst.setBdmsReceiveddate(dateTime);

		if (newBdmTlMst.getBdmsWostartflag().equals("Y")) {
			if (newBdmTlMst.getBdmsWostarttime() != null) {
				if (bdFormBean.getBdmsWostart() != null) {
					newBdmTlMst
							.setBdmsWostarttime(newBdmTlMst.getBdmsWostarttime() + " " + bdFormBean.getBdmsWostart());
					String workstartDate = newBdmTlMst.getBdmsWostarttime() + ":00";
					newBdmTlMst.setBdmsWostarttime(CommonFunctions.pg_getDateTimeFromTimeStamp(workstartDate));

				} else {
					String date2 = newBdmTlMst.getBdmsWostarttime();
					newBdmTlMst.setBdmsWostarttime(CommonFunctions.pg_getDate(date2));

				}
			}
		} else {
			// newBdmTlMst.setBdmsWostarttime(Constants.pgPassNullDate + " 00:00");
			newBdmTlMst.setBdmsWostarttime(Constants.pgPassNullDateTime);
		}
		if (newBdmTlMst.getBdmsWoendflag().equals("Y")) {
			if (newBdmTlMst.getBdmsWoendtime() != null) {
				if (bdFormBean.getBdmsWoend() != null) {
					newBdmTlMst.setBdmsWoendtime(newBdmTlMst.getBdmsWoendtime() + " " + bdFormBean.getBdmsWoend());
					String workendDate = newBdmTlMst.getBdmsWoendtime() + ":00";
					newBdmTlMst.setBdmsWoendtime(CommonFunctions.pg_getDateTimeFromTimeStamp(workendDate));

				} else {
					String date5 = newBdmTlMst.getBdmsWoendtime();
					newBdmTlMst.setBdmsWoendtime(CommonFunctions.pg_getDate(date5));

				}
			}
		} else
			// newBdmTlMst.setBdmsWoendtime(Constants.pgFutureNullDate + " 00:00");
			newBdmTlMst.setBdmsWoendtime(Constants.pgFutureNullDateTime);

		if (newBdmTlMst.getBdmsBreaktime() == null)
			newBdmTlMst.setBdmsBreaktime("0");

		if (newBdmTlMst.getBdmsActualworktime() == null)
			newBdmTlMst.setBdmsActualworktime("0");

		if (newBdmTlMst.getBdmsElementid() == null)
			newBdmTlMst.setBdmsElementid("{}");

		if (newBdmTlMst.getBdmsDowntime() == null)
			newBdmTlMst.setBdmsDowntime("0");

		if (newBdmTlMst.getBdmsPhenomenadescription() == null)
			newBdmTlMst.setBdmsPhenomenadescription("{}");

		if (newBdmTlMst.getBdmsBookedcause() == null)
			newBdmTlMst.setBdmsBookedcause("{}");

		if (newBdmTlMst.getBdmsFinalphenomena() == null)
			newBdmTlMst.setBdmsFinalphenomena("{}");

		if (newBdmTlMst.getBdmsFinalcause() == null)
			newBdmTlMst.setBdmsFinalcause("{}");

		if (!UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag()))
			newBdmTlMst.setBdmsRepeatedbdflag("N");

		if (newBdmTlMst.getBdmsBookedtrade() == null)
			newBdmTlMst.setBdmsBookedtrade("{}");

		if (newBdmTlMst.getBdmsFinaltrade() == null) {
			if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsBookedtrade()))
				newBdmTlMst.setBdmsFinaltrade(newBdmTlMst.getBdmsBookedtrade());
			else
				newBdmTlMst.setBdmsFinaltrade("{}");
		}

		if (newBdmTlMst.getBdmsProblemdescription() == null)
			newBdmTlMst.setBdmsProblemdescription("{}");

		if (newBdmTlMst.getBdmsIsbdlocked() == null)
			newBdmTlMst.setBdmsIsbdlocked("X");

		if (newBdmTlMst.getBdmsShiftincharge() == null)
			newBdmTlMst.setBdmsShiftincharge("{}");

		if (newBdmTlMst.getBdmsStatus() == null) {
			if ("Y".equals(bdFormBean.getChkCompletedBy()))
				newBdmTlMst.setBdmsStatus("C");
			else
				newBdmTlMst.setBdmsStatus("X");
		}

		if (newBdmTlMst.getBdmsBookedby() == null)
			newBdmTlMst.setBdmsBookedby("{}");

		if (newBdmTlMst.getBdmsRemarks() == null)
			newBdmTlMst.setBdmsRemarks("{}");

		if (newBdmTlMst.getBdmsBookingtype() == null)
			newBdmTlMst.setBdmsBookingtype("ONL");

		if (newBdmTlMst.getBdmsBdrelatedto() == null)
			newBdmTlMst.setBdmsBdrelatedto("XXX");

		if (newBdmTlMst.getBdmsWno() == null)
			newBdmTlMst.setBdmsWno("{}");

		if (newBdmTlMst.getBdmsSpareid() == null)
			newBdmTlMst.setBdmsSpareid("{}");

		if (newBdmTlMst.getBdmsPriority() == null)
			newBdmTlMst.setBdmsPriority("0");

		if (newBdmTlMst.getBdmsWoallottedflag() == null)
			newBdmTlMst.setBdmsWoallottedflag("X");

		if (newBdmTlMst.getBdmsWostartflag() == null)
			newBdmTlMst.setBdmsWostartflag("X");

		if (newBdmTlMst.getBdmsWoendflag() == null)
			newBdmTlMst.setBdmsWoendflag("X");

		if (newBdmTlMst.getBdmsSubassemblyid() == null)
			newBdmTlMst.setBdmsSubassemblyid("{}");

		if (newBdmTlMst.getBdmsRepeatedbdflag() == null)
			newBdmTlMst.setBdmsRepeatedbdflag("X");

		if (newBdmTlMst.getBdmsRepeatedbdno() == null)
			newBdmTlMst.setBdmsRepeatedbdno("{}");

		if (newBdmTlMst.getBdmsSectionid() == null)
			newBdmTlMst.setBdmsSectionid("{}");

		if (newBdmTlMst.getBdmsCellid() == null)
			newBdmTlMst.setBdmsCellid("{}");

		if (newBdmTlMst.getBdmsMachineid() == null)
			newBdmTlMst.setBdmsMachineid("{}");

		if (newBdmTlMst.getBdmsFinaltrade() == null)
			newBdmTlMst.setBdmsFinaltrade("{}");

		if (newBdmTlMst.getBdmsRelatedto() == null)
			newBdmTlMst.setBdmsRelatedto("{}");

		if (newBdmTlMst.getBdmsMould() == null)
			newBdmTlMst.setBdmsMould("{}");

		if (newBdmTlMst.getBdmsAssemblyid() == null)
			newBdmTlMst.setBdmsAssemblyid("{}");

		if (newBdmTlMst.getBdmsBookedphenomena() == null)
			newBdmTlMst.setBdmsBookedphenomena("{}");

		if (newBdmTlMst.getBdmsProcessid() == null)
			newBdmTlMst.setBdmsProcessid("{}");

		if (newBdmTlMst.getBdmsIsstandby() == null)
			newBdmTlMst.setBdmsIsstandby("N");

		if (newBdmTlMst.getBdmsStandbyequipment() == null)
			newBdmTlMst.setBdmsStandbyequipment("{}");

		if (newBdmTlMst.getBdmsBreakdowntime() == null)
			newBdmTlMst.setBdmsBreakdowntime("0");

		if (newBdmTlMst.getBdmsProductionstop() == null)
			newBdmTlMst.setBdmsProductionstop("-");

		if (newBdmTlMst.getBdmsImmediateaction() == null)
			newBdmTlMst.setBdmsImmediateaction("-");

		if (newBdmTlMst.getBdmsCompleteddate() == null)
			newBdmTlMst.setBdmsCompleteddate(Constants.pgFutureNullDateTime);

		if (newBdmTlMst.getBdmsProblemreason() == null)
			newBdmTlMst.setBdmsProblemreason("-");

		if (newBdmTlMst.getBdmsActivity() == null)
			newBdmTlMst.setBdmsActivity("-");

		if (newBdmTlMst.getBdmsTempfield7() == null)
			newBdmTlMst.setBdmsTempfield7("-");

		BAL_PcsTlMachineCalTime mchcalTm = new BAL_PcsTlMachineCalTime();
		if (mchcalTm.getMchCalFactory() == null) {
			mchcalTm.setMchCalFactory(newBdmTlMst.getBdmsFactoryid());
		}

		System.out.println("Reported Date 2" + newBdmTlMst.getBdmsReporteddate());

		System.out.println("Entry Date2 " + newBdmTlMst.getBdmsEntrydate());

		System.out.println(" Prodaceep Date 2" + newBdmTlMst.getBdmsProdaccepdate());

		System.out.println("Reported Date 3" + newBdmTlMst.getBdmsReporteddate());

		System.out.println(" Prodaceep Date 3" + newBdmTlMst.getBdmsProdaccepdate());

		newBdmTlMst.setBdmDetail(detailFillValues(newBdmTlMst, oldBdmTlMst, bdFormBean));
		newBdmTlMst.setBdmShiftwise(shiftFillValues(newBdmTlMst, oldBdmTlMst, bdFormBean));
		newBdmTlMst.setbdmTlMultipleResp(MultiRespfillvalues(newBdmTlMst, oldBdmTlMst, bdFormBean));
		// System.out.println("getBdmDetail "+
		// newBdmTlMst.getBdmDetail().get(0).toString());
		return newBdmTlMst;

	}

	private List<BAL_BdmTlMultipleResp> MultiRespfillvalues(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst,
			BAL_BDFormBean bdFormBean) {

		List<BAL_BdmTlMultipleResp> bdmTlMultipleResp = newBdmTlMst.getbdmTlMultipleResp();
		List<BAL_BdmTlMultipleResp> bdmTlMultipleRespArray = new ArrayList<BAL_BdmTlMultipleResp>();
		if (newBdmTlMst.getbdmTlMultipleResp() != null && newBdmTlMst.getbdmTlMultipleResp().size() > 0) // check for
																											// detail
																											// table
																											// data
		{
			for (BAL_BdmTlMultipleResp bdmTlMultipleRespList : bdmTlMultipleResp) {
				if (bdmTlMultipleRespList.getBdrsTempfield() == null)
					bdmTlMultipleRespList.setBdrsTempfield("-");
				if (bdmTlMultipleRespList.getBdrsActive() == null)
					bdmTlMultipleRespList.setBdrsActive("Y");
				bdmTlMultipleRespArray.add(bdmTlMultipleRespList);
			}
		}
		return bdmTlMultipleRespArray;
	}

	private List<BAL_BdmTlDtl> detailFillValues(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst,
			BAL_BDFormBean bdFormBean) {

		String dateTime = CommonFunctions.pg_dateTimeNow();
		List<BAL_BdmTlDtl> newBdmTlDtl = newBdmTlMst.getBdmDetail();
		List<BAL_BdmTlDtl> oldBdmTlDtl = null;
		BAL_BdmTlDtl oldBdmTlDtls = null;
		if (oldBdmTlMst != null) {
			oldBdmTlDtl = oldBdmTlMst.getBdmDetail();
			if (oldBdmTlDtl != null && oldBdmTlDtl.size() > 0) {
				oldBdmTlDtls = oldBdmTlDtl.get(0);
			}
		}
		List<BAL_BdmTlDtl> newBdmTlDtlList = new ArrayList<BAL_BdmTlDtl>();
		for (BAL_BdmTlDtl bdmTlDtl : newBdmTlDtl) {
			if (bdmTlDtl.getBdanKeyid() == null) {
				bdmTlDtl.setBdanCreatedon(dateTime);
			} else {
				// bdmTlDtl.setBdanCreatedon(oldBdmTlDtls.getBdanCreatedon());
				bdmTlDtl.setBdanCreatedon(dateTime);
			}

			bdmTlDtl.setBdanModifiedon(dateTime);
			bdmTlDtl.setBdanActive("Y");
			bdmTlDtl.setBdanCreatedby(newBdmTlMst.getBdmsCreatedby());
			// bdmTlDtl.setBdanBdms_keyid(newBdmTlMst.getBdmsKeyid());

			// genTlEmployeedtl.setEmpdModifiedon(dateTime);
			if (bdmTlDtl.getBdanFinalphenomena() == null)
				bdmTlDtl.setBdanFinalphenomena("{}");

			if (bdmTlDtl.getBdanFinalcause() == null)
				bdmTlDtl.setBdanFinalcause("{}");

			if (bdmTlDtl.getBdanTradeid() == null)
				bdmTlDtl.setBdanTradeid("{}");

			if (bdmTlDtl.getBdanFinalaction() == null)
				bdmTlDtl.setBdanFinalaction("{}");

			if (bdmTlDtl.getBdanCountermeasure() == null)
				bdmTlDtl.setBdanCountermeasure("{}");

			if (bdmTlDtl.getBdanWwrequired() == null) {
				if (UIUtils.isValidKeyId(bdmTlDtl.getBdanWwno()))
					bdmTlDtl.setBdanWwrequired("Y");
				else
					bdmTlDtl.setBdanWwrequired("N");
			}

			if (!UIUtils.isValidKeyId(bdmTlDtl.getBdanWwno()))
				bdmTlDtl.setBdanWwno("{}");

			if (bdmTlDtl.getBdanRootcause() == null)
				bdmTlDtl.setBdanRootcause("{}");

			if (bdmTlDtl.getBdanPreventivemeasure() == null)
				bdmTlDtl.setBdanPreventivemeasure("{}");

			if (bdmTlDtl.getBdanRootcauseid() == null)
				bdmTlDtl.setBdanRootcauseid("{}");

			if (bdmTlDtl.getBdanCountermeasureid() == null)
				bdmTlDtl.setBdanCountermeasureid("{}");

			if (bdmTlDtl.getBdanPreventivemeasureid() == null)
				bdmTlDtl.setBdanPreventivemeasureid("{}");

			if (bdmTlDtl.getBdanBreakdowntime() == null)
				bdmTlDtl.setBdanBreakdowntime("0");

			if (bdmTlDtl.getBdanWorktime() == null)
				bdmTlDtl.setBdanWorktime("0");

			if (bdmTlDtl.getBdanCategoryid() == null)
				bdmTlDtl.setBdanCategoryid("{}");

			System.out.println("spares replaced +++++++++" + bdFormBean.getIssparesY());

			/*
			 * bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesY());
			 * 
			 * if(bdmTlDtl.getBdanIssparesreplaced() == null)
			 * bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesN());
			 * 
			 * if(bdmTlDtl.getBdanIssparesreplaced() == null)
			 * bdmTlDtl.setBdanIssparesreplaced("X");
			 */
			bdmTlDtl.setBdanIssparesreplaced("X");
			if (UIUtils.isValidKeyId(bdFormBean.getIssparesY()))
				bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesY());
			if (UIUtils.isValidKeyId(bdFormBean.getIssparesN()))
				bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesN());
			if (UIUtils.isValidKeyId(bdFormBean.getIssparesW()))
				bdmTlDtl.setBdanIssparesreplaced(bdFormBean.getIssparesW());

			System.out.println("spares replaced ;lllllllll" + bdmTlDtl.getBdanIssparesreplaced());

			if (bdmTlDtl.getBdanAlarmno() == null)
				bdmTlDtl.setBdanAlarmno("{}");

			if (bdmTlDtl.getBdanManpowercost() == null)
				bdmTlDtl.setBdanManpowercost("0");

			if (bdmTlDtl.getBdanContractorcost() == null)
				bdmTlDtl.setBdanContractorcost("0");

			if (bdmTlDtl.getBdanSparescost() == null)
				bdmTlDtl.setBdanSparescost("0");

			if (bdmTlDtl.getBdanOthercost() == null)
				bdmTlDtl.setBdanOthercost("0");

			if (bdmTlDtl.getBdanStatus() == null)
				bdmTlDtl.setBdanStatus("X");

			if (bdmTlDtl.getBdanRemarks() == null) {
				if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsRemarks()))
					bdmTlDtl.setBdanRemarks(newBdmTlMst.getBdmsRemarks());
				else
					bdmTlDtl.setBdanRemarks("{}");
			}

			if (bdmTlDtl.getBdanErppoststatus() == null)
				bdmTlDtl.setBdanErppoststatus("X");
			CommonFunctions.debugMsg("Status In Service Impl : " + bdmTlDtl.getBdanErppoststatus());
			if (bdmTlDtl.getBdanErppoststatus() != null) {
				if (bdmTlDtl.getBdanErppoststatus().equals("BOOKING"))
					bdmTlDtl.setBdanErppoststatus("X");
				else if (bdmTlDtl.getBdanErppoststatus().equals("BOOKED"))
					bdmTlDtl.setBdanErppoststatus("B");
				else if (bdmTlDtl.getBdanErppoststatus().equals("ALLOTTED"))
					bdmTlDtl.setBdanErppoststatus("A");
				else if (bdmTlDtl.getBdanErppoststatus().equals("WORK IN PROGRESS"))
					bdmTlDtl.setBdanErppoststatus("W");
				else
					bdmTlDtl.setBdanErppoststatus("X");
			}

			CommonFunctions.debugMsg("Status In Service Impl : " + bdmTlDtl.getBdanErppoststatus());
			if (bdmTlDtl.getBdanProblemseverity() == null)
				bdmTlDtl.setBdanProblemseverity("X");

			if (bdmTlDtl.getBdanErpnumber() == null)
				bdmTlDtl.setBdanErpnumber("0");

			if (bdmTlDtl.getBdanIsapproved() == null)
				bdmTlDtl.setBdanIsapproved("X");

			if (bdmTlDtl.getBdanApproverdby() == null)
				bdmTlDtl.setBdanApproverdby("{}");

			if (bdmTlDtl.getBdanCostcentre() == null)
				bdmTlDtl.setBdanCostcentre("{}");

			if (bdmTlDtl.getBdanFailuretype() == null)
				bdmTlDtl.setBdanFailuretype("{}");

			if (bdmTlDtl.getBdanClassificationid() == null)
				bdmTlDtl.setBdanClassificationid("{}");

			if (bdmTlDtl.getBdanActiontakenby() == null)
				bdmTlDtl.setBdanActiontakenby("{}");

			if (bdmTlDtl.getBdanCompletedby() == null)
				bdmTlDtl.setBdanCompletedby("{}");

			if (bdmTlDtl.getBdanOtherFailuretype() == null)
				bdmTlDtl.setBdanOtherFailuretype("{}");

			newBdmTlDtlList.add(bdmTlDtl);
		}

		return newBdmTlDtlList;
	}

	private List<BAL_BdmTlShiftwisesplit> shiftFillValues(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst,
			BAL_BDFormBean bdFormBean) {
		System.out.println("Shift Fill Values");
		String dateTime = CommonFunctions.dateTimeNow();
		List<BAL_BdmTlShiftwisesplit> newBdmTlShiftwisesplit = newBdmTlMst.getBdmShiftwise();
		List<BAL_BdmTlShiftwisesplit> oldBdmTlShiftwisesplit = null;
		BAL_BdmTlShiftwisesplit oldBdmTlShiftwisesplitValues = null;
		if (oldBdmTlMst != null) {
			oldBdmTlShiftwisesplit = oldBdmTlMst.getBdmShiftwise();

			if (oldBdmTlShiftwisesplit != null && oldBdmTlShiftwisesplit.size() > 0) {
				oldBdmTlShiftwisesplitValues = oldBdmTlShiftwisesplit.get(0);

			}
		}
		List<BAL_BdmTlShiftwisesplit> newBdmTlShiftwiseList = new ArrayList<BAL_BdmTlShiftwisesplit>();
		for (BAL_BdmTlShiftwisesplit bdmTlShiftwisesplit : newBdmTlShiftwisesplit) {
			if (bdmTlShiftwisesplit.getBdssKeyid() == null) {
				bdmTlShiftwisesplit.setBdssCreatedon(dateTime);
			} else {
				bdmTlShiftwisesplit.setBdssCreatedon(dateTime);
			}

			bdmTlShiftwisesplit.setBdssModifiedon(dateTime);
			bdmTlShiftwisesplit.setBdssActive("Y");
			bdmTlShiftwisesplit.setBdssCreatedby(newBdmTlMst.getBdmsCreatedby());

			if (bdmTlShiftwisesplit.getBdssFactoryid() == null) {
				if (newBdmTlMst.getBdmsFactoryid() != null)
					bdmTlShiftwisesplit.setBdssFactoryid(newBdmTlMst.getBdmsFactoryid());
				else
					bdmTlShiftwisesplit.setBdssFactoryid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssSectionid() == null) {
				if (newBdmTlMst.getBdmsSectionid() != null)
					bdmTlShiftwisesplit.setBdssSectionid(newBdmTlMst.getBdmsSectionid());
				else
					bdmTlShiftwisesplit.setBdssSectionid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssCellid() == null) {
				if (newBdmTlMst.getBdmsCellid() != null)
					bdmTlShiftwisesplit.setBdssCellid(newBdmTlMst.getBdmsCellid());
				else
					bdmTlShiftwisesplit.setBdssCellid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssMachineid() == null) {
				if (newBdmTlMst.getBdmsMachineid() != null)
					bdmTlShiftwisesplit.setBdssMachineid(newBdmTlMst.getBdmsMachineid());
				else
					bdmTlShiftwisesplit.setBdssMachineid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssAssemblyid() == null) {
				if (newBdmTlMst.getBdmsAssemblyid() != null)
					bdmTlShiftwisesplit.setBdssAssemblyid(newBdmTlMst.getBdmsAssemblyid());
				else
					bdmTlShiftwisesplit.setBdssAssemblyid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssPhenomenaid() == null) {
				if (newBdmTlMst.getBdmsBookedphenomena() != null)
					bdmTlShiftwisesplit.setBdssPhenomenaid(newBdmTlMst.getBdmsBookedphenomena());
				else
					bdmTlShiftwisesplit.setBdssPhenomenaid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssCauseid() == null) {
				if (newBdmTlMst.getBdmsBookedcause() != null)
					bdmTlShiftwisesplit.setBdssCauseid(newBdmTlMst.getBdmsBookedcause());
				else
					bdmTlShiftwisesplit.setBdssCauseid("{}");
			}
			if (bdmTlShiftwisesplit.getBdssBdentrydate() == null) {
				if (newBdmTlMst.getBdmsReporteddate() != null)
					bdmTlShiftwisesplit.setBdssBdentrydate(newBdmTlMst.getBdmsReporteddate());
				else
					bdmTlShiftwisesplit.setBdssBdentrydate(dateTime);
			}
			if (bdmTlShiftwisesplit.getBdssBdsplitdate() == null)
				bdmTlShiftwisesplit.setBdssBdsplitdate(dateTime);
			if (bdmTlShiftwisesplit.getBdssShiftid() == null)
				bdmTlShiftwisesplit.setBdssShiftid("{}");
			if (bdmTlShiftwisesplit.getBdssBdno() == null)
				bdmTlShiftwisesplit.setBdssBdno("{}");
			if (bdmTlShiftwisesplit.getBdssDowntime() == null)
				bdmTlShiftwisesplit.setBdssDowntime("{}");
			if (bdmTlShiftwisesplit.getBdssNoplantime() == null)
				bdmTlShiftwisesplit.setBdssNoplantime("{}");
			if (bdmTlShiftwisesplit.getBdssRelatedto() == null)
				bdmTlShiftwisesplit.setBdssRelatedto("XXX");
			if (bdmTlShiftwisesplit.getBdssActivityno() == null)
				bdmTlShiftwisesplit.setBdssActivityno("{}");

			newBdmTlShiftwiseList.add(bdmTlShiftwisesplit);
		}
		return newBdmTlShiftwiseList;

	}

	private BAL_BdmTlNewphncausereq fillPhenValues(BAL_BdmTlNewphncausereq newBdmTlNewphncausereq,
			BAL_BdmTlNewphncausereq oldBdmTlNewphncausereq, BAL_BDFormBean bdFormBean) {

		String[] bdDetails = null;
		System.out.println(bdFormBean.getBphmBddetails());
		newBdmTlNewphncausereq.setBnprActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if (newBdmTlNewphncausereq.getBnprKeyid() == null) {
			newBdmTlNewphncausereq.setBnprCreatedon(dateTime);
		} else {
			newBdmTlNewphncausereq.setBnprCreatedon(dateTime);
		}

		newBdmTlNewphncausereq.setBnprModifiedon(dateTime);
		if (bdFormBean.getBphmBddetails() != null) {
			bdDetails = bdFormBean.getBphmBddetails().split(">");
		}

		if (newBdmTlNewphncausereq.getBnprRequesteddate() == null)
			newBdmTlNewphncausereq.setBnprRequesteddate(dateTime);
		if (newBdmTlNewphncausereq.getBnprRequestedby() == null)
			newBdmTlNewphncausereq.setBnprRequestedby(newBdmTlNewphncausereq.getBnprCreatedby());
		if (newBdmTlNewphncausereq.getBnprDocno() == null) {
			if (bdFormBean.getBphmBddetails() != null)
				newBdmTlNewphncausereq.setBnprDocno(bdDetails[0]);
			else
				newBdmTlNewphncausereq.setBnprDocno("{}");
		}
		if (newBdmTlNewphncausereq.getBnprDocdate() == null) {
			if (bdFormBean.getBphmBddetails() != null)
				newBdmTlNewphncausereq.setBnprDocdate(bdDetails[1]);
			else
				newBdmTlNewphncausereq.setBnprDocdate(dateTime);
		}
		if (newBdmTlNewphncausereq.getBnprPhenomenatype() == null)
			newBdmTlNewphncausereq.setBnprPhenomenatype("BD");
		if (newBdmTlNewphncausereq.getBnprProposedphn() == null)
			newBdmTlNewphncausereq.setBnprProposedphn("{}");
		if (newBdmTlNewphncausereq.getBnprIscausereq() == null)
			newBdmTlNewphncausereq.setBnprIscausereq("Y");
		if (newBdmTlNewphncausereq.getBnprProposedcause() == null)
			newBdmTlNewphncausereq.setBnprProposedcause("{}");
		if (newBdmTlNewphncausereq.getBnprApprovedphn() == null) {
			if (bdFormBean.getBphmAssemblyid() != null)
				newBdmTlNewphncausereq.setBnprApprovedphn(bdFormBean.getBphmAssemblyid());
			else
				newBdmTlNewphncausereq.setBnprApprovedphn("{}");
		}
		if (newBdmTlNewphncausereq.getBnprIscausereqapp() == null)
			newBdmTlNewphncausereq.setBnprIscausereqapp("N");
		if (newBdmTlNewphncausereq.getBnprApprovedcause() == null)
			newBdmTlNewphncausereq.setBnprApprovedcause("{}");
		if (newBdmTlNewphncausereq.getBnprOldphenomenaid() == null)
			newBdmTlNewphncausereq.setBnprOldphenomenaid("{}");
		if (newBdmTlNewphncausereq.getBnprOldcauseid() == null)
			newBdmTlNewphncausereq.setBnprOldcauseid("{}");
		if (newBdmTlNewphncausereq.getBnprNewphenomenaid() == null)
			newBdmTlNewphncausereq.setBnprNewphenomenaid("{}");
		if (newBdmTlNewphncausereq.getBnprNewcauseid() == null)
			newBdmTlNewphncausereq.setBnprNewcauseid("{}");
		if (newBdmTlNewphncausereq.getBnprIsalreadyexist() == null)
			newBdmTlNewphncausereq.setBnprIsalreadyexist("N");
		if (newBdmTlNewphncausereq.getBnprApprovedby() == null)
			newBdmTlNewphncausereq.setBnprApprovedby("{}");
		if (newBdmTlNewphncausereq.getBnprApproveddate() == null)
			newBdmTlNewphncausereq.setBnprApproveddate(dateTime);
		if (newBdmTlNewphncausereq.getBnprApprovedflag() == null)
			newBdmTlNewphncausereq.setBnprApprovedflag("N");

		return newBdmTlNewphncausereq;
	}

	private BAL_BdmTlPhenomenamst fillPhenomenaValues(BAL_BdmTlPhenomenamst newBdmTlPhenomenamst,
			BAL_BdmTlPhenomenamst oldBdmTlPhenomenamst, BAL_BDFormBean bdFormBean) {

		newBdmTlPhenomenamst.setBphmActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		if (newBdmTlPhenomenamst.getBphmKeyid() == null) {
			newBdmTlPhenomenamst.setBphmCreatedon(dateTime);
		} else {
			newBdmTlPhenomenamst.setBphmCreatedon(dateTime);
		}
		newBdmTlPhenomenamst.setBphmModifiedon(dateTime);

		if (newBdmTlPhenomenamst.getBphmPhenomenatype() == null)
			newBdmTlPhenomenamst.setBphmPhenomenatype("BD");
		if (newBdmTlPhenomenamst.getBphmPhenomenaname() == null)
			newBdmTlPhenomenamst.setBphmPhenomenaname("{}");
		if (newBdmTlPhenomenamst.getBphmShortname() == null)
			newBdmTlPhenomenamst.setBphmShortname("{}");
		if (newBdmTlPhenomenamst.getBphmRemarks() == null)
			newBdmTlPhenomenamst.setBphmRemarks("{}");
		if (newBdmTlPhenomenamst.getBphmAssemblyid() == null)
			newBdmTlPhenomenamst.setBphmAssemblyid("{}");
		if (newBdmTlPhenomenamst.getBphmLevelno() == null)
			newBdmTlPhenomenamst.setBphmLevelno("1");
		if (newBdmTlPhenomenamst.getBphmIsphnnotdefined() == null)
			newBdmTlPhenomenamst.setBphmIsphnnotdefined("N");
		if (newBdmTlPhenomenamst.getBphmCausenotneeded() == null)
			newBdmTlPhenomenamst.setBphmCausenotneeded("Y");
		if (newBdmTlPhenomenamst.getBphmChildflag() == null)
			newBdmTlPhenomenamst.setBphmChildflag("N");

		return newBdmTlPhenomenamst;

	}

	private WomTlCommunicationlog fillComTxtValues(WomTlCommunicationlog newWomTlCommunicationlog,
			WomTlCommunicationlog oldWomTlCommunicationlog, BAL_BDFormBean bdFormBean) {
		// TODO Auto-generated method stub
		newWomTlCommunicationlog.setWcmlActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		newWomTlCommunicationlog.setWcmlCreatedon(dateTime);
		newWomTlCommunicationlog.setWcmlModifiedon(dateTime);
		newWomTlCommunicationlog.setWcmlDate(dateTime);

		if (newWomTlCommunicationlog.getWcmlWonumber() == null)
			newWomTlCommunicationlog.setWcmlWonumber("{}");
		if (newWomTlCommunicationlog.getWcmlCommunicationtext() == null)
			newWomTlCommunicationlog.setWcmlCommunicationtext("{}");
		if (newWomTlCommunicationlog.getWcmlEnteredby() == null)
			newWomTlCommunicationlog.setWcmlEnteredby("{}");
		// if(newWomTlCommunicationlog.getWcmlLevel()== null)
		// newWomTlCommunicationlog.setWcmlLevel("INFORMATION TECHNOLOGY");
		if (newWomTlCommunicationlog.getWcmlDisplayorderno() == null)
			newWomTlCommunicationlog.setWcmlDisplayorderno("2");

		System.out.println("1 " + newWomTlCommunicationlog.getWcmlCreatedon());
		System.out.println(newWomTlCommunicationlog.getWcmlLevel());
		return newWomTlCommunicationlog;
	}

	@Override
	public SapExternalServiceMst createExtService(SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "create";
			validations.validate(newSapExternalServiceMst, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																								// - defined rules for
																								// server side
																								// validations
			fillValuesExterServices(newSapExternalServiceMst, existSapExternalServiceMst, bdFormBean);
			CommonFunctions.debugMsg("ceatedBYService  " + newSapExternalServiceMst.getExtmActive());
			CommonFunctions.debugMsg("After Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.createExtService(newSapExternalServiceMst);
	}

	private SapExternalServiceMst fillValuesExterServices(SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BAL_BDFormBean bdFormBean) {
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println("Key ID : " + newSapExternalServiceMst.getExtmKeyid());
		if (UIUtils.isValidKeyId(existSapExternalServiceMst.getExtmKeyid())) {
			newSapExternalServiceMst.setExtmCreatedon(existSapExternalServiceMst.getExtmCreatedon());
		} else {
			newSapExternalServiceMst.setExtmCreatedon(dateTime);
		}
		newSapExternalServiceMst.setExtmModifiedon(dateTime);
		newSapExternalServiceMst.setExtmActive("Y");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmAgreement()))
			newSapExternalServiceMst.setExtmAgreement("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmCostElement()))
			newSapExternalServiceMst.setExtmCostElement("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmDate()))
			newSapExternalServiceMst.setExtmDate(Constants.passNullDate);
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmEquipment()))
			newSapExternalServiceMst.setExtmEquipment("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmExtSubContract()))
			newSapExternalServiceMst.setExtmExtSubContract("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmFlid()))
			newSapExternalServiceMst.setExtmFlid("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmFwOrder()))
			newSapExternalServiceMst.setExtmFwOrder("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmInfoRecord()))
			newSapExternalServiceMst.setExtmInfoRecord("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmMaterialGroup()))
			newSapExternalServiceMst.setExtmMaterialGroup("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmNotificationno()))
			newSapExternalServiceMst.setExtmNotificationno("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmOperationQty()))
			newSapExternalServiceMst.setExtmOperationQty("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmOrderno()))
			newSapExternalServiceMst.setExtmOrderno("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPer()))
			newSapExternalServiceMst.setExtmPer("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPlanDeliverytime()))
			newSapExternalServiceMst.setExtmPlanDeliverytime(Constants.futureNullDate);
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPrice()))
			newSapExternalServiceMst.setExtmPrice("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmPurchaseGroup()))
			newSapExternalServiceMst.setExtmPurchaseGroup("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmRecipient()))
			newSapExternalServiceMst.setExtmRecipient("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmRequisitioner()))
			newSapExternalServiceMst.setExtmRequisitioner("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmShift()))
			newSapExternalServiceMst.setExtmShift("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmSortterm()))
			newSapExternalServiceMst.setExtmSortterm("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmTrackNo()))
			newSapExternalServiceMst.setExtmTrackNo("0");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmType()))
			newSapExternalServiceMst.setExtmType("N");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmUnloadPoint()))
			newSapExternalServiceMst.setExtmUnloadPoint("{}");
		if (!UIUtils.isValidKeyId(newSapExternalServiceMst.getExtmVendor()))
			newSapExternalServiceMst.setExtmVendor("{}");

		// TODO Auto-generated method stub
		return newSapExternalServiceMst;
	}

	@Override
	public SapExternalServiceMst updateExtService(SapExternalServiceMst newSapExternalServiceMst,
			SapExternalServiceMst existSapExternalServiceMst, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "update";
			validations.validate(newSapExternalServiceMst, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																								// - defined rules for
																								// server side
																								// validations
			fillValuesExterServices(newSapExternalServiceMst, existSapExternalServiceMst, bdFormBean);

			CommonFunctions.debugMsg("After Update Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.updateExtService(newSapExternalServiceMst);
	}

	@Override
	public SapExternalServiceMst getExtServiceData(String extKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getExtServiceData(extKeyid);
	}

	@Override
	public SapExternalServiceDtl createExtServiceDtl(SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "create";
			validations.validate(newSapExternalServiceDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																								// - defined rules for
																								// server side
																								// validations
			fillValuesExterServicesDtl(newSapExternalServiceDtl, existSapExternalServiceDtl, bdFormBean);
			CommonFunctions.debugMsg("ceatedBYService  " + newSapExternalServiceDtl.getExtdCreatedby());
			CommonFunctions.debugMsg("After Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.createExtServiceDtl(newSapExternalServiceDtl);

	}

	private SapExternalServiceDtl fillValuesExterServicesDtl(SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl, BAL_BDFormBean bdFormBean) {
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println("Key ID : " + newSapExternalServiceDtl.getExtdKeyid());
		if (UIUtils.isValidKeyId(existSapExternalServiceDtl.getExtdKeyid())) {
			newSapExternalServiceDtl.setExtdCreatedon(existSapExternalServiceDtl.getExtdCreatedon());
		} else {
			newSapExternalServiceDtl.setExtdCreatedon(dateTime);
		}
		newSapExternalServiceDtl.setExtdModifiedon(dateTime);
		newSapExternalServiceDtl.setExtdActive("Y");
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdCostElement())) {
			newSapExternalServiceDtl.setExtdCostElement("{}");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdCurrency())) {
			newSapExternalServiceDtl.setExtdCurrency("{}");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdQty())) {
			newSapExternalServiceDtl.setExtdQty("0");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdServiceNo())) {
			newSapExternalServiceDtl.setExtdServiceNo("{}");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdServiceText())) {
			newSapExternalServiceDtl.setExtdServiceText("{}");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdUom())) {
			newSapExternalServiceDtl.setExtdUom("{}");
		}
		if (!UIUtils.isValidKeyId(newSapExternalServiceDtl.getExtdValue())) {
			newSapExternalServiceDtl.setExtdValue("0");
		}
		return newSapExternalServiceDtl;
		// TODO Auto-generated method stub

	}

	@Override
	public SapExternalServiceDtl updateExtServiceDtl(SapExternalServiceDtl newSapExternalServiceDtl,
			SapExternalServiceDtl existSapExternalServiceDtl, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "upddate";
			validations.validate(newSapExternalServiceDtl, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																								// - defined rules for
																								// server side
																								// validations
			fillValuesExterServicesDtl(newSapExternalServiceDtl, existSapExternalServiceDtl, bdFormBean);
			CommonFunctions.debugMsg("ceatedBYService  " + newSapExternalServiceDtl.getExtdCreatedby());
			CommonFunctions.debugMsg("After Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.UpdateExtServiceDtl(newSapExternalServiceDtl);

	}

	@Override
	public SapExternalServiceDtl getserviceDtlData(String detailKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getServiceDtlData(detailKeyid);
	}

	@Override
	public String delteExtDetail(String detailKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.delteExtDetail(detailKeyid);
	}

	@Override
	public SapExternalRepair createExtRepairDtl(SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "create";
			validations.validate(newSapExternalRepair, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																							// - defined rules for
																							// server
																							// side validations
			fillValuesExterRepairDtl(newSapExternalRepair, existSapExternalRepair, bdFormBean);
			CommonFunctions.debugMsg("ceatedBYService  " + newSapExternalRepair.getExtrCreatedby());
			CommonFunctions.debugMsg("After Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.createExtRepairDtl(newSapExternalRepair);

	}

	@Override
	public SapExternalRepair updateExtRepairDtl(SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BAL_BDFormBean bdFormBean) throws Exception {
		// TODO Auto-generated method stub
		try {

			String validationsFor;
			validationsFor = "update";
			validations.validate(newSapExternalRepair, "Bal_bdcreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																							// - defined rules for
																							// server
																							// side validations
			fillValuesExterRepairDtl(newSapExternalRepair, existSapExternalRepair, bdFormBean);

			CommonFunctions.debugMsg("After Update Filling Values ExterServices");

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
		return bdmTlMstDao.updateExtRepair(newSapExternalRepair);
	}

	private SapExternalRepair fillValuesExterRepairDtl(SapExternalRepair newSapExternalRepair,
			SapExternalRepair existSapExternalRepair, BAL_BDFormBean bdFormBean) {
		String dateTime = CommonFunctions.dateTimeNow();
		System.out.println("Key ID : " + newSapExternalRepair.getExtrKeyid());
		/*
		 * if(UIUtils.isValidKeyId(existSapExternalRepair.getExtrKeyid()) ) {
		 * newSapExternalRepair.setExtrCreatedon(existSapExternalRepair.getExtrCreatedon
		 * ()); } else {
		 */
		newSapExternalRepair.setExtrCreatedon(dateTime);
		// }
		newSapExternalRepair.setExtrModifiedon(dateTime);
		newSapExternalRepair.setExtrActive("Y");
		CommonFunctions.debugMsg("getExtrActive  " + newSapExternalRepair.getExtrActive());
		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrComponentNo()))
			newSapExternalRepair.setExtrComponentNo("{}");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrItemCategory()))
			newSapExternalRepair.setExtrItemCategory("{}");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrMatReworkIndi()))
			newSapExternalRepair.setExtrMatReworkIndi("{}");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrPartno()))
			newSapExternalRepair.setExtrPartno("{}");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrRequirementQty()))
			newSapExternalRepair.setExtrRequirementQty("0");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrStorageLocation()))
			newSapExternalRepair.setExtrStorageLocation("{}");

		if (!UIUtils.isValidKeyId(newSapExternalRepair.getExtrUom()))
			newSapExternalRepair.setExtrUom("{}");

		return newSapExternalRepair;
		// TODO Auto-generated method stub

	}

	@Override
	public SapExternalRepair getRepairDtlData(String detailRepairKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getRepairDtlData(detailRepairKeyid);
	}

	@Override
	public String delteExtRprDetail(String rpeDetailKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.delteExtRprDetail(rpeDetailKeyid);
	}

	@Override
	public SapTlMaintenanceOrdermst getSapSpareInFoData(String bdKeyid) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getSapSpareInFoData(bdKeyid);
	}

	public List<String[]> getRepeatedBreakDown(CommonParams commonParams) throws Exception {
		return bdmTlMstDao.getRepeatedBreakDown(commonParams);
	}

	@Override
	public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.getBDAnalysisRpt(commonFilter);
	}

	@Override
	public Workbook bdRptSummaryExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return this.bdmTlMstDao.bdRptSummaryExportExcel(commonFilter, tblJSONObj, format);
	}

	/*
	 * @Override public List<ComboBox> getSubAssembly(String machineId, String
	 * assmId, ComboFilter comboFilter) throws Exception { // TODO Auto-generated
	 * method stub
	 * 
	 * if(UIUtils.isValidKeyId(assmId)) { comboFilter.setIdField("SBAM_KEYID");
	 * //subAssembly.setCodeField("SBAM_CODE");
	 * comboFilter.setNameField("SBAM_NAME"); StringBuilder CondSql=new
	 * StringBuilder(); CondSql.
	 * append(" and SBAM_KEYID in ( select fnln_originalid from gen_tl_functionallocn"
	 * ) ;
	 * CondSql.append(" where fnln_elementtype = 'SAM' AND instr(fnln_parentid, '");
	 * CondSql.append(machineId); CondSql.append("-"); CondSql.append(assmId);
	 * CondSql.append("')>0 )"); comboFilter.setCondSql(CondSql.toString());
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST); } else {
	 * comboFilter.setIdField("SBAM_KEYID");
	 * //subAssembly.setCodeField("SBAM_CODE");
	 * comboFilter.setNameField("SBAM_NAME");
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST); }
	 * comboFilter.setNameField("FNLN_DISPLAYCODE");
	 * comboFilter.setIdField("FNLN_ORIGINALID");
	 * comboFilter.setTableName("gen_tl_functionallocn"); condSql =
	 * "AND FNLN_ELEMENTTYPE = 'SPR'"; if (UIUtils.isValidKeyId(assmId)) condSql +=
	 * "AND INSTR(FNLN_PARENTID, '"+assmId+"')>0 "; comboFilter.setCondSql(condSql);
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */

	// mano 0206
	// commented and added by priyanka 
	/*
	 * @Override public List<ComboBox> getSubAssembly(String machineId, String
	 * assmId, ComboFilter comboFilter) throws Exception {
	 * 
	 * if (UIUtils.isValidKeyId(assmId)) {
	 * 
	 * comboFilter.setIdField("SBAM_KEYID"); comboFilter.setNameField("SBAM_NAME");
	 * 
	 * StringBuilder condSql = new StringBuilder();
	 * 
	 * condSql.append(" AND SBAM_KEYID IN ( ");
	 * condSql.append(" SELECT FNLN_ORIGINALID ");
	 * condSql.append(" FROM GEN_TL_FUNCTIONALLOCN ");
	 * condSql.append(" WHERE FNLN_ELEMENTTYPE = 'SAM' ");
	 * condSql.append(" AND POSITION('"); condSql.append(machineId);
	 * condSql.append("-"); condSql.append(assmId);
	 * condSql.append("' IN FNLN_PARENTID) > 0 "); condSql.append(" ) ");
	 * 
	 * comboFilter.setCondSql(condSql.toString());
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST);
	 * 
	 * } else {
	 * 
	 * comboFilter.setIdField("SBAM_KEYID"); comboFilter.setNameField("SBAM_NAME");
	 * comboFilter.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST); }
	 * 
	 * return commonFilterDao.fillComboValues(comboFilter); }
	 */

	@Override
	public List<ComboBox> getSubAssembly(String machineId, String assmId,
	        ComboFilter comboFilter) throws Exception {

	    System.out.println("getSubAssembly assmId=[" + assmId + "] machineId=[" + machineId + "]");

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

	    System.out.println("getSubAssembly condSql=[" + condSql + "]");
	    comboFilter.setCondSql(condSql);

	    return commonFilterDao.fillComboValues(comboFilter);
	}
	// end
	
	@Override
	public List<String[]> getMultipleReposibility(String bdEmrNo, CommonParams commonParams) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getMultipleResponsibility(bdEmrNo, commonParams);
	}

	@Override
	public BAL_BdmTlDtl updateErpStatus(BAL_BdmTlDtl newBdmTlDtl, BAL_BdmTlDtl existBdmTlDtl) throws Exception {
		// TODO Auto-generated method stub
		CommonFunctions.debugMsg("in side service implre");
		return bdmTlDtlDao.getUpdateErpStatus(newBdmTlDtl);
	}

	@Override
	public List<String[]> getBreakDownList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.getBreakDownList(commonFilter);
	}

	@Override
	public BAL_BdmTlMst createpcsBd(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst, BAL_BDFormBean bdFormBean)
			throws ValidationExceptions, Exception {

		try {

			String validationsFor;

			// if(employeeBean.getFormActionMode() != null &&
			// employeeBean.getFormActionMode().equals("emp") )
			// validationsFor = "emp";
			// else
			System.out.println("in service impl,,,,,,,,,,,,,,,,,,");
			validationsFor = "create";
			if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag())) {

				if (newBdmTlMst.getBdmsRepeatedbdflag().equals("Y")) {
					CommonFunctions.debugMsg("YES");
				} else {
					validations.validate(newBdmTlMst, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																				// - defined rules for server side
																				// validations
					validations.validate(bdFormBean, "PcsBd", validationsFor);

					List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
					for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
						validations.validate(bdmTlDtl, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																				// - defined rules for server side
																				// validations
					}
				}
			} else {
				validations.validate(newBdmTlMst, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																			// - defined rules for server side
																			// validations
				validations.validate(bdFormBean, "PcsBd", validationsFor);

				List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
				for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
					validations.validate(bdmTlDtl, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																			// - defined rules for server side
																			// validations
				}

			}

			fillValues(newBdmTlMst, oldBdmTlMst, bdFormBean);

			// PcsTlPcsBd oldPcsBd = new PcsTlPcsBd ();
			// pcsbdFillValues(newPcsBd);

			/*
			 * String phen = getPhenType(newBdmTlMst.getBdmsFinalphenomena());
			 * if(UIUtils.isValidKeyId(phen)) { if(phen.equals("ND")) throw new
			 * BusinessApplicationExceptions("UndefinedPhen"); }
			 */
			String strAPstatus = "0";
			if (newBdmTlMst.getBdmsStatus().equals("C"))
				strAPstatus = bdmTlMstDao.getActionPlanDetails(newBdmTlMst);
			;

			if (Integer.parseInt(strAPstatus) > 0)
				throw new BusinessApplicationExceptions("Action Plan pending");
			else {

				return bdmTlMstDao.createPcsBd(newBdmTlMst);
			}

			// return bdmTlMstDao.create(newBdmTlMst);

		} catch (ValidationExceptions e) {
			CommonFunctions.debugMsg("e.getMessage():" + e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}
	}

	public BAL_BdmTlMst updatepcsBd(BAL_BdmTlMst newBdmTlMst, BAL_BdmTlMst oldBdmTlMst, BAL_BDFormBean bdFormBean,
			WomTlWomst womTlWomst) throws BusinessApplicationExceptions, Exception {
		String validationsFor = "update";

		if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsRepeatedbdflag())) {
			System.out.println("Inside service IMPL : " + newBdmTlMst.getBdmsRepeatedbdflag());
			if (newBdmTlMst.getBdmsRepeatedbdflag().equals("Y")) {
				CommonFunctions.debugMsg("YES");
			} else {

				validations.validate(newBdmTlMst, "PcsBd", "update");// com.akranta.validations.tpm.validations.kaizencreation.xml
																		// - defined rules for server side validations

				System.out.println("After Validation in Mst S Upd");
				List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
				for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
					validations.validate(bdmTlDtl, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																			// - defined rules for server side
																			// validations
				}
			}
		} else {

			validations.validate(newBdmTlMst, "PcsBd", "update");// com.akranta.validations.tpm.validations.kaizencreation.xml
																	// - defined rules for server side validations

			System.out.println("After Validation in Mst S Upd");
			List<BAL_BdmTlDtl> bddtls = newBdmTlMst.getBdmDetail();
			for (BAL_BdmTlDtl bdmTlDtl : bddtls) {
				validations.validate(bdmTlDtl, "PcsBd", validationsFor);// com.akranta.validations.tpm.validations.employee.xml
																		// - defined rules for server side validations
				CommonFunctions.debugMsg(
						"Final Action in Service IMPL : " + bdmTlDtl.getBdanFinalaction() + " --------------> "
								+ newBdmTlMst.getBdmsWoendflag() + " : " + newBdmTlMst.getBdmsWostartflag());

			}

		}

		System.out.println("After Update");
		fillValues(newBdmTlMst, oldBdmTlMst, bdFormBean);
		if (UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoprodaccepflag())
				&& UIUtils.isValidKeyId(newBdmTlMst.getBdmsWoendflag())) {
			if (newBdmTlMst.getBdmsWoprodaccepflag().equals("Y") && newBdmTlMst.getBdmsWoendflag().equals("Y")) {
				validations.validate(newBdmTlMst, "PcsBd", "prodAcceptance");
			}
		}
		// if(newBdmTlMst.getBdmsWostartflag().equals("Y") &&
		// newBdmTlMst.getBdmsWoendflag().equals("Y"))
		// validations.validate(newBdmTlMst,"bdcreation","validDateBD");
		String strAPstatus = "0";
		if (newBdmTlMst.getBdmsStatus().equals("C"))
			strAPstatus = bdmTlMstDao.getActionPlanDetails(newBdmTlMst);
		;

		if (Integer.parseInt(strAPstatus) > 0)
			throw new BusinessApplicationExceptions("ActionPlan");
		else {
			return bdmTlMstDao.updatepcsBd(newBdmTlMst, womTlWomst);
		}
	}

	@Override
	public BAL_BdmTlMst deletePcsbd(BAL_BdmTlMst bdmTlMst) throws Exception {
		// TODO Auto-generated method stub
		return bdmTlMstDao.deletePcsbd(bdmTlMst);
	}

}