package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlGenmaintenanceDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlSetupadjsplitSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlGenmaintenanceSql;
//import com.akranta.tpm.dao.sql.CliTlStandardsSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFactorymstSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlGenmaintenanceSql;
import com.akranta.tpm.dao.sql.SqlUtils;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.BAL_BdmTlSetupadjsplit;
import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFactorymst;
//import com.akranta.tpm.model.BAL_PlmTlGenmaintenance;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.DocTypeConstants;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.BAL_GeneralMaintenanceServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;

/* dao implementation */
public class BAL_PlmTlGenmaintenanceDaoImpl implements BAL_PlmTlGenmaintenanceDao {

	private DBActionTemplate dbActionTemplate;
	private CommonFilterSqls commonFilterSqls;
	private BAL_BdmTlSetupadjsplitSql bdmTlSetupadjsplitSql;
	private BAL_GeneralMaintenanceServiceApi GeneralMaintenanceServiceApi;
	FunctionCallApi fnCallApi;

	public void BAL_PlmTlGenmaintenanceDaoImplJwt(String JwtToken) {
		try {
			GeneralMaintenanceServiceApi = new BAL_GeneralMaintenanceServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BAL_PlmTlGenmaintenanceDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		bdmTlSetupadjsplitSql = new BAL_BdmTlSetupadjsplitSql();
		commonFilterSqls = new CommonFilterSqls();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_PlmTlGenmaintenance create(BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		List<Object[]> valueList = new ArrayList<Object[]>();
		List<int[]> dataTypes = new ArrayList<int[]>();
		List<String> charList = new ArrayList<String>();
		BAL_PlmTlGenmaintenanceSql plmTlGenmaintenancesql = new BAL_PlmTlGenmaintenanceSql(); // contains dbtable,field
																								// names, Field types
																								// and related sqls of
																								// master table

		plmTlGenmaintenance.setGmntKeyid(dbActionTemplate
				.getSequenceNumber(BAL_PlmTlGenmaintenanceSql.TBL_PLM_TL_GENMAINTENANCE, 11, "GMN", "YYMM", "Y")); // set
																													// the
																													// sequnce
																													// number
		insertSetupAndAdjustment(plmTlGenmaintenance, sqls, valueList, dataTypes, charList);
		if (FilterCondSql.isValidKeyId(plmTlGenmaintenance.getGmntRefdocid())) {
			WomTlWomst womTlWomst = new WomTlWomst();
			updateWorkOrder(plmTlGenmaintenance, womTlWomst, sqls);
		} else
			insertWorkOrder(plmTlGenmaintenance, sqls);
		sqls.add(BAL_PlmTlGenmaintenanceSql.getInsertSql(plmTlGenmaintenancesql.getGmntDbFields(),
				plmTlGenmaintenance.getSaveArray())); // add insert sql for master table

		// if (plmTlGenmaintenance.getTeamList().size()>0)
		if (plmTlGenmaintenance.getTeamList() != null)
			commonFilterSqls.getTeamLinkSqls(plmTlGenmaintenance.getTeamList(), DocTypeConstants.woms,
					plmTlGenmaintenance.getGmntKeyid(), sqls, dbActionTemplate);

		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return plmTlGenmaintenance;
	}

	public BAL_PlmTlGenmaintenance update(BAL_PlmTlGenmaintenance plmTlGenmaintenance, WomTlWomst womTlWomst)
			throws Exception, BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>();

		List<Object[]> valueList = new ArrayList<Object[]>();
		List<int[]> dataTypes = new ArrayList<int[]>();
		List<String> charList = new ArrayList<String>();
		BAL_PlmTlGenmaintenanceSql plmTlGenmaintenancesql = new BAL_PlmTlGenmaintenanceSql(); // contains dbtable,field
																								// names, Field types
																								// and related sqls of
																								// master table

		System.out.println("KEY ID  NDNNDNN  " + plmTlGenmaintenance.getGmntRefdocid());

		if (FilterCondSql.isValidKeyId(plmTlGenmaintenance.getGmntRefdocid())) {
			WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
			fillWorkOrder(womTlWomst, plmTlGenmaintenance);
			String checkWOConfig = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST,
					"CNFM_SETTINGVALUE", "CNFM_CODE", "MSRCOMPLETEDDATE");
			String MSRStatus = womTlWomst.getWomsStatus();
			if (UIUtils.isValidKeyId(MSRStatus)) {
				if (MSRStatus.equals("C")) {
					if (UIUtils.isValidKeyId(checkWOConfig)) {

						int noOfDays = Integer.parseInt(checkWOConfig);
						int selDays = CommonFunctions.getDateDiff(womTlWomst.getWomsOccurreddate(),
								womTlWomst.getWomsWorkenddate());
						CommonFunctions.debugMsg(noOfDays + " : " + selDays);
						if (selDays > noOfDays)
							throw new BusinessApplicationExceptions("MAXCOMPDATE,");
					}
				}
			}
			womTlWomst.setWomsKeyid(plmTlGenmaintenance.getGmntRefdocid());
			CommonFunctions.debugMsg("Activity Id : " + womTlWomst.getWomsActivityid());
			String updateWOSql = SqlUtils.getUpdateSql(TableNames.TBL_BAL_WOM_TL_MST, womTlWomstSql.getWomsDbFields(),
					womTlWomst.getSaveArray());
			if (UIUtils.isValidKeyId(updateWOSql))
				updateWOSql += " where WOMS_KEYID = ?";
			Object[] updateWO = { womTlWomst.getWomsKeyid() };
			int[] updateWODatas = { Types.VARCHAR };
			sqls.add(updateWOSql);
			valueList.add(updateWO);
			dataTypes.add(updateWODatas);
			charList.add("Q");
			// updateWorkOrder(plmTlGenmaintenance,womTlWomst,sqls);
		} else {
			insertWorkOrder(plmTlGenmaintenance, sqls);
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
		}

		String updateGMSql = SqlUtils.getUpdateSql(BAL_PlmTlGenmaintenanceSql.TBL_PLM_TL_GENMAINTENANCE,
				plmTlGenmaintenancesql.getGmntDbFields(), plmTlGenmaintenance.getSaveArray());
		if (UIUtils.isValidKeyId(updateGMSql))
			updateGMSql += " where GMNT_KEYID = ?";
		Object[] updateGM = { plmTlGenmaintenance.getGmntKeyid() };
		int[] updateGMDatas = { Types.VARCHAR };
		sqls.add(updateGMSql);
		valueList.add(updateGM);
		dataTypes.add(updateGMDatas);
		charList.add("Q");
		// sqls.add(PlmTlGenmaintenanceSql.getUpdateSql(plmTlGenmaintenancesql.getGmntDbFields(),
		// plmTlGenmaintenance.getSaveArray()));

		CommonFunctions.debugMsg("before Team Sqls");
//for team
		// if (plmTlGenmaintenance.getTeamList().size()>0)
		if (plmTlGenmaintenance.getTeamList() != null)
			commonFilterSqls.getTeamLinkSqls(plmTlGenmaintenance.getTeamList(), DocTypeConstants.woms,
					plmTlGenmaintenance.getGmntKeyid(), sqls, dbActionTemplate, valueList, dataTypes, charList);

		CommonFunctions.debugMsg("sqls.size" + sqls.size());
		// CommonFunctions.debugMsg("sqls.get(1)"+sqls.get(1).toString());
		// CommonFunctions.debugMsg("sqls.get(1)"+sqls.get(2).toString());

		CommonFunctions.debugMsg("after Team Sqls");

		String setupAdjFlag = dbActionTemplate.getSingleValue(BAL_PlmTlGenmaintenanceSql.checkSetupAndAdjQuery());
		if (UIUtils.isValidKeyId(setupAdjFlag))
			;
		{
			if (setupAdjFlag.equals("16")) {

				if (plmTlGenmaintenance.getGmntStatus().equals("C")) {
					insertSetupAndAdjustment(plmTlGenmaintenance, sqls, valueList, dataTypes, charList);
					String insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSFORSETUPANDADJ";
					Object[] insertPCSDatas = { plmTlGenmaintenance.getGmntMachineid(),
							plmTlGenmaintenance.getGmntOccureddate(), plmTlGenmaintenance.getGmntWoenddate(),
							plmTlGenmaintenance.getGmntRefdocid(), plmTlGenmaintenance.getGmntCreatedby() };
					int[] insertPCSTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR };
					sqls.add(insertPCSSql);
					valueList.add(insertPCSDatas);
					dataTypes.add(insertPCSTypes);
					charList.add("P");
					CommonFunctions.debugMsg(plmTlGenmaintenance.getGmntMachineid() + ","
							+ plmTlGenmaintenance.getGmntOccureddate() + "," + plmTlGenmaintenance.getGmntWoenddate()
							+ "," + plmTlGenmaintenance.getGmntRefdocid() + ","
							+ plmTlGenmaintenance.getGmntCreatedby());
				}

			}
		}

		char[] sqlType = new char[charList.size()];
		for (int c = 0; c < sqlType.length; c++) {
			sqlType[c] = charList.get(c).charAt(0);
			CommonFunctions.debugMsg(c + " : " + sqlType[c]);
		}
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes, sqlType);
		// dbActionTemplate.executeStatements(sqls);

		return plmTlGenmaintenance;
	}

	@Override
	public BAL_PlmTlGenmaintenance delete(BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlGenmaintenanceSql plmTlGenmaintenancesql = new BAL_PlmTlGenmaintenanceSql();
		try {

			sqls.add(BAL_PlmTlGenmaintenanceSql.getDeleteSql(plmTlGenmaintenancesql.getGmntDbFields(),
					plmTlGenmaintenance.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return plmTlGenmaintenance;
	}

	public BAL_BdmTlSetupadjsplit deleteSubLoss(BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlSetupadjsplitSql bdmTlSetupadjsplitSql = new BAL_BdmTlSetupadjsplitSql();
		try {

			sqls.add(BAL_BdmTlSetupadjsplitSql.getDeleteSql(bdmTlSetupadjsplitSql.getSupsDbFields(),
					bdmTlSetupadjsplit.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return bdmTlSetupadjsplit;
	}

	private List<String> insertSetupAndAdjustment(BAL_PlmTlGenmaintenance plmTlGenmaintenance, List<String> sqls,
			List<Object[]> valueList, List<int[]> dataTypes, List<String> charList) throws Exception {
		if (plmTlGenmaintenance.getBdmTlSetupadjsplit() != null
				&& plmTlGenmaintenance.getBdmTlSetupadjsplit().size() > 0) {
			sqls.add(BAL_BdmTlSetupadjsplitSql.deleteExist(plmTlGenmaintenance.getGmntRefdocid()));
			valueList.add(null);
			dataTypes.add(null);
			charList.add("Q");
			for (int i = 0; i < plmTlGenmaintenance.getBdmTlSetupadjsplit().size(); i++) {
				BAL_BdmTlSetupadjsplit bdmTlSetupadjsplit = (BAL_BdmTlSetupadjsplit) plmTlGenmaintenance
						.getBdmTlSetupadjsplit().get(i);
				// CommonFunctions.debugMsg(i+" : "+bdmTlSetupadjsplit.getSupsLossid() +
				// "->"+bdmTlSetupadjsplit.getSupsDuration());
				bdmTlSetupadjsplit.setSupsActive("Y");
				bdmTlSetupadjsplit.setSupsCreatedby(plmTlGenmaintenance.getGmntCreatedby());
				bdmTlSetupadjsplit.setSupsCreatedon(plmTlGenmaintenance.getGmntModifiedon());
				bdmTlSetupadjsplit.setSupsModificeon(plmTlGenmaintenance.getGmntModifiedon());
				bdmTlSetupadjsplit.setSupsMachineid(plmTlGenmaintenance.getGmntMachineid());
				bdmTlSetupadjsplit.setSupsRefdocid(plmTlGenmaintenance.getGmntRefdocid());
				bdmTlSetupadjsplit.setSupsKeyid(dbActionTemplate
						.getSequenceNumber(BAL_BdmTlSetupadjsplitSql.TBL_BDM_TL_SETUPADJSPLIT, 11, "SUA", "YYMM", "Y")); // set
																															// the
																															// sequnce
																															// number
				sqls.add(BAL_BdmTlSetupadjsplitSql.getInsertSql(bdmTlSetupadjsplitSql.getSupsDbFields(),
						bdmTlSetupadjsplit.getSaveArray()));
				valueList.add(null);
				dataTypes.add(null);
				charList.add("Q");
			}
		}
		return sqls;
	}

	public List<String[]> getdataGenMain(CommonFilter commonFilter, String relto, String setupAdj) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Hi in gm getdatGenmain");
			if (Constants.passNullDate.contains(commonFilter.getFromMonth())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals("Y"))) {
				commonFilter.setFromMonth(CommonFunctions.getFirstDateofMonth(-5).substring(3, 11));
				commonFilter.setToMonth(CommonFunctions.getDate().substring(3, 11));
				commonFilter.setMonwise("Y");
			} else if (Constants.passNullDate.contains(commonFilter.getFromDate())
					&& (commonFilter.getMonwise() == null || commonFilter.getMonwise().equals(" "))) {
				commonFilter.setFromDate(CommonFunctions.getFirstDateofMonth(-3));
				commonFilter.setToDate(CommonFunctions.getDate());
			}
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																					// +";FROMTOROW="+commonFilter.getFromRow()
																					// +" AND " +
																					// commonFilter.getToRow() +";";
			condParms += "RELATEDTO=" + relto + ";";
			commonFilter.setRelatedToMchMld(relto);
			if (UIUtils.isValidKeyId(setupAdj))
				condParms += "ACTIVITYTYPE=" + setupAdj + ";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("paramvalues" + paramValues);
			CommonFunctions.debugMsg("DAO IMPL commonFilter.setFromMonth :" + commonFilter.getFromMonth()
					+ " commonFilter.setToMonth:" + commonFilter.getToMonth() + "commonFilter.getRelatedToMchMld()"
					+ commonFilter.getRelatedToMchMld());
			//List<String[]> genMainList = dbActionTemplate.processFunctionCalls(BAL_PlmTlGenmaintenanceSql.getGenMainListSql(), paramValues);
			List<String[]> genMainList = fnCallApi.callFunction("PLM_FN_GENERALMAINTMODIFY_SB", paramValues, 3, true);
			System.out.println("genMainList  :" + genMainList.toString());
			//---------gopi-------
			if (commonFilter.getViewClick() == 'Y') {
			    String totalCnt = paramValues.get(0);
			    boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			    if (isInteger) {
			        commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			    }
			}


			System.out.println("genMainList  :" + genMainList.toString());
			return genMainList;
			// FIX: build date strings before SQL
//			String fromDateStr;
//			String toDateStr;
//			if (commonFilter.getMonwise() != null && 
//			        commonFilter.getMonwise().equals("Y")) {
//			    fromDateStr = "01-" + commonFilter.getFromMonth();
//			    toDateStr   = "30-" + commonFilter.getToMonth();
//			} else {
//			    fromDateStr = commonFilter.getFromDate();
//			    toDateStr   = commonFilter.getToDate();
//			}
//			String directSql = "SELECT " +
//				    "2 AS DATAORDER, " +
//				    "g.GMNT_KEYID AS DOCNO, " +
//				    "COALESCE(g.GMNT_REFDOCID,'-') AS WORKORDERNO, " +
//				    "COALESCE(secm.SECT_NAME,'-') AS SECTION, " +
//				    "COALESCE(cell.CELL_NAME,'-') AS CELL, " +
//				    "COALESCE(mch.MCHM_MACHINENAME,'-') AS MACHINE, " +
//				    "COALESCE(assm.ASSM_NAME,'-') AS STATION, " +
//				    "TO_CHAR(g.GMNT_TARGETDATE,'DD-MON-YYYY') AS REPORTEDON, " +
//				    "COALESCE(sft.SFTM_NAME,'-') AS SHIFT, " +
//				    "COALESCE(emp.EMPM_NAME,'-') AS REPORTEDBY, " +
//				    "COALESCE(trd.TRDM_NAME,'-') AS TRADE, " +
//				    "CASE UPPER(g.GMNT_ACTIVITYTYPE) WHEN 'G' THEN 'General' WHEN 'S' THEN 'Setup And Adjustment' WHEN 'M' THEN 'Machine under Trial' WHEN 'U' THEN 'Unscheduled(Without WO)' WHEN 'O' THEN 'Others' ELSE COALESCE(g.GMNT_ACTIVITYTYPE,'-') END AS ACTIVITYTYPE, " +
//				    "COALESCE(g.GMNT_PROBLEM,'-') AS PROBLEM, " +
//				    "TO_CHAR(g.GMNT_OCCUREDDATE,'DD-MON-YYYY') AS OCCUREDDATE, " +
//				    "TO_CHAR(g.GMNT_RECEIVEDDATE,'DD-MON-YYYY') AS RECEIVEDDATE, " +
//				    "TO_CHAR(g.GMNT_WOSTARTDATE,'DD-MON-YYYY') AS WORKSTARTDATE, " +
//				    "TO_CHAR(g.GMNT_WOENDDATE,'DD-MON-YYYY') AS WORKENDDATE, " +
//				    "COALESCE(CAST(g.GMNT_DOWNTIME AS VARCHAR),'0') AS DOWNTIME, " +
//				    "COALESCE(g.GMNT_ROOTCAUSE,'-') AS ROOTCAUSE, " +
//				    "COALESCE(g.GMNT_ACTION,'-') AS ACTION, " +
//				    "COALESCE(g.GMNT_COUNTERMEASURE,'-') AS COUNTERMEASURE, " +
//				    "CASE g.GMNT_MCHCONDITION WHEN 'R' THEN 'Running' WHEN 'S' THEN 'ShutDown' WHEN 'B' THEN 'Both' ELSE '-' END AS EQUIPMENTCONDITION, " +
//				    "CASE g.GMNT_STATUS WHEN 'P' THEN 'Pending' WHEN 'C' THEN 'Completed' ELSE '-' END AS STATUS " +
//				    "FROM PLM_TL_GENMAINTENANCE g " +
//				    "LEFT JOIN GEN_TL_SECTIONMST secm ON g.GMNT_SECTIONID = secm.SECT_KEYID " +
//				    "LEFT JOIN GEN_TL_CELLMST cell ON g.GMNT_LINEID = cell.CELL_KEYID " +
//				    "LEFT JOIN GEN_TL_MACHINEMST mch ON g.GMNT_MACHINEID = mch.MCHM_KEYID " +
//				    "LEFT JOIN GEN_TL_ASSEMBLYMST assm ON g.GMNT_STATIONID = assm.ASSM_KEYID " +
//				    "LEFT JOIN GEN_TL_EMPLOYEEMST emp ON g.GMNT_REPORTEDBY = emp.EMPM_KEYID " +
//				    "LEFT JOIN GEN_TL_TRADEMST trd ON g.GMNT_TRADE = trd.TRDM_KEYID " +
//				    "LEFT JOIN GEN_TL_SHIFTMST sft ON g.GMNT_SHIFT = sft.SFTM_KEYID " +
//				    "WHERE g.GMNT_ACTIVE = 'Y' " +
//				    "AND g.GMNT_RELATEDTO = '" + relto + "' " +
//				    "AND g.GMNT_OCCUREDDATE::date BETWEEN " +
//				    "TO_DATE('" + fromDateStr + "','DD-MON-YYYY') " +
//				    "AND TO_DATE('" + toDateStr + "','DD-MON-YYYY') " +
//				    "LIMIT 100";
//
//				List<String[]> dataRows = dbActionTemplate.getDataList(directSql);
//
//				// Build response with tablemodel + header + data
//				List<String[]> genMainList = new ArrayList<>();
//				//genMainList.add(new String[]{"0","1","Doc.No","Work Order No","Section","Cell","Machine","Station","Reported On","Shift","Reported By","Trade","Activity Type","Problem","Occured Date","Received Date","Workstart Date","Workend Date","Downtime","Root Cause","Action","Counter Measure","Equipment Condition","Status"});
//				//genMainList.add(new String[]{"0","1","Doc.No","Work Order No","Section","Cell","Machine","Station","Reported On","Shift","Reported By","Trade","Activity Type","Problem","Occured Date","Received Date","Workstart Date","Workend Date","Downtime","Root Cause","Action","Counter Measure","Equipment Condition","Status"});
//				String[] headerRow = new String[]{"0","1","Doc.No","Work Order No","Section","Cell","Machine","Station","Reported On","Shift","Reported By","Trade","Activity Type","Problem","Occured Date","Received Date","Workstart Date","Workend Date","Downtime","Root Cause","Action","Counter Measure","Equipment Condition","Status"};
//				// Line 386 - condition row without "0","1" prefix
//				genMainList.add(0, new String[]{"2","2","Doc.No","Work Order No","Section","Cell","Machine","Station","Reported On","Shift","Reported By","Trade","Activity Type","Problem","Occured Date","Received Date","Workstart Date","Workend Date","Downtime","Root Cause","Action","Counter Measure","Equipment Condition","Status"});				
//				
//				genMainList.add(1, new String[]{"Doc.No","Work Order No","Section","Cell","Machine","Station","Reported On","Shift","Reported By","Trade","Activity Type","Problem","Occured Date","Received Date","Workstart Date","Workend Date","Downtime","Root Cause","Action","Counter Measure","Equipment Condition","Status"});
//				if (dataRows != null) genMainList.addAll(dataRows);
//
//				System.out.println("genMainList  :" + genMainList.toString());
//				return genMainList;
			//-----------------------------------------------------------------------------
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String[]> getSetupAdjSubLoss(String refDocId) {
		try {
			String checkExist = dbActionTemplate.getSingleValue(BAL_PlmTlGenmaintenanceSql.checkExist());
			CommonFunctions.debugMsg(checkExist);
			if (UIUtils.isValidKeyId(checkExist)) {
				if (Integer.parseInt(checkExist) > 0) {
					List<String[]> subLossList = dbActionTemplate
							.getDataList(BAL_PlmTlGenmaintenanceSql.getSubLossGrid(refDocId));
					return subLossList;
				}
			}
			// String getLossParentId =
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}
		return null;
	}

	@Override
	public BAL_PlmTlGenmaintenance getFillValue(String docno) throws Exception {
		// TODO Auto-generated method stub
		BAL_PlmTlGenmaintenance plmTlGenmaintenance = new BAL_PlmTlGenmaintenance();
		String sql = BAL_PlmTlGenmaintenanceSql.getGenMainSql();
		System.out.println("in dao ");
		System.out.println("SQL "+sql);
		Object args[] = new Object[] { docno };
		plmTlGenmaintenance.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		System.out.println(plmTlGenmaintenance.getGmntProblem()+" IN DAO");
		return plmTlGenmaintenance;
	}

	@Override
	public String getShift(List<String> paramValues) {
		try {
			String sql = BAL_BdmTlMstSql.getShiftFunction();
//			String dateTime = CommonFunctions.dateTimeNow();
//			List<String> paramValues =new ArrayList<String>();
//			paramValues.add(FactId);
//			paramValues.add("{}");
//			paramValues.add("{}");
//			paramValues.add(dateTime);
			System.out.println("paramValues   :" + paramValues);
			List<String[]> shift = dbActionTemplate.processFunctionCalls(sql, paramValues);

			return shift.get(0)[0];
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//	public List<String[]> getShift(List<String> paramValues) {
//		// TODO Auto-generated method stub
//		System.out.println("param Values  :"+paramValues);
//		try
//		{			
//			String sql = PlmTlGenmaintenanceSql.getShiftFunction();	
//			
//			List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramValues);	
//			System.out.println("Size : "+fillShift.size());
//			
//			if(fillShift.size() == 0)
//			{
//				paramValues.set(2, "{}");
//				System.out.println(paramValues.get(0)+"-"+paramValues.get(1)+"-"+paramValues.get(2)+"-"+paramValues.get(3));
//				List<String []> fillShift2 = dbActionTemplate.processFunctionCalls( sql,paramValues);	
//				System.out.println("Size : "+fillShift2.size());
//				
//				if(fillShift2.size() == 0)
//				{
//					paramValues.set(1, "{}");
//					System.out.println(paramValues.get(0)+"-"+paramValues.get(1)+"-"+paramValues.get(2)+"-"+paramValues.get(3));
//					List<String []> fillShift3 = dbActionTemplate.processFunctionCalls( sql,paramValues);
//					System.out.println("Size : "+fillShift3.size());
//					return fillShift3;
//				}
//				return fillShift2;
//			}
//			return fillShift;
//			//commonFilter.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
//			//return  commonFilter;
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			
//		}
//		return null;
//	}
	/*
	 * public List<String[]> isMSRExist(WomTlWomst womTlWomst) throws Exception {
	 * String occuredDate = womTlWomst.getWomsWorkenddate();
	 * 
	 * 
	 * if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17) occuredDate =
	 * occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
	 * 
	 * if (occuredDate != null && occuredDate.contains(" ")) { // Expected input:
	 * "22-Jun-2026 00:00" or "22-Jun-2026 00:00:00" etc. // Trim to first 16
	 * characters: "22-Jun-2026 00:00" occuredDate = occuredDate.length() > 16 ?
	 * occuredDate.substring(0, 16) : occuredDate; } List<String> paramVals = new
	 * ArrayList<String>(); paramVals.add(womTlWomst.getWomsKeyid());
	 * paramVals.add(occuredDate); paramVals.add(womTlWomst.getWomsMachineid());
	 * //List<String[]> overlapFlag =
	 * dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROCCURS",
	 * paramVals); List<String[]> overlapFlag =
	 * dbActionTemplate.processFunctionCalls("BDM_FN_ISMSROCCURS", paramVals);
	 * //List<String[]> dataList = fnCallApi.callFunction("BDM_FN_ISMSROCCURS",
	 * paramVals,3,false); return overlapFlag; }
	 */ 
	
	public List<String[]> isMSRExist(WomTlWomst womTlWomst) throws Exception {
	    String occuredDate = womTlWomst.getWomsWorkenddate();
	    System.out.println("RAW occuredDate: [" + occuredDate + "]");

	    // Robustly extract only "DD-MON-YYYY HH:MM" (first 2 space-separated tokens)
	    if (occuredDate != null) {
	        String[] parts = occuredDate.trim().split("\\s+");
	        
	        // parts[0] = "22-Jun-2026", parts[1] = "null" (literal string)
	        String datePart = parts[0]; // always safe
	        String timePart = "00:00";  // default fallback

	        if (parts.length >= 2 
	                && !parts[1].equalsIgnoreCase("null") 
	                && parts[1].matches("\\d{2}:\\d{2}.*")) {
	            timePart = parts[1].substring(0, 5); // take "HH:MM" only
	        }

	        occuredDate = datePart + " " + timePart; // "22-Jun-2026 00:00"
	    }

	    System.out.println("TRIMMED occuredDate: [" + occuredDate + "]");

	    List<String> paramVals = new ArrayList<String>();

	    paramVals.add(womTlWomst.getWomsKeyid());
	    paramVals.add(occuredDate);
	    paramVals.add(womTlWomst.getWomsMachineid());

	    List<String[]> overlapFlag = dbActionTemplate.processFunctionCalls("BDM_FN_ISMSROCCURS", paramVals);
	    return overlapFlag;
	}
	public List<String> insertWorkOrder(BAL_PlmTlGenmaintenance plmTlGenmaintenance, List<String> sqls)
			throws Exception {
		WomTlWomst womTlWomst = new WomTlWomst();
		fillWorkOrderInsert(womTlWomst, plmTlGenmaintenance);

		WomTlWomstSql womTlWomstSql = new WomTlWomstSql(); // contains dbtable,field names, Field types and related sqls
															// of master table

		try {
			womTlWomst.setWomsKeyid(
					dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_BAL_WOM_TL_WOMST, 15, "MW", null, null));// getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST));
																												// //
																												// set
																												// the
																												// sequnce
																												// number
			List<String[]> overlapFlag = isMSRExist(womTlWomst);
			if (overlapFlag.size() > 0) {
				throw new BusinessApplicationExceptions("msrOverlap,");
			}
			plmTlGenmaintenance.setGmntRefdocid(womTlWomst.getWomsKeyid());
			sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return sqls;
	}

	public List<String> updateWorkOrder(BAL_PlmTlGenmaintenance plmTlGenmaintenance, WomTlWomst womTlWomst,
			List<String> sqls) throws Exception {
		// WomTlWomst womTlWomst = new WomTlWomst();
		fillWorkOrder(womTlWomst, plmTlGenmaintenance);

		WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
		try {
			womTlWomst.setWomsKeyid(plmTlGenmaintenance.getGmntRefdocid());
			List<String[]> overlapFlag = isMSRExist(womTlWomst);
			if (overlapFlag.size() > 0) {
				throw new BusinessApplicationExceptions("msrOverlap,");
			}
			// plmTlGenmaintenance.setGmntRefdocid(womTlWomst.getWomsKeyid());
			sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return sqls;
	}

	private void fillWorkOrder(WomTlWomst womTlWomst, BAL_PlmTlGenmaintenance plmTlGenmaintenance) throws Exception {
		// womTlWomst.setWomsActive(plmTlGenmaintenance.getGmntActive());

		// womTlWomst.setWomsCreatedon(plmTlGenmaintenance.getGmntCreatedon());
		womTlWomst.setWomsModifiedon(plmTlGenmaintenance.getGmntModifiedon());

		String reportedDate = Constants.passNullDate;
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntTargetdate())) {
			reportedDate = plmTlGenmaintenance.getGmntTargetdate();
		}
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsLoss()))
			womTlWomst.setWomsLoss("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsMouldid()))
			womTlWomst.setWomsMouldid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsWorkcenterid()))
			womTlWomst.setWomsWorkcenterid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsAlarmno()))
			womTlWomst.setWomsAlarmno("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsSubassemblyid()))
			womTlWomst.setWomsSubassemblyid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsFailuretypeid()))
			womTlWomst.setWomsFailuretypeid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsSpareid()))
			womTlWomst.setWomsSpareid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsPhenomenaid()))
			womTlWomst.setWomsPhenomenaid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsCauseid()))
			womTlWomst.setWomsCauseid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsLocation()))
			womTlWomst.setWomsLocation("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsBookingremarks()))
			womTlWomst.setWomsBookingremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsAcceptedremarks()))
			womTlWomst.setWomsAcceptedremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduleremarks()))
			womTlWomst.setWomsRescheduleremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedremarks()))
			womTlWomst.setWomsAllottedremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduledremarks()))
			womTlWomst.setWomsRescheduledremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsProductionremarks()))
			womTlWomst.setWomsProductionremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRemarks()))
			womTlWomst.setWomsRemarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovremarks()))
			womTlWomst.setWomsRequestapprovremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsSafetypermitid()))
			womTlWomst.setWomsSafetypermitid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsStandbyadditionalinfo()))
			womTlWomst.setWomsStandbyadditionalinfo("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsIntorextequipdesc()))
			womTlWomst.setWomsIntorextequipdesc("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsStandbyremarks()))
			womTlWomst.setWomsStandbyremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRepairremarks()))
			womTlWomst.setWomsRepairremarks("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsSentrepairid()))
			womTlWomst.setWomsSentrepairid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsSentto()))
			womTlWomst.setWomsSentto("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsJobopeningid()))
			womTlWomst.setWomsJobopeningid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsOrderno()))
			womTlWomst.setWomsOrderno("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedsupplier()))
			womTlWomst.setWomsAllottedsupplier("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsNumofactivities()))
			womTlWomst.setWomsNumofactivities("0");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsPwdmwono()))
			womTlWomst.setWomsPwdmwono("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRefdoctype()))
			womTlWomst.setWomsRefdoctype("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRefdocid()))
			womTlWomst.setWomsRefdocid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsProcessId()))
			womTlWomst.setWomsProcessId("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRequiredstart()))
			womTlWomst.setWomsRequiredstart("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsRequiredend()))
			womTlWomst.setWomsRequiredend("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsDepartmentid()))
			womTlWomst.setWomsDepartmentid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsPlannergroup()))
			womTlWomst.setWomsPlannergroup("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsTempfield1()))
			womTlWomst.setWomsTempfield1("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsFlid()))
			womTlWomst.setWomsFlid("{}");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsElementid()))
			womTlWomst.setWomsElementid("{}");

		String costCenter = null;
		String factory = null;
		/*
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMachineid())) {
		 * womTlWomst.setWomsMachineid(plmTlGenmaintenance.getGmntMachineid());
		 * 
		 * } else { womTlWomst.setWomsMachineid("{}"); }
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntLineid())) {
		 * womTlWomst.setWomsCellid(plmTlGenmaintenance.getGmntLineid()); costCenter =
		 * getKeyId(TableNames.TBL_GEN_TL_CELLMST,"CELL_COSTCENTREID","CELL_KEYID",
		 * plmTlGenmaintenance.getGmntLineid()); } else womTlWomst.setWomsCellid("{}");
		 * 
		 * CommonFunctions.debugMsg("Factory : "+factory);
		 * CommonFunctions.debugMsg("costCenter : "+costCenter);
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntFactoryid()))
		 * womTlWomst.setWomsFactoryid(plmTlGenmaintenance.getGmntFactoryid()); else
		 * womTlWomst.setWomsFactoryid("{}");
		 * 
		 * 
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntSectionid()))
		 * womTlWomst.setWomsSectionid(plmTlGenmaintenance.getGmntSectionid()); else
		 * womTlWomst.setWomsSectionid("{}");
		 * 
		 * 
		 * womTlWomst.setWomsWorkcenterid("{}");
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntOccureddate()))
		 * womTlWomst.setWomsOccurreddate(plmTlGenmaintenance.getGmntOccureddate());
		 * else womTlWomst.setWomsOccurreddate("{}");
		 * 
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntShift()))
		 * womTlWomst.setWomsShiftid(plmTlGenmaintenance.getGmntShift()); else
		 * womTlWomst.setWomsShiftid("{}");
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntShiftdate()))
		 * womTlWomst.setWomsShiftdate(plmTlGenmaintenance.getGmntShiftdate()); else
		 * womTlWomst.setWomsShiftdate("{}");
		 * 
		 * 
		 * womTlWomst.setWomsPriority("N");
		 * womTlWomst.setWomsReporteddate(reportedDate);
		 */

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntReportedby())) {
			// womTlWomst.setWomsReportedby(plmTlGenmaintenance.getGmntReportedby());
			womTlWomst.setWomsAcceptedby(plmTlGenmaintenance.getGmntReportedby());
			// womTlWomst.setWomsRescheduleby(plmTlGenmaintenance.getGmntReportedby());
			// womTlWomst.setWomsProductionby(plmTlGenmaintenance.getGmntReportedby());
			womTlWomst.setWomsRequestapprovedby(plmTlGenmaintenance.getGmntReportedby());
		} else {
			// womTlWomst.setWomsReportedby("{}");
			womTlWomst.setWomsAcceptedby("{}");
			womTlWomst.setWomsRequestapprovedby("{}");
		}
		womTlWomst.setWomsRescheduleby("{}");
		womTlWomst.setWomsProductionby("{}");
		// womTlWomst.setWomsProductionstop("X");

		/*
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMchcondition()))
		 * womTlWomst.setWomsMachinecondition(plmTlGenmaintenance.getGmntMchcondition())
		 * ; else womTlWomst.setWomsMachinecondition("{}");
		 * 
		 * womTlWomst.setWomsMachinecondition("X"); womTlWomst.setWomsActivitytype("G");
		 * 
		 * womTlWomst.setWomsAlarmno("{}");
		 * 
		 * 
		 * 
		 * 
		 * womTlWomst.setWomsSubassemblyid("{}");
		 */

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntStationid()))
			womTlWomst.setWomsAssemblyid(plmTlGenmaintenance.getGmntStationid());
		else
			womTlWomst.setWomsAssemblyid("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntPartlocation()))
			womTlWomst.setWomsPartlocation(plmTlGenmaintenance.getGmntPartlocation());
		else
			womTlWomst.setWomsPartlocation("{}");

		/*
		 * womTlWomst.setWomsSpareid("{}"); womTlWomst.setWomsPhenomenaid("{}");
		 * womTlWomst.setWomsCauseid("{}"); womTlWomst.setWomsLocation("{}");
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntProblem()))
		 * womTlWomst.setWomsProblem(plmTlGenmaintenance.getGmntProblem()); else
		 * womTlWomst.setWomsProblem("{}");
		 */

		/*
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntRemarks())) {
		 * womTlWomst.setWomsBookingremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsAcceptedremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsRescheduleremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsAllottedremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsRescheduledremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsProductionremarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsRemarks(plmTlGenmaintenance.getGmntRemarks());
		 * womTlWomst.setWomsRequestapprovremarks(plmTlGenmaintenance.getGmntRemarks());
		 * } else { womTlWomst.setWomsBookingremarks("{}");
		 * womTlWomst.setWomsAcceptedremarks("{}");
		 * womTlWomst.setWomsRescheduleremarks("{}");
		 * womTlWomst.setWomsAllottedremarks("{}");
		 * womTlWomst.setWomsRescheduledremarks("{}");
		 * womTlWomst.setWomsProductionremarks("{}"); womTlWomst.setWomsRemarks("{}");
		 * womTlWomst.setWomsRequestapprovremarks("{}"); }
		 */

		/*
		 * womTlWomst.setWomsAccepteddate(reportedDate);
		 * womTlWomst.setWomsAcceptedflag("Y");
		 * 
		 * womTlWomst.setWomsProductionapproval("N");
		 * womTlWomst.setWomsSafetypermitsrequried("N");
		 * womTlWomst.setWomsSafetypermitid("{}");
		 * 
		 * womTlWomst.setWomsRescheduleflag("A");
		 * womTlWomst.setWomsRescheduledate(reportedDate);
		 * 
		 * womTlWomst.setWomsAllottedflag("N");
		 * womTlWomst.setWomsAllotteddate(reportedDate);
		 * womTlWomst.setWomsProposedstartdate(reportedDate);
		 * womTlWomst.setWomsProposedenddate(Constants.futureNullDate + " "+"00:00");
		 * 
		 * CommonFunctions.debugMsg("Proposed End Date : "+womTlWomst.
		 * getWomsProposedenddate()); womTlWomst.setWomsProposedstflag("N");
		 * womTlWomst.setWomsProposedendflag("N");
		 * 
		 * womTlWomst.setWomsProposeddtacceptflag("X");
		 * womTlWomst.setWomsReschedulestartdate(Constants.passNullDate + " "+"00:00");
		 * womTlWomst.setWomsRescheduleenddate(Constants.futureNullDate + " "+"00:00");
		 * womTlWomst.setWomsRescheduledstflag("N");
		 * womTlWomst.setWomsRescheduledendflag("N");
		 */

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWostartdate())) {
			womTlWomst.setWomsWorkstartdate(plmTlGenmaintenance.getGmntWostartdate());
			womTlWomst.setWomsWorkstartflag("Y");
		} else {
			womTlWomst.setWomsWorkstartdate(reportedDate);
			womTlWomst.setWomsWorkstartflag("N");
		}

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWoenddate())) {
			womTlWomst.setWomsWorkenddate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsWoapprovaldate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsMachinereleaseddate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsWorkendflag("Y");
			womTlWomst.setWomsWoapprovalflag("Y");
			womTlWomst.setWomsMachinereleaseflag("Y");
		} else {
			womTlWomst.setWomsWorkenddate(Constants.futureNullDate);
			womTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
			womTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
			womTlWomst.setWomsWorkendflag("N");
			womTlWomst.setWomsWoapprovalflag("N");
			womTlWomst.setWomsMachinereleaseflag("N");
		}
		/*
		 * womTlWomst.setWomsProductionstartflag("N");
		 * womTlWomst.setWomsProductionstartdate(womTlWomst.getWomsWorkenddate());
		 * womTlWomst.setWomsFinalactivitytype("G");
		 */

		/*
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntKeyid()))
		 * womTlWomst.setWomsActivityid(plmTlGenmaintenance.getGmntKeyid()); else
		 * womTlWomst.setWomsActivityid("{}");
		 */

		/*
		 * womTlWomst.setWomsIntorextequip("N");
		 * womTlWomst.setWomsIntorextequipdesc("{}");
		 * womTlWomst.setWomsStandbyadditionalinfo("{}");
		 * womTlWomst.setWomsStandbyremarks("{}");
		 * womTlWomst.setWomsSentforrepairflag("X");
		 * womTlWomst.setWomsSentrepairid("{}"); womTlWomst.setWomsSentto("{}");
		 * womTlWomst.setWomsExceptedreturndate(Constants.passNullDate);
		 * womTlWomst.setWomsRepairremarks("{}"); womTlWomst.setWomsJobopeningid("{}");
		 * womTlWomst.setWomsOrderno("{}"); womTlWomst.setWomsRequestapproved("A");
		 * womTlWomst.setWomsSafetypermitcompleted("X");
		 * womTlWomst.setWomsSafetypermitapproved("X");
		 * womTlWomst.setWomsSafetypermitsignoff("X");
		 * womTlWomst.setWomsRequestapproveddate(reportedDate);
		 * womTlWomst.setWomsMaintpriority("0"); womTlWomst.setWomsAllottedsource("X");
		 * womTlWomst.setWomsAllottedsupplier("{}"); womTlWomst.setWomsDirectentry("X");
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntRelatedto()))
		 * womTlWomst.setWomsRelatedto(plmTlGenmaintenance.getGmntRelatedto()); else
		 * womTlWomst.setWomsRelatedto("X");
		 * 
		 * womTlWomst.setWomsLoss("{}");
		 * 
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMouldid()))
		 * womTlWomst.setWomsMouldid(plmTlGenmaintenance.getGmntMouldid()); else
		 * womTlWomst.setWomsMouldid("{}");
		 * 
		 * womTlWomst.setWomsTempfield10("{}"); womTlWomst.setWomsTempfield9("{}");
		 * womTlWomst.setWomsTempfield8("{}"); womTlWomst.setWomsTempfield7("{}");
		 * womTlWomst.setWomsTempfield6("{}"); womTlWomst.setWomsTempfield5("{}");
		 * womTlWomst.setWomsTempfield4("{}"); womTlWomst.setWomsTempfield3("{}");
		 * womTlWomst.setWomsTempfield2("{}"); womTlWomst.setWomsTempfield1("{}");
		 */

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCreatedby()))
			womTlWomst.setWomsModifiedby(plmTlGenmaintenance.getGmntCreatedby());
		else
			womTlWomst.setWomsModifiedby("{}");
		/*
		 * if(UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCreatedby()))
		 * womTlWomst.setWomsCreatedby(plmTlGenmaintenance.getGmntCreatedby()); else
		 * womTlWomst.setWomsCreatedby("{}");
		 */
		if (UIUtils.isValidKeyId(costCenter))
			womTlWomst.setWomsCostcenterid(costCenter);
		else
			womTlWomst.setWomsCostcenterid("{}");

		/*
		 * 
		 * womTlWomst.setWomsFailuretypeid("{}");
		 */
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntTrade()))
			womTlWomst.setWomsTradeid(plmTlGenmaintenance.getGmntTrade());
		else
			womTlWomst.setWomsTradeid("{}");
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntStatus())) {
			if (plmTlGenmaintenance.getGmntStatus().equals("P")) {
				womTlWomst.setWomsStatus("L");
				womTlWomst.setWomsFinalstatus("ALLOTTED");
			} else {
				womTlWomst.setWomsStatus(plmTlGenmaintenance.getGmntStatus());
				womTlWomst.setWomsFinalstatus("COMPLETED");
			}
		} else {
			womTlWomst.setWomsStatus("X");
			womTlWomst.setWomsFinalstatus("{}");
		}
		CommonFunctions.debugMsg("Status ABN : " + womTlWomst.getWomsStatus());
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCompletedby())) {
			womTlWomst.setWomsAllottedto(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsDoneby(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsWoapprovalby(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsMachinereleaseby(plmTlGenmaintenance.getGmntCompletedby());
		} else {
			womTlWomst.setWomsAllottedto("{}");
			womTlWomst.setWomsDoneby("{}");
			womTlWomst.setWomsWoapprovalby("{}");
			womTlWomst.setWomsMachinereleaseby("{}");
		}
	}

	private void fillWorkOrderInsert(WomTlWomst womTlWomst, BAL_PlmTlGenmaintenance plmTlGenmaintenance)
			throws Exception {
		womTlWomst.setWomsActive(plmTlGenmaintenance.getGmntActive());

		womTlWomst.setWomsCreatedon(plmTlGenmaintenance.getGmntCreatedon());
		womTlWomst.setWomsModifiedon(plmTlGenmaintenance.getGmntModifiedon());

		String reportedDate = Constants.passNullDate;
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntTargetdate())) {
			reportedDate = plmTlGenmaintenance.getGmntTargetdate();
		}

		String costCenter = null;
		String factory = null;
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMachineid())) {
			womTlWomst.setWomsMachineid(plmTlGenmaintenance.getGmntMachineid());

		} else {
			womTlWomst.setWomsMachineid("{}");
		}

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntLineid())) {
			womTlWomst.setWomsCellid(plmTlGenmaintenance.getGmntLineid());
			costCenter = getKeyId(TableNames.TBL_GEN_TL_CELLMST, "CELL_COSTCENTREID", "CELL_KEYID",
					plmTlGenmaintenance.getGmntLineid());
		} else
			womTlWomst.setWomsCellid("{}");

		CommonFunctions.debugMsg("Factory : " + factory);
		CommonFunctions.debugMsg("costCenter : " + costCenter);
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntFactoryid()))
			womTlWomst.setWomsFactoryid(plmTlGenmaintenance.getGmntFactoryid());
		else
			womTlWomst.setWomsFactoryid("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntSectionid()))
			womTlWomst.setWomsSectionid(plmTlGenmaintenance.getGmntSectionid());
		else
			womTlWomst.setWomsSectionid("{}");

		womTlWomst.setWomsWorkcenterid("{}");
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntOccureddate()))
			womTlWomst.setWomsOccurreddate(plmTlGenmaintenance.getGmntOccureddate());
		else
			womTlWomst.setWomsOccurreddate("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntShift()))
			womTlWomst.setWomsShiftid(plmTlGenmaintenance.getGmntShift());
		else
			womTlWomst.setWomsShiftid("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntShiftdate()))
			womTlWomst.setWomsShiftdate(plmTlGenmaintenance.getGmntShiftdate());
		else
			womTlWomst.setWomsShiftdate("{}");

		womTlWomst.setWomsPriority("N");
		womTlWomst.setWomsReporteddate(reportedDate);

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntReportedby())) {
			womTlWomst.setWomsReportedby(plmTlGenmaintenance.getGmntReportedby());
			womTlWomst.setWomsAcceptedby(plmTlGenmaintenance.getGmntReportedby());
			// womTlWomst.setWomsRescheduleby(plmTlGenmaintenance.getGmntReportedby());
			// womTlWomst.setWomsProductionby(plmTlGenmaintenance.getGmntReportedby());
			womTlWomst.setWomsRequestapprovedby(plmTlGenmaintenance.getGmntReportedby());
		} else {
			womTlWomst.setWomsReportedby("{}");
			womTlWomst.setWomsAcceptedby("{}");
			womTlWomst.setWomsRequestapprovedby("{}");
		}
		womTlWomst.setWomsRescheduleby("{}");
		womTlWomst.setWomsProductionby("{}");
		womTlWomst.setWomsProductionstop("X");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMchcondition()))
			womTlWomst.setWomsMachinecondition(plmTlGenmaintenance.getGmntMchcondition());
		else
			womTlWomst.setWomsMachinecondition("{}");

		womTlWomst.setWomsMachinecondition("X");
		if (!UIUtils.isValidKeyId(womTlWomst.getWomsActivitytype()))
			womTlWomst.setWomsActivitytype("G");

		if (!UIUtils.isValidKeyId(womTlWomst.getWomsFinalactivitytype())) {
			womTlWomst.setWomsFinalactivitytype(womTlWomst.getWomsActivitytype());
		}

		womTlWomst.setWomsAlarmno("{}");

		womTlWomst.setWomsFlid(plmTlGenmaintenance.getGmntFlid());
		womTlWomst.setWomsElementid(plmTlGenmaintenance.getGmntElementid());

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntStationid()))
			womTlWomst.setWomsAssemblyid(plmTlGenmaintenance.getGmntStationid());
		else
			womTlWomst.setWomsAssemblyid("{}");

		womTlWomst.setWomsSubassemblyid("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntPartlocation()))
			womTlWomst.setWomsPartlocation(plmTlGenmaintenance.getGmntPartlocation());
		else
			womTlWomst.setWomsPartlocation("{}");

		womTlWomst.setWomsSpareid("{}");
		womTlWomst.setWomsPhenomenaid("{}");
		womTlWomst.setWomsCauseid("{}");
		womTlWomst.setWomsLocation("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntProblem()))
			womTlWomst.setWomsProblem(plmTlGenmaintenance.getGmntProblem());
		else
			womTlWomst.setWomsProblem("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntRemarks())) {
			womTlWomst.setWomsBookingremarks(plmTlGenmaintenance.getGmntRemarks());
			/*
			 * womTlWomst.setWomsAcceptedremarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsRescheduleremarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsAllottedremarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsRescheduledremarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsProductionremarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsRemarks(plmTlGenmaintenance.getGmntRemarks());
			 * womTlWomst.setWomsRequestapprovremarks(plmTlGenmaintenance.getGmntRemarks());
			 */
		} else {
			womTlWomst.setWomsBookingremarks("{}");

		}

		womTlWomst.setWomsAcceptedremarks("{}");
		womTlWomst.setWomsRescheduleremarks("{}");
		womTlWomst.setWomsAllottedremarks("{}");
		womTlWomst.setWomsRescheduledremarks("{}");
		womTlWomst.setWomsProductionremarks("{}");
		womTlWomst.setWomsRemarks("{}");
		womTlWomst.setWomsRequestapprovremarks("{}");
		womTlWomst.setWomsAccepteddate(reportedDate);
		womTlWomst.setWomsAcceptedflag("Y");

		womTlWomst.setWomsProductionapproval("N");
		womTlWomst.setWomsSafetypermitsrequried("N");
		womTlWomst.setWomsSafetypermitid("{}");

		womTlWomst.setWomsRescheduleflag("A");
		womTlWomst.setWomsRescheduledate(reportedDate);

		womTlWomst.setWomsAllottedflag("N");
		womTlWomst.setWomsAllotteddate(reportedDate);
		womTlWomst.setWomsProposedstartdate(reportedDate);
		womTlWomst.setWomsProposedenddate(Constants.futureNullDate + " " + "00:00");

		CommonFunctions.debugMsg("Proposed End Date : " + womTlWomst.getWomsProposedenddate());
		womTlWomst.setWomsProposedstflag("N");
		womTlWomst.setWomsProposedendflag("N");

		womTlWomst.setWomsProposeddtacceptflag("X");
		womTlWomst.setWomsReschedulestartdate(Constants.passNullDate + " " + "00:00");
		womTlWomst.setWomsRescheduleenddate(Constants.futureNullDate + " " + "00:00");
		womTlWomst.setWomsRescheduledstflag("N");
		womTlWomst.setWomsRescheduledendflag("N");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWostartdate())) {
			womTlWomst.setWomsWorkstartdate(plmTlGenmaintenance.getGmntWostartdate());
			womTlWomst.setWomsWorkstartflag("Y");
		} else {
			womTlWomst.setWomsWorkstartdate(reportedDate);
			womTlWomst.setWomsWorkstartflag("N");
		}

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntWoenddate())) {
			womTlWomst.setWomsWorkenddate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsWoapprovaldate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsMachinereleaseddate(plmTlGenmaintenance.getGmntWoenddate());
			womTlWomst.setWomsWorkendflag("Y");
			womTlWomst.setWomsWoapprovalflag("Y");
			womTlWomst.setWomsMachinereleaseflag("Y");
		} else {
			womTlWomst.setWomsWorkenddate(Constants.futureNullDate);
			womTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
			womTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
			womTlWomst.setWomsWorkendflag("N");
			womTlWomst.setWomsWoapprovalflag("N");
			womTlWomst.setWomsMachinereleaseflag("N");
		}
		womTlWomst.setWomsProductionstartflag("N");
		womTlWomst.setWomsProductionstartdate(womTlWomst.getWomsWorkenddate());
		// womTlWomst.setWomsFinalactivitytype("G");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntKeyid()))
			womTlWomst.setWomsActivityid(plmTlGenmaintenance.getGmntKeyid());
		else
			womTlWomst.setWomsActivityid("{}");

		womTlWomst.setWomsIntorextequip("N");
		womTlWomst.setWomsIntorextequipdesc("{}");
		womTlWomst.setWomsStandbyadditionalinfo("{}");
		womTlWomst.setWomsStandbyremarks("{}");
		womTlWomst.setWomsSentforrepairflag("X");
		womTlWomst.setWomsSentrepairid("{}");
		womTlWomst.setWomsSentto("{}");
		womTlWomst.setWomsExceptedreturndate(Constants.passNullDate);
		womTlWomst.setWomsRepairremarks("{}");
		womTlWomst.setWomsJobopeningid("{}");
		womTlWomst.setWomsOrderno("{}");
		womTlWomst.setWomsRequestapproved("A");
		womTlWomst.setWomsSafetypermitcompleted("X");
		womTlWomst.setWomsSafetypermitapproved("X");
		womTlWomst.setWomsSafetypermitsignoff("X");
		womTlWomst.setWomsRequestapproveddate(reportedDate);
		womTlWomst.setWomsMaintpriority("0");
		womTlWomst.setWomsAllottedsource("X");
		womTlWomst.setWomsAllottedsupplier("{}");
		womTlWomst.setWomsDirectentry("X");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntRelatedto()))
			womTlWomst.setWomsRelatedto(plmTlGenmaintenance.getGmntRelatedto());
		else
			womTlWomst.setWomsRelatedto("X");

		womTlWomst.setWomsLoss("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntMouldid()))
			womTlWomst.setWomsMouldid(plmTlGenmaintenance.getGmntMouldid());
		else
			womTlWomst.setWomsMouldid("{}");

		womTlWomst.setWomsNumofactivities("0");

		womTlWomst.setWomsPwdmwono("{}");
		womTlWomst.setWomsRefdoctype("{}");
		womTlWomst.setWomsRefdocid("{}");
		womTlWomst.setWomsProcessId("{}");
		womTlWomst.setWomsRequiredstart("{}");
		womTlWomst.setWomsRequiredend("{}");
		womTlWomst.setWomsDepartmentid("{}");
		womTlWomst.setWomsPlannergroup("{}");
		womTlWomst.setWomsTempfield1("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCreatedby()))
			womTlWomst.setWomsModifiedby(plmTlGenmaintenance.getGmntCreatedby());
		else
			womTlWomst.setWomsModifiedby("{}");
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCreatedby()))
			womTlWomst.setWomsCreatedby(plmTlGenmaintenance.getGmntCreatedby());
		else
			womTlWomst.setWomsCreatedby("{}");

		CommonFunctions.debugMsg(" costCenter  " + costCenter);
		if (UIUtils.isValidKeyId(costCenter))
			womTlWomst.setWomsCostcenterid(costCenter);
		else
			womTlWomst.setWomsCostcenterid("{}");

		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntTrade()))
			womTlWomst.setWomsTradeid(plmTlGenmaintenance.getGmntTrade());
		else
			womTlWomst.setWomsTradeid("{}");

		womTlWomst.setWomsFailuretypeid("{}");
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntStatus())) {
			if (plmTlGenmaintenance.getGmntStatus().equals("P")) {
				womTlWomst.setWomsStatus("L");
				womTlWomst.setWomsFinalstatus("ALLOTTED");
			} else {
				womTlWomst.setWomsStatus(plmTlGenmaintenance.getGmntStatus());
				womTlWomst.setWomsFinalstatus("COMPLETED");
			}
		} else {
			womTlWomst.setWomsStatus("X");
			womTlWomst.setWomsFinalstatus("{}");
		}
		CommonFunctions.debugMsg("Status ABN : " + womTlWomst.getWomsStatus());
		if (UIUtils.isValidKeyId(plmTlGenmaintenance.getGmntCompletedby())) {
			womTlWomst.setWomsAllottedto(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsDoneby(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsWoapprovalby(plmTlGenmaintenance.getGmntCompletedby());
			womTlWomst.setWomsMachinereleaseby(plmTlGenmaintenance.getGmntCompletedby());
		} else {
			womTlWomst.setWomsAllottedto("{}");
			womTlWomst.setWomsDoneby("{}");
			womTlWomst.setWomsWoapprovalby("{}");
			womTlWomst.setWomsMachinereleaseby("{}");
		}
	}

	public String getKeyId(String tableName, String keyIdCol, String woKeyCol, String woKey) {
		try {
			return dbActionTemplate.getSingleValue(tableName, keyIdCol, woKeyCol, woKey);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Workbook getGenMaintainanaceReport(CommonFilter commonFilter, JSONObject colmodel, String rptFormat,
			String relto) throws Exception {
		ResultSet rs = null;
		try {

			rs = getGenMaintenanceReportResultSet(commonFilter, relto);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();

			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254, 0, 0)); // red font
			condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormat.setFontHeightPoint((short) 8);
			condFormat.setFontBoldWeight((short) 10);
			condFormat.setFromCol(0);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue("Completed");
			condFormat.setSymbolStr("Completed");
			condFormat.setIdentfier("Completed");
			condFormat.setCondFormulaStr("COUNTIF(?," + "Completed" + ")");
			// condFormat.setBgColor(new RGB(192, 255, 192));
			CommonFunctions.debugMsg("condFormat.getCondFormulaStr()" + condFormat.getCondFormulaStr());
			// condFormat.setCondFormulaStr(condFormat.getCondValue());

			condFormats.add(condFormat);

			XLConditionalFormats condFormatPlan = new XLConditionalFormats();
			condFormatPlan.setFontColor(new RGB(254, 0, 0)); // red font
			condFormatPlan.setFontName(XLConditionalFormats.FONT_DEFAULT);
			condFormatPlan.setFontHeightPoint((short) 8);
			condFormatPlan.setFontBoldWeight((short) 10);
			condFormatPlan.setFromCol(1);
			condFormatPlan.setToCol(1);
			condFormatPlan.setOperator(ComparisonOperator.EQUAL);
			condFormatPlan.setCondValue("Pending");
			condFormatPlan.setSymbolStr("Pending");
			// condFormatPlan.setBgColor(new RGB(253, 184, 184));
			condFormatPlan.setIdentfier("Pending");
			condFormats.add(condFormatPlan);

			// CommonFunctions.debugMsg("condFormat.setCondValue"+condFormat.getCondFormulaStr());

			/*
			 * XLConditionalFormats condFormatNA = new XLConditionalFormats();
			 * //condFormatNA.setFontColor(new RGB(254,0,0)); //red font
			 * condFormatNA.setFontName(XLConditionalFormats.FONT_DEFAULT);
			 * condFormatNA.setFontHeightPoint((short)8);
			 * condFormatNA.setFontBoldWeight((short)10); condFormatNA.setFromCol(0);
			 * condFormatNA.setToCol(26);
			 * condFormatNA.setOperator(ComparisonOperator.EQUAL);
			 * condFormatNA.setCondValue(""); condFormatNA.setSymbolStr("");
			 * //condFormatNA.setBgColor(new RGB(192,192,192));
			 * condFormatNA.setIdentfier("Plan"); condFormats.add(condFormatNA);
			 */

			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs, rptFormat, 2, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getGenMaintenanceReportResultSet(CommonFilter commonFilter, String relto) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter, relto);

		//return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_GeneralMaintModify", paramValues);
		return dbActionTemplate.dbFunctionCall("PLM_FN_GENERALMAINTMODIFY", paramValues);

	}

	private List<String> getFilterParamValues(CommonFilter commonFilter, String relto) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick()
																				// +";FROMTOROW="+commonFilter.getFromRow()
																				// +" AND " + commonFilter.getToRow()
																				// +";";
		condParms += "RelTo=" + relto + ";";
		paramValues.add(condParms);
		paramValues.add(commonParams);

		return paramValues;
	}

	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ");
		String selectSql = null;
		String likeSql = null;

		if (comboFilter.getIdField() != null)
			sql.append(comboFilter.getIdField() + " id ");

		if (comboFilter.getCodeField() != null && comboFilter.getNameField() != null) {
			selectSql = "," + comboFilter.getNameField() + "||'-'||" + comboFilter.getCodeField();
		} else if (comboFilter.getNameField() != null) {
			selectSql = "," + comboFilter.getNameField();
		} else if (comboFilter.getCodeField() != null) {
			selectSql = "," + comboFilter.getCodeField();
		}

		sql.append(selectSql + " text ");
		sql.append(" from ");
		sql.append(comboFilter.getTableName());
		sql.append(",(");// + likeSql);

		if (comboFilter.getCondSql() != null)
			sql.append(comboFilter.getCondSql());

		// if( comboFilter.getOrderByField() != null)
		// sql.append(" order by " + comboFilter.getOrderByField() );
		// else
		sql.append(" order by text ");
		ResultSet rs = null;
		Connection connection = null;

		System.out.println(" sql111 " + sql);
		try {
			rs = dbActionTemplate.getData(sql.toString());
			connection = rs.getStatement().getConnection();

			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while (rs.next()) {
				ComboBox compComb = new ComboBox();
				if (!rs.getString("id").equals("0")) {

					compComb.setId(rs.getString("id"));
					compComb.setText(rs.getString("text"));
					comboList.add(compComb);
				}

			}
			ComboBox compComb = new ComboBox();
			compComb.setId("X");
			compComb.setText(" ");
			comboList.add(compComb);
			return comboList;
		} finally {

			DBActionTemplate.closeConnection(rs, null, null, null, connection);

		}
	}

}
