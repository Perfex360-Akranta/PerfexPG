package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlWhywhymstDao;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.BdmTlWhywhydtlSql;
import com.akranta.tpm.dao.sql.BdmTlWhywhymstSql;
import com.akranta.tpm.dao.sql.BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.BdmTlYydonebymstSql;
import com.akranta.tpm.dao.sql.BdmTlYyeffectivedtlSql;
import com.akranta.tpm.dao.sql.BdmTlYyeffectivemstSql;
import com.akranta.tpm.dao.sql.BdmTlYyproblemattbymstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlDocupdatesSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlWhywhydtl;
import com.akranta.tpm.model.BdmTlWhywhymst;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.BdmTlYydonebymst;
import com.akranta.tpm.model.BdmTlYyeffectivedtl;
import com.akranta.tpm.model.BdmTlYyeffectivemst;
import com.akranta.tpm.model.BdmTlYyproblemattbymst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.MomServiceApi;
//import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BdmTlWhywhymstDaoImpl implements BdmTlWhywhymstDao {

	private DBActionTemplate dbActionTemplate;
	private BdmTlYyeffectivemstSql bdmTlYyeffectivemstSql;
	private BdmTlYyeffectivedtlSql bdmTlYyeffectivedtlSql;
	private BdmTlWhywhymstSql bdmTlWhywhymstSql;
	private BdmTlWhywhydtlSql bdmTlWhywhydtlSql;
	private GenTlDocupdatesSql genTlDocupdatesSql;
	private BdmTlYydonebymstSql bdmTlYydonebymstSql;
	private BdmTlYyproblemattbymstSql bdmTlYyproblemattbymstSql;
	private WhywhyServiceApi whywhyServiceApi;
	FunctionCallApi fnCallApi;
	

	// private BdmTlMstSql bdmTlMstSql ;

	public BdmTlWhywhymstDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		bdmTlWhywhymstSql = new BdmTlWhywhymstSql(); // contains dbtable,field names, Field types and related sqls of
														// master table
		bdmTlWhywhydtlSql = new BdmTlWhywhydtlSql();
		bdmTlYyeffectivemstSql = new BdmTlYyeffectivemstSql();
		bdmTlYyeffectivedtlSql = new BdmTlYyeffectivedtlSql();
		genTlDocupdatesSql = new GenTlDocupdatesSql();
		bdmTlYydonebymstSql = new BdmTlYydonebymstSql();
		bdmTlYyproblemattbymstSql = new BdmTlYyproblemattbymstSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BdmTlWhywhymstDaoImplJwt(String JwtToken) 
	{
		try{
			whywhyServiceApi = new WhywhyServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public BdmTlWhywhymst create(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		// BdmTlWhywhymst newBdmTlWhywhymst=new BdmTlWhywhymst();
		// BdmTlWhywhymstSql bdmTlWhywhymstSql=new BdmTlWhywhymstSql();

		// try{

		/*
		 * for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++) {
		 * 
		 * if(UIUtils.isValidKeyId(newBdmTlWhywhymst.getWwmsKeyid())) {
		 * sqls.add(BdmTlWhywhymstSql.getUpdateSql(bdmTlWhywhymstSql.getWwmsDbFields(),
		 * newBdmTlWhywhymst.getSaveArray())); } else{
		 * CommonMessage.debugMsg("Inside DAO else....");
		 * newBdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(
		 * BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST));
		 * sqls.add(BdmTlWhywhymstSql.getInsertSql(bdmTlWhywhymstSql.getWwmsDbFields(),
		 * newBdmTlWhywhymst.getSaveArray())); } }
		 */

		String elementId = bdmTlWhywhymst.getElementid();
		String location = null;
		String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,
				BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST);

		bdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 12, "YY", "YYMMDD", " "));
		// bdmTlWhywhymst.setWwmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhymstSql.TBL_BDM_TL_WHYWHYMST,12,"YYM","YYMM","
		// ")); // set the sequnce number
		sqls.add(BdmTlWhywhymstSql.getInsertSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray())); // add
																														// insert
																														// sql
																														// for
																														// master
																														// table

		/*
		 * BdmTlWhywhydtl bdmTlWhywhydtl =
		 * (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(i);
		 * bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
		 * bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(
		 * BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL,12,"YYD","YY","Y"));
		 * sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
		 * bdmTlWhywhydtl.getSaveArray()));
		 */
		insertDetail(bdmTlWhywhymst, sqls);

		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsProblemattendby())) {
			insertProblemAtt(bdmTlWhywhymst, sqls);
		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsWhywhydoneby())) {
			insertDoneby(bdmTlWhywhymst, sqls);
		}

		if (bdmTlWhywhymst.getWwmsRefdoctype().equals("WM")) {
			String womsKey = bdmTlWhywhymst.getWwmsRefdocno();
			String YYKeyid = bdmTlWhywhymst.getWwmsKeyid();
			sqls.add(BdmTlMstSql.getUpdateWomstSql(YYKeyid, womsKey));
		}

		dbActionTemplate.executeStatements(sqls);

		String clsfcnId = null;
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause())) {
			// CommonMessage.debugMsg("clsfcnId "+clsfcnId);
			clsfcnId = dbActionTemplate.getSingleValue(
					BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(), getPillar(bdmTlWhywhymst)));
			CommonMessage.debugMsg("clsfcnId " + clsfcnId);
		}
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsRefdoctype())) {
			CommonMessage.debugMsg("bdmTlWhywhymst.getWwmsRefdoctype() " + bdmTlWhywhymst.getWwmsRefdoctype());
			if (bdmTlWhywhymst.getWwmsRefdoctype().equals("BDM"))
				sqls.add(BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						clsfcnId, bdmTlWhywhymst.getWwmsPreventivemeasure(), bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("UPM"))
				sqls.add(BdmTlMstSql.updateYYUPMSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						clsfcnId, bdmTlWhywhymst.getWwmsPreventivemeasure(), bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("KZN"))
				sqls.add(BdmTlMstSql.updateYYKaizenSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("CMC"))
				sqls.add(BdmTlMstSql.updateYYccSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("IMT"))
				sqls.add(BdmTlMstSql.updateYYIMTSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno()));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("DOC"))
				sqls.add(BdmTlMstSql.updateYYDockSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("ACTION"))
				sqls.add(BdmTlMstSql.updateYYActionSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("SFT")) {
				String rootcauseDueTo = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_ROOTCAUSEMST, "WRCM_CODE",
						"WRCM_KEYID", bdmTlWhywhymst.getWwmsRootcauseid());
				sqls.add(BdmTlMstSql.updateYYSafetySql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst, rootcauseDueTo));
				List<String[]> planStatus = dbActionTemplate
						.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(bdmTlWhywhymst.getWwmsRefdocno()));
				String status = "C";
				if (planStatus.size() > 0) {
					for (int i = 0; i < planStatus.size(); i++) {
						if (planStatus.get(i)[0].equals("P")) {
							status = "P";
							break;
						}
					}

				}
				// sqls.add(BdmTlMstSql.updateIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
				// sqls.add(BdmTlMstSql.updateActionPlanIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
			}

		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) {
			if (bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
				insertCounterMeasureLink(bdmTlWhywhymst, sqls);
		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) {
			if (bdmTlWhywhymst.getWwmsIssop().equals("Y"))
				insertCounterMeasureLink(bdmTlWhywhymst, sqls);
		}
		insertDocUpdates(bdmTlWhywhymst, sqls);
		// dbActionTemplate.executeStatements(sqls);

		// populateSqlsForwhywhyDtl(sqls,bdmTlWhywhymst.getBdmTlWhywhydtl(),bdmTlWhywhymst.getWwmsKeyid());

		// execute the block of sqls

		// }

		return bdmTlWhywhymst;
	}

	private static String getPillar(BdmTlWhywhymst bdmTlWhywhymst) {
		String pillar = "";
		if (bdmTlWhywhymst.getWwmsIsjh().equals("Y"))
			pillar = "JH";
		else if (bdmTlWhywhymst.getWwmsIspm().equals("Y"))
			pillar = "PM";
		else if (bdmTlWhywhymst.getWwmsIskk().equals("Y"))
			pillar = "CI";
		else if (bdmTlWhywhymst.getWwmsIsopl().equals("Y"))
			pillar = "ET";
		else if (bdmTlWhywhymst.getWwmsIspy().equals("Y"))
			pillar = "PY";
		else
			pillar = "{}";
		CommonMessage.debugMsg("Pillar : " + pillar);
		return pillar;

	}

	private String getClsId(BdmTlWhywhymst bdmTlWhywhymst) throws Exception {
		return BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(), getPillar(bdmTlWhywhymst));
	}

	private List<String> insertDetail(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception { // detail
																											// insert

		CommonMessage.debugMsg("Size B: " + bdmTlWhywhymst.getBdmTlWhywhydtl().size());
		CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
		if (bdmTlWhywhymst.getBdmTlWhywhydtl() != null && bdmTlWhywhymst.getBdmTlWhywhydtl().size() > 0) // check for
																											// detail
																											// table
																											// data
		{
			CommonMessage.debugMsg("Size : " + bdmTlWhywhymst.getBdmTlWhywhydtl().size());
			CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
			for (int i = 0; i < bdmTlWhywhymst.getBdmTlWhywhydtl().size(); i++) {

				BdmTlWhywhydtl bdmTlWhywhydtl = (BdmTlWhywhydtl) bdmTlWhywhymst.getBdmTlWhywhydtl().get(i); // get
																											// detail
																											// info from
																											// list in
																											// empployee
																											// object
				bdmTlWhywhydtl.setWwdtSlno(Integer.toString(i + 1)); 
				
				bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());//get 
				bdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL,
						12, "YYD", "YY", "Y"));
				sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
						bdmTlWhywhydtl.getSaveArray()));// add insert sql for detail table
			}
		}
		// dbActionTemplate.executeStatements(sqls);
		return sqls;
	} 
	
	

	private List<String> insertDocUpdates(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception {

		GenTlDocupdates genTlDocupdates = new GenTlDocupdates();
		fillDocUpdates(genTlDocupdates, bdmTlWhywhymst);
		genTlDocupdates.setDcupKeyid(
				dbActionTemplate.getSequenceNumber(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES, 12, "DPD", "YY", "Y")); // set
																														// the
																														// sequnce
																														// number
		if (!genTlDocupdates.getDcupUpdatedoctype().equals("PM"))
			sqls.add(GenTlDocupdatesSql.getInsertSql(genTlDocupdatesSql.getDcupDbFields(),
					genTlDocupdates.getSaveArray())); // add insert sql for master table
		return sqls;

	}

	private List<String> insertCounterMeasureLink(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception {
		BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
		BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql = new BdmTlYycountermeasurelinkSql();
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
			bdmTlYycountermeasurelink.setYycmYyid(bdmTlWhywhymst.getWwmsKeyid());

		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) {

			if (bdmTlWhywhymst.getWwmsIsojt().equals("Y")) {
				if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsOjtdesc())) {
					bdmTlYycountermeasurelink.setYycmRefdoctype("OJT");
					bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsOjtdesc());
				}

			}
		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) {

			if (bdmTlWhywhymst.getWwmsIssop().equals("Y")) {
				if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsSopdesc())) {
					bdmTlYycountermeasurelink.setYycmRefdoctype("SOP");
					bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsSopdesc());
				}

			}
		}
		bdmTlYycountermeasurelink.setYycmWoid("{}");
		bdmTlYycountermeasurelink.setYycmTempfield1("-");
		bdmTlYycountermeasurelink.setYycmTempfield2("-");
		bdmTlYycountermeasurelink.setYycmTempfield3("-");
		bdmTlYycountermeasurelink.setYycmTempfield4("-");
		bdmTlYycountermeasurelink.setYycmTempfield5("-");
		bdmTlYycountermeasurelink.setYycmActive("Y");
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
			bdmTlYycountermeasurelink.setYycmCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
			bdmTlYycountermeasurelink.setYycmCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
			bdmTlYycountermeasurelink.setYycmModifieyon(bdmTlWhywhymst.getWwmsModifiedon());
		String getcmLinkId = dbActionTemplate.getSingleValue(BdmTlYycountermeasurelinkSql
				.getCMSql(bdmTlYycountermeasurelink.getYycmRefdoctype(), bdmTlWhywhymst.getWwmsKeyid()));
		if (CommonFunctions.isValidKeyId(getcmLinkId)) {
			bdmTlYycountermeasurelink.setYycmKeyid(getcmLinkId);
			sqls.add(BdmTlYycountermeasurelinkSql.getUpdateSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),
					bdmTlYycountermeasurelink.getSaveArray()));
		} else {
			bdmTlYycountermeasurelink.setYycmKeyid(
					dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK)); // set
																														// the
																														// sequnce
																														// number
			sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),
					bdmTlYycountermeasurelink.getSaveArray()));

		}

		return sqls;
	}

	private List<String> updateDocUpdates(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception {
		new GenTlDocupdates();
		String updateSql = GenTlDocupdatesSql.getDocUpdateSql(bdmTlWhywhymst.getWwmsModifiedon(),
				bdmTlWhywhymst.getWwmsRefdocno());
		CommonMessage.debugMsg(updateSql);
		// if(!genTlDocupdates.getDcupUpdatedoctype().equals("PM"))
		sqls.add(updateSql); // add insert sql for master table
		return sqls;

	}

	private void fillDocUpdates(GenTlDocupdates genTlDocupdates, BdmTlWhywhymst bdmTlWhywhymst) {
		genTlDocupdates.setDcupCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
		genTlDocupdates.setDcupCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
		genTlDocupdates.setDcupModifiedon(bdmTlWhywhymst.getWwmsModifiedon());
		genTlDocupdates.setDcupFeedbackid("{}");
		genTlDocupdates.setDcupUpdatedoctype(getPillar(bdmTlWhywhymst));
		genTlDocupdates.setDcupDetailid("{}");
		genTlDocupdates.setDcupRefdoctype(bdmTlWhywhymst.getWwmsRefdoctype());
		genTlDocupdates.setDcupRefdocid(bdmTlWhywhymst.getWwmsRefdocno());
	}

	public BdmTlWhywhymst update(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>();
		// BdmTlWhywhydtl newBdmTlWhywhydtl = new BdmTlWhywhydtl();
		// BdmTlWhywhydtlSql bdmTlWhywhydtlSql=new BdmTlWhywhydtlSql ();
		// try {
		/*
		 * for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++) {
		 * 
		 * 
		 * if(UIUtils.isValidKeyId(newBdmTlWhywhydtl.getWwdtKeyid())) {
		 * sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
		 * newBdmTlWhywhydtl.getSaveArray())); } else{
		 * CommonMessage.debugMsg("Inside DAO else....");
		 * newBdmTlWhywhydtl.setWwdtKeyid(dbActionTemplate.getSequenceNumber(
		 * SopTlVisualchecklistmstSql.TBL_SOP_TL_VISUALCHECKLISTMST));
		 * sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
		 * newBdmTlWhywhydtl.getSaveArray())); } }
		 */
		CommonMessage.debugMsg("update dao impl");
		sqls.add(BdmTlWhywhymstSql.getUpdateSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray()));

		// sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
		// newBdmTlWhywhydtl.getSaveArray()));

		CommonMessage.debugMsg("bdmTlWhywhymst.getBdmTlWhywhydtl().size()" + bdmTlWhywhymst.getBdmTlWhywhydtl().size());
		/*
		 * for(int i =0;i<bdmTlWhywhymst.getBdmTlWhywhydtl().size();i++) {
		 * BdmTlWhywhydtl bdmTlWhywhydtl =
		 * (BdmTlWhywhydtl)bdmTlWhywhymst.getBdmTlWhywhydtl().get(i);
		 * bdmTlWhywhydtl.setWwdtWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
		 * bdmTlWhywhydtl.setWwdtSlno(Integer.toString(i+1));
		 * //sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields()
		 * , bdmTlWhywhydtl.getSaveArray()));
		 * populateSqlsForwhywhyDtl(sqls,bdmTlWhywhymst.getBdmTlWhywhydtl(),
		 * bdmTlWhywhymst.getWwmsKeyid()); }
		 */
		CommonMessage.debugMsg("DETAILS IN MASTER"+bdmTlWhywhymst);
		populateSqlsForwhywhyDtl(sqls, bdmTlWhywhymst.getBdmTlWhywhydtl(), bdmTlWhywhymst.getWwmsKeyid()); // detail
		CommonMessage.debugMsg("DETAILS IN MASTER 2"+bdmTlWhywhymst);																									// insert
																											// update

		CommonMessage.debugMsg("RootCause :  " + bdmTlWhywhymst.getWwmsRootcause());
		String clsfcnId = "";
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRootcause())) {
			CommonMessage.debugMsg("clsfcnId " + clsfcnId);
			clsfcnId = dbActionTemplate.getSingleValue(
					BdmTlWhywhymstSql.getClassification(bdmTlWhywhymst.getWwmsRootcause(), getPillar(bdmTlWhywhymst)));
			CommonMessage.debugMsg("clsfcnId " + clsfcnId);
		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsRefdoctype())) {
			CommonMessage.debugMsg("bdmTlWhywhymst.getWwmsRefdoctype() " + bdmTlWhywhymst.getWwmsRefdoctype());
			if (bdmTlWhywhymst.getWwmsRefdoctype().equals("BDM"))
				sqls.add(BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						clsfcnId, bdmTlWhywhymst.getWwmsPreventivemeasure(), bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("UPM"))
				sqls.add(BdmTlMstSql.updateYYUPMSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						clsfcnId, bdmTlWhywhymst.getWwmsPreventivemeasure(), bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("KZN"))
				sqls.add(BdmTlMstSql.updateYYKaizenSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("CMC"))
				sqls.add(BdmTlMstSql.updateYYccSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("IMT"))
				sqls.add(BdmTlMstSql.updateYYIMTSql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno()));
			else if (bdmTlWhywhymst.getWwmsRefdoctype().equals("SFT")) {
				String rootcauseDueTo = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_ROOTCAUSEMST, "WRCM_CODE",
						"WRCM_KEYID", bdmTlWhywhymst.getWwmsRootcauseid());
				sqls.add(BdmTlMstSql.updateYYSafetySql(bdmTlWhywhymst.getWwmsKeyid(), bdmTlWhywhymst.getWwmsRefdocno(),
						bdmTlWhywhymst, rootcauseDueTo));
				List<String[]> planStatus = dbActionTemplate
						.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(bdmTlWhywhymst.getWwmsRefdocno()));
				String status = "C";
				if (planStatus.size() > 0) {
					for (int i = 0; i < planStatus.size(); i++) {
						CommonMessage.debugMsg(i + " : " + planStatus.get(i)[0]);
						if (planStatus.get(i)[0].equals("P")) {
							CommonMessage.debugMsg("Inside : " + planStatus.get(i)[0]);
							status = "P";
							break;
						}
					}

				}
				// sqls.add(BdmTlMstSql.updateIncidentSql(bdmTlWhywhymst.getWwmsRefdocno(),status));
			}
		}

		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt())) {
			if (bdmTlWhywhymst.getWwmsIsojt().equals("Y"))
				insertCounterMeasureLink(bdmTlWhywhymst, sqls);
		}
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIssop())) {
			if (bdmTlWhywhymst.getWwmsIssop().equals("Y"))
				insertCounterMeasureLink(bdmTlWhywhymst, sqls);
		}
		/*
		 * if(UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsIsojt()) &&
		 * bdmTlWhywhymst.getWwmsIsojt().equals("Y")) { BdmTlYycountermeasurelink
		 * bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
		 * BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql = new
		 * BdmTlYycountermeasurelinkSql();
		 * if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
		 * bdmTlYycountermeasurelink.setYycmYyid(bdmTlWhywhymst.getWwmsKeyid());
		 * bdmTlYycountermeasurelink.setYycmRefdoctype("OJT");
		 * if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsOjtdesc()))
		 * bdmTlYycountermeasurelink.setYycmCountermsrid(bdmTlWhywhymst.getWwmsOjtdesc()
		 * ); bdmTlYycountermeasurelink.setYycmWoid("{}");
		 * bdmTlYycountermeasurelink.setYycmTempfield1("-");
		 * bdmTlYycountermeasurelink.setYycmTempfield2("-");
		 * bdmTlYycountermeasurelink.setYycmTempfield3("-");
		 * bdmTlYycountermeasurelink.setYycmTempfield4("-");
		 * bdmTlYycountermeasurelink.setYycmTempfield5("-");
		 * bdmTlYycountermeasurelink.setYycmActive("Y");
		 * if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
		 * bdmTlYycountermeasurelink.setYycmCreatedby(bdmTlWhywhymst.getWwmsCreatedby())
		 * ; if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
		 * bdmTlYycountermeasurelink.setYycmCreatedon(bdmTlWhywhymst.getWwmsCreatedon())
		 * ; if(CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
		 * bdmTlYycountermeasurelink.setYycmModifieyon(bdmTlWhywhymst.getWwmsModifiedon(
		 * ));
		 * 
		 * String getcmLinkId =
		 * dbActionTemplate.getSingleValue(BdmTlYycountermeasurelinkSql.getCMSql(
		 * bdmTlYycountermeasurelink.getYycmRefdoctype(),bdmTlWhywhymst.getWwmsKeyid()))
		 * ; if(CommonFunctions.isValidKeyId(getcmLinkId)) {
		 * bdmTlYycountermeasurelink.setYycmKeyid(getcmLinkId);
		 * sqls.add(BdmTlYycountermeasurelinkSql.getUpdateSql(
		 * bdmTlYycountermeasurelinkSql.getYycmDbFields(),
		 * bdmTlYycountermeasurelink.getSaveArray())); } else {
		 * bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(
		 * BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK)); // set the
		 * sequnce number sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(
		 * bdmTlYycountermeasurelinkSql.getYycmDbFields(),
		 * bdmTlYycountermeasurelink.getSaveArray())); } }
		 */
		// sqls.add(BdmTlMstSql.updateYYSql(bdmTlWhywhymst.getWwmsKeyid(),
		// bdmTlWhywhymst.getWwmsRefdocno(),clsfcnId,bdmTlWhywhymst.getWwmsPreventivemeasure(),bdmTlWhywhymst));
		String getDocId = dbActionTemplate.getSingleValue(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES, "DCUP_KEYID",
				"DCUP_REFDOCID", bdmTlWhywhymst.getWwmsRefdocno());
		if (CommonFunctions.isValidKeyId(getDocId))
			updateDocUpdates(bdmTlWhywhymst, sqls);
		else
			insertDocUpdates(bdmTlWhywhymst, sqls);
		// dbActionTemplate.executeStatements(sqls);

		dbActionTemplate.executeStatements(sqls);
		// }

		/*
		 * catch (Exception e) { // TODO Auto-generated catch block e.printStackTrace();
		 * throw new Exception(e.getMessage()); }
		 */

		return bdmTlWhywhymst;
	}

	public String getDocId(String bdId) throws Exception {
		String getDocId = dbActionTemplate.getSingleValue(GenTlDocupdatesSql.TBL_GEN_TL_DOCUPDATES, "DCUP_KEYID",
				"DCUP_REFDOCID", bdId);
		return getDocId;
	}

	public BdmTlWhywhymst delete(BdmTlWhywhymst bdmTlWhywhymst) throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>();
		CommonMessage.debugMsg("count11...");
		String countSql = BdmTlWhywhymstSql.countermeasureCount(bdmTlWhywhymst);
		CommonMessage.debugMsg("count..." + countSql);
		String count = dbActionTemplate.getSingleValue(countSql);

		int cnt = Integer.parseInt(count);
		CommonMessage.debugMsg("cnt00" + cnt);

		if (cnt == 0) {
			sqls.add(BdmTlWhywhydtlSql.getDeleteAllKZNDtl(bdmTlWhywhydtlSql.getWwdtDbFields(),
					bdmTlWhywhymst.getWwmsKeyid()));
			sqls.add(
					BdmTlWhywhymstSql.getDeleteSql(bdmTlWhywhymstSql.getWwmsDbFields(), bdmTlWhywhymst.getSaveArray()));
			sqls.add(BdmTlWhywhymstSql.getUpdateKznSql(bdmTlWhywhymst));
			dbActionTemplate.executeStatements(sqls);
			CommonMessage.debugMsg("cnt no:" + cnt);
		} else
			throw new BusinessApplicationExceptions("COUNTERMEASUREEXIST");

		return bdmTlWhywhymst;
	}

	public BdmTlWhywhydtl deleteYYDtl(String keyId) throws ValidationExceptions, Exception {
		List<String> sqls = new ArrayList<String>();
		String delsql = BdmTlWhywhydtlSql.delYYDtlSql(keyId);
		try {
			sqls.add(delsql);
			dbActionTemplate.executeStatements(sqls);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return null;

	}

	public BdmTlWhywhymst getWWMS(String wwwsKeyid) throws Exception {

		BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();
		String sql = BdmTlWhywhymstSql.getSelectSql(wwwsKeyid);
		List<Object> bdmTlWhywhymstList = (List<Object>) dbActionTemplate.getDataList(sql, bdmTlWhywhymst);
		return (BdmTlWhywhymst) bdmTlWhywhymstList.get(0);
	}

	public String checkCounterMsr() throws Exception {
		CommonMessage.debugMsg("YysDaoimp");
		String checkCM = dbActionTemplate.getSingleValue(BdmTlWhywhymstSql.checkCounterMsrSqlExist());
		if (UIUtils.isValidKeyId(checkCM)) {
			String checkCMVal = dbActionTemplate.getSingleValue(BdmTlWhywhymstSql.checkCounterMsrSql());
			if (UIUtils.isValidKeyId(checkCM))
				return checkCMVal;
		}
		return "NO";

	}

	public BdmTlWhywhymst getWWMSValues(String refDocId) throws Exception {

		BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();
		String sql = BdmTlWhywhymstSql.getSelectYYSql(refDocId);
		List<Object> bdmTlWhywhymstList = (List<Object>) dbActionTemplate.getDataList(sql, bdmTlWhywhymst);
		return (BdmTlWhywhymst) bdmTlWhywhymstList.get(0);
	}

	public List<Object> getWWDT(String wwwsKeyid) throws Exception {

		BdmTlWhywhydtl bdmTlWhywhydtl = new BdmTlWhywhydtl();
		String sql = BdmTlWhywhydtlSql.getSelectWWDT(wwwsKeyid);
		List<Object> bdmTlWhywhydtlList = (List<Object>) dbActionTemplate.getDataList(sql, bdmTlWhywhydtl);
		return bdmTlWhywhydtlList;
	}

	public List<String[]> getRootCause(String openMode) throws Exception {

		String sql = BdmTlWhywhymstSql.getRootCauseSql(openMode);
		CommonMessage.debugMsg(sql);
		return dbActionTemplate.getDataList(sql);
	}

	public List<String[]> getPillar(String yyId, String pillarFlag) throws Exception {

		String sql = BdmTlWhywhymstSql.getPillarSql(yyId, pillarFlag);
		CommonMessage.debugMsg("YY : " + yyId);
		return dbActionTemplate.getDataList(sql);

	}

	public List<String[]> getProgramList(String start, String end) throws Exception {
		String sql = null;
		sql = BdmTlWhywhymstSql.getProgramSql(start, end);
		CommonMessage.debugMsg("All Chlid Sql " + sql);
		return dbActionTemplate.getDataList(sql);

	}

	public List<String[]> getSelectedRootCause(String yyNo) throws Exception {
		String sql = BdmTlWhywhymstSql.getSelectedRootCauseSql(yyNo);
		CommonMessage.debugMsg("gsrc : " + sql);
		return dbActionTemplate.getDataList(sql);
	}

	private void populateSqlsForwhywhyDtl(List<String> sqls, List<BdmTlWhywhydtl> bdmTlWhywhydtlList, String masterId)
			throws Exception {

		if (bdmTlWhywhydtlList != null) {
			for (BdmTlWhywhydtl bdmTlWhywhydtl : bdmTlWhywhydtlList) {
				bdmTlWhywhydtl.setWwdtWwmsKeyid(masterId);
				CommonMessage.debugMsg("detailKeyid  :" + bdmTlWhywhydtl.getWwdtKeyid());
				if (!UIUtils.isValidKeyId(bdmTlWhywhydtl.getWwdtKeyid())) {
					bdmTlWhywhydtl
							.setWwdtKeyid(dbActionTemplate.getSequenceNumber(BdmTlWhywhydtlSql.TBL_BDM_TL_WHYWHYDTL));
					sqls.add(BdmTlWhywhydtlSql.getInsertSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
							bdmTlWhywhydtl.getSaveArray()));
				} else {
					sqls.add(BdmTlWhywhydtlSql.getUpdateSql(bdmTlWhywhydtlSql.getWwdtDbFields(),
							bdmTlWhywhydtl.getSaveArray()));
				}
			}
		}
	}

	public List<String[]> getWhywhyStd(CommonFilter commonFilter) throws Exception {
		try {

			List<String> paramValues = new ArrayList<String>();

			paramValues.add(commonFilter.getKey() != null ? commonFilter.getKey() : "{}");

			paramValues.add(commonFilter.getFinalTrade() != null ? commonFilter.getFinalTrade().getId() : "{}");

			paramValues.add(commonFilter.getFactory() != null ? commonFilter.getFactory().getId() : "{}");

			paramValues.add(commonFilter.getCell() != null ? commonFilter.getCell().getId() : "{}");

			paramValues.add(commonFilter.getCostCenter() != null ? commonFilter.getCostCenter().getId() : "{}");

			paramValues.add(commonFilter.getJhStep() != null ? commonFilter.getJhStep().getId() : "{}");

			paramValues.add(commonFilter.getMachineRank() != null ? commonFilter.getMachineRank().getId() : "{}");

			paramValues.add(commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : "{}");

			paramValues.add(commonFilter.getCircle() != null ? commonFilter.getCircle().getId() : "{}");

			paramValues.add(commonFilter.getAssembly() != null ? commonFilter.getAssembly().getId() : "{}");

			paramValues.add(commonFilter.getSection() != null ? commonFilter.getSection().getId() : "{}");

			paramValues.add(commonFilter.getPhenomena() != null ? commonFilter.getPhenomena().getId() : "{}");

			paramValues.add(commonFilter.getFinalCause() != null ? commonFilter.getFinalCause().getId() : "{}");

			paramValues.add(
					commonFilter.getMaintainChargeId() != null ? commonFilter.getMaintainChargeId().getId() : "{}");

			paramValues.add(commonFilter.getEqpGroup() != null ? commonFilter.getEqpGroup().getId() : "{}");

			paramValues.add(commonFilter.getFromDate());

			paramValues.add(commonFilter.getToDate());

			return dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_WHYWHYREPORT", paramValues);

		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	public String checkMstExist(String refDocNo) throws Exception {
		String count = dbActionTemplate.getSingleValue(BdmTlWhywhymstSql.checkMstExistSql(refDocNo));
		return count;
	}

	public BdmTlWhywhymst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();
		String sql = BdmTlWhywhymstSql.selectWhyWhy();
		CommonMessage.debugMsg(sql);
		Object args[] = new Object[] { keyid };
		bdmTlWhywhymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return bdmTlWhywhymst;
	}

	@Override
	public BdmTlWhywhymst create1(BdmTlWhywhymst newBdmTlWhywhymst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BdmTlWhywhymst selectmaskeyid(String maskeyid) throws Exception {
		BdmTlWhywhymst bdmTlWhywhymst = new BdmTlWhywhymst();// model
		String sql = BdmTlWhywhymstSql.selectmaskeyid();
		CommonMessage.debugMsg(maskeyid + " sql " + sql);
		Object args[] = new Object[] { maskeyid };
		bdmTlWhywhymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return bdmTlWhywhymst;

	}

	@Override
	public Workbook whywhyExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {

			rs = getwhywhyReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, format, 3, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	
	@Override
	public Workbook whywhyAgeExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {

			rs = getwhywhyAgeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short) 14);
			condFormat.setFontBoldWeight((short) 20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue((char) 252 + ""); // Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, format, 3, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	/*
	 * private ResultSet getwhywhyReportResultSet(CommonFilter commonFilter) throws
	 * Exception { List<String> paramValues = new ArrayList<String>(); String
	 * condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter); String
	 * commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
	 * "ISTOTALCNT="+commonFilter.getViewClick() //
	 * +";FROMTOROW="+commonFilter.getFromRow() // +" AND " +
	 * commonFilter.getToRow() // +";";
	 * 
	 * if (UIUtils.isValidKeyId(commonFilter.getRefdocid())) condParms +=
	 * "REFDOCID=" + commonFilter.getRefdocid() + ";"; paramValues.add(condParms);
	 * paramValues.add(commonParams); return
	 * dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYMST", paramValues); }
	 */
	
	private ResultSet getwhywhyReportResultSet(CommonFilter commonFilter) throws Exception {
	    List<String> paramValues = new ArrayList<String>();
	    String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);

	    
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	    if (UIUtils.isValidKeyId(commonFilter.getRefdocid()))
	        condParms += "REFDOCID=" + commonFilter.getRefdocid() + ";";

	    String formMode = (commonFilter.getMaintMode() != null)
	                      ? commonFilter.getMaintMode().trim() : "";
	    condParms += "FORMMODE=" + formMode + ";";

	    CommonMessage.debugMsg("Excel condParms: " + condParms);
	    CommonMessage.debugMsg("Excel commonParams: " + commonParams);

	    paramValues.add(condParms);
	    paramValues.add(commonParams);
	    return dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYMST_SB", paramValues);
	}
	
	private ResultSet getwhywhyAgeReportResultSet(CommonFilter commonFilter) throws Exception {
	    List<String> paramValues = new ArrayList<String>();
	    String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);

	    
	    String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	    if (UIUtils.isValidKeyId(commonFilter.getRefdocid()))
	        condParms += "REFDOCID=" + commonFilter.getRefdocid() + ";";

	    String formMode = (commonFilter.getMaintMode() != null)
	                      ? commonFilter.getMaintMode().trim() : "";
	    condParms += "FORMMODE=" + formMode + ";";

	    CommonMessage.debugMsg("Excel condParms: " + condParms);
	    CommonMessage.debugMsg("Excel commonParams: " + commonParams);

	    paramValues.add(condParms);
	    paramValues.add(commonParams);
	    return dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYAGEINGRPT_SB", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();

		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND " + commonFilter.getToRow()
																				// +";";

//		if (commonFilter.getAbnIsHSE().equals("Y"))
//			condParms+="REFDOCTYPE=SFT;";

		paramValues.add(condParms);
		paramValues.add(commonParams);

		return paramValues;
	}

	@Override
	public List<String[]> getAllWhywhy(CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("inside get abn");

		try {

			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";

			if (UIUtils.isValidKeyId(commonFilter.getRefdocid()))
				condParms += "REFDOCID=" + commonFilter.getRefdocid() + ";";
			condParms += "FORMMODE=" + commonFilter.getWoModeForGrid() + ";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues" + paramValues);
			List<String[]> dataList = null;
			//dataList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_FN_WHYWHYMST",paramValues);

			dataList = fnCallApi.callFunction("BDM_FN_WHYWHYMST_SB",paramValues,3,true);
			
			
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt....." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public List<String[]> getWhywhyAge(CommonFilter commonFilter) throws Exception {
		CommonMessage.debugMsg("inside get abn");

		try {

			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";

			if (UIUtils.isValidKeyId(commonFilter.getRefdocid()))
				condParms += "REFDOCID=" + commonFilter.getRefdocid() + ";";
			condParms += "FORMMODE=" + commonFilter.getWoModeForGrid() + ";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues" + paramValues);
			List<String[]> dataList = null;
			//dataList = dbActionTemplate.processFunctionCallsWithColHeaders("BDM_FN_WHYWHYMST",paramValues);

			dataList = fnCallApi.callFunction("BDM_FN_WHYWHYAGEINGRPT_SB",paramValues,3,true);
			
			
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt....." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	

	@Override
	public BdmTlYyeffectivemst yyEffectivenessCreate(BdmTlYyeffectivemst bdmTlYyeffectivemst) {

		List<String> sqls = new ArrayList<String>();
		try {
			CommonMessage.debugMsg("inside insert...");
			// bdmTlYyeffectivemst.setYyefKeyid(dbActionTemplate.getSequenceNumber(BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,12,"YYE","YYMM","
			// ")); // set the sequnce number
			CommonMessage.debugMsg(bdmTlYyeffectivemst.getYyefKeyid());
			CommonMessage.debugMsg(bdmTlYyeffectivemst.getYyefEffectivedate());
			// sqls.add(BdmTlYyeffectivemstSql.getInsertSql(bdmTlYyeffectivemstSql.getYyefDbFields(),
			// bdmTlYyeffectivemst.getSaveArray())); // add insert sql for master table
			insertEffectMaster(bdmTlYyeffectivemst, sqls);
			// insertEffectDetail(bdmTlYyeffectivemst,sqls);
			CommonMessage.debugMsg("sqls...." + sqls.toString());

			dbActionTemplate.executeStatements(sqls);
			// return bdmTlYyeffectivemst;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bdmTlYyeffectivemst;

	}

	private List<String> insertEffectMaster(BdmTlYyeffectivemst bdmTlYyeffectivemst, List<String> sqls)
			throws Exception {

		String masterKeyid = null;
		if (bdmTlYyeffectivemst.getBdmTlYyeffectivemst() != null
				&& bdmTlYyeffectivemst.getBdmTlYyeffectivemst().size() > 0) // check for detail table data
		{
			for (int i = 0; i < bdmTlYyeffectivemst.getBdmTlYyeffectivemst().size(); i++) {
				BdmTlYyeffectivemst getBdmTlYyeffectivemst = (BdmTlYyeffectivemst) bdmTlYyeffectivemst
						.getBdmTlYyeffectivemst().get(i); // get detail info from list in empployee object
				String getsql = " SELECT YYEF_KEYID FROM BDM_TL_YYEFFECTIVEMST WHERE YYEF_WWMS_KEYID = '"
						+ getBdmTlYyeffectivemst.getYyefWwmsKeyid() + "' ";
				String keyid = dbActionTemplate.getSingleValue(getsql);
				if (UIUtils.isValidKeyId(keyid)) {
					masterKeyid = keyid;
					getBdmTlYyeffectivemst.setYyefKeyid(masterKeyid);
					sqls.add(BdmTlYyeffectivemstSql.getUpdateSql(bdmTlYyeffectivemstSql.getYyefDbFields(),
							getBdmTlYyeffectivemst.getSaveArray()));// add insert sql for detail table
				} else {
					// if(!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())){
					masterKeyid = dbActionTemplate.getSequenceNumber(BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,
							12, "YYE", "YYMM", " ");
					getBdmTlYyeffectivemst.setYyefKeyid(masterKeyid); // set the sequnce number
					sqls.add(BdmTlYyeffectivemstSql.getInsertSql(bdmTlYyeffectivemstSql.getYyefDbFields(),
							getBdmTlYyeffectivemst.getSaveArray()));// add insert sql for detail table
				}

			}
		}

		if (bdmTlYyeffectivemst.getBdmTlYyeffectivedtl() != null
				&& bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size() > 0) // check for detail table data
		{
			CommonMessage.debugMsg("Size : " + bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size());
			CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
			for (int i = 0; i < bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size(); i++) {

				BdmTlYyeffectivedtl getBdmTlYyeffectivedtl = (BdmTlYyeffectivedtl) bdmTlYyeffectivemst
						.getBdmTlYyeffectivedtl().get(i); // get detail info from list in empployee object
				getBdmTlYyeffectivedtl.setYyedYyefKeyid(masterKeyid);
				if (!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())) {
					getBdmTlYyeffectivedtl.setYyedKeyid(dbActionTemplate.getSequenceNumber(
							BdmTlYyeffectivedtlSql.TBL_BDM_TL_YYEFFECTIVEDTL, 12, "YYD", "YYMM", " ")); // set the
																										// sequnce
																										// number
					sqls.add(BdmTlYyeffectivedtlSql.getInsertSql(bdmTlYyeffectivedtlSql.getYyedDbFields(),
							getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
				} else
					sqls.add(BdmTlYyeffectivedtlSql.getUpdateSql(bdmTlYyeffectivedtlSql.getYyedDbFields(),
							getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table

			}
		}

		return sqls;
	}

	private List<String> insertEffectDetail(BdmTlYyeffectivemst bdmTlYyeffectivemst, List<String> sqls)
			throws Exception {

		if (bdmTlYyeffectivemst.getBdmTlYyeffectivedtl() != null
				&& bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size() > 0) // check for detail table data
		{
			CommonMessage.debugMsg("Size : " + bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size());
			CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
			for (int i = 0; i < bdmTlYyeffectivemst.getBdmTlYyeffectivedtl().size(); i++) {
				BdmTlYyeffectivedtl getBdmTlYyeffectivedtl = (BdmTlYyeffectivedtl) bdmTlYyeffectivemst
						.getBdmTlYyeffectivedtl().get(i); // get detail info from list in empployee object
				CommonMessage.debugMsg(" Checking :: 1234 " + getBdmTlYyeffectivedtl.getYyedKeyid());
				getBdmTlYyeffectivedtl.setYyedYyefKeyid(bdmTlYyeffectivemst.getYyefKeyid());
				if (!UIUtils.isValidKeyId(getBdmTlYyeffectivedtl.getYyedKeyid())) {
					getBdmTlYyeffectivedtl.setYyedKeyid(dbActionTemplate.getSequenceNumber(
							BdmTlYyeffectivedtlSql.TBL_BDM_TL_YYEFFECTIVEDTL, 12, "YYD", "YYMM", " ")); // set the
																										// sequnce
																										// number
					sqls.add(BdmTlYyeffectivedtlSql.getInsertSql(bdmTlYyeffectivedtlSql.getYyedDbFields(),
							getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table
				} else
					sqls.add(BdmTlYyeffectivedtlSql.getUpdateSql(bdmTlYyeffectivedtlSql.getYyedDbFields(),
							getBdmTlYyeffectivedtl.getSaveArray()));// add insert sql for detail table

			}
		}
		// dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("dtl sqls..." + sqls);
		return sqls;

	}

	@Override
	public BdmTlYyeffectivemst yyEffectivenessUpdate(BdmTlYyeffectivemst bdmTlYyeffectivemst) {

		List<String> sqls = new ArrayList<String>();
		try {
			// bdmTlYyeffectivemst.setYyefKeyid(dbActionTemplate.getSequenceNumber(BdmTlYyeffectivemstSql.TBL_BDM_TL_YYEFFECTIVEMST,12,"YYM","YYMM","
			// ")); // set the sequnce number
			sqls.add(BdmTlYyeffectivemstSql.getUpdateSql(bdmTlYyeffectivemstSql.getYyefDbFields(),
					bdmTlYyeffectivemst.getSaveArray())); // add insert sql for master table

			insertEffectDetail(bdmTlYyeffectivemst, sqls);

			dbActionTemplate.executeStatements(sqls);
			// return bdmTlYyeffectivemst;
		} catch (Exception e) {

		}
		return bdmTlYyeffectivemst;

	}

	public BdmTlYydonebymst yyDonebyCreate(BdmTlYydonebymst bdmTlYydonebymst) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
		bdmTlYydonebymst.setWwdbKeyid(
				dbActionTemplate.getSequenceNumber(BdmTlYydonebymstSql.TBL_BDM_TL_YYDONEBYMST, 12, "YYB", "YYMM", " ")); // set
																															// the
																															// sequnce
																															// number
		sqls.add(BdmTlYydonebymstSql.getInsertSql(bdmTlYydonebymstSql.getBdm_DbFields(),
				bdmTlYydonebymst.getSaveArray()));// add insert sql for detail table

		dbActionTemplate.executeStatements(sqls);
		// dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("dtl sqls..." + sqls);
		return bdmTlYydonebymst;

	}

	public String deleteYYDoneBy(String keyId) throws Exception {
		String sql = " DELETE FROM BDM_TL_YYDONEBYMST WHERE WWDB_KEYID ='" + keyId + "' ";
		dbActionTemplate.executeStatement(sql);
		return "deleted";
	}

	public BdmTlYyproblemattbymst yyProbattCreate(BdmTlYyproblemattbymst bdmTlYyproblemattbymst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg("yyyyyyyyyyyyaaaaaa");
		bdmTlYyproblemattbymst.setWwpaKeyid(dbActionTemplate
				.getSequenceNumber(BdmTlYyproblemattbymstSql.TBL_BDM_TL_YYPROBLEMATTBYMST, 12, "YYPA", "", "Y")); // set
																													// the
																													// sequnce
																													// number
		CommonMessage.debugMsg("bdmTlYyproblemattbymst.setWwpaKeyid" + bdmTlYyproblemattbymst.getWwpaKeyid());
		sqls.add(BdmTlYyproblemattbymstSql.getInsertSql(bdmTlYyproblemattbymstSql.getWwpaDbFields(),
				bdmTlYyproblemattbymst.getSaveArray()));// add insert sql for detail table

		dbActionTemplate.executeStatements(sqls);
		// dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("dtl sqls..." + sqls);
		return bdmTlYyproblemattbymst;

	}

	public String deleteYYProbAttBy(String keyId) throws Exception {
		// TODO Auto-generated method stub
		String sql = " DELETE FROM Bdm_Tl_yyproblemattbymst WHERE WWPA_KEYID ='" + keyId + "' ";
		CommonMessage.debugMsg("Called");
		CommonMessage.debugMsg("QUERY "+sql);
		dbActionTemplate.executeStatement(sql);
		return "deleted";
	}

	public List<String[]> getWhyWhyGenDrillData(CommonFilter commonFilter) throws Exception {

		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> dataList = null;
		dataList = fnCallApi.callFunction("WHY_FN_WHYWHYGENDRILLDOWN_sb",paramValues,2,true);

		//dataList = dbActionTemplate.processFunctionCalls("WHY_FN_WHYWHYGENDRILLDOWN", paramValues);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);

			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;

	}

	public Workbook getWhyWhyGenDrillDataExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {
			List<String> paramValues = getFilterParamValues(commonFilter);

			rs = dbActionTemplate.NewdbFunctionCall2("WHY_FN_WHYWHYGENDRILLDOWN", paramValues);
			

			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs, format, 1, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	public List<String[]> getWhyWhyCountData(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		List<String[]> dataList = null;

		//dataList = dbActionTemplate.processFunctionCalls("WHY_FN_WHYWHYCOUNT", paramValues);
		
		dataList =fnCallApi.callFunction("WHY_FN_WHYWHYCOUNT_SB", paramValues,3,true);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);

			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	}

	public Workbook getWhyWhyCountExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		ResultSet rs = null;
		try {
			List<String> paramValues = getFilterParamValues(commonFilter);

			rs = dbActionTemplate.NewdbFunctionCall2("WHY_FN_WHYWHYCOUNT", paramValues);
                                
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs, format, 1, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}
	//mano
	/*
	 * public Workbook getWhyWhyCountRootCauseExcel(CommonFilter commonFilter,
	 * JSONObject tblJSONObj, String format) throws Exception { ResultSet rs = null;
	 * try { List<String> paramValues = getFilterParamValues(commonFilter);
	 * 
	 * // BDM_FN_WHYWHYROOTCAUSE returns (nreturncnt, cur1) instead of just a cursor
	 * rs = dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYROOTCAUSE",
	 * paramValues);
	 * 
	 * if (rs == null) { throw new
	 * Exception("No data returned from BDM_FN_WHYWHYROOTCAUSE function"); }
	 * 
	 * ExcelUtils excelUtils = new ExcelUtils(tblJSONObj); return
	 * excelUtils.writeToExcel(rs, format, 1, 0, 0);
	 * 
	 * } finally { if (rs != null) { DBActionTemplate.closeConnection(rs, null,
	 * null, null, rs.getStatement().getConnection()); } } }
	 */
	
	public Workbook getWhyWhyCountRootCauseExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
	        throws Exception {
	    ResultSet rs = null;
	    try {
	        List<String> paramValues = getFilterParamValues(commonFilter);

	        rs = dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYROOTCAUSE", paramValues);

	        if (rs == null) {
	            throw new Exception("No data returned from BDM_FN_WHYWHYROOTCAUSE function");
	        }

	        // Skip first 2 rows manually
	        int rowsToSkip = 2;
	        int currentRow = 0;
	        
	        while (currentRow < rowsToSkip && rs.next()) {
	            currentRow++;
	        }
	        
	        // Now rs is positioned at the 3rd row
	        ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
	        return excelUtils.writeToExcel(rs, format, 3, 0, 0);

	    } finally {
	        if (rs != null) {
	            DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	        }
	    }
	}
	//mano
	public Workbook getWhyWhyCountCounterExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format)
	        throws Exception {
	    ResultSet rs = null;
	    try {
	        List<String> paramValues = getFilterParamValues(commonFilter);

	        rs = dbActionTemplate.NewdbFunctionCall2("BDM_FN_WHYWHYCOUNTERMEASURE", paramValues);
	        
	        int rowsToSkip = 2;
	        int currentRow = 0;
	        
	        while (currentRow < rowsToSkip && rs.next()) {
	            currentRow++;
	        }
	        

	        ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
	        return excelUtils.writeToExcel(rs, format, 1, 0, 0);

	    } finally {
	        DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	    }
	}

	private List<String> insertDoneby(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception {
		BdmTlYydonebymst bdmTlYydonebymst = new BdmTlYydonebymst();

		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
			bdmTlYydonebymst.setWwdbWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsWhywhydoneby()))

			bdmTlYydonebymst.setWwdbEmpmKeyid(bdmTlWhywhymst.getWwmsWhywhydoneby());

		bdmTlYydonebymst.setWwdbKeyid("{}");
		bdmTlYydonebymst.setWwdbTempfield1("-");
		bdmTlYydonebymst.setWwdbTempfield2("-");
		bdmTlYydonebymst.setWwdbTempfield3("-");

		bdmTlYydonebymst.setWwdbActive("Y");
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
			bdmTlYydonebymst.setWwdbCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
			bdmTlYydonebymst.setWwdbCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
			bdmTlYydonebymst.setWwdbModifiedon(bdmTlWhywhymst.getWwmsModifiedon());

		bdmTlYydonebymst.setWwdbKeyid(
				dbActionTemplate.getSequenceNumber(BdmTlYydonebymstSql.TBL_BDM_TL_YYDONEBYMST, 12, "YYB", "YYMM", " ")); // set
																															// the
																															// sequnce
																															// number

		sqls.add(BdmTlYydonebymstSql.getInsertSql(bdmTlYydonebymstSql.getBdm_DbFields(),
				bdmTlYydonebymst.getSaveArray()));// add insert sql for detail table

		return sqls;
	}

	private List<String> insertProblemAtt(BdmTlWhywhymst bdmTlWhywhymst, List<String> sqls) throws Exception {
		BdmTlYyproblemattbymst bdmTlYyproblemattbymst = new BdmTlYyproblemattbymst();
//	BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql = new BdmTlYycountermeasurelinkSql();
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsKeyid()))
			bdmTlYyproblemattbymst.setWwpaWwmsKeyid(bdmTlWhywhymst.getWwmsKeyid());
		if (UIUtils.isValidKeyId(bdmTlWhywhymst.getWwmsProblemattendby()))

			bdmTlYyproblemattbymst.setWwpaEmpmKeyid(bdmTlWhywhymst.getWwmsProblemattendby());

		bdmTlYyproblemattbymst.setWwpaKeyid("{}");
		bdmTlYyproblemattbymst.setWwpaTempfield1("-");
		bdmTlYyproblemattbymst.setWwpaTempfield2("-");
		bdmTlYyproblemattbymst.setWwpaTempfield3("-");

		bdmTlYyproblemattbymst.setWwpaActive("Y");
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedby()))
			bdmTlYyproblemattbymst.setWwpaCreatedby(bdmTlWhywhymst.getWwmsCreatedby());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsCreatedon()))
			bdmTlYyproblemattbymst.setWwpaCreatedon(bdmTlWhywhymst.getWwmsCreatedon());
		if (CommonFunctions.isValidKeyId(bdmTlWhywhymst.getWwmsModifiedon()))
			bdmTlYyproblemattbymst.setWwpaModifiedon(bdmTlWhywhymst.getWwmsModifiedon());
		bdmTlYyproblemattbymst.setWwpaKeyid(dbActionTemplate
				.getSequenceNumber(BdmTlYyproblemattbymstSql.TBL_BDM_TL_YYPROBLEMATTBYMST, 12, "YYPA", "", "Y")); // set
																													// the
																													// sequnce
																													// number
		sqls.add(BdmTlYyproblemattbymstSql.getInsertSql(bdmTlYyproblemattbymstSql.getWwpaDbFields(),
				bdmTlYyproblemattbymst.getSaveArray()));

		return sqls;
	}

	@Override
	public List<String[]> getRootCauseList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<String> paramValues = new ArrayList<String>();

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";

			if (UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
				condParms += "FYEARFIRST=" + commonFilter.getAbnDetect() + ";";
			if (UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
				condParms += "FYEAREND=" + commonFilter.getAbnAllch() + ";";
			if (UIUtils.isValidKeyId(commonFilter.getFlid()))
				condParms += "FLID=" + commonFilter.getFlid() + ";";

			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("BEFORE FUNC CALL");
			//
			List<String[]> dataList = fnCallApi.callFunction("BDM_FN_WHYWHYROOTCAUSE_SB",paramValues,3,false);

			
			
			
			// List<String[]> dataList =
			// dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACCRPT",
			// paramValues);
			CommonMessage.debugMsg("Length...." + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	@Override
	public List<String[]> getCounterMeasureList(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try {
			List<String> paramValues = new ArrayList<String>();

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";

			if (UIUtils.isValidKeyId(commonFilter.getAbnDetect()))
				condParms += "FYEARFIRST=" + commonFilter.getAbnDetect() + ";";
			if (UIUtils.isValidKeyId(commonFilter.getAbnAllch()))
				condParms += "FYEAREND=" + commonFilter.getAbnAllch() + ";";
			if (UIUtils.isValidKeyId(commonFilter.getFlid()))
				condParms += "FLID=" + commonFilter.getFlid() + ";";

			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("BEFORE FUNC CALL");
			//
			List<String[]> dataList = fnCallApi.callFunction("BDM_FN_WHYWHYCOUNTERMEASURE_SB", paramValues,3,false);
			
			
			
			

			// List<String[]> dataList =
			// dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.HSE_FN_HSEMODEVSACCRPT",
			// paramValues);
			CommonMessage.debugMsg("Length...." + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList;
		} catch (Exception e) {
			throw new Exception(e.getMessage());

		}
	}

	/*
	 * @Override public List<String[]> getEmailIds(String WhyWhyno) throws Exception
	 * { // TODO Auto-generated method stub
	 * 
	 * 
	 * String fnlnId =getWhyWhyDetails(WhyWhyno); String pillarId
	 * =getWhyWhyPillarId(WhyWhyno); String timeSpent = getWhywhyTimeSpnt(WhyWhyno);
	 * Float spentTime=Float.parseFloat(timeSpent); String empmKeyId =
	 * getWhywhyEmpmID(WhyWhyno); String tradeId=getWhywhyTradeid(WhyWhyno); String
	 * appStatus=getWhywhyAppStatus(WhyWhyno); String
	 * roleId=getWhywhyAppRoleId(WhyWhyno);
	 * 
	 * CommonMessage.debugMsg("   spentTimespentTime  "+spentTime); // String sql =
	 * " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyId
	 * +"'";
	 * 
	 * String flid=null; String tradeid=null; String pillarid=null; String
	 * empmId=null; String[] timeSpent= null;
	 * System.out.print("whydtlswhydtlswhydtls  "+whydtls.size());; for(int
	 * i=0;i<whydtls.size();i++){ flid= whydtls.get(i).toString(); timeSpent=
	 * whydtls.get(i); tradeid= whydtls.get(i).toString(); pillarid=
	 * whydtls.get(i).toString(); empmId= whydtls.get(i).toString(); }
	 * 
	 * CommonMessage.debugMsg(" IN Side he Send mail" +fnlnId+"  "+timeSpent
	 * +"  "+tradeId +"   "+pillarId +"     "+empmKeyId);
	 * 
	 * String condSql=null;
	 * 
	 * if(tradeId.equals("TGT001") || tradeId.equals("TGT003")
	 * ||tradeId.equals("TGT004") ||tradeId.equals("TGT007")
	 * ||tradeId.equals("TGT008") ) { condSql=
	 * " SELECT flid   FROM gen_mv_flidhierarchy    WHERE INSTR (parentflids "+
	 * "  || '-'  || flid, "+ "   (SELECT SUBSTR(PARENTFLIDS,15,12) "+
	 * "  FROM gen_mv_flidhierarchy "+ " WHERE FLID='"+fnlnId+"' "+
	 * "  ))        >0 and FNLN_ELEMENTTYPE='LCN' ";
	 * 
	 * } else{ condSql=
	 * " SELECT flid   FROM gen_mv_flidhierarchy    WHERE INSTR (parentflids "+
	 * "  || '-'  || flid, "+ "   (SELECT SUBSTR(PARENTFLIDS,28,12) "+
	 * "  FROM gen_mv_flidhierarchy "+ " WHERE FLID='"+fnlnId+"' "+
	 * "  ))        >0 and FNLN_ELEMENTTYPE='SBU' "; } String sqlPillarID=
	 * " SELECT EMPM_EMAIL "+ " FROM "+ "  (SELECT FRT_EMPM_KEYID, "+
	 * "    EMPM_NAME, "+ "    FRT_FRL_KEYID, "+ "    FRT_ROLE_KEYID, "+
	 * "    FRT_FNLN_KEYID, "+ "    FRP_TRADEID, "+ "    EMPM_EMAIL "+
	 * "    FROM GEN_TL_FNLNROLETEAM, "+ "    GEN_TL_TEAMTRADELINK, "+
	 * "    GEN_TL_EMPLOYEEMST, "+ "    GEN_TL_WHYWHY_PILLAR_ROLELINK "+
	 * "  WHERE EMPM_KEYID    =FRT_EMPM_KEYID "+
	 * "  AND FRP_FRT_KEYID(+)=FRT_KEYID "+
	 * "  AND YYRL_ROLEID(+)  = FRT_ROLE_KEYID "+ "  AND EMPM_ACTIVE     ='Y' "+
	 * "  AND FRP_TRADEID     ='"+tradeId+"' "+ "  ORDER BY FRT_EMPM_KEYID "+
	 * "  ), "+ "  GEN_MV_FLIDHIERARCHY "+ " WHERE FRT_FNLN_KEYID(+)=FLID "+
	 * " AND FRT_ROLE_KEYID    IN ('AROL0003','AROL0004','AROL0005','AROL0131','AROL0132','AROL0133','AROL0134','AROL0135') "
	 * + "  AND FRT_FNLN_KEYID    IN "+ "  (SELECT flid "+
	 * "  FROM gen_mv_flidhierarchy "+ "  WHERE INSTR (parentflids "+
	 * "    || '-' || flid,  "+ "    (SELECT SUBSTR(PARENTFLIDS,54,12) "+
	 * "   FROM gen_mv_flidhierarchy "+ "    WHERE FLID='"+fnlnId+"' "+
	 * "    ))        >0 "+ "   )         UNION "+ " SELECT EMPM_EMAIL "+
	 * " FROM GEN_TL_EMPLOYEEMST "+ " WHERE EMPM_KEYID IN "+
	 * "  (SELECT FRT_EMPM_KEYID "+ "  FROM GEN_TL_FNLNROLETEAM, "+
	 * "  GEN_MV_FLIDHIERARCHY "+ "  WHERE FLID        =FRT_FNLN_KEYID "+
	 * "  AND FRT_ROLE_KEYID= "+ "    (SELECT YYRL_ROLEID "+
	 * "    FROM GEN_TL_WHYWHY_PILLAR_ROLELINK "+
	 * "    WHERE YYRL_PILLARID='"+pillarId+"'  ) "+ "  AND FRT_FNLN_KEYID IN "+
	 * "    ( "+condSql+" )) "+ " UNION  "+
	 * " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='"+empmKeyId+"'"
	 * ;
	 * 
	 * 
	 * String sqlDmt=" SELECT EMPM_EMAIL  FROM "+ "  (SELECT FRT_EMPM_KEYID, "+
	 * "    EMPM_NAME, "+ "    FRT_FRL_KEYID, "+ "    FRT_ROLE_KEYID, "+
	 * "    FRT_FNLN_KEYID, "+ "    FRP_TRADEID, "+ "   EMPM_EMAIL "+
	 * "  FROM GEN_TL_FNLNROLETEAM, "+ "    GEN_TL_TEAMTRADELINK, "+
	 * "    GEN_TL_EMPLOYEEMST, "+ "    GEN_TL_WHYWHY_PILLAR_ROLELINK "+
	 * "  WHERE EMPM_KEYID    =FRT_EMPM_KEYID "+
	 * "  AND FRP_FRT_KEYID(+)=FRT_KEYID "+
	 * "  AND YYRL_ROLEID(+)  = FRT_ROLE_KEYID "+ "  AND EMPM_ACTIVE     ='Y' "+
	 * "  AND FRP_TRADEID     ='"+tradeId+"' "+ "  ORDER BY FRT_EMPM_KEYID "+
	 * "  ), "+ "  GEN_MV_FLIDHIERARCHY "+ " WHERE FRT_FNLN_KEYID(+)=FLID "+
	 * " AND FRT_ROLE_KEYID    IN ('AROL0003','AROL0004','AROL0005') "+
	 * "  AND FRT_FNLN_KEYID    IN "+ "  (SELECT flid "+
	 * "  FROM gen_mv_flidhierarchy "+ "  WHERE INSTR (parentflids "+
	 * "    || '-' || flid,  "+ "    (SELECT SUBSTR(PARENTFLIDS,54,12) "+
	 * "   FROM gen_mv_flidhierarchy "+ "    WHERE FLID='"+fnlnId+"' "+
	 * "    ))        >0 "+ "   )        UNION  "+
	 * " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='"+empmKeyId+"'"
	 * ;;
	 * 
	 * List<String[]> emailIds;
	 * 
	 * CommonMessage.debugMsg(sqlDmt +" In side the IF Employee email sql :"
	 * +sqlPillarID);
	 * 
	 * if(spentTime>=4&&appStatus.equals("A")){ emailIds=
	 * dbActionTemplate.getDataList(sqlPillarID); CommonMessage.debugMsg(sqlDmt
	 * +" In side the IF Employee email sql :" +sqlPillarID);
	 * 
	 * } else{ CommonMessage.debugMsg(" inside the else "); emailIds=
	 * dbActionTemplate.getDataList(sqlDmt); } String[] emailId=dbActionTemplate.E
	 * getDataList(sql); emailIds.add(emailId); return emailIds;
	 * 
	 * }
	 */ 
	//mano sep 
	@Override 
	public List<String[]> getEmailIds(String WhyWhyno) throws Exception {
	    // TODO Auto-generated method stub
	    String fnlnId = getWhyWhyDetails(WhyWhyno);
	    String pillarId = getWhyWhyPillarId(WhyWhyno);
	    String timeSpent = getWhywhyTimeSpnt(WhyWhyno);
	    Float spentTime = Float.parseFloat(timeSpent);
	    String empmKeyId = getWhywhyEmpmID(WhyWhyno);
	    String tradeId = getWhywhyTradeid(WhyWhyno);
	    String appStatus = getWhywhyAppStatus(WhyWhyno);
	    String roleId = getWhywhyAppRoleId(WhyWhyno);
	    
	    CommonMessage.debugMsg(" spentTimespentTime " + spentTime);
	    CommonMessage.debugMsg(" IN Side he Send mail" + fnlnId + " " + timeSpent + " " + tradeId + " " + pillarId + " " + empmKeyId);
	    
	    String condSql = null;
	    
	    if(tradeId.equals("TGT001") || tradeId.equals("TGT003") || tradeId.equals("TGT004") || 
	       tradeId.equals("TGT007") || tradeId.equals("TGT008")) {
	        condSql = " SELECT flid FROM gen_mv_flidhierarchy WHERE POSITION(" +
	                " (SELECT SUBSTRING(PARENTFLIDS FROM 15 FOR 12) " +
	                " FROM gen_mv_flidhierarchy " +
	                " WHERE FLID='" + fnlnId + "' " +
	                " ) IN (parentflids || '-' || flid)) > 0 " +
	                " AND FNLN_ELEMENTTYPE='LCN' ";
	    } else {
	        condSql = " SELECT flid FROM gen_mv_flidhierarchy WHERE POSITION(" +
	                " (SELECT SUBSTRING(PARENTFLIDS FROM 28 FOR 12) " +
	                " FROM gen_mv_flidhierarchy " +
	                " WHERE FLID='" + fnlnId + "' " +
	                " ) IN (parentflids || '-' || flid)) > 0 " +
	                " AND FNLN_ELEMENTTYPE='SBU' ";
	    }
	    
	    String sqlPillarID = " SELECT EMPM_EMAIL " +
	            " FROM " +
	            " (SELECT FRT_EMPM_KEYID, " +
	            " EMPM_NAME, " +
	            " FRT_FRL_KEYID, " +
	            " FRT_ROLE_KEYID, " +
	            " FRT_FNLN_KEYID, " +
	            " FRP_TRADEID, " +
	            " EMPM_EMAIL " +
	            " FROM GEN_TL_FNLNROLETEAM " +
	            " LEFT JOIN GEN_TL_TEAMTRADELINK ON FRP_FRT_KEYID = FRT_KEYID " +
	            " INNER JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = FRT_EMPM_KEYID " +
	            " LEFT JOIN GEN_TL_WHYWHY_PILLAR_ROLELINK ON YYRL_ROLEID = FRT_ROLE_KEYID " +
	            " WHERE EMPM_ACTIVE = 'Y' " +
	            " AND FRP_TRADEID = '" + tradeId + "' " +
	            " ORDER BY FRT_EMPM_KEYID " +
	            " ) subquery " +
	            " LEFT JOIN GEN_MV_FLIDHIERARCHY ON FRT_FNLN_KEYID = FLID " +
	            " WHERE FRT_ROLE_KEYID IN ('AROL0003','AROL0004','AROL0005','AROL0131','AROL0132','AROL0133','AROL0134','AROL0135') " +
	            " AND FRT_FNLN_KEYID IN " +
	            " (SELECT flid " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE POSITION(" +
	            " (SELECT SUBSTRING(PARENTFLIDS FROM 54 FOR 12) " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE FLID='" + fnlnId + "' " +
	            " ) IN (parentflids || '-' || flid)) > 0 " +
	            " ) UNION " +
	            " SELECT EMPM_EMAIL " +
	            " FROM GEN_TL_EMPLOYEEMST " +
	            " WHERE EMPM_KEYID IN " +
	            " (SELECT FRT_EMPM_KEYID " +
	            " FROM GEN_TL_FNLNROLETEAM " +
	            " INNER JOIN GEN_MV_FLIDHIERARCHY ON FLID = FRT_FNLN_KEYID " +
	            " WHERE FRT_ROLE_KEYID = " +
	            " (SELECT YYRL_ROLEID " +
	            " FROM GEN_TL_WHYWHY_PILLAR_ROLELINK " +
	            " WHERE YYRL_PILLARID='" + pillarId + "' ) " +
	            " AND FRT_FNLN_KEYID IN " +
	            " ( " + condSql + " )) " +
	            " UNION " +
	            " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyId + "'";
	    
	    String sqlDmt = " SELECT EMPM_EMAIL FROM " +
	            " (SELECT FRT_EMPM_KEYID, " +
	            " EMPM_NAME, " +
	            " FRT_FRL_KEYID, " +
	            " FRT_ROLE_KEYID, " +
	            " FRT_FNLN_KEYID, " +
	            " FRP_TRADEID, " +
	            " EMPM_EMAIL " +
	            " FROM GEN_TL_FNLNROLETEAM " +
	            " LEFT JOIN GEN_TL_TEAMTRADELINK ON FRP_FRT_KEYID = FRT_KEYID " +
	            " INNER JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = FRT_EMPM_KEYID " +
	            " LEFT JOIN GEN_TL_WHYWHY_PILLAR_ROLELINK ON YYRL_ROLEID = FRT_ROLE_KEYID " +
	            " WHERE EMPM_ACTIVE = 'Y' " +
	            " AND FRP_TRADEID = '" + tradeId + "' " +
	            " ORDER BY FRT_EMPM_KEYID " +
	            " ) subquery " +
	            " LEFT JOIN GEN_MV_FLIDHIERARCHY ON FRT_FNLN_KEYID = FLID " +
//	            " WHERE FRT_ROLE_KEYID IN ('AROL0003','AROL0004','AROL0005') " +
	            " WHERE FRT_ROLE_KEYID IN ('AROL0003','AROL0004','AROL0005','AROL0131','AROL0132','AROL0133','AROL0134','AROL0135') " +
	            " AND FRT_FNLN_KEYID IN " +
	            " (SELECT flid " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE POSITION(" +
	            " (SELECT SUBSTRING(PARENTFLIDS FROM 54 FOR 12) " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE FLID='" + fnlnId + "' " +
	            " ) IN (parentflids || '-' || flid)) > 0 " +
	            " ) UNION " +
	            " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyId + "'";
	    
	    String sqlTpmCell = " SELECT EMPM_EMAIL FROM " +
	            " (SELECT FRT_EMPM_KEYID, " +
	            " EMPM_NAME, " +
	            " FRT_ROLE_KEYID, " +
	            " FRT_FNLN_KEYID, " +
	            " EMPM_EMAIL " +
	            " FROM GEN_TL_FNLNROLETEAM " +
	            " INNER JOIN GEN_TL_EMPLOYEEMST ON EMPM_KEYID = FRT_EMPM_KEYID " +
	            " INNER JOIN GEN_MV_FLIDHIERARCHY ON FRT_FNLN_KEYID = FLID " +
	            " WHERE EMPM_ACTIVE = 'Y' " +
	            " AND FRT_ROLE_KEYID IN ('AROL0011') " +
	            " AND FRT_FNLN_KEYID IN " +
	            " (SELECT flid " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE POSITION(" +
	            " (SELECT SUBSTRING(PARENTFLIDS FROM 15 FOR 12) " +
	            " FROM gen_mv_flidhierarchy " +
	            " WHERE FLID='" + fnlnId + "' " +
	            " ) IN (parentflids || '-' || flid)) > 0 " +
	            " )  " +
	            " ORDER BY FRT_EMPM_KEYID " +
	            " )  " ;
	          

	    
	    sqlPillarID =  sqlPillarID + " UNION " + sqlTpmCell;
	    String sqlDmtTpm = sqlDmt + " UNION " + sqlTpmCell;
	    List<String[]> emailIds;
	    CommonMessage.debugMsg(sqlDmt + " In side the IF Employee email sql :" + sqlPillarID);
	    
	    
	    if(spentTime >= 4 ) { // && appStatus.equals("A")
	        emailIds = dbActionTemplate.getDataList(sqlPillarID);
	        CommonMessage.debugMsg(sqlDmt + " In side the IF Employee email sql :" + sqlPillarID);
	    }else if(spentTime > 2) {
	    	emailIds = dbActionTemplate.getDataList(sqlDmtTpm);
	    }else {
	        CommonMessage.debugMsg(" inside the else ");
	        emailIds = dbActionTemplate.getDataList(sqlDmt);
	    }
	    
	    /*String[] emailId=dbActionTemplate.E getDataList(sql);
	    emailIds.add(emailId);*/
	    
	    return emailIds;
	}
//
	public List<String[]> getPcEmailIds(String WhyWhyno) throws Exception {
		// TODO Auto-generated method stub

		CommonMessage.debugMsg(WhyWhyno + "  WhyWhynoWhyWhynoWhyWhynoWhyWhyno ");

		String fnlnId = getWhyWhyDetails(WhyWhyno);
		String pillarId = getWhyWhyPillarId(WhyWhyno);
		String timeSpent = getWhywhyTimeSpnt(WhyWhyno);
		Float spentTime = Float.parseFloat(timeSpent);
		String empmKeyId = getWhywhyEmpmID(WhyWhyno);
		String tradeId = getWhywhyTradeid(WhyWhyno);
		String appStatus = getWhywhyAppStatus(WhyWhyno);
		String roleId = getWhywhyAppRoleId(WhyWhyno);

		CommonMessage.debugMsg("   spentTimespentTime  " + spentTime);

		CommonMessage.debugMsg(" IN Side he Send mail" + fnlnId + "  " + timeSpent + "  " + tradeId + "   " + pillarId
				+ "     " + empmKeyId);

		String condSql = null;

		if (tradeId.equals("TGT001") || tradeId.equals("TGT003") || tradeId.equals("TGT004") || tradeId.equals("TGT007")
				|| tradeId.equals("TGT008")) {
			/*
			 * condSql =
			 * " SELECT flid   FROM gen_mv_flidhierarchy    WHERE INSTR (parentflids " +
			 * "  || '-'  || flid, " + "   (SELECT SUBSTR(PARENTFLIDS,15,12) " +
			 * "  FROM gen_mv_flidhierarchy " + " WHERE FLID='" + fnlnId + "' " +
			 * "  ))        >0 and FNLN_ELEMENTTYPE='LCN' ";
			 */
			condSql =
			        " SELECT flid " +
			        " FROM gen_mv_flidhierarchy " +
			        " WHERE POSITION( " +
			        "       (SELECT SUBSTRING(PARENTFLIDS,15,12) " +
			        "          FROM gen_mv_flidhierarchy " +
			        "         WHERE FLID='" + fnlnId + "') " +
			        "       IN (parentflids || '-' || flid) " +
			        "      ) > 0 " +
			        " AND FNLN_ELEMENTTYPE='LCN' ";

		} else {
			/*
			 * condSql =
			 * " SELECT flid   FROM gen_mv_flidhierarchy    WHERE INSTR (parentflids " +
			 * "  || '-'  || flid, " + "   (SELECT SUBSTR(PARENTFLIDS,28,12) " +
			 * "  FROM gen_mv_flidhierarchy " + " WHERE FLID='" + fnlnId + "' " +
			 * "  ))        >0 and FNLN_ELEMENTTYPE='SBU' ";
			 */
			condSql =
			        " SELECT flid " +
			        " FROM gen_mv_flidhierarchy " +
			        " WHERE POSITION( " +
			        "       (SELECT SUBSTRING(PARENTFLIDS,28,12) " +
			        "          FROM gen_mv_flidhierarchy " +
			        "         WHERE FLID='" + fnlnId + "') " +
			        "       IN (parentflids || '-' || flid) " +
			        "      ) > 0 " +
			        " AND FNLN_ELEMENTTYPE='SBU' ";
		}
		String sqlPillarID = " SELECT EMPM_EMAIL " + " FROM GEN_TL_EMPLOYEEMST " + " WHERE EMPM_KEYID IN "
				+ "  (SELECT FRT_EMPM_KEYID " + "  FROM GEN_TL_FNLNROLETEAM, " + "  GEN_MV_FLIDHIERARCHY "
				+ "  WHERE FLID        =FRT_FNLN_KEYID " + "  AND FRT_ROLE_KEYID IN " + "    (SELECT YYRL_ROLEID "
				+ "    FROM GEN_TL_WHYWHY_PILLAR_ROLELINK " + "    WHERE YYRL_PILLARID='" + pillarId + "'  ) "
				+ "  AND FRT_FNLN_KEYID IN " + "    ( " + condSql + " )) ";

		List<String[]> emailIds = null;

		CommonMessage.debugMsg(" In side the IF Employee email sql :" + sqlPillarID);

		if (spentTime >= 4 && appStatus.equals("A")) {
			emailIds = dbActionTemplate.getDataList(sqlPillarID);
			CommonMessage.debugMsg(" In side the IF Employee email sql :" + sqlPillarID);

		}

		return emailIds;

	}

	private String getWhywhyEmpmID(String whyWhyno) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_CREATEDBY FROM" + " BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + whyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + whyWhyno);
		String empKeyid = null;
		try {
			empKeyid = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empKeyid;
	}

	private String getWhyWhyDetails(String whyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_FLID FROM" + " BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + whyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + whyWhyno);
		String flid = null;
		try {
			flid = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flid;
	}

	private String getWhyWhyPillarId(String whyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_PILLARID FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + whyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + whyWhyno);
		String pillarId = null;
		try {
			pillarId = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pillarId;
	}

	private String getWhywhyTimeSpnt(String whyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_TIMESPENT FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + whyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + whyWhyno);
		String timeSpnt = null;
		try {
			timeSpnt = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return timeSpnt;
	} // getWhywhyTradeid(WhyWhyno)

	private String getWhywhyTradeid(String WhyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_TRADEID FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + WhyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + WhyWhyno);
		String tradeId = null;
		try {
			tradeId = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradeId;
	} // getWhywhyTradeid(WhyWhyno)

	private String getWhywhyAppStatus(String WhyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_APPSTATUS FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + WhyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + WhyWhyno);
		String tradeId = null;
		try {
			tradeId = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradeId;
	} // getWhywhyTradeid(WhyWhyno)

	private String getWhywhyAppRoleId(String WhyWhyno) {
		// TODO Auto-generated method stub
		String sql = "SELECT WWMS_APPROLEID FROM BDM_TL_WHYWHYMST WHERE WWMS_KEYID='" + WhyWhyno + "'";

		CommonMessage.debugMsg(sql + " Query1234" + WhyWhyno);
		String tradeId = null;
		try {
			tradeId = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tradeId;
	} // getWhywhyTradeid(WhyWhyno)

	@Override
	public String getCCEmailId(String empmKeyId) throws Exception {
		// TODO Auto-generated method stub
		empmKeyId = " 00012";
		String sql = " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyId + "'";
		CommonMessage.debugMsg("  Employee email sql :" + sql);
		String emailId = dbActionTemplate.getSingleValue(sql);
		return emailId;
	}
	
	@Override
	public String getWhywhyTrade(String tradeId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT TRDM_NAME FROM GEN_TL_TRADEMST WHERE TRDM_KEYID='" + tradeId + "'";

		CommonMessage.debugMsg(sql + " Query1234" + tradeId);
		String trade = null;
		try {
			trade = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trade;
	}
	
	@Override
	public String getWhywhyPillar(String pillaeId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "SELECT tpmp_name FROM gen_tl_tpmpillarmst WHERE tpmp_keyid='" + pillaeId + "'";

		CommonMessage.debugMsg(sql + " Query1234" + pillaeId);
		String trade = null;
		try {
			trade = dbActionTemplate.getSingleValue(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trade;
	}

}
