package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.ValidationException;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KpiTlKpiremarksDao;
import com.akranta.tpm.dao.sql.KpiTlKpiremarksSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.KpiTlKpiremarks;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.model.KpiTlKpiremarks;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class KpiTlKpiremarksDaoImpl implements KpiTlKpiremarksDao {

	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;
	FunctionCallApi fnCallApi;
	private DBActionTemplate dbActionTemplate; 

	public KpiTlKpiremarksDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void KpiTlKpiremarksDaoImplJwt(String JwtToken) 
	{
		try{
			kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public KpiTlKpiremarks create(KpiTlKpiremarks kpiTlKpiremarks) 	throws Exception {
		CommonMessage.debugMsg("Single:");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KpiTlKpiremarksSql kpiTlKpiremarksSql = new KpiTlKpiremarksSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			kpiTlKpiremarks.setKprmKeyid(dbActionTemplate.getSequenceNumber(KpiTlKpiremarksSql.TBL_KPI_TL_KPIREMARKS)); // set the sequnce number 
			sqls.add(KpiTlKpiremarksSql.getInsertSql(kpiTlKpiremarksSql.getKprmDbFields(), kpiTlKpiremarks.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kpiTlKpiremarks;
	}
	
	public  List<KpiTlKpiremarks> create(List<KpiTlKpiremarks> kpiTlKpiremarks) throws Exception,ValidationException,BusinessApplicationExceptions { 
		CommonMessage.debugMsg("My Area:");
		List<String> sqls = new ArrayList<String>(); 
			KpiTlKpiremarksSql newKpiTlKpiremarksSql = new KpiTlKpiremarksSql();
			if (kpiTlKpiremarks!= null && kpiTlKpiremarks.size() > 0) 
			{
				CommonMessage.debugMsg("kpiTlKpiremarks not Null");
				//sqls.add(" DELETE FROM KPI_TL_KPIREMARKS WHERE KPRM_INDICATORID = '" + kpiTlKpiremarks.get(0).getKprmIndicatorid() + "' ");
				
				for( KpiTlKpiremarks newKpiTlKpiremarks :kpiTlKpiremarks)
				{	
					if(newKpiTlKpiremarks.getKprmActive().equals("Y")){	
						CommonMessage.debugMsg("newKpiTlKpiremarks.getKprmActive():" +newKpiTlKpiremarks.getKprmActive());
						if(!UIUtils.isValidKeyId(newKpiTlKpiremarks.getKprmKeyid())){	
							newKpiTlKpiremarks.setKprmKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_KPI_TL_KPIREMARKS, 10, "KPR", null, null)); // set the sequnce number
							sqls.add(newKpiTlKpiremarksSql.getInsertSql(newKpiTlKpiremarksSql.getKprmDbFields(), newKpiTlKpiremarks.getSaveArray())); // add insert sql for master table}
							CommonMessage.debugMsg("insert:" +newKpiTlKpiremarks.getKprmKeyid());
						}
						else{
							sqls.add(newKpiTlKpiremarksSql.getUpdateSql(newKpiTlKpiremarksSql.getKprmDbFields(), newKpiTlKpiremarks.getSaveArray()));
							CommonMessage.debugMsg("update:" +newKpiTlKpiremarks.getKprmKeyid());
						} 
					}
					else if(newKpiTlKpiremarks.getKprmActive().equals("N")){	
						sqls.add(newKpiTlKpiremarksSql.getDeleteSql(newKpiTlKpiremarksSql.getKprmDbFields(), newKpiTlKpiremarks.getSaveArray()));
						CommonMessage.debugMsg("Detete:" +newKpiTlKpiremarks.getKprmKeyid());
					}
				}
				dbActionTemplate.executeStatements(sqls);
			}	
		return kpiTlKpiremarks;
	}
	
	public KpiTlKpiremarks update(KpiTlKpiremarks kpiTlKpiremarks)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KpiTlKpiremarksSql kpiTlKpiremarksSql = new KpiTlKpiremarksSql();
		try {

			sqls.add(KpiTlKpiremarksSql.getUpdateSql(kpiTlKpiremarksSql.getKprmDbFields(), kpiTlKpiremarks.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kpiTlKpiremarks;
	}
	
	public KpiTlKpiremarks delete(KpiTlKpiremarks kpiTlKpiremarks)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		KpiTlKpiremarksSql kpiTlKpiremarksSql = new KpiTlKpiremarksSql();
		try {
			
			sqls.add(kpiTlKpiremarksSql.getDeleteSql(kpiTlKpiremarksSql.getKprmDbFields(), kpiTlKpiremarks.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kpiTlKpiremarks;
	}
	
	public KpiTlKpiremarks select(KpiTlKpiremarks newKpiTlKpiremarks)throws Exception {
		KpiTlKpiremarksSql kpiTlKpiremarksSql = new KpiTlKpiremarksSql();
		String sql = KpiTlKpiremarksSql.getSelectSql(kpiTlKpiremarksSql.getKprmDbFields(), newKpiTlKpiremarks.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL TEST 1: "+sql);		
		Object [] args =  new Object [] {};
		newKpiTlKpiremarks.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query TEST 2:"+newKpiTlKpiremarks.getKprmKeyid());
		return newKpiTlKpiremarks;
		
	
	}
	
}

