package com.akranta.tpm.dao.impl;
import com.akranta.tpm.utils.CommonMessage;



import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.EntTlEmpnomenationmstDao;
import com.akranta.tpm.dao.sql.EntTlEmpnomenationmstSql;
import com.akranta.tpm.model.EntTlEmpnomenationmst;

/* dao implementation */
public class EntTlEmpnomenationmstDaoImpl implements EntTlEmpnomenationmstDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlEmpnomenationmstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<EntTlEmpnomenationmst> create(List<EntTlEmpnomenationmst> newentTlEmpnomenationmst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlEmpnomenationmstSql entTlEmpnomenationmstSql = new EntTlEmpnomenationmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			for(EntTlEmpnomenationmst entTlEmpnomenationmst  : newentTlEmpnomenationmst )
			{
				entTlEmpnomenationmst.setEpnmKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpnomenationmstSql.TBL_ENT_TL_EMPNOMENATIONMST, 15, "EPNM", "YYYY", "Y"));
						 // set the sequnce number 
				sqls.add(EntTlEmpnomenationmstSql.getInsertSql(entTlEmpnomenationmstSql.getEpnmDbFields(), entTlEmpnomenationmst.getSaveArray())); // add insert sql for master table
		    }
			CommonMessage.debugMsg("Insert sql" + sqls );
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return (List<EntTlEmpnomenationmst>) newentTlEmpnomenationmst;
	}
	
	public List<EntTlEmpnomenationmst> update(List<EntTlEmpnomenationmst> newentTlEmpnomenationmst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlEmpnomenationmstSql entTlEmpnomenationmstSql = new EntTlEmpnomenationmstSql();
		try {
			
			
			
			for(EntTlEmpnomenationmst entTlEmpnomenationmst  : newentTlEmpnomenationmst )
			{ 
				String Keyid = entTlEmpnomenationmst.getEpnmProgramid();
				CommonMessage.debugMsg("Keyid" + Keyid);
				sqls.add(entTlEmpnomenationmstSql.getDeleteSql(Keyid));
				
			}
			
			dbActionTemplate.executeStatements(sqls);
			
			for(EntTlEmpnomenationmst entTlEmpnomenationmst  : newentTlEmpnomenationmst )
			{
				entTlEmpnomenationmst.setEpnmKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpnomenationmstSql.TBL_ENT_TL_EMPNOMENATIONMST, 15, "EPNM", "YYYY", "Y"));						
				sqls.add(EntTlEmpnomenationmstSql.getInsertSql(entTlEmpnomenationmstSql.getEpnmDbFields(), entTlEmpnomenationmst.getSaveArray())); 
		    }
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return (List<EntTlEmpnomenationmst>) newentTlEmpnomenationmst;
	}
	
	public EntTlEmpnomenationmst delete(EntTlEmpnomenationmst entTlEmpnomenationmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlEmpnomenationmstSql entTlEmpnomenationmstSql = new EntTlEmpnomenationmstSql();
		try {
			
			sqls.add(entTlEmpnomenationmstSql.getDeleteSql(entTlEmpnomenationmst.getEpnmKeyid()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlEmpnomenationmst;
	}

	@Override
	public List<String[]> getnewempnominationDetail() throws Exception {
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg("GridsqlStarted");
		sql.append(" Select  'Program Keyid','bach_keyid','Program', 'StartDate', 'EndDate',	'Target Group','Target Skill','Nominate' From Dual "
				 + " union all "
				 + " select prog_keyid,bach_keyid,prog_name,TO_CHAR(bach_fromdate,'DD-MON-YYYY') as StartDate , " 
				 + " TO_CHAR(bach_tilldate,'DD-MON-YYYY') as EndDate,ROLE_NAME, '-' ,''  from gen_tl_rolemst, "
				 + " ( "
				 + " select prog_keyid,bach_keyid,prog_name,bach_fromdate,bach_tilldate "
				 + " from ent_tl_programmst,ent_tl_batchmst where bach_prog_keyid = prog_keyid "
				 + " ),( "
				 + " select prtr_prog_keyid,prtr_trar_keyid,rtal_trar_keyid,rtal_role_keyid  from "
				 + " ("
				 + " select prtr_prog_keyid,prtr_trar_keyid from ent_tl_prog_target_roles, " 
				 + " ent_tl_prog_target_skills "
				 + " where prtr_prog_keyid = prts_prog_keyid"
				 + " ), "
				 + " ( "
				 + " select rtal_keyid,rtal_trar_keyid,rtal_role_keyid "
				 + " from ent_tl_role_trainingarea_link "				 
				 + " )where prtr_trar_keyid = rtal_keyid "
				 + " )where prtr_prog_keyid = prog_keyid and ROLE_KEYID = rtal_role_keyid ");

		CommonMessage.debugMsg("Gridsql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		//CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

	@Override
	public List<String[]> EmpnominationReview() throws Exception {
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg("GridsqlStarted");
		sql.append("Select  'Program Keyid','bach_keyid','Program', 'StartDate', 'EndDate',	'Target Group','Target Skill','Nominated','Max Allowed' From Dual "
					+ " union all "
					+ " select prog_keyid,bach_keyid,prog_name,StartDate,EndDate,ROLE_NAME,TargetSkill,to_char(count(epnm_programid)),'0'  from "
					+ " (select epnm_programid from ENT_TL_EMPNOMENATIONMST),( "
					+ " select prog_keyid,bach_keyid,prog_name,TO_CHAR(bach_fromdate,'DD-MON-YYYY') as StartDate , "
					+ " TO_CHAR(bach_tilldate,'DD-MON-YYYY') as EndDate,ROLE_NAME, '-' as TargetSkill   from gen_tl_rolemst, "
					+ " ( "
					+ " select prog_keyid,bach_keyid,prog_name,bach_fromdate,bach_tilldate " 
					+ " from ent_tl_programmst,ent_tl_batchmst where bach_prog_keyid = prog_keyid  ), "
					+ " (  "
					+ " select prtr_prog_keyid,prtr_trar_keyid,rtal_trar_keyid,rtal_role_keyid  from  "
					+ " ( "
					+ " select prtr_prog_keyid,prtr_trar_keyid from ent_tl_prog_target_roles,  ent_tl_prog_target_skills "  
					+ " where prtr_prog_keyid = prts_prog_keyid ), "
					+ " (  select rtal_keyid,rtal_trar_keyid,rtal_role_keyid  from ent_tl_role_trainingarea_link  ) "
					+ " where prtr_trar_keyid = rtal_keyid  ) "
					+ " where prtr_prog_keyid = prog_keyid and ROLE_KEYID = rtal_role_keyid ) "
					+ " where epnm_programid = prog_keyid "
					+ " group by prog_keyid,bach_keyid,prog_name,StartDate,EndDate,ROLE_NAME,TargetSkill");

		CommonMessage.debugMsg("Gridsql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

	@Override
	public List<String[]> getResultGridEmp(String Keyid,String type) throws Exception {
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg("getResultGridEmp type" + type);
		if(type.equals("Rev"))
		{ 
			CommonMessage.debugMsg("getResultGridEmp type1" + type);
			
			sql.append(" select 'Select' ,'Select_checkbox','txtEpnmKeyid', 'txtEpnmProgramid','txtEpnmBatchid' ,'txtEpnmEmpid','Employee Name',"
					 + " 'txtEpnmcode'  , ' Current Skill',	'Target Skill','Last Training Attd. Date' ,'txtEpnmStatus' From Dual  "
					 + " union all "
					 + " select  case txtepnmKeyid when  '-' then 'N' else 'Y' end , case txtepnmKeyid when  '-' then '0' else '1' end Select_checkbox ,txtepnmKeyid, txtepnmProgramid,txtepnmBatchid,EMPM_KEYID,EMPM_NAME,EMPM_CODE,CurrentSkill,TargetSkill,LastTrainingAttd,'Y' "
					 + " from ( "
					 + " select 'Select_checkbox' , EPNM_KEYID as txtepnmKeyid,prog_keyid as txtepnmProgramid,bach_keyid as txtepnmBatchid, "
					 + " EMPM_KEYID,EMPM_NAME,EMPM_CODE ,'-' CurrentSkill,'-' TargetSkill,'-' LastTrainingAttd  "
					 + " from gen_tl_employeemst,ent_tl_empnomenationmst,"
					 + " (select prog_keyid,bach_keyid,prog_name,bach_fromdate,bach_tilldate  "
					 + " from ent_tl_programmst,ent_tl_batchmst where bach_prog_keyid = prog_keyid  ),"
					 + " (  select prtr_prog_keyid,prtr_trar_keyid,rtal_trar_keyid,rtal_role_keyid,erel_empm_keyid  "
					 + " from  (  select prtr_prog_keyid,prtr_trar_keyid from ent_tl_prog_target_roles,ent_tl_prog_target_skills  "
					 + " where prtr_prog_keyid = prts_prog_keyid  ),  (  select rtal_keyid,rtal_trar_keyid,rtal_role_keyid,erel_empm_keyid  "
					 + " from ent_tl_role_trainingarea_link, ent_tl_role_emp_link  where rtal_keyid = Erel_rtal_keyid  ) "
					 + " where prtr_trar_keyid = rtal_keyid  )where prtr_prog_keyid = prog_keyid "
					 + " and EMPM_KEYID = erel_empm_keyid "
					 + " and EPNM_PROGRAMID(+) = prog_keyid and EMPM_KEYID =epnm_empid   and prog_keyid = '"+ Keyid +"')");
		}
		else
		{ 
			CommonMessage.debugMsg("getResultGridEmp type2" + type);
			sql.append("Select 'Select' ,'Select_checkbox','txtEpnmKeyid', 'txtEpnmProgramid','txtEpnmBatchid' ,'txtEpnmEmpid','Employee Name', 'txtEpnmcode'  , ' Current Skill',	'Target Skill','Last Training Attd. Date' From Dual "
					 + " union all " 
					 + " select 'Select','Select_checkbox' ,nvl(EPNM_KEYID,'-') as txtepnmKeyid,prog_keyid as txtepnmProgramid,bach_keyid as txtepnmBatchid,EMPM_KEYID,EMPM_NAME,EMPM_CODE ,'-' CurrentSkill,'-' TargetSkill,'-' LastTrainingAttd  from gen_tl_employeemst,ENT_TL_EMPNOMENATIONMST, " 
					 + " (select prog_keyid,bach_keyid,prog_name,bach_fromdate,bach_tilldate "
					 + " from ent_tl_programmst,ent_tl_batchmst where bach_prog_keyid = prog_keyid " 
					 + " ),( "
					 + " select prtr_prog_keyid,prtr_trar_keyid,rtal_trar_keyid,rtal_role_keyid,erel_empm_keyid  from "
					 + " ( "
					 + " select prtr_prog_keyid,prtr_trar_keyid from ent_tl_prog_target_roles,ent_tl_prog_target_skills "
					 + " where prtr_prog_keyid = prts_prog_keyid "
					 + " ), "
					 + " ( "
					 + " select rtal_keyid,rtal_trar_keyid,rtal_role_keyid,erel_empm_keyid "
					 + " from ent_tl_role_trainingarea_link, ent_tl_role_emp_link "
					 + " where rtal_keyid = Erel_rtal_keyid "
					 + " )  where prtr_trar_keyid = rtal_keyid "
					 + " )where prtr_prog_keyid = prog_keyid and EMPM_KEYID = erel_empm_keyid and" 
					 + " EPNM_PROGRAMID(+) = prog_keyid and prog_keyid = '"+ Keyid +"'");
		}
		CommonMessage.debugMsg("Gridsql..." + sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value" + gridData.get(1));
		return gridData;
	}

	
 
	
}

