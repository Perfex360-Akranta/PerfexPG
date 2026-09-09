package com.akranta.tpm.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlNearmissreportmstnewDao;

import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlNearmissreportmstnewDaoImpl;


import com.akranta.tpm.dao.sql.GenTlNearmissreportdtlnewSql;

import com.akranta.tpm.dao.sql.GenTlNearmissreportmstnewSql;
import com.akranta.tpm.exportreport.NEARMISSITCExcelTemplate;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.model.GenTlNearmissreportdtlnew;

import com.akranta.tpm.model.GenTlNearmissreportmstnew;
import com.akranta.tpm.service.NewNearMissService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

import net.sf.json.JSONObject;


public class NewNearMissServiceImpl implements NewNearMissService {
	
	
	private GenTlNearmissreportmstnewDao nearMissDao;
	private Validations validations ;
	DBActionTemplate  dbActionTemplate ;
	
	public NewNearMissServiceImpl(DBActionTemplate dbActionTemplate){
		 
		// TODO Auto-generated constructor stub
		nearMissDao = new GenTlNearmissreportmstnewDaoImpl(dbActionTemplate);
		validations = new Validations();
		this.dbActionTemplate  =dbActionTemplate ;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}


	@Override
	public GenTlNearmissreportmstnew create(
			GenTlNearmissreportmstnew genTlNearmissreportmstnew,
			GenTlNearmissreportmstnew existGenTlNearmissreportmst)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Service Impl");
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "create";
		String xmlName="NewNearmiss";
		CommonMessage.debugMsg("Validation XML Name:::::::::"+xmlName);
		validations.validate(genTlNearmissreportmstnew, xmlName, validationsFor);
		fillvalues(genTlNearmissreportmstnew,existGenTlNearmissreportmst);
		return nearMissDao.create(genTlNearmissreportmstnew);
	}


	@Override
	public GenTlNearmissreportmstnew update(
			GenTlNearmissreportmstnew genTlNearmissreportmstnew,
			GenTlNearmissreportmstnew existGenTlNearmissreportmst)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside Service Impl");
		CommonMessage.debugMsg("Inside Create");
		String validationsFor = "update";
		String xmlName="NewNearmiss";
		validations.validate(genTlNearmissreportmstnew,xmlName,validationsFor);
		fillvalues(genTlNearmissreportmstnew,existGenTlNearmissreportmst);
		return nearMissDao.update(genTlNearmissreportmstnew);
	}



	@Override
	public GenTlNearmissreportmstnew delete(
			GenTlNearmissreportmstnew oldGenTlNearmissreportmst)
			throws Exception {
		// TODO Auto-generated method stub
		GenTlNearmissreportdtlnew genTlNearmissreportdtl = new GenTlNearmissreportdtlnew();
		List<String> sqls = new ArrayList<String>();
		List<String> sqls1 = new ArrayList<String>();
		List<String> sqls2 = new ArrayList<String>();
	String keyId;
	keyId=oldGenTlNearmissreportmst.getNmrnKeyid();
		String sql1 = getDeleteDtl(keyId);
		String sql = getDeleteMst(keyId);
		String aplm=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANMST", "APLM_KEYID", "APLM_MASTERREFID", keyId);//getting action plan keyid
		String apld=dbActionTemplate.getSingleValue("GEN_TL_ACTIONPLANDTL", "APLD_KEYID", "APLD_APLM_KEYID", aplm);
		
		
		String sql3="DELETE FROM  GEN_TL_ACTIONPLANDTL WHERE APLD_KEYID='"+apld+"'";
		String sql4="DELETE FROM  GEN_TL_ACTIONPLANMST WHERE APLM_KEYID='"+aplm+"'";
		sqls.add(sql1);
		sqls.add(sql);
		sqls.add(sql3);
		sqls.add(sql4);
		
		
    	dbActionTemplate.executeStatements(sqls);
    	return oldGenTlNearmissreportmst;
	}
	
	
	public static String getDeleteMst(String keyId) {
		String sql="DELETE from " + "GEN_TL_NEARMISSREPORTMSTNEW"+" where NMRN_KEYID ='";
		sql+=keyId;
		sql+="'";
		
		return sql;
	}
	
	public static String getDeleteDtl(String keyId) {
		String sql="DELETE from " + "GEN_TL_NEARMISSREPORTDTLNEW" +" where NMUA_NMRTKEYID = '";
		sql+=keyId;
		sql+="'";
		return sql;
	}
 private void fillvalues(GenTlNearmissreportmstnew genTlNearmissreportmst,GenTlNearmissreportmstnew existGenTlNearmissreportmst) {
		String dateTime = CommonFunctions.dateTimeNow();
genTlNearmissreportmst.setNmrnCreatedon(dateTime);
genTlNearmissreportmst.setNmrnModifiedon(dateTime);

if(genTlNearmissreportmst.getNmrnModifiedby()==null)
	genTlNearmissreportmst.setNmrnModifiedby("{}");

if(genTlNearmissreportmst.getNmrnActionrecommended() == null)
	genTlNearmissreportmst.setNmrnActionrecommended("{}");

if(genTlNearmissreportmst.getNmrnApprovedby() == null)
	genTlNearmissreportmst.setNmrnApprovedby("-");

if(genTlNearmissreportmst.getNmrnTempfield1() == null)
	genTlNearmissreportmst.setNmrnTempfield1("-");


if(genTlNearmissreportmst.getNmrnTempfield2() == null)
	genTlNearmissreportmst.setNmrnTempfield2("-");

if(genTlNearmissreportmst.getNmrnTempfield3() == null)
	genTlNearmissreportmst.setNmrnTempfield3("-");

if(genTlNearmissreportmst.getNmrnTempfield4() == null)
	genTlNearmissreportmst.setNmrnTempfield4("-");

if(genTlNearmissreportmst.getNmrnTempfield5() == null)
	genTlNearmissreportmst.setNmrnTempfield5("-");

if(genTlNearmissreportmst.getNmrnActive() == null)
	genTlNearmissreportmst.setNmrnActive("Y");

if(genTlNearmissreportmst.getNmrnDeptid() == null)
	genTlNearmissreportmst.setNmrnDeptid("{}");

if(genTlNearmissreportmst.getNmrnDescnearmiss() == null)
	genTlNearmissreportmst.setNmrnDescnearmiss("{}");

if(genTlNearmissreportmst.getNmrnEmployeeid() == null)
	genTlNearmissreportmst.setNmrnEmployeeid("{}");

if(genTlNearmissreportmst.getNmrnIdentifiedby() == null)
	genTlNearmissreportmst.setNmrnIdentifiedby("{}");

if(genTlNearmissreportmst.getNmrnOccurrencedatetime() == null)
	genTlNearmissreportmst.setNmrnOccurrencedatetime(dateTime);

if(genTlNearmissreportmst.getNmrnProbablerecrate() ==  null)
	genTlNearmissreportmst.setNmrnProbablerecrate("{}");

if(genTlNearmissreportmst.getNmrnResponsibility() ==  null)
	genTlNearmissreportmst.setNmrnResponsibility("{}");

if(genTlNearmissreportmst.getNmrnSeveritypotentialid() ==  null)
	genTlNearmissreportmst.setNmrnSeveritypotentialid("{}");

if(genTlNearmissreportmst.getNmrnTargetdate()==null)
	genTlNearmissreportmst.setNmrnTargetdate(dateTime);

if(genTlNearmissreportmst.getNmrnRemarks() == null)
	genTlNearmissreportmst.setNmrnRemarks("-");

if(genTlNearmissreportmst.getNmrnStatus() == null)
	genTlNearmissreportmst.setNmrnStatus("P");

//if(genTlNearmissreportmst.getNmrnCompletedby() == null)
	genTlNearmissreportmst.setNmrnCompletedby("{}");

if(genTlNearmissreportmst.getNmrnDatecompleted() == null)
	genTlNearmissreportmst.setNmrnDatecompleted(Constants.passNullDate);


if(genTlNearmissreportmst.getNmrnCreatedby() == null)
	genTlNearmissreportmst.setNmrnCreatedby("{}");

if(genTlNearmissreportmst.getNmrnActionplanno() == null)
	genTlNearmissreportmst.setNmrnActionplanno("-");

if(genTlNearmissreportmst.getNmrnActiontaken() == null)
	genTlNearmissreportmst.setNmrnActiontaken("-");

if(genTlNearmissreportmst.getNmrnChkothersusa() == null)
	genTlNearmissreportmst.setNmrnChkothersusa("-");

if(genTlNearmissreportmst.getNmrnChkothersusc() == null)
	genTlNearmissreportmst.setNmrnChkothersusc("-");

if(genTlNearmissreportmst.getNmrnClosed() == null)
	genTlNearmissreportmst.setNmrnClosed("N");

if(genTlNearmissreportmst.getNmrnOthersusa() == null)
	genTlNearmissreportmst.setNmrnOthersusa("-");

if(genTlNearmissreportmst.getNmrnOthersusc() == null)
	genTlNearmissreportmst.setNmrnOthersusc("-");

if(genTlNearmissreportmst.getNmrnClosedby() == null)
	genTlNearmissreportmst.setNmrnClosedby("{}");

if(genTlNearmissreportmst.getNmrnCloseddate() == null)
	genTlNearmissreportmst.setNmrnCloseddate(Constants.passNullDate);

if(genTlNearmissreportmst.getNmrnRevisedtarget() == null)
	genTlNearmissreportmst.setNmrnRevisedtarget(Constants.passNullDate);

if(genTlNearmissreportmst.getNmrnWhywhyno() == null)
	genTlNearmissreportmst.setNmrnWhywhyno("-");

//if(genTlNearmissreportmst.getNmrnVerifiedby() == null) 
genTlNearmissreportmst.setNmrnVerifiedby("{}");

if(genTlNearmissreportmst.getNmrnDateverified() == null)
	genTlNearmissreportmst.setNmrnDateverified(Constants.passNullDate);  
	
	if(genTlNearmissreportmst.getNmrnVerifiedstatus() == null)
		genTlNearmissreportmst.setNmrnVerifiedstatus("N"); 

	if(genTlNearmissreportmst.getNmrnClosedRemarks() == null)
		genTlNearmissreportmst.setNmrnClosedRemarks("-"); 
	
	if(genTlNearmissreportmst.getNmrnActnCloseRemarks() == null)
		genTlNearmissreportmst.setNmrnActnCloseRemarks("-");
	
	if(genTlNearmissreportmst.getNmrnResponseRemarks() == null)
		genTlNearmissreportmst.setNmrnResponseRemarks("-"); 
	
	if(genTlNearmissreportmst.getNmrnInveRemarks() == null)
		genTlNearmissreportmst.setNmrnInveRemarks("-"); 
	
	if(genTlNearmissreportmst.getNmrnVerifiedRemarks() == null)
		genTlNearmissreportmst.setNmrnVerifiedRemarks("-"); 

if(genTlNearmissreportmst.getNmrnPrepareddatetime() == null)
	genTlNearmissreportmst.setNmrnPrepareddatetime(dateTime);

if(genTlNearmissreportmst.getNmrnInvestigation() == null)
	genTlNearmissreportmst.setNmrnInvestigation("-");

if(genTlNearmissreportmst.getNmrnInvestigationby() == null)
	genTlNearmissreportmst.setNmrnInvestigationby("{}");  
	
	if(genTlNearmissreportmst.getNmrnInvestigationdate() == null)
		genTlNearmissreportmst.setNmrnInvestigationdate(Constants.passNullDate); 

if(genTlNearmissreportmst.getNmrnFlnid()==null)
	genTlNearmissreportmst.setNmrnFlnid("{}");

}



	@Override
	public List<String[]> getNearDetailsList(CommonFilter commonFilter,
			String formType) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearDetailsList(commonFilter,formType);
	}
	
	@Override
	public List<String[]> getNearDetailsListView(CommonFilter commonFilter,
			String formType) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearDetailsListView(commonFilter,formType);
	}



	@Override
	public String UpdateClose(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.UpdateCloseDocument(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
	}



	@Override
	public String UpdateVerify(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.UpdateVerify(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
	}



	@Override
	public String UpdateResponse(String detailkeyid, String responsevalue,
			String targetdate, String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.UpdateResponse(detailkeyid,responsevalue,targetdate,verifystatus,remarks);
	}



	@Override
	public String updateActionCLosure(String detailkeyid, String responsevalue,
			String targetdate, String reviseddate, String status,
			String correctiveaction,String remarks) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.updateActionCLosure(detailkeyid,responsevalue,targetdate,reviseddate,status,correctiveaction,remarks);
	}



	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel,String loginElementid, String empId) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getElementId(loginflid,loginlevel,loginElementid,empId);
	}



	@Override
	public List<String[]> getSwitchUserDetail(String empId) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getSwitchUserDetail(empId);
	}



	@Override
	public List<String[]> getNearMissEmpMailIds(String type, String flid,String Location) throws NoDataFoundException, Exception {
		return nearMissDao.getNearMissEmpMailIds(type,flid,Location);
	}



/*	@Override
	public String UpdateInvest(String repsonse, String targetdate,
			String probable, String invest, String recomd,String keyid) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getUpdateInvest(repsonse,targetdate,probable,invest,recomd,keyid);
	}*/



	@Override
	public GenTlNearmissreportmstnew getGridData(String keyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		GenTlNearmissreportmstnew genTlNearmissreportmstnew=new GenTlNearmissreportmstnew();
		GenTlNearmissreportmstnewSql genTlNearmissreportmstnewSql=new GenTlNearmissreportmstnewSql();
		String sql="SELECT * FROM GEN_TL_NEARMISSREPORTMSTNEW WHERE NMRN_KEYID=?";
		CommonMessage.debugMsg("sql:::"+sql);
		Object args[]=new Object[]{keyid};
		genTlNearmissreportmstnew.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return genTlNearmissreportmstnew;
	}

	@Override
	public List<String[]> getActionPlankeyid(String keyid) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
StringBuffer sql =new StringBuffer();
		
List<String []> userDatas=null;
      if(keyid!=null)
      {
		sql.append("SELECT APLM_KEYID,APLD_KEYID from GEN_TL_NEARMISSREPORTMSTNEW,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL where  APLM_MASTERREFID= NMRN_KEYID ");
		sql.append("AND APLM_KEYID = APLD_APLM_KEYID AND APLD_RESPONSIBILITY= NMRN_RESPONSIBILITY ");
		sql.append("AND APLD_TARGETDATE= NMRN_TARGETDATE AND NMRN_KEYID='"+keyid+"'");
		
		
		
		
		Object [] args = {} ;
		
		CommonMessage.debugMsg("sql:::"+sql.toString());
		
		userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		
		CommonMessage.debugMsg("userDatas:::"+userDatas);
      }
		return userDatas;
	}

	@Override
	public List<String[]> getNearMiss(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearMiss(masterKeyid);
	}

	@Override
	public List<String[]> getNearAct(String masterKeyid) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearAct(masterKeyid);
	}


	
	

	@Override
	public GenTlNearmissreportdtlnew createdtl(GenTlNearmissreportdtlnew newGenTlNearmissreportdtl,
			GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
    GenTlNearmissreportdtlnewSql genTlNearmissreportdtlSql=new GenTlNearmissreportdtlnewSql();
    GenTlNearmissreportdtlnew genTlNearmissreportdtl=new GenTlNearmissreportdtlnew();
		CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+newGenTlNearmissreportdtl.getNmuaCode());
		CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+newGenTlNearmissreportdtl.getNmuaNearkeyid());
		CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+genTlNearmissreportmstnew);
			//fillNearmissreportdtlValues(newGenTlNearmissreportdtl,genTlNearmissreportmstnew);
				
		          genTlNearmissreportdtl.setNmuaCode(newGenTlNearmissreportdtl.getNmuaCode());
		          genTlNearmissreportdtl.setNmuaNearkeyid(newGenTlNearmissreportdtl.getNmuaNearkeyid());
		          genTlNearmissreportdtl.setNmuaNmrtkeyid(genTlNearmissreportmstnew.getNmrnKeyid()); 
		          genTlNearmissreportdtl.setNmuaActive("Y");
				  genTlNearmissreportdtl.setNmuaCreatedon(CommonFunctions.dateTimeNow());
				  genTlNearmissreportdtl.setNmuaModifiedon(CommonFunctions.dateTimeNow());
				  genTlNearmissreportdtl.setNmuaTempfield1("-");
				  genTlNearmissreportdtl.setNmuaTempfield2("-");
			      genTlNearmissreportdtl.setNmuaTempfield3("-");
			      genTlNearmissreportdtl.setNmuaTempfield4("-");
			      genTlNearmissreportdtl.setNmuaCreatedby(newGenTlNearmissreportdtl.getNmuaCreatedby());
		CommonMessage.debugMsg("AFTER FILL VALUES");
		
				genTlNearmissreportdtl.setNmuaKeyid(dbActionTemplate.getSequenceNumber(GenTlNearmissreportdtlnewSql.TBL_GEN_TL_NEARMISSREPORTDTLNEW, 10, "NMU" ,"YYMM" ,"Y")); // set the sequnce number 
				sqls.add(GenTlNearmissreportdtlnewSql.getInsertSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray())); // add insert sql for master table

				dbActionTemplate.executeStatements(sqls);
			return genTlNearmissreportdtl;
			



}
	private GenTlNearmissreportdtlnew fillNearmissreportdtlValues(GenTlNearmissreportdtlnew newGenTlNearmissreportdtl,GenTlNearmissreportmstnew genTlNearmissreportmst) {
		CommonMessage.debugMsg("Inside DAO..fillNearmissreportdtlValues..");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaNmrtkeyid()))
			newGenTlNearmissreportdtl.setNmuaNmrtkeyid(genTlNearmissreportmst.getNmrnKeyid());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaActive()))
			newGenTlNearmissreportdtl.setNmuaActive("Y");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaCreatedon()))
			newGenTlNearmissreportdtl.setNmuaCreatedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaModifiedon()))
			newGenTlNearmissreportdtl.setNmuaModifiedon(CommonFunctions.dateTimeNow());
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield1()))
				newGenTlNearmissreportdtl.setNmuaTempfield1("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield2()))
				newGenTlNearmissreportdtl.setNmuaTempfield2("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield3()))
				newGenTlNearmissreportdtl.setNmuaTempfield3("-");
		if(!UIUtils.isValidKeyId(newGenTlNearmissreportdtl.getNmuaTempfield4()))
				newGenTlNearmissreportdtl.setNmuaTempfield4("-");
		return newGenTlNearmissreportdtl;
	}

	@Override
	public String getDetailid(String nmrnKeyid) throws SQLException {
		// TODO Auto-generated method stub
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NMRTKEYID", "NMUA_NMRTKEYID", nmrnKeyid);
		return keyid;
	}

	@Override
	public GenTlNearmissreportdtlnew Updatedtl(GenTlNearmissreportdtlnew newGenTlNearmissreportdtlact,
			GenTlNearmissreportmstnew genTlNearmissreportmstnew) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	    GenTlNearmissreportdtlnewSql genTlNearmissreportdtlSql=new GenTlNearmissreportdtlnewSql();
	    GenTlNearmissreportdtlnew genTlNearmissreportdtl=new GenTlNearmissreportdtlnew();
			CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+newGenTlNearmissreportdtlact.getNmuaCode());
			CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+newGenTlNearmissreportdtlact.getNmuaNearkeyid());
			CommonMessage.debugMsg("newGenTlNearmissreportdtl::"+genTlNearmissreportmstnew);
				//fillNearmissreportdtlValues(newGenTlNearmissreportdtl,genTlNearmissreportmstnew);
			/*    if(newGenTlNearmissreportdtlact.getNmuaCode().equals("UA"))
			    {
			    	String actid=getDetailActid(genTlNearmissreportmstnew.getNmrnKeyid());
			    	genTlNearmissreportdtl.setNmuaKeyid(actid);
			    	genTlNearmissreportdtl.setNmuaCode("UA");
			    	genTlNearmissreportdtl.setNmuaNmrtkeyid(genTlNearmissreportmstnew.getNmrnKeyid()); 
			    	genTlNearmissreportdtl.setNmuaNearkeyid(newGenTlNearmissreportdtlact.getNmuaNearkeyid());
			    }
			    
			    if(newGenTlNearmissreportdtlact.getNmuaCode().equals("UC"))
			    {
			    	String conid=getDetailConid(genTlNearmissreportmstnew.getNmrnKeyid());
			    	genTlNearmissreportdtl.setNmuaKeyid(conid);
			        genTlNearmissreportdtl.setNmuaCode("UC");
			        genTlNearmissreportdtl.setNmuaNmrtkeyid(genTlNearmissreportmstnew.getNmrnKeyid());
			        genTlNearmissreportdtl.setNmuaNearkeyid(newGenTlNearmissreportdtlact.getNmuaNearkeyid());
			    }
			
			          
			          
			          
			          genTlNearmissreportdtl.setNmuaActive("Y");
					  genTlNearmissreportdtl.setNmuaCreatedon(CommonFunctions.dateTimeNow());
					  genTlNearmissreportdtl.setNmuaModifiedon(CommonFunctions.dateTimeNow());
					  genTlNearmissreportdtl.setNmuaTempfield1("-");
					  genTlNearmissreportdtl.setNmuaTempfield2("-");
				      genTlNearmissreportdtl.setNmuaTempfield3("-");
				      genTlNearmissreportdtl.setNmuaTempfield4("-");
				      genTlNearmissreportdtl.setNmuaCreatedby(newGenTlNearmissreportdtlact.getNmuaCreatedby());
			CommonMessage.debugMsg("AFTER FILL VALUES");
			
				sqls.add(GenTlNearmissreportdtlnewSql.getUpdateSql(genTlNearmissreportdtlSql.getNmuaDbFields(), genTlNearmissreportdtl.getSaveArray())); */// add insert sql for master table
             //  dbActionTemplate.executeStatements(sqls);
			List<String> sqls3 = new ArrayList<String>();
			StringBuffer sql2 = new StringBuffer();
			StringBuffer sql3 = new StringBuffer();
			if(newGenTlNearmissreportdtlact.getNmuaNearkeyid()!=null)
			{
				sql2.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+newGenTlNearmissreportdtlact.getNmuaNearkeyid()+"'");
				sql2.append(",NMUA_CODE='"+newGenTlNearmissreportdtlact.getNmuaCode()+"'"+" WHERE NMUA_NMRTKEYID='"+genTlNearmissreportmstnew.getNmrnKeyid()+"'"); 
				sqls.add(sql2.toString());
				 CommonMessage.debugMsg("SQLS::"+sqls);
				 sql3.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+newGenTlNearmissreportdtlact.getNmuaTempfield1()+"'");
					sql3.append(",NMUA_CODE='"+"UC"+"'"+" WHERE NMUA_NMRTKEYID='"+genTlNearmissreportmstnew.getNmrnKeyid()+"'"); 
					sqls3.add(sql3.toString());
				dbActionTemplate.executeStatements(sqls);
				dbActionTemplate.executeStatements(sqls3);
			}
			
			
			
				return genTlNearmissreportdtl;
	}

	private String getDetailConid(String nmrnKeyid)throws SQLException {
		// TODO Auto-generated method stub
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_KEYID", "NMUA_NMRTKEYID", nmrnKeyid);
		return keyid;
	}

	private String getDetailActid(String nmrnKeyid) throws SQLException {
		// TODO Auto-generated method stub
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_KEYID", "NMUA_NMRTKEYID", nmrnKeyid);
		return keyid;
	}

	@Override
	public String getNearActid(String detailkeyid) throws SQLException {
		// TODO Auto-generated method stub
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NEARKEYID", "NMUA_NMRTKEYID", detailkeyid,"NMUA_CODE='"+"UA"+"'");
		CommonMessage.debugMsg("keyid"+keyid);
		return keyid;
	}

	@Override
	public String getNearCondid(String detailkeyid) throws SQLException {
		// TODO Auto-generated method stub
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NEARKEYID", "NMUA_NMRTKEYID", detailkeyid,"NMUA_CODE='"+"UC"+"'");
		//String keyid1=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NEARKEYID", "NMUA_NMRTKEYID", detailkeyid,"NMUA_CODE='"+"UA"+"'");
		
		return keyid;
	}

	@Override
	public Workbook NearMissExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearMissExportExcel(commonFilter,tblJSONObj,format,formtype);
	}
	
	@Override
	public Workbook NearMissViewExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,
			String formtype) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearMissViewExportExcel(commonFilter,tblJSONObj,format,formtype);
	}

	@Override
	public String UpdateInvest(String repsonse, String targetdate, String probable, String invest, String recomd,
			String keyid, String chko, String chks, String oa, String os,String remarks,String empid)
					throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getUpdateInvest(repsonse,targetdate,probable,invest,recomd,keyid,chko,chks,oa,os,remarks,empid);
	}

	@Override
	public String getActid(String nmrnKeyid, String string) throws SQLException {
		// TODO Auto-generated method stub
		String condsql=" NMUA_CODE='UA'";
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NEARKEYID", "NMUA_NMRTKEYID", nmrnKeyid,condsql);
		return keyid;
	}
	public String getCondid(String nmrnKeyid, String string) throws SQLException {
		// TODO Auto-generated method stub
		String condsql=" NMUA_CODE='UC'";
		String keyid=dbActionTemplate.getSingleValue("GEN_TL_NEARMISSREPORTDTLNEW", "NMUA_NEARKEYID", "NMUA_NMRTKEYID", nmrnKeyid,condsql);
		return keyid;
	}

	@Override
	public String getNearMissActUpdate(String keyid, String con, String act) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
	
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		List<String> sqls1 = new ArrayList<String>();
		StringBuffer sql3 = new StringBuffer();
		
		
			sql2.append("delete from GEN_TL_NEARMISSREPORTDTLNEW WHERE NMUA_NMRTKEYID='"+keyid+"'");
			//sql2.append(",NMUA_CODE='"+"UA"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			sqls.add(sql2.toString());
			//sql3.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+con+"'");
			//sql3.append(",NMUA_CODE='"+"UC"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			//sqls.add(sql3.toString());
		
			dbActionTemplate.executeStatements(sqls);
		
		/*	List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		List<String> sqls1 = new ArrayList<String>();
		StringBuffer sql3 = new StringBuffer();
		if(act!=null)
		{
			sql2.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+act+"'");
			sql2.append(",NMUA_CODE='"+"UA"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			sqls.add(sql2.toString());
			sql3.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+con+"'");
			sql3.append(",NMUA_CODE='"+"UC"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			sqls.add(sql3.toString());
		
			//dbActionTemplate.executeStatements(sqls1);
		
			dbActionTemplate.executeStatements(sqls);
		}
		/*List<String> sqls1 = new ArrayList<String>();
		StringBuffer sql3 = new StringBuffer();
		if(con!=null)
		{
			sql3.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+con+"'");
			sql3.append(",NMUA_CODE='"+"UC"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			sqls1.add(sql3.toString());
		
			dbActionTemplate.executeStatements(sqls1);
		}*/
		
			return "SUCCESS";
	}

	@Override
	public String getNearMissConUpdate(String keyid, String string, String con) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		if(con!=null)
		{
			sql2.append("UPDATE GEN_TL_NEARMISSREPORTDTLNEW SET NMUA_NEARKEYID='"+con+"'");
			sql2.append(",NMUA_CODE='"+"UC"+"'"+" WHERE NMUA_NMRTKEYID='"+keyid+"'"); 
			sqls.add(sql2.toString());
		
			dbActionTemplate.executeStatements(sqls);
		}
			return "SUCCESS";
	}

	public Workbook newnearmissExcelView(String keyid, String flid, String path)
			throws Exception {
		// TODO Auto-generated method stub

		List<String[]> nearmissData = nearMissDao.getfillnewnearmissdata(keyid,flid);
		List<String[]> unsafeactData = nearMissDao.getfillnewunsafeactdata(keyid,flid);
		List<String[]> unsafeconditionData = nearMissDao.getfillnewunsafeconditiondata(keyid,flid);
		
		Workbook wb=(new NEARMISSITCExcelTemplate(dbActionTemplate)).fillValues(nearmissData,unsafeactData,unsafeconditionData,path);
		
		return wb;
	}

	@Override
	public List<String[]> getNearMissReleatedFileManager(String momKeyId) throws Exception {
		// TODO Auto-generated method stub
		return nearMissDao.getNearMissReleatedFileManager(momKeyId);
	}


}