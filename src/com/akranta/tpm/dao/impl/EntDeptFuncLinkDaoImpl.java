package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.DeptFuncLinkBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntDeptFuncLinkDao;
import com.akranta.tpm.dao.sql.EntTlRoleDeptLinkmstSql;
import com.akranta.tpm.dao.sql.EntTlRoleEmpLinkSql;
import com.akranta.tpm.dao.sql.EntTlRoleSkillLinkSql;
import com.akranta.tpm.dao.sql.EntTlRoleSkillRatingSql;
import com.akranta.tpm.dao.sql.PcsTlAncilliarytimeSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleEmpLink;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.model.EntTlRoleSkillRating;
import com.akranta.tpm.model.PcsTlAncilliarytime;
import com.akranta.tpm.model.PcsTlDtl;
import com.akranta.tpm.model.PcsTlLossreasonlink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EntDeptFuncLinkDaoImpl implements EntDeptFuncLinkDao {
	
	private DBActionTemplate dbActionTemplate; 
	EntTlRoleDeptLinkmstSql entTlRoleDeptLinkmstSql=null;
	EntTlRoleSkillLinkSql entTlRoleSkillLinkSql =null; 
	EntTlRoleSkillRatingSql entTlRoleSkillRatingSql=null;
	EntTlRoleEmpLinkSql entTlRoleEmpLinkSql = null;
	
	public EntDeptFuncLinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlRoleDeptLinkmstSql = new EntTlRoleDeptLinkmstSql();
		entTlRoleSkillLinkSql = new EntTlRoleSkillLinkSql();
		entTlRoleSkillRatingSql = new EntTlRoleSkillRatingSql();
		entTlRoleEmpLinkSql = new EntTlRoleEmpLinkSql();
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<ComboBox> getSkillRatingCombo() throws Exception {		
		try
		{	
			StringBuffer sql = new StringBuffer();			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			ResultSet rs = null; 
			
			sql.append("SELECT ID, TEXT FROM (");
			sql.append(" SELECT '1' AS ORD, 'A' AS ID, 'ALL' AS  TEXT FROM DUAL UNION ");
			sql.append(" SELECT '2' AS ORD, TO_CHAR(SKRM_ORDERNO) ID, TO_CHAR(SKRM_ORDERNO) TEXT FROM ENT_TL_SKILL_RATINGMST");
			sql.append(") ORDER BY ORD,TEXT");
			
			rs = dbActionTemplate.getData(sql.toString()) ;
			
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("ID"));
				compComb.setText(rs.getString("TEXT"));
				
				comboList.add(compComb);
			}
			return comboList;
		
		}
		catch(Exception e) {
			return null;
		}
	}

	
	public List<ComboBox> getTargetRatingCombo() throws Exception {		
		try
		{	
			StringBuffer sql = new StringBuffer();			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			ResultSet rs = null; 
						
			sql.append(" SELECT SKRM_ORDERNO ORDERNO FROM ENT_TL_SKILL_RATINGMST");
			sql.append(" ORDER BY SKRM_ORDERNO ");
			
			rs = dbActionTemplate.getData(sql.toString()) ;
			
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("ORDERNO"));
				compComb.setText(rs.getString("ORDERNO"));
				
				comboList.add(compComb);
			}
			return comboList;
		
		}
		catch(Exception e) {
			return null;
		}
	}	
	public List<String[]> getRattings() throws Exception {		
		try
		{	
			StringBuffer sql = new StringBuffer();			
			
			sql.append(" SELECT SKRM_ORDERNO FROM ENT_TL_SKILL_RATINGMST");
			sql.append(" ORDER BY SKRM_ORDERNO");
			
			List<String[]> rattingList = dbActionTemplate.getDataList(sql.toString());
			
			return rattingList;
		
		}
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in get Ratting dao impl"+e.getMessage());
		}
		return null;
	}	
	
	public List<String[]> getDeptFuncLinkGrid(String erdlKeyid)throws Exception {	
		try
		{				
			List<String> paramValues = new ArrayList<String>();
			List<String[]> gridList;
			
			paramValues.add(erdlKeyid);
			
			gridList = dbActionTemplate.processFunctionCalls ("ENT_PC_EDUANDTRAINING.ENT_FN_ROLESKILRATING", paramValues);
			
			return gridList;
			
		}
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getDeptFuncLinkGrid dao impl"+e.getMessage());
		}
		return null;
	}
	
	public List<String[]> getDepartmentType(String deptId)throws Exception {
		try
		{			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT DEPT_TYPE,DEPT_FACTORYID FROM " + TableNames.TBL_GEN_TL_DEPARTMENTMST + " ");
			sql.append(" WHERE DEPT_KEYID ='" + deptId + "' ");			
			List<String[]> typeList = dbActionTemplate.getDataList(sql.toString());			
			return typeList;		
		}
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getDepartmentType impl"+e.getMessage());
		}
		return null;
	}	
	
	public List<String[]> checkLinkExists(String deptId, String cellId, String roleId) throws Exception {
		try
		{			
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT ERDL_KEYID,ERDL_ELECTIVE_SKILLS FROM " + TableNames.TBL_ENT_TL_ROLE_DEPT_LINKMST + " ");
			sql.append(" WHERE ERDL_DEPT_KEYID ='" + deptId + "' ");			
			sql.append(" AND ERDL_CELL_FUN_KEYID ='" + cellId + "' ");
			sql.append(" AND ERDL_ROLE_KEYID ='" + roleId + "' ");
			
			List<String[]> linkList = dbActionTemplate.getDataList(sql.toString());
			return linkList;			
		}
		catch(Exception e) {
			CommonMessage.debugMsg("Exception in getDepartmentType impl"+e.getMessage());
		}
		return null;
	}	
	
	public String getRattingKeyid(String orderNo) throws Exception {
		StringBuffer sql = new StringBuffer();
		String keyid ;
		sql.append(" SELECT DISTINCT SKRM_KEYID FROM " + TableNames.TBL_ENT_TL_SKILL_RATINGMST + " ");
		sql.append(" WHERE SKRM_ORDERNO ='" + orderNo + "' ");
		
		keyid= dbActionTemplate.getSingleValue(sql.toString());
		
		return keyid;
	
	}
	public String getRattingLevel(String skrmKeyid) throws Exception {
		StringBuffer sql = new StringBuffer();
		String levelId ;
		sql.append(" SELECT DISTINCT SKRM_SKLM_KEYID FROM " + TableNames.TBL_ENT_TL_SKILL_RATINGMST + " ");
		sql.append(" WHERE SKRM_KEYID ='" + skrmKeyid + "' ");
		
		levelId = dbActionTemplate.getSingleValue(sql.toString());
		
		return levelId;		
	}
	
	
	public List<String[]> getEmployeeGrid(String ErdlKeyid) throws Exception {
		CommonMessage.debugMsg("Inside entry grid Dao Impl..........");
		try
		{		
			CommonMessage.debugMsg("ErdlKeyid==="+ErdlKeyid);
			
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
			sql1.append(" SELECT ROWNUM, EREL_RTAL_KEYID, EREL_EMPM_KEYID, EMPM_CODE || '-' ||EMPM_NAME  ");
			sql1.append(" FROM " + TableNames.TBL_ENT_TL_ROLE_EMP_LINK + ", GEN_TL_EMPLOYEEMST  ");
			sql1.append(" WHERE EREL_RTAL_KEYID =  '" + ErdlKeyid + "' ");  
			sql1.append(" AND EREL_EMPM_KEYID  = EMPM_KEYID  ");
			
			sql=sql1.toString();
			
			CommonMessage.debugMsg("getEmployeeGrid sql="+sql);
			List<String[]> empList = dbActionTemplate.getDataList(sql);
			return empList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getEmployeeGrid dao impl"+e.getMessage());
		}
		return null;
	}
	
	public List<String[]> getDeptFuncView(String deptId, String cellId, String roleId) throws Exception {
		CommonMessage.debugMsg("Inside entry grid Dao Impl..getDeptFuncView.......");
		try
		{		
			CommonMessage.debugMsg("deptId==="+deptId);
			
			StringBuffer sql1 = new StringBuffer();
			String sql = "";
		
		sql1.append(" SELECT ROWNUM AS SLNO, A.* FROM ( "); 
		sql1.append(" SELECT ERDL_KEYID, ERDL_FACT_KEYID, ERDL_DEPT_KEYID, DEPT_NAME, DECODE(ERDL_CELL_FUNCTION,'C','LINE','S','SECTION','FUNCTION') AS CELLCODE,  "); 
		sql1.append(" ERDL_CELL_FUN_KEYID,   NVL(DPTF_NAME,NVL(SECT_NAME,CELL_NAME)) AS CELLNAME ,   ");
		sql1.append(" ERDL_ROLE_KEYID, ROLE_NAME, ERDL_ELECTIVE_SKILLS, COUNT(DISTINCT ERSL_KEYID) AS SKILLCOUNT, COUNT(DISTINCT EREL_KEYID) AS EMPCOUNT  ");
		sql1.append(" FROM ENT_TL_ROLE_DEPT_LINKMST, GEN_TL_DEPARTMENTMST, GEN_TL_SECTIONMST, GEN_TL_CELLMST, GEN_TL_DEPTFUNCTION,  ");
		sql1.append(" GEN_TL_ROLEMST, ENT_TL_ROLE_SKILL_LINK, ENT_TL_ROLE_EMP_LINK  ");
		sql1.append(" WHERE  ERDL_DEPT_KEYID = DEPT_KEYID AND ROLE_KEYID = ERDL_ROLE_KEYID  ");
		sql1.append(" AND SECT_KEYID (+) = ERDL_CELL_FUN_KEYID AND CELL_KEYID (+) = ERDL_CELL_FUN_KEYID AND DPTF_KEYID (+) = ERDL_CELL_FUN_KEYID  ");
		sql1.append(" AND ERSL_ERDL_KEYID = ERDL_KEYID AND EREL_ERDL_KEYID (+) = ERDL_KEYID  ");
		
		sql1.append(" AND ERSL_ACTIVE = 'Y' ");
		
		if(UIUtils.isValidKeyId(deptId))
			sql1.append(" AND ERDL_DEPT_KEYID =  '" + deptId + "' ");		
		if(UIUtils.isValidKeyId(cellId))
			sql1.append(" AND ERDL_CELL_FUN_KEYID =  '" + cellId + "' ");		
		if(UIUtils.isValidKeyId(roleId))
			sql1.append(" AND ERDL_ROLE_KEYID =  '" + roleId + "' ");		
		
		sql1.append(" GROUP BY ERDL_KEYID, ERDL_FACT_KEYID, ERDL_DEPT_KEYID, DEPT_NAME, DECODE(ERDL_CELL_FUNCTION,'C','LINE','S','SECTION','FUNCTION'),  "); 
		sql1.append(" ERDL_CELL_FUN_KEYID, NVL(DPTF_NAME,NVL(SECT_NAME,CELL_NAME)), ERDL_ROLE_KEYID, ROLE_NAME ,ERDL_ELECTIVE_SKILLS ");
		sql1.append(" ) A ");
		
		sql=sql1.toString();
		
		CommonMessage.debugMsg("getDeptFuncView sql="+sql);
		List<String[]> viewList = dbActionTemplate.getDataList(sql);
		return viewList;
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getEmployeeGrid dao impl"+e.getMessage());
		}
		return null;
	}
	
	public EntTlRoleDeptLinkmst create(EntTlRoleDeptLinkmst entTlRoleDeptLinkmst,EntTlRoleSkillLink entTlRoleSkillLink,
			EntTlRoleSkillRating entTlRoleSkillRating, DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		
		List<String> sqls = new ArrayList<String>(); 		

		//entTlRoleDeptLinkmst.setErdlKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleDeptLinkmstSql.TBL_ENT_TL_ROLE_DEPT_LINKMST)); // set the sequnce number 
		String erdlKeyid=deptFuncLinkBean.getErdlKeyid();
		if (UIUtils.isValidKeyId(erdlKeyid))  {
			sqls.add(entTlRoleDeptLinkmstSql.getUpdateSql(entTlRoleDeptLinkmstSql.getErdlDbFields(), entTlRoleDeptLinkmst.getSaveArray())); // add insert sql for master table
		}
		else {
			entTlRoleDeptLinkmst.setErdlKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleDeptLinkmstSql.TBL_ENT_TL_ROLE_DEPT_LINKMST, 8, "ERDL",null,null)); // set the sequnce number
			sqls.add(entTlRoleDeptLinkmstSql.getInsertSql(entTlRoleDeptLinkmstSql.getErdlDbFields(), entTlRoleDeptLinkmst.getSaveArray())); // add insert sql for master table
		}
		
		entTlRoleSkillLink.setErslErdlKeyid(entTlRoleDeptLinkmst.getErdlKeyid());
		
		String erslKeyid=deptFuncLinkBean.getErslKeyid();
		
		//UPDATE ORDERNO
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT NVL(max(ERSL_ORDERNO),0) FROM " + TableNames.TBL_ENT_TL_ROLE_SKILL_LINK + " ");
		sql.append(" WHERE ERSL_ERDL_KEYID ='" + erdlKeyid + "' "); 
		sql.append(" AND ERSL_SKIL_KEYID ='" + entTlRoleSkillLink.getErslSkilKeyid() + "' ");
		
		String orderNO = dbActionTemplate.getSingleValue(sql.toString());
		CommonMessage.debugMsg("orderNO 22 :"+orderNO);
		int orderno = Integer.parseInt(orderNO)+1;
		orderNO =String.valueOf(orderno);
		CommonMessage.debugMsg("orderNO  :"+orderNO);
		entTlRoleSkillLink.setErslOrderno(orderNO);
		
		if (UIUtils.isValidKeyId(erslKeyid)) {			
			//sqls.add(entTlRoleSkillLinkSql.getUpdateSql(entTlRoleSkillLinkSql.getErslDbFields(), entTlRoleSkillLink.getSaveArray())); // add insert sql for master table
			String tillDate=CommonFunctions.dateTimeNow();			
			StringBuffer sql1 = new StringBuffer();
			sql1.append(" UPDATE " + TableNames.TBL_ENT_TL_ROLE_SKILL_LINK + " ");
			//sql1.append(" SET ERSL_EFF_TILL_DATE = to_date( '" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'), ");
			sql1.append(" SET ERSL_EFF_TILL_DATE = ");
			sql1.append(" DECODE(ROUND((TRUNC(TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'))-TRUNC(ERSL_EFF_FROM_DATE))) " ); 
			sql1.append(" 	,0,ERSL_EFF_FROM_DATE,TRUNC(TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'))-1) ");
			sql1.append(" , ERSL_ACTIVE ='N' ");
			sql1.append(" WHERE ERSL_KEYID ='" + erslKeyid + "' AND ERSL_ACTIVE ='Y'");
			
			sqls.add(sql1.toString());
		}
		
		entTlRoleSkillLink.setErslKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleSkillLinkSql.TBL_ENT_TL_ROLE_SKILL_LINK, 8, "ERSL",null,null)); // set the sequnce number
		sqls.add(entTlRoleSkillLinkSql.getInsertSql(entTlRoleSkillLinkSql.getErslDbFields(), entTlRoleSkillLink.getSaveArray())); // add insert sql for master table
		
		roleSkillRatingInserts(entTlRoleDeptLinkmst,entTlRoleSkillLink,deptFuncLinkBean,sqls);
		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		return entTlRoleDeptLinkmst;		
	}

	public String deleteRoleSkill(String ErslKeyid) throws Exception {
		try
		{		
			List<String> sqls = new ArrayList<String>(); 	
			StringBuffer sql1 = new StringBuffer();
			StringBuffer sql2 = new StringBuffer();
			sql1.append(" DELETE FROM " + TableNames.TBL_ENT_TL_ROLE_SKILL_RATING + " ");
			sql1.append(" WHERE ERSR_ERSL_KEYID = '" + ErslKeyid + "' ");
			
			sqls.add(sql1.toString());
			
			sql2.append(" DELETE FROM " + TableNames.TBL_ENT_TL_ROLE_SKILL_LINK + " ");
			sql2.append(" WHERE ERSL_KEYID = '" + ErslKeyid + "' ");
			
			sqls.add(sql2.toString());
			
			dbActionTemplate.executeStatements(sqls); 
			
			return "Success";
			
		}
		catch(Exception e)
		{
			CommonMessage.debugMsg("error while deleteing "+e.getMessage());
			return "fail";
		}
	}
	
	public EntTlRoleEmpLink createErdlEmployee(EntTlRoleEmpLink entTlRoleEmpLink,DeptFuncLinkBean deptFuncLinkBean) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		String erdlKeyid = deptFuncLinkBean.getErdlKeyid();
		//entTlRoleEmpLink.setErelErdlKeyid(erdlKeyid);
		entTlRoleEmpLink.setErelKeyid(dbActionTemplate.getSequenceNumber(entTlRoleEmpLinkSql.TBL_ENT_TL_ROLE_EMP_LINK, 8, "EREL",null,null)); // set the sequnce number
		sqls.add(entTlRoleEmpLinkSql.getInsertSql(entTlRoleEmpLinkSql.getErelDbFields(), entTlRoleEmpLink.getSaveArray()));
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls

		return entTlRoleEmpLink;		

	}
	public String deleteErdlEmployee(String erdlKeyid, String empId) throws ValidationExceptions, Exception {
		try
		{			
			StringBuffer sql1 = new StringBuffer();
			
			List<String> sqls = new ArrayList<String>(); 
			
			sql1.append(" DELETE FROM " + TableNames.TBL_ENT_TL_ROLE_EMP_LINK + " ");
			sql1.append(" WHERE EREL_ERDL_KEYID  = '" + erdlKeyid + "' ");
			sql1.append(" AND EREL_EMPM_KEYID   = '" + empId + "' ");
			
			sqls.add(sql1.toString());
			
			dbActionTemplate.executeStatements(sqls);
			return "Success";
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("error while deleteing "+e.getMessage());
			return "fail";
		}
	}
	
	private List<String> roleSkillRatingInserts(EntTlRoleDeptLinkmst entTlRoleDeptLinkmst,
			EntTlRoleSkillLink entTlRoleSkillLink, DeptFuncLinkBean deptFuncLinkBean, List<String> sqls) throws Exception
	{
		//CommonMessage.debugMsg("SIZE OF GRID"+entTlRoleSkillRating.getPcsTlAncilliarytimeList().size());
		if(entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList()!= null && entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().size()>=0) // check for detail table data
		{
			if (entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().size()>=0) {
				CommonMessage.debugMsg("erdl keyid in role skill"+entTlRoleDeptLinkmst.getErdlKeyid());
				//sqls.add(entTlRoleSkillRatingSql.getDeleteSql(entTlRoleDeptLinkmst., entTlRoleSkillRating.getPlrkLossid()));
			}

    		String erslKeyid=deptFuncLinkBean.getErslKeyid();
    		if (UIUtils.isValidKeyId(erslKeyid)) {			
    			//sqls.add(entTlRoleSkillLinkSql.getUpdateSql(entTlRoleSkillLinkSql.getErslDbFields(), entTlRoleSkillLink.getSaveArray())); // add insert sql for master table
    			String tillDate=CommonFunctions.dateTimeNow();			
    			StringBuffer sql = new StringBuffer();
    			sql.append(" UPDATE " + TableNames.TBL_ENT_TL_ROLE_SKILL_RATING + " ");
    			//sql.append(" SET ERSR_EFF_TILL_DATE = to_date( '" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'), ");
    			sql.append(" SET ERSR_EFF_TILL_DATE = ");
    			sql.append(" DECODE(ROUND((TRUNC(TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'))-TRUNC(ERSR_EFF_FROM_DATE))) " ); 
    			sql.append(" 	,0,ERSR_EFF_FROM_DATE,TRUNC(TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss'))-1) ");    			
    			sql.append(" ,ERSR_ACTIVE ='N' ");
    			sql.append(" WHERE ERSR_ERSL_KEYID ='" + erslKeyid + "' AND ERSR_ACTIVE ='Y'  ");	    			
    			sqls.add(sql.toString());
    		}
    		
	    	for(int i =0;i<entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().size();i++)
			{	
	    		EntTlRoleSkillRating entTlRoleSkillRating = (EntTlRoleSkillRating)entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().get(i);
	    		CommonMessage.debugMsg("entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().get(i).getErslActive()"+entTlRoleDeptLinkmst.getEntTlRoleSkillRatingList().get(i).getErsrActive());
	    		entTlRoleSkillRating.setErsrErdlKeyid(entTlRoleDeptLinkmst.getErdlKeyid());
	    		entTlRoleSkillRating.setErsrErslKeyid(entTlRoleSkillLink.getErslKeyid());
	    		entTlRoleSkillRating.setErsrSkilKeyid(entTlRoleSkillLink.getErslSkilKeyid());
	    		//pcsTlAncilliarytime.setPtatKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleSkillRatingSql.TBL_ENT_TL_ROLE_SKILL_LINK); // set the sequnce number
	    		entTlRoleSkillRating.setErsrKeyid(dbActionTemplate.getSequenceNumber(EntTlRoleSkillRatingSql.TBL_ENT_TL_ROLE_SKILL_RATING, 8, "ERSR",null,null)); // set the sequnce number
				sqls.add(EntTlRoleSkillRatingSql.getInsertSql(entTlRoleSkillRatingSql.getErsrDbFields(), entTlRoleSkillRating.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}		
}
