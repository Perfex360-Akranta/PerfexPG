package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BAL_BDFormBean;
import com.akranta.tpm.dao.BAL_BdmTlPhenomenamstDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlCausemstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhenomenamstSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlPhncauselinkSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlShiftwisesplitSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlCausemst;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlPhenomenamst;
import com.akranta.tpm.model.BAL_BdmTlPhncauselink;
import com.akranta.tpm.model.BAL_BdmTlShiftwisesplit;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.CommonMessage;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BAL_BdmTlPhenomenamstDaoImpl implements BAL_BdmTlPhenomenamstDao {


	private DBActionTemplate dbActionTemplate; 
	private BAL_BdmTlCausemstSql bdmTlCausemstSql;
	private BAL_BdmTlMstSql bdmTlMstSql;
	private BAL_BdmTlPhncauselinkSql bdmTlPhncauselinkSql;
	private FunctionCallApi fnCallApi;


	public BAL_BdmTlPhenomenamstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		bdmTlCausemstSql = new BAL_BdmTlCausemstSql();
		bdmTlMstSql = new BAL_BdmTlMstSql();
		bdmTlPhncauselinkSql = new BAL_BdmTlPhncauselinkSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void BAL_BdmTlPhenomenamstDaoImplJwt(String jwtToken) {
	    try {
	        fnCallApi = new FunctionCallApi(jwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public BAL_BdmTlPhenomenamst create(BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_BdmTlPhenomenamstSql bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		String phenExistFlag = checkPhenExist(bdmTlPhenomenamst,bdFormBean);
			
			if(phenExistFlag == null)			{
				bdmTlPhenomenamst.setBphmKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlPhenomenamstSql.TBL_BAL_BDM_TL_PHENOMENAMST)); // set the sequnce number
				if(bdmTlPhenomenamst.getBphmShortname().equals("{}"))
					bdmTlPhenomenamst.setBphmShortname(bdmTlPhenomenamst.getBphmKeyid());
				sqls.add(BAL_BdmTlPhenomenamstSql.getInsertSql(bdmTlPhenomenamstSql.getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for master table
				if(bdmTlPhenomenamst.getPhnCauseLink()!= null && bdmTlPhenomenamst.getPhnCauseLink().size()>0)
				{
					BAL_BdmTlPhncauselink phenCauseLink = (BAL_BdmTlPhncauselink)bdmTlPhenomenamst.getPhnCauseLink().get(0);
					insertPhenCauseLink(phenCauseLink,bdmTlPhenomenamst.getBphmKeyid(),bdmTlPhenomenamst.getBphmPhenomenaname(),sqls);
				}
				insertCause(bdmTlPhenomenamst,bdFormBean,sqls);
				updatePhenCauseInBdmMst(bdmTlPhenomenamst,bdFormBean,sqls);

			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	
		return bdmTlPhenomenamst;
	}
	private List<String> insertPhenCauseLink(BAL_BdmTlPhncauselink phenCauseLink,String linkId,String linkName,List<String> sqls) throws Exception {
		
		if(linkId.contains("-"))
		{
			phenCauseLink.setBpclOriginalid(linkId.substring(linkId.indexOf("-")+1));
			phenCauseLink.setBpclElementtype("CAS");
		}
		else
		{
			phenCauseLink.setBpclOriginalid(linkId);
			phenCauseLink.setBpclElementtype("PHN");
		}
		if(phenCauseLink.getBpclElementid().endsWith("-"))
			phenCauseLink.setBpclElementid(phenCauseLink.getBpclElementid()+linkId);
		else
		{
			phenCauseLink.setBpclParentid(phenCauseLink.getBpclElementid());
			phenCauseLink.setBpclElementid(phenCauseLink.getBpclElementid()+"-"+phenCauseLink.getBpclOriginalid());
		}
		phenCauseLink.setBpclDisplaycode(linkName);
		
		sqls.add(BAL_BdmTlPhncauselinkSql.getInsertSql(bdmTlPhncauselinkSql.getBpclDbFields(), phenCauseLink.getSaveArray())); // add insert sql for master table
		return sqls;
		
	}
	
	private List<String> insertCause(BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean,List<String> sqls) throws Exception {
		if(bdmTlPhenomenamst.getBdmTlCausemst()!= null && bdmTlPhenomenamst.getBdmTlCausemst().size()>0) // check for detail table data
		{	
			
			BAL_BdmTlCausemst causeMst = (BAL_BdmTlCausemst)bdmTlPhenomenamst.getBdmTlCausemst().get(0);
			String causeExistFlag = checkCauseExist(causeMst,bdmTlPhenomenamst,bdFormBean);
			if(causeExistFlag.equals("NotExist"))
			{
				causeMst.setBcsmPhenomenaid(bdmTlPhenomenamst.getBphmKeyid());				
				causeMst.setBcsmKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_BDM_TL_CAUSEMST));
				causeMst.setBcsmCode(causeMst.getBcsmKeyid());
				sqls.add(BAL_BdmTlCausemstSql.getInsertSql(bdmTlCausemstSql.getBcsmDbFields(), causeMst.getSaveArray()));// add insert sql for detail table
				BAL_BdmTlPhncauselink phenCauseLink = (BAL_BdmTlPhncauselink)bdmTlPhenomenamst.getPhnCauseLink().get(0);
				insertPhenCauseLink(phenCauseLink,bdmTlPhenomenamst.getBphmKeyid()+"-"+causeMst.getBcsmKeyid(),causeMst.getBcsmName(),sqls);
			}
		}
		return sqls;
	}
	private List<String> updatePhenCauseInBdmMst(BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean,List<String> sqls) throws Exception {
		if(bdmTlPhenomenamst.getBdmTlCausemst()!= null && bdmTlPhenomenamst.getBdmTlCausemst().size()>0) // check for detail table data
		{	
			BAL_BdmTlCausemst causeMst = (BAL_BdmTlCausemst)bdmTlPhenomenamst.getBdmTlCausemst().get(0);
			String sql = BAL_BdmTlMstSql.getUpdatePhenSql(bdmTlPhenomenamst.getBphmKeyid(), causeMst.getBcsmKeyid(), bdFormBean.getBphmBddetails());
			System.out.println("UPDATE PHEN & CAUSE SQL :- "+sql);
			sqls.add(sql);
		}
		return sqls;
	
	}


	public BAL_BdmTlPhenomenamst update(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlPhenomenamstSql bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql();
		try {

			sqls.add(BAL_BdmTlPhenomenamstSql.getUpdateSql(bdmTlPhenomenamstSql.getBphmDbFields(), bdmTlPhenomenamst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlPhenomenamst;
	}
	
	public BAL_BdmTlPhenomenamst delete(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlPhenomenamstSql bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql();
		try {
			
			sqls.add(BAL_BdmTlPhenomenamstSql.getDeleteSql(bdmTlPhenomenamstSql.getBphmDbFields(), bdmTlPhenomenamst.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlPhenomenamst;
	}
	
	public  List<FunctionalLocn> getPhenomenaCause(FunctionalLocn functionalLocn) throws Exception
	{
		try
		{
			String sql = "select  * from BDM_VW_ASMPHNCASLAYOUT";
			if( functionalLocn.getElementId().equals("1"))
				sql +=	" where  INSTR(PARENTID,'"+functionalLocn.getParentId()+"') > 0 ";
			else
				sql +=	" where parentid ='"+functionalLocn.getElementId() +"' and displaycode!= '" + functionalLocn.getElementType() +"'";
			System.out.println(sql);
			List resultList = dbActionTemplate.getDataList(sql);
			return fillTree(resultList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
		
	}
	
	
	   public List<String[]> getUndefinedPhenomena(String mode) throws Exception {
			
			try
			{
				String sql = BAL_BdmTlPhenomenamstSql.getUndefinedPhenSql();
				List<String > paramValues = new ArrayList<String>();		
				paramValues.add(mode);
				paramValues.add("{}");
				paramValues.add("{}");
				paramValues.add("{}");
				
				List<String[] > upDatas =  dbActionTemplate.processFunctionCalls(sql,paramValues);
				System.out.println(upDatas.size());
				return upDatas;
			}
			catch(Exception e)
			{
				System.out.println("MSG - " +e.getMessage());				
				e.printStackTrace();
			}
			return null;
		}
	   
	private List<FunctionalLocn> fillTree(List<String []> resultList) throws SQLException
	{
		
		   List<FunctionalLocn> menus = new ArrayList<FunctionalLocn>();
		   for( String [] row : resultList )
		   {
			   FunctionalLocn fl = new FunctionalLocn();
				System.out.println(row[0]+" : "+row[1]+" : "+row[2]);
				fl.setOriginalId(row[0]);
				fl.setElementId(row[0]);
				fl.setParentId(row[1]);
				fl.setElementType(row[2]);
				fl.setDisplayCode(row[2]);			
				
				menus.add(fl);
	
			   
		   }
  		  return menus;
	 }
	public String checkPhenExist(BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean) 
	{
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(bdmTlPhenomenamst.getBphmPhenomenaname());
		paramValues.add(bdmTlPhenomenamst.getBphmAssemblyid());
		paramValues.add(bdFormBean.getBphmMachineid());
		
		System.out.println(paramValues.get(0)+":"+paramValues.get(1)+":"+paramValues.get(2));
		String sql = BAL_BdmTlPhenomenamstSql.getBphmKey();
		System.out.println(sql);
		//Object args [] = new Object [] {paramValues.get(0),paramValues.get(1),paramValues.get(2)};
		try {
			 List<String[]> phenName = dbActionTemplate.getDataList(sql, paramValues);
			if(phenName.size() <= 0)
				return null;
			else
				return phenName.get(0).toString();
				
		} catch (NoDataFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exc : "+e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exc2 : "+e.getMessage());
		}
		return null;
		
	}
	
	private String checkCauseExist(BAL_BdmTlCausemst causeMst,BAL_BdmTlPhenomenamst bdmTlPhenomenamst,BAL_BDFormBean bdFormBean) {
		// TODO Auto-generated method stub
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(causeMst.getBcsmName());
		paramValues.add(bdmTlPhenomenamst.getBphmAssemblyid());
		paramValues.add(bdFormBean.getBphmMachineid());
		paramValues.add(bdmTlPhenomenamst.getBphmKeyid());
		System.out.println(paramValues.get(0)+":"+paramValues.get(1)+":"+paramValues.get(2));
		String sql = BAL_BdmTlPhenomenamstSql.getCauseKey(paramValues);
		System.out.println("CheckCauseExistSql : "+sql);
		try {
			List<String[]> causeFlag = dbActionTemplate.getDataList(sql);
			if(causeFlag.size() <= 0)
				return "NotExist";
			else
				return causeFlag.get(0).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BAL_BdmTlPhenomenamst createPhenomena(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)
			throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_BdmTlPhenomenamstSql bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		bdmTlPhenomenamst.setBphmKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlPhenomenamstSql.TBL_BAL_BDM_TL_PHENOMENAMST)); // set the sequnce number
		if(bdmTlPhenomenamst.getBphmShortname().equals("{}"))
			bdmTlPhenomenamst.setBphmShortname(bdmTlPhenomenamst.getBphmKeyid());
		sqls.add(BAL_BdmTlPhenomenamstSql.getInsertSql(bdmTlPhenomenamstSql.getBphmDbFields(), bdmTlPhenomenamst.getSaveArray())); // add insert sql for master table
		dbActionTemplate.executeStatements(sqls);
		return bdmTlPhenomenamst;
	}
	
	public BAL_BdmTlPhenomenamst updatePhenomena(BAL_BdmTlPhenomenamst bdmTlPhenomenamst)throws Exception {
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlPhenomenamstSql bdmTlPhenomenamstSql = new BAL_BdmTlPhenomenamstSql();
		try {
			sqls.add(BAL_BdmTlPhenomenamstSql.getUpdateSql(bdmTlPhenomenamstSql.getBphmDbFields(), bdmTlPhenomenamst.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlPhenomenamst;
	}
	
	@Override
	public List<String[]> getPhenomenaGridData(CommonFilter commonFilter, List<String> assemblyIdList) throws Exception {
	    try {
	        if (fnCallApi == null) {
	            throw new Exception("FunctionCallApi not initialized.");
	        }

	        List<String> paramValues = new ArrayList<String>();

	        String condParms = "";

	        if (assemblyIdList != null && !assemblyIdList.isEmpty()) {
	            String assemblyIdCsv = String.join(",", assemblyIdList);
	            condParms = "ASSEMBLYID=" + assemblyIdCsv + ";";
	        }

	        String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	        CommonMessage.debugMsg("getPhenomenaList condParms=[" + condParms + "]");

	        paramValues.add(condParms);
	        paramValues.add(commonParams);

	        List<String[]> dataList = fnCallApi.callFunction(
	                "BAL_BDM_FN_PHENOMENAMST", paramValues, 3, true);

	        System.out.println("Phenomena paramValues after function call = " + paramValues);

	        if (commonFilter.getViewClick() == 'Y') {
	            String totalCnt = paramValues.get(0);

	            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	            if (isInteger) {
	                commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	            }
	        }

	        return dataList;

	    } catch (Exception e) {
	        CommonMessage.debugMsg("getPhenomenaList error: " + e.getMessage());
	        throw new Exception(e.getMessage());
	    }
	}
	
	@Override
	public List<String[]> getCauseList(CommonFilter commonFilter, String phenId) throws Exception {
	    try {
	        if (fnCallApi == null) {
	            throw new Exception("FunctionCallApi not initialized.");
	        }

	        List<String> paramValues = new ArrayList<>();

	        String condParms = "";
	        if (CommonFunctions.isValidKeyId(phenId)) {
	            condParms = "PHENOMENAID=" + phenId + ";";
	        }
	        String commonParams = FilterCondSql.getGridCommonParams(commonFilter);

	        CommonMessage.debugMsg("getCauseList condParms=[" + condParms + "]");

	        paramValues.add(condParms);
	        paramValues.add(commonParams);

	        List<String[]> dataList = fnCallApi.callFunction(
	                "BAL_BDM_FN_CAUSEMST", paramValues, 3, true);

	        if (commonFilter.getViewClick() == 'Y') {
	            String totalCnt = paramValues.get(0);
	            boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	            if (isInteger) {
	                commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	            }
	        }

	        return dataList;

	    } catch (Exception e) {
	        CommonMessage.debugMsg("getCauseList error: " + e.getMessage());
	        throw new Exception(e.getMessage());
	    }
	}

	
public BAL_BdmTlCausemst createCause(BAL_BdmTlCausemst bdmTlCausemst)throws Exception {
	System.out.println("DAO IMPL.............");
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	BAL_BdmTlCausemstSql bdmTlCausemstSql = new BAL_BdmTlCausemstSql(); // contains dbtable,field names, Field types and related sqls  of master table
	bdmTlCausemst.setBcsmKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_BAL_BDM_TL_CAUSEMST)); // set the sequnce number
	bdmTlCausemst.setBcsmCode(bdmTlCausemst.getBcsmKeyid());
	sqls.add(BAL_BdmTlCausemstSql.getInsertSql(bdmTlCausemstSql.getBcsmDbFields(), bdmTlCausemst.getSaveArray())); // add insert sql for master table
	dbActionTemplate.executeStatements(sqls);
	return bdmTlCausemst;	
}

public BAL_BdmTlCausemst updateCause(BAL_BdmTlCausemst bdmTlCausemst)throws Exception {
List<String> sqls = new ArrayList<String>();
BAL_BdmTlCausemstSql bdmTlCausemstSql = new BAL_BdmTlCausemstSql(); 
try {
	sqls.add(BAL_BdmTlCausemstSql.getUpdateSql(bdmTlCausemstSql.getBcsmDbFields(), bdmTlCausemst.getSaveArray()));			
	dbActionTemplate.executeStatements(sqls);			
} catch (Exception e) {
	// TODO Auto-generated catch block
	throw new Exception(e.getMessage());
}

return bdmTlCausemst;
}

@Override
public Workbook getUndefPhenReport(String mode,JSONObject colmodel, String rptFormat) throws Exception {
	   ResultSet rs = null;
	   try{
		
		rs =   getGenMaintRptResultSet(mode);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
private ResultSet getGenMaintRptResultSet(String mode) throws Exception
{
	//String sql = BdmTlPhenomenamstSql.getUndefinedPhenSql();
	List<String > paramValues = new ArrayList<String>();
	paramValues.add(mode);
	paramValues.add("{}");
	paramValues.add("{}");
	paramValues.add("{}");
	CommonFunctions.debugMsg("paramValues"+paramValues);
	
	return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_FILLBREAKDOWN", paramValues);
}
/*private List<String> getFilterParamValues(){
	List<String> paramValues = new ArrayList<String>();
	//String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
	// String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	 
	 //paramValues.add(condParms);
	// paramValues.add(commonParams);
	 CommonFunctions.debugMsg("paramValues"+paramValues);
	
	return paramValues;
}	
*/
}

