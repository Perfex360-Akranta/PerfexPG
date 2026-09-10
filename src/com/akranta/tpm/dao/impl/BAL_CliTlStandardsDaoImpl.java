/*Author MANIKANDAN*/
package com.akranta.tpm.dao.impl;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.bean.BAL_CliTlStandardFormBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_CliTlStandardsDao;
import com.akranta.tpm.dao.sql.AbnormalityReportSqls;
import com.akranta.tpm.dao.sql.BAL_BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.BAL_CliTlStandardsSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlMstSql;
import com.akranta.tpm.dao.sql.OplTlCategorymstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.BAL_PlmTlMultiplemethodsmstSql;
//import com.akranta.tpm.dao.sql.PlmTlStandardsSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlToolsdtlSql;
import com.akranta.tpm.model.BAL_BdmTlYycountermeasurelink;
import com.akranta.tpm.model.BAL_CliTlStandards;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.KznTlMst;
import com.akranta.tpm.model.OplTlCategorymst;
import com.akranta.tpm.service.api.FieldAuditSheetServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.model.BAL_PlmTlMultiplemethodsmst;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.CliTlStandardsServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;


/* dao implementation */
public class BAL_CliTlStandardsDaoImpl implements BAL_CliTlStandardsDao {

	private static final String tbl_gen_tl_machinemst = "gen_tl_machinemst";
	private static final String TBL_BDM_TL_YYCOUNTERMEASURELINK = "BDM_TL_YYCOUNTERMEASURELINK";
	private DBActionTemplate dbActionTemplate;
	private BAL_CliTlStandardsSql cliTlStandardsSql;
	private BAL_PlmTlMultiplemethodsmstSql plmTlMultiplemethodsmstSql;
	private BAL_PlmTlToolsdtlSql plmTlToolsdtlSql;
	private BAL_BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql;
	private CliTlStandardsServiceApi cltiapi;
	FunctionCallApi fnCallApi;

	public BAL_CliTlStandardsDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
		cliTlStandardsSql = new BAL_CliTlStandardsSql();
		plmTlMultiplemethodsmstSql = new BAL_PlmTlMultiplemethodsmstSql();
		plmTlToolsdtlSql = new BAL_PlmTlToolsdtlSql();
		bdmTlYycountermeasurelinkSql = new BAL_BdmTlYycountermeasurelinkSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void BAL_CliTlStandardsDaoImplJwt(String JwtToken) 
	{
		try{
			cltiapi = new CliTlStandardsServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
  

	public BAL_CliTlStandards create(BAL_CliTlStandards cliTlStandards, String dkeyId) throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		// contains dbtable,field names, Field types and related sqls of master table

		try {
			BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
			String elementId = cliTlStandards.getClisElementid();
			String location = null;
			String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,
					BAL_CliTlStandardsSql.TBL_BAL_CLI_TL_STANDARDS);

			// cliTlStandards.setClisKeyid(dbActionTemplate.getSequenceNumber(CliTlStandardsSql.TBL_CLI_TL_STANDARDS));
			// // set the sequnce number
			cliTlStandards.setClisKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "CL", "YY", "Y")); // set the
																												// sequnce
																												// number

			sqls.add(BAL_CliTlStandardsSql.getInsertSql(cliTlStandardsSql.getClisDbFields(),
					cliTlStandards.getSaveArray())); // add insert sql for master table

			String cliKey = cliTlStandards.getClisKeyid();
			CommonFunctions.debugMsg("beanDAOIMPL @@@@@@@@@@@@@@@@@" + dkeyId);
			String updatesql = BAL_CliTlStandardsSql.updateDocUpdatesdatasql(dkeyId, cliKey);
			BAL_BdmTlYycountermeasurelink bdmTlYycountermeasureList = cliTlStandards.getCountermeasureLink();
			if (bdmTlYycountermeasureList != null) {// MLMM_REFDOCID
				CommonFunctions.debugMsg("Counter maesuDao");
				bdmTlYycountermeasureList.setYycmKeyid(dbActionTemplate.getSequenceNumber(
						BAL_BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK, 11, "YCM", "YYMM", "Y"));
				bdmTlYycountermeasureList.setYycmCountermsrid(cliTlStandards.getClisKeyid());
				String delCountrMeasr = "Delete from " + TBL_BDM_TL_YYCOUNTERMEASURELINK
						+ "  where YYCM_REFDOCTYPE = 'PMD' AND YYCM_YYID = '" + bdmTlYycountermeasureList.getYycmYyid()
						+ "'";
				sqls.add(delCountrMeasr);
				sqls.add(BAL_BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),
						bdmTlYycountermeasureList.getSaveArray()));
			}
			List<BAL_PlmTlMultiplemethodsmst> methodslist = cliTlStandards.getMethodDetail();

			if (methodslist != null && methodslist.size() > 0) {// MLMM_REFDOCID
				for (BAL_PlmTlMultiplemethodsmst multipleMethod : methodslist) {
					multipleMethod.setMlmmKeyid(dbActionTemplate
							.getSequenceNumber(BAL_PlmTlMultiplemethodsmstSql.TBL_PLM_TL_MULTIPLEMETHODSMST));
					multipleMethod.setMlmmRefdocid(cliTlStandards.getClisKeyid());
					sqls.add(BAL_PlmTlMultiplemethodsmstSql.getInsertSql(plmTlMultiplemethodsmstSql.getMlmmDbFields(),
							multipleMethod.getSaveArray()));
				}
			}

			// dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			List<BAL_PlmTlToolsdtl> toolsPickUplist = cliTlStandards.getToolsDetail();
			if (toolsPickUplist != null && toolsPickUplist.size() > 0) {// MLMM_REFDOCID
				for (BAL_PlmTlToolsdtl toolsPickUp : toolsPickUplist) {
					System.out.println("toolpickup ss");
					toolsPickUp.setPtldKeyid(
							dbActionTemplate.getSequenceNumber(BAL_PlmTlToolsdtlSql.TBL_BAL_PLM_TL_TOOLSDTL));
					// toolsPickUp.setPtldKeyid(dbActionTemplate.getSequenceNumber(PlmTlToolsdtlSql.TBL_PLM_TL_TOOLSDTL,13,
					// "TLDll", "MMYY", null));
					System.out.println("toolpickup ss" + toolsPickUp.getPtldKeyid());
					toolsPickUp.setPtldStandardid(cliTlStandards.getClisKeyid());

					sqls.add(BAL_PlmTlToolsdtlSql.getInsertSql(plmTlToolsdtlSql.getPtldDbFields(),
							toolsPickUp.getSaveArray()));
				}
			}
			System.out.println("Standard ID TOOLS :" + cliTlStandards.getClisKeyid());

			sqls.add(updatesql);

			dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return cliTlStandards;
	}

	public BAL_CliTlStandards update(BAL_CliTlStandards cliTlStandards, String dkeyId) throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_CliTlStandardsSql cliTlStandardsSql = new BAL_CliTlStandardsSql();
		BAL_CliTlStandardFormBean cliTlStandardFormBean = new BAL_CliTlStandardFormBean();
		System.out.println("Inside update DaoImpl");
		System.out.println("method Detail  :" + cliTlStandards.getMethodDetail());

		try {
			sqls.add(BAL_CliTlStandardsSql.getUpdateSql(cliTlStandardsSql.getClisDbFields(),
					cliTlStandards.getSaveArray()));
			if (cliTlStandards.getMethodDetail() != null) {
				BAL_PlmTlMultiplemethodsmstSql plmTlMultiplemethodsmstSql = new BAL_PlmTlMultiplemethodsmstSql();
				List<BAL_PlmTlMultiplemethodsmst> methodslist = cliTlStandards.getMethodDetail();
				// PlmTlMultiplemethodsmst plmTlMultiplemethodsmst =
				// (PlmTlMultiplemethodsmst)cliTlStandards.getMethodDetail();
				System.out.println("before checking duplicate values Method");
				for (BAL_PlmTlMultiplemethodsmst multipleMethod : methodslist) {
					if (!dbActionTemplate.checkDuplicateValue(
							BAL_PlmTlMultiplemethodsmstSql.TBL_PLM_TL_MULTIPLEMETHODSMST, "MLMM_REFDOCID",
							cliTlStandards.getClisKeyid(), "")) {
						multipleMethod.setMlmmKeyid(dbActionTemplate
								.getSequenceNumber(BAL_PlmTlMultiplemethodsmstSql.TBL_PLM_TL_MULTIPLEMETHODSMST));
						multipleMethod.setMlmmRefdocid(cliTlStandards.getClisKeyid());
						sqls.add(BAL_PlmTlMultiplemethodsmstSql.getInsertSql(
								plmTlMultiplemethodsmstSql.getMlmmDbFields(), multipleMethod.getSaveArray()));
						System.out.println("after checking dup insert Method");
					}

					else {
						System.out.println("after checking dup update Method");
						sqls.add(BAL_PlmTlMultiplemethodsmstSql.getUpdateSql(
								plmTlMultiplemethodsmstSql.getMlmmDbFields(), multipleMethod.getSaveArray()));
					}
				}
			}
			BAL_BdmTlYycountermeasurelink bdmTlYycountermeasureList = cliTlStandards.getCountermeasureLink();
			if (bdmTlYycountermeasureList != null) {// MLMM_REFDOCID
				CommonFunctions.debugMsg("Counter maesuDao");
				bdmTlYycountermeasureList.setYycmKeyid(dbActionTemplate.getSequenceNumber(
						BAL_BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK, 11, "YCM", "YYMM", "Y"));
				bdmTlYycountermeasureList.setYycmCountermsrid(cliTlStandards.getClisKeyid());
				String delCountrMeasr = "Delete from " + TBL_BDM_TL_YYCOUNTERMEASURELINK
						+ "  where YYCM_REFDOCTYPE = 'PMD' AND YYCM_YYID = '" + bdmTlYycountermeasureList.getYycmYyid()
						+ "'";
				sqls.add(delCountrMeasr);
				sqls.add(BAL_BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),
						bdmTlYycountermeasureList.getSaveArray()));
			}
			if (cliTlStandards.getToolsDetail() != null) {
				BAL_PlmTlToolsdtlSql plmTlToolsdtlSql = new BAL_PlmTlToolsdtlSql();
				// PlmTlToolsdtl plmTlToolsdtl =(PlmTlToolsdtl)cliTlStandards.getToolsDetail();
				List<BAL_PlmTlToolsdtl> toolsPickUplist = cliTlStandards.getToolsDetail();
				System.out.println("before checking duplicate values Tools");
				sqls.add(" DELETE FROM BAL_PLM_TL_TOOLSDTL WHERE PTLD_STANDARDID='" + cliTlStandards.getClisKeyid()
						+ "' ");
				for (BAL_PlmTlToolsdtl toolsPickUp : toolsPickUplist) {
					// if( !
					// dbActionTemplate.checkDuplicateValue(PlmTlToolsdtlSql.TBL_PLM_TL_TOOLSDTL,"PTLD_STANDARDID",cliTlStandards.getClisKeyid(),"")){
					toolsPickUp.setPtldKeyid(
							dbActionTemplate.getSequenceNumber(BAL_PlmTlToolsdtlSql.TBL_BAL_PLM_TL_TOOLSDTL));
					System.out.println("toolpickup ss" + toolsPickUp.getPtldKeyid());
					toolsPickUp.setPtldStandardid(cliTlStandards.getClisKeyid());
					sqls.add(BAL_PlmTlToolsdtlSql.getInsertSql(plmTlToolsdtlSql.getPtldDbFields(),
							toolsPickUp.getSaveArray()));
					System.out.println("after checking dup insert Tools");
					/*
					 * } else{ System.out.println("after checking dup update Tools");
					 * sqls.add(PlmTlToolsdtlSql.getUpdateSql(plmTlToolsdtlSql.getPtldDbFields(),
					 * toolsPickUp.getSaveArray())); }
					 */
				}
			}

			String cliKey = cliTlStandards.getClisKeyid();
			String updatesql = BAL_CliTlStandardsSql.updateDocUpdatesdatasql(dkeyId, cliKey);
			sqls.add(updatesql);
			dbActionTemplate.executeStatements(sqls);

			//dbActionTemplate.executeStatements(sqls);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("SQL 00" + e.getMessage());
			throw new Exception(e.getMessage());
		}

		return cliTlStandards;
	}
	
	@Override
	public void deleteImage(String clisKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception {
		BAL_CliTlStandardsSql cliTlStandardsSql = new BAL_CliTlStandardsSql();
	    String sql = " DELETE FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID='"+clisKeyid+"' AND IMFL_REFDOCTYPE='"+refDoc+"' AND IMFL_IMAGETYPE='"+imgType+"'";
	    dbActionTemplate.executeStatement(sql);
	}
	
	
//	@Override
//	public List<GenTlAllmoduleimgfile> getClitEqpImages(String mchId, String fileDir, String imagePath) throws Exception {
//
//	    List<GenTlAllmoduleimgfile> imgList = new ArrayList<GenTlAllmoduleimgfile>();
//
//	    try {
//	        String sql = "select IMFL_REFDOCTYPE, IMFL_FILENAME from GEN_TL_ALLMODULEIMGFILE "
//	                   + "where IMFL_REFKEYID = ? and IMFL_IMAGETYPE = ? "
//	                   + "and IMFL_REFDOCTYPE in ('EQ1','EQ2','EQ3')";
//
//	        List<String> paramValues = new ArrayList<String>();
//	        paramValues.add(mchId);
//	        paramValues.add("EQP");
//
//	        List<String[]> rows = dbActionTemplate.processFunctionCalls(sql, paramValues);
//
//	        if (rows != null) {
//	            for (String[] row : rows) {
//	                String docType  = row[0];
//	                String fileName = row[1];
//	                if (fileName == null || fileName.trim().isEmpty()) continue;
//
//	                CommonMessage.debugMsg("getClitEqpImages docType=" + docType + " file=" + fileName);
//
//	                String condSql = " IMFL_REFDOCTYPE = '" + docType + "' AND IMFL_IMAGETYPE = 'EQP' "
//	                               + " AND IMFL_REFKEYID = '" + mchId + "'";
//	                String fullFileName = fileDir + fileName;
//
//	                dbActionTemplate.restoreFile1(
//	                        TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql, fullFileName);
//
//	                GenTlAllmoduleimgfile imgfile = new GenTlAllmoduleimgfile();
//	                imgfile.setImflRefkeyid(mchId);
//	                imgfile.setImflRefdoctype(docType);
//	                imgfile.setImflImagetype("EQP");
//	                imgfile.setImflFilename(fileName);
//	                imgList.add(imgfile);
//	            }
//	        }
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	        throw e;
//	    }
//
//	    return imgList;
//	}
	
	@Override
	public List<GenTlAllmoduleimgfile> getClitEqpImages(String mchId, String fileDir, String imagePath) throws Exception {

	    List<GenTlAllmoduleimgfile> imgList = new ArrayList<GenTlAllmoduleimgfile>();
	    String[] refDocTypes = {"EQ1", "EQ2", "EQ3"};

	    for (String docType : refDocTypes) {
	        try {
	            String destFileName = mchId + "_" + docType + ".jpg";
	            String condSql = " AND IMFL_REFKEYID = '" + mchId + "' AND IMFL_REFDOCTYPE = '" + docType
	                            + "' AND IMFL_IMAGETYPE = 'EQP'";
	            String fullFileName = fileDir + destFileName;

	            dbActionTemplate.restoreFile1(
	                    TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql, fullFileName);

	            File restored = new File(fullFileName);
	            if (restored.exists() && restored.length() > 0) {
	                GenTlAllmoduleimgfile imgfile = new GenTlAllmoduleimgfile();
	                imgfile.setImflRefkeyid(mchId);
	                imgfile.setImflRefdoctype(docType);
	                imgfile.setImflImagetype("EQP");
	                imgfile.setImflFilename(destFileName);
	                imgList.add(imgfile);
	                CommonMessage.debugMsg("getClitEqpImages restored " + docType + " -> " + fullFileName);
	            }
	        } catch (Exception e) {
	            CommonMessage.debugMsg("getClitEqpImages: no image for " + docType + " (" + e.getMessage() + ")");
	        }
	    }

	    return imgList;
	}

	// for delete
	public String caltblCheck(String keyId) throws Exception {
		try {
			System.out.println("Inside caltblCheck : " + keyId);

			String updatesql = BAL_CliTlStandardsSql.updatejhclitfrmdatasql(keyId);
			String updateStdsql = BAL_CliTlStandardsSql.updatejhclitStddatasql(keyId);
			String updateMethodsql = BAL_PlmTlMultiplemethodsmstSql.updatejhclitmthddatasql(keyId);
			String sql = BAL_CliTlStandardsSql.getCheckcalTblSql(keyId);
			String[] calId = null;
			List<String> sqls = new ArrayList<String>();
			List<String> standardSqls = new ArrayList<String>();
			List<String> methodMstSqls = new ArrayList<String>();
			// System.out.println("caltblCheck : "+sql);
			// System.out.println("caltblUpdate : "+updatesql);
			List<String[]> keyIdList = dbActionTemplate.getDataList(sql);
			// List<String[]> UPDATEList = dbActionTemplate.getDataList(updatesql);
			calId = keyIdList.get(0);
			System.out.println("Return of data---------" + calId[0].toString().substring(0, 3));
			if (calId[0].toString().substring(0, 3).equals("CLI")) {
				System.out.println("Update");
				sqls.add(updatesql);
				dbActionTemplate.executeStatements(sqls);
				standardSqls.add(updateStdsql);
				dbActionTemplate.executeStatements(standardSqls);
				methodMstSqls.add(updateMethodsql);
				dbActionTemplate.executeStatements(methodMstSqls);

				// return "Updated"+calId[0].toString();
			}
			System.out.println("calendar table updated ");
			/*
			 * if(keyIdList.get(0).equals(null)){
			 * System.out.println("calendar table updated "); return
			 * UPDATEList.get(0).toString(); } else{
			 * System.out.println("key id available in calendar table"); return
			 * keyIdList.get(0).toString(); }
			 */

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("dao impl delete exception  :" + e);
			e.printStackTrace();
			return "NoDatas";
			// throw new Exception(e.getMessage());
			// return "NODatas";

		}
		// return keyId;
		return keyId;

	}

	/*
	 * public BAL_CliTlStandards delete(BAL_CliTlStandards cliTlStandards)throws
	 * Exception { List<String> sqls = new ArrayList<String>();
	 * BAL_CliTlStandardsSql CliTlStandardsSql = new BAL_CliTlStandardsSql();
	 * //PlmTlMultiplemethodsmstSql plmTlMultiplemethodsmstSql = new
	 * PlmTlMultiplemethodsmstSql(); try {
	 * System.out.println("Inside the DaoImpl Delete"); String calendarKey =
	 * caltblCheck(cliTlStandards.getClisKeyid());
	 * System.out.println("calendar key  :"+calendarKey); String keyId =
	 * cliTlStandards.getClisKeyid(); String sysdate =
	 * CommonFunctions.dateTimeNow(); String createdby=
	 * cliTlStandards.getClisCreatedby(); String
	 * inactivateDate=cliTlStandards.getClisInactivateddate();
	 *//***/

	/*
	 * if(calendarKey.equals("NoDatas")) {
	 * System.out.println("Key Id not matched in the tabels");
	 * //sqls.add(CliTlStandardsSql.getDeleteSql(CliTlStandardsSql.getClisDbFields()
	 * , CliTlStandards.getSaveArray())); List<BAL_PlmTlMultiplemethodsmst>
	 * plmTlMultiplemethodsmsts= cliTlStandards.getMethodDetail(); if(
	 * plmTlMultiplemethodsmsts != null && plmTlMultiplemethodsmsts.size()> 0 ) {
	 * System.out.println("Inside the detail table"); for(
	 * BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst :
	 * plmTlMultiplemethodsmsts ){
	 * plmTlMultiplemethodsmst.setMlmmRefdocid(cliTlStandards.getClisKeyid());
	 * sqls.add(BAL_PlmTlMultiplemethodsmstSql.getDeleteSql(
	 * plmTlMultiplemethodsmstSql.getMlmmDbFields(),
	 * plmTlMultiplemethodsmst.getSaveArray()));
	 * 
	 * }
	 * sqls.add(CliTlStandardsSql.getDeleteSql(CliTlStandardsSql.getClisDbFields(),
	 * cliTlStandards.getSaveArray()));
	 * System.out.println("SQL Delete  :"+sqls.get(1)); } }
	 *//***//*
			 * else{ System.out.println("Key Id not matched in the tabels");
			 * //sqls.add(CliTlStandardsSql.getDeleteSql(CliTlStandardsSql.getClisDbFields()
			 * , CliTlStandards.getSaveArray())); List<BAL_PlmTlMultiplemethodsmst>
			 * plmTlMultiplemethodsmsts= cliTlStandards.getMethodDetail();
			 * 
			 * if( plmTlMultiplemethodsmsts != null && plmTlMultiplemethodsmsts.size()> 0 )
			 * { System.out.println("Inside the detail table"); for(
			 * BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst :
			 * plmTlMultiplemethodsmsts ){
			 * plmTlMultiplemethodsmst.setMlmmRefdocid(cliTlStandards.getClisKeyid());
			 * //sqls.add(PlmTlMultiplemethodsmstSql.getDeleteSql(plmTlMultiplemethodsmstSql
			 * .getMlmmDbFields(), plmTlMultiplemethodsmst.getSaveArray()));
			 * 
			 * String
			 * updateplmmstdsql=BAL_PlmTlMultiplemethodsmstSql.inactivatejhclitmthddatasql(
			 * keyId, sysdate, createdby); sqls.add(updateplmmstdsql); } }
			 * if(cliTlStandards.getToolsDetail()!=null){ BAL_PlmTlToolsdtlSql
			 * plmTlToolsdtlSql = new BAL_PlmTlToolsdtlSql(); //PlmTlToolsdtl plmTlToolsdtl
			 * =(PlmTlToolsdtl)cliTlStandards.getToolsDetail(); List <BAL_PlmTlToolsdtl>
			 * toolsPickUplist = cliTlStandards.getToolsDetail(); for(BAL_PlmTlToolsdtl
			 * toolsPickUp:toolsPickUplist) { String
			 * updatetoldtldsql=BAL_PlmTlToolsdtlSql.inactivatejhclittooldatasql(keyId,
			 * sysdate, createdby); sqls.add(updatetoldtldsql); } }
			 * 
			 * String updateclissql= CliTlStandardsSql.inactivatejhclitstddatasql(keyId,
			 * sysdate, createdby, inactivateDate);
			 * //sqls.add(CliTlStandardsSql.getDeleteSql(CliTlStandardsSql.getClisDbFields()
			 * , cliTlStandards.getSaveArray()));
			 * System.out.println("SQL Delete  :"+sqls.get(1)); sqls.add(updateclissql);
			 * 
			 * dbActionTemplate.executeStatements(sqls); }
			 * 
			 * }catch(SQLException e){ System.out.println(e); } catch (Exception e) {
			 * e.printStackTrace(); throw new Exception(e.getMessage()); }
			 * 
			 * return cliTlStandards; List<String> sqls = new ArrayList<String>(); try {
			 * 
			 * sqls.add(CliTlStandardsSql.getDeleteSql(cliTlStandardsSql.getClisDbFields(),
			 * cliTlStandards.getSaveArray())); dbActionTemplate.executeStatements(sqls);
			 * 
			 * }catch( Exception e){ throw new Exception(e.getMessage()); } return
			 * cliTlStandards; }
			 * 
			 * 
			 * 
			 */
	public BAL_CliTlStandards delete(BAL_CliTlStandards cliTlStandards) throws Exception {
	    List<String> sqls = new ArrayList<String>();
	    BAL_CliTlStandardsSql CliTlStandardsSql = new BAL_CliTlStandardsSql();
	    try {
	        System.out.println("Inside the DaoImpl Delete");
	        String calendarKey = caltblCheck(cliTlStandards.getClisKeyid());
	        System.out.println("calendar key  :" + calendarKey);
	        String keyId         = cliTlStandards.getClisKeyid();
	        String sysdate       = CommonFunctions.dateTimeNow();
	        String createdby     = cliTlStandards.getClisCreatedby();
	        String inactivateDate = cliTlStandards.getClisInactivateddate();

	        if (calendarKey.equals("NoDatas")) {
	            // No calendar entries → hard delete method details + the standard itself
	            System.out.println("Key Id not found in calendar table — hard delete");

	            List<BAL_PlmTlMultiplemethodsmst> methods = cliTlStandards.getMethodDetail();
	            if (methods != null && methods.size() > 0) {
	                for (BAL_PlmTlMultiplemethodsmst m : methods) {
	                    m.setMlmmRefdocid(keyId);
	                    sqls.add(BAL_PlmTlMultiplemethodsmstSql.getDeleteSql(
	                            plmTlMultiplemethodsmstSql.getMlmmDbFields(), m.getSaveArray()));
	                }
	            }

	            sqls.add(CliTlStandardsSql.getDeleteSql(
	                    CliTlStandardsSql.getClisDbFields(), cliTlStandards.getSaveArray()));

	            System.out.println("Hard delete SQLs count: " + sqls.size());
	            if (!sqls.isEmpty()) {
	                dbActionTemplate.executeStatements(sqls); // ← was missing before
	            }

	        } else {
	            // Calendar entry exists → inactivate (soft delete)
	            System.out.println("Key Id found in calendar table — inactivating");

	            List<BAL_PlmTlMultiplemethodsmst> methods = cliTlStandards.getMethodDetail();
	            if (methods != null && methods.size() > 0) {
	                for (BAL_PlmTlMultiplemethodsmst m : methods) {
	                    sqls.add(BAL_PlmTlMultiplemethodsmstSql
	                            .inactivatejhclitmthddatasql(keyId, sysdate, createdby));
	                }
	            }

	            if (cliTlStandards.getToolsDetail() != null) {
	                List<BAL_PlmTlToolsdtl> tools = cliTlStandards.getToolsDetail();
	                for (BAL_PlmTlToolsdtl tool : tools) {
	                    sqls.add(BAL_PlmTlToolsdtlSql
	                            .inactivatejhclittooldatasql(keyId, sysdate, createdby));
	                }
	            }

	            String updateClsSql = CliTlStandardsSql
	                    .inactivatejhclitstddatasql(keyId, sysdate, createdby, inactivateDate);
	            sqls.add(updateClsSql);

	            System.out.println("Inactivate SQLs count: " + sqls.size());
	            System.out.println("Last SQL: " + updateClsSql);

	            if (!sqls.isEmpty()) {
	                dbActionTemplate.executeStatements(sqls);
	            }
	        }

	    } catch (SQLException e) {
	        System.out.println(e);
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception(e.getMessage());
	    }

	    return cliTlStandards;
	}
	@Override
	public List<String[]> getAlljhclitmachinearea(String mchId) {
		// TODO Auto-generated method stub
		System.out.println("inside machisne area");
		System.out.println("daoimpl  :" + mchId);
		try {
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(mchId);

			List<String[]> jhclitmchareaList = dbActionTemplate
					.processFunctionCalls(BAL_CliTlStandardsSql.getjhclitmchareaListSql(), paramValues);

			return jhclitmchareaList;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<String[]> getAlljhclitstandards(String mchId, String jhasmId) {
		// TODO Auto-generated method stub
		System.out.println("inside standard area");
		System.out.println("daoimpl  :" + mchId + "assmid  :" + jhasmId);

		System.out.println("session" + jhasmId);
		try {
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(mchId);
			paramValues.add(jhasmId);

			List<String[]> jhclitstandardList = dbActionTemplate
					.processFunctionCalls(BAL_CliTlStandardsSql.getjhclitstandardsSql(), paramValues);
			System.out.println("jhclitstandardList  :" + jhclitstandardList);
			return jhclitstandardList;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public BAL_CliTlStandards jhclitformfill(String JhClit_Id)
	// TODO Auto-generated method stub
	{
		try {
			BAL_CliTlStandards cliTlStandards = new BAL_CliTlStandards();

			String sql = BAL_CliTlStandardsSql.getjhclitfrmdatasql();

			System.out.println("DAO SQL : " + sql);
			Object[] args = new Object[] { JhClit_Id };
			cliTlStandards.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			return cliTlStandards;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	@Override
	public List<String[]> getAlljhclitcountList(List<String> paramValues) {
		// TODO Auto-generated method stub
		System.out.println("inside count daoimpl action input");
		try {
			/*
			 * List<String> paramValues = new ArrayList<String>();
			 * paramValues.add(machineIDview); paramValues.add(flag);
			 */

			List<String[]> jhclitcountList = dbActionTemplate
					.processFunctionCalls(BAL_CliTlStandardsSql.getjhclitcountListSql(), paramValues);
			System.out.println("flag item passed  :" + paramValues);
			return jhclitcountList;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<String[]> getAlljhclitcountmodifyList(List<String> paramValues) {

		// TODO Auto-generated method stub
		System.out.println("inside count daoimpl action input");
		try {
			/*
			 * paramValues.add(machineIDview); paramValues.add(flagm);
			 * paramValues.add(start); paramValues.add(end);
			 */
			System.out.println("flag  passed  :" + paramValues);
			String sql = BAL_CliTlStandardsSql.getjhclitcountListSql();
			List<String[]> jhclitcountmodifyList = dbActionTemplate.processFunctionCalls(sql, paramValues);
			System.out.println("jhclitcountmodifyList----" + jhclitcountmodifyList);
			return jhclitcountmodifyList;

		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@Override
	public List<String[]> getAllcountmodifyList(String flag, String machineIDview) {
		// TODO Auto-generated method stub
		System.out.println("inside counttotal daoimpl action input");
		try {
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(machineIDview);
			paramValues.add(flag);
			System.out.println("flag item passed  :" + flag);
			System.out.println("flag  passed  :" + machineIDview);
			String sql = BAL_CliTlStandardsSql.getjhclitcountSql();
			List<String[]> countmodifyList = dbActionTemplate.processFunctionCalls(sql, paramValues);
			return countmodifyList;

		} catch (Exception e) {
			e.getMessage();
		}

		return null;
	}

	@Override
	public List<Object> getmethodlist(String jhclitkeyID) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("methodmstKeyid in dao impl" + jhclitkeyID);
		String sql = plmTlMultiplemethodsmstSql.getmethodCategorySql(jhclitkeyID);

		BAL_PlmTlMultiplemethodsmst plmTlMultiplemethodsmst = new BAL_PlmTlMultiplemethodsmst();
		// categoryList = dbActionTemplate.getDataArr(sql, args)
		List<Object> categoryList = (List<Object>) dbActionTemplate.getDataList(sql, plmTlMultiplemethodsmst);
		return categoryList;
	}

	/*
	 * @startDate :format-"dd-MON-yyyy"
	 * 
	 * @checkForUpdate: Y or N
	 */
	public void generateCalendar(String clitDetailId, String startDate, String checkForUpdate) throws Exception {
		System.out.println("inside generate function daoImpl");
		List<String> inParamValues = new ArrayList<String>();
		inParamValues.add(clitDetailId);
		inParamValues.add(startDate);
		inParamValues.add(checkForUpdate);
		System.out.println("inParamValues  :" + inParamValues);

		Object[] outParams = new Object[1];
		System.out.println("outParams   :" + outParams);
		dbActionTemplate.processPLSQLProcedures("CLI_PR_ADDTOCLICAL", inParamValues, outParams);
//		dbActionTemplate.processPLSQLProcedures("CLI_PC_CALENDARINSERT.CLI_PR_ADDTOCLICAL",inParamValues,outParams);

		System.out.println("outParamsAfter   :" + outParams);
	}

	@Override
	public List<String[]> getAlljhclittools(String toolclisid) {
		// TODO Auto-generated method stub
		try {
			System.out.println("inside dao Impl get tools ");
			String sql = BAL_CliTlStandardsSql.gettooldata(toolclisid);
			List<String[]> addmachineList = dbActionTemplate.getDataList(sql);

			return addmachineList;

		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}

	@Override
	public Workbook clistdrptExportExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)
			throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {

			rs = getclistdrptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			return excelUtils.writeToExcel(rs, rptFormat, 0, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}
	}

	private ResultSet getclistdrptResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);

		return dbActionTemplate.dbFunctionCall("JHN_PC_CLISTANDARD.JHN_FN_GETCLISTANDARDS", paramValues);
	}

	private List<String> getFilterParamValues(CommonFilter commonFilter) {

		// String dateNow = CommonFunctions.getDate();

		/*
		 * String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter);
		 * String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		 */ // "ISTOTALCNT="+commonFilter.getViewClick()
			// +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow()
			// +";";

		List<String> paramValues = new ArrayList<String>();
		CommonFunctions.debugMsg("machine Id " + commonFilter.getMachine().getId());
		paramValues.add(commonFilter.getMachine().getId());
		paramValues.add(commonFilter.getAssembly().getId());

		return paramValues;
	}
	/*
	 * @Override public List<String[]> getAddMachine(String machineID, String
	 * getEquipmentId) { // TODO Auto-generated method stub try {
	 * System.out.println("inside dao Impl getAddMachine"); String sql =
	 * CliTlStandardsSql.getAddMachine(machineID,getEquipmentId); List<String[]>
	 * addmachineList = dbActionTemplate.getDataList(sql);
	 * 
	 * return addmachineList;
	 * 
	 * } catch (Exception e) { e.getMessage(); } return null; }
	 * 
	 * @Override public String geteqpGroup(String machineID) { // TODO
	 * Auto-generated method stub try {
	 * System.out.println("inside dao Impl getAddMachine"); String sql =
	 * CliTlStandardsSql.getEqpGrp(machineID); //String geteqpgroup =
	 * dbActionTemplate.getSingleValue(tbl_gen_tl_machinemst, sql, machineID, "");
	 * String geteqpgroup = dbActionTemplate.getSingleValue(tbl_gen_tl_machinemst,
	 * sql, machineID, null); return geteqpgroup;
	 * 
	 * } catch (Exception e) { e.getMessage(); } return null; }
	 */

	@Override
	public Workbook getClitExcel(JSONObject colmodel, String format, CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		// TODO Auto-generated method stub
		try {

			rs = getClitExcel(commonFilter);

			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs, format, 0, 0, 0);

		} finally {
			DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		}

	}

	// multiple1
	@Override
	public List<String[]> getMultipleClitList(CommonFilter commonFilter, String filePath) throws Exception {
	    try {
	        List<String> paramValues = new ArrayList<String>();

	        String machId = CommonFunctions.isValidKeyId(commonFilter.getMachineId()) ? commonFilter.getMachineId()
	                : "{}";
	        String flId = CommonFunctions.isValidKeyId(commonFilter.getFlid()) ? commonFilter.getFlid() : "{}";

	        paramValues.add(machId);
	        paramValues.add(flId);

	        List<String[]> dataList = dbActionTemplate.processFunctionCalls("jhn_fn_getmultipleclistandards",
	                paramValues);

	        // NEW: restore each row's block-diagram image to disk, same pattern as Visual SOP
	        String refdoctype = "CLI";
	        String imagetype  = "BLK";
	        String condSql = " IMFL_IMAGETYPE = '" + imagetype + "' AND IMFL_REFDOCTYPE = '" + refdoctype + "'";
	        int rIndex = 0;

	        for (String[] row : dataList) {
	            if (rIndex > 0) {
	                // column 17 = hdnClisBlockDiagImg (filename), column 0 = hdnClisKeyid (refkeyid)
	                if (row.length > 17 && CommonFunctions.isValidKeyId(row[17])) {
	                    String fileName = filePath + row[17];
	                    String condSql1 = " AND IMFL_REFKEYID = '" + row[0] + "' AND " + condSql;

	                    try {
	                        String countSql = "select count(*) from GEN_TL_ALLMODULEIMGFILE where 1=1 " + condSql1;
	                        String fileCount = dbActionTemplate.getSingleValue(countSql);

	                        if (Integer.parseInt(fileCount) > 0) {
	                            dataList.get(rIndex)[17] = fileName;
	                        } else {
	                            dataList.get(rIndex)[17] = "no-image";
	                        }

	                        dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
	                                "IMFL_BLOBIMAGE", condSql1, fileName);

	                    } catch (Exception e) {
	                        if ("NO-DATA".equals(e.getMessage())) {
	                            dataList.get(rIndex)[17] = "no-image";
	                        }
	                    }
	                }
	            }
	            rIndex++;
	        }

	        return dataList;

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }
	}

	private ResultSet getClitExcel(CommonFilter commonFilter) throws Exception {

		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(commonFilter.getFlid());
		paramValues.add(commonFilter.getMachineId());

		return dbActionTemplate.dbFunctionCall("JHN_PC_CLISTANDARD.JHN_FN_GETCLISTANDARDS", paramValues);
	}

}
