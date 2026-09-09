package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.VisualControlCheckListBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.VisualControlChartDAO;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlVisualcntchecklistdtlSql;
import com.akranta.tpm.dao.sql.GenTlVisualcontrolchecklistSql;

import com.akranta.tpm.dao.sql.SopTlVisualchecklistdtlSql;
import com.akranta.tpm.dao.sql.SopTlVisualchecklistmstSql;

import com.akranta.tpm.dao.sql.JhaTlVisualsopmstSql;
//import com.akranta.tpm.dao.sql.QtmTlQpointmstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;

import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlVisualcntchecklistdtl;
import com.akranta.tpm.model.GenTlVisualcontrolchecklist;
//import com.akranta.tpm.model.PlmTlPmtasklistdtl;
import com.akranta.tpm.model.SopTlVisualchecklistdtl;
import com.akranta.tpm.model.SopTlVisualchecklistmst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.JHAuditSheetCreationItcServiApi;
import com.akranta.tpm.service.api.VisualControlChecklistServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class VisualControlChartDAOImpl implements VisualControlChartDAO
{
	private DBActionTemplate dbActionTemplate; 
	private GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql;
	private GenTlVisualcntchecklistdtlSql genTlVisualcntchecklistdtlSql;
	

private  VisualControlChecklistServiceApi visualControlChecklistServiceApi;
 FunctionCallApi fnCallApi;
	
	public VisualControlChartDAOImpl(DBActionTemplate dbActionTemplate)
	{	genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql();
		genTlVisualcntchecklistdtlSql = new GenTlVisualcntchecklistdtlSql();
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void VisualControlChecklistDaoImplJwt(String jwtToken) {
		
		try{
			visualControlChecklistServiceApi = new VisualControlChecklistServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<String[]> getVisualControlGrid(CommonFilter commonFilter,String keyid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("insidet dao impl::::"+keyid);
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			if(UIUtils.isValidKeyId(keyid))
				condParms +=";MASTERKEYID="+keyid;
			
			String title = commonFilter.gettitle();
			if(UIUtils.isValidKeyId(title))
				condParms +=";TITLE="+title;
			CommonMessage.debugMsg("condParams=="+condParms);
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			//List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_VISUALCONTROLCHECKLIST", paramValues);
			List<String[]> dataList=fnCallApi.callFunction("GEN_FN_VISUALCONTROLCHECKLIST_SB", paramValues,2,true);
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
	public List<String[]> getVisualControl(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
						
			CommonMessage.debugMsg("ParamValues:"+paramValues);	
			//List<String[]> dataList =dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_VISUALCONTROLLIST", paramValues);
			List<String[]> dataList=fnCallApi.callFunction("GEN_FN_VISUALCONTROLLIST_sb", paramValues,2,true);
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
	public GenTlVisualcontrolchecklist create(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist,VisualControlCheckListBean visualControlCheckListBean)throws BusinessApplicationExceptions, Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql();
		 
		CommonMessage.debugMsg("newGenTlVisualcontrolchecklist.getVcclKeyid();;"+newGenTlVisualcontrolchecklist.getVcclKeyid());
		
			if(UIUtils.isValidKeyId(newGenTlVisualcontrolchecklist.getVcclKeyid()))
				sqls.add(GenTlVisualcontrolchecklistSql.getUpdateSql(genTlVisualcontrolchecklistSql.getVcclDbFields(), newGenTlVisualcontrolchecklist.getSaveArray()));
			else{
				CommonMessage.debugMsg("Inside DAO else....");
				String elementId = newGenTlVisualcontrolchecklist.getElementid();
			 	String location = null;
			 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlVisualcontrolchecklistSql.TBL_GEN_TL_VISUALCONTROLCHECKLIST);
				
				newGenTlVisualcontrolchecklist.setVcclKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,10,"VC","","Y")); 
				//newGenTlVisualcontrolchecklist.setVcclKeyid(dbActionTemplate.getSequenceNumber(GenTlVisualcontrolchecklistSql.TBL_GEN_TL_VISUALCONTROLCHECKLIST,10,"VCL","","Y")); 
				sqls.add(GenTlVisualcontrolchecklistSql.getInsertSql(genTlVisualcontrolchecklistSql.getVcclDbFields(), newGenTlVisualcontrolchecklist.getSaveArray()));
			}
			CommonMessage.debugMsg("Inside DAO for details..eeeeeeeeeeeeee..");
			
			if(newGenTlVisualcontrolchecklist.getVisualControlDetail()!=null){
				CommonMessage.debugMsg("Inside DAO for details....");
				GenTlVisualcntchecklistdtlSql genTlVisualcntchecklistdtlSql=new GenTlVisualcntchecklistdtlSql();
				Boolean setDtlKeyid=false;
				for(int i=0;i<newGenTlVisualcontrolchecklist.getVisualControlDetail().size();i++){
					GenTlVisualcntchecklistdtl genTlVisualcntchecklistdtl=newGenTlVisualcontrolchecklist.getVisualControlDetail().get(i);
					fillValues(genTlVisualcntchecklistdtl,newGenTlVisualcontrolchecklist);
					
				if(genTlVisualcntchecklistdtl.getVcdtKeyid() !=null && genTlVisualcntchecklistdtl.getVcdtVccdKeyid() !=null 
						&& genTlVisualcntchecklistdtl.getVcdtCriteriaval()!=null){
					//visualControlCheckListBean.setDtlKeyid(genTlVisualcntchecklistdtl.getVcdtKeyid());
					sqls.add(GenTlVisualcntchecklistdtlSql.getUpdateSql(genTlVisualcntchecklistdtlSql.getVcdtDbFields(), genTlVisualcntchecklistdtl.getSaveArray()));
										
				}else if(genTlVisualcntchecklistdtl.getVcdtKeyid() !=null && genTlVisualcntchecklistdtl.getVcdtVccdKeyid() ==null 
						&& genTlVisualcntchecklistdtl.getVcdtCriteriaval()!=null) {
										sqls.add(GenTlVisualcntchecklistdtlSql.getDeleteSql(genTlVisualcntchecklistdtlSql.getVcdtDbFields(), genTlVisualcntchecklistdtl.getSaveArray()));
				}
				else {
					 if(genTlVisualcntchecklistdtl.getVcdtVccdKeyid() == null ||
						       genTlVisualcntchecklistdtl.getVcdtVccdKeyid().trim().isEmpty()) {						       
						        continue;
						    }
					String vcdtKeyid = dbActionTemplate.getSequenceNumber(GenTlVisualcntchecklistdtlSql.TBL_GEN_TL_VISUALCNTCHECKLISTDTL,10,"VCDT","","Y");
					//visualControlCheckListBean.setDtlKeyid(vcdtKeyid);
					genTlVisualcntchecklistdtl.setVcdtKeyid(vcdtKeyid); // set the sequnce number 
					sqls.add(GenTlVisualcntchecklistdtlSql.getInsertSql(genTlVisualcntchecklistdtlSql.getVcdtDbFields(), genTlVisualcntchecklistdtl.getSaveArray()));
				}
				CommonMessage.debugMsg("visualControlCheckListBean.gettDtlKeyid=="+visualControlCheckListBean.getDtlKeyid());
				
				if ( !visualControlCheckListBean.getDtlKeyid().equals("null") &&
							setDtlKeyid == false && Integer.parseInt( visualControlCheckListBean.getDtlKeyid()) == (i+1) ) {
					visualControlCheckListBean.setDtlKeyid(genTlVisualcntchecklistdtl.getVcdtKeyid());
					setDtlKeyid=true;
				}				
				CommonMessage.debugMsg("visualControlCheckListBean.gettDtlKeyid=="+visualControlCheckListBean.getDtlKeyid());
			}
		}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		
	CommonMessage.debugMsg("Return DBAction:"+newGenTlVisualcontrolchecklist);
	
	return newGenTlVisualcontrolchecklist;
	}
	private void fillValues(GenTlVisualcntchecklistdtl genTlVisualcntchecklistdtl,GenTlVisualcontrolchecklist genTlVisualcontrolchecklist) {
		CommonMessage.debugMsg("Inside DAO..fillNearmissreportdtlValues..");
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtVcclKeyid()))
			genTlVisualcntchecklistdtl.setVcdtVcclKeyid(genTlVisualcontrolchecklist.getVcclKeyid());
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtActive()))
			genTlVisualcntchecklistdtl.setVcdtActive("Y");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtCreatedon()))
			genTlVisualcntchecklistdtl.setVcdtCreatedon(CommonFunctions.pg_dateTimeNow());
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtModifiedon()))
			genTlVisualcntchecklistdtl.setVcdtModifiedon(CommonFunctions.pg_dateTimeNow());
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield1()))
			genTlVisualcntchecklistdtl.setVcdtTempfield1("-");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield2()))
			genTlVisualcntchecklistdtl.setVcdtTempfield2("-");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield3()))
			genTlVisualcntchecklistdtl.setVcdtTempfield3("-");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield4()))
			genTlVisualcntchecklistdtl.setVcdtTempfield4("-");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield5()))
			genTlVisualcntchecklistdtl.setVcdtTempfield5("-");
		
		if(!UIUtils.isValidKeyId(genTlVisualcntchecklistdtl.getVcdtTempfield6()))
			genTlVisualcntchecklistdtl.setVcdtTempfield6("-");
	}
	@Override
	public GenTlVisualcontrolchecklist getSelect(String keyId) {
		
		try{
			GenTlVisualcontrolchecklistSql genTlVisualcontrolchecklistSql = new GenTlVisualcontrolchecklistSql();
			GenTlVisualcontrolchecklist genTlVisualcontrolchecklist = new GenTlVisualcontrolchecklist();
			String sql = genTlVisualcontrolchecklistSql.getselectsql();
			Object [] args =  new Object [] { keyId };
			CommonMessage.debugMsg("sql:::::"+sql);
			CommonMessage.debugMsg("keyid:::::"+keyId);
			genTlVisualcontrolchecklist.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			
			CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyId);
			
			return  genTlVisualcontrolchecklist;
		}catch(Exception e){
			
		}
		return null;
	}
	@Override
	public GenTlVisualcontrolchecklist delete(GenTlVisualcontrolchecklist newGenTlVisualcontrolchecklist) throws Exception {
		
		
	List<String> sqls = new ArrayList<String>();
	GenTlVisualcontrolchecklistSql Sql = new GenTlVisualcontrolchecklistSql();
	
	String vcclKeyid=newGenTlVisualcontrolchecklist.getVcclKeyid();
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" SELECT Count(*) FROM abn_tl_abnormality where abnm_refdocid IN (" );
		sqlBuf.append(" SELECT VCDT_KEYID FROM GEN_TL_VISUALCNTCHECKLISTDTL WHERE VCDT_VCCL_KEYID='"+vcclKeyid+"') " );
		
		CommonMessage.debugMsg("sqlBuf.toString()"+sqlBuf.toString());
		String abnExists = dbActionTemplate.getSingleValue(sqlBuf.toString());
		//CommonMessage.debugMsg("checkLossExist--------->> "+checkLossExist);
		if(UIUtils.isValidKeyId(abnExists))
		{
			if(Integer.parseInt(abnExists)>0)
				throw new BusinessApplicationExceptions("ABNEXIST,");
				
		}

		sqls.add( "DELETE FROM GEN_TL_VISUALCNTCHECKLISTDTL WHERE  VCDT_VCCL_KEYID ='"+vcclKeyid+"'");
		sqls.add(GenTlVisualcontrolchecklistSql .getDeleteSql(Sql.getVcclDbFields(), newGenTlVisualcontrolchecklist.getSaveArray()));
        
		dbActionTemplate.executeStatements(sqls);
		
		return newGenTlVisualcontrolchecklist;
	}
	@Override
	public Workbook getVisualExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getVisualResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getVisualResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_VISUALCONTROLLIST", paramValues);
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
	public SopTlVisualchecklistmst create(SopTlVisualchecklistmst newSopTlVisualchecklistmst)throws Exception, ValidationExceptions {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		SopTlVisualchecklistmstSql sopTlVisualchecklistmstSql = new SopTlVisualchecklistmstSql();
		
	CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVccmKeyid();;"+newSopTlVisualchecklistmst.getVccmKeyid());
		
	if(UIUtils.isValidKeyId(newSopTlVisualchecklistmst.getVccmKeyid()))
				//CommonMessage.debugMsg(newSopTlVisualchecklistmst.getVisualControlmstdet().get(0).getVccdCheckpoints());
				sqls.add(SopTlVisualchecklistmstSql.getUpdateSql(sopTlVisualchecklistmstSql.getVccmDbFields(), newSopTlVisualchecklistmst.getSaveArray()));
			
			else{
				CommonMessage.debugMsg("Inside DAO else....");
				newSopTlVisualchecklistmst.setVccmKeyid(dbActionTemplate.getSequenceNumber(SopTlVisualchecklistmstSql.TBL_SOP_TL_VISUALCHECKLISTMST, 10, "VCM", "", "Y")); 
				sqls.add(SopTlVisualchecklistmstSql.getInsertSql(sopTlVisualchecklistmstSql.getVccmDbFields(), newSopTlVisualchecklistmst.getSaveArray()));
			}
			CommonMessage.debugMsg("Inside DAO for details..eeeeeeeeeeeeee..");
			
			
			
			if(newSopTlVisualchecklistmst.getVisualControlmstdet()!=null){
				CommonMessage.debugMsg("Inside DAO for details....");
				
				
				SopTlVisualchecklistdtlSql sopTlVisualchecklistdtlSql = new SopTlVisualchecklistdtlSql();
				
				for(int i=0;i<newSopTlVisualchecklistmst.getVisualControlmstdet().size();i++){
					
					SopTlVisualchecklistdtl newSopTlVisualchecklistdtl=newSopTlVisualchecklistmst.getVisualControlmstdet().get(i);
					newSopTlVisualchecklistdtl.setVccdVccmKeyid(newSopTlVisualchecklistmst.getVccmKeyid());			
					CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVccdKeyid();;"+newSopTlVisualchecklistdtl.getVccdKeyid());
					CommonMessage.debugMsg("newSopTlVisualchecklistmst.getVccdvccmKeyid();dao impl;"+newSopTlVisualchecklistdtl.getVccdVccmKeyid());
						if(newSopTlVisualchecklistdtl.getVccdKeyid() !=null && newSopTlVisualchecklistdtl.getVccdVccmKeyid() !=null && newSopTlVisualchecklistdtl.getVccdCheckpoints()!=null){
							
						sqls.add(SopTlVisualchecklistdtlSql.getUpdateSql(sopTlVisualchecklistdtlSql.getVccdDbFields(), newSopTlVisualchecklistdtl.getSaveArray()));
							CommonMessage.debugMsg(newSopTlVisualchecklistdtl.getVccdCheckpoints());					
						} 
						
						else if(newSopTlVisualchecklistdtl.getVccdKeyid() !=null && newSopTlVisualchecklistdtl.getVccdVccmKeyid() !=null && newSopTlVisualchecklistdtl.getVccdCheckpoints()!=null) {
												
						sqls.add(SopTlVisualchecklistdtlSql.getDeleteSql(sopTlVisualchecklistdtlSql.getVccdDbFields(), newSopTlVisualchecklistdtl.getSaveArray()));
											}
						else
						{
							newSopTlVisualchecklistdtl.setVccdVccmKeyid(newSopTlVisualchecklistmst.getVccmKeyid());
							newSopTlVisualchecklistdtl.setVccdKeyid(dbActionTemplate.getSequenceNumber(SopTlVisualchecklistdtlSql.TBL_SOP_TL_VISUALCHECKLISTDTL,10,"VCD","","Y")); // set the sequnce number 
						    sqls.add(SopTlVisualchecklistdtlSql.getInsertSql(sopTlVisualchecklistdtlSql.getVccdDbFields(), newSopTlVisualchecklistdtl.getSaveArray()));
	
					}
					
				}
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls		
		return newSopTlVisualchecklistmst;
	}
	
	@Override
	public SopTlVisualchecklistmst update(SopTlVisualchecklistmst newSopTlVisualchecklistmst)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public SopTlVisualchecklistmst getSelectvis(String keyId) throws Exception {
		

		try{
			SopTlVisualchecklistmstSql sopTlVisualchecklistmstSql = new SopTlVisualchecklistmstSql();
			SopTlVisualchecklistmst newSopTlVisualchecklistmst = new SopTlVisualchecklistmst();
			String sql = sopTlVisualchecklistmstSql.getselectsql();
			Object [] args =  new Object [] {keyId };
			CommonMessage.debugMsg("sql:::::"+sql);
			CommonMessage.debugMsg("keyid:::::"+keyId);
			newSopTlVisualchecklistmst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	    	CommonMessage.debugMsg(sql);
			CommonMessage.debugMsg(keyId);
			return newSopTlVisualchecklistmst;
          }catch(Exception e){
			
		}
		return null;
	}
		

	@Override
	public List<String[]> getSelectvisual(CommonFilter commonFilter) throws Exception {
		
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getVisualControlReport = null;
		
		try {
			String KEYID = commonFilter.getKey() ;
			
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg(" KEYID "+KEYID);
			condParams=condParams+";KEYID="+KEYID+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			getVisualControlReport =dbActionTemplate.processFunctionCalls("SOP_PC_SOP.SOP_FN_VISUALCHECKLISTDTL",paramValues);
		}catch( Exception e){
		}
		return getVisualControlReport;
	}
		
		
		//StringBuffer sql= new StringBuffer();
		

		//sql.append("select 'Keyid','select','Checkpoint','SortOrder'  from dual");
		//if(UIUtils.isValidKeyId(keyId)){
		//sql.append(" union all");
		//sql.append(" select VCCD_KEYID,'',VCCD_CHECKPOINTS,TO_CHAR(VCCD_SORTORDER) from SOP_TL_VISUALCHECKLISTDTL where VCCD_VCCM_KEYID = '"+keyId+"'");
		//}
		//CommonMessage.debugMsg("sql....."+sql);
		///List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		//CommonMessage.debugMsg("Grid value::::::::"+ gridData.size());
		//return gridData;
	//}
	@Override
	public SopTlVisualchecklistmst delete(SopTlVisualchecklistmst newSopTlVisualchecklistmst)
			throws Exception {

	     
		List<String> sqls = new ArrayList<String>();
		SopTlVisualchecklistmstSql sopTlVisualchecklistmstSql = new SopTlVisualchecklistmstSql();
		
		try {
			//sqls.add( "DELETE FROM SOP_TL_VISUALCHECKLISTDTL WHERE  VCCD_VCCM_KEYID ='" + newSopTlVisualchecklistmst.getVccmKeyid()+"'");
			sqls.add(SopTlVisualchecklistmstSql.getDeleteSql(sopTlVisualchecklistmstSql.getVccmDbFields(), newSopTlVisualchecklistmst.getSaveArray()));
	        
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){

		}
		return newSopTlVisualchecklistmst;
		}
	@Override
	public void Deletevis(String keyid) throws Exception {
		try
		{
		String sql = SopTlVisualchecklistdtlSql.Deletevis(keyid);
		
		this.dbActionTemplate.executeStatement(sql);
		}
		
		catch( Exception e){
			String fkCons = e.getMessage();
			CommonMessage.debugMsg("fkCons"+fkCons);
			if (fkCons.contains("FK_VCDT_VCCD_KEYID")) {
				//String sql = SopTlVisualchecklistdtlSql.makeInactive(keyid);
				//this.dbActionTemplate.executeStatement(sql);
				throw new Exception(fkCons);
				
			}
			else
				throw new Exception(e.getMessage());
		}
	}
	@Override
	public Workbook getvisualWorkExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		 ResultSet rs = null;
			// TODO Auto-generated method stub
			 try{
					
				 rs =   getvisualWorkExcel(commonFilter);
				 
				 ExcelUtils excelUtils = new ExcelUtils(colmodel);
					CommonMessage.debugMsg("excelUtils:::"+excelUtils.getNotWriteColumns());
					return excelUtils.writeToExcel(rs,format, 0,0,0 );
					
				   }finally{
					   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
				   }
		}
	private ResultSet getvisualWorkExcel(CommonFilter commonFilter) throws Exception {
		StringBuffer sql = new StringBuffer();
		// sql.append ("SELECT G.VCCM_KEYID, G.VCCM_SERIALNO, G.VCCM_CRETERIA, G.VCCM_TEMPFIELD1, G.VCCM_TEMPFIELD2,G.VCCM_TEMPFIELD3, G.VCCM_TEMPFIELD4, G.VCCM_TEMPFIELD5, G.VCCM_ACTIVE, G.VCCM_CREATEDBY,G.VCCM_CREATEDON, G.VCCM_MODIFIEDON FROM SOP_TL_VISUALCHECKLISTMST G where VCCM_KEYID =?");
		 sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) );
	    CommonMessage.debugMsg("sql..."+sql);
       
		return dbActionTemplate.getData(sql.toString());
	
	}


	@Override
	public List<String[]> getVisualControlReport(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getVisualControlReport = null;
		
		try {
			String Visualkey = commonFilter.getKey() ;
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			CommonMessage.debugMsg(" Visualkey "+Visualkey);
			condParams=condParams+";Visualkey="+Visualkey+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			//getVisualControlReport =dbActionTemplate.processFunctionCalls("GEN_FN_VISUALCHECKLISTREPORT",paramValues);
			getVisualControlReport =fnCallApi.callFunctionWithHeaders("GEN_FN_VISUALCHECKLISTREPORT_SB", paramValues,2,true);
			String totalCnt = paramValues.get(0); 
			CommonMessage.debugMsg("totalCnt...."+totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){ 
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			}
		}catch( Exception e){
		}
		return getVisualControlReport;
	}
	@Override
	public Workbook getVisuaReportExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
				return excelUtils.writeToExcel(rs,format, 1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getResultSet(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_VISUALCHECKLISTREPORT", paramValues);
	}
	@Override
	public List<ComboBox> fillComboValues(ComboFilter recordedby) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<String[]> getVisualControlcheckpoints(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		List<String[]> getVisualControlcheckpoints = null;
		
		try {
			//String Visualkey = commonFilter.getKey() ;
			String condParams = FilterCondSql.getETRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			//CommonMessage.debugMsg(" Visualkey "+Visualkey);
			//condParams=condParams+";Visualkey="+Visualkey+";";
			paramValues.add(condParams);
			paramValues.add(commonParams);
			getVisualControlcheckpoints =dbActionTemplate.processFunctionCalls("SOP_PC_SOP.SOP_FN_VISUALCHECKLISTMST",paramValues);
		}catch( Exception e){
		}
		return getVisualControlcheckpoints;
	}
	@Override
	public Workbook getVisualpointsExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{
				
			 rs =   getResultpointsSet(commonFilter);
			 CommonMessage.debugMsg("dao impl excel1");
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			 return excelUtils.writeToExcel(rs,format, 1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getResultpointsSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub 
		CommonMessage.debugMsg("dao impl excel2");
		List<String> paramValues = getFilterParamValues(commonFilter);
		return dbActionTemplate.dbFunctionCall("SOP_PC_SOP.SOP_FN_VISUALCHECKLISTMST", paramValues);
	}
	@Override
	public List<String[]> selecttitle(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		//String 
		return null;
	}

	@Override
	public Workbook getVisualRptExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		// TODO Auto-generated method stub
		 try{

			 
			 rs =   getVisualReportResultSet(commonFilter);
			 
			 ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			 List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				XLConditionalFormats condFormatYes = new XLConditionalFormats();			
		        condFormatYes.setFontColor(new RGB(0,0,0)); //red font
		        condFormatYes.setFontName(XLConditionalFormats.FONT_WINGDINGS);
		        condFormatYes.setFontHeightPoint((short)14);
		        condFormatYes.setFontBoldWeight((short)20);
		        condFormatYes.setFromCol(4);
		        condFormatYes.setToCol(-1);
		        condFormatYes.setOperator(ComparisonOperator.EQUAL);
				condFormatYes.setCondValue("Y"); //Tick			
				condFormatYes.setFontColor(new RGB(135,206,235));
				condFormatYes.setSymbolStr(XLConditionalFormats.SYMBOL_TICK+"");
				condFormats.add(condFormatYes);
				excelUtils.setCondFormats(condFormats);

				XLConditionalFormats condFormatNo = new XLConditionalFormats();			
		        condFormatNo.setFontColor(new RGB(0,0,0)); //red font
		        condFormatNo.setFontName(XLConditionalFormats.FONT_WINGDINGS );
		        condFormatNo.setFontHeightPoint((short)14);
		        condFormatNo.setFontBoldWeight((short)20);
		        condFormatNo.setFromCol(5);
		        condFormatNo.setToCol(-1);
		        condFormatNo.setOperator(ComparisonOperator.EQUAL);
				condFormatNo.setCondValue("N"); //Tick			
				condFormatNo.setFontColor(new RGB(255,0,0));
				condFormatNo.setSymbolStr(XLConditionalFormats.SYMBOL_CROSS+"");
				condFormats.add(condFormatNo);
				excelUtils.setCondFormats(condFormats);
				
			 return excelUtils.writeToExcel(rs,format, 1,0,0 );
				
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
	}
	private ResultSet getVisualReportResultSet(CommonFilter commonFilter) throws Exception {
		
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";

		String keyid = commonFilter.getKey();
		CommonMessage.debugMsg("insidet dao impl::::"+keyid);
		
		CommonMessage.debugMsg("Keyid====="+keyid);
		
		if(UIUtils.isValidKeyId(keyid))
			condParms +=";MASTERKEYID="+keyid;
		CommonMessage.debugMsg("condParms====="+condParms);
		String title = commonFilter.gettitle();
		if(UIUtils.isValidKeyId(title))
			condParms +=";TITLE="+title;
		CommonMessage.debugMsg("condParams=="+condParms);
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return dbActionTemplate.NewdbFunctionCall2("GEN_FN_VISUALCONTROLCHECKLIST", paramValues);
	}
	

	//graph
	
	@Override
	public List<String[]> getVisualWPScoreGraph(CommonFilter commonFilter) throws Exception {
		try
		{
			
			List<String > paramValues = new ArrayList<String>();
			CommonMessage.debugMsg("Inside  visual Work place Month DAO Impl");
			String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			String type=commonFilter.getAbnViewType();
			
			CommonMessage.debugMsg(" Checking for type "+type);
			
			if(UIUtils.isValidKeyId(type))
				condParms +="TYPE="+type+";";
				
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("Inside Visual Month DAO Impl " + paramValues);
			
			//List<String[]> impVsCompList = dbActionTemplate.processFunctionCallsWithColHeaders("SOP_FN_VISUALWPSCOREGRAPH", paramValues);
			
			List<String[]> impVsCompList = fnCallApi.callFunction("SOP_FN_VISUALWPSCOREGRAPH_SB", paramValues,3,false);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			
			return impVsCompList;			
		
	}
	
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook visualWPGraphExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getIncedentVsCompleteResultSet(commonFilter);
			CommonMessage.debugMsg("rss=="+rs);
			
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			CommonMessage.debugMsg("colModel=="+colModel);
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}

	private ResultSet getIncedentVsCompleteResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = getIncedentFilterParamValues(commonFilter);
		
		return dbActionTemplate.NewdbFunctionCall2("SOP_FN_VISUALWPSCOREGRAPH", paramValues);
	}
	
	private List<String> getIncedentFilterParamValues(CommonFilter commonFilter){
		List<String> paramValues = new ArrayList<String>();
		
		CommonMessage.debugMsg(" commonFilter" +commonFilter);
		String condParms = FilterCondSql.getSafetyRelatedStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		CommonMessage.debugMsg(" condPArams" + condParms);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		
		return paramValues;
	}


}

	
	


	
		
		
	
