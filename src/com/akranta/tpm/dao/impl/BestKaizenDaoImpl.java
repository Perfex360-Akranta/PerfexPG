package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BestKaizenDao;
//import com.akranta.tpm.dao.sql.FilterCondNewSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.KznTlBestdtlSql;
import com.akranta.tpm.dao.sql.KznTlBestmstSql;
import com.akranta.tpm.dao.sql.KznTlBestdtlSql;
import com.akranta.tpm.dao.sql.KznTlEvaluationmstSql;
import com.akranta.tpm.dao.sql.KznTlKaizenbankmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.KznTlBestmst;
import com.akranta.tpm.model.KznTlBestdtl;
import com.akranta.tpm.model.KznTlEvaluationmst;
import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.service.api.BestKaizenServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
//import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class BestKaizenDaoImpl implements BestKaizenDao {
	
	private DBActionTemplate dbActionTemplate;
	FunctionCallApi fnCallApi;	
	BestKaizenServiceApi serviceApi;
	public BestKaizenDaoImpl(DBActionTemplate dbActionTemplate) {
		
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void BestKaizenDaoImplJwt(String JwtToken) 
	{
		try{
			serviceApi = new BestKaizenServiceApi (JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public List<String[]> getBestKaizen(CommonFilter commonFilter)throws Exception {
		 CommonMessage.debugMsg(commonFilter.getKznBankType()+"getBestKaizen Inside daoimpl   "+commonFilter.getFromMonth());
		List<String> paramValues = new ArrayList<String>();
		String quater="0";
		if(UIUtils.isValidKeyId(commonFilter.getFromMonth()))
		{
			String str=((commonFilter.getFromMonth()).toUpperCase()).substring(0, 3);
			if(str.equals("JAN") || str.equals("FEB") || str.equals("MAR"))
				quater="1";
			else if(str.equals("APR") || str.equals("MAY") || str.equals("JUN"))
				quater="2";
			else if(str.equals("JUL") || str.equals("AUG") || str.equals("SEP"))
				quater="3";
			else if(str.equals("OCT") || str.equals("NOV") || str.equals("DEC"))
				quater="4";
			if(commonFilter.getFromMonth().equals(Constants.passNullMonth) || commonFilter.getFromMonth().equals(Constants.futureNullMonth))
			commonFilter.setFromMonth("");
		}else
			commonFilter.setFromMonth("");
		if(!UIUtils.isValidKeyId(commonFilter.getKznBankType()))
			commonFilter.setKznBankType("");
		
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms+"LEVEL="+commonFilter.getKznBankType()+";QTR="+quater+";");
		paramValues.add(commonParams);
		List<String[]> dataList = new ArrayList<String[]>() ;
		CommonMessage.debugMsg(paramValues +"paramValuesparamValuesparamValues in best kaizen");
		
		//dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_BESTKAIZEN", paramValues);
		dataList =   fnCallApi.callFunction("GEN_FN_BESTKAIZEN_SB", paramValues,3,false);
		CommonMessage.debugMsg(commonFilter.getViewClick() +"  Inside daoimpl : " + dataList.size());
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
			CommonMessage.debugMsg(totalCnt+" Inside daoimpl : " + dataList.size());

		}
		return dataList; 
	}
	@Override
	public KznTlBestmst create(KznTlBestmst kznTlBestmst,KznTlBestmst existKznTlBestmst) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlBestmstSql kznTlBestmstSql = new KznTlBestmstSql();
		KznTlBestdtlSql kznTlBestdtlSql = new KznTlBestdtlSql();
		try{
			kznTlBestmst.setKzbmKeyid(dbActionTemplate.getSequenceNumber(KznTlBestmstSql.TBL_KZN_TL_BESTMST ,10, "KZBM", "", ""));
			sqls.add(KznTlBestmstSql.getInsertSql(kznTlBestmstSql.getKzbmDbFields(), kznTlBestmst.getSaveArray())); 
			List<KznTlBestdtl> kznTlBstdtl= kznTlBestmst.getKznTlBestdtl();
			CommonMessage.debugMsg("SIZE OF DTL "+kznTlBstdtl.size());
			if( kznTlBstdtl != null && kznTlBstdtl.size()> 0 )
			{	
				for( KznTlBestdtl kznTlBestdtl : kznTlBstdtl)
				{	
					CommonMessage.debugMsg(kznTlBestmst.getKzbmKeyid()+"  insert master   detail key id  "+kznTlBestdtl.getKzbdKeyid());
					if(!UIUtils.isValidKeyId(kznTlBestdtl.getKzbdKeyid())){
						kznTlBestdtl.setKzbdKeyid(dbActionTemplate.getSequenceNumber(KznTlBestdtlSql.TBL_KZN_TL_BESTDTL, 10, "KZBD", "", ""));
						kznTlBestdtl.setKzbdKzbmKeyid(kznTlBestmst.getKzbmKeyid());
						sqls.add(KznTlBestdtlSql.getInsertSql(kznTlBestdtlSql.getKzbdDbFields(), kznTlBestdtl.getSaveArray()));// add insert sql for detail table
					}else{
						kznTlBestdtl.setKzbdKzbmKeyid(kznTlBestmst.getKzbmKeyid());
						sqls.add(KznTlBestdtlSql.getUpdateSql(kznTlBestdtlSql.getKzbdDbFields(), kznTlBestdtl.getSaveArray()));// add insert sql for detail table
					}
				}
		    }
			CommonMessage.debugMsg("SQLS   "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return kznTlBestmst;
	}
	@Override
	public KznTlBestmst update(KznTlBestmst kznTlBestmst,KznTlBestmst existKznTlBestmst) throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KznTlBestmstSql kznTlBestmstSql = new KznTlBestmstSql();
		KznTlBestdtlSql kznTlBestdtlSql = new KznTlBestdtlSql();
		try{
			//kznTlBestmst.setKzbmKeyid(dbActionTemplate.getSequenceNumber(KznTlBestmstSql.TBL_KZN_TL_BESTMST ,10, "KZBM", "", ""));
			sqls.add(KznTlBestmstSql.getUpdateSql(kznTlBestmstSql.getKzbmDbFields(), kznTlBestmst.getSaveArray())); 
			List<KznTlBestdtl> kznTlBstdtl= kznTlBestmst.getKznTlBestdtl();
			List<KznTlBestdtl> dltBstdtl= existKznTlBestmst.getKznTlBestdtl();
			CommonMessage.debugMsg("SIZE OF DTL "+kznTlBstdtl.size());
			if( dltBstdtl != null && dltBstdtl.size()> 0 )
			{	
				for( KznTlBestdtl kznTlBestdtl : dltBstdtl)
				{	
					sqls.add(KznTlBestdtlSql.getDeleteSql(kznTlBestdtlSql.getKzbdDbFields(), kznTlBestdtl.getSaveArray()));// add insert sql for detail table
				}
		    }
			if( kznTlBstdtl != null && kznTlBstdtl.size()> 0 )
			{	
				for( KznTlBestdtl kznTlBestdtl : kznTlBstdtl)
				{	
					if(!UIUtils.isValidKeyId(kznTlBestdtl.getKzbdKeyid())){
						kznTlBestdtl.setKzbdKeyid(dbActionTemplate.getSequenceNumber(KznTlBestdtlSql.TBL_KZN_TL_BESTDTL, 10, "KZBD", "", ""));
						kznTlBestdtl.setKzbdKzbmKeyid(kznTlBestmst.getKzbmKeyid());
						sqls.add(KznTlBestdtlSql.getInsertSql(kznTlBestdtlSql.getKzbdDbFields(), kznTlBestdtl.getSaveArray()));// add insert sql for detail table
					}else{
						kznTlBestdtl.setKzbdKzbmKeyid(kznTlBestmst.getKzbmKeyid());
						sqls.add(KznTlBestdtlSql.getUpdateSql(kznTlBestdtlSql.getKzbdDbFields(), kznTlBestdtl.getSaveArray()));// add insert sql for detail table
					}
				}
		    }
			CommonMessage.debugMsg("SQLS   "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return kznTlBestmst;
	}
	@Override
	public List<String []> selectData(CommonFilter commonFilter) throws NoDataFoundException, SQLException, Exception {
		KznTlBestmst kznTlBestmst = new KznTlBestmst();
		String sql = KznTlBestmstSql.selectmst(commonFilter);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}
	@Override
	public List<String[]> getBestKzngrdData(CommonFilter commonFilter)throws Exception {
		 CommonMessage.debugMsg("Inside daoimpl   "+commonFilter.getFromMonth());
		List<String> paramValues = new ArrayList<String>();	
		if(!UIUtils.isValidKeyId(commonFilter.getKznBankType()))
			commonFilter.setKznBankType("");
		String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms+"LEVEL="+commonFilter.getKznBankType()+";");
		paramValues.add(commonParams);
		//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_BESTKAIZENJH_SB", paramValues);
		List<String[]> dataList =   fnCallApi.callFunction("GEN_FN_BESTKAIZENJH_SB", paramValues,3,false);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}
		return dataList; 
	}
	@Override
	public KznTlBestmst selectmstData(String keyid) throws NoDataFoundException, SQLException, Exception {
		KznTlBestmst kznTlBestmst = new KznTlBestmst();
		String sql = KznTlBestmstSql.selectmstData();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		kznTlBestmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return kznTlBestmst;
	}
	@Override
	public KznTlBestmst delete(KznTlBestmst kznTlBestmst) throws Exception {
		List<String> sqls = new ArrayList<String>();
		KznTlBestmstSql kznTlBestmstSql = new KznTlBestmstSql();
		KznTlBestdtlSql kznTlBestdtlSql = new KznTlBestdtlSql();
		try {
			sqls.add(KznTlBestdtlSql.getDeletemst(kznTlBestmst.getKzbmKeyid()));
			sqls.add(KznTlBestmstSql.getDeleteSql(kznTlBestmstSql.getKzbmDbFields(), kznTlBestmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kznTlBestmst;
	}
	
	@Override
	public Workbook getBestKaizenJhExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try{
			
			 rs =   getbestkaizenjhResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 2,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	
	private ResultSet getbestkaizenjhResultSet(CommonFilter commonFilter) throws Exception{
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_BESTKAIZEN", paramValues);

	}
	
	@Override
	public Workbook getbestkaizenExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getbestkaizenResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 3,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	
	private ResultSet getbestkaizenResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		commonFilter.setFromRow(null);//Change here 
		commonFilter.setToRow(null);//Change here 
		List<String> paramValues = getFilterParamValues(commonFilter);
		CommonMessage.debugMsg("result");
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_BESTKAIZENJH_SB", paramValues);
		
		
	}
	private List<String> getFilterParamValues(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		   CommonMessage.debugMsg(commonFilter.getKznBankType()+"getBestKaizen Checking for Excel   "+commonFilter.getFromMonth());
		   List<String> paramValues = new ArrayList<String>();
		   String quater="0";
			if(UIUtils.isValidKeyId(commonFilter.getFromMonth()))
			{
				String str=((commonFilter.getFromMonth()).toUpperCase()).substring(0, 3);
				if(str.equals("JAN") || str.equals("FEB") || str.equals("MAR"))
					quater="1";
				else if(str.equals("APR") || str.equals("MAY") || str.equals("JUN"))
					quater="2";
				else if(str.equals("JUL") || str.equals("AUG") || str.equals("SEP"))
					quater="3";
				else if(str.equals("OCT") || str.equals("NOV") || str.equals("DEC"))
					quater="4";
				if(commonFilter.getFromMonth().equals(Constants.passNullMonth) || commonFilter.getFromMonth().equals(Constants.futureNullMonth))
				commonFilter.setFromMonth("");
			}else
				commonFilter.setFromMonth("");
			if(!UIUtils.isValidKeyId(commonFilter.getKznBankType()))
				commonFilter.setKznBankType("");
			String condParms = FilterCondSql.getKAIZENRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			paramValues.add(condParms+"LEVEL="+commonFilter.getKznBankType()+";QTR="+quater+";");
			paramValues.add(commonParams);
	
		    return paramValues;
	}
	
}
