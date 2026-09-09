package com.akranta.tpm.dao.impl;





import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.PcsTlProductionplanDao;
import com.akranta.tpm.dao.sql.PcsTlEnergyconsumptiondtlSql;
import com.akranta.tpm.dao.sql.PcsTlProductionplanSql;
import com.akranta.tpm.model.PcsTlProductionplan;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class PcsTlProductionplanDaoImpl implements PcsTlProductionplanDao {


	private DBActionTemplate dbActionTemplate; 
	private PcsTlProductionplanSql pcsTlProductionplanSql;

	public PcsTlProductionplanDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlProductionplanSql = new PcsTlProductionplanSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public PcsTlProductionplan create(PcsTlProductionplan pcsTlProductionplan) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 		
		try{
			if(pcsTlProductionplan.getProdPlan() != null && pcsTlProductionplan.getProdPlan().size()>0)
			{
				GenSequenceNumber seq = new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), PcsTlProductionplanSql.TBL_PCS_TL_PRODUCTIONPLAN,10,"PPE", "MMYY", "Y");
				for(int i =0;i<pcsTlProductionplan.getProdPlan().size();i++)
				{	
					PcsTlProductionplan productionPlan = (PcsTlProductionplan)pcsTlProductionplan.getProdPlan().get(i);
					
					if(CommonFunctions.isValidKeyId(productionPlan.getPrplProductid()))
						pcsTlProductionplan.setPrplProductid(productionPlan.getPrplProductid());
					if(CommonFunctions.isValidKeyId(productionPlan.getPrplPlanqty()))
						pcsTlProductionplan.setPrplPlanqty(productionPlan.getPrplPlanqty());
					CommonMessage.debugMsg(i + " : "+productionPlan.getPrplRevisionno()+"<---->"+pcsTlProductionplan.getPrplRevisionno());
					if(CommonFunctions.isValidKeyId(productionPlan.getPrplRevisionno()))
						pcsTlProductionplan.setPrplRevisionno(productionPlan.getPrplRevisionno());
					else
						pcsTlProductionplan.setPrplRevisionno("{}");
					
					if(UIUtils.isValidKeyId(productionPlan.getPrplKeyid()))
					{
						pcsTlProductionplan.setPrplCreatedon(pcsTlProductionplan.getPrplModifiedon());
						pcsTlProductionplan.setPrplKeyid(productionPlan.getPrplKeyid());						
						sqls.add(PcsTlProductionplanSql.getUpdateSql(pcsTlProductionplanSql.getPrplDbFields(), pcsTlProductionplan.getSaveArray()));
					}
					else
					{
						pcsTlProductionplan.setPrplCreatedon(pcsTlProductionplan.getPrplModifiedon());
						pcsTlProductionplan.setPrplKeyid(seq.getSequnceNumber()); // set the sequnce number 
						sqls.add(PcsTlProductionplanSql.getInsertSql(pcsTlProductionplanSql.getPrplDbFields(), pcsTlProductionplan.getSaveArray())); // add insert sql for master table
					}
				}
				dbActionTemplate.executeStatements(sqls);
			}			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlProductionplan;
	}
	
	public PcsTlProductionplan update(PcsTlProductionplan pcsTlProductionplan)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();		
		try {

			sqls.add(PcsTlProductionplanSql.getUpdateSql(pcsTlProductionplanSql.getPrplDbFields(), pcsTlProductionplan.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlProductionplan;
	}
	
	public PcsTlProductionplan delete(PcsTlProductionplan pcsTlProductionplan)
			throws Exception {

		List<String> sqls = new ArrayList<String>();		
		try {
			
			sqls.add(PcsTlProductionplanSql.getDeleteSql(pcsTlProductionplanSql.getPrplDbFields(), pcsTlProductionplan.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlProductionplan;
	}
	
}

