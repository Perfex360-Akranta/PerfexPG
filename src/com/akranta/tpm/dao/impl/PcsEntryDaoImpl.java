package com.akranta.tpm.dao.impl;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsEntryDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.GenTlVisitorsSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorDeptLinkSql;
import com.akranta.tpm.dao.sql.PcsTlAncilliarytimeSql;
import com.akranta.tpm.dao.sql.PcsTlDtlSql;
import com.akranta.tpm.dao.sql.PcsTlLosscaptureSql;
import com.akranta.tpm.dao.sql.PcsTlLosscausemstSql;
import com.akranta.tpm.dao.sql.PcsTlLossreasonlinkSql;
import com.akranta.tpm.dao.sql.PcsTlMstSql;
import com.akranta.tpm.dao.sql.PcsTlOperatordtlSql;
import com.akranta.tpm.dao.sql.PcsTlWorkorderlinkSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLosscapture;
import com.akranta.tpm.model.PcsTlLosscausemst;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.model.PcsTlMst;
import com.akranta.tpm.model.PcsTlOperatordtl;
import com.akranta.tpm.model.PcsTlWorkorderlink;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.PcsEntryServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class PcsEntryDaoImpl implements PcsEntryDao {

	private DBActionTemplate dbActionTemplate;
	private PcsTlMstSql pcsTlMstSql = null;
	private PcsTlDtlSql pcsTlDtlSql = null;
	private PcsTlWorkorderlinkSql pcsTlWorkorderlinkSql = null;
	private PcsTlLossreasonlinkSql pcsTlLossreasonlinkSql = null;
	private PcsTlAncilliarytimeSql pcsTlAncilliarytimeSql = null;
	private PcsTlOperatordtlSql pcsTlOperatordtlSql = null;
	FunctionCallApi fnCallApi;
	private PcsEntryServiceApi pcsEntryServiceApi;

	public PcsEntryDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		pcsTlMstSql = new PcsTlMstSql();
		pcsTlDtlSql = new PcsTlDtlSql();
		pcsTlWorkorderlinkSql = new PcsTlWorkorderlinkSql();
		pcsTlLossreasonlinkSql = new PcsTlLossreasonlinkSql();
		pcsTlAncilliarytimeSql = new PcsTlAncilliarytimeSql();
		pcsTlOperatordtlSql = new PcsTlOperatordtlSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	// -- added by vignesh -- //
	public void PcsEntryDaoImplJwt(String JwtToken) {
		try {
			// oplServiceApi = new OplTlMstServiceApi(JwtToken);
			pcsEntryServiceApi = new PcsEntryServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public List<ComboBox> getCycletimeCombo(String cellId, String machineId, String productId, String entryDate,
			String sectId) throws Exception {

		StringBuffer sql = new StringBuffer();
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		String mouldSectionCode = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "MOULD_SECTION_CODES");
		String sectCode = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_SECTIONMST, "SECT_CODE", "SECT_KEYID",
				sectId);
		CommonMessage.debugMsg("hourlyEntry" + hourlyEntry);
		CommonMessage.debugMsg("mouldSectionCode : " + mouldSectionCode);
		CommonMessage.debugMsg("sectCode : " + sectCode);

		if (hourlyEntry.isEmpty() || hourlyEntry == null || hourlyEntry.equals("N")) {
			sql.append(
					" SELECT DISTINCT CYTM_CAVITY || '##' || PRDM_RAWMATTYPE   || '##' || PRDM_NETWEIGHT AS ID, CYTM_CYCLETIME AS TEXT FROM (");
			sql.append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST");
			sql.append(" WHERE 1 = 1 ");
			sql.append(" AND CYTM_ACTIVE = 'Y' ");
			sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
			sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
			sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
					+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
			sql.append(" UNION       ");
			sql.append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY");
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
					sql.append(" SELECT CYTM_PRODUCTID,CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					sql.append(" AND CYTM_CELLID = '" + cellId + "' ");
					sql.append(" AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(" UNION       ");
					sql.append(" select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY");
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
					sql.append(" SELECT CYTM_PRODUCTID, CYTM_CAVITY, CYTM_CYCLETIME FROM PCS_TL_CYCLETIMEMST ");
					sql.append(" WHERE 1 = 1 ");
					sql.append(" AND CYTM_ACTIVE = 'Y' ");
					sql.append(" AND CYTM_CELLID = '" + cellId + "' AND CYTM_MACHINEID = '" + machineId + "'  ");
					sql.append(" AND CYTM_PRODUCTID = '" + productId + "'  AND '" + entryDate
							+ "'  BETWEEN CYTM_FROMDATE  AND CYTM_TILLDATE ");
					sql.append(
							" UNION    select CYTH_PRODUCTID, CYTH_CAVITY ,CYTH_CYCLETIME from PCS_TL_CYCLETIMEHISTORY  ");
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

		CommonMessage.debugMsg(" sql111 " + sql);
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
		CommonMessage.debugMsg("Inside getParameters Dao Impl..........");
		try {
			String firstDate = date;
			if (CommonFunctions.isValidKeyId(date))
				firstDate = "01" + date.substring(date.indexOf("-"));
			String lastDay = CommonFunctions.getLastDayOfMonth(date);
			lastDay = lastDay.substring(0, lastDay.indexOf("-"));
			String detailTable = getDetailTableName(sectionId, date);
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
			sql.append("(SELECT TO_DATE('" + firstDate + "') + ROWNUM-1 AS TEMPDATE FROM TAB WHERE ROWNUM < = "
					+ lastDay + ")");
			sql.append(" WHERE HOLM_DATE(+)  = TEMPDATE  ");
			// sql.append(" AND HOLM_FACTORY(+) ='"+factId+"' ");
			sql.append(" )");
			// sql.append(" WHERE SFTM_FACTORYID = '"+factId+"' AND SFTM_SHIFTORDER <=
			// 3),");
			sql.append(" WHERE SFTM_SHIFTORDER <= 3),");
			sql.append("(SELECT DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,");
			sql.append("SFTM_KEYID SFTMKEYID,PRLM_ENTRYDATE FROM PCS_TL_MST,GEN_TL_SHIFTMST,");
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

			CommonMessage.debugMsg("Str	ing sql=" + sql.toString());

			List<String[]> monthList = dbActionTemplate.getDataList(sql.toString());

			return monthList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in CALENDAR dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getCalendar(String factId, String date, String userId) throws Exception {
		CommonMessage.debugMsg("Inside getParameters Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(date);
			paramValues.add(userId);
			CommonMessage.debugMsg("date" + date);

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

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			List<String[]> monthList = dbActionTemplate.getDataList(sql);

			return monthList;

		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in CALENDAR dao impl" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<String[]> getParameters(String sectId) throws Exception {
		CommonMessage.debugMsg("Inside getParameters Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			// paramValues.add(cellId);
			CommonMessage.debugMsg("sectId==" + sectId);
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			// sql1.append(" SELECT ROWNUM AS RNO,A.* FROM ( ");
			sql1.append(
					" SELECT PLCM_KEYID AS KEYID, PLCM_MAPFIELD, PLCM_MAPCOLUMNNO, PLCM_SHOWPOPUP ,'' AS RES1,'' AS RES2,'' AS RES3,");
			sql1.append(" PLCM_KEYID AS LOSSID,PLCM_ISPARENT AS HEADING,PLCM_PARAMETERCODE AS LOSSPARAM,     ");
			sql1.append(
					" DECODE(PLCM_SHOWLOSSNO,'Y',PLCM_LOSSNO,'') AS LNO, PLCM_PARAMETERNAME AS PARAMETERS, INITCAP(UOMM_CODE) AS UOM,     ");
			sql1.append(
					" PLCM_PARENTID, PLCM_ENTRYTYPE,  PLCM_DATATYPE, PLCM_MAXVALUE,  PLCM_SHOWLOSSNO, PLCM_ROWCOLOR,PLCM_ISHIDDEN,  PLCM_ORDER,PLCM_ISLOSS ");
			sql1.append(
					" FROM    PCS_TL_LOGCONFIGURATION,ADM_TL_UOMMST,PCS_TL_LOSSCELLLINK WHERE    PLCM_UOM = UOMM_KEYID (+)  ");
			sql1.append(" AND PLFL_PARAMETERID = plcm_keyid  AND PLFL_ACTIVE = 'Y'  ");

			sql1.append(" AND  PLCM_ISHIDDEN <> 'Y' ");

			sql1.append(" AND PLFL_CELLID  IN ( SELECT MAX(CELL_KEYID) FROM GEN_TL_CELLMST, PCS_TL_LOSSCELLLINK ");
			sql1.append("  WHERE PLFL_CELLID = CELL_KEYID AND CELL_SECTIONID='" + sectId + "') ");

			// sql1.append(" AND PLFL_CELLID = '" + cellId + "' ");

			sql1.append(" ORDER BY PLCM_ORDER,PLCM_LOSSNO ");
			// sql1.append(" ) A ");

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			// List<String[]> studentList =
			// dbActionTemplate.getDataList(sql);//getDataList(sql);
			// List<String[]> pcsList = dbActionTemplate.getDataListWithColHeader(sql,
			// paramValues);
			List<String[]> pcsList = dbActionTemplate.getDataListWithColHeader(sql, null);
			return pcsList;

		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getParameters dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getProdLoss(String shiftId, String entryDate, String sectId, String cellId, String mchId,
			String factId) throws Exception {
		CommonMessage.debugMsg("Inside getParameters Dao Impl.........." + sectId);
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
				String detailTableName = getDetailTableName(sectId, entryDate);

				CommonMessage.debugMsg("detailTableName==" + detailTableName);

				CommonMessage.debugMsg("sectId==" + sectId);
				// String sql = PcsTlMstSql.getProdLossSql(shiftId,entryDate,cellId, mchId,
				// detailTableName);
				String sql = PcsTlMstSql.getProdLossSql(shiftId, entryDate, sectId, cellId, mchId, detailTableName);

				CommonMessage.debugMsg("ssql===" + sql);
				lossList = dbActionTemplate.getDataListWithColHeader(sql, params);
				return lossList;
			} else if (cnt == 0)
				throw new Exception();

		} catch (Exception e) {
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in getProdLoss dao impl--" + e.getMessage());
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
			String detailTableName = getDetailTableName(sectionId, entryDate);
			String sql = PcsTlMstSql.getProductDetailSql(shiftId, entryDate, cellId, mchId, detailTableName, dtlId);
			List<String[]> lossList = dbActionTemplate.getDataListWithColHeader(sql, params);
			return lossList;
		} catch (Exception e) {
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in getProdLoss dao impl--" + e.getMessage());
			throw new Exception();
		}

	}

	public List<String[]> getEmployeeGrid(String pldetailsId) {
		CommonMessage.debugMsg("Inside entry grid Dao Impl..........");
		try {
			CommonMessage.debugMsg("pldetailsId===" + pldetailsId);

			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(" SELECT POPD_PLDETAILSID, POPD_PLEMPLOYEEID, EMPM_CODE || '-' ||EMPM_NAME  ");
			sql1.append(" FROM PCS_TL_OPERATORDTL, GEN_TL_EMPLOYEEMST  ");
			sql1.append(" WHERE POPD_PLDETAILSID =  '" + pldetailsId + "' ");
			sql1.append(" AND POPD_PLEMPLOYEEID = EMPM_KEYID  ");

			sql = sql1.toString();

			CommonMessage.debugMsg("getEmployeeGrid sql=" + sql);
			List<String[]> empList = dbActionTemplate.getDataList(sql);
			return empList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getEmployeeGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getEntryGrid(String date, String shift, String sectId, String mchId) {
		CommonMessage.debugMsg("Inside entry grid Dao Impl..........");
		try {
			CommonMessage.debugMsg("section id ====" + sectId);

			// String detailTableName = "PCS_TL_" +
			// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
			// "SECT_KEYID", sectId);
			// detailTableName = detailTableName.trim().replaceAll("-", "#");

			String detailTableName = getDetailTableName(sectId, date);

			CommonMessage.debugMsg("detailTableName===" + detailTableName);

			StringBuffer sql1 = new StringBuffer();
			String sql = "";
//			sql1.append(" SELECT  PLDETAILSID, PTWO_KEYID, PRDM_MODEL, PRDM_KEYID, PRDM_NAME, PTWO_WNO, PTWO_CALENDARTIME, PTWO_NOPLANINMINS, PTWO_PLANNEDQTY,  ");
//			sql1.append(" THEORITICALCYCLETIME, ACTUALCYCLETIME, CAVITYAVAILABLE, CAVITYUSED, PTWO_PRODUCEDQTY, REMARKS,  '' AS LOSSLINK  ");
//			sql1.append(" FROM PCS_TL_MST , PCS_TL_WORKORDERLINK, " + detailTableName + " , PCS_TL_PRODUCTMST   ");  
//			sql1.append(" WHERE PRLM_KEYID = PLMASTERID AND PRODUCTID = PRDM_KEYID  ");
//			sql1.append(" AND PTWO_PLDETAILID = PLDETAILSID ");
//			sql1.append(" AND TRUNC(PRLM_ENTRYDATE) = '" + date + "' AND PRLM_SHIFTID = '" + shift + "'   "); 
//			sql1.append(" AND MACHINEID = '" + mchId + "'  " );

			sql1.append(
					" SELECT  DISTINCT DECODE(PLDETAILSID,PLRK_PLDETAILID, 1,0) AS TICK,PLDETAILSID, '' PTWO_KEYID, PRDM_MODEL, ");
			sql1.append(
					" PRDM_KEYID, PRDM_NAME || '-' || PRDM_DRAWINGNO as PRDM_NAME , WNO, CALENDARTIME, NOPLANINMINS, PLANNEDQTY,  ");
			sql1.append(
					" THEORITICALCYCLETIME, ACTUALCYCLETIME, CAVITYAVAILABLE, CAVITYUSED, PRODUCEDQTY, TRIMMINGQTY, EXPANSIONQTY,BACKLOGQTY,'' AS LOSSLINK  ");
			sql1.append("  , RAWMATERIALTYPE, WEIGHT , REMARKS");
			sql1.append(" FROM PCS_TL_MST , " + detailTableName + " , PCS_TL_PRODUCTMST,PCS_TL_LOSSREASONLINK   ");
			sql1.append(
					" WHERE PRLM_KEYID = PLMASTERID AND PRODUCTID = PRDM_KEYID (+) AND PLDETAILSID = PLRK_PLDETAILID(+) ");
			sql1.append(" AND TRUNC(PRLM_ENTRYDATE) = '" + date + "' AND PRLM_SHIFTID = '" + shift + "'   ");
			sql1.append(" AND MACHINEID = '" + mchId + "' ORDER BY PLDETAILSID DESC");

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	Entry Grid sql=" + sql);
			List<String[]> entryList = dbActionTemplate.getDataList(sql);
			return entryList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getEntryGrid dao impl" + e.getMessage());
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
			CommonMessage.debugMsg("qtyList: " + qtyList.size());
			CommonMessage.debugMsg("qtyList: " + qtyList.get(0)[0]);
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

		CommonMessage.debugMsg("efList: " + efList.size());
		CommonMessage.debugMsg("efList: " + efList.get(0)[0]);
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
			CommonMessage.debugMsg("allowList: " + allowList.size());
			CommonMessage.debugMsg("allowList: " + allowList.get(0)[0]);
		}

		return allowList;
	}

	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId) throws Exception {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(
				"  Select  DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY')  ");
		sql1.append(" from PCS_TL_MST,GEN_TL_SHIFTMST, (SELECT DISTINCT PLMASTERID FROM " + detailTable
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
		CommonMessage.debugMsg("sqlsss: " + sql);
		List<String[]> eedList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("EntryExistsDates: " + eedList.size());
		if (eedList.size() > 0)
			CommonMessage.debugMsg("EntryExistsDates: " + eedList.get(0)[0]);
		return eedList;

	}

	public List<String[]> getHolidayDates(String factId, String entryDate) throws Exception {

		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append("  SELECT TO_CHAR(HOLM_DATE,'DD-Mon-YYYY'), HOLM_HOLIDAYFLAG FROM GEN_TL_HOLIDAYMST  ");
		sql1.append("  WHERE to_char(HOLM_DATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM') ");
		// sql1.append(" AND HOLM_FACTORY='" + factId + "' ");
		sql1.append(" ORDER BY HOLM_DATE ");

		sql = sql1.toString();
		CommonMessage.debugMsg("sqlsss: " + sql);
		List<String[]> holiList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("EntryExistsDates: " + holiList.size());
		if (holiList.size() > 0)
			CommonMessage.debugMsg("EntryExistsDates: " + holiList.get(0)[0]);
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

		openMsrList = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISOPENMSR", paramValues);

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
		 * sql1.append(" FROM PCS_TL_MLD#IP,PCS_TL_MST  ");
		 * sql1.append(" WHERE PLMASTERID = PRLM_KEYID  AND PRLM_SECTIONID = '" + sectId
		 * + "'   ");
		 * sql1.append("  AND to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" +
		 * entryDate + "'),'YYYYMM') "); sql1.append(" AND CELLID = '" + cellId +
		 * "'  AND PRLM_TIMEORQTY = 'T' AND PRLM_LOGTYPE = 'S' ");
		 * sql1.append(" GROUP BY PRLM_ENTRYDATE,PRLM_SHIFTID) ");
		 * sql1.append(" ORDER BY TRUNC(PRLM_ENTRYDATE),PRLM_SHIFTID " );
		 * 
		 * sql=sql1.toString(); CommonMessage.debugMsg("sqlsss: " +sql);
		 * List<String[]> oeeList = dbActionTemplate.getDataList(sql);
		 * CommonMessage.debugMsg("OEE Calculation: " +oeeList.size()); if
		 * (oeeList.size() >0 ) CommonMessage.debugMsg("OEE Calculation: "
		 * +oeeList.get(0)[0]);
		 */
		detailTable = getDetailTableName(sectId, entryDate);
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

		CommonMessage.debugMsg("closedMsrList.get(0)[0]" + closedMsrList.get(0)[0]);

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

			CommonMessage.debugMsg("sqll: " + sql);

			dbActionTemplate.executeStatement(sql);

			return "1";
		} catch (Exception e) {
			return "0";
		}
	}

	public String getIsHourlyEntry() throws Exception {
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "INT_HOURLY");
		CommonMessage.debugMsg("hourlyEntry: " + hourlyEntry);
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
			CommonMessage.debugMsg("Section Code Checking : " + expNotMand + "---->" + sectCode);
			if (expNotMand.trim().equals(sectCode.trim()))
				sectCode = "Y";
		}
		mandArr[1] = sectCode;
		return mandArr;
	}

	public String getFieldsForPcs() throws Exception {
		String fieldsForPcs = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE",
				"CNFM_CODE", "FOR_PCS");
		CommonMessage.debugMsg("getFieldsForPcs: " + fieldsForPcs);
		if (fieldsForPcs.isEmpty() || fieldsForPcs == null)
			fieldsForPcs = "";
		return fieldsForPcs;
	}

	public List<String[]> getIsPcsEnabled(String condParam) throws Exception {
		try {

			List<String> paramValues = new ArrayList<String>();
			paramValues.add(condParam);

			String Shiftsql = getchkshift(condParam);
			CommonMessage.debugMsg("Shiftsql....." + Shiftsql);
			String count = dbActionTemplate.getSingleValue(Shiftsql);

			int cnt = Integer.parseInt(count);
			CommonMessage.debugMsg("count....." + count);

			if (cnt > 0) {
				List<String[]> pcsenabled = dbActionTemplate
						.processFunctionCalls("PCS_PC_PRODUCTIONCALC.ChkIsPCSEnabled", paramValues);
				return pcsenabled;
			} else
				return null;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private String getchkshift(String condParam) {
		StringBuffer sql = new StringBuffer();
		String[] data = condParam.split(";");
		CommonMessage.debugMsg("test12..." + data[1]);
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
		// sql.append(" AND SFTM_FACTORYID = '"+fact[1]+"' ");
		sql.append(" AND TO_DATE('" + date[1]
				+ "' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= SYSDATE ");
		sql.append(condSql);

		return sql.toString();
	}

	// -- ALtered by Vignesh 12 NOv 2025
	// -----------------------------------------------------------//

//	public String getDetailTableName(String sectId, String entryDate) throws Exception {
//		
//		CommonMessage.debugMsg("Inside getDetailTableName..........");
//		try
//		{	
//			String sql = "";
//			
//			String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
//			// ---------- Altered # to _ by Vignesh 10Nov2025 ------------------------//
//			detailTableName = detailTableName.trim().replaceAll("-", "_");
//			// ---------- Altered # to _ by Vignesh 10Nov2025 ------------------------//
//			detailTableName = detailTableName.replaceAll(" ", "");
//			detailTableName = detailTableName.toLowerCase();
//			CommonMessage.debugMsg("detailTableNamee.........."+detailTableName);
//			CommonMessage.debugMsg(" printing name tablename for console " + detailTableName );
//			detailTableName = detailTableName.replaceAll("&", "");
//			detailTableName = detailTableName.replaceAll(",", "");
//			detailTableName = detailTableName.replaceAll("/", "");
//			//detailTableName = detailTableName.replaceAll("(", "");
//			CommonMessage.debugMsg("detailTableNamee.........."+detailTableName);
//			String avialTable = dbActionTemplate.getSingleValue("pg_tables", "tablename", "tablename", detailTableName);
//			CommonMessage.debugMsg("avialTable.........."+avialTable);
//			if (! UIUtils.isValidKeyId(avialTable))
//				avialTable =" ";
//			
//			if (avialTable.equals(detailTableName))
//			{
//				CommonMessage.debugMsg("Table already exists ");
//			}
//			else
//			{
//				CommonMessage.debugMsg("Create table for section");
//				sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM PCS_TL_DTL ";
//				CommonMessage.debugMsg("sqlsqlsql"+sql);
//				dbActionTemplate.executeStatement(sql);
//				
//				String pkConst = detailTableName.replace("PCS_TL_", ""); 
//				sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_PCS_TL_DTL_"+pkConst+" PRIMARY KEY (PLDETAILSID) ";				
//				dbActionTemplate.executeStatement(sql);
//				
//				sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_"+pkConst+" "; 
//				sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES PCS_TL_MST (PRLM_KEYID) ";
//				dbActionTemplate.executeStatement(sql);
//
//			}
//			CommonMessage.debugMsg("avialTable.........."+avialTable);
//			String sql1="SELECT to_date('" + entryDate + "') - TO_date(CNFM_FROMDATE)  FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'";
//			CommonMessage.debugMsg("sql1111"+sql1);
//			
//			String diff = dbActionTemplate.getSingleValue(sql1);		
//			
//			CommonMessage.debugMsg("diff"+diff);
//			
//			if ( Integer.parseInt(diff) <0 ) 
//				detailTableName="PCS_TL_DTL";
//				//detailTableName=detailTableName;
//			//else
//				
//
//			return detailTableName;
//		}
//	
//		catch(Exception e)
//		{
//			CommonMessage.debugMsg("Exception in getDetailTableName dao impl"+e.getMessage());
//		}
//		return null;		
//	}

	public String getDetailTableName(String sectId, String entryDate) throws Exception {

		CommonMessage.debugMsg("Inside getDetailTableName..........");
		try {
			// ---------- build detail table name (same logic) ----------
			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);

			// Normalize to a safe unquoted Postgres identifier
			detailTableName = (detailTableName == null ? "" : detailTableName).trim().replace("-", "_").replace(" ", "")
					.replace("&", "").replace(",", "").replace("/", "").toLowerCase();

			CommonMessage.debugMsg("detailTableNamee.........." + detailTableName);
			CommonMessage.debugMsg(" printing name tablename for console " + detailTableName);

			// ---------- exists? (Postgres catalog) ----------
			String avialTable = dbActionTemplate.getSingleValue(
					"SELECT tablename FROM pg_tables WHERE tablename = '" + detailTableName.replace("'", "''") + "'");
			CommonMessage.debugMsg("avialTable.........." + avialTable);
			if (!UIUtils.isValidKeyId(avialTable))
				avialTable = "";

			if (!detailTableName.equals(avialTable)) {
				CommonMessage.debugMsg("Create table for section");

				String sql = "CREATE TABLE " + detailTableName + " AS SELECT * FROM pcs_tl_dtl";
				CommonMessage.debugMsg("sqlsqlsql" + sql);
				dbActionTemplate.executeStatement(sql);

				String pkConst = detailTableName.replace("pcs_tl_", "");
				sql = "ALTER TABLE " + detailTableName + " ADD CONSTRAINT pk_pcs_tl_dtl_" + pkConst
						+ " PRIMARY KEY (pldetailsid)";
				dbActionTemplate.executeStatement(sql);

				sql = "ALTER TABLE " + detailTableName + " ADD CONSTRAINT fk_plmasterid_" + pkConst
						+ " FOREIGN KEY (plmasterid) REFERENCES pcs_tl_mst (prlm_keyid)";
				dbActionTemplate.executeStatement(sql);
			} else {
				CommonMessage.debugMsg("Table already exists ");
			}

			CommonMessage.debugMsg("avialTable.........." + avialTable);

			// ---------- Postgres-safe date difference ----------
			// Accept both "DD-Mon-YYYY" and "DD-Mon-YYYY HH24:MI"
			String ed = (entryDate == null ? "" : entryDate.trim());
			boolean hasTime = ed.contains(":");
			// If there is time, keep as-is; else keep just date part
			String edForSql = hasTime ? ed : (ed.contains(" ") ? ed.substring(0, ed.indexOf(' ')) : ed);

			// Build the correct expression (always cast to ::date for days difference)
			String sql1;
			if (hasTime) {
				// Example: 12-Nov-2025 13:30
				sql1 = "SELECT (to_timestamp('" + edForSql.replace("'", "''")
						+ "', 'DD-Mon-YYYY HH24:MI')::date - cnfm_fromdate::date) "
						+ "FROM adm_tl_configurationmst WHERE cnfm_code = 'TPMSTARTDATE'";
			} else {
				// Example: 12-Nov-2025
				sql1 = "SELECT (to_date('" + edForSql.replace("'", "''")
						+ "', 'DD-Mon-YYYY')::date - cnfm_fromdate::date) "
						+ "FROM adm_tl_configurationmst WHERE cnfm_code = 'TPMSTARTDATE'";
			}

			CommonMessage.debugMsg("sql1111" + sql1);

			String diffStr = dbActionTemplate.getSingleValue(sql1);
			CommonMessage.debugMsg("diff " + diffStr);

			int diff = 0;
			if (diffStr != null && diffStr.trim().length() > 0) {
				diff = Integer.parseInt(diffStr.trim());
			}

			// If the selected entry date is before TPM start date, use the base table
			if (diff < 0) {
				detailTableName = "pcs_tl_dtl";
			}

			return detailTableName;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getDetailTableName dao impl" + e.getMessage());
			// IMPORTANT: never propagate null; fall back so callers don't emit ", null" in
			// FROM
			return "pcs_tl_dtl";
		}
	}

	// ----- ALtered by Vignesh 12 NOv 2025
	// -----------------------------------------------------------//

	public String getMasterKeyid(String entryDate, String shift, String cellId) throws Exception {

		CommonMessage.debugMsg("Inside getMasterKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(" SELECT PRLM_KEYID FROM PCS_TL_MST  ");

			String condSql = "";
			condSql += " WHERE TRUNC(PRLM_ENTRYDATE) = '" + entryDate + "'";
			condSql += " AND PRLM_SHIFTID = '" + shift + "'";
			condSql += " AND PRLM_CELLID = '" + cellId + "'";

			sql1.append(condSql);

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			String keyId = dbActionTemplate.getSingleValue(sql);
			return keyId;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getMasterKeyid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getLastShiftProduct(String sectId, String cellId, String mchId, String entryDate,
			String shift) throws Exception {

		CommonMessage.debugMsg("Inside getLastShiftProduct Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			// String detailTableName = "PCS_TL_" +
			// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
			// "SECT_KEYID", sectId);
			// detailTableName = detailTableName.replaceAll("-", "#");

			String detailTableName = getDetailTableName(sectId, entryDate);
			String sql = "";

			sql1.append(
					" SELECT NVL(MAX(PRODUCTID),'') AS PRODUCTID,  MAX(WNO) AS WNO,max( prlm_entrydate),max(prlm_shiftid) ");
			sql1.append(" FROM PCS_TL_MST, " + detailTableName + "  ");
			sql1.append(
					" WHERE PLMASTERID = PRLM_KEYID  AND CELLID ='" + cellId + "' AND MACHINEID ='" + mchId + "'  ");
			sql1.append(" AND to_char(PRLM_ENTRYDATE,'yyyymmdd') <= to_char(to_date('" + entryDate + "'),'yyyymmdd') ");
			sql1.append(" AND (PRLM_ENTRYDATE, PRLM_SHIFTID) IN ( ");
			sql1.append(" SELECT MAX(PRLM_ENTRYDATE), MAX(PRLM_SHIFTID) FROM PCS_TL_MST, " + detailTableName + " ");
			sql1.append(" WHERE PLMASTERID = PRLM_KEYID  AND CELLID ='" + cellId + "' AND MACHINEID ='" + mchId + "'");
			sql1.append(" AND  prlm_entrydate in ( ");
			sql1.append(" SELECT MAX(prlm_entrydate) FROM PCS_TL_MST, " + detailTableName + " ");
			sql1.append(" WHERE PLMASTERID = PRLM_KEYID  AND CELLID ='" + cellId + "' AND MACHINEID ='" + mchId + "' ");
			sql1.append(" AND to_char(PRLM_ENTRYDATE,'yyyymmdd') <= to_char(to_date('" + entryDate + "'),'yyyymmdd') ");
			sql1.append(" ) ");
			sql1.append(" ) ");
			/*
			 * sql1.
			 * append(" SELECT NVL(MAX(PRODUCTID),'') AS PRODUCTID,  MAX(WNO) AS WNO,max( prlm_entrydate), max(prlm_shiftid) FROM PCS_TL_MST, "
			 * + detailTableName + " ");
			 * sql1.append(" WHERE PLMASTERID = PRLM_KEYID  AND CELLID ='" + cellId +
			 * "' AND MACHINEID ='" + mchId + "' ");
			 * sql1.append(" AND to_char(PRLM_ENTRYDATE,'yyyymmdd') <= to_char(to_date('"
			 * +entryDate+"'),'yyyymmdd')"); CHANGED ON 08-OCT-2013
			 */
			/*
			 * sql1.append(" AND (PRLM_ENTRYDATE,PRLM_SHIFTID) IN ( "); sql1.
			 * append(" SELECT MAX(PRLM_ENTRYDATE), MAX(PRLM_SHIFTID) FROM PCS_TL_MST, " +
			 * detailTableName + " " ); sql1.append(" 	WHERE PRLM_CELLID ='" + cellId +
			 * "' AND MACHINEID ='" + mchId + "'  "); sql1.
			 * append(" AND PRLM_ENTRYDATE IN (SELECT MAX(PRLM_ENTRYDATE) FROM PCS_TL_MST, "
			 * + detailTableName + " ") ; sql1.append(" 	WHERE PRLM_CELLID ='" + cellId +
			 * "' AND MACHINEID ='" + mchId + "' ");
			 * sql1.append(" 	AND PRLM_KEYID=PLMASTERID AND PRLM_ENTRYDATE <='" +
			 * entryDate + "' ) "); sql1.append(" ) ");
			 */

			sql = sql1.toString();

			CommonMessage.debugMsg("Product Filling sql=" + sql);

			// String keyId= dbActionTemplate.getSingleValue(sql);
			// return keyId;
			List<String[]> prevData = dbActionTemplate.getDataList(sql);
			return prevData;

		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getMasterKeyid dao impl" + e.getMessage());
		}
		return null;
	}
	// -- CHAnging for shift displayed in UI -- VIGNESH 10DEC2025
	// --------------------------------------//

//	public String getCurrentShift(String factId) throws Exception {
//		
//		String dateTime = CommonFunctions.dateTimeNow();		
//		Date currTime = CommonFunctions.getDate(dateTime, CommonFunctions.DATE_TIME_FORMAT_NOW);
//		CommonMessage.debugMsg(" currTime "+ currTime.getTime());
//		
//		CommonMessage.debugMsg(" currTime.getTime() "+ currTime.getTime());
//		
//		String sql =" ";
//		StringBuffer sf = new StringBuffer();
//		sf.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST + " ");
//		//sf.append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY')||' ' ||  '" + currTime.getTime() + "'  ");
//		sf.append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(SYSDATE,'HH24:MI') ");
//		sf.append(" BETWEEN    TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
//		sf.append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' ");
//		
//		
//		CommonMessage.debugMsg(" sf.toString()== "+ sf.toString());
//		
//		String shiftId = dbActionTemplate.getSingleValue(sf.toString());
//		
//		if (UIUtils.isValidDate(shiftId)) 
//			return shiftId;	
//		
//		StringBuffer sf1 = new StringBuffer();
//		sf1.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST + " ");
//		//sf1.append(" WHERE   TO_CHAR(SFTM_ENDTIME ,'DD-MON-YYYY') ||' ' || '" + currTime.getTime() + "'  ");
//		sf1.append(" WHERE   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(SYSDATE,'HH24:MI') ");
//		sf1.append(" BETWEEN TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
//		sf1.append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' ");
//		
//		shiftId = dbActionTemplate.getSingleValue(sf1.toString());
//		
//		CommonMessage.debugMsg("sft11=="+sf1.toString());
//		if (UIUtils.isValidDate(shiftId)) 
//			return shiftId;	
//		return "";
//		
//	}
	/*
	 * public String getCurrentShift(String factId) throws Exception {
	 * 
	 * String dateTime = CommonFunctions.dateTimeNow(); Date currTime =
	 * CommonFunctions.getDate(dateTime, CommonFunctions.DATE_TIME_FORMAT_NOW);
	 * CommonMessage.debugMsg(" currTime "+ currTime.getTime());
	 * 
	 * CommonMessage.debugMsg(" currTime.getTime() "+ currTime.getTime());
	 * 
	 * String sql =" "; StringBuffer sf = new StringBuffer();
	 * 
	 * sf.append("SELECT SFTM_KEYID "); sf.append("FROM GEN_TL_SHIFTMST ");
	 * sf.append(" WHERE SFTM_ACTIVE = 'Y' ");
	 * sf.append("   AND CURRENT_TIME BETWEEN SFTM_STARTTIME AND SFTM_ENDTIME ");
	 * 
	 * sf.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST +
	 * " "); //sf.append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY')||' ' ||  '"
	 * + currTime.getTime() + "'  "); sf.
	 * append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(SYSDATE,'HH24:MI') "
	 * ); sf.append(" BETWEEN    TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
	 * sf.
	 * append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' "
	 * );
	 * 
	 * 
	 * 
	 * CommonMessage.debugMsg(" sf.toString()== sf.toString()== "+ sf.toString());
	 * 
	 * String shiftId = dbActionTemplate.getSingleValue(sf.toString());
	 * 
	 * if (UIUtils.isValidDate(shiftId)) return shiftId;
	 * 
	 * StringBuffer sf1 = new StringBuffer();
	 * 
	 * sf1.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST +
	 * " "); //sf1.append(" WHERE   TO_CHAR(SFTM_ENDTIME ,'DD-MON-YYYY') ||' ' || '"
	 * + currTime.getTime() + "'  "); sf1.
	 * append(" WHERE   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(CURRENT_DATE,'HH24:MI') "
	 * ); sf1.append(" BETWEEN TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
	 * sf1.
	 * append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' "
	 * );
	 * 
	 * 
	 * sf1.append(" SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST "); sf1.
	 * append(" WHERE NOW()::time BETWEEN SFTM_STARTTIME::time AND SFTM_ENDTIME::time"
	 * ); sf1.append(" AND SFTM_ACTIVE = 'Y'");
	 * 
	 * CommonMessage.debugMsg("sft11==  sft11==  sft11=="+sf1.toString());
	 * 
	 * shiftId = dbActionTemplate.getSingleValue(sf1.toString());
	 * 
	 * if (UIUtils.isValidDate(shiftId)) return shiftId; return "";
	 * 
	 * }
	 */

	public String getCurrentShift(String factId) throws Exception {

		String dateTime = CommonFunctions.dateTimeNow();
		Date currTime = CommonFunctions.getDate(dateTime, CommonFunctions.DATE_TIME_FORMAT_NOW);
		CommonMessage.debugMsg(" currTime " + currTime.getTime());

		CommonMessage.debugMsg(" currTime.getTime() " + currTime.getTime());

		String sql = " ";
		StringBuffer sf = new StringBuffer();

		sf.append("SELECT SFTM_KEYID ");
		sf.append("FROM GEN_TL_SHIFTMST ");
		sf.append(" WHERE SFTM_ACTIVE = 'Y' ");
		sf.append("   AND CURRENT_TIME BETWEEN SFTM_STARTTIME AND SFTM_ENDTIME ");
		/*
		 * sf.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST +
		 * " "); //sf.append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY')||' ' ||  '"
		 * + currTime.getTime() + "'  "); sf.
		 * append(" WHERE   TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(SYSDATE,'HH24:MI') "
		 * ); sf.append(" BETWEEN    TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
		 * sf.
		 * append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' "
		 * );
		 */

		CommonMessage.debugMsg(" sf.toString()== sf.toString()== " + sf.toString());

		String shiftId = dbActionTemplate.getSingleValue(sf.toString());

		if (UIUtils.isValidDate(shiftId))
			return shiftId;

		StringBuffer sf1 = new StringBuffer();
		/*
		 * sf1.append(" SELECT  SFTM_KEYID  FROM  " + TableNames.TBL_GEN_TL_SHIFTMST +
		 * " "); //sf1.append(" WHERE   TO_CHAR(SFTM_ENDTIME ,'DD-MON-YYYY') ||' ' || '"
		 * + currTime.getTime() + "'  "); sf1.
		 * append(" WHERE   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY') || ' ' || TO_CHAR(CURRENT_DATE,'HH24:MI') "
		 * ); sf1.append(" BETWEEN TO_CHAR(SFTM_STARTTIME,'DD-MON-YYYY HH24:MI') ");
		 * sf1.
		 * append(" AND   TO_CHAR(SFTM_ENDTIME,'DD-MON-YYYY HH24:MI') AND SFTM_ACTIVE = 'Y' "
		 * );
		 */

		/*
		 * sf1.append(" SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST "); sf1.
		 * append(" WHERE NOW()::time BETWEEN SFTM_STARTTIME::time AND SFTM_ENDTIME::time"
		 * ); sf1.append(" AND SFTM_ACTIVE = 'Y'");
		 */

		sf1.append("SELECT SFTM_KEYID FROM GEN_TL_SHIFTMST ");
		sf1.append(" WHERE (Now()::time BETWEEN SFTM_STARTTIME::time  AND to_timestamp('23:59','HH24:MI')::time  OR ");
		sf1.append(" Now()::time BETWEEN   to_timestamp('00:00','HH24:MI')::time AND SFTM_ENDTIME::time) AND ");
		sf1.append(" SFTM_ENDTIME::time < to_timestamp('06:01','HH24:MI')::time ");
		sf1.append(" AND SFTM_ACTIVE = 'Y' ");

		CommonMessage.debugMsg("sft11==  sft11==  sft11==" + sf1.toString());

		shiftId = dbActionTemplate.getSingleValue(sf1.toString());

		if (UIUtils.isValidDate(shiftId))
			return shiftId;

		return shiftId;

		// return  "";

	}

// ------------ cHAnging for shift displayed in UI -- VIGNESH 10DEC2025 --------------------------------------//
	public String getShiftKeyid(String factId, String shiftCode) throws Exception {

		CommonMessage.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" select * from GEN_TL_SHIFTMST   ");
			// sql1.append(" WHERE SFTM_SHIFTORDER ='" + shiftCode + "' ");
			sql1.append(" WHERE SFTM_CODE ='" + shiftCode + "'     ");
			// sql1.append(" AND SFTM_FACTORYID ='" + factId + "' ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			String keyId = dbActionTemplate.getSingleValue(sql);
			return keyId;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return " ";
		}

	}

	public List<String[]> getShiftStarEndTime(String shiftId) throws Exception {

		CommonMessage.debugMsg("Inside getShiftStarEndTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" SELECT  TO_CHAR(SFTM_STARTTIME,'HH24:MI'),  TO_CHAR(SFTM_ENDTIME,'HH24:MI') ");
			sql1.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_KEYID='" + shiftId + "' ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			List<String[]> shiftStrEnd = dbActionTemplate.getDataList(sql);
			return shiftStrEnd;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}

	}

	public List<String[]> getShiftEndTime() throws Exception {

		CommonMessage.debugMsg("Inside getShiftStarEndTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			sql1.append(" SELECT  TO_CHAR(SFTM_STARTTIME,'HH24:MI'),  TO_CHAR(SFTM_ENDTIME,'HH24:MI') ");
			sql1.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER= 3 ");
			sql1.append(" AND SFTM_ACTIVE='Y' ");

			sql = sql1.toString();

			CommonMessage.debugMsg("Str	ing sql=" + sql);

			List<String[]> shiftStrEnd = dbActionTemplate.getDataList(sql);
			return shiftStrEnd;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getShiftKeyid dao impl" + e.getMessage());
			return null;
		}

	}

	// ------------------------- Vignesh 10Nov2025 -----------------------------//

	public List<String[]> getReasonLossNo(String reasonId) throws Exception {
		CommonMessage.debugMsg("Inside getShiftKeyid Dao Impl..........");
		try {
			StringBuilder sql1 = new StringBuilder();

			// Postgres-compatible: explicit JOINs; preserve selected column names
			sql1.append("SELECT ").append("  p.plpm_mainloss AS \"PLPM_MAINLOSS\", ")
					.append("  u.uomm_code     AS \"UOMM_CODE\" ").append("FROM pcs_tl_lossphenomenamst p ")
					.append("JOIN pcs_tl_logconfiguration c ").append("  ON p.plpm_mainloss = c.plcm_keyid ")
					.append("LEFT JOIN adm_tl_uommst u ").append("  ON c.plcm_uom = u.uomm_keyid ")
					.append("WHERE p.plpm_keyid = '").append(reasonId).append("' ").append("  AND p.plpm_active = 'Y'");

			String sql = sql1.toString();
			CommonMessage.debugMsg("String sql=" + sql);

			List<String[]> lossNoDet = dbActionTemplate.getDataList(sql);
			return lossNoDet;
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getReasonLossNo dao impl" + e.getMessage());
			return null; // keep existing behavior
		}
	}

//	public List<String[]>  getReasonLossNo( String reasonId) throws Exception { 
//		
//		
//		CommonMessage.debugMsg("Inside getShiftKeyid Dao Impl..........");
//		try
//		{			
//			StringBuffer sql1 = new StringBuffer();
//			String sql = "";
//			
//			sql1.append(" SELECT PLPM_MAINLOSS, UOMM_CODE FROM PCS_TL_LOSSPHENOMENAMST  , PCS_TL_LOGCONFIGURATION, ADM_TL_UOMMST");
//			sql1.append(" WHERE PLPM_KEYID ='" + reasonId + "'  AND PLPM_ACTIVE='Y' ");
//			sql1.append(" AND PLPM_MAINLOSS = PLCM_KEYID AND PLCM_UOM = UOMM_KEYID (+)  ");
//			
//			sql=sql1.toString();
//			
//			CommonMessage.debugMsg("Str	ing sql="+sql);
//			
//			List<String[]> lossNoDet= dbActionTemplate.getDataList(sql);		
//			
//			return lossNoDet;
//		}		
//		catch(Exception e)
//		{	
//			CommonMessage.debugMsg("Exception in getReasonLossNo dao impl"+e.getMessage());
//			return null;			
//		}			
//		
//	}

	// ------------------------- Vignesh 10Nov2025 -----------------------------//
	public String getCalendarTime(String machineId, String plmasterId, String sectId, String entryDate)
			throws Exception {

		CommonMessage.debugMsg("Inside getCalendarTime Dao Impl..........");
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

			// String calendarTime= dbActionTemplate.getSingleValue(sql);

			List<String[]> calendarTime = dbActionTemplate
					.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETUNREPFORMACHINE", paramValues);

			String calTime = calendarTime.get(0)[0];
			CommonMessage.debugMsg("calTime...0" + calTime);

			return calTime;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getCalendarTime dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getAllTimes(String machineId, String plmasterId, String sectId, String entryDate)
			throws Exception {

		CommonMessage.debugMsg("Inside getCalendarTime Dao Impl..........");
		try {
			StringBuffer sql1 = new StringBuffer();
			String sql = "";

			String detailTable = getDetailTableName(sectId, entryDate);

			List<String> paramValues = new ArrayList<String>();
			paramValues.add(detailTable);
			paramValues.add(machineId);
			paramValues.add(plmasterId);

			List<String[]> calendarTime = dbActionTemplate
					.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GETTIMESFORMACHINE", paramValues);

			return calendarTime;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getCalendarTime dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getSubLossGrid(String pldetailsId) throws Exception {
		CommonMessage.debugMsg("Inside getSubLossGrid Dao Impl..........");
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
					" FROM PCS_TL_LOSSREASONLINK, GEN_TL_MACHINEMST , PCS_TL_LOGCONFIGURATION, PCS_TL_LOSSPHENOMENAMST, ADM_TL_UOMMST ");
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

			CommonMessage.debugMsg("Str	ing sql=" + sql);
			List<String[]> sublossList = dbActionTemplate.getDataList(sql);
			return sublossList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getSubLossGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public List<String[]> getSubLossGridNew(String pldetailsId) throws Exception {
		CommonMessage.debugMsg("Inside getSubLossGrid Dao Impl..........");
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
					" FROM PCS_TL_LOSSREASONLINK, GEN_TL_MACHINEMST , PCS_TL_LOGCONFIGURATION, PCS_TL_LOSSPHENOMENAMST, ADM_TL_UOMMST ");
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

			CommonMessage.debugMsg("Str	ing sql=" + sql);
			List<String[]> sublossList = dbActionTemplate.getDataList(sql);
			return sublossList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getSubLossGrid dao impl" + e.getMessage());
		}
		return null;
	}

	// ------------------------------------ 31Oct2025 ---- Vignesh
	// ------------------------------------------------------//

	@Override
	public List<String[]> getPcsLossCaptureGrid(String flid, String Fromdate, String Todate, String shiftid)
			throws Exception {
		StringBuffer menuSql = new StringBuffer();

		menuSql.append("SELECT * FROM ( ");
		// Header row (constants)
		menuSql.append("  SELECT ");
		menuSql.append("    'PldetailsId'::text, ");
		menuSql.append("    'Keyid'::text, ");
		menuSql.append("    'From Date'::text, ");
		menuSql.append("    'From Time'::text, ");
		menuSql.append("    'To Date'::text, ");
		menuSql.append("    'To Time'::text, ");
		menuSql.append("    'Hours'::text, ");
		menuSql.append("    'LossId'::text, ");
		menuSql.append("    'Loss Ref.No'::text, ");
		menuSql.append("    'LossReasonId'::text, ");
		menuSql.append("    'Loss Reason'::text, ");
		menuSql.append("    'Loss Description'::text, ");
		menuSql.append("    'TradeId'::text, ");
		menuSql.append("    'Trade'::text, ");
		menuSql.append("    'Prodn. Impact'::text, ");
		menuSql.append("    'Prodn.Imp.Qty(Other then Downtime)'::text, ");
		menuSql.append("    'Equipment Name'::text, ");
		menuSql.append("    'Detected By'::text, ");
		menuSql.append("    ' Why-Why'::text, ");
		menuSql.append("    'Why-Why Ref No'::text, ");
		menuSql.append("    'Kaizen Ideas'::text, ");
		menuSql.append("    'Kaizen Ref No.'::text, ");
		menuSql.append("    'Action Plan'::text, ");
		menuSql.append("    'Action plan No.'::text, ");
		menuSql.append("    'Equipemntid'::text, ");
		menuSql.append("    'Employeeid'::text, ");
		menuSql.append("    0 AS DATAORDER ");
		menuSql.append("  UNION ALL ");

		// Data rows
		menuSql.append("  SELECT ");
		menuSql.append("    pl.plos_pldetailsid::text, ");
		menuSql.append("    pl.plos_keyid::text, ");
		menuSql.append("    to_char(pl.plos_fromtime, 'DD-Mon-YYYY')::text, ");
		menuSql.append("    to_char(pl.plos_fromtime, 'HH24:MI')::text, ");
		menuSql.append("    to_char(pl.plos_totime, 'DD-Mon-YYYY')::text, ");
		menuSql.append("    to_char(pl.plos_totime, 'HH24:MI')::text, ");

		// ----- Replace TIMETOHOURFORMAT(...) with pure-Postgres HH:MM from minutes
		// -----
		// Works whether plos_losstime is numeric or text containing a number of
		// minutes.
		menuSql.append("    ( floor(pl.plos_losstime::numeric / 60)::int::text ")
				.append("      || ':' || lpad(mod(pl.plos_losstime::numeric, 60)::int::text, 2, '0') )::text, ");

		menuSql.append("    pl.plos_lossid::text, ");
		menuSql.append("    lc.plcm_lossno::text, ");
		menuSql.append("    pl.plos_lossreason::text, ");
		menuSql.append("    lp.plpm_name::text, ");
		menuSql.append("    pl.plos_lossdescription::text, ");
		menuSql.append("    pl.plos_tradeid::text, ");
		menuSql.append("    tr.trdm_name::text, ");
		menuSql.append("    pl.plos_prod_impact::text, ");
		menuSql.append(
				"    (CASE WHEN pl.plos_prod_imp_qty = 0 THEN NULL ELSE pl.plos_prod_imp_qty::text END) AS prod_imp_qty, ");
		menuSql.append("    eq.ple_name::text, ");
		menuSql.append("    (coalesce(em.empm_name,'') || '-' || coalesce(em.empm_code,''))::text, ");
		menuSql.append("    ''::text AS BTN_WHY, ");
		menuSql.append("    (SELECT string_agg(w.wwms_keyid::text, ',' ORDER BY w.wwms_keyid) ");
		menuSql.append("       FROM bdm_tl_whywhymst w ");
		menuSql.append("      WHERE w.wwms_refdocno NOT IN ('-','{}') ");
		menuSql.append("        AND w.wwms_refdocno = pl.plos_keyid ");
		menuSql.append("        AND w.wwms_refdoctype = 'PCS') AS why_refno, ");
		menuSql.append("    ''::text AS BTN_KAIZEN, ");
		menuSql.append("    (SELECT string_agg(k.kzbn_keyid::text, ',' ORDER BY k.kzbn_keyid) ");
		menuSql.append("       FROM kzn_tl_kaizenbankmst k ");
		menuSql.append("      WHERE k.kzbn_refdocno <> '-' ");
		menuSql.append("        AND k.kzbn_refdocno = pl.plos_keyid) AS kaizen_refno, ");
		menuSql.append("    ''::text AS ActionPlanbtn, ");
		menuSql.append("    (SELECT string_agg(a.aplm_keyid::text, ',' ORDER BY a.aplm_keyid) ");
		menuSql.append("       FROM gen_tl_actionplanmst a ");
		menuSql.append("      WHERE a.aplm_masterrefid NOT IN ('-','{}') ");
		menuSql.append("        AND a.aplm_masterrefid = pl.plos_keyid) AS actionplan_no, ");
		menuSql.append("    pl.plos_equipment::text, ");
		menuSql.append("    pl.plos_detectedby::text, ");
		menuSql.append("    1 AS DATAORDER ");
		menuSql.append("  FROM pcs_tl_losscapture pl ");
		menuSql.append("  JOIN pcs_tl_lossphenomenamst lp ON lp.plpm_keyid = pl.plos_lossreason ");
		menuSql.append("  JOIN pcs_tl_logconfiguration lc ON lc.plcm_keyid = pl.plos_lossid ");
		menuSql.append("  JOIN gen_tl_trademst tr ON tr.trdm_keyid = pl.plos_tradeid ");
		menuSql.append("  LEFT JOIN pcs_tl_equipment eq ON eq.ple_keyid = pl.plos_equipment ");
		menuSql.append("  LEFT JOIN gen_tl_employeemst em ON em.empm_keyid = pl.plos_detectedby ");
		// Use both Fromdate and Todate (DD-MON-YYYY)
		// --- commenting and changing the 2 lines vignesh for date issue ---//

//	    menuSql.append("  WHERE pl.plos_totime::date BETWEEN to_date('").append(Fromdate).append("','DD-MON-YYYY') ")
//	          .append("AND to_date('").append(Todate).append("','DD-MON-YYYY') ");

		// NEW (order-agnostic and case-agnostic)

//	    menuSql.append("  WHERE pl.plos_totime::date BETWEEN ")
//	          .append("LEAST(to_date(UPPER('").append(Fromdate).append("'),'DD-MON-YYYY'), ")
//	          .append("      to_date(UPPER('").append(Todate).append("'),'DD-MON-YYYY')) ")
//	          .append("AND GREATEST(to_date(UPPER('").append(Fromdate).append("'),'DD-MON-YYYY'), ")
//	          .append("            to_date(UPPER('").append(Todate).append("'),'DD-MON-YYYY')) ");

		menuSql.append("  WHERE pl.plos_totime::date BETWEEN ").append("LEAST(to_date(UPPER(trim('").append(Fromdate)
				.append("')), 'FMDD-MON-YYYY'), ").append("      to_date(UPPER(trim('").append(Todate)
				.append("')),   'FMDD-MON-YYYY')) ").append("AND GREATEST(to_date(UPPER(trim('").append(Fromdate)
				.append("')), 'FMDD-MON-YYYY'), ").append("            to_date(UPPER(trim('").append(Todate)
				.append("')),   'FMDD-MON-YYYY')) ");

		// --- commenting and changing the 2 lines vignesh for date issue ---//

		menuSql.append("    AND pl.plos_flid = '").append(flid).append("' ");
		// (shiftid intentionally unused to preserve your current behavior)
		menuSql.append(") AS t ");
		menuSql.append("ORDER BY DATAORDER ");

		CommonMessage.debugMsg("LossSql  :" + menuSql.toString());
		CommonMessage.debugMsg("LossSql  :" + menuSql.toString());
		List<String[]> result = dbActionTemplate.getDataList(menuSql.toString());
		return result;
	}

//	@Override
//	public List<String[]> getPcsLossCaptureGrid(String flid, String Fromdate, String Todate, String shiftid) throws Exception {
//	    StringBuffer menuSql = new StringBuffer();
//
//	    menuSql.append("SELECT * FROM ( ");
//	    // Header row (constants). No FROM needed in Postgres.
//	    menuSql.append("  SELECT ");
//	    menuSql.append("    'PldetailsId'::text, ");
//	    menuSql.append("    'Keyid'::text, ");
//	    menuSql.append("    'From Date'::text, ");
//	    menuSql.append("    'From Time'::text, ");
//	    menuSql.append("    'To Date'::text, ");
//	    menuSql.append("    'To Time'::text, ");
//	    menuSql.append("    'Hours'::text, ");
//	    menuSql.append("    'LossId'::text, ");
//	    menuSql.append("    'Loss Ref.No'::text, ");
//	    menuSql.append("    'LossReasonId'::text, ");
//	    menuSql.append("    'Loss Reason'::text, ");
//	    menuSql.append("    'Loss Description'::text, ");
//	    menuSql.append("    'TradeId'::text, ");
//	    menuSql.append("    'Trade'::text, ");
//	    menuSql.append("    'Prodn. Impact'::text, ");
//	    menuSql.append("    'Prodn.Imp.Qty(Other then Downtime)'::text, ");
//	    menuSql.append("    'Equipment Name'::text, ");
//	    menuSql.append("    'Detected By'::text, ");
//	    menuSql.append("    ' Why-Why'::text, ");
//	    menuSql.append("    'Why-Why Ref No'::text, ");
//	    menuSql.append("    'Kaizen Ideas'::text, ");
//	    menuSql.append("    'Kaizen Ref No.'::text, ");
//	    menuSql.append("    'Action Plan'::text, ");
//	    menuSql.append("    'Action plan No.'::text, ");
//	    menuSql.append("    'Equipemntid'::text, ");
//	    menuSql.append("    'Employeeid'::text, ");
//	    menuSql.append("    0 AS DATAORDER ");
//	    menuSql.append("  UNION ALL ");
//	    // Data rows
//	    menuSql.append("  SELECT ");
//	    menuSql.append("    pl.plos_pldetailsid::text, ");
//	    menuSql.append("    pl.plos_keyid::text, ");
//	    menuSql.append("    to_char(pl.plos_fromtime, 'DD-Mon-YYYY')::text, ");
//	    menuSql.append("    to_char(pl.plos_fromtime, 'HH24:MI')::text, ");
//	    menuSql.append("    to_char(pl.plos_totime, 'DD-Mon-YYYY')::text, ");
//	    menuSql.append("    to_char(pl.plos_totime, 'HH24:MI')::text, ");
//	    // Keep your function name (assumes you have a PG function with same name)
//	    menuSql.append("    (TIMETOHOURFORMAT(pl.plos_losstime))::text, ");
//	    menuSql.append("    pl.plos_lossid::text, ");
//	    menuSql.append("    lc.plcm_lossno::text, ");
//	    menuSql.append("    pl.plos_lossreason::text, ");
//	    menuSql.append("    lp.plpm_name::text, ");
//	    menuSql.append("    pl.plos_lossdescription::text, ");
//	    menuSql.append("    pl.plos_tradeid::text, ");
//	    menuSql.append("    tr.trdm_name::text, ");
//	    menuSql.append("    pl.plos_prod_impact::text, ");
//	    menuSql.append("    (CASE WHEN pl.plos_prod_imp_qty = 0 THEN NULL ELSE pl.plos_prod_imp_qty::text END) AS prod_imp_qty, ");
//	    menuSql.append("    eq.ple_name::text, ");
//	    menuSql.append("    (coalesce(em.empm_name,'') || '-' || coalesce(em.empm_code,''))::text, ");
//	    menuSql.append("    ''::text AS BTN_WHY, ");
//	    menuSql.append("    (SELECT string_agg(w.wwms_keyid::text, ',' ORDER BY w.wwms_keyid) ");
//	    menuSql.append("       FROM bdm_tl_whywhymst w ");
//	    menuSql.append("      WHERE w.wwms_refdocno NOT IN ('-','{}') ");
//	    menuSql.append("        AND w.wwms_refdocno = pl.plos_keyid ");
//	    menuSql.append("        AND w.wwms_refdoctype = 'PCS') AS why_refno, ");
//	    menuSql.append("    ''::text AS BTN_KAIZEN, ");
//	    menuSql.append("    (SELECT string_agg(k.kzbn_keyid::text, ',' ORDER BY k.kzbn_keyid) ");
//	    menuSql.append("       FROM kzn_tl_kaizenbankmst k ");
//	    menuSql.append("      WHERE k.kzbn_refdocno <> '-' ");
//	    menuSql.append("        AND k.kzbn_refdocno = pl.plos_keyid) AS kaizen_refno, ");
//	    menuSql.append("    ''::text AS ActionPlanbtn, ");
//	    menuSql.append("    (SELECT string_agg(a.aplm_keyid::text, ',' ORDER BY a.aplm_keyid) ");
//	    menuSql.append("       FROM gen_tl_actionplanmst a ");
//	    menuSql.append("      WHERE a.aplm_masterrefid NOT IN ('-','{}') ");
//	    menuSql.append("        AND a.aplm_masterrefid = pl.plos_keyid) AS actionplan_no, ");
//	    menuSql.append("    pl.plos_equipment::text, ");
//	    menuSql.append("    pl.plos_detectedby::text, ");
//	    menuSql.append("    1 AS DATAORDER ");
//	    menuSql.append("  FROM pcs_tl_losscapture pl ");
//	    menuSql.append("  JOIN pcs_tl_lossphenomenamst lp ON lp.plpm_keyid = pl.plos_lossreason ");
//	    menuSql.append("  JOIN pcs_tl_logconfiguration lc ON lc.plcm_keyid = pl.plos_lossid ");
//	    menuSql.append("  JOIN gen_tl_trademst tr ON tr.trdm_keyid = pl.plos_tradeid ");
//	    menuSql.append("  LEFT JOIN pcs_tl_equipment eq ON eq.ple_keyid = pl.plos_equipment ");
//	    menuSql.append("  LEFT JOIN gen_tl_employeemst em ON em.empm_keyid = pl.plos_detectedby ");
//	    menuSql.append("  WHERE pl.plos_totime::date BETWEEN to_date('").append(Todate).append("','DD-MON-YYYY') AND to_date('").append(Todate).append("','DD-MON-YYYY') ");
//	    menuSql.append("    AND pl.plos_flid = '").append(flid).append("' ");
//	    // (Keeping shiftid unused to retain existing behavior exactly)
//	    menuSql.append(") AS t ");
//	    menuSql.append("ORDER BY DATAORDER ");
//
//	    CommonMessage.debugMsg("LossSql  :" + menuSql.toString());
//	    CommonMessage.debugMsg("LossSql  :" + menuSql.toString());
//	    List<String[]> result = dbActionTemplate.getDataList(menuSql.toString());
//	    return result;
//	}

//	@Override
//	public List<String[]> getPcsLossCaptureGrid(String flid, String Fromdate,String Todate, String shiftid) throws Exception {
//			// TODO Auto-generated method stub
//			StringBuffer menuSql  = new StringBuffer();
//			
//		menuSql.append(" SELECT * FROM ( " ); 
//		menuSql.append(" SELECT 'PldetailsId', 'Keyid', 'From Date', 'From Time', 'To Date' ,'To Time','Hours','LossId', 'Loss Ref.No', 'LossReasonId','Loss Reason', " );
//		menuSql.append(" 'Loss Description','TradeId','Trade', 'Prodn. Impact', 'Prodn.Imp.Qty(Other then Downtime)','Equipment Name', " );
//	    menuSql.append(" 'Detected By',' Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No.','Action Plan','Action plan No.', 'Equipemntid', 'Employeeid', 0 AS DATAORDER FROM DUAL " );
//	    menuSql.append(" 		UNION " );
//		menuSql.append(" 		SELECT PLOS_PLDETAILSID, PLOS_KEYID, TO_CHAR(PLOS_FROMTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_FROMTIME,'HH24:MI'), TO_CHAR(PLOS_TOTIME,'DD-Mon-YYYY') ,TO_CHAR(PLOS_TOTIME,'HH24:MI'), ");
//		//menuSql.append("  TO_CHAR(ROUND(PLOS_LOSSTIME/60,2)) , ");
//		menuSql.append(" 	TIMETOHOURFORMAT(PLOS_LOSSTIME) , ");
//		menuSql.append(" 	PLOS_LOSSID, PLCM_LOSSNO, PLOS_LOSSREASON," ); 
//		menuSql.append(" 		    PLPM_NAME, PLOS_LOSSDESCRIPTION, PLOS_TRADEID, TRDM_NAME, PLOS_PROD_IMPACT, TO_CHAR(DECODE(PLOS_PROD_IMP_QTY,0,NULL,PLOS_PROD_IMP_QTY)),PLE_NAME, " );
//	    menuSql.append(" 		    EMPM_NAME||'-'||EMPM_CODE, '' AS BTN_WHY, " );
//	    
//	    //'' AS WHY_REFNO,
//	    menuSql.append(" ( SELECT LISTAGG(WWMS_KEYID,',') WITHIN GROUP(ORDER BY WWMS_KEYID)  FROM BDM_TL_WHYWHYMST " );
//	    menuSql.append(" where WWMS_REFDOCNO NOT IN  ('-','{}') AND WWMS_REFDOCNO = pl.PLOS_KEYID ");
//	    menuSql.append("  AND WWMS_REFDOCTYPE='PCS' ");
//	    menuSql.append(" ) " );
//	    menuSql.append(" , '' AS BTN_KAIZEN, ");
//	    
//	    menuSql.append(" ( SELECT LISTAGG(KZBN_KEYID,',') WITHIN GROUP(ORDER BY KZBN_KEYID)  FROM KZN_TL_KAIZENBANKMST " );
//	    menuSql.append(" where KZBN_REFDOCNO <> '-' AND KZBN_REFDOCNO = pl.PLOS_KEYID ");
//	    
//	    menuSql.append(" ) AS KAIZEN_REFNO,'' AS ActionPlanbtn,(SELECT LISTAGG(APLM_KEYID,',') WITHIN GROUP(ORDER BY APLM_KEYID)  FROM gen_tl_actionplanmst "); 
//	    menuSql.append(" where aplm_masterrefid NOT IN  ('-','{}') AND aplm_masterrefid = pl.PLOS_KEYID),PLOS_EQUIPMENT,plos_detectedby,1 AS DATAORDER " );
//	    
//	    menuSql.append(" FROM PCS_TL_LOSSCAPTURE pl, PCS_TL_LOSSPHENOMENAMST, PCS_TL_LOGCONFIGURATION, GEN_TL_TRADEMST,PCS_TL_EQUIPMENT,GEN_TL_EMPLOYEEMST " );
//		menuSql.append(" WHERE PLPM_KEYID = PLOS_LOSSREASON AND PLCM_KEYID = PLOS_LOSSID AND TRDM_KEYID = PLOS_TRADEID AND PLOS_EQUIPMENT=PLE_KEYID(+) " );		
//		//menuSql.append(" AND TO_CHAR('"+ Todate +"','DD-MON-YYYY') BETWEEN PLOS_FROMTIME   AND PLOS_TOTIME  AND PLOS_FLID ='" + flid + "' ");
//		menuSql.append(" AND TRUNC(PLOS_TOTIME )  BETWEEN  TO_DATE('"+ Todate +"') AND TO_DATE('"+ Todate +"')");
//		menuSql.append(" AND PLOS_FLID ='" + flid + "' AND EMPM_KEYID(+)=PLOS_DETECTEDBY ");
//			
//		menuSql.append(" ) ORDER BY DATAORDER  " );
//			/*menuSql.append(" SELECT * FROM ( ");
//			menuSql.append(" SELECT 'From','To','Mins','Loss Ref.No','Loss Reason','Loss Description','Trade','Prodn.Impact(Y/N)','Prodn.Impacted Qty(Other then Downtime)','Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No', 0 AS DATAORDER FROM DUAL ");
//			menuSql.append(" UNION SELECT '02:00','03:00','01:00','','','Description1','','Y','','','BDM13100013, BDM13100015','','KZ1300000023, KZ1300000025', 1 AS DATAORDER FROM DUAL ");
//			menuSql.append(" UNION SELECT '05:00','08:00','03:00','','','Description2','','N','','','BDM13100014','','KZ1300000022, KZ1300000026', 2 AS DATAORDER FROM DUAL ");
//			menuSql.append(" UNION SELECT '12:00','18:00','06:00','','','Description3','','N','','','BDM13100016, BDM13100018','','KZ1300000024, KZ1300000028, KZ1300000032', 3 AS DATAORDER FROM DUAL ");
//			menuSql.append(" UNION SELECT '15:00','20:00','05:00','','','Description4','','Y','','','BDM13100017','','KZ1300000027, KZ1300000029', 4 AS DATAORDER FROM DUAL ");
//			menuSql.append(" UNION SELECT '04:00','06:00','02:00','','','Description5','','N','','','BDM13100018, BDM13100020','','KZ1300000025, KZ1300000027', 5 AS DATAORDER FROM DUAL ");
//			menuSql.append(" ) ORDER BY DATAORDER ");
//			*/
//			CommonMessage.debugMsg("LossSql  :"+menuSql.toString());
//			CommonMessage.debugMsg("LossSql  :"+menuSql.toString());
//			List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
//			return getPillarRelMenu;
//	}
//	
	// ------------------------------------ 31Oct2025 ---- Vignesh
	// ------------------------------------------------------//
	public List<String[]> getPcsResultGrid(String entryDate, String shift, String sectId, String cellId, String mchId)
			throws Exception {
		CommonMessage.debugMsg("Inside getPcsResultGrid Dao Impl..........");
		try {
			List<String> paramValues = new ArrayList<String>();
			List<String[]> resultList;

			String condParms = "ENTRYDATE=" + entryDate + ";SHIFTID=" + shift + ";SECTIONID=" + sectId + ";CELLID="
					+ cellId + ";MACHINEID=" + mchId + ";TIMEORQTY=T;LOGTYPE=S;";
			String commonParams = "FILTERCOND=;ISTOTALCNT=Y;FROMTOROW=1 AND 100;ORDERCOL=;"; // "ISTOTALCNT="+commonFilter.getViewClick()
																								// +";FROMTOROW="+commonFilter.getFromRow()
																								// +" AND " +
																								// commonFilter.getToRow()
																								// +";";

			paramValues.add(condParms);
			paramValues.add(commonParams);

			resultList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_GetResult", paramValues);
			return resultList;
		}

		catch (Exception e) {
			CommonMessage.debugMsg("Exception in getPcsResultGrid dao impl" + e.getMessage());
		}
		return null;
	}

	public PcsTlMst create(PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl, PcsTlWorkorderlink pcsTlWorkorderlink,
			PcsEntryBean pcsEntryBean) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		String CycleTime = "0";
		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlMst.getPrlmSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlMst.getPrlmSectionid(), pcsTlMst.getPrlmEntrydate());

		CommonMessage.debugMsg("detailTableName" + detailTableName);

		if (!UIUtils.isValidKeyId(pcsEntryBean.getPlmasterid())) {
			pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST)); // set the sequnce
																									// number
			sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert
																										// sql for
																										// master table
		} else {
			pcsTlMst.setPrlmKeyid(pcsEntryBean.getPlmasterid());
			sqls.add(PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert
																										// sql for
																										// master table
		}

		CommonMessage.debugMsg("Size : " + pcsTlMst.getPcsDetail().size());

		// if(pcsTlMst.getPcsDetail()!= null && pcsTlMst.getPcsDetail().size()>0) //
		// check for detail table data
		// {

		String avialTable = dbActionTemplate.getSingleValue("TAB", "TNAME", "TNAME", detailTableName);
		if (avialTable.equals(detailTableName)) {
			CommonMessage.debugMsg("Table already exists ");
		} else {
			CommonMessage.debugMsg("Create table for section");
			String sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM PCS_TL_DTL ";
			dbActionTemplate.executeStatement(sql);

			String pkConst = detailTableName.replace("PCS_TL_", "");
			sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_PCS_TL_DTL_" + pkConst
					+ " PRIMARY KEY (PLDETAILSID) ";
			dbActionTemplate.executeStatement(sql);

			sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
			sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES PCS_TL_MST (PRLM_KEYID) ";
			dbActionTemplate.executeStatement(sql);
		}

		CommonMessage.debugMsg("if Loop in MST Dao Impl");
		// PcsTlDtl pcsDetail = (PcsTlDtl)pcsTlMst.getPcsDetail().get(0); // get detail
		// info from list in empployee object
		// pcsDetail.setPlmasterid(pcsTlMst.getPrlmKeyid());
		pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
		CycleTime = pcsTlDtl.getActualcycletime();
		// pcsDetail.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_PCS_TL_DTL));

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
					dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
			/*
			 * String PlanQTY =
			 * dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST,
			 * "CNFM_SETTINGVALUE", "CNFM_CODE", "FETCHPLANQTY");
			 * if(UIUtils.isValidKeyId(PlanQTY)) { if(PlanQTY.equals("Y")) { String pq =
			 * dbActionTemplate.
			 * getSingleValue("SELECT PRPL_PLANQTY FROM PCS_TL_PRODUCTIONPLAN WHERE PRPL_PRODUCTID='"
			 * +pcsTlDtl.getProductid()+"'");
			 * CommonMessage.debugMsg("Production Plan Plan Qty : "+pq);
			 * if(UIUtils.isValidKeyId(pq)) { pcsTlDtl.setPlannedqty(pq); } } }
			 */
			// If (Not ObjGenUtils.CheckSequence(GlbPLDetailsTable, 15, "PDE", 1, 1, "YY",
			// True, True)) Then

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

	public PcsTlMst createNew(PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl, PcsTlWorkorderlink pcsTlWorkorderlink,
			PcsEntryBean pcsEntryBean) throws Exception, BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>();
		String CycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlMst.getPrlmSectionid(), pcsTlMst.getPrlmEntrydate());
		CommonMessage.debugMsg(" In DAO Impl " + pcsTlDtl.getLoss21());
		CommonMessage.debugMsg("DetailTableName : " + detailTableName);

		if (!UIUtils.isValidKeyId(pcsEntryBean.getPlmasterid())) {
			pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST)); // set the sequnce
																									// number
			sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert
																										// sql for
																										// master table
		} else {
			pcsTlMst.setPrlmKeyid(pcsEntryBean.getPlmasterid());
			sqls.add(PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert
																										// sql for
																										// master table
		}

		CommonMessage.debugMsg("Size : " + pcsTlMst.getPcsDetail().size());

		String existTable = dbActionTemplate.getSingleValue("TAB", "TNAME", "TNAME", detailTableName);
		if (!existTable.equals(detailTableName)) {
			CommonMessage.debugMsg("Create table for section");
			String sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM PCS_TL_DTL ";
			dbActionTemplate.executeStatement(sql);

			String pkConst = detailTableName.replace("PCS_TL_", "");
			sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_PCS_TL_DTL_" + pkConst
					+ " PRIMARY KEY (PLDETAILSID) ";
			dbActionTemplate.executeStatement(sql);

			sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
			sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES PCS_TL_MST (PRLM_KEYID) ";
			dbActionTemplate.executeStatement(sql);
		}

		CommonMessage.debugMsg("pcsEntryBean.getPldetailsid() : " + pcsEntryBean.getPldetailsid());
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
						sqls.add(PcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
								pcsTlDtl.getSaveArray()));// add insert sql for detail table
						pcsTlMst.setPrlmActive("U");
					}
				} else {
					CommonMessage.debugMsg("Planned Qty Before Calculations : " + pcsTlDtl.getPlannedqty());
					CommonMessage.debugMsg("Calendar Time Before Calculations : " + pcsTlDtl.getCalendartime());
					pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
					CommonMessage.debugMsg("Planned Qty After Calculations : " + pcsTlDtl.getPlannedqty());
					CommonMessage.debugMsg("Calendar Time After Calculations : " + pcsTlDtl.getCalendartime());
					pcsTlDtl.setPldetailsid(
							dbActionTemplate.getSequenceNumber(PcsTlDtlSql.TBL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
					sqls.add(PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table
					// pcsTlMst.setPrlmActive("I");
				}
			} else {
				pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
				pcsTlDtl.setPldetailsid(
						dbActionTemplate.getSequenceNumber(PcsTlDtlSql.TBL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
				sqls.add(PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
						pcsTlDtl.getSaveArray()));// add insert sql for detail table
			}
		}

		else {
			pcsTlDtl.setPldetailsid(pcsEntryBean.getPldetailsid());
			pcsValidations(pcsTlDtl, pcsTlMst, pcsEntryBean);
			sqls.add(PcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add
																														// insert
																														// sql
																														// for
																														// detail
																														// table
		}

		runBulkSqls(sqls, detailTableName, pcsTlDtl.getPldetailsid(), CycleTime, pcsTlMst.getPrlmFlid());

		return pcsTlMst;

	}

	public void pcsValidations(PcsTlDtl newPcsTlDtl, PcsTlMst pcsTlMst, PcsEntryBean pcsEntryBean) throws Exception {
		CommonMessage.debugMsg("before check");
		String prodQty = newPcsTlDtl.getProducedqty();
		if (!UIUtils.isValidKeyId(prodQty))
			prodQty = "0";
		CommonMessage.debugMsg("prodQty" + prodQty);

		String cavityUsed = newPcsTlDtl.getCavityused();
		if (!UIUtils.isValidKeyId(cavityUsed))
			cavityUsed = "0";
		CommonMessage.debugMsg("cavityUsed" + cavityUsed);

		String actTime = newPcsTlDtl.getActualcycletime();
		if (!UIUtils.isValidKeyId(actTime))
			actTime = "0";
		CommonMessage.debugMsg("actTime" + actTime);

		String availTimeStr = getCalendarTime(newPcsTlDtl.getMachineid(), pcsEntryBean.getPlmasterid(),
				pcsTlMst.getPrlmSectionid(), pcsTlMst.getPrlmEntrydate());
		CommonMessage.debugMsg("availTime" + availTimeStr);

		Float availTime = Float.parseFloat(availTimeStr);
		if (Float.parseFloat(availTimeStr) < 0)
			availTime = (float) 0.0;

		// String tableName =
		// getDetailTableName(pcsTlMst.getPrlmSectionid(),pcsTlMst.getPrlmEntrydate());
		String oldTime = pcsEntryBean.getOldTime();// dbActionTemplate.getSingleValue(tableName, "PRODUCEDQTY",
													// "PLDETAILSID", newPcsTlDtl.getPldetailsid());

		CommonMessage.debugMsg("oldTime : " + oldTime);
		if (UIUtils.isValidKeyId(pcsEntryBean.getPldetailsid()))
			availTime = availTime + Float.parseFloat(oldTime);

		CommonMessage.debugMsg("availTime=" + availTime);

		String cycleTime = newPcsTlDtl.getActualcycletime();
		String theoTime = newPcsTlDtl.getTheoriticalcycletime();
		if (!UIUtils.isValidKeyId(cycleTime))
			cycleTime = "0";
		String cavityAvail = newPcsTlDtl.getCavityavailable();
		if (!UIUtils.isValidKeyId(cavityAvail))
			cavityAvail = "0";
		CommonMessage.debugMsg("cycleTime=" + cycleTime);
		Float planQty;

		if (cycleTime.equals("0"))
			planQty = (float) 0.0;
		else
			planQty = (availTime / Float.parseFloat(cycleTime)) * Float.parseFloat(cavityAvail);

		CommonMessage.debugMsg("planQty=" + planQty);

		newPcsTlDtl.setPlannedqty(planQty.toString());
		newPcsTlDtl.setCalendartime(availTime.toString());
		CommonMessage.debugMsg("Planned Qty In Calculations : " + newPcsTlDtl.getPlannedqty());
		CommonMessage.debugMsg("Calendar Time In Calculations : " + newPcsTlDtl.getCalendartime());
		Float prodTime = (Float.parseFloat(prodQty) / Float.parseFloat(cavityUsed)) * Float.parseFloat(actTime);
		CommonMessage.debugMsg("prodTime" + prodTime);

		CommonMessage.debugMsg("Math.floor(prodTime)=====" + Math.floor(prodTime));
		CommonMessage.debugMsg("Math.floor(availTime)====" + Math.floor(availTime));

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
		CommonMessage.debugMsg("(actPct %" + actPct);
		CommonMessage.debugMsg("(theoTime %" + theoTime);
		Float timePct = (Float.parseFloat(theoTime) * (float) actPct / (float) 100);
		CommonMessage.debugMsg("(time25%" + timePct);
		Float timepctVal = (float) 0;

		NumberFormat df = DecimalFormat.getInstance();
		df.setMinimumFractionDigits(2);
		df.setMaximumFractionDigits(2);
		df.setRoundingMode(RoundingMode.UP);

		timepctVal = (Float.parseFloat(theoTime) + timePct);
		CommonMessage.debugMsg("(timepctVal" + timepctVal);
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
			String insertPCSSql = "PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL";
			Object[] insertPCSDatas = { detailTableName, pldetailsId, "Y", "N", flid };
			CommonMessage.debugMsg("detailTableName : " + detailTableName + " pldetailsId " + pldetailsId);
			int[] insertPCSTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
			bulk_sqls.add(insertPCSSql);
			valueList.add(insertPCSDatas);
			dataTypes.add(insertPCSTypes);
			charList.add("P");

			char[] sqlType = new char[charList.size()];
			CommonMessage.debugMsg("lENGTH : " + sqlType.length);
			for (int c = 0; c < sqlType.length; c++) {
				CommonMessage.debugMsg(c + " : " + charList.get(c));
				sqlType[c] = charList.get(c).charAt(0);
				CommonMessage.debugMsg(c + " : " + sqlType[c]);
			}
			dbActionTemplate.executeStatement(bulk_sqls, valueList, dataTypes, sqlType);
		} catch (Exception e) {

			CommonMessage.debugMsg("Exception in updatng pcs impl" + e.getMessage());
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
				// String unrepmsg = "UNREPORTED TIME EXCEEDS " + unrepTime + " Mins" ;
				String unrepmsg = "Unreported time exceeds " + unrepTime + " Mins";
				e.printStackTrace();
				throw new Exception(unrepmsg.toString());
			}
			/*
			 * else throw new Exception( e.getMessage());
			 */

		}
	}

	public PcsTlOperatordtl createPcsEmployee(PcsTlOperatordtl pcsTlOperatordtl, String Plmasterid, String Pldetailsid)
			throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg("Plmasterid   =====" + Plmasterid);
		pcsTlOperatordtl.setPopdPldetailsid(Pldetailsid);
		pcsTlOperatordtl.setPopdPlmasterid(Plmasterid);
		// pcsTlOperatordtl.setPlrkKeyid(dbActionTemplate.getSequenceNumber(pcsTlLossreasonlinkSql.TBL_PCS_TL_LOSSREASONLINK));
		// // set the sequnce number
		sqls.add(pcsTlOperatordtlSql.getInsertSql(pcsTlOperatordtlSql.getPopdDbFields(),
				pcsTlOperatordtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		return pcsTlOperatordtl;
	}

	// -------------------- vignesh 12Nov2025----For sAVING PG
	// COMPATIBLE----------------------------------------//

	public List<String[]> getPcsMstDtlids(PcsTlLosscapture newPcsTlLosscapture, PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl)
			throws Exception {

		StringBuilder sb = new StringBuilder();

// get section id from cell
		String sql = "SELECT cell_sectionid FROM gen_tl_cellmst WHERE cell_keyid='"
				+ pcsTlDtl.getCellid().replace("'", "''") + "'";
		String sectId = dbActionTemplate.getSingleValue(sql);

// pick the per-section detail table (your existing helper already PG-fixed)
		String detailTableName = getDetailTableName(sectId, pcsTlMst.getPrlmEntrydate());

// Prepare date expression for Postgres
		String rawPlosDate = newPcsTlLosscapture.getPlosDate();
		String safePlosDate = (rawPlosDate == null) ? "" : rawPlosDate.replace("'", "''");
// If time is present, parse as timestamp then cast to date; else parse as date directly
		String dateExpr = (safePlosDate.contains(":"))
				? "to_timestamp('" + safePlosDate + "', 'DD-Mon-YYYY HH24:MI')::date"
				: "to_date('" + safePlosDate + "', 'DD-Mon-YYYY')::date";

// Other filter values (quote-safe)
		String shiftId = (newPcsTlLosscapture.getPlosShiftid() == null) ? ""
				: newPcsTlLosscapture.getPlosShiftid().replace("'", "''");
		String flid = (newPcsTlLosscapture.getPlosFlid() == null) ? ""
				: newPcsTlLosscapture.getPlosFlid().replace("'", "''");

// PG rewrite:
// FROM pcs_tl_mst m
// LEFT JOIN <detailTableName> d ON d.plmasterid = m.prlm_keyid
// WHERE m.prlm_date::date = <dateExpr>
//   AND m.prlm_shiftid = ...
//   AND m.prlm_flid = ...
// GROUP BY m.prlm_keyid
		sb.append("SELECT m.prlm_keyid, MIN(d.pldetailsid) ").append("FROM pcs_tl_mst m ").append("LEFT JOIN ")
				.append(detailTableName).append(" d ON d.plmasterid = m.prlm_keyid ")
				.append("WHERE m.prlm_entrydate::date = ").append(dateExpr).append(" ").append("AND m.prlm_shiftid = '")
				.append(shiftId).append("' ").append("AND m.prlm_flid = '").append(flid).append("' ")
				.append("GROUP BY m.prlm_keyid");

		CommonMessage.debugMsg("getPcsMstDtlids=== " + sb.toString());

		return dbActionTemplate.getDataList(sb.toString());
	}

//	public List<String[]> getPcsMstDtlids(PcsTlLosscapture newPcsTlLosscapture, PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl) throws Exception { 
//		
//		StringBuffer sb = new StringBuffer();
//		
//		String sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID ='"+ pcsTlDtl.getCellid() + "' ";
//		String sectId  =dbActionTemplate.getSingleValue(sql); 
//		String detailTableName =getDetailTableName(sectId, pcsTlMst.getPrlmEntrydate());
//		
//		
//		sb.append(" SELECT PRLM_KEYID, MIN(PLDETAILSID) FROM PCS_TL_MST, "+ detailTableName + " " );
//		sb.append(" WHERE TRUNC(PRLM_DATE) ='"+ newPcsTlLosscapture.getPlosDate() + "' AND PRLM_SHIFTID = '"+ newPcsTlLosscapture.getPlosShiftid() + "' ") ;
//		sb.append(" AND PRLM_FLID='"+ newPcsTlLosscapture.getPlosFlid() + "' " );
//		sb.append(" AND PLMASTERID (+)= PRLM_KEYID GROUP BY PRLM_KEYID " );
//		
//		CommonMessage.debugMsg("getPcsMstDtlids===" + sb.toString());
//		
//		return dbActionTemplate.getDataList(sb.toString());
//		
//	}

	// -------------------- vignesh
	// ------------------------------------------------------------------------//

	public PcsTlLosscapture createLossCapture(PcsTlLosscapture pcsTlLosscapture, PcsTlMst pcsTlMst, PcsTlDtl pcsTlDtl,
			PcsTlLossreasonlink pcsTlLossreasonlink) throws Exception {
		List<String> sqls = new ArrayList<String>();
		PcsTlLosscaptureSql pcsTlLosscaptureSql = new PcsTlLosscaptureSql();

		// CommonMessage.debugMsg("pcsTlMst.getPrlmKeyid()==="+pcsTlMst.getPrlmKeyid());

		// CommonMessage.debugMsg("pcsTlLosscapture.getPlospldetailsid()" +
		// pcsTlLosscapture.getPlospldetailsid());

		List<String> paramValues = new ArrayList<String>();

		ArrayList<String> pcsdtl = new ArrayList<String>();

		CommonMessage.debugMsg("pcsTlLosscapture.getPlosFromtime() " + pcsTlLosscapture.getPlosFromtime()
				+ " pcsTlLosscapture.getPlosTotime() " + pcsTlLosscapture.getPlosTotime());

		String dfromtime = pcsTlLosscapture.getPlosFromtime();
		String dtoTime = pcsTlLosscapture.getPlosTotime();
		CommonMessage.debugMsg("dfromtime::" + dfromtime);
		CommonMessage.debugMsg("dtoTime::" + dtoTime);
		if (dfromtime.length() == 16)
			dfromtime = dfromtime.substring(11, dfromtime.length());
		else
			dfromtime = dfromtime.substring(12, dfromtime.length());

		if (dtoTime.length() == 16)
			dtoTime = dtoTime.substring(11, dtoTime.length());
		else
			dtoTime = dtoTime.substring(12, dtoTime.length());

		String[] dotim = dfromtime.split(":");

		CommonMessage.debugMsg("dotim[0] " + dotim[0] + " length " + dotim[0].length() + "dotim[1] " + dotim[1]
				+ " lenght " + dotim[1].length());

		if (dotim[0].length() == 1) {
			dotim[0] = "0" + dotim[0];
		}
		if (dotim[1].length() == 1) {
			dotim[1] = "0" + dotim[1];
		}
		CommonMessage.debugMsg("dotim[1]===" + dotim[1]);

		if (dotim[0].equals("06") && dotim[1].equals("00")) {
			dotim[1] = "01";
		}

		CommonMessage.debugMsg("dotim[1]===" + dotim[1]);

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

		CommonMessage.debugMsg("fromtime===" + dfromtime);
		CommonMessage.debugMsg("totime===" + dtoTime);

		// String fromTime = dfromtime.substring(12, dfromtime.length());
		// String toTime = dtoTime.substring(12, dtoTime.length());

		// CommonMessage.debugMsg("fromtime totime" + fromTime + toTime );
		paramValues.add(dfromtime);
		paramValues.add(dtoTime);
		paramValues.add(pcsTlLosscapture.getPlosLosstime());

		List<String[]> Noofshift;
		CommonMessage.debugMsg("paramvalues123:::" + paramValues);
		int losstime = Integer.parseInt(pcsTlLosscapture.getPlosLosstime());
		if (losstime <= 300) {
			Noofshift = dbActionTemplate.processFunctionCalls("PCS_FN_PRODLOSSSHIFTCAL", paramValues);
		} else {
			Noofshift = new ArrayList<String[]>();
		}

		// CommonMessage.debugMsg( "Noofshift.size()" + Noofshift.size());
		// CommonMessage.debugMsg("Noofshift.size()::"+Noofshift.size());
		CommonMessage.debugMsg("outside the condition");
		String sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID ='" + pcsTlDtl.getCellid() + "' ";
		String sectId = dbActionTemplate.getSingleValue(sql);

		String detailTableName = getDetailTableName(sectId, pcsTlMst.getPrlmEntrydate());

		CommonMessage.debugMsg(" detailTableName==" + detailTableName);
		CommonMessage.debugMsg("pcsTlLosscapture.getPlospldetailsid()" + pcsTlLosscapture.getPlospldetailsid());

		String Plospldetailsid = pcsTlLosscapture.getPlospldetailsid();

		String Onedateplus = "";
		CommonMessage.debugMsg("outside the fr condition");

		if (Noofshift != null) {

			for (int i = 0; i < Noofshift.size(); i++) {

				pcsTlMst.setPrlmShiftid(Noofshift.get(i)[0]);
				// pcsTlMst.setPrlmShiftid("SFT001");
				String Shiftid = Noofshift.get(i)[0];
				// String Shiftid = "SFT001";
				CommonMessage.debugMsg("Shiftid" + Shiftid);
				// CommonMessage.debugMsg("Shiftid:::"+Noofshift.get(i)[0]);
				CommonMessage.debugMsg("Shiftid:::" + Shiftid);
				if (i == 0) {
					Onedateplus = pcsTlLosscapture.getPlosFromtime();
					Onedateplus = Onedateplus.substring(0, 11);

					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);

				} else {
					CommonMessage.debugMsg("Noofshift.get(i)[0]" + Noofshift.get(i)[0]);

					// CommonMessage.debugMsg("Noofshift.get(i)[0]:::"+Noofshift.get(i)[0]);
					if (Shiftid.equals("SFT001")) {
						String frmdate = Onedateplus;
						// frmdate = frmdate.substring(0,11);
						String Sql = "Select to_char(to_date('" + frmdate
								+ "','DD-MON-YYYY')+ 1 , 'DD-MON-YYYY') FROM DUAL";
						CommonMessage.debugMsg("Noofshift.get(i)[0] Sql" + Sql);
						Onedateplus = dbActionTemplate.getSingleValue(Sql);

						CommonMessage.debugMsg(" Onedateplus " + Onedateplus);

						pcsTlMst.setPrlmDate(Onedateplus);
						pcsTlMst.setPrlmEntrydate(Onedateplus);

						CommonMessage.debugMsg(" PrlmDate inside " + pcsTlMst.getPrlmDate()
								+ pcsTlMst.getPrlmEntrydate() + " " + Onedateplus);
					} else {
						pcsTlMst.setPrlmDate(Onedateplus);
						pcsTlMst.setPrlmEntrydate(Onedateplus);
					}
				}

				CommonMessage.debugMsg(" PrlmDate " + pcsTlMst.getPrlmDate() + pcsTlMst.getPrlmEntrydate());

				pcsTlDtl.setCalendartime(Noofshift.get(i)[2]);
				// pcsTlDtl.setCalendartime("480");
				// if (! UIUtils.isValidKeyId( pcsTlMst.getPrlmKeyid())) {
				CommonFunctions
						.debugMsg("pcsTlLosscapture.getPlospldetailsid()  " + pcsTlLosscapture.getPlospldetailsid());

				pcsTlLosscapture.setPlosPldetailsid(Plospldetailsid);

				if (pcsTlLosscapture.getPlospldetailsid().equals("false"))
					pcsTlLosscapture.setPlosPldetailsid("");

				CommonFunctions
						.debugMsg("pcsTlLosscapture.getPlospldetailsid()  " + pcsTlLosscapture.getPlospldetailsid());

				// if (! UIUtils.isValidKeyId ( pcsTlLosscapture.getPlospldetailsid())) {

				CommonMessage.debugMsg("Inside mst dtl insert 1 ");

				// -- Vignesh

				String Checksql = " select count(*) from PCS_TL_MST " + " where PRLM_ENTRYDATE::date = to_timestamp('"
						+ pcsTlMst.getPrlmEntrydate() + "','DD-Mon-YYYY HH24:MI')::date " + "   and PRLM_SHIFTID = '"
						+ pcsTlMst.getPrlmShiftid() + "'";
				Checksql += " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid() + "' and PRLM_CELLID = '"
						+ pcsTlMst.getPrlmCellid() + "' and ";
				Checksql += " PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() + "' and PRLM_MACHINEID = '"
						+ pcsTlMst.getPrlmMachineid() + "' ";

				// String Checksql = " select count(*) from PCS_TL_MST where
				// TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"+ pcsTlMst.getPrlmEntrydate()
				// +"','DD-MON-YYYY HH24:MI')) and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid()
				// +"'";
				// Checksql = Checksql + " and PRLM_SECTIONID = '"+ pcsTlMst.getPrlmSectionid()
				// + "' and PRLM_CELLID = '" + pcsTlMst.getPrlmCellid() + "' and ";
				// Checksql = Checksql + " PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() +
				// "' and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' " ;

				CommonMessage.debugMsg("Checksql " + Checksql);
				String checkcount = dbActionTemplate.getSingleValue(Checksql);

				CommonMessage.debugMsg("checkcount" + checkcount);
				if (Integer.parseInt(checkcount) > 0) {
					// String Checksqlkeyid = " select PRLM_KEYID from PCS_TL_MST where
					// TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"+ pcsTlMst.getPrlmEntrydate()
					// +"','DD-MON-YYYY HH24:MI')) and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid()
					// +"'";
					// Checksqlkeyid = Checksqlkeyid + " and PRLM_SECTIONID = '"+
					// pcsTlMst.getPrlmSectionid() + "' and PRLM_CELLID = '" +
					// pcsTlMst.getPrlmCellid() + "' and ";
					// Checksqlkeyid = Checksqlkeyid + " PRLM_SUBGROUPID = '" +
					// pcsTlMst.getPrlmSubgroupid() + "' and PRLM_MACHINEID = '" +
					// pcsTlMst.getPrlmMachineid() + "' " ;

					// -- Vignesh
					String Checksqlkeyid = " select PRLM_KEYID from PCS_TL_MST "
							+ " where PRLM_ENTRYDATE::date = to_timestamp('" + pcsTlMst.getPrlmEntrydate()
							+ "','DD-Mon-YYYY HH24:MI')::date " + "   and PRLM_SHIFTID = '" + pcsTlMst.getPrlmShiftid()
							+ "'";
					Checksqlkeyid += " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid() + "' and PRLM_CELLID = '"
							+ pcsTlMst.getPrlmCellid() + "' and ";
					Checksqlkeyid += " PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() + "' and PRLM_MACHINEID = '"
							+ pcsTlMst.getPrlmMachineid() + "' ";

					String id = dbActionTemplate.getSingleValue(Checksqlkeyid);

					pcsTlMst.setPrlmKeyid(id);
					sqls.add(PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table
					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());

					CommonMessage.debugMsg("sqls for Mst Dtl  " + sqls.toString());
				} else {

					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST)); // set the
																											// sequnce
																											// number
					// PcsTlMstSql.getUpdateSql(fieldTypeArr, dataArray)
					sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table

					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					pcsTlDtl.setPldetailsid(
							dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
					sqls.add(pcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table

					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());

					CommonMessage.debugMsg("sqls for Mst Dtl  " + sqls.toString());

				}

				// }

				if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
					// pcsTlLossreasonlink.setPlrkLossid(pcsTlDtl.getPldetailsid());
					pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
					// pcsTlLossreasonlink.setPlrkMinutes(pcsTlLosscapture.getPlosLosstime());
					pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
					pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
					pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());

					sqls.addAll(createLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
							pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

					pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","
							+ pcsTlLossreasonlink.getPlrkMinutes());
				} else { // pcsTlLossreasonlink.setPlrkLossid(pcsTlDtl.getPldetailsid());
					pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
					// pcsTlLossreasonlink.setPlrkMinutes(pcsTlLosscapture.getPlosLosstime());
					pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
					sqls.addAll(updateLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
							pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

					pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","
							+ pcsTlLossreasonlink.getPlrkMinutes());

				}

			}

		} else {

			for (int i = 0; i <= Noofshift.size(); i++) {

				// pcsTlMst.setPrlmShiftid(Noofshift.get(i)[0]);
				pcsTlMst.setPrlmShiftid("SFT001");
				// String Shiftid = Noofshift.get(i)[0];
				String Shiftid = "SFT001";
				CommonMessage.debugMsg("Shiftid" + Shiftid);
				// CommonMessage.debugMsg("Shiftid:::"+Noofshift.get(i)[0]);
				CommonMessage.debugMsg("Shiftid:::" + Shiftid);
				if (i == 0) {
					Onedateplus = pcsTlLosscapture.getPlosFromtime();
					Onedateplus = Onedateplus.substring(0, 11);

					pcsTlMst.setPrlmDate(Onedateplus);
					pcsTlMst.setPrlmEntrydate(Onedateplus);

				} else {
					CommonMessage.debugMsg("Noofshift.get(i)[0]" + Noofshift.get(i)[0]);

					// CommonMessage.debugMsg("Noofshift.get(i)[0]:::"+Noofshift.get(i)[0]);
					if (Shiftid.equals("SFT001")) {
						String frmdate = Onedateplus;
						// frmdate = frmdate.substring(0,11);
						String Sql = "Select to_char(to_date('" + frmdate
								+ "','DD-MON-YYYY')+ 1 , 'DD-MON-YYYY') FROM DUAL";
						CommonMessage.debugMsg("Noofshift.get(i)[0] Sql" + Sql);
						Onedateplus = dbActionTemplate.getSingleValue(Sql);

						CommonMessage.debugMsg(" Onedateplus " + Onedateplus);

						pcsTlMst.setPrlmDate(Onedateplus);
						pcsTlMst.setPrlmEntrydate(Onedateplus);

						CommonMessage.debugMsg(" PrlmDate inside " + pcsTlMst.getPrlmDate()
								+ pcsTlMst.getPrlmEntrydate() + " " + Onedateplus);
					} else {
						pcsTlMst.setPrlmDate(Onedateplus);
						pcsTlMst.setPrlmEntrydate(Onedateplus);
					}
				}

				CommonMessage.debugMsg(" PrlmDate " + pcsTlMst.getPrlmDate() + pcsTlMst.getPrlmEntrydate());

				// pcsTlDtl.setCalendartime(Noofshift.get(i)[2]);
				pcsTlDtl.setCalendartime("480");
				// if (! UIUtils.isValidKeyId( pcsTlMst.getPrlmKeyid())) {
				CommonFunctions
						.debugMsg("pcsTlLosscapture.getPlospldetailsid()  " + pcsTlLosscapture.getPlospldetailsid());

				pcsTlLosscapture.setPlosPldetailsid(Plospldetailsid);

				if (pcsTlLosscapture.getPlospldetailsid().equals("false"))
					pcsTlLosscapture.setPlosPldetailsid("");

				CommonFunctions
						.debugMsg("pcsTlLosscapture.getPlospldetailsid()  " + pcsTlLosscapture.getPlospldetailsid());

				// if (! UIUtils.isValidKeyId ( pcsTlLosscapture.getPlospldetailsid())) {

				CommonMessage.debugMsg("Inside mst dtl insert 2 ");

				String Checksql = " select count(*) from PCS_TL_MST where TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"
						+ pcsTlMst.getPrlmEntrydate() + "','DD-MON-YYYY HH24:MI'))  and PRLM_SHIFTID = '"
						+ pcsTlMst.getPrlmShiftid() + "'";
				Checksql = Checksql + " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '"
						+ pcsTlMst.getPrlmCellid() + "' and ";
				Checksql = Checksql + " PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid()
						+ "'  and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' ";

				CommonMessage.debugMsg("Checksql " + Checksql);
				String checkcount = dbActionTemplate.getSingleValue(Checksql);

				CommonMessage.debugMsg("checkcount" + checkcount);
				if (Integer.parseInt(checkcount) > 0) {
					String Checksqlkeyid = " select PRLM_KEYID from PCS_TL_MST where TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"
							+ pcsTlMst.getPrlmEntrydate() + "','DD-MON-YYYY HH24:MI'))  and PRLM_SHIFTID = '"
							+ pcsTlMst.getPrlmShiftid() + "'";
					Checksqlkeyid = Checksqlkeyid + " and PRLM_SECTIONID = '" + pcsTlMst.getPrlmSectionid()
							+ "'  and PRLM_CELLID = '" + pcsTlMst.getPrlmCellid() + "' and ";
					Checksqlkeyid = Checksqlkeyid + " PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid()
							+ "'  and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' ";

					String id = dbActionTemplate.getSingleValue(Checksqlkeyid);

					pcsTlMst.setPrlmKeyid(id);
					sqls.add(PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					sqls.add(pcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table
					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());

					CommonMessage.debugMsg("sqls for Mst Dtl  " + sqls.toString());
				} else {

					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST)); // set the
																											// sequnce
																											// number
					// PcsTlMstSql.getUpdateSql(fieldTypeArr, dataArray)
					sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add
																												// insert
																												// sql
																												// for
																												// master
																												// table

					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					pcsTlDtl.setPldetailsid(
							dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_PCS_TL_DTL, 15, "PDE", "YY", "Y"));
					sqls.add(pcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(),
							pcsTlDtl.getSaveArray()));// add insert sql for detail table

					pcsTlLosscapture.setPlosPldetailsid(pcsTlDtl.getPldetailsid());

					CommonMessage.debugMsg("sqls for Mst Dtl  " + sqls.toString());

				}

				// }

				if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
					// pcsTlLossreasonlink.setPlrkLossid(pcsTlDtl.getPldetailsid());
					// pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
					pcsTlLossreasonlink.setPlrkMinutes(pcsTlLosscapture.getPlosLosstime());
					pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
					pcsTlLossreasonlink.setPlrkDate(pcsTlMst.getPrlmDate());
					pcsTlLossreasonlink.setPlrkShiftid(pcsTlMst.getPrlmShiftid());

					sqls.addAll(createLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
							pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

					pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","
							+ pcsTlLossreasonlink.getPlrkMinutes());
				} else { // pcsTlLossreasonlink.setPlrkLossid(pcsTlDtl.getPldetailsid());
							// pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
					pcsTlLossreasonlink.setPlrkMinutes(pcsTlLosscapture.getPlosLosstime());
					pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());
					sqls.addAll(updateLosssql(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
							pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()));

					pcsdtl.add(detailTableName + "," + pcsTlDtl.getPldetailsid() + ","
							+ pcsTlLossreasonlink.getPlrkMinutes());

				}

			}

		}
		if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlospldetailsid()))
			pcsTlLosscapture.setPlosPldetailsid("-");

		if (!UIUtils.isValidKeyId(pcsTlLosscapture.getPlosKeyid())) {
			pcsTlLosscapture.setPlosKeyid(dbActionTemplate.getSequenceNumber(PcsTlLosscaptureSql.TBL_PCS_TL_LOSSCAPTURE,
					15, "PLOS", "MMYY", "Y")); // set the sequnce number
			sqls.add(PcsTlLosscaptureSql.getInsertSql(pcsTlLosscaptureSql.getPlosDbFields(),
					pcsTlLosscapture.getSaveArray()));

			pcsTlLossreasonlink.setPlrkPldetailid(pcsTlDtl.getPldetailsid());

			CommonMessage.debugMsg("sqls" + sqls);

			dbActionTemplate.executeStatements(sqls);

			/*
			 * for (int i = 0;i < Noofshift.size();i++) { CommonMessage.debugMsg(
			 * "Noofshiftdtl.size () 1234 = " + i );
			 * pcsTlLossreasonlink.setPlrkMinutes(Noofshift.get(i)[2]);
			 * createLoss(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
			 * pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes()); }
			 */

			// createLoss(pcsTlLossreasonlink, pcsTlDtl.getPldetailsid(),
			// pcsTlLossreasonlink.getPlrkLossid(), pcsTlLossreasonlink.getPlrkMinutes());

		} else {

			// -------------- Vignesh Altered 13Nov2025
			// -----------------------------------//

//			String sql1 =  " SELECT MAX(PLRK_KEYID) FROM PCS_TL_LOSSREASONLINK WHERE PLRK_REASONID = '" + pcsTlLosscapture.getPlosLossreason() + "' " ;
//				sql1+= " AND PLRK_LOSSID = '" + pcsTlLosscapture.getPlosLossid() + "' " ;
//				sql1+= " AND TRUNC(PLRK_DATE) = '" + pcsTlLosscapture.getPlosDate() + "' AND PLRK_SHIFTID = '" + pcsTlLosscapture.getPlosShiftid() + "' AND PLRK_FLID = '" + pcsTlLosscapture.getPlosFlid() + "' " ;
//				

			String sql1 = " SELECT MAX(PLRK_KEYID) FROM PCS_TL_LOSSREASONLINK WHERE PLRK_REASONID = '"
					+ pcsTlLosscapture.getPlosLossreason() + "' " + " AND PLRK_LOSSID = '"
					+ pcsTlLosscapture.getPlosLossid() + "' "
					// ⬇️ Postgres: cast column to date and compare to a parsed date
					+ " AND PLRK_DATE::date = to_date('" + pcsTlLosscapture.getPlosDate() + "', 'DD-Mon-YYYY') "
					+ " AND PLRK_SHIFTID = '" + pcsTlLosscapture.getPlosShiftid() + "' " + " AND PLRK_FLID = '"
					+ pcsTlLosscapture.getPlosFlid() + "' ";

			// -------------- Vignesh Altered 13Nov2025
			// -----------------------------------//

			CommonMessage.debugMsg(" error trunc date" + sql1);

			String plrkKeyid = dbActionTemplate.getSingleValue(sql1);
			pcsTlLossreasonlink.setPlrkKeyid(plrkKeyid);

			sqls.add(PcsTlLosscaptureSql.getUpdateSql(pcsTlLosscaptureSql.getPlosDbFields(),
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
		CommonMessage.debugMsg("pcsdtl.size()" + pcsdtl.size());
		for (int i = 0; i < pcsdtl.size(); i++) {
			String dfvalues = pcsdtl.get(i);
			List<String> dfsqls = new ArrayList<String>();

			String[] values = dfvalues.split(",");
			CommonMessage.debugMsg("pcsdtl.size() values " + values[0] + "2 " + values[1] + "3 " + values[2]);
			runBulkSqls(dfsqls, values[0], values[1], values[2], pcsTlLosscapture.getPlosFlid());
		}

		return pcsTlLosscapture;

	}

	public List<String> createLosssql(PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkSectionid(),
				pcsTlLossreasonlink.getPlrkDate());
		String cycleTime = "0";
		// CommonMessage.debugMsg("detailTableName"+detailTableName);

		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		// CommonMessage.debugMsg("lossMapId"+lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonMessage.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonMessage.debugMsg("loss cOLUMN Name" + lossColName);

		// String sql =
		// pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkReasonid());
		// String causeCount =
		// pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkReasonid());

		String sql = pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkCauseid());
		String causeCount = pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkCauseid());

		CommonMessage.debugMsg("loss cause sql....." + causeCount);
		String checkExistId = dbActionTemplate.getSingleValue(sql);
		causeCount = dbActionTemplate.getSingleValue(sql);
		// CommonMessage.debugMsg("loss cause count....."+checkExistId);
		PcsTlLosscausemst pcsTlLosscausemst = new PcsTlLosscausemst();
		PcsTlLosscausemstSql pcsTlLosscausemstSql = new PcsTlLosscausemstSql();
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
		// CommonMessage.debugMsg("checkLossExist--------->> "+checkLossExist);
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getInsertSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		PcsTlDtl pcsTlDtl = new PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonMessage.debugMsg("test sql3................................." + sqls);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		// runBulkSqls(sqls,detailTableName,Pldetailsid,cycleTime);

		return sqls;
	}

	public PcsTlLossreasonlink createLoss(PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");

		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkSectionid(),
				pcsTlLossreasonlink.getPlrkDate());
		String cycleTime = "0";
		CommonMessage.debugMsg("detailTableName" + detailTableName);

		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonMessage.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonMessage.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonMessage.debugMsg("loss cOLUMN Name" + lossColName);

		// String sql =
		// pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkReasonid());
		// String causeCount =
		// pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkReasonid());

		String sql = pcsTlLossreasonlinkSql.checkExistId(pcsTlLossreasonlink.getPlrkCauseid());
		String causeCount = pcsTlLossreasonlinkSql.pcsCauseCount(pcsTlLossreasonlink.getPlrkCauseid());
		CommonMessage.debugMsg("loss cause sql....." + causeCount);
		String checkExistId = dbActionTemplate.getSingleValue(sql);
		causeCount = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg("loss cause count....." + checkExistId);
		PcsTlLosscausemst pcsTlLosscausemst = new PcsTlLosscausemst();
		PcsTlLosscausemstSql pcsTlLosscausemstSql = new PcsTlLosscausemstSql();
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
		CommonMessage.debugMsg("checkLossExist--------->> " + checkLossExist);
		if (UIUtils.isValidKeyId(checkLossExist)) {
			if (Integer.parseInt(checkLossExist) > 0)
				throw new BusinessApplicationExceptions("LOSSEXIST,");

		}
		sqls.add(pcsTlLossreasonlinkSql.getInsertSql(pcsTlLossreasonlinkSql.getPlrkDbFields(),
				pcsTlLossreasonlink.getSaveArray())); // add insert sql for master table

		PcsTlDtl pcsTlDtl = new PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonMessage.debugMsg("test sql3................................." + sqls);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, pcsTlLossreasonlink.getPlrkFlid());

		return pcsTlLossreasonlink;
	}

	private PcsTlLosscausemst lossCauseFillValues(PcsTlLosscausemst pcsTlLosscausemst,
			PcsTlLossreasonlink pcsTlLossreasonlink) {

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

	public List<String> updateLosssql(PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");
		String cycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkSectionid(),
				pcsTlLossreasonlink.getPlrkDate());

		// CommonMessage.debugMsg("detailTableName"+detailTableName);
		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		// CommonMessage.debugMsg("lossMapId"+lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		// CommonMessage.debugMsg("ParameterCode"+ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		// CommonMessage.debugMsg("loss cOLUMN Name"+lossColName);

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

		PcsTlDtl pcsTlDtl = new PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
//CommonMessage.debugMsg("test sql1................................."+sqls);
		ancilliaryTime(pcsTlLossreasonlink, sqls);

//		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
//		getOEECalculation(detailTableName,Pldetailsid);

		// runBulkSqls(sqls,detailTableName,Pldetailsid,cycleTime);

		return sqls;
	}

	public PcsTlLossreasonlink updateLoss(PcsTlLossreasonlink pcsTlLossreasonlink, String Pldetailsid, String lossId,
			String lossValue) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */

		// String detailTableName = "PCS_TL_" +
		// dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		// "SECT_KEYID", pcsTlLossreasonlink.getPlrkSectionid());
		// detailTableName = detailTableName.replaceAll("-", "#");
		String cycleTime = "0";
		String detailTableName = getDetailTableName(pcsTlLossreasonlink.getPlrkSectionid(),
				pcsTlLossreasonlink.getPlrkDate());

		CommonMessage.debugMsg("detailTableName" + detailTableName);
		String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
				lossId);
		CommonMessage.debugMsg("lossMapId" + lossMapId);
		String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
				"PLCM_KEYID", lossId);
		CommonMessage.debugMsg("ParameterCode" + ParameterCode);
		String lossColName = getColName(ParameterCode, lossId);
		CommonMessage.debugMsg("loss cOLUMN Name" + lossColName);

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

		PcsTlDtl pcsTlDtl = new PcsTlDtl();
		cycleTime = pcsTlDtl.getActualcycletime();
		sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
				pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
		CommonMessage.debugMsg("test sql1................................." + sqls);
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
			resultList = dbActionTemplate.processFunctionCalls("PCS_PC_PRODLOG.PCS_FN_INSERTPCSFORNOPLAN", paramValues);
			String keyid;
			if (resultList.size() > 0) {
				CommonMessage.debugMsg(resultList.get(0)[0]);
				keyid = resultList.get(0)[0];
			} else
				keyid = "";
			return keyid;

		} catch (Exception e) {

			CommonMessage.debugMsg("Exception in insertNoPlan impl" + e.getMessage());
			return "fail";
		}
		/*
		 * List<String> sqls = new ArrayList<String>(); List<Object[]> valueList = new
		 * ArrayList<Object[]>(); List<int[]> dataTypes = new ArrayList<int[]>();
		 * List<String> charList = new ArrayList<String>(); String
		 * sql="SELECT TO_CHAR(SFTM_STARTTIME,'HH:MI:SS') FROM " +
		 * TableNames.TBL_GEN_TL_SHIFTMST + " WHERE SFTM_KEYID ='" + shift + "' ";
		 * String fromTime = date+ " " + dbActionTemplate.getSingleValue(sql);
		 * sql="SELECT TO_CHAR(SFTM_ENDTIME,'HH:MI:SS') FROM " +
		 * TableNames.TBL_GEN_TL_SHIFTMST + " WHERE SFTM_KEYID ='" + shift + "' ";
		 * String endTime = date + " " +dbActionTemplate.getSingleValue(sql);
		 * 
		 * CommonMessage.debugMsg("fromTime="+fromTime);
		 * CommonMessage.debugMsg("endTime="+endTime);
		 * 
		 * String insertPCSSql = "PCS_PC_PRODLOG.PCS_FN_INSERTPCSONCOMPLETEMSR"; Object
		 * [] insertPCSDatas = {mchId,fromTime,endTime,"-","NPNWO",userId}; int []
		 * insertPCSTypes =
		 * {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.
		 * VARCHAR}; sqls.add(insertPCSSql); valueList.add(insertPCSDatas);
		 * dataTypes.add(insertPCSTypes); charList.add("P");
		 * 
		 * char [] sqlType = new char[charList.size()]; for(int
		 * c=0;c<sqlType.length;c++) { sqlType[c] = charList.get(c).charAt(0);
		 * CommonMessage.debugMsg(c +" : "+sqlType[c]); }
		 * dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType); return
		 * "";
		 */

	}

	private List<String> ancilliaryTime(PcsTlLossreasonlink pcsTlLossreasonlink, List<String> sqls) throws Exception {
		// CommonMessage.debugMsg("SIZE OF
		// GRID"+pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size());
		if (pcsTlLossreasonlink.getPcsTlAncilliarytimeList() != null
				&& pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() >= 0) // check for detail table data
		{
			if (pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size() >= 0) {
				CommonMessage.debugMsg("ancilliary Id for time" + pcsTlLossreasonlink.getPlrkLossid());
				sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(pcsTlLossreasonlink.getPlrkPldetailid(),
						pcsTlLossreasonlink.getPlrkLossid()));
			}

			for (int i = 0; i < pcsTlLossreasonlink.getPcsTlAncilliarytimeList().size(); i++) {
				PcsTlAncilliarytime pcsTlAncilliarytime = (PcsTlAncilliarytime) pcsTlLossreasonlink
						.getPcsTlAncilliarytimeList().get(i);
				CommonMessage.debugMsg("pcsTlLossreasonlink.getPcsTlAncilliarytimeList().get(i).active"
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

	public void getOEECalculation(String detailTableName, String Pldetailsid, String flid) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(detailTableName);
		paramValues.add(Pldetailsid);
		paramValues.add(flid);

		dbActionTemplate.processFunctionCalls("PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL", paramValues);
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
		CommonMessage.debugMsg(lossList);
		String lossId = null;
		if (lossList != null) {
			CommonMessage.debugMsg(lossList.size());
			if (lossList.size() > 0) {
				int flag = 0;
				for (String[] loss : lossList) {

					for (int i = 0; i < loss.length; i++) {
						CommonMessage.debugMsg(loss[i]);
						if (loss[i].indexOf("Rejected") > 0)
							lossId = lossList.get(flag)[0];
						CommonMessage.debugMsg(i + "  : " + lossList.get(flag)[0]);
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
	 * CommonMessage.debugMsg("loss link not exists ="+sql);
	 * dbActionTemplate.executeStatement(sql);
	 * 
	 * String sql2 = " SELECT COUNT(*) FROM " + detailTableName +
	 * "  WHERE PLMASTERID = '" + plmasterId + "' "; String detCount =
	 * dbActionTemplate.getSingleValue(sql2); if (detCount.equals("0")) { sql2 =
	 * " DELETE FROM PCS_TL_MST WHERE PRLM_KEYID = '" + plmasterId + "' ";
	 * CommonMessage.debugMsg("master no data exists ="+sql2);
	 * dbActionTemplate.executeStatement(sql2);
	 * 
	 * return "Success_withMaster"; }
	 * 
	 * }
	 * 
	 * return "Success"; } else { return "Exists"; } }
	 * 
	 * catch(Exception e) {
	 * CommonMessage.debugMsg("error while deleteing "+e.getMessage()); return
	 * "fail"; } }
	 */

	@Override
	public PcsTlMst deletePcsLossEntryITCNew(String plrkKeyid, String pldetailsid, String sectId) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		PcsTlMst pcsTlMst = new PcsTlMst();
		// GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
		// GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
		// GenTlMomattendanceSql genTlMomattendanceSql=new GenTlMomattendanceSql();
		// GenTlMomKpiLinkSql genTlMomKpiLinkSql=new GenTlMomKpiLinkSql();
		// GenTlVisitorsSql newGenTlVisitorsSql=new GenTlVisitorsSql();

		String detailTableName = "PCS_TL_"
				+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
		// -------------- Vignesh replaced # to _
		detailTableName = detailTableName.trim().replaceAll("-", "_");
		detailTableName = detailTableName.replaceAll(" ", "");

		CommonMessage.debugMsg(" detailTableName :: " + detailTableName);
		detailTableName.toLowerCase();
		// PcsTlDtl pcsTlDtl = new PcsTlDtl();

		// sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName,Pldetailsid,
		// pcsTlDtlSql.getPcs_DbFields() ,pcsTlDtl.getSaveArray(),lossId, lossMapId,
		// lossColName, lossValue));
		// CommonMessage.debugMsg("test sql2................................."+sqls);

		CommonMessage.debugMsg(" Hello Checking mstid :: " + plrkKeyid + " String " + pldetailsid);

		String sql = "";
		String mstkeyid;
		sql = " select plmasterid from " + detailTableName + " where 1=1 AND pldetailsid= '" + pldetailsid + "'";

		mstkeyid = dbActionTemplate.getSingleValue(sql);

		CommonMessage.debugMsg(" Hello Checking mstid :: " + mstkeyid);

		sqls.add("Delete from " + detailTableName + " where  pldetailsid ='" + pldetailsid + "'");
		sqls.add("Delete from PCS_TL_MST where prlm_keyid='" + mstkeyid + "'");
		sqls.add("Delete from pcs_tl_lossreasonlink where plrk_pldetailid ='" + pldetailsid + "'");
		sqls.add("Delete from PCS_TL_LOSSCAPTURE where plos_keyid ='" + plrkKeyid + "'");
		sqls.add("DELETE from PCS_TL_ANCILLIARYTIME where PTAT_PLDEATILSID = '" + pldetailsid + "'");

		// sqls.add(GenTlMommstSql.getDeleteSql(genTlMommstSql.getMomsDbFields(),
		// genTlMommst.getSaveArray()));

		dbActionTemplate.executeStatements(sqls);

		return pcsTlMst;
	}

	public String deletePcsLossEntryITC(String PlrkKeyid, String PlosKeyid, String Pldetailsid, String sectId,
			String lossId, String lossVal) throws Exception {
		try {
			StringBuffer sql1 = new StringBuffer();
			String cycleTime = "0";
			List<String> sqls = new ArrayList<String>();

			StringBuffer sql0 = new StringBuffer();
			sql0.append(" DELETE FROM Pcs_Tl_Losscapture ");
			sql0.append(" WHERE PLOS_KEYID = '" + PlosKeyid + "' ");

			CommonMessage.debugMsg("ancilliary Id for time" + lossId);

			sqls.add(sql0.toString());

			String plrkMsrno = dbActionTemplate.getSingleValue(
					"SELECT PLRK_MSRNO FROM PCS_TL_LOSSREASONLINK WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonMessage.debugMsg("plrkMsrno=" + plrkMsrno);

			if (!plrkMsrno.equals("{}") && !plrkMsrno.equals("") && !plrkMsrno.equals(" ") && plrkMsrno != null)
				return "LockDelete";
			// throw new BusinessApplicationExceptions("view_LockDelete"+",");

			sql1.append(" DELETE FROM PCS_TL_LOSSREASONLINK ");
			sql1.append(" WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonMessage.debugMsg("ancilliary Id for time" + lossId);
			sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(Pldetailsid, lossId));

			sqls.add(sql1.toString());

			CommonMessage.debugMsg("loss delete sql=" + sql1.toString());

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
			detailTableName = detailTableName.trim().replaceAll("-", "#");
			detailTableName = detailTableName.replaceAll(" ", "");

			String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
					lossId);
			String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
					"PLCM_KEYID", lossId);
			String lossColName = getColName(ParameterCode, lossId);
			String lossValue = "0";

			PcsTlDtl pcsTlDtl = new PcsTlDtl();
			cycleTime = pcsTlDtl.getActualcycletime();
			sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
			CommonMessage.debugMsg("test sql2................................." + sqls);
			// update the parent values

			String parentLossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARENTID",
					"PLCM_KEYID", lossId);

			if (!lossId.equals(parentLossId)) {
				String parentParamCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
						"PLCM_PARAMETERCODE", "PLCM_KEYID", parentLossId);
				String parentColName = getColName(parentParamCode, lossId);

				CommonMessage.debugMsg("parentLossId" + parentLossId);

				StringBuffer sql2 = new StringBuffer();
				sql2.append(" UPDATE " + detailTableName);
				// sql2.append(" SET " + parentColName + " = "+ parentColName + " - " +
				// lossVal);
				sql2.append(" SET " + parentColName + " = DECODE(" + parentColName + " - " + lossVal + ",0,NULL,"
						+ parentColName + " - " + lossVal + ")");
				sql2.append(" WHERE PLDETAILSID = '" + Pldetailsid + "' ");

				CommonMessage.debugMsg("update sqlsss" + sql2.toString());

				sqls.add(sql2.toString());
			}

//			dbActionTemplate.executeStatements(sqls);
//			getOEECalculation(detailTableName,Pldetailsid);

			runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, "");

			return "Success";
		}

		catch (Exception e) {
			CommonMessage.debugMsg("error while deleteing " + e.getMessage());
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

			CommonMessage.debugMsg("plrkMsrno=" + plrkMsrno);

			if (!plrkMsrno.equals("{}") && !plrkMsrno.equals("") && !plrkMsrno.equals(" ") && plrkMsrno != null)
				return "LockDelete";
			// throw new BusinessApplicationExceptions("view_LockDelete"+",");

			sql1.append(" DELETE FROM PCS_TL_LOSSREASONLINK ");
			sql1.append(" WHERE PLRK_KEYID = '" + PlrkKeyid + "' ");

			CommonMessage.debugMsg("ancilliary Id for time" + lossId);
			sqls.add(pcsTlAncilliarytimeSql.getDeleteAncilliarySql(Pldetailsid, lossId));

			sqls.add(sql1.toString());

			CommonMessage.debugMsg("loss delete sql=" + sql1.toString());

			String detailTableName = "PCS_TL_"
					+ dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
			detailTableName = detailTableName.trim().replaceAll("-", "#");
			detailTableName = detailTableName.replaceAll(" ", "");

			String lossMapId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_MAPFIELD", "PLCM_KEYID",
					lossId);
			String ParameterCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARAMETERCODE",
					"PLCM_KEYID", lossId);
			String lossColName = getColName(ParameterCode, lossId);
			String lossValue = "0";

			PcsTlDtl pcsTlDtl = new PcsTlDtl();
			cycleTime = pcsTlDtl.getActualcycletime();
			sqls.add(pcsTlDtlSql.getSingleUpdtSql(detailTableName, Pldetailsid, pcsTlDtlSql.getPcs_DbFields(),
					pcsTlDtl.getSaveArray(), lossId, lossMapId, lossColName, lossValue));
			CommonMessage.debugMsg("test sql2................................." + sqls);
			// update the parent values

			String parentLossId = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION", "PLCM_PARENTID",
					"PLCM_KEYID", lossId);

			if (!lossId.equals(parentLossId)) {
				String parentParamCode = dbActionTemplate.getSingleValue("PCS_TL_LOGCONFIGURATION",
						"PLCM_PARAMETERCODE", "PLCM_KEYID", parentLossId);
				String parentColName = getColName(parentParamCode, lossId);

				CommonMessage.debugMsg("parentLossId" + parentLossId);

				StringBuffer sql2 = new StringBuffer();
				sql2.append(" UPDATE " + detailTableName);
				// sql2.append(" SET " + parentColName + " = "+ parentColName + " - " +
				// lossVal);
				sql2.append(" SET " + parentColName + " = DECODE(" + parentColName + " - " + lossVal + ",0,NULL,"
						+ parentColName + " - " + lossVal + ")");
				sql2.append(" WHERE PLDETAILSID = '" + Pldetailsid + "' ");

				CommonMessage.debugMsg("update sqlsss" + sql2.toString());

				sqls.add(sql2.toString());
			}

//			dbActionTemplate.executeStatements(sqls);
//			getOEECalculation(detailTableName,Pldetailsid);

			runBulkSqls(sqls, detailTableName, Pldetailsid, cycleTime, "");

			return "Success";
		}

		catch (Exception e) {
			CommonMessage.debugMsg("error while deleteing " + e.getMessage());
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
			CommonMessage.debugMsg("error while deleteing " + e.getMessage());
			return "fail";
		}
	}

//getRowNo
	public String getColNameByLossNo(String ParameterLossNo) {
		String colName = "";

		ParameterLossNo = ParameterLossNo.trim();
		CommonMessage.debugMsg("ParameterLossNo==" + ParameterLossNo);
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
			CommonMessage.debugMsg("ParameterLossNo" + ParameterLossNo);
			colName = getColNameByLossNo(ParameterLossNo);
		}

		return colName;

	}

	@Override
	public PcsTlMst update(PcsTlMst PcsTlMst) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getShiftCount(String factId, String entryDate, String shiftId) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS CNT FROM ( SELECT SFTM_KEYID, SFTM_CODE, SFTM_SHIFTORDER");
		sql.append(" FROM GEN_TL_SHIFTMST WHERE SFTM_SHIFTORDER<='3' ");
		// sql.append(" AND SFTM_FACTORYID = '"+factId+"'");
		sql.append(" AND TO_DATE('" + entryDate
				+ "' || TO_CHAR(SFTM_STARTTIME, 'HH24:MI'), 'DD-MON-YYYY HH24:MI') <= SYSDATE ");
		sql.append(" AND SFTM_KEYID = '" + shiftId + "')");
		CommonMessage.debugMsg("sql.toString().........." + sql.toString());
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
					CommonMessage.debugMsg("loss link not exists =" + woLink);

					// dbActionTemplate.executeStatement(sql);
					// String sql2= null;
					String sqldetCount = " SELECT COUNT(*) FROM " + detailTableName + "  WHERE PLMASTERID = '"
							+ plmasterId + "' ";
					String detCount = dbActionTemplate.getSingleValue(sqldetCount);
					/* to change dteCount 0 to 1 and retStatus = "Success_withMaster" */
					if (detCount.equals("1")) {

						// sql2 = " DELETE FROM PCS_TL_MST WHERE PRLM_KEYID = '" + plmasterId + "' ";
						String delPcsMaster = pcsTlMstSql.delpcsMaster(plmasterId);
						CommonMessage.debugMsg("master no data exists =" + delPcsMaster);
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

					String insertPCSsavrSql = "PCS_PC_PRODUCTIONCALC.SAVE_PCS_TL_DAY";
					Object[] insertPCSsavrDatas = { "T", entryDate, "{}", machKeyId, 'N' };
					int[] insertPCSsavrTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
							Types.VARCHAR };
					sqls.add(insertPCSsavrSql);
					valueList.add(insertPCSsavrDatas);
					dataTypes.add(insertPCSsavrTypes);
					charList.add("P");
					// if(retStatus == null)
					// retStatus = "Success";

				}
				char[] sqlType = new char[charList.size()];
				CommonMessage.debugMsg("lENGTH : " + sqlType.length);
				for (int c = 0; c < sqlType.length; c++) {
					CommonMessage.debugMsg(c + " : " + charList.get(c));
					sqlType[c] = charList.get(c).charAt(0);
					CommonMessage.debugMsg(c + " : " + sqlType[c]);
				}

				dbActionTemplate.executeStatement(sqls, valueList, dataTypes, sqlType);

				return retStatus == null ? "Success" : retStatus;
			} else {
				return "Exists";
			}
		}

		catch (Exception e) {
			CommonMessage.debugMsg("error while deleteing " + e.getMessage());
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
		CommonMessage.debugMsg("COUNT : " + "SELECT COUNT(*) FROM " + detailTableName + " WHERE MACHINEID='" + mchId
				+ "' AND PRODUCTID='" + productId + "' AND PLMASTERID='" + mstId + "'");
		if (UIUtils.isValidKeyId(count)) {
			if (Integer.parseInt(count) > 0) {
				String sql = "SELECT PLDETAILSID,WNO,TRIMMINGQTY,EXPANSIONQTY , BACKLOGQTY, RAWMATERIALTYPE, WEIGHT  FROM "
						+ detailTableName;
				sql += " WHERE MACHINEID='" + mchId + "' AND PRODUCTID='" + productId + "' AND PLMASTERID='" + mstId
						+ "'";
				productionList = dbActionTemplate.getDataList(sql);
				CommonMessage.debugMsg("productionList sql: " + sql);
			}
		}
		CommonMessage.debugMsg("productionList : " + productionList.size());
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

		// List<String[]> dataList =
		// dbActionTemplate.processFunctionCallsWithColHeaders("PCS_FN_OTHERLOSSENTRY",
		// paramValues);
		List<String[]> dataList = fnCallApi.callFunction("PCS_FN_OTHERLOSSENTRY_SB", paramValues, 3, true);

		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt...." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(dataList);
		return dataList;
	}

	public String getSectionForCell(String cellid) throws Exception {

		String sql = "";
		CommonMessage.debugMsg("CELLID()===" + cellid);
		sql = " SELECT CELL_SECTIONID FROM GEN_TL_CELLMST WHERE CELL_KEYID='" + cellid + "' ";

		CommonMessage.debugMsg("sql()===" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	@Override
	public String getFlidOriginalid(String flid) throws Exception {
		String sql = "";
		CommonMessage.debugMsg("flid()===" + flid);
		sql = " SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_KEYID='" + flid + "' ";

		CommonMessage.debugMsg("sql()===" + sql);
		return dbActionTemplate.getSingleValue(sql);
	}

	/*
	 * @Override public List<String[]> getLossEntryData(GridParams gridParams,
	 * CommonFilter commonFilter, String flid) throws Exception { // TODO
	 * Auto-generated method stub CommonMessage.debugMsg("Flid in Dao Impl" +
	 * flid); List<String> paramValues = new ArrayList<String>(); StringBuilder sql
	 * = new StringBuilder(); String fromDate, toDate;
	 * CommonMessage.debugMsg("MONTHWISE:" + commonFilter.getChkMonwise()); if
	 * ("Y".equals(commonFilter.getMonwise())) { String from =
	 * commonFilter.getFromMonth(); String to = commonFilter.getToMonth(); fromDate
	 * = "01-" + from;
	 * CommonMessage.debugMsg(CommonFunctions.getLastDayOfMonth(to.substring(0,
	 * 2)) + "to is :" + to); toDate = CommonFunctions.getLastDayOfMonth("01-" +
	 * to); } else { String frDt = commonFilter.getFromDate();
	 * 
	 * fromDate=commonFilter.getdFromDate(); toDate=commonFilter.getdToDate();
	 * 
	 * if (frDt == null) { fromDate = "01-JAN-1801"; toDate = "31-DEC-2100"; } else
	 * { fromDate = commonFilter.getFromDate(); toDate = commonFilter.getToDate(); }
	 * } // sql.append("select * from (SELECT rownum AS slno, A.* FROM(");
	 * sql.append(pcsTlMstSql.getLossEntrySql(flid, fromDate, toDate));
	 * 
	 * CommonMessage.debugMsg("sql1:" + sql.toString());
	 * 
	 * String Flid=commonFilter.getFlid(); if(UIUtils.isValidKeyId(Flid)){
	 * sql.append(" AND FLID='"+Flid+"'");
	 * 
	 * }
	 * 
	 * String conditionalparam = FilterCondSql.getPCSRelatedCondStr(commonFilter);
	 * String commonparam = FilterCondSql.getGridCommonParams(commonFilter);
	 * paramValues.add(conditionalparam); paramValues.add(commonparam); String
	 * countSql = CommonFilterSqls.countSql(sql.toString(),
	 * gridParams.getGridFilters()); CommonMessage.debugMsg("sqlcount:" +
	 * countSql); CommonMessage.debugMsg("MONTHWISE:" +
	 * commonFilter.getMonwise()); String viewinfo =
	 * dbActionTemplate.getSingleValue(countSql); long counts =
	 * Long.parseLong(viewinfo); if (counts > 0) { String sqlStr =
	 * CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
	 * gridParams.setTotalRecordCnt(counts); CommonMessage.debugMsg("sql2FL:" +
	 * sqlStr.toString()); List<String[]> datacon =
	 * dbActionTemplate.getDataList(sqlStr); return datacon;
	 * 
	 * } // sql.append(" ) A");
	 * 
	 * String condSql =""; if( gridParams != null) { condSql =
	 * FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	 * sql.append(" WHERE 1=1 "); sql.append(condSql); } sql.append(
	 * ") where slno >= "); sql.append(start); sql.append(" and slno <= ");
	 * sql.append(end);
	 * 
	 * CommonMessage.debugMsg("SQL FOR PCS LOSS ENTRY GRID:" + sql.toString());
	 * throw new NoDataFoundException("No Data Found"); // return
	 * dbActionTemplate.getDataList(sql.toString()); }
	 */
	
	
	
	@Override
	public List<String[]> getLossEntryData(GridParams gridParams, CommonFilter commonFilter, String flid)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Flid in Dao Impl" + flid);
		List<String> paramValues = new ArrayList<String>();
		StringBuilder sql = new StringBuilder();
		String fromDate, toDate;
		CommonMessage.debugMsg("MONTHWISE:" + commonFilter.getChkMonwise());
		if ("Y".equals(commonFilter.getMonwise())) {
			String from = commonFilter.getFromMonth();
			String to = commonFilter.getToMonth();
			fromDate = "01-" + from;
			CommonMessage.debugMsg(CommonFunctions.getLastDayOfMonth(to.substring(0, 2)) + "to is :" + to);
			toDate = CommonFunctions.getLastDayOfMonth("01-" + to);
		} else {
			String frDt = commonFilter.getFromDate();
			/*
			 * fromDate=commonFilter.getdFromDate(); toDate=commonFilter.getdToDate();
			 */
			if (frDt == null) {
				fromDate = "01-JAN-1801";
				toDate = "31-DEC-2100";
			} else {
				fromDate = commonFilter.getFromDate();
				toDate = commonFilter.getToDate();
			}
		}

		String conditionalparam = FilterCondSql.getPCSRelatedCondStr(commonFilter);
		String commonparam = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(conditionalparam);
		paramValues.add(commonparam);
		
		List<String[]> dataList =  fnCallApi.callFunction("APP_FN_LOSSENTRYGRID_SB",paramValues,1,true);
	
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		
		if(  isInteger )
		{
			long counts=Long.parseLong(totalCnt);
			gridParams.setTotalRecordCnt(counts);
		}
		
	//		throw new NoDataFoundException("No Data Found");
		return dataList;

	}

	// ------------------------------------ 31Oct2025 ---- Vignesh
	// ------------------------------------------------------//

	@Override
	public String getTotalLossEntryData(String flid) throws Exception {
		CommonMessage.debugMsg("Flid in Dao Impl" + flid);

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM ( ");
		sql.append("  SELECT FLID, PLOSSDATE, DMT, JH, SHIFTID, COUNT(*) ");
		sql.append("  FROM ( ");
		sql.append("    SELECT ");
		sql.append("      h.flid AS FLID, ");
		sql.append("      to_char(l.plos_date::date, 'DD-MON-YYYY') AS PLOSSDATE, ");
		sql.append("      split_part(h.parents, '/', 4) AS DMT, ");
		sql.append("      coalesce(array_to_string((string_to_array(h.parents,'/'))[5:], '/'), '') AS JH, ");
		sql.append("      l.plos_shiftid AS SHIFTID ");
		sql.append("    FROM pcs_tl_losscapture l ");
		sql.append("    JOIN gen_mv_flidhierarchy h ON l.plos_flid = h.flid ");
		sql.append("    WHERE position('").append(flid).append("' in (h.parentflids || '/' || h.flid)) > 0 ");
		sql.append("      AND l.plos_fromtime::timestamp >= timestamp '1801-01-01' ");
		sql.append("      AND l.plos_fromtime::timestamp <= timestamp '2100-12-31' ");
		sql.append("  ) t ");
		sql.append("  GROUP BY FLID, PLOSSDATE, DMT, JH, SHIFTID ");
		sql.append(") s");

		CommonMessage.debugMsg("SQL FOR TOTAL PCS LOSS ENTRY GRID:" + sql.toString());
		return dbActionTemplate.getSingleValue(sql.toString());
	}

//	@Override
//	public String getTotalLossEntryData(String flid) throws Exception {
//		// TODO Auto-generated method stub
//		CommonMessage.debugMsg("Flid in Dao Impl"+flid);
//		StringBuilder sql=new StringBuilder();
//		sql.append("select count(*) from(select FLID,PLOSSDATE, DMT,JH,SHIFTID, COUNT(*) FROM ( ");
//		sql.append("SELECT FLID, TO_CHAR((PLOS_DATE),'DD-MON-YYYY') AS PLOSSDATE, ");
//		sql.append("SUBSTR(PARENTS, INSTR(PARENTS,'/',1,3)+1,LENGTH(SUBSTR(PARENTS,INSTR(PARENTS,'/',1,3)+1,INSTR(PARENTS,'/',1,4)-INSTR(PARENTS,'/',1,3)-1))) DMT, ");
//		sql.append("SUBSTR(PARENTS, INSTR(PARENTS,'/',1,4)+1, LENGTH(SUBSTR(PARENTS,INSTR(PARENTS,'/',1,4)+1))) JH,PLOS_SHIFTID SHIFTID ");
//		sql.append(" FROM PCS_TL_LOSSCAPTURE,GEN_MV_FLIDHIERARCHY ");
//		sql.append("WHERE  PLOS_FLID=FLID AND INSTR(PARENTFLIDS||'/'||FLID,'");
//		sql.append(flid);
//		sql.append("')>0 AND TO_DATE(PLOS_FROMTIME) >= TO_DATE('01-Jan-1801') AND TO_DATE(PLOS_FROMTIME) <= TO_DATE('31-DEC-2100')) ");
//		sql.append("GROUP BY FLID,PLOSSDATE, DMT,JH,SHIFTID order by PLOSSDATE)");
//		CommonMessage.debugMsg("SQL FOR TOTAL PCS LOSS ENTRY GRID:"+sql.toString());
//		return dbActionTemplate.getSingleValue(sql.toString());
//	}

	// ------------------------------------ 31Oct2025 ---- Vignesh
	// ------------------------------------------------------//

	@Override
	public Workbook getExcelreport(CommonFilter commonFilter, JSONObject tblJSONObj, String formats,
			GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {
			CommonMessage.debugMsg("formats==" + formats);
			rs = getExcelResultSet(gridParams, commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs, formats, 2, 0, 0);// elumalai

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}

	}

	public ResultSet getExcelResultSet(GridParams gridParams, CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try {
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			if (commonFilter.getDrillLevel().equals("LIN")) {
				if (UIUtils.isValidKeyId(commonFilter.getSectionId()))
					condParms += "SECTIONID=" + commonFilter.getSectionId() + ";";
			} else if (commonFilter.getDrillLevel().equals("FCT")) {
				if (UIUtils.isValidKeyId(commonFilter.getFactoryId()))
					condParms += "FACTORYID=" + commonFilter.getFactoryId() + ";";
			} else if (commonFilter.getDrillLevel().equals("CEL")) {
				if (UIUtils.isValidKeyId(commonFilter.getCellId()))
					condParms += "CELLID=" + commonFilter.getCellId() + ";";
			}
			if (UIUtils.isValidKeyId(commonFilter.getFlid()))
				condParms += "flid=" + commonFilter.getFlid() + ";";

			if (UIUtils.isValidKeyId(commonFilter.getIndicator()))
				condParms += "INDICATORID=" + commonFilter.getIndicator() + ";";

			if (UIUtils.isValidKeyId(commonFilter.getPillarWise()))
				condParms += "PILLARID=" + commonFilter.getPillarWise() + ";";

			if (UIUtils.isValidKeyId(commonFilter.getDrillLevel()))
				condParms += "DRILLLEVEL=" + commonFilter.getDrillLevel() + ";";

			if (UIUtils.isValidKeyId(commonFilter.getType()))
				condParms += "TYPE=" + commonFilter.getType() + ";";

			paramValues.add(condParms);
			paramValues.add(commonParams);
			String sql = KpiTlIndicatorDeptLinkSql.getPillarID(commonFilter.getPillarWise());
			String pillId = dbActionTemplate.getSingleValue(sql);
			/*
			 * String indicatorSql=KpiTlIndicatorDeptLinkSql.getIndicatorCnt(pillId);
			 * 
			 * if(UIUtils.isValidKeyId(commonFilter.getIndicator()))
			 * indicatorSql+="  and KINK_KEYID='"+commonFilter.getIndicator()+"'";
			 * 
			 * String indicatorCount=dbActionTemplate.getSingleValue(indicatorSql);
			 */

			rs = dbActionTemplate.NewdbFunctionCall2("PCS_FN_LOSSPHENFACTORYLINK", paramValues);

			// long totalCnt1 = Long.parseLong(indicatorCount);
			// gridParams.setTotalRecordCnt(totalCnt1);
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				// CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			// CommonMessage.debugMsg(" topFailList size " + topFailList.size());
			return rs;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	private ResultSet getResultSetRecords(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValue(commonFilter);
		StringBuffer sb = new StringBuffer();
		String flid = commonFilter.getFlid();
		String fromDate, toDate;
		CommonMessage.debugMsg("MONTHWISE:" + commonFilter.getChkMonwise());
		if ("Y".equals(commonFilter.getMonwise())) {
			String from = commonFilter.getFromMonth();
			String to = commonFilter.getToMonth();
			fromDate = "01-" + from;
			CommonMessage.debugMsg(CommonFunctions.getLastDayOfMonth(to.substring(0, 2)) + "to is :" + to);
			toDate = CommonFunctions.getLastDayOfMonth("01-" + to);
		} else {
			String frDt = commonFilter.getFromDate();
			/*
			 * fromDate=commonFilter.getdFromDate(); toDate=commonFilter.getdToDate();
			 */
			if (frDt == null) {
				fromDate = "01-JAN-1801";
				toDate = "31-DEC-2100";
			} else {
				fromDate = commonFilter.getFromDate();
				toDate = commonFilter.getToDate();
			}
		}
		sb.append(pcsTlMstSql.getLossEntrySql(flid, fromDate, toDate));
		CommonMessage.debugMsg("Sql====" + sb.toString());
		return dbActionTemplate.getData(sb.toString());
	}

	private List<String> getFilterParamValue(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

}
