package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import org.apache.catalina.connector.Request;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.BAL_PhenCauseBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_BdmTlPhencauseDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlCausemstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhencauseSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhenomenamstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhncauselinkSql;
import com.akranta.tpm.dao.sql.BAL_GenTlAssemblymstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;

//import com.akranta.tpm.model.BdmTlPhenomenamst;
//import com.akranta.tpm.model.BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlAssemblymst;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_BdmTlPhenCauseDaoImpl implements BAL_BdmTlPhencauseDao {

	private DBActionTemplate dbActionTemplate;

	public BAL_BdmTlPhenCauseDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

// link
	// commented and added below by priyanka
	/*
	 * public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink,
	 * BAL_PhenCauseBean phenCauseBean) throws Exception {
	 * 
	 * List<String> sqls = new ArrayList<String>(); sqls for execution
	 * 
	 * BAL_BdmTlPhencauseSql bdmTlPhenCauseSql = new BAL_BdmTlPhencauseSql(); //
	 * contains dbtable,field names, Field types and related sqls of master table
	 * 
	 * //if(bdmTlPhncauselink.getBdmTlPhenomenamst()!= null &&
	 * bdmTlPhncauselink.getBdmTlPhenomenamst().size()>0) // check for detail table
	 * data
	 * 
	 * //try{
	 * 
	 * //PHENOMENA BAL_BdmTlPhenomenamst bdmTlPhenomenamst =
	 * (BAL_BdmTlPhenomenamst)bdmTlPhncauselink.getBdmTlPhenomenamst().get(0);
	 * BAL_BdmTlCausemst bdmTlCausemst =
	 * (BAL_BdmTlCausemst)bdmTlPhncauselink.getBdmTlCausemst().get(0);
	 * 
	 * String parentId= null; String elementId= null; String linkFlag = null;
	 * 
	 * System.out.println("combined id while saving:"+phenCauseBean.getCombinedId())
	 * ;
	 * 
	 * if(bdmTlPhenomenamst.getBphmKeyid() == null) { BAL_BdmTlPhenomenamstSql
	 * bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql(); linkFlag = "I";
	 * bdmTlPhenomenamst.setBphmKeyid(dbActionTemplate.getSequenceNumber(
	 * bdmTlPhenCauseSql.TBL_BAL_BDM_TL_PHENOMENAMST,8,"PHM","","")); // set the
	 * sequnce number )
	 * sqls.add(BAL_BdmTlPhenomenamstSql.getInsertSql(bdmTlPhenomenamstSql.
	 * getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for
	 * master table //sqls.add(bdmTlPhenCauseSql.getPhenInsertSql(bdmTlPhenCauseSql.
	 * getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for
	 * master table
	 * 
	 * } parentId =phenCauseBean.getCombinedId() + "-" +
	 * bdmTlPhenomenamst.getBphmAssemblyid() ; elementId = parentId + "-" +
	 * bdmTlPhenomenamst.getBphmKeyid();
	 * System.out.println("Element ID Insert: "+elementId);
	 * 
	 * bdmTlPhncauselink.setBpclOriginalid(bdmTlPhenomenamst.getBphmKeyid());
	 * bdmTlPhncauselink.setBpclParentid(parentId);
	 * bdmTlPhncauselink.setBpclElementid(elementId);
	 * if(UIUtils.isValidKeyId(bdmTlPhenomenamst.getBphmPhenomenaname()))
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlPhenomenamst.getBphmPhenomenaname()
	 * ); else { String phenName =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHENOMENAMST,
	 * "BPHM_PHENOMENANAME", "BPHM_KEYID", bdmTlPhenomenamst.getBphmKeyid());
	 * if(UIUtils.isValidKeyId(phenName))
	 * bdmTlPhncauselink.setBpclDisplaycode(phenName); }
	 * 
	 * //bdmTlPhncauselink.setBpclElementid(bdmTlPhenomenamst.getBphmAssemblyid() +
	 * "-" + bdmTlPhenomenamst.getBphmKeyid());
	 * bdmTlPhncauselink.setBpclElementtype("PHN");
	 * 
	 * String phmElementid =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK,
	 * "BPCL_ELEMENTID", "BPCL_ORIGINALID", bdmTlPhncauselink.getBpclElementid());
	 * CommonFunctions.debugMsg("Update Element Id "+phmElementid);
	 * if(UIUtils.isValidKeyId(phmElementid))//if(linkFlag.equals("I")) {
	 * //if(UIUtils.isValidKeyId(linkFlag) && linkFlag.equals("I")) //{
	 * sqls.add(bdmTlPhenCauseSql.getPclUpdateSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray()));
	 * 
	 * } else {
	 * sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table
	 * 
	 * } CommonFunctions.debugMsg("Cause Defined : "+bdmTlCausemst.
	 * getBcsmIscausedefined()); //CAUSE
	 * if(!UIUtils.isValidKeyId(bdmTlCausemst.getBcsmIscausedefined()))
	 * bdmTlCausemst.setBcsmIscausedefined("N");
	 * if(!bdmTlCausemst.getBcsmIscausedefined().equals("Y")) {
	 * bdmTlCausemst.setBcsmPhenomenaid(bdmTlPhenomenamst.getBphmKeyid());
	 * 
	 * if(bdmTlCausemst.getBcsmKeyid() == null) { linkFlag = "CI";
	 * bdmTlCausemst.setBcsmKeyid(dbActionTemplate.getSequenceNumber(
	 * bdmTlPhenCauseSql.TBL_BAL_BDM_TL_CAUSEMST,8,"CSM","","")); // set the sequnce
	 * number ) bdmTlCausemst.setBcsmCode(bdmTlCausemst.getBcsmKeyid());
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlCausemst.getBcsmName());
	 * sqls.add(bdmTlPhenCauseSql.getCauseInsertSql(bdmTlPhenCauseSql.
	 * getBcsmDbFields(), bdmTlCausemst.getSaveArray())); // add insert sql for
	 * master table }
	 * 
	 * parentId =phenCauseBean.getCombinedId() + "-" +
	 * bdmTlPhenomenamst.getBphmAssemblyid() + "-" +
	 * bdmTlPhenomenamst.getBphmKeyid() ; elementId = parentId + "-" +
	 * bdmTlCausemst.getBcsmKeyid(); System.out.println(elementId);
	 * 
	 * bdmTlPhncauselink.setBpclOriginalid(bdmTlCausemst.getBcsmKeyid());
	 * bdmTlPhncauselink.setBpclParentid(parentId);
	 * bdmTlPhncauselink.setBpclElementid(elementId);
	 * if(UIUtils.isValidKeyId(bdmTlCausemst.getBcsmName()))
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlCausemst.getBcsmName()); else {
	 * String CSName =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_CAUSEMST,
	 * "BCSM_NAME", "BCSM_KEYID", bdmTlCausemst.getBcsmKeyid());
	 * if(UIUtils.isValidKeyId(CSName))
	 * bdmTlPhncauselink.setBpclDisplaycode(CSName); }
	 * 
	 * //bdmTlPhncauselink.setBpclElementid(bdmTlPhenomenamst.getBphmKeyid() + "-" +
	 * bdmTlCausemst.getBcsmKeyid()); bdmTlPhncauselink.setBpclElementtype("CAS");
	 * 
	 * String csmElementid =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK,
	 * "BPCL_ELEMENTID", "BPCL_ORIGINALID", bdmTlCausemst.getBcsmKeyid());
	 * if(UIUtils.isValidKeyId(csmElementid))//if(linkFlag.equals("CI")) {
	 * sqls.add(bdmTlPhenCauseSql.getPclUpdateSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); //if(UIUtils.isValidKeyId(linkFlag) &&
	 * linkFlag.equals("CI")) //{
	 * 
	 * } else {
	 * sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table } }
	 * 
	 * 
	 * 
	 * dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	 * 
	 * //} catch(Exception e) { throw new Exception(e.getMessage()); } return
	 * bdmTlPhncauselink; }
	 */

	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink, BAL_PhenCauseBean phenCauseBean)
			throws Exception {

		List<String> sqls = new ArrayList<String>();

		BAL_BdmTlPhenomenamstSql phenSql = new BAL_BdmTlPhenomenamstSql();
		BAL_BdmTlPhencauseSql phenCauseSql = new BAL_BdmTlPhencauseSql();

		BAL_BdmTlPhenomenamst phen = (BAL_BdmTlPhenomenamst) bdmTlPhncauselink.getBdmTlPhenomenamst().get(0);

		if (!UIUtils.isValidKeyId(phen.getBphmKeyid())) {
			phen.setBphmKeyid(
					dbActionTemplate.getSequenceNumber(phenCauseSql.TBL_BAL_BDM_TL_PHENOMENAMST, 8, "PHM", "", ""));

			sqls.add(BAL_BdmTlPhenomenamstSql.getInsertSql(phenSql.getBphmDbFields(), phen.getSaveArray()));
		} else {
			sqls.add(phenSql.getUpdateSql(phenSql.getBphmDbFields(), phen.getSaveArray()));
		}

		dbActionTemplate.executeStatements(sqls);

		bdmTlPhncauselink.setBpclOriginalid(phen.getBphmKeyid());
		bdmTlPhncauselink.setBpclParentid(phenCauseBean.getCombinedId());
		bdmTlPhncauselink.setBpclElementid(phen.getBphmKeyid());
		bdmTlPhncauselink.setBpclDisplaycode(phen.getBphmPhenomenaname());
		bdmTlPhncauselink.setBpclElementtype("PHN");

		return bdmTlPhncauselink;
	}
	// end

	// commented and added below by priyanka
	/*
	 * public BAL_BdmTlPhncauselink update(BAL_BdmTlPhncauselink bdmTlPhncauselink,
	 * BAL_PhenCauseBean phenCauseBean) throws Exception {
	 * 
	 * List<String> sqls = new ArrayList<String>(); BAL_BdmTlPhencauseSql
	 * bdmTlPhenCauseSql = new BAL_BdmTlPhencauseSql(); // try {
	 * 
	 * String parentId = null; String elementId = null; String linkFlag = "U";
	 * BAL_BdmTlPhenomenamst bdmTlPhenomenamst = (BAL_BdmTlPhenomenamst)
	 * bdmTlPhncauselink.getBdmTlPhenomenamst() .get(0); BAL_BdmTlCausemst
	 * bdmTlCausemst = (BAL_BdmTlCausemst)
	 * bdmTlPhncauselink.getBdmTlCausemst().get(0); // comment and added by priyanka
	 * 
	 * if(bdmTlPhenomenamst.getBphmKeyid() == null) { BAL_BdmTlPhenomenamstSql
	 * bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql(); linkFlag = "I";
	 * bdmTlPhenomenamst.setBphmKeyid(dbActionTemplate.getSequenceNumber(
	 * bdmTlPhenCauseSql.TBL_BAL_BDM_TL_PHENOMENAMST,8,"PHM","","")); // set the
	 * sequnce number )
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlPhenomenamst.getBphmPhenomenaname()
	 * ); //sqls.add(bdmTlPhenCauseSql.getPhenInsertSql(bdmTlPhenCauseSql.
	 * getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for
	 * master table
	 * sqls.add(BAL_BdmTlPhenomenamstSql.getInsertSql(bdmTlPhenomenamstSql.
	 * getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for
	 * master table
	 * 
	 * }
	 * 
	 * 
	 * BAL_BdmTlPhenomenamstSql phenSql = new BAL_BdmTlPhenomenamstSql();
	 * 
	 * if (!UIUtils.isValidKeyId(bdmTlPhenomenamst.getBphmKeyid())) { // New
	 * Phenomena bdmTlPhenomenamst.setBphmKeyid(dbActionTemplate
	 * .getSequenceNumber(bdmTlPhenCauseSql.TBL_BAL_BDM_TL_PHENOMENAMST, 8, "PHM",
	 * "", ""));
	 * 
	 * sqls.add( BAL_BdmTlPhenomenamstSql.getInsertSql(phenSql.getBphmDbFields(),
	 * bdmTlPhenomenamst.getSaveArray())); } else { // Existing Phenomena
	 * sqls.add(phenSql.getUpdateSql(phenSql.getBphmDbFields(),
	 * bdmTlPhenomenamst.getSaveArray())); } // end
	 * 
	 * parentId = phenCauseBean.getCombinedId() + "-" +
	 * bdmTlPhenomenamst.getBphmAssemblyid(); elementId = parentId + "-" +
	 * bdmTlPhenomenamst.getBphmKeyid(); System.out.println("Update Elem : " +
	 * elementId);
	 * 
	 * bdmTlPhncauselink.setBpclOriginalid(bdmTlPhenomenamst.getBphmKeyid());
	 * bdmTlPhncauselink.setBpclParentid(parentId);
	 * bdmTlPhncauselink.setBpclElementid(elementId); if
	 * (UIUtils.isValidKeyId(bdmTlPhenomenamst.getBphmPhenomenaname()))
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlPhenomenamst.getBphmPhenomenaname()
	 * ); else { String phenName =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHENOMENAMST,
	 * "BPHM_PHENOMENANAME", "BPHM_KEYID", bdmTlPhenomenamst.getBphmKeyid()); if
	 * (UIUtils.isValidKeyId(phenName))
	 * bdmTlPhncauselink.setBpclDisplaycode(phenName); } //
	 * bdmTlPhncauselink.setBpclElementid(bdmTlPhenomenamst.getBphmAssemblyid() + //
	 * "-" + bdmTlPhenomenamst.getBphmKeyid());
	 * bdmTlPhncauselink.setBpclElementtype("PHN");
	 * 
	 * // changed String phmElementid by priyanka // String phmElementid = //
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK, //
	 * "BPCL_ELEMENTID", "BPCL_ELEMENTID", bdmTlPhncauselink.getBpclElementid());
	 * String phmElementid =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK,
	 * "BPCL_ELEMENTID", "BPCL_ELEMENTID", bdmTlPhncauselink.getBpclElementid()); //
	 * end CommonFunctions.debugMsg("Update Element Id " + phmElementid); if
	 * (UIUtils.isValidKeyId(phmElementid))// if(linkFlag.equals("I")) {
	 * sqls.add(bdmTlPhenCauseSql.getPclUpdateSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray()));
	 * 
	 * } else {
	 * sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table }
	 * CommonFunctions.debugMsg("Cause Defined : " +
	 * bdmTlCausemst.getBcsmIscausedefined()); // CAUSE if
	 * (!UIUtils.isValidKeyId(bdmTlCausemst.getBcsmIscausedefined()))
	 * bdmTlCausemst.setBcsmIscausedefined("N"); if
	 * (!bdmTlCausemst.getBcsmIscausedefined().equals("Y")) { // CAUSE
	 * bdmTlCausemst.setBcsmPhenomenaid(bdmTlPhenomenamst.getBphmKeyid());
	 * 
	 * // comment and added by priyanka
	 * 
	 * if(bdmTlCausemst.getBcsmKeyid() == null) { linkFlag = "CI";
	 * bdmTlCausemst.setBcsmKeyid(dbActionTemplate.getSequenceNumber(
	 * bdmTlPhenCauseSql.TBL_BAL_BDM_TL_CAUSEMST,8,"CSM","","")); // set the sequnce
	 * number ) bdmTlCausemst.setBcsmCode(bdmTlCausemst.getBcsmKeyid());
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlCausemst.getBcsmName());
	 * sqls.add(bdmTlPhenCauseSql.getCauseInsertSql(bdmTlPhenCauseSql.
	 * getBcsmDbFields(), bdmTlCausemst.getSaveArray())); // add insert sql for
	 * master table }
	 * 
	 * 
	 * BAL_BdmTlCausemstSql causeSql = new BAL_BdmTlCausemstSql();
	 * 
	 * if (!UIUtils.isValidKeyId(bdmTlCausemst.getBcsmKeyid())) {
	 * bdmTlCausemst.setBcsmKeyid(dbActionTemplate.getSequenceNumber(
	 * bdmTlPhenCauseSql.TBL_BAL_BDM_TL_CAUSEMST, 8, "CSM", "", ""));
	 * 
	 * sqls.add(causeSql.getInsertSql(causeSql.getBcsmDbFields(),
	 * bdmTlCausemst.getSaveArray())); } else {
	 * sqls.add(causeSql.getUpdateSql(causeSql.getBcsmDbFields(),
	 * bdmTlCausemst.getSaveArray())); } // end
	 * 
	 * parentId = phenCauseBean.getCombinedId() + "-" +
	 * bdmTlPhenomenamst.getBphmAssemblyid() + "-" +
	 * bdmTlPhenomenamst.getBphmKeyid(); elementId = parentId + "-" +
	 * bdmTlCausemst.getBcsmKeyid(); System.out.println(elementId);
	 * 
	 * bdmTlPhncauselink.setBpclOriginalid(bdmTlCausemst.getBcsmKeyid());
	 * bdmTlPhncauselink.setBpclParentid(parentId);
	 * bdmTlPhncauselink.setBpclElementid(elementId); if
	 * (UIUtils.isValidKeyId(bdmTlCausemst.getBcsmName()))
	 * bdmTlPhncauselink.setBpclDisplaycode(bdmTlCausemst.getBcsmName()); else {
	 * String CSName =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_CAUSEMST,
	 * "BCSM_NAME", "BCSM_KEYID", bdmTlCausemst.getBcsmKeyid()); if
	 * (UIUtils.isValidKeyId(CSName)) bdmTlPhncauselink.setBpclDisplaycode(CSName);
	 * } // bdmTlPhncauselink.setBpclElementid(bdmTlPhenomenamst.getBphmKeyid() +
	 * "-" + // bdmTlCausemst.getBcsmKeyid());
	 * bdmTlPhncauselink.setBpclElementtype("CAS");
	 * 
	 * // changed String csmElementid by priyanka // String csmElementid = //
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK, //
	 * "BPCL_ELEMENTID", "BPCL_ELEMENTID", bdmTlPhncauselink.getBpclElementid());
	 * String csmElementid =
	 * dbActionTemplate.getSingleValue(TableNames.TBL_BAL_BDM_TL_PHNCAUSELINK,
	 * "BPCL_ELEMENTID", "BPCL_ELEMENTID", elementId); // end if
	 * (UIUtils.isValidKeyId(csmElementid))// if(linkFlag.equals("CI")) {
	 * sqls.add(bdmTlPhenCauseSql.getPclUpdateSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray()));
	 * 
	 * } else {
	 * sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table } }
	 * 
	 * dbActionTemplate.executeStatements(sqls);
	 * 
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block throw new
	 * Exception(e.getMessage()); }
	 * 
	 * 
	 * return bdmTlPhncauselink; }
	 */
	public BAL_BdmTlPhncauselink update(BAL_BdmTlPhncauselink bdmTlPhncauselink,BAL_PhenCauseBean phenCauseBean) throws Exception {

	    List<String> sqls = new ArrayList<String>();

	    BAL_BdmTlPhenomenamstSql phenSql = new BAL_BdmTlPhenomenamstSql();
	    BAL_BdmTlPhencauseSql phenCauseSql = new BAL_BdmTlPhencauseSql();

	    BAL_BdmTlPhenomenamst phen =
	        (BAL_BdmTlPhenomenamst) bdmTlPhncauselink
	            .getBdmTlPhenomenamst()
	            .get(0);

	    if (!UIUtils.isValidKeyId(phen.getBphmKeyid())) {
	        phen.setBphmKeyid(dbActionTemplate.getSequenceNumber(phenCauseSql.TBL_BAL_BDM_TL_PHENOMENAMST,8,"PHM","",""));

	        sqls.add(
	            BAL_BdmTlPhenomenamstSql.getInsertSql(phenSql.getBphmDbFields(),phen.getSaveArray()));
	    } else {
	        sqls.add(phenSql.getUpdateSql(phenSql.getBphmDbFields(),phen.getSaveArray()));
	    }

	    dbActionTemplate.executeStatements(sqls);

	    bdmTlPhncauselink.setBpclOriginalid(phen.getBphmKeyid());
	    bdmTlPhncauselink.setBpclParentid(phenCauseBean.getCombinedId());
	    bdmTlPhncauselink.setBpclElementid(phen.getBphmKeyid());
	    bdmTlPhncauselink.setBpclDisplaycode(phen.getBphmPhenomenaname());
	    bdmTlPhncauselink.setBpclElementtype("PHN");

	    return bdmTlPhncauselink;
	}
	//end 

	// commented and added below by priyanka
	/*
	 * public BAL_BdmTlPhncauselink delete(BAL_BdmTlPhncauselink bdmTlPhncauselink)
	 * throws Exception {
	 * 
	 * List<String> sqls = new ArrayList<String>(); BAL_BdmTlPhencauseSql
	 * bdmTlPhenCauseSql = new BAL_BdmTlPhencauseSql(); try {
	 * sqls.add(bdmTlPhenCauseSql.getPclDeleteSql(bdmTlPhenCauseSql.getBpclDbFields(
	 * ), bdmTlPhncauselink.getSaveArray())); // delete phenomena row
	 * sqls.add("DELETE FROM BAL_BDM_TL_PHNCAUSELINK " + "WHERE BPCL_ORIGINALID = '"
	 * + bdmTlPhncauselink.getBpclParentid() + "' " +
	 * "AND BPCL_ELEMENTTYPE = 'PHN'"); dbActionTemplate.executeStatements(sqls); }
	 * catch (Exception e) { throw new Exception(e.getMessage()); } return
	 * bdmTlPhncauselink; }
	 */
	
	public BAL_BdmTlPhncauselink delete(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception {

	    List<String> sqls = new ArrayList<String>();

	    try {
	        String phenId = bdmTlPhncauselink.getBpclOriginalid();

	        sqls.add(
	            "DELETE FROM BAL_BDM_TL_PHENOMENAMST " +
	            "WHERE BPHM_KEYID = '" + phenId + "'"
	        );

	        dbActionTemplate.executeStatements(sqls);

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }

	    return bdmTlPhncauselink;
	}
	
	/*
	 * public BAL_BdmTlPhncauselink deleteCause(BAL_BdmTlPhncauselink
	 * bdmTlPhncauselink) throws Exception {
	 * 
	 * List<String> sqls = new ArrayList<String>();
	 * 
	 * try { String causeId = bdmTlPhncauselink.getBpclOriginalid();
	 * 
	 * sqls.add( "DELETE FROM bal_bdm_tl_causemst " + "WHERE bcsm_keyid = '" +
	 * causeId + "'" );
	 * 
	 * dbActionTemplate.executeStatements(sqls);
	 * 
	 * } catch (Exception e) { throw new Exception(e.getMessage()); }
	 * 
	 * return bdmTlPhncauselink; }
	 */
	
	public BAL_BdmTlPhncauselink deleteCause(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception {

	    List<String> sqls = new ArrayList<String>();

	    try {
	        String causeId = bdmTlPhncauselink.getBpclOriginalid();

	     // Hard delete the cause master itself
	        sqls.add(
	            "DELETE FROM bal_bdm_tl_causemst " +
	            "WHERE bcsm_keyid = '" + causeId + "'"
	        );

	        // If the phenomena-cause link row also needs to be deactivated,
	        // do it as a second statement (same pattern you used for SubAssembly FNLN)
	        sqls.add(
	            "UPDATE bal_bdm_tl_phncauselink " +
	            "SET bpcl_active = 'N' " +
	            "WHERE bpcl_elementid = '" + causeId + "' " +
	            "AND bpcl_elementtype = 'CSE'"
	        );

	        dbActionTemplate.executeStatements(sqls);

	    } catch (Exception e) {
	        throw new Exception(e.getMessage());
	    }

	    return bdmTlPhncauselink;
	}
	
// end 
	// Commented this by priyanka on 16/06/2026
	/*
	 * public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink
	 * bdmTlPhncauselink,List<String> pcValues) throws Exception { // TODO
	 * Auto-generated method stub String sql=""; List<String> sqls = new
	 * ArrayList<String>(); String parentField =
	 * bdmTlPhncauselink.getBpclParentid(); for(int i =0;i<pcValues.size();i++) {
	 * sql = ""; String[] pcVal = pcValues.get(i).split(","); String
	 * pcval=pcValues.get(0); if(pcval.substring(0,
	 * 3).equals("FCT")||pcval.substring(0, 3).equals("LIN")) parentField =
	 * bdmTlPhncauselink.getBpclParentid().substring(7); System.out.println(pcVal);
	 * sql += "INSERT INTO BDM_TL_PHNCAUSELINK VALUES('"+pcVal[0]+"','"; sql +=
	 * parentField+"-"+pcVal[0]+"','"+parentField+"','"; sql += pcVal[2]+"-"+
	 * pcVal[1]+"','"; sql += getElemType(pcVal[0].substring(0, 1))+"','Y')";
	 * System.out.println("Insert SQL > "+ sql); sqls.add(sql); }
	 * dbActionTemplate.executeStatements(sqls); return bdmTlPhncauselink; }
	 */

	// Added this by priyanka on 16/06/2026

	public BAL_BdmTlPhncauselink create(BAL_BdmTlPhncauselink bdmTlPhncauselink, List<String> pcValues)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		String parentField = bdmTlPhncauselink.getBpclParentid();

		// Determine parentField ONCE before the loop (based on first element)
		String pcval = pcValues.get(0);
		if (pcval.substring(0, 3).equals("FCT") || pcval.substring(0, 3).equals("LIN")) {
			parentField = bdmTlPhncauselink.getBpclParentid().substring(7);
		}

		for (int i = 0; i < pcValues.size(); i++) {
			String sql = "";
			String[] pcVal = pcValues.get(i).split(",");

			System.out.println(java.util.Arrays.toString(pcVal));

			sql += "INSERT INTO bal_bdm_tl_phncauselink (bpcl_id, bpcl_linkid, bpcl_parentid, bpcl_childid, bpcl_elemtype, bpcl_active)";
			sql += " VALUES (";
			sql += "'" + pcVal[0] + "', ";
			sql += "'" + parentField + "-" + pcVal[0] + "', ";
			sql += "'" + parentField + "', ";
			sql += "'" + pcVal[2] + "-" + pcVal[1] + "', ";
			sql += "'" + getElemType(pcVal[0].substring(0, 1)) + "', ";
			sql += "'Y')";

			System.out.println("Insert SQL > " + sql);
			sqls.add(sql);
		}

		dbActionTemplate.executeStatements(sqls);
		return bdmTlPhncauselink;
	}

	public BAL_BdmTlPhncauselink select(String UtilField) throws Exception {
		try {
			BAL_BdmTlPhncauselink bdmTlPhncauselink = new BAL_BdmTlPhncauselink();
			String sql = null;

			// sql = BdmTlPhencauseSql.selectSql().;
			System.out.println("DAO SQL : " + sql);
			// Object [] args = new Object [] { UtilField };
			// bdmTlPhncauselink.setSaveArray(dbActionTemplate.getDataArr(sql,args ) );
			return bdmTlPhncauselink;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	public List<BAL_BdmTlPhncauselink> getAllLocation(BAL_BdmTlPhncauselink bdmTlPhncauselink) throws Exception {
		try {
			String sql = BAL_BdmTlPhncauselinkSql.getPhencauseTreeSql(bdmTlPhncauselink);

			List resultList = dbActionTemplate.getDataList(sql);

			return fillPhenCauseTree(resultList);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public String getCmpFromPhnCauseLink(String searchNode) throws Exception {
		String[] searchArr = searchNode.split("-");
		String factLineFlag = searchArr[0] + '-' + searchArr[1];
		String parentId = dbActionTemplate.getSingleValue("BAL_BDM_VW_PHNCASLAYOUT", "PCT_PARENTID", "PCT_ELEMENTID",
				factLineFlag);
		String cmpId = dbActionTemplate.getSingleValue("BAL_BDM_VW_PHNCASLAYOUT", "PCT_PARENTID", "PCT_ELEMENTID",
				parentId);

		return cmpId;
		/// String sql = BdmTlPhncauselinkSql.getSearchNodeSql(searchNode,originalId);
	}

	public List<String[]> getSearchNode(String searchNode) throws Exception {
		try {
			String originalId = null;
			if (searchNode.indexOf("||") > 0) {
				originalId = searchNode.substring(searchNode.indexOf("||") + 2);
				searchNode = searchNode.substring(0, searchNode.indexOf("||"));
			}
			String sql = BAL_BdmTlPhncauselinkSql.getSearchNodeSql(searchNode, originalId);
			System.out.println("Search SQL : " + sql);
			return dbActionTemplate.getDataList(sql);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	private List<BAL_BdmTlPhncauselink> fillPhenCauseTree(List<String[]> resultList) throws SQLException {

		List<BAL_BdmTlPhncauselink> menus = new ArrayList<BAL_BdmTlPhncauselink>();
		for (String[] row : resultList) {
			BAL_BdmTlPhncauselink pcl = new BAL_BdmTlPhncauselink();
			pcl.setBpclOriginalid(row[0]);
			pcl.setBpclElementid(row[1]);
			pcl.setBpclParentid(row[2]);
			pcl.setBpclElementtype(row[3]);
			pcl.setBpclDisplaycode(row[4]);
			menus.add(pcl);
		}
		return menus;
	}

	public List<String[]> getParentElem(String elemId) throws Exception {
		String sql = BAL_BdmTlPhncauselinkSql.getParentElemSql(elemId);
		System.out.println("PARENT SQL : " + sql);
		return dbActionTemplate.getDataList(sql);
	}

	public List<String[]> getChildElem(List<String> childElem, String formField, String start, String end,
			GridParams gridParams) throws Exception {
		String sql = BAL_BdmTlPhncauselinkSql.getChildSql(childElem.size(), formField, start, end, gridParams);
		com.akranta.tpm.utils.CommonFunctions.debugMsg(" cHILD elem Sql -->" + sql);
		if (childElem.size() > 0) {
			List<String[]> dataList = dbActionTemplate.getDataList(sql, childElem);
			return dbActionTemplate.getDataList(sql, childElem);
		} else {
			return dbActionTemplate.getDataList(sql);
		}

	}

	public String getTotalCount(List<String> childElem, String formfield) throws Exception {
		com.akranta.tpm.utils.CommonFunctions.debugMsg("getTotalCount" + childElem.size());
		String sql = BAL_BdmTlPhncauselinkSql.getChildSql(childElem.size(), formfield, null, null, null);
		com.akranta.tpm.utils.CommonFunctions.debugMsg("Sql -->" + sql);
		if (childElem.size() > 0) {
			List<String[]> dataList = dbActionTemplate.getDataList(sql, childElem);
			com.akranta.tpm.utils.CommonFunctions.debugMsg("Size Of datalist : " + dataList.size());
			String returnCount = null;
			if (dataList.size() > 0)
				returnCount = dataList.get(0)[0];
			return returnCount;
		} else
			return dbActionTemplate.getSingleValue(sql);
	}

	public BAL_BdmTlPhenomenamst selectPhenomena(String keyId) throws Exception {
		// TODO Auto-generated method stub
		BAL_BdmTlPhenomenamst bdmTlPhenomenamst = new BAL_BdmTlPhenomenamst();
		String sql = BAL_BdmTlPhncauselinkSql.getPhenmstSql();

		Object args[] = new Object[] { keyId };
		bdmTlPhenomenamst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return bdmTlPhenomenamst;
	}

	public BAL_BdmTlCausemst selectCause(String keyid) throws Exception {
		BAL_BdmTlCausemst bdmTlCausemst = new BAL_BdmTlCausemst();

		String sql = BAL_BdmTlPhncauselinkSql.getCausemstSql();

		Object args[] = new Object[] { keyid };
		bdmTlCausemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return bdmTlCausemst;
	}
	

	private static String getElemType(String orgId) {
		String elemType = null;
		if (orgId.equals("P"))
			elemType = "PHN";
		if (orgId.equals("C"))
			elemType = "CAS";

		return elemType;
	}

}
