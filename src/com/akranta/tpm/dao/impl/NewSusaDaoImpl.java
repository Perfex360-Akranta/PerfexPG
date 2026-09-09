package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.NewSusaDao;
import com.akranta.tpm.dao.sql.FieldAuditSheetdtlSql;
import com.akranta.tpm.dao.sql.FieldAuditSheetmstSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.GenTlSusaAddBehaviormstSql;
import com.akranta.tpm.dao.sql.GenTlSusaParticipantmstSql;
import com.akranta.tpm.dao.sql.GenTlSusamstNewSql;
import com.akranta.tpm.dao.sql.MocRfcBasismstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FieldAuditSheetdtl;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlSusaAddBehaviormst;
import com.akranta.tpm.model.GenTlSusaParticipantmst;
import com.akranta.tpm.model.GenTlSusamstNew;
/*import com.akranta.tpm.model.MocRfcBasismst;*/
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class NewSusaDaoImpl implements NewSusaDao{
	private DBActionTemplate dbActionTemplate;
	private CommonFilterDao commonFilterdao;
	GenTlSusaParticipantmstSql genTlSusaParticipantmstSql=null;
	GenTlSusamstNewSql genTlSusamstNewSql=null;
	GenTlSusaAddBehaviormstSql genTlSusaAddBehaviormstSql=null;
	public NewSusaDaoImpl(DBActionTemplate dbActionTemplate) {
		genTlSusaParticipantmstSql=new GenTlSusaParticipantmstSql();
		genTlSusamstNewSql=new GenTlSusamstNewSql();
		genTlSusaAddBehaviormstSql=new GenTlSusaAddBehaviormstSql();
		this.dbActionTemplate = dbActionTemplate;	
	}  
  public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
public List<String[]> getEmployeeList(String susaKeyid) throws Exception{
	StringBuilder sql=new StringBuilder("select '',SUST_KEYID,SUST_SUSN_KEYID,EMPM_CODE ||'-'|| EMPM_NAME  as \"Employee\",'' as \"Delete\"  ");
    sql.append("FROM GEN_TL_EMPLOYEEMST,GEN_TL_SUSAPARTICIPANTSMST");
    sql.append(" WHERE SUST_SUSN_KEYID='"+susaKeyid+"' ");
    sql.append(" AND SUST_EMPM_KEYID=EMPM_KEYID ");    
	List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);
	return dataList;
}
public GenTlSusamstNew  getSelectedData(String keyid) throws Exception{
	GenTlSusamstNew newGenTlSusamstNew=new GenTlSusamstNew();
	String sql = GenTlSusamstNewSql.getMasterDataSql();
	Object args[] = new Object[] {keyid};
	newGenTlSusamstNew.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return newGenTlSusamstNew;
}

public String DeleteParticipants(String keyid) throws Exception{
    String Sql=" DELETE FROM GEN_TL_SUSAPARTICIPANTSMST WHERE SUST_KEYID='"+keyid+"'";
    dbActionTemplate.executeStatement(Sql);
	return Sql;
}
@Override
public List<String[]> getNewSusaGridData(CommonFilter commonFilter) throws Exception {
	try {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		CommonMessage.debugMsg("Paramvalues"+paramValues);
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_SUSAMODIFICATIONGRID",paramValues);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
}
public GenTlSusaAddBehaviormst updateActionPlanId(String susaid,String AplKeyid) throws Exception{
	GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst =new GenTlSusaAddBehaviormst();
	String sql=GenTlSusaAddBehaviormstSql.UpdateActionplan(susaid, AplKeyid);
    dbActionTemplate.executeStatement(sql);
	return newGenTlSusaAddBehaviormst;
}
@Override
public List<String[]> getElementId(String loginflid, String loginlevel,
		String loginElementid, String empId) throws Exception {
	// TODO Auto-generated method stub
    StringBuffer sql =new StringBuffer();
	sql.append("SELECT FNLN_ELEMENTID,FNLN_KEYID,ROLE_LEVEL,ROLE_NAME,ROLE_KEYID FROM  GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM,ADM_TL_ROLEMST  ");
		sql.append(" WHERE FNLN_KEYID = FRT_FNLN_KEYID  and FRT_ROLE_KEYID = ROLE_KEYID  ");
	if(UIUtils.isValidKeyId(loginflid))
			sql.append(" AND FRT_FNLN_KEYID  = '"+ loginflid +"' ");
		
		sql.append(" AND FRT_EMPM_KEYID = '"+ empId +"'  AND ROLE_LEVEL= '"+ loginlevel +"'");
		
		Object [] args = {} ;
		List<String []> userDatas = dbActionTemplate.getDataList(sql.toString(),args);
		return userDatas;
}
public List<String[]> getBehaviorList(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	try {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		condParms +="SUSAID="+commonFilter.getKey()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_SUSABEHAVIORGRID",paramValues);
		if (commonFilter.getViewClick() == 'Y') {
			String totalCnt = paramValues.get(0);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if (isInteger) {
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList;

	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
}
public GenTlSusamstNew create(GenTlSusamstNew genTlSusamstNew) throws Exception{
	List<String> sqls = new ArrayList<String>();
	GenTlSusamstNewSql genTlSusamstNewSql=new GenTlSusamstNewSql();
	GenTlSusaAddBehaviormstSql genTlSusaAddBehaviormstSql=new GenTlSusaAddBehaviormstSql();
	GenTlSusaAddBehaviormst genTlSusaAddBehaviormst=new GenTlSusaAddBehaviormst();
	try{
		
		genTlSusamstNew.setSusnKeyid(dbActionTemplate.getSequenceNumber(GenTlSusamstNewSql.TBL_GEN_TL_SUSAMSTNEW,12,"SUN",null,"N"));
		sqls.add(GenTlSusamstNewSql.getInsertSql(genTlSusamstNewSql.getSusnDbFields(),genTlSusamstNew.getSaveArray()));
		
		if (genTlSusamstNew.getAddBehaviourMst()!=null){	
			
			List<GenTlSusaAddBehaviormst> genTlSusaAddBehaviorMstList= genTlSusamstNew.getAddBehaviourMst();
			for(int i=0 ;i<=genTlSusaAddBehaviorMstList.size()-1;i++){				
				genTlSusaAddBehaviormst=genTlSusaAddBehaviorMstList.get(i);
				if (!CommonFunctions.isValidKeyId(genTlSusaAddBehaviormst.getSuabKeyid())){
					genTlSusaAddBehaviormst.setSuabSusnKeyid(genTlSusamstNew.getSusnKeyid());
					genTlSusaAddBehaviormst.setSuabKeyid(dbActionTemplate.getSequenceNumber(GenTlSusaAddBehaviormstSql.TBL_GEN_TL_SUSAADDBEHAVIOURMST,12,"FASD","",""));
					sqls.add(genTlSusaAddBehaviormstSql.getInsertSql(genTlSusaAddBehaviormstSql.getsuabDbFields(), genTlSusaAddBehaviormst.getSaveArray()));
				}				
			}
		}
		dbActionTemplate.executeStatements(sqls); 
	}catch (ValidationExceptions e){
		throw new ValidationExceptions(e.getMessage());
	}	
	catch (BusinessApplicationExceptions e){			
		throw new BusinessApplicationExceptions(e.getMessage());
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}	
	return genTlSusamstNew;	
}

public GenTlSusamstNew update(GenTlSusamstNew genTlSusamstNew) throws Exception{
	/*List<String> sqls = new ArrayList<String>();		
	       GenTlSusamstNewSql genTlSusamstNewSql = new GenTlSusamstNewSql();
	       StringBuilder SqlData=new StringBuilder();	          
	       SqlData.append("SELECT SUSN_KEYID FROM  GEN_TL_SUSAMSTNEW WHERE SUSN_KEYID ='"+genTlSusamstNew.getSusnKeyid()+"' ");
	   	   String isExists = dbActionTemplate.getSingleValue(SqlData.toString());	   	 
	   	    if (!UIUtils.isValidKeyId(isExists)) { 
	   	    	genTlSusamstNew.setSusnKeyid(dbActionTemplate.getSequenceNumber(GenTlSusamstNewSql.TBL_GEN_TL_SUSAMSTNEW,12,"SUN",null,"N"));
		        sqls.add(GenTlSusamstNewSql.getInsertSql(genTlSusamstNewSql.getSusnDbFields(),genTlSusamstNew.getSaveArray()));
				}
			else {
				genTlSusamstNew.setSusnKeyid(isExists);
				sqls.add(GenTlSusamstNewSql.getUpdateSql(genTlSusamstNewSql.getSusnDbFields(),genTlSusamstNew.getSaveArray())); // add insert sql for master table 
			}    
           dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	    return genTlSusamstNew;*/
	List<String> sqls = new ArrayList<String>();
	GenTlSusamstNewSql genTlSusamstNewSql=new GenTlSusamstNewSql();
	GenTlSusaAddBehaviormstSql genTlSusaAddBehaviormstSql=new GenTlSusaAddBehaviormstSql();
	GenTlSusaAddBehaviormst genTlSusaAddBehaviormst=new GenTlSusaAddBehaviormst();
	
	try {
		sqls.add(GenTlSusamstNewSql.getUpdateSql(genTlSusamstNewSql.getSusnDbFields(),genTlSusamstNew.getSaveArray()));
         if (genTlSusamstNew.getAddBehaviourMst()!=null){
			List<GenTlSusaAddBehaviormst> genTlSusaAddBehaviorMstList= genTlSusamstNew.getAddBehaviourMst();
			for(int i=0 ;i<=genTlSusaAddBehaviorMstList.size()-1;i++){				
				genTlSusaAddBehaviormst=genTlSusaAddBehaviorMstList.get(i);
				if (!CommonFunctions.isValidKeyId(genTlSusaAddBehaviormst.getSuabKeyid())){
					genTlSusaAddBehaviormst.setSuabSusnKeyid(genTlSusamstNew.getSusnKeyid());
					genTlSusaAddBehaviormst.setSuabKeyid(dbActionTemplate.getSequenceNumber(GenTlSusaAddBehaviormstSql.TBL_GEN_TL_SUSAADDBEHAVIOURMST,12,"FASD","",""));
					sqls.add(genTlSusaAddBehaviormstSql.getInsertSql(genTlSusaAddBehaviormstSql.getsuabDbFields(), genTlSusaAddBehaviormst.getSaveArray()));
				}	
				else{
					sqls.add(genTlSusaAddBehaviormstSql.getUpdateSql(genTlSusaAddBehaviormstSql.getsuabDbFields(), genTlSusaAddBehaviormst.getSaveArray()));					
				}	
			}
		}
		dbActionTemplate.executeStatements(sqls);	
	}catch (ValidationExceptions e){
		throw new ValidationExceptions(e.getMessage());
	}	
	catch (BusinessApplicationExceptions e){			
		throw new BusinessApplicationExceptions(e.getMessage());
	}catch( Exception e){
		throw new Exception(e.getMessage());
	}	
	   return genTlSusamstNew;
		}

public GenTlSusamstNew deleteSusa(GenTlSusamstNew genTlSusamstNew) throws Exception{
  List<String> sql=new ArrayList<String>();
  sql.add("DELETE FROM GEN_TL_SUSAMSTNEW WHERE SUSN_KEYID='"+genTlSusamstNew.getSusnKeyid()+"'");
  sql.add("DELETE FROM GEN_TL_SUSAPARTICIPANTSMST WHERE SUST_SUSN_KEYID='"+genTlSusamstNew.getSusnKeyid()+"'");
  sql.add("DELETE FROM GEN_TL_SUSAADDBEHAVIOURMST WHERE SUAB_SUSN_KEYID='"+genTlSusamstNew.getSusnKeyid()+"'");
  dbActionTemplate.executeStatements(sql);
  return genTlSusamstNew;
}
@Override
public Workbook getNewSusaModificationGridDataExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
		String format) throws Exception {
	// TODO Auto-generated method stub
	 List<String> paramValues = new ArrayList<String>();				
	 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
	 paramValues.add(condParms);	 
	 paramValues.add(commonParams);

	 ResultSet rs = null;
	 try{
	 	rs=dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_SUSAMODIFICATIONGRID", paramValues);
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		return excelUtils.writeToExcel(rs,format,2,0,0 );
	 		  
	 }finally{
		   if( rs != null)
	 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
	 }
}

@Override
public List<String[]> BehaviourDetail(CommonFilter commonFilter) throws Exception {		
	try
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms="FASM_KEYID="+commonFilter.getKey()+";";
		}			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);			
		paramValues.add(commonParams);
		List<String[]> dataList =  null;
		dataList =  dbActionTemplate.processFunctionCalls("HSE_PC_SAFETY.JHA_TL_SUSABEHAVIOUR_LIST", paramValues);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
		//	CommonMessage.debugMsg("totalCnt....."+totalCnt);
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



 public GenTlSusaParticipantmst ParticipantsCreate(GenTlSusaParticipantmst newGenTlSusaParticipantmst) throws Exception{
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */
	newGenTlSusaParticipantmst.setSustKeyid(dbActionTemplate.getSequenceNumber(GenTlSusaParticipantmstSql.TBL_GEN_TL_SUSAPARTICIPANTSMST,12,"SUST","","Y"));
	sqls.add(GenTlSusaParticipantmstSql.getInsertSql(genTlSusaParticipantmstSql.getSustDbFields(),newGenTlSusaParticipantmst.getSaveArray()));			
	dbActionTemplate.executeStatements(sqls);
	return newGenTlSusaParticipantmst;
}
 public  GenTlSusaAddBehaviormst BehaviourCreate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst) throws Exception{
	  List<String> sqls = new ArrayList<String>(); /* sqls for execution */
	  newGenTlSusaAddBehaviormst.setSuabKeyid(dbActionTemplate.getSequenceNumber(GenTlSusaAddBehaviormstSql.TBL_GEN_TL_SUSAADDBEHAVIOURMST,12,"SUAB","","Y"));   
	 
	  
	  sqls.add(GenTlSusaAddBehaviormstSql.getInsertSql(genTlSusaAddBehaviormstSql.getsuabDbFields(),newGenTlSusaAddBehaviormst.getSaveArray()));			
	 
	  dbActionTemplate.executeStatements(sqls);
	  return newGenTlSusaAddBehaviormst;
 }
 public  GenTlSusaAddBehaviormst BehaviourUpdate(GenTlSusaAddBehaviormst newGenTlSusaAddBehaviormst) throws Exception{
	  List<String> sqls = new ArrayList<String>();		
	  GenTlSusaAddBehaviormstSql genTlSusaAddBehaviormstSql = new GenTlSusaAddBehaviormstSql();
      StringBuilder SqlData=new StringBuilder();	          
      SqlData.append("SELECT SUAB_KEYID FROM  GEN_TL_SUSAADDBEHAVIOURMST WHERE SUAB_KEYID ='"+newGenTlSusaAddBehaviormst.getSuabKeyid()+"' ");
  	   String isExists = dbActionTemplate.getSingleValue(SqlData.toString());	   	 
  	    if (!UIUtils.isValidKeyId(isExists)) { 
  	    	newGenTlSusaAddBehaviormst.setSuabKeyid(dbActionTemplate.getSequenceNumber(GenTlSusaAddBehaviormstSql.TBL_GEN_TL_SUSAADDBEHAVIOURMST,12,"SUSB",null,"Y"));
	        sqls.add(GenTlSusaAddBehaviormstSql.getInsertSql(genTlSusaAddBehaviormstSql.getsuabDbFields(),newGenTlSusaAddBehaviormst.getSaveArray()));
			}
		else {
			CommonMessage.debugMsg("Else Called");
			newGenTlSusaAddBehaviormst.setSuabKeyid(isExists);
			sqls.add(GenTlSusaAddBehaviormstSql.getUpdateSql(genTlSusaAddBehaviormstSql.getsuabDbFields(),newGenTlSusaAddBehaviormst.getSaveArray())); // add insert sql for master table 
		}    
      dbActionTemplate.executeStatements(sqls); // execute the block of sqls
   return newGenTlSusaAddBehaviormst;
 }

 @Override
 public List<String[]> getBehaviorCountData(CommonFilter commonFilter) throws Exception {
 	// TODO Auto-generated method stub
 	List<String> paramValues = getFilterParamValues(commonFilter);
 	List<String[]> dataList =  null;
 ComboFilter cell=	commonFilter.getSubcell();
 	CommonMessage.debugMsg("cell "+paramValues);
 	String Types=commonFilter.getAssType();
 	CommonMessage.debugMsg("The Types"+Types);
 	
 	if(Types.equals("SAFE")){
 		dataList = dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_SUSABEHAVIORSAFECOUNT", paramValues);
 	}
 	else{
 		dataList = dbActionTemplate.processFunctionCalls("GEN_PC_REPORTS.GEN_FN_SUSABEHAVIORUNSAFECOUNT", paramValues);	
 	}
 	
 	if( commonFilter.getViewClick() == 'Y'){
 		String totalCnt = paramValues.get(0); 

 		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
 		if(  isInteger ){
 			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
 		}
 	}
 	return dataList;
 }
 @Override
 public List<String[]> getBehaviorCategoryCountData(CommonFilter commonFilter) throws Exception {
 	// TODO Auto-generated method stub
 	List<String> paramValues = getFilterParamValues(commonFilter);
 	List<String[]> dataList =  null;
 	
 	String Types=commonFilter.getAssType();
 	CommonMessage.debugMsg("The Types"+Types);
 	
 	if(Types.equals("SAFE")){
 		dataList = dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.GEN_FN_SUSACATEGORYSAFECOUNT", paramValues);
 	}
 	else{
 		
 		CommonMessage.debugMsg("else");
 		dataList = dbActionTemplate.processFunctionCalls("TEST_PC_TEST2.GEN_FN_SUSACATEGORYUNSAFECOUNT", paramValues);	
 	}
 	
 	if( commonFilter.getViewClick() == 'Y'){
 		String totalCnt = paramValues.get(0); 

 		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
 		if(  isInteger ){
 			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
 		}
 	}
 	return dataList;
 }
public Workbook getNewSusaSafeUnsafeExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception {
		// TODO Auto-generated method stub
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
         String Type= commonFilter.getAssType();	
		 CommonMessage.debugMsg("The Type is"+Type);
         ResultSet rs = null;
		 try{
			if(Type.equals("SAFE")){
			 	rs=dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.GEN_FN_SUSACATEGORYSAFECOUNT", paramValues);
			}
			else{
			 	rs=dbActionTemplate.dbFunctionCall("TEST_PC_TEST2.GEN_FN_SUSACATEGORYUNSAFECOUNT", paramValues);
			}
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,1,0,0);
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
		 }
	}
public Workbook getNewSusaSUBehaviourExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj,
		String format) throws Exception {
	// TODO Auto-generated method stub
	 List<String> paramValues = new ArrayList<String>();				
	 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
	 paramValues.add(condParms);	 
	 paramValues.add(commonParams);
     String Type= commonFilter.getAssType();	
	 CommonMessage.debugMsg("The Type is"+Type);
     ResultSet rs = null;
	 try{
		if(Type.equals("SAFE")){
		 	rs=dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_SUSABEHAVIORSAFECOUNT", paramValues);
		}
		else{                                                  
		 	rs=dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_SUSABEHAVIORUNSAFECOUNT", paramValues);
		}
		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
		return excelUtils.writeToExcel(rs,format,1,0,0);
	 		  
	 }finally{
		   if( rs != null)
	 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
	 }
} 
private List<String> getFilterParamValues(CommonFilter commonFilter) {
 List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	return paramValues;
}
    @Override
    public List<String[]> getNewSusaReportData(CommonFilter commonFilter) throws Exception {
    	// TODO Auto-generated method stub
    	try {
    		List<String> paramValues = new ArrayList<String>();
    		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
    		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
    		paramValues.add(condParms);
    		paramValues.add(commonParams);
    		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_SUSAREPORT",paramValues);
    		if (commonFilter.getViewClick() == 'Y') {
    			String totalCnt = paramValues.get(0);
    			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
    			if (isInteger) {
    				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
    			}
    		}
    		return dataList;
    	} catch (Exception e) {
    		throw new Exception(e.getMessage());
    	}
    }
    @Override
    public Workbook getNewSusaDiscussionExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String format) throws Exception {
    	// TODO Auto-generated method stub
    	 List<String> paramValues = new ArrayList<String>();				
    	 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
    	 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
    	 paramValues.add(condParms);	 
    	 paramValues.add(commonParams);

    	 ResultSet rs = null;
    	 try{
    	 	rs=dbActionTemplate.dbFunctionCall("GEN_PC_REPORTS.GEN_FN_SUSASAFEUNSAFERPT", paramValues);
    		ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
    		return excelUtils.writeToExcel(rs,format,2,0,0 );
    	 		  
    	 }finally{
    		   if( rs != null)
    	 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());		     
    	 }
    }
   /* public List<String[]> SusaDetailsData(String keyid,String flid) throws Exception{
        StringBuilder sql=new StringBuilder();
        sql.append("SELECT SUSN_KEYID AS KEYID,TO_CHAR (SUSN_DATE, 'DD-MON-YYYY HH:MM') AS SUSN_DATE,");
        sql.append("DECODE(SUSN_DISCUSSIONTYPE,'SUDT000001','Individual','SUDT000002','Group')");
        sql.append(" AS DISCUSSIONTYPE,SUSN_DISCUSSIONNO AS DISCUSSIONNO, "); 
        sql.append("DECODE(SUSN_PERSONROLE,'E','Employee','M','Manager') AS PERSONROLE,");
        sql.append("A.SECT_NAME||'-'|| A.CELL_CODE AS DMTJH,EMPM_NAME AS SUSN_PREPAREDBY,");
        sql.append(" SUSN_DISCUSSIONSUMM AS DISSUMM,B.CELL_NAME AS SUSN_SUSADONEJH,SUSN_OTHERPARTICIPANTS AS SUSN_OTHERPARTICIPANTS");
        sql.append(" FROM GEN_TL_SUSAMSTNEW,GEN_TL_EMPLOYEEMST,GEN_VW_FNLN A, GEN_TL_CELLMST B ");
        sql.append("WHERE A.FNLN_KEYID=SUSN_FLID AND SUSN_SUSADONEJH=B.CELL_KEYID(+) ");
        sql.append("AND SUSN_PREPAREDBY=EMPM_KEYID AND SUSN_KEYID='"+keyid+"'");
        CommonMessage.debugMsg("The SQl"+sql);
        List<String[]> totalData=dbActionTemplate.getDataList(sql.toString());
        CommonMessage.debugMsg("The Sql Data"+sql.toString());
        return totalData;
    }*/
    
    public List<String[]> SusaDetailsData(String keyid,String flid) throws Exception{
    StringBuilder sql=new StringBuilder();
    sql.append("SELECT SUSN_KEYID AS KEYID,TO_CHAR (SUSN_DATE, 'DD-MON-YYYY HH:MM') AS SUSN_DATE,");
    sql.append("DECODE(SUSN_DISCUSSIONTYPE,'SUDT000001','Individual','SUDT000002','Group') AS DISCUSSIONTYPE, ");
    sql.append("SUSN_DISCUSSIONNO AS DISCUSSIONNO,DECODE(SUSN_PERSONROLE,'E','Employee','M','Manager') AS PERSONROLE, ");
    sql.append("A.SECT_NAME||'-'|| A.CELL_CODE AS DMTJH,EMPM_NAME AS SUSN_PREPAREDBY,");
    sql.append(" SUSN_DISCUSSIONSUMM AS DISSUMM,B.CELL_NAME AS SUSN_SUSADONEJH,SUSN_OTHERPARTICIPANTS AS SUSN_OTHERPARTICIPANTS");
    sql.append(" FROM GEN_TL_SUSAMSTNEW,GEN_TL_EMPLOYEEMST,GEN_VW_FNLN A, GEN_TL_CELLMST B ");
    sql.append("WHERE A.FNLN_KEYID=SUSN_FLID AND SUSN_SUSADONEJH=B.CELL_KEYID(+) ");
    sql.append("AND SUSN_PREPAREDBY=EMPM_KEYID AND SUSN_KEYID='"+keyid+"'");
    CommonMessage.debugMsg("The SQl"+sql);
    List<String[]> totalData=dbActionTemplate.getDataList(sql.toString());
    CommonMessage.debugMsg("The Sql Data"+sql.toString());
    return totalData;
}
   public List<String[]> susaBehaviourData(String keyid) throws Exception{
    	 StringBuilder sqlData=new StringBuilder();
    	 CommonMessage.debugMsg("Inside the susaBehaviourData method");
    	 sqlData.append("SELECT DECODE (SUAB_TYPE,'U', 'Unsafe','S', 'Safe') AS TYPE,");
    	 sqlData.append("SUBC_NAME AS BehaviorCategory,SUSB_NAME AS Behavior,");  
    	 sqlData.append("SUSC_NAME AS Cause,SUAA_NAME AS Action,SUSP_NAME AS Probability,SUCM_NAME AS Consequence ");
         sqlData.append(" FROM GEN_TL_SUSABEHAVIOURMST,GEN_TL_SUSABEHAVIOURCATEGORY,GEN_TL_SUSACONSEQUENCEMST,");
         sqlData.append("GEN_TL_SUSAPROBABILITYMST,GEN_TL_SUSACAUSEMST,GEN_TL_SUSAADDBEHAVIOURMST,GEN_TL_SUSAACTIONMST ");
         sqlData.append("WHERE 1=1 AND SUAB_CAUSE=SUSC_KEYID(+) AND SUAB_ACTION =SUAA_KEYID(+) "); 
         sqlData.append("AND SUAB_BEHAVCATEGORY=SUBC_KEYID(+) AND SUAB_PROBABILITY=SUSP_KEYID(+) ");
         sqlData.append("AND SUAB_CONSEQUENCE=SUCM_KEYID(+) AND SUAB_BEHAVIOUR=SUSB_KEYID(+) ");
         sqlData.append("AND SUAB_SUSN_KEYID='"+keyid+"' ");
         CommonMessage.debugMsg("The SQl in Excel View"+sqlData);
         List<String[]> behaviourData=dbActionTemplate.getDataList(sqlData.toString());
         return behaviourData;
    }
   

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    public String getImageCount(String keyid) throws Exception{
    String query="SELECT COUNT(*) FROM GEN_TL_ALLMODULEIMGFILE WHERE IMFL_REFKEYID='"+keyid+"'";
    return dbActionTemplate.getSingleValue(query);	
    }
    public GenTlSusaAddBehaviormst behaviourDelete(GenTlSusaAddBehaviormst newenTlSusaAddBehaviormst) throws Exception{
    	List<String> sqls = new ArrayList<String>();
    	GenTlSusaAddBehaviormstSql genTlSusaAddBehaviormstSql=new GenTlSusaAddBehaviormstSql();
    	try{
  			sqls.add(genTlSusaAddBehaviormstSql.getDeleteSql(genTlSusaAddBehaviormstSql.getsuabDbFields(),newenTlSusaAddBehaviormst.getSaveArray()));
            dbActionTemplate.executeStatements(sqls);            
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return newenTlSusaAddBehaviormst;
    }

	public List<GenTlAllmoduleimgfile> getSusaImages(List<GenTlAllmoduleimgfile> SusaImgList) throws NoDataFoundException, Exception{
		
	CommonMessage.debugMsg("Inside Dao impl EXL Image ");
	List<GenTlAllmoduleimgfile> genAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
	for(GenTlAllmoduleimgfile gentlallModuleimage:SusaImgList)
	{
		/*String condSql = " IMFL_REFDOCTYPE = '" + gentlallModuleimage.getImflRefdoctype()+ "'AND IMFL_IMAGETYPE = '" + gentlallModuleimage.getImflImagetype()+"'";
		CommonMessage.debugMsg("The condSql::::"+condSql);
		String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,"IMFL_FILENAME","IMFL_REFKEYID", gentlallModuleimage.getImflRefkeyid(),condSql); 	
		CommonMessage.debugMsg("The File Name::::"+fileName);*/
		
		CommonMessage.debugMsg(" gentlallModuleimage.getImflRefdoctype()"+ gentlallModuleimage.getImflRefdoctype() +" gentlallModuleimage.getImflImagetype()"+ gentlallModuleimage.getImflImagetype());
		String condSql = "  DMDM_REFDOCTYPE = '" + gentlallModuleimage.getImflRefdoctype()+"'";
		CommonMessage.debugMsg("The condSql::::"+condSql);
		String ReefDocId = dbActionTemplate.getSingleValue("GEN_TL_SUSAADDBEHAVIOURMST","MIN(SUAB_KEYID)","SUAB_SUSN_KEYID", gentlallModuleimage.getImflRefkeyid()); 	

		
		String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_DCM_TL_DOCUMENTMANAGER,"DMDM_FILENAME","DMDM_REFDOCNO", ReefDocId,condSql); 	
		CommonMessage.debugMsg("The File Name::::"+fileName);
		if( fileName != null )
		{
			if( fileName.lastIndexOf("/") > -1 )
			fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			String fileNamePath = gentlallModuleimage.getImflBlobimage()+  fileName;
			CommonMessage.debugMsg(" The fileNamePath:::" + fileNamePath);
			String imgFileName = gentlallModuleimage.getImflFilename()+fileName;
			CommonMessage.debugMsg(" IMFI FILENAME:::: " + imgFileName);
			gentlallModuleimage.setImflFilename(fileNamePath);
			CommonMessage.debugMsg(" INFI set fileName " + fileNamePath);
			genAllmoduleimgList.add(gentlallModuleimage);
		}
	}
	return genAllmoduleimgList;
}
	
	  @Override
	    public List<String[]> getSusaSafeUnsafeReport(CommonFilter commonFilter) throws Exception {
	    	// TODO Auto-generated method stub
	    	try {
	    		List<String> paramValues = new ArrayList<String>();
	    		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
	    		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	    		paramValues.add(condParms);
	    		paramValues.add(commonParams);
	    		List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_PC_REPORTS.GEN_FN_SUSASAFEUNSAFERPT",paramValues);
	    		if (commonFilter.getViewClick() == 'Y') {
	    			String totalCnt = paramValues.get(0);
	    			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	    			if (isInteger) {
	    				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	    			}
	    		}
	    		return dataList;
	    	} catch (Exception e) {
	    		throw new Exception(e.getMessage());
	    	}
	    	
	    }	
	    
		@Override
		public void DeleteSusaBehaviour(String keyid) throws Exception 
		{
			List<String > sqls = new ArrayList<String>();
			
			sqls.add( GenTlSusaAddBehaviormstSql.DeleteSusaBehaviour(keyid));
	  
			dbActionTemplate.executeStatements(sqls);
		}
}