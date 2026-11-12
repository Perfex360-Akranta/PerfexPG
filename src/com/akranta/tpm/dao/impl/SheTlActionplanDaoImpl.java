package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlActionplanDao;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.SheTlActionplanSql;
import com.akranta.tpm.dao.sql.SheTlIncidentEmployeeSql;
import com.akranta.tpm.dao.sql.SheTlIncidentmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.SheTlActionplan;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class SheTlActionplanDaoImpl implements SheTlActionplanDao {


	private DBActionTemplate dbActionTemplate; 

	public SheTlActionplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

public SheTlActionplan create(SheTlActionplan sheTlActionplan) 	throws Exception {

		
		
		com.akranta.tpm.utils.CommonFunctions.debugMsg("Inside DaoImpl.........");
		List<String> sqls = new ArrayList<String>(); 	
		SheTlActionplanSql sheTlActionplanSql = new SheTlActionplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			com.akranta.tpm.utils.CommonFunctions.debugMsg("Inside DaoImpl.........");
			/*String status = dbActionTemplate.getSingleValue(TableNames.TBL_SHE_TL_INCIDENTMST, "SINC_STATUS", "SINC_KEYID", sheTlActionplan.getSacpSincKeyid());
			if(UIUtils.isValidKeyId(status))
			{
				if(status.equals("C"))
					sheTlActionplan.setSacpStatus("C");
			}*/
			sheTlActionplan.setSacpKeyid(dbActionTemplate.getSequenceNumber(SheTlActionplanSql.TBL_SHE_TL_ACTIONPLAN,12,"SAC","","")); //(EntTlChecklistmstSql.TBL_ENT_TL_CHECKLISTMST, 10, "CHK", "YYMM", "Y"));// set the sequnce number 
			sqls.add(SheTlActionplanSql.getInsertSql(sheTlActionplanSql.getSacpDbFields(), sheTlActionplan.getSaveArray())); // add insert sql for master table
			if(UIUtils.isValidKeyId(sheTlActionplan.getSacpStatus()))
			{
				CommonFunctions.debugMsg("ACTION PLAN STATUS : "+sheTlActionplan.getSacpStatus());
				if(sheTlActionplan.getSacpStatus().equals("C"))
				{
					String yyStatus = dbActionTemplate.getSingleValue(TableNames.TBL_SHE_TL_INCIDENTMST, "SINC_WHYWHYFLAG", "SINC_KEYID", sheTlActionplan.getSacpSincKeyid());
					CommonFunctions.debugMsg("WHY WHY STATUS : "+yyStatus);
					if(UIUtils.isValidKeyId(yyStatus))
					{
						if(yyStatus.equals("C"))
						{
							List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(sheTlActionplan.getSacpSincKeyid()));
							String status = "C";
							if(planStatus.size()>0)
							{
								for(int i=0;i<planStatus.size();i++)
								{
									if(planStatus.get(i)[0].equals("P"))
									{
										CommonFunctions.debugMsg("STATUS ->"+i+" : "+planStatus.get(i)[0]);
										status = "P";
										break;
									}
								}
								
							}
							CommonFunctions.debugMsg("INCIDENT KEY : "+sheTlActionplan.getSacpSincKeyid());
							//sqls.add(BdmTlMstSql.updateIncidentSql(sheTlActionplan.getSacpSincKeyid(),status)); by karthikeyanb
						}
					}
				}
			}
			dbActionTemplate.executeStatements(sqls); 
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("test daoImpl...."+e.getMessage());
		}
		return sheTlActionplan;
		
		
		
		/*List<String> sqls = new ArrayList<String>(); 
		SheTlActionplanSql sheTlActionplan = new SheTlActionplanSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			sheTlActionplan.setSacp_keyid(dbActionTemplate.getSequenceNumber(SheTlActionplanSql.TBL_SHE_TL_ACTIONPLAN)); // set the sequnce number 
			sqls.add(SheTlActionplanSql.getInsertSql(sheTlActionplanSql.getSacpDbFields(), sheTlActionplan.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return sheTlActionplan;*/
		
		
		
	}
	
public SheTlActionplan update(SheTlActionplan sheTlActionplan)	throws Exception { 
	
	List<String> sqls = new ArrayList<String>();
	SheTlActionplanSql sheTlActionplanSql = new SheTlActionplanSql();
	try {
		//String status = dbActionTemplate.getSingleValue(TableNames.TBL_SHE_TL_INCIDENTMST, "SINC_STATUS", "SINC_KEYID", sheTlActionplan.getSacpSincKeyid());
		if(UIUtils.isValidKeyId(sheTlActionplan.getSacpStatus()))
		{
			if(sheTlActionplan.getSacpStatus().equals("C"))
			{
				String yyStatus = dbActionTemplate.getSingleValue(TableNames.TBL_SHE_TL_INCIDENTMST, "SINC_WHYWHYFLAG", "SINC_KEYID", sheTlActionplan.getSacpSincKeyid());
				if(UIUtils.isValidKeyId(yyStatus))
				{
					if(yyStatus.equals("C"))
					{
						List<String[]> planStatus = dbActionTemplate.getDataList(SheTlIncidentmstSql.selectActionPlanStatus(sheTlActionplan.getSacpSincKeyid()));
						String status = "C";
						if(planStatus.size()>0)
						{
							for(int i=0;i<planStatus.size();i++)
							{
								if(planStatus.get(i)[0].equals("P"))
								{
									status = "P";
									break;
								}
							}
							
						}
						//sqls.add(BdmTlMstSql.updateIncidentSql(sheTlActionplan.getSacpSincKeyid(),status)); by karthikeyanb
					}
				}
			}
		}
		/*if(UIUtils.isValidKeyId(status))
		{
			if(status.equals("C"))
				sheTlActionplan.setSacpStatus("C");
		}*/
		sqls.add(SheTlActionplanSql.getUpdateSql(sheTlActionplanSql.getSacpDbFields(), sheTlActionplan.getSaveArray()));
		
		dbActionTemplate.executeStatements(sqls);
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		throw new Exception(e.getMessage());
	}
	
	return sheTlActionplan;
	//return null;
}
	
	public SheTlActionplan delete(SheTlActionplan sheTlActionplan)
			throws Exception {

		/*List<String> sqls = new ArrayList<String>();
		SheTlActionplanSql sheTlActionplan = new SheTlActionplanSql();
		try {
			
			sqls.add(SheTlActionplan.getDeleteSql(sheTlActionplan.getSacpDbFields(), sheTlActionplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlActionplan;*/
		return null;
	}

	@Override
	public List<String[]> getIncidentActionPlan(SheTlActionplan sheTlActionplan) throws Exception {
		
		try
		{
			String sql = SheTlActionplanSql.getIncidentActionPlan(sheTlActionplan);
			CommonFunctions.debugMsg("sql fun...."+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SheTlActionplan deleteActionPlan(SheTlActionplan sheTlActionplan)	throws Exception {
		List<String> sqls = new ArrayList<String>();
		SheTlActionplanSql sheTlActionplanSql = new SheTlActionplanSql();
		try {
			
			sqls.add(SheTlActionplanSql.getDeleteSql(sheTlActionplanSql.getSacpDbFields(), sheTlActionplan.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlActionplan;
	}
	
}

