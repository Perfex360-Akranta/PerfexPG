package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;


import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlRoleMenuLinkDao;
import com.akranta.tpm.dao.sql.AdmTlRoleMenuLinkSql;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;


/* dao implementation */
public class AdmTlRoleMenuLinkDaoImpl implements AdmTlRoleMenuLinkDao {


	private DBActionTemplate dbActionTemplate; 

	public AdmTlRoleMenuLinkDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate =dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlRoleMenuLink create(AdmTlRoleMenuLink admTlRoleMenuLink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlRoleMenuLinkSql admTlRoleMenuLinkSql = new AdmTlRoleMenuLinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		
			if(admTlRoleMenuLink.getAdmRoleMenuLink()!= null && admTlRoleMenuLink.getAdmRoleMenuLink().size()>0) // check for detail table data
			{	
				String menuIds = null;				
				String roleId = null;
				for(int i =0;i<admTlRoleMenuLink.getAdmRoleMenuLink().size();i++)
				{
					AdmTlRoleMenuLink roleMenuLink = (AdmTlRoleMenuLink)admTlRoleMenuLink.getAdmRoleMenuLink().get(i);
					boolean allow = true;
					if(roleMenuLink.getArmlMenuid().indexOf(":")>0)
					{
						allow = false;
						roleMenuLink.setArmlMenuid(roleMenuLink.getArmlMenuid().substring(0, roleMenuLink.getArmlMenuid().indexOf(":")));
					}
					sqls.add(AdmTlRoleMenuLinkSql.deleteMenuRole(roleMenuLink.getArmlRoleid(), roleMenuLink.getArmlMenuid()));
					if(allow)
					{
						sqls.add(AdmTlRoleMenuLinkSql.getInsertSql(admTlRoleMenuLinkSql.getArmlDbFields(), roleMenuLink.getSaveArray())); // add insert sql for master table
					}
					if(CommonFunctions.isValidKeyId(menuIds))
					{
						menuIds += "'"+roleMenuLink.getArmlMenuid()+"'";
						if(i != admTlRoleMenuLink.getAdmRoleMenuLink().size()-1)
							menuIds += ",";
					}
					else
						menuIds = "'"+roleMenuLink.getArmlMenuid()+"',";
					
					if(!CommonFunctions.isValidKeyId(roleId))
						roleId = roleMenuLink.getArmlRoleid();
				}
				if(CommonFunctions.isValidKeyId(menuIds) && CommonFunctions.isValidKeyId(roleId))
				{
					sqls.add(AdmTlRoleMenuLinkSql.deleteMenuRoleLink(roleId, menuIds));
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
		return admTlRoleMenuLink;
	}
	
	public AdmTlRoleMenuLink update(AdmTlRoleMenuLink admTlRoleMenuLink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlRoleMenuLinkSql admTlRoleMenuLinkSql = new AdmTlRoleMenuLinkSql();
		try {

			sqls.add(AdmTlRoleMenuLinkSql.getUpdateSql(admTlRoleMenuLinkSql.getArmlDbFields(), admTlRoleMenuLink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlRoleMenuLink;
	}
	
	public AdmTlRoleMenuLink delete(AdmTlRoleMenuLink admTlRoleMenuLink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlRoleMenuLinkSql admTlRoleMenuLinkSql = new AdmTlRoleMenuLinkSql();
		try {
			
			sqls.add(AdmTlRoleMenuLinkSql.getDeleteSql(admTlRoleMenuLinkSql.getArmlDbFields(), admTlRoleMenuLink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlRoleMenuLink;
	}
	public String checkMenuRoleExist(String roleId,String menuId) throws Exception
	{
		String assignedFlag = dbActionTemplate.getSingleValue(AdmTlRoleMenuLinkSql.checkMenuRole(roleId, menuId));
		return assignedFlag;
	}
}

