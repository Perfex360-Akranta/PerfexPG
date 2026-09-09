package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlProgTargetSkillsDao;
import com.akranta.tpm.dao.sql.EntTlProgImpactSkillsSql;
import com.akranta.tpm.dao.sql.EntTlProgTargetSkillsSql;
import com.akranta.tpm.dao.sql.EntTlProgrammstSql;
import com.akranta.tpm.dao.sql.EntTlRoleSkillRatingSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.EntTlProgImpactSkills;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.model.EntTlProgrammst;
import com.akranta.tpm.model.EntTlRoleDeptLinkmst;
import com.akranta.tpm.model.EntTlRoleSkillLink;
import com.akranta.tpm.model.EntTlRoleSkillRating;
//import com.akranta.tpm.model.PlmTlToolsdtl;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlProgTargetSkillsDaoImpl implements EntTlProgTargetSkillsDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlProgTargetSkillsDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlProgTargetSkills create(EntTlProgTargetSkills entTlProgTargetSkills) 	throws BusinessApplicationExceptions,Exception {
		CommonMessage.debugMsg("inside DaoIMpl skills");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlProgTargetSkillsSql entTlProgTargetSkillsSql = new EntTlProgTargetSkillsSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			CommonMessage.debugMsg("inside try");
			//String chkskill = "SELECT PRTS_TOPI_KEYID FROM ENT_TL_PROG_TARGET_SKILLS WHERE PRTS_TOPI_KEYID = '"+entTlProgTargetSkills.getPrtsTopiKeyid()+ "' AND PRTS_ACTIVE = 'Y'";
			String chkskill = "SELECT PRTS_TOPI_KEYID FROM ENT_TL_PROG_TARGET_SKILLS WHERE PRTS_TOPI_KEYID = '"+entTlProgTargetSkills.getPrtsTopiKeyid()+ "'AND PRTS_SPOKE_KEYID = '"+entTlProgTargetSkills.getPrtsSpokeKeyid()+ "'  AND PRTS_KEYID = '"+entTlProgTargetSkills.getPrtsKeyid()+ "' AND PRTS_ACTIVE = 'Y'";
			CommonMessage.debugMsg("topic Exists ----"+chkskill);
			String skillKey = dbActionTemplate.getSingleValue(chkskill);
			CommonMessage.debugMsg("skillKey----"+skillKey);
			//if(!UIUtils.isValidKeyId(skillKey)){ --Templorarly commented
			//String skillKey = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_SKIL_KEYID", "PRTS_SKIL_KEYID", entTlProgTargetSkills.getPrtsTopiKeyid());
			String orderNO = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "NVL(max(PRTS_ORDERNO),0)", "PRTS_PROG_KEYID", entTlProgTargetSkills.getPrtsProgKeyid());
			CommonMessage.debugMsg("orderNO 22 :"+orderNO);
			int orderno = Integer.parseInt(orderNO)+1;
			orderNO =String.valueOf(orderno);
			CommonMessage.debugMsg("orderNO  :"+orderNO);
			CommonMessage.debugMsg("Skill "+skillKey+"  from from  "+entTlProgTargetSkills.getPrtsTopiKeyid());
			entTlProgTargetSkills.setPrtsOrderno(orderNO);
			CommonMessage.debugMsg("Skill "+skillKey+"  from from  "+entTlProgTargetSkills.getPrtsTopiKeyid());
//			update Skill dtl
			String prtsKeyid=entTlProgTargetSkills.getPrtsKeyid();
			CommonMessage.debugMsg("prtsKeyid  :" + prtsKeyid);
			String topiId = null;
			if (UIUtils.isValidKeyId(prtsKeyid)) {			
				 topiId = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_TOPI_KEYID", "PRTS_KEYID", prtsKeyid);
				//sqls.add(entTlRoleSkillLinkSql.getUpdateSql(entTlRoleSkillLinkSql.getErslDbFields(), entTlRoleSkillLink.getSaveArray())); // add insert sql for master table
				String tillDate=CommonFunctions.dateTimeNow();			
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE " + TableNames.TBL_ENT_TL_PROG_TARGET_SKILLS + " ");
				sql.append(" SET PRTS_EFF_TILL_DATE = DECODE(FLOOR((TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss')-PRTS_EFF_FROM_DATE)), ");
				sql.append("0,PRTS_EFF_FROM_DATE,TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss')-1) ");    			
				sql.append(" ,PRTS_ACTIVE ='N' ");
				sql.append(" WHERE PRTS_KEYID ='" + prtsKeyid + "' AND PRTS_ACTIVE ='Y'  ");	    			
				sqls.add(sql.toString());
			}
//			End
			
				CommonMessage.debugMsg("Skill avai not");
				 
				CommonMessage.debugMsg("inside Before Inserted skills");
				
				if(entTlProgTargetSkills.getPrtsTopiKeyid().equals(skillKey)){
					CommonMessage.debugMsg("Topic  available "+skillKey);
					sqls.add(EntTlProgTargetSkillsSql.getUpdateSql(entTlProgTargetSkillsSql.getPrtsDbFields(), entTlProgTargetSkills.getSaveArray())); // add insert sql for master table
				}
				else{
				 entTlProgTargetSkills.setPrtsKeyid(dbActionTemplate.getSequenceNumber(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, 10, "PRT", "MMYY", "Y")); // set the sequnce number
				 sqls.add(EntTlProgTargetSkillsSql.getInsertSql(entTlProgTargetSkillsSql.getPrtsDbFields(), entTlProgTargetSkills.getSaveArray())); // add insert sql for master table
				}
				CommonMessage.debugMsg("inside Inserted skills");
				roleSkillRatingInserts(	entTlProgTargetSkills,sqls);
			/*}
			else{
				throw new BusinessApplicationExceptions("Skill Already Exist For Program");
			}*/
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}catch(BusinessApplicationExceptions e)
		{
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlProgTargetSkills;
	}
	
	
	

	public EntTlProgTargetSkills update(EntTlProgTargetSkills entTlProgTargetSkills)	throws BusinessApplicationExceptions,Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlProgTargetSkillsSql entTlProgTargetSkillsSql = new EntTlProgTargetSkillsSql();
		try {
			String skillKey = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_SKIL_KEYID", "PRTS_SKIL_KEYID", entTlProgTargetSkills.getPrtsTopiKeyid());
			String orderNO = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "NVL(max(PRTS_ORDERNO),0)", "PRTS_PROG_KEYID", entTlProgTargetSkills.getPrtsProgKeyid());
			String getCreatedOn = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_CREATEDON", "PRTS_SKIL_KEYID", entTlProgTargetSkills.getPrtsTopiKeyid());
			CommonMessage.debugMsg("orderNO 22 :"+orderNO);
			entTlProgTargetSkills.setPrtsCreatedon(getCreatedOn);
			int orderno = Integer.parseInt(orderNO)+1;
			orderNO =String.valueOf(orderno);
			CommonMessage.debugMsg("orderNO  :"+orderNO);
			CommonMessage.debugMsg("Skill "+skillKey+"  from from  "+entTlProgTargetSkills.getPrtsTopiKeyid());
			entTlProgTargetSkills.setPrtsOrderno(orderNO);
			
			if(!UIUtils.isValidKeyId(skillKey)){
				CommonMessage.debugMsg("Skill avai not");
			sqls.add(EntTlProgTargetSkillsSql.getUpdateSql(entTlProgTargetSkillsSql.getPrtsDbFields(), entTlProgTargetSkills.getSaveArray()));
			roleSkillRatingInserts(	entTlProgTargetSkills,sqls);
			}
			else{
				throw new BusinessApplicationExceptions("Skill Already Exist For Program");
			}
			dbActionTemplate.executeStatements(sqls);
			
		} catch(BusinessApplicationExceptions e)
		{
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlProgTargetSkills;
	}
	
	public EntTlProgTargetSkills delete(EntTlProgTargetSkills entTlProgTargetSkills)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlProgTargetSkillsSql entTlProgTargetSkillsSql = new EntTlProgTargetSkillsSql();
		try {
			
			sqls.add(EntTlProgTargetSkillsSql.getDeleteSql(entTlProgTargetSkillsSql.getPrtsDbFields(), entTlProgTargetSkills.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlProgTargetSkills;
	}
 //*Detail Save*/
	private List<String> roleSkillRatingInserts(EntTlProgTargetSkills entTlProgTargetSkills, List<String> sqls)throws Exception { 
	{
		//CommonMessage.debugMsg("SIZE OF GRID"+entTlRoleSkillRating.getPcsTlAncilliarytimeList().size());
		EntTlProgImpactSkillsSql entTlProgImpactSkillsSql = new EntTlProgImpactSkillsSql();
//		update Skill dtl
		String prtsKeyid=entTlProgTargetSkills.getPrtsKeyid();
		String chkskill = "SELECT PRTS_TOPI_KEYID FROM ENT_TL_PROG_TARGET_SKILLS WHERE PRTS_TOPI_KEYID = '"+entTlProgTargetSkills.getPrtsTopiKeyid()+ "'AND PRTS_SPOKE_KEYID = '"+entTlProgTargetSkills.getPrtsSpokeKeyid()+ "'  AND PRTS_KEYID = '"+entTlProgTargetSkills.getPrtsKeyid()+ "' AND PRTS_ACTIVE = 'Y'";
		String  topiId = null;
		if (UIUtils.isValidKeyId(prtsKeyid)) {
			//topiId = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_TOPI_KEYID", "PRTS_KEYID", prtsKeyid);
			topiId = dbActionTemplate.getSingleValue(chkskill);
			//sqls.add(entTlRoleSkillLinkSql.getUpdateSql(entTlRoleSkillLinkSql.getErslDbFields(), entTlRoleSkillLink.getSaveArray())); // add insert sql for master table
			String tillDate=CommonFunctions.dateTimeNow();			
			StringBuffer sql = new StringBuffer();
			sql.append(" UPDATE " + TableNames.TBL_ENT_TL_PROG_IMPACT_SKILLS + " ");
			sql.append(" SET PIMS_EFF_TILL_DATE = DECODE(FLOOR((TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss')-PIMS_EFF_FROM_DATE)), ");
			sql.append("0,PIMS_EFF_FROM_DATE,TO_DATE('" +  tillDate + "','dd-Mon-yyyy hh24:mi:ss')-1) ");    			
			sql.append(" ,PIMS_ACTIVE ='N' ");
			sql.append(" WHERE PIMS_PRTS_KEYID ='" + prtsKeyid + "' AND PIMS_ACTIVE ='Y'  ");		    			
			sqls.add(sql.toString());
		}
//		End
		if(entTlProgTargetSkills.getEntTlProgImpactSkills()!= null && entTlProgTargetSkills.getEntTlProgImpactSkills().size()>=0) // check for detail table data
		{
			if (entTlProgTargetSkills.getEntTlProgImpactSkills().size()>=0) {
				CommonMessage.debugMsg("targetSkil keyid in role skill"+entTlProgTargetSkills.getEntTlProgImpactSkills().size());
				
			}
			List <EntTlProgImpactSkills> progImpactSkillslist = entTlProgTargetSkills.getEntTlProgImpactSkills();
	    	
	      for(EntTlProgImpactSkills entTlProgImpactSkills:progImpactSkillslist)
			{	
	    		
	    		entTlProgImpactSkills.setPimsKeyid(dbActionTemplate.getSequenceNumber(EntTlProgImpactSkillsSql.TBL_ENT_TL_PROG_IMPACT_SKILLS, 8, "PIMS",null,null)); // set the sequnce number
	    		entTlProgImpactSkills.setPimsPrtsKeyid(entTlProgTargetSkills.getPrtsKeyid());
	    		entTlProgImpactSkills.setPimsCreatedby(entTlProgTargetSkills.getPrtsCreatedby());
	    		if(entTlProgTargetSkills.getPrtsTopiKeyid().equals(topiId)){
					CommonMessage.debugMsg("Topic  available "+topiId);
					sqls.add(EntTlProgImpactSkillsSql.getUpdateSql(entTlProgImpactSkillsSql.getPimsDbFields(), entTlProgImpactSkills.getSaveArray()));// add insert sql for detail table
	    		}
	    		else{
	    			CommonMessage.debugMsg("Topic  NOT  available ");
				sqls.add(EntTlProgImpactSkillsSql.getInsertSql(entTlProgImpactSkillsSql.getPimsDbFields(), entTlProgImpactSkills.getSaveArray()));// add insert sql for detail table
	    		}
			}
		}
		return sqls;
	}		
}
//*END **/
	@Override
	public EntTlProgTargetSkills getSkilformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		EntTlProgTargetSkills entTlProgTargetSkills = new EntTlProgTargetSkills();
		String sql = EntTlProgTargetSkillsSql.getFormData();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { progKeyId };
		entTlProgTargetSkills.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return entTlProgTargetSkills;
	}

	@Override
	public List<String[]> getskillgridData(String string) throws Exception {
		// TODO Auto-generated method stub
		//String sql = EntTlProgTargetSkillsSql.skillGrid(string);
		List<String>  paramValues =  new ArrayList<String>();	
		paramValues.add(string);
		//List<String[]> grdData = dbActionTemplate.getDataList(sql);
		
		return dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_TARGETTOPIC", paramValues);
	}

	@Override
	public String  delgridData(String prtsKeyid) throws Exception {
		// TODO Auto-generated method stub
		try{
			String sqlMas = EntTlProgTargetSkillsSql.getDeleteSkillSql(prtsKeyid);
			String sqlDet = EntTlProgTargetSkillsSql.getDeleteSkillDtlSql(prtsKeyid);
			CommonMessage.debugMsg(sqlDet);
			CommonMessage.debugMsg(sqlMas);
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			
			sqls.add(sqlDet);
			sqls.add(sqlMas);
			dbActionTemplate.executeStatements(sqls);
			Object [] values = {prtsKeyid };
			int [] dataTypes = { Types.VARCHAR, Types.VARCHAR };
			
		return "success"; 
		}catch(Exception e){
			return "failure";
		}
		
		
		
	}

	@Override
	public String chkKeyExists(String progKeyId) throws SQLException {
		// TODO Auto-generated method stub
		String skillKey = dbActionTemplate.getSingleValue(EntTlProgTargetSkillsSql.TBL_ENT_TL_PROG_TARGET_SKILLS, "PRTS_KEYID", "PRTS_PROG_KEYID", progKeyId);
		return skillKey;
	}

	@Override
	public String getRattingKeyid(String orderno) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		String keyid ;
		sql.append(" SELECT DISTINCT SKRM_KEYID FROM " + TableNames.TBL_ENT_TL_SKILL_RATINGMST + " ");
		sql.append(" WHERE SKRM_ORDERNO ='" + orderno + "' ");
		
		keyid= dbActionTemplate.getSingleValue(sql.toString());
		
		return keyid;
	}
	
}

