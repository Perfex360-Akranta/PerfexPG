package com.akranta.tpm.service.impl;

import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.KaizenFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.KznTlMstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.KznTlMstDaoImpl;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.GenTlWorkflowInfo;
import com.akranta.tpm.model.KznTlGraphdata;
import com.akranta.tpm.model.KznTlHdmst;
import com.akranta.tpm.model.KznTlLosslink;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.KznTlPillarlink;
import com.akranta.tpm.model.QtmTlCustcomplaintdtl;
import com.akranta.tpm.service.KaizenServices;
import com.akranta.tpm.service.api.AbnormalityServiceApi;
//import com.akranta.tpm.service.api.KaizenBankMstServiceApi;
import com.akranta.tpm.service.api.KznTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class KaizenServiceImpl implements KaizenServices {

	private KznTlMstDao kznTlMstDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations;
	private KznTlMstServiceApi kznServiceApi;
	DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);

	DateTimeFormatter outputFmtDt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	DateTimeFormatter outputFmtDtTm = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

	public KaizenServiceImpl(DBActionTemplate dbActionTemplate) {
		kznTlMstDao = new KznTlMstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}

	public void setKznTlMstDao(KznTlMstDao kznTlMstDao) {
		this.kznTlMstDao = kznTlMstDao;
	}

	public void KaizenFormServiceImplJwt(String JwtToken) {
		try {
			kznTlMstDao.KznTlMstDaoImplJwt(JwtToken);
			kznServiceApi = new KznTlMstServiceApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public KznTlMst create(KznTlMst newKznTlMst, KznTlMst oldKznTlMst, KaizenFormBean kaizenFormBean,
			String apprvallevel) throws BusinessApplicationExceptions, ValidationExceptions, Exception {

		try {

			String validationsFor;

			if (kaizenFormBean.getFormActionMode() != null && kaizenFormBean.getFormActionMode().equals("whywhy"))
				validationsFor = "whywhy";
			else if (kaizenFormBean.getFormActionMode() != null && kaizenFormBean.getFormActionMode().equals("hdScan"))
				validationsFor = "hdScan";
			else
				validationsFor = "create";

			/*
			 * CommonMessage.debugMsg("validationsFor ="+validationsFor);
			 * if(newKznTlMst.getKznmRelatedto().equalsIgnoreCase("MLD"))
			 * validations.validate(newKznTlMst,"kaizencreation","mould");
			 */
			validations.validate(newKznTlMst, "kaizencreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																				// - defined rules for server side
																				// validations
			validations.validate(kaizenFormBean, "kaizencreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																					// - defined rules for server side
																					// validations
			CommonMessage.debugMsg("YY ID : " + kaizenFormBean.getYyId());
			fillValues(newKznTlMst, oldKznTlMst, kaizenFormBean, apprvallevel);
			BdmTlYycountermeasurelink bdmTlYycountermeasurelink = fillYYLinkValues(newKznTlMst, kaizenFormBean);
			GenTlDocupdates genTlDocupdates = fillDocUpdates(newKznTlMst, kaizenFormBean);
			QtmTlCustcomplaintdtl qtmTlCustcomplaintdtl = fillCustomerDetails(newKznTlMst, kaizenFormBean);
			// return
			 //kznTlMstDao.create(newKznTlMst,bdmTlYycountermeasurelink,genTlDocupdates,qtmTlCustcomplaintdtl);
			return kznServiceApi.insertRecord(newKznTlMst, bdmTlYycountermeasurelink, genTlDocupdates,
					qtmTlCustcomplaintdtl);

		} catch (ValidationExceptions e) {
			e.printStackTrace();
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public KznTlMst update(KznTlMst newKznTlMst, KznTlMst oldKznTlMst, KaizenFormBean kaizenFormBean,
			String apprvallevel) throws ValidationExceptions, Exception {
		String validationsFor = "update";
		// if(newKznTlMst.getKznmRelatedto().equalsIgnoreCase("MLD"))
		// validations.validate(newKznTlMst,"kaizencreation","mould");

		validations.validate(newKznTlMst, "kaizencreation", validationsFor);// com.akranta.validations.tpm.validations.kaizencreation.xml
																			// - defined rules for server side
																			// validations
		validations.validate(kaizenFormBean, "kaizencreation", validationsFor);
		fillValues(newKznTlMst, oldKznTlMst, kaizenFormBean, apprvallevel);
		BdmTlYycountermeasurelink bdmTlYycountermeasurelink = fillYYLinkValues(newKznTlMst, kaizenFormBean);
		GenTlDocupdates genTlDocupdates = fillDocUpdates(newKznTlMst, kaizenFormBean);

		// return
		// kznTlMstDao.update(newKznTlMst,bdmTlYycountermeasurelink,genTlDocupdates);
		
		CommonMessage.debugMsg(newKznTlMst.getKznmPresentproblem());
		return kznServiceApi.saveKaizen(newKznTlMst, bdmTlYycountermeasurelink, kaizenFormBean);
	}

	@Override
	public KznTlMst delete(KznTlMst kznTlMst) throws Exception {

		return kznTlMstDao.delete(kznTlMst);
	}

	@Override
	public List<GenTlAllmoduleimgfile> saveKznImg(KznTlMst existKznTlMst, KaizenFormBean kaizenFormBean)
			throws Exception {
		// TODO Auto-generated method stub
		try {
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = existKznTlMst.getAllmoduleimgfile();
			CommonMessage.debugMsg("newGenTlAllmoduleimgfile =" + newGenTlAllmoduleimgfile.size());
			CommonMessage.debugMsg("newGenTlAllmoduleimgfile =" + newGenTlAllmoduleimgfile);
			if (newGenTlAllmoduleimgfile != null) {
				newGenTlAllmoduleimgfile = fillKznImgValues(existKznTlMst);
				return this.kznTlMstDao.saveKznImg(existKznTlMst, kaizenFormBean);
			}
		} catch (Exception e) {
			CommonMessage.debugMsg(e.getMessage());
		}
		return null;
	}

	@Override
	public KznTlMst select(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznServiceApi.getById(kznKeyid);
		// return kznTlMstDao.select(kznKeyid);
	}

	@Override
	public List<String[]> getAllKaizenReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getKaizenReport(commonFilter);
	}

	public List<String[]> getAllKaizenDeleteRpt(CommonFilter commonFilter) throws Exception {
		return kznTlMstDao.getAllKaizenDeleteRpt(commonFilter);
	}

	@Override
	public KznTlMst getkznImage(String fileName, String filePath, KznTlMst kznTlMst)
			throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Service Impl img");
		List<GenTlAllmoduleimgfile> kznImgList = null;

		kznImgList = getKaizenImage(fileName, filePath, kznTlMst.getKznmKeyid());

		if (kznImgList != null) {
			for (GenTlAllmoduleimgfile genTlAllmoduleimgfile : kznImgList) {
				if ("AFT".equals(genTlAllmoduleimgfile.getImflImagetype()))
					kznTlMst.setKznmAfterimage(genTlAllmoduleimgfile.getImflFilename());
				else if ("PRE".equals(genTlAllmoduleimgfile.getImflImagetype()))
					kznTlMst.setKznmPresentimage(genTlAllmoduleimgfile.getImflFilename());
				else if ("RES".equals(genTlAllmoduleimgfile.getImflImagetype()))
					kznTlMst.setKznmResultimage(genTlAllmoduleimgfile.getImflFilename());
				else if ("BEN".equals(genTlAllmoduleimgfile.getImflImagetype()))
					kznTlMst.setKznmBenefitsimage(genTlAllmoduleimgfile.getImflFilename());
			}
			kznTlMst.setAllmoduleimgfile(kznImgList);
		}
		return kznTlMst;
	}

	public List<GenTlAllmoduleimgfile> getKaizenImage(String fileName, String filePath, String kznKeyId)
			throws NoDataFoundException, Exception {
		CommonMessage.debugMsg("kaizen img...test");
		List<GenTlAllmoduleimgfile> kznImgList = new ArrayList<GenTlAllmoduleimgfile>();
		CommonMessage.debugMsg("kaizen img...");
		if (kznKeyId != null) {
			CommonMessage.debugMsg("fileName:" + fileName + ",path:" + filePath + ",kaizId:" + kznKeyId);

			GenTlAllmoduleimgfile kznBeforeImage = new GenTlAllmoduleimgfile();

			kznBeforeImage.setImflBlobimage(filePath);
			kznBeforeImage.setImflFilename(fileName);
			kznBeforeImage.setImflRefkeyid(kznKeyId);
			kznBeforeImage.setImflRefdoctype("KZN");
			kznBeforeImage.setImflImagetype("PRE");
			kznImgList.add(kznBeforeImage);

			GenTlAllmoduleimgfile kznAfterImage = new GenTlAllmoduleimgfile();

			kznAfterImage.setImflBlobimage(filePath);
			kznAfterImage.setImflFilename(fileName);
			kznAfterImage.setImflRefkeyid(kznKeyId);
			kznAfterImage.setImflRefdoctype("KZN");
			kznAfterImage.setImflImagetype("AFT");
			kznImgList.add(kznAfterImage);

			GenTlAllmoduleimgfile kznResultImage = new GenTlAllmoduleimgfile();

			kznResultImage.setImflBlobimage(filePath);
			kznResultImage.setImflFilename(fileName);
			kznResultImage.setImflRefkeyid(kznKeyId);
			kznResultImage.setImflRefdoctype("KZN");
			kznResultImage.setImflImagetype("RES");
			kznImgList.add(kznResultImage);
			CommonMessage.debugMsg("kznImgList:" + kznImgList);

			GenTlAllmoduleimgfile kznBenefitsImage = new GenTlAllmoduleimgfile();

			kznBenefitsImage.setImflBlobimage(filePath);
			kznBenefitsImage.setImflFilename(fileName);
			kznBenefitsImage.setImflRefkeyid(kznKeyId);
			kznBenefitsImage.setImflRefdoctype("KZN");
			kznBenefitsImage.setImflImagetype("BEN");
			kznImgList.add(kznBenefitsImage);
			CommonMessage.debugMsg("kznImgList:" + kznImgList);
		}
		kznImgList = kznTlMstDao.getKznImage(kznImgList);

		return kznImgList;
	}

	public KznTlMst deleteKzn(KznTlMst newKznTlMst) throws Exception {
		return kznTlMstDao.deleteKzn(newKznTlMst);
	}

	public List<ComboBox> getImprovementNoCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("KZNM_KEYID");
		comboFilter.setIdField("KZNM_KEYID");
		comboFilter.setTableName(TableNames.TBL_KZN_TL_MST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getWhyWhyCombo(String condSql, ComboFilter comboFilter) throws Exception {
		// ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("WWMS_KEYID");
		comboFilter.setIdField("WWMS_KEYID");
		if (UIUtils.isValidKeyId(condSql))
			comboFilter.setCondSql(" AND WWMS_REFDOCNO='" + condSql + "' AND WWMS_REFDOCTYPE='KZN'");
		comboFilter.setTableName(TableNames.TBL_BDM_TL_WHYWHYMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> getAllPillarLink(String kznId) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getAllPillarLink(kznId);
	}

	@Override
	public List<String[]> getAllImprvCategory(List<String> pillarId) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkznImprvCategory(pillarId);
	}

	@Override
	public List<String[]> getMultiSelectLoss() throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getMultiSelectLoss();
	}

	@Override
	public List<String[]> getkznHDScanTbl(String kaizenId, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkznHDScanTbl(kaizenId, commonFilter);
	}

	@Override
	public List<String[]> getkznWhyWhyData(String wwmsKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkznWhyWhyData(wwmsKeyid);
	}

	@Override
	public List<String[]> getKaizenCompletedDtls(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getKaizenCompletedDtls(commonFilter);
	}

	@Override
	public KznTlMst deleteKznHd(String khdmkeyIds) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.deleteKznHd(khdmkeyIds);
	}

	@Override
	public List<String[]> getKaizenHDView(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getKaizenHDView(commonFilter);
	}

	@Override
	public List<String[]> updateKznComplete(String kznmKeyid, String khdmKeyid, String remarks, String completedBy,
			String completedDate) throws Exception {
		// TODO Auto-generated method stub

		String modifiedDate = CommonFunctions.getDate();
		return kznTlMstDao.updateKznComplete(kznmKeyid, khdmKeyid, remarks, completedBy, completedDate, modifiedDate);
	}

	@Override
	public KznTlMst updateKznCompletion(KznTlMst kznTlMst) throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("Inside Service impl Validationss.......");
			validations.validate(kznTlMst, "kaizencreation", "Completion");
			return kznTlMstDao.updateKznCompletion(kznTlMst);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}

	@Override
	public KznTlHdmst updateKznHDCompletion(KznTlHdmst kznTlHdmst) throws Exception {
		// TODO Auto-generated method stub
		try {
			// validations.validate(kznTlHdmst,"kaizencreation","updateComplete");
			return kznTlMstDao.updateKznHDCompletion(kznTlHdmst);
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<String[]> getKznHdCellMch(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getKznHdCellMch(kznKeyid);
	}

	@Override
	public List<String[]> getAllGraphData(String kznKeyid, String fromMonth, String toMonth) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getGraphData(kznKeyid, fromMonth, toMonth);
	}

	@Override
	public List<String[]> getResultData(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getResultData(kznKeyid);
	}

	@Override
	public List<String[]> getAllGraphMnths(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getAllGraphMnths(kznKeyid);
	}

	@Override
	public List<String[]> chkForDuplicates(KznTlMst newKznTlMst) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.chkForDuplicates(newKznTlMst);
	}

	@Override
	public List<String[]> fillkznComplete(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.fillkznComplete(kznKeyid);
	}

	@Override
	public KznTlMst updateDocUpdates(KznTlMst kznTlMst, String docId, String yyId) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.updateDocUpdates(kznTlMst, docId, yyId);
	}

	@Override
	public Workbook kaizenRptExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String reportType)
			throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.kaizenRptExportExcel(commonFilter, tblJSONObj, reportType);
	}

	@Override
	public Workbook getEmPillarReportExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getEmPillarReportExcel(colmodel, format, commonFilter);
	}

	@Override
	public Workbook kaizenApprovalExportExcel(CommonFilter commonFilter, JSONObject colmodel, String format)
			throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.kaizenApprovalExportExcel(colmodel, format, commonFilter);
	}

	@Override
	public KznTlMst updateKaizenStatus(String status, String keyid, String nextLevel, String type, String value,
			String approvallevel, String mpvalue, String verifyamount) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.updateKaizenStatus(status, keyid, nextLevel, type, value, approvallevel, mpvalue,
				verifyamount);
	}

	@Override
	public Workbook kaizenCompleteRptExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String reportType,
			boolean isKznHd) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.kaizenCompleteRptExportExcel(commonFilter, tblJSONObj, reportType, isKznHd);
	}

	@Override
	public List<ComboBox> getKpicombo(ComboFilter comboFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		// comboFilter.setCodeField("TRDM_CODE"); KINK_KEYID,KINK_INDICATORNAME
		CommonMessage.debugMsg("KPI Flid" + flid);
		comboFilter.setNameField("KINK_INDICATORNAME");
		comboFilter.setIdField("KINK_KEYID");
		comboFilter.setTableName(" KPI_TL_INDICATOR, KPI_TL_INDICATOR_DEPT_LINK ");
		comboFilter.setCondSql(" AND KIDL_INDICATORID= KINK_KEYID AND KIDL_DEPTID ='" + flid + "'");
		// comboFilter.setCondSql(" AND KIDL_DEPTID ='"+flid+"' " );

		CommonMessage.debugMsg("The comboFilter" + comboFilter);

		return commonFilterDao.fillComboValues(comboFilter);
	}

	public List<ComboBox> getKznNoName(ComboFilter comboFilter) throws Exception {

		comboFilter.setIdField("KZPM_PROJECTNO");
		comboFilter.setCodeField("KZPM_PROJECTNO");
		comboFilter.setNameField("KZPM_PROJECTNAME");
		comboFilter.setTableName("KZN_TL_PROJECTCREATIONMST");
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.FillControlData(keyid);
	}

	@Override
	public List<String[]> FillTeamControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.FillTeamControlData(keyid);
	}

	/** For Image **/
	private List<GenTlAllmoduleimgfile> fillKznImgValues(KznTlMst kznTlMst) {
		List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = kznTlMst.getAllmoduleimgfile();
		List<GenTlAllmoduleimgfile> genTlAllmoduleimgfileList = new ArrayList<GenTlAllmoduleimgfile>();
		for (GenTlAllmoduleimgfile genTlAllmoduleimgfile : newGenTlAllmoduleimgfile) {
			String dateTime = CommonFunctions.pg_dateTimeNow();
			CommonMessage.debugMsg(dateTime);

			genTlAllmoduleimgfile.setImflRefkeyid(kznTlMst.getKznmKeyid());
			genTlAllmoduleimgfile.setImflModifiedon(kznTlMst.getKznmModifiedon());
			long length = 0;

			String fileName = genTlAllmoduleimgfile.getImflFilename();
			if (CommonFunctions.isValidKeyId(fileName)) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
				genTlAllmoduleimgfile.setImflFilename(fileName);
				fileName = genTlAllmoduleimgfile.getImflBlobimage() + fileName;
				CommonMessage.debugMsg(" fileName " + fileName);
				genTlAllmoduleimgfile.setImflBlobimage(fileName);

				if (CommonFunctions.isFileExists(fileName))
					length = new File(fileName).length();

				genTlAllmoduleimgfile.setImflBloblength(Long.toString(length));

				if (genTlAllmoduleimgfile.getImflRefdoctype() == null)
					genTlAllmoduleimgfile.setImflRefdoctype("KZN");
				if (genTlAllmoduleimgfile.getImflTempfield1() == null)
					genTlAllmoduleimgfile.setImflTempfield1("{}");
				if (genTlAllmoduleimgfile.getImflTempfield2() == null)
					genTlAllmoduleimgfile.setImflTempfield2("{}");

				CommonMessage.debugMsg("IMS : " + genTlAllmoduleimgfile.getImflRefkeyid());

				genTlAllmoduleimgfileList.add(genTlAllmoduleimgfile);
			}
		}

		return genTlAllmoduleimgfileList;
	}

	private List<KznTlPillarlink> fillPillarLinkValues(KznTlMst newKznTlMst, KznTlMst oldKznTlMst) {
		CommonMessage.debugMsg("Inside pillar fillval");

		String currentDate = CommonFunctions.getDate();
		List<KznTlPillarlink> newOplTlPillarlink = newKznTlMst.getPillarLink();
		List<KznTlPillarlink> oldKznTlPillarLinks = null;
		KznTlPillarlink oldKznTlPillarLink = null;

		if (oldKznTlMst != null) {
			CommonMessage.debugMsg("before insert INSIDE IF");
			oldKznTlPillarLinks = oldKznTlMst.getPillarLink();

			if (oldKznTlPillarLinks != null && oldKznTlPillarLinks.size() > 0) {
				CommonMessage.debugMsg("before insert IF IFF ");
				oldKznTlPillarLink = oldKznTlPillarLinks.get(0);
				CommonMessage.debugMsg(oldKznTlPillarLink.getKzplCreatedon() + "before insert INSIDE IF "
						+ oldKznTlPillarLinks.size());
			}
		}
		CommonMessage.debugMsg("before insert");

		List<KznTlPillarlink> newOplTlPillarlinkList = new ArrayList<KznTlPillarlink>();
		if (newOplTlPillarlink != null) {
			for (KznTlPillarlink kznTlPillarlink : newOplTlPillarlink) {
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()=" + newKznTlMst.getKznmCreatedon());
				kznTlPillarlink.setKzplActive("Y");

				/*
				 * if(kznTlPillarlink.getDbMode().equals("INSERT")) {
				 */
				kznTlPillarlink.setKzplCreatedon(currentDate);
				/*
				 * } else {
				 * kznTlPillarlink.setKzplCreatedon(oldKznTlPillarLink.getKzplCreatedon()); }
				 */
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()=" + newKznTlMst.getKznmKeyid());

				kznTlPillarlink.setKzplModifiedon(currentDate);

				if (kznTlPillarlink.getKzplTpmpillarid() == null)
					kznTlPillarlink.setKzplTpmpillarid("{}");

				if (kznTlPillarlink.getKzplKzncategoryid() == null)
					kznTlPillarlink.setKzplKzncategoryid("{}");

				kznTlPillarlink.setKzplCreatedby(newKznTlMst.getKznmCreatedby());

				if (kznTlPillarlink.getSelectionFlag().equals("DELETE"))
					kznTlPillarlink.setDbMode("DELETE");

				if (kznTlPillarlink.getSelectionFlag().equals("UPDATE"))
					kznTlPillarlink.setDbMode("UPDATE");

				newOplTlPillarlinkList.add(kznTlPillarlink);
			}
		}
		return newOplTlPillarlinkList;
	}

	private List<KznTlLosslink> fillLossLinkValues(KznTlMst newKznTlMst, KznTlMst oldKznTlMst) {
		CommonMessage.debugMsg("Inside loss fillval");
		String currentDate = CommonFunctions.getDate();
		List<KznTlLosslink> newKznTlLosslink = newKznTlMst.getLossLink();
		List<KznTlLosslink> oldKznTlLosslinks = null;
		KznTlLosslink oldKznTlLosslink = null;

		if (oldKznTlMst != null) {
			oldKznTlLosslinks = oldKznTlMst.getLossLink();

			if (oldKznTlLosslinks != null && oldKznTlLosslinks.size() > 0)
				oldKznTlLosslink = oldKznTlLosslinks.get(0);
		}
		List<KznTlLosslink> LosslinkList = new ArrayList<KznTlLosslink>();
		List<KznTlLosslink> newKznTlLosslinkList = new ArrayList<KznTlLosslink>();
		if (newKznTlLosslink != null) {
			for (KznTlLosslink kznTlLosslink : newKznTlLosslink) {
				CommonMessage.debugMsg("LOss id=" + kznTlLosslink.getKzllLossid());
				String lossKeyid = kznTlLosslink.getKzllLossid();

				String[] splitLossKeyid = lossKeyid.split(",\\s*");
				StringBuffer splitLossId = new StringBuffer(lossKeyid);
				// splitLossId.append(lossKeyid.split( ",\\s*" ));

				for (int i = 0; i < splitLossKeyid.length; i++) {
					KznTlLosslink newkznTlLosslink = new KznTlLosslink();

					CommonMessage.debugMsg("splitLossKeyid[" + i + "]=" + splitLossKeyid[i]);

					newkznTlLosslink.setKzllActive("Y");
					newkznTlLosslink.setKzllKaizenid(kznTlLosslink.getKzllKaizenid());
					newkznTlLosslink.setKzllTpmpillarid(kznTlLosslink.getKzllTpmpillarid());
					newkznTlLosslink.setKzllCreatedby(newKznTlMst.getKznmCreatedby());
					newkznTlLosslink.setKzllCreatedon(currentDate);
					newkznTlLosslink.setKzllLossid(splitLossKeyid[i]);
					newkznTlLosslink.setKzllModifiedon(currentDate);
					CommonMessage.debugMsg("Loss KeyID=" + newkznTlLosslink.getKzllLossid());

					newkznTlLosslink.setSelectLossFlag(kznTlLosslink.getSelectLossFlag());
					// newKznTlLosslink.add(newkznTlLosslink);
					newKznTlLosslinkList.add(newkznTlLosslink);
					// LosslinkList.add(newkznTlLosslink);

				}
				kznTlLosslink.setKzllCreatedon(currentDate);

				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()=" + newKznTlMst.getKznmKeyid());

				CommonMessage.debugMsg("kznTlLosslink.getKzllCreatedon" + kznTlLosslink.getKzllCreatedon());

				// newKznTlLosslinkList.add(kznTlLosslink);
			}
		}
		return newKznTlLosslinkList;
	}

	private List<KznTlGraphdata> fillKznGraphDataValues(KznTlMst newKznTlMst, KznTlMst oldKznTlMst) {
		CommonMessage.debugMsg("Inside graph Data fillval");

		String currentDate = CommonFunctions.getDate();
		List<KznTlGraphdata> newKznTlGraphdata = newKznTlMst.getGraphData();

		List<KznTlGraphdata> newKznTlGraphdataList = new ArrayList<KznTlGraphdata>();
		if (newKznTlGraphdata != null) {
			for (KznTlGraphdata kznTlGraphdata : newKznTlGraphdata) {
				// if(kznTlGraphdata.getDbMode().equals("INSERT"))

				kznTlGraphdata.setKzgdCreatedon(currentDate);

				CommonMessage.debugMsg("kznTlGraphdata Beofrrr " + kznTlGraphdata.getKzgdBeforedata());
				CommonMessage.debugMsg("newKznTlMst.getKznmKeyid()=" + newKznTlMst.getKznmKeyid());

				// if(kznTlGraphdata.getSelectionFlag().equals("DELETE"))
				// kznTlGraphdata.setDbMode("DELETE");

				// if(kznTlGraphdata.getSelectionFlag().equals("UPDATE"))
				// kznTlGraphdata.setDbMode("UPDATE");
				newKznTlGraphdataList.add(kznTlGraphdata);
			}
		}
		return newKznTlGraphdataList;
	}

	/**
	 * For KZN_TL_MST TABLE
	 * 
	 * @param apprvallevel
	 * @throws Exception
	 **/
	private KznTlMst fillValues(KznTlMst newKznTlMst, KznTlMst oldKznTlMst, KaizenFormBean kaizenFormBean,
			String apprvallevel) throws Exception {

		CommonMessage.debugMsg(" Inside Fill values :: 1 " + newKznTlMst.getKznmThemecategoryid());

		CommonMessage.debugMsg(" Inside Fill values :: 2 " + newKznTlMst.getKznmApprovLevel());

		// CommonMessage.debugMsg(" Inside Fill values :: 3
		// "+oldKznTlMst.getKznmApprovLevel());

		newKznTlMst.setKznmActive("Y");
		newKznTlMst.setKznmKaizenUpload("N");
		CommonMessage.debugMsg("lllllll");
		String dateTime = CommonFunctions.pg_dateTimeNow();
		String currentDate = CommonFunctions.pg_getDate();
		
		CommonMessage.debugMsg(newKznTlMst.getKznmDate() +" Before getKznmStartdategetKznmStartdategetKznmStartdate "+ newKznTlMst.getKznmStartdate());

		String kznmDate = CommonFunctions.pg_getDateTimeFromDate(newKznTlMst.getKznmDate());
		String startdate = CommonFunctions.pg_getDateTimeFromDate(newKznTlMst.getKznmStartdate());
		String endDate = CommonFunctions.pg_getDateTimeFromDate(newKznTlMst.getKznmEnddate());
		
		  newKznTlMst.setKznmDate(kznmDate); 
		  newKznTlMst.setKznmStartdate(startdate);
		  newKznTlMst.setKznmEnddate(endDate);
		 
		CommonMessage.debugMsg(newKznTlMst.getKznmDate() +" getKznmStartdategetKznmStartdategetKznmStartdate "+ newKznTlMst.getKznmStartdate());

		// newKznTlMst.setKznmCreatedon(dateTime);
		// newKznTlMst.setKznmModifiedon(dateTime);

		if (newKznTlMst.getKznmKeyid() == null) {
			newKznTlMst.setKznmCreatedon(dateTime);
			newKznTlMst.setKznmModifiedon(dateTime);
		} else {
			newKznTlMst.setKznmCreatedon(dateTime);
			newKznTlMst.setKznmModifiedon(dateTime);
		}
		// else
		// newKznTlMst.setKznmCreatedon(oldKznTlMst.getKznmCreatedon());

		CommonMessage.debugMsg(dateTime);
		// if(newKznTlMst.getKznmModifiedon() == null )
		// newKznTlMst.setKznmModifiedon(dateTime);

		
		/*
		 * newKznTlMst.setKznmDate(normalizeUiDate(newKznTlMst.getKznmDate()));
		 * if(newKznTlMst.getKznmPrepareddate().length()==10) {
		 * newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate().substring(0,10)));
		 * 
		 * } else { newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate().substring(0,11)));
		 * 
		 * }
		 * 
		 * if(newKznTlMst.getKznmEnddate().length()==10) {
		 * newKznTlMst.setKznmEnddate(normalizeUiDate(newKznTlMst.getKznmEnddate().
		 * substring(0,10)));
		 * 
		 * } else {
		 * newKznTlMst.setKznmEnddate(normalizeUiDate(newKznTlMst.getKznmEnddate().
		 * substring(0,11)));
		 * 
		 * }
		 * 
		 * if(newKznTlMst.getKznmStartdate().length()==10) {
		 * newKznTlMst.setKznmStartdate(normalizeUiDate(newKznTlMst.getKznmStartdate().
		 * substring(0,10)));
		 * 
		 * } else {
		 * newKznTlMst.setKznmStartdate(normalizeUiDate(newKznTlMst.getKznmStartdate().
		 * substring(0,11)));
		 * 
		 * }
		 * 
		 * newKznTlMst.setKznmStartdate(newKznTlMst.getKznmStartdate().substring(0,11));
		 * newKznTlMst.setKznmEnddate(newKznTlMst.getKznmEnddate().substring(0,11));
		 * 
		 * // } newKznTlMst.setKznmApproveddate(normalizeUiDate(newKznTlMst.
		 * getKznmApproveddate()));
		 * newKznTlMst.setKznmCompleteddate(normalizeUiDate(newKznTlMst.
		 * getKznmCompleteddate()));
		 * //newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate()));
		 * 
		 */ 
		
		
		
		if (newKznTlMst.getKznmTpmpillarid() == null)
			newKznTlMst.setKznmTpmpillarid("{}");
		if (newKznTlMst.getKznmMachineid() == null)
			newKznTlMst.setKznmMachineid("{}");

		if (newKznTlMst.getKznmKpiid() == null)
			newKznTlMst.setKznmKpiid("{}");

		if (newKznTlMst.getKznmActivitypillarid() == null)
			newKznTlMst.setKznmActivitypillarid("{}");

		if (newKznTlMst.getKznmActive() == null)
			newKznTlMst.setKznmActive("Y");

		if (newKznTlMst.getKznmLossid() == null)
			newKznTlMst.setKznmLossid("{}");
		if (UIUtils.isValidKeyId(newKznTlMst.getKznmFlid())) {
			String elementId = kznTlMstDao.getElementID(newKznTlMst.getKznmFlid());
			newKznTlMst.setKznmElementid(elementId);
		} else
			newKznTlMst.setKznmElementid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmKzbnkeyid()))
			newKznTlMst.setKznmKzbnkeyid("{}");
		CommonMessage.debugMsg("kznmkzbn keyid    " + newKznTlMst.getKznmKzbnkeyid());
		String resultArea = "";
		String resultAreaSec = new String();

		CommonMessage.debugMsg(" Inside Fill values :: 2 ");

		if (kaizenFormBean.getResultAreaP() != null)
			resultArea = kaizenFormBean.getResultAreaP();
		if (kaizenFormBean.getResultAreaQ() != null)
			resultArea += kaizenFormBean.getResultAreaQ();
		if (kaizenFormBean.getResultAreaC() != null)
			resultArea += kaizenFormBean.getResultAreaC();
		if (kaizenFormBean.getResultAreaD() != null)
			resultArea += kaizenFormBean.getResultAreaD();
		if (kaizenFormBean.getResultAreaS() != null)
			resultArea += kaizenFormBean.getResultAreaS();
		if (kaizenFormBean.getResultAreaM() != null)
			resultArea += kaizenFormBean.getResultAreaM();
		if (kaizenFormBean.getResultAreaE() != null)
			resultArea += kaizenFormBean.getResultAreaE();

		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecP()))
			resultAreaSec = kaizenFormBean.getResultAreaSecP();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecQ()))
			resultAreaSec += kaizenFormBean.getResultAreaSecQ();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecC()))
			resultAreaSec += kaizenFormBean.getResultAreaSecC();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecD()))
			resultAreaSec += kaizenFormBean.getResultAreaSecD();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecS()))
			resultAreaSec += kaizenFormBean.getResultAreaSecS();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecM()))
			resultAreaSec += kaizenFormBean.getResultAreaSecM();
		if (UIUtils.isValidKeyId(kaizenFormBean.getResultAreaSecE()))
			resultAreaSec += kaizenFormBean.getResultAreaSecE();

		newKznTlMst.setKznmRelatedto("MCH");
		CommonMessage.debugMsg("resultArea before setting =" + resultArea);
		CommonMessage.debugMsg("resultAreaSec before setting =" + resultAreaSec);
		if (UIUtils.isValidKeyId(resultAreaSec)) {
			newKznTlMst.setKznmResultareasec(resultAreaSec);
		} else
			newKznTlMst.setKznmResultareasec("{}");

		newKznTlMst.setKznmResultarea(resultArea);

		if (newKznTlMst.getKznmIswhywhy() != null && newKznTlMst.getKznmIswhywhy().trim().equals("Y")) {
			newKznTlMst.setKznmIswhywhy("Y");
			CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()10" + newKznTlMst.getKznmAnalysis());
			newKznTlMst.setKznmAnalysis("<**>");
		} else {
			newKznTlMst.setKznmIswhywhy("N");
			CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()11" + newKznTlMst.getKznmAnalysis());
			if (!UIUtils.isValidKeyId(newKznTlMst.getKznmAnalysis()))
				newKznTlMst.setKznmAnalysis("{}");
		}
		CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()" + newKznTlMst.getKznmIswhywhy());
		CommonMessage.debugMsg("kaizenFormBean.getKznmIswhywhy()" + newKznTlMst.getKznmAnalysis());
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmBenchmark()))
			newKznTlMst.setKznmBenchmark("0");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmTarget()))
			newKznTlMst.setKznmTarget("0");

		if (newKznTlMst.getKznmTeammembers() == null)
			newKznTlMst.setKznmTeammembers("<**>");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmUtiliseforfuture()))
			newKznTlMst.setKznmUtiliseforfuture("N");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmUtiliseforfuture()))
			newKznTlMst.setKznmUtiliseforfuture("N");
		else if (newKznTlMst.getKznmUtiliseforfuture().equalsIgnoreCase("ON"))
			newKznTlMst.setKznmUtiliseforfuture("Y");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCellid()))
			newKznTlMst.setKznmCellid("{}");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmSectionid()))
			newKznTlMst.setKznmSectionid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCircleid()))
			newKznTlMst.setKznmCircleid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCostcentreid()))
			newKznTlMst.setKznmCostcentreid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmAssemblyid()))
			newKznTlMst.setKznmAssemblyid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmFactoryid()))
			newKznTlMst.setKznmFactoryid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmMachineid()))
			newKznTlMst.setKznmMachineid("{}");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmBenefittype()))
			newKznTlMst.setKznmBenefittype("{}");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmBenefitvalue()))
			newKznTlMst.setKznmBenefitvalue("{}");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCostperequipment()))
			newKznTlMst.setKznmCostperequipment("0");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCostperhour()))
			newKznTlMst.setKznmCostperhour("0");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmVerifyamount()))
			newKznTlMst.setKznmVerifyamount("0");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmCsmValue()))
			newKznTlMst.setKznmCsmValue("N");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmIndustry()))
			newKznTlMst.setKznmIndustry("X");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmIndustryReq()))
			newKznTlMst.setKznmIndustryReq("N");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmIcoe()))
			newKznTlMst.setKznmIcoe("N");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmPcoe()))
			newKznTlMst.setKznmPcoe("N");

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmFip()))
			newKznTlMst.setKznmFip("N");

		// if(! UIUtils.isValidKeyId(newKznTlMst.getkznmAnalysis()) )

		CommonMessage.debugMsg(" newKznTlMst Fill values :: Before  " + newKznTlMst.getKznmCsmValue());
		/*
		 * if((!UIUtils.isValidKeyId(newKznTlMst.getKznmApprovLevel()))){
		 * if((!UIUtils.isValidKeyId(oldKznTlMst.getKznmApprovLevel())))
		 * newKznTlMst.setKznmApprovLevel(oldKznTlMst.getKznmApprovLevel()); }else
		 * newKznTlMst.setKznmApprovLevel("-");
		 */ CommonMessage.debugMsg(" Inside Fill values :: 2 ");

		CommonMessage.debugMsg(" newKznTlMst Fill values :: Before " + newKznTlMst.getKznmApprovLevel());
		CommonMessage.debugMsg(" newKznTlMst Fill values :: analysispoint " + newKznTlMst.getKznmAnalysis());
		// CommonMessage.debugMsg(" oldKznTlMst Fill values :: Before
		// "+oldKznTlMst.getKznmApprovLevel());

		/*
		 * if(newKznTlMst.getKznmApprovLevel()==null ){
		 * newKznTlMst.setKznmApprovLevel("-"); }else {
		 * newKznTlMst.setKznmApprovLevel(newKznTlMst.getKznmApprovLevel()); }
		 * 
		 */

		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmFipRequired()))
			newKznTlMst.setKznmFipRequired("N");
		if (!UIUtils.isValidKeyId(newKznTlMst.getKznmFipNumber()))
			newKznTlMst.setKznmFipNumber("{}");

		CommonMessage.debugMsg("Utilise For future   " + newKznTlMst.getKznmUtiliseforfuture());
		String ideaIndGrp = new String();

		if (UIUtils.isValidKeyId(kaizenFormBean.getIdeagroupindividualG()))
			ideaIndGrp = kaizenFormBean.getIdeagroupindividualG();
		if (UIUtils.isValidKeyId(kaizenFormBean.getIdeagroupindividualI()))
			ideaIndGrp = kaizenFormBean.getIdeagroupindividualI();
		CommonMessage.debugMsg("ideaIndGrp   " + ideaIndGrp);
		newKznTlMst.setKznmIdeagroupindividual(ideaIndGrp);
		CommonMessage.debugMsg("getKznmIdeagroupindividual()   " + newKznTlMst.getKznmIdeagroupindividual());

		if (newKznTlMst.getKznmTheme() == null)
			newKznTlMst.setKznmTheme("<**>");
		if (newKznTlMst.getKznmThemecategoryid() == null)
			newKznTlMst.setKznmThemecategoryid("{}");

		/*
		 * if( newKznTlMst.getKznmBenchmark() == null )
		 * newKznTlMst.setKznmBenchmark("<**>");
		 */
		if (newKznTlMst.getKznmPresentproblem() == null)
			newKznTlMst.setKznmPresentproblem("<**>");

		if (newKznTlMst.getKznmIdea() == null)
			newKznTlMst.setKznmIdea("<**>");

		if (newKznTlMst.getKznmPresentimage() == null)
			newKznTlMst.setKznmPresentimage("{}");

		if (newKznTlMst.getKznmWwmsKeyid() == null)
			newKznTlMst.setKznmWwmsKeyid("{}");

		if (newKznTlMst.getKznmRootcause() == null)
			newKznTlMst.setKznmRootcause("<**>");

		if (newKznTlMst.getKznmCountermeasure() == null)
			newKznTlMst.setKznmCountermeasure("<**>");

		if (newKznTlMst.getKznmAfterimage() == null)
			newKznTlMst.setKznmAfterimage("<**>");

		if (newKznTlMst.getKznmResultdescription() == null)
			newKznTlMst.setKznmResultdescription("<**>");

		if (newKznTlMst.getKznmResultimage() == null)
			newKznTlMst.setKznmResultimage("{}");

		if (newKznTlMst.getKznmBenefits() == null)
			newKznTlMst.setKznmBenefits("<**>");

		if (newKznTlMst.getKznmBenefitsimage() == null)
			newKznTlMst.setKznmBenefitsimage("<**>");

		String temp = "X";

		CommonMessage.debugMsg("kaizenFormBean.getProviding()" + kaizenFormBean.getProviding());
		CommonMessage.debugMsg("kaizenFormBean.getChanging()" + kaizenFormBean.getChanging());

		if (kaizenFormBean.getProviding() != null && kaizenFormBean.getProviding().trim().equalsIgnoreCase("P")) {
			CommonMessage.debugMsg("ISPROVIDNG " + kaizenFormBean.getProviding());
			newKznTlMst.setKznmIsprovidingchanging("P");
			CommonMessage.debugMsg("ISPROVIDNG " + kaizenFormBean.getProviding());
		} else if (kaizenFormBean.getChanging() != null && kaizenFormBean.getChanging().trim().equalsIgnoreCase("C")) {
			CommonMessage.debugMsg("CHANGE " + kaizenFormBean.getProviding());
			newKznTlMst.setKznmIsprovidingchanging("C");
			CommonMessage.debugMsg("CHANGE " + newKznTlMst.getKznmIsprovidingchanging());
		} else {
			newKznTlMst.setKznmIsprovidingchanging(temp);
			CommonMessage.debugMsg("ELSE" + newKznTlMst.getKznmIsprovidingchanging());
		}

		CommonMessage.debugMsg("REVERSE" + kaizenFormBean.getReversible());
		CommonMessage.debugMsg("IRRREVERSE" + kaizenFormBean.getIrreversible());

		if (kaizenFormBean.getReversible() != null && kaizenFormBean.getReversible().trim().equalsIgnoreCase("R")) {
			CommonMessage.debugMsg("If" + kaizenFormBean.getReversible());
			newKznTlMst.setKznmReversibleirreversible("R");
		} else if (kaizenFormBean.getIrreversible() != null
				&& kaizenFormBean.getIrreversible().trim().equalsIgnoreCase("I")) {
			CommonMessage.debugMsg("Irreverese" + kaizenFormBean.getIrreversible());
			newKznTlMst.setKznmReversibleirreversible("I");
			CommonMessage.debugMsg("Irreverese" + newKznTlMst.getKznmReversibleirreversible());
		} else {
			CommonMessage.debugMsg("Else in reverse");
			newKznTlMst.setKznmReversibleirreversible(temp);
		}

		CommonMessage.debugMsg("REVERSE LAST =" + newKznTlMst.getKznmReversibleirreversible());

		if (newKznTlMst.getKznmKaizenlink() == null)
			newKznTlMst.setKznmKaizenlink("N");

		if (newKznTlMst.getKznmKaizenlinktype() == null)
			newKznTlMst.setKznmKaizenlinktype("-");

		CommonMessage.debugMsg(" newKznTlMst.getKznmAssemblyid()" + newKznTlMst.getKznmAssemblyid() + "-");
		if (newKznTlMst.getKznmAssemblyid() == null)
			newKznTlMst.setKznmAssemblyid("{}");

		if (newKznTlMst.getKznmPhenomenaid() == null)
			newKznTlMst.setKznmPhenomenaid("{}");

		if (newKznTlMst.getKznmCauseid() == null)
			newKznTlMst.setKznmCauseid("{}");

		if (newKznTlMst.getKznmMaterialcost() == null)
			newKznTlMst.setKznmMaterialcost("<**>");

		if (newKznTlMst.getKznmLabourcost() == null)
			newKznTlMst.setKznmLabourcost("<**>");

		if (newKznTlMst.getKznmHowtosustain() == null)
			newKznTlMst.setKznmHowtosustain("<**>");

		if (newKznTlMst.getKznmAdditionaldetails() == null)
			newKznTlMst.setKznmAdditionaldetails("<**>");

		if (newKznTlMst.getKznmAdditionalimage() == null)
			newKznTlMst.setKznmAdditionalimage("<**>");

		if (newKznTlMst.getKznmAdditionalimage() == null)
			newKznTlMst.setKznmAdditionalimage("<**>");

		CommonMessage.debugMsg("kaizenFormBean.getHdRequiredY()" + kaizenFormBean.getHdRequiredY());
		CommonMessage.debugMsg("kaizenFormBean.getHdRequiredN()" + kaizenFormBean.getHdRequiredN());
		if (kaizenFormBean.getHdRequiredY() != null && kaizenFormBean.getHdRequiredY().trim().equals("Y"))
			newKznTlMst.setKznmIshdpossible("Y");
		else
			newKznTlMst.setKznmIshdpossible("N");

		// CommonMessage.debugMsg("newKznTlMst.getKznmIshdpossible()"
		// +newKznTlMst.getKznmIshdpossible());
		CommonMessage.debugMsg("WOO " + kaizenFormBean.getWoRequiredY());
		if (kaizenFormBean.getWoRequiredY() != null && kaizenFormBean.getWoRequiredY().trim().equals("Y")) {
			newKznTlMst.setKznmIsworequired("Y");
			CommonMessage.debugMsg("WOO is Y" + newKznTlMst.getKznmIsworequired());
		} else if (kaizenFormBean.getWoRequiredN() != null && kaizenFormBean.getWoRequiredN().trim().equals("N")) {
			newKznTlMst.setKznmIsworequired("N");
			CommonMessage.debugMsg("WOO is N" + newKznTlMst.getKznmIsworequired());
		} else

		{
			CommonMessage.debugMsg("WOO Else");
			newKznTlMst.setKznmIsworequired("X");
			CommonMessage.debugMsg("WOO Else" + newKznTlMst.getKznmIsworequired());
		}

		CommonMessage.debugMsg("WOO " + newKznTlMst.getKznmIsworequired());
		if (newKznTlMst.getKznmNoofhds() == null)
			newKznTlMst.setKznmNoofhds("0");

		if (newKznTlMst.getKznmPreparedid() == null)
			newKznTlMst.setKznmPreparedid("{}");

		if (newKznTlMst.getKznmPrepareddate() == null)
			newKznTlMst.setKznmPrepareddate(Constants.pgPassNullDateTime);

		if (newKznTlMst.getKznmApprovedid() == null)
			newKznTlMst.setKznmApprovedid("{}");

		if (newKznTlMst.getKznmApproveddate() == null)
			newKznTlMst.setKznmApproveddate(Constants.pgPassNullDateTime);

		if (newKznTlMst.getKznmRefdoctype() == null)
			newKznTlMst.setKznmRefdoctype("<**>");

		if (newKznTlMst.getKznmRefdocno() == null)
			newKznTlMst.setKznmRefdocno("<**>");

		CommonMessage.debugMsg("kaizenFormBean.getFormMode()" + kaizenFormBean.getFormMode());
		// if( kaizenFormBean.getFormMode().equals("CREATE") )
		// newKznTlMst.setKznmStatus("A");
		// else
		// newKznTlMst.setKznmStatus("C");

		if (newKznTlMst.getKznmWoid() == null)
			newKznTlMst.setKznmWoid("{}");

		if (newKznTlMst.getKznmWofeedbackid() == null)
			newKznTlMst.setKznmWofeedbackid("{}");

		if (newKznTlMst.getKznmCompleteddate() == null)
			newKznTlMst.setKznmCompleteddate(Constants.pgPassNullDateTime);

		if (newKznTlMst.getKznmCompletedid() == null)
			newKznTlMst.setKznmCompletedid("{}");

		if (newKznTlMst.getKznmRemarks() == null)
			newKznTlMst.setKznmRemarks("<**>");

		if (newKznTlMst.getKznmOperations() == null)
			newKznTlMst.setKznmOperations("<**>");

		if (newKznTlMst.getKznmWhattosustain() == null)
			newKznTlMst.setKznmWhattosustain("<**>");

		if (newKznTlMst.getKznmSustainfreq() == null)
			newKznTlMst.setKznmSustainfreq("<**>");

		if (newKznTlMst.getKznmTotalcost() == null)
			newKznTlMst.setKznmTotalcost("<**>");

		if (newKznTlMst.getKznmCircleid() == null)
			newKznTlMst.setKznmCircleid("{}");

		if (newKznTlMst.getKznmDepartmentid() == null)
			newKznTlMst.setKznmDepartmentid("{}");

		if (newKznTlMst.getKznmCostcentreid() == null)
			newKznTlMst.setKznmCostcentreid("{}");

		newKznTlMst.setKznmIstpmkzn("R");

		if (newKznTlMst.getKznmMaterialno() == null)
			newKznTlMst.setKznmMaterialno("<**>");

		if (newKznTlMst.getKznmIsworthformp() == null)
			newKznTlMst.setKznmIsworthformp("N");
		else if (newKznTlMst.getKznmIsworthformp().equalsIgnoreCase("ON"))
			newKznTlMst.setKznmIsworthformp("Y");

		if (newKznTlMst.getKznmMouldid() == null)
			newKznTlMst.setKznmMouldid("{}");

		newKznTlMst.setKznmCreationflag("N");

		CommonMessage.debugMsg("In fill Service impl=" + newKznTlMst);
		newKznTlMst.setPillarLink(fillPillarLinkValues(newKznTlMst, oldKznTlMst));// Pillar Link

		newKznTlMst.setLossLink(fillLossLinkValues(newKznTlMst, oldKznTlMst));// Loss Link

		CommonMessage.debugMsg("In Main Fill Values=" + newKznTlMst.getLossLink().size());
		CommonMessage.debugMsg("In Main Fill Values=" + newKznTlMst.getLossLink());

		CommonMessage.debugMsg("In Main Fill Values= getKznmDate() " + newKznTlMst.getKznmDate());

		// LocalDateTime dateTime = LocalDateTime.parse(newKznTlMst.getKznmDate(),
		// inputFmt);

		/*
		 * newKznTlMst.setKznmDate(normalizeUiDate(newKznTlMst.getKznmDate()));
		 * if(newKznTlMst.getKznmPrepareddate().length()==10) {
		 * newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate().substring(0,10)));
		 * 
		 * } else { newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate().substring(0,11)));
		 * 
		 * }
		 * 
		 * if(newKznTlMst.getKznmEnddate().length()==10) {
		 * newKznTlMst.setKznmEnddate(normalizeUiDate(newKznTlMst.getKznmEnddate().
		 * substring(0,10)));
		 * 
		 * } else {
		 * newKznTlMst.setKznmEnddate(normalizeUiDate(newKznTlMst.getKznmEnddate().
		 * substring(0,11)));
		 * 
		 * }
		 * 
		 * if(newKznTlMst.getKznmStartdate().length()==10) {
		 * newKznTlMst.setKznmStartdate(normalizeUiDate(newKznTlMst.getKznmStartdate().
		 * substring(0,10)));
		 * 
		 * } else {
		 * newKznTlMst.setKznmStartdate(normalizeUiDate(newKznTlMst.getKznmStartdate().
		 * substring(0,11)));
		 * 
		 * }
		 * 
		 * newKznTlMst.setKznmStartdate(newKznTlMst.getKznmStartdate().substring(0,11));
		 * newKznTlMst.setKznmEnddate(newKznTlMst.getKznmEnddate().substring(0,11));
		 * 
		 * // } newKznTlMst.setKznmApproveddate(normalizeUiDate(newKznTlMst.
		 * getKznmApproveddate()));
		 * newKznTlMst.setKznmCompleteddate(normalizeUiDate(newKznTlMst.
		 * getKznmCompleteddate()));
		 * //newKznTlMst.setKznmPrepareddate(normalizeUiDate(newKznTlMst.
		 * getKznmPrepareddate()));
		 * 
		 */

		CommonMessage.debugMsg(" Date inside the Fill Values .... " + newKznTlMst.getKznmDate());

		newKznTlMst.setGraphData(fillKznGraphDataValues(newKznTlMst, oldKznTlMst));// Graph Data

		return newKznTlMst;
	}

	public static String normalizeUiDate(String uiDate) {

		DateTimeFormatter uiFmt = DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.ENGLISH);

		DateTimeFormatter apiFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

		LocalDate date = LocalDate.parse(uiDate, uiFmt);

		return date.atStartOfDay().format(apiFmt);
	}

	private BdmTlYycountermeasurelink fillYYLinkValues(KznTlMst newKznTlMst, KaizenFormBean kaizenFormBean) {
		// TODO Auto-generated method stub

		BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();

		bdmTlYycountermeasurelink.setYycmActive("Y");
		bdmTlYycountermeasurelink.setYycmTempfield1("-");
		bdmTlYycountermeasurelink.setYycmTempfield2("-");
		bdmTlYycountermeasurelink.setYycmTempfield3("-");
		bdmTlYycountermeasurelink.setYycmTempfield4("-");
		bdmTlYycountermeasurelink.setYycmTempfield5("-");
		bdmTlYycountermeasurelink.setYycmWoid("{}");
		bdmTlYycountermeasurelink.setYycmRefdoctype("KZN");

		if (UIUtils.isValidKeyId(kaizenFormBean.getYyId()))
			bdmTlYycountermeasurelink.setYycmYyid(kaizenFormBean.getYyId());

		if (UIUtils.isValidKeyId(newKznTlMst.getKznmKeyid()))
			bdmTlYycountermeasurelink.setYycmCountermsrid(newKznTlMst.getKznmKeyid());

		bdmTlYycountermeasurelink.setYycmCreatedby(newKznTlMst.getKznmCreatedby());
		bdmTlYycountermeasurelink.setYycmCreatedon(newKznTlMst.getKznmCreatedon());

		bdmTlYycountermeasurelink.setYycmModifieyon(newKznTlMst.getKznmModifiedon());

		if (UIUtils.isValidKeyId(kaizenFormBean.getYyId()))
			return bdmTlYycountermeasurelink;
		else
			return null;

	}

	private GenTlDocupdates fillDocUpdates(KznTlMst newKznTlMst, KaizenFormBean kaizenFormBean) {
		// TODO Auto-generated method stub

		GenTlDocupdates genTlDocupdates = new GenTlDocupdates();
		genTlDocupdates.setDcupDetailid(newKznTlMst.getKznmKeyid());

		genTlDocupdates.setDcupUpdatedoctype("KZN");
		if (UIUtils.isValidKeyId(kaizenFormBean.getDocId()))
			genTlDocupdates.setDcupKeyid(kaizenFormBean.getDocId());

		if (UIUtils.isValidKeyId(kaizenFormBean.getDocId()))
			return genTlDocupdates;
		else
			return null;
	}

	private QtmTlCustcomplaintdtl fillCustomerDetails(KznTlMst newKznTlMst, KaizenFormBean kaizenFormBean) {
		// TODO Auto-generated method stub

		QtmTlCustcomplaintdtl qtmTlCustcomplaintdtl = new QtmTlCustcomplaintdtl();
		qtmTlCustcomplaintdtl.setCucdKaizenno(newKznTlMst.getKznmKeyid());

		qtmTlCustcomplaintdtl.setCucdIskaizendone("Y");
		if (UIUtils.isValidKeyId(kaizenFormBean.getCucdkeyId()))
			qtmTlCustcomplaintdtl.setCucdKeyid(kaizenFormBean.getCucdkeyId());

		if (UIUtils.isValidKeyId(kaizenFormBean.getCucdkeyId()))
			return qtmTlCustcomplaintdtl;
		else
			return null;
	}

//Added For Kaizen Approval
	@Override
	public List<String[]> getAllKaizenApproval(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getAllKaizenApproval(commonFilter);
	}

	@Override
	public List<String[]> getAllPiller() throws Exception {
		return kznTlMstDao.getAllPiller();
	}

	@Override
	public List<String[]> getAllBenifitReport(String type) throws Exception {
		return kznTlMstDao.getAllBenifitReport(type);
	}

	public List<String[]> getAllAuthorization(String type) throws Exception {

		return kznTlMstDao.getAllAuthorization(type);
	}

	@Override
	public List<String[]> getBTSGridData(CommonFilter commonFilter) throws Exception {
		return kznTlMstDao.getBTSGridData(commonFilter);
	}

	@Override
	public String selectKznb(String kznbKeyid) throws NoDataFoundException, SQLException, Exception {
		// return kznTlMstDao.selectKznb(kznbKeyid);

		return kznServiceApi.findKeyid(kznbKeyid);
	}

	/*
	 * @Override public KznTlMst selectKznb(String kznbKeyid) throws
	 * NoDataFoundException, SQLException, Exception { //return
	 * kznTlMstDao.selectKznb(kznbKeyid);
	 * 
	 * return kznServiceApi.getById(kznbKeyid) ;
	 * 
	 * 
	 * }
	 */
	@Override
	public String getkaizenTheme(String kznbKeyid) throws Exception {
		return kznTlMstDao.getkaizenTheme(kznbKeyid);
	}

	@Override
	public List<String[]> getKaizenEmployeeWiseMonthWise(CommonFilter commonFilter) throws Exception {
		return kznTlMstDao.getKaizenEmployeeWiseMonthWise(commonFilter);
	}

	@Override
	public Workbook getMonthEmployeeWiseKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format)
			throws Exception {

		return kznTlMstDao.getMonthEmployeeWiseKaizenExcel(commonFilter, tableModel, format);

	}

	@Override
	public List<String[]> getKaizenEmployeeWiseMonthWiseTotal(CommonFilter commonFilter) throws Exception {
		return kznTlMstDao.getKaizenEmployeeWiseMonthWiseTotal(commonFilter);
	}

	@Override
	public List<String[]> getKznDateUpdateData(CommonFilter commonFilter) throws Exception {
		return this.kznTlMstDao.getKznDateUpdateData(commonFilter);
	}

	public void updateKaizenDate(String kaizenId, String kznRespid) throws Exception {
		kznTlMstDao.updateKaizenDate(kaizenId, kznRespid);

	}

	@Override
	public Workbook getKaizenDateUpdateExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		return kznTlMstDao.getKaizenDateUpdateExcel(colmodel, format, commonFilter);
	}

	public List<String[]> getAllHorizontalDeploy(CommonFilter commonFilter) throws Exception {

		return kznTlMstDao.getKaizenHoriaonDeploy(commonFilter);
	}

	@Override
	public KznTlMst updateKznRejRewStatus(KznTlMst kznTlMst) throws ValidationExceptions, Exception {
		return kznTlMstDao.updateKznRejRewStatus(kznTlMst);
	}

	@Override
	public List<String[]> getEmployeeWiseMonthWiseKzn(CommonFilter commonFilter) throws Exception {
		return kznTlMstDao.getEmployeeWiseMonthWiseKzn(commonFilter);
	}

	@Override
	public Workbook getEmployeeWiseMonthKaizenExcel(CommonFilter commonFilter, JSONObject tableModel, String format)
			throws Exception {
		return kznTlMstDao.getEmployeeWiseMonthKaizenExcel(commonFilter, tableModel, format);
	}

	@Override
	public Workbook getMonthEmployeeWiseTotalKaizenExcel(CommonFilter commonFilter, JSONObject tableModel,
			String format) throws Exception {

		return kznTlMstDao.getMonthEmployeeWiseTotalKaizenExcel(commonFilter, tableModel, format);

	}

	@Override
	public List<ComboBox> getKznThemeCategory(ComboFilter comboFilter, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		comboFilter.setNameField("KZCT_NAME");
		comboFilter.setIdField("KZCT_KEYID");
		comboFilter.setTableName(" KZN_TL_CATEGORYTHMMST ");
		comboFilter.setCondSql("  AND KZCT_ACTIVE ='Y' ");
		// comboFilter.setCondSql(" AND KIDL_INDICATORID= KINK_KEYID " );
		// comboFilter.setCondSql(" AND KIDL_DEPTID ='"+commonFilter.getFlid()+"' " );
		return commonFilterDao.fillComboValues(comboFilter);
	}

	@Override
	public List<String[]> FillCategoryData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.FillCategoryData(keyid);
	}

	public List<String[]> getKaizenThemeGridData(CommonFilter commonFilter) throws Exception {

		return this.kznTlMstDao.getKaizenThemeGridData(commonFilter);

	}

	public List<KznTlMst> updateTheme(List<KznTlMst> KaizenThemeList) throws Exception {

		//return this.kznTlMstDao.updateTheme(KaizenThemeList);
		return kznServiceApi.updateTheme(KaizenThemeList);
	}

	@Override
	public Workbook getKaizenThemeUpdateExcel(JSONObject colmodel, String format, CommonFilter commonFilter)
			throws Exception {
		return kznTlMstDao.getKaizenThemeUpdateExcel(colmodel, format, commonFilter);
	}

	@Override
	public String getkaizenBenefit(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkaizenBenefit(kznbKeyid);
	}

	@Override
	public String getkaizenPcdqsme(String kznbKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getkaizenPcdqsme(kznbKeyid);
	}

	@Override
	public String getThemename(String benefit) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getThemename(benefit);
	}

	@Override
	public String updateCategory(String keyid, String kzbnkeyid, String kznmBenefit) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.updateCategory(keyid, kzbnkeyid, kznmBenefit);
	}

	public List<String[]> getKaizenDataGrid(CommonFilter commonFilter) throws Exception {
		return this.kznTlMstDao.getKaizenDataGrid(commonFilter);
	}

	public List<KznTlMst> updateKaizenData(List<KznTlMst> KaizenDataList) throws Exception {
		return this.kznTlMstDao.updateKaizenData(KaizenDataList);
	}

	public List<String[]> getAllSimplifiedKaizenApproval(CommonFilter commonFilter) throws Exception {
		return this.kznTlMstDao.getAllSimplifiedKaizenApproval(commonFilter);
	}

	public List<GenTlWorkflowInfo> createMultipleApproval(List<GenTlWorkflowInfo> genTlWorkflowInfo) throws Exception {
		fillGenTlWorkFlowInfoMst(genTlWorkflowInfo);
		//return this.kznTlMstDao.createMultipleApproval(genTlWorkflowInfo);
		return  kznServiceApi.createMultipleApproval(genTlWorkflowInfo);
	}

	private List<GenTlWorkflowInfo> fillGenTlWorkFlowInfoMst(List<GenTlWorkflowInfo> genTlworkflowinfo) {
		List<GenTlWorkflowInfo> newGentlworkflowinfo = genTlworkflowinfo;
		List<GenTlWorkflowInfo> newEntTlEmployeeLinkList = new ArrayList<GenTlWorkflowInfo>();
		int index = 0;
		if (genTlworkflowinfo != null)
			for (GenTlWorkflowInfo gentlworkflowLink : genTlworkflowinfo) {
				String dateTime = CommonFunctions.pg_dateTimeNow();
				GenTlWorkflowInfo gentlworkflowLink1 = newGentlworkflowinfo.get(index);
				index++;
				gentlworkflowLink.setWrinCreatedon(dateTime);
				gentlworkflowLink.setWrinModifiedon(dateTime);

				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinDate())) {
					gentlworkflowLink.setWrinDate(dateTime);
				}else {
					gentlworkflowLink.setWrinDate(CommonFunctions.pg_getDateTimeFromDate(gentlworkflowLink.getWrinDate()));
				}
				
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinRemarks())) {
					gentlworkflowLink.setWrinRemarks("-");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinWrkdKeyid())) {
					gentlworkflowLink.setWrinWrkdKeyid("{}");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinWrmlKeyid())) {
					gentlworkflowLink.setWrinWrmlKeyid("{}");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinTempfield2())) {
					gentlworkflowLink.setWrinTempfield2("-");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinTempfield3())) {
					gentlworkflowLink.setWrinTempfield3("-");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinTempfield4())) {
					gentlworkflowLink.setWrinTempfield4("-");
				}
				if (!UIUtils.isValidKeyId(gentlworkflowLink.getWrinTempfield5())) {
					gentlworkflowLink.setWrinTempfield5("-");
				}
				newEntTlEmployeeLinkList.add(gentlworkflowLink);
			}
		return newEntTlEmployeeLinkList;
	}

	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empid)
			throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		return this.kznTlMstDao.getElementId(loginflid, loginlevel, loginElementid, empid);
	}

	@Override
	public List<String[]> getIndividualKaizenReport(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getIndividualKaizenReport(commonFilter);
	}

	@Override
	public Workbook kaizenIndividualRptExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String reportType)
			throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.kaizenIndividualRptExportExcel(commonFilter, tblJSONObj, reportType);
	}

	public String getFileName(String kznKeyid) throws Exception {
		// TODO Auto-generated method stub
		return kznTlMstDao.getFileName(kznKeyid);
	}
}
