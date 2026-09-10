package com.akranta.tpm.dao.impl;




import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

//import org.apache.poi.hslf.model.Table;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PlmTlStandardsDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlMultipleRespSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.BAL_CliTlStandardsSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlCbmstdcadtlSql;
import com.akranta.tpm.dao.sql.PlmTlGenmaintenanceSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlMethodsmstSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlMethodtasklistSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlMultipleRespSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlMultiplemethodsmstSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlPlanconfigurationSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlPmsftpermitlinkSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlStandardsSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlToolsdtlSql;
import com.akranta.tpm.dao.sql.BAL_PlmTlUnscheduledactmstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BAL_BdmTlMultipleResp;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl;
import com.akranta.tpm.model.BAL_PlmTlMethodsmst;
import com.akranta.tpm.model.BAL_PlmTlMethodtasklist;
import com.akranta.tpm.model.BAL_PlmTlMultipleResp;
import com.akranta.tpm.model.BAL_PlmTlMultiplemethodsmst;
//import com.akranta.tpm.model.BAL_PlmTlPlanconfiguration;
import com.akranta.tpm.model.BAL_PlmTlPmsftpermitlink;
import com.akranta.tpm.model.BAL_PlmTlStandards;
import com.akranta.tpm.model.BAL_PlmTlToolsdtl;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.service.api.BAL_PlmStandardsServiceApi;

import com.akranta.tpm.service.api.FunctionCallApi;

/* dao implementation */
public class BAL_PlmTlStandardsDaoImpl implements BAL_PlmTlStandardsDao {

	private static final String TBL_BDM_TL_YYCOUNTERMEASURELINK = "BDM_TL_YYCOUNTERMEASURELINK";
	private DBActionTemplate dbActionTemplate; 
	private  BAL_PlmTlMethodsmstSql plmTlMethodsmstSql;
	private  BAL_PlmTlToolsdtlSql plmTlToolsdtlSql;
	private BAL_PlmTlMultipleRespSql plmTlMultipleRespSql;
	private  BAL_PlmTlPmsftpermitlinkSql plmTlPmsftpermitlinkSql;
	private BAL_PlmTlStandardsSql plmTlStandardsSql ;
	private BAL_BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql;
	private BAL_PlmTlCbmstdcadtlSql plmTlCbmstdcadtlSql;
	private BAL_PlmStandardsServiceApi balpmstandards;
	FunctionCallApi fnCallApi;
	public BAL_PlmTlStandardsDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		plmTlPmsftpermitlinkSql = new BAL_PlmTlPmsftpermitlinkSql();
		plmTlMethodsmstSql = new BAL_PlmTlMethodsmstSql();
		plmTlToolsdtlSql = new BAL_PlmTlToolsdtlSql();
		plmTlStandardsSql = new BAL_PlmTlStandardsSql();
		plmTlMultipleRespSql =new BAL_PlmTlMultipleRespSql();
		bdmTlYycountermeasurelinkSql = new BAL_BdmTlYycountermeasurelinkSql();
		plmTlCbmstdcadtlSql = new BAL_PlmTlCbmstdcadtlSql();
	}
	//mano
	  public void BAL_PlmTlStandardsDaoImplJwt(String JwtToken) 
		{
			try{
				balpmstandards = new BAL_PlmStandardsServiceApi(JwtToken);
			fnCallApi = new FunctionCallApi(JwtToken);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_PlmTlStandards create(BAL_PlmTlStandards plmTlStandards,String dKeyId) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String toolreq = plmTlStandards.getPmsdIstoolsreq();
		
		try{
			System.out.println("inside DAO IMPL");
			plmTlStandards.setPmsdKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlStandardsSql.TBL_BAL_PLM_TL_STANDARDS, 12, "PMD", "YY", "Y"));  // set the sequnce number 
			 
			System.out.println("before INsert");
			String pmsdKeyId =plmTlStandards.getPmsdKeyid(); 
			System.out.println("dsdsds  sd  ss  "+toolreq);
			List <String[]> chkPlanExist = getchkplnexists("M",plmTlStandards.getPmsdMachineid());
			if(chkPlanExist.size() >0 )
				plmTlStandards.setPmsdPlanconfigstatus("Y");
			else{
				CommonFunctions.debugMsg("chkPlanExist   :"+chkPlanExist.size());
				plmTlStandards.setPmsdPlanconfigstatus("N");
			}
			sqls.add(BAL_PlmTlStandardsSql.getInsertSql(plmTlStandardsSql.getPmsdDbFields(), plmTlStandards.getSaveArray())); // add insert sql for master table
			String updatesql= BAL_PlmTlStandardsSql.updateDocUpdatesdatasql(dKeyId,pmsdKeyId);
			sqls.add(updatesql);
			System.out.println("after INsert");
			BdmTlYycountermeasurelink bdmTlYycountermeasureList = plmTlStandards.getCountermeasureLink();
			if(bdmTlYycountermeasureList != null )
			{//MLMM_REFDOCID
				CommonFunctions.debugMsg("Counter maesuDao");
				bdmTlYycountermeasureList.setYycmKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
				bdmTlYycountermeasureList.setYycmCountermsrid(plmTlStandards.getPmsdKeyid());
					String delCountrMeasr = "Delete from "+TBL_BDM_TL_YYCOUNTERMEASURELINK + "  where YYCM_REFDOCTYPE = 'PMD' AND YYCM_YYID = '"+bdmTlYycountermeasureList.getYycmYyid()+"'";
					sqls.add(delCountrMeasr );
					sqls.add(BAL_BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasureList.getSaveArray()));
				}
			
			List<BAL_PlmTlPmsftpermitlink> plmTlPmsftpermitlinkList  = plmTlStandards.getPermitlinkDetail();
					
					if(plmTlPmsftpermitlinkList != null && plmTlPmsftpermitlinkList.size()>0)
					{//MLMM_REFDOCID
						
						for(BAL_PlmTlPmsftpermitlink plmTlPmsftpermitlink:plmTlPmsftpermitlinkList)
						{
							//plmTlGenmaintenance.setGmntKeyid(dbActionTemplate.getSequenceNumber(PlmTlGenmaintenanceSql.TBL_PLM_TL_GENMAINTENANCE, 15, "GMN", "MMYY", "Y")); // set the sequnce number
							//plmTlPmsftpermitlink.setPsplSftpermitid(dbActionTemplate.getSequenceNumber(PlmTlPmsftpermitlinkSql.TBL_PLM_TL_PMSFTPERMITLINK));
							plmTlPmsftpermitlink.setPsplPmstandardid(plmTlStandards.getPmsdKeyid());
						
							//CommonFunctions.debugMsg("pspldbField     "+plmTlPmsftpermitlinkSql.getPsplDbFields() );
							sqls.add(BAL_PlmTlPmsftpermitlinkSql.getInsertSql(plmTlPmsftpermitlinkSql.getPsplDbFields(),plmTlPmsftpermitlink.getSaveArray()));
						}
						String pmsdKey = plmTlStandards.getPmsdKeyid();
						String delsql= BAL_PlmTlPmsftpermitlinkSql.delPermLinkdatasql(pmsdKey);
						sqls.add(delsql);
					}
		List <BAL_PlmTlMethodsmst> methodslist = plmTlStandards.getMethodDetail();
			
			if(methodslist != null && methodslist.size()>0)
			{//MLMM_REFDOCID
				for(BAL_PlmTlMethodsmst methodmst:methodslist)
				{
					methodmst.setPmmsKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlMethodsmstSql.TBL_PLM_TL_METHODSMST, 15, "PMM", "MMYY", "Y"));
					methodmst.setPmmsPmkeyid(plmTlStandards.getPmsdKeyid());
				 
				sqls.add(BAL_PlmTlMethodsmstSql.getInsertSql(plmTlMethodsmstSql.getPmmsDbFields(),methodmst.getSaveArray()));
				}
			}
			List <BAL_PlmTlCbmstdcadtl> cbmlist = plmTlStandards.getCbmData();
			
			if(cbmlist != null && cbmlist.size()>0)
			{//MLMM_REFDOCID
				for(BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl:cbmlist)
				{
					plmTlCbmstdcadtl.setCmdtKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlCbmstdcadtlSql.TBL_PLM_TL_CBMSTDCADTL, 12, "PCC", "MMYY", "Y")); // set the sequnce number 
					System.out.println("before INsert");
					plmTlCbmstdcadtl.setCmdtPmstandardid(plmTlStandards.getPmsdKeyid());
					sqls.add(BAL_PlmTlCbmstdcadtlSql.getInsertSql(plmTlCbmstdcadtlSql.getCmdtDbFields(), plmTlCbmstdcadtl.getSaveArray())); // add insert sql for master table
				}
			}
			//dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			List <BAL_PlmTlToolsdtl> toolsPickUplist = plmTlStandards.getToolsDetail();
		   if(toolsPickUplist != null && toolsPickUplist.size()>0)
			{//MLMM_REFDOCID
				for(BAL_PlmTlToolsdtl toolsPickUp:toolsPickUplist)
				{
					System.out.println("toolpickup ss  "+toolreq);
					if(toolreq.equals("N"))
					{ CommonFunctions.debugMsg("inside  sdf");
						sqls.add(BAL_PlmTlToolsdtlSql.getDeleteSql(plmTlToolsdtlSql.getPtldDbFields(),toolsPickUp.getSaveArray()));
					}
					else{
					toolsPickUp.setPtldKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlToolsdtlSql.TBL_BAL_PLM_TL_TOOLSDTL));
					//toolsPickUp.setPtldKeyid(dbActionTemplate.getSequenceNumber(PlmTlToolsdtlSql.TBL_PLM_TL_TOOLSDTL,13, "TLDll", "MMYY", null));
					System.out.println("toolpickup ss"+toolsPickUp.getPtldKeyid());
					toolsPickUp.setPtldStandardid(plmTlStandards.getPmsdKeyid());
					
				   
					sqls.add(BAL_PlmTlToolsdtlSql.getInsertSql(plmTlToolsdtlSql.getPtldDbFields(),toolsPickUp.getSaveArray()));
				}
				}	
			}	
		   
			System.out.println("Standard ID TOOLS :"+plmTlStandards.getPmsdKeyid());
			//multipleResponsibility(plmTlStandards,sqls);
			//sqls.add(sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return plmTlStandards;
	}
	
	private List<String> multipleResponsibility(BAL_PlmTlStandards plmTlStandards,List<String> sqls) throws Exception{
		if(plmTlStandards.getplmTlMultipleResp()!= null && plmTlStandards.getplmTlMultipleResp().size()>0) // check for detail table data
		{
			sqls.add(BAL_BdmTlMultipleRespSql.getDeleteSqlstr(plmTlStandards.getPmsdKeyid()));
	    	for(int i =0;i<plmTlStandards.getplmTlMultipleResp().size();i++)
			{	
	    		BAL_PlmTlMultipleResp plmTlMultipleResp = (BAL_PlmTlMultipleResp)plmTlStandards.getplmTlMultipleResp().get(i); // get detail info from list in empployee object
	    		plmTlMultipleResp.setPmrsRefid(plmTlStandards.getPmsdKeyid());				
	    		plmTlMultipleResp.setPmrsKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlMultipleRespSql.TBL_PLM_TL_MULTIPLE_RESP));
				sqls.add(BAL_PlmTlMultipleRespSql.getInsertSql(plmTlMultipleRespSql.getPmrsDbFields(), plmTlMultipleResp.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}

	public BAL_PlmTlStandards update(BAL_PlmTlStandards plmTlStandards,String dKeyId)throws Exception { 
		CommonFunctions.debugMsg("Update DaoImpl");
		List<String> sqls = new ArrayList<String>();
		String toolreq = plmTlStandards.getPmsdIstoolsreq();
		//PlmTlStandardsSql plmTlStandardsSql = new PlmTlStandardsSql();
		try {
			List <String[]> chkPlanExist = getchkplnexists("M",plmTlStandards.getPmsdMachineid());
			if(chkPlanExist.size() >0 )
				plmTlStandards.setPmsdPlanconfigstatus("Y");
			else{
				CommonFunctions.debugMsg("chkPlanExist   :"+chkPlanExist.size());
				plmTlStandards.setPmsdPlanconfigstatus("N");
			}
			sqls.add(BAL_PlmTlStandardsSql.getUpdateSql(plmTlStandardsSql.getPmsdDbFields(), plmTlStandards.getSaveArray()));
			String pmsdKeyId =plmTlStandards.getPmsdKeyid(); 
			String updatesql= BAL_PlmTlStandardsSql.updateDocUpdatesdatasql(dKeyId,pmsdKeyId);
			sqls.add(updatesql);
			List<BAL_PlmTlPmsftpermitlink> plmTlPmsftpermitlinkList  = plmTlStandards.getPermitlinkDetail();
			
			if(plmTlPmsftpermitlinkList != null && plmTlPmsftpermitlinkList.size()>0)
			{//MLMM_REFDOCID
				String pmsdKey = plmTlStandards.getPmsdKeyid();
				String delsql= BAL_PlmTlPmsftpermitlinkSql.delPermLinkdatasql(pmsdKey);
				sqls.add(delsql);
				for(BAL_PlmTlPmsftpermitlink plmTlPmsftpermitlink:plmTlPmsftpermitlinkList)
				{
					//plmTlGenmaintenance.setGmntKeyid(dbActionTemplate.getSequenceNumber(PlmTlGenmaintenanceSql.TBL_PLM_TL_GENMAINTENANCE, 15, "GMN", "MMYY", "Y")); // set the sequnce number
					//plmTlPmsftpermitlink.setPsplSftpermitid(dbActionTemplate.getSequenceNumber(PlmTlPmsftpermitlinkSql.TBL_PLM_TL_PMSFTPERMITLINK));
					plmTlPmsftpermitlink.setPsplPmstandardid(plmTlStandards.getPmsdKeyid());
					
				sqls.add(BAL_PlmTlPmsftpermitlinkSql.getInsertSql(plmTlPmsftpermitlinkSql.getPsplDbFields(),plmTlPmsftpermitlink.getSaveArray()));
				}
			}
			List <BAL_PlmTlMethodsmst> methodslist = plmTlStandards.getMethodDetail();
				
				if(methodslist != null && methodslist.size()>0)
				{//MLMM_REFDOCID
					for(BAL_PlmTlMethodsmst methodmst:methodslist)
					{
						methodmst.setPmmsKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlMethodsmstSql.TBL_PLM_TL_METHODSMST, 15, "PMM", "MMYY", "Y"));
						methodmst.setPmmsPmkeyid(plmTlStandards.getPmsdKeyid());
					 
					sqls.add(BAL_PlmTlMethodsmstSql.getInsertSql(plmTlMethodsmstSql.getPmmsDbFields(),methodmst.getSaveArray()));
					
					}
				}
				List <BAL_PlmTlCbmstdcadtl> cbmlist = plmTlStandards.getCbmData();
				
				if(cbmlist != null && cbmlist.size()>0)
				{//MLMM_REFDOCID
					for(BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl:cbmlist)
					{
						if(!UIUtils.isValidKeyId(plmTlCbmstdcadtl.getCmdtKeyid())){
							plmTlCbmstdcadtl.setCmdtKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlCbmstdcadtlSql.TBL_PLM_TL_CBMSTDCADTL, 12, "PCC", "MMYY", "Y")); // set the sequnce number
						System.out.println("before insert in update");
						sqls.add(BAL_PlmTlCbmstdcadtlSql.getInsertSql(plmTlCbmstdcadtlSql.getCmdtDbFields(), plmTlCbmstdcadtl.getSaveArray())); // add insert sql for master table 
						}	
						else{
						System.out.println("before update");
					    sqls.add(BAL_PlmTlCbmstdcadtlSql.getUpdateSql(plmTlCbmstdcadtlSql.getCmdtDbFields(), plmTlCbmstdcadtl.getSaveArray())); // add insert sql for master table
					    }
					}
				}
				//dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				List <BAL_PlmTlToolsdtl> toolsPickUplist = plmTlStandards.getToolsDetail();
			   if(toolsPickUplist != null && toolsPickUplist.size()>0)
				{//MLMM_REFDOCID
					for(BAL_PlmTlToolsdtl toolsPickUp:toolsPickUplist)
					{
						System.out.println("toolpickup ss");
						/**/
						if( ! dbActionTemplate.checkDuplicateValue(BAL_PlmTlToolsdtlSql.TBL_BAL_PLM_TL_TOOLSDTL,"PTLD_STANDARDID",plmTlStandards.getPmsdKeyid(),"")){
							toolsPickUp.setPtldKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlToolsdtlSql.TBL_BAL_PLM_TL_TOOLSDTL));
							System.out.println("toolpickup update  "+toolreq);
							toolsPickUp.setPtldStandardid(plmTlStandards.getPmsdKeyid());
							if(toolreq.equals("N"))
							{ CommonFunctions.debugMsg("inside  sdf");
								sqls.add(BAL_PlmTlToolsdtlSql.getDeleteSql(plmTlToolsdtlSql.getPtldDbFields(),toolsPickUp.getSaveArray()));
							}
							else{
							sqls.add(BAL_PlmTlToolsdtlSql.getInsertSql(plmTlToolsdtlSql.getPtldDbFields(), toolsPickUp.getSaveArray()));
							System.out.println("after checking dup insert Tools");
							}
						}
						else{
							System.out.println("after checking dup update Tools");
							sqls.add(BAL_PlmTlToolsdtlSql.getUpdateSql(plmTlToolsdtlSql.getPtldDbFields(), toolsPickUp.getSaveArray()));
						}
						/**/
					}
				}
				System.out.println("Standard ID TOOLS :"+plmTlStandards.getPmsdKeyid());
				//multipleResponsibility(plmTlStandards,sqls);
			  dbActionTemplate.executeStatements(sqls);
			  
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		
		return plmTlStandards;
	}
	
	public BAL_PlmTlStandards delete(BAL_PlmTlStandards plmTlStandards)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PlmTlStandardsSql plmTlStandardsSql = new BAL_PlmTlStandardsSql();
		try {
			
			sqls.add(BAL_PlmTlStandardsSql.getDeleteSql(plmTlStandardsSql.getPmsdDbFields(), plmTlStandards.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return plmTlStandards;
	}
	@Override
	public List<String[]> getAllsubtype() {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("inside dao Impl subtype");
			String sql = BAL_PlmTlStandardsSql.getSubtype();
			List<String[]> pmSubtypeList = dbActionTemplate.getDataList(sql);
			
			return pmSubtypeList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAllgridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
		
			
//			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
		 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
//			 
			//paramValues.add("");
			 String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			// String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				condParms += FilterCondSql.getComboSelectionId(commonFilter.getJobtype(),"ACTIVITYTYPE");
				paramValues.add(condParms);
			
		//	 paramValues.add(commonParams);
		 paramValues.add(commonParams);
			System.out.println("paramvalues//////////" + paramValues);
			List<String[]> pmStdList = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETACTFORPM", paramValues);			
			System.out.println(" ::: List  " + pmStdList.size());
			return pmStdList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public BAL_PlmTlStandards getFillValue(String pmstdKeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		BAL_PlmTlStandards plmTlStandards = new BAL_PlmTlStandards();
		String sql = BAL_PlmTlStandardsSql.getpmstdListSql(pmstdKeyid);
		System.out.println("in dao impl" );
		Object args [] = new Object [] { pmstdKeyid };
		plmTlStandards.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		
		
		return plmTlStandards;
	}

	@Override
	public List<String[]> getAllsprGriddata(String pmsdkeyid) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("inside dao Impl");
			String sql = BAL_PlmTlStandardsSql.getsprData(pmsdkeyid);
			List<String[]> pmSubtypeList = dbActionTemplate.getDataList(sql);
			
			return pmSubtypeList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getSprPickup(String standardId) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("standardId in doa imlpl"+standardId);
			List<String > paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlStandardsSql.getSparesPickupTbl();
			paramValues.add(standardId);
			System.out.println("String sql="+sql);
			List<String[]>SprPkupList = dbActionTemplate.getDataList(sql,paramValues);
			return SprPkupList;
		}
		
		catch(Exception e)
		{
			System.out.println("Exception in get SprPkup dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public void generateCalendar(String pmsdEffectivedate,
			String pmsdFactoryid, String pmsdSectionid, String pmsdCellid,
			String pmsdMachineid, String pmsdAssemblyid,
			String pmsdFrequencyunit, String pmsdMouldid,String month) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("inside generate function daoImpl" + pmsdEffectivedate);
		List<String> inParamValues = new ArrayList<String>();
		inParamValues.add(pmsdEffectivedate);
		inParamValues.add(pmsdFactoryid);
		inParamValues.add(pmsdSectionid);
		
		inParamValues.add(pmsdCellid);
		inParamValues.add(pmsdMachineid);
		inParamValues.add(pmsdAssemblyid);
		
		inParamValues.add(pmsdFrequencyunit);
		if(UIUtils.isValidKeyId(pmsdMouldid))
			inParamValues.add("MLD");
		else
			inParamValues.add("MCH");
		inParamValues.add(pmsdMouldid);

		inParamValues.add("{}");
		inParamValues.add(month);
		System.out.println("inParamValues  :"+inParamValues);
		
		
		 
		Object [] outParams = new Object[ 1 ];
		System.out.println("outParams   :"+outParams);
		dbActionTemplate.processPLSQLProceduresNew("PLM_PR_ANNUALCALGEN",inParamValues,outParams);
		System.out.println("outParamsAfter   :"+outParams);
	}

	@Override
	public List<String[]> getactSubValue(String replActSub) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("replActSub   :");
		String getsql= BAL_PlmTlStandardsSql.actSubdatasql(replActSub);
		return dbActionTemplate.getDataList(getsql);
	}

	@Override
	public List<String[]> getAlljhclittools(String toolpmsdid) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("inside dao Impl get tools ");
			String sql = BAL_PlmTlStandardsSql.gettooldata(toolpmsdid);
			List<String[]> addmachineList = dbActionTemplate.getDataList(sql);
			
			return addmachineList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getchkplnexists(String machorasswise, String machineId) {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("inside dao Impl getchkplnexists");
			List<String> paramValues = new ArrayList<String>();
			paramValues.add(machorasswise);
			paramValues.add(machineId);
			//List<String[]> chkplnexts =  dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_CHKPLANEXISTS",paramValues);
			List<String[]> chkplnexts =  dbActionTemplate.processFunctionCalls("PLM_FN_CHKPLANEXISTS",paramValues);
			CommonFunctions.debugMsg(chkplnexts.size());
			return chkplnexts;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAllgridassmData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
		
			
//			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
//			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
//			 
			//paramValues.add("");
			 String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			// if(UIUtils.isValidKeyId(commonFilter.getJobtype()))
					condParms += FilterCondSql.getComboSelectionId(commonFilter.getJobtype(),"ACTIVITYTYPE");
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			// paramValues.add(commonParams);
			 //System.out.println("activity type  "+commonFilter.getJobtype().getId() +" -- "+commonFilter.getJobType().g);
			System.out.println("paramvalues" + paramValues);
			//List<String[]> pmStdassmList = dbActionTemplate.processFunctionCallsWithColHeaders("PLM_PC_PLANNEDMAINT.PLM_FN_TRADEWISECOUNT", paramValues);
			List<String[]> pmStdassmList = dbActionTemplate.processFunctionCallsWithColHeaders("PLM_FN_TRADEWISECOUNT_SB", paramValues);
			System.out.println(" ::: List  " + pmStdassmList.size());
			 if( commonFilter.getViewClick() == 'Y'){
					String totalCnt = paramValues.get(0); 
					CommonFunctions.debugMsg("totalCnt==" + totalCnt);
					boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
					if(  isInteger ){
						commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
					}
				 }
			return pmStdassmList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getassmgridData(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = new ArrayList<String>();
		    String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
			 String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
				condParms += FilterCondSql.getComboSelectionId(commonFilter.getJobtype(),"ACTIVITYTYPE");
			 paramValues.add(condParms);
			 paramValues.add(commonParams);
			// paramValues.add(commonParams);
			System.out.println("paramvalues" + commonFilter.getTrade()+"-----"+commonFilter.getcboActivitytype());
			List<String[]> pmStdassmList = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_GETASSEMBLYFORMCH", paramValues);			
			System.out.println(" ::: List  " + pmStdassmList.size());
			return pmStdassmList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public String delSpares(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from "+TableNames.TBL_PLM_TL_SPAREDTL+" where PSPD_STANDARDID = '"+pmstdId+"'";
			String updatePmstd = "update "+TableNames.TBL_PLM_TL_STANDARDS+" set  PMSD_ISSPARESREQ = 'N' where PMSD_KEYID = '"+pmstdId+"'";
			dbActionTemplate.executeStatement(updatePmstd);
			dbActionTemplate.executeStatement(sql);
			return "Success";
		}catch(Exception e){
			return "fail";
		}
		
	}
	@Override
	public String delTool(String pmstdId) throws Exception {
		// TODO Auto-generated method stub
		try{
			String sql = "delete from "+TableNames.TBL_PLM_TL_TOOLSDTL+" where PTLD_STANDARDID = '"+pmstdId+"'";
			String updatePmstd = "update "+TableNames.TBL_PLM_TL_STANDARDS+" set  PMSD_ISTOOLSREQ = 'N' where PMSD_KEYID = '"+pmstdId+"'";
			dbActionTemplate.executeStatement(updatePmstd);
			dbActionTemplate.executeStatement(sql);
			return "Success";
		}catch(Exception e){
			return "fail";
		}
		
	}

	@Override
	public List<String[]> getCBM(String pmStandardId) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("standardId in doa imlpl"+pmStandardId);
			List<String> paramValues = new ArrayList<String>();
			String sql = BAL_PlmTlStandardsSql.getCBMTbl(pmStandardId);
			List<String[]>cbmList = null;
			
			if(!UIUtils.isValidKeyId(pmStandardId))
				paramValues.add(null);
			else
				paramValues.add(pmStandardId);
			
				cbmList = dbActionTemplate.getDataList(sql,paramValues);
			/*else
				cbmList = dbActionTemplate.getDataList(sql);*/
			System.out.println("String sql="+sql);
			
			return cbmList;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception in get cbm dao impl"+e.getMessage());
		}
		return null;
	}

	@Override
	public BAL_PlmTlCbmstdcadtl createCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			String dKeyId) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	
		
		try{
			System.out.println("inside DAO IMPL");
			newPlmTlCbmstdcadtl.setCmdtKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlCbmstdcadtlSql.TBL_PLM_TL_CBMSTDCADTL, 12, "PCC", "MMYY", "Y")); // set the sequnce number 
			System.out.println("before INsert");
			String pmsdKeyId =newPlmTlCbmstdcadtl.getCmdtKeyid(); 
			System.out.println("dsdsds  sd  ss  ");
			
		
			sqls.add(BAL_PlmTlCbmstdcadtlSql.getInsertSql(plmTlCbmstdcadtlSql.getCmdtDbFields(), newPlmTlCbmstdcadtl.getSaveArray())); // add insert sql for master table
		
			System.out.println("after INsert");
			
			System.out.println("CBM ID  :"+newPlmTlCbmstdcadtl.getCmdtKeyid());
			
			//sqls.add(sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newPlmTlCbmstdcadtl;
	}

	@Override
	public BAL_PlmTlCbmstdcadtl updateCBM(BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl,
			String dKeyId) throws Exception {
		// TODO Auto-generated method stub
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	
		
		try{
			System.out.println("inside DAO IMPL");
			//newPlmTlCbmstdcadtl.setCmdtKeyid(dbActionTemplate.getSequenceNumber(PlmTlCbmstdcadtlSql.TBL_PLM_TL_CBMSTDCADTL)); // set the sequnce number 
			System.out.println("before update");
			String pmsdKeyId =newPlmTlCbmstdcadtl.getCmdtKeyid(); 
			System.out.println("dsdsSDdDSDSD ");
			
		
			sqls.add(BAL_PlmTlCbmstdcadtlSql.getUpdateSql(plmTlCbmstdcadtlSql.getCmdtDbFields(), newPlmTlCbmstdcadtl.getSaveArray())); // add insert sql for master table
		
			System.out.println("after update");
			
			System.out.println("CBM ID  :"+newPlmTlCbmstdcadtl.getCmdtKeyid());
			
			//sqls.add(sqls);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newPlmTlCbmstdcadtl;
	}

	@Override
	public BAL_PlmTlCbmstdcadtl getFillValueCMB(String pmsdId) throws Exception {
		// TODO Auto-generated method stub
		BAL_PlmTlCbmstdcadtl plmTlCbmstdcadtl = new BAL_PlmTlCbmstdcadtl();
		String sql = BAL_PlmTlCbmstdcadtlSql.getCMBListSql(pmsdId);
		System.out.println("in dao impl" );
		Object args [] = new Object [] { pmsdId };
		plmTlCbmstdcadtl.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		return plmTlCbmstdcadtl;
	}

	@Override
	public List<String[]> getPermitLinkData(String pmStandardId)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<String> paramValues = new ArrayList<String>();
		paramValues.add(pmStandardId);
		System.out.println("paramValues"+paramValues );
		List<String[]> permitLinkList = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_PMSFTPERMITLINK", paramValues);
		return permitLinkList;
	}

	@Override
	public List<String[]> getfillJobType() throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			String sql = "SELECT DISTINCT '',PMAM_CODE,PMAM_NAME  FROM  PLM_TL_ACTIVITYTYPEMST";
			
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
	
		return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getfillActivity() throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			String sql = "SELECT '' AS CHKSELECTED,PMSD_KEYID,PMSD_ACTIVITY FROM PLM_TL_STANDARDS WHERE PMSD_ACTIVITYTYPE ='SDM'";
			
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
	
		return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getCbmGrid(CommonFilter commonFilter)  throws Exception 
	{

      String sql="  select '','X','X','X' FROM DUAL";
      sql+=" UNION ALL select '' ,'1', '10:15', '12' FROM DUAL";
      sql+=" UNION ALL select '' ,'2', '11:25', '12' FROM DUAL";
      sql+=" UNION ALL select '' ,'3', '12:15', '12' FROM DUAL";
      sql+=" UNION ALL select '' ,'4', '1:30',  '10' FROM DUAL";
      sql+=" UNION ALL select '' ,'5', '2:35',  '10' FROM DUAL";
      sql+=" UNION ALL select '' ,'6', '3:25',  '09' FROM DUAL";
		 List<String[]> gridData = dbActionTemplate.getDataList(sql);
		 System.out.println("Inside DAO IMpl "+sql);
		 System.out.println("Inside DAO IMpl "+"size"+gridData.size());
		 return gridData;
	}

	@Override
	public List<String[]> getPMReport(CommonFilter commonFilter)
			throws Exception {
		 List<String> params=null;
		StringBuffer sql=new StringBuffer();
		sql.append("select 'Date','Equipment','Description Of Activity','Measuring Point','Data Upload','CBM Action','Work Order NO.','Status'");
		sql.append(" From dual union all");
		sql.append(" select '02-OCT-2013','STATION BATTERY CHARGER(NFL-1)','BEARING CONDITIONAL SHOULD BE DONE BY VIBRATION ANALYSIS','Measuring Point 1','','','WO13110066','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '14-NOV-2013','STATION BATTERY CHARGER(NFL-1)','DESCRIPTION OF ACTIVITY 1','Measuring Point 2','','','WO13110070','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '19-OCT-2013','NUMERIC UPS-2 (NFL-1)','DESCRIPTION OF ACTIVITY 2','Measuring Point 3','','','WO13110069','COMPLETED'");
		sql.append(" From dual union all");
		sql.append(" select '22-NOV-2013','STATION BATTERY CHARGER(NFL-1)','DESCRIPTION OF ACTIVITY 3','Measuring Point 4','','','WO13110067','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '07-OCT-2013','BSW-3 APFC','DESCRIPTION OF ACTIVITY 4','Measuring Point 5','','','WO13110064','WORK IN PROGRESS'");
		sql.append(" From dual union all");
		sql.append(" select '03-DEC-2013','STATION BATTERY CHARGER(NFL-1)','DESCRIPTION OF ACTIVITY 5','Measuring Point 6','','','WO13110064','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '05-OCT-2013','BSW-3 APFC','DESCRIPTION OF ACTIVITY 6','Measuring Point 7','','','WO13110064','WORK IN PROGRESS'");
		sql.append(" From dual union all");
		sql.append(" select '11-DEC-2013','NUMERIC UPS-2 (NFL-1).','DESCRIPTION OF ACTIVITY 7','Measuring Point 8','','','WO13110064','COMPLETED'");
		sql.append(" From dual union all");
		sql.append(" select '12-OCT-2013','STATION BATTERY CHARGER(NFL-1)','DESCRIPTION OF ACTIVITY 8','Measuring Point 9','','','WO13110064','WORK IN PROGRESS'");
		sql.append(" From dual union all");
		sql.append(" select '13-OCT-2013','NFL-1 MCC IO RACK AHU','DESCRIPTION OF ACTIVITY 9','Measuring Point 10','','','WO13110064','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '22-DEC-2013','NUMERIC UPS-2 (NFL-1)','DESCRIPTION OF ACTIVITY 10','Measuring Point 11','','','WO13110064','COMPLETED'");
		sql.append(" From dual union all");
		sql.append(" select '01-OCT-2013','STATION BATTERY CHARGER(NFL-1)','DESCRIPTION OF ACTIVITY 11','Measuring Point 12','','','WO13110064','WORK IN PROGRESS'");
		sql.append(" From dual union all");
		sql.append(" select '06-OCT-2013','BSW-3 APFC','DESCRIPTION OF ACTIVITY 12','Measuring Point 13','','','WO13110064','PENDING'");
		sql.append(" From dual union all");
		sql.append(" select '03-NOV-2013','NFL-1 MCC IO RACK AHU','DESCRIPTION OF ACTIVITY 13','Measuring Point 14','','','WO13110064','WORK IN PROGRESS'");
		sql.append(" From dual ");
		List<String[]> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		System.out.println("sql.... "+sql);
		return gridData;
	}
	
	public List<String[]> getMethodTaskList(CommonFilter commonFilter)throws Exception{
//		return dbActionTemplate.getDataList(PlmTlStandardsSql.getMethodTaskList(machineId));
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
            String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			//condParms += FilterCondSql.getComboSelectionId(commonFilter.getJobtype(),"ACTIVITYTYPE");
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("paramvalues" + paramValues);
			List<String[]> pmStdList = dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_TASKLISTDEFINITION", paramValues);			
			System.out.println(" ::: List  " + pmStdList.size());
			return pmStdList;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public BAL_PlmTlMethodtasklist createMethodTask(BAL_PlmTlMethodtasklist newPlmTlMethodtasklist) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PlmTlMethodtasklistSql plmTlMethodtasklistsql = new BAL_PlmTlMethodtasklistSql();
		List<BAL_PlmTlMethodtasklist> pmMethidTaskList = newPlmTlMethodtasklist.getPlmTlMethodtasklist();
		for(BAL_PlmTlMethodtasklist plmTlMethodtasklist : pmMethidTaskList){
			plmTlMethodtasklist.setMtskKeyid(dbActionTemplate.getSequenceNumber(BAL_PlmTlMethodtasklistSql.TBL_PLM_TL_METHODTASKLIST , 8, "MSTK", "", "Y"));
			sqls.add(BAL_PlmTlMethodtasklistSql.getInsertSql(plmTlMethodtasklistsql.getMtskDbFields(), plmTlMethodtasklist.getSaveArray()));
		}
		dbActionTemplate.executeStatements(sqls);
		return newPlmTlMethodtasklist;
	}
	//TO COPY THE STATNDARDS
	@Override
	public List<String[]> getEquipmentList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String[]> pmStdList =null;
		//List<String[]> sucess=new ArrayList<String[]>();
		//String[] sucs=new String[0];
		//String[] error=new String[0];
		//sucs[0]="success";
		//error[0]="error";
		try
		{
			
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getPMRelatedCondStr(commonFilter);
            String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			//condParms += FilterCondSql.getComboSelectionId(commonFilter.getJobtype(),"ACTIVITYTYPE");
            if(commonFilter.getCellId()!=null)
            {
            	condParms += "CellID="+commonFilter.getCellId()+";";
            }
            
            if(commonFilter.getMachineId()!=null)
            {
            	condParms += "MCHID="+commonFilter.getMachineId()+";";
            }
			paramValues.add(condParms);
			paramValues.add(commonParams);
			System.out.println("paramvalues" + paramValues);
			pmStdList= dbActionTemplate.processFunctionCalls("PLM_PC_PLANNEDMAINT.PLM_FN_EQUIPMENTLINK", paramValues);			
			System.out.println(" ::: List  " + pmStdList.size());
			//pmStdList.add(sucs);
	
		
	}
		catch(Exception e)
		{
			System.out.println("e"+e);
			//pmStdList.add(error);
		}
		return pmStdList;
}

	@Override
	public String getEquipflid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", commonFilter.getCellId());
	}

	@Override
	public List<String[]> copyStandards(List<String> eqlist, List<String> actlst,String elementid,String tradeid)
			throws Exception {
		// TODO Auto-generated method stub
		try{
		int i,j;
		String  machineid;
		String  activityid;
		String elmnid = null;
		String tradekeyid=null;
		
		
		System.out.println("elementid"+elementid);
		if(actlst.size()!=0&&actlst!=null)
		{
			String standardid=actlst.get(0);
			tradekeyid=dbActionTemplate.getSingleValue("PLM_TL_STANDARDS", "PMSD_TRADEID", "PMSD_KEYID", standardid );
			System.out.println("tradekeyid"+tradekeyid);
		}
		
		if(elementid!=null&&elementid.length()!=0)
		{
			
			 elmnid=dbActionTemplate.getSingleValue("GEN_TL_FUNCTIONALLOCN", "FNLN_ELEMENTID", "FNLN_ORIGINALID", elementid );
			
		}
		String section=elmnid.substring(33, 43);
		System.out.println("section:   "+section +"    elmnid:  "+elmnid);
		
		for(i=0;i<eqlist.size();i++)
		{
			machineid=eqlist.get(i);
			System.out.println("Machineid"+machineid);
			if(machineid!=null)
				{
				//System.out.println("elmnid"+elmnid+"-"+machineid);
				for(j=0;j<actlst.size();j++)
				{
				   activityid=actlst.get(j);
				   System.out.println("activity id::"+activityid);
				   System.out.println("activity id::"+tradeid);
				   
				   List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
					
					paramValues.add(activityid);
					paramValues.add(machineid);
					paramValues.add(elmnid+"-"+machineid);
					
					if(tradekeyid!=null){
				    paramValues.add(tradekeyid);
					}					
					paramValues.add(elementid);
					paramValues.add(section);
					System.out.println("procedure returned IN DAI "+ paramValues);
					dbActionTemplate.processPLSQLProcedures("PLM_PC_COPYSTANDARD.INSERT_COPYSTANDARDS",paramValues,outParam);
					System.out.println("procedure returned");
				}
				
				
			}
			
			
		}
		return null;
		}
		catch(Exception E)
		{
			throw new Exception("Standards Not Copied!!!");
		}
	}
	public String delteStandards(String detailKeyid, String cellId,
			String machineId)  {
		// TODO Auto-generated method stub
		try{
		List<String> sqls = new ArrayList<String>();
		String keyids=detailKeyid.replaceAll("\\\"", "\\'");
		CommonFunctions.debugMsg(" replace keyids "+keyids);
		String sql= " UPDATE PLM_TL_STANDARDS SET PMSD_ACTIVE='N' ,PMSD_MODIFIEDON=SYSDATE WHERE PMSD_MACHINEID='"+machineId+"' AND PMSD_CELLID='"+cellId+"' AND PMSD_KEYID IN ("+keyids+") ";
		String sql1=" UPDATE PLM_TL_CALENDAR  SET PMCL_ACTIVE='N' ,PMCL_MODIFIEDON=SYSDATE WHERE PMCL_MACHINEID='"+machineId+"' AND PMCL_CELLID='"+cellId+"' AND PMCL_PMREFID IN("+keyids+") AND PMCL_FROMDATE>=SYSDATE AND PMCL_STATUS<>'Y'";
		String sql2=" DELETE FROM PLM_TL_WODTL WHERE PWDD_CALENDARID IN (SELECT PMCL_KEYID FROM PLM_TL_CALENDAR WHERE  PMCL_MACHINEID='"+machineId+"' AND PMCL_CELLID='"+cellId+"'  AND PMCL_PMREFID IN("+keyids+") AND PMCL_FROMDATE>=SYSDATE) AND PWDD_STATUS<>'C'";
		sqls.add(sql);
		sqls.add(sql1);
		sqls.add(sql2);
		 dbActionTemplate.executeStatements(sqls);
		return "Data Deleted Successfully.";
		}
		catch(Exception e){
		return "Data Not Deleted .";
		}
	}
	@Override
	public Workbook stdExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
			 ResultSet rs = null;
			   try{
				
				rs =   getStandardsResultset(commonFilter);
				ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
				return excelUtils.writeToExcel(rs,format, 1,0,0 );
			   }
			   catch(Exception e){
				   e.printStackTrace();
				   
				   
			   }
			   finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }
			return null;
		}
		private ResultSet getStandardsResultset(CommonFilter commonFilter) throws Exception
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			
				return dbActionTemplate.dbFunctionCall("PLM_PC_PLANNEDMAINT.PLM_FN_TRADEWISECOUNTEXCEL", paramValues);
			
		}
		private List<String> getFilterParamValues(CommonFilter commonFilter){
			List<String> paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getBDRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			return paramValues;
		}
		@Override
		public List<String[]> getMultiplePmsdList(CommonFilter commonFilter) throws Exception {
		    try {
		        List<String> paramValues = new ArrayList<String>();

		        String machId  = CommonFunctions.isValidKeyId(commonFilter.getMachineId()) ? commonFilter.getMachineId() : "{}";
		        String flId    = CommonFunctions.isValidKeyId(commonFilter.getFlid())      ? commonFilter.getFlid()      : "{}";
		      //  String tradeId = CommonFunctions.isValidKeyId(commonFilter.getTrade())   ? commonFilter.getTradea()   : "{}";
		        String tradeVal = (commonFilter.getTrade() != null) ? commonFilter.getTrade().getId() : null;
		        String tradeId  = CommonFunctions.isValidKeyId(tradeVal) ? tradeVal : "{}";

		        paramValues.add(machId);
		        paramValues.add(flId);
		        paramValues.add(tradeId);

		        return dbActionTemplate.processFunctionCalls("plm_fn_getmultiplepmstandards", paramValues);

		    } catch (Exception e) {
		        throw new Exception(e.getMessage());
		    }
		}
	}


