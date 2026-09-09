package com.akranta.tpm.dao.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BDFormBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.ShiftBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BdmTlMstDao;
import com.akranta.tpm.dao.sql.BdmTlDtlSql;
import com.akranta.tpm.dao.sql.BdmTlMstSql;
import com.akranta.tpm.dao.sql.BdmTlPhencauseSql;
import com.akranta.tpm.dao.sql.BdmTlShiftwisesplitSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlEmployeedtlSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.GenTlSparesmstSql;
import com.akranta.tpm.dao.sql.PlmTlUnplannedmaintdtlSql;
import com.akranta.tpm.dao.sql.PlmTlUnplannedmaintmstSql;
import com.akranta.tpm.dao.sql.SapExternalRepairSql;
import com.akranta.tpm.dao.sql.SapExternalServiceDtlSql;
import com.akranta.tpm.dao.sql.SapExternalServiceMstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.dao.sql.WomTlCommunicationlogSql;
import com.akranta.tpm.dao.sql.WomTlWomstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlMst;
import com.akranta.tpm.model.BdmTlPhncauselink;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.model.PlmTlUnplannedmaintdtl;
import com.akranta.tpm.model.PlmTlUnplannedmaintmst;
import com.akranta.tpm.model.SapExternalRepair;
import com.akranta.tpm.model.SapExternalServiceDtl;
import com.akranta.tpm.model.SapExternalServiceMst;
import com.akranta.tpm.model.SapTlMaintenanceOrdermst;
import com.akranta.tpm.model.SapTlMaintenanceorder;
import com.akranta.tpm.model.WomTlCommunicationlog;
import com.akranta.tpm.model.WomTlWomst;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class BdmTlMstDaoImpl implements BdmTlMstDao {


	//private static final Object[][] String = null;
	private DBActionTemplate dbActionTemplate; 
	private BdmTlMstSql bdmTlMstSql=null ;
	private BdmTlDtlSql bdmTlDtlSql=null ;
	private BdmTlShiftwisesplitSql bdmTlShiftwisesplitSql =null;
	private WomTlCommunicationlogSql womTlCommunicationlogSql =null;
    private SapExternalServiceMstSql sapExternalServiceMstsql ;
    private SapExternalServiceDtlSql sapExternalServiceDtlsql ;
    private SapExternalRepairSql sapExternalRepairSql;
    FunctionCallApi fnCallApi;
	public BdmTlMstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
	 	
		this.dbActionTemplate = dbActionTemplate;
	 
		bdmTlMstSql = new BdmTlMstSql();
	 
		bdmTlDtlSql = new BdmTlDtlSql();
	 
		bdmTlShiftwisesplitSql = new BdmTlShiftwisesplitSql();
	 
		womTlCommunicationlogSql = new WomTlCommunicationlogSql();
 
		sapExternalServiceMstsql = new SapExternalServiceMstSql(); 
		sapExternalServiceDtlsql = new SapExternalServiceDtlSql();
		sapExternalRepairSql     = new SapExternalRepairSql();
 
	}
	//-- added by vignesh -- //
	public void BdmTlMstDaoImplJwt(String JwtToken) {
	    try {
	     //   oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<String[]> isMSRExist(WomTlWomst womTlWomst) throws Exception
	{
		String occuredDate = womTlWomst.getWomsWorkenddate();	
		if(occuredDate.indexOf(" ") > 0 && occuredDate.length() > 17)
			occuredDate = occuredDate.substring(0, occuredDate.indexOf(" ") + 6);
		List<String> paramVals = new ArrayList<String>();
		paramVals.add(womTlWomst.getWomsKeyid());		
		paramVals.add(occuredDate);		
		paramVals.add(womTlWomst.getWomsMachineid());
		List<String[]>  overlapFlag = dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_ISMSROCCURS", paramVals);
		return overlapFlag;
	}
	public BdmTlMst create(BdmTlMst bdmTlMst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			
			BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); 
			String YYReq = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "YYREQUIRED");
			String YYmand = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "ISYYMANDATRY");
			if(CommonFunctions.isValidKeyId(bdmDetail.getBdanErppoststatus()) && bdmDetail.getBdanErppoststatus().equals("C"))
			{
				if(CommonFunctions.isValidKeyId(YYmand) && YYmand.equals("Y"))
					bdmDetail.setBdanErppoststatus("W");					
			}
			CommonMessage.debugMsg(YYReq + " : "+YYmand);
		}
		bdmTlMst.setBdmsKeyid(dbActionTemplate.getSequenceNumber(BdmTlMstSql.TBL_BDM_TL_MST, 10, "BDM", "", "Y") ); // set the sequnce number
		if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno()))
		{
			WomTlWomst womTlWomst = new WomTlWomst();
			updateWorkOrder(bdmTlMst,womTlWomst,sqls);		
		}
		else
			insertWorkOrder(bdmTlMst,sqls);
		
		sqls.add(BdmTlMstSql.getInsertSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray())); // add insert sql for master table
		CommonMessage.debugMsg( "Size : " +bdmTlMst.getBdmDetail().size());
		if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
		{	
			CommonMessage.debugMsg("If Loop in MST Dao Impl");
			BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0); // get detail info from list in empployee object
			bdmDetail.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid()); 
			bdmDetail.setBdanKeyid(dbActionTemplate.getSequenceNumber(BdmTlDtlSql.TBL_BDM_TL_DTL, 10, "BDA", "", "Y"));
			sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmDetail.getSaveArray()));// add insert sql for detail table
		}
		insertShiftWise(bdmTlMst,sqls);
		CommonMessage.debugMsg( "bdmTlMst.getBdmsWno() : "+bdmTlMst.getBdmsWno());
		
			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		return bdmTlMst;
	}
	
	private List<String> insertShiftWise(BdmTlMst bdmTlMst,List<String> sqls) throws Exception {
		if(bdmTlMst.getBdmShiftwise()!= null && bdmTlMst.getBdmShiftwise().size()>0) // check for detail table data
		{
			sqls.add(BdmTlShiftwisesplitSql.getDeleteSql(bdmTlMst.getBdmsKeyid()));
	    	for(int i =0;i<bdmTlMst.getBdmShiftwise().size();i++)
			{	
	    		BdmTlShiftwisesplit bdmTlShiftwise = (BdmTlShiftwisesplit)bdmTlMst.getBdmShiftwise().get(i); // get detail info from list in empployee object
				bdmTlShiftwise.setBdssBdno(bdmTlMst.getBdmsKeyid());				
				bdmTlShiftwise.setBdssKeyid(dbActionTemplate.getSequenceNumber(BdmTlShiftwisesplitSql.TBL_BDM_TL_SHIFTWISESPLIT));
				sqls.add(BdmTlShiftwisesplitSql.getInsertSql(bdmTlShiftwisesplitSql.getBdssDbFields(), bdmTlShiftwise.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}

	public BdmTlMst update(BdmTlMst bdmTlMst,WomTlWomst womTlWomst)	throws Exception ,BusinessApplicationExceptions{  
		
		List<String> sqls = new ArrayList<String>();
		BdmTlMstSql bdmTlMstSql = new BdmTlMstSql();
		PlmTlUnplannedmaintmstSql plmTlUnplannedmaintmstSql = new PlmTlUnplannedmaintmstSql();
		PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();	
		PlmTlUnplannedmaintdtlSql plmTlUnplannedmaintdtlSql = new PlmTlUnplannedmaintdtlSql();
		PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
		//GenTlEmployeedtlSql genTlEmployeedtlSql = new GenTlEmployeedtlSql();
		
		
			CommonMessage.debugMsg("Inside the upDate"+bdmTlMst.getBdmsKeyid().substring(0,1));
			if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
				sqls.add(BdmTlMstSql.getUpdateSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			else
			{
				plmTlUnplannedmaintmst = (PlmTlUnplannedmaintmst) UIUtils.copyObject(bdmTlMst, plmTlUnplannedmaintmst);
				sqls.add(PlmTlUnplannedmaintmstSql.getUpdateSql(plmTlUnplannedmaintmstSql.getUpmmDbFields(), plmTlUnplannedmaintmst.getSaveArray())); // add insert sql for master table
			}
			List<BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			String bdanNo = null;
			if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{					
				for( BdmTlDtl bdmTlDtl : bdmTlDtls ){
					//bdmTlDtl.setBdanKeyid(bdmTlMst.getBdmsKeyid());
					if(bdmTlMst.getBdmsKeyid().substring(0,1).equals("B"))
					{
						bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
						
						if( ! dbActionTemplate.checkDuplicateValue(BdmTlDtlSql.TBL_BDM_TL_DTL,
										"BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid(), "") )
						{	
							sqls.add(BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}	
						else{
							sqls.add(BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
						}						
					}
					else
					{
						plmTlUnplannedmaintdtl = (PlmTlUnplannedmaintdtl) UIUtils.copyObject(bdmTlDtl, plmTlUnplannedmaintdtl);
						CommonMessage.debugMsg(plmTlUnplannedmaintdtl.getUpmdKeyid());
						plmTlUnplannedmaintdtl.setUpmdUpmmKeyid(plmTlUnplannedmaintmst.getUpmmKeyid());
						CommonMessage.debugMsg(plmTlUnplannedmaintmst.getUpmmKeyid());
						if( ! dbActionTemplate.checkDuplicateValue(PlmTlUnplannedmaintdtlSql.TBL_PLM_TL_UNPLANNEDMAINTDTL,
								"UPMD_UPMM_KEYID", plmTlUnplannedmaintmst.getUpmmKeyid(), "") )
						{	
							sqls.add(PlmTlUnplannedmaintdtlSql.getInsertSql(plmTlUnplannedmaintdtlSql.getUpmdDbFields(), plmTlUnplannedmaintdtl.getSaveArray()));
						}	
						else{
							sqls.add(PlmTlUnplannedmaintdtlSql.getUpdateSql(plmTlUnplannedmaintdtlSql.getUpmdDbFields(), plmTlUnplannedmaintdtl.getSaveArray()));
						}	
					}
			}
			
				
				insertShiftWise(bdmTlMst,sqls);
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsWno()))
					updateWorkOrder(bdmTlMst,womTlWomst,sqls);			
				else
					insertWorkOrder(bdmTlMst,sqls);
				
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
				{
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalphenomena()));
					
					String Phenomena = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_PHENOMENAMST, "BPHM_PHENOMENANAME", "BPHM_KEYID", bdmTlMst.getBdmsFinalphenomena());
					CommonMessage.debugMsg("checkPhnLinkExists : "+checkPhnLinkExists);
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalphenomena(),Phenomena,"PHN",sqls);
						
					}
				}
				CommonMessage.debugMsg(" Bdm Tl If dao impl :1");
				if(FilterCondSql.isValidKeyId(bdmTlMst.getBdmsFinalcause()))
				{
					
					String parentId = bdmTlMst.getBdmsFactoryid() + "-"+bdmTlMst.getBdmsSectionid();
						   parentId += "-"+bdmTlMst.getBdmsCellid() + "-"+bdmTlMst.getBdmsMachineid();
						   parentId += "-"+bdmTlMst.getBdmsAssemblyid()+ "-"+bdmTlMst.getBdmsFinalphenomena();
					String checkPhnLinkExists = dbActionTemplate.getSingleValue(BdmTlMstSql.checkPhnLinkExists(parentId,bdmTlMst.getBdmsFinalcause()));
					String Cause = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_CAUSEMST, "BCSM_NAME", "BCSM_KEYID", bdmTlMst.getBdmsFinalcause());
					if(checkPhnLinkExists.equals("0"))
					{
						insertPhenCauseLink(parentId,bdmTlMst.getBdmsFinalcause(),Cause,"CAS",sqls);
						
					}
				}
				//CommonMessage.debugMsg("SQL"+sqls.get(1));
			}
			
			
			dbActionTemplate.executeStatements(sqls);
			
		
		
		return bdmTlMst;
	}
	
	public BdmTlMst delete(BdmTlMst bdmTlMst) throws Exception 
	{
		List<String> sqls = new ArrayList<String>();
		BdmTlMstSql bdmTlMstSql = new BdmTlMstSql();
		try {
			CommonMessage.debugMsg("Inside the DaoImpl Delete");
			List<BdmTlDtl> bdmTlDtls= bdmTlMst.getBdmDetail();
			String woId = "";
			CommonMessage.debugMsg("------------------------->"+bdmTlMst.getBdmsWoprodaccepflag());
			CommonMessage.debugMsg("------------------------->"+bdmTlMst.getBdmsReporteddate());
			
			/*if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoprodaccepflag()))
			{
				if(bdmTlMst.getBdmsWoprodaccepflag().equals("Y"))
				{*/
					woId = dbActionTemplate.getSingleValue(TableNames.TBL_WOM_TL_MST,"WOMS_KEYID", "WOMS_ACTIVITYID", bdmTlMst.getBdmsKeyid());
					if(UIUtils.isValidKeyId(woId))
					{
						CommonMessage.debugMsg("ID : "+woId);
						sqls.add(BdmTlMstSql.getUpdateWoSql(bdmTlMst.getBdmsReporteddate(),woId));
					}
				/*}
			}*/
			if( bdmTlDtls != null && bdmTlDtls.size()> 0 )
			{	
				CommonMessage.debugMsg("Inside the detail table");
				for( BdmTlDtl bdmTlDtl : bdmTlDtls ){
					bdmTlDtl.setBdanBdms_keyid(bdmTlMst.getBdmsKeyid());
					String bdanId = dbActionTemplate.getSingleValue(BdmTlDtlSql.TBL_BDM_TL_DTL,"BDAN_KEYID", "BDAN_BDMS_KEYID", bdmTlMst.getBdmsKeyid());
					bdmTlDtl.setBdanKeyid(bdanId);
					sqls.add(BdmTlDtlSql.getDeleteSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
				}				
			}
			sqls.add(BdmTlMstSql.getDeleteSql(bdmTlMstSql.getBdmsDbFields(), bdmTlMst.getSaveArray()));
			sqls.add("delete from "+SapExternalServiceMstSql.TBL_SAP_EXTERNAL_SERVICE_MST+" where EXTM_NOTIFICATIONNO = '"+bdmTlMst.getBdmsKeyid()+"'");
			CommonMessage.debugMsg("SQL Delete"+sqls.get(0));
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}		
		return bdmTlMst;		
	}
	public WomTlCommunicationlog saveCommTxt(WomTlCommunicationlog womTlCommunicationlog) throws Exception
	{
		try{			
			CommonMessage.debugMsg("saveComm DaOIMPL");
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */
			CommonMessage.debugMsg(womTlCommunicationlog.getWcmlKeyid());
			if(!UIUtils.isValidKeyId(womTlCommunicationlog.getWcmlKeyid())){
				womTlCommunicationlog.setWcmlKeyid(dbActionTemplate.getSequenceNumber(WomTlCommunicationlogSql.TBL_WOM_TL_COMMUNICATIONLOG, 11, "CMC", "MMYY", "Y"));
				//(WomTlCommunicationlogSql.TBL_WOM_TL_COMMUNICATIONLOG)); // set the sequnce number
				sqls.add(WomTlCommunicationlogSql.getInsertSql(womTlCommunicationlogSql.getWcmlDbFields(), womTlCommunicationlog.getSaveArray())); // add insert sql for master table
			}
			else
			{
				CommonMessage.debugMsg("grid update" + womTlCommunicationlog.getWcmlKeyid());
				sqls.add(WomTlCommunicationlogSql.getUpdateSql(womTlCommunicationlogSql.getWcmlDbFields(), womTlCommunicationlog.getSaveArray())); // add insert sql for master table
			}
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	 
		}catch(Exception e){
			e.printStackTrace();
		}
		return womTlCommunicationlog;
	}
	public String deleteCommLog(String comLogKeyid) throws Exception {
// 
		try{
			String delsql = WomTlCommunicationlogSql.getDeleteSql(comLogKeyid);
			
			dbActionTemplate.executeStatement(delsql);
			return "Data Deleted Successfully";
		}catch(Exception e){
			return "Data Not Deleted";
		}
	}
	
	
	// TODO Auto-generated method stub
	
	
	
   public List<String[]> getAllBD(CommonFilter commonFilter) throws Exception {
	  
		try
		{
			String maintMode = commonFilter.getMaintMode();
			List<String> paramValues = new ArrayList<String>();		
			
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
			String keyId = FilterCondSql.getComboSelectionId(commonFilter.getCmbbdRootCause());
			String keyid = commonFilter.getMainkeyid();
			if(keyid != null && keyid != "")
			{
				if(keyid.substring(0,3).equals("FCT"))
					condParms += "FACTORYID="+keyid;
				else if(keyid.substring(0,3).equals("CMP"))
					condParms += "COMPANYID="+keyid;
				else if(keyid.substring(0,3).equals("LCN"))
					condParms += "LOCATIONID="+keyid;
				else if(keyid.substring(0,3).equals("LIN"))
					condParms += "SECTIONID="+keyid;
				else if(keyid.substring(0,3).equals("CEL"))
					condParms += "CELLID="+keyid;
				else if(keyid.substring(0,3).equals("MCH"))
					condParms += "MACHINEID="+keyid;
				else if(keyid.substring(0,3).equals("ASM"))
				{	
					condParms += "ASSEMBLYID="+keyid;
					condParms += ";MACHINEID="+commonFilter.getMachineId();
				}		
				condParms += ";";
			}		 
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			List<String[]> dataList =null;
			if(CommonFunctions.isValidKeyId(maintMode))				
				 dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETUNPLANNEDMAINTS", paramValues);
			else
				dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_GetActivitiesForBDA", paramValues);	
			CommonMessage.debugMsg("After " +dataList.size());
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
			/*Updated 20-Mar-2012
			String sql = BdmTlMstSql.selectBdSql();
			List<String > paramValues = new ArrayList<String>();			
			//String frmDt = com.akranta.tpm.utils.CommonFunctions.getFirstDateofMonth(-1);
			//String toDt = com.akranta.tpm.utils.CommonFunctions.getTodayNextDate();				
			/*if (commonFilter.getFromDate() == Constants.passNullDate) 
				commonFilter.setFromDate(frmDt);
			if (commonFilter.getToDate() == Constants.futureNullDate) 
				commonFilter.setToDate(toDt);*/		
		
			/*paramValues.add(commonFilter.getFromDate());
			paramValues.add(commonFilter.getToDate());
			paramValues.add(FilterCondSql.getComboSelectionId(commonFilter.getFactory()).trim());
			paramValues.add(FilterCondSql.getComboSelectionId(commonFilter.getSection()).trim());
			paramValues.add(FilterCondSql.getComboSelectionId(commonFilter.getCell()).trim());
			paramValues.add(FilterCondSql.getComboSelectionId(commonFilter.getMachine()).trim());
			CommonMessage.debugMsg("Date : "+commonFilter.getFromDate()+"///////////"+commonFilter.getToDate());
			//return dbActionTemplate.getDataList(sql);
			return dbActionTemplate.processFunctionCalls(sql,paramValues);*/	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
   
   @Override
   public List<java.lang.String[]> getAllBreakdownnew(CommonFilter commonFilter)
   		throws Exception {
   	// TODO Auto-generated method stub
	   StringBuffer sql = new StringBuffer();
		sql.append("select 'ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651','BDM13100001','B','5-NOV-2013 9:50' "
				+ " From Dual "
				+ " Union All "
				+"select 'ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651','BDM13100002','A','8-NOV-2013 11:00' "
				+ " From Dual "
				+ " Union All "
				+"select 'ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651','BDM13100003','C','14-NOV-2013 13:20' "
				+ " From Dual "
				+ " Union All "
				+"select 'ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651','BDM13100004','B','20-NOV-2013 15:00' "
				+ " From Dual "
				+ " Union All "
				+"select 'ITC-BCM2000 / SBU 2SBU 2 / PBU BOARDPBU BOARD / TEST SBU1SBU2 / TEST PBU2PBU2 / FH - 123FH-123 / FH 123 - SHEETER 1, 2 & 6 (CP)FH 123 - SHEETER 1, / SHEETER-1 SLITTER UNIT10001651','BDM13100005','A','23-NOV-2013 20:40' "
				+ " From Dual "
				);
		CommonMessage.debugMsg("Breakdown"+sql);
		
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		
		return gridData;
   }


@Override
public BdmTlMst select(String keyid) throws Exception {
	// TODO Auto-generated method stub
	BdmTlMst bdmTlMst = new BdmTlMst();
	String sql = BdmTlMstSql.selectBD();
	CommonMessage.debugMsg(sql);
	Object args [] = new Object [] { keyid };
	bdmTlMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	String sqlExtServ = "Select EXTM_KEYID from SAP_EXTERNAL_SERVICE_MST where EXTM_NOTIFICATIONNO= '"+bdmTlMst.getBdmsWno()+"'";
	String extKeyid = dbActionTemplate.getSingleValue(sqlExtServ);
	CommonMessage.debugMsg(keyid+" KEYIDSSS  "+extKeyid);
	if(UIUtils.isValidKeyId(extKeyid))
	bdmTlMst.setExternalserviceId(extKeyid );
	return bdmTlMst;
}

public PlmTlUnplannedmaintmst selectUPM(String keyid) throws Exception {
	// TODO Auto-generated method stub
	PlmTlUnplannedmaintmst plmTlUnplannedmaintmst = new PlmTlUnplannedmaintmst();	
	String sql = BdmTlMstSql.selectUPMSql();
	CommonMessage.debugMsg(sql);
	Object args [] = new Object [] { keyid };
	plmTlUnplannedmaintmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return plmTlUnplannedmaintmst;
}

public PlmTlUnplannedmaintdtl selectUPMDetail(String keyid) throws Exception {
	// TODO Auto-generated method stub
	PlmTlUnplannedmaintdtl plmTlUnplannedmaintdtl = new PlmTlUnplannedmaintdtl();
	String sql = BdmTlMstSql.selectUPMDetailSql();
	CommonMessage.debugMsg(sql);
	Object args [] = new Object [] { keyid };
	plmTlUnplannedmaintdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return plmTlUnplannedmaintdtl;
}

/*public List<String[]> getShift(List<String> paramValues)
{
	try
	{			
		String sql = BdmTlMstSql.getShiftFunction();	
		
		List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramValues);	
		CommonMessage.debugMsg("Size : "+fillShift.size());
		
		if(fillShift.size() == 0)
		{
			paramValues.set(2, "{}");
			CommonMessage.debugMsg(paramValues.get(0)+"-"+paramValues.get(1)+"-"+paramValues.get(2)+"-"+paramValues.get(3));
			List<String []> fillShift2 = dbActionTemplate.processFunctionCalls( sql,paramValues);	
			CommonMessage.debugMsg("Size : "+fillShift2.size());
			
			if(fillShift2.size() == 0)
			{
				paramValues.set(1, "{}");
				CommonMessage.debugMsg(paramValues.get(0)+"-"+paramValues.get(1)+"-"+paramValues.get(2)+"-"+paramValues.get(3));
				List<String []> fillShift3 = dbActionTemplate.processFunctionCalls( sql,paramValues);
				CommonMessage.debugMsg("Size : "+fillShift3.size());
				return fillShift3;
			}
			return fillShift2;
		}
		return fillShift;
		//commonFilter.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
		//return  commonFilter;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return null;
}*/
public String getShift(ShiftBean shiftBean)
{
	try
	{			
		String sql = BdmTlMstSql.getShiftFunction();
		CommonMessage.debugMsg(sql);
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(shiftBean.getFactId());
		paramValues.add(shiftBean.getSectId());
		paramValues.add(shiftBean.getCellId());
		paramValues.add(shiftBean.getFromTime());
		CommonMessage.debugMsg(shiftBean.getFactId());
	//	List<String []> fillShift = dbActionTemplate.processFunctionCalls( sql,paramValues);
		List<String []> fillShift = fnCallApi.callMultiParamFunction( "BDM_FN_GetshiftForTime_SB",paramValues,2,false);
		CommonMessage.debugMsg("Shift : "+fillShift.get(0)[0]);
		return fillShift.get(0)[0];
	}
	catch(Exception e)
	{
		//CommonMessage.debugMsg("err : "+e.toString());
	}
	return null;
}
public List<String[]> getDownTime(List<String> paramValues)
{
	try
	{			
		String sql = BdmTlMstSql.getDownTimeFunction();	
		
		List<String []> downTimeDatas = dbActionTemplate.processFunctionCalls( sql,paramValues);	
		CommonMessage.debugMsg("Size : "+downTimeDatas.size());
		return downTimeDatas;		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return null;
	
}
public List<String[]> getCommText(String bdId)
{
	String sql = BdmTlMstSql.getCommTextSql(bdId);
	CommonMessage.debugMsg(sql);
	List<String[]> commDatas;	
	try {
		commDatas = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("Size : "+commDatas.size());				
		return commDatas;	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}
public List<String[]> getYY(String wwNo)
{
	try
	{			
		String sql = BdmTlMstSql.getYYSql();
		List<String > paramValues = new ArrayList<String>();
		paramValues.add(wwNo);
		CommonMessage.debugMsg("Roopa wwNo : "+wwNo);
		List<String []> yyDatas = dbActionTemplate.processFunctionCalls( sql,paramValues);	
		CommonMessage.debugMsg("Size : "+yyDatas.size());
		return yyDatas;		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}
	return null;
	
}
public List<String[]> getPillarClassfcn(String pillar) throws Exception
{
	String sql = BdmTlMstSql.getPillarClassfcnSql(pillar);
	CommonMessage.debugMsg("....................................");
	CommonMessage.debugMsg(sql);
	List<String > paramValues = new ArrayList<String>();
	paramValues.add(pillar);
	
	return dbActionTemplate.getDataList(sql);
}
public List<String[]> getRootCause(String wwNo) throws Exception
{
	String sql = BdmTlMstSql.getRootCauseSql();
	List<String > paramValues = new ArrayList<String>();
	paramValues.add(wwNo);	
	return dbActionTemplate.getDataList(sql);	
	
}
public String getPhenType(String bookedPhen)
{
	try
	{			
		String phenType = dbActionTemplate.getSingleValue(TableNames.TBL_BDM_TL_PHENOMENAMST, "BPHM_PHENOMENATYPE", "BPHM_KEYID", bookedPhen);
		CommonMessage.debugMsg("getPhenType "+phenType);
		return phenType;		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		
	}	
	return bookedPhen;
	
}
public List<String> insertPhenCauseLink(String parentId,String originalId,String dispCode,String type,List<String> sqls) 	throws Exception {
	
	BdmTlPhncauselink bdmTlPhncauselink = new BdmTlPhncauselink();
	bdmTlPhncauselink.setBpclOriginalid( originalId);
	bdmTlPhncauselink.setBpclElementid(parentId + "-"+originalId);
	bdmTlPhncauselink.setBpclParentid(parentId);
	bdmTlPhncauselink.setBpclDisplaycode(dispCode);
	bdmTlPhncauselink.setBpclElementtype(type);
	bdmTlPhncauselink.setBpclActive("Y");
	BdmTlPhencauseSql bdmTlPhenCauseSql = new BdmTlPhencauseSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
	try{		
		sqls.add(bdmTlPhenCauseSql.getPclInsertSql(bdmTlPhenCauseSql.getBpclDbFields(), bdmTlPhncauselink.getSaveArray())); // add insert sql for master table
	}catch(Exception e)
	{
		throw new Exception(e.getMessage());
	}
	return sqls;
}

public List<String> insertWorkOrder(BdmTlMst bdmTlMst,List<String> sqls) 	throws Exception {
	WomTlWomst womTlWomst = new WomTlWomst();
	fillWorkOrder(womTlWomst,bdmTlMst);	
	WomTlWomstSql womTlWomstSql = new WomTlWomstSql(); // contains dbtable,field names, Field types and related sqls  of master table
	
		
		womTlWomst.setWomsKeyid(dbActionTemplate.getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST, 15, "MW", null, null));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
		bdmTlMst.setBdmsWno(womTlWomst.getWomsKeyid());
		sqls.add(WomTlWomstSql.getInsertSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray())); // add insert sql for master table
	
	return sqls;
}
public List<String>  updateWorkOrder(BdmTlMst bdmTlMst,WomTlWomst womTlWomst,List<String> sqls) 	throws Exception,BusinessApplicationExceptions{ 
	
	

	
	fillWorkOrder(womTlWomst,bdmTlMst);
	CommonMessage.debugMsg("STATUS ---- > "+bdmTlMst.getBdmsStatus() + " : "+womTlWomst.getWomsStatus());
	List<String[]>  overlapFlag = isMSRExist(womTlWomst);
	if(overlapFlag.size()>0)
	{
		throw new BusinessApplicationExceptions("msrOverlap,");
	}
	/*String checkWOConfig = dbActionTemplate.getSingleValue(TableNames.TBL_ADM_TL_CONFIGURATIONMST, "CNFM_SETTINGVALUE", "CNFM_CODE", "MSRCOMPLETEDDATE");
	String MSRStatus = womTlWomst.getWomsStatus();
	if(UIUtils.isValidKeyId(MSRStatus)) 
	{
	 if(MSRStatus.equals("C"))
	 {
		 if(UIUtils.isValidKeyId(checkWOConfig))
		 {
		
			int noOfDays = Integer.parseInt(checkWOConfig);
			int selDays = CommonFunctions.getDateDiff(womTlWomst.getWomsOccurreddate(),womTlWomst.getWomsWorkenddate());
			CommonMessage.debugMsg(noOfDays + " : "+selDays);
			if(selDays > noOfDays)
				throw new BusinessApplicationExceptions("MAXCOMPDATE,");			
		 }
	 }
	}*/
	WomTlWomstSql womTlWomstSql = new WomTlWomstSql();
	
	womTlWomst.setWomsKeyid(bdmTlMst.getBdmsWno());
	//bdmTlMst.setBdmsWno(womTlWomst.getWomsKeyid());
	sqls.add(WomTlWomstSql.getUpdateSql(womTlWomstSql.getWomsDbFields(), womTlWomst.getSaveArray()));
		
	return sqls;
}
@Override
public Workbook breakdownRpt(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
	   ResultSet rs = null;
	   try{
		
		rs =   getGenMaintRptResultSet(commonFilter);
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		
		List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
		XLConditionalFormats statusBooked = new XLConditionalFormats();
		statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked.setFontHeightPoint((short)8);
		statusBooked.setFontBoldWeight((short)20);
		statusBooked.setDbChkColIndx(28);
		statusBooked.setFromCol(2);
		statusBooked.setToCol(2);
		statusBooked.setOperator(ComparisonOperator.EQUAL);
		statusBooked.setCondValue("COMPLETED"); 
		statusBooked.setIdentfier("C");
		statusBooked.setBgColor(new RGB(67, 197, 221));
		condFormats.add(statusBooked);
		
		XLConditionalFormats statusBooked2 = new XLConditionalFormats();
		statusBooked2.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked2.setFontHeightPoint((short)8);
		statusBooked2.setFontBoldWeight((short)20);
		statusBooked2.setDbChkColIndx(25);
		statusBooked2.setFromCol(2);
		statusBooked2.setToCol(2);
		statusBooked2.setOperator(ComparisonOperator.EQUAL);
		statusBooked2.setCondValue("PENDING"); 
		statusBooked2.setIdentfier("P");
		statusBooked2.setBgColor(new RGB(224, 195, 195));
		condFormats.add(statusBooked2);
		
		XLConditionalFormats statusBooked3 = new XLConditionalFormats();
		statusBooked3.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked3.setFontHeightPoint((short)8);
		statusBooked3.setFontBoldWeight((short)20);
		statusBooked3.setDbChkColIndx(23);
		statusBooked3.setFromCol(2);
		statusBooked3.setToCol(2);
		statusBooked3.setOperator(ComparisonOperator.EQUAL);
		statusBooked3.setCondValue("PENDING"); 
		statusBooked3.setIdentfier("P");
		statusBooked3.setBgColor(new RGB(175, 214, 254));
		condFormats.add(statusBooked3);
		
		XLConditionalFormats statusBooked4 = new XLConditionalFormats();
		statusBooked4.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked4.setFontHeightPoint((short)8);
		statusBooked4.setFontBoldWeight((short)20);
		statusBooked4.setDbChkColIndx(22);
		statusBooked4.setFromCol(2);
		statusBooked4.setToCol(2);
		statusBooked4.setOperator(ComparisonOperator.EQUAL);
		statusBooked4.setCondValue("PENDING"); 
		statusBooked4.setIdentfier("P");
		statusBooked4.setBgColor(new RGB(239, 182, 239));
		condFormats.add(statusBooked4);
		
		XLConditionalFormats statusBooked5 = new XLConditionalFormats();
		statusBooked5.setFontName(XLConditionalFormats.FONT_DEFAULT);
		statusBooked5.setFontHeightPoint((short)8);
		statusBooked5.setFontBoldWeight((short)20);
		statusBooked5.setDbChkColIndx(22);
		statusBooked5.setFromCol(2);
		statusBooked5.setToCol(2);
		statusBooked5.setOperator(ComparisonOperator.EQUAL);
		statusBooked5.setCondValue("REPEAT BREAKDOWN"); 
		statusBooked5.setIdentfier("R");
		statusBooked5.setBgColor(new RGB(249, 162, 172));
		condFormats.add(statusBooked5);
		excelUtils.setCondFormats(condFormats);
		
		return excelUtils.writeToExcel(rs,rptFormat,0,1,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
}
@Override
public List<String[]> getSapInfoList(java.lang.String wwNo) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	sql.append("select 'Sl.No','Part No','Spare Name','Spare Loc','Model','Qty','Rate','Avl Stock' from dual UNION ");
	sql.append(" SELECT 'Sl.No',SSPM_SPARENO,SPRM_PARTNAME,SSPM_STORAGELOCATION,SPRM_MODEL,TO_CHAR(SSPM_QUANTITY),TO_CHAR(SSPM_RATE),'' as AvailStock ");
	sql.append(" FROM  SAP_TL_SPARESREPLACED,GEN_TL_SPARESMST ");
    sql.append(" WHERE 1=1 AND SSPM_SPARENO  = SPRM_PARTNO ");	
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

	return gridData;
}

@Override
public List<String[]> getExtServiceList(CommonFilter commonFilter,String ExtMasterId)
			throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	List<String> params=new ArrayList<String>();
	//sql.append("select 'Notification No','WO No','SAP Ref No.','Operation Qty','Price','Material Group','Purch Group','Agreement','Recipient','Requisitioner','Planned Delivery Time','External Sub contract ?','Sort Term','Cost Element','Vendor','Info Record','Unloading Point','Tracking Number','FW Order' from dual");
	sql.append(" select EXTD_EXTM_KEYID mstkeyid,EXTD_KEYID detKeyid, Extd_Service_No \"ServKey\",MST.SERVM_ACTIVITY_NUMBER  \"SERVICENO\",EXTD_SERVICE_TEXT \"SERVICETEXT\",EXTD_QTY \"QUANTITY\", exTD_UOM \"UOMCODE\",UOMM_CODE \"UOM\",EXTD_VALUE \"GROSSPRICE\",EXTD_CURRENCY \"CURRENCY\",EXTD_TOTALPRICE \"TOTALPRICE\"   ");
	//sql.append("  ");
	sql.append(" from  SAP_EXTERNAL_SERVICE_DTL,gen_tl_servicemst mst,ADM_TL_UOMMST  ");
	sql.append(" WHERE EXTD_EXTM_KEYID = '"+ExtMasterId+"' and EXTD_SERVICE_NO= MST.SERVM_KEYID(+) and extd_uom=uomm_keyid(+)");
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("sql..." + sql);String cSql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
    String cntStr = dbActionTemplate.getSingleValue(cSql);
    int count = Integer.parseInt(cntStr); 
		commonFilter.setTotalRecordCnt(count);
		if( count > 0  ){
			GridParams gridParams = new GridParams();
			gridParams.setFromRow(commonFilter.getFromRow());
			gridParams.setToRow(commonFilter.getToRow());

			gridParams.setGridFilters(commonFilter.getGridFilter());
			String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
			CommonMessage.debugMsg("sql " + oSql);
			String cntSql=CommonFilterSqls.countSql(oSql, null) ;
			String cntFinal=dbActionTemplate.getSingleValue(cntSql);
			
	//List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(oSql.toString(), null);
			List<String[]> gridData = dbActionTemplate.getDataList(oSql, params);
	return gridData;
		}
		throw new NoDataFoundException("No Data Found");
}

@Override
public List<String[]> getExtSubList(String extSrvID)
		throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	//sql.append("select 'Component No.','PART NO','Requirement Qty','UOM','Item Category','Storage Location','Material Rework Indicator' from dual");
	sql.append("select EXTR_EXTM_KEYID as mstKeyid,EXTR_KEYID as repairId , EXTR_COMPONENT_NO as \"Component No.\",EXTR_PARTNO  as  \"PART NO\",");
	sql.append(" EXTR_REQUIREMENT_QTY as \"Requirement Qty\",EXTR_UOM  as \"UOM\",EXTR_ITEM_CATEGORY as \"Item Category\",EXTR_STORAGE_LOCATION as \"Storage Location\",EXTR_MAT_REWORK_INDI as \"Material Rework Indicator\" from SAP_EXTERNAL_REPAIR");			
	// TODO Auto-generated method stub
	sql.append(" where EXTR_EXTM_KEYID='");
	sql.append(extSrvID);
	sql.append("'");
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(),null);

	return gridData;
}

@Override
public List<String[]> getExtRepairList(java.lang.String wwNo)
		throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	String sql1="";
	sql.append("select 'Service No.','Service Text','Qty','Value','UOM','Currency','Cost Element' from dual");
	
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("sql..." + sql);
	List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());

	return gridData;
}

private ResultSet getGenMaintRptResultSet(CommonFilter commonFilter) throws Exception
{
	List<String> paramValues = getFilterParamValues(commonFilter);
	
	return dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_GetActivitiesForBDA", paramValues);
}
private List<String> getFilterParamValues(CommonFilter commonFilter){
	List<String> paramValues = new ArrayList<String>();		
	
	String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
	
	paramValues.add(condParms);
	paramValues.add(commonParams);
			
	return paramValues;
}	

private void fillWorkOrder(WomTlWomst womTlWomst,BdmTlMst bdmTlMst) 	throws Exception {
	String woStatus = dbActionTemplate.getSingleValue(WomTlWomstSql.TBL_WOM_TL_WOMST, "WOMS_STATUS", "WOMS_KEYID", bdmTlMst.getBdmsWno());
	womTlWomst.setWomsActive(bdmTlMst.getBdmsActive());
	String dateTime = CommonFunctions.dateTimeNow();
	womTlWomst.setWomsCreatedon(bdmTlMst.getBdmsCreatedon());
	womTlWomst.setWomsModifiedon(bdmTlMst.getBdmsModifiedon());
	
	String reportedDate = Constants.passNullDate;
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsReporteddate()))
	{		
		reportedDate = bdmTlMst.getBdmsReporteddate();
	}	
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFactoryid()))
		womTlWomst.setWomsFactoryid(bdmTlMst.getBdmsFactoryid());
	else
		womTlWomst.setWomsFactoryid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsSectionid()))
		womTlWomst.setWomsSectionid(bdmTlMst.getBdmsSectionid());
	else
		womTlWomst.setWomsSectionid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsCellid()))
		womTlWomst.setWomsCellid(bdmTlMst.getBdmsCellid());
	else
		womTlWomst.setWomsCellid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsMachineid()))
		womTlWomst.setWomsMachineid(bdmTlMst.getBdmsMachineid());
	else
		womTlWomst.setWomsMachineid("{}");
	
		womTlWomst.setWomsWorkcenterid("{}");
	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsElementid()))
			womTlWomst.setWomsElementid(bdmTlMst.getBdmsElementid());
		else
			womTlWomst.setWomsElementid("{}");
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFlid()))
			womTlWomst.setWomsFlid(bdmTlMst.getBdmsFlid());
		else
			womTlWomst.setWomsFlid("{}");
		
	if(UIUtils.isValidKeyId(reportedDate))	
		womTlWomst.setWomsOccurreddate(reportedDate);
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsShiftid()))	
		womTlWomst.setWomsShiftid(bdmTlMst.getBdmsShiftid());	
	else
		womTlWomst.setWomsShiftid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsEntrydate()))
	{
		womTlWomst.setWomsShiftdate(bdmTlMst.getBdmsEntrydate());
	}
	else
		womTlWomst.setWomsShiftdate("{}");	

	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsPriority()))
	{
		womTlWomst.setWomsPriority(bdmTlMst.getBdmsPriority());
	}
	else
		womTlWomst.setWomsPriority("X");
	
	womTlWomst.setWomsReporteddate(reportedDate);
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsBookedby()))
	{
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsReportedby()))
			womTlWomst.setWomsReportedby(bdmTlMst.getBdmsBookedby());
		
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsAcceptedby()))
			womTlWomst.setWomsAcceptedby(bdmTlMst.getBdmsBookedby());
		
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduleby()))
			womTlWomst.setWomsRescheduleby(bdmTlMst.getBdmsBookedby());
		
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsProductionby()))
			womTlWomst.setWomsProductionby(bdmTlMst.getBdmsBookedby());
		
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovedby()))
			womTlWomst.setWomsRequestapprovedby(bdmTlMst.getBdmsBookedby());
		
		//womTlWomst.setWomsReportedby(bdmTlMst.getBdmsBookedby());
		//womTlWomst.setWomsAcceptedby(bdmTlMst.getBdmsBookedby());
		//womTlWomst.setWomsRescheduleby(bdmTlMst.getBdmsBookedby());
		//womTlWomst.setWomsProductionby(bdmTlMst.getBdmsBookedby());
		//womTlWomst.setWomsRequestapprovedby(bdmTlMst.getBdmsBookedby());
	}
	else
	{
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsReportedby()))
			womTlWomst.setWomsReportedby("{}");	
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsAcceptedby()))
			womTlWomst.setWomsAcceptedby("{}");	
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsRescheduleby()))
			womTlWomst.setWomsRescheduleby("{}");	
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsProductionby()))
			womTlWomst.setWomsProductionby("{}");	
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovedby()))
			womTlWomst.setWomsRequestapprovedby("{}");	
	
		
		//
		//womTlWomst.setWomsRequestapprovedby("{}");
	}
	womTlWomst.setWomsRescheduleby("{}");
	womTlWomst.setWomsProductionby("{}");
	womTlWomst.setWomsProductionstop("X");	
	womTlWomst.setWomsMachinecondition("X");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsActivitytype()))
		womTlWomst.setWomsActivitytype("B");
	if(!UIUtils.isValidKeyId(womTlWomst.getWomsFinalactivitytype()))
		womTlWomst.setWomsFinalactivitytype("B");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsAlarmdescription()))	
		womTlWomst.setWomsAlarmno(bdmTlMst.getBdmsAlarmdescription());
	else
		womTlWomst.setWomsAlarmno("{}");
	
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsAssemblyid()))
		womTlWomst.setWomsAssemblyid(bdmTlMst.getBdmsAssemblyid());
	else
		womTlWomst.setWomsAssemblyid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsSubassemblyid()))
		womTlWomst.setWomsSubassemblyid(bdmTlMst.getBdmsSubassemblyid());
	else
		womTlWomst.setWomsSubassemblyid("{}");	
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsPartlocationid()))
		womTlWomst.setWomsPartlocation(bdmTlMst.getBdmsPartlocationid());
	else
		womTlWomst.setWomsPartlocation("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsSpareid()))
		womTlWomst.setWomsSpareid(bdmTlMst.getBdmsSpareid());
	else
		womTlWomst.setWomsSpareid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalphenomena()))
		womTlWomst.setWomsPhenomenaid(bdmTlMst.getBdmsFinalphenomena());
	else
		womTlWomst.setWomsPhenomenaid("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsFinalcause()))
		womTlWomst.setWomsCauseid(bdmTlMst.getBdmsFinalcause());
	else
		womTlWomst.setWomsCauseid("{}");
	
		womTlWomst.setWomsLocation("{}");
		
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsProblemdescription()))
		womTlWomst.setWomsProblem(bdmTlMst.getBdmsProblemdescription());
	else
		womTlWomst.setWomsProblem("{}");
	
	if(UIUtils.isValidKeyId(bdmTlMst.getBdmsRemarks()))
	{
		womTlWomst.setWomsBookingremarks(bdmTlMst.getBdmsRemarks());
		/*womTlWomst.setWomsAcceptedremarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsRescheduleremarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsAllottedremarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsRescheduledremarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsProductionremarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsRemarks(bdmTlMst.getBdmsRemarks());
		womTlWomst.setWomsRequestapprovremarks(bdmTlMst.getBdmsRemarks());*/
	}
	else
	{
		womTlWomst.setWomsBookingremarks("{}");	
	
	}
		womTlWomst.setWomsAcceptedremarks("{}");
		womTlWomst.setWomsRescheduleremarks("{}");
		womTlWomst.setWomsAllottedremarks("{}");
		womTlWomst.setWomsRescheduledremarks("{}");
		womTlWomst.setWomsProductionremarks("{}");
		womTlWomst.setWomsRemarks("{}");
		womTlWomst.setWomsRequestapprovremarks("{}");
		womTlWomst.setWomsAccepteddate(reportedDate);	
		womTlWomst.setWomsAcceptedflag("Y");	
		
		womTlWomst.setWomsProductionapproval("N");	
		womTlWomst.setWomsSafetypermitsrequried("N");	
		womTlWomst.setWomsSafetypermitid("{}");
	
		womTlWomst.setWomsRescheduleflag("A");	
		womTlWomst.setWomsRescheduledate(reportedDate);	
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoallottedflag()))
			womTlWomst.setWomsAllottedflag(bdmTlMst.getBdmsWoallottedflag());
		else
			womTlWomst.setWomsAllottedflag("N");
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsReceiveddate()))
			womTlWomst.setWomsAllotteddate(bdmTlMst.getBdmsReceiveddate());
		else
			womTlWomst.setWomsAllotteddate(reportedDate);
	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWostarttime()))
			womTlWomst.setWomsProposedstartdate(bdmTlMst.getBdmsWostarttime());
		else
			womTlWomst.setWomsProposedstartdate(reportedDate);
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsBookedtrade()))
			womTlWomst.setWomsTradeid(bdmTlMst.getBdmsBookedtrade());
		else
			womTlWomst.setWomsTradeid("{}");
	
		CommonMessage.debugMsg("Proposed Start Date : "+womTlWomst.getWomsProposedstartdate());
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendtime()))
			womTlWomst.setWomsProposedenddate(bdmTlMst.getBdmsWoendtime());
		else
			womTlWomst.setWomsProposedenddate(Constants.futureNullDate  + " "+"00:00");	
	
		CommonMessage.debugMsg("Proposed End Date : "+womTlWomst.getWomsProposedenddate());
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWostartflag()))
			womTlWomst.setWomsProposedstflag(bdmTlMst.getBdmsWostartflag());
		else
			womTlWomst.setWomsProposedstflag("N");
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendflag()))
			womTlWomst.setWomsProposedendflag(bdmTlMst.getBdmsWoendflag());
		else
			womTlWomst.setWomsProposedendflag("N");
		
		womTlWomst.setWomsProposeddtacceptflag("X");	
		womTlWomst.setWomsReschedulestartdate(Constants.passNullDate  + " "+"00:00");
		womTlWomst.setWomsRescheduleenddate(Constants.futureNullDate  + " "+"00:00");		
		womTlWomst.setWomsRescheduledstflag("N");
		womTlWomst.setWomsRescheduledendflag("N");		
	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoprodaccepflag()))
		    womTlWomst.setWomsProductionstartflag(bdmTlMst.getBdmsWoprodaccepflag());
		else
			womTlWomst.setWomsProductionstartflag("N");	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsProdaccepdate()))
			womTlWomst.setWomsProductionstartdate(bdmTlMst.getBdmsProdaccepdate());
		else
			womTlWomst.setWomsProductionstartdate(reportedDate);
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWostarttime()))
			womTlWomst.setWomsWorkstartdate(bdmTlMst.getBdmsWostarttime());
		else
			womTlWomst.setWomsWorkstartdate(reportedDate);
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendtime()))
		{
			womTlWomst.setWomsWorkenddate(bdmTlMst.getBdmsWoendtime());
			womTlWomst.setWomsWoapprovaldate(bdmTlMst.getBdmsWoendtime());
			womTlWomst.setWomsMachinereleaseddate(bdmTlMst.getBdmsWoendtime());
		}
		else
		{
			womTlWomst.setWomsWorkenddate(Constants.futureNullDate);
			womTlWomst.setWomsWoapprovaldate(Constants.passNullDate);
			womTlWomst.setWomsMachinereleaseddate(Constants.passNullDate);
		}
	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWostartflag()))
		    womTlWomst.setWomsWorkstartflag(bdmTlMst.getBdmsWostartflag());
		else
			womTlWomst.setWomsWorkstartflag("N");	
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsWoendflag()))
		{
		    womTlWomst.setWomsWorkendflag(bdmTlMst.getBdmsWoendflag());
		    womTlWomst.setWomsWoapprovalflag(bdmTlMst.getBdmsWoendflag());
		    womTlWomst.setWomsMachinereleaseflag(bdmTlMst.getBdmsWoendflag());
		}
		else
		{
			womTlWomst.setWomsWorkendflag("N");	
			womTlWomst.setWomsWoapprovalflag("N");
			womTlWomst.setWomsMachinereleaseflag("N");
		}
			
		//womTlWomst.setWomsFinalactivitytype("B");
	
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsKeyid()))
			womTlWomst.setWomsActivityid(bdmTlMst.getBdmsKeyid());
		else
			womTlWomst.setWomsActivityid("{}");

		womTlWomst.setWomsIntorextequip("N");	
		womTlWomst.setWomsIntorextequipdesc("{}");
		womTlWomst.setWomsStandbyadditionalinfo("{}");
		womTlWomst.setWomsStandbyremarks("{}");
		womTlWomst.setWomsSentforrepairflag("X");
		womTlWomst.setWomsSentrepairid("{}");
		womTlWomst.setWomsSentto("{}");
		womTlWomst.setWomsExceptedreturndate(Constants.passNullDate);
		womTlWomst.setWomsRepairremarks("{}");
		womTlWomst.setWomsJobopeningid("{}");
		womTlWomst.setWomsOrderno("{}");
		womTlWomst.setWomsRequestapproved("A");
		womTlWomst.setWomsSafetypermitcompleted("X");
		womTlWomst.setWomsSafetypermitapproved("X");
		womTlWomst.setWomsSafetypermitsignoff("X");
		womTlWomst.setWomsRequestapproveddate(reportedDate);
		womTlWomst.setWomsMaintpriority("0");
		womTlWomst.setWomsAllottedsource("{}");
		womTlWomst.setWomsAllottedsupplier("{}");
		womTlWomst.setWomsDirectentry("Y");
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsRelatedto()))
			womTlWomst.setWomsRelatedto(bdmTlMst.getBdmsRelatedto());
		else
			womTlWomst.setWomsRelatedto("X");
		
		womTlWomst.setWomsLoss("{}");
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsMould()))
			womTlWomst.setWomsMouldid(bdmTlMst.getBdmsMould());
		else
			womTlWomst.setWomsMouldid("{}");
		
		womTlWomst.setWomsNumofactivities("0");
		
		womTlWomst.setWomsPwdmwono("{}");
		womTlWomst.setWomsRefdoctype("{}");
		womTlWomst.setWomsRefdocid("{}");
		womTlWomst.setWomsProcessId("{}");
		womTlWomst.setWomsRequiredstart("{}");
		womTlWomst.setWomsRequiredend("{}");
		womTlWomst.setWomsDepartmentid("{}");
		womTlWomst.setWomsPlannergroup("{}");
		womTlWomst.setWomsTempfield1("{}");
		/*womTlWomst.setWomsTempfield5("{}");
		womTlWomst.setWomsTempfield4("{}");
		womTlWomst.setWomsTempfield3("{}");
		womTlWomst.setWomsTempfield2("{}");
		womTlWomst.setWomsTempfield1("{}");*/
		
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsCreatedby()))
			womTlWomst.setWomsModifiedby(bdmTlMst.getBdmsCreatedby());
		else
			womTlWomst.setWomsModifiedby("{}");
		if(UIUtils.isValidKeyId(bdmTlMst.getBdmsCreatedby()))
			womTlWomst.setWomsCreatedby(bdmTlMst.getBdmsCreatedby());
		else
			womTlWomst.setWomsCreatedby("{}");
	if(bdmTlMst.getBdmDetail()!= null && bdmTlMst.getBdmDetail().size()>0) // check for detail table data
	{	
		BdmTlDtl bdmDetail = (BdmTlDtl)bdmTlMst.getBdmDetail().get(0);
		
		if(UIUtils.isValidKeyId(bdmDetail.getBdanCostcentre()))
			womTlWomst.setWomsCostcenterid(bdmDetail.getBdanCostcentre());
		else
			womTlWomst.setWomsCostcenterid("{}");
		
		/*if(UIUtils.isValidKeyId(bdmDetail.getBdanTradeid()))
			womTlWomst.setWomsTradeid(bdmDetail.getBdanTradeid());
		else
			womTlWomst.setWomsTradeid("{}");*/
		
		if(UIUtils.isValidKeyId(bdmDetail.getBdanFailuretype()))
			womTlWomst.setWomsFailuretypeid(bdmDetail.getBdanFailuretype());
		else
			womTlWomst.setWomsFailuretypeid("{}");
		CommonMessage.debugMsg("Status BD "+bdmDetail.getBdanErppoststatus());
		
		
		CommonMessage.debugMsg("woStatus "+woStatus);

		if(UIUtils.isValidKeyId(bdmDetail.getBdanErppoststatus()))
		{
			if(bdmDetail.getBdanErppoststatus().equals("C"))
			{
				if(CommonFunctions.isValidKeyId(woStatus))
				{
					if(woStatus.equals("E"))
					{
						womTlWomst.setWomsStatus("P");
						womTlWomst.setWomsFinalstatus("PRODUCTION APPROVED WITHOUT COMPLETION");
					}
					else
					{
						womTlWomst.setWomsStatus(bdmDetail.getBdanErppoststatus());
						womTlWomst.setWomsFinalstatus(bdmDetail.getBdanErppoststatus());
					}
				}
				else
				{
					womTlWomst.setWomsStatus("C");
					womTlWomst.setWomsFinalstatus("COMPLETED");
				}
				
			}
			else
			{
				if(CommonFunctions.isValidKeyId(woStatus))
				{
					if(woStatus.equals("E"))
					{
						womTlWomst.setWomsStatus(woStatus);
						womTlWomst.setWomsFinalstatus("PRODUCTION APPROVED WITHOUT COMPLETION");
					}
					else
					{
						womTlWomst.setWomsStatus("L");
						womTlWomst.setWomsFinalstatus("ALLOTTED");
					}
				}
				else
				{
					womTlWomst.setWomsStatus("L");
					womTlWomst.setWomsFinalstatus("ALLOTTED");
				}
				
			}
		}
		else
		{
			womTlWomst.setWomsStatus("X");
			womTlWomst.setWomsFinalstatus("{}");
		}
		CommonMessage.debugMsg("Status IN BD: "+womTlWomst.getWomsStatus() + " BD STATUS : "+bdmDetail.getBdanErppoststatus());
		CommonMessage.debugMsg(womTlWomst.getWomsAllottedto());
		CommonMessage.debugMsg(womTlWomst.getWomsDoneby());
		CommonMessage.debugMsg(womTlWomst.getWomsWoapprovalby());
		CommonMessage.debugMsg(womTlWomst.getWomsMachinereleaseby());
		if(UIUtils.isValidKeyId(bdmDetail.getBdanCompletedby()))	
		{
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedto()))
				womTlWomst.setWomsAllottedto(bdmDetail.getBdanCompletedby());
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsDoneby()))
				womTlWomst.setWomsDoneby(bdmDetail.getBdanCompletedby());
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsWoapprovalby()))
				womTlWomst.setWomsWoapprovalby(bdmDetail.getBdanCompletedby());
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsMachinereleaseby()))
				womTlWomst.setWomsMachinereleaseby(bdmDetail.getBdanCompletedby());
		}
		else
		{
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsAllottedto()))
				womTlWomst.setWomsAllottedto("{}");
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsDoneby()))
				womTlWomst.setWomsDoneby("{}");
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsWoapprovalby()))
				womTlWomst.setWomsWoapprovalby("{}");
			if(!UIUtils.isValidKeyId(womTlWomst.getWomsMachinereleaseby()))
				womTlWomst.setWomsMachinereleaseby("{}");
		}
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsDoneby()))
			womTlWomst.setWomsDoneby("{}");
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsWoapprovalby()))
			womTlWomst.setWomsWoapprovalby("{}");
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsMachinereleaseby()))
			womTlWomst.setWomsMachinereleaseby("{}");
		if(!UIUtils.isValidKeyId(womTlWomst.getWomsRequestapprovedby()))
			womTlWomst.setWomsRequestapprovedby("{}");
	}	
}

@Override
public SapExternalServiceMst createExtService(
		SapExternalServiceMst newSapExternalServiceMst) throws Exception {
	// TODO Auto-generated method stub
	try{
		  CommonMessage.debugMsg("aCTIVE  "+newSapExternalServiceMst.getExtmActive());
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		newSapExternalServiceMst.setExtmKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceMstSql.TBL_SAP_EXTERNAL_SERVICE_MST,10, "SESM", "MMYY", "Y"));
		CommonMessage.debugMsg("test  "+newSapExternalServiceMst.getExtmKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceMst.getExtmCreatedby());
		sqls.add(SapExternalServiceMstSql.getInsertSql(sapExternalServiceMstsql.getExtmDbFields(),newSapExternalServiceMst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("MK  "+e.getMessage());
		throw new Exception(e.getMessage());
	}
	return newSapExternalServiceMst;
}

@Override
public SapExternalServiceMst getExtServiceData(String extKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	SapExternalServiceMst sapExternalServiceMst = new SapExternalServiceMst();
	String sql = "Select * from SAP_EXTERNAL_SERVICE_MST where EXTM_KEYID= ?";
	 
	Object args [] = new Object [] { extKeyid};
	sapExternalServiceMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	CommonMessage.debugMsg(extKeyid+" sapExternalServiceMst  ");
	return sapExternalServiceMst;
}

@Override
public SapExternalServiceMst updateExtService(
		SapExternalServiceMst newSapExternalServiceMst) throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonMessage.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg("test  "+newSapExternalServiceMst.getExtmKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceMst.getExtmCreatedby());
		sqls.add(SapExternalServiceMstSql.getUpdateSql(sapExternalServiceMstsql.getExtmDbFields(),newSapExternalServiceMst.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("update  "+e.getMessage());
	}
	return newSapExternalServiceMst;
}

@Override
public SapExternalServiceDtl createExtServiceDtl(
		SapExternalServiceDtl newSapExternalServiceDtl) throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonMessage.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		newSapExternalServiceDtl.setExtdKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL,10, "SESD", "MMYY", "Y"));
		CommonMessage.debugMsg("test  "+newSapExternalServiceDtl.getExtdKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceDtl.getExtdCreatedby());

		sqls.add(SapExternalServiceDtlSql.getInsertSql(sapExternalServiceDtlsql.getExtdDbFields(),newSapExternalServiceDtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("insert  "+e.getMessage());
	}
	return newSapExternalServiceDtl;
}

@Override
public SapExternalServiceDtl getServiceDtlData(String detailKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	SapExternalServiceDtl newSapExternalServiceDtl = new SapExternalServiceDtl();
	String sql = "select * from "+SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL+" where EXTD_KEYID = ?";
	Object args[] = new Object[]{detailKeyid};
	newSapExternalServiceDtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return newSapExternalServiceDtl;
}

@Override
public SapExternalServiceDtl UpdateExtServiceDtl(
		SapExternalServiceDtl newSapExternalServiceDtl) throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonMessage.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		//newSapExternalServiceDtl.setExtdKeyid(dbActionTemplate.getSequenceNumber(SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL,10, "SESD", "MMYY", "Y"));
		CommonMessage.debugMsg("test  "+newSapExternalServiceDtl.getExtdKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalServiceDtl.getExtdCreatedby());

		sqls.add(SapExternalServiceDtlSql.getUpdateSql(sapExternalServiceDtlsql.getExtdDbFields(),newSapExternalServiceDtl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("update  "+e.getMessage());
	}
	return newSapExternalServiceDtl;
}

@Override
public java.lang.String delteExtDetail(String detailKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	try{
		String delsql = "Delete from "+SapExternalServiceDtlSql.TBL_SAP_EXTERNAL_SERVICE_DTL+" where EXTD_KEYID = '"+detailKeyid+"'";
		dbActionTemplate.executeStatement(delsql);
		return "Data Deleted Successfully";
	}catch(Exception e){
		return "Data Not Deleted";
	}
	
}

@Override
public SapExternalRepair createExtRepairDtl(
		SapExternalRepair newSapExternalRepair) throws Exception {
	// TODO Auto-generated method stub
	try{
		 
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		newSapExternalRepair.setExtrKeyid(dbActionTemplate.getSequenceNumber(SapExternalRepairSql.TBL_SAP_EXTERNAL_REPAIR,10, "SESR", "MMYY", "Y"));
		CommonMessage.debugMsg("test  "+newSapExternalRepair.getExtrKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalRepair.getExtrCreatedby());
		sqls.add(SapExternalRepairSql.getInsertSql(sapExternalRepairSql.getExtrDbFields(),newSapExternalRepair.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("MK  "+e.getMessage());
		throw new Exception(e.getMessage());
	}
	return newSapExternalRepair;
}

@Override
public SapExternalRepair updateExtRepair(SapExternalRepair newSapExternalRepair)
		throws Exception {
	// TODO Auto-generated method stub
	try{
		CommonMessage.debugMsg("test  update extservice" );
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */
		CommonMessage.debugMsg("test  "+newSapExternalRepair.getExtrKeyid());
		CommonMessage.debugMsg("ceatedBYDaoimpl  "+newSapExternalRepair.getExtrCreatedby());
		sqls.add(SapExternalRepairSql.getUpdateSql(sapExternalRepairSql.getExtrDbFields(),newSapExternalRepair.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}catch(Exception e){
		e.printStackTrace();
		CommonMessage.debugMsg("update  "+e.getMessage());
	}
	return newSapExternalRepair;
}

@Override
public SapExternalRepair getRepairDtlData(String detailRepairKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	SapExternalRepair newSapExternalRepair = new SapExternalRepair();
	String sql = "select * from "+SapExternalRepairSql.TBL_SAP_EXTERNAL_REPAIR+" where EXTR_KEYID = ?";
	Object args[] = new Object[]{detailRepairKeyid};
	newSapExternalRepair.setSaveArray(dbActionTemplate.getDataArr(sql, args));
	return newSapExternalRepair;
}

@Override
public java.lang.String delteExtRprDetail(java.lang.String rpeDetailKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	try{
		String delsql = "Delete from "+SapExternalRepairSql.TBL_SAP_EXTERNAL_REPAIR+" where EXTR_KEYID = '"+rpeDetailKeyid+"'";
		dbActionTemplate.executeStatement(delsql);
		return "Data Deleted Successfully";
	}catch(Exception e){
		return "Data Not Deleted";
	}
}

@Override
public SapTlMaintenanceOrdermst getSapSpareInFoData(String woKeyid)
		throws Exception {
	// TODO Auto-generated method stub
	try{
		Object args[] = new Object[]{woKeyid};
		SapTlMaintenanceOrdermst sapTlMaintenanceOrdermst = new SapTlMaintenanceOrdermst();
		String sql = "select * from SAP_TL_MAINTENANCE_ORDERMST where MOMS_REFDOCID = ?";
		CommonMessage.debugMsg(sql+" -  "+ woKeyid);
		sapTlMaintenanceOrdermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return sapTlMaintenanceOrdermst;	 
	}catch(Exception e){
		return null;
	}
	
}

@Override
public List<String[]> getExtServiceList(CommonFilter commonFilter,String womsId, String taskId)
		throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql = new StringBuffer();
	List<String> params=new ArrayList<String>();
	//String sql1="select EXTM_KEYID from SAP_EXTERNAL_SERVICE_MST where EXTM_ORDERNO='"+womsId+"' and EXTM_TASKID='"+taskId+"'";
	//sql.append("select 'Notification No','WO No','SAP Ref No.','Operation Qty','Price','Material Group','Purch Group','Agreement','Recipient','Requisitioner','Planned Delivery Time','External Sub contract ?','Sort Term','Cost Element','Vendor','Info Record','Unloading Point','Tracking Number','FW Order' from dual");
	sql.append(" select EXTD_EXTM_KEYID mstkeyid,EXTD_KEYID detKeyid,EXTD_SERVICE_NO \"SERVICENO\",EXTD_SERVICE_TEXT \"SERVICETEXT\",EXTD_QTY \"QUANTITY\", EXTD_UOM \"UOM\",EXTD_CURRENCY \"CURRENCY\",EXTD_COST_ELEMENT \"Cost Element\" ");
	sql.append(" , Extd_Service_No \"Extd_Service_No\"");
	sql.append(" from  SAP_EXTERNAL_SERVICE_DTL");
	sql.append(" WHERE EXTD_EXTM_KEYID in (select EXTM_KEYID from SAP_EXTERNAL_SERVICE_MST where EXTM_ORDERNO='"+womsId+"' and EXTM_TASKID='"+taskId+"')");
	// TODO Auto-generated method stub
	CommonMessage.debugMsg("sql..." + sql);
	String cSql = CommonFilterSqls.countSql(sql.toString(), commonFilter.getGridFilter());
    String cntStr = dbActionTemplate.getSingleValue(cSql);
    int count = Integer.parseInt(cntStr); 
		commonFilter.setTotalRecordCnt(count);
		if( count > 0  ){
			GridParams gridParams = new GridParams();
			gridParams.setFromRow(commonFilter.getFromRow());
			gridParams.setToRow(commonFilter.getToRow());

			gridParams.setGridFilters(commonFilter.getGridFilter());
			String oSql =  CommonFilterSqls.addPaginationParams(sql.toString(), gridParams);
			CommonMessage.debugMsg("sql " + oSql);
			String cntSql=CommonFilterSqls.countSql(oSql, null) ;
			String cntFinal=dbActionTemplate.getSingleValue(cntSql);
			
	List<String[]> gridData = dbActionTemplate.getDataList(oSql.toString(), params);

	return gridData;
		}
		throw new NoDataFoundException("No Data Found");
		
}

@Override
public String getServicemstId(String taskid) throws Exception {
	// TODO Auto-generated method stub
	StringBuffer sql=new StringBuffer();
	sql.append("select extm_keyid from SAP_EXTERNAL_SERVICE_MST where EXTM_TASKID='"+taskid+"'");
	CommonMessage.debugMsg("extmid sql:"+sql.toString());
	String servicemstId=dbActionTemplate.getSingleValue(sql.toString());
	return servicemstId;
}
public  List<String[]> getServiceText(String servmId) throws Exception {
	String getTextSql="select SERVM_SHORT_TEXT from gen_tl_servicemst where SERVM_KEYID= '"+servmId+"'";
	List<String[]> shortText=dbActionTemplate.getDataList(getTextSql);
	return shortText;
}

@Override
public List<String[]> getBDAnalysisRpt(CommonFilter commonFilter) throws Exception {
// TODO Auto-generated method stub
try
{
String maintMode = commonFilter.getMaintMode();
List<String> paramValues = new ArrayList<String>();		

String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
String keyId = FilterCondSql.getComboSelectionId(commonFilter.getCmbbdRootCause());
String keyid = commonFilter.getMainkeyid();
if(keyid != null && keyid != "")
{
	if(keyid.substring(0,3).equals("FCT"))
		condParms += "FACTORYID="+keyid;
	else if(keyid.substring(0,3).equals("CMP"))
		condParms += "COMPANYID="+keyid;
	else if(keyid.substring(0,3).equals("LCN"))
		condParms += "LOCATIONID="+keyid;
	else if(keyid.substring(0,3).equals("LIN"))
		condParms += "SECTIONID="+keyid;
	else if(keyid.substring(0,3).equals("CEL"))
		condParms += "CELLID="+keyid;
	else if(keyid.substring(0,3).equals("MCH"))
		condParms += "MACHINEID="+keyid;
	else if(keyid.substring(0,3).equals("ASM"))
	{	
		condParms += "ASSEMBLYID="+keyid;
		condParms += ";MACHINEID="+commonFilter.getMachineId();
	}		
	condParms += ";";
}

paramValues.add(condParms);
paramValues.add(commonParams);

List<String[]> dataList =null;
if(CommonFunctions.isValidKeyId(maintMode))				
	 dataList =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETUNPLANNEDMAINTS", paramValues);
else
	dataList =  dbActionTemplate.processFunctionCalls("BDM_PC_BREAKDOWN.BDM_FN_BDANALYSISREPORTSUMMARY", paramValues);	
if( commonFilter.getViewClick() == 'Y'){
	String totalCnt = paramValues.get(0); 
	boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
	if(  isInteger ){
		commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	}
}
return dataList; 
}
catch(Exception e)
{
e.printStackTrace();
}
return null;
}

@Override
public Workbook getsummaryAnalysisExportExcel(CommonFilter commonFilter,
		JSONObject tblJSONObj, String format) throws Exception {
	// TODO Auto-generated method stub

	  ResultSet rs = null;
	   try
	   {
		   	rs =   getSummaryReportResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,format,2, -1,0 );
	   }
	   
	   finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());	   
	   }
}
private ResultSet getSummaryReportResultSet(CommonFilter commonFilter) throws Exception
{
	//List<String> paramValues = getFilterParamValues(commonFilter);
	
	List<String> paramValues = new ArrayList<String>();
	List<String[]> relatedMst = null;
//	String sql = AbnormalityReportSqls.getAbnAllocation(commonFilter);			
//	CommonMessage.debugMsg("Sqls..."+sql);
//	relatedMst = dbActionTemplate.getDataList(sql);
	String  condParam= FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParam  = FilterCondSql.getGridCommonParams(commonFilter);
	
	paramValues.add(condParam);
	paramValues.add(commonParam);
	
	return  dbActionTemplate.dbFunctionCall("BDM_PC_BREAKDOWN.BDM_FN_BDANALYSISREPORTSUMMARY", paramValues);
	
}

	
}

