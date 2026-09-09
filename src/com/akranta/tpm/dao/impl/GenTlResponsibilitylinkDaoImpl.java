package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlResponsibilitylinkDao;
import com.akranta.tpm.dao.sql.GenTlActplnNonemployeeSql;
import com.akranta.tpm.dao.sql.GenTlResponsibilitylinkSql;
import com.akranta.tpm.model.GenTlActplnNonemployee;
import com.akranta.tpm.model.GenTlResponsibilitylink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlResponsibilitylinkDaoImpl implements GenTlResponsibilitylinkDao {


	private DBActionTemplate dbActionTemplate; 

	public GenTlResponsibilitylinkDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlResponsibilitylink create(GenTlResponsibilitylink genTlResponsibilitylink) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlResponsibilitylinkSql genTlResponsibilitylinkSql = new GenTlResponsibilitylinkSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlActplnNonemployeeSql genTlActplnNonemployeeSql=new GenTlActplnNonemployeeSql();
		List<GenTlResponsibilitylink> ResposiableDtl=genTlResponsibilitylink.getResposiablelinkDtl();
		CommonMessage.debugMsg("genTlNearmissreportmst.gettargetdate():   dao impl bfr try "+genTlResponsibilitylink.getRsplTargetdate());
		CommonMessage.debugMsg("link keyid: gg:::::"+genTlResponsibilitylink.getResposiablelinkDtl().get(0).getRsplKeyid());
		CommonMessage.debugMsg("genTlResponsibilitylink.getname:::"+genTlResponsibilitylink.getGenTlActplnNonemployee().getNactName());

		try{
			if(genTlResponsibilitylink.getResposiablelinkDtl()!=null){
				//sqls.add(GenTlResponsibilitylinkSql.getDeleteSqlAll(genTlResponsibilitylink.getResposiablelinkDtl().get(0).getRsplRefdocid())); // add insert sql for master table
				GenTlActplnNonemployee genTlActplnNonemployee=new GenTlActplnNonemployee();
				CommonMessage.debugMsg("Inside DAO for details....");
				for(int i=0;i<ResposiableDtl.size();i++){
					GenTlResponsibilitylink genTlResponsibilitylinkdtl=genTlResponsibilitylink.getResposiablelinkDtl().get(i);
					CommonMessage.debugMsg(genTlResponsibilitylinkdtl.getFlag()+" link keyid: keyid:::::"+genTlResponsibilitylinkdtl.getRsplKeyid());
					CommonMessage.debugMsg("Target date:::::"+genTlResponsibilitylinkdtl.getRsplTargetdate());
					//CommonMessage.debugMsg("link keyid: flag:::::"+genTlResponsibilitylinkdtl.getFlag());

					if( genTlResponsibilitylinkdtl.getFlag().equals("I")) {
						CommonMessage.debugMsg("link keyid: gg insert :::::"+genTlResponsibilitylinkdtl.getRsplKeyid()+"::::::: faldg"+genTlResponsibilitylinkdtl.getFlag());
						CommonMessage.debugMsg(genTlResponsibilitylink.getRsplTargetdate()  +  "   dao impl   678 checkng targetdate");
						genTlResponsibilitylink.setRsplEmployeeid(genTlResponsibilitylinkdtl.getRsplEmployeeid());
						//genTlResponsibilitylink.setRsplTargetdate(genTlResponsibilitylinkdtl.getRsplTargetdate());
						CommonMessage.debugMsg(genTlResponsibilitylink.getRsplTargetdate()  +  "   dao impl checkng targetdate");
						fillNearmissreportdtlValues(genTlResponsibilitylinkdtl,genTlResponsibilitylink);
						genTlResponsibilitylink.setRsplKeyid(dbActionTemplate.getSequenceNumber(GenTlResponsibilitylinkSql.TBL_GEN_TL_RESPONSIBILITYLINK,10,"RSLK","","")); // set the sequnce number 
						sqls.add(GenTlResponsibilitylinkSql.getInsertSql(genTlResponsibilitylinkSql.getRsplDbFields(), genTlResponsibilitylink.getSaveArray())); // add insert sql for master table
					
					
					}
					else if(genTlResponsibilitylinkdtl.getFlag().equals("D")){
						CommonMessage.debugMsg("link keyid: gg delete :::::"+genTlResponsibilitylinkdtl.getRsplKeyid()+"::::::: faldg"+genTlResponsibilitylinkdtl.getFlag());
						sqls.add(GenTlResponsibilitylinkSql.getDeleteSql(genTlResponsibilitylinkSql.getRsplDbFields(), genTlResponsibilitylinkdtl.getSaveArray())); // add insert sql for master table
					
					}
					
				}
				if(UIUtils.isValidKeyId(genTlResponsibilitylink.getGenTlActplnNonemployee().getNactName())){
					CommonMessage.debugMsg("genTlResponsibilitylink.getname in if:::"+genTlResponsibilitylink.getGenTlActplnNonemployee().getNactName());

					genTlActplnNonemployee.setNactName(genTlResponsibilitylink.getGenTlActplnNonemployee().getNactName());	
					genTlActplnNonemployee.setNactRefdocid(genTlResponsibilitylink.getGenTlActplnNonemployee().getNactRefdocid());
					fillGenTlActplnNonemployeeValues(genTlActplnNonemployee,genTlResponsibilitylink);
					genTlActplnNonemployee.setNactKeyid((dbActionTemplate.getSequenceNumber(GenTlActplnNonemployeeSql.TBL_GEN_TL_ACTPLN_NONEMPLOYEE,10,"NACT","",""))); // set the sequnce number 
					sqls.add(GenTlActplnNonemployeeSql.getInsertSql(genTlActplnNonemployeeSql.getNactDbFields(), genTlActplnNonemployee.getSaveArray())); // add insert sql for master table
				
				}
			}
			for (GenTlResponsibilitylink genTlResponsibilitylinkmodel : ResposiableDtl) {
				CommonMessage.debugMsg("link keyid::::::"+genTlResponsibilitylinkmodel.getRsplKeyid());
				}
			
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlResponsibilitylink;
	}
	
	private void fillGenTlActplnNonemployeeValues(GenTlActplnNonemployee genTlActplnNonemployee, GenTlResponsibilitylink genTlResponsibilitylink) {
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactActive()))
			genTlActplnNonemployee.setNactActive("Y");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedon()))
			genTlActplnNonemployee.setNactCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedby()))
			genTlActplnNonemployee.setNactCreatedby("{}");
		
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactApldKeyid()))
			genTlActplnNonemployee.setNactApldKeyid("{}");
		
		
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactCreatedon()))
			genTlActplnNonemployee.setNactCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactModifiedon()))
			genTlActplnNonemployee.setNactModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactRefdocid()))
			genTlActplnNonemployee.setNactRefdocid("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactRemarks()))
			genTlActplnNonemployee.setNactRemarks("{}");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled1()))
			genTlActplnNonemployee.setNactTempfiled1("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled2()))
			genTlActplnNonemployee.setNactTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled3()))
			genTlActplnNonemployee.setNactTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled4()))
			genTlActplnNonemployee.setNactTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled5()))
			genTlActplnNonemployee.setNactTempfiled5("-");
		if(!UIUtils.isValidKeyId(genTlActplnNonemployee.getNactTempfiled6()))
			genTlActplnNonemployee.setNactTempfiled6("-");
	
		
	}

	private void fillNearmissreportdtlValues(GenTlResponsibilitylink newGenTlResponsibilitylinkdtl, GenTlResponsibilitylink genTlResponsibilitylink) {
	
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplActive()))
			genTlResponsibilitylink.setRsplActive("Y");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplCompletedon()))
			genTlResponsibilitylink.setRsplCompletedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplCreatedby()))
			genTlResponsibilitylink.setRsplCreatedby("{}");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplCreatedon()))
			genTlResponsibilitylink.setRsplCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplCreatedby()))
			genTlResponsibilitylink.setRsplCreatedby("{}");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplModifiedon()))
			genTlResponsibilitylink.setRsplModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplRefdocid()))
			genTlResponsibilitylink.setRsplRefdocid("{}");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplRefdoctype()))
			genTlResponsibilitylink.setRsplRefdoctype("{}");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplStatus()))
			genTlResponsibilitylink.setRsplStatus("P");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTargetdate()))
			genTlResponsibilitylink.setRsplTargetdate(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTempfiled1()))
			genTlResponsibilitylink.setRsplTempfiled1("-");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTempfiled2()))
			genTlResponsibilitylink.setRsplTempfiled2("-");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTempfiled3()))
			genTlResponsibilitylink.setRsplTempfiled3("-");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTempfiled4()))
			genTlResponsibilitylink.setRsplTempfiled4("-");
		if(!UIUtils.isValidKeyId(genTlResponsibilitylink.getRsplTempfiled5()))
			genTlResponsibilitylink.setRsplTempfiled5("-");
	
}
	public GenTlResponsibilitylink update(GenTlResponsibilitylink genTlResponsibilitylink)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlResponsibilitylinkSql genTlResponsibilitylinkSql = new GenTlResponsibilitylinkSql();
		try {

			sqls.add(GenTlResponsibilitylinkSql.getUpdateSql(genTlResponsibilitylinkSql.getRsplDbFields(), genTlResponsibilitylink.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return genTlResponsibilitylink;
	}
	
	public GenTlResponsibilitylink delete(GenTlResponsibilitylink genTlResponsibilitylink)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		GenTlResponsibilitylinkSql genTlResponsibilitylinkSql = new GenTlResponsibilitylinkSql();
		try {
			
			sqls.add(genTlResponsibilitylinkSql.getDeleteSql(genTlResponsibilitylinkSql.getRsplDbFields(), genTlResponsibilitylink.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return genTlResponsibilitylink;
	}
	
}

