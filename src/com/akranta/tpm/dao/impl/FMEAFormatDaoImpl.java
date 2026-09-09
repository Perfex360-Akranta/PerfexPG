package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.FMEAFormatDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.PlmTlDesignfmeadtlSql;
import com.akranta.tpm.dao.sql.PlmTlDesignfmeamstSql;
import com.akranta.tpm.dao.sql.PlmTlEquipmentfmeadtlSql;
import com.akranta.tpm.dao.sql.PlmTlEquipmentfmeamstSql;
import com.akranta.tpm.dao.sql.PlmTlProcessfmeadtlSql;
import com.akranta.tpm.dao.sql.PlmTlProcessfmeamstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PlmTlDesignfmeadtl;
import com.akranta.tpm.model.PlmTlDesignfmeamst;
import com.akranta.tpm.model.PlmTlEquipmentfmeadtl;
import com.akranta.tpm.model.PlmTlEquipmentfmeamst;
import com.akranta.tpm.model.PlmTlProcessfmeadtl;
import com.akranta.tpm.model.PlmTlProcessfmeamst;
import com.akranta.tpm.service.api.FmeaServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class FMEAFormatDaoImpl implements FMEAFormatDao {
	private FmeaServiceApi fmeaServiceApi;
	FunctionCallApi fnCallApi;
	
	private DBActionTemplate dbActionTemplate;

	public FMEAFormatDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
public void FMEAFormatDaoImplJwt(String jwtToken) {
		
		try{
			fmeaServiceApi= new FmeaServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Override
	public List<String[]> getFMEADtlsList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = getFmeaRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//			dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_FMEADTLSLIST", paramValues);
			dataList =  fnCallApi.callFunction("PLM_FN_FMEADTLSLIST_SB", paramValues,3,true);
			
			CommonMessage.debugMsg("Printing the SQL");
			for(String[] arr:dataList) 
			{
				CommonMessage.debugMsg(Arrays.toString(arr));
			}                                                
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
		}
	}

	@Override
	public List<String[]> getFMEAReviewList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = getFmeaRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//			dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_FMEAREVIEWLIST", paramValues);
			dataList =  fnCallApi.callFunction("PLM_FN_FMEAREVIEWLIST_SB", paramValues,3,true);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
		}
	}

	@Override
	public List<String[]> getFMEAMstList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
			String condParms = getFmeaRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);			
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
//			dataList =  dbActionTemplate.processFunctionCalls("PLM_FN_FMEAMSTLIST", paramValues);
			dataList =  fnCallApi.callFunction("PLM_FN_FMEAMSTLIST_SB", paramValues,3,true);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
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
		}
	}
    
	private String getFmeaRelatedCondStr(CommonFilter commonFilter){
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms+="FMEAKEYID="+commonFilter.getKey()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFlid())){
			condParms+="FLID="+commonFilter.getFlid()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getBdType())){
			condParms+="TYPE="+commonFilter.getBdType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getDocType())){
			condParms+="DOCTYPE="+commonFilter.getDocType()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getDM())){
			condParms+="DOCMSTID="+commonFilter.getDM()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getDmtdetailid())){
			condParms+="DOCDTLID="+commonFilter.getDmtdetailid()+";";
		}
		
		if(CommonFunctions.isValidKeyId(commonFilter.getFromDate())){
			condParms+="FROMDATE="+commonFilter.getFromDate()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getToDate())){
			condParms+="TODATE="+commonFilter.getToDate()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getFromMonth())){
			condParms+="FROMMONTH="+commonFilter.getFromMonth()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getToMonth())){
			condParms+="TOMONTH="+commonFilter.getToMonth()+";";
		}
		if(CommonFunctions.isValidKeyId(commonFilter.getMonwise())){
			condParms+="ISMONTHWISE="+commonFilter.getMonwise() +";";
		}
		return condParms;
	}
	
	@Override
	public PlmTlDesignfmeamst create(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlDesignfmeamstSql PlmTlDesignfmeamstSql = new PlmTlDesignfmeamstSql();
		PlmTlDesignfmeadtlSql plmTlDesignfmeadtlSql=new PlmTlDesignfmeadtlSql();
		PlmTlDesignfmeadtl plmTlDesignfmedtl= new PlmTlDesignfmeadtl();
		try{		
			newPlmTlDesignfmeamst.setFmdmKeyid(dbActionTemplate.getSequenceNumber(PlmTlDesignfmeamstSql.TBL_PLM_TL_DESIGNFMEAMST,15,"FDM", "", "")); // set the sequnce number
			newPlmTlDesignfmeamst.setFmdmNo(newPlmTlDesignfmeamst.getFmdmKeyid());
			sqls.add(PlmTlDesignfmeamstSql.getInsertSql(PlmTlDesignfmeamstSql.getFmdmDbFields(), newPlmTlDesignfmeamst.getSaveArray())); // add insert sql for master table			
					
			List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList= newPlmTlDesignfmeamst.getplmTlDesignfmeadtl();
			for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){				
				plmTlDesignfmedtl=plmTlDesignfmeadtlList.get(i);
				plmTlDesignfmedtl.setFmddFmdmKeyid(newPlmTlDesignfmeamst.getFmdmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlDesignfmedtl.getFmddKeyid())){
					plmTlDesignfmedtl.setFmddKeyid(dbActionTemplate.getSequenceNumber(plmTlDesignfmeadtlSql.TBL_PLM_TL_DESIGNFMEADTL,15,"FDD", "", "")); // set the sequnce number
					sqls.add(plmTlDesignfmeadtlSql.getInsertSql(plmTlDesignfmeadtlSql.getFmddDbFields(), plmTlDesignfmedtl.getSaveArray())); // add insert sql for master table					
				}				
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlDesignfmeamst;
	}

	@Override
	public PlmTlDesignfmeamst update(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlDesignfmeamstSql PlmTlDesignfmeamstSql = new PlmTlDesignfmeamstSql();
		PlmTlDesignfmeadtlSql plmTlDesignfmeadtlSql=new PlmTlDesignfmeadtlSql();
		PlmTlDesignfmeadtl plmTlDesignfmedtl= new PlmTlDesignfmeadtl();
		try{
			sqls.add(PlmTlDesignfmeamstSql.getUpdateSql(PlmTlDesignfmeamstSql.getFmdmDbFields(), newPlmTlDesignfmeamst.getSaveArray())); // add insert sql for master table			
					
			List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList= newPlmTlDesignfmeamst.getplmTlDesignfmeadtl();
			for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){				
				plmTlDesignfmedtl=plmTlDesignfmeadtlList.get(i);
				plmTlDesignfmedtl.setFmddFmdmKeyid(newPlmTlDesignfmeamst.getFmdmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlDesignfmedtl.getFmddKeyid())){
					plmTlDesignfmedtl.setFmddKeyid(dbActionTemplate.getSequenceNumber(plmTlDesignfmeadtlSql.TBL_PLM_TL_DESIGNFMEADTL,15,"FDD", "", "")); // set the sequnce number
					sqls.add(plmTlDesignfmeadtlSql.getInsertSql(plmTlDesignfmeadtlSql.getFmddDbFields(), plmTlDesignfmedtl.getSaveArray())); // add insert sql for master table					
				}	
				else{
					sqls.add(plmTlDesignfmeadtlSql.getUpdateSql(plmTlDesignfmeadtlSql.getFmddDbFields(), plmTlDesignfmedtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlDesignfmeamst;
	}

	@Override
	public PlmTlEquipmentfmeamst create(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlEquipmentfmeamstSql PlmTlEquipmentfmeamstSql = new PlmTlEquipmentfmeamstSql();
		PlmTlEquipmentfmeadtlSql plmTlEquipmentfmeadtlSql=new PlmTlEquipmentfmeadtlSql();
		PlmTlEquipmentfmeadtl plmTlEquipmentfmedtl= new PlmTlEquipmentfmeadtl();
		try{		
			newPlmTlEquipmentfmeamst.setFmeqKeyid(dbActionTemplate.getSequenceNumber(PlmTlEquipmentfmeamstSql.TBL_PLM_TL_EQUIPMENTFMEAMST,15,"FEM", "", "")); // set the sequnce number
			newPlmTlEquipmentfmeamst.setFmeqNo(newPlmTlEquipmentfmeamst.getFmeqKeyid());
			sqls.add(PlmTlEquipmentfmeamstSql.getInsertSql(PlmTlEquipmentfmeamstSql.getFmeqDbFields(), newPlmTlEquipmentfmeamst.getSaveArray())); // add insert sql for master table			
					
			List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList= newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
			for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){				
				plmTlEquipmentfmedtl=plmTlEquipmentfmeadtlList.get(i);
				plmTlEquipmentfmedtl.setFmedFmeqKeyid(newPlmTlEquipmentfmeamst.getFmeqKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlEquipmentfmedtl.getFmedKeyid())){
					plmTlEquipmentfmedtl.setFmedKeyid(dbActionTemplate.getSequenceNumber(plmTlEquipmentfmeadtlSql.TBL_PLM_TL_EQUIPMENTFMEADTL,15,"FED", "", "")); // set the sequnce number
					sqls.add(plmTlEquipmentfmeadtlSql.getInsertSql(plmTlEquipmentfmeadtlSql.getFmedDbFields(), plmTlEquipmentfmedtl.getSaveArray())); // add insert sql for master table					
				}				
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlEquipmentfmeamst;
	}

	@Override
	public PlmTlEquipmentfmeamst update(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlEquipmentfmeamstSql PlmTlEquipmentfmeamstSql = new PlmTlEquipmentfmeamstSql();
		PlmTlEquipmentfmeadtlSql plmTlEquipmentfmeadtlSql=new PlmTlEquipmentfmeadtlSql();
		PlmTlEquipmentfmeadtl plmTlEquipmentfmedtl= new PlmTlEquipmentfmeadtl();
		try{ 
			sqls.add(PlmTlEquipmentfmeamstSql.getUpdateSql(PlmTlEquipmentfmeamstSql.getFmeqDbFields(), newPlmTlEquipmentfmeamst.getSaveArray())); // add insert sql for master table			
					
			List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList= newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
			for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){				
				plmTlEquipmentfmedtl=plmTlEquipmentfmeadtlList.get(i);
				plmTlEquipmentfmedtl.setFmedFmeqKeyid(newPlmTlEquipmentfmeamst.getFmeqKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlEquipmentfmedtl.getFmedKeyid())){
					plmTlEquipmentfmedtl.setFmedKeyid(dbActionTemplate.getSequenceNumber(plmTlEquipmentfmeadtlSql.TBL_PLM_TL_EQUIPMENTFMEADTL,15,"FED", "", "")); // set the sequnce number
					sqls.add(plmTlEquipmentfmeadtlSql.getInsertSql(plmTlEquipmentfmeadtlSql.getFmedDbFields(), plmTlEquipmentfmedtl.getSaveArray())); // add insert sql for master table					
				}	
				else{
					sqls.add(plmTlEquipmentfmeadtlSql.getUpdateSql(plmTlEquipmentfmeadtlSql.getFmedDbFields(), plmTlEquipmentfmedtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlEquipmentfmeamst;
	}

	@Override
	public PlmTlProcessfmeamst create(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlProcessfmeamstSql PlmTlProcessfmeamstSql = new PlmTlProcessfmeamstSql();
		PlmTlProcessfmeadtlSql plmTlProcessfmeadtlSql=new PlmTlProcessfmeadtlSql();
		PlmTlProcessfmeadtl plmTlProcessfmedtl= new PlmTlProcessfmeadtl();
		try{		
			newPlmTlProcessfmeamst.setFmpmKeyid(dbActionTemplate.getSequenceNumber(PlmTlProcessfmeamstSql.TBL_PLM_TL_PROCESSFMEAMST,15,"FPM", "", "")); // set the sequnce number
			newPlmTlProcessfmeamst.setFmpmNo(newPlmTlProcessfmeamst.getFmpmKeyid());
			sqls.add(PlmTlProcessfmeamstSql.getInsertSql(PlmTlProcessfmeamstSql.getFmpmDbFields(), newPlmTlProcessfmeamst.getSaveArray()));
			
			List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList= newPlmTlProcessfmeamst.getplmTlProcessfmeadtl();
			for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){				
				plmTlProcessfmedtl=plmTlProcessfmeadtlList.get(i);
				plmTlProcessfmedtl.setFmpdFmpmKeyid(newPlmTlProcessfmeamst.getFmpmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlProcessfmedtl.getFmpdKeyid())){
					plmTlProcessfmedtl.setFmpdKeyid(dbActionTemplate.getSequenceNumber(plmTlProcessfmeadtlSql.TBL_PLM_TL_PROCESSFMEADTL,15,"FPD", "", "")); // set the sequnce number
					sqls.add(plmTlProcessfmeadtlSql.getInsertSql(plmTlProcessfmeadtlSql.getFmpdDbFields(), plmTlProcessfmedtl.getSaveArray())); // add insert sql for master table					
				}				
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlProcessfmeamst;
	}

	@Override
	public PlmTlProcessfmeamst update(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlProcessfmeamstSql PlmTlProcessfmeamstSql = new PlmTlProcessfmeamstSql();
		PlmTlProcessfmeadtlSql plmTlProcessfmeadtlSql=new PlmTlProcessfmeadtlSql();
		PlmTlProcessfmeadtl plmTlProcessfmedtl= new PlmTlProcessfmeadtl();
		try{
			sqls.add(PlmTlProcessfmeamstSql.getUpdateSql(PlmTlProcessfmeamstSql.getFmpmDbFields(), newPlmTlProcessfmeamst.getSaveArray())); // add insert sql for master table			
					
			List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList= newPlmTlProcessfmeamst.getplmTlProcessfmeadtl();
			for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){				
				plmTlProcessfmedtl=plmTlProcessfmeadtlList.get(i);
				plmTlProcessfmedtl.setFmpdFmpmKeyid(newPlmTlProcessfmeamst.getFmpmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlProcessfmedtl.getFmpdKeyid())){
					plmTlProcessfmedtl.setFmpdKeyid(dbActionTemplate.getSequenceNumber(plmTlProcessfmeadtlSql.TBL_PLM_TL_PROCESSFMEADTL,15,"FPD", "", "")); // set the sequnce number
					sqls.add(plmTlProcessfmeadtlSql.getInsertSql(plmTlProcessfmeadtlSql.getFmpdDbFields(), plmTlProcessfmedtl.getSaveArray())); // add insert sql for master table					
				}
				else{
					sqls.add(plmTlProcessfmeadtlSql.getUpdateSql(plmTlProcessfmeadtlSql.getFmpdDbFields(), plmTlProcessfmedtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlProcessfmeamst;
	}

	@Override
	public List<String[]> select(String keyId, String type) throws Exception {
		// TODO Auto-generated method stub
		
		String sql="";
		if(type.equals("design")){
			sql=PlmTlDesignfmeamstSql.getSelectSql(keyId);
		}
		else if(type.equals("equipment")){
			sql=PlmTlEquipmentfmeamstSql.getSelectSql(keyId);
		}
		else if(type.equals("process")){
			sql=PlmTlProcessfmeamstSql.getSelectSql(keyId);
		}
		List<String[]> dataList=dbActionTemplate.getDataList(sql);
		return dataList;
	}

	@Override
	public PlmTlProcessfmeamst delete(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlProcessfmeamstSql PlmTlProcessfmeamstSql = new PlmTlProcessfmeamstSql();
		PlmTlProcessfmeadtlSql plmTlProcessfmeadtlSql=new PlmTlProcessfmeadtlSql();
		try{
			sqls.add(plmTlProcessfmeadtlSql.getDeleteSql(newPlmTlProcessfmeamst.getFmpmKeyid())); // add insert sql for master table
			sqls.add(PlmTlProcessfmeamstSql.getDeleteSql(PlmTlProcessfmeamstSql.getFmpmDbFields(), newPlmTlProcessfmeamst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlProcessfmeamst;
	}

	@Override
	public PlmTlDesignfmeamst delete(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlDesignfmeamstSql PlmTlDesignfmeamstSql = new PlmTlDesignfmeamstSql();
		PlmTlDesignfmeadtlSql plmTlDesignfmeadtlSql=new PlmTlDesignfmeadtlSql();
		try{	
			sqls.add(plmTlDesignfmeadtlSql.getDeleteAllSql(newPlmTlDesignfmeamst.getFmdmKeyid())); // add insert sql for master table					
			sqls.add(PlmTlDesignfmeamstSql.getDeleteSql(PlmTlDesignfmeamstSql.getFmdmDbFields(), newPlmTlDesignfmeamst.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); 
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlDesignfmeamst;
	}

	@Override
	public PlmTlEquipmentfmeamst delete(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlEquipmentfmeamstSql PlmTlEquipmentfmeamstSql = new PlmTlEquipmentfmeamstSql();
		PlmTlEquipmentfmeadtlSql plmTlEquipmentfmeadtlSql=new PlmTlEquipmentfmeadtlSql();
		try{		
			
			sqls.add(plmTlEquipmentfmeadtlSql.getDeleteAllSql(newPlmTlEquipmentfmeamst.getFmeqKeyid())); // add insert sql for master table					
			sqls.add(PlmTlEquipmentfmeamstSql.getDeleteSql(PlmTlEquipmentfmeamstSql.getFmeqDbFields(), newPlmTlEquipmentfmeamst.getSaveArray())); // add insert sql for master table			
			dbActionTemplate.executeStatements(sqls); 
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlEquipmentfmeamst;
	}		 
	
	@Override
	public PlmTlProcessfmeamst deleteDtls(PlmTlProcessfmeamst newPlmTlProcessfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlProcessfmeamstSql PlmTlProcessfmeamstSql = new PlmTlProcessfmeamstSql();
		PlmTlProcessfmeadtlSql plmTlProcessfmeadtlSql=new PlmTlProcessfmeadtlSql();
		PlmTlProcessfmeadtl plmTlProcessfmedtl= new PlmTlProcessfmeadtl();
		try{		
			List<PlmTlProcessfmeadtl> plmTlProcessfmeadtlList= newPlmTlProcessfmeamst.getplmTlProcessfmeadtl();
			for(int i=0 ;i<=plmTlProcessfmeadtlList.size()-1;i++){				
				plmTlProcessfmedtl=plmTlProcessfmeadtlList.get(i);
				plmTlProcessfmedtl.setFmpdFmpmKeyid(newPlmTlProcessfmeamst.getFmpmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlProcessfmedtl.getFmpdReviewby())){
					sqls.add(plmTlProcessfmeadtlSql.getDeleteSql(plmTlProcessfmeadtlSql.getFmpdDbFields(), plmTlProcessfmedtl.getSaveArray())); // add insert sql for master table					
				}
				else{
					sqls.add(plmTlProcessfmeadtlSql.getUpdateReviewSql(plmTlProcessfmeadtlSql.getFmpdDbFields(), plmTlProcessfmedtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); 
			String cnt=dbActionTemplate.getSingleValue("Select Count(*) from PLM_TL_PROCESSFMEADTL where FMPD_FMPM_KEYID='"+newPlmTlProcessfmeamst.getFmpmKeyid()+"'");
			CommonMessage.debugMsg("cnt:"+cnt);
			if(Integer.parseInt(cnt)==0){
				String sql=PlmTlProcessfmeamstSql.getDeleteSql(PlmTlProcessfmeamstSql.getFmpmDbFields(), newPlmTlProcessfmeamst.getSaveArray());
				dbActionTemplate.executeStatement(sql);
			}
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlProcessfmeamst;
	}

	@Override
	public PlmTlDesignfmeamst deleteDtls(PlmTlDesignfmeamst newPlmTlDesignfmeamst)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlDesignfmeamstSql PlmTlDesignfmeamstSql = new PlmTlDesignfmeamstSql();
		PlmTlDesignfmeadtlSql plmTlDesignfmeadtlSql=new PlmTlDesignfmeadtlSql();
		PlmTlDesignfmeadtl plmTlDesignfmedtl= new PlmTlDesignfmeadtl();
		try{	
			List<PlmTlDesignfmeadtl> plmTlDesignfmeadtlList= newPlmTlDesignfmeamst.getplmTlDesignfmeadtl();
			for(int i=0 ;i<=plmTlDesignfmeadtlList.size()-1;i++){				
				plmTlDesignfmedtl=plmTlDesignfmeadtlList.get(i);
				plmTlDesignfmedtl.setFmddFmdmKeyid(newPlmTlDesignfmeamst.getFmdmKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlDesignfmedtl.getFmddReviewby())){
					sqls.add(plmTlDesignfmeadtlSql.getDeleteSql(plmTlDesignfmeadtlSql.getFmddDbFields(), plmTlDesignfmedtl.getSaveArray())); // add insert sql for master table					
				}	
				else{
					sqls.add(plmTlDesignfmeadtlSql.getUpdateReviewSql(plmTlDesignfmeadtlSql.getFmddDbFields(), plmTlDesignfmedtl.getSaveArray())); // add insert sql for master table
				}
			}
			dbActionTemplate.executeStatements(sqls); 
			String cnt=dbActionTemplate.getSingleValue("Select Count(*) from PLM_TL_DESIGNFMEADTL where FMDD_FMDM_KEYID='"+newPlmTlDesignfmeamst.getFmdmKeyid()+"'");
			CommonMessage.debugMsg("cnt:"+cnt);
			if(Integer.parseInt(cnt)==0){
				String sql=PlmTlProcessfmeamstSql.getDeleteSql(PlmTlDesignfmeamstSql.getFmdmDbFields(), newPlmTlDesignfmeamst.getSaveArray());
				dbActionTemplate.executeStatement(sql);
			}
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlDesignfmeamst;
	}

	@Override
	public PlmTlEquipmentfmeamst deleteDtls(
			PlmTlEquipmentfmeamst newPlmTlEquipmentfmeamst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		PlmTlEquipmentfmeamstSql PlmTlEquipmentfmeamstSql = new PlmTlEquipmentfmeamstSql();
		PlmTlEquipmentfmeadtlSql plmTlEquipmentfmeadtlSql=new PlmTlEquipmentfmeadtlSql();
		PlmTlEquipmentfmeadtl plmTlEquipmentfmedtl= new PlmTlEquipmentfmeadtl();
		try{		
			List<PlmTlEquipmentfmeadtl> plmTlEquipmentfmeadtlList= newPlmTlEquipmentfmeamst.getplmTlEquipmentfmeadtl();
			for(int i=0 ;i<=plmTlEquipmentfmeadtlList.size()-1;i++){				
				plmTlEquipmentfmedtl=plmTlEquipmentfmeadtlList.get(i);
				plmTlEquipmentfmedtl.setFmedFmeqKeyid(newPlmTlEquipmentfmeamst.getFmeqKeyid());
				if (!CommonFunctions.isValidKeyId(plmTlEquipmentfmedtl.getFmedReviewby())){
					sqls.add(plmTlEquipmentfmeadtlSql.getDeleteSql(plmTlEquipmentfmeadtlSql.getFmedDbFields(), plmTlEquipmentfmedtl.getSaveArray())); // add insert sql for master table					
				}	
				else{
					sqls.add(plmTlEquipmentfmeadtlSql.getUpdateReviewSql(plmTlEquipmentfmeadtlSql.getFmedDbFields(), plmTlEquipmentfmedtl.getSaveArray())); // add insert sql for master table
				}
			}			
			dbActionTemplate.executeStatements(sqls); 
			String cnt=dbActionTemplate.getSingleValue("Select Count(*) from PLM_TL_EQUIPMENTFMEADTL where FMED_FMEQ_KEYID='"+newPlmTlEquipmentfmeamst.getFmeqKeyid()+"'");
			CommonMessage.debugMsg("cnt:"+cnt);
			/*
			 * if(Integer.parseInt(cnt)==0){ String
			 * sql=PlmTlProcessfmeamstSql.getDeleteSql(PlmTlEquipmentfmeamstSql.
			 * getFmeqDbFields(), newPlmTlEquipmentfmeamst.getSaveArray());
			 * dbActionTemplate.executeStatement(sql); }
			 */
			
			if (Integer.parseInt(cnt) == 0) {

			   
			    PlmTlProcessfmeamstSql plmTlProcessfmeamstSql =
			            new PlmTlProcessfmeamstSql();

			    String sql = plmTlProcessfmeamstSql.getDeleteSql(
			            plmTlProcessfmeamstSql.getFmpmDbFields(),
			            newPlmTlEquipmentfmeamst.getSaveArray()
			    );

			    dbActionTemplate.executeStatement(sql);
			}

		}catch( Exception e){
			throw new Exception(e.getMessage());
		}	
		return newPlmTlEquipmentfmeamst;
	}

	@Override
	public Workbook getFmeaListExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getFmeaMstReportResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);			
			return excelUtils.writeToExcel(rs,format, 2,1,0 );//elumalai
			
		   }
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getFmeaMstReportResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = getFmeaRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);	
		CommonMessage.debugMsg("Inside export to excel FMEA");
		return dbActionTemplate.NewdbFunctionCall2("PLM_FN_FMEAMSTLIST", paramValues);
	}

	@Override
	public Map<Integer, List<String[]>> fmeaExcel(String keyid,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();	
			String condParms = getFmeaRelatedCondStr(commonFilter);
			paramValues.add(condParms);	
			for( int params = 0; params < paramValues.size(); params++)
		    {
		    	com.akranta.tpm.utils.CommonMessage.debugMsg("params : "+paramValues.get(params));
		    }
			
			Map<Integer, List<String[]>> fmeaExport= dbActionTemplate.processDbFunCallMultCursor("PLM_FN_FMEAEXCEL", paramValues,1);
			
			for(int i = 0; i <  fmeaExport.size();i++ )
			{
				CommonMessage.debugMsg("Inside v: " +fmeaExport.get(i).size() );
			}
			return fmeaExport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}

//	@Override
//	public List<String[]> getTotalVal(String severity, String occurance,
//			String detection) throws Exception {
//		String select="";
//		String selectTable="";
//		String selectCond="";
//		if(UIUtils.isValidKeyId(severity)){
//			select+="SEVF_CODE*";
//			selectTable+="PLM_TL_FMEASEVERITYMST,";
//			selectCond+=" AND SEVF_KEYID='"+severity+"'  ";
//		}
//		if(UIUtils.isValidKeyId(occurance)){
//			select+=" OCCF_CODE *";
//			selectTable+=" PLM_TL_FMEAOCCURENCEMST,";
//			selectCond+=" AND OCCF_KEYID='"+occurance+"' ";
//		}
//		if(UIUtils.isValidKeyId(detection)){
//			select+="DETF_CODE*";
//			selectTable+="PLM_TL_FMEADETECTIONMST,";
//			selectCond+=" AND DETF_KEYID='"+detection+"'  ";
//		}
//		select=select.substring(0, select.length()-1);
//		selectTable=selectTable.substring(0, selectTable.length()-1);
//		
//		String sql = " SELECT "+select+" FROM "+selectTable+" WHERE 1=1 "+selectCond;
//		CommonMessage.debugMsg("sql  "+sql);
//		List<String []> gridData = dbActionTemplate.getDataList(sql);
//		return gridData;
//	}
	
	@Override
	public List<String[]> getTotalVal(String severity, String occurance, String detection) throws Exception {

	    StringBuilder select = new StringBuilder();
	    StringBuilder selectTable = new StringBuilder();
	    StringBuilder selectCond = new StringBuilder();

	    if (UIUtils.isValidKeyId(severity)) {
	        select.append("CAST(SEVF_CODE AS INTEGER) * ");
	        selectTable.append("PLM_TL_FMEASEVERITYMST, ");
	        selectCond.append(" AND SEVF_KEYID='").append(severity).append("' ");
	    }

	    if (UIUtils.isValidKeyId(occurance)) {
	        select.append("CAST(OCCF_CODE AS INTEGER) * ");
	        selectTable.append("PLM_TL_FMEAOCCURENCEMST, ");
	        selectCond.append(" AND OCCF_KEYID='").append(occurance).append("' ");
	    }

	    if (UIUtils.isValidKeyId(detection)) {
	        select.append("CAST(DETF_CODE AS INTEGER) * ");
	        selectTable.append("PLM_TL_FMEADETECTIONMST, ");
	        selectCond.append(" AND DETF_KEYID='").append(detection).append("' ");
	    }

	    // remove trailing "* "
	    select.setLength(select.length() - 2);
	    // remove trailing ", "
	    selectTable.setLength(selectTable.length() - 2);

	    String sql = "SELECT " + select +
	                 " FROM " + selectTable +
	                 " WHERE 1=1 " + selectCond;

	    CommonMessage.debugMsg("sql :: " + sql);

	    return dbActionTemplate.getDataList(sql);
	}


	/*@Override
	public List<String[]> getActionplanExcel(String keyid) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sqlData1=new StringBuilder();
		sqlData1.append(" SELECT MIN(APLD_KEYID),FMED_KEYID txtFmedKeyid,TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY'),APLD_ACTIONPLAN,MIN(EMPM_NAME),3 DATAORDER ");
		//sqlData1.append(" SELECT SINM_CARECOMMENDED AS CARECOMME,C.EMPM_NAME AS sinm_responsibility, ");
		sqlData1.append(" FROM PLM_TL_EQUIPMENTFMEADTL,PLM_TL_FMEASEVERITYMST SEVERITY,PLM_TL_FMEAOCCURENCEMST OCCURENCE, PLM_TL_FMEADETECTIONMST DETECTION,PLM_TL_FMEASEVERITYMST RESEVERITY, ");
		sqlData1.append(" PLM_TL_FMEAOCCURENCEMST REOCCURENCE,PLM_TL_FMEADETECTIONMST REDETECTION,GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,GEN_TL_EMPLOYEEMST  WHERE  1 = 1  AND SEVERITY.SEVF_KEYID = FMED_SEVERITY_KEYID AND OCCURENCE.OCCF_KEYID = FMED_OCCURRENCE_KEYID "); 
		//sqlData1.append(" TO_CHAR(SINM_TARGETDATE,'DD-MON-YYY') AS sinm_targetdate,SINM_ACTIONTAKEN  AS sinm_actiontaken,D.EMPM_NAME AS sinm_completedby, "); 
		sqlData1.append("AND DETECTION.DETF_KEYID = FMED_DETECTION_KEYID  AND RESEVERITY.SEVF_KEYID(+) = FMED_RESEVERITY_KEYID AND REOCCURENCE.OCCF_KEYID(+) = FMED_REOCCURRENCE_KEYID  AND REDETECTION.DETF_KEYID(+) =FMED_REDETECTION_KEYID ");
		sqlData1.append("  AND APLM_DETAILREFID(+) = FMED_KEYID  AND APLD_APLM_KEYID=APLM_KEYID AND EMPM_KEYID=APLD_RESPONSIBILITY(+)  AND APLD_RESPONSIBILITY   IN( SELECT APLD_RESPONSIBILITY  from GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,PLM_TL_EQUIPMENTFMEADTL where  APLM_DETAILREFID(+) = FMED_KEYID ");
		sqlData1.append("  AND APLD_APLM_KEYID=APLM_KEYID AND APLD_KEYID IN(SELECT MIN(APLD_KEYID)  from GEN_TL_ACTIONPLANMST,GEN_TL_ACTIONPLANDTL,PLM_TL_EQUIPMENTFMEADTL where  APLM_DETAILREFID(+) = FMED_KEYID ");
		//sqlData1.append(" TO_CHAR(SINM_DATECOMPLETED,'DD-MON-YYY') sinm_datecompleted,E.EMPM_NAME  AS sinm_verifiedby,TO_CHAR(SINM_DATEVERIFIED,'DD-MON-YYY') AS sinm_dateverified ");
		sqlData1.append(" AND APLD_APLM_KEYID=APLM_KEYID  AND FMED_FMEQ_KEYID = '"+keyid+"'  Group by FMED_KEYID ))");
		sqlData1.append("  AND FMED_FMEQ_KEYID = '"+keyid+"'  GROUP BY FMED_KEYID,TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY'),APLD_ACTIONPLAN ");
		sqlData1.append("  ORDER BY FMED_KEYID DESC");
		CommonMessage.debugMsg("The Final sqlData1 Content::: "+sqlData1.toString());
		List<String[]> getFillAccidentCon=dbActionTemplate.getDataList(sqlData1.toString());
		return getFillAccidentCon;
	}*/
	
	/*@Override
	public List<String[]> getActionplanExcel(String keyid) throws Exception {

	    StringBuilder sql = new StringBuilder();

	    sql.append(" SELECT ");
	    sql.append("   MIN(APLD_KEYID), ");
	    sql.append("   FMED_KEYID AS txtFmedKeyid, ");
	    sql.append("   TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') AS target_date, ");
	    sql.append("   APLD_ACTIONPLAN, ");
	    sql.append("   MIN(EMPM_NAME) AS employee_name, ");
	    sql.append("   3 AS DATAORDER ");

	    sql.append(" FROM PLM_TL_EQUIPMENTFMEADTL ");

	    sql.append(" INNER JOIN PLM_TL_FMEASEVERITYMST SEVERITY ");
	    sql.append("   ON SEVERITY.SEVF_KEYID = FMED_SEVERITY_KEYID ");

	    sql.append(" INNER JOIN PLM_TL_FMEAOCCURENCEMST OCCURENCE ");
	    sql.append("   ON OCCURENCE.OCCF_KEYID = FMED_OCCURRENCE_KEYID ");

	    sql.append(" INNER JOIN PLM_TL_FMEADETECTIONMST DETECTION ");
	    sql.append("   ON DETECTION.DETF_KEYID = FMED_DETECTION_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEASEVERITYMST RESEVERITY ");
	    sql.append("   ON RESEVERITY.SEVF_KEYID = FMED_RESEVERITY_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEAOCCURENCEMST REOCCURENCE ");
	    sql.append("   ON REOCCURENCE.OCCF_KEYID = FMED_REOCCURRENCE_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEADETECTIONMST REDETECTION ");
	    sql.append("   ON REDETECTION.DETF_KEYID = FMED_REDETECTION_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST ");
	    sql.append("   ON APLM_DETAILREFID = FMED_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANDTL ");
	    sql.append("   ON APLD_APLM_KEYID = APLM_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST ");
	    sql.append("   ON EMPM_KEYID = APLD_RESPONSIBILITY ");

	    sql.append(" WHERE FMED_FMEQ_KEYID = '").append(keyid).append("' ");

	    sql.append(" GROUP BY ");
	    sql.append("   FMED_KEYID, ");
	    sql.append("   TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY'), ");
	    sql.append("   APLD_ACTIONPLAN ");

	    sql.append(" ORDER BY FMED_KEYID DESC ");

	    CommonMessage.debugMsg("Final SQL ::: " + sql.toString());

	    return dbActionTemplate.getDataList(sql.toString());
	}*/
	
	@Override
	public List<String[]> getActionplanExcel(String keyid) throws Exception {

	    StringBuilder sql = new StringBuilder();

//	    sql.append(" SELECT ");
//	    sql.append("   MIN(APLD_KEYID), ");
//	    sql.append("   FMED_KEYID AS txtFmedKeyid, ");
//	    sql.append("   TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') AS target_date, ");
//	    sql.append("   APLD_ACTIONPLAN, ");
//	    sql.append("   MIN(EMPM_NAME) AS employee_name, ");
//	    sql.append("   3 AS DATAORDER ");
//
//	    sql.append(" FROM PLM_TL_EQUIPMENTFMEADTL ");
//
//	    sql.append(" INNER JOIN PLM_TL_FMEASEVERITYMST SEVERITY ");
//	    sql.append("   ON SEVERITY.SEVF_KEYID = FMED_SEVERITY_KEYID ");
//
//	    sql.append(" INNER JOIN PLM_TL_FMEAOCCURENCEMST OCCURENCE ");
//	    sql.append("   ON OCCURENCE.OCCF_KEYID = FMED_OCCURRENCE_KEYID ");
//
//	    sql.append(" INNER JOIN PLM_TL_FMEADETECTIONMST DETECTION ");
//	    sql.append("   ON DETECTION.DETF_KEYID = FMED_DETECTION_KEYID ");
//
//	    sql.append(" LEFT JOIN PLM_TL_FMEASEVERITYMST RESEVERITY ");
//	    sql.append("   ON RESEVERITY.SEVF_KEYID = FMED_RESEVERITY_KEYID ");
//
//	    sql.append(" LEFT JOIN PLM_TL_FMEAOCCURENCEMST REOCCURENCE ");
//	    sql.append("   ON REOCCURENCE.OCCF_KEYID = FMED_REOCCURRENCE_KEYID ");
//
//	    sql.append(" LEFT JOIN PLM_TL_FMEADETECTIONMST REDETECTION ");
//	    sql.append("   ON REDETECTION.DETF_KEYID = FMED_REDETECTION_KEYID ");
//
//	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST ");
//	    sql.append("   ON APLM_DETAILREFID = FMED_KEYID ");
//
//	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANDTL ");
//	    sql.append("   ON APLD_APLM_KEYID = APLM_KEYID ");
//
//	    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST ");
//	    sql.append("   ON EMPM_KEYID = APLD_RESPONSIBILITY ");
//
//	    sql.append(" WHERE FMED_FMEQ_KEYID = '").append(keyid).append("' ");
//
//	    sql.append(" GROUP BY ");
//	    sql.append("   FMED_KEYID, ");
//	    sql.append("   TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY'), ");
//	    sql.append("   APLD_ACTIONPLAN ");
//
//	    sql.append(" ORDER BY FMED_KEYID DESC ");
	    
	    sql.append(" SELECT ");
	    sql.append("   FMED_KEYID AS txtFmedKeyid, ");                    // Index 0
	    sql.append("   APLD_ACTIONPLAN AS recommended_action, ");         // Index 1: Column M (Recommended Action)
	    sql.append("   EMPM_NAME AS employee_name, ");                    // Index 2: Column N (Resp)
	    sql.append("   TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') AS target_date, ");  // Index 3: Column O (Target Date)
	    sql.append("   CASE ");
	    sql.append("       WHEN APLM_STATUS = 'P' THEN 'Pending' ");
	    sql.append("       WHEN APLM_STATUS = 'C' THEN 'Completed' ");
	    sql.append("       ELSE '' ");
	    sql.append("   END AS actions_taken, ");                          // Index 4: Column P (Actions Taken)
	    sql.append("   APLD_KEYID, ");                                    // Index 5
	    sql.append("   3 AS DATAORDER ");                                 // Index 6                                // Index 6

	    sql.append(" FROM PLM_TL_EQUIPMENTFMEADTL ");

	    sql.append(" INNER JOIN PLM_TL_FMEASEVERITYMST SEVERITY ");
	    sql.append("   ON SEVERITY.SEVF_KEYID = FMED_SEVERITY_KEYID ");

	    sql.append(" INNER JOIN PLM_TL_FMEAOCCURENCEMST OCCURENCE ");
	    sql.append("   ON OCCURENCE.OCCF_KEYID = FMED_OCCURRENCE_KEYID ");

	    sql.append(" INNER JOIN PLM_TL_FMEADETECTIONMST DETECTION ");
	    sql.append("   ON DETECTION.DETF_KEYID = FMED_DETECTION_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEASEVERITYMST RESEVERITY ");
	    sql.append("   ON RESEVERITY.SEVF_KEYID = FMED_RESEVERITY_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEAOCCURENCEMST REOCCURENCE ");
	    sql.append("   ON REOCCURENCE.OCCF_KEYID = FMED_REOCCURRENCE_KEYID ");

	    sql.append(" LEFT JOIN PLM_TL_FMEADETECTIONMST REDETECTION ");
	    sql.append("   ON REDETECTION.DETF_KEYID = FMED_REDETECTION_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANMST ");
	    sql.append("   ON APLM_DETAILREFID = FMED_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_ACTIONPLANDTL ");
	    sql.append("   ON APLD_APLM_KEYID = APLM_KEYID ");

	    sql.append(" LEFT JOIN GEN_TL_EMPLOYEEMST ");
	    sql.append("   ON EMPM_KEYID = APLD_RESPONSIBILITY ");

	    sql.append(" WHERE FMED_FMEQ_KEYID = '").append(keyid).append("' ");

	    sql.append(" ORDER BY FMED_KEYID DESC, APLD_KEYID ");

	    CommonMessage.debugMsg("Final SQL ::: " + sql.toString());

	    return dbActionTemplate.getDataList(sql.toString());
	}


	@Override
	public String getFlid(String cellid) throws Exception {
		// TODO Auto-generated method stub
		return dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", cellid);
	}
	
	public List<String[]> getExistingFmeaByEquip(String equipId, String flid) throws Exception {
	    try {
	        String sql =
	            " SELECT FMEQ_KEYID, FMEQ_NO, FMEQ_DATE, FMEQ_PREPAREDBY, " +
	            " FMEQ_CORETEAM, FMEQ_SUPEQUIPID " +
	            " FROM PLM_TL_EQUIPMENTFMEAMST " +
	            " WHERE FMEQ_EQUIPID = '" + equipId + "' ";

	        if(flid != null && !flid.trim().isEmpty()) {
	            sql += " AND FMEQ_FLID = '" + flid + "' ";
	        }

	        sql += " ORDER BY FMEQ_DATE DESC LIMIT 1 ";
	        CommonMessage.debugMsg("getExistingFmeaByEquip SQL: " + sql);
	        return dbActionTemplate.getDataList(sql);
	    } catch(Exception e){
	        throw new Exception(e.getMessage());
	    }
	}
	
}
