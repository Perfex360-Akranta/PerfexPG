package com.akranta.tpm.dao.impl;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsEntryDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.BAL_PcsJhlossDetailSql;
import com.akranta.tpm.dao.sql.PcsTlAncilliarytimeSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlDtlSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlLosscaptureSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlLosscausemstSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlLossreasonlinkSql;
import com.akranta.tpm.dao.sql.PcsTlLosscausemstSql;
import com.akranta.tpm.dao.sql.PcsTlLossreasonlinkSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlMstSql;
import com.akranta.tpm.dao.sql.PcsTlOperatordtlSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlProductiondataSql;
import com.akranta.tpm.dao.sql.PcsTlWorkorderlinkSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlJhlossDetail;
import com.akranta.tpm.model.BAL_PcsTlLosscapture;
import com.akranta.tpm.model.BAL_PcsTlLosscausemst;
import com.akranta.tpm.model.BAL_PcsTlLossreasonlink;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_PcsEntryDaoImpl implements BAL_PcsEntryDao {

	private DBActionTemplate dbActionTemplate;
	private BAL_PcsTlMstSql pcsTlMstSql = null;
	private BAL_PcsTlDtlSql pcsTlDtlSql = null;
	private PcsTlWorkorderlinkSql pcsTlWorkorderlinkSql = null;
	private BAL_PcsTlLossreasonlinkSql pcsTlLossreasonlinkSql = null;
	private PcsTlAncilliarytimeSql pcsTlAncilliarytimeSql = null;
	private PcsTlOperatordtlSql pcsTlOperatordtlSql = null;
	private BAL_PcsTlProductiondataSql pcsTlProductiondataSql = null;

	public BAL_PcsEntryDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		pcsTlMstSql = new BAL_PcsTlMstSql();
		pcsTlDtlSql = new BAL_PcsTlDtlSql();
		pcsTlWorkorderlinkSql = new PcsTlWorkorderlinkSql();
		pcsTlLossreasonlinkSql = new BAL_PcsTlLossreasonlinkSql();
		pcsTlAncilliarytimeSql = new PcsTlAncilliarytimeSql();
		pcsTlOperatordtlSql = new PcsTlOperatordtlSql();
		pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public String getActTimePct() throws Exception {
		String actTimePct = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE",
				"ACTIMEPCT");
		return actTimePct;
	}

	public String getLossNo(String plcmKeyid) throws Exception {
		String sql = " SELECT PLCM_LOSSNO FROM PCS_VW_LOSSNAMESFORENTRY WHERE KEYID = '" + plcmKeyid + "' ";
		String actTimePct = dbActionTemplate.getSingleValue(sql);
		return actTimePct;
	}

	public List<ComboBox> getManpowerCombo(String cellId, String machineId, String productId, String entryDate,
			String sectId) throws Exception {

		StringBuffer sql = new StringBuffer();
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		CommonFunctions.debugMsg("hourlyEntry" + hourlyEntry);
		String mouldSectionCode = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "MOULD_SECTION_CODES");
		CommonFunctions.debugMsg("mouldSectionCode : " + mouldSectionCode);
		String sectCode = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_SECTIONMST, "SECT_CODE", "SECT_KEYID",
				sectId);
		CommonFunctions.debugMsg("sectCode : " + sectCode);

		if (hourlyEntry.isEmpty() || hourlyEntry == null || hourlyEntry.equals("N")) {
			sql.append(" SELECT DISTINCT CYTM_KEYID AS ID, CYTM_MANPOWER AS TEXT FROM (");
			sql.append(" SELECT CYTM_PRODUCTID,CYTM_KEYID, CYTM_MANPOWER FROM BAL_PCS_TL_CYCLETIMEMST");
			sql.append(" WHERE 1 = 1 ");
			sql.append(" AND CYTM_ACTIVE = 'Y' ");
			sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
			if (!UIUtils.isValidKeyId(machineId))
				machineId = "-";
			sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
			sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
					+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
			sql.append(" UNION       ");
			sql.append(" select CYTH_PRODUCTID, CYTH_KEYID ,CYTH_MANPOWER from BAL_PCS_TL_CYCLETIMEHISTORY");
			sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '" + machineId
					+ "'  ");
			sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '" + entryDate
					+ "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");

			sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
			sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
			sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
			sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
			sql.append(
					" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
			sql.append("  order by text ");

		} else {
			if (UIUtils.isValidKeyId(mouldSectionCode)) {
				if (UIUtils.isValidKeyId(sectCode) && mouldSectionCode.indexOf(sectCode) < 0) {
					sql.append(" SELECT DISTINCT CYTM_KEYID AS ID, CYTM_MANPOWER AS TEXT FROM (");
					sql.append(" SELECT CYTM_PRODUCTID,CYTM_KEYID, CYTM_MANPOWER FROM BAL_PCS_TL_CYCLETIMEMST");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
					if (!UIUtils.isValidKeyId(machineId))
						machineId = "-";
					sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(" UNION       ");
					sql.append(" select CYTH_PRODUCTID, CYTH_KEYID ,CYTH_MANPOWER from BAL_PCS_TL_CYCLETIMEHISTORY");
					sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '"
							+ machineId + "'  ");
					sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");

					sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
					sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
					sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
					sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
					sql.append(
							" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
					sql.append("  order by text ");
					// sql.append(" ) order by text ");
				} else {
					sql.append(" SELECT DISTINCT  NVL(MLDM_NOOFCAVITIES,CYTM_KEYID AS ID, CYTM_MANPOWER AS TEXT ");
					sql.append(" FROM (  ");
					sql.append(" SELECT CYTM_PRODUCTID, CYTM_KEYID, CYTM_MANPOWER FROM BAL_PCS_TL_CYCLETIMEMST ");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					if (!UIUtils.isValidKeyId(machineId))
						machineId = "-";
					sql.append(" AND CYTM_CELLID = '" + cellId + "' AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(
							" UNION    select CYTH_PRODUCTID, CYTH_KEYID ,CYTH_MANPOWER from BAL_PCS_TL_CYCLETIMEHISTORY  ");
					sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '"
							+ machineId + "'  ");
					sql.append(" AND '" + entryDate + "' BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE   ");
					sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
					sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
					sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
					sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
					sql.append(
							" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
					sql.append("  order by text ");
				}
			}
		}

		ResultSet rs = null;
		Connection connection = null;

		System.out.println(" sql111 " + sql);
		try {
			rs = dbActionTemplate.getData(sql.toString());
			connection = rs.getStatement().getConnection();

			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while (rs.next()) {
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				comboList.add(compComb);
			}
			return comboList;
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, connection);
		}
	}

	public List<String[]> getCycletime(String cellId, String machineId, String productId, String entryDate,
			String sectId) throws Exception {

		StringBuffer sql = new StringBuffer();
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		CommonFunctions.debugMsg("hourlyEntry" + hourlyEntry);
		String mouldSectionCode = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "MOULD_SECTION_CODES");
		CommonFunctions.debugMsg("mouldSectionCode : " + mouldSectionCode);
		String sectCode = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_SECTIONMST, "SECT_CODE", "SECT_KEYID",
				sectId);
		CommonFunctions.debugMsg("sectCode : " + sectCode);
		sql.append(" SELECT DISTINCT CYTM_KEYID AS ID , CYTM_CYCLETIME AS TEXT FROM BAL_PCS_TL_CYCLETIMEMST");
		sql.append(" WHERE CYTM_KEYID = '" + productId + "'");
		sql.append(" ORDER BY TEXT");
		/*
		 * if (hourlyEntry.isEmpty() || hourlyEntry == null || hourlyEntry.equals("N"))
		 * { sql.
		 * append(" SELECT DISTINCT CYTM_CAVITY || '##' || PRDM_RAWMATTYPE   || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT FROM ("
		 * ); sql.
		 * append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST"
		 * ); sql.append(" WHERE 1 = 1 "); sql.append(" AND CYTM_ACTIVE = 'Y' ");
		 * sql.append(" AND CYTM_CELLID = '" + cellId + "' "); if( !
		 * UIUtils.isValidKeyId(machineId)) machineId="-";
		 * sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
		 * sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '"+ entryDate +
		 * "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE "); sql.append(" UNION       ");
		 * sql.
		 * append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY"
		 * ); sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId +
		 * "' AND CYTH_MACHINEID = '" + machineId + "'  ");
		 * sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '"+ entryDate +
		 * "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");
		 * 
		 * sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
		 * sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
		 * sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
		 * sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   "); sql.
		 * append(" ), BAL_PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID "
		 * ); sql.append("  order by text ");
		 * 
		 * } else { if(UIUtils.isValidKeyId(mouldSectionCode) ) { if(
		 * UIUtils.isValidKeyId(sectCode) && mouldSectionCode.indexOf(sectCode)<0) {
		 * sql.
		 * append(" SELECT DISTINCT CYTM_CAVITY || '##' || PRDM_RAWMATTYPE  || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT FROM ("
		 * ); sql.
		 * append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST"
		 * ); sql.append(" WHERE 1 = 1 "); sql.append(" AND CYTM_ACTIVE = 'Y' ");
		 * sql.append(" AND CYTM_CELLID = '" + cellId + "' "); if( !
		 * UIUtils.isValidKeyId(machineId)) machineId="-";
		 * sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
		 * sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '"+ entryDate +
		 * "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE "); sql.append(" UNION       ");
		 * sql.
		 * append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY"
		 * ); sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId +
		 * "' AND CYTH_MACHINEID = '" + machineId + "'  ");
		 * sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '"+ entryDate +
		 * "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");
		 * 
		 * sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
		 * sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
		 * sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
		 * sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   "); sql.
		 * append(" ), BAL_PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID "
		 * ); sql.append("  order by text "); //sql.append(" ) order by text "); } else{
		 * sql.
		 * append(" SELECT DISTINCT  NVL(MLDM_NOOFCAVITIES,CYTM_CAVITY) || '##' || PRDM_RAWMATTYPE || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT "
		 * ); sql.append(" FROM (  "); sql.
		 * append(" SELECT CYTM_PRODUCTID, CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST "
		 * ); sql.append(" WHERE 1 = 1 "); sql.append(" AND CYTM_ACTIVE = 'Y' "); if( !
		 * UIUtils.isValidKeyId(machineId)) machineId="-";
		 * sql.append(" AND CYTM_CELLID = '" + cellId + "' AND CYTM_MACHINEID = '" +
		 * machineId + "'  "); sql.append(" AND CYTM_PRODUCTID = '" + productId +
		 * "'  AND '"+ entryDate + "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE "); sql.
		 * append(" UNION    select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY  "
		 * ); sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId +
		 * "' AND CYTH_MACHINEID = '" + machineId + "'  "); sql.append(" AND '"+
		 * entryDate + "' BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE   ");
		 * sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
		 * sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
		 * sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
		 * sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   "); sql.
		 * append(" ), BAL_PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID "
		 * ); sql.append("  order by text "); } } }
		 */

		ResultSet rs = null;
		Connection connection = null;

		System.out.println(" sql111 " + sql);
		try {
			List<String[]> cycletime = dbActionTemplate.getDataList(sql.toString());
			return cycletime;
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, connection);
		}
	}

	public List<ComboBox> getCycletimeCombo(String cellId, String machineId, String productId, String entryDate,
			String sectId) throws Exception {

		StringBuffer sql = new StringBuffer();
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		String mouldSectionCode = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "MOULD_SECTION_CODES");
		String sectCode = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_SECTIONMST, "SECT_CODE", "SECT_KEYID",
				sectId);
		CommonFunctions.debugMsg("hourlyEntry" + hourlyEntry);
		CommonFunctions.debugMsg("mouldSectionCode : " + mouldSectionCode);
		CommonFunctions.debugMsg("sectCode : " + sectCode);

		if (hourlyEntry.isEmpty() || hourlyEntry == null || hourlyEntry.equals("N")) {
			sql.append(
					" SELECT DISTINCT CYTM_CAVITY || '##' || PRDM_RAWMATTYPE   || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT FROM (");
			sql.append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM BAL_PCS_TL_CYCLETIMEMST");
			sql.append(" WHERE 1 = 1 ");
			sql.append(" AND CYTM_ACTIVE = 'Y' ");
			sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
			if (!UIUtils.isValidKeyId(machineId))
				machineId = "-";
			sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
			sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
					+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
			sql.append(" UNION       ");
			sql.append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from BAL_PCS_TL_CYCLETIMEHISTORY");
			sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '" + machineId
					+ "'  ");
			sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '" + entryDate
					+ "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");

			sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
			sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
			sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
			sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
			sql.append(
					" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
			sql.append("  order by text ");

		} else {
			if (UIUtils.isValidKeyId(mouldSectionCode)) {
				if (UIUtils.isValidKeyId(sectCode) && mouldSectionCode.indexOf(sectCode) < 0) {
					sql.append(
							" SELECT DISTINCT CYTM_CAVITY || '##' || PRDM_RAWMATTYPE  || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT FROM (");
					sql.append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM BAL_PCS_TL_CYCLETIMEMST");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
					if (!UIUtils.isValidKeyId(machineId))
						machineId = "-";
					sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(" UNION       ");
					sql.append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from BAL_PCS_TL_CYCLETIMEHISTORY");
					sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '"
							+ machineId + "'  ");
					sql.append(" AND CYTH_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE ");

					sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
					sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
					sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
					sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
					sql.append(
							" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
					sql.append("  order by text ");
					// sql.append(" ) order by text ");
				} else {
					sql.append(
							" SELECT DISTINCT  NVL(MLDM_NOOFCAVITIES,CYTM_CAVITY) || '##' || PRDM_RAWMATTYPE || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT ");
					sql.append(" FROM (  ");
					sql.append(" SELECT CYTM_PRODUCTID, CYTM_CAVITY, CYTM_CYCLETIME FROM BAL_PCS_TL_CYCLETIMEMST ");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					if (!UIUtils.isValidKeyId(machineId))
						machineId = "-";
					sql.append(" AND CYTM_CELLID = '" + cellId + "' AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(
							" UNION    select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from BAL_PCS_TL_CYCLETIMEHISTORY  ");
					sql.append(" WHERE CYTH_ACTIVE = 'Y'  AND CYTH_CELLID = '" + cellId + "' AND CYTH_MACHINEID = '"
							+ machineId + "'  ");
					sql.append(" AND '" + entryDate + "' BETWEEN CYTH_FROMDATE  AND CYTH_TILLDATE   ");
					sql.append(" ) , (SELECT MLPL_PRODUCTID, MLDM_NOOFCAVITIES ");
					sql.append(" FROM GEN_TL_MOULDPRODLINK, GEN_TL_MOULDMST ");
					sql.append(" WHERE MLDM_MOULDID = MLPL_MOULDID  ");
					sql.append(" AND MLPL_PRODUCTID = '" + productId + "'   ");
					sql.append(
							" ), PCS_TL_PRODUCTMST WHERE MLPL_PRODUCTID (+) = CYTM_PRODUCTID  AND PRDM_KEYID = CYTM_PRODUCTID ");
					sql.append("  order by text ");
				}
			}
		}

		ResultSet rs = null;
		Connection connection = null;

		System.out.println(" sql111 " + sql);
		try {
			rs = dbActionTemplate.getData(sql.toString());
			connection = rs.getStatement().getConnection();

			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while (rs.next()) {
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				comboList.add(compComb);
			}
			return comboList;
		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, connection);
		}
	}

	public List<String[]> getPCSCalendar(String factId, String date, String userId, String sectionId, String cellId)
			throws Exception {
		CommonFunctions.debugMsg("Inside getParameters Dao Impl..........");
		try {
			String firstDate = date;
			if (CommonFunctions.isValidKeyId(date))
				firstDate = "01" + date.substring(date.indexOf("-"));
			String lastDay = CommonFunctions.getLastDayOfMonth(date);
			lastDay = lastDay.substring(0, lastDay.indexOf("-"));
			String detailTable = getDetailTableName(cellId, date);
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT TO_CHAR(TEMPDATE, 'DD-Mon-YYYY') AS TEMPDATE,");
			sql.append("MAX(DECODE(SFTM_SHIFTORDER, 1, COMPFLAG)) AS SFT1,");
			sql.append("MAX(DECODE(SFTM_SHIFTORDER, 2, COMPFLAG)) AS SFT2,");
			sql.append("MAX(DECODE(SFTM_SHIFTORDER, 3, COMPFLAG)) AS SFT3,");
			sql.append("FLAG AS TOTAL,ORDERDT FROM");
			sql.append("(SELECT TEMPDATE,FLAG,SFTM_KEYID,SFTM_SHIFTORDER,");
			sql.append("DECODE(TO_CHAR(TEMPDATE,'YYYYMMDD'), TO_CHAR(PRLM_ENTRYDATE, 'YYYYMMDD'),");
			sql.append(
					"DECODE(SFTM_KEYID, SFTMKEYID, PDE_COMPLETEDFLAG)) AS COMPFLAG,TO_CHAR(TEMPDATE, 'YYYYMMDD') AS ORDERDT");
			sql.append(" FROM(SELECT TEMPDATE,FLAG,SFTM_KEYID,SFTM_SHIFTORDER FROM GEN_TL_SHIFTMST,");
			sql.append(
					"(SELECT TEMPDATE,DECODE(TO_CHAR(HOLM_DATE,'YYYYMMDD'), TO_CHAR(TEMPDATE,'YYYYMMDD'), HOLM_HOLIDAYFLAG) AS FLAG,");
			sql.append("TO_CHAR(TEMPDATE, 'YYYYMMDD') AS ORDERDT  FROM GEN_TL_HOLIDAYMST,");
			// sql.append("(SELECT TO_DATE('" + firstDate + "') + ROWNUM-1 AS TEMPDATE FROM
			// TAB WHERE ROWNUM < = "
			// + lastDay + ")");
			sql.append("(SELECT TO_DATE('" + firstDate + "','DD-MM-YYYY') + (n-1) AS TEMPDATE FROM generate_series(1, "
					+ lastDay + ") AS s(n))");
			sql.append(" WHERE HOLM_DATE(+)  = TEMPDATE  ");
			// sql.append(" AND HOLM_FACTORY(+) ='"+factId+"' ");
			sql.append(" )");
			// sql.append(" WHERE SFTM_FACTORYID = '"+factId+"' AND SFTM_SHIFTORDER <=
			// 3),");
			sql.append(" WHERE SFTM_SHIFTORDER <= 3),");
			sql.append("(SELECT DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,");
			sql.append("SFTM_KEYID SFTMKEYID,PRLM_ENTRYDATE FROM BAL_PCS_TL_MST,GEN_TL_SHIFTMST,");
			sql.append("(SELECT DISTINCT PLMASTERID  FROM " + detailTable + " WHERE ACTIVE = 'Y'");
			sql.append(" AND (NOPLANINMINS       > 0 OR NVL(PRODUCTID,'{}') <> '{}')) PLD");
			sql.append(" WHERE TO_CHAR(PRLM_ENTRYDATE,'YYYYMM') = TO_CHAR(to_date('" + date + "'),'YYYYMM')");
			sql.append(" AND PRLM_SHIFTID = SFTM_KEYID AND PLD.PLMASTERID = PRLM_KEYID");
			sql.append(" AND PRLM_CELLID = '" + cellId + "' AND PRLM_LOGTYPE= 'S'");
			sql.append(" AND PRLM_TIMEORQTY = 'T' GROUP BY DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),");
			sql.append("SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE)");
			sql.append(" WHERE PRLM_ENTRYDATE(+) = TEMPDATE  AND SFTMKEYID(+)        = SFTM_KEYID");
			sql.append(" AND TEMPDATE           <= SYSDATE) GROUP BY TO_CHAR(TEMPDATE, 'DD-Mon-YYYY'),FLAG,ORDERDT");
			sql.append(
					" UNION SELECT 'DATE' AS TEMPDATE,MAX(DECODE(SFTM_SHIFTORDER, 1, SFTM_KEYID)) AS SFT1,MAX(DECODE(SFTM_SHIFTORDER, 2, SFTM_KEYID)) AS SFT2,MAX(DECODE(SFTM_SHIFTORDER, 3, SFTM_KEYID)) AS SFT3,'TOTAL' AS TOTAL, '21001231'");
			sql.append(" FROM (SELECT SFTM_KEYID,SFTM_SHIFTORDER FROM GEN_TL_SHIFTMST  ");
			sql.append(" WHERE 1 = 1 ");
			// sql.append(" AND SFTM_FACTORYID = '"+factId+"' ");
			sql.append(" AND SFTM_SHIFTORDER<=3 AND SFTM_ACTIVE ='Y') ORDER BY ORDERDT DESC");

			CommonFunctions.debugMsg("Str	ing sql=" + sql.toString());

			List<String[]> monthList = dbActionTemplate.getDataList(sql.toString());

			return monthList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in CALENDAR dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getCalendar(String factId, String date, String userId) throws Exception {
		CommonFunctions.debugMsg("Inside getParameters Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(date);
			paramValues.add(userId);
			CommonFunctions.debugMsg("date" + date);

			/*
			 * dbActionTemplate.processPLSQLProcedures("PCS_CALENDAR", paramValues, null);
			 * String sql2 =""; sql2 = "SELECT * FROM PCS_CALENDER WHERE USERID = '" +
			 * userId + "'" ; List<String[]> calList = dbActionTemplate.getDataList(sql2);
			 * return calList;
			 */

			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			/*
			 * sql1.append( " SELECT to_char(TRUNC (to_date('" + date +
			 * "'), 'MON')+rownum-1,'DD-Mon-YYYY') AS DATE1, " ); sql1.append(
			 * " (SELECT SFTM_CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_FACTORYID = '" + factId
			 * + "' AND SFTM_SHIFTORDER=1) AS SHIFT_1, "); sql1.append(
			 * " (SELECT SFTM_CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_FACTORYID = '" + factId
			 * + "' AND SFTM_SHIFTORDER=2) AS SHIFT_2, "); sql1.append(
			 * " (SELECT SFTM_CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_FACTORYID = '" + factId
			 * + "' AND SFTM_SHIFTORDER=3) AS SHIFT_3 ");
			 * 
			 * // "'1' AS SHIFT_1, '2' AS SHIFT_2, '3' AS SHIFT_3 "); sql1.append(
			 * " FROM DUAL " ); sql1.append( " where to_char(to_date('" + date +
			 * "'),'MON')=to_char(TRUNC (to_date('" + date + "'), 'MON')+rownum-1,'MON') ");
			 * sql1.append( " CONNECT BY LEVEL <= 31 " );
			 */

			sql1.append("  SELECT to_char(DATE1,'DD-Mon-YYYY') AS DATE1, SHIFT_1, SHIFT_2, SHIFT_3,TOTAL FROM ( ");
			// sql1.append( " SELECT TRUNC(TRUNC (to_date('" + date + "'), 'MON')-1) AS
			// DATE1, " );
			sql1.append(" 	SELECT TRUNC(TRUNC (to_date('" + date + "'), 'MON')+32) AS DATE1, ");
			// sql1.append( " (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID =
			// '" + factId + "' AND SFTM_SHIFTORDER=1 AND SFTM_ACTIVE='Y') AS SHIFT_1, " );
			// sql1.append( " (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID =
			// '" + factId + "' AND SFTM_SHIFTORDER=2 AND SFTM_ACTIVE='Y') AS SHIFT_2, " );
			// sql1.append( " (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID =
			// '" + factId + "' AND SFTM_SHIFTORDER=3 AND SFTM_ACTIVE='Y') AS SHIFT_3 " );
			sql1.append(
					" (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=1 AND SFTM_ACTIVE='Y') AS SHIFT_1, ");
			sql1.append(
					" (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=2 AND SFTM_ACTIVE='Y') AS SHIFT_2, ");
			sql1.append(
					" (SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=3 AND SFTM_ACTIVE='Y') AS SHIFT_3 ");
			sql1.append(" ,'TOTAL' AS TOTAL ");
			sql1.append(" FROM DUAL ");
			sql1.append(" UNION  ");
			sql1.append(" SELECT TRUNC(TRUNC (to_date('" + date + "'), 'MON')+rownum-1) AS DATE1, ");
			// sql1.append( " (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID
			// = '" + factId + "' AND SFTM_SHIFTORDER=1 AND SFTM_ACTIVE='Y') AS SHIFT_1, "
			// );
			// sql1.append( " (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID
			// = '" + factId + "' AND SFTM_SHIFTORDER=2 AND SFTM_ACTIVE='Y') AS SHIFT_2, "
			// );
			// sql1.append( " (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST WHERE SFTM_FACTORYID
			// = '" + factId + "' AND SFTM_SHIFTORDER=3 AND SFTM_ACTIVE='Y') AS SHIFT_3 " );
			sql1.append(
					" (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=1 AND SFTM_ACTIVE='Y') AS SHIFT_1, ");
			sql1.append(
					" (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=2 AND SFTM_ACTIVE='Y') AS SHIFT_2,  ");
			sql1.append(
					" (SELECT '-' AS CODE FROM GEN_TL_SHIFTMST  WHERE SFTM_SHIFTORDER=3 AND SFTM_ACTIVE='Y') AS SHIFT_3 ");
			sql1.append(" ,(SELECT '-' AS CODE FROM DUAL ) AS TOTAL ");
			sql1.append(" FROM DUAL  ");
			sql1.append(" where to_char(to_date('" + date + "'),'MON')=to_char(TRUNC (to_date('" + date
					+ "'), 'MON')+rownum-1,'MON') ");
			sql1.append(" AND TRUNC(TRUNC (to_date('" + date + "'), 'MON')+rownum-1) <= SYSDATE   ");
			sql1.append(" CONNECT BY LEVEL <= 31 ");
			sql1.append(" ) ORDER BY to_date(date1)  DESC ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			List<String[]> monthList = dbActionTemplate.getDataList(sql);

			return monthList;

		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in CALENDAR dao impl" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getParameters(String sectId) throws Exception {
		CommonFunctions.debugMsg("Inside getParameters Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			// paramValues.add(cellId);
			CommonFunctions.debugMsg("sectId==" + sectId);
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			// sql1.append(" SELECT ROWNUM AS RNO,A.* FROM ( ");
			/*
			 * sql1.append(
			 * " SELECT PLCM_KEYID AS KEYID, PLCM_MAPFIELD, PLCM_MAPCOLUMNNO, PLCM_SHOWPOPUP ,'' AS RES1,'' AS RES2,'' AS RES3,"
			 * ); sql1.
			 * append(" PLCM_KEYID AS LOSSID,PLCM_ISPARENT AS HEADING,PLCM_PARAMETERCODE AS LOSSPARAM,     "
			 * ); sql1.append(
			 * " DECODE(PLCM_SHOWLOSSNO,'Y',PLCM_LOSSNO,'') AS LNO, PLCM_PARAMETERNAME AS PARAMETERS, INITCAP(UOMM_CODE) AS UOM,     "
			 * ); sql1.append(
			 * " PLCM_PARENTID, PLCM_ENTRYTYPE,  PLCM_DATATYPE, PLCM_MAXVALUE,  PLCM_SHOWLOSSNO, PLCM_ROWCOLOR,PLCM_ISHIDDEN,  PLCM_ORDER,PLCM_ISLOSS "
			 * ); sql1.append(
			 * " FROM    PCS_TL_LOGCONFIGURATION,ADM_TL_UOMMST,PCS_TL_LOSSCELLLINK WHERE    PLCM_UOM = UOMM_KEYID (+)  "
			 * );
			 * sql1.append(" AND PLFL_PAsRAMETERID = plcm_keyid  AND PLFL_ACTIVE = 'Y'  ");
			 * 
			 * sql1.append(" AND  PLCM_ISHIDDEN <> 'Y' ");
			 * 
			 * sql1.
			 * append(" AND PLFL_CELLID  IN ( SELECT MAX(CELL_KEYID) FROM GEN_TL_CELLMST, PCS_TL_LOSSCELLLINK "
			 * ); sql1.append("  WHERE PLFL_CELLID = CELL_KEYID AND CELL_SECTIONID='" +
			 * sectId + "') ");
			 * 
			 * // sql1.append(" AND PLFL_CELLID = '" + cellId + "' ");
			 * 
			 * sql1.append(" ORDER BY PLCM_ORDER,PLCM_LOSSNO "); // sql1.append(" ) A ");
			 */
			sql1.append(
				    " SELECT PLCM_KEYID AS KEYID, PLCM_MAPFIELD, PLCM_MAPCOLUMNNO, " +
				    " PLCM_SHOWPOPUP, '' AS RES1, '' AS RES2, '' AS RES3, "
				);

				sql1.append(
				    " PLCM_KEYID AS LOSSID, PLCM_ISPARENT AS HEADING, " +
				    " PLCM_PARAMETERCODE AS LOSSPARAM, "
				);

				sql1.append(
				    " CASE WHEN PLCM_SHOWLOSSNO = 'Y' " +
				    " THEN PLCM_LOSSNO::TEXT ELSE '' END AS LNO, " +
				    " PLCM_PARAMETERNAME AS PARAMETERS, INITCAP(UOMM_CODE) AS UOM, "
				);

				sql1.append(
				    " PLCM_PARENTID, PLCM_ENTRYTYPE, PLCM_DATATYPE, PLCM_MAXVALUE, " +
				    " PLCM_SHOWLOSSNO, PLCM_ROWCOLOR, PLCM_ISHIDDEN, " +
				    " PLCM_ORDER, PLCM_ISLOSS "
				);

				sql1.append(
				    " FROM PCS_TL_LOGCONFIGURATION L " +
				    " LEFT JOIN ADM_TL_UOMMST U ON L.PLCM_UOM = U.UOMM_KEYID " +
				    " JOIN PCS_TL_LOSSCELLLINK C ON C.PLFL_PARAMETERID = L.PLCM_KEYID "
				);

				sql1.append(
				    " WHERE C.PLFL_ACTIVE = 'Y' " +
				    " AND L.PLCM_ISHIDDEN <> 'Y' "
				);

				sql1.append(
				    " AND C.PLFL_CELLID IN ( " +
				    " SELECT MAX(CELL_KEYID) " +
				    " FROM GEN_TL_CELLMST G " +
				    " JOIN PCS_TL_LOSSCELLLINK C2 ON C2.PLFL_CELLID = G.CELL_KEYID " +
				    " WHERE G.CELL_SECTIONID = '" + sectId + "' " +
				    " ) "
				);

				sql1.append(
				    " ORDER BY L.PLCM_ORDER, L.PLCM_LOSSNO "
				);
			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			// List<String[]> studentList =
			// dbActionTemplate.getDataList(sql);//getDataList(sql);
			// List<String[]> pcsList = dbActionTemplate.getDataListWithColHeader(sql,
			// paramValues);
			List<String[]> pcsList = dbActionTemplate.getDataListWithColHeader(sql, null);
			return pcsList;

		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getParameters dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getProdLoss(String shiftId, String entryDate, String sectId, String cellId, String mchId,
			String factId) throws Exception {
		CommonFunctions.debugMsg("Inside getParameters Dao Impl.........." + sectId);
		try {
			List<String> params = new ArrayList<String>();
			params.add(shiftId.trim());
			params.add(entryDate);
			params.add(sectId);
			List<String[]> lossList;
			int cnt = 1;
			if (UIUtils.isValidKeyId(factId)) {
				String count = getShiftCount(factId, entryDate, shiftId);
				cnt = Integer.parseInt(count);
			}

			// String detailTableName = "PCS_TL_" +
			// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
			// "SECT_KEYID", sectId);
			// detailTableName = detailTableName.trim().replaceAll("-", "#");
			if (cnt > 0) {
				String detailTableName = getDetailTableName(cellId, entryDate);

				CommonFunctions.debugMsg("detailTableName==" + detailTableName);

				CommonFunctions.debugMsg("sectId==" + sectId);
				// String sql = PcsTlMstSql.getProdLossSql(shiftId,entryDate,cellId, mchId,
				// detailTableName);
				String sql = BAL_PcsTlMstSql.getProdLossSql(shiftId, entryDate, sectId, cellId, mchId, detailTableName);

				CommonFunctions.debugMsg("ssql===" + sql);
				lossList = dbActionTemplate.getDataListWithColHeader(sql, params);
				return lossList;
			} else if (cnt == 0)
				throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunctions.debugMsg("Exception in getProdLoss dao impl--" + e.getMessage());
			throw new Exception();
		}

		return null;

	}

	public List<String[]> getProductDetail(String shiftId, String entryDate, String cellId, String sectionId,
			String mchId, String dtlId) throws Exception {
		try {
			List<String> params = new ArrayList<String>();
			if (UIUtils.isValidKeyId(shiftId))
				params.add(shiftId.trim());
			if (UIUtils.isValidKeyId(entryDate))
				params.add(entryDate);
			if (UIUtils.isValidKeyId(cellId))
				params.add(cellId);
			if (UIUtils.isValidKeyId(dtlId))
				params.add(dtlId.trim());
			String detailTableName = getDetailTableName(cellId, entryDate);
			String sql = BAL_PcsTlMstSql.getProductDetailSql(shiftId, entryDate, cellId, mchId, detailTableName, dtlId);
			List<String[]> lossList = dbActionTemplate.getDataListWithColHeader(sql, params);
			return lossList;
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunctions.debugMsg("Exception in getProdLoss dao impl--" + e.getMessage());
			throw new Exception();
		}

	}

	public List<String[]> getEmployeeGrid(String pldetailsId) {
		CommonFunctions.debugMsg("Inside entry grid Dao Impl..........");
		try {
			CommonFunctions.debugMsg("pldetailsId===" + pldetailsId);

			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(" SELECT POPD_PLDETAILSID, POPD_PLEMPLOYEEID, EMPM_CODE || '-' ||EMPM_NAME  ");
			sql1.append(" FROM PCS_TL_OPERATORDTL, GEN_TL_EMPLOYEEMST  ");
			sql1.append(" WHERE POPD_PLDETAILSID =  '" + pldetailsId + "' ");
			sql1.append(" AND POPD_PLEMPLOYEEID = EMPM_KEYID  ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("getEmployeeGrid sql=" + sql);
			List<String[]> empList = dbActionTemplate.getDataList(sql);
			return empList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getEmployeeGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getEntryGrid(String date, String shift, String sectId, String mchId) {
		CommonFunctions.debugMsg("Inside entry grid Dao Impl..........");
		try {
			CommonFunctions.debugMsg("section id ====" + sectId);

			// String detailTableName = "PCS_TL_" +
			// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
			// "SECT_KEYID", sectId);
			// detailTableName = detailTableName.trim().replaceAll("-", "#");

			String detailTableName = getDetailTableName(sectId, date);

			CommonFunctions.debugMsg("detailTableName===" + detailTableName);

			StringBuffer sql1 = new StringBuffer();
			String sql = "";
//			sql1.append(" SELECT  PLDETAILSID, PTWO_KEYID, PRDM_MODEL, PRDM_KEYID, PRDM_NAME, PTWO_WNO, PTWO_CALENDARTIME, PTWO_NOPLANINMINS, PTWO_PLANNEDQTY,  ");
//			sql1.append(" THEORITICALCYCLETIME, ACTUALCYCLETIME, CAVITYAVAILABLE, CAVITYUSED, PTWO_PRODUCEDQTY, REMARKS,  '' AS LOSSLINK  ");
//			sql1.append(" FROM BAL_PCS_TL_MST , PCS_TL_WORKORDERLINK, " + detailTableName + " , BAL_PCS_TL_PRODUCTMST   ");  
//			sql1.append(" WHERE PRLM_KEYID = PLMASTERID AND PRODUCTID = PRDM_KEYID  ");
//			sql1.append(" AND PTWO_PLDETAILID = PLDETAILSID ");
//			sql1.append(" AND TRUNC(PRLM_ENTRYDATE) = '" + date + "' AND PRLM_SHIFTID = '" + shift + "'   "); 
//			sql1.append(" AND MACHINEID = '" + mchId + "'  " );

			/*
			 * sql1.append(
			 * " SELECT  DISTINCT DECODE(PLDETAILSID,PLRK_PLDETAILID, 1,0) AS TICK,PLDETAILSID, '' PTWO_KEYID, PRDM_MODEL, "
			 * ); sql1.append(
			 * " PRDM_KEYID, PRDM_NAME || '-' || PRDM_DRAWINGNO as PRDM_NAME , WNO, CALENDARTIME, NOPLANINMINS, PLANNEDQTY,  "
			 * ); sql1.append(
			 * " THEORITICALCYCLETIME, ACTUALCYCLETIME, CAVITYAVAILABLE, CAVITYUSED, PRODUCEDQTY, TRIMMINGQTY, EXPANSIONQTY,BACKLOGQTY,'' AS LOSSLINK  "
			 * ); sql1.append("  , RAWMATERIALTYPE, WEIGHT , REMARKS"); sql1.append(
			 * " FROM BAL_PCS_TL_MST , " + detailTableName +
			 * " , BAL_PCS_TL_PRODUCTMST,PCS_TL_LOSSREASONLINK   "); sql1.append(
			 * " WHERE PRLM_KEYID = PLMASTERID AND PRODUCTID = PRDM_KEYID (+) AND PLDETAILSID = PLRK_PLDETAILID(+) "
			 * ); sql1.append(" AND TRUNC(PRLM_ENTRYDATE) = '" + date +
			 * "' AND PRLM_SHIFTID = '" + shift + "'   "); sql1.append(" AND MACHINEID = '"
			 * + mchId + "' ORDER BY PLDETAILSID DESC");
			 */
			
			sql1.append(
				    " SELECT DISTINCT " +
				    " CASE WHEN PLDETAILSID = PLRK_PLDETAILID THEN 1 ELSE 0 END AS TICK, " +
				    " PLDETAILSID, '' AS PTWO_KEYID, PRDM_MODEL, "
				);

				sql1.append(
				    " PRDM_KEYID, PRDM_NAME || '-' || PRDM_DRAWINGNO AS PRDM_NAME, " +
				    " WNO, CALENDARTIME, NOPLANINMINS, PLANNEDQTY, "
				);

				sql1.append(
				    " THEORITICALCYCLETIME, ACTUALCYCLETIME, CAVITYAVAILABLE, " +
				    " CAVITYUSED, PRODUCEDQTY, TRIMMINGQTY, EXPANSIONQTY, BACKLOGQTY, " +
				    " '' AS LOSSLINK "
				);

				sql1.append(
				    " , RAWMATERIALTYPE, WEIGHT, REMARKS "
				);

				sql1.append(
				    " FROM BAL_PCS_TL_MST M " +
				    " CROSS JOIN " + detailTableName + " D " +
				    " LEFT JOIN PCS_TL_PRODUCTMST P ON PRODUCTID = PRDM_KEYID " +
				    " LEFT JOIN PCS_TL_LOSSREASONLINK L ON PLDETAILSID = PLRK_PLDETAILID "
				);

				sql1.append(
				    " WHERE PRLM_KEYID = PLMASTERID " +
				    " AND PRLM_ENTRYDATE >= CAST('" + date + "' AS DATE) " +
				    " AND PRLM_ENTRYDATE < CAST('" + date + "' AS DATE) + INTERVAL '1 day' " +
				    " AND PRLM_SHIFTID = '" + shift + "' "
				);

				sql1.append(
				    " AND MACHINEID = '" + mchId + "' " +
				    " ORDER BY PLDETAILSID DESC "
				);
			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	Entry Grid sql=" + sql);
			List<String[]> entryList = dbActionTemplate.getDataList(sql);
			return entryList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getEntryGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> checkIsQtyLoss(String lossId) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(" SELECT DECODE(UOMM_CODE,'NOS','Y','N') FROM PCS_TL_LOGCONFIGURATION, ADM_TL_UOMMST ");
		sql1.append(" WHERE PLCM_UOM = UOMM_KEYID AND PLCM_KEYID ='" + lossId + "' ");
		sql = sql1.toString();
		List<String[]> qtyList = dbActionTemplate.getDataList(sql);

		if (qtyList.size() > 0) {
			CommonFunctions.debugMsg("qtyList: " + qtyList.size());
			CommonFunctions.debugMsg("qtyList: " + qtyList.get(0)[0]);
		}
		return qtyList;
	}

	public List<String[]> checkEquipmentFailure(String lossId) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(" SELECT PLCM_PARAMETERCODE FROM PCS_TL_LOGCONFIGURATION WHERE PLCM_KEYID IN (   ");
		sql1.append(" SELECT PLCM_PARENTID  FROM PCS_TL_LOGCONFIGURATION WHERE PLCM_KEYID ='" + lossId + "') ");
		sql = sql1.toString();
		List<String[]> efList = dbActionTemplate.getDataList(sql);

		CommonFunctions.debugMsg("efList: " + efList.size());
		CommonFunctions.debugMsg("efList: " + efList.get(0)[0]);
		return efList;
	}

	public String getPldRemarks(String detailTable, String pldetailsId) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(" SELECT REMARKS  FROM " + detailTable + "  ");
		sql1.append(" WHERE PLDETAILSID = '" + pldetailsId + "' ");
		sql = sql1.toString();

		String remarks = dbActionTemplate.getSingleValue(sql);
		return remarks;
	}

	public List<String[]> getEntryAllowDates() throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(
				" SELECT CNFM_SETTINGVALUE FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE ='PCSENTRY' and CNFM_ACTIVE='Y'   ");
		sql = sql1.toString();
		List<String[]> allowList = dbActionTemplate.getDataList(sql);

		if (allowList.size() > 0) {
			CommonFunctions.debugMsg("allowList: " + allowList.size());
			CommonFunctions.debugMsg("allowList: " + allowList.get(0)[0]);
		}

		return allowList;
	}

	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(
				"  Select  DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY')  ");
		sql1.append(" from BAL_PCS_TL_MST,GEN_TL_SHIFTMST, (SELECT DISTINCT PLMASTERID FROM " + detailTable
				+ " WHERE ACTIVE = 'Y'  ");
		sql1.append("  AND (NOPLANINMINS > 0 OR NVL(PRODUCTID,'{}') <> '{}') ) PLD  ");
		sql1.append("  	WHERE   to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')  ");
		sql1.append(" AND PRLM_SHIFTID = SFTM_KEYID AND PLD.PLMASTERID = PRLM_KEYID  AND PRLM_CELLID  = '" + cellId
				+ "'   ");
		sql1.append(" AND PRLM_LOGTYPE = 'S' AND PRLM_TIMEORQTY = 'T'   ");
		sql1.append(
				" GROUP BY DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),  SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE   ");
		sql1.append(" ORDER BY PRLM_ENTRYDATE,SFTM_SHIFTORDER   ");

		sql = sql1.toString();
		CommonFunctions.debugMsg("sqlsss: " + sql);
		List<String[]> eedList = dbActionTemplate.getDataList(sql);
		CommonFunctions.debugMsg("EntryExistsDates: " + eedList.size());
		if (eedList.size() > 0)
			CommonFunctions.debugMsg("EntryExistsDates: " + eedList.get(0)[0]);
		return eedList;

	}

	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append("  SELECT TO_CHAR(HOLM_DATE,'DD-Mon-YYYY'), HOLM_HOLIDAYFLAG FROM BAL_GEN_TL_HOLIDAYMST  ");
		// sql1.append(" WHERE to_char(HOLM_DATE,'YYYYMMDD') = to_char(to_date('" +
		// entryDate + "'),'YYYYMMDD') ");
		sql1.append("  WHERE to_char(HOLM_DATE,'YYYYMMDD') = to_char(to_date('" + entryDate
				+ "','DD-Mon-YYYY'),'YYYYMMDD') ");
		sql1.append(" AND HOLM_FACTORY='" + factId + "' ");
		sql1.append(" ORDER BY HOLM_DATE ");

		sql = sql1.toString();
		CommonFunctions.debugMsg("sqlsss: " + sql);
		List<String[]> holiList = dbActionTemplate.getDataList(sql);
		CommonFunctions.debugMsg("EntryExistsDates: " + holiList.size());
		if (holiList.size() > 0)
			CommonFunctions.debugMsg("EntryExistsDates: " + holiList.get(0)[0]);
		return holiList;
	}

	public List<String[]> checkIsOpenMsr(String mchId, String date, String shift) throws Exception {

		// StringBuffer sql1 = new StringBuffer();
		// String sql = "";

		List<String> paramValues = new ArrayList<String>();
		List<String[]> openMsrList;

		paramValues.add(mchId);
		paramValues.add(shift);
		paramValues.add(date);

		openMsrList = dbActionTemplate.processFunctionCalls("BDM_FN_ISOPENMSR", paramValues);

		return openMsrList;
	}

	public List<String[]> getDateOeeValue(String detailTable, String sectId, String cellId, String mchId,
			String entryDate) throws Exception {

//		StringBuffer sql1 = new StringBuffer();
//		String sql = "";
		/*
		 * sql1.
		 * append("  SELECT  TO_CHAR(TO_DATE(PRLM_ENTRYDATE),'DD-Mon-YYYY') ,PRLM_SHIFTID, ROUND((ROA*ROP*ROQ)/10000,2) AS OEE  "
		 * ); sql1.append(" FROM ( SELECT PRLM_ENTRYDATE, PRLM_SHIFTID,  "); sql1.
		 * append(" ROUND ( DECODE( (SUM(NVL(MCHAVAILABLETIME,0)) - SUM(NVL(SHUTDOWNLOSS_ML,0) + NVL(NOPLANINMINS,0))),0,0,(( (SUM(NVL(MCHAVAILABLETIME,0))  "
		 * ); sql1.
		 * append(" - SUM(NVL(SHUTDOWNLOSS_ML,0) + NVL(NOPLANINMINS,0)))) -  SUM(NVL(EQUIPMENTFAILURE_ML,0) +   NVL(SETUPANDADJUSTMENT_ML,0) "
		 * ); sql1.
		 * append(" +     NVL(TOOLCHfANGELOSS_ML,0) + NVL(STARTUPLOSS_ML,0) +   NVL(COMMONUTILITYLOSS_SL,0) + NVL(LOGISTICSLOSS_ML,0)  "
		 * ); sql1.
		 * append(" +  NVL(MEASURINGANDADJLOSS_ML,0) + NVL(DEFECTTIME_SL,0)+ NVL(MANAGEMENTLOSS_ML,0) + NVL(OPERATINGMOTIONLOSS_ML, 0)+  "
		 * ); sql1.
		 * append(" NVL(LINEORGANISATIONLOSS_ML, 0) )) / (SUM(NVL(MCHAVAILABLETIME,0)) - SUM(NVL(SHUTDOWNLOSS_ML,0) + NVL(NOPLANINMINS,0))))* 100,2)ROA, "
		 * ); sql1.
		 * append(" ROUND (DECODE((( (SUM(NVL(MCHAVAILABLETIME,0)) - SUM(NVL(SHUTDOWNLOSS_ML,0) + NVL(NOPLANINMINS,0)))) -  SUM(NVL(EQUIPMENTFAILURE_ML,0)  "
		 * ); sql1.
		 * append(" +   NVL(SETUPANDADJUSTMENT_ML,0) +     NVL(TOOLCHANGELOSS_ML,0) + NVL(STARTUPLOSS_ML,0) +   NVL(COMMONUTILITYLOSS_SL,0)  "
		 * ); sql1.
		 * append(" + NVL(LOGISTICSLOSS_ML,0) +  NVL(MEASURINGANDADJLOSS_ML,0) + NVL(DEFECTTIME_SL,0)+ NVL(MANAGEMENTLOSS_ML,0) + NVL(OPERATINGMOTIONLOSS_ML, 0) "
		 * ); sql1.
		 * append(" + NVL(LINEORGANISATIONLOSS_ML, 0) )) ,0,0, ROUND(SUM(NVL(EFFECTIVEPRODMINS,0)),2)/(( (SUM(NVL(MCHAVAILABLETIME,0)) - SUM(NVL(SHUTDOWNLOSS_ML,0) "
		 * ); sql1.
		 * append(" + NVL(NOPLANINMINS,0)))) -  SUM(NVL(EQUIPMENTFAILURE_ML,0) +   NVL(SETUPANDADJUSTMENT_ML,0) +     NVL(TOOLCHANGELOSS_ML,0)  "
		 * ); sql1.
		 * append(" + NVL(STARTUPLOSS_ML,0) +   NVL(COMMONUTILITYLOSS_SL,0) + NVL(LOGISTICSLOSS_ML,0) +  NVL(MEASURINGANDADJLOSS_ML,0) "
		 * ); sql1.
		 * append(" + NVL(DEFECTTIME_SL,0)+ NVL(MANAGEMENTLOSS_ML,0) + NVL(OPERATINGMOTIONLOSS_ML, 0)+ NVL(LINEORGANISATIONLOSS_ML, 0) )) )* 100,2)ROP,  "
		 * ); sql1.
		 * append(" ROUND(DECODE(SUM(NVL(PRODUCEDQTY,0)),0,0,(SUM(NVL(PRODUCEDQTY,0)) -  SUM(NVL(REJECTEDQTY,0)+NVL(REWORKQTY,0)))  "
		 * ); sql1.append(" / SUM(NVL(PRODUCEDQTY,0)))*100,2)ROQ  ");
		 * sql1.append(" FROM PCS_TL_MLD#IP,BAL_PCS_TL_MST  ");
		 * sql1.append(" WHERE PLMASTERID = PRLM_KEYID  AND PRLM_SECTIONID = '" + sectId
		 * + "'   ");
		 * sql1.append("  AND to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" +
		 * entryDate + "'),'YYYYMM') "); sql1.append(" AND CELLID = '" + cellId +
		 * "'  AND PRLM_TIMEORQTY = 'T' AND PRLM_LOGTYPE = 'S' ");
		 * sql1.append(" GROUP BY PRLM_ENTRYDATE,PRLM_SHIFTID) ");
		 * sql1.append(" ORDER BY TRUNC(PRLM_ENTRYDATE),PRLM_SHIFTID " );
		 * 
		 * sql=sql1.toString(); CommonFunctions.debugMsg("sqlsss: " +sql);
		 * List<String[]> oeeList = dbActionTemplate.getDataList(sql);
		 * CommonFunctions.debugMsg("OEE Calculation: " +oeeList.size()); if
		 * (oeeList.size() >0 ) CommonFunctions.debugMsg("OEE Calculation: "
		 * +oeeList.get(0)[0]);
		 */
		detailTable = getDetailTableName(cellId, entryDate);
		List<String> paramValues = new ArrayList<String>();
		List<String[]> oeeList;

		paramValues.add(detailTable);
		paramValues.add(sectId);
		paramValues.add(cellId);
		paramValues.add(mchId);
		paramValues.add(entryDate);

		oeeList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_DATEOEE", paramValues);

		return oeeList;
	}

	public List<String[]> getClosedMsr(String prodMchId, String condParam) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";

		List<String> paramValues = new ArrayList<String>();
		List<String[]> closedMsrList;

		// String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		// String commonParams = FilterCondSql.getGridCommonParams(commonFilter); //
		// "ISTOTALCNT="+commonFilter.getViewClick()
		// +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
		// +";";
		String commonParams = "";

		paramValues.add(prodMchId);
		paramValues.add(condParam);
		paramValues.add(commonParams);

		closedMsrList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETCOMPLETEDMSRS", paramValues);

		return closedMsrList;
	}

	public List<String[]> getMsrsDownTime(String msrNos) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";

		List<String> paramValues = new ArrayList<String>();
		List<String[]> closedMsrList;

		paramValues.add(msrNos);

		closedMsrList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETANCILLIARYTIME", paramValues);

		CommonFunctions.debugMsg("closedMsrList.get(0)[0]" + closedMsrList.get(0)[0]);

		return closedMsrList;
	}

	public String updatePldRemarks(String detailTable, String plDetailsid, String remarks) throws Exception {
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(" UPDATE " + detailTable + "  ");
			sql1.append(" SET REMARKS = '" + remarks + "' ");
			sql1.append(" WHERE PLDETAILSID = '" + plDetailsid + "' ");
			sql = sql1.toString();

			CommonFunctions.debugMsg("sqll: " + sql);

			dbActionTemplate.executeStatement(sql);

			return "1";
		} catch (Exception e) {
			return "0";
		}
	}

	public String getIsHourlyEntry() throws Exception {
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		CommonFunctions.debugMsg("hourlyEntry: " + hourlyEntry);
		if (hourlyEntry.isEmpty() || hourlyEntry == null)
			hourlyEntry = "";
		return hourlyEntry;
	}

	public String CheckConf() throws Exception {
		String flag = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE",
				"FETCHPLANQTY");
		return flag;
	}

	public String getMouldSectionCode() throws Exception {
		String mouldCode = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE",
				"MOULD_SECTION_CODES");
		return mouldCode;
	}

	public String getSectionCode(String sectId) throws Exception {
		String sectCode = dbActionTemplate.getSingleValue("gen_tl_sectionmst", "SECT_CODE", "SECT_KEYID", sectId);
		return sectCode;
	}

	public String isExpansionQtyMandatory() throws Exception {
		return dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE",
				"ISPCSEXPQTYMANDATORY");
	}

	public String[] isExpansionQtyNotMandatory(String sectId) throws Exception {
		String sectCode = dbActionTemplate.getSingleValue("gen_tl_sectionmst", "SECT_CODE", "SECT_KEYID", sectId);
		String[] mandArr = new String[2];
		String checkMand = isExpansionQtyMandatory();
		mandArr[0] = checkMand;
		String expNotMand = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE",
				"ISPCSEXPQTYNOTMAND");
		if (UIUtils.isValidKeyId(expNotMand) && UIUtils.isValidKeyId(sectCode)) {
			CommonFunctions.debugMsg("Section Code Checking : " + expNotMand + "---->" + sectCode);
			if (expNotMand.trim().equals(sectCode.trim()))
				sectCode = "Y";
		}
		mandArr[1] = sectCode;
		return mandArr;
	}

	public String getFieldsForPcs() throws Exception {
		String fieldsForPcs = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "FOR_PCS");
		CommonFunctions.debugMsg("getFieldsForPcs: " + fieldsForPcs);
		if (fieldsForPcs.isEmpty() || fieldsForPcs == null)
			fieldsForPcs = "";
		return fieldsForPcs;
	}

	public List<String[]> getIsPcsEnabled(String condParam) throws Exception {
		try {

			List<String> paramValues = new ArrayList<String>();
			paramValues.add(condParam);

			String Shiftsql = getchkshift(condParam);
			CommonFunctions.debugMsg("Shiftsql....." + Shiftsql);
			String count = dbActionTemplate.getSingleValue(Shiftsql);

			int cnt = Integer.parseInt(count);
			CommonFunctions.debugMsg("count....." + count);

			if (cnt > 0) {
				// List<String[]> pcsenabled = dbActionTemplate
				// .processFunctionCalls("PCS_PC_PRODUCTIONCALC.ChkIsPCSEnabled", paramValues);
				List<String[]> pcsenabled = dbActionTemplate.processFunctionCalls("pcs_fn_chkispcsenabled",
						paramValues);

				return pcsenabled;
			} else
				return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	/*
	 * private String getchkshift(String condParam) { StringBuffer sql = new
	 * StringBuffer(); String[] data = condParam.split(";");
	 * CommonFunctions.debugMsg("test12..." + data[1]); String[] fact =
	 * data[5].split("="); String[] date = data[3].split("="); String[] shift =
	 * data[4].split("="); String[] col = data[6].split("="); String condSql; if
	 * (col[1].equals("C")) condSql = " AND SFTM_SHIFTORDER = '" + shift[1] + "')";
	 * else condSql = " AND SFTM_KEYID = '" + shift[1] + "')"; sql.
	 * append("SELECT COUNT(*) AS CNT FROM ( SELECT SFTM_KEYID, SFTM_CODE, SFTM_SHIFTORDER"
	 * ); sql.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER<='3' "); //
	 * sql.append(" AND SFTM_FACTORYID = '"+fact[1]+"' ");
	 * sql.append(" AND TO_DATE('" + date[1] +
	 * "' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= CURRENT_TIMESTAMP "
	 * ); sql.append(condSql);
	 * 
	 * return sql.toString(); }
	 */

	private String getchkshift(String condParam) {
		StringBuffer sql = new StringBuffer();
		String[] data = condParam.split(";");
		CommonFunctions.debugMsg("test12..." + data[1]);
		String[] fact = data[5].split("=");
		String[] date = data[3].split("=");
		String[] shift = data[4].split("=");
		String[] col = data[6].split("=");
		String condSql;
		if (col[1].equals("C"))
			condSql = " AND SFTM_SHIFTORDER = '" + shift[1] + "')";
		else
			condSql = " AND SFTM_KEYID = '" + shift[1] + "')";
		sql.append("SELECT COUNT(*) AS CNT FROM ( SELECT SFTM_KEYID, SFTM_CODE, SFTM_SHIFTORDER");
		sql.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER<='3' ");
		sql.append(" AND TO_TIMESTAMP('" + date[1]
				+ " ' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= CURRENT_TIMESTAMP ");
		sql.append(condSql);

		return sql.toString();
	}

	public String getCellid(String machineId) {

		String sql = "SELECT MCHM_CELLID FROM gen_tl_machinemst WHERE MCHM_KEYID='" + machineId + "'";
		CommonFunctions.debugMsg(sql + " in side the cellid sql ");

		try {
			String cellid = dbActionTemplate.getSingleValue(sql);// getSingleValue(sql);
			CommonFunctions.debugMsg(cellid + " in side the cellid ");
			return cellid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * public String getDetailTableName(String cellId, String entryDate) throws
	 * Exception {
	 * 
	 * CommonFunctions.debugMsg("in side trhe detaisa table " + cellId);
	 * CommonFunctions.debugMsg("Inside getDetailTableName..........");
	 * 
	 * if(cellId.substring(0,3).equals("MCH")){ String cellid=getCellid (cellId);
	 * cellId=cellid; }
	 * 
	 * 
	 * if (cellId == null || cellId.trim().length() == 0) { CommonFunctions.
	 * debugMsg("cellId is null/empty - cannot resolve detail table name"); return
	 * null; // or throw a meaningful exception, see note below }
	 * 
	 * if (cellId.length() >= 3 && cellId.substring(0, 3).equals("MCH")) { String
	 * cellid = getCellid(cellId); cellId = cellid; } try { String sql = "";
	 * 
	 * // String detailTableName = "PCS_TL_" + //
	 * dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", //
	 * "SECT_KEYID", sectId); String detailTableName = "BAL_PCS_TL_" +
	 * dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_CODE", "CELL_KEYID",
	 * cellId); detailTableName = detailTableName.trim().replaceAll("-", "_");
	 * detailTableName = detailTableName.replaceAll(" ", "");
	 * CommonFunctions.debugMsg("detailTableNamee.........." + detailTableName);
	 * detailTableName = detailTableName.replaceAll("&", ""); detailTableName =
	 * detailTableName.replaceAll(",", ""); detailTableName =
	 * detailTableName.replaceAll("/", ""); // detailTableName =
	 * detailTableName.replaceAll("(", "");
	 * CommonFunctions.debugMsg("detailTableNamee.........." + detailTableName);
	 * String avialTable = dbActionTemplate.getSingleValue("TAB", "TNAME", "TNAME",
	 * detailTableName); CommonFunctions.debugMsg("avialTable.........." +
	 * avialTable); if (!UIUtils.isValidKeyId(avialTable)) avialTable = " ";
	 * 
	 * if (avialTable.equals(detailTableName)) {
	 * CommonFunctions.debugMsg("Table already exists "); } else {
	 * CommonFunctions.debugMsg("Create table for section"); sql = " CREATE TABLE "
	 * + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL ";
	 * CommonFunctions.debugMsg("sqlsqlsql" + sql);
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * String pkConst = detailTableName.replace("PCS_TL_", ""); sql =
	 * " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_" +
	 * pkConst + " PRIMARY KEY (PLDETAILSID) ";
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" +
	 * pkConst + " "; sql = sql +
	 * " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * } CommonFunctions.debugMsg("avialTable.........." + avialTable); String sql1
	 * = "SELECT to_date('" + entryDate +
	 * "') - TO_date(CNFM_FROMDATE)  FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'"
	 * ; CommonFunctions.debugMsg("sql1111" + sql1);
	 * 
	 * String diff = dbActionTemplate.getSingleValue(sql1);
	 * 
	 * CommonFunctions.debugMsg("diff" + diff);
	 * 
	 * if (Integer.parseInt(diff) < 0) detailTableName = "BAL_PCS_TL_DTL"; //
	 * detailTableName=detailTableName; // else
	 * 
	 * return detailTableName; }
	 * 
	 * catch (Exception e) {
	 * CommonFunctions.debugMsg("Exception in getDetailTableName dao impl" +
	 * e.getMessage()); } return null; }
	 */

	// sriram

	public String getDetailTableName(String cellId, String entryDate) throws Exception {

		CommonFunctions.debugMsg("in side trhe detaisa table " + cellId);
		CommonFunctions.debugMsg("Inside getDetailTableName..........");

		if (cellId == null || cellId.trim().length() == 0) {
			CommonFunctions.debugMsg("cellId is null/empty - cannot resolve detail table name");
			return null;
		}

		if (cellId.length() >= 3 && cellId.substring(0, 3).equals("MCH")) {
			String cellid = getCellid(cellId);
			cellId = cellid;
		}

		try {
			String sql = "";

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_CODE", "CELL_KEYID", cellId);
			detailTableName = detailTableName.trim().replaceAll("-", "_");
			detailTableName = detailTableName.replaceAll(" ", "");
			detailTableName = detailTableName.replaceAll("&", "");
			detailTableName = detailTableName.replaceAll(",", "");
			detailTableName = detailTableName.replaceAll("/", "");
			CommonFunctions.debugMsg("detailTableNamee.........." + detailTableName);

			// Postgres has no TAB/TNAME catalog - use to_regclass instead.
			// Unquoted identifiers are folded to lowercase by Postgres, so check lowercase.
			String checkSql = "SELECT to_regclass('" + detailTableName.toLowerCase() + "')::text";
			String avialTable = dbActionTemplate.getSingleValue(checkSql);
			CommonFunctions.debugMsg("avialTable.........." + avialTable);

			if (UIUtils.isValidKeyId(avialTable)) {
				CommonFunctions.debugMsg("Table already exists ");
			} else {
				CommonFunctions.debugMsg("Create table for section");
				sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL WHERE 1=0 ";
				CommonFunctions.debugMsg("sqlsqlsql" + sql);
				dbActionTemplate.executeStatement(sql);

				// prefix is now BAL_PCS_TL_, not PCS_TL_ - strip the actual prefix in use
				String pkConst = detailTableName.replace("BAL_PCS_TL_", "");

				sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_" + pkConst
						+ " PRIMARY KEY (PLDETAILSID) ";
				dbActionTemplate.executeStatement(sql);

				sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
				sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
				dbActionTemplate.executeStatement(sql);
			}

			CommonFunctions.debugMsg("avialTable.........." + avialTable);

			// to_date requires an explicit format mask in Postgres - match entryDate's
			// actual format (e.g. '21-Sep-2026')
			/*
			 * String sql1 = "SELECT (to_date('" + entryDate +
			 * "', 'DD-Mon-YYYY') - CNFM_FROMDATE) " +
			 * "FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'";
			 */
			
			String sql1 = "SELECT (to_date('" + entryDate + "', 'DD-Mon-YYYY') - CNFM_FROMDATE::date) "
			        + "FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'";
			
			CommonFunctions.debugMsg("sql1111" + sql1);

			String diff = dbActionTemplate.getSingleValue(sql1);
			CommonFunctions.debugMsg("diff" + diff);

			if (UIUtils.isValidKeyId(diff) && Integer.parseInt(diff.trim()) < 0)
				detailTableName = "BAL_PCS_TL_DTL";

			return detailTableName;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getDetailTableName dao impl" + e.getMessage());
		}
		return null;
	}

	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception {

		CommonFunctions.debugMsg("Inside getMasterKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			/*
			 * sql1.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST  ");
			 * 
			 * String condSql = ""; condSql += " WHERE TRUNC(PRLM_ENTRYDATE) = '" +
			 * entryDate + "'"; condSql += " AND PRLM_SHIFTID = '" + shift + "'"; condSql +=
			 * " AND PRLM_CELLID = '" + cellId + "'";
			 */
			
			sql1.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST ");

			String condSql = "";
			condSql += " WHERE PRLM_ENTRYDATE::date = '" + entryDate + "'";
			condSql += " AND PRLM_SHIFTID = '" + shift + "'";
			condSql += " AND PRLM_CELLID = '" + cellId + "'";

			sql1.append(condSql);

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			String keyId = dbActionTemplate.getSingleValue(sql);
			return keyId;
		} catch (Exception e) {
			e.printStackTrace();
			CommonFunctions.debugMsg("Exception in getMasterKeyid dao impl" + e.getMessage());
		}
		return null;
	}

	/*
	 * public List<String[]> getLastShiftProduct(String factId, String cellId,
	 * String flId, String entryDate, String shift) throws Exception {
	 * 
	 * CommonFunctions.debugMsg("Inside getLastShiftProduct Dao Impl..........");
	 * try { StringBuffer sql1 = new StringBuffer(); // String detailTableName =
	 * "PCS_TL_" + // dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST",
	 * "SECT_CODE", // "SECT_KEYID", sectId); // detailTableName =
	 * detailTableName.replaceAll("-", "#");
	 * 
	 * String detailTableName = getDetailTableName(cellId, entryDate); String sql =
	 * "";
	 * 
	 * sql1.
	 * append(" SELECT PPRD_KEYID,PRDM_KEYID,PRMM_KEYID,PPRD_MANPOWER,PPRD_CYCLETIME"
	 * ); sql1.append( " FROM " + detailTableName +
	 * ",BAL_PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,BAL_PCS_TL_PRODUCTIONDATA ");
	 * sql1.append(
	 * " WHERE productid = PRDM_KEYID (+) AND PRDM_MODEL = PRMM_KEYID AND PPRD_TEMPFIELD1=pldetailsid "
	 * ); sql1.
	 * append(" AND PRDM_ACTIVE = 'Y' AND PRMM_ACTIVE = 'Y'  AND PLDETAILSID  IN ("
	 * ); sql1.
	 * append(" SELECT KEYID FROM (SELECT     MAX(PLDETAILSID) AS KEYID, MAX(PRLM_ENTRYDATE) AS ENTRYDATE,"
	 * ); sql1.append(
	 * " MAX(DECODE(SFTM_CODE,'A',1,'B',2,'C',3,SFTM_CODE)) AS SFTMCODE, PRLM_CELLID , MAX(MACHINEID) AS MACHINEID "
	 * ); sql1.append("  FROM  BAL_PCS_TL_MST,GEN_TL_SHIFTMST,");
	 * sql1.append(detailTableName);
	 * sql1.append(" WHERE SFTM_KEYID = PRLM_SHIFTID AND TRUNC(PRLM_ENTRYDATE) <= '"
	 * + entryDate); sql1.
	 * append("' AND PLMASTERID = PRLM_KEYID AND PRODUCTID <> '{}'  AND PRLM_CELLID ='"
	 * + cellId); sql1.append("' AND SFTM_FACTORYID = '" + factId +
	 * "' GROUP BY  PRLM_CELLID ))");
	 * 
	 * sql = sql1.toString();
	 * 
	 * CommonFunctions.debugMsg("Product Filling sql=" + sql);
	 * 
	 * List<String[]> prevData = dbActionTemplate.getDataList(sql); return prevData;
	 * 
	 * } catch (Exception e) {
	 * CommonFunctions.debugMsg("Exception in getMasterKeyid dao impl" +
	 * e.getMessage()); } return null; }
	 */

	/*
	 * public List<String[]> getLastShiftProduct(String factId, String cellId,
	 * String flId, String entryDate, String shift) throws Exception {
	 * 
	 * CommonFunctions.debugMsg("Inside getLastShiftProduct Dao Impl..........");
	 * 
	 * String detailTableName = getDetailTableName(cellId, entryDate); if
	 * (!UIUtils.isValidKeyId(detailTableName)) { CommonFunctions.
	 * debugMsg("getLastShiftProduct: detailTableName could not be resolved for cellId="
	 * + cellId + ", entryDate=" + entryDate); return new ArrayList<String[]>(); //
	 * fail safely, not with null }
	 * 
	 * StringBuffer sql1 = new StringBuffer(); sql1.
	 * append(" SELECT PPRD_KEYID,PRDM_KEYID,PRMM_KEYID,PPRD_MANPOWER,PPRD_CYCLETIME"
	 * ); sql1.append(" FROM " + detailTableName +
	 * ",BAL_PCS_TL_PRODUCTMST ,PCS_TL_PRODUCTMODELMST,BAL_PCS_TL_PRODUCTIONDATA ");
	 * sql1.
	 * append(" WHERE productid = PRDM_KEYID (+) AND PRDM_MODEL = PRMM_KEYID AND PPRD_TEMPFIELD1=pldetailsid "
	 * ); sql1.
	 * append(" AND PRDM_ACTIVE = 'Y' AND PRMM_ACTIVE = 'Y'  AND PLDETAILSID  IN ("
	 * ); sql1.
	 * append(" SELECT KEYID FROM (SELECT     MAX(PLDETAILSID) AS KEYID, MAX(PRLM_ENTRYDATE) AS ENTRYDATE,"
	 * ); sql1.append(
	 * " MAX(DECODE(SFTM_CODE,'A',1,'B',2,'C',3,SFTM_CODE)) AS SFTMCODE, PRLM_CELLID , MAX(MACHINEID) AS MACHINEID "
	 * ); sql1.append("  FROM  BAL_PCS_TL_MST,GEN_TL_SHIFTMST,");
	 * sql1.append(detailTableName);
	 * sql1.append(" WHERE SFTM_KEYID = PRLM_SHIFTID AND TRUNC(PRLM_ENTRYDATE) <= '"
	 * + entryDate); sql1.
	 * append("' AND PLMASTERID = PRLM_KEYID AND PRODUCTID <> '{}'  AND PRLM_CELLID ='"
	 * + cellId); sql1.append("' AND SFTM_FACTORYID = '" + factId +
	 * "' GROUP BY  PRLM_CELLID ))");
	 * 
	 * String sql = sql1.toString(); CommonFunctions.debugMsg("Product Filling sql="
	 * + sql);
	 * 
	 * return dbActionTemplate.getDataList(sql); // let real errors propagate —
	 * don't swallow to null }
	 */
	
	public List<String[]> getLastShiftProduct(String factId, String cellId, String flId, String entryDate, String shift)
			throws Exception {

		CommonFunctions.debugMsg("Inside getLastShiftProduct Dao Impl..........");

		String detailTableName = getDetailTableName(cellId, entryDate);
		if (!UIUtils.isValidKeyId(detailTableName)) {
			CommonFunctions.debugMsg("getLastShiftProduct: detailTableName could not be resolved for cellId=" + cellId
					+ ", entryDate=" + entryDate);
			return new ArrayList<String[]>(); // fail safely, not with null
		}

		StringBuffer sql1 = new StringBuffer();
		sql1.append(" SELECT PPRD_KEYID,PRDM_KEYID,PRMM_KEYID,PPRD_MANPOWER,PPRD_CYCLETIME");
		sql1.append(" FROM " + detailTableName + " dt");
		sql1.append(" LEFT JOIN PCS_TL_PRODUCTMST ON dt.productid = PRDM_KEYID");
		sql1.append(" JOIN PCS_TL_PRODUCTMODELMST ON PRDM_MODEL = PRMM_KEYID");
		sql1.append(" JOIN BAL_PCS_TL_PRODUCTIONDATA ON PPRD_TEMPFIELD1 = dt.pldetailsid");
		sql1.append(" WHERE PRDM_ACTIVE = 'Y' AND PRMM_ACTIVE = 'Y' AND dt.PLDETAILSID IN (");
		sql1.append(" SELECT KEYID FROM (SELECT     MAX(PLDETAILSID) AS KEYID, MAX(PRLM_ENTRYDATE) AS ENTRYDATE,");
		sql1.append(
				" MAX(CASE SFTM_CODE WHEN 'A' THEN '1' WHEN 'B' THEN '2' WHEN 'C' THEN '3' ELSE SFTM_CODE END) AS SFTMCODE, PRLM_CELLID , MAX(MACHINEID) AS MACHINEID ");
		sql1.append("  FROM  BAL_PCS_TL_MST");
		sql1.append(" JOIN GEN_TL_SHIFTMST ON SFTM_KEYID = PRLM_SHIFTID");
		sql1.append(" JOIN ").append(detailTableName).append(" ON PLMASTERID = PRLM_KEYID");
		sql1.append(" WHERE PRLM_ENTRYDATE::date <= '" + entryDate);
		sql1.append("' AND PRODUCTID <> '{}'  AND PRLM_CELLID ='" + cellId);
		sql1.append("' AND SFTM_FACTORYID = '" + factId + "' GROUP BY  PRLM_CELLID) sub)");

		String sql = sql1.toString();
		CommonFunctions.debugMsg("Product Filling sql=" + sql);

		return dbActionTemplate.getDataList(sql); // let real errors propagate — don't swallow to null
	}

	public String getCurrentShift(String factId) throws Exception {

		String dateTime = CommonFunctions.dateTimeNow();
		Date currTime = CommonFunctions.getDate(dateTime, CommonFunctions.DATE_FORMAT_NOW);
		CommonFunctions.debugMsg(" currTime " + currTime.getTime());

		CommonFunctions.debugMsg(" currTime.getTime() " + currTime.getTime());

		String sql = " ";
		StringBuffer sf = new StringBuffer();
		sf.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST + " ");
		sf.append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY')||' ' ||  '" + currTime.getTime() + "'  ");
		sf.append(" BETWEEN    TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
		sf.append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' ");

		CommonFunctions.debugMsg(" sf.toString()== " + sf.toString());

		String shiftId = dbActionTemplate.getSingleValue(sf.toString());

		if (UIUtils.isValidDate(shiftId))
			return shiftId;

		StringBuffer sf1 = new StringBuffer();
		sf1.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST + " ");
		sf1.append(" WHERE   TO_CHAR(SFTM_ENDTIME ,'DD-MON-YYYY') ||' ' || '" + currTime.getTime() + "'  ");
		sf1.append(" BETWEEN TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
		sf1.append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' ");

		shiftId = dbActionTemplate.getSingleValue(sf1.toString());
		if (UIUtils.isValidDate(shiftId))
			return shiftId;
		return "";

	}

	public String getShiftKeyid(String factId, String shiftCode) throws Exception {

		CommonFunctions.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" select * from GEN_TL_SHIFTMST   ");
			// sql1.append(" WHERE SFTM_SHIFTORDER ='" + shiftCode + "' ");
			sql1.append(" WHERE SFTM_CODE ='" + shiftCode + "'     ");
			// sql1.append(" AND SFTM_FACTORYID ='" + factId + "' ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			String keyId = dbActionTemplate.getSingleValue(sql);
			return keyId;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return " ";
		}

	}

	public List<String[]> getShiftStarEndTime(String shiftId) throws Exception {

		CommonFunctions.debugMsg("Inside getShiftStarEndTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(
					" SELECT  SFTM_STARTTIME,SFTM_ENDTIME,SFTM_BREAKTIME,TO_CHAR(SFTM_STARTTIME,'HH24:MI'),  TO_CHAR(SFTM_ENDTIME,'HH24:MI') ,");
			sql1.append(" SFTM_CODE,SFTM_SHIFTORDER FROM GEN_TL_SHIFTMST WHERE SFTM_KEYID='" + shiftId + "' ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("String getShiftStarEndTimesql=" + sql);

			List<String[]> shiftStrEnd = dbActionTemplate.getDataList(sql);
			return shiftStrEnd;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}

	}

	public List<String[]> getShiftEndTime() throws Exception {

		CommonFunctions.debugMsg("Inside getShiftStarEndTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" SELECT  TO_CHAR(SFTM_STARTTIME,'HH24:MI'),  TO_CHAR(SFTM_ENDTIME,'HH24:MI') ");
			sql1.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER= 3 ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			List<String[]> shiftStrEnd = dbActionTemplate.getDataList(sql);
			return shiftStrEnd;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}

	}

	public List<String[]> getReasonLossNo(String reasonId) throws Exception {

		CommonFunctions.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(
					" SELECT PLPM_MAINLOSS, UOMM_CODE FROM BAL_PCS_TL_LOSSPHENOMENAMST  , PCS_TL_LOGCONFIGURATION, ADM_TL_UOMMST");
			sql1.append(" WHERE PLPM_KEYID ='" + reasonId + "'  AND PLPM_ACTIVE='Y' ");
			sql1.append(" AND PLPM_MAINLOSS = PLCM_KEYID AND PLCM_UOM = UOMM_KEYID (+)  ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			List<String[]> lossNoDet = dbActionTemplate.getDataList(sql);

			return lossNoDet;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getReasonLossNo dao impl" + e.getMessage());
			return null;
		}

	}

	public List<String[]> getSubMainLossNo(String subLossId) throws Exception {

		CommonFunctions.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" SELECT PLCM_PARENTID FROM PCS_TL_LOGCONFIGURATION");
			sql1.append(" WHERE PLCM_KEYID ='" + subLossId + "' ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);

			List<String[]> lossNoDet = dbActionTemplate.getDataList(sql);

			return lossNoDet;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getReasonLossNo dao impl" + e.getMessage());
			return null;
		}

	}

	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate,
			String shiftWise) throws Exception {

		CommonFunctions.debugMsg("Inside getCalendarTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			String detailTable = getDetailTableName(sectId, entryDate);

			// sql1.append(" SELECT 480 - NVL(SUM(PRODUCTIONTIME)
			// ,0)-NVL(SUM(PRODUCTIONLOSSES) ,0) AS CALENDERTIME ");
			// as on11-dec-2012
			/*
			 * sql1.
			 * append(" SELECT 480 - NVL(SUM(MCHAVAILABLETIME) ,0)-NVL(SUM(PRODUCTIONLOSSES) ,0) AS CALENDERTIME "
			 * ); sql1.append("   FROM " + detailTable + "  ");
			 * sql1.append("   WHERE 1 = 1 ");
			 * 
			 * if (UIUtils.isValidKeyId(machineId)) sql1.append(" AND MACHINEID = '" +
			 * machineId + "' " ); if (UIUtils.isValidKeyId(plmasterId))
			 * sql1.append(" AND PLMASTERID = '" + plmasterId + "' " ); else
			 * sql1.append(" AND PLMASTERID = '{}' " );
			 * 
			 * sql=sql1.toString();
			 */

			List<String> paramValues = new ArrayList<String>();
			paramValues.add(detailTable);
			paramValues.add(machineId);
			paramValues.add(plmasterId);
			paramValues.add(shiftWise); // String calendarTime= dbActionTemplate.getSingleValue(sql);

			List<String[]> calendarTime = dbActionTemplate
					.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETUNREPFORMACHINE", paramValues);

			String calTime = calendarTime.get(0)[0];
			CommonFunctions.debugMsg("calTime...0" + calTime);

			return calTime;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getCalendarTime dao impl" + e.getMessage());
		}
		return null;
	}

	/*
	 * public List<String[]>getAllTimeforHour(String cellId,String shiftId,String
	 * entryDate,String hourId,String shiftWise) throws Exception { String sql = "";
	 * String detailTableName =getDetailTableName(cellId, entryDate); StringBuffer
	 * sb =new StringBuffer();
	 * CommonFunctions.debugMsg("plMasterId"+cellId+"  "+shiftId+"  "+entryDate+"  "
	 * +hourId);
	 * 
	 * // sb.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST  "); //
	 * sb.append(" WHERE TRUNC(PRLM_ENTRYDATE) ='"+entryDate+
	 * "' AND PRLM_SHIFTID = '"+ shiftId + "' AND PRLM_CELLID ='"+cellId+"'") ;
	 * 
	 * sb.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST  ");
	 * sb.append(" WHERE PRLM_ENTRYDATE::date = TO_DATE('" + entryDate +
	 * "', 'DD-Mon-YYYY')"); sb.append(" AND PRLM_SHIFTID = '" + shiftId +
	 * "' AND PRLM_CELLID = '" + cellId + "'");
	 * 
	 * 
	 * sb.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST  ");
	 * sb.append(" WHERE PRLM_ENTRYDATE::date = TO_DATE('" + entryDate +
	 * "', 'DD-Mon-YYYY')"); sb.append(" AND PRLM_SHIFTID = '" + shiftId +
	 * "' AND PRLM_CELLID = '" + cellId + "'");
	 * 
	 * CommonFunctions.debugMsg("plMasterId"+sb.toString()); sql=sb.toString();
	 * String plMasterId = dbActionTemplate.getSingleValue(sql);
	 * CommonFunctions.debugMsg("plMasterId"+plMasterId);
	 * 
	 * try{ StringBuffer sql1 =new StringBuffer(); if(shiftWise.equals("Y")){
	 * //sql1.
	 * append(" select nvl(sum(calendartime),480), nvl( sum(unaccountedtime),0) ,nvl(sum(productiontime),0) "
	 * ); sql1.
	 * append(" select coalesce(sum(calendartime),480), coalesce(sum(unaccountedtime),0), coalesce(sum(productiontime),0) "
	 * ); sql1.append(" from "+ detailTableName );
	 * sql1.append(" where plmasterid ='"+plMasterId+"'");
	 * 
	 * } else{ //sql1.
	 * append(" select calendartime, unaccountedtime ,nvl(productiontime,0)  from "+
	 * detailTableName ); //sql1.
	 * append(" select nvl(sum(calendartime),60), nvl( sum(unaccountedtime),0) ,nvl(sum(productiontime),0) "
	 * ); sql1.
	 * append(" select coalesce(sum(calendartime),60), coalesce(sum(unaccountedtime),0), coalesce(sum(productiontime),0) "
	 * ); sql1.append(" from "+ detailTableName );
	 * sql1.append(" where plmasterid ='"+plMasterId+"' and machineid ='"+hourId+"'"
	 * ); } sql=sql1.toString();
	 * CommonFunctions.debugMsg("String getAlltimeSql====   "+sql);
	 * 
	 * List<String[]> allTime= dbActionTemplate.getDataList(sql); return allTime; }
	 * catch(Exception e) {
	 * CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl"+e.getMessage()
	 * ); return null; }
	 * 
	 * }
	 */

	public List<String[]> getAllTimeforHour(String cellId, String shiftId, String entryDate, String hourId,
			String shiftWise) throws Exception {
		String sql = "";
		String detailTableName = getDetailTableName(cellId, entryDate);
		StringBuffer sb = new StringBuffer();
		CommonFunctions.debugMsg("plMasterId" + cellId + "  " + shiftId + "  " + entryDate + "  " + hourId);

		if (!UIUtils.isValidKeyId(detailTableName)) {
			CommonFunctions.debugMsg("getAllTimeforHour: detailTableName could not be resolved for cellId=" + cellId
					+ ", entryDate=" + entryDate);
			return null; // servlet MUST null-check this — see below
		}

		if (!UIUtils.isValidKeyId(cellId) || !UIUtils.isValidKeyId(shiftId) || !UIUtils.isValidKeyId(entryDate)) {
			CommonFunctions.debugMsg("getAllTimeforHour: missing cellId/shiftId/entryDate");
			return null;
		}

		sb.append(" SELECT PRLM_KEYID FROM BAL_PCS_TL_MST  ");
		sb.append(" WHERE PRLM_ENTRYDATE::date = TO_DATE('" + entryDate + "', 'DD-Mon-YYYY')");
		sb.append(" AND PRLM_SHIFTID = '" + shiftId + "' AND PRLM_CELLID = '" + cellId + "'");

		CommonFunctions.debugMsg("plMasterId" + sb.toString());
		sql = sb.toString();
		String plMasterId = dbActionTemplate.getSingleValue(sql);
		CommonFunctions.debugMsg("plMasterId" + plMasterId);

		if (!UIUtils.isValidKeyId(plMasterId)) {
			CommonFunctions.debugMsg("getAllTimeforHour: no PL Master found for given cell/shift/date");
			return null;
		}

		try {
			StringBuffer sql1 = new StringBuffer();
			if (shiftWise.equals("Y")) {
				sql1.append(
						" select coalesce(sum(calendartime),480), coalesce(sum(unaccountedtime),0), coalesce(sum(productiontime),0) ");
				sql1.append(" from " + detailTableName);
				sql1.append(" where plmasterid ='" + plMasterId + "'");
			} else {
				sql1.append(" select coalesce(sum(calendartime),60), coalesce(sum(unaccountedtime),0), coalesce(sum(productiontime),0) ");
				sql1.append(" from " + detailTableName);
				sql1.append(" where plmasterid ='" + plMasterId + "' and machineid ='" + hourId + "'");
			}
			sql = sql1.toString();
			CommonFunctions.debugMsg("String getAlltimeSql====   " + sql);

			List<String[]> allTime = dbActionTemplate.getDataList(sql);
			return allTime;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}
	}

	public List<String[]> getLossHourTime(String shiftId, String hourId) throws SQLException {
		/*
		 * String lossFromTime=dbActionTemplate.getSingleValue("GEN_TL_SHIFTBREAKMST",
		 * "SFBM_FROMTIME", " SFBM_SHIFTID", shiftId); String
		 * lossToTime=dbActionTemplate.getSingleValue("GEN_TL_SHIFTBREAKMST",
		 * "SFBM_TOTIME", "SFBM_SHIFTID", shiftId);
		 * CommonFunctions.debugMsg("Inside getLossHourTime Dao Impl."+lossToTime+
		 * "........."+lossFromTime);
		 */
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(
					" SELECT  SFBM_FROMTIME,SFBM_TOTIME,TO_CHAR(SFBM_FROMTIME,'HH24:MI'),  TO_CHAR(SFBM_TOTIME,'HH24:MI') ,");// SFTM_BREAKTIME,
			sql1.append(
					" SFBM_SHIFTID,SFBM_HOURNUMBER FROM GEN_TL_SHIFTBREAKMST WHERE SFBM_SHIFTID='" + shiftId + "' ");
			sql1.append(" AND SFBM_ACTIVE='Y' AND SFBM_HOURNUMBER='" + hourId + "'");

			sql = sql1.toString();

			CommonFunctions.debugMsg("String getLossHourTime=" + sql);

			List<String[]> lossHourTimeStarEnd = dbActionTemplate.getDataList(sql);
			return lossHourTimeStarEnd;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}
	}

	public List<String[]> getAllTimes(String cellId, String plmasterId, String sectId, String entryDate)
			throws Exception {

		CommonFunctions.debugMsg("Inside getCalendarTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			String detailTable = getDetailTableName(cellId, entryDate);
			CommonFunctions.debugMsg("DetailTableName" + detailTable);

			sql1.append("Select ");

			/*
			 * List<String> paramValues = new ArrayList<String>();
			 * paramValues.add(detailTable); paramValues.add(cellId);
			 * paramValues.add(plmasterId);
			 * 
			 * List<String[]> calendarTime = dbActionTemplate.processFunctionCalls(
			 * "PCS_PC_PRODLOG.PCS_FN_GETTIMESFORMACHINE", paramValues);
			 * 
			 * return calendarTime;
			 */
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getCalendarTime dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getSubLossGrid(String pldetailsId) throws Exception {
		CommonFunctions.debugMsg("Inside getSubLossGrid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(
					" SELECT PLRK_KEYID, PLRK_MACHINEID, MCHM_MACHINENAME, PLRK_LOSSID, PLCM_PARAMETERNAME, PLRK_REASONID, DECODE(PLPM_NAME,NULL,PHENOMENA,PLPM_NAME) REASON, ");
			sql1.append(
					" DECODE(PLRK_CAUSEID,NULL,PLCS_KEYID,PLRK_CAUSEID) CAUSE,  DECODE(QLYT_DESCRIPTION,NULL,PLCS_DESCRIPTION,QLYT_DESCRIPTION) DESCRIPTION, QRCM_KEYID, QRCM_NAME,PLRK_PROCESSID, PROCESS,  ");
			sql1.append(
					" DECODE(UOMM_CODE,'MIN',PLRK_MINUTES,0) AS PLRK_MINUTES, DECODE(UOMM_CODE,'NOS',PLRK_MINUTES,0) AS PLRK_QTY, ");
			sql1.append(
					" PLRK_INSTANCE,PLRK_WNO, REPLACE(REPLACE(PLRK_REMARKS,'<*',''),'*>','') AS PLRK_REMARKS, PLRK_MSRNO  ");
			sql1.append(
					" FROM BAL_PCS_TL_LOSSREASONLINK, gen_tl_machinemst , PCS_TL_LOGCONFIGURATION, BAL_PCS_TL_LOSSPHENOMENAMST, ADM_TL_UOMMST ");
			sql1.append(" , QTM_VW_PHENPROCESS, QTM_TL_LAYOUT, QTM_TL_ROOTCAUSEMST,PCS_TL_LOSSCAUSEMST ");
			// sql1.append(" where PLRK_MACHINEID = '" + mchId + "' ");
			sql1.append(" where PLRK_PLDETAILID = '" + pldetailsId + "' ");
			sql1.append(" AND PLRK_MACHINEID = MCHM_KEYID   ");
			sql1.append(" AND  PLCM_KEYID = PLRK_LOSSID AND  PLPM_KEYID (+) = PLRK_REASONID   ");
			sql1.append(" AND ELEMENTID (+) = PLRK_PROCESSID  || '-' || PLRK_REASONID  ");
			sql1.append(
					" AND QLYT_ORIGINALID (+)=  PLRK_CAUSEID  AND QLYT_PARENTID (+) = PLRK_PROCESSID  || '-' || PLRK_REASONID ");
			sql1.append(
					" AND QRCM_KEYID (+) = PLRK_ROOTCAUSEID   AND PLCM_UOM  = UOMM_KEYID(+) AND PLRK_CAUSEID  = PLCS_KEYID(+)   ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);
			List<String[]> sublossList = dbActionTemplate.getDataList(sql);
			return sublossList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getSubLossGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getSubLossGridNew(String pldetailsId) throws Exception {
		CommonFunctions.debugMsg("Inside getSubLossGrid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(
					" SELECT PLRK_KEYID, PLRK_MACHINEID, MCHM_MACHINENAME, PLRK_LOSSID, TRIM(PLCM_PARAMETERNAME), PLRK_REASONID, DECODE(PLPM_NAME,NULL,PHENOMENA,PLPM_NAME) REASON, ");
			sql1.append(
					" DECODE(UOMM_CODE,'MIN',PLRK_MINUTES,0) AS PLRK_MINUTES, DECODE(UOMM_CODE,'NOS',PLRK_MINUTES,0) AS PLRK_QTY, ");
			sql1.append(" PLRK_INSTANCE, '',  PLRK_WNO,  ");
			sql1.append(" PLRK_CAUSEID, QLYT_DESCRIPTION, QRCM_KEYID, QRCM_NAME,PLRK_PROCESSID, PROCESS,  ");
			sql1.append(" REPLACE(REPLACE(PLRK_REMARKS,'<*',''),'*>','') AS PLRK_REMARKS, PLRK_MSRNO ");
			sql1.append(
					" FROM PCS_TL_LOSSREASONLINK, gen_tl_machinemst , PCS_TL_LOGCONFIGURATION, BAL_PCS_TL_LOSSPHENOMENAMST, ADM_TL_UOMMST ");
			sql1.append(" , QTM_VW_PHENPROCESS, QTM_TL_LAYOUT, QTM_TL_ROOTCAUSEMST ");
			// sql1.append(" where PLRK_MACHINEID = '" + mchId + "' ");
			sql1.append(" where PLRK_PLDETAILID = '" + pldetailsId + "' ");
			sql1.append(" AND PLRK_MACHINEID = MCHM_KEYID   ");
			sql1.append(" AND  PLCM_KEYID = PLRK_LOSSID AND  PLPM_KEYID (+) = PLRK_REASONID   ");
			sql1.append(" AND ELEMENTID (+) = PLRK_PROCESSID  || '-' || PLRK_REASONID  ");
			sql1.append(
					" AND QLYT_ORIGINALID (+)=  PLRK_CAUSEID  AND QLYT_PARENTID (+) = PLRK_PROCESSID  || '-' || PLRK_REASONID ");
			sql1.append(" AND QRCM_KEYID (+) = PLRK_ROOTCAUSEID   AND PLCM_UOM  = UOMM_KEYID(+)   ");

			sql = sql1.toString();

			CommonFunctions.debugMsg("Str	ing sql=" + sql);
			List<String[]> sublossList = dbActionTemplate.getDataList(sql);
			return sublossList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getSubLossGrid dao impl" + e.getMessage());
		}
		return null;
	}

	/*
	 * public List<String[]> getPcsRejectionCaptureGrid(String mstkeyid) throws
	 * Exception { // TODO Auto-generated method stub StringBuffer Sql = new
	 * StringBuffer();
	 * Sql.append("Select PRBD_KEYID as keyid,PPRD_KEYID as mstKeyid,");
	 * Sql.append(" DECODE(PRBD_TYPE,'R','Reject','W','Rework') as Type,");
	 * Sql.append(" QPHM_NAME as Phenomena,QPHM_KEYID AS PhenomenaId,");
	 * Sql.append(" PRBD_Quantity as Qty, PRBD_REJECTIONREASON as Reason,"); Sql.
	 * append(" DECODE(PRBD_DEFECTSOURCE,'I','In House Defect','V','Vendor Defect') as Source,"
	 * ); Sql.
	 * append(" PRBD_DEFECT as Defect ,MCHM_MACHINENAME as Equipment, MCHM_KEYID as EquipmentId, "
	 * ); Sql.append(" '0' as TypeId,'0' as DefectSourceId "); Sql.append(
	 * " from PCS_TL_REJECTIONBREAKUPDATA, BAL_PCS_TL_PRODUCTIONDATA,QTM_TL_PHENOMENAMST,gen_tl_machinemst "
	 * ); Sql.append(" where PRBD_PPRD_KEYID = PPRD_KEYID"); Sql.
	 * append(" AND PRBD_PHENOMENA = QPHM_KEYID(+) AND PRBD_EQUIPMENT = mchm_keyid(+)"
	 * ); // if(UIUtils.isValidKeyId(mstkeyid)) Sql.append(" AND PRBD_PPRD_KEYID ='"
	 * + mstkeyid + "'");
	 * 
	 * CommonFunctions.debugMsg("PRejectionSql  :" + Sql.toString()); List<String[]>
	 * getrejectiondata = dbActionTemplate.getDataList(Sql.toString()); return
	 * getrejectiondata; }
	 */
	
	public List<String[]> getPcsRejectionCaptureGrid(String mstkeyid) throws Exception {
	    StringBuffer Sql = new StringBuffer();
	    Sql.append("Select PRBD_KEYID as keyid,PPRD_KEYID as mstKeyid,");
	    Sql.append(" CASE PRBD_TYPE WHEN 'R' THEN 'Reject' WHEN 'W' THEN 'Rework' END as Type,");
	    Sql.append(" QPHM_NAME as Phenomena,QPHM_KEYID AS PhenomenaId,");
	    Sql.append(" PRBD_Quantity as Qty, PRBD_REJECTIONREASON as Reason,");
	    Sql.append(" CASE PRBD_DEFECTSOURCE WHEN 'I' THEN 'In House Defect' WHEN 'V' THEN 'Vendor Defect' END as Source,");
	    Sql.append(" PRBD_DEFECT as Defect ,MCHM_MACHINENAME as Equipment, MCHM_KEYID as EquipmentId, ");
	    Sql.append(" '0' as TypeId,'0' as DefectSourceId ");
	    Sql.append(" from BAL_PCS_TL_REJECTIONBREAKUPDATA ");
	    Sql.append(" JOIN BAL_PCS_TL_PRODUCTIONDATA ON PRBD_PPRD_KEYID = PPRD_KEYID ");
	    Sql.append(" LEFT JOIN QTM_TL_PHENOMENAMST ON PRBD_PHENOMENA = QPHM_KEYID ");
	    Sql.append(" LEFT JOIN gen_tl_machinemst ON PRBD_EQUIPMENT = MCHM_KEYID ");
	    Sql.append(" where 1 = 1 ");
//			if(UIUtils.isValidKeyId(mstkeyid))
	    Sql.append(" AND PRBD_PPRD_KEYID ='" + mstkeyid + "'");

	    CommonFunctions.debugMsg("PRejectionSql  :" + Sql.toString());
	    List<String[]> getrejectiondata = dbActionTemplate.getDataList(Sql.toString());
	    return getrejectiondata;
	}
	
	/*
	 * public List<String[]> getPcsProductionCaptureGrid(String cellid, String date,
	 * String Todate, String shiftid) throws Exception { // TODO Auto-generated
	 * method stub StringBuffer Sql = new StringBuffer(); String detailTable =
	 * getDetailTableName(cellid, Todate); CommonFunctions.debugMsg("detailTabke" +
	 * detailTable); Sql.append(
	 * "SELECT DISTINCT PPRD_KEYID AS keyId, PLMASTERID, PLDETAILSID, Decode(PRDM_KEYID,'', '-',PRDM_KEYID,PRDM_KEYID )AS productid,"
	 * ); Sql.
	 * append("Decode(prmm_keyid,'','-',PRMM_KEYID,PRMM_KEYID) AS productmodelid,");
	 * Sql.append("DECODE(CYTM_KEYID,'','-',CYTM_KEYID,CYTM_KEYID) AS manpowerid,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_HOUR) AS Hour, DECODE(PRDM_NAME,'','No plan',PRDM_NAME,PRDM_NAME) || ' - ' || prdm_code  AS Product,"
	 * );
	 * Sql.append("DECODE(PRMM_NAME,'','-',PRMM_NAME,PRMM_NAME) AS ProductModel,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_PRODUCEDQTY ) AS ProdQty,TO_CHAR(PPRD_REWORKQTY) AS ReworkQty,TO_CHAR(PPRD_REJECTEDQTY)  AS RejQty,"
	 * ); Sql.
	 * append(" DECODE(CYTM_MANPOWER,'','0',CYTM_MANPOWER,CYTM_MANPOWER )AS Manpower"
	 * ); Sql.append(
	 * " ,TO_CHAR(PPRD_CYCLETIME) AS RNT ,TO_CHAR(PPRD_NOPLANDURATION) as Duration ,PPRD_REMARKS AS Remarks "
	 * ); Sql.
	 * append(" ,PPRD_SHIFTNOPLAN as SHIFTNOPLAN , PPRD_HOURLYNOPLAN AS Hourlynoplan "
	 * ); Sql.
	 * append(", TO_CHAR(calendartime) as Availtimehourly,TO_CHAR(productiontime) as Prodtimehourly,"
	 * ); Sql.append(
	 * " TO_CHAR(SUM( NVL(EQUIPMENTFAILURE_ML,0)+ NVL(SETUPANDADJUSTMENT_ML,0)+ NVL(TOOLCHANGELOSS_ML,0)+ NVL(STARTUPLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(MINORSTOPPAGELOSS_ML,0)+ NVL(SPEEDLOSS_ML,0)+ NVL(SHUTDOWNLOSS_ML,0)+ NVL(MANAGEMENTLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(OPERATINGMOTIONLOSS_ML,0)+ NVL(LINEORGANISATIONLOSS_ML,0)+ NVL(LOGISTICSLOSS_ML,0)+ NVL(MEASURINGANDADJLOSS_ML,0)+ NVL(DEFECTSANDREWORKLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(DIETOOLANDJIGLOSS_ML,0)+ NVL(ENERGYLOSS_ML,0)+ NVL(YIELDLOSS_ML,0)+ NVL(POKAYOKELINESTOP_ML,0)+ NVL(JIGFIXTURELOSS_ML,0)+ NVL(COMMONUTILITYLOSS_SL,0))) "
	 * );
	 * Sql.append(" as Losstimehourly,TO_CHAR(unaccountedtime ) as Unreptimehourly "
	 * ); Sql.append(", completedflag AS completedflag "); Sql.append(
	 * "FROM BAL_PCS_TL_PRODUCTIONDATA ,  PCS_TL_CYCLETIMEMST ,BAL_PCS_TL_PRODUCTMST , PCS_TL_PRODUCTMODELMST, "
	 * ); Sql.append(detailTable); Sql.
	 * append(" where PPRD_MANPOWER = CYTM_KEYID(+) AND PPRD_PRODUCTID = PRDM_KEYID(+) "
	 * ); Sql.append(" AND PPRD_PRODUCTMODEL =PRMM_KEYID(+) ");
	 * Sql.append(" AND PPRD_TEMPFIELD1 = PLDETAILSID(+) ");
	 * CommonFunctions.debugMsg("cellid" + cellid);
	 * CommonFunctions.debugMsg("shiftid" + shiftid); if
	 * (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "Group by PPRD_KEYID, PLMASTERID, PLDETAILSID, PRDM_KEYID, PRMM_KEYID, CYTM_KEYID, PPRD_HOUR , PRDM_NAME,"
	 * ); Sql.append(
	 * " PRDM_CODE, PRMM_NAME, PPRD_PRODUCEDQTY, PPRD_REWORKQTY, PPRD_REJECTEDQTY, CYTM_MANPOWER, unaccountedtime, "
	 * ); Sql.append(
	 * " productiontime ,completedflag, PPRD_HOUR, PPRD_CYCLETIME, PPRD_NOPLANDURATION, PPRD_REMARKS, PPRD_SHIFTNOPLAN,"
	 * ); Sql.append(" PPRD_HOURLYNOPLAN, calendartime");// order by hour,product
	 * "); Sql.append(
	 * "  UNION   SELECT '' ,'','','','',''as hour,''AS Product, '','TOTAL' AS CODE ,TO_CHAR( sum(PPRD_PRODUCEDQTY)),To_char(sum(PPRD_REWORKQTY)),To_char(sum(PPRD_REJECTEDQTY)),'','','','','','','','','','',''"
	 * ); Sql.append(
	 * "FROM BAL_PCS_TL_PRODUCTIONDATA ,  PCS_TL_CYCLETIMEMST ,BAL_PCS_TL_PRODUCTMST , PCS_TL_PRODUCTMODELMST,"
	 * ); Sql.append(detailTable); Sql.
	 * append("  where PPRD_MANPOWER = CYTM_KEYID(+) AND PPRD_PRODUCTID = PRDM_KEYID(+) "
	 * ); Sql.append("AND PPRD_PRODUCTMODEL =PRMM_KEYID(+)  ");
	 * Sql.append("AND PPRD_TEMPFIELD1 = PLDETAILSID(+)  "); if
	 * (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "GROUP BY '', '', '', '', '', '', '', '', 'TOTAL', '', '', '', '', '', '', '', '', '', '', ''  order by hour,product"
	 * );
	 * 
	 * CommonFunctions.debugMsg("ProductionSql  :" + Sql.toString()); List<String[]>
	 * getproductiondata = dbActionTemplate.getDataList(Sql.toString());
	 * 
	 * // int productionDataSize = getproductiondata.size();
	 * if(getproductiondata.size() == 0 || getproductiondata == null) {
	 * StringBuilder sb =new StringBuilder();
	 * sb.append(" SELECT PRLM_KEYID from BAL_PCS_TL_MST where ");
	 * if(UIUtils.isValidKeyId(cellid)) sb.append(" AND PRLM_CELLID ='"+cellid+"'");
	 * if(UIUtils.isValidKeyId(shiftid))
	 * sb.append(" AND PRLM_SHIFTID ='"+shiftid+"'");
	 * sb.append(" AND PRLM_DATE ='"+date+"'  ");
	 * CommonFunctions.debugMsg("masterid SQL in grid"+sb.toString()); String
	 * plmasterid = dbActionTemplate.getSingleValue(sb.toString());
	 * CommonFunctions.debugMsg("masterid in grid"+plmasterid); if(
	 * !UIUtils.isValidKeyId(plmasterid)) {
	 * 
	 * } // String detailTableName =getDetailTableName(cellid, Todate);
	 * 
	 * // String plmasterid = } else return getproductiondata;
	 * 
	 * 
	 * return getproductiondata; }
	 */

	//sriram
	/*
	 * public List<String[]> getPcsProductionCaptureGrid(String cellid, String date,
	 * String Todate, String shiftid) throws Exception { StringBuffer Sql = new
	 * StringBuffer(); String detailTable = getDetailTableName(cellid, Todate);
	 * CommonFunctions.debugMsg("detailTabke" + detailTable);
	 * 
	 * if (!UIUtils.isValidKeyId(detailTable)) { CommonFunctions.
	 * debugMsg("getPcsProductionCaptureGrid: detailTable could not be resolved for cellid="
	 * + cellid + ", Todate=" + Todate); return new ArrayList<String[]>(); // fail
	 * safely instead of building a query with "null" in FROM }
	 * 
	 * Sql.append(
	 * "SELECT DISTINCT PPRD_KEYID AS keyId, PLMASTERID, PLDETAILSID, Decode(PRDM_KEYID,'', '-',PRDM_KEYID,PRDM_KEYID )AS productid,"
	 * ); Sql.
	 * append("Decode(prmm_keyid,'','-',PRMM_KEYID,PRMM_KEYID) AS productmodelid,");
	 * Sql.append("DECODE(CYTM_KEYID,'','-',CYTM_KEYID,CYTM_KEYID) AS manpowerid,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_HOUR) AS Hour, DECODE(PRDM_NAME,'','No plan',PRDM_NAME,PRDM_NAME) || ' - ' || prdm_code  AS Product,"
	 * );
	 * Sql.append("DECODE(PRMM_NAME,'','-',PRMM_NAME,PRMM_NAME) AS ProductModel,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_PRODUCEDQTY ) AS ProdQty,TO_CHAR(PPRD_REWORKQTY) AS ReworkQty,TO_CHAR(PPRD_REJECTEDQTY)  AS RejQty,"
	 * ); Sql.
	 * append(" DECODE(CYTM_MANPOWER,'','0',CYTM_MANPOWER,CYTM_MANPOWER )AS Manpower"
	 * ); Sql.append(
	 * " ,TO_CHAR(PPRD_CYCLETIME) AS RNT ,TO_CHAR(PPRD_NOPLANDURATION) as Duration ,PPRD_REMARKS AS Remarks "
	 * ); Sql.
	 * append(" ,PPRD_SHIFTNOPLAN as SHIFTNOPLAN , PPRD_HOURLYNOPLAN AS Hourlynoplan "
	 * ); Sql.
	 * append(", TO_CHAR(calendartime) as Availtimehourly,TO_CHAR(productiontime) as Prodtimehourly,"
	 * ); Sql.append(
	 * " TO_CHAR(SUM( NVL(EQUIPMENTFAILURE_ML,0)+ NVL(SETUPANDADJUSTMENT_ML,0)+ NVL(TOOLCHANGELOSS_ML,0)+ NVL(STARTUPLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(MINORSTOPPAGELOSS_ML,0)+ NVL(SPEEDLOSS_ML,0)+ NVL(SHUTDOWNLOSS_ML,0)+ NVL(MANAGEMENTLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(OPERATINGMOTIONLOSS_ML,0)+ NVL(LINEORGANISATIONLOSS_ML,0)+ NVL(LOGISTICSLOSS_ML,0)+ NVL(MEASURINGANDADJLOSS_ML,0)+ NVL(DEFECTSANDREWORKLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(DIETOOLANDJIGLOSS_ML,0)+ NVL(ENERGYLOSS_ML,0)+ NVL(YIELDLOSS_ML,0)+ NVL(POKAYOKELINESTOP_ML,0)+ NVL(JIGFIXTURELOSS_ML,0)+ NVL(COMMONUTILITYLOSS_SL,0))) "
	 * );
	 * Sql.append(" as Losstimehourly,TO_CHAR(unaccountedtime ) as Unreptimehourly "
	 * ); Sql.append(", completedflag AS completedflag ");
	 * 
	 * // Oracle (+) outer join syntax replaced with standard LEFT JOIN; //
	 * BAL_PCS_TL_PRODUCTIONDATA (PPRD_*) is the driving table.
	 * Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	 * Sql.append("LEFT JOIN PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID ");
	 * Sql.append("LEFT JOIN BAL_PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID "
	 * ); Sql.
	 * append("LEFT JOIN PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID "
	 * ); Sql.append("LEFT JOIN " + detailTable +
	 * " ON PPRD_TEMPFIELD1 = PLDETAILSID "); Sql.append(" WHERE 1 = 1 ");
	 * 
	 * CommonFunctions.debugMsg("cellid" + cellid);
	 * CommonFunctions.debugMsg("shiftid" + shiftid); if
	 * (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "Group by PPRD_KEYID, PLMASTERID, PLDETAILSID, PRDM_KEYID, PRMM_KEYID, CYTM_KEYID, PPRD_HOUR , PRDM_NAME,"
	 * ); Sql.append(
	 * " PRDM_CODE, PRMM_NAME, PPRD_PRODUCEDQTY, PPRD_REWORKQTY, PPRD_REJECTEDQTY, CYTM_MANPOWER, unaccountedtime, "
	 * ); Sql.append(
	 * " productiontime ,completedflag, PPRD_HOUR, PPRD_CYCLETIME, PPRD_NOPLANDURATION, PPRD_REMARKS, PPRD_SHIFTNOPLAN,"
	 * ); Sql.append(" PPRD_HOURLYNOPLAN, calendartime");
	 * 
	 * Sql.append(
	 * "  UNION   SELECT '' ,'','','','',''as hour,''AS Product, '','TOTAL' AS CODE ,TO_CHAR( sum(PPRD_PRODUCEDQTY)),To_char(sum(PPRD_REWORKQTY)),To_char(sum(PPRD_REJECTEDQTY)),'','','','','','','','','','',''"
	 * );
	 * 
	 * Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	 * Sql.append("LEFT JOIN PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID ");
	 * Sql.append("LEFT JOIN BAL_PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID "
	 * ); Sql.
	 * append("LEFT JOIN PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID "
	 * ); Sql.append("LEFT JOIN " + detailTable +
	 * " ON PPRD_TEMPFIELD1 = PLDETAILSID "); Sql.append(" WHERE 1 = 1 ");
	 * 
	 * if (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "GROUP BY '', '', '', '', '', '', '', '', 'TOTAL', '', '', '', '', '', '', '', '', '', '', ''  order by hour,product"
	 * );
	 * 
	 * CommonFunctions.debugMsg("ProductionSql  :" + Sql.toString()); List<String[]>
	 * getproductiondata = dbActionTemplate.getDataList(Sql.toString());
	 * 
	 * return getproductiondata; }
	 */

	// Replacement for BAL_PcsEntryDaoImpl.getPcsProductionCaptureGrid(...)
	//
	// All 6 DECODE(col,'',default,col,col) calls -> COALESCE(NULLIF(col,''), default),
	// which is functionally identical (Oracle treats '' and NULL as equivalent for
	// VARCHAR2, matching what COALESCE/NULLIF does here) and is the idiomatic
	// Postgres way to write "use this default if the column is blank or null."
	// Everything else in the method (the LEFT JOIN rewrite, the guard on
	// detailTable) is unchanged from what you already have.

	/*
	 * public List<String[]> getPcsProductionCaptureGrid(String cellid, String date,
	 * String Todate, String shiftid) throws Exception { StringBuffer Sql = new
	 * StringBuffer(); String detailTable = getDetailTableName(cellid, Todate);
	 * CommonFunctions.debugMsg("detailTabke" + detailTable);
	 * 
	 * if (!UIUtils.isValidKeyId(detailTable)) { CommonFunctions.
	 * debugMsg("getPcsProductionCaptureGrid: detailTable could not be resolved for cellid="
	 * + cellid + ", Todate=" + Todate); return new ArrayList<String[]>(); // fail
	 * safely instead of building a query with "null" in FROM }
	 * 
	 * Sql.append(
	 * "SELECT DISTINCT PPRD_KEYID AS keyId, PLMASTERID, PLDETAILSID, COALESCE(NULLIF(PRDM_KEYID,''), '-') AS productid,"
	 * ); Sql.append("COALESCE(NULLIF(PRMM_KEYID,''), '-') AS productmodelid,");
	 * Sql.append("COALESCE(NULLIF(CYTM_KEYID,''), '-') AS manpowerid,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_HOUR) AS Hour, COALESCE(NULLIF(PRDM_NAME,''), 'No plan') || ' - ' || prdm_code  AS Product,"
	 * ); Sql.append("COALESCE(NULLIF(PRMM_NAME,''), '-') AS ProductModel,");
	 * Sql.append(
	 * "TO_CHAR(PPRD_PRODUCEDQTY ) AS ProdQty,TO_CHAR(PPRD_REWORKQTY) AS ReworkQty,TO_CHAR(PPRD_REJECTEDQTY)  AS RejQty,"
	 * ); Sql.append(" COALESCE(NULLIF(CYTM_MANPOWER,''), '0') AS Manpower");
	 * Sql.append(
	 * " ,TO_CHAR(PPRD_CYCLETIME) AS RNT ,TO_CHAR(PPRD_NOPLANDURATION) as Duration ,PPRD_REMARKS AS Remarks "
	 * ); Sql.
	 * append(" ,PPRD_SHIFTNOPLAN as SHIFTNOPLAN , PPRD_HOURLYNOPLAN AS Hourlynoplan "
	 * ); Sql.
	 * append(", TO_CHAR(calendartime) as Availtimehourly,TO_CHAR(productiontime) as Prodtimehourly,"
	 * ); Sql.append(
	 * " TO_CHAR(SUM( NVL(EQUIPMENTFAILURE_ML,0)+ NVL(SETUPANDADJUSTMENT_ML,0)+ NVL(TOOLCHANGELOSS_ML,0)+ NVL(STARTUPLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(MINORSTOPPAGELOSS_ML,0)+ NVL(SPEEDLOSS_ML,0)+ NVL(SHUTDOWNLOSS_ML,0)+ NVL(MANAGEMENTLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(OPERATINGMOTIONLOSS_ML,0)+ NVL(LINEORGANISATIONLOSS_ML,0)+ NVL(LOGISTICSLOSS_ML,0)+ NVL(MEASURINGANDADJLOSS_ML,0)+ NVL(DEFECTSANDREWORKLOSS_ML,0)"
	 * ); Sql.append(
	 * " + NVL(DIETOOLANDJIGLOSS_ML,0)+ NVL(ENERGYLOSS_ML,0)+ NVL(YIELDLOSS_ML,0)+ NVL(POKAYOKELINESTOP_ML,0)+ NVL(JIGFIXTURELOSS_ML,0)+ NVL(COMMONUTILITYLOSS_SL,0))) "
	 * );
	 * Sql.append(" as Losstimehourly,TO_CHAR(unaccountedtime ) as Unreptimehourly "
	 * ); Sql.append(", completedflag AS completedflag ");
	 * 
	 * // NOTE: NVL(...) calls above are still Oracle-only too — see the //
	 * accompanying note about orafce. If orafce isn't available, these need //
	 * COALESCE(...) the same way the DECODE calls did.
	 * 
	 * Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	 * Sql.append("LEFT JOIN BAL_PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID "
	 * );
	 * Sql.append("LEFT JOIN BAL_PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID "
	 * ); Sql.
	 * append("LEFT JOIN BAL_PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID "
	 * ); Sql.append("LEFT JOIN " + detailTable +
	 * " ON PPRD_TEMPFIELD1 = PLDETAILSID "); Sql.append(" WHERE 1 = 1 ");
	 * 
	 * CommonFunctions.debugMsg("cellid" + cellid);
	 * CommonFunctions.debugMsg("shiftid" + shiftid); if
	 * (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "Group by PPRD_KEYID, PLMASTERID, PLDETAILSID, PRDM_KEYID, PRMM_KEYID, CYTM_KEYID, PPRD_HOUR , PRDM_NAME,"
	 * ); Sql.append(
	 * " PRDM_CODE, PRMM_NAME, PPRD_PRODUCEDQTY, PPRD_REWORKQTY, PPRD_REJECTEDQTY, CYTM_MANPOWER, unaccountedtime, "
	 * ); Sql.append(
	 * " productiontime ,completedflag, PPRD_HOUR, PPRD_CYCLETIME, PPRD_NOPLANDURATION, PPRD_REMARKS, PPRD_SHIFTNOPLAN,"
	 * ); Sql.append(" PPRD_HOURLYNOPLAN, calendartime");
	 * 
	 * Sql.append(
	 * "  UNION   SELECT '' ,'','','','',''as hour,''AS Product, '','TOTAL' AS CODE ,TO_CHAR( sum(PPRD_PRODUCEDQTY)),To_char(sum(PPRD_REWORKQTY)),To_char(sum(PPRD_REJECTEDQTY)),'','','','','','','','','','',''"
	 * );
	 * 
	 * Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	 * Sql.append("LEFT JOIN BAL_PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID "
	 * );
	 * Sql.append("LEFT JOIN BAL_PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID "
	 * ); Sql.
	 * append("LEFT JOIN BAL_PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID "
	 * ); Sql.append("LEFT JOIN " + detailTable +
	 * " ON PPRD_TEMPFIELD1 = PLDETAILSID "); Sql.append(" WHERE 1 = 1 ");
	 * 
	 * if (UIUtils.isValidKeyId(cellid)) Sql.append(" AND PPRD_CELLID ='" + cellid +
	 * "'"); if (UIUtils.isValidKeyId(shiftid)) Sql.append(" AND PPRD_SHIFTID ='" +
	 * shiftid + "'"); Sql.append(" AND PPRD_DATE ='" + date + "' "); Sql.append(
	 * "GROUP BY '', '', '', '', '', '', '', '', 'TOTAL', '', '', '', '', '', '', '', '', '', '', ''  order by hour,product"
	 * );
	 * 
	 * CommonFunctions.debugMsg("ProductionSql  :" + Sql.toString()); List<String[]>
	 * getproductiondata = dbActionTemplate.getDataList(Sql.toString());
	 * 
	 * return getproductiondata; }
	 */
    //elumalai
	public List<String[]> getPcsProductionCaptureGrid(String cellid, String date, String Todate, String shiftid)
	        throws Exception {
	    StringBuffer Sql = new StringBuffer();
	    //elumalai
//	    if (!UIUtils.isValidKeyId(cellid)) {
//	        CommonFunctions.debugMsg("getPcsProductionCaptureGrid: no cell selected, returning empty grid");
//	        return new ArrayList<String[]>();
//	    }
	    String detailTable = getDetailTableName(cellid, Todate);
	    //String detailTable = "BAL_PCS_TL_DTL";
	    CommonFunctions.debugMsg("detailTabke" + detailTable);

	    if (!UIUtils.isValidKeyId(detailTable)) {
	        CommonFunctions.debugMsg("getPcsProductionCaptureGrid: detailTable could not be resolved for cellid="
	                + cellid + ", Todate=" + Todate);
	        return new ArrayList<String[]>(); // fail safely instead of building a query with "null" in FROM
	    }

	    Sql.append( "SELECT * FROM (" );
	    Sql.append( "SELECT DISTINCT PPRD_KEYID AS keyId, PLMASTERID, PLDETAILSID, COALESCE(NULLIF(PRDM_KEYID,''), '-') AS productid,");
	    Sql.append("COALESCE(NULLIF(PRMM_KEYID,''), '-') AS productmodelid,");
	    Sql.append("COALESCE(NULLIF(CYTM_KEYID,''), '-') AS manpowerid,");
	    Sql.append(
	            "PPRD_HOUR::text AS Hour, COALESCE(NULLIF(PRDM_NAME,''), 'No plan') || ' - ' || prdm_code  AS Product,");
	    Sql.append("COALESCE(NULLIF(PRMM_NAME,''), '-') AS ProductModel,");
	    Sql.append(
	            "PPRD_PRODUCEDQTY::text AS ProdQty, PPRD_REWORKQTY::text AS ReworkQty, PPRD_REJECTEDQTY::text AS RejQty,");
	    Sql.append(" COALESCE(NULLIF(CYTM_MANPOWER::text, ''), '0') AS Manpower");
	    Sql.append(
	            " ,PPRD_CYCLETIME::text AS RNT ,PPRD_NOPLANDURATION::text as Duration ,PPRD_REMARKS AS Remarks ");
	    Sql.append(" ,PPRD_SHIFTNOPLAN as SHIFTNOPLAN , PPRD_HOURLYNOPLAN AS Hourlynoplan ");
	    Sql.append(", calendartime::text as Availtimehourly, productiontime::text as Prodtimehourly,");
	    Sql.append(
	            " (SUM( COALESCE(EQUIPMENTFAILURE_ML,0)+ COALESCE(SETUPANDADJUSTMENT_ML,0)+ COALESCE(TOOLCHANGELOSS_ML,0)+ COALESCE(STARTUPLOSS_ML,0)");
	    Sql.append(
	            " + COALESCE(MINORSTOPPAGELOSS_ML,0)+ COALESCE(SPEEDLOSS_ML,0)+ COALESCE(SHUTDOWNLOSS_ML,0)+ COALESCE(MANAGEMENTLOSS_ML,0)");
	    Sql.append(
	            " + COALESCE(OPERATINGMOTIONLOSS_ML,0)+ COALESCE(LINEORGANISATIONLOSS_ML,0)+ COALESCE(LOGISTICSLOSS_ML,0)+ COALESCE(MEASURINGANDADJLOSS_ML,0)+ COALESCE(DEFECTSANDREWORKLOSS_ML,0)");
	    Sql.append(
	            " + COALESCE(DIETOOLANDJIGLOSS_ML,0)+ COALESCE(ENERGYLOSS_ML,0)+ COALESCE(YIELDLOSS_ML,0)+ COALESCE(POKAYOKELINESTOP_ML,0)+ COALESCE(JIGFIXTURELOSS_ML,0)+ COALESCE(COMMONUTILITYLOSS_SL,0)))::text ");
	    Sql.append(" as Losstimehourly, unaccountedtime::text as Unreptimehourly ");
	    Sql.append(", completedflag AS completedflag ");

	    Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	    Sql.append("LEFT JOIN BAL_PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID ");
	    Sql.append("LEFT JOIN PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID ");
	    Sql.append("LEFT JOIN PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID ");
	    Sql.append("LEFT JOIN " + detailTable + " ON PPRD_TEMPFIELD1 = PLDETAILSID ");
	    Sql.append(" WHERE 1 = 1 ");

	    CommonFunctions.debugMsg("cellid" + cellid);
	    CommonFunctions.debugMsg("shiftid" + shiftid);
	    if (UIUtils.isValidKeyId(cellid))
	        Sql.append(" AND PPRD_CELLID ='" + cellid + "'");
	    if (UIUtils.isValidKeyId(shiftid))
	        Sql.append(" AND PPRD_SHIFTID ='" + shiftid + "'");
	    //Sql.append(" AND PPRD_DATE ='" + date + "' ");
	    Sql.append(" AND PPRD_DATE::date = TO_DATE('" + date + "','DD-Mon-YYYY') ");
	    Sql.append(
	            "Group by PPRD_KEYID, PLMASTERID, PLDETAILSID, PRDM_KEYID, PRMM_KEYID, CYTM_KEYID, PPRD_HOUR , PRDM_NAME,");
	    Sql.append(
	            " PRDM_CODE, PRMM_NAME, PPRD_PRODUCEDQTY, PPRD_REWORKQTY, PPRD_REJECTEDQTY, CYTM_MANPOWER, unaccountedtime, ");
	    Sql.append(
	            " productiontime ,completedflag, PPRD_HOUR, PPRD_CYCLETIME, PPRD_NOPLANDURATION, PPRD_REMARKS, PPRD_SHIFTNOPLAN,");
	    Sql.append(" PPRD_HOURLYNOPLAN, calendartime");

	    Sql.append(
	            "  UNION   SELECT '' ,'','','','',''as hour,''AS Product, '','TOTAL' AS CODE ,sum(PPRD_PRODUCEDQTY)::text, sum(PPRD_REWORKQTY)::text, sum(PPRD_REJECTEDQTY)::text,'','','','','','','','','','',''");

	    Sql.append("FROM BAL_PCS_TL_PRODUCTIONDATA ");
	    Sql.append("LEFT JOIN BAL_PCS_TL_CYCLETIMEMST ON PPRD_MANPOWER = CYTM_KEYID ");
	    Sql.append("LEFT JOIN PCS_TL_PRODUCTMST ON PPRD_PRODUCTID = PRDM_KEYID ");
	    Sql.append("LEFT JOIN PCS_TL_PRODUCTMODELMST ON PPRD_PRODUCTMODEL = PRMM_KEYID ");
	    Sql.append("LEFT JOIN " + detailTable + " ON PPRD_TEMPFIELD1 = PLDETAILSID ");
	    Sql.append(" WHERE 1 = 1 ");

	    if (UIUtils.isValidKeyId(cellid))
	        Sql.append(" AND PPRD_CELLID ='" + cellid + "'");
	    if (UIUtils.isValidKeyId(shiftid))
	        Sql.append(" AND PPRD_SHIFTID ='" + shiftid + "'");
	    //Sql.append(" AND PPRD_DATE ='" + date + "' ");
	    Sql.append(" AND PPRD_DATE::date = TO_DATE('" + date + "','DD-Mon-YYYY') ");
		/*
		 * Sql.append(
		 * "GROUP BY '', '', '', '', '', '', '', '', 'TOTAL', '', '', '', '', '', '', '', '', '', '', ''  order by hour,product"
		 * );
		 */
	    

	     Sql.append(" ) t ORDER BY ");
	    	    Sql.append(" CASE WHEN t.productmodel = 'TOTAL' THEN 1 ELSE 0 END, ");
	    	    Sql.append(" CASE WHEN t.hour ~ '^[0-9]+$' THEN t.hour::int END, ");
	    	    Sql.append(" t.product ");
	  //  Sql.append("order by hour,product");
	    
	    CommonFunctions.debugMsg("ProductionSql  :" + Sql.toString());
	    List<String[]> getproductiondata = dbActionTemplate.getDataList(Sql.toString());

	    return getproductiondata;
	}
	
	/*
	 * @Override public List<String[]> getPcsLossCaptureGrid(String flid, String
	 * Fromdate, String Todate, String shiftid) throws Exception { // TODO
	 * Auto-generated method stub StringBuffer menuSql = new StringBuffer();
	 * 
	 * menuSql.append(" SELECT DISTINCT * FROM ( "); menuSql.append(
	 * " SELECT 'PldetailsId', 'Keyid', 'Hr No' as LOSSHRNO,'From Date', 'From Time', 'To Date' ,'To Time','Minutes','LossId', 'Loss Ref.No', 'LossReasonId', 'Loss / Subloss',   'Loss Reason','Equipment','Machineid','Tool Name','ToolId', "
	 * );// menuSql.
	 * append(" 'LossParentId','Trade', 'Prodn. Impact', 'Prodn.Imp.Qty(Other then Downtime)', "
	 * );// menuSql.append(
	 * " ' Remarks ',' Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No.', 0 AS DATAORDER FROM DUAL "
	 * ); menuSql.append(" 		UNION "); menuSql.append(
	 * " 		SELECT PLOS_PLDETAILSID, PLOS_KEYID,PLOS_LOSSHRNO, TO_CHAR(PLOS_FROMTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_FROMTIME,'HH24:MI'), TO_CHAR(PLOS_TOTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_TOTIME,'HH24:MI'), "
	 * ); // menuSql.append(" TO_CHAR(ROUND(PLOS_LOSSTIME/60,2)) , ");
	 * menuSql.append(" 	PLOS_LOSSTIME , ");
	 * menuSql.append(" PLOS_LOSSID, PLCM_LOSSNO, PLRK_KEYID , PLCM_PARAMETERNAME, "
	 * );// menuSql.append(
	 * " PLOS_LOSSREASON, MCHM_MACHINENAME,PLOS_MACHINEID,PTLM_NAME,PLOS_TOOLID,PLCM_PARENTID , TRDM_NAME, PLOS_PROD_IMPACT, TO_CHAR(DECODE(PLOS_PROD_IMP_QTY,NULL,0,PLOS_PROD_IMP_QTY)),"
	 * );// menuSql.append(" PLOS_REMARKS,"); menuSql.append(" '' AS BTN_WHY, ");
	 * 
	 * // '' AS WHY_REFNO, menuSql.
	 * append(" ( SELECT LISTAGG(WWMS_KEYID, ', ') WITHIN GROUP (ORDER BY WWMS_KEYID) FROM BDM_TL_WHYWHYMST "
	 * ); menuSql.
	 * append(" where WWMS_REFDOCNO NOT IN  ('-','{}') AND WWMS_REFDOCNO = pl.PLOS_KEYID "
	 * );// <> '-' // menuSql.append(" AND WWMS_REFDOCTYPE='PCS' ");
	 * menuSql.append(" ) "); menuSql.append(" , '' AS BTN_KAIZEN, ");
	 * 
	 * menuSql.append(
	 * " ( SELECT LISTAGG(KZBN_KEYID, ', ') WITHIN GROUP (ORDER BY KZBN_KEYID)  FROM KZN_TL_KAIZENBANKMST "
	 * ); menuSql.
	 * append(" where KZBN_REFDOCNO NOT IN  ('-','{}') AND KZBN_REFDOCNO = pl.PLOS_KEYID "
	 * );// pl.PLOS_KEYID <> // '-'
	 * 
	 * menuSql.append(" ) AS KAIZEN_REFNO, 1 AS DATAORDER"); menuSql.append(
	 * " FROM BAL_PCS_TL_LOSSCAPTURE pl,gen_tl_machinemst, BAL_PCS_TL_LOSSPHENOMENAMST, BAL_PCS_TL_LOGCONFIGURATION, GEN_TL_TRADEMST , PCS_TL_LOSSREASONLINK ,pcs_tl_toolsmst"
	 * ); menuSql.append(
	 * " WHERE PLPM_KEYID (+)= PLOS_LOSSREASON AND PLCM_KEYID(+) = PLOS_LOSSID  AND mchm_keyid(+)=PLOS_MACHINEID AND PLRK_LOSSID = PLOS_LOSSID AND TRDM_KEYID (+)= PLOS_TRADEID  AND PLRK_PLDETAILID(+) = PLOS_PLDETAILSID AND PTLM_KEYID(+)=PLOS_TOOLID "
	 * );
	 * 
	 * // menuSql.append(" and plrk_hourno (+) = plos_losshrno "); //data should //
	 * savewith hour then need to add
	 * 
	 * // menuSql.append(" AND TO_CHAR('"+ Todate +"','DD-MON-YYYY') BETWEEN //
	 * PLOS_FROMTIME AND PLOS_TOTIME AND PLOS_FLID ='" + flid + "' ");
	 * menuSql.append(" AND TRUNC(PLOS_TOTIME )  BETWEEN  TO_DATE('" + Todate +
	 * "') AND TO_DATE('" + Todate + "')"); menuSql.append(" AND PLOS_FLID ='" +
	 * flid + "' "); if (UIUtils.isValidKeyId(shiftid))
	 * menuSql.append(" AND PLOS_SHIFTID ='" + shiftid + "' ");
	 * 
	 * menuSql.append(" ) ORDER BY LOSSHRNO DESC  ");
	 * 
	 * menuSql.append(" SELECT * FROM ( "); menuSql.
	 * append(" SELECT 'From','To','Mins','Loss Ref.No','Loss Reason','Loss Description','Trade','Prodn.Impact(Y/N)','Prodn.Impacted Qty(Other then Downtime)','Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No', 0 AS DATAORDER FROM DUAL "
	 * ); menuSql.
	 * append(" UNION SELECT '02:00','03:00','01:00','','','Description1','','Y','','','BDM13100013, BDM13100015','','KZ1300000023, KZ1300000025', 1 AS DATAORDER FROM DUAL "
	 * ); menuSql.
	 * append(" UNION SELECT '05:00','08:00','03:00','','','Description2','','N','','','BDM13100014','','KZ1300000022, KZ1300000026', 2 AS DATAORDER FROM DUAL "
	 * ); menuSql.
	 * append(" UNION SELECT '12:00','18:00','06:00','','','Description3','','N','','','BDM13100016, BDM13100018','','KZ1300000024, KZ1300000028, KZ1300000032', 3 AS DATAORDER FROM DUAL "
	 * ); menuSql.
	 * append(" UNION SELECT '15:00','20:00','05:00','','','Description4','','Y','','','BDM13100017','','KZ1300000027, KZ1300000029', 4 AS DATAORDER FROM DUAL "
	 * ); menuSql.
	 * append(" UNION SELECT '04:00','06:00','02:00','','','Description5','','N','','','BDM13100018, BDM13100020','','KZ1300000025, KZ1300000027', 5 AS DATAORDER FROM DUAL "
	 * ); menuSql.append(" ) ORDER BY DATAORDER ");
	 * 
	 * CommonFunctions.debugMsg("LossSql    ----:" + menuSql.toString());
	 * 
	 * List<String[]> getPillarRelMenu =
	 * dbActionTemplate.getDataList(menuSql.toString()); return getPillarRelMenu; }
	 */

	@Override
	public List<String[]> getPcsLossCaptureGrid(String flid, String Fromdate, String Todate, String shiftid)
			throws Exception {
		StringBuffer menuSql = new StringBuffer();

		menuSql.append(" SELECT DISTINCT * FROM ( ");
		menuSql.append(
				" SELECT 'PldetailsId', 'Keyid', 'Hr No' as LOSSHRNO,'From Date', 'From Time', 'To Date' ,'To Time','Minutes','LossId', 'Loss Ref.No', 'LossReasonId', 'Loss / Subloss',   'Loss Reason','Equipment','Machineid','Tool Name','ToolId', ");
		menuSql.append(" 'LossParentId','Trade', 'Prodn. Impact', 'Prodn.Imp.Qty(Other then Downtime)', ");
		menuSql.append(" ' Remarks ',' Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No.', 0 AS DATAORDER ");
		menuSql.append(" 		UNION ");
		menuSql.append(
				" 		SELECT PLOS_PLDETAILSID, PLOS_KEYID, PLOS_LOSSHRNO::text, TO_CHAR(PLOS_FROMTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_FROMTIME,'HH24:MI'), TO_CHAR(PLOS_TOTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_TOTIME,'HH24:MI'), ");
		menuSql.append(" 	PLOS_LOSSTIME::text , ");
		menuSql.append(" PLOS_LOSSID, PLCM_LOSSNO, PLRK_KEYID , PLCM_PARAMETERNAME, ");
		menuSql.append(
				" PLOS_LOSSREASON, MCHM_MACHINENAME,PLOS_MACHINEID,PTLM_NAME,PLOS_TOOLID,PLCM_PARENTID , TRDM_NAME, PLOS_PROD_IMPACT,COALESCE(PLOS_PROD_IMP_QTY,0)::text,");
		menuSql.append(" PLOS_REMARKS,");
		menuSql.append(" '' AS BTN_WHY, ");

		menuSql.append(" ( SELECT STRING_AGG(WWMS_KEYID, ', ' ORDER BY WWMS_KEYID) FROM BDM_TL_WHYWHYMST ");
		menuSql.append(" where WWMS_REFDOCNO NOT IN  ('-','{}') AND WWMS_REFDOCNO = pl.PLOS_KEYID ");
		menuSql.append(" ) ");
		menuSql.append(" , '' AS BTN_KAIZEN, ");

		menuSql.append(" ( SELECT STRING_AGG(KZBN_KEYID, ', ' ORDER BY KZBN_KEYID) FROM KZN_TL_KAIZENBANKMST ");
		menuSql.append(" where KZBN_REFDOCNO NOT IN  ('-','{}') AND KZBN_REFDOCNO = pl.PLOS_KEYID ");

		menuSql.append(" ) AS KAIZEN_REFNO, 1 AS DATAORDER");
		menuSql.append(" FROM BAL_PCS_TL_LOSSCAPTURE pl ");
		menuSql.append(" LEFT JOIN BAL_PCS_TL_LOSSPHENOMENAMST ON PLPM_KEYID = PLOS_LOSSREASON ");
		menuSql.append(" LEFT JOIN PCS_TL_LOGCONFIGURATION ON PLCM_KEYID = PLOS_LOSSID ");
		menuSql.append(" LEFT JOIN gen_tl_machinemst ON mchm_keyid = PLOS_MACHINEID ");
		menuSql.append(" LEFT JOIN GEN_TL_TRADEMST ON TRDM_KEYID = PLOS_TRADEID ");
		menuSql.append(" LEFT JOIN PCS_TL_LOSSREASONLINK ON PLRK_LOSSID = PLOS_LOSSID AND PLRK_PLDETAILID = PLOS_PLDETAILSID ");
		menuSql.append(" LEFT JOIN BAL_pcs_tl_toolsmst ON PTLM_KEYID = PLOS_TOOLID ");
		// menuSql.append(" WHERE PLOS_TOTIME::date = TO_DATE('" + Todate + "',
		// 'DD-Mon-YYYY') ");
		//menuSql.append(" WHERE TO_DATE(PLOS_TOTIME::text, 'YYYYMMDD') = TO_DATE('" + Todate + "', 'DD-Mon-YYYY') ");
		menuSql.append(" WHERE PLOS_TOTIME::date = TO_DATE('" + Todate + "', 'DD-Mon-YYYY') ");
		
		menuSql.append(" AND PLOS_FLID ='" + flid + "' ");
		if (UIUtils.isValidKeyId(shiftid))
			menuSql.append(" AND PLOS_SHIFTID ='" + shiftid + "' ");

		menuSql.append(" ) ORDER BY LOSSHRNO DESC  ");

		CommonFunctions.debugMsg("LossSql    ----:" + menuSql.toString());

		List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
		return getPillarRelMenu;
	}

	public List<String[]> getPcsResultGrid(String entryDate, String shift, String sectId, String cellId, String mchId)
			throws Exception {
		CommonFunctions.debugMsg("Inside getPcsResultGrid Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			List<String[]> resultList;

			String condParms = "ENTRYDATE=" + entryDate + ";SHIFTID=" + shift + ";SECTIONID=" + sectId + ";CELLID="
					+ cellId + ";TIMEORQTY=T;";// LOGTYPE=S;" ;
			String commonParams = "FILTERCOND=;ISTOTALCNT=Y;FROMTOROW=1 AND 100;ORDERCOL=;"; // "ISTOTALCNT="+commonFilter.getViewClick()
																								// +";FROMTOROW="+commonFilter.getFromRow()
																								// +" AND " +
																								// commonFilter.getToRow()
																								// +";";

			paramValues.add(condParms);
			paramValues.add(commonParams); //bal_pcs_fn_getresultss

			resultList = dbActionTemplate.processFunctionCalls("BAL_PCS_FN_GETRESULT", paramValues); // PCS_PC_PRODLOG
			return resultList;
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("Exception in getPcsResultGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public BAL_PcsTlMst create(BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl, PcsTlWorkorderlink pcsTlWorkorderlink,
			BAL_PcsEntryBean pcsEntryBean) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		String CycleTime = "0";
		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlMst.getPrlmSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlMst.getPrlmCellid(), pcsTlMst.getPrlmEntrydate());

		CommonFunctions.debugMsg("detailTableName" + detailTableName);

		if (!UIUtils.isValidKeyId(pcsEntryBean.getPlmasterid())) {
			pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST)); // set the
																											// sequnce
																											// number
			sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																											// insert
																											// sql for
																											// master
																											// table
		} else {
			pcsTlMst.setPrlmKeyid(pcsEntryBean.getPlmasterid());
			sqls.add(BAL_PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																											// insert
																											// sql for
																											// master
																											// table
		}

		CommonFunctions.debugMsg("Size : " + pcsTlMst.getPcsDetail().size());

		// if(pcsTlMst.getPcsDetail()!= null && pcsTlMst.getPcsDetail().size()>0) //
		// check for detail table data
		// {

		String avialTable = dbActionTemplate.getSingleValue("TAB", "TNAME", "TNAME", detailTableName);
		if (avialTable.equals(detailTableName)) {
			CommonFunctions.debugMsg("Table already exists ");
		} else {
			CommonFunctions.debugMsg("Create table for section");
			String sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL ";
			dbActionTemplate.executeStatement(sql);

			String pkConst = detailTableName.replace("PCS_TL_", "");
			sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_" + pkConst
					+ " PRIMARY KEY (PLDETAILSID) ";
			dbActionTemplate.executeStatement(sql);

			sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
			sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
			dbActionTemplate.executeStatement(sql);
		}
		CommonFunctions.debugMsg("if Loop in MST Dao Impl");
		// PcsTlDtl pcsDetail = (PcsTlDtl)pcsTlMst.getPcsDetail().get(0); // get detail
		// info from list in empployee object
		// pcsDetail.setPlmasterid(pcsTlMst.getPrlmKeyid());
		pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
		CycleTime = pcsTlDtl.getActualcycletime();
		// pcsDetail.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL));

		CommonFunctions.debugMsg(pcsEntryBean.getPldetailsid() + "pcsEntryBean.getPldetailsid()");
		if (!UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid())) {
			/*
			 * sqls.add(" UPDATE " + detailTableName +
			 * "  SET CALENDARTIME =  PRODUCTIONTIME " +
			 * " , PLANNEDQTY  = PRODUCTIONTIME  / theoriticalcycletime " +
			 * " , UNACCOUNTEDTIME = ROUND((PRODUCTIONTIME - NOPLANINMINS - (ACTUALCYCLETIME * PRODUCEDQTY)) ,2) "
			 * + "WHERE PLMASTERID = '" + pcsTlMst.getPrlmKeyid() + "' ");
			 */
			// pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(detailTableName));
			// pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(detailTableName,
			// 15, "PDE", "YY","Y"));
			pcsTlDtl.setPldetailsid(
					dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
			/*
			 * String PlanQTY =
			 * dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST,
			 * "CNFM_SETTINGVALUE", "CNFM_CODE", "FETCHPLANQTY");
			 * if(UIUtils.isValidKeyId(PlanQTY)) { if(PlanQTY.equals("Y")) { String pq =
			 * dbActionTemplate.
			 * getSingleValue("SELECT PRPL_PLANQTY FROM PCS_TL_PRODUCTIONPLAN WHERE PRPL_PRODUCTID='"
			 * +pcsTlDtl.getProductid()+"'");
			 * CommonFunctions.debugMsg("Production Plan Plan Qty : "+pq);
			 * if(UIUtils.isValidKeyId(pq)) { pcsTlDtl.setPlannedqty(pq); } } }
			 */
			// If (Not ObjGenUtils.CheckSequence(GlbPLDetailsTable, 15, "PDE", 1, 1, "YY",
			// True, True)) Then
			CommonFunctions.debugMsg("pcsTlDtlSql.getPcs_DbFields()" + pcsTlDtlSql.getPcs_DbFields().toString()
					+ "pcsTlDtl.getSaveArray()" + pcsTlDtl.getSaveArray().length);

			sqls.add(pcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add
																														// insert
																														// sql
																														// for
																														// detail
																														// table

		} else {
			pcsTlDtl.setPldetailsid(pcsEntryBean.getPldetailsid());
			sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add
																														// insert
																														// sql
																														// for
																														// detail
																														// table
		}

		/*
		 * pcsTlWorkorderlink.setPtwoPldetailid(pcsTlDtl.getPldetailsid());
		 * pcsTlWorkorderlink.setPtwoMasterid(pcsTlMst.getPrlmKeyid());
		 * 
		 * if (! UIUtils.isValidKeyId(pcsEntryBean.getPtwokeyid())) { StringBuffer
		 * sql_str = new StringBuffer();
		 * 
		 * sqls.
		 * add(" UPDATE PCS_TL_WORKORDERLINK  SET PTWO_CALENDARTIME =  PTWO_PRODUCTIONTIME "
		 * + " , PTWO_PLANNEDQTY  = PTWO_PRODUCTIONTIME  / PTWO_theoriticalcycletime " +
		 * " , PTWO_UNACCOUNTEDTIME = ROUND((PTWO_PRODUCTIONTIME - PTWO_NOPLANINMINS - (PTWO_ACTUALCYCLETIME * PTWO_PRODUCEDQTY)) ,2) "
		 * + "WHERE PTWO_MASTERID = '" + pcsTlMst.getPrlmKeyid() + "' ");
		 * 
		 * pcsTlWorkorderlink.setPtwoKeyid(dbActionTemplate.getSequenceNumber(
		 * PcsTlWorkorderlinkSql.TBL_PCS_TL_WORKORDERLINK, 15, "PWO", "YY","Y"));
		 * 
		 * sqls.add(pcsTlWorkorderlinkSql.getInsertSql(pcsTlWorkorderlinkSql.
		 * getPtwoDbFields(), pcsTlWorkorderlink.getSaveArray()));// add insert sql for
		 * detail table
		 * 
		 * } else { pcsTlWorkorderlink.setPtwoPldetailid(pcsEntryBean.getPldetailsid());
		 * pcsTlWorkorderlink.setPtwoMasterid(pcsEntryBean.getPlmasterid());
		 * pcsTlWorkorderlink.setPtwoKeyid(pcsEntryBean.getPtwokeyid());
		 * 
		 * sqls.add(pcsTlWorkorderlinkSql.getUpdateSql(pcsTlWorkorderlinkSql.
		 * getPtwoDbFields(), pcsTlWorkorderlink.getSaveArray()));// add insert sql for
		 * detail table //sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName,
		 * pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql
		 * for detail table }
		 * 
		 * StringBuffer sql_str1 = new StringBuffer();
		 * 
		 * sql_str1.append(" UPDATE " + detailTableName +
		 * " SET WNO = (SELECT DISTINCT PCS_PC_PRODLOG.PCS_FN_MERGEWORKORDER('PTWO_WNO','"
		 * + pcsTlDtl.getPldetailsid() + "') as WNO FROM PCS_TL_WORKORDERLINK) ");
		 * sql_str1.append(" WHERE PLDETAILSID='" + pcsTlDtl.getPldetailsid() + "' ");
		 * sqls.add(sql_str1.toString());
		 * 
		 */

		// dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		// getOEECalculation(detailTableName,pcsTlDtl.getPldetailsid());

		// bulk exexute
		runBulkSqls(sqls, detailTableName, pcsTlDtl.getPldetailsid(), CycleTime, pcsTlMst.getPrlmFlid());

		return pcsTlMst;

	}

	public BAL_PcsTlMst createNew(BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl, PcsTlWorkorderlink pcsTlWorkorderlink,
			BAL_PcsEntryBean pcsEntryBean) throws Exception, BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>();
		String CycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlMst.getPrlmCellid(), pcsTlMst.getPrlmEntrydate());
		CommonFunctions.debugMsg(" In DAO Impl......... " + pcsTlDtl.getLoss21());
		CommonFunctions.debugMsg(pcsEntryBean.getPlmasterid() + "DetailTableName :......... " + detailTableName);

		if (!UIUtils.isValidKeyId(pcsEntryBean.getPlmasterid())) {
			pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST)); // set the
																											// sequnce
																											// number
			sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																											// insert
																											// sql for
																											// master
																											// table
			CommonFunctions.debugMsg("sql Query in daoimpl   " + sqls);
		} else {
			pcsTlMst.setPrlmKeyid(pcsEntryBean.getPlmasterid());
			pcsTlMst.setPrlmLogtype(pcsEntryBean.getShiftWise());

			CommonFunctions.debugMsg("sql Query in pcsEntryBean.getShiftWise()   " + pcsEntryBean.getShiftWise());

			sqls.add(BAL_PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																											// insert
																											// sql for
																											// master
																											// table
		}

		CommonFunctions.debugMsg("Size......... : " + pcsTlMst.getPcsDetail().size());

		String existTable = dbActionTemplate.getSingleValue("TAB", "TNAME", "TNAME", detailTableName);
		if (!existTable.equals(detailTableName)) {
			CommonFunctions.debugMsg("Create table for cell");
			String sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL ";
			dbActionTemplate.executeStatement(sql);

			String pkConst = detailTableName.replace("PCS_TL_", "");
			sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_" + pkConst
					+ " PRIMARY KEY (PLDETAILSID) ";
			dbActionTemplate.executeStatement(sql);

			sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
			sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
			dbActionTemplate.executeStatement(sql);
		}

		CommonFunctions.debugMsg("pcsEntryBean.getPldetailsid() : " + pcsEntryBean.getPldetailsid());
		pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
		CycleTime = pcsTlDtl.getActualcycletime();

		if (!UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid())) {
			String count = dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM " + detailTableName
					+ " WHERE MACHINEID='" + pcsTlDtl.getMachineid() + "' AND PRODUCTID='" + pcsTlDtl.getProductid()
					+ "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'");
			if (UIUtils.isValidKeyId(count)) {
				if (Integer.parseInt(count) > 0) {
					String dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM " + detailTableName
							+ " WHERE MACHINEID='" + pcsTlDtl.getMachineid() + "' AND PRODUCTID='"
							+ pcsTlDtl.getProductid() + "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'");
					if (UIUtils.isValidKeyId(dtlId)) {
						pcsTlDtl.setPldetailsid(dtlId);
						pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
						sqls.add(BAL_PcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
								pcsTlDtl.getSaveArray()));// add insert sql for detail table
						pcsTlMst.setPrlmActive("U");
					}
				} else {
					CommonFunctions.debugMsg("Planned Qty Before Calculations : " + pcsTlDtl.getPlannedqty());
					CommonFunctions.debugMsg("Calendar Time Before Calculations : " + pcsTlDtl.getCalendartime());
					pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
					CommonFunctions.debugMsg("Planned Qty After Calculations : " + pcsTlDtl.getPlannedqty());
					CommonFunctions.debugMsg("Calendar Time After Calculations : " + pcsTlDtl.getCalendartime());
					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL, 15,
							"PDE", "YY", "Y"));
					sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table
					// pcsTlMst.setPrlmActive("I");
				}
			} else {
				pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
				pcsTlDtl.setPldetailsid(
						dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
				sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
						pcsTlDtl.getSaveArray()));// add insert sql for detail table
			}
		}

		else {
			pcsTlDtl.setPldetailsid(pcsEntryBean.getPldetailsid());
			pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
			sqls.add(BAL_PcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray()));// add insert sql for detail table
		}

		runBulkSqls(sqls, detailTableName, pcsTlDtl.getPldetailsid(), CycleTime, pcsTlMst.getPrlmFlid());

		return pcsTlMst;

	}

	public void pcsValidations(BAL_PcsTlDtl newPcsTlDtl, BAL_PcsTlMst pcsTlMst, BAL_PcsEntryBean pcsEntryBean)
			throws Exception {
		CommonFunctions.debugMsg("before check");
		String prodQty = newPcsTlDtl.getProducedqty();
		if (!UIUtils.isValidKeyId(prodQty))
			prodQty = "0";
		CommonFunctions.debugMsg("prodQty" + prodQty);

		String cavityUsed = newPcsTlDtl.getCavityused();
		if (!UIUtils.isValidKeyId(cavityUsed))
		//	cavityUsed = "0";
		    cavityUsed = "1";
		CommonFunctions.debugMsg("cavityUsed" + cavityUsed);

		String actTime = newPcsTlDtl.getActualcycletime();
		if (!UIUtils.isValidKeyId(actTime))
			actTime = "0";
		CommonFunctions.debugMsg("actTime" + actTime);
		String shiftWise = pcsTlMst.getPrlmTempfield2();
		String availTimeStr = getCalendarTime(newPcsTlDtl.getMachineid(), pcsEntryBean.getPlmasterid(),
				pcsTlMst.getPrlmSectionid(), pcsTlMst.getPrlmEntrydate(), shiftWise);
		CommonFunctions.debugMsg("availTime" + availTimeStr);

		Float availTime = Float.parseFloat(availTimeStr);
		if (Float.parseFloat(availTimeStr) < 0)
			availTime = (float) 0.0;

		// String tableName =
		// getDetailTableName(pcsTlMst.getPrlmCellid(),pcsTlMst.getPrlmEntrydate());
		String oldTime = pcsEntryBean.getOldTime();// dbActionTemplate.getSingleValue(tableName, "PRODUCEDQTY",
													// "PLDETAILSID", newPcsTlDtl.getPldetailsid());

		CommonFunctions.debugMsg("oldTime : " + oldTime);
		if (UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid()))
			availTime = availTime + Float.parseFloat(oldTime);

		CommonFunctions.debugMsg("availTime=" + availTime);

		String cycleTime = newPcsTlDtl.getActualcycletime();
		String theoTime = newPcsTlDtl.getTheoriticalcycletime();
		if (!UIUtils.isValidKeyId(cycleTime))
			cycleTime = "0";
		String cavityAvail = newPcsTlDtl.getCavityavailable();
		if (!UIUtils.isValidKeyId(cavityAvail))
			cavityAvail = "0";
		CommonFunctions.debugMsg("cycleTime=" + cycleTime);
		Float planQty;

		if (cycleTime.equals("0"))
			planQty = (float) 0.0;
		else
			planQty = (availTime / Float.parseFloat(cycleTime)) * Float.parseFloat(cavityAvail);

		CommonFunctions.debugMsg("planQty=" + planQty);

		newPcsTlDtl.setPlannedqty(planQty.toString());
		newPcsTlDtl.setCalendartime(availTime.toString());
		CommonFunctions.debugMsg("Planned Qty In Calculations : " + newPcsTlDtl.getPlannedqty());
		CommonFunctions.debugMsg("Calendar Time In Calculations : " + newPcsTlDtl.getCalendartime());
		Float prodTime = (Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
		CommonFunctions.debugMsg("prodTime" + prodTime);

		CommonFunctions.debugMsg("Math.floor(prodTime)=====" + Math.floor(prodTime));
		CommonFunctions.debugMsg("Math.floor(availTime)====" + Math.floor(availTime));

		if (prodTime > availTime)

			throw new BusinessApplicationExceptions("productionTime_Greate");

		/*
		 * if (Float.parseFloat(cavityUsed) > Float.parseFloat(cavityAvail)) throw new
		 * Exception(" 'Cavity Used' Can not be greater than Cavity Available");
		 * 
		 * if (Float.parseFloat(cavityUsed) < 1) throw new
		 * Exception(" 'Cavity Used' Should be atleast 1");
		 */

		String actTimePct = getActTimePct();
		// dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST",
		// "CNFM_SETTINGVALUE", "CNFM_CODE", "ACTIMEPCT");
		int actPct = Integer.parseInt(actTimePct);
		CommonFunctions.debugMsg("(actPct %" + actPct);
		CommonFunctions.debugMsg("(theoTime %" + theoTime);
		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
		CommonFunctions.debugMsg("(time25%" + timePct);
		Float timepctVal = (float) 0;

		NumberFormat df = DecimalFormat.getInstance();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.UP);

		timepctVal = (Float.parseFloat(theoTime) + timePct);
		CommonFunctions.debugMsg("(timepctVal" + timepctVal);
		timepctVal = Float.parseFloat(df.format(timepctVal));

		if (Float.parseFloat(cycleTime) > (Float.parseFloat(theoTime) + timePct))
			throw new Exception(" Error in 'Actual Cycle Time' " + "<br>" + " (Exceeds " + actPct
					+ "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " + timepctVal + ")");

		timepctVal = (Float.parseFloat(theoTime) - timePct);

		timepctVal = Float.parseFloat(df.format(timepctVal));

		if (Float.parseFloat(cycleTime) < (Float.parseFloat(theoTime) - timePct))
			throw new Exception(" Error in 'Actual Cycle Time'  " + "<br>" + " (Less " + actPct
					+ "% of Theoritical Cycle Time = " + actPct + "% of " + theoTime + " = " + timepctVal + ")");

	}

	public void runBulkSqls(List<String> sqls, String detailTableName, String pldetailsId, String CycleTime,
			String flid) throws Exception {
		try {
			List<String> bulk_sqls = new ArrayList<String>();
			List<Object[]> valueList = new ArrayList<Object[]>();
			List<int[]> dataTypes = new ArrayList<int[]>();
			List<String> charList = new ArrayList<String>();
			// int [] dataTypesBD = { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
			for (int i = 0; i < sqls.size(); i++) {
				String insertPCSSql = sqls.get(i);
				Object[] insertPCSDatas = {};
				int[] insertPCSTypes = {};
				bulk_sqls.add(insertPCSSql);
				valueList.add(insertPCSDatas);
				dataTypes.add(insertPCSTypes);
				charList.add("Q");
			}
			String insertPCSSql ="bal_pcs_fn_updatepldtl";// "PCS_FN_UPDATEPLDTL";
			Object[] insertPCSDatas = { detailTableName, pldetailsId, CycleTime, "Y", "N", flid };
			CommonFunctions.debugMsg(" PCS_FN_UPDATEPLDTL : detailTableName : " + detailTableName
					+ " pldetailsId " + pldetailsId + "  Availtime (cycletime) " + CycleTime);
			int[] insertPCSTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
			bulk_sqls.add(insertPCSSql);
			valueList.add(insertPCSDatas);
			dataTypes.add(insertPCSTypes);
			charList.add("P");

			char[] sqlType = new char[charList.size()];

			for (int c = 0; c < sqlType.length; c++) {
				sqlType[c] = charList.get(c).charAt(0);
			}
			dbActionTemplate.executeStatement(bulk_sqls, valueList, dataTypes, sqlType);

		} catch (Exception e)

		{
			e.printStackTrace();
			if (e.getMessage().equals("-2")) {
				String tolrance = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
						"CNFM_CODE", "MAXROP");
				int tolval = Integer.parseInt(tolrance) + 100;
				String tolmsg = "ROP is greater than " + tolval + "%";
				throw new Exception(tolmsg.toString());
			} else if (e.getMessage().equals("-3")) {
				String unrepTime = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
						"CNFM_CODE", "MAXUNREPORTED");

				if (UIUtils.isValidKeyId(unrepTime) && UIUtils.isValidKeyId(CycleTime)) {
					if (Double.parseDouble(CycleTime) > Integer.parseInt(unrepTime))
						unrepTime = CycleTime;
				}
				// String unrepmsg = "UNREPORTED TIME EXCEEDS " + unrepTime + " Mins";
				String unrepmsg = "Unreported time exceeds " + unrepTime + " Mins";
				e.printStackTrace();
				throw new Exception(unrepmsg.toString());
			}
			//uncommented by elumalai
			  else throw new Exception( e.getMessage());
			 

		}
	}

	public PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl pcsTlOperatordtl, String Plmasterid, String Pldetailsid)
			throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonFunctions.debugMsg("Plmasterid   =====" + Plmasterid);
		pcsTlOperatordtl.setPopdPldetailsid(Pldetailsid);
		pcsTlOperatordtl.setPopdPlmasterid(Plmasterid);
		// pcsTlOperatordtl.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK));
		// // set the sequnce number
		sqls.add(pcsTlOperatordtlSql.getInsertSql(pcsTlOperatordtlSql.getPopdDbFields(),
				pcsTlOperatordtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		return pcsTlOperatordtl;
	}

	public List<String[]> getPcsMstDtlids(BAL_PcsTlLosscapture newPcsTlLosscapture, BAL_PcsTlMst pcsTlMst,
			BAL_PcsTlDtl pcsTlDtl) throws Exception {

		StringBuffer sb = new StringBuffer();
		CommonFunctions.debugMsg("cellidindao" + pcsTlDtl.getCellid());
//		/*
//		 * String sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID ='"
//		 * + pcsTlDtl.getCellid() + "' "; String sectId =
//		 * dbActionTemplate.getSingleValue(sql); String detailTableName =
//		 * getDetailTableName(pcsTlDtl.getCellid(), pcsTlMst.getPrlmEntrydate()); //
//		 * String detailTableName =getDetailTableName(sectId, //
//		 * pcsTlMst.getPrlmEntrydate());
//		 * 
//		 * sb.append(" SELECT PRLM_KEYID, MIN(PLDETAILSID) FROM BAL_PCS_TL_MST, " +
//		 * detailTableName + " "); sb.append(" WHERE TRUNC(PRLM_ENTRYDATE) ='" +
//		 * newPcsTlLosscapture.getPlosDate() + "' AND PRLM_SHIFTID = '" +
//		 * newPcsTlLosscapture.getPlosShiftid() + "' "); sb.append(" AND PRLM_FLID='" +
//		 * newPcsTlLosscapture.getPlosFlid() + "' ");
//		 * sb.append(" AND PLMASTERID (+)= PRLM_KEYID GROUP BY PRLM_KEYID ");
//		 * CommonFunctions.debugMsg("getPcsMstDtlids===" + sb.toString());
//		 */
		
		String sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID ='" + pcsTlDtl.getCellid() + "' ";
		String sectId = dbActionTemplate.getSingleValue(sql);
		String detailTableName = getDetailTableName(pcsTlDtl.getCellid(), pcsTlMst.getPrlmEntrydate());

		sb.append(" SELECT PRLM_KEYID, MIN(PLDETAILSID) FROM BAL_PCS_TL_MST ");
		sb.append(" LEFT JOIN " + detailTableName + " ON PLMASTERID = PRLM_KEYID ");
		sb.append(" WHERE PRLM_ENTRYDATE::date = TO_DATE('" + newPcsTlLosscapture.getPlosDate() + "', 'DD-Mon-YYYY') ");
		sb.append(" AND PRLM_SHIFTID = '" + newPcsTlLosscapture.getPlosShiftid() + "' ");
		sb.append(" AND PRLM_FLID = '" + newPcsTlLosscapture.getPlosFlid() + "' ");
		sb.append(" GROUP BY PRLM_KEYID ");
		CommonFunctions.debugMsg("getPcsMstDtlids===" + sb.toString());
		return dbActionTemplate.getDataList(sb.toString());

	}

	public BAL_PcsTlLosscapture createLossCapture(BAL_PcsTlLosscapture pcsTlLosscapture, BAL_PcsTlMst pcsTlMst,
			BAL_PcsTlDtl pcsTlDtl, BAL_PcsTlLossreasonlink pcsTlLossreasonlink, BAL_PcsEntryBean pcsEntryBean)
			throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlLosscaptureSql pcsTlLosscaptureSql = new BAL_PcsTlLosscaptureSql();
		CommonFunctions.debugMsg("pcsTlLossreasonlink()===" + pcsTlMst.getPrlmFactoryid());

		// CommonFunctions.debugMsg("pcsTlLosscapture.getPlospldetailsid()" +
		// pcsTlLosscapture.getPlospldetailsid());

		List<String> paramValues = new ArrayList<String>();

		ArrayList<String> pcsdtl = new ArrayList<String>();

		CommonFunctions.debugMsg(
				pcsTlDtl.getPldetailsid() + "pcsTlLosscapture.getPlosFromtime() " + pcsTlLosscapture.getPlosFromtime()
						+ " pcsTlLosscapture.getPlosTotime() " + pcsTlLosscapture.getPlosTotime());

		String dfromtime = pcsTlLosscapture.getPlosFromtime();
		String dtoTime = pcsTlLosscapture.getPlosTotime();
		if (dfromtime.length() == 16)
			dfromtime = dfromtime.substring(11, dfromtime.length());
		else
			dfromtime = dfromtime.substring(12, dfromtime.length());

		if (dtoTime.length() == 16)
			dtoTime = dtoTime.substring(11, dtoTime.length());
		else
			dtoTime = dtoTime.substring(12, dtoTime.length());

		String[] dotim = dfromtime.split(":");

		CommonFunctions.debugMsg("dotim[0] " + dotim[0] + " length " + dotim[0].length() + "dotim[1] " + dotim[1]
				+ " lenght " + dotim[1].length());

		if (dotim[0].length() == 1) {
			dotim[0] = "0" + dotim[0];
		}
		if (dotim[1].length() == 1) {
			dotim[1] = "0" + dotim[1];
		}
		CommonFunctions.debugMsg("dotim[1]===" + dotim[1]);

		if (dotim[0].equals("06") && dotim[1].equals("00")) {
			dotim[1] = "01";
		}

		CommonFunctions.debugMsg("dotim[1]===" + dotim[1]);

		dfromtime = dotim[0] + ":" + dotim[1];

		String[] dototim = dtoTime.split(":");
		if (dototim[0].length() == 1) {
			dototim[0] = "0" + dototim[0];
		}
		if (dototim[1].length() == 1) {
			dototim[1] = "0" + dototim[1];

		}

		if (dototim[0].equals("06") && dototim[1].equals("00")) {
			dototim[0] = "05";
			dototim[1] = "59";
		}

		dtoTime = dototim[0] + ":" + dototim[1];

		CommonFunctions.debugMsg("fromtime===" + dfromtime);
		CommonFunctions.debugMsg("totime===" + dtoTime);

		// String fromTime = dfromtime.substring(12, dfromtime.length());
		// String toTime = dtoTime.substring(12, dtoTime.length());

		// CommonFunctions.debugMsg("fromtime totime" + fromTime + toTime );
		paramValues.add(dfromtime);
		paramValues.add(dtoTime);
		paramValues.add(pcsTlLosscapture.getPlosLosstime());
		paramValues.add(pcsTlMst.getPrlmFactoryid());

		CommonFunctions.debugMsg("Noofshift.size()______,_________" + paramValues.toString());

		List<String[]> Noofshift = dbActionTemplate.processFunctionCalls("PCS_FN_PRODLOSSSHIFTCAL",
				paramValues);
		// String[] Noofshiftdtl = new String[];

		CommonFunctions.debugMsg(Noofshift.toString() + "Noofshift.size()" + Noofshift.size());

		// String sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID
		// ='"+ pcsTlDtl.getCellid() + "' ";
		// String sectId =dbActionTemplate.getSingleValue(sql);

		String detailTableName = getDetailTableName(pcsTlDtl.getCellid(), pcsTlMst.getPrlmEntrydate());

		CommonFunctions.debugMsg(" detailTableName==" + detailTableName);
		CommonFunctions.debugMsg("pcsTlLosscapture.getPlospldetailsid()" + pcsTlLosscapture.getPlospldetailsid());

		String Plospldetailsid = pcsTlLosscapture.getPlospldetailsid();
		CommonFunctions.debugMsg("Plospldetailsid" + Plospldetailsid + " Noofshift.size()" + Noofshift.size());
		String Onedateplus = "";
		for (int i = 0; i < Noofshift.size(); i++) {

			CommonFunctions.debugMsg("In for loop" + i + "   onedateplus" + Onedateplus);
			pcsTlMst.setPrlmShiftid(Noofshift.get(i)[0]);
			String Shiftid = Noofshift.get(i)[0];
			CommonFunctions.debugMsg("Shiftid" + Shiftid);
			if (i == 0) {
				Onedateplus = pcsTlLosscapture.getPlosFromtime();
				Onedateplus = Onedateplus.substring(0, 11);
				CommonFunctions.debugMsg(" Onedateplus,.. " + Onedateplus);
				if (Shiftid.equals("SFT001") || Shiftid.equals("SFT008")) {
					String frmdate = Onedateplus;
				// By kiran	String Sql = "Select to_char(to_date('" + frmdate + "','DD-MON-YYYY')-1 , 'DD-MON-YYYY') FROM DUAL";
					String Sql = "SELECT TO_CHAR(TO_DATE('" + frmdate + "', 'DD-MON-YYYY') - 1, 'DD-MON-YYYY')";
					Onedateplus = dbActionTemplate.getSingleValue(Sql);
					CommonFunctions.debugMsg(" Onedateplus " + Onedateplus);
					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);
				} else {
					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);
				}
				// pcsTlMst.setPrlmDate(Onedateplus);
				// pcsTlMst.setPrlmEntrydate(Onedateplus);
				CommonFunctions.debugMsg("Noofshift.get(i)[0]" + Noofshift.get(i)[0]);
			} else {
				CommonFunctions.debugMsg("Noofshift.get(i)[0]" + Noofshift.get(i)[0]);
				if (Shiftid.equals("SFT001") || Shiftid.equals("SFT008")) {
					String frmdate = Onedateplus;
					// frmdate = frmdate.substring(0,11);
					// String Sql = "Select to_char(to_date('"+frmdate+"','DD-MON-YYYY')+ 1 ,
					// 'DD-MON-YYYY') FROM DUAL";
				//	By Kiran String Sql = "Select to_char(to_date('" + frmdate + "','DD-MON-YYYY') , 'DD-MON-YYYY') FROM DUAL";
					String Sql = "SELECT TO_CHAR(TO_DATE('" + frmdate + "', 'DD-MON-YYYY') - 1, 'DD-MON-YYYY')";
					// CommonFunctions.debugMsg("Noofshift.get(i)[0] Sql" + Sql);
					Onedateplus = dbActionTemplate.getSingleValue(Sql);

					CommonFunctions.debugMsg(" Onedateplus " + Onedateplus);

					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);

					// CommonFunctions.debugMsg(" PrlmDate inside " + pcsTlMst.getPrlmDate() +
					// pcsTlMst.getPrlmEntrydate() + " " + Onedateplus);
				} else {
					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);
				}
			}

			CommonFunctions.debugMsg(" PrlmDate " + pcsTlMst.getPrlmDate() + pcsTlMst.getPrlmEntrydate());

			pcsTlDtl.setCalendartime(Noofshift.get(i)[2]);
			// if (! UIUtils.isValidKeyId( pcsTlMst.getPrlmKeyid())) {
			// CommonFunctions.debugMsg("pcsTlLosscapture.getPlospldetailsid() " +
			// pcsTlLosscapture.getPlospldetailsid() );

			pcsTlLosscapture.setPlosPldetailsid(Plospldetailsid);

			if (pcsTlLosscapture.getPlospldetailsid().equals("false"))
				pcsTlLosscapture.setPlosPldetailsid("");

			// CommonFunctions.debugMsg("pcsTlLosscapture.getPlospldetailsid() " +
			// pcsTlLosscapture.getPlospldetailsid() );

			// if (! UIUtils.isValidKeyId ( pcsTlLosscapture.getPlospldetailsid())) {

			// CommonFunctions.debugMsg("Inside mst dtl insert ");
			
			

			String Checksql = " select count(*) from BAL_PCS_TL_MST where prlm_entrydate::date = TO_DATE('"
					+ pcsTlMst.getPrlmEntrydate() + "','DD-MON-YYYY HH24:MI')  and PRLM_SHIFTID = '"
					+ pcsTlMst.getPrlmShiftid() + "'";
			Checksql = Checksql + " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '"
					+ pcsTlMst.getPrlmCellid() + "'  ";
//				Checksql = Checksql + " and PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() + "'  and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' " ;

			CommonFunctions.debugMsg("Checksql " + Checksql);
			String checkcount = dbActionTemplate.getSingleValue(Checksql);

			CommonFunctions.debugMsg("checkcount" + checkcount);
			if (Integer.parseInt(checkcount) > 0) {

				String Checksqlkeyid = " select PRLM_KEYID from BAL_PCS_TL_MST where PRLM_ENTRYDATE::date = TO_DATE('"
						+ pcsTlMst.getPrlmEntrydate() + "','DD-MON-YYYY HH24:MI')  and PRLM_SHIFTID = '"
						+ pcsTlMst.getPrlmShiftid() + "'";
				Checksqlkeyid = Checksqlkeyid + " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid()
						+ "'  and PRLM_CELLID = '" + pcsTlMst.getPrlmCellid() + "' ";
				// Checksqlkeyid = Checksqlkeyid + " and PRLM_SUBGROUPID = '" +
				// pcsTlMst.getPrlmSubgroupid() + "' and PRLM_MACHINEID = '" +
				// pcsTlMst.getPrlmMachineid() + "' " ;

				String id = dbActionTemplate.getSingleValue(Checksqlkeyid);
				pcsTlMst.setPrlmKeyid(id);
				sqls.add(BAL_PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table
				List<String[]> NoofHours = getLossHourNo(Shiftid, dfromtime, dtoTime);
				int totalNoofLosshours = NoofHours.size();
				CommonFunctions.debugMsg("noofhoursize  " + totalNoofLosshours);
				for (int j = 0; j < totalNoofLosshours; j++) {

					/*
					 * CommonFunctions.debugMsg("ifnoofhour"+NoofHours.get(j)[0]);
					 * CommonFunctions.debugMsg("ifnoofhour"+NoofHours.get(j)[1]);
					 * CommonFunctions.debugMsg("ifnoofhour"+NoofHours.get(j)[2]);
					 * CommonFunctions.debugMsg("ifnoofhour"+NoofHours.get(j)[3]);
					 * CommonFunctions.debugMsg("ifnoofhour"+NoofHours.get(i)[4]);
					 */

					String hourno = NoofHours.get(j)[3];
					String houravailtime = NoofHours.get(j)[4];
					CommonFunctions.debugMsg("houravailtime 0 :: " + houravailtime + " getProductionavltime"
							+ pcsTlDtl.getProductionavltime());
					if (Integer.parseInt((pcsTlDtl.getProductionavltime())) > 60) {
						houravailtime = pcsTlDtl.getProductionavltime();
						CommonFunctions.debugMsg("houravailtime 1 :: " + houravailtime + " getProductionavltime"
								+ pcsTlDtl.getProductionavltime());
					} else {
						houravailtime = NoofHours.get(j)[4];
						CommonFunctions.debugMsg("houravailtime 2 :: " + houravailtime);
					}

					String hourStarttime = NoofHours.get(j)[1];
					String hourEndtime = NoofHours.get(j)[2];

					int lossreasonminutes = lossReasonMinutes(j, totalNoofLosshours, hourStarttime, hourEndtime,
							dfromtime, dtoTime);

					// String plrkminutes =Integer.toString(lossreasonminutes);
					String plrkminutes = pcsTlLosscapture.getPlosLosstime();
					CommonFunctions.debugMsg(pcsTlMst.getPrlmKeyid() + "plrkminutes :: " + plrkminutes);
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					String dtlId;
					String sql = "SELECT MAX(PLDETAILSID) FROM " + detailTableName + " WHERE MACHINEID='" + hourno
							+ "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'";

					CommonFunctions.debugMsg(pcsTlMst.getPrlmLogtype() + ".....sql...." + sql);
					if (pcsTlMst.getPrlmLogtype() == "S") {
						hourno = "0";
						dtlId = dbActionTemplate
								.getSingleValue("SELECT MAX(PLDETAILSID) FROM " + detailTableName + " WHERE MACHINEID='"
										+ hourno + "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'");
					} else {
						dtlId = dbActionTemplate
								.getSingleValue("SELECT MAX(PLDETAILSID) FROM " + detailTableName + " WHERE MACHINEID='"
										+ hourno + "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'");

					}
					CommonFunctions.debugMsg("detailid" + dtlId);
					if (pcsTlMst.getPrlmLogtype() == "S") {
						pcsTlDtl.setMachineid("0");

					} else {
						pcsTlDtl.setMachineid(hourno);
					}
					// pcsTlDtl.setCalendartime(houravailtime);
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					int unaccountedtime = Integer.parseInt(houravailtime) - lossreasonminutes;

					CommonFunctions.debugMsg("unaccountedtime ? " + unaccountedtime + ", houravailtime " + houravailtime
							+ ", " + pcsTlDtl.getCalendartime());
					// pcsTlDtl.setUnaccountedtime(String.valueOf(unaccountedtime));
					// pcsTlDtl.setProductionavltime(String.valueOf(unaccountedtime));
					// pcsTlDtl.setMchavailabletime(String.valueOf(unaccountedtime));
					// CommonFunctions.debugMsg("detailid"+dtlId);
					if (!UIUtils.isValidKeyId(dtlId)) {
						pcsTlDtl.setUnaccountedtime(String.valueOf(unaccountedtime));
						pcsTlDtl.setMchavailabletime(String.valueOf(unaccountedtime));
						// pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
						pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,
								15, "PDE", "YY", "Y"));
						dtlId = pcsTlDtl.getPldetailsid();

						sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
								pcsTlDtl.getSaveArray()));// add insert sql for detail table

					} else {
						pcsTlDtl.setPldetailsid(dtlId);
						sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
								pcsTlDtl.getSaveArray()));// add insert sql for detail table

					}
					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());
					pcsTlDtl.setPldetailsid(dtlId);
					CommonFunctions.debugMsg("pcsTlLosscapture.getPlosKeyid() " + pcsTlLosscapture.getPlosKeyid());
					// if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
					if (Integer.parseInt(checkcount) > 0) {
						pcsTlDtl.setPldetailsid(dtlId);
						// CommonFunctions.debugMsg(" In If csTlLosscapture.getPlosKeyid() 1 " +
						// pcsTlDtl.getPldetailsid());
						// pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);

						pcsTlLossreasonlink.setPlrkMinutes(plrkminutes);
						pcsTlLossreasonlink.setPlrkCellid(pcsTlMst.getPrlmCellid());
						pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
						pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
						pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());
						pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());

						sqls.addAll(createLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
								pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

						// pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","+
						// pcsTlLossreasonlink.getPlrkMinutes() );
						pcsdtl.add(
								detailTableName + "," + pcsTlDtl.getPldetailsid() + "," + pcsTlDtl.getCalendartime());
					} else {
						// CommonFunctions.debugMsg(" In Else csTlLosscapture.getPlosKeyid()
						// 1"+pcsTlDtl.getPldetailsid());
						pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
						pcsTlLossreasonlink.setPlrkMinutes(plrkminutes);
						pcsTlLossreasonlink.setPlrkCellid(pcsTlMst.getPrlmCellid());
						pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
						pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
						pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());
						// pcsTlLossreasonlink.setPlrkKeyid(PcsEntr)

						sqls.addAll(updateLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
								pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

						// pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","+
						// pcsTlLossreasonlink.getPlrkMinutes() );
						pcsdtl.add(
								detailTableName + "," + pcsTlDtl.getPldetailsid() + "," + pcsTlDtl.getCalendartime());
					}

				}

				// pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
				// sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName,
				// pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql
				// for detail table
				// pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());
				// CommonFunctions.debugMsg("sqls for Mst Dtl " + sqls.toString() );
			} else {

				pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST)); // set
																												// the
																												// sequnce
																												// number
				// PcsTlMstSql.getUpdateSql(fieldTypeArr, dataArray)
				sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table

				List<String[]> NoofHours = getLossHourNo(Shiftid, dfromtime, dtoTime);
				int totalNoofLosshours = NoofHours.size();
				CommonFunctions.debugMsg("noofhoursize" + totalNoofLosshours);
				for (int j = 0; j < totalNoofLosshours; j++) {
					// CommonFunctions.debugMsg("noofhour"+NoofHours.get(j)[0]);
					// CommonFunctions.debugMsg("noofhour"+NoofHours.get(j)[1]);
					// CommonFunctions.debugMsg("noofhour"+NoofHours.get(j)[2]);
					// CommonFunctions.debugMsg("noofhour"+NoofHours.get(j)[3]);
					// CommonFunctions.debugMsg("noofhour"+NoofHours.get(j)[4]);

					// CommonFunctions.debugMsg("noofhour"+j);
					String hourno = NoofHours.get(j)[3];
					String houravailtime = NoofHours.get(j)[4];

					String hourStarttime = NoofHours.get(j)[1];
					String hourEndtime = NoofHours.get(j)[2];

					int lossreasonminutes = lossReasonMinutes(j, totalNoofLosshours, hourStarttime, hourEndtime,
							dfromtime, dtoTime);
					String plrkminutes = Integer.toString(lossreasonminutes);
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());

					String dtlId = dbActionTemplate.getSingleValue("SELECT MAX(PLDETAILSID) FROM " + detailTableName
							+ " WHERE MACHINEID='" + hourno + "' AND PLMASTERID='" + pcsTlDtl.getPlmasterid() + "'");
					if (pcsTlMst.getPrlmLogtype() == "Y") {
						pcsTlDtl.setMachineid("0");

					} else {
						pcsTlDtl.setMachineid(hourno);
					}
					// pcsTlDtl.setCalendartime(houravailtime);
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					int unaccountedtime = Integer.parseInt(houravailtime) - lossreasonminutes;

					CommonFunctions
							.debugMsg("unaccountedtime 2 " + unaccountedtime + ", houravailtime " + houravailtime);

					if (!UIUtils.isValidKeyId(dtlId)) {
						pcsTlDtl.setUnaccountedtime(String.valueOf(unaccountedtime));
						pcsTlDtl.setProductionavltime(String.valueOf(unaccountedtime));
						pcsTlDtl.setMchavailabletime(String.valueOf(unaccountedtime));

						pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,15, "PDE", "YY", "Y"));
						sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
								pcsTlDtl.getSaveArray()));// add insert sql for detail table

					}

					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());
					if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
						// CommonFunctions.debugMsg(" In If csTlLosscapture.getPlosKeyid() 2"
						// +pcsTlDtl.getPldetailsid());
						pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
						pcsTlLossreasonlink.setPlrkMinutes(plrkminutes);
						pcsTlLossreasonlink.setPlrkCellid(pcsTlMst.getPrlmCellid());
						pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
						pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
						pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());

						sqls.addAll(createLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
								pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

						// pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","+
						// pcsTlLossreasonlink.getPlrkMinutes() );
						pcsdtl.add(
								detailTableName + "," + pcsTlDtl.getPldetailsid() + "," + pcsTlDtl.getCalendartime());
					} else {
						// CommonFunctions.debugMsg(" In Else csTlLosscapture.getPlosKeyid() 2");
						pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
						pcsTlLossreasonlink.setPlrkMinutes(plrkminutes);
						pcsTlLossreasonlink.setPlrkCellid(pcsTlMst.getPrlmCellid());
						pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
						pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
						pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());

						// pcsTlLossreasonlink.setPlrkLossid(pcsTlDtl.getPldetailsid());

						pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
						sqls.addAll(updateLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
								pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

						// pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","+
						// pcsTlLossreasonlink.getPlrkMinutes() );
						pcsdtl.add(
								detailTableName + "," + pcsTlDtl.getPldetailsid() + "," + pcsTlDtl.getCalendartime());
					}

				}
//					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
//					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
//					sqls.add(PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table

				// pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());

				// CommonFunctions.debugMsg("sqls for Mst Dtl " + sqls.toString() );

			}

			// }

		}
		//

		if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlospldetailsid()))
			pcsTlLosscapture.setPlosPldetailsid("-");

		if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
			pcsTlLosscapture.setPlosKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlLosscaptureSql.BAL_TBL_PCS_TL_LOSSCAPTURE, 15, "PLOS", "YYMM", "Y")); // set
																													// the
																													// sequnce
																													// number
			sqls.add(BAL_PcsTlLosscaptureSql.getInsertSql(pcsTlLosscaptureSql.getPlosDbFields(),
					pcsTlLosscapture.getSaveArray()));

			pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());

			 CommonFunctions.debugMsg( "sqls" + sqls);

			dbActionTemplate.executeStatements(sqls);

			/*
			 * for (int i = 0;i < Noofshift.size();i++) { System.out.println(
			 * "Noofshiftdtl.size () 1234 = " + i );
			 * pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
			 * createLoss(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
			 * pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()); }
			 */

			// createLoss(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
			// pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes());

		} else {
			
			String sql1 = " SELECT MAX(PLRK_KEYID) FROM PCS_TL_LOSSREASONLINK WHERE PLRK_REASONID = '"
			        + pcsTlLosscapture.getPlosLossreason() + "' ";
			sql1 += " AND PLRK_LOSSID = '" + pcsTlLosscapture.getPlosLossid() + "' ";
			sql1 += " AND PLRK_DATE::date = '" + pcsTlLosscapture.getPlosDate() + "'::date AND PLRK_SHIFTID = '"
			        + pcsTlLosscapture.getPlosShiftid() + "' AND PLRK_FLID = '" + pcsTlLosscapture.getPlosFlid() + "' ";

			/*
			 * String sql1 =
			 * " SELECT MAX(PLRK_KEYID) FROM PCS_TL_LOSSREASONLINK WHERE PLRK_REASONID = '"
			 * + pcsTlLosscapture.getPlosLossreason() + "' "; sql1 += " AND PLRK_LOSSID = '"
			 * + pcsTlLosscapture.getPlosLossid() + "' "; sql1 +=
			 * " AND TRUNC(PLRK_DATE) = '" + pcsTlLosscapture.getPlosDate() +
			 * "' AND PLRK_SHIFTID = '" + pcsTlLosscapture.getPlosShiftid() +
			 * "' AND PLRK_FLID = '" + pcsTlLosscapture.getPlosFlid() + "' ";
			 */
			String plrkKeyid = dbActionTemplate.getSingleValue(sql1);
			pcsTlLossreasonlink.setPlrkKeyid(plrkKeyid);

			sqls.add(BAL_PcsTlLosscaptureSql.getUpdateSql(pcsTlLosscaptureSql.getPlosDbFields(),
					pcsTlLosscapture.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			/*
			 * for (int i = 0;i < Noofshift.size();i++) {
			 * 
			 * pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
			 * 
			 * updateLoss(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
			 * pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()); }
			 */

		}
		CommonFunctions.debugMsg("pcsdtl.size()" + pcsdtl.size());
		for (int i = 0; i < pcsdtl.size(); i++) {
			String dfvalues = pcsdtl.get(i);
			List<String> dfsqls = new ArrayList<String>();

			String[] values = dfvalues.split(",");

			// CommonFunctions.debugMsg( "pcsdtl.size() values " + values[0] + " 2nd " +
			// values[1] + " 3rd " + values[2]);
			// CommonFunctions.debugMsg("pcsTlLosscapture.getPlosFlid..."+pcsTlLosscapture.getPlosFlid());
			runBulkSqls(dfsqls, values[0], values[1], values[2], pcsTlLosscapture.getPlosFlid());
		}

		return pcsTlLosscapture;

	}

	private int lossReasonMinutes(int j, int totalnooflosshours, String hourStarttime, String hourEndtime,
			String dfromtime, String dtoTime) throws ParseException {
		int diffinminutes;
		hourStarttime = hourStarttime.substring(12, 17);
		hourEndtime = hourEndtime.substring(12, 17);
		System.out.println("hourStartTime " + hourStarttime);
		System.out.println("hourEndTime " + hourEndtime);
		System.out.println("dfromtime " + dfromtime);
		System.out.println("dtoTime " + dtoTime);

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

		java.util.Date lossfromtime = simpleDateFormat.parse(dfromtime);
		java.util.Date losstotime = simpleDateFormat.parse(dtoTime);
		java.util.Date hourstart = simpleDateFormat.parse(hourStarttime);
		java.util.Date hourend = simpleDateFormat.parse(hourEndtime);

		if (j == 0) {

			if (totalnooflosshours != 1) {
				diffinminutes = (int) ((hourend.getTime() - lossfromtime.getTime()) / (1000 * 60));
			} else
				diffinminutes = (int) ((losstotime.getTime() - lossfromtime.getTime()) / (1000 * 60));
			System.out.println(diffinminutes);
			System.out.println("In int j ==1 " + diffinminutes);
			if (Integer.toString(diffinminutes).startsWith("-")) {
				diffinminutes = (int) 1440 + (int) (diffinminutes);
				System.out.println(diffinminutes);
			}
		}

		else if (j == (totalnooflosshours - 1)) {
			System.out.println(" in last hour in int j");
			diffinminutes = (int) ((losstotime.getTime() - hourstart.getTime()) / (1000 * 60));
			System.out.println(diffinminutes);
			if (Integer.toString(diffinminutes).startsWith("-")) {
				diffinminutes = (int) 1440 + (int) (diffinminutes);
				System.out.println(diffinminutes);
			}

		} else {
			diffinminutes = (int) ((hourend.getTime() - hourstart.getTime()) / (1000 * 60));
			System.out.println("In middle hour " + diffinminutes);
			if (Integer.toString(diffinminutes).startsWith("-")) {
				diffinminutes = (int) 1440 + (int) (diffinminutes);
				System.out.println(diffinminutes);
			}
		}

		return diffinminutes;
	}

	public List<String> createLosssql(BAL_PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkCellid(),
				pcsTlLossreasonlink.getPlrkDate());
		String cycleTime = "0";
		CommonFunctions.debugMsg("detailTableName... " + detailTableName);

		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonFunctions.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonFunctions.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonFunctions.debugMsg("loss cOLUMN Name" + lossColName);

		String sql = pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkReasonid());
		String causeCount = pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkReasonid());
		CommonFunctions.debugMsg("loss cause sql....." + causeCount);
		String checkExistId = dbActionTemplate.getSingleValue(sql);
		causeCount = dbActionTemplate.getSingleValue(sql);
		CommonFunctions.debugMsg("loss cause count....." + checkExistId);
		BAL_PcsTlLosscausemst pcsTlLosscausemst = new BAL_PcsTlLosscausemst();
		BAL_PcsTlLosscausemstSql pcsTlLosscausemstSql = new BAL_PcsTlLosscausemstSql();
		lossCauseFillValues(pcsTlLosscausemst, pcsTlLossreasonlink);
		if (UIUtils.isValidKeyId(checkExistId)) {
			if (Integer.parseInt(checkExistId) == 0) {
				// if(Integer.parseInt(causeCount)==0)
				{
					// String
					// keyid=dbActionTemplate.getSequenceNumber(TableNames.TBL_PCS_TL_LOSSCAUSEMST,8,"PLCS","","");
					// CommonFunctions.debugMsg("keyid..."+keyid);
					pcsTlLosscausemst.setPlcsKeyid(
							dbActionTemplate.getSequenceNumber(TableNames.TBL_PCS_TL_LOSSCAUSEMST, 8, "PLCS", "", ""));
					CommonFunctions.debugMsg("CausemstKeyid" + pcsTlLosscausemst.getPlcsKeyid());
					pcsTlLosscausemst.setPlcsPhenomenaid(pcsTlLossreasonlink.getPlrkReasonid());
					pcsTlLosscausemst.setPlcsDescription(pcsTlLossreasonlink.getPlrkCauseid());
					pcsTlLossreasonlink.setPlrkCauseid(pcsTlLosscausemst.getPlcsKeyid());
					sqls.add(pcsTlLosscausemstSql.getInsertSql(pcsTlLosscausemstSql.getPlcsDbFields(),
							pcsTlLosscausemst.getSaveArray()));
				}

			} else {
				pcsTlLosscausemst.setPlcsKeyid(pcsTlLossreasonlink.getPlrkCauseid());
				sqls.add(pcsTlLosscausemstSql.getUpdateSql(pcsTlLosscausemstSql.getPlcsDbFields(),
						pcsTlLosscausemst.getSaveArray()));
			}
		}
		pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK)); // set
																														// the
																														// sequnce
																														// number
		String checkLossExist = dbActionTemplate.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(pcsTlLossreasonlink));
		// CommonFunctions.debugMsg("checkLossExist--------->> "+checkLossExist);
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getInsertSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonFunctions.debugMsg("test sql3................................." + sqls + " lossId" + lossId+ " lossMapId " + lossMapId + "   " + lossColName + "   " + lossValue);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		// runBulkSqls(sqls,detailTableName,Pldetailsid,cycleTime);

		return sqls;
	}

	public BAL_PcsTlLossreasonlink createLoss(BAL_PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkCellid(),
				pcsTlLossreasonlink.getPlrkDate());
		String cycleTime = "0";
		CommonFunctions.debugMsg("detailTableName" + detailTableName);

		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonFunctions.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonFunctions.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonFunctions.debugMsg("loss cOLUMN Name" + lossColName);

		String sql = pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkReasonid());
		String causeCount = pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkReasonid());
		CommonFunctions.debugMsg("loss cause sql....." + causeCount);
		String checkExistId = dbActionTemplate.getSingleValue(sql);
		causeCount = dbActionTemplate.getSingleValue(sql);
		CommonFunctions.debugMsg("loss cause count....." + checkExistId);
		BAL_PcsTlLosscausemst pcsTlLosscausemst = new BAL_PcsTlLosscausemst();
		BAL_PcsTlLosscausemstSql pcsTlLosscausemstSql = new BAL_PcsTlLosscausemstSql();
		lossCauseFillValues(pcsTlLosscausemst, pcsTlLossreasonlink);
		if (UIUtils.isValidKeyId(checkExistId)) {
			if (Integer.parseInt(checkExistId) == 0) {
				// if(Integer.parseInt(causeCount)==0)
				{
					pcsTlLosscausemst.setPlcsKeyid(
							dbActionTemplate.getSequenceNumber(TableNames.TBL_PCS_TL_LOSSCAUSEMST, 8, "PLCS", "", ""));
					pcsTlLosscausemst.setPlcsPhenomenaid(pcsTlLossreasonlink.getPlrkReasonid());
					pcsTlLosscausemst.setPlcsDescription(pcsTlLossreasonlink.getPlrkCauseid());
					pcsTlLossreasonlink.setPlrkCauseid(pcsTlLosscausemst.getPlcsKeyid());
					sqls.add(pcsTlLosscausemstSql.getInsertSql(pcsTlLosscausemstSql.getPlcsDbFields(),
							pcsTlLosscausemst.getSaveArray()));
				}

			} else {
				pcsTlLosscausemst.setPlcsKeyid(pcsTlLossreasonlink.getPlrkCauseid());
				sqls.add(pcsTlLosscausemstSql.getUpdateSql(pcsTlLosscausemstSql.getPlcsDbFields(),
						pcsTlLosscausemst.getSaveArray()));
			}
		}
		pcsTlLossreasonlink
				.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK)); // set
																														// the
																														// sequnce
																														// number
		String checkLossExist = dbActionTemplate
				.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(pcsTlLossreasonlink));
		CommonFunctions.debugMsg("checkLossExist--------->> " + checkLossExist);
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getInsertSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonFunctions.debugMsg("test sql3.................................0" + sqls + " lossId" + lossId
				+ " lossMapId " + lossMapId + "   " + lossColName + "   " + lossValue);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, pcsTlLossreasonlink.getPlrkFlid());

		return pcsTlLossreasonlink;
	}

	private BAL_PcsTlLosscausemst lossCauseFillValues(BAL_PcsTlLosscausemst pcsTlLosscausemst,
			BAL_PcsTlLossreasonlink pcsTlLossreasonlink) {

		if (pcsTlLosscausemst.getPlcsDescription() == null)
			pcsTlLosscausemst.setPlcsDescription("{}");
		if (pcsTlLosscausemst.getPlcsPhenomenaid() == null)
			pcsTlLosscausemst.setPlcsPhenomenaid("{}");
		if (pcsTlLosscausemst.getPlcsTempfield2() == null)
			pcsTlLosscausemst.setPlcsTempfield2("{}");
		if (pcsTlLosscausemst.getPlcsTempfield3() == null)
			pcsTlLosscausemst.setPlcsTempfield3("{}");
		return pcsTlLosscausemst;
	}

	public List<String> updateLosssql(BAL_PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");
		String cycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkCellid(),
				pcsTlLossreasonlink.getPlrkDate());

		CommonFunctions.debugMsg("detailTableName" + detailTableName);
		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonFunctions.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonFunctions.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonFunctions.debugMsg("loss cOLUMN Name" + lossColName);
		CommonFunctions.debugMsg("loss cOLUMN Name" + pcsTlLossreasonlink.getPlrkMinutes());
		pcsTlLossreasonlink.setPlrkMinutes(pcsTlLossreasonlink.getPlrkMinutes().replace(":", "."));
		CommonFunctions.debugMsg("loss cOLUMN Name" + pcsTlLossreasonlink.getPlrkMinutes());
		// pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK));
		// // set the sequnce number
		String checkLossExist = dbActionTemplate
				.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(pcsTlLossreasonlink));
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getUpdateSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
//CommonFunctions.debugMsg("test sql1................................."+sqls);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		// runBulkSqls(sqls,detailTableName,Pldetailsid,cycleTime);

		return sqls;
	}

	public BAL_PcsTlLossreasonlink updateLoss(BAL_PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");
		String cycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkCellid(),
				pcsTlLossreasonlink.getPlrkDate());

		CommonFunctions.debugMsg("detailTableName" + detailTableName);
		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonFunctions.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonFunctions.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonFunctions.debugMsg("loss cOLUMN Name" + lossColName);

		pcsTlLossreasonlink.setPlrkMinutes(pcsTlLossreasonlink.getPlrkMinutes().replace(":", "."));

		// pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK));
		// // set the sequnce number
		String checkLossExist = dbActionTemplate
				.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(pcsTlLossreasonlink));
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getUpdateSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonFunctions.debugMsg("test sql1................................." + sqls);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, pcsTlLossreasonlink.getPlrkFlid());

		return pcsTlLossreasonlink;
	}

	public String insertNoPlan(String mchId, String date, String shift, String userId, String type, String duration,
			String frmTime, String toTime) throws Exception {
		try {
			List<String> paramValues = new ArrayList<String>();
			List<String[]> resultList;
			paramValues.add(mchId);
			paramValues.add(date);
			paramValues.add(shift);
			paramValues.add(userId);
			paramValues.add(type);
			paramValues.add(duration);
			paramValues.add(frmTime);
			paramValues.add(toTime);
			// resultList=
			// dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_INSERTPCSFORNOPLAN",
			// paramValues);
			resultList = dbActionTemplate.processFunctionCalls("PCS_FN_INSERTPCSFORNOPLAN", paramValues);
			String keyid;
			if (resultList.size() > 0) {
				CommonFunctions.debugMsg(resultList.get(0)[0]);
				keyid = resultList.get(0)[0];
			} else
				keyid = "";
			return keyid;

		} catch (Exception e) {

			CommonFunctions.debugMsg("Exception in insertNoPlan impl" + e.getMessage());
			return "fail";
		}

	}

	private List<String> ancilliaryTime(BAL_PcsTlLossreasonlink pcsTlLossreasonlink, List<String> sqls) throws Exception {
		// System.out.println("SIZE OF
		// GRID"+pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size());
		if (pcsTlLossreasonlink.getPcsTlAncilliarytimeList() != null
				&& pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() >= 0) // check for detail table data
		{
			if (pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() >= 0) {
				System.out.println("ancilliary Id for time" + pcsTlLossreasonlink.getPlrkLossid());
				sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(pcsTlLossreasonlink.getPlrkPldetailid(),
						pcsTlLossreasonlink.getPlrkLossid()));
			}

			for (int i = 0; i < pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size(); i++) {
				PcsTlAncilliarytime pcsTlAncilliarytime = (PcsTlAncilliarytime) pcsTlLossreasonlink
						.getPcsTlAncilliarytimeList().get(i);
				CommonFunctions.debugMsg("pcsTlLossreasonlink.getPcsTlAncilliarytimeList().get(i).active"
						+ pcsTlLossreasonlink.getPcsTlAncilliarytimeList().get(i).getPtatActive());
				pcsTlAncilliarytime.setPtatPldeatilsid(pcsTlLossreasonlink.getPlrkPldetailid());
				pcsTlAncilliarytime.setPtatLossid(pcsTlLossreasonlink.getPlrkLossid());
				pcsTlAncilliarytime.setPtatKeyid(
						dbActionTemplate.getSequenceNumber(PcsTlAncilliarytimeSql.TBL_PCS_TL_ANCILLIARYTIME)); // set
																												// the
																												// sequnce
																												// number

				sqls.add(PcsTlAncilliarytimeSql.getInsertSql(pcsTlAncilliarytimeSql.getPtatDbFields(),
						pcsTlAncilliarytime.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}

	public List<String[]> getLossHourNo(String shiftId, String dfromtime, String dtoTime) throws Exception {

		CommonFunctions.debugMsg("Inside getHourNo Dao Impl..........");
		try {
			StringBuffer sftstarttime = new StringBuffer();

			sftstarttime.append("select TO_char(SFTM_STARTTIME,'HH24:MI' ) from gen_tl_shiftmst ");
			sftstarttime.append(" where sftm_keyid = '");
			sftstarttime.append(shiftId);
			sftstarttime.append("'");

			String shiftStarttime = dbActionTemplate.getSingleValue(sftstarttime.toString());

			CommonFunctions.debugMsg("sftstarttimeindoa" + sftstarttime);
			StringBuffer sql1 = new StringBuffer();

			dtoTime = dtoTime.replace(":", "");
			dfromtime = dfromtime.replace(":", "");
			String sql = "";
	

			// p: one row for the shift (start, duration in minutes, number of hourly slots)
			sql1.append(" WITH p AS ( SELECT s.sftm_keyid, ");
			sql1.append(" (CURRENT_DATE + TIME '" + shiftStarttime + "') AS base_ts, ");
			sql1.append(" extract(hour FROM s.sftm_duration) * 60 + extract(minute FROM s.sftm_duration) AS assettime, ");
			sql1.append(" ceil(extract(epoch FROM (s.sftm_endtime::time - s.sftm_starttime::time) ");
			sql1.append("      + CASE WHEN s.sftm_endtime::time <= s.sftm_starttime::time ");
			sql1.append("             THEN INTERVAL '24 hours' ELSE INTERVAL '0' END) / 3600)::int AS nhours ");
			sql1.append(" FROM gen_tl_shiftmst s WHERE s.sftm_keyid = '" + shiftId + "' ");
			sql1.append(" AND s.sftm_shiftorder <= (SELECT sftm_shiftorder FROM gen_tl_shiftmst WHERE sftm_keyid = '" + shiftId + "') ), ");

			// a: the hourly slots (replaces ROWNUM and the annual table)
			sql1.append(" a AS ( SELECT p.sftm_keyid, ");
			sql1.append(" p.base_ts + (g.n - 1) * INTERVAL '1 hour' AS starttime, ");
			sql1.append(" p.base_ts + g.n * INTERVAL '1 hour' AS endtime, ");
			sql1.append(" g.n AS hournum, ");
			sql1.append(" GREATEST(0, LEAST(60, p.assettime - 60 * (g.n - 1))) AS shiftime ");
			sql1.append(" FROM p CROSS JOIN LATERAL generate_series(1, p.nhours) AS g(n) ) ");

			// main select
			sql1.append(" SELECT * FROM a, ");
			sql1.append(" (SELECT min(starttime) AS minstarttime FROM a) mn, ");
			sql1.append(" (SELECT max(endtime) AS maxendtime FROM a) mx ");
			sql1.append(" WHERE ( ");
			sql1.append(" ( starttime >= (SELECT max(starttime) FROM a WHERE to_char(minstarttime,'YYYYMMDD') || '" + dfromtime);
			sql1.append("' >= to_char(starttime,'YYYYMMDDHH24MI')) ");
			sql1.append(" AND endtime <= (SELECT min(endtime) FROM a WHERE to_char(starttime,'YYYYMMDD') || '" + dtoTime);
			sql1.append("' <= to_char(endtime,'YYYYMMDDHH24MI')) ) ");
			sql1.append(" OR ( starttime >= (SELECT max(starttime) FROM a WHERE to_char(minstarttime,'YYYYMMDD') || '" + dfromtime);
			sql1.append("' >= to_char(starttime,'YYYYMMDDHH24MI')) ");
			sql1.append(" AND endtime <= (SELECT min(endtime) FROM a WHERE to_char(maxendtime,'YYYYMMDD') || '" + dtoTime);
			sql1.append("' <= to_char(endtime,'YYYYMMDDHH24MI')) ) ");
			sql1.append(" OR ( starttime >= (SELECT max(starttime) FROM a WHERE to_char(minstarttime,'YYYYMMDD') || '" + dfromtime);
			sql1.append("' >= to_char(starttime,'YYYYMMDDHH24MI')) ");
			sql1.append(" AND endtime <= (SELECT min(endtime) FROM a WHERE to_char(endtime,'YYYYMMDD') || '" + dtoTime);
			sql1.append("' <= to_char(endtime,'YYYYMMDDHH24MI')) ) ) ");
		
			//commenteed by Kiran
			/*		sql1.append(" WITH  a as ( SELECT   sftm_keyid,");
			sql1.append(" TO_DATE (TO_CHAR (sftm_starttime, 'DD-MON-YYYY') || '" + shiftStarttime
					+ "' ,'DD-MON-YYYY HH24:MI') ");
			sql1.append(" + ((ROWNUM - 1) / 24) starttime, TO_DATE (TO_CHAR (sftm_starttime, 'DD-MON-YYYY') || '"
					+ shiftStarttime);
			sql1.append("' , 'DD-MON-YYYY HH24:MI') + ((ROWNUM) / 24) endtime,");
			sql1.append(
					" ROWNUM AS hournum, DECODE (SIGN (assettime - 60 * ROWNUM),-1, DECODE (SIGN (60 + (assettime - 60 * ROWNUM)), ");
			sql1.append(" 1, 60 + (assettime - 60 * ROWNUM),0),60) AS shiftime");
			sql1.append(" FROM bal_gen_tl_annualtable,(SELECT sftm_keyid, sftm_starttime, ");
			sql1.append(" TO_NUMBER (TO_CHAR (sftm_duration, 'HH'))* 60 + TO_NUMBER (TO_CHAR (sftm_duration, 'MI')) ");
			sql1.append(" AS assettime,sftm_breaktime,sftm_endtime FROM gen_tl_shiftmst WHERE sftm_shiftorder <= ( ");
			sql1.append(" SELECT sftm_shiftorder FROM gen_tl_shiftmst WHERE sftm_keyid = '" + shiftId
					+ "')  AND sftm_keyid = '");
			sql1.append(shiftId
					+ "') WHERE ROWNUM <= (SELECT CEIL ((sftm_endtime - sftm_starttime) * 24) FROM gen_tl_shiftmst ");
			sql1.append(" WHERE sftm_keyid = '" + shiftId + "') ORDER BY hournum )");
			sql1.append(" select * from a,( select min(starttime) as minstarttime from a ");
			sql1.append(" ),(select max(endtime) as maxendtime from a) where ( (starttime >= ");
			sql1.append(" ( select max(starttime) from a where to_char(minstarttime,'YYYYMMDD') || '" + dfromtime);
			sql1.append("' >=  to_char(STARTTIME,'YYYYMMDDHH24MI') ) and endtime <= (select min(endtime) from a ");
			sql1.append(" where to_char(starttime,'YYYYMMDD') || '" + dtoTime
					+ "' <= to_char(endtime,'YYYYMMDDHH24MI')) )");
			sql1.append(" or ( starttime >= ( select max(starttime) from a where to_char(minstarttime,'YYYYMMDD') || '"
					+ dfromtime);
			sql1.append("' >=  to_char(STARTTIME,'YYYYMMDDHH24MI') ) and endtime <= (select min(endtime) from a");
			sql1.append("   where to_char(maxendtime,'YYYYMMDD') || '" + dtoTime
					+ "'  <= to_char(endtime,'YYYYMMDDHH24MI'))) ");
			sql1.append(" or ( starttime >= ( select max(starttime) from a where to_char(minstarttime,'YYYYMMDD') || '"
					+ dfromtime);
			sql1.append("' >=  to_char(STARTTIME,'YYYYMMDDHH24MI') ) and endtime <= (select min(endtime) from a");
			sql1.append("   where to_char(endtime,'YYYYMMDD') || '" + dtoTime
					+ "'  <= to_char(endtime,'YYYYMMDDHH24MI')))) ");
*/
			sql = sql1.toString();

			CommonFunctions.debugMsg("String getHourNo=" + sql);

			List<String[]> noofhours = dbActionTemplate.getDataList(sql);
			return noofhours;
		} catch (Exception e) {
			CommonFunctions.debugMsg("Exception in noofhours dao impl" + e.getMessage());
			return null;
		}

	}

	public void getOEECalculation(String detailTableName, String Pldetailsid, String flid) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(detailTableName);
		paramValues.add(Pldetailsid);
		paramValues.add(flid);
		CommonFunctions.debugMsg("paramValues before the Function call " + paramValues);

		dbActionTemplate.processFunctionCalls("PCS_FN_UPDATEPLDTL", paramValues);
		CommonFunctions.debugMsg("paramValues after the function call " + paramValues);
		// dbActionTemplate.pr
		// dbActionTemplate.processPLSQLProcedures("PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL",
		// paramValues, null);

	}

	public List<String[]> getPendingTime(String detailTable, String pldetailsId) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";

		List<String> paramValues = new ArrayList<String>();
		List<String[]> pendingTimeList;

		paramValues.add(detailTable);
		paramValues.add(pldetailsId);

		pendingTimeList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODUCTIONCALC.PCS_FN_GETPENDINGTIME",
				paramValues);

		return pendingTimeList;
	}

	public String getSelectModel(String prdId) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String modelId = "";
		modelId = dbActionTemplate.getSingleValue("PCS_TL_PRODUCTMST", "PRDM_MODEL", "PRDM_KEYID", prdId);

		return modelId;
	}

	public String getLineRejectionLoss() throws Exception {
		List<String[]> lossList = dbActionTemplate.getDataList("SELECT * FROM PCS_VW_LOSSNAMESFORENTRY");
		CommonFunctions.debugMsg(lossList);
		String lossId = null;
		if (lossList != null) {
			CommonFunctions.debugMsg(lossList.size());
			if (lossList.size() > 0) {
				int flag = 0;
				for (String[] loss : lossList) {

					for (int i = 0; i < loss.length; i++) {
						CommonFunctions.debugMsg(loss[i]);
						if (loss[i].indexOf("Rejected") > 0)
							lossId = lossList.get(flag)[0];
						CommonFunctions.debugMsg(i + "  : " + lossList.get(flag)[0]);
					}
					flag++;
				}
			}
		}
		return lossId;
	}

	/*
	 * public String deletePcsEntry(String sectId, String plmasterId, String
	 * pldetailsId, String workOrderNo) throws Exception { try { StringBuffer sql1 =
	 * new StringBuffer(); String sql =
	 * " SELECT COUNT(*) FROM PCS_TL_LOSSREASONLINK  WHERE PLRK_PLDETAILID = '" +
	 * pldetailsId + "' ";
	 * 
	 * String LossCount = dbActionTemplate.getSingleValue(sql);
	 * 
	 * String detailTableName = "PCS_TL_" +
	 * dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
	 * "SECT_KEYID", sectId); detailTableName =
	 * detailTableName.trim().replaceAll("-", "#");
	 * 
	 * if (LossCount.equals("0")) {
	 * 
	 * sql = " DELETE FROM PCS_TL_WORKORDERLINK WHERE PTWO_WNO ='" + workOrderNo +
	 * "' "; sql += " AND PTWO_PLDETAILID ='" + pldetailsId + "' ";
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * sql = " SELECT COUNT(*) FROM PCS_TL_WORKORDERLINK WHERE PTWO_PLDETAILID = '"
	 * + pldetailsId + "' "; String workOrderCount =
	 * dbActionTemplate.getSingleValue(sql);
	 * 
	 * if (workOrderCount.equals("0")) { sql1.append(" DELETE FROM " +
	 * detailTableName + " "); sql1.append(" WHERE PLDETAILSID = '" + pldetailsId +
	 * "' "); sql=sql1.toString();
	 * 
	 * CommonFunctions.debugMsg("loss link not exists ="+sql);
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * String sql2 = " SELECT COUNT(*) FROM " + detailTableName +
	 * "  WHERE PLMASTERID = '" + plmasterId + "' "; String detCount =
	 * dbActionTemplate.getSingleValue(sql2); if (detCount.equals("0")) { sql2 =
	 * " DELETE FROM BAL_PCS_TL_MST WHERE PRLM_KEYID = '" + plmasterId + "' ";
	 * CommonFunctions.debugMsg("master no data exists ="+sql2);
	 * dbActionTemplate.executeStatement(sql2);
	 * 
	 * return "Success_withMaster"; }
	 * 
	 * }
	 * 
	 * return "Success"; } else { return "Exists"; } }
	 * 
	 * catch(Exception e) {
	 * CommonFunctions.debugMsg("error while deleteing "+e.getMessage()); return
	 * "fail"; } }
	 */

	public String deletePcsLossEntryITC(String PlrkKeyid, String PlosKeyid, String Pldetailsid, String sectId,
			String lossId, String lossVal) throws Exception {
		try {
			StringBuffer sql1 = new StringBuffer();
			String cycleTime = "0";
			List<String> sqls = new ArrayList<String>();

			StringBuffer sql0 = new StringBuffer();
			sql0.append(" DELETE FROM BAL_PCS_TL_LOSSCAPTURE ");
			sql0.append(" WHERE PLOS_KEYID = '" + PlosKeyid + "' ");

			System.out.println("ancilliary Id for time" + lossId);

			sqls.add(sql0.toString());

			String plrkMsrno = dbActionTemplate.getSingleValue(
					"SELECT PLRK_MSRNO FROM PCS_TL_LOSSREASONLINK WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonFunctions.debugMsg("plrkMsrno=" + plrkMsrno);

			if (!plrkMsrno.equals("{}") && !plrkMsrno.equals("") && !plrkMsrno.equals(" ") && plrkMsrno != null)
				return "LockDelete";
			// throw new BusinessApplicationExceptions("view_LockDelete"+",");

			sql1.append(" DELETE FROM PCS_TL_LOSSREASONLINK ");
			sql1.append(" WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			System.out.println("ancilliary Id for time" + lossId);
			sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(Pldetailsid, lossId));

			sqls.add(sql1.toString());

			CommonFunctions.debugMsg("loss delete sql=" + sql1.toString());

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
			detailTableName = detailTableName.trim().replaceAll("-", "#");
			detailTableName = detailTableName.replaceAll(" ", "");

			String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD",
					"PLCM_KEYID", lossId);
			String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
					"PLCM_KEYID", lossId);
			String lossColName = getColName(ParameterCode, lossId);
			String lossValue = "0";

			BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
			cycleTime = pcsTlDtl.getActualcycletime();
			sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
			CommonFunctions.debugMsg("test sql2................................." + sqls);
			// update the parent values

			String parentLossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARENTID",
					"PLCM_KEYID", lossId);

			if (!lossId.equals(parentLossId)) {
				String parentParamCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
						"PLCM_PARAMETERCODE", "PLCM_KEYID", parentLossId);
				String parentColName = getColName(parentParamCode, lossId);

				CommonFunctions.debugMsg("parentLossId" + parentLossId);

				StringBuffer sql2 = new StringBuffer();
				sql2.append(" UPDATE " + detailTableName);
				// sql2.append(" SET " + parentColName + " = "+ parentColName + " - " +
				// lossVal);
				sql2.append(" SET " + parentColName + " = DECODE(" + parentColName + " - " + lossVal + ",0,NULL,"
						+ parentColName + " - " + lossVal + ")");
				sql2.append(" WHERE PLDETAILSID = '" + Pldetailsid + "' ");

				CommonFunctions.debugMsg("update sqlsss" + sql2.toString());

				sqls.add(sql2.toString());
			}

//			dbActionTemplate.executeStatements(sqls);
//			getOEECalculation(detailTableName,Pldetailsid);

			runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, "");

			return "Success";
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

	public String deletePcsLossEntryBajaj(String PlosKeyid, String PlrkKeyid, String Pldetailsid, String cellId,
			String lossId, String lossVal, String entryDate) throws Exception {
		try {
			StringBuffer sql1 = new StringBuffer();
			List<String> sqls = new ArrayList<String>();

			String plrkMsrno = dbActionTemplate.getSingleValue(
					"SELECT PLRK_MSRNO FROM PCS_TL_LOSSREASONLINK WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonFunctions.debugMsg("plrkMsrno=" + plrkMsrno);

			if (!plrkMsrno.equals("{}") && !plrkMsrno.equals("") && !plrkMsrno.equals(" ") && plrkMsrno != null)
				return "LockDelete";
			// throw new BusinessApplicationExceptions("view_LockDelete"+",");
			String plrkCauseId = dbActionTemplate.getSingleValue(
					"Select PLRK_CAUSEID from PCS_TL_LOSSREASONLINK WHERE PLRK_KEYID = '" + PlrkKeyid + "'");
			sql1.append("DELETE FROM BAL_PCS_TL_LOSSCAPTURE where PLOS_KEYID = '" + PlosKeyid + "'");
			sqls.add(sql1.toString());
			StringBuffer sb1 = new StringBuffer();
			sb1.append(" DELETE FROM PCS_TL_LOSSCAUSEMST where PLCS_KEYID ='" + plrkCauseId + "'");
			sqls.add(sb1.toString());
			StringBuffer sb2 = new StringBuffer();
			sb2.append(" DELETE FROM PCS_TL_LOSSREASONLINK ");
			sb2.append(" WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");
			System.out.println("ancilliary SB1" + sb1.toString());
			System.out.println("ancilliary Id for time" + lossId);
			// sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(Pldetailsid, lossId));

			sqls.add(sb2.toString());

			CommonFunctions.debugMsg("loss delete sql SB2=" + sql1.toString());
			String detailTableName = getDetailTableName(cellId, entryDate);
			/*
			 * String detailTableName = "PCS_TL_" +
			 * dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
			 * "SECT_KEYID", sectId); detailTableName =
			 * detailTableName.trim().replaceAll("-", "#"); detailTableName =
			 * detailTableName.replaceAll(" ", "");
			 */
			String cycleTime = dbActionTemplate.getSingleValue(
					"Select CALENDARTIME from " + detailTableName + " where pldetailsid ='" + Pldetailsid + "'");
			String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD",
					"PLCM_KEYID", lossId);
			String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
					"PLCM_KEYID", lossId);
			String lossColName = getColName(ParameterCode, lossId);
			// String lossValue = "0";
			CommonFunctions.debugMsg("Cycletime " + cycleTime);
			BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();

			sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossVal));
			CommonFunctions.debugMsg("test sql2 Bajaj................................." + sqls);
			// update the parent values

			String parentLossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARENTID",
					"PLCM_KEYID", lossId);

			if (!lossId.equals(parentLossId)) {
				String parentParamCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
						"PLCM_PARAMETERCODE", "PLCM_KEYID", parentLossId);
				String parentColName = getColName(parentParamCode, lossId);

				CommonFunctions.debugMsg("parentLossId" + parentLossId);

				StringBuffer sql2 = new StringBuffer();
				sql2.append(" UPDATE " + detailTableName);
				// sql2.append(" SET " + parentColName + " = "+ parentColName + " - " +
				// lossVal);
				sql2.append(" SET " + parentColName + " = DECODE(" + parentColName + " - " + lossVal + ",0,NULL,"
						+ parentColName + " - " + lossVal + ")");
				sql2.append(" WHERE PLDETAILSID = '" + Pldetailsid + "' ");

				CommonFunctions.debugMsg("update sqlsss" + sql2.toString());

				sqls.add(sql2.toString());
			}

//			dbActionTemplate.executeStatements(sqls);
//			getOEECalculation(detailTableName,Pldetailsid);

			runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, "");

			return "Success";
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

	public String deletePcsLossEntry(String PlrkKeyid, String Pldetailsid, String sectId, String lossId, String lossVal)
			throws Exception {
		try {
			StringBuffer sql1 = new StringBuffer();
			String cycleTime = "0";
			List<String> sqls = new ArrayList<String>();

			String plrkMsrno = dbActionTemplate.getSingleValue(
					"SELECT PLRK_MSRNO FROM PCS_TL_LOSSREASONLINK WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonFunctions.debugMsg("plrkMsrno=" + plrkMsrno);

			// if (! plrkMsrno.equals("{}") && ! plrkMsrno.equals("") && !
			// plrkMsrno.equals(" ") && plrkMsrno != null)
			// return "LockDelete";
			// throw new BusinessApplicationExceptions("view_LockDelete"+",");

			sql1.append(" DELETE FROM PCS_TL_LOSSREASONLINK ");
			sql1.append(" WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			System.out.println("ancilliary Id for time" + lossId);
			sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(Pldetailsid, lossId));

			sqls.add(sql1.toString());

			CommonFunctions.debugMsg("loss delete sql=" + sql1.toString());

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
			detailTableName = detailTableName.trim().replaceAll("-", "#");
			detailTableName = detailTableName.replaceAll(" ", "");

			String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD",
					"PLCM_KEYID", lossId);
			String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
					"PLCM_KEYID", lossId);
			String lossColName = getColName(ParameterCode, lossId);
			String lossValue = "0";

			BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
			cycleTime = pcsTlDtl.getActualcycletime();
			sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
			CommonFunctions.debugMsg("test sql2................................." + sqls);
			// update the parent values

			String parentLossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARENTID",
					"PLCM_KEYID", lossId);

			if (!lossId.equals(parentLossId)) {
				String parentParamCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
						"PLCM_PARAMETERCODE", "PLCM_KEYID", parentLossId);
				String parentColName = getColName(parentParamCode, lossId);

				CommonFunctions.debugMsg("parentLossId" + parentLossId);

				StringBuffer sql2 = new StringBuffer();
				sql2.append(" UPDATE " + detailTableName);
				// sql2.append(" SET " + parentColName + " = "+ parentColName + " - " +
				// lossVal);
				sql2.append(" SET " + parentColName + " = DECODE(" + parentColName + " - " + lossVal + ",0,NULL,"
						+ parentColName + " - " + lossVal + ")");
				sql2.append(" WHERE PLDETAILSID = '" + Pldetailsid + "' ");

				CommonFunctions.debugMsg("update sqlsss" + sql2.toString());

				sqls.add(sql2.toString());
			}

//			dbActionTemplate.executeStatements(sqls);
//			getOEECalculation(detailTableName,Pldetailsid);

			runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, "");

			return "Success";
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

	public String deletePcsEmployee(String Pldetailsid, String empId) throws ValidationExceptions, Exception {
		try {
			StringBuffer sql1 = new StringBuffer();

			List<String> sqls = new ArrayList<String>();

			sql1.append(" DELETE FROM PCS_TL_OPERATORDTL ");
			sql1.append(" WHERE POPD_PLDETAILSID = '" + Pldetailsid + "' ");
			sql1.append(" AND POPD_PLEMPLOYEEID  = '" + empId + "' ");

			sqls.add(sql1.toString());

			dbActionTemplate.executeStatements(sqls);
			return "Success";
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

//getRowNo
	public String getColNameByLossNo(String ParameterLossNo) {
		String colName = "";

		ParameterLossNo = ParameterLossNo.trim();
		CommonFunctions.debugMsg("ParameterLossNo==" + ParameterLossNo);
		if (ParameterLossNo.equals("1"))
			colName = "EQUIPMENTFAILURE_ML";
		else if (ParameterLossNo.equals("2"))
			colName = "SETUPANDADJUSTMENT_ML";
		else if (ParameterLossNo.equals("3"))
			colName = "TOOLCHANGELOSS_ML";
		else if (ParameterLossNo.equals("4"))
			colName = "STARTUPLOSS_ML";
		else if (ParameterLossNo.equals("5"))
			colName = "MINORSTOPPAGELOSS_ML";
		else if (ParameterLossNo.equals("6"))
			colName = "SPEEDLOSS_ML";
		else if (ParameterLossNo.equals("7"))
			colName = "DEFECTSANDREWORKLOSS_ML";
		else if (ParameterLossNo.equals("8"))
			colName = "SHUTDOWNLOSS_ML";
		else if (ParameterLossNo.equals("9"))
			colName = "MANAGEMENTLOSS_ML";
		else if (ParameterLossNo.equals("10"))
			colName = "OPERATINGMOTIONLOSS_ML";
		else if (ParameterLossNo.equals("11"))
			colName = "LINEORGANISATIONLOSS_ML";
		else if (ParameterLossNo.equals("12"))
			colName = "LOGISTICSLOSS_ML";
		else if (ParameterLossNo.equals("13"))
			colName = "MEASURINGANDADJLOSS_ML";
		else if (ParameterLossNo.equals("14"))
			colName = "DIETOOLANDJIGLOSS_ML";
		else if (ParameterLossNo.equals("15"))
			colName = "ENERGYLOSS_ML";
		else if (ParameterLossNo.equals("16"))
			colName = "YIELDLOSS_ML";
		// Rejection Losses

		return colName;

	}

	// getRowNo
	public String getColName(String ParameterCode, String lossId) throws SQLException {
		String colName = "";
		if (ParameterCode.equals("MCHAVLTIME"))
			colName = "MCHAVAILABLETIME";
		else if (ParameterCode.equals("PLANNEDQTY"))
			colName = "PLANNEDQTY";
		else if (ParameterCode.equals("PRODTIME"))
			colName = "PRODUCTIONTIME";
		else if (ParameterCode.equals("UNREPORTED"))
			colName = "UNACCOUNTEDTIME";
		else if (ParameterCode.equals("NOPLAN"))
			colName = "NOPLANINMINS";
		else if (ParameterCode.equals("NUMPRODUCTS"))
			colName = "NOOFPRODUCTS";
		else if (ParameterCode.equals("PARTNO"))
			colName = "PARTNO";
		else if (ParameterCode.equals("PARTNAME"))
			colName = "PARTNAME";
		else if (ParameterCode.equals("PRODUCTMODEL"))
			colName = "PRODUCTMODEL";
		else if (ParameterCode.equals("CYCLETIME"))
			colName = "CYCLETIME";
		else if (ParameterCode.equals("NUMOPERATOR"))
			colName = "OPERATORS";
		else if (ParameterCode.equals("OPNNO"))
			colName = "OPERATIONNO";
		else if (ParameterCode.equals("OPNDESC"))
			colName = "OPERATIONDESCRIPTION";
		else if (ParameterCode.equals("WNO"))
			colName = "WNO";
		else if (ParameterCode.equals("WEIGHT"))
			colName = "WEIGHT";
		else if (ParameterCode.equals("RAWMATERIALTYPE"))
			colName = "RAWMATERIALTYPE";

		else if (ParameterCode.equals("PRODUCEDQTY"))
			colName = "PRODUCEDQTY";
		// Rejection Losses

		else if (ParameterCode.equals("INSPECTEDQTY"))
			colName = "INSPECTEDQTY";

		else if (ParameterCode.equals("DEFECTNREWORK"))
			colName = "DEFECTSANDREWORKLOSS_ML";
		else if (ParameterCode.equals("REJECTEDQTY"))
			colName = "REJECTEDQTY";
		else if (ParameterCode.equals("REWORKQTY"))
			colName = "REWORKQTY";

		else if (ParameterCode.equals("QAACCEPTEDQTY"))
			colName = "QAACCEPTEDQTY";

		else if (ParameterCode.equals("DEFECTPREVPROC"))
			colName = "DEFECTPREVPROC";

		else if (ParameterCode.equals("DEFECTTIME"))
			colName = "DEFECTTIME_SL";
		else if (ParameterCode.equals("MRNMT"))
			colName = "MRNMT";
		else if (ParameterCode.equals("REPROCESS"))
			colName = "REPROCESSING";

		else if (ParameterCode.equals("PRODUCTIONLOSS"))
			colName = "PRODUCTIONLOSSES";

		// Equipment Failure
		else if (ParameterCode.equals("EF"))
			colName = "EQUIPMENTFAILURE_ML";
		else if (ParameterCode.equals("EFE"))
			colName = "EF_ELECTRICAL";
		else if (ParameterCode.equals("EFL"))
			colName = "EF_ELECTRONICS";
		else if (ParameterCode.equals("EFM"))
			colName = "EF_MECHANICAL";
		else if (ParameterCode.equals("EFJ"))
			colName = "EF_JIGFIXTURELOSS";
		// added for PGC - begins
		else if (ParameterCode.equals("PEFL"))
			colName = "PEFL";
		else if (ParameterCode.equals("UEFL"))
			colName = "UEFL";
		else if (ParameterCode.equals("PROCESSFAILURE"))
			colName = "PROCESSFAILURE";
		// Added for PGC - ends
		// Setup and Adjustment
		else if (ParameterCode.equals("SETUPANDADJ"))
			colName = "SETUPANDADJUSTMENT_ML";
		else if (ParameterCode.equals("MODELCHANGEPART"))
			colName = "MODELCHANGEPART";
		else if (ParameterCode.equals("MODELCHANGE"))
			colName = "MODELCHANGE";
		else if (ParameterCode.equals("MODELADJUSTMENT"))
			colName = "MODELADJUSTMENT";
		else if (ParameterCode.equals("PARTMODEL"))
			colName = "PARTMODEL";

		// Tool change losses
		else if (ParameterCode.equals("TOOLCHANGELOSS"))
			colName = "TOOLCHANGELOSS_ML";
		/*
		 * else if ( ParameterCode.equals("TOOLCHANGE")) colName = "TOOLCHANGE"; else if
		 * ( ParameterCode.equals("COLORCHANGE")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("ELECTRODECHANGE")) colName = "PLANNEDQTY"; //added for
		 * PGC - begins else if ( ParameterCode.equals("TOOLCHANGSUBJEC")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("TOOLCHANGEACHIV")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("TOOLCHANGEBDF")) colName =
		 * "PLANNEDQTY"; //added for PGC - ends
		 */
		else if (ParameterCode.equals("STARTUPLOSS"))
			colName = "STARTUPLOSS_ML";
		else if (ParameterCode.equals("MS"))
			colName = "MINORSTOPPAGELOSS_ML";
		else if (ParameterCode.equals("SPEEDLOSS"))
			colName = "SPEEDLOSS_ML";
		else if (ParameterCode.equals("SPEEDVARIATION"))
			colName = "PLANNEDQTY";
		// Shutdown losses
		else if (ParameterCode.equals("SHUTDOWNLOSS"))
			colName = "SHUTDOWNLOSS_ML";
		/*
		 * else if ( ParameterCode.equals("PMEETING")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("PTRAINING")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("PM")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("JH")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NEWPRODUCT")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("BREAKS")) colName = "PLANNEDQTY"; //added for PGC -
		 * begins else if ( ParameterCode.equals("PELECSHUTDOWN")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("PGASSHUTDOWN")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("PAIRSHUTDOWN")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("OPLANSHUTDOWN")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("SPARETRIALEXP")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("RAWTRIALEXP")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("PROGRAMCANCEL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("PROGRAMHELDUP")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("SPLTRAINING")) colName =
		 * "PLANNEDQTY";
		 */
		// added for PGC - ends
		// management losses
		else if (ParameterCode.equals("MANAGEMENTLOSS"))
			colName = "MANAGEMENTLOSS_ML";

		/*
		 * else if ( ParameterCode.equals("NORAWMATERIAL")) colName = "PLANNEDQTY"; else
		 * if ( ParameterCode.equals("NOMATERIALFACT")) colName = "PLANNEDQTY"; else if
		 * ( ParameterCode.equals("NOTOOL")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("LINECHANGEOVER")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NOMANPOWER")) colName = "PLANNEDQTY"; //added for PGC -
		 * Begins else if ( ParameterCode.equals("WAITINSTRUCTION")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITMATERIAL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITPERSONAL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITTUBE")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITINK")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITSOLVENT")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITVIALTRAY")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITPSPLATE")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITLASTSHEET")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITSHERSHEET")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITPARAOIL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITCUTTINGOIL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITCAPSHELL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITWADS")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITPLASTICCAP")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITSEAL")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITCOLOURPP")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITMASTERBASE")) colName =
		 * "PLANNEDQTY"; else if ( ParameterCode.equals("WAITPP")) colName =
		 * "PLANNEDQTY";
		 */
		// added for PGC - Begins

		// common utility loss
		else if (ParameterCode.equals("COMMONUTILITY"))
			colName = "COMMONUTILITYLOSS_SL";
		/*
		 * else if ( ParameterCode.equals("NOPOWER")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NOAIR")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NOWATER")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NOGAS")) colName = "PLANNEDQTY"; else if (
		 * ParameterCode.equals("NOCOOLANT")) colName = "PLANNEDQTY";
		 */

		else if (ParameterCode.equals("MOTIONLOSS"))
			colName = "OPERATINGMOTIONLOSS_ML";
		else if (ParameterCode.equals("LINEORGLOSS"))
			colName = "LINEORGANISATIONLOSS_ML";
		else if (ParameterCode.equals("LOGISTICSLOSS"))
			colName = "LOGISTICSLOSS_ML";
		else if (ParameterCode.equals("MEASUREMENTLOSS"))
			colName = "MEASURINGANDADJLOSS_ML";
		else if (ParameterCode.equals("DIETOOLJIGLOSS"))
			colName = "DIETOOLANDJIGLOSS_ML";
		else if (ParameterCode.equals("ENERGYLOSS"))
			colName = "ENERGYLOSS_ML";
		else if (ParameterCode.equals("YIELDLOSS"))
			colName = "YIELDLOSS_ML";
		else if (ParameterCode.equals("RESULTS"))
			colName = "RESULTS";
		else if (ParameterCode.equals("LOADINGTIME"))
			colName = "LOADINGTIME";
		else if (ParameterCode.equals("PRODAVLTIME"))
			colName = "PRODUCTIONAVLTIME";
		else if (ParameterCode.equals("EFFPRODMINS"))
			colName = "EFFECTIVEPRODMINS";
		else if (ParameterCode.equals("ROA"))
			colName = "ROA";
		else if (ParameterCode.equals("ROP"))
			colName = "ROQ";
		else if (ParameterCode.equals("ROQ"))
			colName = "ROQ";
		else if (ParameterCode.equals("OEE"))
			colName = "OEEREMARKS";
		else if (ParameterCode.equals("REMARKS"))
			colName = "REMARKS";

		// added on 26-Aug-2014 by babu
		if (!UIUtils.isValidKeyId(colName)) {
			String ParameterLossNo = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_LOSSNO",
					"PLCM_KEYID", lossId);
			CommonFunctions.debugMsg("ParameterLossNo" + ParameterLossNo);
			colName = getColNameByLossNo(ParameterLossNo);
		}

		return colName;

	}

	@Override
	public BAL_PcsTlMst update(BAL_PcsTlMst PcsTlMst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * public String getShiftCount(String factId, String entryDate, String shiftId)
	 * throws Exception {
	 * 
	 * StringBuffer sql = new StringBuffer(); sql.
	 * append("SELECT COUNT(*) AS CNT FROM ( SELECT SFTM_KEYID, SFTM_CODE, SFTM_SHIFTORDER"
	 * ); sql.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER<='3' "); //
	 * sql.append(" AND SFTM_FACTORYID = '"+factId+"'"); sql.append(" AND TO_DATE('"
	 * + entryDate +
	 * "' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= CURRENT_TIMESTAMP  "
	 * ); sql.append(" AND SFTM_KEYID = '" + shiftId + "')");
	 * CommonFunctions.debugMsg("sql.toString().........." + sql.toString()); String
	 * cnt = dbActionTemplate.getSingleValue(sql.toString()); return cnt;
	 * 
	 * }
	 */

	public String getShiftCount(String factId, String entryDate, String shiftId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS CNT FROM ( SELECT SFTM_KEYID, SFTM_CODE, SFTM_SHIFTORDER");
		sql.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER<='3' ");
		sql.append(" AND TO_TIMESTAMP('" + entryDate
				+ " ' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= CURRENT_TIMESTAMP  ");
		sql.append(" AND SFTM_KEYID = '" + shiftId + "')");
		CommonFunctions.debugMsg("sql.toString().........." + sql.toString());
		String cnt = dbActionTemplate.getSingleValue(sql.toString());
		return cnt;
	}

	@Override
	public String deletePcsEntry(String sectId, String plmasterId, String pldetailsId, String workOrderNo,
			String machKeyId, String entryDate) throws Exception {
		// TODO Auto-generated method stub
		try {
			StringBuffer sql1 = new StringBuffer();
			List<String> bulk_sqls = new ArrayList<String>();
			List<Object[]> valueList = new ArrayList<Object[]>();
			List<int[]> dataTypes = new ArrayList<int[]>();
			List<String> charList = new ArrayList<String>();
			List<String> sqls = new ArrayList<String>();
			String retStatus = null;
			// String sql = " SELECT COUNT(*) FROM PCS_TL_LOSSREASONLINK WHERE
			// PLRK_PLDETAILID = '" + pldetailsId + "' ";
			String LossCountsql = pcsTlMstSql.getCount(pldetailsId);
			String LossCount = dbActionTemplate.getSingleValue(LossCountsql);

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
			detailTableName = detailTableName.trim().replaceAll("-", "#");
			detailTableName = detailTableName.replaceAll(" ", "");

			if (LossCount.equals("0")) {

				/*
				 * sql = " DELETE FROM PCS_TL_WORKORDERLINK WHERE PTWO_WNO ='" + workOrderNo +
				 * "' "; sql += " AND PTWO_PLDETAILID ='" + pldetailsId + "' ";
				 */
				// dbActionTemplate.executeStatement(sql);
				String woLink = pcsTlMstSql.delWOLink(workOrderNo, pldetailsId);
				sqls.add(woLink);
				valueList.add(null);
				dataTypes.add(null);
				charList.add("Q");
				String sqlworkOrderCount = " SELECT COUNT(*) FROM PCS_TL_WORKORDERLINK WHERE PTWO_PLDETAILID = '"
						+ pldetailsId + "' ";
				String workOrderCount = dbActionTemplate.getSingleValue(sqlworkOrderCount);

				if (workOrderCount.equals("0")) {

					// int [] dataTypesBD = { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};

					/*
					 * sql1.append(" DELETE FROM " + detailTableName + " ");
					 * sql1.append(" WHERE PLDETAILSID = '" + pldetailsId + "' ");
					 */
					// sql=sql1.toString();
					String delDetailId = pcsTlMstSql.delDetail(detailTableName, pldetailsId);
					sqls.add(delDetailId);
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
					CommonFunctions.debugMsg("loss link not exists =" + woLink);

					// dbActionTemplate.executeStatement(sql);
					// String sql2= null;
					String sqldetCount = " SELECT COUNT(*) FROM " + detailTableName + "  WHERE PLMASTERID = '"
							+ plmasterId + "' ";
					String detCount = dbActionTemplate.getSingleValue(sqldetCount);
					/* to change dteCount 0 to 1 and retStatus = "Success_withMaster" */
					if (detCount.equals("1")) {

						// sql2 = " DELETE FROM BAL_PCS_TL_MST WHERE PRLM_KEYID = '" + plmasterId + "'
						// ";
						String delPcsMaster = pcsTlMstSql.delpcsMaster(plmasterId);
						CommonFunctions.debugMsg("master no data exists =" + delPcsMaster);
						sqls.add(delPcsMaster);
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");
						retStatus = "Success_withMaster";
						// dbActionTemplate.executeStatement(sql2);
					}
					// " DELETE FROM PCS_TL_DAY WHERE ENTRYDATE = '"+entryDate+"' AND MACHINEID =
					// '"+machKeyId+"'";
					String delPcsTLDAY = pcsTlMstSql.delPcsTLDAY(entryDate, machKeyId);
					sqls.add(delPcsTLDAY);
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");

					String insertPCSsavrSql = "SAVE_PCS_TL_DAY";
					System.out.println("insertPCSsavrSql>>>>::" + insertPCSsavrSql);
					Object[] insertPCSsavrDatas = { "T", entryDate, "{}", machKeyId, 'N' };
					int[] insertPCSsavrTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR };
					sqls.add(insertPCSsavrSql);
					valueList.add(insertPCSsavrDatas);
					dataTypes.add(insertPCSsavrTypes);
					charList.add("P");
					// if(retStatus == null)
					// retStatus = "Success";
					CommonFunctions.debugMsg("insert save pcs day : " + insertPCSsavrSql);
				}
				char[] sqlType = new char[charList.size()];
				CommonFunctions.debugMsg("lENGTH : " + sqlType.length);
				for (int c = 0; c < sqlType.length; c++) {
					CommonFunctions.debugMsg(c + " : " + charList.get(c));
					sqlType[c] = charList.get(c).charAt(0);
					CommonFunctions.debugMsg(c + " : " + sqlType[c]);
				}

				dbActionTemplate.executeStatement(sqls, valueList, dataTypes, sqlType);

				return retStatus == null ? "Success" : retStatus;
			} else {
				return "Exists";
			}
		}

		catch (Exception e) {
			CommonFunctions.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

	public List<String[]> selectLossRelatedValues(String lossId, String plDetailId) throws Exception {
		List<String[]> lossList = new ArrayList<String[]>();
		String check = dbActionTemplate.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(lossId, plDetailId));
		if (CommonFunctions.isValidKeyId(check)) {
			if (Integer.parseInt(check) > 0) {
				lossList = dbActionTemplate.getDataList(pcsTlLossreasonlinkSql.getLossRelatedSql(lossId, plDetailId));
			}
		}

		return lossList;

	}

	public List<String[]> selectDetailIdValues(String productId, String mchId, String sectId, String entryDate,
			String mstId, String plDetailId) throws Exception {
		String detailTableName = getDetailTableName(sectId, entryDate);
		List<String[]> productionList = new ArrayList<String[]>();
		String count = dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM " + detailTableName + " WHERE MACHINEID='"
				+ mchId + "' AND PRODUCTID='" + productId + "' AND PLMASTERID='" + mstId + "'");
		CommonFunctions.debugMsg("COUNT : " + "SELECT COUNT(*) FROM " + detailTableName + " WHERE MACHINEID='" + mchId
				+ "' AND PRODUCTID='" + productId + "' AND PLMASTERID='" + mstId + "'");
		if (UIUtils.isValidKeyId(count)) {
			if (Integer.parseInt(count) > 0) {
				String sql = "SELECT PLDETAILSID,WNO,TRIMMINGQTY,EXPANSIONQTY , BACKLOGQTY, RAWMATERIALTYPE, WEIGHT  FROM "
						+ detailTableName;
				sql += " WHERE MACHINEID='" + mchId + "' AND PRODUCTID='" + productId + "' AND PLMASTERID='" + mstId
						+ "'";
				productionList = dbActionTemplate.getDataList(sql);
				CommonFunctions.debugMsg("productionList sql: " + sql);
			}
		}
		CommonFunctions.debugMsg("productionList : " + productionList.size());
		return productionList;
	}

	@Override
	public List<String[]> getAllfillgriddata(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

		condParms += "LDATE=";
		if (UIUtils.isValidKeyId(commonFilter.getDtestart()))
			condParms += commonFilter.getDtestart();
		condParms += ";LOSSID=";
		if (UIUtils.isValidKeyId(commonFilter.getLossId()))
			condParms += commonFilter.getLossId();
		condParms += ";MONTH=";
		if (UIUtils.isValidKeyId(commonFilter.getDteEnd()))
			condParms += commonFilter.getDteEnd();
		condParms += ";";
		paramValues.add(condParms);
		paramValues.add(commonParams);

		List<String[]> dataList = dbActionTemplate
				.processFunctionCallsWithColHeaders("PCS_PC_PRODLOG.PCS_FN_OTHERLOSSENTRY", paramValues);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			CommonFunctions.debugMsg("totalCnt...." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonFunctions.debugMsg(dataList);
		return dataList;
	}

	public String getSectionForCell(String cellid) throws Exception {

		String sql = "";
		CommonFunctions.debugMsg("CELLID()===" + cellid);
		sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID='" + cellid + "' ";

		CommonFunctions.debugMsg("sql()===" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public String getFlidOriginalid(String flid) throws Exception {
		String sql = "";
		CommonFunctions.debugMsg("flid()===" + flid);
		sql = " SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID='" + flid + "' ";
		CommonFunctions.debugMsg("sql()===" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	public int getShiftTotaltime(String startdate, String enddate, String shiftId) throws Exception {
		String shiftStarttime = dbActionTemplate.getSingleValue("GEN_TL_SHIFTMST", "SFTM_STARTTIME", "SFTM_KEYID",
				shiftId);
		String shiftEndtime = dbActionTemplate.getSingleValue("GEN_TL_SHIFTMST", "SFTM_ENDTIME", "SFTM_KEYID", shiftId);
		String shiftBreaktime = dbActionTemplate.getSingleValue("GEN_TL_SHIFTMST", "SFTM_BREAKTIME", "SFTM_KEYID",
				shiftId);
		// SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy
		// hh:mm");
		// NEW LINE
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				startdate.trim().length() <= 8 ? "HH:mm:ss" : "dd-MMM-yyyy hh:mm");
		java.util.Date date1 = simpleDateFormat.parse(startdate);
		java.util.Date date2 = simpleDateFormat.parse(enddate);
		CommonFunctions.debugMsg(date1);
		CommonFunctions.debugMsg(date2 + "  enddate " + enddate);
		int difInMinutes = (int) ((date2.getTime() - date1.getTime()) / (1000 * 60));
		difInMinutes = difInMinutes - Integer.parseInt(shiftBreaktime.toString());
		CommonFunctions.debugMsg("difInMinutes :- " + difInMinutes + " shiftBreaktime :- " + shiftBreaktime);
		return difInMinutes;

	}

	@Override
	public List<String[]> getJhEquipmnt(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String cellid = commonFilter.getKey();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (");
		sql.append(
				"	SELECT 'checkvalue','checkBox', 'KeyId' as KeyId,'txtMachineNo' as Machine_Number,'txtMachineName' as Machine_Name  ,'txtCleanTime' as CLEAN ,'txtLubTime' as LUB,'txtInspectTime' as INSPECT,'txtTightenTime' as TIGHTEN,0 as dataorder FROM DUAL");
		sql.append("	UNION ALL");
		sql.append(
				"( select '' as checkvalue,'' as checkBox,'' Keyid,'' as code, 'TOTAL' as total, To_char(sum('')),to_char(sum('')),to_char(sum('')),to_char(sum('')), 2 as Dataorder from dual ");
		sql.append(" GROUP BY '', '', 'TOTAL', '', 2)");
		sql.append("	UNION ALL");
		sql.append(
				"	select '' as CheckValue,'' as checkbox, MCHM_KEYID AS KEYID,MCHM_MACHINENO ,MCHM_MACHINENAME,'','','','',1 as dataorder FROM gen_tl_machinemst WHERE 1=1 AND  MCHM_ACTIVE='Y' AND MCHM_CELLID='"
						+ cellid + "'");
		sql.append(") order by dataorder");

		System.out.println(sql + "  -- sql in pcsEntry");
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

		// CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData.size());

		return gridData;

	}

	public List<String[]> getJhEquipmntSpeed(CommonFilter commonFilter) throws Exception {
// TODO Auto-generated method stub
		String cellid = commonFilter.getKey();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM (");
		sql.append("	SELECT  'KEYID','Machine Number','Machine Name','Quantity','Time',0 as dataorder FROM DUAL");
		sql.append("	UNION ALL");
		sql.append(
				"( select '' Keyid,'' as code, 'TOTAL' as total, To_char(sum('')),to_char(sum('')), 2 as Dataorder from dual ");
		sql.append(" GROUP BY '', '', 'TOTAL', '', 2)");
		sql.append("	UNION ALL");
		sql.append(
				"	select  MCHM_KEYID AS KEYID,MCHM_MACHINENO ,MCHM_MACHINENAME,'','',1 as dataorder FROM gen_tl_machinemst WHERE 1=1 AND  MCHM_ACTIVE='Y' AND MCHM_CELLID='"
						+ cellid + "'");
		sql.append(") order by dataorder");

		System.out.println(sql + "        sql in pcsEntry");
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		// CommonFunctions.debugMsg("GRIDDATA.SIZE"+gridData.size());
		return gridData;

	}

	public BAL_PcsTlJhlossDetail createJhDetails(BAL_PcsTlJhlossDetail newPcsTlJhlossDetail) throws Exception {
		BAL_PcsJhlossDetailSql pcsTljhLossDetailSql = new BAL_PcsJhlossDetailSql();
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		StringBuffer jhLossDetails = new StringBuffer();
		BAL_PcsTlJhlossDetail pcsTlJhlossDetail = new BAL_PcsTlJhlossDetail();
		try {
			CommonFunctions.debugMsg("In Side the Dao impl4    " + pcsTlJhlossDetail.getJhlossCleanTime());

			for (BAL_PcsTlJhlossDetail newPcsTlJhLossDetail : pcsTlJhlossDetail.getJhDetails()) {
				String jhLossKeyid = dbActionTemplate.getSequenceNumber(pcsTljhLossDetailSql.TBL_PCS_TL_JHLOSSDETAILS,
						12, "PCJ", "MMYY", "Y");
				CommonFunctions.debugMsg("In Side the Dao impl");
				newPcsTlJhLossDetail.setJhlossKeyid(jhLossKeyid);
				newPcsTlJhLossDetail.setJhlossCreatedBy(newPcsTlJhLossDetail.getJhlossCreatedBy());
				sqls.add(BAL_PcsJhlossDetailSql.getInsertSql(pcsTljhLossDetailSql.getPlosDbFields(),
						newPcsTlJhLossDetail.getSaveArray()));
				System.out.println("In Side the DAo impl sql  " + sqls);
			}
			dbActionTemplate.executeStatements(sqls);
			return newPcsTlJhlossDetail;
		} catch (Exception e) {
			e.printStackTrace();
			if (UIUtils.isValidKeyId(e.getMessage())) {
				CommonFunctions.debugMsg("MKSAPSPAREINFO  " + e.getMessage());
				throw new Exception(e.getMessage());
			}
		}
		return newPcsTlJhlossDetail;
	}/*
		 * public List<String> updateLosssql (BAL_PcsTlLossreasonlink
		 * pcsTlLossreasonlink, String Pldetailsid, String lossId, String lossValue)
		 * throws Exception { List<String> sqls = new ArrayList<String>(); sqls for
		 * execution
		 * 
		 * //String detailTableName = "PCS_TL_" +
		 * dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		 * "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid()); //detailTableName =
		 * detailTableName.replaceAll("-", "#"); String cycleTime="0"; String
		 * detailTableName =getDetailTableName(pcsTlLossreasonlink.getPlrkCellid(),
		 * pcsTlLossreasonlink.getPlrkDate());
		 * 
		 * CommonFunctions.debugMsg("detailTableName"+detailTableName); String lossMapId
		 * = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD",
		 * "PLCM_KEYID", lossId); CommonFunctions.debugMsg("lossMapId"+lossMapId);
		 * String ParameterCode =
		 * dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
		 * "PLCM_PARAMETERCODE", "PLCM_KEYID", lossId);
		 * CommonFunctions.debugMsg("ParameterCode"+ParameterCode); String lossColName =
		 * getColName(ParameterCode, lossId);
		 * CommonFunctions.debugMsg("loss cOLUMN Name"+lossColName);
		 * CommonFunctions.debugMsg("loss cOLUMN Name"+pcsTlLossreasonlink.
		 * getPlrkMinutes());
		 * pcsTlLossreasonlink.setPlrkMinutes(pcsTlLossreasonlink.getPlrkMinutes().
		 * replace(":", "."));
		 * CommonFunctions.debugMsg("loss cOLUMN Name"+pcsTlLossreasonlink.
		 * getPlrkMinutes());
		 * //pcsTlLossreasonlink.setPlrkKeyid(dbActionTemplate.getSequenceNumber(
		 * pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK)); // set the sequnce number
		 * String checkLossExist =
		 * dbActionTemplate.getSingleValue(pcsTlLossreasonlinkSql.checkLossExistSql(
		 * pcsTlLossreasonlink)); if(UIUtils.isValidKeyId(checkLossExist)) {
		 * if(Integer.parseInt(checkLossExist)>0) throw new
		 * BusinessApplicationExceptions("LOSSEXIST,");
		 * 
		 * } sqls.add(pcsTlLossreasonlinkSql.getUpdateSql(pcsTlLossreasonlinkSql.
		 * getPlrkDbFields(), pcsTlLossreasonlink.getSaveArray())); // add insert sql
		 * for master table
		 * 
		 * BAL_PcsTlDtl pcsTlDtl = new BAL_PcsTlDtl();
		 * cycleTime=pcsTlDtl.getActualcycletime();
		 * sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName,Pldetailsid,
		 * pcsTlDtlSql.getPcs_DbFields() ,pcsTlDtl.getSaveArray(),lossId, lossMapId,
		 * lossColName, lossValue));
		 * //CommonFunctions.debugMsg("test sql1................................."+sqls)
		 * ; ancilliaryTime(pcsTlLossreasonlink,sqls);
		 * 
		 * // dbActionTemplate.executeStatements(sqls); // execute the block of sqls //
		 * getOEECalculation(detailTableName,Pldetailsid);
		 * 
		 * //runBulkSqls(sqls,detailTableName,Pldetailsid,cycleTime);
		 * 
		 * return sqls; }
		 */
	
}