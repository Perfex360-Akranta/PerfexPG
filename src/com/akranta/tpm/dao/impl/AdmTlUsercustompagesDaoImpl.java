/*package com.akranta.tpm.dao.impl;


package com.akranta.tpm.dao.impl;


import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.AdmTlUsercustompagesDao;
import com.akranta.tpm.dao.sql.AdmTlUsercustompagesSql;
import com.akranta.tpm.model.AdmTlUsercustompages;


public class AdmTlUsercustompagesDaoImpl implements AdmTlUsercustompagesDao {


	private DBActionTemplate dbActionTemplate; 

	public AdmTlUsercustompagesDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlUsercustompages create(AdmTlUsercustompages admTlUsercustompages) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); 
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			admTlUsercustompages.setUscpKeyid(dbActionTemplate.getSequenceNumber(AdmTlUsercustompagesSql.TBL_ADM_TL_USERCUSTOMPAGES)); // set the sequnce number 
			String updateSql = admTlUsercustompagesSql.getUpdateSqlForUser(admTlUsercustompages.getUscpUsrmKeyid());
			if(admTlUsercustompages.getUscpUsrmKeyid() != null)
				sqls.add(updateSql);
			sqls.add(AdmTlUsercustompagesSql.getInsertSql(admTlUsercustompagesSql.getUscpDbFields(), admTlUsercustompages.getSaveArray())); // add insert sql for master table

			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return admTlUsercustompages;
	}
	
	public AdmTlUsercustompages update(AdmTlUsercustompages admTlUsercustompages)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
		try {

			sqls.add(AdmTlUsercustompagesSql.getUpdateSql(admTlUsercustompages.getWwmsDbFields(), admTlUsercustompages.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlUsercustompages;
	}
	
	public AdmTlUsercustompages delete(AdmTlUsercustompages admTlUsercustompages)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlUsercustompagesSql admTlUsercustompages = new AdmTlUsercustompagesSql();
		try {
			
			sqls.add(AdmTlUsercustompages.getDeleteSql(admTlUsercustompages.getUscpDbFields(), admTlUsercustompages.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlUsercustompages;
	}
	
}

*/


package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.AdmTlUsercustompagesDao;
import com.akranta.tpm.dao.sql.AdmTlPwdhistorySql;
import com.akranta.tpm.dao.sql.AdmTlUsercustompagesSql;
import com.akranta.tpm.dao.sql.AdmTlUsermstSql;
import com.akranta.tpm.model.AdmTlUsercustompages;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class AdmTlUsercustompagesDaoImpl implements AdmTlUsercustompagesDao {


	private DBActionTemplate dbActionTemplate; 

	public AdmTlUsercustompagesDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public AdmTlUsercustompages create(AdmTlUsercustompages admTlUsercustompages) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			admTlUsercustompages.setUscpKeyid(dbActionTemplate.getSequenceNumber(AdmTlUsercustompagesSql.TBL_ADM_TL_USERCUSTOMPAGES, 8, "USC", "DDMM", "Y")); 
			if(admTlUsercustompages.getUscpPageuri() == "" && admTlUsercustompages.getUscpFormheader().equals("Home"))
				sqls.add(admTlUsercustompagesSql.getUpdateSqlForUser(admTlUsercustompages.getUscpUsrmKeyid()));
			else{
				sqls.add(admTlUsercustompagesSql.getDeleteSqlForUser(admTlUsercustompages.getUscpPageuri()));
				sqls.add(admTlUsercustompagesSql.getUpdateSqlForUser(admTlUsercustompages.getUscpUsrmKeyid()));
				sqls.add(AdmTlUsercustompagesSql.getInsertSql(admTlUsercustompagesSql.getUscpDbFields(), admTlUsercustompages.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls);
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return admTlUsercustompages;
	}
	
	public AdmTlUsercustompages update(AdmTlUsercustompages admTlUsercustompages)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
		try {

			sqls.add(AdmTlUsercustompagesSql.getUpdateSql(admTlUsercustompagesSql.getUscpDbFields(), admTlUsercustompages.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return admTlUsercustompages;
	}
	
	public AdmTlUsercustompages delete(AdmTlUsercustompages admTlUsercustompages)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
		try {
			
			sqls.add(admTlUsercustompagesSql.getDeleteSql(admTlUsercustompagesSql.getUscpDbFields(), admTlUsercustompages.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return admTlUsercustompages;
	}

	@Override
	public AdmTlUsercustompages recallHomePage(String userId) throws Exception {
		
		AdmTlUsercustompages admTlUsercustompages = new  AdmTlUsercustompages();
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
		
		String sql = admTlUsercustompagesSql.getUserByKeyid();
	    
		Object args [] = new Object [] {userId};
		CommonMessage.debugMsg("Select sql......."+sql);
		admTlUsercustompages.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		
		return admTlUsercustompages;
	}

	@Override
	public List<String[]> getMenuMasterData(String userId)	throws Exception {
		
		CommonMessage.debugMsg("SQL..... userId in daao impl " +userId);

		List<String []> dataList = new ArrayList<String[]>();
		AdmTlUsercustompagesSql admTlUsercustompagesSql = new AdmTlUsercustompagesSql();
		String menuMstDataSql = admTlUsercustompagesSql.getMenuMstData(userId);
		CommonMessage.debugMsg("SQL....."+menuMstDataSql);
		List<String []> menuMstData = dbActionTemplate.getDataList(menuMstDataSql);
		if(menuMstData.size()>0)
		{
			String isMaster = menuMstData.get(0)[0];
			String isFilter = menuMstData.get(0)[1];			
			String relatedFilter = menuMstData.get(0)[2];	
			String formHeader = menuMstData.get(0)[3];
			String url = menuMstData.get(0)[4];		
			
			String[] row = new String[ 5 ];
			row[0] = isMaster;
			row[1] = isFilter;
			row[2] = relatedFilter;		
			row[3] = formHeader;
			row[4] = url;
			dataList.add(row);				
		}
		
		return dataList;
		
	}
	
}

