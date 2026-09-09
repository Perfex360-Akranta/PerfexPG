package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KaizenBankDao; 
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.KaizenBankServiceAPI;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class KaizenBankDaoImpl implements KaizenBankDao{
	
	private DBActionTemplate dbActionTemplate;
	private KaizenBankServiceAPI kaizenBankServiceApi;
	private FunctionCallApi fnCallApi;

	public KaizenBankDaoImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
public void KaizenBankDaoImplJwt(String jwtToken) {
		
		try{
			kaizenBankServiceApi = new KaizenBankServiceAPI(jwtToken);
			fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
			    condParms +="EHSMODE="+commonFilter.getSafetyMode()+";";
			}
			
			if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
			    condParms +="KZNMODE="+commonFilter.getSafetyMode()+";";
			}
	
			if(UIUtils.isValidKeyId(commonFilter.getKey())){
		    condParms +="KZBNKEYID="+commonFilter.getKey()+";";
			}
			
			String kznBank=commonFilter.getKey();
			CommonMessage.debugMsg("The Get KaizenBank"+kznBank);
			if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey())){
				condParms +="LOGINID="+commonFilter.getGetKaizenkey()+";";
			}
			if(UIUtils.isValidKeyId(kznBank)){
		     condParms +="KZBNKEYID="+commonFilter.getKey()+";";
		   }
			
			     condParms +="FRMMODE="+commonFilter.getType()+";";
				
			paramValues.add(condParms+"KZNBANKTYPE="+commonFilter.getKznBankType()+";");
			paramValues.add(commonParams);
			
			List<String[]> dataList =   null;
			
			CommonMessage.debugMsg(" For EHS "+commonFilter.getSafetyMode());
			
			CommonMessage.debugMsg(" For Normal "+commonFilter.getKznBankType());
			
			String kznType = commonFilter.getKznBankType();   
			if(!UIUtils.isValidKeyId(commonFilter.getSafetyMode())){CommonMessage.debugMsg(" 1 ");
			if (kznType.equals( "V")|| kznType.equals( "I")||kznType.equals( "M"))
				//CommonMessage.debugMsg("Simple");
//				dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues);
				dataList =   fnCallApi.callFunction("JHN_FN_KAIZENACCEPTEDVERIFY_SB", paramValues,3,false);
			else if(kznType.equals( "R") )
			{
				CommonMessage.debugMsg("Simple");
//				dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVIEW", paramValues);
				dataList =   fnCallApi.callFunction("JHN_FN_KAIZENACCEPTEDVIEW_SB", paramValues,3,true);
	}
			else if(kznType.equals("IV")){
				CommonMessage.debugMsg("Individual View");
				dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENINDIVIDUALVIEW", paramValues);	
			}
			else
				dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENBANKMST", paramValues);
			}
			else{
			if(commonFilter.getSafetyMode().equals("Y")){
//			    dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues);
				dataList =   fnCallApi.callFunction("JHN_FN_KAIZENACCEPTEDVERIFY_SB", paramValues,3,false);
			}else if(commonFilter.getSafetyMode().equals("view")){
				CommonMessage.debugMsg("View");
//			    dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues)
				dataList =   fnCallApi.callFunction("JHN_FN_KAIZENACCEPTEDVERIFY_SB", paramValues,3,false);

			}
			}
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			//throw new Exception(e.getMessage()); 
		}
		return null;
	}
	
	@Override
	public List<String[]> getfillgriddataIndividual(CommonFilter commonFilter)throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			String EMPId=commonFilter.getKAIZEN();
			CommonMessage.debugMsg("EmpID"+EMPId);
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			if(UIUtils.isValidKeyId(commonFilter.getKAIZEN())){
				condParms+="EMPID="+commonFilter.getKAIZEN()+";";	
			}
			if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
			    condParms +="EHSMODE="+commonFilter.getSafetyMode()+";";
			}
			
			if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
			    condParms +="KZNMODE="+commonFilter.getSafetyMode()+";";
			}
	
			if(UIUtils.isValidKeyId(commonFilter.getKey())){
		    condParms +="KZBNKEYID="+commonFilter.getKey()+";";
			}
			
			String kznBank=commonFilter.getKey();
			CommonMessage.debugMsg("The Get KaizenBank"+kznBank);
			
			if(UIUtils.isValidKeyId(kznBank)){
		     condParms +="KZBNKEYID="+commonFilter.getKey()+";";
		   }
			
			     condParms +="FRMMODE="+commonFilter.getType()+";";
				 
			paramValues.add(condParms+"KZNBANKTYPE="+commonFilter.getKznBankType()+";");
			paramValues.add(commonParams);
			
			List<String[]> dataList =   null;
			
			CommonMessage.debugMsg(" For EHS "+commonFilter.getSafetyMode());
			
			CommonMessage.debugMsg(" For Normal "+commonFilter.getKznBankType());
			
			String kznType = commonFilter.getKznBankType();   
			if(!UIUtils.isValidKeyId(commonFilter.getSafetyMode())){CommonMessage.debugMsg(" 1 ");
			 if(kznType.equals( "R") )
			{
				 CommonMessage.debugMsg("In IF");
				dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVIEWIND", paramValues);	
			}
			 else if(commonFilter.getSafetyMode().equals("view")){
//			    dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues);
				 dataList =   fnCallApi.callFunction("JHN_FN_KAIZENACCEPTEDVERIFY_SB", paramValues,3,false);
			}
			}
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public KznTlKaizenbankmst create(KznTlKaizenbankmst kznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) 	throws Exception,BusinessApplicationExceptions {

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
	
	public KznTlKaizenbankmst update(KznTlKaizenbankmst kznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql();
		try {
			if(dataKeyidArr!=null){
				if(dataKeyidArr.length>0){
					if(dataFlidArr.length>0){
						for(int i=0;i<dataFlidArr.length;i++){
							kznTlKaizenbankmst.setKzbnKeyid(dataKeyidArr[i]);
/*							kznTlKaizenbankmst.setKzbnFlid(dataFlidArr[i]);
							kznTlKaizenbankmst.setKzbnResponsibility(dataSuggestArr[i]);
							kznTlKaizenbankmst.setKzbnSuggestedby(dataSuggestArr[i]);
							String elementId = getElementID (kznTlKaizenbankmst.getKzbnFlid());
							kznTlKaizenbankmst.setKzbnElementid(elementId);*/
							sqls.add(KznTlKaizenbankmstSql.getUpdateotherSql(kznTlKaizenbankmst,type));
						}
					}
				}
			}else
				sqls.add(KznTlKaizenbankmstSql.getUpdateSql(kznTlKaizenbankmstSql.getKzbnDbFields(), kznTlKaizenbankmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kznTlKaizenbankmst;
	}
	
	public KznTlKaizenbankmst delete(KznTlKaizenbankmst kznTlKaizenbankmst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql();
		try {
			
			sqls.add(kznTlKaizenbankmstSql.getDeleteSql(kznTlKaizenbankmstSql.getKzbnDbFields(), kznTlKaizenbankmst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznTlKaizenbankmst;
	}

	@Override
	public KznTlKaizenbankmst getRecall(String keyid) throws Exception {
		try
		{
			KznTlKaizenbankmstSql newKznTlKaizenbankmstsql = new KznTlKaizenbankmstSql();
			KznTlKaizenbankmst newKznTlKaizenbankmst = new KznTlKaizenbankmst();
			String sql = newKznTlKaizenbankmstsql.getRecall();
			CommonMessage.debugMsg("DAO SQL : "+sql);
			Object [] args =  new Object [] { keyid };
			CommonMessage.debugMsg("keyid:::::"+keyid);
			newKznTlKaizenbankmst.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyid);
			return  newKznTlKaizenbankmst;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}

	@Override
	public Workbook getKaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
			// TODO Auto-generated method stub
			 try{
			     String KznIndView=commonFilter.getKznBankType();
			     CommonMessage.debugMsg("Individual View::"+KznIndView);
				 rs=getKaizenResultSet(commonFilter,"AC",KznIndView);//ACC  CHANGED 
				 int rowStart = 0;
				 if("R".equals(KznIndView)) rowStart = 1;
				 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				 return excelUtils.writeToExcel(rs,format, 2,rowStart,0 );
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
	}
	/*private ResultSet getKaizenResultSet(CommonFilter commonFilter, String type) throws Exception{
		List<String> paramValues = getFilterParamValues(commonFilter,type);
		
		if("MAIN".equals(type))
			return dbActionTemplate.dbFunctionCall("KZN_FN_KAIZENBANKMAIGRID", paramValues);
		else
			return dbActionTemplate.dbFunctionCall("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues);
	}*/
	private ResultSet getKaizenResultSet(CommonFilter commonFilter, String type,String KznIndView) throws Exception{
		List<String> paramValues = getFilterParamValues(commonFilter,type,KznIndView);
		CommonMessage.debugMsg("TYPE"+type+"KznIndView"+KznIndView);
		
		 if(KznIndView=="IV"){
			 CommonMessage.debugMsg("Individual Excel View");
			return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KAIZENINDIVIDUALVIEW",paramValues);
		}
		else if("MAIN".equals(type)){
			CommonMessage.debugMsg("TYPE"+type);
			return dbActionTemplate.NewdbFunctionCall2("KZN_FN_KAIZENBANKMAIGRID", paramValues);
//			return fnCallApi.callFunction("KZN_FN_KAIZENBANKMAIGRID_SB",paramValues,3,true);

		}
		else if("ACC".equals(type)){
			return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KAIZENACCEPTEDVIEW", paramValues);
		}
		else if("R".equals(KznIndView)){
			return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KAIZENACCEPTEDVIEW", paramValues);
		}
		else{
			return dbActionTemplate.NewdbFunctionCall2("JHN_FN_KAIZENACCEPTEDVERIFY", paramValues);			   	
		}
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter,String type,String KznIndView) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		CommonMessage.debugMsg("KznIndView"+KznIndView);
		 if("IV".equals(KznIndView))
				if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey()))
				condParms+="EMPID="+commonFilter.getGetKaizenkey()+";";
		  if("MAIN".equals(type))
			paramValues.add(condParms);			
		else
			paramValues.add(condParms+"KZNBANKTYPE="+commonFilter.getKznBankType()+";");
		paramValues.add(commonParams);
		return paramValues;
}
	public String getElementID(String originalId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select fnln_elementid from gen_tl_functionallocn where FNLN_KEYID = '"+originalId+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
		return getElementID;
	}

	@Override
	public KznTlKaizenbankmst selectmst(String keyid) throws NoDataFoundException, SQLException, Exception {
		KznTlKaizenbankmst kznTlKaizenbankmst = new KznTlKaizenbankmst();
		String sql = KznTlKaizenbankmstSql.selectmst(keyid);
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		kznTlKaizenbankmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return kznTlKaizenbankmst;
	}

	@Override
	public List<String[]> getmaingrid(CommonFilter commonFilter)
			throws Exception {

		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		CommonMessage.debugMsg("ParamValues:"+paramValues);	

		List<String[]> dataList=null;
		
		if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
//			dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENBANKMAIGRID", paramValues);
			dataList =   fnCallApi.callFunction("KZN_FN_KAIZENBANKMAIGRID_SB", paramValues,3,true);
			
		}else
//			dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENBANKMAIGRID", paramValues);

		    dataList =   fnCallApi.callFunction("KZN_FN_KAIZENBANKMAIGRID_SB", paramValues,3,true);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(dataList);
		return dataList; 
	}

	@Override
	public Workbook getKaizenMainExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{
			 String KznIndView=commonFilter.getKznBankType();
		     CommonMessage.debugMsg("Individual View::"+KznIndView);
			 rs=getKaizenResultSet(commonFilter,"MAIN",KznIndView);
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 2,1,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	@Override
	public List<String[]> getSafetySuggGrid(CommonFilter commonFilter)
			throws Exception {
		try
		{
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	        String kznBank=commonFilter.getKey();
		    String sugMode=commonFilter.getType();
		
		   
			if(UIUtils.isValidKeyId(kznBank)){
		     condParms +="KZBNKEYID="+commonFilter.getKey()+";";
			}

			if(UIUtils.isValidKeyId(sugMode)){
		     condParms +="FRMMODE="+commonFilter.getType()+";";
			}
			paramValues.add(condParms);
			paramValues.add(commonParams);

						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("HSE_FN_SAFETYSUGGESTION", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook getSafetyExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		try{
			
			 rs =   getSaferResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}

	private ResultSet getSaferResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getSafetyFilterParamValues(commonFilter);
			return dbActionTemplate.dbFunctionCall("HSE_FN_SAFETYSUGGESTION", paramValues);
	}

	private List<String> getSafetyFilterParamValues(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}

	@Override
	public List<KznTlKaizenbankmst>  updateSugg(List<KznTlKaizenbankmst> kznTlEvmstList) throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg(" Status :: Remarks :: Dao Impl :: "+kznTlEvmstList.get(0).getKzbnAccrejremarks());
		for(KznTlKaizenbankmst kznTlKaizenbankmst:kznTlEvmstList){
			String sql = KznTlKaizenbankmstSql.updateKaizenBank(kznTlKaizenbankmst);
			sqls.add(sql);
		}
		dbActionTemplate.executeStatements(sqls);
		return kznTlEvmstList;
	}
	
	public KznTlKaizenbankmst updateVerifyDetails(List<KznTlKaizenbankmst> kaizenVerifyList )throws ValidationExceptions, Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
			
		CommonMessage.debugMsg("Dao Impl......");
		
		KznTlKaizenbankmst kznTlKaizenbankmstt = new KznTlKaizenbankmst();
		
		CommonMessage.debugMsg(" kaizenFormBean.getKaizenBankList().size() " + kaizenVerifyList.size());
		
		if(kaizenVerifyList!= null && kaizenVerifyList.size()>0) // check for detail table data
			{
			CommonMessage.debugMsg("inside");
		    	for(int i =0;i<kaizenVerifyList.size();i++)
				{	
		    		KznTlKaizenbankmst kznTlKaizenbankmst = (KznTlKaizenbankmst)kaizenVerifyList.get(i); // get detail info from list in empployee object
		    		StringBuffer sqlUpdt = new StringBuffer();
		    		sqlUpdt.append(" update kzn_Tl_Kaizenbankmst set Kzbn_Status = '"+kznTlKaizenbankmst.getKzbnStatus()+"' " );
		    		sqlUpdt.append(" , KZBN_KAIZEN = '"+kznTlKaizenbankmst.getKzbnKaizen()+"' " );
		    		sqlUpdt.append(" , Kzbn_Acrejby = '"+kznTlKaizenbankmst.getKzbnAcrejby()+"' " );
		    		sqlUpdt.append(" , Kzbn_Implementcost = '"+kznTlKaizenbankmst.getKzbnImplementcost()+"' " );
		    		if (UIUtils.isValidDate(kznTlKaizenbankmst.getKzbnTargetdate()))
		    			sqlUpdt.append(" , Kzbn_Targetdate = '"+kznTlKaizenbankmst.getKzbnTargetdate()+"' " );
		    		if (UIUtils.isValidDate(kznTlKaizenbankmst.getKzbnResponsibility()))
		    			sqlUpdt.append(" , KZBN_RESPONSIBILITY = '"+kznTlKaizenbankmst.getKzbnResponsibility()+"' " );
		    		sqlUpdt.append(" , Kzbn_Verifyremarks = '"+kznTlKaizenbankmst.getKzbnVerifyremarks()+"' " );
		    		sqlUpdt.append("  where kzbn_keyid ='"+kznTlKaizenbankmst.getKzbnKeyid()+"' " );
		    		
		    		sqls.add(sqlUpdt.toString());
		    		
		    		String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + kznTlKaizenbankmst.getKzbnKeyid() + "'";
					sqls.add(uSql);
		    
		    		CommonMessage.debugMsg("sqlypdt"+sqlUpdt.toString());
		    		
		    		kznTlKaizenbankmstt=kznTlKaizenbankmst;
				}
		    	dbActionTemplate.executeStatements(sqls);
			}
			
		return kznTlKaizenbankmstt;
	}
	public List<String[]> getSafetySuggestionSummaryGridData(CommonFilter commonFilter) throws Exception {
		try {

			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();
			

			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);

			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

			paramValues.add(condParms);

			paramValues.add(commonParams);
			

			List<String[]> dataList = dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_SAFETY_SUGGESTION",	paramValues);

			CommonMessage.debugMsg("Inside daoimpl 5: " + dataList.size());
			if (commonFilter.getViewClick() == 'Y') {
				String totalCnt = paramValues.get(0);
				CommonMessage.debugMsg("totalCnt...." + totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if (isInteger) {
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg("Test --->" + dataList.size());
			return dataList;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Workbook getSafetySuggestionSummaryGridDataExportExcel(CommonFilter commonFilter, JSONObject tblJSONObj, String rptFormat) throws Exception {
		 List<String> paramValues = new ArrayList<String>();				
		 String condParms = FilterCondSql.getEmployeeRoleRelatedCondStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		 
		 paramValues.add(condParms);	 
		 paramValues.add(commonParams);
		 CommonMessage.debugMsg("U are in Dao------------");
		 ResultSet rs = null;
		 try{
		 	rs=dbActionTemplate.dbFunctionCall("GEN_FN_SAFETY_SUGGESTION", paramValues);
		 	
		 	CommonMessage.debugMsg("Result set from DAO Impl = "+rs);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,rptFormat,2,0,0 );
		 		  
		 }finally{
			   if( rs != null)
		 				  DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			     
		 }
	}

	@Override
	public String getEmailId(String empmKeyid) throws Exception {		
		String sql = " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyid +"'";
		CommonMessage.debugMsg("Employee email sql :" +sql);
		String emailId= dbActionTemplate.getSingleValue(sql);		
		return emailId;
	}
	@Override
	public String getJhLeaderEmailId(String flid) throws Exception {		
		StringBuffer sql =new StringBuffer("SELECT EMPM_EMAIL ");
		sql.append(" FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,GEN_MV_FLIDHIERARCHY ");
		sql.append(" WHERE 1=1 AND EMPM_KEYID=FRT_EMPM_KEYID AND FRT_FNLN_KEYID =FLID AND FRT_ROLE_KEYID ='AROL0006'");
		sql.append(" AND FLID='").append(flid).append("'");
		CommonMessage.debugMsg("Jh Leader email sql :" +sql);
		String emailId= dbActionTemplate.getSingleValue(sql.toString());		
		return emailId;
	}
	@Override
	public KznTlKaizenbankmst updatekznsugg(
			KznTlKaizenbankmst newkznTlKaizenbankmst) throws Exception {
		// TODO Auto-generated method stub

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	    		
		StringBuffer sqlUpdt = new StringBuffer();
		
		sqlUpdt.append(" update kzn_Tl_Kaizenbankmst set Kzbn_Status = '"+newkznTlKaizenbankmst.getKzbnStatus()+"' " );
		sqlUpdt.append(" , KZBN_KAIZEN = '"+newkznTlKaizenbankmst.getKzbnKaizen()+"' " );
		sqlUpdt.append(" , Kzbn_Acrejby = '"+newkznTlKaizenbankmst.getKzbnAcrejby()+"' " );
		
		if (UIUtils.isValidDate(newkznTlKaizenbankmst.getKzbnImplementcost()))
			sqlUpdt.append(" , Kzbn_Implementcost = '"+newkznTlKaizenbankmst.getKzbnImplementcost()+"' " );
		else
			sqlUpdt.append(" , Kzbn_Implementcost = '0' " );
		
		if (UIUtils.isValidDate(newkznTlKaizenbankmst.getKzbnTargetdate()))
			sqlUpdt.append(" , Kzbn_Targetdate = '"+newkznTlKaizenbankmst.getKzbnTargetdate()+"' " );
		CommonMessage.debugMsg("getKzbnTargetdate()");
		if (UIUtils.isValidDate(newkznTlKaizenbankmst.getKzbnmocrequired()))
			sqlUpdt.append(" , KZBN_MOCREQUIRED = '"+newkznTlKaizenbankmst.getKzbnmocrequired()+"' " );
		if (UIUtils.isValidDate(newkznTlKaizenbankmst.getKzbnResponsibility()))
			sqlUpdt.append(" , KZBN_RESPONSIBILITY = '"+newkznTlKaizenbankmst.getKzbnResponsibility()+"' " );
		if (UIUtils.isValidDate(newkznTlKaizenbankmst.getKzbnVerifyremarks()))
			sqlUpdt.append(" , Kzbn_Verifyremarks = '"+newkznTlKaizenbankmst.getKzbnVerifyremarks()+"' " );
		sqlUpdt.append("  where kzbn_keyid ='"+newkznTlKaizenbankmst.getKzbnKeyid()+"' " );
		
		sqls.add(sqlUpdt.toString());
		
		String uSql  = " DELETE FROM  ADM_APPROVALS_LIST WHERE DOCUMENTNO = '" + newkznTlKaizenbankmst.getKzbnKeyid() + "'";
		sqls.add(uSql);

		CommonMessage.debugMsg("sqlypdt"+sqlUpdt.toString());
		
		//newkznTlKaizenbankmst=kznTlKaizenbankmst;

		dbActionTemplate.executeStatements(sqls);
	
		return newkznTlKaizenbankmst;	
	}
	@Override
	public List<String[]> FillkznData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = KznTlKaizenbankmstSql.selectkznData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	
	@Override
	public KznTlKaizenbankmst updatekznsuggstatus(
			KznTlKaizenbankmst newkznTlKaizenbankmst) throws Exception {
		// TODO Auto-generated method stub

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	    		
		StringBuffer sqlUpdtStatus = new StringBuffer();
		sqlUpdtStatus.append(" UPDATE KZN_TL_KAIZENBANKMST SET KZBN_STATUS = '"+newkznTlKaizenbankmst.getKzbnStatus()+"' " );
		sqlUpdtStatus.append(" WHERE KZBN_KEYID='"+newkznTlKaizenbankmst.getKzbnKeyid()+"' ");
        sqls.add(sqlUpdtStatus .toString());
		dbActionTemplate.executeStatements(sqls);
        return newkznTlKaizenbankmst;
	}
	@Override
	public String getEmailIdOfSuggestedBy(String kaizenNo) throws Exception {
		String sql1 = " select kzbn_suggestedby from kzn_Tl_Kaizenbankmst where kzbn_keyid='" + kaizenNo +"'";
		CommonMessage.debugMsg("Find Suggestedb By  sql :" +sql1);
		String empmKeyid= dbActionTemplate.getSingleValue(sql1);
		
		String sql2 = " select kzbn_ehsrelated from kzn_Tl_Kaizenbankmst where kzbn_keyid='" + kaizenNo +"'";
		CommonMessage.debugMsg("Find Suggestedb By  sql :" +sql2);
		String ehsrelated= dbActionTemplate.getSingleValue(sql2);
		
		String sql3 = " select kzbn_kaizen from kzn_Tl_Kaizenbankmst where kzbn_keyid='" + kaizenNo +"'";
		CommonMessage.debugMsg("Find Suggestedb By  sql :" +sql3);
		String kaizen= dbActionTemplate.getSingleValue(sql3);
		
		
		String sql = " SELECT EMPM_EMAIL FROM gen_tl_employeemst where empm_keyid='" + empmKeyid +"'";
		CommonMessage.debugMsg("Employee email sql :" +sql);
		String emailId= dbActionTemplate.getSingleValue(sql);		
		return emailId +";" +empmKeyid+";"+ehsrelated +";"+kaizen;
	}
	public String getSuggestedName(String kaizenNo) throws Exception{
		 String kaizenSuggestedName="SELECT EMPM_NAME FROM GEN_TL_EMPLOYEEMST,KZN_TL_KAIZENBANKMST WHERE EMPM_KEYID=KZBN_SUGGESTEDBY AND KZBN_KEYID='"+kaizenNo+"'";
		return dbActionTemplate.getSingleValue(kaizenSuggestedName);	
	}
	
	//******************Direct Kaizen*******************************//
  @Override
  public KznTlKaizenbankmst selectMasterKeyid(String keyid) throws NoDataFoundException, SQLException, Exception {
	KznTlKaizenbankmst kznTlKaizenbankmst = new KznTlKaizenbankmst();
	String sql = KznTlKaizenbankmstSql.selectmst(keyid);
	CommonMessage.debugMsg(sql);
	Object args [] = new Object [] { keyid };
	kznTlKaizenbankmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return kznTlKaizenbankmst;
  }
 public KznTlKaizenbankmst createDKaizen(KznTlKaizenbankmst kznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr) 	throws Exception,BusinessApplicationExceptions {
        
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		 CommonMessage.debugMsg("CreateDKaizen");
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
 public KznTlKaizenbankmst updateDKaizen(KznTlKaizenbankmst kznTlKaizenbankmst,String type,String[] dataKeyidArr,String[] dataFlidArr,String[] dataSuggestArr)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql();
		try {
			if(dataKeyidArr!=null){
				if(dataKeyidArr.length>0){
					if(dataFlidArr.length>0){
						for(int i=0;i<dataFlidArr.length;i++){
							kznTlKaizenbankmst.setKzbnKeyid(dataKeyidArr[i]);
/*							kznTlKaizenbankmst.setKzbnFlid(dataFlidArr[i]);
							kznTlKaizenbankmst.setKzbnResponsibility(dataSuggestArr[i]);
							kznTlKaizenbankmst.setKzbnSuggestedby(dataSuggestArr[i]);
							String elementId = getElementID (kznTlKaizenbankmst.getKzbnFlid());
							kznTlKaizenbankmst.setKzbnElementid(elementId);*/
							sqls.add(KznTlKaizenbankmstSql.getUpdateotherSql(kznTlKaizenbankmst,type));
						}
					}
				}
			}else
				sqls.add(KznTlKaizenbankmstSql.getUpdateSql(kznTlKaizenbankmstSql.getKzbnDbFields(), kznTlKaizenbankmst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return kznTlKaizenbankmst;
	}
 
	public List<String[]> FillThemeCategoryData(String keyid)throws Exception{
    String sql=KznTlKaizenbankmstSql.selectCategory(keyid);
    CommonMessage.debugMsg("The Sql Data:::"+sql);
    List<String[]> listData=dbActionTemplate.getDataList(sql);
	return listData;
	}
	public List<KznTlKaizenbankmst> MultipleSuggestion(List<KznTlKaizenbankmst> list) throws Exception,BusinessApplicationExceptions{
		KznTlKaizenbankmstSql kznTlKaizenbankmstSql = new KznTlKaizenbankmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		try
		{
				 List<String> sqls = new ArrayList<String>();
			     List <KznTlKaizenbankmst> methodslist = list;
				 GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),KznTlKaizenbankmstSql.TBL_KZN_TL_KAIZENBANKMST,10,"KZBN","","");		
				 for(KznTlKaizenbankmst SuggestionLink:methodslist)
				 {   
					    SuggestionDuplication(SuggestionLink);
						String seqNo=sequenceNumber.getSequnceNumber();
						SuggestionLink.setKzbnKeyid(seqNo);
						sqls.add(KznTlKaizenbankmstSql.getInsertSql(kznTlKaizenbankmstSql.getKzbnDbFields(),SuggestionLink.getSaveArray() ));
				 }
				 dbActionTemplate.executeStatements(sqls);
		}
		catch(BusinessApplicationExceptions e)
		{
			CommonMessage.debugMsg("Business Application   :"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage()); 
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return list;
	}
   private void SuggestionDuplication(KznTlKaizenbankmst suggestionLink) throws Exception{
	   String sql=KznTlKaizenbankmstSql.checkDuplication(suggestionLink);
	   String count= dbActionTemplate.getSingleValue(sql);
	   if(Integer.parseInt(count)>0)
		  throw new BusinessApplicationExceptions("duplication,");
	}
   @Override
	public List<String[]> getIndividualmaingrid(CommonFilter commonFilter)
			throws Exception {

		CommonMessage.debugMsg("Inside daoimpl");
		List<String> paramValues = new ArrayList<String>();	
		
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey()))
				condParms+="EMPID="+commonFilter.getGetKaizenkey()+";";
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
					
		CommonMessage.debugMsg("ParamValues:"+paramValues);	

		List<String[]> dataList=null;
		
		if(UIUtils.isValidKeyId(commonFilter.getSafetyMode())){
//			dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_KAIZENBANKMAIGRID",paramValues);
			dataList =   fnCallApi.callFunction("KZN_FN_KAIZENBANKMAIGRID_SB",paramValues,3,true);
		}else
		    dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("KZN_FN_SUGGINDIVIDUALGRD", paramValues);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		CommonMessage.debugMsg(dataList);
		return dataList; 
	}
	@Override
	public Workbook getIndividualKaizenMainExcel(JSONObject colmodel,String reportType,CommonFilter commonFilter) throws Exception 
	{
		  ResultSet rs = null;
		   try
		   {  
			   rs=getKaizenIndividualResultSet(commonFilter);
			   ExcelUtils excelUtils = new ExcelUtils(colmodel);
			   return excelUtils.writeToExcel(rs,reportType,  2, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());   
		   }
	}
	private ResultSet getKaizenIndividualResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 				
		if(UIUtils.isValidKeyId(commonFilter.getGetKaizenkey()))
			condParms+="EMPID="+commonFilter.getGetKaizenkey()+";";

		paramValues.add(condParms);
		paramValues.add(commonParams);
		return  dbActionTemplate.dbFunctionCall("KZN_FN_SUGGINDIVIDUALGRD", paramValues);	 	
	}

	@Override
	public List<String[]> MOCcheck(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
	/*	String MOC=dbActionTemplate.getSingleValue("SELECT nvl(moc_rfc_status,'NA') as RfcStatus  FROM KZN_TL_KAIZENBANKMST,MOC_TL_RFCMST WHERE KZBN_KEYID=MOC_RFC_SUGGESTIONID(+) AND KZBN_KEYID='"+commonFilter.getKey()+"' AND KZBN_MOCREQUIRED='Y' ");
		CommonMessage.debugMsg("the MOC"+MOC);
		return MOC;*/
		StringBuffer menuSql  = new StringBuffer();

		
//		menuSql.append("SELECT nvl(moc_rfc_status,'NA') as RfcStatus  FROM KZN_TL_KAIZENBANKMST,MOC_TL_RFCMST WHERE KZBN_KEYID=MOC_RFC_SUGGESTIONID(+) AND KZBN_MOCREQUIRED='Y'");
		menuSql.append("SELECT COALESCE(moc_rfc_status,'NA') as RfcStatus  FROM KZN_TL_KAIZENBANKMST LEFT JOIN MOC_TL_RFCMST ON KZBN_KEYID=MOC_RFC_SUGGESTIONID  WHERE  KZBN_MOCREQUIRED='Y'");


			   menuSql.append(" AND KZBN_KEYID='"+commonFilter.getKey()+"'"); 
			
			   List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
				return getPillarRelMenu;
	}
}
