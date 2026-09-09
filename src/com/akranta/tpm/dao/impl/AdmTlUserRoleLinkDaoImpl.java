package com.akranta.tpm.dao.impl;





import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.AdmTlUserRoleLinkDao;
import com.akranta.tpm.dao.sql.AdmTlUserRoleLinkSql;
import com.akranta.tpm.model.AdmTlUserRoleLink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class AdmTlUserRoleLinkDaoImpl implements AdmTlUserRoleLinkDao {


	private DBActionTemplate dbActionTemplate; 

	public AdmTlUserRoleLinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlUserRoleLink create(AdmTlUserRoleLink admTlUserRoleLink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlUserRoleLinkSql admTlUserRoleLinkSql = new AdmTlUserRoleLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		//admTlUserRoleLink.setArulRoleid(dbActionTemplate.getSequenceNumber(AdmTlUserRoleLinkSql.TBL_ADM_TL_USER_ROLE_LINK)); // set the sequnce number 
		sqls.add(AdmTlUserRoleLinkSql.getInsertSql(admTlUserRoleLinkSql.getArulDbFields(), admTlUserRoleLink.getSaveArray())); // add insert sql for master table
			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return admTlUserRoleLink;
	}
	
	public AdmTlUserRoleLink update(AdmTlUserRoleLink admTlUserRoleLink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlUserRoleLinkSql admTlUserRoleLinkSql = new AdmTlUserRoleLinkSql();
		sqls.add(AdmTlUserRoleLinkSql.getUpdateSql(admTlUserRoleLinkSql.getArulDbFields(), admTlUserRoleLink.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
			
		
		return admTlUserRoleLink;
	}
	
	public String delete(String rollId,String userId)
			throws Exception {
		String status=null;
		List<String> sqls = new ArrayList<String>();
	
			
		sqls.add("delete from ADM_TL_USER_ROLE_LINK where ARUL_ROLEID='"+rollId+"' and ARUL_USERID = '" + userId +"'");
		//CommonMessage.debugMsg("sqls in dao impl=>"+sqls);
		dbActionTemplate.executeStatements(sqls);
		 status="success";
	
		return status;
	}

	@Override
	public List<String[]> getUserRoll(String userId) throws Exception {
		
		                   
			String sql =AdmTlUserRoleLinkSql.getUserRoll(userId);
			CommonMessage.debugMsg("sql in dao"+sql);
			List<String[]> result=dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg("result=>"+result);
			return result;
			
		
		
	}

	@Override
	public List<String[]> getUserRollS(String userid) throws Exception {
		// TODO Auto-generated method stub
		AdmTlUserRoleLink admTlUserRoleLink=new AdmTlUserRoleLink();
		String sql = AdmTlUserRoleLinkSql.getUserRollS(userid);
	    
	    
		CommonMessage.debugMsg("sql in dao"+sql);
		List<String[]> result=dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("result=>"+result);
		return result;
	}
	
}

