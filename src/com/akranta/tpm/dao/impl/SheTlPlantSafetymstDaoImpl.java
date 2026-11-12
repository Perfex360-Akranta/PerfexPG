package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.SheTlPlantSafetymstDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.AbnTlAbnormalitySql;
//import com.akranta.tpm.dao.sql.EntTlTrainingNeedindenfymstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
//import com.akranta.tpm.dao.sql.MocRfcmstSql;
import com.akranta.tpm.dao.sql.SheTlPlantSafetydtlSql;
import com.akranta.tpm.dao.sql.SheTlPlantSafetymstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.AbnTlAbnormality;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.model.SheTlPlantSafetydtl;
import com.akranta.tpm.model.SheTlPlantSafetymst;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.MomServiceApi;
//import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.SheTlPlantSafetyServiceApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;

import net.sf.json.JSONObject;

public class SheTlPlantSafetymstDaoImpl implements SheTlPlantSafetymstDao{
	private DBActionTemplate dbActionTemplate; 
	private SheTlPlantSafetymstSql sheTlPlantSafetymstSql;
	private AbnTlAbnormalitySql abnTlAbnormalitySql =  null;
	private SheTlPlantSafetyServiceApi sheTlPlantSafetyServiceApi;
	FunctionCallApi fnCallApi;
	
	public SheTlPlantSafetymstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		sheTlPlantSafetymstSql=new SheTlPlantSafetymstSql ();
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void SheTlPlantSafetymstDaoImplJwt(String JwtToken) 
	{
		try{
			sheTlPlantSafetyServiceApi = new SheTlPlantSafetyServiceApi(JwtToken);
		     fnCallApi = new FunctionCallApi(JwtToken);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Override
	public SheTlPlantSafetymst create(SheTlPlantSafetymst sheTlPlantSafetymst)throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SheTlPlantSafetymstSql sheTlPlantSafetymstSql=new SheTlPlantSafetymstSql();
		try{
			sheTlPlantSafetymst.setSpsmKeyid(dbActionTemplate.getSequenceNumber(SheTlPlantSafetymstSql.TBL_SHE_TL_PLANTSAFETYMST, 12, "EHSM", "DDMM", "Y"));
			/*	String elementId = sheTlPlantSafetymst.getSpsmKeyid();
			String seqIdentfi =com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,SheTlPlantSafetymstSql.TBL_SHE_TL_PLANTSAFETYMST);
			if(sheTlPlantSafetymst.getSpsmType().equals("DMT")){
			sheTlPlantSafetymst.setSpsmKeyid(dbActionTemplate.getSequenceNumber(SheTlPlantSafetymstSql.TBL_SHE_TL_PLANTSAFETYMST, 12, "UNIT", "DDMM", "Y"));
			}else if(sheTlPlantSafetymst.getSpsmType().equals("MGR")){
				sheTlPlantSafetymst.setSpsmKeyid(dbActionTemplate.getSequenceNumber(SheTlPlantSafetymstSql.TBL_SHE_TL_PLANTSAFETYMST, 12, "DMTM", "DDMM", "Y"));
			}else if(sheTlPlantSafetymst.getSpsmType().equals("UH")){
				sheTlPlantSafetymst.setSpsmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi));//,12,"EHSM", "DDMM", "")); // set the sequnce number
				
			} */
			sqls.add(SheTlPlantSafetymstSql.getInsertSql(sheTlPlantSafetymstSql.getspsmDbFields(), sheTlPlantSafetymst.getSaveArray())); // add insert sql for master table
			insertAuditDtl(sheTlPlantSafetymst,sqls);
			dbActionTemplate.executeStatements(sqls);
			 
			}catch (ValidationExceptions e){
			throw new ValidationExceptions(e.getMessage());
			}	
			catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage());
			}catch( Exception e){
			throw new Exception(e.getMessage());
			}	
		return sheTlPlantSafetymst;
	}
	private List<String> insertAuditDtl(SheTlPlantSafetymst sheTlPlantSafetymst,List<String> sqls) throws Exception {
		SheTlPlantSafetymstSql sheTlPlantSafetymstSql = new SheTlPlantSafetymstSql();
		SheTlPlantSafetydtlSql sheTlPlantSafetydtlSql = new SheTlPlantSafetydtlSql();
		
		if(sheTlPlantSafetymst.getSafetyDetails()!= null && sheTlPlantSafetymst.getSafetyDetails().size()>0) // check for detail table data
		{
			for(int i =0;i<sheTlPlantSafetymst.getSafetyDetails().size();i++)
			{	
				SheTlPlantSafetydtl sheTlPlantSafetydtl = (SheTlPlantSafetydtl)sheTlPlantSafetymst.getSafetyDetails().get(i); // get detail info from list in empployee object
				String PSITYPE=sheTlPlantSafetydtl.getSpsdTempfield2();
				System.out.println("psitype in :::::::"+PSITYPE);
				if(sheTlPlantSafetydtl.getSpsdCreatedby()==null){
					sheTlPlantSafetydtl.setSpsdCreatedby(sheTlPlantSafetymst.getSpsmCreatedby());
				}
				if(sheTlPlantSafetydtl.getSpsdCreatedon()==null){
					sheTlPlantSafetydtl.setSpsdCreatedon(sheTlPlantSafetymst.getSpsmCreatedon());
				}
				if(sheTlPlantSafetydtl.getSpsdModifiedon()==null){
					sheTlPlantSafetydtl.setSpsdModifiedon(sheTlPlantSafetymst.getSpsmModifiedon());
				}
				
				sheTlPlantSafetydtl.setSpsdSpsmKeyid(sheTlPlantSafetymst.getSpsmKeyid());	
				if(!CommonFunctions.isValidKeyId(sheTlPlantSafetydtl.getSpsdKeyid())){
					sheTlPlantSafetydtl.setSpsdKeyid(dbActionTemplate.getSequenceNumber(sheTlPlantSafetydtlSql.TBL_SHE_TL_PLANTSAFETYDTL, 12, "SPSD", "DDMM", "Y"));
					sqls.add(SheTlPlantSafetydtlSql.getInsertSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray()));// add insert sql for detail table
				}else{
					sqls.add(SheTlPlantSafetydtlSql.getUpdateSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray()));
				}
			}
		}
		return sqls;
	}
	@Override
	public SheTlPlantSafetymst update(SheTlPlantSafetymst sheTlPlantSafetymst) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		SheTlPlantSafetymstSql sheTlPlantSafetymstSql=new SheTlPlantSafetymstSql();
		try {
			sqls.add(SheTlPlantSafetymstSql.getUpdateSql(sheTlPlantSafetymstSql.getspsmDbFields(), sheTlPlantSafetymst.getSaveArray()));
			insertAuditDtl(sheTlPlantSafetymst,sqls);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return sheTlPlantSafetymst;
	}
		
	@Override
	public SheTlPlantSafetymst delete(SheTlPlantSafetymst sheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		SheTlPlantSafetymstSql sheTlPlantSafetymstSql=new SheTlPlantSafetymstSql();
		if(UIUtils.isValidKeyId(sheTlPlantSafetydtl.getSpsdKeyid()))
		{ 
		List<String> sql = new ArrayList<String>();
		SheTlPlantSafetydtlSql sheTlPlantSafetydtlSql=new SheTlPlantSafetydtlSql();
		sql.add(sheTlPlantSafetydtlSql.getDtlsDeleteSql(sheTlPlantSafetydtlSql.getSpsdDbFields(), sheTlPlantSafetydtl.getSaveArray()));
		}
		try {	
			sqls.add(sheTlPlantSafetymstSql.getDeleteDtlsSql(sheTlPlantSafetymstSql.getspsmDbFields(), sheTlPlantSafetymst.getSaveArray()));
			sqls.add(sheTlPlantSafetymstSql.getDeleteSql(sheTlPlantSafetymstSql.getspsmDbFields(), sheTlPlantSafetymst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		}	
		catch (BusinessApplicationExceptions e){			
			throw new BusinessApplicationExceptions(e.getMessage()+",FK_SPSD_KEYID");
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return sheTlPlantSafetymst;

	}

	@Override
	public List<String[]> getSafetyDetailsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			if(CommonFunctions.isValidKeyId(commonFilter.getSafetyMode())){
				condParms +="MODE="+commonFilter.getSafetyMode()+";";
				System.out.println("commonFilter.getSafetyMode() in modify::"+commonFilter.getSafetyMode());
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getShe())){
				condParms +="EMPID="+commonFilter.getShe()+";";
				System.out.println("commonFilter.getSafetyMode() in modify::"+commonFilter.getShe());
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getStatus())){
				condParms +="TYPE="+commonFilter.getStatus()+";";
				System.out.println("commonFilter.getStatus() in modify::"+commonFilter.getStatus());
			}
			if(CommonFunctions.isValidKeyId(commonFilter.getEmpch())){
				condParms +="EMPROLE="+commonFilter.getEmpch()+";";
				System.out.println("commonFilter.getetEmpch() in modify::"+commonFilter.getEmpch());
			}
			
			if(commonFilter.getEmpch().equals("DMT MEMBER"))
			{
				condParms +="PSITYPE="+"DM"+";";
			}
			else if(commonFilter.getEmpch().equals("UNIT HEAD"))
			{
				condParms +="PSITYPE="+"UH"+";";
			}
			if(commonFilter.getEmpch().equals("UNIT EHS MANAGER"))
			{
				condParms +="PSITYPE="+"EM"+";";
			}
			//commonFilter.setRoleLevel(rolename);
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			//dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_PLANTSAFETYMSTLIST", paramValues);
			dataList =  fnCallApi.callFunction("HSE_FN_PLANTSAFETYMSTLIST_SB", paramValues,2,true);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid,String empid) throws NoDataFoundException, Exception {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer();
		sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
		if(UIUtils.isValidKeyId(loginflid))
		sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
		sql.append(" AND FRT_EMPM_KEYID = '"+ empid +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
		Object [] args = {} ;
		List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		return userDatas;
	}
	@Override
	public List<String[]> safetyPopup(CommonFilter commonFilter)throws Exception {
		try
		{                          
			String sql = SheTlPlantSafetymstSql.safetyPopup(commonFilter);
			
			System.out.println("mano========================================"+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public 	List<String[]> getExistAuditRecord(String combotype, String flId, String datevalue) throws Exception {
		StringBuffer sql = new StringBuffer();
		List<String> sqls = new ArrayList<String>();
		sql.append(" SELECT COUNT(*) FROM  SHE_TL_PLANTSAFETYMST WHERE " );  
	    sql.append(" SPSM_DATE = '" + datevalue  + "' " ); 
		sql.append(" AND SPSM_TYPE = '" + combotype  + "' " ); 
		sql.append(" AND SPSM_FLID = '" + flId  + "' " ); 
		sqls.add(sql.toString());
		 Object [] args = {} ;
		 
		System.out.println("mano2========================="+sqls);
		 
			List<String[]> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
			return userDatas;
			}

	@Override
	public List<String[]> resupdate(String dtlid,String Resid,String targetdate)throws Exception {
		try
		{                          
			String sql = SheTlPlantSafetydtlSql.resupdate(dtlid,Resid,targetdate);			
			System.out.println("Res&Audit Sql "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String UpdateVerfification(String detailkeyid, String responsevalue, String targetdate,String verifystatus,String remarks) throws BusinessApplicationExceptions, Exception {

		List<String> sqls = new ArrayList<String>();
		StringBuffer sql2 = new StringBuffer();
		if(remarks==null||remarks.length()==0)
		{
			remarks="-";
		}
		
		if(verifystatus.equals("Y"))
		{
			sql2.append("UPDATE SHE_TL_PLANTSAFETYDTL SET SPSD_VERIFIED='"+verifystatus+"'");
			sql2.append(",SPSD_VERIFIEDDATE='"+targetdate+"'"+",SPSD_VERIFYREMARKS='"+remarks+"'"+",SPSD_VERIFIEDBY='"+responsevalue+"'"+"WHERE SPSD_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql2.toString());
			 System.out.println("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		return "SUCCESS";
	}
	
	
	@Override
	public String updateActionClosure(String detailkeyid, String responsevalue, String targetdate, String revisedate,String status,String correctiveaction,String remarks,String abnKeyid) throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		System.out.println("targetdate"+targetdate);
		if(remarks==null||remarks.length()==0)
		{
			remarks="-";
		}
		if(status.equals("C"))
		{
			if(UIUtils.isValidKeyId(abnKeyid)&&abnKeyid!="X"){
				sql2.append(" UPDATE ABN_TL_ABNORMALITY SET abnm_status='C',ABNM_COMPLETEDBY='"+responsevalue+"',abnm_wostarttime='"+targetdate+"',abnm_woendtime='"+targetdate+"',ABNM_REMARKS='"+remarks+"' WHERE ABNM_KEYID='"+abnKeyid+"'");
			}
			
			sql1.append(" UPDATE SHE_TL_PLANTSAFETYDTL SET SPSD_COMPLETEDBY='"+responsevalue+"'");
			sql1.append(",SPSD_COMPLETEDDATE='"+targetdate+"'"+",SPSD_STATUS='"+status+"'"+",SPSD_COMPLETEREMARKS='"+remarks+"'"+",SPSD_CORRECACTION='"+correctiveaction+"'"+"WHERE SPSD_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql1.toString());
			sqls.add(sql2.toString());
			System.out.println("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		else
		{
			sql1.append(" UPDATE SHE_TL_PLANTSAFETYDTL SET SPSD_STATUS='"+status+"'");
			sql1.append(",SPSD_REVISEDDATE='"+revisedate+"'"+",SPSD_COMPLETEREMARKS='"+remarks+"'"+"WHERE SPSD_KEYID='"+detailkeyid+"'"); 
			sqls.add(sql1.toString());
			 System.out.println("SQLS::"+sqls);
			dbActionTemplate.executeStatements(sqls);
		}
		return "SUCCESS";
	}
	@Override
	public List<String> getCheckDeleteList(String detailkeyid,String Apmdelete) throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		List<String> sqls2 = new ArrayList<String>();
		List<String> sqls3 = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();
		StringBuffer sql2 = new StringBuffer();
		StringBuffer sql3 = new StringBuffer();
		String modiefiedid=detailkeyid.replaceAll("\"", "'");
	if(detailkeyid!=null)
		{
		
		sql1.append(" DELETE FROM SHE_TL_PLANTSAFETYDTL");
		sql1.append(" WHERE SPSD_KEYID in (" + modiefiedid + ") ");	
		sqls.add(sql1.toString());
			 
		
			 
			dbActionTemplate.executeStatements(sqls);
			
			
		
		}
	if(Apmdelete!=null)
	{
		String deletedid=Apmdelete.replaceAll("\"", "'");
		sql2.append(" DELETE FROM GEN_TL_ACTIONPLANDTL");
		sql2.append(" WHERE APLD_KEYID in (" + deletedid + ") ");	
		sqls2.add(sql2.toString());
		sql3.append(" DELETE FROM GEN_TL_ACTIONPLANMST");
		sql3.append(" WHERE APLM_DETAILREFID in (" + modiefiedid + ") ");	
		sqls3.add(sql3.toString());
			 
		
			 System.out.println("sqls2::"+sqls2);
			dbActionTemplate.executeStatements(sqls2);
			dbActionTemplate.executeStatements(sqls3);
	}
	return sqls;
}
	@Override
	public List<String[]> verifyupdate(String dtlid,String Verid,String verifydate)throws Exception {
		try
		{                          
			String sql = SheTlPlantSafetydtlSql.verifyupdate(dtlid,Verid,verifydate);			
			System.out.println("Res&Audit Sql "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<String[]> getSafetygridDetail(CommonFilter commonFilter) throws Exception {
	 
		/*try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = "";
			if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
				condParms="SDAM_KEYID="+commonFilter.getKey()+";";
				System.out.println("commonFilter.getKey()::"+commonFilter.getKey());
			}
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("TEST_PC_TEST3.HSE_FN_PLANTSAFETYDTLGRID", paramValues); //TEST_PC_TEST3.HSE_FN_PLANTSAFETYDTLGRID
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if( isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
 	} */
	
		try
		{
		List<String> paramValues = new ArrayList<String>();
		
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms +="SDAM_KEYID="+commonFilter.getKey()+";";
			System.out.println("commonFilter.getKey()::"+commonFilter.getKey());
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getType())){
			condParms +="TYPE="+commonFilter.getType()+";";
			System.out.println("commonFilter.getType()::"+commonFilter.getType());
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getSafetyMode())){
			condParms +="MODE="+commonFilter.getSafetyMode()+";";
			System.out.println("commonFilter.getSafetyMode()::"+commonFilter.getSafetyMode());
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getAuditFromDate())){
			condParms +="AUDITDATE="+commonFilter.getAuditFromDate()+";";
			System.out.println("getAuditFromDate()::"+commonFilter.getAuditFromDate());
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms +="FLID="+commonFilter.getFlid()+";";
			System.out.println("commonFilter.getFlid()::"+commonFilter.getFlid());
		}
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		//dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_PLANTSAFETYDTLLIST", paramValues);
		
		dataList =  fnCallApi.callFunction("HSE_FN_PLANTSAFETYDTLLIST_SB", paramValues,3,false);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonFunctions.debugMsg("totalCnt....."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
		}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
	}


	}
	@Override
	public Workbook getAuditExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format,String type,String formmode) throws Exception {
	
		 ResultSet rs = null;
		   try
		   {
			   	rs =   getAuditReportResultSet(commonFilter,type,formmode);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormat.setFontHeightPoint((short)8);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setDbChkColIndx(3);
				condFormat.setFromCol(2);
				condFormat.setToCol(2);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("RED"); //Tick
				condFormat.setIdentfier("RED");
				//condFormat.setBgColor(new RGB(213,255,195));
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
					
				return excelUtils.writeToExcel(rs,format,2, 0,0 );
				
			
	}
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }


}
	private ResultSet getAuditReportResultSet(CommonFilter commonFilter, String type, String formmode) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		List<String[]> relatedMst = null;
		String condParams = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		condParams+="MODE="+formmode+";";
		condParams+="TYPE="+type+";";
		condParams+="EMPID="+commonFilter.getEmpch()+";";
	
		paramValues.add(condParams);
		paramValues.add(commonParams);
		return  dbActionTemplate.NewdbFunctionCall2("HSE_FN_PLANTSAFETYMSTLIST", paramValues);
		
	}
	@Override
	public SheTlPlantSafetymst getvaluesmst(String keyid) throws Exception {
		// TODO Auto-generated method stub
		SheTlPlantSafetymst  newSheTlPlantSafetymst=new SheTlPlantSafetymst();
		String sql =SheTlPlantSafetymstSql.getSelectSql();
		CommonFunctions.debugMsg(keyid+" sql   "+sql);
		Object args [] = new Object [] {keyid};
		newSheTlPlantSafetymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return newSheTlPlantSafetymst;
	}
	
	@Override
	public List<String[]> deletedtl(String dtlid) throws Exception {
		SheTlPlantSafetydtl  sheTlPlantSafetydtl=new SheTlPlantSafetydtl();
		String sql =SheTlPlantSafetydtlSql.getDeletedtlSql(dtlid);
		return dbActionTemplate.getDataList(sql);
	}
	

	@Override
	public String singleResponsiblty(String detailkeyid, String responsevalue, String targetdate,SheTlPlantSafetymst sheTlPlantSafetymst,SheTlPlantSafetydtl sheTlPlantSafetydtl) throws BusinessApplicationExceptions, Exception {
		
		List<String> sqls = new ArrayList<String>();
		List<String> sqls2 = new ArrayList<String>();
		StringBuffer sql1 = new StringBuffer();
		System.out.println("PSD000000671");
		if(sheTlPlantSafetydtl.getSpsdResponRemarks()==null||sheTlPlantSafetydtl.getSpsdResponRemarks().length()==0)
		{
			sheTlPlantSafetydtl.setSpsdResponRemarks("-");
		}
			if(detailkeyid!=null&&detailkeyid.length()!=0)
		{
			sql1.append(" UPDATE SHE_TL_PLANTSAFETYDTL SET SPSD_RESPONSIBILITY='"+responsevalue+"'");
			sql1.append(",SPSD_TARGETDATE='"+targetdate+"'"+",SPSD_RESPREMARKS='"+sheTlPlantSafetydtl.getSpsdResponRemarks()+"'"+"WHERE SPSD_KEYID='"+detailkeyid+"'"); 
			sqls2.add(sql1.toString());
			 System.out.println("SQLS::"+sqls2);
			dbActionTemplate.executeStatements(sqls2);
		}
		return "SUCCESS";
		
	}
	@Override
	public List<String[]> getCheck(String keyid,String auditflid,String auditdate,String type) throws Exception {
		String sql =SheTlPlantSafetymstSql.getCheckSql(keyid,auditflid,auditdate,type);
		System.out.println("getCheckSql when create : :"+sql);
		return dbActionTemplate.getDataList(sql);
	}

	@Override
	public SheTlPlantSafetydtl getvaluesdtl(String keyid) throws Exception {
		// TODO Auto-generated method stub
		SheTlPlantSafetydtl  newSheTlPlantSafetydtl=new SheTlPlantSafetydtl();
		String sql =SheTlPlantSafetydtlSql.getSelectSql();
		CommonFunctions.debugMsg(keyid+" sql   "+sql);
		Object args [] = new Object [] {keyid};
		newSheTlPlantSafetydtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return newSheTlPlantSafetydtl;
	}

	@Override
	public String getdetailvalue(String keyid) throws SQLException {
		// TODO Auto-generated method stub
		String count=dbActionTemplate.getSingleValue("SHE_TL_PLANTSAFETYDTL", "SPSD_KEYID", "SPSD_SPSM_KEYID", keyid );
	  return count;
	}

	@Override
	public List<String[]> getSafetyReportDetails(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String reponse=commonFilter.getAbnDetectBy();
			String fromdate=commonFilter.getAftFromDt();
			String todate=commonFilter.getAftToDt();
			String type=commonFilter.getAreatype();
			String status=commonFilter.getAwise();
			String role=commonFilter.getAbnAllch();
			

		
			
		System.out.println("role:::"+role+commonFilter.getAbnAllch());
			
			if(commonFilter.getAbnAllch().equals("DMT MEMBER"))
			{
				condParms +="PSITYPE="+"DM"+";";
			}
			else if(commonFilter.getAbnAllch().equals("UNIT HEAD"))
			{
				condParms +="PSITYPE="+"UH"+";";
			}
			if(commonFilter.getAbnAllch().equals("UNIT EHS MANAGER"))
			{
				condParms +="PSITYPE="+"EM"+";";
			}
			
			
			if(status!=null)
			{
			condParms+="SAFETYSTATUS="+status+";";
			}
			
			condParms+="SAFETYTYPE="+type+";";
			if(status!=null)
			{
			condParms+="SAFETYSTATUS="+status+";";
			}
			if(reponse!=null)
			{
			condParms+="RESPONSEBY="+reponse+";";
			}
			if(fromdate!=null&&todate!=null)
					{		
			condParms+="SAFETYROMSDTE="+fromdate+";";
			condParms+="SAFETYTOSDTE="+todate+";";
					}
		
			condParms+="FNLID="+commonFilter.getActionKeyId()+";";
			

			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("paramValues"+paramValues);
			System.out.println("condParms"+condParms);
			System.out.println("commonParams"+commonParams);
			List<String[]> dataList =  null;
			//dataList =  dbActionTemplate.processFunctionCalls("HSE_FN_PLANTSAFETYREPORTLIST", paramValues);
			dataList =  fnCallApi.callFunction("HSE_FN_PLANTSAFETYREPORTLIST_SB", paramValues,3,false);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonFunctions.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	/*
	 * public List<String[]> PlansafeList(String Plansafetyid,String flid)throws
	 * Exception{
	 * 
	 * StringBuilder sql=new StringBuilder();
	 * 
	 * sql.append(" SELECT SPSM_KEYID AS mstkeyid,SECT_NAME AS DMT,CELL_NAME AS JH,"
	 * ); sql.
	 * append(" DECODE(SPSM_TYPE,'UH','Unit Head','DM','DMT Member','EM','EHS Manager','-') audittype,"
	 * ); sql.
	 * append(" TO_CHAR(SPSM_DATE,'DD-MON-YYYY') auditdate,DECODE(SPSM_DONEBY,A.EMPM_KEYID,A.EMPM_NAME||'-'||A.EMPM_CODE,'-') preparedby,"
	 * ); sql.
	 * append(" SPSM_MEMBERS members,SPSM_REMARKS remarks FROM SHE_TL_PLANTSAFETYMST,gen_vw_fnln,"
	 * ); sql.
	 * append(" GEN_TL_EMPLOYEEMST A WHERE FNLN_KEYID=SPSM_FLID AND A.EMPM_KEYID=SPSM_DONEBY "
	 * ); sql.append(" AND SPSM_KEYID= '"+Plansafetyid +"' ");
	 * System.out.println("The PlanSafety Data:::"+sql.toString()); List<String[]>
	 * listData=dbActionTemplate.getDataList(sql.toString()); return listData; }
	 */
	//mano
	
	public List<String[]> PlansafeList(String Plansafetyid, String flid) throws Exception {
	    
	    StringBuilder sql = new StringBuilder();
	    
	    sql.append(" SELECT SPSM_KEYID AS mstkeyid, SECT_NAME AS DMT, CELL_NAME AS JH,");
	    sql.append(" CASE WHEN SPSM_TYPE = 'UH' THEN 'Unit Head'");
	    sql.append("      WHEN SPSM_TYPE = 'DM' THEN 'DMT Member'");
	    sql.append("      WHEN SPSM_TYPE = 'EM' THEN 'EHS Manager'");
	    sql.append("      ELSE '-' END AS audittype,");
	    sql.append(" TO_CHAR(SPSM_DATE, 'DD-Mon-YYYY') AS auditdate,");
	    sql.append(" CASE WHEN SPSM_DONEBY = A.EMPM_KEYID THEN A.EMPM_NAME || '-' || A.EMPM_CODE");
	    sql.append("      ELSE '-' END AS preparedby,");
	    sql.append(" SPSM_MEMBERS AS members, SPSM_REMARKS AS remarks");
	    sql.append(" FROM SHE_TL_PLANTSAFETYMST, gen_vw_fnln, GEN_TL_EMPLOYEEMST A");
	    sql.append(" WHERE FNLN_KEYID = SPSM_FLID AND A.EMPM_KEYID = SPSM_DONEBY");
	    sql.append(" AND SPSM_KEYID = '").append(Plansafetyid).append("'");
	    
	    System.out.println("The PlanSafety Data:::" + sql.toString());
	    List<String[]> listData = dbActionTemplate.getDataList(sql.toString());
	    return listData;
	}

	/*
	 * public List<String[]> PlansafeDetailList(String planSafetyKeyid,String
	 * flid)throws Exception{
	 * 
	 * StringBuilder sql1=new StringBuilder(); sql1.
	 * append(" SELECT SPSD_OBSERVATIONS txtSpsdObserve,DECODE(trim(SPSD_TYPE),'S','Minor','M','Medium','X','Major','-') cmbSpsdType,"
	 * ); sql1.
	 * append(" DECODE(SPSD_RELATEDTO,'E  ','Environment','H  ','Health','S  ','Safety') cmbSpsdRelatedto,"
	 * ); sql1.
	 * append(" SPSD_RECCOMACTION txtSpsdRecomAction,SPSD_CORRECACTION txtSpsdCorrectAction,"
	 * ); sql1.
	 * append(" DECODE(SPSD_RESPONSIBILITY,A.EMPM_KEYID,A.EMPM_NAME||'-'||A.EMPM_CODE,'-')  cmbSpsdResponsibilty,"
	 * ); sql1.
	 * append(" TO_CHAR(SPSD_TARGETDATE,'DD-MON-YYYY') as dteSpsdTargetDate,DECODE(SPSD_STATUS,'P','Pending','C','Completed') as cmbSpsdStatus "
	 * ); sql1.
	 * append(" FROM SHE_TL_PLANTSAFETYDTL,SHE_TL_PLANTSAFETYMST,GEN_TL_EMPLOYEEMST A "
	 * ); sql1.
	 * append(" WHERE 1=1 AND SPSM_KEYID = SPSD_SPSM_KEYID AND A.EMPM_KEYID=SPSD_RESPONSIBILITY "
	 * ); sql1.append(" AND SPSM_FLID='" +flid+ "' ");
	 * sql1.append(" AND SPSD_SPSM_KEYID= '" +planSafetyKeyid+ "' ");
	 * System.out.println("The PlanSafetyDetail Data:::"+sql1.toString());
	 * List<String[]>
	 * plansafetyDetailList=dbActionTemplate.getDataList(sql1.toString()); return
	 * plansafetyDetailList; }
	 */
	//mano 
	public List<String[]> PlansafeDetailList(String planSafetyKeyid, String flid) throws Exception {
	    
	    StringBuilder sql1 = new StringBuilder();
	    
	    sql1.append(" SELECT SPSD_OBSERVATIONS AS txtSpsdObserve,");
	    sql1.append(" CASE WHEN TRIM(SPSD_TYPE) = 'S' THEN 'Minor'");
	    sql1.append("      WHEN TRIM(SPSD_TYPE) = 'M' THEN 'Medium'");
	    sql1.append("      WHEN TRIM(SPSD_TYPE) = 'X' THEN 'Major'");
	    sql1.append("      ELSE '-' END AS cmbSpsdType,");
	    sql1.append(" CASE WHEN SPSD_RELATEDTO = 'E  ' THEN 'Environment'");
	    sql1.append("      WHEN SPSD_RELATEDTO = 'H  ' THEN 'Health'");
	    sql1.append("      WHEN SPSD_RELATEDTO = 'S  ' THEN 'Safety'");
	    sql1.append("      ELSE NULL END AS cmbSpsdRelatedto,");
	    sql1.append(" SPSD_RECCOMACTION AS txtSpsdRecomAction,");
	    sql1.append(" SPSD_CORRECACTION AS txtSpsdCorrectAction,");
	    sql1.append(" CASE WHEN SPSD_RESPONSIBILITY = A.EMPM_KEYID THEN A.EMPM_NAME || '-' || A.EMPM_CODE");
	    sql1.append("      ELSE '-' END AS cmbSpsdResponsibilty,");
	    sql1.append(" TO_CHAR(SPSD_TARGETDATE, 'DD-Mon-YYYY') AS dteSpsdTargetDate,");
	    sql1.append(" CASE WHEN SPSD_STATUS = 'P' THEN 'Pending'");
	    sql1.append("      WHEN SPSD_STATUS = 'C' THEN 'Completed'");
	    sql1.append("      ELSE NULL END AS cmbSpsdStatus");
	    sql1.append(" FROM SHE_TL_PLANTSAFETYDTL, SHE_TL_PLANTSAFETYMST, GEN_TL_EMPLOYEEMST A");
	    sql1.append(" WHERE 1=1 AND SPSM_KEYID = SPSD_SPSM_KEYID AND A.EMPM_KEYID = SPSD_RESPONSIBILITY");
	    sql1.append(" AND SPSM_FLID = '").append(flid).append("'");
	    sql1.append(" AND SPSD_SPSM_KEYID = '").append(planSafetyKeyid).append("'");
	    
	    System.out.println("The PlanSafetyDetail Data:::" + sql1.toString());
	    List<String[]> plansafetyDetailList = dbActionTemplate.getDataList(sql1.toString());
	    return plansafetyDetailList;
	}
	/*
	 * public List<String[]> PlanSafetyVerify(String Keyid,String flid)throws
	 * Exception{
	 * 
	 * StringBuilder sql2=new StringBuilder(); sql2.
	 * append(" SELECT DECODE(SPSD_COMPLETEDBY,A.EMPM_KEYID,A.EMPM_NAME||'-'||A.EMPM_CODE,'-') cmbSpsdCompletedby, "
	 * ); sql2.
	 * append(" TO_CHAR(SPSD_COMPLETEDDATE,'DD-MON-YYYY') as dteSpsdCompletedate, "
	 * ); sql2.
	 * append(" DECODE(SPSD_VERIFIEDBY,B.EMPM_KEYID,B.EMPM_NAME||'-'||B.EMPM_CODE,'-')  cmbSpsdVerifiedBy, "
	 * ); sql2.
	 * append(" TO_CHAR(SPSD_VERIFIEDDATE,'DD-MON-YYYY') as dteSpsdVerifiedDate ");
	 * sql2.
	 * append(" FROM SHE_TL_PLANTSAFETYDTL,SHE_TL_PLANTSAFETYMST,GEN_TL_EMPLOYEEMST A,GEN_TL_EMPLOYEEMST B "
	 * ); sql2.append(" WHERE 1=1 AND B.EMPM_KEYID=SPSD_VERIFIEDBY AND ");
	 * sql2.append(" SPSM_KEYID = SPSD_SPSM_KEYID AND SPSD_SPSM_KEYID='"+Keyid+"' "
	 * ); System.out.println("The PlanSafetyVerify Data:::"+sql2.toString());
	 * List<String[]> VerifyList=dbActionTemplate.getDataList(sql2.toString());
	 * return VerifyList; }
	 */
	//mano 
	public List<String[]> PlanSafetyVerify(String Keyid, String flid) throws Exception {
	    
	    StringBuilder sql2 = new StringBuilder();
	    
	    sql2.append(" SELECT CASE WHEN SPSD_COMPLETEDBY = A.EMPM_KEYID THEN A.EMPM_NAME || '-' || A.EMPM_CODE");
	    sql2.append("             ELSE '-' END AS cmbSpsdCompletedby,");
	    sql2.append(" TO_CHAR(SPSD_COMPLETEDDATE, 'DD-Mon-YYYY') AS dteSpsdCompletedate,");
	    sql2.append(" CASE WHEN SPSD_VERIFIEDBY = B.EMPM_KEYID THEN B.EMPM_NAME || '-' || B.EMPM_CODE");
	    sql2.append("      ELSE '-' END AS cmbSpsdVerifiedBy,");
	    sql2.append(" TO_CHAR(SPSD_VERIFIEDDATE, 'DD-Mon-YYYY') AS dteSpsdVerifiedDate");
	    sql2.append(" FROM SHE_TL_PLANTSAFETYDTL, SHE_TL_PLANTSAFETYMST, GEN_TL_EMPLOYEEMST A, GEN_TL_EMPLOYEEMST B");
	    sql2.append(" WHERE 1=1 AND B.EMPM_KEYID = SPSD_VERIFIEDBY");
	    sql2.append(" AND SPSM_KEYID = SPSD_SPSM_KEYID AND SPSD_SPSM_KEYID = '").append(Keyid).append("'");
	    
	    System.out.println("The PlanSafetyVerify Data:::" + sql2.toString());
	    List<String[]> VerifyList = dbActionTemplate.getDataList(sql2.toString());
	    return VerifyList;
	}
		
	public Workbook getPlantSafetyExcel(JSONObject colmodel, String rptformat,
			CommonFilter commonFilter) throws Exception {
		  ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getPlantSafetyResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName(XLConditionalFormats.FONT_DEFAULT);
				condFormat.setFontHeightPoint((short)8);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setDbChkColIndx(3);
				condFormat.setFromCol(2);
				condFormat.setToCol(2);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue("RED"); //Tick
				condFormat.setIdentfier("RED");
				//condFormat.setBgColor(new RGB(213,255,195));
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
					
				return excelUtils.writeToExcel(rs,rptformat,2, 0,0 );
				
			
	}
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
		   }


	}
	private ResultSet getPlantSafetyResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAuditRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		

		String reponse=commonFilter.getAbnDetectBy();
		String fromdate=commonFilter.getAftFromDt();
		String todate=commonFilter.getAftToDt();
		String type=commonFilter.getAreatype();
		String status=commonFilter.getAwise();
		String role=commonFilter.getAbnAllch();
		
		
	System.out.println("role123:::"+role+status+type+todate+reponse);
		
		if(commonFilter.getAbnAllch().equals("DMT MEMBER"))
		{
			condParms +="PSITYPE="+"DM"+";";
		}
		else if(commonFilter.getAbnAllch().equals("UNIT HEAD"))
		{
			condParms +="PSITYPE="+"UH"+";";
		}
		if(commonFilter.getAbnAllch().equals("UNIT EHS MANAGER"))
		{
			condParms +="PSITYPE="+"EM"+";";
		}
		
		
		if(status!=null)
		{
		condParms+="SAFETYSTATUS="+status+";";
		}
		
		condParms+="SAFETYTYPE="+type+";";
		if(status!=null)
		{
		condParms+="SAFETYSTATUS="+status+";";
		}
		if(reponse!=null)
		{
		condParms+="RESPONSEBY="+reponse+";";
		}
		if(fromdate!=null&&todate!=null)
				{		
		condParms+="SAFETYROMSDTE="+fromdate+";";
		condParms+="SAFETYTOSDTE="+todate+";";
				}
	
		condParms+="FNLID="+commonFilter.getActionKeyId()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("HSE_FN_PLANTSAFETYREPORTLIST", paramValues);
	}

	@Override
	public AbnTlAbnormality createPsiAbn(AbnTlAbnormality abnTlAbnormality) throws Exception {
		// TODO Auto-generated method stub
	
		System.out.println("In Dao Impl");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table

	try{

		abnTlAbnormality.setAbnmKeyid(dbActionTemplate.getSequenceNumber(AbnTlAbnormalitySql.TBL_ABN_TL_ABNORMALITY,10,"AB","YYMM","y")); // set the sequnce number 
	sqls.add(AbnTlAbnormalitySql.getInsertSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray())); // add insert sql for master table

	//   
	System.out.println("after dtl "+sqls.toString());

	dbActionTemplate.executeStatements(sqls); // execute the block of sqls

	}		
	catch(Exception e)
				{
					e.printStackTrace();
					throw new Exception(e.getMessage());
				}
		return abnTlAbnormality;
	}

	@Override
	public AbnTlAbnormality UpdatePSIAbn(AbnTlAbnormality abnTlAbnormality) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("Inside Abnormality Dao Impl");
		List<String> sqls = new ArrayList<String>();
		AbnTlAbnormalitySql abnTlAbnormalitySql = new AbnTlAbnormalitySql(); // contains dbtable,field names, Field types and related sqls  of master table

		try {

			sqls.add(AbnTlAbnormalitySql.getUpdateSql(abnTlAbnormalitySql.getAbnmDbFields(), abnTlAbnormality.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return abnTlAbnormality;
	}

	@Override
	public  KznTlKaizenbankmst createPSISuggetion(KznTlKaizenbankmst kznTlKaizenbankmst) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			String sql=KznTlKaizenbankmstSql.checkDuplication(kznTlKaizenbankmst);//"SELECT COUNT(*) FROM "+KznTlKaizenbankmstSql.TBL_KZN_TL_KAIZENBANKMST+" WHERE ";
			String count= dbActionTemplate.getSingleValue(sql);
			if(Integer.parseInt(count)>0)
				throw new BusinessApplicationExceptions("duplication,");
			else{
				kznTlKaizenbankmst.setKzbnKeyid(dbActionTemplate.getSequenceNumber(KznTlKaizenbankmstSql.TBL_KZN_TL_KAIZENBANKMST, 10, "KZBN", "", "")); // set the sequnce number 
				sqls.add(KznTlKaizenbankmstSql.getInsertSql(kznTlKaizenbankmstSql.getKzbnDbFields(), kznTlKaizenbankmst.getSaveArray())); // add insert sql for master table
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			}
			
		}catch(BusinessApplicationExceptions e){
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kznTlKaizenbankmst;
	}

	@Override
	public String getElementId(String flid) throws Exception {
		 String Elementid="SELECT  FNLN_ELEMENTID FROM gen_tl_functionallocn  WHERE  FNLN_KEYID ='"+flid+"' ";
		 
			System.out.println(" Element id "+ Elementid);
				return dbActionTemplate.getSingleValue(Elementid);
		
	}

	@Override
	public KznTlKaizenbankmst updatePSISuggetion(KznTlKaizenbankmst newKznTlKaizenbankmst) throws Exception {
		System.out.println("Inside suggestiondao Dao Impl");
		List<String> sqls = new ArrayList<String>();
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql(); // contains dbtable,field names, Field types and related sqls  of master table

		try {

			sqls.add(KznTlKaizenbankmstSql.getUpdateSql(kznTlKaizenbankmstSql.getKzbnDbFields(), newKznTlKaizenbankmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return newKznTlKaizenbankmst;
	}
	}
		
	

