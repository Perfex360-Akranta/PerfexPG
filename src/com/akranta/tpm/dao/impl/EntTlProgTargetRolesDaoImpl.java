package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlProgTargetRolesDao;
import com.akranta.tpm.dao.sql.EntTlProgTargetRolesSql;
import com.akranta.tpm.dao.sql.EntTlProgTargetSkillsSql;
import com.akranta.tpm.model.EntTlProgTargetRoles;
import com.akranta.tpm.model.EntTlProgTargetSkills;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlProgTargetRolesDaoImpl implements EntTlProgTargetRolesDao {


	private DBActionTemplate dbActionTemplate; 

	public EntTlProgTargetRolesDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlProgTargetRoles create(EntTlProgTargetRoles entTlProgTargetRoles) 	throws BusinessApplicationExceptions,Exception {
		CommonMessage.debugMsg("inside DaoImpl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlProgTargetRolesSql entTlProgTargetRolesSql = new EntTlProgTargetRolesSql(); // contains dbtable,field names, Field types and related sqls  of master table
		String chkroleExist = dbActionTemplate.getSingleValue(EntTlProgTargetRolesSql.TBL_ENT_TL_PROG_TARGET_ROLES, "PRTR_TRAR_KEYID", "PRTR_PROG_KEYID" , entTlProgTargetRoles.getPrtrProgKeyid());
		String rtlKeyId = dbActionTemplate.getSingleValue("SELECT RTAL_ROLE_KEYID FROM ENT_TL_ROLE_TRAININGAREA_LINK where RTAL_KEYID ='"+entTlProgTargetRoles.getPrtrTrarKeyid()+"'");
		CommonMessage.debugMsg("chekddd  "+chkroleExist+"----"+entTlProgTargetRoles.getPrtrTrarKeyid());
			try{
				if(!UIUtils.isValidKeyId(chkroleExist)||!chkroleExist.equals(entTlProgTargetRoles.getPrtrTrarKeyid()) ){
					CommonMessage.debugMsg("chekddd  "+chkroleExist+"***********"+entTlProgTargetRoles.getPrtrTrarKeyid());
				entTlProgTargetRoles.setPrtrKeyid(dbActionTemplate.getSequenceNumber(EntTlProgTargetRolesSql.TBL_ENT_TL_PROG_TARGET_ROLES, 10, "PRR", "MMYY", "Y")); // set the sequnce number
				//entTlProgTargetRoles.setPrtrTrarKeyid(rtlKeyId);
				sqls.add(EntTlProgTargetRolesSql.getInsertSql(entTlProgTargetRolesSql.getPrtrDbFields(), entTlProgTargetRoles.getSaveArray())); // add insert sql for master table
				}
				else{
					throw new BusinessApplicationExceptions("Role exists");
				}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				
		}catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("exceptions   "+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlProgTargetRoles;
	}
	
	public EntTlProgTargetRoles update(EntTlProgTargetRoles entTlProgTargetRoles)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlProgTargetRolesSql entTlProgTargetRolesSql = new EntTlProgTargetRolesSql();
		String rtlKeyId = dbActionTemplate.getSingleValue("SELECT RTAL_KEYID FROM ENT_TL_ROLE_TRAININGAREA_LINK where RTAL_ROLE_KEYID ='"+entTlProgTargetRoles.getPrtrTrarKeyid()+"'");
		try {
			entTlProgTargetRoles.setPrtrTrarKeyid(rtlKeyId);
			sqls.add(EntTlProgTargetRolesSql.getUpdateSql(entTlProgTargetRolesSql.getPrtrDbFields(), entTlProgTargetRoles.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlProgTargetRoles;
	}
	
	public EntTlProgTargetRoles delete(EntTlProgTargetRoles entTlProgTargetRoles)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlProgTargetRolesSql entTlProgTargetRolesSql = new EntTlProgTargetRolesSql();
		try {
			
			sqls.add(EntTlProgTargetRolesSql.getDeleteSql(entTlProgTargetRolesSql.getPrtrDbFields(), entTlProgTargetRoles.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return entTlProgTargetRoles;
	}

	@Override
	public List<String[]> getRolegridData(String progKeyId) throws Exception {
		// TODO Auto-generated method stub
		String sql = EntTlProgTargetRolesSql.roleGrid(progKeyId);
		List<String[]> grdData = dbActionTemplate.getDataList(sql);
		return grdData;
	}

	@Override
	public EntTlProgTargetRoles getRoleformValues(String progKeyId) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		EntTlProgTargetRoles entTlProgTargetRoles = new EntTlProgTargetRoles();
		String sql = EntTlProgTargetRolesSql.getFormData();
		CommonMessage.debugMsg("in dao " );
		Object args [] = new Object [] { progKeyId };
		entTlProgTargetRoles.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return entTlProgTargetRoles;
	}

	@Override
	public String delRolegridData(String prtrKeyid) throws Exception {
		// TODO Auto-generated method stub
		try{
			String sql = EntTlProgTargetRolesSql.getDeleteRoleSql(prtrKeyid);
			CommonMessage.debugMsg(sql);
			/*Object [] values = {prtrKeyid };
			int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; */
			dbActionTemplate.executeStatement(sql);
			return "success";
		}catch(Exception e){
			return "failure";
		}
		
	}

	@Override
	public String chkKeyRoleExists(String progKeyId) throws Exception {
		// TODO Auto-generated method stub
		String  sql = EntTlProgTargetRolesSql.getChkRoleExist(progKeyId);
		String roleKey = dbActionTemplate.getSingleValue(sql);
		return roleKey;
		
	}
	
}

