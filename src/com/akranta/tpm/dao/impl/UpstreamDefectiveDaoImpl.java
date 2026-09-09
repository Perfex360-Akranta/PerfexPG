package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.UpstreamDefect;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.UpstreamDefectiveDAO;
import com.akranta.tpm.dao.sql.FilterCondSql;
//import com.akranta.tpm.dao.sql.GenTlControlandresponseplanSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.GenTlUpstreamdefectDetSql;
import com.akranta.tpm.dao.sql.GenTlUpstreamdefectMstSql;
import com.akranta.tpm.dao.sql.GenTlUpstreamdefectSql;
import com.akranta.tpm.dao.sql.GenTlVisitorsSql;
import com.akranta.tpm.dao.sql.JhaTlFiveSAuditareamstSql;
//import com.akranta.tpm.dao.sql.PlmTlConditionalappraisalSql;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlUpstreamdefect;
import com.akranta.tpm.model.GenTlUpstreamdefectDet;
import com.akranta.tpm.model.GenTlUpstreamdefectMst;
import com.akranta.tpm.model.JhaTlFiveSAuditareamst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.UpstreamdefectServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;



public class UpstreamDefectiveDaoImpl implements UpstreamDefectiveDAO 
{
	private DBActionTemplate dbActionTemplate; 
	

	private UpstreamdefectServiceApi upstreamdefectServiceApi;
	FunctionCallApi fnCallApi;
	public UpstreamDefectiveDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	
    public void UpstreamDefectiveDaoImplJwt(String jwtToken) {
		
		try{
			upstreamdefectServiceApi = new UpstreamdefectServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public List<String[]> getUpstreamGrid(CommonFilter commonFilter,String keyid) throws Exception 
	{
		// TODO Auto-generated method stub
		
		
		
			
		/*String sql=" select '','JH42201','TXF','pulp from pulp mill','Hi delta B (OBA contamination)',' Wetend shift incharge & other paper m/cs to not to send back water to ring dilution tank','Add OBA quencher in Mixing & m/c chest' FROM DUAL";
                
		        sql+="  UNION ALL select '','JH42201','TXF','Pulp from pulp mill','Black specks','Pulp mill S/I & wet end S/I','Increase fiber mizer rejects If possible draw other pulp more from SFT'FROM DUAL"; 
                sql+="  UNION ALL select '','JH42201','TXG','Pulp from pulp mill','Shives','Pulp mill S/I & wet end S/I','Increase fiber mizer rejects If possible draw other pulp more from SFT'FROM DUAL"; 
                sql+="  UNION ALL select '','JH42201','TXF','Pulp from pulp mill','Incoming SR(<19,>22)','Pulp mill S/I & wet end S/I','Adjust refiner loads'FROM DUAL";
                sql+="  UNION ALL select '','JH42201','TXG','Pulp from pulp mill','Ph(<6.5,>7.2)','Pulp mill S/I & wet end S/I','Adjust alum dosage' FROM DUAL"; 
                sql+="  UNION ALL select '','JH42201','TXF','Pulp from pulp mill','Brightness(<85,>89)','Pulp mill S/I & wet end S/I','If possible draw other pulp more from SFT' FROM DUAL";
                sql+="  UNION ALL select '','JH42201','TXG','Pulp from pulp mill','Ph(<6.5,>7.2)','Pulp mill S/I & wet end S/I','Adjust alum dosage' FROM DUAL";
                sql+="  UNION ALL select '','JH42201','TXF','Pulp from SFT','Hi delta B (OBA contamination)','Wetend shift incharge & SFT S/I','Add OBA quencher in Mixing & m/c chest' FROM DUAL";
                CommonMessage.debugMsg("sql..." + sql);
	       		List<String[]> gridData = dbActionTemplate.getDataList(sql);
	       		CommonMessage.debugMsg("Grid value" + gridData.get(1));
	       		return gridData;*/
		
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			if(!UIUtils.isValidKeyId(keyid))
				keyid="";
			condParms=condParms+"KEYID="+keyid+";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_UPSTREAMDEFECTS", paramValues);
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_UPSTREAMDEFECTS", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("QTM_FN_UPSTREAMDEFECTS_SB", paramValues,3,true);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}

	
	@Override
	public List<String[]> getUpstreamFormGrid(CommonFilter commonFilter, String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............ Passed :: "+keyId);
			
			
			
			String FNLNID=commonFilter.getFlid();
			String DATE=commonFilter.getDteend();
			String KEYID=commonFilter.getKey();
			
			CommonMessage.debugMsg(" setKey "+KEYID);
			
			if(UIUtils.isValidKeyId(commonFilter.getDteend()))
			   condParms+="DATE="+DATE+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getFlid()))
			   condParms+="FLID="+FNLNID+";";
			
			//if(UIUtils.isValidKeyId(commonFilter.getKey()))
				//   condParms+="KEYID="+KEYID+";";
			
			if(UIUtils.isValidKeyId(keyId))
				   condParms+="KEYID="+keyId+";";
				
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			CommonMessage.debugMsg(" FNLNID :: "+FNLNID+" DATE :: "+DATE+" KEYID :: "+KEYID);
			
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_UPSTREAMDEFECTSGRID", paramValues);
			//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("QTM_FN_UPSTREAMDEFECTSGRID", paramValues);
			List<String[]> dataList =  fnCallApi.callFunction("QTM_FN_UPSTREAMDEFECTSGRID_SB", paramValues,3,true);
			
			if( commonFilter.getViewClick() == 'Y')
			{
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt..."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger )
				{
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
	}
	catch (Exception e)
	{
		throw new Exception(e.getMessage()); 
		//e.printStackTrace();
	}
	}
	
	@Override
	public Workbook getUpstreamDefectExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getUpstreamDefectResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,1,0 );//elumalai
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet getUpstreamDefectResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		paramValues.add(condParms);
		paramValues.add(commonParams);
        return dbActionTemplate.NewdbFunctionCall2("QTM_FN_UPSTREAMDEFECTS", paramValues);
	
	}
	
	@Override
	public GenTlUpstreamdefect createUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
		   GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlUpstreamdefectSql genTlUpstreamdefectSql=new GenTlUpstreamdefectSql();	
		try{
			CommonMessage.debugMsg(" Inside Dao Impl:: try block ");
			newGenTlUpstreamdefect.setUpsdKeyid(dbActionTemplate.getSequenceNumber("TBL_GEN_TL_UPSTREAMDEFECT", 10, "UPST", "", "Y")); // set the sequnce number 
			sqls.add(GenTlUpstreamdefectSql.getInsertSql(genTlUpstreamdefectSql.getUpsdDbFields(), newGenTlUpstreamdefect.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return newGenTlUpstreamdefect;
	}

	@Override
	public GenTlUpstreamdefect updateUpstream(GenTlUpstreamdefect newGenTlUpstreamdefect,
		   GenTlUpstreamdefect existGenTlUpstreamdefect,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
        GenTlUpstreamdefectSql genTlUpstreamdefectSql=new GenTlUpstreamdefectSql();	
				
        try{
			CommonMessage.debugMsg(" Inside Dao Impl:: try block ");
			 
			sqls.add(GenTlUpstreamdefectSql.getUpdateSql(genTlUpstreamdefectSql.getUpsdDbFields(), newGenTlUpstreamdefect.getSaveArray())); // add insert sql for master table

			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return newGenTlUpstreamdefect;
	}

	@Override
	public GenTlUpstreamdefectMst createNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
		   GenTlUpstreamdefectMst existGenTlUpstreamdefectMst, GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		 
		GenTlUpstreamdefectMstSql genTlUpstreamdefectMstSql =new GenTlUpstreamdefectMstSql();
		GenTlUpstreamdefectDetSql genTlUpstreamdefectDetSql =new GenTlUpstreamdefectDetSql();
		
		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		
		//newGenTlUpstreamdefectDet=newGenTlUpstreamdefectMst.getUpstreamdefect();
		try{
				
			    CommonMessage.debugMsg(" Inside Dao Impl:: try block "+newGenTlUpstreamdefectDet.getUpsdPreventiveaction());
			    CommonMessage.debugMsg(" Inside Dao Impl:: try block "+newGenTlUpstreamdefectDet.getUpsdRawmaterial());
			    CommonMessage.debugMsg(" Inside Dao Impl:: try block "+newGenTlUpstreamdefectDet.getUpsdInformto());
			    CommonMessage.debugMsg(" Inside Dao Impl:: try block "+newGenTlUpstreamdefectDet.getUpsdActive());
				
			    if(newGenTlUpstreamdefectMst.getUpsmKeyid()==null){
				
					newGenTlUpstreamdefectMst.setUpsmKeyid(dbActionTemplate.getSequenceNumber("TBL_GEN_TL_UPSTREAMDEFECT_MST", 10, "UPSM", "", "Y")); // set the sequnce number
				    sqls.add(GenTlUpstreamdefectMstSql.getInsertSql(genTlUpstreamdefectMstSql.getUpsmDbFields(), newGenTlUpstreamdefectMst.getSaveArray())); // add insert sql for master table
				    newGenTlUpstreamdefectDet.setUpsdUpsmKeyid(newGenTlUpstreamdefectMst.getUpsmKeyid());
				
				if(newGenTlUpstreamdefectDet.getUpsdKeyid()==null){
					
					newGenTlUpstreamdefectDet.setUpsdKeyid(dbActionTemplate.getSequenceNumber("TBL_GEN_TL_UPSTREAMDEFECT_DET", 10, "UPSD", "", "Y")); // set the sequnce number
					fillValuesNewgenTlUpstreamdtl(newGenTlUpstreamdefectDet, existGenTlUpstreamdefectDet,upstreamDefect);
					sqls.add(GenTlUpstreamdefectDetSql.getInsertSql(genTlUpstreamdefectDetSql.getUpsdDbFields(), newGenTlUpstreamdefectDet.getSaveArray())); // add insert sql for master table
				   
				   }

			    }
			    
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return newGenTlUpstreamdefectMst;

	}

	@Override
	public GenTlUpstreamdefectMst updateNewUpstream(GenTlUpstreamdefectMst newGenTlUpstreamdefectMst,
		   GenTlUpstreamdefectMst existGenTlUpstreamdefectMst, GenTlUpstreamdefectDet newGenTlUpstreamdefectDet ,UpstreamDefect upstreamDefect) throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		 
		GenTlUpstreamdefectMstSql genTlUpstreamdefectMstSql =new GenTlUpstreamdefectMstSql();
		GenTlUpstreamdefectDetSql genTlUpstreamdefectDetSql =new GenTlUpstreamdefectDetSql();
		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		
		CommonMessage.debugMsg(" Inside :: Dao Impl  "+newGenTlUpstreamdefectDet.getUpsdKeyid());
		
		try{
				
			    CommonMessage.debugMsg(" Inside Dao Impl:: try block :: Update  ");
		
			    sqls.add(GenTlUpstreamdefectMstSql.getUpdateSql(genTlUpstreamdefectMstSql.getUpsmDbFields(), newGenTlUpstreamdefectMst.getSaveArray())); // add insert sql for master table
			    newGenTlUpstreamdefectDet.setUpsdUpsmKeyid(newGenTlUpstreamdefectMst.getUpsmKeyid());
				
				if(newGenTlUpstreamdefectDet.getUpsdKeyid()==null){
					
					newGenTlUpstreamdefectDet.setUpsdKeyid(dbActionTemplate.getSequenceNumber("TBL_GEN_TL_UPSTREAMDEFECT_DET", 10, "UPSD", "", "Y")); // set the sequnce number
					fillValuesNewgenTlUpstreamdtl(newGenTlUpstreamdefectDet, existGenTlUpstreamdefectDet,upstreamDefect);
					sqls.add(GenTlUpstreamdefectDetSql.getInsertSql(genTlUpstreamdefectDetSql.getUpsdDbFields(), newGenTlUpstreamdefectDet.getSaveArray())); // add insert sql for master table
					
				   
				   }
				else{
					
					fillValuesNewgenTlUpstreamdtl(newGenTlUpstreamdefectDet, existGenTlUpstreamdefectDet,upstreamDefect);
					sqls.add(GenTlUpstreamdefectDetSql.getUpdateSql(genTlUpstreamdefectDetSql.getUpsdDbFields(), newGenTlUpstreamdefectDet.getSaveArray())); // add insert sql for master table
				}

			
			    
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return newGenTlUpstreamdefectMst;

	
	}
	
	@Override
	public GenTlUpstreamdefectMst deleteNewUpstreamDefect(
			GenTlUpstreamdefectMst newGenTlUpstreamdefectMst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		
		GenTlUpstreamdefectMstSql genTlUpstreamdefectMstSql =new GenTlUpstreamdefectMstSql();
		GenTlUpstreamdefectDetSql genTlUpstreamdefectDetSql =new GenTlUpstreamdefectDetSql();
		GenTlUpstreamdefectDet newGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		
				
		CommonMessage.debugMsg("newGenTlUpstreamdefect.getUpsdKeyid()  "+newGenTlUpstreamdefectMst.getUpsmKeyid());
		
		sqls.add("Delete from " +genTlUpstreamdefectDetSql.TBL_GEN_TL_UPSTREAMDEFECT_DET +" where  UPSD_UPSM_KEYID ='"+ newGenTlUpstreamdefectMst.getUpsmKeyid()+"'");
		
		sqls.add("Delete from " +genTlUpstreamdefectMstSql.TBL_GEN_TL_UPSTREAMDEFECT_MST+" where UPSM_KEYID='"+newGenTlUpstreamdefectMst.getUpsmKeyid()+"'");
		
			
		dbActionTemplate.executeStatements(sqls);
	
		return newGenTlUpstreamdefectMst;
	}

	
	@Override
	public GenTlUpstreamdefectDet deleteNewUpstreamDefectDetails(
			GenTlUpstreamdefectDet newGenTlUpstreamdefectDet) throws Exception {
		// TODO Auto-generated method stub
        
		List<String> sqls = new ArrayList<String>();
		
		GenTlUpstreamdefectDetSql genTlUpstreamdefectDetSql =new GenTlUpstreamdefectDetSql();
		GenTlUpstreamdefectDet existGenTlUpstreamdefectDet=new GenTlUpstreamdefectDet();
		sqls.add("Delete from " +genTlUpstreamdefectDetSql.TBL_GEN_TL_UPSTREAMDEFECT_DET +" where  UPSD_KEYID ='"+ newGenTlUpstreamdefectDet.getUpsdKeyid()+"'");
		dbActionTemplate.executeStatements(sqls);
		return existGenTlUpstreamdefectDet;
		
	}
	
	
	
	private void fillValuesNewgenTlUpstreamdtl(GenTlUpstreamdefectDet newGenTlUpstreamdefectDet,
			GenTlUpstreamdefectDet existGenTlUpstreamdefectDet,UpstreamDefect upstreamDefect)throws Exception {
		// TODO Auto-generated method stub
		
		//AdmTlUsermst user = UIUtils.getLoginUser(request);
		
		newGenTlUpstreamdefectDet.setUpsdActive("Y");

		String dateTime = CommonFunctions.dateTimeNow();

		newGenTlUpstreamdefectDet.setUpsdCreatedon(dateTime);

		newGenTlUpstreamdefectDet.setUpsdModifiedon(dateTime);
		if (newGenTlUpstreamdefectDet.getUpsdUpsmKeyid() == null)
			newGenTlUpstreamdefectDet.setUpsdUpsmKeyid("{}");

		if (newGenTlUpstreamdefectDet.getUpsdInformto() == null)
			newGenTlUpstreamdefectDet.setUpsdInformto("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdRawmaterial() == null)
			newGenTlUpstreamdefectDet.setUpsdRawmaterial("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdDefect() == null)
			newGenTlUpstreamdefectDet.setUpsdDefect("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdCorrectionaction() == null)
			newGenTlUpstreamdefectDet.setUpsdCorrectionaction("{}");
		
		if (newGenTlUpstreamdefectDet.getUpsdPreventiveaction() == null)
			newGenTlUpstreamdefectDet.setUpsdPreventiveaction("{}");
		
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield1() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield1("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield2() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield2("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield3() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield3("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield4() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield4("-");
		
		if (newGenTlUpstreamdefectDet.getUpsdTempfield5() == null)
			newGenTlUpstreamdefectDet.setUpsdTempfield5("-");


		if (newGenTlUpstreamdefectDet.getUpsdCreatedby() == null)
			newGenTlUpstreamdefectDet.setUpsdCreatedby("{}");
		
	}

	@Override
	public GenTlUpstreamdefect deleteUpstreamDefect(GenTlUpstreamdefect newGenTlUpstreamdefect) throws Exception {
		// TODO Auto-generated method stub
			
		    List<String> sqls = new ArrayList<String>();
			GenTlUpstreamdefectSql genTlUpstreamdefectSql = new GenTlUpstreamdefectSql();
		
			CommonMessage.debugMsg("sql.......");
			sqls.add(GenTlUpstreamdefectSql.getDeleteSql(genTlUpstreamdefectSql.getUpsdDbFields(), newGenTlUpstreamdefect.getSaveArray()));
			CommonMessage.debugMsg("sql......."+sqls); 
			dbActionTemplate.executeStatement(GenTlUpstreamdefectSql.getDeleteSql(genTlUpstreamdefectSql.getUpsdDbFields(), newGenTlUpstreamdefect.getSaveArray()));
		
		    return newGenTlUpstreamdefect;
		    
	}
	
	
	@Override
	public GenTlUpstreamdefect getFillControlData(String keyid)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside DaoImpl Files :: "+keyid);
		GenTlUpstreamdefect newGenTlUpstreamdefect = new GenTlUpstreamdefect();
		String sql = GenTlUpstreamdefectSql.getupstreamdata();
		Object args[] = new Object[] {keyid};
		newGenTlUpstreamdefect.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return newGenTlUpstreamdefect;
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		
		String sql = GenTlUpstreamdefectSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
		
	}

	@Override
	public GenTlUpstreamdefectMst getFillControlDatas(String FnlnId, String date,String Keyid)
			throws Exception {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg(" "+);
		GenTlUpstreamdefectMst newGenTlUpstreamdefectMst=new GenTlUpstreamdefectMst();
		String sql = GenTlUpstreamdefectSql.selectDataMst(Keyid);
		CommonMessage.debugMsg("sql  "+sql);
		Object args[] = new Object[] {Keyid};
		newGenTlUpstreamdefectMst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return newGenTlUpstreamdefectMst;
	}

	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	    StringBuffer sql = new StringBuffer();
	    
	    sql.append("SELECT FNLN_ELEMENTID, FNLN_KEYID, ROLE_LEVEL, ROLE_NAME, ROLE_KEYID ");
	    sql.append("FROM GEN_TL_FUNCTIONALLOCN, GEN_TL_FNLNROLETEAM, ADM_TL_ROLEMST ");
	    sql.append("WHERE FNLN_KEYID = FRT_FNLN_KEYID ");
	    sql.append("AND FRT_ROLE_KEYID = ROLE_KEYID ");
	    
	    if (UIUtils.isValidKeyId(loginflid)) {
	        sql.append("AND FRT_FNLN_KEYID = '" + loginflid + "' ");
	    }
	    
	    sql.append("AND FRT_EMPM_KEYID = '" + empId + "' ");
	    sql.append("AND ROLE_LEVEL = '" + loginlevel + "'");
	    
	    Object[] args = new Object[0];
	    
	    CommonMessage.debugMsg("=== UPSTREAM DEFECT DAO SQL ===");
	    CommonMessage.debugMsg(sql.toString());
	    CommonMessage.debugMsg("=== END SQL ===");
	    
	    List<String[]> userDatas = this.dbActionTemplate.getDataList(sql.toString(), args);
	    
	    return userDatas;
	}





}

