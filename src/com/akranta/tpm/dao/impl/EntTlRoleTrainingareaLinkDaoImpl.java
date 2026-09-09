package com.akranta.tpm.dao.impl;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.bean.EntTlAssessmentmstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlRoleTrainingareaLinkDao;
import com.akranta.tpm.dao.sql.AdmTlUsermstSql;
import com.akranta.tpm.dao.sql.EntTlAssessmentdtlSql;
import com.akranta.tpm.dao.sql.EntTlAssessmentmstSql;
import com.akranta.tpm.dao.sql.EntTlRoleDeptLinkmstSql;
import com.akranta.tpm.dao.sql.EntTlRoleEmpLinkSql;
import com.akranta.tpm.dao.sql.EntTlRoleTopicLinkSql;
import com.akranta.tpm.dao.sql.EntTlRoleTopicRatingSql;
import com.akranta.tpm.dao.sql.EntTlRoleTrainingareaLinkSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlAssessmentChecklist;
import com.akranta.tpm.model.EntTlAssessmentdtl;
import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.model.EntTlRoleTopicLink;
import com.akranta.tpm.model.EntTlRoleTopicRating;
import com.akranta.tpm.model.EntTlRoleTrainingareaLink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
//import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

/* dao implementation */
public class EntTlRoleTrainingareaLinkDaoImpl implements EntTlRoleTrainingareaLinkDao {


	private DBActionTemplate dbActionTemplate; 
	EntTlRoleTrainingareaLinkSql entTlRoleTrainingareaLinkSql =null;
	EntTlRoleTopicLinkSql entTlRoleTopicLinkSql = null;
	EntTlRoleTopicRatingSql entTlRoleTopicRatingSql = null;
	EntTlRoleEmpLinkSql entTlRoleEmpLinkSql;
	EntTlAssessmentmstSql entTlAssessmentmstSql = null;
	EntTlAssessmentdtlSql entTlAssessmentdtlSql = null;
	GenSequenceNumber seqRoleEmpLink = null;
	GenSequenceNumber seqAssmntMst = null;
	GenSequenceNumber seqAssement =null;
	 GenSequenceNumber seqRoleTopicLink = null;
	public EntTlRoleTrainingareaLinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlRoleTrainingareaLinkSql = new EntTlRoleTrainingareaLinkSql();
		entTlRoleTopicLinkSql  = new EntTlRoleTopicLinkSql ();
		entTlRoleTopicRatingSql  = new EntTlRoleTopicRatingSql ();
		entTlRoleEmpLinkSql = new EntTlRoleEmpLinkSql();
		entTlAssessmentmstSql = new EntTlAssessmentmstSql();
		entTlAssessmentdtlSql = new EntTlAssessmentdtlSql();
		try {
			seqRoleEmpLink = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),EntTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK, 8, "EREL",null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			seqAssmntMst = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),TableNames.TBL_ENT_TL_ASSESSMENTMST,12,"ESM",null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			seqAssement = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),TableNames.TBL_ENT_TL_ASSESSMENTDTL,12,"ESD",null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			seqRoleTopicLink = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(),TableNames.TBL_ENT_TL_ASSESSMENTDTL,12,"ESD",null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink) 	throws Exception {
		CommonMessage.debugMsg("inside create 123");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		if(! UIUtils.isValidKeyId(entTlRoleTrainingareaLink.getRtalKeyid())){
			CommonMessage.debugMsg("inside create if 123");
			entTlRoleTrainingareaLink.setRtalKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTrainingareaLinkSql.TBL_ENT_TL_ROLE_TRAININGAREA_LINK,10,"RTA",null,null));  
			sqls.add(EntTlRoleTrainingareaLinkSql.getInsertSql(entTlRoleTrainingareaLinkSql.getRtalDbFields(), entTlRoleTrainingareaLink.getSaveArray()));
			EntTlRoleTopicLink entTlRoleTopicLink = entTlRoleTrainingareaLink.getEntTlRoleTopicLink();  
			entTlRoleTopicLink.setRtlkKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK,10,"RTL",null,null));
			entTlRoleTopicLink.setRtlkRtalKeyid(entTlRoleTrainingareaLink.getRtalKeyid());
			sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields(), entTlRoleTopicLink.getSaveArray()));
			saveEntTlTopicRating(entTlRoleTopicLink,sqls);
			
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}
		else {
			update(entTlRoleTrainingareaLink);
		}
		
		return entTlRoleTrainingareaLink;
	}
	
	private void saveEntTlTopicRating(EntTlRoleTopicLink entTlRoleTopicLink, List<String> sql) throws Exception{
		CommonMessage.debugMsg("UpsDate12322132");
		List<EntTlRoleTopicRating> entTlRoleTopicRatingList = entTlRoleTopicLink.getEntTlRoleTopicRatingList();

		// 	Commented delete Sql is changed from here inside if function below 31-01-2013  --- sql.add(EntTlRoleTopicRatingSql.getDeleteWithRtrlKeyIdSql(entTlRoleTopicLink.getRtlkKeyid()));
		for(EntTlRoleTopicRating entTlRoleTopicRating: entTlRoleTopicRatingList){
			CommonMessage.debugMsg("UpsDate2" +entTlRoleTopicRating.getRtrlKeyid() );
			
			if( ! UIUtils.isValidKeyId( entTlRoleTopicRating.getRtrlKeyid()))
			{
				entTlRoleTopicRating.setRtrlKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTopicRatingSql.TBL_ENT_TL_ROLE_TOPIC_RATING,10,"RTR",null,null));
				entTlRoleTopicRating.setRtrlRtlkKeyid(entTlRoleTopicLink.getRtlkKeyid());
				
				sql.add(EntTlRoleTopicRatingSql.getInsertSql(entTlRoleTopicRatingSql.getRtrlDbFields(), entTlRoleTopicRating.getSaveArray()));
			}
			else{
				entTlRoleTopicRating.setRtrlRtlkKeyid(entTlRoleTopicLink.getRtlkKeyid());
				sql.add(EntTlRoleTopicRatingSql.getUpdateSql(entTlRoleTopicRatingSql.getRtrlDbFields(), entTlRoleTopicRating.getSaveArray()));
			}
		}
		
	}
	public EntTlRoleTrainingareaLink update(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink)	throws Exception { 
		CommonMessage.debugMsg("UpsDate");
		List<String> sqls = new ArrayList<String>();
		EntTlRoleTrainingareaLinkSql entTlRoleTrainingareaLinkSql = new EntTlRoleTrainingareaLinkSql();
		
		sqls.add(EntTlRoleTrainingareaLinkSql.getUpdateSql(entTlRoleTrainingareaLinkSql.getRtalDbFields(), entTlRoleTrainingareaLink.getSaveArray()));
		
		EntTlRoleTopicLink entTlRoleTopicLink = entTlRoleTrainingareaLink.getEntTlRoleTopicLink();
		entTlRoleTopicLink.setRtlkRtalKeyid(entTlRoleTrainingareaLink.getRtalKeyid());
		if(! UIUtils.isValidKeyId(entTlRoleTopicLink.getRtlkKeyid()))
			entTlRoleTopicLink.setRtlkKeyid(dbActionTemplate.getSingleValue("SELECT RTLK_KEYID FROM "+EntTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK+" WHERE  RTLK_RTAL_KEYID='"+entTlRoleTopicLink.getRtlkRtalKeyid()+"' AND RTLK_TOPI_KEYID = '"+entTlRoleTopicLink.getRtlkTopiKeyid()+"'"));
		CommonMessage.debugMsg("SELECT RTLK_KEYID FROM "+EntTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK+" WHERE  RTLK_RTAL_KEYID='"+entTlRoleTopicLink.getRtlkRtalKeyid()+"' AND RTLK_TOPI_KEYID = '"+entTlRoleTopicLink.getRtlkTopiKeyid()+"'");
		if( ! UIUtils.isValidKeyId(entTlRoleTopicLink.getRtlkKeyid())){
			entTlRoleTopicLink.setRtlkKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK,10,"RTL",null,null));
			
			sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields(), entTlRoleTopicLink.getSaveArray()));
		}else{
			entTlRoleTopicLink.setRtlkRtalKeyid(entTlRoleTrainingareaLink.getRtalKeyid());
			sqls.add(EntTlRoleTopicLinkSql.getUpdateSql(entTlRoleTopicLinkSql.getRtlkDbFields(), entTlRoleTopicLink.getSaveArray()));
		}
		
		saveEntTlTopicRating(entTlRoleTopicLink,sqls);
		getAssementData(entTlRoleTrainingareaLink.getRtalKeyid(),"null", entTlRoleTrainingareaLink.getRtalCreatedby(),entTlRoleTopicLink,sqls);
		dbActionTemplate.executeStatements(sqls);
			
		
		
		return entTlRoleTrainingareaLink;
	}
	
	
	public EntTlRoleTrainingareaLink delete(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlRoleTrainingareaLinkSql entTlRoleTrainingareaLinkSql = new EntTlRoleTrainingareaLinkSql();
		try {
			
			sqls.add(EntTlRoleTrainingareaLinkSql.getDeleteSql(entTlRoleTrainingareaLinkSql.getRtalDbFields(), entTlRoleTrainingareaLink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlRoleTrainingareaLink;
	}

	@Override
	public List<String[]> getevalGrdData(String trainAreaId, String topiId ,String roleId, String spokId) throws Exception {
		// TODO Auto-generated method stub
		
		String getdat = entTlRoleTrainingareaLinkSql.getRoEvalDat(trainAreaId,topiId,roleId, spokId);
		List<String[]> reslData = dbActionTemplate.getDataList(getdat);
		return reslData;
	}

	@Override
	public List<ComboBox> getSkillRatingCombo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ComboBox> getTargetRatingCombo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getRattings() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getDeptFuncLinkGrid(String condparam)
			throws Exception {
		List<String> paramValues = new ArrayList<String>();
		List<String[]> gridList;
		
		paramValues.add(condparam);
		CommonMessage.debugMsg("paramValues   :"+paramValues);
		gridList = dbActionTemplate.processFunctionCalls ("ENT_PC_EDUANDTRAINING.ENT_FN_ROLETOPICRATING", paramValues);
		
		return gridList;
	}

	@Override
	public List<String[]> getDepartmentType(String deptId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> checkLinkExists(String deptId, String cellId,
			String roleId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRattingKeyid(String orderNo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRattingLevel(String skrmKeyid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(" SELECT  EREL_RTAL_KEYID, EREL_EMPM_KEYID, EMPM_CODE || '-' ||EMPM_NAME,''AS DELTE ");
		sql1.append(" FROM " + TableNames.TBL_ENT_TL_ROLE_EMP_LINK + ", GEN_TL_EMPLOYEEMST  ");
		sql1.append(" WHERE EREL_RTAL_KEYID =  '" + ErdlKeyid + "' ");  
		sql1.append(" AND EREL_EMPM_KEYID  = EMPM_KEYID  ");
		
		sql=sql1.toString();
		CommonMessage.debugMsg("Emp grid  "+sql);
		
		return  dbActionTemplate.getDataList(sql);
	}

	@Override
	public List<String[]> getAllTrainAreaRoleView(CommonFilter commonFilter) throws Exception {
		StringBuffer sql1 = getAllTrainAreaRoleViewSql(commonFilter);
		return dbActionTemplate.getDataList(sql1.toString());
	}
	public StringBuffer getAllTrainAreaRoleViewSql(CommonFilter commonFilter)
	{
		StringBuffer sql1 = new StringBuffer();
		sql1.append(" SELECT A.* FROM ( "); 
		sql1.append(" SELECT RTAL_KEYID, PARENTNAMES ,  "); 
		sql1.append(" ROLE_NAME, COUNT(DISTINCT RTLK_TOPI_KEYID) AS TOPICCOUNT, COUNT(DISTINCT EREL_KEYID) AS EMPCOUNT  ");
		String joinTables="FROM ENT_TL_ROLE_TOPIC_LINK,ENT_TL_ROLE_TRAININGAREA_LINK,ent_tL_trainingarea, ENT_TL_ROLE_EMP_LINK,GEN_TL_ROLEMST,ENT_VW_TRAININGAREACHILDPATH ";
		String whereCond=" WHERE  RTLK_RTAL_KEYID = RTAL_KEYID AND RTAL_TRAR_KEYID = TRAR_KEYID AND KEYID =rtal_trar_keyid  AND erel_rtal_keyid(+) = rtal_keyid AND rtal_role_keyid = role_keyid";
		//Added By Dhanalakshmi.R for Filter Conditions
		if(UIUtils.isValidKeyId(commonFilter.getTrarId()))
			whereCond+=" AND trar_keyid='"+commonFilter.getTrarId()+"'";
		if(UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getEmployee())))
		{
			joinTables+=",GEN_TL_EMPLOYEEMST";
			whereCond+=" and erel_empm_keyid=empm_keyid and empm_keyid='"+FilterCondSql.getComboSelectionId(commonFilter.getEmployee())+"' + ";
		}
		if(commonFilter.getSkillType()!=null)
		{
			
		}
		if(UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getSpoke())))
		{
			
			joinTables+=",ent_tl_spokemst";
			whereCond+=" and rtlk_spok_id=spok_keyid and spok_keyid='"+FilterCondSql.getComboSelectionId(commonFilter.getSpoke())+"'";
		}
		if(UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getTopic())))
		{
			joinTables+=",ent_tl_topicmst";
			whereCond+=" and rtlk_topi_keyid=topi_keyid and topi_keyid='"+FilterCondSql.getComboSelectionId(commonFilter.getTopic())+"'";
		}
		if(UIUtils.isValidKeyId(FilterCondSql.getComboSelectionId(commonFilter.getDesignation())))
		{
			whereCond+=" and rtal_role_keyid=role_keyid and role_keyid='"+FilterCondSql.getComboSelectionId(commonFilter.getDesignation())+"'";
		}
		sql1.append(joinTables);
		sql1.append(whereCond);
		sql1.append(" GROUP BY RTAL_KEYID,PARENTNAMES,ROLE_NAME"); 
		sql1.append(" ) A ");	
		CommonMessage.debugMsg("getDeptFuncView sql="+sql1);
		return sql1;
	}
	@Override
	public EntTlRoleDeptLinkmst create(
			EntTlRoleDeptLinkmst newEntTlRoleDeptLinkmst,
			EntTlRoleSkillLink newEntTlRoleSkillLink,
			EntTlRoleSkillRating newEntTlRoleSkillRating,
			DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRoleSkill(String ErslKeyid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink newEntTlRoleEmpLink) throws Exception {
	/*	try{
		List<String> sqls = new ArrayList<String>();  sqls for execution 
		newEntTlRoleEmpLink.setErelKeyid(dbActionTemplate.getSequenceNumber(entTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK, 8, "EREL",null,null)); // set the sequnce number
		sqls.add(entTlRoleEmpLinkSql.getInsertSql(entTlRoleEmpLinkSql.getErelDbFields(), newEntTlRoleEmpLink.getSaveArray()));
		EntTlRoleTopicLink entTlRoleTopicLink = new EntTlRoleTopicLink();
		getAssementData(newEntTlRoleEmpLink.getErelRtalKeyid(),newEntTlRoleEmpLink.getErelEmpmKeyid(),newEntTlRoleEmpLink.getErelCreatedby(),entTlRoleTopicLink,sqls);
		
		dbActionTemplate.executeStatements(sqls);
		return newEntTlRoleEmpLink;
		}
		catch(BusinessApplicationExceptions e){
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}*/
		EntTlRoleTopicLink entTlRoleTopicLink = new EntTlRoleTopicLink();
		try{
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			List <EntTlRoleEmpLink> methodslist = newEntTlRoleEmpLink.getmethodEntTlRoleEmployeeLink();
			if(methodslist==null)
			{
				CommonMessage.debugMsg("method list null"); 
				newEntTlRoleEmpLink.setErelKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK, 8, "EREL",null,null)); // set the sequnce number
				sqls.add(EntTlRoleEmpLinkSql.getInsertSql(entTlRoleEmpLinkSql.getErelDbFields(), newEntTlRoleEmpLink.getSaveArray()));
				
				getAssementData(newEntTlRoleEmpLink.getErelRtalKeyid(),newEntTlRoleEmpLink.getErelEmpmKeyid(),newEntTlRoleEmpLink.getErelCreatedby(),entTlRoleTopicLink,sqls);
				
			}
			
			else if(methodslist != null && methodslist.size()>0)
			{//MLMM_REFDOCID
				 
				for(EntTlRoleEmpLink empRoleLink:methodslist)
				{
					CommonMessage.debugMsg("in dao impl");
					empRoleLink.setErelKeyid(seqRoleEmpLink.getSequnceNumber());
							//dbActionTemplate.getSequenceNumber(EntTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK, 8, "EREL",null,null));
					CommonMessage.debugMsg("EMPKEYID  = "+ empRoleLink.getErelKeyid());
				 // multipleMethod.setMlmmRefdocid(cliTlStandards.getClisKeyid());
				  sqls.add(EntTlRoleEmpLinkSql.getInsertSql(entTlRoleEmpLinkSql.getErelDbFields(), empRoleLink.getSaveArray()));
				  //EntTlRoleTopicLink entTlRoleTopicLink = new EntTlRoleTopicLink();
				  getAssementData(empRoleLink.getErelRtalKeyid(),empRoleLink.getErelEmpmKeyid(),empRoleLink.getErelCreatedby(),entTlRoleTopicLink,sqls);
				}
			}
			dbActionTemplate.executeStatements(sqls);
			return newEntTlRoleEmpLink;
			}
			catch(BusinessApplicationExceptions e){
				CommonMessage.debugMsg("Business Application   :"+e.getMessage());
				throw new BusinessApplicationExceptions(e.getMessage()); 
			}
	}

	@Override
	public String deleteErdlEmployee(String erdlKeyid, String empId,EntTlRoleEmpLink newEntTlRoleEmpLink)
			throws ValidationExceptions, Exception {
		// TODO Auto-generated method stub
		EntTlRoleEmpLink entRoleEmpLink = newEntTlRoleEmpLink;
		entRoleEmpLink.setErelEmpmKeyid(newEntTlRoleEmpLink.getErelEmpmKeyid());
		CommonMessage.debugMsg(entRoleEmpLink.getErelEmpmKeyid()+"9090909090"+newEntTlRoleEmpLink.getErelEmpmKeyid());
		entRoleEmpLink.setErelRtalKeyid(erdlKeyid);
		List<String> sqls = new ArrayList<String>();
		//EntTlRoleDeptLinkmstSql entTlRoleDeptLinkmst = new EntTlRoleDeptLinkmstSql();
		try {
			String delSql= "DELETE from " +entTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK +" where EREL_RTAL_KEYID = '"+newEntTlRoleEmpLink.getErelRtalKeyid()+"' AND EREL_EMPM_KEYID = '"+newEntTlRoleEmpLink.getErelEmpmKeyid()+"'" ;
			//sqls.add(entTlRoleEmpLinkSql.getDeleteSql(entTlRoleEmpLinkSql.getErelDbFields(), entRoleEmpLink.getSaveArray()));
			String delAsseMast = "Delete from "+TableNames.TBL_ENT_TL_ASSESSMENTMST+" where ASMM_EMPM_KEYID = '"+newEntTlRoleEmpLink.getErelEmpmKeyid()+"' AND ASMM_EVALUATION_TYPE = 'CUR'";
			String assmntMstId = dbActionTemplate.getSingleValue("select ASMM_KEYID from "+TableNames.TBL_ENT_TL_ASSESSMENTMST+" where ASMM_EMPM_KEYID = '"+newEntTlRoleEmpLink.getErelEmpmKeyid()+"' AND ASMM_EVALUATION_TYPE = 'CUR'" );
			CommonMessage.debugMsg("del  mstId "+assmntMstId);
			String delAsseDtl = "Delete from "+TableNames.TBL_ENT_TL_ASSESSMENTDTL+ " where ASMD_ASMM_KEYID  = '"+assmntMstId+"'";
			sqls.add(delAsseDtl);
			sqls.add(delAsseMast);
			sqls.add(delSql);
			dbActionTemplate.executeStatements(sqls);
			
			
		}catch( Exception e){
			
			throw new Exception(e.getMessage());
		}
		
		return "success";
	
	}

	@Override
	public EntTlRoleEmpLink create(EntTlRoleEmpLink newEntTlRoleEmpLink,
			EntTlRoleTopicLink newEntTlRoleTopicLink,
			DeptFuncLinkBean deptFuncLinkBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlRoleTrainingareaLink create(EntTlRoleTrainingareaLink entTlRoleTrainingareaLink,
			EntTlRoleTopicLink entTlRoleTopicLink,
			EntTlRoleTopicRating newEntTlRoleTopicRating) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlRoleTrainingareaLinkSql entTlRoleTrainingareaLinkSql = new EntTlRoleTrainingareaLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		EntTlRoleTopicLinkSql entTlRoleTopicLinkSql = new EntTlRoleTopicLinkSql();
		try{
		
			entTlRoleTrainingareaLink.setRtalKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTrainingareaLinkSql.TBL_ENT_TL_ROLE_TRAININGAREA_LINK, 12, "RTA", "YYMM", "Y"));
			sqls.add(EntTlRoleTrainingareaLinkSql.getInsertSql(entTlRoleTrainingareaLinkSql.getRtalDbFields(), entTlRoleTrainingareaLink.getSaveArray())); // add insert sql for master table
			
			entTlRoleTopicLink.setRtlkKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleTopicLinkSql.TBL_ENT_TL_ROLE_TOPIC_LINK, 10, "RTL", "YYMM", "Y"));
			entTlRoleTopicLink.setRtlkRtalKeyid(entTlRoleTrainingareaLink.getRtalKeyid());
			entTlRoleTopicLink.setRtlkTargetskrmKeyid("SRM02");
			
			CommonMessage.debugMsg("key   dd    "+entTlRoleTopicLink.getRtlkKeyid());
			CommonMessage.debugMsg("keydddd    "+entTlRoleTopicLink.getRtlkRtalKeyid());
			
			sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields(),entTlRoleTopicLink.getSaveArray()));
			//sqls.add(EntTlRoleTopicLinkSql.getInsertSql(entTlRoleTopicLinkSql.getRtlkDbFields(), entTlRoleTopicLink.getSaveArray())); // add insert sql for detail table
			CommonMessage.debugMsg("befr execute  ");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return entTlRoleTrainingareaLink;
	}
	public EntTlRoleTrainingareaLink getEntTlRoleTrainingareaLink(String roleTrainAreaId) throws Exception {
		EntTlRoleTrainingareaLink entTlRoleTrainingareaLink = new EntTlRoleTrainingareaLink();
		
		String sql = EntTlRoleTrainingareaLinkSql.getSelectSql();
		
		Object [] args =  new Object [] { roleTrainAreaId };
		entTlRoleTrainingareaLink.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return entTlRoleTrainingareaLink;
	}

	@Override
	public String deleteTopicRating(String rtalKeyid, String rtlkKeyid)
			throws Exception {
		// TODO Auto-generated method stub
		try{
			List<String> sqls = new ArrayList<String>();
			String delRoleLink = "delete  from "+TableNames.TBL_ENT_TL_ROLE_TOPIC_LINK+" where RTLK_KEYID = '"+rtlkKeyid+"' AND RTLK_RTAL_KEYID = '"+rtalKeyid+"'";
			String delRoleRatting ="delete from "+TableNames.TBL_ENT_TL_ROLE_TOPIC_RATING+" where RTRL_RTLK_KEYID = '"+rtlkKeyid+"' ";
			CommonMessage.debugMsg(delRoleRatting);
			CommonMessage.debugMsg(delRoleLink);
			sqls.add(delRoleRatting);
			sqls.add(delRoleLink);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			return "Success";
		}catch(Exception e){
			return "Fail";
		}
	}

	@Override
	public String getcheckmanuf(String reftype) throws Exception {
		// TODO Auto-generated method stub
		String retManuf = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_TL_TRAININGAREA,"TRAR_REFTYPE", "TRAR_KEYID", reftype);
		CommonMessage.debugMsg("reftype "+reftype+" :"+retManuf);
		return retManuf;
	}

	@Override
	public String getChildVals(String keyId) throws Exception {
		// TODO Auto-generated method stub
		String retChildVal = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH,"CHILDPATH", "KEYID", keyId);
		String retchkManuf = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH,"CLASSTYPE", "KEYID", keyId);
		retChildVal = retchkManuf+retChildVal;
		CommonMessage.debugMsg("retChildVal "+keyId+" :"+retChildVal+":::"+retchkManuf);
		return retChildVal;
	}

	@Override
	public String getdispFuncloc(String trarKeyid) throws Exception {
		// TODO Auto-generated method stub
		
		String retdispName = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH,"PARENTNAMES", "KEYID", trarKeyid);
		String retchkManuf = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH,"CLASSTYPE", "KEYID", trarKeyid);
		retdispName =retchkManuf+retdispName;
		CommonMessage.debugMsg("retChildVal "+trarKeyid+" :"+retdispName);
		return retdispName;
	}

	@Override
	public String getspokId(String topKey) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Daoimpl Getspoke  :"+topKey);
		String sql = "select distinct SPOKID from ent_vw_spoketopic where TOPICID = '"+topKey+"'";
		List<String[]> spokeId = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("sql spoke "+sql);
		CommonMessage.debugMsg(spokeId.get(0)[0]);
		//CommonMessage.debugMsg(spokeId.get(0)[2]);
		CommonMessage.debugMsg("Spoke size   :"+spokeId.size());
		String spok = null;
		if(spokeId.size()== 1)
			spok =spokeId.get(0)[0];
		return spok;
	}

	@Override
	public String getdispFunclockey(String trarKeyid) throws Exception {
		// TODO Auto-generated method stub
		String rethdnKey = dbActionTemplate.getSingleValue(TableNames.TBL_ENT_VW_TRAININGAREACHILDPATH,"CHILDPATH", "KEYID", trarKeyid);
		CommonMessage.debugMsg("retChildVal "+trarKeyid+" :"+rethdnKey);
		return rethdnKey;
	}
	private void getAssementData(String trarKeyid,String EmpId, String createdBy, EntTlRoleTopicLink entTlRoleTopicLink, List<String> sqls) throws NoDataFoundException, SQLException, Exception{
		EntTlAssessmentmst newEntTlAssessmentmst = new EntTlAssessmentmst();
		EntTlAssessmentmst oldEntTlAssessmentmst = new EntTlAssessmentmst();
		EntTlAssessmentmstBean entTlAssessmentmstBean = new EntTlAssessmentmstBean();
		
		//EntTlRoleTopicLink entTlRoleTopicLink = new EntTlRoleTopicLink();
		//EntTlRoleTopicRating entTlRoleTopicRating = new EntTlRoleTopicRating();
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("Before fetch the data  " +trarKeyid);
		CommonMessage.debugMsg(trarKeyid);
		String sql = null;
		if(UIUtils.isValidKeyId(entTlRoleTopicLink.getRtlkTopiKeyid())){	
			  
			   
		}
		else{
			 sql = entTlRoleTopicLinkSql.getTopicLinkDatawithoutTopic(trarKeyid);
			CommonMessage.debugMsg("topi id not available "+ sql);
			/*Object args [] = new Object [] {trarKeyid};
			entTlRoleTopicLink.setSaveArray(dbActionTemplate.getDataArr(sql, args));*/
	
		}
		/**RoleTopicLink Table**/
		if(UIUtils.isValidKeyId(sql)){
		
		}
		/***END OF ROLE TOPIC LINK TABLE**/
		/**RoleTopicRating Table**/
		CommonMessage.debugMsg("RtlkRtalKeyid  :"+entTlRoleTopicLink.getRtlkRtalKeyid());
		/*String tpRtngSql =entTlRoleTopicRatingSql.getTopicRatingData(entTlRoleTopicLink.getRtlkKeyid());
		List<EntTlRoleTopicRating> entTlRoleTopicRatingList = (List<EntTlRoleTopicRating>) dbActionTemplate.getDataList(tpRtngSql, entTlRoleTopicRating);*/
		/***END OF ROLE TOPIC RATING TABLE**/
		String RATINGsql = null;
		RATINGsql =" SELECT SKRM_KEYID,SKRM_MINPOINTS FROM  ENT_TL_SKILL_RATINGMST  WHERE SKRM_ORDERNO = '0' ";
		String defaultRating =  dbActionTemplate.getSingleValue(RATINGsql);
		/**GET TRAR KEYID AND ROLE ID**/
		if(!UIUtils.isValidKeyId(trarKeyid))
			trarKeyid = entTlRoleTopicLink.getRtlkRtalKeyid();
		String trarKeyId = dbActionTemplate.getSingleValue("SELECT RTAL_TRAR_KEYID FROM "+TableNames.TBL_ENT_TL_ROLE_TRAININGAREA_LINK+" WHERE RTAL_KEYID  ='"+trarKeyid+"'");
		String roleId    = dbActionTemplate.getSingleValue("SELECT RTAL_ROLE_KEYID FROM "+TableNames.TBL_ENT_TL_ROLE_TRAININGAREA_LINK+" WHERE RTAL_KEYID  ='"+trarKeyid+"'");	
		/**END **/
		//ASMM_EMPM_KEYID IN ('EMP00004','EMP00060') AND ASMM_ROLE_KEYID AND ASMM_TRAR_KEYID =
		
		String employeeId = null;
		String assemntMstrId = null;
		String assemntDtlId = null;
		
		CommonMessage.debugMsg("EmpId  "+EmpId);
		if(!UIUtils.isValidKeyId(EmpId) && UIUtils.isValidKeyId(entTlRoleTopicLink.getRtlkTopiKeyid())){//Add topic
			String EmpIdSql = "SELECT EREL_EMPM_KEYID FROM ENT_TL_ROLE_EMP_LINK where EREL_RTAL_KEYID =  '"+trarKeyid+"'";
			String empId = dbActionTemplate.getSingleValue(EmpIdSql);
			assemntMstrId = " SELECT * FROM ENT_TL_ASSESSMENTMST  WHERE  ASMM_EMPM_KEYID IN ( ";
			assemntMstrId += " SELECT EREL_EMPM_KEYID FROM ENT_TL_ROLE_EMP_LINK where "; 
			assemntMstrId += " EREL_RTAL_KEYID =  '"+trarKeyid+"' ) "; 
			assemntMstrId += " AND ASMM_ROLE_KEYID = '"+roleId+"' ";
			assemntMstrId += " AND ASMM_TRAR_KEYID = '"+trarKeyId+"' AND  ASMM_EVALUATION_TYPE ='CUR'";
			CommonMessage.debugMsg("get assemntMstrId  :"+assemntMstrId);
		//	assemntDtlId  =  "SELECT ASMD_SPOK_KEYID,ASMD_TOPI_KEYID FROM ENT_TL_ASSESSMENTDTL  WHERE  ASMD_ASMM_KEYID in( "+assemntMstrId+")";
			String spokeTOpicSql = "select RTLK_SPOK_ID,RTLK_TOPI_KEYID from ENT_TL_ROLE_topic_link where RTLK_RTAL_KEYID = 'RTA121000019'";
			if(UIUtils.isValidKeyId(empId)){
			  EntTlAssessmentmst entTlAssessmentmst = new EntTlAssessmentmst();
			  List<EntTlAssessmentmst> entTlAssessmentmstList =(List<EntTlAssessmentmst>) dbActionTemplate.getDataList(assemntMstrId, entTlAssessmentmst );
			  CommonMessage.debugMsg("ENT_TL_ASSESSMENTDTL   ::"+employeeId+"---"+assemntMstrId+"--"+entTlAssessmentmstList.size());
			  String getSpokTopi = "select RTLK_SPOK_ID, RTLK_TOPI_KEYID from ENT_TL_ROLE_topic_link where RTLK_KEYID = '"+entTlRoleTopicLink.getRtlkKeyid()+"'";
			  List<String []> spokeTopic = dbActionTemplate.getDataList(spokeTOpicSql);
			  
			  for(EntTlAssessmentmst entTlAssesmst :entTlAssessmentmstList){
					CommonMessage.debugMsg("inside dtl insert");
					CommonMessage.debugMsg("RtlkTopiKeyid  " +entTlRoleTopicLink.getRtlkTopiKeyid());
					if(spokeTopic.size()>0)
						  sqls.add("delete from ENT_TL_ASSESSMENTDTl where ASMD_ASMM_KEYID = '"+entTlAssesmst.getAsmmKeyid()+"' AND ASMD_TOPI_KEYID = '"+spokeTopic.get(0)[1]+"' AND ASMD_SPOK_KEYID = '"+spokeTopic.get(0)[0]+"'" );
					EntTlAssessmentdtl entTlAssessmentdtl = new EntTlAssessmentdtl();
				    entTlAssessmentdtl.setAsmdKeyid(seqAssement.getSequnceNumber());
					CommonMessage.debugMsg("detail  Key  "+entTlAssessmentdtl.getAsmdKeyid());
				    entTlAssessmentdtl.setAsmdCreatedby(createdBy);
				    entTlAssessmentdtl.setAsmdAsmmKeyid(entTlAssesmst.getAsmmKeyid());
					//entTlAssessmentdtl.setAsmdCutoff(roleTopicRating.getRtrlCutoff());
					//entTlAssessmentdtl.setAsmdScore(roleTopicRating.getRtrlCutoff());
					entTlAssessmentdtl.setAsmdSpokKeyid(entTlRoleTopicLink.getRtlkSpokId());
					entTlAssessmentdtl.setAsmdTopiKeyid(entTlRoleTopicLink.getRtlkTopiKeyid());
					entTlAssessmentdtl.setAsmdCurrentRate(defaultRating);
					CommonMessage.debugMsg("AsmdTopiKeyid" +entTlAssessmentdtl.getAsmdTopiKeyid());
					entTlAssessmentdtl.setAsmdActive("Y");
					entTlAssessmentdtl.setAsmdModifiedon(dateTime);
					entTlAssessmentdtl = fillValuesAssmDtl(entTlAssessmentdtl,entTlAssesmst);
					CommonMessage.debugMsg("before dtl insert");
					sqls.add(EntTlAssessmentdtlSql.getInsertSql(entTlAssessmentdtlSql.getAsmdDbFields(), entTlAssessmentdtl.getSaveArray()));
				}
			}
			 // updateAssementDtl(entTlAssessmentdtlList,entTlRoleTopicRatingList,entTlRoleTopicLink,defaultRating,sqls);
		}
		else{
			CommonMessage.debugMsg("master insert" +trarKeyId);
			CommonMessage.debugMsg("inside if EmpId is notnull ");	
			
				newEntTlAssessmentmst.setAsmmKeyid(seqAssmntMst.getSequnceNumber());
				CommonMessage.debugMsg("master Key  "+newEntTlAssessmentmst.getAsmmKeyid());
				 CommonMessage.debugMsg("trarKeyId  :"+trarKeyId+"roleId  :"+roleId);
				newEntTlAssessmentmst.setAsmmEmpmKeyid(EmpId);
				newEntTlAssessmentmst.setAsmmEvaluationDate(dateTime);
				newEntTlAssessmentmst.setAsmmEvaluationDesc("CURRENT ASSESSMENT");
				newEntTlAssessmentmst.setAsmmEvaluationType("CUR");
				newEntTlAssessmentmst.setAsmmIsLocked("Y");
				newEntTlAssessmentmst.setAsmmTrarKeyid(trarKeyId);
				newEntTlAssessmentmst.setAsmmCreatedby(createdBy);
				newEntTlAssessmentmst.setAsmmRoleKeyid(roleId);
				newEntTlAssessmentmst = fillValues(newEntTlAssessmentmst,oldEntTlAssessmentmst,entTlAssessmentmstBean);
				sqls.add(EntTlAssessmentmstSql.getInsertSql(entTlAssessmentmstSql.getAsmmDbFields(), newEntTlAssessmentmst.getSaveArray()));
				List<EntTlRoleTopicLink> entTlRoleTopicLinkList = (List<EntTlRoleTopicLink>) dbActionTemplate.getDataList(sql, entTlRoleTopicLink);
				if(entTlRoleTopicLinkList.size()>0)
				CommonMessage.debugMsg("entTlRoleTopicLinkList size :"+entTlRoleTopicLinkList.size());
				
			 for(EntTlRoleTopicLink roleTopicLink :	entTlRoleTopicLinkList){
				//for(EntTlRoleTopicRating roleTopicRating :entTlRoleTopicRatingList){
					CommonMessage.debugMsg("inside dtl insert");
					CommonMessage.debugMsg("RtlkTopiKeyid  " +roleTopicLink.getRtlkTopiKeyid());
					EntTlAssessmentdtl entTlAssessmentdtl = new EntTlAssessmentdtl();
				//    CommonMessage.debugMsg(roleTopicRating.getRtrlCriteriadesc()+"---"+ roleTopicRating.getRtrlCutoff()+"--"+ roleTopicRating.getRtrlSkrmKeyid());
				    entTlAssessmentdtl.setAsmdKeyid(seqRoleTopicLink.getSequnceNumber());
					CommonMessage.debugMsg("detail  Key  "+entTlAssessmentdtl.getAsmdKeyid());
				    entTlAssessmentdtl.setAsmdCreatedby(createdBy);
				    entTlAssessmentdtl.setAsmdAsmmKeyid(newEntTlAssessmentmst.getAsmmKeyid());
					//entTlAssessmentdtl.setAsmdCutoff(roleTopicRating.getRtrlCutoff());
					//entTlAssessmentdtl.setAsmdScore(roleTopicRating.getRtrlCutoff());
					entTlAssessmentdtl.setAsmdSpokKeyid(roleTopicLink.getRtlkSpokId());
					entTlAssessmentdtl.setAsmdTopiKeyid(roleTopicLink.getRtlkTopiKeyid());
					entTlAssessmentdtl.setAsmdCurrentRate(defaultRating);
					CommonMessage.debugMsg("AsmdTopiKeyid" +entTlAssessmentdtl.getAsmdTopiKeyid());
					entTlAssessmentdtl.setAsmdActive("Y");
					entTlAssessmentdtl.setAsmdModifiedon(dateTime);
					entTlAssessmentdtl = fillValuesAssmDtl(entTlAssessmentdtl,newEntTlAssessmentmst);
					CommonMessage.debugMsg("before dtl insert");
					sqls.add(EntTlAssessmentdtlSql.getInsertSql(entTlAssessmentdtlSql.getAsmdDbFields(), entTlAssessmentdtl.getSaveArray()));
				}
			CommonMessage.debugMsg("Before DB execute");
		 }
		//}
	
	}
	
	

	private EntTlAssessmentdtl fillValuesAssmDtl(
			EntTlAssessmentdtl entTlAssessmentdtl, EntTlAssessmentmst newEntTlAssessmentmst) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		if(entTlAssessmentdtl.getAsmdKeyid() == null ){			
			entTlAssessmentdtl.setAsmdCreatedon(dateTime);
		}
		else{
			entTlAssessmentdtl.setAsmdCreatedon(newEntTlAssessmentmst.getAsmmCreatedon());
		}
		
		if(entTlAssessmentdtl.getAsmdCutoff()== null)
			entTlAssessmentdtl.setAsmdCutoff("0");
		
		if(entTlAssessmentdtl.getAsmdScore()== null)
			entTlAssessmentdtl.setAsmdScore("0");
		
		if(entTlAssessmentdtl.getAsmdProgKeyid()== null)
		entTlAssessmentdtl.setAsmdProgKeyid("{}");
		
		if(entTlAssessmentdtl.getAsmdBachKeyid()== null)
			entTlAssessmentdtl.setAsmdBachKeyid("{}");
		
		if(entTlAssessmentdtl.getAsmdCurrentRate()== null)
			entTlAssessmentdtl.setAsmdCurrentRate("{}");
		
		if(entTlAssessmentdtl.getAsmdPreviousRate()== null)
			entTlAssessmentdtl.setAsmdPreviousRate("{}");
		
		if(entTlAssessmentdtl.getAsmdResult()== null)
			entTlAssessmentdtl.setAsmdResult("P");
		
		//if(entTlAssessmentdtl.getAsmdTempfield1() == null)
		//	entTlAssessmentdtl.setAsmdTempfield1("-");
		
		//if(entTlAssessmentdtl.getAsmdTempfield2() == null)
		//	entTlAssessmentdtl.setAsmdTempfield2("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield3() == null)
			entTlAssessmentdtl.setAsmdTempfield3("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield4() == null)
			entTlAssessmentdtl.setAsmdTempfield4("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield5() == null)
			entTlAssessmentdtl.setAsmdTempfield5("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield6() == null)
			entTlAssessmentdtl.setAsmdTempfield6("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield7() == null)
			entTlAssessmentdtl.setAsmdTempfield7("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield8() == null)
			entTlAssessmentdtl.setAsmdTempfield8("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield9() == null)
			entTlAssessmentdtl.setAsmdTempfield9("-");
		
		if(entTlAssessmentdtl.getAsmdTempfield10() == null)
			entTlAssessmentdtl.setAsmdTempfield10("-");
		return entTlAssessmentdtl;
	}

	private void updateAssementDtl(List<EntTlAssessmentdtl> entTlAssessmentdtlList,List<EntTlRoleTopicRating> entTlRoleTopicRatingList,EntTlRoleTopicLink entTlRoleTopicLink, String defaultRating, List<String> sqls) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("inside dtl update");
		for(EntTlAssessmentdtl assmntDtl : entTlAssessmentdtlList ){
			for(EntTlRoleTopicRating roleTopicRating :entTlRoleTopicRatingList){
				CommonMessage.debugMsg("inside dtl insert");
				//EntTlAssessmentdtl entTlAssessmentdtl = new EntTlAssessmentdtl();
			
				CommonMessage.debugMsg("update assemnentDtl  :"+entTlRoleTopicLink.getRtlkTopiKeyid());
			    CommonMessage.debugMsg(roleTopicRating.getRtrlCriteriadesc()+"---"+ roleTopicRating.getRtrlCutoff()+"--"+ roleTopicRating.getRtrlSkrmKeyid());
			    assmntDtl.setAsmdKeyid(assmntDtl.getAsmdKeyid());
				CommonMessage.debugMsg("detail  Key  "+assmntDtl.getAsmdKeyid());
			    assmntDtl.setAsmdCreatedby(assmntDtl.getAsmdCreatedby() );
			    assmntDtl.setAsmdAsmmKeyid(assmntDtl.getAsmdAsmmKeyid());
				//assmntDtl.setAsmdCutoff(roleTopicRating.getRtrlCutoff());
				//assmntDtl.setAsmdScore(roleTopicRating.getRtrlCutoff());
				//assmntDtl.setAsmdSpokKeyid(entTlRoleTopicLink.getRtlkSpokId());
				//assmntDtl.setAsmdTopiKeyid(entTlRoleTopicLink.getRtlkTopiKeyid());
				assmntDtl.setAsmdCurrentRate(defaultRating);
				CommonMessage.debugMsg("update AsmdTopiKeyid  :"+assmntDtl.getAsmdTopiKeyid());
				assmntDtl.setAsmdActive("Y");
				assmntDtl.setAsmdModifiedon(dateTime);
				if(assmntDtl.getAsmdKeyid() == null ){			
					assmntDtl.setAsmdCreatedon(dateTime);
				}
				else{
					assmntDtl.setAsmdCreatedon(assmntDtl.getAsmdCreatedon());
				}
				
				if(assmntDtl.getAsmdCutoff()== null)
					assmntDtl.setAsmdCutoff("0");
				
				if(assmntDtl.getAsmdScore()== null)
					assmntDtl.setAsmdScore("0");
				
				if(assmntDtl.getAsmdProgKeyid()== null)
				assmntDtl.setAsmdProgKeyid("{}");
				
				if(assmntDtl.getAsmdBachKeyid()== null)
					assmntDtl.setAsmdBachKeyid("{}");
				
				if(assmntDtl.getAsmdCurrentRate()== null)
					assmntDtl.setAsmdCurrentRate("{}");
				
				if(assmntDtl.getAsmdPreviousRate()== null)
					assmntDtl.setAsmdPreviousRate("{}");
				
				if(assmntDtl.getAsmdResult()== null)
					assmntDtl.setAsmdResult("P");
				
			//	if(assmntDtl.getAsmdTempfield1() == null)
			//		assmntDtl.setAsmdTempfield1("-");
				
			//	if(assmntDtl.getAsmdTempfield2() == null)
			//		assmntDtl.setAsmdTempfield2("-");
				
				if(assmntDtl.getAsmdTempfield3() == null)
					assmntDtl.setAsmdTempfield3("-");
				
				if(assmntDtl.getAsmdTempfield4() == null)
					assmntDtl.setAsmdTempfield4("-");
				
				if(assmntDtl.getAsmdTempfield5() == null)
					assmntDtl.setAsmdTempfield5("-");
				
				if(assmntDtl.getAsmdTempfield6() == null)
					assmntDtl.setAsmdTempfield6("-");
				
				if(assmntDtl.getAsmdTempfield7() == null)
					assmntDtl.setAsmdTempfield7("-");
				
				if(assmntDtl.getAsmdTempfield8() == null)
					assmntDtl.setAsmdTempfield8("-");
				
				if(assmntDtl.getAsmdTempfield9() == null)
					assmntDtl.setAsmdTempfield9("-");
				
				if(assmntDtl.getAsmdTempfield10() == null)
					assmntDtl.setAsmdTempfield10("-");
				
				CommonMessage.debugMsg("before dtl insert");
			 }
				sqls.add(EntTlAssessmentdtlSql.getUpdateSql(entTlAssessmentdtlSql.getAsmdDbFields(), assmntDtl.getSaveArray()));
			}
		 
	
		CommonMessage.debugMsg("END");
		
	}

	private EntTlAssessmentmst fillValues(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst oldEntTlAssessmentmst,EntTlAssessmentmstBean EntTlAssessmentmstBean) throws BusinessApplicationExceptions 
	{
			CommonMessage.debugMsg("Inside fill Values");
			newEntTlAssessmentmst.setAsmmActive("Y");
			String dateTime = CommonFunctions.dateTimeNow();		
			if(newEntTlAssessmentmst.getAsmmKeyid() == null ){	
				newEntTlAssessmentmst.setAsmmCreatedon(dateTime);
				if(newEntTlAssessmentmst.getAsmmEvaluationDate() == null)
					newEntTlAssessmentmst.setAsmmEvaluationDate(dateTime);
			}					
			else{	
				newEntTlAssessmentmst.setAsmmCreatedon(dateTime);
				if(newEntTlAssessmentmst.getAsmmEvaluationDate() == null)
					newEntTlAssessmentmst.setAsmmEvaluationDate(oldEntTlAssessmentmst.getAsmmEvaluationDate());
				CommonMessage.debugMsg("oldEntTlAssessmentmst.getAsmmIsLocked():"+oldEntTlAssessmentmst.getAsmmIsLocked());
				newEntTlAssessmentmst.setAsmmIsLocked(oldEntTlAssessmentmst.getAsmmIsLocked());		
			}
			newEntTlAssessmentmst.setAsmmModifiedon(dateTime);
			
			if(newEntTlAssessmentmst.getAsmmEvaluationDesc() == null)
				newEntTlAssessmentmst.setAsmmEvaluationDesc("{}");
			
			if(newEntTlAssessmentmst.getAsmmEvaluationNo() == null)
				newEntTlAssessmentmst.setAsmmEvaluationNo("0");
			
			if(newEntTlAssessmentmst.getAsmmEvaluationType() == null)
			newEntTlAssessmentmst.setAsmmEvaluationType("POS");
			
			if(newEntTlAssessmentmst.getAsmmTrarKeyid() == null)
				newEntTlAssessmentmst.setAsmmTrarKeyid("{}");
			
			if(newEntTlAssessmentmst.getAsmmRoleKeyid() == null)
				newEntTlAssessmentmst.setAsmmRoleKeyid("{}");
			
			if(newEntTlAssessmentmst.getAsmmEmpmKeyid() == null)
				newEntTlAssessmentmst.setAsmmEmpmKeyid("{}");
			
			if(newEntTlAssessmentmst.getAsmmIsLocked() == null)
				newEntTlAssessmentmst.setAsmmIsLocked("N");		
			
		/*	if(newEntTlAssessmentmst.getAsmmTempfield1() == null)
				newEntTlAssessmentmst.setAsmmTempfield1("-");
			
			if(newEntTlAssessmentmst.getAsmmTempfield2() == null)
				newEntTlAssessmentmst.setAsmmTempfield2("-");
			
			if(newEntTlAssessmentmst.getAsmmTempfield3() == null)
				newEntTlAssessmentmst.setAsmmTempfield3("-");
			
			if(newEntTlAssessmentmst.getAsmmTempfield4() == null)
				newEntTlAssessmentmst.setAsmmTempfield4("-");
			*/
			//if(newEntTlAssessmentmst.getAsmmTempfield5() == null)
				//newEntTlAssessmentmst.setAsmmTempfield5("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield6() == null)
				newEntTlAssessmentmst.setAsmmTempfield6("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield7() == null)
				newEntTlAssessmentmst.setAsmmTempfield7("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield8() == null)
				newEntTlAssessmentmst.setAsmmTempfield8("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield9() == null)
				newEntTlAssessmentmst.setAsmmTempfield9("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield10() == null)
				newEntTlAssessmentmst.setAsmmTempfield10("-");
			
			//newEntTlAssessmentmst.setEntTlAssessmentdtl(detailFillValues(newEntTlAssessmentmst,oldEntTlAssessmentmst,EntTlAssessmentmstBean));
			
			return newEntTlAssessmentmst;
	}
	
	
	private List<EntTlAssessmentChecklist> fillCheckListValues(EntTlAssessmentdtl newEntTlAssessmentdtl)
	{
			// TODO Auto-generated method stub
			String dateTime = CommonFunctions.dateTimeNow();	
			List<EntTlAssessmentChecklist> newEntTlAssessmentChecklist = new ArrayList<EntTlAssessmentChecklist>();
			newEntTlAssessmentChecklist=newEntTlAssessmentdtl.getEntTlAssessmentChecklist();		
			List<EntTlAssessmentChecklist> newEntTlAssessmentChecklistList = new ArrayList<EntTlAssessmentChecklist>();
			
			if (newEntTlAssessmentdtl.getEntTlAssessmentChecklist() != null
					&& newEntTlAssessmentdtl.getEntTlAssessmentChecklist().size() > 0) 
			{
				for( EntTlAssessmentChecklist entTlAssessmentChecklist :newEntTlAssessmentChecklist)
				{	
					entTlAssessmentChecklist.setAsclCreatedby(newEntTlAssessmentdtl.getAsmdCreatedby());
					
					entTlAssessmentChecklist.setAsclModifiedon(dateTime);
					if(entTlAssessmentChecklist.getAsclKeyid() == null )			
						entTlAssessmentChecklist.setAsclCreatedon(dateTime);
					else
						entTlAssessmentChecklist.setAsclCreatedon(newEntTlAssessmentdtl.getAsmdCreatedon());
					
					if(entTlAssessmentChecklist.getAsclActive()== null)
						entTlAssessmentChecklist.setAsclActive("Y");
					
					if(entTlAssessmentChecklist.getAsclTopiKeyid()== null)
						entTlAssessmentChecklist.setAsclTopiKeyid("{}");
						
					if(entTlAssessmentChecklist.getAsclChekKeyid()== null)
						entTlAssessmentChecklist.setAsclChekKeyid("{}");
					
					if(entTlAssessmentChecklist.getAsclStatus()== null)
						entTlAssessmentChecklist.setAsclStatus("P");
					
					if(entTlAssessmentChecklist.getAsclTempfield1() == null)
						entTlAssessmentChecklist.setAsclTempfield1("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield2() == null)
						entTlAssessmentChecklist.setAsclTempfield2("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield3() == null)
						entTlAssessmentChecklist.setAsclTempfield3("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield4() == null)
						entTlAssessmentChecklist.setAsclTempfield4("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield5() == null)
						entTlAssessmentChecklist.setAsclTempfield5("-");
					
					
						
					newEntTlAssessmentChecklistList.add(entTlAssessmentChecklist);	
				}
			}
		return newEntTlAssessmentChecklistList;
	}

	@Override
	public Map<Integer, List<String[]>> getViewExcel(String condparam) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		
		
		paramValues.add(condparam);
		CommonMessage.debugMsg("paramValues   :"+paramValues);
		Map<Integer, List<String[]>> excelData  = dbActionTemplate.processDbFunCallMultCursor("ENT_PC_EDUANDTRAINING.ENT_FN_TRAININGAREAEXCEL", paramValues,2);
		
		
		CommonMessage.debugMsg("excelData.size("+excelData.size());
		/*for(int i = 0; i <  excelData.size();i++ )
		{
			lists =  excelData.get(i);
		}
		CommonMessage.debugMsg("list data in dao impl:"+lists.get(1));*/
		return excelData;
	}

	@Override
	public List<String[]> getEmployeeList(String erdlKeyid,GridParams gridParams, String empFilter) throws Exception {
		// TODO Auto-generated method stub
		String[] empFltr = empFilter.split("~");
		//CommonMessage.debugMsg(empFltr[0]+" ~~~~ "+empFltr[1]+" ~~~~ "+empFltr[2]);
		String role =  null; 
		role = empFltr[0];
		String deptmnt = null; 
		deptmnt = empFltr[1] ;
		String manager = null; 
		manager = empFltr[2];
		CommonMessage.debugMsg(role+" ~~~~ "+deptmnt+" ~~~~ "+manager);
		String mgrSql =" AND  EMPM_KEYID in (select  EEMD_EMPM_KEYID from ENT_TL_EMPMANAGERDTL,ENT_TL_EMPMANAGERMST ";
		mgrSql += " where EEMD_EEMM_KEYID = EEMM_KEYID AND EEMM_MANAGER_ID= '" + manager + "') ";
		String sql=" SELECT ROWNUM AS slno, empm_keyid, '' AS hdnchkselect, empm_name || '-' || empm_code FROM gen_tl_employeemst WHERE empm_active = 'Y' "+
		" AND EMPM_KEYID NOT IN( SELECT EREL_EMPM_KEYID FROM ENT_TL_ROLE_EMP_LINK WHERE EREL_ACTIVE = 'Y') ";
		if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(manager) && UIUtils.isValidKeyId(deptmnt))
			sql +=  mgrSql + " AND  EMPM_ROLEID = '" + role + "'  AND EMPM_DEPARTMENTID = '" + deptmnt + "'" ; 
		else if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(manager))
			sql +=  mgrSql + " AND  EMPM_ROLEID = '" + role + "'" ; 
		else if(UIUtils.isValidKeyId(deptmnt) && UIUtils.isValidKeyId(manager))
			sql +=  mgrSql + " AND EMPM_DEPARTMENTID = '" + deptmnt + "'" ; 
		else if(UIUtils.isValidKeyId(role) && UIUtils.isValidKeyId(deptmnt))
			sql += " AND EMPM_DEPARTMENTID = '" + deptmnt + "' AND  EMPM_ROLEID = '" + role + "'" ; 
		else if(UIUtils.isValidKeyId(role))
			sql += " AND  EMPM_ROLEID = '" + role + "'";
		else if(CommonFunctions.isValidKeyId(deptmnt)){
			sql += " AND EMPM_DEPARTMENTID= '" + deptmnt + "'";
		}	
		else if(UIUtils.isValidKeyId(manager))
			sql += mgrSql ;
								
		String query="select count(*) from ("+sql+")";
		String count=dbActionTemplate.getSingleValue(query);
		long counts=Long.parseLong(count);
		
		gridParams.setTotalRecordCnt(counts);
		List<String> params= new ArrayList<String>();
		params.add(gridParams.getFromRow());
		
	params.add(gridParams.getToRow());
	CommonMessage.debugMsg("params:"+params);
	String sqls = " SELECT * FROM ( "+sql+" ) WHERE slno >= ? and slno <= ?";
		List<String[]> result=dbActionTemplate.getDataList(sqls,params);
		return result;
	}
	@Override
	public Workbook getEmpRoleExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception
	{
		ResultSet rs = null;
		try
		{
			rs =   getdownTimeReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			return excelUtils.writeToExcel(rs,format,0, 0,0 );
			
		 }
		finally
		 {
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	
	}
	private ResultSet getdownTimeReportResultSet(CommonFilter commonFilter) throws Exception
	{
		StringBuffer sql=getAllTrainAreaRoleViewSql(commonFilter);
		return dbActionTemplate.getData(sql.toString());
		
	}
	@Override
	public String saveErdlEmployee(String employeelist, String rtalKeyID,
			String usrm_ccno) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" employeelist "+ employeelist.substring(employeelist.length()-2,employeelist.length()-1));
		String lastBeforeValue = employeelist.substring(employeelist.length()-2,employeelist.length()-1);
		String listemployee = null ;
		if((";").equals(lastBeforeValue) )
			listemployee = employeelist.substring(1,employeelist.length()-1);
		else
			listemployee = employeelist.substring(1,employeelist.length()-1)+";";
		CommonMessage.debugMsg(" employeelist "+ employeelist);
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(rtalKeyID);
		paramValues.add(listemployee);
		paramValues.add(usrm_ccno);
		CommonMessage.debugMsg("paramValuesinsert Employee    :"+paramValues);
		/*List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		String ENTEmpSql = "ENT_PC_EDUANDTRAINING.ENT_FN_INSERTASSESSMENT";
		Object [] ENTEmpDatas = {rtalKeyID,employeelist,usrm_ccno};
		int [] ENTEmpTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,};
		sqls.add(ENTEmpSql);
		valueList.add(ENTEmpDatas);
		dataTypes.add(ENTEmpTypes);
		charList.add("P");
		
		char [] sqlType = new char[charList.size()];
		CommonMessage.debugMsg("lENGTH : "+sqlType.length);
		for(int c=0;c<sqlType.length;c++)
		{
			CommonMessage.debugMsg(c +" : "+charList.get(c));
			sqlType[c] = charList.get(c).charAt(0);
			CommonMessage.debugMsg(c +" : "+sqlType[c]);
		}
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);*/
		try{
		List<String[]> reslutsave = dbActionTemplate.processFunctionCalls ("ENT_PC_EDUANDTRAINING.ENT_FN_INSERTASSESSMENT", paramValues);
		CommonMessage.debugMsg("reslutsave Employee    :"+reslutsave.get(0).toString());
		return "Success";
		}catch(Exception e){
			return "Fail";
		}
	}

}

